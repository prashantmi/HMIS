
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>List of Indoor Patient</title>
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
	
		document.forms[0].hmode.value = "SHOWRPT";
		
		if(document.forms[0].strHospitalCode.value == "0"){
			alert("Please Select Hospital");
			return false;
		} 
		
		if(document.forms[0].strDeptCode.value == "0"){
			alert("Department is a mandatory field");
			return false;
		} 
		
		
		
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html"){
			
				document.forms[0].target = "_self";
			}else{
			
			document.forms[0].target = "_blank";
			}

			document.forms[0].submit();
		}
		
		
		function getUnitCombo(obj){ 

 		var url ="ListIndoorPatientRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		 		
 			ajaxFunction(url,"1");
		
		}

		function getWardCombo(obj){ 

 		var url="ListIndoorPatientRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value+"&deptCode="+document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].value;
 	
 			ajaxFunction(url,"2");
		
		}
	function getDeptCombo(obj)
	{ 
 		var url;
 		url="ListIndoorPatientRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"4");
	}
		
	function getAjaxResponse(res,mode){
	
	
	if(mode=="1"){   
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
	}
	
	if(mode=="2"){   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="4")
	{   
		var objVal= document.getElementById("deptDivId");
		objVal.innerHTML = "<select name ='strDeptCode' class='comboNormal' onchange='getUnitCombo(this);'>" + res + "</select>";
	}
}	
function displayTime(obj)
 {
 	if(obj.checked)
 	{
 		obj.value="1";
 		document.getElementById("timeDiv").style.display = "block";
 	}
 	else
 	{
 		obj.value="0";
 		document.getElementById("timeDiv").style.display = "none";
 	}
 }
 
function cancel()
 {
       	document.forms[0].hmode.value = "CANCEL";
       	 document.forms[0].target = "_self";
  	    document.forms[0].submit();
 }

</script>
</head>
<body >
<html:form action="/reports/ListIndoorPatientRptCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
	

	<tag:tab tabLabel="List of Indoor Patient"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">List of Indoor Patient</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this);">
			<bean:write name="listIndoorPatRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
		
		<tr> 
    		<td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    		<td width="50%" class="CONTROL"><div id="deptDivId">
    		<select name="strDeptCode" class="comboNormal" onchange="getUnitCombo(this);" >
        	<option value="0">Select Value</option>
        	 </select>
        	</div> </td>
  		</tr>
  		 <tr>
			<td width="50%" class="LABEL">
			Unit
			</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">All</option></select>
			</div>
			</td>
			
			</tr>
  			<tr>
			 <td width="50%" class="LABEL">Ward</td>
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" onchange="">
    		<option value="0">All</option>
          </select> </div>
     	 </td>
			</tr>
		   		
   		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat" class="comboSmall"  onchange=""><option value="html">HTML</option><option value="pdf">PDF</option></select>
			</td>
			
		</tr> 
   
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="listIndoorPatRpt" value="1"></html:checkbox>
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
			 <td colspan="2"> </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>