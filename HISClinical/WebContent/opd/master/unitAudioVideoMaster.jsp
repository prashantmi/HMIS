<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*,hisglobal.utility.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title>Unit Wise Audio Video Master</title>
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
function submitTile(mode)
{	
	if(document.getElementsByName("hmode")[0].value!="VIEW")
	{
		
		MoveToSelectedAVFile();
	}	
	document.getElementsByName("hmode")[0].value=mode;
	
	document.forms[0].submit();
}

function moveRightSingle()
{
	var source;
	var target;

	
		source  = document.forms[0].AVfileCode;
		target = document.forms[0].selectedAVFileCode;	
	
	
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
	
		source  = document.forms[0].AVfileCode;
		target = document.forms[0].selectedAVFileCode;	
	

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

		target  = document.forms[0].AVfileCode;
		source = document.forms[0].selectedAVFileCode;	


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

	
		target  = document.forms[0].AVfileCode;
		source = document.forms[0].selectedAVFileCode;	
	
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
	// Unselect Units
	if(document.getElementsByName("unitsList")[0])	
		for(var i=0;i<document.getElementsByName("unitsList")[0].options.length;i++)
			document.getElementsByName("unitsList")[0].options[i].selected=false;
	
	// Select Units Selected
	if(document.getElementsByName("selectedUnit")[0])
		for(var i=0;i<document.getElementsByName("selectedUnit")[0].options.length;i++)
			document.getElementsByName("selectedUnit")[0].options[i].selected=true;
			
	// Unselect Remaining AVfileCode
	if(document.getElementsByName("AVfileCode")[0])
		for(var i=0;i<document.getElementsByName("AVfileCode")[0].options.length;i++)
			document.getElementsByName("AVfileCode")[0].options[i].selected=false;
	
	// Select All File in Selected
	if(document.getElementsByName("selectedAVFileCode")[0])
		for(var i=0;i<document.getElementsByName("selectedAVFileCode")[0].options.length;i++)
			document.getElementsByName("selectedAVFileCode")[0].options[i].selected=true;
}

function validateAdd()
{
	if(document.forms[0].selectedUnit.options.length==0)
	{
		alert("Choose at Least One Unit");
		document.forms[0].selectedUnit.focus();
		return false;
	}
	else if (document.getElementsByName("selectedAVFileCode")[0].options.length==0)
	{
		alert("Choose at Least One Audio Video File");
		document.getElementsByName("AVfileCode").focus();
	}
	else
	submitTile('SAVE');
}

function validateModify()
{	
	
	if (document.getElementsByName("selectedAVFileCode")[0].options.length==0)
	{
		alert("Choose at Least One Audio Video File");
		document.getElementsByName("AVfileCode").focus();
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
		<html:form action="/master/UnitAudioVideoMaster">
			<his:TransactionContainer>
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="ADD">
					<his:TitleTag name="Add Audio Video File To Unit">
					</his:TitleTag>
				</logic:equal>
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="MODIFY">
					<his:TitleTag name="Modify Audio Video File Unit Wise">
					</his:TitleTag>
				</logic:equal>
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="VIEW">
					<his:TitleTag name="View Audio Video File Unit Wise">
					</his:TitleTag>
				</logic:equal>
				<his:ContentTag>
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="MODIFY">
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
								&nbsp;<bean:write name="UnitAudioVideoMasterFB" property="unitName" />
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					</table>
				</logic:equal>
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="VIEW">
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
								&nbsp;<bean:write name="UnitAudioVideoMasterFB" property="unitName" />
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont"></td>
					</tr>
					</table>
				</logic:equal>
				
				
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="ADD">
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
								<html:select name="UnitAudioVideoMasterFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
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
								<html:select name="UnitAudioVideoMasterFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.ESSENTIAL_BO_LIST_ALL_UNIT_NOT_IN_HOPT_UNIT_PLAYERFILE_MST%>" >
										<html:options collection="<%=OpdConfig.ESSENTIAL_BO_LIST_ALL_UNIT_NOT_IN_HOPT_UNIT_PLAYERFILE_MST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UnitAudioVideoMasterFB" property="unitsList" multiple="true" size="6">
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
								<html:select name="UnitAudioVideoMasterFB" property="selectedUnit" multiple="true" size="6">
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
					</logic:equal>
				</his:ContentTag>
				
				<his:ContentTag>
				<logic:notEqual name="UnitAudioVideoMasterFB" property="hmode" value="VIEW">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="allAudioVideoFile"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedAudioVideoFile"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
						<div align="center">
						<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="ADD">
							<html:select name="UnitAudioVideoMasterFB" property="AVfileCode" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.ESSENTIAL_BO_LIST_ALL_AUDIO_VIDEO_FILE%>" >
								<html:options collection="<%=OpdConfig.ESSENTIAL_BO_LIST_ALL_AUDIO_VIDEO_FILE %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</logic:equal>	
						<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="MODIFY">
							<html:select name="UnitAudioVideoMasterFB" property="AVfileCode" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.ESSENTIAL_BO_LIST_ALL_AV_FILE_NOT_IN_SELECTED_BASED_ON_UNIT%>" >
								<html:options collection="<%=OpdConfig.ESSENTIAL_BO_LIST_ALL_AV_FILE_NOT_IN_SELECTED_BASED_ON_UNIT %>" property="value" labelProperty="label" />
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
						<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="ADD">
							<html:select name="UnitAudioVideoMasterFB" property="selectedAVFileCode" multiple="true" size="6">
							</html:select>
						</logic:equal>	
						<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="MODIFY">
							<html:select name="UnitAudioVideoMasterFB" property="selectedAVFileCode" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST%>" >
								<html:options collection="<%=OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST %>" property="value" labelProperty="label" />
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
					<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="VIEW">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="50%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="audioVideoFile" />
											</b>
										</font>
									</div>
								</td>
								<td width="50%" class="tdfont"></td>
							</tr>
							
								<%  List lstAVFile=(List)session.getAttribute(OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST);
									Iterator itr=lstAVFile.iterator();
									while(itr.hasNext())
									{
										Entry entFile=(Entry) itr.next();
									
								 %>
							 <tr>
								<td width="50%" class="tdfonthead"></td>
								<td width="50%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=entFile.getLabel()  %>
					  					</font>
									</div>
								</td>
							</tr>
							<%} %>
							
						</table>
					
					</logic:equal>
					
				</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd() && submitTile('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitTile('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('ADD')" onkeypress="if(event.keyCode==13) submitTile('ADD');">
				</logic:equal>	
				<logic:equal name="UnitAudioVideoMasterFB" property="hmode" value="MODIFY">	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitTile('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitTile('MODIFYSAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearModify()" onkeypress="if(event.keyCode==13) clearModify();">
				</logic:equal>	
				
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
				
				</his:ButtonToolBarTag>
				
				<html:hidden name="UnitAudioVideoMasterFB" property="hmode"/>
				<html:hidden name="UnitAudioVideoMasterFB" property="unitName"/>
				<html:hidden name="UnitAudioVideoMasterFB" property="unitCode"/>
				<html:hidden name="UnitAudioVideoMasterFB" property="tempMode"/>
				
				<div id="status" align="left" style="display:none;" >
				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										No units Found
									</b>	
								</font>
		</div>
			</his:TransactionContainer>
		<his:status/>
		</html:form>
	</body>
</html>