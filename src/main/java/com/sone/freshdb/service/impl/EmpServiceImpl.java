package com.sone.freshdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sone.freshdb.dao.EmpDAO;
import com.sone.freshdb.dto.EmpDTO;
import com.sone.freshdb.service.EmpService;
import com.sone.freshdb.utils.Beans;
import com.sone.freshdb.vo.EmpVO;

@Service(value="empService")
@Lazy(value=true)
public class EmpServiceImpl implements EmpService {

	@Inject private EmpDAO empDAO;

	@Override
	@Transactional
	public void save(EmpVO vo) {
		EmpDTO dto = prepareDto(vo);
		empDAO.save(dto);
	}

	@Override
	public List<EmpVO> getAllEmps() {
		List<EmpVO> lstVO = new ArrayList<EmpVO>();
		List<EmpDTO> lstDto =  empDAO.getAllEmps();
		for(EmpDTO dto:lstDto){
			lstVO.add(prepareVO(dto));
		}
		return lstVO;
	}
	
	private EmpVO prepareVO(EmpDTO dto){
		EmpVO vo=new EmpVO();
		Beans.copyProperties(dto, vo);
		return vo;
		
	}
	private EmpDTO prepareDto(EmpVO vo){
		EmpDTO dto = new EmpDTO();
		Beans.copyProperties(vo,dto);
		return dto;
	}
}
