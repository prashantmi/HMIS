<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>

<script type="text/javascript">
function printVitalChart(e)
{
	document.getElementsByName('htmlVitalChartData')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
	document.getElementsByName('hmode')[0].value='VITALCHARTPRINT';
	document.forms[0].submit();
}

function callOnLoadEvent(e)
{
	if(document.getElementsByName('hmode')[0].value == 'VITALCHARTPRINT')
	{
		var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=NONE";
		//document.getElementsByName('errorMode')[0].value="NONE";
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}

function closeForm()
{
	self.close();
}
</script>

<body onload="callOnLoadEvent(event)">
<html:form action="/monitorVitals">

<his:TitleTag key="vitalchart">
</his:TitleTag>

	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%"  class="tdfonthead">
					<div align="right">
						<a style="cursor:pointer" onclick="printVitalChart(event);" >
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								PRINT
							</font>
						</a>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>

	<his:ContentTag>
	<div id="pdfPrintingHTMLData">
		<jsp:include page="/hisglobal/utility/generictemplate/chartTile.cnt" flush="true" />
	</div>
	</his:ContentTag>	


<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm();" >
</his:ButtonToolBarTag>

<html:hidden name="MonitorVitalsFB" property="hmode"/>
<html:hidden name="MonitorVitalsFB" property="userSeatId"/>
<html:hidden name="MonitorVitalsFB" property="wardCode"/>
<html:hidden name="MonitorVitalsFB" property="deptUnitCode"/>
<html:hidden name="MonitorVitalsFB" property="episodeCode"/>
<html:hidden name="MonitorVitalsFB" property="episodeVisitNo"/>
<html:hidden name="MonitorVitalsFB" property="admissionNo"/>
<html:hidden name="MonitorVitalsFB" property="deskType"/>
<html:hidden name="MonitorVitalsFB" property="deskId"/>
<html:hidden name="MonitorVitalsFB" property="deskMenuName"/>
<html:hidden name="MonitorVitalsFB" property="htmlVitalChartData"/>

</html:form>
</body>
</html>