<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Anshuman Singh
 * Date of Creation : 25-Oct-2013
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
<html>
<head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/reports/js/bmed_dynamic_desk_report.js" />
</head>
<body marginheight="0" marginwidth="0" onload="onLoadPage();" >

<html:form  action="/reports/DynamicReportDeskCNT"  method="post">
	<his:TransactionContainer>
		<his:TitleTag name="Dynamic Report Desk">
		</his:TitleTag>	

		<his:ContentTag>
			<table class="TABLE_STYLE">
				
	    
		<tr	id="comboId">
		 		
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="dispaly:block;">
						<font color="red">*</font>Hospital Name
					</div>
				</td>
				<td width="50%" colspan="2" class="CONTROL_TD">
					<div id="divDeptCmbId" style="display:block;">
					 <select name="strDeptId"   class="comboNormal" onchange="getStoreName()">
					   <bean:write name="DynamicReportDeskFB" property="strDeptCmb" filter="false" />
					 </select>
					 </div>
				</td>
		</tr> 
		
		<tr >
					<td width="50%" colspan="2" class="LABEL_TD">Lab Name
					</td>

					<td width="50%" colspan="2" class="CONTROL_TD">	
						<div id="storeNameId">					
							<select name="strStoreId" class='COMBO_NORMAL' onchange="getItemNameOnBasisOfStore();">
								<option value="0">Select Value</option>
								<bean:write name="DynamicReportDeskFB" property="strStoreNameCombo" filter="false" />
									
							</select>
						</div>
					</td>				
						
		</tr>			
		
		<tr	id="comboId">
		 		
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="dispaly:block;">
						<font color="red">*</font>Category
					</div>
				</td>
				<td width="50%" colspan="2" class="CONTROL_TD">
					<div id="divItemCmbId" style="display:block;">
					 <select name="strItemId"   class="comboNormal">
					   <option value="%">Select Category</option>
					   <option value="StockInHand">Stock In Hand</option>
					   <option value="Installation">Installation</option>
					   <option value="Down-UpTime">Down/UP Time</option>
					   <option value="Inspection-Test">Inspection/Test</option>
					   <option value="Complaints">Complaints</option>
					   <option value="Maintenance">Maintenance</option>
					   <option value="Warranty">Warranty</option>
					   <option value="Estimation">Estimation</option>
					   <option value="Costing">Costing</option>
					   <option value="Planning">Planning</option>
					   <option value="Budgeting">Budgeting</option>
					   <option value="Expenditure">Expenditure</option>
					   <option value="Revenue">Revenue</option>
					   <option value="Auction">Auction</option>					   
					   
					 </select>
					 </div>
				</td>
		</tr>
		
		
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			<font color="red">*</font>From Date
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strFromDate" 
				 value="${DynamicReportDeskFB.strCurrentDate}"/>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			<font color="red">*</font>To Date
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strToDate" 
				 value="${DynamicReportDeskFB.strCurrentDate}"/>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<html:checkbox property="strIsFooter" name="DynamicReportDeskFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			User Remarks
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<input class="txtFldMax" type="text" name="strUserRemarks" maxlength="100">
			</td>
			
		</tr>
	
			</table>
		</his:ContentTag>
		
		
		
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					 <td colspan="4"><font color='red'>*</font>Mandatory Field </td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			
			<img style="cursor: pointer" src="/HEMMS_ODISHA/hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="DynamicReportDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="DynamicReportDeskFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="DynamicReportDeskFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        <input type="hidden" name="strCurrentDate" value="${DynamicReportDeskFB.strCurrentDate}" />
        <input type="hidden" name="strUniqId" />
			
				
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>