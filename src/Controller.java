import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flp.ems.service.EmployeeServiceImpl;
@WebServlet("/controller")
public class Controller extends HttpServlet{

	private String action;
	private String ACTION_CREATE = "create";
	private String ACTION_MODIFY = "modify";
	private String ACTION_SEARCH = "search";
	private String ACTION_SEEALL = "seeAll";
	private String ACTION_GETALL = "getAll";
	private String ACTION_REMOVE = "remove";
	private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		action = request.getParameter("action");
		if(action.equalsIgnoreCase(ACTION_CREATE)){
			
			HashMap<String, String> create = new HashMap<String,String>();
			create.put("name", request.getParameter("name"));
			create.put("address", request.getParameter("address"));
			create.put("phoneNumber", request.getParameter("phoneNumber"));
			create.put("birthDate", request.getParameter("birthDate"));
			create.put("joiningDate", request.getParameter("joiningDate"));
			create.put("departmentId", request.getParameter("dept"));
			create.put("projectId",request.getParameter("project"));
			create.put("roleId", request.getParameter("role"));
			System.out.println(request.getParameter("birthDate"));
			try {
				employeeService.addEmployee(create);
				response.sendRedirect("controller?action=getAll");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(action.equalsIgnoreCase(ACTION_MODIFY)){
			HashMap<String, String> create = new HashMap<String,String>();
			create.put("kinId", request.getParameter("Save"));
			create.put("name", request.getParameter("name"));
			create.put("address", request.getParameter("address"));
			create.put("phoneNumber", request.getParameter("phoneNumber"));
			create.put("birthDate", request.getParameter("birthDate"));
			create.put("joiningDate", request.getParameter("joiningDate"));
			create.put("departmentId", request.getParameter("department"));
			create.put("projectId",request.getParameter("project"));
			create.put("roleId", request.getParameter("role"));
			System.out.println(request.getParameter("birthDate"));
			
			try {
				employeeService.modifyEmployee(create);
				response.sendRedirect("controller?action=getAll");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		else if(action.equalsIgnoreCase(ACTION_SEEALL)){
		
			try {
				ArrayList<HashMap<String, String>> employees = employeeService.getAllEmployee();
				if(employees == null || employees.size() == 0){
					
				}
				request.setAttribute("employees", employees);
				HttpSession session = request.getSession();
				session.setAttribute("employees", employees);
				//RequestDispatcher dispatcher = request.getRequestDispatcher();
				//dispatcher.forward(request, response);
				response.sendRedirect(request.getParameter("page"));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		else if(action.equalsIgnoreCase(ACTION_GETALL)){
			try {
				ArrayList<HashMap<String, String>> employees = employeeService.getAllEmployee();
				request.setAttribute("employees", employees);
				HttpSession session = request.getSession();
				session.setAttribute("employees", employees);
				//RequestDispatcher dispatcher = request.getRequestDispatcher("modifyEmployee.jsp");
				//dispatcher.forward(request, response);
				response.sendRedirect("getAll.jsp");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		else if(action.equalsIgnoreCase(ACTION_SEARCH)){
			HashMap<String, String> employee = new HashMap<String,String>();
			ArrayList	<HashMap<String, String>> employees = new ArrayList<HashMap<String, String>>();
			String criteria = request.getParameter("criteria");
			String value=request.getParameter("value");
			switch (criteria) {
			case "name":
				employee.put("name", value);
				break;
			case "kinId":
				employee.put("kinId", value);
				break;
				
			case "emailId":
				employee.put("emailId", value);
				break;
			default:
				break;
			}
			try {
				employees = employeeService.searchEmployee(employee);
				HttpSession session = request.getSession();
				session.setAttribute("employees", employees);
				response.sendRedirect("getAll.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equalsIgnoreCase("searchModify")){
			HashMap<String, String> employee = new HashMap<String,String>();
			ArrayList	<HashMap<String, String>> employees = new ArrayList<HashMap<String, String>>();
			String criteria = request.getParameter("criteria");
			String value=request.getParameter("value");
			switch (criteria) {
			case "name":
				employee.put("name", value);
				break;
			case "kinId":
				employee.put("kinId", value);
				break;
				
			case "emailId":
				employee.put("emailId", value);
				break;
			default:
				break;
			}
			try {
				employees = employeeService.searchEmployee(employee);
				HttpSession session = request.getSession();
				session.setAttribute("employees", employees);
				response.sendRedirect("modifyEmployee.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equalsIgnoreCase("searchRemove")){
			HashMap<String, String> employee = new HashMap<String,String>();
			ArrayList	<HashMap<String, String>> employees = new ArrayList<HashMap<String, String>>();
			String criteria = request.getParameter("criteria");
			String value=request.getParameter("value");
			switch (criteria) {
			case "name":
				employee.put("name", value);
				break;
			case "kinId":
				employee.put("kinId", value);
				break;
				
			case "emailId":
				employee.put("emailId", value);
				break;
			default:
				break;
			}
			try {
				employees = employeeService.searchEmployee(employee);
				HttpSession session = request.getSession();
				session.setAttribute("employees", employees);
				response.sendRedirect("removeEmployee.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equalsIgnoreCase(ACTION_REMOVE)){
			HashMap<String, String> create = new HashMap<String,String>();
			create.put("kinId", request.getParameter("Delete"));
			create.put("name", request.getParameter("name"));
			/*create.put("address", request.getParameter("address"));
			create.put("phoneNumber", request.getParameter("phoneNumber"));
			create.put("birthDate", request.getParameter("birthDate"));
			create.put("joiningDate", request.getParameter("joiningDate"));
			create.put("departmentId", request.getParameter("department"));
			create.put("projectId",request.getParameter("project"));
			create.put("roleId", request.getParameter("role"));
			System.out.println(request.getParameter("birthDate"));*/
			
			try {
				employeeService.removeEmployee(create);
				response.sendRedirect("controller?action=getAll");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		
		
	}
	
}
