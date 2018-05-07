<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html>
<head>
<link href="resources/adminPanel.css" rel="stylesheet" type="text/css">	
</head>
<body>
<div id = 'header'>
	<h1>[BCMS]</h1>
	<div class="navbar">
  		<a href="mainpage.jsp">Logout</a>
  		<div class="dropdown">
    		<button class="dropbtn">Denominations 
      		<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
      			<a href="./BCMSController?action=viewDenomination">Denomination</a>
      			<a href="./BCMSController?action=viewCoupon">Coupons</a>
      			
    		</div>
  		</div>
  		
  		<div class="dropdown">
    		<button class="dropbtn">Companies 
      		<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
      			<a href="./BCMSController?action=viewCompany">Companies</a>
      			<a href="./BCMSController?action=viewCustomer">Customers</a>
      			
    		</div>
  		</div>
  		<div class="dropdown">
    		<button class="dropbtn">Staff 
      		<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
      			<a href="./BCMSController?action=staffRegister">add</a>
      			<a href="./BCMSController?action=adViewStaff">view</a>
      			
    		</div>
  		</div>
  		
	</div>	
	<p id = adm>Admin : ${cuser.name }  
	</p> 
	
	
</div>