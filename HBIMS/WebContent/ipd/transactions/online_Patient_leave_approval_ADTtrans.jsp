<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head><meta charset="utf-8" />
<title>Patient Leave Request Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<style type='text/css'>
.dragme {
	cursor: move;
	color: #FFFFFF;
	background-color: #005680;
}
</style>
<script language="Javascript" src="../../hisglobal/js/validation2.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="Javascript" src="../../ipd/js/PatientLeaveApprovalTrans.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script type="text/javascript"><!--
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
var ie = document.all;
var nn6 = document.getElementById &&! document.all;
var isdrag = false;
var x, y,tx,ty;
var dobj;
///////1
function movemouse( e ) {
 //alert(e.clientX);
  if( isdrag ) {
    dobj.style.left = nn6 ?tx+ e.clientX-x+"px"  : tx+event.clientX-x+"px";
    dobj.style.top  = nn6 ?ty+ e.clientY-y+"px" :ty+ event.clientY-y+"px";
    
   // alert("mm: left:"+dobj.style.left+"top:"+dobj.style.top);
   dobj.style.display="none";
   display_popup_menu(this.document,"menu1",dobj.style.left,dobj.style.top);
    return false;
  }
}
///////2
function selectmouse( e ) {
  //alert("selectmouse");
  var fobj       = nn6 ? e.target : event.srcElement;
  var topelement = nn6 ? "HTML" : "BODY";
  while (fobj.tagName != topelement && fobj.className != "dragme") {
  fobj = nn6 ? fobj.parentNode : fobj.parentElement;
  }
  if (fobj.className=="dragme") {
    isdrag = true;
 //   alert("isDrag is true");
    dobj = document.getElementById("menu1");
    tx = parseInt(dobj.style.left+0);
    ty = parseInt(dobj.style.top+0);
    x = nn6 ? e.clientX : event.clientX;
    y = nn6 ? e.clientY : event.clientY;
    document.onmousemove=movemouse;
    return false;
  }
}
///////3
function styledPopupClose() {
  document.getElementById("menu1").style.display = "none";
}
///////4
function display_popup_menu(parent,divId,leftPos,topPos)
{
    var menu_element = document.getElementById(divId);
	//override the 'display:none;' style attribute
    menu_element.style.display = "";
	//get the placement of the element that invoked the menu...
    var placement = findPos(parent);
	//...and put the menu there
	menu_element.style.width="280px";
	menu_element.style.height="200px";
	menu_element.style.position="absolute";
	if(leftPos == ""){
		menu_element.style.left = placement[0]+300 + "px";
	}else{
		menu_element.style.left = leftPos ;
	}
	if(topPos == ""){
		menu_element.style.top = placement[1] + "px";
	}else{
		menu_element.style.top = topPos;
	}
}
///////5
function findPos(obj) {
	var curleft = curtop = 0;
	if (obj.offsetParent) {
		curleft = obj.offsetLeft
		curtop = obj.offsetTop
		while (obj = obj.offsetParent) {
			curleft += obj.offsetLeft
			curtop += obj.offsetTop
		}
	}
	return [curleft,curtop];
}
document.onmousedown=selectmouse;
    document.onmouseup=new Function("isdrag=false");
 
function enterOnTextBox(obj)
{
	var flag=validateData(obj,5);	
	if(flag){
			if(obj.keyCode==13)
			{
				var flag1=goFunc();
				
			}
		}
		else
		{
			return false;
		}
}
 function goFunc(){
 	if(!validateThroughRegExp(document.forms[0].strCrNo,1))
 	{
 		return;
 	}
	var hisValidator = new HISValidator("patientLeaveApprovalTransBean");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo","minlen="+document.getElementsByName("strCrNo")[0].maxLength,"CR No. must be "+document.getElementsByName("strCrNo")[0].maxLength+" Digits" );
	var retVal = hisValidator.validate(); 
	if(retVal){
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
					adt_create_loading_msg();//loading_msg
		}else{
		
		return false;
		}
		
}    

