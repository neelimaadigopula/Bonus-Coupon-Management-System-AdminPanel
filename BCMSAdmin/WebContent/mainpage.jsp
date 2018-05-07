<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
h1{text-align:center;color:white}
body{
background-image:url("./images/welcome.jpg");
background-size:100%;
font-family:verdana;
font-size:15px;
}
 ul{
  list-style-type:none;
  margin:0;
  padding:0;
  overflow:auto;
  background-color:brown;
  }
  li{
   float:left;
  }
  li a{
  	display:block;
  	color:white;
  	text-align:center;
  	padding:14px 16px;
  	text-decoration:none;
  }
  li a:hover:not(.active){
    background-color:#333;
  }
  .active{
    background-color:brown;
  }
  

</style>

<body>
<h1>BONUS COUPON MANAGEMENT SYSTEM</h1>
<ul>
    <li> <a class = "active" href="#home"> Home</a></li>
    <li> <a href="#"> Company</a></li>
    <li> <a href="#"> Customer</a></li>
    <li> <a href="./BCMSController?action=staffLogin"> Staff</a></li>
    <li> <a href="#contact"> Contact Us</a></li>    
  </ul>
</body>
</html>