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
<title>Global Discharge Type Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript">
function validate1(){   
     
      var hisValidator = new HISValidator("globalDischargeTypeBean");
      
           	hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
            hisValidator.addValidation("strDischargeTypeName", "req", "Global Discharge Type is a Mandatory Field" );
            //hisValidator.addValidation("strEffectiveDate", "date","Effective Date is a mandatory field");
			//hisValidator.addValidation("strEffectiveDate", "date","Effective Date should be a valid Date");
		//	hisValidator.addValidation("strEffectiveDate", "dtgtet=${globalDischargeTypeBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");
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
<body onLoad="document.forms[0].strDischargeTypeName.focus()"> 
<html:form action="/masters/GlobalDischargeTypeMstACTION">

<div class="errMsg"><bean:write name="globalDischargeTypeBean" property="strErrorMsg"/></div>  
<div class="normalMsg" id="normalMsg"></div>  
<tag:tab tabLabel="Modify Global Discharge Type" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Global Discharge Type Master&gt;&gt;Modify</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Discharge Type</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strDischargeTypeName"
				class='txtFldMax'
				value="${globalDischargeTypeBean.strDischargeTypeName }"
				onkeypress="return validateData(event,4);" maxlength="40"></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Effective Date</td>
			<td class="CONTROL">
				<bean:write name="globalDischargeTypeBean" property="strEffectiveDate" />
			</td>
		</tr>
		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="LABEL">
			<div align="left"><textarea name="strRemarks"
				onkeyup="lTrim(this);" onblur="Trim(this);" rows="2"><bean:write
				name="globalDischargeTypeBean" property="strRemarks" /></textarea></div>
			</td>
		</tr>
		<tr>
			<td width="45%" class="LABEL">Record Status</td>
			<td width="45%" class="CONTROL"><html:radio
				name="globalDischargeTypeBean" property="strIsValid" value="1">Active</html:radio>
			<html:radio name="globalDischargeTypeBean" property="strIsValid" value="2">InActive</html:radio>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center"><!-- <img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer" onClick="return validate1(); "/>
				<img src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer" onClick="cancel('LIST');"/>
				 -->
				<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
				</td>
		</tr>
	</table>
	<input type="hidden" name="chk" value="${globalDischargeTypeBean.chk[0] }">
	<input type="hidden" name="strEffectiveDate" value="${globalDischargeTypeBean.strEffectiveDate}" />
	<input type="hidden" name="hmode">
<cmbPers:cmbPers/> 
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	