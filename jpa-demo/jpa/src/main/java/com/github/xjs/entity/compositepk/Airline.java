package com.github.xjs.entity.compositepk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
/**
 create table Airline (
    endCity varchar(3) not null,
    startCity varchar(3) not null,
    name varchar(30),
    primary key (endCity, startCity));
 * */
@Getter
@Setter
@Entity
public class Airline {

    @EmbeddedId
    private AirlinePK id;

    @Column(length = 30)
    private String name;
}
