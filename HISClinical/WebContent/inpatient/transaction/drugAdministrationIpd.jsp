<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>	
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<link href="/HISClinical/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<his:javascript src="/inpatient/js/drug_admin.js"/> 
<his:javascript src="/hisglobal/js/jquery/jquery-1.10.2.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

	<his:TitleTag name="Drug Administration">
	</his:TitleTag>
	
	<!-- ********************adding drug property*************************** -->
			<div id="blanket" style="height: 600;width: 900;display: none;"></div>
			<div id="userIdDiv" style="display: none; height: 110px;width: 700px;position:absolute; top: 10%; left: 5%;z-index: 9100;background-color: #E0EBEB;">
				
				<table  width="700">
					<tr CLASS="HEADER">
						<td >Drug Safety Alert</td>
					</tr>
				</table>
				
				<div id="drugSafetyId"></div>
				
				<table  width="700">
					<tr CLASS="HEADER">
						<td>Drug Dose Indication</td>
					</tr>
				</table>	

				<div id="drugProfileId" ></div>
				
				<table  width="700">
					<tr>
						<td align="center">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancel()">
							</div>
						</td>
					</tr>
				</table>		
						
			</div>
	<!-- ********************end drug property******************************** -->		
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
	<logic:notEmpty name="<%=InpatientConfig.SELECTED_IVFLUIDS_LIST%>">
	<his:SubTitleTag name="InProcess IvFluid Detail">
	</his:SubTitleTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="select" />
								</b>
							</font>
						</div>
					</td>
			<td width="12%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="drugRoute" />
								</b>
							</font>
						</div>
					</td>
					
						
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="time" />
								<bean:message key="timeFormat" />
								
								</b>
							</font>
						</div>
					</td>
					
					<td width="8%" class="tdfonthead" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="8%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="endTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="8%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="volume" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="batchNo" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="expiryDate" />
							</b>
						</font>
					</div>
				</td>
				<td width="13%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
		</tr>
		<logic:iterate id="drugAdminDtlVO" indexId="idx" name="<%=InpatientConfig.SELECTED_IVFLUIDS_LIST%>" type="hisglobal.vo.DrugAdminDtlVO">
		<%String index=idx.toString(); %>
		<tr>
			<td width="5%" class="tdfont">
						<div align="center">
							<html:checkbox property="ivFluidSelIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB" ></html:checkbox>	
							<html:hidden name="DrugAdministrationFB" property="ivFluidSelAdviceDateArray" value="<%=drugAdminDtlVO.getAdviceDate() %>"/>
							<html:hidden name="DrugAdministrationFB" property="ivFluidSelSerealNoArray" value="<%=drugAdminDtlVO.getSerialNo() %>"/>
							<html:hidden name="DrugAdministrationFB" property="ivFluidSelItemIdArray" value="<%=drugAdminDtlVO.getItemId() %>"/>												
							<html:hidden name="DrugAdministrationFB" property="sosFlagArray" value="<%=drugAdminDtlVO.getSosFlag() %>"/>
							<html:hidden name="DrugAdministrationFB" property="selStartTimeForInTakeArray" value="<%=drugAdminDtlVO.getAdministrationTime() %>"/>
							<html:hidden name="DrugAdministrationFB" property="ivFluidSelRouteIdArray" value="<%=drugAdminDtlVO.getDrugRouteId() %>"/>
							<html:hidden name="DrugAdministrationFB" property="ivFluidSelDrugBrandIdArray" value="<%=drugAdminDtlVO.getDrugBrandId() %>"/>	
						</div>
					</td>
			<td width="15%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="itemName"/>
							</b>
						</div>
					</td>
			<td width="10%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="doseName"/>
							</b>
						</div>
					</td>
			<td width="12%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="drugRouteName"/>
							</b>
						</div>
					</td>
					
					
			<td width="15%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="doseTime"/>
							</b>
						</div>
					</td>
			<td width="15%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="administrationTime"/>
							</b>
						</div>
					</td>
			<td width="10%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="ivFluidSelEndExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" />
						:
						<html:text name="DrugAdministrationFB" property="ivFluidSelEndExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()"  />
					</div>
				</td>
			<td width="10%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="ivFluidSelVolumeArray" size="4" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" value="<%=drugAdminDtlVO.getVolume() %>"/>
						
					</div>
				</td>	
					
		<td width="15%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="batchNo"/>
							</b>
						</div>
					</td>
		<td width="15%" class="tdfont" >
						<div align="center">
							<b>
								<bean:write name="drugAdminDtlVO" property="expiryDate"/>
							</b>
						</div>
					</td>
		<td width="10%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="ivFluidSelRemarksArray" value="<%=drugAdminDtlVO.getRemarks() %>" size="10" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>																	
		</tr>
		</logic:iterate>
	</table>
	</logic:notEmpty>
	
	
		<his:SubTitleTag name="Today Scheduled Drug Advice Detail">
			
			<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				
				<td width="60%">
					
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getDrugGivenDetail(event)" >
	      							<U><bean:message key="viewDrugGiven"/></U>
	      						</a>
							</b>
						</font>		
					
				</td>
				<td width="40%">
					
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="showCompleteTreatment(event)" >
	      							<U><bean:message key="completeTreatment"/></U>
	      						</a>
							</b>
						</font>		
					
				</td>
			</tr>
			
		</table>
		</his:SubTitleTag>
		<logic:notEmpty name="<%=InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT%>">
			<table  width="100%" border="1"  cellspacing="2" cellpadding="0" >
				<tr>
					<td width="3%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="select" />
								
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="drugRoute" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="scheduleDate" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="time" />
								<bean:message key="timeFormat" />
								                                                        
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="endTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="4%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="volume" />
							</b>
						</font>
					</div>
				</td>
				<td width="3%" class="tdfonthead" <% if(InpatientConfig.SOURCE_OF_DRUG_PATIENT_STOCK.equals(InpatientConfig.SOURCE_OF_DRUG_PATIENT_STOCK_NO)){ %>style="display: none;" <%} %> nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<!--<font color="#FF0000">*</font>
							-->
							<bean:message key="fromPatient" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="3%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="availDrugBrand" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="3%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="batchNo" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="7%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="expiryDate" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>	
				</tr>
				<logic:iterate id="drugAdminDtlVO" indexId="idx" name="<%=InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT%>" type="hisglobal.vo.DrugAdminDtlVO">
				<%String ind=idx.toString(); %>
				<tr>
				
					<logic:equal value="0" name="drugAdminDtlVO" property="drugConsentStatus">
							<%String itemTypeId= drugAdminDtlVO.getItemTypeId();
								String itemId= drugAdminDtlVO.getItemId();
								String itemName= drugAdminDtlVO.getItemName();
								System.out.println(itemName);
								String ivfluidFlag=drugAdminDtlVO.getIvFluidFlag();
								String doseTime=drugAdminDtlVO.getDoseTime();
								String index=idx.toString();
								String temp="sendDataForDrugDoseList("+itemTypeId+","+index+","+itemId+",'"+itemName+"')";
								String itemTypeAndIndex=itemTypeId+"#"+index+"#"+doseTime.replace(':','@');
								//if(doseTime!=null && !doseTime.equalsIgnoreCase(":") && !doseTime.isEmpty())
								//{
								String doseTimeHours=doseTime.split(":")[0];
								String doseTimeMinut=doseTime.split(":")[1];
								
								String sys=(String)session.getAttribute(Config.SYSDATE);
								String time=sys.split(" ")[1];
								
								String sysHours=time.split(":")[0];
								String sysMinut=time.split(":")[1];
								
								String timeLimitBefore=drugAdminDtlVO.getBeforeTimeLimit();
								String timeLimitAfter=drugAdminDtlVO.getAfterTimeLimit();
								System.out.println("timeLimitBefore:"+timeLimitBefore);
								System.out.println("timeLimitAfter:"+timeLimitAfter);
								String timeLimitHrsBefore=timeLimitBefore.split(":")[0];
								String timeLimitMinBefore=timeLimitBefore.split(":")[1];
								String timeLimitHrsAfter=timeLimitAfter.split(":")[0];
								String timeLimitMinAfter=timeLimitAfter.split(":")[1];
							//	String timeLimit=InpatientConfig.TIME_LIMIT_FOR_DRUG;
								
								Integer totalTimeLimitHours=Integer.parseInt(sysHours)-Integer.parseInt(timeLimitHrsBefore);
								Integer totalTimeLimitMinut=Integer.parseInt(sysMinut)-Integer.parseInt(timeLimitMinBefore);
								Integer totalTimeLimitHoursA=Integer.parseInt(sysHours)+Integer.parseInt(timeLimitHrsAfter);
								Integer totalTimeLimitMinutA=Integer.parseInt(sysMinut)+Integer.parseInt(timeLimitHrsAfter);
								if(Integer.parseInt(doseTimeHours)<totalTimeLimitHoursA && Integer.parseInt(doseTimeHours)>totalTimeLimitHours)
								{
							%>
							<td width="5%" class="tdfont">
								<div align="center">
									<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB"  onchange="<%=temp%>" ></html:checkbox>
								<div id="divAlert<%=idx.toString()%>" style="display: none;" >
								<img src="/HIS/hisglobal/images/alert_button.png" alt="Drug Alert" id="alertBellId" title="Drug Alert" height="15px" width="15px" onmouseover="showDivAlert();" onmouseout="hideDivALert()">
								</div>
								</div>
							</td>	
							<%		
								}
								else
								{
									if(Integer.parseInt(doseTimeHours)==totalTimeLimitHours || Integer.parseInt(doseTimeHours)==totalTimeLimitHoursA)
									{
										if(Integer.parseInt(doseTimeMinut)<totalTimeLimitMinut)
										{
											//enabled
							%>
							<td width="5%" class="tdfont">
								<div align="center">
									<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB"  onchange="<%=temp %>"  ></html:checkbox>
								</div>
							</td>	
							<%				
										}
										else
										{
							%>
							<td width="5%" bgcolor="#FFFFCC">
								<div align="center">
									<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB"  onchange="<%=temp %>"  disabled="true" ></html:checkbox>
								</div>
							</td>	
							<%			
											//disabled
										}
									}
									else
									{
							%>
							<td width="5%" bgcolor="#FFFFCC">
								<div align="center">
									<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB"  onchange="<%=temp %>"  disabled="true" ></html:checkbox>
								</div>
							</td>	
							<%			
										//disabled
									}
								}
								//}	
							%>
					</logic:equal>	
					<logic:notEqual value="0" name="drugAdminDtlVO" property="drugConsentStatus">
						<td width="5%" bgcolor="#CCFFFF">
						<div align="center">
							<%String itemTypeId= drugAdminDtlVO.getItemTypeId();
								String ivfluidFlag=drugAdminDtlVO.getIvFluidFlag();
								String doseTime=drugAdminDtlVO.getDoseTime();
								String index=idx.toString();
								String itemTypeAndIndex=itemTypeId+"#"+index+"#"+doseTime;
								//if(doseTime!=null && !doseTime.equalsIgnoreCase(":") && !doseTime.isEmpty()  )
								//{
								String doseTimeHours=doseTime.split(":")[0];
								String doseTimeMinut=doseTime.split(":")[1];
								
								String sys=(String)session.getAttribute(Config.SYSDATE);
								String time=sys.split(" ")[1];
								String sysHours=time.split(":")[0];
								String sysMinut=time.split(":")[1];
								
								String timeLimitBefore=drugAdminDtlVO.getBeforeTimeLimit();
								String timeLimitAfter=drugAdminDtlVO.getAfterTimeLimit();
								System.out.println("timeLimitBefore:"+timeLimitBefore);
								System.out.println("timeLimitAfter:"+timeLimitAfter);
								String timeLimitHrsBefore=timeLimitBefore.split(":")[0];
								String timeLimitMinBefore=timeLimitBefore.split(":")[1];
								String timeLimitHrsAfter=timeLimitAfter.split(":")[0];
								String timeLimitMinAfter=timeLimitAfter.split(":")[1];
							//	String timeLimit=InpatientConfig.TIME_LIMIT_FOR_DRUG;
								
								Integer totalTimeLimitHours=Integer.parseInt(sysHours)-Integer.parseInt(timeLimitHrsBefore);
								Integer totalTimeLimitMinut=Integer.parseInt(sysMinut)-Integer.parseInt(timeLimitMinBefore);
								Integer totalTimeLimitHoursA=Integer.parseInt(sysHours)+Integer.parseInt(timeLimitHrsAfter);
								Integer totalTimeLimitMinutA=Integer.parseInt(sysMinut)+Integer.parseInt(timeLimitHrsAfter);
								if(Integer.parseInt(doseTimeHours)<totalTimeLimitHoursA && Integer.parseInt(doseTimeHours)>totalTimeLimitHours)
								{
							%>
							
							<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB" title="<%=itemTypeAndIndex %>" onchange="sendDataForDrugDoseList(this,'<%=index%>')" disabled="true" ></html:checkbox>
							<%		
								}
								else
								{
									if(Integer.parseInt(doseTimeHours)==totalTimeLimitHours || Integer.parseInt(doseTimeHours)==totalTimeLimitHoursA)
									{
										if(Integer.parseInt(doseTimeMinut)<totalTimeLimitMinut)
										{
											//enabled
							%>
							<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB" title="<%=itemTypeAndIndex %>" onchange="sendDataForDrugDoseList(this,'<%=index%>')" disabled="true" ></html:checkbox>
							<%				
										}
										else
										{
							%>
							<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB" title="<%=itemTypeAndIndex %>" onchange="sendDataForDrugDoseList(this,'<%=index%>')" disabled="true" ></html:checkbox>
							<%			
											//disabled
										}
									}
									else
									{
							%>
							<html:checkbox property="selIndexArray" value="<%=idx.toString()%>" name="DrugAdministrationFB" title="<%=itemTypeAndIndex %>" onchange="sendDataForDrugDoseList(this,'<%=index%>')" disabled="true" ></html:checkbox>
							<%			
										//disabled
									}
								}	
								//}
							%>
							
						</div>
					</td>
					</logic:notEqual>	
								
							<html:hidden name="DrugAdministrationFB" property="selItemIdArray" value="<%=drugAdminDtlVO.getItemId() %>"/>
							<html:hidden name="DrugAdministrationFB" property="selItemTypeIdArray" value="<%=drugAdminDtlVO.getItemTypeId() %>"/>
							<html:hidden name="DrugAdministrationFB" property="selIvFluidFlagArray" value="<%=drugAdminDtlVO.getIvFluidFlag() %>"/>
							<html:hidden name="DrugAdministrationFB" property="selAdviceDateArray" value="<%=drugAdminDtlVO.getAdviceDate() %>"/>
							<html:hidden name="DrugAdministrationFB" property="selSerealNoArray" value="<%=drugAdminDtlVO.getSerialNo() %>"/>
							<html:hidden name="DrugAdministrationFB" property="prevDoseId" value="<%=drugAdminDtlVO.getDoseId() %>"/>
							<html:hidden name="DrugAdministrationFB" property="prevDrugRouteId" value="<%=drugAdminDtlVO.getDrugRouteId() %>"/>
							<html:hidden name="DrugAdministrationFB" property="reactionStatusArray" value="<%=drugAdminDtlVO.getReactionStatus() %>"/>
							<html:hidden name="DrugAdministrationFB" property="allergyStatusArray" value="<%=drugAdminDtlVO.getAllergyStatus() %>"/>												
						    <html:hidden name="DrugAdministrationFB" property="selItemBrandIdArray" value="<%=drugAdminDtlVO.getDrugBrandId() %>"/>												
						
						
						
					<td width="15%" 
					<%if(!drugAdminDtlVO.getAllergyStatus().equals("0")){ %>
					bgcolor="#FF0000"
					<%}%> 
					<%if(!drugAdminDtlVO.getReactionStatus().equals("0")){ %>
					bgcolor="#00BFFF"
					<%}%>
					<%if(drugAdminDtlVO.getReactionStatus().equals("0") && drugAdminDtlVO.getAllergyStatus().equals("0")){ %>
					class="tdfont"
					<%}%> 
					nowrap="nowrap">
						<div align="center" >
						<b>
								<%-- <a style="cursor:pointer" onclick="getDrugDetail(<%=drugAdminDtlVO.getItemId()%>,<%=idx.toString()%>)" >	 --%>
									<bean:write name="drugAdminDtlVO" property="itemName" />
									<html:hidden name="DrugAdministrationFB" property="selDrugBrandName" value="<%=drugAdminDtlVO.getItemName() %>"/>
								<!-- </a>	 -->
								</b>
							
						</div>
					</td>
					<td width="16%" class="tdfont"  >
					<div align="center" id="drugRouteTDId">
						<bean:write name="drugAdminDtlVO" property="doseName"/>
						<html:hidden name="DrugAdministrationFB" property="selDoseIdArray" value="<%=drugAdminDtlVO.getDoseId() %>"/>
						<html:hidden name="DrugAdministrationFB" property="selDoseNameArray" value="<%=drugAdminDtlVO.getDoseName() %>"/>
					</div>
					</td>
					
					
					
					<td width="16%" class="tdfont">
					<div align="center" id="drugRouteTDId" >
					
						<html:select name="DrugAdministrationFB" property="selDrugRouteIdArray" tabindex="1" value="<%=drugAdminDtlVO.getDrugRouteId() %>"  onchange="setDrugRouteName(findIndex(this))" style="width: 100px;" disabled="true" >	
						<logic:notEmpty name="drugAdminDtlVO" property="drugRouteId">
									<html:option value="<%=drugAdminDtlVO.getDrugRouteId() %>">
								<bean:write name="drugAdminDtlVO" property="drugRouteName"/> 
							</html:option>	
						</logic:notEmpty>
					<logic:empty name="drugAdminDtlVO" property="drugRouteId">
							<html:option value="">Select Value</html:option>
							</logic:empty> 
						</html:select>
						<html:hidden name="DrugAdministrationFB" property="selDrugRouteName" value="<%=drugAdminDtlVO.getDrugRouteName()%>"></html:hidden>	
					</div>
				</td>
					
					
					<%-- <td width="16%" class="tdfont">
					<div align="center" id="drugRouteTDId">
						<html:select name="DrugAdministrationFB" property="selDrugRouteIdArray" tabindex="1" 
							value="<%=drugAdminDtlVO.getDrugRouteId() %>" >
							<html:option value="-1">Select</html:option >
							<logic:present name="<%=InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO%>" >
							<html:options collection="<%=InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO%>" property="value" labelProperty="label"/>
							</logic:present>
							</html:select>
						
						<html:hidden name="DrugAdministrationFB" property="selDrugRouteName" value="<%=drugAdminDtlVO.getDrugRouteName()%>"></html:hidden>	
							</div>
					</td> --%>
					
					
					
					<!--  drug route before change 05.02.2016 -->
				<%-- <%
				
					if(drugAdminDtlVO.getDrugRouteId()!=null && !drugAdminDtlVO.getDrugRouteId().equals(""))
					{
				%>
					<td width="16%" class="tdfont"  >
					<div align="center" id="drugRouteTDId">
						<bean:write name="drugAdminDtlVO" property="drugRouteName"/>
						<html:hidden name="DrugAdministrationFB" property="selDrugRouteIdArray" value="<%=drugAdminDtlVO.getDoseId() %>"/>
					</div>
					</td>
				<%		
					}
					else
					{
				%>
					<td width="16%" class="tdfont"  >
					<div align="center" id="drugRouteTDId">
					<%String tempNotChangeRoute="notAllowChangeRoute("+drugAdminDtlVO.getDrugRouteId()+","+ind+")"; %>
						<html:select name="DrugAdministrationFB" property="selDrugRouteIdArray" tabindex="1"  value="<%=drugAdminDtlVO.getActualDrugRouteId() %>" disabled="true" style="width:80" onchange="<%=tempNotChangeRoute %>"> 
							<html:option value="">Select</html:option>
						</html:select>
					</div>
				</td>
				
				<%		
					}
				%>	 --%>
				
				<td width="5%" class="tdfont">
						<div align="center">
							
								<b>
									<%-- <bean:write name="drugAdminDtlVO" property="scheduleDate"/> --%>
							<%-- 		<input:text name="DrugAdministrationFB" property="selScheduleDateArray" id='selExpriryDateArray<%=idx.toString()%>' value="<%=drugAdminDtlVO.getScheduleDate() %>" disabled="true"/>
							 --%>		
							 <input type='text' size='12' name='selScheduleDateArray'  id='selScheduleDateArray<%=idx.toString()%>' readonly='readonly' value="<%=drugAdminDtlVO.getScheduleDate() %>">
								<input type="hidden" name="selScheduleDate" value="<%=drugAdminDtlVO.getScheduleDate() %>"/>
								</b>
								
								<img src='/HISClinical/hisglobal/images/imgDatepicker.png' id='IselScheduleDateArray<%=idx.toString()%>' style='cursor: pointer; border: 1px solid red; display: none;' 
						    	title='Date selector' tabindex='1' onmouseover="this.style.background='red';" onmouseout="this.style.background=''">
					
					<script type="text/javascript">
						Calendar.setup({inputField:'selScheduleDateArray<%=idx.toString()%>', mapkey:'32', ifFormat:'%d-%b-%Y', button:'IselScheduleDateArray<%=idx.toString()%>', singleClick:true});	
					</script>
		
							
						</div>
					</td>
					
					<html:hidden name="DrugAdministrationFB" property="beforeTimeLimitArray" value="<%=drugAdminDtlVO.getBeforeTimeLimit() %>" />
                    <html:hidden name="DrugAdministrationFB" property="afterTimeLimitArray" value="<%=drugAdminDtlVO.getAfterTimeLimit() %>"/>
					
					<td width="10%" class="tdfont">
						<div align="center">
							
								<b>
									<bean:write name="drugAdminDtlVO" property="doseTime"/>
									<html:hidden name="DrugAdministrationFB" property="selDoseTimeArray" value="<%=drugAdminDtlVO.getDoseTime() %>"/>
											
								</b>
							
						</div>
					</td>
					
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<html:text name="DrugAdministrationFB" property="selStartExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" disabled="true"/>
							:
							<html:text name="DrugAdministrationFB" property="selStartExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" disabled="true" />
						</div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="selEndExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" disabled="true"/>
						:
						<html:text name="DrugAdministrationFB" property="selEndExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" disabled="true" />
					</div>
				</td>
				<td width="4%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="selVolumeArray" size="4" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" disabled="true"/>
						
					</div>
				</td>
					
			
				<td width="3%" class="tdfont" <% if(InpatientConfig.SOURCE_OF_DRUG_PATIENT_STOCK.equals(InpatientConfig.SOURCE_OF_DRUG_PATIENT_STOCK_NO)){ %>style="display: none;" <%} %> nowrap="nowrap" id="fromPatient" >
					<div align="center">
						<input type="checkbox" value="<%=idx.toString()%>" name="fromPatientcheckBox" id="fromPatientCheckBoxId"  onclick="fromPatientValue(this,<%=idx.toString()%>)"  disabled="disabled"/>
						
						<div style="display: none">
							<html:text name="DrugAdministrationFB" property="drugStoreSourceArray" value="0" size="1" maxlength="3" disabled="true"/>
						</div>
						 
					</div>
				</td>
						
				
				
			<%
			String index1=idx.toString();
			String ConcatDivDrugRooute="drugRouteTD"+index1;
			String ConcatDivFromPatient="fromPatientDivId"+index1;
			System.out.print(ConcatDivDrugRooute);
			
			%>	
		
			
				
				<td width="5%" class="tdfont" nowrap="nowrap" >					
						<div align="center" id="<%=ConcatDivDrugRooute%>" style="display: block;">
						
							<logic:notEqual name="DrugAdministrationFB" property="drugSource" value="<%=InpatientConfig.SOURCE_OF_DRUG_IS_PATIENT%>">
								<% String temp1= "handleGetSetBatchNoList(this,"+ind+")"; %>
								<html:select name="DrugAdministrationFB" property="selDrugBrandArrayFromStore"  tabindex="1"  value=""  style="width:80"  onchange="<%=temp1%>" disabled="true" > 
									<html:option value="">Select</html:option>
								</html:select>
							</logic:notEqual>	
										</div>	
							
							<div align="center" id="<%=ConcatDivFromPatient%>" style="display: none;">
							<html:select name="DrugAdministrationFB" property="selDrugBrandArray"  tabindex="1"  value=""  style="width:80" onchange="setDrugBrandName(findIndex(this),false,false)" disabled="true" > 
									<html:option value="">Select</html:option>
								</html:select>
								
								
						<html:hidden name="DrugAdministrationFB" property="selDrugBrandName"></html:hidden>	
				
							
							</div>
							
								
					
				</td>
				
				<td width="5%" class="tdfont" nowrap="nowrap" >					
						<div align="center" id="batch<%=index1%>" >
							<logic:notEqual name="DrugAdministrationFB" property="drugSource" value="<%=InpatientConfig.SOURCE_OF_DRUG_IS_PATIENT%>">
								<% String temp3= "showExpiryDate("+ind+")"; %>
								<html:select name="DrugAdministrationFB" property="selBatchNoArray"  tabindex="1"  value=""  style="width:80" onchange="<%=temp3%>" disabled="true" > 
									<html:option value="">Select</html:option>
								</html:select>
							</logic:notEqual>	
						</div>				
				
					<div align="center" id="batchFromPatient<%=index1%>" style="display: none;">
							<html:text name="DrugAdministrationFB" property="selPatientBatchNoArray" size="6" maxlength="50" tabindex="1"  value=""  onkeypress="return validateAlphaNumOnly(this, event)"></html:text>
					</div>
					
				</td>
				
				<td width="7%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<input type='text' size='12' name='selExpriryDateArray'  id='selExpriryDateArray<%=ind%>' readonly='readonly' value=''>
						<img src='/HISClinical/hisglobal/images/imgDatepicker.png' id='IselExpriryDateArray<%=ind%>' style='cursor: pointer; border: 1px solid red; display: none;' 
							title='Date selector' tabindex='1' onmouseover="this.style.background='red';" onmouseout="this.style.background=''">
					</div>
					<script type="text/javascript">
						Calendar.setup({inputField:'selExpriryDateArray<%=ind%>', mapkey:'32', ifFormat:'%d-%b-%Y', button:'IselExpriryDateArray<%=ind%>', singleClick:true});	
					</script>
				</td>	
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<html:text name="DrugAdministrationFB" property="selRemarksArray" value="" size="10" tabindex="1" disabled="true" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
						</div>
					</td>	
					
				</tr>
				
				
				</logic:iterate>
			</table>
	</logic:notEmpty>
	
	<logic:notEmpty name="<%=InpatientConfig.SOS_DRUG_LIST_FOR_PATIENT%>">
	<his:SubTitleTag name="As Per Requirment Drug Advice Detail">
	</his:SubTitleTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td width="15%" class="tdfonthead">
					<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="drugRoute" />
								</b>
							</font>
						</div>
					</td>
										
					<td width="8%" class="tdfonthead" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="8%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="endTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="volume" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="drugBrandName" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="5%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="batchNo" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="expiryDate" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap" >
				</td>
		</tr>
		<tr>
				<td width="15%" class="tdfont" >
					<div align="center">
						<html:select name="DrugAdministrationFB" property="sosSelDrugId" tabindex="1" value="" onchange="sendDataForSOSDrugDoseList(this)" style="width:80"> 
							<html:option value="">Select</html:option>
							<logic:present name="<%=InpatientConfig.SOS_DRUG_LIST_FOR_PATIENT%>" >
							<html:options collection="<%=InpatientConfig.SOS_DRUG_LIST_FOR_PATIENT%>" property="value" labelProperty="label"/>
							</logic:present>
							
						</html:select>
					</div>
				</td>
				<td width="10%" class="tdfont"  >
					<div align="center" id="drugRouteTDId">
						<%-- <html:select name="DrugAdministrationFB" property="sosSelDoseId" tabindex="1" value="" style="width:80"> 
							<html:option value="">Select</html:option>
						</html:select> --%>
				<html:text name="DrugAdministrationFB" property="sosSelDoseId" tabindex="1" readonly='true' value=''  ></html:text>
						
					</div>
				</td>
				<td width="16%" class="tdfont"  >
				
							<html:hidden name="DrugAdministrationFB" property="sosSelDrugRouteName"></html:hidden>	
							<html:hidden name="DrugAdministrationFB" property="sosSelDrugRouteId"></html:hidden>
					<div align="center" id="drugRouteTDSosId" >
						<html:select name="DrugAdministrationFB" property="sosSelDrugRouteId1" tabindex="1"  value=""  style="width:80"> 
							<html:option value="">Select</html:option>
						</html:select>
					</div>
							<div align="center" id="drugRoutesosTDId" style="display: none;" >
						<html:select name="DrugAdministrationFB" property="sosSelDrugRouteId2" tabindex="1"  value=""  style="width:80" onchange="setAllRoute();"> 
							<html:option value="">Select</html:option>
						</html:select>
					</div>
				</td>
					
					
				<td width="10%" class="tdfont" nowrap="nowrap" >
					<div align="center">
							<html:text name="DrugAdministrationFB" property="sosSelStartExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" />
							:
							<html:text name="DrugAdministrationFB" property="sosSelStartExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()"  />
					</div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="sosSelEndExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" disabled="true"/>
						:
						<html:text name="DrugAdministrationFB" property="sosSelEndExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" disabled="true" />
					</div>
				</td>
				<td width="5%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="sosSelVolume" size="4" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" disabled="true"/>
						
					</div>
				</td>
				
				<td width="10%" class="tdfont" nowrap="nowrap" >
				<div align="center" >
							<html:select name="DrugAdministrationFB" property="selDrugBrandArraySOS"  tabindex="1"  value=""  style="width:80" onchange="setDrugBrandName(findIndex(this),false,true)"  > 
									<html:option value="">Select</html:option>
								</html:select>
						
				</div>
				<html:hidden name="DrugAdministrationFB" property="selDrugBrandNameSOS"></html:hidden>	
					</td>
					
				<td width="5%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="sosSelBatchNo" value="" size="10" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>
				<td width="7%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<his:date name="sosSelExpriryDate" dateFormate="%d-%b-%Y"  >
						</his:date>
					</div>
				</td>	
				<td width="10%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<html:text name="DrugAdministrationFB" property="sosSelRemarks" value="" size="10" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>	
				<td  class="tdfont" width="5%">
					<div align="left">  
						<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Component" title="Add Component" onkeypress="if(event.keyCode==13)if(true) AddRowToTable(validateRow(),'ADDDRUGEXEROW') ;" onclick="if(true)AddRowToTable(validateRow(),'ADDDRUGEXEROW')">
					</div>
				</td> 	
				</tr>
			<logic:notEmpty name="<%=InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST%>">	
			<logic:iterate id="drugAdminDtlVO" indexId="idx" name="<%=InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST%>" type="hisglobal.vo.DrugAdminDtlVO">	
				<tr>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="itemName"/>
						</div>
					</td>
					<td width="5%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="doseName"/>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="drugRouteName"/>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="administrationTime"/>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="administrationEndTime"/>
						</div>
					</td>
					<td width="5%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="volume"/>
						</div>
					</td>
					
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="drugBrandName"/>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="batchNo"/>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="expiryDate"/>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="drugAdminDtlVO" property="remarks"/>
						</div>
					</td>
					<td class="tdfont" width="5%" >
						<div align="left">
							<img name="minus" class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('DELETEDRUGEXEROW',findIndex(this)) ;" onclick=" deleteRow('DELETEDRUGEXEROW',findIndex(this))">
						</div>
					</td>	
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</logic:notEmpty>
	
	<div id="divSummarySOS" align="left" >
			<table width="100%">
			
				<bean:write name="DrugAdministrationFB" property="summary" filter="false"/>
			
			</table>
		</div>
	
	
