package com.lamukhin.springboot.dto;

public class UserDto {
	//хоть возможность смотреть всех пользователей доступна только админам,
	//пароль и токен не отображаются в выдаче, поэтому этих полей в dto нет.
	private int id;
	private String email;
	private String name;
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	} 
	
}
