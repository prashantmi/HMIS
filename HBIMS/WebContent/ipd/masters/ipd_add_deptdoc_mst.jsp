<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset="utf-8" />
<title>Department Document Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">
function ajaxDeptDoc(obj)
{ 
   var mode ="DEPTDOCVALUE";  
   var url="CNTDeptDocumentMst.cnt?hmode=DEPTDOCVALUE&deptName="+document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].value+"&docName="+document.forms[0].strDocumentCode[document.forms[0].strDocumentCode.selectedIndex].value;
   ajaxFunction(url,"1");
}
function getAjaxResponse(res,mode)
{
	var objVal;
	if(mode=="1")
	{				
		objVal= document.getElementById("addedcompId");
		objVal.innerHTML =  res ;
	}
}
function showDetails(divId)
{
            document.getElementById(divId).style.display="block";                     
            document.getElementById('minus'+divId).style.display="block";
            document.getElementById('plus'+divId).style.display="none";
}
function hideDetails(divId)
{
			document.getElementById(divId).style.display="none";
            document.getElementById('minus'+divId).style.display="none";
            document.getElementById('plus'+divId).style.display="block";
}
function validate1()
{
	var hisValidator = new HISValidator("deptdocBean"); 
	var retCheck = false;
	hisValidator.addValidation("strDeptCode","dontselect=0","Please select a value from Deparment Combo");
	//hisValidator.addValidation("strDeptCode","req","First select the Deparment Combo");
	hisValidator.addValidation("strDocumentCode","dontselect=0","Please select a value from Document Combo");
	hisValidator.addValidation("strComponentCode","dontselect=0","Please select a value from Component Combo");
	hisValidator.addValidation("strComponentFileName","req","File Name should not be blank");
	hisValidator.addValidation("strComponentFileName","maxlen=50","File Name should not be greater 25 characters");	
	hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not be greater than 100 characters");
	retCheck = hisValidator.validate();
		
	if(retCheck)
	{
		retCheck=checkMultirow('strComponentFileName','File Name Already Entered in MultiRow');
	}
	if(retCheck)
	{
		retCheck = checkDataExists1('file','strComponentFileName','File Name Already Exists');
	}
	if(retCheck)
	{
		hisValidator.addValidation("strEffectiveFrom", "req","Effective From is a Mandatory Field");
		hisValidator.addValidation("strEffectiveFrom", "dtgtet=${deptdocBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
		retCheck=hisValidator.validate();
	}
	if(retCheck)
	{
		document.forms[0].hmode.value = "SAVE";
		document.forms[0].submit();
	}
	else
	{
		return false;
	}
}
function fun()
{
   if(document.forms[0].strDeptCode.value == 0 && document.forms[0].strDocumentCode.value != 0 )
   {
		alert('First select the Deparment Combo');
	 	document.forms[0].strDocumentCode.selectedIndex = 0;
	 	document.forms[0].strDeptCode.focus();
	}		 
}
</script>
</head>
<body onLoad="addRows(new Array('strComponentCode','strComponentFileName','strComponentRemark')
						,new Array('s','t','t'),'1','1','I');">
<html:form action="/masters/CNTDeptDocumentMst">

	<div class="errMsg"><bean:write name="deptdocBean" property="strErrorMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="deptdocBean" property="strWarning"/></div>
	<div class="normalMsg"><bean:write name="deptdocBean" property="strMsg"/></div>

	<tag:tab tabLabel="Add Department Document" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">Department Document Master&gt;&gt;Add</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Department Name</td>
			<td class="CONTROL" width="50%">
			<select name="strDeptCode" class='comboNormal'>
				<bean:write name="deptdocBean" property="deptarmentAdd" filter="false"/>
			</select>
			</td>
		</tr>
         <tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Document Name</td>
			<td class="CONTROL" width="50%">
			<select name="strDocumentCode" class='comboNormal' onChange ='ajaxDeptDoc(this),fun();' >
			<bean:write name="deptdocBean" property="documentAdd" filter="false"/>
			</select>
			</td>
		</tr>
		</table>		
	<div id="addedcompId"></div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
			<tr>
					<td colspan="4" class="TITLE"> New Component</td>
				</tr>
				<tr>
				   <td width="30%" class="multiLabel"><font color="red">*</font>Component Name</td>
				   <td width="30%" class="multiLabel"><font color="red">*</font>File Name</td>
				   <td width="30%" class="multiLabel">Remarks</td>
				   <td width="10%" class="multiLabel"><img
						src="../../hisglobal/images/plus.gif" style="cursor:pointer;"
						onclick="addRows(new Array('strComponentCode','strComponentFileName','strComponentRemark')
						,new Array('s','t','t'),'1','1','R');"></td>
				</tr>
			</table>
			<div id="id1"></div>			
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
			<tr>
				<td colspan="3" class="LABEL" width="50%"><font color="red">*</font>Effective From</td>
				<td colspan="1" class="CONTROL" width="50%"><date:date name="strEffectiveFrom" value="${deptdocBean.strEffectiveFrom}"></date:date></td>
			</tr>		
			<tr> 
    			 <td colspan="3" class="LABEL">Is Default</td>
   				 <td colspan="1" class="CONTROL"> 
     			 <html:radio name="deptdocBean" property="strIsDefault" value="0">No</html:radio>
   				 <html:radio name="deptdocBean" property="strIsDefault" value="1">Yes</html:radio>
    			 </td>
  			</tr>		
			<tr class="FOOTER">
				<td colspan="4"><font size="2" color="red">*</font>Mandatory Fields</td>
			</tr>
			</table>		
		<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center"><img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer"
				onClick="return validate1();" />				
				<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" onClick="document.forms[0].reset();">	
				<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;position: absolute;" 
				onClick="cancel('LIST');" /></td>
		</tr>
		</table>	
	<input type="hidden" name="hmode" />
	<cmbPers:cmbPers/>
	</html:form>
<jsp:include page="multirow_deptdocMst_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>