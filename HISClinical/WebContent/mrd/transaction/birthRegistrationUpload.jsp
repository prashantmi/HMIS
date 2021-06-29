<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.vo.PatientVO"%>
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
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

window.onload = function() 
{
	if(document.getElementsByName("isPrint")[0].value==<%=MrdConfig.YES%>)
	{
		openDeathPrintPopup();
	}
}

function openDeathPrintPopup()
{
	var patCrNo=document.getElementsByName("selectedChild")[0].value;
	
	var path='/HISClinical/mrd/birthRegistrationUpload.cnt?hmode=PRINTSLIP&selectedChild='+patCrNo;
	openPrintPopup(path,700,800);
	document.getElementsByName("isPrint")[0].value=<%=MrdConfig.NO%>;
}

function openPrintPopup(url, height, width)
{
	child = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}

function getDetail(obj)
{	
	document.getElementsByName("statusCode")[0].value=obj.value.split("@")[1];
	document.getElementsByName("selectedChild")[0].value=obj.value.split("@")[0];	

	if(document.getElementsByName("statusCode")[0].value==0)
		submitForm('GETDTL');
	if(document.getElementsByName("statusCode")[0].value==1)
		submitForm('GETHANDOVERDTL');
	if(document.getElementsByName("statusCode")[0].value==2 || document.getElementsByName("statusCode")[0].value==3)
		submitForm('GETDUPHANDOVERDTL');			
}

function showHandoverToDiv(obj)
{
	if(obj.checked==true)
		document.getElementById("divHandoverDetail").style.display="block";
	else
		document.getElementById("divHandoverDetail").style.display="none";
}

