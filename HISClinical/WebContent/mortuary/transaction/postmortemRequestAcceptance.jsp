<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.PostmortemRequestDetailVO"%>
<%@page import="java.util.List"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>

<script>

window.onload=function()
{
	showTeamPanelDiv();
	
	
	if(document.getElementsByName("addOpinionFlag")[0].value==<%=MortuaryConfig.YES%>)
		showAddOpinion();
	if(document.getElementsByName("addItemFlag")[0].value==<%=MortuaryConfig.YES%>)
		showAddItem();	
	if(document.getElementsByName("consentStatus")[0].value==<%=MortuaryConfig.CONSENT_RAISED%>)
		showReceivedConsentDtl();	
} 

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function acceptPostmortemReq()
{
	var count=0;
	for (var i=0;i<document.getElementsByName("selectedPostmortemReq").length;i++)
	{
		if(document.getElementsByName("selectedPostmortemReq")[i].checked)
		{
			document.getElementsByName("postmortemRequestValue")[0].value=document.getElementsByName("selectedPostmortemReq")[i].value;
			count++;
			break;
		}
	}
	
	if(count==0)
	{
		alert("Please Select a Postmortem Request")
	}
	else
	{
		var str=document.getElementsByName("postmortemRequestValue")[0].value;
		document.getElementsByName("postmortemId")[0].value=str.split("@")[0];
		document.getElementsByName("deceasedNo")[0].value=str.split("@")[1];
		document.getElementsByName("postmortemType")[0].value=str.split("@")[2];
		document.getElementsByName("postmortemStatus")[0].value=str.split("@")[3];
		
		if(document.getElementsByName("postmortemStatus")[0].value==<%=MortuaryConfig.POSTMORTEM_STATUS_REQUEST_RAISED%>)
			submitPage('ACCEPT');
		else
			alert("The Postmortem Request is Approved");	
	}
}

function rejectPostmortemReq()
{
	var count=0;
	for (var i=0;i<document.getElementsByName("selectedPostmortemReq").length;i++)
	{
		if(document.getElementsByName("selectedPostmortemReq")[i].checked)
		{
			document.getElementsByName("postmortemRequestValue")[0].value=document.getElementsByName("selectedPostmortemReq")[i].value;
			count++;
			break;
		}
	}
	
	if(count==0)
	{
		alert("Please Select a Postmortem Request")
	}
	else
	{
		var str=document.getElementsByName("postmortemRequestValue")[0].value;
		document.getElementsByName("postmortemId")[0].value=str.split("@")[0];
		document.getElementsByName("deceasedNo")[0].value=str.split("@")[1];
		document.getElementsByName("postmortemType")[0].value=str.split("@")[2];
		
		if(document.getElementsByName("postmortemType")[0].value==<%=MortuaryConfig.POSTMORTEM_TYPE_CLINICAL%>)
			submitPage('REJECT');
		else
			alert("You Cannot Canceled The Forensic Postmortem Request. \n You Have To Waveoff The Forensic Postmortem Request.");	
	}
}

function validateOpinionAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].requestedOpinion,"Opinion For"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function deleteOpinionRow(obj1,obj2)
{
	document.getElementsByName("hiddenOpinionCode")[0].value=obj1;
	document.getElementsByName("hiddenOpinionName")[0].value=obj2;
	submitForm21('DELETEOPINION')
}


function validateItemAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].requestedItem,"Item Name"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function deleteItemRow(obj1,obj2)
{
	document.getElementsByName("hiddenItemCode")[0].value=obj1;
	document.getElementsByName("hiddenItemName")[0].value=obj2;
	submitForm21('DELETEITEM')
}

function validateAcceptSave()
{
	var valid=true;
	if(validateOpinionSave() && validateConsentSave() && validateAcceptanceSave())
		valid=true;
	else
		valid=false;
	
	return valid;		
}

