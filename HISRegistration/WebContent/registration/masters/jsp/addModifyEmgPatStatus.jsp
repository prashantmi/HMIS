<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 05-May-2014 		-->

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
<s:form action="EmgPatStatusMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Patient"/>&nbsp;<s:text name="Status"/>&nbsp;<s:text name="global.master"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name=" Patient"/>&nbsp;<s:text name="Status"/></div>
				<div class="div-table-col control width45"><s:textfield key="strEmgPatStatusDesc" value="%{emgPatStatusModel.strEmgPatStatusDesc}" 
															name="emgPatStatusModel.strEmgPatStatusDesc" maxlength="50" size="30" > </s:textfield>
				<s:hidden key="strOldEmgPatStatusDesc" name="emgPatStatusModel.strOldEmgPatStatusDesc" value="%{emgPatStatusModel.strEmgPatStatusDesc}"></s:hidden>
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
	<s:hidden key="strEmgPatStatusCode" name="emgPatStatusModel.strEmgPatStatusCode" value="%{emgPatStatusModel.strEmgPatStatusCode}"></s:hidden>
	<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token></s:token>

</s:form>
<s:actionerror/>
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{emgPatStatusModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<%--<h4><font color="#FF0000"><s:property value="%{emgPatStatusModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript">
$('[name="emgPatStatusModel.strEmgPatStatusDesc"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});

$('#submitId').click(function(e){
	$("#EmgPatStatusMst").attr('action',"/HISRegistration/registration/saveEmgPatStatusMst.action");
	if($('#EmgPatStatusMst').form('validate'))
		{
		sortandEncodebase64($("#EmgPatStatusMst"));
		$('#EmgPatStatusMst').submit();
		
		}
});
$('#cancelId').click(function(e){	
	$("#EmgPatStatusMst").attr('action',"/HISRegistration/registration/cancelEmgPatStatusMst.action");	
		$('#EmgPatStatusMst').submit();			
});
$('#modifyId').click(function(e){	
	$("#EmgPatStatusMst").attr('action',"/HISRegistration/registration/updateEmgPatStatusMst.action");	
	if($('#EmgPatStatusMst').form('validate'))
		{
		sortandEncodebase64($("#EmgPatStatusMst"));
		$('#EmgPatStatusMst').submit();
		}
				
});
$('#clearId').click(function(e){
	$('[name="emgPatStatusModel.strEmgPatStatusDesc"]').val('');
	});
$('#reloadId').click(function(e){
	location.reload(true);
});


</script>
</body>
</html>