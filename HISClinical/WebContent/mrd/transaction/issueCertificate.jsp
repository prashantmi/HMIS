<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatMedicalDtlVO"%>
<%@page import="hisglobal.vo.PatFitnessDtlVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function validateCount()
{
	var count=0;
	if(document.getElementsByName("certificateType")[0].checked==true)
	{
		for (var i=0;i<document.getElementsByName("selectedGenMC").length;i++)
		{
			if(document.getElementsByName("selectedGenMC")[i].checked==true)
			{
				count++;
			}
		}
	}
	else
	{
		if(document.getElementsByName("certificateType")[1].checked==true)
		{
			for (var i=0;i<document.getElementsByName("selectedGenFC").length;i++)
			{
				if(document.getElementsByName("selectedGenFC")[i].checked==true)
				{
					count++;
				}
			}
		}
		else
		{
			if(document.getElementsByName("certificateType")[2].checked==true)
			{
				for (var i=0;i<document.getElementsByName("selectedDupMC").length;i++)
				{
					if(document.getElementsByName("selectedDupMC")[i].checked==true)
					{
						count++;
					}
				}
			}
			else
			{
				for (var i=0;i<document.getElementsByName("selectedDupFC").length;i++)
				{
					if(document.getElementsByName("selectedDupFC")[i].checked==true)
					{
						count++;
					}
				}
			}
		}
	}
	
	if(count>0)
		return true;
	else
		return false;		
}
function validateIssue()
{
	var valid=true;
	if(!validateCount())
	{
		alert("Please Select a Record");
		valid=false;
	}
	else if(document.getElementsByName("remarks")[0].value=="")
	{
		alert("Please Enter the Remarks");
		document.getElementsByName("remarks")[0].focus();
		valid=false;
	}
	return valid;
}

function getMCDetail(obj)
{
	document.getElementById("divMedicalCertificate").style.display="block";
	document.getElementById("commonDiv").style.display="block";
	document.getElementById("divFitnessCertificate").style.display="none";
	document.getElementById("divDupMedicalCertificate").style.display="none";
	document.getElementById("divDupFitnessCertificate").style.display="none";
	
	medicalCertId.innerHTML="<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
							"Medical Certificate No.	</font></div></td>"+
							"<td width='25%' class='tdfont'><div align='left'>&nbsp;"+document.getElementsByName('hiddenMCMedicalCertificateDesc')[obj.value].value+
							"</div></td>"+
							"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
							"Start Day </font></div></td>"+
							"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenMCFromDate")[obj.value].value+
							"</font></div></td></tr>"+
							
							"<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
							"Suffering From	</font></div></td>"+
							"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName('hiddenMCSufferingFrom')[obj.value].value+
							"</font></div></td>"+
							"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
							"End Day </font></div></td>"+
							"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenMCToDate")[obj.value].value+
							"</font></div></td></tr>"+
							
							"<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
							"Advice Days	</font></div></td>"+
							"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName('hiddenMCAdviceDays')[obj.value].value+
							"</font></div></td>"+
							"<td width='25%' class='tdfonthead'><div align='right'></td>"+
							"<td width='25%' class='tdfont'></td></tr>"
							
							
	
	
	document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("hiddenMCSufferingFrom")[obj.value].value;
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("hiddenMCFromDate")[obj.value].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("hiddenMCToDate")[obj.value].value;
	document.getElementsByName("adviceDays")[0].value=document.getElementsByName("hiddenMCAdviceDays")[obj.value].value;
	document.getElementsByName("medicalCertificateDesc")[0].value=document.getElementsByName("hiddenMCMedicalCertificateDesc")[obj.value].value;
	
}

function getFCDetail(obj)
{
	document.getElementById("divMedicalCertificate").style.display="none";
	document.getElementById("divFitnessCertificate").style.display="block";
	document.getElementById("commonDiv").style.display="block";
	document.getElementById("divDupMedicalCertificate").style.display="none";
	document.getElementById("divDupFitnessCertificate").style.display="none";
	
	fitnessCertificateId.innerHTML="<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Fitness Certificate No.	</font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'>&nbsp;"+document.getElementsByName('hiddenFCFitnessCertificateDesc')[obj.value].value+
									"</div></td>"+
									"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Medical Certificate No. </font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenFCMedicalCertificateDesc")[obj.value].value+
									"</font></div></td></tr>"+
									
									"<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Suffering From	</font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName('hiddenFCSufferingFrom')[obj.value].value+
									"</font></div></td>"+
									"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Fitness Date </font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenFCFitnessDate")[obj.value].value+
									"</font></div></td></tr>"
	

	document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("hiddenFCSufferingFrom")[obj.value].value;
	document.getElementsByName("fitnessDate")[0].value=document.getElementsByName("hiddenFCFitnessDate")[obj.value].value;
	document.getElementsByName("fitnessCertificateDesc")[0].value=document.getElementsByName("hiddenFCFitnessCertificateDesc")[obj.value].value;
	document.getElementsByName("medicalCertificateDesc")[0].value=document.getElementsByName("hiddenFCMedicalCertificateDesc")[obj.value].value;

}

