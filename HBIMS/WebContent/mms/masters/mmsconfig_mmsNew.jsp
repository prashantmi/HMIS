<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<meta charset="utf-8">
<title>Store Setup</title>
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script src="../../hisglobal/masterutil/js/master.js"></script>
<script src="../../hisglobal/js/tab.js"></script>
<script src="../../hisglobal/js/calendar.js"></script>
<script src="../../hisglobal/js/validation.js"></script>
<script src="../../hisglobal/js/util.js"></script>
<style type="css/text">
.txtFldLarge {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-weight: normal;
	color: #000000;
	height: 14px;
	width: 200px;
}
</style>
<script type="text/javascript">

	
	function validate1()
	{	
		retVal = false;
		
		if(document.forms[0].strSCMIntegration.value!=null && document.forms[0].strSCMIntegration.value=="1" && document.forms[0].strBillingIntegration.value == "1"){
			alert("Billing can't be done in case of Integration with e-Aushadhi");
			document.forms[0].strBillingIntegration.value = "0";
			return false;
		}
		
		//if( document.forms[0].strIssueRateConfigFlg.value=='1' && document.forms[0].strConfigIssueRate.value=='0' && document.forms[0].strIssueRateConfigFlg.value<='100')
		var paramVal = document.forms[0].strConfigIssueRate.value;
		if(document.forms[0].strIssueRateConfigFlg.value=='1')
		{
		  if(paramVal<100)
		  {
		   alert("Issue Rate always greater than or equal to 100 ");
		   return false;
		  }
		}		
		var hisValidator = new HISValidator("mmsConfigBean");
		hisValidator.addValidation("strStoreId", "dontselect=0","Please Select a Store  Name");
		//hisValidator.addValidation("strCountryCode", "dontselect=0","Please Select a Country Name");
		//hisValidator.addValidation("strStateCode", "dontselect=0","Please Select a State  Name");
			
		if(document.getElementById("finDateEntryDivId").style.display == "block")
		{
			hisValidator.addValidation("strFinancialStartDate", "req","Financial Start Date is a Mandatory Field");
			hisValidator.addValidation("strFinancialEndDate", "req","Financial End Date is a Mandatory Field");
			hisValidator.addValidation("strFinancialEndDate", "dtgt="+document.forms[0].strFinancialStartDate.value+"","Financial End Date should be Greater than Financial Start Date");
		}
		hisValidator.addValidation("strResidualCost","req","Residual Cost for Auction is a Mandatory Field");
		
		hisValidator.addValidation("strResidualCost", "amount=9,2","Residual Cost for Auction should be in 000000000.00");
		hisValidator.addValidation("strIndianDeliveryTime","req","Indian Delivery Time is a Mandatory Field");
		hisValidator.addValidation("strImportedDeliveryTime","req","Imported Delivery Time is a Mandatory Field");
		hisValidator.addValidation("strIndianDeliveryTime","req","Indian Delivery Time is a Mandatory Field");
		hisValidator.addValidation("strStampPaperAmt","req","Stamp Paper Amount is a Mandatory Field");
		hisValidator.addValidation("strCommitteeFilePath", "req","Path For Committee File Recommendation is a Mandatory Field");
		
		retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal)
		{
		    
		    var paramVal = document.forms[0].strContractValue.value;
		// alert("paramVal:"+paramVal);
		    if (paramVal<0 || paramVal >100)
			{
				  alert("Please Enter Contract Value greater than 0% and less than 100%!!!");
				  document.forms[0].strContractValue.value=document.forms[0].strHiddenContractValue.value;
				  return;
			}
			
		    var   chkVal = document.forms[0].strExpAlertDays.value;
            var   prsVal = parseInt(document.forms[0].strExpAlertDays.value);
				if (chkVal != "" && !(prsVal >= "0" && prsVal <= "364"))
				{
				  alert("Please Enter days less than 365!!!");
				  retVal = false;
				}
				else
				{				
					document.forms[0].selectedTab.value = "SAVEGENDTLS";
					document.forms[0].submit();
				}	

		}else{
		
		return false;
		}
		
	}


