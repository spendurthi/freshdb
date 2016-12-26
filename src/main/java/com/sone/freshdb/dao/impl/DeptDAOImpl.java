package com.sone.freshdb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.sone.freshdb.dao.DeptDAO;
import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.dto.EmpDTO;
import com.sone.freshdb.exceptions.FreshDbException;
import com.sone.freshdb.model.Dept;
import com.sone.freshdb.model.Emp;

@Repository(value="deptDAO")
@Lazy(value=true)
public class DeptDAOImpl implements DeptDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(DeptDTO dto) throws FreshDbException{
		Dept ent = prepareEntity(dto);
		entityManager.persist(ent);
	}

	@Override
	public List<DeptDTO> getAllDepts()throws FreshDbException {
		List<DeptDTO> lstDto=new ArrayList<DeptDTO>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Dept> cq = builder.createQuery(Dept.class);
		Root<Dept> root = cq.from(Dept.class);
		cq.select(root);
		List<Dept> lstEnt = entityManager.createQuery(cq).getResultList();
		for(Dept dept:lstEnt){
			lstDto.add(prepareDto(dept));
		}
		return lstDto;
	}
	@Override
	public List<DeptDTO> getDepts(DeptDTO deptDto) throws FreshDbException {
		List<Dept> lstDept = null;
		List<DeptDTO> lstDeptDto = new ArrayList<DeptDTO>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Dept> cq = builder.createQuery(Dept.class);
		Root<Dept> root = cq.from(Dept.class);
		cq.select(root);
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		if (deptDto.getDeptId()!=null){
			criteriaList.add(builder.like(root.get("deptId").as(String.class), "%"+deptDto.getDeptId()+"%"));
		}
		if (deptDto.getDeptName()!=null){
			Expression<String> filterKeyExp = root.get("deptName").as(String.class);
			filterKeyExp = builder.lower(filterKeyExp);
			criteriaList.add(builder.like(filterKeyExp, "%"+deptDto.getDeptName().toLowerCase()+"%"));
		}
		if (deptDto.getDeptLoc()!=null){
			Expression<String> filterKeyExp = root.get("deptLoc").as(String.class);
			filterKeyExp = builder.lower(filterKeyExp);
			criteriaList.add(builder.like(filterKeyExp, "%"+deptDto.getDeptId().toString()+"%"));
		}
		if (criteriaList.size()>0){
			cq.where(criteriaList.toArray(new Predicate[]{}));
			lstDept=entityManager.createQuery(cq).getResultList();
		}
		if (lstDept!=null && lstDept.size()>0){
			for(Dept dept:lstDept){
				lstDeptDto.add(prepareDto(dept));
			}
		}
		return lstDeptDto;
	}
	private Dept prepareEntity(DeptDTO deptDto){
		Dept deptEnt=new Dept();
		Emp empEnt = null;

		deptEnt.setDeptId(deptDto.getDeptId());
		deptEnt.setDeptLoc(deptDto.getDeptLoc());
		deptEnt.setDeptName(deptDto.getDeptName());
		
		for(EmpDTO empDto:deptDto.getEmployees()){
			empEnt=new Emp();			
			empEnt.setComm(empDto.getComm());
			empEnt.setHireDate(empDto.getHireDate());
			empEnt.setId(empDto.getId());
			empEnt.setJob(empDto.getJob());
			empEnt.setMgr(empDto.getMgr());
			empEnt.setName(empDto.getName());
			empEnt.setSal(empDto.getSal());
			empEnt.setSid(empDto.getSid());
			deptEnt.getEmployees().add(empEnt);
			empEnt.setDept(deptEnt);
		}
		return deptEnt;
		
	}
	private DeptDTO prepareDto(Dept deptEnt){
		DeptDTO deptDto = new DeptDTO();
		EmpDTO empDto=null;
		
		deptDto.setDeptId(deptEnt.getDeptId());
		deptDto.setDeptLoc(deptEnt.getDeptLoc());
		deptDto.setDeptName(deptEnt.getDeptName());
		deptDto.setSid(deptEnt.getSid());
		
		for(Emp empEnt:deptEnt.getEmployees()){
			empDto = new EmpDTO();
			empDto.setComm(empEnt.getComm());
			empDto.setHireDate(empEnt.getHireDate());
			empDto.setId(empEnt.getId());
			empDto.setJob(empEnt.getJob());
			empDto.setMgr(empEnt.getMgr());
			empDto.setName(empEnt.getName());
			empDto.setSal(empEnt.getSal());
			empDto.setSid(empEnt.getSid());
			empDto.setDept(deptDto);
			deptDto.getEmployees().add(empDto);
		}
		
		return deptDto;
	}

}
