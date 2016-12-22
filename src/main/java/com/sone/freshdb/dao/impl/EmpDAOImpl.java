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

import com.sone.freshdb.dao.EmpDAO;
import com.sone.freshdb.dto.EmpDTO;
import com.sone.freshdb.model.Emp;
import com.sone.freshdb.utils.Beans;

@Repository(value="empDAO")
@Lazy(value=true)
public class EmpDAOImpl implements EmpDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(EmpDTO dto) {
		Emp ent = prepareEntity(dto);
		entityManager.persist(ent);
	}

	@Override
	public List<EmpDTO> getAllEmps() {
		List<EmpDTO> lstDto=new ArrayList<EmpDTO>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Emp> cq = builder.createQuery(Emp.class);
		Root<Emp> root = cq.from(Emp.class);
		cq.select(root);
		List<Emp> lstEnt = entityManager.createQuery(cq).getResultList();
		for(Emp ent:lstEnt){
			lstDto.add(prepareDto(ent));
		}
		return lstDto;
	}
	private Emp prepareEntity(EmpDTO dto){
		Emp ent=new Emp();
		Beans.copyProperties(dto, ent);
		return ent;
		
	}
	private EmpDTO prepareDto(Emp ent){
		EmpDTO dto = new EmpDTO();
		Beans.copyProperties(ent,dto);
		return dto;
	}

}