function displayValues(cmbObj){

	

	var val = cmbObj.value;
	document.getElementById("errMsg").innerHTML=""; 
	document.getElementById("normalMsg").innerHTML=""; 
	//document.forms[0].strIssueRateConfigFlg.value="0";
	//document.forms[0].strConfigIssueRate.value="0";
	document.forms[0].strFinancialStartDate.value = document.forms[0].strCtDate.value;
	document.forms[0].strFinancialEndDate.value = document.forms[0].strCtDate.value;
		
		
	if(val == '0'){
	
	
		document.getElementById("finDateEntryDivId").style.display = "none";
		document.getElementById("finDateDisDivId").style.display = "none";
			
		return false;
	
	}
	
	
	if(val.split('^')[1].length > 1){
		
		var temp = val.split('^');
		
		document.getElementById("finDateEntryDivId").style.display = "none";
		
		
		document.getElementById("finStartDateDivID").innerHTML = temp[1];
		document.getElementById("finEndDateDivID").innerHTML = temp[2];
		
		document.forms[0].strFinancialStartDate.value = temp[1];
		document.forms[0].strFinancialEndDate.value = temp[2];
		
		document.getElementById("finDateDisDivId").style.display = "block";
		
			
	}else{
	
		document.getElementById("finDateEntryDivId").style.display = "block";
		document.getElementById("finDateDisDivId").style.display = "none";
		
				
		document.forms[0].strFinancialStartDate.value = document.forms[0].strCtDate.value;
			document.forms[0].strFinancialEndDate.value = document.forms[0].strCtDate.value;
		
		
	}
	

}

function clearPage(mode){

	document.forms[0].selectedTab.value = mode ;
	document.forms[0].submit();
}

function getStateCmbo()
{ 	
	 var mode="GETSTATE";
     //var url="mmsConfigMstACTION.cnt?selectedTab="+mode+"&strCountryCode="+document.forms[0].strCountryCode[document.forms[0].strCountryCode.selectedIndex].value;
     var url="MmsConfigMstCNT.cnt?selectedTab="+mode+"&strCountryCode="+document.forms[0].strCountryCode[document.forms[0].strCountryCode.selectedIndex].value;
     ajaxFunction(url,"1");
	 
}
function getAjaxResponse(res,mode)
{
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
   
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 
       
  	
      if(mode=="1")
      {
                 				 
				 var objVal = document.getElementById("stateCmb");
                 objVal.innerHTML = "<select name = 'strStateCode' class='form-control'>" + res + "</select>";
				 
      }
     
}

function showHideRate(obj)
{
  if(obj.checked)
  {
     document.getElementById("issueRate").style.display="block";
     document.forms[0].strIssueRateConfigFlg.value="1";
    
  }
  else
  {
    document.getElementById("issueRate").style.display="none";
    document.forms[0].strIssueRateConfigFlg.value="0";
  }
}

function initValues()
{
	if(document.forms[0].strChkFlg.value=='1')
	 document.getElementById("issueRate").style.display="block";
	else
	  document.getElementById("issueRate").style.display="none";
}
/*
function setHospCode()
{
	var value=document.forms[0].strSCMIntegration.value;
	if(value=='1')
		document.getElementById("hospitalcode").style.display="block";
	else
		document.getElementById("hospitalcode").style.display="none";
	
}*/
function setValueZero()
{
	if(document.forms[0].strContractValue.value==null || document.forms[0].strContractValue.value==""){
		document.forms[0].strContractValue.value=document.forms[0].strZero.value;
	}
	if(document.forms[0].strStampPaperAmt.value==null || document.forms[0].strStampPaperAmt.value==""){
		document.forms[0].strStampPaperAmt.value=document.forms[0].strZero.value;
	}
	
}

