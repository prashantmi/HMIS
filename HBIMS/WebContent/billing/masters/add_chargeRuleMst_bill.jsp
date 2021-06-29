<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
/**
 * Developer : Tanvi Sappal
 * Process Name : Charge Rule Master
 * Date : 29 April 2010
 * Version : 1.0
 * Modify Date : 
 *
 */
 -->

<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Charge Rule</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
@import url(../../hisglobal/css/calendar-tas.css);
</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript">

function ajaxNewWardType()
{
 		var mode="NEWWARDTYPE";
		var url="CNTChargeRuleMst.cnt?hmode=NEWWARDTYPE&newchargetype="+document.forms[0].strNewChargeTypeId.value;
		ajaxFunction(url,"1");
		
		
}

function ajaxOldWardType()
{       
        var mode="OLDWARDTYPE";
		var url="CNTChargeRuleMst.cnt?hmode=OLDWARDTYPE&oldchargetype="+document.forms[0].strOldChargeTypeId.value;
		ajaxFunction(url,"2");
		
		
}

function ajaxNewPatCat()
{
	    var mode="NEWPATCAT";
		var url="CNTChargeRuleMst.cnt?hmode=NEWPATCAT&newchargetype="+document.forms[0].strNewChargeTypeId.value
										+"&newwardtype="+document.forms[0].strNewIPDChargeTypeId.value;
		ajaxFunction(url,"3");
}

function ajaxOldPatCat()
{
	    var mode="OLDPATCAT";
		var url="CNTChargeRuleMst.cnt?hmode=OLDPATCAT&oldchargetype="+document.forms[0].strOldChargeTypeId.value
										+"&oldwardtype="+document.forms[0].strOldIPDChargeTypeId.value;
		ajaxFunction(url,"4");
}

function getAjaxResponse(res,mode){	


	if(mode==1)
	{
	     var objVal = document.getElementById("newWardDiv");
		 objVal.innerHTML = "<select name='strNewIPDChargeTypeId' class='comboNormal' onchange='ajaxNewPatCat();'>" + res + "</select>";
		 
		 ajaxNewPatCat();
		 valNewDisable();	
	} 
	if(mode==2)
	{
	  	 var objVal = document.getElementById("oldWardDiv");
		 objVal.innerHTML = "<select name='strOldIPDChargeTypeId' class='comboNormal'onchange='ajaxOldPatCat();'>" + res + "</select>";
		 
		 ajaxOldPatCat();
		 valOldDisable();
	}
	if(mode==3)
	{
	     var objVal = document.getElementById("newCategoryDiv");
		 objVal.innerHTML = "<select name='strNewPatientCatcode' class='comboNormal'>" + res + "</select>";	
	} 
	if(mode==4)
	{
	  	 var objVal = document.getElementById("oldCategoryDiv");
		 objVal.innerHTML = "<select name='strOldPatientCatcode' class='comboNormal'>" + res + "</select>";
	}
}

function view1(obj1,obj2,obj3)
{
	document.getElementById(obj1).style.display="none";
	document.getElementById(obj2).style.display="block";
	document.getElementById(obj3).style.display="block";
}
function view2(obj1,obj2,obj3)
{
	document.getElementById(obj1).style.display="block";
	document.getElementById(obj2).style.display="none";
	document.getElementById(obj3).style.display="none";
}


function validate1(){   
     
      var hisValidator = new HISValidator("chargeRuleBean");

	        hisValidator.addValidation("strVariation","req","Variation is a Mandatory Field" );
            hisValidator.addValidation("strRuleName","req","Rule Name is a Mandatory Field" );
            hisValidator.addValidation("strRuleName","maxlen=30","Rule Name should not contain greater than 30 characters" );
            hisValidator.addValidation("strEffectiveFrom", "date","Effective Date is a mandatory field");         
            hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not be greater than 100 characters");
            
            var retVal = hisValidator.validate(); 
    
          if(retVal){
      				   document.forms[0].hmode.value = "SAVE";
                       document.forms[0].submit();
            }else{

           return false;

          }
    }
    
function valNewDisable()
{

	// alert("document.forms[0].strNewChargeTypeId.value==="+document.forms[0].strNewChargeTypeId.value)

	if(document.forms[0].strNewChargeTypeId.value == "2")
	
	{
		document.forms[0].strNewIPDChargeTypeId.disabled = false;
	}else
	{
		document.forms[0].strNewIPDChargeTypeId.disabled = true;
	}
}  

