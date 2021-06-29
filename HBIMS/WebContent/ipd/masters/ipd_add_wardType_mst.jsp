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
<title>Ward Type Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="javaScript">
function validate1()
{   
      		var hisValidator = new HISValidator("wardtypeBean");
      		hisValidator.addValidation("strGlobalWardType","dontselect=0","Global Ward Type is a Mandatory Field");
            hisValidator.addValidation("strWardType", "req", "Ward Type is a Mandatory Field" );
            hisValidator.addValidation("strEffectiveDate", "date","Effective Date is a mandatory field");
			hisValidator.addValidation("strEffectiveDate", "date","Effective Date should be a valid Date");
            hisValidator.addValidation("strEffectiveDate", "dtgtet=${wardtypeBean.strCtDate}" , "Effective From Date should be Greater than or Equal to Current Date");         
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
function setLocalWardType()
{
	document.getElementsByName('strWardType')[0].value=document.forms[0].strGlobalWardType.options[document.forms[0].strGlobalWardType.selectedIndex].text;
}
</script>
</head>
<body onLoad="document.forms[0].strWardType.focus();">
<html:form action="/masters/CNTWardTypeMst">
	
<div class="errMsg"><bean:write name="wardtypeBean" property="strerrorMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="wardtypeBean" property="msg"/></div>
<div class="warningMsg"><bean:write name="wardtypeBean" property="warnings"/></div>
	
	<tag:tab tabLabel="Add Ward Type" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="2">Ward Type Master&gt;&gt;Add</td>
		</tr>
		<tr> 
		    <td width="50%" class="LABEL"> <font color="red">*</font>Global Ward Type</td>
		    <td width="50%" class="CONTROL"> <select name="strGlobalWardType" id="strGlobalWardType" class="comboNormal" onchange="setLocalWardType()">
		       <bean:write name="wardtypeBean" property="strGlobalWardTypeCombo" filter="false"/> </select>
		    </td>
	  	</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Ward Type</td>
			<td width="50%" class="CONTROL"><input type="text" onkeyup="lTrim(this);" onblur="Trim(this);" class='txtFldMax' name="strWardType"
				value="" maxlength="20" onkeypress="return validateData(event,4);"></td>
		</tr>
		<tr>
			<td class="LABEL" ><font color="red">*</font>Block Time Duration(Hrs)</td>
			<td class="CONTROL" width="50%">
				
				<input type="text" style="width:35px;" placeholder="Hrs" class='txtFldMax' name="blockhrs"
				value="" maxlength="3" onkeypress="return validateFunc(this,5);">
    		</td>
		</tr>
		<tr style="display: none;">
			<td class="LABEL" width="50%">
			<font color="red">*</font>Effective Date
			</td>
			<td class="CONTROL">
			<date:date name="strEffectiveDate" value="${wardtypeBean.strEffectiveDate}"></date:date></td>
		</tr>
		
		
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0">
		<tr class="FOOTER">
			<td  width='82%' colspan="1"><div align="left"><font size='1'>(Global Ward Type Shows Only Those Records which are unmapped in the hospital.)</font></div></td>
			<td  width='18%' colspan="1"><font size='1' color='red'>*</font>Mandatory Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center"><div align='center'>
			<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer" onClick=" return validate1();"/>
			<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" onClick="document.forms[0].reset();">		
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick="cancel('LIST');"/></div>
			 -->
			<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="clear1;"><span class="document.forms[0].reset();">Clear</span></a> 
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