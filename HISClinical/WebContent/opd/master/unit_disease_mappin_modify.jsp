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
	var child = window.open(createFHashAjaxQuery('/HISClinical/opd/master/DeptUnitIcdMapping.cnt?hmode=MAPPEDDISEASE'),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
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

function MoveToSelected()
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
			document.getElementsByName("hmode")[0].value="GETMODDISEASE"
			MoveToSelected();
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
			document.getElementsByName("hmode")[0].value="GETMODSUBGROUP"
		//  document.getElementsByName('diseaseCode')[0].length=0;
			document.forms[0].submit()	
	}	
}

function validateModify()
{	if(document.getElementsByName("icdGroupCode")[0].value=="-1")
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
function modifyClear()
{
	moveLeftAll("1");
}
function submitForm(mode)
{
  	  MoveToSelected();
     document.getElementsByName("hmode")[0].value=mode;
	 document.forms[0].submit();
	 
}
function clearForm(){

	document.getElementsByName('icdGroupCode')[0].value="-1";
	document.getElementsByName('icdSubgroupCode')[0].value="-1";
	document.getElementsByName('diseaseCode')[0].length=0;
	document.getElementsByName('selectedDisease')[0].length=0;
}
</script>

<body>
<html:form action="/master/DeptUnitIcdMapping">
<html:hidden name="DeptUnitIcdMappingFB" property="hmode"/>
<html:hidden name="DeptUnitIcdMappingFB" property="selectedUnit"/>
	<his:TransactionContainer>
		<his:ContentTag>
			<his:TitleTag name="Department Unit Icd Mapping>>Modify ">
				</his:TitleTag>
				<logic:notEqual value="VIEW" name="DeptUnitIcdMappingFB" property="hmode">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfont">
							<div align="left">
							<bean:write name="DeptUnitIcdMappingFB" property="deptUnitName"/>
							</div>
						</td>
						<td width="20%"  class="tdfonthead">
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="group"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="icdGroupCode"  style="width:200px;" onchange="getSubGroup(this)">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%" class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="subGroup"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="icdSubgroupCode" style="width:200px;" onchange="getDisease(this)" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" property="icdSubgroupCode" labelProperty="icdSubgroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="disease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="28%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedDisease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="7%"  class="tdfonthead">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer" onclick="mappedDiseasePopUp(event);" >
							<div>
						</td>
					</tr>
					<tr>
						<td width="35%"  class="tdfont">
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="diseaseCode"  multiple="true" size="6"  >
										<logic:present name="<%=OpdConfig.DISEASE_LIST_NOT_MAPPED%>" >
										<html:options collection="<%=OpdConfig.DISEASE_LIST_NOT_MAPPED%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
						</td>
						<td width="28%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfont">
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="selectedDisease" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.MAPPED_DISEASE_LIST%>" >
										<html:options collection="<%=OpdConfig.MAPPED_DISEASE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
							<td width="7%"  class="tdfont">
						</td>
					</tr>
					<tr>
						<td width="35%" class="tdfonthead"></td>
						<td width="28%" class="tdfonthead"></td>
						<td width="35%" class="tdfonthead"></td>
						<td width="7%"  class="tdfonthead"></td>
					</tr>
				</table>
			</logic:notEqual>
	<logic:equal value="VIEW" name="DeptUnitIcdMappingFB" property="hmode">
			<his:TitleTag name="Department Unit Icd Mapping>>">
				</his:TitleTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%"  class="tdfonthead">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="unit"/>
								</font>
								</div>
							</td>
							<td width="40%" class="tdfont">
								<div align="left">
								<bean:write name="DeptUnitIcdMappingFB" property="deptUnitName"/>
								</div>
							</td>
							<td width="20%"  class="tdfonthead">
							</td>
						</tr>
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%"  class="tdfonthead"></td>
						</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="selectedDisease"/></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead"></td>
					</tr>
					<tr>
						<logic:present name="<%=OpdConfig.MAPPED_DISEASE_LIST%>" >
							<logic:iterate id="entryObj" indexId="idx" name="<%=OpdConfig.MAPPED_DISEASE_LIST%>" type="hisglobal.utility.Entry">
							<tr>
								<td width="40%" class="tdfonthead"></td>
								<td width="60%" class="tdfont">
									<div align="left">
											<bean:write name="entryObj" property="label"/>
									</div>
								</td>
							</tr>
							</logic:iterate>
						</logic:present>
					</tr>
				</table>
			</logic:equal>
			
		</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
					<logic:notEqual name="DeptUnitIcdMappingFB" property="hmode" value="VIEW">	
					<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm('MODIFYSAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="modifyClear()" onkeypress="if(event.keyCode==13) modifyClear();">
				</logic:notEqual>
			
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="DeptUnitIcdMappingFB" property="hmode"/>
				<html:hidden name="DeptUnitIcdMappingFB" property="deptUnitName"/>
				<html:hidden name="DeptUnitIcdMappingFB" property="deptUnitCode"/>
				<div id="status" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No disease Found</b>	
					</font>
				</div>
				
			</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
		</html:form>
	</body>
</html>