<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script><!--
		
function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].templateList;
		target = document.forms[0].selectedTemplate;	
	}
	
	if(listNo=="2")
	{
		source  = document.forms[0].serviceIdLst;
		target = document.forms[0].selServiceIdLst;	
	}
	
	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].templateList;
		target = document.forms[0].selectedTemplate;	
	}
	
	if(listNo=="2")
	{
		source  = document.forms[0].serviceIdLst;
		target = document.forms[0].selServiceIdLst;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].templateList;
		source = document.forms[0].selectedTemplate;	
	}
	
	if(listNo=="2")
	{
		source  = document.forms[0].selServiceIdLst;	
		target = document.forms[0].serviceIdLst;
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].templateList;
		source = document.forms[0].selectedTemplate;	
	}
	
	if(listNo=="2")
	{
		source  = document.forms[0].selServiceIdLst;	
		target = document.forms[0].serviceIdLst;
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}

function MoveToSelected()
{
	
	// Unselect Remaining Services
	if(document.forms[0].serviceIdLst)
	{
		for(var i=0;i<document.forms[0].serviceIdLst.options.length;i++)
		{
			document.forms[0].serviceIdLst.options[i].selected=false;
		}
	}
	// Select All Services in Selected
	if(document.forms[0].selServiceIdLst)
	{	
		for(var i=0;i<document.forms[0].selServiceIdLst.options.length;i++)
		{
			document.forms[0].selServiceIdLst.options[i].selected=true;
		}
	}
	// Unselect Remaining Template
	if(document.forms[0].templateList)
	{
		for(var i=0;i<document.forms[0].templateList.options.length;i++)
			document.forms[0].templateList.options[i].selected=false;
	}
	

	// Select All Template in Selected
	if(document.forms[0].selectedTemplate)
	{	
		for(var i=0;i<document.forms[0].selectedTemplate.options.length;i++)
			document.forms[0].selectedTemplate.options[i].selected=true;
	}
	
}

function MoveToSelectedServices()
{
	// Unselect Remaining service
	if(document.forms[0].serviceIdLst)
	{
		for(var i=0;i<document.forms[0].serviceIdLst.options.length;i++)
			document.forms[0].serviceIdLst.options[i].selected=false;
			//alert(document.forms[0].serviceIdLst.value);
	}
	

	// Select All service in Selected
	if(document.forms[0].selServiceIdLst)
	{	
		for(var i=0;i<document.forms[0].selServiceIdLst.options.length;i++)
			document.forms[0].selServiceIdLst.options[i].selected=true;
	}
	
}
		
	function ValidateForm(mode)
	{
		//alert("ddddddddddddddddddddddd");
		MoveToSelected();
		MoveToSelectedServices();
		if(document.getElementsByName('serviceTypeId')[0].value=="")
		{
			alert("Please Select Service Type");
			document.getElementsByName('serviceTypeId')[0].focus();
			return false;
		}
		else if(document.getElementsByName('serviceId')[0].value==-1)
		{
			alert("Please Select Services");
			document.getElementsByName('serviceId')[0].focus();
			return false;
		}
		if(document.getElementsByName('selServiceIdLst')[0])
		if(document.getElementsByName('selServiceIdLst')[0].value=="")
		{
			alert("Please Select Service");
			document.getElementsByName('serviceIdLst')[0].focus();
			return false;
		}
		if(document.getElementsByName('selectedTemplate')[0].value=="")
		{
			alert("Please Select Template");
			document.getElementsByName('templateList')[0].focus();
			return false;
		}
		
		if(document.getElementsByName('serviceTypeId')[0].value!=-1 && document.getElementsByName('serviceId')[0].value!=-1 && document.getElementsByName('selectedTemplate')[0].value!="")
		{
			submitForm(mode);
		}
	}
	
	/*function validateForm1(mode)
	{
		MoveToSelected();
		if(document.getElementsByName('selServiceIdLst')[0].value=="")
		{
			alert("You Can't Modify This Record");
			return false;
		}
		else
		{
			submitForm(mode);
		}
		if(document.getElementsByName('selectedTemplate')[0].value=="")
		{
			alert("You Can't Modify This Record");
			return false;
		}
		else
		{
			submitForm(mode);
		}
		
			
	}*/
	function clearForm()
 	{
   		document.getElementsByName('serviceTypeId')[0].value="";
   		document.getElementsByName('serviceId')[0].value="-1";
   	}
	
	function submitForm(mode)
		{	
			
			MoveToSelected();
			MoveToSelectedServices();
			document.getElementsByName('hmode')[0].value=mode
			document.forms[0].submit();
		}
		
		
		
	--></script>
