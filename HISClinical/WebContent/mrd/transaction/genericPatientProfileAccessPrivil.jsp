<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/opd/js/generic_profile_access_privil.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function callThisOnload()
{
	var previousUnitUser=document.getElementsByName("previousUnitUser")[0].value;
	var previousUnitUserArray=previousUnitUser.split('#');
	//alert("array "+previousUnitUserArray)
	
	//if(document.forms[0].unitList)
	//{
	//	for(var i=0;i<document.forms[0].unitList.options.length;i++)
	//		document.forms[0].unitList.options[i].selected=false;
	//}
	
	for(i=0;i<previousUnitUserArray.length;i++)
	{
		if(previousUnitUserArray[i]==<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC%>)
		{
			document.getElementsByName("selectedUserUnitSpecificType")[0].checked=true;
		}
		
		if(previousUnitUserArray[i]==<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND%>)
		{
			document.getElementsByName("selectedUserUnitSpecificType")[1].checked=true;
		}
	}
	showunitUserSpecific();
}

function deleteRow(obj)
{
	document.getElementsByName("deleteIndex")[0].value=obj;
	submitForm('DELETEUSERPRIV');
}

function showunitUserSpecific()
{
	var selectedAccessType=document.getElementsByName("selectedAccessType")[0].value;
	var allUnit=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL%>
	var userUnitSpecific=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER%>
	var unitSpecific=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC%>
	var restrictedUser=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND%>
	var selectedUserUnitSpecificType=document.getElementsByName("selectedUserUnitSpecificType")[0].value;
	
	if(selectedAccessType==userUnitSpecific)
	{
		document.getElementById("unitSpecificTypeId").style.display="";
		document.getElementById("userSpecificTypeId").style.display="";
		document.getElementById("userLevelControlAll").style.display="none";
		document.getElementById("userLevelLabelAll").style.display="none";
		if(document.getElementsByName("selectedUserUnitSpecificType")[0].checked)
		{
			document.getElementById("unitPrivilegdeId").style.display="";
		
		}
		else
		{
			document.getElementById("unitPrivilegdeId").style.display="none";
		}
		if(document.getElementsByName("selectedUserUnitSpecificType")[1].checked)
		{
			document.getElementById("userPrivilegdeId").style.display="";
		}
		else
		{
			document.getElementById("userPrivilegdeId").style.display="none";
		}
	}
	
	if(selectedAccessType==allUnit)
	{
		document.getElementById("userSpecificTypeId").style.display="none";
		document.getElementById("unitSpecificTypeId").style.display="none";
		document.getElementById("unitPrivilegdeId").style.display="none";
		document.getElementById("userPrivilegdeId").style.display="none";
		document.getElementById("userLevelControlAll").style.display="";
		document.getElementById("userLevelLabelAll").style.display="";
	}
}


function showPrivilegdes()
{
	var selectedAccessType=document.getElementsByName("selectedAccessType")[0].value;
	var allUnit=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL%>
	var userUnitSpecific=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC%>
	
	
	if(selectedAccessType==allUnit)
	{
		document.getElementById("unitSpecificTypeId").style.display="none";
		document.getElementById("userSpecificTypeId").style.display="none";
		document.getElementById("unitPrivilegdeId").style.display="none";
		document.getElementById("userPrivilegdeId").style.display="none";
		document.getElementById("userLevelControlAll").style.display="";
		document.getElementById("userLevelLabelAll").style.display="";
	}
	if(selectedAccessType==userUnitSpecific)
	{
		document.getElementById("unitSpecificTypeId").style.display="";
		document.getElementById("userSpecificTypeId").style.display="";
		document.getElementById("userLevelControlAll").style.display="none";
		document.getElementById("userLevelLabelAll").style.display="none";
	}
	
	showunitUserSpecific();
	
}

function validateAccessPrivil(mode)
{
	var flag=false;
	// alert("hjghjf"+document.getElementsByName("selectedUserUnitSpecificType")[0].value)
	if(document.forms[0].unitList)
	{
		MoveToSelected();
		
	//	if(document.getElementsByName("selectedUserUnitSpecificType")[0].value==<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC %>)
	//	{
	//		alert("gfgfg"+document.getElementsByName("selectedUnitUserLevel")[0].value)
	//		if(document.getElementsByName("selectedUnitUserLevel")[0].value=="-1")
	//		{
	//			alert("Please Select User Level");
	//			flag=false;
				
	//		}
		//	else
	//		{
	///			flag=true;
	//		}
	//	}
	}
	else if(document.forms[0].usersList)
	{
		MoveToSelected();
		
	}
	
/*	{
		MoveToSelected();
		//---------
		if(!validateAddUser())
			return;
	}*/
	submitFormOnValidate(true,mode);
	//window.close();
}


</script>

<html:form action="/opdPatientProfile">
<his:TransactionContainer> 
<his:TitleTag key="accessPrivileges">
</his:TitleTag>

