<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="registration.RegistrationConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<script type="text/javascript">

function printPage(){
	window.print();
}
function cancelPage()
{
	window.close();
	}
</script>


<html:form action="/patientDeathDetail">
	<body>
		<table width="80%" align="center">
			<tr>
				<td width="100%">
					<div id="noPrint" align="right"> 
						<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('ALLSUMMARY')" onkeypress="if(event.keyCode==13) submitPage('ALLSUMMARY')">
					</div>
				</td>
			</tr>		
				<br>
				<br>
			<tr>	
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								FORM NO.4
							</b>
						</font>
					</div>
				</td>
			</tr>	
			<tr>
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								(See Rule 7)
								
							</b>
						</font>
					</div>
				</td>
			</tr>	
			<tr>
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								MEDICAL CERTIFICATE OF CAUSE OF DEATH
							</b>
						</font>
					</div>
				</td>	
			</tr>
			<tr>
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">
									(Hospital in-patients. Not to be used for still births)
								</logic:equal>
								<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
										(For non-institutional deaths. Not to be used for still births)
								</logic:equal>
							</b>
						</font>
					</div>
				</td>	
			</tr>
			<tr>
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							To be sent to Registrar along with Form No 2 (Death Report)
						</font>
					</div>
				</td>	
			</tr>
			<tr>
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;
						</font>
					</div>
				</td>	
			</tr>
				
			<tr>	
				<td width="100%">	
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">
								I hereby certify that the person whose particulars are given below died in the Hospital in Ward 
								No. 
								<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="wardName"/></b>
								&nbsp;on<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathDate"/></b>
								at  <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathTime"/></b>
							</logic:equal>
							<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
								I hereby certify that the person<b>&nbsp; <bean:write name="PatientDeathDetailFB" property="patName"/></b>
								<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="husbandName"/></b>
								resident of <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deceasedAddress"/></b>
								was under my treatment from <b><bean:write name="PatientDeathDetailFB" property="onSetDate"/></b>
								to <b><bean:write name="PatientDeathDetailFB" property="deathDate"/></b>
								and died on <b><bean:write name="PatientDeathDetailFB" property="deathDate"/></b> 
								at<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathTime"/></b>
							</logic:equal>	
						</font>
					</div>
				</td>	
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="1" cellpadding="0" cellspacing="0" >
						<tr>
							<td width="20%" colspan="5">	
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">
											CR No.<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patCrNo"/></b>
											DOA <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patAdmDate"/></b>
											Admission No. <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patAdmNo"/></b>
											Name of Deceased<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patName"/></b>
											<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="husbandName"/></b>
											Address:<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deceasedAddress"/></b>
										</logic:equal>
										<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
											CR No.:<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patCrNo"/></b>
											Name of Deceased:<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patName"/></b>
										</logic:equal>
									</font>
								</div>
							</td>	
							<td width="20%" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										For use of Statistical office
									</font>
								</div>	
							</td>
						</tr>
					
						<tr>
							<td width="20%" rowspan="2">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Sex
									</font>
								</div>
							</td>
							<td width="20%" colspan="4">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Age at Death
									</font>
								</div>
							</td>
							<td width="20%" rowspan="7">
								&nbsp;
							</td>
						</tr>
						<tr>
							
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										If 1 year or more, Age in years
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										If less than 1 year, Age in months
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										If less than 1 month, Age in days
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										If less than 1 day, Age in hours
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;<bean:write name="PatientDeathDetailFB" property="patGender"/>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<logic:equal name="PatientDeathDetailFB" property="patAgeUnits" value="Yr">
												<bean:write name="PatientDeathDetailFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Yr">
												&nbsp;
											</logic:notEqual>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<logic:equal name="PatientDeathDetailFB" property="patAgeUnits" value="Mth">
												<bean:write name="PatientDeathDetailFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Mth">
												&nbsp;
											</logic:notEqual>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<logic:equal name="PatientDeathDetailFB" property="patAgeUnits" value="Wk">
												<bean:write name="PatientDeathDetailFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Wk">
												&nbsp;
											</logic:notEqual>
											<logic:equal name="PatientDeathDetailFB" property="patAgeUnits" value="D">
												<bean:write name="PatientDeathDetailFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="D">
												&nbsp;
											</logic:notEqual>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<logic:equal name="PatientDeathDetailFB" property="patAgeUnits" value="Hr">
												<bean:write name="PatientDeathDetailFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Hr">
												&nbsp;
											</logic:notEqual>
											<logic:equal name="PatientDeathDetailFB" property="patAgeUnits" value="Min">
												<bean:write name="PatientDeathDetailFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Min">
												&nbsp;
											</logic:notEqual>
										</b>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" colspan="4">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											CAUSE OF DEATH
										</b>
									</font>
								</div>
							</td>	
							<td width="20%" rowspan="4" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											Interval between onset & death approx.
											<br>
											<br>
											<logic:notEmpty name="PatientDeathDetailFB" property="daysInterval">
												<b><bean:write name="PatientDeathDetailFB" property="daysInterval"/></b>&nbsp; Days
											</logic:notEmpty>
											
									</font>	
								</div>
							</td>	
						</tr>
						<tr>
							<td width="20%" colspan="2">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;I
											<br>
											&nbsp;&nbsp;Immediate Cause
										</b>	
										
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;State the disease, injury or 
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;complication which caused 
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;death,not the mode of dying 
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;such as heart failure, asthenia,
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;etc.
										
										
									</font>
								</div>
							</td>
							<td width="20%" colspan="2" valign="top">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<br>
										 &nbsp;(a)&nbsp;<bean:write name="PatientDeathDetailFB" property="immediateCause1"/>
										 <br><br>
										 due to(or as a consequences of)
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" colspan="2">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;&nbsp;Antecedent Cause
										</b>	
										
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;Morbid conditions,if any,giving 
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;rise to the above cause, stating
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;underlying conditions last 
											
										
										
									</font>
								</div>
							</td>
							<td width="20%" colspan="2" valign="top">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<br>
										 &nbsp;(b)&nbsp;<bean:write name="PatientDeathDetailFB" property="immediateCause2"/>
										  <br><br>
										 due to(or as a consequences of) &nbsp;<bean:write name="PatientDeathDetailFB" property="immediateCause3"/>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" colspan="2">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;II
										</b>	
										
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;Other significant conditions
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;contributing to the death but
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;not related to the disease or 
											<br>
											&nbsp;&nbsp;&nbsp;&nbsp;conditions causing it.
											
										
										
									</font>
								</div>
							</td>
							<td width="20%" colspan="2" valign="top">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<br>
										 &nbsp;(c)&nbsp;<bean:write name="PatientDeathDetailFB" property="otherCause"/>
										 
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" colspan="2">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;Manner of Death
										</b>	
										
									</font>
								</div>
							</td>
							<td width="20%" colspan="4" valign="top">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>How did the injury occur?</b>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" colspan="2">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<bean:write name="PatientDeathDetailFB" property="deathMannerName"/>
									</font>
								</div>
							</td>
							<td width="20%" colspan="4" valign="top">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<logic:present name="PatientDeathDetailFB" property="injuryNatureCode">
										<logic:notEqual name="PatientDeathDetailFB" property="injuryNatureCode" value="-1">
											&nbsp;Injury Type: <b><bean:write name="PatientDeathDetailFB" property="injuryTypeName"/></b>
											 &nbsp;Nature of Injury: <b> <bean:write name="PatientDeathDetailFB" property="injuryNatureName"/></b>
											 <br>
											 &nbsp;&nbsp;&nbsp;<bean:write name="PatientDeathDetailFB" property="injuryRemarks"/>
										 </logic:notEqual>
										 <logic:equal name="PatientDeathDetailFB" property="injuryNatureCode" value="-1">
										 &nbsp;&nbsp;-
										 </logic:equal>
										 </logic:present>
									</font>
								</div>
							</td>
						</tr>
						<logic:equal name="PatientDeathDetailFB" property="patGender" value="Female">
							<tr>
								<td width="20%" colspan="2">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<b>Is Pregnant:</b>&nbsp;<bean:write name="PatientDeathDetailFB" property="isPregnant"/>
										</font>
									</div>
								</td>
								<td width="20%" colspan="4" valign="top">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<b>Is Delivery:</b>&nbsp;<bean:write name="PatientDeathDetailFB" property="isDelivery"/>
										</font>
									</div>
								</td>
							</tr>
						</logic:equal>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
					&nbsp;	
				</td>
			</tr>
			<tr>
				<td width="100%">
					&nbsp;	
				</td>
			</tr>
			<tr>
				<td width="100%">	
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								Name and signature of Medical Attendant certifying the cause of death
								<br><br>
								Date of Verification: <u><bean:write name="PatientDeathDetailFB" property="verificationDate"/> <bean:write name="PatientDeathDetailFB" property="verificationTime"/></u>
							</b>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%">
					&nbsp;	
				</td>
			</tr>
			<tr>
				<td width="100%">	
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								Note: Send this form within 24 hours from the time of death to Biostatistics Department.
								<br>
								-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
								
							</b>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								(To be detached and handed over to the relative of the deceased)
						</font>
					</div>
				</td>
			</tr>
			<tr>	
				<td width="100%">	
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">	
								I hereby certify that<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patName"/></b>
								&nbsp;<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="husbandName"/></b>
								CR No.<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patCrNo"/></b>
								&nbsp;Admission No.<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patAdmNo"/></b>
								R/O <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deceasedAddress"/></b>
								was admitted to this hospital in ward  <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="wardName"/></b>
								&nbsp; on <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patAdmDate"/></b>
								&nbsp; and expired on <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathDate"/></b>
								at  <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathTime"/></b>
							</logic:equal>	
							<logic:equal name="PatientDeathDetailFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
								I hereby certify that the person<b>&nbsp; <bean:write name="PatientDeathDetailFB" property="patName"/></b>
								<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="husbandName"/></b>
								CR No.<b>&nbsp;<bean:write name="PatientDeathDetailFB" property="patCrNo"/></b>
								resident of <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deceasedAddress"/></b>
								was under my treatment from <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="onSetDate"/></b>
								to <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathDate"/></b>
								and died on <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathDate"/></b>
								at  <b>&nbsp;<bean:write name="PatientDeathDetailFB" property="deathTime"/></b>
							</logic:equal>
						</font>
					</div>
				</td>	
			</tr>
			<tr>
				<td width="100%">	
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								Name of Doctor________________________________
								<br><br>
								Designation___________________________________
								<br><br>
								Department___________________________________
							</b>
						</font>
					</div>
				</td>
			</tr>
		</table>
		 <html:hidden name="PatientDeathDetailFB" property="hmode"/>
		 <html:hidden name="PatientDeathDetailFB" property="patCrNo"/>
		  <html:hidden name="PatientDeathDetailFB" property="noOfCopies"/>
		  <html:hidden name="PatientDeathDetailFB" property="isInpatient"/>
		
	</body>
</html:form>	