function validateConsentSave()
{
	var valid=false;
	if(document.getElementsByName("consentStatus")[0].value==<%=MortuaryConfig.CONSENT_NOT_REQUIRED%>)
	{
		valid=true;
	}
	if(document.getElementsByName("consentStatus")[0].value==<%=MortuaryConfig.CONSENT_RECEIVED%>)
	{
		valid=true;
	}
	if(document.getElementsByName("consentStatus")[0].value==<%=MortuaryConfig.CONSENT_RAISED%>)
	{
		if(document.getElementsByName("isConsentTaken")[0].checked)
		{
			if(isEmpty(document.forms[0].relativeName,"Relative Name")
				&& comboValidation(document.forms[0].relativeCode,"Relationship")
				&& isEmpty(document.forms[0].relativeAddress,"Relative Address")
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
		else
		{
			alert("Please Take The Consent From Deceased Relative");
			valid=false;
		}	
	}
	
	return valid;	
}

function validateOpinionSave()
{
	var valid=true;
	var count=0;
	var revokeOpinionLen=document.getElementsByName("revokeOpinion").length;
	for(var i=0;i<revokeOpinionLen;i++)
	{
		if(document.getElementsByName("revokeOpinion")[i].checked)
			count++;
	}
	
	if(document.getElementsByName("addOpinionFlag")[0].value==<%=MortuaryConfig.NO%>)
	{
		if(document.getElementsByName("requestedOpinion")[0] && document.getElementsByName("requestedOpinion")[0].value=="-1")
		{
			if(revokeOpinionLen==count)
			{
				alert("Please At Least Add An Opinion");
				valid=false;
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
	}
	else
	{
		valid=true;
	}
	
	return valid;
}

function validateAcceptanceSave()
{
	var valid=true;
	if(comboValidation(document.forms[0].autopsyType,"Autopsy Type")
		&& comboValidation(document.forms[0].approvedBy,"Approved By")
		&& validateIncharge())
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateIncharge()
{
	var vaid=true;
	if(document.getElementsByName("conductedBy")[0].checked)
	{
		if(comboValidation(document.forms[0].teamEmployeeIncharge,"Incharge"))
			valid=true;
		else
			valid=false;
	}
	else
	{
		valid=validateInchargeAdd();
	}
	return valid;
}

function clearForm()
{
	document.getElementsByName("autopsyType")[0].value="-1";
	document.getElementsByName("conductedBy")[0].value="-1";
	document.getElementsByName("employeeIncharge")[0].value="-1";
	document.getElementsByName("approvedBy")[0].value="-1";
}

function hideUnhide(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function validateCancelSave()
{
	if(document.getElementsByName("cancelReason")[0].value=="")
	{
		alert("Please Enter The Cancel Reason");
		document.getElementsByName("cancelReason")[0].focus();
		return false;
	}
	else
		return true;
}

function clearCancelForm()
{
	document.getElementsByName("cancelReason")[0].value="";
}

function showTeamPanelDiv()
{
	if(document.getElementsByName("conductedBy")[0].checked)
	{
		document.getElementById("divTeamLabel").style.display="block";
		document.getElementById("divTeamControl").style.display="block";
		document.getElementById("divPanelLabel").style.display="none";
		document.getElementById("divPanelControl").style.display="none";
		document.getElementById("divPanelIncharge").style.display="none";
	}
	else
	{
		document.getElementById("divTeamLabel").style.display="none";
		document.getElementById("divTeamControl").style.display="none";
		document.getElementById("divPanelLabel").style.display="block";
		document.getElementById("divPanelControl").style.display="block";
		document.getElementById("divPanelIncharge").style.display="block";
	}
}


function validateInchargeAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].employeeIncharge,"Employee Incharge")
		&& comboValidation(document.forms[0].roleId,"Role Name"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function deleteInchargeRow(obj1,obj2)
{
	document.getElementsByName("hiddenInchargeId")[0].value=obj1;
	document.getElementsByName("hiddenInchargeName")[0].value=obj2;
	submitForm21('DELETEPANELINCHARGE');
}

function showAddOpinion()
{
	document.getElementById("divNewOpinionAdd").style.display="block";
}

function showAddItem()
{
	document.getElementById("divNewItemAdd").style.display="block";
}

function showReceivedConsentDtl()
{
	if(document.getElementsByName("isConsentTaken")[0].checked)
		document.getElementById("divConsentReceivedRelativeDtl").style.display="block";
	else
		document.getElementById("divConsentReceivedRelativeDtl").style.display="none";	
}


function showLegends(){
	  document.getElementById("divLegends").style.display=""; 
	}
	
function showLegendsNone(){
	  document.getElementById("divLegends").style.display="none";
	}
	
	
</script>

<body>
	<html:form action="/postmortemReqAcceptance">
		<his:TransactionContainer>
		<his:statusUnsuccessfull>
			<his:TitleTag name="Postmortem Request Acceptance/Cancel">
			</his:TitleTag>
		</his:statusUnsuccessfull>
			<his:statusList>
			<his:TitleTag name="Postmortem Request Acceptance/Cancel">
			</his:TitleTag>
			
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
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceasedNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="requestDate"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="postmortemType"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="postmortem"/>
											<bean:message key="status"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="arrPostReqListVO" name="<%=MortuaryConfig.ARR_POSTMORTM_REQUEST_LIST_VO %>" type="hisglobal.vo.PostmortemRequestDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="PostmortemRequestAcceptanceFB" property="selectedPostmortemReq" value="<%=arrPostReqListVO.getPostmortemId()+'@'+arrPostReqListVO.getDeceasedNo()+'@'+arrPostReqListVO.getPostmortemType()+'@'+arrPostReqListVO.getPostmortemStatus()%>" tabindex="1" ></html:radio>
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="<%=arrPostReqListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrPostReqListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="center">
										<font color="<%=arrPostReqListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrPostReqListVO.getDeceasedName() %>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="<%=arrPostReqListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrPostReqListVO.getRequestDateTime() %>
										</font>	
									</div>
								</td>
								
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="<%=arrPostReqListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrPostReqListVO.getPostmortemTypeName() %>
										</font>	
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="<%=arrPostReqListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrPostReqListVO.getPostmortemStatusLabel() %>
										</font>	
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>	
				
				 <his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="5%">
					<font color="#990000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>red</b>
					</font>
				</td>
				<td width="5%"></td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Mlc Casesheet
					</font>
					</div>
				</td>				
			</tr>
			
		</table>
		
	</his:ContentTag>
	</div>			
				
			</his:statusList>
			
			<his:statusTransactionInProcess>
			<logic:equal name="PostmortemRequestAcceptanceFB" property="acceptanceFlag" value="<%=MortuaryConfig.ACCEPTANCE_FLAG_ACCEPT %>">
				<his:TitleTag name="Postmortem Request Acceptance">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="PostmortemRequestAcceptanceFB" property="acceptanceFlag" value="<%=MortuaryConfig.ACCEPTANCE_FLAG_REJECT %>">
				<his:TitleTag name="Postmortem Request Canceled">
				</his:TitleTag>
			</logic:equal>	
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
				<%PostmortemRequestDetailVO postmortemReqVO=(PostmortemRequestDetailVO)session.getAttribute(MortuaryConfig.POSTMORTEM_REQUEST_DETAIL_VO); %>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="postmortem"/>
										<bean:message key="requestDate"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getRequestDateTime() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="postmortem"/>
										<bean:message key="status"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getPostmortemStatus() %>
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<%if(postmortemReqVO.getCaseNo()!=null && postmortemReqVO.getCaseNo()!=""){ %>
				<his:SubTitleTag name="Police Verification Detail">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div align="right" >
									<img id="imgPoliceVerification" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<div id="divPoliceVerification" style="display: none;">
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="policecaseno"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getCaseNo() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="policestation"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getPoliceStation() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="docketNo"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getDocketNo() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="officerincharge" />
										<bean:message key="name" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getOfficerIncharge() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="officerincharge" />
											<bean:message key="designation" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getIoDesignation() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="officerincharge" />
											<bean:message key="batchno" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getIoBatchNo() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dutyOfficer" />
												<bean:message key="name" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getDutyOffName() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dutyOfficer" />
												<bean:message key="designation" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getDutyOffDesignation() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dutyOfficer" />
												<bean:message key="batchno" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getDutyOffBatchNo() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deathPlace"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getDeathPlace() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deathdate"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getDeathDate() %>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="incidenceDate"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getIncidenceDate() %>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="caseremarks" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3">
								<div align="left">
									&nbsp;<%=postmortemReqVO.getCaseRemarks() %>
								</div>
							</td>	
						</tr>
					</table>
				</his:ContentTag>	
				</div>	
				<%} %>
				<logic:equal name="PostmortemRequestAcceptanceFB" property="acceptanceFlag" value="<%=MortuaryConfig.ACCEPTANCE_FLAG_ACCEPT %>">
				<his:SubTitleTag name="Opinion Requested">
					<div align="right">
						<img class='button' src='<his:path src="/hisglobal/images/plus.gif"/>'  style=cursor:pointer onclick ="showAddOpinion()" onkeypress="if(event.keyCode==13) showAddOpinion()") tabindex="1" title="Add New Opinion">	
					</div>
				</his:SubTitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="revoke"/>
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="opinion"/>
									</font>
								</div>
							</td>
							<td width="65%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="opinion"/>
										<bean:message key="remarks"/>
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="requestedOpinionVO" name="<%=MortuaryConfig.ARR_OPINION_REQUESTED_VO %>" type="hisglobal.vo.PostmortemOpinionReqDtlVO">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:checkbox name="PostmortemRequestAcceptanceFB" property="revokeOpinion" value="<%=requestedOpinionVO.getOpinionCode() %>" tabindex="1"></html:checkbox>		
									</div>
								</td>
								<td width="30%" class="tdfont">
									<div align="center">
										<%=requestedOpinionVO.getOpinionName() %>
									</div>
								</td>	
								<td width="65%" class="tdfont">
									<div align="center">
										<%=requestedOpinionVO.getRemarks()==null?"-":requestedOpinionVO.getRemarks() %>
									</div>
								</td>	
							</tr>	
						</logic:iterate>
					</table>
					<div id="divNewOpinionAdd" style="display: none;">
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr bgcolor="#086FA6 "> 
								<td width="20%" colspan="3">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												New Opinion
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="30%" class="tdfonthead" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="opinion"/>
												<bean:message key="for"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="65%" class="tdfonthead" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="opinion"/>
												<bean:message key="remarks"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="5%" class="tdfonthead" >
									<div align="center">
										
									</div>
								</td>
							</tr>
							<%List lstopinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_REQUESTED);
								if(lstopinion.size()>0){%>
							<tr>
								<td width="30%" class="tdfont" >
									<div align="center">
										<html:select name="PostmortemRequestAcceptanceFB"  property="requestedOpinion" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.OPINION_LIST_NOT_REQUESTED%>">
											<html:options collection="<%= MortuaryConfig.OPINION_LIST_NOT_REQUESTED%>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="65%" class="tdfont" >
									<div align="center">
										<html:textarea name="PostmortemRequestAcceptanceFB" property="opinionRemark" rows="1" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea> 
									</div>
								</td>
								<td width="5%" class="tdfont" >
									<div align="center">
										<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateOpinionAdd()) submitForm21('ADDOPINION') ;" onclick="if(validateOpinionAdd()) submitForm21('ADDOPINION')" tabindex="1" >
									</div>
								</td>
							</tr>
							<%} %>
						</table>
					</div>
				</his:ContentTag>
				<%if(session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO)!=null){ %>
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<logic:iterate id="opinionReqVO" name="<%=MortuaryConfig.ARR_OPINION_REQUEST_VO %>" type="hisglobal.vo.PostmortemOpinionReqDtlVO">
							<tr>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=opinionReqVO.getOpinionName() %>
									</div>
								</td>
								<td class="tdfont" width="65%" >
									<div align="center">
										<%=opinionReqVO.getRemarks().equals("")?"-":opinionReqVO.getRemarks() %>
									</div>
								</td>
								<td class="tdfont" width="5%" >
									<div align="center">
										<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteOpinionRow('<%=opinionReqVO.getOpinionCode() %>','<%=opinionReqVO.getOpinionName() %>') ;" onclick=" deleteOpinionRow('<%=opinionReqVO.getOpinionCode() %>','<%=opinionReqVO.getOpinionName() %>')" tabindex="1" >
										<html:hidden name="PostmortemRequestAcceptanceFB" property="hiddenOpinionCode"/>
										<html:hidden name="PostmortemRequestAcceptanceFB" property="hiddenOpinionName"/>
									</div>
								</td>
							</tr>	
						</logic:iterate>
					</table>
				</his:ContentTag>	
				<%} %>
				
				<his:SubTitleTag name="Item Requested To Be Preserved">
					<div align="right">
						<img class='button' src='<his:path src="/hisglobal/images/plus.gif"/>'  style=cursor:pointer onclick ="showAddItem()" onkeypress="if(event.keyCode==13) showAddItem()") title="Add New Item" tabindex="1">	
					</div>
				</his:SubTitleTag>
				
				
				<his:ContentTag>
				<%if(session.getAttribute(MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED)!=null){ %>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="revoke"/>
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="itemName"/>
									</font>
								</div>
							</td>
							<td width="65%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="item"/>
										<bean:message key="remarks"/>
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="requestedItemVO" name="<%=MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED %>" type="hisglobal.vo.PostmortemItemReqDtlVO">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:checkbox name="PostmortemRequestAcceptanceFB" property="revokeItem" value="<%=requestedItemVO.getItemCode() %>" tabindex="1"></html:checkbox>		
									</div>
								</td>
								<td width="30%" class="tdfont">
									<div align="center">
										<%=requestedItemVO.getItemName() %>
									</div>
								</td>	
								<td width="65%" class="tdfont">
									<div align="center">
										<%=requestedItemVO.getRemarks()==null?"-":requestedItemVO.getRemarks() %>
									</div>
								</td>
							</tr>	
						</logic:iterate>
					</table>
				
				<%}else{ %>
					
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" class="tdfont">
									<div align="center">
										<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											No Item Found To Be Preserved
										</font>
									</div>	
								</td>
							</tr>
						</table>
				
					<%} %>
					
						<div id="divNewItemAdd" style="display: none;">
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr bgcolor="#086FA6 "> 
									<td width="20%" colspan="3">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<b>
													New Item
												</b>	
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="30%" class="tdfonthead" >
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<B>
													<bean:message key="itemName"/>
												</B>
											</font>	
										</div>
									</td>
									<td width="65%" class="tdfonthead" >
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<B>
													<bean:message key="item"/>
													<bean:message key="remarks"/>
												</B>
											</font>	
										</div>
									</td>
									<td width="5%" class="tdfonthead" >
										<div align="center">
											
										</div>
									</td>
								</tr>
								<%List lstItem=(List)session.getAttribute(MortuaryConfig.ITEM_LIST_NOT_REQUESTED);
								if(lstItem.size()>0){%>
								<tr>
									<td width="30%" class="tdfont" >
										<div align="center">
											<html:select name="PostmortemRequestAcceptanceFB"  property="requestedItem" tabindex="1" >
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ITEM_LIST_NOT_REQUESTED%>">
												<html:options collection="<%= MortuaryConfig.ITEM_LIST_NOT_REQUESTED%>" property="value" labelProperty="label" />
												</logic:present>
											</html:select>
										</div>
									</td>
									<td width="65%" class="tdfont" >
										<div align="center">
											<html:textarea name="PostmortemRequestAcceptanceFB" property="itemRemark" rows="1" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea> 
										</div>
									</td>
									<td width="5%" class="tdfont" >
										<div align="center">
											<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateItemAdd()) submitForm21('ADDITEM') ;" onclick="if(validateItemAdd()) submitForm21('ADDITEM')" tabindex="1" >
										</div>
									</td>
								</tr>
								<%} %>
							</table>
						</div>	
					</his:ContentTag>	
					<%if(session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO)!=null){ %>
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
							<logic:iterate id="itemReqVO" name="<%=MortuaryConfig.ARR_ITEM_REQUEST_VO %>" type="hisglobal.vo.PostmortemItemReqDtlVO">
								<tr>
									<td class="tdfont" width="30%" >
										<div align="center">
											<%=itemReqVO.getItemName() %>
										</div>
									</td>
									<td class="tdfont" width="65%" >
										<div align="center">
											<%=itemReqVO.getRemarks().equals("")?"-":itemReqVO.getRemarks() %>
										</div>
									</td>
									<td class="tdfont" width="5%" >
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteItemRow('<%=itemReqVO.getItemCode() %>','<%=itemReqVO.getItemName() %>') ;" onclick=" deleteItemRow('<%=itemReqVO.getItemCode() %>','<%=itemReqVO.getItemName() %>')" tabindex="1" >
											<html:hidden name="PostmortemRequestAcceptanceFB" property="hiddenItemCode"/>
											<html:hidden name="PostmortemRequestAcceptanceFB" property="hiddenItemName"/>
										</div>
									</td>
								</tr>	
							</logic:iterate>
						</table>
					</his:ContentTag>	
				<%} %>
				
				
				<logic:equal name="PostmortemRequestAcceptanceFB" property="consentStatus" value="<%=MortuaryConfig.CONSENT_RECEIVED %>">
					<his:SubTitleTag name="Consent Detail">
					</his:SubTitleTag> 
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
							<tr>
								<td width="100%" class="tdfont">
									<div align="center">
										Consent Received
									</div>	
								</td>
							</tr>
						</table>	
					</his:ContentTag>
				</logic:equal>	
				
				<logic:equal name="PostmortemRequestAcceptanceFB" property="consentStatus" value="<%=MortuaryConfig.CONSENT_RAISED %>">
					<his:SubTitleTag name="Consent Received From Detail">
					</his:SubTitleTag> 
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="consent"/>
											<bean:message key="status"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										&nbsp; Not Received
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="isConsentTaken"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:checkbox name="PostmortemRequestAcceptanceFB" property="isConsentTaken" onclick="showReceivedConsentDtl()" tabindex="1"></html:checkbox>
									</div>
								</td>
							</tr>
						</table>
						<div id="divConsentReceivedRelativeDtl">
							<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
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
											<html:text name="PostmortemRequestAcceptanceFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
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
											<html:select name="PostmortemRequestAcceptanceFB" property="relativeCode" tabindex="1">
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
											<html:textarea name="PostmortemRequestAcceptanceFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
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
											<html:text name="PostmortemRequestAcceptanceFB" property="relativeContactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
							</table>
						</div>		
					</his:ContentTag>
				</logic:equal>
				
				<his:SubTitleTag name="Acceptance Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="autopsy"/>
										<bean:message key="type"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="PostmortemRequestAcceptanceFB"  property="autopsyType" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESSENTIAL_AUTOPSY_TYPE_LIST%>">
										<html:options collection="<%= MortuaryConfig.ESSENTIAL_AUTOPSY_TYPE_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="approvedBy"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="PostmortemRequestAcceptanceFB"  property="approvedBy" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.EMPLOYEE_APPROVED_BY_LIST%>">
										<html:options collection="<%= MortuaryConfig.EMPLOYEE_APPROVED_BY_LIST%>" property="value" labelProperty="label" />
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
										<bean:message key="conductedBy"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:radio name="PostmortemRequestAcceptanceFB" property="conductedBy" value="<%=MortuaryConfig.POSTMORTEM_CONDUCTED_BY_TEAM%>" onclick="showTeamPanelDiv()" tabindex="1"></html:radio>
									<bean:message key="team"/>
									<html:radio name="PostmortemRequestAcceptanceFB" property="conductedBy" value="<%=MortuaryConfig.POSTMORTEM_CONDUCTED_BY_PANEL%>" onclick="showTeamPanelDiv()" tabindex="1"></html:radio>
									<bean:message key="panel"/>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right" id="divTeamLabel">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="incharge"/>
									</font>
								</div>
								<div align="left" id="divPanelLabel">
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left" id="divTeamControl">
									<html:select name="PostmortemRequestAcceptanceFB"  property="teamEmployeeIncharge" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.EMPLOYEE_INCHARGE_LIST%>">
										<html:options collection="<%= MortuaryConfig.EMPLOYEE_INCHARGE_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
								<div align="left" id="divPanelControl">
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				<div id="divPanelIncharge" style="display: none; ">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="40%" class="tdfonthead" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="incharge"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="40%" class="tdfonthead" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<B>
												<bean:message key="roleName"/>
											</B>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfonthead" >
									<div align="center">
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="40%" class="tdfont" >
									<div align="center">
										<html:select name="PostmortemRequestAcceptanceFB"  property="employeeIncharge" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.EMPLOYEE_INCHARGE_LIST%>">
											<html:options collection="<%= MortuaryConfig.EMPLOYEE_INCHARGE_LIST%>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="40%" class="tdfont" >
									<div align="center">
										 <html:select name="PostmortemRequestAcceptanceFB"  property="roleId" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.POSTMORTEM_INCHARGE_ROLE_LIST%>">
											<html:options collection="<%= MortuaryConfig.POSTMORTEM_INCHARGE_ROLE_LIST%>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="20%" class="tdfont" >
									<div align="center">
										<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateInchargeAdd()) submitForm21('ADDPANELINCHARGE') ;" onclick="if(validateInchargeAdd()) submitForm21('ADDPANELINCHARGE')" tabindex="1" >
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>	
					<%if(session.getAttribute(MortuaryConfig.ARR_TEAM_INCHARGE_VO)!=null){ %>
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
							<logic:iterate id="teamVO" name="<%=MortuaryConfig.ARR_TEAM_INCHARGE_VO %>" type="hisglobal.vo.PostmortemTeamDetailVO">
								<tr>
									<td class="tdfont" width="40%" >
										<div align="center">
											<%=teamVO.getEmpName() %>
										</div>
									</td>
									<td class="tdfont" width="40%" >
										<div align="center">
											<%=teamVO.getRoleName() %>
										</div>
									</td>
									<td class="tdfont" width="20%" >
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteInchargeRow('<%=teamVO.getEmpId() %>','<%=teamVO.getEmpName() %>') ;" onclick=" deleteInchargeRow('<%=teamVO.getEmpId() %>','<%=teamVO.getEmpName() %>')" tabindex="1" >
											<html:hidden name="PostmortemRequestAcceptanceFB" property="hiddenInchargeId"/>
											<html:hidden name="PostmortemRequestAcceptanceFB" property="hiddenInchargeName"/>
										</div>
									</td>
								</tr>	
							</logic:iterate>
						</table>
					</his:ContentTag>	
				<%} %>
				</div>
				
				</logic:equal>
				<logic:equal name="PostmortemRequestAcceptanceFB" property="acceptanceFlag" value="<%=MortuaryConfig.ACCEPTANCE_FLAG_REJECT %>">
					<his:SubTitleTag name="Opinion Requested">
					</his:SubTitleTag>
					<his:ContentTag>
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
							<tr>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="opinion"/>
										</font>
									</div>
								</td>
								<td width="70" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="opinion"/>
											<bean:message key="remarks"/>
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate id="requestedOpinionVO" name="<%=MortuaryConfig.ARR_OPINION_REQUESTED_VO %>" type="hisglobal.vo.PostmortemOpinionReqDtlVO">
								<tr>
									<td width="30%" class="tdfont">
										<div align="center">
											<%=requestedOpinionVO.getOpinionName() %>
										</div>
									</td>	
									<td width="70%" class="tdfont">
										<div align="center">
											<%=requestedOpinionVO.getRemarks()==null?"-":requestedOpinionVO.getRemarks() %>
										</div>
									</td>	
								</tr>	
							</logic:iterate>
						</table>
					</his:ContentTag>
					
					<his:SubTitleTag name="Item Requested To Be Preserved">
					</his:SubTitleTag>
					<%if(session.getAttribute(MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED)!=null){ %>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="itemName"/>
										</font>
									</div>
								</td>
								<td width="70%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="item"/>
											<bean:message key="remarks"/>
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate id="requestedItemVO" name="<%=MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED %>" type="hisglobal.vo.PostmortemItemReqDtlVO">
								<tr>
									<td width="30%" class="tdfont">
										<div align="center">
											<%=requestedItemVO.getItemName() %>
										</div>
									</td>	
									<td width="70%" class="tdfont">
										<div align="center">
											<%=requestedItemVO.getRemarks()==null?"-":requestedItemVO.getRemarks() %>
										</div>
									</td>
								</tr>	
							</logic:iterate>
						</table>
					</his:ContentTag>
					<%}else{ %>
						<his:ContentTag>	
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="100%" class="tdfont">
										<div align="center">
											<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												No Item To Be Preserved
											</font>
										</div>	
									</td>
								</tr>
							</table>
						</his:ContentTag>
					<%} %>
					<his:SubTitleTag name="Cancelled Reason">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" class="tdfont">
									<div align="center">
										<html:textarea name="PostmortemRequestAcceptanceFB" property="cancelReason" rows="3" cols="100" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
				</logic:equal>
				
				 
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusList>
					<img class="button" src='<his:path src="/hisglobal/images/Accept.png"/>'  style=cursor:pointer tabindex="1" onclick ="acceptPostmortemReq()" onkeypress="if(event.keyCode==13) acceptPostmortemReq()">
					<img class="button" src='<his:path src="/hisglobal/images/reject_tab.gif"/>'  style=cursor:pointer tabindex="1" onclick =" rejectPostmortemReq()" onkeypress="if(event.keyCode==13) rejectPostmortemReq()">
				
				
				
				</his:statusList>
					
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc();" onkeypress="if(event.keyCode==13)cancelFunc();">
				</his:statusUnsuccessfull>
				
				<his:statusTransactionInProcess>
					<logic:equal name="PostmortemRequestAcceptanceFB" property="acceptanceFlag" value="<%=MortuaryConfig.ACCEPTANCE_FLAG_REJECT %>">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateCancelSave()) submitPage('CANCELSAVE')" onkeypress="if(event.keyCode==13)if(validateCancelSave())submitPage('CANCELSAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc();" onkeypress="if(event.keyCode==13) cancelFunc();">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearCancelForm()" onkeypress="if(event.keyCode==13) clearCancelForm()">
					</logic:equal>
					<logic:equal name="PostmortemRequestAcceptanceFB" property="acceptanceFlag" value="<%=MortuaryConfig.ACCEPTANCE_FLAG_ACCEPT %>">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateAcceptSave()) submitPage('APPROVED')" onkeypress="if(event.keyCode==13)if(validateAcceptSave())submitPage('APPROVED')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc();" onkeypress="if(event.keyCode==13) cancelFunc();">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
					</logic:equal>
				</his:statusTransactionInProcess>
				
			</his:ButtonToolBarTag>
		</his:TransactionContainer>
		
		<html:hidden name="PostmortemRequestAcceptanceFB" property="hmode"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="postmortemId"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="deceasedNo"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="postmortemType"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="postmortemRequestValue"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="acceptanceFlag"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="addOpinionFlag"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="postmortemStatus"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="addItemFlag"/>
		<html:hidden name="PostmortemRequestAcceptanceFB" property="consentStatus"/>
		
		
	</html:form>
	<his:status/>
</body>

</html>		
		