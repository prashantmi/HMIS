<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getSchedule(){
	
	if(document.getElementsByName("certificateTypeID")[0].value=="-1")
	{
		alert("Please select certificate type");
		document.getElementsByName("certificateTypeID")[0].focus();
		return false;
	}
	
	 var winl = (screen.width - 100) / 2;
	 var wint = (screen.height - 200) / 2;

    var departmentUnitCode=document.getElementsByName("certificateTypeID")[0].value.split("#")[1];
	var certificateTypeId=document.getElementsByName("certificateTypeID")[0].value.split("#")[0];

	//alert("departmentUnitCode " +departmentUnitCode);
	//alert("certificateTypeId "+ certificateTypeId);
	
    popup=window.open(createFHashAjaxQuery("/HISClinical/medicalboard/medicalBoardInitialization.cnt?hmode=GETSCHEDULELIST&deptUnitCode=" + departmentUnitCode + "&certificateTypeID=" + certificateTypeId + "&index=0") ,
    "Select Schedule","height=300,width=500 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
	popup.opener = self;
    }
}  
	
function AddRowToDocRoleTable(mode)
{
	if(document.getElementsByName("empID")[0].value=="-1")
	{
		alert("Please select doctor");
		document.getElementsByName("empID")[0].focus();
		return false;
	}
	if(document.getElementsByName("roleID")[0].value=="-1")
	{
		alert("Please select role");
		document.getElementsByName("roleID")[0].focus();
		return false;
	}
	
	var len=document.getElementsByName("docRoleArray").length;
	
	for(var i=0;i<len;i++)
	{
		var empid=document.getElementsByName("docRoleArray")[i].length;
		if(document.getElementsByName("empID")[0].value==document.getElementsByName("docRoleArray")[i].value)
		{
			alert("This doctor already added");
			document.getElementsByName("empID")[0].focus();
			return false;
		}
	}
	
	
	submitPage(mode);
}	

function AddEscortedRowToTable(mode)
{
	if(document.getElementsByName("escortedEmpID")[0].value=="-1")
	{
		alert("Please select escorted by");
		document.getElementsByName("escortedEmpID")[0].focus();
		return false;
	}
	
	var len=document.getElementsByName("escortedArray").length;
	
	for(var i=0;i<len;i++)
	{
		var empid=document.getElementsByName("docRoleArray")[i].length;
		if(document.getElementsByName("escortedEmpID")[0].value==document.getElementsByName("escortedArray")[i].value)
		{
			alert("This escorted by already added");
			document.getElementsByName("escortedEmpID")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function deleteDocRoleRow(mode,index)
{
	//alert("index "+index);
	document.getElementsByName("docRoleIndex")[0].value=index;
	submitPage(mode);
}

function deleteEscortedRow(mode,index)
{
	//alert("index "+index);
	document.getElementsByName("escortedRowIndex")[0].value=index;
	submitPage(mode);
}


function showTeamDetail(mode)
{
	//document.getElementById("teamDetailDivId").style.display="";
	var len=document.getElementsByName("selectedBoardArray").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedBoardArray")[i].checked)
		{
			document.getElementsByName("selectedBoard")[0].value=document.getElementsByName("selectedBoardArray")[i].value;
		}
	}
	
	
	
	submitPage(mode);
}

function validateForm(mode)
{
	var docRoleSize=document.getElementsByName("docRoleArray").length;
	
	if(docRoleSize==0)
	{
		alert("Please select doctor and role "+'\n'+" then click on plus button");
		document.getElementsByName("empID")[0].focus();
		return false;
	}
	
	var escortSize=document.getElementsByName("escortedArray").length;
	//alert("escortSize "+escortSize);
	if(escortSize==0)
	{
		alert("Please select escorted by"+'\n'+" then click on plus button");
		document.getElementsByName("escortedEmpID")[0].focus();
		return false;
	}
	
	
	if(document.getElementsByName("location")[0].value=="")
	{
		alert("Please enter location");
		document.getElementsByName("location")[0].focus();
		return false;
	}
	
	submitPage(mode);
}

function validateNewBoardAddForm(mode)
{
	var docRoleSize=document.getElementsByName("addNewBoardDocArray").length;
	
	if(docRoleSize==0)
	{
		alert("Please select doctor and role "+'\n'+" then click on plus button");
		document.getElementsByName("empID")[0].focus();
		return false;
	}
	
	var escortSize=document.getElementsByName("addNewBoardEscortArray").length;
	//alert("escortSize "+escortSize);
	if(escortSize==0)
	{
		alert("Please select escorted by"+'\n'+" then click on plus button");
		document.getElementsByName("escortedEmpID")[0].focus();
		return false;
	}
	
	
	if(document.getElementsByName("location")[0].value=="")
	{
		alert("Please enter location");
		document.getElementsByName("location")[0].focus();
		return false;
	}
	
	submitPage(mode);
}

function validateModifySave(mode)
{
	
	var docRoleSize=document.getElementsByName("modifyDocArray").length;
	
	if(docRoleSize==0)
	{
		alert("Please select doctor and role "+'\n'+" then click on plus button");
		document.getElementsByName("empID")[0].focus();
		return false;
	}
	
	var escortSize=document.getElementsByName("modifyEscortedArray").length;
	//alert("escortSize "+escortSize);
	if(escortSize==0)
	{
		alert("Please select escorted by"+'\n'+" then click on plus button");
		document.getElementsByName("escortedEmpID")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("location")[0].value=="")
	{
		alert("Please enter location");
		document.getElementsByName("location")[0].focus();
		return false;
	}
	
	submitPage(mode);
}

function showAssignTeamDetail(mode)
{
	var len=document.getElementsByName("assignBoardNoArray").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("assignBoardNoArray")[i].checked)
		{
			document.getElementsByName("selectedAssignBoardNo")[0].value=document.getElementsByName("assignBoardNoArray")[i].value.split("#")[0];
			document.getElementsByName("selectedAssignBoardId")[0].value=document.getElementsByName("assignBoardNoArray")[i].value.split("#")[1];
			document.getElementsByName("selectedLocation")[0].value=document.getElementsByName("locationArray")[i].value;
			//alert("board no"+ document.getElementsByName("selectedAssignBoardNo")[0].value);
		}
	}
	
	submitPage(mode);
}

