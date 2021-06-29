<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page import="registration.*"%>
<%@ page import="hisglobal.hisconfig.Config"%>
<%@ page import="java.util.*"%>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function validateRevokeEpisode()
{
	if(!isCheckBoxSelected())
	{
		alert("Select At Least One Episode to Revoke..");
		document.getElementsByName('chks')[0].focus();
		return false;
	}
	var remks = document.getElementsByName('selRemarks');
	for(var i=0;i<remks.length;i++)
	{
		if(remks[i].disabled == false && remks[i].value=="")
		{
			alert("Please Enter Remarks");
			remks[i].focus();
			return false;
		}
	}
	return true;
}

function callThisOnload()
{
	focusCrNo();
}

function selectDeselect(chk)
{

	var radioObject=document.getElementsByName("chks")
	//alert(radioObject.length)
	for(var i=0;i<radioObject.length;i++)
	{
		if(chk.value==i)
		{
		
			document.getElementsByName('selEpisodeCode')[i].disabled = false;
			document.getElementsByName('selEpisodeVisitNo')[i].disabled = false;
			document.getElementsByName('selMlcNo')[i].disabled = false;
			document.getElementsByName('selRemarks')[i].disabled = false;
		}
		else
		{
		
			document.getElementsByName('selEpisodeCode')[i].disabled = true;
			document.getElementsByName('selEpisodeVisitNo')[i].disabled = true;
			document.getElementsByName('selMlcNo')[i].disabled = true;
			document.getElementsByName('selRemarks')[i].value = "";
			document.getElementsByName('selRemarks')[i].disabled = true;
		}
	}
	/*for(var i=0;i<radios.length;i++)
	{
		var divId="divRemark"+radios[i].value;
		if(radios[i].checked)
			document.getElementById(divId).style.display = "block";
		else
			document.getElementById(divId).style.display = "none";
	}*/
}

function isCheckBoxSelected()
{
	var chks = document.getElementsByName('chks');
	var flag = false;
	for(var i=0;i<chks.length;i++)
		if(chks[i].checked)
			flag = true;
	return flag;
}

function submitToCancel(){
	document.getElementsByName("mode")[0].value="CANCEL"
	document.forms[0].submit();
	
}
</script>

<%	String systemDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm"); %>

<his:TitleTag>
	<his:name>
		<bean:message key="mlcToNonMlc" />
	</his:name>
	<b> <font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		
	</font> </b>
</his:TitleTag>

<his:statusNew>
	<his:InputCrNoTag name="RevokeMLCEpisodeFB"></his:InputCrNoTag>
</his:statusNew>

<his:statusUnsuccessfull>
	<jsp:include page="/registration/patientDetail.cnt" flush="true" />
</his:statusUnsuccessfull>

<his:statusTransactionInProcess>
	<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	<html:hidden name="RevokeMLCEpisodeFB" property="patCrNo"/>
	
	<his:SubTitleTag name="Conversion of Episode From MLC to Non MLC Case">
	</his:SubTitleTag>

	<logic:notEmpty name="<%=RegistrationConfig.ARR_OPEN_TODAY_MLC_EPISODE_VO%>">
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="5%" class="tdfonthead">						
					</td>
<!-- 					<td width="20%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
								<bean:message key="epicode" />
							</b></font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
								<bean:message key="visitNo" />
							</b></font>
						</div>
					</td> -->
					<td width="45%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
								<bean:message key="unit" /> <bean:message key="name" />
							</b></font>
						</div>
					</td>
					<!--<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
								<bean:message key="mlcnumber" />
							</b></font>
						</div>
					</td>
					--><td width="50%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
								<bean:message key="remarks" /><font color="#FF0000">*</font>
							</b></font>
						</div>
					</td>
				</tr>

				<logic:iterate  id="episodeVo" name="<%=RegistrationConfig.ARR_OPEN_TODAY_MLC_EPISODE_VO%>" indexId="j" type="hisglobal.vo.EpisodeVO">
				<tr> 
					<td width="5%" class="tdfont">
					<div align="center">
						<input type="radio" name="chks" value="<%=j.toString()%>" onclick="selectDeselect(this)"/>
					</div>
					</td>
<!-- 					<td width="20%" class="tdfont">
						<html:hidden name="RevokeMLCEpisodeFB" property="selEpisodeCode" value="<%=episodeVo.getEpisodeCode()%>"/>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="episodeVo" property="episodeCode" /></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont">
						<html:hidden name="RevokeMLCEpisodeFB" property="selEpisodeVisitNo" value="<%=episodeVo.getEpisodeVisitNo()%>"/>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="episodeVo" property="episodeVisitNo" /></b>
							</font>
						</div>
					</td> -->
					<td width="45%" class="tdfont">
						<html:hidden name="RevokeMLCEpisodeFB" property="selEpisodeCode" value="<%=episodeVo.getEpisodeCode()%>"/>
						<html:hidden name="RevokeMLCEpisodeFB" property="selEpisodeVisitNo" value="<%=episodeVo.getEpisodeVisitNo()%>"/>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="episodeVo" property="departmentUnit" /></b>
							</font>
						</div>
					</td>
					<html:hidden name="RevokeMLCEpisodeFB" property="selMlcNo" value="<%=episodeVo.getMlcNo()%>"/>
					<!--<td width="30%" class="tdfont">
						
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="episodeVo" property="mlcNo" /></b>
							</font>
						</div>
					</td>
					--><td width="50%" class="tdfont">
						<div align="center" >
							<html:text name="RevokeMLCEpisodeFB" property="selRemarks" disabled="true" maxlength="150" size="50" ></html:text>
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
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRevokeEpisode(),'SAVE');" tabindex="1" onclick="submitFormOnValidate(validateRevokeEpisode(),'SAVE')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToCancel();" tabindex="1" onclick="submitToCancel();">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:statusTransactionInProcess>
	<his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToCancel();" tabindex="1" onclick="submitToCancel();">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:statusNew>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitToCancel();" tabindex="1" onclick="submitToCancel();">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>

<his:status />

<html:hidden name="RevokeMLCEpisodeFB" property="hmode"/>

