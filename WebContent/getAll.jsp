<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Home</title>
<link rel="stylesheet" href="index.css">
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="wrapper">
    <div class="top_banner">
      <h1 align="center">Employee Management System</h1>
    </div>
    <div class="navigation" align="left">
      <ul>
        <li><a href="createEmployee.jsp">Add New Employee</a></li>
        <li><a href="controller?action=seeAll&page=modifyEmployee.jsp">Modify Employee Details</a></li>
        <li><a href="search.jsp">Search Employee</a></li>
        <li><a href="controller?action=seeAll&page=removeEmployee.jsp">Remove Employee</a></li>
        <li><a href="controller?action=getAll">See All Employees</a></li>
      </ul>
    </div>
    <div class="modify">
    	
    	<table>
  <tr>
    <th>Name</th>
    <th>Address</th>
    <th>Phone number</th>
    <th>Birth date</th>
    <th>joining Date</th>
    <th>Department</th>
    <th>Project</th>
    <th>Role</th>
  </tr>
  <%
  	ArrayList<HashMap<String, String>> employees =  (ArrayList<HashMap<String, String>>)session.getAttribute("employees");
  	for(HashMap<String, String> employee: employees){
  	
  	%>
  	<tr>
  		<td><%=employee.get("name") %></td>	
		<td><%=employee.get("address") %></td>  
   		<td><%=employee.get("phoneNumber") %></td>
   		<td><%=employee.get("birthDate") %></td>
   		<td><%=employee.get("joiningDate") %></td>
   		<td><%=employee.get("departmentId") %></td>
   		<td><%=employee.get("projectId") %></td>
   		<td><%=employee.get("roleId") %></td>
  </tr>
 
  <%} %>
</table>

    	
    
    
    </div>
</div>


</body>
</html>