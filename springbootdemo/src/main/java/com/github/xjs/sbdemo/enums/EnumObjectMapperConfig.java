package com.github.xjs.sbdemo.enums;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月16日 下午1:40:22<br/>
 */
@Configuration
public class EnumObjectMapperConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public SimpleModule serializeEnumModule() {
		SimpleModule module = new SimpleModule();
		EnumSerializer enumSerializer = new EnumSerializer();
		List enumClasses =EnumFactory.getAllEnumClass();
		for(Object enumClass : enumClasses) {
			Class clazz = (Class)enumClass;
			module.addSerializer(clazz,enumSerializer);  
		}
		return module;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public SimpleModule deserializeEnumModule() {
		SimpleModule module = new SimpleModule();
		EnumDeserializer enumDeserializer = new EnumDeserializer();
		List enumClasses =EnumFactory.getAllEnumClass();
		for(Object enumClass : enumClasses) {
			Class clazz = (Class)enumClass;
			module.addDeserializer(clazz, enumDeserializer);  
		}
		return module;
	}
	
	@SuppressWarnings("rawtypes")
	private static class EnumSerializer extends JsonSerializer<BaseEnum> {
		@Override
		public void serialize(BaseEnum baseEnum, JsonGenerator jgen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			Object value = baseEnum.getValue();
			serializeValue(jgen, value);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static class EnumDeserializer extends JsonDeserializer {
		@Override
		public BaseEnum deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String name = jp.getParsingContext().getCurrentName();
			JsonNode node = jp.getCodec().readTree(jp);
			Class enumClass = EnumFactory.getEnumTypeByAlias(name);
			String source = node.asText();
			return EnumFactory.convertByStringValue(enumClass, source);
		}
	}

	@SuppressWarnings("rawtypes")
	private static void serializeValue(JsonGenerator jgen, Object value) throws IOException {
		Class valueClazz = value.getClass();
		if (valueClazz == String.class || valueClazz == Character.class || valueClazz == char.class) {
			jgen.writeString(value.toString());
		} else if (valueClazz == Boolean.class || valueClazz == boolean.class) {
			jgen.writeBoolean(Boolean.valueOf(value.toString()));
		} else if (valueClazz == Integer.class || valueClazz == int.class) {
			jgen.writeNumber(Integer.valueOf(value.toString()));
		} else if (valueClazz == Long.class || valueClazz == long.class) {
			jgen.writeNumber(Long.valueOf(value.toString()));
		} else if (valueClazz == Double.class || valueClazz == double.class) {
			jgen.writeNumber(Double.valueOf(value.toString()));
		} else if (valueClazz == Float.class || valueClazz == float.class) {
			jgen.writeNumber(Float.valueOf(value.toString()));
		} else if (valueClazz == Byte.class || valueClazz == byte.class) {
			jgen.writeNumber(Byte.valueOf(value.toString()));
		} else if (valueClazz == Short.class || valueClazz == short.class) {
			jgen.writeNumber(Short.valueOf(value.toString()));
		} else {
			jgen.writeString(value.toString());
		}
	}
}
