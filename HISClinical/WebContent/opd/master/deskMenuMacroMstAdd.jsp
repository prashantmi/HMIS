
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
<his:css src="/css/calendar-blue2.css"/>

<script>
function submitTile(mode)
{
val=document.getElementsByName("hmode")[0].value;
if(val=="ADD")
{
MoveToSelected();
}
   document.getElementsByName("mode")[0].value=mode;
   document.getElementsByName("hmode")[0].value="GETMACROHEAD";
   //alert("mode"+document.getElementsByName("mode")[0].value);
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

function MoveToSelected()
{
	
	// Unselect Remaining Units
	if(document.forms[0].unitsList)
	{	
		for(var i=0;i<document.forms[0].unitsList.length;i++)
			document.forms[0].unitsList.options[i].selected=false;
	}

	
	// Select All Units in Selected
	for(var i=0;i<document.forms[0].selectedUnits.length;i++)
		document.forms[0].selectedUnits.options[i].selected=true;
}
function clearValue()
{
	document.getElementsByName("macroHead")[0].value="";
	document.getElementsByName("macroDesc")[0].value="";
}

function validateModify()
{
	if(document.getElementsByName("macroHead")[0].value=="")
	{
		alert("Enter the Macro Head");
	}
	else if(document.getElementsByName("macroDesc")[0].value=="")
	{
		alert("Enter the Macro Desc");
	}
	else
	submitTile("MODIFYSAVE");
}

function validateAdd()
{
	var length;
	len=document.getElementsByName("macroDesc")[0].value;
	length=len.length;
	
	
	if(document.getElementsByName("macroHead")[0].value=="")
	{
		alert("Enter the Macro Head");
		document.getElementsByName("macroHead")[0].focus();
	}
	else if(document.getElementsByName("macroDesc")[0].value=="")
	{
		alert("Enter the Macro Desc");
		document.getElementsByName("macroDesc")[0].focus();
	}
	else if(length>500)
	{
		alert("you can't enter more than 500 characters in Macro Description");
		document.getElementsByName("macroDesc")[0].focus();
	} 
	
	else if (document.getElementsByName("selectedUnits")[0].options.length==0)
	{
		alert("Choose at Least One Unit");
		document.getElementsByName("unitsList").focus();
	}
	
	
	else 
	submitTile("SAVE");
	
}

</script>


<body>

<html:form action="/master/deskMenuMacroMstAdd">

<his:TransactionContainer>



<logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="ADD">
<his:TitleTag name="Desk Menu Macro Master >> Add">

</his:TitleTag>
</logic:equal>
<logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="MOD">
<his:TitleTag name="Desk Menu Macro Master >> Modify">
</his:TitleTag>
</logic:equal>

	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead"></td>
			<td width="50%" class="tdfonthead"></td>
		</tr>
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
			<td width="50%" class="tdfonthead">
				<div align="left">
					<b>&nbsp;					
						<bean:write name="DeskMenuMacroMstAddFB" property="deskName"/>
						<html:hidden name="DeskMenuMacroMstAddFB" property="deskName"/>
						<html:hidden name="DeskMenuMacroMstAddFB" property="deskType"/>
					</b>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="deskMenu"/>
						</b>
					</font>
				</div>
			</td>
			<td width="50%" class="tdfonthead">
				<div align="left">
					<b>&nbsp;
						<bean:write name="DeskMenuMacroMstAddFB" property="deskMenu"/>
						<html:hidden name="DeskMenuMacroMstAddFB" property="deskMenu"/>
						<html:hidden name="DeskMenuMacroMstAddFB" property="deskMenuID"/>
					</b>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead"></td>
			<td width="50%" class="tdfonthead"></td>
		</tr>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		<logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="ADD">
			<tr>
				<td width="40%"  class="tdfonthead"></td>
				<td width="20%"  class="tdfont"></td>
				<td width="40%"  class="tdfonthead"></td>
			</tr>
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="center">
						<html:select name="DeskMenuMacroMstAddFB" property="unitsList" multiple="true" size="6">
							<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS%>" >
							<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS %>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				<td width="20%"  class="tdfont">
					<div align="center">
						<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
						<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("2");'/> 	
						<br><br>
						<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
						<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("2");'/>
					</div>
				</td>
				<td width="40%"  class="tdfonthead">
					<div align="center">
						<html:select name="DeskMenuMacroMstAddFB" property="selectedUnits" multiple="true" size="6">
						</html:select>
					</div>
				</td>
			</tr>
			<tr>
				<td width="40%"  class="tdfonthead"></td>
				<td width="20%"  class="tdfont"></td>
				<td width="40%" class="tdfonthead"></td>
			</tr>
		</logic:equal>
		<logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="MOD">
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="unit"/>
								<bean:message key="name"/>
							</b>
						</font>
					</div>
				</td>
				
				<td width="50%" class="tdfonthead">
					<div align="left">
						<b>&nbsp;
							<bean:write name="DeskMenuMacroMstAddFB" property="deptUnitName"/>
						</b>	
					</div>	
				</td>
			</tr>
		</logic:equal>	
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="50%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="macroHead"/>
							</b>
						</font>
					</div>
				</td>
				<td width="50%" class="tdfonthead">
					<div align="left">
						<html:text name="DeskMenuMacroMstAddFB" property="macroHead" maxlength="50" size="35" onkeypress="return validateAlphaNumericOnly(event)">
						</html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="macroDesc"/>
							</b>
						</font>
					</div>
				</td>
				<td width="50%" class="tdfonthead">
					<div align="left">
						<html:textarea name="DeskMenuMacroMstAddFB" property="macroDesc" cols="32" rows="3">
						</html:textarea>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	
	
	
	
	<his:ButtonToolBarTag>
	<logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="ADD">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateAdd() && submitTile('SAVE')" onkeypress="if(event.keyCode==13) validateAdd() && submitTile('SAVE')">
    </logic:equal>	
    
    <logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="MOD">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateModify() && submitTile('MODIFYSAVE');" onkeypress="if(event.keyCode==13) validateModify() && submitTile('MODIFYSAVE')">
    </logic:equal>	
    
    
    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    
    <logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="ADD">	
    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
    </logic:equal>
    
    <logic:equal name="DeskMenuMacroMstAddFB" property="hmode" value="MOD">
    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearValue()" onkeypress="clearValue();">
    </logic:equal>
		
	</his:ButtonToolBarTag>
	
	<html:hidden  name="DeskMenuMacroMstAddFB" property="mode"/>
	<html:hidden  name="DeskMenuMacroMstAddFB" property="hmode"/>
	<html:hidden  name="DeskMenuMacroMstAddFB" property="chk"/>
	
</his:TransactionContainer>
</html:form>
</body>

</html>