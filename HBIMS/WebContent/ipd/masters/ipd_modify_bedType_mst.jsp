<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Bed Type Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript"><!--
function validate1()
{   
      		var hisValidator = new HISValidator("bedtypeBean");
            hisValidator.addValidation("strGlobalBedType", "dontselect=0", "Global Bed Type is a Mandatory Field" );
            hisValidator.addValidation("strBedType", "req", "Bed Type is a Mandatory Field" );
<!--            hisValidator.addValidation("strEffectiveDate", "date","Effective Date is a mandatory field");-->
<!--			hisValidator.addValidation("strEffectiveDate", "date","Effective Date should be a valid Date");-->
<!--			hisValidator.addValidation("strEffectiveDate", "dtgtet=${bedtypeBean.strCtDate}" , "Effective From Date Should be Greater than or Equal to Current Date");-->
			hisValidator.addValidation("strBedType", "maxlen=40", "Bed Type Name should have less than or equal to 40 Characters" );
       		hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
		//	hisValidator.addValidation("strEffectiveDate", "dtgtet=${bedtypeBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");         
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
--></script>
</head>
<body onLoad="document.forms[0].strBedType.focus()">
<html:form action="/masters/CNTBedTypeMst">
  
 <div class="errMsg"><bean:write name="bedtypeBean" property="strErrorMsg"/></div>
 <div class="normalMsg" id="normalMsg"></div>  
<tag:tab tabLabel="Modify Bed Type" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"></tag:tab>
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="2">Bed Type Master &gt;&gt;Modify</td>
  </tr>   
  <tr> 
	    <td width="50%" class="LABEL"> <font color="red">*</font>Global Bed Type</td>
	    <td width="50%" class="CONTROL"> <select name="strGlobalBedType" id="strGlobalBedType" class="comboMax" disabled="disabled">
	       <bean:write name="bedtypeBean" property="strGlobalBedTypeCombo" filter="false"/> </select>
	    </td>
  </tr>           
  <tr>
    <td class="LABEL"><font color="red">*</font>Bed Type</td>
    <td width="50%" class ="CONTROL"><input onkeyup="lTrim(this);" onblur="Trim(this);" type="text" name="strBedType" class='txtFldMax' 
    		value ="${bedtypeBean.strBedType }"  maxlength="40"
    		 onkeypress="return validateData(event,4);" > </td>
  </tr>
  <tr>
    <td class ="LABEL" width ="50%">Effective Date</td>
    <td class ="CONTROL"><bean:write name="bedtypeBean" property ="strEffectiveDate" filter="false"/></td>
  </tr>    
  <tr>
      <td class="LABEL"><div align="right">Remarks</div></td>
      <td class="CONTROL">
	  <div align="left">
        <textarea name="strRemarks" onkeyup="lTrim(this);" onblur="Trim(this);" rows="2"><bean:write name="bedtypeBean" property="strRemarks"/></textarea>
      </div></td>
  </tr>    
  <tr>
    <td width ="45%" class ="LABEL">Record Status</td>
    <td width ="45%" class ="CONTROL" >
    <html:radio name="bedtypeBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="bedtypeBean" property="strIsValid" value="2">InActive</html:radio>
    </tr>    
    <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>																														
<table CLASS ="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr> 
		<td align="center">
		<!-- <img src="../../hisglobal/images/btn-sv.png"   style="cursor:pointer" onClick="return validate1(); " title="Save the record"/>
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');" title="Go to list page"/> -->
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	   </td>
	</tr>
</table>
	    <input type="hidden" name="chk" value="${bedtypeBean.chk[0] }">
	    <input type="hidden" name="strEffectiveDate" value ="${bedtypeBean.strEffectiveDate}" >
	   	<input type="hidden" name="hmode">
<cmbPers:cmbPers/>	 
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>		