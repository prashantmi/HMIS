<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>Patient Diagnosis Report</title>
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

function getValues(obj){

if(obj.value == 1){
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("roomDivId").style.display = "block";
	document.getElementById("dateDivId").style.display = "none";
	}else if(obj.value == 2){
	document.getElementById("deptDivId").style.display = "none";
	document.getElementById("unitDivId").style.display = "none";
	document.getElementById("wardDivId").style.display = "none";
	document.getElementById("roomDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	}else if(obj.value == 3){
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("roomDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "none";
	}else if(obj.value == 4){
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("roomDivId").style.display = "block";
	document.getElementById("dateDivId").style.display = "none";
	}

}



function validate(){
	
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html"){
			
				document.forms[0].target = "_self";
		}else{
			
			document.forms[0].target = "_blank";
		}

		document.forms[0].submit();
	}	


	function getUnitCombo(obj){ 

 		var url ="PatientDiagnosisRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		 		
 			ajaxFunction(url,"1");
		
	}

function getWardCombo(obj){ 

 		var url="PatientDiagnosisRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
 	
 			ajaxFunction(url,"2");
		
	}
	
	function getRoomCombo(obj){ 

 		var url;
 		
  			url="PatientDiagnosisRptCNT.cnt?hmode=ROOMCMB&wardCode="+obj.value+"&deptunitCode="+document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex].value;
 			ajaxFunction(url,"3");
		
	}

function getAjaxResponse(res,mode){
	
		alert(res);
	if(mode=="1"){   
		var objVal= document.getElementById("unitId");
		objVal.innerHTML = "<select name ='strUnitCode' class='comboNormal' onChange ='getWardCombo(this);'>" + res + "</select>";
	}
	
	if(mode=="2"){   
		var objVal= document.getElementById("wardId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal' onChange ='getRoomCombo(this);'>" + res + "</select>";
	}
	
	if(mode=="3"){   
		var objVal= document.getElementById("roomId");
		objVal.innerHTML = "<select name ='strRoomNo' class='comboNormal' >" + res + "</select>";
	}
}



</script>
</head>
<body >
<html:form action="/reports/PatientDiagnosisRptCNT.cnt" method="post">
	<div class="normalMsg" id="normalMsg"></div>

	<center>
	<tag:tab tabLabel="Patient Diagnosis" selectedTab="FIRST" align="center" width="85%">
	</tag:tab>
	</center>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">Patient Diagnosis</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
   
    <html:radio property="strCase" name="patDiagnosisRpt" value="1" onclick="getValues(this);" >Only Admitted</html:radio>
    <html:radio property="strCase" name="patDiagnosisRpt" value="2" onclick="getValues(this);" >Also For Discharge</html:radio>
    </div>
    </td>
    </tr>
    <tr >
    <td class="LABEL" colspan="2">
    <div align="right">
   
    <html:radio property="strCase" name="patDiagnosisRpt" value="3" onclick="getValues(this);" >Ward</html:radio>
    <html:radio property="strCase" name="patDiagnosisRpt" value="4" onclick="getValues(this);" >Room</html:radio>
    </div>
    </td>
    </tr>
    </table>
    
     <div id="deptDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    <td width="50%" class="CONTROL"><select name="strDeptCode"  class="comboNormal" >
        <bean:write name="patDiagnosisRpt" property="strDeptValues" filter="false"/>
        <option value ="0">Select Value</option></select></td>
  </tr>
  </table>
  </div>
  <div id="unitDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Unit</td>
    <td width="50%" class="CONTROL"><div Id ="unitId"><select name="strUnitCode" class="comboNormal" onchange="getWardCombo();">
			<option value="0">Select Value</option></select>
			</div>
			</td>
  </tr>
  </table>
  </div>
  
   <div id="wardDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  
  	<tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Ward Name</td>
    <td width="50%" class="CONTROL"><div Id = "wardId"><select name="strWardCode" class="comboNormal" onchange="">
    		<option value ="0">Select Value</option>
          </select>
          </div>
          </td>
  	</tr>
	</table>
	</div>
	 <div id="roomDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  
  	<tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Room No</td>
    <td width="50%" class="CONTROL"><div Id = "roomId"><select name="strRoomNo" class="comboNormal" onchange="">
    		<option value ="0">Select Value</option>
          </select>
          </div>
          </td>
  	</tr>
	</table>
	</div>
  
     <div id="wardDivId" style="display:none">
     	
     </div>
       
      <div id="dateDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr>
			<td class="LABEL"><font color="red">*</font> Effective From</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="" /></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Effective To</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="" /></td>
		</tr>
		
  </table>
  </div>
    
    
    	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
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
			<html:checkbox property="strIsFooter" name="patDiagnosisRpt" value="1"></html:checkbox>
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