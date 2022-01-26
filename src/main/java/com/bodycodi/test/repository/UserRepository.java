package com.bodycodi.test.repository;

import com.bodycodi.test.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insert(UserDto user) {
//        int re = this.jdbcTemplate.update("사용자 정보를 저장하는 쿼리 작성", user.getUsername(), user.getPassword());
    	// 2022-01-26
    	String sql = "INSERT INTO USERS (USER_NAME, PASSWORD) VALUES (?, ?)";
    	int re = this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    	// 2022-01-26, 중복 확인 추가
    	// 아이디가 아닌 이름이기 때문에 중복 가능
//    	int userCnt = this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USERS WHERE USER_NAME = ?", new Object[]{user.getUsername()}, Integer.class);
//    	if(userCnt == 0) {
//    		re = this.jdbcTemplate.update("INSERT INTO USERS (USER_NAME, PASSWORD) VALUES (?, ?)", user.getUsername(), user.getPassword());
//    	}    		
    	
        if (re == 1) {
//            int userId =1 ;// 저장한 사용자의 아이디 가지고 오기
        	// 2022-01-26, 이름, 비밀번호 모두 동일한 정보를 저장한 경우 예외 발생
        	// id 기준 내림차순 정렬하여 가장 위의 데이터 id 반환
        	sql = "SELECT ID FROM USERS WHERE USER_NAME = ? AND PASSWORD = ? ORDER BY ID DESC LIMIT 1";
        	int userId = this.jdbcTemplate.queryForObject(sql, new Object[]{user.getUsername(), user.getPassword()}, Integer.class);
        	
            return userId;
        } else {
//            throw new RuntimeException("inert error");
        	throw new RuntimeException("insert error");	// 2022-01-26
        }
    }


    public UserDto findUser(String username) {
        return this.jdbcTemplate.queryForObject("사용자 정보 찾기 쿼리 작성", new String[]{username}, new RowMapper<UserDto>() {

            @Override
            public UserDto mapRow(ResultSet resultSet, int i) throws SQLException {
                UserDto user = new UserDto();
                //가져온 사용자 정보 매핑

                return user;
            }
        });
    }
}
