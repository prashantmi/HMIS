<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatientVO"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
<script type="text/javascript">

window.onload=function(){
	if(document.getElementsByName("hrgstr_fname")[0]){
		document.getElementsByName("hrgstr_fname")[0].focus();
	}
}

function enableRadio() 
{
	var count=0;
	
	for(var i=0;i<document.getElementsByName("crNoToRetrieve").length;i++)
	{
		if(document.getElementsByName("crNoToRetrieve")[i].checked==true)
		{
			if(document.getElementsByName("isMainCrNo")[i].value==<%=MrdConfig.CR_NUMBER_IS_MAIN_YES%>)
			{
				count+=1;
				
				document.getElementsByName("patMainCrNo")[i].disabled=false; 
				document.getElementsByName("patMainCrNo")[i].checked=true;

				disabledMainCrNo(document.getElementsByName("crNoToRetrieve")[i].value);
			}
		}
	}		
	
	for(var i=0;i<document.getElementsByName("crNoToRetrieve").length;i++)
	{
		if(document.getElementsByName("crNoToRetrieve")[i].checked==true)
		{
			if(count==0)
				document.getElementsByName("patMainCrNo")[i].disabled=false; 
			else
			{
				if(document.getElementsByName("isMainCrNo")[i].value==<%=MrdConfig.CR_NUMBER_IS_MAIN_YES%>)
					document.getElementsByName("patMainCrNo")[i].disabled=false; 
				else
					document.getElementsByName("patMainCrNo")[i].disabled=true;
			}	
		}
		else
		{
			document.getElementsByName("patMainCrNo")[i].disabled=true;
	
			if(document.getElementsByName("isMainCrNo")[i].value==<%=MrdConfig.CR_NUMBER_IS_MAIN_YES%> && !document.getElementsByName("crNoToRetrieve")[i].disabled)
			{
				document.getElementsByName("patMainCrNo")[i].checked=false;
				enabledMainCrNo();
			}
		}
	}

	if(count==0)
		getMinCRNumber();
}

function enabledMainCrNo()
{
	for(var i=0;i<document.getElementsByName("crNoToRetrieve").length;i++)
	{
		if(document.getElementsByName("isMainCrNo")[i].value==<%=MrdConfig.CR_NUMBER_IS_MAIN_YES%>)
		{
			document.getElementsByName("crNoToRetrieve")[i].disabled=false;
		}
	}
}

function disabledMainCrNo(crNo)
{
	for(var i=0;i<document.getElementsByName("crNoToRetrieve").length;i++)
	{
		if(document.getElementsByName("isMainCrNo")[i].value==<%=MrdConfig.CR_NUMBER_IS_MAIN_YES%> && document.getElementsByName("crNoToRetrieve")[i].value!=crNo)
		{
			document.getElementsByName("crNoToRetrieve")[i].disabled=true;
		}
	}
}

function getMinCRNumber()
{
	var crNo="";
	var min;
	for(var i=0;i<document.getElementsByName("crNoToRetrieve").length;i++)
	{
		if(document.getElementsByName("crNoToRetrieve")[i].checked==true)
		{
			crNo=crNo+document.getElementsByName("crNoToRetrieve")[i].value+"@";
		}
	}

	if(crNo!="") crNo=crNo.substring(0,crNo.length-1);
	
	var arrayCrNo=crNo.split('@');
	if(arrayCrNo.length==1)
	{
		min=arrayCrNo[0];
	}
	else
	{
		min=parseInt(arrayCrNo[0]);
		
		for(var i=1;i<arrayCrNo.length;i++)
		{
			if(min < parseInt(arrayCrNo[i]))
				min=min;
			else
				min=arrayCrNo[i];
		}
		
	}
		
	for(var i=0;i<document.getElementsByName("crNoToRetrieve").length;i++)
	{
		if(parseInt(document.getElementsByName("crNoToRetrieve")[i].value)==min)
		{
			document.getElementsByName("patMainCrNo")[i].checked=true;
		}
		else
		{
			document.getElementsByName("patMainCrNo")[i].checked=false;
		}
	}	
}

function submitSearch()
{
	if(document.forms[0].hrgstr_fname.value!="" ||
	document.forms[0].hrgstr_mname.value!="" ||
	document.forms[0].hrgstr_lname.value!="" ||
	document.forms[0].hrgstr_father_name.value!="" ||
	document.forms[0].gnum_gender_code.value!="-1" ||
	document.forms[0].gnum_city_loc_code.value!="-1" ||
	document.forms[0].hrgstr_contact_no.value!="" ||
	document.forms[0].hrgstr_national_id.value!="" ||
	document.forms[0].hrgnum_id_no.value!="")
	{
		document.getElementsByName("hmode")[0].value='SEARCH';
		document.forms[0].submit();
	}
	else
	{
		alert("Please Enter Value In Atleast One Field")
	}
}

function validateSave()
{
	var len=document.getElementsByName("crNoToRetrieve").length
	var count=0;
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("crNoToRetrieve")[i].checked)
		{
			count++;
		}
	}
	
	if(count < 2)
	{
		alert("Atleast Select Two CR Number");
		return false;
	}
	
	return true;
}

</script>

