
<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Department Ward Disease Master Add Page</title>

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

function myFunc(mode, comObj){

		var hmode = "UNITVALUES"; 
		var wVal = document.forms[0].combo.value;
		var url = "CNTDepartmentWardDiseaseMst.cnt?hmode="+hmode+"&deptName="+comObj.value+"&wardVal="+wVal;
		ajaxFunction(url,"1");
		
	}
	
	function getAjaxResponse(res,mode){
	
		var objEle = document.getElementById("unitId");
		objEle.innerHTML = "<select class='comboMax' name='strUnit' >"+res+"</select>";
		}

function validate1(){

		var hisValidator = new HISValidator("deptWardDiseaseBean");

		hisValidator.addValidation("strDepartment", "dontselect=0","Please Select A Department");
		hisValidator.addValidation("strUnit", "dontselect=0","Please Select An Unit");
		hisValidator.addValidation("strDisease", "dontselect=0","Please Select A Disease");
	
		if(document.deptWardDiseaseBean.strRemarks.value != ""){
			hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
		}
		
		var retVal = hisValidator.validate(); 
	
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
<body onLoad="addRows(new Array('strDisease','strEffectiveFromDate'),new Array('s','d'),'1','1','I'); document.forms[0].deptWardDiseaseBean.focus();">
<html:form action="/masters/CNTDepartmentWardDiseaseMst.cnt" method="post">

	<div class="errMsg"><bean:write name="deptWardDiseaseBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="deptWardDiseaseBean" property="strWarning"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="deptWardDiseaseBean" property="strMsg"/></div>
	
	<tag:tab tabLabel="Add Department Ward Disease" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
		
		
<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
  <tr class="HEADER"> 
    <td height="17" colspan="4">Department Ward Disease&gt;&gt; Add</td>
  </tr>
  <tr> 
    <td colspan="2"  class="LABEL"> Ward Name</td>
    <td colspan="2"  class="CONTROL"><bean:write name="deptWardDiseaseBean" property="comboValue"/></td>
  </tr>
  <tr> 
    <td  width="25%"  class="LABEL"><font color="red">*</font>Department </td>
	    <td width="25%"  class="CONTROL"><select name="strDepartment" class='comboNormal' onChange="myFunc('1',this);">
	<bean:write name="deptWardDiseaseBean" property="strDeptmentValues" filter="false"/>   
      </select></td>
    <td width="25%"  class="LABEL"><font color="red">*</font>Unit </td>
    <td width="25%"  class="CONTROL">
    <div id="unitId" >
    <select name="strUnit" class='comboNormal'>
  <bean:write name="deptWardDiseaseBean" property="strUnitValues" filter="false"/> 
      </select>
      </div>
      </td>
  </tr>
  <tr> 
    <td colspan="4"> 
    <table width="100%" cellspacing="1px" cellpadding="1px">
        <tr> 
          <td width="47%" class="multiLabel">Disease</td>
         <td width="47%" class="multiLabel">Effective From</td>
          <td width="6%" class="multiLabel"><img
					src="../../hisglobal/images/plus.gif" width="15" height="15" style="cursor:hand;pointer:hand"
					onClick="addRows(new Array('strDisease','strEffectiveFromDate'),new Array('s','d'),'1','1','R');"></td>
        </tr>
      </table>
      <div id="id1"> </div></td>
  </tr>
  <tr>
  <td width="50%" colspan="2" class="LABEL">Remarks</td>
    <td width="50%" colspan="2" class="CONTROL"> <textarea rows="2" cols="20" name="strRemarks"></textarea></td> 
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
	<input type="hidden" name="combo" value="${deptWardDiseaseBean.combo[0]}" />
	<input type="hidden" name="comboValue" value="${deptWardDiseaseBean.comboValue}" />
	<input type="hidden" name="strDeptWardValue" value="${deptWardDiseaseBean.combo[0]}"/>
	<input type="hidden" name="strDeptWardName" value="${deptWardDiseaseBean.comboValue}" />
	
		
<cmbPers:cmbPers/>		
</html:form>
<jsp:include page="multirow_departmentwarddisease_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>