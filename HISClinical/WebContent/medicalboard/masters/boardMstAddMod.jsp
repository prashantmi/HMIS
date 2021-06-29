<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
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
	<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/medicalboard/js/boardMstAddMod.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />


<script type="text/javascript">

function callThisOnload()
{
 var hmode=document.getElementsByName('hmode')[0].value;
 //alert("onload")
  if(hmode=="MODIFY" || hmode=="MODIFYSAVE")
  {
     //alert("onload")
     AddRowToTableOnModify();
     if(document.getElementsByName('escortedEmpIDValue')[0].value!=""){
     AddEscortedByRowToTableOnModify();}
  }
  
   if(hmode=="VIEW" )
  {
     //alert("onload")
      AddRowToTableOnView();
      if(document.getElementsByName('escortedEmpIDValue')[0].value!=""){
      AddEscortedByRowToTableOnView();}
  }
}

</script>		



<body>
<html:form action="/masters/BoardMasterACTION" >

     <%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="BoardMasterFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	


	<html:hidden name="BoardMasterFB" property="hmode"/>
	<html:hidden name="BoardMasterFB" property="slNo"/>
    <html:hidden name="BoardMasterFB" property="boardID"/>
    
     <html:hidden name="BoardMasterFB" property="boardTypeID"/>
     
     <html:hidden name="BoardMasterFB" property="empName"/>
     <html:hidden name="BoardMasterFB" property="roleName"/>
     <html:hidden name="BoardMasterFB" property="escortedEmpName"/>
     
     <html:hidden name="BoardMasterFB" property="empIDValue"/>
     <html:hidden name="BoardMasterFB" property="roleIDValue"/>
     <html:hidden name="BoardMasterFB" property="escortedEmpIDValue"/>
	
	 <html:hidden name="BoardMasterFB" property="numberOfRow"/>
	  <html:hidden name="BoardMasterFB" property="numberOfRowEscorted"/>
	<his:TransactionContainer>
		<his:TitleTag name="Board Master">
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
						    <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
							</font> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="boardType"/>&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     &nbsp;&nbsp;<b><bean:write name="BoardMasterFB"  property="boardTypeName" /> </b>
						   </font>  
						</div>
					</td>
					</tr>
					<tr>
					     <td width="50%" class="tdfonthead">
					     <div align="right">
					        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
							</font> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="boardName"/>&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
						  &nbsp;<html:text name="BoardMasterFB"  property="boardName" maxlength="50" size="25" disabled="<%=this.readOnly %>"  style="width:110;" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
					  </html:text>
							
						</div>
					</td>
				  </tr>
				</table>
			 </his:ContentTag>
					
			<his:SubTitleTag name="Team Detail">
			 </his:SubTitleTag>
			  <his:ContentTag> 
			   <table width="100%" id="idComboValue" border="0" cellspacing="1" cellpadding="0">
			
			    <tr>
			      <td width="45%" class="tdfonthead">
				    <div align="center">
				      <font color="RED" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							     	*
					  </font> 
					  <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="doctor"/>&nbsp;
					  </font>
				    </div>
				  </td>
				  
				  <td width="45%" class="tdfonthead">
				    <div align="center">
				      <font color="RED" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							     	*
					  </font> 
					  <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="role"/>&nbsp;
					  </font>
				    </div>
				  </td>
				    <td width="10%" class="tdfonthead"></td>
				  </tr>
				     <logic:notEqual name="BoardMasterFB" property="hmode" value="VIEW">
				  <tr>
			      <td width="45%" class="tdfont">
			       <div align="center">
					 <html:select name="BoardMasterFB" property="empID" style="width:156;" > 
						<html:option value="-1">Select Value</html:option>
						 <logic:present name="<%=MedicalBoardConfig.LIST_OF_EMP_DOCTOR %>">
						  <html:options collection="<%=MedicalBoardConfig.LIST_OF_EMP_DOCTOR %>" labelProperty="label" property="value"  />
				         </logic:present>	
					 </html:select> 
 				   </div>
				  </td>
				
			      <td width="45%" class="tdfont">
			       <div align="center">
					  <html:select name="BoardMasterFB" property="roleID" style="width:156;" > 
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MedicalBoardConfig.LIST_OF_ROLL %>">
						 <html:options collection="<%=MedicalBoardConfig.LIST_OF_ROLL %>" labelProperty="label" property="value"  />
					    </logic:present>
					  </html:select> 
 				   </div>
				  </td>
				  
				  <td width="10%" class="tdfont">
				   <div align="left">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onkeypress="if(event.keyCode==13)if(validateOnAddDoctor()) AddRowToTable() ;" onclick="if(validateOnAddDoctor())AddRowToTable()" tabindex="1"/>
				   </div>
			      </td>
				 </tr> 
		   </logic:notEqual>
		
		   		
				
				
			</table>
	</his:ContentTag>
	
	
	<his:ContentTag> 
			   <table width="100%" id="idEscortedBy" border="0" cellspacing="1" cellpadding="0">
			      <tr>
			      <td width="45%" class="tdfonthead">
				    <div align="center">
				      <font color="RED" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							     	*
					  </font> 
					  <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							<bean:message key="escortedBy"/>&nbsp;
					  </font>
				    </div>
				  </td>
				  <td width="45%" class="tdfonthead"></td>
				  <td width="10%" class="tdfonthead"></td>
				 </tr>
				   <logic:notEqual name="BoardMasterFB" property="hmode" value="VIEW">
				 <tr>
				   <td width="45%" class="tdfont">
			       <div align="center">
					  <html:select name="BoardMasterFB" property="escortedEmpID" style="width:156;" > 
						<html:option value="-1">Select Value</html:option>
						 <logic:present name="<%=MedicalBoardConfig.LIST_OF_EMP_ESCORTS %>">
						  <html:options collection="<%=MedicalBoardConfig.LIST_OF_EMP_ESCORTS %>" labelProperty="label" property="value"  />
					     </logic:present>
					  </html:select> 
 				   </div>
				  </td>
				  <td width="45%" class="tdfont">
				   <div align="left">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onkeypress="if(event.keyCode==13)if(validateOnAddEscort()) AddEscortedRowToTable() ;" onclick="if(validateOnAddEscort())AddEscortedRowToTable()" tabindex="1"/>
				   </div>
			      </td>
				  <td width="10%" class="tdfont"></td>
				 </tr> 
				</logic:notEqual>
			</table>
	</his:ContentTag>
	
	
	
	
	
	
		<his:ButtonToolBarTag>
			  <span id="saveDiv">
			     <logic:notEqual name="BoardMasterFB" property="hmode" value="MODIFY">
			     <logic:notEqual name="BoardMasterFB" property="hmode" value="VIEW">
					 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				 </logic:notEqual>
				 </logic:notEqual>
				 <logic:equal name="BoardMasterFB" property="hmode" value="MODIFY">
				     <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				 </logic:equal>    
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		  </his:ButtonToolBarTag>
	</his:TransactionContainer>
	
	<table>
	<tr>
	
	<his:status/>
	
	</tr>
   </table>
  </html:form>

 </html>