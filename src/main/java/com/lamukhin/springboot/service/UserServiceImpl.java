package com.lamukhin.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamukhin.springboot.dao.UserRepository;
import com.lamukhin.springboot.entity.User;
import com.lamukhin.springboot.exception_handling.AccessException;
import com.lamukhin.springboot.exception_handling.LoginException;
import com.lamukhin.springboot.exception_handling.NoSuchUserException;
import com.lamukhin.springboot.exception_handling.RegistrationException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers(String token) {
		User user = userRepository.findUserByToken(token);
		if (user == null) {
			throw new NoSuchUserException("User with this token is not registred. Check your data and try again.");
		} else if (user.getRole().equals("ADMIN")) {
			return userRepository.findAll();
		} else {
			throw new AccessException("You don't have rights to get this data.");
		}

	}

	@Override
	public String saveUser(User user) {
		if (userRepository.findUserByEmail(user.getEmail()) == null) {
			String uuid = UUID.randomUUID().toString();
			user.setToken(uuid);
			userRepository.save(user);
			return uuid;
		} else {
			throw new RegistrationException("There is user with email " + user.getEmail() + " in DB."
					+ "Login in with your password on /api/login");
		}
	}

	@Override
	public User getUser(String email, String password) {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new NoSuchUserException("No users with email " + email + " have found. " + "Sign up on /api/signup");
		} else if (!user.getPassword().equals(password)) {
			throw new LoginException("You have entered wrong pasword, try again.");
		}
		return user;
	}
}
