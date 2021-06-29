<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 12-Feb-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
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

function saveDisclaimer()
{
	if(validateIt())
	{
		document.forms[0].action="saveDefaultDisclaimerMst.action";
		document.forms[0].submit();			
	}
}

function cancelDisclaimer()
{
		document.forms[0].action="cancelDefaultDisclaimerMst.action";
		document.forms[0].submit();			
}

function updateDisclaimer()
{
	if(validateIt())
	{
		document.forms[0].action="updateDefaultDisclaimerMst.action";
		document.forms[0].submit();		
	}
}
function clearDisclaimer()
{
	document.getElementsByName("disclaimerModel.strIsHeader")[1].checked="true";
	document.getElementsByName("disclaimerModel.strAlignment")[0].value="1";
	document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc1")[0].value="";
	document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc2")[0].value="";
	document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc3")[0].value="";
	document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc1")[0].value="";
	document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc2")[0].value="";
	document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc3")[0].value="";
	document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc1")[0].value="";
	document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc2")[0].value="";
	document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc3")[0].value="";
	
}


function validateIt()
{
	var isValid=true;
	if(document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc1")[0].value==""){
		alert("Please Enter Disclaimer1..!");
		document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc1")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc2")[0].value==""){
		alert("Please Enter Disclaimer2..!");
		document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc2")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc3")[0].value==""){
		alert("Please Enter Disclaimer3..!");
		document.getElementsByName("disclaimerModel.strGeneralDisclaimerDesc3")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc1")[0].value==""){
		alert("Please Enter Disclaimer1..!");
		document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc1")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc2")[0].value==""){
		alert("Please Enter Disclaimer2..!");
		document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc2")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc3")[0].value==""){
		alert("Please Enter Disclaimer3..!");
		document.getElementsByName("disclaimerModel.strSpecialDisclaimerDesc3")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc1")[0].value==""){
		alert("Please Enter Disclaimer1..!");
		document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc1")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc2")[0].value==""){
		alert("Please Enter Disclaimer2..!");
		document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc2")[0].focus();
		isValid=false;		
	}
	else if(document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc3")[0].value==""){
		alert("Please Enter Disclaimer3..!");
		document.getElementsByName("disclaimerModel.strCasualityDisclaimerDesc3")[0].focus();
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
						<s:text name="global.default"/>&nbsp;<s:text name="global.disclaimer"/>						
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if>
						<s:else >>><s:text name="global.modify"/></s:else >
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label width25"><s:text name="global.location"/></div>
				<div class="div-table-col column width25"><s:radio key="strIsHeader" id="disclaimerModel.strIsHeader" name="disclaimerModel.strIsHeader" value="%{disclaimerModel.strIsHeader}"
						     												list="#{'1':'Header','0':'Footer'}"> </s:radio> 
				</div>				
				<div class="div-table-col label width25"><s:text name="global.alignment"/></div>
				<div class="div-table-col column width25"><s:select key="strAlignment" value="%{disclaimerModel.strAlignment}"
 				 															list="#{'1':'Center','2':'Right','3':'Left'}" name="disclaimerModel.strAlignment"></s:select>			
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col title width100 "><s:text name="disclaimer.normalcard"/>
				</div>
			</div>			
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer1"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strGeneralDisclaimerDesc1" name="disclaimerModel.strGeneralDisclaimerDesc1" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer2"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strGeneralDisclaimerDesc2" name="disclaimerModel.strGeneralDisclaimerDesc2" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer3"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strGeneralDisclaimerDesc3" name="disclaimerModel.strGeneralDisclaimerDesc3" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>		
			<div class="div-table-row ">
				<div class="div-table-col title width100 "><s:text name="disclaimer.specialcard"/>
				</div>
			</div>			
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer1"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strSpecialDisclaimerDesc1" name="disclaimerModel.strSpecialDisclaimerDesc1" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer2"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strSpecialDisclaimerDesc2" name="disclaimerModel.strSpecialDisclaimerDesc2" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer3"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strSpecialDisclaimerDesc3" name="disclaimerModel.strSpecialDisclaimerDesc3" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col title width100 "><s:text name="disclaimer.casualitycard"/>
				</div>
			</div>			
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer1"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strCasualityDisclaimerDesc1" name="disclaimerModel.strCasualityDisclaimerDesc1" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer2"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strCasualityDisclaimerDesc2" name="disclaimerModel.strCasualityDisclaimerDesc2" maxlength="100" size="110"> </s:textfield>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width20"><s:text name="global.disclaimer3"/></div>	
				<div class="div-table-col column  width80"><s:textfield key="strCasualityDisclaimerDesc3" name="disclaimerModel.strCasualityDisclaimerDesc3" maxlength="100" size="110"> </s:textfield>
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
			<s:if test="flagAddMod=='MODIFY'">
    		<a href="#" class="button" id="modifyId"><span class="save"><s:text name="save"/></span></a>
    		<a href="#" class="button" id="cancelId"><span class="cancel"><s:text name="cancel"/></span></a>
    		<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
    		</s:if>
				
	</div>
</div>
</div>
	
	<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
	<s:hidden key="strDisclaimerCode" name="disclaimerModel.strDisclaimerCode" value="%{disclaimerModel.strDisclaimerCode}"></s:hidden>
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
$('#modifyId').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/updateDefaultDisclaimerMst.action");	
	if($('#DisclaimerMst').form('validate'))
		{
		sortandEncodebase64($("#DisclaimerMst"));
		$('#DisclaimerMst').submit();
		}
					
});
$('#cancelId').click(function(e){	
	$("#DisclaimerMst").attr('action',"/HISRegistration/registration/cancelDefaultDisclaimerMst.action");	
		$('#DisclaimerMst').submit();			
});
$('#clearId').click(function(e){
	$('[name="disclaimerModel.strGeneralDisclaimerDesc1"]').val('');
	$('[name="disclaimerModel.strGeneralDisclaimerDesc2"]').val('');
	$('[name="disclaimerModel.strGeneralDisclaimerDesc3"]').val('');
	$('[name="disclaimerModel.strSpecialDisclaimerDesc1"]').val('');
	$('[name="disclaimerModel.strSpecialDisclaimerDesc2"]').val('');
	$('[name="disclaimerModel.strSpecialDisclaimerDesc3"]').val('');
	$('[name="disclaimerModel.strCasualityDisclaimerDesc1"]').val('');
	$('[name="disclaimerModel.strCasualityDisclaimerDesc2"]').val('');
	$('[name="disclaimerModel.strCasualityDisclaimerDesc3"]').val('');
});
</script>
</body>
</html>