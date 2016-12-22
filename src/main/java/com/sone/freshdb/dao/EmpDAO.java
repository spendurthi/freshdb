package com.sone.freshdb.dao;

import java.util.List;

import com.sone.freshdb.dto.EmpDTO;

public interface EmpDAO {

	void save(EmpDTO dto);
	List<EmpDTO> getAllEmps();
}
