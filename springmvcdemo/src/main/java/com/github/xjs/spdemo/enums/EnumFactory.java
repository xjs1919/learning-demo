package com.github.xjs.spdemo.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月11日 上午10:52:36<br/>
 */
public class EnumFactory {
	
	private EnumFactory() {}
	
	/**按照类来缓存已经加载的所有的Enum**/
	@SuppressWarnings("rawtypes")
	private static ConcurrentHashMap<Class, Set<BaseEnum>> enumCache = new ConcurrentHashMap<Class, Set<BaseEnum>>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addAll(Class enumClazz) {
		if(enumClazz == null) {
			return;
		}
		init(enumClazz);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <R extends BaseEnum<T>, T> void add(R enumValue) {
		if(enumValue == null) {
			return;
		}
		Class<R> enumClass = (Class<R>)enumValue.getClass();
		Set set = enumCache.get(enumClass);
		if(set == null) {
			set = new HashSet<BaseEnum<T>>();
			enumCache.putIfAbsent(enumClass, set);
		} 
		set = enumCache.get(enumClass);
		set.add(enumValue);
	}
	
	/**
	 * 根据value获取enum
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <R extends BaseEnum<T>,T>  R getByValue(Class<R> enumClass, T value) {
		if(enumClass == null) {
			return null;
		}
		init(enumClass);
		Set<BaseEnum> set = enumCache.get(enumClass);
		for(BaseEnum<T> e : set) {
			if(e.getValue().equals(value)) {
				return (R)e;
			}
		}
		return null;
	}
	
	/**
	 * 根据desc获取enum
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <R extends BaseEnum<T>, T> R getByDesc(Class<R> enumClass, String desc) {
		if(enumClass == null) {
			return null;
		}
		init(enumClass);
		Set<BaseEnum> set = enumCache.get(enumClass);
		for(BaseEnum<T> e : set) {
			if(e.getDesc().equals(desc)) {
				return (R)e;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <R extends BaseEnum<V>, V> List<R> getAll(Class<R> clazz) {
		if(clazz == null) {
			return null;
		}
        init(clazz);
        Set<R> set = (Set<R>) enumCache.get(clazz);
        if (set != null && set.size() > 0){
        	List<R> list =  new ArrayList<R>(set.size());
            list.addAll(set);
            return list;
        }
        return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <R extends BaseEnum<V>, V> List<Class<R>> getAllEnumClass() {
		List<Class<R>> list = new ArrayList<Class<R>>();
		Enumeration<Class> keys = enumCache.keys();
		while(keys.hasMoreElements()) {
			Class clazz = keys.nextElement();
			list.add(clazz);
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V, R extends BaseEnum<V>> Class<R> getEnumTypeByAlias(String aliasClassName) {
		if(aliasClassName == null || aliasClassName.length() <= 0) {
			return null;
		}
		Set<Map.Entry<Class, Set<BaseEnum>>> entrySet = enumCache.entrySet();
		for(Map.Entry<Class, Set<BaseEnum>> entry : entrySet) {
			Class clazz = entry.getKey();
			if(clazz.getSimpleName().equalsIgnoreCase(aliasClassName)) {
				return clazz;
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public static <V, R extends BaseEnum<V>> Class<V> getValueTypeByClass(Class<R> enumClsss) {
		if(enumClsss == null) {
			return null;
		}
		Set<BaseEnum> set = enumCache.get(enumClsss);
		return (Class<V>)set.iterator().next().getValue().getClass();
	}
	
	private static <R> void init(Class<R> enumClass) {
		//说明已经初始化过了
		if(enumCache.get(enumClass) != null){
			return;
		}
		//反射获取其中的一个field，导致类实例化，导致所有的static的Field初始化，然后就会被添加到enumCache中
		Field[]  fields = enumClass.getFields();
		if(fields.length <= 0) {
			return;
		}
		Field field = fields[0];
		try {
			field.setAccessible(true);
			field.get(null);
		}catch(Throwable e) {
			//
		}
	}

}
