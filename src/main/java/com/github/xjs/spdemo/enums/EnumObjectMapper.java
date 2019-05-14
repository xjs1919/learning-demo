package com.github.xjs.spdemo.enums;

import java.io.IOException;
import java.util.List;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 上午9:40:09<br/>
 * 参考：https://jackyrong.iteye.com/blog/2005323
 */
@SuppressWarnings({ "unchecked", "rawtypes" ,"serial"})
public class EnumObjectMapper extends ObjectMapper  {
	public EnumObjectMapper() {
		//获取所有的枚举类
		List enumClasses =EnumFactory.getAllEnumClass();
		//添加Serializer
		SimpleModule module = new SimpleModule();  
		EnumSerializer enumSerializer = new EnumSerializer();
		for(Object enumClass : enumClasses) {
			Class clazz = (Class)enumClass;
			module.addSerializer(clazz,enumSerializer);  
		}
		this.registerModule(module); 
		//添加Deserializer
		SimpleModule module2 = new SimpleModule();  
		EnumDeserializer enumDeserializer = new EnumDeserializer();
		for(Object enumClass : enumClasses) {
			Class clazz = (Class)enumClass;
			module2.addDeserializer(clazz, enumDeserializer);  
		}
		this.registerModule(module2);  
	}
	
	private static class EnumSerializer extends JsonSerializer<BaseEnum> {  
		@Override
		public void serialize(BaseEnum baseEnum, JsonGenerator jgen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			Object value = baseEnum.getValue();
			serializeValue(jgen, value);
		}  
	}  
	
	private static class EnumDeserializer extends JsonDeserializer{
		@Override
		public BaseEnum deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
				String name = jp.getParsingContext().getCurrentName();
				JsonNode node = jp.getCodec().readTree(jp); 
				Class enumClass = EnumFactory.getEnumTypeByAlias(name);
				String source= node.asText();
				return getByValue(enumClass, source);
		}  
	} 
	
	private static void serializeValue(JsonGenerator jgen, Object value) throws IOException {
		Class valueClazz = value.getClass();
		if(valueClazz == String.class || valueClazz==Char.class || valueClazz == char.class) {
			jgen.writeString(value.toString());
		}else if(valueClazz == Boolean.class || valueClazz == boolean.class) {
			jgen.writeBoolean(Boolean.valueOf(value.toString()));
		}else if(valueClazz == Integer.class || valueClazz == int.class ) {
			jgen.writeNumber(Integer.valueOf(value.toString()));
		}else if(valueClazz == Long.class || valueClazz == long.class ) {
			jgen.writeNumber(Long.valueOf(value.toString()));
		} else if(valueClazz == Double.class || valueClazz == double.class) {
			jgen.writeNumber(Double.valueOf(value.toString()));
		} else if(valueClazz == Float.class || valueClazz == float.class) {
			jgen.writeNumber(Float.valueOf(value.toString()));
		} else if(valueClazz == Byte.class || valueClazz == byte.class ) {
			jgen.writeNumber(Byte.valueOf(value.toString()));
		} else if( valueClazz == Short.class || valueClazz == short.class) {
			jgen.writeNumber(Short.valueOf(value.toString()));
		} else {
			jgen.writeString(value.toString());
		}
	}
	
	public static <T extends BaseEnum> T getByValue(Class<T> enumClass, String source) {
		Class valueClazz = EnumFactory.getValueTypeByClass(enumClass);
		if(valueClazz == String.class || valueClazz==Char.class || valueClazz == char.class) {
			return (T)EnumFactory.getByValue(enumClass, source);
		}else if(valueClazz == Boolean.class || valueClazz == boolean.class) {
			return (T)EnumFactory.getByValue(enumClass, Boolean.valueOf(source));
		}else if(valueClazz == Integer.class || valueClazz == int.class ) {
			return (T)EnumFactory.getByValue(enumClass, Integer.valueOf(source));
		}else if(valueClazz == Long.class || valueClazz == long.class ) {
			return (T)EnumFactory.getByValue(enumClass, Long.valueOf(source));
		} else if(valueClazz == Double.class || valueClazz == double.class) {
			return (T)EnumFactory.getByValue(enumClass, Double.valueOf(source));
		} else if(valueClazz == Float.class || valueClazz == float.class) {
			return (T)EnumFactory.getByValue(enumClass, Float.valueOf(source));
		} else if(valueClazz == Byte.class || valueClazz == byte.class ) {
			return (T)EnumFactory.getByValue(enumClass, Byte.valueOf(source));
		} else if( valueClazz == Short.class || valueClazz == short.class) {
			return (T)EnumFactory.getByValue(enumClass, Short.valueOf(source));
		} else {
			return (T)EnumFactory.getByValue(enumClass, source);
		}
	}
}
