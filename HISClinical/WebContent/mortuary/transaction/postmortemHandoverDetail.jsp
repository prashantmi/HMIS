<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
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

window.onload=function ()
{
	showHandoverDetail();
} 

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDeceasedDetail(obj1,obj2)
{
	document.getElementsByName("deceasedNo")[0].value=obj1;
	document.getElementsByName("postmortemType")[0].value=obj2;
	submitForm('DECEASEDDTL')
}

function showHandoverDetail()
{
	if(document.getElementsByName("isHandoverTo")[0].value=="-1")
	{
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
	}	
	if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE%>)
	{
		document.getElementById("divBodyHandoverToPolice").style.display="block";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
	}	
	if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE%>)
	{
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="block";
	}
}

function validateSave()
{
	var valid=false;
	if(document.getElementsByName("isHandoverTo")[0].value=="-1")
	{
		alert("Please Select Handed Over To");
		document.getElementsByName("isHandoverTo")[0].focus();
		valid=false;
	}
	else
	{
		if(document.getElementsByName("isHandoverTo")[0].value==<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE%>)
			valid=validatePolice();
		else
			valid=validateRelative();
	}		
	//alert (valid);
	return valid;
}

function validatePolice()
{
	var valid=false;
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

	return valid;
}

function validateRelative()
{
	var valid=false;
	if(isEmpty(document.forms[0].relativeName,"Relative Name")
		&& comboValidation(document.forms[0].relativeCode,"Relationship")
		&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
		&& isEmpty(document.forms[0].relativePhone,"Relative Contact No")
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

function clearForm()
{
	document.getElementsByName("relativeCode")[0].value="-1";
	document.getElementsByName("relativeAddress")[0].value="";
	document.getElementsByName("relativeName")[0].value="";
	document.getElementsByName("relativePhone")[0].value="";
	
	document.getElementsByName("officerName")[0].value="";
	document.getElementsByName("officerDesignation")[0].value="";
	document.getElementsByName("officerBadgeNo")[0].value="";
}
</script>

<body>
	<html:form action="/postmortemHandoverDetail">
		<his:TransactionContainer>
			<his:TitleTag name="Deceased Postmortem Handover Detail">
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
											<bean:message key="deceasedNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="postMortemReqDate"/>
										</b>	
									</font>
								</div>
							</td>
							
						</tr>
						<logic:iterate id="arrDeceasedListVO" name="<%=MortuaryConfig.ARR_POSTMORTEM_READY_TO_HANDOVER_VO %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<%String str="getDeceasedDetail('"+arrDeceasedListVO.getDeceasedNo()+"','"+arrDeceasedListVO.getPostmortemType()+"')"; %>
										<html:radio name="PostmortemHandoverDetailFB" property="selectedPostmortemId" value="<%= arrDeceasedListVO.getPostmortemId()%>" onclick="<%=str %>" tabindex="1" ></html:radio>
									
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="40%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
											<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
											<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
										</font>	
									</div>
								</td>
								<td width="35%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getRequestDateTime() %>
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
				<his:SubTitleTag name="Postmortem Handover Detail">
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
									<logic:equal name="PostmortemHandoverDetailFB" property="postmortemType" value="<%=MortuaryConfig.POSTMORTEM_TYPE_CLINICAL %>">
										&nbsp;Relative
										<html:hidden name="PostmortemHandoverDetailFB" property="isHandoverTo" value="<%=MortuaryConfig.BODY_HANDOVER_TO_RELATIVE %>"/>
									</logic:equal>
									<logic:equal name="PostmortemHandoverDetailFB" property="postmortemType" value="<%=MortuaryConfig.POSTMORTEM_TYPE_FORENSIC%>">
										&nbsp;Police
										<html:hidden name="PostmortemHandoverDetailFB" property="isHandoverTo" value="<%=MortuaryConfig.BODY_HANDOVER_TO_POLICE %>"/>
									</logic:equal>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead" >
							</td>
							<td width="25%" class="tdfont" >
							</td>
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
										<html:text name="PostmortemHandoverDetailFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
										<html:select name="PostmortemHandoverDetailFB" property="relativeCode" tabindex="1" >
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
										<html:textarea name="PostmortemHandoverDetailFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
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
										<html:text name="PostmortemHandoverDetailFB" property="relativePhone" maxlength="30"   onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
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
									<html:text name="PostmortemHandoverDetailFB" property="officerName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
										<html:text name="PostmortemHandoverDetailFB" property="officerDesignation" maxlength="50" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
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
										<html:text name="PostmortemHandoverDetailFB" property="officerBadgeNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
					</div>
				</his:ContentTag>
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusList>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
				</his:statusList>
					
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				</his:statusUnsuccessfull>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				</his:statusTransactionInProcess>
			</his:ButtonToolBarTag>
			
		</his:TransactionContainer>
		
		<html:hidden name="PostmortemHandoverDetailFB" property="hmode"/>
		<html:hidden name="PostmortemHandoverDetailFB" property="patCrNo" />
		<html:hidden name="PostmortemHandoverDetailFB" property="deceasedNo" />
		<html:hidden name="PostmortemHandoverDetailFB" property="postmortemId" />
		<html:hidden name="PostmortemHandoverDetailFB" property="postmortemType" />
		
	</html:form>
	<his:status/>
</body>

</html>	