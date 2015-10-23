package com.startception.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
			
			boolean allowedEmail = SecurityHandler.analyzeCharacters(email, true);
			boolean allowedpsw = SecurityHandler.analyzeCharacters(password, false);
			
			if(!allowedEmail || !allowedpsw){			
				loginErrorHandling(rd,request,response);
			}		
			
			String encrEmail = SecurityHandler.toHashText(email);
			String encrPwd = SecurityHandler.toHashText(password);
			boolean accountInDB;
			
			synchronized(this){
				DatabaseHandler dbHandler = (DatabaseHandler)request.getServletContext().getAttribute("dbHandler");
				accountInDB = dbHandler.verifyClient(encrEmail, encrPwd);
			}
			
			
			if(accountInDB) {
				
				HttpSession session = request.getSession();
	            session.setAttribute("authorized", new Boolean(true));
	            //setting session to expire in 30 mins
	            session.setMaxInactiveInterval(30*60);
	           
	            Cookie userName = new Cookie("email", email);
	            userName.setMaxAge(30*60);
	            response.addCookie(userName);
	            //response.sendRedirect("welcomeUser.jsp");
	            request.setAttribute("email", email);
	            rd = request.getRequestDispatcher("welcomeUser.jsp");
	            rd.forward(request, response);

			} else {
				loginErrorHandling(rd,request,response);
			}

		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.html");
//			String errorMsg = "Oops! Something failed! try again later!";
//			request.setAttribute("errorMsg", errorMsg);
//			rd = request.getRequestDispatcher("index.jsp");
//			rd.forward(request, response);
		}
				
	}
		
	private void loginErrorHandling(RequestDispatcher rd, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String loginErrorMsg = "Login failed!";
		request.setAttribute("loginErrorMsg", loginErrorMsg);
		rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			boolean authorized = (Boolean) session.getAttribute("authorized");
			if (authorized)
				authorized = false;
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
