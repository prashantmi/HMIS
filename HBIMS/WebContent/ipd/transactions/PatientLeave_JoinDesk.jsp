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
<title>Patient Leave Offline</title>
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
<script language="Javascript" src="../../ipd/js/PatLeaveJoin.js"></script>
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
function changeLeaveType()
{
 if(document.getElementsByName("strLeaveType")[0].checked==true)
   {
      document.forms[0].strValidTo.value=document.forms[0].strProbJoinDateForExternalInv.value
      document.getElementById('validTo').style.display="none";
      document.getElementById('dateId2').style.display="";
      document.getElementById('daysOnLeave').innerHTML="1 days";
   }
  else
   {
      document.getElementById('validTo').style.display="";
      document.getElementById('dateId2').style.display="none";
       document.getElementById('daysOnLeave').innerHTML="1 days";
   }

}
function setLeaveType()
{
 document.getElementsByName("strLeaveType")[0].checked=true;
 document.getElementById('daysOnLeave').innerHTML="1 days";
}
function leaveRequestType()
{
	var Id=document.forms[0].strLeaveReqType.value;
	if(Id==0)
	{
	document.getElementById("leaveReq").innerHTML="<!--<input type=radio name=strCheckbox value=0 onclick='' checked='checked'>Online Leave --><input type=radio name=strCheckbox value=1 onclick='' checked='checked'>Offline Leave";
	}
	if(Id==1)
	{
	//document.getElementById("leaveReq").innerHTML="<input type=radio name=strCheckbox value=0 onclick='' disabled>Online Leave <input type=radio name=strCheckbox value=1 onclick='' checked='checked'>Offline Leave";
    document.getElementById("leaveReq").innerHTML="<!--<input type=radio name=strCheckbox value=0 onclick='' disabled style='display:none'>--><input type=radio name=strCheckbox value=1 onclick='' checked='checked'>Offline Leave";
		
	}
	if(Id==2)
	{
	document.getElementById("leaveReq").innerHTML="<!--<input type=radio name=strCheckbox value=0 onclick='chkClicked(this)'>Online Leave --><input type=radio name=strCheckbox value=1 onclick='chkClicked(this)' checked='checked'>Offline Leave";
	
	}
	document.getElementById("daysOnLeave").innerHTML="";
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

/*function dateDiff(date_1,date_2) 
{
var ret=parse_Date(date_1,"-");
var ret1=parse_Date(date_2,"-");
var dt1=ret.month+"-"+ret.day+"-"+ret.year;
var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
date1 = new Date();
date2 = new Date();
diff  = new Date();
//alert("Valid From/in format DD-MM-YYYY->"+dt1);
//alert("Valid To/in format DD-MM-YYYY->"+dt2);
{ // Validates first date 
var myDate1=new Array();
myDate1=dt1.split("-");
date1temp = new Date(myDate1[2],myDate1[0],myDate1[1]);
date1.setTime(date1temp.getTime());
}
{ // Validates second date 
var myDate2=new Array();
myDate2=dt2.split("-");
date2temp = new Date(myDate2[2],myDate2[0],myDate2[1]);
//alert("date2temp.getTime()->"+date2temp.getTime());
date2.setTime(date2temp.getTime());
}
// sets difference date to difference of first date and second date
//alert("date1.getTime()->"+date1.getTime());
diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
timediff = diff.getTime();
//alert("timediff->"+timediff);
weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
timediff -= days * (1000 * 60 * 60 * 24);
days=parseInt(weeks)*7+days;
var diff = /*weeks + " weeks, " +*/ /* days + " days " ;
//alert("date diff->"+diff);
var o=document.getElementById("daysOnLeave");
o.innerHTML="<font color='blue' weight='strong'>"+diff+"</font>";
document.forms[0].strDaysOnLeave.value=diff;
}*/

</script>
</head>
<body onLoad="CNTIni(),butdis(),hlpOnLoad_LJ();leaveRequestType();setLeaveType();" onUnload="closePopUp();">
<html:form action="/transactions/PatientLeaveCNT.cnt" method="post">
	<div id="menu1"></div>
	<div class="errMsg" id="errMsg"><bean:write name="patientLeaveBean" property="strErrMsgString"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="patientLeaveBean" property="strNormalMsgString"/></div>
	<tag:tab tabLabel="Patient on Offline Leave" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	<div id="patTldglbdiv">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='0px'>
	  <tr class="HEADER">
		<td width="50%">Leave Offline Details&gt;&gt;</td>
		<td width="50%" align="right"><div id="leaveReq"><!--  <input type=radio name=strCheckbox value=0 onclick="chkClicked(this)" style="display:none;">
		Online Leave --><input type=radio name=strCheckbox value=1 onclick="chkClicked(this)">
		Offline Leave</div>
		<input type=hidden name=strTmpLabel value="${patientLeaveBean.strTmpLabel }">
		<script>
		   if(document.getElementsByName("strTmpLabel")[0].value==0){
		   		document.getElementsByName("strCheckbox")[0].checked=true;
		   }else{
		   		document.getElementsByName("strCheckbox")[1].checked=true;
		   }
		   function chkClicked(_these){
		   		if(_these.value!=document.getElementsByName("strTmpLabel")[0].value){
			   		if(_these.value==0){
			   			document.forms[0].hmode.value="LEAVEREQUEST";
			   		}else{
			   			document.forms[0].hmode.value="OFFLINELEAVE";
			   		}
		   			document.forms[0].submit();
		   		}
		   }
		   </script>
		</td>
	  </tr>
	  </table>
	  <!--  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr>
		 <td width='100%' class='TITLE' align='center'>
		   <div id="plusonLineId">
		   <img src="../../hisglobal/images/plus.gif" name="plusonLine"  onclick="showDetails();" 
		   style="cursor: pointer;"/>&nbsp;&nbsp;Patient Demographic Details</div>
		   <div id="minusonLineId"  style="display: none">
		   <img src="../../hisglobal/images/minus.gif" name="minusonLine"  onclick="hideDetails();" 
		   style="cursor: pointer;">&nbsp;&nbsp;Patient Demographic	Details</div>
	</td>
	</tr>
	</table>-->
	<div id="crNoId">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap">CR No.</td>
			<td width="75%" class="CONTROL">
			<input type="hidden" name="strCrNo" value="${patientLeaveBean.strCrNo}">
			<bean:write name="patientLeaveBean" property="strCrNo"></bean:write>
			</td>			
		</tr>
	</table>
	</div>
	</div>
	<div id="patDtlTld" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<pDtl:patDtl crNo="${patientLeaveBean.strCrNo}" address="false"></pDtl:patDtl>
			</td></tr>
		</table>	
	</div>
	<div id="admDtlTldglbdiv" style="display: none">
	  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='100%' class='TITLE' align='center'>
			<div id="plusonLineId1" style="display: none">
			<img src="../../hisglobal/images/plus.gif" name="plusonLine1"  onclick="showDetails1();" style="cursor: pointer;">
			&nbsp;&nbsp;Admission Details</div>
			<div id="minusonLineId1">
			<img src="../../hisglobal/images/minus.gif" name="minusonLine1"
				 onclick="hideDetails1();" style="cursor: pointer;">&nbsp;&nbsp;Admission Details</div>
		</tr>
	</table>
	</div>
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtl crNo="${patientLeaveBean.strCrNo}"></aDtl:addDtl></div>		
  	<div id="transChng" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width='100%' class='TITLE' align='center'>
			<div id="plusonLineId2" style="display:none">
			<img src="../../hisglobal/images/plus.gif" name="plusonLine2" onclick="showDetails2();" style="cursor: pointer;"/>
			&nbsp;&nbsp;Leave Details</div>
			<div id="minusonLineId2">
			<img src="../../hisglobal/images/minus.gif" name="minusonLine2" onclick="hideDetails2();" style="cursor: pointer;">
			&nbsp;&nbsp;Leave Details</div>
			</td>
	    </tr>
	</table>
 </div>	
 <div id="LeaveId" style="display:none">
       <table align="center" border="0" cellspacing="1" class="TABLEWIDTH">
             <tr>
            <td class="LABEL" width="25%"><div align="right">Leave Type</div></td>
            <td class="CONTROL" width="75%" colspan="3"><div align="left">
            <input type=radio name=strLeaveType value=1 onclick="changeLeaveType(this)">
		     External Investigation <input type=radio name=strLeaveType value=2 onclick="changeLeaveType(this)">
		     Other Purpose
		     </div></td>
            </tr>
            <tr>              
             <td class="LABEL" width="25%"><div align="right">Leave from Date</div></td>
             <td class="CONTROL" colspan="" width="25%"><div id="validFrm">
             <bean:write name="patientLeaveBean" property="strCtDate"/></div></td>
             <td class="LABEL" width="25%"><div align="right"><font size="1" color="red">*</font>
             Probable Joining Date</div></td>
             <td colspan="" class="CONTROL" width="25%"><div id="validTo" style="display:none">
             <date:date name="strValidTo" value="${patientLeaveBean.strProbJoinDateForExternalInv}" 
               jsFunc="dateDiff(document.forms[0].strCtDate.value,document.forms[0].strValidTo.value);">
               </date:date></div>
                <div id="dateId2"  style="">
               <bean:write name="patientLeaveBean" property="strProbJoinDateForExternalInv"/></div>
               </td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">No. of Days on Leave</div></td>
              <td class="CONTROL" width="25%">
              <div id="daysOnLeave"><bean:write name="patientLeaveBean" property="strDaysOnLeave"/></div></td>             
             <td class="LABEL" width="25%"><div align="right">Leave Sanctioned Date</div></td>
             <td class="CONTROL" colspan="" width="25%"><div id="entryDt">
             <date:date name="strSactionDate" value="${patientLeaveBean.strCtDate}"></date:date></div>
             </td>            
            </tr>            
            <tr>
              <td class="LABEL" width="25%"><div align="right"><font size="1" color="red">*</font>
              Address During Leave</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <textarea name="strAddrLeave" cols="19" rows="">
              <bean:write name="patientLeaveBean" property="strAddrLeave"/></textarea></td>
              <td class="LABEL" width="25%"><div align="right"><font size="1" color="red">*</font>Phone No.</div></td>
              <td colspan="" class="CONTROL" width="25%">
              <input type="text" name="strPhoneNo" value="${patientLeaveBean.strPhoneNo}" 
              onkeypress="return validateData(event,5);" maxlength="12"></td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right"><font size="1" color="red">*</font>
              Patient Condition at the time of Leave</div></td>
              <td colspan="" class="CONTROL" width="25%"><textarea name="strPatCondL" cols="19" rows="">
              <bean:write name="patientLeaveBean" property="strPatCondL"/></textarea></td>
              <td class="LABEL" width="25%"><div align="right">
              <font size="1" id="adviceIdMandatory" color="red">*</font>Advice Given at the time of Leave</div></td>
              <td colspan="" class="CONTROL" width="25%"><textarea name="strAdviceL" cols="19" rows="">
              <bean:write name="patientLeaveBean" property="strAdviceL"/></textarea></td>
            </tr>
            <tr>
              <td class="LABEL" width="25%"><div align="right">Whether Vacant the Bed</div></td>
              <td colspan="1" class="CONTROL" width="25%">
              <input type="checkbox" name="strIsBedVacant" value="${patientLeaveBean.strIsBedVacant}" checked></td>
              <td class="LABEL" width="25%"><div align="right"></div></td>
              <td colspan="1" class="CONTROL" width="25%">
            </tr>
      </table>
 	</div>
	<div id="disBnR" style="display: none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width='25%' class='LABEL' colspan="1">
			   <div align="right"><font color='red'>*</font>Leave Sanctioned By</div>
			</td>
			<td width="25%" class="CONTROL" colspan="1">
			   <select style='cursor: pointer;' title="Employee Code and List of Doctors" name="strRmkL" class="comboMax">
				  <bean:write name="patientLeaveBean" property="strRmk" filter="false" />
			   </select>
			</td>
			<td width='25%' class='LABEL' colspan="1"></td>
			<td width='25%' class='CONTROL' colspan="1"></td>
		 </tr>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right"><font color="red">*</font>Reason for Leave</div>
			</td>
			<td width="25%" class="CONTROL"><textarea name="strRsnL" cols="19" rows="" ></textarea></td>
			<td width='25%' class='LABEL' colspan="1"></td>
			<td width='25%' class='CONTROL' colspan="1"></td>
		 </tr>
	  </table>
	</div>
	<div id="disBnR_LdtlJ" style="display: none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width='25%' class='LABEL'>
			   <div align="right"><font color='red'>*</font>Leave Sanctioned By</div>
			</td>
			<td width="75%" class="CONTROL">
				  <bean:write name="patientLeaveBean" property="strLeaveBy" filter="false" />
			</td>
		 </tr>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right"><font color="red">*</font>Reason for Leave</div>
			</td>
			<td width="75%" class="CONTROL"><textarea name="strLeaveRsn" cols="20" rows="" readonly>
			<bean:write name="patientLeaveBean" property="strRsn" filter="false" /></textarea></td>
		 </tr>
	  </table>
	</div>
	<div id="transChngj" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width='5%' class='multiControl' align='center'>
	        <td width="95%" class="TITLE">Joining Details
		    </td>
	    </tr>
	</table>
 </div>	
	<div id="id1"></div>
	<div id="disBnRj" style="display:none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width='25%' class='LABEL'>
			   <div align="right"><font color='red'>*</font>Join Accepted By</div>
			</td>
			<td width="75%" class="CONTROL">
			   <select title="Employee Code and List of Doctors" name="strRmkJ">
				  <bean:write name="patientLeaveBean" property="strRmk" filter="false" />
			   </select>
			</td>
		 </tr>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right"><font color="red">*</font>Remarks</div>
			</td>
			<td width="75%" class="CONTROL"><textarea name="strRsnJ" cols="20" rows=""></textarea></td>
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
	  <logic:equal value="1" property="strCheckFlagType" name="patientLeaveBean" >
		<td align="center">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" 
		   title="To save the Record" onClick="return saveIPD();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" 
		   title="Go back to the Main Page" onClick="closeTabOnDesk();">
	    </td>
	  </logic:equal>
	  <logic:equal value="0" property="strCheckFlagType" name="patientLeaveBean" >
		<td align="center">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" 
		   title="To save the Record" onClick="return save();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" 
		   title="Go back to the Main Page" onClick="cancelToDesk()">
	    </td>
	  </logic:equal>
	</tr>
	</table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strTempVal" value="">
	<input type="hidden" name="strTime" value=""> 
	<input type="hidden" name="strEntryDate" value="${patientLeaveBean.strEntryDate}"> 
	<input type="hidden" name="strValidFromDate" value="${patientLeaveBean.strValidFrom}"> 
	<input type="hidden" name="strValidToDate" value="${patientLeaveBean.strValidTo}"> 
	<input type="hidden" name="strLeaveSlNo" value="${patientLeaveBean.strLeaveSlNo}">
	<input type="hidden" name="strDaysOnLeave" value="${patientLeaveBean.strDaysOnLeave}">
	<input type="hidden" name="strErrMsgString" value="${patientLeaveBean.strErrMsgString}">
	<input type="hidden" name="strNormalMsgString" value="${patientLeaveBean.strNormalMsgString}">
	<input type="hidden" name="strMlc" value="">
	<input type="hidden" name="strjHLP" value="${patientLeaveBean.strjHLP}">
	<input type="hidden" name="chk" value="${patientLeaveBean.chk}">
	<input type="hidden" name="strAdmStatCode" value="${patientLeaveBean.strAdmStatCode}">
	<input type="hidden" name="cnt" value="">
	<input type="hidden" name="strCtDate" value="${patientLeaveBean.strCtDate}">
	<input type="hidden" name="strBId" value="${patientLeaveBean.strBId}">
	<input type="hidden" name="strRemarksOthersOfflineMandatoryFlag" value="${patientLeaveBean.strRemarksOthersOfflineMandatoryFlag}">
	<input type="hidden" name="strRemarksOthersJoinMandatoryFlag" value="${patientLeaveBean.strRemarksOthersJoinMandatoryFlag}">
	<input type="hidden" name="strProbJoinDateForExternalInv" value="${patientLeaveBean.strProbJoinDateForExternalInv}">
	<input type="hidden" name="strLeaveReqType" value="${patientLeaveBean.strLeaveReqType}">
	<input type="hidden" name="strCheckFlagType" value="${patientLeaveBean.strCheckFlagType }">
	<cmbPers:cmbPers/>
</html:form>
<script>
	if(document.forms[0].strRemarksOthersOfflineMandatoryFlag.value==0 && document.forms[0].strAdmStatCode.value=="12")
		document.getElementById("adviceIdMandatory").style.display="none"
	</script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>