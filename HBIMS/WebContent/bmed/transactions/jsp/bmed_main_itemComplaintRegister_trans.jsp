<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%--
/**
 * Developer Name : Vivek Aggarwal
 * Process Name : Item Complaint Register
 * Date : 18/April/2011
 * Modify By/Date : 
 */ 
 --%>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:css src="/hisglobal/css/newpopup.css" />
<his:css src="/hisglobal/css/popup.css" />

<his:javascript  src="/hisglobal/masterutil/js/master.js" />
<his:javascript  src="/hisglobal/js/util.js" />
<his:javascript  src="/hisglobal/js/tab.js" />
<his:javascript  src="/hisglobal/js/calendar.js" />
<his:javascript  src="/hisglobal/js/validation.js" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript  src="/hisglobal/js/commonFunctions.js" />
<his:javascript  src="/hisglobal/js/newpopup.js" />
<his:javascript  src="/hisglobal/js/popup.js" />

<his:javascript	src="/bmed/transactions/js/bmed_main_itemComplaintRegister_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>

</head>

<body marginheight="0" marginwidth="0"  onload="onLoadFunction(),getReport();">
<html:form name="complaintMaintenanceStatusBean" action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	
	<his:TransactionContainer>
	
	<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">	
	
		<his:TitleTag name="Item Complaint Register">
		</his:TitleTag>
	</logic:equal>
	
	<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
		<his:TitleTag name="Non-Item Complaint Register">
		</his:TitleTag>
	</logic:equal>	
	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td colspan="4" class="LABEL_TD">

							<html:radio name="complaintMaintenanceStatusBean" property="strItemOrNonItemMode" value="1" onclick="changeViewMode();" /> Item
							<html:radio name="complaintMaintenanceStatusBean" property="strItemOrNonItemMode" value="0" onclick="changeViewMode();" /> Non-Item
					</td>
				</tr>
				


				<tr>					
					<td  colspan="4" class="CONTROL_TD" style="text-align: right; display: none;">

						<html:checkbox name="complaintMaintenanceStatusBean"  property="strIsAttached" value="1" onclick="showHideEmpDetails(this);getItemName();" />Is Attachment

					</td>
				</tr>
<!--	Complaint Type	-->	
			<logic:equal name="complaintMaintenanceStatusBean" property="strConfigPropertyValue" value="0">						
					<tr>				
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Complaint Type
						</td>
								
						<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">								
						<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintMaintenanceStatusBean" property="strComplaintType" value="1"  /> Internal
						</td>
					
						<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintMaintenanceStatusBean" property="strComplaintType" value="2" /> External
						</td>
						</logic:equal>
						
						<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
							
							<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintMaintenanceStatusBean" property="strComplaintType" value="2" /> External
							</td>
							
							<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintMaintenanceStatusBean" property="strComplaintType" value="1"  /> Internal
							</td>
						</logic:equal>	
							
						<td width="25%" class="CONTROL_TD"></td>
					</tr>
			</logic:equal>
				
			</table>
		</his:ContentTag>
				
		<his:TitleTag name="Employee Details">
		</his:TitleTag>	


		<his:ContentTag>		
<!--	Employee Details	-->
			
				<table class="TABLE_STYLE">	
	<!-- Employee Name & Employee Id		-->
				 	 <tr id="divEmpDetails" align="center" style="display: none">
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Employee Name</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strEmpName" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Employee Id</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strEmpId" />						
						</td>
					</tr>
	
	<!--	Department & Designation -->
					<tr id="divEmpDeptAndDesignation" align="center" style="display: none">
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Department</td>
						<td width="25%" class="CONTROL_TD">
						<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
							<select name="strDeptCode" class='COMBO_NORMAL' onchange="getItemName();emptyDetailsForItem();">
								<bean:write name="complaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
						
						<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
							<select name="strDeptCode" class='COMBO_NORMAL' >
								<bean:write name="complaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
							
						</td>				
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Designation</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strDesignation" />						
						</td>
					</tr>	
				</table>
			</his:ContentTag>	
							
