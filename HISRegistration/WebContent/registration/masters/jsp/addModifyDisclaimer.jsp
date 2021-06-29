<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 12-Feb-2014 		-->

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
<script>

function saveDeptDisclaimer()
{
	//if(valDept()&&validateIt())
	{
		document.forms[0].action="saveDeptWiseDisclaimerMst.action";
		document.forms[0].submit();			
	}
}
function saveUnitDisclaimer()
{
	//if(valUnit()&&validateIt())
	{
		document.forms[0].action="saveUnitWiseDisclaimerMst.action";
		document.forms[0].submit();			
	}
}

function updateDeptDisclaimer()
{
	if(validateIt())
	{
		document.forms[0].action="updateDeptWiseDisclaimerMst.action";
		document.forms[0].submit();			
	}
}

function updateUnitDisclaimer()
{
	if(validateIt())
	{
		document.forms[0].action="updateUnitWiseDisclaimerMst.action";
		document.forms[0].submit();			
	}
}

function cancelUnitDisclaimer()
{
		document.forms[0].action="cancelUnitWiseDisclaimerMst.action";
		document.forms[0].submit();			
}

function cancelDeptDisclaimer()
{
		document.forms[0].action="cancelDeptWiseDisclaimerMst.action";
		document.forms[0].submit();			
}

function clearDisclaimer()
{
	document.getElementsByName("disclaimerModel.strAlignment")[0].value="-1";
	document.getElementsByName("disclaimerModel.strIsHeader")[0].value="0";
	document.getElementsByName("disclaimerModel.strUsabilityFlag")[0].value="-1";
	document.getElementsByName("disclaimerModel.strDisclaimerDesc1")[0].value="";
	document.getElementsByName("disclaimerModel.strDisclaimerDesc2")[0].value="";
	document.getElementsByName("disclaimerModel.strDisclaimerDesc3")[0].value="";
}

function valDept()
{
	var isValid=true;
	if(document.getElementsByName("disclaimerModel.strDeptCode")[0].value=="-1"){
		alert("Please Select Department..!");document.getElementsByName("disclaimerModel.strDeptCode")[0].focus();
		isValid=false;		
	}
	return isValid;
	
}
function valUnit()
{
	var isValid=true;
	if(document.getElementsByName("disclaimerModel.strDeptUnitCode")[0].value=="-1"){
		alert("Please Select Unit..!");document.getElementsByName("disclaimerModel.strDeptUnitCode")[0].focus();
		isValid=false;		
	}
	return isValid;
}

function validateIt()
{
	var isValid=true;
	if(document.getElementsByName("disclaimerModel.strAlignment")[0].value=="-1"){
		alert("Please Select Alignment..!");
		document.getElementsByName("disclaimerModel.strAlignment")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strUsabilityFlag")[0].value=="-1"){
		alert("Please Select Usability..!");document.getElementsByName("disclaimerModel.strUsabilityFlag")[0].focus();
		isValid=false;		
	}
	return isValid;
	
}

