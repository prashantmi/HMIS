<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!--
	@author Pragya Sharma
	Creation Date: 20-Feb-2012
 -->


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.vo.MrdReportDataVO"%>

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

<html:form action="/report/pediatricsImmunizationsReport" >

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
	List lstRptData = (List) session.getAttribute(MrdConfig.MRD_REPORT_DATA);
	if(lstRptData!=null && lstRptData.size()>0)
	{
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
			<br><b><font color="#000000" size="3" face="Arial">Immunizations Report</font></b>
			<br><b><font color="#000000" size="2" face="Arial">From Date:&nbsp;<bean:write name="PediatricsImmunizationsReportFB" property="fromDate"/>
			&nbsp;&nbsp;To Date:&nbsp;<bean:write name="PediatricsImmunizationsReportFB" property="toDate"/></font></b>
		</div>		
		<br>
		
		<table width="80%" align="center" cellspacing="1" cellpadding="0">
			<tr>
				<td valign="middle" width="20%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">
							<bean:message key="srNo" />
						</font></b>
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
			<%
				for(int i=0; i<lstRptData.size(); i++)
				{
					MrdReportDataVO vo = (MrdReportDataVO)lstRptData.get(i);
			%>
				<tr>
					<td valign="top" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Arial">
								<%=Integer.toString(i+1)%>.
							</font>
						</div>
					</td>
					<td valign="top" width="40%">
						<div align="center">
							<font color="#000000" size="2" face="Arial">
								<%=vo.getStrImmunizationType()%>
							</font>
						</div>
					</td>
					<td valign="top" width="40%">
						<div align="center">
							<font color="#000000" size="2" face="Arial">
								<%=vo.getStrCount()%>
							</font>
						</div>
					</td>
				</tr>
			<%
				}
			%>
		</table>
	</div>
<%	
	}
	else
	{
%>
	<div align="center">
		<b><font color="#FF0000" size="3" face="Arial, Arial, Helvetica, sans-serif">
			<his:status/>
			&nbsp;&nbsp;&nbsp;<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">	
		</font></b>
	</div>
<%
	}
%>
</his:statusTransactionInProcess>
<html:hidden name="PediatricsImmunizationsReportFB" property="hmode"/>            
</html:form>
</body>
</html>