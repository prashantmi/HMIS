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
	document.getElementsByName("searchFName")[0].value="";
	document.getElementsByName("searchMName")[0].value="";
	document.getElementsByName("searchLName")[0].value="";
	document.getElementsByName("deathDate")[0].value="";
}

function submitSearch()
{
	var valid
	if(document.forms[0].searchFName.value!="" ||
	document.forms[0].searchMName.value!="" ||
	document.forms[0].searchLName.value!="" ||
	document.forms[0].deathDate.value!="" )
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

function getDetail(obj)
{	
	opener.document.getElementsByName('hmode')[0].value='DECEASEDDTL'
	opener.document.getElementsByName("deceasedNo")[0].value=obj.value;
	opener.document.forms[0].submit();
	self.close();
}
</script>

<html:form action="/deceasedGeneralAppearance">
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
								<bean:message key="firstName"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<html:text name="DeceasedGeneralAppearanceFB" property="searchFName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
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
							<html:text name="DeceasedGeneralAppearanceFB" property="searchMName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
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
							<html:text name="DeceasedGeneralAppearanceFB" property="searchLName" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
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
						<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="18%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deceasedNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deceased"/>
										<bean:message key="name"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
						<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deathdate"/>
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate name="<%=MortuaryConfig.ARR_SEARCHED_DECEASED_VO %>" id="searchVO" type="hisglobal.vo.DeceasedDetailVO">
						<tr>
							<td width="7%" class="tdfont">
								<div align="center">
									<html:radio name="DeceasedGeneralAppearanceFB" property="selectedDeceasedNo" value="<%=searchVO.getDeceasedNo()%>" onclick="getDetail(this)" tabindex="1" ></html:radio>
								</div>
							</td>
							<td width="18%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getDeceasedNo() %>
									</font>
								</div>
							</td>
							<td width="30%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getPatFirstName()==null?"-":searchVO.getPatFirstName() %>
										<%=searchVO.getPatMiddleName()==null?"":searchVO.getPatMiddleName() %>
										<%=searchVO.getPatLastName()==null?"":searchVO.getPatLastName() %>
									</font>
								</div>
							</td>
							<td width="20%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getPatGender() %>/
										<%=searchVO.getPatAge() %>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchVO.getDeathDate() %>
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
	
	<html:hidden name="DeceasedGeneralAppearanceFB" property="hmode"/>
</html:form>