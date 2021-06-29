<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.master.controller.fb.ModifyUnitConsultantFB"%>
<html><head>
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

function showSaveDiv(obj){
     
   <bean:define name="ModifyUnitConsultantFB" property="isHOU" id="hou" type="java.lang.String"/>   
   
   ishou=obj.value;  
  // alert("bean define hou "+ <%=hou %>) 
  // alert ("obj hou "+ishou)       
   if(ishou=='<%=hou %>'){
    //document.getElementById("saveDiv").style.display="none";  
   }   
   else
   {
   //document.getElementById("saveDiv").style.display="";  
   }	  
 }

function submitTile(mode){
   if(this.value!='-1')
   document.getElementsByName("transactionMode")[0].value=mode;
   //alert("transactionMode"+document.getElementsByName("transactionMode")[0].value);
   document.forms[0].submit();
   ValidateAddUnit();
 }
function submitForm(mode){
   document.getElementsByName("transactionMode")[0].value=mode;
   document.forms[0].submit();
}

</script>
<%System.out.println("dsfsdhgfsdjg"); %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	<%System.out.println("....1");%>  

<body>
<html:form action="/master/modifyUnitConsultant.cnt">

<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>

<his:TitleTag name="Modify Consultants To Unit" >			
	<his:name>
	 <bean:message key="modify"/>
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
	              <b><bean:message key="department"/></b>
	              </font>
	            </div>
      </td>                        
      
      <td width="20%" class="tdfont">
	  <div align="left">
      
      <logic:equal name="ModifyUnitConsultantFB" property="source" value="fromview">
         <bean:write name="ModifyUnitConsultantFB" property="departmentName"/>
         <html:hidden name="ModifyUnitConsultantFB" property="departmentName"/>      
	     <html:hidden name="ModifyUnitConsultantFB" property="departmentCode"/>      
      </logic:equal>
      </div>
      <logic:notEqual name="ModifyUnitConsultantFB" property="source" value="fromview">
		  <html:select name="ModifyUnitConsultantFB" tabindex="1" property="departmentCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>
            </td>
  <%System.out.println("....4");%>
      <td width="20%"  class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="unit"/></b>
	              </font>
	            </div>
      </td> 
      <td width="20%" class="tdfont">
	  <div align="left">     
      <logic:equal name="ModifyUnitConsultantFB" property="source" value="fromview">
         <bean:write name="ModifyUnitConsultantFB" property="unitName"/>
         <html:hidden name="ModifyUnitConsultantFB" property="unitName"/>      
	     <html:hidden name="ModifyUnitConsultantFB" property="unitCode"/>      
      </logic:equal>
     
      <logic:notEqual name="ModifyUnitConsultantFB" property="source" value="fromview">
		  <html:select name="ModifyUnitConsultantFB" tabindex="1" property="unitCode" styleClass="registrationCmb">
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

 <his:SubTitleTag name="Existing Consultants">
 <his:name>
  <bean:message key="exist"/>
  <bean:message key="con"/>
 </his:name>
 </his:SubTitleTag> 
 <his:ContentTag>
 <table width ="100%" cellpadding="0" cellspacing="1">

 <logic:empty name="ModifyUnitConsultantFB" property="employeeNameExisting">
 <tr>
 <td>
  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:message key="noconsultantadded"/>
		              </font>
 </td>
 
 </tr>
 </logic:empty>
 
 <logic:notEmpty name="ModifyUnitConsultantFB" property="employeeNameExisting">
 <tr>
  <td width="20%"  class="tdfonthead">
		  <div align="center">
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		            <b>
		            <bean:message key="employeeid"/>
		            </b>
		            </font>
          </div>
 </td>     
 <td width="20%"  class="tdfonthead">
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <b>
		             <bean:message key="hou"/>
		             </b>
		             </font>
		            </div>
 </td>     
 
	 <td width="20%"  class="tdfonthead">
          <div align="center">	           
            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
            <b>
            <bean:message key="level"/>
            </b>
            </font>
           </div>
	 </td>     
 
<td width="20%" class="tdfonthead"></td>
 </tr> 
  <% ModifyUnitConsultantFB fb=(ModifyUnitConsultantFB)pageContext.findAttribute("ModifyUnitConsultantFB");%>
  
  <logic:iterate name="ModifyUnitConsultantFB" property="employeeNameExisting" id="empname" indexId="idx">
   <%String i= idx.toString(); 
   int index =  Integer.parseInt(i);%>
  <tr>
	   <td width="20%"  class="tdfont" nowrap>
		           <div align="right">	           
		             <font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">		        
		              <bean:write name="empname"/>
		              </font>
		            </div>
		            <input type="hidden" name="employeeNameExisting"  value = '<bean:write name = "empname"/>'/>
		            <html:hidden name="ModifyUnitConsultantFB" property="employeeNo" value="<%=fb.getEmployeeNo()[index] %>"/>
	   </td>	    	   
  
         
               
		  <td width = "20%"  class = "tdfont">	   
		   <div align="center">  
		      <html:radio name="ModifyUnitConsultantFB" property="isHOU" tabindex="1" value="<%=i%>" onclick="showSaveDiv(this);" />
	        </div>
		  </td>	   	 
		  
		  <td width="20%"  class="tdfont"> 
		  	 <div align="center">  
	   		<html:select name="ModifyUnitConsultantFB" property="hierarchyLevel" value='<%=fb.getHierarchyLevel()[index]!=null?fb.getHierarchyLevel()[index]:"" %>'>
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
		  		  
		 
 <% varStatus="InProcess";%>
   
   <td width="20%" class="tdfont"></td>
  </tr>
  </logic:iterate>
 </logic:notEmpty>
</table>
 </his:ContentTag> 
</his:statusInProcessWithJsp>
 <html:hidden name="ModifyUnitConsultantFB" property="transactionMode"/>
  <html:hidden name="ModifyUnitConsultantFB" property="source"/>
  <html:hidden name="ModifyUnitConsultantFB" property="removeConsultant"/> 
 <his:ButtonToolBarTag>  
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('SAVE');" tabindex="1" onclick="submitTile('SAVE');" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('VIEW')" onkeypress="if(event.keyCode==13) submitForm('VIEW')">
          <!--<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR')">
          
--></his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="ModifyUnitConsultantFB" property="existingHOU"/>
</html:form>
<his:status/>
</body>
</html>
 