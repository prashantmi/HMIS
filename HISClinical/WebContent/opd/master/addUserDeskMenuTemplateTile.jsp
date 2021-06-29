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



function deptSelected(cboDept)
{
	var deptId=cboDept.value;
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
	}
}

function validateAddTemplateToMenuRow()
{
	if(document.getElementById('deskMenuList').value <= 0 )
	{
		alert("Select Desk Menu ... ");
		document.getElementById('deskMenuList').focus();
		return false;
	}
	if(document.getElementById('templateList').value <= 0 )
	{
		alert("Select Template ... ");
		document.getElementById('templateList').focus();
		return false;
	}
	if(document.getElementById('isDefault').value < 0 )
	{
		alert("Set Is Default Mode ... ");
		document.getElementById('isDefault').focus();
		return false;
	}
	return true;
}
function addTemplateToMenuRow()
{
	if(! validateAddTemplateToMenuRow()) return;
	var deskMenuId=document.getElementById('deskMenuList').value;
	var templateId=document.getElementById('templateList').value;
	var isDefaultMode=document.getElementById('isDefault').value;
	
	var data=document.getElementsByName('dataTemplateList')[0].value;
	var flag=false;
	
	if(data!="")
	{
		var rows=data.split("@");
		for(var i=0;i<rows.length;i++)
		{
			var rowdata=rows[i].split("#");
			if(rowdata[1].split("&")[0]==templateId)
			{
				alert("The Template is already added to '"+rowdata[0].split("&")[1]+"' Menu ... ");
				document.getElementById('deskMenuList').focus();
				return;
			}
		}
		flag=true;
		data+="@";
	}
	else flag=true;
	
	if(flag)
	{
		data+=deskMenuId+"&"+document.getElementById('deskMenuList').options[document.getElementById('deskMenuList').selectedIndex].text+"#";
		data+=templateId+"&"+document.getElementById('templateList').options[document.getElementById('templateList').selectedIndex].text+"#";
		data+=isDefaultMode+"&"+document.getElementById('isDefault').options[document.getElementById('isDefault').selectedIndex].text;
		document.getElementsByName('dataTemplateList')[0].value=data;
		document.getElementById('deskMenuList').value="-1";
		document.getElementById('templateList').value="-1";
		document.getElementById('isDefault').value="-1";
		setAddedTemplateRows();
	}
}

function subTemplateToMenuRow(d)
{
	var data=document.getElementsByName('dataTemplateList')[0].value;
	var rows=data.split("@");
	data="";
	for(var i=0;i<rows.length;i++)
		if(i!=(d-1)) data+=rows[i]+"@";
	if(data!="")data=data.substring(0,data.length-1);
	document.getElementsByName('dataTemplateList')[0].value=data;
	setAddedTemplateRows();
}

function setAddedTemplateRows()
{
	var h="";
	var data=document.getElementsByName('dataTemplateList')[0].value;
	if(data!="")
	{
		h+="<table  width='100%' border='0' cellspacing='0' cellpadding='0'>";
		var rows=data.split("@");
		for(var i=0;i<rows.length;i++)
		{
			var rowdata=rows[i].split("#");
			h+="<tr>";
			
			h+="<td width='30%' class='tdfont'>";
			h+="<div align='center'>";
			h+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
			h+=rowdata[0].split("&")[1];
			h+="</font>";
			h+="</div>";
			h+="</td>";
			h+="<td width='40%' class='tdfont'>";
			h+="<div align='center'>";
			h+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
			h+=rowdata[1].split("&")[1];
			h+="</font>";
			h+="</div>";
			h+="</td>";
			h+="<td width='20%' class='tdfont'>";
			h+="<div align='center'>";
			h+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
			h+=rowdata[2].split("&")[1];
			h+="</font>";
			h+="</div>";
			h+="</td>";
			h+="<td width='10%' class='tdfont'>";
			h+="<div align='center'>";
			if(document.getElementsByName('hmode')[0].value=="MODIFY")
				h+="<img class='button' src='/HIS/hisglobal/images/Mi_Green_Sqr.png' style='cursor:pointer' onclick ='subTemplateToMenuRow("+(i+1)+")' onkeypress='if(event.keyCode==13) subTemplateToMenuRow("+(i+1)+");'>";
			else
				h+="<img class='button' src='/HIS/hisglobal/images/Mi_Green_Sqr.png' style='cursor:pointer' onclick ='subTemplateToMenuRow("+(i+1)+")' onkeypress='if(event.keyCode==13) subTemplateToMenuRow("+(i+1)+");'>";
			h+="</div>";
			h+="</td>";
			
			h+="</tr>";
		}
		h+="</table>";
	}
	document.getElementById('templateAddList').innerHTML=h;
}
function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	//3 ->Wards
	
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

