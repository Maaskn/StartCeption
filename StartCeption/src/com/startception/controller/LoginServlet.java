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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		Någon whitelist här
//		email = whiteliststuff
//		password = whiteliststuff
		
		DatabaseHandler dbHandler = new DatabaseHandler();
		Crypto crypto = new Crypto();
		
		RequestDispatcher rd;
		if(dbHandler.verifyClient(crypto.hashText(email), crypto.hashText(password))) {
			rd = request.getRequestDispatcher("welcomeUser.jsp");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
		}
		
	}

}
