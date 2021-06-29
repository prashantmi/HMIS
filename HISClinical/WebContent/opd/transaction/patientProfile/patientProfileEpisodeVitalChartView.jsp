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
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.GenericPatientProfileFB"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.ChartMasterVO"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Set"%>


<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.generictemplate.ChartColumnHead"%>
<%@page import="hisglobal.utility.generictemplate.ChartCellData"%>

<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/HISClinical/opd/js/chartReporting.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<bean:define id="intMasRecordCount" name="GenericPatientProfileFB" property="maxRowsCount" type="java.lang.Integer"></bean:define>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>

function viewChart()
{
	 document.getElementsByName("hmode")[0].value="GETVITALCHARTVIEW";
	 document.forms[0].submit();
	
}



function viewOptions()
{
		 document.getElementsByName("hmode")[0].value="PROFILEOPTIONS";
	 document.forms[0].submit();
	
}



function GOChartDetails()
{
	 document.getElementsByName("hmode")[0].value="SETCHARTDETAIL";
	 document.forms[0].submit();
	
}

function setChartHtml()
{
	 document.getElementsByName("chartHtml")[0].value= document.getElementById("pdfPrintingHTMLData").innerHTML;
	 //alert(document.getElementsByName("chartHtml")[0].value);
}


</script>

<body>
<html:form action="/opdPatientProfile.cnt">


<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + menuURL; %>
<his:TitleTag key="patientProfile" width="100%">
</his:TitleTag>



<%	boolean haveDefaultData = false; %>
	<his:statusTransactionInProcess>
		<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
		<html:hidden name="GenericPatientProfileFB" property="entryDate" value="<%=sysDate%>" />
		<bean:define id="flg" name="GenericPatientProfileFB" property="haveDefault" type="java.lang.Boolean"></bean:define>
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
								&nbsp;<html:select name="GenericPatientProfileFB" property="chartId" tabindex="1" onchange="<%=strFunction%>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" property="chartId" labelProperty="chartName"/>
									</logic:present>
								</html:select>
							</div>
							<div style="display: none;">
								&nbsp;<html:select name="GenericPatientProfileFB" property="generationType">
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE%>" property="chartId" labelProperty="generationType"/>
									</logic:present>
								</html:select>
							</div>
						</td>
				   		<td width="25%" class="tdfonthead">
		           			<!-- <div align="right">
		           				<input type="checkbox" name="customize">&nbsp;
		            		</div> -->
						</td>
	   					<td width="25%" class="tdfont" style="vertical-align: middle;" valign="middle">
	   						<%-- <div align="left">
	  								&nbsp;<bean:message key="customize"/>
	   						</div> --%>
	   					</td>
					</tr>
	
						<tr>
					   		<td width="25%" class="tdfonthead">
			           			<div align="right">
			           				<b><bean:message key="fromDate"/></b>&nbsp;
			            		</div>
							</td>
							<bean:define name="GenericPatientProfileFB" property="fromDate" id="frmDate" type="java.lang.String"/>
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
		
		<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		<%	if(!haveDefaultData){	%>
			<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' title="View Chart" style="cursor:pointer" onclick="viewChart();" onkeypress="if(event.keyCode==13) viewChart();" >
		<%} %>
		</his:statusTransactionInProcess>		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="viewOptions();" onkeypress="if(event.keyCode==13) viewOptions();" >
	</his:ButtonToolBarTag>
</his:statusTransactionInProcess>

	<his:statusTransactionInProcess>
		<%-- <his:TitleTag key="viewchart">
		</his:TitleTag> --%>
<%
Map mpDefaults =(Map)session.getAttribute(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
Set stCharts = mpDefaults.keySet();			
Iterator itr = stCharts.iterator();
while(itr.hasNext())
{
	String strChartId = (String)itr.next();

	Map mp = (Map)mpDefaults.get(strChartId);
	LinkedHashMap mpHeader1 = null;
	LinkedHashMap mpData1 = null;
	mpHeader1 = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
	mpData1 = (LinkedHashMap)mp.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);

	request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL, mpHeader1);
	request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL, mpData1);
	request.setAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL_KEYS_AS_AARAY, mpData1.keySet().toArray());
	request.setAttribute("ChartID",strChartId);
	request.setAttribute("ChartName",((GenericPatientProfileFB)request.getAttribute("GenericPatientProfileFB")).getChartHeader());
	request.setAttribute("flgShowOnNonData",true);
	request.setAttribute("flgShowGraph",((GenericPatientProfileFB)request.getAttribute("GenericPatientProfileFB")).getIsGraph());
%>
		
