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
<title>Patient Transfer</title>
<link href="../../ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

<style type='text/css'>
.dragme {
	cursor: move;
	color: #FFFFFF;
	background-color: #005680;
}
</style>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="Javascript" src="../../ipd/js/PatTransfer.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
 <script type="text/javascript" src="../js/bootstrap.min.js"></script>
 

<script type='text/javascript'>
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

</script>
</head>
<body onLoad="document.forms[0].strCrNo.focus(),hlpOnLoad(),butdis(),selectDept();" onUnload="closePopUp();">
<html:form action="/transactions/PatientTransferTransCNT.cnt" method="post">
	<div id="menu1"></div>
	<div class="errMsg" id="errMsg"><bean:write name="patientMoveTransBean" property="strErrMsgString"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="patientMoveTransBean" property="strNormalMsgString"/></div>
	<tag:tab tabLabel="Patient Transfer" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	<div id="patTldglbdiv">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr class="HEADER">
		<td colspan="4">Patient Movement Details&gt;&gt;</td>
	  </tr>
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
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap"><font color="red">*</font>CR No.</td>
			<td width="75%" class="CONTROL"><crNo:crNo
				value="${patientMoveTransBean.strCrNo}"
				js="onkeypress='return validateData(event,5);' onkeyup='enter(event,'patientMoveTransBean');'"></crNo:crNo>
			<img style='cursor: pointer; cursor: hand' src="../../hisglobal/images/Go.png" align="top" onclick="return goFunc('patientMoveTransBean');">
			</td>
		</tr>
	</table>
	</div>
	<div id="patDtlTld" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
			<tr><td>
				<pDtl:patDtl crNo="${patientMoveTransBean.strCrNo}" address="false"></pDtl:patDtl>
			</td></tr>
		</table>
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
	<aDtl:addDtl crNo="${patientMoveTransBean.strCrNo}"></aDtl:addDtl></div>
 <div id="transChng" style="display:none">	
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	    <tr>
	        <td width="25%" class="TITLE">Transfer To
			    <select style='cursor: pointer; cursor: hand' name="strTransferUnit"
				class="comboNormal" dir="ltr" title="Transfer to/between Clinical Unit"
				onChange="return transferOf(this);">
				<option value="1" title="Transfer to/between Ward">Dept. and Ward</option>
				<option value="2" title="Transfer to/between Bed">Room/Bed</option>
				<option value="3" title="Transfer to/between Deptt./Unit">Deptt./Unit</option>
				<option value="4" title="Transfer to/between Service Area">Service Area</option>
				<option value="5" title="Transfer to/between Hospital">Outside Hospital</option>
			    </select>
		    </td>
	    </tr>
	</table>
 </div>	
 
 <div style="display:none;" id="id1"></div>
 
	<div id="disBnR" style="display: none">
	  <table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
		 <tr>
			<td width='25%' class='LABEL'>
			   <div align="right"><font color='red'>*</font>Advised By</div>
			</td>
			<td width="75%" class="CONTROL">
			   <select style='cursor: pointer; cursor: hand' title="Employee Code and List of Doctors" name="strRmk">
				  <bean:write name="patientMoveTransBean" property="strRmk" filter="false" />
			   </select>
			</td>
		 </tr>
		 <tr>
			<td width="25%" class="LABEL">
			   <div align="right"><font color="red">*</font>Reason for Transfer:</div>
			</td>
			<td width="75%" class="CONTROL"><textarea name="strRsn" cols="20" rows=""></textarea></td>
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
		<td align="center">
		   <!-- <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-sv.png" title="To save the Record" onClick="return save();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-clr.png" title="Clear the Record" onClick="cancel();">
		   <img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" title="Go back to the Main Page" onClick="init_Main();">
	     -->
	    <br><a href="#" class="button" id="" onClick="return save();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="cancel();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onClick="init_Main();"><span class="cancel">Cancel</span></a>
	    </td>
	</tr>
	</table>
	
	<div class="modal-container" style="display: none;">
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content-->
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
	<input type="hidden" name="hmode">
	<input type="hidden" name="ajaxResDivId" value="id1">
	<input type="hidden" name="strTempVal" value="">
	<input type="hidden" name="strPrevDblOcc" value="${patientMoveTransBean.strPrevDblOcc}">
	<input type="hidden" name="strHoldRoom" value="${patientMoveTransBean.strHoldRoom}">
	<input type="hidden" name="strErrMsgString" value="${patientMoveTransBean.strErrMsgString}">
	<input type="hidden" name="strNormalMsgString" value="${patientMoveTransBean.strNormalMsgString}">
	<input type="hidden" name="strMlc" value="${patientMoveTransBean.strMlc}">
	<input type="hidden" name="strPvtWardCode" value="${patientMoveTransBean.strPvtWardCode}">
	<input type="hidden" name="strPopUp_id">
	<input type="hidden" name="cnt" value="">
	<input type="hidden" name="strBId" value="${patientMoveTransBean.strBId}">
	<input type="hidden" name="strIpdConfIcuWard" value="${patientMoveTransBean.strIpdConfIcuWard }">
	
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strAge" value="">
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>