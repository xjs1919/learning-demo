package com.github.xjs.dao;

import com.github.xjs.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonDao extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

    List<Person> findByName(String name);

    List<Person> findByNameLike(String name);

    @Query("select p from Person p where p.name = :name")
    List<Person> selectByName(String name);

    @Query("update Person set name=:name where id =:id")
    @Modifying
    void updateNameById(String name, Integer id);


}
