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


<%@page import="java.util.*,registration.*"%>
<%@page import="hisglobal.presentation.*,opd.*"%>
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

<his:javascript src="/opd/js/templateFunctions.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

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

function validateReportView()
{
	if(document.getElementsByName("selectedVisitNo")[0])
	{
		if(document.getElementsByName("selectedVisitNo")[0].value=="-1")
		{
			alert("Select Visit Date to View");
			document.getElementsByName("selectedVisitNo")[0].focus();
			return false;
		}
	}
	else
	{
		/*if(document.getElementsByName("fromVisitDate")[0].value=="")
		{
			alert("Enter From Date ... ");
			document.getElementsByName("fromVisitDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("toVisitDate")[0].value=="")
		{
			alert("Enter To Date ... ");
			document.getElementsByName("toVisitDate")[0].focus();
			return false;
		}*/
		
		if(!ValidateDates())
		{
			return false;			
		}

		var paras="";
		var chks=document.getElementsByName('paras');
		for( var i=0; i<chks.length;i++)
		{
			if(chks[i].checked)
				paras+=chks[i].id+"&";
		}
		paras=paras.substring(0,paras.length-1);
		document.getElementsByName('consentHtmlToPrint')[0].value=paras;
		
		if(paras=="")
		{
			alert("Select at Least One Parameter ...");			
			return false;			
		}
	}
	return true;
}

function ValidateDates()
{
	var visitFromDate = document.getElementsByName("fromVisitDate")[0];
	var visitToDate = document.getElementsByName("toVisitDate")[0];
	var entryDate = document.getElementsByName("entryDate")[0];

	if( !isEmpty(document.forms[0].fromVisitDate,"From Visit Date") )
		return false;
	if( !isEmpty(document.forms[0].toVisitDate,"From To Date") )
		return false;

	if(!compareDateCall(fromVisitDate,entryDate,2,"From Visit Date","Current Date") )
		return false;
	if(!compareDateCall(toVisitDate,entryDate,2,"To Visit Date","Current Date") )
		return false;
	if(!compareDateCall(fromVisitDate,toVisitDate,2,"From Visit Date","To Visit Date"))
		return false;
	return true;
}

function compareDateCall(d1,d2,mode,l1,l2) 
{
	var valid=true;
	if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode))
			valid = true;
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
	return valid;
}


</script>


<his:SubTitleTag name=''>
	<div align="left"><bean:write name="OpdTemplateTabFB"  property="deskMenuName"/></div>
</his:SubTitleTag>
	
<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>

