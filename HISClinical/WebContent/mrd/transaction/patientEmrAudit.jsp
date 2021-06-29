<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.PatientEmrAuditFB"%>
<%@page import="mrd.vo.PatientEmrAuditVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
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
	function openPopup(url,eventObj, height, width)
	{
	 	// alert("eventObj.type "+eventObj.type)
	 	if(validate1()){
	 		if(eventObj.type=="click" || eventObj.keyCode==13 )
	 		 {
	 		  //ert("url "+url)
	 		   	var child = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
	 		  	child.moveTo(250,250);
	 		 	child.focus(); 
	 		
	 		if(!child.opener)
	 		   child.opener = self;
	 		 }
	 		 return child;
	 	}else{
	 		return false;
	 	}
		
	}	
	function doPagination(e,p)
	{
		//alert("insde doPagination");
		document.getElementsByName('currentPage')[0].value=p;
		var radioClick =document.getElementsByName('strPrimaryKey');
			document.getElementsByName('hmode')[0].value='PAGINATION';
		document.forms[0].submit();
	}
	function validateCRNoCHECK(size)
	{
		var valid=true;
		var toValidate=false;
		var patCrNo1="101"+document.getElementsByName('sysDate')[0].value.split("-")[2].substring(2);
		//alert("patCrNo1 :"+patCrNo1);
		if(validate1()){
			if(document.getElementsByName('patCrNo')[0].value!="" && document.getElementsByName('patCrNo')[0].value!=patCrNo1){
				toValidate=true;
				//alert("toValidate :"+toValidate);			
			}
			//alert("toValidate :"+toValidate);		
			if(toValidate)
			{
				if(validateMinLength(document.getElementsByName('patCrNo')[0],size))
				{
					if(validateCheckSumBySize(size))
					{
						valid=true
					//	alert("valid"+valid)
					}
					else
					{	
					//alert("invalid Cr no")
						valid=false;
					}
				// 	alert("valid 123456 "+valid)
				}else{
					valid=false;
					alert("invalid Cr no");
				}
					
			}
			//alert("valid :"+valid);
			return valid;
		}else{
			return false;
		}
		
	} 
	  function submitFormOnValidate(flag,mode)
	 {
	 	//alert("flag 123456 "+flag+" mode 123456 "+mode);
	 	if(flag)
	 	{
	 	 //alert("inside if");
	 	 	submitMode(mode);	 	 	
	 	}
	 	else{
//	  	alert("elesee")
	 	return false;
	 	}
	 	
	 } 
	  
	  
