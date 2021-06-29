<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Income Comparative Statement Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">

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
	
	var hisValidator = new HISValidator("incomeCorpStatRpt");
	
	var thisYear = new Date().getFullYear(); 
	
		if(document.forms[0].strCase[0].checked == true){
			
			hisValidator.addValidation("strFromYear", "req", "From Year is a Mandatory Field");
			hisValidator.addValidation("strFromYear", "minlen=4", "From Year must be a 4 digit Number");
			hisValidator.addValidation("strToYear", "req", "To Year is a Mandatory Field");
			hisValidator.addValidation("strToYear", "minlen=4", "To Year must be a 4 digit Number");	
			
			hisValidator.addValidation("strFromYear", "numltet="+document.forms[0].strToYear.value, "From Year must be Less than or Equal to To Year");
			hisValidator.addValidation("strToYear", "numltet="+thisYear, "To Year must be Less than or Equal to Current Year");
						
		}else{
		
			hisValidator.addValidation("strFromMonthYear", "req", "From Year is a Mandatory Field");
			hisValidator.addValidation("strFromMonthYear", "minlen=4", "From Year must be a 4 digit Number");
			hisValidator.addValidation("strToMonthYear", "req", "To Year is a Mandatory Field");
			hisValidator.addValidation("strToMonthYear", "minlen=4", "To Year must be a 4 digit Number");	
			
			hisValidator.addValidation("strFromMonthYear", "numltet="+document.forms[0].strToMonthYear.value, "From Year must be Less than or Equal to To Year");
			hisValidator.addValidation("strToMonthYear", "numltet="+thisYear, "To Year must be Less than or Equal to Current Year");
			
			hisValidator.addValidation("strFromMonth", "numltet="+document.forms[0].strToMonth.value, "From Month must be Less than or Equal to To Month");
					
		
		}
	
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
	
	function getClick(obj){
		if(obj.checked){
				document.forms[0].strGraph.value = '1';
			}
	}
	
function getCombo(obj){


	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			//document.forms[0].reset();
			break;
		}
		
	}


document.forms[0].strGraph.value = '1';

	if(obj.value == 1){
	
	document.getElementById("yearDivId").style.display = "block";
	document.getElementById("monthDivId").style.display = "none";
	
	}else{
	
	document.getElementById("monthDivId").style.display = "block";
	document.getElementById("yearDivId").style.display = "none";
		}
	}
	
	function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	         document.forms[0].target = "_self";
	document.forms[0].submit();

}
</script>
</head>
<body onload="getCombo(document.forms[0].strCase);">
<html:form action="/reports/IncomeCorpStatementRptCNT.cnt" method="post">
	

	<tag:tab tabLabel="Income Comparative Statement"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="4">Income Comparative Statement</td>
		</tr>
		
		<tr >
    <td class="LABEL" colspan="4">
    <div align="right">
   	<html:radio property="strCase" name="incomeCorpStatRpt" value="1" onclick="getCombo(this);" >Year Wise</html:radio>
    <html:radio property="strCase" name="incomeCorpStatRpt" value="2" onclick="getCombo(this);" >Month Wise</html:radio>
  
    </div>
    </td>
 	 </tr>
		 <tr> 
    <td width="50%" colspan="2" class="LABEL">Hospital Service</td>
    <td width="50%" colspan="2" class="CONTROL"><select name="strHospSerName" class="comboNormal" >
        <bean:write name="incomeCorpStatRpt" property="strHospSerValues" filter="false"/></select> </td>
  </tr>
  </table>
		
		<div id="yearDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
		
		<tr> 
    		<td colspan="1" class="LABEL"><font color="red">*</font>From Year</td>
    		<td width="25%" colspan="1" class="CONTROL">
			<input class="txtFldMin" type="text" name="strFromYear" maxlength="4" onkeypress="return validateData(event,5)">
			</td>
        	<td colspan="1" class="LABEL"><font color="red">*</font>To Year</td>
        	<td width="25%" colspan="1" class="CONTROL">
			<input class="txtFldMin" type="text" name="strToYear" maxlength="4" onkeypress="return validateData(event,5)">
			</td>
  		</tr>
	</table>
	</div>	
	
	<div id="monthDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
		
			<tr> 
    		<td width="25%" class="LABEL"><font color="red">*</font>From Month</td>
    		<td width="25%" class="CONTROL">
			<select name="strFromMonth"  >
			     <option value="1">Jan</option> 
			     <option value="2">Feb</option>  
			     <option value="3">Mar</option>  
			     <option value="4">Apr</option>  
			     <option value="5">May</option>  
			     <option value="6">Jun</option>  
			     <option value="7">Jul</option>  
			     <option value="8">Aug</option>  
			     <option value="9">Sep</option>  
			     <option value="10">Oct</option>  
			     <option value="11">Nov</option>  
			     <option value="12">Dec</option>  
			      
        	</select> 
			
			<input class="txtFldMin" type="text" name="strFromMonthYear" maxlength="4" onkeypress="return validateData(event,5)">
			</td>
			<td width="25%" class="LABEL"><font color="red">*</font>To Month</td>
        	<td width="25%"  class="CONTROL">
			<select name="strToMonth"  >
				 <option value="1">Jan</option> 
			     <option value="2">Feb</option>  
			     <option value="3">Mar</option>  
			     <option value="4">Apr</option>  
			     <option value="5">May</option>  
			     <option value="6">Jun</option>  
			     <option value="7">Jul</option>  
			     <option value="8">Aug</option>  
			     <option value="9">Sep</option>  
			     <option value="10">Oct</option>  
			     <option value="11">Nov</option>  
			     <option value="12">Dec</option>		
			</select>
			
			<input class="txtFldMin" type="text" name="strToMonthYear" maxlength="4" onkeypress="return validateData(event,5)">
			</td>
			</tr>
  		</table></div>
	
	
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
	
	<tr>
			<td width="50%" colspan="2" class="LABEL">
			Graph Generate
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strGraph" name="incomeCorpStatRpt" onclick="getClick(this);"></html:checkbox>
			</td>
			
		</tr>
	
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			<font color="red">*</font>Report Format
			</td>
			<td width="50%" colspan="2" class="CONTROL">
		<select name="strReportFormat"  onchange=""><option value="html">Html</option><option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" colspan="2" class="LABEL">
			Footer Required
			</td>
			<td width="50%" colspan="2" class="CONTROL">
			<html:checkbox property="strIsFooter" name="incomeCorpStatRpt" value="1"></html:checkbox>
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
			 <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>