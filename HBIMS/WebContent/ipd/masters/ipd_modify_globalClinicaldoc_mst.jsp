<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Global Clinical Document Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript">
function validate1()
{   
            var hisValidator = new HISValidator("globalDocumentBean");
            hisValidator.addValidation("strDocumentName", "req", "Global Document Name is a Mandatory Field" );
            hisValidator.addValidation("strDocumentName", "maxlen=50", "Global Document Name should have less than or equal to 50 Characters" );
       		hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            //hisValidator.addValidation("strEffectiveFrom", "date","Effective From Date is a mandatory field");
            //hisValidator.addValidation("strEffectiveFrom", "dtgtet=${globalDocumentBean.strCtDate}","Effective From Date should be Greater than or Equal to Current Date");
            var retVal = hisValidator.validate(); 
          if(retVal)
          {
           	   document.forms[0].hmode.value = "UPDATE";
               document.forms[0].submit();
          }
          else
          {
             return false;
          }
}
</script>
</head>
<body onLoad="document.forms[0].strDocumentName.focus()">
<html:form action="/masters/GlobalClinicalDocumentMstACTION">

<div class="errMsg"><bean:write name="globalDocumentBean" property="strErrorMsg"/></div>
<div class="normalMsg" id="normalMsg"></div>  
<tag:tab tabLabel="Modify Global Clinical Document" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Global Clinical Document Master&gt;&gt;Modify</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Document Name</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strDocumentName"
				class='txtFldMax' value="${globalDocumentBean.strDocumentName}"
				maxlength="50" onkeypress="return validateData(event,9);"></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Effective From</td>
			<td class="CONTROL">
				<bean:write	name="globalDocumentBean" property="strEffectiveFrom" />
			</td>
		</tr>
		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="LABEL">
			<div align="left"><textarea onkeyup="lTrim(this);"
				onblur="Trim(this);" name="strRemarks" rows="2"><bean:write
				name="globalDocumentBean" property="strRemarks" /></textarea></div>
			</td>
		</tr>
		<tr>
			<td width="45%" class="LABEL">Record Status</td>
			<td width="45%" class="CONTROL">
			<html:radio name="globalDocumentBean" property="strIsValid" value="1">Active</html:radio>
			<html:radio name="globalDocumentBean" property="strIsValid" value="2">InActive</html:radio>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			<img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer" onClick="return validate1(); "/>
				<img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer"
				onClick="cancel('LIST');"/></td>
		</tr>
	</table>
	<input type="hidden" name="chk" value="${globalDocumentBean.strChk1}">
	<input type="hidden" name="strEffectiveFrom" value="${globalDocumentBean.strEffectiveFrom}">
	<input type="hidden" name="hmode">
<cmbPers:cmbPers/> 
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>