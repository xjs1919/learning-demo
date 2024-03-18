package com.github.xjs.service.impl;

import com.github.xjs.dto.UserDTO;
import com.github.xjs.service.UserService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService
        (
        targetNamespace = "http://www.xjs1919.com",
        serviceName = "userService"
)
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getById(Integer id) {
        return new UserDTO(id, "hello_"+id);
    }
}
