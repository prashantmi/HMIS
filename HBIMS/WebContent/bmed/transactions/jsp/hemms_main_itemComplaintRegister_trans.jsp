<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%--
/**
 * Developer Name : T. Saratkumar
 * Process Name : Equipment Complaint Register
 * Product Name : HEMMS Product
 * Version		: 1.0
 * Date : 30/July/2013
 * Modify By/Date : 05/Nov./2013
 */ 
 --%>

<html>
<head>

<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">



<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tabIndex.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<his:javascript	src="/bmed/transactions/js/hemms_main_itemComplaintRegister_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>



</head>

<!--<body marginheight="0" marginwidth="0"  onload="onLoadFunction(),getReport();">
-->
<body marginheight="0" marginwidth="0"  onload="onLoadFunction()" class="background">

<html:form name="hemmsComplaintMaintenanceStatusBean" action="/transactions/HemmsComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.HemmsComplaintMaintenanceStatusFB" styleClass="formbg">
	
	<div class="ERR_DIV" id="errMsg"><bean:write
			name="hemmsComplaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="hemmsComplaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write
			name="hemmsComplaintMaintenanceStatusBean" property="strNormalMsg" /></div>
	
	<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">	
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Complaint Register</td>
		</tr>
	</table>
	
	</logic:equal>
	
	<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Non-Item Complaint Register</td>
		</tr>
	</table>
		
	</logic:equal>	
	
		
		
			<table style="display: none;" class="TABLEWIDTH">
				<tr style="display: none;">
					<td colspan="4" class="LABEL">

						 <html:radio name="hemmsComplaintMaintenanceStatusBean" property="strItemOrNonItemMode" style="display: none " value="1" onclick="changeViewMode();" /> <!-- Item --> 
 						<!-- html:radio name="hemmsComplaintMaintenanceStatusBean" property="strItemOrNonItemMode" value="0" onclick="changeViewMode();" / --> <!-- Non-Item  -->
 
					</td>
				</tr>
				


				<tr style="display: none;">					
					<td  colspan="4" class="CONTROL" style="text-align: right;">

						<html:checkbox name="hemmsComplaintMaintenanceStatusBean"  property="strIsAttached" value="1" onclick="showHideEmpDetails(this);" />Is Attachment

					</td>
				</tr>
<!--	Complaint Type	-->	
			<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strConfigPropertyValue" value="0">						
					<tr style="display: none;">				
						<td width="25%" class="LABEL">
							<font color="red">*</font>Complaint Type
						</td>
								
						<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">								
						<td width="25%" class="CONTROL">
								<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strComplaintType" value="1"  /> Internal
						</td>
					
						<td width="25%" class="CONTROL">
								<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strComplaintType" value="2" /> External
						</td>
						</logic:equal>
						
						<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
							
							<td width="25%" class="CONTROL">
								<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strComplaintType" value="2" /> External
							</td>
							
							<td width="25%" class="CONTROL">
								<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strComplaintType" value="1"  /> Internal
							</td>
						</logic:equal>	
							
						<td width="25%" class="CONTROL"></td>
					</tr>
			</logic:equal>
				
			</table>
		
		<div class="line">
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px"  border="0">
		<tr >
		<td>Equipment Details</td>
		</tr>
		</table>
		</div>
			
<!--	Equipment Details	-->
			
				<table class="TABLEWIDTH">	
	<!-- Employee Name & Employee Id		-->
				 	 <tr id="divEmpDetails" align="center" style="display: none">
						<td width="25%" class="LABEL">
							<font color="red">*</font>Employee Name</td>
						<td width="25%" class="CONTROL">
							<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEmpName" />						
						</td>
					
						<td width="25%" class="LABEL">
							<font color="red">*</font>Employee Id</td>
						<td width="25%" class="CONTROL">
							<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEmpId" />						
						</td>
			</tr>
	
	<!--	Department & Designation -->
					<tr id="divEmpDeptAndDesignation" align="center" style="display: none">
						<td width="25%" class="LABEL">
							<font color="red">*</font>Hospital Name</td>
						<td width="25%" class="CONTROL">
						<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
							<select name="strDeptCode" class='comboMax' onchange="getItemName();emptyDetailsForItem();">
								<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
						
						<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
							<select name="strDeptCode" class='comboMax' >
								<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
							
						</td>				
					
						<td width="25%" class="LABEL">
							<font color="red">*</font>Designation</td>
						<td width="25%" class="CONTROL">
							<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strDesignation" />						
						</td>
					</tr>	
				</table>
			
							
