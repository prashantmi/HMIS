<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>    


<his:statusInProcessWithJsp>

	<his:SubTitleTag>
	<his:name>
		<bean:message key="patient"/>
		<bean:message key="detail"/>
	 </his:name>
	 <b>
		<bean:message key="admDate" />
		<bean:write name="PatientDetailFB" property="admDateTime" />
	</b>
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%">
			<tr>
				<td class="tdfonthead" width="25%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="name" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="PatientDetailFB" property="patFirstName" />
							<bean:write	name="PatientDetailFB" property="patMiddleName" />
							<bean:write	name="PatientDetailFB" property="patLastName" />
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
						<b>
							<bean:write name="PatientDetailFB" property="patCrNo" />
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
						</font>
					</div>
				</td>
				<td width="25%" nowrap class="tdfont">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="PatientDetailFB" property="patAge" /> 
							<logic:notEqual	name="PatientDetailFB" property="patGender" value="">/</logic:notEqual>
							<bean:write name="PatientDetailFB" property="patGender" />
						</font>
					</b>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="primCat" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" nowrap>
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="PatientDetailFB" property="patPrimaryCat" />
						</font>
					</b>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="fatherName" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="PatientDetailFB" property="patGuardianName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="husbandName" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="PatientDetailFB" property="patSpouceName" />
						</font>
					</b>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dept/unit" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="PatientDetailFB" property="departmentName" />/
							<bean:write name="PatientDetailFB" property="departmentUnitName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="admNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<bean:write name="PatientDetailFB" property="patAdmNo" />
					</b>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="ward" />/
							<bean:message key="roomNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="PatientDetailFB" property="wardName" />/
							<bean:write name="PatientDetailFB" property="roomCode" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="bedNo" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="PatientDetailFB" property="bedName" />
						</font>
					</b>
				</td>
			</tr>
			
			
			<tr>
				<td class="tdfonthead" width="25%" nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="con" />
							<bean:message key="name" />
						</font>
					</div>
				</td>			
				<td width="25%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="PatientDetailFB" property="consultantName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="25%" nowrap>
					
				</td>	
					
				<td width="25%" class="tdfont">
			
			</tr>
			
		</table>
	</his:ContentTag>

</his:statusInProcessWithJsp>
