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
		
		String regMsg = "Sorry! This user is already registered!";
		RequestDispatcher rd = request.getRequestDispatcher("registerUser.jsp");
		request.setAttribute("regFailMsg", regMsg);
		try{
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			boolean allowedEmail = SecurityHandler.analyzeCharacters(email, true);
			boolean allowedpsw = SecurityHandler.analyzeCharacters(password, false);
			
			if(!allowedEmail || !allowedpsw){
				
				regMsg = "Oops! Something is not allowed here, try again!";
				request.setAttribute("regFailMsg", regMsg);						
				rd.forward(request, response);
			}
			
			
			String crEmail = SecurityHandler.toHashText(email);
			String crPass = SecurityHandler.toHashText(password);
			
			DatabaseHandler dbHandler;
			boolean alreadyRegistered;
			
			synchronized(this){
				dbHandler = (DatabaseHandler)request.getServletContext().getAttribute("dbHandler");
				alreadyRegistered = dbHandler.verifyClient(crEmail,crPass);
			}
			
			if(alreadyRegistered) {
				//regMsg = "Sorry! This user is already registered!";
				
				//rd = request.getRequestDispatcher("registerUser.jsp");			
				rd.forward(request, response);
			} else {
				
				if(dbHandler.registerClient(crEmail,crPass) == true){
					response.sendRedirect("regResponse.html");
				}else{
					//regMsg = "Sorry! This user is already registered!";
					//request.setAttribute("regFailMsg", regMsg);
					//rd = request.getRequestDispatcher("registerUser.jsp");			
					rd.forward(request, response);
				}				
				
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.html");
//			regMsg = "Sorry! This user is already registered!";
//			request.setAttribute("regFailMsg", regMsg);
//			rd = request.getRequestDispatcher("registerUser.jsp");			
//			rd.forward(request, response);
		}
		
	}
	
	

}
