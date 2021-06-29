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

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
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
	
	if(document.forms[0].selectedKeyword.options.length==0)
	{
		alert("Choose at Least One Keyword");
		document.forms[0].selectedKeyword.focus();
		return false;
	}
	MoveToSelected();
	return true;
}

function MoveToSelected()
{
	// Unselect Remaining Keywords
	for(var i=0;i<document.getElementsByName("unselectedKeyword")[0].length;i++)
		document.getElementsByName("unselectedKeyword")[0].options[i].selected=false;
	
	// Select All Keywords in Selected
	for(var i=0;i<document.getElementsByName("selectedKeyword")[0].length;i++)
		document.getElementsByName("selectedKeyword")[0].options[i].selected=true;
		
	// Unselect Units
	for(var i=0;i<document.getElementsByName("unitsList")[0].length;i++)
		document.getElementsByName("unitsList")[0].options[i].selected=false;
	
	// Select All Units in Selected
	for(var i=0;i<document.getElementsByName("selectedUnit")[0].length;i++)
		document.getElementsByName("selectedUnit")[0].options[i].selected=true;
}
</script>

<%@ page import="opd.*"%>

<body>
	<html:form action="/master/AddOPDUnitKeywordMaster.cnt">
		<html:hidden name="UnitEpisodeKeywordFB" property="hmode" />

		<his:TransactionContainer>
			<his:TitleTag name="ADD Keywords To Department Units">
			</his:TitleTag>
	
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="deptName"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="center">
								<html:select name="UnitEpisodeKeywordFB" property="deptCode" onkeypress="if(event.keyCOde==13)deptSelected(this);" onchange="deptSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;&nbsp;<bean:message key="sel"/> <bean:message key="unit"/>&nbsp;&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					<tr>
						
						<td width="40%"  class="tdfonthead">
							<div align="center" style="display: none;" >
								<html:select name="UnitEpisodeKeywordFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST%>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UnitEpisodeKeywordFB" property="unitsList" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveSingle(document.forms[0].unitsList,document.forms[0].selectedUnit);'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveAll(document.forms[0].unitsList,document.forms[0].selectedUnit);'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveSingle(document.forms[0].selectedUnit,document.forms[0].unitsList);'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveAll(document.forms[0].selectedUnit,document.forms[0].unitsList);'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UnitEpisodeKeywordFB" property="selectedUnit" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfonthead"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;&nbsp;<bean:message key="sel"/> <bean:message key="keywords"/>&nbsp;&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<html:select name="UnitEpisodeKeywordFB" property="unselectedKeyword" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_KEYWORD_LIST %>">
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_KEYWORD_LIST%>" property="episodeKeywordID" labelProperty="episodeKeyword" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveSingle(document.forms[0].unselectedKeyword,document.forms[0].selectedKeyword);' />
								<img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveAll(document.forms[0].unselectedKeyword,document.forms[0].selectedKeyword);' />
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveSingle(document.forms[0].selectedKeyword,document.forms[0].unselectedKeyword);' />
								<img src="/HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveAll(document.forms[0].selectedKeyword,document.forms[0].unselectedKeyword);' />
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<html:select name="UnitEpisodeKeywordFB" property="selectedKeyword" multiple="true" size="6">
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
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
				</span>
			</his:ButtonToolBarTag>
			<div id="status" align="center" style="display:none;" >
		<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										No units Found
									</b>	
								</font>
		</div>
		<center><b><his:status/></b></center>
		</his:TransactionContainer>
	</html:form>
</body>
</html>