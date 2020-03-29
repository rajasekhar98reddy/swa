<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="cities"></jsp:include>
<jsp:include page="states"></jsp:include>
<jsp:include page="countries"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
	<h3>Registration page</h3>
	<form action="reg" >
		<table border="2">
			<tr><td align="right">Username</td><td><input type="text" name="username"></td></tr>
			<tr><td align="right">Password</td><td><input type="password" name="password"></td></tr>
			<tr><td align="right">Re-type password</td><td><input type="password" name="cpassword"></td></tr>
			<tr><td align="right">Email</td><td><input type="text" name="email"></td></tr>
			<tr><td align="right">City</td><td><select name="city"><option>Select</option><c:forEach items="${cities}" var="city"><option value="${city }">${city}</option></c:forEach></select></td></tr>
			<tr><td align="right">State</td><td><select name="state"><option>Select</option><c:forEach items="${states}" var="state"><option value="${state}">${state}</option></c:forEach></select></td></tr>
			<tr><td align="right">Country</td><td><select name="country"><option>Select</option><c:forEach items="${countries}" var="country"><option value="${country}">${country}</option></c:forEach></select></td></tr>
			<tr><td></td><td align="right"><input type="submit" value="Register"></td></tr>	
		</table>
	</form>
</body>
</html>