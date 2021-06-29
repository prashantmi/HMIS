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
<%@page import="hisglobal.presentation.*,opd.*"%>


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
	
<% 
	List lstTemps = (List)WebUTIL.getSession(request).getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST) ;
	List lst = (List)WebUTIL.getSession(request).getAttribute(OpdConfig.OPD_DESKMENU_TEMPLATES_INACTIVE_IDS_LIST) ;
	if(lstTemps!=null && lstTemps.size()==0 &&	lst.size()==0)
	{
%>
		<his:statusTransactionInProcess>
			<his:ContentTag>
 				<table width="100%" cellspacing="1" cellpadding="0">
 					<tr>
						<td width="100%"  class="tdfont">
							&nbsp;&nbsp;&nbsp;<b><font color="#FF0000"> No Template exists for <bean:write name="OpdTemplateTabFB"  property="deskMenuName"/></font></b>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		</his:ButtonToolBarTag>
<%
	}
	else
	{
%>
		<his:statusTransactionInProcess>
		<bean:define name="OpdTemplateTabFB" property="episodeVisitNo" id="visitNo" type="java.lang.String" />
	<%
		int visit=Integer.parseInt(visitNo);
		if(lst.size()>0 || visit>1)
		{
	%>
			<his:ContentTag>
 				<table width="100%" cellspacing="1" cellpadding="0">
 					<tr>
						<td width="100%"  class="tdfonthead">
						<% if(lst.size()>0)	{ %>
							<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openPopup('/HISClinical/opd/opdTemplateTab.cnt?hmode=POPUP',event,700,600);" >
						<%	}	%>
						&nbsp;&nbsp;&nbsp;
						<% if(visit>1)	{%>
							<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-view.png"/>' alt="View" title="View" onclick="submitMyForm('PREVVISIT','PREVVISIT');" >
						<%	}	%>
						</td>
					</tr>
				</table>
			</his:ContentTag>
	<% 
		}
	%>
			<html:hidden name="OpdTemplateTabFB"  property="consentHtmlToPrint"/>
			<logic:iterate name="<%=OpdConfig.OPD_DESKMENU_TEMPLATES_ACTIVE_LIST%>" id="tempBean" >
				<his:ContentTag>
 					<table width="100%" cellspacing="1" cellpadding="0">
	 					<tr>
							<td width="100%">
								<his:OpdTemplateTag name="tempBean"></his:OpdTemplateTag>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</logic:iterate>
		</his:statusTransactionInProcess>

		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"   onclick ="submitMyForm(validatePARAMETERCompulsoryField(),'SAVE');" onkeypress="if(event.keyCode==13) submitMyForm(validatePARAMETERCompulsoryField(),'SAVE');" >
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer"  onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
		</his:ButtonToolBarTag>
<%
	}
%>

<html:hidden name="OpdTemplateTabFB" property="hmode"/>
<html:hidden name="OpdTemplateTabFB" property="userSeatId"/>
<html:hidden name="OpdTemplateTabFB" property="departmentUnitCode"/>
<html:hidden name="OpdTemplateTabFB" property="patCrNo"/>
<html:hidden name="OpdTemplateTabFB" property="episodeCode"/>
<html:hidden name="OpdTemplateTabFB" property="episodeVisitNo"/>
<html:hidden name="OpdTemplateTabFB" property="deskMenuName"/>
<html:hidden name="OpdTemplateTabFB" property="selUndefaultTemp" value=""/>
<html:hidden name="OpdTemplateTabFB" property="haveTemplate"/>
