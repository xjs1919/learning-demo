package com.github.xjs.dao;

import com.github.xjs.entity.Blog;
import com.github.xjs.entity.User;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * https://blog.csdn.net/qq_21484461/article/details/139941072
 * */
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testOneToManyRelationship() {
        User user = new User();
        user.setName("John");

        Blog blog1 = new Blog();
        blog1.setTitle("First Blog");
        blog1.setContent("This is the content of the first blog.");
        blog1.setUsr(user);

        Blog blog2 = new Blog();
        blog2.setTitle("Second Blog");
        blog2.setContent("This is the content of the second blog.");
        blog2.setUsr(user);

        user.getBlogs().add(blog1);
        user.getBlogs().add(blog2);

        userDao.save(user);
    }


    @Test
    @Transactional
    public void testFindById(){
        User user = userDao.findById(9L).orElse(null);
        System.out.println(user);
    }

    @Test
    @Transactional
    public void testUseQuery(){
        User user = userDao.queryByIdAndName(9L, "John");
        System.out.println(user);
    }

    @Test
    @Transactional
    public void testUseNativeQuery(){
        User user = userDao.nativeQueryByIdAndName(9L, "John");
        System.out.println(user);
    }

    //Hibernate: select distinct user0_.id as id1_2_, user0_.name as name2_2_ from tb_user user0_ left outer join tb_blog blogs1_ on user0_.id=blogs1_.user_id and (blogs1_.title like ?) where user0_.id=9 and user0_.name=? and blogs1_.user_id=9 order by user0_.id desc
    //Hibernate: select blogs0_.user_id as user_id4_0_0_, blogs0_.id as id1_0_0_, blogs0_.id as id1_0_1_, blogs0_.content as content2_0_1_, blogs0_.title as title3_0_1_, blogs0_.user_id as user_id4_0_1_ from tb_blog blogs0_ where blogs0_.user_id=?
    @Test
    @Transactional
    public void testUseSpecification(){
        List<User> users = userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> idPath = root.get("id");
                Path<Object> namePath = root.get("name");
                Predicate odPredicate = cb.equal(idPath, 9L);
                Predicate namePredicate = cb.equal(namePath, "John");
                // 表关联
                Join<Blog, User> blogJoin = root.join("blogs", JoinType.LEFT);
                // 在join上添加额外的关联条件
                Path<Object> blogTitlePath = blogJoin.get("title");
                Predicate blogIdPredicate = cb.like(blogTitlePath.as(String.class), "%Blog");
                blogJoin.on(blogIdPredicate);
                // 去重
                query.distinct(true);
                // 排序
                query.orderBy(new OrderImpl(root.get("id"), false));
                // 添加blog表上的条件
                Path<Object> usrPath = blogJoin.get("usr");
                Predicate titlePredicate = cb.equal(usrPath, 9L);
                return cb.and(odPredicate, namePredicate, titlePredicate);
            }
        });
        System.out.println(users);
    }

}