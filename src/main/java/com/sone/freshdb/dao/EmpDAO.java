package com.sone.freshdb.dao;

import java.util.List;

import com.sone.freshdb.dto.EmpDTO;
import com.sone.freshdb.exceptions.FreshDbException;

public interface EmpDAO {

	void save(EmpDTO dto)throws FreshDbException;
	List<EmpDTO> getAllEmps()throws FreshDbException;
	
}
