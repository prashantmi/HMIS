<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<meta charset="utf-8">
<title>Modify Drug Master...</title>

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
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>

<script type="text/javascript">

function setIsMisc()
{
	if(document.forms[0].strIsMisc.checked)
	{
		document.forms[0].strIsMisc.value="1";
		
	}
	else
	{
		document.forms[0].strIsMisc.value="0";
	}
}

function chkItemSachet(){
	
	
	if(document.getElementsByName('strIsItemSachet')[0].value == 1){
	
	
		document.getElementsByName('strIsItemSachet')[0].checked = true;
	
	}
	
	if(document.getElementsByName('strIsQuantifiable')[0].value == 1){
	
		document.getElementsByName('strIsQuantifiable')[0].checked = true;
	
	}
	//alert(document.getElementsByName('strIsMisc')[0].value);
	if(document.getElementsByName('strIsMisc')[0].value == 1){
		
		
		document.getElementsByName('strIsMisc')[0].checked = true;
	
	}
	
}


function setIsItemSachet()
{
	
	if(document.forms[0].strIsItemSachet.checked)
	{
		document.forms[0].strIsItemSachet.value="1";
	}
	else
	{
		document.forms[0].strIsItemSachet.value="0";
	}
	
}
	
function setIsQuantifiable()
{
	if(document.forms[0].strIsQuantifiable.checked)
	{
		document.forms[0].strIsQuantifiable.value="1";
	}
	else
	{
		document.forms[0].strIsQuantifiable.value="0";
	}
}


function checkMandatoryDetails(){
	
	var obj = document.getElementsByName("strDrugType")[0];
	
	if(obj.value == 2 ){
		
		document.getElementById("manfMandatoryDivId").style.display = "none";	
		document.getElementById("manfNonMandatoryDivId").style.display = "block";
		
		document.getElementById("specMandatoryDivId").style.display = "none";	
		document.getElementById("specNonMandatoryDivId").style.display = "block";
		
	}else{
	
		document.getElementById("manfMandatoryDivId").style.display = "block";	
		document.getElementById("manfNonMandatoryDivId").style.display = "none";
		
		document.getElementById("specMandatoryDivId").style.display = "block";	
		document.getElementById("specNonMandatoryDivId").style.display = "none";
	
	}
	

}



function validate()
{
	var hisValidator = new HISValidator("drugBean");
	if(document.forms[0].strIsItemCodeRequired.value == '1'){
		 hisValidator.addValidation("strNewCPACode", "req", "Drug Code is a Mandatory Field" );
	}
    hisValidator.addValidation("strItemType", "dontselect=0","Please Select Drug Type");
    hisValidator.addValidation("strDrugName", "req", "Drug Name is a Mandatory Field" );
    
  
   
    
    /*Manufacturer and Specification not mandatory for non branded drug*/
    if(document.getElementsByName("strDrugType")[0].value!="2") {
    	// hisValidator.addValidation("strManufacturerId", "dontselect=0","Please Select Manufacturer Name");
    	 hisValidator.addValidation("strSpecification", "req","Specifications is a mandatory field");
    }
  
    //hisValidator.addValidation("strDefaultRate", "req", "Default Rate is a Mandatory Field" );
    hisValidator.addValidation("strDefaultRate", "amount=9,2", "Default Rate should be in format 0000000.00" ); 
    hisValidator.addValidation("strRateUnitId", "dontselect=0","Please Select Rate Unit");
    hisValidator.addValidation("strApprovedType", "dontselect=0","Please Select ApprovedType Type");
    hisValidator.addValidation("strIssueType ", "dontselect=0","Please Select Issue Type"); 
    hisValidator.addValidation("strEffectiveFrom", "date","Effective Date is a mandatory field");
   
    
   // hisValidator.addValidation("strConfigIssueRate", "amount=3,2","Issue Cost should be in 000.00");
    hisValidator.addValidation("strSpecification", "maxlen=4000", "Specifications should have less than or equal to 4000 Characters" );
    
    if(document.forms[0].strEdlCat.checked == true){
   		//alert('1');
    	document.forms[0].strEdlCat.value='1';
 		}else{
 	 		
 		document.forms[0].strEdlCat.value='0';
 	 		}
     
    var retVal = hisValidator.validate();
    hisValidator.clearAllValidations();
    
        if(retVal) 
	    {
	    	  
	   		document.forms[0].hmode.value="UPDATE";
	   		document.forms[0].submit();
	    }
}

