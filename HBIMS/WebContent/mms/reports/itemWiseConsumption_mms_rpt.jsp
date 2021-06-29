<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Drug wise Consumption Details</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function validate(){

	var hisValidator = new HISValidator("itemWiseConsumptionRpt");

	hisValidator.addValidation("strItemCategoryNo", "dontselect=0","Please Select Item Category");
	
	if(document.forms[0].strDateYear[0].checked){
	
		hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCtDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	}else if(document.forms[0].strDateYear[1].checked){
	
		hisValidator.addValidation("strFromYear", "req","From Year is a mandatory field");
		hisValidator.addValidation("strToYear", "req","To Year is a mandatory field");
		hisValidator.addValidation("strFromYear","numltet="+document.forms[0].strToYear.value,"Please Select To Year Greater Than or Equal To From Year ");
	
	}
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if(retVal){
		document.forms[0].hmode.value = "SHOWRPT";
		
			if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
			{
				document.forms[0].target = "_self";
			}else{
				document.forms[0].target = "_blank";
			}
		document.forms[0].submit();
		}else{
		return false;
		}
	}
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}

function groupNameCombo()
{
   var mode ="GROUPNAME";  
   var url="ItemWiseConsumptionRptCNT.cnt?hmode=GROUPNAME&itemCatNo="+document.forms[0].strItemCategoryNo.value;
   ajaxFunction(url,"1");
} 

function subGroupNameCombo()
{
   var mode ="SUBGROUPNAME";  
   var url="ItemWiseConsumptionRptCNT.cnt?hmode=SUBGROUPNAME&groupId="+document.forms[0].strGroupId.value+
   								"&itemCateg="+document.forms[0].strItemCategoryNo.value;
   ajaxFunction(url,"2");
} 

function itemNameCombo()
{
	if(document.forms[0].strGroupId.value!="0"){
		var mode ="ITEMNAME";
	   var url="ListItemWiseSupplierRptCNT.cnt?hmode=ITEMNAME&itemCatNo="+document.forms[0].strItemCategoryNo[document.forms[0].strItemCategoryNo.selectedIndex].value+
	   				"&groupId="+document.forms[0].strGroupId[document.forms[0].strGroupId.selectedIndex].value+
	   				"&subgrpid="+document.forms[0].strSubGroupId.value;
	   ajaxFunction(url,"3");
   }
} 

function getAjaxResponse(res,mode)
{
	var objVal;
	if(mode=="1")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }else
        {
       	  objVal= document.getElementById("groupId");
		  objVal.innerHTML = "<select name ='strGroupId' class='comboNormal' onChange='subGroupNameCombo();'>" + res + "</select>";
		  
        }
        itemNameCombo();
    }
    
  	if(mode=="2")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }else
        {
        	var temp = res.split('@');
        	
          objVal= document.getElementById("subGroupId");
		  objVal.innerHTML = "<select name ='strSubGroupId' class='comboNormal' onChange='itemNameCombo()'>" + temp[0] + "</select>";
		   
		  objVal= document.getElementById("ItemId");
		  objVal.innerHTML = "<select name ='strItemId' class='comboNormal' >" + temp[1] + "</select>";
		     
        }
    }
     if(mode=="3")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }else
        {
        	objVal= document.getElementById("ItemId");
		    objVal.innerHTML = "<select name ='strItemId' class='comboNormal' >" + res + "</select>";
		   
        }
    }
}

function radioButton(obj){
	
	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			break;
		}
		
	}

	if(obj.value == 1){
		document.getElementById("DateDivId").style.display = "block";
		document.getElementById("YearDivId").style.display = "none";
		document.forms[0].strItemCategoryNo.value="0";
		document.forms[0].strFromDate.value = document.forms[0].strCtDate.value;
	 	document.forms[0].strToDate.value = document.forms[0].strCtDate.value;
		
	}else if(obj.value == 2){
		document.getElementById("DateDivId").style.display = "none";
		document.getElementById("YearDivId").style.display = "block";
		document.forms[0].strItemCategoryNo.value="0";
		document.forms[0].strFromYear.value="";
	 	document.forms[0].strToYear.value="";
		
	}

}

function clearButton(){
	 
	 if(document.forms[0].strDateYear[0].checked){
	 	
	 	document.forms[0].strItemCategoryNo.value="0";
	 	document.forms[0].strGroupId.value="0";
	 	document.forms[0].strSubGroupId.value="0";
	 	document.forms[0].strItemId.value="0";
	 
	 }else if(document.forms[0].strDateYear[1].checked){
	 	
	 	document.forms[0].strItemCategoryNo.value="0";
	 	document.forms[0].strGroupId.value="0";
	 	document.forms[0].strSubGroupId.value="0";
	 	document.forms[0].strItemId.value="0";
	 	document.forms[0].strFromYear.value="";
	 	document.forms[0].strToYear.value="";
	 
	 }

}

function onLoadpage(){

	
	document.forms[0].strItemCategoryNo.value="0";
	
	if(document.forms[0].strDateYear[0].checked){
			document.getElementById("DateDivId").style.display = "block";
			document.getElementById("YearDivId").style.display = "none";
			document.forms[0].strFromDate.value=document.forms[0].strCtDate.value;
			document.forms[0].strToDate.value=document.forms[0].strCtDate.value;
		}else if(document.forms[0].strDateYear[1].checked){
			document.getElementById("DateDivId").style.display = "none";
			document.getElementById("YearDivId").style.display = "block";
			document.forms[0].strFromYear.value="";
			document.forms[0].strToYear.value="";
		}
	
}

