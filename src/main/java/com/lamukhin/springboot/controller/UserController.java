package com.lamukhin.springboot.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;

import com.lamukhin.springboot.config.MapperUtil;
import com.lamukhin.springboot.dto.LoginDto;
import com.lamukhin.springboot.dto.MessageDto;
import com.lamukhin.springboot.dto.ResponseWithTokenDto;
import com.lamukhin.springboot.dto.ShortResponse;
import com.lamukhin.springboot.dto.SignupDto;
import com.lamukhin.springboot.dto.UserDto;
import com.lamukhin.springboot.entity.Message;
import com.lamukhin.springboot.entity.User;
import com.lamukhin.springboot.service.AdditionalService;
import com.lamukhin.springboot.service.MessageService;
import com.lamukhin.springboot.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdditionalService additionalService;

	// регистрация пользователя	
	@PostMapping("/signup")
	public ResponseEntity<ResponseWithTokenDto> addNewUser(@RequestBody SignupDto signupDto) {
		User newUser = new User();
		newUser.setEmail(signupDto.getEmail());
		newUser.setPassword(signupDto.getPassword());
		newUser.setName(signupDto.getName());
		newUser.setRole("DEFAULT");
		String newToken = userService.saveUser(newUser);
		ResponseWithTokenDto responseBody = new ResponseWithTokenDto("You have been registred! Your unique token is",
				newToken.toString());
		return new ResponseEntity<>(responseBody,
				additionalService.responseHeaders(HttpHeaders.AUTHORIZATION, newToken.toString()),
				HttpStatus.OK);
	}

	// авторизация пользователя
	@PostMapping("/login")
	public ResponseEntity<ResponseWithTokenDto> loginUser(@RequestBody LoginDto loginDto) {
		User user = userService.getUser(loginDto.getEmail(), loginDto.getPassword());
		ResponseWithTokenDto responseBody = new ResponseWithTokenDto("Welcome, " + user.getName() + "! Your unique token is", user.getToken()); 
		return new ResponseEntity<>(responseBody,
				additionalService.responseHeaders(HttpHeaders.AUTHORIZATION, user.getToken()), 
				HttpStatus.OK);
	}

	// получение всех пользователей
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader(value = "Authorization") String token) {
		List<User> allUsers = userService.getAllUsers(token);
		List<UserDto> responseBody = MapperUtil.convertList(allUsers, additionalService::convertToUserDto);
		return ResponseEntity.ok(responseBody);
	}
	
	
}
