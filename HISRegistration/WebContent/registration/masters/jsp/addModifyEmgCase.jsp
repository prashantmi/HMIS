<!-- /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
  ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	Emergency Case
 ## Process/Database Object Name		:   Emergency Case Master
 ## Purpose								:	This master is used to capture the Emergency Case used for investigation Process
 ## Date of Creation					:   
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Yogender yadav
 	 Date			: 06-Feb-2014 		-->

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

</script>
</head>
<body>
<s:form action="EmgCaseMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="emergency"/>&nbsp;<s:text name="case"/>&nbsp;<s:text name="global.master"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="emergency"/>&nbsp;<s:text name="case"/></div>
				<div class="div-table-col control width45"><s:textfield key="strEmgCaseDesc" value="%{emgCaseModel.strEmgCaseDesc}" 
															name="emgCaseModel.strEmgCaseDesc" maxlength="50" size="30" > </s:textfield>
				<s:hidden key="strOldEmgCaseDesc" name="emgCaseModel.strOldEmgCaseDesc" value="%{emgCaseModel.strEmgCaseDesc}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="mlc"/>&nbsp;<s:text name="required"/></div>
				<div class="div-table-col control width45">
				<s:radio name="emgCaseModel.strIsMlcRequired" list="#{'1':'Yes','0':'No'}" value="%{emgCaseModel.strIsMlcRequired}"></s:radio>		 
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="case"/>&nbsp;<s:text name="global.type"/></div>
				
				<div class="div-table-col control" style="width: 16%;">
				<s:select key="caseType" value="%{emgCaseModel.caseType}" headerKey="-1" headerValue="Select Value" 
				 				 list="#{'1':'Normal','2':'Trauma','3':'Non Trauma','4':'Ambulatory'}" name="emgCaseModel.caseType" cssStyle="width:197px"></s:select>

					</div>
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
    		<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="modifyId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadId"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
	</div>
</div>

	<%-- <s:property value="%{flagAddMod}"/> --%>
	  <s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden> 
	  <s:hidden name="emgCaseModel.strEmgCaseCode" value="%{emgCaseModel.strEmgCaseCode}"></s:hidden> 
	
	<%-- <s:hidden key="strEmgCaseDesc" name="emgCaseModel.strEmgCaseCode" value="%{emgCaseModel.strEmgCaseCode}"></s:hidden> --%> 
	<cmbPers:cmbPers></cmbPers:cmbPers>
	 <s:token></s:token> 

</s:form>
<s:actionerror/>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{emgCaseModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<%--<h4><font color="#FF0000"><s:property value="%{emgCaseModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript">


$('[name="emgCaseModel.strEmgCaseDesc"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
/* $('[name="emgCaseModel.strEmgCaseCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
}); */
$('[name="emgCaseModel.caseType"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});


//$('[name="emgCaseModel.strEmgCaseDesc"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});

$('#submitId').click(function(e){
	$("#EmgCaseMst").attr('action',"/HISRegistration/registration/saveEmgCaseMst.action");
	//alert("in1");
	if($('#EmgCaseMst').form('validate'))
		{
		
		sortandEncodebase64($("#EmgCaseMst"));
		$('#EmgCaseMst').submit();
		
		}
});
$('#cancelId').click(function(e){	
	$("#EmgCaseMst").attr('action',"/HISRegistration/registration/cancelEmgCaseMst.action");	
		$('#EmgCaseMst').submit();			
});
$('#modifyId').click(function(e){	
	$("#EmgCaseMst").attr('action',"/HISRegistration/registration/updateEmgCaseMst.action");	
	if($('#EmgCaseMst').form('validate'))
		{
		sortandEncodebase64($("#EmgCaseMst"));
		$('#EmgCaseMst').submit();	
		
		}
});
$('#clearId').click(function(e){
	$('[name="emgCaseModel.strEmgCaseDesc"]').val('');
	$('[name="emgCaseModel.strIsMlcRequired"]')[0].checked=true;
	});
$('#reloadId').click(function(e){
	location.reload(true);
});


</script>
</body>
</html>