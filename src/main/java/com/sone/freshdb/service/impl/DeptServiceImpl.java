package com.sone.freshdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sone.freshdb.dao.DeptDAO;
import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.service.DeptService;
import com.sone.freshdb.utils.Beans;
import com.sone.freshdb.vo.DeptVO;

@Service(value="deptService")
@Lazy(value=true)
public class DeptServiceImpl implements DeptService {

	@Inject private DeptDAO deptDAO;

	@Override
	@Transactional
	public void save(DeptVO vo) {
		DeptDTO dto = prepareDto(vo);
		deptDAO.save(dto);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public List<DeptVO> getAllDepts() {
		List<DeptVO> lstVO = new ArrayList<DeptVO>();
		List<DeptDTO> lstDto =  deptDAO.getAllDepts();
		for(DeptDTO dto:lstDto){
			lstVO.add(prepareVO(dto));
		}
		return lstVO;
	}
	
	private DeptVO prepareVO(DeptDTO dto){
		DeptVO vo=new DeptVO();
		Beans.copyProperties(dto, vo);
		return vo;
		
	}
	private DeptDTO prepareDto(DeptVO vo){
		DeptDTO dto = new DeptDTO();
		Beans.copyProperties(vo,dto);
		return dto;
	}
}
