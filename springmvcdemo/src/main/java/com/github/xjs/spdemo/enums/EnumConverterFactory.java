package com.github.xjs.spdemo.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 上午9:42:02<br/>
 * 参考：
 * （1）https://blog.csdn.net/renhui999/article/details/9837897
 * （2）https://my.oschina.net/sugarZone/blog/706413
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class EnumConverterFactory  implements ConverterFactory<String, BaseEnum>  {
	@Override
	public <T extends BaseEnum>Converter getConverter(Class<T> targetType) {
		return new StringToEnum(targetType);
	}
	private static class StringToEnum<T extends BaseEnum> implements Converter<String, T> {
		private final Class<T> enumClass;
		public StringToEnum(Class<T> enumClass) {
			this.enumClass = enumClass;
		}
		@Override
		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			return (T)EnumFactory.convertByStringValue(this.enumClass, source);
		}
	}
}
