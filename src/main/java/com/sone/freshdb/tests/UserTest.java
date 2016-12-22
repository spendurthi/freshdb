package com.sone.freshdb.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sone.freshdb.service.UserService;
import com.sone.freshdb.vo.UserVO;

public class UserTest {
	
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		List<UserVO> list = userService.getAllUsers();
		System.out.println("User count: " + list.size());

		UserVO userVO = new UserVO();
		userVO.setUserName("pendurthis");
		userVO.setPassword("abcd123$");
		userVO.setFirstName("Surya");
		userVO.setLastName("Pendurthi");
		userService.save(userVO);
		System.out.println("User inserted!");

		list = userService.getAllUsers();
		System.out.println("User count: " + list.size());
	}
	
	public static void main(String[] args) {
		UserTest userTest = new UserTest();
		userTest.test();
		

	}
}
