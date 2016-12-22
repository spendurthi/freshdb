package com.sone.freshdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sone.freshdb.dao.UserDAO;
import com.sone.freshdb.dto.UserDTO;
import com.sone.freshdb.service.UserService;
import com.sone.freshdb.utils.Beans;
import com.sone.freshdb.vo.UserVO;

@Service(value="userService")
@Lazy(value=true)
public class UserServiceImpl implements UserService {

	@Inject private UserDAO userDAO;

	@Override
	@Transactional
	public void save(UserVO vo) {
		UserDTO dto = prepareDto(vo);
		userDAO.save(dto);
	}

	@Override
	public List<UserVO> getAllUsers() {
		List<UserVO> lstVO = new ArrayList<UserVO>();
		List<UserDTO> lstDto =  userDAO.getAllUsers();
		for(UserDTO dto:lstDto){
			lstVO.add(prepareVO(dto));
		}
		return lstVO;
	}
	
	private UserVO prepareVO(UserDTO dto){
		UserVO vo=new UserVO();
		Beans.copyProperties(dto, vo);
		return vo;
		
	}
	private UserDTO prepareDto(UserVO vo){
		UserDTO dto = new UserDTO();
		Beans.copyProperties(vo,dto);
		return dto;
	}

}
