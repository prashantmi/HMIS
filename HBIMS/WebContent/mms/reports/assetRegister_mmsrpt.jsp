<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Asset Register Report</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function validate(){

	document.forms[0].strItemCatName.value    = document.forms[0].strItemCatId[document.forms[0].strItemCatId.selectedIndex].text;
	
		document.forms[0].hmode.value = "SHOWRPT";
		
			if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
			{
				document.forms[0].target = "_self";
			}else{
				document.forms[0].target = "_blank";
			}
		document.forms[0].submit();
		
	}

	
	
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}

function onLoadPage(){

	document.forms[0].strItemCatNo.value="0";

}

</script>
</head>
<body onload="onLoadPage();">
<html:form action="/reports/AssetRegisterRptCNT" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="assetRegisterRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="assetRegisterRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="assetRegisterRpt" property="strWarningMsg"/></div>


	<tag:tab tabLabel="Asset Register Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4"></td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">Category Name</td>
			<td width="50%" colspan="2" class="CONTROL">
				<select name="strItemCatId" class="comboNormal" >
					<bean:write name="assetRegisterRpt" property="strItemCatVal" filter="false" />
				</select>
			</td>
		</tr>
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Stock Status
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strStockStatus"  onchange="">
			<option value="0">All</option>
			<option value="10">Active</option>
			<option value="13">Dead</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="assetRegisterRpt" value="1"></html:checkbox>
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
			 <td colspan="4"><font color='red'>*</font>Mandatory Field </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strItemCatName" value="${assetRegisterRpt.strItemCatName}" />

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>