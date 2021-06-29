
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.io.*" %>
<%@ page import ="java.util.*,java.text.*" %> 
<html>
<head>
<meta charset=utf-8><title>Bill Receipt Print Pop Up</title>
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../css/transaction.css" rel="stylesheet" type="text/css" media="print">
<script language="JavaScript" src="../../billing/js/PatWallet_offline_trans.js"></script>
<script type="text/javascript">

</script>
<style type='text/css'>
.SLIPCONTROL {
	
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-style: normal;
}
</style>
</head>
<body onload="printSlip();">
<html:form action="/transactions/PatWalletTransCNT.cnt" method="post">
	<% 
		final ResourceBundle hisProp = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties"); 
		final String header1 =hisProp.getString("PHDM_HEADER1");
		final String header2 =hisProp.getString("PHDM_HEADER2");
		final String header3 =hisProp.getString("PHDM_HEADER3");
		final String header4 =hisProp.getString("PHDM_HEADER4");

	%>
	<table width="100%" style='display:none;'>
		<tr>
			<td colspan="1"><div align="right">
			<img src="/HBIMS/hisglobal/images/nims-tr-logo.gif"/></div></td>
			<td colspan="5"><div align="center">
			<table width="100%">
			<tr>
				<td colspan="6"><div align="left" class='SLIPCONTROL' ><b><%=header1 %></b></div></td>
			</tr>
			<tr>
				<td class='SLIPCONTROL' colspan="6"><div align="left"><b><%=header2 %></b></div></td>
			</tr>
			<tr>
				<td class='SLIPCONTROL' colspan="6"><div align="left"><b><%=header3 %></b></div></td>
			</tr>
			<tr>
				<td class='SLIPCONTROL' colspan="6"><div align="left"><b><%=header4 %></b></div></td>
			</tr>
			</table>
			</div>
			</td>
		</tr>	
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>		
	</table>
	<table style="border:1px solid black;border-collapse: collapse;background-color: rgba(186, 185, 185, 1); " width="100%" cellspacing="0"  cellpadding="0">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
		<td class='SLIPCONTROLBOLD' colspan="6"><div align="center"><b>BILLING SERVICES RECEIPT</b></div></td>
		</tr>		
	</table>	
	<table style="border:1px solid black;border-collapse: collapse; " width="100%" cellspacing="0"  cellpadding="0">
		<tr>
			<td width="100%">	
			<%
			if(request.getAttribute("filePath")!=null)
			{
				String file=request.getAttribute("filePath").toString();
				//String file="C:/NIMS/AHIMSG5/PrintTemp/Billing10047.dat";
				BufferedReader reader = new BufferedReader(new FileReader(file));
			    StringBuilder sb = new StringBuilder();
			    String line;
			    while((line = reader.readLine())!= null)
			    {
			    	sb.append(line+"\n");
			    }
			    out.println("<center><pre>"+sb.toString()+"</pre></center>");
			}
		    %>
		    </td>
		</tr>
<!--		<tr>-->
<!--			<td width="100%" rowspan="1" height="550">&nbsp;</td>-->
<!--		</tr>	-->
	</table>
   
	<input type="hidden" name="filePath" value="${PatWalletTransBean.filePath}" />
	
</html:form>
</body>
</html>