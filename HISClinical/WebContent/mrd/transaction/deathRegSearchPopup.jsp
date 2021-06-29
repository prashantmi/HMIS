<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="mrd.MrdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function validateSearch(mode)
{
	if(document.getElementsByName("str_patCrNo")[0].value=="" && document.getElementsByName("str_deathDate")[0].value=="" && document.getElementsByName("str_firstName")[0].value=="" && document.getElementsByName("str_middleName")[0].value=="" && document.getElementsByName("str_lastName")[0].value=="")
	{
		alert("Please enter something for search");
		document.getElementsByName("str_firstName")[0].focus();
		return false;
	}
	
	submitPage(mode);
	
}

function clearForm()
{
	document.getElementsByName("str_patCrNo")[0].value="";
	document.getElementsByName("str_deathDate")[0].value="";
	document.getElementsByName("str_firstName")[0].value="";
	document.getElementsByName("str_middleName")[0].value="";
	document.getElementsByName("str_lastName")[0].value="";
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
		opener.document.getElementsByName("patCrNo")[0].value=selectedChild;	
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
		opener.document.getElementsByName("patCrNo")[0].value=selectedChild;	
		opener.document.getElementsByName('isFromSearch')[0].value=<%=MrdConfig.YES%>;
		opener.document.forms[0].submit();
		self.close();
	}
	if(document.getElementsByName("statusCode")[0].value==2 || document.getElementsByName("statusCode")[0].value==3)
	{
		opener.document.getElementsByName('hmode')[0].value='GETDUPHANDOVERDTL'
		opener.document.getElementsByName("statusCode")[0].value=statusCode;
		opener.document.getElementsByName("patCrNo")[0].value=selectedChild;	
		opener.document.getElementsByName('isFromSearch')[0].value=<%=MrdConfig.YES%>;
		opener.document.forms[0].submit();
		self.close();
	}
		
}


</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/deathRegistrationUpload">
		
		<his:TitleTag name="Search">
		</his:TitleTag>
		<his:ContentTag>
			
		<table  width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fname"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="DeathRegistrationUploadFB"  maxlength="32" size="30" property="str_firstName" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mname"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="DeathRegistrationUploadFB"  maxlength="32" size="30" property="str_middleName" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="lname"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="DeathRegistrationUploadFB"  maxlength="32" size="30" property="str_lastName" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		
	  	</tr>
		
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="DeathRegistrationUploadFB"  maxlength="13" size="30" property="str_patCrNo" styleClass ="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="deathdate"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
	  				<his:date name="str_deathDate" dateFormate="%d-%b-%Y" ></his:date>
				</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
	  		</td>
	  		<td width="17%" class="tdfont">
	  		</td>
	  		
	  	</tr>
		
	  	</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
	 		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="validateSearch('SEARCHDETAIL');" >
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
 		</his:ButtonToolBarTag>
 		
 		<logic:present name="<%=MrdConfig.SERACH_DEATH_REGISTRATION_UPLOAD_LIST %>">
 		<his:SubTitleTag name="Search Detail">
 		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="crNo"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="name"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="12%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="gender"/>/
									<bean:message key="age"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="deathtime"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="19%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="status"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate id="arrDeathListVO" name="<%=MrdConfig.SERACH_DEATH_REGISTRATION_UPLOAD_LIST %>" type="hisglobal.vo.PatientVO" indexId="idx">
					<tr>
						<td width="4%" class="tdfonthead">
							<div align="center">
								<html:radio name="DeathRegistrationUploadFB" property="selectedChild" value='<%=arrDeathListVO.getPatCrNo()+"@"+arrDeathListVO.getPatStatusCode()%>' onclick="getDetail(this)" tabindex="1" ></html:radio>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatCrNo() %>
								</font>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatFirstName() %>
								</font>
							</div>
						</td>
						<td width="12%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatGender() %>/
									<%=arrDeathListVO.getPatAge() %>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getDeathDateTime() %>
								</font>
							</div>
						</td>
						<td width="19%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeathListVO.getPatStatus() %>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
		</logic:present>
		
 		
		<his:status/>
		
		

	<html:hidden name="DeathRegistrationUploadFB" property="hmode" />
	<html:hidden name="DeathRegistrationUploadFB" property="selectedChild"/>
	<html:hidden name="DeathRegistrationUploadFB" property="statusCode"/>
	<html:hidden name="DeathRegistrationUploadFB" property="isFromSearch"/>
	
	
</html:form>
</his:TransactionContainer>


</body>
</html>