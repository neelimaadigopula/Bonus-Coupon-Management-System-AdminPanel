<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>LoginForm</title>
<link href="./resources/loginform.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="title"></div><center><h1> Staff Login</h1></center></div>
	<div class="container">
	<div class="left"></div>
	<div class="right">
		<div class="formBox">
			<form  action="./BCMSController" method="post">
			<p> Id</p>
			<input type="text" name="id" placeholder="Enter Id">
			<p> Password</p>
			<input type="password" name="pswd" placeholder="Enter Password">
			<input type = "hidden" name = "action" value = "staffLogin">
			<button type="submit">SignIn</button>
			
			</form>
			
			
		</div>
	
	</div>
	</div>
</body>
</html>