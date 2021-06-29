<%@page import="hisglobal.utility.HelperMethods"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Map"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="hisglobal.vo.TemplateMasterVO"%>
<%@page import="ehr.complaints.vo.EHRSection_ComplaintsVO"%>
<%@page import="hisglobal.utility.generictemplate.controller.utl.GenericTemplateUTL"%>
<%@page import="hisglobal.tools.tag.GenericTemplateTag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<script type="text/javascript">

function printReport(e)
{
	var patCrno = document.getElementsByName("patCrNo")[0].value;
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY&waterMarkText=CR No.:"+patCrno;
	openPopup(url,e,400,600);
}

function validateGoGetTemplate()
{
	
		var selTemps = document.getElementsByName('selectedTemplates');
		var count=0;
		for(var i=0;i<selTemps.length;i++)
			if(selTemps[i].checked)
				count++;
		if(count==0)
		{
			alert("Select at Least One Template ");
			selTemps[0].focus();
			return false;
		}
	
	return true;
}

function selUnselTempPara(evnt,tempChk)
{
	var tblParas = document.getElementById('tblParas#'+tempChk.value);
	var elemParas = tblParas.getElementsByTagName("INPUT");	
	for(var i=0;i<elemParas.length;i++)
		if(tempChk.checked)
			elemParas[i].checked=true;
		else
			elemParas[i].checked=false;
}
</script>

<%!
	private boolean haveDataInVisit(List _lstTemps, Map _mpChartClinicalData, String _visit)
	{
		boolean flag=false;
		Map mpVisitData = (Map)_mpChartClinicalData.get(_visit);
		Iterator _lstTempsItr = _lstTemps.iterator();
		while(_lstTempsItr.hasNext())
		{
			Entry temp = (Entry)_lstTempsItr.next();
			if(mpVisitData.get(temp.getValue())!=null && ((Map)mpVisitData.get(temp.getValue())).size()>0)
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

<body style="margin:0%">
<form>
<html:hidden name="EHRSection_ComplaintsFB" property="deskMenuId" value="1"/>
<div class="">

<div id="complaints">	
	<div class="row">
		<div class="col-xs-12">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b> COMPLAINTS </b></font>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">


<!-- <div id="pdfPrintingHTMLData">	 -->
<table width="100%" style="margin:0%">
		
		<%	
			List lstVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES_COMPLAINTS);
			List lstTemps = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_COMPLAINTS);
			Map mpChartClinicalData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP_COMPLAINTS);
			Map mpChartParaDetailData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_DETAIL_MAP_COMPLAINTS);
			Map map = (Map)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
			if(lstVisitDates!=null)
			{
			Iterator lstVisitDatesItr = lstVisitDates.iterator();
			while(lstVisitDatesItr.hasNext())
			{
				
			Entry visitDate = (Entry)lstVisitDatesItr.next();
			if(haveDataInVisit(lstTemps, mpChartClinicalData, visitDate.getValue()))
			{
				
		%>
	
		
			<tr>
			<td width="100%" class="" colspan="2"><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">	
			<u><b><%=visitDate.getLabel()%></b></u>
					
				</font>	</td>
				</tr>
			<%
			Map mpVisitData = (Map)mpChartClinicalData.get(visitDate.getValue());
			Iterator lstTempsItr = lstTemps.iterator();
			
			while(lstTempsItr.hasNext())
			{
				
				Entry temp = (Entry) lstTempsItr.next();
				if(mpVisitData!=null && haveDataInTemplate(mpVisitData, temp.getValue()))
				{
					session.setAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP,mpVisitData);
					session.setAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP,mpChartParaDetailData.get(visitDate.getValue()));
					
					
		%>				
		<tr>
		<td width="2%" class="">
			<div align="left">
			</div>
					</td> 
		<td width="98%" class="" ><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">	
		
		<b><%=temp.getLabel()%></b> 
 		 <his:GenericTemplateTag templateId="<%=temp.getValue()%>" mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT %>"></his:GenericTemplateTag>
		</font></td> 
		</tr>
		
		<% }	}
			}    } }
			
		%>	
	
	</table>	
	
</div></div></div></div></form></body></html>