function MoveToSelected()
{
	// Select All Units in Selected
	if(document.forms[0].selectedUnits)
		for(var i=0;i<document.forms[0].selectedUnits.length;i++)
			document.forms[0].selectedUnits.options[i].selected=true;
	// Select All Wards in Selected
	if(document.forms[0].selectedWards)
		for(var i=0;i<document.forms[0].selectedWards.length;i++)
			document.forms[0].selectedWards.options[i].selected=true;
}

function goSelected()
{
	
	
	if(document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	submitPage("GETDESKSWITHOUTSEAT");
}

function goGetDesk()
{
	
	if(document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	submitPage("GETDESKSWITHOUTSEAT");
}

function goGetDeskForWardWise()
{
	if(document.forms[0].selectedWards.options.length==0)
	{
		alert("Choose at Least One Ward");
		document.forms[0].wardsList.focus();
		return;
	}
	// Select All Units in Selected
	//for(var i=0;i<document.forms[0].selectedUnits.length;i++)
		//document.forms[0].selectedUnits.options[i].selected=true;
	// Select All Seats in Selected
	for(var i=0;i<document.forms[0].selectedWards.length;i++)
		document.forms[0].selectedWards.options[i].selected=true;
	submitPage("GETDESKSFORWARDWISE");
}

function goGetDeskForWardSeatWise()
{
	if(document.forms[0].selectedSeats.options.length==0)
	{
		alert("Choose at Least One Seat");
		document.forms[0].seatsList.focus();
		return;
	}
	// Select All Units in Selected
	//for(var i=0;i<document.forms[0].selectedUnits.length;i++)
		//document.forms[0].selectedUnits.options[i].selected=true;
	// Select All Seats in Selected
	for(var i=0;i<document.forms[0].selectedSeats.length;i++)
		document.forms[0].selectedSeats.options[i].selected=true;
	submitPage("GETDESKSFORWARDSEATWISE");
}


function goGetWard()
{
	
	if(document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	submitPage("GETWARD");
}

function goGetWardForWardSeatWise()
{
	
	if(document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	submitPage("GETWARDFORWARDSEATWISE");
}

function goGetAllDesk()
{
	
	if(document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	submitPage("GETALLDESK");
}

function goGetDesks()
{
	if(document.forms[0].selectedSeats.options.length==0)
	{
		alert("Choose at Least One Seat");
		document.forms[0].unitsList.focus();
		return;
	}
	// Select All Units in Selected
	//for(var i=0;i<document.forms[0].selectedUnits.length;i++)
		//document.forms[0].selectedUnits.options[i].selected=true;
	// Select All Seats in Selected
	for(var i=0;i<document.forms[0].selectedSeats.length;i++)
		document.forms[0].selectedSeats.options[i].selected=true;
	submitPage("GETDESKS");
}

function goGetDeskMenuWithoutSeat()
{	
	
		submitPage("GETDESKMENU");

}

function goGetDeskMenuForWardWise()
{	
	
		submitPage("GETDESKMENUFORWARDWISE");

}

function goGetDeskMenuForWardSeatWise()
{	
	
		submitPage("GETDESKMENUFORWARDSEATWISE");

}

function goGetAllDeskMenu()
{	
	
		submitPage("GETALLDESKMENU");

}


function goGetDeskMenu()
{	
	
		submitPage("GETDESKMENUWITHSEAT");

}

function goGetSeats()
{
	
	if(document.forms[0].selectedUnits.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].unitsList.focus();
		return;
	}
	// Select All Units in Selected
	for(var i=0;i<document.forms[0].selectedUnits.length;i++)
		document.forms[0].selectedUnits.options[i].selected=true;
	submitPage("GETSEATS");
}

function goGetSeatsForWardSeatWise()
{
	
	if(document.forms[0].selectedWards.options.length==0)
	{
		alert("Choose at Least One Ward");
		document.forms[0].wardsList.focus();
		return;
	}
	// Select All Wards in Selected
	for(var i=0;i<document.forms[0].selectedWards.length;i++)
		document.forms[0].selectedWards.options[i].selected=true;
	submitPage("GETSEATSFORWARDSEATWISE");
}

function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	
	if(document.getElementsByName('dataTemplateList')[0].value=="")
	{
		alert("Add At One Template ... ");
		document.getElementById('deskMenuList').focus();
		return false;
	}
	return true;
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) 
	return;
	
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
</script>

	<body>
		<html:form action="/master/AddUserDeskMenuTemplateMaster.cnt">
			<html:hidden name="UserDeskMenuTemplateMasterFB" property="hmode"/>
			<html:hidden name="UserDeskMenuTemplateMasterFB" property="isGoPressed"/>
				
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

			<logic:notEqual name="UserDeskMenuTemplateMasterFB" property="hmode" value="MODIFY">
			<logic:notEqual name="UserDeskMenuTemplateMasterFB" property="hmode" value="VIEW">
			<his:TitleTag name="User Desk Template Master">
			</his:TitleTag>
			</logic:notEqual>
			</logic:notEqual>
				
			<his:ContentTag>			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0" style="display:none">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="aditionMode"/>
								</font>
							</div>
						</td>
						<td width="75%" colspan="3" class="tdfont">
							<div align="left">
								<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0%>">
								<bean:message key="unitWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								<bean:message key="unitSeatWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								<bean:message key="deskWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								<bean:message key="wardWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								<bean:message key="wardSeatWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								</logic:equal>
								<logic:notEqual name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0%>">
								<bean:message key="unitWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>" disabled="true"></html:radio>
								<bean:message key="unitSeatWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>" disabled="true"></html:radio>								
								<bean:message key="deskWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>" disabled="true"></html:radio>
								<bean:message key="wardWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>" disabled="true"></html:radio>
								<bean:message key="wardSeatWise"/>
								<html:radio name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>" disabled="true"></html:radio>								
								</logic:notEqual>
							</div>	
						</td>
					</tr>
				</table>
			
			</his:ContentTag>
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<his:SubTitleTag name="Select Department and Units">
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="deptId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
							<div align="center">
								<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE%>">
									<html:select name="UserDeskMenuTemplateMasterFB" property="unitsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_USERDESKUNITS_NOT_UNITTEMPLATE %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_USERDESKUNITS_NOT_UNITTEMPLATE%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>		
								</logic:equal>
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedUnits" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
					
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<his:SubTitleTag name="Select Department and Units">
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="deptId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
							<div align="center">
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
									<html:select name="UserDeskMenuTemplateMasterFB" property="unitsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_USERDESKUNITS_SEAT_NOTNULL %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_USERDESKUNITS_SEAT_NOTNULL%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>		
							</logic:equal>
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedUnits" multiple="true" size="6">
								</html:select>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				</logic:equal>
				</logic:equal>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
				<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>	
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
				<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>	
			
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
			<his:SubTitleTag name="Select Desk">
			</his:SubTitleTag>
					
			<% 
							 List deskWithoutUnits=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT);
						       int sizeOfDeskList=deskWithoutUnits.size();
						       if(sizeOfDeskList!=0){
							%>
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="deskId" styleClass="registrationCmb">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT %>">
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT%>" property="value" labelProperty="label" />
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
			<%} %>		
			</logic:equal>
		</logic:equal>
		
		
				
		
	<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
	<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1%>">
	
			<his:SubTitleTag name="Selected Units">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
								</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
	</logic:equal>
	</logic:equal>
	
	<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
	<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2%>">
	
			<his:SubTitleTag name="Selected Units">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
								</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
	</logic:equal>
	</logic:equal>
	
	<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
	<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3%>">
	
			<his:SubTitleTag name="Selected Units">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
								</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
	</logic:equal>
	</logic:equal>
				
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode"  value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="1">  
				<his:SubTitleTag name="Select Seats ">
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
									<html:select name="UserDeskMenuTemplateMasterFB" property="seatsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESK_SEATS %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_USER_DESK_SEATS%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="20%"  class="tdfont">
								<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
								</div>
							</td>
							<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedSeats" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						</tr>
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
						</tr>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>
			
					
	
<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>">
 
			<his:SubTitleTag name="Selected Seats">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotSeats" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
		</logic:equal>	
		</logic:equal>
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
 
			<his:SubTitleTag name="Selected Seats">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotSeats" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
		</logic:equal>	
		</logic:equal>
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
			<his:SubTitleTag name="Select Desk">
			</his:SubTitleTag>
					
			<% 
							   List deskWithSeats=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS);
						       int sizeOfDeskListWithSeat=deskWithSeats.size();
						       if(sizeOfDeskListWithSeat!=0){
			%>
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="deskId" styleClass="registrationCmb">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS %>">
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS%>" property="value" labelProperty="label" />
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
			<%} %>		
			</logic:equal>
		</logic:equal>

		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>">	
			<his:SubTitleTag name="Selected Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="deskId" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='UserDeskMenuTemplateMasterFB' property='deskName'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">	
			<his:SubTitleTag name="Selected Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="deskId" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='UserDeskMenuTemplateMasterFB' property='deskName'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
			
		
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
			
			<his:ContentTag>

			</his:ContentTag>
			</logic:equal>
		
		
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<his:SubTitleTag name="Select Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deskName"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="deskId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK %>">
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					
					</table>
				</his:ContentTag>
				</logic:equal>
				</logic:equal>
				
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">	
			<his:SubTitleTag name="Selected Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="deskId" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='UserDeskMenuTemplateMasterFB' property='deskName'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<his:SubTitleTag name="Select Department and Units">
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="deptId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
							<div align="center">
								<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE%>">
									<html:select name="UserDeskMenuTemplateMasterFB" property="unitsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDWSISE %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDWSISE%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>		
								</logic:equal>
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedUnits" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
				<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>	
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode"  value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="1">  
				<his:SubTitleTag name="Select Ward ">
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
									<html:select name="UserDeskMenuTemplateMasterFB" property="wardsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_WARDS_UNITWARDWISE %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_WARDS_UNITWARDWISE%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="20%"  class="tdfont">
								<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("3");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("3");'/> 	
								</div>
							</td>
							<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedWards" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						</tr>
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
						</tr>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
				<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>	
		
		
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
				<his:SubTitleTag name="Selected Wards">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>	
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
			<his:SubTitleTag name="Select Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deskName"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="deskId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS %>">
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					
					</table>
				</his:ContentTag>
				</logic:equal>
				</logic:equal>
		
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>">	
			
			<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			
			<his:SubTitleTag name="Selected Wards">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			
			<his:SubTitleTag name="Selected Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="deskId" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='UserDeskMenuTemplateMasterFB' property='deskName'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
			
		
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<his:SubTitleTag name="Select Department and Units">
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="deptId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
							<div align="center">
								<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE%>">
									<html:select name="UserDeskMenuTemplateMasterFB" property="unitsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDSEATWSISE %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDSEATWSISE%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>		
								</logic:equal>
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
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedUnits" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode"  value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="1">  
			
			
			
			<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Select Ward ">
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
									<html:select name="UserDeskMenuTemplateMasterFB" property="wardsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_WARDS_UNITWARDSEATWISE %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_WARDS_UNITWARDSEATWISE%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="20%"  class="tdfont">
								<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("3");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("3");'/> 	
								</div>
							</td>
							<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedWards" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						</tr>
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
						</tr>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>
		
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode"  value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="2">  
				
				<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Selected Wards">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Select Seats ">
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
									<html:select name="UserDeskMenuTemplateMasterFB" property="seatsList" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESK_SEATS %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_USER_DESK_SEATS%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="20%"  class="tdfont">
								<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
								</div>
							</td>
							<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="selectedSeats" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						</tr>
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
						</tr>
					</table>
				</his:ContentTag>
				</logic:equal>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>">
			
			<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Selected Wards">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
					<his:SubTitleTag name="Selected Seats">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotSeats" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
			
			<his:SubTitleTag name="Select Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deskName"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTemplateMasterFB" property="deskId" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS %>">
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					
					</table>
				</his:ContentTag>
				</logic:equal>
				</logic:equal>
		
		
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP4 %>">
			
			<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Selected Wards">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS%>" id="list">
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
				
					<his:SubTitleTag name="Selected Seats">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate name="<%=OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS%>" id="list">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotSeats" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='list' property='label'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
				<his:SubTitleTag name="Selected Desk">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="deskId" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='UserDeskMenuTemplateMasterFB' property='deskName'/></b>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			</logic:equal>
		
	<his:statusInProcessWithJsp>
			<%
				varIsNewStatus=true;
				varStatus="InProcess";
			%>
			
			<his:SubTitleTag name="Set Templates to Desk Menu">
			</his:SubTitleTag>

			<his:ContentTag>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr><td colspan="4" class="tdfonthead"></td></tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="center">	           
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="menuName"/></b>
								</font>
							</div>
	  					</td>
	  					<td width="40%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="tmplname"/></b>
								</font>
							</div>
						</td>
	  					<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="isdefault"/></b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead"></td>
					</tr>
					
					<tr><td colspan="100%">
						<html:hidden name="UserDeskMenuTemplateMasterFB" property="dataTemplateList" />
						<div id="templateAddList">
							<bean:write name="UserDeskMenuTemplateMasterFB" property="htmlTemplateList" filter="false"/>
						</div>
					</td></tr>
					<tr><td colspan="4" class="tdfont"></td></tr>

					<logic:notEqual name="UserDeskMenuTemplateMasterFB" property="hmode" value="VIEW">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<select id="deskMenuList" >
									<option value="-1" selected="selected"> Select Value</option>
									<logic:iterate name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST%>" id="list">
										<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
									</logic:iterate>
								</select>
							</div>
	  					</td>
	  					<td width="40%" class="tdfonthead">
							<div align="center">
								<select id="templateList" >
									<option value="-1" selected="selected" > Select Value</option>
									<logic:iterate name="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES%>" id="list">
										<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
									</logic:iterate>
								</select>
							</div>
						</td>
	  					<td width="20%" class="tdfonthead">
							<div align="center">
								<select id="isDefault">
									<option value="0" >False</option>
									<option value="1" >True</option>
								</select>
							</div>
						</td>
						<td width="10%" class="tdfonthead">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="addTemplateToMenuRow()" onkeypress="if(event.keyCode==13) addTemplateToMenuRow();">
							</div>
						</td>
					</tr>
					</logic:notEqual>
					<tr><td colspan="4" class="tdfonthead"></td></tr>
				</table>
			</his:ContentTag>
			</his:statusInProcessWithJsp>
			<his:ButtonToolBarTag>
						
						<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
						
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>" >
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetSeats();" onclick="goGetSeats();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>" >
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDesks();" onclick="goGetDesks();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>" >
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDeskMenu();" onclick="goGetDeskMenu();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>" >
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
						</logic:equal>
								
						</logic:equal>
						
						<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="0">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDesk();" onclick="goGetDesk();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="1">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDeskMenuWithoutSeat();" onclick="goGetDeskMenuWithoutSeat();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>" >
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
						
						</logic:equal>
					</logic:equal>
						
		<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>">
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="0">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetAllDesk();" onclick="goGetAllDeskMenu();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>" >
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
						
							</logic:equal>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="0">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetWard();" onclick="goGetWard();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="1">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDeskForWardWise();" onclick="goGetDeskForWardWise();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>" >
							<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDeskMenuForWardWise();" onclick="goGetDeskMenuForWardWise();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>" >
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVEWARDWISE')" onclick="finalSubmit('SAVEWARDWISE')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
					</logic:equal>
					
					<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="0">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetWardForWardSeatWise();" onclick="goGetWardForWardSeatWise();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="1">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetSeatsForWardSeatWise();" onclick="goGetSeatsForWardSeatWise();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>" >
							<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDeskForWardSeatWise();" onclick="goGetDeskForWardSeatWise();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP3 %>" >
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) goGetDeskMenuForWardSeatWise();" onclick="goGetDeskMenuForWardSeatWise();">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
							<logic:equal name="UserDeskMenuTemplateMasterFB" property="isGoPressed" value="<%=OpdConfig.STEP4 %>" >
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVEWARDWISE')" onclick="finalSubmit('SAVEWARDWISE')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
							</logic:equal>
					</logic:equal>
					
					
			
		
				
				</his:ButtonToolBarTag>
			</his:TransactionContainer>
		</html:form>
		<center><b><his:status/></b></center>
	</body>
</html>