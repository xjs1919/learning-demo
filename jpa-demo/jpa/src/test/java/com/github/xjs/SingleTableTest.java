package com.github.xjs;

import com.github.xjs.entity.singletable.Gender;
import com.github.xjs.entity.singletable.Person;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class SingleTableTest {

    @Test
    public void testCreateTable(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpatest");
        //EntityManager em = factory.createEntityManager();
        factory.close();
    }

    @Test
    public void testSave(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Person p = new Person();
        p.setName("xjs");
        p.setBirthday(new Date());
        p.setGender(Gender.MALE);
        p.setInfo("hello my name is xjs");
        em.persist(p);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void testQuery(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = factory.createEntityManager();

        // 主键查询
        System.out.println("--------主键查询-------------------");
        Person person = em.find(Person.class, 1);
        System.out.println(person);

        System.out.println("--------query查询-------------------");
        // query查询, p是别名，Person是java实体不是数据库表，p.name也是java字段非列名
        Query query = em.createQuery("select p from Person p where p.name = :name");
        query.setParameter("name", "xjs");
        List list = query.getResultList();
        System.out.println(list);

        System.out.println("----------延迟加载-----------------");
        // 延迟加载
        //只是返回一个代理对象，当对代理对象的属性进行访问的时候，才会从数据库中得到这条记录，不然不会发生数据的加载行为。EntityManager必须没有关闭
        Person proxy = em.getReference(Person.class, 1);
        System.out.println(proxy == null);
        System.out.println(proxy);
        // 当数据在数据库不存在，会报错：javax.persistence.EntityNotFoundException: Unable to find com.github.xjs.entity.singletable.Person with id 2
        proxy = em.getReference(Person.class, 2);
        System.out.println(proxy == null);
        System.out.println(proxy);

        em.close();
        factory.close();
    }

    @Test
    public void testUpdate(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        System.out.println("----------更新-----------------");
        // 把其他字段都更新成null了
//        Person p = new Person();
//        p.setId(1);
//        p.setName("xjs-new");
//        em.merge(p);

          // 只更新所需字段
//        Query query = em.createQuery("update Person p set p.name=:name where p.id = :id");
//        query.setParameter("name", "xjs");
//        query.setParameter("id",1);
//        int result = query.executeUpdate();
//        System.out.println("update result : " + result);

//         // 一条select，一条update，更新所有字段
//        Person proxy = em.getReference(Person.class, 1);
//        proxy.setName("joshua");

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void testDelete(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

//        Query query = em.createQuery("delete from Person p where p.name = :name");
//        query.setParameter("name","joshua");
//        int result = query.executeUpdate();
//        System.out.println("result:" + result);

        Person proxy = em.getReference(Person.class, 2);
        em.remove(proxy);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
