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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@page import="inpatient.InpatientConfig"%>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

</script>
<html>
<html:form action="/nurBloodTransfusion">
<body>
<his:TransactionContainer>
	<his:TitleTag name="Blood Bag Detail">
	</his:TitleTag>
	<his:ContentTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="10%" class="tdfonthead">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
						key="bloodBagNo" /> </b> </font></div>
					</td>
			<td width="8%" class="tdfonthead">
				<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
						key="bloodGroup" /> </b> </font></div>
					</td>
					<td width="15%" class="tdfonthead">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
						key="component" /> </b> </font></div>
					</td>

					<td width="8%" class="tdfonthead">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
						key="expiryDate" /> </b> </font></div>
					</td>		
		</tr>
		<tr>
			<td width="8%" class="tdfont">
						<div align="center"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:write
							name="BloodTransfusionFB" property="popupBloodBagNo" /> </b> </font></div>
						</td>
			<td width="8%" class="tdfont">
						<div align="center"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:write
							name="BloodTransfusionFB" property="popupBloodgroup" /> </b> </font></div>
						</td>
						<td width="15%" class="tdfont">
						<div align="center"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:write
							name="BloodTransfusionFB" property="popupcomponent" /> </b> </font></div>
						</td>

						<td width="8%" class="tdfont">
						<div align="center"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:write
							name="BloodTransfusionFB" property="popupBagExpiry" /> </b> </font></div>
						</td>
		</tr>
	</table>
	</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
</his:TransactionContainer>
</body>
</html:form>
</html>