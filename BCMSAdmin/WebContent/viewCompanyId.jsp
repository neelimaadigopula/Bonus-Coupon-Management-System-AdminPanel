<%@include file="adHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<center>
<h3 style = "align : center" >Select company </h3>
<form action="./BCMSController" method="post">
<input type="hidden" name="action" value="viewCustomerInfo2" >

<select name = "comId" onchange = "this.form.submit()">
<option>Select</option>
<c:forEach  items = "${companyList}" var = "company"> 
   		<option value = "${company.id}" >
   			${company.name}
   		</option>
   		
   
</c:forEach>
</select>
</form>
</center>
<%@include file="adFooter.jsp"%>