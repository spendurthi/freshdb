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

import com.sone.freshdb.dao.DeptDAO;
import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.model.Dept;
import com.sone.freshdb.utils.Beans;

@Repository(value="deptDAO")
@Lazy(value=true)
public class DeptDAOImpl implements DeptDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(DeptDTO dto) {
		Dept ent = prepareEntity(dto);
		entityManager.persist(ent);
	}

	@Override
	public List<DeptDTO> getAllDepts() {
		List<DeptDTO> lstDto=new ArrayList<DeptDTO>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Dept> cq = builder.createQuery(Dept.class);
		Root<Dept> root = cq.from(Dept.class);
		cq.select(root);
		List<Dept> lstEnt = entityManager.createQuery(cq).getResultList();
		for(Dept dept:lstEnt){
			System.out.println(dept.getEmployees());
			lstDto.add(prepareDto(dept));
		}
		return lstDto;
	}
	private Dept prepareEntity(DeptDTO dto){
		Dept ent=new Dept();
		Beans.copyProperties(dto, ent);
		return ent;
		
	}
	private DeptDTO prepareDto(Dept ent){
		DeptDTO dto = new DeptDTO();
		Beans.copyProperties(ent,dto);
		return dto;
	}

}