function valOldDisable()
{
	if(document.forms[0].strOldChargeTypeId.value == "2")
	{
		document.forms[0].strOldIPDChargeTypeId.disabled = false;
	}else
	{
		document.forms[0].strOldIPDChargeTypeId.disabled = true;
	}
}  

function GetIndex(index,endVal)  // Pagination
{
          for(var i = 1; i <= endVal ; i++)
		  {
			  document.getElementById("DivId"+i).style.display="none";
		  }
		  document.getElementById("DivId"+index).style.display="block";
}

function openModify(index)
{

	var radioRule = document.getElementById('strRadioRuleDetail'+index);
	if(radioRule.checked==false)
	{
		alert("First checked the Rule Detail");
		return;
	}
	

	 document.getElementById("ModifyRuleDivId").style.display="block";
	 
	 if(document.forms[0].strModify[0].checked==true)
	 {
	 	document.getElementById("CorrectionDivId").style.display="block";
	 	document.getElementById("ModificationDivId").style.display="none";
	 }
	 else if(document.forms[0].strModify[1].checked==true)
	 {
	 	document.getElementById("CorrectionDivId").style.display="none";
	 	document.getElementById("ModificationDivId").style.display="block";
	 }
}
function getIndex(_these){
	var nTmpI;
	var strSelectedObjName=document.getElementsByName(_these.name)
	for(nTmpI=0;nTmpI<strSelectedObjName.length;nTmpI++)
		if(_these==strSelectedObjName[nTmpI])
			break;
	return nTmpI;
}

function modifyData(_these){

	document.getElementById("normalMsg").style.display="none";

	var radioRule = document.getElementsByName("strRadioRuleDetail")[getIndex(_these)];
	if(radioRule.checked==false)
	{
		alert("First checked the Rule Detail");
		return;
	}

	document.getElementById("RuleDetailsDivId").style.display="none";
	document.getElementById("ModifyRuleDivId").style.display="block";
	
	document.getElementById("insertdiv").style.display="none";
	document.getElementById("updatediv").style.display="block";
	

	var strData = document.getElementsByName("strRadioRuleDetail")[getIndex(_these)].value.split("^");
	document.getElementsByName("strNewChargeTypeId")[0].value = strData[2];
	document.getElementsByName("strOldChargeTypeId")[0].value = strData[5];
	document.getElementsByName("strHNewIPDChargeTypeId")[0].value = strData[4];
	document.getElementsByName("strHOldIPDChargeTypeId")[0].value = strData[7];
	document.getElementsByName("strHNewPatientCatcode")[0].value = strData[3];
	document.getElementsByName("strHOldPatientCatcode")[0].value = strData[6];
	document.getElementsByName("strVariation")[0].value = strData[8];
	// document.getElementsByName("strEffectiveFrom")[0].value = strData[10];
	document.getElementsByName("strRemarks")[0].value = strData[9];
	document.getElementsByName("strRuleName")[0].value = strData[15];
	
	document.forms[0].strHidRuleId.value = strData[0]
	
	fillLabel("divNewChargeTypeLabelId","strNewChargeTypeId");	
	fillLabel("divOldChargeTypeLabelId","strOldChargeTypeId");
	fillDiv("newWardDivLabel",strData[11]);
	fillDiv("OldWardDivLabel",strData[12]);
	fillDiv("newCategoryDivLabel",strData[13]);
	fillDiv("OldCategoryDivLabel",strData[14]);
	fillDiv("divEffectiveLabelId",strData[10]);
	
	hideDiv("divNewChargeTypeId");
	hideDiv("divOldChargeTypeId");
	hideDiv("newWardDiv");
	hideDiv("oldWardDiv");
	hideDiv("newCategoryDiv");
	hideDiv("oldCategoryDiv");
	hideDiv("divEffectiveId");
	
	
}

function hideDiv(divId){
	document.getElementById(divId).style.display = "none";
}

function fillLabel(divId,selectCombo){
	document.getElementById(divId).innerHTML = document.getElementsByName(selectCombo)[0].options[document.getElementsByName(selectCombo)[0].options.selectedIndex].text;
}
function fillDiv(divId,value){
	document.getElementById(divId).innerHTML = value;
}

