<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
	<head>
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		<title>
			Master Report
		</title>
	</head>
	
	<body onLoad = 'checkBrowser();'>
		<form name = 'form1' METHOD='POST' action = 'cnt_NutritionAvail_mst.jsp'>
			<table width='100%' border='0' cellspacing='1' cellpadding='0'><tr><td><div  id = 'id1' align='right'><a style=cursor:hand><img src='../../hisglobal/images/Cancel.gif' border=0 onClick='submitpage("DEFAULT");'></a>

</div></td></tr></table>
<table width='100%' border='0' cellspacing='1' cellpadding='0'>
<thead>
<tr>
<td align = 'center' colspan = '4'><strong>
<font size='3' face='Verdana, Arial, Helvetica, sans-serif'>
MGIMS,Sevagram
</font></strong>
</td>
</tr>
<tr>
<td align = 'center' colspan = '4'><strong>
<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>
Report for Nutrition Availibility Master
</font></strong>
</td>
</tr>
<tr>
<td align = 'right' colspan = '4'>
<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>
<strong>Date of Report : </strong>
Jun 03,2006</font></td>
</tr><tr>
<td colspan = '4'><hr></td>
<tr>
<td width = '5%' align = 'left'>
<font size='3'>
<strong>S.No</strong>
</font></td>
<td width='35%%' align = 'left'>
<font size='3'>
<strong>Item Name</strong>
</font>
</td><td width='40%%' align = 'left'>
<font size='3'>
<strong>Nutrition Name</strong>
</font>
</td><td width='25%%' align = 'left'>
<font size='3'>
<strong>Nutrition Qty(%)</strong>
</font>
</td></tr>
<tr>
<TD COLSPAN = '4'><HR></TD>
</tr>
</thead>
<tbody>
<tr>
<td align = 'left' width = '5%'>1.</td>
<td align = 'left' width = '35%'>Milk</td>
<td align = 'left' width = '40%'>Calcium</td>
<td align = 'left' width = '25%'>30</td>
</tr>
<tr>
<td align = 'left' width = '5%'>2.</td>
<td align = 'left' width = '35%'>Milk</td>
<td align = 'left' width = '40%'>Iron</td>
<td align = 'left' width = '25%'>5</td>
</tr>
<tr>
<td align = 'left' width = '5%'>3.</td>
<td align = 'left' width = '35%'>Urad Dal</td>
<td align = 'left' width = '40%'>Protein</td>
<td align = 'left' width = '25%'>40</td>
</tr>
<tr>
<td align = 'left' width = '5%'>4.</td>
<td align = 'left' width = '35%'>Wheat Flour</td>
<td align = 'left' width = '40%'>Fat1</td>
<td align = 'left' width = '25%'>10.2</td>
</tr>
<tr>
<td align = 'left' width = '5%'>5.</td>
<td align = 'left' width = '35%'>Wheat Flour</td>
<td align = 'left' width = '40%'>Iron</td>
<td align = 'left' width = '25%'>0</td>
</tr>
</tbody><tfoot style='DISPLAY:TABLE-FOOTER-GROUP'>
<tr>
<TD COLSPAN = '4'><HR></TD>
</tr>
</tfoot>
</table>
			<input type="hidden" name="hmode">
			<input type="hidden" name="combo1" value = ''>
			<input type="hidden" name="combo2" value = ''>
			<input type="hidden" name="combo3" value = ''>
			<input type="hidden" name="cboSearch" value = '-1'>
			<input type="hidden" name="txtSearch" value = ''>
		</form>
	</body>
	
</html>

