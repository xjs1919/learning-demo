package com.github.xjs;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class CityCodeUtils {

    private static Properties properties1 = new Properties();
    private static Properties properties2 = new Properties();

    public static String DEFAULT_CODE = "101010100";

    private CityCodeUtils() {

    }

    static  {
        ClassLoader classLoader = CityCodeUtils.class.getClassLoader();

        // 方法一：把字节流包装成字符流，jdk9以后更推荐使用这种方式
        InputStream is1 = classLoader.getResourceAsStream("citycodes.properties");
        try {
            InputStreamReader reader = new InputStreamReader(is1, "UTF-8");
            properties1.load(reader);
            is1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 方法二：把properties文件中的中文用native2ascii转码成unicode格式
        //native2ascii -encoding UTF-8 citycodes.properties citycodes.properties
        InputStream is2 = classLoader.getResourceAsStream("citycodes2.properties");
        try {
            properties2.load(is2);
            is2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getCityCode1(String cityName) {
        String code = properties1.getProperty(cityName, DEFAULT_CODE);
        return code;
    }

    public static String getCityCode2(String cityName) {
        String code = properties2.getProperty(cityName, DEFAULT_CODE);
        return code;
    }

}
