package com.sone.freshdb.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DEPT")
public class Dept {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DEPT_SID", nullable = false)
	private Integer sid;
	
	@Column(name="DEPT_ID",nullable=false)
	private Integer deptId;
	
	@Column(name="DEPT_NAME",nullable=false)
	private String deptName;
	
	@Column(name="DEPT_LOC")
	private String deptLoc;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "dept")
	private List<Emp> employees;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}
	
	public void setEmployees(List<Emp> employees) {
		this.employees = employees;
	}
	public List<Emp> getEmployees() {
		return employees;
	}
	
}
