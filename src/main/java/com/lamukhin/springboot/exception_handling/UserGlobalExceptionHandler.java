package com.lamukhin.springboot.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {
	@ExceptionHandler //эта аннотация отмечает метод-отлавливатель ошибок
	//респонс энтити - обертка http response, а его дженерик - объект, который добавится в http response body
	//в данном случае это будет объект, содержащий джсон с 1 полем для указания ошибки
	//в качестве параметра передается тип ошибки, на который данный хэндлер должен реагировать
	public ResponseEntity<IncorrectData> handleException(RegistrationException exception){
		IncorrectData data = new IncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler 
	public ResponseEntity<IncorrectData> handleException(LoginException exception){
		IncorrectData data = new IncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler 
	public ResponseEntity<IncorrectData> handleException(NoSuchUserException exception){
		IncorrectData data = new IncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler 
	public ResponseEntity<IncorrectData> handleException(AccessException exception){
		IncorrectData data = new IncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
	}
}