function clearRecord()
{
	
	document.forms[0].strAddress.value="";
	document.forms[0].strSupplierName.value="";
	document.forms[0].strContactPerson.value="";
	document.forms[0].strCity.value="";
	document.forms[0].strPincode.value="";
	document.forms[0].strPhoneNo1.value="";
	document.forms[0].strPhoneNo2.value="";
	document.forms[0].strEmailId1.value="";
	document.forms[0].strEmailId2.value="";
	document.forms[0].strFaxNo1.value="";
	document.forms[0].strFaxNo2.value="";
	document.forms[0].strWebsite.value="";
	document.forms[0].strOrderNo.value="";
	document.forms[0].strBlackListedRemarks.value="";
	document.forms[0].strRemarks.value="";
	document.getElementById("errMsgId").innerHTML="";
	document.getElementById("warningMsgId").innerHTML="";
	document.getElementById("normalMsgId").innerHTML="";
	document.forms[0].strSupplierName.focus();
	
}
function chkPrevCPACode()
{
	if(document.getElementsByName("strIsItemCodeRequired")[0].value=="1"){
		document.getElementById("cpaCodeId").innerHTML="<font color='red'>*</font>Drug Code";
		//document.getElementById("CPAFINALID").style.display="block";
	}else{
			document.getElementById("cpaCodeId").innerHTML="Drug Code";
			//document.getElementById("CPAFINALID").style.display="none";
			
	}
	if(document.forms[0].strConfigIssueRate.value>'0')
	{
	   document.getElementById("issueRate").style.display='';
	}
	else
	{
	   document.getElementById("issueRate").style.display='none';
	}
	
}	


function getAjaxResponse(res,mode){
	
	if(mode=="1"){ 
		
		
		
			var objVal= document.getElementById("unitDivId");
			objVal.innerHTML = "<select name ='strMktRateUnitId' class='comboNormal' onchange=''>"+res+"</select>";		
		
	}	
	

}	
	


// Function for Numeric(11,2)
function numericWithTwoDecimalPlaces(fld, milSep, decSep, e)
{
	var sep = 0;
	var key = '';
	var milSep="";
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;
	//if (whichCode == 13) return true;  // Enter
	if (whichCode == 0)	return true; //tab-index
	//alert(whichCode);
	if (whichCode == 8) return true;  // Back-Space 
	key = String.fromCharCode(whichCode);  // Get key value from key code
		if (strCheck.indexOf(key) == -1) return false;  // Not a valid key
		//len = fld.value.length;
		
		len=11;
		for(i = 0; i < len; i++)
		if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep)) break;
		aux = '';
		
		for(; i < len; i++)
		if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
		aux += key;
		len = aux.length;
		if (len == 0) fld.value = '';
		if (len == 1) fld.value = ''+ decSep + '' + aux;
		if (len == 2) fld.value = ''+ decSep + aux;
		if (len > 2) {
		aux2 = '';
		for (j = 0, i = len - 3; i >= 0; i--) {
		if (j == 3) {
		aux2 += milSep;
		j = 0;
	}
	aux2 += aux.charAt(i);
	j++;
	}
	fld.value = " ";
	len2 = aux2.length;
	for (i = len2 - 1; i >= 0; i--)
	fld.value += aux2.charAt(i);
	fld.value += decSep + aux.substr(len - 2, len);
	}
return false;
}	

function view1(id1,id2,id3)
{
	document.getElementById(id1).style.display="none";
	document.getElementById(id2).style.display="block";
	document.getElementById(id3).style.display="block";
}
function view2(id1,id2,id3)
{
	document.getElementById(id1).style.display="block";
	document.getElementById(id2).style.display="none";
	document.getElementById(id3).style.display="none";
}

function setBatchNo()
{
	if(document.forms[0].strBatchNo.checked)
	{
		document.forms[0].strBatchNo.value="1";
	}
	else
	{
		document.forms[0].strBatchNo.value="0";
	}
}
function setExpiryDate()
{
	if(document.forms[0].strExpiryDate.checked)
	{
		document.forms[0].strExpiryDate.value="1";
	}
	else
	{
		document.forms[0].strExpiryDate.value="0";
	}
}


function setIsItemSachet()
{
	
	if(document.forms[0].strIsItemSachet.checked)
	{
		document.forms[0].strIsItemSachet.value="1";
	}
	else
	{
		document.forms[0].strIsItemSachet.value="0";
	}
	
}
	
function setIsQuantifiable()
{
	if(document.forms[0].strIsQuantifiable.checked)
	{
		document.forms[0].strIsQuantifiable.value="1";
		
	}
	else
	{
		document.forms[0].strIsQuantifiable.value="0";
	}
}

</script>
<style type="text/css">
.legendvs{
    position: absolute;
    right: 100px;
    line-height: 1.2em;
    top: 0.6em;
}
</style>
</head>
<body onload="chkItemSachet(),chkPrevCPACode(),checkMandatoryDetails();">
<html:form action="/masters/DrugMstBSCNT" name="drugBean"
	type="mms.masters.controller.fb.DrugMstFB">
<fieldset form="form1"><br>
<div class="prescriptionTile">
<div class=" rowFlex reFlex" >
<div class="legendvs">
<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancel('LIST');">
<i class="fas fa-ban iround"  title="Cancel"></i>
</button>
<button  type="button" id="submitId" onClick="return validate();" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' style="background-color: #5cb85c;">					
<i class="fa fa-save iround"  title="Save" ></i>
</button> 							                 
  </div> 
