<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset=utf-8>
<title>Tariff Master Modify Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<style type="text/css">@import url(../../hisglobal/css/calendar-tas.css);</style>

<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>

<script language="JavaScript">
var grpArray = new Array();
var serArray = new Array();

function changeToPackage()
{
	var serviceCmb1,lobj,grpLen=0,serLen=0;	
	if(document.forms[0].isPackage.checked)
	{
		document.forms[0].isPackage.value="1";
		document.getElementById("divId1").style.display="block";				
		var i = 0, count =0;
		var robj,len;		
		lobj = document.getElementsByName("grpId");
		robj = document.getElementsByName("grpId");		
		len = lobj[0].length;
		if(lobj.length > 0)		//list box exists
		{						
			for(i=0;i<lobj[0].length;i++)
			{
				grpArray[grpLen++] = lobj[0].options[i].value;
				grpArray[grpLen++] = lobj[0].options[i].text;
				if(i==0)
				{
					robj[0].options[count].text=lobj[0].options[i].text;					
					robj[0].options[count].value=lobj[0].options[i].value;
					count++;
				}
				if(lobj[0].options[i].value.split("#")[1] == "1")
				{	
					robj[0].options[count].text=lobj[0].options[i].text;					
					robj[0].options[count].value=lobj[0].options[i].value;
					count++;
				}
			}			
			for(i=count;i<=len;i++)
			{								
				robj[0].length--;
			}
			count = 0;
		}
		serviceCmb1 = document.getElementsByName("serviceName");
		var serviceCmb2 = document.getElementsByName("serviceName");
		var serLen1 = serviceCmb1[0].length;		
		if(serviceCmb1.length>0)
		{
			for(var j=0;j<serviceCmb1[0].length;j++)
			{
				serArray[serLen++] = serviceCmb1[0].options[j].value;
				serArray[serLen++] = serviceCmb1[0].options[j].text;
				if(j==0)
				{
				  serviceCmb2[0].options[count].text=serviceCmb1[0].options[j].text;					
				  serviceCmb2[0].options[count].value=serviceCmb1[0].options[j].value;
					count++;
				}				
				if(serviceCmb1[0].options[j].value.split("#")[2] == "2")
				{
					serviceCmb2[0].options[count].text = serviceCmb1[0].options[j].text;
					serviceCmb2[0].options[count].value = serviceCmb1[0].options[j].value;
					count++;
				}
			}
			for(var j=count;j<serLen1;j++)
			{
				serviceCmb2[0].length--;
			}
		}
	}
	else
	{
		document.forms[0].isPackage.value="0";
		document.getElementById("divId1").style.display="none";
		grpCmb = document.getElementsByName("grpId");
		serCmb = document.getElementsByName("serviceName");		
		var serOpt = document.forms[0].serviceName.options;
		var len1 = document.forms[0].serviceName.options.length;
		var grpOpt = document.forms[0].groupId.options;
		var len2 = document.forms[0].groupId.options.length;
		if(len1>0)
		{
			while(len1>0)
			{
				serCmb[0].length--;
				len1--;
			}
		}
		
		if(len2>0)
		{
			while(len2>0)
			{
				grpCmb[0].length--;
				len2--;
			}
		}
		for(var y=0;y<serArray.length;y+=2)
		{			
			serOpt.add(new Option(serArray[y+1],serArray[y]),len1+1);
		}
		for(var x=0;x<grpArray.length;x+=2)
		{
			grpOpt.add(new Option(grpArray[x+1],grpArray[x]),len2+1);
		}		
	}
	document.forms[0].groupId.focus();
	
}


function checkCompCharges(){
	
	var chkobj = document.forms[0].strUndefinedCharges ;
	
		if(chkobj.checked){
		
			document.forms[0].strIsDefaultForIpd.value=0;
			document.forms[0].strIsDefaultForIpd.disabled = true;
			
		}else{
		
			document.forms[0].strIsDefaultForIpd.disabled = false;
		
		}
	
	}


function checkUpdateMode(obj)
{ 
          if(obj.checked)
	      {
	        obj.value = 1;
           	obj.checked = true;
           
	      }
	      else
	      {
	         obj.value = 0;
           	 obj.checked = false;
	       	
  	      }
  	     

}

