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

<%@page import="java.util.Map"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
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

<html:form action="/report/hospitalPerformanceReport" >

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
	Map mapReportData = (Map) session.getAttribute(MrdConfig.MRD_REPORT_DATA);
	if(mapReportData!=null && mapReportData.keySet().size()>0)
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
			<br><b><font color="#000000" size="3" face="Arial">Hospital Performance Report</font></b>
			<br><b><font color="#000000" size="2" face="Arial">From Date:&nbsp;<bean:write name="HospitalPerformanceReportFB" property="fromDate"/>
			&nbsp;&nbsp;To Date:&nbsp;<bean:write name="HospitalPerformanceReportFB" property="toDate"/></font></b>
		</div>		
		<br>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">1. OPD</font></b>
		</div>
		<%
			List lst1 = (List)mapReportData.get("1");
			MrdReportDataVO voData1 = (MrdReportDataVO) lst1.get(0);
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="30%" colspan="4">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="new" /></font></b>
					</div>
				</td>
				<td valign="middle" width="30%" colspan="4">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="old" /></font></b>
					</div>
				</td>
				<td valign="middle" width="40%" colspan="4">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="13%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData1.getStrNewMaleCount() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData1.getStrNewFemaleCount() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrNewChildMaleCount())+Integer.parseInt(voData1.getStrNewChildFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrNewMaleCount())+Integer.parseInt(voData1.getStrNewFemaleCount())
									+ Integer.parseInt(voData1.getStrNewChildMaleCount())+Integer.parseInt(voData1.getStrNewChildFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData1.getStrOldMaleCount() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData1.getStrOldFemaleCount() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrOldChildMaleCount())+Integer.parseInt(voData1.getStrOldChildFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrOldMaleCount())+Integer.parseInt(voData1.getStrOldFemaleCount())
									+ Integer.parseInt(voData1.getStrOldChildMaleCount())+Integer.parseInt(voData1.getStrOldChildFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrNewMaleCount())+Integer.parseInt(voData1.getStrOldMaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrNewFemaleCount())+Integer.parseInt(voData1.getStrOldFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrNewChildMaleCount())+Integer.parseInt(voData1.getStrNewChildFemaleCount())
									+Integer.parseInt(voData1.getStrOldChildMaleCount())+Integer.parseInt(voData1.getStrOldChildFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="13%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData1.getStrNewMaleCount())+Integer.parseInt(voData1.getStrOldMaleCount())
									+Integer.parseInt(voData1.getStrNewFemaleCount())+Integer.parseInt(voData1.getStrOldFemaleCount())
									+Integer.parseInt(voData1.getStrNewChildMaleCount())+Integer.parseInt(voData1.getStrNewChildFemaleCount())
									+Integer.parseInt(voData1.getStrOldChildMaleCount())+Integer.parseInt(voData1.getStrOldChildFemaleCount()))%>
						</font>
					</div>
				</td>
			</tr>
		</table>

		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">2. IPD</font></b>
		</div>
		<%
			List lst2 = (List)mapReportData.get("2");
			MrdReportDataVO voData2 = (MrdReportDataVO) lst2.get(0);
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="25%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData2.getStrMaleCount() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData2.getStrFemaleCount() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData2.getStrChildMaleCount())+Integer.parseInt(voData2.getStrChildFemaleCount()))%>
						</font>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=Integer.toString(Integer.parseInt(voData2.getStrMaleCount())+Integer.parseInt(voData2.getStrFemaleCount())
									+Integer.parseInt(voData2.getStrChildMaleCount())+Integer.parseInt(voData2.getStrChildFemaleCount()))%>
						</font>
					</div>
				</td>
			</tr>
		</table>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">3. Dental Patients</font></b>
		</div>
		<%
			List lst3 = (List)mapReportData.get("3");
			MrdReportDataVO voData3 = null; 
			if(lst3!=null && lst3.size()>0)
				voData3 = (MrdReportDataVO) lst3.get(0);
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="30%" colspan="4">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="new" /></font></b>
					</div>
				</td>
				<td valign="middle" width="30%" colspan="4">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="old" /></font></b>
					</div>
				</td>
				<td valign="middle" width="40%" colspan="4">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="male" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="female" /></font></b>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="child" /></font></b>
					</div>
				</td>
				<td valign="middle" width="13%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?voData3.getStrNewMaleCount():"0" %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?voData3.getStrNewFemaleCount():"0" %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrNewChildMaleCount())+Integer.parseInt(voData3.getStrNewChildFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrNewMaleCount())+Integer.parseInt(voData3.getStrNewFemaleCount())
									+ Integer.parseInt(voData3.getStrNewChildMaleCount())+Integer.parseInt(voData3.getStrNewChildFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?voData3.getStrOldMaleCount():"0" %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?voData3.getStrOldFemaleCount():"0" %>
						</font>
					</div>
				</td>
				<td valign="middle" width="7%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrOldChildMaleCount())+Integer.parseInt(voData3.getStrOldChildFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrOldMaleCount())+Integer.parseInt(voData3.getStrOldFemaleCount())
									+ Integer.parseInt(voData3.getStrOldChildMaleCount())+Integer.parseInt(voData3.getStrOldChildFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrNewMaleCount())+Integer.parseInt(voData3.getStrOldMaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrNewFemaleCount())+Integer.parseInt(voData3.getStrOldFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="9%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrNewChildMaleCount())+Integer.parseInt(voData3.getStrNewChildFemaleCount())
									+Integer.parseInt(voData3.getStrOldChildMaleCount())+Integer.parseInt(voData3.getStrOldChildFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
				<td valign="middle" width="13%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=(voData3!=null)?(Integer.toString(Integer.parseInt(voData3.getStrNewMaleCount())+Integer.parseInt(voData3.getStrOldMaleCount())
									+Integer.parseInt(voData3.getStrNewFemaleCount())+Integer.parseInt(voData3.getStrOldFemaleCount())
									+Integer.parseInt(voData3.getStrNewChildMaleCount())+Integer.parseInt(voData3.getStrNewChildFemaleCount())
									+Integer.parseInt(voData3.getStrOldChildMaleCount())+Integer.parseInt(voData3.getStrOldChildFemaleCount()))):"0"%>
						</font>
					</div>
				</td>
			</tr>
		</table>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">4. Lab Tests</font></b>
		</div>
		<%
			List lst4 = (List)mapReportData.get("4");
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Lab Tests</font></b>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Total Tests</font></b>
					</div>
				</td>
			</tr>
		<%
			if(lst4!=null && lst4.size()>0)
			{
				for(int n4=0; n4<lst4.size(); n4++)
				{
					MrdReportDataVO voData4 = (MrdReportDataVO) lst4.get(n4);
		%>
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData4.getStrProcedureName() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData4.getStrCount() %>
						</font>
					</div>
				</td>
			</tr>
		<%
				}
			}
			else
			{
		%>
			<tr>
				<td valign="middle" width="100%" colspan="2">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							No Lab Test Data Present
						</font>
					</div>
				</td>
			</tr>
		<%	} %>
		</table>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">5. Radiology</font></b>
		</div>
		<%
			List lst5 = (List)mapReportData.get("5");
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Radiology Tests</font></b>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Total Tests</font></b>
					</div>
				</td>
			</tr>
		<%
			for(int n5=0; n5<lst5.size(); n5++)
			{
				MrdReportDataVO voData5 = (MrdReportDataVO) lst5.get(n5);
		%>
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData5.getStrProcedureName() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData5.getStrCount() %>
						</font>
					</div>
				</td>
			</tr>
		<%
			}
		%>
		</table>



		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">6. ECG</font></b>
		</div>
		<%
			List lst6 = (List)mapReportData.get("6");
			MrdReportDataVO voData6 = (MrdReportDataVO) lst6.get(0);
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">ECG Tests</font></b>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Total Tests</font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							ECG
						</font>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData6.getStrCount() %>
						</font>
					</div>
				</td>
			</tr>
		</table>

		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">7. Surgeries</font></b>
		</div>
		<%
			List lst7 = (List)mapReportData.get("7");
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Surgeries</font></b>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Total Surgeries</font></b>
					</div>
				</td>
			</tr>
		<%
			if(lst7.size()>0)
			{
				for(int n7=0; n7<lst7.size(); n7++)
				{
					MrdReportDataVO voData7 = (MrdReportDataVO) lst7.get(n7);
		%>
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData7.getStrProcedureName() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData7.getStrCount() %>
						</font>
					</div>
				</td>
			</tr>
		<%
				}
			}
			else
			{
		%>
			<tr>
				<td valign="middle" width="100%" colspan="2">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							No Surgeries Data Present
						</font>
					</div>
				</td>
			</tr>
		<%
			}
		%>
		</table>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">8. Family Welfare</font></b>
		</div>
		<%
			List lst8 = (List)mapReportData.get("8");
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Family Welfare</font></b>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
		<%
			for(int n8=0; n8<lst8.size(); n8++)
			{
				MrdReportDataVO voData8 = (MrdReportDataVO) lst8.get(n8);
		%>
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData8.getStrProcedureName() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData8.getStrCount() %>
						</font>
					</div>
				</td>
			</tr>
		<%
			}
		%>
		</table>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">9. Immunizations</font></b>
		</div>
		<%
			List lst9 = (List)mapReportData.get("9");
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Immunizations</font></b>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial"><bean:message key="tot" /></font></b>
					</div>
				</td>
			</tr>
		<%
			if(lst9!=null && lst9.size()>0)
			{
				for(int n9=0; n9<lst9.size(); n9++)
				{
					MrdReportDataVO voData9 = (MrdReportDataVO) lst9.get(n9);
		%>
			<tr>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData9.getStrProcedureName() %>
						</font>
					</div>
				</td>
				<td valign="middle" width="50%">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							<%=voData9.getStrCount() %>
						</font>
					</div>
				</td>
			</tr>
		<%
				}
			}
			else
			{
		%>
			<tr>
				<td valign="middle" width="100%" colspan="2">
					<div align="center">
						<font color="#000000" size="2" face="Arial">
							No Immunization Data Present
						</font>
					</div>
				</td>
			</tr>
		<%	} %>
		</table>


		<br>
		<div align="left">
			<br><b><font color="#000000" size="3" face="Arial">10. Category</font></b>
		</div>
		<%
			//List lst10 = (List)mapReportData.get("10");
		%>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Category</font></b>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Sanctioned</font></b>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Posted</font></b>
					</div>
				</td>
				<td valign="middle" width="25%">
					<div align="center">
						<b><font color="#000000" size="2" face="Arial">Detailed </font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial">Doctors</font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
			</tr>
			<tr>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial">Nursing Staff</font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
			</tr>
			<tr>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial">Pharmacist</font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
			</tr>
			<tr>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial">Other Para Medicals</font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
			</tr>
			<tr>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial">Ministrial Staff Including Officers</font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
			</tr>
			<tr>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial">Class IV staff</font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
				<td valign="middle" width="25%"><div align="center"><font color="#000000" size="2" face="Arial"></font></div></td>
			</tr>
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
<html:hidden name="HospitalPerformanceReportFB" property="hmode"/>            
</html:form>
</body>
</html>