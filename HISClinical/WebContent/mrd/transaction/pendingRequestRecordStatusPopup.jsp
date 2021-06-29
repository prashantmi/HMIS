<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	self.close();
}

function deleteRejectedRequest()
{
	opener.document.getElementsByName("hmode")[0].value='DELREJREC';
	opener.document.getElementsByName("requestId")[0].value=document.getElementsByName("requestId")[0].value;
	opener.document.forms[0].submit();
	self.close();
}
function displayProfileFile(path)
{
	//alert(path)
	window.open(createFHashAjaxQuery(path),'DisplayFile','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+ ',left=10,top=10,,dependent=yes,resizable=yes');
}


function getUploadedeDoc(event,docId)
{
	//alert("Hello OPEN Doc")
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open(createFHashAjaxQuery("/HISClinical/mrd/mrdDocumentUpload.cnt?hmode=VIEWDOC&documentCode="+docId),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}

</script>

<html:form action="/onlineMrdRecordRequest">
	<body>  
		<his:SubTitleTag name="Record Detail">
		</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="crNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="patient"/> <bean:message key="name"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<logic:equal name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<bean:message key="fileNo"/>
										</logic:equal>
										<logic:notEqual name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<bean:message key="admNo"/>
										</logic:notEqual>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="recordType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="purpose"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="status"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="casesheet"/>  <bean:message key="url"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="reject"/>
										<bean:message key="reason"/>
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate id="recordVO" name="<%=MrdConfig.ONLINE_PENDING_REQUEST_STATUS_DETAIL %>" type="hisglobal.vo.RequestRecordDtlVO"> 
						<tr>	
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=recordVO.getPatCrNo() %>
									</font>
								</div>
							</td>
							<td width="20%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=recordVO.getPatName() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<logic:equal name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<%=recordVO.getFileNo() %>
										</logic:equal>	
										<logic:notEqual name="OnlineMrdRecordReqDtlFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<%=recordVO.getPatAdmNo() %>
										</logic:notEqual>
										
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=recordVO.getRecordTypeName() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=recordVO.getPurpose() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%String status=MrdConfig.REQUESTED_RECORD_STATUS_ARRAY[Integer.parseInt(recordVO.getReqStatus())];%>
									<%=status %>
										<%-- <%String str="Returned";%>
										<%if(recordVO.getReturnDate()!=null){%>
											<%=str %>
										<%}else {%>
										 <%=MrdConfig.MRD_RECORD_REQUEST_STATUS_ARRAY[Integer.parseInt(recordVO.getReqStatus())] %> 
										<%} %>	 --%>
									</font>
									<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">	
										<%if(recordVO.getReqStatus().equals(MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT))
										{
											if(recordVO.getReturnDate()!=null){%>
												(<%=recordVO.getReturnDate() %>)
											<%} %>	
												
										<%} else { 
										if(recordVO.getIssueDate() != null){%>
											( <%=recordVO.getIssueDate() %>) 
											<% }}%>
										
									</font>
								</div>
							</td>
							<td width="20%" class="tdfontheadExam" >
								<%-- <div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%if(recordVO.getProfileId()!=null) {%>
										<%String urlProfile="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId="+recordVO.getProfileId();%>
											<b><a style="cursor: pointer" onclick="displayProfileFile('<%=urlProfile%>');">CaseSheet URL</a></b>
											<%} else {} %>
									</font>
								</div> --%>
								
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%if(recordVO.getFileNo()!=null) {%>
											<b><a style="cursor: pointer" onclick="getUploadedeDoc(event,<%=recordVO.getFileNo()%>);">CaseSheet URL</a></b>
											<%} else {} %>
									</font>
								</div> 
								
								
							</td>
							<td width="20%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=recordVO.getCancelReason()==null?"-":recordVO.getCancelReason() %>
									</font>
								</div>
							</td>
							
						</tr>	
					</logic:iterate>
				</table>
			</his:ContentTag>		
		
		<logic:equal name="OnlineMrdRecordReqDtlFB" property="reqStatus" value="<%=MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT %>">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="request"/>
										<bean:message key="reject"/>
										<bean:message key="reason"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="75%" class="tdfontheadExam">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:textarea name="OnlineMrdRecordReqDtlFB" property="rejectReason" rows="3" cols="90" readonly="true"></html:textarea>
								</font>	
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
		</logic:equal>
		
		<his:ButtonToolBarTag>
			<logic:equal name="OnlineMrdRecordReqDtlFB" property="reqStatus" value="<%=MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT %>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-del.png"/>'  style=cursor:pointer tabindex="1" onclick =" deleteRejectedRequest()" onkeypress="if(event.keyCode==13)deleteRejectedRequest()">
			</logic:equal>
										
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" closeForm()" onkeypress="if(event.keyCode==13)closeForm()">
		</his:ButtonToolBarTag>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="reqStatus"/>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="requestId"/>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="recordType"/>
		 
	</body>
	<his:status/>
</html:form>
	