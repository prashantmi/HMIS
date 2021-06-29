<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.DeceasedIdentityDtlVO"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

window.onload=function()
{
	showIdentifyByDiv();
	showRelativeHandoverDtl();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function showIdentifyByDiv()
{
	if(document.getElementsByName("isIdentifiyBy")[0].value=="-1")
	{
		document.getElementById("divRlativeId").style.display="none";
		document.getElementById("divPoliceId").style.display="none";
		document.getElementById("blank1").style.display="block";
		document.getElementById("blank2").style.display="block";
		document.getElementById("blank3").style.display="none";
		document.getElementById("blank4").style.display="none";
	}
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE%>")
	{
		document.getElementById("divRlativeId").style.display="none";
		document.getElementById("divPoliceId").style.display="block";
		document.getElementById("blank1").style.display="block";
		document.getElementById("blank2").style.display="block";
		document.getElementById("blank3").style.display="none";
		document.getElementById("blank4").style.display="none";
	}
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE%>")
	{
		document.getElementById("divRlativeId").style.display="block";
		document.getElementById("divPoliceId").style.display="none";
		if(document.getElementsByName("relativeExist")[0].value==<%=MortuaryConfig.RELATIVE_EXIST_TO_IDENTIFY_YES%>)
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
	if(document.getElementsByName("isIdentifiyBy")[0].value=="-1")
	{
		alert("Please Select Identify By");
		document.getElementsByName("isIdentifiyBy")[0].focus();
		valid=false;
	}
	
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE%>")
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
	
	if(document.getElementsByName("isIdentifiyBy")[0].value=="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE%>")
	{
		if(document.getElementsByName("existingRelativeFlag")[1].checked)
		{
			if(isEmpty(document.forms[0].identifiyByName,"Relative Name")
				&& comboValidation(document.forms[0].relativeCode,"Relationship")
				&& isEmpty(document.forms[0].identifiyByAddress,"Address of Relative")
				&& isEmpty(document.forms[0].identifiyByPhone,"Relative Contact No")
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
			for(var i=0;i<document.getElementsByName("relativeExistChk").length;i++)
			{
				if(document.getElementsByName("relativeExistChk")[i].checked)
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
	return valid;
}

function clearForm()
{
	document.getElementsByName("isIdentifiyBy")[0].value="-1";
	document.getElementsByName("identifiyByName")[0].value="";
	document.getElementsByName("identifiyByAddress")[0].value="";
	document.getElementsByName("identifiyByPhone")[0].value="";
	document.getElementsByName("relativeCode")[0].value="-1";
	document.getElementsByName("officerName")[0].value="";
	document.getElementsByName("officerDesignation")[0].value="";
	document.getElementsByName("officerBadgeNo")[0].value="";
	document.getElementsByName("identityRemarks")[0].value="";
	showIdentifyByDiv();
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

</script>

<his:TransactionContainer>
	<his:TitleTag name="Body Identification Detail">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	
	<his:statusTransactionInProcess>
		
		<%if(session.getAttribute(MortuaryConfig.ARR_BODY_IDENTIFIED_BY)!=null){ %>
			<his:SubTitleTag name="Body Identified By Detail">
			</his:SubTitleTag>
			<%DeceasedIdentityDtlVO[] identifyByRelative=(DeceasedIdentityDtlVO[])session.getAttribute(MortuaryConfig.ARR_BODY_IDENTIFIED_BY_RELATIVE);
				if(identifyByRelative.length>0){
			%>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr bgcolor="#086FA6 "> 
						<td width="20%" colspan="4">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Identified By Relative
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<tr>
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
						<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="contactNo"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate name="<%=MortuaryConfig.ARR_BODY_IDENTIFIED_BY_RELATIVE %>" id="identifiedByRelativeVO" type="hisglobal.vo.DeceasedIdentityDtlVO">
						<tr>
							<td width="25%" class="tdfont">
								<div align="center">
									<%=identifiedByRelativeVO.getIdentifiyByName() %>
								</div>	
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<%=identifiedByRelativeVO.getRelativeCodeName() %>
								</div>	
							</td>
							<td width="40%" class="tdfont">
								<div align="center">
									<%=identifiedByRelativeVO.getIdentifiyByAddress() %>
								</div>	
							</td>
							<td width="20%" class="tdfont">
								<div align="center">
									<%=identifiedByRelativeVO.getIdentifiyByPhone()==null?"-":identifiedByRelativeVO.getIdentifiyByPhone() %>
								</div>	
							</td>
						</tr>
					</logic:iterate>
					</table>
				</his:ContentTag>	
					
			<%} %>
			<%DeceasedIdentityDtlVO[] identifyByPolice=(DeceasedIdentityDtlVO[])session.getAttribute(MortuaryConfig.ARR_BODY_IDENTIFIED_BY_POLICE);
				if(identifyByPolice.length>0){
			%>
				<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr bgcolor="#086FA6 "> 
						<td width="20%" colspan="3">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										Identified By Police
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="name"/>
									</b>
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="policeDesignation"/>
									</b>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="batchno"/>
									</b>
								</font>
							</div>
						</td>
						
					</tr>
					<logic:iterate name="<%=MortuaryConfig.ARR_BODY_IDENTIFIED_BY_POLICE %>" id="identifiedByPoliceVO" type="hisglobal.vo.DeceasedIdentityDtlVO">
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<%=identifiedByPoliceVO.getIdentifiyByName() %>
								</div>	
							</td>
							<td width="30%" class="tdfont">
								<div align="center">
									<%=identifiedByPoliceVO.getOfficerDesignation() %>
								</div>	
							</td>
							<td width="40%" class="tdfont">
								<div align="center">
									<%=identifiedByPoliceVO.getOfficerBadgeNo() %>
								</div>	
							</td>
						</tr>
					</logic:iterate>
					</table>
				</his:ContentTag>	
			<%} %>
			
		<%} %>
		<his:SubTitleTag name="Body Identification">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<bean:message key="identifyBy"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:select name="BodyIdentificationDetailFB" property="isIdentifiyBy" onchange="showIdentifyByDiv()"  tabindex="1">
								<html:option value="-1">Select value</html:option>
								<html:option value="<%=MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE %>">Relative</html:option>
								<html:option value="<%=MortuaryConfig.BODY_IDENTIFIED_BY_POLICE %>">Police</html:option>
							</html:select>
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
									<html:radio name="BodyIdentificationDetailFB" property="existingRelativeFlag" value="<%=MortuaryConfig.DEAD_BODY_IDENTIFY_BY_EXISTING%>" onclick="showRelativeHandoverDtl()" tabindex="1" ></html:radio>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="new"/>
										</b>
									</font>	
									<html:radio name="BodyIdentificationDetailFB" property="existingRelativeFlag" value="<%=MortuaryConfig.DEAD_BODY_IDENTIFY_BY_NEW%>" onclick="showRelativeHandoverDtl()" tabindex="1"  ></html:radio>
								</div>
							</td>
				</tr>
			</table>	
				
			<div id="divRlativeId" style="display: none;">
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
											<html:checkbox name="BodyIdentificationDetailFB" property="relativeExistChk" value="<%=idx.toString() %>" tabindex="1" ></html:checkbox>
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
											<%=relativeVO.getRelativeContactNo()==null?"-":relativeVO.getRelativeContactNo() %>
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
									<html:text name="BodyIdentificationDetailFB" property="identifiyByName" maxlength="60"  tabindex="1" ></html:text>
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
									<html:select name="BodyIdentificationDetailFB" property="relativeCode" tabindex="1" >
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
									<html:textarea name="BodyIdentificationDetailFB" property="identifiyByAddress" rows="1" cols="30"  tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" ></html:textarea>
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
									<html:text name="BodyIdentificationDetailFB" property="identifiyByPhone" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
								</div>
							</td>
						</tr>
					</table>
				</div>		
			</div>
				
			<div id="divPoliceId" style="display: none;">
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
								<html:text name="BodyIdentificationDetailFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
								<html:text name="BodyIdentificationDetailFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
								<html:text name="BodyIdentificationDetailFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
				
				</table>	
			</div>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="identification"/>
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" >
						<div align="left">
							<html:textarea name="BodyIdentificationDetailFB" property="identityRemarks" rows="1" cols="70"  tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
						</div>
					</td>
				</tr>
			</table>		
				
		</his:ContentTag>
		
	</his:statusTransactionInProcess>
	
	<his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToCancel('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToCancel('NEW','NEW')">
		</his:statusUnsuccessfull>
	
	</his:ButtonToolBarTag>
	
</his:TransactionContainer>		

	<html:hidden name="BodyIdentificationDetailFB" property="hmode"/>
	<html:hidden name="BodyIdentificationDetailFB" property="deceasedNo"/>
	<html:hidden name="BodyIdentificationDetailFB" property="relativeExist" />