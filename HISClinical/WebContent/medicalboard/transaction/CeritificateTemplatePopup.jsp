<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="investigation.InvestigationStaticConfigurator"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PatientImageDtlVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />

<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar-setup.js" />
<his:javascript src="/registration/js/registration.js" />

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />

<script type="text/javascript">
function Cancel(mode)

{
		CancelClear();	
		window.close();

//window.opener.document.getElementById(Client ID Of Button).click();		
}
function CancelClear()
{
	if(document.getElementsByName('hmode')[0].value!='PRINT')
	{
			window.opener.clearForm();		
			window.opener.submitPage('NEW')
	}
}
function printTemplate(e)
{
	document.getElementsByName('templateHtmlCode')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
	document.getElementsByName('hmode')[0].value='PRINT';
	document.forms[0].submit();
}

function callOnLoadEvent(e)
{
	if(document.getElementsByName('hmode')[0].value == 'PRINT')
	{
		var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?modePrint=ONLYHTML";
		document.getElementsByName('errorMode')[0].value="NONE";
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}
</script>

<body onunload="CancelClear()" onload="callOnLoadEvent(event)">
<form action="/HISClinical/medicalboard/certificateHandover.cnt" method="post">
<his:TitleTag name="CERTIFICATE">
	<div align="right"><a style="cursor: pointer"
		onclick="printTemplate(event);"> <font color="#0000FF" size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> PRINT </font> </a></div>
</his:TitleTag>

<his:ContentTag>
	<bean:define id="tempId" name="CertificateHandoverFB"
			property="templateId" type="java.lang.String"></bean:define>
		<div id="pdfPrintingHTMLData">
		<table width="100%">
		<tr>
		<td width="90%">
		<his:GenericTemplateTag templateId='<%=tempId%>' ></his:GenericTemplateTag>
		</td>
		<logic:equal name="CertificateHandoverFB" property="isPatient" value="1">
		<td  width="10%" style="height: 20px" class="tdfont" valign="top">
				<div align="center">
					<%//String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; %>
					<%//String imagepath="/image/showImage?"+ imageIndex+"="+0; %>
					
				<% 
				
				PatientImageDtlVO patVo = (PatientImageDtlVO)session.getAttribute(MrdConfig.PATIENT_IMAGE_DTL_VO_LIST);
				String displayFileServletURL = ServletsUtilityConfig.SERVLET_DISPLAY_FTP_FILE_URL+ "?" 
					+ ServletsUtilityConfig.APP_SERVER_MAIN_FOLDER_WINDOWS + "=" + Config.PATH_APPLICATION_SERVER_FILE_STORAGE_WINDOWS 
					+ "&" + ServletsUtilityConfig.APP_SERVER_MAIN_FOLDER_LINUX + "=" + Config.PATH_APPLICATION_SERVER_FILE_STORAGE_LINUX 
					+ "&" + ServletsUtilityConfig.FTP_SERVER_MAIN_FOLDER + "=" + InvestigationStaticConfigurator.resultprintingftpaddress 
					+ "&" + ServletsUtilityConfig.FILE_TARGET_FOLDER + "=" + Config.TARGET_FOLDER_PATIENT_IMAGE 
					+ "&" + ServletsUtilityConfig.FILE_NAME + "="+ patVo.getFileNo();
		 %>
	    	<img id="imgPat" style=cursor:pointer src="<%=displayFileServletURL%>" height="100px" title="Click To Enlarge" >

				</div>
		</td>
		</logic:equal>
		</tr>
		</table>
		</div>
</his:ContentTag>

<his:ButtonToolBarTag>
	<img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		style="cursor: pointer" tabindex="1" onclick="Cancel('NEW'); "
		onkeypress="if(event.keyCode==13) Cancel('NEW'); ">
</his:ButtonToolBarTag>

<html:hidden property="hmode" name="CertificateHandoverFB" /> <html:hidden
	property="flagClose" name="CertificateHandoverFB" value="0" /> <html:hidden
	property="templateId" name="CertificateHandoverFB" /> <html:hidden
	property="templateHtmlCode" name="CertificateHandoverFB" />
	<input type="hidden" name="errorMode" />

</form>
</body>
</html>