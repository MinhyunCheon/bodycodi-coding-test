package com.bodycodi.test.service;

import com.bodycodi.test.dto.MessageDto;
import com.bodycodi.test.repository.MessageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class MessageService {
	@Resource
	MessageRepository messageRepository;	// 2022-01-28
	
    public int insert(MessageDto message) {
        return messageRepository.insert(message);	// 2022-01-28
    }

    public List<MessageDto> select(int recipient, int start, int limit) {
        return messageRepository.select(recipient, start, limit);	// 2022-01-28
    }
}
