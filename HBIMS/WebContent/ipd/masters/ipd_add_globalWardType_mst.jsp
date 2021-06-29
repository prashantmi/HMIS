<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta charset=utf-8>
<title>Global Ward Type Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="javaScript">
function validate1()
{   
      		var hisValidator = new HISValidator("globalWardtypeBean");
            hisValidator.addValidation("strWardType", "req", "Global Ward Type is a Mandatory Field" );
            //hisValidator.addValidation("strEffectiveDate", "date","Effective Date is a mandatory field");
			//hisValidator.addValidation("strEffectiveDate", "date","Effective Date should be a valid Date");
            //hisValidator.addValidation("strEffectiveDate", "dtgtet=${globalWardtypeBean.strCtDate}" , "Effective From Date should be Greater than or Equal to Current Date");         
            var retVal = hisValidator.validate();
          if(retVal)
          {
          	   document.forms[0].hmode.value = "SAVE";
               document.forms[0].submit();
          }
          else
          {
	          return false;
	      }
}
</script>
</head>
<body onLoad="document.forms[0].strWardType.focus();">
<html:form action="/masters/GlobalWardTypeMstACTION">
	
<div class="errMsg"><bean:write name="globalWardtypeBean" property="strerrorMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="globalWardtypeBean" property="msg"/></div>
<div class="warningMsg"><bean:write name="globalWardtypeBean" property="warnings"/></div>
	
	<tag:tab tabLabel="Add Global Ward Type" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="2">Global Ward Type Master&gt;&gt;Add</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Ward Type</td>
			<td width="50%" class="CONTROL"><input type="text" onkeyup="lTrim(this);" onblur="Trim(this);" class='txtFldNormal' name="strWardType"
				value="" maxlength="20" onkeypress="return validateData(event,9);"></td>
		</tr>
		<!-- 
		<tr>
			<td class="LABEL" width="50%">
			<font color="red">*</font>Effective Date
			</td>
			<td class="CONTROL">
			<date:date name="strEffectiveDate" value="${globalWardtypeBean.strEffectiveDate}"></date:date></td>
		</tr>-->
		<tr class="FOOTER">
			<td colspan="2"><font size='2' color='red'>*</font>Mandatory Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer" onClick=" return validate1();"/>
			<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" onClick="document.forms[0].reset();">		
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick="cancel('LIST');"/>
			 -->
			<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
			<a href="#" class="button"	onClick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode">
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>