function tariffCombo()
{	
	if(document.getElementsByName("strIsEstimationFlag")[0].value == 1 )
	{
		  document.getElementsByName("strServiceId")[0].disabled=true;
		  document.getElementsByName("tariffName")[0].disabled=true;
	}
	else
	{
		 
		  document.getElementsByName("strServiceId")[0].disabled=false;
		  document.getElementsByName("tariffName")[0].disabled=false;
    }
	
	//alert("In tariffCombo of Modify page");
	var objVal1 = document.getElementById("tariffType");
	if(document.forms[0].strCostType.value=='0')
	{
	  objVal1.innerHTML = "<select name='strCostType' class='comboNormal'><option value='0'>Select</option><option value='1'>Hospital Cost</option><option value='2'>Third Party Cost</option><option value='3'>Others</option></select>"; 
	}
	else
	{
	  if(document.forms[0].strCostType.value=='1')
	  {
	     objVal1.innerHTML = "<select name='strCostType' class='comboNormal'><option value='0'>Select</option><option value='1' selected>Hospital Cost</option><option value='2'>Third Party Cost</option><option value='3'>Others</option></select>"; 
	  }
	  else
	  {
	     objVal1.innerHTML = "<select name='strCostType' class='comboNormal'><option value='0'>Select</option><option value='1'>Hospital Cost</option><option value='2' selected>Third Party Cost</option><option value='3'>Others</option></select>"; 
	  }
	}
	
	
	var temp = document.forms[0].strServiceId.value;
	// alert(document.forms[0].tariffId.value);
	var tariffId = document.forms[0].strModTariffId.value ;
	var mode="POPULATEDATA";
	var url="CNTTrfMst.cnt?hmode="+mode+"&service="+temp+"&tariffId="+tariffId;
	
	//alert("url : "+url);	
	if(temp!='0')
	ajaxFunction(url,"1");
}

function getGroupCmb()
{
	if(document.forms[0].isPackage.checked)
	{
		if(document.forms[0].strIsEstimationFlag.checked)
		{
			alert("Package Can Not Be Selected Because Tariff Is Already Defined As Estimation");
			return;			
		}		
	}	
	if(document.forms[0].isPackage.checked)
	{
		document.forms[0].defaultUnitCombo.value=document.getElementById("defaultUnitCombo").innerHTML ;
		document.forms[0].isPackage.value="1";
		document.getElementById("defaultUnitCombo").innerHTML = "<select tabindex='1' name='defaultUnit' class='comboNormal'>"+			
		"<option title='No.(Each)' value='1701'>No(Each)</option>"+
		"</select>"; 
	}
	else
	{
		document.forms[0].isPackage.value="0";
		document.getElementById("defaultUnitCombo").innerHTML=document.forms[0].defaultUnitCombo.value ;
	}	
	/* var mode="getAddGroupCombo";
	var url="CNTTrfMst.cnt?hmode="+mode+"&isPackage="+isPackage;
	ajaxFunction(url,"2");*/
}

function showMultiRow()
{
 	 	
 	var totalrow = document.forms[0].totalrows.value;
 
 	var mode="GETMULTIROW";
	var url="CNTTrfMst.cnt?hmode="+mode+"&totalrow="+ totalrow;
	ajaxFunction(url,"3");
 	
}

function getAjaxResponse(res,mode)
{
	if(mode==1)
	{
	var objVal = document.getElementById("tId");
	if(res.length > 0){
		objVal.innerHTML = "<select name = 'tariffName' class='comboNormal'>" + res + "</select>";
	}else{
	objVal.innerHTML = "<select name = 'tariffName' class='comboNormal'><option value='0'>Select Value</option></select>";
	}
	
	
	}
	if(mode==2)
	{
	var objVal = document.getElementById("grpId");
	objVal.innerHTML = "<select name = 'groupId' class='comboNormal'>" + res + "</select>"; 
	}
	if(mode==3)
	{
	var objVal = document.getElementById("mrow");
	objVal.innerHTML =  res ;  
	}
	
}

function getData()
{
	if(document.forms[0].defaultVal.checked)
	{
		document.getElementById("divId2").style.display="";
	}
	else
	{						
		for(var v=0;v<document.forms[0].chargeTypeId.length;v++)
		{
			document.forms[0].chargeTypeId[v].checked=false;			
			changeDefaultData(v);
		}
		document.getElementById("divId2").style.display="none";
	}
}

