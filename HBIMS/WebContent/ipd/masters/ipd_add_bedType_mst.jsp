<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset="utf-8" /> 
<title>Bed Type Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language ="javaScript">

function validate1()
{   
            var hisValidator = new HISValidator("bedtypeBean");
            hisValidator.addValidation("strGlobalBedType", "dontselect=0", "Global Bed Type is a Mandatory Field" );
            hisValidator.addValidation("strBedType", "req", "Bed Type is a Mandatory Field" );
            hisValidator.addValidation("strBedType", "maxlen=40", "Bed Type Name should have less than or equal to 40 Characters" );
       		hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
            hisValidator.addValidation("strEffectiveDate", "date","Effective Date is a mandatory field");
		    hisValidator.addValidation("strEffectiveDate", "dtgtet=${bedtypeBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");
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
function setLocalBedType()
{
	document.getElementsByName('strBedType')[0].value=document.forms[0].strGlobalBedType.options[document.forms[0].strGlobalBedType.selectedIndex].text;
}
</script>

</head>
<body onLoad="document.forms[0].strBedType.focus()">
<html:form action="/masters/CNTBedTypeMst" focusIndex="2">

  	<div class="errMsg"><bean:write name="bedtypeBean" property="strErrorMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="bedtypeBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="bedtypeBean" property="strWarning"/></div>

<tag:tab tabLabel="Add Bed Type" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
  	<tr class="HEADER"> 
    	<td colspan="2">Bed Type Master&gt;&gt;Add</td>
  	</tr>  
  	<tr> 
  		<td width="50%" class="LABEL"> <font color="red">*</font>Global Bed Type</td>
	    <td width="50%" class="CONTROL"> <select name="strGlobalBedType" id="strGlobalBedType" class="comboNormal" onchange="setLocalBedType()">
	       <bean:write name="bedtypeBean" property="strGlobalBedTypeCombo" filter="false"/> </select>
	    </td>
	</tr>
  	<tr >
	    <td class="LABEL"><font color="red">*</font>Bed Type</td>
	    <td width="50%" class ="CONTROL"><input onkeyup="lTrim(this);" onblur="Trim(this);" type="text" name="strBedType" class='txtFldMax' value ="" 
	                   maxlength="40" onkeypress="return validateData(event,4);"> </td>
  	</tr>  
  	<tr>
	    <td class ="LABEL" width ="50%"><font color="red">*</font>Effective Date</td>
	    <td class ="CONTROL"><date:date name="strEffectiveDate" value ="${bedtypeBean.strEffectiveDate}"></date:date></td>
  	</tr>
  	<tr>
      	<td class="LABEL"><div align="right">Remarks</div></td>
      	<td class="CONTROL">
	  	<div align="left">
        	<textarea name="strRemarks" onkeyup="lTrim(this);" onblur="Trim(this);" rows="2"></textarea>
      	</div></td>
  	</tr>   
  	<tr class="FOOTER"> 
    	<td colspan="2" ><font size="2" color="red">*</font>Mandatory Fields</td>
  	</tr>
</table>
<table CLASS ="TABLEWIDTH"  align="center" cellspacing="1px" cellpadding="1px">
  	<tr> 
		<td align="center">		
		<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer"  onClick="return validate1(); " title="Save the Record"/>
		<img src="../../hisglobal/images/btn-clr.png" style="cursor:pointer" onClick="document.forms[0].reset();" title="Clear All Fields">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');" title="Go to List page"/>
		 -->
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	oonClick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	   </td>
  	</tr>
</table>
	    <input type="hidden" name="hmode" />
<cmbPers:cmbPers/>   
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	