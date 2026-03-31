package com.github.xjs.controller;

import com.github.xjs.entity.h2.Author;
import com.github.xjs.repository.h2.AuthorRepository;
import com.github.xjs.vo.AuthorVO;
import com.github.xjs.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/all")
    public List<AuthorVO> all(){
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(a -> {
            AuthorVO vo = new AuthorVO();
            vo.setId(a.getId());
            vo.setName(a.getName());
            vo.setBooks(a.getBooks().stream().map(b -> new BookVO(b.getId(), b.getName())).toList());
            return vo;
        }).toList();
    }

    @GetMapping("/join_fetch")
    public List<AuthorVO> fetch_all(){
        List<Author> authors = authorRepository.joinFetch();
        return authors.stream().map(a -> {
            AuthorVO vo = new AuthorVO();
            vo.setId(a.getId());
            vo.setName(a.getName());
            vo.setBooks(a.getBooks().stream().map(b -> new BookVO(b.getId(), b.getName())).toList());
            return vo;
        }).toList();
    }

    @GetMapping("/entity_graph")
    public List<AuthorVO> entity_graph(){
        List<Author> authors = authorRepository.entityGraph();
        return authors.stream().map(a -> {
            AuthorVO vo = new AuthorVO();
            vo.setId(a.getId());
            vo.setName(a.getName());
            vo.setBooks(a.getBooks().stream().map(b -> new BookVO(b.getId(), b.getName())).toList());
            return vo;
        }).toList();
    }
}