function validateSave()
{
	var valid=false;
	if(isEmpty(document.forms[0].registrtionNo,"Registration No")
	&& validateHandover()
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

function validateHandover()
{
	var valid=false;
	if(document.getElementsByName("isHandoverTo")[0].checked)
	{
		if(isEmpty(document.forms[0].relativeName,"Relative Name")
			&& comboValidation(document.forms[0].relativeCode,"Relationship")
			&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
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

function validateHandoverSave()
{
	var valid=false;
	if(isEmpty(document.forms[0].relativeName,"Relative Name")
		&& comboValidation(document.forms[0].relativeCode,"Relationship")
		&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
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

function openSearchPopup(event)
{
	var path='/HISClinical/mrd/birthRegistrationUpload.cnt?hmode=SEARCHPOPUP';
	openPrintPopup(path,300,800);
}

function validateHandoverDupSave()
{
	return validateHandoverSave();
}
</script>

<body>
	<his:TransactionContainer>
		<his:SubTitleTagBroad name="Birth Registration Upload">
			<div align="right">
				<img class='button' src='<his:path src="/hisglobal/images/btn-search.png"/>'  style=cursor:pointer onclick ="openSearchPopup(event)" onkeypress="if(event.keyCode==13) openSearchPopup(event)")>	
			</div>
		</his:SubTitleTagBroad>
		<his:statusList>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="4%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="14%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="child"/>
									<bean:message key="crNo"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="14%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="child"/>
									<bean:message key="name"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="8%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="gender"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="birdatetime"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="14%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="motherCrNo"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="14%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="motherName"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="14%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="status"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate id="arrBirthListVO" name="<%=MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_LIST_VO %>" type="hisglobal.vo.ANCNeonatalDetailVO" indexId="idx">
					<tr>
						<td width="4%" class="tdfonthead">
							<div align="center">
								<html:radio name="BirthRegistrationUploadFB" property="selectedCrNo" value='<%=arrBirthListVO.getChildCrNo()+"@"+arrBirthListVO.getStatusCode()%>' onclick="getDetail(this)" tabindex="1" ></html:radio>
							</div>
						</td>
						<td width="14%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getChildCrNo() %>
								</font>
							</div>
						</td>
						<td width="14%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getChildName() %>
								</font>
							</div>
						</td>
						<td width="8%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getGender() %>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getBirthDateTime() %>
								</font>
							</div>
						</td>
						<td width="14%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getPatCrNo() %>
								</font>
							</div>
						</td>
						<td width="14%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getMotherName() %>
								</font>
							</div>
						</td>
						<td width="14%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrBirthListVO.getStatus() %>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
		</his:statusList>
		
		<his:statusTransactionInProcess>
		<%PatientVO motherDtlVO=(PatientVO)session.getAttribute(MrdConfig.MOTHER_DETAIL_VO); %>
		<his:SubTitleTag name="Mother Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="motherName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=motherDtlVO.getPatFirstName()==null?"":motherDtlVO.getPatFirstName() %>
							<%=motherDtlVO.getPatMiddleName()==null?"":motherDtlVO.getPatMiddleName() %>
							<%=motherDtlVO.getPatLastName()==null?"":motherDtlVO.getPatLastName() %>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="motherCrNo"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=motherDtlVO.getPatCrNo()==null?"":motherDtlVO.getPatCrNo() %>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="age"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=motherDtlVO.getPatAge()==null?"":motherDtlVO.getPatAge() %>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="pathusbandname"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=motherDtlVO.getPatHusbandName()==null?"":motherDtlVO.getPatHusbandName() %>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<%PatientVO childDtlVO=(PatientVO)session.getAttribute(MrdConfig.CHILD_DETAIL_VO); %>
		
		<his:SubTitleTag name="Child Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="child"/>
									<bean:message key="name"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=childDtlVO.getPatFirstName()==null?"":childDtlVO.getPatFirstName() %>
							<%=childDtlVO.getPatMiddleName()==null?"":childDtlVO.getPatMiddleName() %>
							<%=childDtlVO.getPatLastName()==null?"":childDtlVO.getPatLastName() %>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="child"/>
								<bean:message key="crNo"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=childDtlVO.getPatCrNo()==null?"":childDtlVO.getPatCrNo() %>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=childDtlVO.getPatGender()==null?"":childDtlVO.getPatGender() %>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dob"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left" >
							<%=childDtlVO.getPatDOB()==null?"":childDtlVO.getPatDOB() %>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<%boolean readonly=false; %>
		<logic:equal name="BirthRegistrationUploadFB" property="statusCode" value="0">
			<%readonly=false; %>
		</logic:equal>
		<logic:notEqual name="BirthRegistrationUploadFB" property="statusCode" value="0">
			<%readonly=true; %>
		</logic:notEqual>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="registration"/>
									<bean:message key="number"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:text name="BirthRegistrationUploadFB" property="registrtionNo" readonly="<%=readonly %>"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="remarks"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left" >
								<html:textarea name="BirthRegistrationUploadFB" property="remarks" rows="1" cols="33" readonly="<%=readonly %>"></html:textarea>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<logic:equal name="BirthRegistrationUploadFB" property="statusCode" value="0">
				<his:SubTitleTag name="">
					<div align="left">
						<html:checkbox name="BirthRegistrationUploadFB" tabindex="1" value="<%=RegistrationConfig.IS_HANDOVER_TRUE%>" property="isHandoverTo" onclick="showHandoverToDiv(this)" />
						Handover To Detail
					</div>
				</his:SubTitleTag>
				<div id="divHandoverDetail" style="display: none;">
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
										<html:text name="BirthRegistrationUploadFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
										<html:select name="BirthRegistrationUploadFB" property="relativeCode">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>">
												<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>" property="value" labelProperty="label" />
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
										<html:textarea name="BirthRegistrationUploadFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead"></td>
								<td width="25%" class="tdfont"></td>
							</tr>
						</table>
					</his:ContentTag>	
				</div>	
			</logic:equal>	
			<logic:notEqual name="BirthRegistrationUploadFB" property="statusCode" value="0">
				<his:SubTitleTag name="Handover To Detail">
				</his:SubTitleTag>
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
									<html:text name="BirthRegistrationUploadFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event)"></html:text>
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
									<html:select name="BirthRegistrationUploadFB" property="relativeCode">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>">
											<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_RELATION_LIST%>" property="value" labelProperty="label" />
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
									<html:textarea name="BirthRegistrationUploadFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>
				</his:ContentTag>	
			</logic:notEqual>
			
			
		</his:statusTransactionInProcess>
		
		<his:ButtonToolBarTag>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			</his:statusList>
			
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			</his:statusUnsuccessfull>
			
			<his:statusTransactionInProcess>
				<logic:equal name="BirthRegistrationUploadFB" property="statusCode" value="0">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitForm('SAVE')">
				</logic:equal>
				<logic:equal name="BirthRegistrationUploadFB" property="statusCode" value="1">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateHandoverSave()) submitForm('HANDOVERSAVE')" onkeypress="if(event.keyCode==13)if(validateHandoverSave())submitForm('HANDOVERSAVE')">
				</logic:equal>	
				<logic:equal name="BirthRegistrationUploadFB" property="statusCode" value="2">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateHandoverDupSave()) submitForm('HANDOVERDUPSAVE')" onkeypress="if(event.keyCode==13)if(validateHandoverDupSave())submitForm('HANDOVERDUPSAVE')">
				</logic:equal>	
				<logic:equal name="BirthRegistrationUploadFB" property="statusCode" value="3">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateHandoverDupSave()) submitForm('HANDOVERDUPSAVE')" onkeypress="if(event.keyCode==13)if(validateHandoverDupSave())submitForm('HANDOVERDUPSAVE')">
				</logic:equal>	
				
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
		</his:ButtonToolBarTag>
		
		<html:hidden name="BirthRegistrationUploadFB" property="hmode" value="unspecified"/>
		<html:hidden name="BirthRegistrationUploadFB" property="selectedChild"/>
		<html:hidden name="BirthRegistrationUploadFB" property="statusCode"/>
		<html:hidden name="BirthRegistrationUploadFB" property="isPrint"/>
		<html:hidden name="BirthRegistrationUploadFB" property="isFromSearch"/>
			
	</his:TransactionContainer>
</body>
</html>