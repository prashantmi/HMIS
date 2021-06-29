<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />


<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
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


function submitPage(mode)
{	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function submitMyForm(flag,mode)
{
	if(flag)
	{
		document.getElementsByName('hmode')[0].value=mode;
		submitFormOnValidate(flag,mode);
	}
}

function validateSaveForm()
{
	if(document.forms[0].selectedUnit.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].selectedUnit.focus();
		return false;
	}
	
	if(document.forms[0].selectedImages.options.length==0)
	{
		alert("Choose at Least One Image");
		document.forms[0].selectedImages.focus();
		return false;
	}
	MoveToSelected();
	return true;
}

function MoveToSelected()
{
	// Unselect Remaining Images
	for(var i=0;i<document.getElementsByName("unselectedImages")[0].length;i++)
		document.getElementsByName("unselectedImages")[0].options[i].selected=false;
	
	// Select All Image in Selected
	for(var i=0;i<document.getElementsByName("selectedImages")[0].length;i++)
		document.getElementsByName("selectedImages")[0].options[i].selected=true;
		
	// Unselect Remaining Units
	for(var i=0;i<document.getElementsByName("unitsList")[0].length;i++)
		document.getElementsByName("unitsList")[0].options[i].selected=false;
	
	// Select All Image in Selected
	for(var i=0;i<document.getElementsByName("selectedUnit")[0].length;i++)
		document.getElementsByName("selectedUnit")[0].options[i].selected=true;
}

function moveRightSingle()
{
	var source;
	var target;

	
	source  = document.forms[0].unselectedImages;
	target = document.forms[0].selectedImages;	
	
	

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


function moveLeftSingle()
{
	var source;
	var target;

		target  = document.forms[0].unselectedImages;
		source = document.forms[0].selectedImages;	


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

	
		target  = document.forms[0].unselectedImages;
		source = document.forms[0].selectedImages;	
	

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


function moveRightAll()
{
	var source;
	var target;
	
	
		source  = document.forms[0].unselectedImages;
		target = document.forms[0].selectedImages;	
	

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







function MoveToSelectedUnits()
{
	// Unselect Remaining Images
	for(var i=0;i<document.getElementsByName("unitsList")[0].length;i++)
		document.getElementsByName("unitsList")[0].options[i].selected=false;
	
	// Select All Image in Selected
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

<%@ page import="registration.*,opd.*"%>

<body>
	<html:form action="/master/AddOPDUnitImageMaster.cnt">
		<html:hidden name="OPDUnitImageMasterFB" property="hmode" />

		<his:TransactionContainer>
			<his:TitleTag name="ADD Images To Department Units">
			</his:TitleTag>
	
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
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
								<html:select name="OPDUnitImageMasterFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					<tr>
						
						<td width="35%"  class="tdfonthead">
							<div align="center" style="display: none;" >
								<html:select name="OPDUnitImageMasterFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST%>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="OPDUnitImageMasterFB" property="unitsList" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingleUnits();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAllUnits();'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingleUnits();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAllUnits();'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="OPDUnitImageMasterFB" property="selectedUnit" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="35%" class="tdfonthead"></td>
						<td width="15%" class="tdfonthead"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			
				</his:ContentTag>
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<html:select name="OPDUnitImageMasterFB" property="unselectedImages" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_ALL_IMAGE_LIST %>">
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_ALL_IMAGE_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveRightSingle();' />
								<img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveRightAll();' />
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveLeftSingle();' />
								<img src="/HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveLeftAll();' />
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<html:select name="OPDUnitImageMasterFB" property="selectedImages" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
	
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitMyForm(validateSaveForm(),'SAVE')" onclick="submitMyForm(validateSaveForm(),'SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="MoveToSelected(); submitPage('CANCEL');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitPage('CANCEL');}">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
				</span>
			</his:ButtonToolBarTag>
			<div id="status" align="left" style="display:none;" >
		<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										No units Found
									</b>	
								</font>
		</div>
		</his:TransactionContainer>
	</html:form>
	<center><b><his:status/></b></center>
</body>
</html>