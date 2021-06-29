<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config,registration.master.controller.fb.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<script>
window.history.forward()

function submitTransMode(mode){
 document.getElementsByName("transactionMode")[0].value=mode;  
  if(mode=="SAVECONTINUETOCONSULT"){   
    submitTile("SAVE");
  } 
}

function removeEmp(idx,mode){
	var elmt=document.getElementsByName('removeConsultant')[0];
	elmt.value=idx;	
	submitTile(mode);
}
function submitTileAddEmp(mode){
      		
		if( document.getElementsByName("empNo")[0].value=="-1")
		{
			alert("Select the value ");
			return;
		}
			else
		{
		//alert("In submitTitle:  "+mode);
		document.getElementsByName("transactionMode")[0].value=mode;
		document.forms[0].submit();
        }
}
function addConsultant(){
	var empNo=document.getElementsByName("empNo")[0]
	var label;
	for(var i=0;i<empNo.length;i++){
		if(empNo[i].selected){
			label=empNo[i].text;
		}	
	}
	//alert(label);
	document.getElementsByName("empFullName")[0].value=label
	submitTileAddEmp('ADDCONSULTANT')
}

function submitTile(mode)
{
        //alert("In submitTitle:  "+mode);
		document.getElementsByName("transactionMode")[0].value=mode;
		document.forms[0].submit();
 }
function validateConsultant(){
<%
AddUnitConsultantFB consultantFb=(AddUnitConsultantFB)pageContext.findAttribute("AddUnitConsultantFB");
int lengthArray=0;
if(consultantFb!=null){
	if(consultantFb.getConsultantNameNew()!=null){
		lengthArray=consultantFb.getConsultantNameNew().length;
	}
}
%>
	if(<%=lengthArray%>==0){
		alert("Please add atLeast one Consultant");
		return false;
	}
	else{
		validateHOU();	
	}
}

function validateHOU(){
		var flag=false;
		var len=document.getElementsByName('isHOU').length
		//alert("len :"+len)
		if(len==0){
			flag=true;
		}
		for(i=0;i<len;i++)
		{
			//alert(document.getElementsByName('isHOU')[i].checked)
			if(document.getElementsByName('isHOU')[i].checked){
				flag=true;
				break;
			}
		}
		
		if(flag){
			submitTile('SAVE')
		}
		else{
			alert("Please Select Head of Unit");
			return false;
		}	

}

 </script>
<%System.out.println("dsfsdhgfsdjg"); %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	<%System.out.println("....1");%>  

<body>
<html:form action="/master/addUnitConsultant.cnt">
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>

<his:TitleTag name="Add Consultants To Unit" >			
	<his:name>
	 <bean:message key="add"/>
	 <bean:message key="unit"/>
	 <bean:message key="to"/>
	  <bean:message key="con"/>
	</his:name>
</his:TitleTag> 
<%System.out.println("....1");%>  
<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">    
  <tr>
      <td width="25%"  class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <bean:message key="department"/>
	              </font>
	            </div>
         </td>
      <td width="20%" class="tdfont">
	  <div align="left">
      
      <logic:equal name="AddUnitConsultantFB" property="source" value="fromview">
         <bean:write name="AddUnitConsultantFB" property="departmentName"/>
         <html:hidden name="AddUnitConsultantFB" property="departmentName"/>      
	     <html:hidden name="AddUnitConsultantFB" property="departmentCode"/>      
      </logic:equal>
      </div>
      <logic:notEqual name="AddUnitConsultantFB" property="source" value="fromview">
		  <html:select name="AddUnitConsultantFB" tabindex="1" property="departmentCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>
            </td>
  <%System.out.println("....4");%>
      <td width="20%"  class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <bean:message key="unit"/>
	              </font>
	            </div>
      </td> 
      <td width="20%" class="tdfont">
	  <div align="left">     
      <logic:equal name="AddUnitConsultantFB" property="source" value="fromview">
         <bean:write name="AddUnitConsultantFB" property="unitName"/>
         <html:hidden name="AddUnitConsultantFB" property="unitName"/>      
	     <html:hidden name="AddUnitConsultantFB" property="unitCode"/>      
      </logic:equal>
     
      <logic:notEqual name="AddUnitConsultantFB" property="source" value="fromview">
		  <html:select name="AddUnitConsultantFB" tabindex="1" property="unitCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual> 
	   </div>
	   </td>                                                 
      
 </tr>
 </table>
 </his:ContentTag>
