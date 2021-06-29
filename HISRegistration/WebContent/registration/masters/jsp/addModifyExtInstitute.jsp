<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 22-Jan-2014 		-->

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
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>


/*function validateCheck()
{
	var retValue=true;
	if(document.getElementsByName("extInstituteModel.strInstituteName")[0].value=="")
	{
		alert("Please Enter Institute Name!");
		document.getElementsByName("extInstituteModel.strInstituteName")[0].focus();
		retValue=false;
	}
	else if(document.getElementsByName("extInstituteModel.strShortName")[0].value=="")
	{
		alert("Please Enter Institute Short Name!");
		document.getElementsByName("extInstituteModel.strShortName")[0].focus();
		retValue=false;
	}
	else if(document.getElementsByName("extInstituteModel.strInstituteType")[0].value=="-1")
	{
		alert("Please Select Institute Type!");
		document.getElementsByName("extInstituteModel.strInstituteType")[0].focus();
		retValue=false;
	}
	else if(document.getElementsByName("extInstituteModel.strCity")[0].value=="")
	{
		alert("Please Enter City!");
		document.getElementsByName("extInstituteModel.strCity")[0].focus();
		retValue=false;
	}
	else if(document.getElementsByName("extInstituteModel.strStateCode")[0].value=="-1")
	{
		alert("Please Select State!");
		document.getElementsByName("extInstituteModel.strStateCode")[0].focus();
		retValue=false;
	}
	
	return retValue;

}


function saveExtInstituteMstAction()
{
	if(validateCheck())
	{
		document.forms[0].action="saveExtInstituteMst.action";
		document.forms[0].submit();
	}
	
}

function updateExtInstituteMst()
{
	if(validateCheck())
	{
		document.forms[0].action="updateExtInstituteMst.action";
		document.forms[0].submit();
	}
}

function cancelExtInstituteMst()
{
	document.forms[0].action="cancelExtInstituteMst.action";
	document.forms[0].submit();
}*/

</script>
</head>

<s:form action="ExtInstituteMst">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.extinstitute"/>&nbsp;<s:text name="global.master"/>
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.hospital"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col control width45"><s:radio list="#{'0':'Not Associated','1':'Associated'}" key="strIsAssociated" 
															name="extInstituteModel.strIsAssociated"></s:radio>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.institute"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strInstituteName" value="%{extInstituteModel.strInstituteName}" 
															name="extInstituteModel.strInstituteName" maxlength="100" size="30" ></s:textfield>
				<s:hidden key="strOldInstituteName" name="extInstituteModel.strOldInstituteName" value="%{extInstituteModel.strInstituteName}"></s:hidden>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.institute"/>&nbsp;<s:text name="global.short"/>&nbsp;<s:text name="global.name"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strShortName" value="%{extInstituteModel.strShortName}" 
															name="extInstituteModel.strShortName" maxlength="10" ></s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.institute"/>&nbsp;<s:text name="global.type"/></div>
				<div class="div-table-col control  width45"><s:select key="strInstituteType" value="%{extInstituteModel.strInstituteType}" headerKey="-1" headerValue="Select Value" 
				 				 list="#{'1':'Govt Hospital','2':'Dispensary','3':'Private Hospital','4':'Nursing Home'}" name="extInstituteModel.strInstituteType" ></s:select>
				 </div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="address"/>1</div>
				<div class="div-table-col control  width45"><s:textarea key="strAddress1" value="%{extInstituteModel.strAddress1}" rows="2" cols="16"
															name="extInstituteModel.strAddress1" ></s:textarea>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="address"/>2</div>
				<div class="div-table-col control  width45"><s:textarea key="strAddress2" value="%{extInstituteModel.strAddress2}" rows="2" cols="16"
															name="extInstituteModel.strAddress2" ></s:textarea>
				</div>
			</div>	
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.city"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strCity" value="%{extInstituteModel.strCity}" 
															name="extInstituteModel.strCity" maxlength="40" ></s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.state"/></div>
				<div class="div-table-col control  width45"><s:select key="strStateCode" value="%{extInstituteModel.strStateCode}" headerKey="-1" headerValue="Select Value" 
				 				 list="%{#session.stateList}" name="extInstituteModel.strStateCode" listKey="value" listValue="label"></s:select>
				 </div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="extinstitute.contperson"/></div>
		<div class="div-table-col control  width45"><s:textfield key="strContactPerson" value="%{extInstituteModel.strContactPerson}" 
															name="extInstituteModel.strContactPerson" maxlength="50"></s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.phone"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strPhone" value="%{extInstituteModel.strPhone}" 
															name="extInstituteModel.strPhone" maxlength="13" ></s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.fax"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strFax" value="%{extInstituteModel.strFax}" 
															name="extInstituteModel.strFax" maxlength="20" ></s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.email"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strEmail" value="%{extInstituteModel.strEmail}" 
															name="extInstituteModel.strEmail" maxlength="50" ></s:textfield>
				</div>
			</div>
