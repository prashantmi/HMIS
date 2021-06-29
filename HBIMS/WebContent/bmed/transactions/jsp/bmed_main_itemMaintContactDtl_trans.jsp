<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

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
<his:css src="/hisglobal/css/Color.css" />

<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:css src="/hisglobal/css/newpopup.css" />

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/newpopup.js"/>

<script language="JavaScript"
	src="/HBIMS/bmed/transactions/js/bmed_main_itemMaintContactDtl_trans.js"></script>

<script type="text/javascript">

</script>

</head>
<body marginheight="0" marginwidth="0"  onLoad="getOnLoad();">
<html:form name="itemContractDetailsFB"
	action="/transactions/ItemMaintContractDtlsACTION"
	type="bmed.transactions.controller.fb.ItemMaintContractDtlsFB" enctype="multipart/form-data">
	<his:TransactionContainer>
		<div id="TitleHide1" style="display:block;">		
	         <his:TitleTag name="Item Maintenance Contract Details">
	         </his:TitleTag>
	    </div> 
	   
	   <div id="TitleHide2" style="display:none;">		
	         <his:TitleTag name="Non-Item Maintenance Contract Details">
	         </his:TitleTag>
	    </div> 
	   
	   <div class="ERR_DIV" id="errMsg"><bean:write		name="itemContractDetailsFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write			name="itemContractDetailsFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write			name="itemContractDetailsFB" property="strNormalMsg" /></div>
	   
		<his:ContentTag>
				
				  <table class="TABLE_STYLE">
						<tr>
							<td colspan="4" class="LABEL_TD">
							<div align="right"><input type="radio"
								name="strItemMaintenanceContractMode" value="1"
								onClick="changeViewMode(this);" /> Item<input type="radio"
								name="strNonItemMaintenanceContractMode" value="2"
								onClick="changeViewMode(this);" />Non-Item</div>
							</td>
							
						</tr>
					</table>
		</his:ContentTag>	
			
				<div id="itemHide2" style="display:block;">
				<his:ContentTag>
				   <table class="TABLE_STYLE">
						<tr>
							<td width="25%" class="LABEL_TD"><font color="red">*</font>Department
							Name</td>
							<td width="25%" class="CONTROL_TD"><select name="strDeptId"
								class="COMBO_NORMAL" onchange="getStoreName();">
								<bean:write name="itemContractDetailsFB"
									property="strDeptNameCombo" filter="false" />
							</select></td>
		
							<td width="25%" class="LABEL_TD">Store
							Name</td>
							<td width="25%" class="CONTROL_TD">
							<div id="storeNameDiv"><select name="strStoreId"
								class="COMBO_NORMAL" onchange="">
								<bean:write name="itemContractDetailsFB"
									property="strStoreNameCombo" filter="false" /><option value="0">Select Value</option>
							</select>
							
							</div>
		
							</td>
						</tr>
                  </table>
                  </his:ContentTag>
				</div>
			<his:ContentTag>
               <table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering
					Item Type</td>
					<td width="25%" class="CONTROL_TD">
					<div id="engineeringItemTypeId"><select
						name="strEnggItemTypeId" class="COMBO_NORMAL"
						onchange="getEnggItemSubTypeCombo();">
						<bean:write name="itemContractDetailsFB"
							property="strEnggItemTypeCmb" filter="false" />
					</select></div>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering
					Item Sub-Type</td>
					<td width="25%" class="CONTROL_TD">
					<div id="engineeringItemSubTypeId"><select
						name="strEnggItemSubTypeId" class="COMBO_NORMAL" >
						<bean:write name="itemContractDetailsFB"
							property="strEnggItemSubTypeCmb" filter="false" />
						<option value="0">Select Value</option>
					</select></div>
					</td>
				</tr>


				<tr>

					<td width="25%" class="LABEL_TD"><font color="red">*</font>Item
					Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="itemNameId"><select name=strItemBrandId class="COMBO_NORMAL"
						onchange="getStockDtl();">
						<bean:write name="itemContractDetailsFB" property="strItemNameCmb"
							filter="false" />
						<option value="0">Select Value</option>
					</select></div>
					</td>

					<td width="25%" class="CONTROL_TD"></td>


					<td width="25%" class="CONTROL_TD"></td>

				</tr>
			</table>
		</his:ContentTag>

		<his:ContentTag>
			<div id="stockValueId"></div>
		</his:ContentTag>
		<his:ContentTag>
			<div id="prevMaintDtlId"></div>
		</his:ContentTag>
            <div id="reNewTagId"></div>
		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Maintenance
					Contract Supplier</td>
					<td width="25%" class="CONTROL_TD">
					<div id="renewMaintenanceContractSupplierId" style='display:none;'></div>
					<div id="maintenanceContractSupplierId" style='display:block;'><select
						name="strMantContractSuppId" class="COMBO_NORMAL">
						<option value="0">Select Value</option>
					</select></div>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Maintenance
					Contract Type</td>
					<td width="25%" class="CONTROL_TD"><select
						name="strMantContractTypeId" class="COMBO_NORMAL">
						<bean:write name="itemContractDetailsFB"
							property="strMantContractTypeCmb" filter="false" />
						<option value="0">Select Value</option>
					</select></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Maintenance Company Name</td>
					<td width="25%" class="CONTROL_TD"><div id="mantContName"><input type="text"
						class="TEXT_FIELD_MAX" name="strMaintenanceContractName" maxlength="250"
						value=""  onkeypress="return validateData(event,9);"></div></td>
					<td width="25%" class="LABEL_TD">Manufacturer Name</td>
					<td width="25%" class="CONTROL_TD"><div id="manufacturerName"><input type="text"
						class="TEXT_FIELD_MAX" name="strManufacturerName" maxlength="50"
						value=""  onkeypress="return validateData(event,9);"></div></td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD">Tender No</td>
					<td width="25%" class="CONTROL_TD"><div id="tenderNoDiv"><input type="text"
						class="TEXT_FIELD_MAX" name="strTenderNo" maxlength="30" value=""
						onkeypress="return validateDataWithSpecialChars(event,9 ,'\')"></div></td>
						
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Tender Date</td>
					<td width="25%" class="CONTROL_TD"><div id="tenderDateDiv"><dateTag:date
						name="strTenderDate"
						value=""></dateTag:date></div></td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Order No</td>
					<td width="25%" class="CONTROL_TD"><div id="orderNoDiv"><input type="text"
						class="TEXT_FIELD_MAX" name="strOrderNo" maxlength="30" value=""
						onkeypress="return validateDataWithSpecialChars(event,9 ,'\')"></div></td>
						
					<td width="25%" class="LABEL_TD">Order Date</td>
					<td width="25%" class="CONTROL_TD"><div id="orderDateDiv"><dateTag:date
						name="strOrderDate"
						value=""></dateTag:date></div></td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>From
					Date</td>
					<td width="25%" class="CONTROL_TD"><div id="fromDateDiv"><dateTag:date
						name="strFromDate"
						value=""></dateTag:date></div></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>To Date</td>
					<td width="25%" class="CONTROL_TD"><div id="toDateDiv"><dateTag:date
						name="strToDate" value=""></dateTag:date></div>
					</td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Preventive Maintenance</td>
					<td width="25%" class="CONTROL_TD"><div id="routineFreqDiv"><input type="text"
						class="TEXT_FIELD_SMALL" name="strRoutineFrequency" maxlength="2"
						value="" onkeypress="return validateData(event,5);"></div></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Unit
					Name</td>
					<td width="25%" class="CONTROL_TD"><div id="routineFreqUnitDiv"><select
						name="strRoutineUnitId" class="COMBO_NORMAL">
						<bean:write name="itemContractDetailsFB" property="strUnitIdCmb"
							filter="false" />
					</select></div><div id="cancelRoutineFreqUnitDiv"></div></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Response
					Time</td>
					<td width="25%" class="CONTROL_TD"><div id="responseTimeDiv"><input type="text"
						class="TEXT_FIELD_SMALL" name="strResponseTime" maxlength="1" value=""
						onkeypress="return validateData(event,5);"></div></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Unit
					Name</td>
					<td width="25%" class="CONTROL_TD"><div id="responseTimeUnitDiv"><select
						name="strResponseTimeUnitId" class="COMBO_NORMAL">
						<bean:write name="itemContractDetailsFB" property="strUnitIdCmb"
							filter="false" />
					</select></div><div id="cancelResponseTimeUnitDiv"></div></td>
				</tr>

				<tr>
					<td class="LABEL_TD" width="25%">
					<div align="right"><font color="red">*</font>Maintenance Cost</div>
					</td>
					<td class="CONTROL_TD"><div id="mantCostDiv"><input type="text" class="TEXT_FIELD_MAX"
						name="strMaintenanceCost" maxlength="10" value=""
						onkeypress="return(numericWithTwoDecimalPlaces(this,',','.',event));"></div></td>

					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD"><div id="remarksDiv"><textarea name="strRemarks" 
						cols="25" rows="2" ></textarea></div></td>
				</tr>


				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Terms &amp; Condition</td>
					<td width="25%" class="CONTROL_TD"><div id="termsCondDiv"><textarea
						name="strTermsAndCond" cols="25" rows="2" onkeypress="return validateData(event,9);"
						></textarea></div></td>

					<td width="25%" class="LABEL_TD"><font color="red">*</font>Penalty Conditions</td>
					<td width="25%" class="CONTROL_TD"><div id="penaltyDiv"><textarea
						name="strPeneltyCond" cols="25" rows="2"  onkeypress="return validateData(event,9);"
						></textarea></div></td>
				</tr>


			</table>
		</his:ContentTag>

		<his:ContentTag>
			<jsp:include page="/bmed/global/bmed_uploadFile.jsp"></jsp:include>
		</his:ContentTag>


		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;"
				src="/HBIMS/hisglobal/images/btn-sv.png"
				onClick="return validate1();" />
			<img style="cursor: pointer;"
				src="/HBIMS/hisglobal/images/btn-clr.png" onClick="ClearPage();">
			<img style="cursor: pointer;"
				src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>

<br><br>
		<div class="ERR_DIV" id="errMsg"><bean:write		name="itemContractDetailsFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write			name="itemContractDetailsFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write			name="itemContractDetailsFB" property="strNormalMsg" /></div>

		<input type="hidden" name="hmode" />

		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strCheck" value="0" />
		<input type="hidden" name="strIsReNew" value="0" />
		<input type="hidden" name="strIsReNewPK" value="0" />
		<input type="hidden" name="strIsCancel" value="0" />



		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>

				<div id="strRenew" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
	</his:TransactionContainer>
</html:form>
</body>
</html>