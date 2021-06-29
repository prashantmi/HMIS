<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Department Ward Disease Master Modify Page</title>

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
function validate1(){

		var hisValidator = new HISValidator("deptWardDiseaseBean");

		hisValidator.addValidation("strEffTempDate", "req","Effective From is a Mandatory Field");
		hisValidator.addValidation("strEffTempDate", "date","Effective From should be a valid Date");
		hisValidator.addValidation("strEffTempDate", "dtgtet=${deptWardDiseaseBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
	
		if(document.deptWardDiseaseBean.strRemarks.value != ""){
			hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
		}
		
		var retVal = hisValidator.validate(); 
	
		if(retVal){
				document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
		}else{
		
		return false;
		}
	}

</script>

</head>
<body>
<html:form action="/masters/CNTDepartmentWardDiseaseMst.cnt" method="post">
	
<div class="errMsg"><bean:write name="deptWardDiseaseBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="deptWardDiseaseBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"></div>  	
	<tag:tab tabLabel="Modify Department Ward Disease" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
		
		
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER"> 
    <td height="17" colspan="2">Department Ward Disease&gt;&gt; Modify</td>
  </tr>
  <tr> 
    <td   class="LABEL"> Ward Name</td>
    <td   class="CONTROL"><bean:write name="deptWardDiseaseBean" property="strDeptWardName"/></td>
  </tr>
  <tr>
			<td width="50%" class="LABEL">Department
			Name</td>
			<td width="50%" class="CONTROL"><bean:write name="deptWardDiseaseBean" property="strDepartment"/>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Unit
			Name</td>
			<td width="50%" class="CONTROL"><bean:write name="deptWardDiseaseBean" property="strUnit"/>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">Disease</td>
			<td width="50%" class="CONTROL"><bean:write name="deptWardDiseaseBean" property="strDiseaseTempName"/>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Effective From</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffTempDate" id="strEffTempDate" value="${deptWardDiseaseBean.strEffTempDate}"></dateTag:date>
			</td>
		</tr>
		<tr>
  <td width="50%"  class="LABEL">Remarks</td>
    <td width="50%" class="CONTROL"> <textarea rows="2" cols="20" name="strRemarks"><bean:write name="deptWardDiseaseBean" property="strRemarks"/></textarea></td> 
  </tr>
  <tr> 
    <td class="LABEL">Record Status</td>
    <td  class="CONTROL" > 
    
     <html:radio name="deptWardDiseaseBean" property="strActive" value="1">Active</html:radio>
    <html:radio name="deptWardDiseaseBean" property="strActive" value="2">InActive</html:radio>
        </td>
  </tr>
  <tr class="FOOTER"> 
    <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>

			<td align="right"><img
				src="../../hisglobal/images/btn-sv.png"
				style="cursor:pointer" onClick=" return validate1();" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
				style="cursor:pointer" onClick="cancel('LIST');" /></td>
		</tr>
	</table>
<input type="hidden" name="chk" value="${deptWardDiseaseBean.chk[0]}" />
	<input type="hidden" name="hmode" />
		
	 <cmbPers:cmbPers/>	
</html:form>
<jsp:include page="multirow_departmentwarddisease_ipd.jsp"></jsp:include>
</body>
</html>