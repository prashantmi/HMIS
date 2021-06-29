<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Property Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript">

function validate1(){   
     
      var hisValidator = new HISValidator("propertyBean");
              
            
            hisValidator.addValidation("strPropertyName", "req", "Property Name is a Mandatory Field" );
            hisValidator.addValidation("strRemark", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            //hisValidator.addValidation("strEffectiveFrom", "date", "Effective from is a mandatory field" );
            // hisValidator.addValidation("strEffectiveFrom", "dtgtet=${propertyBean.strCtDate}" , "Effective from should be Greater than or Equal to Current Date");   
            var retVal = hisValidator.validate();           
          if(retVal){
                       
      				   document.forms[0].hmode.value = "UPDATE";
                       document.forms[0].submit();
            }else{

           return false;

          }
    }



</script>

</head>

<body onLoad="document.forms[0].strPropertyName.focus();">
<html:form name="propertyBean" action="/masters/CNTPropertyMst"
	type="ipd.masters.vo.PropertyMstVO">
	

<div class="errMsg"><bean:write name="propertyBean" property="strErrorMsg"/></div>
	<div class="normalMsg"><bean:write name="propertyBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="propertyBean" property="strWarnings"/></div>

	<tag:tab tabLabel="Modify Property " selectedTab="FIRST" align="center" width ="TABLEWIDTH"></tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Property Master&gt;&gt;Modify</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Property Name</td>
			<td width="50%" class="CONTROL"><input type="text"  onkeyup="lTrim(this);" onblur="Trim(this);" class='txtFldNormal' name="strPropertyName"
				value="${propertyBean.strPropertyName}" maxlength="100" onkeypress="return validateData(event,9);"></td>
		</tr>

		<tr>
			<td class="LABEL" width="50%">Effective From</td>
			<td class="CONTROL"><bean:write  name="propertyBean" property="strEffectiveFrom" /></td>
		</tr>
		<tr>
	      <td class="LABEL"><div align="right"> Remarks </div></td>
	      <td class="LABEL">
		  <div align="left">
	        <textarea name="strRemark" rows="2" onkeyup="lTrim(this);" onblur="Trim(this);" id="strRemark"><bean:write name="propertyBean" property="strRemark" filter="false"/></textarea>
	      </div></td>
	    </tr>

		<tr>
			<td width="45%" class="LABEL">Record
			Status</td>
			<td width="45%" class="CONTROL">
			 <html:radio name="propertyBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="propertyBean" property="strIsValid" value="2">InActive</html:radio>
   </td>
		</tr>

		<tr class="FOOTER">
			<td colspan="2">&nbsp;</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>

			<td align="right"><img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record"
				onClick="return validate1();" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel Process"
				onClick="cancel('LIST');" /></td>
		</tr>
	</table>
	 <input type="hidden" name="hmode" value=""/>
	 <input type="hidden" name="strEffectiveFrom" value="${propertyBean.strEffectiveFrom}" />
	 <input type="hidden" name="strChk" value="${propertyBean.strChk}"/>
	 
	
	<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>

