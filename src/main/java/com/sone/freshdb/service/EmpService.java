package com.sone.freshdb.service;

import java.util.List;

import com.sone.freshdb.vo.EmpVO;

public interface EmpService {

	void save(EmpVO dept);
	List<EmpVO> getAllEmps();
}
