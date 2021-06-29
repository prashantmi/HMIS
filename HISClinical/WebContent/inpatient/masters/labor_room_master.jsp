<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="inpatient.InpatientConfig"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<!-- 

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1/Dec/2010
 *  Process Name : Labor Room Master
 *  Last Modified By/ Date : 
 */

 -->
<%@page import="inpatient.InpatientConfig;"%>
<html>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<script type="text/javascript">

function submitForm(mode)
{	
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function validateForm(mode)
{
	if(document.getElementsByName("departmentCode")[0].value=="-1")
	{
	
		alert("Please Select Department");
		document.getElementsByName("departmentCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("laborRoomName")[0].value=="")
	{
		alert("Please Enter Labor Room Name");
		document.getElementsByName("laborRoomName")[0].focus();
		return false;
	}	
	
	if(document.getElementsByName("laborRoomShortName")[0].value=="")
	{
		alert("Please Enter  The Labor Room Short Name");
		document.getElementsByName("laborRoomShortName")[0].focus();
		return false;
	}		
	

	return true;
}

function finalSubmit(mode)
{
	if (!validateForm()) 
	return;
	
	submitForm(mode);
}

function clearForm()
{	
	document.getElementsByName("departmentCode")[0].value="-1";
	document.getElementsByName("laborRoomName")[0].value="";
	document.getElementsByName("laborRoomShortName")[0].value="";
	document.getElementsByName("laborRoomDescription")[0].value="";
}

</script>

<html:form action="/master/LaborRoomMasterACT">
	<his:TransactionContainer>
		<his:TitleTag name="Labor Room Master">
		</his:TitleTag>
		<his:statusNew>
	
<his:ContentTag>
			<logic:notEqual value="VIEW" property="hmode" name="LaborRoomMasterFB">
			
			<div id="divTemplateCategoryCombo">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="dept" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="LaborRoomMasterFB" property="departmentCode" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS%>">
										<html:options collection="<%=InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>	
							</div>
						</td>
					</tr>
				</table>
			</div>
			
		<table width='100%' border='0' cellspacing='1' cellpadding='0'>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="laborRoom" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="LaborRoomMasterFB" property="laborRoomName" tabindex="1" maxlength="50" size="33" onkeypress="return validateAlphaNumericOnly(event,this)"/>
							</div>					
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="shortName" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="LaborRoomMasterFB" property="laborRoomShortName" tabindex="1" maxlength="3" size="33" onkeypress="return validateAlphaNumericOnly(event,this)"/>
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000"></font>
									<b><bean:message key="description" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="LaborRoomMasterFB" property="laborRoomDescription" tabindex="1"   rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'150') && validateAlphaNumericOnly(event,this))"/>
							</div>
						</td>
					</tr>											
			</table>
		</logic:notEqual>

		<logic:equal value="VIEW" name="LaborRoomMasterFB" property="hmode">
					<div id="divTemplateCategoryCombo">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000">*</font> <b><bean:message key="dept" /></b> </font></div>
							</td>
							<td width="50%" class="tdfont">
							<div align="left"><html:select name="LaborRoomMasterFB" property="departmentCode" tabindex="2" disabled="true" >
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS%>">
									<html:options collection="<%=InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select></div>
							</td>
						</tr>
					</table>
					</div>
		</logic:equal>
		<logic:equal value="VIEW" name="LaborRoomMasterFB" property="hmode">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font> <b><bean:message key="laborRoom" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:text name="LaborRoomMasterFB" property="laborRoomName" tabindex="2" size="33" readonly="true" /></div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font> <b><bean:message key="shortName" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:text name="LaborRoomMasterFB" property="laborRoomShortName" tabindex="2" size="33" readonly="true" /></div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000"></font> <b><bean:message key="description" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:textarea name="LaborRoomMasterFB" property="laborRoomDescription" rows="1" cols="30" tabindex="2"  readonly="true" /></div>
						</td>
					</tr>
				</table>
			</logic:equal>
			
							
	</his:ContentTag>
		</his:statusNew>
		
		<his:ButtonToolBarTag>
			<logic:notEqual value="MODIFY" property="hmode" name="LaborRoomMasterFB">
				<logic:notEqual value="VIEW" property="hmode" name="LaborRoomMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" 
						onclick=" finalSubmit('SAVE')"  onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" />
				</logic:notEqual>
			</logic:notEqual>
			<logic:notEqual value="VIEW" property="hmode" name="LaborRoomMasterFB">
				<logic:equal value="MODIFY" property="hmode" name="LaborRoomMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" 
						onclick=" finalSubmit('MODIFYSAVE')" onkeypress="if(event.keyCode==13)   finalSubmit('MODIFYSAVE')" />
				</logic:equal>
			</logic:notEqual>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" 
				onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL') ">
			
			<logic:notEqual value="VIEW" property="hmode" name="LaborRoomMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" 
					onclick="clearForm()" onkeypress="if(event.keyCode==13)  clearForm()" >
			</logic:notEqual>
		</his:ButtonToolBarTag>
		<his:status />


		<html:hidden property="hmode" name="LaborRoomMasterFB" />	
		<html:hidden property="deptName" name="LaborRoomMasterFB" />	
		<html:hidden property="laborRoomId" name="LaborRoomMasterFB" />
		<html:hidden property="slNo" name="LaborRoomMasterFB" />
		<html:hidden property="hospitalCode" name="LaborRoomMasterFB" />
		<html:hidden property="isValid" name="LaborRoomMasterFB" />
		<html:hidden property="seatID" name="LaborRoomMasterFB" />
		<html:hidden property="entryDate" name="LaborRoomMasterFB" />
		<html:hidden property="lastModifyDate" name="LaborRoomMasterFB" />
		<html:hidden property="headOfDepartmentEmpId" name="LaborRoomMasterFB" />
		<html:hidden property="lastModifiedSeatID" name="LaborRoomMasterFB" />
		<html:hidden property="chk" name="LaborRoomMasterFB" />
	</his:TransactionContainer>
</html:form>
</body>
</html>
