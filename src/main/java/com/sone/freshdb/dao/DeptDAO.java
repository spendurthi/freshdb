package com.sone.freshdb.dao;

import java.util.List;

import com.sone.freshdb.dto.DeptDTO;

public interface DeptDAO {

	void save(DeptDTO dto);
	List<DeptDTO> getAllDepts();
}
