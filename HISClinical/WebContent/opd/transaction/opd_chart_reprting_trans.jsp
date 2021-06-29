<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.ChartMasterVO"%>
<%@page import="java.util.LinkedHashMap"%>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/opd/js/chartReporting.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%	boolean haveDefaultData = false; %>
	<his:statusTransactionInProcess>
		<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
		<html:hidden name="ChartReportingFB" property="entryDate" value="<%=sysDate%>" />
		<bean:define id="flg" name="ChartReportingFB" property="haveDefault" type="java.lang.Boolean"></bean:define>
		<%				
			Map mpDefaults = null;
			List lstCharts = null;
			if(flg)
			{
				mpDefaults =(Map)session.getAttribute(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
				lstCharts = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE);			
				Iterator itr = lstCharts.iterator();
				while(itr.hasNext())
				{
					ChartMasterVO vo =(ChartMasterVO)itr.next();
					if(vo.getIsDefault().equals(OpdConfig.IS_DEFAULT_NO))	continue;
					Map mp = (Map)mpDefaults.get(vo.getChartId());
					LinkedHashMap mpHeader = null;
					LinkedHashMap mpData = null;
					mpHeader = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
					mpData = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
					if(mpData!=null && mpData.size()>0)
					{
						haveDefaultData = true;
						break;
					}
				}
			}
		%>

		<his:TitleTag key="charts">
			<%	if(haveDefaultData){	%>
			<img id="imgViewAllChart" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onClickImage(this)"/>
			<%	}	%>
		</his:TitleTag>
		
		<div id="divViewAllChart" <% if(haveDefaultData) {%> style="display: none;" <%}%> >
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<b><bean:message key="selchart"/></b>&nbsp;
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<%	String strFunction = "setCutomizeOption(this,'"+ OpdConfig.CHART_GENERATION_TYPE_ROW_WISE +"')";  %>
								&nbsp;<html:select name="ChartReportingFB" property="chartId" tabindex="1" onchange="<%=strFunction%>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" property="chartId" labelProperty="chartName"/>
									</logic:present>
								</html:select>
							</div>
							<div style="display: none;">
								&nbsp;<html:select name="ChartReportingFB" property="generationType">
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" property="chartId" labelProperty="generationType"/>
									</logic:present>
								</html:select>
							</div>
						</td>
				   		<td width="25%" class="tdfonthead">
		           			<div align="right">
		           				<input type="checkbox" name="customize">&nbsp;
		            		</div>
						</td>
	   					<td width="25%" class="tdfont" style="vertical-align: middle;" valign="middle">
	   						<div align="left">
	  								&nbsp;<bean:message key="customize"/>
	   						</div>
	   					</td>
					</tr>
	<!-- 				<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<b><bean:message key="criteria"/></b>&nbsp;
							</div>
						</td>
						<td width="50%" class="tdfont" style="vertical-align: middle;" valign="middle">
							<div align="left" style="vertical-align: middle;">
								<html:radio name="ChartReportingFB" property="filterCriteria" value="<%=OpdConfig.CHOICE_EPISODE_WISE %>" onclick="setFilterCiteria()"></html:radio>
								<bean:message key="episodeWise"/>&nbsp;&nbsp;
								<html:radio name="ChartReportingFB" property="filterCriteria" value="<%=OpdConfig.CHOICE_DATE_WISE %>" onclick="setFilterCiteria()"></html:radio>
								<bean:message key="date"/> <bean:message key="wise"/>
							</div>
						</td>
					</tr> 
				</table>
				<div id="divBetweenDates" style="display: none;">
					<table width="100%" cellpadding="0" cellspacing="1">-->
						<tr>
					   		<td width="25%" class="tdfonthead">
			           			<div align="right">
			           				<b><bean:message key="fromDate"/></b>&nbsp;
			            		</div>
							</td>
							<bean:define name="ChartReportingFB" property="fromDate" id="frmDate" type="java.lang.String"/>
		   					<td width="25%" class="tdfont" style="vertical-align: middle;" valign="middle">
		   						<div align="left">
	   								&nbsp;<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=frmDate%>" />
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfonthead">
		   						<div align="right">
	   								<b><bean:message key="toDate"/></b>&nbsp;
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfont" style="vertical-align: middle;" valign="middle">
		   						<div align="left">
	   								&nbsp;<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>" />
		   						</div>
		   					</td>
		   				</tr>
						<%	if(haveDefaultData){	%>
						<tr>
					   		<td width="25%" class="tdfonthead" colspan="4">
			           			<div align="center">
			           				<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' title="View Chart" style="cursor:pointer" onclick="viewChart();" onkeypress="if(event.keyCode==13) viewChart();" >
			            		</div>
							</td>
						</tr>
						<%	} %>
					</table>
				<!-- </div> -->
			</his:ContentTag>

		</div>
		<%
			if(haveDefaultData)
			{
				Iterator itr = lstCharts.iterator();
				while(itr.hasNext())
				{
					ChartMasterVO vo =(ChartMasterVO)itr.next();
					if(vo.getIsDefault().equals(OpdConfig.IS_DEFAULT_NO))	continue;
						
					Map mp = (Map)mpDefaults.get(vo.getChartId());
					LinkedHashMap mpHeader = null;
					LinkedHashMap mpData = null;
					mpHeader = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
					mpData = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
	
					request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL, mpHeader);
					request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL, mpData);
					request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL_KEYS_AS_AARAY, mpData.keySet().toArray());
					request.setAttribute("ChartID",vo.getChartId());
					request.setAttribute("ChartName",vo.getChartName());
					request.setAttribute("flgShowOnNonData",false);
					request.setAttribute("flgShowGraph",vo.getIsGraph());
		%>
			<jsp:include page="/opd/transaction/opd_chart_view_tile.jsp"></jsp:include>
		<%		}
			} %>
	</his:statusTransactionInProcess>
		
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		<%	if(!haveDefaultData){	%>
			<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' title="View Chart" style="cursor:pointer" onclick="viewChart();" onkeypress="if(event.keyCode==13) viewChart();" >
		<%	} %>
		</his:statusTransactionInProcess>		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
	</his:ButtonToolBarTag>

<html:hidden name="ChartReportingFB" property="hmode"/>
<html:hidden name="ChartReportingFB" property="deskType"/>
<html:hidden name="ChartReportingFB" property="deptUnitCode"/>
<html:hidden name="ChartReportingFB" property="episodeCode"/>
<html:hidden name="ChartReportingFB" property="episodeVisitNo"/>
<html:hidden name="ChartReportingFB" property="admissionNo"/>
<html:hidden name="ChartReportingFB" property="epiAdmStartDate"/>
<html:hidden name="ChartReportingFB" property="haveDefault"/>
<html:hidden name="ChartReportingFB" property="targetPage" value="NEW"/>
<html:hidden name="ChartReportingFB" property="sortType"/>
<html:hidden name="ChartReportingFB" property="sortChartId"/>
<html:hidden name="ChartReportingFB" property="oldSortType"/>
<html:hidden name="ChartReportingFB" property="currentPage"/>
<html:hidden name="ChartReportingFB" property="maxRowsCount"/>
<html:hidden name="ChartReportingFB" property="graphChartId"/>
