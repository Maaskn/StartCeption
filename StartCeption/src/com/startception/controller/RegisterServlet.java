package com.startception.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.startception.model.SecurityHandler;
import com.startception.model.DatabaseHandler;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String responseH1 = "";
		String regMsg;
		RequestDispatcher rd;
		try{
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			boolean allowedEmail = SecurityHandler.analyzeCharacters(email, true);
			boolean allowedpsw = SecurityHandler.analyzeCharacters(password, false);
			
			if(!allowedEmail || !allowedpsw){
				System.out.println("not email or psw allowed to register");
				regMsg = "Sorry! This user is already registered!";
				request.setAttribute("regFailMsg", regMsg);
				rd = request.getRequestDispatcher("registerUser.jsp");			
				rd.forward(request, response);
			}
			
			
			String crEmail = SecurityHandler.toHashText(email);
			String crPass = SecurityHandler.toHashText(password);
			
//			Någon whitelist här
//			email = whiteliststuff
//			password = whiteliststuff
			
			
			DatabaseHandler dbHandler;
			boolean alreadyRegistered;
			
			synchronized(this){
				dbHandler = (DatabaseHandler)request.getServletContext().getAttribute("dbHandler");
				alreadyRegistered = dbHandler.verifyClient(crEmail,crPass);
			}
			
			
			
			if(alreadyRegistered) {
				regMsg = "Sorry! This user is already registered!";
				request.setAttribute("regFailMsg", regMsg);
				rd = request.getRequestDispatcher("registerUser.jsp");			
				rd.forward(request, response);
			} else {
				dbHandler.registerClient(crEmail,crPass);
				responseH1 = "The registration was successful!";
				regMsg = "Log in <a href=\"index.jsp\">here</a> now!";
				request.setAttribute("responseH1", responseH1);
				request.setAttribute("titleRes", "Welcome!");
				request.setAttribute("regMsg", regMsg);
				rd = request.getRequestDispatcher("regResponse.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			regMsg = "Sorry! This user is already registered!";
			request.setAttribute("regFailMsg", regMsg);
			rd = request.getRequestDispatcher("registerUser.jsp");			
			rd.forward(request, response);
		}
		
	}
	
	

}