<his:statusNew>


<table>
 <tr>
 	<td width="15%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="accessType" /></b>
						</font>
					</div>
				</td>

				
				
					<td width="15%" class="tdfonthead">
					<div align="left">
						
						<html:select name="GenericPatientProfileFB" property="selectedAccessType" onchange="showunitUserSpecific()" tabindex="1" >
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL%>">To All</html:option>
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER%>">Unit Specific/User Specific</html:option>
						</html:select>
					</div>
				</td>
				
				<td width="15%" class="tdfonthead" id="userLevelLabelAll"  >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="userlevel" /></b>
						</font>
					</div>
				</td>
				<td width="15%" id="userLevelControlAll" class="tdfonthead" >
					<div align="left">
						<html:select name="GenericPatientProfileFB" property="selectedUserLevel" tabindex="1" >
							<html:option value="-1">Select Value</html:option>
							<html:option value="0">No Level</html:option>
							<html:option value="1">Level 1</html:option>
							<html:option value="2">Level 2</html:option>
							<html:option value="3">Level 3</html:option>
							<html:option value="4">Level 4</html:option>
							<html:option value="5">Level 5</html:option>
							<html:option value="6">Level 6</html:option>
						</html:select>
					</div>
				</td>
 </tr>
</table>




<div id="unitSpecificTypeId" style="display: none" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="40%"  class="tdfonthead">
				<div align="left" >
				<html:checkbox name="GenericPatientProfileFB" property="selectedUserUnitSpecificType" value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC %>" onclick="showunitUserSpecific(this)" ></html:checkbox>
				<bean:message key="unitSpecific" />
				</div>
				</td>
				<td width="15%" class="tdfonthead" >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="userlevel" /></b>
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead" >
					<div align="left">
						<html:select name="GenericPatientProfileFB" property="selectedUnitUserLevel" tabindex="1" >
							<html:option value="-1">Select Value</html:option>
							<html:option value="0">No Level</html:option>
							<html:option value="1">Level 1</html:option>
							<html:option value="2">Level 2</html:option>
							<html:option value="3">Level 3</html:option>
							<html:option value="4">Level 4</html:option>
							<html:option value="5">Level 5</html:option>
							<html:option value="6">Level 6</html:option>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>


<div id="unitPrivilegdeId" style="display: none;" >
	<his:TitleTag name="Give Unit Privilegdes">
	</his:TitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="40%"  class="tdfonthead"></td>
				<td width="20%"  class="tdfont"></td>
				<td width="40%" class="tdfonthead"></td>
			</tr>
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="center">
						<html:select name="GenericPatientProfileFB" property="unitList" multiple="true" size="6">
						<logic:present name="<%=OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST%>" >
							<html:options collection="<%=OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST%>" property="value" labelProperty="label" />
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
						<html:select name="GenericPatientProfileFB" property="selectedUnits" multiple="true" size="6">
						<logic:present name="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST%>" >
							<html:options collection="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_UNIT_LIST%>" property="value" labelProperty="label" />
						</logic:present>								
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
</div>

<div id="userSpecificTypeId" style="display: none" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="40%"  class="tdfonthead">
				<div align="left" >
				<html:checkbox name="GenericPatientProfileFB" property="selectedUserUnitSpecificType" value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND%>"  onclick="showunitUserSpecific(this)" ></html:checkbox>
				<bean:message key="userSpecific" />
				</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>


