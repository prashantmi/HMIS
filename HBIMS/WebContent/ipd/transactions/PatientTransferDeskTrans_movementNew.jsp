
<!DOCTYPE HTML >
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

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
 <link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
 
<link href="../../ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../ipd/css/basic.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">


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
<script language="Javascript" src="../../ipd/js/PatTransferBS.js"></script>
<script language="Javascript" src="../../ipd/js/nursingDesk.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HBIMS/ipd/js/bootstrap.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<script type='text/javascript'>



document.onmousedown=selectmouse;
    document.onmouseup=new Function("isdrag=false");

</script>
</head>
<body onLoad="CNTIni(),butdis(),hlpOnLoad();setAdvisedBy();" onUnload="closePopUp();">
<html:form action="/transactions/PatientTransferTransBSCNT.cnt" method="post">

<fieldset>
	
  <legend class='legendHeader' id='nonPrintableLegend'>Patient Movement Details</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="lastbuttons" class="btn btn-outline-success mt-1 btn-circle savebtn " tabindex='2' onclick='return save();' name="patientAdmissionModiTransBean">					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>
									                 
  </div>
  	<div id="patDtlTld" style="display: none">
		<pDtl:patDtlNew crNo="${patientMoveTransBean.strCrNo}" address="false"></pDtl:patDtlNew>
	</div>
	
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtlNew crNo="${patientMoveTransBean.strCrNo}"></aDtl:addDtlNew>
		</div>
<div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  <div class="col-md-12">
  
