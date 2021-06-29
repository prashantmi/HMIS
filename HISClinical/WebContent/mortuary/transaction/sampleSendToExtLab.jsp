<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.MortuaryExtLabInvReqDtlVO"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

window.onload=function()
{
	checkedSelectedItem();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenLabTestId")[0].value=obj1;
	document.getElementsByName("hiddenLabTestName")[0].value=obj2;
	submitPage('DELETEROW');
}

function validateAdd()
{
	var valid=true;
	
	if(comboValidation(document.forms[0].labTestId,"Investigation")
		&& isEmpty(document.forms[0].labTestRemrks,"Detection For")
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
function validateSample()
{
	var valid=true;
	var count=0;
	
	for(var i=0;i<document.getElementsByName("selectedItem").length;i++)
	{
		if(document.getElementsByName("selectedItem")[i].checked)
			count++;
	}
	
	if(count==0)
	{
		alert("Please Select At Least One Sample To Send External Lab");
		valid=false;
	}
	else
		valid=true;
	return valid;	
}

function validateHandover()
{
	var valid=false;
	if(document.getElementsByName("postmortemType")[0].value=="<%=MortuaryConfig.POSTMORTEM_TYPE_FORENSIC%>")
	{
		if(isEmpty(document.forms[0].policeStation,"Police Station")
			&& isEmpty(document.forms[0].dutyOffName,"Duty Officer Name")
			&& isEmpty(document.forms[0].dutyOffDesignation,"Duty Officer Designation")
			&& isEmpty(document.forms[0].dutyOffBatchNo,"Duty Officer Batch No")
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
function validateSave()
{
	var valid=false;
	if(validateSample()
		&& comboValidation(document.forms[0].extLabId,"External Lab")
		&& validateAdd()
		&& validateHandover()
	)
		valid=true;
	else
		valid=false;
	return valid;		
}

function checkedSelectedItem()
{
	var str=document.getElementsByName("checkedItem")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedItem');
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

function clearForm()
{
	document.getElementsByName("extLabId")[0].value="-1";
	document.getElementsByName("labTestId")[0].value="-1";
	document.getElementsByName("labTestRemrks")[0].value="";
	document.getElementsByName("policeStation")[0].value="";
	document.getElementsByName("dutyOffName")[0].value="";
	document.getElementsByName("dutyOffDesignation")[0].value="";
	document.getElementsByName("dutyOffBatchNo")[0].value="";
	
	for(var i=0;i<document.getElementsByName("selectedItem").length;i++)
	{
		document.getElementsByName("selectedItem")[i].checked=false;
	}
	
}
</script>


<his:TransactionContainer>
	<his:TitleTag name="Sample Send To External Lab">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Sample Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="select"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="sample"/>
								</B>
							</font>	
						</div>
					</td>
					
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="preservative"/>
									<bean:message key="used"/>
								</B>
							</font>	
						</div>
					</td>
					
				</tr>
				<logic:iterate name="<%=MortuaryConfig.ARR_PRESERVED_ITEM_IN_MORTUARY_DETAIL_VO %>" id="itemPreserved" type="hisglobal.vo.PostmortemItemDtlVO">
					<tr>
						<td width="20%" class="tdfont" >
							<div align="center">
								<html:checkbox name="SampleSendToExtLabFB" property="selectedItem" value="<%=itemPreserved.getItemCode() %>" tabindex="1"></html:checkbox>
							</div>
						</td>
						<td width="40%" class="tdfont" >
							<div align="center">
								<%=itemPreserved.getItemName() %>
							</div>
						</td>	
						<td width="40%" class="tdfont" >
							<div align="center">
								<%=itemPreserved.getPreservativeName()==null?"-":itemPreserved.getPreservativeName() %>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>		
		<his:SubTitleTag name="Sample Send Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="extLab"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left" >
							<html:select name="SampleSendToExtLabFB" property="extLabId" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_LIST%>">
								<html:options collection="<%= MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
			</table>	
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="investigation"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="65%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="detection"/>
									<bean:message key="for"/>
								</B>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						&nbsp;
					</td>
				</tr>
				<tr>	
					<td width="25%" class="tdfont" >
						<div align="center">
							<html:select name="SampleSendToExtLabFB" property="labTestId" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST%>">
								<html:options collection="<%= MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="65%" class="tdfont" >
						<div align="center">
							<html:textarea name="SampleSendToExtLabFB" property="labTestRemrks" rows="1" cols="80" tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
						</div>
					</td>
					<td width="10%" class="tdfont" >
						<div align="center">
							<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('ADDROW');">
						</div>	
					</td>
				</tr>
				<%MortuaryExtLabInvReqDtlVO[] invVO=(MortuaryExtLabInvReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO);
					if(invVO!=null && invVO.length>0){
				%>
					<logic:iterate id="addedInvVO" name="<%=MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO %>" type="hisglobal.vo.MortuaryExtLabInvReqDtlVO" indexId="idx">
						<tr>
							<td width="25%" class="tdfont">
								<div align="center">
									<%=addedInvVO.getLabTestName() %>
								</div>
							</td>	
							<td width="65%" class="tdfont">
								<div align="center">
									<%=addedInvVO.getLabTestRemrks() %>
								</div>
							</td>
							<td width="10%" class="tdfont">
								<div align="center">
									<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=addedInvVO.getLabTestId()%>','<%=addedInvVO.getLabTestName()%>') ;" onclick=" deleteRow('<%=addedInvVO.getLabTestId()%>','<%=addedInvVO.getLabTestName()%>')" tabindex="1" >
								</div>
							</td>
						</tr>
					</logic:iterate>
				<%} %>	
			</table>		
		</his:ContentTag>
		
		<logic:equal name="SampleSendToExtLabFB" property="postmortemType" value="<%=MortuaryConfig.POSTMORTEM_TYPE_FORENSIC %>">
			<his:SubTitleTag name="Sample Handover Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="policestation"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:text name="SampleSendToExtLabFB" property="policeStation" tabindex="1"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="dutyOfficer" />
									<bean:message key="name" />
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:text name="SampleSendToExtLabFB" property="dutyOffName" tabindex="1"></html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="dutyOfficer" />
									<bean:message key="designation" />
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:text name="SampleSendToExtLabFB" property="dutyOffDesignation" tabindex="1"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="dutyOfficer" />
									<bean:message key="batchno" />
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:text name="SampleSendToExtLabFB" property="dutyOffBatchNo" tabindex="1"></html:text>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>		
		</logic:equal>
	</his:statusTransactionInProcess>
	<his:ButtonToolBarTag>
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
		
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
		</his:statusTransactionInProcess>
		
	</his:ButtonToolBarTag>
	
</his:TransactionContainer>

	<html:hidden name="SampleSendToExtLabFB" property="hmode"/>
	<html:hidden name="SampleSendToExtLabFB" property="deceasedNo"/>
	<html:hidden name="SampleSendToExtLabFB" property="hiddenLabTestId"/>
	<html:hidden name="SampleSendToExtLabFB" property="hiddenLabTestName"/>
	<html:hidden name="SampleSendToExtLabFB" property="checkedItem"/>
	<html:hidden name="SampleSendToExtLabFB" property="postmortemType"/>
	
	