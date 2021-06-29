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
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head><meta charset="utf-8" />
<title>Patient Leave/Join Record Desk</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<style type='text/css'>
.dragme{
	cursor: move;
	color: #FFFFFF;
	background-color: #005680;
}
</style>
<script language="Javascript" src="../../hisglobal/js/validation2.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/ADT_Trans.js"></script>
<script language="Javascript" src="../js/PatientLeaveJoinRecordTrans.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../js/nursingDesk.js"></script>
<script type="text/javascript">
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

function dateDiff(date_1,date_2) 
{
var ret=parse_Date(date_1,"-");
var ret1=parse_Date(date_2,"-");
var dt1=ret.month+"-"+ret.day+"-"+ret.year;
var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
date1 = new Date();
date2 = new Date();
diff  = new Date();
{ // Validates first date 
var myDate1=new Array();
myDate1=dt1.split("-");
date1temp = new Date(myDate1[2],(myDate1[0]-1),myDate1[1]);
date1.setTime(date1temp.getTime());
}
{ // Validates second date 
var myDate2=new Array();
myDate2=dt2.split("-");
date2temp = new Date(myDate2[2],(myDate2[0]-1),myDate2[1]);
date2.setTime(date2temp.getTime());
}
// sets difference date to difference of first date and second date
diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
timediff = diff.getTime();
weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
timediff -= days * (1000 * 60 * 60 * 24);
days=parseInt(weeks)*7+days;
var diff = /*weeks + " weeks, " +*/ days + " days " ;
//alert("date diff->"+diff);
var o=document.getElementById("daysOnLeave");
o.innerHTML="<font color='blue' weight='strong'>"+diff+"</font>";
document.forms[0].strDaysOnLeave.value=diff;
}
</script>
</head>
<body onLoad="CNTIni(),butdis(),hlpOnLoadDesk_LJ(),leaveJoinDesk();leave_join_ipd();" onUnload="closePopUp();">
<html:form action="/transactions/PatientLeaveJoinRecordTransCNT.cnt" method="post">
	<div id="menu1"></div>
	<div class="errMsg" id="errMsg">
	<bean:write name="patientLeaveJoinRecordTransBean" property="strErrMsgString"/></div>
	<div class="normalMsg" id="normalMsg">
	<bean:write name="patientLeaveJoinRecordTransBean" property="strNormalMsgString"/></div>
	<tag:tab tabLabel="Patient Leave/Join Record" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	<div id="patTldglbdiv">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='0px'>
	  <tr class="HEADER">
		<td width="90%">Patient Leave/Join Record Entry&gt;&gt;</td>
		<td width="10%" align='center'>
		<input type="hidden" value="${patientLeaveJoinRecordTransBean.strTempLeaveJoin}" name="strIs_Leave_Join">
		<bean:write name="patientLeaveJoinRecordTransBean" property="strTempLeaveJoinLabel"></bean:write></td>		
	  </tr>
	 </table> 
	 <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr>
		 <td width='5%'  class='TITLE' align='center'>
		   <div id="plusonLineId">
		   <img src="../../hisglobal/images/plus.gif" name="plusonLine" onclick="showPatDetails();" style="cursor: pointer;"/></div>
		   <div id="minusonLineId" style="display: none">
		   <img src="../../hisglobal/images/minus.gif" name="minusonLine" onclick="hidePatDetails();" style="cursor: pointer;"/></div>
			</td>
			<td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
	  </tr>
	</table>
	<div id="crNoId">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>    
			<td width="25%" class="LABEL" nowrap="nowrap">CR No.</td>
			<td width="75%" class="CONTROL">
			<input type="hidden" name="strCrNo" value="${patientLeaveJoinRecordTransBean.strCrNo}">
			<bean:write name="patientLeaveJoinRecordTransBean" property="strCrNo"></bean:write>
			</td>
		</tr>
		<tr>
			<pDtl:patDtl crNo="${patientLeaveJoinRecordTransBean.strCrNo}" address="false"></pDtl:patDtl>
		</tr>
	</table>
	</div>
	</div>
	<!--  <div id="patDtlTld" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<pDtl:patDtl crNo="${patientLeaveJoinRecordTransBean.strCrNo}" address="false"></pDtl:patDtl>
			</td></tr>
		</table>
	
	</div>-->
	<div id="admDtlTldglbdiv" style="display: none">
	  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId1" style="display: none">
			<img src="../../hisglobal/images/plus.gif" name="plusonLine1"  onclick="showDetails1();" ></div>
			<div id="minusonLineId1"><img
				src="../../hisglobal/images/minus.gif" name="minusonLine1"
				 onclick="hideDetails1();"></div>
			</td>
			<td colspan="7" class="TITLE">Admission Details</td>
		</tr>
	</table>
	</div>
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtl crNo="${patientLeaveJoinRecordTransBean.strCrNo}"></aDtl:addDtl>
	</div>		
 	<div id="transChng" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId2" style="display:none"><img
				src="../../hisglobal/images/plus.gif" name="plusonLine2"  onclick="showDetails2();" /></div>
			<div id="minusonLineId2"><img
				src="../../hisglobal/images/minus.gif" name="minusonLine2"  onclick="hideDetails2();"></div>
			</td>
	        <td width="95%" class="TITLE">Leave Request Details</td>
	    </tr>
	</table>
 	</div>	
	<div id="LeaveId" style="display:none;">
       <table align="center" border="0" cellspacing="1" class="TABLEWIDTH">
            <tr>
              <td class="LABEL" width="25%"><div align="right">Leave Type</div></td>
             <td class="CONTROL" width="25%"><bean:write name="patientLeaveJoinRecordTransBean" property="strLeaveTypeText"/></td>
             <td class="LABEL" width="25%"></td>
             <td class="LABEL" width="25%"></td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">Leave Request Entry Date</div></td>
             <td class="CONTROL" colspan="" width="25%"><font size="2" color="blue">
             <bean:write name="patientLeaveJoinRecordTransBean" property="strEntryDate"/></font></td>
             <td class="LABEL" width="25%"><div align="right">Leave Sanctioned from Date</div></td>
             <td class="CONTROL" colspan="" width="25%">
             <bean:write name="patientLeaveJoinRecordTransBean" property="strValidFrom"/></td>
            </tr>
            <tr>
               <td class="LABEL" width="25%"><div align="right">Leave Sanctioned till Date</div></td>
               <td colspan="" class="CONTROL" width="25%">
               <bean:write name="patientLeaveJoinRecordTransBean" property="strValidTo"/></td>
               <td class="LABEL" width="25%"><div align="right">Whether Vacant the Bed</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <input type="checkbox" name="strIsBedVacant" value="${patientLeaveJoinRecordTransBean.strIsBedVacant}" disabled></td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">Address During Leave</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <textarea name="strAddrLeave" cols="20" rows="" readonly><bean:write name="patientLeaveJoinRecordTransBean" property="strAddrLeave"/></textarea></td>
              <td class="LABEL" width="25%"><div align="right">Phone No.</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <input type="text" name="strPhoneNo" value="${patientLeaveJoinRecordTransBean.strPhoneNo}" readonly></td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">Patient Condition at the time of Leave</div></td>
              <td colspan="3" class="CONTROL" width="75%">
              <textarea name="strPatCondL" cols="20" rows="" readonly><bean:write name="patientLeaveJoinRecordTransBean" property="strPatCondL"/></textarea></td>
              
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
			<textarea name="strRsn" cols="20" rows="" readonly><bean:write name="patientLeaveJoinRecordTransBean" property="strRsn"/></textarea></td>
		 </tr>
	  </table>
	</div>
 	<div id="transChngl" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width="100%" class="TITLE">Leave Record Entry</td>
	    </tr>
	</table>
 	</div>
 	<div id="transChngj" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width="100%" class="TITLE">Joining Record Entry</td>
	    </tr>
	</table>
 	</div>	
    <div id="id0" style="display:none">
      <table class="TABLEWIDTH" align="center" border="0" cellspacing="1px">
	    <tr>
	        <td width="25%" class="LABEL" colspan="1">Leaving Date/Time</td>
	        <td width="25%" class="CONTROL" colspan="3"><font color='blue'>
	        <bean:write name="patientLeaveJoinRecordTransBean" property="strCtDateTime"/></font></td>
	       <!-- <td class="LABEL" width="25%" colspan="1"><font color='red'>*</font>Leaving Time</td>
			<td class="CONTROL" width="25%" colspan="1"> -->
				<!--  <input type="text" class="txtFldSmall" name="strShiftHour_L" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="hour(this,event)"><b>:</b>
			<input type="text" class="txtFldSmall" name="strShiftMin_L" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="min(this,event);"><b>:</b>
			<input type="text" class="txtFldSmall" name="strShiftSec_L" maxlength="2" onkeypress="return validateData(event,5);" onkeyup="sec(this,event)">
			<select class="comboSmall" name="strShiftAmPm_L">
					<option value="1">AM</option>
					<option value="2">PM</option>
			</select> </td>-->		
	    </tr>
	    <tr>
	      <td width='25%' class='LABEL' colspan="1">
	      <font id="adviceAtLeaveIdMandatory" color='red'>*</font>Advice Given at the time of Leave</td>
	      <td width="75%" class="CONTROL" colspan="3"><textarea name="strAdviceL" rows="" cols="20"></textarea>
	    </tr>
	</table>
    </div>
	<div id="id1" style="display:none"></div>
	<div id="disBnRj" style="display:none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width='25%' class='LABEL'>
			   <div align="right"><font color='red'>*</font>Join Accepted By:</div>
			</td>
			<td width="75%" class="CONTROL">
			   <select style='cursor: pointer; cursor: hand' class="comboMax" title="Employee Code and List of Doctors" name="strRmkJ">
				  <bean:write name="patientLeaveJoinRecordTransBean" property="strRmk" filter="false" />
			   </select>
			</td>
		 </tr>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right"><font id="remarksIdMandatory" color="red">*</font>Remarks:</div>
			</td>
			<td width="75%" class="CONTROL"><textarea name="strRsn_Entry" cols="20" rows=""></textarea></td>
		 </tr>
	  </table>
	</div>
	<table class="TABLEWIDTH" border="0" align="center" cellspacing='1px'>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<table border='0' cellspacing='1px' class="TABLEWIDTH" align="center">
	<tr>
	  <logic:equal value="1" property="strCheckFlagType" name="patientLeaveJoinRecordTransBean" >
		<td align="center">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" 
		   title="To save the Record" onClick="return saveInDeskIPD();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" 
		   title="Go back to the Main Page" onClick="closeTabOnDesk();">
	    </td>
	   </logic:equal>
	   <logic:equal value="0" property="strCheckFlagType" name="patientLeaveJoinRecordTransBean" >
		<td align="center">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" 
		   title="To save the Record" onClick="return saveInDesk();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" 
		   title="Go back to the Main Page" onClick="cancelToDesk();">
	    </td>
	   </logic:equal>
	</tr>
	</table>
	<div id="patDtlTld" style="display: none">
	</div>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strTempVal" value="">
	<input type="hidden" name="strEntryDate" value=""> 
	<input type="hidden" name="strLeavingDt" value="">
	<input type="hidden" name="strTime" value=""> 
	<input type="hidden" name="strLeaveSlNo" value="${patientLeaveJoinRecordTransBean.strLeaveSlNo}">
	<input type="hidden" name="strTempLeaveJoin" value="${patientLeaveJoinRecordTransBean.strTempLeaveJoin}">
	<input type="hidden" name="strDaysOnLeave" value="${patientLeaveJoinRecordTransBean.strDaysOnLeave}">
	<input type="hidden" name="strErrMsgString" value="${patientLeaveJoinRecordTransBean.strErrMsgString}">
	<input type="hidden" name="strNormalMsgString" value="${patientLeaveJoinRecordTransBean.strNormalMsgString}">
	<input type="hidden" name="strMlc" value="">
	<input type="hidden" name="strjHLP" value="${patientLeaveJoinRecordTransBean.strjHLP}">
	<input type="hidden" name="cnt" value="">
	<input type="hidden" name="strAge" value="">
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strCtDate" value="${patientLeaveJoinRecordTransBean.strCtDate}">
	<input type="hidden" name="strCtDateTime" value="${patientLeaveJoinRecordTransBean.strCtDateTime}">
	<input type="hidden" name="strBId" value="${patientLeaveJoinRecordTransBean.strBId}">
	<input type="hidden" name="strBId" value="${patientLeaveJoinRecordTransBean.strBId}">
	<input type="hidden" name="strRemarksOthersJoinMandatoryFlag" value="${patientLeaveJoinRecordTransBean.strRemarksOthersJoinMandatoryFlag}">
	<input type="hidden" name="strRemarksOthersOnlineMandatoryFlag" value="${patientLeaveJoinRecordTransBean.strRemarksOthersOnlineMandatoryFlag}">
	<input type="hidden" name="strCheckFlagType" value="${patientLeaveJoinRecordTransBean.strCheckFlagType }">
	<cmbPers:cmbPers/>
</html:form>
<script>
	if(document.forms[0].strRemarksOthersJoinMandatoryFlag.value==0)
		document.getElementById("remarksIdMandatory").style.display="none"
	if(document.forms[0].strRemarksOthersOnlineMandatoryFlag.value==0)
		document.getElementById("adviceAtLeaveIdMandatory").style.display="none"
</script>
<script>
	if(document.forms[0].strCrNo.value.length>=12){
		getAgeSex();
	}
 </script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>