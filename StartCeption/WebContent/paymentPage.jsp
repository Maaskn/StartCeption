<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Donate now, were starving!</title>
</head>
<body>
<%
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
%>
<h1>Donate now!</h1>

<script charset="UTF-8" src="https://ssl.ditonlinebetalingssystem.dk/integration/ewindow/paymentwindow.js" type="text/javascript"></script>
 <script type="text/javascript">
     paymentwindow = new PaymentWindow({
         'merchantnumber': "8021018",
         'amount': "10095",
         'currency': "SEK",
         'language': "2",
         'orderid': "154"
     });
 </script>
 
  <input onclick="javascript: paymentwindow.open()" type="button" value="Go to payment">

	<form action="AccountServlet" method="get">					
		<input type="submit" value="Log out" />
	</form>

</body>
</html>