function clearPage()
{
	document.forms[0].hmode.value = "unspecified";
    document.forms[0].submit();
}

function cancelPage()
{
	document.forms[0].hmode.value = "CANCELPAGE";
    document.forms[0].submit();
}

function validate2(){   
     
            var hisValidator = new HISValidator("chargeRuleBean");

            hisValidator.addValidation("strVariation","req","Variation is a Mandatory Field" );
            hisValidator.addValidation("strRuleName","req","Rule Name is a Mandatory Field" );
            hisValidator.addValidation("strRuleName","maxlen=30","Rule Name should not contain greater than 30 characters" );        
            hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not be greater than 100 characters");
            
            var retVal = hisValidator.validate(); 
    
          if(retVal){
      				   document.forms[0].hmode.value = "CORRECTION";
                       document.forms[0].submit();
            }else{

           return false;

          }
    }
    
function getValue()
{

	if(document.forms[0].strModify[0].checked==true)
	 {
	 	document.getElementById("modifydiv").style.display="none";
	    document.getElementById("updatediv").style.display="block";
	    
	    document.getElementById("divEffectiveId").style.display="none";
	    document.getElementById("divEffectiveLabelId").style.display="block";
	 }
	 if(document.forms[0].strModify[1].checked==true)
	 {
	 	document.getElementById("modifydiv").style.display="block";
	    document.getElementById("updatediv").style.display="none";
	    
	    document.getElementById("divEffectiveId").style.display="block";
	    document.getElementById("divEffectiveLabelId").style.display="none";
	    document.getElementsByName("strEffectiveFrom")[0].value = document.getElementById("divEffectiveLabelId").innerHTML;
	     
	 }
}

function validate3(){   
         
            var hisValidator = new HISValidator("chargeRuleBean");

            hisValidator.addValidation("strVariation","req","Variation is a Mandatory Field" );
            hisValidator.addValidation("strRuleName","req","Rule Name is a Mandatory Field" );
            hisValidator.addValidation("strRuleName","maxlen=30","Rule Name should not contain greater than 30 characters" );
            hisValidator.addValidation("strEffectiveFrom", "date","Effective Date is a mandatory field");         
            hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not be greater than 100 characters");
            
            var retVal = hisValidator.validate(); 
    
          if(retVal){
      				   document.forms[0].hmode.value = "MODIFICATION";
                       document.forms[0].submit();
            }else{

           return false;

          }
    }
    
function deleteData(_these)
{
	
	var radioRule = document.getElementsByName("strRadioRuleDetail")[getIndex(_these)];
	if(radioRule.checked==false)
	{
		alert("First checked the Rule Detail");
		return;
	}
	
	// alert("radioRule.value--"+radioRule.value);
	
	
	
	var temp = document.getElementsByName("strRadioRuleDetail")[getIndex(_these)].value.split("^");
	
	  // alert("temp[16]--"+temp[16]);
	
	
	
	
	/*document.forms[0].strHNewIPDChargeTypeId.value = temp[4];
	document.forms[0].strHNewPatientCatcode.value = temp[3];
	document.forms[0].strNewChargeTypeId.value = temp[2];
	
	alert("temp[2]--"+document.forms[0].strNewChargeTypeId.value);
	alert("temp[3]--"+document.forms[0].strHNewPatientCatcode.value);
	alert("temp[4]--"+document.forms[0].strHNewIPDChargeTypeId.value);*/
	
	if(temp[16]=="0")
	{
		var conf = confirm("Are you sure you want to delete this detail!!!");
	    if(conf==true)
	    {
	    	  document.getElementsByName("strHNewIPDChargeTypeId")[0].value = temp[4];
	          document.getElementsByName("strHNewPatientCatcode")[0].value = temp[3];
	          document.getElementsByName("strNewChargeTypeId")[0].value = temp[2];
	          document.getElementsByName("strEffectiveFrom")[0].value = temp[10]
	    	   document.forms[0].strHidRuleId.value = temp[0];
	    
	          document.forms[0].hmode.value = "DELETE";
	          document.forms[0].submit();
	    }else
	    {
	          return false;
		}
	}else
	{	
		alert("Selected Details cannot be deleted")
	}
}
</script>
</head>
<body>

