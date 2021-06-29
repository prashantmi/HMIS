
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset=utf-8>
<title>Nurse Check List Add Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<script type="text/javascript">

var delIndex = "";

function myFunc(mode, comObj, delIdx){

		delIndex = delIdx;
		var hmode = "UNITVALUES"; 
		var url = "CNTNurseCheckListMst.cnt?hmode="+hmode+"&deptName="+comObj.value;
		ajaxFunction(url,"1");
		
	}
	
	function getAjaxResponse(res,mode){
	
		var objEle = document.getElementById("unitId" + delIndex);
		objEle.innerHTML = "<select name='strUnit' class='comboMax' id='strUnit" + delIndex + "'>"+res+"</select>";
		}
		
		
		function blockFunc(){
	
		if(document.nurseCheckListBean.strCheckListType.options[0].selected || document.nurseCheckListBean.strCheckListType.options[1].selected){
			document.getElementById("mulid").style.display="none";
		}else{
			document.getElementById("mulid").style.display="block";
		}
	}
		

function validate1()
{
	var strDeptUnitValue="";
	var i=0;	
	var hisValidator = new HISValidator("nurseCheckListBean");
	
		hisValidator.addValidation("strCheckListName", "req","Check List Name is a Mandatory Field");
		hisValidator.addValidation("strCheckListType", "dontselect=0","Please Select a Check List Type");
		hisValidator.addValidation("strCheckListDataType", "req","Check List Data Type is a Mandatory Field");
		//hisValidator.addValidation("strCheckListDefaultVal", "req","Check List Default Value is a Mandatory Field");
		hisValidator.addValidation("strCheckListDefaultVal", "maxlen=50","Check List Default Value Can't be more than 50");
		hisValidator.addValidation("strCheckListUnit", "maxlen=10","Check List Unit Can't be more than 50");
	if(document.nurseCheckListBean.strCheckListType.options[2].selected || document.nurseCheckListBean.strCheckListType.options[3].selected)
	{
			hisValidator.addValidation("strDepartment", "dontselect=0","Please Select A Department");
			hisValidator.addValidation("strUnit", "dontselect=0","Please Select An Unit");
	}
		
		hisValidator.addValidation("strEffectiveFromDate", "req","Effective From is a Mandatory Field");
		hisValidator.addValidation("strEffectiveFromDate", "date","Effective From should be a valid Date");
		hisValidator.addValidation("strEffectiveFromDate", "dtgtet=${nurseCheckListBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
	
		if(document.nurseCheckListBean.strRemarks.value != ""){
			hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
		}
		
		retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal){
		
				if(document.multirow.rowIndex1.value>=1 && (document.nurseCheckListBean.strCheckListType.options[2].selected || document.nurseCheckListBean.strCheckListType.options[3].selected))
				{
					
					for(i=0;i<document.multirow.rowIndex1.value;i++)
					{
						
						if(i==0)
						{
							
							strDeptUnitValue+=document.getElementsByName("strDepartment")[0].value+"@"+document.getElementsByName("strUnit")[0].value;
						}
						else
						{
							strDeptUnitValue+="#";
							strDeptUnitValue+=document.getElementsByName("strDepartment")[i].value+"@"+document.getElementsByName("strUnit")[i].value
						}
					}
				}
				else
					strDeptUnitValue="0";
				document.forms[0].strCheckListFor.value=strDeptUnitValue;
				
				//retVal=checkMultirow('strDepartment', 'You have Selected Duplicate Data in Department Combo');
				if(retVal){
				
				retVal=checkMultirow('strUnit', 'You have Selected Duplicate Data in Unit Combo');
				
				}
				if(retVal){
					document.forms[0].hmode.value = "INSERT";
					document.forms[0].submit();
				}
		}else{
		hisValidator.clearAllValidations();
		return false;
		}
	}
function validateDefaultVal(e)
{
	// Check List Data Type Alpahnumeric
	if(document.forms[0].strCheckListDataType.value=="1")
	{
		return validateData(e,8);
	}
	// Check List Data Type Alpahnumeric
	if(document.forms[0].strCheckListDataType.value=="2")
	{
		return validateData(e,5);
	}
}

</script>

</head>
<body onLoad="addRows(new Array('strDepartment','strUnit'),new Array('s','s'),'1','1','I'),blockFunc(); document.forms[0].strCheckListName.focus();">
<html:form action="/masters/CNTNurseCheckListMst.cnt" method="post">

<div class="errMsg"><bean:write name="nurseCheckListBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="nurseCheckListBean" property="strWarning"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="nurseCheckListBean" property="strMsg"/></div>

	<tag:tab tabLabel="Add Nurse Check List" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="2">Nurse Checklist Master &gt;&gt; Add</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Check List Name</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strCheckListName"
				class='txtFldMax' maxlength="40"
				onkeypress="return validateData(event,9);"></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Check List Unit</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strCheckListUnit"
				class='txtFldMax' maxlength="10"
				onkeypress="return validateData(event,9);"></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Check List Data Type</td>
			<td width="50%" class="CONTROL"><select
				name="strCheckListDataType" class='comboNormal'
				onchange="document.forms[0].strCheckListDefaultVal.value='';">
				<option value="1" selected="selected">Alphanumeric</option>
				<option value="2">Numeric</option>
			</select></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Check List Default Value</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strCheckListDefaultVal"
				class='txtFldMax' maxlength="50"
				onkeypress="return validateDefaultVal(event);"></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Check List Type</td>
			<td width="50%" class="CONTROL"><select name="strCheckListType"
				class='comboMax' onchange="blockFunc();">
				<option value="0" selected="selected">Select Option</option>
				<option value="1">All Department</option>
				<option value="2">All Given Department</option>
				<option value="3">All Department Except</option>
			</select></td>
		</tr>
	</table>
	<div id="mulid" style="default: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="50%" class="multiLabel">Department</td>
			<td width="44%" class="multiLabel">Unit</td>
			<td width="6%" class="multiLabel"><img
				src="../../hisglobal/images/plus.gif"
				style="cursor: hand; pointer: hand"
				onClick="addRows(new Array('strDepartment','strUnit'),new Array('s','s'),'1','1','R');"></td>
		</tr>
	</table>

	<div id="id1"></div>
	</div>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFromDate"
				value="${nurseCheckListBean.strCtDate}">
			</dateTag:date></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Remarks</td>
			<td width="50%" class="CONTROL"><textarea onkeyup="lTrim(this);"
				onblur="Trim(this);" rows="2" cols="20" name="strRemarks"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer" onClick=" return validate1();"/>
			<img src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer" onClick="document.forms[0].reset();">
			<img src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer" onClick="cancel('LIST');"/></td>
		</tr>
	</table>

	<input type="hidden" name="hmode"/>
	<input type="hidden" name="strCheckListFor" value=""/>
	<cmbPers:cmbPers/>
</html:form>
<jsp:include page="multirow_nursechecklistmst_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>