<div id="divDrugDoseList" style="display: none; position: absolute;">
	<select name="drugDoseList" id="drugDoseList">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES%>">
			<logic:iterate id="voDrugDose" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES%>" type="hisglobal.vo.DrugDoseVO">
				<%String valDrugDose = voDrugDose.getDoseId()+"#"+voDrugDose.getItemTypeId(); %> 			
				<option value="<%=valDrugDose%>"> <%=voDrugDose.getDoseName()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>	
	

<div id="divDrugRouteList" style="display: none; position: absolute;">
	<select name="drugRouteList" id="drugRouteList"> 
		<logic:present name="<%=InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO%>">
			<logic:iterate id="voDrugRoute" name="<%=InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO%>" type="hisglobal.vo.DrugRouteMstVO">
				<%String valDrugRoute = voDrugRoute.getDrugRouteId()+"#"+voDrugRoute.getItemTypeId(); %> 			
				<option value="<%=valDrugRoute%>"> <%=voDrugRoute.getDrugRouteName()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>

<div id="divSOSDrugDoseList" style="display: none; position: absolute;">
	<select name="sosDrugDoseList" id="sosDrugDoseList">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES%>">
			<logic:iterate id="voDrugDose" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES%>" type="hisglobal.vo.DrugDoseVO">
				<%String valDrugDose = voDrugDose.getDoseId()+"#"+voDrugDose.getItemTypeId(); %> 			
				<option value="<%=valDrugDose%>"> <%=voDrugDose.getDoseName()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>	
	