<body>
	<html:form action="/onlineCrNoMerge">
		<his:TransactionContainer>
			<his:TitleTag name="Merging of Cr Number">
				
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
								<html:text name="OnlineCRNoMergeFB" property="hrgstr_fname" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="OnlineCRNoMergeFB" property="hrgstr_mname" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="OnlineCRNoMergeFB" property="hrgstr_lname" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
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
									<bean:message key="fatherName"/>
								</B>
							</font>
						</div>	
					</td>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="gender"/>
								</B>
							</font>
						</div>
					</td>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="location"/>
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
								<html:text name="OnlineCRNoMergeFB" property="hrgstr_father_name" tabindex="1" maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:select name="OnlineCRNoMergeFB" property="gnum_gender_code" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ESSENTIAL_GENDER_LIST %>">
										<html:options collection="<%=MrdConfig.ESSENTIAL_GENDER_LIST %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:select name="OnlineCRNoMergeFB" tabindex="1" property="gnum_city_loc_code">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ESSENTIAL_CITY_LOCATION_LIST %>">
										<html:options collection="<%=MrdConfig.ESSENTIAL_CITY_LOCATION_LIST %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
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
								<html:text name="OnlineCRNoMergeFB" property="hrgnum_id_no" tabindex="1" maxlength="30" onkeypress="return validateAlphaNumericOnly(event,this)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="OnlineCRNoMergeFB" property="hrgstr_contact_no" tabindex="1" maxlength="30" onkeypress="return validateNumeric(event)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="OnlineCRNoMergeFB" property="hrgstr_national_id" tabindex="1" maxlength="16" onkeypress="return validateAlphaNumericOnly(event,this)"/>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' tabindex="1" style="cursor:pointer" alt="Search" title="Search" onclick="submitSearch()" onkeypress="if(event.keyCode==13) submitSearch();" >
			 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('CANCEL');" onkeypress="if(event.keyCode==13) submitForm('CANCEL');"  >
		 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</his:ButtonToolBarTag>
			
			<%if(session.getAttribute(MrdConfig.ARR_ONLINE_SEARCH_PATIENT)!=null)
				{
				PatientVO[] patVO=(PatientVO[])session.getAttribute(MrdConfig.ARR_ONLINE_SEARCH_PATIENT);
				if(patVO.length>0){
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
					<td width="8%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="main" /> 
									<bean:message key="crNo" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="12%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;" >
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="crNo" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="23%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
					<td width="27%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="address" />
								</font>
							</b>
						</div>
					</td>
				</tr>
				
				<logic:iterate id="arrPatientVO" name="<%=MrdConfig.ARR_ONLINE_SEARCH_PATIENT %>" type="hisglobal.vo.PatientVO">
				<%String color="";
				if(arrPatientVO.getIsMainCrNo().equals(MrdConfig.CR_NUMBER_IS_MAIN_YES))
						 color="#FF0000";
					else
						 color="#000000";	
					%>
					<tr>
						<td width="5%" class="tdfonthead" >
							<div align="center">
								<html:checkbox name="OnlineCRNoMergeFB" property="crNoToRetrieve" value="<%=arrPatientVO.getPatCrNo()%>" onclick="enableRadio()" tabindex="1"></html:checkbox>
								<html:hidden name="OnlineCRNoMergeFB" property="isMainCrNo" value="<%=arrPatientVO.getIsMainCrNo() %>"/>
							</div>
						</td>
						<td width="8%" class="tdfont" >
							<div align="center">
								<html:radio  name="OnlineCRNoMergeFB" property="patMainCrNo" value="<%=arrPatientVO.getPatCrNo()%>" disabled="true" tabindex="1"></html:radio>
							</div>
						</td>
						<td width="12%" class="tdfont">
							<div align="center">
								<font color="<%=color %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatientVO" property="patCrNo"/>
								</font>	
							</div>
						</td>
						<td width="23%" class="tdfont" >
							<div align="center">
								<font color="<%=color %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatientVO" property="patFirstName"/>
									<bean:write name="arrPatientVO" property="patMiddleName"/>
									<bean:write name="arrPatientVO" property="patLastName"/>
								</font>	
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="<%=color %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatientVO" property="patAge"/>/
									<bean:write name="arrPatientVO" property="patGender"/>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="<%=color %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatientVO" property="patGuardianName"/>
								</font>	
							</div>
						</td>
						<td width="27%" class="tdfont" >
							<div align="center">
								<font color="<%=color %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatientVO" property="patAddHNo"/>
									<bean:write name="arrPatientVO" property="patAddCityLoc"/>
									<bean:write name="arrPatientVO" property="patAddCity"/>
									<bean:write name="arrPatientVO" property="patAddDistrict"/>
								</font>	
							</div>
						</td>
					</tr>	
				</logic:iterate>
			</table>
			
		</his:ContentTag>
	
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td width="15%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="remarks"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="85%" class="tdfont">
						<div align="left">
							<html:text name="OnlineCRNoMergeFB" property="remarks" maxlength="50" size="35" tabindex="1" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
		</his:ButtonToolBarTag>
			<%}} %>
			
		</his:TransactionContainer>
		
		
		
		<html:hidden name="OnlineCRNoMergeFB" property="hmode"/>
		
		
		<his:status/>
	</html:form>
</body>

</html>