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
<title>Tariff Master</title>
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
		//document.getElementById("divId1").style.display="block";				
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
		//document.getElementById("divId1").style.display="none";
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

	function checkCompCharges(chkobj){
	
	
		if(chkobj.checked){
		
			document.forms[0].strIsDefaultForIpd.value=0;
			document.forms[0].strIsDefaultForIpd.disabled = true;
			
		}else{
		
			document.forms[0].strIsDefaultForIpd.disabled = false;
		
		}
	
	}

function tariffCombo()
{	
	
	var temp = document.forms[0].strServiceId.value;
	
	
	var mode="POPULATEDATA";
	var url="CNTTrfMst.cnt?hmode="+mode+"&service="+temp + "&tariffId=0";
	
	//alert("url : "+url);	
	ajaxFunction(url,"1");
}

function getGlobalTariff(obj)
{	
	/*if(document.getElementsByName("groupId")[0].value == "105")
	{
	document.getElementsByName("strIsEstimationFlag")[0].checked=true;
	document.getElementsByName("strIsEstimationFlag")[0].disabled=true;
	}
	else
	{
	document.getElementsByName("strIsEstimationFlag")[0].checked=false;
	document.getElementsByName("strIsEstimationFlag")[0].disabled=true;
	}*/
	
	/*if(document.getElementsByName("groupId")[0].value == "105")
	{
	document.getElementsByName("strServiceId")[0].disabled=true;
	document.getElementsByName("tariffName")[0].disabled=true;
	}
	else
	{
	document.getElementsByName("strServiceId")[0].disabled=false;
	document.getElementsByName("tariffName")[0].disabled=false;
	}*/	
	
	
	var mode="GLOBALTARIFF";
	var url="CNTTrfMst.cnt?hmode="+mode+"&groupId="+obj.value;
	
	//alert("url : "+url);	
	ajaxFunction(url,"3");
}

function getGroupCmb()
{	
	if(document.forms[0].isPackage.checked)
	{
		if(document.forms[0].strIsEstimationFlag.checked)
		{
			document.forms[0].strIsEstimationFlag.checked=false;			
		}		
	}
	
	if(document.forms[0].isPackage.checked)
	{
		document.forms[0].isPackage.value="1";
	}
	else
	{
		document.forms[0].isPackage.value="0";
	}
	var isPackage = document.forms[0].isPackage.value;
	if(isPackage == '1')
	{
	  document.getElementById("unitId").style.display="none";
      document.getElementById("unitIdWthPkg").style.display="block";
	}
	else
	{
	  document.getElementById("unitId").style.display="block";
      document.getElementById("unitIdWthPkg").style.display="none";
	} 
	  /* var mode="getAddGroupCombo";
	  var url="CNTTrfMst.cnt?hmode="+mode+"&isPackage="+isPackage;	
	  ajaxFunction(url,"2"); */
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
	if(mode==2)//On Package Change
	{			
		var objVal = document.getElementById("grpId");
		objVal.innerHTML = "<select name = 'groupId' class='comboNormal' onchange='getGlobalTariff(this);'>" +res+ "</select>"; 
		
	//	var objVal1 = document.getElementById("unitIdWthPkg");
	//	objVal1.innerHTML = "<select name = 'defaultUnit'>" +temp[1]+ "</select>"; 
	 
	
  }	
  if(mode==3)
  {
		var objVal = document.getElementById("globalTariffId");
		objVal.innerHTML = "<select name = 'globalTariff' class='comboNormal' onchange='setTariffName(this);'>" +res+ "</select>";		
  }	
}