<!-- Department Name & 	Store Name -->
		
			<his:ContentTag>
				<table class="TABLE_STYLE">	
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Type</td>
						<td width="25%" class="CONTROL_TD">
							<div id="engineeringItemTypeId">
							<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();">
									<bean:write name="complaintMaintenanceStatusBean" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
							<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();emptyDetailsForNonItem();">
									<bean:write name="complaintMaintenanceStatusBean" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
								
							</div>
						</td>
						
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Sub Type</td>
						<td width="25%" class="CONTROL_TD">						
							<div id="enggItemSubTypeCmbDivId">
							<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL'>
									<bean:write name="complaintMaintenanceStatusBean" property="strEngineeringItemSubTypeCmb" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</logic:equal>
								
							<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">	
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL' onchange="getNonItemName();">
										<bean:write name="complaintMaintenanceStatusBean" property="strEngineeringItemSubTypeCmb" filter="false" />
											<option value="0">Select Value</option>
								</select>
							</logic:equal>	
							</div>
						</td>
					</tr>
					<tr id="divDeptNameAndStoreDetails">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Department Name</td>
						<td width="25%" class="CONTROL_TD">
							<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">	
							
								<select name="strDeptCodeNew" class='COMBO_NORMAL' onchange="getStoreName();emptyDetailsForItem();">
									<bean:write name="complaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
							
							<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">	
							
								<select name="strDeptCodeNew" class='COMBO_NORMAL' >
									<bean:write name="complaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
						</td>

						<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">	
							<td width="25%" class="LABEL_TD">
									<font color="red">*</font>Store Name
							</td>

							<td width="25%" class="CONTROL_TD">	
								<div id="storeNameId">					
									<select name="strStoreId" class='COMBO_NORMAL' onchange="getItemNameOnBasisOfStore();emptyDetailsForItem();">
										<bean:write name="complaintMaintenanceStatusBean" property="strStoreNameCombo" filter="false" />
											
									</select>
								</div>
							</td>
						</logic:equal>
						
						<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
							<td width="25%" class="CONTROL_TD"></td>


							<td width="25%" class="CONTROL_TD"></td>
						</logic:equal>	
						
					</tr>					
				
<!-- Engineering Item Type & Engineering Item Sub-Type -->
					
			</table>
			<table class="TABLE_STYLE" align="left">	
			<tr>
					<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Item Name</td>
						<td width="75%" class="CONTROL_TD">
							<div id="itemNameId">
								<select name="strItemId" class='COMBO_NORMAL' >
									<bean:write name="complaintMaintenanceStatusBean" property="strItemNameCombo" filter="false" />
<!--										<option value="0">Select Value</option>-->
								</select>
							</div>
						</td>
					</logic:equal>
					
					<!--	Non-Item Name				-->
					<logic:equal name="complaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
						<td class="LABEL_TD"><font color="red">*</font>Non-Item Name</td>
						<td  class="CONTROL_TD">
							<div id="itemNameId">
								<select name="strNonItemId" class='COMBO_NORMAL' >
									<bean:write name="complaintMaintenanceStatusBean" property="strNonItemNameCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</div>
						</td>
					</logic:equal>
						
					</tr>
					</table>
		</his:ContentTag>
        
		<his:ContentTag>
			<div id="stockValueId"></div>
		</his:ContentTag>
		
		<his:ContentTag>
			<div id="prevCompDtlId"></div>
		</his:ContentTag>

		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag>

		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>



	<his:TitleTag name="Complaint Details">
		</his:TitleTag>	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
			
			
<!--	Is Item in Working Condition -->
				<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Is Item in Working Condition
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<html:radio name="complaintMaintenanceStatusBean" property="strIsItemInWorkingCondition" value="0" onclick="isItemInWorkingConditionchangeMode();"/> Yes
					</td>
					
					<td width="25%" class="CONTROL_TD">	
						<html:radio name="complaintMaintenanceStatusBean" property="strIsItemInWorkingCondition" value="1" onclick="isItemInWorkingConditionchangeMode();" /> NO
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				
<!--	Date & Time	-->

					<tr id="dateAndTimeId" style="display: none;">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strComplaintDate" value="${complaintMaintenanceStatusBean.strComplaintDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strComplaintTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>
			
<!--	Preferred From Time	&	Preferred To Time		-->
				<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Preferred Visiting Hours(From)
					</td>
					
					<td width="25%" class="CONTROL_TD">
					  	<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strPreferredFromTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)
					</td>
					
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Preferred Visiting Hours(To)
					</td>
					
					<td width="25%" class="CONTROL_TD">
					  	<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strPreferredToTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)
					</td>
				</tr>
				