</script>
</head>
<body onload="">
<s:form action="DisclaimerMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.department"/>&nbsp;<s:text name="global.disclaimer"/>						
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<s:if test="flagDept=='YES'">
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/></div>
				<div class="div-table-col column  width50"><s:select key="strDeptCode" value="%{disclaimerModel.strDeptCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.deptList}" listKey="value" listValue="label" name="disclaimerModel.strDeptCode"></s:select>
				 					<s:hidden key="strDeptName" name="disclaimerModel.strDeptName" value="%{disclaimerModel.strDeptName}"></s:hidden>
				</div>
			</div>
			</s:if>
			<s:else>
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.department"/></div>
				<div class="div-table-col column  width50"><s:property value="%{disclaimerModel.strDeptName}"/>
				 					<s:hidden key="strDeptCode" name="disclaimerModel.strDeptCode" value="%{disclaimerModel.strDeptCode}"></s:hidden>
				 					<s:hidden key="strDeptName" name="disclaimerModel.strDeptName" value="%{disclaimerModel.strDeptName}"></s:hidden>				 					
				</div>			
			</s:else>
			</s:if>
			<s:else>
			<s:if test="flagAddMod=='ADD'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/></div>
				<div class="div-table-col column  width50"><s:select key="strDeptUnitCode" value="%{disclaimerModel.strDeptUnitCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.deptUnitList}" listKey="value" listValue="label" name="disclaimerModel.strDeptUnitCode"></s:select>
				 				<s:hidden key="strUnitName" name="disclaimerModel.strUnitName" value="%{disclaimerModel.strUnitName}"></s:hidden>
				</div>
			</div>
			</s:if>
			<s:else>
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.unit"/></div>
				<div class="div-table-col column  width50"><s:property value="%{disclaimerModel.strUnitName}"/>
				 					<s:hidden key="strDeptUnitCode" name="disclaimerModel.strDeptUnitCode" value="%{disclaimerModel.strDeptUnitCode}"></s:hidden>
				 					<s:hidden key="strUnitName" name="disclaimerModel.strUnitName" value="%{disclaimerModel.strUnitName}"></s:hidden>				 					
				</div>
			</s:else>
			</s:else>
			<div id="strEmpCodeId" class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.alignment"/></div> 
				<div class="div-table-col column  width50"><s:select key="strAlignment" value="%{disclaimerModel.strAlignment}" headerKey="-1" headerValue="Select Value" 
				 				list="#{'1':'Center','2':'Right','3':'Left'}" name="disclaimerModel.strAlignment"></s:select>			
				</div>
			</div>		
			<div id="disclaimerModel.strIsUnit" class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.location"/></div>
				<div class="div-table-col column  width50"><s:radio key="strIsHeader" id="disclaimerModel.strIsHeader" name="disclaimerModel.strIsHeader" value="%{disclaimerModel.strIsHeader}"
															list="#{'1':'Header','0':'Footer'}"> </s:radio>
				</div>
			</div>
			<div id="strEmpCodeId" class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.usability"/></div> 
				<div class="div-table-col column  width50"><s:select key="strUsabilityFlag" value="%{disclaimerModel.strUsabilityFlag}" headerKey="-1" headerValue="Select Value" 
				 				list="#{'1':'Normal','2':'Special','3':'Casualty','12':'General','13':'Discharge Profile','17':'Transfer'}" name="disclaimerModel.strUsabilityFlag"></s:select>			
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.disclaimer1"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strDisclaimerDesc1" name="disclaimerModel.strDisclaimerDesc1" maxlength="100" size="50"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.disclaimer2"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strDisclaimerDesc2" name="disclaimerModel.strDisclaimerDesc2" maxlength="100" size="50"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.disclaimer3"/></div>	
				<div class="div-table-col column  width50"><s:textfield key="strDisclaimerDesc3" name="disclaimerModel.strDisclaimerDesc3" maxlength="100" size="50"> </s:textfield>
				</div>
			</div>		
			
</div>

