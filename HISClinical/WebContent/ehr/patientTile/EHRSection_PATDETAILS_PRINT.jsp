<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="ehr.followup.vo.EHRSection_FollowupVO"%>
<%@page import="ehr.EHRConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>OPD Prescription</title>      
   
       
  
   
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
    	border: 2px black solid !important;
    	margin-bottom:5px;
    }
    .pcontainer
    {
    	border: 2px black solid !important;
    	margin-bottom:5px;
    }
</style>


<body style="margin:0%">
<form>
<% PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO); %>
<% EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS); %>

<% EHRSection_FollowupVO VisitVO1 = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS_NEW); %>
<div class="">
	<hr style="border-top:1px solid #000000;">
<table width="100%" cellspacing="0" cellpadding="0">
	<tbody><tr>
		<td style="empty-cells: show" width="25%">
		<div align="right">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					Name&nbsp;
				</font>
			</div>
		</td>
		<td width="25%">
			<div align="left">
				<b>
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
						&nbsp;<%=StringUtils.capitalise(patVO.getPatName().toString())%>
					</font>
				</b>
			</div>
		</td>
		<td style="empty-cells: show" width="25%">
			<div align="right">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					CR No.&nbsp;
				</font>
			</div>
		</td>
		<td style="empty-cells: show" width="25%" nowrap="">
			<div align="left">
				<b>
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
						&nbsp;
						
						<%=patVO.getPatCrNo() %>
					</font>
				</b>
			</div>
		</td>
	</tr>
	<tr>
		<td style="empty-cells: show" width="25%" nowrap="">
			<div align="right">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					Age / Gender&nbsp;
				</font>
			</div>
		</td>
		<td style="empty-cells: show" width="25%" nowrap="">
			<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
				&nbsp;<%=patVO.getPatAge() %>/<%=patVO.getPatGender() %>
			</font></b>
		</td>
		<td style="empty-cells: show" width="25%" nowrap="">
			<div align="right">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					Primary Category&nbsp;
				</font>
			</div>
		</td>
		<td style="empty-cells: show" width="25%" nowrap="">
			<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
				&nbsp;<%=patVO.getPatPrimaryCat()%>
			</font></b>
		</td>
	</tr>
	<tr>
		<td style="empty-cells: show" width="25%" nowrap="">
			<div align="right">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					Father/Spouce Name&nbsp;
				</font>
			</div>
		</td>
		<td style="empty-cells: show" width="25%" nowrap="">
			<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
				&nbsp;<%=patVO.getPatFatherName() == null ? "" : StringUtils.capitalise(patVO.getPatFatherName().toString()) + (patVO.getPatSpouceName() == null ? "": "/" + StringUtils.capitalise(patVO.getPatSpouceName().toString()))%>
			</font></b>
		</td>
		<td style="empty-cells: show" width="25%" nowrap="">
					<div align="right">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
							Department(Unit)&nbsp;
						</font>
					</div>
				</td>			
				<td style="empty-cells: show" width="25%" nowrap="">
					<b>
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
						&nbsp;<%=patVO.getDepartmentName() %>(<%=patVO.getDepartmentUnitName() %>)
						</font>
					</b>
				</td>
	</tr>
	
	<tr>
	<td style="empty-cells: show" width="25%">
				<div align="right">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
						Visit Date&nbsp;
					</font>
				</div>
			</td>
			<td style="empty-cells: show" width="25%">
				<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					&nbsp;<%=VisitVO.getEntryDate() %>
				</font></b>
			</td>
			
			<td style="empty-cells: show" width="25%">
				<div align="right">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
						Consultant Name&nbsp;
					</font>
				</div>
			</td>
			<td style="empty-cells: show" width="25%">
				<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					&nbsp;<%=VisitVO1.getUnitConsultant() %>
				</font></b>
			</td>
	</tr>
	<%-- <tr>
			<td style="empty-cells: show" width="25%">
				<div align="right">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
						Mlc no&nbsp;
					</font>
				</div>
			</td>
			<td style="empty-cells: show" width="25%">
				<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
					&nbsp;<%=VisitVO.getMlcNo()==null?"-": VisitVO.getMlcNo()%>
				</font></b>
			</td>

	</tr>
	 --%>
</tbody></table><hr style="border-top:1px solid #000000;">

</div>
<script>

</script>
</form>
</body>

</html>