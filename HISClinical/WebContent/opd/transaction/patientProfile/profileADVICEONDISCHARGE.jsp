
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.PatDrugTreatmentDetailVO"%>
<%@page import="hisglobal.vo.PatDietAdviceDetailVO"%>
<%@page import="hisglobal.vo.PatExtTreatmentDetailVO"%>
<%@page import="hisglobal.vo.RestAdviceDtlVO"%>
<%@page import="java.util.ArrayList"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
	String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session
			.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpAdviceOnDischarge = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstDrugAdvice = (List) mpAdviceOnDischarge.get("lstPatientDrugAdviceVO");
		List lstDietAdvice = (List) mpAdviceOnDischarge.get("lstPatientDietAdviceVO");
		List lstRestAdvice = (List) mpAdviceOnDischarge.get("lstPatientRestAdviceVO");
		List lstOtherAdvice = (List) mpAdviceOnDischarge.get("lstPatientOtherAdviceVO");
		List lstOtherInstructions = (List) mpAdviceOnDischarge.get("lstPatientOtherInstructionsVO");
		List lstOtherActivity = (List) mpAdviceOnDischarge.get("lstPatientOtherActivityVO");
%>
<%@page import="java.util.Iterator"%>

<table width="100%" border="1" cellpadding="0" cellspacing="0">
	          <tr>
				<td width="100%" align="left">
					<table width='100%'>
						<tr>
							<td width="100%">
								<b>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="adviceOnDischarge"/>
									</font>
								</b>
							</td>
						</tr>
						<%
						if(lstDrugAdvice.size()!=0)
						{	
						%>
						<tr>
							<td width="100%">
								<table width='100%'>
							<tr>
								<td width="100%" align="left">
									<b>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="drugAdvice"/>
										</font>
									</b>
								</td>
							</tr>
						</table>
						
						<table width='100%'>
							<tr>
								<td width="15%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="drugname" />
										</b>
									</font>
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="dosage" />
										</b>
									</font>
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="frequency" />
										</b>
									</font>
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="dosedays" />
										</b>
									</font>
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="startday" />
										</b>
									</font>
								</div>
							</td>
							
							<td width="10%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
											<bean:message key="emptyStomach"/>
											</b>
										</font>	
									</div>
								</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="drugRoute" />
										</b>
									</font>
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="instructions" />
										</b>
									</font>
								</div>
							</td>
								
								
							</tr>
							<%
								Iterator lstDrugAdviceItr = lstDrugAdvice.iterator();
								while(lstDrugAdviceItr.hasNext())
								{
									PatDrugTreatmentDetailVO vo = (PatDrugTreatmentDetailVO)lstDrugAdviceItr.next();
							%>
							<tr>
								<td width='15%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getDrugName()%>
									</font>
								</td>
								<td width='10%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getDoseName()%>
									</font>
								</td>
								<td width='16%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getFrequencyName()%>
									</font>
								</td>
								<td width='7%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getDays()%>
									</font>
								</td>
								<td width='7%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getStartDay()%>
									</font>
								</td>
								<td width='10%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getIsEmptyStomachName()%>
									</font>
								</td>
								<td width='20%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getDrugRouteName()%>
									</font>
								</td>
								<td width='20%' align='center'>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=vo.getRemarks()%>
									</font>
								</td>
								
							</tr>
							<%
								}
							%>
							</table>
							</td>
						</tr>
						<%} %>
						<%
							if(lstOtherAdvice.size()!=0)
							{	
							%>
							<tr>
						    <td width="100%">
								<table width='100%'>
									<tr>
										<td width="100%" align="left">
											<b>
												<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="otherAdvice"/>
												</font>
											</b>
										</td>
									</tr>
								</table>
								
								<table width='100%'>
									<tr>
									<td width="20%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="ExtTreatmentName" />
												</b>
											</font>
										</div>
									</td>
									
									<td width="20%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="frequency" />
												</b>
											</font>
										</div>
									</td>
									<td width="20%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="dosedays" />
												</b>
											</font>
										</div>
									</td>
									<td width="20%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="startday" />
												</b>
											</font>
										</div>
									</td>
									<td width="20%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="instructions" />
												</b>
											</font>
										</div>
									</td>
									
								</tr>
									<%
										Iterator lstOtherAdviceItr = lstOtherAdvice.iterator();
										while(lstOtherAdviceItr.hasNext())
										{
											PatExtTreatmentDetailVO vo = (PatExtTreatmentDetailVO)lstOtherAdviceItr.next();
									%>
									<tr>
										<td width='20%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getExtTreatmentName()%>
											</font>
										</td>
										<td width='20%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getFrequencyId()%>
											</font>
										</td>
										<td width='20%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getDays()%>
											</font>
										</td>
										<td width='20%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getStartDay()%>
											</font>
										</td>
										<td width='20%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getRemarks()%>
											</font>
										</td>
									</tr>
									<%
										}
									%>
								</table>
							</td>
						</tr>
						<%} %>
						<%
						if(lstDietAdvice.size()!=0)
						{	
						%>
						<tr>
						  <td width="100%">
								<table width='100%'>
									<tr>
										<td width="100%" align="left">
											<b>
												<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="dietAdvice"/>
												</font>
											</b>
										</td>
									</tr>
								</table>
								
								<table width='100%'>
									<tr>
										<td width="25%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="dietType" />
												</b>
											</font>
										</div>
									</td>				
									<td width="25%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="dosedays" />
												</b>
											</font>
										</div>
									</td>
									<td width="25%">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
												<bean:message key="remark" />
												</b>
											</font>
										</div>
									</td>
										
										
									</tr>
									<%
										Iterator lstDietAdviceItr = lstDietAdvice.iterator();
										while(lstDietAdviceItr.hasNext())
										{
											PatDietAdviceDetailVO vo = (PatDietAdviceDetailVO)lstDietAdviceItr.next();
									%>
									<tr>
										<td width='25%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getDietTypeDesc()%>
											</font>
										</td>
										<td width='25%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getDays()%>
											</font>
										</td>
										<td width='25%' align='center'>
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=vo.getRemarks()%>
											</font>
										</td>
										
										
									</tr>
									<%
										}
									%>
								</table>
							</td>
						</tr>
						<%} %>
						<%
								if(lstRestAdvice.size()!=0)
								{	
						%>
								<tr>
							    <td width="100%">
									<table width='100%'>
										<tr>
											<td width="100%" align="left">
												<b>
													<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="restAdvice"/>
													</font>
												</b>
											</td>
										</tr>
									</table>
									
									<table width='100%'>
										<tr>
											<td width="50%">
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<b>
														<bean:message key="dosedays" />
														</b>
													</font>
												</div>
											</td>	
										<td width="50%">
												<div align="left">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<b>
														<bean:message key="reason" />
														</b>
													</font>
												</div>
											</td>
									</tr>
										<%
											Iterator lstRestAdviceItr = lstRestAdvice.iterator();
											while(lstRestAdviceItr.hasNext())
											{
												RestAdviceDtlVO vo = (RestAdviceDtlVO)lstRestAdviceItr.next();
										%>
										<tr>
											<td width='50%' align='center'>
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=vo.getDays()%>
												</font>
											</td>
											<td width='50%' align='left'>
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<%=vo.getRestReason()%>
												</font>
											</td>
										</tr>
										<%
											}
										%>
									</table>
								</td>
							</tr>
				<%} %>
							
						<%
						if(lstOtherInstructions.size()!=0||lstOtherActivity.size()!=0)
						{
						%>
						<tr>
						    <td width="100%">
								<table width='100%'>
									<tr>
										<td width="100%" align="left">
											<b>
												<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="otherInstruction/Activity"/>
												</font>
											</b>
										</td>
									</tr>
								</table>
							</td>
						</tr>		
						<%
						}
						%>
						<%
						if(lstOtherInstructions.size()!=0)
						{ 	
							int k=0;
							
						%>
						
						<tr>
						    <td width="100%">
								
								<table  width="100%">
								<tr>
									
										<td width="100%" colspan="2">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="instructions" />
													</b>
												</font>
											</div>
										</td>		
								</tr>
								<logic:iterate id="entryObj" indexId="i" name="<%=OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH%>" type="hisglobal.utility.Entry">
								<tr>
									<td width="5%">
									</td>					
									<td width="95%" nowrap="nowrap">
											<div align="left">
											<%
													List otherInstList=(List)session.getAttribute(OpdConfig.PREV_OTHER_INSTRUCTION_LIST);
													if(otherInstList==null)
													{
														otherInstList=new ArrayList();
													}
													Boolean flag=false;
													if(otherInstList.size()!=0)
													{
														for(int j=0;j<otherInstList.size();j++)
														{
															PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)otherInstList.get(j);
																								
															if(vo.getExtTreatmentId().equals(entryObj.getValue()))
															{
													
																flag=true;
												%>
												<%k=k+1; %>
												<%=k %>.
												<%=entryObj.getLabel() %>
												<%			
															}
														}
													}
													
												%>
												
												
												
												
											</div>
										</td>
									
									
								</tr>
								</logic:iterate>
							</table>
							</td>
							</tr>
						
						<%} %>
						<%
						if(lstOtherActivity.size()!=0)
						{ 	
							int l=0;
						%>
						
						<tr>
						    <td width="100%">
								
								<table  width="100%">
								<tr>
									
										<td width="100%" colspan="2">
											<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="activity" />
													</b>
												</font>
											</div>
										</td>		
								</tr>
								<logic:iterate id="entryObj1" indexId="i" name="<%=OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH%>" type="hisglobal.utility.Entry">
								<tr>
									<td width="5%">
									</td>	
									<td width="95%">
											<div align="left">
											 <%
													List activityListList=(List)session.getAttribute(OpdConfig.PREV_OTHER_ACTIVITY_LIST);
													if(activityListList==null)
													{
														activityListList=new ArrayList();
													}
													Boolean flag=false;
													
													if(activityListList.size()!=0)
													{
														for(int j=0;j<activityListList.size();j++)
														{
															PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)activityListList.get(j);
																								
															if(vo.getExtTreatmentId().equals(entryObj1.getValue()))
															{
																flag=true;
												%>
												<%l=l+1; %>
												<%=l %>.
												<%=entryObj1.getLabel() %>
												<%			
															}
														}
													}
													
												%>
												
												
												
												
											</div>
										</td>
									
									
								</tr>
								</logic:iterate>
							</table>
							</td>
							</tr>
						
						<%} %>
					</table>	<!-- main table -->
				</td>
			 </tr>
	
	
	
</table>			
<%
	}
%>