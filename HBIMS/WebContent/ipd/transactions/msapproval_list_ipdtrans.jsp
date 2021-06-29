<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


  <%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
  <%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
  <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
  <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String file_path=request.getParameter("filePath");
if(file_path==null)
	file_path="def";

%>
<html>
<head>
<meta charset=utf-8>
<title>List Generation</title>
<link href="../../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script type="text/javascript">

function getResult(obj){
if(obj.value == 1){
document.getElementById("listDivId").style.display = "none";
}else{
document.getElementById("listDivId").style.display = "block";
}
}

function getCombo(obj){
	
	
	if(obj.value == 1){
	
	document.getElementById("wardAllotListDivId").style.display = "none";
	document.getElementById("listDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "none";
	
	}else if(obj.value == 2){
	
	document.getElementById("wardAllotListDivId").style.display = "block";
	document.getElementById("listDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "none";
	
	}else if(obj.value == 3){
	
	document.getElementById("wardAllotListDivId").style.display = "none";
	document.getElementById("listDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	
	}
	else if(obj.value == 4){
	
	document.getElementById("wardAllotListDivId").style.display = "none";
	document.getElementById("listDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	
	}
}
	
	function validate(){
/*	
	var hisValidator = new HISValidator("msApprovalTransBean");
	
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	var retVal = hisValidator.validate(); 
	
	
	if(retVal){
	*/
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat.value=="html")
		{
			document.forms[0].target = "_self";
		}
		else
		{
			document.forms[0].target = "_blank";
		}
		document.forms[0].submit();
	/*	}else{
		return false;
		}*/
}

	function cancel(mode)
	{

		document.forms[0].hmode.value = "LIST";
		document.forms[0].target = "_self";
		document.forms[0].submit();
}



</script>
</head>

<body>
<html:form action="/transactions/MsApprovalTransCNT" method="post">	
<tag:tab tabLabel="List Generation" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"></tag:tab>
<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
	<tr class="HEADER"><td >List Generation</td></tr>
	<tr>
    <td class="LABEL" >
    <div align="right">
    <html:radio property="strCase" name="msApprovalTransBean" value="1" onclick="getCombo(this);" >Approved but ward not allotted</html:radio>
    <html:radio property="strCase" name="msApprovalTransBean" value="2" onclick="getCombo(this);" >Ward Allotted List</html:radio>
    <html:radio property="strCase" name="msApprovalTransBean" value="3" onclick="getCombo(this);" >Rejected List</html:radio>
    <html:radio property="strCase" name="msApprovalTransBean" value="4" onclick="getCombo(this);" >Cancel List</html:radio>
     </div>
    </td>
    </tr>
</table>  
  <div id="wardAllotListDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >
		<tr> 
		<td class="LABEL" >
   		 <div align="center">
    		<html:radio property="strWardAllotChk" name="msApprovalTransBean" value="1" onclick="getResult(this);" >New List(Current Date)</html:radio>
    		<html:radio property="strWardAllotChk" name="msApprovalTransBean" value="2" onclick="getResult(this);" >Duplicate List(Last 30 Days)</html:radio>
 		 </div>
 		 </td>
  		</tr>
		</table>
	</div>	
	<div id="listDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">		
		<tr>
			<td width="50%" class="LABEL">List Id</td>
			<td width="50%" class="CONTROL">
			<select name="strListNo" class="comboMax" >
			<bean:write name="msApprovalTransBean" property="strListValues" filter="false"/> 
			</select>
			</td>
			</tr>
		</table>
		</div>		
		<div id="dateDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">  
  		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>From Date</td>
			<td width="50%" class="CONTROL"><date:date name="strEffectiveFrom" value ="${msApprovalTransBean.strCurrentDate}"></date:date></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="50%" class="CONTROL"><date:date name="strEffectiveTo" value ="${msApprovalTransBean.strCurrentDate}"></date:date></td>
		</tr>
  </table>
  </div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
		<tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat"  onchange=""><option value="html">Html</option><option value="pdf">Pdf</option></select>
			</td>			
		</tr> 		
		<tr>
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="msApprovalTransBean" value="1"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">User Remarks</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font>Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png"  onclick="cancel('LIST');">
			 -->
			 <br>
			<a href="#" class="button"	onClick="return validate();"><span class="generate">Generate</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			</td>			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${msApprovalTransBean.strCurrentDate}"/>
</html:form>
</body>
<tag:autoIndex></tag:autoIndex>
</html>