<html:form action="/masters/CNTChargeRuleMst" name="chargeRuleBean" type="billing.masters.controller.fb.ChargeRuleMstFB">
	<div class="errMsg" id="errMsg"><bean:write name="chargeRuleBean" property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write name="chargeRuleBean" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="chargeRuleBean" property="strNormalMsg" /></div>

	<tag:tab tabLabel="Charge Rule Master"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>


	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
	    <tr class="HEADER"><td colspan="4">Charge Rule Master &gt; &gt;</td></tr>
	</table>
	
	<div id="RuleDetailsDivId" style="display:block"><bean:write name="chargeRuleBean" property="strRuleDetails" filter="false"></bean:write></div>
	
	<div id="ModifyRuleDivId" style="display: none;">
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="LABEL" colspan="2">
             <div align="right">
              <html:radio property="strModify" name="chargeRuleBean" value="1" onclick="getValue(this);" >Correction</html:radio>
              <html:radio property="strModify" name="chargeRuleBean" value="2" onclick="getValue(this);" >Modification</html:radio>
             </div>
            </td>
		</tr>
	</table>
	</div>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1" cellspacing="1">   
		<tr>
		 <td  colspan="1" class="TITLE" width="5%">
			<div id="newRulePlusId" align="center" style="display:none;">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view1('newRulePlusId','newRuleMinusId','newRuleDivId');" style="cursor: pointer; cursor: hand"/>
				</div>
				<div id="newRuleMinusId" style="display:block;" align="center">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view2('newRulePlusId','newRuleMinusId','newRuleDivId');" style="cursor: pointer; cursor: hand"/>
				</div>
		 </td>	
		 <td  colspan="3" class="TITLE" width="95%">New Rule</td>
		</tr>
	</table>
	<div id="newRuleDivId" style="display: block;">
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td CLASS="multiLabel" width="30%">#</td>
			<td CLASS='multiLabel' width='35%'>Rule For</td>
			<td CLASS='multiLabel' width='35%'>Rule With</td>
		</tr>
		<tr>
			<td class="LABEL" width="30%">Hospital Service</td>
			<td class="multiControl" width="35%"><div id="divNewChargeTypeId"><select name="strNewChargeTypeId" class="comboNormal" onchange="ajaxNewWardType();">
               <bean:write name="chargeRuleBean" property="strNewChargeTypeCombo" filter="false"/>                
            </select></div><div id="divNewChargeTypeLabelId"></div></td>
            <td class="multiControl" width="35%"><div id="divOldChargeTypeId"><select name="strOldChargeTypeId" class="comboNormal" onchange="ajaxOldWardType();">
               <bean:write name="chargeRuleBean" property="strOldChargeTypeCombo" filter="false"/> 
            </select></div><div id="divOldChargeTypeLabelId"></div></td>
		</tr>
		<tr>
			<td class="LABEL" width="30%">Ward Type</td>
			<td class="multiControl" width="35%"><div id="newWardDiv"><select name="strNewIPDChargeTypeId" class="comboNormal" >
                 <option value="0">Select Value</option>
            </select></div><input type=hidden name=strHNewIPDChargeTypeId ><div id="newWardDivLabel"></div></td>
            <td class="multiControl" width="35%"><div id="oldWardDiv"><select name="strOldIPDChargeTypeId" class="comboNormal" >
               <option value="0">Select Value</option>
            </select></div><input type=hidden name=strHOldIPDChargeTypeId ><div id="OldWardDivLabel"></div></td>
		</tr>
		<tr>
			<td class="LABEL" width="30%">Patient Category</td>
			<td class="multiControl" width="35%"><div id="newCategoryDiv"><select name="strNewPatientCatcode" class="comboNormal" >
               <option value="0">Select Value</option> 
            </select></div><input type=hidden name=strHNewPatientCatcode ><div id="newCategoryDivLabel"></div></td>
            <td class="multiControl" width="35%"><div id="oldCategoryDiv"><select name="strOldPatientCatcode" class="comboNormal" >
                <option value="0">Select Value</option>
            </select></div><input type=hidden name=strHOldPatientCatcode ><div id="OldCategoryDivLabel"></div></td>
		</tr>
		</table>
	
	    <table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="LABEL" width="30%"><font color="red">*</font>Variation</td>
			<td class="CONTROL" width="70%"><input type="text" class="txtFldNormal" name="strVariation" value="" maxlength="7,2"
				onkeypress="return validateData(event,7);"> % </td>
		</tr>
		
		<tr>
			<td class="LABEL" width="30%"><font color="red">*</font>Rule Name</td>
			<td class="CONTROL" width="70%"><input type="text" class="txtFldNormal" name="strRuleName" value="" maxlength="30"
				onkeypress="return validateData(event,4);"></td>
		</tr>
		
		<tr>
			<td width="30%" class="LABEL"><font
				color="red">*</font>Effective From</td>
			<td width="70%" class="CONTROL"><div id="divEffectiveId"><dateTag:date
				name="strEffectiveFrom" value="${chargeRuleBean.strEffectiveFrom}"/></div><div id="divEffectiveLabelId"></div></td>
		</tr>
		
		<tr>
			<td width="30%" class="LABEL">Remarks</td>
			<td width="70%" class="CONTROL"><textarea rows="2"
				name="strRemarks"></textarea></td>
		</tr>
	</table>
