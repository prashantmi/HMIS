<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="mortuary.MortuaryConfig"%>
<%@page import="java.util.List"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head> 	
<script type="text/javascript">

window.onload=function()
{
	activeExtraPreservative();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateSave()
{
	var valid=true;
	for(var i=0;i<document.getElementsByName("isPreserved").length;i++)
	{
		if(document.getElementsByName("isPreserved")[i].checked)
		{
			if(document.getElementsByName("preservativeId")[i].value=="-1")
			{
				alert("Please Select The Preservative");
				document.getElementsByName("preservativeId")[i].focus();
				return false;
			}
		}
	}
	
	for(var i=0;i<document.getElementsByName("isPreserved").length;i++)
	{
		if(document.getElementsByName("isPreserved")[i].checked)
		{	
			document.getElementsByName("isPreservedValue")[i].value="1";
		}	
		else
		{
			document.getElementsByName("isPreservedValue")[i].value="0";
		}	
	}
	
	if(document.getElementsByName("itemFoundFlag")[0].value==<%=MortuaryConfig.YES%>)
	{
		if(typeof document.getElementsByName("extraItemCode")[0] != 'undefined')
		{
			if(document.getElementsByName("extraItemCode")[0].value!="-1")
			{
				valid=validateAdd();
			}
			else
				valid=true;
		}
		else
			valid=true;	
	}
	else
	{
		if(typeof document.getElementsByName("extraItemCode")[0] != 'undefined')
			valid=validateAdd();
		else
			valid=true;	
	}
	
	//alert(valid);
	return valid;
}

function clearForm()
{
	for(var i=0;i<document.getElementsByName("itemCode").length;i++)
	{
		document.getElementsByName("remarks")[i].value=""
	}
}

function activePreservative()
{
	for(var i=0;i<document.getElementsByName("isPreserved").length;i++)
	{
		if(document.getElementsByName("isPreserved")[i].checked)
		{	
			document.getElementsByName("preservativeId")[i].disabled=false;
		}	
		else
		{
			document.getElementsByName("preservativeId")[i].disabled=true;
			document.getElementsByName("preservativeId")[i].value="-1";
		}	
	}
}

function activeExtraPreservative()
{
	if(document.getElementsByName("extraIsPreserved")[0].checked)
	{	
		document.getElementsByName("extraPreservativeId")[0].disabled=false;
	}	
	else
	{
		document.getElementsByName("extraPreservativeId")[0].disabled=true;
		document.getElementsByName("extraPreservativeId")[0].value="-1";
	}	
	
}

function validateAdd()
{
	var valid=true;
	if(document.getElementsByName("extraItemCode")[0].value=="-1")
	{
		alert("Please Select The Item ")
		document.getElementsByName("extraItemCode")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("extraIsPreserved")[0].checked)
	{
		if(document.getElementsByName("extraPreservativeId")[0].value=="-1")
		{
			alert("Please Select The Preservative ")
			document.getElementsByName("extraPreservativeId")[0].focus();
			val=false;
		}
		else
			val=true;
		
		valid=val;	
	}
	
	if(document.getElementsByName("extraIsPreserved")[0].checked)
		document.getElementsByName("extraIsPreservedValue")[0].value="1";
	else
		document.getElementsByName("extraIsPreservedValue")[0].value="0";
		
	
	return valid;
}

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenExtraItemCode")[0].value=obj1;
	document.getElementsByName("hiddenExtraItemName")[0].value=obj2;
	submitForm('DELETEROW')
}
</script>

