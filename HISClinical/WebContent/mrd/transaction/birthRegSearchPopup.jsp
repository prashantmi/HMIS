
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
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
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

window.onload=function()
{
	showChildMotherDiv();
}
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
	document.getElementsByName("searchChildCrNo")[0].value="";//<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>;
	document.getElementsByName("searchMomCrNo")[0].value="";//<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>;
	document.getElementsByName("searchMomFName")[0].value="";
	document.getElementsByName("searchMomMName")[0].value="";
	document.getElementsByName("searchMomLName")[0].value="";
	document.getElementsByName("searchChildDob")[0].value="";
}

function submitSearch()
{
	var valid
	if(document.getElementsByName("searchType")[0].checked)
	{
		if(document.forms[0].searchChildCrNo.value!="" ||
		document.forms[0].searchChildDob.value!="" )
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
	}
	else
	{
		if(document.forms[0].searchMomCrNo.value!="" ||
		document.forms[0].searchMomFName.value!="" ||
		document.forms[0].searchMomMName.value!="" ||
		document.forms[0].searchMomLName.value!="" )
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
	}	
	return valid
}

function showChildMotherDiv()
{
	if(document.getElementsByName("searchType")[0].checked)
	{
		document.getElementById("divChild").style.display="block";
		document.getElementById("divMother").style.display="none";
	}
	else
	{
		document.getElementById("divChild").style.display="none";
		document.getElementById("divMother").style.display="block";
	}
}

function getDetail(obj)
{	
	var statusCode=obj.value.split("@")[1];
	var selectedChild=obj.value.split("@")[0];
	//alert("statusCode>>>>"+statusCode+">>selectedChild>>"+selectedChild);
		
		document.getElementsByName("statusCode")[0].value=statusCode;
		
	
	if(document.getElementsByName("statusCode")[0].value==0)
	{
		opener.document.getElementsByName('hmode')[0].value='GETDTL'
		opener.document.getElementsByName("statusCode")[0].value=statusCode;
		opener.document.getElementsByName("selectedChild")[0].value=selectedChild;	
		opener.document.getElementsByName('isFromSearch')[0].value=<%=MrdConfig.YES%>;
	//	alert(opener.document.getElementsByName("selectedChild")[0].value)
		opener.document.forms[0].submit();
		self.close();
		//submitForm('GETDTL');
	}	
	if(document.getElementsByName("statusCode")[0].value==1)
	{
		opener.document.getElementsByName('hmode')[0].value='GETHANDOVERDTL'
		opener.document.getElementsByName("statusCode")[0].value=statusCode;
		opener.document.getElementsByName("selectedChild")[0].value=selectedChild;	
		opener.document.getElementsByName('isFromSearch')[0].value=<%=MrdConfig.YES%>;
		opener.document.forms[0].submit();
		self.close();
	}
	if(document.getElementsByName("statusCode")[0].value==2 || document.getElementsByName("statusCode")[0].value==3)
	{
		opener.document.getElementsByName('hmode')[0].value='GETDUPHANDOVERDTL'
		opener.document.getElementsByName("statusCode")[0].value=statusCode;
		opener.document.getElementsByName("selectedChild")[0].value=selectedChild;	
		opener.document.getElementsByName('isFromSearch')[0].value=<%=MrdConfig.YES%>;
		opener.document.forms[0].submit();
		self.close();
	}
		
}

</script>


<html:form action="/birthRegistrationUpload">
	<body>
		<his:SubTitleTag name="Search">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="selectseacrhType"/>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="child"/>
							</font>	
							<html:radio name="BirthRegistrationUploadFB" property="searchType"  value="<%=MrdConfig.SEARCH_TYPE_CHILD %>" onclick="showChildMotherDiv()"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mother"/>
							</font>	
							<html:radio name="BirthRegistrationUploadFB" property="searchType"  value="<%=MrdConfig.SEARCH_TYPE_MOTHER %>" onclick="showChildMotherDiv()"></html:radio>
						</div>
					</td>
				</tr>	
			</table>
			<div id="divChild">	
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="child"/>
									<bean:message key="crNo"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
							 <%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
								<html:text name="BirthRegistrationUploadFB" property="searchChildCrNo" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>"  onkeypress="return validateNumeric(event)"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="child"/>
									<bean:message key="dob"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="searchChildDob"></his:date>
							</div>
						</td>
					</tr>
				</table>
			</div>	
			<div id="divMother">	
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">	
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="motherCrNo"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="BirthRegistrationUploadFB" property="searchMomCrNo" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>"  onkeypress="return validateNumeric(event)"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="mother"/>
									<bean:message key="firstName"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="BirthRegistrationUploadFB" property="searchMomFName"></html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="mother"/>
									<bean:message key="middleName"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="BirthRegistrationUploadFB" property="searchMomMName"></html:text>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="mother"/>
									<bean:message key="lastName"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:text name="BirthRegistrationUploadFB" property="searchMomLName"></html:text>
							</div>
						</td>
					</tr>
				</table>
			</div>	
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
				<logic:iterate id="arrBirthListVO" name="<%=MrdConfig.ARR_BIRTH_REGISTRATION_UPLOAD_DUPLICATE_VO %>" type="hisglobal.vo.ANCNeonatalDetailVO" indexId="idx">
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
		</his:statusTransactionInProcess>
		
		
		
		<his:status/>
	</body>
	
	<html:hidden name="BirthRegistrationUploadFB" property="hmode"/>
	<html:hidden name="BirthRegistrationUploadFB" property="selectedChild"/>
	<html:hidden name="BirthRegistrationUploadFB" property="statusCode"/>
	<html:hidden name="BirthRegistrationUploadFB" property="isFromSearch"/>
</html:form>
