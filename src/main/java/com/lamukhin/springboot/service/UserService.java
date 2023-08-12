package com.lamukhin.springboot.service;

import java.util.List;
import java.util.UUID;

import com.lamukhin.springboot.entity.User;


public interface UserService {
	//получение всех юзеров админом
	public List<User> getAllUsers(String token);
	//регистрация
	public String saveUser(User user);
	//получение юзера из бд для проверки логина
	public User getUser(String email, String password);

}
