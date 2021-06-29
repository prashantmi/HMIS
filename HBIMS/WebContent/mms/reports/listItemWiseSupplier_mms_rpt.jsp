<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Rate Contract Details</title>
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

	var hisValidator = new HISValidator("listItemWiseSupplierRpt");

	hisValidator.addValidation("strItemCategoryNo", "dontselect=0","Please Select Item Category");
	//hisValidator.addValidation("strItemId", "dontselect=0","Please Select Item Name");
	hisValidator.addValidation("strSupplierId", "dontselect=-1","Please Select Supplier Name");
	hisValidator.addValidation("strhospCmb","dontselect=0","Please Select Hospital  Name");

	if(document.getElementsByName("strActiveOrNearExpiry")[1].checked == true)
	{
		hisValidator.addValidation("strFrmExpiryDays", "req","Expiry days is a mandatory field");
			
	}
	
	
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if(retVal)
	{
	
		if( parseInt(document.getElementsByName("strFrmExpiryDays")[0].value) ==0)
		{
			alert("Please select the Expiry Days > 0");
			document.getElementsByName("strFrmExpiryDays")[0].focus();
			return false;
		}	
	
			document.forms[0].hmode.value = "SHOWRPT";
		
			if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
			{
				document.forms[0].target = "_self";
			}
			else
			{
				document.forms[0].target = "_blank";
			}
			document.forms[0].submit();
	}
	else
	{
		return false;
	}
	
}
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}

function onLoadPage(){

	document.getElementsByName("strActiveOrNearExpiry")[0].checked = true;

	document.forms[0].strItemCategoryNo.value = "0";
}

function groupNameCombo()
{
   var mode ="GROUPNAME";  
   var url="ListItemWiseSupplierRptCNT.cnt?hmode=GROUPNAME&itemCatNo="+document.forms[0].strItemCategoryNo.value;
   ajaxFunction(url,"1");
} 

function subGroupNameCombo()
{
   var mode ="SUBGROUPNAME";  
   var url="ListItemWiseSupplierRptCNT.cnt?hmode=SUBGROUPNAME&groupId="+document.forms[0].strGroupId.value+
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
		  objVal.innerHTML = "<select name ='strGroupId' class='comboMax' onChange='subGroupNameCombo();'>" + res + "</select>";
		  
        }
        subGroupNameCombo();
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
		  objVal.innerHTML = "<select name ='strItemId' class='comboMax' >" + temp[1] + "</select>";
		     
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
		    objVal.innerHTML = "<select name ='strItemId' class='comboMax' >" + res + "</select>";
		   
        }
    }
}

function getExpiryDays()
{
	if(document.getElementsByName("strActiveOrNearExpiry")[0].checked == true)
		document.getElementById("expDaysDiv").style.display = 'none';
	else
			document.getElementById("expDaysDiv").style.display = '';
}

</script>
</head>
<body onload="onLoadPage();">
<html:form action="/reports/ListItemWiseSupplierRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="listItemWiseSupplierRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="listItemWiseSupplierRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="listItemWiseSupplierRpt" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Rate Contract Details"
		selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4">Rate Contract Details</td>
		</tr>
		
		<tr style="display: none;" >
			<td class="LABEL" colspan="4">
				<input type="radio" name="strActiveOrNearExpiry"    value="1" onclick="getExpiryDays();">Active
		<input type="radio" name="strActiveOrNearExpiry" value="2" onclick="getExpiryDays();">Near Expiry
			</td>
		</tr>
		<tr style="display: none;">
		<td colspan="1" class="LABEL">
			<font color="red">*</font>Hospital Name	
		</td>
		<td colspan="3" class="CONTROL">
			<select name="strhospCmb" class="comboMax" ">
					<bean:write name="listItemWiseSupplierRpt" property="strhospCmb" filter="false" />
			</select>
		</td>
	</tr>
		
		<tr>
		<td colspan="1" class="LABEL">
			<font color="red">*</font>Item Category		
		</td>
		<td colspan="3" class="CONTROL">
			<select name="strItemCategoryNo" class="comboMax" onChange="groupNameCombo();">
					<bean:write name="listItemWiseSupplierRpt" property="strItemCategoryCombo" filter="false" />
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" class="LABEL">
			Group Name		
		</td>
		<td colspan="2" class="CONTROL">
			<div id="groupId"><select name="strGroupId" class="comboMax" onchange="subGroupNameCombo();">
					
					<option value="0">All</option>
			</select></div>
		</td>
		</tr>
		<tr style="display: none;">
		<td colspan="2" class="LABEL">
			Sub Group Name		
		</td>
		<td colspan="2" class="CONTROL">
			<div id="subGroupId" >
			<select name="strSubGroupId" class="comboNormal" onChange="itemNameCombo();">
					<option value="0">All</option>
			</select></div>
		</td>
	</tr>
	
	<tr>
		<td colspan="1" class="LABEL"><font color="red">*</font>Item Name</td>
		<td colspan="3" class="CONTROL">
			<div id="ItemId"><select name="strItemId" class="comboMax" >
					
					<option value="0">All</option>
			</select></div>
		</td>
	</tr>
		
	
<%-- Supplier Name	 --%>		
		<tr>
			<td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Supplier Name</td>
			<td width="50%" colspan="2" class="CONTROL">
			<div id="strStoreDivId" >
				<select name="strSupplierId" class="comboNormal" >
					<bean:write name="listItemWiseSupplierRpt" property="strManufactureCombo" filter="false"/>
				</select>
			</div>				
			</td>
		</tr>		
		
		
		<tr id="expDaysDiv" style="display:none;">
	        <td width="50%" colspan="2" class="LABEL"><font color="red">*</font>Expiry Days</td>			        
			<td width="50%" colspan="2" class="CONTROL">
	              <input class="txtFldNormal" type="text" name="strFrmExpiryDays" onkeypress="return validateData(event,5);" maxlength="3" >
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
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="listItemWiseSupplierRpt" value="1"></html:checkbox>
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

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>