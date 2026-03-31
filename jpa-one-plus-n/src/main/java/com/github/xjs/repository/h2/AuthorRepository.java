package com.github.xjs.repository.h2;

import com.github.xjs.entity.h2.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("from Author a join fetch a.books")
    public List<Author> joinFetch();

    // 引用刚才定义的图
    @EntityGraph("Author.books")
    @Query("SELECT a FROM Author a")
    List<Author> entityGraph();

}