<!-- 			<div class="div-table-row "> -->
<%-- 				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="shift.active"/></div> --%>
<%-- 				<div class="div-table-col control  width45"><s:select key="strIsValid" value="%{extInstituteModel.strIsValid}"   --%>
<%-- 				 				 list="#{'1':'Active','2':'InActive'}" name="extInstituteModel.strIsValid" ></s:select> --%>
<!-- 				 </div> -->
<!-- 			</div> -->
			<s:if test="flagAddMod=='MODIFY'">
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><s:text name="global.remarks"/></div>
				<div class="div-table-col control  width45"><s:textfield key="strRemarks" value="%{extInstituteModel.strRemarks}" 
															name="extInstituteModel.strRemarks" maxlength="50" size="30" ></s:textfield>
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
    		<a href="#" class="button" Id="submitId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" Id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" Id="clearId"><span class="clear"><s:text name="clear"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" Id="updateId"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" Id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
			<a href="#" class="button" id="reloadlId"><span class="clear"><s:text name="clear"/></span></a>
			</s:else>
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strInstituteCode" name="extInstituteModel.strInstituteCode" value="%{extInstituteModel.strInstituteCode}"></s:hidden>
	<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<h4><font color="#FF0000"><s:property value="%{extInstituteModel.StrWarning}"/></font></h4>
<h4><s:property value="message"/></h4>
<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<script type="text/javascript" >
$('[name="extInstituteModel.strInstituteName"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
$('[name="extInstituteModel.strShortName"]').validatebox({required: true,	validType:  'alphaNumericWithSpaces'});
$('[name="extInstituteModel.strInstituteType"]').validatebox({required: true,
	validType: ['selectCombo[-1]']
});
//Modify Date				: 28thNov'14
//Reason	(CR/PRS)		: Bug Id 7592
//Modify By                 : Sheeldarshi
//$('[name="extInstituteModel.strAddress1"]').validatebox({	validType: ' alphaSpecialChar1'});
//$('[name="extInstituteModel.strAddress2"]').validatebox({	validType: ' alphaSpecialChar1'});
$('[name="extInstituteModel.strAddress1"]').validatebox({	validType: ' alphaNumSpecialChar'});
$('[name="extInstituteModel.strAddress2"]').validatebox({	validType: ' alphaNumSpecialChar'});
//End:Sheeldarshi
$('[name="extInstituteModel.strCity"]').validatebox({required: true,	validType: 'alphaNumericWithSpaces'});
$('[name="extInstituteModel.strStateCode"]').validatebox({required: true,
	validType: ['selectCombo[-1]']
	});
$('[name="extInstituteModel.strContactPerson"]').validatebox({	validType: 'alphaNumericWithSpaces'});
$('[name="extInstituteModel.strPhone"]').validatebox({	validType: 'contactNo'});
$('[name="extInstituteModel.strFax"]').validatebox({validType: 'numeric'});
$('[name="extInstituteModel.strEmail"]').validatebox({	validType: 'email'});


$('#submitId').click(function(e){
	$("#ExtInstituteMst").attr('action',"/HISRegistration/registration/saveExtInstituteMst.action");
	if($('#ExtInstituteMst').form('validate'))
		{
		sortandEncodebase64($("#ExtInstituteMst"));
		$('#ExtInstituteMst').submit();
		}
			});
$('#cancelId').click(function(e){	
	$("#ExtInstituteMst").attr('action',"/HISRegistration/registration/cancelExtInstituteMst.action");
		$('#ExtInstituteMst').submit();			
			});


$('#clearId').click(function(e){
	$('[name="extInstituteModel.strInstituteName"]').val('');
	$('[name="extInstituteModel.strShortName"]').val('');
    $('[name="extInstituteModel.strInstituteType"]').val('-1');
	$('[name="extInstituteModel.strAddress1"]').val('');
	$('[name="extInstituteModel.strAddress2"]').val('');
    $('[name="extInstituteModel.strCity"]').val('');
	$('[name="extInstituteModel.strStateCode"]').val('-1');
	$('[name="extInstituteModel.strContactPerson"]').val('');
    $('[name="extInstituteModel.strPhone"]').val('');
    $('[name="extInstituteModel.strFax"]').val('');
    $('[name="extInstituteModel.strEmail"]').val('');
//    $('[name="extInstituteModel.strIsValid"]').val('');
    $('[name="extInstituteModel.strIsAssociated"]').filter("[value='0']").prop("checked",true);
});

$('#updateId').click(function(e){	
	$("#ExtInstituteMst").attr('action',"/HISRegistration/registration/updateExtInstituteMst.action");	
	if($('#ExtInstituteMst').form('validate'))
		{
		sortandEncodebase64($("#ExtInstituteMst"));
		$('#ExtInstituteMst').submit();
		
		}
			});
$('#reloadlId').click(function(e){
	location.reload(true);
});


	</script>
</body>
</html>