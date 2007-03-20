<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.google.checkout.example.GoogleOrder" %>
<%@ page import="com.google.checkout.MerchantConstants" %>
<%@ page import="com.google.checkout.example.CheckoutRequestFactory" %>
<%@ page import="com.google.checkout.impl.util.Utils" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
<script language="JavaScript">
  function go(i) {
      s = document.getElementById('act_'+i);
      u = s.options[s.selectedIndex].value;
      window.location = u;
  };
</script>
<h1>Orders</h1>
<form>
<table border="1">
<tr>
<td><b>Last Updated</b></td>
<td><b>Google Order Number</b></td>
<td><b>Buyer Email Address</b></td>
<td><b>Financial Status</b></td>
<td><b>Fulfilment Status</b></td>
<td><b>Order Total</b></td>
<td><b>Action</b></td>

</tr>
<%
  MerchantConstants mc = com.google.checkout.example.CheckoutRequestFactory.getMerchantConstants();

  GoogleOrder[] orders = GoogleOrder.findAll(mc.getMerchantId());
  for (int i = 0; i < orders.length; i++)
  {
	  String orderNumber = orders[i].getOrderNumber();
	  String lastUpdated = Utils.getDateString(orders[i].getLastUpdateTime()).replace('T', ' ');
	  String finStatus = orders[i].getLastFinStatus();
	  String fulStatus = orders[i].getLastFulStatus();
	  String buyer = orders[i].getBuyerEmail();
	  String amount = orders[i].getOrderAmount();
%>
	  <tr>
	  <td><%=lastUpdated%></td>
	  <td><a href="order_detail.jsp?orderNumber=<%=orderNumber%>"><%=orderNumber%></a></td>
	  <td><%=buyer%></td>
	  <td><%=finStatus%></td>
	  <td><%=fulStatus%></td>
	  <td><%=amount%></td>
	  <td>
        <select id="act_<%=i %>">
		  <option value ="chargeorder.jsp?orderNumber=<%=orderNumber%>&amount=<%=amount %>">Charge Order</option>
	  	  <option value ="refundorder.jsp?orderNumber=<%=orderNumber%>&amount=<%=amount %>">Refund Order</option>
	  	  <option value ="cancelorder.jsp?orderNumber=<%=orderNumber%>">Cancel Order</option>
	  	  <option value ="authorizeorder.jsp?orderNumber=<%=orderNumber%>">Authorize Order</option>
	  	  <option value ="processorder.jsp?orderNumber=<%=orderNumber%>">Process Order</option>
	  	  <option value ="addmerchantordernumber.jsp?orderNumber=<%=orderNumber%>">Add Merchant Order Number</option>
	  	  <option value ="deliverorder.jsp?orderNumber=<%=orderNumber%>">Deliver Order</option>
	  	  <option value ="addtrackingdata.jsp?orderNumber=<%=orderNumber%>">Add Tracking Data</option>
	  	  <option value ="sendbuyermessage.jsp?orderNumber=<%=orderNumber%>">Send Buyer Message</option>
	  	  <option value ="archiveorder.jsp?orderNumber=<%=orderNumber%>">Archive Order</option>
	  	  <option value ="unarchiveorder.jsp?orderNumber=<%=orderNumber%>">Unarchive Order</option>
		</select>
		<input type="button" value="Go" onClick="javascript:go(<%=i %>)">
      </td>
	  </tr>
	  <% 	  
  }
%>
</table>
</form>
</body>
</html>