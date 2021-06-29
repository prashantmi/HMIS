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

<%@page import="opd.OpdConfig"%>

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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

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
	}

/*	var deptId=cboDept.value;
	var deptName=cboDept.options[cboDept.selectedIndex].text;
	if(deptId != "-1")
	{
		var lst=document.forms[0].unitsList;
		var len=lst.options.length;
		var limit=0;
		var deptNameLen=deptName.length;
		for(i=len-1;i>=limit;i--)
		{
			//var str=lst.options[i].value.substr(3,3);
			var str=lst.options[i].text.substr(0,deptNameLen);
			if(str==deptName)
			{
				var val=lst.options[i].value;
				var txt=lst.options[i].text;
				for(j=i-1;j>=0;j--)
				{
					lst.options[j+1].value=lst.options[j].value;
					lst.options[j+1].text=lst.options[j].text;
				}
				lst.options[0].value=val;
				lst.options[0].text=txt;
				if(i!=0)i++;
				limit++;
			}
		}
	}*/
}


function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		source = document.forms[0].unitsList;
		target = document.forms[0].selectedUnits;	
	}
	
	if(listNo=="3")
	{
		source = document.forms[0].wardsList;
		target = document.forms[0].selectedWards;	
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
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		source = document.forms[0].unitsList;
		target = document.forms[0].selectedUnits;	
	}
	
		if(listNo=="3")
	{
		source = document.forms[0].wardsList;
		target = document.forms[0].selectedWards;	
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
		target  = document.forms[0].seatsList;
		source = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		target = document.forms[0].unitsList;
		source = document.forms[0].selectedUnits;	
	}
	
	
		if(listNo=="3")
	{
		target = document.forms[0].wardsList;
		source = document.forms[0].selectedWards;	
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
		target  = document.forms[0].seatsList;
		source = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		target = document.forms[0].unitsList;
		source = document.forms[0].selectedUnits;	
	}
	
	
		if(listNo=="3")
	{
		target = document.forms[0].wardsList;
		source = document.forms[0].selectedWards;	
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
	// Unselect Remaining Seats
	if(document.forms[0].seatsList)
	{
		for(var i=0;i<document.forms[0].seatsList.options.length;i++)
			document.forms[0].seatsList.options[i].selected=false;
	}
	
	 //Unselect Remaining Units
	if(document.forms[0].unitsList)
	{	
		for(var i=0;i<document.forms[0].unitsList.options.length;i++)
			document.forms[0].unitsList.options[i].selected=false;
	}
	
	 //Unselect Remaining Wards
	if(document.forms[0].wardsList)
	{	
		for(var i=0;i<document.forms[0].wardsList.options.length;i++)
			document.forms[0].wardsList.options[i].selected=false;
	}
	
	// Select All Seats in Selected
	if(document.forms[0].selectedSeats)
	{
		for(var i=0;i<document.forms[0].selectedSeats.options.length;i++)
			document.forms[0].selectedSeats.options[i].selected=true;
	}
	
	// Select All Units in Selected
	if(document.forms[0].selectedUnits)
	{
		for(var i=0;i<document.forms[0].selectedUnits.options.length;i++)
			document.forms[0].selectedUnits.options[i].selected=true;
	}
		
	// Select All Wards in Selected
	if(document.forms[0].selectedWards)
	{
	for(var i=0;i<document.forms[0].selectedWards.options.length;i++)
			document.forms[0].selectedWards.options[i].selected=true;
	}	
}

function goSelected()
{
	/*if(document.forms[0].deptId.value=="-1")
	{
		alert("Select Department ...")
		document.forms[0].deptId.focus();
		return;
	}*/
	/*if(document.forms[0].deskType.value=="-1")
	{
		alert("Select Desk Type ...")
		document.forms[0].deskType.focus();
		return;
	}*/
	if(document.forms[0].selectedWards.options.length==0)
	{
		alert("Choose at Least One Ward");
		document.forms[0].wardsList.focus();
		return;
	}
	submitPage("SELECTSEATS");
}

