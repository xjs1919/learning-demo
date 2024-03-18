package com.github.xjs.service;


import com.github.xjs.dto.UserDTO;

import javax.jws.WebParam;

public interface UserService {

    UserDTO getById(@WebParam(name="id") Integer id);

}
