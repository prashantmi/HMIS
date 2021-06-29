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
<%@page import="ehr.examination.vo.EHRSection_ExaminationVO"%>
<%@page import="hisglobal.utility.generictemplate.controller.utl.GenericTemplateUTL"%>
<%@page import="hisglobal.tools.tag.GenericTemplateTag"%>

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
	if('<bean:write name="EHRSection_ExaminationFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE%>')
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
	else if('<bean:write name="EHRSection_ExaminationFB" property="reportMode"/>' == '<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE%>')
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
			if(mpVisitData!=null && mpVisitData.get(temp.getValue())!=null && ((Map)mpVisitData.get(temp.getValue())).size()>0)
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


<his:statusTransactionInProcess>
<his:ContentTag>
<div id="pdfPrintingHTMLData">	
<table width="100%" style="margin:1%">
	<logic:equal name="EHRSection_ExaminationFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE %>">
		
		<%
			List lstVisitDates = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES_EXAMINATION);
			List lstTemps = (List)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_EXAMINATION);
			Map mpChartClinicalData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP_EXAMINATION);
			Map mpChartParaDetailData = (Map)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_DETAIL_MAP_EXAMINATION);
			Map map = (Map)session.getAttribute(OpdConfig.GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP);
			
			Iterator lstVisitDatesItr = lstVisitDates.iterator();
			while(lstVisitDatesItr.hasNext())
			{
				
				Entry visitDate = (Entry)lstVisitDatesItr.next();
				if(haveDataInVisit(lstTemps, mpChartClinicalData, visitDate.getValue()))
				{
					
		%>
	
		
			<tr>
			 <td width="2%" class="">
						<div align="left">
						<%-- <input type="checkbox" name="selectedTemplates" value="<%=visitDate.getLabel()%>" checked="checked" /> --%>
										
						<%-- <input type="checkbox" name="ehrcomp_COMPLAINTS_chk_select"    checked="checked"  value="<%=drugDtlVO.getSelectedSectionData()%>" onchange="setPatDocSelectJSON('COMPLAINTS')" />
						<input type="hidden" name="ehrcomp_COMPLAINTS_dataelem_json_<%=drugDtlVO.getSelectedSectionData()%>" value='<%=drugDtlVO.getJSONObj().toString() %>'  />   
 --%>
					
				<%-- <input type="checkbox" name="ehrcomp_EXAMINATION_chk_select"    checked="checked"  value="<%=visitDate.getValue()%>" onchange="setPatDocSelectJSON('EXAMINATION')" />
				<input type="hidden" name="ehrcomp_EXAMINATION_dataelem_json_<%=visitDate.getValue()%>" value='<%=visitDate.getValue() %>'  />   
 --%>
							</div>
					</td> 
			<td width="98%" class="">
						<div align="left">
							
							<u><b><%=visitDate.getLabel()%></b></u>
						</div>
					</td>
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
					TemplateMasterVO vo= new TemplateMasterVO();
					vo = GenericTemplateUTL.getTemplateDataById(temp.getValue(), request);
					EHRSection_ExaminationVO evo=new EHRSection_ExaminationVO();
					evo.setSelectedSectionData(temp.getValue());
					HelperMethods.populate(evo, vo);
					evo.setSelectedSectionData(temp.getValue());
					evo.setEntryDate(visitDate.getLabel());
					GenericTemplateTag tag= new GenericTemplateTag(temp.getValue(),GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT);
					/* String html = tag.templateLogic(request, session);
				 	//System.out.println("jammm"+html);
					evo.setTemplateHTML(html); */
					 					
					
		%>				
		<tr>
		<td width="2%" class="">
			<div align="left">
			</div>
					</td> 
		<td width="98%" class="" >
		<!-- <div align="left"> -->
		<%-- <input type="checkbox" name="ehrcomp_EXAMINATION_chk_select"    checked="checked"  value="<%=evo.getSelectedSectionData()%>" onchange="setPatDocSelectJSON('EXAMINATION')" /> --%>
		<input type="checkbox" name="ehrcomp_EXAMINATION_chk_select" value="<%=evo.getSelectedSectionData()%>"  onchange="setPatDocSelectJSON('EXAMINATION')" checked="checked"/>
					 
		
		<b><font color="#126295"><%=temp.getLabel()%></font></b> 
 		 
 		
 		<%String html = tag.templateLogic(request, session);
 		evo.setTemplateHTML(html.substring(0, html.length() - 2));
 		System.out.println("htmlllllllllll"+evo.getTemplateHTML());%>
 		 <input type="hidden" name="ehrcomp_EXAMINATION_dataelem_json_<%=evo.getSelectedSectionData()%>" value='<%=evo.getJSONObj().toString() %>'/>  
		<%-- <his:GenericTemplateTag templateId="<%=temp.getValue()%>" mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT %>"></his:GenericTemplateTag> --%>
		<!-- </div> -->
		<%System.out.println("htmlllExam"+evo.getTemplateHTML());%>
		</td> 
		</tr>
		
		<% }	}
			}    } 
			
		%>	
	</logic:equal>
	</table>	</div>
	<logic:equal name="EHRSection_ExaminationFB" property="reportMode" value="<%=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_PARAMETER_WISE %>">
		<jsp:include page="/hisglobal/utility/generictemplate/chartTile.cnt" flush="true"  />
	</logic:equal>
	</his:ContentTag>
</his:statusTransactionInProcess>



<html:hidden name="EHRSection_ExaminationFB" property="hmode"/>
<html:hidden name="EHRSection_ExaminationFB" property="userSeatId"/>
<html:hidden name="EHRSection_ExaminationFB" property="wardCode"/>
<html:hidden name="EHRSection_ExaminationFB" property="deptUnitCode"/>
<html:hidden name="EHRSection_ExaminationFB" property="episodeCode"/>
<html:hidden name="EHRSection_ExaminationFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_ExaminationFB" property="admissionNo"/>
<html:hidden name="EHRSection_ExaminationFB" property="deskType"/>
<html:hidden name="EHRSection_ExaminationFB" property="deskId"/>
<html:hidden name="EHRSection_ExaminationFB" property="deskMenuName"/>
<html:hidden name="EHRSection_ExaminationFB" property="reportMode"/>
