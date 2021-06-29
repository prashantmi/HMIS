
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
	if(document.getElementsByName("str_patCrNo")[0].value=="" && document.getElementsByName("str_patAdmNo")[0].value=="" && document.getElementsByName("str_firstName")[0].value=="" && document.getElementsByName("str_middleName")[0].value=="" && document.getElementsByName("str_lastName")[0].value=="")
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
	document.getElementsByName("str_patAdmNo")[0].value="";
	document.getElementsByName("str_firstName")[0].value="";
	document.getElementsByName("str_middleName")[0].value="";
	document.getElementsByName("str_lastName")[0].value="";
}


function getPatientDtl()
{
	
	var len=document.getElementsByName("patAdmNoArray").length;
		
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("patAdmNoArray")[i].checked)
			{
				opener.document.getElementsByName("patName")[0].value=document.getElementsByName("patNameArray")[i].value;
				opener.document.getElementsByName("ageGender")[0].value=document.getElementsByName("patGenderAndAgeArray")[i].value;
				opener.document.getElementsByName("patCrNo")[0].value=document.getElementsByName("patCrNoArray")[i].value;
				opener.document.getElementsByName("patAdmNo")[0].value=document.getElementsByName("patAdmNoArray")[i].value;
				opener.document.getElementsByName("admissionDeptUnit")[0].value=document.getElementsByName("patDeptUnitArray")[i].value;
				opener.document.getElementsByName("patAdmDate")[0].value=document.getElementsByName("patAdmDateArray")[i].value;
				opener.document.getElementsByName("episodeCode")[0].value=document.getElementsByName("episodeCodeArray")[i].value;
				opener.document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("episodeVisitNoArray")[i].value;
				opener.document.getElementsByName("dischargeDate")[0].value=document.getElementsByName("dischargeDateArray")[i].value;
			}
		}
		
	closeForm();
}


</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/insuranceClaimReceive">
		
		<his:TitleTag name="Patient Search">
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
				<html:text name="InsuranceClaimReceiveFB"  maxlength="32" size="30" property="str_firstName" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="mname"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="InsuranceClaimReceiveFB"  maxlength="32" size="30" property="str_middleName" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="lname"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="InsuranceClaimReceiveFB"  maxlength="32" size="30" property="str_lastName" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
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
				<html:text name="InsuranceClaimReceiveFB"  maxlength="13" size="30" property="str_patCrNo" styleClass ="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="admissionNo"/></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="InsuranceClaimReceiveFB"  maxlength="11" size="30" property="str_patAdmNo" styleClass ="textbox" tabindex="1" onkeypress="return validateNumeric(event)"/>
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
	 		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="validateSearch('SEARCH');" onkeypress="if(event.keyCode==13) validateSearch('SEARCH');">
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
	 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
 		</his:ButtonToolBarTag>
 		
		<logic:present name="<%=MrdConfig.ALL_PAT_INFO_LIST %>">
		<logic:notEmpty name="<%=MrdConfig.ALL_PAT_INFO_LIST %>">
		
		<his:SubTitleTag name="Patient Search Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td class="tdfonthead" width="5%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="admissionNo"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>	
						</div>	
					</td>
					
					<td class="tdfonthead" width="20%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="gender/age"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="admissionDate"/>
							</font>	
						</div>	
					</td>
					<td class="tdfonthead" width="15%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dischargeDate"/>
							</font>	
						</div>	
					</td>
			</tr>
			<logic:iterate id="insuranceDetailVO" indexId="i" name="<%=MrdConfig.ALL_PAT_INFO_LIST %>" type="hisglobal.vo.InsuranceDetailVO">
			<tr>
				<td class="tdfont" width="5%" >
					 	<div align="center">
					 		<html:radio property="patAdmNoArray" value="<%=insuranceDetailVO.getPatAdmNo() %>" onclick="getPatientDtl()"></html:radio>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="patNameArray" value="<%=insuranceDetailVO.getPatName() %>"/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="patGenderAndAgeArray" value="<%=insuranceDetailVO.getPatGender()+'/'+ insuranceDetailVO.getPatAge()%>"/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="patCrNoArray" value="<%=insuranceDetailVO.getPatCrNo() %>"/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="patDeptUnitArray" value='<%=insuranceDetailVO.getDeptName()+"("+insuranceDetailVO.getUnitName()+")" %>'/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="patAdmDateArray" value="<%=insuranceDetailVO.getAdmDate() %>"/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="episodeCodeArray" value="<%=insuranceDetailVO.getEpisodeCode() %>"/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="episodeVisitNoArray" value="<%=insuranceDetailVO.getEpisodeVisitNo() %>"/>
					 		<html:hidden name="InsuranceClaimReceiveFB" property="dischargeDateArray" value="<%=insuranceDetailVO.getDischargeDate() %>"/>
					 	</div>
				 	</td>
				 <td class="tdfont" width="15%" >
					 	<div align="center">
					 		<bean:write name="insuranceDetailVO" property="patAdmNo"/>
					 	</div>
				 	</td>
				 <td class="tdfont" width="15%" >
					 	<div align="center">
					 		<bean:write name="insuranceDetailVO" property="patCrNo"/>
					 	</div>
				 	</td>	
				 			
				 <td class="tdfont" width="20%" >
					 	<div align="center">
					 		<bean:write name="insuranceDetailVO" property="patName"/>
					 	</div>
				 	</td>
				 <td class="tdfont" width="15%" >
					 	<div align="center">
					 		<bean:write name="insuranceDetailVO" property="patGender"/>/
					 		<bean:write name="insuranceDetailVO" property="patAge"/>
					 	</div>
				 	</td>
				<td class="tdfont" width="15%" >
					 	<div align="center">
					 		<bean:write name="insuranceDetailVO" property="admDate"/>
					 	</div>
				 	</td>
				 <td class="tdfont" width="15%" >
					 	<div align="center">
					 		<bean:write name="insuranceDetailVO" property="dischargeDate"/>
					 	</div>
				 	</td> 					
			</tr>
			</logic:iterate>
		</table>
		</his:ContentTag>
		</logic:notEmpty>
		</logic:present>
		
		
		
		
		<his:status/>
		
		

	<html:hidden name="InsuranceClaimReceiveFB" property="hmode" />
	
	
	
</html:form>
</his:TransactionContainer>


</body>
</html>