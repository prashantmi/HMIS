
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page info="Used for addition , modification and view of disaster type master" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script><!--
function finalSubmit(mode){
	if(validateSave()){
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
	else{
		return false;
	}	
}

function finalSubmitModify(mode)
{
	if(submitModifySave()){
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
	else{
		return false;
	}
}

function submitModifySave(mode)
{
	var valid=false;
	
	if(validateGroupDetail()){
		valid=true;
	}
	else{
		valid=false;
	}
	
	return valid;
}

function validateGroupDetail()
{
		var valid=false;
		var tempFlag=false;
		var userPresent=<%=OpdConfig.PROFILE_GROUP_DETAIL_USER_PRESENT%>;
		var userNotPresent=<%=OpdConfig.PROFILE_GROUP_DETAIL_USER_NOT_PRESENT%>;
		
		if(document.getElementsByName("selectedUserUnitSpecificType")[0].checked || document.getElementsByName("selectedUserUnitSpecificType")[1].checked)
		{	
			if(document.getElementsByName("selectedUserUnitSpecificType")[0].checked)
			{
				if(document.forms[0].selectedUnits.options.length==0)
				{
					alert("Please Add Unit Priviledges");
					valid=false;
						tempFlag=true;
				}
				else
				{
					valid=true;
				}
				
			}
			if(tempFlag!=true)
			{
				if(document.getElementsByName("selectedUserUnitSpecificType")[1].checked)
				{
						if(document.getElementsByName("userPriveledgeFlag")[0].value==userNotPresent)
						{
							alert("Please Add User Priviledges");
							valid=false;	
							
						}
						else
						{
							valid=true;
						}	
				}
			}
		}
		else
		{
			alert("Please Select Either Unit Specific or User Specific ")
			valid=false;	
		}
		MoveToSelected();
		return valid;
		
}

function validateSave(){
	var valid=false;
	
	if(comboValidation(document.getElementsByName("profileGroupId")[0],"Profile Group Name")
	&& validateGroupDetail()){
		valid=true;
	}
	else{
		valid=false;
	}
	
	return valid;

}

function submitPage(mode){
	document.getElementsByName("hmode")[0].value=mode
	document.forms[0].submit();
}

function clearForm(){
	document.getElementsByName("profileGroupId")[0].value=""
	document.getElementsByName("deptUnitCode")[0].value="-1"
	if(document.getElementsByName("isActive")[0]){
		document.getElementsByName("isActive")[0].value="-1"
	}
}

function validateIsActive(){
	var valid=true
	if(document.getElementsByName("isActive")[0]){
		if(comboValidation(document.getElementsByName("isActive")[0],"Is Active")){
			valid= true;
		}
		else{
			valid= false;
		}	
	}
	return valid;
}

function showunitUserSpecific()
{
	
	var userUnitSpecific=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER%>
	var unitSpecific=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC%>
	var restrictedUser=<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND%>
	var selectedUserUnitSpecificType=document.getElementsByName("selectedUserUnitSpecificType")[0].value;
	
	
		//document.getElementById("unitSpecificTypeId").style.display="";
		//document.getElementById("userSpecificTypeId").style.display="";
		//document.getElementById("userLevelControlAll").style.display="none";
		//document.getElementById("userLevelLabelAll").style.display="none";
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

function moveRightSingle(listNo)
{
	var source;
	var target;
	// 1 -> Units
	if(listNo=="1")
	{
		source  = document.forms[0].unitList;
		target = document.forms[0].selectedUnits;	
	}	
	else if(listNo=="2")
	{
		source  = document.forms[0].usersList;
		target = document.forms[0].selectedUsers;	
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
		source  = document.forms[0].unitList;
		target = document.forms[0].selectedUnits;	
	}
	else if(listNo=="2")
	{
		source  = document.forms[0].usersList;
		target = document.forms[0].selectedUsers;	
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
		target  = document.forms[0].unitList;
		source = document.forms[0].selectedUnits;	
	}
	else if(listNo=="2")
	{
		target  = document.forms[0].usersList;
		source = document.forms[0].selectedUsers;	
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
		target  = document.forms[0].unitList;
		source = document.forms[0].selectedUnits;	
	}
	else if(listNo=="2")
	{
		target  = document.forms[0].usersList;
		source = document.forms[0].selectedUsers;	
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

function validateSearch(code)
{
	document.getElementsByName("searchMode")[0].value = code;
	var str="";
	if(code==1)
	{
		if(document.getElementsByName("searchUserName")[0].value=="")
		{
			alert("Please Enter User Name for Search");
			document.getElementsByName("searchUserName")[0].focus();
			return false;
		}
		str=document.getElementsByName("searchUserName")[0].value;
		document.getElementsByName("searchEmpId")[0].value="";
		document.getElementsByName("searchEmpName")[0].value="";
	}
	else if(code==2)
	{
		if(document.getElementsByName("searchEmpId")[0].value=="")
		{
			alert("Please Enter Employee Id for Search");
			document.getElementsByName("searchEmpId")[0].focus();
			return false;
		}
		str=document.getElementsByName("searchEmpId")[0].value;
		document.getElementsByName("searchUserName")[0].value="";
		document.getElementsByName("searchEmpName")[0].value="";
	}
	else if(code==3)
	{
		if(document.getElementsByName("searchEmpName")[0].value=="")
		{
			alert("Please Enter Employee Name for Search");
			document.getElementsByName("searchEmpName")[0].focus();
			return false;
		}
		str=document.getElementsByName("searchEmpName")[0].value;
		document.getElementsByName("searchUserName")[0].value="";
		document.getElementsByName("searchEmpId")[0].value="";
	}
	document.getElementsByName("searchString")[0].value = str;
	MoveToSelected();
	return true;
}

function MoveToSelected()
{
	// Unselect Remaining Units
	
	
	if(document.forms[0].unitList)
	{
		for(var i=0;i<document.forms[0].unitList.options.length;i++)
			document.forms[0].unitList.options[i].selected=false;
	}

	// Select All Units in Selected
	if(document.forms[0].selectedUnits)
	{	
		for(var i=0;i<document.forms[0].selectedUnits.options.length;i++)
			document.forms[0].selectedUnits.options[i].selected=true;
	}
	
	// Select All Users
	if(document.forms[0].usersList)
	{	
		for(var i=0;i<document.forms[0].usersList.options.length;i++)
			document.forms[0].usersList.options[i].selected=true;
	}

	// Select All Users in Selected
	if(document.forms[0].selectedUsers)
	{	
		for(var i=0;i<document.forms[0].selectedUsers.options.length;i++)
			document.forms[0].selectedUsers.options[i].selected=true;
	}
}

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
	MoveToSelected();
}

function validateAddUser()
{
	var chks = document.getElementsByName("selectedUserIndex");
	var flag=false;
	for(var i=0; i<chks.length; i++)
	{
		if(chks[i].checked)
		{
			flag=true;
			break;
		}
	}
	if(!flag)
	{
		alert("Please Select at least One User to Give Add");
		chks[0].focus();
		return false;
	}
	MoveToSelected();
	return true;
}

function deleteRow(obj)
{
	document.getElementsByName("deleteIndex")[0].value=obj;
	submitForm('DELETEUSERPRIV');
}


</script>

<his:TransactionContainer>
 <body>

  <html:form action="/master/profileGroupDetail" > 
    <html:hidden name="ProfileGroupDetailFB" property="hmode"/>
    <html:hidden name="ProfileGroupDetailFB" property="tempMode"/>
    <html:hidden name="ProfileGroupDetailFB" property="serialNo"/>
    <html:hidden name="ProfileGroupDetailFB" property="chk"/>
    <html:hidden name="ProfileGroupDetailFB" property="profileGroupName"/>
    <html:hidden name="ProfileGroupDetailFB" property="searchMode"/>
    <html:hidden name="ProfileGroupDetailFB" property="searchString"/>
    <html:hidden name="ProfileGroupDetailFB" property="previousUnitUser"/>
    <html:hidden name="ProfileGroupDetailFB" property="selectedUserIndex"/>
    <html:hidden name="ProfileGroupDetailFB" property="deleteIndex"/>
    <html:hidden name="ProfileGroupDetailFB" property="serialNo"/>
    <html:hidden name="ProfileGroupDetailFB" property="userPriveledgeFlag"/>
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="ProfileGroupDetailFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
  
        
	   		<his:TitleTag name="Profile Group Detail">
			</his:TitleTag>
  	   
       
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<logic:equal name="ProfileGroupDetailFB" property="hmode" value="ADD">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileGroupName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;	<html:select name="ProfileGroupDetailFB" property="profileGroupId" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=OpdConfig.PROFILE_GROUP_DETAIL_GROUP_LIST %>" property="value"  labelProperty="label"  />
						</html:select>
					</div>
				</td>
		      </tr>
		  </logic:equal>    
		  <logic:equal name="ProfileGroupDetailFB" property="hmode" value="MODIFY">
		  		<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileGroupName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp; <bean:write name="ProfileGroupDetailFB" property="profileGroupName"/>	
					
					</div>
				</td>
		  </logic:equal>
		  <logic:equal name="ProfileGroupDetailFB" property="hmode" value="VIEW">
		  		<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileGroupName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp; <bean:write name="ProfileGroupDetailFB" property="profileGroupName"/>	
					
					</div>
				</td>
		  </logic:equal>
		  </table>
      </his:ContentTag>
      	<logic:notEqual name="ProfileGroupDetailFB" property="hmode" value="VIEW">
				<his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="40%"  class="tdfonthead">
							<div align="left" >
							<html:checkbox name="ProfileGroupDetailFB" property="selectedUserUnitSpecificType" value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC %>" onclick="showunitUserSpecific(this)" ></html:checkbox>
							<bean:message key="unitSpecific" />
							</div>
							</td>
							
						</tr>
					</table>
				</his:ContentTag>
			
			
			<div id="unitPrivilegdeId" style="display:none;" >
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
									<html:select name="ProfileGroupDetailFB" property="unitList" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST%>" >
										<html:options collection="<%=OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST%>" property="value" labelProperty="label" />
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
									<html:select name="ProfileGroupDetailFB" property="selectedUnits" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST%>" >
										<html:options collection="<%=OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST%>" property="value" labelProperty="label" />
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
			
		
				<his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="40%"  class="tdfonthead">
							<div align="left" >
							<html:checkbox name="ProfileGroupDetailFB" property="selectedUserUnitSpecificType" value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND%>"  onclick="showunitUserSpecific(this)" ></html:checkbox>
							<bean:message key="userSpecific" />
							</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				
			<div id="userPrivilegdeId" style="display:none;" > 
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
										<html:text name="ProfileGroupDetailFB" property="searchUserName" tabindex="1" 
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
										<html:text name="ProfileGroupDetailFB" property="searchEmpId" tabindex="1" 
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
										<html:text name="ProfileGroupDetailFB" property="searchEmpName" tabindex="1" 
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
													<html:checkbox name="ProfileGroupDetailFB" property="selectedUserIndex" value="<%=user.getUserId()%>" onclick="validateIfAdded(this)" />
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
														<html:checkbox name="ProfileGroupDetailFB" property="selectedUserIndex" value="<%=user.getUserId()%>" onclick="validateIfAdded(this)" />
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
		   </logic:notEqual>
		   <logic:equal name="ProfileGroupDetailFB" property="hmode" value="VIEW">
		       		<his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="40%"  class="tdfonthead">
							<div align="left" >
							<html:checkbox name="ProfileGroupDetailFB" property="selectedUserUnitSpecificType" value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC %>" disabled="true" onclick="showunitUserSpecific(this)" ></html:checkbox>
							<bean:message key="unitSpecific" />
							</div>
							</td>
							
						</tr>
					</table>
				</his:ContentTag>
			
			
			<div id="unitPrivilegdeId" style="display:none;" >
				<his:TitleTag name="Given Unit Privilegdes">
				</his:TitleTag>
				<his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="40%"  class="tdfonthead"></td>
							<td width="20%"  class="tdfont"></td>
							<td width="40%" class="tdfonthead"></td>
						</tr>
						<tr>
							
							<td width="40%"  class="tdfonthead" colspan="3">
								<div align="center">
									<html:select name="ProfileGroupDetailFB" property="selectedUnits" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST%>" >
										<html:options collection="<%=OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST%>" property="value" labelProperty="label" />
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
				<his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="40%"  class="tdfonthead">
							<div align="left" >
							<html:checkbox name="ProfileGroupDetailFB" property="selectedUserUnitSpecificType" value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND%>" disabled="true"  onclick="showunitUserSpecific(this)" ></html:checkbox>
							<bean:message key="userSpecific" />
							</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				
			<div id="userPrivilegdeId" style="display:none;" > 
					<his:TitleTag name="Given User Privilegdes">
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
										
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>
					</his:ContentTag>
				</div>
					
		       </logic:equal>
		  	  
	    
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			   <his:statusTransactionInProcess>
				<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST%>">
					<logic:equal name="ProfileGroupDetailFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateAddUser(),'ADDACCESSPRIVUSER');" onclick="submitFormOnValidate(validateAddUser(),'ADDACCESSPRIVUSER');" tabindex="1" />
					</logic:equal>
					<logic:equal name="ProfileGroupDetailFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateAddUser(),'ADDACCESSPRIVUSER');" onclick="submitFormOnValidate(validateAddUser(),'ADDACCESSPRIVUSER');" tabindex="1" />
					</logic:equal>
				</logic:notEmpty>
				</his:statusTransactionInProcess>	
			    <logic:equal name="ProfileGroupDetailFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>
				
				<logic:equal name="ProfileGroupDetailFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmitModify('MODIFYSAVE')" onclick="finalSubmitModify('MODIFYSAVE')">
				</logic:equal>
				
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 
		 <center><b><his:status/></b></center>
        
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  