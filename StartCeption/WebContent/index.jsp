<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in here!</title>
</head>
<body>
	<fieldset>
		<legend>Login</legend>
		<form action="LoginServlet" method="post">
		<table>		
			<tr><td><input type="email" name="email" placeholder="Email Adress" required /></td></tr> 
			<tr>
				<td><input type="password" name="password" placeholder="Password" id="passLog" onchange="verifyPassword()" required /></td>
				<td><div id="errorMsg"></div></td>
			</tr>
			<tr><td><input type="submit"  value="Log in" id="logButton" disabled/></td></tr>
			<tr><td>New member? register <a href="registerUser.html">here!</a></td></tr>
		</table>
		</form>
	</fieldset>
	<div><p></p></div>
	<script type="text/javaScript" src="scripts/login.js"></script>
</body>
</html>