<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateNextVisitDetail()
{
	return true;
}
</script>

<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + menuURL; %>
<his:TitleTag key="patientProfile" width="100%">
</his:TitleTag>

<his:statusTransactionInProcess>

<his:SubTitleTag key="nextvisitdetail">
</his:SubTitleTag>

<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_NEXTVISITDETAIL_EPISODEVO%>" >
	<%
		EpisodeVO episodeVO = (EpisodeVO)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_NEXTVISITDETAIL_EPISODEVO);
	%>	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="20%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="nextVisitDate"/>&nbsp; </b>
						</font>
					</div>
				</td>
				<td width="80%"  class="tdfont">
					<div align="left">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b> <%=(episodeVO.getEpisodeNextVisitDate()!=null)?episodeVO.getEpisodeNextVisitDate():""%> </b>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="20%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="visit"/> <bean:message key="summary"/>&nbsp;</b>
						</font>
					</div>
				</td>
				<td width="80%"  class="tdfont">
					<div align="left">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(episodeVO.getComplainDetail()!=null)?episodeVO.getComplainDetail():""%>
						</font>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</logic:notEmpty>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateNextVisitDetail(),'<%=targetHmode%>');" onclick="submitFormOnValidate(validateNextVisitDetail(),'<%=targetHmode%>');" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm21('PROFILEOPTIONS');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('<%=menuURL%>')" onkeypress="if(event.keyCode==13) submitForm21('<%=menuURL%>')">
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>

<html:hidden name="GenericPatientProfileFB" property="hmode" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="userLevel" />
<html:hidden name="GenericPatientProfileFB" property="remarks" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenu" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />