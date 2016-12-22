package com.sone.freshdb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.sone.freshdb.dao.UserDAO;
import com.sone.freshdb.dto.UserDTO;
import com.sone.freshdb.model.User;
import com.sone.freshdb.utils.Beans;

@Repository(value="userDAO")
@Lazy(value=true)
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(UserDTO dto) {
		User user = prepareEntity(dto);
		entityManager.persist(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> lstDto=new ArrayList<UserDTO>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		List<User> lstEnt = entityManager.createQuery(cq).getResultList();
		for(User usr:lstEnt){
			lstDto.add(prepareDto(usr));
		}
		return lstDto;
	}
	private User prepareEntity(UserDTO dto){
		User ent=new User();
		Beans.copyProperties(dto, ent);
		return ent;
		
	}
	private UserDTO prepareDto(User ent){
		UserDTO dto = new UserDTO();
		Beans.copyProperties(ent,dto);
		return dto;
	}

}
