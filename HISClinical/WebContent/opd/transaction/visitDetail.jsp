<!-- 
/**
 * @author CDAC
 */
-->
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	
<div id="visitDetailId" style="margin-top: 1px; width: 99%;" align="center">
	<%
		
	List patDetailForCurrentVisit = (List)session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
		//PatientDetailVO vo = (PatientDetailVO)session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
		List patDetailEpisodeDtl = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST);
		List patReferDetail = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_REEERRED_DETAIL_LIST);
		System.out.print("On Visit detail JSP list Size is :-"+patDetailForCurrentVisit.size());
	%>
	<div class="div-table width100" >
			<%
			if(patDetailForCurrentVisit!=null && patDetailForCurrentVisit.size()>0)
			{
				//PatientDetailVO vo = (PatientDetailVO)patDetailForCurrentVisit.get(0);
				PatientDetailVO vo = (PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				Integer patDetailForCurrentVisit_size =patDetailForCurrentVisit.size();
			%>
						<div class="div-table-row ">							
							<div class="div-table-col width100 deskTile" align="left">
								<font color="#126295">Reason of Visit : </font>
								<font color="#000000"><%=(vo.getVisitReason()==null)?"NA":vo.getVisitReason()  %></font>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100 deskTile" align="left">
								<font color="#126295">Visit Date : </font>
								<font color="#000000"><%=(vo.getEntryDate()==null)?"NA":vo.getEntryDate()  %></font>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100 deskTile " align="left">
								<font color="#126295">Visit No : </font>
								<font color="#000000"><%=(vo.getEpisodeVisitNo()==null)?"NA":vo.getEpisodeVisitNo()  %></font>
							</div>
						</div>
						<% 	if(vo.getEpisodeVisitNo()!=null)
						{	int vno=Integer.parseInt(vo.getEpisodeVisitNo());
						if(vno>1)
				{ %>	
						<div class="div-table-row ">
							<div class="div-table-col width100 deskTile " align="left">
								<font color="#126295">First Visit Date : </font>
								<font color="#000000"><%=(vo.getEpisodeStartDate()==null)?"NA":vo.getEpisodeStartDate()  %></font>
							</div>
						</div>
						<% 	}} %>	
			<% 	if(patReferDetail!=null && patReferDetail.size()>0)
				{ 
					EpisodeReferVO referVo = (EpisodeReferVO)patReferDetail.get(0);
					if((referVo.getIsRefferInOut().equals("2")) || (referVo.getIsRefferInOut().equals("3")))
					{			
						%>		
						
						<%if(referVo.getIsRefferInOut().equals("3")){ %>
							<div class="div-table width100" >
							 <div class="div-table-row ">							
											<div class="div-table-col width100 deskTile" align="left" >
												<font color="#126295">Refer Details : </font>
												<font color="#000000"> Internal Refer;
												<%if((referVo.getReffDateTime()!=null)){ %>Date : <%=referVo.getReffDateTime()%>;<%} %>
												<%if((referVo.getPatRefDoctor()!=null)){ %>By : <%=referVo.getPatRefDoctor()%>;<%} %>
												<%if((referVo.getRefFromDepartment()!=null)){ %>Address :<%=referVo.getRefFromDepartment()%>;<%} %> 
												<%if((referVo.getPatRefHospitalName()!=null)){ %><%=referVo.getPatRefHospitalName()%>;<%} %>
												</font>			
											</div>
							</div>
						   </div>
						<%} if(referVo.getIsRefferInOut().equals("2")){ %>
							<div class="div-table width100" >
							 <div class="div-table-row ">							
											<div class="div-table-col width100 deskTile" align="left" >
												<font color="#126295">Refer Details : </font>
												<font color="#000000"> External Refer;
												<%if((referVo.getReffDateTime()!=null)){ %>Date : <%=referVo.getReffDateTime()%>;<%} %>
												<%if((referVo.getExtHospDoctName()!=null)){ %>By : <%=referVo.getExtHospDoctName()%>;<%} %>
												<%if((referVo.getExtHospitalName()!=null)){ %>Hospital :<%=referVo.getExtHospitalName()%>;<%} %>	
												<%if((referVo.getExtHospitalDept()!=null)){ %>Department :<%=referVo.getExtHospitalDept()%><%} %>					
												<%if((referVo.getExtHospitalDeptUnit()!=null)){ %>Unit :<%=referVo.getExtHospitalDeptUnit()%><%} %>
												</font>					
											</div>
							</div>
									
							</div>
						<%}
					}
				}
				%>							
				<% if(vo.getKeywords()!=null){ %>
					<div class="div-table width100" >
						<div class="div-table-row ">							
										<div class="div-table-col width100 deskTile" align="left" >
											<font color="#000000">Keywords - <%= vo.getKeywords()%>;</font>
										</div>
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
						<div class="div-table-col width100 deskTile" align="center">
								<font color="#000000">No Data Found</font>
						</div>
				</div>
			</div>
		<%
			}
		%>
	<!--<iframe class="wrapper rounded" id="frmDynamicDeskHeader" name="frmDynamicDeskHeader" src="/HISClinical/hisglobal/utility/dynamicdesk/headerNew.cnt"  
				scrolling="no"  frameborder="1" marginwidth="0" marginheight="0" ></iframe>-->
	</div>
</div>

<html:hidden name="DoctorDeskFB" property="episodeCode"/>
<html:hidden name="DoctorDeskFB" property="episodeVisitNo"/>
<html:hidden name="DoctorDeskFB" property="departmentUnitCode"/>
<html:hidden name="DoctorDeskFB" property="roomCode"/>
<script type="text/javascript">
    $(function(){      
      $('#visitDetailId').slimscroll({
        railDraggable: true,
        height: '85%'
      });
    });
</script>

</body>
</html>