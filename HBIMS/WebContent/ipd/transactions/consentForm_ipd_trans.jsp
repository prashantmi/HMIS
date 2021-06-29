
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>
<head>
<meta charset=utf-8>
<title>Consent Form</title>

<script type="text/javascript">
function Cancel()
{
	window.close();
}

function printConsent(e)
{
	document.getElementsByName('consentHtmlCode')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
	document.getElementsByName('hmode')[0].value='PRINT';
	document.forms[0].submit();	
}

function callOnLoadEvent(e)
{
	if(document.getElementsByName('hmode')[0].value == 'PRINT')
	{
		var url="/AHIMS/hisglobal/utility/generictemplate/printingTile.cnt";
		document.getElementsByName('errorMode')[0].value="NONE";
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}
</script>

</head>
<body onload='callOnLoadEvent(event)'>
<html:form action="/transactions/PatientFinalDischargeCNT.cnt" method="post">
<% 

//String strConsenttemplateId=request.getParameter("consentTemplateId").toString(); %>

<div align="right">
				<a style="cursor:pointer" onclick="printConsent(event);" >
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						PRINT
					</font>
				</a>
</div>

<bean:define id="strConsentTemplateId" name="patientDischargeBean" property="strConsentTemplateId" type="java.lang.String"></bean:define>
<div id="pdfPrintingHTMLData">
	<his:GenericTemplateTag templateId="<%=strConsentTemplateId%>"></his:GenericTemplateTag>
</div>

<%-- <his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="Cancel()" onkeypress="if(event.keyCode==13) Cancel()">
</his:ButtonToolBarTag>	--%>


<input type='hidden' name="consentHtmlCode" >
<input type='hidden' name="strConsentTemplateId" >
<html:hidden property="hmode" name="patientDischargeBean" />
<input type="hidden" name="errorMode" />
</html:form>
</body>
</html>