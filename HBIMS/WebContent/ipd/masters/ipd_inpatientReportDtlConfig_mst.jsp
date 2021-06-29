<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<head><meta charset="utf-8" />
<title>In Patient Configuration</title>

<script type="text/javascript">
			
function validate1(){	
	
	retVal = false;
	var hisValidator = new HISValidator("inPatientConfigBean");
	
	hisValidator.addValidation("strDischargeSummaryReportAdvice", "req","Discharge Summary Report Advice is a Mandatory Field");
	hisValidator.addValidation("strDischargeSummaryReportAdvice", "maxlen=500","Discharge Summary Report should contain Less than 500 Characters");
	hisValidator.addValidation("strDischargeSummaryReportFooter", "req","Discharge Summary Report Footer is a Mandatory Field");
	hisValidator.addValidation("strDischargeSummaryReportFooter", "maxlen=200","Discharge Summary Report Footer should contain Less than 200 Characters");		

	retVal = hisValidator.validate(); 
	
		if(retVal){
				
				document.forms[0].selectedTab.value = "RPTDTLSAVE";
				document.forms[0].submit();
				
		}else{
			hisValidator.clearAllValidations();
		return false;
		}
	}
	
	function cancelFunction(){
	document.forms[0].selectedTab.value = "INITIALPAGE";
				document.forms[0].submit();		
		
		}
	
	</script>

</head>

<body>
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post" >

<div class="normalMsg"><bean:write name="inPatientConfigBean"
	property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="FIRST"
	align="center" width="TABLEWIDTH"></tag:tab> <tag:tab
	tabList="${inPatientConfigBean.lhm}" selectedTab="ipdreportdtls"
	align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">

	<tr class="HEADER">
		<td colspan="4" height="22">In Patient Configuration</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL" colspan="2"><font color="red">*</font>Discharge
		Summary Report Advice</td>
		<td width="50%" class="CONTROL" colspan="2"><textarea
			name="strDischargeSummaryReportAdvice" rows="4" cols="40"><bean:write
			name="inPatientConfigBean" property="strDischargeSummaryReportAdvice" /></textarea>
		</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL" colspan="2"><font color="red">*</font>Discharge
		Summary Report Footer</td>
		<td width="50%" class="CONTROL" colspan="2"><textarea
			name="strDischargeSummaryReportFooter" rows="2" cols="40"><bean:write
			name="inPatientConfigBean" property="strDischargeSummaryReportFooter" /></textarea>
		</td>
	</tr>
	<tr class="FOOTER">
		<td colspan="4"><font size="2" color="red">*</font> Mandatory
		Fields
	</tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>

		<!-- <td align="right"><img src="../../hisglobal/images/btn-sv.png"
			style="cursor:pointer" onClick=" return validate1();" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			style="cursor:pointer" onClick="cancelFunc();" /></td>
			 -->
			<td align="right">
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</td>
	</tr>
</table>

<input type="hidden" name="selectedTab"></html:form>
<tag:autoIndex></tag:autoIndex>
</body>

</html>