</div>   

	<center>
	<div class="errMsg" id="errMsg"><bean:write name="drugBean"
		property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="drugBean" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="drugBean"
		property="strNormalMsg" /></div>

	<%-- <tag:tab tabLabel="Drug Master" selectedTab="FIRST" align="center"
		width="TABLEWIDTH" onlyTabIndexing="1">
	</tag:tab> --%>
	</center>
	
<div class="row" align="left">	

<p class="subHeaders" style="margin-bottom: 0;">
<i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>
Drug Master
<i class="fas fa-angle-double-right"></i>
<label>Modify</label></p>

<div class="col-sm-8" align="center">
<div class="row">
<div class="col-sm-2">
<label>Group Name:</label>
</div>
<div class="col-sm-4" align="left" style="font-weight: 400">
<bean:write name="drugBean" property="strGroupName" filter="false" />
</div>
<div class="col-sm-3">
<label>Generic Drug Name:</label>
</div>
<div class="col-sm-3" style="font-weight: 400" align="left">
<bean:write name="drugBean" property="strGenericItemName" filter="false" />
</div>
</div>
</div>
</div>
<br>
<div class="row">	
<div  class="col-sm-2" >
<label><font color="red">*</font>Drug Code</label>
</div>
<div  class="col-sm-2" >
<logic:equal value="0" name="drugBean" property="strPrevCPACode">
<input type="text" name="strNewCPACode" maxlength="20" value="${drugBean.strNewCPACode}"
onkeypress="return validateData(event,9);" class="form-control">
</logic:equal> 
<logic:notEqual value="0" name="drugBean" property="strPrevCPACode">
<bean:write name="drugBean" property="strPrevCPACode" filter="false" />
<input type="text" name="strNewCPACode" maxlength="20" value="${drugBean.strNewCPACode}"
onkeypress="return validateData(event,9);" class="form-control">
</logic:notEqual>
</div>

<div class="col-sm-2">

<label><font color="red">*</font>Drug Type</label>
</div>
<div class="col-sm-2">
<select name="strItemType" class="form-control">
<bean:write name="drugBean" property="strItemTypeValues" filter="false" />
</select>
</div>
<div class="col-sm-2">

<label><font color="red">*</font>Drug Name</label>
</div>
<div class="col-sm-2">
<input type="text"	name="strDrugName" maxlength="250" value="${drugBean.strDrugName}"
class="form-control"	onkeypress="return validateDataWithSpecialChars(event,18,'%.:;+-/*,½');" />
</div>
</div>
<div class="row">
<div class="col-sm-2">
<div id="manfMandatoryDivId" style=""><label><font color="red">*</font>Manufacturer</label>
</div>
<div id="manfNonMandatoryDivId" style="display: none;">
<label><font color="red">*</font>Manufacturer
</label></div>
</div>
<div class="col-sm-2">
<select name="strManufacturerId" class="form-control">
<bean:write name="drugBean" property="strManufacturerVal" filter="false" />
</select>
</div>
<div class="col-sm-2">
<label><font color="red">*</font>HSN Code.</label>
</div>
<div class="col-sm-2">
<input type="text"	name="strHSNCode" maxlength="10" value="${drugBean.strHSNCode}" class="form-control"
	onkeypress="return validateData(event,7);" />
</div>
<div class="col-sm-2">

<label><font color="red">*</font>Default Rate</label>
</div>
<div class="col-sm-2">
<input type="text"	name="strDefaultRate" value="${drugBean.strDefaultRate}" class="form-control" 
maxlength="14"	onkeypress="return validateData(event,7);" />
</div>
</div>

<div class="row">
<div class="col-sm-2">
<label><font color="red">*</font>Rate/Unit</label>
</div>
<div class="col-sm-2" id="unitDivId1">
<select	name="strRateUnitId" class="form-control">
<bean:write name="drugBean" property="strUnitValues" filter="false" />
</select>
</div>

<%-- <input type="hidden" name="strRateUnitId"	value="${drugBean.strRateUnitId }">
 --%></div>

<%-- <div id="itemManagePlusId" align="left" style="display: none;">
 <p class="subHeaders" style="margin-bottom: 0;">
<button type="button" class="btn btn-info" onClick="view1('itemManagePlusId','itemManageMinusId','itemManageDtlId');" style="cursor: pointer;">
<i class="fas fa-plus iround"></i>&nbsp;Drug Managed By</button>
</p>
</div>
<div id="itemManageMinusId" style="display: block;" align="left">
<p class="subHeaders" style="margin-bottom: 0;">
<button type="button" class="btn btn-info" onClick="view2('itemManagePlusId','itemManageMinusId','itemManageDtlId');" style="cursor: pointer;">
<i class="fas fa-minus iround"></i>&nbsp;Drug Managed By
</button>
</p>
</div>
<div id="itemManageDtlId">
<div class="row" >
<div class="col-sm-3">
</div>
<div class="col-sm-2">

<label>Batch No.</label>
</div>
<div class="col-sm-1">
<logic:equal value="1" property="strBatchNo" name="drugBean">
<input type="checkbox"	value="1"  name="strBatchNo" onclick="setBatchNo();" checked="checked"/>
</logic:equal>
<logic:notEqual value="1" property="strBatchNo" name="drugBean">
<input type="checkbox"	value="0"  onclick="setBatchNo();" name="strBatchNo" />
</logic:notEqual>
</div>
<div class="col-sm-2">

