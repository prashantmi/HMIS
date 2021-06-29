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


<script>

window.history.forward()

function submitTile(mode)
{
   document.getElementsByName("hmode")[0].value=mode;
  // alert("hmode"+document.getElementsByName("hmode")[0].value);
   document.forms[0].submit();
}

function deptSelect()
{
	var a;
	var isValid = true;
	a=document.getElementByName("departmentCode");
	
	if(document.getElementByName("departmentCode")="-1")
	{
		ll
		}
}

function validateModify()
{
	var len;
	var isValid = true;
	count=0;

	len=document.getElementsByName("roomCode").length;
	
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("roomCode")[i].checked){
				count++;
					}
				}
	
    if(count==0){    
        alert("Please Select a Room");
        return;
    	}
    if(count>1){    
        alert("Editing Multiple Records is not Allowed");
    	}	    	
	else
	{
	//alert("00000");
	submitTile('MOD');
	}

}



function validateRooms(mode)
{
	var len;
	var isValid = true;
	//int count=0;
	count=0;
//	alert("before assignment");
	len=document.getElementsByName("roomCode").length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("roomCode")[i].checked){
				count++;
					}
				}
	
    if(count==0){    
        alert("Please Select Room");
    	}
	else
	{
	//alert("00000");
	submitTile(mode)
	}

}
</script>
<%System.out.println("dsfsdhgfsdjg"); %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	<%System.out.println("....1");%>  

<body>
<html:form action="/master/deptUnitRoomMaster.cnt">
<his:TransactionContainer>

<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>

<his:TitleTag name="Department Unit Room Master">			
	<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            
     </font></b>
</his:TitleTag> 
<%System.out.println("....1");%>

<his:ContentTag>
  <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
  <tr>
      <td width="20%" class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="department"/></b>
	              </font>
	            </div>
      </td>                        
      
      <td width="20%" class="tdfont" >
	         <div align="left">	           
	             <html:select name="DeleteRoomFromUnitFB" tabindex="1" property="departmentCode" styleClass="registrationCmb" onchange="submitTile('GETUNIT')">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
      </td>
        
  <%System.out.println("....4");%>
      <td width="20%" class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="unit"/></b>
	              </font>
	            </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	           
	             <html:select name="DeleteRoomFromUnitFB" tabindex="1" property="unitCode" styleClass="registrationCmb" onchange="submitTile('GETROOMS')">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
      </td>                                             
 </tr>
 </table>
 </his:ContentTag>
<his:statusInProcessWithJsp>
<logic:notEmpty name="<%=RegistrationConfig.DEPT_UNIT_ROOM_MASTER_SEQUENCE %>">
 <his:SubTitleTag name="Existing Rooms to Unit"> 
 </his:SubTitleTag> 
 <his:ContentTag>
 <table>
 

 
 <tr>
 
 <td width="5%" class="tdfonthead">
 		<div align="center">
	          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Select</b>
			  </font>
	    </div>
 </td>
 	    	 
 <td width="16%" class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomSequence"/></b>
		              </font>
		            </div>
 </td>     

 <td width="16%" class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomName"/></b>
		              </font>
		            </div>
 </td>      
 <td width="16%" class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="capacity"/></b>
		              </font>
		            </div>
 </td>  
 
  <td width="16%" class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="capacityMode"/></b>
		              </font>
		            </div>
 </td>
 
 </tr>   
 
 <logic:iterate name="<%=RegistrationConfig.DEPT_UNIT_ROOM_MASTER_SEQUENCE %>" id="sequenceList" indexId="idx">
 <bean:define name="sequenceList" property="roomCode" id ="code" type="java.lang.String"/>
 <tr> 
 <td width = "5%"  class = "tdfont">	   
	   <div align="center">  
    	   <html:checkbox name="DeleteRoomFromUnitFB" property="roomCode" tabindex="1" value="<%=code%>"/>
       </div>
  </td>  
 <td width="16%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <bean:write  name="sequenceList" property="sequenceNo"/>	              		         
		              </font>
		            </div>
 </td>

<td width="16%" class="tdfont" nowrap valign="top">
	<div align="center">	           
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		           
		<bean:write  name="sequenceList" property="roomName"/>              
		</font>
	</div>
</td>
  
  
 
  
 <td width="16%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write  name="sequenceList" property="capacity"/>		              
		              </font>
		            </div>
 </td> 

 <td width="16%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write  name="sequenceList" property="capacityMode"/>		              
		              </font>
		            </div>
 </td>
  
 
 </tr> 
 </logic:iterate>
</table>
 </his:ContentTag> 
</logic:notEmpty>
<%varStatus="InProcess";%>
</his:statusInProcessWithJsp>
 <html:hidden name="DeleteRoomFromUnitFB" property="hmode"/>

 	 <his:ButtonToolBarTag> 	

  <%if(varStatus.equals("InProcess")){%>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitTile('ADD');" tabindex="1" onclick="submitTile('ADD')" >    
          <logic:notEmpty name="<%=RegistrationConfig.DEPT_UNIT_ROOM_MASTER_SEQUENCE %>">
           <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateModify()&& submitTile('MOD');" tabindex="1" onclick="validateModify()&& submitTile('MOD');" >   
           <img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateRooms() && submitTile('SAVE');" tabindex="1" onclick="validateRooms('SAVE')" >
           </logic:notEmpty>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       <%} else{ %>
         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW')"> 
	  <%} %>
</his:ButtonToolBarTag>

 </his:TransactionContainer>
 </html:form>
 <his:status/>
 </body>
 </html>
 