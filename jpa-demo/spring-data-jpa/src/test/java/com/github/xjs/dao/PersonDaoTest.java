package com.github.xjs.dao;

import com.github.xjs.entity.Gender;
import com.github.xjs.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PersonDaoTest {

    @Autowired
    private PersonDao personDao;



    @Test
    public void testSave(){
        Person p = new Person();
        p.setName("xjs");
        p.setBirthday(new Date());
        p.setGender(Gender.MALE);
        p.setInfo("hello my name is xjs");
        personDao.save(p);
        System.out.println(p.getId());
    }

    @Test
    public void testFindById(){
        Person person = personDao.findById(8).orElse(null);
        System.out.println(person);
    }

    @Test
    @Transactional
    public void testGetReferenceById(){
        Person person = personDao.getReferenceById(4);
        System.out.println(person);
    }

    @Test
    public void testFindByName(){
        List<Person> persons = personDao.findByName("xjs-3");
        System.out.println(persons);
    }

    @Test
    public void testFindByNameLike(){
        List<Person> persons = personDao.findByNameLike("xjs%");
        System.out.println(persons);
    }

    @Test
    public void testSelectByName(){
        List<Person> persons = personDao.selectByName("xjs");
        System.out.println(persons);
    }
    @Test
    public void testFindAll(){
        List<Person> all = personDao.findAll(new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> namePath = root.get("name");
                Predicate predicate = criteriaBuilder.equal(namePath, "xjs");
                return predicate;
            }
        });
        System.out.println(all);
    }

    @Test
    public void testFindByNameAndId(){
        List<Person> all = personDao.findAll(new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> namePath = root.get("name");
                Path<Object> idPath = root.get("id");

                Predicate namePredicate = criteriaBuilder.equal(namePath, "xjs");
                Predicate idPredicate = criteriaBuilder.equal(idPath, 5);

                return criteriaBuilder.and(namePredicate, idPredicate);
            }
        });
        System.out.println(all);
    }

    @Test
    public void testUpdate(){
        Person p = personDao.findById(4).orElse(null);
        p.setName("xjs-new");
        personDao.save(p);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testUpdateNameById(){
        personDao.updateNameById("xjs-3", 5);
    }


    @Test
    public void testDelete(){
        personDao.deleteById(4);
    }

}