function changeDefaultData(index)
{
	if(document.forms[0].chargeTypeId[index].checked)
	{
		document.forms[0].rate[index].disabled=false;
		document.forms[0].actualCost[index].disabled=false;
		document.forms[0].maxDisc[index].disabled=false;
		document.forms[0].serviceTax[index].disabled=false;	
		document.forms[0].effectiveFromDfltDet[index].disabled=false;		
	}
	else
	{
		document.forms[0].rate[index].disabled=true;	
		document.forms[0].actualCost[index].disabled=true;
		document.forms[0].maxDisc[index].disabled=true;
		document.forms[0].serviceTax[index].disabled=true;	
		document.forms[0].effectiveFromDfltDet[index].disabled=true;
	}
	document.forms[0].rate[index].value="";
	document.forms[0].actualCost[index].value="";
	document.forms[0].maxDisc[index].value="";
	document.forms[0].serviceTax[index].value="";
	document.forms[0].effectiveFromDfltDet[index].value="";
}

function submitData(mode)
{


	var hisValidator = new HISValidator("localTariffBean");
	// hisValidator.addValidation("groupId","dontselect=0","Please Select Value From Group Combo");
	hisValidator.addValidation("trfPkgName","req","Tariff Name Is Mandatory");
	
	hisValidator.addValidation("strDiscountLimit","req","Discount Limit Is Mandatory Field");
	hisValidator.addValidation("strDiscountLimit","numltet=100","Discount Limit should be less than or equal to 100");
	hisValidator.addValidation("strDiscountPrivilege","req","Discount Privilege Is Mandatory Field");
	hisValidator.addValidation("strDiscountLimit","numltet=100","Discount Privilege should be less than or equal to 100");
	
	
	hisValidator.addValidation("rate","req","Default Rate Is Mandatory");
	hisValidator.addValidation("actualCost","req","Actual Cost Is Mandatory");
	hisValidator.addValidation("serviceTax","req","Service Tax Is Mandatory");
	//hisValidator.addValidation("effectiveFromContbDet","req","Effective From is Mandatory"); 
	hisValidator.addValidation("tariffCode","req","Tariff Code Is Mandatory Field");
	
	hisValidator.addValidation("trfPkgName","maxlen=100","Tariff Name should not contain greater than 50 characters" );
	hisValidator.addValidation("defaultUnit","dontselect=0","Please Select Value From Default Unit Combo");

	hisValidator.addValidation("rate","amount=8,2","Please Enter Valid Amount.");
	hisValidator.addValidation("actualCost","amount=8,2","Please Enter Valid Amount.");
	// hisValidator.addValidation("maxDisc","numltet=100","Please Select % Less Than Or Equal To 100");
	hisValidator.addValidation("serviceTax","numltet=100","Please Select % Less Than Or Equal To 100");	
	// hisValidator.addValidation("maxDisc","amount=4,1","Please Enter Valid Percentage.");
	hisValidator.addValidation("serviceTax","amount=4,1","Please Enter Valid Percentage.");
	// hisValidator.addValidation("effectiveFromDfltDet","dtgtet="+"${tariffBean.currentDate}","Please Select Effective From Date Greater Than Or Equal To Current Date.");

	hisValidator.addValidation("share","numltet=100","Please Select % Less Than Or Equal To 100");
	hisValidator.addValidation("share","amount=4,1","Please Enter Valid Percentage.");
	hisValidator.addValidation("remarks","maxlen=50","Remarks should not be greater than 50 characters");
	// hisValidator.addValidation("effectiveFromContbDet","dtgtet="+"${tariffBean.currentDate}","Please Select Effective From Date Greater Than Or Equal To Current Date.");
	var retVal = hisValidator.validate();

	if(retVal){	
	var arr = document.getElementsByName("department");
		var multirowLen = arr.length-1;
		if(multirowLen>1)
		{
		for(i=0; i< multirowLen-1 ; i++){
			for(j=multirowLen-1; j>i; j--){
			//alert(document.forms[0].strMultiTariffId[j].value);
			//alert(document.forms[0].strMultiTariffId[i].value);
			if(document.forms[0].department[j].value == document.forms[0].department[i].value)
			{
				alert("Selected Department Name can not be same");
				retVal = false;
			 	break;
			 }
		 }	
		}	
	 }
	}
	if(retVal){	
		// alert(document.getElementsByName("share").length );
		var arr = document.getElementsByName("share");
		var total = 0;
		if(arr.length>1){
			for(i=0;i<arr.length-1;i++)
			{
			total = total + parseInt(arr[i].value);
			}
			// alert("total--"+total)
			if(total>100)
			{
			 alert("total of share% can not be greater than 100%")
			 retVal = false;
			}
		}	
		}
	if(retVal){	
	
	destroyMultiRow('1');
	
		document.forms[0].strIsDefaultForIpd.disabled = false;
	    document.getElementsByName("strServiceId")[0].disabled=false;
	    document.getElementsByName("tariffName")[0].disabled=false;
	    if(document.forms[0].tariffName.value!="0")
	    document.forms[0].gstrTariffName.value=document.forms[0].tariffName.options[document.forms[0].tariffName.selectedIndex].text ;
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
	}else{		
		return false;
	}	
	
}

