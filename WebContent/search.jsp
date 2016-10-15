<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Home</title>
<link rel="stylesheet" href="index.css">
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
    <div class="search">
    	<form action="controller?action=search" method="post">
    		<select name="criteria">
    			<option>select Search Criteria</option>
    			<option value="name">name</option>
    			<option value="kinId">kin ID</option>
    			<option value="emailId">emailId</option>
    		</select><br><br>
    		<input type="text" name="value" placeholder="enter the value"><br><br>
    		<input type="submit" name="search" value="Search"> 
    	</form>
    
    </div>
</div>

</body>
</html>