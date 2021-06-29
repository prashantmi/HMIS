<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
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
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>

<%!
	private boolean haveDataInVisit(List _lstTemps, Map _mpChartClinicalData, String _visit)
	{
		boolean flag=false;
		Map mpVisitData = (Map)_mpChartClinicalData.get(_visit);
		Iterator _lstTempsItr = _lstTemps.iterator();
		while(_lstTempsItr.hasNext())
		{
			Entry temp = (Entry)_lstTempsItr.next();
			if((Map)mpVisitData.get(temp.getValue())!=null && ((Map)mpVisitData.get(temp.getValue())).size()>0)
			{
				flag=true;
				break;
			}
		}
		return flag;
	}

	private boolean haveDataInTemplate(Map _mpVisitData, String _tempId)
	{
		boolean flag=false;
		if(_mpVisitData.get(_tempId)!=null && ((Map)_mpVisitData.get(_tempId)).size()>0)
			flag=true;
		return flag;
	}
%>


<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="100%" align="left" class="tdfonthead">
			<div align="left">
						<b>
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="complaintsDetail"/>
							</font>
						</b>
			</div>			
					</td>
		</tr>
</table>	
<!-- <his:css src="/hisglobal/utility/generictemplate/css/generic_chart.css"/> -->
<%
			List lstVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
			List lstTemps = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS);
			Map mpChartClinicalData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
			Iterator lstVisitDatesItr = lstVisitDates.iterator();
			while(lstVisitDatesItr.hasNext())
			{
				Entry visitDate = (Entry)lstVisitDatesItr.next();
				if(haveDataInVisit(lstTemps, mpChartClinicalData, visitDate.getValue()))
				{
		%>
		<his:SubTitleTag name="">
			<div align="left"><%=visitDate.getLabel()%></div>				
		</his:SubTitleTag>
		<%
					Map mpVisitData = (Map)mpChartClinicalData.get(visitDate.getValue());
					Iterator lstTempsItr = lstTemps.iterator();
					while(lstTempsItr.hasNext())
					{
						Entry temp = (Entry) lstTempsItr.next();
						if(mpVisitData!=null && haveDataInTemplate(mpVisitData, temp.getValue()))
						{
							session.setAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP,mpVisitData);
		%>
		<his:ContentTag>
			<his:GenericTemplateTag templateId="<%=temp.getValue()%>" mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT %>"></his:GenericTemplateTag>
		</his:ContentTag>			
		<%
						}
					}
				}
			}
		%>		

<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/btn-done.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) window.close();" onclick="window.close();" tabindex="1" />
</his:ButtonToolBarTag>