function chkSCMInt()
{
	//alert(document.forms[0].strSCMIntegration.value);
	if(document.forms[0].strSCMIntegration.value!=null && document.forms[0].strSCMIntegration.value=="1" && document.forms[0].strBillingIntegration.value == "1"){
		alert("Billing can't done in case of Integration with e-Aushadhi");
		document.forms[0].strBillingIntegration.value = "0";
		return false;
	}
	
}
</script>
<style type="text/css">
.legendvs{
    position: absolute;
    right: 100px;
    line-height: 1.2em;
    top: 0.8em;
}
</style>
</head>
<body onload="setValueZero(); initValues(); ">
<html:form action="/masters/MmsConfigMstBSCNT" method="post" name="mmsConfigBean" type="mms.masters.controller.fb.MmsConfigMstFB" >
<fieldset form="form1"><br>
<div class="prescriptionTile">
<div class="row rowFlex reFlex" >
<div class="col-sm-5">
<p class="subHeaders" style="margin-bottom: 0;">
<i class="fas fa-university" style="font-size: 26px;"></i>
Store Setup
<i class="fas fa-angle-double-right"></i>
<label>General Details</label></p>
</div>
<div class="legendvs">
<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
<i class="fas fa-ban iround"  title="Cancel"></i>
</button>
<button type="button" class=" btn btn-secondary btn-circle" onclick="clearPage('mmsgeneraldtls');" style="background: #b9b9b9; border-color:#b9b9b9;margin-top: 0.25rem !important;">
<img  src="/HIS/hisglobal/images/clear3.png" title="Clear" style="width: 23px; color: #fff;">
</button>
<button  type="button" id="submitId" onclick=' return validate1();' class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' style="background-color: #5cb85c;">					
<i class="fa fa-save iround"  title="Save" ></i>
</button> 							                 
  </div> 
</div>


<center>
<div id="errMsg" class="errMsg"><bean:write name="mmsConfigBean" property="strErrMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="mmsConfigBean" property="strNormalMsg" /></div>
</center>
<div class="row">
<center>
	
	
     <tag:tabNew tabList="${mmsConfigBean.lhm}" selectedTab="mmsgeneraldtls" align="center" width="TABLEWIDTH"></tag:tabNew>
</center>
</div>
<div class="row">
<div class="col-sm-2">

<label>
Store  Name
</label>
</div>
<div class="col-sm-2">
<select class="form-control" name="strStoreId" onchange="displayValues(this);">
<bean:write  name="mmsConfigBean" property="strStoreDetailsValues"  filter="false"/>
</select>
</div>
<div class="col-sm-8" id="finDateDisDivId" style="display: none">
<div class="row">
<div class="col-sm-3">
<label>
Financial Start Date
</label>
</div>
<div class="col-sm-3" id="finStartDateDivID">
<%-- <date:date name="strFinancialStartDate" value="${mmsConfigBean.strCtDate}"></date:date>
 --%></div>
<div class="col-sm-3">
<label>
Financial End Date</label>
</div>
 <div class="col-sm-3" id="finEndDateDivID">
<%-- <date:date name="strFinancialEndDate" value="${mmsConfigBean.strCtDate}"></date:date>
 --%></div> 
</div>
</div>
</div>
<div class="row">
<div class="col-sm-2">
<label>
Expiry Alert
</label>
</div>
<div class="col-sm-2">
<input type='text' name="strExpAlertDays" value="${mmsConfigBean.strExpAlertDays }" 
onkeypress="return validateData(event,7);" maxlength="12" class='form-control'>
</div>
<div class="col-sm-2">

<label>
Residual Cost for Auction</label>
</div>
<div class="col-sm-2">
<input type='text' name="strResidualCost" value="${mmsConfigBean.strResidualCost }" 
		onkeypress="return validateData(event,7);" maxlength="12" class='form-control'>
</div>
<div class="col-sm-2">

<label>
Store Category
</label>
</div>
<div class="col-sm-2">
<select name="strItemCatgId" onchange="" class='form-control'>
<bean:write  name="mmsConfigBean" property="strItemCatgCmb"  filter="false"/>
</select>
</div>
</div>


<div class="row">
<div class="col-sm-2">
<label>
Default Country Name
</label>
</div>
<div class="col-sm-2">
<select name="strCountryCode" onchange="getStateCmbo();" class="form-control">
<bean:write  name="mmsConfigBean" property="strCountryCmb"  filter="false"/>
</select>
</div>
<div class="col-sm-2">

