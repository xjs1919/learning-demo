package com.github.xjs.aopdemo.demo1.cglibdemo;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

import java.io.*;

public class ObjenesisDemo {

    public static class User implements Serializable {

        private String name;
        public User(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        Objenesis objenesis = new ObjenesisStd();
        User user = objenesis.newInstance(User.class);
        System.out.println(user);
        ObjectInstantiator<User> userObjectInstantiator = objenesis.getInstantiatorOf(User.class);
        User user1 = userObjectInstantiator.newInstance();
        System.out.println(user1);
        User user2 = userObjectInstantiator.newInstance();
        System.out.println(user2);
        System.out.println(user1 == user2);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out  = new ObjectOutputStream(bout);
        out.writeObject(new User("xjs"));
        byte[] bytes = bout.toByteArray();
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        ObjectInputStream oin = new ObjectInputStream(bin);
        User u = (User)oin.readObject();
        System.out.println(u);
    }
}
