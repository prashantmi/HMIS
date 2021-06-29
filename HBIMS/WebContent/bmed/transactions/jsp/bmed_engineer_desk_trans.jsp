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
 * Developer Name : Saratkumar Thokchom
 * Process Name : Engineer Desk
 * Date : 10/July/2013
 * Modify By/Date : 
 */ 
 --%>

<html>
<head>
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
<his:javascript	src="/bmed/transactions/js/bmed_complaint_log_offline_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>

</head>

<body marginheight="0" marginwidth="0"  onload="onLoadFunction();">
<html:form name="complaintLogOfflineBean" action="/transactions/ComplaintLogOfflineACTION"
	type="bmed.transactions.controller.fb.ComplaintLogOfflineFB">
	
	<his:TransactionContainer>
	
	<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">	
	
		<his:TitleTag name="Engineer Desk">
		</his:TitleTag>
	</logic:equal>
	
	<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">
		<his:TitleTag name="Engineer Desk">
		</his:TitleTag>
	</logic:equal>	
	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td colspan="4" class="LABEL_TD">

							<html:radio name="complaintLogOfflineBean" property="strItemOrNonItemMode" value="1" onclick="changeViewMode();" /> Item
							<!-- html:radio name="complaintLogOfflineBean" property="strItemOrNonItemMode" value="0" onclick="changeViewMode();" / --> <!-- Non-Item -->
					</td>
				</tr>
				


			<!-- <tr>					
					<td  colspan="4" class="CONTROL_TD" style="text-align: right;">

						<html:checkbox name="complaintLogOfflineBean"  property="strIsAttached" value="1" onclick="showHideEmpDetails(this);" />Is Attachment

					</td>
				</tr>  -->
<!--	Complaint Type	-->	
			<logic:equal name="complaintLogOfflineBean" property="strConfigPropertyValue" value="0">						
					<tr>				
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Complaint Type
						</td>
								
						<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">								
						<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintLogOfflineBean" property="strComplaintType" value="1"  onclick="getstrComplaintType()"/> Internal
						</td>
					
						<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintLogOfflineBean" property="strComplaintType" value="2" onclick="getstrComplaintType()" /> External
						</td>
						</logic:equal>
						
						<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">
							
							<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintLogOfflineBean" property="strComplaintType" value="2" onclick="getstrComplaintType()" /> External
							</td>
							
							<td width="25%" class="CONTROL_TD">
								<html:radio name="complaintLogOfflineBean" property="strComplaintType" value="1" onclick="getstrComplaintType()"  /> Internal
							</td>
						</logic:equal>	
							
						<td width="25%" class="CONTROL_TD"></td>
					</tr>
			</logic:equal>
				
			</table>
		</his:ContentTag>

							