<label>
Default State Name
</label>
</div>
<div class="col-sm-2" id="stateCmb">
<select name="strStateCode"  class='form-control'>
<bean:write name="mmsConfigBean" property="strStateCmb" filter="false" />
</select>
</div>
<div class="col-sm-2">

<label>
Purchase Lead Time
</label>
</div>
<div class="col-sm-2">
<input type='text' name="strPurchaseLeadTime" value="${mmsConfigBean.strPurchaseLeadTime}" 
onkeypress="return validateData(event,7);" maxlength="12" class='form-control' />
</div>
</div>

<div class="row">
<div class="col-sm-2">

<label>
Shelf Life(Days)
</label>
</div>
<div class="col-sm-2">
<input type='text' name="strSelfLife" value="${mmsConfigBean.strSelfLife}" 
onkeypress="return validateData(event,7);" maxlength="12" class='form-control'/>

</div>
<div class="col-sm-2">

<label>
Tin No.
</label>
</div>
<div class="col-sm-2">
<input type='text' name="strTinNo" value="${mmsConfigBean.strTinNo}" 
onkeypress="return validateData(event,7);" maxlength="11" class='form-control' />
</div>
</div>
<div class="row" align="left">
<div class="col-sm-12" >
<p class="subHeaders" style="margin-bottom: 0;">
Billing Configuration
</p>
</div></div>
<div class="row">
<div class="col-sm-12" align="right">
<font color="red">*</font>
<label>Whether Integration With Billing</label>
<logic:equal name="mmsConfigBean" property="strSCMIntegration" value="1">
<html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="1" disabled="true">Yes</html:radio>&nbsp;&nbsp;
<html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="0" disabled="true">No</html:radio>
</logic:equal>
<logic:notEqual name="mmsConfigBean" property="strSCMIntegration" value="1">
<html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="1" >Yes</html:radio>&nbsp;&nbsp;
<html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="0" >No</html:radio>
</logic:notEqual>
</div>
</div>
<div class="row" align="left">
<div class="col-sm-12" >
<p class="subHeaders" style="margin-bottom: 0;">
FMS Configuration
</p>
</div>
</div>
<div class="row">
<div class="col-sm-12" align="right">
<font color="red">*</font>
<label>Whether Integration With FMS</label>
<logic:equal name="mmsConfigBean" property="strSCMIntegration" value="1">
<html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="1" disabled="true">Yes</html:radio>&nbsp;&nbsp;
<html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="0" disabled="true">No</html:radio>
</logic:equal>
<logic:notEqual name="mmsConfigBean" property="strSCMIntegration" value="1">
<html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="1" >Yes</html:radio>&nbsp;&nbsp;
<html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="0" >No</html:radio>
</logic:notEqual>
</div>
</div>
<div class="row" align="left">
<div class="col-sm-12">
<p class="subHeaders" style="margin-bottom: 0;">
Tax Related Configuration
</p>
</div>
</div>
<div class="row">
<div class="col-sm-8"></div>
<div class="col-sm-2"  align="right">
<label><font color="red">*</font>Tax Rate</label>
</div>
<div class="col-sm-2">
<input type='text' name="strTaxRate" value="${mmsConfigBean.strTaxRate}" onkeypress="return validateData(event,7);"
maxlength="10" class='form-control' >
</div>
</div>
<div class="row" align="left">
<div class="col-sm-6" >
<p class="subHeaders" style="margin-bottom: 0;">
Purchase Order Parameter
</p>
</div>
</div>
<div class="row">
<div class="col-sm-2">
<label><font color="red">*</font>Indian Delivery Time</label>
</div>
<div class="col-sm-2">
<input type="text" onkeypress="return validateData(event,5);" name="strIndianDeliveryTime" 
 class="form-control" maxlength="4" placeholder="Days From P.O. Generation">
</div> 
<div class="col-sm-2">