<his:TransactionContainer>
	<his:TitleTag name="Deceased Item Detail">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	<%if(session.getAttribute(MortuaryConfig.ARR_PRESERVED_ITEM_DETAIL_VO)!=null){ %>
		<his:SubTitleTag name="Deceased Preserved Item Detail">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="deceasedItem"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="isPreserved"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="preservative"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="50%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<B>
									<bean:message key="remarks"/>
								</B>
							</font>	
						</div>
					</td>
				</tr>
				<logic:iterate name="<%=MortuaryConfig.ARR_PRESERVED_ITEM_DETAIL_VO %>" id="itemPreserved" type="hisglobal.vo.PostmortemItemDtlVO">
					<tr>
						<td width="20%" class="tdfont" >
							<div align="center">
								<%=itemPreserved.getItemName() %>
							</div>
						</td>	
						<td width="10%" class="tdfont" >
							<div align="center">
								<%=itemPreserved.getIsPreservedLabel() %>
							</div>
						</td>	
						<td width="20%" class="tdfont" >
							<div align="center">
								<%=itemPreserved.getPreservativeName()==null?"-":itemPreserved.getPreservativeName() %>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="center">
								<%=itemPreserved.getRemarks()==null?"-":itemPreserved.getRemarks() %>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>		
	<%} %>
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Deceased Item Detail">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="deceasedItem"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="isPreserved"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="preservative"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="45%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<B>
									<bean:message key="remarks"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						&nbsp;
					</td>
				</tr>
				<logic:present name="<%=MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED %>">
					<logic:iterate name="<%=MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED %>" id="itemReq" type="hisglobal.vo.PostmortemItemReqDtlVO">
						<tr>
							<td width="20%" class="tdfont" >
								<div align="center">
									<%=itemReq.getItemName() %>
									<html:hidden name="DeceasedItemFB" property="itemCode" value="<%=itemReq.getItemCode() %>"/>
								</div>
							</td>	
							<td width="10%" class="tdfont" >
								<div align="center">
									<html:checkbox name="DeceasedItemFB" property="isPreserved" onclick="activePreservative()" tabindex="1"></html:checkbox>
									<html:hidden name="DeceasedItemFB" property="isPreservedValue"/>
								</div>
							</td>	
							<td width="20%" class="tdfont" >
								<div align="center">
									<html:select name="DeceasedItemFB" property="preservativeId" disabled="true" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESSENTIAL_PRESERVATIVE_LIST%>">
										<html:options collection="<%= MortuaryConfig.ESSENTIAL_PRESERVATIVE_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="45%" class="tdfont" >
								<div align="center">
									<html:text name="DeceasedItemFB" property="remarks" size="50" value="" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
								</div>
							</td>
							<td width="5%" class="tdfont" >
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
				<%List lstExtItem=(List)session.getAttribute(MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST);
					if(lstExtItem.size()>0){
				%>
				<tr>
					<td width="20%" class="tdfont" >
						<div align="center">
							<html:select name="DeceasedItemFB" property="extraItemCode" tabindex="1" >
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST%>">
								<html:options collection="<%= MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="10%" class="tdfont" >
						<div align="center">
							<html:checkbox name="DeceasedItemFB" property="extraIsPreserved" onclick="activeExtraPreservative()" tabindex="1"></html:checkbox>
							<html:hidden name="DeceasedItemFB" property="extraIsPreservedValue"/>
						</div>
					</td>	
					<td width="20%" class="tdfont" >
						<div align="center">
							<html:select name="DeceasedItemFB" property="extraPreservativeId" disabled="true" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_PRESERVATIVE_LIST%>">
								<html:options collection="<%= MortuaryConfig.ESSENTIAL_PRESERVATIVE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="45%" class="tdfont" >
						<div align="center">
							<html:text name="DeceasedItemFB" property="extraRemarks" size="50" value="" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
						</div>
					</td>
					<td width="5%" class="tdfont" >
						<div align="center">
							<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style='cursor:pointer' tabindex="1" onclick ="if(validateAdd()) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('ADDROW');">
						</div>	
					</td>
				</tr>
				<% }%>
				<%if(session.getAttribute(MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO)!=null){ %>
					<logic:iterate id="extraItemVO" name="<%=MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO %>" type="hisglobal.vo.PostmortemItemDtlVO">
						<tr>
							<td width="20%" class="tdfont">
								<div align="center">
									<%=extraItemVO.getItemName() %>
								</div>
							</td>	
							<td width="10%" class="tdfont">
								<div align="center">
									<%=extraItemVO.getIsPreservedLabel() %>
								</div>
							</td>
							<td width="20%" class="tdfont">
								<div align="center">
									<%=extraItemVO.getPreservativeName() %>
								</div>
							</td>
							<td width="45%" class="tdfont">
								<div align="center">
									<%=extraItemVO.getRemarks()==""?"-":extraItemVO.getRemarks() %>
								</div>
							</td>
							<td width="5%" class="tdfont">
								<div align="center">
									<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=extraItemVO.getItemCode() %>','<%=extraItemVO.getItemName() %>') ;" onclick=" deleteRow('<%=extraItemVO.getItemCode() %>','<%=extraItemVO.getItemName() %>')" tabindex="1" >
									<html:hidden name="DeceasedItemFB" property="hiddenExtraItemCode"/>
									<html:hidden name="DeceasedItemFB" property="hiddenExtraItemName"/>
								</div>
							</td>
						</tr>
					</logic:iterate>
				<%} %>
			</table>
		</his:ContentTag>	
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

	<html:hidden name="DeceasedItemFB" property="hmode"/>
	<html:hidden name="DeceasedItemFB" property="deceasedNo"/>
	<html:hidden name="DeceasedItemFB" property="itemFoundFlag"/>
	
	