<body>
<html:form action="/master/AddConsentMappingMaster">
	<his:TransactionContainer>
		<%@ page import="opd.*"%>
		<his:TitleTag name=" Service Consent/Guideline Mapping Master">
		</his:TitleTag>
		<logic:notEqual value="MODIFY" property="hmode" name="consentMappingMasterFB">
			<logic:notEqual value="VIEW" property="hmode" name="consentMappingMasterFB">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfonthead" width="30%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="templateType" /></b>
								</font>
							</div>
						</td>
						<td class="tdfont" width="35%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="consentMappingMasterFB" property="choice" tabindex="1" 
										value="<%=OpdConfig.TEMPLATE_MODE_CONSENT%>" onclick="submitForm('GETDTL')" />
									&nbsp;
									<b><bean:message key="consent" /></b> 
								</font>
							</div>
						</td>
						<td width="35%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="consentMappingMasterFB" property="choice" tabindex="1" 
										value="<%=OpdConfig.TEMPLATE_MODE_GUIDELINE%>" onclick="submitForm('GETDTL')" />
										&nbsp;
									<b><bean:message key="guidelines" /></b> 
								</font>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
							key="serviceType" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont"><html:select name="consentMappingMasterFB" tabindex="1" property="serviceTypeId"
							styleClass="registrationCmb" onchange="submitForm('GETDTL')">
							<html:option value="">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_SERVICE_TYPE_LIST%>">
								<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_SERVICE_TYPE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></td>
					</tr>
					<tr style="display: none;">
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
							key="services" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont"><html:select name="consentMappingMasterFB" tabindex="1" property="serviceId"
							styleClass="registrationCmb">
							<html:option value="11">Select Value</html:option>
							<logic:notEmpty name="consentMappingMasterFB" property="serviceTypeId">
								<logic:present name="<%=OpdConfig.OPD_REMAINING_TEMPLATE_LIST_BY_TEMPLATETYPE%>">
									<html:options collection="<%=OpdConfig.OPD_REMAINING_TEMPLATE_LIST_BY_TEMPLATETYPE%>" property="value"
										labelProperty="label" />
								</logic:present>
							</logic:notEmpty>
						</html:select></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="100%" class="tdfonthead">
						<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
							key="services" /></b> </font></div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="35%" class="tdfonthead">
						<div align="center"><html:select name="consentMappingMasterFB" property="serviceIdLst" multiple="true" size="6"
							style="width: 200px;">
							<logic:notEmpty name="consentMappingMasterFB" property="serviceTypeId">
								<logic:present name="<%=OpdConfig.OPD_REMAINING_TEMPLATE_LIST_BY_TEMPLATETYPE%>">
									<html:options collection="<%=OpdConfig.OPD_REMAINING_TEMPLATE_LIST_BY_TEMPLATETYPE%>" property="value"
										labelProperty="label" />
								</logic:present>
							</logic:notEmpty>
						</html:select></div>
						</td>
						<td width="15%" class="tdfont">
						<div align="center"><img src="/../HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveRightSingle("2");' />
						<img src="/../HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveRightAll("2");' /> <br>
						<br>
						<img src="/../HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveLeftSingle("2");' /> <img
							src="/../HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveLeftAll("2");' /></div>
						</td>
						<td width="35%" class="tdfonthead">
						<div align="center"><html:select name="consentMappingMasterFB" property="selServiceIdLst" multiple="true"
							size="6" style="width: 200px;">
						</html:select></div>
						</td>
					</tr>
				</table>
			</logic:notEqual>
		</logic:notEqual>
		<logic:equal value="MODIFY" property="hmode" name="consentMappingMasterFB">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="templateType" /></b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write property="templateDesc" name="consentMappingMasterFB" /></b>
								<html:hidden property="templateDesc" name="consentMappingMasterFB" />
								<html:hidden property="choice" name="consentMappingMasterFB" />
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="serviceType" /></b> </font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write property="serviceTypeDesc" name="consentMappingMasterFB" /></b>
								<html:hidden property="serviceTypeDesc" name="consentMappingMasterFB" />
								<html:hidden property="serviceTypeId" name="consentMappingMasterFB" />
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="services" /></b> 
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write property="serviceDesc" name="consentMappingMasterFB" /></b>
								<html:hidden property="serviceDesc" name="consentMappingMasterFB" />
							</font>
						</div>
						<div style="display: none;">
							<html:select name="consentMappingMasterFB" property="selServiceIdLst" multiple="true" size="6" style="width: 200px;">
								<html:option value='<bean:write property="selServiceIdLst" name="consentMappingMasterFB" />'><bean:write property="serviceDesc" name="consentMappingMasterFB" /></html:option>
							</html:select>
						</div>
					</td>
				</tr>
			</table>
		</logic:equal>
		<logic:equal value="----MODIFY" property="hmode" name="consentMappingMasterFB">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%" class="tdfonthead">
					<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="services" /></b> </font></div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="35%" class="tdfonthead">
					<div align="center"><html:select name="consentMappingMasterFB" property="serviceIdLst" multiple="true" size="6"
						style="width: 200px;">
						<logic:notEmpty name="consentMappingMasterFB" property="serviceTypeId">
							<logic:present name="<%=OpdConfig.SERVICE_LIST%>">
								<html:options collection="<%=OpdConfig.SERVICE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</logic:notEmpty>
					</html:select></div>
					</td>
					<td width="15%" class="tdfont">
					<div align="center"><img src="/../HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveRightSingle("2");' />
					<img src="/../HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveRightAll("2");' /> <br>
					<br>
					<img src="/../HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveLeftSingle("2");' /> <img
						src="/../HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveLeftAll("2");' /></div>
					</td>
					<td width="35%" class="tdfonthead">
					<div align="center"><html:select name="consentMappingMasterFB" property="selServiceIdLst" multiple="true" size="6"
						style="width: 200px;">
						<logic:equal value="MODIFY" property="hmode" name="consentMappingMasterFB">
							<logic:present name="<%=OpdConfig.SELECTED_SERVICE_LIST%>">
								<html:options collection="<%=OpdConfig.SELECTED_SERVICE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</logic:equal>
					</html:select></div>
					</td>
				</tr>
			</table>
		</logic:equal>
		<logic:equal value="VIEW" property="hmode" name="consentMappingMasterFB">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="templateType" /></b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write property="templateDesc" name="consentMappingMasterFB" /></b>
								<html:hidden property="templateDesc" name="consentMappingMasterFB" />
								<html:hidden property="choice" name="consentMappingMasterFB" />
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="serviceType" /></b> </font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write property="serviceTypeDesc" name="consentMappingMasterFB" /></b>
								<html:hidden property="serviceTypeDesc" name="consentMappingMasterFB" />
								<html:hidden property="serviceTypeId" name="consentMappingMasterFB" />
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="services" /></b> 
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<b><bean:write property="serviceDesc" name="consentMappingMasterFB" /></b>
								<html:hidden property="serviceDesc" name="consentMappingMasterFB" />
							</font>
						</div>
						<div style="display: none;">
							<html:select name="consentMappingMasterFB" property="selServiceIdLst" multiple="true" size="6" style="width: 200px;">
								<html:option value='<bean:write property="selServiceIdLst" name="consentMappingMasterFB" />'><bean:write property="serviceDesc" name="consentMappingMasterFB" /></html:option>
							</html:select>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="tmpl" /></b> </font></div>
					</td>
					<td width="50%" class="tdfont">
					<div align="left"><html:select name="consentMappingMasterFB" property="selectedTemplate" multiple="true" size="6">
						<logic:present name="<%=OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID%>">
							<html:options collection="<%=OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID%>" property="value"
								labelProperty="label" />
						</logic:present>
					</html:select></div>
					</td>
				</tr>
			</table>
		</logic:equal>
		<logic:notEqual value="VIEW" name="consentMappingMasterFB" property="hmode">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%" class="tdfonthead">
					<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="tmpl" /></b> </font></div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="35%" class="tdfonthead">
					<div align="center"><html:select name="consentMappingMasterFB" property="templateList" multiple="true" size="6"
						style="width: 200px;">
						<logic:equal value="GETDTL" property="hmode" name="consentMappingMasterFB">
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_TEMPLATE_LIST_BY_TEMPLATEIDTYPE%>">
								<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_TEMPLATE_LIST_BY_TEMPLATEIDTYPE%>" property="value"
									labelProperty="label" />
							</logic:present>
						</logic:equal>
						<logic:equal value="MODIFY" property="hmode" name="consentMappingMasterFB">
							<logic:present name="<%=OpdConfig.OPD_REMAINING_TEMPLATE_LIST%>">
								<html:options collection="<%=OpdConfig.OPD_REMAINING_TEMPLATE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</logic:equal>
					</html:select></div>
					</td>
					<td width="15%" class="tdfont">
					<div align="center"><img src="/HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveRightSingle("1");' />
					<img src="/../HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveRightAll("1");' /> <br>
					<br>
					<img src="/../HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveLeftSingle("1");' /> <img
						src="/../HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveLeftAll("1");' /></div>
					</td>
					<td width="35%" class="tdfonthead">
					<div align="center"><html:select name="consentMappingMasterFB" property="selectedTemplate" multiple="true"
						size="6" style="width: 200px;">
						<logic:equal value="MODIFY" property="hmode" name="consentMappingMasterFB">
							<logic:present name="<%=OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID%>">
								<html:options collection="<%=OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID%>" property="value"
									labelProperty="label" />
							</logic:present>
						</logic:equal>
						<logic:equal value="VIEW" property="hmode" name="consentMappingMasterFB">
							<logic:present name="<%=OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID%>">
								<html:options collection="<%=OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID%>" property="value"
									labelProperty="label" />
							</logic:present>
						</logic:equal>
					</html:select></div>
					</td>
				</tr>
			</table>
		</logic:notEqual>
		<his:ButtonToolBarTag>
			<logic:notEqual value="MODIFY" property="hmode" name="consentMappingMasterFB">
				<logic:notEqual value="VIEW" property="hmode" name="consentMappingMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons//btn-sv.png"/>' style="cursor: pointer" tabindex="1"
						onclick="ValidateForm('SAVE')" onkeypress="if(event.keyCode==13) ValidateForm('SAVE')">
				</logic:notEqual>
			</logic:notEqual>
			<logic:notEqual value="VIEW" property="hmode" name="consentMappingMasterFB">
				<logic:equal value="MODIFY" property="hmode" name="consentMappingMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons//btn-sv.png"/>'
						style="cursor: pointer" tabindex="1" onclick="ValidateForm('MODIFYSAVE')"
						onkeypress="if(event.keyCode==13) ValidateForm('MODIFYSAVE')">
				</logic:equal>
			</logic:notEqual>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1"
				onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			<logic:notEqual value="VIEW" property="hmode" name="consentMappingMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1"
					onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
			</logic:notEqual>
		</his:ButtonToolBarTag>
		<his:status />
		<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b
			id="boldStatus"></b></font></div>
		<html:hidden property="hmode" name="consentMappingMasterFB" />
		<html:hidden property="serviceId" name="consentMappingMasterFB" />
		
		
		<html:hidden property="chk" name="consentMappingMasterFB" />
		<html:hidden property="templateType" name="consentMappingMasterFB" />
	</his:TransactionContainer>
</html:form>
</body>
</html>
