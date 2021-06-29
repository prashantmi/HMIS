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
 * Developer Name : Arun vR
 * Process Name : Equipment Inspection Test Details
 * Date : 21/Aug/2012
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
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<his:javascript	src="/bmed/transactions/js/bmed_equipment_inspection_test_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>

</head>

<body marginheight="0" marginwidth="0"  onload="onLoadFunction();">
<html:form name="equipmentInspectionTestDtlsFB" action="/transactions/EquipmentInspectionTestDtlsACTION"
	type="bmed.transactions.controller.fb.EquipmentInspectionTestDtlsFB">
	
	<his:TransactionContainer>	
		<his:TitleTag name="Equipment Inspection Test Details">	</his:TitleTag>		
			<his:ContentTag>
			
				<div class="ERR_DIV" id="errMsg"><bean:write name="equipmentInspectionTestDtlsFB" property="strErrMsg" /></div>
			    <div class="WARNING_DIV" id="warningMsg"><bean:write name="equipmentInspectionTestDtlsFB" property="strWarningMsg" /></div>
			    <div class="NORMAL_DIV" id="normalMsg"><bean:write name="equipmentInspectionTestDtlsFB" property="strNormalMsg" /></div>
			
				<table class="TABLE_STYLE">	
					<tr id="divDeptNameAndStoreDetails">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Department Name</td>
						<td width="25%" class="CONTROL_TD">
							<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">	
							
								<select name="strDeptCodeNew" class='COMBO_NORMAL' onchange="getStoreName();emptyDetailsForItem();">
									<bean:write name="equipmentInspectionTestDtlsFB" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
							
							<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">	
							
								<select name="strDeptCodeNew" class='COMBO_NORMAL' >
									<bean:write name="equipmentInspectionTestDtlsFB" property="strDeptCombo" filter="false" />
										
								</select>
							</logic:equal>
							
						</td>

						<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">	
							<td width="25%" class="LABEL_TD">
									<font color="red">*</font>Store Name
							</td>

							<td width="25%" class="CONTROL_TD">	
								<div id="storeNameId">					
									<select name="strStoreId" class='COMBO_NORMAL' onchange="getItemNameOnBasisOfStore();emptyDetailsForItem();">
										<bean:write name="equipmentInspectionTestDtlsFB" property="strStoreNameCombo" filter="false" />
											
									</select>
								</div>
							</td>
						</logic:equal>
						
						<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">
							<td width="25%" class="CONTROL_TD"></td>


							<td width="25%" class="CONTROL_TD"></td>
						</logic:equal>	
						
					</tr>		
					
					
					<!--	Department & Designation -->
					<tr id="divEmpDeptAndDesignation" align="center" style="display: none">
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Department</td>
						<td width="25%" class="CONTROL_TD">
						<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">
							<select name="strDeptCode" class='COMBO_NORMAL' onchange="getItemName();emptyDetailsForItem();">
								<bean:write name="equipmentInspectionTestDtlsFB" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
						
						<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">
							<select name="strDeptCode" class='COMBO_NORMAL' >
								<bean:write name="equipmentInspectionTestDtlsFB" property="strDeptCombo" filter="false" />
									
							</select>
						</logic:equal>
							
						</td>				
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Designation</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="equipmentInspectionTestDtlsFB" property="strDesignation" />						
						</td>
					</tr>
					
					
										
								
				
<!-- Engineering Item Type & Engineering Item Sub-Type -->
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Type</td>
						<td width="25%" class="CONTROL_TD">
							<div id="engineeringItemTypeId">
							<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();">
									<bean:write name="equipmentInspectionTestDtlsFB" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
							<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();emptyDetailsForNonItem();">
									<bean:write name="equipmentInspectionTestDtlsFB" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</logic:equal>
							
								
							</div>
						</td>
						
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Sub Type</td>
						<td width="25%" class="CONTROL_TD">						
							<div id="enggItemSubTypeCmbDivId">
							<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL' onchange="getServiceEngName();">
									<bean:write name="equipmentInspectionTestDtlsFB" property="strEngineeringItemSubTypeCmb" filter="false" />
										<option value="0">Select Value</option>
								</select>
							</logic:equal>
								
							<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">	
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL' onchange="getNonItemName(); getServiceEngName();">
										<bean:write name="equipmentInspectionTestDtlsFB" property="strEngineeringItemSubTypeCmb" filter="false" />
											<option value="0">Select Value</option>
								</select>
							</logic:equal>	
							</div>
						</td>
					</tr>

<!--	Item Name	-->
			
					<tr>
					<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Item Name</td>
						<td width="25%" class="CONTROL_TD">
							<div id="itemNameId">
								<select name="strItemId" class='COMBO_NORMAL' >
									<bean:write name="equipmentInspectionTestDtlsFB" property="strItemNameCombo" filter="false" />
