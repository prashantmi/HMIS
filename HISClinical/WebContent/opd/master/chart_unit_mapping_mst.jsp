
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title></title>
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
	var elemUnitList = document.forms[0].unitsList;
	
	// Adding Units that are not in Main List but in Unit List
	for(var i=0;i<elemUnitList.options.length;i++)
	{
		var op=document.createElement("option");
		op.value=elemUnitList.options[i].value;
		op.innerHTML=elemUnitList.options[i].text;
		elemMainUnitList.appendChild(op);
	}	
	// Clean Exiting
	elemUnitList.innerHTML = "";
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
				elemUnitList.appendChild(op);
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



function submitTile(mode)
{	
	document.getElementsByName("hmode")[0].value=mode;
	if(document.getElementsByName("hmode")[0].value!="VIEW")
	{
		MoveToSelectedAVFile();
	}	
	document.forms[0].submit();
	
}

function moveRightSingle()
{
	var source;
	var target;
	
	
		source  = document.forms[0].chartListCodeList;
		target = document.forms[0].selectedChartListCode;	
		
		var defaultVar=document.forms[0].defaultChartListCode;
		var selectedDeafult="-1";
		
		for(var i=0;i<defaultVar.length;i++)
		{
			if(defaultVar.options[i].selected)
			{
				selectedDeafult=defaultVar.options[i].value;
			}
		}
				
		defaultVar.innerHTML=0;
		var op=document.createElement("option");
			op.value="-1";
			op.innerHTML="Select Value";
			defaultVar.appendChild(op);	
			
	
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
	
	for(var i=0;i<target.length;i++)
	{
			val = target.options[i].value;
			txt = target.options[i].text;
		
		
			var op=document.createElement("option");
			op.value=val;
			op.innerHTML=txt;
			defaultVar.appendChild(op);	
			
			
	}
	
	defaultVar.value=selectedDeafult;
	deleteThis(target,source);
}

function moveRightAll()
{
	var source;
	var target;
	
		source  = document.forms[0].chartListCodeList;
		target = document.forms[0].selectedChartListCode;	
	
		var defaultVar=document.forms[0].defaultChartListCode;
		var selectedDeafult="-1";
		
		for(var i=0;i<defaultVar.length;i++)
		{
			if(defaultVar.options[i].selected)
			{
				selectedDeafult=defaultVar.options[i].value;
			}
		}
		
		defaultVar.innerHTML=0;
		var op=document.createElement("option");
			op.value="-1";
			op.innerHTML="Select Value";
			defaultVar.appendChild(op);	
		
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
	
	for(var i=0;i<target.length;i++)
	{
			val = target.options[i].value;
			txt = target.options[i].text;
		
		
			var op=document.createElement("option");
			op.value=val;
			op.innerHTML=txt;
			defaultVar.appendChild(op);	
	}
	
	defaultVar.value=selectedDeafult;
	
	
	deleteThis(target,source);
}


function moveLeftSingle()
{
	var source;
	var target;

		target  = document.forms[0].chartListCodeList;
		source = document.forms[0].selectedChartListCode;	
		
		var defaultVar=document.forms[0].defaultChartListCode;
		var selectedDeafult="-1";
		
		for(var i=0;i<defaultVar.length;i++)
		{
			if(defaultVar.options[i].selected)
			{
				selectedDeafult=defaultVar.options[i].value;
			}
		}
		defaultVar.innerHTML=0;
		var op=document.createElement("option");
			op.value="-1";
			op.innerHTML="Select Value";
			
			defaultVar.appendChild(op);	

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
	
	for(var i=0;i<source.length;i++)
	{
			val = source.options[i].value;
			txt = source.options[i].text;
		
		
			var op=document.createElement("option");
			op.value=val;
			op.innerHTML=txt;
			defaultVar.appendChild(op);	
	}
	
	defaultVar.value=selectedDeafult;
	enableIsDefaultCombo(defaultVar);
}

function moveLeftAll()
{
	var source;
	var target;

	
		target  = document.forms[0].chartListCodeList;
		source = document.forms[0].selectedChartListCode;	
		
		var defaultVar=document.forms[0].defaultChartListCode;
		var selectedDeafult="-1";
		
		for(var i=0;i<defaultVar.length;i++)
		{
			if(defaultVar.options[i].selected)
			{
				selectedDeafult=defaultVar.options[i].value;
			}
		}
		
		defaultVar.innerHTML=0;
		var op=document.createElement("option");
			op.value="-1";
			op.innerHTML="Select Value";
			defaultVar.appendChild(op);	
	
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
	
	for(var i=0;i<source.length;i++)
	{
			val = source.options[i].value;
			txt = source.options[i].text;
		
		
			var op=document.createElement("option");
			op.value=val;
			op.innerHTML=txt;
			defaultVar.appendChild(op);	
	}
	
	defaultVar.value=selectedDeafult;
	enableIsDefaultCombo(defaultVar);
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

function MoveToSelectedAVFile()
{
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		for(var i=0;i<document.getElementsByName("unitsList")[0].length;i++)
			document.getElementsByName("unitsList")[0].options[i].selected=false;
		
		// Select All Image in Selected
		for(var i=0;i<document.getElementsByName("selectedUnit")[0].length;i++)
			document.getElementsByName("selectedUnit")[0].options[i].selected=true;
	}
	// Unselect Remaining chartListCodeList
	if(document.forms[0].chartListCodeList)
	{	
		for(var i=0;i<document.forms[0].chartListCodeList.length;i++)
			document.forms[0].chartListCodeList.options[i].selected=false;
	}
	
	// Select All File in Selected
	for(var i=0;i<document.forms[0].selectedChartListCode.length;i++)
		document.forms[0].selectedChartListCode.options[i].selected=true;
}

