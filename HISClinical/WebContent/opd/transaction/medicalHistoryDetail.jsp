<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.vo.PatientAlertsDetailVO"%>
<%@page import="hisglobal.vo.PatAllergyDtlVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="ehr.history.vo.EHRSection_HistoryVO"%>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function openTab(deskMenuId, deskMenuURL)
{
	alert("Open Dialog"+"  deskMenuId :"+deskMenuId+"  deskMenuURL :"+deskMenuURL);
}
</script>
</head>

<body>

    <bean:define id="crno" name="DoctorDeskFB" property="patCrNo"></bean:define>
	<bean:define id="episodecode" name="DoctorDeskFB" property="episodeCode"></bean:define>
	<bean:define id="episodevisitno" name="DoctorDeskFB" property="episodeVisitNo"></bean:define>
	<% 
	
	
	
	String medicalHistoryUrl = "/emr/ehrComposition_PAT_HISTORY.cnt?hmode="+"PATCLINICALDOC_PAT_HISTORY_SELECT_OLD"+"&patCrNo="+crno+"&episodeVisitNo="+episodevisitno+"&episodeCode="+episodecode;  %>
	
<div id="medicalHistoryId" style="margin-top: 1px; width: 99%;" align="center">
	<%
		List patAllergyDetail = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_DESK_TILE);
		List patChronicDetail = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_DESK_TILE);
		
		//List<EHRSection_HistoryVO> patHistory = (List<EHRSection_HistoryVO>)session.getAttribute(EHRConfig.EPISODE_HISTORY_LIST_FOR_DASHBOARD);
		System.out.print("On Allergy detail list Size is :-"+patAllergyDetail.size());
		System.out.print("On Chronic detail list Size is :-"+patChronicDetail.size());
	%>
	<%if((patAllergyDetail!=null && patAllergyDetail.size()>0) || (patChronicDetail!=null && patChronicDetail.size()>0))
	{ %>
	
			<%if(patAllergyDetail!=null && patAllergyDetail.size()>0)
			{%>
				<div class="div-table width100" >
						<div class="div-table-row ">							
							<div class="div-table-col width100 deskTile"  align="left">
								<a onclick="open_Tab_New('268','Patient Allergies','DESKPATIENTALERGIES')" style="font-style: bold;" href="#"><font color="#000000">Allergies</font></a>
							
							 <!-- 	<a onclick="open_Tab_New('7','Allergies','DESKALLERGIES')" style="font-style: bold;" href="#"><font color="#000000">Allergies</font></a>
							 -->
							 	<a><font color="#000000" style="font: bold; ">	-					
									<%
										for(int x=0;x<patAllergyDetail.size();x++)
										{
											PatAllergyDtlVO vo = (PatAllergyDtlVO)patAllergyDetail.get(x);
									%>
														&nbsp;<%-- <%=vo.getAllergyTypeName()%>: --%> <%=vo.getAllergyName()%> (<%=vo.getDurationDays() %>);
									<%
										}
									%>			
							</font></a></div>
						</div>
				</div> <br>
			<%} %>
			
			<%if(patChronicDetail!=null && patChronicDetail.size()>0)
			{%>				
				<div class="div-table width100" >
						<div class="div-table-row ">							
							<div class="div-table-col width100 deskTile"  align="left">
								<a onclick="open_Tab_New('107','Patient Chronic Disease','GENERICPATIENTALERTS')" style="font-style: bold;" href="#"><font color="#000000">Chronic Diseases</font></a>
								<a><font color="#000000">	-
									<%
										for(int x=0;x<patChronicDetail.size();x++)
										{
											PatientAlertsDetailVO alertVo = (PatientAlertsDetailVO)patChronicDetail.get(x);
									%>
														&nbsp;<%=alertVo.getAlertName()%>;
									<%
										}
									%>		
							</font></a></div>
						</div>
				</div>
			<%} %>
		<%
		}
		else
		{
		%>
			<div class="div-table width100" >
				<div class="div-table-row ">							
						<div class="div-table-col width100" align="center">
								
						</div>
				</div>
			</div>
		<%
			}
		%>
</div>
<div>
<jsp:include page="<%=medicalHistoryUrl%>" flush="true"></jsp:include>
</div>

<html:hidden name="DoctorDeskFB" property="episodeCode"/>
<html:hidden name="DoctorDeskFB" property="episodeVisitNo"/>
<html:hidden name="DoctorDeskFB" property="departmentUnitCode"/>
<html:hidden name="DoctorDeskFB" property="roomCode"/> 
<html:hidden name="DoctorDeskFB" property="patCrNo"/> 
<script type="text/javascript">
    $(function(){      
      $('#medicalHistoryId').slimscroll({
        railDraggable: true,
        height: '85%'
      });
    });
</script>

</body>
</html>