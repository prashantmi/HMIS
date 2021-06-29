<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.ANCNeonatalApgarVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function printCertificate(e)
{
	document.getElementsByName('htmlCertificateData')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
	document.getElementsByName('hmode')[0].value='PRINTBIRTHCERTIFICATE';
	document.forms[0].submit();
}

function callOnLoadEvent(e)
{
	if(document.getElementsByName('hmode')[0].value == 'PRINTBIRTHCERTIFICATE')
	{
		var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=NONE";
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}

function closeForm()
{
	self.close();
}
</script>
</head>

<body onload="callOnLoadEvent(event)">
<html:form action="/antenatalNeonatalDetail">
	<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<td width="100%">
				<div id="noPrint" align="right">
					<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printCertificate(event);" onkeypress="if(event.keyCode==13) printCertificate(event);">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
				</div>
			</td>
		</tr>
		<br>
		<br>
	</table>
	<div id="pdfPrintingHTMLData" align="center">
	<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<td width="100%">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><u>
						BIRTH NOTIFICATION FORM
					</u></b></font>
				</div>
			</td>
		</tr>
		<br>		
		<tr>
			<td width="100%">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						Department of Pediatrics
					</b></font>
				</div>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						Cr No. <logic:present name="ANCNeonatalDetailFB" property="childCrNo"> : <bean:write name="ANCNeonatalDetailFB" property="childCrNo"/>  </logic:present>
						<logic:empty name="ANCNeonatalDetailFB" property="pregnancyDuration">______________________</logic:empty>
					</b></font>
				</div>
			</td>
		</tr>
		<tr>
			<td width="100%" valign="top" align="left">
				<table width="100%" align="center" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
					<tr>
						<td width="50%">
							<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Name of Mother:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="patName"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Mother CR Number:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="patCrNo"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Name of Father:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="patSpouceName"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Age of Mother:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="patAge"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Gravida:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="gravidaNo"/></b>
												&nbsp;&nbsp;Parity:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="parityNo"/></b>
												&nbsp;&nbsp;Abortus:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="abortusNo"/></b>
												<!-- 
												PARA:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="deliveryCount"/></b>
												 -->
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Admission No.:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="admissionNo"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Date of Admission:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="admDateTime"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Indication:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="inductionMethod"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												LMP:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="lmpDate"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												EDD:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="expectedDeliveryDate"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												POG:&nbsp;
												<b><logic:present name="ANCNeonatalDetailFB" property="pregnancyDuration"><bean:write name="ANCNeonatalDetailFB" property="pregnancyDuration"/> Weeks</logic:present></b>
											</font>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td width="50%">
							<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Date &amp; Time of Birth:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="birthDateTime"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Gender of Baby:&nbsp;
												<b><bean:write name="ANCNeonatalDetailFB" property="gender"/></b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Birth Weight:&nbsp;
												<b><logic:present name="ANCNeonatalDetailFB" property="weight"><bean:write name="ANCNeonatalDetailFB" property="weight"/> kg</logic:present></b>
											</font>
										</div>
									</td>
								</tr>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_APGAR_DETAIL_LIST%>">
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>APGAR SCORE:</b>
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="100%">
										<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
											<% 
												List lstApgarDtl = (List) session.getAttribute(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_APGAR_DETAIL_LIST);
												if(lstApgarDtl!=null && lstApgarDtl.size()>0)
												{
													String width = (75/lstApgarDtl.size())+"%";
											%>
											<tr>
												<td width="25%" nowrap="nowrap"></td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getApgarTimeDesc()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<tr>
												<td width="25%" nowrap="nowrap">
													<div align="left">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b>Respiration</b>
														</font>
													</div>
												</td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getRespirationApgar()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<tr>
												<td width="25%" nowrap="nowrap">
													<div align="left">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b>Heart Rate</b>
														</font>
													</div>
												</td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getHeartRateApgar()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<tr>
												<td width="25%" nowrap="nowrap">
													<div align="left">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b>Color</b>
														</font>
													</div>
												</td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getColorApgar()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<tr>
												<td width="25%" nowrap="nowrap">
													<div align="left">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b>Tone</b>
														</font>
													</div>
												</td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getActivityApgar()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<tr>
												<td width="25%" nowrap="nowrap">
													<div align="left">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b>Reflex Activity</b>
														</font>
													</div>
												</td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getGrimaceApgar()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<tr>
												<td width="25%" nowrap="nowrap">
													<div align="left">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b>Total:</b>
														</font>
													</div>
												</td>
											<%
													for(int i=0; i<lstApgarDtl.size(); i++)
													{
														ANCNeonatalApgarVO apgarDlVO = (ANCNeonatalApgarVO) lstApgarDtl.get(i);
											%>
												<td width="<%=width%>" nowrap="nowrap">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<b><%=apgarDlVO.getApgarScore()%></b>
														</font>
													</div>
												</td>
											<%		}	%>
											</tr>
											<%
												}
											%>
										</table>
									</td>
								</tr>
								</logic:present>
								<tr>
									<td width="100%" nowrap="nowrap">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Umbilical Arteries:&nbsp;
												<logic:present name="ANCNeonatalDetailFB" property="umbilicalArteries">
													<bean:define id="idUmbilicalArteries" name="ANCNeonatalDetailFB" property="umbilicalArteries" type="java.lang.String"></bean:define>
													<b><%=(idUmbilicalArteries!=null && !idUmbilicalArteries.trim().equals(""))?InpatientConfig.UMBILICAL_ARTERIES_ARR[Integer.parseInt(idUmbilicalArteries)]:""%></b>
												</logic:present>
											</font>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="80%" align="center">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						Name of Doctor who performed the Delivery :&nbsp;&nbsp;
					</b></font>
				</div>
			</td>
		</tr>
		<tr>
			<td width="80%" align="center">
				<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td>
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
									Date________________________
								</b></font>
							</div>
						</td>
						<td>
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
									Signature_____________________________
								</b></font>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
	<html:hidden name="ANCNeonatalDetailFB" property="hmode"/>
	<html:hidden name="ANCNeonatalDetailFB" property="patCrNo"/>
	<html:hidden name="ANCNeonatalDetailFB" property="noOfCopies"/>
	<html:hidden name="ANCNeonatalDetailFB" property="selBabySerialCert"/>
	<html:hidden name="ANCNeonatalDetailFB" property="htmlCertificateData"/>	
	<html:hidden name="ANCNeonatalDetailFB" property="patSpouceName"/>
</html:form>
</body>
</html>	