package com.flp.ems.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.flp.ems.domain.Employee;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


@Repository("dao")
public class EmployeeDaoImplForDB implements IEmployeeDao{

	
	Properties props = new Properties();
	InputStream propsFile;
	Connection dbConnection;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private DataSource dataSource;
	@Override
	public void addEmployee(Employee employee) throws Exception {
		
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		BeanPropertySqlParameterSource sqlParameterSource;
		sqlParameterSource = new BeanPropertySqlParameterSource(employee);
		
		String insertQuery = "insert into employee (name,kin_id,email_id,phone_number,birth_date,joining_date,address,department_id,project_id,role_id) values(:name,:kinId,:emailId,:phoneNumber,:birthDate,:joiningDate,:addres,:departmentId,:projectId,:roleId)";
		
		namedParameterJdbcTemplate.update(insertQuery, sqlParameterSource);
			
		
		
		
		
		
	}

	@Override
	public boolean modifyEmployee(Employee modifyEmployee) throws Exception{
		
		if(modifyEmployee != null){
			
			this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
			BeanPropertySqlParameterSource sqlParameterSource;
			sqlParameterSource = new BeanPropertySqlParameterSource(modifyEmployee);
			
			String updateQuery = "update employee set name=:name, phone_number=:phoneNumber,birth_date=:birthDate,joining_date=:joiningDate,address=:addres,department_id=:departmentId,project_id=:projectId,role_id=:roleId where kin_id=:kinId";
			namedParameterJdbcTemplate.update(updateQuery, sqlParameterSource);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean removeEmployee(String kinId) throws Exception {
		
		if(kinId != null){
			this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
			String updateQuery = "delete from employee where kin_id=:kinId";
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("kinId",kinId);
			namedParameterJdbcTemplate.update(updateQuery, sqlParameterSource);
			
			return true;
		}
		else{
			return false;
		}
		
		
		
	}

	@Override
	public ArrayList<Employee> searchEmployee(Employee employee) throws Exception {
		
		ArrayList<Employee> empl = new ArrayList<Employee>();
		String searchQuery;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(employee);
		if(employee.getKinId() != null){
			searchQuery = "select * from employee where kin_id=:kinId";
			empl= (ArrayList<Employee>) namedParameterJdbcTemplate.query(searchQuery, sqlParameterSource,new Employee());
		}
		else if(employee.getEmailId() != null){
			searchQuery = "select * from employee where email_id=:emailId";
			empl= (ArrayList<Employee>) namedParameterJdbcTemplate.query(searchQuery, sqlParameterSource,new Employee());
		}
		else{
			searchQuery = "select * from employee where name=:name";
			empl= (ArrayList<Employee>) namedParameterJdbcTemplate.query(searchQuery, sqlParameterSource,new Employee());
		}
		
		return empl;
		
	}

	@Override
	public ArrayList getAllEmployee() throws Exception{
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		String searchQuery;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		searchQuery = "select * from employee";
		employees= (ArrayList<Employee>) namedParameterJdbcTemplate.query(searchQuery,new Employee());
		
		return employees;
		
		
		
	}

	@Override
	public Employee getEmpForModification(String kinId) throws Exception{
		
		String searchQuery;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		searchQuery = "select * from employee where kin_id=:kinId";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("kinId",kinId);
		Employee empl= (Employee) namedParameterJdbcTemplate.query(searchQuery, sqlParameterSource,new Employee());
		return empl;
		
	}

	
	public int getNumberOfEmployees()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
		PreparedStatement selectStatement = dbConnection.prepareStatement("select * from employee");
		ResultSet result = selectStatement.executeQuery();
		int count = 0;
		if(result.next()){
			result.last();
			count = result.getInt("employee_id");
		}
		return count;		
	}
	

	
	
	
	
}
