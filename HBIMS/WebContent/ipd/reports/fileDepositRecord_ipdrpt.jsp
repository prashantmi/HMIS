<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>File Deposit Record Report</title>
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
	
		document.forms[0].hmode.value = "SHOWRPT";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
}

function getUnitCombo(obj){ 

 		var url ="FileDepositRecordRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		ajaxFunction(url,"1");
		
	}

function getWardCombo(obj){ 

 		var url="FileDepositRecordRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
 		
 		ajaxFunction(url,"2");
		
	}

	function getAjaxResponse(res,mode){
	
	
	
	if(mode=="1"){   
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode' onChange ='getWardCombo(this);'>" + res + "</select>";
	}
	
	if(mode=="2"){   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'>" + res + "</select>";
	}
}	



</script>
</head>
<body >
<html:form action="/reports/FileDepositRecordRptCNT.cnt" method="post">

	<div class="normalMsg" id="normalMsg"></div>

	<center>
	<tag:tab tabLabel="File Deposit Record" selectedTab="FIRST" align="center" width="85%">
	</tag:tab>
	</center>

	<table class="TABLEWIDTH" align="center">   
	<tr class="HEADER">
			<td colspan="2">File Deposit Record</td>
		</tr>
		<tr> 
    		<td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    		<td width="50%" class="CONTROL"><select name="strDeptCode"  onchange="getUnitCombo(this);" >
        	<bean:write name="fileDepRecordRpt" property="strDeptValues" filter="false"/> </select> </td>
  		</tr>
  		 <tr>
			<td width="50%" class="LABEL">
			<font color="red">*</font>Unit
			</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">Select Value</option></select>
			</div>
			</td>
			
		</tr>
  		<tr>
			 <td width="50%" class="LABEL"><font color="red">*</font>Ward Name</td>
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" onchange="">
    		<option value ="0">Select Value</option>
          </select> </div>
      </td>
		</tr>
   <tr>
			<td  class="LABEL"><font color="red">*</font> Effective From</td>
			<td  class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${fileDepRecordRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td  class="LABEL"><font color="red">*</font> Effective To</td>
			<td  class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${fileDepRecordRpt.strCurrentDate}" /></td>
		</tr>
		
		<tr>
			<td width="50%" class="LABEL">
			<font color="red">*</font>Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat"  onchange=""><option value="html">Html</option><option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="fileDepRecordRpt" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			User Remarks
			</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img src="../../hisglobal/images/btn-clr.png" onClick="" >
			<img src="../../hisglobal/images/btn-ccl.png" onClick="" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>