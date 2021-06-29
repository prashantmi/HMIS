<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opd.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitFormOnValidateButton(id,menuId)
{
	//  alert("menuId "+menuId)
	// alert("document "+parent.document.getElementById("opdReportDeskFrame"))
	parent.document.getElementById("opdReportDeskFrame").src="/HISClinical/opd/report.cnt?mode="+id;
} 



</script>
<his:statusInProcess>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">

			<logic:iterate id="buttonList"
				name="<%=opd.OpdConfig.OPD_REPORT_DESK_LEFT_MENU_DTL%>">
				<tr>
					<his:opdDeskButtonTag
						name="buttonList">
					</his:opdDeskButtonTag>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusInProcess> <his:status />


