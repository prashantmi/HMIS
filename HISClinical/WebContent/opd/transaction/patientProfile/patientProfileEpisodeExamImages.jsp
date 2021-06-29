<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateExamImages()
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
		alert("Select At least One Image for Profile .. ");
		chks[i].focus();
		return false;
	} 
	return true;
}

function getImageView(event,path)
{
	openDependentPopup(createFHashAjaxQuery(path),event,600,700,'yes');
}

function callThisOnload()
{
		var str=document.getElementsByName("imageCheckFlag")[0].value;
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
}

</script>

<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + menuURL; %>
<his:TitleTag key="patientProfile" width="100%">
</his:TitleTag>

<his:statusTransactionInProcess>

<his:SubTitleTag key="allergiesDetail">
</his:SubTitleTag>

<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEEXAMIMAGESVO_ARRAY%>" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="5%"  class="tdfonthead">
					<div align="center">	
						<input type="checkbox" name="selectCheck" onclick="selectAllCheckboxes(this,'selectedRow')"> 
					</div>
				</td>
				
				<td width="45%"  class="tdfonthead">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="image"/> <bean:message key="name"/></b>
						</font>
					</div>
				</td>
				<td width="50%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>
						</font>
					</div>
				</td>
			</tr>
		</table>
		
		<table width="100%" cellpadding="0" cellspacing="0">
			<logic:iterate id="episodeExamImageVO" name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEEXAMIMAGESVO_ARRAY%>" indexId="id" type="hisglobal.vo.OpdPatientImageDtlVO" >
				<tr>
					<td width="5%"  class="tdfont">
						<div align="center">	           
							<html:checkbox name="GenericPatientProfileFB" property="selectedRow" value="<%=id.toString()%>" ></html:checkbox>
						</div>
					</td>  
					
					<%
						String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
							+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS + "&" 
							+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX + "&" 
							+ ServletsUtilityConfig.FILE_NAME + "=" + episodeExamImageVO.getImageFileName(); 
					%>
					<td width="45%"  class="tdfont">
						<div align="center">	   
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<a style="cursor:pointer" onclick="getImageView(event,'<%=path%>')" >
									<bean:write name="episodeExamImageVO" property="imageName" />
		      					</a>
							</font>
						</div>
					</td>
					<td width="50%"  class="tdfont">
						<div align="center">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="episodeExamImageVO" property="remarks" />
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
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateExamImages(),'<%=targetHmode%>');" onclick="submitFormOnValidate(validateExamImages(),'<%=targetHmode%>');" tabindex="1" />		
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
<html:hidden name="GenericPatientProfileFB" property="imageCheckFlag" />