<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<html>
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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

window.onload=function()
{
	if(document.getElementsByName("printFlag")[0].value=="1")
	{
		openHandoverReceiptPopup();
	}
	else
	{
		showHandoverDetail();
		showRelativeHandoverDtl();
	}
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDeceasedDetail(obj)
{
	document.getElementsByName("patCrNo")[0].value=obj.value.split("@")[1];
	document.getElementsByName("deceasedNo")[0].value=obj.value.split("@")[0];
	submitForm('DECEASEDDTL')
}

function showHandoverDetail()
{
	if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF%> || document.getElementsByName("isHandoverTo")[0].value=="-1")
	{
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
		document.getElementById("blank1").style.display="block";
		document.getElementById("blank2").style.display="block";
		document.getElementById("blank3").style.display="none";
		document.getElementById("blank4").style.display="none";
	}
	if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE%>)
	{
		document.getElementById("divBodyHandoverToPolice").style.display="block";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
		document.getElementById("blank1").style.display="block";
		document.getElementById("blank2").style.display="block";
		document.getElementById("blank3").style.display="none";
		document.getElementById("blank4").style.display="none";
	}
	if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE%>)
	{
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="block";
		if(document.getElementsByName("relativeExist")[0].value==<%=MortuaryConfig.RELATIVE_EXIST_TO_HANDOVER_YES%>)
		{
			document.getElementById("blank1").style.display="none";
			document.getElementById("blank2").style.display="none";
			document.getElementById("blank3").style.display="block";
			document.getElementById("blank4").style.display="block";
		}
		else
		{
			document.getElementById("blank1").style.display="block";
			document.getElementById("blank2").style.display="block";
			document.getElementById("blank3").style.display="none";
			document.getElementById("blank4").style.display="none";
		}	
	}
}

function validateSave()
{
	var valid=false;
	if(document.getElementsByName("isHandover")[0].value==<%=MortuaryConfig.YES%>)
	{
		if(document.getElementsByName("isHandoverTo")[0].value=="-1")
		{
			alert("Please Select Hand Over To");
			document.getElementsByName("isHandoverTo")[0].focus();
			valid=false;
		}
		
		if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF%>)
		{
			valid=true;
		}
		
		if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE%>)
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
		
		if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE%>)
		{
			if(document.getElementsByName("existingRelativeFlag")[1].checked)
			{
				if(isEmpty(document.forms[0].newHandoverToName,"Relative Name")
					&& comboValidation(document.forms[0].newRelativeCode,"Relationship")
					&& isEmpty(document.forms[0].newHandoverToAddress,"Address of Relative")
					&& isEmpty(document.forms[0].newHandoverToPhone,"Relative Contact No")
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
				var count=0;
				for(var i=0;i<document.getElementsByName("relativeExistRadioBtn").length;i++)
				{
					if(document.getElementsByName("relativeExistRadioBtn")[i].checked)
						count++;
				}
				
				if(count>0)
					valid=true;
				else
				{
					alert("Please Select a Existing Record");
					valid=false;
				}	
			}	
		}
	}
	else
	{
		alert("Deceased Cannot Be Handover Until The Postmortem Completed");
		valid=false;
	}
	return valid;
}

function clearForm()
{
	if(document.getElementsByName("isMlcCase")[0].value==<%=MortuaryConfig.IS_MLC_NO%>)
		document.getElementsByName("isHandoverTo")[0].value="-1";
	
	document.getElementsByName("newHandoverToName")[0].value="";
	document.getElementsByName("newRelativeCode")[0].value="-1";
	document.getElementsByName("newHandoverToAddress")[0].value="";
	document.getElementsByName("newHandoverToPhone")[0].value="";
	document.getElementsByName("officerName")[0].value="";
	document.getElementsByName("officerDesignation")[0].value="";
	document.getElementsByName("officerBadgeNo")[0].value="";
	
	showHandoverDetail();
	
}

function showRelativeHandoverDtl()
{
	if(document.getElementsByName("existingRelativeFlag")[0].checked)
	{
		document.getElementById("divBodyHandoverToRelativeExisting").style.display="block";
		document.getElementById("divBodyHandoverToRelativeNew").style.display="none";
	}
	if(document.getElementsByName("existingRelativeFlag")[1].checked)
	{
		document.getElementById("divBodyHandoverToRelativeExisting").style.display="none";
		document.getElementById("divBodyHandoverToRelativeNew").style.display="block";
	}
}