<label>Expiry Date</label>
</div>
<div class="col-sm-1">
<logic:equal value="1" property="strExpiryDate" name="drugBean">
<input type="checkbox"	value="1"  name="strExpiryDate" onclick="setExpiryDate();" checked="checked"/>
</logic:equal>
			
<logic:notEqual value="1" property="strExpiryDate" name="drugBean">
<input type="checkbox"	value="0" onclick="setExpiryDate();" name="strExpiryDate"  />
</logic:notEqual>
</div>
<div class="col-sm-3">
</div>
</div>

</div> --%>
<div class="row">

<p class="subHeaders" style="margin-bottom: 0;">
Drug Managed By
</p>
</div>
<div class="row">	
<div class="col-sm-4">
</div>
<div class="col-sm-2">
<label>Batch No.</label>
</div>
<div class="col-sm-2" align="right">
<logic:equal value="1" property="strBatchNo" name="drugBean">
<input type="checkbox"	value="1"  name="strBatchNo" onclick="setBatchNo();" checked="checked"/>
</logic:equal>
<logic:notEqual value="1" property="strBatchNo" name="drugBean">
<input type="checkbox"	value="0"  onclick="setBatchNo();" name="strBatchNo" />
</logic:notEqual>
</div>
<div class="col-sm-2">
<label>Expiry Date</label>
</div>
<div class="col-sm-2" align="right">
<logic:equal value="1" property="strExpiryDate" name="drugBean">
<input type="checkbox"	value="1"  name="strExpiryDate" onclick="setExpiryDate();" checked="checked"/>
</logic:equal>
			
<logic:notEqual value="1" property="strExpiryDate" name="drugBean">
<input type="checkbox"	value="0" onclick="setExpiryDate();" name="strExpiryDate"  />
</logic:notEqual>
</div>
</div>


<div class="row" align="left">	
<p class="subHeaders" style="margin-bottom: 0;">
Drug Parameter
</div>

<div class="row">
<div class="col-sm-2">

<label><font color="red">*</font>Approved Type</label>
</div>
<div class="col-sm-2">
<select	name="strApprovedType" class="form-control">
<bean:write name="drugBean" property="strApprovedTypeOptions"	filter="false" />
</select>
</div>
<div class="col-sm-2">

<label><font color="red">*</font>Drug Make</label>
</div>
<div class="col-sm-2">
<html:select  style="width: 100%; padding: 0.375rem 0.75rem;border: 1px solid #ced4da;border-radius: 0.25rem;"
property="strItemMake" name="drugBean">
				<html:option value="1">Indian</html:option>
				<html:option value="2">Foriegn</html:option>
</html:select>
</div>
<div class="col-sm-2">
<label><font color="red">*</font>QC Type
</label>
</div><div class="col-sm-2">
<html:select style="width: 100%; padding: 0.375rem 0.75rem;border: 1px solid #ced4da;border-radius: 0.25rem;"
property="strQCType" name="drugBean">
				<html:option value="0">Non-Mandatory</html:option>
				<html:option value="1">Mandatory</html:option>
				<html:option value="2">Desirable</html:option> 
</html:select>
</div>

</div>

<div class="row" style="margin-top: 10px">
<!-- <div class="col-sm-3">

<label>Whether Issue Rate Is Configurable</label>
</font>
</div>
<div class="col-sm-1">
<input type="checkbox" name="strChkFlg" value="0" onClick="showHideRate(this);">
</div> -->	
<div class="col-sm-2">
<label><font color="red">*</font>Drug Class
</label>
</div>
<div class="col-sm-2">
<logic:equal value="1" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="form-control">
				<option value="0">Select Value</option>
				<option value="1" selected="selected">Consumables</option>
				<option value="2">Drugs</option>
				<option value="3">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>
			
			<logic:equal value="0" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="form-control">
				<option value="0" selected="selected">Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2">Drugs</option>
				<option value="3">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>

            <logic:equal value="2" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="form-control">
				<option value="0" >Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2" selected="selected">Drugs</option>
				<option value="3">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>
            
            <logic:equal value="3" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="form-control">
				<option value="0" >Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2" >Drugs</option>
				<option value="3" selected="selected">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>
			
			<logic:equal value="4" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="form-control">
				<option value="0" >Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2" >Drugs</option>
				<option value="3">Misc.</option>
				<option value="4" selected="selected">Sutures</option>
			</select>
			</logic:equal>
			
</div>
<div class="col-sm-2" > <!-- id="issueRate" style="display:none;" -->

<label>Issue Rate</label>
</div>
<div class="col-sm-2">
<input type='text' name="strConfigIssueRate" value="${drugBean.strConfigIssueRate}" 
onkeypress="return validateData(event,7);" maxlength="5"  placeholder="% of Purchase Rate" class='form-control'>			  
</div>
<div class="col-sm-3">

