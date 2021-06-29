<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head><meta charset=utf-8>
<title>Advance Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

	function validate1(){	
	
		var hisValidator = new HISValidator("advanceBean");
		
		hisValidator.addValidation("strAdvanceAmount", "req", "Advance Amount is a Mandatory Field");
		hisValidator.addValidation("strAdvanceAmount", "amount=8,2", "Enter a Valid Advance Amount" );
		//hisValidator.addValidation("strAdvanceSecurity", "req", "Security Amount is a Mandatory Field");
		//hisValidator.addValidation("strAdvanceSecurity", "amount=8,2", "Enter a Valid Security Amount" );
		hisValidator.addValidation("strPartPayment", "req", "Part Payment is a Mandatory Field" );
		hisValidator.addValidation("strPartPayment", "amount=8,2", "Enter a Valid Part Payment" );
		hisValidator.addValidation("strEffectiveFromDate", "req","Effective From is a Madatory Field");
		hisValidator.addValidation("strEffectiveFromDate", "date","Effective From should be a valid Date");
		
		if(document.forms[0].strUpdateMode[1].checked){
		
			hisValidator.addValidation("strEffectiveFromDate", "dtgtet="+document.forms[0].ctDate.value,"Effective From Date should be Greater than or Equal to Current Date");
		}
						
		if(document.advanceBean.strRemarks.value != ""){
				hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
		}
		
		var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
		if(retVal){
				document.forms[0].hmode.value = "UPDATE";
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
				document.forms[0].strAdvanceAmount.focus();
			}else{
			document.getElementById("wardDivId").style.display = "none";
			document.forms[0].strAdvanceAmount.focus();
			}
	}

function checkUpdateMode(radioObj){
	
		if(radioObj.value == 0){
		
			document.getElementById("modifyDateId").style.display = "none";
			document.getElementById("displayDateId").style.display = "block";
			
			document.forms[0].strEffectiveFromDate.value = "${advanceBean.strEffectiveFromDate}";
			
		}else{
		
			document.getElementById("displayDateId").style.display = "none";
			document.getElementById("modifyDateId").style.display = "block";
		}

	}

</script>

</head>
<body onload="checkWardVisible();">
<html:form action="/masters/CNTAdvanceMst.cnt"
	type="billing.masters.controller.fb.VOAdvanceMst" name="advanceBean">
	
		
	<div class="errMsg"><bean:write name="advanceBean" property="strErr"/></div>
	<div class="warningMsg" id='normalMsg'><bean:write name="advanceBean" property="strWarning"/></div>
	
	<tag:tab tabLabel="Modify Advance" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	
<table class="TABLEWIDTH" align="center" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="2">Advance Master&gt;&gt; Modify</td>
  </tr>
  
	<tr>
			<td class="LABEL" colspan="4"> <b> <html:radio name="advanceBean" property="strUpdateMode" value="0" onclick="checkUpdateMode(this);">Correction </html:radio> <html:radio name="advanceBean" property="strUpdateMode" value="1" onclick="checkUpdateMode(this);">Update</html:radio> </b> &nbsp;&nbsp;</td>
			</tr>
 <tr> 
    <td   width="50%" class="LABEL"> Hospital Service</td>
    <td  width="50%" class="CONTROL"><bean:write name="advanceBean" property="strHospitalService"/></td>
  </tr>
  </table>
  <div id="wardDivId">
  <table class="TABLEWIDTH" align="center" cellspacing="1px">
 
  <tr> 
    <td width="50%" class="LABEL">Ward Type</td>
    <td width="50%" class="CONTROL"> <select  id="strWardType" class="comboNormal" disabled="disabled">
       <bean:write name="advanceBean" property="strWardValues" filter="false"/> </select></td>
  </tr>
  <tr style="display:none"> 
    <td width="50%" class="LABEL"> Special Ward Type</td>
    <td width="50%" class="CONTROL"> <select name="strSpecialWardType" id="strSpecialWardType" class="comboNormal" disabled="disabled">
       <bean:write name="advanceBean" property="strSpecialWardValues" filter="false"/> </select></td>
  </tr>
  </table>
  </div>
  <table class="TABLEWIDTH" align="center" cellspacing="1px">
  <tr> 
    <td  width="50%" class="LABEL">Category</td>
    <td  width="50%" class="CONTROL"><select  id="strCategory" class="comboNormal" disabled="disabled">
        <bean:write name="advanceBean" property="strCategoryValues" filter="false"/> </select> </td>
  </tr>
  <tr> 
    <td  width="50%" class="LABEL"> <font color="red">*</font>(Advance + Security) Amount</td>
    <td  width="50%" class="CONTROL">
      <input name="strAdvanceAmount" autocomplete='off' type="text" id="strAdvanceAmount" class="txtFldNormal" value="${advanceBean.strAdvanceAmount}" maxlength="9" onkeypress="return validateData(event,7);" onkeyup="enterAmount();">
      <input name="strAdvanceSecurity" type="text" autocomplete='off' value="0" class="txtFldNormal" maxlength="9" onkeypress="return validateData(event,7);" onkeyup="enterAmount();" style="display: none;">
     <input name="strAdvanceTotal" type="text" autocomplete='off' id="strAdvanceTotal" class="txtFldMax" value="${advanceBean.strAdvanceAmount + advanceBean.strAdvanceSecurity}" readonly   style="display: none;">
    </td>
  </tr>
  <tr> 
    <td  width="50%" class="LABEL"> <font color="red">*</font>Part Payment</td>
    <td  width="50%" class="CONTROL"> 
        <input name="strPartPayment"  type="text" autocomplete='off' id="strPartPayment" class="txtFldNormal" value="${advanceBean.strPartPayment}" maxlength="9" onkeypress="return validateData(event,7);"/>
      </td>
  </tr>
  <tr> 
    <td  width="50%" class="LABEL"> <font color="red">*</font>Effective From </td>
    <td  width="50%" class="CONTROL">
    <div id='displayDateId'>${advanceBean.strEffectiveFromDate}</div> <div id='modifyDateId' style="display:none"><dateTag:date name="strEffectiveFromDate"
				value="${advanceBean.strEffectiveFromDate}"></dateTag:date></div>	
     </td>
  </tr>
  <tr> 
    <td  width="50%" class="LABEL"> Remarks</td>
    <td  width="50%" class="CONTROL"> <textarea name="strRemarks" cols="20" rows="2"
				id="strRemarks"><bean:write name="advanceBean" property="strRemarks" /></textarea> </td>
  </tr>
  <tr class="FOOTER"> 
     <td colspan="2" align="right"><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>
	<table width="75%" border="0" align="center">
		<tr>
			<br>
    <td align="right">  
       <!--  <img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record"
				onClick="return validate1();" /> -->
				<a href="#" class="button" id="" onclick="return validate1();"><span class="save">Save</span></a>
      </td> 
    <td align="left"> 
       <!--  <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel Process"
				onClick="cancel('LIST');" /> -->
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
      </td>
		</tr>
	</table>
	<input type="hidden" name="chk" value="${advanceBean.chk[0]}" />
	<input type="hidden" name="hmode">
	<input type="hidden" name="ctDate" value="${advanceBean.strCtDate }"/>
	<input type="hidden" name="strWardType" value="${advanceBean.strWardType }"/>
	<input type="hidden" name="strCategory" value="${advanceBean.strCategory }"/>
	<input type="hidden" name="strHospitalServiceId" value="${advanceBean.strHospitalServiceId }"/>
	<cmbPers:cmbPers></cmbPers:cmbPers>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>