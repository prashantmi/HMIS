<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Aadil Wasi
 	 Date			: Dec 2013 -->

<%@ taglib uri="/struts-tags" prefix="s"%>
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

<link href="./../hisglobal/css/buttons.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/layout.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<body>
	<s:form action="Occupation" validate="true">


		<div class="wrapper rounded">
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col  width100 "><s:text name="occupation"/>&nbsp;<s:text name="global.master"/></div>
				</div>

				<div class="div-table-row ">
					<div class="div-table-col title width100 ">
						<s:if test="flagAddMod=='ADD'">
							<s:text name="global.add"/>&nbsp;<s:text name="occupation"/>&nbsp;<s:text name="global.details"/> >>
					</s:if>
						<s:else>
							<s:text name="global.modify"/>&nbsp;<s:text name="occupation"/>&nbsp;<s:text name="global.details"/> >>
					</s:else>
					</div>
				</div>
			</div>
			<div class='div-table'>
				<div class="div-table-row ">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="occupation"/>&nbsp;<s:text name="global.name"/>:
					</div>
					<div class="div-table-col  column width45">
						<s:textfield key="Occupation Name"
							name="VOOccupation.strOccupationName" size='50' maxlength="100">
						</s:textfield>
						<s:hidden name="VOOccupation.strOldOccupationName" value="%{VOOccupation.strOccupationName}"></s:hidden>
					</div>
				</div>

			</div>

			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col  width100 height20 "></div>
				</div>


			</div>

			<div class="div-table-button">
				<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
				</div>
					<div class="div-table-row">
					<div class="div-table-col emptyBar"></div>
				</div>
				<div class="div-table-row" align='center'>
					<s:if test="flagAddMod=='ADD'">
						<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
						<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
						<a href="#" class="button" id="clearOccupationID"><span	class="clear"><s:text name="clear"/></span></a>
					</s:if>
					<s:else>
						<a href="#" class="button" id="updateid"><span class="save"><s:text name="save"/></span></a>
						<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
						<a href="#" class="button" id="clearUpdateOccupationID"><span	class="clear"><s:text name="clear"/></span></a>
					</s:else>
				</div>
			</div>



		</div>

		<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
		<s:hidden name="VOOccupation.strOccupationId"
			value="%{VOOccupation.strOccupationId}"></s:hidden>

<cmbPers:cmbPers></cmbPers:cmbPers>
 <s:token></s:token>
</s:form>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<s:actionerror/>

 <div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{VOOccupation.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message" /></div>


<%-- <h4><font color="#FF0000"><s:property value="%{VOOccupation.strErrorMsg}"/></font></h4> --%>

<%-- <h4><font color="#FF0000"><s:property value="%{VOOccupation.strErrorMsg}"/></font></h4> --%>

<script type="text/javascript" >

$('[name="VOOccupation.strOccupationName"]').validatebox({required: true,	validType: 'alphaWithSpaceandComma'});
$('#submitId').click(function(e){
		$("#Occupation").attr('action',"/HISRegistration/registration/saveOccupation.action");
		if($('#Occupation').form('validate')==true)
			{
			    sortandEncodebase64($("#Occupation"));
				$('#Occupation').submit();
		    }
});
	
		$('#cancelId').click(function(e){
			
			$("#Occupation").attr('action',"/HISRegistration/registration/cancelOccupation.action");
					$('#Occupation').submit();
		});
		
		
		
		$('#updateid').click(function(e){
				$("#Occupation").attr('action',"/HISRegistration/registration/updateOccupation.action");
				if($('#Occupation').form('validate')==true)
					{
					    sortandEncodebase64($("#Occupation"));
						$('#Occupation').submit();
					}
		});
		
$('#clearOccupationID').click(function(e){
	$('[name="VOOccupation.strOccupationName"]').val('');	
		});
		
$('#clearUpdateOccupationID').click(function(e){
	$("#Occupation").attr('action',"/HISRegistration/registration/modifyOccupation.action");
	if($('#Occupation').form('validate')==true)
		{
			$('#Occupation').submit();
			}
});
	
</script>
</body>
</html>