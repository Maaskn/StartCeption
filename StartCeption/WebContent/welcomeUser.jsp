<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome User</title>
</head>
<body>
<%
//allow access only if session exists
String user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
  if(cookie.getName().equals("user")) userName = cookie.getValue();
  if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<h1>Welcome</h1>
  
 <input onclick="javascript: paymentwindow.open()" type="button" value="Go to payment">

<a href="paymentPage.jsp">Donate now!</a>
	<form action="AccountServlet" method="get">					
		<input type="submit" value="Log out" />
	</form>

</body>
</html>