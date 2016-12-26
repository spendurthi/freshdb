package com.sone.freshdb.dto;

import java.util.Date;

public class EmpDTO {
	private Integer sid;	
	private Integer id;	
	private String name;	
	private String job;	
	private Integer mgr;	
	private Date hireDate;	
	private float sal;	
	private float comm;
	private DeptDTO dept;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	public float getComm() {
		return comm;
	}

	public void setComm(float comm) {
		this.comm = comm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpDTO other = (EmpDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public void setDept(DeptDTO deptDto) {
		this.dept = deptDto;
	}
	public DeptDTO getDept() {
		return dept;
	}
}
