package com.github.xjs.controller;

import com.github.xjs.service.AuthorService;
import com.github.xjs.service.ContactService;
import com.github.xjs.vo.AuthorVO;
import com.github.xjs.vo.ContactVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("{id}")
    public ContactVO getById(@PathVariable("id") Long id){
        return contactService.getById(id);
    }

}