<label>Is Misc</label>
</div>
<div class="col-sm-1" align="right">
<logic:equal value="1" property="strIsMisc" name="drugBean">
<input type="checkbox"	value="1"  name="strIsMisc"  onclick="setIsMisc();" checked="checked"/>
</logic:equal>
			
<logic:notEqual value="1" property="strIsMisc" name="drugBean">
<input type="checkbox"	value="0"   onclick="setIsMisc();" name="strIsMisc" />
</logic:notEqual>
</div>	
</div>
<div class="row" style="margin-top: 10px">
<div class="col-sm-3">

<label>Whether Drug is Sachet</label>
</div>
<div class="col-sm-1" align="right">
<logic:equal value="1" property="strIsItemSachet" name="drugBean">
<input type="checkbox"	value="1"  name="strIsItemSachet"  onclick="setIsItemSachet();" checked="checked"/>
</logic:equal>
			
<logic:notEqual value="1" property="strIsItemSachet" name="drugBean">
<input type="checkbox"	value="0"   onclick="setIsItemSachet();" name="strIsItemSachet" />
</logic:notEqual>
</div>
<div class="col-sm-3" >
<label>Whether Drug is Non-Quantifiable</label>
</div>
<div class="col-sm-1" align="right">
<logic:equal value="1" property="strIsQuantifiable" name="drugBean">
<input type="checkbox"	value="1"   onclick="setIsQuantifiable();" name="strIsQuantifiable" checked="checked"/>
</logic:equal>
			
<logic:notEqual value="1" property="strIsQuantifiable" name="drugBean">
<input type="checkbox"	value="0"    onclick="setIsQuantifiable();" name="strIsQuantifiable" />
</logic:notEqual>
</div>
<div class="col-sm-3">
<label>Whether Drug is EDL Category</label>
</div>
<div class="col-sm-1" align="right">
<logic:equal value="1" property="strEdlCat" name="drugBean">
<input type="checkbox"	value="1"   onclick="setIsQuantifiable();" name="strEdlCat" checked="checked"/>
</logic:equal>
			
<logic:notEqual value="1" property="strEdlCat" name="drugBean">
<input type="checkbox"	value="0"    onclick="setIsQuantifiable();" name="strEdlCat" />
</logic:notEqual>
</div>
</div>

<div class="row" style="margin-top: 10px">
<div class="col-sm-2" >
<label>Record Status</label>
</div>
<div class="col-sm-2" align="right">

<html:radio	name="drugBean" property="strIsValid" value="1">Active</html:radio>
<html:radio name="drugBean" property="strIsValid" value="2">&nbsp;&nbsp;Inactive</html:radio>
</div>
<div class="col-sm-2">
<div id="specMandatoryDivId" style="display: block;">

<label>
<font color="red">*</font>
Specification</label>
</div>
<div id="specNonMandatoryDivId" style="display: none;">

