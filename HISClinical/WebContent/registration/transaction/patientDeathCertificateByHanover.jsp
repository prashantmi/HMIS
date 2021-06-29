<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="registration.RegistrationConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="cancelPage()" onkeypress="if(event.keyCode==13) cancelPage()">
						<%-- <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('ALLSUMMARY')" onkeypress="if(event.keyCode==13) submitPage('ALLSUMMARY')"> --%>
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
								<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">
									(Hospital in-patients. Not to be used for still births)
								</logic:equal>
								<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
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
							<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">	
								I hereby certify that the person whose particulars are given below died in the Hospital in Ward 
								No. 
								<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="wardName"/></b>
								&nbsp;on<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathDate"/></b>
								at  <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathTime"/></b>
							</logic:equal>	
							<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
								I hereby certify that the person<b>&nbsp; <bean:write name="HandoverToDeadBodyFB" property="patName"/></b>
								<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="husbandName"/></b>
								resident of <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deceasedAddress"/></b>
								was under my treatment from <b><bean:write name="HandoverToDeadBodyFB" property="onSetDate"/></b>
								to <b><bean:write name="HandoverToDeadBodyFB" property="deathDate"/></b>
								and died on <b><bean:write name="HandoverToDeadBodyFB" property="deathDate"/></b> 
								at  <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathTime"/></b>
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
										<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">
											CR No.<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patCrNo"/></b>
											DOA <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patAdmDate"/></b>
											Admission No. <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patAdmNo"/></b>
											Name of Deceased<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patName"/></b>
											<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="husbandName"/></b>
											Address<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deceasedAddress"/></b>
										</logic:equal>
										<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
											&nbsp;
										</logic:equal>
									</font>
								</div>
							</td>	
							<td width="20%" rowspan="2" valign="top">
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
							<td width="20%" rowspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patGender"/>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<logic:equal name="HandoverToDeadBodyFB" property="patAgeUnits" value="Yr">
												<bean:write name="HandoverToDeadBodyFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="HandoverToDeadBodyFB" property="patAgeUnits" value="Yr">
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
											<logic:equal name="HandoverToDeadBodyFB" property="patAgeUnits" value="Mth">
												<bean:write name="HandoverToDeadBodyFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="HandoverToDeadBodyFB" property="patAgeUnits" value="Mth">
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
											<logic:equal name="HandoverToDeadBodyFB" property="patAgeUnits" value="Wk">
												<bean:write name="HandoverToDeadBodyFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="HandoverToDeadBodyFB" property="patAgeUnits" value="Wk">
												&nbsp;
											</logic:notEqual>
											<logic:equal name="HandoverToDeadBodyFB" property="patAgeUnits" value="D">
												<bean:write name="HandoverToDeadBodyFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="HandoverToDeadBodyFB" property="patAgeUnits" value="D">
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
											<logic:equal name="HandoverToDeadBodyFB" property="patAgeUnits" value="Hr">
												<bean:write name="HandoverToDeadBodyFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="HandoverToDeadBodyFB" property="patAgeUnits" value="Hr">
												&nbsp;
											</logic:notEqual>
											<logic:equal name="HandoverToDeadBodyFB" property="patAgeUnits" value="Min">
												<bean:write name="HandoverToDeadBodyFB" property="patAge"/>
											</logic:equal>
											<logic:notEqual name="HandoverToDeadBodyFB" property="patAgeUnits" value="Min">
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
											<logic:notEmpty name="HandoverToDeadBodyFB" property="daysInterval">
												<b><bean:write name="HandoverToDeadBodyFB" property="daysInterval"/></b>&nbsp; Days
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
										 &nbsp;(a)&nbsp;<bean:write name="HandoverToDeadBodyFB" property="immediateCause1"/>
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
										 &nbsp;(b)&nbsp;<bean:write name="HandoverToDeadBodyFB" property="immediateCause2"/>
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
										 &nbsp;(c)&nbsp;<bean:write name="HandoverToDeadBodyFB" property="otherCause"/>
										 
									</font>
								</div>
							</td>
						</tr>
						<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">
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
											How did the injury occur?
											 
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="20%" colspan="2">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathMannerName"/>
										</font>
									</div>
								</td>
								<td width="20%" colspan="4" valign="top">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:notEqual name="HandoverToDeadBodyFB" property="injuryNatureCode" value="-1">
												&nbsp;Injury Type: <b><bean:write name="HandoverToDeadBodyFB" property="injuryTypeName"/></b>
												 &nbsp;Nature of Injury: <b> <bean:write name="HandoverToDeadBodyFB" property="injuryNatureName"/></b>
												 <br>
												 &nbsp;&nbsp;&nbsp;<bean:write name="HandoverToDeadBodyFB" property="injuryRemarks"/>
											 </logic:notEqual>
											 <logic:equal name="HandoverToDeadBodyFB" property="injuryNatureCode" value="-1">
											 &nbsp;&nbsp;-
											 </logic:equal>
											 
										</font>
									</div>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="HandoverToDeadBodyFB" property="patGender" value="Female">
							<tr>
								<td width="20%" colspan="2">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;Is Pregnant :&nbsp;<bean:write name="HandoverToDeadBodyFB" property="isPregnant"/>
										</font>
									</div>
								</td>
								<td width="20%" colspan="3" valign="top">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;Is Delivery :&nbsp;<bean:write name="HandoverToDeadBodyFB" property="isDelivery"/>
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
								Date of Verification__________________________________________
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
								Note: Send this form within 24hoursfrom the time of death to Biostatistics Department.
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
							<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.YES %>">	
								Certify that<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patName"/></b>
								&nbsp;<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="husbandName"/></b>
								CR No.<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patCrNo"/></b>
								&nbsp;Admission No.<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patAdmNo"/></b>
								R/O <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deceasedAddress"/></b>
								was admitted to this hospital in ward  <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="wardName"/></b>
								&nbsp; on <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="patAdmDate"/></b>
								&nbsp; and expired on <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathDate"/></b>
							</logic:equal>	
							<logic:equal name="HandoverToDeadBodyFB" property="isInpatient" value="<%=RegistrationConfig.NO %>">
								I hereby certify that the person<b>&nbsp; <bean:write name="HandoverToDeadBodyFB" property="patName"/></b>
								<b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="husbandName"/></b>
								resident of <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deceasedAddress"/></b>
								was under my treatment from <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="onSetDate"/></b>
								to <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathDate"/></b>
								and died on <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathDate"/></b>
								at  <b>&nbsp;<bean:write name="HandoverToDeadBodyFB" property="deathTime"/></b>
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
		 <html:hidden name="HandoverToDeadBodyFB" property="hmode"/>
		 <html:hidden name="HandoverToDeadBodyFB" property="patCrNo"/>
		  <html:hidden name="HandoverToDeadBodyFB" property="noOfCopies"/>
		  <html:hidden name="HandoverToDeadBodyFB" property="isInpatient"/>
		
	</body>
</html:form>	