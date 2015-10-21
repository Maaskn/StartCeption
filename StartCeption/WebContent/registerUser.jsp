<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<h1>Help us here and now!</h1>
	<fieldset>
		<legend>Register here</legend>		
		<form action="RegisterServlet" method="post">
			<table>
				<tr><td><input type="email" name="email" placeholder="Email Adress" required /></td></tr>
				<tr><td><input type="password" name="password" placeholder="Password" id="passReg" oninput="verifyPassword()" required /></td>
				<td><div id="errorMsg"></div></td></tr>
				<tr><td><input type="submit" value="Register now" id="regButton" disabled/></td>				
					<td><b>${regFailMsg}</b></td></tr>
				<tr><td>Already member? log in <a href="index.jsp">here!</a></td></tr>
			</table>
		</form>
	</fieldset>
	<h2>Our actual working tool</h2>
	<table align="center">
	<tr>
		<td>
			<div><img src="http://imagenchistosa.com/wp-content/uploads/2012/09/computadora.jpg" alt="working tool" style="width:400px;height:400px;"></div>
		</td>		
	</tr>
	<tr>
		<td>
			<div style ="background-image:url(http://blog.spoongraphics.co.uk/wp-content/uploads/2012/05/51.jpg); 
			background-size: cover; 
			background-repeat: no-repeat;">
			<table>								
				<tr><td>
					<p>With your help, we will buy only the best</p> 
				<p>tools to work!</p>
				</td></tr>
			</table>
			</div>
		</td>		
	</tr>
	</table>
	
	
	<script type="text/javaScript" src="scripts/registration.js"></script>
</body>
</html>