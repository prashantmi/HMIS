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

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>




function getDeptName()
{
	var deptCode = document.getElementsByName("deptModel.strDeptCode")[0];
	document.getElementsByName("deptModel.strDeptName")[0].value=deptCode.options[deptCode.selectedIndex].innerHTML;
	document.forms[0].action="putLocalDepartment.action";
	document.forms[0].submit();
}



</script>
</head>
<body>

<s:form action="LocalDepartment">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.department"/>&nbsp;<s:text name="global.detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.global"/>&nbsp;<s:text name="global.department"/></div>
				<div class="div-table-col column  width45"><s:select key="strDeptCode" value="%{deptModel.strDeptCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.globalDeptList}" listKey="value" listValue="label"  name="deptModel.strDeptCode" onchange="getDeptName();" cssStyle="width:198px"> </s:select>
				</div>
			</div>
			</s:if>
			<s:else>
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.global"/>&nbsp;<s:text name="global.department"/></div>
					<div class="div-table-col column  width45"><s:property value="%{deptModel.strDeptNameGlobal}"/>
					</div>
					<s:hidden name="deptModel.strDeptNameGlobal" value="%{deptModel.strDeptNameGlobal}"></s:hidden>
					<s:hidden name="deptModel.strDeptCode" value="%{deptModel.strDeptCode}"></s:hidden>
			</s:else>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.name"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strDeptName" name="deptModel.strDeptName" maxlength="50" size="30" > </s:textfield>
			 	<s:hidden key="strOldDeptName" name="deptModel.strOldDeptName" value="%{deptModel.strDeptName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strDeptType" value="%{deptModel.strDeptType}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.departmentType}" listKey="value" listValue="label" name="deptModel.strDeptType" disabled="true" cssStyle="width:198px"> </s:select>
				 				 	<s:hidden name="deptModel.strDeptType" value="%{deptModel.strDeptType}"></s:hidden>
				 </div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/>&nbsp;<s:text name="global.location"/></div>
				<div class="div-table-col column  width45"><s:select key="strDeptLocCode" value="%{deptModel.strDeptLocCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.locationList}" listKey="value" listValue="label" name="deptModel.strDeptLocCode" cssStyle="width:198px"> </s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.head"/></div> 
				<div class="div-table-col column  width45"><s:select key="strHodCode" value="%{deptModel.strHodCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.hodList}" listKey="value" listValue="label" name="deptModel.strHodCode" cssStyle="width:198px"> </s:select>			
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.age"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strAgeLimit" name="deptModel.strAgeLimit"  maxlength="3" cssStyle="width:196px" id="maxAge"> </s:textfield>
				</div>
			</div>
					
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.lowerAge"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strLowerAgeLimit" name="deptModel.strLowerAgeLimit"  maxlength="3" cssStyle="width:196px" > </s:textfield>
				</div>
			</div>
			
						
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.gender"/></div>
				<div class="div-table-col column  width45"><s:select key="strGenderCode" headerKey="" headerValue="Select Value" 
				 				 list="%{#session.genderList}" listKey="value" listValue="label" name="deptModel.strGenderCode" cssStyle="width:198px"> </s:select>
				</div>
			</div>
			
			
			
			
			<div class="div-table-row ">
			<div class="div-table-col label  width50"><s:text name="department.IsCappingAllowed"/></div>	
			<div class="div-table-col column  width45"><s:select key="strIsCappingAllowed"  headerKey="-1" id="isCapping" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Dept-Wise(Only General)','2':'Dept-Wise(General+Special)','3':'Unit-Wise'}" name="deptModel.strIsCappingAllowed" cssStyle="width:198px" ></s:select>
			    <%--   list=" #{'2':'Unitwise','1':'Departmentwise','0':'No'}" --%>
			     <%-- <s:radio label="Answer" name="isDefault" list="#{'1':'Yes','0':'No'}" value="%{patCategoryModel.strIsDefault}"  onchange="setIsDefault(this);"/> --%>
				</div>
				</div>
			
				
			
			
			
			
			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.MaxWalkinReg"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinReg" name="deptModel.strMaxWalkinReg"  maxlength="5" cssStyle="width:196px" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.MaxWalkFolloUp"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkFolloUp" name="deptModel.strMaxWalkinFolloUp"  maxlength="5" cssStyle="width:196px" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.MaxWalkPortReg"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinPortReg" name="deptModel.strMaxWalkinPortReg"  maxlength="5" cssStyle="width:196px" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="department.MaxWalkPortFollowUP"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strMaxWalkinPortFollowUP" name="deptModel.strMaxWalkinPortFollowUP"  maxlength="5" cssStyle="width:196px" > </s:textfield>
				</div>
			</div>
			
			
					
			
					
			
			<s:if test="flagAddMod=='MODIFY'">			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.remarks"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strRemarks" name="deptModel.strRemarks"  maxlength="50" cssStyle="width:197px" > </s:textfield>
				</div>
			</div>
			</s:if>
			
