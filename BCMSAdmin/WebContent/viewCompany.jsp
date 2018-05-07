<%@include file="adHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<center>
<h2>COMPANY_INFO</h2><br></br>
<table border = "1">
<tbody>
<tr><th>ID</th><th>NAME</th><th>ESTABLISHED_ON</th><th>REGISTERED_ON</th><th>EMAIL_ID</th><th>ADDRESS</th></tr>
<c:forEach  items = "${companyList}" var = "company">  
   <tr><td>${company.id}</td><td>${company.name}</td><td>${company.doe}</td><td>${company.dor}</td><td>${company.email}</td>
   <td>${company.surveyNo},${company.landmark},${company.location},${company.city},${company.state},${company.country},${company.pincode}</td></tr> 
   
</c:forEach>
</tbody>  
</table>
</center>
<%@include file="adFooter.jsp"%>