function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	if(document.forms[0].selectedUnits && document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	
	if(document.forms[0].selectedWards && document.forms[0].selectedWards.options.length==0)
	{
		alert("Choose at Least One Ward");
		document.forms[0].wardsList.focus();
		return;
	}
	
	if(document.getElementsByName("hmode")[0].value=="SELECTSEATS" || document.getElementsByName("hmode")[0].value=="GROUPSEAT")
	{
		if(document.getElementsByName("group")[0].value=="-1")
		{
			alert("Please Select the Group");
			document.getElementsByName("group")[0].focus();
			return false;
		}	
	}
		
	
	if(document.forms[0].selectedSeats && document.forms[0].selectedSeats.options.length==0)
	{
		alert("Choose at least One Seat ... ");
		document.forms[0].seatsList.focus();
		return false;
	}
	if(document.forms[0].deskId && document.forms[0].deskId.value=="-1")
	{
		alert("Select Desk ... ");
		document.forms[0].deskId.focus();
		return false;
	}
	return true;
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function showSeats()
{
	if(document.getElementsByName("group")[0].value=="-1")
	{
		alert("Please Select The Group")
	}
	else
	{
		submitPage('GROUPSEAT');
	}
}
function showWards()
{
	if(document.getElementsByName("unitId")[0].value=="-1")
	{
		alert("Please Select The Unit")
	}
	else
	{
		submitPage('UNITWARD');
	}
}

function showWardsWhereSeatNotNull()
{
	if(document.getElementsByName("unitId")[0].value=="-1")
	{
		alert("Please Select The Unit")
	}
	else
	{
		submitPage('UNITWARDSEAT');
	}
}
</script>






	<body>
		<html:form action="/master/AddUserDeskUnitWardMappingMaster.cnt">
			<html:hidden name="UserDeskUnitWardMappingMasterFB" property="hmode"/>
	
			<his:TransactionContainer>

			<his:TitleTag name="Unit Ward User Wise Desk Mapping Master>>Add">
			</his:TitleTag>
		
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" colspan="3"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="aditionMode"/>
								</font>
							</div>
						</td>
						<td width="45%" colspan="3" class="tdfont" nowrap="nowrap">
							<div align="left">
							<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">
								<bean:message key="unitWise"/>
								<html:radio name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
							
								<bean:message key="unitwardwise"/>
								<html:radio name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE %>" onclick="submitPage('UNITWARD')"></html:radio>
								
								<bean:message key="unitwardseat"/>
								<html:radio name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE %>" onclick="submitPage('UNITWARDSEAT')"></html:radio>
						
							</logic:equal>
							<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.YES%>">
								<bean:message key="unitWise"/>
								<html:radio name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE %>" disabled="true"></html:radio>
							
								<bean:message key="unitwardwise"/>
								<html:radio name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE %>" disabled="true"></html:radio>								
							
								<bean:message key="unitwardseat"/>
								<html:radio name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE %>" disabled="true"></html:radio>
						
							</logic:equal>
							</div>	
						</td>
						<td width="10%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deskType"/>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name="UserDeskUnitWardMappingMasterFB" property="deskTypeDesc"/></b>
								</font>
								<html:hidden name="UserDeskUnitWardMappingMasterFB" property="deskType"/>
								<html:hidden name="UserDeskUnitWardMappingMasterFB" property="deskTypeDesc"/>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>		
			
			
			
			
			
			
			
			
			
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE %>">			
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">
		
			<his:SubTitleTag name="Select Units">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deptName"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="deptId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center" style="display: none;" >
								<html:select name="UserDeskUnitWardMappingMasterFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED_SEAT_AND_WARD%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED_SEAT_AND_WARD%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="unitsList" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("2");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="selectedUnits" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>
		</logic:equal>
		
		
		
		
		
		
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE %>">
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">
			<his:SubTitleTag name="Select Wards">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="unitId" onchange="showWards()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER%>" >
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="ward"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
							<html:select name="UserDeskUnitWardMappingMasterFB" property="wardsList" multiple="true" size="6">
							<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT%>" >
							<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT%>" property="value" labelProperty="label" />
							</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("3");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("3");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="selectedWards" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>
		</logic:equal>
		
	
		
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE %>">
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">
									
			<his:SubTitleTag name="Select Wards">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="unitId" onchange="showWardsWhereSeatNotNull()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER%>" >
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="ward"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="wardsList" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS%>" >
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS%>" property="value" labelProperty="label" />
								</logic:present>								
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("3");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("3");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="selectedWards" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>
		</logic:equal>
	
	
	
	
	
	
	 	<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.YES%>">
			<his:SubTitleTag name="Selected Wards And Units">
			</his:SubTitleTag>
			<his:ContentTag>			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
					
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="selectedWards" multiple="true" size="6">
									<logic:present name="UserDeskUnitWardMappingMasterFB" property="selectedWardsName" >
									<html:optionsCollection name="UserDeskUnitWardMappingMasterFB" property="selectedWardsName" label="label" value="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>
		
				 
				 
				 
				 
				 
				 
				 
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE %>">
		 <logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">
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
								<b><bean:message key="seldsk"/></b>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<div align="left">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="deskId" styleClass="registrationCmb">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" property="value" labelProperty="label" />
									</logic:present>								
								</html:select>
							</div>
						</td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>	
			</logic:equal>
			</logic:equal>
			
			
			
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE %>">
		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">
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
								<b><bean:message key="seldsk"/></b>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							
							
					<%-- 		<div align="left">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="deskId" styleClass="registrationCmb">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" property="value" labelProperty="label" />
									</logic:present>								
								</html:select>
							</div> --%>
				<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType" tabindex="1" onchange="submitForm21('GETDESK')"  %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							
				</td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>	
			</logic:equal>
			</logic:equal>
		  







	<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE %>">
 		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.YES%>">		 	 
 			<his:SubTitleTag name="Select Users & Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="group"/>
								</font>	
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="group" onchange="showSeats()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST%>" >
									<html:options collection="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST %>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
				
					
					<tr>
						<td width="15%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="user"/>
								</font>	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="seatsList" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS_MAPPING_MASTER%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS_MAPPING_MASTER%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="selectedSeats" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
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
								<b><bean:message key="seldsk"/></b>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<div align="left">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="deskId" styleClass="registrationCmb">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>			
		</logic:equal>
	</logic:equal>
	
	
	
	
	
	<his:ButtonToolBarTag>
		<span id="saveDiv">
		
			<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE%>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" tabindex="1" onclick="finalSubmit('SAVE')">
			</logic:equal>
			
			<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE%>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" tabindex="1" onclick="finalSubmit('SAVE')">
			</logic:equal>
			
			<logic:equal name="UserDeskUnitWardMappingMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE%>">
		 		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.NO%>">		 	 
					<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goSelected();" tabindex="1" onclick="goSelected();">
				</logic:equal>
				
				
				
		 		<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isGoPressed" value="<%=OpdConfig.YES%>">		 	 
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" tabindex="1" onclick="finalSubmit('SAVE')">
				</logic:equal>
			</logic:equal>
			
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
		
		
		</span>
	</his:ButtonToolBarTag>	

	<html:hidden name="UserDeskUnitWardMappingMasterFB" property="additionMode"/>
	<html:hidden name="UserDeskUnitWardMappingMasterFB" property="unitId"/>
			</his:TransactionContainer>
		</html:form>
		<center><b><his:status/></b></center>
	</body>
</html>