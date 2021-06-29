
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Tracking</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="JavaScript" src="../js/patientTracking.js"></script>

<script type="text/javascript">
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
</script>
</head>
<body onload="CNTIni(),butdis(),hlpOnLoad_disCancel();changeRadio(document.forms[0].strCase);" onUnload="closePopUp();">
<html:form action="/transactions/PatientTrackingTransCNT.cnt" method="post" onsubmit="return goFunc();">
<div id="blanket" style="display: none; height: 543px;"></div>
<div id="errMsg" class="errMsg"><bean:write name="patientTrackingTransBean" property="strErrMsgString" /></div>
<div class="normalMsg" id="normalMsg"><bean:write name="patientTrackingTransBean" property="strNormalMsgString"/></div>
<tag:tab tabLabel="Patient Tracking" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
	
	<div id="patTldglbdiv">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr class="HEADER">
		<td colspan="4">Admitted Patient Tracking&gt;&gt;</td>
	  </tr>
	  <tr >
	    <td class="LABEL" colspan="2">
	    <div align="right">
		    <html:radio property="strCase" name="patientTrackingTransBean" value="1" onclick="changeRadio(this);hideDtl(this);" >Admission No.</html:radio>
		    <html:radio property="strCase" name="patientTrackingTransBean" value="2" onclick="changeRadio(this);hideDtl(this);" >CR No.</html:radio>
	    </div>
	    </td>
    </tr>
	</table>
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr id='admNoId'>
			<td width="25%" class="LABEL" nowrap="nowrap"><font color="red">*</font>Admission No.</td>
			<td width="75%" class="CONTROL">
			<input type="text" name="strAdmnNo" value="${patientTrackingTransBean.strAdmnNo}" maxlength="15" 
			onkeypress="return validateData(event,5)" onkeyup="return goFuncOnEnter(event);" />
			<!-- <img style="cursor:pointer;cursor:hand;" id="searhPatientImageId" 
				src="../../hisglobal/images/viewDetails.gif" title="Click here for Patient Search" align="middle" 
				name='searchPatient' onclick="showPatientListingWindow('5',document.forms[0].strCrNo,'setSelectedCrNo');"/> -->
			<img style='cursor: pointer; cursor: hand' src="../../hisglobal/images/Go.png" align="top" onclick="goFunc();">
			</td>
		</tr>
		<tr id='crNoId'>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId">
				<crNo:crNo value="${patientTrackingTransBean.strCrNo}" js="onkeypress='return validateData(event,5)' onkeyup='return goFuncOnEnter(event);'"></crNo:crNo>
				<img style='cursor: pointer; cursor: hand' src="../../hisglobal/images/Go.png" align="top" onclick="goFunc();">				
			</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="patDemDtlId" style="display: none;">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='0'>
	  <tr>
		 <td width='5%' class='TITLE' align='center'>
		   <div id="plusonLineId" style="display: none"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine" title="Show Details" onclick="showDetails();" /></div>
		   <div id="minusonLineId"><img style='cursor: pointer; cursor: hand;'
				src="../../hisglobal/images/minus.gif" name="minusonLine" title="Hide Details" onclick="hideDetails();"></div>
		   </td>
		   <td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
	  </tr>
	</table>	
	</div>
	<div id="patDtlTld" style="display: none">		
	 <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='25%' class='LABEL' align='center'><div align="right">CR No.</div></td>
			<td width='75%' class='CONTROL' align='center'><div align="left">
			<bean:write name="patientTrackingTransBean" property="strCrNo"/></div>
			</td>			
		</tr>
	</table>
	 <pDtl:patDtl
		crNo="${patientTrackingTransBean.strCrNo}" address="false"></pDtl:patDtl>
		
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px' id='admDtlId'>
		<tr>
			<td width='25%' class='LABEL' align='center'><div align="right">Admission Date/Time</div></td>
			<td width='25%' class='CONTROL' align='center'><div align="left">
			<bean:write name="patientTrackingTransBean" property="strAdmnDate"/></div>
			</td>
			<td width='25%' class='LABEL' align='center'><div align="right">Discharge Date/Time</div></td>
			<td width='25%' class='CONTROL' align='center'><div align="left">
			<bean:write name="patientTrackingTransBean" property="strDischargeTime"/></div>
			</td>
		</tr>		
	</table>
	</div>
	<div id="admDtlTldglbdiv" style="display: none"></div>
	<div id="admDtlTld" style="display: none"></div>
	
	<div id="movementDtlId" style="display: none">
	<bean:write name="patientTrackingTransBean" property="strMovementDtl" filter="false"/>
	</div>
	<div id="movDetails" style="display: none">	
	</div>
	
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr class="FOOTER"> 
    <td colspan="8"><font size="2" color="red">*</font>Mandatory Fields</td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td align="center">		
		<img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-clr.png" title="Clear the Record" onClick="fun();">
		<img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" title="Go back to the Main Page" onClick="cancelPage();">
	</td>
	</tr>
</table>
<input type="hidden" name="hmode" value=""/>	
<input type="hidden" name="cnt" value="">
<input type="hidden" name="strErrMsgString" value="${patientTrackingTransBean.strErrMsgString}">
<input type="hidden" name="strNormalMsgString" value="${patientTrackingTransBean.strNormalMsgString}">



</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>