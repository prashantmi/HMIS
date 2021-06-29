<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>



<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>

function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;  
	document.forms[0].submit();
}

function validateAddColorDescToMenuRow()
{
	if(document.getElementById('color').value == -1)
	{
		alert("Select Color");
		document.getElementById('color').focus();
		return false;
	}
	if(document.getElementById('imageDescList').value <=0)
	{
		alert('Select Image Description');
		document.getElementById('imageDescList').focus();
		return false;
	}
	return true;
}

function MoveToSelected()
{
	for(var i=0;i<document.getElementsByName("imageDescCode")[0].length;i++)
		document.getElementsByName("imageDescCode")[0].options[i].selected=false;
	
	
	for(var i=0;i<document.getElementsByName("selectedImageDescCode")[0].length;i++)
		document.getElementsByName("selectedImageDescCode")[0].options[i].selected=true;
}

function validateAdd()
{
	if(document.forms[0].selectedUnit.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].selectedUnit.focus();
		return false;
	}
	else if (document.getElementsByName("selectedImageDescCode")[0].options.length==0)
	{
		alert("Choose at Least One Image Description");
		document.getElementsByName("selectedImageDescCode").focus();
	}
	else
	{
		MoveToSelected();
		MoveToSelectedUnits();
		submitTile('SAVE');
	}
}
function finalSubmit(mode)
{
	if(!validateAdd()) return;
	submitTile(mode);
}

function finalSubmitForModify(mode)
{
	if(document.getElementsByName("selectedImageDescCode")[0].options.length==0)
	{
		alert("Choose at Least One Image Description");
		document.getElementsByName("selectedImageDescCode").focus();
		return false;
	}
	MoveToSelected();
	submitTile(mode);
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



function MoveToSelectedUnits()
{
	 
	for(var i=0;i<document.getElementsByName("unitsList")[0].length;i++)
		document.getElementsByName("unitsList")[0].options[i].selected=false;
	
	
	for(var i=0;i<document.getElementsByName("selectedUnit")[0].length;i++)
		document.getElementsByName("selectedUnit")[0].options[i].selected=true;
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

function moveRightSingle()
{
	var source;
	var target;

	
		source  = document.forms[0].imageDescCode;
		target = document.forms[0].selectedImageDescCode;	
	
	
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

function moveRightAll()
{
	var source;
	var target;
	
		source  = document.forms[0].imageDescCode;
		target = document.forms[0].selectedImageDescCode;	
	

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


function moveLeftSingle()
{
	var source;
	var target;

		target  = document.forms[0].imageDescCode;
		source = document.forms[0].selectedImageDescCode;	


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

function moveLeftAll()
{
	var source;
	var target;

	
		target  = document.forms[0].imageDescCode;
		source = document.forms[0].selectedImageDescCode;	
	
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

</script>

<body >
	<html:form action="/master/addUnitImageDescMaster" >
		<his:TransactionContainer>
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Unit Image Description Master >> Add">
				</his:TitleTag>
			</logic:equal>
			
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Unit Image Description Master >> Modify">
				</his:TitleTag>
			</logic:equal>
			
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Unit Image Description Master >> View">
				</his:TitleTag>
			</logic:equal>
			
			
			<his:ContentTag>
			
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="ADD">		
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
							<html:select name="UnitImageDescMasterFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
								<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" property="value" labelProperty="label" />
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
							<html:select name="UnitImageDescMasterFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
								<logic:present name="<%=OpdConfig.LIST_UNITS_NOTIN_TABLE%>" >
									<html:options collection="<%=OpdConfig.LIST_UNITS_NOTIN_TABLE%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
						<div align="center">
							<html:select name="UnitImageDescMasterFB" property="unitsList" multiple="true" size="6">
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
							<html:select name="UnitImageDescMasterFB" property="selectedUnit" multiple="true" size="6">
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
			<logic:notEqual name="UnitImageDescMasterFB" property="hmode" value="ADD">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">	
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<b>&nbsp;
									<bean:write name="UnitImageDescMasterFB" property="unitName"/>
								</b>
							</div>
						</td>
					</tr>
				</table>		
			</logic:notEqual>	
			</his:ContentTag>
			<his:ContentTag>
			<logic:notEqual name="UnitImageDescMasterFB" property="hmode" value="VIEW">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="allImageDescription"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedImageDescription"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<logic:equal name="UnitImageDescMasterFB" property="hmode" value="ADD">
									<html:select name="UnitImageDescMasterFB" property="imageDescCode" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.ESSENTIAL_BO_OPTION_LIST_ALL_IMAGE_DESC%>" >
										<html:options collection="<%=OpdConfig.ESSENTIAL_BO_OPTION_LIST_ALL_IMAGE_DESC %>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</logic:equal>	
								<logic:equal name="UnitImageDescMasterFB" property="hmode" value="MODIFY">
									<html:select name="UnitImageDescMasterFB" property="imageDescCode" multiple="true" size="6">
										<logic:present name="<%=OpdConfig.LIST_ALL_IMAGE_DESC_NOT_IN_SELECTED_BASED_ON_UNIT%>" >
										<html:options collection="<%=OpdConfig.LIST_ALL_IMAGE_DESC_NOT_IN_SELECTED_BASED_ON_UNIT %>" property="value" labelProperty="label" />
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
							<logic:equal name="UnitImageDescMasterFB" property="hmode" value="ADD">
								<html:select name="UnitImageDescMasterFB" property="selectedImageDescCode" multiple="true" size="6">
								</html:select>
							</logic:equal>	
							<logic:equal name="UnitImageDescMasterFB" property="hmode" value="MODIFY">
								<html:select name="UnitImageDescMasterFB" property="selectedImageDescCode" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.MAPPED_IMAGE_DESC_LIST%>" >
										<html:options collection="<%=OpdConfig.MAPPED_IMAGE_DESC_LIST %>" property="value" labelProperty="label" />
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
				</logic:notEqual>
				
				<logic:equal name="UnitImageDescMasterFB" property="hmode" value="VIEW">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="selectedImageDescription" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont"></td>
						</tr>
						<logic:present name="<%=OpdConfig.MAPPED_IMAGE_DESC_LIST%>" >
						<logic:iterate id="entryObj" indexId="idx" name="<%=OpdConfig.MAPPED_IMAGE_DESC_LIST%>" type="hisglobal.utility.Entry">
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
					<html:hidden name="UnitImageDescMasterFB" property="selectedImageDescCode" />
				</logic:equal>
			</his:ContentTag>
			
						
			<his:ButtonToolBarTag>
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="finalSubmit('SAVE')" onkeypress="if(event.keyCode==13)finalSubmit('SAVE')">
			</logic:equal>
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="finalSubmitForModify('MODIFYSAVE')" onkeypress="if(event.keyCode==13)finalSubmitForModify('MODIFYSAVE')">
			</logic:equal>
				
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			<logic:equal name="UnitImageDescMasterFB" property="hmode" value="ADD">	
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('ADD')" onkeypress="if(event.keyCode==13) submitTile('ADD');">
			</logic:equal>	
			</his:ButtonToolBarTag>
			
			<center><b><his:status/></b></center>
			
			<div id="status" align="left" style="display:none;" >
				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						No Units Found
					</b>	
				</font>
			</div>
	</his:TransactionContainer>
	
		<html:hidden name="UnitImageDescMasterFB" property="hmode"/>
		<html:hidden name="UnitImageDescMasterFB" property="tempMode"/>
		<html:hidden name="UnitImageDescMasterFB" property="unitCode"/>
		<html:hidden name="UnitImageDescMasterFB" property="unitName"/>

	</html:form>

</body>
</html>