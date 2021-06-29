
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatientVO"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function submitSearch()
{
	var valid
	if(document.forms[0].hrgstr_fname.value!="" ||
	document.forms[0].hrgstr_mname.value!="" ||
	document.forms[0].hrgstr_lname.value!="" ||
	document.forms[0].hrgstr_contact_no.value!="" ||
	document.forms[0].hrgstr_national_id.value!="" ||
	document.forms[0].hrgnum_id_no.value!="")
	{
	valid=true
	document.getElementsByName("hmode")[0].value='SEARCH';
	document.forms[0].submit();
	
	}
	else
	{
		valid=false
		alert("Please Enter Value In Atleast One Field")
	}
	return valid
}

function validateAddPatient()
{
	var len=document.getElementsByName("crNoToRetrieve").length
	var count=0;
	var patCrNo="";
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("crNoToRetrieve")[i].checked)
		{
			count++;
			patCrNo=patCrNo+document.getElementsByName("crNoToRetrieve")[i].value+"@";
		}
	}
	
	if(count==0)
	{
		alert("Please Select a Patient");
	}
	else
	{
		if(patCrNo!="") patCrNo=patCrNo.substring(0,patCrNo.length-1);
		
	opener.document.getElementsByName('hmode')[0].value='GO'
	opener.document.getElementsByName('concatedCrNo')[0].value=patCrNo;
	opener.document.forms[0].submit();
	//	submitPage('GO');
	//alert("submit opener")
		self.close();
	}
}



</script>

<html:form action="/crNoMerged">
<body>  
	<his:TitleTag>
		<his:name>
			<bean:message key="search"/>
		</his:name>
    </his:TitleTag>

	<his:SubTitleTag name="">
		<tr>
			<td width="30%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<B>
							<bean:message key="firstName"/>
						</B>
					</font>
				</div>	
			</td>
			<td width="30%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<B>
							<bean:message key="middleName"/>
						</B>
					</font>
				</div>
			</td>
			<td width="30%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<B>
							<bean:message key="lastName"/>
						</B>
					</font>
				</div>	
			</td>
		</tr>
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="CrNoMergeDtlFB" property="hrgstr_fname" tabindex="1" maxlength="30" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="CrNoMergeDtlFB" property="hrgstr_mname" tabindex="1" maxlength="20" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="CrNoMergeDtlFB" property="hrgstr_lname" tabindex="1" maxlength="30" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	
	<his:SubTitleTag name="">
		<tr>
			<td width="30%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<B>
							<bean:message key="employeeid"/>
						</B>
					</font>
				</div>	
			</td>
			<td width="30%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<B>
							<bean:message key="contactNo"/>
						</B>
					</font>
				</div>
			</td>
			<td width="30%" >
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<B>
							<bean:message key="nationalId"/>
						</B>
					</font>
				</div>	
			</td>
		</tr>
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="CrNoMergeDtlFB" property="hrgnum_id_no" tabindex="1" maxlength="30" onkeypress="if(event.keyCode!=13) return validateAlphaNumericOnly(event,this); else submitSearch();"/>
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="CrNoMergeDtlFB" property="hrgstr_contact_no" tabindex="1" maxlength="30" onkeypress="return validateNumeric(event)"/>
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<html:text name="CrNoMergeDtlFB" property="hrgstr_national_id" tabindex="1" maxlength="16" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	
	<his:ButtonToolBarTag>
		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="submitSearch()" tabindex="1" onkeypress="if(event.keyCode==13) submitSearch()" >
	 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('POPUP')" onkeypress="if(event.keyCode==13) submitForm('POPUP');">
	</his:ButtonToolBarTag>
	
	<%if(session.getAttribute(MrdConfig.ARR_SEARCH_PATIENT)!=null)
	{
		List lstPatVO=(List)session.getAttribute(MrdConfig.ARR_SEARCH_PATIENT);
		if(lstPatVO.size()>0){
	%>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="5%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="select" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="15%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;" >
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="crNo" /> 
										
								</font>
							</b>
						</div>
					</td>
					<td width="25%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="name" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="10%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="age" />
									<bean:message key="slash" />
									<bean:message key="gender" />
								</font>
							</b>
						</div>
					</td>
					<td width="15%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="fatherName" />
								</font>
							</b>
						</div>
					</td>
					<td width="30%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="address" />
								</font>
							</b>
						</div>
					</td>
				</tr>
				
				<logic:iterate id="arrPatientVO" name="<%=MrdConfig.ARR_SEARCH_PATIENT %>" type="hisglobal.vo.PatientVO">
					<tr>
						<td width="5%" class="tdfonthead" >
							<div align="center">
								<html:checkbox name="CrNoMergeDtlFB" property="crNoToRetrieve" value="<%=arrPatientVO.getPatCrNo()%>" tabindex="1"></html:checkbox>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<bean:write name="arrPatientVO" property="patCrNo"/>
							</div>
						</td>
						<td width="25%" class="tdfont" >
							<div align="center">
								<bean:write name="arrPatientVO" property="patFirstName"/>
								<bean:write name="arrPatientVO" property="patMiddleName"/>
								<bean:write name="arrPatientVO" property="patLastName"/>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<bean:write name="arrPatientVO" property="patAge"/>/
								<bean:write name="arrPatientVO" property="patGender"/>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<bean:write name="arrPatientVO" property="patGuardianName"/>
							</div>
						</td>
						<td width="30%" class="tdfont" >
							<div align="center">
								<bean:write name="arrPatientVO" property="patAddHNo"/>
								<bean:write name="arrPatientVO" property="patAddCityLoc"/>
								<bean:write name="arrPatientVO" property="patAddCityLocCode"/>
								<bean:write name="arrPatientVO" property="patAddCity"/>
								<bean:write name="arrPatientVO" property="patAddDistrict"/>
							</div>
						</td>
					</tr>	
				</logic:iterate>
			</table>
			
		</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateAddPatient()" onkeypress="if(event.keyCode==13)validateAddPatient() ">
		</his:ButtonToolBarTag>
	<%}} %>
	
	<html:hidden name="CrNoMergeDtlFB" property="hmode"/>
	<html:hidden name="CrNoMergeDtlFB" property="patCrNo"/>
	<his:status/>
	</body>
</html:form>