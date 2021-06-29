<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mortuary.MortuaryConfig"%>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateTeamAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].teamMember,"Team Member")
		&& comboValidation(document.forms[0].role,"Role Name")
	)
	{
		valid=checkDuplicateMember();
	}
	else
	{
		valid=false;
	}
	
	return valid;
}

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenTeamMemberId")[0].value=obj1;
	document.getElementsByName("hiddenTeamMemberName")[0].value=obj2;
	submitForm('DELETEROW')
}

function clearForm()
{
	document.getElementsByName("role")[0].value="-1";
	document.getElementsByName("teamMember")[0].value="-1";
}

function validateSave()
{
	var valid=false;
	setCheckBoxValue();
	if(validateNotPerformed() && validateTeamAdd())
		valid=true;
	else
		valid=false;
		 	
	return valid;	
}

function validateNotPerformed()
{
	var valid=true;
	for(var i=0;i<document.getElementsByName("notPerformed").length;i++)
	{
		if(document.getElementsByName("notPerformed")[i].checked==false)
		{
			if(document.getElementsByName("reason")[i].value=="")
			{
				alert("Please Enter The Not Performed Reason");
				document.getElementsByName("reason")[i].focus();
				return false;
			}	 
		}
	}
	
	return valid;
}

function checkDuplicateMember()
{
	var valid=true;
	len=document.getElementsByName("hiddenAddedTeamMemberId").length;
	
	for(var i=0;i<len;i++)
	{
		if(trimData(document.getElementsByName("hiddenAddedTeamMemberId")[i]).value==trimData(document.getElementsByName("teamMember")[0].value))
		{
			alert("'"+document.getElementsByName("hiddenAddedTeamMemberName")[i].value+"' Already Added");
			valid=false;
			break;
		}
	}
	return valid;
}

function enabledField(obj)
{
	if(document.getElementsByName("notPerformed")[obj].checked)
	{
		document.getElementsByName("reason")[obj].readOnly=true;
		document.getElementsByName("reason")[obj].value="";
	}	
	else
	{
		document.getElementsByName("reason")[obj].readOnly=false;
	}	
}

function setCheckBoxValue()
{
	for(var i=0;i<document.getElementsByName("notPerformed").length;i++)
	{
		if(document.getElementsByName("notPerformed")[i].checked)
			document.getElementsByName("notPerformedValue")[i].value=<%=MortuaryConfig.INCHARGE_IS_PERFORMED_YES%>;
		else
			document.getElementsByName("notPerformedValue")[i].value=<%=MortuaryConfig.INCHARGE_IS_PERFORMED_NO%>;
	}
}
</script>