function deleteAssignEscortedRow(mode,index)
{
	document.getElementsByName("escortedAssignRowIndex")[0].value=index;
	submitPage(mode);
}

function AddAssignEscortedRowToTable(mode)
{
	if(document.getElementsByName("escortedEmpID")[0].value=="-1")
	{
		alert("Please select escorted by");
		document.getElementsByName("escortedEmpID")[0].focus();
		return false;
	}
	
	var len=document.getElementsByName("modifyEscortedArray").length;
	
	for(var i=0;i<len;i++)
	{
		var empid=document.getElementsByName("modifyEscortedArray")[i].length;
		if(document.getElementsByName("escortedEmpID")[0].value==document.getElementsByName("modifyEscortedArray")[i].value)
		{
			alert("This escorted by already added");
			document.getElementsByName("escortedEmpID")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}
function deleteAssignDocRoleRow(mode,index)
{
	document.getElementsByName("docRoleAssignIndex")[0].value=index;
	submitPage(mode);
}

function AddAssignRowToDocRoleTable(mode)
{
	if(document.getElementsByName("empID")[0].value=="-1")
	{
		alert("Please select doctor");
		document.getElementsByName("empID")[0].focus();
		return false;
	}
	if(document.getElementsByName("roleID")[0].value=="-1")
	{
		alert("Please select role");
		document.getElementsByName("roleID")[0].focus();
		return false;
	}
	
	var len=document.getElementsByName("modifyDocArray").length;
	
	for(var i=0;i<len;i++)
	{
		var empid=document.getElementsByName("modifyDocArray")[i].length;
		if(document.getElementsByName("empID")[0].value==document.getElementsByName("modifyDocArray")[i].value)
		{
			alert("This doctor already added");
			document.getElementsByName("empID")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function validateAddForm(mode)
{
	submitPage(mode);
}

function AddRowToDocRoleNewBoard(mode)
{
	if(document.getElementsByName("empID")[0].value=="-1")
	{
		alert("Please select doctor");
		document.getElementsByName("empID")[0].focus();
		return false;
	}
	if(document.getElementsByName("roleID")[0].value=="-1")
	{
		alert("Please select role");
		document.getElementsByName("roleID")[0].focus();
		return false;
	}
	
	var len=document.getElementsByName("addNewBoardDocArray").length;
	
	for(var i=0;i<len;i++)
	{
		var empid=document.getElementsByName("addNewBoardDocArray")[i].length;
		if(document.getElementsByName("empID")[0].value==document.getElementsByName("addNewBoardDocArray")[i].value)
		{
			alert("This doctor already added");
			document.getElementsByName("empID")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}	

function AddEscortedRowToNewBoard(mode)
{
	if(document.getElementsByName("escortedEmpID")[0].value=="-1")
	{
		alert("Please select escorted by");
		document.getElementsByName("escortedEmpID")[0].focus();
		return false;
	}
	
	var len=document.getElementsByName("addNewBoardEscortArray").length;
	
	for(var i=0;i<len;i++)
	{
		var empid=document.getElementsByName("addNewBoardEscortArray")[i].length;
		if(document.getElementsByName("escortedEmpID")[0].value==document.getElementsByName("addNewBoardEscortArray")[i].value)
		{
			alert("This escorted by already added");
			document.getElementsByName("escortedEmpID")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function deleteDocRoleRowNewBoard(mode,index)
{
	//alert("index "+index);
	document.getElementsByName("docRoleIndex")[0].value=index;
	submitPage(mode);
}

function deleteEscortedRowNewBoard(mode,index)
{
	//alert("index "+index);
	document.getElementsByName("escortedRowIndex")[0].value=index;
	submitPage(mode);
}

function validateDeleteForm(mode)
{
	var len=document.getElementsByName("assignBoardNoArray").length;
	var flag=false;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("assignBoardNoArray")[i].checked)
		{
			flag=true;
		}
	}
	
	if(!flag)
	{
		alert("Plaese select board");
		document.getElementsByName("assignBoardNoArray")[0].focus();
		return false;
	}
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("assignBoardNoArray")[i].checked)
		{
			document.getElementsByName("selectedAssignBoardNo")[0].value=document.getElementsByName("assignBoardNoArray")[i].value.split("#")[0];
			
		
		}
	}
	
	submitPage(mode);
}

function validateModifyForm(mode)
{
	var len=document.getElementsByName("assignBoardNoArray").length;
	var flag=false;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("assignBoardNoArray")[i].checked)
		{
			flag=true;
		}
	}
	
	if(!flag)
	{
		alert("Plaese select board");
		document.getElementsByName("assignBoardNoArray")[0].focus();
		return false;
	}
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("assignBoardNoArray")[i].checked)
		{
			document.getElementsByName("selectedAssignBoardNo")[0].value=document.getElementsByName("assignBoardNoArray")[i].value.split("#")[0];
			document.getElementsByName("selectedAssignBoardId")[0].value=document.getElementsByName("assignBoardNoArray")[i].value.split("#")[1];
		
		}
	}
	
	submitPage(mode);
}

function refreshPage(mode)
{
	document.getElementsByName("scheduleDate")[0].value="";
	document.getElementsByName("minReq")[0].value="";
	document.getElementsByName("maxReq")[0].value="";
	document.getElementsByName("registeredReq")[0].value="";
	
	submitPage(mode);
}

function clearForm()
{
	document.getElementsByName("location")[0].value="";
}

</script>
<his:TransactionContainer>
<html:form action="/medicalBoardInitialization">	   
		   
   <his:TitleTag name="Medical Board Initialization"> 		
   </his:TitleTag>
   <his:ContentTag>
   <table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
	   		<td width="25%" class="tdfonthead">
	   			<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    	<b><bean:message key="certificateType"/></b>
				 	</font>
				 </div>	
		   </td>
		  <td width="25%" class="tdfont">
		  	<div align="left">
			  	<html:select name="MedicalBoardInitializationFB" property="certificateTypeID" tabindex="1" styleClass="regcbo" onchange="refreshPage('REFERESHPAGE');">
					<html:option value="-1">Select</html:option>
					<logic:present name="<%=MedicalBoardConfig.ALL_CERTIFICATE_TYPE_LIST %>">
					<html:options collection="<%=MedicalBoardConfig.ALL_CERTIFICATE_TYPE_LIST %>" labelProperty="label" property="value"/>
					</logic:present>
				</html:select>
			</div>	
		  </td>
		  <td width="25%" class="tdfonthead">
		   	<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="scheduleDate"/></b>
				</font>
			</div>	
		   </td>
			<td width="25%" class="tdfont">
				<div align="left">	
					<html:text property="scheduleDate" name="MedicalBoardInitializationFB" readonly="true" size="12"></html:text>
					<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>'  style=cursor:pointer border="1" 
			   				onkeypress="if(event.keyCode==13) getSchedule()" onclick="getSchedule()" title="Click to select Date">
				</div>
				<html:hidden property="minReq" name="MedicalBoardInitializationFB" ></html:hidden>
				<html:hidden property="maxReq" name="MedicalBoardInitializationFB" ></html:hidden>			
				<html:hidden property="registeredReq" name="MedicalBoardInitializationFB" ></html:hidden>
			 </td>
	   </tr>
	</table>   
	</his:ContentTag>
   
   <logic:present name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE %>">
   <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE %>">
  
   <his:SubTitleTag name="Assigned Board Detail">
   </his:SubTitleTag>
   
   <table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
			<td width="5%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="select"/></b>
				 </font>
				</div>
			</td>
			<td width="40%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="boardName"/></b>
				 </font>
				</div>
			</td>
			<td width="40%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="location"/></b>
				 </font>
				</div>
			</td>
	   </tr>
   <logic:iterate id="boardDetailVO" name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE %>" type="hisglobal.vo.BoardDetailVO" >
	   <tr>
	   		<td width="5%" class="tdfont">
			  <div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 <html:radio name="MedicalBoardInitializationFB" property="assignBoardNoArray" value='<%=boardDetailVO.getBoardNo()+"#"+boardDetailVO.getBoardId() %>' onclick="showAssignTeamDetail('GETASSIGNTEAMDETAIL')" tabindex="1"></html:radio>
				<html:hidden name="MedicalBoardInitializationFB" property="locationArray" value="<%=boardDetailVO.getLocation() %>"/>
				</font>
				</div>
			  </td>
	   		<td width="40%" class="tdfont">
			  <div align="center">
					<%=boardDetailVO.getBoardName() %>
				</div>
			  </td>
			  <td width="40%" class="tdfont">
			  <div align="center">
					<%=boardDetailVO.getLocation() %>
				</div>
			  </td>
	   </tr>
   </logic:iterate>
   </table>
   
   <his:statusRecordFound>
   <div id="teamDetailDivId" >
    <his:SubTitleTag name="Assigned Team Detail">
	</his:SubTitleTag>
		<his:ContentTag> 
			<table width="100%" id="idComboValue" border="0" cellspacing="1" cellpadding="0">
				<tr>
			    	<td width="45%" class="tdfonthead">
				    	<div align="center">
				      		<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
								<bean:message key="doctor"/>&nbsp;
					  		</font>
				    	</div>
				  	</td>
				  	<td width="45%" class="tdfonthead">
				    	<div align="center">
				      		<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
								<bean:message key="role"/>&nbsp;
					  		</font>
				    	</div>
				  	</td>
				    <td width="10%" class="tdfonthead"></td>
				 </tr>
	 <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST %>" >
	 <logic:iterate id="boardTeamDetailVO" name="<%=MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST %>" indexId="ind" type="hisglobal.vo.BoardTeamDetailVO" >
	 	<%String index=ind.toString(); %>
			 	<tr>
	 				<td width="40%" class="tdfont">
				 		<div align="center">
							<%=boardTeamDetailVO.getEmpName() %>
						</div>
					</td>
		  			<td width="40%" class="tdfont">
		  				<div align="center">
							<%=boardTeamDetailVO.getRoleName() %>
						</div>
		  			</td>
		 			<td width="10%" class="tdfont"></td>
		 		</tr>
	 </logic:iterate>		
	 </logic:notEmpty>	   		
	 </table>
	</his:ContentTag>
	
	<his:ContentTag> 
		<table width="100%" id="idEscortedBy" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="45%" class="tdfonthead">
					<div align="center">
				    	<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="escortedBy"/>&nbsp;
					  	</font>
				    </div>
				  </td>
				  <td width="45%" class="tdfonthead"></td>
				  <td width="10%" class="tdfonthead"></td>
			</tr>
				   
	 <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST %>" >
	 <logic:iterate id="boardTeamDetailVO" name="<%=MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST %>" indexId="ind" type="hisglobal.vo.BoardTeamDetailVO" >
	 	<%String index=ind.toString(); %>
		 	<tr>
	 			<td width="40%" class="tdfont">
					<div align="center">
						<%=boardTeamDetailVO.getEmpName() %>
					</div>
				</td>
		  		<td width="10%" class="tdfont"></td>
		  		<td width="10%" class="tdfont"></td>
	 		</tr>
	 </logic:iterate>		
	 </logic:notEmpty>	
	 </table>
	 </his:ContentTag>
	 <his:ContentTag> 
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="location"/>&nbsp;
						</font>
					</div>
				</td>
				<td  class="tdfont" >
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="MedicalBoardInitializationFB" tabindex="1" rows="2" cols="70" property="location" onkeypress="return (validateTextArea(event,this,'50'))" disabled="true">
						</html:textarea>
					</font>
				</td>
			</tr>	
		</table>
	</his:ContentTag>		   
	</div>
	</his:statusRecordFound>
	</logic:notEmpty>
    </logic:present>
   
    <logic:present name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY %>">
    <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY %>">
    <his:SubTitleTag name="Assigned Board Detail">
    </his:SubTitleTag>
    <table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="5%" class="tdfonthead">
		  		<div align="center" >
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  			<b><bean:message key="select"/></b>
			 		</font>
				</div>
			</td>
			<td width="40%" class="tdfonthead">
		  		<div align="center" >
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  			<b><bean:message key="boardName"/></b>
			 		</font>
				</div>
			</td>
			<td width="40%" class="tdfonthead">
		  		<div align="center" >
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  			<b><bean:message key="location"/></b>
			 		</font>
				</div>
			</td>
		</tr>
	<logic:iterate id="boardDetailVO" name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY %>" type="hisglobal.vo.BoardDetailVO" >
		<tr>
   			<td width="5%" class="tdfont">
		 		<div align="center">
					<input type="radio" name="modifyCheck" checked="checked">
				</div>
			</td>
   			<td width="40%" class="tdfont">
		  		<div align="center">
					<%=boardDetailVO.getBoardName() %>
				</div>
		  	</td>
		  	<td width="40%" class="tdfont">
		  		<div align="center">
					<%=boardDetailVO.getLocation() %>
				</div>
		  	</td>
		</tr>
   </logic:iterate>
   </table>
   
	<his:statusRecordFound>
	<div id="teamDetailDivId" >
    <his:SubTitleTag name="Assigned Team Detail">
	</his:SubTitleTag>
		<his:ContentTag> 
			<table width="100%" id="idComboValue" border="0" cellspacing="1" cellpadding="0">
				<tr>
			    	<td width="45%" class="tdfonthead">
				    	<div align="center">
				    		<font color="#FF0000" ><b>*</b></font>
				      		<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
								<bean:message key="doctor"/>&nbsp;
					  		</font>
				    	</div>
				  	</td>
				  	<td width="45%" class="tdfonthead">
				    	<div align="center">
				    		<font color="#FF0000" ><b>*</b></font>
				       			<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
									<bean:message key="role"/>&nbsp;
					  			</font>
				    	</div>
				  	</td>
				    <td width="10%" class="tdfonthead"></td>
				</tr>
				<tr>
			    	<td width="45%" class="tdfont">
			        	<div align="center">
					 		<html:select name="MedicalBoardInitializationFB" property="empID" style="width:156;" > 
								<html:option value="-1">Select Value</html:option>
						 	<logic:present name="<%=MedicalBoardConfig.LIST_OF_EMP_DOCTOR %>">
						  		<html:options collection="<%=MedicalBoardConfig.LIST_OF_EMP_DOCTOR %>" labelProperty="label" property="value"  />
				         	</logic:present>	
					 		</html:select> 
 				   		</div>
				  	</td>
					<td width="45%" class="tdfont">
			        	<div align="center">
					  		<html:select name="MedicalBoardInitializationFB" property="roleID" style="width:156;" > 
								<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MedicalBoardConfig.LIST_OF_ROLL %>">
						 		<html:options collection="<%=MedicalBoardConfig.LIST_OF_ROLL %>" labelProperty="label" property="value"  />
					    	</logic:present>
					  		</html:select> 
 				   		</div>
				  	</td>
				    <td width="10%" class="tdfont">
				    	<div align="left">
							<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onkeypress="if(event.keyCode==13) AddAssignRowToDocRoleTable('ADDASSIGNDOCROLEROW') ;" onclick="AddAssignRowToDocRoleTable('ADDASSIGNDOCROLEROW')" tabindex="1"/>
				   		</div>
			      	</td>
				 </tr> 
		   
	 <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST %>" >
	 <logic:iterate id="boardTeamDetailVO" name="<%=MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST %>" indexId="ind" type="hisglobal.vo.BoardTeamDetailVO" >
	 	<%String index=ind.toString(); %>
				<tr>
	 				<td width="40%" class="tdfont">
				 		<div align="center">
							<%=boardTeamDetailVO.getEmpName() %>
								<html:hidden name="MedicalBoardInitializationFB" property="modifyDocArray" value="<%=boardTeamDetailVO.getEmployeeId() %>"/>
						</div>
					</td>
		  			<td width="40%" class="tdfont">
		  				<div align="center">
							<%=boardTeamDetailVO.getRoleName() %>
						</div>
		  			</td>
		  			<td width="10%" class="tdfont">
						<div align="left">
							<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteAssignDocRoleRow('REMOVEASSIGNDOCROLEROW',<%=index%>)" onclick="deleteAssignDocRoleRow('REMOVEASSIGNDOCROLEROW',<%=index%>)">
						</div>
		  			</td>	
				</tr>
	</logic:iterate>		
	</logic:notEmpty>	   		
	</table>
	</his:ContentTag>
	
	<his:ContentTag> 
		<table width="100%" id="idEscortedBy" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="45%" class="tdfonthead">
					<div align="center">
				    	<font color="#FF0000" ><b>*</b></font>
				      	<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="escortedBy"/>&nbsp;
					  	</font>
				    </div>
				</td>
				<td width="45%" class="tdfonthead"></td>
				<td width="10%" class="tdfonthead"></td>
			</tr>
			<tr>
				<td width="45%" class="tdfont">
			    	<div align="center">
						<html:select name="MedicalBoardInitializationFB" property="escortedEmpID" style="width:156;" > 
							<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MedicalBoardConfig.LIST_OF_EMP_ESCORTS %>">
						 	<html:options collection="<%=MedicalBoardConfig.LIST_OF_EMP_ESCORTS %>" labelProperty="label" property="value"  />
					     </logic:present>
					  	</html:select> 
 				   </div>
				</td>
				<td width="45%" class="tdfont">
					<div align="left">
						<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onkeypress="if(event.keyCode==13) AddAssignEscortedRowToTable('ADDASSIGNESCORTEDROW') ;" onclick=" AddAssignEscortedRowToTable('ADDASSIGNESCORTEDROW')" tabindex="1"/>
				    </div>
				</td>
				<td width="10%" class="tdfont"></td>
			</tr> 
	 <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST %>" >
	 <logic:iterate id="boardTeamDetailVO" name="<%=MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST %>" indexId="ind" type="hisglobal.vo.BoardTeamDetailVO" >
	 	<%String index=ind.toString(); %>
	 	<tr>
	 		<td width="40%" class="tdfont">
				<div align="center">
					<%=boardTeamDetailVO.getEmpName() %>
				<html:hidden name="MedicalBoardInitializationFB" property="modifyEscortedArray" value="<%=boardTeamDetailVO.getEmployeeId() %>"/>
				</div>
			</td>
			<td width="10%" class="tdfont">
				<div align="left">
					<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteAssignEscortedRow('REMOVEASSIGNESCORTEDROW',<%=index%>)" onclick="deleteAssignEscortedRow('REMOVEASSIGNESCORTEDROW',<%=index%>)">
				</div>
		  	</td>	
		  	<td width="10%" class="tdfont"></td>
	 	</tr>
	</logic:iterate>		
	</logic:notEmpty>	
	</table>
	</his:ContentTag>
	<his:ContentTag> 
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<font color="#FF0000" ><b>*</b></font>
								<bean:message key="location"/>&nbsp;
							 </font>
					</div>
				</td>
				<td  class="tdfont" >
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="MedicalBoardInitializationFB" tabindex="1" rows="2" cols="70" property="location" onkeypress="return (validateTextArea(event,this,'50'))">
						</html:textarea>
					</font>
				</td>
			</tr>	
		</table>
	</his:ContentTag>		   
	</div>
	</his:statusRecordFound>
	</logic:notEmpty>
    </logic:present>
   
   
   
   
   
   
	<logic:present name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>">
	<logic:notEmpty name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>">
	
	<his:SubTitleTag name="Available Board Detail">
   </his:SubTitleTag>
   
   <table width="100%" cellspacing="1" cellpadding="0">
   <tr>
		<td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		</td>
		<td width="40%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="boardName"/></b>
			 </font>
			</div>
		</td>
		<td width="40%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="boardType"/></b>
			 </font>
			</div>
		</td>
   </tr>
   <logic:iterate id="boardMstVO" name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>" type="hisglobal.vo.MedicalBoardMasterVO" >
   <tr>
   		<td width="5%" class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:radio name="MedicalBoardInitializationFB" property="selectedBoardArray" value="<%=boardMstVO.getBoardID()%>" onclick="showTeamDetail('GETTEAMDETAILNEWBOARD')" tabindex="1"></html:radio>
			</font>
			</div>
		  </td>
   		<td width="40%" class="tdfont">
		  <div align="center">
				<%=boardMstVO.getBoardName() %>
			</div>
		  </td>
		  <td width="40%" class="tdfont">
		  <div align="center">
				<%=boardMstVO.getBoardTypeName() %>
			</div>
		  </td>
   </tr>
   </logic:iterate>
   </table>
   
    <his:statusRecordFound>
   <div id="teamDetailDivId" >
    <his:SubTitleTag name="Team Detail">
	</his:SubTitleTag>
			  <his:ContentTag> 
			   <table width="100%" id="idComboValue" border="0" cellspacing="1" cellpadding="0">
			
			    <tr>
			      <td width="45%" class="tdfonthead">
				    <div align="center">
				    	 <font color="#FF0000" ><b>*</b></font>
				      <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="doctor"/>&nbsp;
					  </font>
				    </div>
				  </td>
				  
				  <td width="45%" class="tdfonthead">
				    <div align="center">
				    <font color="#FF0000" ><b>*</b></font>
				       <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="role"/>&nbsp;
					  </font>
				    </div>
				  </td>
				    <td width="10%" class="tdfonthead"></td>
				  </tr>
				     
				  <tr>
			      <td width="45%" class="tdfont">
			       <div align="center">
					 <html:select name="MedicalBoardInitializationFB" property="empID" style="width:156;" > 
						<html:option value="-1">Select Value</html:option>
						 <logic:present name="<%=MedicalBoardConfig.LIST_OF_EMP_DOCTOR %>">
						  <html:options collection="<%=MedicalBoardConfig.LIST_OF_EMP_DOCTOR %>" labelProperty="label" property="value"  />
				         </logic:present>	
					 </html:select> 
 				   </div>
				  </td>
				
			      <td width="45%" class="tdfont">
			       <div align="center">
					  <html:select name="MedicalBoardInitializationFB" property="roleID" style="width:156;" > 
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MedicalBoardConfig.LIST_OF_ROLL %>">
						 <html:options collection="<%=MedicalBoardConfig.LIST_OF_ROLL %>" labelProperty="label" property="value"  />
					    </logic:present>
					  </html:select> 
 				   </div>
				  </td>
				  
				  <td width="10%" class="tdfont">
				   <div align="left">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onkeypress="if(event.keyCode==13) AddRowToDocRoleNewBoard('ADDDOCROLEROWNEWBOARD') ;" onclick="AddRowToDocRoleNewBoard('ADDDOCROLEROWNEWBOARD')" tabindex="1"/>
				   </div>
			      </td>
				 </tr> 
	 <logic:notEmpty name="<%=MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD %>" >
	 <logic:iterate id="boardTeamDetailVO" name="<%=MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD %>" indexId="ind" type="hisglobal.vo.BoardTeamDetailVO" >
	 	<%String index=ind.toString(); %>
	 	<tr>
	 		<td width="40%" class="tdfont">
				 <div align="center">
					<%=boardTeamDetailVO.getEmpName() %>
					<html:hidden name="MedicalBoardInitializationFB" property="addNewBoardDocArray" value="<%=boardTeamDetailVO.getEmployeeId() %>"/>
				</div>
			</td>
		  <td width="40%" class="tdfont">
		  	<div align="center">
				<%=boardTeamDetailVO.getRoleName() %>
			</div>
		  </td>
		  <td width="10%" class="tdfont">
			<div align="left">
				<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteDocRoleRowNewBoard('REMOVEDOCROLEROWNEWBOARD',<%=index%>)" onclick="deleteDocRoleRowNewBoard('REMOVEDOCROLEROWNEWBOARD',<%=index%>)">
			</div>
		  </td>	
		  
	 	</tr>
	 
	 </logic:iterate>		
	</logic:notEmpty>	
	  		
				
				
			</table>
	</his:ContentTag>
	
	
	<his:ContentTag> 
			   <table width="100%" id="idEscortedBy" border="0" cellspacing="1" cellpadding="0">
			      <tr>
			      <td width="45%" class="tdfonthead">
				    <div align="center">
				    	 <font color="#FF0000" ><b>*</b></font>
				      <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="escortedBy"/>&nbsp;
					  </font>
				    </div>
				  </td>
				  <td width="45%" class="tdfonthead"></td>
				  <td width="10%" class="tdfonthead"></td>
				 </tr>
				   
				 <tr>
				   <td width="45%" class="tdfont">
			       <div align="center">
					  <html:select name="MedicalBoardInitializationFB" property="escortedEmpID" style="width:156;" > 
						<html:option value="-1">Select Value</html:option>
						 <logic:present name="<%=MedicalBoardConfig.LIST_OF_EMP_ESCORTS %>">
						  <html:options collection="<%=MedicalBoardConfig.LIST_OF_EMP_ESCORTS %>" labelProperty="label" property="value"  />
					     </logic:present>
					  </html:select> 
 				   </div>
				  </td>
				  <td width="45%" class="tdfont">
				   <div align="left">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onkeypress="if(event.keyCode==13) AddEscortedRowToNewBoard('ADDESCORTEDROWNEWBOARD') ;" onclick=" AddEscortedRowToNewBoard('ADDESCORTEDROWNEWBOARD')" tabindex="1"/>
				   </div>
			      </td>
				  <td width="10%" class="tdfont"></td>
				 </tr> 
				 
	 <logic:notEmpty name="<%=MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD %>" >
	 <logic:iterate id="boardTeamDetailVO" name="<%=MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD %>" indexId="ind" type="hisglobal.vo.BoardTeamDetailVO" >
	 	<%String index=ind.toString(); %>
	 	<tr>
	 		<td width="40%" class="tdfont">
				 <div align="center">
					<%=boardTeamDetailVO.getEmpName() %>
					<html:hidden name="MedicalBoardInitializationFB" property="addNewBoardEscortArray" value="<%=boardTeamDetailVO.getEmployeeId() %>"/>
				</div>
			</td>
		  <td width="10%" class="tdfont">
			<div align="left">
				<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteEscortedRowNewBoard('REMOVEESCORTEDROWNEWBOARD',<%=index%>)" onclick="deleteEscortedRowNewBoard('REMOVEESCORTEDROWNEWBOARD',<%=index%>)">
			</div>
		  </td>	
		  <td width="10%" class="tdfont"></td>
	 	</tr>
	 
	 </logic:iterate>		
	</logic:notEmpty>	
	</table>
	
	</his:ContentTag>
	<his:ContentTag> 
			   <table width="100%" border="0" cellspacing="1" cellpadding="0">
			   	<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#FF0000" ><b>*</b></font>
						    <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
								<bean:message key="location"/>&nbsp;
							 </font>
						 </div>
					</td>
					<td  class="tdfont" >
						<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
							<html:textarea name="MedicalBoardInitializationFB" tabindex="1" rows="2" cols="70" property="location" onkeypress="return (validateTextArea(event,this,'50'))">
							</html:textarea>
						</font>
					</td>
				</tr>	
			   </table>
	</his:ContentTag>		   
	</div>
	</his:statusRecordFound>
	
	</logic:notEmpty>
	</logic:present>
   
   
     
    <his:ButtonToolBarTag>
	   	<logic:present name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE %>">
	    <logic:notEmpty name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE %>">
			<his:statusRecordFound>
				<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) validateModifyForm('MODIFY')" onclick="validateModifyForm('MODIFY')" tabindex="1">
				<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) validateDeleteForm('DELETEBOARD')" onclick="validateDeleteForm('DELETEBOARD')" tabindex="1">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('GETBOARDDETAIL');" onkeypress="if(event.keyCode==13) submitPage('GETBOARDDETAIL');">
			</his:statusRecordFound>
		</logic:notEmpty>
		</logic:present>
		
		<logic:present name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY %>">
   		<logic:notEmpty name="<%=MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY %>">
   			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateModifySave('MODIFYSAVE');" onkeypress="if(event.keyCode==13)validateModifySave('MODIFYSAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('GETBOARDDETAIL');" onkeypress="if(event.keyCode==13) submitPage('GETBOARDDETAIL');">
		</logic:notEmpty>
   		</logic:present>
		
		<logic:present name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>">
	    <logic:notEmpty name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>">
	    	<his:statusRecordFound>
	    		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateNewBoardAddForm('SAVENEWBOARD');" onkeypress="if(event.keyCode==13)validateNewBoardAddForm('SAVENEWBOARD');")>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('GETBOARDDETAIL');" onkeypress="if(event.keyCode==13) submitPage('GETBOARDDETAIL');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm();" onkeypress="if(event.keyCode==13) clearForm();">
	    	</his:statusRecordFound>
		</logic:notEmpty>
		</logic:present>
		
		<his:statusDone>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
		</his:statusDone>
		
		<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
		</his:statusList>
	</his:ButtonToolBarTag>
	
   <logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE %>">
   <logic:empty name="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE %>">
   	<font color="#FF0000" size="1">
   		<b>No Board Found for the Certificate Type</b>
   	</font>
   </logic:empty>
   </logic:present>
   
   <logic:present name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>">
   <logic:empty name="<%=MedicalBoardConfig.NEW_BOARD_ADD_LIST %>">
   	<font color="#FF0000" size="1">
   		<b>No Board left for Assignment</b>
   	</font>
   </logic:empty>
   </logic:present>
   <br>
   <div id="dvMessage" align="left"><bean:write name="MedicalBoardInitializationFB" property="divMessage" filter="false"/></div>

   <his:status/>
   
    <html:hidden name="MedicalBoardInitializationFB" property="hmode"/>
    <html:hidden name="MedicalBoardInitializationFB" property="docRoleIndex"/>
    <html:hidden name="MedicalBoardInitializationFB" property="docRoleAssignIndex"/>
    <html:hidden name="MedicalBoardInitializationFB" property="escortedRowIndex"/>
    <html:hidden name="MedicalBoardInitializationFB" property="escortedAssignRowIndex"/>
    <html:hidden name="MedicalBoardInitializationFB" property="selectedBoard"/>
    <html:hidden name="MedicalBoardInitializationFB" property="selectedAssignBoardNo"/>
    <html:hidden name="MedicalBoardInitializationFB" property="selectedAssignBoardId"/>
    <html:hidden name="MedicalBoardInitializationFB" property="selectedLocation"/>
    <html:hidden name="MedicalBoardInitializationFB" property="divMessage"/>
        
</html:form>
</his:TransactionContainer>   