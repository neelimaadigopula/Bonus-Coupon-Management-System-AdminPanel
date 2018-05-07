<%@include file="adHeader.jsp"%>

<center>
<h2>CUSTOMER_INFO</h2><br></br>
<table>

<tr><th>ID</th><th>NAME</th><th>SURNAME</th><th>DOB</th><th>GENDER</th><th>EMAIL</th><th>PHONE_NO</th><th>ADDRESS</th></tr>
<c:forEach  items = "${customer}" var = "customer">  
   <tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.surname}</td><td>${customer.dob}</td><td>${customer.gender}</td><td>${customer.email}</td>
   <td>${customer.phone}</td><td>${customer.house},${customer.location},${customer.city},${customer.state},${customer.country},${customer.pincode}</td></tr> 
   
</c:forEach>
  
</table>
</center>
<%@include file="adFooter.jsp"%>