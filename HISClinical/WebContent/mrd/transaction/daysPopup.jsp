<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="inpatient.InpatientConfig"%>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

</script>
<html>
<html:form action="/patTreatmentDetailTile">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TitleTag name="Extended Daya Detail">
</his:TitleTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="10%" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="days" />
						</b>
					</font>
				</div>
			</td>
			<td width="8%" class="tdfont">
				<div align="left">
					<html:text property="extensionDays" name="PatientTreatmentDetailFB"></html:text>
				</div>
			</td>		
		</tr>
	</table>

</html:form>
<body>
</html>