<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 02-Jan-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<link href="./../hisglobal/css/buttons.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/layout.css" rel="stylesheet"	type="text/css"/>
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
</head>
<body>
<s:form action="Location">
<div class="wrapper rounded">

<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.location"/>&nbsp;<s:text name="global.detail"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.location"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strLocDescription" value="%{locModel.strLocDescription}" 
															name="locModel.strLocDescription" maxlength="100" size="30" > </s:textfield>
				<s:hidden key="strOldLocDescription" name="locModel.strOldLocDescription" value="%{locModel.strLocDescription}"></s:hidden>															
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.location"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col column  width45"><s:select key="strLocTypeCode" value="%{locModel.strLocTypeCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.locationType}" listKey="value" listValue="label" name="locModel.strLocTypeCode" cssStyle="width:197px"></s:select>
				 </div>
			</div>			
			<div id="locModel.strExpiryMonth" class="div-table-row " >
				<div class="div-table-col label  width50"><s:text name="global.landmark"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strLandmark" name="locModel.strLandmark" size="30" maxlength="100"> </s:textfield>
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
			<a href="#" class="button" id="clearLocationId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" id="updateId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="clearModifyId"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
	</div>
</div>

</div>

	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strLocCode" name="locModel.strLocCode" value="%{locModel.strLocCode}"></s:hidden>
	
		<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<h4><font color="#FF0000"><s:property value="%{locModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{locModel.strErrorMsg}"/></font></h4> --%>
<h4><s:property value="message"/></h4>
<s:actionerror/>


<div class="div-table">
	<div class="div-table-row   fontError">
		<s:fielderror ></s:fielderror>
	</div>
</div>
 <script type="text/javascript" >

$('[name="locModel.strLocDescription"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
$('[name="locModel.strLocTypeCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
$('#submitId').click(function(e)
		{
		$("#Location").attr('action',"/HISRegistration/registration/saveLocation.action");
		//if($('#Location').form('validate')==true)
			{	
				sortandEncodebase64($("#Location"));
				$('#Location').submit();
			}
});
$('#updateId').click(function(e){
	$("#Location").attr('action',"/HISRegistration/registration/updateLocation.action");
	//if($('#Location').form('validate'))
		{	
			sortandEncodebase64($("#Location"));
			$('#Location').submit();
		}
});


$('#cancelId').click(function(e){
	
	$("#Location").attr('action',"/HISRegistration/registration/cancelLocation.action");
			$('#Location').submit();
});

$('#clearLocationId').click(function(e)
{
	$('[name="locModel.strLocDescription"]').val('');	
	
	$('[name="locModel.strLocTypeCode"]').val('-1');
		
	$('[name="locModel.strLandmark"]').val('');
		});	

	
$('#clearModifyId').click(function(e){
	location.reload(true);
});
</script>

</body>
</html>