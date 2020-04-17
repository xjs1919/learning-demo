package com.github.xjs.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.*;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.*;

@Configuration
public class ConfigRefreshAutoConfiguration implements BeanFactoryAware, EnvironmentAware, BeanDefinitionRegistryPostProcessor,
        ApplicationListener<RefreshEvent> {

    private Environment environment;
    private BeanFactory beanFactory;
    private BeanDefinitionRegistry registry;
    private List<String> beanNames = new ArrayList<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment){
        this.environment = environment;
    }

    /**
     * 收到刷新请求
     * */
    @Override
    public void onApplicationEvent(RefreshEvent event){
        //刷新配置
        RefreshScope.RefreshedConfig refreshedConfig = (RefreshScope.RefreshedConfig)event.getSource();
        String configFileName = refreshedConfig.getYml();
        Map<String, Object> newProperties = refreshedConfig.getProperties();
        PropertySource oldPropertySource = null;
        MutablePropertySources propertySources = ((ConfigurableEnvironment)environment).getPropertySources();
        for(Iterator<PropertySource<?>> it = propertySources.iterator(); it.hasNext(); ){
            PropertySource propertySource = it.next();
            String propertySourceName = propertySource.getName();
            if(propertySourceName.indexOf(configFileName) > 0){
                oldPropertySource = propertySource;
                break;
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(newProperties);
        MapPropertySource ps = new MapPropertySource(oldPropertySource.getName(), map);
        ((ConfigurableEnvironment)environment).getPropertySources().remove(oldPropertySource.getName());
        ((ConfigurableEnvironment)environment).getPropertySources().addLast(ps);
        //刷新beans
        for(String beanName : beanNames){
            registry.removeBeanDefinition(beanName);
        }
        postProcessBeanDefinitionRegistry(this.registry);
    }

    /**
     * 扫描系统中需要刷新的Bean
     * */
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException{
        this.registry = registry;
        //注册RefreshScope这个bean，它实现了BeanFactoryPostProcessor，在里面可以注册Scope
        if(!registry.containsBeanDefinition(RefreshScope.class.getName())){
            BeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(RefreshScope.class).getBeanDefinition();
            registry.registerBeanDefinition(RefreshScope.class.getName(), bd);
        }
        //做refresh Bean的扫描
        RefreshBeanScanner scanner = new RefreshBeanScanner(registry, beanNames);
        scanner.setAnnotationClass(com.github.xjs.config.anno.RefreshScope.class);
        scanner.registerFilters();
        scanner.doScan(AutoConfigurationPackages.get(this.beanFactory).toArray(new String[0]));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    public static class RefreshBeanScanner extends ClassPathBeanDefinitionScanner {
        private Class annotationClass;
        private List<String> beanNames;
        public RefreshBeanScanner(BeanDefinitionRegistry registry, List<String> beanNames) {
            super(registry);
            this.beanNames = beanNames;
        }
        public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
            this.annotationClass = annotationClass;
        }
        public void registerFilters() {
            if (this.annotationClass != null) {
                this.addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            }
        }
        public Set<BeanDefinitionHolder> doScan(String[] basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            if (beanDefinitions.isEmpty()) {
                this.logger.warn("No mapper was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
            } else {
                Iterator iterator = beanDefinitions.iterator();
                while(iterator.hasNext()) {
                    BeanDefinitionHolder holder = (BeanDefinitionHolder)iterator.next();
                    String beanName = holder.getBeanName();
                    beanNames.add(beanName);
                    GenericBeanDefinition definition = (GenericBeanDefinition)holder.getBeanDefinition();
                    definition.setScope(RefreshScope.class.getSimpleName());
                }
            }
            return beanDefinitions;
        }
        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return beanDefinition.getMetadata().isIndependent();
        }
    }
}
