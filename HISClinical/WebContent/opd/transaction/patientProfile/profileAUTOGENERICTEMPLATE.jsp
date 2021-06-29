<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.Map"%>
<%@page import="opd.OpdConfig"%>
<%@page import="java.util.HashMap"%>
<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="java.util.Set" %>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%!
	private boolean haveDataInVisit(List _lstTemps, Map _mpChartClinicalData, String _visit)
	{
		boolean flag=false;
		Map mpVisitData = (Map)_mpChartClinicalData.get(_visit);
		System.out.println("mpVisitData:"+mpVisitData);
		Iterator _lstTempsItr = _lstTemps.iterator();
		while(_lstTempsItr.hasNext())
		{
			Entry temp = (Entry)_lstTempsItr.next();
			System.out.println("function temp:"+temp);
			if(mpVisitData!=null && mpVisitData.size()>0){
				System.out.println("mpVisitData.get(temp.getValue()):"+mpVisitData.get(temp.getValue().split("#")[0]));
				if(mpVisitData.get(temp.getValue().split("#")[0])!=null && ((Map)mpVisitData.get(temp.getValue().split("#")[0])).size()>0)
				{
					flag=true;
					break;
				}
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

<%
	/*String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session
	.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpDiag = (Map)proforma.mapProfileOptions.get(deskMenuId);
		
		*/
	String deskmenu = request.getParameter("DeskMenuID"); 
	// Getting Desk Menu Wise Report Modes
	Map mpDeskMenuReportModes = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_PROFILE_REPORT_MODE_MAP);
	String strReportMode = OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE;
	System.out.println("Report Mode:"+strReportMode);
	if(strReportMode.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
	{
			// Getting Desk Menu Wise
			//List lstVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
			Map mpDeskMenuChartVisitDates = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_VISIT_DATES);
			List lstVisitDates = (List) mpDeskMenuChartVisitDates.get(deskmenu);
			System.out.println("lstVisitDates:"+lstVisitDates);
			// Getting Desk Menu Wise
			//List lstTemps = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS);
			Map mpDeskMenuTemps = (Map) session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS);
			List lstTemps = (List) mpDeskMenuTemps.get(deskmenu);
			System.out.println("lstTemps:"+lstTemps);
			// Getting Desk Menu Wise
			//Map mpChartClinicalData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
			Map mpDeskMenuChartClinicalData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
			Map mpChartClinicalData = (Map) mpDeskMenuChartClinicalData.get(deskmenu);
			System.out.println("mpChartClinicalData:"+mpChartClinicalData);
			Iterator lstVisitDatesItr = lstVisitDates.iterator();
			while(lstVisitDatesItr.hasNext())
			{
				Entry visitDate = (Entry)lstVisitDatesItr.next();
				System.out.println("visitDate.getValue()"+visitDate.getLabel());
				if(haveDataInVisit(lstTemps, mpChartClinicalData, visitDate.getLabel()))
				{
					System.out.println("visitDate.getLabel()"+visitDate.getLabel());	
				
		%>
<!-- <his:css src="/hisglobal/utility/generictemplate/css/generic_chart.css"/> -->
		<table width="100%">
			<tr>
				<td width="100%" class="applicationBackgroundColor">
					<div align="left">
						<b><u><i><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=visitDate.getLabel()%>
						</font></i></u></b>
					</div>
				</td>
			</tr>
		</table>				

		<%
					Map mpVisitData = (Map)mpChartClinicalData.get(visitDate.getLabel());
					System.out.println("mpVisitData"+mpVisitData);
					Iterator lstTempsItr = lstTemps.iterator();
					while(lstTempsItr.hasNext())
					{
						Entry temp = (Entry) lstTempsItr.next();
						System.out.println("temp"+temp);
						String tempId= temp.getValue().split("#")[0];
						if(mpVisitData!=null && haveDataInTemplate(mpVisitData, temp.getValue().split("#")[0]))
						{
							session.setAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP,mpVisitData);
		%>
			<his:GenericTemplateTag templateId="<%=tempId%>" mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT %>"></his:GenericTemplateTag>
		<%
						}
					}
				}
			}
	}
	else if(strReportMode.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
	{
		Map mpTemps = null;
		LinkedHashMap mpVisits = null;
		Map mpTempParas = null;
		Map mpTempParaValues = null;
		double maxColumns=8;
		try{
		// Getting Desk Menu Wise
		//mpTemps = (HashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP);
		Map mpDeskMenuMapTemps = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP);
		mpTemps = (HashMap) mpDeskMenuMapTemps.get(deskmenu);
	
		Map mpVisitsWithDeskId = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
		mpVisits = (LinkedHashMap) mpVisitsWithDeskId.get(deskmenu);
		if(mpVisits!=null && mpVisits.size()>0){
			
		}else{
			mpVisits = (LinkedHashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
		}
		
	
		// Getting Desk Menu Wise
		//mpTempParas = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
		Map mpDeskMenuTempParas = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
		mpTempParas = (HashMap) mpDeskMenuTempParas.get(deskmenu);
		
		// Getting Desk Menu Wise
		//mpTempParaValues = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);	
		Map mpDeskMenuTempParaValues = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
		mpTempParaValues = (HashMap) mpDeskMenuTempParaValues.get(deskmenu);	
		}catch(Exception e){
			e.printStackTrace();
		}
		Iterator mpTempsKeyItr = mpTemps.keySet().iterator();
		while(mpTempsKeyItr.hasNext())
		{
			String tempId = (String)mpTempsKeyItr.next();
%>
	<%@page import="java.util.Iterator"%>
	<%@page import="java.util.LinkedHashMap"%>
	<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="100%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>&nbsp;&nbsp;<u><%=(String)mpTemps.get(tempId)%></u></b>
					</font>
				</div>
			</td>
		</tr>
		<tr><td width="100%">
<%
		List lstColParas = (List)mpTempParas.get(tempId);
		int colsCount = lstColParas.size();
		int loops = (int)Math.ceil(colsCount/maxColumns);
		int colsLimit = (int)Math.ceil(colsCount/Math.ceil(colsCount/maxColumns));
		for(int i=0;i<loops;i++)        
		{
%>
	<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="15%"></td>
<%
			for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
			{
%>
			<td width="<%=85/colsLimit%>%">
				<%if(j<colsCount){%>
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><%=((Entry)lstColParas.get(j)).getLabel()%></b>
					</font>
				</div><%}%>
			</td>
<%			}	%>
		</tr>
<%			
			Iterator mpVisitsKeyItr = mpVisits.keySet().iterator();
			while(mpVisitsKeyItr.hasNext())
			{
				String _visit = (String)mpVisitsKeyItr.next();
				Map mpParaValues = (Map)((Map)mpTempParaValues.get(_visit)).get(tempId);
%>
		<tr>
			<td width="15%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><%=(String)mpVisits.get(_visit)%></b>
					</font>
				</div>
			</td>
<%				for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
				{	%>
			<td width="<%=85/colsLimit%>%">
				<%if(j<colsCount){%>
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%if(mpParaValues!=null && mpParaValues.get(((Entry)lstColParas.get(j)).getValue())!=null)
						{%> <%=(String)mpParaValues.get(((Entry)lstColParas.get(j)).getValue())%> <%}%>
					</font>
				</div><%}%>
			</td>
<%				} %>				
		</tr>
<%			}%>
		</table>
		<%} %>
		</td></tr>
	</table>
<%
		}
	}	
%>
