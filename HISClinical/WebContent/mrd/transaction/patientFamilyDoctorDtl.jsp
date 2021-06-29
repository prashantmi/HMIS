<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatFamilyDocDtlVO"%>
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
<his:javascript src="/hisglobal/js/calendar.js"/>

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateMobileNo(e,obj){
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	
	//alert("key--"+key);
	//alert("keychar--"+keychar);
	//alert("getCursorIdex(obj)--"+getCursorIdex(obj));
	//alert("value----"+obj.value)
	
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27))
	   return true;
	else if(key==32)
		return false
	else if((getCursorIdex(obj)==0) && (key==48))
		return false
	else if(getCursorIdex(obj)==10)
		return false

	// alphas and space
	
	
	
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
	
	

}

function checkMobileNoLength(obj)
{
	var flag=true;
	if(obj.value.length!=10)
	{
		alert("Mobile No. Should be of 10 Digits")
		flag=false;
		obj.focus();
		return flag;
	}
	if(obj.value.charAt(0)==0)
	{
		alert("Mobile No. Cannot start with 0")
		flag= false;	
		obj.focus();
		return flag;	 
	} 						
	return flag;
}

window.onload=function(){
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
}
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateAddModify()
{

//alert(checkMobileNoLength(document.getElementsByName("mobileNo")[0]))

	if(document.getElementsByName("physicianType")[0].value=="-1")
	{
		alert("Please Select The Physician Type");
		document.getElementsByName("physicianType")[0].focus();
		return false;
	}
	else if(document.getElementsByName("doctorName")[0].value=="")
	{
		alert("Please Enter The Doctor Name");
		document.getElementsByName("doctorName")[0].focus();
		return false;
	}
	else if(document.getElementsByName("clinicHosName")[0].value=="")
	{
		alert("Please Enter The Clinic/Hospital Name");
		document.getElementsByName("clinicHosName")[0].focus();
		return false;
	}
	else if(document.getElementsByName("address1")[0].value=="")
	{
		alert("Please Enter The Address");
		document.getElementsByName("address1")[0].focus();
		return false;
	}
	else if(document.getElementsByName("city")[0].value=="")
	{
		alert("Please Enter The City");
		document.getElementsByName("city")[0].focus();
		return false;
	}
	else if(document.getElementsByName("mobileNo")[0].value=="")//document.getElementsByName("phNo")[0].value=="" && 
	{
		alert("Please Enter Mobile No");
		document.getElementsByName("mobileNo")[0].focus();
		return false;
	}
	else if(document.getElementsByName("consultationFor")[0].value=="")
	{
		alert("Please Enter The Doctor Speciality ");
		document.getElementsByName("consultationFor")[0].focus();
		return false;
	}
	else 
	return (checkMobileNoLength(document.getElementsByName("mobileNo")[0]));
	
}

function validateListModifyView()
{
	var len;
	count=0;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{    
        alert("Please Select a Record");
        return false;
    }
    if(count>1)
    {    
        alert("Multiple Records Not Allowed");
        return false;
    }
    
    return true;
}

function validateListDelete()
{
	var len;
	count=0;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{    
        alert("Please Select At Least One Record");
        return false;
    }

	if(!confirm("Are you sure to delete the selected Record!"))
	{
        return false;
    }
    return true;
}

function clearForm()
{
	document.getElementsByName("doctorName")[0].value="";
	document.getElementsByName("physicianType")[0].value="-1";
	document.getElementsByName("qualification")[0].value="";
	document.getElementsByName("doctorId")[0].value="";
	document.getElementsByName("docRegNo")[0].value="";
	document.getElementsByName("clinicHosName")[0].value="";
	document.getElementsByName("clinicHosId")[0].value="";
	document.getElementsByName("address1")[0].value="";
	document.getElementsByName("address2")[0].value="";
	document.getElementsByName("city")[0].value="";
	document.getElementsByName("phNo")[0].value="";
	document.getElementsByName("mobileNo")[0].value="";
	document.getElementsByName("email")[0].value="";
	document.getElementsByName("faxNo")[0].value="";
	document.getElementsByName("patientId")[0].value="";
	document.getElementsByName("consultationFor")[0].value="";
	document.getElementsByName("remarks")[0].value="";
}

</script>

