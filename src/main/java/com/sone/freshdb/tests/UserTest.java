package com.sone.freshdb.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sone.freshdb.dao.UserDAO;
import com.sone.freshdb.dto.UserDTO;

public class UserTest {
	
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserDAO userDao = (UserDAO) ctx.getBean("userService");
		List<UserDTO> list = userDao.getAllUsers();
		System.out.println("User count: " + list.size());

		UserDTO userDto = new UserDTO();
		userDto.setUserName("pendurthis");
		userDto.setPassword("abcd123$");
		userDto.setFirstName("Surya");
		userDto.setLastName("Pendurthi");
		userDao.save(userDto);
		System.out.println("User inserted!");

		list = userDao.getAllUsers();
		System.out.println("User count: " + list.size());
	}
	
	public static void main(String[] args) {
		UserTest userTest = new UserTest();
		userTest.test();
		

	}
}
