<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Consumption Report</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
	
<script language="JavaScript" src="../../hisglobal/jquery/jquery-3.3.1.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-ui.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery.autocomplete.min.js"></script>
<script language="Javascript" src="../../hisglobal/js/autocompleteItemSearch.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">


function validate(){

	var hisValidator = new HISValidator("consumptionRpt");
	
document.forms[0].strStoreName.value = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
	    
	    hisValidator.addValidation("strStoreId","dontselect=-1","Please Select Store Name");
		hisValidator.addValidation("strItemCatNo", "dontselect=0","Select Item Category From Item Category Combo");
		hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		//hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
	
		var itmeLength = parseInt(document.forms[0].strRightItemIds.length);
		//alert(itmeLength);
		if(itmeLength>0)
		{	
			var itemBrandIds = [];
			$('#strRightItemIds option').each(function() { 
			    itemBrandIds.push( $(this).val() );
			});	
			//alert(itemBrandIds);
			document.forms[0].strItemBrandId.value = 	itemBrandIds;	   
		}
		else
		{
			document.forms[0].strItemBrandId.value = "0";
		}
		
		var store = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
		document.forms[0].strStoreName.value=store;
		var cat = document.forms[0].strItemCatNo[document.forms[0].strItemCatNo.selectedIndex].text;
		document.forms[0].strCatName.value = cat;
		
		var retVal = hisValidator.validate();
	   var dd= dateDiff(document.forms[0].strFromDate.value ,document.forms[0].strToDate.value);
	 //alert(dd);
	var dd2 = dd.split(' ')[0];
		     if( dd2 > 90)
			     {
			     alert("Date difference couldn't be more than 90 days");
			    retVal= false;
			     }
		
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
	
	
function getItemCatCmb(){ 

		if(document.forms[0].strStoreId.value!=0)
		{
			//document.getElementsByName("strWhetherConsolidated")[0].disabled=true;
		
			var url ="ConsumptionRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"1");
	 	}
	 	else
	 	{
	 		//document.getElementsByName("strWhetherConsolidated")[0].disabled=false;
			document.forms[0].strItemCatNo.value="0";
		}
}
function getItemCombo()
{
	if( document.forms[0].strItemCatNo.value!=0)
	{
		//document.getElementsByName("strWhetherConsolidated")[0].disabled=true;
	
		var url ="ConsumptionRptCNT.cnt?hmode=ITEMCMB&storeId="+document.forms[0].strStoreId.value+"&catId="+document.forms[0].strItemCatNo.value;
 		ajaxFunction(url,"2");
 	}
 	else
 	{
 		//document.getElementsByName("strWhetherConsolidated")[0].disabled=false;
		document.forms[0].strItemId.value="0";
	}
}
function getSupplierCombo()
{
	if(document.forms[0].strItemCatNo.value!=0)
	{
		//document.getElementsByName("strWhetherConsolidated")[0].disabled=true;
	
		var url ="ConsumptionRptCNT.cnt?hmode=SUPPCMB&catId="+document.forms[0].strItemCatNo.value;
 		ajaxFunction(url,"3");
 	}
 	else
 	{
 		//document.getElementsByName("strWhetherConsolidated")[0].disabled=false;
		document.forms[0].strSupplierId.value="0";
	}
}

function LeftListTransfer()
{	
	//shiftToRightLimit("strLeftItemIds","strRightItemIds",1,25);
   // $('#strItemId').html($('#strLeftItemIds').html()); 
   // loadAutocompleteItems();          
    
    var ob1=document.forms[0].strLeftItemIds.value;
	 var ob=document.getElementById("strLeftItemIds");
	 shiftToRight("strLeftItemIds","strRightItemIds",1);
}

function transferToLeft()
{        
    shiftToLeft("strLeftItemIds","strRightItemIds",1);
    $('#strItemId').html($('#strLeftItemIds').html()); 
   // loadAutocompleteItems();         
}


function getAjaxResponse(res,mode){
	
	if(mode=="1"){ 
		
		
			var objVal= document.getElementById("itemCatDivId");
			objVal.innerHTML = "<select name ='strItemCatNo' onchange='getItemCombo();' class='comboNormal'>"+res+"</select>";		
		
	}	
	if(mode=="2"){ 
		
		
		var objVal= document.getElementById("itemDivId");
		objVal.innerHTML = "<select name ='strItemId' class='comboNormal'>"+res+"</select>";
		var objVal1= document.getElementById("LeftItemIds");
		objVal1.innerHTML = "<select id='strLeftItemIds' name='strLeftItemIds' size='10' multiple style='width: 560px' onChange='showSelection(this);' >"+res+"</select>";
		getSupplierCombo();		
	
}	
	if(mode=="3"){ 
		
		
		var objVal= document.getElementById("suppDivId");
		objVal.innerHTML = "<select name ='strSupplierId' class='comboNormal'>"+res+"</select>";		
	
}	
		
}		
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}


function onLoadPage(){

	document.forms[0].strStoreId.value = "0";
	document.forms[0].strItemCatNo.value = "0";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	
}
/*
function getDisplay (obj){

	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			break;
		}
		}
	document.forms[0].strStoreId.value=0;
	document.forms[0].strItemCatNo.value=0;
}*/