function openHandoverReceiptPopup()
{
	var deceasedNo=document.getElementsByName("deceasedNo")[0].value;
	var path='/HISClinical/mortuary/deceasedHandover.cnt?hmode=PRINT&deceasedNo='+deceasedNo;
	openPrintPopup(path,300,700);
	document.getElementsByName("printFlag")[0].value="0";
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
		child.moveTo(250,250);
		child.focus(); 
}

</script>

<body>
	<html:form action="/deceasedHandover">
		<his:TransactionContainer>
			<his:TitleTag name="Deceased Handover Detail">
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
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deathdate"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="receiveDate"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate id="arrDeceasedListVO" name="<%=MortuaryConfig.DECEASED_LIST_TO_HANDOVER %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="DeceasedHandoverFB" property="selectedDeceased" value="<%=arrDeceasedListVO.getDeceasedNo()+'@'+ arrDeceasedListVO.getPatCrNo()%>" onclick="getDeceasedDetail(this)" tabindex="1" ></html:radio>
									
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
											<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
											<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
										</font>	
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedType() %>
										</font>	
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeathDate() %>
										</font>	
									</div>
								</td>
								
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getReceivedDateTime() %>
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
				<his:SubTitleTag name="Storage And Postmortem Status Detail">
				</his:SubTitleTag>
				<his:ContentTag>
				<logic:equal name="DeceasedHandoverFB" property="bodyStatus" value="<%=MortuaryConfig.BODY_STATUS_IN_CHAMBER %>">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="chamber"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<bean:write name="DeceasedHandoverFB" property="chamberName"/>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="rack"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<bean:write name="DeceasedHandoverFB" property="rackName"/>
								</div>
							</td>
						</tr>
					</table>		
				</logic:equal>
				<logic:notEqual name="DeceasedHandoverFB" property="bodyStatus" value="<%=MortuaryConfig.BODY_STATUS_IN_CHAMBER %>">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="100%" class="tdfont">
								<div align="center">
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Deceased Is On Stretcher
									</font>
								</div>
							</td>
						</tr>
					</table>		
				</logic:notEqual>
				
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="postmortemStatus"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<bean:write name="DeceasedHandoverFB" property="postmortemStatus"/>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
							</td>
							<td width="25%" class="tdfont">
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				
				<logic:equal name="DeceasedHandoverFB" property="isHandover" value="<%=MortuaryConfig.YES %>">
					<his:SubTitleTag name="Deceased Handover Detail">
					</his:SubTitleTag>
					
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
										<logic:equal name="DeceasedHandoverFB" property="isMlcCase" value="<%=MortuaryConfig.IS_MLC_NO %>">
											<html:select name="DeceasedHandoverFB" property="isHandoverTo" onchange="showHandoverDetail()" tabindex="1" >
												<html:option value="-1">Select Value</html:option>
												<html:option value="<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE %>">Police</html:option>
												<html:option value="<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE %>">Relative</html:option>
											</html:select>
										</logic:equal>
										<logic:equal name="DeceasedHandoverFB" property="isMlcCase" value="<%=MortuaryConfig.IS_MLC_YES %>">
											&nbsp;<bean:message key="police"/>
											<html:hidden name="DeceasedHandoverFB" property="isHandoverTo" value="<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE %>"/>
										</logic:equal>
									</div>
								</td>
								
								<td width="25%" class="tdfonthead" >
									<div id="blank1"></div>
									
									<div id="blank3" align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relative"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div id="blank2"></div>
									
									<div id="blank4" align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="exist"/>
											</b>	
										</font>
										<html:radio name="DeceasedHandoverFB" property="existingRelativeFlag" value="<%=MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING%>" onclick="showRelativeHandoverDtl()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="new"/>
											</b>
										</font>	
										<html:radio name="DeceasedHandoverFB" property="existingRelativeFlag" value="<%=MortuaryConfig.DEAD_BODY_HANDOVER_NEW%>" onclick="showRelativeHandoverDtl()" tabindex="1"  ></html:radio>
									</div>
								</td>
								
							</tr>
						</table>
						
						<div id="divBodyHandoverToRelative" style="display: none;">
							<div id="divBodyHandoverToRelativeExisting">
							<%if(session.getAttribute(MortuaryConfig.ARR_DECEASED_RELATIVE_VO)!=null){ %>
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="select"/>
													</b>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="name"/>
													</b>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="realtionship"/>
													</b>
												</font>
											</div>
										</td>
										<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="address"/>
													</b>
												</font>
											</div>
										</td>
										<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<b>
														<bean:message key="contactNo"/>
													</b>
												</font>
											</div>
										</td>
									</tr>
									<logic:iterate name="<%=MortuaryConfig.ARR_DECEASED_RELATIVE_VO %>" id="relativeVO" type="hisglobal.vo.DeceasedRelativeDtlVO" indexId="idx">
										<tr>
											<td width="5%" class="tdfont">
												<div align="center">
													<html:radio name="DeceasedHandoverFB" property="relativeExistRadioBtn" value="<%=idx.toString() %>" tabindex="1" ></html:radio>
												</div>	
											</td>
											<td width="25%" class="tdfont">
												<div align="center">
													<%=relativeVO.getRelativeName() %>
												</div>	
											</td>
											<td width="15%" class="tdfont">
												<div align="center">
													<%=relativeVO.getRelativeCodeName() %>
												</div>	
											</td>
											<td width="40%" class="tdfont">
												<div align="center">
													<%=relativeVO.getRelativeAddress() %>
												</div>	
											</td>
											<td width="15%" class="tdfont">
												<div align="center">
													<%=relativeVO.getRelativeContactNo() %>
												</div>	
											</td>
										</tr>
									</logic:iterate>
								</table>
								<%} %>
								
							</div>
							
							<div id="divBodyHandoverToRelativeNew">
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
												<html:text name="DeceasedHandoverFB" property="newHandoverToName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
												<html:select name="DeceasedHandoverFB" property="newRelativeCode" tabindex="1" >
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
												<html:textarea name="DeceasedHandoverFB" property="newHandoverToAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
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
												<html:text name="DeceasedHandoverFB" property="newHandoverToPhone" maxlength="30"   onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
											</div>
										</td>
									</tr>
								</table>
							</div>
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
										<html:text name="DeceasedHandoverFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
											<html:text name="DeceasedHandoverFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
											<html:text name="DeceasedHandoverFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
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
											<html:text name="DeceasedHandoverFB" property="policeStnHandOver" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
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
											<html:text name="DeceasedHandoverFB" property="policeContactNo" maxlength="15" onkeypress="return validatePositiveIntegerWithDashOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</his:ContentTag>
				</logic:equal>	
			</his:statusTransactionInProcess>	
			
			
				<his:ButtonToolBarTag>
					<his:statusList>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
					</his:statusList>
						
					<his:statusUnsuccessfull>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					</his:statusUnsuccessfull>
					
					<his:statusTransactionInProcess>
						<logic:equal name="DeceasedHandoverFB" property="isHandover" value="<%=MortuaryConfig.YES %>">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
						</logic:equal>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
						<logic:equal name="DeceasedHandoverFB" property="isHandover" value="<%=MortuaryConfig.YES %>">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
						</logic:equal>
					</his:statusTransactionInProcess>
			
				</his:ButtonToolBarTag>
		
		</his:TransactionContainer>
		
		<html:hidden name="DeceasedHandoverFB" property="hmode"/>
		<html:hidden name="DeceasedHandoverFB" property="patCrNo" />
		<html:hidden name="DeceasedHandoverFB" property="deceasedNo" />
		<html:hidden name="DeceasedHandoverFB" property="isMlcCase" />
		<html:hidden name="DeceasedHandoverFB" property="bodyStatus" />
		<html:hidden name="DeceasedHandoverFB" property="relativeExist" />
		<html:hidden name="DeceasedHandoverFB" property="isHandover" />
		<html:hidden name="DeceasedHandoverFB" property="printFlag" />
		
	</html:form>
	<his:status/>

</body>


</html>