<his:statusInProcessWithJsp>

 <logic:notEqual name="AddUnitConsultantFB" property="employeeNameExisting" value="null">
 <logic:notEmpty name="AddUnitConsultantFB" property="employeeNameExisting">
 <his:SubTitleTag>
 <his:name>
  <bean:message key="exist"/>
  <bean:message key="con"/>
 </his:name>
 </his:SubTitleTag> 
 <his:ContentTag>
 <table width ="100%" cellpadding="0" cellspacing="1">
 <%System.out.println("sjjjjjjjjjjj"); %>
 
 
 
 <tr>
  <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <b> <bean:message key="employeeid"/></b>
		              </font>
		            </div>
 </td>     
<td width="20%"  class="tdfont">
<td width="20%"  class="tdfonthead">
<td width="20%"  class="tdfont">
 </tr>
	<% AddUnitConsultantFB fb=(AddUnitConsultantFB)pageContext.findAttribute("AddUnitConsultantFB");%>
						<logic:iterate name="AddUnitConsultantFB"
							property="employeeNameExisting" id="empname" indexId="idx">
							<%String i= idx.toString(); %>
							<tr>
								<td width="20%" class="tdfonthead" nowrap>
								<div align="right"><font color="#000000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
									name="empname" /> </font></div>
								<input type="hidden" name="employeeNameExisting"
									value='<bean:write name = "empname"/>' />
								<html:hidden name="AddUnitConsultantFB" property="employeeIdExisting" value="<%=fb.getEmployeeIdExisting()[Integer.parseInt(i)] %>"/>	
								</td>
								
								
								<logic:equal name="AddUnitConsultantFB" property="houToMark"
									value="<%=i%>">
									<td width="20%" class="tdfont">
									<div align="left"><font color="#000000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
										key="hod" /></b> </font></div>
									<input type="hidden" name="houToMark" value="<%=i%>" /></td>
								</logic:equal>
								<logic:equal name="AddUnitConsultantFB" property="doctorHou"
									value="<%=i%>">
									<td width="20%" class="tdfont">
									<div align="left"><font color="#000000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
										key="hod" /></b> </font></div>
									<input type="hidden" name="doctorHou" value="<%=i%>" /></td>
								</logic:equal>
								<logic:notEqual name="AddUnitConsultantFB" property="doctorHou" value="<%=i%>">
									<logic:notEqual name="AddUnitConsultantFB" property="houToMark" value="<%=i%>">
										<td width="20%" class="tdfont">
									</logic:notEqual>
								</logic:notEqual>
								
								<td width="20%" class="tdfonthead">
								<td width="20%" class="tdfont">
							</tr>
						</logic:iterate>
					
</table>
 </his:ContentTag>
 </logic:notEmpty>
 </logic:notEqual>
 <his:SubTitleTag name="Add Consultants">
 <his:name>
  <bean:message key="add"/>
  <bean:message key="con"/>
 </his:name> 
 </his:SubTitleTag>
 <his:ContentTag>
 <table width="100%" cellpadding="0" cellspacing="1">
  <tr>      
      <td width="20%"  class="tdfonthead">
	         <div align="right">	           
	             <html:select name="AddUnitConsultantFB" tabindex="1" property="empNo" styleClass="registrationCmb">
			 			<html:option value="-1">Select Value</html:option>
			 			<logic:present name="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>">
  			 			<html:options collection="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>" property="value" labelProperty="label" />
  			 			</logic:present>
	   		 	</html:select>
	          </div>
	  </td>  
	  <td width="15%" class="tdfont">
			<div align="left">        
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="add consultant" title="Add Consultant" onKeyPress="if(event.keyCode==13) addConsultant()" onClick="addConsultant()"></div>
      </td> 
       <td width="20%"  class="tdfonthead"> </td>
	   <td width="20%"  class="tdfont"></td>
 </tr>
 
 
 <logic:notEmpty name="AddUnitConsultantFB" property="consultantNameNew">
 <%varStatus="InProcess";%>
  <tr>
 	<td width="20%"  class="tdfonthead">
          <div align="center">	           
             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
               <bean:message key="employeeid"/> 
             </font>
           </div>
      </td>
 	
 	<td width="20%"  class="tdfonthead">
          <div align="center">	           
             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <bean:message key="isHOU"/>
             </font>
           </div>
      </td>
 	<td width="20%"  class="tdfonthead">
          <div align="center">	           
             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             	<bean:message key="level"/>
             </font>
           </div>
      </td>
 	  <td width="20%"  class="tdfonthead"></td>
 </tr>
    <logic:iterate name="AddUnitConsultantFB" property="consultantNameNew" id="consultants" indexId="idx">    
   
  <tr>
    <td width="20%"  class="tdfont" nowrap>
		           <div align="right">	           
		             <font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write name="consultants"/>
