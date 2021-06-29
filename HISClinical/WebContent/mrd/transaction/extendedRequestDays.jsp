<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.RequestRecordDtlVO"%>
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
function validateSave()
{
	if(document.getElementsByName("extendDays")[0].value == "")
		{
			alert("enter no of days to extend");
			return false;
		}
	else if(document.getElementsByName("extendReason")[0].value == "")
		{
			alert("enter reason to extend no of days");
			return false;
		}
	else
		{
			return true;
		}
}

function saveExtendays()
{
	if(!validateSave())
		{
			return flase;
		}
	else
		{
			submitForm("SAVE_EXTEND_DAYS");
			self.close();
		}
	}

</script>
<%-- <% RequestRecordDtlVO RequestRecordDtlVO = (RequestRecordDtlVO)session.getAttribute(MrdConfig.EXTENDED_REQUEST_DAYS); %> --%>
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
											<bean:message key="for"/>
											<bean:message key="days"/>
										</b>	
									</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
										<b>
											<bean:message key="extending"/>  <bean:message key="days"/>
											
										</b>	
									</font>
									</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
									<b>
										<bean:message key="extending"/>  <bean:message key="reason"/>
										
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
										<bean:write  name="OnlineMrdRecordReqDtlFB" property="forDays"></bean:write>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<html:text name="OnlineMrdRecordReqDtlFB" property="extendDays" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1" value="<%=(recordVO.getExtendDays() == null) ? \"\" : recordVO.getExtendDays() %>"></html:text>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<html:textarea name="OnlineMrdRecordReqDtlFB" property="extendReason" rows="2" cols="20" tabindex="1" onkeypress="return CheckMaxLength(event,this,500,1)"/>
								</div>
							</td>
						</tr>	
				</logic:iterate>
				</table>
			</his:ContentTag>		
		
		
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="saveExtendays()" onkeypress="if(event.keyCode==13)saveExtendays()">
				
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" closeForm()" onkeypress="if(event.keyCode==13)closeForm()">
		</his:ButtonToolBarTag>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="reqStatus"/>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="requestId"/>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="recordType"/>
		 <html:hidden name="OnlineMrdRecordReqDtlFB" property="forDays"/>
		  <html:hidden name="OnlineMrdRecordReqDtlFB" property="extendDays"/>
		  <html:hidden name="OnlineMrdRecordReqDtlFB" property="hmode"/>
		 
	</body>
	<his:status/>
</html:form>
	