<!-- Department Name & 	Store Name -->
		
			<his:ContentTag>
				<table class="TABLE_STYLE">	
					<tr id="divDeptNameAndStoreDetails">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Hospital Name</td>
						<td width="25%" class="CONTROL_TD">
							<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">	
							
								<select name="strDeptCodeNew" class='COMBO_NORMAL' onchange="getStoreName();emptyDetailsForItem();">
									<bean:write name="complaintLogOfflineBean" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
							
							<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">	
							
								<select name="strDeptCodeNew" class='COMBO_NORMAL' >
									<bean:write name="complaintLogOfflineBean" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
						</td>

						<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">	
							<td width="25%" class="LABEL_TD">
									<font color="red">*</font>Lab Name
							</td>

							<td width="25%" class="CONTROL_TD">	
								<div id="storeNameId">					
									<select name="strStoreId" class='COMBO_NORMAL' onchange="getItemNameOnBasisOfStore();emptyDetailsForItem();">
										<bean:write name="complaintLogOfflineBean" property="strStoreNameCombo" filter="false" />
											
									</select>
								</div>
							</td>
						</logic:equal>
						
						<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">
							<td width="25%" class="CONTROL_TD"></td>


							<td width="25%" class="CONTROL_TD"></td>
						</logic:equal>	
						
					</tr>		
					
					
					<!--	Department & Designation -->
					<tr id="divEmpDeptAndDesignation" align="center" style="display: none">
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Hospital</td>
						<td width="25%" class="CONTROL_TD">
						<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">
							<select name="strDeptCode" class='COMBO_NORMAL' onchange="getItemName();emptyDetailsForItem();">
								<bean:write name="complaintLogOfflineBean" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
						
						<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">
							<select name="strDeptCode" class='COMBO_NORMAL' >
								<bean:write name="complaintLogOfflineBean" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
							
						</td>				
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Designation</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintLogOfflineBean" property="strDesignation" />						
						</td>
					</tr>
					
					
					<!-- Employee Name & Employee Id		-->
				 	 <tr id="divEmpDetails" align="center" >
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Complainer  Name</td>
						<td width="25%" class="CONTROL_TD">
							<html:hidden name="complaintLogOfflineBean" property="strEmpName" />			
							<div id="strEmpDivId">
							<select name="strEmpId" class='COMBO_NORMAL' onchange="getEmpDesignation();">
								<bean:write name="complaintLogOfflineBean" property="strEmpComboOptions" filter="false" />
									
							</select>
							</div>
										
						</td>
					
							<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Designation</td>
						<td width="25%" class="CONTROL_TD">
						<div  id="strDesignationDivId" >		
							</div>					
						</td>
					</tr>	
					
								
				
<!-- Engineering Item Type & Engineering Item Sub-Type -->
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Type</td>
						<td width="25%" class="CONTROL_TD">
							<div id="engineeringItemTypeId">
							<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();">
									<bean:write name="complaintLogOfflineBean" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
							<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();emptyDetailsForNonItem();">
									<bean:write name="complaintLogOfflineBean" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
								
							</div>
						</td>
						
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Sub Type</td>
						<td width="25%" class="CONTROL_TD">						
							<div id="enggItemSubTypeCmbDivId">
							<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL' onchange="getServiceEngName();">
									<bean:write name="complaintLogOfflineBean" property="strEngineeringItemSubTypeCmb" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</logic:equal>
								
							<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">	
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL' onchange="getNonItemName(); getServiceEngName();">
										<bean:write name="complaintLogOfflineBean" property="strEngineeringItemSubTypeCmb" filter="false" />
											<option value="0">Select Value</option>
								</select>
							</logic:equal>	
							</div>
						</td>
					</tr>

<!--	Item Name	-->
			
					<tr>
					<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="ITEM">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Equipment Name</td>
						<td width="25%" class="CONTROL_TD">
							<div id="itemNameId">
								<select name="strItemId" class='COMBO_NORMAL' >
									<bean:write name="complaintLogOfflineBean" property="strItemNameCombo" filter="false" />
<!--										<option value="0">Select Value</option>-->
								</select>
							</div>
						</td>
					</logic:equal>
					
					<!--	Non-Item Name				-->
					<logic:equal name="complaintLogOfflineBean" property="strPageFlag" value="NON_ITEM">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Non-Item Name</td>
						<td width="25%" class="CONTROL_TD">
							<div id="itemNameId">
								<select name="strNonItemId" class='COMBO_NORMAL' >
									<bean:write name="complaintLogOfflineBean" property="strNonItemNameCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</div>
						</td>
					</logic:equal>
						
						<td width="25%" class="CONTROL_TD"></td>
	
	
						<td width="25%" class="CONTROL_TD"></td>
	
					</tr>
			
			
			
		
			</table>
		</his:ContentTag>
        
		<his:ContentTag>
			<div id="stockValueId"></div>
		</his:ContentTag>
		
				
		
	
		
		<logic:empty name="complaintLogOfflineBean" property="strWarrantyDetailsTable">
		
				<his:SubTitleTag name="Repaied By Vendor"><html:checkbox name="complaintLogOfflineBean" property="isRepairedByVendor" onclick="enableVendorDetails();"></html:checkbox>
		</his:SubTitleTag>
		<table class="TABLE_STYLE">
		
	
		<tr>
		<td width="25%" class="LABEL_TD">Vendor Name</td>
						<td width="25%" class="CONTROL_TD">
						<div id="strVendorId">
								<select name="strVendorId" class='COMBO_NORMAL' onchange="getVenderDetails();"  >
									<bean:write name="complaintLogOfflineBean" property="strVenderCombo" filter="false" />
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
		
		
		<his:SubTitleTag name="Guarantee Details">
		</his:SubTitleTag>

		<his:ContentTag name="Guarantee Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="complaintLogOfflineBean" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="complaintLogOfflineBean" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
