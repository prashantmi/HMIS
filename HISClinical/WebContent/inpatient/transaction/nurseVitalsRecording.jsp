<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.*"%>
<%@page import="hisglobal.presentation.*"%>
<%@page import="hisglobal.utility.Entry"%>

<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.vo.PatientClinicalDetailVO"%>
<%@page import="hisglobal.vo.PatientMonitoringMstVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.fb.GenericTemplateTileFB;"%>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>
<his:javascript src="/registration/js/registration.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<script>
function getVitalCharting(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/ipdVitalRecording.cnt?hmode=VITALCHART&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,800);
}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:TitleTag key="vitals">
	<logic:equal name="VitalsRecordingFB" property="vitalChartFlag" value="<%=OpdConfig.YES%>">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<b>
			<a style="cursor:pointer" onclick="getVitalCharting(event)" >	
				<bean:message key="vitalchart"/>
			</a>	
		</b>
	</font>
	</logic:equal>
</his:TitleTag>

<his:statusTransactionInProcess>

<%
	PatientMonitoringMstVO[] arrVitals = (PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_ARR);
	if(arrVitals!=null && arrVitals.length>0)
	{
%>
	<his:SubTitleTag>
		<his:name>
			<bean:message key="monitorvitals"/>
		</his:name>
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="vital"/></b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="duration"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="last"/> <bean:message key="recordDate" /></b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="lastrecordValue"/></b>
						</font>
					</div>
				</td>
			</tr>
			<logic:iterate id="lastVitalValVO" indexId="idx" name="<%=InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_ARR%>" type="hisglobal.vo.PatientMonitoringMstVO">
				<%
					Map mpValues = (Map)session.getAttribute(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_LAST_RECORD_VALUES_MAP);
					PatientClinicalDetailVO valVO = (PatientClinicalDetailVO)mpValues.get(lastVitalValVO.getParaId());
				%>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:write name="lastVitalValVO" property="paraName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="lastVitalValVO" property="monitorModeName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<%	if(valVO!=null){ %>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=valVO.getRecordDate()%>
							</font>
						</div>
						<%	}	%>
					</td>
					<td width="25%" class="tdfonthead">
						<%	if(valVO!=null){ %>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=valVO.getParaValue()%>
							</font>
						</div>
						<%	}	%>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
<% 
	}
	String deskMenuId= ((GenericTemplateTileFB)request.getAttribute("VitalsRecordingFB")).getDeskMenuId();//new GenericTemplateTileFB();
	List lstActiveTemps=null ;
		System.out.print("*********************"+deskMenuId);
		lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST+deskMenuId) ;
	//if(deskMenuId.equals("1"))//"Complaints"
		//lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST_COMPLAINT) ;
		
		//else if(deskMenuId.equals("2"))//"History"
		//	lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST_HISTORY) ;
		
		//else if(deskMenuId.equals("3"))//"Examination"
		//	lstActiveTemps= (List)session.getAttribute(GenericTemplateConfig.TEMPLATE_TILE_DEFAULT_TEMPLATE_LIST_EXAMINATION) ;
	List lstInactiveTemps = (List)WebUTIL.getSession(request).getAttribute(GenericTemplateConfig.TEMPLATE_TILE_UNDEFAULT_TEMPLATE_LIST+deskMenuId) ;
	if(lstActiveTemps!=null && lstActiveTemps.size()==0 && lstInactiveTemps.size()==0)
	{
%>
	
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%"  class="tdfont">
					&nbsp;&nbsp;&nbsp;<b><font color="#FF0000"> No Template exists for <bean:write name="VitalsRecordingFB"  property="deskMenuName"/></font></b>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
	</his:ButtonToolBarTag>
<%
	}
	else
	{
%>
	<bean:define name="VitalsRecordingFB" property="episodeVisitNo" id="visitNo" type="java.lang.String" />
	<his:ContentTag>
		<div id="pdfPrintingHTMLData">
		<table width="100%" cellspacing="1" cellpadding="0">
		<%	Iterator lstActiveTempsItr = lstActiveTemps.iterator();
			while(lstActiveTempsItr.hasNext())	
			{	
				Entry e = (Entry) lstActiveTempsItr.next();
		%>
			<tr>
				<td width="100%">
					<his:GenericTemplateTag templateId="<%=e.getValue()%>" ></his:GenericTemplateTag>
				</td>
			</tr>
		<%	}	%>
		</table>
		</div>
		<table width="100%" cellspacing="1" cellpadding="0">
		<%	if(lstInactiveTemps.size()>0)	{	%>
			<!-- <tr>
				<td width="100%">
					<a style="cursor:pointer" onclick="openPopup('/HISClinical/opd/opdTemplateTab.cnt?hmode=MORETEMPLATE',event,300,500);" >
						<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							MORE ...
						</font>
					</a>							
				</td>
			</tr> -->
		<%	}	%>
		</table>
	</his:ContentTag>
	
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"   onclick ="submitFormOnValidate(validatePARAMETERCompulsoryField(),'SAVE');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validatePARAMETERCompulsoryField(),'SAVE');" > 
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer"  onclick ="submitForm21('NEW');" onkeypress="if(event.keyCode==13) submitForm21('NEW');" >
	</his:ButtonToolBarTag>
<%
	}
%>
</his:statusTransactionInProcess>

<html:hidden name="VitalsRecordingFB" property="hmode"/>
<html:hidden name="VitalsRecordingFB" property="userSeatId"/>
<html:hidden name="VitalsRecordingFB" property="wardCode"/>
<html:hidden name="VitalsRecordingFB" property="deptUnitCode"/>
<html:hidden name="VitalsRecordingFB" property="episodeCode"/>
<html:hidden name="VitalsRecordingFB" property="episodeVisitNo"/>
<html:hidden name="VitalsRecordingFB" property="admissionNo"/>
<html:hidden name="VitalsRecordingFB" property="deskType"/>
<html:hidden name="VitalsRecordingFB" property="deskId"/>
<html:hidden name="VitalsRecordingFB" property="deskMenuName"/>
<html:hidden name="VitalsRecordingFB" property="deskMenuId"/>
<html:hidden name="VitalsRecordingFB" property="selUndefaultTemp" value=""/>
<html:hidden name="VitalsRecordingFB" property="reportMode"/>