package com.wise.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wise.project.dto.Company;
import com.wise.project.dto.Coupon;
import com.wise.project.dto.CouponReq;
import com.wise.project.dto.Customer;
import com.wise.project.dto.Denomination;
import com.wise.project.dto.Employee;
import com.wise.project.dto.Order;
import com.wise.project.dto.OrderLine;
import com.wise.project.dto.Product;
//import com.svecw.util.DBConnection;
import com.wise.project.util.DBUtility;
import com.wise.project.dao.CompanyDAO;
import com.wise.project.dao.CouponDAO;
import com.wise.project.dao.CouponReqDAO;
import com.wise.project.dao.CustomerDAO;
import com.wise.project.dao.DenominationDAO;
import com.wise.project.dao.EmployeeDAO;
import com.wise.project.dao.OrderDAO;
import com.wise.project.dao.OrderLineDAO;
import com.wise.project.dao.ProductDAO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Servlet implementation class BCMSController
 */
@WebServlet("/BCMSController")
public class BCMSController extends HttpServlet {
	       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BCMSController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		
		if (action.equals("company")) {
			response.sendRedirect("companyLogin.jsp");
			return;
		}
		
		

		if(action.equals("index")) {
			response.sendRedirect("index.jsp");
			return;
		}
		if(action.equals("staffLogin")) {
			response.sendRedirect("staffLogin.jsp");
			return;
		}
		if(action.equals("staffRegister")) {
			response.sendRedirect("staffRegister.jsp");
			return;
		}
		
		if(action.equals("viewCompany")) {
			getCompanyInfo(request).forward(request, response);
			
		}
		if(action.equals("adViewStaff")) {
			getStaffInfo(request).forward(request, response);
			
		}
		if(action.equals("viewCustomer")) {
			getCompanyId(request).forward(request, response);
			
		}
		if(action.equals("viewCustomerInfo")) {
			getCustomerInfo(request).forward(request, response);
			
		}
		if(action.equals("viewDenomination")) {
			viewDenomination(request).forward(request, response);
		}
		if(action.equals("viewCoupon")) {
			viewCoupon(request).forward(request, response);
		}
				
