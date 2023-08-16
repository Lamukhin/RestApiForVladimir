package com.lamukhin.springboot.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MessageDto {
	private int id;
	private String text;
	private Timestamp timestamp;
	@JsonProperty("user")
	private UserDto userDto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
