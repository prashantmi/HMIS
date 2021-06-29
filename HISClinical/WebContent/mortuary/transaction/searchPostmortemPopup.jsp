<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>

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

function clearForm()
{
	document.getElementsByName("deceasedNo")[0].value="";
	document.getElementsByName("searchFName")[0].value="";
	document.getElementsByName("searchMName")[0].value="";
	document.getElementsByName("searchLName")[0].value="";
	document.getElementsByName("deathDate")[0].value="";
}

function submitSearch()
{
	var valid;
	if(document.forms[0].deceasedNo.value!="" ||
	document.forms[0].searchFName.value!="" ||
	document.forms[0].searchMName.value!="" ||
	document.forms[0].searchLName.value!="" ||
	document.forms[0].deathDate.value!="" )
	{
		if(document.forms[0].deceasedNo.value!="" && !validateDeceasedNo())
			return false;
	
		valid=true;
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

function getDetail(obj)
{	
	opener.document.getElementsByName('hmode')[0].value='GETREQUEST'
	opener.document.getElementsByName("postmortemId")[0].value=obj.value;
	opener.document.forms[0].submit();
	self.close();
}

function validateDeceasedNo()
{
	var valid=false;
	if(document.getElementsByName("deceasedNo")[0].value=="")
	{
		alert("Please Enter The Deceased No");
		document.getElementsByName("deceasedNo")[0].focus();
		valid=false;
	}
	else
	{
		if(document.getElementsByName("deceasedNo")[0].value.length==12)
			valid=true;
		else
		{
			alert("Invalid Deceased No");
			document.getElementsByName("deceasedNo")[0].focus();
			valid=false;
		}
	}
	return valid;
}

</script>

<html:form action="/sampleResultEntry">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<his:SubTitleTag name="Search">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="deceasedNo"/>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" colspan="3"> 
						<div align="left">
							<html:text name="SampleResultEntryFB" property="deceasedNo" maxlength="10" onkeypress="return validateNumericOnly(this,event)" tabindex="1"></html:text>
						</div>
					</td>
				</tr>	
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="firstName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="SampleResultEntryFB" property="searchFName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="middleName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="SampleResultEntryFB" property="searchMName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="lastName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="SampleResultEntryFB" property="searchLName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="deathdate"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<his:date name="deathDate"></his:date>
						</div>
					</td>
				</tr>
			</table>	
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="submitSearch()" tabindex="1" onkeypress="if(event.keyCode==13) submitSearch()" >
		 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
 			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
		</his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
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
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deceasedNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deceased"/>
										<bean:message key="name"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="gender"/>
										<bean:message key="slash"/>
										<bean:message key="age"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deathdate"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="postmortemNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="requestDate"/> <bean:message key="time"/>
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate name="<%=MortuaryConfig.ARR_SEARCHED_POSTMORTEM_VO %>" id="searchVO" type="hisglobal.vo.DeceasedDetailVO">
						<tr>
							<td width="5%" class="tdfont">
								<div align="center">
									<html:radio name="SampleResultEntryFB" property="selectedPostmortemNo" value="<%=searchVO.getPostmortemId()%>" onclick="getDetail(this)" tabindex="1" ></html:radio>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getDeceasedNo() %>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getPatFirstName()==null?"-":searchVO.getPatFirstName() %>
										<%=searchVO.getPatMiddleName()==null?"":searchVO.getPatMiddleName() %>
										<%=searchVO.getPatLastName()==null?"":searchVO.getPatLastName() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getPatGender() %>/
										<%=searchVO.getPatAge() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getDeathDate() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getPostmortemId() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getRequestDateTime() %>
									</font>
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>		
		
		</his:statusTransactionInProcess>
	
		<his:status/>
	
	</body>
	
	<html:hidden name="SampleResultEntryFB" property="hmode"/>
</html:form>