package com.sone.freshdb.dao;

import java.util.List;

import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.exceptions.FreshDbException;

public interface DeptDAO {

	void save(DeptDTO dto) throws FreshDbException;
	List<DeptDTO> getAllDepts()throws FreshDbException;
	List<DeptDTO> getDepts(DeptDTO deptDto)throws FreshDbException;
}