<!--	Contact Person Name	&	Contact No.		-->

				<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Contact Person Name
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="250" name="strContactPersonName" onkeypress="return validateData(event,4);">
					</td>
					
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Contact No.
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="10" name="strContactNo" onkeypress="return validateData(event,2);">
					</td>
				</tr>
				
<!--	Land Mark Description -->

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Land Mark Description</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="complaintMaintenanceStatusBean" property="strLandMarkDescription" value="" cols="20" rows="2"  />
					</td>
<!--	Remarks -->					
				<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="complaintMaintenanceStatusBean" property="strRemarks" cols="20" rows="2"  />
					</td>
				</tr>
<!--	Complaint Description -->

				<tr>
					<td width="25%" class="LABEL_TD">Nature of Service</td>
					
					<td width="25%" class="CONTROL_TD">
						<select name="strNatureOfServiceId" class="COMBO_NORMAL" onchange="editOthers(this);">							
									<bean:write name="complaintMaintenanceStatusBean" property="strNatureOfServiceComboData" filter="false" />								
						</select>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Enter Nature of Service</td>
					<td width="25%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="50" name="strNatureOfService" disabled="disabled">
					</td>
					
					
				</tr>	
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Complaint Description</td>
					<td width="75%" class="CONTROL_TD" colspan='3'>
						<html:textarea name="complaintMaintenanceStatusBean" property="strComplaintDescription" cols="70" rows="2" onkeypress="return validateData(event,9);" />
					</td>
					
					
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
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-sv.png" onClick="return validate1();" onkeypress="if(event.keyCode==13) validate1();"/>
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-clr.png" onClick="clearPage();" onkeypress="if(event.keyCode==13) clearPage();" />
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write name="complaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="complaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write
			name="complaintMaintenanceStatusBean" property="strNormalMsg" /></div>
<br /><br />
		<input type="hidden" name="hmode" />
		
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strStockInfoVal" value="${complaintMaintenanceStatusBean.strStockInfoVal}" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${complaintMaintenanceStatusBean.strEmpName}" />
		<input type="hidden" name="strEmpId" value="${complaintMaintenanceStatusBean.strEmpId}" />
		<input type="hidden" name="strPageFlag" value="${complaintMaintenanceStatusBean.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strOldDepartmentCode" value="${complaintMaintenanceStatusBean.strDeptCode}" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strRequestNo" value="" />
		<input type="hidden" name="strComplaintId" value="${complaintMaintenanceStatusBean.strComplaintId}" />
		<input type="hidden" name="strReportFlg" value="0" />		
		<input type="hidden" name="strDeptName" value="${complaintMaintenanceStatusBean.strDeptName}" />
		<input type="hidden" name="strItemName" value="${complaintMaintenanceStatusBean.strItemName}" />
		<input type="hidden" name="strWarrantyOrMaintenanceSlNoAndType" value="${complaintMaintenanceStatusBean.strWarrantyOrMaintenanceSlNoAndType}" />
		<input type="hidden" name="strVendorName" value="${complaintMaintenanceStatusBean.strVendorName}" />
		<input type="hidden" name="strWarrantyDuration" value="${complaintMaintenanceStatusBean.strWarrantyDuration}" />
		<input type="hidden" name="strAMCStartDate" value="${complaintMaintenanceStatusBean.strAMCStartDate}" />
		<input type="hidden" name="strAMCEndDate" value="${complaintMaintenanceStatusBean.strAMCEndDate}" />
		<input type="hidden" name="strIsItemInWorkingCondition" value="${complaintMaintenanceStatusBean.strIsItemInWorkingCondition}" />
		<input type="hidden" name="strPreviousComplaintDetails" value="${complaintMaintenanceStatusBean.strPreviousComplaintDetails}" />
		
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