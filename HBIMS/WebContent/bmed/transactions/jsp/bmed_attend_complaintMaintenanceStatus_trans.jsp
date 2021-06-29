<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Aritra Kumar Dhawa
 * Date of Creation : 22-Feb-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
<html>
<head>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
 <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />



 <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:css src="/hisglobal/css/popup.css" />

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript  src="/hisglobal/js/time.js" />

<his:javascript
	src="/bmed/transactions/js/bmed_attend_complaintMaintenanceStatus_trans.js" />
</head>
<body marginheight="0" marginwidth="0">
<html:form name="complaintMaintenanceStatusBean"
	action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Attend">
		</his:TitleTag>

		<his:SubTitleTag name="Complaint Details">

		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Department Name</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strDeptName}</td>
					<td width="25%" class="LABEL_TD">Store Name</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strStoreName}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Item Name</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strItemName}</td>
					<td class="LABEL_TD">Batch No.</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Serial No</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strItemSerialNo}</td>
					<td width="25%" class="LABEL_TD">Manufacturer Serial No.</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strManufacturerSerialNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" colspan="3">${complaintMaintenanceStatusBean.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Warranty Details">

		</his:SubTitleTag>

		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strWarrantyDetailsTable" filter="false" />
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">

		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strMaintenanceContractDetailsTable" filter="false" />
			</table>
		</his:ContentTag>


		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show"
						src="/HBIMS/hisglobal/images/plus.gif"
						onclick="tableShow('schedulesDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;">Schedules Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Schedules Details">
			<table class="TABLE_STYLE" id="schedulesDetailsTable"
				style="display: none;">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strSchedulesDetailsTable" filter="false" />
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show"
						src="/HBIMS/hisglobal/images/plus.gif"
						onclick="tableShow('attenderDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;">Attender Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Attender Details">
			<table class="TABLE_STYLE" id="attenderDetailsTable"
				style="display: none;">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strAttenderDetailsTable" filter="false" />
			</table>
		</his:ContentTag>
		<his:SubTitleTag name="Attend Details">

		</his:SubTitleTag>
		<his:ContentTag name="attendDetails">
			<table class="TABLE_STYLE" id="attendDetailsTable">
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Service
					Engineer Name</td>
					<td class="CONTROL_TD"><input type="text"
						name="strServiceEngineerName" class='TEXT_FIELD_MAX'
						maxlength="100" onkeypress="return validateData(event,4);"
						onchange="return validateData(event,4);"></td>
					<td class="LABEL_TD"><font color="red">*</font>Contact No.</td>
					<td class="CONTROL_TD"><input type="text" name="strContactNo"
						class='TEXT_FIELD_MAX' value="" maxlength="25"
						onkeypress="return validateData(event,2);"
						onchange="return validateData(event,2);"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Attend
					Date</td>
					<td width="25%" class="CONTROL_TD"><his:date
						name="strAttendDate" value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Attend
					Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						name="strAttendTime" class="TEXT_FIELD_MIN" maxlength="5" 						
						onkeypress='return checkTime(event,this);'>&nbsp;[HH:MM]
					24hr</td>
				</tr>
				<tr>
					<td class="LABEL_TD" colspan="2"><font color="red">*</font>Actual
					Problem Description</td>
					<td class="CONTROL_TD" colspan="2"><textarea rows="2"
						cols="30" name="strProblemDescription"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>

				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Is Item/Spare Part Moved Out</td>
					<td width="25%" class="CONTROL_TD"><input type="checkbox"
						value="1" name="strItemOrSparePartMovedOut"
						onchange="hideOrShowGatePass(this);"></td>
					<td width="25%" class="LABEL_TD"><span id="getPassLebel"
						style="display: none;">Gate Pass No.</span></td>
					<td width="25%" class="CONTROL_TD"><span id="getPassControl"
						style="display: none;"><input type="text"
						name="strGatePassNo" class='TEXT_FIELD_MAX' maxlength="50"
						onkeypress="return validateData(event,9);"
						onchange="return validateData(event,9);"></span></td>
				</tr>

			</table>
		</his:ContentTag>
		<his:SubTitleTag name="Spare Part Maintenace Status">

		</his:SubTitleTag>
		<his:ContentTag name="sparePartMaintenaceStatus">
			<table class="TABLE_STYLE" id="sparePartMaintenaceStatusTable">
				<tr>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Spare
					Part Name</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Manufacturer
					Name</td>
					<td width="20%" class="LABEL_TD" style="text-align: center;">Manufacturer
					Id</td>
					<td width="20%" class="LABEL_TD" style="text-align: center;">Item
					Serial No.</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Status</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">&nbsp;</td>
				</tr>
				<bean:write name="complaintMaintenanceStatusBean"
					property="strSparePartMaintenaceStatusTableRow" filter="false" />
				<tr>
					<td class="CONTROL_TD" colspan="1" style="text-align: center;">&nbsp;</td>
					<td class="CONTROL_TD" colspan="4" style="text-align: center;"><span
						id="divSparePartAddRepairOrReplace" class="popup"
						style="display: none;"></span><img class="button" tabindex="1"
						src='<his:path src="/hisglobal/images/btn-add.png"/>' title="Add"
						style="cursor: pointer"
						onclick="addSparePartsDetails(this.parentNode);"
						onkeypress="if(event.keyCode==13) addSparePartsDetails(this.parentNode);" /></td>
					<td class="CONTROL_TD" colspan="1" style="text-align: center;">&nbsp;</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ContentTag name="otherDetails">
			<table class="TABLE_STYLE" id="otherDetailsTable" >
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Solution Provided</td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25" name="strSolutionProvided"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					<td width="25%" class="LABEL_TD">Tasks</td>
					<td width="25%" class="CONTROL_TD">
					<span id="taskDivMain" class="popup" style="display: none;"></span>
					<input type="checkbox" value="1" name="strTasksChecked" onchange="displayTaskPopup(this);"></td>

				</tr>
				<tr>
					<td class="LABEL_TD" colspan="2"><font color="red">*</font>Item is in Working Condition</td>
					<td class="CONTROL_TD" colspan="2"><input type="radio"
						name="strWorkingCondition" value="1">Yes<input
						type="radio" name="strWorkingCondition" value="0">No</td>

				</tr>
				
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>From
					Date</td>
					<td width="25%" class="CONTROL_TD"><his:date
						name="strFromDate" value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>From
					Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						name="strFromTime" class="TEXT_FIELD_MIN" maxlength="5" 						
						onkeypress='return checkTime(event,this);'>&nbsp;[HH:MM]
					24hr</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>To
					Date</td>
					<td width="25%" class="CONTROL_TD"><his:date name="strToDate"
						value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>To
					Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						name="strToTime" class="TEXT_FIELD_MIN" maxlength="5" 						
						onkeypress='return checkTime(event,this);'>&nbsp;[HH:MM]
					24hr</td>
				</tr>
				<tr>
					<td class="LABEL_TD" colspan="2"><font color="red">*</font>Visit  </td>
					<td class="CONTROL_TD" colspan="2">
					<input type="radio" name="strWorkStatus" value="1" onchange="showOrHideSparePartReqRow(this);">Yes
					<input type="radio" name="strWorkStatus" value="2" onchange="showOrHideSparePartReqRow(this);">No</td>
				</tr>
				
				<tr>
					<td class="LABEL_TD" colspan="1">Is Cost Involved </td>
					<td class="CONTROL_TD" colspan="1">
						<input type="checkbox" name="strIsCostInvolved" value="1" onclick="isCostInvolved(this);">Yes	
					</td>
										
					<td class="LABEL_TD" width="25%" colspan="1"><div id="costInvolvedDivId1" style="display: none;"><font color="red">*</font>Cost</div></td>					
					<td class="CONTROL_TD" colspan="1">
						<div id="costInvolvedDivId2" style="display: none;">
							<input type="text" name="strCost" maxlength="9" onkeypress="return validateData(event,5);" >
						</div>	
					</td>									
				</tr>	
				
				<tr >
					<td class="LABEL_TD" colspan="1"><div id="costInvolvedDivId3" style="display: none;" >Bill No.</div></td>
					
					<td class="CONTROL_TD" colspan="1">
						<div id="costInvolvedDivId4" style="display: none;" >
							<input type="text" name="strBillNo" maxlength="25" onkeypress="return validateData(event,8);">
						</div>		
					</td>
														
					<td class="LABEL_TD" colspan="1"><div id="costInvolvedDivId5" style="display: none;" >Bill Date</div></td>					
					
					<td class="CONTROL_TD" colspan="1">	
						<div id="costInvolvedDivId6" style="display: none;" >					
							<his:date name="strBillDate" value=""></his:date>
						</div>							
					</td>									
				</tr>
							
				
				<tr id="sparePartReqRowId" style="display: none;">
					<td class="LABEL_TD" colspan="2">Spare Parts Required</td>
					<td class="CONTROL_TD" colspan="2">
					<input type="checkbox" value="1" name="strSparePartsRequired"></td>
				</tr>
				
				<tr>
					<td class="LABEL_TD" colspan="2"><font color="red">*</font>Attendent Remarks</td>
					<td class="CONTROL_TD" colspan="2"><textarea rows="2"
						cols="30" name="strRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>

				</tr>
			</table>
		</his:ContentTag>
		<span id="sparePartAddRepairOrReplace" style="display: none;">
		<his:SubTitleTag name="Spare Parts Details">
			<img alt="Close" src="/HBIMS/hisglobal/images/stop.png" onclick="cancelSparePartAddPopup();">
		</his:SubTitleTag> <his:ContentTag name="sparePartsDetails">
			<table class="TABLE_STYLE" id="sparePartsDetailsTable" style="width: 600px;">
				<tr>
					<td class="LABEL_TD" width="50%">Spare Part Status</td>
					<td class="CONTROL_TD" width="50%">
						<select name="strSparePartStatusId" id="strSparePartStatusId"
							class="COMBO_NORMAL" onchange="changeSparePartAddPopup(this);">
							<bean:write name="complaintMaintenanceStatusBean" property="strSparePartStatusOptions" filter="false" />
						</select>
					</td>

				</tr>
			</table>
		</his:ContentTag> <!-- Spare Part Stock Details Start --> <span
			id="sparePartStockDetails" style="display: none;"> <his:SubTitleTag
			name="Spare-part Stock Details">

		</his:SubTitleTag> <his:ContentTag name="sparePartStockDetails">
			<table class="TABLE_STYLE" id="sparePartStockDetailsTable">
				<tr>
					<td class="LABEL_TD" width="4%" style="text-align: center;">&nbsp;</td>
					<td class="LABEL_TD" width="24%" style="text-align: center;">Spare-part
					Name</td>
					<td class="LABEL_TD" width="24%" style="text-align: center;">Item
					Serial No.</td>
					<td class="LABEL_TD" width="24%" style="text-align: center;">Batch
					No.</td>
					<td class="LABEL_TD" width="24%" style="text-align: center;">Manufacture
					No.</td>
				</tr>
				<%-- 
				<tr>
					<td class="CONTROL_TD" style="text-align: center;"><input
						type="radio" name="strSparePartStockDetailsRadio" value="Aritra"
						onclick="showAddSparePartDetailsInReplaceMode('Aritra')"></td>
					<td class="CONTROL_TD" style="text-align: center;">RAM</td>
					<td class="CONTROL_TD" style="text-align: center;">CDAC/HIS/11/0210</td>
					<td class="CONTROL_TD" style="text-align: center;">ASB</td>
					<td class="CONTROL_TD" style="text-align: center;">AA2010</td>
				</tr>
				<tr>
					<td class="CONTROL_TD" style="text-align: center;"><input
						type="radio" name="strSparePartStockDetailsRadio" value="Kimba"
						onclick="showAddSparePartDetailsInReplaceMode('Kimba')"></td>
					<td class="CONTROL_TD" style="text-align: center;">HDD</td>
					<td class="CONTROL_TD" style="text-align: center;">CDAC/HIS/11/0210</td>
					<td class="CONTROL_TD" style="text-align: center;">TM-1024</td>
					<td class="CONTROL_TD" style="text-align: center;">SN125623P8</td>
				</tr>
				--%>
				<bean:write name="complaintMaintenanceStatusBean"
					property="strSparePartDetailsTable" filter="false" />
			</table>
		</his:ContentTag> </span> <!-- Spare Part Stock Details End --> <!-- Add Spare part Detail Start -->

		<span id="addSparePartDetails" style="display: none;"> <his:SubTitleTag
			name="Add Spare part Detail">

		</his:SubTitleTag> <his:ContentTag name="addSparePartDetails">
			<table class="TABLE_STYLE" id="addSparePartDetailsTable">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Spare
					Part Name</td>
					<td width="25%" class="CONTROL_TD"><span id="sparePartNameDiv"
						style="font: inherit;"></span><span id="strSparePartIdCombo"><select
						name="strSparePartId" id="strSparePartId" class="COMBO_NORMAL">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strSparePartNameOptions" filter="false" />
					</select></span></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Item
					Serial No.</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						name="strSpareItemSerialNo" id="strSpareItemSerialNo"
						class='TEXT_FIELD_MAX' value="" maxlength="20"
						onkeypress="return validateData(event,9);"
						onchange="return validateData(event,9);"></td>

				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Manufacture
					Name</td>
					<td class="CONTROL_TD"><select class="COMBO_NORMAL"
						name="strSpareManufacturerId" id="strSpareManufacturerId">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strManufactureNameOptions" filter="false" />
					</select></td>
					<td class="LABEL_TD"><font color="red">*</font>Manufacture
					Serial No</td>
					<td class="CONTROL_TD"><input type="text"
						name="strSpareManufactureSerialNo"
						id="strSpareManufactureSerialNo" class="TEXT_FIELD_MAX"
						maxlength="20" onchange="return validateData(event,9);"
						onkeypress="return validateData(event,9);"></td>

				</tr>
				<tr>
					<td colspan="2" class="LABEL_TD"><font color="red">*</font>Warranty
					From Date</td>
					<td colspan="2" class="CONTROL_TD"><input type="text"
						name="strWarrantyFromDateDD" id="strWarrantyFromDateDD"
						class="TEXT_FIELD_SMALL" maxlength="2"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);">-<select
						class="COMBO_SMALL" name="strWarrantyFromDateMM"
						id="strWarrantyFromDateMM">
						<option value="01">Jan</option>
						<option value="02">Feb</option>
						<option value="03">Mar</option>
						<option value="04">Apr</option>
						<option value="05">May</option>
						<option value="06">Jun</option>
						<option value="07">Jul</option>
						<option value="08">Aug</option>
						<option value="09">Sep</option>
						<option value="10">Oct</option>
						<option value="11">Nov</option>
						<option value="12">Dec</option>
					</select>-<input type="text" name="strWarrantyFromDateYYYY"
						id="strWarrantyFromDateYYYY" class="TEXT_FIELD_MIN" maxlength="4"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);">&nbsp;[DD-Mon-YYYY]</td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Warranty Upto</td>
					<td class="CONTROL_TD"><input type="text"
						name="strWarrantyUpto" id="strWarrantyUpto" class="TEXT_FIELD_MIN"
						maxlength="3" onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);"></td>
					<td class="LABEL_TD"><font color="red">*</font>Warranty Upto
					Unit</td>
					<td class="CONTROL_TD"><select class="COMBO_NORMAL"
						name="strWarrantyUptoUnitId" id="strWarrantyUptoUnitId">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strWarrantyUptoUnitOptions" filter="false" />
					</select></td>
				</tr>
				<tr>
					<td class="LABEL_TD" colspan="2"><font color="red">*</font>Specification</td>
					<td class="CONTROL_TD" colspan="2"><textarea rows="2"
						cols="30" name="strSpecification" id="strSpecification"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>

				</tr>
				<tr>
					<td class="LABEL_TD" colspan="2"><font color="red">*</font>Performed
					Date</td>
					<td class="CONTROL_TD" colspan="2"><input type="text"
						name="strPerformedDateDD" id="strPerformedDateDD"
						class="TEXT_FIELD_SMALL" maxlength="2"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);">-<select
						class="COMBO_SMALL" id="strPerformedDateMM"
						name="strPerformedDateMM">
						<option value="01">Jan</option>
						<option value="02">Feb</option>
						<option value="03">Mar</option>
						<option value="04">Apr</option>
						<option value="05">May</option>
						<option value="06">Jun</option>
						<option value="07">Jul</option>
						<option value="08">Aug</option>
						<option value="09">Sep</option>
						<option value="10">Oct</option>
						<option value="11">Nov</option>
						<option value="12">Dec</option>
					</select>-<input type="text" name="strPerformedDateYYYY"
						id="strPerformedDateYYYY" class="TEXT_FIELD_MIN" maxlength="4"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);">&nbsp;[DD-Mon-YYYY]</td>

				</tr>
			</table>
		</his:ContentTag> </span> <!-- Add Spare part Detail End --> <!-- Popup Button And Footer Start -->
		<his:ContentTag>
			<table id="sparePartAddRepairOrReplaceButton" class="TABLE_STYLE"
				style="display: none;">
				<tr>
					<td class="CONTROL_TD" style="text-align: center;"><img
						class="button" tabindex="1"
						src='<his:path src="/hisglobal/images/btn-sv.png"/>' title="Save"
						style="cursor: pointer" onclick="validateSparePartAdd();"
						onkeypress="if(event.keyCode==13) validateSparePartAdd();" /><img
						class="button" tabindex="1"
						src='<his:path src="/hisglobal/images/btn-clr.png"/>'
						title="Clear" style="cursor: pointer"
						onclick="resetSparePartAddPopup();"
						onkeypress="if(event.keyCode==13) resetSparePartAddPopup();" /><img
						class="button" tabindex="1"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
						title="Cancel" style="cursor: pointer"
						onclick="cancelSparePartAddPopup();"
						onkeypress="if(event.keyCode==13) cancelSparePartAddPopup();" /></td>
				</tr>
				<tr class="FOOTER_TR">
					<td colspan="4"><font size="2" color="red">*</font> Mandatory
					Fields</td>
				</tr>
			</table>
		</his:ContentTag> <!-- Popup Button And Footer End --> </span>


		<%-- Task Div --%>

		<span id="taskDivBottom" style="display: none;"> <his:SubTitleTag name="Task Details">
		</his:SubTitleTag> <his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="20%" class="LABEL_TD" style="text-align: center;">Serial
					No</td>
					<td width="40%" class="LABEL_TD" style="text-align: center;">Task
					Name</td>
					<td width="40%" class="LABEL_TD" style="text-align: center;">Check</td>
				</tr>
				
				<%-- 
				<tr>
					<td class="CONTROL_TD" style="text-align: center;">1)</td>
					<td class="CONTROL_TD" style="text-align: center;">Wiring
					Change</td>
					<td class="CONTROL_TD" style="text-align: center;"><input
						type="checkbox" name="arrStrTaskId" value="0001"></td>
				</tr>
				<tr>
					<td class="CONTROL_TD" style="text-align: center;">2)</td>
					<td class="CONTROL_TD" style="text-align: center;">Oil Change</td>
					<td class="CONTROL_TD" style="text-align: center;"><input
						type="checkbox" name="arrStrTaskId" value="0002"></td>
				</tr>
				--%>
				<bean:write name="complaintMaintenanceStatusBean"
					property="strTaskTable" filter="false" />
			</table>
			<table id="taskDivButton" class="TABLE_STYLE">
				<tr>
					<td class="CONTROL_TD" style="text-align: center;"><img
						class="button" tabindex="1"
						src='<his:path src="/hisglobal/images/btn-ok.png"/>' title="Ok"
						style="cursor: pointer" onclick="hide_popup_menu('taskDivMain');"
						onkeypress="if(event.keyCode==13) hide_popup_menu('taskDivMain');" /></td>
				</tr>

			</table>
		</his:ContentTag>
		</span>

		<his:ContentTag name="Footer">
			<table class="TABLE_STYLE">
				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-ok.png"/>' title="Save Record"
				style="cursor: pointer"
				onclick="validateAttendDetailSave('saveAttended');"
				onkeypress="if(event.keyCode==13) validateAttendDetailSave('saveAttended');" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-clr.png"/>' title="Clear"
				style="cursor: pointer" onclick="clearAttended();"
				onkeypress="if(event.keyCode==13) clearAttended();" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>' title="Cancel"
				style="cursor: pointer" onclick="cancelAttended();"
				onkeypress="if(event.keyCode==13) cancelAttended();" />
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="complaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="complaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="complaintMaintenanceStatusBean" property="strNormalMsg" /></div>
			<br />
		<input type="hidden" name="hmode" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strComplaintId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strMainteId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strVendorId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strWarrantyFromDate" styleId="strWarrantyFromDate" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strPerformedDate" styleId="strPerformedDate" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strStoreId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strItemId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strSerialNo" />
		<%-- 	
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strBatchNo"/>
			--%>
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strManufSerialNo" />
		
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strIsHemDesk" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strHiddenComplaintId" />
			
			<html:hidden name="complaintMaintenanceStatusBean"
			property="strUploadFileId" />
		
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>