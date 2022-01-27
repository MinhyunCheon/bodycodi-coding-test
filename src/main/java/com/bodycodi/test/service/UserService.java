package com.bodycodi.test.service;

import com.bodycodi.test.dto.UserDto;
import com.bodycodi.test.repository.UserRepository;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Resource
	private UserRepository userRepository;

    public int insert(UserDto user) {
//        return 1;
    	return userRepository.insert(user);	// 2022-01-26
    }

    public UserDto findUser(String username) {
        return userRepository.findUser(username);	// 2022-01-27
    }

    /**
     * 2022-01-27
     * 로그인
     * @param userDto
     * @return id
     */
    public int loginUser(UserDto userDto) {
    	return userRepository.loginUser(userDto);
    }

}
