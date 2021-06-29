<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="ehr.visitreason.vo.EHRSection_VisitReasonVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="emr.vo.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>VISIT_REASON_PRN</title>      

   
 </head>
<style>

</style>
<%
// EHRSection_VisitReasonVO VisitVO = (EHRSection_VisitReasonVO)session.getAttribute(EHRConfig.VISIT_REASON_ESSENTAILS); 
/* EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
EHRSection_VisitReasonVO VisitVO= patencountervo.getListSaveAllVisitReason(); */



%>


<body style="margin:0%">
<form>
<div class="">
	<div class="row">
		<div class="col-xs-12">
		<% 
				EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
                EHRSection_VisitReasonVO VisitVO= patencountervo.getListSaveAllVisitReason();

				
				if(VisitVO.getEhrVisitReason()!=null)
				{
				 %>
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
			<b> VISIT REASON</b>
			</font>
			<%} %>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">
		<%-- <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=VisitVO.getEhrVisitReason()==null ? "-" : VisitVO.getEhrVisitReason() %></font> --%>
		<%	    //Map mpEssentials = (HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				//List<EHRSection_DiagnosisVO> listdrugDtlVO = (List<EHRSection_DiagnosisVO>) mpEssentials.get(EHRConfig.EHR_DIAGNOSIS_SAVE); 
				
				EHR_PatEncounterDetailsVO patencountervo1=(EHR_PatEncounterDetailsVO) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
                EHRSection_VisitReasonVO VisitVO1= patencountervo.getListSaveAllVisitReason();

				
				if(VisitVO!=null)
				{
				 %>
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					<%=VisitVO1.getEhrVisitReason()==null?"":VisitVO.getEhrVisitReason()%><br>
		
				</font>
			<%} %>
		</div>
	</div>
</div>

<script>

</script>
</form>
</body>

</html>