<his:statusTransactionInProcess>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="100%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="repMode" /></b>
					</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="tempWise" />
					</font>
					<html:radio name="OpdTemplateTabFB" property="choice" tabindex="1" value="<%=OpdConfig.CHOICE_PREVVISIT_TEMP_WISE%>" onclick="submitMyForm(true,'TEMPWISE');" />
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="paraWise" />
					</font>
					<html:radio name="OpdTemplateTabFB" property="choice" tabindex="1" value="<%=OpdConfig.CHOICE_PREVVISIT_PARA_WISE%>" onclick="submitMyForm(true,'PARAWISE');" />
				</td>
			</tr>
		</table>
	</his:ContentTag>

	<logic:equal name="OpdTemplateTabFB" property="choice" value="<%=OpdConfig.CHOICE_PREVVISIT_TEMP_WISE%>" >
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="50%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="previous"/>&nbsp;<bean:message key="visitDate"/>
					</font>
				</td>
				<td class="tdfont" width="50%" >
					<html:select name="OpdTemplateTabFB" property="selectedVisitNo" >
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=OpdConfig.OPD_LIST_PREV_VISIT_DATES%>" >
						<html:options collection="<%=OpdConfig.OPD_LIST_PREV_VISIT_DATES%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	</logic:equal>

	<logic:equal name="OpdTemplateTabFB" property="choice" value="<%=OpdConfig.CHOICE_PREVVISIT_PARA_WISE%>" >
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="25%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
				</td>
				<td class="tdfont" width="25%" >
					<bean:define  name="OpdTemplateTabFB" property="fromVisitDate" id="visitFrom" type="java.lang.String"/>
					<%	if(visitFrom==null||visitFrom.equalsIgnoreCase(""))	{	visitFrom =sysDate;	}	%>
					<his:date name='<%="fromVisitDate"%>' dateFormate="%d-%b-%Y" value="<%=visitFrom %>" />
				</td>
				<td class="tdfonthead" width="25%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>
					</font>
				</td>
				<td class="tdfont" width="25%">
					<bean:define  name="OpdTemplateTabFB" property="toVisitDate" id="visitTo" type="java.lang.String"/>
					<%	if(visitTo==null||visitTo.equalsIgnoreCase(""))	{	visitTo =sysDate;	}	%>
					<his:date name='<%="toVisitDate"%>' dateFormate="%d-%b-%Y" value="<%=visitTo %>" />
				</td>
			</tr>
		</table>
		<his:SubTitleTag name=''>
		<div align="left">Select Parameters To View</div>
		</his:SubTitleTag>
		<logic:iterate name="<%=OpdConfig.OPD_DESK_TEMPLATES_BY_CRNO_VISIT_LIST%>" id="entTemplate" >
			<%
				String tempId=((Entry)entTemplate).getValue();
				String tempName=((Entry)entTemplate).getLabel();
				Map map=(Map)WebUTIL.getSession(request).getAttribute(OpdConfig.OPD_DESK_TEMPWISE_PARAS_BY_CRNO_VISIT_MAP) ;
				List lstParas = (List)map.get(tempId);
			%>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>				
					<td class="tdfonthead" width="100%" colspan="4">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=tempName%>
							</font>
						</div>
					</td>
				</tr>
				<%
					for(int i=0;i<lstParas.size();i++)
					{
						String paraId=((Entry)lstParas.get(i)).getValue();
						String paraName=((Entry)lstParas.get(i)).getLabel();
				%>
				<tr>
					<td class="tdfont" width="50%" colspan="2">
						<input name="paras" type="checkbox" id='<%=paraId+"#"+tempId%>'/>
					</td>
					<td class="tdfont" width="50%" colspan="2">
						<%=paraName %>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</logic:iterate>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" width="100%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="repDetMode" /></b>
					</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="paraWise" />
					</font>
					<html:radio name="OpdTemplateTabFB" property="haveTemplate" tabindex="1" value="<%=OpdConfig.CHOICE_PREVVISIT_SHOW_PARA_WISE%>" />
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="visitWise" />
					</font>
					<html:radio name="OpdTemplateTabFB" property="haveTemplate" tabindex="1" value="<%=OpdConfig.CHOICE_PREVVISIT_SHOW_VISIT_WISE%>" />
				</td>
			</tr>
		</table>
	</his:ContentTag>
	</logic:equal>
</his:statusTransactionInProcess>

	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' style="cursor:pointer"   onclick ="submitMyForm(validateReportView(),'VIEWREPORT');" onkeypress="if(event.keyCode==13) submitMyForm(validateReportView(),'VIEWREPORT');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer"  onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
	</his:ButtonToolBarTag>

<html:hidden name="OpdTemplateTabFB" property="entryDate" value="<%=sysDate%>" />
<html:hidden name="OpdTemplateTabFB" property="hmode"/>
<html:hidden name="OpdTemplateTabFB" property="userSeatId"/>
<html:hidden name="OpdTemplateTabFB" property="departmentUnitCode"/>
<html:hidden name="OpdTemplateTabFB" property="patCrNo"/>
<html:hidden name="OpdTemplateTabFB" property="episodeCode"/>
<html:hidden name="OpdTemplateTabFB" property="episodeVisitNo"/>
<html:hidden name="OpdTemplateTabFB" property="deskMenuName"/>
<html:hidden name="OpdTemplateTabFB" property="consentHtmlToPrint"/>
<html:hidden name="OpdTemplateTabFB" property="selUndefaultTemp"/>
<html:hidden name="OpdTemplateTabFB" property="haveTemplate"/>
				