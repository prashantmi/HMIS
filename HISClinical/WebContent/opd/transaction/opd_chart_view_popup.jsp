<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.LinkedHashMap"%>
<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.fb.ChartReportingFB"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<his:javascript src="/opd/js/chartReporting.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TransactionContainer>
	<his:statusTransactionInProcess>
		<his:TitleTag key="viewchart">
		</his:TitleTag>
<%
Map mpDefaults =(Map)session.getAttribute(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
Set stCharts = mpDefaults.keySet();			
Iterator itr = stCharts.iterator();
while(itr.hasNext())
{
	String strChartId = (String)itr.next();

	Map mp = (Map)mpDefaults.get(strChartId);
	LinkedHashMap mpHeader = null;
	LinkedHashMap mpData = null;
	mpHeader = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
	mpData = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);

	request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL, mpHeader);
	request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL, mpData);
	request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL_KEYS_AS_AARAY, mpData.keySet().toArray());
	request.setAttribute("ChartID",strChartId);
	request.setAttribute("ChartName",((ChartReportingFB)request.getAttribute("ChartReportingFB")).getChartHeader());
	request.setAttribute("flgShowOnNonData",true);
	request.setAttribute("flgShowGraph",((ChartReportingFB)request.getAttribute("ChartReportingFB")).getIsGraph());
%>
		<jsp:include page="/opd/transaction/opd_chart_view_tile.jsp"></jsp:include>
<%	} %>
	</his:statusTransactionInProcess>

	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitFormOnValidate(true,'NEW')" onkeypress="if(event.keyCode==13) submitFormOnValidate(true,'NEW');" >
	</his:ButtonToolBarTag>
</his:TransactionContainer>

<html:hidden name="ChartReportingFB" property="hmode"/>
<html:hidden name="ChartReportingFB" property="deskType"/>
<html:hidden name="ChartReportingFB" property="deptUnitCode"/>
<html:hidden name="ChartReportingFB" property="episodeCode"/>
<html:hidden name="ChartReportingFB" property="episodeVisitNo"/>
<html:hidden name="ChartReportingFB" property="admissionNo"/>
<html:hidden name="ChartReportingFB" property="epiAdmStartDate"/>
<html:hidden name="ChartReportingFB" property="targetPage" value="VIEW"/>
<html:hidden name="ChartReportingFB" property="sortType"/>
<html:hidden name="ChartReportingFB" property="sortChartId"/>
<html:hidden name="ChartReportingFB" property="oldSortType"/>
<html:hidden name="ChartReportingFB" property="graphChartId"/>