<%
	LinkedHashMap mpHeader = (LinkedHashMap)request.getAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
	LinkedHashMap mpData = (LinkedHashMap)request.getAttribute(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
	String strChartID = (String) request.getAttribute("ChartID");
	String strChartName = (String) request.getAttribute("ChartName");
	boolean flgShowOnNonData = true;
	if(request.getAttribute("flgShowOnNonData")!=null)
		flgShowOnNonData = ((Boolean)request.getAttribute("flgShowOnNonData")).booleanValue();
	String flgShowGraph = OpdConfig.NO;
	if(request.getAttribute("flgShowGraph")!=null)
		flgShowGraph = (String)request.getAttribute("flgShowGraph");
	
	double maxColumns=15;	

	if(mpData==null || mpData.size()==0)
	{
		if(flgShowOnNonData)
		{
%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.GenericPatientProfileFB"%>
<his:SubTitleTag name="<%=strChartName%>">
	</his:SubTitleTag>
	<his:ContentTag>
	<div id="pdfPrintingHTMLData">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td class="tdfonthead" width="100%">
					<div align="left">
						<b>No Information Found for the Chart: <font color="#0000FF">"<%=strChartName%>"</font></b>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</his:ContentTag>
<%		}
	}
	else
	{ 
		PaginationFB fbPage = new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((GenericPatientProfileFB)request.getAttribute("GenericPatientProfileFB")).getCurrentPage());
		fbPage.setObjArrName(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL_KEYS_AS_AARAY);
		if(flgShowGraph.equals(OpdConfig.YES))
			fbPage.setAppendInTitle(strChartName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' onclick=\"showGraph(event,'"+strChartID+"','"+strChartName.replace("&","and")
				+ "')\">Show Graph</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' onclick='printChart(event);' >PRINT</a>");
		else
			fbPage.setAppendInTitle(strChartName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' onclick='printChart(event);' >PRINT</a>");
		fbPage.setTitleRequired(false);
		fbPage.setMaxRecords(intMasRecordCount.intValue());
		
%>
	<%--  --%>
	<%if(mpData.keySet().size()>intMasRecordCount.intValue()){%>
		<br>
	<%-- 	<his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
	<%}else{%>
	<his:SubTitleTag name="<%=strChartName%>">
		<div align="right">
			<% if(flgShowGraph.equals(OpdConfig.YES)){%>
				<a style="cursor:pointer" onclick="showGraph(event,'<%=strChartID%>','<%=strChartName.replace("&","and") %>')">Show Graph</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%}%>
			<a style="cursor:pointer" onclick="printChart(event);" >
				PRINT
			</a>
		</div>
	</his:SubTitleTag>
	<%}%>
	<his:ContentTag>
	<div id="pdfPrintingHTMLData">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr><td width="100%">
<%		
		int colsCount = mpHeader.keySet().size();
		maxColumns = colsCount;//-------------------------------
		int loops = (int)Math.ceil(colsCount/maxColumns);
		int colsLimit = (int)Math.ceil(colsCount/Math.ceil(colsCount/maxColumns));
		for(int i=0;i<loops;i++)
		{
%>
				<table width="100%" cellpadding="0" cellspacing="1" border="1">
					<tr>
							
					 <td width="15%" ><div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									&nbsp;</font>
							</div>
						</td>
	<%
			for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
			{
				ChartColumnHead chartCol = (ChartColumnHead)mpHeader.get(mpHeader.keySet().toArray()[j]);
	%>
						 <td width="<%=85/colsLimit%>%" >
							<%if(j<colsCount){%>
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=chartCol.getName()%></b>
								</font>
							</div>
							<%	}%>
						</td>
<%			}	%>
					</tr>
<%			
			Object[] arrKeys = mpData.keySet().toArray();
			int startIndex = 0, endIndex = arrKeys.length-1;
			if(request.getAttribute(PaginationTag.PAGINATION_START_INDEX)!=null)
				startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
			if(request.getAttribute(PaginationTag.PAGINATION_END_INDEX)!=null)
				endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
		
			for(int k=startIndex; k<=endIndex; k++)
			{
				
				String entryDate = (String) arrKeys[k];
				Map mpParaValues = (Map) mpData.get(entryDate);
%>
					<tr>
					<td width="15%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=entryDate%></b>
								</font>
							</div>
						</td>
<%				for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
				{
					ChartColumnHead chartCol = (ChartColumnHead)mpHeader.get(mpHeader.keySet().toArray()[j]);
%>
				<td width="<%=85/colsLimit%>%" >
							<%if(j<colsCount){%>
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%	
										if(mpParaValues!=null && mpParaValues.get(chartCol.getId())!=null)
										{	ChartCellData cell = (ChartCellData)mpParaValues.get(chartCol.getId());
									%>
										<% if(cell.getFlag().equals("0")){%><font color="#FF0000">	<%} %>
										<%=cell.getValue()%> 
										<% if(cell.getFlag().equals("0")){%></font>	<%} %>
									<%	}else{%> - <%}%>
								</font>
							</div><%	}%>
						</td>
<%				} %>
					</tr>
<%			}	%>
				</table>
<%		}	%>
			</td></tr>
		</table>
	</div>
</his:ContentTag>
<%	} %>

	<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13)setChartHtml(); GOChartDetails();" onclick="setChartHtml();GOChartDetails();" tabindex="1" />		
	</his:ButtonToolBarTag>

<%	} %>

</his:statusTransactionInProcess>
<html:hidden name="GenericPatientProfileFB" property="hmode" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="userLevel" />
<html:hidden name="GenericPatientProfileFB" property="remarks" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenu" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />
<html:hidden name="GenericPatientProfileFB" property="vitalChartCheckFlag" />
<html:hidden name="GenericPatientProfileFB" property="chartHtml" />

</html:form></body>
