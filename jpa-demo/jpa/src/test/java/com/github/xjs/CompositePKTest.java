package com.github.xjs;

import com.github.xjs.entity.compositepk.Airline;
import com.github.xjs.entity.compositepk.AirlinePK;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompositePKTest {
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
        Airline airline = new Airline();
        AirlinePK pk = new AirlinePK();

        airline.setName("从北京到上海");
        pk.setStartCity("PEK");
        pk.setEndCity("SHA");

        airline.setId(pk);

        em.persist(airline);
        em.getTransaction().commit();
    }
}