<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
				</div>
				<div class="div-table-row">
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
			<s:if test="flagDept=='YES'">
    		<a href="#" class="button" id="submitDeptDisclaimer"><span class="save"><s:text name="save"/></span></a>
    		<a href="#" class="button" id="cancelDeptDisclaimer"><span class="cancel"><s:text name="cancel"/></span></a>
    		</s:if>
    		<s:else>
    		<a href="#" class="button" id="submitUnitDisclaimer"><span class="save"><s:text name="save"/></span></a>
    		<a href="#" class="button" id="cancelUnitDisclaimer"><span class="cancel"><s:text name="cancel"/></span></a>
    		</s:else>
			<a href="#" class="button" id="clearId"><span class="clear">Clear</span></a>
			</s:if>
			<s:else >
			<s:if test="flagDept=='YES'">
    		<a href="#" class="button" id="updateDeptDisclaimer"><span class="save"><s:text name="save"/></span></a>
    		<a href="#" class="button" id="cancelDeptDisclaimer"><span class="cancel"><s:text name="cancel"/></span></a>
    		</s:if>
    		<s:else>
    		<a href="#" class="button" id="updateUnitDisclaimer"><span class="save"><s:text name="save"/></span></a>
	   		<a href="#" class="button" id="cancelUnitDisclaimer"><span class="cancel"><s:text name="cancel"/></span></a>
    		</s:else>
			<a href="#" class="button" id="reloadId"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
			
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strSelectDisclaimer" name="disclaimerModel.strSelectDisclaimer" value="%{disclaimerModel.strSelectDisclaimer}"></s:hidden>
	<s:hidden key="strDisclaimerCode" name="disclaimerModel.strDisclaimerCode" value="%{disclaimerModel.strDisclaimerCode}"></s:hidden>
	<s:hidden key="strHospitalCode" name="disclaimerModel.strHospitalCode" value="%{disclaimerModel.strHospitalCode}"></s:hidden>
	<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<h4><font color="#FF0000"><s:property value="%{disclaimerModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{disclaimerModel.strErrorMsg}"/></font></h4> --%>
<h4><s:property value="message"/></h4>
<script type="text/javascript" >
$('[name="disclaimerModel.strDeptCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('[name="disclaimerModel.strDeptUnitCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); 
$('[name="disclaimerModel.strAlignment"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); 
$('[name="disclaimerModel.strUsabilityFlag"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); 
$('#submitDeptDisclaimer').click(function(e){
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/saveDeptWiseDisclaimerMst.action");
	if($('#DisclaimerMst').form('validate'))
		{
		sortandEncodebase64($("#DisclaimerMst"));
		$('#DisclaimerMst').submit();	
		}
				
});
$('#cancelDeptDisclaimer').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/cancelDeptWiseDisclaimerMst.action");	
		$('#DisclaimerMst').submit();			
});
$('#submitUnitDisclaimer').click(function(e){
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/saveUnitWiseDisclaimerMst.action");
	if($('#DisclaimerMst').form('validate'))
		{
		sortandEncodebase64($("#DisclaimerMst"));
		$('#DisclaimerMst').submit();
		}
					
});
$('#cancelUnitDisclaimer').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/cancelUnitWiseDisclaimerMst.action");	
		$('#DisclaimerMst').submit();			
});
$('#updateDeptDisclaimer').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/updateDeptWiseDisclaimerMst.action");	
	//if($('#DisclaimerMst').form('validate'))
		$('#DisclaimerMst').submit();			
});
$('#cancelDeptDisclaimer').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/cancelDeptWiseDisclaimerMst.action");	
		$('#DisclaimerMst').submit();			
});
$('#updateUnitDisclaimer').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/updateUnitWiseDisclaimerMst.action");	
	//if($('#DisclaimerMst').form('validate'))
		$('#DisclaimerMst').submit();			
});
$('#cancelUnitDisclaimer').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/cancelUnitWiseDisclaimerMst.action");	
		$('#DisclaimerMst').submit();			
});
$('#clearId').click(function(e){
	$('[name="disclaimerModel.strDeptCode"]').val('-1');
	$('[name="disclaimerModel.strDeptUnitCode"]').val('-1');
	$('[name="disclaimerModel.strAlignment"]').val('-1');
	$("[name='disclaimerModel.strIsHeader']").filter("[value='0']").prop("checked",true);
	$('[name="disclaimerModel.strUsabilityFlag"]').val('-1');
	$('[name="disclaimerModel.strDisclaimerDesc1"]').val('');
	$('[name="disclaimerModel.strDisclaimerDesc2"]').val('');
	$('[name="disclaimerModel.strDisclaimerDesc3"]').val('');
});
$('#reloadId').click(function(e){
	location.reload("true");
});
</script>
</body>
</html>