function getDuplicateMCDetail(obj)
{
	document.getElementById("divMedicalCertificate").style.display="none";
	document.getElementById("divFitnessCertificate").style.display="none";
	document.getElementById("divDupMedicalCertificate").style.display="block";
	document.getElementById("commonDiv").style.display="block";
	document.getElementById("divDupFitnessCertificate").style.display="none";
	
	dupMedicalCertId.innerHTML="<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
								"Medical Certificate No.	</font></div></td>"+
								"<td width='25%' class='tdfont'><div align='left'>&nbsp;"+document.getElementsByName('hiddenDMCMedicalCertificateDesc')[obj.value].value+
								"</div></td>"+
								"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
								"Start Day </font></div></td>"+
								"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenDMCFromDate")[obj.value].value+
								"</font></div></td></tr>"+
								
								"<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
								"Suffering From	</font></div></td>"+
								"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName('hiddenDMCSufferingFrom')[obj.value].value+
								"</font></div></td>"+
								"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
								"End Day </font></div></td>"+
								"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenDMCToDate")[obj.value].value+
								"</font></div></td></tr>"+
								
								"<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
								"Advice Days	</font></div></td>"+
								"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName('hiddenDMCAdviceDays')[obj.value].value+
								"</font></div></td>"+
								"<td width='25%' class='tdfonthead'><div align='right'></td>"+
								"<td width='25%' class='tdfont'></td></tr>"
								
	
	document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("hiddenDMCSufferingFrom")[obj.value].value;
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("hiddenDMCFromDate")[obj.value].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("hiddenDMCToDate")[obj.value].value;
	document.getElementsByName("adviceDays")[0].value=document.getElementsByName("hiddenDMCAdviceDays")[obj.value].value;
	document.getElementsByName("medicalCertificateDesc")[0].value=document.getElementsByName("hiddenDMCMedicalCertificateDesc")[obj.value].value;
	
}

function getDuplicateFCDetail(obj)
{
	document.getElementById("divMedicalCertificate").style.display="none";
	document.getElementById("divFitnessCertificate").style.display="none";
	document.getElementById("divDupMedicalCertificate").style.display="none";
	document.getElementById("divDupFitnessCertificate").style.display="block";
	document.getElementById("commonDiv").style.display="block";
	
	dupFitnessCertId.innerHTML="<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Fitness Certificate No.	</font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'>&nbsp;"+document.getElementsByName('hiddenDFCFitnessCertificateDesc')[obj.value].value+
									"</div></td>"+
									"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Medical Certificate No. </font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenDFCMedicalCertificateDesc")[obj.value].value+
									"</font></div></td></tr>"+
									
									"<tr><td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Suffering From	</font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName('hiddenDFCSufferingFrom')[obj.value].value+
									"</font></div></td>"+
									"<td width='25%' class='tdfonthead'><div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+
									"Fitness Date </font></div></td>"+
									"<td width='25%' class='tdfont'><div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>&nbsp;"+document.getElementsByName("hiddenDFCFitnessDate")[obj.value].value+
									"</font></div></td></tr>"
	
	document.getElementsByName("sufferingFrom")[0].value=document.getElementsByName("hiddenDFCSufferingFrom")[obj.value].value;
	document.getElementsByName("fitnessDate")[0].value=document.getElementsByName("hiddenDFCFitnessDate")[obj.value].value;
	document.getElementsByName("fitnessCertificateDesc")[0].value=document.getElementsByName("hiddenDFCFitnessCertificateDesc")[obj.value].value;
	document.getElementsByName("medicalCertificateDesc")[0].value=document.getElementsByName("hiddenDFCMedicalCertificateDesc")[obj.value].value;
	
}

