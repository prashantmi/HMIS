<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>



<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<%@page import="ehr.EHRConfig"%>


<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="ehr.diagnosis.vo.EHRSection_DiagnosisVO"%>
<%@page import="ehr.diagnosis.presentation.EHRSection_DiagnosisFB"%>
<%@page import="opd.*"%>

<his:javascript src="/opd/js/generic_patient_profile.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
<%-- function validateDiagnosis()
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
		var str=document.getElementsByName("diagnosisCheckFlag")[0].value;

	    //alert(">>>>>."+str)
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
} --%>
 function selectAllCheckboxes(obj,name)
{
	var chks = document.getElementsByName(name);
	for(var i=0;i<chks.length;i++)
		if(obj.checked)
			chks[i].checked=true;
		else
			chks[i].checked=false;
} 
</script>

<bean:define id="menuURL" name="EHRSection_DiagnosisFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + menuURL; %>
<%-- <his:TitleTag key="patientProfile" width="100%">
</his:TitleTag> --%>

<%-- <his:SubTitleTag key="diagnosisDetail">
</his:SubTitleTag> --%>
<logic:notEmpty name="<%=EHRConfig.EHR_COMPOSITION_SESSION_KEY_LIST_PATIENT_ENCOUNTER_DIAGNOSIS%>" >

<%-- <logic:notEmpty name="<%=EHRConfig.EHR_SECTION_KEY_DIAGNOSIS%>" > --%>
	<his:ContentTag>
		<table width="75%" cellpadding="0" cellspacing="0" style="margin:1%">
			<tr>
				<td width="2%"  class="">
					<div align="center">	
 						<!-- <input type="checkbox" name="selectCheck" onclick="selectAllCheckboxes(this,'selectedRow')">  -->
 						
 						<!-- <input type="checkbox" name="selectCheck" checked="checked" onclick="selectAllCheckboxes(this,'selectedRow')">  -->
 				</div>
				</td>
				<logic:notEqual name="EHRSection_DiagnosisFB" property="deskType" value="<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK %>">
				<%-- <td width="15%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="visitDate"/></b>
						</font>
					</div>
				</td> --%>
				</logic:notEqual>
				<%-- <td width="20%"  class="">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="diagnosisCode"/></b>
						</font>
					</div>
				</td> --%>
				<td width="27%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="diagnosisName"/></b>
						</font>
					</div>
				</td>
				<td width="27%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="diagonosisType"/></b>
						</font>
					</div>
				</td>
			<!-- 	<td width="26%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>
						</font>
					</div>
				</td> -->
			</tr>
		</table>
		
		<table width="75%" cellpadding="0" cellspacing="0" style="margin:1%">
		<logic:iterate id="EHRSection_DiagnosisVO" name="<%=EHRConfig.EHR_COMPOSITION_SESSION_KEY_LIST_PATIENT_ENCOUNTER_DIAGNOSIS%>" indexId="id" type="ehr.diagnosis.vo.EHRSection_DiagnosisVO" >
			<%-- <logic:iterate id="episodeDiagnosisVO" name="<%=EHRConfig.EHR_SECTION_KEY_DIAGNOSIS%>" indexId="id" type="ehr.diagnosis.vo.EHRSection_DiagnosisVO" > --%>
				<tr>
				 
					<td width="2%"  class="">
						<div align="left">	   
						<%-- <html:checkbox name="EHRSection_DiagnosisFB" property="selectedRow" value="<%=id.toString()%>" ></html:checkbox> --%>
                        <input type="checkbox" name="ehrcomp_ENCDIAGNOSIS_chk_select" checked="checked"   value="<%=EHRSection_DiagnosisVO.getSelectedSectionData()%>" onchange="setPatDocSelectJSON('ENCDIAGNOSIS')"  />
						<input type="hidden" name="ehrcomp_ENCDIAGNOSIS_dataelem_json_<%=EHRSection_DiagnosisVO.getSelectedSectionData()%>" value='<%=EHRSection_DiagnosisVO.getJSONObj().toString() %>'  />         
 						</div>
					</td>  
				<logic:notEqual name="EHRSection_DiagnosisFB" property="deskType" value="<%=DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK %>">
					<%-- <td width="15%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="EHRSection_DiagnosisVO" property="episodeDate" />
							</font>
						</div>
					</td>   --%> 
					</logic:notEqual>
					<%-- <td width="20%"  class="">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="EHRSection_DiagnosisVO" property="diagnosticCode" />
								<html:hidden name="EHRSection_DiagnosisFB" property="diagnosticCode" value="<%=EHRSection_DiagnosisVO.getDiagnosticCode() %>"/>
							</font>
						</div>
					</td>    --%>
					<td width="27%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="EHRSection_DiagnosisVO" property="dignosisName" />
								<html:hidden name="EHRSection_DiagnosisFB" property="dignosisName" value="<%=EHRSection_DiagnosisVO.getDignosisName() %>"/>
							</font>
						</div>
					</td>   
					<td width="27%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="EHRSection_DiagnosisVO" property="diagnosticTypeName" />
								<html:hidden name="EHRSection_DiagnosisFB" property="diagnosticTypeName" value="<%=EHRSection_DiagnosisVO.getDiagnosticTypeName() %>"/>
							</font>
						</div>
					</td>   
				<!-- 	<td width="26%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="EHRSection_DiagnosisVO" property="remarks" />
								<html:hidden name="EHRSection_DiagnosisFB" property="remarks" value="<%=EHRSection_DiagnosisVO.getRemarks() %>"/>
 							</font>
						</div>
					</td>    -->
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</logic:notEmpty>

<%-- <his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateDiagnosis(),'<%=targetHmode%>');" onclick="submitFormOnValidate(validateDiagnosis(),'<%=targetHmode%>');" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm('PROFILEOPTIONS');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('<%=menuURL%>')" onkeypress="if(event.keyCode==13) submitForm('<%=menuURL%>')">
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag> --%>


<html:hidden name="EHRSection_DiagnosisFB" property="hmode" />
<html:hidden name="EHRSection_DiagnosisFB" property="patCrNo" />
<html:hidden name="EHRSection_DiagnosisFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_DiagnosisFB" property="episodeCode" />




<%-- <html:hidden name="GenericPatientProfileFB" property="hmode" />

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
<html:hidden name="GenericPatientProfileFB" property="diagnosisCheckFlag" />
 --%>
 
 
 <script>

 </script>