<!-- Department Name & 	Store Name -->
		
			
				<table class="TABLEWIDTH">	
					<tr id="divDeptNameAndStoreDetails">
						<td width="25%" class="LABEL"><font color="red">*</font>Hospital Name</td>
						<td width="25%" class="CONTROL">
							<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">	
							
								<select name="strDeptCodeNew" class='comboMax' onchange="getStoreName();emptyDetailsForItem();">
									<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
							
							<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">	
							
								<select name="strDeptCodeNew" class='comboMax' >
									<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
						</td>

						<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">	
							<td width="25%" class="LABEL">
									<font color="red">*</font>Lab Name
							</td>

							<td width="25%" class="CONTROL">	
								<div id="storeNameId">					
									<select name="strStoreId" class='comboMax' onchange="getItemNameOnBasisOfStore();">
									<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strStoreNameCombo" filter="false" />  
											
									</select>
								</div>
							</td>
						</logic:equal>
						
						<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
							<td width="25%" class="CONTROL"></td>


							<td width="25%" class="CONTROL"></td>
						</logic:equal>	
						
					</tr>					
				
<!-- Engineering Item Type & Engineering Item Sub-Type -->
					<tr style="display: none;">
						<td width="25%" class="LABEL"><font color="red">*</font>Engineering Item Type</td>
						<td width="25%" class="CONTROL">
							<div id="engineeringItemTypeId">
							<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
								<select	name="strEngineeringItemTypeId" class='comboMax' onchange="getEnggItemSubTypeCombo();">
									<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
							<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">
								<select	name="strEngineeringItemTypeId" class='comboMax' onchange="getEnggItemSubTypeCombo();emptyDetailsForNonItem();">
									<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
								
							</div>
						</td>
						
						<td width="25%" class="LABEL"><font color="red">*</font>Engineering Item Sub Type</td>
						<td width="25%" class="CONTROL">						
							<div id="enggItemSubTypeCmbDivId">
							<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="ITEM">
								<select name="strEngineeringItemSubTypeId" class='comboMax'>
									<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEngineeringItemSubTypeCmb" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</logic:equal>
								
							<logic:equal name="hemmsComplaintMaintenanceStatusBean" property="strPageFlag" value="NON_ITEM">	
								<select name="strEngineeringItemSubTypeId" class='comboMax' onchange="getNonItemName();">
										<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEngineeringItemSubTypeCmb" filter="false" />
											<option value="0">Select Value</option>
								</select>
							</logic:equal>	
							</div>
						</td>
					</tr>