function clearValue()
{
	document.getElementsByName("remarks")[0].value="";
}
</script>

	<body>
		<html:form action="/issueCertificate">
		
		<%
			String medicalCertificate="none";
			String fitnessCertificate="none";
			String dupMedicalCertificate="none";
			String dupFitnessCertificate="none";
			String commonDiv="none";
			
		%>
			<his:TransactionContainer>
				<his:TitleTag name="Issue Certificate">
				</his:TitleTag>
				
				<logic:empty name="IssueCertificateFB" property="patCrNo" >
					<his:InputCrNoTag name="IssueCertificateFB"></his:InputCrNoTag>
				</logic:empty>
				<his:statusList>
				<jsp:include page="/registration/patientDetail.cnt" flush="true" />
					<his:SubTitleTag name="Generated Certificate">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="15%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="certificateType"/>
											</B>
										</font>
									</div>
								</td>
								<td width="85%" colspan="3" class="tdfont">
									<div align="left">
										<bean:message key="medicalCertificate"/>
										<html:radio name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE%>" onclick="submitForm('CHANGEMODE')" ></html:radio>
										<bean:message key="fitnessCertificate"/>
										<html:radio name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE%>" onclick="submitForm('CHANGEMODE')" ></html:radio>
										<bean:message key="duplicate"/>
										<bean:message key="medicalCertificate"/>
										<html:radio name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_DUPLICATE_MEDICAL_CERTIFICATE%>" onclick="submitForm('CHANGEMODE')" ></html:radio>
										<bean:message key="duplicate"/>
										<bean:message key="fitnessCertificate"/>
										<html:radio name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_DUPLICATE_FITNESS_CERTIFICATE%>" onclick="submitForm('CHANGEMODE')" ></html:radio>
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
					
						<logic:equal name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE %>">
							<%
							PatMedicalDtlVO[] arrPatMedDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_MC_OF_PATIENT);
								if(arrPatMedDtlVO.length>0){
							 %>
								<his:SubTitleTag name="Generated Medical Certificate">
								</his:SubTitleTag>
							<his:ContentTag>	
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="select"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="medCertificateNo"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="sufferingFrom"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="advice"/>
														<bean:message key="day"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="13%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="startday"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="endday"/>
													</b>	
												</font>
											</div>
										</td>
									</tr>
									<logic:iterate name="<%=MrdConfig.ALL_GENERATED_MC_OF_PATIENT %>" id="patMedDtlVO" type="hisglobal.vo.PatMedicalDtlVO" indexId="mcIndex">
										<tr>
											<td class="tdfont" width="5%" >
												<div align="center">
													<html:radio name="IssueCertificateFB" property="selectedGenMC" value="<%=mcIndex.toString() %>" onclick="getMCDetail(this)"></html:radio>
												</div>
											</td>
											<td class="tdfont" width="20%" >
												<div align="center">
													<bean:write name="patMedDtlVO" property="medicalCertificateDesc"/>
													<html:hidden name="IssueCertificateFB" property="hiddenMCMedicalCertificateDesc" value="<%=patMedDtlVO.getMedicalCertificateDesc() %>"/>
												</div>
											</td>
											<td class="tdfont" width="40%" >
												<div align="center">
													<bean:write name="patMedDtlVO" property="sufferingFrom"/>
													<html:hidden name="IssueCertificateFB" property="hiddenMCSufferingFrom" value="<%=patMedDtlVO.getSufferingFrom() %>"/>
												</div>
											</td>
											<td class="tdfont" width="10%" >
												<div align="center">
													<bean:write name="patMedDtlVO" property="adviceDays"/>
													<html:hidden name="IssueCertificateFB" property="hiddenMCAdviceDays" value="<%=patMedDtlVO.getAdviceDays() %>"/>
												</div>
											</td>
											<td class="tdfont" width="13%" >
												<div align="center">
													<bean:write name="patMedDtlVO" property="fromDate"/>
													<html:hidden name="IssueCertificateFB" property="hiddenMCFromDate" value="<%=patMedDtlVO.getFromDate() %>"/>
												</div>
											</td>
											<td class="tdfont" width="12%" >
												<div align="center">
													<bean:write name="patMedDtlVO" property="toDate"/>
													<html:hidden name="IssueCertificateFB" property="hiddenMCToDate" value="<%=patMedDtlVO.getToDate() %>"/>
												</div>
											</td>	
										</tr>
									</logic:iterate>
								</table>
							</his:ContentTag>	
							<%} %>		
						</logic:equal>
						
						<logic:equal name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE %>">
							<%
							PatFitnessDtlVO[] arrPatFitDtlVO=(PatFitnessDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_FC_OF_PATIENT);
								if(arrPatFitDtlVO.length>0){
							 %>
							 	<his:SubTitleTag name="Generated Fitness Certificate">
								</his:SubTitleTag>
							<his:ContentTag>	
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="select"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="fitnessCertificateNo"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="45%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="sufferingFrom"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="fitnessDate"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="medCertificateNo"/>
													</b>	
												</font>
											</div>
										</td>
										
									</tr>
									<logic:iterate name="<%=MrdConfig.ALL_GENERATED_FC_OF_PATIENT %>" id="patFitDtlVO" type="hisglobal.vo.PatFitnessDtlVO" indexId="fcIndex">
										<tr>
											<td class="tdfont" width="5%" >
												<div align="center">
													<html:radio name="IssueCertificateFB" property="selectedGenFC" value="<%=fcIndex.toString() %>" onclick="getFCDetail(this)"></html:radio>
												</div>
											</td>
											<td class="tdfont" width="20%" >
												<div align="center">
													<bean:write name="patFitDtlVO" property="fitnessCertificateDesc"/>
													<html:hidden name="IssueCertificateFB" property="hiddenFCFitnessCertificateDesc" value="<%=patFitDtlVO.getFitnessCertificateDesc() %>"/>
												</div>
											</td>
											<td class="tdfont" width="45%" >
												<div align="center">
													<bean:write name="patFitDtlVO" property="sufferingFrom"/>
													<html:hidden name="IssueCertificateFB" property="hiddenFCSufferingFrom" value="<%=patFitDtlVO.getSufferingFrom() %>"/>
												</div>
											</td>
											<td class="tdfont" width="10%" >
												<div align="center">
													<bean:write name="patFitDtlVO" property="fitnessDate"/>
													<html:hidden name="IssueCertificateFB" property="hiddenFCFitnessDate" value="<%=patFitDtlVO.getFitnessDate() %>"/>
												</div>
											</td>
											<td class="tdfont" width="20%" >
												<div align="center">
													<bean:write name="patFitDtlVO" property="medicalCertificateDesc"/>
													<html:hidden name="IssueCertificateFB" property="hiddenFCMedicalCertificateDesc" value="<%=patFitDtlVO.getMedicalCertificateDesc() %>"/>
												</div>
											</td>
											
										</tr>
									</logic:iterate>
								</table>
							</his:ContentTag>	
							<%} %>
						</logic:equal>
						
							
						<logic:equal name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_DUPLICATE_MEDICAL_CERTIFICATE %>">
							
							<%PatMedicalDtlVO[] arrPatMedDupDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_DUPLICATE_MC_OF_PATIENT);
								if(arrPatMedDupDtlVO.length>0){
							 %>
								<his:SubTitleTag name="Duplicate Medical Certificate List">
								</his:SubTitleTag>
							<his:ContentTag>	
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="select"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="medCertificateNo"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="sufferingFrom"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="advice"/>
														<bean:message key="day"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="13%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="startday"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="endday"/>
													</b>	
												</font>
											</div>
										</td>
									</tr>
									<logic:iterate name="<%=MrdConfig.ALL_GENERATED_DUPLICATE_MC_OF_PATIENT %>" id="patMedDupDtlVO" type="hisglobal.vo.PatMedicalDtlVO" indexId="dupMCIndex">
										<tr>
											<td class="tdfont" width="5%" >
												<div align="center">
													<html:radio name="IssueCertificateFB" property="selectedDupMC" value="<%=dupMCIndex.toString() %>" onclick="getDuplicateMCDetail(this)"></html:radio>
												</div>
											</td>
											<td class="tdfont" width="20%" >
												<div align="center">
													<bean:write name="patMedDupDtlVO" property="medicalCertificateDesc"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDMCMedicalCertificateDesc" value="<%=patMedDupDtlVO.getMedicalCertificateDesc() %>"/>
												</div>
											</td>
											<td class="tdfont" width="40%" >
												<div align="center">
													<bean:write name="patMedDupDtlVO" property="sufferingFrom"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDMCSufferingFrom" value="<%=patMedDupDtlVO.getSufferingFrom() %>"/>
												</div>
											</td>
											<td class="tdfont" width="10%" >
												<div align="center">
													<bean:write name="patMedDupDtlVO" property="adviceDays"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDMCAdviceDays" value="<%=patMedDupDtlVO.getAdviceDays() %>"/>
												</div>
											</td>
											<td class="tdfont" width="13%" >
												<div align="center">
													<bean:write name="patMedDupDtlVO" property="fromDate"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDMCFromDate" value="<%=patMedDupDtlVO.getFromDate() %>"/>
												</div>
											</td>
											<td class="tdfont" width="12%" >
												<div align="center">
													<bean:write name="patMedDupDtlVO" property="toDate"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDMCToDate" value="<%=patMedDupDtlVO.getToDate() %>"/>
												</div>
											</td>	
										</tr>
									</logic:iterate>
								</table>
							</his:ContentTag>	
							<%} %>
							</logic:equal>
							
							<logic:equal name="IssueCertificateFB" property="certificateType" value="<%=MrdConfig.CERTIFICATE_TYPE_DUPLICATE_FITNESS_CERTIFICATE %>">
							<%
							PatFitnessDtlVO[] arrPatFitDupDtlVO=(PatFitnessDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_DUPLICATE_FC_OF_PATIENT);
								if(arrPatFitDupDtlVO.length>0){
							 %>
							 	<his:SubTitleTag name="Duplicate Fitness Certificate List">
								</his:SubTitleTag>
							<his:ContentTag>	
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="select"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="fitnessCertificateNo"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="45%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="sufferingFrom"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="fitnessDate"/>
													</b>	
												</font>
											</div>
										</td>
										<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="medCertificateNo"/>
													</b>	
												</font>
											</div>
										</td>
										
									</tr>
									<logic:iterate name="<%=MrdConfig.ALL_GENERATED_DUPLICATE_FC_OF_PATIENT %>" id="patFitDupDtlVO" type="hisglobal.vo.PatFitnessDtlVO" indexId="dupFCIndex">
										<tr>
											<td class="tdfont" width="5%" >
												<div align="center">
													<html:radio name="IssueCertificateFB" property="selectedDupFC" value="<%=dupFCIndex.toString() %>" onclick="getDuplicateFCDetail(this)"></html:radio>
												</div>
											</td>
											<td class="tdfont" width="20%" >
												<div align="center">
													<bean:write name="patFitDupDtlVO" property="fitnessCertificateDesc"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDFCFitnessCertificateDesc" value="<%=patFitDupDtlVO.getFitnessCertificateDesc() %>"/>
												</div>
											</td>
											<td class="tdfont" width="45%" >
												<div align="center">
													<bean:write name="patFitDupDtlVO" property="sufferingFrom"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDFCSufferingFrom" value="<%=patFitDupDtlVO.getSufferingFrom() %>"/>
												</div>
											</td>
											<td class="tdfont" width="10%" >
												<div align="center">
													<bean:write name="patFitDupDtlVO" property="fitnessDate"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDFCFitnessDate" value="<%=patFitDupDtlVO.getFitnessDate() %>"/>
												</div>
											</td>
											<td class="tdfont" width="20%" >
												<div align="center">
													<bean:write name="patFitDupDtlVO" property="medicalCertificateDesc"/>
													<html:hidden name="IssueCertificateFB" property="hiddenDFCMedicalCertificateDesc" value="<%=patFitDupDtlVO.getMedicalCertificateDesc() %>"/>
												</div>
											</td>
											
										</tr>
									</logic:iterate>
								</table>
							</his:ContentTag>	
							 <%} %>
						</logic:equal>
					
					
					<his:ContentTag>
						<div id="divMedicalCertificate" style="display: <%=medicalCertificate%>">
							<his:SubTitleTag name="Generated Medical Certificate Detail">
							</his:SubTitleTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="medicalCertId">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="IssueCertificateFB" property="medicalCertificateDesc" readonly="true"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="startday"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="fromDate" readonly="true"></html:text>
											</font>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="sufferingFrom"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="sufferingFrom" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="endday"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="toDate" readonly="true"></html:text>
											</font>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="advice"/>
												<bean:message key="day"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="adviceDays" readonly="true" ></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead"></td>
									<td width="25%" class="tdfont" ></td>
									
								</tr>
								
							</table>	
							
						</div>
						
						<div id="divFitnessCertificate" style="display: <%=fitnessCertificate%>">
							<his:SubTitleTag name="Generated Fitness Certificate Detail">
							</his:SubTitleTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="fitnessCertificateId">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="fitnessCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text property="fitnessCertificateDesc" name="IssueCertificateFB" readonly="true"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text property="medicalCertificateDesc" name="IssueCertificateFB" readonly="true"></html:text>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="sufferingFrom"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text property="sufferingFrom" name="IssueCertificateFB" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="fitnessDate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text property="fitnessDate" name="IssueCertificateFB" readonly="true"></html:text>
											</font>	
										</div>
									</td>
								</tr>
								
							</table>	
							
						</div>
						
						<div id="divDupMedicalCertificate" style="display: <%=dupMedicalCertificate%>">
							<his:SubTitleTag name="Duplicate Medical Certificate Detail">
							</his:SubTitleTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="dupMedicalCertId" >
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="IssueCertificateFB" property="medicalCertificateDesc" readonly="true"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="startday"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="fromDate" readonly="true"></html:text>
											</font>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="sufferingFrom"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="sufferingFrom" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="endday"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="toDate" readonly="true"></html:text>
											</font>	
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="advice"/>
												<bean:message key="day"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text name="IssueCertificateFB" property="adviceDays" readonly="true" ></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead"></td>
									<td width="25%" class="tdfont" ></td>
								</tr>
								
							</table>
						</div>
						
						<div id="divDupFitnessCertificate" style="display: <%=dupFitnessCertificate%>">
							<his:SubTitleTag name="Duplicate Fitness Certificate Detail">
							</his:SubTitleTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="dupFitnessCertId">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="fitnessCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text property="fitnessCertificateDesc" name="IssueCertificateFB" readonly="true"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="medCertificateNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text property="medicalCertificateDesc" name="IssueCertificateFB" readonly="true"></html:text>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="sufferingFrom"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text property="sufferingFrom" name="IssueCertificateFB" readonly="true"></html:text>
											</font>	
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="fitnessDate"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<html:text property="fitnessDate" name="IssueCertificateFB" readonly="true"></html:text>
											</font>	
										</div>
									</td>
								</tr>
								
							</table>
						</div>
						
						<div id="commonDiv" style="display: <%=commonDiv%>">
							<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="dupFitnessCertId">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="remarks"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="IssueCertificateFB" property="remarks" maxlength="50" size="30"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="isReceiptTaken"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<input type="checkbox" name="isReceiptTaken" checked="checked">
										</div>
									</td>
								</tr>
							</table>
						</div>
					</his:ContentTag>
				</his:statusList>
				
								
				<his:ButtonToolBarTag>
					<logic:equal name="IssueCertificateFB" property="hmode" value="GETPATDTL">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateIssue()) submitForm('SAVEISSUECER')" onkeypress="if(event.keyCode==13)if(validateIssue()) submitForm('SAVEISSUECER')">
					</logic:equal>
					<logic:equal name="IssueCertificateFB" property="hmode" value="CHANGEMODE">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateIssue()) submitForm('SAVEISSUECER')" onkeypress="if(event.keyCode==13)if(validateIssue()) submitForm('SAVEISSUECER')">
					</logic:equal>
				
					
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
					
					<logic:notEqual name="IssueCertificateFB" property="hmode" value="GETPATDTL">
						<logic:notEqual name="IssueCertificateFB" property="hmode" value="CHANGEMODE">	
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
						</logic:notEqual>
					</logic:notEqual>
					
					<logic:equal name="IssueCertificateFB" property="hmode" value="GETPATDTL">					
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearValue()" onkeypress="if(event.keyCode==13)clearValue()">
					</logic:equal>
					<logic:equal name="IssueCertificateFB" property="hmode" value="CHANGEMODE">					
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearValue()" onkeypress="if(event.keyCode==13)clearValue()">
					</logic:equal>
				</his:ButtonToolBarTag>
				
			</his:TransactionContainer>
			
			<html:hidden name="IssueCertificateFB" property="hmode" />
			<html:hidden name="IssueCertificateFB" property="patCrNo"/>
			<html:hidden name="IssueCertificateFB" property="certificateType"/>
			<html:hidden name="IssueCertificateFB" property="dupCertificateType"/>
			<html:hidden name="IssueCertificateFB" property="selectedGenFC"/>
			<html:hidden name="IssueCertificateFB" property="selectedGenMC"/>
			<html:hidden name="IssueCertificateFB" property="selectedDupMC"/>
			<html:hidden name="IssueCertificateFB" property="selectedDupFC"/>
			<html:hidden name="IssueCertificateFB" property="tempMode" />
		</html:form>
		<his:status/>
	</body>

</html>