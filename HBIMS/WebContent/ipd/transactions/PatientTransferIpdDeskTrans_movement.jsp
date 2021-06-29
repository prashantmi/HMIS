
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head><meta charset="utf-8" />
<title>Patient Transfer and Movement</title>
<link href="../../ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../ipd/css/basic.css" rel="stylesheet" type="text/css">
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
<script language="Javascript" src="../../ipd/js/nursingDesk.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HBIMS/ipd/js/bootstrap.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<script type='text/javascript'>





var child = null;
var popIndex = 0;
var gblCntrlObj = null;
var ie = document.all;
var nn6 = document.getElementById &&! document.all;
var isdrag = false;
var x, y,tx,ty;
var dobj;

var objXmlHttp = null;
var userMode = "";
///////1
  
  $(document).ready(function()
{
	
	
});
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
function setAdvisedBy()
{
	if(typeof(document.forms[0].strConsultantId)=="undefined")
		document.forms[0].strRmk.value=0;
	else
		document.forms[0].strRmk.value=document.forms[0].strConsultantId.value;
	//document.getElementById("strRmk").selectedIndex=document.forms[0].strConsultant.value;
}

function getAdvisedBy()
{
	document.forms[0].strCurrentDeptUnitRoom.value=document.forms[0].curDept_Unt_RomCode.value;
    var mode="TRANSFERADVISEDBY";
	var url="../../ipd/transactions/PatientTransferTransCNT.cnt?hmode="+mode+"&curDept="+document.forms[0].strCurrentDeptUnitRoom.value+"&cons="+document.forms[0].strConsultant.value;
	ajaxFunction(url,"333");
}
function hideUnitChange()
{
	if(document.forms[0].strTransferUnit.value=='1')
	{
		document.getElementById("changeUnitId").style.display="";
	}
	else
	{
		document.getElementById("changeUnitId").style.display="none";
	}
}


document.onmousedown=selectmouse;
document.onmouseup=new Function("isdrag=false");

