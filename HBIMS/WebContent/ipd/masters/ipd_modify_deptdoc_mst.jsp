<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Department Document Master Modify Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>

<script type="text/javascript">

function validate1()
{
	var retCheck;
	var hisValidator = new HISValidator("deptdocBean"); 
	//hisValidator.addValidation("strEffectiveFrom", "dtgtet=${deptdocBean.strEffectiveFrom}", "Effective Date should be Greater than Current Date" );
	hisValidator.addValidation("strComponentFile", "req", "File Name is mandatory Field" );	
//	hisValidator.addValidation("strEffectiveFrom", "dtgtet=${deptdocBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
	retVal = hisValidator.validate(); 
		
			if(retVal){
				
				document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
			}else{		
			return false;
			}
}

</script>

</head>
<body onload="document.forms[0].strDeptName.focus();">
<html:form action="/masters/CNTDeptDocumentMst">
<div class="errMsg"><bean:write name="deptdocBean" property="strErrorMsg"/></div>
	<div class="warningMsg"><bean:write name="deptdocBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"></div>
	<tag:tab tabLabel="Modify Department Document" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="4">Department Document Details&gt;&gt; Modify</td>
  </tr>
  <tr >
    <td colspan="3" class="LABEL">Department Name </td>
    <td colspan="1" width="50%" class ="CONTROL"><bean:write name="deptdocBean" property="strDeptName"/></td>
  </tr>
  <tr>
    <td colspan="3" class="LABEL">Document Name </td>
    <td colspan="1" width="50%" class ="CONTROL"><bean:write name="deptdocBean" property="strDocumentName"/></td>
   </tr>
   <tr >
    <td colspan="3" class="LABEL">Component Name </td>
    <td colspan="1" width="50%" class ="CONTROL"><bean:write name="deptdocBean" property="strComponentName"/></td>
  </tr>
  <tr>
    <td colspan="3" class="LABEL"><font color="red">*</font>File Name</td>
    <td colspan="1" width="50%" class ="CONTROL">
    <input type="text" onkeyup="lTrim(this);" onblur="Trim(this);" name="strComponentFile" class='txtFldMax' value ="${deptdocBean.strComponentFile}" maxlength="50" onkeypress="return validateData(event,9);"> </td>
  </tr>
  <tr >
    <td colspan="3" class="LABEL">Remarks</td>
    <td colspan="1" width="50%" class ="CONTROL"><textarea onkeyup="lTrim(this);" onblur="Trim(this);" rows="2" cols="20" name="strRemarks"><bean:write name="deptdocBean" property="strRemarks"/></textarea></td>
  </tr>
  <tr >
    <td colspan="3" class ="LABEL" width ="50%">Effective From</td>
    <td colspan="1" class ="CONTROL"><bean:write name="deptdocBean" property="strEffectiveFrom" filter="false" /></td>
  </tr>  
  <tr> 
    <td colspan="3" class="LABEL">Record Status</td>
    <td colspan="1" class="CONTROL"> 
      <html:radio name="deptdocBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="deptdocBean" property="strIsValid" value="2">InActive</html:radio>
    </td>
    </tr>
  <tr> 
    <td colspan="3" class="LABEL">Is Default</td>
    <td colspan="1" class="CONTROL"> 
      <html:radio name="deptdocBean" property="strIsDefault" value="0">No</html:radio>
    <html:radio name="deptdocBean" property="strIsDefault" value="1">Yes</html:radio>
    </td>
  </tr>
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="right"><img
				src="../../hisglobal/images/btn-sv.png"
				style="cursor:pointer" onClick=" return validate1();" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
				style="cursor:pointer" onClick="cancel('LIST');" /></td>
		</tr>
	</table>		
	<input type="hidden" name="chk" value="${deptdocBean.chk[0]}">	
	<input type="hidden" name="strEffectiveFrom" value ="${deptdocBean.strEffectiveFrom }" />	
	<input type="hidden" name="hmode" />
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>