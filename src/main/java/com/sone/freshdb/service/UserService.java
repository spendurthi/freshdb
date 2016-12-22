package com.sone.freshdb.service;

import java.util.List;

import com.sone.freshdb.vo.UserVO;

public interface UserService {

	void save(UserVO vo);
	List<UserVO> getAllUsers();
}
