<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${email}</title>
<link rel="stylesheet" type="text/css" href="css/welcome.css">
</head>
<body>
<h1>Feed us to code to another day!</h1>
<%
	//Filter the access
	Boolean authorized = (Boolean)(session.getAttribute("authorized"));
	if(authorized == null){
		response.sendRedirect("../index.jsp");
	}else{
		if(!authorized){response.sendRedirect("../index.jsp");}
	}
%>
       
        <fieldset>
                <legend>Donate now!</legend>
                
			
 			<input onclick="javascript: paymentwindow.open()" type="button" value="donate here">
               
                <legend>Log out here.</legend>
                <form action="AccountServlet" method="get">
                <table>        
                        <tr><td><input type="submit" value="Log out" /></td></tr>
                </table>
                </form>
        </fieldset>
       
        <h2>Did you know that poor programmers are the leading cause to the often fatal sad pug syndrom! Donate today!</h2>
        <table align="center">
        <tr>
                <td>
                        <div><img src="http://i.pbase.com/o4/66/474066/1/65547033.9Z6szGJU.Img20060818_0033CopyJPEGof.jpg" alt="Sad Pug 1" style="width:304px;height:228px;"></div>
                </td>
                <td>
                        <div><img src="http://rack.2.mshcdn.com/media/ZgkyMDE0LzA0LzI4LzU4L3B1Z0JXLjlmZjE0LmpwZwpwCXRodW1iCTEyMDB4NjI3IwplCWpwZw/b7db1284/ddb/pugBW.jpg" alt="Sand Pug 2" style="width:304px;height:228px;"></div>
                </td>
        </tr>
        <tr>
                <td>
                        <div style ="background-image:url(http://blog.spoongraphics.co.uk/wp-content/uploads/2012/05/51.jpg);
                        background-size: cover;
                        background-repeat: no-repeat;">
                        <table>                                                
 
                                <tr><td>
                                        <p>"Long time ago I used to have Windows 95,</p>
                                <p>i still do." </p>
                                <p>Real pug testimony - <i>Sad Pug</i></p>
                                </td></tr>
                        </table>
                        </div>
                </td>
                <td>
                        <div style ="background-image:url(http://blog.spoongraphics.co.uk/wp-content/uploads/2012/05/51.jpg);
                        background-size: cover;
                        background-repeat: no-repeat;">
                        <table>
                                <tr><td><p>"There are only 10 kinds of people in this world:</p>
                                <p> those who know binary and those who don't" </p>
                                <p>Heartbreaking pug -<i>Another equally sad pug</i></p>
                                </td></tr>
                        </table>
                        </div>
                </td>
        </tr>
        </table>
        <script charset="UTF-8" src="https://ssl.ditonlinebetalingssystem.dk/integration/ewindow/paymentwindow.js" type="text/javascript"></script>
         <script type="text/javascript">
     paymentwindow = new PaymentWindow({
         'merchantnumber': "8021018",
         'amount': "10095",
         'currency': "SEK",
         'language': "2",
         'orderid': "gr8SCpj"
     });
 </script>
       
        </body>
</html>
