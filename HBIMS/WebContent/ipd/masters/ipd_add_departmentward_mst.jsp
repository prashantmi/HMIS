

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta  charset=utf-8>
<title>Department Ward Master Add Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<script type="text/javascript">

function myFunc(mode, comObj, delIdx){

		delIndex = delIdx;
		var hmode = "UNITVALUES"; 
		var url = "CNTDepartmentWardMst.cnt?hmode="+hmode+"&deptName="+comObj.value+"&wardVal="+document.forms[0].strDeptWardValue.value;
		ajaxFunction(url,"1");
		
	}
	
	function getAjaxResponse(res,mode){
	
		var objEle = document.getElementById("unitId" + delIndex);
		objEle.innerHTML = "<select name='strUnit' class='comboMax' id='strUnit" + delIndex + "'>"+res+"</select>";
		}

function validate1(){

	var retVal = false;
	var hisValidator = new HISValidator("deptWardBean");
	
		hisValidator.addValidation("strDepartment", "dontselect=0","Please Select A Department");
		hisValidator.addValidation("strUnit", "dontselect=0","Please Select An Unit");
	
	
		if(document.deptWardBean.strRemarks.value != ""){
			hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
		}
		retVal = hisValidator.validate(); 
		if(retVal){
			destroyMultiRow("1");
				document.forms[0].hmode.value = "INSERT";
				document.forms[0].submit();
		}else{
		
		return false;
		}
	}


</script>

</head>
<body onLoad="addRows(new Array('strDepartment','strUnit','strEffectiveFromDate'),new Array('s','s','d'),'1','1','I');">
<html:form action="/masters/CNTDepartmentWardMst.cnt" method="post">

<div class="errMsg"><bean:write name="deptWardBean" property="strErr"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="deptWardBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="deptWardBean" property="strWarning"/></div>
	
	<tag:tab tabLabel="Add Department Ward" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
  <tr class="HEADER"> 
    <td colspan="2">Department Ward &gt;&gt; Add</td>
  </tr>
  <tr> 
    <td width="50%"  class="LABEL"> Ward Name</td>
    <td width="50%"  class="CONTROL"><bean:write name="deptWardBean" property="comboValue"/></td>   
  </tr>
  <tr>
  <td colspan="2"> 
  	<table width="100%" cellspacing="1px" cellpadding="1px">
			<tr>
				<td width="32%" class="multiLabel">Department</td>
				<td width="30%" class="multiLabel">Unit</td>
				<td width="32%" class="multiLabel">Effective From</td>
				<td width="6%" class="multiLabel"><img
					src="../../hisglobal/images/plus.gif" width="15" height="15" style="cursor:hand;pointer:hand"
					onClick="addRows(new Array('strDepartment','strUnit','strEffectiveFromDate'),new Array('s','s','d'),'1','1','R');"></td>
			</tr>
			</table>
			<div id="id1">  </div>
  </td>
  </tr>
  
  <tr>
  <td width="50%"  class="LABEL">Remarks</td>
    <td width="50%" class="CONTROL"> <textarea rows="2" cols="20" name="strRemarks"></textarea></td> 
  </tr>
   <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center"><img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer"
				onClick=" return validate1();" />
				<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" onClick="document.forms[0].reset();">
				<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer"
				onClick="cancel('LIST');" /></td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	
	<input type="hidden" name="combo" value="${deptWardBean.combo[0]}" />
	<input type="hidden" name="comboValue" value="${deptWardBean.comboValue}" />
	<input type="hidden" name="strDeptWardValue" value="${deptWardBean.combo[0]}"/>
	<input type="hidden" name="strDeptWardName" value="${deptWardBean.comboValue}" />
<cmbPers:cmbPers/>	
</html:form>
<jsp:include page="multirow_departmentward_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>