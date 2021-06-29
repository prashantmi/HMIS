<!DOCTYPE html>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <head>
<meta charset=utf-8></head>
<jsp:useBean id="util" class="billing.masters.controller.fb.BillSetupMstFB"
	scope="request"></jsp:useBean>
<%
		if (!(request.getAttribute("billsetupbean") == null))
		util = (billing.masters.controller.fb.BillSetupMstFB) request
		.getAttribute("billsetupbean");
%>

<form name="multirow">
<div id="rowAdded1" style="display: none">
<table width="100%">
	<tr>
		<td width="32%" class="multiControl"><select name="emgGroupName"
			id="emgGroupName#delIndex#" onChange="myFunc('1',this,'#delIndex#');">
			<%=util.getEmgGroupValues()%>
		</select></td>
		<td width="31%" class="multiControl">
		<div id="emgTariffId#delIndex#" >
		 <select name="emgTariffName" id="emgTariffName#delIndex#">
			<%=util.getEmgTariffValues()%>
		</select> </div>
		</td>
		<td width="31%" class="multiControl"><select name="emgUnit"
			id="emgUnit#delIndex#">
			<option value="1">Daily</option>
			<option value="0">One Time</option>
		</select></td>
		<td width="6%" class="multiControl"><img
			src="../../hisglobal/images/minus.gif"
			onClick="deleteRow('#delIndex#','1','0');"></td>
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>