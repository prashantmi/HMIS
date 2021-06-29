
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<his:ContentTag>
			<his:SubTitleTag name="Family Doctor Details Entry"> 
					</his:SubTitleTag>
					
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="doctor"/>
											<bean:message key="name"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="doctorName" maxlength="60" onkeypress="return validateAlphaOnly(this,event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="physician"/>
											<bean:message key="type"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="PatientFamilyDoctorDtlFB" property="physicianType">
											<html:option value="-1">Select Value</html:option>	
											<logic:present name="<%=MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST%>">									
												<html:options collection="<%=MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST %>" property="value" labelProperty="label"/>
											</logic:present>
										</html:select>	
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="qualification"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="qualification" maxlength="50" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="doctorId"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="doctorId" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="regNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="docRegNo" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
									</div>
								</td>
							
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="clinic/hos"/>
											<bean:message key="name"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="clinicHosName" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="address1"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="address1" maxlength="50" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="address2"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="address2" maxlength="50" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="city"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="city" maxlength="50" onkeypress="return validateAlphaOnly(this,event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="phone"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="phNo" maxlength="20" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="mobileNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="mobileNo" maxlength="20" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="email"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="email" maxlength="50"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="faxNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="faxNo" maxlength="20" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="patientId"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="patientId" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="speciality"/>/
											<bean:message key="consultationFor"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="PatientFamilyDoctorDtlFB" property="consultationFor" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))"></html:textarea>
									</div>
								</td>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="remarks"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="PatientFamilyDoctorDtlFB" property="remarks" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))"></html:textarea>
									</div>
								</td>
							</tr>
						</table>		
</his:ContentTag>
</body>
</html>