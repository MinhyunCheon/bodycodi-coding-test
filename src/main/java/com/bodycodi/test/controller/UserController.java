package com.bodycodi.test.controller;

import com.bodycodi.test.dto.TokenDto;
import com.bodycodi.test.dto.UserDto;
import com.bodycodi.test.service.UserService;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	@Resource
	private UserService userService;	// 2022-01-26

    /**
     * 사용자를 가입한다.
     * @param userDto
     * @return
     */
    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto) {
        if(StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPassword())) {
            throw new RuntimeException("Required username and password");
        }
//        int id = 1; // user Id 정보 가지고 오는 구조 개발
        int id = userService.insert(userDto);	// 2022-01-26
        
        UserDto dto = new UserDto();
        dto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /**
     * 로그인 한뒤 토큰을 생성한다.
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto) {
    	// 2022-01-27
        int userId = userService.loginUser(userDto);
        boolean isLogin = userId != 0 ? true : false;
        
//        boolean isLogin = true;  // 로그인 체크
        TokenDto tokenDto = new TokenDto(); // 인증 토크 전달
        
        if(isLogin) {
        	// 2022-01-27
        	tokenDto.setId(userId);
        	tokenDto.setToken(makeJwtToken(userId, userDto.getUsername()));
        	TokenController.getInstence().setToken(tokenDto);
            
            return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenDto);
        }

    }
    
    // 2022-01-27, 토큰 사용 경험이 없어 사이트 참조했습니다.(https://shinsunyoung.tistory.com/110)
    public String makeJwtToken(int id, String username) {
        Date now = new Date();

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
            .setIssuer("redblue") // (2)
            .setIssuedAt(now) // (3)
            .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
            .claim("id", id) // (5)
            .claim("username", username)
            .signWith(SignatureAlgorithm.HS256, "secret") // (6)
            .compact();
    }

    /**
     * 토큰 테스트
     * @param userDto
     * @return
     */
    @PostMapping("/tokentest")
    @ResponseBody
    public ResponseEntity<UserDto> tokentest(@RequestAttribute("user") UserDto userDto) {

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
