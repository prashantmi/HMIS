<%@page import="hisglobal.hisconfig.Config"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Adil Wasi
 * Date of Creation : 03-Sept-2012
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
<html>
<head>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/reports/js/bmed_equipmentInspectionOrTest_report.js" />
</head>
<body marginheight="0" marginwidth="0" onload="onLoadPage();" >
<html:form  action="/reports/EquipmentInspectionOrTestReportACTION"  method="post">
	<his:TransactionContainer>
		<his:TitleTag name="Equipment Inspection/Test Report">
		</his:TitleTag>	

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td class="LABEL_TD" colspan="4">
						
						<html:radio property="strEquipmentTestChkDetail" name="equipmentInspectionOrTestReportFB" value="1" onclick="setValueChk();">Equipment Wise</html:radio>
				    	<html:radio property="strEquipmentTestChkDetail" name="equipmentInspectionOrTestReportFB" value="2" onclick="setValueChk();">Test Wise</html:radio>
				    	<html:radio property="strEquipmentTestChkDetail" name="equipmentInspectionOrTestReportFB" value="3" onclick="setValueChk();">Test Properties</html:radio>
					
					</td>
				</tr>
	    
				<tr	id="comboId">
			 		
					<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="display:block;">
					    <font color="red">*</font>Store Name</div>
					</td>
					
					<td width="50%" colspan="2" class="CONTROL_TD">
						<div id="divStoreCmb" style="display:block;">
							 <select name="strStoreId"   class="comboNormal" onchange="getEquipmentName();">
							   <bean:write name="equipmentInspectionOrTestReportFB" property="strStoreCmb" filter="false" />
							 </select>
						 </div>
					 </td>
				</tr>
			
					
				 	<tr id="divEuipmentCmb">
		 				<td width="50%" colspan="2" class="LABEL_TD">
								 <font color="red">*</font>Equipment Name
						</td>
						<td width="50%" colspan="2" class="CONTROL_TD">
							<div id="ItemDiv">
						 		<select name="strItemId" class="comboNormal" onchange="getEquipmentSlNo();">
						   			<option value="0">Select value</option>
						   		</select>
						    </div>
						</td>
					</tr>
				 	<tr	 id="divInspentionTestCmb" style="display: none;">
		 				<td width="50%" colspan="2" class="LABEL_TD">
						    	<font color="red">*</font>Inspection/Test Name
						</td>
						<td width="50%" colspan="2" class="CONTROL_TD">
						 		<select name="strTestId"   class="comboNormal" onchange="getEquipmentSlNo();">
						   			<bean:write name="equipmentInspectionOrTestReportFB" property="strTestCmb" filter="false" />
						   		</select>
						</td>
					</tr>
				 	<tr	id="divEuipmentSlNoCmb" style="display:none;">
		 				<td width="50%" colspan="2" class="LABEL_TD">
						    	<font color="red">*</font>Equipment SL.No
						</td>
						<td width="50%" colspan="2" class="CONTROL_TD">
						 	<div id="SlDiv">
						 		<select name="strEquipmentTestSlNoId"   class="comboNormal">
						   			<option value="0">Select value</option>
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
						 value="${equipmentInspectionOrTestReportFB.strCurrentDate}"/>
					</td>
					
				</tr> 
				<tr>
					<td width="50%" colspan="2" class="LABEL_TD">
					<font color="red">*</font>To Date
					</td>
					<td width="50%" colspan="2" class="CONTROL_TD">
						<dateTag:date name="strToDate" 
						 value="${equipmentInspectionOrTestReportFB.strCurrentDate}"/>
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
					<html:checkbox property="strIsFooter" name="equipmentInspectionOrTestReportFB" value="1"></html:checkbox>
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
			
			<img style="cursor: pointer" src="/HBIMS/hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="/HBIMS/hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="equipmentInspectionOrTestReportFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="equipmentInspectionOrTestReportFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="equipmentInspectionOrTestReportFB" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode"/>
        <input type="hidden" name="strCurrentDate" value="${equipmentInspectionOrTestReportFB.strCurrentDate}" />
        <input type="hidden" name="strUniqId" />
			
				
	</his:TransactionContainer>
	<cmbPers:cmbPers/>
</html:form>
</body>
</html>