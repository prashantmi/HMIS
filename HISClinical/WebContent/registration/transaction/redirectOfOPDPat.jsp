
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
	if(document.getElementsByName("departmentUnitCode")[0].value=="-1")
	{
		alert("Please Select Unit");
		document.getElementsByName("departmentUnitCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("roomNo")[0].value=="-1")
	{
		alert("Please Select Room");
		document.getElementsByName("roomNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("transferDepartmentUnitCode")[0].value=="-1")
	{
		alert("Please Select Redirect Unit");
		document.getElementsByName("transferDepartmentUnitCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("transferRoomCode")[0].value=="-1")
	{
		alert("Please Select Redirect Room");
		document.getElementsByName("transferRoomCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("changeReason")[0].value=="")
	{
		alert("Please Enter Change Reason");
		document.getElementsByName("changeReason")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("departmentUnitCode")[0].value==document.getElementsByName("transferDepartmentUnitCode")[0].value && document.getElementsByName("transferRoomCode")[0].value==document.getElementsByName("roomNo")[0].value)
	{
		alert("You can not transfer patient in same Department Unit Room");
		document.getElementsByName("transferDepartmentUnitCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("modeOfTransfer")[1].checked)
	{
		var len=document.getElementsByName("selectedPatArray").length;
		//alert("len "+len);
		var flag=false;
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("selectedPatArray")[i].checked)
			{
				flag=true;
			}
		}
		if(!flag)
		{
			alert("Please Select at least one patient ");
			document.getElementsByName("selectedPatArray")[0].focus();
			return false;
		}
	}
	
	var todayPatListFlag=document.getElementsByName("todayPatListFlag")[0].value;
	if(todayPatListFlag==0)
	{
		var len=document.getElementsByName("roomShiftArray").length;
		//alert("len "+len);
		var flag=false;
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("roomShiftArray")[i].checked)
			{
				flag=true;
			}
		}
		if(!flag)
		{
			alert("Please Select at least one Deactive Room  ");
			document.getElementsByName("roomShiftArray")[0].focus();
			return false;
		}
	}
	
	
	submitPage(mode);
}

function clearForm()
{
	document.getElementsByName("roomShiftArray")[0].value=null;
	document.getElementsByName("hiddenRoomShiftArray")[0].value=null;
	
	document.getElementsByName("transferRoomCode")[0].value="-1";
	document.getElementsByName("transferDepartmentUnitCode")[0].value="-1";
	
	document.getElementsByName("changeReason")[0].value="";
}
       
 </script>

<html:form action="/redirectOfOPDPatient">
<body onload="showInformation()">
<his:TransactionContainer>
     <his:TitleTag name="Redirect Of OPD Patient">
     </his:TitleTag>
     
     <his:ContentTag>
	     <table width="100%" border="0" cellspacing="1" cellpadding="0" >
	     	<tr>
	     		<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="units"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:select name="RedirectOfOPDPatFB" property="departmentUnitCode" tabindex="1" disabled="false" onchange="getRoomList('GETROOMS')">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.LIST_OF_DEPT_UNIT_BY_ROSTER%>" >
							<html:options collection="<%=RegistrationConfig.LIST_OF_DEPT_UNIT_BY_ROSTER%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="room"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:select name="RedirectOfOPDPatFB" property="roomNo" tabindex="1" disabled="false" onchange="getRoomDetail('GETROOMDTL')">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.ROOM_LIST_BY_DEPT_UNIT%>" >
							<html:options collection="<%=RegistrationConfig.ROOM_LIST_BY_DEPT_UNIT%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
				 	</div>
				 </td>
	     	</tr>
	     </table>
	     
	    <logic:present name="<%=RegistrationConfig.PARTICULAR_ROOM_DETAIL_BY_DEPT_UNIT %>">
		<logic:notEmpty name="<%=RegistrationConfig.PARTICULAR_ROOM_DETAIL_BY_DEPT_UNIT %>">
		<his:SubTitleTag name="Room Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="5%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="deactive"/>	
				  			</font>
				  		</div>
		      		</td>
					<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="roomName"/>
				  			</font>
				  		</div>
		      		</td>
			      	<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="startTime"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="endTime"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="status"/>	
				  			</font>
				  		</div>
			      	</td>	
				</tr>
			<logic:iterate id="deptUnitRosterVO" indexId="id" name="<%=RegistrationConfig.PARTICULAR_ROOM_DETAIL_BY_DEPT_UNIT %>" type="hisglobal.vo.DeptUnitRosterVO">
				<%String index=id.toString(); %>
				<tr>
					<td  class="tdfont" width="5%"  >
						<div align="center" >
							<%if(deptUnitRosterVO.getStatus().equals("Active"))
							{
							%>
							<html:checkbox property="roomShiftArray" name="RedirectOfOPDPatFB" value='<%=deptUnitRosterVO.getStartDateTime()+"#"+deptUnitRosterVO.getEndDateTime()+"#"+index %>'></html:checkbox>
							<%
							}
							else
							{
							%>
							<html:checkbox property="roomShiftArray" name="RedirectOfOPDPatFB" value='<%=deptUnitRosterVO.getStartDateTime()+"#"+deptUnitRosterVO.getEndDateTime()+"#"+index %>' disabled="true"></html:checkbox>
							<%	
							}
							%>
							
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="roomName"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="startDate"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="endDate"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="deptUnitRosterVO" property="status"/>
						</div>
					</td>
				</tr>
			</logic:iterate>	
			</table>
			
		<div id="redirectUnitRoomDetailId" style="display: block;">
		<his:SubTitleTag name="Redirect Unit Room Detail">
		</his:SubTitleTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
	     	<tr>
	     		<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="units"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:select name="RedirectOfOPDPatFB" property="transferDepartmentUnitCode" tabindex="1" disabled="false" onchange="getActiveRoomList('GETACTIVEROOMS')">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.LIST_OF_ALL_ACTIVE_DEPT_UNIT_BY_ROSTER%>" >
							<html:options collection="<%=RegistrationConfig.LIST_OF_ALL_ACTIVE_DEPT_UNIT_BY_ROSTER%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="room"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:select name="RedirectOfOPDPatFB" property="transferRoomCode" tabindex="1" disabled="false">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.ALL_ACTIVE_ROOM_LIST_BY_DEPT_UNIT%>" >
							<html:options collection="<%=RegistrationConfig.ALL_ACTIVE_ROOM_LIST_BY_DEPT_UNIT%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
				 	</div>
				 </td>
	     	</tr>
	     	<tr id="rowDeactivateRoomId">
		    	<td class="tdfonthead" width="25%" >
					<div align="right">	           
				 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 			<bean:message key="modeOfTransfer"/>	
				  		</font>
				  	</div>
		      	</td>
		      	<td  class="tdfont" width="25%"  >
						<div align="left" >
							All Patient<html:radio property="modeOfTransfer"  value="<%=RegistrationConfig.MODE_OF_TRANSFER_ALL_PAT %>" name="RedirectOfOPDPatFB" onclick="showDetail('GETPATLIST')"></html:radio>
							Patient Wise<html:radio property="modeOfTransfer" value="<%=RegistrationConfig.MODE_OF_TRANSFER_PAT_WISE %>" name="RedirectOfOPDPatFB" onclick="showDetail('GETPATLIST')"></html:radio>
						</div>
					</td>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="changeReason"/>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="1" width="25%">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="RedirectOfOPDPatFB" tabindex="1" rows="2" cols="30" property="changeReason" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
			</tr>
	     </table>
		</div>
		<his:statusList>	
		<logic:present name="<%=RegistrationConfig.TODAY_VISIT_PAT_LIST_BY_ROOM %>">
		<logic:notEmpty name="<%=RegistrationConfig.TODAY_VISIT_PAT_LIST_BY_ROOM %>">
		<his:SubTitleTag name="Patient Detail List">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="5%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="select"/>	
				  			</font>
				  		</div>
		      		</td>
					<td class="tdfonthead" width="5%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="qNo"/>	
				  			</font>
				  		</div>
		      		</td>
			      	<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="crNo"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="name"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="gender/age"/>	
				  			</font>
				  		</div>
			      	</td>
			      		<td class="tdfonthead" width="21%" >
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="patientCategory"/>
				  			</font>
				  		</div>
			      	</td>	
				</tr>
			<logic:iterate id="dailyPatientVO"  name="<%=RegistrationConfig.TODAY_VISIT_PAT_LIST_BY_ROOM %>" type="hisglobal.vo.DailyPatientVO">
				<tr>
					<td  class="tdfont" width="5%"  >
						<div align="center" >
							<html:checkbox property="selectedPatArray" value='<%=dailyPatientVO.getPatCrNo()+"#"+dailyPatientVO.getEpisodeCode()+"#"+dailyPatientVO.getEpisodeVisitNo()+"#"+dailyPatientVO.getQueNo()+"#"+dailyPatientVO.getChnageToRoomCode()%>' name="RedirectOfOPDPatFB"></html:checkbox>
						</div>
					</td>
					<td  class="tdfont" width="5%"  >
						<div align="center" >
							<bean:write name="dailyPatientVO" property="queNo"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="dailyPatientVO" property="patCrNo"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="dailyPatientVO" property="patName"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="dailyPatientVO" property="patGenderAge"/>
						</div>
					</td>
					<td  class="tdfont" width="21%"  >
						<div align="center" >
							<bean:write name="dailyPatientVO" property="patPrimaryCat"/>
						</div>
					</td>
				</tr>
			</logic:iterate>	
			</table>
		</logic:notEmpty>
		</logic:present>
		</his:statusList>
		</logic:notEmpty>
		</logic:present>
     </his:ContentTag>
     
	<his:ButtonToolBarTag>
		<his:statusInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
		</his:statusInProcess>
		<his:statusDone>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex="1" onclick ="validateForm('SAVE')" onkeypress="if(event.keyCode==13) validateForm('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  tabindex="1" onclick =" submitForm('GETROOMDTL')" onkeypress="if(event.keyCode==13) submitForm('GETROOMDTL')">
		</his:statusDone>
		<his:statusList>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex="1" onclick ="validateForm('SAVE')" onkeypress="if(event.keyCode==13) validateForm('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer  tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer  tabindex="1" onclick ="submitForm('GETROOMDTL')" onkeypress="if(event.keyCode==13) submitForm('GETROOMDTL')">
		</his:statusList>
		
		
	</his:ButtonToolBarTag>
	
	<html:hidden name="RedirectOfOPDPatFB" property="hmode" />
	<html:hidden name="RedirectOfOPDPatFB" property="hiddenIndexArray" />
	<html:hidden name="RedirectOfOPDPatFB" property="hiddenSelectedPatArray" />
	<html:hidden name="RedirectOfOPDPatFB" property="todayPatListFlag" />
			
</his:TransactionContainer>	
	</body>
	</html:form>
<his:status/>


</html>