function activateMRow()
{
	
	var rowObj = document.getElementsByName("mIndex");
	
	if(rowObj.length > 0){
	
	document.multirow.rowIndex1.value = document.forms[0].mIndex.value;
	document.multirow.rowLength1.value = document.forms[0].mLength.value;
	}
	
}

function showHideDefaultDiv()
{
	if(document.getElementById("defaultId").style.display=="")
	{
		document.getElementById("defaultId").style.display="none";
		document.getElementById("divId2").style.display="none";
	}
	else
	{
		document.getElementById("defaultId").style.display="";
		document.getElementById("divId2").style.display="";
	}
}

function disableService()
{
	if(document.forms[0].strIsEstimationFlag.checked)
	{
		if(document.forms[0].isPackage.checked)
		{
			alert("Estimation Can Not Be Selected Because Tariff Is Already Defined As Package");
			return;
		}		
	}
	if(document.getElementsByName("strIsEstimationFlag")[0].checked == true )
	{
		  document.getElementsByName("strServiceId")[0].disabled=true;
		  document.getElementsByName("tariffName")[0].disabled=true;
	}
	else
	{
		 
		  document.getElementsByName("strServiceId")[0].disabled=false;
		  document.getElementsByName("tariffName")[0].disabled=false;
    }
}
function unitCombo()
{
	if(document.forms[0].isPackage.checked)
	{
		document.getElementById("defaultUnitCombo").innerHTML = "<select tabindex='1' name='defaultUnit' class='comboNormal'>"+			
		"<option title='No.(Each)' value='1701'>No(Each)</option>"+
		"</select>"; 
	}
}

</script> 
</head>

