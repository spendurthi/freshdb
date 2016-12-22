package com.sone.freshdb.dao;

import java.util.List;

import com.sone.freshdb.dto.UserDTO;

public interface UserDAO {

	void save(UserDTO user);
	List<UserDTO> getAllUsers();
}
