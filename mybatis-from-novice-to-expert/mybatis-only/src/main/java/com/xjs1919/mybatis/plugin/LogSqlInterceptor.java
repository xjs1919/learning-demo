package com.xjs1919.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
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
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

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
),
@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class LogSqlInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        Method mapperMethod = getMapperMethodByStatementId(mappedStatement.getId());
        EnableLogSql annotation = mapperMethod.getAnnotation(EnableLogSql.class);
        if(annotation != null && !annotation.value()){
            return invocation.proceed();
        }
        try {
            Object parameter = invocation.getArgs()[1];
            Configuration configuration = mappedStatement.getConfiguration();
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String sql = getSql(configuration, boundSql);
            System.out.println(sql);
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

    public static String getSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings == null || parameterMappings.size() <= 0 || parameterObject == null) {
            return sql;
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(formatParameterValue(parameterObject)));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for(ParameterMapping parameterMapping : parameterMappings){
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object parameterValue = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(formatParameterValue(parameterValue)));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object parameterValue = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(formatParameterValue(parameterValue)));
                } else {
                    sql = sql.replaceFirst("\\?", "**UNKNOWN**");
                }
            }
        }
        return sql;
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

    private static String formatParameterValue(Object obj) {
        if (obj instanceof String) {
            return "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(2, 2, Locale.CHINA);
            return "'" + formatter.format((Date)obj) + "'";
        } else if (obj != null) {
            return obj.toString();
        } else {
            return "";
        }
    }

}
