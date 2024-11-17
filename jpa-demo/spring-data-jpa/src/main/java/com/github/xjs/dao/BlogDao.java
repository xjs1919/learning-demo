package com.github.xjs.dao;

import com.github.xjs.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
}
