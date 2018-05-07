<%@include file="adHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<center>
<h2 >STAFF_INFO</h2><br></br>
<table border = "1">
<tbody>
<tr><th>ID</th><th>NAME</th><th>ROLE</th><th>DATE OF JOINING</th><th>EMAIL</th><th>PHONE_NO</th></tr>
<c:forEach  items = "${staffList}" var = "staff">  
   <tr><td>${staff.id}</td><td>${staff.name}</td><td>${staff.role}</td><td>${staff.doj}</td><td>${staff.email}</td>
   <td>${staff.phoneNo}</td></tr> 
   
</c:forEach>
</tbody>  
</table>
</center>
<%@include file="adFooter.jsp"%>