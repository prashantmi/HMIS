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
	
	
		source  = document.forms[0].drugListCodeList;
		target = document.forms[0].selectedDrugListCode;	
		
		var defaultVar=document.forms[0].defaultDrugListCode;
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
	
		source  = document.forms[0].drugListCodeList;
		target = document.forms[0].selectedDrugListCode;	
	
		var defaultVar=document.forms[0].defaultDrugListCode;
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

		target  = document.forms[0].drugListCodeList;
		source = document.forms[0].selectedDrugListCode;	
		
		var defaultVar=document.forms[0].defaultDrugListCode;
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
}

function moveLeftAll()
{
	var source;
	var target;

	
		target  = document.forms[0].drugListCodeList;
		source = document.forms[0].selectedDrugListCode;	
		
		var defaultVar=document.forms[0].defaultDrugListCode;
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
	// Unselect Remaining drugListCodeList
	if(document.forms[0].drugListCodeList)
	{	
		for(var i=0;i<document.forms[0].drugListCodeList.length;i++)
			document.forms[0].drugListCodeList.options[i].selected=false;
	}
	
	// Select All File in Selected
	for(var i=0;i<document.forms[0].selectedDrugListCode.length;i++)
		document.forms[0].selectedDrugListCode.options[i].selected=true;
		
		
}

function validateAdd()
{
	if(document.forms[0].selectedUnit.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].selectedUnit.focus();
		return false;
	}
	else if (document.getElementsByName("selectedDrugListCode")[0].options.length==0)
	{
		alert("Choose at Least One Drug List");
		document.getElementsByName("drugListCodeList").focus();
	}
	else
	{
		submitTile('SAVE');
	}
	
	
}

function validateModify()
{	
	
	if (document.getElementsByName("selectedDrugListCode")[0].options.length==0)
	{
		alert("Choose at Least One Drug List");
		document.getElementsByName("drugListCodeList").focus();
	}
	else
	submitTile('MODIFYSAVE');
}

function clearModify()
{
	moveLeftAll();
}


</script>

	<body>
		<html:form action="/master/unitDrugListMaster">
			<his:TransactionContainer>
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="ADD">
					<his:TitleTag name="Add Drug List To Unit">
					</his:TitleTag>
				</logic:equal>
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="MODIFY">
					<his:TitleTag name="Modify Drug List Unit Wise">
					</his:TitleTag>
				</logic:equal>
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="VIEW">
					<his:TitleTag name="View Drug List Unit Wise">
					</his:TitleTag>
				</logic:equal>
				<his:ContentTag>
				
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="MODIFY">
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
								&nbsp;<bean:write name="UnitDrugListMasterFB" property="deptUnitName" />
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					</table>
				</logic:equal>
				
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="VIEW">
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
								&nbsp;<bean:write name="UnitDrugListMasterFB" property="deptUnitName" />
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					</table>
				</logic:equal>
				
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="ADD">
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
								<html:select name="UnitDrugListMasterFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
								<html:select name="UnitDrugListMasterFB" property="mainUnitsList" multiple="true" size="6" disabled="true" style="width:150px">
									<logic:present name="<%=OpdConfig.NOT_MAPPED_ALL_UNIT_LIST%>" >
										<html:options collection="<%=OpdConfig.NOT_MAPPED_ALL_UNIT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UnitDrugListMasterFB" property="unitsList" multiple="true" size="6" style="width:150px">
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
								<html:select name="UnitDrugListMasterFB" property="selectedUnit" multiple="true" size="6" style="width:150px">
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
				<logic:notEqual name="UnitDrugListMasterFB" property="hmode" value="VIEW">
					
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="allDrugList"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedDrugList"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
						<div align="center">
						<logic:equal name="UnitDrugListMasterFB" property="hmode" value="ADD">
							<html:select name="UnitDrugListMasterFB" property="drugListCodeList" multiple="true" size="6" style="width:150px">
								<logic:present name="<%=OpdConfig.ALL_DRUGLIST_LIST%>" >
								<html:options collection="<%=OpdConfig.ALL_DRUGLIST_LIST %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</logic:equal>
						<logic:equal name="UnitDrugListMasterFB" property="hmode" value="MODIFY">
							<html:select name="UnitDrugListMasterFB" property="drugListCodeList" multiple="true" size="6" style="width:150px">
								<logic:present name="<%=OpdConfig.LIST_ALL_DRUGLIST_NOT_IN_SELECTED_BASED_ON_UNIT%>" >
								<html:options collection="<%=OpdConfig.LIST_ALL_DRUGLIST_NOT_IN_SELECTED_BASED_ON_UNIT %>" property="value" labelProperty="label" />
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
						<logic:equal name="UnitDrugListMasterFB" property="hmode" value="ADD">
							<html:select name="UnitDrugListMasterFB" property="selectedDrugListCode" multiple="true" size="6" style="width:150px">
							</html:select>
						</logic:equal>
						<logic:equal name="UnitDrugListMasterFB" property="hmode" value="MODIFY">
							<html:select name="UnitDrugListMasterFB" property="selectedDrugListCode" multiple="true" size="6" style="width:150px">
								<logic:present name="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST%>" >
								<html:options collection="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST %>" property="value" labelProperty="label" />
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
										<bean:message key="selectDefaultDrugList"/>
									</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<html:select name="UnitDrugListMasterFB" property="defaultDrugListCode" styleClass="registrationCmb"  style="width:150px">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST%>" >
									<html:options collection="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					</table>	
					</logic:notEqual>
					
					<logic:equal name="UnitDrugListMasterFB" property="hmode" value="VIEW">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="50%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="selectedDrugList" />
											</b>
										</font>
									</div>
								</td>
								<td width="50%" class="tdfont"></td>
							</tr>
							
							<logic:present name="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST%>" >
							<logic:iterate id="entryObj" indexId="idx" name="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST%>" type="hisglobal.utility.Entry">
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
							<html:hidden name="UnitDrugListMasterFB" property="selectedDrugListCode" />
							
						
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="selectDefaultDrugList"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfonthead">
								<div align="left">
									<html:select name="UnitDrugListMasterFB" property="defaultDrugListCode" styleClass="registrationCmb" disabled="true" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST%>" >
										<html:options collection="<%=OpdConfig.MAPPED_UNIT_DRUGLIST_LIST %>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
						</table>
							
					</logic:equal>
					
				</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd() && submitTile('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitTile('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('ADD')" onkeypress="if(event.keyCode==13) submitTile('ADD');">
				</logic:equal>	
				<logic:equal name="UnitDrugListMasterFB" property="hmode" value="MODIFY">	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitTile('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitTile('MODIFYSAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearModify()" onkeypress="if(event.keyCode==13) clearModify();">
				</logic:equal>	
				
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
				
				</his:ButtonToolBarTag>
				
				<html:hidden name="UnitDrugListMasterFB" property="hmode"/>
				<html:hidden name="UnitDrugListMasterFB" property="tempMode"/>
				<html:hidden name="UnitDrugListMasterFB" property="deptCode"/>
				<html:hidden name="UnitDrugListMasterFB" property="deptUnitName"/>
				
				<div id="status" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No units Found</b>	
					</font>
				</div>
				
				<div id="statusTreat" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No Drug List Found</b>	
					</font>
				</div>
			</his:TransactionContainer>
			<center><b><his:status/></b></center>
		
		</html:form>
	</body>
</html>