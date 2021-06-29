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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/css/calendar-blue2.css"/>

<script>
function deptSelected(cboDept)
{
	var deptId=cboDept.value;
	var elemMainUnitList = document.forms[0].mainUnitsList;
	var unitList = document.forms[0].unitsList;
	
	// Adding Units that are not in Main List but in Unit List
	for(var i=0;i<unitList.options.length;i++)
	{
		var op=document.createElement("option");
		op.value=unitList.options[i].value;
		op.innerHTML=unitList.options[i].text;
		elemMainUnitList.appendChild(op);
	}	
	// Clean Exiting
	unitList.innerHTML = "";
	document.getElementById("status").style.display="none";
	if(deptId != "-1")
	{
		// Adding Units of Selected Department
		var toBeRemoved = "";
		for(var i=0;i<elemMainUnitList.options.length;i++)
		{
			var str=elemMainUnitList.options[i].value.substr(0,3);
			if(str==deptId)
			{
				var op=document.createElement("option");
				op.value=elemMainUnitList.options[i].value;
				op.innerHTML=elemMainUnitList.options[i].text;
				unitList.appendChild(op);
				toBeRemoved += i+",";
			}
		}
		if(toBeRemoved.length>0)
		{
			toBeRemoved = toBeRemoved.substring(0,toBeRemoved.length-1);
			var arr = toBeRemoved.split(",");
			for(var i=0;i<arr.length;i++)
			{
				elemMainUnitList.options[arr[i]-i]=null;
			}
		}	
			else 
			 document.getElementById("status").style.display="block";
	}
}

function moveRightSingleUnits()
{
	var source;
	var target;

	
	source  = document.forms[0].unitsList;
	target = document.forms[0].selectedUnit;	
	
	

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


function moveLeftSingleUnits()
{
	var source;
	var target;

		target  = document.forms[0].unitsList;
		source = document.forms[0].selectedUnit;	


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


function moveLeftAllUnits()
{
	var source;
	var target;

	
		target  = document.forms[0].unitsList;
		source = document.forms[0].selectedUnit;	
	

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


function moveRightAllUnits()
{
	var source;
	var target;
	
	
		source  = document.forms[0].unitsList;
		target = document.forms[0].selectedUnit;	
	

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
function validateAdd()
{
	if(document.getElementsByName("deptCode")[0].value=="-1")
	{
		alert("Choose at Least One Department");
		document.forms[0].deptCode.focus();
		return false;
	}
	if(document.forms[0].selectedUnit.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].selectedUnit.focus();
		return false;
	}
	return true;
	
	
}
function finalSubmit(mode)
{
		if(validateAdd())
				
		
	submitPage(mode);
	
}
function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function MoveToSelected()
{
	if(document.forms[0].unitsList)
		for(var i=0;i<document.forms[0].selectedUnit.length;i++)
			document.forms[0].selectedUnit.options[i].selected=true;
	
	if(document.forms[0].selectedUnit)
	{	
		for(var i=0;i<document.forms[0].unitsList.options.length;i++)
			document.forms[0].unitsList.options[i].selected=false;
	}
}
function clearForm(){
	document.getElementsByName('deptCode')[0].value="-1";
}
function submitForm(mode)
{	
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}
</script>

	<body>
		<html:form action="/master/DeptUnitIcdMapping">
			<his:TransactionContainer>
			
				<his:ContentTag>
				<his:TitleTag name="Department Unit Mapping>> ">
				</his:TitleTag>
				
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deptName"/>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST%>" >
									<html:options collection="<%=OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedUnits"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center" style="display: none;" >
								<html:select name="DeptUnitIcdMappingFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:notEqual name="DeptUnitIcdMappingFB" property="hmode"  value="NEW">
									<logic:present name="<%=OpdConfig.ALL_UNIT_LIST_NOT_MAPPED%>" >
										<html:options collection="<%=OpdConfig.ALL_UNIT_LIST_NOT_MAPPED%>" property="value" labelProperty="label" />
									</logic:present>
									</logic:notEqual>
								</html:select>
							</div>
						
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="unitsList" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingleUnits();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAllUnits();'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingleUnits();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAllUnits();'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="selectedUnit" multiple="true" size="6">
								<logic:notEqual name="DeptUnitIcdMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.MAPPED_UNIT_LIST%>" >
								<html:options collection="<%=OpdConfig.MAPPED_UNIT_LIST %>" property="value" labelProperty="label" />
								</logic:present>
								</logic:notEqual>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfonthead"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					</table>
					
				</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' style=cursor:pointer tabindex="1" onkeypress="if(event.keyCode==13) finalSubmit('NEXT')" tabindex="1" onclick="finalSubmit('NEXT')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearForm()& submitPage('ADD')" onkeypress="if(event.keyCode==13) clearForm()& submitPage('ADD');">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="DeptUnitIcdMappingFB" property="hmode"/>
				<html:hidden name="DeptUnitIcdMappingFB" property="deptUnitCode"/>
				
				<div id="status" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No units Found</b>	
					</font>
				</div>
				
				
			</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
		</html:form>
	</body>
</html>