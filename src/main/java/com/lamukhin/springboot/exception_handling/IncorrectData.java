package com.lamukhin.springboot.exception_handling;
//данный класс ответственен за содержимое json при выбрасывании исключения
public class IncorrectData {
	private String info;
	
	public IncorrectData() {}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