</div>


<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="modifyId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadId"><span class="clear"><s:text name="clear"/></span></a>
	
<%-- 	    	<s:submit action="updateLocalDepartment" value="Save" /> --%>
<%-- 	    	<s:submit action="cancelLocalDepartment" value="Cancel" /> --%>
			</s:else>
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden name="flagGlobal" value="%{flagGlobal}"></s:hidden>
		<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{deptModel.StrWarning}"/></div>
<%--  <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:set name="messageVar" value="message"></s:set></div>--%>
<h4><s:property value="message"/></h4>

<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
<s:actionerror/>
</div>
</div>

<%-- <h4><font color="#FF0000"><s:property value="%{deptModel.strErrorMsg}"/></font></h4> --%>
<script type="text/javascript">

$(document).ready(function(){
	
	//alert($('[name="deptModel.strIsCappingAllowed"]').val()+"\n flagAddMod "+$('[name="flagAddMod"]').val()+"\n flagGlobal "+$('[name="flagGlobal"]').val());
	if($('[name="flagAddMod"]').val() === 'ADD'){
		$('[name="deptModel.strIsCappingAllowed"]').val('0');
	}
});

$(function(){
	$('#isCapping').onchange(function(e){
		var isValid = true;
		var inp = $("#maxAge").val();
		if(($.trim(inp).length <= 0)|| ($.trim($(inp).val()) == ''))
		{
			var isValid = false;
			$('#maxAge').css({
				"border": "1px solid red",
				"background": "#FFCECE"
			});
			$("#maxAge").focusin();
		}
		if (isValid == false)
		 e.preventDefault();
	});
});

$('[name="deptModel.strDeptCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="deptModel.strDeptType"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="deptModel.strDeptName"]').validatebox({required: true,	validType: 'alphaWithSpace'});
$('[name="deptModel.strDeptLocCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="deptModel.strAgeLimit"]').validatebox({validType:  ' numberRangeOneToOneHundredTwentyFiveOnly'});
$('[name="deptModel.strLowerAgeLimit"]').validatebox({validType:  'numberRangeZeroToTwentythree'});

$('[name="deptModel.strRemarks"]').validatebox({validType: 'alphaSpecialChar'});
$('#submitId').click(function(e){
	$("#LocalDepartment").attr('action',"/HISRegistration/registration/saveLocalDepartment.action");
	if($('#LocalDepartment').form('validate'))
		{
		
		sortandEncodebase64($("#LocalDepartment"));
		$('#LocalDepartment').submit();
		
		}
			});
$('#cancelId').click(function(e){	
		$('#LocalDepartment').submit();			
			});
$('#modifyId').click(function(e){	
	$("#LocalDepartment").attr('action',"/HISRegistration/registration/updateLocalDepartment.action");	
	if($('#LocalDepartment').form('validate'))
		{
		sortandEncodebase64($("#LocalDepartment"));
		$('#LocalDepartment').submit();
		
		}
			});			
$('#clearId').click(function(e){
	$('[name="deptModel.strDeptCode"]').val('-1');
	$('[name="deptModel.strDeptType"]').val('-1');
	$('[name="deptModel.strDeptName"]').val('');
	$('[name="deptModel.strDeptLocCode"]').val('-1');
	$('[name="deptModel.strHodCode"]').val('-1');
	$('[name="deptModel.strAgeLimit"]').val('');
	$('[name="deptModel.strGenderCode"]').val('X');
			});
$('#reloadId').click(function(e){
	location.reload(true);
});



</script>
</body>
</html>