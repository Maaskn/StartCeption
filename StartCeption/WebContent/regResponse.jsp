<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>${titleRes}</title>
</head>
<body>
	<h1>${responseH1}</h1>
	<table align="center">
	<tr>
		<td>
			<div><img src="http://i86.photobucket.com/albums/k88/3820112/smile.jpg" alt="homeless man smiling" style="width:600px;height:600px;"></div>
		</td>		
	</tr>
	<tr>
		<td>
			<div style ="background-image:url(http://blog.spoongraphics.co.uk/wp-content/uploads/2012/05/51.jpg); 
			background-size: cover; 
			background-repeat: no-repeat;">
			<table>								
				<tr><td>
					<p style="text-align:center;">${regMsg}</p>
				</td></tr>
			</table>
			</div>
		</td>		
	</tr>
	</table>
	
	
</body>
</html>