function setTariffName(obj)
{
	document.forms[0].trfPkgName.value=obj.options[obj.selectedIndex].text;
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
	hisValidator.addValidation("groupId","dontselect=0","Please Select Value From Group");
	hisValidator.addValidation("globalTariff","dontselect=0","Please Select a Global Tariff Name");
	hisValidator.addValidation("trfPkgName","req","Tariff Name Is Mandatory Field");
	hisValidator.addValidation("tariffCode","req","Tariff Code Is Mandatory Field");
	
	hisValidator.addValidation("strDiscountLimit","req","Discount Limit Is Mandatory Field");
	hisValidator.addValidation("strDiscountLimit","numltet=100","Discount Limit should be less than or equal to 100");
	hisValidator.addValidation("strDiscountPrivilege","req","Discount Privilege Is Mandatory Field");
	hisValidator.addValidation("strDiscountLimit","numltet=100","Discount Privilege should be less than or equal to 100");
	
	hisValidator.addValidation("rate","req","Default Rate Is Mandatory");
	hisValidator.addValidation("actualCost","req","Actual Cost Is Mandatory");
	hisValidator.addValidation("serviceTax","req","Service Tax Is Mandatory");
	
	hisValidator.addValidation("trfPkgName","maxlen=100","Tariff Name should not contain greater than 50 characters" );
	
	if(document.forms[0].isPackage.checked == true)
	{	
		hisValidator.addValidation("defaultPackUnit","dontselect=0","Please Select Value From Default Unit");
	}
	else
	{	
		hisValidator.addValidation("defaultUnit","dontselect=0","Please Select Value From Default Unit");	
	}
	 	//hisValidator.addValidation("effectiveFromContbDet","req","Effective From is Mandatory");
	 	
	
	hisValidator.addValidation("rate","amount=8,2","Please Enter Valid Amount.");
	hisValidator.addValidation("actualCost","amount=8,2","Please Enter Valid Amount.");
	hisValidator.addValidation("serviceTax","numltet=100","Please Select % Less Than Or Equal To 100");	
	hisValidator.addValidation("serviceTax","amount=4,1","Please Enter Valid Percentage.");
	hisValidator.addValidation("share","numltet=100","Please Select % Less Than Or Equal To 100");
	hisValidator.addValidation("share","amount=4,1","Please Enter Valid Percentage.");
	hisValidator.addValidation("remarks","maxlen=50","Remarks should not be greater than 50 characters");
	var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();
	
	if(retVal){	
	var arr = document.getElementsByName("department");
		var multirowLen = arr.length-1;
		if(multirowLen>1)
		{
			for(i=0; i< multirowLen-1 ; i++)
			{
				for(j=multirowLen-1; j>i; j--)
				{
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
			else{		
		return false;
			}
			
	if(retVal)
	{	
		document.forms[0].strIsDefaultForIpd.disabled = false;
		if(!document.forms[0].tariffName.value=="0")
		document.forms[0].gstrTariffName.value=document.forms[0].tariffName.options[document.forms[0].tariffName.selectedIndex].text ;	
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
	}
	else
		{		
		return false;
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
			document.forms[0].isPackage.checked=false;
			document.forms[0].isPackage.value="0";
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
	
</script>
</head>

<body onload="document.forms[0].groupId.focus();" >
<html:form name="localTariffBean" action="/masters/CNTTrfMst.cnt"
	type="billing.masters.controller.fb.LocalTariffMstFB">

	<div id = "errMsg" class="errMsg"><bean:write name="localTariffBean" property="errMsg"/></div>
	<div id = "normalMsg" class="normalMsg"><bean:write name="localTariffBean" property="normalMsg"/></div>
	<tag:tab tabLabel="Add Tariff Master" width="TABLEWIDTH" align="center"></tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="2" WIDTH="100%">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="HEADER">
					<td width="50%">
						<div align="left">Tariff Master&gt;&gt; Add</div>
					</td>
					<td width="50%">
						<div align="right">
							<html:checkbox name="localTariffBean" property="isPackage" onchange="getGroupCmb()" value="1" />Package
							<input type="checkbox" name="strIsEstimationFlag" onclick="disableService();">Estimation
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="grpId"><html:select name="localTariffBean" styleClass='comboNormal' property="groupId"  onchange='getGlobalTariff(this);'>
			<bean:write name="localTariffBean" property="addGroupCombo" filter="false"/>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Global Tariff Name</td>
			<td class="CONTROL" width="50%">
			<div id="globalTariffId"><html:select styleClass='comboNormal' name="localTariffBean" property="globalTariff"  >
			<bean:write name="localTariffBean" property="addGlobalTariffCombo" filter="false"/>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Tariff/Package Name</td>
			<td class="CONTROL"><input type="text" class="txtFldBig"
			name="trfPkgName" onkeypress="return validateData(event,16);"
				maxlength="100"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Default Unit</td>
			<td class="CONTROL">
			  <div id="unitId" style="display:block">
			    <html:select name="localTariffBean" property="defaultUnit" styleClass='comboNormal'>
			      <bean:write name="localTariffBean" property="addDefaultUnitCombo" filter="false"/>
			    </html:select>
			  </div>
			  <div id="unitIdWthPkg" style="display:none">
			    <html:select name="localTariffBean" property="defaultPackUnit" styleClass='comboNormal'>
			      <bean:write name="localTariffBean" property="addDefaultUnitComboWithPkg" filter="false"/>
			    </html:select>
			  </div>			  
			</td>
		</tr>
		
		
		
		<tr>  
			<td class ="LABEL" width ="50%">Un-Defined Charges</td>
			<td class="CONTROL" width="30%"> 
			<html:checkbox property="strUndefinedCharges" name="localTariffBean" value="1" onchange="checkCompCharges(this);" ></html:checkbox> </td>
	    </tr>
	</table>
	<div id="divId1" style="display:none">
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="50%">Length of Stay</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldBig" name="lengthOfStay" maxlength="2" value="0" onkeypress="return validateData(event,5);"></td>
		</tr>
	</table>
	</div>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="2"><div class='line'><label class='DIVLABEL'>Associated With</label></div></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Tariff Code</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMax" style="text-transform: uppercase" name="tariffCode" maxlength="15" onkeypress="return validateData(event,8);"></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Service Type</td>
			<td class="CONTROL" width="50%">
			<html:select name="localTariffBean" property="strServiceId"  onchange="tariffCombo();" styleClass='comboNormal'>
			<bean:write name="localTariffBean" property="addServiceCombo" filter="false"/>
			</html:select></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">~</font>Mapped Service Name</td>
			<td class="CONTROL" width="50%">
			<div id="tId">
				<html:select name="localTariffBean" property="tariffName" styleId="tariffId" styleClass='comboNormal'>
					<html:option value="0">Select Value</html:option>
				</html:select>
			</div></td>
		</tr>
	</table>	
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px" style="display: none;">
		<tr class="HEADER">
			<td colspan="2">Discount Rule</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Discount Limit</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMin" maxlength="3" name="strDiscountLimit" value="0" onkeypress="return validateData(event,5);"> %</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Discount Privilege</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMin" maxlength="3" name="strDiscountPrivilege" value="0" onkeypress="return validateData(event,5);"> %</td>
		</tr>		
	</table>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
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
	<div id="divId2" style="display: none;">
          <bean:write name="localTariffBean" property="toDefault" filter="false"/>
          <table class="TABLEWIDTH" border="0" align="center">
			<tr>
			<td colspan="2" width="50%">
			<table width="100%" border="0" align="center" cellpadding="1px" cellspacing="1px">
			<tr>
				<td width="50%"><div class='line'><label class='DIVLABEL'>Contribution Details</label></div></td>
				<td width="50%"><div align="right" class='lineContinue2'><img src="../../hisglobal/images/plus.gif" onClick="addRows(new Array('department','share'),new Array('s','t'),'1','1','R');"></div></td>
			</tr>				
			</table>
			</td> 
			</tr>
		  </table>
		  <table class="TABLEWIDTH" border="0" align="center">
			<tr>
				<td class="multiLabel" width="55%">Department</td>
				<td class="multiLabel" width="35%">Share(%)</td>				
				<td class="multiLabel" width="10%">&nbsp;</td>
			</tr>
		  </table>
		  <div id="id1"></div>
		  <table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
			<tr class="TITLE"><td  colspan="2" ></td></tr>	
			<tr>  
				<td class ="LABEL" width ="50%">Tariff Type</td>
				<td width="50%" class="CONTROL">
					<select name='strCostType' class='comboNormal'>
						<option value='0'>Select Value</option><option value='1'>Hospital Cost</option>
						<option value='2'>Third Party Cost</option>
						<option value='3'>Others</option>
					</select></td>
			</tr>	
			<tr>  
				<td class ="LABEL" width ="50%">Is Compulsory (for IPD)</td>
				<td class="CONTROL" width="50%">
				<html:select property="strIsDefaultForIpd" name="localTariffBean" styleClass="comboNormal">
					<html:option value="0">Select Value</html:option>
					<html:option value='1'>Except New Born Baby</html:option>
					<html:option value='2'>New Born Baby</html:option>
					<html:option value='3'>All</html:option>
				</html:select></td>	
			</tr>	
			<tr style="display: none;">  
				<td class ="LABEL" width ="50%"><span class="style1"><font color="red">*</font></span> Effective From</td>
				<td class="CONTROL" width="30%"><dateTag:date name="effectiveFromContbDet"  value="${localTariffBean.currentDate}"/></td>
			</tr>			
		  </table>
	</div>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="50%">Remarks</td>
			<td class="CONTROL" width="50%"><textarea name="remarks" rows="2"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="1"><div align="left"><font color="red">~</font>(Only Unmapped Tariff Will Be Displayed)</div></td>
			<td colspan="1"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="100%">			
			<div align="center">
				<!-- <img name="save" src="../../hisglobal/images/btn-sv.png" title="Save Record" style="cursor:pointer;cursor:hand"
					onClick="destroyMultiRow('1'); return submitData('SAVEADD'); document.forms[0].reset();" /> 
				<img style="cursor:pointer;cursor:hand"  title="Reset Content" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset(),document.forms[0].addGroupCombo.focus();" />
				<img title="Cancel Process" style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />
			 -->
			
			<br><a href="#" class="button" id="" onClick="destroyMultiRow('1'); return submitData('SAVEADD'); document.forms[0].reset();"><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset(),document.forms[0].addGroupCombo.focus();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			</div>
		</td>
		</tr>
	</table>
	
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="gstrTariffName"/>
    <input type="hidden" name="strHospitalCode" value ="${localTariffBean.strHospitalCode}" />
    
    <cmbPers:cmbPers/>
    
</html:form>
<jsp:include page="addmultirow_tariffMst_bill.jsp"></jsp:include>

</body>
</html>