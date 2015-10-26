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
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 * @author Erik Perez and Erik Sandstrom
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String regMsg = "Sorry! This user is already registered!";
		RequestDispatcher rd = request.getRequestDispatcher("registerUser.jsp");
		request.setAttribute("regFailMsg", regMsg);
		try {

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			controlInputs(request, response, rd, email, password, regMsg);

			String crEmail = SecurityHandler.toHashText(email);
			String crPass = SecurityHandler.toHashText(password);
			this.controlRegistration(request, response, rd, crEmail, crPass);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}

	}
	
	/**
	 * This method verifies if the requested parameters of the site have allowed access to register
	 * @param request @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param response @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param rd The RequestDispatcher than takes hand of the response of the registration error	
	 * @param email the email to be verified
	 * @param password the password to be verified
	 * @param regMsg the message to be displayed if something is not allowed
	 * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author Erik Perez and Erik Sandstrom
	 * */	
	private void controlInputs(HttpServletRequest request, HttpServletResponse response, RequestDispatcher rd,
			String email, String password, String regMsg) throws ServletException, IOException {
		boolean allowedEmail = SecurityHandler.analyzeCharacters(email, true);
		boolean allowedpsw = SecurityHandler.analyzeCharacters(password, false);

		if (!allowedEmail || !allowedpsw) {
			regMsg = "Oops! Something is not allowed here, try again!";
			request.setAttribute("regFailMsg", regMsg);
			rd.forward(request, response);
		}

	}
	
	/**
	 * This method verifies if the values to be saved in the database already exists there
	 * @param request @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param response @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param rd The RequestDispatcher than takes hand of the response of the registration error 	
	 * @param crEmail the encrypted email value to be verified
	 * @param crPassword the encrypted password value to be verified
	 * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author Erik Perez and Erik Sandstrom
	 * */
	private void controlRegistration(HttpServletRequest request, HttpServletResponse response, RequestDispatcher rd,
			String crEmail, String crPass) throws ServletException, IOException {
		DatabaseHandler dbHandler;
		boolean alreadyRegistered;

		synchronized (this) {
			dbHandler = (DatabaseHandler) request.getServletContext().getAttribute("dbHandler");
			alreadyRegistered = dbHandler.verifyClient(crEmail, crPass);
		}

		if (alreadyRegistered) {
			rd.forward(request, response);
		} else {

			if (dbHandler.registerClient(crEmail, crPass) == true) {
				response.sendRedirect("regResponse.html");
			} else {
				rd.forward(request, response);
			}

		}
	}

}
