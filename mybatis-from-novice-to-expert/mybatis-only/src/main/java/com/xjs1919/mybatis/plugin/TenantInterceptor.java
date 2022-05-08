package com.xjs1919.mybatis.plugin;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author jiashuai.xujs
 * @date 2022/4/2 9:41
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class TenantInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        Method mapperMethod = getMapperMethodByStatementId(mappedStatement.getId());
        EnableInjectTenantId annotation = mapperMethod.getAnnotation(EnableInjectTenantId.class);
        if(annotation != null && !annotation.value()){
            return invocation.proceed();
        }
        try {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            Object parameter = invocation.getArgs()[1];
            if (parameter instanceof Map) {
                Map parameterMap = (Map) parameter;
                parameterMap.put("tenantId", 12);
                // 遍历map的所有的value
                for(Object entry : parameterMap.entrySet()){
                    Object value = ((Map.Entry)entry).getValue();
                    // 如果是个List，填充List的元素
                    if(List.class.isAssignableFrom(value.getClass())){
                        for (Object paramBean : (List)value) {
                            MetaObject metaObject = configuration.newMetaObject(paramBean);
                            if (metaObject.hasSetter("tenantId")) {
                                metaObject.setValue("tenantId", "12");
                            }
                        }
                    }else {
                        //否则 填充对象本身
                        MetaObject metaObject = configuration.newMetaObject(value);
                        if (metaObject.hasSetter("tenantId")) {
                            metaObject.setValue("tenantId", "12");
                        }
                    }
                }
            } else if (typeHandlerRegistry.hasTypeHandler(parameter.getClass())) {
                BoundSql boundSql = mappedStatement.getBoundSql(parameter);
                MapperMethod.ParamMap paramMap = new MapperMethod.ParamMap<>();
                paramMap.put(boundSql.getParameterMappings().get(0).getProperty(), parameter);
                paramMap.put("tenantId", 12);
                invocation.getArgs()[1] = paramMap;
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameter);
                metaObject.setValue("tenantId", "12");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private Method getMapperMethodByStatementId(String statementId){
        String mapperClassName = statementId.substring(0, statementId.lastIndexOf("."));
        String methodName = statementId.substring(statementId.lastIndexOf(".")+1);
        try{
            Class mapperClass = Class.forName(mapperClassName);
            Method[] methods = mapperClass.getMethods();
            Method mapperMethod = null;
            for(Method method : methods){
                if(method.getName().equals(methodName)){
                    mapperMethod = method;
                    break;
                }
            }
            return mapperMethod;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
