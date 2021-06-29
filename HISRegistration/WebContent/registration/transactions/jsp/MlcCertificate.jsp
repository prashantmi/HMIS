<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css"> 
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">

<style>
@media print { 
		#noPrint
		{display: none;}
}
</style>
<script>
function printPage() 
{

window.print();
}
function submitPage(mode)
{
	
	
	var daddy = window.self;
	daddy.opener = window.self;
	daddy.close();
}
</script>
<title>MLC Certificate</title>
</head>
<body>
<s:form action="EmgMlcDtl">
<table width="60%" align="center">
<!-- <tr>	
	<td width="100%">	
		<div align="center">
			<font color="000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
					POST GRADUATE INSTITUTE OF MEDICAL EDUCATION CHANDIGARH-(INDIA)
					
				</b>
			</font>
		</div>
	</td>
</tr>
<br>	 -->
<tr>
				<td width="100%">
					<div id="noPrint" align="right"> 
						
						<img class="button" src="../../../HIS/hisglobal/images/buttons/btn-pnt.png"   style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
						<img class="button" src="../../../HIS/hisglobal/images/buttons/btn-ccl.png"   style="cursor: pointer" onclick="submitPage('ALLSUMMARY')" onkeypress="if(event.keyCode==13) submitPage('ALLSUMMARY')">
					</div>
				</td>
			</tr>		
				<br/>
				<br/>
<!--  -->
<tr>
	<td width="100%">
		<div class="div-table-col" id="divOPDCardLogo" style="width: 15%" align="left">
			<img src="/HISRegistration/hisglobal/images/nims-tr-logo.gif">
		</div>
		<div class="div-table-col" id="divOPDCardHeader" style="width: 65%" align="center">
			<font color="000000" size="4" face="Verdana, Arial, Helvetica, sans-serif">
			<b><s:property	 value="%{HospitalName}" /></b></font><br>
			<b><s:property value="%{address1}" /></b>,&nbsp;<b><s:property value="%{districtName}"/></b>-<b><s:property value="%{PinCode}"/></b>,&nbsp;<b><s:property value="%{stateName}" /></b>
			<br>
			Phone :<s:property value="%{Phone}" />
		</div>
	</td>
</tr>
<!--  -->				
<tr>	
	<td width="100%">	
		<div align="center">
			<font color="000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
					MEDICO-LEGAL CASE (MLC) - POLICE INFORMATION PROFORMA
				</b>
			</font>
		</div>
	</td>
</tr><br>	
<tr>	
	<td width="100%">	
		<div align="center">
			<font color="000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					(Prepare in Duplicate and obtain signatures of the receiving officer)
					
				
			</font>
		</div>
	</td>
</tr>
	<br>
<tr>
	<td width="80%">
	<font style="margin-left:1000px"><div style="margin-left:1000px" id="divTime">Time :<b> <s:property value="%{mlcTime}" /></b></div></font>
	</td>
</tr>	
<tr>
	<td width="80%">
	<font style="margin-left:1000px"><div style="margin-left:1000px" id="divDate">Date :  <b><s:property value="%{mlcDate}" /></b></div></font>
	</td>
</tr>
<tr>
	<td width="80%">
	<font style="margin-left:100px">To,</font>
	<br><font style="margin-left:150px"><b>The Office Incharge,</b></font>
	<br><font style="margin-left:150px"><b>Police Post,</b></font>
	<br><font style="margin-left:150px"><b><s:property value="%{hospitalName}" /></b></font>
	<br><font style="margin-left:150px"><b><s:property value="%{address1}" /></b></font>
	<br><font style="margin-left:150px"><b><s:property value="%{address2}" /></b></font>
	<br><font style="margin-left:150px"><b><s:property value="%{city}" /></b>&nbsp;<b><s:property value="%{districtName}" /></b></font>
	<br><font style="margin-left:150px"><b><s:property value="%{stateName}" /></b>-<b><s:property value="%{PinCode}"/></b></font>
	<br>
	<br><font style="margin-left:200px">A Patient with the following particulars has come/been brought to the emergency OPD and is 
     treated/discharged/has expired.This is for your information </font><br><font style="margin-left:150px"> and necessary action please.</font>
     <br><br>
     <font style="margin-left:250px"><div style="margin-left:250px" id="divName">Name :<b> <s:property value="%{patFirstName}" />&nbsp;<s:property value="%{patMiddleName}" />&nbsp;<s:property value="%{patLastName}" /></b></div></font><br>
     <font style="margin-left:250px"><div style="margin-left:250px" id="divFatherName">Father's Name : <b><s:property value="%{patGuardianName}" />&nbsp;</b></div></font><br>
     <font style="margin-left:250px"><div style="margin-left:250px" id="divAgeGender">Age :<b> <s:property value="%{patAge}" /></b> &nbsp;&nbsp;&nbsp; Sex :<b> <s:property value="%{patGender}" /></b> &nbsp;&nbsp;&nbsp;
     CR No. : <b> <s:property value="%{patCrNo}" /></b> </div></font>
     <br>
      <font style="margin-left:250px"><div style="margin-left:250px" id="divAdm">Date and Time of Admission : </div></font><br>
       <font style="margin-left:250px"><div style="margin-left:250px" id="divDiagnosis">Diagnosis :<b> <s:property value="%{diagnosis}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(RSA, Medico-legal, Injuries, Poisoning, Burns, BD)</div></font><br>
	</td>
</tr>	
<tr>
	<td width="80%">
	<font style="margin-left:1000px"><div style="margin-left:900px">Signature of MO/SMO</div></font>
	</td>
</tr>
<tr>
	<td width="80%">
	<font style="margin-left:1000px"><div style="margin-left:900px">Name :  _______________</div></font>
	</td>
</tr>
<tr>
	<td width="80%">
<br>
<font style="margin-left:80px">------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</font>
<br><br><br></td></tr>
<tr>
	<td width="80%">
	<font style="margin-left:100px">Time :  _______________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(AM/PM)</font>
	<font style="margin-left:542px">Signature of Police</font><br><br>
	</td>
</tr>
<tr>
	<td width="80%">
	<font style="margin-left:100px">Date :  _______________</font>
	<font style="margin-left:635px">Name :  _______________</font>
	</td>
</tr>
</table>
<%-- <s:hidden name="patCrNo" value="%{patCrNo}" id="patCrNo"/> --%>
<s:hidden name="patFirstName" value="%{patFirstName}" id="patFirstName"/>
<s:hidden name="patLastName" value="%{patLastName}" id="patLastName"/>
<s:hidden name="patMiddleName" value="%{patMiddleName}" id="patMiddleName"/>
<s:hidden name="patGuardianName" value="%{patGuardianName}" id="patGuardianName"/>
<s:hidden name="patHusbandName" value="%{patHusbandName}" id="patHusbandName"/>
<s:hidden name="patAge" value="%{patAge}" id="patAge"/>
<s:hidden name="patGender" value="%{patGender}" id="patGender"/>
<s:hidden name="diagnosis" value="%{diagnosis}" id="diagnosis"/>
<s:hidden name="mlcDate" value="%{mlcDate}" id="mlcDate"/>
<s:hidden name="mlcTime" value="%{mlcTime}" id="mlcTime"/>

<s:token></s:token>
</s:form>
</body>
</html>