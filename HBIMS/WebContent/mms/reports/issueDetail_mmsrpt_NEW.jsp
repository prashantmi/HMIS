<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<!--<link href="HBIMS/css/master.css" rel="stylesheet" type="text/css">
 <link href="../../hisglobal/css/dwh.css" rel="stylesheet" type="text/css"> 
<link href="../../hisglobal/css/control.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/autocomplete.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	-->

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/autocomplete.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	

<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-ui.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery.autocomplete.min.js"></script>	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/autocompleteItemSearch.js"></script>
<script type="text/javascript"><!--

$(function() {	
	 displaySelectedDrug("strItemBrandId");
});

function loadAutocompleteItems()
{
	$('#strSearchDrug').val("");
     displaySelectedDrug("strItemBrandId");
     
     var itemList = [];
	$('#strItemBrandId option').each(function() {
	    itemList.push({ "value" :this.textContent , "data" : this.value });
	});
	
	$('#strSearchDrug').autocomplete({
	   lookup: itemList,
	   minChars:3,
	   onSelect: function (suggestion) {        
	     getDrugNameSelected(suggestion.data, "strItemBrandId");
	     getBatchNo();	     
	   }	    
	 });
	 
	 $('#strSearchDrug').click(function(){	 
	 	$(this).val("");
	 });	
}

function validate(){


 		var hisValidator = new HISValidator("issuedtlRptBean");
 		
 		
 		
        hisValidator.addValidation("strStoreId", "dontselect=-1", "Please Select Store Combo" );
        hisValidator.addValidation("strItemCatId", "dontselect=-1", "Please Select Category " );
        hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		//hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
	       	 
        
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


function validate1(){


if(true){
	document.forms[0].hmode.value = "SHOWRPT1";
	
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
        var objVal = document.getElementById("itemCategDIV");
        objVal.innerHTML = "<select name = 'strItemCategNo' class='comboMax' onchange='getDrugName();' >" + res + "</select>";
		getProgName();      
   }
   
   if(mode=="2")
   {
        var objVal = document.getElementById("drugNameDivId");
        objVal.innerHTML = "<select id='strItemBrandId' name='strItemBrandId' class='comboMax' onchange='getBatchNo();' >" + res + "</select>";
       loadAutocompleteItems();		      
   }
   
   if(mode=="3")
   {
   
        var objVal = document.getElementById("ExistingBatchId");
        objVal.innerHTML = "<select name = 'strBatchNo' class='comboMax'  >" + res + "</select>";
		      
   }
   
   if(mode=="4")
   {			
			var objVal1= document.getElementById("progNameDivId");
			objVal1.innerHTML = "<select id='strProgId' name='strProgId' class='comboMax'>"+res+"</select>";
   }
   if(mode=="5")
   {			
			var objVal1= document.getElementById("ItemNameDivId");
			objVal1.innerHTML = "<select id='strItemCatId' name='strItemCatId' class='comboMax' onchange='getDrugName();'  >"+res+"</select>";
   }
}


function getProgName()
{
		
	    var url ="IssueDetailRptCNT_NEW.cnt?hmode=PROGNAME&storeId="+document.forms[0].strStoreId.value+
		         "&itemcat="+document.forms[0].strStoreId.value;
	    
	    ajaxFunction(url,"4");
}				
  


function setValueChk(){
	if(document.getElementsByName("strCase")[0].checked){
		document.getElementsByName("strCase")[0].value="1";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementsByName("strItemCategNo")[0].value="0";
		document.getElementsByName("strStoreId")[0].value="0";
		
	}else if(document.getElementsByName("strCase")[1].checked){
		document.getElementsByName("strCase")[1].value="2";
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementsByName("strItemCategNo")[0].value="0";
		document.getElementsByName("strStoreId")[0].value="0";
	}else{
			document.getElementsByName("strCase")[2].value="3";
			document.getElementsByName("strUserRemarks")[0].value="";
			document.getElementsByName("strItemCategNo")[0].value="0";
			document.getElementsByName("strStoreId")[0].value="0";
	}
}

function onLoadPage(){

	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	getDrugName1();
}

function onClearButton(){

if((document.getElementsByName("strCase")[0].checked)||(document.getElementsByName("strCase")[1].checked)||
		(document.getElementsByName("strCase")[2].checked)){
    document.forms[0].reset();
	document.forms[0].strStoreId.value = "0";
	document.forms[0].strItemCategNo.value = "0";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	displaySelectedDrug("strItemBrandId");
	}
}

 function getDrugName()
{
		var mode="DRUGNAMECOMBO";
   		var url="IssueDetailRptCNT_NEW.cnt?hmode="+mode+"&storeId="+document.forms[0].strStoreId.value+"&catId="+document.forms[0].strItemCatId.value;
   		ajaxFunction(url,"2");
	
	
}

 function getDrugName1()
 {
 		var mode="DRUGNAMECOMBO";
    		var url="IssueDetailRptCNT_NEW.cnt?hmode="+mode+"&storeId="+0+"&catId="+10;
    		ajaxFunction(url,"2");
 	
 	
 }
function getItemCatName()
{
		var mode="ITEMCATEGORYCOMBO";
   		var url="IssueDetailRptCNT_NEW.cnt?hmode="+mode+"&storeId="+document.forms[0].strStoreId.value;  
   		ajaxFunction(url,"5");
	
	
}

function getBatchNo()
{  var mode="EXISTINGBATCH";
    var url="IssueDetailRptCNT_NEW.cnt?hmode="+mode
	 +"&strItemBrandId="+ document.forms[0].strItemBrandId.value 
	 + "&storeId="+ document.forms[0].strStoreId.value+"&catId="+document.forms[0].strItemCatId.value;
	ajaxFunction(url,"3");
   
}

</script>
</head>
<body class="background" onload=" "><!-- onload="onLoadPage();" commented--> 
<html:form action="/reports/IssueDetailRptCNT_NEW" method="post" styleClass="formbg">
	
<div class="errMsg" id="errMsg"><bean:write name="issuedtlRptBean" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="issuedtlRptBean" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="issuedtlRptBean" property="strWarningMsg"/></div>
<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4" class='all-four-rounded-corners'>Sale / Issue Register</td>
		</tr>
		
		<tr>
			<td width="25%"  class="LABEL">
			<font color="red">*</font>Store Name
			</td>
			<td width="25%"  class="CONTROL">
			<select name="strStoreId"  onchange="getItemCatName();" class="comboMax">                   
			<bean:write name="issuedtlRptBean" property="strStoreVal" filter="false" /></select>
			</td>
			<td width="25%"  class="LABEL"></td>
			<td width="25%"  class="CONTROL" ></td>
			</tr>
		
		<tr>
			<td width="25%"  class="LABEL"><font color="red">*</font>Item Category</td>
			<td width="25%"  class="CONTROL">
			<div id="ItemNameDivId">
			 <select name="strItemCatId" class="comboMax" >
				<option value="0">All</option>
			</select></div>
			</td>
		</tr>
		 
		<tr>
			<td class="LABEL" width="25%" colspan="1">Item Name</td>
			<td class="CONTROL" width="75%" >
			<input type="text" id="strSearchDrug" name="strSearchDrug" size="70%"/>
		  </td>
		</tr>
		
		<tr>
				<td class="LABEL" width="25%" colspan="1">Batch No.</td>
	
				<td class="CONTROL" width="25%" >
				    <div id="ExistingBatchId">
					    <select name="strBatchNo"	class='comboNormal' >
						<option value="0">All</option>
					    </select>
				    </div>
				</td>
				
				<td class="CONTROL" width="50%">
				<div id="drugNameDivId" style="display: none;">			
					<select id="strItemBrandId" name="strItemBrandId" class='comboMax' onChange='getBatchNo();'>
						<option value="0">All</option>
					</select>
				</div>
			</td>
				
		</tr>
		
		<tr>
			<td class="LABEL" width="25%" >Selected Item Name</td>
			<td class="CONTROL" width="75%" >
			<div id="DrugNameId" style="color:blue;font-weight:bold"></div>	
		  </td>
		</tr>
		
		<tr>
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>From Date</td>
			<td width="25%" colspan="1" class="CONTROL">
				<dateTag:date name="strFromDate" value="${issuedtlRptBean.strCurrentDate}"/>
			</td>
			</tr>
			<tr>
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="25%" colspan="1" class="CONTROL">
				<dateTag:date name="strToDate" value="${issuedtlRptBean.strCurrentDate}"/>
			</td>
			
		</tr>
		 
		<tr>
			<td width="50%"  class="LABEL">Report Format</td>
			<td width="50%"  class="CONTROL">
				<select name="strReportFormat"  onchange="">
					<option value="html">Html</option>
					<option value="pdf">Pdf</option>
					<option value="xls">Excel</option>
				</select>
			</td>
			
		</tr> 
		<tr>
			<td width="50%"  class="LABEL">Footer Required</td>
			<td width="50%"  class="CONTROL">
				<html:checkbox property="strIsFooter" name="issuedtlRptBean" value="1"></html:checkbox>
			</td>
		</tr>
		
		<tr>
			<td width="50%"  class="LABEL">User Remarks</td>
			<td width="50%"  class="CONTROL">
				<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		
		<tr class="FOOTER">
			 <td colspan="4" class='all-four-rounded-corners'><font size="2" color="red">*</font> Mandatory Fields</div></td>
		</tr>
		
	</table>
	
	<div><div class="legends">
	<div class="control_button">
	<table  class="TABLEWIDTH" align="center">
	<tr>
	<br>
	<td align="center"><div>
				<a href="#" class="button" onClick="return validate1();"><span class="generate">Generate</span></a>
				<a href="#" class="button" onClick="onClearButton();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();"><span class="cancel">Cancel</span></a>				 
				</div></td>
	</tr>	  
	</table>
	</div></div>	
	
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${issuedtlRptBean.strCurrentDate}" />

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>