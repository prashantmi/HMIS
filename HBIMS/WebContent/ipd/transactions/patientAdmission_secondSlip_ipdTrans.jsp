<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<!--  
   NIMS PRINT SECOND SLIP JSP
-->
<%@page import="ipd.IpdConfigUtil"%>
<html>
<head>
<meta charset=utf-8>
<title>Admission Slip</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../ipd/js/patientAdmission.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body onload="printSecondSlip();">
<html:form action="/transactions/PatientAdmissionTransCNT.cnt" method="post">
<div class="errMsg" id="errID"><bean:write name="paientAdmissionTransBean" property="strMsgString"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="paientAdmissionTransBean" property="strMsg" filter="false"/></div>
	<div class="warningMsg" id="wrnID"><bean:write name="paientAdmissionTransBean" property="strWarningMsg"/></div>
	<table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
	</table>
   <table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><font size="4">
			<b>AUTHORIZATION FOR OPERATION ETC.</b></font></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><font size="4"><b></b></font></div></td>
		</tr>
  </table>
	
	<table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
	</table>
	
	<table width="100%">
	<tr>
			<td align="center" colspan="6">
			<img src="../../hisglobal/images/Manipuri Content Image.JPG"/>			
			</td>
		</tr>
	 </table>	
		
	<table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
	</table>
	
	<table width="100%" >
		<tr>
		   <td class='SLIPCONTROL' colspan="5"><div align="left">
		   <font size="4">(a) Authorization for Medical and/or Surgical Treatment:</font></div></td>
		</tr>
			<tr>
		   <td class='SLIPCONTROL' colspan="6"><div align="left">
		   <font size="4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		   Permission is hereby given for the performance of any diagnostic examination, biopsy, 
		   transfusion or operation and for the administration of any anaesthetic as may be 
		   deemed advisable in the course of this hospital admission.</font></div></td>
		</tr>
			
	</table>
	<table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		
	</table>
	
	<table width="100%" >
		<tr>
		    <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4"></font></div></td>
		
		   <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4">Signature of Patient/Relative&nbsp;______________</font></div></td>
		</tr>
		<tr>
		    <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4"></font></div></td>
		
		   <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4">Relationship to Patient&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______________</font></div></td>
		</tr>
		<tr>
		    <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4"></font></div></td>
		
		   <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4">Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______________</font></div></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
	</table>
	<table width="100%" >
		<tr>
		   <td class='SLIPCONTROL' colspan="6"><div align="left"><font size="4">
		   (b) Release of Responsibility of Discharge:</font></div></td>
		</tr>
			<tr>
		   <td class='SLIPCONTROL' colspan="6"><div align="left">
		   <font size="4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		   I <b><u>am leaving</u></b> <b> / </b><b><u>taking away the patient </u></b>from the Hospital 
		   against the Medical Advice of the attending Physician.deemed advisable in the course of this 
		   Hospital admission. I acknowledge that I have been informed of the risk involved and hereby release 
		   the attending Physician and the Hospital from all responsibilities for any ill effect, 
		   which may result from such discharge.</font></div></td>
		</tr>
			
		
	</table>
	<table width="100%">
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		
	</table>
	
	<table width="100%" >
		<tr>
		    <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4"></font></div></td>
		
		   <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4">Signature of Patient/Relative&nbsp;______________</font></div></td>
		</tr>
		<tr>
		    <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4"></font></div></td>
		
		   <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4">Relationship to Patient&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______________</font></div></td>
		</tr>
		<tr>
		    <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4"></font></div></td>
		
		   <td class='SLIPCONTROL' width="50%" colspan="3"><div align="left"><font size="4">Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______________</font></div></td>
		</tr>
	</table>
	
	<input type="hidden" name="hmode"/>
	
	<input type="hidden" name='strHospCode' />
	<input type="hidden" name="strIsAdvanceAmountAtAdmission"	value="${paientAdmissionTransBean.strIsAdvanceAmountAtAdmission} "/>	
	
	
	
</html:form>
</body>
</html>