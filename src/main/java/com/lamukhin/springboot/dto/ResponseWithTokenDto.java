package com.lamukhin.springboot.dto;

public class ResponseWithTokenDto {
	private String info;
	private String token;
	
	public ResponseWithTokenDto() {};
	
	public ResponseWithTokenDto(String info, String token) {
		this.info = info;
		this.token = token;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
