<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@page import="hisglobal.hisconfig.Config"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>

<his:javascript src="/opd/js/templateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>

function SubmitThisForm(flag,mode)
{
	if(flag)
	{
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
	}
}

function validateFinalSave()
{
	if(document.getElementsByName('templateName')[0].value=="")
	{
		alert("Enter the Template Name ...");
		document.getElementsByName('templateName')[0].focus();
		return false;
	}
	if(document.getElementsByName('templateType')[0].value=="0")
	{
		alert("Select Template Type ...");
		document.getElementsByName('templateType')[0].focus();
		return false;
	}
	if(!ValidateDates())
		return false;
	setTempModifyMode();
	return true;
}

function setTempModifyMode()
{
	var chk=document.getElementById('chkModeModify');
	//alert(chk);
	if(chk)
	{
		//alert(chk.checked);
		//alert("1- "+document.getElementsByName('modeTempModify')[0].value);
		if(chk.checked)
			document.getElementsByName('modeTempModify')[0].value="1";
		else
			document.getElementsByName('modeTempModify')[0].value="0";
		//alert("2- "+document.getElementsByName('modeTempModify')[0].value);
	}
	//alert("3- "+document.getElementsByName('modeTempModify')[0].value);
	
}

function ValidateDates()
{
	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	//alert(effectiveFrom.value);
	effectiveTo = document.getElementsByName("effectiveTo")[0];
	//alert(effectiveTo.value);
	entryDate = document.getElementsByName("entryDate")[0];
	//alert(entryDate.value);
	modeModify=document.getElementsByName("transactionMode")[0].value;

	if(!isEmpty(document.forms[0].effectiveFrom,"Effective From") )
		return false;
	if(modeModify!="MISTAKE" && !compareDateCall(entryDate,effectiveFrom,2,"Current Date","Effective From") )
		return false;
	if(document.getElementsByName('effectiveTo')[0].value!="")
		if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
			return false;
	return true;
}

function compareDateCall(d1,d2,mode,l1,l2) 
{
	var valid=true;
	if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode))
			valid = true;
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
	return valid;
}

</script>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>

	<body>
		<html:form action="/master/AddTemplateParameterACTION.cnt">
			<html:hidden name="TemplateParameterMasterFB" property="hmode"/>

			<his:TransactionContainer>
			<%
				boolean varIsNewStatus=false;
				String varStatus="";
			%>

			<his:statusNew>
			<%
				varIsNewStatus=true;
				varStatus="New";
			%>
			</his:statusNew>

			<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>

			<his:TitleTag name="Modify Template">
			</his:TitleTag>

			<%Date sysDateObject=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT); %>
			
			<bean:define name="TemplateParameterMasterFB" property="effectiveTo" id="effTo" type="java.lang.String" />
			<%	if( effTo == null  ||effTo.equalsIgnoreCase(""))	effTo = "";	%>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<%
					Date effToDate=new Date();
					if(effTo!="")
						effToDate=new Date(effTo);
					if(effToDate.compareTo(sysDateObject)>=0 || effTo.equals(""))
					{
				%>
					<tr>
						<td class="tdfonthead" colspan="4" width="100%">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="updateMode" /></b>
							</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="correction" />
							</font>
							<html:radio name="TemplateParameterMasterFB" property="choice" tabindex="1" value="<%=RegistrationConfig.CHOICE_MISTAKE%>" onclick="SubmitThisForm(true,'MISTAKE');" />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="modification" />
							</font>
							<html:radio name="TemplateParameterMasterFB" property="choice" tabindex="1" value="<%=RegistrationConfig.CHOICE_ADDITION%>" onclick="SubmitThisForm(true,'ADDITION');" />
						</td>
					</tr>
				<%}else{%>
					<html:hidden name="TemplateParameterMasterFB" property="choice" value="2"/>
				<%} %>
					<tr>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="25%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tmplname"/>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<div align="left">
								<logic:equal name="TemplateParameterMasterFB" property="transactionMode" value="MISTAKE">
									<html:text name="TemplateParameterMasterFB" property="templateName"></html:text>
						 		</logic:equal>
								<logic:equal name="TemplateParameterMasterFB" property="transactionMode" value="ADDITION">
									<html:hidden name="TemplateParameterMasterFB" property="templateName" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="templateName"/></b>
									</font>
						 		</logic:equal>
							</div>
						</td>
						<td width="25%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tmpl"/> <bean:message key="type"/>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<div align="left">								
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<logic:equal name="TemplateParameterMasterFB" property="templateType" value="1">&nbsp;Normal</logic:equal>
										<logic:equal name="TemplateParameterMasterFB" property="templateType" value="2">&nbsp;Matrix</logic:equal>
										<logic:equal name="TemplateParameterMasterFB" property="templateType" value="3">&nbsp;Consent</logic:equal>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
				   		<td width="25%" class="tdfonthead">
		           			<div align="right">	           
		              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              				<bean:message key="effectiveFrom"/>
		              			</font>
		            		</div>
						</td>
						
						<bean:define  name="TemplateParameterMasterFB" property="effectiveFrom"  id="effFrom" type="java.lang.String"/>
	   					<%	if(effFrom==null||effFrom.equalsIgnoreCase(""))	{	effFrom =sysDate;	}	%>

	   					<td width="25%" class="tdfont">
	   						<div align="left">
								<logic:equal name="TemplateParameterMasterFB" property="transactionMode" value="MISTAKE">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveFrom" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveFrom"/></b>
									</font>
						 		</logic:equal>
								<logic:equal name="TemplateParameterMasterFB" property="transactionMode" value="ADDITION">
	   								<his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
						 		</logic:equal>
	   						</div>
	   					</td>
	   					<td width="25%" class="tdfonthead">
	   						<div align="right">
	   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	   								<bean:message key="effectiveTo"/>
	   							</font>
	   						</div>
	   					</td>
	   					<td width="25%" class="tdfont">
	   						<div align="left">
							<his:date name='effectiveTo' dateFormate="%d-%b-%Y" value="<%=effTo%>" />
	   						</div>
	   					</td>
	   				</tr>
					<tr>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
					
					<logic:equal name="TemplateParameterMasterFB" property="transactionMode" value="MISTAKE">
					<tr>
						<td class="tdfont" colspan="4" width="100%">
							<html:hidden name="TemplateParameterMasterFB" property="modeTempModify" />
							<input type="checkbox" id="chkModeModify" />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 &nbsp; Want to modify Template
							</font>
						</td>
					</tr>
					</logic:equal>
				</table>
			</his:ContentTag>
			
			<html:hidden name="TemplateParameterMasterFB" property="templateId" />
			<html:hidden name="TemplateParameterMasterFB" property="tempSerialNo" />
			<html:hidden name="TemplateParameterMasterFB" property="templateType"/>
			<html:hidden name="TemplateParameterMasterFB" property="transactionMode" />
			<html:hidden name="TemplateParameterMasterFB" property="entryDate" value="<%=sysDate%>" />
			
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) SubmitThisForm(validateFinalSave(),'MODIFYTEMPLATE')" onclick="SubmitThisForm(validateFinalSave(),'MODIFYTEMPLATE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="SubmitThisForm(true,'LIST')" onkeypress="if(event.keyCode==13) SubmitThisForm(true,'LIST')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="SubmitThisForm(true,'ADD')" onkeypress="if(event.keyCode==13) SubmitThisForm(true,'ADD')">
				</span>
			</his:ButtonToolBarTag>	


			</his:TransactionContainer>
		</html:form>
 		<center><b><his:status/></b></center>
 	</body>
</html>