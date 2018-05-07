<%@include file="adHeader.jsp"%>
    <center>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
            <h2>DENOMINATIONS</h2><br></br>
            <table border=1>
                <tr>
                    <th>ID</th>
                    <th>PRICE</th>
                </tr>
                <c:forEach items="${denomination}" var="denomination">
                    <tr>
                        <td>${denomination.id}</td>
                        <td>${denomination.amount}</td>
                    </tr>
                </c:forEach>
            </table>
				            
    </center>
    <%@include file="adFooter.jsp"%>