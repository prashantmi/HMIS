<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>


<!-- 
/**
 * @author Amit Kumar
 * Date of Creation : 28-April-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
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

<his:javascript src="/bmed/transactions/js/bemd_main_ItemNonItemWarrantyDtl_trans.js"/>

<script type="text/javascript">

</script>

</head>
<body marginheight="0" marginwidth="0"  onLoad="getOnLoad();">
<html:form name="itemWarrantyDetailsFB"
	action="/transactions/ItemWarrantyDtlsACTION"
	type="bmed.transactions.controller.fb.ItemWarrantyDtlsFB" enctype="multipart/form-data">
	
	
	
	<his:TransactionContainer>
		<div id="TitleHide1" style="display:block;">		
	         <his:TitleTag name="Item Warranty Details">
	         </his:TitleTag>
	    </div> 
	   
	   <div id="TitleHide2" style="display:none;">		
	         <his:TitleTag name="Non-Item Warranty Details">
	         </his:TitleTag>
	    </div> 
	   
	   <div class="ERR_DIV" id="errMsg"><bean:write	name="itemWarrantyDetailsFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write		name="itemWarrantyDetailsFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write			name="itemWarrantyDetailsFB" property="strNormalMsg" /></div>
		
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
								<bean:write name="itemWarrantyDetailsFB"
									property="strDeptNameCombo" filter="false" />
							</select></td>
		
							<td width="25%" class="LABEL_TD">Store
							Name</td>
							<td width="25%" class="CONTROL_TD">
							<div id="storeNameDiv"><select name="strStoreId"
								class="COMBO_NORMAL" onchange="">
								<bean:write name="itemWarrantyDetailsFB"
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
						<bean:write name="itemWarrantyDetailsFB"
							property="strEnggItemTypeCmb" filter="false" />
					</select></div>
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Engineering
					Item Sub-Type</td>
					<td width="25%" class="CONTROL_TD">
					<div id="engineeringItemSubTypeId"><select
						name="strEnggItemSubTypeId" class="COMBO_NORMAL" >
						<bean:write name="itemWarrantyDetailsFB"
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
						<bean:write name="itemWarrantyDetailsFB" property="strItemNameCmb"
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
			<div id="prevWarrantyDtlId"></div>
		</his:ContentTag>
            <div id="extendTagId"></div>
		<his:ContentTag>
		  <div id="hideWarrantyDtlId" style="display:block;">
			<table class="TABLE_STYLE">
			
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Manufacturer Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="extendeSupplierId" style='display:none;'></div>
						    <div id="supplierIdDiv" style='display:block;'>
						      <select name="strSupplierId" class="COMBO_NORMAL">
								    <option value="0">Select Value</option>
							</select></div>
					
			    	</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Warranty Start Date</td>
					<td width="25%" class="CONTROL_TD"><div id="warrantyStartDateDiv"><dateTag:date
						name="strWarrantyStartDate"
						value=""></dateTag:date></div></td>
				</tr>
				
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Warranty UpTo
					</td>
					<td width="25%" class="CONTROL_TD"><div id="warrantyUpToDiv"><input type="text"
						class="TEXT_FIELD_SMALL" name="strWarrantyUpTo" maxlength="3"
						value="" onkeypress="return validateData(event,5);"></div></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Unit
					</td>
					<td width="25%" class="CONTROL_TD"><div id="routineFreqUnitDiv"><select
						name="strWarrantyId" class="COMBO_NORMAL" >
						<bean:write name="itemWarrantyDetailsFB" property="strUnitIdCmb"
							filter="false" />
					</select></div></td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD">Tender No</td>
					<td width="25%" class="CONTROL_TD"><div id="tenderNoDiv"><input type="text"
						class="TEXT_FIELD_MAX" name="strTenderNo" maxlength="30" value=""
						 onkeypress="return validateDataWithSpecialChars(event,9 ,'\')"></div></td>
					<td width="25%" class="LABEL_TD">Tender Date</td>
					<td width="25%" class="CONTROL_TD"><div id="tenderDateDiv"><dateTag:date
						name="strTenderDate"
						value=""></dateTag:date></div></td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Order No</td>
					<td width="25%" class="CONTROL_TD"><div id="orderNoDiv"><input type="text"
						class="TEXT_FIELD_MAX" name="strOrderNo" maxlength="30" value=""
						onkeypress="return validateDataWithSpecialChars(event,9 ,'\')"></div></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Order Date</td>
					<td width="25%" class="CONTROL_TD"><div id="orderDateDiv"><dateTag:date
						name="strOrderDate"
						value=""></dateTag:date></div></td>
				</tr>

				
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Installation Date</td>
					<td width="25%" class="CONTROL_TD">
						<div id="orderDateDiv">
							<dateTag:date name="strInstallationDate" value="${itemWarrantyDetailsFB.strCurrentDate}"></dateTag:date>
						</div>
					</td>
					<td width="25%" class="LABEL_TD">Terms &amp; Condition</td>
					<td width="25%" class="CONTROL_TD"><div id="terms&CondDiv"><textarea
						name="strTermsAndCond" cols="25" rows="2" ></textarea></div></td>

					
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Remarks</td>
						<td width="25%" class="CONTROL_TD">
							<div id="remarksDiv">
								<textarea name="strRemarks" cols="25" rows="2" ></textarea>
							</div>
						</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				
	    	</table>
  		 </div>
  		 <div id="hideWarrantyExtndDtlId" style='display:none;'>
  		 
  		 
  		 <table class="TABLE_STYLE">
			
				<tr>
					<td width="25%" class="LABEL_TD">Supplier Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="extndSupplierDivId"></div>
			    	</td>
					<td width="25%" class="LABEL_TD">Warranty Start Date</td>
					<td width="25%" class="CONTROL_TD"><div id="exntdWarrantyStartDateDiv"></div></td>
				</tr>
				
				<tr>
					<td width="25%" class="LABEL_TD">Warranty UpTo</td>
					<td width="25%" class="CONTROL_TD"><div id="exntdWarrantyUpToDiv"></div></td>
					<td width="25%" class="LABEL_TD">Unit</td>
					<td width="25%" class="CONTROL_TD"><div id="warrantyUpToUnitDiv"></div></td>
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD">Terms &amp; Condition
					</td>
					<td width="25%" class="CONTROL_TD"><div id="termsAndCondDiv"></div></td>
					<td width="25%" class="CONTROL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
					
				</tr>

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Extended Warranty Date</td>
					<td width="25%" class="CONTROL_TD"><div id="orderNoDiv"><dateTag:date
						name="strExtndWarrantyStartDate"
						value=""></dateTag:date></div></td>
					<td width="25%" class="CONTROL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>


                 <tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Warranty UpTo
					</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						class="TEXT_FIELD_SMALL" name="strExtndWarrantyUpTo" maxlength="3"
						value="" onkeypress="return validateData(event,5);"></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Unit
					</td>
					<td width="25%" class="CONTROL_TD"><select
						name="strExtndWarrantyId" class="COMBO_NORMAL" >
						<bean:write name="itemWarrantyDetailsFB" property="strUnitIdCmb"
							filter="false" /></select>
					</td>
				</tr>
				
				<tr>
					<td width="25%" class="LABEL_TD">Terms &amp; Condition</td>
					<td width="25%" class="CONTROL_TD"><div id="terms&CondDiv"><textarea
						name="strExtndTermsAndCond" cols="25" rows="2" ></textarea></div></td>

					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD"><div id="remarksDiv"><textarea name="strExtndRemarks" cols="25" rows="2" ></textarea></div></td>
				</tr>
				
	    	</table>
  		 
  		 
  		 
  		 
  		 
  		 </div>
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

		

		<input type="hidden" name="hmode" />

		<input type="hidden" name="strStockInfoVal" value="0" />
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strCheck" value="0" />
		<input type="hidden" name="strIsReNew" value="0" />
		<input type="hidden" name="strIsExtend" value="0" />
		<input type="hidden" name="strIsExtendPK" value="0" />
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