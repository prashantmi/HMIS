<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:statusInProcessWithJsp>

	<his:SubTitleTag>
	<his:name>
		<bean:message key="deceased"/>
		<bean:message key="detail"/>
	 </his:name>
	</his:SubTitleTag>
	
	
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
							<bean:write	name="DeceasedTileFB" property="patFirstName" />
							<bean:write	name="DeceasedTileFB" property="patMiddleName" />
							<bean:write	name="DeceasedTileFB" property="patLastName" />
						</font>
					</b>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="deceasedNo" />
						</font>
					</div>
				</td>
				<td class="tdfont">
					<div align="left">
						<b>
							<bean:write name="DeceasedTileFB" property="deceasedNo" />
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
							<bean:write name="DeceasedTileFB" property="patAge" />
							<logic:notEqual	name="DeceasedTileFB" property="patGender" value="">/</logic:notEqual>
							<bean:write name="DeceasedTileFB" property="patGender" />
						</font>
					</b>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="fatherName" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" nowrap>
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="DeceasedTileFB" property="patGuardianName" />
						</font>
					</b>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="deathdate" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" nowrap>
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="DeceasedTileFB" property="deathDate" />
						</font>
					</b>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="receiveDate" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" nowrap>
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write	name="DeceasedTileFB" property="receivedDateTime" />
						</font>
					</b>
				</td>
			</tr>
			<logic:notEmpty name="DeceasedTileFB" property="caseNo">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="caseNo" />
						</font>
					</div>
				</td>
				<td width="25%" nowrap class="tdfont">
					<b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="DeceasedTileFB" property="caseNo" />
						</font>
					</b>
				</td>
				<logic:notEmpty name="DeceasedTileFB" property="mlcNo">
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcNo" />
							</font>
						</div>
					</td>	
					<td width="25%" nowrap class="tdfont">
						<b>
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="DeceasedTileFB" property="mlcNo" />
							</font>
						</b>
					</td>
				</logic:notEmpty>
				
				<logic:empty name="DeceasedTileFB" property="mlcNo">
					<td width="25%" class="tdfonthead"></td>
					<td width="25%" class="tdfont"></td>
				</logic:empty>
			</tr>
			</logic:notEmpty>
		</table>
		
</his:statusInProcessWithJsp>	 