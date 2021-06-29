<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Discharge Cancellation</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">



<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="JavaScript" src="../js/dischargeCancel.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<script type="text/javascript">
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
</script>
</head>
<body onload="CNTIni(),butdis(),hlpOnLoad_disCancel();" onUnload="closePopUp();">
<html:form action="/transactions/DischargeCancelTransCNT.cnt" method="post" onsubmit="return goFunc();">
<div id="errMsg" class="errMsg"><bean:write name="dischargecancelTransBean" property="strErrMsgString" /></div>
<div class="normalMsg" id="normalMsg"><bean:write name="dischargecancelTransBean" property="strNormalMsgString"/></div>
<tag:tab tabLabel="Discharge Cancellation Details" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
	
	<div id="patTldglbdiv">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr class="HEADER">
		<td colspan="4">Discharge Cancellation&gt;&gt;</td>
	  </tr>
	</table>
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap"><font color="red">*</font>Admission No.</td>
			<td width="75%" class="CONTROL">
			<input type="text" name="strAdmnNo" value="${dischargecancelTransBean.strAdmnNo}" maxlength="15" onkeypress="return validateData(event,5)" onkeyup="return goFuncOnEnter(event);" />
			<img style='cursor: pointer; cursor: hand' src="../../hisglobal/images/Go.png" align="top" onclick="goFunc();">
			</td>
		</tr>
	</table>
	</div>
	<div id="patDemDtlId" style="display: none;">
	 <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr>
		 <td width='5%' class='TITLE' align='center'>
		   <div id="plusonLineId" style="display: none"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine" title="Show Details" onclick="showDetails();" /></div>
		   <div id="minusonLineId"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/minus.gif" name="minusonLine" title="Hide Details" onclick="hideDetails();"></div>
		   </td>
		   <td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
	  </tr>
	</table> 	
	</div>
	
	<div id="patDtlTld" style="display: none">		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr>
			<td width="45%" class="TITLE">Patient Details</td>
			</tr>
		</table>
	 <pDtl:patDtl crNo="${dischargecancelTransBean.strCrNo}" address="false"></pDtl:patDtl> 
	</div>
	<div id="admDtlTldglbdiv" style="display: none">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId1" style="display: none"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine1" title="Show Details" onclick="showDetails1();" ></div>
			<div id="minusonLineId1"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/minus.gif" name="minusonLine1" title="Hide Details"
				 onclick="hideDetails1();"></div>
			</td>
			<td colspan="7" class="TITLE">Admission Details</td>
		</tr>
	</table>
	</div>
	<div id="admDtlTld" style="display: none">
		<bean:write name="dischargecancelTransBean" property="admissionDetailValues" filter="false"/></div>
	
	<div id="transChng" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width="25%" class="TITLE">Discharge Cancellation Record Entry</td>
	    </tr>
	</table>
 </div>	
 
 <div id="id1" align="center">
	          <bean:write name="dischargecancelTransBean" property="changeOfBed" filter="false"/> 
	   </div>
 
	<div id="disBnR" style="display: none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width='25%' class='LABEL'>
			   <div align="right"><font color='red'>*</font>Discharge Cancellation By:</div>
			</td>
			<td width="75%" class="CONTROL">
			   <select style='cursor: pointer;' tabindex='1' class="comboMax" title="Employee Code and List of Doctors" name="strRmk">
				  <bean:write name="dischargecancelTransBean" property="strRmk" filter="false" />
			   </select>
			</td>
		 </tr>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right"><font color="red">*</font>Cancellation Remarks:</div>
			</td>
			<td width="75%" class="CONTROL"><textarea name="strRsn" tabindex='1' cols="20" rows=""></textarea></td>
		 </tr>
	  </table>
	</div>
	
	<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="1px">
	<tr class="FOOTER"> 
	<td colspan="1" width='90%'><div align="left"><font size="2" color="red">#</font>Discharge Cancellation By Lists Only Those Employee/Doctors of the department in which patient was last admitted.   </div></td>
    <td colspan="1" width='10%'><font size="2" color="red">*</font>Mandatory Fields</td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td align="center">
		<logic:notEmpty name="dischargecancelTransBean" property="strCrNo">
		<!-- 	<img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" title="To save the Record"  onClick="return validate1();"> -->
			<a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</logic:notEmpty>
		<!-- <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-clr.png" title="Clear the Record" onClick="fun();">
		<img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" title="Go back to the Main Page" onClick="cancelFunc();">
		 -->
		 
		<a href="#" class="button"	onClick="fun();"><span class="clear">Clear</span></a> 
		<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
	</td>
	</tr>
</table>
<div class="modal-container" style="display: none;">
	<div id="myModal" class="modal fade" role="dialog">  
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content -->
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">Bed Dashboard Status</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body" id="modalSpaceId">
	        
	      </div>
	    </div>
	
	  </div>
	</div>
	 </div> 
<input type="hidden" name="hmode" value=""/>	
<input type="hidden" name="chkIndx" value=""/>
<input type="hidden" name="cnt" value="">
<input type="hidden" name="strAge" value="">
<input type="hidden" name="strAgeUnit" value="">
<input type="hidden" name="strSexCode" value="">
<input type="hidden" name="strErrMsgString" value="${dischargecancelTransBean.strErrMsgString}">
<input type="hidden" name="strNormalMsgString" value="${dischargecancelTransBean.strNormalMsgString}">
<input type="hidden" name="strCrNo" value="${dischargecancelTransBean.strCrNo}"/>

</html:form>
<script>
if(document.forms[0].strCrNo.value.length>=12){
	getAgeSex();
}
 </script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>