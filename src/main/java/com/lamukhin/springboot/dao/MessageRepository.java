package com.lamukhin.springboot.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.lamukhin.springboot.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	//если мы правильно называеем класс и наследуемся от JpaRepository с указанием в дженериках класса,
	//которым будем управлять и типа Primary Key, то дополнительно логику писать не нужно, 
	//Spring data сделает всё за нас.

	//все дополнительные методы для работы с базой мы можем реализовать тут сами, если их нет в JpaRepository
	//например, используя правильное название метода, спринг сам поймет, какую функциональность будет нести метод
	//и нам не придётся реализовывать логику в нем.
	//Для правильной работы метода необходимо верно указать параметр.
	//Если ищем name, то должен быть стринг
														
}