<div class="prescriptionTile">


	<div id="menu1"></div>
	<div class="errMsg" id="errMsg"><bean:write
		name="patientMoveTransBean" property="strErrMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="patientMoveTransBean" property="strNormalMsgString" /></div>
	<%-- <tag:tab tabLabel="Patient Transfer" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab> --%>
	<div id="patTldglbdiv">
		<input type="hidden" name="strMode" value="0" />
		<input type="hidden" value="${patientMoveTransBean.strCrNo}" name="strCrNo" /> 
	  <!-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>

		<tr>
			<td colspan="4" class='TITLE'>
			<input type="radio" name="strMode" value="0" checked="checked" onclick="changeMode();">Transfer
			<input type="radio" name="strMode" value="1" onclick="changeMode();" disabled="disabled">Movement
			</td>
		</tr>
		  <tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId" style="display: none"><img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine"
				title="Show Details" onclick="showDetails();" /></div>
			<div id="minusonLineId"><img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/minus.gif" name="minusonLine"
				title="Hide Details" onclick="hideDetails();"></div>
			</td>
			<td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
		</tr>
	</table> -->
	<!--<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap">CR No.</td>
			<td width="75%" class="CONTROL"><input type="hidden"
				value="${patientMoveTransBean.strCrNo}" name="strCrNo" /> <bean:write
				name="patientMoveTransBean" property="strCrNo"></bean:write></td>
		</tr>
	</table> -->
	</div>
	
	<div id="admDtlTldglbdiv" style="display: none">
	  <!-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
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
	</table> -->
	</div>
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtlNew crNo="${patientMoveTransBean.strCrNo}"></aDtl:addDtlNew>
		</div>
	<div id="transChng" style="display: none">
	
	
	<div class="row rowFlex reFlex newrow">
	           <!--  <input type="radio" name="strTransferUnit" value="1" checked="checked" onclick="hideUnitChange();return transferOf(this);">&nbsp;Department/Ward
				&nbsp;&nbsp;<input type="radio" name="strTransferUnit" value="2" onclick="hideUnitChange();return transferOf(this);">&nbsp;Room/Bed
				&nbsp;&nbsp;<input type="radio" name="strTransferUnit" value="3" onclick="hideUnitChange();return transferOf(this);">&nbsp;vService Area/OT
				&nbsp;&nbsp;<input type="radio" name="strTransferUnit" value="6" onclick="return transferOf(this);">&nbsp;Unit  -->
				
				
	<div class="col-sm-2">
	<label class="container" style="color: white;">Department/Ward
	            <input type="radio" name="strTransferUnit" value="1" checked="checked" onclick="hideUnitChange();return transferOf(this);">
    <span class="checkmark"></span>
    </label>
    </div>
    <div class="col-sm-2">
    <label class="container" style="color: white;">Room/Bed
				<input type="radio" name="strTransferUnit" value="2" onclick="hideUnitChange();return transferOf(this);">
    <span class="checkmark"></span>
    </label>
    </div>
    <div class="col-sm-2">
    <label class="container" style="color: white;">Service Area/OT
				<input type="radio" name="strTransferUnit" value="3" onclick="hideUnitChange();return transferOf(this);">
    <span class="checkmark"></span>
    </label>
    </div>
    <div class="col-sm-2">
    <label class="container" style="color: white;">Unit
				<input type="radio" name="strTransferUnit" value="6" onclick="return transferOf(this);"> 
    <span class="checkmark"></span>
    </label>
    </div>
    <div class="col-sm-4">
    <div align="right" id="changeUnitId" style="display: none;">Change Unit&nbsp;<input type="checkbox" name="changeUnit" value="1" onclick="enableUnitCombo(this)">
			</div>
	</div>
	</div>
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<!-- <td width="25%" class="TITLE">
				<input type="radio" name="strTransferUnit" value="1" checked="checked" onclick="hideUnitChange();return transferOf(this);">Department/Ward
				<input type="radio" name="strTransferUnit" value="2" onclick="hideUnitChange();return transferOf(this);">Room/Bed
				<input type="radio" name="strTransferUnit" value="3" onclick="hideUnitChange();return transferOf(this);">Service Area/OT
				<input type="radio" name="strTransferUnit" value="6" onclick="return transferOf(this);">Unit 
			 
			 	Transfer To <select style='cursor: pointer; cursor: hand' name="strTransferUnit" class="comboNormal" dir="ltr"
				title="Transfer to/between Clinical Unit" onChange="hideUnitChange();return transferOf(this);">
				<option value="1" title="Transfer to/between Ward">Department/Ward</option>
				<option value="2" title="Transfer to/between Bed">Room/Bed</option>
				option value="3" title="Transfer to/between Deptt./Unit">Deptt./Unit</option>
				<option value="4" title="Transfer to/between Service Area">Service Area</option>
				</select>
				
			</td> -->
			<!-- <td width="25%" class="TITLE"><div align="right" id="changeUnitId" style="display: none;">Change Unit&nbsp;<input type="checkbox" name="changeUnit" value="1" onclick="enableUnitCombo(this)">
			</div></td> -->
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
	<div class="row rowFlex reFlex">
	<div class="col-sm-3" align="right"><label><font color='red'>*</font>Advised By</label></div>
	<div class="col-sm-2" id="idDisBy">
	<select tabindex='1' style='cursor: pointer; cursor: hand' title="List of Doctors" name="strRmk" id="strRmk">
				<bean:write name="patientMoveTransBean" property="strRmk" filter="false" />
			</select>
	</div>
	<div class="col-sm-7"></div>
	</div>
	<div class="row rowFlex reFlex" id="reasonID">
	<div class="col-sm-3" align="right"><label>Reason For Transfer</label></div>
	<div class="col-sm-9" >
	<textarea name="strRsn" class="form-control"></textarea>
	</div>
	</div>
	<%-- <table class="TABLEWIDTH" border="0" align="center" border='0'
		cellspacing='1px'>
		<tr>
			<td width='25%' class='LABEL'>
			<div align="right"><font color='red'>*</font>Advised By</div>
			</td>
			<td width="75%" class="CONTROL">
			<div id="idDisBy"><select tabindex='1'  title="List of Doctors" name="strRmk" id="strRmk">
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
	</table> --%>
	</div>

	<!-- <table class="TABLEWIDTH" border="0" align="center" cellspacing='1px'>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table> -->
	<!-- <table border='0' cellspacing='1px' class="TABLEWIDTH" align="center">
		<tr>
			<td align="center"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/btn-sv.png" title="To save the Record"
				onClick="return save();"> <img
				style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/btn-ccl.png"
				title="Go back to the Main Page" onClick="cancelToDesk();">
				
				<br><a href="#" class="button" id="" onClick="return save();" ><span class="save">Save</span></a>
			<a href="#" class="button" onclick="cancelToDesk();"><span class="cancel">Cancel</span></a>
				</td>
		</tr>
	</table> -->
	<hr>
	<div class="row rowFlex reFlex">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" align="right">
									<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
								</div>
							</div>
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
	<input type="hidden" name="cnt" value="">
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
	<input type='hidden' name="strConsultant" value="${patientMoveTransBean.strConsultant }">
	<cmbPers:cmbPers />
	</div>
	</div>
	</div>
	</div>
	</fieldset>
</html:form>
<script>
	if(document.getElementsByName("strIssuedItemRequired")[0].value==0)
		document.getElementById("divIssuedDtl").style.display="none";
	if(document.getElementsByName("strBelongingRequired")[0].value==0)
		document.getElementById("divBelongingDtl").style.display="none";

//if(document.getElementsByName("strCrNo")[0].value.length>=12 && document.getElementsByName("strDischargeType")[0].value==2)
//	getICDDetails();
</script>

</body>
</html>