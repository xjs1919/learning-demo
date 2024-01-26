package com.github.xjs;


import org.junit.Test;

public class PropertiesTest {

    @Test
    public void testReadProperties(){

        String 青岛 = CityCodeUtils.getCityCode1("青岛");
        System.out.println(青岛);

        String 济南 = CityCodeUtils.getCityCode2("济南");
        System.out.println(济南);

    }
}
