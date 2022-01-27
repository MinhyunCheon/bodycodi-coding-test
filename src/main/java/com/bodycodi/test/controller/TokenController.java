package com.bodycodi.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bodycodi.test.dto.TokenDto;

// 2022-01-27, token 유효성 검사 추가 필요
public class TokenController {
	private static TokenController instence;
//	private List<TokenDto> tokenList;
	private Map<String, Integer> tokenMap;
	
	private TokenController() {
//		tokenList = new ArrayList<>();
		tokenMap = new HashMap<>();
	}
	
	public synchronized static TokenController getInstence() {
		if(instence == null) instence = new TokenController();
		return instence;
	}
	
//	public TokenDto getToken(String token) {
//		System.out.println("tokenController getToken");
//		
////		for(TokenDto td : tokenList) {
////			if(td.getToken().equals(token)) return td;
////		}
//		
//		return null;
//	}
	
	public int getTokenId(String token) {
		return tokenMap.get(token) != null ? tokenMap.get(token) : 0;
	}
	
	public void setToken(TokenDto tokenDto) {
//		tokenList.add(tokenDto);
		tokenMap.put(tokenDto.getToken(), tokenDto.getId());
	}
	
	public void printToken() {
//		for(TokenDto td : tokenList) {
//			System.out.println(td);
//		}
		for(String key : tokenMap.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + tokenMap.get(key));
			System.out.println();
		}
	}
}
