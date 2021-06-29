
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/css/calendar-blue2.css"/>

<title>Department Unit Grouping </title>


<script>
window.history.forward()

	function submitTile(mode)
	{
		
	   document.getElementsByName("hmode")[0].value=mode;
	   //alert("hmode"+document.getElementsByName("hmode")[0].value);
	   document.forms[0].submit();
	}
	
	function submitFinal(mode)
	{
		
	   document.getElementsByName("hmode")[0].value=mode;
	   //alert("hmode"+document.getElementsByName("hmode")[0].value);
	   document.forms[0].submit();
	}
	submitFinal
	function submitPage(mode)
	{
		var a=document.getElementsByName("groupingType")[0].value;
		if(a==1)
		{
			MoveToSelectedDept();
		}
		if(a==2)
		{
			MoveToSelectedUnit();
		}
		document.getElementsByName("hmode")[0].value=mode;
	   // alert("hmode"+document.getElementsByName("hmode")[0].value);
	    document.forms[0].submit();
		
	}
	function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Dept
	// 2 -> Unit
	
	if(listNo=="1")
	{
		source  = document.forms[0].deptList;
		target = document.forms[0].selectedDept;	
	}
	if(listNo=="2")
	{
		source = document.forms[0].unitList;
		target = document.forms[0].selectedUnit;	
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
		source  = document.forms[0].deptList;
		target = document.forms[0].selectedDept;	
	}
	if(listNo=="2")
	{
		source = document.forms[0].unitList;
		target = document.forms[0].selectedUnit;	
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
		target  = document.forms[0].deptList;
		source = document.forms[0].selectedDept;	
	}
	if(listNo=="2")
	{
		target = document.forms[0].unitList;
		source = document.forms[0].selectedUnit;	
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
		target  = document.forms[0].deptList;
		source = document.forms[0].selectedDept;	
	}
	if(listNo=="2")
	{
		target = document.forms[0].unitList;
		source = document.forms[0].selectedUnit;	
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

function MoveToSelectedDept()
{
	
	// Unselect Remaining Dept
	if(document.forms[0].deptList)
	{	
		for(var i=0;i<document.forms[0].deptList.length;i++)
			document.forms[0].deptList.options[i].selected=false;
	}
	
	// Select All Dept in Selected
	for(var i=0;i<document.forms[0].selectedDept.length;i++)
		document.forms[0].selectedDept.options[i].selected=true;	
}

function MoveToSelectedUnit()
{
	// Unselect Remaining Units
	if(document.forms[0].unitList)
	{	
		for(var i=0;i<document.forms[0].unitList.length;i++)
			document.forms[0].unitList.options[i].selected=false;
	}

	
	// Select All Units in Selected
	for(var i=0;i<document.forms[0].selectedUnit.length;i++)
		document.forms[0].selectedUnit.options[i].selected=true;
}

function clearAdd()
{
//	document.getElementsByName("isValid")[0].value="-1";
	document.getElementsByName("groupingMode")[0].value="-1";
	document.getElementsByName("groupingValidity")[0].value="0";
	if(document.getElementsByName("groupingType")[0].value==1)
	{
		moveLeftAll("1");
	}
	else
	{
		moveLeftAll("2");
	}
}

function validateAdd()
{
	if(document.getElementsByName("groupingType")[0].value==1)
	{
		if(document.getElementsByName("fromDeptCode")[0].value=="-1")
		{
			alert("Select the To Department");
			document.getElementsByName("fromDeptCode")[0].focus();
		}
		else if (document.getElementsByName("selectedDept")[0].options.length==0)
		{
			alert("Choose at Least One Department");
			document.getElementsByName("deptList").focus();
		}
		
		else if (document.getElementsByName("groupingMode")[0].value=="-1")
		{
			alert("Select the Grouping Mode");
			document.getElementsByName("groupingMode")[0].focus();
		}
		else 
		submitPage("SAVE");
	}
	
	if(document.getElementsByName("groupingType")[0].value==2)
	{
		if(document.getElementsByName("fromDeptUnitCode")[0].value=="-1")
		{
			alert("Select the To Unit");
			document.getElementsByName("fromDeptUnitCode")[0].focus();
		}
		else if (document.getElementsByName("selectedUnit")[0].options.length==0)
		{
			alert("Choose at Least One Unit");
			document.getElementsByName("unitList").focus();
		}
		
		else if (document.getElementsByName("groupingMode")[0].value=="-1")
		{
			alert("Select the Grouping Mode");
			document.getElementsByName("groupingMode")[0].focus();
		}
		else 
		submitPage("SAVE");
	}
}

function validateDelete()
{
	var len;
	var isValid = true;
	count=0;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{    
        alert("Please Select a record");
        return;
    }
    if(count>1)
    {    
        alert("Deleting multiple records not allowed");
        return;
    }
    else
    {
    	submitTile('DELETE');
    }	    
}

function validateModify()
{
	var len;
	var isValid = true;
	count=0;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{    
        alert("Please Select a record");
        return;
    }
    if(count>1)
    {    
        alert("Modifying multiple records not allowed");
        return;
    }
    else
    {
    	submitTile('MODIFY');
    }	    
}


function clearModify()
{
	document.getElementsByName("groupingValidity")[0].value="0";
	document.getElementsByName("isValid")[0].value="-1";
}

function validateModifySave()
{
	if(document.getElementsByName("isValid")[0].value=="-1")
	{
		alert("Select Is Valid");
		document.getElementsByName("isValid")[0].focus();
	}
	else
		submitFinal("SAVEMODIFY");
}

</script>

	<body>
		<html:form action="/master/deptUnitGroupingMaster">
			<his:TransactionContainer>
			<%
  			String varStatus="";
			%>
			<his:statusNew>
			<%	varStatus="New";%>
			</his:statusNew>
				<his:TitleTag name="Department/Unit Grouping Master" >
				</his:TitleTag>
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="grouping"/>
											<bean:message key="type"/>
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
								<logic:equal name="deptUnitGroupingMasterFB" property="hmode" value="NEW">
									<html:select name="deptUnitGroupingMasterFB" property="groupingType" tabindex="1" onchange="submitTile('LIST')" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Department Wise</html:option>
										<html:option value="2">Department Unit Wise</html:option>
									</html:select>
								</logic:equal>
								
								<logic:notEqual name="deptUnitGroupingMasterFB" property="hmode" value="NEW">	
									<logic:equal name="deptUnitGroupingMasterFB" property="groupingType" value="1">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  						<b>
					  							<bean:message key="deptWise"/>
					  						</b>
					  					</font>								
									</logic:equal>	
									<logic:equal name="deptUnitGroupingMasterFB" property="groupingType" value="2">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  						<b>
					  							<bean:message key="deptUnitWise"/>
					  						</b>
					  					</font>								
									</logic:equal>
								</logic:notEqual>
								</div>
							</td>
						</tr>
					
					</table>
				</his:ContentTag>
			<logic:equal name="deptUnitGroupingMasterFB" property="hmode" value="LIST">	
		<his:statusList>
			<logic:notEmpty name="<%=RegistrationConfig.ARRAY_DEPT_GROUPING_VO %>">
				<his:TitleTag name="Grouping List" >
				</his:TitleTag>
				<his:ContentTag>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td width="10%" class="tdfonthead">
		 						<div align="center">
			          				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  					<b>
					  						<bean:message key="select"/>
					  					</b>
					  				</font>
			    				</div>
		 					</td>
		 					<td width="45%" class="tdfonthead">
				           		<div align="left">	           
				              		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				              			<b>
				              				<bean:message key="referFromDept"/>
				              			</b>
				              		</font>
				            	</div>
		 					</td>
		 					<td width="45%" class="tdfonthead">
				           		<div align="left">	           
				              		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				              			<b>
				              				<bean:message key="toDept"/>
				              			</b>
				              		</font>
				            	</div>
		 					</td>  
						</tr>
						<logic:iterate name="<%=RegistrationConfig.ARRAY_DEPT_GROUPING_VO %>" id="ARRAY_DEPT_GROUPING_VO" indexId="index" type="hisglobal.vo.DeptUnitGroupingMasterVO">
							<tr>
								<td width="10%" class="tdfont">
									<div align="center">
										 <html:checkbox name="deptUnitGroupingMasterFB" property="chk" tabindex="1" value='<%=ARRAY_DEPT_GROUPING_VO.getFromDeptCode()+"_"+ARRAY_DEPT_GROUPING_VO.getToDeptCode()%>'/>
									</div>
								</td>
								<td width="45%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="ARRAY_DEPT_GROUPING_VO" property="fromDeptName"/>
										</font>
									</div>
								</td>
								<td width="45%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="ARRAY_DEPT_GROUPING_VO" property="toDeptName"/>
										</font>
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</logic:notEmpty>
			<logic:notEmpty name="<%=RegistrationConfig.ARRAY_UNIT_GROUPING_VO %>">
				<his:TitleTag name="Grouping List" >
				</his:TitleTag>
				<his:ContentTag>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td width="10%" class="tdfonthead">
		 						<div align="center">
			          				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  					<b>
					  						<bean:message key="select"/>
					  					</b>
					  				</font>
			    				</div>
		 					</td>
		 					<td width="45%" class="tdfonthead">
				           		<div align="left">	           
				              		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				              			<b>
				              				<bean:message key="fromUnit"/>
				              			</b>
				              		</font>
				            	</div>
		 					</td>
		 					<td width="45%" class="tdfonthead">
				           		<div align="left">	           
				              		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				              			<b>
				              				<bean:message key="toUnit"/>
				              			</b>
				              		</font>
				            	</div>
		 					</td>  
						</tr>
						<logic:iterate name="<%=RegistrationConfig.ARRAY_UNIT_GROUPING_VO %>" id="ARRAY_UNIT_GROUPING_VO" indexId="index" type="hisglobal.vo.DeptUnitGroupingMasterVO">
							<tr>
								<td width="10%" class="tdfont">
									<div align="center">
										 <html:checkbox name="deptUnitGroupingMasterFB" property="chk" tabindex="1" value='<%=ARRAY_UNIT_GROUPING_VO.getFromDeptUnitCode()+"_"+ARRAY_UNIT_GROUPING_VO.getToDeptUnitCode()%>' />
									</div>
								</td>
								<td width="45%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="ARRAY_UNIT_GROUPING_VO" property="fromDeptUnitName"/>
										</font>
									</div>
								</td>
								<td width="45%" class="tdfont">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="ARRAY_UNIT_GROUPING_VO" property="toDeptUnitName"/>
										</font>
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</logic:notEmpty>
		<%varStatus="List";%>	
		</his:statusList>	
	</logic:equal>
	<logic:equal name="deptUnitGroupingMasterFB" property="hmode" value="ADD">
	
		<his:TitleTag name="Add Page"> 
		</his:TitleTag>	
		<logic:equal name="deptUnitGroupingMasterFB" property="groupingType" value="1">
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="toDept"/>
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="deptUnitGroupingMasterFB" property="fromDeptCode" onchange="submitTile('GETDEPTUNIT')" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT_LIST %>" property="value" labelProperty="label" />
							</html:select>
						</div>
					</td>
				</tr>
				</table>
			<his:statusTransactionInProcess>	
			<table width="100%" border="0" cellpadding="0" cellspacing="1">	
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="referFromDept"/>
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfonthead">
						
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="40%"  class="tdfonthead"></td>
					<td width="20%"  class="tdfont"></td>
					<td width="40%"  class="tdfonthead"></td>
				</tr>
				<tr>
					<td width="40%"  class="tdfonthead">
						<div align="center">
							<html:select name="deptUnitGroupingMasterFB" property="deptList" multiple="true" size="6" tabindex="1">
								<html:options collection="<%=RegistrationConfig.ALL_DEPT_EXCEPT_FROM_DEPT %>" property="value" labelProperty="label" />
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
							<html:select name="deptUnitGroupingMasterFB" property="selectedDept" multiple="true" size="6" tabindex="1">
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
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="grouping"/>
									<bean:message key="mode"/>
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="deptUnitGroupingMasterFB" property="groupingMode" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="1">Single</html:option>
								<html:option value="2">Vice Versa</html:option>
							</html:select>
						</div>
					</td>		
				</tr>
				
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									Is Visit Date Bound
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="deptUnitGroupingMasterFB" property="groupingValidity" tabindex="1">
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>
							</html:select>
						</div>
					</td>		
				</tr>
			
			</table>
		</his:statusTransactionInProcess>	
		</his:ContentTag>
		</logic:equal>
		
		
		<logic:equal name="deptUnitGroupingMasterFB" property="groupingType" value="2">
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="toUnit"/>
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="deptUnitGroupingMasterFB" property="fromDeptUnitCode" onchange="submitTile('GETDEPTUNIT')" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT %>" property="value" labelProperty="label" />
							</html:select>
						</div>
					</td>
				</tr>
				</table>
			<his:statusTransactionInProcess>	
			<table width="100%" border="0" cellpadding="0" cellspacing="1">	
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="fromUnit"/>
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfonthead">
						
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="40%"  class="tdfonthead"></td>
					<td width="20%"  class="tdfont"></td>
					<td width="40%"  class="tdfonthead"></td>
				</tr>
				<tr>
					<td width="40%"  class="tdfonthead">
						<div align="center">
							<html:select name="deptUnitGroupingMasterFB" property="unitList" multiple="true" size="6" tabindex="1">
								<html:options collection="<%=RegistrationConfig.ALL_UNIT_EXCEPT_FROM_UNIT %>" property="value" labelProperty="label" />
							</html:select>
						</div>
					</td>
					<td width="20%"  class="tdfont">
						<div align="center">
							<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("2");' tabindex="1"/> 	
							<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("2");' tabindex="1"/> 	
							<br><br>
							<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("2");' tabindex="1"/> 	
							<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("2");' tabindex="1"/>
						</div>
					</td>
					<td width="40%"  class="tdfonthead">
						<div align="center">
							<html:select name="deptUnitGroupingMasterFB" property="selectedUnit" multiple="true" size="6">
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
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="grouping"/>
									<bean:message key="mode"/>
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="deptUnitGroupingMasterFB" property="groupingMode" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="1">Single</html:option>
								<html:option value="2">Vice Versa</html:option>
							</html:select>
						</div>
					</td>		
				</tr>
				
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									Is Visit Date Bound
								</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="deptUnitGroupingMasterFB" property="groupingValidity" tabindex="1">
								<html:option value="0">No</html:option>
								<html:option value="1">Yes</html:option>
							</html:select>
						</div>
					</td>		
				</tr>
			
			</table>
		</his:statusTransactionInProcess>	
		</his:ContentTag>
		</logic:equal>
		</logic:equal>
		
		<logic:equal name="deptUnitGroupingMasterFB" property="hmode" value="MODIFY">
			<his:TitleTag name="Modify Page">
			</his:TitleTag>
			<logic:equal name="deptUnitGroupingMasterFB" property="groupingType" value="1">
				<his:ContentTag>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="referFromDept"/>		
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;
											<bean:write name="deptUnitGroupingMasterFB" property="fromDeptName"/>
											<html:hidden name="deptUnitGroupingMasterFB" property="fromDeptName"/>
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="toDept"/>		
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;
											<bean:write name="deptUnitGroupingMasterFB" property="toDeptName"/>
											<html:hidden name="deptUnitGroupingMasterFB" property="toDeptName"/>
										</b>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfont">
							<td width="25%" class="tdfont">
							<td width="25%" class="tdfont">
							<td width="25%" class="tdfont">
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											Is Visit Date Bound
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="2">
								<div align="left">
									<html:select name="deptUnitGroupingMasterFB" property="groupingValidity" tabindex="1">
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
									</html:select>
								</div>
							</td>		
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="isvalid"/>		
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="2">
								<div align="left">
									<html:select name="deptUnitGroupingMasterFB" property="isValid" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Active</html:option>
										<html:option value="2">In Active</html:option>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</logic:equal>
			
			<logic:equal name="deptUnitGroupingMasterFB" property="groupingType" value="2">
				<his:ContentTag>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="fromUnit"/>		
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;
											<bean:write name="deptUnitGroupingMasterFB" property="fromDeptUnitName"/>
											<html:hidden name="deptUnitGroupingMasterFB" property="fromDeptUnitName"/>
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="toUnit"/>		
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;
											<bean:write name="deptUnitGroupingMasterFB" property="toDeptUnitName"/>
											<html:hidden name="deptUnitGroupingMasterFB" property="toDeptUnitName"/>
										</b>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfont">
							<td width="25%" class="tdfont">
							<td width="25%" class="tdfont">
							<td width="25%" class="tdfont">
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											Is Visit Date Bound
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="2">
								<div align="left">
									<html:select name="deptUnitGroupingMasterFB" property="groupingValidity" tabindex="1">
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
									</html:select>
								</div>
							</td>		
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="isvalid"/>		
										</b>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="2">
								<div align="left">
									<html:select name="deptUnitGroupingMasterFB" property="isValid" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Active</html:option>
										<html:option value="2">In Active</html:option>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</logic:equal>
		</logic:equal>
			
			<his:ButtonToolBarTag>
				
				
				<%if(varStatus.equals("List")){%>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitTile('ADD');" tabindex="1" onclick="submitTile('ADD')" >
					<logic:notEmpty name="<%=RegistrationConfig.ARRAY_DEPT_GROUPING_VO %>">
						<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateModify() && submitTile('MODIFY');" tabindex="1" onclick=" validateModify() && submitTile('MODIFY');" >   
		           		<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateDelete() && submitTile('DELETE');" tabindex="1" onclick="validateDelete() && submitTile('DELETE')" >
		           </logic:notEmpty>
		           <logic:notEmpty name="<%=RegistrationConfig.ARRAY_UNIT_GROUPING_VO %>">
						<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateModify() && submitTile('MODIFY');" tabindex="1" onclick="validateModify() && submitTile('MODIFY');" >   
		           		<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateDelete() && submitTile('DELETE');" tabindex="1" onclick="validateDelete() && submitTile('DELETE')" >
		           </logic:notEmpty>
		           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
				<%} %>
				<logic:equal name="deptUnitGroupingMasterFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd() && submitPage('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitPage('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearAdd()" onkeypress="if(event.keyCode==13) clearAdd();">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('LIST')" onkeypress="if(event.keyCode==13) submitTile('LIST')">
				</logic:equal>
				
				<logic:equal name="deptUnitGroupingMasterFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick=" validateModifySave() && submitFinal('SAVEMODIFY')" onkeypress="if(event.keyCode==13) validateModifySave() && submitFinal('SAVEMODIFY')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearModify()" onkeypress="if(event.keyCode==13) clearModify();">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('LIST')" onkeypress="if(event.keyCode==13) submitTile('LIST')">
				</logic:equal>
				
			</his:ButtonToolBarTag>
				
				
			<html:hidden name="deptUnitGroupingMasterFB" property="hmode"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="chk"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="groupingType"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="groupingMode"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="tempMode"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="fromDeptCode"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="toDeptCode"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="fromDeptUnitCode"/>
			<html:hidden name="deptUnitGroupingMasterFB" property="toDeptUnitCode"/>
			</his:TransactionContainer>
		<his:status/>
		</html:form>
	</body>
</html>