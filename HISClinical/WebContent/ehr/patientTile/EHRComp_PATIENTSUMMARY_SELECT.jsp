<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="ehr.EHRConfig" %>
<%@page import="emr.vo.PatientClinicalDocDetailVO" %>
<his:css src="/hisglobal/css/Color.css"/>
<his:javascript src="/opd/js/opd_desk_new.js"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:statusInProcessWithJsp>
	

		<%

	//	PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
		PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		
		patVO.setPatientName();
		patVO.setPatientCompleteAddress();
		StringBuffer strHiddenPatDtl = new StringBuffer("");
		strHiddenPatDtl.append((patVO.getPatName()!=null && !patVO.getPatName().trim().equals(""))?patVO.getPatName():"");	
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatGuardianName()!=null && !patVO.getPatGuardianName().trim().equals(""))?patVO.getPatGuardianName():"");
		strHiddenPatDtl.append("/");
		strHiddenPatDtl.append((patVO.getPatSpouceName()!=null && !patVO.getPatSpouceName().trim().equals(""))?patVO.getPatSpouceName():"");
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatPrimaryCat()!=null && !patVO.getPatPrimaryCat().trim().equals(""))?patVO.getPatPrimaryCat():"");
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatAge()!=null && !patVO.getPatAge().trim().equals(""))?patVO.getPatAge():"");
		strHiddenPatDtl.append("/");
		strHiddenPatDtl.append((patVO.getPatGender()!=null && !patVO.getPatGender().trim().equals(""))?patVO.getPatGender():"");
		strHiddenPatDtl.append("^");
		strHiddenPatDtl.append((patVO.getPatCompleteAddress()!=null && !patVO.getPatCompleteAddress().trim().equals(""))?patVO.getPatCompleteAddress():"");
		
		StringBuffer strMotherDtl = new StringBuffer("");
		strMotherDtl.append((patVO.getPatMotherName()!=null && !patVO.getPatMotherName().trim().equals(""))?patVO.getPatMotherName():"");
		strMotherDtl.append("^");
		strMotherDtl.append((patVO.getPatMotherCrNo()!=null && !patVO.getPatMotherCrNo().trim().equals(""))?patVO.getPatMotherCrNo():"");
		if(patVO.getMlcNo()!= null && !patVO.getMlcNo().trim().equals("")) patVO.setIsMLC("1");	else patVO.setIsMLC("0");
		%>
		<input type="hidden" name="strHiddenPatDtl" value="<%=strHiddenPatDtl.toString()%>" />
		<input type="hidden" name="strPatName" value="<%=patVO.getPatName()%>" />
		<input type="hidden" name="strCategoryCode" value="<%=patVO.getPatPrimaryCatCode()%>" />
		<input type="hidden" name="strIsMLC" value="<%=patVO.getIsMLC()%>" />
		<input type="hidden" name="strMLCNo" value="<%=patVO.getMlcNo()%>" />
		<input type="hidden" name="strIsNewBorn" value="<%=patVO.getIsNewBorn()%>" />
		<input type="hidden" name="strMotherDtl" value="<%=strMotherDtl%>" />
		<input type="hidden" name="patBalance" value="<%=patVO.getPatBalance()%>" /> <!-- // added by manisha gangwar for alert on patient dashboard for zero balance -->
		
	
		<table width="100%" >
		<% PatientClinicalDocDetailVO clinicalDocVO=(PatientClinicalDocDetailVO)session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS); 
		if(clinicalDocVO.getDocumentType().equalsIgnoreCase("51")){
		
		%>
		<tr><td > 
		<font  style="vertical-align: super; font-size:16px ;font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prescription&nbsp;</font>
		<font  style="vertical-align: super; font-size:16px ;">[&nbsp;<bean:write name="EHRSection_PatientTileFB" property="departmentName" />&nbsp;] 
		</font>
		</td></tr>
		<%} 
		else{%>
			<tr><td > 
		<font  style="vertical-align: super; font-size:16px ;font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Discharge Summary&nbsp;</font>
		<font  style="vertical-align: super; font-size:16px ;">[&nbsp;<bean:write name="EHRSection_PatientTileFB" property="departmentUnitName" />&nbsp;] 
		</font>
		</td></tr>
			
		<%}%>
		
	
			<tr>
			<td style="text-align:center;">
			<div style="display:none"> 
			<input type="checkbox" name="ehrcomp_PATIENTSUMMARY_chk_select"   checked="checked"  value="<%=patVO.getSelectedSectionData()%>" onchange="setPatDocSelectJSON('PATIENTSUMMARY')" />
			<input type="hidden" name="ehrcomp_PATIENTSUMMARY_dataelem_json_<%=patVO.getSelectedSectionData()%>" value='<%=patVO.getJSONObj().toString() %>'  />    
		   </div>
		  <!--  <img src="/HIS/hisglobal/images/patient.jpg" style="height: 5em;padding-right: 10px;"> -->
			<font  style="vertical-align: super; font-size:16px ;font-weight: bold;">
							<bean:write	name="EHRSection_PatientTileFB" property="patFirstName" />
							<bean:write	name="EHRSection_PatientTileFB" property="patMiddleName" />
							<bean:write	name="EHRSection_PatientTileFB" property="patLastName" /></font> &nbsp;
        	<font style="vertical-align: super; font-size:16px ;">(<logic:notEqual name="EHRSection_PatientTileFB" property="patGender" value=""></logic:notEqual>
							<bean:write name="EHRSection_PatientTileFB" property="patGender" />/<bean:write	name="EHRSection_PatientTileFB" property="patAge" />/<bean:write name="EHRSection_PatientTileFB" property="patCat" />&nbsp;) 
							</font>&nbsp;&nbsp;&nbsp;
        	<font style="vertical-align: super; font-size:14px ;font-weight: bold;" >CRN :</font>&nbsp;&nbsp;
        	<font  style="vertical-align: super; font-size:16px ;"><bean:write name="EHRSection_PatientTileFB" property="patCrNo" /></font>
        	</td>
        	<td valign="top">
					<div align="left">						
							<logic:equal name="EHRSection_PatientTileFB" property="patGender" value="Male">
							<img src="/HISClinical/hisglobal/images/Patient-Male-icon.png" />
							</logic:equal>							
							<logic:equal name="EHRSection_PatientTileFB" property="patGender" value="Female">
							<img src="/HISClinical/hisglobal/images/Patient-Female-icon.png" />
							</logic:equal>
					</div>
				</td>
			</tr>
			<tr>
			<td style="text-align:center; padding-bottom:4px;" >
			<font  style="vertical-align: super; font-size:14px ;">
			<b>	Father/Spouse Name :</b>
			 <logic:equal name="EHRSection_PatientTileFB" property="patFatherName" value="">
			 <bean:write name="EHRSection_PatientTileFB" property="patSpouseName" />
			 </logic:equal>
			<bean:write name="EHRSection_PatientTileFB" property="patFatherName" />&nbsp; 
			<b>	Address :</b>
				<bean:write name="EHRSection_PatientTileFB" property="patCompleteAddressForDischargeSummary" />	
				</font>		   
			</td>
			</tr>
		</table>
		

</his:statusInProcessWithJsp></html>