<div id="divSOSDrugRouteList" style="display: none; position: absolute;">
	<select name="sosDrugRouteList" id="sosDrugRouteList">
		<logic:present name="<%=InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO%>">
			<logic:iterate id="voDrugRoute" name="<%=InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO%>" type="hisglobal.vo.DrugRouteMstVO">
				<%String valDrugRoute = voDrugRoute.getDrugRouteId()+"#"+voDrugRoute.getItemTypeId(); %> 			
				<option value="<%=valDrugRoute%>"> <%=voDrugRoute.getDrugRouteName()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>



<div id="divBatchNoList" style="display: none; position: absolute;">
	<select name="batchNoList" id="batchNoList">
		<logic:present name="<%=InpatientConfig.ESSENTIALS_LIST_BATCHNO_FROM_STORE%>">
			<logic:iterate id="entryObj" name="<%=InpatientConfig.ESSENTIALS_LIST_BATCHNO_FROM_STORE%>" type="hisglobal.utility.Entry">
				<option value="<%=entryObj.getValue()%>"> <%=entryObj.getLabel()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>


<div id="divDrugBrandList" style="display: none; position: absolute;">
	<select name="drugBrandList" id="drugBrandList">
		<logic:present name="<%=InpatientConfig.ESSENTIALS_LIST_BRANDLIST_FROM_GENERICTYPE%>">
			<logic:iterate id="entryObj" name="<%=InpatientConfig.ESSENTIALS_LIST_BRANDLIST_FROM_GENERICTYPE%>" type="hisglobal.utility.Entry">
				<option value="<%=entryObj.getValue()%>"> <%=entryObj.getLabel()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>


