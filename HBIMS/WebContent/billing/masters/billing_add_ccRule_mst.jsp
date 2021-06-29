<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!--  
 
													 Developer : PAWAN KUMAR B N
													 Created On: 08-09-2011
 													 Module : Billing
 													 Process: Consumable Charge Discount Rule Master
 													 JSP URL : billing/masters/billing_add_ccRule_mst.jsp

-->

<html>
<head>
<meta charset=utf-8>
<title>Consumable Charge Discount Rule Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="javaScript">

function validate1(){   

		var val=document.forms[0].strDiscountAmt.value;
		var selIndex=document.forms[0].strChargeTypeId.selectedIndex;
		var rval=true;
      var hisValidator = new HISValidator("ccRuleBean");
            hisValidator.addValidation("strChargeTypeId","dontselect=0","Please select a value from Hospital Service Combo");
            if(selIndex==2)
    		{
    		hisValidator.addValidation("strIpdChargeTypeId","dontselect=0","Please select a value from Ward Type Combo");
    		}
            
            hisValidator.addValidation("strPatientCatCode","dontselect=0","Please select a value from Patient Category Combo");
            
            if(val<0 || val>100 || val==""){
              alert("Please Enter Valid Discount Percentage");
              document.forms[0].strDiscountAmt.value="";
              var rval=false;
              }
            var retVal = hisValidator.validate(); 
          if(retVal && rval){
                       document.forms[0].hmode.value = "SAVE";
                        document.forms[0].submit();
                         
            }else{

           return false;

          }
    }
   
function setDefaults()
{
document.forms[0].strChargeTypeId.selectedIndex=0;
document.forms[0].strIpdChargeTypeId.selectedIndex=0;
document.forms[0].strPatientCatCode.selectedIndex=0;
document.forms[0].strDiscountAmt.value="";
}
 
function checkforIPD()
{
    var selIndex=document.forms[0].strChargeTypeId.selectedIndex;
    if(selIndex==2)
    {
    document.forms[0].strIpdChargeTypeId.disabled=false;
    }
    else
    {
    document.forms[0].strIpdChargeTypeId.disabled=true;
    }
}

function checkIsNaN()
{
    var val=document.forms[0].strDiscountAmt.value;
    if(isNaN(val))
    {
    alert("Please Enter Numerics");
    document.forms[0].strDiscountAmt.value="";
    document.forms[0].strDiscountAmt.focus();
    return false;
    }
  
    else
    {
    return true;
    }
}

</script>

<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
</head>
<body onload="setDefaults();">
<html:form name="ccRuleBean" action="/masters/CNTCCRuleMst"
	type="billing.masters.controller.fb.ConsumableChargeDiscountRuleMstFB">
	<div class="errMsg"><bean:write name="ccRuleBean"
		property="strErrorMsg" /></div>
	<div class="warningMsg"><bean:write name="ccRuleBean"
		property="strWarning" /></div>
	<div class="normalMsg" id='normalMsg'><bean:write
		name="ccRuleBean" property="strMsg" /></div>



	<tag:tab tabLabel="Add Consumable Charge Discount Rule"
		selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Consumable Charge Discount Rule Master
			&gt;&gt;Add</td>
		</tr>
		<tr>
			<td class="LABEL"><span class="style1">*</span>Hospital Service
			</td>
			<td width="50%" class="CONTROL"><select name="strChargeTypeId"
				class="comboNormal" onchange="checkforIPD();">
				<bean:write name="ccRuleBean" property="strHospServiceValues"
					filter="false" />
			</select></td>
		</tr>
		<tr>
			<td class="LABEL"><span class="style1">*</span>Ward Type</td>
			<td width="50%" class="CONTROL"><select
				name="strIpdChargeTypeId" class="comboNormal" disabled="disabled">
				<bean:write name="ccRuleBean" property="strWardTypeValues"
					filter="false" />
			</select></td>
		</tr>
		<tr>
			<td class="LABEL"><span class="style1">*</span>Patient Category</td>
			<td width="50%" class="CONTROL"><select name="strPatientCatCode"
				class="comboNormal">
				<bean:write name="ccRuleBean" property="strPatCatValues"
					filter="false" />
			</select></td>
		</tr>
		<tr>
			<td class="LABEL"><span class="style1">*</span>Discount Value
			(%)</td>
			<td width="50%" class="CONTROL"><input type="text"
				class='txtFldMax' name="strDiscountAmt" value="" maxlength="3"
				onkeypress=" return checkIsNaN();"></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>

	<table CLASS="TABLEWIDTH" align="center">
		<tr>
			<td align="center"><img style="cursor: pointer; cursor: hand"
				src="../../hisglobal/images/btn-sv.png" title="Save Record"
				onClick=" return validate1(); " /> <img
				style="cursor: pointer; cursor: hand"
				src="../../hisglobal/images/btn-clr.png" title="Reset Content"
				onClick="setDefaults();" /> <img
				style="cursor: pointer; cursor: hand"
				src="../../hisglobal/images/btn-ccl.png" title="Cancel Process"
				onClick="cancel('LIST');" /></td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="StrHospitalCode" value="" />
	<input type="hidden" name="StrChargeTypeId" value="" />
	<cmbPers:cmbPers />

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>