<his:TransactionContainer>
	<his:TitleTag name="Postmortem Team Datail">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	<%if(session.getAttribute(MortuaryConfig.ARR_ADDED_TEAM_MEMBER_VO)!=null){ %>
		<his:SubTitleTag name="Added Team Member Details">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="teamMember"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="roleName"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="isIncharge"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="isPerformed"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="reason"/>
								</B>
							</font>	
						</div>
					</td>
				</tr>
				<logic:iterate id="addedTeamDetailVO" name="<%=MortuaryConfig.ARR_ADDED_TEAM_MEMBER_VO %>" type="hisglobal.vo.PostmortemTeamDetailVO" indexId="idx">
					<tr>
						<td width="20%" class="tdfont" >
							<div align="center">
								<%=addedTeamDetailVO.getEmpName() %>
								<html:hidden name="PostmortemTeamDetailFB" property="hiddenAddedTeamMemberId" value="<%=addedTeamDetailVO.getEmpId() %>"/>
								<html:hidden name="PostmortemTeamDetailFB" property="hiddenAddedTeamMemberName" value="<%=addedTeamDetailVO.getEmpName() %>"/>
							</div>
						</td>
						<td width="20%" class="tdfont" >
							<div align="center">
								<%=addedTeamDetailVO.getRoleName() %>
							</div>
						</td>
						<td width="10%" class="tdfont" >
							<div align="center">
								<%=addedTeamDetailVO.getIsInchargeLabel() %>
							</div>
						</td>
						<td width="10%" class="tdfont" >
						<%String index=idx.toString(); 
							String str="enabledField('"+index+"')";%>
							<div align="center">
								<%if(addedTeamDetailVO.getIsPerformed().equals(MortuaryConfig.INCHARGE_IS_PERFORMED_ASSIGN)){ %>
								
								<input type="checkbox" name="notPerformed" checked="checked" onclick="<%=str %>" tabindex="1">
								<%}else{ %>
									<%=addedTeamDetailVO.getIsPerformedLabel() %>
								<%} %>
							</div>
							
						</td>
						<html:hidden name="PostmortemTeamDetailFB" property="notPerformedValue"/>
						<td width="40%" class="tdfont" >
							<div align="center">
								<%if(addedTeamDetailVO.getIsPerformed().equals(MortuaryConfig.INCHARGE_IS_PERFORMED_ASSIGN)){ %>
								<html:textarea name="PostmortemTeamDetailFB" property="reason" rows="1" cols="40" readonly="true" tabindex="1"  onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								<%}else{ %>
									<%=addedTeamDetailVO.getReason()==null?"-":addedTeamDetailVO.getReason() %>
								<%} %>
							</div>
						</td>
					</tr>		
				</logic:iterate>
			</table>
		</his:ContentTag>
	<%} %>
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Team Members Detail">
		</his:SubTitleTag>
					
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="teamMember"/>
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
							<html:select name="PostmortemTeamDetailFB"  property="teamMember" tabindex="1" >
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST%>">
								<html:options collection="<%= MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="40%" class="tdfont" >
						<div align="center">
							<html:select name="PostmortemTeamDetailFB"  property="role" tabindex="1" >
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MortuaryConfig.POSTMORTEM_CONDUCTED_BY_ROLE%>">
								<html:options collection="<%= MortuaryConfig.POSTMORTEM_CONDUCTED_BY_ROLE%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="40%" class="tdfont" >
						<div align="center">
							<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' onkeypress="if(event.keyCode==13)if(validateTeamAdd()) submitForm('ADDROW') ;" onclick="if(validateTeamAdd()) submitForm('ADDROW')" tabindex="1" >
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>	
		<%if(session.getAttribute(MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO)!=null){ %>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
				<logic:iterate id="teamDetailVO" name="<%=MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO %>" type="hisglobal.vo.PostmortemTeamDetailVO">
					<tr>
						<td class="tdfont" width="40%" >
							<div align="center">
								<%=teamDetailVO.getEmpName() %>
							</div>
						</td>
						<td class="tdfont" width="40%" >
							<div align="center">
								<%=teamDetailVO.getRoleName() %>
							</div>
						</td>
						<td class="tdfont" width="40%" >
							<div align="center">
								<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=teamDetailVO.getEmpId() %>','<%=teamDetailVO.getEmpName() %>') ;" onclick=" deleteRow('<%=teamDetailVO.getEmpId() %>','<%=teamDetailVO.getEmpName() %>')" tabindex="1" >
								<html:hidden name="PostmortemTeamDetailFB" property="hiddenTeamMemberId"/>
								<html:hidden name="PostmortemTeamDetailFB" property="hiddenTeamMemberName"/>
							</div>
						</td>
					</tr>	
				</logic:iterate>
			</table>
		</his:ContentTag>	
		<%} %>
		
	</his:statusTransactionInProcess>	
	
	<his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	
	</his:ButtonToolBarTag>
	
	
</his:TransactionContainer>	

	<html:hidden name="PostmortemTeamDetailFB" property="hmode"/>
	<html:hidden name="PostmortemTeamDetailFB" property="deceasedNo"/>
	
	