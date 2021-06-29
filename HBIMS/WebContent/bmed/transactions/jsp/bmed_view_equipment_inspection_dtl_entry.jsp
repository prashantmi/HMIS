<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
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
 * Process Name : Equipment Inspection Test Details Verify
 * Date : 17/Oct/2013
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
<his:javascript	src="/bmed/transactions/js/bmed_equipment_inspection_test_trans.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>

</head>

<body marginheight="0" marginwidth="0"  onload="onLoadFunction();" class="background">
<html:form name="equipmentInspectionTestDtlsFB" action="/transactions/EquipmentInspectionTestDtlsACTION"
	type="bmed.transactions.controller.fb.EquipmentInspectionTestDtlsFB" styleClass="formbg">
	
		<div class="ERR_DIV" id="errMsg"><bean:write
			name="equipmentInspectionTestDtlsFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="equipmentInspectionTestDtlsFB" property="strWarningMsg" /></div>
			<div style="display:none;" class="normalMsg" id="normalMsg"><bean:write
			name="equipmentInspectionTestDtlsFB" property="strNormalMsg" /></div>
		
		
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Inspection Test Details</td>
		</tr>
	</table>
	
		
		
			<table class="TABLEWIDTH">
				


			<!-- <tr>					
					<td  colspan="4" class="CONTROL_TD" style="text-align: right;">

						<html:checkbox name="equipmentInspectionTestDtlsFB"  property="strIsAttached" value="1" onclick="showHideEmpDetails(this);" />Is Attachment

					</td>
				</tr>  -->

			</table>
		

							
<!-- Department Name & 	Store Name -->
		
			
				<table class="TABLEWIDTH">	
					<tr id="divDeptNameAndStoreDetails">
						<td width="25%" class="LABEL">Hospital Name</td>
						<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strDeptName }
						</td>

						<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="ITEM">	
							<td width="25%" class="LABEL">
									Lab Name
							</td>

							<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strStoreId }
							</td>
						</logic:equal>
						
						<logic:equal name="equipmentInspectionTestDtlsFB" property="strPageFlag" value="NON_ITEM">
							<td width="25%" class="CONTROL"></td>


							<td width="25%" class="CONTROL"></td>
						</logic:equal>	
						
					</tr>		
								
				
<!-- Engineering Item Type & Engineering Item Sub-Type -->
				<!--  	<tr>
						<td width="25%" class="LABEL"><font color="red">*</font>Engineering Item Type</td>
						<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strEngineeringItemTypeId }
						</td>
						
						<td width="25%" class="LABEL"><font color="red">*</font>Engineering Item Sub Type</td>
						<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strEngineeringItemSubTypeId }
						</td>
					</tr> -->

			<tr>
					<td width="25%" class="LABEL">Equipment
					Name</td>
					<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strItemId }
					</td>
					<td width="25%" class="LABEL">Equipment
					Model</td>
					<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strItemBrandId }
					</td>
				</tr>
			
		
			</table>
		
        
		
			<div id="stockValueId"></div>
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Warranty Details</td>
		</tr>
	</table>
		
	

		
			<table class="TABLEWIDTH" id="warrantyDetailsTable">
				<bean:write name="equipmentInspectionTestDtlsFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Maintenance Contract Details</td>
		</tr>
	</table>
		
	
		
			<table class="TABLEWIDTH" id="maintenanceContractDetailsTable">
				<bean:write name="equipmentInspectionTestDtlsFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Test/Inspection Details</td>
		</tr>
	</table>

	
		
		
			<table class="TABLEWIDTH">
			<tr>
			
<!--  Inspection/Test Classification -->			
			
			<td width="25%" class="LABEL">Classification</td>
					<td  width="25%"class="CONTROL">${equipmentInspectionTestDtlsFB.strClassificationMode }
					</td>
					
					<td width="25%" colspan="2" class="CONTROL">
					</td>
					
					
				  	
				
				
				</tr>
				
			<tr>
			
<!--  Inspection/Test Name -->			
			
			<td width="25%" class="LABEL">Inspection/Test Name</td>
					<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strTestId }
					</td>
					<td width="25%" class="LABEL">Test/Inspected By</td>
					<td  width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strItemOrNonItemMode }
					</td>
				</tr>
				
					<tr >
						<td width="25%" class="LABEL">Test/Inspection Date</td>
						<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strTestDate }
						</td>					
					
						<td width="25%" class="LABEL">Test/Inspection Time 
						</td>
						
						<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strAttendTime }					
						</td>					
					</tr>		
				<tr>
					<td width="25%" class="LABEL">Result</td>
					<td width="25%" class="CONTROL">${equipmentInspectionTestDtlsFB.strResult }
					</td>
					
					<td width="25%" class="CONTROL">
					
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
				</tr>		
				  <tr>
					
				
				
				
				<td width="25%" class="LABEL">
			  <div id="strVendorServiceEngNameDivId"  style="display:block" >
				Confirmed By 
				
				
				</div>
				
				<div id="strSpareManufacturerDivId" style="display:none" >
				Supplier/Manufacturer Name
				</div>
				</td>
				<td  class="CONTROL">${equipmentInspectionTestDtlsFB.strEmpName }
				<div class="CONTROL" id="strSpareManufacturerDivId1" style="display:none" >
				</div>
				
			
				</td>
				
					<td width="25%" class="CONTROL">
					
						
					<div id="otherNameDivId" style="display: none;">
					</div>			
										
					</td>
				
					
				<td width="25%"  class="CONTROL"></td>
				
				
					
				</tr>
				
				
				
				
		</table>
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Parameter Details</td>
		</tr>
	</table>
	
		
			<table class="TABLEWIDTH" id="TestParametersTable">
				<bean:write name="equipmentInspectionTestDtlsFB" property="strTestParametersTable" filter="false"/>
			</table>
		
		
			<div id="id1" ></div>

	
		
			
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<!--	<table cellspacing="1px" class="TABLEWIDTH" align="center">-->
			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		
	<!--<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div>
				<a href="#" class="button" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>  -->
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();"/></td>
		</tr>
	</table>

	
		


		<input type="hidden" name="hmode" />
		
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strIsExInFlag" value="0" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${equipmentInspectionTestDtlsFB.strEmpName}" />
		<input type="hidden" name="strChk" value="${equipmentInspectionTestDtlsFB.strChk}"  />
		<input type="hidden" name="strPath" value="${hemComplaintAppDeskFB.strPath}">
		<input type="hidden" name="strPageFlag" value="${equipmentInspectionTestDtlsFB.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strOldDepartmentCode" value="${equipmentInspectionTestDtlsFB.strDeptCode}" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strAttendedContactPerson" value="" />
		<input type="hidden" name="strNormalMsg" value="${equipmentInspectionTestDtlsFB.strNormalMsg}" />
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
	
</html:form>
<jsp:include page="bmed_equipment_inspection_multirow_mst.jsp"></jsp:include>
</body>
</html>