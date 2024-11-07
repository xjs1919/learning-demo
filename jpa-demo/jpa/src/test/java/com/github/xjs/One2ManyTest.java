package com.github.xjs;

import com.github.xjs.entity.one2many.Order;
import com.github.xjs.entity.one2many.OrderItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class One2ManyTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @BeforeEach
    public void beforeEach(){
        factory = Persistence.createEntityManagerFactory("jpatest");
        em = factory.createEntityManager();
    }

    @AfterEach
    public void afterEach(){
        if(em != null){
            em.close();
        }
        if(factory !=null){
            factory.close();
        }
    }

    @Test
    public void testSave(){
        em.getTransaction().begin();

        Order order = new Order();
        order.setOrderNo("1111");
        order.setAmount(100.0);

        OrderItem item1 = new OrderItem();
        item1.setOrder(order);
        item1.setProductName("小米");
        item1.setProductPrice(50.0);

        OrderItem item2 = new OrderItem();
        item2.setOrder(order);
        item2.setProductName("华为");
        item2.setProductPrice(50.0);

        Set<OrderItem> items = new HashSet<>();
        items.add(item1);
        items.add(item2);
        order.setItems(items);

        em.persist(order);
        em.getTransaction().commit();
    }

    @Test
    public void testQuery(){
//        Order order = em.find(Order.class, "1111");
//        System.out.println(order);
        OrderItem orderItem = em.find(OrderItem.class, 1);
        System.out.println(orderItem);
    }
}
