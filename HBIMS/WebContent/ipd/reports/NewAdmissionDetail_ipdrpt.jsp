   <!-- 
   author : Debashis Sardar 
    
   New Admission Detail User Wise JSP 
    
   Date: 30/12/2011 
   -->
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>New Admission Detail User Wise</title>
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

var hisValidator = new HISValidator("NewAdmissionDetailRpt");

    hisValidator.addValidation("strClerk", "dontselect=0","Please Select Clerk Name");
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if(retVal){
	
		document.forms[0].strClerkSelected.value = document.forms[0].strClerk[document.forms[0].strClerk.selectedIndex].text;
	   document.forms[0].strUserId.value=document.forms[0].strClerk[document.forms[0].strClerk.selectedIndex].value;
	   
	   
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

function cancel()
 {
       	document.forms[0].hmode.value = "CANCEL";
       	   document.forms[0].target = "_self";
  	    document.forms[0].submit();
 }
function clearForm()
{
        document.forms[0].hmode.value = "CLEAR";
  	    document.forms[0].submit();
}
	
</script>
</head>
<body >


<html:form action="/reports/NewAdmissionDetailRptCNT.cnt" method="post">
		
	<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="New Admission Detail User Wise"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center">   
	<tr class="HEADER">
			<td colspan="2">New Admission Detail User Wise</td>
		</tr>
		
  <tr>
			 <td width="50%" class="LABEL"><font color="red">*</font>Clerk Name</td>
			 <td width="50%" class ="CONTROL"><div id ="clerkDivId">
			 <select name="strClerk" class="comboNormal" >
    		<bean:write name="NewAdmissionDetailRpt" property="strClerkList" filter="false"/>
          </select> </div>
      </td>
		</tr>
  <tr>
			<td class="LABEL"><font color="red">*</font> From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${NewAdmissionDetailRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> To Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${NewAdmissionDetailRpt.strCurrentDate}" /></td>
		</tr>
		
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
			<select name="strReportFormat" class='comboSmall'  onchange="">
			<option value="html">HTML</option><option value="pdf">PDF</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="NewAdmissionDetailRpt" value="1"></html:checkbox>
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
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center">
			<img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
				<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();clearForm();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strClerkSelected" value=""/>  
<input type="hidden" name="strCurrentDate" value="${NewAdmissionDetailRpt.strCurrentDate}"/>
<input type="hidden" name="strUserId" />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>