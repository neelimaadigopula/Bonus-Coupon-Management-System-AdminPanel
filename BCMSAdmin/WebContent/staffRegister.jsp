<%@include file="adHeader.jsp"%>
  <center>
    <div class="formBox">
			<form  action="./BCMSController" method="post">
				<h1>Staff Registration Form</h1>
				<input type="text" name="EmployeeId" placeholder="Enter Id">
				<input type="text" placeholder="Employee Name" name="EmployeeName" required>
				 <input type="text" placeholder="Enter Role" name="role" required>
			  	<input type="text" placeholder="Date Of Joining(yyyy/mm/dd)" name="DateOfJoining" required>
			   <input type="text" placeholder="EmailId" name="EmailId" required>
			   <input type="text" placeholder="Phone No" name="PhoneNo" required>
				<input type="password" name="Password" placeholder="Enter Password">
				
				<input type = "hidden" name = "action" value = "staffRegister">
				<br></br>
				<input type="submit" value = "Register" >
  				<input type="reset" value = "Clear">
			</form>
	</div>		
  </center>
<center>
<%
 if(request.getAttribute("registrationStatus")== null){
	 out.println();
 }
 else
	 out.println(request.getAttribute("registrationStatus"));
 %>
 </center>

 </div>
<%@include file="adFooter.jsp"%>