<!--										<option value="0">Select Value</option>-->
								</select>
							</div>
						</td>
					</logic:equal>
					
					<!--	Non-Item Name				-->
					<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Non-Item Name</td>
						<td width="25%" class="CONTROL_TD">
							<div id="itemNameId">
								<select name="strNonItemId" class='COMBO_NORMAL' >
									<bean:write name="equipmentInspectionTestDtlsFB" property="strNonItemNameCombo" filter="false" />
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
		
		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag>

		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="equipmentInspectionTestDtlsFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="equipmentInspectionTestDtlsFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>


	<his:TitleTag name="Test/Inspection Details">
		</his:TitleTag>	
		
		
		
		
		
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
			<tr>
			
<!--  Inspection/Test Name -->			
			
			<td width="25%" class="LABEL_TD"><font color="red">*</font>Inspection/Test Name</td>
					<td width="25%" class="CONTROL_TD">
					
						<select name="strTestId" class='COMBO_NORMAL' onchange="getAttendName();"  >
									<bean:write name="equipmentInspectionTestDtlsFB" property="strTestNameCombo" filter="false" />
										<!-- <option value="0">Select Value</option> -->
								</select>
								
								
					</td>
					
					<td  width="25%"   colspan="2" class="LABEL_TD">

							<html:radio name="equipmentInspectionTestDtlsFB" property="strItemOrNonItemMode" value="1" onclick="getInternalData();" /> Internal
							<html:radio name="equipmentInspectionTestDtlsFB" property="strItemOrNonItemMode" value="0" onclick="getExternalData();" /> External
					</td>
				  	
				
				
				</tr>
			
				
				
					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Test Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strTestDate" value="${equipmentInspectionTestDtlsFB.strTestDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Test Time 
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strAttendTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>		
				<tr>
					<td width="25%" class="LABEL_TD">Result</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="equipmentInspectionTestDtlsFB" property="strResult" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				  <tr>
					
				
				
				
				<td width="25%" class="LABEL_TD">
			  <div id="strVendorServiceEngNameDivId"  style="display:block" >
				<font color="red">*</font>Confirmed By 
				
				
				</div>
				
				<div id="strSpareManufacturerDivId" style="display:none" >
				<font color="red">*</font>Supplier/Manufacturer Name
				</div>
				</td>
				<td  class="CONTROL_TD">
				
				 <div class="CONTROL_TD" id="strVendorServiceEngNameDivId1"  style="display:block" >
				<div  id="strServiceEngId" class="CONTROL_TD">
						<select name="strVendorServiceEngName" class='COMBO_NORMAL' onchange="getOtherName();"  >
									<bean:write name="equipmentInspectionTestDtlsFB" property="strServiceEngNameCombo" filter="false" />
										
								</select>
							
							</div>
				
				</div>
				<div class="CONTROL_TD" id="strSpareManufacturerDivId1" style="display:none" >
				<select class='COMBO_NORMAL'
						name="strSpareManufacturerId" id="strSpareManufacturerId">
						<bean:write name="equipmentInspectionTestDtlsFB"
							property="strManufactureNameOptions" filter="false" />
					      </select>
				</div>
				
			
				</td>
				
					<td width="25%" class="CONTROL_TD">
					
						
					<div id="otherNameDivId" style="display: none;">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strOtherName" onkeypress="return validateData(event,2);">
					</div>			
										
					</td>
				
					
				<td width="25%"  class="CONTROL_TD"></td>
				
				
					
				</tr>
				
				
				
				
		</table>
		
		</his:ContentTag>
		
		<his:TitleTag name="Parameter Details">
		</his:TitleTag>	
		
		<table class="TABLE_STYLE" >
		
		<tr>
			<td width="33%" class="LABEL_TD"><font color="red">*</font>Input</td>
			<td width="33%" class="LABEL_TD"><font color="red">*</font>Output</td>
			<td width="33%" class="LABEL_TD"></td>
			</tr>
		
		
		<tr>
			<td width="33%" class="CONTROL_TD" >
			<div align="right">
			<select class='COMBO_NORMAL'
						name="strTestParaId" id="strSpareManufacturerId">
						<bean:write name="equipmentInspectionTestDtlsFB"
							property="strTestParaNameCombo" filter="false" />
					      </select>
						
			</div>								
										
			</td>
			<td width="33%" class="CONTROL_TD">
					<div align="right">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="stroutPut" onkeypress="return validateData(event,2);">
					</div>						
										
			</td>
			<td width="33%" class="CONTROL_TD">
					
								
			<img  Src="../../hisglobal/images/plus.gif" style="cursor: pointer;"
						title="Add New Parameter Name"
						OnClick="addRows(new Array('strTestParaId','stroutPut'),new Array('s','t'),'1','1','R');">			
										
			</td>
			
		</tr>
		
		</table>
			<div id="id1" ></div>

		
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

<br>
		

		<input type="hidden" name="hmode" />
		
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strIsExInFlag" value="0" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${equipmentInspectionTestDtlsFB.strEmpName}" />
	
		<input type="hidden" name="strPageFlag" value="${equipmentInspectionTestDtlsFB.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strOldDepartmentCode" value="${equipmentInspectionTestDtlsFB.strDeptCode}" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strAttendedContactPerson" value="" />
		<html:hidden name="equipmentInspectionTestDtlsFB" property="strSerialNo" value="0"/>
		
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
<jsp:include page="bmed_equipment_inspection_multirow_mst.jsp"></jsp:include>
</body>
</html>