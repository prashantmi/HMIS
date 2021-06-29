<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="registration.RegistrationConfig,hisglobal.vo.PatientVO"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:statusInProcessWithJsp>



	<his:SubTitleTag>
	<his:name>
	 <bean:message key="patient"/><bean:message key="detail"/>
	 </his:name>
	<%--  <b>
		<bean:message key="registration" />
		<bean:message key="date" />
		<bean:write name="patientDetailFB" property="registerDate" /></b> --%>
	</his:SubTitleTag>


	<his:ContentTag>
		<table width="100%">
			<tr>
				<td class="tdfonthead" width="25%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="name" /></font></div>
				</td>
				<td width="25%" class="tdfont">
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write	name="patientDetailFB" property="patFirstName" />
				<bean:write name="patientDetailFB" property="patMiddleName" />
				<bean:write name="patientDetailFB" property="patLastName" />
				</font>
				</b>
				</td>
				
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo" />
				</font>
				</div>
				</td>
				
				
			<td class="tdfont">
				<div align="left">
				<b><bean:write name="patientDetailFB" property="patCrNo" />
						
				</b>
				</div>
				</td>
			
			</tr>
			
			<tr>
				
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="age" />
				<bean:message key="slash" />
				<bean:message key="gender" />
				</font></div>
				</td>
				
				<td width="25%" nowrap class="tdfont"><b><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientDetailFB" property="patAge" /> <bean:write
					name="patientDetailFB" property="patAgeUnit" /><logic:notEqual
					name="patientDetailFB" property="patGender" value="">/</logic:notEqual>
				<bean:write name="patientDetailFB" property="patGender" /></font></b></td>
				
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="primCat" /> </font></div>
				</td>
				
				<td width="25%" class="tdfont" nowrap><b><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientDetailFB" property="patPrimaryCat" /> </font></b></td>
				
			</tr>
			<tr>
			
				<td class="tdfonthead" width="25%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fatherName" />
				
				<td width="25%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientDetailFB" property="patGuardianName" />
				</font>
				</b>
				</td>
				
				<td class="tdfonthead" width="25%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="husbandName" />
				
				<td width="25%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientDetailFB" property="patHusbandName" />
				</font>
				</b>
				</td>
			</tr>
				<!-- <td width="25%" nowrap class="tdfont">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    <bean:write name="patientDetailFB" property="patCat" />
			    </font>
			    </b>
			    </td>-->
			  <%PatientDetailVO ptaientDetailVO;
			  ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			  if((ptaientDetailVO ==null) || (ptaientDetailVO.getMlcNo()==null))
			  {
				  PatientVO patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO); 
				  
				  /* Changed by Chetan Sharma*/
				//  if((patVO.getMlcNo()!=null && !patVO.getMlcNo().equals("")) || (patVO.getIsDead().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD )))
					  if((patVO.getMlcNo()!=null && patVO.getMlcNo().equals("")) || (patVO.getIsDead().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD )))
				  {
			  %>
			<tr>
			  <logic:notEqual name="patientDetailFB" property="mlcNo" value="">
				<td class="tdfonthead" width="25%" nowrap>
				<div align="right">
				<font color="FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 <bean:message	key="mlcNo" />
				     </font>
				     </div>
				</td>
				
				<td width="25%" class="tdfont"><b><font color="#FF0000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write	name="patientDetailFB" property="mlcNo" />
					</font>
					</b>
				</td>
			</logic:notEqual>
			<logic:equal name="patientDetailFB" property="mlcNo" value="">
			<td class="tdfonthead" width="25%" nowrap></td>
			<td width="25%" class="tdfont"></td>
			</logic:equal>
			
			<logic:equal name="patientDetailFB" property="isDead" value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD %>">
			<td class="tdfonthead" width="25%" nowrap></td>
			<td width="25%" class="tdfont"><div align="left">
				<b><font color="#FF0000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					 <bean:message	key="patIsDead" />
				     </font></b>
				     </div>
				</td>
			</logic:equal>
			<logic:notEqual name="patientDetailFB" property="isDead" value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD %>">
				<td class="tdfonthead" width="25%" nowrap></td>
				<td width="25%" class="tdfont"></td>
			</logic:notEqual>
			</tr>
			<%} }%>
			<!--<tr>
			 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2"	face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="registration" />
					<bean:message key="date" />
					<bean:message key="and" />
    				<bean:message key="time" />
			</td>
			<td colspan="3" class="tdfont">
			<b><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientDetailFB" property="registerDate" /></font></b>
			</td>
			</tr>-->
			
		</table>

 


	</his:ContentTag>
</his:statusInProcessWithJsp>