<label><font color="red">*</font>Imported Delivery Time</label>
</div>
<div class="col-sm-2">
<input onkeypress="return validateData(event,5);" type="text" name="strImportedDeliveryTime" 
placeholder="Days From P.O. Generation" class="form-control" maxlength="4"> 
</div> 
<div class="col-sm-2">
<label><font color="red">*</font>Contract Value</label>
</div>
<div class="col-sm-2">
<input onkeypress="return validateData(event,5);" type="text" name="strContractValue"
placeholder="Percentage" class="form-control" maxlength="3">
</div> 
</div>
<div class="row" style="margin-top: 10px;">
<div class="col-sm-2">
<label><font color="red">*</font>Stamp Paper Amount</label>
</div>
<div class="col-sm-2">
<input onkeypress="return validateData(event,5);" type="text" name="strStampPaperAmt" 
placeholder="Rs" class="form-control" maxlength="4">
</div> 
<div class="col-sm-2" align="right">
</div>
<div class="col-sm-4" >
<label><font color="red">*</font>Single Item Multi Supplier on PO Desk</label>
</div>
<div class="col-sm-2" align="right">
<html:radio name="mmsConfigBean" property="strWhetherSingleItemMultiSupplier" value="1">&nbsp;Yes</html:radio>&nbsp;&nbsp;
<html:radio name="mmsConfigBean" property="strWhetherSingleItemMultiSupplier" value="0">&nbsp;No</html:radio>
</div>

</div>
<div class="row" >
<div class="col-sm-6" >
<p class="subHeaders" style="margin-bottom: 0;">
Committee Recommendation
</p>
</div>
<div class="col-sm-4" >
<label><font color="red">*</font>Path For Committee Recommendation</label>
</div>
<div class="col-sm-2" >
<input  type="text"  class="form-control" value="${mmsConfigBean.strCommitteeFilePath }" name="strCommitteeFilePath">
</div>
</div>
<div class="row">
<div class="col-sm-6" >
<p class="subHeaders" style="margin-bottom: 0;">
OSTF Category Patient Limit Amount
</p>
</div>
<div class="col-sm-4" >
<label><font color="red">*</font>OSTF Category Limit Amount</label>
</div>
<div class="col-sm-2" >
<input  type="text"  class="form-control" value="${mmsConfigBean.strIndentLimitAmt }" name="strIndentLimitAmt">
</div>
</div>
<hr>
<div class="row">
<div class='col-sm-9'>
</div>
<div class='col-sm-3' align="right">
<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>
<label>&nbsp;Mandatory Fields</label>

</div>
</div>
 
<div class="row">
<div id="finDateEntryDivId" style="display: none">
<div class="col-sm-2">
<label>
<font color="red">*</font>Financial Start Date</label>
</div>
<div class="col-sm-2">		
<date:date name="strFinancialStartDate" value="${mmsConfigBean.strCtDate}"></date:date>
</div>
<div class="col-sm-2">
<label>
<font color="red">*</font>Financial End Date</label>
</div>
<div class="col-sm-2">		
<date:date name="strFinancialEndDate" value="${mmsConfigBean.strCtDate}"></date:date>
</div>	
</div>
</div>
<%--
<div class="row">
<div id="finDateDisDivId" style="display: none">
<div class="col-sm-2">
<label>
<font color="red">*</font>Financial Start Date</label>
</div>
<div class="col-sm-2" id="finStartDateDivID">		
 </div>
<div class="col-sm-2">
<label>
<font color="red">*</font>Financial End Date</label>
</div>
<div class="col-sm-2" id="finEndDateDivID">		
 </div>	
</div>		
</div>		


 --%>

































<%-- <fieldset class="col-sm-6">
 <div class="row">
<p class="subHeaders" style="margin-bottom: 0;">
Committee Recommendation</font>
</p>
</div><br>
<div class="row">
<div class="col-sm-6" >