</script>
</head>
<body onLoad="hlpOnLoad();setAdvisedBy();" onUnload="closePopUp();">
<html:form action="/transactions/PatientTransferTransCNT.cnt" method="post">
	<div id="menu1"></div>
	<div class="errMsg" id="errMsg"><bean:write
		name="patientMoveTransBean" property="strErrMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="patientMoveTransBean" property="strNormalMsgString" /></div>
	<tag:tab tabLabel="Patient Transfer" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	<div id="patTldglbdiv">
		<input type="hidden" name="strMode" value="0" />
		<input type="hidden" value="${patientMoveTransBean.strCrNo}" name="strCrNo" /> 
	  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr class="HEADER">
			<td colspan="4">Patient Movement Details&gt;&gt;</td>
		</tr>
		<!--<tr>
			<td colspan="4" class='TITLE'>
			<input type="radio" name="strMode" value="0" checked="checked" onclick="changeMode();">Transfer
			<input type="radio" name="strMode" value="1" onclick="changeMode();" disabled="disabled">Movement
			</td>
		</tr> -->
		  <tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId"><img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine"
				title="Show Details" onclick="showDetails();" /></div>
			<div id="minusonLineId" style="display: none"><img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/minus.gif" name="minusonLine"
				title="Hide Details" onclick="hideDetails();"></div>
			</td>
			<td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
		</tr>
	</table>
	<!--<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap">CR No.</td>
			<td width="75%" class="CONTROL"><input type="hidden"
				value="${patientMoveTransBean.strCrNo}" name="strCrNo" /> <bean:write
				name="patientMoveTransBean" property="strCrNo"></bean:write></td>
		</tr>
	</table> -->
	</div>
	<div id="patDtlTld" style="display: none">
		<pDtl:patDtl crNo="${patientMoveTransBean.strCrNo}" address="false"></pDtl:patDtl>
	</div>
	<div id="admDtlTldglbdiv" style="display: none">
	  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId1" style="display: none"><img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine1"
				title="Show Details" onclick="showDetails1();"></div>
			<div id="minusonLineId1"><img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/minus.gif" name="minusonLine1"
				title="Hide Details" onclick="hideDetails1();"></div>
			</td>
			<td colspan="7" class="TITLE">Admission Details</td>
		</tr>
	</table>
	</div>
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtl crNo="${patientMoveTransBean.strCrNo}"></aDtl:addDtl>
		</div>
	<div id="transChng" style="display: none">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width="25%" class="TITLE">
				<input type="radio" name="strTransferUnit" value="1" checked="checked" onclick="return transferOfIPD(this);">Department/Ward
				<input type="radio" name="strTransferUnit" value="2" onclick="return transferOfIPD(this);">Room/Bed
				<input type="radio" name="strTransferUnit" value="3" onclick="return transferOfIPD(this);">Service Area/OT
				
				<!-- <input type="radio" name="strTransferUnit" value="6" onclick="return transferOf(this);">Unit  -->
			 
			 	<!-- Transfer To <select style='cursor: pointer; cursor: hand' name="strTransferUnit" class="comboNormal" dir="ltr"
				title="Transfer to/between Clinical Unit" onChange="hideUnitChange();return transferOf(this);">
				<option value="1" title="Transfer to/between Ward">Department/Ward</option>
				<option value="2" title="Transfer to/between Bed">Room/Bed</option> -->
				<!-- option value="3" title="Transfer to/between Deptt./Unit">Deptt./Unit</option>
				<option value="4" title="Transfer to/between Service Area">Service Area</option> -->
				<!-- </select> -->
				
			</td>
			<td width="25%" class="TITLE" style="display:none;"><div align="right" id="changeUnitId" style="display: none;">Change Unit&nbsp;<input type="checkbox" name="changeUnit" value="1" onclick="enableUnitCombo(this)">
			</div></td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" id="divTrasTypeDtl" align="center" border='0' cellspacing='1px'>
		<tr id="divBelongingDtl">
			<td width="25%" class="LABEL" nowrap="nowrap">Transfer Type for Belonging</td>
			<td width="75%" class="CONTROL">
			<input type="radio" name="strTransferTypeBelonging" value=0  checked="checked">Without Belonging<input type="radio" name="strTransferTypeBelonging" value=1> With Belonging </td>
		</tr>
		<tr id="divIssuedDtl">
			<td width="25%" class="LABEL" nowrap="nowrap">Transfer Type for Issued Item</td>
			<td width="75%" class="CONTROL"><input type="radio"
				name="strTransferTypeIssue" value=0  checked="checked">Without Items<input type="radio"
				name="strTransferTypeIssue" value=1>With Items</td>
		</tr>
	</table>
	<div style="display: none;" id="id1"></div>

	<div id="disBnR" style="display: none">
	<table class="TABLEWIDTH" border="0" align="center" border='0'
		cellspacing='1px'>
		<tr>
			<td width='25%' class='LABEL'>
			<div align="right"><font color='red'>*</font>Advised By</div>
			</td>
			<td width="75%" class="CONTROL">
			<div id="idDisBy"><select style='cursor: pointer; cursor: hand' title="List of Doctors" name="strRmk" id="strRmk">
				<bean:write name="patientMoveTransBean" property="strRmk" filter="false" />
			</select></div></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">
			<div align="right" id="reasonID">Reason For Transfer</div>
			</td>
			<td width="75%" class="CONTROL"><textarea name="strRsn"
				cols="20" rows=""></textarea></td>
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
			<td align="center"><!-- <img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/btn-sv.png" title="To save the Record"
				onClick="return saveIPD();"> <img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/btn-ccl.png"
				title="Go back to the Main Page" onClick="closeTabOnDesk();">
				 -->
				<br><a href="#" class="button" id="" onClick="return saveIPD();" ><span class="save">Save</span></a>
			<a href="#" class="button" onClick="closeTabOnDesk();"><span class="cancel">Cancel</span></a>
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
	<div id="strLocationId" style="display: none;"><bean:write
		property="strHospCombo" name="patientMoveTransBean" filter="false"></bean:write>
	</div>
	<input type="hidden" name="strTempVal" value="">
	
	<input type="hidden" name="strTime"
		value="${patientMoveTransBean.strTime}">
	<input type="hidden" name="strPrevDblOcc"
		value="${patientMoveTransBean.strPrevDblOcc}">	
	<input type="hidden" name="strHospChange"
		value="${patientMoveTransBean.strHospChange}">
	<input type="hidden" name="strHoldRoom"
		value="${patientMoveTransBean.strHoldRoom}">
	<input type="hidden" name="strErrMsgString"
		value="${patientMoveTransBean.strErrMsgString}">
	<input type="hidden" name="strNormalMsgString"
		value="${patientMoveTransBean.strNormalMsgString}">
	<input type="hidden" name="strMlc" value="1000334567">
	<input type="hidden" name="strPopUp_id">
	<input type="hidden" name="strPvtWardCode"
		value="${patientMoveTransBean.strPvtWardCode}">
	<input type="hidden" name="cnt" value="PatientTransferTransCNT.cnt">
	<input type="hidden" name="strBId"
		value="${patientMoveTransBean.strBId}">
	<input type="hidden" name="strIpdConfIcuWard"
		value="${patientMoveTransBean.strIpdConfIcuWard }">
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strAge" value="">
	<input type=hidden name=strIssuedItemRequired value='${patientMoveTransBean.strIssuedItemRequired }'>
	<input type=hidden name=strBelongingRequired value='${patientMoveTransBean.strBelongingRequired }'>
	<input type="hidden" name="strCurrentDeptUnitRoom">
	<input type="hidden" name="strValidatedFlag" >
	<input type='hidden' name='combo' value=''/>
	<input type='hidden' name='combo' value=''/>
	<input type='hidden' name='combo' value=''/>
	<input type='hidden' name='combo' value=''/>
	<input type='hidden' name="strConsultant" value="${patientMoveTransBean.strConsultant }">
	<!--  <cmbPers:cmbPers /> is Not Working here..-->
	<!--<cmbPers:cmbPers />-->
</html:form>
<script>
	if(document.getElementsByName("strIssuedItemRequired")[0].value==0)
		document.getElementById("divIssuedDtl").style.display="none";
	if(document.getElementsByName("strBelongingRequired")[0].value==0)
		document.getElementById("divBelongingDtl").style.display="none";
	
	
	hlpOnLoadIPD();
	//setAdvisedBy();

//if(document.getElementsByName("strCrNo")[0].value.length>=12 && document.getElementsByName("strDischargeType")[0].value==2)
//	getICDDetails();
</script>

</body>
</html>