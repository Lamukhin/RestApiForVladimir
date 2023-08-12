package com.lamukhin.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamukhin.springboot.authorization.Login;
import com.lamukhin.springboot.authorization.Signup;
import com.lamukhin.springboot.entity.Message;
import com.lamukhin.springboot.entity.User;
import com.lamukhin.springboot.service.MessageService;
import com.lamukhin.springboot.service.UserService;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	// регистрация пользователя
	@PostMapping("/signup")
	public ResponseEntity<String> addNewUser(@RequestBody Signup signup) {
		User newUser = new User();
		newUser.setEmail(signup.getEmail());
		newUser.setPassword(signup.getPassword());
		newUser.setName(signup.getName());
		newUser.setRole("DEFAULT");
		String newToken = userService.saveUser(newUser);
		String answer = "You have been registred! Your unique token is " + newToken.toString();
		return new ResponseEntity<>(answer, responseHeaders(HttpHeaders.AUTHORIZATION, newToken.toString()),
				HttpStatus.OK);
	}

	// авторизация пользователя
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Login login) {
		User user = userService.getUser(login.getEmail(), login.getPassword());
		String answer = "Welcome, " + user.getName() + "! Your unique token is " + user.getToken();
		return new ResponseEntity<>(answer, responseHeaders(HttpHeaders.AUTHORIZATION, user.getToken()), HttpStatus.OK);
	}

	// получение всех пользователей
	@GetMapping("/users")
	public List<User> getAllUsers(@RequestHeader(value = "Authorization") String token) {
		return userService.getAllUsers(token);
	}

	// получение всех сообщений
	@GetMapping("/messages")
	public List<Message> getAllMessages() {
		return messageService.getAllMessages();
	}

	// отправка сообщения
	@PostMapping("/messages")
	public String saveMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message message) {
		messageService.saveMessage(message, token);
		return "Done!";
	}

	// удаление сообщения 
	@DeleteMapping("/messages/{id}")
	public String deleteMessage(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {
		messageService.deleteMessage(id, token);
		return "You have deleted message with ID = "+id;
	}

	// добавление токена в хидерс
	private MultiValueMap<String, String> responseHeaders(String key, String value) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		return headers;
	}

}
