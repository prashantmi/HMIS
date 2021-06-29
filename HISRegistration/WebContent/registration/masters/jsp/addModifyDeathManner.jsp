<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 06-May-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%> --%>

<link href="/HISRegistration/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HISRegistration/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HISRegistration/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<%-- <script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script> --%>

</head>
<body>

<div class="wrapper rounded">
<s:form action="EmgDeathMst">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="Death"/>&nbsp;<s:text name="Manner"/>&nbsp;<s:text name="global.master"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Death"/>&nbsp;<s:text name="Manner"/></div>
				<div class="div-table-col control width45"><s:textfield key="strEmgDeathManner" value="%{emgDeathModel.strEmgDeathManner}" 
															name="emgDeathModel.strEmgDeathManner" maxlength="50" size="30" > </s:textfield>
				<s:hidden key="strOldEmgDeathManner" name="emgDeathModel.strOldEmgDeathManner" value="%{emgDeathModel.strEmgDeathManner}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Death"/>&nbsp;<s:text name="Manner Description"/></div>
				<div class="div-table-col control width45"><s:textfield key="strEmgDeathMannerDesc" value="%{emgDeathModel.strEmgDeathMannerDesc}" 
															name="emgDeathModel.strEmgDeathMannerDesc" maxlength="50" size="30" > </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Is"/>&nbsp;<s:text name="PostMortem Required"/></div>
				<div class="div-table-col control width45"><s:select key="strIsPostMortem" name="emgDeathModel.strIsPostMortem" value="%{emgDeathModel.strIsPostMortem}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'0':'No','1':'Yes'}" ></s:select>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="Is"/>&nbsp;<s:text name="Accidental"/></div>
				<div class="div-table-col control width45"><s:select key="strIsAccidental" name="emgDeathModel.strIsAccidental" value="%{emgDeathModel.strIsAccidental}" headerKey="-1" headerValue="Select Value" 
				 				 list=" #{'1':'YES','0':'NORMAL','2':'OBS & GYN DEATH'}" ></s:select>
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

	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strEmgDeathMannerCode" name="emgDeathModel.strEmgDeathMannerCode" value="%{emgDeathModel.strEmgDeathMannerCode}"></s:hidden>
<cmbPers:cmbPers></cmbPers:cmbPers>	
<s:token></s:token>	
</s:form>
</div>
<s:actionerror/> 
<div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{emgDeathModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>


<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div> 
<%--<h4><font color="#FF0000"><s:property value="%{emgDeathModel.StrWarning}"/></font></h4>--%>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript">
$('[name="emgDeathModel.strEmgDeathManner"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="emgDeathModel.strEmgDeathMannerDesc"]').validatebox({required: true, validType: 'alphaNumericWithSpaces'});
$('[name="emgDeathModel.strIsPostMortem"]').validatebox({required: true, validType: ['selectCombo[-1]']});
$('[name="emgDeathModel.strIsAccidental"]').validatebox({required: true, validType: ['selectCombo[-1]']});

$('#submitId').click(function(e){
	$("#EmgDeathMst").attr('action',"/HISRegistration/registration/saveEmgDeathMst.action");
	if($('#EmgDeathMst').form('validate'))
		{
		sortandEncodebase64($("#EmgDeathMst"));
		$('#EmgDeathMst').submit();
		}
});
$('#cancelId').click(function(e){	
	$("#EmgDeathMst").attr('action',"/HISRegistration/registration/cancelEmgDeathMst.action");	
		$('#EmgDeathMst').submit();			
});
$('#modifyId').click(function(e){	
	$("#EmgDeathMst").attr('action',"/HISRegistration/registration/updateEmgDeathMst.action");	
	if($('#EmgDeathMst').form('validate'))
		{
		sortandEncodebase64($("#EmgDeathMst"));
		$('#EmgDeathMst').submit();
		}
					
});
$('#clearId').click(function(e){
	$('[name="emgDeathModel.strEmgDeathManner"]').val('');
	$('[name="emgDeathModel.strEmgDeathMannerDesc"]').val('');
	$('[name="emgDeathModel.strIsPostMortem"]').val('0');
	$('[name="emgDeathModel.strIsAccidental"]').val('0');
	});
$('#reloadId').click(function(e){
	location.reload(true);
});


</script>
</body>
</html>