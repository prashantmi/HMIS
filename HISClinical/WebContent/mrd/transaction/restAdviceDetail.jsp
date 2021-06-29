<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
		<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST%>">
		<his:SubTitleTag name="Previous Rest Advice Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		<table id="prevRestDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="reason" />
							</b>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="endday" />
							</b>
						</font>
					</div>
				</td>
				
			</tr>
			<logic:iterate id="restDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST%>" type="hisglobal.vo.RestAdviceDtlVO">
			<tr>
				<td width="33%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="restDtlVO" property="restReason"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="33%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="restDtlVO" property="startDate"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:write name="restDtlVO" property="endDate"/>
							</b>
						</font>		
					</div>
				</td>
				
			</tr>
			</logic:iterate>
			</table>
			</his:ContentTag>
			</logic:notEmpty>
			<his:SubTitleTag name="Rest Advice Detail">
			</his:SubTitleTag>
			<his:ContentTag>
			<table id="restAdviceTableId" width="100%" border="0" cellspacing="1" cellpadding="0" >
			<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST%>">
				<logic:iterate id="restDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST%>" type="hisglobal.vo.RestAdviceDtlVO">	
				<tr>
					<td width="10%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="left">
							<html:text name="PatientTreatmentDetailFB" property="restDays" onkeypress="return validateNumberWithoutZeroOnly(event)" maxlength="2" size="4" value="<%=restDtlVO.getDays() %>" tabindex="1"></html:text>
						</div>
					</td>
					
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="from" />
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" nowrap="nowrap" >
						<div align="left">
							<html:text name="PatientTreatmentDetailFB" property="restStartDate" maxlength="100" size="12" readonly="true" value="<%=restDtlVO.getStartDate()%>" tabindex="1"></html:text>
						</div>
					 </td>
									
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="reason" />
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
						<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">	
							<html:textarea name="PatientTreatmentDetailFB" property="restReason" rows="3" cols="69" value="<%=restDtlVO.getRestReason() %>" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:textarea>
							<html:hidden name="PatientTreatmentDetailFB" property="restSerialNo" value="<%=restDtlVO.getSerialNo() %>"/>
						</font>
						</div>
					</td>
					
				</tr>
											
			 </logic:iterate>	
			 </logic:notEmpty>
			 <logic:empty name="<%=OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST%>">
			 	<tr>
					<td width="10%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="left">
							<html:text name="PatientTreatmentDetailFB" property="restDays" onkeypress="return validateNumberWithoutZeroOnly(event)" maxlength="2" size="4" tabindex="1"></html:text>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="from" />
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" nowrap="nowrap" >
						<div align="left">
							<html:text name="PatientTreatmentDetailFB" property="restStartDate" maxlength="100" size="12" readonly="true" tabindex="1"></html:text>
						</div>
					 </td>
				</tr>
			 	<tr>
			 		<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="reason" />
								</b>
							</font>
						</div>
					</td>
					<td class="tdfont" colspan="3">
						<div align="left">
						<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
							<html:textarea name="PatientTreatmentDetailFB" property="restReason" rows="3" cols="69" tabindex="1"></html:textarea>
							<html:hidden name="PatientTreatmentDetailFB" property="restSerialNo" value=""/>
						</font>
						</div>
					</td>
					</tr>
			 	
			 	
			 	
					
			</logic:empty>	
			</table>
			</his:ContentTag>
</body>
</html>