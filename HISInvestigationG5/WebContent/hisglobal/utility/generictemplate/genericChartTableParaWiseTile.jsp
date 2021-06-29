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
<%@page import="java.util.Iterator"%>

<!-- <his:css src="/hisglobal/utility/generictemplate/css/generic_chart.css"/> -->
<%
	List lstParas = null;
	LinkedHashMap mpVisits = null;
	Map mpRecordParaValues = null;
	double maxColumns=8;	
	lstParas = (List)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST);
	mpVisits = (LinkedHashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP);
	mpRecordParaValues = (HashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);	
%>

<%@page import="java.util.LinkedHashMap"%>
<table width="100%" cellpadding="0" cellspacing="1">
	<tr><td width="100%">
<%		
		int colsCount = lstParas.size();
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
						<b><%=((Entry)lstParas.get(j)).getLabel()%></b>
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
				Map mpParaValues = (Map)mpRecordParaValues.get(_visit);
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
						<%if(mpParaValues!=null && mpParaValues.get(((Entry)lstParas.get(j)).getValue())!=null)
						{%> <%=(String)mpParaValues.get(((Entry)lstParas.get(j)).getValue())%> <%} else {%>-<%}%>
					</font>
				</div><%}%>
			</td>
<%				} %>				
		</tr>
<%			}%>
	</table>
<%
		}
%>
	</td></tr>
</table>
