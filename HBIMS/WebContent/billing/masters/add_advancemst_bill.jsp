<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset=utf-8>
<title>Advance Master Add Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>

<script type="text/javascript">

	function validate1(){	
	
		var hisValidator = new HISValidator("advanceBean");  
		
		if(document.forms[0].strHospitalServiceId.value == 2){
		hisValidator.addValidation("strWardType","dontselect=0","Please select a value from Ward Type");
		}
		hisValidator.addValidation("strCategory","dontselect=0","Please select a value from Category");
		hisValidator.addValidation("strAdvanceAmount", "req", "Advance Amount is a Mandatory Field" );
		hisValidator.addValidation("strAdvanceAmount", "amount=8,2", "Enter a Valid Advance Amount " );
		//hisValidator.addValidation("strAdvanceSecurity", "req", "Security Amount is a Mandatory Field");
		//hisValidator.addValidation("strAdvanceSecurity", "amount=8,2", "Enter a Valid Security Amount" );
		hisValidator.addValidation("strPartPayment", "req", "Part Payment is a Mandatory Field" );
		hisValidator.addValidation("strPartPayment", "amount=8,2", "Enter a Valid Part Payment" );
		hisValidator.addValidation("strEffectiveFromDate", "req","Effective From is a Mandatory Field");
		hisValidator.addValidation("strEffectiveFromDate", "date","Effective From should be a valid Date");
		hisValidator.addValidation("strEffectiveFromDate", "dtgtet="+document.forms[0].ctDate.value,"Effective From Date should be Greater than or Equal to Current Date");
	
		if(document.advanceBean.strRemarks.value != ""){
				hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
		}
		
		var retVal = hisValidator.validate(); 
	
		if(retVal){
				document.forms[0].hmode.value = "INSERT";
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
	}

	function enterAmount(){
		
			var retVal = false;
		
			var adv = document.advanceBean.strAdvanceAmount.value;	
			var sec = document.advanceBean.strAdvanceSecurity.value;
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.forms[0].strAdvanceTotal.value = total;
					retVal = true;
				
		
		return retVal;
	}




	function checkWardVisible(){
		
			if(document.forms[0].strHospitalServiceId.value == 2){
				document.getElementById("wardDivId").style.display = "block";
				document.forms[0].strWardType.focus();
			}else{
			document.getElementById("wardDivId").style.display = "none";
			document.forms[0].strCategory.focus();
			}
	}

</script>

</head>
<body onLoad="checkWardVisible();">
<html:form action="/masters/CNTAdvanceMst.cnt"
	type="billing.masters.controller.fb.VOAdvanceMst" name="advanceBean">
	
	<div class="errMsg"><bean:write name="advanceBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="advanceBean" property="strWarning"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="advanceBean" property="strMsg"/></div>
	
	<tag:tab tabLabel="Add Advance" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" >
  <tr class="HEADER"> 
    <td colspan="2">Advance Master&gt;&gt; Add</td>
  </tr>
  <tr> 
    <td   width="50%" class="LABEL">Hospital Service</td>
    <td  width="50%" class="CONTROL"><bean:write name="advanceBean" property="comboValue"/></td>
  </tr>
  </table>
  <div id="wardDivId">
  <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr> 
    <td width="50%" class="LABEL"> <font color="red">*</font>Ward Type</td>
    <td width="50%" class="CONTROL"> <select name="strWardType" id="strWardType" class="comboNormal">
       <bean:write name="advanceBean" property="strWardValues" filter="false"/> </select></td>
  </tr>
   <tr style="display:none"> 
    <td width="50%" class="LABEL"> Special Ward Type</td>
    <td width="50%" class="CONTROL"> <select name="strSpecialWardType" id="strSpecialWardType" class="comboNormal">
       <bean:write name="advanceBean" property="strSpecialWardValues" filter="false"/> </select></td>
  </tr>
  </table>
  </div>
  <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Category</td>
    <td width="50%" class="CONTROL"><select name="strCategory" id="strCategory" class="comboNormal" >
        <bean:write name="advanceBean" property="strCategoryValues" filter="false"/> </select> </td>
  </tr>
   <tr> 
    <td width="50%" class="LABEL"> <font color="red">*</font>(Advance + Security) Amount</td>
    <td width="50%" class="CONTROL">
    	<input name="strAdvanceAmount" type="text" value="" class="txtFldNormal" maxlength="9" onkeypress="return validateData(event,7);" onKeyUp="enterAmount();">
     	<input name="strAdvanceSecurity" type="text" value="0" autocomplete='off' class="txtFldNormal" maxlength="9" onkeypress="return validateData(event,7);" onKeyUp="enterAmount();" style="display: none;">
     	<input name="strAdvanceTotal" type="text"  autocomplete='off' class="txtFldMax" value="0" readonly style="display: none;">
    </td>
  </tr>
  <tr> 
    <td width="50%" class="LABEL"> <font color="red">*</font>Part Payment</td>
    <td width="50%" class="CONTROL">
        <input name="strPartPayment" type="text" autocomplete='off' id="strPartPayment" class="txtFldNormal" maxlength="9" onkeypress="return validateData(event,7);">
      </td>
  </tr>
  
  <tr> 
    <td width="50%" class="LABEL"> <font color="red">*</font>Effective From </td>
    <td width="50%" class="CONTROL"> <dateTag:date name="strEffectiveFromDate" id="fromDateId"
				 value="${advanceBean.strEffectiveFromDate}"/> </td>
  </tr>
  <tr> 
    <td width="50%" class="LABEL"> Remarks</td>
    <td width="50%" class="CONTROL"> <textarea name="strRemarks" cols="20" rows="2"
				id="strRemarks" ></textarea> </td>
  </tr>
  <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
             
			<td align="center">
			<!-- <img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record" onClick=" return validate1();" />
			<img src="../../hisglobal/images/btn-clr.png" style="cursor:pointer;cursor:hand;" title="Reset Content"
				onClick="document.forms[0].reset();" />
				<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel Process"
				onClick="submitPage('CANCEL');" />-->
				
				<br><a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="submitPage('CANCEL');"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	
<input type="hidden" name="hmode"/>
<input type="hidden" name="ctDate" value="${advanceBean.strCtDate }"/>
<input type="hidden" name="combo" value="${advanceBean.combo[0]}" />
<input type="hidden" name="comboValue" value="${advanceBean.comboValue}" />
<input type="hidden" name="strHospitalServiceId" value="${advanceBean.combo[0]}"/>
<input type="hidden" name="strHospitalService" value="${advanceBean.comboValue}" />

	<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>