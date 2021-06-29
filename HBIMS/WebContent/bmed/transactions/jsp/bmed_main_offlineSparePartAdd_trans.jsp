<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%--
/**
 * Developer Name : Vivek Aggarwal
 * Process Name : Offline Spare Part Add
 * Date : 10/May/2011
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


<his:javascript	src="/bmed/transactions/js/bmed_offlineSparePartAdd_trans.js" />

</head>

<body marginheight="0" marginwidth="0" >

<html:form name="offlineSparePartAddTransFB" action="/transactions/OfflineSparePartAddTransACTION"
	type="bmed.transactions.controller.fb.OfflineSparePartAddTransFB">
	
	<his:TransactionContainer>
	
		<his:TitleTag name="Offline Spare Part Add">
		</his:TitleTag>
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
	
<%-- Department Name & Store Name --%>
		
					<tr>
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Department Name</td>
						
						<td width="25%" class="CONTROL_TD">
							<select name="strDeptCode" class='COMBO_NORMAL' onchange="getStoreName();emptyDetails();">
								<bean:write name="offlineSparePartAddTransFB" property="strDeptNameCombo" filter="false" />
							</select>
						</td>				
					
						
						<td width="25%" class="LABEL_TD">
									<font color="red">*</font>Store Name</td>

						<td id="storeNameId" width="25%" class="CONTROL_TD">	
									
							<select name="strStoreId" class='COMBO_NORMAL' >
								<bean:write name="offlineSparePartAddTransFB" property="strStoreNameCombo" filter="false" />
									<option value="0">Select Value</option>
							</select>
						</td>
					</tr>	
					
<%-- Engineering Item Type & Engineering Item Sub-Type --%>
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Type</td>
						<td width="25%" class="CONTROL_TD">
							<div id="engineeringItemTypeId">
								<select	name="strEngineeringItemTypeId" class='COMBO_NORMAL' onchange="getEnggItemSubTypeCombo();">
									<bean:write name="offlineSparePartAddTransFB" property="strEngineeringItemTypeCmb" filter="false" />
								</select>
							</div>
						</td>
						
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering Item Sub Type</td>
						<td id="enggItemSubTypeCmbDivId" width="25%" class="CONTROL_TD">						
								<select name="strEngineeringItemSubTypeId" class='COMBO_NORMAL'>
									<bean:write name="offlineSparePartAddTransFB" property="strEngineeringItemSubTypeCmb" filter="false" />
										<option value="0">Select Value</option>
								</select>
						</td>
					</tr>
					
<%-- Item Category &  Item Name --%>
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Item Category</td>
						<td id="itemCategoryNameId" width="25%" class="CONTROL_TD">
								<select name=strItemCategoryId class='COMBO_NORMAL' >
									<bean:write name="offlineSparePartAddTransFB" property="strItemCategoryNameCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>

						</td>
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Item Name</td>
						<td id="itemNameId"  width="25%" class="CONTROL_TD">
								<select name="strItemId" class='COMBO_NORMAL' >
									<bean:write name="offlineSparePartAddTransFB" property="strItemNameCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			
				
	<%-- Stock Details --%>
					
		<his:ContentTag>
			<div id="stockValueId"></div>
		</his:ContentTag>
		
<%-- Spare Part Stock Details --%>
	    <his:SubTitleTag name="Spare-part Stock Details">
		</his:SubTitleTag>
		
		
		<his:ContentTag name="Spare-part Stock Details">
			<table class="TABLE_STYLE" id="sparePartStockDetailsId">
				<bean:write name="offlineSparePartAddTransFB" property="strSparePartStockDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

			
			<his:ContentTag>
			<table class="TABLE_STYLE">

<%-- Spare-Part Name & Spare-Part Serial No --%>
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Spare-Part Name</td>
						<td id="sparePartId" width="25%" class="CONTROL_TD">
								<select name="strSparePartId" class='COMBO_NORMAL' >
									<bean:write name="offlineSparePartAddTransFB" property="strSparePartNameCombo" filter="false" />
										<option value="0">Select Value</option>
								</select>
						</td>
					
						<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Spare-Part Serial No</td>
					
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" maxlength="20" name=strSparePartSerialNo onkeypress="return validateData(event,8);">
						</td>
					
					</tr>
					
<%-- Manufacturer Name & Manufacturer Serial No --%>
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Manufacturer Name</td>
						<td width="25%" class="CONTROL_TD">
								<select name="strManufacturerId" class='COMBO_NORMAL' >
									<bean:write name="offlineSparePartAddTransFB" property="strManufacturerNameCombo" filter="false" />
								</select>
						</td>
					
						<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Manufacturer Serial No</td>
					
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" maxlength="20" name="strManufacturerSerialNo" onkeypress="return validateData(event,8);">
						</td>
					</tr>
					
<%-- Warranty From Date  --%>
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Warranty From Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strWarrantyFromDate" value="${offlineSparePartAddTransFB.strWarrantyFromDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"></td>
						<td width="25%" class="LABEL_TD"></td>
					</tr>
					
<%-- Warranty Upto &  Unit --%>

					<tr>
						<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Warranty Upto</td>
					
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" maxlength="3" name="strWarrantyUpto" onkeypress="return validateData(event,5);">
						</td>
						
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Unit</td>
						<td width="25%" class="CONTROL_TD">
								<select name="strUnitId" class='COMBO_NORMAL' >
									<bean:write name="offlineSparePartAddTransFB" property="strUnitNameCombo" filter="false" />
								</select>
						</td>
					</tr>
					
<%-- Specification --%>
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Specification</td>
						<td width="25%" class="CONTROL_TD">
							<html:textarea name="offlineSparePartAddTransFB" property="strSpecification" cols="25" rows="2"  />
						</td>
						
						<td width="25%" class="CONTROL_TD"></td>
						
						<td width="25%" class="CONTROL_TD"></td>
					</tr>
				
<%-- Performed Date  --%>
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Performed Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strPerformedDate" value="${offlineSparePartAddTransFB.strPerformedDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"></td>
						<td width="25%" class="LABEL_TD"></td>
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
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelThePage();" onkeypress="if(event.keyCode==13) cancelThePage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write name="offlineSparePartAddTransFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write name="offlineSparePartAddTransFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write name="offlineSparePartAddTransFB" property="strNormalMsg" /></div>
		
		<input type="hidden" name="hmode" value="" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="ManufactNo" value=""  />
	

</his:TransactionContainer>
</html:form>
</body>
</html>