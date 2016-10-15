package com.flp.ems.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class Employee implements Cloneable,RowMapper<Employee>{

//	private static long employeeId;
	private long phoneNumber;
	private String name, kinId, emailId, addres;
	private Date joiningDate, birthDate;
	private int departmentId, projectId, roleId;
	
	/*static {
		employeeId++;
	}


	public static long getEmployeeId() {
		return employeeId;
	}*/


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getKinId() {
		return kinId;
	}


	public void setKinId(String kinId) {
		this.kinId = kinId;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getAddres() {
		return addres;
	}


	public void setAddres(String addres) {
		this.addres = addres;
	}


	public Date getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int long1) {
		this.departmentId = long1;
	}


	public int getProjectId() {
		return projectId;
	}


	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
			
			return super.clone();
		}


	@Override
	public Employee mapRow(ResultSet selectResult, int arg1) throws SQLException {
		Employee tempEmployee = new Employee();
		tempEmployee.setName(selectResult.getString("name"));
		tempEmployee.setKinId(selectResult.getString("kin_id"));
		tempEmployee.setEmailId(selectResult.getString("email_id"));
		tempEmployee.setPhoneNumber(selectResult.getLong("phone_number"));
		tempEmployee.setBirthDate(selectResult.getDate("birth_date"));
		tempEmployee.setJoiningDate(selectResult.getDate("joining_date"));
		tempEmployee.setAddres(selectResult.getString("address"));
		tempEmployee.setDepartmentId(selectResult.getInt("department_id"));
		tempEmployee.setProjectId(selectResult.getInt("project_id"));
		tempEmployee.setRoleId(selectResult.getInt("role_id"));
		return tempEmployee;
	}
}
