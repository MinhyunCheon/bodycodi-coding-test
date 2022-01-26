package com.bodycodi.test.repository;

import com.bodycodi.test.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MessageRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public int insert(MessageDto message) {
        int re = this.jdbcTemplate.update("메세지 정보를 저장하는 쿼리 작성");
        return re;
    }


    public List<MessageDto> select(int recipient, int start, int limit) {
        return this.jdbcTemplate.query("메세지 리스트를 가지고 오는 쿼리 작성", new Integer[]{recipient, start, limit},
                (rs, rowNum) -> {
                    MessageDto dto = new MessageDto();
                    // 가지고온 데이터 매핑
                    return dto;

        });
    }
}