</script>
<body>
	
		<html:form action="/patientEmrAudit">
		
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
			<his:statusNew>
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<logic:notEqual name="patientEmrAuditFB" property="hmode" value="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
											<font color="#FF0000" size="2">*</font><bean:message key="auditType" />&nbsp; </font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="patientEmrAuditFB" property="strEmrAuditTypeId" styleClass="regcbo" onchange="setEmrAuditUserList()">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.PATIENT_EMR_AUDIT_TYPE_LIST %>">
												<html:options collection="<%=MrdConfig.PATIENT_EMR_AUDIT_TYPE_LIST%>" labelProperty="label" property="value" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
											<font color="#FF0000" size="2">*</font><bean:message key="lsitingType" />&nbsp; </font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:radio name="patientEmrAuditFB" property="strLitingType" value="0" onclick="toggleOption(this)">Patient-Wise</html:radio>
										<html:radio name="patientEmrAuditFB" property="strLitingType" value="1" onclick="toggleOption(this)">User-Wise</html:radio>
									</div>
								</td>
							</tr>
							
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000" size="2">*</font>
											<bean:message key="fromDate"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<his:date name="strFromDate" dateFormate="%d-%b-%Y" onchange="" value="<%=sysDate %>"></his:date>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000" size="2">*</font>
											<bean:message key="toDate"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<his:date name="strToDate" dateFormate="%d-%b-%Y" onchange="" value="<%=sysDate %>"></his:date>
									</div>
								</td>
							</tr>
						</logic:notEqual>
					</table>
					<his:SubTitleTag name="">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr><td width="65%"></td></tr>
						</table>
					</his:SubTitleTag>
					<div id="patientWiseDivId" style="display: block">
						<his:InputCrNoTag name="patientEmrAuditFB"></his:InputCrNoTag>
						<%-- <logic:empty name="patientEmrAuditFB" property="patCrNo" >
							<his:InputCrNoTag name="patientEmrAuditFB"></his:InputCrNoTag>
						</logic:empty> --%>
					</div>
					
					<div id="userWiseDivId" style="display: none">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="auditUser" />&nbsp; </font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div id="emrAuditUserDivId" align="left" style="display: block">
										<html:select name="patientEmrAuditFB" property="strEmrAuditUserId" styleClass="regcbo" onchange="">
											<html:option value="-1">Select Value</html:option>
										</html:select>
										<html:hidden name="patientEmrAuditFB" property="strEmrAuditUserName"/>
									</div>
								</td>
								<td width="25%" class="tdfont" ><img class="button" align="left" src='/HISClinical/hisglobal/images/GO.png' tabindex="1" style="cursor: pointer;" onclick="validate2();"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
					</div>
					</his:ContentTag>
					</his:statusNew>
					<his:statusList>
						<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<logic:notEqual name="patientEmrAuditFB" property="hmode" value="0">
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
													<font color="#FF0000" size="2">*</font><bean:message key="auditType" />&nbsp; </font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<html:select name="patientEmrAuditFB" property="strEmrAuditTypeId" styleClass="regcbo" onchange="setEmrAuditUserList()" disabled="true">
													<html:option value="-1">Select Value</html:option>
													<logic:present name="<%=MrdConfig.PATIENT_EMR_AUDIT_TYPE_LIST %>">
														<html:options collection="<%=MrdConfig.PATIENT_EMR_AUDIT_TYPE_LIST%>" labelProperty="label" property="value" />
													</logic:present>
												</html:select>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
													<font color="#FF0000" size="2">*</font><bean:message key="lsitingType" />&nbsp; </font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<html:radio name="patientEmrAuditFB" property="strLitingType" value="0" disabled="true">Patient-Wise</html:radio>
												<html:radio name="patientEmrAuditFB" property="strLitingType" value="1" disabled="true">User-Wise</html:radio>
											</div>
										</td>
									</tr>
									
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000" size="2">*</font>
													<bean:message key="fromDate"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<bean:write name="patientEmrAuditFB" property="strFromDate"/>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000" size="2">*</font>
													<bean:message key="toDate"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<bean:write name="patientEmrAuditFB" property="strToDate"/>
											</div>
										</td>
									</tr>
								</logic:notEqual>
							</table>
							</his:ContentTag>
							
						<% 	PaginationFB fbPage= new PaginationFB();
							int nMaxRecord=10;
							pageContext.setAttribute("fbPagination",fbPage);
							fbPage.setCurrentPage(((PatientEmrAuditFB)request.getAttribute("patientEmrAuditFB")).getCurrentPage());
							fbPage.setObjArrName(MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST);
							fbPage.setAppendInTitle("Patient EMR Detail");
							fbPage.setMaxRecords(nMaxRecord);
						%>
						

						<his:PaginationTag name="fbPagination"></his:PaginationTag>
						<his:ContentTag>
						
							
							
							
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							
								  
								<tr>
									<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="select"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="13%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="crNo"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="patientName"/>											
												</b>	
											</font>
										</div>
									</td>
									<td width="8%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="age/sex"/>											
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="visitDate"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="dept/unit"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="auditStatus"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="auditType"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="deoName"/>
												</b>	
											</font>
										</div>
									</td>
									<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													<bean:message key="entryDate"/>
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<%-- <logic:present name="<%=MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST%>" > --%>
								<%
								 	List patientEmrAuditVos=(List) session.getAttribute(MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST);
									if(patientEmrAuditVos!=null && patientEmrAuditVos.size()>0)
									{
										startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
										endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
										
										for(int j=startIndex;j<=endIndex;j++)
										{
											PatientEmrAuditVO patientEmrAuditVO=(PatientEmrAuditVO)patientEmrAuditVos.get(j);
											int i=j-startIndex;%>
								
											<%-- <logic:iterate indexId="idx" id="patientEmrAuditVO" name="<%=MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST%>" type="mrd.vo.PatientEmrAuditVO"> --%>
											<tr>
												<td width="5%" class="tdfont">
													<div align="center">
														<bean:define id="currentPageId" name="patientEmrAuditFB" property="currentPage" type="java.lang.Integer"></bean:define>
														<%
														int nIndex=(nMaxRecord*(currentPageId-1))+i;
														String method1="showPatientEmrDialTile('"+nIndex +"')" ; %>
														<html:radio name="patientEmrAuditFB" property="strPrimaryKeyIndex" value='<%=(nIndex+"")%>' onclick='<%=method1 %>' tabindex="1" ></html:radio>
														<html:hidden name="patientEmrAuditFB"  property="strPrimaryKey" value="<%=patientEmrAuditVO.getStrPrimaryKey() %>"/>
														
													</div>
												</td>
												<td width="13%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getPatCrNo()%>
														</font>	
													</div>
												</td>
												<td width="12%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%= patientEmrAuditVO.getStrPatientName() %>																								
														</font>	
													</div>
												</td>
												<td width="8%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getStrAgeSex()%>
														</font>	
													</div>
												</td>
												<td width="10%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getStrVisitDate() %>
														</font>	
													</div>
												</td>
												<td width="12%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%patientEmrAuditVO.setStrDeptAndUnit(); %> <%=patientEmrAuditVO.getStrDeptAndUnit()%>
														</font>	
													</div>
												</td>
												<td width="10%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getStrAuditStatus()%>
														</font>	
													</div>
												</td>
												<td width="10%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getStrAuditTypeName()%>
														</font>	
													</div>
												</td>
												<td width="10%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getStrDEOName()%>
														</font>	
													</div>
												</td>
												<td width="10%" class="tdfont">
													<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<%=patientEmrAuditVO.getStrEntryDate()%>
														</font>	
													</div>
												</td>										
											</tr><%
										}
									}else{%>
										<tr>
											<td width="100%" class="tdfont" colspan="10">
												<div align="center">
													<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														No Record Found
													</font>	
												</div>
											</td>
										</tr><% 
									}%>
								<%-- </logic:present> --%>
							</table>
						</his:ContentTag>
						<html:hidden name="patientEmrAuditFB" property="patCrNo"/>
						<html:hidden name="patientEmrAuditFB" property="strFromDate"/>
						<html:hidden name="patientEmrAuditFB" property="strToDate"/>
						<html:hidden name="patientEmrAuditFB" property="strEmrAuditTypeId"/>
						<html:hidden name="patientEmrAuditFB" property="strEpisodeCode"/>
						<html:hidden name="patientEmrAuditFB" property="strAdmissionNo"/>
						<html:hidden name="patientEmrAuditFB" property="strVisitNo"/>
						<html:hidden name="patientEmrAuditFB" property="strDEOSeatId"/>
						<html:hidden name="patientEmrAuditFB" property="strDeoDateTime"/>
						<html:hidden name="patientEmrAuditFB" property="strVisitDate"/>
						<html:hidden name="patientEmrAuditFB" property="strSearchPatCrNo"/>
						<html:hidden name="patientEmrAuditFB" property="strDEOName"/>
						
					</his:statusList>
					
					<his:ButtonToolBarTag>
			
						<his:statusList>
							<div id="showCancelOnly" style="display: block;">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="resetForm('1')" onkeypress="if(event.keyCode==13) resetForm('1')">
							</div>
						</his:statusList>
						
						<his:statusNew>
							<div id="showCancelOnly" style="display: block;">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitMode('FINALCANCEL')" onkeypress="if(event.keyCode==13) submitMode('CANCEL')">								
							</div>
							<div id="showCancelGO" style="display: none;">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitMode('FINALCANCEL')" onkeypress="if(event.keyCode==13) submitMode('CANCEL')">
								
							</div>
							
						</his:statusNew>
						
						
						
						<his:statusUnsuccessfull>
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
						</his:statusUnsuccessfull>
						
						<his:statusTransactionInProcess>
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
						</his:statusTransactionInProcess>
					</his:ButtonToolBarTag>
					
					<his:status/>
					
			</his:TransactionContainer>
					
				
				
			
			<html:hidden name="patientEmrAuditFB" property="hmode"/>
			<html:hidden name="patientEmrAuditFB" property="currentPage"/>
			<%-- <html:hidden name="patientEmrAuditFB" property="startIndex" value="<%=Integer.toString(startIndex)%>" />
	  		<html:hidden name="patientEmrAuditFB" property="endIndex" value="<%=Integer.toString(endIndex)%>"/> --%>
			
			
			
			
		</html:form>

</body>
</html>