<body>
	<html:form action="/patientFamilyDoctorDtl">
	<%boolean readOnly=false; %>
		<his:TransactionContainer>
			<his:TitleTag name="Patient Family Doctor Detail">
			</his:TitleTag>
			
			<his:statusNew>
				<logic:empty name="PatientFamilyDoctorDtlFB" property="patCrNo" >
					<his:InputCrNoTag name="PatientFamilyDoctorDtlFB"></his:InputCrNoTag>
				</logic:empty>
			</his:statusNew>
			
			<his:statusList>
			
				<jsp:include page="/registration/patientDetail.cnt" flush="true" />
				<%PatFamilyDocDtlVO[] arrExistingPatFamilyDocVO=(PatFamilyDocDtlVO[])session.getAttribute(MrdConfig.ARR_EXISTING_PAT_FAMILY_DOC);
					if(arrExistingPatFamilyDocVO.length>0)
					{
				%>
					<his:SubTitleTag name="Existing Family Doctor Detail">
					</his:SubTitleTag>
					
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="10%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="doctor"/>
												<bean:message key="name"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="physician"/>
												<bean:message key="type"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="40%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="speciality"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="contactNo"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=MrdConfig.ARR_EXISTING_PAT_FAMILY_DOC %>" id="arrExistFamilDoctorVO" type="hisglobal.vo.PatFamilyDocDtlVO">
								<tr>
									<td class="tdfont">
										<div align="center">
											<html:checkbox name="PatientFamilyDoctorDtlFB" tabindex="1" property="chk" value='<%=arrExistFamilDoctorVO.getPatCrNo()+"@"+arrExistFamilDoctorVO.getHospitalCode()+"@"+arrExistFamilDoctorVO.getSlNo() %>'></html:checkbox>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
											<%=arrExistFamilDoctorVO.getDoctorName() %>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
											<%=arrExistFamilDoctorVO.getPhysicianTypeDesc() %>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
											<%=arrExistFamilDoctorVO.getConsultationFor() %>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
											<%=(arrExistFamilDoctorVO.getMobileNo()==null)?arrExistFamilDoctorVO.getPhNo():arrExistFamilDoctorVO.getMobileNo() %>
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>		
					</his:ContentTag>
				<%} %>
				
			</his:statusList>	
			<his:statusTransactionInProcess>
			<jsp:include page="/registration/patientDetail.cnt" flush="true" />
				<logic:equal name="PatientFamilyDoctorDtlFB" property="hmode" value="ADD">
					<his:SubTitleTag name="Family Doctor Details Entry"> 
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="physician"/>
											<bean:message key="type"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										<html:select name="PatientFamilyDoctorDtlFB" tabindex="1" property="physicianType">
											<html:option value="-1">Select Value</html:option>	
											<logic:present name="<%=MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST%>">									
												<html:options collection="<%=MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST %>" property="value" labelProperty="label"/>
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
											<bean:message key="doctor"/>
											<bean:message key="name"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" tabindex="1" property="doctorName" maxlength="60" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="doctorId"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="doctorId" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="qualification"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" tabindex="1" property="qualification" maxlength="50" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="regNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="docRegNo" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="clinic/hos"/>
											<bean:message key="name"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="clinicHosName" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="clinic/hos"/>
											<bean:message key="id"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="clinicHosId" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="address1"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="address1" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="address2"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="address2" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="city"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="city" tabindex="1" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="mobileNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="mobileNo" tabindex="1"  maxlength="10" onkeypress="return validateMobileNo(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="phone"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="phNo" tabindex="1" maxlength="20" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="email"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="email" tabindex="1"  onkeypress="return CheckMaxLength(event,this,50,3)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="faxNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="faxNo" tabindex="1" maxlength="20" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="patientId"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="patientId" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="speciality"/>/
											<bean:message key="consultationFor"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="PatientFamilyDoctorDtlFB" property="consultationFor" tabindex="1" rows="1" cols="30" onkeypress="return CheckMaxLength(event,this,100,1)"></html:textarea>
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
									<div align="left">
										<html:textarea name="PatientFamilyDoctorDtlFB" property="remarks" tabindex="1" rows="1" cols="30" onkeypress="return CheckMaxLength(event,this,100,1)"></html:textarea>
									</div>
								</td>
							</tr>
						</table>		
					</his:ContentTag>
				</logic:equal>
				<logic:equal name="PatientFamilyDoctorDtlFB" property="hmode" value="VIEW">
					<% readOnly=true; %>
				</logic:equal>
				<logic:notEqual name="PatientFamilyDoctorDtlFB" property="hmode" value="ADD">
					<his:SubTitleTag name="Family Doctor Details Modify"> 
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="physician"/>
											<bean:message key="type"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										<html:select name="PatientFamilyDoctorDtlFB" property="physicianType" tabindex="1" disabled="<%=readOnly %>">
											<html:option value="-1">Select Value</html:option>	
											<logic:present name="<%=MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST%>">									
												<html:options collection="<%=MrdConfig.FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST %>" property="value" labelProperty="label"/>
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
											<bean:message key="doctor"/>
											<bean:message key="name"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="doctorName" tabindex="1" maxlength="60" onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=readOnly %>"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="doctorId"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="doctorId" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="qualification"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="qualification" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="regNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="docRegNo" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="clinic/hos"/>
											<bean:message key="name"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="clinicHosName" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="clinic/hos"/>
											<bean:message key="id"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="clinicHosId" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="address1"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="address1" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="address2"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="address2" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="city"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="city" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="phone"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="phNo" tabindex="1" maxlength="20" readonly="<%=readOnly %>" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="mobileNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="mobileNo" tabindex="1" maxlength="20" readonly="<%=readOnly %>" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="email"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="email" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return CheckMaxLength(event,this,50,3)"></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="faxNo"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="faxNo" tabindex="1" maxlength="20" readonly="<%=readOnly %>" onkeypress="return validateNumeric(event)"></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="patientId"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="PatientFamilyDoctorDtlFB" property="patientId" tabindex="1" maxlength="50" readonly="<%=readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="speciality"/>/
											<bean:message key="consultationFor"/>
										</font>	
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="PatientFamilyDoctorDtlFB" property="consultationFor" tabindex="1" rows="1" cols="30" readonly="<%=readOnly %>" onkeypress="return CheckMaxLength(event,this,100,1)"></html:textarea>
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
									<div align="left">
										<html:textarea name="PatientFamilyDoctorDtlFB" property="remarks" tabindex="1" rows="1" cols="30" readonly="<%=readOnly %>" onkeypress="return CheckMaxLength(event,this,100,1)"></html:textarea>
									</div>
								</td>
							</tr>
						</table>		
					</his:ContentTag>
				</logic:notEqual>
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusNew>		
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
				</his:statusNew>
				
				<his:statusList>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('ADD')" onkeypress="if(event.keyCode==13)submitPage('ADD')">
					<%PatFamilyDocDtlVO[] arrExistPatFamilyDocVO=(PatFamilyDocDtlVO[])session.getAttribute(MrdConfig.ARR_EXISTING_PAT_FAMILY_DOC);
					if(arrExistPatFamilyDocVO.length>0)
					{
					%>
						<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateListModifyView()) submitPage('MODIFY')" onkeypress="if(event.keyCode==13)if(validateListModifyView()) submitPage('MODIFY')">
						<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateListModifyView()) submitPage('VIEW')" onkeypress="if(event.keyCode==13)if(validateListModifyView()) submitPage('VIEW')">
						<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateListDelete()) submitPage('DELETE')" onkeypress="if(event.keyCode==13)if(validateListDelete()) submitPage('DELETE')">
					<%} %>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('NEW')" onkeypress="if(event.keyCode==13)submitPage('NEW')">
				</his:statusList>
				
				<his:statusTransactionInProcess>
					<logic:equal name="PatientFamilyDoctorDtlFB" property="hmode" value="ADD">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validateAddModify()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateAddModify()) submitPage('SAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('GETPATDTL')" onkeypress="if(event.keyCode==13)submitPage('GETPATDTL')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
					</logic:equal>
					
					
					<logic:equal name="PatientFamilyDoctorDtlFB" property="hmode" value="MODIFY">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateAddModify()) submitPage('MODIFYSAVE')" onkeypress="if(event.keyCode==13)if(validateAddModify()) submitPage('MODIFYSAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('GETPATDTL')" onkeypress="if(event.keyCode==13)submitPage('GETPATDTL')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
					</logic:equal>
					
					<logic:equal name="PatientFamilyDoctorDtlFB" property="hmode" value="VIEW">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('GETPATDTL')" onkeypress="if(event.keyCode==13)submitPage('GETPATDTL')">
					</logic:equal>
				</his:statusTransactionInProcess>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>
		
		<html:hidden name="PatientFamilyDoctorDtlFB" property="hmode" value="unspecified"/>
		<html:hidden name="PatientFamilyDoctorDtlFB" property="patCrNo"/>
		<html:hidden name="PatientFamilyDoctorDtlFB" property="slNo"/>
		
	
	</html:form>
	<his:status/>
</body>

</html>