<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Dynamic Report Template Master Add</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
 
<script type="text/javascript">


function validateInsert(){   
     
           var hisValidator = new HISValidator("templateBean");
           
            hisValidator.addValidation("strReportName", "req", "Report Name is a Mandatory Field" );  
            hisValidator.addValidation("strDisplayName", "req", "Display Name is a Mandatory Field" );  
            hisValidator.addValidation("strReportHeaderTypeId", "dontselect=0", "Please Select a Report Header Type" );
            hisValidator.addValidation("strReportWidth", "req", "Report Width is a Mandatory Field" );
            hisValidator.addValidation("strReportWidthUnit", "dontselect=0", "Please Select a Report Width Unit" );
            
            var retVal = hisValidator.validate(); 

          if(retVal){
                 	   document.forms[0].hmode.value = "SAVE";
                       document.forms[0].submit();
          }else{
             return false;
          }
}




</script>

<!-- 
/**
 * @author Balasubramaniam M
 * Date of Creation : 17/4/2012
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Dynamic Report Generation
 */
 -->
</head>
<body class="background">
<html:form action="/masters/DynamicReportTemplateMstCNT"
	name="templateBean"
	type="dynamicreports.masters.controller.fb.DynamicReportTemplateMstFB" styleClass="formbg">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="templateBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="templateBean" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="templateBean" property="strNormalMsg" /></div>


	</center>

	<table class="TABLEWIDTH" align="center">
		<tr class="HEADER">
			<td colspan="4">Dynamic Report Template Master&gt;&gt; Add</td>
		</tr>


	</table>


	<table border="0" class="TABLEWIDTH" align="center" border='0'
		cellspacing='1px' cellpadding='1px'>

		<tr>
			<td colspan="2" class="LABEL">Report Type</td>
			<td colspan="2" class="CONTROL"><bean:write
				name="templateBean" property="strReportTypeName" filter="false" />
			</td>
			</tr>
		<tr>
			<td colspan="2" class="LABEL"><font color="red">*</font>Report Name</td>
			<td colspan="2" class="CONTROL"><input type="text" class="txtFldMax"  name="strReportName" maxlength="100" value=""
				onkeypress="return validateData(event,18);"> </td>
			 
		</tr>

<tr>
			<td colspan="2" class="LABEL"><font color="red">*</font>Display Name</td>
			<td colspan="2" class="CONTROL"><input type="text" class="txtFldBig"  name="strDisplayName" maxlength="250" value=""
				onkeypress="return validateData(event,18);"> </td>
			 
		</tr>
		
		
		<tr>
			<td colspan="2" class="LABEL"><font color="red">*</font>Header Type</td>
			<td colspan="2" class="CONTROL"> <select name="strReportHeaderTypeId" class="comboMax" >
			<bean:write name="templateBean" property="strReportHeaderTypes" filter="false"/>
			</select> </td>
			 
		</tr>
		
		
		<tr>
			<td colspan="2" class="LABEL">Border Required</td>
			<td colspan="2" class="CONTROL"> <html:checkbox property="strIsBorder" name="templateBean" value="1"></html:checkbox> </td>
			 
		</tr>

		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Report Width</td>
			<td colspan="1" class="CONTROL"><input type="text"
				class="txtFldNormal" name="strReportWidth" maxlength="3" value=""
				onkeypress="return validateData(event,5);"></td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Report Width Unit
			</td>
			<td colspan="1" class="CONTROL"><select name="strReportWidthUnit"
				class='comboNormal'>
				<option value="0">Select Value</option>
				<option value="1">Percentage</option>
				<option value="2">Pixel</option>
			</select></td>
		</tr>
 
		<tr class="FOOTER">
			<td colspan="4"></td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
					<div class="legends">
						<font color='red'>*</font>Mandatory Fields
					</div>
					<div class="control_button" align="left">
						<a href="#" class="button" title="Save Record" tabindex="1"
							onClick="return validateInsert();"  onkeypress="return validateInsert();"><span class="save">Save</span></a>
						<a href="#" class="button" title="Reset Content" tabindex="1"
							onkeypress="document.forms[0].reset();"  onClick="document.forms[0].reset();"><span class="clear">Clear</span></a>
						<a href="#" class="button" tabindex="1" onkeypress="cancel('LIST');" onClick="cancel('LIST');"><span
							class="cancel">Cancel</span></a>
					</div>
				</td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="comboValue"
		value="${templateBean.comboValue}">
	<input type="hidden" name="strChkValue" value="${templateBean.chk}">
	 
	<cmbPers:cmbPers />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>