</script>
</head>
<body onload="onLoadpage();">
<html:form action="/reports/ItemWiseConsumptionRptCNT" method="post">

	<div class="errMsg" id="errMsg"><bean:write
		name="itemWiseConsumptionRpt" property="strErrMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="itemWiseConsumptionRpt" property="strNormalMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="itemWiseConsumptionRpt" property="strWarningMsg" /></div>


	<tag:tab tabLabel="Item wise Consumption Details" selectedTab="FIRST"
		align="center" width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4"></td>
		</tr>

		<tr style='display: none;'>
			<td colspan="2" class="LABEL" align="center"><html:radio
				name="itemWiseConsumptionRpt" property="strDateYear" value="1"
				onclick="radioButton(this);">Date</html:radio><html:radio
				name="itemWiseConsumptionRpt" property="strDateYear" value="2"
				onclick="radioButton(this);">Year</html:radio> </td>
				
		</tr>
		<tr style="display: none;">
			<td width="50%" class="LABEL" colspan="2"><font color="red">*</font>Hospital
			name</td>
			<td width="50%" class="CONTROL" colspan="2"><select
				name="strHospitalCode" class="comboNormal">
				<bean:write name="itemWiseConsumptionRpt"
					property="strHospNameValues" filter="false" />
			</select></td>
		</tr>

		<tr>
			<td colspan="2" width="50%" class="LABEL"><font color="red">*</font>Item Category</td>
			<td colspan="2" width="50%" class="CONTROL"><select
				name="strItemCategoryNo" class='comboNormal' onChange="groupNameCombo()">
				<bean:write name='itemWiseConsumptionRpt' property="strItemCategoryCombo" filter='false'/>				
			</select></td>
		</tr>

		<tr>
			<td colspan="2" width="50%" class="LABEL">Group Name</td>
			<td colspan="2" width="50%" class="CONTROL">
			<div id="groupId"><select name="strGroupId" class='comboNormal'
				onchange="subGroupNameCombo();">

				<option value="0">All</option>
			</select></div>
			</td>
		</tr>
		<tr style="display: none;">
			<td colspan="2" width="50%" class="LABEL">Sub Group Name</td>
			<td colspan="2" width="50%" class="CONTROL">
			<div id="subGroupId"><select name="strSubGroupId"
				class='comboNormal' onChange="itemNameCombo();">

				<option value="0">All</option>
			</select></div>
			</td>
		</tr>

		<tr>
			<td colspan="2" class="LABEL">Item Name</td>
			<td colspan="2" class="CONTROL">
			<div id="ItemId"><select name="strItemId" class='comboNormal'>

				<option value="0">All</option>
			</select></div>
			</td>
		</tr>
	</table>
	<div id="DateDivId" style="display: block;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>From
			Date</td>
			<td class="CONTROL" width="50%"><dateTag:date name="strFromDate"
				value="${itemWiseConsumptionRpt.strCtDate}"></dateTag:date></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>To Date</td>
			<td class="CONTROL" width="50%"><dateTag:date name="strToDate"
				value="${itemWiseConsumptionRpt.strCtDate}"></dateTag:date></td>
		</tr>
	</table>
	</div>

	<div id="YearDivId" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>From
			Year</td>
			<td class="CONTROL" width="50%"><input type="text"
				name="strFromYear" maxlength="4" class="txtFldMin" value=""
				onkeypress="return validateData(event,5);"></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>To Year</td>
			<td class="CONTROL" width="50%"><input type="text"
				name="strToYear" maxlength="4" class="txtFldMin" value=""
				onkeypress="return validateData(event,5);"></td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td width="50%" colspan="2" class="LABEL">Report Format</td>
			<td width="50%" colspan="2" class="CONTROL"><select
				name="strReportFormat" onchange="">
				<option value="html">Html</option>
				<option value="pdf">Pdf</option>
			</select></td>

		</tr>

		<tr>
			<td width="50%" colspan="2" class="LABEL">Footer Required</td>
			<td width="50%" colspan="2" class="CONTROL"><html:checkbox
				property="strIsFooter" name="itemWiseConsumptionRpt" value="1"></html:checkbox>
			</td>

		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">User Remarks</td>
			<td width="50%" colspan="2" class="CONTROL"><input
				class="txtFldMax" type="text" name="strUserRemarks"></td>

		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font color='red'>*</font>Mandatory Field</td>
		</tr>
	</table>
	<!--<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>

			<td align="center"><img style="cursor: pointer"
				src="../../hisglobal/images/btn-generate.png"
				onClick="return validate();" /> <img style="cursor: pointer"
				src="../../hisglobal/images/btn-clr.png" onClick="clearButton();">
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png"
				onClick="cancelFunc();"></td>

		</tr>
	</table>-->
	<br>
<div align="center" id="">					 
					 	<a href="#" class="button" id="" onclick=' return validate();'><span class="generate">Generate</span></a>
						<a href="#" class="button"	onclick="clearButton"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</div>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strCtDate"
		value="${itemWiseConsumptionRpt.strCtDate}" />

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>