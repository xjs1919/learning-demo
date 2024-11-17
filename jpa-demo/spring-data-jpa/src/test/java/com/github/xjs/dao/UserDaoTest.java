package com.github.xjs.dao;

import com.github.xjs.entity.Blog;
import com.github.xjs.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * https://blog.csdn.net/qq_21484461/article/details/139941072
 * */
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testOneToManyRelationship() {
        User user = new User();
        user.setName("John Doe");

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

        userRepository.save(user);

        Optional<User> fetchedUser = userRepository.findById(user.getId());
        System.out.println(fetchedUser.isPresent());
        System.out.println(fetchedUser.get());
        System.out.println(fetchedUser.get().getBlogs());
    }


    @Test
    public void testGet(){
        Optional<User> fetchedUser = userRepository.findById(9L);
        System.out.println(fetchedUser.isPresent());
//        System.out.println(fetchedUser.isPresent());
//        System.out.println(fetchedUser.isPresent()?fetchedUser.get():"null");
//        System.out.println(fetchedUser.isPresent()?fetchedUser.get().getBlogs():"null");
    }
}