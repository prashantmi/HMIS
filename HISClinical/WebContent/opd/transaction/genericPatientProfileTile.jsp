<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/> 
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/generic_patient_profile.js"/>

  
<script>

var profileGenerationMode=0;
function generateAutomaticProfile(e)
{
	<% String gen_url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWAUTOMATICGENRATEDPROFILE";%>
	var hmode="VIEWAUTOMATICGENRATEDPROFILE";
	var autoProfileCalledFrom=0  //patientProfile =0, patientRefered=1, externalRefered=2
	var profileType=document.getElementsByName("profileType")[0].value;
	profileType = profileType.replace(/[#]/g,'$');
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var deskId=document.getElementsByName("deskId")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var url="/HISClinical/opd/opdPatientProfile.cnt?hmode="+hmode+"&patCrNo="+patCrNo+"&autoProfileCalledFrom="+autoProfileCalledFrom+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&profileGenerationMode="+profileGenerationMode+"&deskId="+deskId+"&profileType="+profileType;
	//alert("url :"+url);
	openDependentPopup(createFHashAjaxQuery(url),e,700,700,true);
	
}
function setAccessProfilePriv(e,accessType,obj,profileStatus,url)
{
	//if(accessType==1 || accessType==4)
	//{
	///	alert("Dont need to set Access Priviledges");
	//	return;
//	}
		
		// alert("status"+profileStatus);
		var status="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS%>";
		// alert("stat"+status)
		if(profileStatus==status)
		{
			alert("Please First Generate The Profile");
			return false;
		}
		else
		{
			document.getElementsByName("profileId")[0].value=obj;
			var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
			url=url+'&profileId='+document.getElementsByName("profileId")[0].value+'&episodeVisitNo='+episodeVisitNo;
			//alert("url"+url)
			openDependentPopup(createFHashAjaxQuery(url),e,700,700,true);
		}
}
  
function submitProfileType(event,obj)
{
	//  alert("inside submit")
	// alert("profile type "+document.getElementsByName("profileType")[0].value)
		/*
	var profileHeader="<%=OpdConfig.PROFILE_HEADER_DISCHARGE_TYPE_DESC%>";
	if(document.getElementsByName("profileType")[0].value==<%=OpdConfig.PROFILE_TYPE_DISCHARGE%>)
	{
		document.getElementsByName("profileHeader")[0].value=profileHeader;
	}
	else if(document.getElementsByName("profileType")[0].value==<%=OpdConfig.PROFILE_TYPE_CASESHEET%>)
	{
		document.getElementsByName("profileHeader")[0].value="<%=OpdConfig.PROFILE_TYPE_CASESHEET_DESC%>";
	}
	else if(document.getElementsByName("profileType")[0].value==<%=OpdConfig.PROFILE_TYPE_GENERAL%>)
	{
		document.getElementsByName("profileHeader")[0].value="<%=OpdConfig.PROFILE_TYPE_GENERAL_DESC%>";
	}
	else
	{
		document.getElementsByName("profileHeader")[0].value="";
	}
	*/
	var profileType=0;
	if(obj.value!="-1"){
		var profileHeader=obj.value.split("#")[2];
		profileType=obj.value.split("#")[0];
		profileGenerationMode=obj.value.split("#")[3];
		document.getElementsByName("profileHeader")[0].value=profileHeader;
		document.getElementsByName("profileType")[0].value=obj.value;
		document.getElementsByName("profileGenerationMode")[0].value=profileGenerationMode;
	}
	else{
		document.getElementsByName("profileHeader")[0].value="";
	}
	
	if((profileGenerationMode == 2) || (profileGenerationMode==3))
	{
		document.getElementById('next').style.display = 'none'
		document.getElementById('generate').style.display = ''
		document.getElementById('imgStepGenerateId').style.display = 'none'
	}
	else
	{
		document.getElementById('next').style.display = ''
		document.getElementById('generate').style.display = 'none'
		document.getElementById('imgStepGenerateId').style.display = ''
	}
}

<%-- function viewPrintProfile(e,profileStatus,url,profileHeader,serialNo,isPDF)
{
	var status="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS%>";

	var newUrl = url+"&profileHeader="+profileHeader+"&serialNo="+serialNo+"&isSinglePageFlag="+isPDF+"&isCallFromEMR="+"0";
	if(profileStatus==status)
	{
			alert("Please First Generate The Profile");
			return false;
	}
	else
	{
		openDependentPopup(createFHashAjaxQuery(newUrl),e,700,700,true);
	}
} --%>



//Added By Ranjit for FTP
function viewPrintProfile(e,profileStatus,url,profileHeader,serialNo,isPDF)
{
	var status="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS%>";

	var newUrl = url+"&profileHeader="+profileHeader+"&serialNo="+serialNo+"&isSinglePageFlag="+isPDF+"&isCallFromEMR="+"0";
	if(profileStatus==status)
	{
			alert("Please First Generate The Profile");
			return false;
	}
	else
	{
		// openDependentPopup(createFHashAjaxQuery(newUrl),e,700,700,true);
		var left=(screen.width-600)/2;
	   var top=(screen.height-400)/2;
		window.open(createFHashAjaxQuery(newUrl),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
		
		
		
	}
}


</script>


<his:TitleTag key="patientProfile">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

<his:statusList>
	<his:SubTitleTag key="previousProfileDtl">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="sel" />
						</font>
					</div>
				</td>

				<td width="20%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="profileHeader" />
						</font>
					</div>
				</td>
				
				<td width="20%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="profileType" />
						</font>
					</div>
				</td>
<%--
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="accessType" /></b>
						</font>
					</div>
				</td>

				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="userlevel" /></b>
						</font>
					</div>
				</td>
--%>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="creationDate" />
						</font>
					</div>
				</td>
				
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="privilege" />
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					
				</td>
			</tr>
			<logic:iterate id="patProfileVO" name="<%=OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST%>" indexId="idx" type="hisglobal.vo.PatientProfileDetailVO">
				<%
					String modifyDivId = "modify" + idx.toString();
				%>
			<tr>
				<td width="5%" class="tdfont">
					<div align="center">
						<html:checkbox name="GenericPatientProfileFB" property="selectedIndex" value="<%=idx.toString()%>" tabindex="1" onchange="onChangeSelectedIndex(this)"></html:checkbox>
					</div>
				</td>

				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				        <% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId="+patProfileVO.getProfileId(); %>
				        <% String profileHeader=patProfileVO.getProfileHeader(); %>
				        <% String serialNo=patProfileVO.getSerialNo(); %>
				         <% String isPDF=patProfileVO.getIsSinglePageFlag(); %>
				        <%-- <a style="cursor: pointer;" onclick="viewPrintProfile(event,'<%=patProfileVO.getProfileStatus() %>','<%=url%>');">  --%> <!-- commented by Dheeraj  -->
						<a style="cursor: pointer;" onclick="viewPrintProfile(event,'<%=patProfileVO.getProfileStatus() %>','<%=url%>','<%=profileHeader%>','<%=serialNo%>','<%=isPDF%>');"> 
						<bean:write name="patProfileVO" property="profileHeader"/>
						</a>
				
						<input type="hidden" name="proName" value='<bean:write name="patProfileVO" property="profileHeader"/>'>
						<input type="hidden" name="proStatus" value='<bean:write name="patProfileVO" property="profileStatus"/>'>
						
						</b></font>
					</div>
					
				</td>
				
				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="patProfileVO" property="profileTypeDesc" /></b>
						</font>
					</div>
				</td>
				
<%--				<td width="15%" class="tdfont">
					<div align="center">
						<% String selonchange="setUserLevel("+idx.toString()+")"; %>
						<html:select name="GenericPatientProfileFB" property="selectedAccessType" onchange="<%=selonchange%>" value="<%=patProfileVO.getAccessType()%>" tabindex="1" disabled="true">
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_ALL%>">To All</html:option>
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OWNING_UNIT%>">Owning Unit</html:option>
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC%>">Unit Specific</html:option>
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_RESTRICTED_USERS%>">Restricted User</html:option>
						</html:select>
					</div>
				</td>

				<td width="15%" class="tdfont">
					<div align="center">
						<html:select name="GenericPatientProfileFB" property="selectedUserLevel" value="<%=patProfileVO.getUserLevel()%>" tabindex="1" disabled="true">
							<html:option value="-1">Select Value</html:option>
							<html:option value="0">No Level</html:option>
							<html:option value="1">Level 1</html:option>
							<html:option value="2">Level 2</html:option>
							<html:option value="3">Level 3</html:option>
							<html:option value="4">Level 4</html:option>
							<html:option value="5">Level 5</html:option>
							<html:option value="6">Level 6</html:option>
						</html:select>
					</div>
				</td>
--%>
				<td width="25%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="patProfileVO" property="entryDate" /></b>
						</font>
					</div>
				</td>
				
				<!-- (1) this block has been modified   -->
				<%-- <logic:equal name="patProfileVO" property="profileType" value="<%=OpdConfig.PROFILE_TYPE_DISCHARGE%>"> 
				<td width="15%" class="tdfont">
					
				</td>
				</logic:equal>
				<logic:notEqual name="patProfileVO" property="profileType" value="<%=OpdConfig.PROFILE_TYPE_DISCHARGE%>">
				--%>
				<td width="15%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				  		<% String urlPri="/HISClinical/opd/opdPatientProfile.cnt?hmode=SETACCESSPRIV&accessPrivilSerialNo="+idx.toString(); %>
						<a style="cursor: pointer;" onclick="setAccessProfilePriv(event,'<%=patProfileVO.getAccessType()%>','<%=patProfileVO.getProfileId()%>','<%=patProfileVO.getProfileStatus()%>','<%=urlPri%>');">
							SET
						</a>
						</b></font>
					</div>
				</td>
				<%--</logic:notEqual> --%>
				<!-- (1) Modification ends here --> 
				<logic:equal name="patProfileVO" property="profileStatus" value="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED %>">
				<td width="15%" class="tdfont">
					
				</td>
				</logic:equal>
				<logic:notEqual name="patProfileVO" property="profileStatus" value="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED %>">
				<td width="15%" class="tdfont">
					<div align="center">
					  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onclick="submitProfileDetails('<%=patProfileVO.getProfileId()%>','<%=patProfileVO.getProfileHeader()%>','<%=patProfileVO.getSerialNo()%>',<%=patProfileVO.getProfileType() %>,'FETCHPROFILEDETAILS')" onkeypress="if(event.keyCode==13) submitProfileDetails('<%=patProfileVO.getProfileId()%>','<%=patProfileVO.getProfileHeader()%>','<%=patProfileVO.getSerialNo()%>',<%=patProfileVO.getProfileType() %>,'FETCHPROFILEDETAILS');">
					</div>
				</td>
				</logic:notEqual>
			
				
			</tr>
					<!--<td width="20%" class="tdfont" nowrap="nowrap">
					</td>
					 <td width="10%" class="tdfont" nowrap="nowrap">
						<div id="<%=modifyDivId%>" align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<a style="cursor: pointer;" onclick="modifyProfile('<bean:write name="patProfileVO" property="profileId" />','<bean:write name="patProfileVO" property="serialNo" />','<%=idx.toString()%>')">
									<bean:message key="modify" />
								</a>
							</font>
						</div>
					</td> 
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div id="<%=modifyDivId%>" align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<a style="cursor: pointer;" onclick="removeProfile('<bean:write name="patProfileVO" property="profileId" />','<bean:write name="patProfileVO" property="serialNo" />','<%=idx.toString()%>')">
									<bean:message key="remove" />
								</a>
							</font>
						</div>
					</td>
				</tr>-->
			</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusList>

<his:statusNew>
	<his:SubTitleTag key="ProfileDtl">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="profileType" />
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<input type="hidden" name="profileType"/>
						<html:select name="GenericPatientProfileFB" property="profileType" onchange="submitProfileType(event,this)" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=OpdConfig.PROFILE_TYPE_LIST %>" property="value" labelProperty="label"  />
						</html:select>
					</div>
				</td>
			</tr>
			
						
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="profileHeader" />
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" >
					<div align="left">
						<html:text name="GenericPatientProfileFB" property="profileHeader" tabindex="1" size="50" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event);"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfont"  colspan="2">
					<div align="right" >
						<img class="button" id="next" style="display: none" src='<his:path src="/hisglobal/images/btn-next.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateForm(),'PROFILEOPTIONS');" onclick="submitFormOnValidate(validateForm(),'PROFILEOPTIONS');" tabindex="1" />
											
						<img class="button" id="generate" style="display: none" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) generateAutomaticProfile(event);" onclick="generateAutomaticProfile(event);" tabindex="1" />
					</div>		
				</td>
			</tr>
			
		</table>
	</his:ContentTag>
</his:statusNew>

<his:ButtonToolBarTag>	
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	<his:statusList>
		<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRemoveProfile(),'REMOVEPROFILE');" onclick="submitFormOnValidate(validateRemoveProfile(),'REMOVEPROFILE');" tabindex="1" />
	</his:statusList>
	<% String gurl="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWGENRATEDPROFILE";%>
	<img id="imgStepGenerateId" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateProfile(event,'<%=gurl%>');" onclick="generateProfile(event,'<%=gurl%>');" tabindex="1" />
	
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
<html:hidden name="GenericPatientProfileFB" property="serialNo"/>
<html:hidden name="GenericPatientProfileFB" property="profileHTML"/>
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="patCategoryCode" />
<html:hidden name="GenericPatientProfileFB"	property="selectedProfileId" />
<html:hidden name="GenericPatientProfileFB" property="selectedSerialNo" />
<html:hidden name="GenericPatientProfileFB"	property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB"	property="profileType" />
<html:hidden name="GenericPatientProfileFB"	property="profileGenerationMode" />
<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB"	property="isSinglePageFlag" />