<logic:notEmpty name="<%=InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT%>">
<his:SubTitleTag name="Legends">
	<div align="right"><b>
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td width="70%"> </td>
				<td width="30%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font>
						<img src='/HIS/hisglobal/images/avai/arrow_down.gif' onclick="showLegends();">	
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font>
						<img src='/HIS/hisglobal/images/avai/arrow_up.gif' onclick="hideLegends();">
				</div>
				</td>			
			</tr>
		</table>
	</b></div>
</his:SubTitleTag>
<div id="legendId" style="display: none;">		
<table width="100%" cellpadding="0" cellspacing="1">
	<logic:iterate name="<%=InpatientConfig.IPD_DRUG_LIST_COLOR_CODIFICATION_INFO%>" id="colorCode" type="hisglobal.utility.Entry">
		<tr>
			<td width="20%" class="tdfont" nowrap="nowrap" >
						<label style="background-color: <%=colorCode.getValue()%>">&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<font color="#000000" size="2" >
							<%String lable= colorCode.getLabel().toLowerCase();%>
							<b>&nbsp;<%=lable.toLowerCase()%></b>
						</font>						
					</td>
		</tr>
	</logic:iterate>
	<tr>
		<td width="20%" class="tdfont" nowrap="nowrap" >
		<font color="#000000" size="2" >
							<img src="/HIS/hisglobal/images/alert_button.png" alt="Drug Alert" height="15px" width="15px">
							<b>This Drug has Alerts (Allergic, Duplicate,  Reactive, Contradictory, Pregnant Patient Reactive)</b>
						</font>	
		</tr>
