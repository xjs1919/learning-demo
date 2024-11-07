package com.github.xjs;

import com.github.xjs.entity.one2one.IDCard;
import com.github.xjs.entity.one2one.People;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class One2OneTest {
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

        People people = new People();
        people.setName("xjs");

        IDCard card = new IDCard();
        card.setCode("111");

        people.setCard(card);
        card.setPeople(people);

        em.persist(people);

        em.getTransaction().commit();
    }

    @Test
    public void testQuery(){
        People people = em.find(People.class, 1);
        System.out.println(people);

        IDCard idCard = em.find(IDCard.class, 1);
        System.out.println(idCard);
    }
}
