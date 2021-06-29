<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.generictemplate.ChartColumnHead"%>
<%@page import="hisglobal.utility.generictemplate.ChartCellData"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<bean:define id="intMasRecordCount" name="ChartReportingFB" property="maxRowsCount" type="java.lang.Integer"></bean:define>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 --><!-- <script src="http://code.jquery.com/jquery-1.8.3.js"></script> -->
 
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>

</head>

 <script>
 /* Added By warish :04-aug-2017: this funtion worked by row to coloumn */
 $(function(){
	    $('#btnInvertTbl').click(function() { 
	    	$("#pivotTable").each(function() {
		    	var tbl = $(this);
		      var newrows = [];
		        tbl.find("tr").each(function(){
		        	var i = 0;
		            $(this).find("td").each(function(){
		             i++;
		              if(newrows[i] === undefined) { newrows[i] = $("<tr></tr>"); }
		                newrows[i].append($(this));
		               
		            });
		        });
		        tbl.find("tr").remove();
		        $.each(newrows, function(){
		        tbl.append(this);
		        });
		    });

		    return false;
	    });
	}); 
   </script> 
   
   
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
<%@page import="opd.transaction.controller.fb.ChartReportingFB"%>
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
		fbPage.setCurrentPage(((ChartReportingFB)request.getAttribute("ChartReportingFB")).getCurrentPage());
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
		<his:PaginationTag name="fbPagination"></his:PaginationTag>
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
			
			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<!--  Added By warish :04-aug-2017: this button worked by row to coloumn --> 
			<img id="btnInvertTbl" tabindex="0" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/imagechanges1.jpg">
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
				<table id="pivotTable" width="100%" cellpadding="0" cellspacing="1" border="1">
					<tr>
					<!-- 	<td width="15%" class="tdfonthead" style="border-left:outset black 2px; border-right:inset black 2px;border-top:outset black 2px; border-bottom:inset black 2px;">
					 -->		
					 <td width="15%" ><div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									&nbsp;<!-- <b><bean:message key="date" /></b>
									<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13) getOrderBy('<%=Config.SORT_TYPE_ASC%>','<%=strChartID%>');" onClick="getOrderBy('<%=Config.SORT_TYPE_ASC%>','<%=strChartID%>');">
									<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13) getOrderBy('<%=Config.SORT_TYPE_DSC%>','<%=strChartID%>');" onClick="getOrderBy('<%=Config.SORT_TYPE_DSC%>','<%=strChartID%>');"> -->
								</font>
							</div>
						</td>
	<%
			for(int j=i*colsLimit;j<(i*colsLimit)+colsLimit;j++)
			{
				ChartColumnHead chartCol = (ChartColumnHead)mpHeader.get(mpHeader.keySet().toArray()[j]);
	%>
						<%-- <td width="<%=85/colsLimit%>%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						 --%>	 <td width="<%=85/colsLimit%>%" >
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
			//Iterator mpDataKeyItr = mpData.keySet().iterator();
			//while(mpDataKeyItr.hasNext())
			for(int k=startIndex; k<=endIndex; k++)
			{
				//String entryDate = (String) mpDataKeyItr.next();
				String entryDate = (String) arrKeys[k];
				Map mpParaValues = (Map) mpData.get(entryDate);
%>
					<tr>
						<!-- <td width="15%" class="tdfonthead"  style="border-left:outset black 2px; border-right:inset black 2px;">
						 -->	<td width="15%">
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
					<%-- 	<td width="<%=85/colsLimit%>%" class="tdfont">
					 --%>	<td width="<%=85/colsLimit%>%" >
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
