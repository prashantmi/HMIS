<!-- 
  Developer : Mukund
  Date		: June-2016 
-->

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page%>



<html>	
<head>
<style>
@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		#nonprintableDiv4 
		{
		 display: none; 
		}
		#noPrint
		{display: none;}
}
</style>
<script>
function printPage() 
{
//var frameElement = parent.document.getElementById("f2");
//alert("frameElement :"+frameElement);
//var win = frameElement.contentWindow ;
window.print();
}
function submitPage(mode)
{
	
	//document.forms[0].hmode.value=mode;
	//document.forms[0].submit();
	var daddy = window.self;
	daddy.opener = window.self;
	daddy.close();
}
</script>
</head>
	<body>
	
	<s:form action="PatientDeathDetail">
		<table  width="80%" align="center">
			<tr>
				<td width="100%">
					<div id="noPrint" align="right"> 
						
						<img class="button" src="../../../HIS/hisglobal/images/buttons/btn-pnt.png"   style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
						<img class="button" src="../../../HIS/hisglobal/images/buttons/btn-ccl.png"   style="cursor: pointer" onclick="submitPage('ALLSUMMARY')" onkeypress="if(event.keyCode==13) submitPage('ALLSUMMARY')">
					</div>
				</td>
			</tr>		
				<br/>
				<br/>
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
									(Not to be used for still births)
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
							
								I hereby certify that the person whose particulars are given below died in the Hospital in Ward 
								No. 
								<b>&nbsp;<s:property value="%{wardName}"/></b>&nbsp;on
								<b>&nbsp;<s:property value="%{deathDate}"/></b>&nbsp;at
								<b>&nbsp;<s:property value="%{deathTime}"/></b>
							
							<%-- <s:if test="PatientDeathDetailSUP.getIsInpatient()==RegistrationConfig.NO">
								I hereby certify that the person
								<b>&nbsp; <s:property value="PatientDeathDetailSUP.patName"/></b>
								<b>&nbsp;<s:property value="PatientDeathDetailSUP.husbandName"/></b>
								resident of <b>&nbsp;<s:property value="PatientDeathDetailSUP.deceasedAddress"/></b>
								was under my treatment from <b><s:property value="PatientDeathDetailSUP.onSetDate"/></b>
								to <b><s:property value="PatientDeathDetailSUP.deathDate"/></b>
								and died on <b>&nbsp;<s:property value="PatientDeathDetailSUP.deathDate"/></b> 
								at<b>&nbsp;<s:property value="PatientDeathDetailSUP.deathTime"/></b> 
							</s:if> --%>
							
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
										CR No.<b>&nbsp;<s:property value="%{patCrNo}"/></b>
										DOA <b>&nbsp;<s:property value="%{patAdmDate}"/></b>
										Admission No. <b>&nbsp;<s:property value="%{patAdmNo}"/></b>
										Name of Deceased<b>&nbsp;<s:property value="%{patName}"/></b>
										<b>&nbsp;<s:property value="husbandName"/></b>
										Address:<b>&nbsp;<s:property value="%{deceasedAddress}"/></b>
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
											&nbsp;<s:property value="patGender"/>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
										
											<s:property value="patAge"/>
										
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
										<s:if test="patAgeUnits=='Mth'">
											<s:property value="patAge"/>
										</s:if>
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<!--<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
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
									</font>-->
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<!--<b>
											<s:if test="patAgeUnits=='Hr'">
												<s:property value="%{patAge}"/>
											</s:if>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Hr">
												&nbsp;
											</logic:notEqual>
											<s:if test="patAgeUnits=='Min'">
												<s:property value="%{patAge}"/>
											</s:if>
											<logic:notEqual name="PatientDeathDetailFB" property="patAgeUnits" value="Min">
												&nbsp;
											</logic:notEqual>
										</b>-->
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
											Interval between onset and death approx.
											<br>
											<br>
											<b><s:property value="%{daysInterval}"/></b>&nbsp; Days
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
										 &nbsp;(a)&nbsp;<s:property value="%{immediateCause1}"/>
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
										 &nbsp;(b)&nbsp;<s:property value="%{immediateCause2}"/>
										  <br><br>
										 due to(or as a consequences of) &nbsp;<s:property value="%{immediateCause3}"/>
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
										 &nbsp;(c)&nbsp;<s:property value="%{otherCause}"/>
										 
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
										&nbsp;<s:property value="%{deathMannerName}"/>
									</font>
								</div>
							</td>
							<td width="20%" colspan="4" valign="top">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										
										
											<%-- &nbsp;Injury Type: <b><s:property value="%{injuryTypeName}"/></b> --%>
											 &nbsp;Nature of Injury: <b> <s:property value="%{injuryNatureName}"/></b>
											 <br>
											 &nbsp;&nbsp;&nbsp;<s:property value="%{injuryRemarks}"/>
									</font>
								</div>
							</td>
						</tr>
						<%-- <s:if test="PatientDeathDetailSUP.getPatGender()=='Female'" >
							<tr>
								<td width="20%" colspan="2">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<b>Is Pregnant:</b>&nbsp;<s:property value= "PatientDeathDetailSUP.isPregnant" />
										</font>
									</div>
								</td>
								<td width="20%" colspan="4" valign="top">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;<b>Is Delivery:</b>&nbsp;<s:property value="%{#isDelivery}"/>
										</font>
									</div>
								</td>
							</tr>
						</s:if> --%>
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
								Date of Verification: <u><s:property value="%{verificationDate}"/> <s:property value="%{verificationTime}"/></u>
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
							
								I hereby certify that<b>&nbsp;<s:property value="%{patName}"/></b>
								&nbsp;<b>&nbsp;<s:property value="%{husbandName}"/></b>
								CR No.<b>&nbsp;<s:property value="%{patCrNo}"/></b>
								&nbsp;Admission No.<b>&nbsp;<s:property value="%{patAdmNo}"/></b>
								R/O <b>&nbsp;<s:property value="%{deceasedAddress}"/></b>
								was admitted to this hospital in ward  <b>&nbsp;<s:property value="%{wardName}"/></b>
								&nbsp; on <b>&nbsp;<s:property value="%{patAdmDate}"/></b>
								&nbsp; and expired on <b>&nbsp;<s:property value="%{deathDate}"/></b>
								at  <b>&nbsp;<s:property value="%{deathTime}"/></b>
														
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
		<s:hidden name="patCrNo" value="%{patCrNo}" id="patCrNo"/>
		<s:hidden name="noOfCopies" value="%{noOfCopies}" id="noOfCopies"></s:hidden>
		<s:hidden name="isInpatient" value="%{isInpatient}" id="isInpatient"></s:hidden>
		
</s:form>		
	</body>
</html>
