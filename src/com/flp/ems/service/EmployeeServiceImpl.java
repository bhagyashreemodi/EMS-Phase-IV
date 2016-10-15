package com.flp.ems.service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.flp.ems.dao.EmployeeDaoImplForDB;
import com.flp.ems.domain.Employee;
@Service("service")
public class EmployeeServiceImpl implements IEmployeeService{

	
	//EmployeeDaoImplForDB employees = new EmployeeDaoImplForDB();
	@Autowired
	EmployeeDaoImplForDB employees;
	@Override
	public void addEmployee(HashMap<String, String> employeeInfo) throws Exception {
		
		Employee employee = new Employee();
		employee.setName(employeeInfo.get("name"));
		
		int i = getNumberOfEmployees();
		String kinId = i + 1+ "_FS";
		String emailId = kinId + employeeInfo.get("name") + "@barclays.com";
		employee.setKinId(kinId);
		employee.setEmailId(emailId.replaceAll("\\s",""));
		employee.setPhoneNumber(Long.valueOf(employeeInfo.get("phoneNumber")));
		employee.setAddres(employeeInfo.get("address"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = format.parse(employeeInfo.get("birthDate"));
		employee.setBirthDate(birthDate);
		Date joiningDate = format.parse(employeeInfo.get("joiningDate"));
		employee.setJoiningDate(joiningDate);
		employee.setDepartmentId(Integer.valueOf(employeeInfo.get("departmentId")));
		employee.setProjectId(Integer.valueOf(employeeInfo.get("projectId")));
		employee.setRoleId(Integer.valueOf(employeeInfo.get("roleId")));
		employees.addEmployee(employee);
	
	}
	

	@Override
	public boolean modifyEmployee(HashMap<String, String> employeeInfo) throws Exception {
		
		//ArrayList<Employee> employees = this.employees.getAllEmployee();
		Employee modifyEmployee = this.employees.getEmpForModification(employeeInfo.get("kinId"));
		Employee employee =null;
		
		if(modifyEmployee == null){
			return false;
		}
		try {
			employee = (Employee) modifyEmployee.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		employee.setPhoneNumber(Long.valueOf(employeeInfo.get("phoneNumber")));
		employee.setAddres(employeeInfo.get("address"));
		employee.setName(employeeInfo.get("name"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = format.parse(employeeInfo.get("birthDate"));
		employee.setBirthDate(birthDate);
		Date joiningDate = format.parse(employeeInfo.get("joiningDate"));
		employee.setJoiningDate(joiningDate);
		employee.setDepartmentId(Integer.valueOf(employeeInfo.get("departmentId")));
		employee.setProjectId(Integer.valueOf(employeeInfo.get("projectId")));
		employee.setRoleId(Integer.valueOf(employeeInfo.get("roleId")));
		
		
		
		return this.employees.modifyEmployee(employee);
	}

	@Override
	public boolean removeEmployee(HashMap<String, String> remEmployeeMap) throws Exception{
		
		Employee employee = new Employee();
		//employee.setName(remEmployeeMap.get("name"));
		employee.setKinId(remEmployeeMap.get("kinId"));
	return this.employees.removeEmployee(employee.getKinId());
	}

	@Override
	public ArrayList<HashMap<String, String>> searchEmployee(HashMap<String, String> employeeInfo) throws Exception{
		Employee employee = new Employee();
		employee.setName(employeeInfo.get("name"));
		employee.setKinId(employeeInfo.get("kinId"));
		employee.setEmailId(employeeInfo.get("emailId"));
		ArrayList<Employee> employeesArr = this.employees.searchEmployee(employee);
		ArrayList	<HashMap<String, String>> employeesMap = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < employeesArr.size(); i++) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("kinId", (employeesArr.get(i).getKinId()));
			map.put("emailId", (employeesArr.get(i).getEmailId()));
			map.put("name", (employeesArr.get(i).getName()));
			map.put("address", (employeesArr.get(i).getAddres()));
			map.put("departmentId", String.valueOf((employeesArr.get(i).getDepartmentId())));
			map.put("projectId",String.valueOf((employeesArr.get(i).getProjectId())));
			map.put("roleId", String.valueOf((employeesArr.get(i).getRoleId())));
			map.put("phoneNumber", (String.valueOf(employeesArr.get(i).getPhoneNumber())));
			map.put("birthDate",employeesArr.get(i).getBirthDate().toString());
			map.put("joiningDate",employeesArr.get(i).getJoiningDate().toString());	employeesMap.add(map);
		}
		return employeesMap;
	}

	@Override
	public ArrayList<HashMap<String, String>> getAllEmployee() throws Exception {
		ArrayList<Employee> employeesArr = this.employees.getAllEmployee();
	ArrayList	<HashMap<String, String>> employeesMap = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < employeesArr.size(); i++) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("kinId", (employeesArr.get(i).getKinId()));
			map.put("emailId", (employeesArr.get(i).getEmailId()));
			map.put("name", (employeesArr.get(i).getName()));
			map.put("address", (employeesArr.get(i).getAddres()));
			map.put("departmentId", String.valueOf((employeesArr.get(i).getDepartmentId())));
			map.put("projectId",String.valueOf((employeesArr.get(i).getProjectId())));
			map.put("roleId", String.valueOf((employeesArr.get(i).getRoleId())));
			map.put("phoneNumber", (String.valueOf(employeesArr.get(i).getPhoneNumber())));
			map.put("birthDate",employeesArr.get(i).getBirthDate().toString());
			map.put("joiningDate",employeesArr.get(i).getJoiningDate().toString());
			employeesMap.add(map);
		}
		return employeesMap;
	}

	public int getNumberOfEmployees()throws Exception{
		return employees.getNumberOfEmployees();		
	}
	
	
}
