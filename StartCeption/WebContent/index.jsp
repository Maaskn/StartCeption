<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in here!</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<h1>Get us out from poverty!</h1>
	<fieldset>
		<legend>Log in</legend>
		<form action="AccountServlet" method="post">
		<table>		
			<tr><td><input type="email" name="email" placeholder="Email Adress" required /></td></tr> 
			<tr>
				<td><input type="password" name="password" placeholder="Password" id="passLog" oninput="verifyPassword()" required /></td>
				<td><div id="errorMsg"></div></td>
			</tr>
			<tr><td><input type="submit"  value="Log in" id="logButton" disabled/></td>
				<td><b>${loginErrorMsg}${logOutMsg}</b></td></tr>
			<tr><td>New member? register <a href="registerUser.jsp">here!</a></td></tr>
		</table>
		</form>
	</fieldset>
	<h2>Meet the two developers you will help</h2>
	<table align="center">
	<tr>
		<td>
			<div><img src="http://entreparentesis.org/images/fotos_blog/jospini/vagabundoordenador.jpg" alt="Erik Perez" style="width:304px;height:228px;"></div>
		</td>
		<td>
			<div><img src="http://ultimosavances.com/wp-content/uploads/2010/10/2ba72173aastech2.jpg.jpg" alt="Erik Sandstrom" style="width:304px;height:228px;"></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style ="background-image:url(http://blog.spoongraphics.co.uk/wp-content/uploads/2012/05/51.jpg); 
			background-size: cover; 
			background-repeat: no-repeat;">
			<table>								
				<tr><td>
					<p>"Long time ago I used to have a life,</p> 
				<p>until someone told me to create a Facebook</p>
				<p>account." - <i>Erik Perez</i></p>
				</td></tr>
			</table>
			</div>
		</td>
		<td>
			<div style ="background-image:url(http://blog.spoongraphics.co.uk/wp-content/uploads/2012/05/51.jpg); 
			background-size: cover; 
			background-repeat: no-repeat;">
			<table>
				<tr><td><p>"All my life I thought air was free,</p>
				<p> until I bought a bag of chips." </p>
				<p>- <i>Erik Sandstrom</i></p>
				</td></tr>
			</table>
			</div>
		</td>
	</tr>
	</table>
	
	<script type="text/javaScript" src="scripts/login.js"></script>
</body>
</html>