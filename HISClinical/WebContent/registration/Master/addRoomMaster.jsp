
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>


<script>

function validateDepartmentType()
{ var valid=false;
	if(document.getElementsByName("departmentType")[0].value=="-1")
	{
	alert("Select the Type of department")
	valid=false
	}
	else
	{
		valid=true
	}
	
	return valid;
}


function validateCombo()
{ var valid=true;
	if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
	   valid= false;
	   }
	if(document.getElementsByName('hodCode')[0].value=="-1")
		{
		alert("Please Select the HOD for the Department");
		valid= false;
		}   
	if(document.getElementsByName('locationCode')[0].value=="-1")
		{
		alert("Please Select the Location for the Department");
		valid= false;
		} 
		return valid; 
}


function validateRemarks()
{	var valid=true;
	if(document.getElementsByName('remarks')[0].value.length>50)
	 {
	 	alert("Characters in Remarks greater than 50");
	 	valid= false;
	 }
	 return valid;
}

function validateRoomMaster()
{
	var valid=true;   
	alert("SAVE");	
	if(isEmpty(document.forms[0].roomName,"Room Name"))       
	    {
	  	valid=true;
	  	alert("one");	  		
	  	submitTile('SAVE');
	  	}
	  	  
	else
	{
		valid=false;
		
	}
}
function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" cant be greater than "+l2);
	valid = false;
	}
}

else
valid=false;

return valid;
}
function getBlock()
{
	alert("Inside getBlock");
	submitTile('GETBLOCK');
}
function getFloor()
{
	alert("Inside getFloor");
	submitTile('GETFLOOR');
}
function getRoom()
{
	alert("Inside getRoom");
	submitTile('GETROOM');
}

function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  
    document.forms[0].submit();
}

</script>

<body>
<html:form action="/master/addRoomMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
<his:TitleTag name="Add Room">			
	<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	         <bean:message key="date"/>
	         <bean:message key="and"/>
	         <bean:message key="time"/>
         <bean:write name="<%=Config.SYSDATE%>"/>      
     </font></b>
</his:TitleTag> 


<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <font color="#FF0000">*</font>
	  <b>
	  <bean:message key="roomName"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">	    
	  	<html:text name="RoomMasterFB"  maxlength="20" styleClass="textbox" property="roomName" onkeypress="return validateAlphabetsOnly(event)"/>  	     
	  </div>      
       </td>
  </tr>
  
  <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <font color="#FF0000">*</font>
	  <bean:message key="roomDescription"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	         
	         	<html:textarea name="RoomMasterFB" styleClass="textarea"   property="roomDescription" />	  
	               
	         </div>
      </td>                                           
  </tr>	   
  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="location"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:text name="RoomMasterFB" readonly="true"  property="locationName" />
	          </div>
       </td>                                                 
  </tr>
   <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b>
		                <font color="#FF0000">*</font>
		                <bean:message key="effectiveFrom"/>
		               </b>
		              </font>
		            </div>
	   </td> 
	    
	   <bean:define  name="RoomMasterFB" property="effectiveDate"  id="effFrom" type="java.lang.String"/>
	   <%
         if(effFrom==null||effFrom.equalsIgnoreCase("")){ 
        	 
        	
        	 effFrom =sysDate;
         }%>
         
         
	   <td class="tdfont">    
	    	<div align="left">
	    	<his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
	    	</div>
	   </td>
  </tr>   
   
  <tr>
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000"></font>
		              <b><bean:message key="isvalid"/></b>
		              </font>
		            </div>
	   </td>
	
		 <td width="20%"  class="tdfont">
	         <div align="left">
	         <html:select property="isValid" name="RoomMasterFB" tabindex="1" styleClass="regCbo">
	         <html:option value="">Select</html:option>
	         <html:option value="0">Not Valid</html:option>
	         <html:option value="1">Valid</html:option>
	         </html:select>	                
	          </div>
       </td>  
	      	  
  </tr> 
  </table>
</his:ContentTag> 
<his:TitleTag name="Mapping With Estate">
</his:TitleTag>  
<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">  
  

<tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="building"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="buildingCode" styleClass="regCbo" onchange="getBlock()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>
 <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="block"/></b>
		              </font>
		            </div>
	   </td> 
	   <logic:notPresent name="<%=RegistrationConfig.ROOM_MST_BLOCK%>">     
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="blockCode" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>  			 		
	   		 		</html:select>
	          </div>
       </td> 
       </logic:notPresent>       
       <logic:present name="<%=RegistrationConfig.ROOM_MST_BLOCK%>">     
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="blockCode" styleClass="regCbo" onchange="getFloor()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_BLOCK%>" property="value" labelProperty="label" />
	   		 		</html:select>
	          </div>
       </td> 
       </logic:present>                                                
  </tr>
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="floor"/></b>
		              </font>
		            </div>
	   </td>      
	   <logic:notPresent name="<%=RegistrationConfig.ROOM_MST_FLOOR%>">     
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="floorCode" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>  			 		
	   		 		</html:select>
	          </div>
       </td> 
       </logic:notPresent> 
       <logic:present name="<%=RegistrationConfig.ROOM_MST_FLOOR%>">     
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="floorCode" styleClass="regCbo" onchange="getRoom()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_FLOOR%>" property="value" labelProperty="label" />
	   		 		</html:select>
	          </div>
       </td> 
       </logic:present>                                                 
  </tr>
    <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="roomName"/></b>
		              </font>
		            </div>
	   </td>      
	   <logic:notPresent name="<%=RegistrationConfig.ROOM_MST_ESTATEROOM%>">     
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="estateRoomId" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>  			 		
	   		 		</html:select>
	          </div>
	          
       </td> 
       </logic:notPresent> 
       <logic:present name="<%=RegistrationConfig.ROOM_MST_ESTATEROOM%>">     
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="RoomMasterFB" tabindex="1" property="estateRoomId" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_ESTATEROOM%>" property="value" labelProperty="label" />
	   		 		</html:select>
	          </div>
       </td> 
       </logic:present>                                                 
  </tr>
  
   </table>
</his:ContentTag> 

<html:hidden name="RoomMasterFB" property="transactionMode" />
<his:ButtonToolBarTag>   
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateRoomMaster()" onkeypress="if(event.keyCode==13) validateCityLocationMaster()">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
</his:ButtonToolBarTag>

</his:TransactionContainer>
 
</html:form>
</body>
</html>