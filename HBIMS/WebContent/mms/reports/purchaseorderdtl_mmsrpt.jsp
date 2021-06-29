<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>PO Register</title>
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

function validate()
{

		var hisValidator = new HISValidator("purchaseOrderDtlRpt");

		//hisValidator.addValidation("strHospitalCode", "dontselect=0","Please Select Hospital Name");
		hisValidator.addValidation("strSupplierId", "dontselect=-1","Supplier Name is a mandatory field");
		hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
	     var retVal = hisValidator.validate();
	     var dd= dateDiff(document.forms[0].strFromDate.value ,document.forms[0].strToDate.value);
// alert(dd);
var dd2 = dd.split(' ')[0];
	     if( dd2> 90)
		     {
		     alert("Date difference couldn't be more than 90 days");
		    retVal= false;
		     }
	
	hisValidator.clearAllValidations();
	
	
	if(retVal)
	{
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

	function onLoadPage()
	{
		document.getElementsByName("strWhetherItemShown")[0].checked = true;
	}
	
	function onClearPage()
	{
		document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value ;
		document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value ;
	}

function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}

</script>
</head>
<body onload="onLoadPage();">
<html:form action="/reports/PurchaseOrderDtlRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="purchaseOrderDtlRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="purchaseOrderDtlRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="purchaseOrderDtlRpt" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Purchase Order Detail" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
		
		<tr class="HEADER">
			<td colspan="4">Purchase Order Detail</td>
		</tr>
		
		<tr style="display:none;">
			<td width="50%" class="LABEL" colspan="2"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL" colspan="2">
			<select name="strHospitalCode" class="comboNormal">
			<bean:write name="purchaseOrderDtlRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>	
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>Supplier Combo</td>
			<td class="CONTROL" colspan="2">
				<select name="strSupplierId" >
					<bean:write name="purchaseOrderDtlRpt" property="strSupplierCmb" filter="false" />
				</select>
			</td>
		</tr>
		
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate" value="${purchaseOrderDtlRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>To Date</td>
			<td class="CONTROL" colspan="2">
				<dateTag:date name="strToDate" value="${purchaseOrderDtlRpt.strCurrentDate}" />
			</td>
		</tr>
		
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>Whether Item has to show</td>
			<td class="CONTROL" colspan="2">
				<input type="checkbox" name="strWhetherItemShown" value="1" />
			</td>
		</tr>
		
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>PO Status</td>
			<td class="CONTROL" colspan="2">
				<select name="strPoStatus" >
					<option value="1">Active</option>
					<option value="2">Corrigendum</option>
					<option value="3">Cancelled/Rejected</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td class="LABEL" colspan="2"><font color="red">*</font>PO Type</td>
			<td class="CONTROL" colspan="2"><input type="radio" name = "strPOType" value = "1" checked/>Bulk PO<input type="radio" name = "strPOType" value = "2" />Local PO
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
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">Footer Required</td>
			<td width="50%" colspan="2" class="CONTROL">
				<html:checkbox property="strIsFooter" name="purchaseOrderDtlRpt" value="1"></html:checkbox>
			</td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">User Remarks</td>
			<td width="50%" colspan="2" class="CONTROL">
				<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
		</tr>
		
		<tr class="FOOTER">
			 <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	
	<!-- <table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="onClearPage();" >
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			</td>
			
		</tr>
	</table>-->
	<br>
<div align="center" id="">					 
					 	<a href="#" class="button" id="" onclick=' return validate();'><span class="generate">Generate</span></a>
						<a href="#" class="button"	onclick="onClearPage"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</div>
	
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${purchaseOrderDtlRpt.strCurrentDate}"/>
<input type="hidden" name="strStoreName" value="${purchaseOrderDtlRpt.strStoreName}" />

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>