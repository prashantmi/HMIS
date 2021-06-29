<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<his:javascript src="/opd/js/generic_patient_profile.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateDiagnosis()
{
	var chks = document.getElementsByName('selectedRow');
	var flag = false;
	for(var i=0;i<chks.length;i++)
		if(chks[i].checked)
		{
			flag = true;
			break;
		}
	if(!flag)
	{
		alert("Select At least One Diagnosis for Profile .. ");
		chks[i].focus();
		return false;
	} 
	return true;
}

function callThisOnload()
{
		var str=document.getElementsByName("treatmentCheckFlag")[0].value;

	    // alert(">>>>>."+str)
		var arr=str.split("#");
		
		var chks=document.getElementsByName('selectedRow');
		//alert(chks.length)
		for(var i=0;i<chks.length;i++)
		
		{
			//alert(arr[i]);
			
			if(arr[i]==<%=OpdConfig.PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES%>)
			{
			//alert("inisde")
					chks[i].checked=true;
			}
			else
			{
			//alert("else")
			chks[i].checked=false;
			}
				
		
		}	
}

</script>

<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + menuURL; %>
<his:TitleTag key="patientProfile" width="100%">
</his:TitleTag>

<his:statusTransactionInProcess>

<his:SubTitleTag key="treatmentDetail">
</his:SubTitleTag>

<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODETREATMENTVO_ARRAY%>" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="5%"  class="tdfonthead">
					<div align="center">	
						<input type="checkbox" name="selectCheck" onclick="selectAllCheckboxes(this,'selectedRow')"> 
					</div>
				</td>
				
				<td width="15%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="medicationType"/></b>
						</font>
					</div>
				</td>
				
				<td width="15%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="medicationName"/></b>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="dosage"/></b>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="frequency"/></b>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="startDate"/></b>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="endDate"/></b>
						</font>
					</div>
				</td>
			</tr>
		</table>
		
		<table width="100%" cellpadding="0" cellspacing="0">
			<logic:iterate id="patDrugTreatmentDtlVO" name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODETREATMENTVO_ARRAY%>" indexId="id" type="hisglobal.vo.PatDrugTreatmentDetailVO" >
				<tr>
				 
					<td width="5%"  class="tdfont">
						<div align="center">	   
						<html:checkbox name="GenericPatientProfileFB" property="selectedRow" value="<%=id.toString()%>" ></html:checkbox>   
						</div>
					</td>  
					
					<td width="15%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="medicationType" />
							</font>
						</div>
					</td>   
					
				
					<td width="15%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="medicationName" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="patDrugTreatmentDtlVO" property="doseName" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="frequencyName" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="startDate" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="endDate" />
							</font>
						</div>
					</td>   
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</logic:notEmpty>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateDiagnosis(),'<%=targetHmode%>');" onclick="submitFormOnValidate(validateDiagnosis(),'<%=targetHmode%>');" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm('PROFILEOPTIONS');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('<%=menuURL%>')" onkeypress="if(event.keyCode==13) submitForm('<%=menuURL%>')">
		
		
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
<html:hidden name="GenericPatientProfileFB" property="treatmentCheckFlag" />
