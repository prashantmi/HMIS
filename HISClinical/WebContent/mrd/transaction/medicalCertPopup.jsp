<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function Cancel()
{
	window.close();
}

function printConsent(e)
{
	document.getElementsByName('consentHtmlCode')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML; 
	//Added by Vasu on 09.Mar.18 for Base64 encoding
	var htmlData = document.getElementsByName('consentHtmlCode')[0].value;
	var enc = window.btoa(htmlData);
	document.getElementsByName('consentHtmlCode')[0].value = enc;
	document.getElementsByName('hmode')[0].value='PRINT';
	document.forms[0].submit();
}

function callOnLoadEvent(e)
{
	
	if(document.getElementsByName('hmode')[0].value == 'PRINT')
	{
		var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt";
		document.getElementsByName('errorMode')[0].value="NONE";
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}
</script>

<body onload="callOnLoadEvent(event)">
	<form action="/HISClinical/mrd/medicalCertificate.cnt" method="post">
		<his:TitleTag name="Certificate">
			<div align="right">
				<a style="cursor:pointer" onclick="printConsent(event);" >
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						PRINT
					</font>
				</a>
			</div>
		</his:TitleTag>
		
		<his:statusTransactionInProcess>
			<his:ContentTag>
				<bean:define id="strTempId" name="medicalCertificateFB" property="templateId" type="java.lang.String"></bean:define>
				<div id="pdfPrintingHTMLData" align="center">
				<div align="center">
					<table width="80%" cellpadding="0" cellspacing="0" border="1"  ><tr><td width='100%' align="center">
					<his:GenericTemplateTag templateId="<%=strTempId%>"></his:GenericTemplateTag>
					</td></tr></table>
					</div>
				</div>
			</his:ContentTag>
		</his:statusTransactionInProcess>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="Cancel()" onkeypress="if(event.keyCode==13) Cancel()">
		</his:ButtonToolBarTag>
		
		<html:hidden property="hmode" name="medicalCertificateFB" />
		<html:hidden property="templateId" name="medicalCertificateFB" />
		<html:hidden property="consentHtmlCode" name="medicalCertificateFB" />
		<input type="hidden" name="errorMode" />
		
		<center><b><his:status /></b></center>
	</form>
</body>
</html>