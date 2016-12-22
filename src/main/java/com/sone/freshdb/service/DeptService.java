package com.sone.freshdb.service;

import java.util.List;

import com.sone.freshdb.vo.DeptVO;

public interface DeptService {

	void save(DeptVO dept);
	List<DeptVO> getAllDepts();
}
