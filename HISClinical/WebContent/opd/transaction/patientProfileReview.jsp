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

function submitTile(mode)
{
	submitForm(mode);
}

function validateSave()
{
	
	if(document.getElementsByName("remarks")[0].value=="")
	{
		alert("Please Enter Review Remark");
		document.getElementsByName("remarks")[0].focus();
		return false;
	}
	else
	{
		submitTile('SAVE');
	}
}

function viewPrintProfile(e,url)
{
	
		openDependentPopup(url,e,700,700,true);
}

function validateSelectedIndex(obj)
{

	// alert("inside selected");
	var count=0;
	for (var i=0;i<document.getElementsByName("selectedIndex").length;i++)
	{
		if(document.getElementsByName("selectedIndex")[i].checked)
		{
			document.getElementsByName("profileReviewValue")[0].value=document.getElementsByName("selectedIndex")[i].value;
			count++;
			break;
		}
	}

	var str=document.getElementsByName("profileReviewValue")[0].value;
	// alert("str"+str)
		document.getElementsByName("admissionNo")[0].value=str.split("@")[0];
		document.getElementsByName("profileId")[0].value=str.split("@")[1];
	
		if(document.getElementsByName("admissionNo")[0].value=='null'||document.getElementsByName("admissionNo")[0].value=='-')
		{
		
			document.getElementsByName("admissionNo")[0].value="";
		}
	
		submitTile('GETREVIEWDTL');
 
}

function openProfilePopup(e,url1)
{
	openDependentPopup(createFHashAjaxQuery(url1),e,500,800,true);
	
}

</script>

<html:form action="/patientProfileReviewDtl">
	<body>
	
	<his:TitleTag name="Patient Profile Review Detail">
	</his:TitleTag>
		<his:statusList>
			<his:InputCrNoTag name="PatientProfileReviewFB">
			</his:InputCrNoTag>
		</his:statusList>
		<his:statusTransactionInProcess>
		<jsp:include page="/registration/patientDetail.cnt" flush="true"/>
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

				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="creationDate" />
						</font>
					</div>
				</td>
				
				<td width="20%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="admissionNo" />
						</font>
					</div>
				</td>
				
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="previousReview" />
						</font>
					</div>
				</td>
				
							
			</tr>
			<logic:iterate id="patProfileVO" name="<%=OpdConfig.PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_PROFILE_VO_ARRAY%>" indexId="idx" type="hisglobal.vo.PatientProfileDetailVO">
				
			<tr>
				<td width="5%" class="tdfont">
					<div align="center">
					<html:radio name="PatientProfileReviewFB" property="selectedIndex" value='<%=patProfileVO.getAdmissionNo()+"@"+patProfileVO.getProfileId()%>' tabindex="1" onclick="validateSelectedIndex(this)"></html:radio>
					</div>
				</td>

				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				        <% String url="/HISClinical/opd/patientProfileReviewDtl.cnt?hmode=VIEWPRINTPROFILE&profileId="+patProfileVO.getProfileId(); %>
						<a style="cursor: pointer;" onclick="viewPrintProfile(event,'<%=url%>');"> 
						<bean:write name="patProfileVO" property="profileHeader"/>
						</a>
							
					
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
				

				<td width="25%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="patProfileVO" property="entryDate" /></b>
						</font>
					</div>
				</td>
				
				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="patProfileVO" property="admissionNo" /></b>
						</font>
					</div>
				</td>
				
				<td width="15%" class="tdfont">
					<div align="center">	        
					 <% String url1="/HISClinical/opd/patientProfileReviewDtl.cnt?hmode=FETCHPROFILEDETAIL&profileId="+patProfileVO.getProfileId()+"&patCrNo="+patProfileVO.getPatCrNo(); %>   
							<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'  style=cursor:pointer tabindex="1" onclick ="openProfilePopup(event,'<%=url1%>')">
						</div>
				</td>
				
							
			</logic:iterate>
		</table>
	</his:ContentTag>
		
		</his:statusTransactionInProcess>
		<his:statusRecordFound>
			<his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
									<b><bean:message key="reviewRemark" /></b>
								</font>
							</div>
						</td>
						<td width="85%" class="tdfont">
							<div align="left">
							<html:textarea name="PatientProfileReviewFB" property="remarks" value="" tabindex="1" rows="5" cols="120" onkeypress="return (validateTextArea(event,this,'1000'))"></html:textarea>
							</div>
						</td>
					</tr>
					</table>
			</his:ContentTag>	
			
		</his:statusRecordFound>
	</body>

<his:ButtonToolBarTag>
		<his:statusList>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
		</his:statusList>
		
		<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
		</his:statusRecordFound>
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('unspecified')" onkeypress="if(event.keyCode==13) submitTile('unspecified')">
		</his:statusTransactionInProcess>
</his:ButtonToolBarTag>

<html:hidden name="PatientProfileReviewFB" property="hmode"/>
<html:hidden name="PatientProfileReviewFB" property="patCrNo"/>
<html:hidden name="PatientProfileReviewFB" property="profileId"/>
<html:hidden name="PatientProfileReviewFB" property="admissionNo"/>
<html:hidden name="PatientProfileReviewFB" property="profileReviewValue"/>
<his:status/>
</html:form>