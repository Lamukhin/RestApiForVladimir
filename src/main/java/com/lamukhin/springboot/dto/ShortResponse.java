package com.lamukhin.springboot.dto;

public class ShortResponse {
	
	private String info;
	
	public ShortResponse() {};
	
	public ShortResponse(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
