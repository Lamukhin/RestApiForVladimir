package com.lamukhin.springboot.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.lamukhin.springboot.dto.MessageDto;
import com.lamukhin.springboot.dto.UserDto;
import com.lamukhin.springboot.entity.Message;
import com.lamukhin.springboot.entity.User;

@Service
public class AdditionalServiceImpl implements AdditionalService{
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MultiValueMap<String, String> responseHeaders(String key, String value) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		return headers;
	}

	@Override
	public MessageDto convertToMessageDto(Message message) {
		MessageDto messageDto = modelMapper.map(message, MessageDto.class);
		messageDto.setUserDto(convertToUserDto(message.getUser()));
        return messageDto;
    }

	@Override
	public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
