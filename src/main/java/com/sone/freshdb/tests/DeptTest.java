package com.sone.freshdb.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sone.freshdb.dao.DeptDAO;
import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.exceptions.FreshDbException;


public class DeptTest {

	public static void main(String[] args) throws FreshDbException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		DeptDAO deptDao = (DeptDAO) ctx.getBean("deptDAO");
		DeptTest deptTest=new DeptTest();
		//deptTest.readAll(deptDao);
		//deptTest.save(deptDao);
		deptTest.read(deptDao);
	}
	public void save(DeptDAO deptDao) throws FreshDbException{
		DeptDTO deptDto = new DeptDTO();
		deptDto.setDeptId(101);
		deptDto.setDeptName("CM");
		deptDao.save(deptDto);
		System.out.println("Dept inserted!");
		readAll(deptDao);
	}
	public void readAll(DeptDAO deptDao) throws FreshDbException{
		List<DeptDTO> list = deptDao.getAllDepts();
		System.out.println("Dept count: " + list.size());
		
		for(DeptDTO vo:list){
			System.out.println(vo);
			
		}		
	}
	public void read(DeptDAO deptDao) throws FreshDbException{
		DeptDTO dto=new DeptDTO();
		dto.setDeptName("it");
		List<DeptDTO> list = deptDao.getDepts(dto);
		System.out.println("Dept count: " + list.size());
		
		for(DeptDTO vo:list){
			System.out.println(vo);
			
		}		
	}

}