<div id="userPrivilegdeId" style="display: none" > 
	<his:TitleTag name="User Privilegdes">
	</his:TitleTag>
	
	<his:ContentTag>
		<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST%>">
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						
						<td width="25%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="user" /> <bean:message key="name" /></b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="employee" /> <bean:message key="id" /></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="employee" /> <bean:message key="name" /></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead" colspan="2">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="designation" /></b>
								</font>
							</div>
							
						</td>
						
					</tr>
					<logic:iterate id="user" name="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST%>" indexId="idx" type="hisglobal.vo.UserVO">
						<tr>
							
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userSeatId"/>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userEmpID"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userName"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userType"/>
								</font>
							</div>
						</td>
						<td class="tdfont" width="5%" >
							<div align="center">
									<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow(<%=idx.toString() %>) ;" onclick=" deleteRow(<%=idx.toString() %>)" tabindex="1">
							</div>	
						</td>
						</tr>
					</logic:iterate>
				</table>
			</logic:notEmpty>
	</his:ContentTag>

	<his:SubTitleTag key="search">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="25%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="user" /> <bean:message key="name" />
						</font>
					</div>
				</td>
				<td width="8%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp; &nbsp;
					</font>
				</td>
				<td width="25%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="employee" /> <bean:message key="id" />
						</font>
					</div>
				</td>
				<td width="9%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp; &nbsp;
					</font>
				</td>
				<td width="25%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="employee" /> <bean:message key="name" />
						</font>
					</div>
				</td>
				<td width="8%" class="addtoolbar" style="border-top: outset black 2px; border-bottom: inset black 2px;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp; &nbsp;
					</font>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="searchUserName" tabindex="1" 
							maxlength="100" />
					</div>
				</td>
				<td width="8%" class="tdfonthead">
					<div align="center">
						<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" 
							onClick="if(validateSearch(1)) submitForm('SEARCHUSER');"
							onkeypress="if(event.keyCode==13) if(validateSearch(1)) submitForm('SEARCHUSER');">
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="searchEmpId" tabindex="1" 
							maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)"/>
					</div>
				</td>
				<td width="9%" class="tdfonthead">
					<div align="center">
						<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" 
							onClick="if(validateSearch(2)) submitForm('SEARCHUSER');"
							onkeypress="if(event.keyCode==13) if(validateSearch(2)) submitForm('SEARCHUSER');">
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="searchEmpName" tabindex="1" 
							maxlength="100" onkeypress="return validateAlphaOnly(this,event)"/>
					</div>
				</td>
				<td width="8%" class="tdfonthead">
					<div align="center">
						<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" 
							onClick="if(validateSearch(3)) submitForm('SEARCHUSER');"
							onkeypress="if(event.keyCode==13) if(validateSearch(3)) submitForm('SEARCHUSER');">
					</div>
				</td>
			</tr>
		</table>

		<his:statusTransactionInProcess>
			<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST%>">
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="10%" class="tdfonthead">
							<div align="center">
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="user" /> <bean:message key="name" /></b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="employee" /> <bean:message key="id" /></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="employee" /> <bean:message key="name" /></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="designation" /></b>
								</font>
							</div>
						</td>
					</tr>
				<logic:iterate id="user" name="<%=OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST%>" indexId="idx" type="hisglobal.vo.UserVO">
					
				<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST%>">
					<%boolean flag=false; %>
						<logic:iterate id="user1" name="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST%>" indexId="idx" type="hisglobal.vo.UserVO">
							<%if(user.getUserId().equals(user1.getUserId())) { flag=true; break;}%>
						</logic:iterate>
							<%if(flag==false) {%>
							<tr>
							
							<td width="10%" class="tdfonthead">
								<div align="center">
									<html:checkbox name="GenericPatientProfileFB" property="selectedUserIndex" value="<%=user.getUserId()%>" onclick="validateIfAdded(this)" />
								</div>
							</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userSeatId"/>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userEmpID"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userName"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="user" property="userType"/>
								</font>
							</div>
						</td>
						
						</tr>
						<%} %>
					
							
						
						
					</logic:notEmpty>	
					 <logic:empty name="<%=OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST%>">
							<tr>
							
								<td width="10%" class="tdfonthead">
									<div align="center">
										<html:checkbox name="GenericPatientProfileFB" property="selectedUserIndex" value="<%=user.getUserId()%>" onclick="validateIfAdded(this)" />
									</div>
								</td>
							<td width="25%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="user" property="userSeatId"/>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="user" property="userEmpID"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="user" property="userName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="user" property="userType"/>
									</font>
								</div>
							</td>
							
							</tr>
						</logic:empty>
					</logic:iterate>
				</table>
			</logic:notEmpty>
			
		</his:statusTransactionInProcess>
	</his:ContentTag>
</div>

</his:statusNew>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST%>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateAddUser(),'ADDACCESSPRIVUSER');" onclick="submitFormOnValidate(validateAddUser(),'ADDACCESSPRIVUSER');" tabindex="1" />
		</logic:notEmpty>
	</his:statusTransactionInProcess>	
	<his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) validateAccessPrivil('SAVEACCESSPRIV');" onclick="validateAccessPrivil('SAVEACCESSPRIV');" tabindex="1" />
	</his:statusNew>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="window.close();" onkeypress="if(event.keyCode==13) window.close()">
</his:ButtonToolBarTag>

<his:status/>

</his:TransactionContainer>

<html:hidden name="GenericPatientProfileFB" property="hmode" />
<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="searchMode" />
<html:hidden name="GenericPatientProfileFB" property="searchString" />
<html:hidden name="GenericPatientProfileFB" property="accessPrivilSerialNo" />
<html:hidden name="GenericPatientProfileFB" property="previousUnitUser" />
<html:hidden name="GenericPatientProfileFB" property="unitListLeft" />
<html:hidden name="GenericPatientProfileFB" property="selectedUserUnitSpecificType" />
<html:hidden name="GenericPatientProfileFB" property="deleteIndex" />
<html:hidden name="GenericPatientProfileFB" property="selectedUnitUserLevel" />
<html:hidden name="GenericPatientProfileFB" property="selectedUserLevel" />
<html:hidden name="GenericPatientProfileFB"	property="dischargeModifyFlag" />
</html:form>