</script>
</head>
<body onload="onLoadPage();">
<html:form action="/reports/ConsumptionRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="consumptionRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="consumptionRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="consumptionRpt" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Consumption Report"
      selectedTab="FIRST" align="center" width="TABLEWIDTH">
    </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4"></td>
	</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">Store</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strStoreId" class="comboNormal" onchange="getItemCatCmb();">
					<bean:write name="consumptionRpt" property="strStoreValues" filter="false" />
				</select>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Category</td>
				<td width="50%" colspan="2" class="CONTROL">
					<div id="itemCatDivId">
						<select	name="strItemCatNo" class="comboNormal" onchange="getItemCombo();">
						<bean:write name="consumptionRpt" property="strCatVal" filter="false" />
						
				</select>
				</div>
			</td>			
		</tr>
		<tr>
		<td class="LABEL" width="25%" colspan="1"></td>
  			<td class="CONTROL" colspan="1" width="25%">  			 
				<div id="LeftItemIds" align="left">
					<select id="strLeftItemIds" name="strLeftItemIds" size="10" multiple style="width: 560px" onChange='showSelection(this);' >
						<bean:write name="consumptionRpt" property="strLeftItemList" filter="false"/>
					</select>
				</div>				
			</td>
			<td width="10%" class="CONTROL" colspan="1" align="left">			
			<div style='display:inline;'>
					<img src="../../hisglobal/images/forward3.gif" width="30" height="21" onclick ="LeftListTransfer();">
				<center>
			<img src="../../hisglobal/images/forwardward.gif" width="30" height="21" style="display: none;"
				align="middle" onClick="shiftAllToRight('strLeftItemIds','strRightItemIds',1);"/></center>
			<br/>
					
			<img src="../../hisglobal/images/backward.gif" width="30" height="21" style="display: none;"
				onClick="shiftAllToLeft('strLeftItemIds','strRightItemIds',1);">
		
				<img src="../../hisglobal/images/back3.gif" width="30" height="21" 
					onclick="transferToLeft();"/>
					</div>
					
			</td>			
			<td class="CONTROL" colspan="3">
			<div id="RightItemIds" align="left" style='display:inline;'>
					<select id="strRightItemIds" name="strRightItemIds" size="10" multiple style="width: 560px">
						<bean:write name="consumptionRpt" property="strRightItemList" filter="false"/>
					</select>
				</div>
			</td>		
		</tr>
		<tr style="display:none;">
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Item</td>
				<td width="50%" colspan="2" class="CONTROL">
					<div id="itemDivId">
						<select	name="strItemId" class="comboNormal" >
						<option value="0">All</option>
				</select>
				</div>
			</td>			
		</tr>
		<tr style="display:none;">
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Supplier</td>
				<td width="50%" colspan="2" class="CONTROL">
					<div id="suppDivId">
						<select	name="strSupplierId" class="comboNormal" >
						<option value="0">All</option>
				</select>
				</div>
			</td>			
		</tr>
		<tr style="display:none;">
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate"
				value="${consumptionRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>Till Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strToDate"
				value="${consumptionRpt.strCurrentDate}" /></td>
		</tr>
		
			<tr style="display:none;">
			<td class="LABEL" colspan="2"><font color="red">*</font>PO Type</td>
			<td class="CONTROL" colspan="2"><input type="radio" name = "strPOType" value = "1" checked/>Bulk PO<input type="radio" name = "strPOType" value = "2" />Local PO(online)<input type="radio" name = "strPOType" value = "3" />Local PO(offline)
			</td>
		</tr>	
		
		<tr style="display:none;">
			<td class="LABEL" colspan="2"><font color="red">*</font>Item Type</td>
			<td class="CONTROL" colspan="2"><input type="radio" name = "strItemType" value = "0" checked/>Unit<input type="radio" name = "strItemType" value = "1" />Misc.
			</td>
		</tr>
			
		<tr>
			<td width="50%" colspan="2" class="LABEL">Report Format</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strReportFormat"  onchange="">
				<option value="html">Html</option>
				<option value="pdf">Pdf</option>
				<option value="xls">Excel</option>	
					
				</select>
			</td>
			
		</tr> 
		
	<!--<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="consumptionRpt" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			User Remarks
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>  -->	
		<tr class="FOOTER">
			 <td colspan="4"><div align='left'>Opening balance as on 1st April of Current Financial Year or Stock Ledger Initiation Date whichever is later</div></td>
		</tr>
	</table>
	<!-- <table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			</td>
			
		</tr>
	</table>-->
<br>
<div align="center" id="">					 
					 	<a href="#" class="button" id="" onclick=' return validate();'><span class="generate">Generate</span></a>
						<a href="#" class="button"	onclick="document.forms[0].reset()"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</div>
<input type="hidden" name="hmode"/>

<input type="hidden" name="strCurrentDate" value="${consumptionRpt.strCurrentDate}"/>
<input type="hidden" name="strStoreName" value="${consumptionRpt.strStoreName}" />
<input type="hidden" name="strCatName" value="${consumptionRpt.strCatName}" />
<input type="hidden" name="strItemBrandId" value="0"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>