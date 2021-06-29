<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.PostmortemRequestDetailVO"%>
<%@page import="hisglobal.vo.MortuaryPoliceVerificationVO"%>
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

<script type="text/javascript">

function getDeceasedDetail(obj)
{

	var count=0;
	for (var i=0;i<document.getElementsByName("selectedDeceased").length;i++)
	{
		if(document.getElementsByName("selectedDeceased")[i].checked)
		{
			document.getElementsByName("postmortemRequestValue")[0].value=document.getElementsByName("selectedDeceased")[i].value;
			count++;
			break;
		}
	}

	var str=document.getElementsByName("postmortemRequestValue")[0].value;
		document.getElementsByName("postmortemId")[0].value=str.split("@")[0];
		document.getElementsByName("deceasedNo")[0].value=str.split("@")[1];
		document.getElementsByName("postmortemType")[0].value=str.split("@")[2];
		document.getElementsByName("postmortemStatus")[0].value=str.split("@")[3];
	submitForm('DECEASEDDTL')
}


function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function clearForm()
{
	document.getElementsByName("waveoffBy")[0].value="";
	document.getElementsByName("letterNo")[0].value="";
	document.getElementsByName("waveoffReason")[0].value="";
	document.getElementsByName("approvedBy")[0].value="-1";
}

function validateSave(mode)
{

		if(document.getElementsByName("waveoffBy")[0].value=="")
		{
			alert("Please Enter Waveoff By");
			document.getElementsByName("waveoffBy")[0].focus();
			return false;
		}
		else if(document.getElementsByName("letterNo")[0].value=="")
		{
			alert("Please Enter Letter No");
			document.getElementsByName("letterNo")[0].focus();
			return false;
		}
		else if(document.getElementsByName("waveoffReason")[0].value=="")
		{
			alert("Please Enter Waveoff Reason");
			document.getElementsByName("waveoffReason")[0].focus();
			return false;
		}
		else if(document.getElementsByName("approvedBy")[0].value=="-1")
		{
			alert("Please Select Approved By");
			document.getElementsByName("approvedBy")[0].focus();
			return false;
		}
		else
		{
			submitPage(mode);
		}

}

</script>

<body>
	<html:form action="/postmortemWaveoff">
		<his:TransactionContainer>
			<his:TitleTag name="Postmortem Waveoff Detail">
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
							<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceasedNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="name"/>
										</b>	
									 </font>
								</div>
							</td>
							<td width="16%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="Apt_patAge"/>
											<bean:message key="slash"/>
									   		<bean:message key="Apt_patSex"/>
									   	</b>	
									</font>
								</div>
							</td>
							<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="postmortemNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="postmortemType"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="postmortemStatus"/>
										</b>	
									</font>
								</div>
							</td>
							
						</tr>
						<logic:iterate id="arrDeceasedListVO" name="<%=MortuaryConfig.POSTMORTEM_WAVEOFF_DETAIL_VO_ARRAY %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="PostmortemWaveoffDetailFB" property="selectedDeceased" value="<%=arrDeceasedListVO.getPostmortemId()+'@'+arrDeceasedListVO.getDeceasedNo()+'@'+arrDeceasedListVO.getPostmortemType()+'@'+arrDeceasedListVO.getPostmortemStatus()%>" onclick="getDeceasedDetail(this)" tabindex="1" ></html:radio>
									
									</div>
  								<td width="16%" class="tdfont">
									<div align="center">
										<font color="#000000%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="16%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
											<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
											<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
										</font>	
									</div>
								</td>
								<td width="16%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatAge() %>
											<bean:message key="slash"/>
											<%=arrDeceasedListVO.getPatGender() %>
										</font>	
									</div>
								</td>
								
								<td width="16%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPostmortemIdLabel() %>
										</font>	
									</div>
								</td>
								
								<td width="16%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPostmortemType() %>
										</font>	
									</div>
								</td>
								
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPostmortemStatus() %>
										</font>	
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
					
				</his:ContentTag>
			</his:statusList>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
				<%PostmortemRequestDetailVO postmortemReqVO=(PostmortemRequestDetailVO)session.getAttribute(MortuaryConfig.POSTMORTEM_REQUEST_DETAIL_VO); %>
				<%MortuaryPoliceVerificationVO mortuaryPoliceVeriVOs=(MortuaryPoliceVerificationVO)session.getAttribute(MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO); %>
				<logic:present name="<%=MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO %>">
					<logic:notEmpty name="<%=MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO %>">
						<bean:define id="mortuaryPoliceVeriVO" name="<%=MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO %>"></bean:define>
					</logic:notEmpty>
				</logic:present>
				<%if(postmortemReqVO!=null){ %>
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
				<%} %>
				<logic:present name="<%=MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO %>">
					<logic:notEmpty name="<%=MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO %>">
				
				<his:SubTitleTag name="Police Verification Detail">
					
				</his:SubTitleTag>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="caseNo"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="policeStation"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="docketNo"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="officerIncharge"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="ioDesignation"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="ioBatchNo"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="dutyOffName"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="dutyOffDesignation"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="dutyOffBatchNo"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="deathPlace"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="deathDate"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="incidenceDate"/>
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
									&nbsp;
									<bean:write name="mortuaryPoliceVeriVO" property="caseRemarks"/>
								</div>
							</td>	
						</tr>
					</table>
				</his:ContentTag>
				</logic:notEmpty>
				</logic:present>
				<his:SubTitleTag name="Waveoff Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
										<bean:message key="waveoffBy"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="PostmortemWaveoffDetailFB" property="waveoffBy" tabindex="1" maxlength="100" onkeypress="return(validateAlphaNumericOnly(event,this))"></html:text>
								</div>
							</td>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
										<bean:message key="letterNo"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="PostmortemWaveoffDetailFB" property="letterNo" tabindex="1" maxlength="50"></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
										<bean:message key="waveoffReason"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<html:textarea name="PostmortemWaveoffDetailFB" property="waveoffReason" tabindex="1" rows="2" cols="30" onkeypress="return (validateTextArea(event,this,'100')&& validateAlphaNumericOnly(event,this))"></html:textarea>
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
									<html:select name="PostmortemWaveoffDetailFB"  property="approvedBy" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.EMPLOYEE_APPROVED_BY_LIST%>">
										<html:options collection="<%= MortuaryConfig.EMPLOYEE_APPROVED_BY_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							
						</tr>	
					</table>	
				</his:ContentTag>
				
			</his:statusTransactionInProcess>
		
		
		
		<his:ButtonToolBarTag>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</his:statusList>
							
			<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				</his:statusUnsuccessfull>
				
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave('SAVE')" onkeypress="if(event.keyCode==13) validateSave('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
			
		</his:ButtonToolBarTag>
		
		</his:TransactionContainer>
		
		<html:hidden name="PostmortemWaveoffDetailFB" property="hmode"/>
		<html:hidden name="PostmortemWaveoffDetailFB" property="patCrNo" />
		<html:hidden name="PostmortemWaveoffDetailFB" property="postmortemId"/>
		<html:hidden name="PostmortemWaveoffDetailFB" property="deceasedNo"/>
		<html:hidden name="PostmortemWaveoffDetailFB" property="postmortemType"/>
		<html:hidden name="PostmortemWaveoffDetailFB" property="postmortemStatus"/>
		<html:hidden name="PostmortemWaveoffDetailFB" property="postmortemRequestValue" />
	</html:form>
	<his:status/>
</body>

</html>