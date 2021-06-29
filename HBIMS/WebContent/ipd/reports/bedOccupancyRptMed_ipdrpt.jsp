<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>Bed Occupancy Report</title>
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
var hisValidator = new HISValidator("bedOccupancyRpt");
	
	if(document.forms[0].strDeptCode.value == "0"){
			alert("Department is a mandatory field");
			return false;
	}
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
	var dateDifference = dateDiff(document.forms[0].strEffectiveFrom.value,document.forms[0].strEffectiveTo.value);
	 if(parseInt(dateDifference) > 30)
	{
				alert("Date Difference should not be greater than 30 days");
				return false;
	} 
	
	
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

function getCombo(obj){

	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			break;
		}
		
	}
	

	if(obj.value == 1){
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "none";
	document.getElementById("wardDivId").style.display = "none";
	}else if(obj.value == 2){
	document.getElementById("wardDivId").style.display = "";
	document.getElementById("unitDivId").style.display = "";
	document.getElementById("deptDivId").style.display = "";
	}
	
}

function getUnitCombo(obj){ 

		var url ="AvgStayDepartmentRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		ajaxFunction(url,"1");
		
	}

function getWardCombo(obj){ 

 		var url="AvgStayDepartmentRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
 		ajaxFunction(url,"2");
		
	}

function getAjaxResponse(res,mode){
	
	if(mode=="1"){   
			var objVal= document.getElementById("unitId");
			objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
			objVal= document.getElementById("wardId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";

	}
	
	if(mode=="2"){   
		var objVal= document.getElementById("wardId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal' >" + res + "</select>";
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
<body onload="getCombo(document.forms[0].strCase);" >
<html:form action="/reports/BedOccupancyRptCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
	

	<tag:tab tabLabel="Bed Occupancy Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>
                  
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	<tr class="HEADER">
			<td colspan="2">Bed Occupancy Report</td>
		</tr>
		
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    <html:radio property="strCase" name="bedOccupancyRpt" value="1" onclick="getCombo(this);" >Department</html:radio>
    <html:radio property="strCase" name="bedOccupancyRpt" value="2" onclick="getCombo(this);" >Ward</html:radio>
    </div>
    </td>
    
    </tr>
		 </table>
    
    <div id="deptDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" >  
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    <td width="50%" class="CONTROL"><select name="strDeptCode" onchange="getUnitCombo(this);" class="comboNormal" >
        <bean:write name="bedOccupancyRpt" property="strDeptValues" filter="false"/>
        </select></td>
  </tr>
  </table>
  </div>
  
  <div id="unitDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  <tr> 
    <td width="50%" class="LABEL">Unit</td>
    <td width="50%" class="CONTROL">
    <div Id = "unitId">
    <select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
    <option value="0">All</option>
	</select>
	</div>
	</td>
  </tr>
  </table>
  </div>
  
  	<div id="wardDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" >  
  
  	<tr> 
    <td width="50%" class="LABEL">Ward </td>
    <td width="50%" class="CONTROL"><div id ="wardId"><select name="strWardCode"  class="comboNormal" >
       <option value="0">All</option>
        </select>
        </div>
        </td>
  	</tr>
  	<tr>
			<td width="50%" class="LABEL">Whether Unit has to display</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strUnitReq" name="bedOccupancyRpt" value="1"></html:checkbox>
			</td>
	</tr>
  	
	</table>
	</div>
   	 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
   	  	 
   		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${bedOccupancyRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${bedOccupancyRpt.strCurrentDate}" /></td>
		</tr>
	</table>
		 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		
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
			<html:checkbox property="strIsFooter" name="bedOccupancyRpt" value="1"></html:checkbox>
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
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${bedOccupancyRpt.strCurrentDate}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>