<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

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
<%@page import="registration.reports.report1.controller.fb.PatWiseVisitDtlReportFB"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date,java.util.Map,java.util.Set,java.util.Iterator"%>

<%@page import="registration.RegistrationConfig"%>
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
<!--

//-->
function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    
   window.back();
   
}

function printPage() 
{
	window.print();
}
</script>
<style type="text/css">
@media print { #noPrint { display: none; }} 
</style>

<%
	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	String sysTime = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), " HH : mm ");
%>
	
<his:statusNew>
	<div align="center">
		<b><font color="#FF0000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:write name="PatWiseVisitDtlReportFB" property="strWarning"/>
			&nbsp;&nbsp;&nbsp;<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">	
		</font></b>
	</div>
</his:statusNew>

<his:statusTransactionInProcess>
<%
	HospitalMstVO voHosp = ControllerUTIL.getHospitalVO(request);
	List lstData = (List) session.getAttribute(RegistrationConfig.RPT_DATA_PATIENT_WISE_VISIT);
	PatWiseVisitDtlReportFB fb=(PatWiseVisitDtlReportFB)pageContext.findAttribute("PatWiseVisitDtlReportFB");
	
%>
<logic:present name="<%=RegistrationConfig.MAP_RPT_DATA_PATIENT_WISE_VISIT %>">
<div id="noPrint" align="right"> 
		<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
	</div>
		
	<div id="pdfPrintingHTMLData">

		<br>
		<div align="right">
			<b><font color="#000000" size="2" face="Verdana">
				Report Date: <%=sysDate%> <%=sysTime%>
			</font></b>
		</div>
		
		
		<br>
		
		<div align="center">
			<b><font color="#000000" size="2" face="Verdana">
				 <%=voHosp.getHospitalName()%>
			</font></b>
		</div>
		<br>
		<div align="center">
			<b><font color="#000000" size="2" face="Verdana">
				 <%=voHosp.getAddress1()+" "+voHosp.getAddress2()+" "+voHosp.getCity()+" "+voHosp.getPinCode()%>
				</font></b>
		</div>
		
		
		<br>
		<div align="center">
			<b><font color="#000000" size="3" face="Verdana">
				Patient-Wise Visit Detail Report 
			</font></b>
			
			<br/>
			
			
			<logic:present name="PatWiseVisitDtlReportFB" property="fromDate" >
			<b><font color="#000000" size="2" face="Verdana">From Date : </font></b><font color="#000000" size="2" face="Verdana"><bean:write name="PatWiseVisitDtlReportFB" property="fromDate"/>&nbsp;&nbsp;</font>
			</logic:present>
			
			<logic:present name="PatWiseVisitDtlReportFB" property="toDate" >
			<b><font color="#000000" size="2" face="Verdana">To Date : </font></b><font color="#000000" size="2" face="Verdana"><bean:write name="PatWiseVisitDtlReportFB" property="toDate"/></font>
			</logic:present>
	</div>		





	<table width="100%" bordercolor ="#000000" border="1">
	<br/>
	<br/>
			<tr bordercolor="#000000" >
				<td  width="25%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="name" /></font></div>
				</td>
				<td width="25%" >
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write	name="PatWiseVisitDtlReportFB" property="patFirstName" />
				<bean:write name="PatWiseVisitDtlReportFB" property="patMiddleName" />
				<bean:write name="PatWiseVisitDtlReportFB" property="patLastName" />
				</font>
				</b>
				</td>
				
				<td width="25%" >
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo" />
				</font>
				</div>
				</td>
				
				
			<td>
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:write name="PatWiseVisitDtlReportFB" property="patCrNo" />
				</b>
				</font>
				</div>
				</td>
			
			</tr>
			
			<tr>
				
				<td width="25%" >
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="age" />
				<bean:message key="slash" />
				<bean:message key="gender" />
				</font></div>
				</td>
				
				<td width="25%" ><b><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="PatWiseVisitDtlReportFB" property="patAge" /> <bean:write
					name="PatWiseVisitDtlReportFB" property="patAgeUnit" /><logic:notEqual
					name="PatWiseVisitDtlReportFB" property="patGender" value="">/</logic:notEqual>
				<bean:write name="PatWiseVisitDtlReportFB" property="patGender" /></font></b></td>
				
				
				<td width="25%" >
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="date" />
				</font></div>
				</td>
				
				<td width="25%" ><b><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="PatWiseVisitDtlReportFB" property="registerDate" /></font></b></td>
				
			</tr>
			<tr>
			
				<td width="25%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fatherName" />/<bean:message key="husbandName" />
				
				<td width="25%" ><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="PatWiseVisitDtlReportFB" property="patGuardianName" />
				<logic:notEqual
					name="PatWiseVisitDtlReportFB" property="patHusbandName" value="">/</logic:notEqual>
				
				</font>
				</b>
				</td>
				
				<td  width="25%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mctsNo" />
				</font>
				</div>
				</td>
			
				
				<td width="25%" ><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="PatWiseVisitDtlReportFB" property="strMctsNo" />
								</font>
				</b>
				</td>
			</tr>
	


<%	String columnName1="";
String GroupByTDName="";
if(fb.getChoice().equals("0")){
	columnName1= "Visit Date";
	GroupByTDName= "Department Name";
}else
	{
	GroupByTDName="Visit Date";
	columnName1= "Department Name";
	} %>
<bean:define id="episodeDtlMap" name="<%=RegistrationConfig.MAP_RPT_DATA_PATIENT_WISE_VISIT %>" type="java.util.HashMap"></bean:define>
<logic:iterate indexId="idx" id="episodeEntry" name="episodeDtlMap" type="java.util.Map.Entry">
<bean:define id="UnitOrDate" name="episodeEntry" property="key" type="java.lang.String"></bean:define>
<bean:define id="episodeList" name="episodeEntry" property="value" type="java.util.ArrayList"></bean:define>
	
	<tr>
		<td width="25%" colspan="4" align="left">
			<b>
			<%=GroupByTDName %>:
			</b>
		
			<%=UnitOrDate %>
		</td>
	
	</tr>	
	
			<tr>
			<td width="25%" colspan="2"align="center">
				<b>
				<%=columnName1%>
				<b>
			</td>
			
			<td width="25%" colspan="2" align="center">
			<b>
			Visit No.
			</b>
			</td>
			
		</tr>
		
	
	<logic:iterate indexId="idx" id="episodeData" name="episodeList" type="java.lang.String">
		<tr>
		
		<td width="25%" colspan="2" align="center">
		<%=episodeData.split("#")[0] %>
		</td>
		<td width="25%" colspan="2" align="center">
		<%=episodeData.split("#")[1] %>
		</td>
		</tr>
		
	</logic:iterate>

</logic:iterate>
</table>
</logic:present>
<logic:notPresent name="<%=RegistrationConfig.MAP_RPT_DATA_PATIENT_WISE_VISIT %>">

<h1>
	 <font color="#FF0000" face="Verdana, Arial, Helvetica, sans-serif">
		Sorry, there is no data available.
	</font>
</h1>
	<font face="Verdana, Arial, Helvetica, sans-serif">
		The database does not contains any record corresponding to the parameter provided to generate the report.
		
	</font>	
	<img class="button" src='<his:path src="/hisglobal/images/back_tab.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">

</logic:notPresent>



</his:statusTransactionInProcess>

<html:hidden name="PatWiseVisitDtlReportFB" property="reportMode"/>            
<html:hidden name="PatWiseVisitDtlReportFB" property="reportType"/>            
<%--<html:hidden name="PatWiseVisitDtlReportFB" property="htmlCode"/>--%>            


