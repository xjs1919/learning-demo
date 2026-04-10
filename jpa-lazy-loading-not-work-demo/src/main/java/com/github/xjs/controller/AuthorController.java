package com.github.xjs.controller;

import com.github.xjs.service.AuthorService;
import com.github.xjs.vo.AuthorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("{id}")
    public AuthorVO getById(@PathVariable("id") Long id){
        return authorService.getById(id);
    }

}