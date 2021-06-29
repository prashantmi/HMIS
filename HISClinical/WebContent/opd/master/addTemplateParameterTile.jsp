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

<his:css src="/hisglobal/css/color_picker.css"/>


<his:javascript src="/hisglobal/js/colorPicker.js"/>

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

function validateGoGetRows()
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
/*	if(document.getElementsByName('noOfRows')[0].value<=0)
	{
		alert("Enter at Least One Row for Template ...");
		document.getElementsByName('noOfRows')[0].focus();
		return false; 
	}*/
	return true;
}



function validateFinalSave()
{
	//alert("into Validation"+document.getElementsByName('templateName')[0].value);
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
	if(document.getElementsByName('noOfRows')[0].value<=0)
	{
		alert("Enter at Least One Row for Template ...");
		document.getElementsByName('noOfRows')[0].focus();
		return false;
	}
	
	getTemplateParameterValuesList();
	
	if(document.getElementsByName('parameterValuesList')[0].value=="")
	{
		alert("Enter at Least One Parameter for the Template ... ");
		return false;
	}
	if(document.getElementsByName('hmode')[0].value !='MODIFY')
		if(!ValidateDates())
		return false;
	return true;
}

function ValidateDates()
{
	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	//alert(effectiveFrom.value);
	effectiveTo = document.getElementsByName("effectiveTo")[0];
	//alert(effectiveTo.value);
	entryDate = document.getElementsByName("entryDate")[0];
	//alert(entryDate.value);

	if(!isEmpty(document.forms[0].effectiveFrom,"Effective From") )
		return false;
	if(!compareDateCall(entryDate,effectiveFrom,2,"Current Date","Effective From") )
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

<%@ page import ="java.util.*,registration.*,opd.*,hisglobal.presentation.*" %>

	<body>
		<html:form action="/master/AddTemplateParameterACTION.cnt">
			<html:hidden name="TemplateParameterMasterFB" property="hmode"/>

			<his:TransactionContainer>
			<%
				String varStatus="";
			%>

			<his:statusNew>
			<%
				varStatus="New";
			%>
			</his:statusNew>

			<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>

			<logic:equal name="TemplateParameterMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Add Template">
				</his:TitleTag>
			</logic:equal>

			<logic:equal name="TemplateParameterMasterFB" property="hmode" value="GOGETTEMPLATE">
				<his:TitleTag name="Add Template">
				</his:TitleTag>
			</logic:equal>
			
			<logic:equal name="TemplateParameterMasterFB" property="hmode" value="SAVE">
				<his:TitleTag name="Add Template">
				</his:TitleTag>
			</logic:equal>

			<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
			<html:hidden name="TemplateParameterMasterFB" property="templateId"></html:hidden>
				<his:TitleTag name="Modify Template">
				</his:TitleTag>
			</logic:equal>

			<logic:equal name="TemplateParameterMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="View Template">
				</his:TitleTag>
			</logic:equal>

			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
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
								
								<logic:equal name="TemplateParameterMasterFB" property="hmode" value="ADD">
									<html:hidden name="TemplateParameterMasterFB" property="noOfRows" value="5"></html:hidden>
								</logic:equal>

								<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="ADD">
									<html:hidden name="TemplateParameterMasterFB" property="noOfRows"></html:hidden>
								</logic:notEqual>
								
								<logic:equal name="TemplateParameterMasterFB" property="noOfRows" value="0">
									<html:text name="TemplateParameterMasterFB" property="templateName" maxlength="50">
									</html:text>
								</logic:equal>
								<logic:notEqual name="TemplateParameterMasterFB" property="noOfRows" value="0">
									<html:hidden name="TemplateParameterMasterFB" property="templateName"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="templateName"/></b>
									</font>
								</logic:notEqual>
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
								<logic:equal name="TemplateParameterMasterFB" property="noOfRows" value="0">
									<html:select name="TemplateParameterMasterFB" property="templateType">
										<html:option value="0">Select Value</html:option>
										<html:option value="1">Normal</html:option>
										<html:option value="2">Matrix</html:option>
										<html:option value="3">Consent</html:option>
									</html:select>
								</logic:equal>
								<logic:notEqual name="TemplateParameterMasterFB" property="noOfRows" value="0">
									<html:hidden name="TemplateParameterMasterFB" property="templateType"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<logic:equal name="TemplateParameterMasterFB" property="templateType" value="1">&nbsp;Normal</logic:equal>
											<logic:equal name="TemplateParameterMasterFB" property="templateType" value="2">&nbsp;Matrix</logic:equal>
											<logic:equal name="TemplateParameterMasterFB" property="templateType" value="3">&nbsp;Consent</logic:equal>
										</b>
									</font>
								</logic:notEqual>
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
								<logic:equal name="TemplateParameterMasterFB" property="noOfRows" value="0">
	   								<his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
	   							</logic:equal>
								<logic:notEqual name="TemplateParameterMasterFB" property="noOfRows" value="0">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveFrom" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveFrom"/></b>
									</font>
								</logic:notEqual>
	   						</div>
	   					</td>
	   					<td width="25%" class="tdfonthead">
	   						<div align="right">
	   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	   								<bean:message key="effectiveTo"/>
	   							</font>
	   						</div>
	   					</td>
	   					
	   					<bean:define  name="TemplateParameterMasterFB" property="effectiveTo"  id="effTo" type="java.lang.String"/>
	   					<%	if(effTo==null||effTo.equalsIgnoreCase(""))	{	effTo = sysDate;	}	%>
	   					
	   					<td width="25%" class="tdfont">
	   						<div align="left">
								<logic:equal name="TemplateParameterMasterFB" property="noOfRows" value="0">
		   							<his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y"  />
	   							</logic:equal>
								<logic:notEqual name="TemplateParameterMasterFB" property="noOfRows" value="0">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveTo" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveTo"/></b>
									</font>
								</logic:notEqual>
	   						</div>
	   					</td>
	   				</tr>
					<tr>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>

			<his:statusInProcessWithJsp>
			<%
				varStatus="InProcess";
			%>
			<his:SubTitleTag name="Template">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfonthead" colspan="4">	</td>
					</tr>
					<tr>
						<td colspan="4">
							<his:ContentTag>
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td colspan="4">
											<html:hidden name="TemplateParameterMasterFB" property="parameterValuesList"/>
											<div id="htmlModifyTarget">
												<bean:write name="TemplateParameterMasterFB" property="htmlTemplate" filter="false"/>
											</div>
										</td>
									</tr>
								</table>
							</his:ContentTag>
						</td>
					</tr>
					<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="VIEW">
					<tr>
						<td class="tdfont" colspan="4">
							<div align="right">
								<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="addTemplateRow()" onkeypress="if(event.keyCode==13) addTemplateRow()">
								&nbsp;&nbsp;
								<img class="button" src='<his:path src="/hisglobal/images/Mi_Green_Sqr.png"/>' style='cursor:pointer' onclick ="minusTemplateRow()" onkeypress="if(event.keyCode==13) minusTemplateRow()">
								&nbsp;&nbsp;
							</div>
						</td>
					</tr>
					</logic:notEqual>
					<tr>
						<td colspan="4">
							<div id="paraSetter">
							</div>
							<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="VIEW">
							<div id="divParaList" style="display: none;position: absolute;">
								<select name="paraList" id="paraList" multiple="multiple" size="4">
									<logic:iterate name="<%=OpdConfig.EssentialBO_LIST_ALL_PARAMETERS%>" id="list">
										<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
									</logic:iterate>
								</select>
							</div>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" colspan="4"></td>
					</tr>
				</table>
			</his:ContentTag>
			</his:statusInProcessWithJsp>

			<html:hidden name="TemplateParameterMasterFB" property="entryDate" value="<%=sysDate%>" />
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<%if(varStatus.equals("InProcess")){%>
						<logic:equal name="TemplateParameterMasterFB" property="hmode" value="GOGETTEMPLATE">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) SubmitThisForm(validateFinalSave(),'SAVE')" onclick="SubmitThisForm(validateFinalSave(),'SAVE')">
						</logic:equal>
						<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) SubmitThisForm(validateFinalSave(),'MODIFYSAVE')" onclick="SubmitThisForm(validateFinalSave(),'MODIFYSAVE')">
						</logic:equal>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="SubmitThisForm(true,'LIST')" onkeypress="if(event.keyCode==13) SubmitThisForm(true,'LIST')">
						<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="VIEW">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="SubmitThisForm(true,'ADD')" onkeypress="if(event.keyCode==13) SubmitThisForm(true,'ADD')">
						</logic:notEqual>
					<%} else{ %>
						<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) SubmitThisForm(validateGoGetRows(),'GOGETTEMPLATE');" onclick="SubmitThisForm(validateGoGetRows(),'GOGETTEMPLATE');">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="SubmitThisForm(true,'LIST')" onkeypress="if(event.keyCode==13) SubmitThisForm(true,'LIST')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="SubmitThisForm(true,'ADD')" onkeypress="if(event.keyCode==13) SubmitThisForm(true,'ADD')">
					<%} %>
				</span>
			</his:ButtonToolBarTag>	


			</his:TransactionContainer>
		</html:form>
 		<center><b><his:status/></b></center>
 	</body>
</html>