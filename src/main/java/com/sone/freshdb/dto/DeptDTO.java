package com.sone.freshdb.dto;

import java.util.ArrayList;
import java.util.List;

public class DeptDTO {
	private Integer sid;	
	private Integer deptId;	
	private String deptName;	
	private String deptLoc;
	private List<EmpDTO> employees = new ArrayList<EmpDTO>(0);
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptId;
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
		DeptDTO other = (DeptDTO) obj;
		if (deptId != other.deptId)
			return false;
		return true;
	}
	public void setEmployees(List<EmpDTO> employees) {
		this.employees = employees;
	}
	public List<EmpDTO> getEmployees() {
		return employees;
	}
	
}
