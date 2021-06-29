<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%--
/**
 * Developer Name : Saratkumar Thokchom/Partha P Chattaraj
 * Process Name : Engineer Desk
 * Date : 04/Sept/2013
 * Modify By/Date : 
 */ 
 --%>

<html>
<head>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />

<his:javascript  src="/hisglobal/masterutil/js/master.js" />
<his:javascript  src="/hisglobal/js/util.js" />
<his:javascript  src="/hisglobal/js/tab.js" />
<his:javascript  src="/hisglobal/js/calendar.js" />
<his:javascript  src="/hisglobal/js/validation.js" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript  src="/hisglobal/js/commonFunctions.js" />
<his:javascript  src="/hisglobal/js/popup.js" />
<his:javascript  src="/hisglobal/js/utilityFunctions.js" />
<his:javascript	src="/bmed/transactions/js/bmed_engineer_desk_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>

</head>

<body marginheight="0" marginwidth="0"  onload="chkRecordSaved();">
<html:form name="hemmsEngineerDeskFB" action="/transactions/HemmsEngineerDeskACTION"
	type="bmed.transactions.controller.fb.HemmsEngineerDeskFB">
	
	<his:TransactionContainer>
	
	<logic:equal name="hemmsEngineerDeskFB" property="strPageFlag" value="ITEM">	
	
		<his:TitleTag name="Engineer Desk">
		</his:TitleTag>
	</logic:equal>
	
	<logic:equal name="hemmsEngineerDeskFB" property="strPageFlag" value="NON_ITEM">
		<his:TitleTag name="Engineer Desk">
		</his:TitleTag>
	</logic:equal>	
	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td colspan="4" class="LABEL_TD">

							<html:radio name="hemmsEngineerDeskFB" property="strItemOrNonItemMode" value="1" onclick="changeViewMode();" /> Item
							<!-- html:radio name="hemmsEngineerDeskFB" property="strItemOrNonItemMode" value="0" onclick="changeViewMode();" / --> <!-- Non-Item -->
					</td>
				</tr>
		</table>
		</his:ContentTag>		

