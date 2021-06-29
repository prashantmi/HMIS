<!DOCTYPE html>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<meta charset=utf-8></head>

<!--  
 
 				Developer : PAWAN KUMAR B N
 			    Created On: 19-09-2011
 				Module : Billing
 				Process: BILL SETUP Master
 				JSP URL : billing/masters/billing_multirow_ipdcompulsorycharges_BillSetup_mst.jsp

-->	






<jsp:useBean id="util" class="billing.masters.controller.fb.BillSetupMstFB"
	scope="request"></jsp:useBean>
<%
		if (!(request.getAttribute("billsetupbean") == null))
		util = (billing.masters.controller.fb.BillSetupMstFB) request
		.getAttribute("billsetupbean");
%>

<form name="multirow">

<div id="rowAdded2" style="display: none">
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td width="15%" class="multiControl"><select name="secWardName"
			id="secWardName#delIndex#" >
			<%=util.getIpdComplChrgWardValues()%>
		</select></td>
		<td width="30%" class="multiControl"><select
			name="secGroupName" class="comboMax" onchange="myFunc('4' , this,'#delIndex#' )"
			id="secGroupName#delIndex#">
			<%=util.getIpdComplChrgSecGroupValues()%>
		</select></td>
		<td width="50%" class="multiControl"><div id="secTariff#delIndex#"><select name="secTariffName" class="comboBig"
			id="secTariffName#delIndex#">
			<%=util.getIpdComplChrgSecTariffValuesI()%>
		</select></div></td>
		<td width="5%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','2','0');"></td>
	</tr>
</table>
</div>


<input type="hidden" name="rowIndex2" value="0"> 
<input 	type="hidden" name="rowLength2" value="0">

<div id="rowAdded3" style="display: none">
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td width="15%" class="multiControl"><select name="thirdWardName"
			id="thirdWardName#delIndex#">
			<%=util.getThirdWardTypeValue()%>
		</select></td>
		<td width="30%" class="multiControl"><select
			name="thirdGroupName" class="comboMax" onchange="myFunc('2' , this,'#delIndex#' )"
			id="thirdGroupName#delIndex#">
			<%=util.getIpdComplChrgThirdGroupValues()%>
		</select></td>
		<td width="50%" class="multiControl"><div id="thirdTariff#delIndex#"><select name="thirdTariffName" class="comboBig"
			id="thirdTariffName#delIndex#">
				<%=util.getIpdComplChrgThirdTariffValues()%>
		</select></div></td>
		<td width="5%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','3','0');"></td>
	</tr>
</table>
</div>

	<input type="hidden" name="rowIndex3" value="0"> <input
	type="hidden" name="rowLength3" value="0">
	</form>
	