<!--	Item Name	-->
			
					<tr>
					<td width="25%" class="LABEL"><font color="red">*</font>Equipment
					Name</td>
					<td width="25%" class="CONTROL">
					<div id="equipmentNameId"><select name="strEquipmentNameId" class="comboMax" onChange="getItemBrandName();">
					<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strEquipmentNameCmb" filter="false" /> 
				<option value="0">Select Value</option>
						</select></div>
					</td>
					<td width="25%" class="LABEL"><font color="red">*</font>Equipment
					Model</td>
					<td width="25%" class="CONTROL">
					<div id="itemNameId"><select name="strItemId" class="comboMax"
						onchange="getStockDtl();">
							
					<option value="0">Select Value</option>
											</select></div>
					</td>
				</tr>
		
			</table>
		
        
		
			<div id="stockValueId" style="display: none"></div>
		
		
		
			<div id="prevCompDtlId"></div>
		


	<div class="line">
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px"  border="0">
		<tr >
		<td>Guarantee Details</td>
		</tr>
		</table>
		</div>
		
		
			<table class="TABLEWIDTH" id="warrantyDetailsTable">
				<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		
		
		
	<div class="line">
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px"  border="0">
		<tr >
		<td >Maintenance Contract Details</td>
		</tr>
		</table>
		</div>
		
			<table class="TABLEWIDTH" id="maintenanceContractDetailsTable">
				<bean:write name="hemmsComplaintMaintenanceStatusBean" property="strMaintenanceContractDetailsTable" filter="false"/>
		
			</table>
			
		<center>
		<input type="button" name="addEqupment" id="addEqupment" value="Add" onClick="addEquipmentForComplaints()"/>
		</center>
		
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Selected Equipment</td>
		</tr>
	</table>

		<table id="selectedEquipmentTable" class="TABLEWIDTH">
			<tr>
				<td class="LABEL" style="text-align: center;" width="21%">Equipment Name</td>
				<td class="LABEL" style="text-align: center;" width="19%">Equipment Model</td>
				<td class="LABEL" style="text-align: center;" width="18%">Serial No</td>
				<td class="LABEL" style="text-align: center;" width="19%">Supplier Name</td>
				<td class="LABEL" style="text-align: center;" width="20%">Guarantee/AMC Details</td>
				<td class="LABEL" style="text-align: center;" width="5%"></td>			

			</tr>
		</table>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Complaint Details</td>
		</tr>
	</table>
		
			<table class="TABLEWIDTH">
			
			<!--	Complaint Nature -->			
			
			<tr>
					<td width="25%" class="LABEL">
						<font color="red">*</font>Complaint Category
					</td>
					
					<td width="25%" class="CONTROL">
						<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strComplaintNature" value="0" onclick="" /> Normal
					</td>
					
					<td width="25%" class="CONTROL">	
						<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strComplaintNature" value="1" onclick="" /> Preventive Maintenance
					</td>
					
					
					<td width="25%" class="CONTROL"></td>
				</tr>
			
<!--	Complaint Description -->

				<tr>
					<td width="25%" class="LABEL"><font color="red">*</font>Complaint Description</td>
					<td width="50%" colspan="3" class="CONTROL">
						<html:textarea name="hemmsComplaintMaintenanceStatusBean" property="strComplaintDescription" cols="63" rows="2" onkeypress="return validateData(event,9);" />
					</td>
				</tr>			
				
<!--	Is Item in Working Condition -->
				<tr>
					<td width="25%" class="LABEL">
						<font color="red">*</font>Is Item in Working Condition
					</td>
					
					<td width="25%" class="CONTROL">
						<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strIsItemInWorkingCondition" value="0" onclick="isItemInWorkingConditionchangeMode();"/> Yes
					</td>
					
					<td width="25%" class="CONTROL">	
						<html:radio name="hemmsComplaintMaintenanceStatusBean" property="strIsItemInWorkingCondition" value="1" onclick="isItemInWorkingConditionchangeMode();" /> NO
					</td>
					
					
					<td width="25%" class="CONTROL"></td>
				</tr>
				
<!--	Date & Time	-->

					<tr id="dateAndTimeId" style="display: none;">
						<td width="25%" class="LABEL"><font color="red">*</font>Date</td>
						<td width="25%" class="CONTROL">
							<dateTag:date name="strComplaintDate" value="${hemmsComplaintMaintenanceStatusBean.strComplaintDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL"><font color="red">*</font>Time
						</td>
						
						<td width="25%" class="CONTROL">
							<div style="display: none;"><input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strComplaintTimeOld" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)</div>
							<select name="strComplaintTime" class="comboMin">
					  	<option value="01:00">01:00</option>
						<option value="01:30">01:30</option>
						<option value="02:00">02:00</option>
						<option value="02:30">02:30</option>
						<option value="03:00">03:00</option>
						<option value="03:30">03:30</option>
						<option value="04:00">04:00</option>
						<option value="04:30">04:30</option>
						<option value="05:00">05:00</option>
						<option value="05:30">05:30</option>
						<option value="06:00">06:00</option>
						<option value="06:30">06:30</option>
						<option value="07:00">07:00</option>
						<option value="07:30">07:30</option>
						<option value="08:00">08:00</option>
						<option value="08:30">08:30</option>
						<option value="09:00">09:00</option>
						<option value="09:30">09:30</option>
						<option value="10:00">10:00</option>
						<option value="10:30">10:30</option>
						<option value="11:00">11:00</option>
						<option value="11:30">11:30</option>
						<option value="12:00">12:00</option>
						<option value="12:30">12:30</option>
					  	</select>
					  	<select class="comboMin" name="strComplaintTimeMeridian"><OPTION value="AM">AM</OPTION><OPTION value="PM">PM</OPTION></select>					
						</td>					
					</tr>
			
