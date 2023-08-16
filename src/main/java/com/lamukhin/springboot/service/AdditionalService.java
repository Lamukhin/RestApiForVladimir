package com.lamukhin.springboot.service;

import org.springframework.util.MultiValueMap;

import com.lamukhin.springboot.dto.MessageDto;
import com.lamukhin.springboot.dto.UserDto;
import com.lamukhin.springboot.entity.Message;
import com.lamukhin.springboot.entity.User;

public interface AdditionalService {
	/*это служебный класс, который содержит в себе
	дополнительные функции, которые требуются в разных
	частях проекта. пока функций немного, они будут храниться тут*/

	// добавление токена в хидерс
	public MultiValueMap<String, String> responseHeaders(String key, String value);
	// конвертация Message - MessageDto
	public MessageDto convertToMessageDto(Message message);
	// конвертация User - UserDto
    public UserDto convertToUserDto(User user);

}
