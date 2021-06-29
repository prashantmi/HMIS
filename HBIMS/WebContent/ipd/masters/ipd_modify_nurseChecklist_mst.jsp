
<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Nurse Check List Modify Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>



<script type="text/javascript">

function setMultiRow(){
		<logic:equal name="nurseCheckListBean" property="strTotalRows" value="${nurseCheckListBean.strMultiRow }">
</logic:equal>
		var rowLength = ${nurseCheckListBean.strTotalRows}
		document.multirow.rowLength1.value = rowLength;
		document.multirow.rowIndex1.value = rowLength;
	}

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

function validate1(){
		
	var strDeptUnitValue="";
	var hisValidator = new HISValidator("nurseCheckListBean");
	
		hisValidator.addValidation("strCheckListName", "req","Check List Name is a Mandatory Field");
		hisValidator.addValidation("strCheckListType", "dontselect=0","Please Select a Check List Type");
		hisValidator.addValidation("strCheckListDataType", "req","Check List Data Type is a Mandatory Field");
		//hisValidator.addValidation("strEffectiveFromDate", "dtgtet=${nurseCheckListBean.strCtDate}","Effective From Date should be Greater than or Equal to Current Date");
		//hisValidator.addValidation("strCheckListDefaultVal", "req","Check List Default Value is a Mandatory Field");
		if(document.nurseCheckListBean.strCheckListType.options[2].selected || document.nurseCheckListBean.strCheckListType.options[3].selected){

			hisValidator.addValidation("strDepartment", "dontselect=0","Please Select A Department");
			hisValidator.addValidation("strUnit", "dontselect=0","Please Select An Unit");
	}
		
		if(document.nurseCheckListBean.strRemarks.value != ""){
			hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
		}
		
		retVal = hisValidator.validate(); 
		
		
		if(retVal){
					//alert(document.getElementsByName("strDepartment")[1].value);
				//alert(document.getElementsByName("strUnit")[1].value);
				if(document.multirow.rowIndex1.value>=1 && (document.nurseCheckListBean.strCheckListType.options[2].selected || document.nurseCheckListBean.strCheckListType.options[3].selected) )
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
			hisValidator.clearAllValidations();
			if(retVal){
				document.forms[0].hmode.value = "UPDATE";
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
<body onLoad="setMultiRow();document.forms[0].strCheckListName.focus();">
<html:form action="/masters/CNTNurseCheckListMst.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>  
	<div class="errMsg"><bean:write name="nurseCheckListBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="nurseCheckListBean" property="strWarning"/></div>

	<tag:tab tabLabel="Modify Nurse Check List" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="0px">
		<tr class="HEADER">
			<td colspan="2">Nurse Check list Master &gt;&gt; Modify</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Check List Name</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strCheckListName"
				class='txtFldMax' value="${nurseCheckListBean.strCheckListName }"
				maxlength="40"></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Check List Unit</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text" name="strCheckListUnit"
				class='txtFldMax' value="${nurseCheckListBean.strCheckListUnit }"
				maxlength="10"></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Check List Data Type</td>
			<td width="50%" class="CONTROL"><html:select
				name="nurseCheckListBean" property="strCheckListDataType"
				onchange="document.forms[0].strCheckListDefaultVal.value='';"
				styleClass='comboNormal'>
				<html:option value="1">Alphanumeric</html:option>
				<html:option value="2">Numeric</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Check List Default Value</td>
			<td width="50%" class="CONTROL"><input onkeyup="lTrim(this);"
				onblur="Trim(this);" type="text"
				\
    name="strCheckListDefaultVal" class='txtFldMax'
				value="${nurseCheckListBean.strCheckListDefaultVal}"
				\
    maxlength="50" onkeypress="return validateDefaultVal(event);">
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Check List Type</td>
			<td width="50%" class="CONTROL"><html:select
				name="nurseCheckListBean" property="strCheckListType"
				onchange="blockFunc();" styleClass='comboMax'>
				<html:option value="0">SelectValue</html:option>
				<html:option value="1">All Department</html:option>
				<html:option value="2">All Given Department</html:option>
				<html:option value="3">All Department Except</html:option>
			</html:select></td>
		</tr>
	</table>

	<logic:equal name="nurseCheckListBean" property="strCheckListType" value="1">
		<div id="mulid" style="display: none">
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td width="50%" class="multiLabel">Department</td>
				<td width="44%" class="multiLabel">Unit</td>
				<td width="6%" class="multiLabel">
				<img src="../../hisglobal/images/plus.gif"
					style="cursor: hand; pointer: hand"
					onClick="addRows(new Array('strDepartment','strUnit'),new Array('s','s'),'1','1','R');"></td>
			</tr>
		</table>

		<div id="id1">${nurseCheckListBean.strMultiRow }</div>
		</div>
	</logic:equal>

	<logic:notEqual name="nurseCheckListBean" property="strCheckListType" value="1">
		<div id="mulid" style="display: block">
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
		<div id="id1">${nurseCheckListBean.strMultiRow }</div>
		</div>
	</logic:notEqual>

	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From</td>
			<td class="CONTROL"><bean:write name="nurseCheckListBean" property="strEffectiveFromDate" filter="false"/></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Remarks</td>
			<td width="50%" class="CONTROL"><textarea onkeyup="lTrim(this);"
				onblur="Trim(this);" rows="2" cols="20" name="strRemarks"><bean:write
				name="nurseCheckListBean" property="strRemarks" /></textarea></td>
		</tr>
		<tr>
			<td class="LABEL">Record Status</td>
			<td class="CONTROL"><html:radio name="nurseCheckListBean"
				property="strActive" value="1">Active</html:radio> <html:radio
				name="nurseCheckListBean" property="strActive" value="2">InActive</html:radio>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="right"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer" onClick=" return validate1();"/></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer"
				onClick="cancel('LIST');"/></td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="chk" value="${nurseCheckListBean.chk[0]}" />
	<input type="hidden" name="strEffectiveFromDate" value="${nurseCheckListBean.strEffectiveFromDate}" />
	<input type="hidden" name="strCheckListFor" value="" />
	<cmbPers:cmbPers/>
</html:form>
<jsp:include page="multirow_nursechecklistmst_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>