<div id="spareDiv" style="display: none" >
<his:SubTitleTag name="Spare Part Maintenace Status">

		</his:SubTitleTag>
		<his:ContentTag name="sparePartMaintenaceStatus">
			<table class="TABLE_STYLE" id="sparePartMaintenaceStatusTable">
				
				
			</table>	
			
					<div id="sparepartTablePartDivId"></div>
				
			
			<table class="TABLE_STYLE" id="">
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

</div>


<span id="sparePartAddRepairOrReplace" style="display: none;">
		<his:SubTitleTag name="Spare Parts Details">
			<img alt="Close" src="/HEMMS_ODISHA/hisglobal/images/stop.png" onclick="cancelSparePartAddPopup();">
		</his:SubTitleTag> <his:ContentTag name="sparePartsDetails">
			<table class="TABLE_STYLE" id="sparePartsDetailsTable" style="width: 600px;">
				<tr>
					<td class="LABEL_TD" width="50%">Spare Part Status</td>
					<td class="CONTROL_TD" width="50%">
					<div id="strSparePartStatusDivId">
						<select name="strSparePartStatusId" id="strSparePartStatusId"
							class="COMBO_NORMAL" onchange="changeSparePartAddPopup(this);">
							<bean:write name="complaintLogOfflineBean" property="strSparePartStatusOptions" filter="false" />
						</select>
						</div>
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
					Equipment</td>
					<td class="LABEL_TD" width="24%" style="text-align: center;">Equipment
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
				<bean:write name="complaintLogOfflineBean"
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
						style="font: inherit;"></span><span id="strSparePartIdCombo">
						<div id="strSparePartDivId">
						<select
						name="strSparePartId" id="strSparePartId" class="COMBO_NORMAL">
						<bean:write name="complaintLogOfflineBean"
							property="strSparePartNameOptions" filter="false" />
					</select>
					</div>
					</span></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Equipment
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
					<td class="CONTROL_TD">
					<div id="strSpareManufacturerDivId">
					<select class="COMBO_NORMAL"
						name="strSpareManufacturerId" id="strSpareManufacturerId">
						<bean:write name="complaintLogOfflineBean"
							property="strManufactureNameOptions" filter="false" />
					</select>
					<div>
					</td>
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
					<td class="CONTROL_TD">
					<div id="strWarrantyUptoUnitDivId"> 
					<select class="COMBO_NORMAL"
						name="strWarrantyUptoUnitId" id="strWarrantyUptoUnitId">
						<bean:write name="complaintLogOfflineBean"
							property="strWarrantyUptoUnitOptions" filter="false" />
					</select>
					<div>
					</td>
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






	<his:TitleTag name="Engineer Desk">
		</his:TitleTag>	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
			<tr>
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">Vendor/Service Engineer Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVendorServiceEngId">
						<select name="strVendorServiceEngName" class='COMBO_NORMAL' onchange="getAttendName();"  >
									<bean:write name="complaintLogOfflineBean" property="strServiceEngNameCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>
								
								</div>
					</td>
			
