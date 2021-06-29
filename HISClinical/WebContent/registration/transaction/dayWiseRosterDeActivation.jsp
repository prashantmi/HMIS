<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="registration.controller.fb.HandoverToDeadBodyFB"%>
<%@page import="hisglobal.vo.PatientDeathDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	
	<his:javascript src="/registration/js/registration.js" />
	<his:javascript src="/registration/js/validationCalls.js" />
	<his:javascript src="/registration/js/validationCommon.js" />
	<his:javascript src="/registration/js/commonFunctions.js" />
	<his:javascript src="/hisglobal/js/calendar.js"/>
	<his:javascript src="/registration/js/popup.js"/>

<script type="text/javascript">


function dateCheckLess()
{
	  var aArray=(document.getElementsByName("deactivationDate")[0].value).split("-");
      var amonth=aArray[1];
      
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      
      var bArray=(document.getElementsByName("todayDate")[0].value).split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
      if(adate < bdate)
      {
      alert("Date cannot be less than the Current Date");
      document.forms[0].deactivationDate.focus(); 
      return false;
      }
 	  else
 	 return true;
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getRoomList(mode)
{
	submitPage(mode);
}    

function getRoomDetail(mode)
{
	submitPage(mode);
}   

function showReason()
{
	if(document.getElementsByName("deactivateRoom")[0].checked)
	{
		document.getElementById("rowDeactivateRoomId").style.display="";
	}
	else
	{
		document.getElementsByName("deactiveReason")[0].value="";
		document.getElementById("rowDeactivateRoomId").style.display="none";
	}
}

function showDetail(mode)
{
	/*
	if(document.getElementsByName("modeOfTransfer")[1].checked==true)
	{
		submitPage(mode);
	}
	*/
	//document.getElementById("redirectUnitRoomDetailId").style.display="";
	submitPage(mode);
}

function getActiveRoomList(mode)
{
	submitPage(mode);
}

function showInformation()
{
	
	var index=document.getElementsByName("hiddenIndexArray")[0].value;
	var arr=index.split("#");
	//alert("indexArray "+arr);
	var chks=document.getElementsByName('roomShiftArray');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value.split("#")[2]==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}
function checkedSelectedValue()
{
	var str=document.getElementsByName("tempChkValue")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedCheckList');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}
function validateForm(mode)
{

	if(document.getElementsByName("departmentCode")[0].value=="-1")
	{
		alert("Please Select Department");
		document.getElementsByName("departmentCode")[0].focus();
		return false;
	}
	else
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		alert("Please Select Unit");
		document.getElementsByName("unitCode")[0].focus();
		return false;
	}
	else
	if(document.getElementsByName("deactivationDate")[0].value=="")
	{
		alert("Please Select Deactivation Date");
		document.getElementsByName("deactivationDate")[0].focus();
		return false;
	}
	
	
	
		var roomShiftArray=document.getElementsByName("roomShiftArray").length;
	
		var flag1=false;
		for(var i=0;i<roomShiftArray;i++)
		{
			if(document.getElementsByName("roomShiftArray")[i].checked)
			{
			  flag1=true; 
			  			  
			  if(document.getElementsByName("reasonArray")[i].value==""){
				  alert("Please Enter Reason to DeActive");
				  document.getElementsByName("reasonArray")[i].focus();
				  return false; 
				}

			}
		}
		
		
		
		var roomShiftArrayInActive=document.getElementsByName("roomShiftArrayInActive").length;
	
		var flag2=false;
		for(var i=0;i<roomShiftArrayInActive;i++)
		{
			if(document.getElementsByName("roomShiftArrayInActive")[i].checked)
			{
			  flag2=true; 
			  			  
			  if(document.getElementsByName("reasonArrayInActive")[i].value==""){
				  alert("Please Enter Reason to Active");
				  document.getElementsByName("reasonArrayInActive")[i].focus();
				  return false; 
				}

			}
		}
		
		
		if(flag1==false && flag2==false)
		{
			alert("Please Select at Least one  Room to Active/DeActive ");
			document.getElementsByName("roomShiftArray")[0].focus();
			return false;
		}
	    else	
		submitPage(mode);
}

function clearForm()
{
	var roomShiftArray=document.getElementsByName("roomShiftArray").length;
	
	
		for(var i=0;i<roomShiftArray;i++)
		{
			document.getElementsByName("roomShiftArray")[i].checked=false;
			document.getElementsByName("reasonArray")[i].value="";
			document.getElementsByName("reasonArray")[i].disabled=true;
			
		}

		
		var roomShiftArrayInActive=document.getElementsByName("roomShiftArrayInActive").length;
	
	
		for(var i=0;i<roomShiftArrayInActive;i++)
		{
			document.getElementsByName("roomShiftArrayInActive")[i].checked=false;
			document.getElementsByName("reasonArrayInActive")[i].value="";
			document.getElementsByName("reasonArrayInActive")[i].disabled=true;
			
		}
}

function validateGoSubmit(event,mode){

		

if(document.getElementsByName("departmentCode")[0].value=="-1")
	{
		alert("Please Select Department");
		document.getElementsByName("departmentCode")[0].focus();
		return false;
	}
	else
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		alert("Please Select Unit");
		document.getElementsByName("unitCode")[0].focus();
		return false;
	}
	else
	if(document.getElementsByName("deactivationDate")[0].value=="")
	{
		alert("Please Select Deactivation Date");
		document.getElementsByName("deactivationDate")[0].focus();
		return false;
	}
	else
if(dateCheckLess()==false)
	return false;
	else
	submitPage(mode);

}       

