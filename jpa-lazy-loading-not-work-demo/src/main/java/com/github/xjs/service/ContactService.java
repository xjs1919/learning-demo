package com.github.xjs.service;

import com.github.xjs.entity.Contact;
import com.github.xjs.repository.ContactRepository;
import com.github.xjs.vo.ContactVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {


    @Autowired
    private ContactRepository contactRepository;

    public ContactVO getById(Long id) {
        return contactRepository.findById(id)
                .map(c->new ContactVO(c.getId(), c.getMobile(), c.getEmail()))
                .orElse(null);
    }
}
