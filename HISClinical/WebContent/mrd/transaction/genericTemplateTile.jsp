<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>

<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function printAllTemplates(e)
{
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY";
	openPopup(url,e,400,600);
}
</script>

<his:SubTitleTag name="">
	<div align="left"><bean:write name="GenericTemplateTileFB"  property="deskMenuName"/></div>
</his:SubTitleTag>

<his:statusTransactionInProcess>
<% 
	List lstActiveTemps = (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST) ;
	List lstInactiveTemps = (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST) ;
	String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
	if(lstActiveTemps!=null && lstActiveTemps.size()==0 && lstInactiveTemps.size()==0)
	{
%>
	
	<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
				<td width="100%"  class="tdfont">
					&nbsp;&nbsp;&nbsp;<b><font color="#FF0000"> No Template exists for <bean:write name="GenericTemplateTileFB"  property="deskMenuName"/></font></b>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
	</his:ButtonToolBarTag>
<%
	}
	else
	{
%>
	<bean:define name="GenericTemplateTileFB" property="episodeVisitNo" id="visitNo" type="java.lang.String" />
	<%
		int visit=Integer.parseInt(visitNo);
		if(visit>1 || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
	%>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%"  class="tdfonthead">
					<div align="right">
						<% if(visit>1)	{	%>
							<!-- <a style="cursor:pointer" onclick="submitFormOnValidate(true,'LASTVISIT');" >
								<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									LAST VISIT...
								</font>
							</a>	 -->
						<%	}	%>						
						<%	if(visit>2 || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))	{	%>
							&nbsp;&nbsp;
							<a style="cursor:pointer" onclick="submitFormOnValidate(true,'TEMPREPORT');" >
								<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									PREVIOUS ALL...
								</font>
							</a>
											
						<%	}	%>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<% 
		}
	%>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="100%"  class="tdfonthead">
				<div align="right">
					<a style="cursor:pointer" onclick="printAllTemplates(event);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							PRINT ALL...
							</font>
					</a>
				</div>
			</td>
		</tr>
	</table>
	<his:ContentTag>
		<div id="pdfPrintingHTMLData">
		<table width="100%" cellspacing="1" cellpadding="0">
		<%	Iterator lstActiveTempsItr = lstActiveTemps.iterator();
			while(lstActiveTempsItr.hasNext())
			{
				Entry e = (Entry)lstActiveTempsItr.next();
		%>
			<tr>
				<td width="100%">
					<his:GenericTemplateTag templateId="<%=e.getValue()%>" ></his:GenericTemplateTag>
				</td>
			</tr>
		<%	}	%>
		</table>
		</div>
		<table width="100%" cellspacing="1" cellpadding="0">
		<%	if(lstInactiveTemps.size()>0)	{	%>
			<tr>
				<td width="100%">
					<a style="cursor:pointer" onclick="openPopup('/HISClinical/opd/opdTemplateTab.cnt?hmode=MORETEMPLATE',event,300,500);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							MORE ...
						</font>
					</a>							
				</td>
			</tr>
		<%	}	%>
		</table>
	</his:ContentTag>
	
		
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"   onclick ="submitFormOnValidate(validatePARAMETERCompulsoryField(),'SAVE');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validatePARAMETERCompulsoryField(),'SAVE');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer"  onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
	</his:ButtonToolBarTag>
<%
	}
%>
</his:statusTransactionInProcess>

<html:hidden name="GenericTemplateTileFB" property="hmode"/>
<html:hidden name="GenericTemplateTileFB" property="userSeatId"/>
<html:hidden name="GenericTemplateTileFB" property="wardCode"/>
<html:hidden name="GenericTemplateTileFB" property="deptUnitCode"/>
<html:hidden name="GenericTemplateTileFB" property="episodeCode"/>
<html:hidden name="GenericTemplateTileFB" property="episodeVisitNo"/>
<html:hidden name="GenericTemplateTileFB" property="admissionNo"/>
<html:hidden name="GenericTemplateTileFB" property="deskType"/>
<html:hidden name="GenericTemplateTileFB" property="deskId"/>
<html:hidden name="GenericTemplateTileFB" property="deskMenuName"/>
<html:hidden name="GenericTemplateTileFB" property="selUndefaultTemp" value=""/>
<html:hidden name="GenericTemplateTileFB" property="reportMode"/>