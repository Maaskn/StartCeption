package com.startception.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.startception.model.Crypto;
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
		Crypto crypto = new Crypto();
		DatabaseHandler dbHandler = new DatabaseHandler();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String crEmail = crypto.hashText(email);
		String crPass = crypto.hashText(password);
		
//		Någon whitelist här
//		email = whiteliststuff
//		password = whiteliststuff
		
		RequestDispatcher rd;
		boolean alreadyRegistered = dbHandler.verifyClient(crEmail,crPass);
		String responseH1 = "";
		String regMsg;
		
		if(alreadyRegistered) {
			responseH1 = "Sorry! This user is already registered";
			regMsg = "Try to register again <a href=\"registerUser.html\">here</a>";
			request.setAttribute("responseH1", responseH1);
			request.setAttribute("titleRes", "Oops!");
			request.setAttribute("regMsg", regMsg);
			rd = request.getRequestDispatcher("regResponse.jsp");			
			rd.forward(request, response);
		} else {
			dbHandler.registerClient(crEmail,crPass);
			responseH1 = "The registration was successful!";
			regMsg = "Log in <a href=\"index.html\">here</a> now!";
			request.setAttribute("responseH1", responseH1);
			request.setAttribute("titleRes", "Welcome!");
			request.setAttribute("regMsg", regMsg);
			rd = request.getRequestDispatcher("regResponse.jsp");
			rd.forward(request, response);
		}
	}
	
	

}
