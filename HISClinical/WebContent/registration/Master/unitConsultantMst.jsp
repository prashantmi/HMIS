<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config"%>
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

function validate(mode)
{
	var len;
	var isValid = true;
	//int count=0;
	count=0;
	//alert("before assignment");
	len=document.getElementsByName('employeeNo').length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName('employeeNo')[i].checked){
				count++;
					}
				}
	
    if(count==0){    
        alert("Please Select record");
        return;
    	}
	else
	{
	//alert("00000");
		if(validateDelete(count)){
			submitTile(mode)
		}	
	
	}

}


function submitTile(mode)
{
       // alert("In submitTitle:  "+mode);
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
 }
 
function getUnit(obj)
{
    if(obj.value=="-1"){
		submitTile("NEW")
	}
	else{
		submitTile("GETUNIT")
	}
}
function getConsultants(obj)
{
    if(obj.value=="-1"){
		submitTile("NEW")
	}
	else{
		submitTile("GETCONSULTANTS")
	}
}

function validateDelete(count){
	var valid=true;
	var hod="";
	var selectedConsultant=document.getElementsByName("employeeNo")
	
	
	if(typeof document.getElementsByName("houToMark")[0] == 'undefined' )
	{
		hod=document.getElementsByName("doctorHou")[0].value;
	}	
	else
	{
		hod=document.getElementsByName("houToMark")[0].value;
	}
	//alert(hod)
	if(selectedConsultant.length>1){
		for(var i=0;i<selectedConsultant.length;i++){
			if(selectedConsultant[i].checked){
				//alert("checked")
				if(hod==i){
					alert("You cannot delete consultant whose is Head of Unit. Please make another consultant as Head of Unit in order to delete this.")
					valid= false;
					break;
				}			
			}
		}
	}
	return valid;
}

function validateModify()
{
	var valid=false;
	if(document.getElementsByName("doctorHou")[0].value!="")
	{
		alert("Cannot Modify The Head Of Unit");
		valid=false;
	}
	else
		valid=true;
	
	return valid;	
}

function disabledDoctorHou()
{
	var doctorHou=document.getElementsByName("doctorHou")[0].value;
	for(var i=0;i<document.getElementsByName("employeeNo").length;i++)
	{
		document.getElementsByName("employeeNo")[doctorHou].disabled=true;
	}
}

 </script>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	<%System.out.println("....1");%>  

<body onload="disabledDoctorHou()">
<html:form action="/master/unitConsultantMaster.cnt">
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
 
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>

<his:TitleTag name="Unit Consultant Master">			
</his:TitleTag> 

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
      <td width="25%"  class="tdfont">
	         <div align="left">	           
	             <html:select name="UnitConsultantFB" tabindex="1" property="departmentCode" styleClass="registrationCmb" onchange="getUnit(this)">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
      </td>                                                 
       
  

  
      <td width="25%"  class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <bean:message key="unit"/>
	              </font>
	            </div>
      </td>      
      <td width="25%"  class="tdfont">
	         <div align="left">	           
	             <html:select name="UnitConsultantFB" tabindex="1" property="unitCode" styleClass="registrationCmb" onchange=" getConsultants(this)">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
      </td>                                           
      
 </tr>
 </table>
 </his:ContentTag>
<his:statusInProcessWithJsp>

 <logic:notEmpty name="UnitConsultantFB" property="employeeNameExisting">
 <his:SubTitleTag>
 <his:name>
  <bean:message key="exist"/>
  <bean:message key="con"/>
 </his:name>
 </his:SubTitleTag> 
 <his:ContentTag>
 <table width ="100%" cellpadding="0" cellspacing="1">
 
 
 
 
 <tr>
 <td width="5%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <b> <bean:message key="select"/></b>
		              </font>
		            </div> 
 </td>
  <td width="95%"  class="tdfonthead">
		           <div align="left">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <b> &nbsp;&nbsp;&nbsp;  <bean:message key="employee"/></b>
		              </font>
		            </div>
 </td>     
 </tr>
 
	<logic:iterate name="UnitConsultantFB" 	property="employeeNameExisting" id="empname" indexId="idx">
	   <bean:define name="UnitConsultantFB" property="employeeNo" id="code" type="java.lang.String[]"/>
	   <%String i= idx.toString(); %>
	   
<tr>
	
<td width ="5%"  class = "tdfonthead">	   
	   <div align="center">  
    	   <html:checkbox name="UnitConsultantFB" property="employeeNo" tabindex="1" value="<%=code[idx.intValue()]%>"  />
       </div>
  </td>  
  <td width="95%" class="tdfonthead" nowrap align="left">
    	<div align="left"><font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
    	   <logic:notEmpty name="empname">
    	   <bean:write name="empname" />
    	   </logic:notEmpty>
    	  </font>
    	   <logic:notEmpty name="empname">
    	 <input type="hidden" name="employeeNameExisting" value='<bean:write name = "empname"/>' />
  		</logic:notEmpty>

								
		<logic:equal name="UnitConsultantFB" property="houToMark" value="<%=i%>">

           <b> 
             <bean:message key="hod" />           
           </b> 
			<input type="hidden" name="houToMark" value="<%=i%>" />
		</logic:equal>
		<logic:notEqual name="UnitConsultantFB" property="houToMark"
			value="<%=i%>">
		</logic:notEqual>	
		<logic:equal name="UnitConsultantFB" property="doctorHou" value="<%=i%>">

           <b> 
             <bean:message key="hod" />           
           </b> 
		</logic:equal>
		<logic:notEqual name="UnitConsultantFB" property="doctorHou" value="<%=i%>">
		</logic:notEqual>
		
		</div>
		</td>							
	</tr>
  	</logic:iterate>	
  	<html:hidden name="UnitConsultantFB" property="doctorHou"/>				
</table>
 </his:ContentTag>
 </logic:notEmpty>
  <%varStatus="InProcess";%>
</his:statusInProcessWithJsp>
 <html:hidden name="UnitConsultantFB" property="hmode"/>
 <his:ButtonToolBarTag>
      <%if(varStatus.equals("InProcess")){%>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitTile('ADD');" tabindex="1" onclick="submitTile('ADD')" >    
          <logic:notEmpty name="UnitConsultantFB" property="employeeNameExisting">
             <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13)if(validateModify()) submitTile('MOD');" tabindex="1" onclick="if(validateModify()) submitTile('MOD');" >   
             <img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validate('SAVE');" tabindex="1" onclick="validate('SAVE')" >
          </logic:notEmpty>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       <%} else{ %>
<%--           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW')">--%>
 
	  <%} %>
</his:ButtonToolBarTag>
 </his:TransactionContainer>
 <his:status/>
 </html:form>
 </body>
 </html>
 