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
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.vo.EpisodeReferVO"%>
<%@page import="opd.OpdConfig"%>
<script type="text/javascript" src="/HISClinical/hisglobal/transactionutil/js/master.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/validation.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/util.js"></script>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	
<div style="margin-top: 1px; width: 99%;" align="center">
	<%
		List patDetailForCurrentVisit = (List)session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
		//PatientDetailVO vo = (PatientDetailVO)session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
		List patDetailEpisodeDtl = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST);
		List patReferDetail = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_REEERRED_DETAIL_LIST);
		System.out.print("On Visit detail JSP list Size is :-"+patDetailForCurrentVisit.size());
		Integer patDetailForCurrentVisit_size =patDetailForCurrentVisit.size();
	%>
			<%
			if(patDetailForCurrentVisit!=null && patDetailForCurrentVisit.size()>0)
			{
				for(int x=0;x<patDetailForCurrentVisit.size();x++)
				{
					PatientDetailVO vo = (PatientDetailVO)patDetailForCurrentVisit.get(x);
			%>
				<div class="div-table width100" >
						<div class="div-table-row ">							
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<a>Visit Reason- </a>
								<a><%=(vo.getVisitReason()==null)?"NA":vo.getVisitReason()  %></a>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<a>Visit date- </a>
								<a><%=(vo.getEpisodeStartDate()==null)?"NA":vo.getEpisodeStartDate()  %></a>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<a>Visit No- </a>
								<a><%=(vo.getEpisodeVisitNo()==null)?"NA":vo.getEpisodeVisitNo()  %></a>
							</div>
						</div>
				</div>
			<%
				}
			}
			else
			{
		%>
			<div class="div-table width100" >
				<div class="div-table-row ">							
						<div class="div-table-col width100" align="center">
								<a>No Data Found</a>
						</div>
				</div>
			</div>
		<%
			}
		%>
		<% 	if(patReferDetail!=null && patReferDetail.size()>0)
			{ %>		
			<div class="div-table width100" >
			<%
			for(int x=0;x<patReferDetail.size();x++)
			{
				EpisodeReferVO referVo = (EpisodeReferVO)patReferDetail.get(x);
			%>
				 <div class="div-table-row ">							
								<div class="div-table-col width100" style="font-style: bold;" align="left" >
									<a>Refer Details- </a>
									<a> Type : <%=(referVo.getReferMode()==null)?"NA":referVo.getReferMode()  %> ;
									Date : <%=(referVo.getEpisodeDate()==null)?"NA":referVo.getEpisodeDate()  %> ;
									By : <%=(referVo.getPatRefDoctor()==null)?"NA":referVo.getPatRefDoctor()  %> ;
									Address :<%=(referVo.getRefFromDepartment()==null)?"NA":referVo.getRefFromDepartment()  %><%=(referVo.getPatRefHospitalName()==null)?"NA":referVo.getPatRefHospitalName()  %></a>						
								</div>
				</div>
						
				<div class="div-table-row ">							
								<div class="div-table-col width100" style="font-style: bold;" align="left" >
									<a>Keywords :</a>
									<a> Type : <%=referVo.getDepartment()%></a>
								</div>
				</div>
						
			<%
				}
			%>							
			</div>
			<%	} %>
	<!--<iframe class="wrapper rounded" id="frmDynamicDeskHeader" name="frmDynamicDeskHeader" src="/HISClinical/hisglobal/utility/dynamicdesk/headerNew.cnt"  
				scrolling="no"  frameborder="1" marginwidth="0" marginheight="0" ></iframe>-->
</div>

<html:hidden name="DoctorDeskFB" property="episodeCode"/>
<html:hidden name="DoctorDeskFB" property="episodeVisitNo"/>
<html:hidden name="DoctorDeskFB" property="departmentUnitCode"/>
<html:hidden name="DoctorDeskFB" property="roomCode"/>

</body>
</html>