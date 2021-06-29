<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Not Issued Items</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function validate(){

		var hisValidator = new HISValidator("pendingIndentStatusRecordRpt");

	    hisValidator.addValidation("strStoreId", "dontselect=-1","Select Store Name from Store Combo ");
		hisValidator.addValidation("strItemCatNo", "dontselect=0","Select Item Category From Item Category Combo");
		hisValidator.addValidation("strReqTypeId", "dontselect=0","Select Request Type From Request Type Combo");
		
		hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
	   var retVal = hisValidator.validate();
	   var dd= dateDiff(document.forms[0].strFromDate.value ,document.forms[0].strToDate.value);
		// alert(dd);
		var dd2 = dd.split(' ')[0];
			     if( dd2> 60)
				     {
				     alert("Time difference couldn't be more than 60 days");
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

		if(document.forms[0].strStoreId.value!=0){
			var url ="PendingIndentStatusRecordRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"1");
	 	}else{
		//document.forms[0].strItemCatNo.value="0";
	 		var url ="PendingIndentStatusRecordRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"1");	
	}
}
function getReqTypeCmb(){ 
	if(document.forms[0].strStoreId.value!=0 && document.forms[0].strItemCatNo.value!=0 ){
		var url ="PendingIndentStatusRecordRptCNT.cnt?hmode=REQTYPECMB&storeId="+document.forms[0].strStoreId.value+"&catId="+document.forms[0].strItemCatNo.value;
 		ajaxFunction(url,"2");
 	}else{
	//document.forms[0].strItemCatNo.value="0";
 		var url ="PendingIndentStatusRecordRptCNT.cnt?hmode=REQTYPECMB&storeId="+document.forms[0].strStoreId.value+"&catId="+document.forms[0].strItemCatNo.value;
 		ajaxFunction(url,"2");	
}
}
function getAjaxResponse(res,mode){
	
	if(mode=="1"){ 
		
		
			var objVal= document.getElementById("itemCatDivId");
			objVal.innerHTML = "<select name ='strItemCatNo' class='comboNormal' onChange='getReqTypeCmb();' >"+res+"</select>";		
		
	}	

	if(mode=="2"){ 
		
		
		var objVal= document.getElementById("reqDivId");
		objVal.innerHTML = "<select name ='strReqTypeId' class='comboNormal'>"+res+"</select>";		
	
}	
}		
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}

function onLoadpage(){
	document.forms[0].strItemCatNo.value = "0";
	document.forms[0].strStoreId.value = "-1";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
}

// added by vipul on 10.05.2021
function getStoreName(Storename){
	var filter="";
	filter +="Store Name: "+Storename.options[Storename.selectedIndex].text;
	document.getElementById("filter").value=filter;
	//alert(filter);
	
}

function getConsolidate(){
	var checkoutConsolidated = document.getElementById('StrIsConsolidated');	
	 if (checkoutConsolidated.checked) {
		 document.forms[0].strStoreId.value = "0";
		 document.forms[0].strStoreId.disabled =true;
		 getItemCatCmb();
	    } else {
	    	document.forms[0].strStoreId.disabled =false;
	    	document.forms[0].strStoreId.value = "-1";
	    }
	
}

</script>
</head>
<body onload="onLoadpage();">
<html:form action="/reports/PendingIndentStatusRecordRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="pendingIndentStatusRecordRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="pendingIndentStatusRecordRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="pendingIndentStatusRecordRpt" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Not Issued Drugs/Items Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4">Not Issued Drugs/Items Report</td>
			
		</tr>
		
		<tr>
		<td width="50%" colspan="2" class="LABEL"></td>
		<td width="50%" colspan="2" class="CONTROL">
		<div align="right">
		<input type="Checkbox" name="strIsConsolidated" value="3" tabindex="1" id="StrIsConsolidated" onChange="getConsolidate();">Consolidated
<%-- 		<html:checkbox property="StrIsConsolidated" name="pendingIndentStatusRecordRpt" value="1">Consolidated</html:checkbox> --%>
		</div>
		</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Store Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strStoreId" class="comboNormal" onchange="getItemCatCmb(); getStoreName(this);">
					<bean:write name="pendingIndentStatusRecordRpt" property="strStoreValues"
						filter="false" />
				<option title="All" value="0">All</option>
				</select>
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Category</td>
				<td width="50%" colspan="2" class="CONTROL">
					<div id="itemCatDivId">
						<select	name="strItemCatNo" class="comboNormal">
					<option value="0">SelectValue</option>
				</select>
				</div>
			</td>			
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Request Type</td>
				<td width="50%" colspan="2" class="CONTROL">
					<div id="reqDivId">
						<select	name="strReqTypeId" class="comboNormal">
					<option value="0">SelectValue</option>
				</select>
				</div>
			</td>			
						
		</tr>
		
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate"
				value="${pendingIndentStatusRecordRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>To Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strToDate"
				value="${pendingIndentStatusRecordRpt.strCurrentDate}" /></td>
		</tr>
			<tr>
			<td width="50%" colspan="2" class="LABEL">Generation Type</td>
			<td width="50%" colspan="2" class="CONTROL">
			<input type="radio" name="strRptType" value="1" checked="" tabindex="1">Not Issued<input type="radio" name="strRptType" value="2" tabindex="1">Issued
			</td>
			
		</tr> 
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option>
			<option value="xls">Excel</option>
			</select>
			</td>
			
		</tr> 
	<!-- 		 added by vipul on 10.05.2021 -->
		<input type="hidden" name="filter" id="filter">
	<!-- 		 ended by vipul on 10.05.2021 -->	
		<tr style='display:none;'>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="pendingIndentStatusRecordRpt" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			User Remarks
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		<tr class="FOOTER">
			 <td colspan="4"> <font color='red'>*</font>Mandatory Field</td>
		</tr>
	</table>
<!-- 	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
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
<input type="hidden" name="strCurrentDate" value="${pendingIndentStatusRecordRpt.strCurrentDate}"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>