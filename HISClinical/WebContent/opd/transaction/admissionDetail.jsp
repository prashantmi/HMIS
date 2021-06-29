<!-- 
/**
 * @author CDAC
 */
-->

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.vo.EpisodeReferVO"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>.
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>

<body>

<div style="margin-top: 1px; width: 99%;" align="center">
	<%
		//List patDetailForCurrentVisit = (List)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
	PatientDetailVO vo = (PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		
	%>
	<div class="div-table width100" >
			<%
			if(vo!=null )
			{
			%>    <!--  Modified by Vasu on 23-june-2017 for: Fetching patient admission details into "Admission Summary" section   -->
			
						<div class="div-table-row">							
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<font color="#126295">Admission No : </font><b><font color="#000000"><%=(vo.getPatAdmNo()==null)?"NA":vo.getPatAdmNo()%></font></b>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<font color="#126295">Admission Date & Time : </font><b><font color="#000000"><%=(vo.getAdmDateTime()==null)?"NA":vo.getAdmDateTime()%></font></b>
							</div>
						</div>
						<div class="div-table-row">							
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<font color="#126295">Patient Name : </font><b><font color="#000000"><%=(vo.getPatName()==null)?"NA":vo.getPatName()%></font></b>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<font color="#126295">Department Unit : </font><b><font color="#000000"><%=(vo.getDepartmentUnitName()==null)?"NA":vo.getDepartmentUnitName()%></font></b>
							</div>
						</div>
						<div class="div-table-row">							
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<font color="#126295">Room : </font><b><font color="#000000"><%=(vo.getIpdRoomName()==null)?"NA":vo.getIpdRoomName()%></font></b>
							</div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col width100" style="font-size: x-small;" align="left">
								<font color="#126295">Bed: </font><b><font color="#000000"><%=(vo.getBedName()==null)?"NA":vo.getBedName()%></font></b>
							</div>
						</div>							
				<% if(vo.getRequestBy()!=null){ %>	
						<div class="div-table-row ">							
										<div class="div-table-col width100" style="font-size: x-small;" align="left" >
											<font color="#126295">Requested By : </font><b><%= vo.getRequestBy()%>;</b>
										</div>
						</div>
				<%} %>						
				<% if(vo.getConsultantName()!=null){ %>
						<div class="div-table-row ">							
										<div class="div-table-col width100" style="font-size: x-small;" align="left" >
											<font color="#126295">Consultant Name : </font><b><%= vo.getConsultantName()%>;</b>
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
								<font color="#126295"><b>No Data Found</b></font>
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

</body>
</html>