</table>	
</div>
</logic:notEmpty>

	<his:ButtonToolBarTag>
		<%-- <logic:notEmpty name="<%=InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT%>">
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex='2' onclick =  "validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
		</logic:notEmpty>	
		 --%><img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex='2' onclick =  "validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	    <logic:notEmpty name="<%=InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT%>">    
	        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</logic:notEmpty>
	</his:ButtonToolBarTag>
	
	
	<!-- <div id="showDivALerts" style="display: none;">
	<td onclick="callMenu('/HISSupport/alertmanagement/alertDesk.cnt','Alert Desk')" width="57%">
		<img src="/HIS/hisglobal/images/alert_button.png" alt="Alert Desk" id="alertBellId" title="Alert Desk" height="20px" width="20px">
			<span style="display: inline;" class="alertCount" id="alertCountId" title="Alert Desk"><font color="#FFFF00" size="2"><b>(4)</b></font></span>
		</td>
	
	
	</div> -->
	<%-- <bean:define id="summaryId" name="DrugAdministrationFB" property="summary" type="java.lang.String"></bean:define> --%>
	
	<div id="divSummary" style="display:none; float:inherit; z-index:2; border-style:groove;border-radius:6px; position:absolute; top:97%; margin-left:8%; background:#E5E5E6; width:60%;" >
			<table width="100%">
			
				<bean:write name="DrugAdministrationFB" property="summary" filter="false"/>
			
			</table>
		</div>
		
		
		
		
		
	
<html:hidden name="DrugAdministrationFB" property="hmode"/>
<html:hidden name="DrugAdministrationFB" property="patCrNo"/>
<html:hidden name="DrugAdministrationFB" property="departmentUnitcode"/>
<html:hidden name="DrugAdministrationFB" property="summary" />
		
<html:hidden name="DrugAdministrationFB" property="episodeVisitNo"/>
<html:hidden name="DrugAdministrationFB" property="episodeCode"/>
<html:hidden name="DrugAdministrationFB" property="admissionNo"/>
<html:hidden name="DrugAdministrationFB" property="sysDate"/>
<html:hidden name="DrugAdministrationFB" property="admissionDate"/>
<html:hidden name="DrugAdministrationFB" property="index"/>
<html:hidden name="DrugAdministrationFB" property="sysHours"/>
<html:hidden name="DrugAdministrationFB" property="sysMinut"/>
<html:hidden name="DrugAdministrationFB" property="timeLimit"/>
<html:hidden name="DrugAdministrationFB" property="sosSelDoseId"/>





	