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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />


<script>
function validateRoomMaster()
{
	var valid=true;   
	alert("UPDATE");	
	if(isEmpty(document.forms[0].roomName,"Room Name"))       
	    {
	  	valid=true;
	  	alert("one");	  		
	  	submitTile('UPDATE');
	  	}
	  	  
	else
	{
		valid=false;
		
	}
}

function validateDate()
{
	var valid=true;
	var checkeffectiveDateFrom=document.getElementById("effectiveDate1");
	
	if(checkeffectiveDateFrom !=null && checkeffectiveDateFrom != 'null')
	   {
	   
	        if(!compareDateCall(entryDate,effectiveDate,2,"CurrentDate","Effective From Date"))
	        valid= false;
       }
    if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveDate,effectiveTo,2,"Effective From Date","Effective To Date"))
	   valid= false;
	   }
	return valid;
}

function compareDateCall(d1,d2,mode,l1,l2){

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
   alert(document.getElementsByName('transactionMode')[0].value);
   document.forms[0].submit();
}


</script>

<body>
<html:form action="/master/modifyRoomMaster">
<his:TransactionContainer>
		<%@ page	import="java.util.*,registration.*,hisglobal.presentation.*"%>
		<%boolean varReadOnly = false;
		  String strReadOnly="false";
		%>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
		<%varReadOnly=true;
		strReadOnly="true";%>
		</logic:equal>
		
		<%String sysDate = WebUTIL
							.getCustomisedSysDate(
									(Date) session
											.getAttribute(RegistrationConfig.SYSADATEOBJECT),
									"dd-MMM-yyyy");

					%>

		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
			value="modify">
			<his:TitleTag name="Modify Room">
				<b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="date" /> <bean:message key="and" /> <bean:message key="time" />
				<bean:write name="<%=Config.SYSDATE%>" /> </font> </b>
			</his:TitleTag>
		</logic:equal>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
			<his:TitleTag name="View Room">
				<b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="date" /> <bean:message key="and" /> <bean:message key="time" />
				<bean:write name="<%=Config.SYSDATE%>" /> </font> </b>
			</his:TitleTag>
		</logic:equal>

<%Date sysDateObject=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT); %>
<his:ContentTag>
			
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
						color="#FF0000">*</font> <bean:message key="roomName" /> </b> </font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:text name="RoomMasterFB" property="roomName" maxlength="20" styleClass="textbox" />
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:text name="RoomMasterFB" property="roomName" readonly="true" styleClass="textbox"/></div>
					</logic:equal>			
						
					</td>
				</tr>	
	<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
						color="#FF0000">*</font> <bean:message key="roomDescription" /> </b> </font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:textarea name="RoomMasterFB" property="roomDescription" styleClass="textarea" />
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:textarea name="RoomMasterFB" property="roomDescription" readonly="true" styleClass="textarea"/></div>
					</logic:equal>
						
					</td>
				</tr>
				
 <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="isvalid"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	         <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	          
	                <html:select name="RoomMasterFB" tabindex="1" property="isValid" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="0">Not Valid</html:option>
	        		 <html:option value="1">Valid</html:option>
	   		 	</html:select>
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:text name="RoomMasterFB" property="isValid" readonly="true" styleClass="textbox"/>	         
  		 	</logic:equal>	   		 	
	          </div>
       </td>                                                 
  </tr>
  <tr>
					<td width="30%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<font color="#FF0000">*</font> 
					<b> <bean:message key="effectiveDate" />
					</b> 
					</font>
					</div>
					</td>
					<bean:define name="RoomMasterFB" property="effectiveDate" id="effFrom" type="java.lang.String" />					
					<td class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">					
							<his:date name='effectiveDate' dateFormate="%d-%b-%Y" value="<%=effFrom%>" />						 
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="RoomMasterFB" property="effectiveDate" readonly="true" styleClass="textbox"/>
					</logic:equal>
					</div>
					</td>
	</tr>	
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
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	                
	                <html:select name="RoomMasterFB" tabindex="1" property="buildingCode" styleClass="regCbo" onchange="getBlock()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>" property="value" labelProperty="label" />
	   		 	</html:select>
	   		 	
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:select disabled="true" name="RoomMasterFB" tabindex="1" property="buildingCode" styleClass="regcbo" onchange="getBlock()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>" property="value" labelProperty="label" />
	   		 	</html:select>	         
  		 	</logic:equal>	   		 	
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
	        
	   <td width="20%"  class="tdfont">
	         <div align="left">
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	                
	                <html:select name="RoomMasterFB" tabindex="1" property="blockCode" styleClass="regCbo" onchange="getFloor()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_BLOCK%>" property="value" labelProperty="label" />
	   		 	</html:select>  		 	
	   		 	
	   		 	</logic:equal>
	   	
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	     <html:select disabled="true" name="RoomMasterFB" tabindex="1" property="blockCode" styleClass="regCbo" onchange="getFloor()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_BLOCK%>" property="value" labelProperty="label" />
	   		 	</html:select>         
  		 	</logic:equal>	   		 	
	          </div>
       </td>    
                                                           
  </tr>
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="floor"/></b>
		              </font>
		            </div>
	   </td>
	       
	   <td width="20%"  class="tdfont">
	         <div align="left">
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	                
	                <html:select name="RoomMasterFB" tabindex="1" property="floorCode" styleClass="regCbo" onchange="getRoom()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_FLOOR%>" property="value" labelProperty="label" />
	   		 	</html:select>
	   		 	
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	     <html:select disabled="true" name="RoomMasterFB" tabindex="1" property="floorCode" styleClass="regCbo" onchange="getRoom()">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_FLOOR%>" property="value" labelProperty="label" />
	   		 	</html:select>	         
  		 	</logic:equal>	   		 	
	          </div>
       </td>                                                 
  </tr> 
   <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font><bean:message key="roomName"/></b>
		              </font>
		            </div>
	   </td>	         
	   <td width="20%"  class="tdfont">
	         <div align="left">
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	                
	                <html:select name="RoomMasterFB" tabindex="1" property="estateRoomId" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_ESTATEROOM%>" property="value" labelProperty="label" />
	   		 	</html:select>
	   		 	
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:select disabled="true" name="RoomMasterFB" tabindex="1" property="estateRoomId" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ROOM_MST_ESTATEROOM%>" property="value" labelProperty="label" />
	   		 	     </html:select>    
  		 	</logic:equal>	   		 	
	          </div>
       </td>                                                            
  </tr>  
				
</table>  

 </his:ContentTag>
 <html:hidden name="RoomMasterFB" property="transactionMode" />
<html:hidden name="RoomMasterFB" property="<%=RegistrationConfig.VIEWORMODIFY%>" />		
<html:hidden name="RoomMasterFB" property="chk" />
<html:hidden name="RoomMasterFB" property="locationCode" />
<html:hidden name="RoomMasterFB" property="entryDate" value="<%=sysDate%>"/> 
<his:ButtonToolBarTag>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
				value="modify">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
					style=cursor:pointer tabindex="1" onclick="validateRoomMaster()"
					onkeypress="if(event.keyCode==13) validateRoomMaster()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CLEAR')"
					onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
		</his:ButtonToolBarTag>		
</his:TransactionContainer>
</html:form>
<his:status/>
</body>
</html>