		if(action.equals("getCoupons")){
			getCoupon(request).forward(request, response);
		}
		if(action.equals("companyLogin")) {
			response.sendRedirect("companyLogin.jsp");
			return;
		}
		if (action.equals("company")) {
			response.sendRedirect("companyLogin.jsp");
			return;
		}
	}			
	
	private RequestDispatcher getCoupon(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		CouponDAO couponDAO = new CouponDAO();
		int dId = Integer.parseInt( request.getParameter("denomId"));
		DenominationDAO denominationDAO = new DenominationDAO();
        List<Denomination> list = denominationDAO.get();
		HttpSession session = request.getSession(false);
		session.setAttribute("denomination",list);
		List<Coupon> list1 = couponDAO.getCoupons(dId);
		
		if(list1.size() != 0) {
            request.setAttribute("couponsList", list1);
        }
     dispatcher = request.getRequestDispatcher("viewDenomination.jsp");
     return dispatcher;
	}

	private RequestDispatcher viewCoupon(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		CouponDAO couponDAO = new CouponDAO();
        List<Coupon> list = couponDAO.get();      
         if(list.size() != 0) {
                request.setAttribute("couponList", list);
            }
         dispatcher = request.getRequestDispatcher("viewCoupon.jsp");
         return dispatcher;
	}

	private RequestDispatcher viewDenomination(HttpServletRequest request) {
        // TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
        DenominationDAO denominationDAO = new DenominationDAO();
        List<Denomination> list = denominationDAO.get();  
         if(list.size() != 0) {
                request.setAttribute("denomination", list);
            }
         dispatcher = request.getRequestDispatcher("viewDenomination.jsp");
         return dispatcher;
    }
	
	private RequestDispatcher getCompanyId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		CompanyDAO companyDAO = new CompanyDAO();
		RequestDispatcher dispatcher = null;
		List<Company> list = companyDAO.get();
		if (list.size() != 0){
			request.setAttribute("companyList", list);
		}
		dispatcher = request.getRequestDispatcher("viewCompanyId.jsp");
		return dispatcher;
		
	}

	private RequestDispatcher getCustomerInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		CustomerDAO customerDAO = new CustomerDAO();
		RequestDispatcher dispatcher = null;
		List<Customer> list = customerDAO.get();
		if (list.size() != 0){
			request.setAttribute("customerList", list);
		}
		dispatcher = request.getRequestDispatcher("viewCustomer.jsp");
		return dispatcher;
			
	}

	private RequestDispatcher getStaffInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		EmployeeDAO employeeDAO = new EmployeeDAO();
		RequestDispatcher dispatcher = null;
		List<Employee> list = employeeDAO.get();
		
		if (list.size() != 0){
			request.setAttribute("staffList", list);
		}
		dispatcher = request.getRequestDispatcher("adViewStaff.jsp");
		return dispatcher;
	}
	
	private RequestDispatcher getCompanyInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		CompanyDAO companyDAO = new CompanyDAO();
		RequestDispatcher dispatcher = null;
		List<Company> list = companyDAO.get();
		if (list.size() != 0){
			request.setAttribute("companyList", list);
		}
		dispatcher = request.getRequestDispatcher("viewCompany.jsp");
		return dispatcher;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String action = request.getParameter("action");
		if(action.equals("staffLogin")) {
			loginStaff(request).forward(request, response);
			
		}
		if(action.equals("staffRegister"))	{
			registerStaff(request).forward(request,response);
			return;
		}
		if(action.equals("viewCustomerInfo2"))	{
			getCustomers(request).forward(request,response);
			return;
		}
		
	}
		
			
		private RequestDispatcher getCustomers(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int companyId = Integer.parseInt(request.getParameter("comId"));
		CustomerDAO customerDAO = new CustomerDAO();
		RequestDispatcher dispatcher = null;
		List<Customer> list = customerDAO.getCustomer(companyId);
		if(list.size()!= 0) {
			request.setAttribute("customer",list);
		}
		dispatcher = request.getRequestDispatcher("viewCustomer.jsp");
		return dispatcher;
		
	}
		private RequestDispatcher viewCompany(HttpServletRequest request) {
		// TODO Auto-generated method stub
		CompanyDAO companyDAO = new CompanyDAO();
		RequestDispatcher dispatcher = null;
		List<Company> list = companyDAO.get();
		//Company company = new Company();
		//HttpSession session=request.getSession(false);
		 //company=(Company)session.getAttribute("cuser");
		if(list.size()!= 0) {
			request.setAttribute("companyList",list);
		}
		dispatcher = request.getRequestDispatcher("viewCompany.jsp");
		return dispatcher;
	}
	

	
	private RequestDispatcher loginStaff(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		Employee employee = new Employee();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		//Object ob = request.getSession().getAttribute("cuser");
		//Employee employee2 = (Employee)ob;
		//System.out.println(employee2.getName());
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("pswd");
		employee = employeeDAO.get(id);
		if(employee == null) {
			request.setAttribute("loginStatus", "Login Id doesnot exists");
			dispatcher = request.getRequestDispatcher("staffLogin.jsp");
		}else {
		if(password.equals(employee.getPassword())) {
			if(employee.getRole().equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("cuser", employee);
				String employeeName = employee.getName();
				//System.out.println(employeeName);
				request.setAttribute("name", employeeName);
				dispatcher = request.getRequestDispatcher("adminPanel.jsp");
			}
			else if(employee.getRole().equals("manager")) {
				HttpSession session = request.getSession();
				session.setAttribute("cuser", employee);
				dispatcher = request.getRequestDispatcher("manager.jsp");
				String employeeName = employee.getName();
				request.setAttribute("name", employeeName);
				
			}
		}
		else {
			request.setAttribute("loginStatus", "Password is not matched");
			dispatcher = request.getRequestDispatcher("staffLogin.jsp");
		}
		}
		return dispatcher;
	}

	private RequestDispatcher registerStaff(HttpServletRequest request) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// TODO Auto-generated method stub
		String id = request.getParameter("EmployeeId");
		String name = request.getParameter("EmployeeName");
		String role = request.getParameter("role");
		String doj = request.getParameter("DateOfJoining");
		String phoneNo = request.getParameter("PhoneNo");
		String password = request.getParameter("Password");
		String emailId = request.getParameter("EmailId");
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(id));
		employee.setName(name);
		employee.setRole(role);
		employee.setDoj(DBUtility.stringToUtilDate(doj));
		employee.setEmail(emailId);
		employee.setPhoneNo(phoneNo);
		employee.setPassword(password);
		int status = employeeDAO.add(employee);
		if(status > 0 ) {
			request.setAttribute("registrationStatus", "Registration Successful");
		}
		else {
			request.setAttribute("registrationStatus", "Registration Failed");
		}
		return request.getRequestDispatcher("staffRegister.jsp");
	}
}	

