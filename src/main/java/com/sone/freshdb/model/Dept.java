package com.sone.freshdb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEPT")
public class Dept {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SID", nullable = false)
	private int sid;
	
	@Column(name="DEPT_ID",nullable=false)
	private int deptId;
	
	@Column(name="DEPT_NAME",nullable=false)
	private String deptName;
	
	@Column(name="DEPT_LOC")
	private String deptLoc;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dept")
	private Set<Emp> employees = new HashSet<Emp>(0);

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
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
	
	public void setEmployees(Set<Emp> employees) {
		this.employees = employees;
	}
	public Set<Emp> getEmployees() {
		return employees;
	}
	
}
