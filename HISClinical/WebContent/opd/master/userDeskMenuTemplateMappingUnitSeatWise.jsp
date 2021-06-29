<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>

<%@page import="java.util.List"%>
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

function getTemplates()
{	
	var valid=true;
	if(document.getElementsByName("group")[0].value=="-1")
	{
		alert("Please Select The Group")
		valid=false;
	}
	
	if(document.getElementsByName("group")[0].value!="-1")
	{	
		if(document.forms[0].selectedSeats.options.length==0)
		{
			alert("Choose at Least One Seat");
			document.forms[0].seatsList.focus();
			valid=false;
		}
	}
		if(valid)
		{
			MoveToSelected();
			submitPage("GETMENUSTEMPLATEFORUNITSEAT");	
		}
		return valid;
	
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

function goSelected()
{	
	var valid=true;
	if(document.getElementsByName("deptId")[0].value=="-1")
	{
		alert("Please Select The Department")
		valid=false;
	}
	
	if(document.getElementsByName("deptId")[0].value!="-1")
	{	
		if(document.forms[0].selectedUnits.options.length==0)
		{
			alert("Choose at Least One Unit");
			document.forms[0].unitsList.focus();
			valid=false;
		}
	}
	if(valid)
	{
		MoveToSelected();
		submitPage("SELECTSEATS");
	}
	return valid;
}

function showMenus()
{
MoveToSelected();
 document.getElementById('viewDisplay').style.display="block";
 submitForm('GETMENUSFORUNITSEAT');
}

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
			var deskType=document.getElementsByName("deskType")[0].value;
			var str=elemMainUnitList.options[i].value.substr(0,3);
			var isCasualty=elemMainUnitList.options[i].value.split("@")[1];
			if(deskType=="2")
			{
				if(str==deptId && isCasualty=="3")
				{
					var op=document.createElement("option");
					op.value=elemMainUnitList.options[i].value;
					op.innerHTML=elemMainUnitList.options[i].text;
					elemUnitList.appendChild(op);
					toBeRemoved += i+",";
				}
			}
			else
			{
				if(str==deptId)
				{
					var op=document.createElement("option");
					op.value=elemMainUnitList.options[i].value;
					op.innerHTML=elemMainUnitList.options[i].text;
					elemUnitList.appendChild(op);
					toBeRemoved += i+",";
				}
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
	
	/*var deptName=cboDept.options[cboDept.selectedIndex].text;
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

	if(listNo=="1")
	{
		source = document.forms[0].unitsList;
		target = document.forms[0].selectedUnits;	
	}
		if(listNo=="2")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
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
		source = document.forms[0].unitsList;
		target = document.forms[0].selectedUnits;	
	}
		if(listNo=="2")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
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
		target = document.forms[0].unitsList;
		source = document.forms[0].selectedUnits;	
	}
		if(listNo=="2")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
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
		target = document.forms[0].unitsList;
		source = document.forms[0].selectedUnits;	
	}
		if(listNo=="2")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
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
	// Select All Seats in Selected
	if(document.forms[0].selectedSeats)
	{
		for(var i=0;i<document.forms[0].selectedSeats.options.length;i++)
			document.forms[0].selectedSeats.options[i].selected=true;
	}
	
	// Unselect Remaining Units
	if(document.forms[0].unitsList)
	{	
		for(var i=0;i<document.forms[0].unitsList.options.length;i++)
			document.forms[0].unitsList.options[i].selected=false;
	}

	
	// Select All Units in Selected
	for(var i=0;i<document.forms[0].selectedUnits.options.length;i++)
		document.forms[0].selectedUnits.options[i].selected=true;
}

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function showTemplateByDesk()
{
	if(document.getElementsByName("deskId")[0].value=="-1")
	{
		//alert("Please Select a Desk Type");
		hideAllShowed();
		document.getElementsByName("deskId")[0].focus();
		return;
	}
	submitPage('TEMPLATEBYDESK');
}

function deleteRow(obj1,obj2)
{
	document.getElementsByName("templateName")[0].value=obj1;
	document.getElementsByName("hiddenTemplateId")[0].value=obj2;
	submitPage('DELETEROW');
}

function validateAdd()
{
	var valid=true;
	if(document.getElementsByName("deskMenuId")[0].value=="-1")
	{
		alert("Please Select the Desk Menu");
		document.getElementsByName("deskMenuId")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("templateId")[0].value=="-1")
	{
		alert("Please Select the Template");
		document.getElementsByName("templateId")[0].focus();
		valid=false;
	}
	return valid;
}


function  getDeskByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
	 
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETDESKBYUNIT");
}

function getSeatByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETSEATBYUNIT");
}
function getDeskByUnitNSeat()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("userSeatId")[0].value=="-1")
	{
	//	alert("Please Select a Seat");
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		return;
	}
	submitPage("GETDESKBYUNITNSEAT");
}

function getWardByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETWARDBYUNIT");
}

function getDeskByWard()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("wardCode")[0].value=="-1")
	{
	//	alert("Please Select a Ward");
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		document.getElementsByName("wardCode")[0].focus();
		hideAllShowed();
		return;
	}
	submitPage("GETDESKBYWARD");
}

function getWardForUnitWardSeatByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	
	submitPage("GETWARDBYUNITFORUWS");
}

function getSeatByUnitNWard()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("wardCode")[0].value=="-1")
	{
	//	alert("Please Select a Ward");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("wardCode")[0].focus();
		return;
	}
	submitPage("GETSEATBYUNITNWARD");
}

function getDeskByUnitNWardNSeat()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("wardCode")[0].value=="-1")
	{
	//	alert("Please Select a Ward");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("wardCode")[0].focus();
		return;
	}
	if(document.getElementsByName("userSeatId")[0].value=="-1")
	{
	//	alert("Please Select a Seat");
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed();
		document.getElementsByName("userSeatId")[0].focus();
		return;
	}
	submitPage("GETDESKBYUNITNWARDNSEAT");
}

function doEmptyCombo(cbo)
{
	cbo.innerHTML="";
	var op=document.createElement("option");
	op.value="-1";
	op.innerHTML="Select Value";
	cbo.appendChild(op);
}

function hideAllShowed()
{
	if(document.getElementById('viewDisplay'))
		document.getElementById('viewDisplay').innerHTML="";
	if(document.getElementById('addButton'))
		document.getElementById('addButton').style.display="none";
	if(document.getElementById('modifyButton'))
		document.getElementById('modifyButton').style.display="none";
	if(document.getElementById('deleteButton'))
		document.getElementById('deleteButton').style.display="none";
}

function getTemplate()
{
	submitPage('GETTEMPLATE');
}

function validateModify()
{
	var valid=true;
	var menuName=document.getElementsByName("deskMenuId")[0].value;
	var tempName=document.getElementsByName("templateId")[0].value;
	if(menuName!="-1" || tempName!="-1")
	{
		if(!validateAdd())
			valid=false;
	}
	
	return valid;
}

</script>

	<body>
		<html:form action="/master/UserDeskMenuTempMapping.cnt">
			<his:TransactionContainer>
				<his:TitleTag name="User Desk Menu Template Mapping Master">
				</his:TitleTag>
		
			<%boolean disabledFlag=false; %>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="MODIFYDESKID">	
				<%disabledFlag=true; %>
			</logic:equal>	
			<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="ADDBYDESKID">
				<%disabledFlag=true; %>
			</logic:equal>	
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="aditionMode"/>
									</B>
								</font>
							</div>
						</td>
						<td width="75%" colspan="3" class="tdfont">
							<div align="left">
								
								<bean:message key="deskWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="unitWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="unitSeatWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="wardWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="unitWardSeatWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>	
				
			<his:ContentTag>
				
				
				<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType"  disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								
							</div>
							<html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>
						</td>	
					</tr>
							<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskName"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskId"  disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								
							</div>
						</td>	
					</tr>
						
			
					</table>
						
				</logic:equal>	
			
				<his:SubTitleTag name="Units">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
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
								<html:select name="UserDeskMenuTempMapFB" property="deptId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT %>">
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
								<html:select name="UserDeskMenuTempMapFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
								
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="unitsList" multiple="true" size="6">
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
								<html:select name="UserDeskMenuTempMapFB" property="selectedUnits" multiple="true" size="6">
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
					</logic:equal>
					
					<logic:notEqual name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedUnits" multiple="true" size="6">
									<logic:present name="UserDeskMenuTempMapFB" property="selectedUnitsName" >
									<html:optionsCollection name="UserDeskMenuTempMapFB" property="selectedUnitsName" label="label" value="value"/>
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
					</logic:notEqual>
				
				</table>
			</his:ContentTag>
	
		</his:ContentTag>
	
	<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
	<his:SubTitleTag name="Seats">
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
								<html:select name="UserDeskMenuTempMapFB" property="group" onchange="showSeats()">
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
									<bean:message key="seat"/>
								</font>	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="seatsList" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS%>" property="value" labelProperty="label" />
									</logic:present>
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
								<html:select name="UserDeskMenuTempMapFB" property="selectedSeats" multiple="true" size="6">
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
	</logic:equal>
	
	
	
	
	
	<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
	<his:SubTitleTag name="Seats">
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
								<html:select name="UserDeskMenuTempMapFB" property="selectedSeats" multiple="true" size="6">
									<logic:present name="UserDeskMenuTempMapFB" property="selectedSeatsName" >
									<html:optionsCollection name="UserDeskMenuTempMapFB" property="selectedSeatsName" label="label" value="value"/>
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
	
	
	
	<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">		
		<his:statusRecordFound>
			<his:SubTitleTag name="Select Menus">
			</his:SubTitleTag>
							
			
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="menuName"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="tmplname"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="isdefault"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td width="30%" class="tdfont">		
								<div align="center">
									<html:select name="UserDeskMenuTempMapFB"  property="deskMenuId" onchange="getTemplate()">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST%>" >
										<html:options collection="<%= OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="30%" class="tdfont">		
								<div align="center">
									<html:select name="UserDeskMenuTempMapFB"  property="templateId">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES %>" property="templateId" labelProperty="templateName" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="30%" class="tdfont">		
								<div align="center">
									<html:select name="UserDeskMenuTempMapFB"  property="isDefault">
										<html:option value="0" >False</html:option>
										<html:option value="1" >True</html:option>
									</html:select>
								</div>
							</td>
							<td width="10%" class="tdfont">		
								<div align="center">
									<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitPage('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitPage('ADDROW');">
								</div>
							</td>	
						</tr>
					</table>
				</his:ContentTag>	
				<%if(session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO)!=null){ %>
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
						<logic:iterate id="arrUserDeskMenuTempVo" name="<%=OpdConfig.ARR_USER_DESK_MENU_TEMP_VO %>" type="hisglobal.vo.UserDeskMenuTemplateMasterVO">
							<tr>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=arrUserDeskMenuTempVo.getDeskMenuName() %>
									</div>
								</td>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=arrUserDeskMenuTempVo.getTemplateName() %>
									</div>
								</td>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=arrUserDeskMenuTempVo.getIsDefaultName() %>
									</div>
								</td>
								
								<td class="tdfont" width="10%" >
									<div align="center">
										<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=arrUserDeskMenuTempVo.getTemplateName() %>','<%=arrUserDeskMenuTempVo.getTemplateId() %>') ;" onclick=" deleteRow('<%=arrUserDeskMenuTempVo.getTemplateName() %>','<%=arrUserDeskMenuTempVo.getTemplateId() %>')">
										<html:hidden name="UserDeskMenuTempMapFB" property="templateName"/>
										<html:hidden name="UserDeskMenuTempMapFB" property="hiddenTemplateId"/>
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>	
				</his:ContentTag>
				
<%} %>
</his:statusRecordFound>
			
		
			</logic:equal>
			
	
			<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>	
				<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(validateAdd()) submitPage('SAVEBYUNITSEAT')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitPage('SAVEBYUNITSEAT')">
				</logic:equal>	
				
			</his:statusTransactionInProcess>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="goSelected()" onkeypress="if(event.keyCode==13) goSelected()">
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="getTemplates()" onkeypress="if(event.keyCode==13) getTemplates()">
			</logic:equal>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">			
			
			</his:ButtonToolBarTag>
						
			</his:TransactionContainer>
		
		<html:hidden name="UserDeskMenuTempMapFB" property="hmode"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="tempMode"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="additionMode"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="tempUnitCode"/>
	
		</html:form>
	</body>
<center><b><his:status/></b></center>
</html>