function validateAdd()
{
	if(document.forms[0].selectedUnit.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].selectedUnit.focus();
		return;
	}
	if (document.getElementsByName("selectedChartListCode")[0].options.length==0)
	{
		alert("Choose at Least One Chart");
		document.getElementsByName("chartListCodeList").focus();
		return;
	}
	if(document.getElementsByName("defaultChartListCode")[0].value!="-1" 
		&& document.getElementsByName("isDefault")[0].value=="-1")
	{
		alert("Choose at Least One Default Value");
		document.getElementsByName("isDefault")[0].focus();
		return;
	}

	if(document.getElementsByName("hmode")[0].value!="VIEW")
		MoveToSelectedAVFile();
	submitTile('SAVE');
}

function validateModify()
{	
	if (document.getElementsByName("selectedChartListCode")[0].options.length==0)
	{
		alert("Choose at Least One Chart");
		document.getElementsByName("chartListCodeList")[0].focus();
		return;
	}
	if(document.getElementsByName("defaultChartListCode")[0].value!="-1" 
		 && document.getElementsByName("isDefault")[0].value=="-1")
	{
		alert("Choose at Least One Default Value");
		document.getElementsByName("isDefault")[0].focus();
		return;
	}

	if(document.getElementsByName("hmode")[0].value!="VIEW")
		MoveToSelectedAVFile();
	submitTile('MODIFYSAVE');
}

function clearModify()
{
	moveLeftAll();
}

function enableIsDefaultCombo(obj)
{
	if(obj){
			 if(obj.value==-1){document.getElementById("divIsDefault").style.display="none";
			 
			}
			else {  
			document.getElementById("divIsDefault").style.display="block";
			}
	    }
}
function clearForm(){
	document.getElementsByName('deptCode')[0].value="-1";
}
window.onload=function()
	{	
		if(document.getElementsByName("defaultChartListCode")[0].value!="-1")
		  {enableIsDefaultCombo(document.getElementsByName("defaultChartListCode"));
		  }
		 
		}
