package com.github.xjs.service;

import com.github.xjs.repository.AuthorRepository;
import com.github.xjs.vo.AuthorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public AuthorVO getById(Long id) {
       return authorRepository.findById(id)
               .map(author -> new AuthorVO(author.getId(), author.getName()))
               .orElse(null);
    }

}
