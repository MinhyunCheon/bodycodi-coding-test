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
        int re = this.jdbcTemplate.update("사용자 정보를 저장하는 쿼리 작성", user.getUsername(), user.getPassword());
        if (re == 1) {
            int userId =1 ;// 저장한 사용자의 아이디 가지고 오기
            return userId;
        } else {
            throw new RuntimeException("inert error");
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
