package com.github.xjs.entity.one2one;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 一对一关联，关联关系维护在任何一方都ok
 * 我们维护在主对象这一边
 CREATE TABLE `people` (
 `id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL,
 `pid` int NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `UK_nsw4jqtniyrhi800ifwajw0me` (`pid`),
 CONSTRAINT `FKhwkatl43x45p67ile0fvoraha` FOREIGN KEY (`pid`) REFERENCES `id_card` (`id`)
 );
 CREATE TABLE `id_card` (
 `id` int NOT NULL AUTO_INCREMENT,
 `code` varchar(18) NOT NULL,
 PRIMARY KEY (`id`)
 );
 * */
@Getter
@Setter
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST},
    fetch = FetchType.EAGER,
    optional = false)
    @JoinColumn(name = "pid")
    private IDCard card;

}
