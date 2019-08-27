package com.github.xjs.protobuffdemo;

import com.github.xjs.protobuffdemo.bean.Person;
import com.github.xjs.protobuffdemo.nano.PersonProto;
import com.github.xjs.protobuffdemo.util.ProtobuffUtil;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author xujs@mamcharge.com
 * @Date 2019/8/27 12:59
 **/
@Component
public class PersonTest extends ProtobuffDemoApplicationTests{

    @Test
    public void testPersonSerialize() throws Exception{
        Person p = new Person();
        p.setName("张三");
        p.setAge(30);
        byte[] bytes = ProtobuffUtil.toBytes(ProtobuffUtil.toNano(p, PersonProto.Person.class));
        System.out.println("bytes.length:"+bytes.length);//10
        p = ProtobuffUtil.toBean(ProtobuffUtil.fromBytes(bytes, PersonProto.Person.class), Person.class);
        System.out.println(p.getName() + "," + p.getAge());
    }
}