<label>
<font color="red">*</font>
Specification</label>
</div>
</div>
<div class="col-sm-6">
<textarea class="form-control" name="strSpecification" rows="2" cols="16">
<bean:write name="drugBean" property="strSpecification" filter="false" />
</textarea>
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
	
	
	
	
	
	
	
	
	
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">Drug Master &gt;&gt; Modify</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Category</td>
			<td width="25%" class="CONTROL">Drug Category</td>


			<td width="25%" class="LABEL">Group Name</td>
			<td width="25%" class="CONTROL"><bean:write name="drugBean"
				property="strGroupName" filter="false" /></td>

		</tr>

		<tr>
			<td colspan="2" class="LABEL">Generic Drug Name</td>
			<td colspan="2" class="CONTROL"><bean:write
				name="drugBean" property="strGenericItemName" filter="false" /></td>

		</tr>
	</table> --%>
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td colspan="2" class="LABEL">
			<div id='cpaCodeId'><font color="red">*</font>Drug Code</div>
			</td>
			<td colspan="2" width="50%" class="CONTROL"><logic:equal
				value="0" name="drugBean" property="strPrevCPACode">
				<input type="text" name="strNewCPACode" maxlength="20"
					value="${drugBean.strNewCPACode}"
					onkeypress="return validateData(event,9);" class="txtFldMax">
			</logic:equal> <logic:notEqual value="0" name="drugBean" property="strPrevCPACode">
				<bean:write name="drugBean" property="strPrevCPACode" filter="false" />.<input
					type="text" name="strNewCPACode" maxlength="20"
					value="${drugBean.strNewCPACode}"
					onkeypress="return validateData(event,9);" class="txtFldMax">
			</logic:notEqual></td>

		</tr>
	</table> --%>
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Drug
			Type</td>
			<td width="50%" class="CONTROL"><select name="strItemType"
				class="comboNormal">
				<bean:write name="drugBean" property="strItemTypeValues"
					filter="false" />

			</select></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Drug
			Name</td>
			<td width="50%" class="CONTROL"><input type="text"
				name="strDrugName" maxlength="250" value="${drugBean.strDrugName}"
				class="txtFldMax" style="width: 300px"
				onkeypress="return validateDataWithSpecialChars(event,18,'%.:;+-/*,½');" />
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			<div id="manfMandatoryDivId" style="display: block;">Manufacturer</div>
			<div id="manfNonMandatoryDivId" style="display: none;">Manufacturer</div>
			</td>
			<td width="50%" class="CONTROL"><select name="strManufacturerId"
				class="comboNormal">
				<bean:write name="drugBean" property="strManufacturerVal"
					filter="false" />

			</select></td>
		</tr>
		
		<tr>
			<td width="50%" class="LABEL">HSN Code</td>
			<td width="50%" class="CONTROL">
			<input type="text"	name="strHSNCode" maxlength="10" value="${drugBean.strHSNCode}" class="txtFld" style="width: 100px"	onkeypress="return validateData(event,7);" />
			</td>
		</tr>
	</table> --%>
	<%-- <div ><table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td colspan="1" width="25%" class="LABEL">Default Rate</td>
			<td colspan="1" width="25%" class="CONTROL"><input type="text"
				name="strDefaultRate" value="${drugBean.strDefaultRate}"
				class="txtFldNormal" maxlength="11"
				onkeypress="return validateData(event,7);" /></td>
			<td colspan="1" width="25%" class="LABEL"><font color="red">*</font>Rate/Unit
			</td>
			<td colspan="1" width="25%" class="CONTROL"><select
				name="strRateUnitId" class="comboNormal">
				<bean:write name="drugBean" property="strUnitValues" filter="false" />
			</select></td>
		</tr>


	</table></div> --%>
	
	
	<!-- Added by vikrant after discussion with priyesh sir -->

    <%--  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td colspan="4" class="TITLE" width="25%">
			<div id="itemManagePlusId" align="left" style="display: none;">
			<img src="../../hisglobal/images/plus.gif"	onClick="view1('itemManagePlusId','itemManageMinusId','itemManageDtlId');"	style="cursor: pointer; " />Drug Managed By</div>
			<div id="itemManageMinusId" style="display: block;" align="left">
			<img src="../../hisglobal/images/minus.gif"	onClick="view2('itemManagePlusId','itemManageMinusId','itemManageDtlId');" style="cursor: pointer; " /> Drug Managed By</div>
			</td>
		</tr>
	  </table>
	
	<div id="itemManageDtlId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"	cellpadding="0px">
		<tr>
			<td class="LABEL" colspan="1" width="25%">Batch No.</td>
			<td class="CONTROL" colspan="" width="25%">
			
			<logic:equal value="1" property="strBatchNo" name="drugBean">
			<input type="checkbox"	value="1"  name="strBatchNo" onclick="setBatchNo();" checked="checked"/>
			</logic:equal>
			
			<logic:notEqual value="1" property="strBatchNo" name="drugBean">
			<input type="checkbox"	value="0"  onclick="setBatchNo();" name="strBatchNo" />
			</logic:notEqual>
			
			</td>
			
			<td class="LABEL" width="25%">Expiry Date</td>
			<td class="CONTROL" width="25%">
			
			<logic:equal value="1" property="strExpiryDate" name="drugBean">
			<input type="checkbox"	value="1"  name="strExpiryDate" onclick="setExpiryDate();" checked="checked"/>
			</logic:equal>
			
			<logic:notEqual value="1" property="strExpiryDate" name="drugBean">
			<input type="checkbox"	value="0" onclick="setExpiryDate();" name="strExpiryDate"  />
			</logic:notEqual>
			
			</td>
		</tr>
	 </table>
	 </div> --%>
	




	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="0px">