<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Type</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintType}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Hospital Name</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strStoreName}</td>
					<td width="25%" class="LABEL_TD">Lab Name</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strLabName}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Engineering Item Type</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEnggItemType}</td>
					<td width="25%" class="LABEL_TD">Engineering Item Sub Type</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEnggItemSubType}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Equipment Name</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemName}</td>
					<td class="LABEL_TD">Equipment Model</td>
					<!--<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemModel}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">UIN</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strEquipmentUIDNo}</td>
					<td width="25%" class="LABEL_TD">Item Serial No.</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemSerialNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Manufacturer/Supplier Name</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strManufacturerName}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" colspan="3">${hemmsEngineerDeskFB.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('warrantyDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Guarantee Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Guarantee Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable" style="display: none;">
				<bean:write name="hemmsEngineerDeskFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('acknowledgeDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Acknowledgment and Approval Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Complaint Acknowledgment">
			<table class="TABLE_STYLE" id="acknowledgeDetailsTable" style="display: none;" >
			     <tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIsAcknowledge}
			</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strRemarks}</td>
			
		</tr>
		<tr>
					<td width="25%" class="LABEL_TD">Complaint Nature</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL_TD">Action Taken</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strActionTaken}</td>
			</tr>     
			     <tr>
			<td width="25%" class="LABEL_TD" >Approved</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIsApproved}</td>

					<td width="25%" class="LABEL_TD" >Remarks</td>
					<td width="25%" class="CONTROL_TD" >${hemmsEngineerDeskFB.strRemarks}</td>
				</tr>
		</table>
		</his:ContentTag>
		<his:ContentTag name="HEMM Acknowledgment">
			<table class="TABLE_STYLE" style="display: none;">
			<tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strAckRemarks}</td>
			</tr>
			     
			</table>
		</his:ContentTag>	
		
		
		<%-- ************** Schedule Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('scheduleDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >HEMM Schedule Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Schedule Details">
			<table class="TABLE_STYLE" id="scheduleDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Expected Schedule Date
					</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strScheduleDate}</td>
					<td width="25%" class="LABEL_TD">Schedule
					Time</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strScheduleTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Service Engineer Remarks</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strServiceEngineerRemarks}</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Intimation
					Date</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIntimationDate}</td>
					<td width="25%" class="LABEL_TD">Intimation
					Time</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIntimationTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Contact Person</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strContactPerson}</td>
					<td class="LABEL_TD">Contact No.</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strContactNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Company Name</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strCompanyName}</td>
					<td class="LABEL_TD">Company Address</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strCompanyAddress}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Vendor Id</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strVendorId}</td>
					<td class="LABEL_TD">Communication
					Id</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strCommunicationId}</td>
				</tr>
				<tr>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${hemmsEngineerDeskFB.strServiceProviderRemarks}</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
		</his:ContentTag> 
		<%--======================== Schedule Details End ========================= --%>	
		<%-- ************** Attend Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('attendDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >HEMM Attend Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag  name="Attend Details">
		
			<table class="TABLE_STYLE" id="attendDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL_TD">Service Engineer Address</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEngineerAddress}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Mobile Number</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strMobileNo}</td>
					<td class="CONTROL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Attend
					Date</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strHEMAttendDate}</td>
					<td width="25%" class="LABEL_TD">Attend
					Time</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strHEMAttendTime}&nbsp;[HH:MM]
					24hr</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Actual Problem Desc.</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strActualProblemDesc}</td>
					<td class="CONTROL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
				<tr>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${hemmsEngineerDeskFB.strAttendRemarks}</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
			
		</his:ContentTag> 
		
		<%--======================== Attend Details End ========================= --%>
		
		<logic:empty name="hemmsEngineerDeskFB" property="strWarrantyDetailsTable">
		
				<his:SubTitleTag name="Repaied By Vendor"><html:checkbox name="hemmsEngineerDeskFB" property="isRepairedByVendor" onclick="enableVendorDetails();"></html:checkbox>
		</his:SubTitleTag>
		<table class="TABLE_STYLE">
		
	
		<tr>
		<td width="25%" class="LABEL_TD">Vendor Name</td>
						<td width="25%" class="CONTROL_TD">
						<div id="strVendorId">
								<select name="strVendorId" class='COMBO_NORMAL' onchange="getVenderDetails();"  >
									<bean:write name="hemmsEngineerDeskFB" property="strVenderCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</div>
						
						</td>
						
						
						<td width="25%" class="LABEL_TD">Contact No</td>
	
	
						<td width="25%" class="CONTROL_TD"><div id="vendorcontactNoDivId"> </div></td>
		
		</tr>
		<tr >
					<td width="25%" class="LABEL_TD">Address</td>
	
	
						<td width="25%" class="CONTROL_TD"><div id="vendorAddrDivId"> </div></td>
						
						<td width="25%" class="LABEL_TD">Invoice No.</td>
	
	
						<td width="25%" class="CONTROL_TD">
						
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strInvoiceNo" onkeypress="return validateData(event,2);">
						</td>
		
		</tr>
		</table>
		</logic:empty>


		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Maintenance Contract Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable" style="display: none;">
				<bean:write name="hemmsEngineerDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

	<his:SubTitleTag name="Engineer Acknowledgment Desk">
		</his:SubTitleTag>	
		
			<his:ContentTag name="HEMM Acknowledgment">
			<table class="TABLE_STYLE">
			     <tr>
			<td width="50%" class="LABEL_TD"><font color="red">*</font>Acknowledgment</td>
			<td width="50%" class="CONTROL_TD">
			   <input type="radio" name="strIsHEMAcknowledge"  value="1" />Yes&nbsp;&nbsp;
			   <input type="radio" name="strIsHEMAcknowledge" value="2" />No
			</td>

			
		</tr>
		
		    <tr>
					<td width="50%" class="LABEL_TD"><font color="red">*</font>Remarks</td>
					<td width="50%" class="CONTROL_TD"><textarea rows="2" cols="20" name="strAckRemarks" onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea> </td>
				</tr>
			     
			</table>
		</his:ContentTag>				

		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-sv.png" onClick="return validate1();" onkeypress="if(event.keyCode==13) validate1();"/>
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="clearPage();" onkeypress="if(event.keyCode==13) clearPage();" />
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write
			name="hemmsEngineerDeskFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="hemmsEngineerDeskFB" property="strWarningMsg" /></div>
		<div style="display:none;" class="NORMAL_DIV" id="normalMsg"><bean:write
			name="hemmsEngineerDeskFB" property="strNormalMsg" /></div>

		<input type="hidden" name="hmode" />
		<input type="hidden" name="strPath" value="${hemmsEngineerDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hemmsEngineerDeskFB.strChk}">
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${hemmsEngineerDeskFB.strEmpName}" />
		<input type="hidden" name="strOldDepartmentCode" value="${hemmsEngineerDeskFB.strDeptCode}" />
		<input type="hidden" name="strPageFlag" value="${hemmsEngineerDeskFB.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strHemmsEngineerDesk" value="${hemmsEngineerDeskFB.strHemmsEngineerDesk}" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strAttendedContactPerson" value="" />
		<html:hidden name="hemmsEngineerDeskFB" property="strSerialNo" value="0"/>
		<html:hidden name="hemmsEngineerDeskFB" property="strSparePartsFlag" value="0"/>
		<input type="hidden" name="strRetValue" value="${hemmsEngineerDeskFB.strRetValue}" />
		<input type="hidden" name="strMsgString" value="${hemmsEngineerDeskFB.strMsgString}" />
		
		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>

				<div id="stockDtlsDivId" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
	</his:TransactionContainer>
</html:form>
</body>
</html>