<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig;"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:javascript src="/registration/js/policeExaminationReportGenerationDtl.js" />
<his:javascript src="/registration/js/popup.js" />
<script type="text/javascript">
function cancelPopup()
{
	window.close();
}

function printPoliceExamRpt(e)
{
	document.getElementsByName('templateHtmlCode')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
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
<his:TransactionContainer>
		<form action="/HISClinical/registration/policeExaminationReportGenerationDtl.cnt"  method="post">
		<his:TitleTag name="Police Examination Report Template">
			<div align="right">
				<a style="cursor:pointer" onclick="printPoliceExamRpt(event);" >
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						PRINT
					</font>
				</a>
			</div>
		</his:TitleTag>
		<his:statusTransactionInProcess>
		<his:ContentTag>
			<bean:define id="strTempId" name="policeExaminationReportGenerationDtlFB" property="templateId" type="java.lang.String" ></bean:define>
			<div id="pdfPrintingHTMLData">
				<his:GenericTemplateTag templateId="<%=strTempId %>"  ></his:GenericTemplateTag>
				<%--  mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT%>"--%>
			</div>
		</his:ContentTag>
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor: pointer;" tabindex="1" onclick =" cancelPopup()" onkeypress="if(event.keyCode==13) cancelPopup()">
		</his:ButtonToolBarTag>
		
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="patCrNo"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="templateHtmlCode"/>
		<html:hidden name="policeExaminationReportGenerationDtlFB" property="hmode"/>
		<input type="hidden" name="errorMode" />
	
	
		</form>
		<his:status/>
	</his:TransactionContainer>	
</body>

</html>