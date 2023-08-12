package com.lamukhin.springboot.service;

import java.util.List;

import com.lamukhin.springboot.entity.Message;


public interface MessageService {
	//получение всех сообщений пользователем 
	public List<Message> getAllMessages();
	//сохранение сообщения в бд по уникальному токену
	public void saveMessage(Message message, String token);
	//удаление сообщения пользователем по id использованием токена
	public void deleteMessage(int id, String token);

}
