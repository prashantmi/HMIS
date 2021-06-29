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
<%@page import="hisglobal.vo.EpisodeDiagnosisVO;"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	
<div id="diagnosis_Id" style="margin-top: 1px; width: 99%;" align="center">
	<%
		List patDiagnosisDetail = (List)session.getAttribute(OpdConfig.OPD_EPISODE_VISIT_DIAGNOSIS_DETAIL);
		//EpisodeDiagnosisVO vo = (EpisodeDiagnosisVO)session.getAttribute(OpdConfig.OPD_EPISODE_VISIT_DIAGNOSIS_DETAIL);
		System.out.print("On Visit detail JSP list Size is :-"+patDiagnosisDetail.size());
	%>
	
			<%
				if(patDiagnosisDetail!=null && patDiagnosisDetail.size()>0)
				{
					for(int x=0;x<patDiagnosisDetail.size();x++)
					{
						EpisodeDiagnosisVO vo = (EpisodeDiagnosisVO)patDiagnosisDetail.get(x);
				%>
					<div class="div-table width100" >
							<div class="div-table-row " style="font-size: x-small;" align="left">							
								<div class="div-table-col width100 deskTile" >
									<a align="left"><%-- <font color="#000000">* <%=(vo.getDiagnosticCode()==null)?"NA":vo.getDiagnosticCode()  %></font> --%></a>
									<a><font color="#000000"><%=(vo.getDignosisName()==null)?"NA":vo.getDignosisName()  %>
									;
									<%=(vo.getDiagnosticTypeName()==null)?"NA":vo.getDiagnosticTypeName()  %></font></a>
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
							<div class="div-table-col width100 deskTile" align="center">
									<a><font color="#000000">No Data Found</font></a>
							</div>
					</div>
				</div>
			<%
				}
			%>
</div>

<html:hidden name="DoctorDeskFB" property="episodeCode"/>
<html:hidden name="DoctorDeskFB" property="episodeVisitNo"/>
<html:hidden name="DoctorDeskFB" property="departmentUnitCode"/>
<html:hidden name="DoctorDeskFB" property="roomCode"/>
<script type="text/javascript">
    $(function(){ 
    //alert("diagnosis");
      $('#diagnosis_Id').slimscroll({
        railDraggable: true,
        height: '85%'
      });
    });
</script>
</body>
</html>