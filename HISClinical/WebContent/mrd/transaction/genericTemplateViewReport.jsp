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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function printReport(e)
{
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY";
	openPopup(url,e,400,600);
}

function validateGoGetTemplate()
{
	if('<bean:write name="GenericTemplateTileFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE%>')
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
	}
	else if('<bean:write name="GenericTemplateTileFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE%>')
	{
		var selParas = document.getElementsByName('selectedParas');
		var count=0;
		for(var i=0;i<selParas.length;i++)
			if(selParas[i].checked)
				count++;
		if(count==0)
		{
			alert("Select at Least One Parameter ");
			selParas[0].focus();
			return false;
		}
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


<his:TitleTag name='Template Report'>
</his:TitleTag>

<his:statusTransactionInProcess>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%"  class="tdfonthead">
					<div align="right">
						<a style="cursor:pointer" onclick="printReport(event);" >
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								PRINT
							</font>
						</a>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<div id="pdfPrintingHTMLData">
	<logic:equal name="GenericTemplateTileFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE %>">
		<%
			List lstVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES);
			List lstTemps = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS);
			Map mpChartClinicalData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
			Map mpChartParaDetailData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_DETAIL_MAP);

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
							session.setAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP,mpChartParaDetailData.get(visitDate.getValue()));
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
	</logic:equal>
	<logic:equal name="GenericTemplateTileFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE %>">
	<his:ContentTag>
		<jsp:include page="/hisglobal/utility/generictemplate/chartTile.cnt" flush="true" />
	</his:ContentTag>	
	</logic:equal>
	</div>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitForm('TEMPREPORT');" onkeypress="if(event.keyCode==13) submitForm('TEMPREPORT');" >
</his:ButtonToolBarTag>

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
<html:hidden name="GenericTemplateTileFB" property="reportMode"/>