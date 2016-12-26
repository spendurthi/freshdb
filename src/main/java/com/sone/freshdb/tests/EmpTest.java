package com.sone.freshdb.tests;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sone.freshdb.dao.EmpDAO;
import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.dto.EmpDTO;
import com.sone.freshdb.exceptions.FreshDbException;

public class EmpTest {

	public static void main(String[] args) throws FreshDbException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EmpDAO empDao = (EmpDAO) ctx.getBean("empDAO");
		EmpTest empTest=new EmpTest();
		empTest.save(empDao);
		empTest.readAll(empDao);
	}
	public void save(EmpDAO empDao) throws FreshDbException{
		EmpDTO empDto = new EmpDTO();
		empDto.setId(1002);
		empDto.setName("Surya1");
		empDto.setComm(10);
		empDto.setHireDate(new Date());
		empDto.setJob("Developer1");
		empDto.setSal(6000);
		empDto.setMgr(1000);
		DeptDTO deptDto = new DeptDTO();
		deptDto.setSid(103);
		empDto.setDept(deptDto);
		
		
		
		empDao.save(empDto);
		System.out.println("Emp inserted!");
	}
	public void readAll(EmpDAO empDao) throws FreshDbException{
		
		List<EmpDTO> list = empDao.getAllEmps();
		System.out.println("Emp count: " + list.size());
		for(EmpDTO empDto:list){
			System.out.println(empDto);
		}

	}

}
