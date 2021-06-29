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
<title>Ward Disease Type Add Page</title>
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
            var hisValidator = new HISValidator("diseasetypeBean");
            hisValidator.addValidation("strDiseaseTypeName", "req", "DiseaseType Name could not be blank" );
            hisValidator.addValidation("strDiseaseTypeName", "maxlen=50", "Disease Type Name should not be greater than 50 Characters" );
            hisValidator.addValidation("strEffectiveFrom", "date","EffectiveFrom Date is a mandatory field");
		    hisValidator.addValidation("strEffectiveFrom", "dtgtet=${diseasetypeBean.strCtDate}" , "EffectiveFrom Date should not be Less than Current Date");
       		hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should not be greater than 50 Characters" );
            
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
<body onLoad="document.forms[0].strDiseaseTypeName.focus()">
<html:form action="/masters/CNTWardDiseaseTypeMst"> 	
 	<div class="errMsg"><bean:write name="diseasetypeBean" property="strErrorMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="diseasetypeBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="diseasetypeBean" property="strWarning"/></div>
	
<tag:tab tabLabel="Add Ward DiseaseType" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="2">Ward DiseaseType Master&gt;&gt;Add</td>
  </tr>               
   <tr >
    <td class="LABEL"><font color="red">*</font>Disease Type Name</td>
    <td width="54%" class ="CONTROL">
    <input type="text" onkeyup="lTrim(this);" onblur="Trim(this);" class='txtFldMax' 
    name="strDiseaseTypeName" maxlength="50" onkeypress="return validateData(event,9);"> </td>
  </tr>  
  <tr>
    <td class ="LABEL" width ="46%"><font color="red">*</font>Effective From</td>
    <td class ="CONTROL">
    <date:date name="strEffectiveFrom" value ="${diseasetypeBean.strEffectiveFrom}"></date:date></td>
  </tr>
  <tr>
      <td class="LABEL"><div align="right">Remarks</div></td>
      <td class="LABEL">
	  <div align="left">
        <textarea name="strRemarks" onkeyup="lTrim(this);" onblur="Trim(this);" rows="2" ></textarea>
      </div></td>
    </tr>   
   <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font>Mandatory Fields</td>
  </tr>
</table>	 
	    <table CLASS ="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	    <tr> 
		<td align="center">
		<img src="../../hisglobal/images/btn-sv.png"  style="cursor:pointer"  onClick="return validate1(); "/>
		<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" onClick="document.forms[0].reset();">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');"/>
	   </td>
	      </tr>
	    </table>
	    <input type="hidden" name="hmode"/>
    <cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>