<!--		              <bean:write name="AddUnitConsultantFB" property="empFullName"/>-->
	                 </font>
      </div>
                   <input type="hidden" name="consultantNameNew"  value = '<bean:write name = "consultants"/>'/></td>
	   <% String i=idx.toString(); %>
	   <logic:notPresent name="AddUnitConsultantFB" property="doctorHou">
	       <logic:empty name="AddUnitConsultantFB" property="houToMark">
	         <td width = "20%"  class = "tdfont">	   
			   <div align="center">  
			      <html:radio name="AddUnitConsultantFB" property="isHOU" tabindex="1" value="<%=i%>" />
		        </div>
			  </td>
	      </logic:empty> 
		   <logic:notEmpty name="AddUnitConsultantFB" property="houToMark"> 	
		   <td width="20%"  class="tdfont"> </td>
		   
		   </logic:notEmpty> 
	   </logic:notPresent>
	   <logic:present name="AddUnitConsultantFB" property="doctorHou">
	     <td width="20%"  class="tdfont"> </td>
	   </logic:present>
	   
	   <td width="20%"  class="tdfont"> 
	   	<div align="center">
	   		<html:select name="AddUnitConsultantFB" property="hierarchyLevel" >
	   			<html:option value="0">Select Value</html:option>
	   			<html:option value="1">Level 1 (Highest)</html:option>
	   			<html:option value="2">Level 2</html:option>
	   			<html:option value="3">Level 3</html:option>
	   			<html:option value="4">Level 4</html:option>
	   			<html:option value="5">Level 5</html:option>
	   			<html:option value="6">Level 6</html:option>
	   			<html:option value="7">Level 7</html:option>
	   			<html:option value="8">Level 8</html:option>
	   			<html:option value="9">Level 9 (Lowest)</html:option>
	   		</html:select>
	   	</div>	
	   </td>
	   
	   <td width="20%" class="tdfont">
			<div align="center">   
			   <img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/Minus.png"/>' alt="Delete consultant" title="Delete Consultant" onkeypress="if(event.keyCode==13) removeEmp(<bean:write name="idx"/>,'REMOVECONSULTANT')" onclick="removeEmp(<bean:write name="idx"/>,'REMOVECONSULTANT')">
			</div>
      </td> 	   
  </tr>  
 </logic:iterate>  
 </logic:notEmpty>
 
</table>
 </his:ContentTag> 	 
</his:statusInProcessWithJsp>
 <html:hidden name="AddUnitConsultantFB" property="transactionMode"/>
  <html:hidden name="AddUnitConsultantFB" property="source"/>
 
  <html:hidden name="AddUnitConsultantFB" property="removeConsultant"/> 
 <his:ButtonToolBarTag>
     <%if(varStatus.equals("InProcess")){%>
     <logic:notEmpty name="AddUnitConsultantFB" property="consultantNameNew">
            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateConsultant();" tabindex="1" onclick="validateConsultant();" >
      </logic:notEmpty>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
         
       <%} else{ %>
           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW'))">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
	  <%} %>
</his:ButtonToolBarTag>
 </his:TransactionContainer>
 <his:status/>
 <html:hidden name="AddUnitConsultantFB" property="empFullName"/>
 </html:form>
 </body>
 </html>
 