<!-- 
		<tr>
			<td colspan="4" class="TITLE" width="25%">Drug Parameter</td>
		</tr> -->


		<%-- <tr>
		
		<td class="LABEL" colspan="1" width="25%"><font color="red">*</font>Approved Type</td>
		<td class="CONTROL" colspan="1" width="25%">
		<select	name="strApprovedType" class="comboNormal">
		<bean:write name="drugBean" property="strApprovedTypeOptions"	filter="false" />
		</select>
		</td>
			
		
		<td class="LABEL" colspan="1" width="25%"><font color="red">*</font>Drug Make</td>
		<td class="CONTROL" width="25%">
		<html:select property="strItemMake" name="drugBean">
				<html:option value="1">Indian</html:option>
				<html:option value="2">Foriegn</html:option>
		</html:select>
		</td>
			
		<!-- Commented By Vikrant As Per Review Points For Drug Master To Hide Issue Type,Qc Code etc. -->	
		 <td class="LABEL" colspan="1" width="25%"><font color="red">*</font>Issue Type</td>
			<td class="CONTROL" colspan="1" width="25%">
				<html:select property="strIssueType" name="drugBean">
					<bean:write name="drugBean" property="strIssueTypeComboOptions" filter="false" />
				</html:select>
		</td>
		</tr> --%>
		<%-- <tr>
		
		<!-- Commented By Vikrant As Per Review Points For Drug Master To Hide Issue Type,Qc Code etc. -->	
		 
		<td class="LABEL" colspan="1" width="25%"><font color="red">*</font>Drug Make</td>
		<td class="CONTROL" width="25%">
		<html:select property="strItemMake" name="drugBean">
				<html:option value="1">Indian</html:option>
				<html:option value="2">Foriegn</html:option>
		</html:select>
		</td>
		
			
		<td class="LABEL" colspan="1" width="25%"></td>
			<td class="CONTROL" colspan="1" width="25%">
		</td>	
			
		<!-- Commented By Vikrant As Per Review Points For Drug Master To Hide Issue Type,Qc Code etc. -->		
		
		 <td class="LABEL" colspan="1" width="25%"><font color="red">*</font>QC Type</td>
		<td class="CONTROL" width="25%">
		<html:select property="strQCType" name="drugBean">
				<html:option value="0">Non-Mandatory</html:option>
				<html:option value="1">Mandatory</html:option>
				<html:option value="2">Desirable</html:option> 
		</html:select>
		</td>
			
		</tr> --%>
		
		
		<!-- Commented By Vikrant As Per Review Points For Drug Master To Hide Issue Type,Qc Code etc. -->	
		 
		<%-- <tr>
		
		<td class="LABEL" colspan="1" width="25%">Whether Drug is Sachet
		</td>
		<td class="CONTROL" colspan="1" width="25%">
		<input	type="checkbox" name="strIsItemSachet"	value="${drugBean.strIsItemSachet }" onclick="setIsItemSachet();">
		</td>
		
		<td class="LABEL" colspan="1" width="25%">Whether Drug is Quantifiable</td>
		<td class="CONTROL" colspan="1" width="25%">
		<input	type="checkbox" name="strIsQuantifiable" value="${drugBean.strIsQuantifiable }"	onclick="setIsQuantifiable();">
		</td>
		
		</tr> --%>
		

	
			<%-- <tr id="issueRate" style="display:none;">
				   <td class="LABEL" width="25%" colspan="1">Issue Rate</td>
				    <td class="CONTROL" width="25%" colspan="1">
				  
				    <input type='text' name="strConfigIssueRate" value="${drugBean.strConfigIssueRate}" onkeypress="return validateData(event,7);" maxlength="5" class='txtFldNormal'><b>% of Purchase Rate</b>
				  
				 	</td>			  
				 	<td class="LABEL" width="25%" colspan="1"></td>	
				 	<td class="CONTROL" width="25%" colspan="1"></td>	
			</tr>	 --%>
			
	    <%--  <tr>
		<td class="LABEL" colspan="1" width="25%">Drug Class</td>
			<td class="CONTROL" colspan="1" width="25%"  >
		
			<logic:equal value="1" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="comboNormal">
				<option value="0">Select Value</option>
				<option value="1" selected="selected">Consumables</option>
				<option value="2">Drugs</option>
				<option value="3">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>
			
			<logic:equal value="0" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="comboNormal">
				<option value="0" selected="selected">Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2">Drugs</option>
				<option value="3">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>

            <logic:equal value="2" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="comboNormal">
				<option value="0" >Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2" selected="selected">Drugs</option>
				<option value="3">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>
            
            <logic:equal value="3" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="comboNormal">
				<option value="0" >Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2" >Drugs</option>
				<option value="3" selected="selected">Misc.</option>
				<option value="4">Sutures</option>
			</select>
			</logic:equal>
			
			<logic:equal value="4" property="strDrugClass" name="drugBean">
			<select	name="strDrugClass" class="comboNormal">
				<option value="0" >Select Value</option>
				<option value="1" >Consumables</option>
				<option value="2" >Drugs</option>
				<option value="3">Misc.</option>
				<option value="4" selected="selected">Sutures</option>
			</select>
			</logic:equal>
			
                        			

		   </td>
		<td class="LABEL" colspan="1" width="25%"  >Is Misc</td>
			<td class="CONTROL" colspan="1" width="25%"  >
			
			<logic:equal value="1" property="strIsMisc" name="drugBean">
			<input type="checkbox"	value="1"  name="strIsMisc"  onclick="setIsMisc();" checked="checked"/>
			</logic:equal>
			
			<logic:notEqual value="1" property="strIsMisc" name="drugBean">
			<input type="checkbox"	value="0"   onclick="setIsMisc();" name="strIsMisc" />
			</logic:notEqual> </td>
		</tr>	 --%>
			
			<%-- <tr style="display:none;">
					<td class="LABEL" width="25%" colspan="1" >Market Price</td>
				    <td class="CONTROL" width="20%" colspan="1" >			  
				    	<input type='text' name="strMktRate" value="${drugBean.strMktRate}"   onkeypress="return(numericWithTwoDecimalPlaces(this,',','.',event));" >
				    					   <b>/</b>
				 	</td>
				 	
				 	<td class="CONTROL" width="55%" colspan="2" >
				 		<div id="unitDivId">			  
					    	<select name="strMktRateUnitId" >
					    		<bean:write name="drugBean" property="strMktRateUnitIdValues" filter="false" />
					    	</select>				    				
				    	</div>				  
				 	</td>  
			</tr>	 --%>
			
			
			<%-- <tr>
			<td class="LABEL" colspan="1" width="25%" >Whether Drug is Sachet</td>
			<td class="CONTROL" colspan="1" width="25%">
			
			
			<logic:equal value="1" property="strIsItemSachet" name="drugBean">
			<input type="checkbox"	value="1"  name="strIsItemSachet"  onclick="setIsItemSachet();" checked="checked"/>
			</logic:equal>
			
			<logic:notEqual value="1" property="strIsItemSachet" name="drugBean">
			<input type="checkbox"	value="0"   onclick="setIsItemSachet();" name="strIsItemSachet" />
			</logic:notEqual>
			
			</td>
			
			<td class="LABEL" colspan="1" width="25%"  >Whether Drug is Non-Quantifiable</td>
			<td class="CONTROL" colspan="1" width="25%"  >
			
		    <logic:equal value="1" property="strIsQuantifiable" name="drugBean">
			<input type="checkbox"	value="1"   onclick="setIsQuantifiable();" name="strIsQuantifiable" checked="checked"/>
			</logic:equal>
			
			<logic:notEqual value="1" property="strIsQuantifiable" name="drugBean">
			<input type="checkbox"	value="0"    onclick="setIsQuantifiable();" name="strIsQuantifiable" />
			</logic:notEqual>
			
			
			</td>
			</tr> --%>
			
			<%-- <tr>
			<td class="LABEL" colspan="1" width="25%" >Whether Drug is EDL Category</td>
			<td class="CONTROL" colspan="1" width="25%">
			
			<logic:equal value="1" property="strEdlCat" name="drugBean">
			<input type="checkbox"	value="1"   onclick="setIsQuantifiable();" name="strEdlCat" checked="checked"/>
			</logic:equal>
			
			<logic:notEqual value="1" property="strEdlCat" name="drugBean">
			<input type="checkbox"	value="0"    onclick="setIsQuantifiable();" name="strEdlCat" />
			</logic:notEqual>
			
			
			</td>
			</tr>
			 --%>
	</table>
	
	<%-- <table cellspacing="1px" cellpadding="1px" class="TABLEWIDTH"
		align="center">
		<tr>
			<td class="LABEL" colspan="1" width="25%">

			<div id="specMandatoryDivId" style="display: block;"><font
				color="red">*</font>Specification</div>
			<div id="specNonMandatoryDivId" style="display: none;">Specification</div>
			</td>
			<td class="CONTROL" width="25%" colspan="1"><textarea
				name="strSpecification" rows="2"><bean:write
				name="drugBean" property="strSpecification" filter="false" /></textarea></td>
			<td class="LABEL" colspan="1" width="25%" style="display : none;"><font color="red">*</font>Effective From</td>
			<td class="CONTROL" width="25%" colspan="1" style="display : none;"><bean:write name="drugBean" property="strEffectiveFrom"/>
			</td>
		</tr>

		<tr>
			<td colspan="2" width="45%" class="LABEL">Record Status</td>
			<td colspan="2" width="45%" class="CONTROL"><html:radio
				name="drugBean" property="strIsValid" value="1">Active</html:radio>
			<html:radio name="drugBean" property="strIsValid" value="2">Inactive</html:radio>

			</td>
		</tr>

	</table> --%>
	<!-- <table cellspacing="1px" cellpadding="1px" class="TABLEWIDTH"
		align="center">
		<tr class="FOOTER">
			<td colspan="4" width="25%"><font size="2" color="red">*</font>
			Mandatory Fields</td>
		</tr>
	</table> -->
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">

		<!-- <tr>

			<td align="center" colspan="2" width="25%"><img
				src="../../hisglobal/images/btn-sv.png" title="Save Record"
				onClick="return validate();" style="cursor: pointer;" /> <img
				src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');"
				style="cursor: pointer;" title="Cancel Process"></td>
		</tr> -->
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" value="${drugBean.strCurrentDate}"
		name="strCurrentDate" />
	<input type="hidden" value="${drugBean.strGroupId}" name="strGroupId" />
	<input type="hidden" value="${drugBean.strTempCmbValue}"
		name="comboValue" />
	<input type="hidden" value="${drugBean.strGenericItemId}"
		name="strGenericItemId" />
	<input type="hidden" value="${drugBean.strDrugName}" name="strDrugName" />
	<input type="hidden" value="${drugBean.strGroupName}"
		name="strGroupName" />
	<input type="hidden" value="${drugBean.strGenericItemName}"
		name="strGenericItemName" />
	<input type="hidden" value="${drugBean.strChk}" name="chk" />

	<input type="hidden" value="${drugBean.strIsItemCodeRequired}"
		name="strIsItemCodeRequired" />

	<input type="hidden" value="" name="strCPACode" />
	<input type="hidden" value="${drugBean.strPrevCPACode}"
		name="strPrevCPACode" />
	<input type="hidden" value="${drugBean.strDrugType}" name="strDrugType" />
	<input type="hidden"
				name="strEffectiveFrom" value="${drugBean.strEffectiveFrom}">
	<cmbPers:cmbPers />
</div>
</fieldset>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>