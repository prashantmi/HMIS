<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/trfMultirow.tld" prefix="trfMrow"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Package Service Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.redMultiControl
{
	 
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-style: normal;
	font-weight: normal;
	color: #FF3300;
	background-color: #F1ECE2;
	height: 16px;
	text-align:center;

}
</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="Javascript" src="../../js/billing.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>

<script type="text/javascript">

function isGroupCombo(obj)
{
	var grpId = obj.value;
 	var mode="TARIFFCOMBO";
	var url="CNTpackservMst.cnt?hmode="+mode+"&grpId="+grpId;
	ajaxFunction(url,"2");
}

function getTariffDtls(){

	document.forms[0].groupName.focus();
	var mode="TARIFFDETAIL";
	var url="CNTpackservMst.cnt?hmode="+mode;
	ajaxFunction(url,"1");
	document.getElementById("normalMsg").style.display="block";
		//document.getElementById("normalMsg").innerHTML="";
	document.getElementById("normalMsg").innerHTML=document.forms[0].strNormalMsg.value;
}

var gblIndex = "";

 function getUnitDtls(cmbObj , index){
 
 	gblIndex = index;
 
 	var unitId = cmbObj.value.split('^')[1];

 	var mode="BASEUNITVAL";
	var url="CNTpackservMst.cnt?hmode="+mode+"&unitId="+unitId;
	ajaxFunction(url,"3");
	document.getElementById("normalMsg").style.display="block";
 
 }

function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{
		//alert("Normal Message------>"+document.forms[0].strNormalMsg.value);
		
		document.getElementById("normalMsg").style.display="block";
		//document.getElementById("normalMsg").innerHTML="";
		document.getElementById("normalMsg").innerHTML=document.forms[0].strNormalMsg.value;
		var objEle=document.getElementById("tariffId");
		// alert("res--"+res);
		objEle.innerHTML=res;
		
		
		
	}
	if(mode=="2")
	{
		var objEle=document.getElementById("trfcmb#delIndex#");
						
		objEle.innerHTML="<select name='strMultiTariffId' onchange='getUnitDtls(this , \"#delIndex#\");' id='tariffname#delIndex#' class='comboMax'>"+res+"</select>";
	}
	
	if(mode == "3"){
	
		document.getElementById("trfUnitDivId"+gblIndex).innerHTML = "<select name='strMultiUnitId' id='unitname"+gblIndex+"'  class='comboNormal' >"+res+"</select>";
	}
	
}

function Showlayer1()
{
  var obj=document.getElementById("plus");
  obj.style.display="none";
  var obj=document.getElementById("minus");
  obj.style.display="block";
   var obj=document.getElementById("tariffId");
  obj.style.display="block";
}

function Showlayer2()
{
  var obj=document.getElementById("plus");
  obj.style.display="block";
  var obj=document.getElementById("minus");
  obj.style.display="none";
  var obj=document.getElementById("tariffId");
  obj.style.display="none";
}
function CheckDataExistsByCmbText(dataName, multiCompName,userMessage){
		
		if(userMessage == '') userMessage = "Data Already Exists";

			var dataVal = document.getElementsByName(dataName);
			var dataLen = dataVal.length;

		if(dataLen != 0){

			var multVal = document.getElementsByName(multiCompName);
			var multLen = multVal.length - 1;

			//alert("multLen-"+multLen);
			//alert("dataLen-"+dataLen);
			for(i=0; i<dataLen; i++){

				for(j=0; j<multLen; j++){

					// alert("dataVal[i].value-"+dataVal[i].value); 
					// alert("multVal[j].value-"+multVal[j][multVal[j].selectedIndex].text);
					if(dataVal[i].value == multVal[j][multVal[j].selectedIndex].text){

							alert(userMessage);
							multVal[j].focus();

							return false;
					}
				}
			}
		}

		return true;
	}
function submitData(mode){

	var hisValidator = new HISValidator("packservBean");
	var retVal = checkMultirow('strMultiTariffId','Same Tariff Name cannot be Entered ');
		
		if(retVal){
		
		retVal = CheckDataExistsByCmbText('tariffName','strMultiTariffId','Tariff Name Already Exists');  
	 	
		}
		
		if(retVal){
		hisValidator.addValidation("strMultiTariffId","dontselect=0","Please select a value from Tariff Name Combo");
	 	 hisValidator.addValidation("strMultiQty", "req", "Quantity is a Mandatory Field" );
    	 hisValidator.addValidation("strMultiUnitId","dontselect=0","Please select a value from Unit Combo");
    	 hisValidator.addValidation("effectiveFrm", "req", "Effective From Date is a Mandatory Field" ); 
		  hisValidator.addValidation("effectiveFrm","dtgtet="+"${packservBean.strCtDate}","Effective From Date must be Greater Than Or Equal To Current Date");
		hisValidator.addValidation("remarks","maxlen=50","Remarks should not be greater than 50 characters");
	
	 retVal = hisValidator.validate();
	
	}
	if(retVal){	
		if(document.forms[0].strMultiQty.value=="0")
		{
			alert("Quantity cannot be zero");
			retVal = false;
		}
	}
	if(retVal){	
	var arr = document.getElementsByName("strMultiTariffId");
		var multirowLen = arr.length-1;
		if(multirowLen>1)
		{
		for(i=0; i< multirowLen-1 ; i++){
			for(j=multirowLen-1; j>i; j--){
			//alert(document.forms[0].strMultiTariffId[j].value);
			//alert(document.forms[0].strMultiTariffId[i].value);
			if(document.forms[0].strMultiTariffId[j].value == document.forms[0].strMultiTariffId[i].value)
			{
				alert("Selected Tariff Name can not be same");
				retVal = false;
			 	break;
			 }
		 }	
		}	
	 }
	}
	if(retVal){	
		document.forms[0].hmode.value="SAVE";
	 	document.forms[0].submit();
	}else{		
		return false;
	}	
}

