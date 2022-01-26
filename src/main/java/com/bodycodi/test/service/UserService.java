package com.bodycodi.test.service;

import com.bodycodi.test.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public int insert(UserDto user) {
        return 1;
    }

    public UserDto findUser(String username) {
        return null;
    }


}
