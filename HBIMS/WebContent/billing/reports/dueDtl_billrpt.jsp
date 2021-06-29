<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head><meta charset=utf-8>
<title>Due Detail Report</title>
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
 		
 		var hisValidator = new HISValidator("dueDtlRpt");
        hisValidator.addValidation("strHospitalCode","dontselect=0","Please select a value from Hospital name Combo");
		hisValidator.addValidation("strDeptCode","dontselect=0","Please select a value from Department Combo");
		hisValidator.addValidation("strUnitCode","dontselect=0","Please select a value from Unit Combo");
		hisValidator.addValidation("strWardCode","dontselect=0","Please select a value from Ward Combo");
		
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
function getDeptCombo(obj)
{ 
 		var url;
 		url="DueDetailsRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"3");
}
function getUnitCombo(obj)
 { 
	
	  	var url ="DueDetailsRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value+"&hospCode="+document.forms[0].strHospitalCode.value;
 		ajaxFunction(url,"1");
 		
}
function getWardCombo(obj)
   { 
	     
        var url ="DueDetailsRptCNT.cnt?hmode=WARDCMB&unitCode="+obj.value+"&hospCode="+document.forms[0].strHospitalCode.value;;
 	
 		ajaxFunction(url,"2");
 		
}
	
function getAjaxResponse(res,mode){

	if(mode=="1"){
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode' class='comboNormal' onchange='getWardCombo(this);'>" + res + "</select>";
	//	getWardCombo(document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex]);
		
	  }
	 if(mode=="2"){
		var objVal= document.getElementById("wardDivID");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal' >" + res + "</select>";
		
	 }
	 if(mode=="3")
	{   
		var objVal= document.getElementById("deptDivId");
		objVal.innerHTML = "<select name ='strDeptCode' class='comboNormal' onchange='getUnitCombo(this);'>" + res + "</select>";
	}
}
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	         document.forms[0].target = "_self";
	document.forms[0].submit();

}
</script>
</head>
<body >
<html:form action="/reports/DueDetailsRptCNT.cnt" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="dueDtlRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="Due Detail Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>
                  
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
 
     <tr class="HEADER">
			<td colspan="2">Due Detail Report</td>
 	</tr>
 	
 	
 	<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this)">
			<bean:write name="dueDtlRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
		<tr>
		    <td  width="50%"class="LABEL"> <font color="red">*</font>
			  Department
			</td>
			<td   width="50%" class="CONTROL" >
			<div id="deptDivId">
		    <select name="strDeptCode" onchange="getUnitCombo(this);" class="comboNormal">
			  <bean:write  name="dueDtlRpt" property="strDeptValues" filter="false" />
			  <option value="0">Select Value</option>
			 </select>
			 </div>
		    </td>		
			
		</tr>
		
	
		<tr>
			<td  width="50%" class="LABEL"> <font color="red">*</font>Unit</td>
			
			<td  width="50%"  class="CONTROL">
			
				<div id="unitDivId">
			<select name="strUnitCode"  onchange="getWardCombo(this);" class="comboNormal">
			<option value="0">Select Value</option>
			</select>
			</div>
				
		</td>
			
		</tr>


		<tr>
			<td  width="50%" class="LABEL"><font color="red">*</font>Ward</td>
			<td  width="50%" class="CONTROL">
			<div id="wardDivID">
				<select name="strWardCode" class="comboNormal">
				<option value="0">Select Value</option></select>
				</div>
			</td>
		</tr>
		
       	<tr>
			<td  width="50%" class="LABEL">
			Report Format
			</td>
		<td width="50%" class="CONTROL">
		   <select name="strReportFormat"  onchange="">
		    <option value="html">Html</option><option value="pdf">Pdf</option></select>
		</td>
		
			
		
		
		<tr>
			<td  width="50%" class="LABEL">
			Footer Required
			</td>
			<td  width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="dueDtlRpt" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td  width="50%" class="LABEL">
			User Remarks
			</td>
			<td  width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks"  >
			</td>
			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"> <font size="2" color="red">*</font> Mandatory Fields  </td>
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
<input type="hidden" name="strCurrentDate" value="${counterFeeDtlRpt.strCurrentDate}"/>

</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>