<label><font color="red">*</font>Path For Committee Recommendation</label>
</font>
</div>
<div class="col-sm-6" >
<input  type="text"  class="form-control" value="${mmsConfigBean.strCommitteeFilePath }" name="strCommitteeFilePath">
</div>
</div>
 </fieldset> --%>





	<%-- <table class="TABLEWIDTH">   
	<!-- <tr class="HEADER">
			<td colspan="2">Store Setup&gt;&gt; General Details</td>
		</tr> -->
		<tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Store  Name
		</td>
		<td class="CONTROL" width="50%" > <select name="strStoreId" onchange="displayValues(this);">
		<bean:write  name="mmsConfigBean" property="strStoreDetailsValues"  filter="false"/>
		</select>
		</td>
		</tr>
		</table> --%>
		
		<%-- <div id="finDateEntryDivId" style="display: none">
		<table class="TABLEWIDTH">
		<tr>
		   <td class="LABEL" width="50%"><font color="red">*</font>Financial Start Date 
		</td>
		<td class="CONTROL" width="50%" ><date:date name="strFinancialStartDate" value="${mmsConfigBean.strCtDate}"></date:date>
		</td>
		</tr>
		<tr>
		   <td class="LABEL" width="50%"><font color="red">*</font>Financial End Date 
		</td>
		<td class="CONTROL" width="50%"><date:date name="strFinancialEndDate" value="${mmsConfigBean.strCtDate}"></date:date>
		</td>
		</tr>
		</table>
		</div>
		
		<div id="finDateDisDivId" style="display: none">
		<table class="TABLEWIDTH" >
		<tr>
		   <td class="LABEL" width="50%" >Financial Start Date 
		</td>
		<td class="CONTROL" width="50%" ><div id="finStartDateDivID"></div>
		</td>
		</tr>
		<tr>
		   <td class="LABEL" width="50%" >Financial End Date 
		</td>
		<td class="CONTROL" width="50%" ><div id="finEndDateDivID"></div>
		</td>
		</tr>
		</table>
		
		</div> --%>
		<%-- <table class="TABLEWIDTH">
		<tr>
		   <td class="LABEL" width="50%" ><font color='red'>*</font>Expiry Alert
		</td>
		<td class="CONTROL" width="50%" >
		<input type='text' name="strExpAlertDays" value="${mmsConfigBean.strExpAlertDays }" onkeypress="return validateData(event,7);"
		maxlength="12" class='txtFldNormal'>days
		</td>
		</tr>
		</table> --%>
		
		
		
		<%-- <table class="TABLEWIDTH">
		<tr>
		   <td class="LABEL" width="50%" ><font color='red'>*</font>Residual Cost for Auction
		</td>
		<td class="CONTROL" width="50%" >
		<input type='text' name="strResidualCost" value="${mmsConfigBean.strResidualCost }" 
		onkeypress="return validateData(event,7);" maxlength="12" class='txtFldNormal'>%
		</td>
		</tr>
		</table> --%>
		<div id="budget" style="display:none">
			<table class="TABLEWIDTH">
			<tr>
			   <td class="LABEL" width="50%">
			  
			      <html:checkbox name="mmsConfigBean"  property="strBudgetFlg" value="1" ></html:checkbox>
			  
			</td>
			<td class="CONTROL" width="50%"><b>Whether Budget Functionality Required</b></td>
			</tr>
			</table>
		
			<table class="TABLEWIDTH">
			<tr>
			   <td class="LABEL" width="50%">
			    <html:checkbox name="mmsConfigBean"  property="strChkFlg" value="1" onclick="showHideRate(this);" ></html:checkbox>
			   
			  <!-- 
			      <html:checkbox name="mmsConfigBean"  property="strIssueRateConfigFlg" value="1"></html:checkbox>
			   --> 
			</td>
			<td class="CONTROL" width="50%"><b>Whether Issue Rate Is Configurable</b></td>
			</tr>
			</table>
		    <div id="issueRate" style="display:none;">
				  <table class="TABLEWIDTH">
					<tr>
					   <td class="LABEL" width="50%">Issue Rate</td>
					    <td class="CONTROL" width="50%">
					  
					    <input type='text' name="strConfigIssueRate" value="${mmsConfigBean.strConfigIssueRate}" onkeypress="return validateData(event,7);" maxlength="5" class='txtFldNormal'><b>% of Purchase Rate</b>
					  
					 </td>  	
					</tr>		
				</table>
		   </div>
		</div>
		<table class="TABLEWIDTH">
		<%-- <tr>
		<td class="LABEL" width="50%" >Store Category
		</td>
		<td class="CONTROL" width="50%" > <select name="strItemCatgId" onchange="">
		<bean:write  name="mmsConfigBean" property="strItemCatgCmb"  filter="false"/>
		</select>
		</td>
		</tr> --%>
		
		
		<%-- <tr>
		<td class="LABEL" width="50%">Default Country Name
		</td>
		<td class="CONTROL" width="50%" >
		 <select name="strCountryCode" onchange="getStateCmbo();">
		   <bean:write  name="mmsConfigBean" property="strCountryCmb"  filter="false"/>
		 </select>
		</td>
		</tr> --%>
		
		<%-- <tr>
		<td class="LABEL" width="50%" >Default State Name
		</td>
		<td class="CONTROL" width="50%" >
			
			<div id="stateCmb" >
			     <select name="strStateCode"  class='comboNormal'>
			           <bean:write name="mmsConfigBean" property="strStateCmb" filter="false" />
                  </select>
                </div>
			
		</td>
		</tr> --%>
		
		
		<%-- <tr>
		<td class="LABEL" width="50%" >Purchase Lead Time(Days)</td>
		<td class="CONTROL" width="50%" >
		<input type='text' name="strPurchaseLeadTime" value="${mmsConfigBean.strPurchaseLeadTime}" onkeypress="return validateData(event,7);" maxlength="12" class='txtFldNormal' />
        </td>
		</tr> --%>
		
		<%-- <tr>
		<td class="LABEL" width="50%" >Shelf Life(Days)</td>
		<td class="CONTROL" width="50%" >
		<input type='text' name="strSelfLife" value="${mmsConfigBean.strSelfLife}" onkeypress="return validateData(event,7);" maxlength="12" class='txtFldNormal' />
        </td>
		</tr> --%>
		
		<%-- <tr>
		<td class="LABEL" width="50%" >Tin No.</td>
		<td class="CONTROL" width="50%" >
		<input type='text' name="strTinNo" value="${mmsConfigBean.strTinNo}" onkeypress="return validateData(event,7);" maxlength="11" class='txtFldMax' />
        </td>
		</tr> --%>

		<!-- <tr>
			<td class="TITLE" colspan="2">Billing Configuration</td>
		</tr> -->
		<%-- <tr>
		 	<td class="LABEL" width="50%" ><font color="red">*</font>Whether Integration With Billing 
		</td>
		<logic:equal name="mmsConfigBean" property="strSCMIntegration" value="1"><td class="CONTROL" width="50%">
		 	 <html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="1" disabled="true">Yes</html:radio>&nbsp;&nbsp;
			<html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="0" disabled="true">No</html:radio>
			</td></logic:equal>
				<logic:notEqual name="mmsConfigBean" property="strSCMIntegration" value="1"><td class="CONTROL" width="50%">
		 	 <html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="1" >Yes</html:radio>&nbsp;&nbsp;
			<html:radio name="mmsConfigBean" property="strBillingIntegration" onclick="chkSCMInt();" value="0" >No</html:radio>
			</td></logic:notEqual>
		</tr> --%>
		
		<!-- <tr>
			<td class="TITLE" colspan="2">FMS Configuration</td>
		</tr> -->
		<%-- <tr>
		 	<td class="LABEL" width="50%" ><font color="red">*</font>Whether Integration With FMS 
		</td>
		<logic:equal name="mmsConfigBean" property="strSCMIntegration" value="1"><td class="CONTROL" width="50%">
		 	 <html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="1" disabled="true">Yes</html:radio>&nbsp;&nbsp;
			<html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="0" disabled="true">No</html:radio>
			</td></logic:equal>
				<logic:notEqual name="mmsConfigBean" property="strSCMIntegration" value="1"><td class="CONTROL" width="50%">
		 	 <html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="1" >Yes</html:radio>&nbsp;&nbsp;
			<html:radio name="mmsConfigBean" property="strFMSIntegration" onclick="" value="0" >No</html:radio>
			</td></logic:notEqual>
		</tr> --%>
		
		<%-- <tr>
			<td class="TITLE" colspan="2">Tax Related Configuration</td>
		</tr>
		<tr>
		 	<td class="LABEL" width="50%" ><font color="red">*</font>Tax Rate 
		</td>
			<td class="CONTROL" width="50%">
			<input type='text' name="strTaxRate" value="${mmsConfigBean.strTaxRate}" onkeypress="return validateData(event,7);" maxlength="10" class='txtFldNormal' >
		</tr> --%>
		
		<!-- <tr>
			<td class="TITLE" colspan="2">Purchase Order Parameter</td>
		</tr> -->
		<%-- <tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Indian Delivery Time
		</td>
		<td class="CONTROL" width="50%"><input type="text" onkeypress="return validateData(event,5);" name="strIndianDeliveryTime" value="${mmsConfigBean.strIndianDeliveryTime }" class="txtFldMin" maxlength="4"> &nbsp;(Days From P.O. Generation)</td>
		</tr>
		<tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Imported Delivery Time
		</td>
		<td class="CONTROL" width="50%">
		<input onkeypress="return validateData(event,5);" type="text" name="strImportedDeliveryTime" value="${mmsConfigBean.strImportedDeliveryTime }" class="txtFldMin" maxlength="4"> &nbsp;(Days From P.O. Generation)</td>
		</tr> --%>
		
		<%-- <tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Contract Value 
		</td>
		<td class="CONTROL" width="50%">
		<input onkeypress="return validateData(event,5);" type="text" name="strContractValue" value="${mmsConfigBean.strContractValue }" class="txtFldMin" maxlength="3"> &nbsp;(%)</td>
		</tr> --%>
		
		<%-- <tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Stamp Paper Amount(Rs.)
		</td>
		<td class="CONTROL" width="50%">
		<input onkeypress="return validateData(event,5);" type="text" name="strStampPaperAmt" value="${mmsConfigBean.strStampPaperAmt }" class="txtFldMin" maxlength="4"> &nbsp;Rs</td>
		</tr> --%>
		
		<%-- <tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Single Item Multi Supplier on PO Desk</td>
		<td class="CONTROL" width="50%">
			<html:radio name="mmsConfigBean" property="strWhetherSingleItemMultiSupplier" value="1">Yes</html:radio>&nbsp;&nbsp;
			<html:radio name="mmsConfigBean" property="strWhetherSingleItemMultiSupplier" value="0">No</html:radio>
		</td>
		</tr> --%>
		
		<%-- <tr>
			<td class="TITLE" colspan="2">Committee Recommendation</td>
		</tr>
		<tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>Path For Committee Recommendation  
		</td>
		<td class="CONTROL" width="50%" > <input  type="text"  value="${mmsConfigBean.strCommitteeFilePath }" name="strCommitteeFilePath">
		</td>
		</tr> --%>
		
		<%-- <tr>
			<td class="TITLE" colspan="2">OSTF Categoery Patient Limit AMount</td>
		</tr>
		<tr>
		<td class="LABEL" width="50%" ><font color="red">*</font>OSTF Cateory Limit Amount  
		</td>
		<td class="CONTROL" width="50%" >
		<input  type="text"  value="${mmsConfigBean.strIndentLimitAmt }" name="strIndentLimitAmt">
		</td>
		</tr> --%>
		
	<!-- </table>
		<table class="TABLEWIDTH">   
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table> -->
	
	<!-- <table class="TABLEWIDTH">
		<tr>

			<td align="center">
			<img style="cursor: pointer; "
				src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" />
				<img style="cursor: pointer; " src="../../hisglobal/images/btn-clr.png" onClick="clearPage('mmsgeneraldtls')" >
			<img  style="cursor: pointer; " src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			</td>
		</tr>
	</table>-->
	<div align="center" id=" ">					 
					 	<!-- <a href="#" class="button" id="submitId" onclick=' return validate1();'><span class="save">Save</span></a>
						<a href="#" class="button"	onclick="clearPage('mmsgeneraldtls');"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a> -->
				</div> 

<input type="hidden" name="strCtDate" value="${mmsConfigBean.strCtDate}">
<input type="hidden" name="strIssueRateConfigFlg" value="0">
<input type="hidden" name="strZero" value="0">
<input type="hidden" name="strHiddenContractValue" value="${mmsConfigBean.strContractValue }"	>	
<input 	type="hidden" name="selectedTab">
<input 	type="hidden" name="strSCMIntegration" value="${mmsConfigBean.strSCMIntegration}">
</div>
</fieldset>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>