<!--	Preferred Visit From & To Time		-->
				<tr>
					<td width="25%" class="LABEL">
						<font color="red">*</font>Preferred Visit Time From
					</td>
					
					<td width="25%" class="CONTROL">
					  	<div style="display: none;">
					  	<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strPreferredFromTimeOld" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)
					  	</div>
					  	<select name="strPreferredFromTime" class="comboMin">
					  	<option value="01:00">01:00</option>
						<option value="01:30">01:30</option>
						<option value="02:00">02:00</option>
						<option value="02:30">02:30</option>
						<option value="03:00">03:00</option>
						<option value="03:30">03:30</option>
						<option value="04:00">04:00</option>
						<option value="04:30">04:30</option>
						<option value="05:00">05:00</option>
						<option value="05:30">05:30</option>
						<option value="06:00">06:00</option>
						<option value="06:30">06:30</option>
						<option value="07:00">07:00</option>
						<option value="07:30">07:30</option>
						<option value="08:00">08:00</option>
						<option value="08:30">08:30</option>
						<option value="09:00">09:00</option>
						<option value="09:30">09:30</option>
						<option value="10:00" selected="selected">10:00</option>
						<option value="10:30">10:30</option>
						<option value="11:00">11:00</option>
						<option value="11:30">11:30</option>
						<option value="12:00">12:00</option>
						<option value="12:30">12:30</option>
					  	</select>
					  	<select class="comboMin" name="strTimeMeridian"><OPTION value="AM">AM</OPTION><OPTION value="PM">PM</OPTION></select>
					</td>
					
					<td width="25%" class="LABEL">
						<font color="red">*</font>To
					</td>
					
					<td width="25%" class="CONTROL">
					<div style="display: none;">
					  	<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strPreferredToTimeOld" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)
					</div>
					<select name="strPreferredToTime" class="comboMin" >
					  	<option value="01:00">01:00</option>
						<option value="01:30">01:30</option>
						<option value="02:00">02:00</option>
						<option value="02:30">02:30</option>
						<option value="03:00">03:00</option>
						<option value="03:30">03:30</option>
						<option value="04:00" selected="selected">04:00</option>
						<option value="04:30">04:30</option>
						<option value="05:00">05:00</option>
						<option value="05:30">05:30</option>
						<option value="06:00">06:00</option>
						<option value="06:30">06:30</option>
						<option value="07:00">07:00</option>
						<option value="07:30">07:30</option>
						<option value="08:00">08:00</option>
						<option value="08:30">08:30</option>
						<option value="09:00">09:00</option>
						<option value="09:30">09:30</option>
						<option value="10:00">10:00</option>
						<option value="10:30">10:30</option>
						<option value="11:00">11:00</option>
						<option value="11:30">11:30</option>
						<option value="12:00">12:00</option>
						<option value="12:30">12:30</option>
					  	</select>
					  	<select class="comboMin" name="strTimeMeridianTo"><OPTION value="AM">AM</OPTION><OPTION value="PM" selected="selected">PM</OPTION></select>
					</td>
				</tr>
				
<!--	Contact Person Name	&	Contact No.		-->

				<tr>
					<td width="25%" class="LABEL">
						<font color="red">*</font>Contact Person Name
					</td>
					
					<td width="25%" class="CONTROL">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="250" name="strContactPersonName" onkeypress="return validateData(event,4);">
					</td>
					
					<td width="25%" class="LABEL">
						<font color="red">*</font>Contact No.
					</td>
					
					<td width="25%" class="CONTROL">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="10" name="strContactNo" onkeypress="return validateData(event,5);">
					</td>
				</tr>
				
<!--	Land Mark Description -->

				<tr>
					<td width="25%" class="LABEL"><font color="red">*</font>Address/Landmark</td>
					<td width="25%" class="CONTROL">
						<html:textarea name="hemmsComplaintMaintenanceStatusBean" property="strLandMarkDescription" value="" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
				</tr>
				
