<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="registration.config.RegistrationConfig,vo.registration.PatientVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<his:statusInProcessWithJsp>



	<his:SubTitleTag>
	<his:name>
	 <bean:message key="patient"/>&nbsp;<bean:message key="global.details"/>
	 </his:name>
	 <b style="font-size: 11px">
		<bean:message key="registration" />
		<bean:message key="date" />
		<bean:write name="patientDetailFB" property="registerDate" /></b>
	</his:SubTitleTag>


	<his:ContentTag>
		<table width="100%">
			<tr>
				<td class="tdfonthead" width="25%">
				<div align="right"><b><font
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="name" /></font></b></div>
				</td>
				<td width="25%" class="tdfont">
				<div>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write	name="patientDetailFB" property="patFirstName" />
				<bean:write name="patientDetailFB" property="patMiddleName" />
				<bean:write name="patientDetailFB" property="patLastName" />
				</font>
				</div>
				
				</td>
				
				<td width="25%" class="tdfonthead">
				<div align="right">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo" />
				</font></b>
				</div>
				</td>
				
				
			<td class="tdfont">
				<div align="left">
				<bean:write name="patientDetailFB" property="patCrNo" />
						
				
				</div>
				</td>
			
			</tr>
			
			<tr>
				
				<td width="25%" class="tdfonthead">
				<div align="right">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="age" />
				<bean:message key="slash" />
				<bean:message key="gender" />
				</font></b></div>
				</td>
				
				<td width="25%" nowrap class="tdfont"><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientDetailFB" property="patAge" /> <bean:write
					name="patientDetailFB" property="patAgeUnit" /><logic:notEqual
					name="patientDetailFB" property="patGender" value="">/</logic:notEqual>
				<bean:write name="patientDetailFB" property="patGender" /></font></td>
				
				<td width="25%" class="tdfonthead">
				<div align="right"><b><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="primCat" /> </font></b></div>
				</td>
				
				<td width="25%" class="tdfont" nowrap><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientDetailFB" property="patPrimaryCat" /> </font></td>
				
			</tr>
			<tr>
			
				<td class="tdfonthead" width="25%" nowrap>
				<div align="right"><b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fatherName" /></font></b></div>
				
				<td width="25%" class="tdfont">
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientDetailFB" property="patGuardianName" />
				</font>
				
				</td>
				
				<td class="tdfonthead" width="25%" nowrap>
				<div align="right"><b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="husbandName" /></font></b></div>
				
				<td width="25%" class="tdfont">
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientDetailFB" property="patHusbandName" />
				</font>
				
				</td>
			</tr>
				<!-- <td width="25%" nowrap class="tdfont">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    <bean:write name="patientDetailFB" property="patCat" />
			    </font>
			    </b>
			    </td>-->
			  <%PatientVO patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO); 
			  if((patVO.getMlcNo()!=null && !patVO.getMlcNo().equals("")) || (patVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD )))
			  {%>
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
			
			<logic:equal name="patientDetailFB" property="patIsDead" value="<%=RegistrationConfig.PATIENT_IS_DEAD %>">
			<td class="tdfonthead" width="25%" nowrap></td>
			<td width="25%" class="tdfont"><div align="left">
				<b><font color="#FF0000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					 <bean:message	key="patIsDead" />
				     </font></b>
				     </div>
				</td>
			</logic:equal>
			<logic:notEqual name="patientDetailFB" property="patIsDead" value="<%=RegistrationConfig.PATIENT_IS_DEAD %>">
				<td class="tdfonthead" width="25%" nowrap></td>
				<td width="25%" class="tdfont"></td>
			</logic:notEqual>
			</tr>
			<%} %>
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
