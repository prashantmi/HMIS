<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@ page import="java.util.*"%>
<%@page import="opd.*"%>
<%@page import="hisglobal.utility.Entry"%>


<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/templateFunctions.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">

function submitMyForm(flag,mode)
{
	if(flag)
	{
		document.getElementsByName('hmode')[0].value=mode;
		submitFormOnValidate(flag,mode);
	}
}

</script>


<his:SubTitleTag name=''>
	<div align="left"><bean:write name="OpdTemplateTabFB"  property="deskMenuName"/></div>
</his:SubTitleTag>
	
<his:statusTransactionInProcess>

	<logic:equal name="OpdTemplateTabFB" property="choice" value="<%=OpdConfig.CHOICE_PREVVISIT_TEMP_WISE%>" >
	<logic:iterate name="<%=OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST%>" id="tempBean" >
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%">
						<his:OpdTempReportTag name="tempBean"></his:OpdTempReportTag>
					</td>
				</tr>
			</table>
		</his:ContentTag>
	</logic:iterate>
	</logic:equal>

	<logic:equal name="OpdTemplateTabFB" property="choice" value="<%=OpdConfig.CHOICE_PREVVISIT_PARA_WISE%>" >
		<!-- <table width="100%" cellspacing="1" cellpadding="0"> -->
			<!-- <bean:write name="OpdTemplateTabFB" property="consentHtmlToPrint" filter="false"/> -->
			<%
				List lst1 =(ArrayList)session.getAttribute(OpdConfig.OPD_PREV_VISIT_PARA_WISE_REP_LIST);
				if(lst1.size()>0)
				{
			%>
			<logic:iterate name="<%= OpdConfig.OPD_PREV_VISIT_PARA_WISE_REP_LIST %>" id="tempBean" >
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr><td width="100%" colspan="2"> <b><bean:write name="tempBean" property="label"/></b> </td></tr>
							<%
								String visitNo=((Entry)tempBean).getValue();
								Map mp= (HashMap)session.getAttribute(OpdConfig.OPD_PREV_VISIT_PARA_WISE_REP_MAP);
								ArrayList lst=(ArrayList)mp.get(visitNo);
							%>
							<logic:iterate collection="<%=lst%>" id="inner" >
							<tr>
								<td class="tdfonthead" width="50%"><bean:write name="inner" property="value" />&nbsp;</td>
								<td class="tdfont" width="50%">&nbsp;<bean:write name="inner" property="label" /> </td>
							</tr>
							</logic:iterate>
					</table>
				</his:ContentTag>
			</logic:iterate>
			<%
				}
				else
				{
			%>
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr><td width="100%" colspan="2" class="tdfont" ></td></tr>
					<tr>
						<td width="100%" colspan="2" class="tdfont" > 
							<font color="#FF0000"><b>No Previous Visit exists between the Dates ..</b></font> 
						</td>
					</tr>
					<tr><td width="100%" colspan="2" class="tdfont" ></td></tr>
				</table>
			<%
				}
			%>
		<!-- </table> -->
	</logic:equal>


</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
</his:ButtonToolBarTag>

<html:hidden name="OpdTemplateTabFB" property="entryDate"/>
<html:hidden name="OpdTemplateTabFB" property="hmode"/>
<html:hidden name="OpdTemplateTabFB" property="userSeatId"/>
<html:hidden name="OpdTemplateTabFB" property="departmentUnitCode"/>
<html:hidden name="OpdTemplateTabFB" property="patCrNo"/>
<html:hidden name="OpdTemplateTabFB" property="episodeCode"/>
<html:hidden name="OpdTemplateTabFB" property="episodeVisitNo"/>
<html:hidden name="OpdTemplateTabFB" property="deskMenuName"/>
<html:hidden name="OpdTemplateTabFB" property="consentHtmlToPrint"/>
<html:hidden name="OpdTemplateTabFB" property="selUndefaultTemp" value=""/>
<html:hidden name="OpdTemplateTabFB" property="haveTemplate"/>
			