var regexForNumericOnlyValidation=/(^\d\d*$)/;
var regexForWordOnlyValidation=/(^\w\w*$)/;
var regexForAlphanumeric=/[^a-zA-Z0-9_]/ ;
var regexForAlphabetic=/[^a-zA-Z_ ]/ ;
var regExpForValidateNumeric= /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/;
var noalpha = /^[a-zA-Z]*$/;
var nonums = /^[0-9]*$/;
//Alphabetic
function validateThroughRegExp(obj,validationType)
{
   var str = "";
   switch(validationType)
	{
		case 1:		//for numbers only
			if(obj.value.search(nonums)==0) 
	   		{
	     		//return false;
	   		}
	   		else
	   		{
			    obj.focus();
			    str = "Incorrect Format:Numbers Only";
	    	}
			break;
			
		case 2:		//for characters only
			if(obj.value.search(regexForAlphabetic)==-1) 
	   		{
	     		//return false;
	   		}
	   		else
	   		{
			    obj.focus();
			    str = "Incorrect Format:Characters Only";
	    	}
			break;
			
		case 3:		//for alphanumeric only
			if(obj.value.search(regexForAlphanumeric)==-1) 
	   		{
	     		//return false;
	   		}
	   		else
	   		{
			    obj.focus();
			    str = "Incorrect Format:Special Characters Not Allowed";
	    	}
			break;
			
		case 4:		//for Numeric With Space
			if(obj.value.search(regexForAlphabetic)==-1) 
	   		{
	     		//return false;
	   		}
	   		else
	   		{
			    obj.focus();
			    str = "Incorrect Format:Numbers Only";
	    	}
			break;
			
		case 5:		//for characters with space
			if(obj.value.search(regexForAlphabetic)==-1) 
	   		{
	     		//return false;
	   		}
	   		else
	   		{
			    obj.focus();
			    str = "Incorrect Format:Characters Only";
	    	}
			break;
			
		case 6:		//for alphanumeric with space
			if(obj.value.search(regexForAlphabetic)==-1) 
	   		{
	     		//return false;
	   		}
	   		else
	   		{
			    obj.focus();
			    str = "Incorrect Format:Special Characters Not Allowed";
	    	}
			break;
			
	}	//end of Switch statement
	//return str;
	if(str!="")
	{
		alert(str);
		obj.value="";
		return false;
	}
	else
	{
		return true;
	}   

}
function getSanctionDate()
{
	dateDiff(document.forms[0].strValidFrom.value,document.forms[0].strValidTo.value);
}
--></script>
</head>
<body onLoad="getSanctionDate(),CNTIni(),butdis(),document.forms[0].strCrNo.focus(),hlpOnLoad_LJ();" onUnload="closePopUp();">
<html:form action="/transactions/PatientLeaveApprovalTransCNT.cnt" method="post">
	<div id="menu1"></div>
	<div class="errMsg" id="errMsg"><bean:write name="patientLeaveApprovalTransBean" property="strErrMsgString"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="patientLeaveApprovalTransBean" property="strNormalMsgString"/></div>
	<tag:tab tabLabel="Patient Leave Approval" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr class="HEADER">
		<td colspan="4">Patient Leave Approval&gt;&gt;</td>
	  </tr>
	 </table>
	 <div id="patTldglbdiv" style="display: none">
	 <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr>
		 <td width='5%' class='TITLE' align='center'>
		   <div id="plusonLineId">
		   <img src="../../hisglobal/images/plus.gif" name="plusonLine"  onclick="showPatDetails();" /></div>
		   <div id="minusonLineId"  style="display: none">
		   <img src="../../hisglobal/images/minus.gif" name="minusonLine"  onclick="hidePatDetails();"></div>
			</td>
			<td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
	  </tr>
	</table>
	</div>
	 <div id="crNoId">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='0'>
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td width="75%" class="CONTROL">
			<crNo:crNo value="${patientLeaveApprovalTransBean.strCrNo}"
				js=" onkeypress='return enterOnTextBox(event);' "></crNo:crNo>
				
				<!-- search field utility -->
				<img style="cursor:pointer;" src="../../hisglobal/images/viewDetails.gif" 
				   title="Click here for Patient Search" align="middle" name='searchPatient' 
				   onclick="showPatientListingWindow('1',document.forms[0].strCrNo,'setSelectedCrNo');" id="searchImgId"/>
			    <!-- search field utility ends -->
			    
			<img src="../../hisglobal/images/Go.png" style="cursor: pointer;" align="top" alt="Go" title="Get Patient Data"
			onclick="return goFunc('patientLeaveApprovalTransBean');">
			</td>			
		</tr>
	</table>
	</div>
	<div id="patDtlTld" style="display: none">		
	 <pDtl:patDtl crNo="${patientLeaveApprovalTransBean.strCrNo}" address="false"></pDtl:patDtl> 
	</div>
	<div id="admDtlTldglbdiv" style="display: none">
	  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId1" style="display: none"><img src="../../hisglobal/images/plus.gif" 
			name="plusonLine1"  onclick="showDetails1();" ></div>
			<div id="minusonLineId1"><img
				src="../../hisglobal/images/minus.gif" name="minusonLine1"
				 onclick="hideDetails1();"></div>
			</td>
			<td colspan="7" class="TITLE">Admission Details</td>
		</tr>
	</table>
	</div>
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtl crNo="${patientLeaveApprovalTransBean.strCrNo}"></aDtl:addDtl>
	</div>		
 <div id="transChng" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId2" style="display:none"><img
				src="../../hisglobal/images/plus.gif" name="plusonLine2"  onclick="showDetails2();"/></div>
			<div id="minusonLineId2"><img
				src="../../hisglobal/images/minus.gif" name="minusonLine2"  onclick="hideDetails2();"></div>
			</td>
	        <td width="95%" class="TITLE">Leave Details
		    </td>
	    </tr>
	</table>
 </div>	
 <div id="LeaveId" style="display:none;">
       <table align="center" border="0" cellspacing="1" class="TABLEWIDTH">
            <tr>
             <td class="LABEL" width="25%"><div align="right">Leave Type</div></td>
             <td class="CONTROL" colspan="" width="25%">
             <bean:write name="patientLeaveApprovalTransBean" property="strLeaveTypeText"/></td>
             <td class="LABEL" width="25%"></td>
             <td class="LABEL" width="25%"></td>
            </tr>
            <tr>
             <td class="LABEL" width="25%"><div align="right">Leave Request From Date</div></td>
             <td class="CONTROL" colspan="" width="25%"><font size="2" color="blue">
             <bean:write name="patientLeaveApprovalTransBean" property="strLeavReqFrmDate"/></font></td>
             <td class="LABEL" width="25%"><div align="right">Leave Request To Date</div></td>
             <td class="CONTROL" colspan="" width="25%"><font size="2" color="blue">
             <bean:write name="patientLeaveApprovalTransBean" property="strLeavReqToDate"/></font></td>
            </tr>
            <tr>
            <td class="LABEL" width="25%"><div align="right">Leave Sanctioned from Date</div></td>
             <td class="CONTROL" colspan="" width="25%">
             <date:date name="strValidFrom" value="${patientLeaveApprovalTransBean.strCtDate}" 
             jsFunc="dateDiff(document.forms[0].strValidFrom.value,document.forms[0].strValidTo.value);">
             </date:date></td>
             <td class="LABEL" width="25%"><div align="right">
               <font size="1" color="red">*</font>Leave Sanctioned till Date</div></td>
               <td colspan="" class="CONTROL" width="25%">
               <date:date name="strValidTo" value="${patientLeaveApprovalTransBean.strLeavReqToDate}" 
               jsFunc="dateDiff(document.forms[0].strValidFrom.value,document.forms[0].strValidTo.value);">
               </date:date></td>
            </tr>
            <tr>               
              <td class="LABEL" width="25%"><div align="right">No. of Days on Leave</div></td>
              <td class="CONTROL" width="25%"><div id="daysOnLeave">
              <bean:write name="patientLeaveApprovalTransBean" property="strDaysOnLeave"/></div></td>
              <td class="LABEL" width="25%"><div align="right">Leave Request Entry Date</div></td>
             <td class="CONTROL" colspan="" width="25%"><font size="2" color="blue">
             <bean:write name="patientLeaveApprovalTransBean" property="strEntryDate"/></font></td>             
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">Address During Leave</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <textarea name="strAddrLeave" cols="20" rows="" readonly><bean:write name="patientLeaveApprovalTransBean" property="strAddrLeave"/></textarea></td>
              <td class="LABEL" width="25%"><div align="right">Phone No.</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <input type="text" name="strPhoneNo" value="${patientLeaveApprovalTransBean.strPhoneNo}" readonly></td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">Patient Condition at the time of Leave</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <textarea name="strPatCondL" cols="20" rows="" readonly><bean:write name="patientLeaveApprovalTransBean" property="strPatCondL"/></textarea></td>
              <td class="LABEL" width="25%"><div align="right">Whether Bed has to be vacant</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <input type="checkbox" name="strIsBedVacant" value="${patientLeaveApprovalTransBean.strIsBedVacant}" disabled></td>
            </tr>
      </table>
 </div>
	<div id="disBnR" style="display: none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right">Reason for Leave:</div>
			</td>
			<td width="75%" class="CONTROL">
			<textarea name="strRsn" cols="20" rows="" readonly><bean:write name="patientLeaveApprovalTransBean" property="strRsn"/></textarea></td>
		 </tr>
	  </table>
	</div>
  <div id="leaveApproval" style="display: none">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td  class="TITLE" colspan="2">Leave Approval</td>
	    </tr>
	    <tr>
	       <td width='25%' class='LABEL'>Approve Leave</td>
	       <td width="75%" class="CONTROL">
	       Yes<input type="radio" name="strLeaveApproval" onClick="displayLeaveApprDtls();" checked>
	       No<input type="radio" name="strLeaveApproval" onClick="displayLeaveApprDtls();"></td>
	</table>
   </div>
	<div id="disBnRj" style="display: none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width="25%" class="LABEL">
			   <div id="rmkMandate" align="right">Remarks:</div>
			</td>
			<td width="75%" class="CONTROL">
			<textarea name="strApprRejectRmk" cols="20" rows=""></textarea></td>
		 </tr>
	  </table>
	</div>
	<table class="TABLEWIDTH" border="0" align="center" cellspacing='1px'>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>
	<table border='0' cellspacing='1px' class="TABLEWIDTH" align="center">
	<tr>
		<td align="center">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" 
		   title="To save the Record" onClick="return save();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-clr.png" 
		   title="Clear the Record" onClick="cancel();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" 
		   title="Go back to the Main Page" onClick="init_Main();">
	    </td>
	</tr>
	</table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strTempVal" value="">
	<input type="hidden" name="strLeaveStatusCode" value="${patientLeaveApprovalTransBean.strLeaveStatusCode}">
	<input type="hidden" name="strLeaveSlNo" value="${patientLeaveApprovalTransBean.strLeaveSlNo}">
	<input type="hidden" name="strDaysOnLeave" value="">
	<input type="hidden" name="strErrMsgString" value="${patientLeaveApprovalTransBean.strErrMsgString}">
	<input type="hidden" name="strNormalMsgString" value="${patientLeaveApprovalTransBean.strNormalMsgString}">
	<input type="hidden" name="strMlc" value="">
	<input type="hidden" name="strjHLP" value="${patientLeaveApprovalTransBean.strjHLP}">
	<input type="hidden" name="cnt" value="">
	<input type="hidden" name="strCtDate" value="${patientLeaveApprovalTransBean.strCtDate}">
	<input type="hidden" name="strBId" value="${patientLeaveApprovalTransBean.strBId}">
	<input type="hidden" name="strLeavReqFrmDate" value="${patientLeaveApprovalTransBean.strLeavReqFrmDate}">
	<input type="hidden" name="strLeavReqToDate" value="${patientLeaveApprovalTransBean.strLeavReqToDate}">
	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>