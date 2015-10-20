<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register here</title>

</head>
<body>
	<fieldset style="background-color: #E6E6E6;">
		<legend>Register here</legend>		
		<form action="RegisterServlet" method="post">
			<table>
				<tr><td><input type="email" name="email" placeholder="Email Adress" required /></td></tr>
				<tr><td><input type="password" name="password" placeholder="Password" id="passReg" onchange="verifyPassword()" required /></td>
				<td><div id="errorMsg"></div></td></tr>
				<tr><td><input type="submit" value="Register now" id="regButton" disabled/></td></tr>
				<tr><td>Already member? log in <a href="index.jsp">here!</a></td></tr>
			</table>
		</form>
	</fieldset>
	<b>${regFailMsg}</b>
	<script type="text/javaScript" src="scripts/registration.js"></script>
</body>
</html>