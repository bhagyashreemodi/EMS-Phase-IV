<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Home</title>
<link rel="stylesheet" href="index.css">
<link rel="stylesheet" href="style.css">
</head>
<!-- <script type="text/javascript">
	function confirm() {
		var kinid = document.getElementsByName("Delete").value;
		alert("Do u want to remove the employee with kinId"+kinid);
		return false;
	}



</script> -->
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
    <div class="remove">
    	<form action="controller?action=searchRemove" method="post">
    		<select name="criteria">
    			<option>select Search Criteria</option>
    			<option value="name">name</option>
    			<option value="kinId">kin ID</option>
    			<option value="emailId">emailId</option>
    		</select><br><br>
    		<input type="text" name="value" placeholder="enter the value"><br><br>
    		<input type="submit" name="search" value="Search"> 
    	</form>
    	<table style="top:40%">
  <tr>
    <th>Kin ID</th>
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
  	<form action="controller?action=remove" method="post">
  	<tr>
  	
    	<td><input type="submit" name="Delete" value=<%=employee.get("kinId") %> style="width:100%" onclick="return confirm('Do you want to remove the employee with kin Id <%=employee.get("kinId") %>?');"></td>
    	<td><%=employee.get("name") %></td>	
		<td><%=employee.get("address") %></td>  
   		<td><%=employee.get("phoneNumber") %></td>
   		<td><%=employee.get("birthDate") %></td>
   		<td><%=employee.get("joiningDate") %></td>
   		<td><%=employee.get("departmentId") %></td>
   		<td><%=employee.get("projectId") %></td>
   		<td><%=employee.get("roleId") %></td>   
  </tr>
  </form>
  <%} %>
</table>

    	
    
    
    </div>
</div>


</body>
</html>