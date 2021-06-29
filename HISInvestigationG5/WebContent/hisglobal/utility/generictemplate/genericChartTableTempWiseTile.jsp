<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>

<!-- <his:css src="/hisglobal/utility/generictemplate/css/generic_chart.css"/> -->
<%
	Map mpTemps = null;
	LinkedHashMap mpVisits = null;
	Map mpTempParas = null;
	Map mpTempParaValues = null;
	double maxColumns=8;
	mpTemps = (HashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP);
	mpVisits = (LinkedHashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
	mpTempParas = (HashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_PARAMETER_LIST_MAP);
	mpTempParaValues = (HashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);	

	Iterator mpTempsKeyItr = mpTemps.keySet().iterator();
	while(mpTempsKeyItr.hasNext())
	{
		String tempId = (String)mpTempsKeyItr.next();
%>
	<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>
<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="100%" class="tdfonthead">
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
			<td width="15%" class="tdfont"></td>
<%
			for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
			{
%>
			<td width="<%=85/colsLimit%>%" class="tdfonthead">
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
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><%=(String)mpVisits.get(_visit)%></b>
					</font>
				</div>
			</td>
<%				for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
				{	%>
			<td width="<%=85/colsLimit%>%" class="tdfont">
				<%if(j<colsCount){%>
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%if(mpParaValues!=null && mpParaValues.get(((Entry)lstColParas.get(j)).getValue())!=null)
						{%> <%=(String)mpParaValues.get(((Entry)lstColParas.get(j)).getValue())%> <%} else {%>-<%}%>
					</font>
				</div><%}%>
			</td>
<%				} %>				
		</tr>
<%			}%>
		</table>
		</td></tr>
	</table>
<%
		}
	}
%>