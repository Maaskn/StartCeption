package com.startception.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.startception.model.SecurityHandler;
import com.startception.model.DatabaseHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			controlInputs(request,response,rd,email,password);
			
			String encrEmail = SecurityHandler.toHashText(email);
			String encrPwd = SecurityHandler.toHashText(password);
			boolean accountInDB;
			
			synchronized(this){
				DatabaseHandler dbHandler = (DatabaseHandler)request.getServletContext().getAttribute("dbHandler");
				accountInDB = dbHandler.verifyClient(encrEmail, encrPwd);
			}
			
			accountHandling(accountInDB, email, request, response, rd);
		

		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
				
	}
	/**
	 * This method verifies if the user is registered in the database, if not then the user is not allowed
	 * to come in to the welcome page.
	 * @param accountInDB the default boolean that verifies if the user is registered in the database
	 * @param email the email of the user
	 * @param request @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param response @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param rd @see loginErrorHandling(RequestDispatcher rd, HttpServletRequest request, HttpServletResponse response) 
	 * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * */
	private void accountHandling(boolean accountInDB,String email,HttpServletRequest request, HttpServletResponse response,
			RequestDispatcher rd) throws ServletException, IOException{
		if(accountInDB) {
			
			HttpSession session = request.getSession();
            session.setAttribute("authorized", new Boolean(true));
            
            //setting session to expire in 30 mins
            session.setMaxInactiveInterval(30*60);
            request.setAttribute("email", email);
            rd = request.getRequestDispatcher("user/welcomeUser.jsp");
            rd.forward(request, response);

		} else {
			loginErrorHandling(rd,request,response);
		}
	}
	
	/**
	 * This method verifies if the requested parameters of the site have allowed access to come through the application
	 * @param request @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param response @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param rd @see loginErrorHandling(RequestDispatcher rd, HttpServletRequest request, HttpServletResponse response) 	
	 * @param email the email to be verified
	 * @param password the password to be verified
	 * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * */	
	private void controlInputs(HttpServletRequest request, HttpServletResponse response,
			RequestDispatcher rd,String email, String password) throws ServletException, IOException{
		boolean allowedEmail = SecurityHandler.analyzeCharacters(email, true);
		boolean allowedpsw = SecurityHandler.analyzeCharacters(password, false);
		
		if(!allowedEmail || !allowedpsw){			
			loginErrorHandling(rd,request,response);
		}
	}
	
	/**
	 * This method handles the login errors
	 * @param request @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param response @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param rd The RequestDispatcher than takes hand om the response of the login error
	 * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * */		
	private void loginErrorHandling(RequestDispatcher rd, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String loginErrorMsg = "Login failed!";
		request.setAttribute("loginErrorMsg", loginErrorMsg);
		rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	/******************doGet() happens when the user logs out**********************/

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			boolean authorized = (Boolean) session.getAttribute("authorized");
			
			if (authorized)	authorized = false;
			session.invalidate();
			
			String logOutMsg = "Welcome back!";
			request.setAttribute("logOutMsg", logOutMsg);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof NullPointerException){
				response.sendRedirect("index.jsp");
			}
		}

	}
	
	

}
