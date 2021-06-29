<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 06-May-2014 		-->

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
<s:form action="EmgMlcCaseTypeMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Mlc"/>&nbsp;<s:text name="Case Type"/>&nbsp;<s:text name="global.master"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Mlc"/>&nbsp;<s:text name="Type"/></div>
				<div class="div-table-col control width45"><s:textfield key="strEmgMlcCaseTypeDesc" value="%{emgMlcCaseTypeModel.strEmgMlcCaseTypeDesc}" 
															name="emgMlcCaseTypeModel.strEmgMlcCaseTypeDesc" maxlength="50" size="30" > </s:textfield>
				<s:hidden key="strOldEmgMlcCaseTypeDesc" name="emgMlcCaseTypeModel.strOldEmgMlcCaseTypeDesc" value="%{emgMlcCaseTypeModel.strEmgMlcCaseTypeDesc}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Is"/>&nbsp;<s:text name="Associated"/></div>
				<div class="div-table-col control width45">
				<s:radio name="emgMlcCaseTypeModel.strIsMlcBound" list="#{'1':'MLC','2':'Injury','3':'Both'}" value="%{emgMlcCaseTypeModel.strIsMlcBound}"></s:radio>		 
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
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strEmgMlcCaseTypeCode" name="emgMlcCaseTypeModel.strEmgMlcCaseTypeCode" value="%{emgMlcCaseTypeModel.strEmgMlcCaseTypeCode}"></s:hidden>
	
	<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token></s:token>

</s:form>
<s:actionerror/>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{emgMlcCaseTypeModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<%--<h4><font color="#FF0000"><s:property value="%{emgMlcCaseTypeModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript">
$('[name="emgMlcCaseTypeModel.strEmgMlcCaseTypeDesc"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});

$('#submitId').click(function(e){
	$("#EmgMlcCaseTypeMst").attr('action',"/HISRegistration/registration/saveEmgMlcCaseTypeMst.action");
	if($('#EmgMlcCaseTypeMst').form('validate'))
		{
		sortandEncodebase64($("#EmgMlcCaseTypeMst"));
		$('#EmgMlcCaseTypeMst').submit();
		}
});
$('#cancelId').click(function(e){	
	$("#EmgMlcCaseTypeMst").attr('action',"/HISRegistration/registration/cancelEmgMlcCaseTypeMst.action");	
		$('#EmgMlcCaseTypeMst').submit();			
});
$('#modifyId').click(function(e){	
	$("#EmgMlcCaseTypeMst").attr('action',"/HISRegistration/registration/updateEmgMlcCaseTypeMst.action");	
	if($('#EmgMlcCaseTypeMst').form('validate'))
		{
		sortandEncodebase64($("#EmgMlcCaseTypeMst"));
		$('#EmgMlcCaseTypeMst').submit();	
		}
					
});
$('#clearId').click(function(e){
	$('[name="emgMlcCaseTypeModel.strEmgMlcCaseTypeDesc"]').val('');
	$('[name="emgMlcCaseTypeModel.strIsMlcBound"]')[0].checked=true;
	});
$('#reloadId').click(function(e){
	location.reload(true);
});


</script>
</body>
</html>