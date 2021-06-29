
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="ehr.followup.vo.EHRSection_FollowupVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="emr.vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>FOLLOW_UP_PRN</title>      
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!--  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/combined.css" rel="stylesheet">  -->
       <link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
           <script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>

       
  <!--   <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> -->
      
  

   <script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
   
 </head>
<style>
fieldset.scheduler-border {
    border: 1px groove black !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0px 5px 10px;
        border-bottom:none;
        color:
    }
    .vcontainer
    {
    	border: 1px black solid !important;
    	margin-bottom:5px;
    }
     .pcontainer
    {
    	border: 2px black solid !important;
    	margin-bottom:5px;
    }
</style>
<% //EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS);
EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
if(patencountervo.getListSaveAllFollowUp()!=null)
{
EHRSection_FollowupVO VisitVO= patencountervo.getListSaveAllFollowUp();

%>

<body style="margin:0%">
<form>
<his:ContentTag>
<table id="tablefollowup" width="100%">		
<!-- <div class="">

	<div class="row">
 -->
 <tr>	<td width="10%" class="" >
		<div class="col-xs-1">
			<input type="checkbox" name="ehrcomp_FOLLOWUPADVICE_chk_select"   checked="checked"  value="<%=VisitVO.getSelectedSectionData()%>" onchange="setPatDocSelectJSON('FOLLOWUPADVICE')" />
			<input type="hidden" name="ehrcomp_FOLLOWUPADVICE_dataelem_json_<%=VisitVO.getSelectedSectionData()%>" value='<%=VisitVO.getJSONObj().toString() %>'  />    
		</div>
		</td>
	
	
	<%-- <logic:iterate id="followUpVO" indexId="idx" name="<%=EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST%>" type="ehr.followup.vo.EHRSection_FollowupVO">
	
 --%>	<td width="90%" class="" >
		<!-- <div class="col-xs-11 text-left"> -->
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<% if(VisitVO.getEpisodeIsOpen().equals("0"))
			{%>
				<font color="#126295" size="2"><b>EPISODE END</b></font> <br>
				<font color="#126295" size="2"><b>PROGRESS NOTES</b> </font><br><%=VisitVO.getVisitNotes() == null ? "" : VisitVO.getVisitNotes()%><br>
				<font color="#126295" size="2"><b>EPISODE SUMMARY</b> </font><br><%=VisitVO.getEpisodeSummary() == null ? "" : VisitVO.getEpisodeSummary() %>
			<%}
		else
		{%>
		
			<font color="#126295" size="2"><b>PROGRESS NOTES</b></font> <br/>
				<%=VisitVO.getVisitNotes() == null ? "" : VisitVO.getVisitNotes()%>
		<h5></h5>		
		
			<font color="#126295" size="2"><b>FOLLOW UP </b> </font><br/>
		
		<% if(VisitVO.getNextVisitCriteria()!=null && VisitVO.getNextVisitCriteria().equals("0"))
			{%>
				SOS
			
			<%} %>
			
			
		<% if(VisitVO.getNextVisitCriteria() !=null && VisitVO.getNextVisitCriteria().equals("1"))
				{ %>
					<%=VisitVO.getNextVisitDuration() %>
				<%} %>
				
			<% if( VisitVO.getNextVisitCriteria() !=null && VisitVO.getNextVisitCriteria().trim().equals("4"))
				{ %> Tentative Next Visit Date: 
					<%=VisitVO.getEpisodeNextVisitDate() == null ? "" : VisitVO.getEpisodeNextVisitDate() %>
				<%} %><br>
				
		<%} %>
		</font></td>
		
	
<%--</div></div></div> </logic:iterate> --%>



</tr></table>
</his:ContentTag>
</form>
</body>
<%} %>
</html>