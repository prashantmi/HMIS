<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html>
<head>
<title>List of Consumables Whose Expiry Date is Due</title>
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

	var hisValidator = new HISValidator("consexpirydateRpt");
    if(document.getElementsByName("strDistrictFlag")[0].value == '2')
    {
	    hisValidator.addValidation("strStoreId", "dontselect=-1","Store Name");
	    document.forms[0].strStoreName.value = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
	}
	else
	{
	    hisValidator.addValidation("strDistrictStoreId", "dontselect=-1","Select District Drug Warehouse Name ");
	    document.forms[0].strStoreName.value = document.forms[0].strDistrictStoreId[document.forms[0].strDistrictStoreId.selectedIndex].text;
	}   
	
	if(document.getElementsByName("strExpNonExpiryFlag")[0].value=='20')
    {        
         if(document.getElementsByName("strDateDaysFlag")[0].value=='22')
         {
           hisValidator.addValidation("strDueDate", "date","Near Expiry Date is a mandatory field");
// Not required           hisValidator.addValidation("strDueDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select Due Date Less Than or Equal To Current Date");
         }
         else
         {        
           hisValidator.addValidation("strExpiryDays","req","Near Expiry Day(s) is a Mandatory Field" );
         }  
    }
		hisValidator.addValidation("strItemCatNo", "dontselect=0","Select Item Category From Item Category Combo");
		hisValidator.addValidation("strDueDate", "date","From Date is a mandatory field");
		
			
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
function getItemCatCmb(){ 

		if(document.forms[0].strStoreId.value!=0){
			var url ="ListConsumablesExpiryDateRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"1");
	 	}else{
		//document.forms[0].strItemCatNo.value="0";
	 		var url ="ListConsumablesExpiryDateRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"1");
	}
}

function getAjaxResponse(res,mode){
	
	if(mode=="1"){ 
		
		
			var objVal= document.getElementById("itemCatDivId");
			objVal.innerHTML = "<select name ='strItemCatNo' class='comboNormal'>"+res+"</select>";		
		
	}	
	if(mode=="2"){ 
		
		
			var objVal= document.getElementById("itemCatDivId");
			objVal.innerHTML = "<select name ='strItemCatNo' class='comboNormal' >"+res+"</select>";		
		
	}	
		
}

function onLoadPage(){

if(document.forms[0].strUserLevel.value=='6')
{
		//document.forms[0].strStoreId.value = "0";
}
else{
	//document.forms[0].strStoreId.value = "-1";
}
	
	document.forms[0].strItemCatNo.value="0";
	document.forms[0].strStoreId.value = "-1";
	document.forms[0].strDueDate.value = document.forms[0].strCurrentDate.value;
	document.getElementsByName("expired")[0].checked=true;
	 document.getElementsByName("nearExpiry")[0].checked=false;
	 
	 
	 document.getElementsByName("strExpiryDays")[0].checked=true;
	 document.getElementsByName("strExpiryDate")[0].checked=false;
	 
	document.forms[0].strDueDate.value = document.forms[0].strCurrentDate.value; 
	document.getElementsByName("strExpNonExpiryFlag")[0].value="10";// Expired

}

function setValueChk(chkObj)
{
    
     if(chkObj.value =='1')
 	 {
	    document.getElementsByName("nearExpiry")[0].checked=false;
		document.getElementById("nearExpDiv").style.display="none";
		document.getElementById("expDaysDiv").style.display="none";
		document.getElementById("expDateDiv").style.display="none";
		
		
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementsByName("strItemCatNo")[0].value="0";
		document.getElementsByName("strStoreId")[0].value="0";
		document.forms[0].strDueDate.value = document.forms[0].strCurrentDate.value;
		document.getElementsByName("strExpNonExpiryFlag")[0].value="10";// Expired
	 	
		
	}
	if(chkObj.value =='2')
 	{ 
	    document.getElementsByName("expired")[0].checked=false;
		document.getElementById("nearExpDiv").style.display="block";
		document.getElementById("expDaysDiv").style.display="block";
		document.getElementById("expDateDiv").style.display="none";
				
		document.getElementsByName("strUserRemarks")[0].value="";
		document.getElementsByName("strItemCatNo")[0].value="0";
		document.getElementsByName("strStoreId")[0].value="0";
		document.forms[0].strDueDate.value = document.forms[0].strCurrentDate.value;
		document.getElementsByName("strExpNonExpiryFlag")[0].value="20";//  Near Expiry
	 	
	}
}


function setExpiryChk(chkObj)
{
    
     if(chkObj.value =='11')
 	 {
		document.getElementsByName("strExpiryDays")[0].checked=true;
	    document.getElementsByName("strExpiryDate")[0].checked=false;
		document.getElementById("expDaysDiv").style.display="block";
		document.getElementById("expDateDiv").style.display="none";
		document.getElementsByName("strExpiryDays")[0].value="11";
		document.getElementsByName("strDateDaysFlag")[0].value="11"; // Due Date
		
		
	}
	if(chkObj.value =='22')
 	 {
 	    document.getElementsByName("strExpiryDays")[0].checked=false;
	    document.getElementsByName("strExpiryDate")[0].checked=true;
		document.getElementById("expDaysDiv").style.display="none";
		document.getElementById("expDateDiv").style.display="block";
		document.getElementsByName("strExpiryDate")[0].value="22";	
		document.getElementsByName("strDateDaysFlag")[0].value="22"; // Due Days	
			
		document.forms[0].strDueDate.value = document.forms[0].strCurrentDate.value;
	 	
	}
}		

