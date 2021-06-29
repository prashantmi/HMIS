
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@ page import="java.util.*"%>
<%@ page import="hisglobal.hisconfig.Config,hisglobal.vo.*"%>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

	function validateSelectedRadio(obj)
	{
		document.getElementsByName("popupRemark")[0].value=obj.value;
		document.getElementById("remarkLabel").style.display="block";
	}

</script>


<html:form action="/patientProfileReviewDtl">
	<body>
	
	<his:TitleTag name="Previous Review Detail">
	</his:TitleTag>
			<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
						
					<td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="profileHeader" />
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;<bean:write name="PatientProfileReviewFB" property="profileHeader"/></b>
						</font>
					</div>
					</td>
					
					<td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="profileType" />
							</font>
						</div>
					</td>
						<td width="20%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:write name="PatientProfileReviewFB" property="profileTypeDesc"/></b>
							</font>
						</div>
						</td>
						</tr>
						<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="creationDate" />
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:write name="PatientProfileReviewFB" property="creationDate"/></b>
							</font>
						</div>
						</td>
						<td width="20%" class="tdfonthead">
						
						</td>
						<td width="20%" class="tdfonthead">
						
						</td>
				</tr>
				
				</table>
				</his:ContentTag>
	<his:ContentTag>
	<logic:present name="<%=OpdConfig.PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_REVIEW_DTL_VO_ARRAY%>">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="sel" />
						</font>
					</div>
				</td>
				<td width="45%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="reviewDate" />
						</font>
					</div>
				</td>
				<td width="50%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="reviewBy" />
						</font>
					</div>
				</td>
			</tr>
			<logic:iterate id="profileReviewDtlVO" name="<%=OpdConfig.PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_REVIEW_DTL_VO_ARRAY%>" indexId="idx" type="hisglobal.vo.ProfileReviewDtlVO">
			<tr>
				<td width="5%" class="tdfont">
					<div align="center">
					<html:radio name="PatientProfileReviewFB" property="selectedRadio" value="<%=profileReviewDtlVO.getRemarks() %>" tabindex="1" onclick="validateSelectedRadio(this)"></html:radio>
					</div>
				</td>

				
				<td width="45%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="profileReviewDtlVO" property="reviewTime" /></b>
						</font>
					</div>
				</td>
				
				<td width="50%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="profileReviewDtlVO" property="empName" /></b>
						</font>
					</div>
				</td>
			</tr>
		</logic:iterate>
			
							
		</table>
		</logic:present>
		
	</his:ContentTag>		
	<his:ContentTag>
	<table width="100%" cellpadding="0" cellspacing="1" id="remarkLabel" style="display: none;">
				<tr>
								<td width="5%" class="tdfonthead" colspan="1" nowrap="nowrap">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="reviewRemark" /></b>
										</font>
									</div>
								</td>
								<td width="95%" class="tdfont" colspan="2">
									<div align="left">
									<html:textarea name="PatientProfileReviewFB"  property="popupRemark" readonly="true" value="" tabindex="1" rows="5" cols="120" onkeypress="return (validateTextArea(event,this,'1000'))"></html:textarea>
									</div>
								</td>
							</tr>		
		</table>
	
	</his:ContentTag>
					
	</body>
	
	<his:ButtonToolBarTag>
		
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="self.close()" onkeypress="if(event.keyCode==13) self.close()">
	</his:ButtonToolBarTag>
	
	<his:status/>
	<html:hidden name="PatientProfileReviewFB" property="profileHeader"/>
	<html:hidden name="PatientProfileReviewFB" property="profileTypeDesc"/>
	<html:hidden name="PatientProfileReviewFB" property="creationDate"/>
	</html:form>
	
	