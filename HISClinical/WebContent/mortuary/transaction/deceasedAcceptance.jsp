<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"><!--
window.onload=function()
{
	relativeDetails();
	showHandoverStorage();
	showHandoverDetail();
	showNewImage();
	showExistingImage();
	checkedSelectedValue();
	enableRadio();
	showPoliceVerification();
	DutyOfficerDetail();
	
	showStorage();
	disabledIsClaimed();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDeceasedDetail(obj)
{
	document.getElementsByName("patCrNo")[0].value=obj.value;;
	submitForm21('DECEASEDDTL')
}

function showHandoverStorage()
{
	if(document.getElementsByName("handoverStorageFlag")[0].checked)
	{
		document.getElementById("divBodyHandoverId").style.display="none";
		document.getElementById("divBodyStorageId").style.display="block";
		showStorage();
	}
	if(document.getElementsByName("handoverStorageFlag")[1].checked)
	{
		document.getElementById("divBodyHandoverId").style.display="block";
		document.getElementById("divBodyStorageId").style.display="none";
		showStorage();
	}
	
}

function showHandoverDetail()
{
	if(document.getElementsByName("bodyHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF%> || document.getElementsByName("bodyHandoverTo")[0].value=="-1")
	{
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
	}
	if(document.getElementsByName("bodyHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE%>)
	{
		document.getElementById("divBodyHandoverToPolice").style.display="block";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
	}
	if(document.getElementsByName("bodyHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE%>)
	{
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="block";
	}
}

function DutyOfficerDetail()
{
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		document.getElementById('dutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('dutyOfficerDetailId').style.display="none";
	}
}

function newDutyOfficerDetail()
{
	if(document.getElementsByName("newDutyOfficeFlag")[1].checked)
	{
		document.getElementById('newDutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('newDutyOfficerDetailId').style.display="none";
	}
}

function showPoliceVerification(event)
{
	if(document.getElementsByName("policeVerificationFlag")[1].checked)
	{
		document.getElementById("divNewPoliceCaseId").style.display="block";
		document.getElementById("divExistingPoliceCaseId").style.display="none";
		document.getElementsByName("newDutyOfficeFlag")[1].checked=true
		newDutyOfficerDetail();
	}
	if(document.getElementsByName("policeVerificationFlag")[0].checked)
	{
		if(document.getElementsByName("caseNo")[0].value=="")
		{
			alert("No Police Verification Detail Exists");
			document.getElementsByName("policeVerificationFlag")[1].checked=true;
		}
		else
		{
			document.getElementById("divNewPoliceCaseId").style.display="none";
			document.getElementById("divExistingPoliceCaseId").style.display="block";
		}
		/*var patMlcNo=document.getElementsByName("patMlcNo")[0].value;
		var path='/HISClinical/mortuary/deceasedAcceptance.cnt?hmode=SHOWEXISTING&patMlcNo='+patMlcNo;
		openPopup(createFHashAjaxQuery(path),event,300,800);*/
	}	
}

function getRack()
{
	var chamberId=document.getElementsByName("chamberId")[0].value;
	if(chamberId!="-1")
		submitForm21('GETRACK');
}

function validateSave()
{
	var valid=false;
	if(comboValidation(document.forms[0].broughtByName,"Peon Name")
		//&& validateExistingImageCapture()
		//&& validateNewImageCapture()
		//&& validateIsDefaultImage()
		&& validateIdMark()
		&& validateMLC()
		&& validateHandoverStorage()
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
//	alert(valid);
//	return false;
	return valid;	
}

function validateMLC()
{
	var valid=false;
	if(document.getElementsByName("isMlcCase")[0].value==<%= MortuaryConfig.IS_MLC_YES%>)
	{
		if(document.getElementsByName("policeVerificationFlag")[1].checked)
		{
			valid=validatePoliceVerification();
		}
		else
		{
			valid=true;
		}
	}
	else
	{
		valid=true;
	}
	return valid;
}

function validatePoliceVerification()
{
	var valid=false;
	if(isEmpty(document.forms[0].newCaseNo,"Case No")
		&& isEmpty(document.forms[0].newPoliceStation,"Police Station")
		&& isEmpty(document.forms[0].newDocketNo,"Docket No")
		&& isEmpty(document.forms[0].newOfficerIncharge,"Investigating Officer Name")
		&& isEmpty(document.forms[0].newIoDesignation,"Investigating Officer Designation")
		&& isEmpty(document.forms[0].newIoBatchNo,"Investigating Officer Badge No.")
		&& validateDutyOfficer()
		&& isEmpty(document.forms[0].newCaseRemarks,"Case Remarks")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateDutyOfficer()
{
	var valid=false;
	if(document.getElementsByName("newDutyOfficeFlag")[1].checked)
	{
		if(isEmpty(document.forms[0].newDutyOffName,"Duty Officer Name")
			&& isEmpty(document.forms[0].newDutyOffDesignation,"Duty Officer Designation")
			&& isEmpty(document.forms[0].newDutyOffBatchNo,"Duty Officer Badge No.")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
	{
		valid=true;
	}
	return valid;	
}

function validateHandoverStorage()
{
	var valid=false;
	if(document.getElementsByName("handoverStorageFlag")[1].checked)
	{
		valid=validateHandover();
	}
	else
	{
		valid=validateBodyInMortuary();
	}
	return valid;
}

function validateBodyInMortuary()
{
	var valid=false;
	if(document.getElementsByName("storageFlag")[0].checked)
	{
		valid=validateStorageRelative();
	}
	else
	{
		valid=validateStorage();
	}
	return valid;
}
function validateStorage()
{
	var valid=false;
	if(comboValidation(document.forms[0].chamberId,"Chamber")
		&& comboValidation(document.forms[0].chamberRackId,"Rack")
		&& comboValidation(document.forms[0].bodyPutBy,"Body Put By")
		&& validateStorageUpto()
		&& validateStorageRelative()
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateStorageRelative()
{
	var valid=true;
	if(document.getElementsByName("isClaimed")[0].checked)
	{
		if(isEmpty(document.forms[0].storageRelativeName,"Relative Name")
			&& comboValidation(document.forms[0].storageRelativeCode,"Relationship")
			&& isEmpty(document.forms[0].storageRelativeAddress,"Address of Relative")
			&& isEmpty(document.forms[0].storageRelativeContactNo,"Relative Contact No")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
		valid=true;
	
	return valid;
}

function validateStorageUpto()
{
	var valid=true;
	var maxUpto="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>"
	if(document.getElementsByName("storageUpto")[0].value=="" || document.getElementsByName("storageUpto")[0].value=="0" || document.getElementsByName("storageUpto")[0].value=="00")
	{
		alert("Please Enter The Storage Duration");
		document.getElementsByName("storageUpto")[0].focus();
		valid=false;
	}
	else
	{
		valid=true;
		
	}
	
	return valid;
}

function validateHandover()
{
	var valid=false;
	if(document.getElementsByName("bodyHandoverTo")[0].value=="-1")
	{
		alert("Please Select Hand Over To");
		document.getElementsByName("bodyHandoverTo")[0].focus();
		valid=false;
	}
	
	if(document.getElementsByName("bodyHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF%>)
	{
		valid=true;
	}
	
	if(document.getElementsByName("bodyHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE%>)
	{
		if(isEmpty(document.forms[0].officerName,"Officer Name")
			&& isEmpty(document.forms[0].officerDesignation,"Officer Designation")
			&& isEmpty(document.forms[0].officerBadgeNo,"Officer Badge No")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	
	if(document.getElementsByName("bodyHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE%>)
	{
		if(isEmpty(document.forms[0].relativeName,"Relative Name")
			&& comboValidation(document.forms[0].relativeCode,"Relationship")
			&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
			&& isEmpty(document.forms[0].relativeContactNo,"Relative Contact No")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	return valid;
}

function validateIdMark()
{
	var valid=false;
	if(document.getElementsByName("unidentifiedBody")[0].value==<%=MortuaryConfig.UNIDENTIFIED_BODE_YES %>)
	{
		if(isEmpty(document.forms[0].idMark1,"ID Mark 1")
			&& isEmpty(document.forms[0].idMark2,"ID Mark 2")
		)	
		{
			valid=true;
		}	
		else
		{
			valid=false;
		}
	}
	else
		valid=true;
	return valid;
}

function clearForm()
{
	document.getElementsByName("broughtByName")[0].value="-1";
	if(document.getElementsByName("isMlcCase")[0].value==<%=MortuaryConfig.IS_MLC_YES%>)
	{
		if(document.getElementsByName("policeVerificationFlag")[0].checked)
		{
			document.getElementsByName("newCaseNo")[0].value="";
			document.getElementsByName("newPoliceStation")[0].value="";
			document.getElementsByName("newDocketNo")[0].value="";
			document.getElementsByName("newOfficerIncharge")[0].value="";
			document.getElementsByName("newIoDesignation")[0].value="";
			document.getElementsByName("newIoBatchNo")[0].value="";
			document.getElementsByName("newDutyOffName")[0].value="";
			document.getElementsByName("newDutyOffDesignation")[0].value="";
			document.getElementsByName("newDutyOffBatchNo")[0].value="";
			document.getElementsByName("newCaseRemarks")[0].value="";
		}
	}	
	if(document.getElementsByName("isMlcCase")[0].value==<%=MortuaryConfig.IS_MLC_NO%>)
	{
		document.getElementsByName("bodyHandoverTo")[0].value="-1";
	}
	
	document.getElementsByName("officerName")[0].value="";
	document.getElementsByName("officerDesignation")[0].value="";
	document.getElementsByName("officerBadgeNo")[0].value="";
	document.getElementsByName("relativeName")[0].value="";
	document.getElementsByName("relativeCode")[0].value="-1";
	document.getElementsByName("relativeAddress")[0].value="";
	document.getElementsByName("relativeContactNo")[0].value="";
	document.getElementsByName("chamberId")[0].value="-1";
	document.getElementsByName("chamberRackId")[0].value="-1";
	document.getElementsByName("bodyPutBy")[0].value="-1";
	document.getElementsByName("storageReason")[0].value="";
	document.getElementsByName("storageUpto")[0].value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>";
	document.getElementsByName("storageRelativeName")[0].value="";
	document.getElementsByName("storageRelativeCode")[0].value="-1";
	document.getElementsByName("storageRelativeAddress")[0].value="";
	document.getElementsByName("storageRelativeContactNo")[0].value="";
	document.getElementsByName("idMark1")[0].value="";
	document.getElementsByName("idMark2")[0].value="";
}

function submit4image()
{
	elem = document.getElementsByName('hmode')[0];
	elem.value = "REFRESHFORIMAGE"; 
	document.forms[0].submit();
}

function showExistingImage()
{
	if(document.getElementsByName("existingImage")[0].checked)
	{
		if(document.getElementsByName("imageExistFlag")[0].value==<%=MortuaryConfig.IMAGE_EXIST_YES%>)
		{
			document.getElementById("divExistingImageId").style.display="block";
		}
		else
		{
			alert("No Image Exists");
			document.getElementsByName("existingImage")[0].checked=false;
		}
		
	}
	else
	{
		document.getElementById("divExistingImageId").style.display="none";
	}
}

function showNewImage()
{
	if(document.getElementsByName("newImage")[0].checked)
	{
		document.getElementById("divNewImageId").style.display="block";
	}
	else
	{
		document.getElementById("divNewImageId").style.display="none";
	}
}

function submitToUpload(event)
{
	openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600);
}

function deleteRow(key)
{	
	document.getElementsByName("removeImageIndex")[0].value=key;
	//alert("uploadedFileName===  "+key);
	submitForm21("REMOVE");
}

function getUploadedeFile(event,path)
{
	openDependentPopup(path,event,300,600,'yes');
}

function enableRadio()
{
	for(var i=0;i<document.getElementsByName("selectedExistingImage").length;i++)
	{
		if(document.getElementsByName("selectedExistingImage")[i].checked==true)
		{
			document.getElementsByName("isDefaultImage")[i].disabled=false; 
		}
		else
		{
			document.getElementsByName("isDefaultImage")[i].disabled=true;
			document.getElementsByName("isDefaultImage")[i].checked=false;
		}
	}	
}

/* function validateExistingImageCapture()
{
	var valid=true;
	if(document.getElementsByName("existingImage")[0].checked==true)
	{
		valid=validateExistingImage();
	}
	return valid;
} 

function validateNewImageCapture()
{
	var valid=true;
	if(document.getElementsByName("newImage")[0].checked==true)
	{
		valid=validateNewImage();
	}
	return valid;
}

function validateExistingImage()
{
	var valid=true;
	var count=0;
	for(var i=0;i<document.getElementsByName("selectedExistingImage").length;i++)
	{
		if(document.getElementsByName("selectedExistingImage")[i].checked==true)
		{
			count++;
		}
	}
	
	if(count<=0)
	{
		alert("Please Select At Least One Existing Image");
		valid=false;
	}
	else
	{
		valid=true;
	}
	return valid;	
}

function validateNewImage()
{
	var valid=true;
	if(document.getElementsByName("newAddImageExistFlag")[0].value=="0")
	{
		alert("Please Add At Least One New Image");
		valid=false;
	}
	else
	{
		valid=true;
	}
		
	return valid;	
} */

function checkedSelectedValue()
{
	var str=document.getElementsByName("tempChkValue")[0].value;
	var arr=str.split("#");
	var chks=document.getElementsByName('selectedExistingImage');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}

function validateIsDefaultImage()
{
	var valid=true;
	if(document.getElementsByName("existingImage")[0].checked==true || document.getElementsByName("newImage")[0].checked==true)
	{
		var count=0;
		var len=document.getElementsByName("isDefaultImage").length;
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("isDefaultImage")[i].checked==true)
				count++;
		} 
		
		if(count<1)
		{
			alert("Please Select The Default Image");
			valid=false;
		}
	}
	else
		valid=true;	
	return valid;
}

function relativeDetails()
{
	if(document.getElementsByName("isClaimed")[0].checked)
	{
		document.getElementById("divRelativeStorageId").style.display="block";
	//	document.getElementsByName("isClaimed")[0].value="<%=MortuaryConfig.IS_BODY_CLAIMED_YES%>";
	}
	else
	{
		document.getElementById("divRelativeStorageId").style.display="none";
	//	document.getElementsByName("isClaimed")[0].value="<%=MortuaryConfig.IS_BODY_CLAIMED_NO%>";
	}
	
}

function showStorage()
{
	if(document.getElementsByName("storageFlag")[0].checked)
	{
		document.getElementById("divBodyInChamber").style.display="none";
		relativeDetails();
	}
	else
	{
		document.getElementById("divBodyInChamber").style.display="block";
		relativeDetails();
	}
}

function disabledIsClaimed()
{
	if(document.getElementsByName("unidentifiedBody")[0].value==<%=MortuaryConfig.UNIDENTIFIED_BODE_YES%>)
		document.getElementsByName("isClaimed")[0].disabled=true;
	else
		document.getElementsByName("isClaimed")[0].disabled=false;
}
--></script>


<body>
	<html:form action="/deceasedAcceptance">
		<his:TransactionContainer>
			<his:TitleTag name="Deceased Acceptance">
			</his:TitleTag>
			
			<his:statusList>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="crNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="type"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deathdate"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="arrDeadPatListVO" name="<%=MortuaryConfig.DEAD_PATIENT_LIST_SEND_TO_MORTUARY %>" type="hisglobal.vo.PatientDeathDetailVO">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="DeceasedAcceptanceFB" property="deadPatRadio" value="<%=arrDeadPatListVO.getPatCrNo() %>" onclick="getDeceasedDetail(this)" tabindex="1" ></html:radio>
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeadPatListVO.getPatCrNo() %>
										</font>
									</div>
								</td>
								<td width="35%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeadPatListVO.getPatName() %>
										</font>	
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeadPatListVO.getDeceasedType() %>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeadPatListVO.getDeathDate() %>
										</font>	
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</his:statusList>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
				<his:SubTitleTag name="Deceased Brought By Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="from"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="DeceasedAcceptanceFB" property="fromUnit"/>
										<html:hidden name="DeceasedAcceptanceFB" property="fromUnit"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deathDuration"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="DeceasedAcceptanceFB" property="deathDuration"/>
										<html:hidden name="DeceasedAcceptanceFB" property="deathDuration"/>
									</font>
							</td>
						</tr>
						<tr>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="peon"/>
										<bean:message key="name"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="DeceasedAcceptanceFB" property="broughtByName" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESSENTIAL_PEON_LIST %>">
											<html:options collection="<%=MortuaryConfig.ESSENTIAL_PEON_LIST%>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
							<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">										
										<bean:message key="DeathEnteredBy"/>
									</font>
							</td>
							<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="DeceasedAcceptanceFB" property="deathEnteredBy"/>
									</font>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				
				<!-- Added as per the new CR -->
				<his:SubTitleTag name="Item Description">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="itemDesc"/>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:textarea name="DeceasedAcceptanceFB" property="itemDesc" rows="3" cols="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:textarea>
									</font>
								</div>
							</td>							
						</tr>						
					</table>
				</his:ContentTag>
				<!-- ADD Subtital [Image Captured] before open this code   , dated on 08-05-2015
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="100%" >
								<div align="right">
									<html:checkbox name="DeceasedAcceptanceFB" property="existingImage" value="<%=MortuaryConfig.IMAGE_UPLOAD_FROM_EMERGENCY%>" onclick="showExistingImage()" tabindex="1" ></html:checkbox>
									<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="exist"/>
										</b>
									</font>	
									<html:checkbox name="DeceasedAcceptanceFB" property="newImage" value="<%=MortuaryConfig.IMAGE_UPLOAD_FROM_MORTUARY%>" onclick="showNewImage()" tabindex="1" ></html:checkbox>
									<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="new"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
					</table>
				-->
				<div id="divExistingImageId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr bgcolor="#FFB468">
								<td width="50%" colspan="4">
									<div align="left">
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="exist"/>
											</b>
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="date"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="image"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="isdefault"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<%if(session.getAttribute(MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO)!=null) {%>
							<logic:iterate id="arrDeceasedImageVO" name="<%=MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO %>" type="hisglobal.vo.PatientImageDtlVO" indexId="idx">
								<tr>
									<td width="10%" class="tdfonthead">
									<%String imgIndex=MortuaryConfig.IMAGE_EXIST_YES+"@"+idx; %>
										<div align="center">
											<html:checkbox name="DeceasedAcceptanceFB" property="selectedExistingImage" value="<%=imgIndex %>" onclick="enableRadio()" tabindex="1" ></html:checkbox>
										</div>
									</td>
									<td width="30%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%=arrDeceasedImageVO.getEntryDate() %>
											</font>
										</div>
									</td>
									<%
									String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; 
									String imagepath="/HISClinical/image/showImage?"+ imageIndex+"="+idx; 
		  							%>
									<td width="30%" class="tdfonthead">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												
												<a style="cursor:pointer" onclick="getUploadedeFile(event,'<%=imagepath%>')" > 
													 <%=arrDeceasedImageVO.getFileNo() %>
												</a>
												
											</font>	
										</div>
									</td>
									<td width="30%" class="tdfonthead">
									<%String value=MortuaryConfig.IMAGE_EXIST_YES+"@"+idx; %>
										<div align="center">
											<html:radio name="DeceasedAcceptanceFB" property="isDefaultImage" value="<%=value %>" disabled="true" tabindex="1" ></html:radio>
										</div>
									</td>
								</tr>
							</logic:iterate>
							<%} %>
						</table>
						</his:ContentTag>		
					</div>
					
					<div id="divNewImageId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr bgcolor="#FFB468">
								<td width="50%" colspan="3">
									<div align="left">
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="new"/>
											</b>
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="40%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="uploadImage"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfont">
									<div align="center">
										<img class="button"  class="button" src="../hisglobal/images/plus.gif"  border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)submitToUpload(event);" onClick="submitToUpload(event);" >
									</div>
								</td>
								<td width="30%" class="tdfont">
									<div align="center">
										
									</div>
								</td>
							</tr>
						</table>	
						
						<%if(session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE)!=null){	%>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">	
							<logic:iterate id="addedImage" name="<%=MortuaryConfig.ARR_NEW_ADDED_IMAGE %>" type="hisglobal.vo.DocumentUploadDtlVO" indexId="index">
								<tr>
									<td width="40%" class="tdfont">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<%String key=Config.IMAGE_BYTE_ARRAY_KEY; %>
												<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX;%>
													<%String imagepath="/HISClinical/image/showImage?"+imageIndex+"="+index+"&"+key+"="+MortuaryConfig.UPLOADED_IMAGE_IN_SESSION;%>											
												<a style="cursor:pointer" onclick="getUploadedeFile(event,'<%=imagepath%>')" > 
													<b>
														<%=addedImage.getDocumentName() %>
													</b>
												</a>		
											</font>
										</div>
									</td>
									<td width="30%" class="tdfont">
										<div align="center">
											<img class="button"  class="button" src="../hisglobal/images/minus.gif"  border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)deleteRow('<%=index.toString() %>');" onClick="deleteRow('<%=index.toString() %>');" tabindex="1"  >
										</div>
									</td>
									<td width="30%" class="tdfont">
									<%String val=MortuaryConfig.IMAGE_EXIST_NO+"@"+index; %>
										<div align="center">
											<html:radio name="DeceasedAcceptanceFB" property="isDefaultImage" value="<%=val %>" tabindex="1" ></html:radio>
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>	
						<%} %>
				</his:ContentTag>
				</div>
				
				<logic:equal name="DeceasedAcceptanceFB" property="unidentifiedBody" value="<%=MortuaryConfig.UNIDENTIFIED_BODE_YES %>">
					<his:SubTitleTag name="Deceased Identification Mark Detail">
					</his:SubTitleTag>
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" nowrap="nowrap" class="tdfonthead">
										
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="idMark1" />
										</font>
									</td>
					
									<td width="75%" nowrap="nowrap" class="tdfont">
										<html:text name="DeceasedAcceptanceFB"  maxlength="200" size="100" property="idMark1" onkeypress="return validateAlphaNumOnly(this,event)"  tabindex="1"  />
									</td>
								</tr>
								<tr>
									<td width="25%" nowrap="nowrap" class="tdfonthead">
										
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="idMark2" />
										</font>
									</td>
					
									<td width="75%" nowrap="nowrap" class="tdfont">
										<html:text name="DeceasedAcceptanceFB"  property="idMark2" size="100" maxlength="200" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"  />
									</td>
								</tr>
							</table>
						</his:ContentTag>		
					
				</logic:equal>
				<logic:equal name="DeceasedAcceptanceFB" property="isMlcCase" value="<%=MortuaryConfig.IS_MLC_YES %>">
					<his:SubTitleTag name="Police Verification Detail">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" >
									<div align="right">
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="exist"/>
											</b>	
										</font>
										<html:radio name="DeceasedAcceptanceFB" property="policeVerificationFlag" value="<%=MortuaryConfig.POLICE_VERIFICATION_EXISTING%>" onclick="showPoliceVerification(event)" tabindex="1" ></html:radio>
										<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="new"/>
											</b>
										</font>	
										<html:radio name="DeceasedAcceptanceFB" property="policeVerificationFlag" value="<%=MortuaryConfig.POLICE_VERIFICATION_NEW%>" onclick="showPoliceVerification(event)" tabindex="1"  ></html:radio>
									</div>
								</td>
							</tr>
						</table>	
					</his:SubTitleTag>
					<div id="divExistingPoliceCaseId" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policecaseno" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="DeceasedAcceptanceFB"  maxlength="50" property="caseNo" onkeypress="return validateAlphaNumOnly(this,event)"  readonly="true" tabindex="1"  />
									</td>
					
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policestation" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="DeceasedAcceptanceFB"  property="policeStation" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true" tabindex="1"  />
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="docketNo" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="docketNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="name" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="officerIncharge" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" readonly="true" tabindex="1"></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="designation" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="ioDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" readonly="true" tabindex="1"></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="batchno" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="ioBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true" tabindex="1"></html:text>
									</td>
								</tr>
								
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="dutyOfficer" />
												<bean:message key="detail" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
										<html:radio name="DeceasedAcceptanceFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_IO %>" onclick="DutyOfficerDetail()" disabled="true" tabindex="1"></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="io" />
										</font>		
										<html:radio name="DeceasedAcceptanceFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_OTHER %>" onclick="DutyOfficerDetail()" disabled="true" tabindex="1"></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="other" />
										</font>
									</td>
								</tr>
							</table>
								
							<div id="dutyOfficerDetailId">
								<table width="100%" cellspacing="1" cellpadding="0">
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="name" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="DeceasedAcceptanceFB" property="dutyOffName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" readonly="true" tabindex="1"></html:text>
										</td>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="designation" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="DeceasedAcceptanceFB" property="dutyOffDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" readonly="true" tabindex="1"></html:text>
										</td>
									</tr>
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="batchno" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
											<html:text name="DeceasedAcceptanceFB" property="dutyOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true" tabindex="1"></html:text>
										</td>
									
									</tr>
								</table>
							</div>
							
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="1">
											<tr>
												<td nowrap="nowrap" class="tdfonthead" width="30%">
													<div align="right">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<font color="#FF0000">*</font>
															<bean:message key="caseremarks" />
														</font>
													</div>	
												</td>
								
												<td colspan="2" nowrap="nowrap" width="70%" class="tdfont">
													<html:textarea name="DeceasedAcceptanceFB" tabindex="1" property="caseRemarks" rows="1" cols="90" 
														onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" readonly="true" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</his:ContentTag>
					</div>
					
					<div id="divNewPoliceCaseId" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policecaseno" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="DeceasedAcceptanceFB"  maxlength="50" property="newCaseNo" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
									</td>
					
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policestation" />
											</font>
										</div>	
									</td>
					
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="DeceasedAcceptanceFB"  property="newPoliceStation" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="docketNo" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="newDocketNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="name" />
											</font>
										</div>
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="newOfficerIncharge" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="designation" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="newIoDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="batchno" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="DeceasedAcceptanceFB" property="newIoBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
								</tr>
								
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="dutyOfficer" />
												<bean:message key="detail" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
										<html:radio name="DeceasedAcceptanceFB" property="newDutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_IO %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="io" />
										</font>		
										<html:radio name="DeceasedAcceptanceFB" property="newDutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_OTHER %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="other" />
										</font>
									</td>
								</tr>
							</table>
								
							<div id="newDutyOfficerDetailId">
								<table width="100%" cellspacing="1" cellpadding="0">
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="name" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="DeceasedAcceptanceFB" property="newDutyOffName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
										</td>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="designation" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="DeceasedAcceptanceFB" property="newDutyOffDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
										</td>
									</tr>
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="batchno" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
											<html:text name="DeceasedAcceptanceFB" property="newDutyOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</td>
									
									</tr>
								</table>
							</div>
							
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="1">
											<tr>
												<td nowrap="nowrap" class="tdfonthead" width="30%">
													<div align="right">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<font color="#FF0000">*</font>
															<bean:message key="caseremarks" />
														</font>
													</div>	
												</td>
								
												<td colspan="2" nowrap="nowrap" width="70%" class="tdfont">
													<html:textarea name="DeceasedAcceptanceFB" tabindex="1" property="newCaseRemarks" rows="1" cols="90" 
														onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</his:ContentTag>
					</div>
				</logic:equal>
				
				<his:SubTitleTag name="">
					<div align="left">
						<html:radio name="DeceasedAcceptanceFB" property="handoverStorageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER %>" onclick="showHandoverStorage()" tabindex="1"></html:radio>
						<bean:message key="bodyInMortuary" />
						<html:radio name="DeceasedAcceptanceFB" property="handoverStorageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_HANDOVER %>" onclick="showHandoverStorage()" tabindex="1"></html:radio>
						<bean:message key="bodyHandover" />
					</div>
				</his:SubTitleTag>
				
				<div id="divBodyHandoverId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="handoverto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<logic:equal name="DeceasedAcceptanceFB" property="isMlcCase" value="<%=MortuaryConfig.IS_MLC_NO %>">
											<html:select name="DeceasedAcceptanceFB" property="bodyHandoverTo" onchange="showHandoverDetail()" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<html:option value="<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE %>">Police</html:option>
												<html:option value="<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE %>">Relative</html:option>
											</html:select>
										</logic:equal>
										<logic:equal name="DeceasedAcceptanceFB" property="isMlcCase" value="<%=MortuaryConfig.IS_MLC_YES %>">
											&nbsp;<bean:message key="police"/>
											<html:hidden name="DeceasedAcceptanceFB" property="bodyHandoverTo" value="<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE %>"/>
										</logic:equal>
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
						<div id="divBodyHandoverToRelative" style="display: none;">
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativename"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="realtionship"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="DeceasedAcceptanceFB" property="relativeCode" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeaddress"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:textarea name="DeceasedAcceptanceFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeContactNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="relativeContactNo" maxlength="10" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<div id="divBodyHandoverToPolice" style="display: none;">
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officer"/>
												<bean:message key="name"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
										<html:text name="DeceasedAcceptanceFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officer"/>
												<bean:message key="designation"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officer"/>
												<bean:message key="batchno"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead"></td>
									<td width="25%" class="tdfont"></td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="PoliceStnDtls"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="policeStnHandOver" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
									<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="PoliceCntctNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
									<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="policeContactNo" maxlength="15" onkeypress="return validatePositiveIntegerWithDashOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</his:ContentTag>
				</div>
				
				<div id="divBodyStorageId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" class="tdfont">
									<html:radio name="DeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER %>" onclick="showStorage()" tabindex="1"></html:radio>
									<bean:message key="bodyInStreacher" />
									<html:radio name="DeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE %>" onclick="showStorage()" tabindex="1"></html:radio>
									<bean:message key="bodyStorage" />
								</td>
							</tr>
						</table>
						<div id="divBodyInChamber">
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="chamber"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="DeceasedAcceptanceFB" property="chamberId" onchange="getRack()" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
											
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
													<bean:message key="rack"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="DeceasedAcceptanceFB" property="chamberRackId" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>">
													<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
											
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
													<bean:message key="bodyPutBy"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="DeceasedAcceptanceFB" property="bodyPutBy" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>">
													<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
											
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="storageUpto"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="storageUpto" value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS %>" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
											<bean:message key="hrs"/>
										</div>
									</td>
								</tr>
								<tr>	
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="reason"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="storageReason" maxlength="100" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
									</td>
									<td width="25%" class="tdfont">
									</td>
								</tr>
							</table>
						</div>
					</his:ContentTag>
					
					<his:SubTitleTag name="">
						<div align="left">
							<html:checkbox name="DeceasedAcceptanceFB" property="isClaimed" onclick="relativeDetails()" tabindex="1"></html:checkbox>
							Is Claimed
						</div>	
					</his:SubTitleTag>	
					
					<div id="divRelativeStorageId">	
						<his:ContentTag>		
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativename"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="storageRelativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="realtionship"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="DeceasedAcceptanceFB" property="storageRelativeCode" tabindex="1">
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeaddress"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:textarea name="DeceasedAcceptanceFB" property="storageRelativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeContactNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="DeceasedAcceptanceFB" property="storageRelativeContactNo" maxlength="10" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
							</table>
						</his:ContentTag>
					</div>			
				</div>
				
								
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusList>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
				</his:statusList>
				
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
				</his:statusUnsuccessfull>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:statusTransactionInProcess>
			</his:ButtonToolBarTag>
			
		</his:TransactionContainer>	
		
		
		
		<html:hidden name="DeceasedAcceptanceFB" property="hmode"/>
		<html:hidden name="DeceasedAcceptanceFB" property="patCrNo"/>
		<html:hidden name="DeceasedAcceptanceFB" property="isMlcCase"/>
		<html:hidden name="DeceasedAcceptanceFB" property="patMlcNo"/>
		<html:hidden name="DeceasedAcceptanceFB" property="policeVerificationExist"/>
		<html:hidden name="DeceasedAcceptanceFB" property="imageExistFlag"/>
		<html:hidden name="DeceasedAcceptanceFB" property="removeImageIndex"/>
		<html:hidden name="DeceasedAcceptanceFB" property="newAddImageExistFlag"/>
		<html:hidden name="DeceasedAcceptanceFB" property="tempChkValue"/>
		<html:hidden name="DeceasedAcceptanceFB" property="unidentifiedBody"/>
		
	</html:form>
	<his:status/>
</body>	

</html>