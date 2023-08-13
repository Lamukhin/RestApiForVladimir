package com.lamukhin.springboot.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamukhin.springboot.dao.MessageRepository;
import com.lamukhin.springboot.dao.UserRepository;
import com.lamukhin.springboot.entity.Message;
import com.lamukhin.springboot.entity.User;
import com.lamukhin.springboot.exception_handling.AccessException;
import com.lamukhin.springboot.exception_handling.NoSuchMessageException;
import com.lamukhin.springboot.exception_handling.NoSuchUserException;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	@Override
	public void saveMessage(Message message, String token) {
		if(userRepository.findUserByToken(token)==null) {
			throw new NoSuchUserException("There is no user with this token " +token+ " in DB.");
		}
		message.setTimestamp(new Timestamp(System.currentTimeMillis()));
		message.setUser(userRepository.findUserByToken(token));
		messageRepository.save(message);
	}

	@Override
	public void deleteMessage(int id, String token) {
		Message message = messageRepository.getById(id);
		if (message == null) {
			throw new NoSuchMessageException("There is no message with ID = " + id + " in DB");
		} else if ((message.getUser().getToken().equals(token)) || (userRepository.findUserByToken(token).getRole().equals("ADMIN"))) {
			messageRepository.deleteById(id);
		} else {
			throw new AccessException("You cannot delete the message you didn't post.");
		}
		messageRepository.deleteById(id);

	}

}