</div>
	
	
	
	
	<table class="TABLEWIDTH" border="0" align="center">
		
		<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	
	<div id='insertdiv' style="display: block;">
	<div align="center"><!--<img name="save"
		src="../../hisglobal/images/btn-sv.png"
		style="cursor: pointer; cursor: hand;" title="Save Record"
		onClick="return validate1();"> <img
		src="../../hisglobal/images/btn-clr.png"
		style="cursor: pointer; cursor: hand;" title="Reset Content"
		onClick="clearPage();" /> <img
		src="../../hisglobal/images/btn-ccl.png"
		style="cursor: pointer; cursor: hand;" title="Cancel Process"
		onclick="cancelFunc();"></div>
		-->
		<br><a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</div>
	</div>	
	<div id='updatediv' style="display: none;">
	<div align="center"><!-- <img name="save"
		src="../../hisglobal/images/btn-sv.png"
		style="cursor: pointer; cursor: hand;" title="Save Record"
		onClick="return validate2();"> <img
		src="../../hisglobal/images/btn-clr.png"
		style="cursor: pointer; cursor: hand;" title="Reset Content"
		onClick="clearPage();" /> <img
		src="../../hisglobal/images/btn-ccl.png"
		style="cursor: pointer; cursor: hand;" title="Cancel Process"
		onclick="cancelPage();">
		 -->
		<br><a href="#" class="button" id="" onclick=' return validate2();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelPage();"><span class="cancel">Cancel</span></a>
		</div>
	</div>	
	<div id='modifydiv' style="display: none;">
	<div align="center"><!-- <img name="save"
		src="../../hisglobal/images/btn-sv.png"
		style="cursor: pointer; cursor: hand;" title="Save Record"
		onClick="return validate3();"> <img
		src="../../hisglobal/images/btn-clr.png"
		style="cursor: pointer; cursor: hand;" title="Reset Content"
		onClick="clearPage();" /> <img
		src="../../hisglobal/images/btn-ccl.png"
		style="cursor: pointer; cursor: hand;" title="Cancel Process"
		onclick="cancelPage();">
		 -->
		
		<br><a href="#" class="button" id="" onclick=' return validate3();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelPage();"><span class="cancel">Cancel</span></a>
		</div>
	</div>	
		
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="strHidRuleId" value="${chargeRuleBean.strHidRuleId}" />
	<input type="hidden" name="strHNewIPDChargeTypeId" value="${chargeRuleBean.strHNewIPDChargeTypeId}" />
	<input type="hidden" name="strHNewPatientCatcode" value="${chargeRuleBean.strHNewPatientCatcode}" />
	<input type="hidden" name="strHOldIPDChargeTypeId" value="${chargeRuleBean.strHOldIPDChargeTypeId}" />
	<input type="hidden" name="strHOldPatientCatcode" value="${chargeRuleBean.strHOldPatientCatcode}" />
	
	<input type="hidden" name="strNewChargeComboValues" value="${chargeRuleBean.strNewChargeComboValues}" />
	
	
	
	<cmbPers:cmbPers />
</html:form>

<tag:autoIndex></tag:autoIndex>

</body>
</html>