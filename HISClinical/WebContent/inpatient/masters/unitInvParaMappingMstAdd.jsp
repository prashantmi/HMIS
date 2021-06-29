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
<%@page import="inpatient.InpatientConfig"%>

<%@page import="org.apache.commons.validator.Var"%>
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



function unitSelected(cboDept)
{
	var unitId=cboDept.value;
	submitPage()
	
	var unitName=cboDept.options[cboDept.selectedIndex].text;
	if(unitId != "-1")
	{
		var lst=document.forms[0].unitsList;
		var len=lst.options.length;
		var limit=0;
		var unitNameLen=unitName.length;
		for(i=len-1;i>=limit;i--)
		{
			//var str=lst.options[i].value.substr(3,3);
			var str=lst.options[i].text.substr(0,unitNameLen);
			if(str==unitName)
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

function wardSelected(cboDept)
{
	var wardCode=cboDept.value;
	var wardName=cboDept.options[cboDept.selectedIndex].text;
	if(unitId != "-1")
	{
		var lst=document.forms[0].wardsList;
		var len=lst.options.length;
		var limit=0;
		var wardNameLen=wardName.length;
		for(i=len-1;i>=limit;i--)
		{
			//var str=lst.options[i].value.substr(3,3);
			var str=lst.options[i].text.substr(0,wardNameLen);
			if(str==wardName)
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

function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Parameter
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].paraList;
		target = document.forms[0].selectedPara;	
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
		source  = document.forms[0].paraList;
		target = document.forms[0].selectedPara;	
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
		target  = document.forms[0].paraList;
		source = document.forms[0].selectedPara;	
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
		target  = document.forms[0].paraList;
		source = document.forms[0].selectedPara;	
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
	// Select All Parameters in Selected
	if(document.forms[0].selectedPara)
		for(var i=0;i<document.forms[0].selectedPara.length;i++)
			document.forms[0].selectedPara.options[i].selected=true;
	// Select All Wards in Selected
	if(document.forms[0].selectedWards)
		for(var i=0;i<document.forms[0].selectedWards.length;i++)
			document.forms[0].selectedWards.options[i].selected=true;
	// Unselect Remaining Parameters
	if(document.forms[0].paraList)
	{	
		for(var i=0;i<document.forms[0].paraList.options.length;i++)
			document.forms[0].paraList.options[i].selected=false;
	}
}


function goGetWard()
{
	submitPage("GETWARD");
}

function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	
	if(document.getElementsByName('unitId')[0].value=="-1")
	{
		alert("Select unit");
		document.getElementById('unitId').focus();
		return false;
	}
	
	if(document.forms[0].selectedPara.options.length==0)
	{
		alert("Choose at Least One Parameter");
		document.forms[0].paraList.focus();
		return;
	}
	return true;
}
function validateFinalSubmitForWardWise()
{
	
	if(document.getElementsByName('wardCode')[0].value=="-1")
	{
		alert("Select Ward");
		document.getElementById('wardCode').focus();
		return false;
	}
	
	if(document.forms[0].selectedPara.options.length==0)
	{
		alert("Choose at Least One Parameter");
		document.forms[0].paraList.focus();
		return;
	}
	return true;
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) 
	return;
	
	submitPage(mode);
}

function finalSubmitForWardWise(mode)
{
	if (!validateFinalSubmitForWardWise()) 
	return;
	
	submitPage(mode);
}

</script>

	<body>
		<html:form action="/master/AddUnitInvParaMappingMstACT.cnt">
			<html:hidden name="UnitInvParaMappingMstFB" property="hmode"/>
			
				
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

			<logic:notEqual name="UnitInvParaMappingMstFB" property="hmode" value="MODIFY">
			<logic:notEqual name="UnitInvParaMappingMstFB" property="hmode" value="VIEW">
			<his:TitleTag name="ADD:Unit Investigation Parameter Mapping Master">
			</his:TitleTag>
			</logic:notEqual>
			</logic:notEqual>
				
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
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
								<logic:equal name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWISE_MODE%>">
								
								<bean:message key="unitWise"/>
								<html:radio name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWISE_MODE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								
								<bean:message key="unitwardwise"/>
								<html:radio name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWARD_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>								
								</logic:equal>
								<logic:equal name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWARD_WISE%>">
								
								<bean:message key="unitWise"/>
								<html:radio name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWISE_MODE %>" onclick="submitPage('CHANGEMODE')"></html:radio>
								
								<bean:message key="unitwardwise"/>
								<html:radio name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWARD_WISE %>" onclick="submitPage('CHANGEMODE')"></html:radio>								
								</logic:equal>
							</div>	
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<logic:equal name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWISE_MODE%>">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Units </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="75%" colspan="3" class="tdfont">
							<div align="center">
								<html:select name="UnitInvParaMappingMstFB" property="unitId"  onkeypress="if(event.keyCOde==13)unitSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.EssentialBO_LIST_ALL_UNITSFORUNITWISE%>">
									<html:options collection="<%=InpatientConfig.EssentialBO_LIST_ALL_UNITSFORUNITWISE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Parameters </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="UnitInvParaMappingMstFB" property="paraList" multiple="true" size="6">
									<logic:present name="<%=InpatientConfig.EssentialBO_LIST_ALL_PARAMETER%>">
									<html:options collection="<%=InpatientConfig.EssentialBO_LIST_ALL_PARAMETER%>" property="value" labelProperty="label" />
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
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:select name="UnitInvParaMappingMstFB" property="selectedPara" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			<logic:equal name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWARD_WISE%>">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:equal name="UnitInvParaMappingMstFB" property="isGoPressed" value="<%=InpatientConfig.STEP0%>">
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Units </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="75%" colspan="3" class="tdfont">
							<div align="center">
								<html:select name="UnitInvParaMappingMstFB" property="unitId" onkeypress="if(event.keyCOde==13)unitSelected(this);"  styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.EssentialBO_LIST_ALL_UNITSFORWARDWISE%>">
									<html:options collection="<%=InpatientConfig.EssentialBO_LIST_ALL_UNITSFORWARDWISE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<html:hidden name="UnitInvParaMappingMstFB" property="unitId"/>
						</td>
					</tr>
					</logic:equal>
					<logic:equal name="UnitInvParaMappingMstFB" property="isGoPressed" value="<%=InpatientConfig.STEP1%>">		
					<tr>
						<td width="75%" colspan="3" class="tdfont">
							<div align="center">
								<html:select name="UnitInvParaMappingMstFB" property="unitId" disabled="true" onkeypress="if(event.keyCOde==13)unitSelected(this);"  styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.EssentialBO_LIST_ALL_UNITSFORWARDWISE%>">
									<html:options collection="<%=InpatientConfig.EssentialBO_LIST_ALL_UNITSFORWARDWISE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<html:hidden name="UnitInvParaMappingMstFB" property="unitId"/>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Wards </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="75%" colspan="3" class="tdfont">
							<div align="center">
								<html:select name="UnitInvParaMappingMstFB" property="wardCode" onkeypress="if(event.keyCOde==13)wardSelected(this);" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.EssentialBO_LIST_WARDS_FORSPECIFICUNITS%>">
									<html:options collection="<%=InpatientConfig.EssentialBO_LIST_WARDS_FORSPECIFICUNITS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Parameters </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="35%"  class="tdfont">
							<div align="center">
								<html:select name="UnitInvParaMappingMstFB" property="paraList" multiple="true" size="6">
									<logic:present name="<%=InpatientConfig.EssentialBO_LIST_ALL_PARAMETER%>">
									<html:options collection="<%=InpatientConfig.EssentialBO_LIST_ALL_PARAMETER%>" property="value" labelProperty="label" />
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
						<td width="35%"  class="tdfont">
							<div align="center">
								<html:select name="UnitInvParaMappingMstFB" property="selectedPara" multiple="true" size="6">
								</html:select>
								<html:hidden name="UnitInvParaMappingMstFB" property="unitId"/>
							</div>
						</td>
					</tr>
					</logic:equal>
				</table>
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWISE_MODE %>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('LIST')" onkeypress="if(event.keyCode==13) submitPage('LIST')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
			</logic:equal>
			<logic:equal name="UnitInvParaMappingMstFB" property="additionMode" value="<%=InpatientConfig.UNITWARD_WISE %>">
				<logic:equal name="UnitInvParaMappingMstFB" property="isGoPressed" value="<%=InpatientConfig.STEP0 %>">
					<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer"  onclick="goGetWard();">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('LIST')" onkeypress="if(event.keyCode==13) submitPage('LIST')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
				</logic:equal>
				<logic:equal name="UnitInvParaMappingMstFB" property="isGoPressed" value="<%=InpatientConfig.STEP1 %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmitForWardWise('SAVE')" onclick="finalSubmitForWardWise('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('LIST')" onkeypress="if(event.keyCode==13) submitPage('LIST')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
				</logic:equal>
			</logic:equal>
			</his:ButtonToolBarTag>
			</his:TransactionContainer>
		</html:form><his:status/>
	</body>
</html>