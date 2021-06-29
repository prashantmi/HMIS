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

</head>


<body>
<s:form action="Room">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.room"/>&nbsp;<s:text name="global.detail"/>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.room"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col column  width45"><s:textfield key="strRoomName" value="%{roomModel.strRoomName}" 
															name="roomModel.strRoomName" maxlength="20" size="30" > </s:textfield>
				<s:hidden key="strOldRoomName" name="roomModel.strOldRoomName" value="%{roomModel.strRoomName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.location"/></div>
				<div class="div-table-col column  width45"><s:select key="strLocCode" value="%{roomModel.strLocCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.locationList}" listKey="value" listValue="label" name="roomModel.strLocCode" cssStyle="width:197px"></s:select>
				 </div>
			</div>			
			<div id="roomModel.strExpiryMonth" class="div-table-row " >
				<div class="div-table-col label  width50"><s:text name="global.room"/>&nbsp;<s:text name="global.description"/></div>	
				<div class="div-table-col column  width45"><s:textfield key="strRoomDescription" name="roomModel.strRoomDescription" maxlength="50" cssStyle="width:195px"> </s:textfield>
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
			<a href="#" class="button" id="clearlId"><span class="clear"><s:text name="clear"/></span></a>				
			</s:if>
			<s:else >
			<a href="#" class="button" id="updateId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>	
			<a href="#" class="button" id="reloadlId"><span class="clear"><s:text name="clear"/></span></a>			
			</s:else>
	</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strRoomCode" name="roomModel.strRoomCode" value="%{roomModel.strRoomCode}"></s:hidden>
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<h4><font color="#FF0000"><s:property value="%{roomModel.StrWarning}"/></font></h4>
<%-- <h4><font color="#FF0000"><s:property value="%{roomModel.strErrorMsg}"/></font></h4> --%>
<h3><s:property value="message"/></h3>
<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<script type="text/javascript" >
$('[name="roomModel.strRoomName"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
$('[name="roomModel.strLocCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']

});

$('#submitId').click(function(e){
	$("#Room").attr('action',"/HISRegistration/registration/saveRoom.action");
	if($('#Room').form('validate'))
		{
		sortandEncodebase64($("#Room"));
		$('#Room').submit();
		}
			});

$('#cancelId').click(function(e){	
	$("#Room").attr('action',"/HISRegistration/registration/cancelRoom.action");
		$('#Room').submit();			
			});		
$('#clearlId').click(function(e){
	$('[name="roomModel.strRoomName"]').val('');
	$('[name="roomModel.strLocCode"]').val('-1');
	$('[name="roomModel.strRoomDescription"]').val('');
});


$('#updateId').click(function(e){	
	$("#Room").attr('action',"/HISRegistration/registration/updateRoom.action");	
	if($('#Room').form('validate'))
		{
		sortandEncodebase64($("#Room"));
		$('#Room').submit();			
		}
			});
$('#reloadlId').click(function(e){
	location.reload(true);
});

</script>
</body>
</html>