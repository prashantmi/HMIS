<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!--
	@author Pragya Sharma
	Creation Date: 21-Feb-2012
 -->


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>

<html> 
<head>    

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<script language="JavaScript" src="/HISClinical/mrd/reports/js/mrdReport.js"></script>
	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<style type="text/css">
@media print { #noPrint { display: none; }} 
</style>

</head>

<body> 

<html:form action="/report/deliveriesAndCUTInsertionReport" >

<%
	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
	
<his:statusNew>
	<div align="center">
		<b><font color="#FF0000" size="3" face="Arial, Verdana, Helvetica, sans-serif">
			<his:status/>
			&nbsp;&nbsp;&nbsp;<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">	
		</font></b>
	</div>
</his:statusNew>

<his:statusTransactionInProcess>
<%
	HospitalMstVO voHosp = ControllerUTIL.getHospitalVO(request);
%>
	<div id="noPrint" align="right"> 
		<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
	</div>
		
	<div id="">
		<br>
		<div align="right">
			<b><font color="#000000" size="2" face="Arial">
				Report Date &amp; Time: <%=sysDate%>
			</font></b>
		</div>
		<br>
		<div align="center">
			<b><font color="#000000" size="4" face="Arial"><%=voHosp.getHospitalName()%></font></b>
			<br><b><font color="#000000" size="3" face="Arial"><%=voHosp.getCity() %></font></b>
			<br>
		</div>
		<div align="center">
			<br><b><font color="#000000" size="3" face="Arial">Medical Record Department</font></b>
			<br><b><font color="#000000" size="3" face="Arial">Deliveries & Cu-T Insertion Report</font></b>
			<br><b><font color="#000000" size="2" face="Arial">From Date:&nbsp;<bean:write name="DeliveriesAndCUTInsertionReportFB" property="fromDate"/>
			&nbsp;&nbsp;To Date:&nbsp;<bean:write name="DeliveriesAndCUTInsertionReportFB" property="toDate"/></font></b>
		</div>		
		<br>
		
		<bean:define id="serv" name="DeliveriesAndCUTInsertionReportFB" property="strService" type="java.lang.String"></bean:define>
		
		<table width="80%" align="center" cellspacing="1" cellpadding="0">
			<tr>
				<td valign="middle" width="20%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="srNo" /></font></b>
					</div>
				</td>
				<td valign="middle" width="40%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">
							<bean:message key="description" />
						</font></b>
					</div>
				</td>
				<td valign="middle" width="40%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">
							<bean:message key="noOfPatients" />
						</font></b>
					</div>
				</td>
			</tr>
			<% if((serv.equals("1"))||(serv.equals("0"))) { %>
			<tr>
				<td valign="top" width="20%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							1.
						</font>
					</div>
				</td>
				<td valign="top" width="40%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							Deliveries
						</font>
					</div>
				</td>
				<td valign="top" width="40%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<bean:write name="DeliveriesAndCUTInsertionReportFB" property="strDeliveriesCount" />
						</font>
					</div>
				</td>
			</tr>
			<%} if ((serv.equals("2"))||(serv.equals("0"))) { %>
			<tr>
				<td valign="top" width="20%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
						<%if(serv.equals("2")){ %>1.
						<%}else{ %>2.<%} %>
						</font>
					</div>
				</td>
				<td valign="top" width="40%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							Cu-T Insertion
						</font>
					</div>
				</td>
				<td valign="top" width="40%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<bean:write name="DeliveriesAndCUTInsertionReportFB" property="strCuTInsertCount"/>
						</font>
					</div>
				</td>
			</tr>
			<%} %>
			<tr>
				<td valign="top" width="20%" style="border-top: 1px solid black; border-bottom: 1px solid black;" >
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">
							Total
						</font></b>
					</div>
				</td>
				<td valign="top" width="40%" style="border-top: 1px solid black; border-bottom: 1px solid black;" >
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">
						</font></b>
					</div>
				</td>
				<td valign="top" width="40%" style="border-top: 1px solid black; border-bottom: 1px solid black;" >
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">
							<bean:define name="DeliveriesAndCUTInsertionReportFB" property="strDeliveriesCount" id="del" type="java.lang.String"/>
							<bean:define name="DeliveriesAndCUTInsertionReportFB" property="strCuTInsertCount" id="cuT" type="java.lang.String"/>
							<%=Integer.toString(Integer.parseInt(del)+Integer.parseInt(cuT))%>
						</font></b>
					</div>
				</td>
			</tr>
		</table>
	</div>
</his:statusTransactionInProcess>
<html:hidden name="DeliveriesAndCUTInsertionReportFB" property="hmode"/>            
</html:form>
</body>
</html>