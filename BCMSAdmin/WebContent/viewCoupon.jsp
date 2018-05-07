<%@include file="adHeader.jsp"%>
<center>
<h2>COUPONS</h2>
</center>
<!-- <table border = 1>
<tr><th>ID</th><th>VALIDITY</th><th>DENOMINATION_ID</th></tr>
<c:forEach  items = "${couponList}" var = "coupon">  
   <tr><td>${coupon.id}</td><td>${coupon.validity}</td><td>${coupon.denomination.getId()}</td></tr> 
   
</c:forEach>  
</table> -->
<div style = "height : auto" class="row">
<c:forEach  items = "${couponList}" var = "coupon">
		<div  class = "column">
			<div class="text">
			<p>Coupon_Id : ${coupon.id }</p>
			<p>Validity : ${coupon.validity } Days</p>
			<p>Price : ${coupon.denomination.getAmount() }</p>
			</div>
		</div>
		&nbsp;
</c:forEach>
</div>
<%@include file="adFooter.jsp"%>