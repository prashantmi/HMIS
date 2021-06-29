<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 23-Dec-2013 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>


</head>


<body>

<s:form action="GlobalDepartment" namespace="/">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.department"/>&nbsp;<s:text name="global.detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strDeptName" name="deptModel.strDeptName" maxlength="50" size="30" > </s:textfield>
			 	<s:hidden key="strOldDeptName" name="deptModel.strOldDeptName" value="%{deptModel.strDeptName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strDeptType" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.departmentType}" listKey="value" listValue="label" name="deptModel.strDeptType" cssStyle="width:197px"> </s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.age"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strAgeLimit" name="deptModel.strAgeLimit"  maxlength="3" cssStyle="width:194px"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.gender"/></div>
				<div class="div-table-col column  width45"><s:select key="strGenderCode" headerKey="" headerValue="Select Value" 
				 				 list="%{#session.genderList}" listKey="value" listValue="label" name="deptModel.strGenderCode" cssStyle="width:197px"> </s:select>
				</div>
			</div>
			
</div>


<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId" ><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId" ><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
		<%-- 	    	<s:submit  action="updateGlobalDepartment" value="Save" /><s:submit action="cancelGlobalDepartment" value="Cancel" /> --%>
			</s:else>
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden name="flagGlobal" value="%{flagGlobal}"></s:hidden>
	<s:hidden name="deptModel.strDeptCode" value="%{deptModel.strDeptCode}"></s:hidden>
	
	<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token></s:token>
</s:form>

<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{deptModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>
<script type="text/javascript" >
$('[name="deptModel.strDeptName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
$('[name="deptModel.strDeptType"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); 
$('[name="deptModel.strAgeLimit"]').validatebox({validType:  ' numberRangeOneToOneHundredTwentyFiveOnly'});
$('#submitId').click(function(e){
	$("#GlobalDepartment").attr('action',"/HISRegistration/registration/saveGlobalDepartment.action");
	if($('#GlobalDepartment').form('validate'))
		{
		sortandEncodebase64($("#GlobalDepartment"));
		$('#GlobalDepartment').submit();
		
		}
			});
$('#cancelId').click(function(e){	
	$("#GlobalDepartment").attr('action',"/HISRegistration/registration/cancelGlobalDepartment.action");	
		$('#GlobalDepartment').submit();			
			});
$('#clearId').click(function(e){	
		$('[name="deptModel.strDeptName"]').val('');	
		$('[name="deptModel.strDeptType"]').val('-1');
		$('[name="deptModel.strGenderCode"]').val('');
		$('[name="deptModel.strAgeLimit" ]').val('');
			});
</script>
</body>
</html>