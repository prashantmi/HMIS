<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmb"%>

<%--
/**
 * Developer Name : Vivek Aggarwal
 * Process Name : List Of Equipment Under Amc Report
 * Date : 23-June-2011
 * Modify By/Date : 
 */ 
 --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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


<his:javascript	src="/bmed/reports/js/bmed_listOfEquipmentUnderAmc_report.js" />

</head>

<body marginheight="0" marginwidth="0"  onload="onLoadPage();">

<html:form name="listOfEquipmentUnderAmcReportFB" action="/reports/ListOfEquipmentUnderAmcReportACTION"
	type="bmed.reports.controller.fb.ListOfEquipmentUnderAmcReportFB">
	
	<his:TransactionContainer>
	
		<his:TitleTag name="List Of Equipments Under Amc Report">
		</his:TitleTag>
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
	

		
		<tr>
			<td class="LABEL_TD" colspan="4">
				
				<html:radio property="strEquipOrVendorOrAmcType" name="listOfEquipmentUnderAmcReportFB" value="1" onclick="setValueChk();">Equipment</html:radio>
		    	<html:radio property="strEquipOrVendorOrAmcType" name="listOfEquipmentUnderAmcReportFB" value="2" onclick="setValueChk();">Vendor</html:radio>
		    	<html:radio property="strEquipOrVendorOrAmcType" name="listOfEquipmentUnderAmcReportFB" value="3" onclick="setValueChk();">AMC Type</html:radio>
		    	<html:radio property="strEquipOrVendorOrAmcType" name="listOfEquipmentUnderAmcReportFB" value="4" onclick="setValueChk();">Date</html:radio>
			
			</td>
		</tr>
			
		<logic:notEqual name="listOfEquipmentUnderAmcReportFB"  property="strEquipOrVendorOrAmcType" value="4">
		<tr>
		
			<logic:equal name="listOfEquipmentUnderAmcReportFB"  property="strEquipOrVendorOrAmcType" value="1">
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="display:block;"><font color="red">*</font>Equipment</div>
				</td>
			</logic:equal>
			
			<logic:equal name="listOfEquipmentUnderAmcReportFB"  property="strEquipOrVendorOrAmcType" value="2">
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="display:block;"><font color="red">*</font>Vendor</div>
				</td>
			</logic:equal>
			
			<logic:equal name="listOfEquipmentUnderAmcReportFB"  property="strEquipOrVendorOrAmcType" value="3">
				<td width="50%" colspan="2" class="LABEL_TD">
					<div id="id1" style="display:block;"><font color="red">*</font>AMC Type</div>
				</td>
			</logic:equal>
			
			
			<td width="50%" colspan="2" class="CONTROL_TD">
				<select name="strUniqId"   class="COMBO_NORMAL">
					<bean:write name="listOfEquipmentUnderAmcReportFB" property="strUniqValCmb" filter="false" />
				</select>
			</td>
			
		</tr> 
		</logic:notEqual>
		
		
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
				<font color="red">*</font>From Date
			</td>
			
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strFromDate"  value="${listOfEquipmentUnderAmcReportFB.strCurrentDate}"/>
			</td>
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
				<font color="red">*</font>To Date
			</td>
			
			<td width="50%" colspan="2" class="CONTROL_TD">
				<dateTag:date name="strToDate" 
				 value="${listOfEquipmentUnderAmcReportFB.strCurrentDate}"/>
			</td>
		</tr> 

		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<select name="strReportFormat" class="COMBO_NORMAL" onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<html:checkbox property="strIsFooter" name="listOfEquipmentUnderAmcReportFB" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL_TD">
			User Remarks
			</td>
			<td width="50%" colspan="2" class="CONTROL_TD">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
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
		
		<img style="cursor: pointer" src="/HBIMS//hisglobal/images/btn-generate.png" onClick="return validate();" onkeypress="if(event.keyCode==13) return validate();"/>
		<img style="cursor: pointer" src="/HBIMS//hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" onkeypress="if(event.keyCode==13) document.forms[0].reset();"/>
		<img style="cursor: pointer" src="/HBIMS//hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13)  cancelPage();"/>
		
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write name="listOfEquipmentUnderAmcReportFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write name="listOfEquipmentUnderAmcReportFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write name="listOfEquipmentUnderAmcReportFB" property="strNormalMsg" /></div>
		

<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${listOfEquipmentUnderAmcReportFB.strCurrentDate}" />
	

</his:TransactionContainer>
<cmb:cmbPers/>
</html:form>
</body>
</html>