<body  onload=" tariffCombo() , activateMRow() , checkCompCharges();unitCombo();">
<html:form name="localTariffBean" action="masters/CNTTrfMst.cnt"
	type="billing.masters.controller.fb.LocalTariffMstFB">

	<div class="errMsg"><bean:write name="localTariffBean" property="errMsg"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="localTariffBean" property="normalMsg"/></div>
	<tag:tab tabLabel="Modify Tariff Master" width="TABLEWIDTH" align="center"></tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="2" WIDTH="100%">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Tariff Master&gt;&gt; Modify</div>
					</td>
					<td width="50%">
					<div align="right">
						<html:checkbox name="localTariffBean" property="isPackage" value="1" disabled="true" />Package
						<logic:equal value="1" property="strIsEstimationFlag" name="localTariffBean">
							<input type="checkbox" name="strIsEstimationFlag" value ="1" checked="checked" disabled="disabled"   onclick="disableService();"/>Estimation
						</logic:equal>
						<logic:equal value="0" property="strIsEstimationFlag" name="localTariffBean">
							<input type="checkbox" name="strIsEstimationFlag" value ="0"  disabled="disabled"  onclick="disableService();"/>Estimation
						</logic:equal>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font> Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="grpId">
				<html:select name="localTariffBean" property="groupId" disabled="true" >
				<bean:write name="localTariffBean" property="addGroupCombo" filter="false"/>
				</html:select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Tariff/Package Name</td>
			<td class="CONTROL">
			<input type="text" name="trfPkgName" class="txtFldBig" onkeypress="return validateData(event,16);" value="${localTariffBean.trfPkgName}" maxlength="100"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Default Unit</td>
			<td class="CONTROL">
				<div id="defaultUnitCombo">
					<html:select name="localTariffBean" property="defaultUnit">
						<bean:write name="localTariffBean" property="addDefaultUnitCombo" filter="false"/>
					</html:select>			
					<html:checkbox property="strUpdateChargeUnit" name="localTariffBean" value="1" >Whether Update in Charge Master</html:checkbox> 
				</div>
			</td>
		</tr>
		<tr>  
			<td class ="LABEL" width ="50%">Un-Defined Charges</td>
			<td class="CONTROL" width="30%">
				<html:checkbox property="strUndefinedCharges" name="localTariffBean" onchange="checkCompCharges();"  value="1"></html:checkbox> </td>
	    </tr>
	   	<tr>
			
		</tr>
	</table>
	<div id="divId1" style="display:none">
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="50%">Length of Stay</td>
			<td class="CONTROL" width="50%">
			<input type="text" name="lengthOfStay" maxlength="2" onkeypress="return validateData(event,5);"></td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td colspan="2"><div class='line'><label class='DIVLABEL'>Associated With</label></div></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Tariff Code</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMax" style="text-transform: uppercase;" name="tariffCode" maxlength="15"  value="${localTariffBean.tariffCode}" onkeypress="return validateData(event,8);"></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Service Type</td>
			<logic:equal value="1" property="strIsEstimationFlag" name="localTariffBean">
			<td class="CONTROL" width="50%">
			<html:select name="localTariffBean" property="strServiceId"  onchange="tariffCombo();" disabled="disabled">
			<bean:write name="localTariffBean" property="addServiceCombo" filter="false"/>
			</html:select></td>
			</logic:equal>
			
			<logic:equal value="0" property="strIsEstimationFlag" name="localTariffBean">
			<td class="CONTROL" width="50%">
			<html:select name="localTariffBean" property="strServiceId"  onchange="tariffCombo();">
			<bean:write name="localTariffBean" property="addServiceCombo" filter="false"/>
			</html:select></td>
			</logic:equal>

			
			
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">~</font>Mapped Service Name</td>
			<logic:equal value="1" property="strIsEstimationFlag" name="localTariffBean">
			<td class="CONTROL" width="50%">
			<div id="tId">
			<html:select name="localTariffBean" property="tariffName" styleId="tariffId" value="${localTariffBean.tariffId}" disabled="disabled">
				<html:option value="0">Select Value</html:option>
			</html:select></div></td>
			</logic:equal>
			
			<logic:equal value="0" property="strIsEstimationFlag" name="localTariffBean">
			<td class="CONTROL" width="50%">
			<div id="tId">
			<html:select name="localTariffBean" property="tariffName" styleId="tariffId" value="${localTariffBean.tariffId}">
			<html:option value="0">Select Value</html:option>
			</html:select></div></td>
			</logic:equal>
			
		</tr>		
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px" style="display: none;">
		<tr class="HEADER">
			<td colspan="2">Discount Rule</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Discount Limit</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMin" maxlength="3" name="strDiscountLimit" onkeypress="return validateData(event,5);" value="${localTariffBean.strDiscountLimit}"> %</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Discount Privilege</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMin" maxlength="3" name="strDiscountPrivilege" onkeypress="return validateData(event,5);" value="${localTariffBean.strDiscountPrivilege}"> %</td>
		</tr>
		
	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr onclick="showHideDefaultDiv()" style="cursor: pointer;">
			<td colspan="5"><div class='line'><label class='DIVLABEL'><font color="red">*  </font>Additional Information(Show/Hide)</label></div></td>
		</tr>
		<tr id='defaultId' style="display: none;">			
			<td class="multiLabel" width="20%">Hospital Service</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Default Rate</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Actual Cost</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Service tax(%)</td>
			<td class="multiLabel" width="20%"><font color="red">*</font>Visibility</td>
			
		</tr>
	</table>
	
	<div id="divId2" style="display: none;">${localTariffBean.toDefault}
	
		<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
			<tr>
				<td colspan="2" width="50%">
				<table width="100%" border="0" align="center">
					<tr>
						<td width="50%"><div class='line'><label class='DIVLABEL'>Contribution Details</label></div></td>
						<td width="50%"><div align="right" class='lineContinue2'><img src="../../hisglobal/images/plus.gif" onClick="addRows(new Array('department','share'),new Array('s','t'),'1','1','R');"></div></td>
					</tr>			
				</table>
				</td> 
			</tr>
		</table>
		<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
			<tr>
				<td class="multiLabel" width="55%">Department</td>
				<td class="multiLabel" width="35%">Share(%)</td>				
				<td class="multiLabel" width="10%">&nbsp;</td>
			</tr>
		</table>
		
		<div id="id1">
			<bean:write name="localTariffBean" property="strMultiRow" filter="false"/>
		</div>
		
		<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
			<tr class="TITLE"><td  colspan="2" ></td></tr>
			<tr>  
					<td class ="LABEL" width ="50%">Tariff Type</td>
					<td width="50%" class="CONTROL"><div id="tariffType"></div></td>
			</tr>	
			<tr>  
				<td class ="LABEL" width ="50%">Is Compulsory (for IPD)</td>
				<td class="CONTROL" width="50%">			
				<html:select property="strIsDefaultForIpd" name="localTariffBean" styleClass="comboNormal">
					<html:option value="0">Select Value</html:option>
					<html:option value='1'>Except New Born Baby</html:option>
					<html:option value='2'>New Born Baby</html:option>
					<html:option value='3'>All</html:option>
				</html:select>	
			</tr>
			<tr style="display: none;">  
				<td class ="LABEL" width ="50%"><span class="style1"><font color="red">*</font></span> Effective From</td>
				<td class="CONTROL" width="50%"><dateTag:date name="effectiveFromContbDet"  value="${localTariffBean.strEffectiveFrom}"/></td>			
		</table>
		
	</div>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
	<!-- <tr>
			<td class="LABEL" width="50%">Is Estimation</td>
			<logic:equal value="1" property="strIsEstimationFlag" name="localTariffBean">
			<td class="CONTROL" width="50%"><input type="checkbox" name="strIsEstimationFlag" value ="1"  checked="checked" disabled="disabled"/></td>
			</logic:equal>
			<logic:equal value="0" property="strIsEstimationFlag" name="localTariffBean">
			<td class="CONTROL" width="50%"><input type="checkbox" name="strIsEstimationFlag" value ="0"  disabled="disabled"/></td>
			</logic:equal>
	</tr>-->
	<tr>
			<td class="LABEL" width="50%">Remarks</td>
			<td class="CONTROL" width="50%"><textarea name="remarks" rows="2" ><bean:write name="localTariffBean" property="remarks" filter="false"/></textarea></td>
		</tr>
		<tr >
		    <td width ="45%" class ="LABEL"><font color="red"> * </font> Record Status </td>
		    <td width ="45%" class ="CONTROL" >
			    <html:radio name="localTariffBean" property="isValid" value="1" />Active<span class="LABEL">
			    <html:radio name="localTariffBean" property="isValid" value="2" />InActive
		    </span></td>
    	</tr>
		<tr class="FOOTER">
			<td colspan="1"><div align="left"><font color="red">~</font>~(Only Unmapped Tariff & Entered Data Will Be Displayed)</div></td>
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	
	<div align="center">
	<!-- <img title="Save Record" src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand" onClick=" return submitData('SAVEMODIFY'); " /> 
	<img title="Cancel Process" style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />
	 -->
	<br><td align="right"><a href="#" class="button" id="" onClick=" return submitData('SAVEMODIFY');"><span class="save">Save</span></a></td>
				<td align="left"><a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a></td>
	</div>
	
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="chk" value="${localTariffBean.chk[0]}">
	<input type="hidden" name="totalrows" value="${localTariffBean.strTotalRows}">
	<input type="hidden" name="strGroupId" value="${localTariffBean.strGroupId}" />
	<input type="hidden" name="strModTariffId" value="${localTariffBean.tariffId}" />
    <input type="hidden" name="strHospitalCode" value ="${localTariffBean.strHospitalCode}" />
    <input type="hidden" name="strCostType" value="${localTariffBean.strCostType}" />
    <input type="hidden" name="gstrTariffName" value="${localTariffBean.gstrTariffName}"/>
    <input type="hidden" name="defaultUnitComboData"/>
    
<cmbPers:cmbPers></cmbPers:cmbPers>

</html:form>
<jsp:include page="addmultirow_tariffMst_bill.jsp"></jsp:include>
</body>
</html>