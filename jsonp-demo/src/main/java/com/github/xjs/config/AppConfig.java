package com.github.xjs.config;

import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

@Configuration
public class AppConfig {
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter() {
            protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
                // 我们上面设置的值在这里用上了，关键就在该值是否有
                // 只有有值的情况下我们才会进行JSONP的处理
                String jsonpFunction = (object instanceof JsonpMappingJacksonValue ? ((JsonpMappingJacksonValue) object).getJsonpFunction() : null);
                if (jsonpFunction != null) {
                    generator.writeRaw("/**/");
                    generator.writeRaw(jsonpFunction + "(");
                }
            }
            protected void writeSuffix(JsonGenerator generator, Object object) throws IOException {
                String jsonpFunction = (object instanceof JsonpMappingJacksonValue ? ((JsonpMappingJacksonValue) object).getJsonpFunction() : null);
                if (jsonpFunction != null) {
                    generator.writeRaw(");");
                }
            }
        };
        return converter;
    }
}
