package com.github.xjs;

import com.github.xjs.entity.many2many.Student;
import com.github.xjs.entity.many2many.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class Many2ManyTest {

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
        Set<Teacher> teachers = new HashSet<>();
        Teacher t1 = new Teacher();
        t1.setName("徐老师");
        Teacher t2 = new Teacher();
        t2.setName("王老师");
        teachers.add(t1);
        teachers.add(t2);

        Set<Student> set1 = new HashSet<>();
        Student s11 = new Student();
        s11.setName("zhangsan");
        s11.setCode("001");
        s11.setTeachers(teachers);
        Student s12 = new Student();
        s12.setName("lisi");
        s12.setCode("002");
        s12.setTeachers(teachers);
        set1.add(s11);set1.add(s12);
        t1.setStudents(set1);

        Set<Student> set2 = new HashSet<>();
        Student s21 = new Student();
        s21.setName("wangwu");
        s21.setCode("003");
        s21.setTeachers(teachers);
        Student s22 = new Student();
        s22.setName("zhangliu");
        s22.setCode("004");
        s22.setTeachers(teachers);
        set2.add(s21);set2.add(s22);
        t2.setStudents(set2);

        em.persist(t1);
        em.persist(t2);
        em.persist(s11);
        em.persist(s12);
        em.persist(s21);
        em.persist(s22);

        em.getTransaction().commit();

    }

    @Test
    public void testDeleteTeacher(){
        em.getTransaction().begin();
//        // 外键约束，删除不掉，因为teacher不是关系的维护方，所以没法删除约束
//        Teacher reference = em.getReference(Teacher.class, 1);
//        em.remove(reference);
        // 先删除约束 才能删除teacher，因为student才是关系的维护方，因此是可以删除约束的
        // select student0_.id as id1_5_0_, student0_.code as code2_5_0_, student0_.name as name3_5_0_ from Student student0_ where student0_.id=?
        // delete from teacher_student where student_id=?
        // delete from Student where id=?
        Student reference = em.getReference(Student.class, 1);
        em.remove(reference);

        em.getTransaction().commit();
    }

    @Test
    public void testUpdate(){
        em.getTransaction().begin();
        //select student0_.id as id1_5_0_, student0_.code as code2_5_0_, student0_.name as name3_5_0_ from Student student0_ where student0_.id=?
        Student student = em.find(Student.class, 2);
        // select teachers0_.student_id as student_1_7_0_, teachers0_.teacher_id as teacher_2_7_0_, teacher1_.id as id1_6_1_, teacher1_.name as name2_6_1_ from teacher_student teachers0_ inner join Teacher teacher1_ on teachers0_.teacher_id=teacher1_.id where teachers0_.student_id=?
        Set<Teacher> teachers = student.getTeachers();
        if(teachers == null || teachers.size() <= 0){
            teachers = new HashSet<>();
            student.setTeachers(teachers);
        }
        Teacher t = new Teacher();
        t.setName("刘老师");
        teachers.add(t);
        // insert into Teacher (name) values (?)
        em.persist(t);
        // insert into teacher_student (student_id, teacher_id) values (?, ?)
        em.merge(student);
        em.getTransaction().commit();
    }
}
