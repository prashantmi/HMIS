
<%--
Author: Asha
Date: Dec-06-2010
Description: This page is used for mapping ICD Diesease with Hospital Disease or Chronic Disease

  --%>
 <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 
<%@page import="opd.OpdConfig"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>

function mappedDiseasePopUp(e)
{
	var selectedDisease=document.getElementsByName("selectedDisease")[0].value;
	var child = window.open(createFHashAjaxQuery('/HISClinical/opd/master/opdIcdMappingMaster.cnt?hmode=MAPPEDDISEASE'),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus();
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}
function moveRightSingle(listNo)
{
	var source;
	var target;
	if(listNo=="1")
	{
		source  = document.forms[0].diseaseCode;
		target = document.forms[0].selectedDisease;	
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
		source  = document.forms[0].diseaseCode;
		target = document.forms[0].selectedDisease;	
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
		target  = document.forms[0].diseaseCode;
		source = document.forms[0].selectedDisease;	
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
		target  = document.forms[0].diseaseCode;
		source = document.forms[0].selectedDisease;	
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

function moveToSelected()
{
	// Select All para in Selected
	if(document.forms[0].diseaseCode)
		for(var i=0;i<document.forms[0].selectedDisease.length;i++)
			document.forms[0].selectedDisease.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].selectedDisease)
	{	
		for(var i=0;i<document.forms[0].diseaseCode.options.length;i++)
			document.forms[0].diseaseCode.options[i].selected=false;
	}
}



function getDisease(obj){

	if(obj.value=="-1")
	{
		 //  document.getElementsByName('diseaseCode')[0].length=0;
		
	}
	else{
			document.getElementsByName("hmode")[0].value="GETICDDISEASE"
			moveToSelected();
		 // document.getElementsByName('diseaseCode')[0].length=0;
			document.forms[0].submit()	
	}	
}
function doEmtyCombo(cbo)
{
	cbo.innerHTML = "";
	var opt = document.createElement("option");
	opt.text = "Select Value";
	opt.value = "-1";
	cbo.appendChild(opt);
}

function getSubGroup(obj){

	if(obj.value=="-1")
	{
		doEmtyCombo(document.getElementsByName("icdSubgroupCode")[0]);
		//  document.getElementsByName('diseaseCode')[0].length=0;
		
	}
	else{
			document.getElementsByName("hmode")[0].value="GETSUBGROUP"
			moveToSelected();
		//  document.getElementsByName('diseaseCode')[0].length=0;
			document.forms[0].submit()	
	}	
}
function validateAdd()
{	
	if(document.getElementsByName("mappingType")[0].value=="2")
	{
	if(document.getElementsByName("chronicDisease")[0].value=="-1")
	{
		alert("Choose at Least One Chronic Disease");
		document.forms[0].chronicDisease.focus();
		return false;
	}}
	if(document.getElementsByName("mappingType")[0].value=="1"){
	if(document.getElementsByName("hospitalDisease")[0].value=="-1")
	{
		alert("Choose at Least One Hospital Disease");
		document.forms[0].hospitalDisease.focus();
		return false;
	}}
	if(document.getElementsByName("icdGroupCode")[0].value=="-1")
	{
		alert("Choose at Least One Group");
		document.forms[0].icdGroupCode.focus();
		return false;
	}
	if(document.getElementsByName("icdSubgroupCode")[0].value=="-1")
	{
		alert("Choose at Least One SubGroup");
		document.forms[0].icdSubgroupCode.focus();
		return false;
	}
	if(document.forms[0].selectedDisease.options.length==0)
	{
		alert("Choose at Least One Disease");
		document.forms[0].selectedDisease.focus();
		return false;
	}
	return true;
	
	
}
function submitForm(mode)
{
  	  moveToSelected();
     document.getElementsByName("hmode")[0].value=mode;
	 document.forms[0].submit();
	 
}
function clearForm(){

	if(document.getElementsByName("mappingType")[0].value=="2")
	{
	document.getElementsByName('chronicDisease')[0].value="-1";
	}
	if(document.getElementsByName("mappingType")[0].value=="1")
	{
	document.getElementsByName('hospitalDisease')[0].value="-1";
	}
	document.getElementsByName('icdGroupCode')[0].value="-1";
	document.getElementsByName('icdSubgroupCode')[0].value="-1";
	document.getElementsByName('diseaseCode')[0].length=0;
	document.getElementsByName('selectedDisease')[0].length=0;
}
</script>

<body>
<html:form action="/master/opdIcdMappingMaster">
<html:hidden name="OpdIcdMappingMasterFB" property="hmode"/>
	<his:TransactionContainer>
		<his:ContentTag>
				<his:TitleTag name="ICD Mapping>>Add ">
				</his:TitleTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="33%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message key="mappingType"/></b>&nbsp;
								</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead">
							<div align="left">
									<b>&nbsp;<bean:write name="OpdIcdMappingMasterFB" property="mappingTypeDesc"/></b>
								<html:hidden name="OpdIcdMappingMasterFB" property="mappingType"/>
								<html:hidden name="OpdIcdMappingMasterFB" property="mappingTypeDesc"/>
							</div>
						</td>
						<td width="33%"  class="tdfonthead">
						</td>
						<td width="4%"  class="tdfonthead">
						</td>
						</tr>
						</table>
				<logic:equal value="<%=OpdConfig.CHRONIC_DISEASE %>"  name="OpdIcdMappingMasterFB" property="mappingType">
		    	 <table width="100%" border="0" cellspacing="1" cellpadding="0" >
		  		 	<tr>
			 			 <td width="33%" class="tdfonthead">
			     			<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="chronicDisease"/></b>&nbsp;
							</font>
							
				 			 </div>
			  			</td>
			 	 		<td width="30%" class="tdfonthead">
							<div align="left">
								<html:select name="OpdIcdMappingMasterFB" property="chronicDisease"  style="width:200px;" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHRONIC_DISEASE %>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHRONIC_DISEASE %>" property="value" labelProperty="label" />
									</logic:present>
									
								</html:select>
							</div>
						</td>
						<td width="33%"  class="tdfonthead">
						</td>
						<td width="4%"  class="tdfonthead">
						</td>
		  	 		</tr>
				</table>
				</logic:equal>
			<logic:equal value="<%=OpdConfig.HOSPITAL_DISEASE %>"  name="OpdIcdMappingMasterFB" property="mappingType">
		    	 <table width="100%" border="0" cellspacing="1" cellpadding="0" >
		  		 	<tr>
			 			 <td width="33%" class="tdfonthead">
			     			<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="hospitalDisease"/></b>&nbsp;
							</font>
				 			 </div>
			  			</td>
			 	 		<td width="30%" class="tdfonthead">
							<div align="left">
								<html:select name="OpdIcdMappingMasterFB" property="hospitalDisease"  style="width:200px;" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE %>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE%>" property="value" labelProperty="label" />
									</logic:present>
									
								</html:select>
							</div>
						</td>
						<td width="33%"  class="tdfonthead">
						</td>
						<td width="4%"  class="tdfonthead">
						</td>
		  	 		</tr>
				</table>
			</logic:equal>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="33%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="group"/></b>&nbsp;
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="left">
								<html:select name="OpdIcdMappingMasterFB" property="icdGroupCode"  style="width:200px;" onchange="getSubGroup(this)">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP %>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="33%" class="tdfonthead"></td>
						<td width="4%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="33%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="subGroup"/></b>&nbsp;
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="left">
								<html:select name="OpdIcdMappingMasterFB" property="icdSubgroupCode" style="width:200px;" onchange="getDisease(this)" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_SUBGROUP_GROUPWISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_SUBGROUP_GROUPWISE%>" property="icdSubgroupCode" labelProperty="icdSubgroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="33%" class="tdfonthead"></td>
						<td width="4%"  class="tdfonthead">
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="33%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="disease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
						
						<td width="33%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="mappedDisease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="4%"  class="tdfonthead"></td>
					</tr>
						
					<tr>
						<td width="33%"  class="tdfont">
							<div align="center"  >
								<html:select name="OpdIcdMappingMasterFB" property="diseaseCode"  multiple="true" size="6"  >
										<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE %>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE%>" property="diseaseCode" labelProperty="disease" />
										</logic:present>
								</html:select>
							</div>
						</td>
						
						<td width="30%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="33%"  class="tdfont">
							<div align="center">
								<html:select name="OpdIcdMappingMasterFB" property="selectedDisease" multiple="true" size="6">
									</html:select>
							</div>
						</td>
						<td width="4%"  class="tdfont">
						<div align="center">
						<logic:present name="<%=OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE %>" >
								<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer" onclick="mappedDiseasePopUp(event);" >
						</logic:present>
						</div></td>
					</tr>
					<tr>
						<td width="33%" class="tdfonthead"></td>
						<td width="30%" class="tdfonthead"></td>
						<td width="33%" class="tdfonthead"></td>
						<td width="4%" class="tdfonthead"></td>
					</tr>
					</table>
				
		</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd() && submitForm('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="OpdIcdMappingMasterFB" property="hmode"/>
				<center><b><his:status/></b></center>
			</his:TransactionContainer>
		<div align="center">
			
		</div>
		
		</html:form>
	</body>
</html>