</script>

	<body>
		<html:form action="/master/chartUnitMappingMst">
		 
			<his:TransactionContainer>
				<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="ADD">
					<his:TitleTag name="Add Chart List To Unit">
					</his:TitleTag>
				</logic:equal>
				<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="MODIFY">
					<his:TitleTag name="Modify Chart List Unit Wise">
					</his:TitleTag>
				</logic:equal>
				<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="VIEW">
					<his:TitleTag name="View Chart List Unit Wise">
					</his:TitleTag>
				</logic:equal>
				<his:ContentTag>
				
				<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="MODIFY">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ChartUnitMappingMstFB" property="deptUnitName" />
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					</table>
				</logic:equal>
				
				<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="VIEW">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ChartUnitMappingMstFB" property="deptUnitName" />
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					</table>
				</logic:equal>
				
				<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="ADD">
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
								<html:select name="ChartUnitMappingMstFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
						<td width="20%"  class="tdfont"></td>
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
								<html:select name="ChartUnitMappingMstFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_CHART%>" >
										<html:options collection="<%=OpdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_CHART%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="ChartUnitMappingMstFB" property="unitsList" multiple="true" size="6">
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
								<html:select name="ChartUnitMappingMstFB" property="selectedUnit" multiple="true" size="6">
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
					</logic:equal>
				</his:ContentTag>
				
				<his:ContentTag>
				<logic:notEqual name="ChartUnitMappingMstFB" property="hmode" value="VIEW">
					
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="allChartList"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedChartList"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
						<div align="center">
						<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="ADD">
							<html:select name="ChartUnitMappingMstFB" property="chartListCodeList" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.CHART_NAME_LIST%>" >
								<html:options collection="<%=OpdConfig.CHART_NAME_LIST %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</logic:equal>
						<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="MODIFY">
							<html:select name="ChartUnitMappingMstFB" property="chartListCodeList" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.LIST_ALL_CHARTLIST_NOT_IN_SELECTED_BASED_ON_UNIT%>" >
								<html:options collection="<%=OpdConfig.LIST_ALL_CHARTLIST_NOT_IN_SELECTED_BASED_ON_UNIT %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</logic:equal>	
						</div>
					</td>
					<td width="20%"  class="tdfont">
						<div align="center">
							<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle();'/> 	
							<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll();'/> 	
							<br><br>
							<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 	
							<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll();'/>
						</div>
					</td>
					<td width="40%"  class="tdfonthead">
						<div align="center">
						<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="ADD">
							<html:select name="ChartUnitMappingMstFB" property="selectedChartListCode" multiple="true" size="6">
							</html:select>
						</logic:equal>
						<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="MODIFY">
							<html:select name="ChartUnitMappingMstFB" property="selectedChartListCode" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST%>" >
								<html:options collection="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</logic:equal>	
						</div>
					</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					
					</table>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectDefaultChartList"/>
									</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
							<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="ADD">
								<html:select name="ChartUnitMappingMstFB" property="defaultChartListCode" styleClass="registrationCmb"  onchange="enableIsDefaultCombo(this);">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST%>" >
									<html:options collection="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								</logic:equal>
								<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="MODIFY">
								<html:select name="ChartUnitMappingMstFB" property="defaultChartListCode" styleClass="registrationCmb"  onchange="enableIsDefaultCombo(this);">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST%>" >
									<html:options collection="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								</logic:equal>
							</div>
						</td>
					</tr>
					</table>
					<div id="divIsDefault" style="display: none"  >
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
		 				  <tr>
							  <td width="40%" class="tdfonthead">
			   						 <div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
										<b><bean:message key="isdefault"/>&nbsp;</b>
										</font>
				 					</div>
			 				 </td>
			 				 <td width="50%" class="tdfonthead" >
			   				 <div align="left">
									<html:select name="ChartUnitMappingMstFB" property="isDefault" styleClass="registrationCmb"  >
										<html:option value="-1">Select Value</html:option>
										<html:option value="<%=OpdConfig.CURRENT_VISIT%>">Current Visit</html:option>
										<html:option value="<%=OpdConfig.COMPLETE_EPISODE%>">Complete Episode</html:option>
									</html:select>
								</div>
			  				</td>
			  				
						</tr>
						</table>
					</div>	
					</logic:notEqual>
					
					<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="VIEW">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="50%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="selectedChartList" />
											</b>
										</font>
									</div>
								</td>
								<td width="50%" class="tdfont"></td>
							</tr>
							
							<logic:present name="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST%>" >
							<logic:iterate id="entryObj" indexId="idx" name="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST%>" type="hisglobal.utility.Entry">
							<tr>
								<td width="50%" class="tdfonthead"></td>
								<td width="50%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="entryObj" property="label"/>
										</font>	
									</div>
								</td>
							</tr>
							</logic:iterate>
							</logic:present>
							
						</table>
							<html:hidden name="ChartUnitMappingMstFB" property="selectedChartListCode" />
							
						
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="selectDefaultChartList"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfonthead">
								<div align="left">
									<html:select name="ChartUnitMappingMstFB" property="defaultChartListCode" styleClass="registrationCmb" disabled="true" onchange ="enableIsDefaultCombo(this);" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST%>" >
										<html:options collection="<%=OpdConfig.MAPPED_UNIT_CHARTLIST_LIST %>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
						</table>
							<div id="divIsDefault" style="display: none">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
		 				  <tr>
							  <td width="40%" class="tdfonthead">
			   						 <div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
										<b><bean:message key="isdefault"/>&nbsp;</b>
										</font>
				 					</div>
			 				 </td>
			 				 <td width="50%" class="tdfonthead" >
			   				 <div align="left">
									<html:select name="ChartUnitMappingMstFB" property="isDefault" styleClass="registrationCmb" disabled="true"   >
										<html:option value="-1">Select Value</html:option>
										<html:option value="<%=OpdConfig.CURRENT_VISIT%>">Current Visit</html:option>
										<html:option value="<%=OpdConfig.COMPLETE_EPISODE%>">Complete Episode</html:option>
									</html:select>
								</div>
			  				</td>
			  				
						</tr>
						</table>
					</div>
					</logic:equal>
					
				</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
					<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="ADD">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd()" onkeypress="if(event.keyCode==13) validateAdd()">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearForm(); submitTile('ADD');" onkeypress="if(event.keyCode==13){ clearForm(); submitTile('ADD');}">
					</logic:equal>	
					<logic:equal name="ChartUnitMappingMstFB" property="hmode" value="MODIFY">	
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify()" onkeypress="if(event.keyCode==13) validateModify();">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearModify()" onkeypress="if(event.keyCode==13) clearModify();">
					</logic:equal>	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="ChartUnitMappingMstFB" property="hmode"/>
				<html:hidden name="ChartUnitMappingMstFB" property="tempMode"/>
				<html:hidden name="ChartUnitMappingMstFB" property="deptCode"/>
				<html:hidden name="ChartUnitMappingMstFB" property="deptUnitName"/>
				
				<div id="status" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No units Found</b>	
					</font>
				</div>
				
				<div id="statusTreat" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No Chart List Found</b>	
					</font>
				</div>
			</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
		</html:form>
	</body>
</html>