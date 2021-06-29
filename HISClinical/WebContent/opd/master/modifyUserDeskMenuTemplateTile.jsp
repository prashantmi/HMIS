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

function goGetSeats()
{
	if(document.forms[0].deptId.value=="-1")
	{
		alert("Select Department ...")
		document.forms[0].deptId.focus();
		return;
	}
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

function goGetDeskMenu()
{
	if(document.forms[0].deskId.value=="-1")
	{
		alert("Select Desk ... ");
		document.forms[0].deskId.focus();
		return;
	}
	// Select All Units in Selected
	//for(var i=0;i<document.forms[0].selectedUnits.length;i++)
		//document.forms[0].selectedUnits.options[i].selected=true;
	// Select All Seats in Selected
	//for(var i=0;i<document.forms[0].selectedSeats.length;i++)
		//document.forms[0].selectedSeats.options[i].selected=true;
	submitPage("GETDESKMENU");
}

function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
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
		for(var i=0;i<document.forms[0].seatsList.length;i++)
			document.forms[0].seatsList.options[i].selected=false;
	// Unselect Remaining Units
	if(document.forms[0].unitsList)
		for(var i=0;i<document.forms[0].unitsList.length;i++)
			document.forms[0].unitsList.options[i].selected=false;
	// Select All Seats in Selected
	if(document.forms[0].selectedSeats)
		for(var i=0;i<document.forms[0].selectedSeats.length;i++)
			document.forms[0].selectedSeats.options[i].selected=true;
	// Select All Units in Selected
	if(document.forms[0].selectedUnits)
		for(var i=0;i<document.forms[0].selectedUnits.length;i++)
			document.forms[0].selectedUnits.options[i].selected=true;
}

function validateFinalSubmit()
{
	if(document.forms[0].selectedSeats && document.forms[0].selectedSeats.length==0)
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
	if (!validateFinalSubmit()) return;
	submitPage(mode);
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

</script>



<%@ page import ="registration.*,opd.*" %>

	<body >
		<html:form action="/master/ModifyViewUserDeskMenuTemplateMaster.cnt">
			<html:hidden  name="UserDeskMenuTemplateMasterFB" property="hmode"/>

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

			<logic:equal name="UserDeskMenuTemplateMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="MODIFY TEMPLATES LIST ADDED TO DESK MENUs">
				</his:TitleTag>
			</logic:equal>

			<logic:equal name="UserDeskMenuTemplateMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="VIEW TEMPLATES LIST ADDED TO DESK MENUs">
				</his:TitleTag>
			</logic:equal>
			
			<his:statusInProcessWithJsp>
			<%
				varIsNewStatus=true;
				varStatus="InProcess";
			%>
		<logic:notEqual name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>">
		<his:SubTitleTag name="Selected Units">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					
						<tr>
							<td width="30%"  class="tdfonthead"></td>
							<td width="40%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="UserDeskMenuTemplateMasterFB" property="unitName" /></b>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="unitName"/>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="deptUnitCode"/>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="slNo"/>
									</font>
								</div>
							</td>
							<td width="30%"  class="tdfonthead"></td>
						</tr>
					
					</table>
				</his:ContentTag>
				<logic:equal name="UserDeskMenuTemplateMasterFB" property="gotSeats" value="1">
				 <his:SubTitleTag name="Selected Seats">
		     	 </his:SubTitleTag>
		     	 <his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotSeats" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="UserDeskMenuTemplateMasterFB" property="seatName"/></b>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="seatName"/>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="userSeatId"/>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				
				</table>
			</his:ContentTag>
		     	</logic:equal>
		     	</logic:notEqual>
			
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>" >
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="gotWards" value="1">
				 <his:SubTitleTag name="Selected Wards">
		     	 </his:SubTitleTag>
		     	 <his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotWards" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="UserDeskMenuTemplateMasterFB" property="wardName"/></b>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="wardName"/>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="wardId"/>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				
				</table>
			</his:ContentTag>
		     	</logic:equal>
		     	</logic:equal>
		     	
		     	<logic:equal name="UserDeskMenuTemplateMasterFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>" >
			<logic:equal name="UserDeskMenuTemplateMasterFB" property="gotWards" value="1">
				 <his:SubTitleTag name="Selected Wards">
		     	 </his:SubTitleTag>
		     	 <his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="30%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:hidden name="UserDeskMenuTemplateMasterFB" property="gotWards" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="UserDeskMenuTemplateMasterFB" property="wardName"/></b>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="wardName"/>
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="wardId"/>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				
				</table>
			</his:ContentTag>
		     	</logic:equal>
		     	</logic:equal>
			
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
										<html:hidden  name="UserDeskMenuTemplateMasterFB" property="deskName"/>
									</font>
							</div>
						</td>
						<td width="30%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>	
			
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
				<span id="saveDiv">
					<%if(varStatus.equals("InProcess")){%>
						<logic:equal name="UserDeskMenuTemplateMasterFB" property="hmode" value="MODIFY">
							<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
						</logic:equal>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
						<%} 
							%>
				</span>
			</his:ButtonToolBarTag>	
			</his:TransactionContainer>
			
		</html:form>
	</body>
</html>