function addMultirow()
{
	var retVal;
	var hisValidator = new HISValidator("packservBean");
	 hisValidator.addValidation("groupName","dontselect=0","First Select the Group Name");
	 retVal = hisValidator.validate();
	if(retVal){
		//alert("Group Name-->"+document.forms[0].groupName.value);
		addRows(new Array('tariffname','quantity','unitname'),new Array('s','t','s'),'1','1','R');
		document.getElementById("id1").style.display="block";
	}else{
	    document.getElementById("id1").style.display="none";
		return false;
	}
}
</script>

</head>
<!--<body onLoad="checkIsValid();">-->

<body onLoad="getTariffDtls();">

<html:form name="packservBean"
	action="/masters/CNTpackservMst.cnt"
	type="billing.masters.VOpackservMst">

		<div class="errMsg"><bean:write name="packservBean" property="errmsg"  filter="false"/></div>
	<div class="warningMsg"><bean:write name="packservBean" property="warningMsg" filter="false"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="packservBean" property="strNormalMsg" filter="false"/></div>
	
	
	<tag:tab tabLabel="Add Page" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
    
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td class="HEADER" colspan="2">Package Service Master&gt;&gt;add</td>
		</tr>
		<tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center">


		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Package Name</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="packageId"><bean:write
				name="packservBean" property="packageName" filter="false" /></div>
			</td>
		</tr>

		




	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="TITLE">
			<td class="TITLE" colspan="2">
			<div id="plus" style="display: none"><img
				src="../../hisglobal/images/plus.gif" align="left"
				onClick="Showlayer1();"></div>
			<div id="minus" style="display: block"><img
				src="../../hisglobal/images/minus.gif" align="left"
				onClick="Showlayer2();"></div>
			Added Tariff Details</td>
		</tr>

	</table>
	<div id="tariffId"></div>



	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="TITLE"><td><div align="right" id="groupId">Group Name   
		 <select name="groupName" 
				  onchange="isGroupCombo(this);" class='comboNormal'>
				<bean:write name="packservBean" property="packgrpModuleCombo"
					filter="false" />
			</select> </div></td>
		</tr>
		</table>
		<table class="TABLEWIDTH" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="TITLE">
			<td align="left" width="95%">New Tariff Details</td>
			
			<td width="5%"><div align="center"><img align="middle" src="../../hisglobal/images/plus.gif"
				onClick="addMultirow();" ></div>
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="40%">
			<div align="CENTER"><font color="red">*</font>Tariff Name</div>
			</td>
			<td class="LABEL" width="18%">
			<div align="CENTER"><font color="red">*</font>Quantity</div>
			</td>
			<td class="LABEL" width="42%">
			<div align="CENTER"><font color="red">*</font>Unit</div>
			</td>

		</tr>
	</table>

	
	
			<div id='id1' style="display: none"></div>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">

		<tr>
			<td class="LABEL">
			<div align="right"><font color="red">*</font>Effective From</div>
			</td>
			<td class="LABEL">
			<div align="left"><dateTag:date name="effectiveFrm"
				value="${packservBean.strCtDate}" /></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Remarks</td>
			<td class="CONTROL" width="50%"><textarea name="remarks" rows="2"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>


	<div align="center"><img style="cursor:pointer;cursor:hand" 
		src="../../hisglobal/images/btn-sv.png" title="Save Record"
		onClick="submitData('SAVE');" /> 
		<img style="cursor:pointer;cursor:hand" title="Reset Content" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset(),document.forms[0].effectiveFrm.focus();" />
		<img style="cursor:pointer;cursor:hand" 
		src="../../hisglobal/images/btn-ccl.png" title="Cancel Process" onClick="cancel('LIST');">
	</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="isGrpFlag" value="0" />
	<input type="hidden" name="strNormalMsg" value="${packservBean.strNormalMsg}" />
	<cmbPers:cmbPers/>
</html:form>

<jsp:include page="addmultirow_PackageMst_bill.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
