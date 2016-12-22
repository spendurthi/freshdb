package com.sone.freshdb.tests;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sone.freshdb.service.EmpService;
import com.sone.freshdb.vo.EmpVO;

public class EmpTest {

	public static void main(String[] args) {
		EmpTest empTest=new EmpTest();
		empTest.test();
	}
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EmpService empService = (EmpService) ctx.getBean("empService");
		List<EmpVO> list = empService.getAllEmps();
		System.out.println("Emp count: " + list.size());

		EmpVO empVO = new EmpVO();
		empVO.setId(1000);
		empVO.setName("Surya");
		empVO.setComm(10);
		empVO.setHireDate(new Date());
		empVO.setJob("Developer");
		empVO.setSal(6000);
		empVO.setMgr(1000);
		
		empService.save(empVO);
		System.out.println("Emp inserted!");

		list = empService.getAllEmps();
		System.out.println("Dept count: " + list.size());
	}

}
