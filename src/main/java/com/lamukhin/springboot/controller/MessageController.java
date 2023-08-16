package com.lamukhin.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamukhin.springboot.config.MapperUtil;
import com.lamukhin.springboot.dto.MessageDto;
import com.lamukhin.springboot.dto.ShortResponse;
import com.lamukhin.springboot.entity.Message;
import com.lamukhin.springboot.service.AdditionalService;
import com.lamukhin.springboot.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private AdditionalService additionalService;
	
	// получение всех сообщений
		@GetMapping("/messages")
		public ResponseEntity<List<MessageDto>> getAllMessages() {
			List<Message> allMessages = messageService.getAllMessages();
			List<MessageDto> responseBody = MapperUtil.convertList(allMessages, additionalService::convertToMessageDto);
			return ResponseEntity.ok(responseBody);
		}

		// отправка сообщения
		@PostMapping("/messages")
		public ResponseEntity<ShortResponse> saveMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message message) {
			messageService.saveMessage(message, token);
			ShortResponse responseBody = new ShortResponse("Done! Your message is saved.");
			return ResponseEntity.ok(responseBody);
		}

		// удаление сообщения 
		@DeleteMapping("/messages/{id}")
		public ResponseEntity<ShortResponse> deleteMessage(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {
			messageService.deleteMessage(id, token);
			ShortResponse responseBody = new ShortResponse("You have deleted message with ID = "+id);
			return ResponseEntity.ok(responseBody);
		}
}
