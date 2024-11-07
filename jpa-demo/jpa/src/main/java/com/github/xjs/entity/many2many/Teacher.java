package com.github.xjs.entity.many2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * 多对多关联需要借助中间的关联表，关系可以维护在任何一方
 * 比如：要维护在student上，也就是student来维护外键
 CREATE TABLE `teacher` (
 `id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL,
 PRIMARY KEY (`id`)
 );
 CREATE TABLE `student` (
 `id` int NOT NULL AUTO_INCREMENT,
 `code` varchar(10) NOT NULL,
 `name` varchar(20) NOT NULL,
 PRIMARY KEY (`id`)
 );
 CREATE TABLE `teacher_student` (
 `student_id` int NOT NULL,
 `teacher_id` int NOT NULL,
 PRIMARY KEY (`student_id`,`teacher_id`),
 KEY `FK1rqhxgk1kqw1t79nm6idqv6va` (`teacher_id`),
 CONSTRAINT `FK1rqhxgk1kqw1t79nm6idqv6va` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
 CONSTRAINT `FKfh5l7dr80uq4w04kv5oc3rhjj` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
 )
 * */
@Entity
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students;

}
