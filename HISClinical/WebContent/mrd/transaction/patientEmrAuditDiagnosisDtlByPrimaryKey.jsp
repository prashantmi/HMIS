
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.PatientEmrAuditFB"%>
<%@page import="mrd.vo.PatientEmrAuditVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>

<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.PatientEmrAuditFB"%>
<%@page import="java.util.*,mrd.*,hisglobal.presentation.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/mrd/js/patientEmrAudit.js" />

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/dynamicdesk/js/desk.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/commonDesigner.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/validation.js"> </script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/paginationTag.js"> </script>
<script type="text/javascript">
	
	
</script>
<body onload="getScanDoc()">
	
		<html:form action="/patientEmrAudit" >
		
		<his:TransactionContainer>
			<his:TitleTag name="Patient EMR Audit">
			</his:TitleTag>
			<%
				String 	sysDate=null;
				int startIndex=-1, endIndex=-1;
				
				try{
					sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				}catch(Exception e){
				 e.printStackTrace();
				}
		 	%>
		 	<input type="hidden" name="sysDate" value="<%=sysDate%>"/>
			<html:hidden name="patientEmrAuditFB" property="patCrNo"/>
			
			
				<his:statusList>
					<html:hidden name="patientEmrAuditFB" property="patCrNo"/>
					<bean:define id="patCrNoId2" name="patientEmrAuditFB" property="patCrNo" type="java.lang.String"></bean:define>
					<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true">
						<jsp:param value="<%=patCrNoId2 %>" name="patCrNo"/>
					</jsp:include>
					<div id="divRestPatDtl" style="display: block">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead" align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="visitDate"/>	
									</font>
								</td>
								<td width="25%" class="tdfont" align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="patientEmrAuditFB" property="strVisitDate"/>
									</font>	
								</td>
								<td width="25%" class="tdfonthead" align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dept/unit"/>	
									</font>
								</td>
								<td width="25%" class="tdfont" align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="patientEmrAuditFB" property="strDepartment"/>/<bean:write name="patientEmrAuditFB" property="strDepartmentUnit"/>
									</font>	
								</td>
							</tr>
								
							<tr>
								<td width="25%" class="tdfonthead" align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deoName"/>	
									</font>
								</td>
								<td width="25%" class="tdfont" align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="patientEmrAuditFB" property="strDEOName"/>
									</font>	
								</td>
								<td width="25%" class="tdfonthead" align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="entryDate"/></b>	
									</font>
								</td>
								<td width="25%" class="tdfont" align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="patientEmrAuditFB" property="strEntryDate"/>
									</font>	
								</td>
							</tr>
						</table>
					</div>
					<his:SubTitleTag name="Audit Type Wise Record">
						<img id="imgAuditTypeWiseRecordDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/> 
					</his:SubTitleTag>
					<his:ContentTag>
						<div id="divAuditTypeWiseRecordDtl" style="display: block;">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						
							<%
							 	List lstOfLstPatientEmrAudit=(List) session.getAttribute(MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_DIAL_TILE_BY_PRIMARY_KEY_LIST);
								
								List lstColumnLabel  = (List)lstOfLstPatientEmrAudit.get(0);
								int lstOfLstPatientEmrAuditLength=lstOfLstPatientEmrAudit.size();
								int lstColumnLabelLength=lstColumnLabel.size();
								String width=100/lstColumnLabelLength+"%";
								//endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
								
								
							%>  
							<tr>
								<%for(int i=0; i<lstColumnLabelLength;i++)
								{%>
									<td width="<%=width %>" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<%=lstColumnLabel.get(i) %>
												</b>	
											</font>
										</div>
									</td>
								<%} %>
							</tr>
							<%for(int i=1; i<lstOfLstPatientEmrAuditLength;i++)
							{
								List lstPatientEmrAudit	= (List) lstOfLstPatientEmrAudit.get(i);%>
								<tr>
									<%for(int j=0; j<lstColumnLabelLength;j++)
									{%>
										<td width="<%=width %>" class="tdfont">
											<div align="center">
												<%=lstPatientEmrAudit.get(j)%>
											</div>
										</td><%
									} %>
								</tr><% 
							} %>
						</table>
						</div>
						</his:ContentTag>
						<his:SubTitleTag name="Scan Document Detail">
							<img id="imgScanDocumentDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
						</his:SubTitleTag>
						<div id="divScanDocumentDtl" style="display: none">
						<his:ContentTag>
							<table>
							<tr>
								<td id="tdScannedDocs"  valign="top">
									<div id="divScanDocDtl" align="center"></div>
								</td>
							</tr>
							</table>
						</his:ContentTag>
						</div>
						<%-- <div id="divScanDocumentDtl" style="display: block">
						
							<bean:define id="patCrNoId" name="patientEmrAuditFB" property="patCrNo" type="java.lang.String"></bean:define>
							<bean:define id="strEpisodeCodeId" name="patientEmrAuditFB" property="strEpisodeCode" type="java.lang.String"></bean:define>
							<%try{%>
							<jsp:include page="/scanning/viewScannedTile.cnt" flush="false">
								<jsp:param value="<%=patCrNoId %>" name="patCrNo"/>
								<jsp:param value="<%=strEpisodeCodeId %>" name="episodeCode"/>
							</jsp:include>
							<%}catch(Exception e){e.printStackTrace();} %>
							
						</div> --%>
						
						<logic:present name="<%=MrdConfig.PATIENT_EMR_AUDIT_PREVIOUS_DTL_BY_PRIMARY_KEY_LIST %>">
						<logic:notEmpty name="<%=MrdConfig.PATIENT_EMR_AUDIT_PREVIOUS_DTL_BY_PRIMARY_KEY_LIST %>">
						<his:SubTitleTag name="Previous Audit Detail">
							<img id="imgPreviousAuditDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
						</his:SubTitleTag>
						<div id="divPreviousAuditDtl" style="display: none">
						<his:ContentTag>
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>	
									<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="auditDate"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="auditBy"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="auditStatus"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
							<%List lstPatEmrAuditPrevDtlByPrimKey= (List) session.getAttribute(MrdConfig.PATIENT_EMR_AUDIT_PREVIOUS_DTL_BY_PRIMARY_KEY_LIST); %>
							<%if(lstPatEmrAuditPrevDtlByPrimKey!=null && lstPatEmrAuditPrevDtlByPrimKey.size()>0){ %>
								<logic:iterate id="prevPatientEmrAuditVOId" name="<%=MrdConfig.PATIENT_EMR_AUDIT_PREVIOUS_DTL_BY_PRIMARY_KEY_LIST %>" type="mrd.vo.PatientEmrAuditVO">
									<tr>
										<td width="30%" class="tdfont">
											<div align="center">
												<%=prevPatientEmrAuditVOId.getStrPreviousAuditDate()%>
											</div>
										</td>
										<td width="40%" class="tdfont">
											<div align="center">
												<%=prevPatientEmrAuditVOId.getStrPreviousAuditBy()%>
											</div>
										</td>
										<td width="30%" class="tdfont">
											<div align="center">
												<%=prevPatientEmrAuditVOId.getStrPreviousAuditStatus()%>
											</div>
										</td>
									</tr>
								</logic:iterate>
							<%}else{%>
								<tr>
									<td width="100%" class="tdfont" colspan="3">
											<div align="center">
												No Previous Audit Detail Found
											</div>
										</td>
								</tr>
							<% }%>
							</table>
						</his:ContentTag>	
						</div>
						</logic:notEmpty>
						</logic:present>
						
						<his:SubTitleTag name="Audit Detail">
							<img id="imgAuditDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
						</his:SubTitleTag>
						<div id="divAuditDtl" style="display: block">
						<his:ContentTag>
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td width="50%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
												<font color="#FF0000" size="2">*</font><bean:message key="auditStatus" />&nbsp; </font>
										</div>
									</td>
									<td width="50%" class="tdfont">
										<div align="left">
											<html:select name="patientEmrAuditFB" property="strAuditStatusId" styleClass="regcbo" onchange="setEmrAuditUserList()">
												<html:option value="-1">Select Value</html:option>
												<html:option value="0">No data found in scan document</html:option>
												<html:option value="1">No-Error</html:option>
												<html:option value="2">Minor Error</html:option>
												<html:option value="3">Major Error</html:option>
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
												<font color="#FF0000" size="2">*</font><bean:message key="remarks" />&nbsp; </font>
										</div>
									</td>
									<td width="50%" class="tdfont">
										<div align="left">
											<html:textarea name="patientEmrAuditFB" property="strRemark"></html:textarea>
										</div>
									</td>
								</tr>
							</table>
						</his:ContentTag>
					</div>
				</his:statusList>
				
				<his:ButtonToolBarTag>
		
					<his:statusList>
						<div id="showCancelOnly" style="display: block;">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'   style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" resetForm(0)" onkeypress="if(event.keyCode==13) resetForm(0)">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitMode('CANCEL')" onkeypress="if(event.keyCode==13) submitMode('CANCEL')">
						</div>
					</his:statusList>
				</his:ButtonToolBarTag>
					
			</his:TransactionContainer>
			
			<html:hidden name="patientEmrAuditFB" property="hmode"/>
			<html:hidden name="patientEmrAuditFB" property="strFromDate"/>
			<html:hidden name="patientEmrAuditFB" property="strToDate"/>
			<html:hidden name="patientEmrAuditFB" property="strEmrAuditTypeId"/>
			<html:hidden name="patientEmrAuditFB" property="strEpisodeCode"/>
			<html:hidden name="patientEmrAuditFB" property="strAdmissionNo"/>
			<html:hidden name="patientEmrAuditFB" property="strVisitNo"/>
			<html:hidden name="patientEmrAuditFB" property="strDEOSeatId"/>
			<html:hidden name="patientEmrAuditFB" property="strDeoDateTime"/>
			<html:hidden name="patientEmrAuditFB" property="strEmrAuditUserId"/>
			<html:hidden name="patientEmrAuditFB" property="strVisitDate"/>
			<html:hidden name="patientEmrAuditFB" property="strSearchPatCrNo"/>
			<html:hidden name="patientEmrAuditFB" property="strDEOName"/>
		</html:form>

</body>
</html>