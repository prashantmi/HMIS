<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.vo.PatAllergyDtlVO"%>
<%@page import="hisglobal.vo.PatientAlertsDetailVO"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">


</script>

<his:TransactionContainer>
	<his:TitleTag name="Patient Medical Record">
		
	</his:TitleTag>

		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
		
		<his:SubTitleTag name="Patient Allergy Detail">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
			<%if(session.getAttribute(InpatientConfig.PATIENT_CURRENT_ALLERGY_VO_ARR)!=null){ %>
				<tr>
					<td class="tdfonthead" width="30%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="allergyType"/>
								</b>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="40%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="duration"/>
								</b>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="40%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="allergyName"/>
								</b>	
							</font>	
						</div>
					</td>
				</tr>
				<%PatAllergyDtlVO[] arrPatAllergyDtlVO=(PatAllergyDtlVO[])session.getAttribute(InpatientConfig.PATIENT_CURRENT_ALLERGY_VO_ARR); 
					
						for(int i=0;i<arrPatAllergyDtlVO.length;i++)
						{
				%>
					<tr>
						<td class="tdfont" width="30%">
							<div align="center">
								<%=arrPatAllergyDtlVO[i].getAllergyTypeName() %>
							</div>
						</td>
						<td class="tdfont" width="40%">
							<div align="center">
								<%=arrPatAllergyDtlVO[i].getDurationDays() %>
							</div>
						</td>
						<td class="tdfont" width="40%">
							<div align="center">
								<%=arrPatAllergyDtlVO[i].getAllergyName() %>
							</div>
						</td>
					</tr>
				<%}}else{ %>
					<tr>
						<td width="30%" colspan="3" class="tdfonthead">
							<div align="center">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>Patient Has No Allergy</b>
								</font>
							</div>
						</td>
					</tr>
					
				<%} %>
			</table>	
		</his:ContentTag>
		
		<his:SubTitleTag name="Patient Chronic Disease Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
			<%if(session.getAttribute(InpatientConfig.PATIENT_CURRENT_CHRONIC_DISEASE_VO_ARR)!=null){ %>
				<tr>
					<td class="tdfonthead" width="30%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="chronicDisease"/>
								</b>	
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="70%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="duration"/>
								</b>	
							</font>	
						</div>
					</td>
				</tr>
				<%PatientAlertsDetailVO[] arrPatAssignedAlertVO=(PatientAlertsDetailVO[])session.getAttribute(InpatientConfig.PATIENT_CURRENT_CHRONIC_DISEASE_VO_ARR); 
					
						for(int i=0;i<arrPatAssignedAlertVO.length;i++)
						{
				%>
					<tr>
						<td class="tdfont" width="30%">
							<div align="center">
								<%=arrPatAssignedAlertVO[i].getAlertName() %>
							</div>
						</td>
						<td class="tdfont" width="70%">
							<div align="center">
								<%=arrPatAssignedAlertVO[i].getDurationDays() %>
							</div>
						</td>
						
					</tr>
				<%}}else{ %>
					<tr>
						<td width="30%" colspan="3" class="tdfonthead">
							<div align="center">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>Patient Has No Chronic Disease.</b>
								</font>
							</div>
						</td>
					</tr>
					
				<%} %>
			</table>	
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:ButtonToolBarTag>

</his:TransactionContainer>		

	<html:hidden name="PatientMedicalRecordFB" property="hmode"/>