function getItemCatCmb()
{ 
    

	if(document.getElementsByName("whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse")[0].checked==true)
	{
			
			
			if(document.forms[0].strDistrictStoreId.value!=0)
			{
				var url ="ListConsumablesExpiryDateRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strDistrictStoreId.value;
		 		ajaxFunction(url,"2");
		 		
		 	}
		 			
	}
	else
	{
		if(document.forms[0].strStoreId.value!=0)
		{
			var url ="ListConsumablesExpiryDateRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
	 		ajaxFunction(url,"2");
	 		
	 	}
		else{var url ="ListConsumablesExpiryDateRptCNT.cnt?hmode=ITEMCATCMB&storeId="+document.forms[0].strStoreId.value;
 		ajaxFunction(url,"2");}
	 	
	}
	
		
}	

function setDistrictDrugWarehouseCombo()
{
    document.getElementsByName("strItemCatNo")[0].value="0";
     
    
	if(document.getElementsByName("whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse")[0].checked==true)
	{
		document.getElementById("drugWarehouseDivId").style.display='none';
		
		document.getElementById("districtDrugWarehouseDivId").style.display='';
		document.getElementsByName("strDistrictFlag")[0].value='1';
		
	}
	else
	{
		document.getElementById("drugWarehouseDivId").style.display='';
		
		document.getElementById("districtDrugWarehouseDivId").style.display='none';
		document.getElementsByName("strDistrictFlag")[0].value='2';
		
	}
	
}		
		
		
</script>
</head>
<body onload="onLoadPage();">
<html:form action="/reports/ListConsumablesExpiryDateRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="consexpirydateRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="consexpirydateRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="consexpirydateRpt" property="strWarningMsg"/></div>


	<tag:tab tabLabel="List of Consumables Whose Expiry Date is Due"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4"></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="4">
				<input type="radio" name="expired"    value="1" onclick="setValueChk(this);">Expired
				<input type="radio" name="nearExpiry" value="2" onclick="setValueChk(this);">Near Expiry
				
				
			</td>
		</tr>
		<tr style="display:none";>
		<td colspan="2" class="LABEL" width="50%" align="right">
				<input type="checkbox" name="whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse" value="1" onclick="setDistrictDrugWarehouseCombo();" />
			</td>
		
		
			<td colspan="2" class="CONTROL" width="50%" align="right">
				Whether Consolidated Stock For All Institutes Of District Drug Warehouse
			</td>			
		</tr>
		
		<tr	id="drugWarehouseDivId">
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Store Name</td>
			<td width="50%" colspan="2" class="CONTROL"><select
				name="strStoreId" class="comboMax" onchange="getItemCatCmb();">
				<option title="Select Value" value="-1">Select Value</option>
				<bean:write name="consexpirydateRpt" property="strStoreValues"
					filter="false" />
						
			</select></td>
		</tr>
		</table>
		<div id="districtDrugWarehouseDivId" style="display:none;">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>District Drug Warehouse Name</td>
			<td width="50%" colspan="2" class="CONTROL">
			
				<select name="strDistrictStoreId" class="comboMax" onchange="getItemCatCmb();">
					<bean:write name="consexpirydateRpt" property="strDistrictStoreValues" filter="false"/>
				</select>
							
			</td>
		</tr>		
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
			
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Category</td>
				<td width="50%" colspan="2" class="CONTROL">
					<div id="itemCatDivId">
					<logic:equal name="consexpirydateRpt" property="strUserLevel" value="6" >
						<select	name="strItemCatNo" class="comboNormal">
						<bean:write name="consexpirydateRpt" property="strItemCatValues" filter="false"/>
							<option value="0">Select Value</option>								
							<option value="10">Drug</option>					
						</select>
					</logic:equal>
					
					<logic:notEqual name="consexpirydateRpt" property="strUserLevel" value="6" >
					<select	name="strItemCatNo" class="comboNormal">
					<bean:write name="consexpirydateRpt" property="strItemCatValues" filter="false"/>
						</select>
					</logic:notEqual>
					
				</div>
			</td>			
		</tr>
		</table>
		<div id="nearExpDiv" style="display:none;">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
			<tr>
			<td class="LABEL" width="50%">Near Expiry In</td>
				<td class="CONTROL" width="25%">
				
					<input type="radio" name="strExpiryDays"  value="11" onclick="setExpiryChk(this);">Day(s)
				</td>	
				<td class="CONTROL" width="25%">
					<input type="radio" name="strExpiryDate" value="22" onclick="setExpiryChk(this);">Date
					
					
				</td>
				</tr>
				</table>
		</div>
				
		<div id="expDaysDiv" style="display:none;">
								
				<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
				        <td width="50%"  class="LABEL"></td>
				        
						<td width="25%"  class="CONTROL">
					              <input class="txtFldNormal" type="text" name="strFrmExpiryDays" onkeypress="return validateData(event,5);" maxlength="3" >
					    </td>
					    
					    <td width="25%"  class="CONTROL"></td>
			    </tr>
			  </table>
			</div>
	    <div id="expDateDiv" style="display:none;">
								
				<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
				        <td width="50%"  class="LABEL"></td>
				        
						<td width="50%"  class="CONTROL">
					              <dateTag:date name="strDueDate" value="${consexpirydateRpt.strCurrentDate}" />
					    </td>
					    
					    
			    </tr>
			  </table>
			</div>		
			
			
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
							
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
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="consexpirydateRpt" value="1"></html:checkbox>
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
<!--	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
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
<input type="hidden" name="strDistrictFlag" value="2"/>
<input type="hidden" name="strExpNonExpiryFlag" />
<input type="hidden" name="strDateDaysFlag" />
<input type="hidden" name="strStoreName" />
<input type="hidden" name="strCurrentDate" value="${consexpirydateRpt.strCurrentDate}"/>
<input type="hidden" name="strUserLevel" value="${consexpirydateRpt.strUserLevel}"/>


</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>