<!-- Communicate Id/Contact No -->	


	
			
				<td width="25%" class="LABEL_TD">Communicate Id/Contact No</td>
					<td width="25%" class="CONTROL_TD">
					<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="	strCommunicateIdContactId" onkeypress="return validateData(event,2);">
					</td>
			
			
	<!--	Attend Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date of Attend</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strAttendDate" value="${complaintLogOfflineBean.strAttendDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time of Attend
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strAttendTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>		
			
			
			
			
			
				<!--	Closing Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date of Closing</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strClosingDate" value="${complaintLogOfflineBean.strClosingDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time of Closing
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strClosingTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>		
			
			
			
			<!-- Is Spare Part Maintenance Involved -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Spare Parts Used
					</td>
					
					<td width="25%" class="CONTROL_TD">
					<html:checkbox name="complaintLogOfflineBean" property="strIsSparePartsMaintenanceInvolved"  value="0" onclick="isSparePartsMaintenanceInvolved();"/> 
						
					</td>
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
			
			
			
		
			
<!--	Problem Description -->

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Problem Description</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="complaintLogOfflineBean" property="strProblemDescription" cols="25" rows="2" onkeypress="return validateData(event,9);" />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>			
				

<!--	Solution provided -->

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Solution Provided</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="complaintLogOfflineBean" property="strSolutionProvided" cols="25" rows="2" onkeypress="return validateData(event,9);" />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>			
				
<!--	Remarks -->

				<tr>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="complaintLogOfflineBean" property="strRemarks" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
				
				
<!--	Reason for close -->

				<tr>
					<td width="25%" class="LABEL_TD">Reason for Close</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="complaintLogOfflineBean" property="strReasonForClosing" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
	<!-- Is  Item In working Condition -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<html:radio name="complaintLogOfflineBean" property="strIsItemInWorkingCondition" value="0" onclick="isItemInWorkingConditionchangeMode();"/> Yes
					</td>
					
					<td width="25%" class="CONTROL_TD">	
						<html:radio name="complaintLogOfflineBean" property="strIsItemInWorkingCondition" value="1" onclick="isItemInWorkingConditionchangeMode();" /> NO
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
										
	
				<!--	From Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>From Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strFromDate" value="${complaintLogOfflineBean.strFromDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>From Time
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strFromTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>					

			
				<tr>
					<td width="25%" class="LABEL_TD">
						Is Penality
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<html:checkbox name="complaintLogOfflineBean" property="strIsPenality" value="0" onclick="isItemInWorkingConditionchangeMode();"/> Yes
					</td>
					
					<td width="25%" class="LABEL_TD">Penalty Amount
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"><input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strInvoiceNo" onkeypress="return validateData(event,2);"></td>
				</tr>
			
				
<!--	Contact Person Name	&	Contact No.		-->

					<tr>
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">VerfiedBy</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVerifiedById">
						<select name="strVerifiedBy" class='COMBO_NORMAL' onchange=""  >
									<bean:write name="complaintLogOfflineBean" property="strVerifiedBy" filter="false" />
										<option value="0">Select Value</option>
								</select>
								</div>
					</td>
					
					
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
	
	
		<!--	Verfied  Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strVerifyDate" value="${complaintLogOfflineBean.strVerifyDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time 
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strVerifyTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
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
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-sv.png" onClick="return validate1();" onkeypress="if(event.keyCode==13) validate1();"/>
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="clearPage();" onkeypress="if(event.keyCode==13) clearPage();" />
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write
			name="complaintLogOfflineBean" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="complaintLogOfflineBean" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write
			name="complaintLogOfflineBean" property="strNormalMsg" /></div>

		<input type="hidden" name="hmode" />
		
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${complaintLogOfflineBean.strEmpName}" />
	
		<input type="hidden" name="strPageFlag" value="${complaintLogOfflineBean.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strOldDepartmentCode" value="${complaintLogOfflineBean.strDeptCode}" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strAttendedContactPerson" value="" />
		<html:hidden name="complaintLogOfflineBean" property="strSerialNo" value="0"/>
		<html:hidden name="complaintLogOfflineBean" property="strSparePartsFlag" value="0"/>
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