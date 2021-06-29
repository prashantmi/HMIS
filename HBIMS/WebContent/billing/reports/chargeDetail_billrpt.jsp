<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Charge Detail Report</title>
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

	var hisValidator = new HISValidator("chargeDtlRpt");
// for validating a hospitl name is selected or not.Added by Anshul.
	if(document.forms[0].strHospitalCode.value == "0"){
			alert("Hospital Name is a mandatory field");
			return false;
		} 
		
	if(document.forms[0].strChargeTypeId.value == "0"){
			alert("Hospital service is mandatory.");
			return false;
		} 
		
		if(document.forms[0].strRateVal.value != "1"){
			hisValidator.addValidation("strYear", "req","Year is a mandatory field");
		}

	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	
	if(retVal){
	
		document.forms[0].hmode.value = "SHOWRPT";
		
		 var strCatCode = document.forms[0].strCategory[document.forms[0].strCategory.selectedIndex].text;
		 document.forms[0].strCatCode.value=strCatCode;
		 
		 var strMonthVal = document.forms[0].strMonth[document.forms[0].strMonth.selectedIndex].text;
		 document.forms[0].strMonthVal.value=strMonthVal;
		 
		
		 
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
		
function getGroupCombo(){
 		
  	 		var temp = document.forms[0].strChargeTypeId[document.forms[0].strChargeTypeId.selectedIndex].value ;
  	 		
  	 		if(temp.length <= 0)
  	 			temp = 0;
  	 		
 	 		
			var mode="GROUPCMB";
			var url="ChargeDetailRptCNT.cnt?hmode="+mode+"&chargeTypeId="+temp+"&hospCode="+document.forms[0].strHospitalCode.value;
			
			ajaxFunction(url,"1");
		
	}

function getAjaxResponse(res,mode){	
	
			if(mode==1){
	
			 	var objVal = document.getElementById("grpName");
				objVal.innerHTML = "<select name = 'strGroupNameId' class='comboNormal' >" + res + "</select>";	
				
			}
			if(mode=="2")
	{   
		var objVal= document.getElementById("hospSerDivId");
		objVal.innerHTML = "<select name ='strHospSerName' class='comboNormal'>" + res + "</select>";
	}
		}
		
		
		
function getClick(obj){
		if(obj.checked == false){
				document.getElementById("monyearDivId").style.display = "block";
				document.forms[0].strRateVal.value = "0";
			}else{
				document.getElementById("monyearDivId").style.display = "none";
				document.forms[0].strRateVal.value = "1";
			}
		}	

function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].submit();

}

function valueBlank(){
	document.forms[0].strCurrentRate.checked = true;

	//for(var i = 0; i<document.forms[0].strMonth.length; i++){
	
	//if(document.forms[0].strMonth[i].text == "${chargeDtlRpt.strCurrentDate}".split("-")[0])
	//		document.forms[0].strMonth[i].checked = true;
	//}
	//document.forms[0].strYear.value = "${chargeDtlRpt.strCurrentDate}".split("-")[1];
}
</script>
</head>
<body onload="getGroupCombo(); valueBlank();">
<html:form action="/reports/ChargeDetailRptCNT.cnt" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="chargeDtlRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="Charge Detail Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="2">Charge Detail Report</td>
		</tr>
	
	
	
		<!--  to populate the hospital names in the combo  Added by Anshul-->
	
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal">
			<bean:write name="chargeDtlRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
 	 	
		
		
		
        
    <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Hospital Service</td>
    <td width="50%" class="CONTROL">
     <div id="hospSerDivId">
    <select name="strChargeTypeId" class="comboNormal" onchange="getGroupCombo(this);" >
           <bean:write name="chargeDtlRpt" property="strHospSerValues" filter="false"/>
          </select> 
           </div> </td>

        
  </tr>
    
    
    
		
		
		<tr> 
    		<td  width="50%" class="LABEL">Patient Category</td>
    		<td  width="50%" class="CONTROL">
    		<select name="strCategory"  class="comboNormal">
        	<bean:write name="chargeDtlRpt" property="strCategoryValues" filter="false"/></select></td>
  		</tr>
  		
  		 <tr> 
   			 <td width="50%" class="LABEL">Group Name</td>
   			 <td width="50%" class="CONTROL">
   			 <div id="grpName">
   			 <select name="strGroupNameId" class="comboNormal" >
       		 <option value="0">All</option></select> 
       		 </div>
       		 </td>
 		 </tr>
  		
  		
  		<tr>
			<td width="50%" colspan="1" class="LABEL">
			Current Rate
			</td>
			<td width="50%" colspan="1" class="CONTROL">
			<html:checkbox property="strCurrentRate" name="chargeDtlRpt" value="1" onclick="getClick(this);"></html:checkbox>
			</td>
			
		</tr>
  	
  		</table>
  		
  		<div id="monyearDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
		
			<tr> 
    		<td width="25%" class="LABEL"><font color="red">*</font>Month/Year</td>
    		<td width="25%" class="CONTROL">
			<select name="strMonth"  >
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
			      
        	</select> /			
			<input class="txtFldMin" type="text" name="strYear" maxlength="4" 
							onkeypress="return validateData(event,5)">
				</td>
			</tr>
  		</table></div>
  		
  		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
			<select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="chargeDtlRpt" value="1"></html:checkbox>
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
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${chargeDtlRpt.strCurrentDate}"/>
<input type="hidden" name="strCatCode" value=""/>
<input type="hidden" name="strMonthVal" value=""/>
<input type="hidden" name="strRateVal" value="1"/>



</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>