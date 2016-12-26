package com.sone.freshdb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.sone.freshdb.dao.EmpDAO;
import com.sone.freshdb.dto.DeptDTO;
import com.sone.freshdb.dto.EmpDTO;
import com.sone.freshdb.exceptions.FreshDbException;
import com.sone.freshdb.model.Dept;
import com.sone.freshdb.model.Emp;

@Repository(value="empDAO")
@Lazy(value=true)
public class EmpDAOImpl implements EmpDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(EmpDTO dto)throws FreshDbException {
		Emp ent = prepareEntity(dto);		
		if (dto.getDept()!=null){
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Dept> cq = builder.createQuery(Dept.class);
			Root<Dept> root = cq.from(Dept.class);
			cq.select(root);
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			Predicate predicate1 = builder.equal(root.get("sid"),dto.getDept().getSid());
			criteriaList.add(predicate1);
			cq.where(criteriaList.toArray(new Predicate[]{}));
			List<Dept> lstDept=entityManager.createQuery(cq).getResultList();
			if (lstDept!=null && lstDept.size()>0){
				Dept existingDept=lstDept.get(0);				
				ent.setDept(existingDept);
				List<Emp> emps = new ArrayList<Emp>();
				emps.add(ent);
				existingDept.setEmployees(emps);
				entityManager.persist(existingDept);
			}
		}		
	}

	@Override
	public List<EmpDTO> getAllEmps()throws FreshDbException {
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
	private Emp prepareEntity(EmpDTO empDto){
		Emp empEnt=new Emp();			
		empEnt.setComm(empDto.getComm());
		empEnt.setHireDate(empDto.getHireDate());
		empEnt.setId(empDto.getId());
		empEnt.setJob(empDto.getJob());
		empEnt.setMgr(empDto.getMgr());
		empEnt.setName(empDto.getName());
		empEnt.setSal(empDto.getSal());
		empEnt.setSid(empDto.getSid());
		DeptDTO deptDto = empDto.getDept();
		if (deptDto!=null){
			Dept deptEnt = new Dept();
			deptEnt.setDeptId(deptEnt.getDeptId());
			deptEnt.setDeptLoc(deptEnt.getDeptLoc());
			deptEnt.setDeptName(deptEnt.getDeptName());
			deptEnt.setSid(deptEnt.getSid());
			empEnt.setDept(deptEnt);
		}
		return empEnt;
		
	}
	private EmpDTO prepareDto(Emp empEnt){
		EmpDTO empDto = new EmpDTO();
		empDto.setComm(empEnt.getComm());
		empDto.setHireDate(empEnt.getHireDate());
		empDto.setId(empEnt.getId());
		empDto.setJob(empEnt.getJob());
		empDto.setMgr(empEnt.getMgr());
		empDto.setName(empEnt.getName());
		empDto.setSal(empEnt.getSal());
		empDto.setSid(empEnt.getSid());
		Dept deptEnt = empEnt.getDept();
		if (deptEnt!=null){
			DeptDTO deptDto = new DeptDTO();
			deptDto.setDeptId(deptEnt.getDeptId());
			deptDto.setDeptLoc(deptEnt.getDeptLoc());
			deptDto.setDeptName(deptEnt.getDeptName());
			deptDto.setSid(deptEnt.getSid());
			empDto.setDept(deptDto);
		}		
		return empDto;
	}

}
