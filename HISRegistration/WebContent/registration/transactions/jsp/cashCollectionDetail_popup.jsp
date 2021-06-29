<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html>
<head>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">


<script language="JavaScript" src="../../masterutil/js/master.js"></script>

<!-- <script language="Javascript" src="../js/cashcollection_withoutcr_trans.js"></script> -->
</head>
<body>

			<table width='100%' >
				<tr class='HEADER'>
					<th colspan='2' align="center">&nbsp;Cash Collection Detail  
					</th>
				</tr>
			</table>
			<table width='100%' border="0" >
				
				<tr >
				<td  width="25%" align="left"><font size="2"><b>User Name :</b></font></td>
				
				<td  width="25%" align="left" ><font size="5"><b><u><%=request.getAttribute("userName") %></u></b></font></td>
				
					<td  width="25%" align="left" nowrap="nowrap"><font size="2"><b>Total No. of Bill :</b></font></td>
					<td  width="25%" align="left" ><font size="5"><b><u><%=request.getAttribute("totalBill") %></u></b></font></td>
					
				</tr>
			</table>
			<table width='100%' border="0" >
				
				<tr>
					<td  width="50%" align="right"><font size="2"><b>Total Recieved Amount :</b></font></td>
					<td  width="50%" align="left"><font size="5"><b><u><%=request.getAttribute("recievedAmount") %></u></b></font></td>
					
				</tr>
				<tr>
					<td  width="50%" align="right"><font size="2"><b>Total Refund Amount :</b></font></td>
					<td  width="50%" align="left"><font size="5"><b><u><%=request.getAttribute("refundAmount") %></u></b></font></td>
					
				</tr>
				<tr>
					<td  width="50%" align="right"><font size="2"><b>Net Amount :</b></font></td>
					<td  width="50%" align="left"><b><u><font size="5" color="blue"><%=request.getAttribute("netAmount") %></font></u></b></td>

				</tr>
				
			</table>
			<center>
			<table width='100%' border='1' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><img
						style="cursor: hand; cursor: pointer"
						src='/HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1"
						onClick="window.close()"></center>
					</td>
				</tr>
			</table>
			</center>
		
	




</body>
</html>