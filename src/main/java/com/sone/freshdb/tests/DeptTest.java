package com.sone.freshdb.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sone.freshdb.service.DeptService;
import com.sone.freshdb.vo.DeptVO;

public class DeptTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		DeptService deptService = (DeptService) ctx.getBean("deptService");
		DeptTest deptTest=new DeptTest();
		deptTest.readAll(deptService);
	}
	public void save(DeptService deptService){
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptId(101);
		deptVO.setDeptName("CM");
		deptService.save(deptVO);
		System.out.println("Dept inserted!");
		readAll(deptService);
	}
	public void readAll(DeptService deptService){
		List<DeptVO> list = deptService.getAllDepts();
		System.out.println("Dept count: " + list.size());
		
		for(DeptVO vo:list){
			System.out.println(vo.getDeptName());
			
		}
		
	}

}
