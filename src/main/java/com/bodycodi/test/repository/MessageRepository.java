package com.bodycodi.test.repository;

import com.bodycodi.test.dto.ContentDto;
import com.bodycodi.test.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    	// 2022-01-28, url과 text는 공백이 허용되야 할 것으로 판단하고 설계
    	String sql = "INSERT INTO MESSAGES (SENDER, RECIPIENT, CONTENT_TYPE, CONTENT_URL, CONTENT_TEXT) VALUES (?, ?, ?, ?, ?)";
        int re = this.jdbcTemplate.update(sql
        								, message.getSender()
        								, message.getRecipient()
        								, message.getContent().getType()
        								, message.getContent().getUrl()
        								, message.getContent().getText());
        return re;
    }


    public List<MessageDto> select(int recipient, int start, int limit) {
    	// 2022-01-28
    	String sql = "SELECT * FROM MESSAGES WHERE RECIPIENT = ? LIMIT ?, ?";
        return this.jdbcTemplate.query(sql, new Integer[]{recipient, start, limit},
                (rs, rowNum) -> {
                    MessageDto dto = new MessageDto();
                    // 가지고온 데이터 매핑
                    // 2022-01-28
                    dto.setId(rs.getInt(1));
                    dto.setSender(rs.getInt(2));
                    dto.setRecipient(rs.getInt(3));
                    ContentDto cDto = new ContentDto();
                    cDto.setType(rs.getString(4));
                    cDto.setUrl(rs.getString(5));
                    cDto.setText(rs.getString(6));
                    dto.setContent(cDto);
                    // java - Fri Jan 28 14:03:39 KST 2022
                    // web - "timestamp":"2022-01-28T05:03:39.000+0000"
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
						dto.setTimestamp(sdf.parse(rs.getString(7)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    // java - 2022-01-28 14:01:43.0
                    // web - "timestamp":"2022-01-28T05:01:43.000+0000"
//                    dto.setTimestamp(Timestamp.valueOf(rs.getString(7)));
                    
                    return dto;

        });
    }
}
