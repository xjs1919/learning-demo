package com.github.xjs.dao;

import com.github.xjs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    //Hibernate: select distinct u.* from tb_user u left join tb_blog b on u.id = b.user_id where u.id=? and u.name=?
    //Hibernate: select blogs0_.user_id as user_id4_0_0_, blogs0_.id as id1_0_0_, blogs0_.id as id1_0_1_, blogs0_.content as content2_0_1_, blogs0_.title as title3_0_1_, blogs0_.user_id as user_id4_0_1_ from tb_blog blogs0_ where blogs0_.user_id=?
    @Query(value = "select distinct u.* from tb_user u left join tb_blog b on u.id = b.user_id where u.id=:uid and u.name=:name",nativeQuery = true)
    User queryByIdAndName(@Param("uid") Long uid,@Param("name")  String name);

    //Hibernate: select distinct user0_.id as id1_2_, user0_.name as name2_2_ from tb_user user0_ left outer join tb_blog blog1_ on (user0_.id=blog1_.user_id) where user0_.id=? and user0_.name=?
    //Hibernate: select blogs0_.user_id as user_id4_0_0_, blogs0_.id as id1_0_0_, blogs0_.id as id1_0_1_, blogs0_.content as content2_0_1_, blogs0_.title as title3_0_1_, blogs0_.user_id as user_id4_0_1_ from tb_blog blogs0_ where blogs0_.user_id=?
    @Query(value = "select distinct u from User u left join Blog b on u.id = b.usr where u.id=:uid and u.name=:name")
    User nativeQueryByIdAndName(Long uid,String name);
}
