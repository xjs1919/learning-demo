package com.github.xjs.sbdemo.enums;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月13日 下午3:47:12<br/>
 */
public class EnumRegistrar implements  ImportBeanDefinitionRegistrar {
	
	private static Logger log = LoggerFactory.getLogger(EnumRegistrar.class);
	private static final String RESOURCE_PATTERN = "**/*.class";
	private static final String CLASSPATH_URL_PREFIX = "classpath:";
	
	@Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annAttr = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnumComponentScan.class.getName()));
        String[] basePackages = annAttr.getStringArray("value");
        if (ObjectUtils.isEmpty(basePackages)) {
            basePackages = annAttr.getStringArray("basePackages");
        }
        if (ObjectUtils.isEmpty(basePackages)) {
            basePackages = new String[] {ClassUtils.getPackageName(importingClassMetadata.getClassName())};
        }
        List<Class<?>> candidates = scanPackages(basePackages, null, null);
        if (candidates.isEmpty()) {
            return;
        }
        for(Class<?> clazz :candidates ) {
        	EnumFactory.addAll(clazz);
        }
    }
	
	private List<Class<?>> scanPackages(String[] basePackages, List<TypeFilter> includeFilters, List<TypeFilter> excludeFilters) {
        List<Class<?>> candidates = new ArrayList<Class<?>>();
        for (String pkg : basePackages) {
            try {
            	List<Class<?>> list = findCandidateClasses(pkg, includeFilters, excludeFilters);
            	if(list != null) {
            		 candidates.addAll(list);
            	}
            } catch (IOException e) {
            	log.error("扫描包["+pkg+"]时出现异常",e);
                continue;
            }
        }
        return candidates;
    }
	private List<Class<?>> findCandidateClasses(String basePackage, List<TypeFilter> includeFilters, List<TypeFilter> excludeFilters) throws IOException {
        List<Class<?>> candidates = new ArrayList<Class<?>>();
        String packageSearchPath = CLASSPATH_URL_PREFIX + replaceDotByDelimiter(basePackage) + '/' + RESOURCE_PATTERN;
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory(resourceLoader);
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(packageSearchPath);
        for (Resource resource : resources) {
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            Class<?> candidateClass = transform(reader.getClassMetadata().getClassName());
            if (candidateClass != null && BaseEnum.class.isAssignableFrom(candidateClass) && candidateClass != BaseEnum.class) {
                candidates.add(candidateClass);
                log.info("扫描到Enum类：{}",candidateClass.getSimpleName());
            }
        }
        return candidates;
    }
	
	private Class<?> transform(String className) {
        Class<?> clazz = null;
        try {
            clazz = ClassUtils.forName(className, this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
        	log.info("未找到类:{}", className);
        }
        return clazz;
    }

	protected boolean isCandidateResource(MetadataReader reader, MetadataReaderFactory readerFactory, List<TypeFilter> includeFilters,
            List<TypeFilter> excludeFilters) throws IOException {
			if(excludeFilters != null && excludeFilters.size() > 0) {
				for (TypeFilter tf : excludeFilters) {
					if (tf.match(reader, readerFactory)) {
						return false;
					}
				}
			}
			if(includeFilters != null && includeFilters.size() > 0) {
				for (TypeFilter tf : includeFilters) {
					if (tf.match(reader, readerFactory)) {
						return true;
					}
				}
			}
			return false;
	}

	private String replaceDotByDelimiter(String path) {
        return StringUtils.replace(path, ".", "/");
    }

}