<!--	Remarks -->

				<tr>
					<td width="25%" class="LABEL">Remarks</td>
					<td width="25%" class="CONTROL">
						<html:textarea name="hemmsComplaintMaintenanceStatusBean" property="strRemarks" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
				</tr>				

			</table>
	
		
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<!--	<table cellspacing="1px" class="TABLEWIDTH" align="center">-->
			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		
		<!-- <div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>


	<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="return validate1();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onclick="clearPage();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div> -->
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate1();" />  <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Clear Process"
				onclick="clearPage();" /> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" /></td>
		</tr>
	</table>
		

		<input type="hidden" name="hmode" />
		
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strStockInfoVal" value="${hemmsComplaintMaintenanceStatusBean.strStockInfoVal}" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${hemmsComplaintMaintenanceStatusBean.strEmpName}" />
		<input type="hidden" name="strEmpId" value="${hemmsComplaintMaintenanceStatusBean.strEmpId}" />
		<input type="hidden" name="strPageFlag" value="${hemmsComplaintMaintenanceStatusBean.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strOldDepartmentCode" value="${hemmsComplaintMaintenanceStatusBean.strDeptCode}" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strRequestNo" value="" />
		<input type="hidden" name="strComplaintId" value="${hemmsComplaintMaintenanceStatusBean.strComplaintId}" />
		<input type="hidden" name="strReportFlg" value="0" />		
		<input type="hidden" name="strDeptName" value="${hemmsComplaintMaintenanceStatusBean.strDeptName}" />
		<input type="hidden" name="strItemName" value="${hemmsComplaintMaintenanceStatusBean.strItemName}" />
		<input type="hidden" name="strItemName" value="${hemmsComplaintMaintenanceStatusBean.strItemName}" />
		<input type="hidden" name="strEquipmentName" value="${hemmsComplaintMaintenanceStatusBean.strEquipmentName}" />
		
		<input type="hidden" name="strWarrantyOrMaintenanceSlNoAndType" value="${hemmsComplaintMaintenanceStatusBean.strWarrantyOrMaintenanceSlNoAndType}" />
		<input type="hidden" name="strVendorName" value="${hemmsComplaintMaintenanceStatusBean.strVendorName}" />
		<input type="hidden" name="strWarrantyDuration" value="${hemmsComplaintMaintenanceStatusBean.strWarrantyDuration}" />
		<input type="hidden" name="strAMCStartDate" value="${hemmsComplaintMaintenanceStatusBean.strAMCStartDate}" />
		<input type="hidden" name="strAMCEndDate" value="${hemmsComplaintMaintenanceStatusBean.strAMCEndDate}" />
		<input type="hidden" name="strIsItemInWorkingCondition" value="${hemmsComplaintMaintenanceStatusBean.strIsItemInWorkingCondition}" />
		<input type="hidden" name="strPreviousComplaintDetails" value="${hemmsComplaintMaintenanceStatusBean.strPreviousComplaintDetails}" />
		<input type="hidden" name="strIsComplaintDesk"  value="${hemmsComplaintMaintenanceStatusBean.strIsComplaintDesk}"/>
		<input type="hidden" name="strPath"  value="${hemmsComplaintMaintenanceStatusBean.strPath}"/>
		<input type="hidden" name="strComplaintsReqId"  value="${hemmsComplaintMaintenanceStatusBean.strComplaintsReqId}"/>
		<input type="hidden" name="strSerialNo"  value="${hemmsComplaintMaintenanceStatusBean.strSerialNo}"/>
		
		
		
		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table id="popUpTable" style="width:300px;" bgcolor="white">
			<tr >
				<td colspan="2" style="width:300px;" class="LABEL"><center>Complaint registered successfully. </center></td>
			</tr>
			<tr>
				<td width="50%" class="LABEL" style='text-align:center'>Serial No.</td>
				<td width="50%" class="LABEL" style='text-align:center'>Complaint Id	</td>
				
				
			</tr>
			<tr>
				<td colspan="2">
				<center><input id="popUpOkButton" type="button" value="OK" onClick="popUpOkButtonClick()" /></center>
				</td>
			</tr>
			<tr>
			<td colspan="2" style="width:300px;" class="LABEL">
				
			</td>
			</tr>
		</table>
		</div>

		
	
	<cmbPers:cmbPers />
</html:form>

</body>
</html>