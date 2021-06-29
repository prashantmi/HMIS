<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Consolidated Discount Detail Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
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
	var hisValidator = new HISValidator("billCancelLogDtlRpt");
	if(document.forms[0].strHospitalCode.value == "0"){
			alert("Hospital Name is a mandatory field");
			return false;
		} 
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Date : "+document.forms[0].strCurrentDate.value);
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();

	if(retVal){
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html"){
			
			document.forms[0].target = "_self";
		}else{
			
			document.forms[0].target = "_blank";
		}

		document.forms[0].submit();
		}else{
		return false;
		}
	}


/*function getCombo(obj){

	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			break;
		}
		
	}

	if(obj.value == 1){
	document.getElementById("treatcatDivId").style.display = "block";
	document.getElementById("dateDivId").style.display = "block";
	document.getElementById("grpWiseDivId").style.display = "none";
	document.getElementById("trfWiseDivId").style.display = "none";
	}else if(obj.value == 2){
	document.getElementById("treatcatDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	document.getElementById("grpWiseDivId").style.display = "none";
	document.getElementById("trfWiseDivId").style.display = "none";
	}else if(obj.value == 3){
	document.getElementById("treatcatDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	document.getElementById("grpWiseDivId").style.display = "block";
	document.getElementById("trfWiseDivId").style.display = "none";
	}else if(obj.value == 4){
	document.getElementById("treatcatDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	document.getElementById("grpWiseDivId").style.display = "none";
	document.getElementById("trfWiseDivId").style.display = "block";
	}
	
}*/
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}


function getCombo(obj)
{	
	for(var i = 0 ; i < obj.length ; i++)
	{	
		if(obj[i].checked)
		{
			obj = obj[i];
			break;
		}		
	}	

	var feeClerkDiv=document.getElementById("feeClerkDiv");
	var feeClerkNameTr=document.getElementById("feeClerkNameTr");
	if(obj.value == 1)
		{
	   		feeClerkDiv.style.display="none";
	   		feeClerkNameTr.style.display="none";
		}
	else
		{
			feeClerkDiv.style.display="";
			feeClerkNameTr.style.display="";
		}
	
}





</script>
</head>
<body onload="document.forms[0].strCase.value=1">
<html:form action="/reports/BillCancellationLogDetailRptCNT.cnt" method="post">
	<div class="errMsg" id="errMsg"><bean:write name="billCancelLogDtlRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="Bill Cancellation Log Detail"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">Bill Cancellation Log Detail</td>
		</tr>
 	 </table>
  

  
  
 	 <div id="dateDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		 	 <tr>
  				<td class="LABEL" colspan="2">
  					<div align="right">
  						<html:radio property="strCase" name="billCancelLogDtlRpt" value="1" onclick="getCombo(this);" >Date</html:radio>
  						<html:radio property="strCase" name="billCancelLogDtlRpt" value="2" onclick="getCombo(this);" >Cashier</html:radio>
  			
  					</div>
  				</td>	
 		    </tr>
  			<tr>
				<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
				<td width="50%" class="CONTROL">
				<select name="strHospitalCode" class="comboNormal" >
				<bean:write name="billCancelLogDtlRpt" property="strHospNameValues" filter="false"/>
				</select>
				</td>	
			</tr>
		<tr>
				<td width="5%" class="LABEL" id="feeClerkNameTr" style="display:none";>Fee Clerk Name</td>
				<td width="5%" class="CONTROL">
		
			   <div id="feeClerkDiv" style="display:none;">
				<select name="strFeeClerkName" class="comboNormal">
						 <bean:write name="billCancelLogDtlRpt" property="strFeeClekNameValues" filter="false"/> 
				</select>
		         </div>
		   </td>
		   <td colspan="2" width="5%" class="LABEL"></td>
		</tr>
 	 	
 	 	
  <tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${billCancelLogDtlRpt.strCurrentDate}" /></td>
				
			
			
			
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${billCancelLogDtlRpt.strCurrentDate}" /></td>
				
				
		</tr>
  </table>
  </div>
 	 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat"  onchange=""><option value="html">Html</option>
		<option value="pdf">Pdf</option>
		<option value="excel">Excel</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="billCancelLogDtlRpt" value="1"></html:checkbox>
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
			<!-- <img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			 -->
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${billCancelLogDtlRpt.strCurrentDate}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>