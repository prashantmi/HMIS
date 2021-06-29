<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig;"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:javascript src="/opd/js/opd_desk_new.js"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
</head>
<his:statusInProcessWithJsp>
	<%System.out.println("hellodf"); %>
	<%
		PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_DISCHARGE_ADMISSION_VO);
		
		%>
	
		<div class="col-*-*" style="font-size:1.2em;text-align:left;">
		<b>Admission Detail&nbsp;:</b>
		</div>
		<div class="container">
		<div class="row" >
		<div class="col-sm-4 col-lg-4 col-md-4 col-xl-4" style="text-align:left;padding-top:5px;">
		<b><bean:message key="admNo" />:</b>
				<bean:write name="EHRSection_PatientTileFB" property="patAdmNo" />
		</div>		
		<div class="col-sm-4 col-lg-4 col-md-4 col-xl-4" style="text-align:left;padding-top:5px;">		
			<b><bean:message key="admittedOn" />:</b>
			<bean:write name="EHRSection_PatientTileFB" property="admittedOn" />
		</div>
		<div class="col-sm-4 col-lg-4 col-md-4 col-xl-4"  style="text-align:left;padding-top:5px;">
		<b>	<bean:message key="consultant" />:</b>
			<bean:write name="EHRSection_PatientTileFB" property="consultantName" />						
		</div>
		</div>
		<div class="row">
		<div class="col-sm-4 col-lg-4 col-md-4 col-xl-4" style="padding-top:7px;text-align:left;">
				<b><bean:message key="deptUnitName" />:</b>
				<bean:write name="EHRSection_PatientTileFB" property="departmentUnitName" />
		</div>
		<div class="col-sm-4 col-lg-4 col-md-4 col-xl-4" style="padding-top:7px;text-align:left;">
				<b><bean:message key="ward" />:</b>
				<bean:write name="EHRSection_PatientTileFB" property="wardName" />
		</div>
		<div class="col-sm-4 col-lg-4 col-md-4 col-xl-4" style="padding-top:7px; text-align:left;">
			<b>	<bean:message key="room" />/Bed:</b>
				<bean:write name="EHRSection_PatientTileFB" property="ipdRoomName" />(<bean:write name="EHRSection_PatientTileFB" property="bedName" />)
				</div>
		</div>
		<div class="row">
			<div class="col-sm-12 col-lg-12 col-md-12 col-xl-12" style="padding-top:7px; text-align:left;">
				 <% 
				                 String dischargeDate="";
				
				            %>
				
				            <% 	   
				            dischargeDate=(String)session.getAttribute("dischargeDate");
				            System.out.println("DischargeDate=++###="+dischargeDate);
				            if(dischargeDate==null || dischargeDate.equals("")){
				        	dischargeDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

				           }
				            
				           %>
				            <b>Discharge On:</b>&nbsp;<%= dischargeDate%>
				         
				</div>
		</div>
		</div>
		

<%-- 		<table class="table ">
		<td style="font-size:1.2em;font-weight:bold;"width="100%" colspan="6">
       Admission Detail&nbsp;:

</td>
			<tr>
			            
						<td  valign="top" >
						<div align="left">
					
							<b><bean:message key="admNo" />:</b>
							
								<bean:write name="EHRSection_PatientTileFB" property="patAdmNo" />
												
					
					</div>
						</td>
			           	<td  valign="top" >
						<div align="left">
						
							<b><bean:message key="admittedOn" />:</b>
							
								<bean:write name="EHRSection_PatientTileFB" property="admittedOn" />
												
					
						</div>
						</td>

						
						<td valign="top" >
						<div align="left">
						
							<b><bean:message key="deptUnitName" />:</b>
							
								<bean:write name="EHRSection_PatientTileFB" property="departmentUnitName" />
										
					
					</div>
						</td>
						<td  valign="top" >
						<div align="left">
					
							<b><bean:message key="ward" />:</b>
						
								<bean:write name="EHRSection_PatientTileFB" property="wardName" />
							</div>
						</td>
						
						</tr>
						<tr>
						<td  valign="top" >
						<div align="left">
						
							<b><bean:message key="room" />:</b>
						
								<bean:write name="EHRSection_PatientTileFB" property="ipdRoomName" />
						
						</div>
						</td>
						<td  valign="top" >
						<div align="left">
						
							<b><bean:message key="bed" />:</b>
							
								<bean:write name="EHRSection_PatientTileFB" property="bedName" />
											
					
					</div>
						</td>
					<td  valign="top" colspan="2">
					
						<div align="left">
						
							
							<b><bean:message key="consultant" />:</b>
							
								<bean:write name="EHRSection_PatientTileFB" property="consultantName" />
								</div></td>
						</tr>
						
						
		</table> --%>


</his:statusInProcessWithJsp>
</html>