function enableReason(these){
var index=these.value.split("#")[2];

if(these.checked==true)
	document.getElementsByName("reasonArray")[index].disabled=false;
else{
	document.getElementsByName("reasonArray")[index].value="";
	document.getElementsByName("reasonArray")[index].disabled=true;
	}
}
      
  
function enableReasonInActive(these){
var index=these.value.split("#")[2];

if(these.checked==true)
	document.getElementsByName("reasonArrayInActive")[index].disabled=false;
else{
	document.getElementsByName("reasonArrayInActive")[index].value="";
	document.getElementsByName("reasonArrayInActive")[index].disabled=true;
	}
}
       
 </script>
 
<bean:define id="deactivationDateId" name="DayWiseRosterDeActivationFB" property="deactivationDate" type="java.lang.String" /> 
 
 				
<%
String sysDate =WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

if(deactivationDateId==null || deactivationDateId.equals(""))
	deactivationDateId=sysDate;
%>
<html:form action="/dayWiseRosterDeActivation">
<body >
<his:TransactionContainer>
     <his:TitleTag name="Daywise Roster Activation/DeActivation">
     </his:TitleTag>
     
     <his:ContentTag>
	     <table width="100%" border="0" cellspacing="1" cellpadding="0" >
	     	<tr>
	     		<td class="tdfonthead" width="20%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="department"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="17%" >
				 	<div align="left">
				 	
				 		<his:statusTransactionInProcess>
				 	
				 		<html:select name="DayWiseRosterDeActivationFB" property="departmentCode" styleClass="regCbo"  tabindex="1" disabled="false" onchange="getRoomList('GETUNITS_DEPARTMENTWISE')">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" >
							<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</his:statusTransactionInProcess>
					
					
									
					
					<his:statusDone>
					<bean:write name="DayWiseRosterDeActivationFB" property="departmentName"/>
					<html:hidden property="departmentCode" name="DayWiseRosterDeActivationFB" />
					</his:statusDone>
						
				 	</div>
				 </td>
				 <td class="tdfonthead" width="10%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="unit"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="17%" >
				 	<div align="left">
				 	
				 	<his:statusTransactionInProcess>
				 		<html:select name="DayWiseRosterDeActivationFB" property="unitCode" tabindex="1" styleClass="regCbo" disabled="false" >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" >
							<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</his:statusTransactionInProcess>
						
					<his:statusDone>
						<bean:write name="DayWiseRosterDeActivationFB" property="unitName"/>
						<html:hidden property="unitCode" name="DayWiseRosterDeActivationFB" />
					</his:statusDone>
					
				 	</div>
				 </td>
				 
				<td class="tdfonthead" width="14%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="date"/>
			 			</font>
			  		</div>
		      	</td>
		      	
		      	 <td class="tdfont" width="31%" >
				 	<div align="left">
				 			<his:statusTransactionInProcess>
				 		  <his:date name='deactivationDate' dateFormate="%d-%b-%Y" value="<%=deactivationDateId%>"/>
				 		</his:statusTransactionInProcess>
				 		
				 	<his:statusDone>
				 		<html:hidden property="deactivationDate" name="DayWiseRosterDeActivationFB" />
						<bean:write name="DayWiseRosterDeActivationFB" property="deactivationDate"/>
					</his:statusDone>
					
				 	</div>
				 </td>
				 
	     	</tr>
	     	
	     	
	     	
	     </table>
	     
	    <logic:present name="<%=RegistrationConfig.UNIT_DATE_WISE_ACTIVE_ROOM_LIST %>">
		<logic:notEmpty name="<%=RegistrationConfig.UNIT_DATE_WISE_ACTIVE_ROOM_LIST %>">
		<his:SubTitleTag name="Active Room Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="10%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="deactive"/>	
				  			</font>
				  		</div>
		      		</td>
					<td class="tdfonthead" width="20%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="roomName"/>
				  			</font>
				  		</div>
		      		</td>
			      	<td class="tdfonthead" width="20%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="startTime"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="20%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="endTime"/>	
				  			</font>
				  		</div>
			      	</td>
			      	
			      	<td class="tdfonthead" width="30%" >
				  		<div align="center">	
				  		      <font color="#FF0000">*</font>     
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="deactive"/> <bean:message key="reason"/>	
				  			</font>
				  		</div>
			      	</td>
			      	
				</tr>
			<logic:iterate id="deptUnitRosterVO" indexId="id" name="<%=RegistrationConfig.UNIT_DATE_WISE_ACTIVE_ROOM_LIST %>" type="hisglobal.vo.DeptUnitRosterVO">
				<%String index=id.toString(); %>
				<tr>
					<td  class="tdfont" width="10%"  >
						<div align="center" >
							
							<html:checkbox property="roomShiftArray" name="DayWiseRosterDeActivationFB" tabindex="1" value='<%=deptUnitRosterVO.getStartDateTime()+"#"+deptUnitRosterVO.getEndDateTime()+"#"+index+"#"+deptUnitRosterVO.getRoomCode() %>' onclick="enableReason(this)" />
														
						</div>
					</td>
					<td  class="tdfont" width="20%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="roomName"/>
						</div>
					</td>
					<td  class="tdfont" width="20%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="startDate"/>
						</div>
					</td>
					<td  class="tdfont" width="20%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="endDate"/>
						</div>
					</td>
				
				 <td  class="tdfont" width="30%"  >
						<div align="center" >
							
							<html:textarea property="reasonArray" name="DayWiseRosterDeActivationFB" tabindex="1" cols="20" rows="1" disabled="true"/>
														
						</div>
					</td>
				
					
				</tr>
			</logic:iterate>	
			</table>
			
		
		
		</logic:notEmpty>
		</logic:present>
		
		
		
		 
		
		
		
		
		 <logic:present name="<%=RegistrationConfig.UNIT_DATE_WISE_INACTIVE_ROOM_LIST %>">
		<logic:notEmpty name="<%=RegistrationConfig.UNIT_DATE_WISE_INACTIVE_ROOM_LIST %>">
		<his:SubTitleTag name="InActive Room Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="10%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="active"/>	
				  			</font>
				  		</div>
		      		</td>
					<td class="tdfonthead" width="20%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="roomName"/>
				  			</font>
				  		</div>
		      		</td>
			      	<td class="tdfonthead" width="20%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="startTime"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="20%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="endTime"/>	
				  			</font>
				  		</div>
			      	</td>
			      	
			      	<td class="tdfonthead" width="30%" >
				  		<div align="center">	
				  		      <font color="#FF0000">*</font>     
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 			<bean:message key="active"/> <bean:message key="reason"/>	
				  			</font>
				  		</div>
			      	</td>
			      	
				</tr>
			<logic:iterate id="deptUnitRosterVO" indexId="id" name="<%=RegistrationConfig.UNIT_DATE_WISE_INACTIVE_ROOM_LIST %>" type="hisglobal.vo.DeptUnitRosterVO">
				<%String index=id.toString(); %>
				<tr>
					<td  class="tdfont" width="10%"  >
						<div align="center" >
							
							<html:checkbox property="roomShiftArrayInActive" name="DayWiseRosterDeActivationFB" tabindex="1" value='<%=deptUnitRosterVO.getStartDateTime()+"#"+deptUnitRosterVO.getEndDateTime()+"#"+index+"#"+deptUnitRosterVO.getRoomCode() %>' onclick="enableReasonInActive(this)" />
														
						</div>
					</td>
					<td  class="tdfont" width="20%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="roomName"/>
						</div>
					</td>
					<td  class="tdfont" width="20%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="startDate"/>
						</div>
					</td>
					<td  class="tdfont" width="20%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="endDate"/>
						</div>
					</td>
				
				 <td  class="tdfont" width="30%"  >
						<div align="center" >
							
							<html:textarea property="reasonArrayInActive" name="DayWiseRosterDeActivationFB" tabindex="1" cols="20" rows="1" disabled="true"/>
														
						</div>
					</td>
				
					
				</tr>
			</logic:iterate>	
			</table>
			
		
		
		</logic:notEmpty>
		</logic:present>
		
		
		
     </his:ContentTag>
     
	<his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>'   style="cursor: pointer; " tabindex="1" onkeypress="if(event.keyCode==13)validateGoSubmit(event,'GETROOMDTL')" onclick="validateGoSubmit(event,'GETROOMDTL')">
		</his:statusTransactionInProcess>
		
		<his:statusDone>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex="1" onclick ="validateForm('SAVE')" onkeypress="if(event.keyCode==13) validateForm('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
		</his:statusDone>
		
		
		
	</his:ButtonToolBarTag>
	
	<html:hidden name="DayWiseRosterDeActivationFB" property="hmode" />
	<html:hidden name="DayWiseRosterDeActivationFB" property="hiddenIndexArray" />
	<html:hidden name="DayWiseRosterDeActivationFB" property="hiddenSelectedPatArray" />
	<html:hidden name="DayWiseRosterDeActivationFB" property="todayPatListFlag" />
	<html:hidden name="DayWiseRosterDeActivationFB" property="todayDate" value="<%=sysDate%>"/>
	<his:status/>
		
			
</his:TransactionContainer>	
	</body>
	</html:form>



</html>
