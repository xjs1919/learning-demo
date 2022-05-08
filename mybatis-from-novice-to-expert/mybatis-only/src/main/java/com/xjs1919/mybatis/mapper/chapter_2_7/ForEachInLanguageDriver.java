package com.xjs1919.mybatis.mapper.chapter_2_7;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 若鱼1919
 * @date 2022/3/30 0030 19:46
 */
public class ForEachInLanguageDriver extends XMLLanguageDriver {

    private final Pattern inPattern = Pattern.compile("\\(#\\{(\\w+)\\}\\)");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType){
        Matcher matcher = inPattern.matcher(script);
        if (matcher.find()) {
            script = matcher.replaceAll("<foreach collection=\"$1\" item=\"id\" open=\"(\" close=\")\"  separator=\",\">#{id}</foreach>");
        }
        script = "<script>"+script+"</script>";
        return super.createSqlSource(configuration, script, parameterType);
    }
}
