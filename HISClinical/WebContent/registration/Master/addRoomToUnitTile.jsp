<%
try{
%>
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
<his:javascript src="/registration/js/dateFunctions.js"/>

<script>

window.history.forward()

function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmptyWithoutFocus(d1,l1) && isEmptyWithoutFocus(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" can not be greater than "+l2);
	valid = false;
	}
}

else
valid=false;

return valid;
}

function isEmptyWithoutFocus(obj,name)
{
  	if(obj!=null && obj!='undefined')
	{
	var value=obj.value;
		if(value=="" || value=="-1")
		{
			alert("Please Enter the "+ name);
			obj.value=value;
			return false;
		}
		else
		return true;
	}
	return false;
}

function submitForm(mode)
{
	document.getElementsByName("transactionMode")[0].value=mode;
    document.forms[0].submit();
}

function validateDate()
{
var valid=true;
if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
		   {
		   valid= false;
		   return valid
		   }
	   }
	   return valid
}

function validateDeptUnitRoomMaster()
{//alert("validateDeptUnitRoomMaster");
	var valid=true;
      // effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	//   effectiveTo = document.getElementsByName("effectiveTo")[0];
	//   entryDate = document.getElementsByName("entryDate")[0];
	   
	   
//alert("values assigned");
	
for(var i=0; i < document.getElementsByName("capacity").length ; i++ )
	{
//	alert("1")
	valid=false;

if(isEmpty(document.getElementsByName("capacity")[i],"Capacity"))
 		valid=true;
 		else
 		break;


	}
	//alert("valid----"+valid)
	
	if(valid==true)
		submitTile('SAVE'); 
		

/*
	if(
		  isEmpty(document.forms[0].capacity,"Capacity") //&& 
	  //  isEmptyWithoutFocus(document.forms[0].effectiveFrom,"Effective From Date") &&
      //  compareDateCall(entryDate, effectiveFrom ,2,"Current Date","Effective From Date") &&
      //  validateDate()
       )
	   {
	  	valid=true;
	  	submitTile('SAVE');
	  	}
	else
	{
		valid=false;
		
	}*/
	
	
}

	function removeRooms(idx,mode){
	// alert("sdsd");
		var elmt=document.getElementsByName('removeRoom')[0];
		//alert("sdsd");
		elmt.value=idx;	
		//alert("sdsd");
		submitTile(mode);
	}
	
	function save(){			
	 document.getElementById("defaultSequence").style.display="";  
	 document.getElementById("saveDiv").style.display="none";  
	 compareDate(exisingeffectiveDate,exisinginactiveFromDate,mode);
	}

	function submitTile(mode){
	   document.getElementsByName("transactionMode")[0].value=mode;
	  // alert("transactionMode"+document.getElementsByName("transactionMode")[0].value);
	   document.forms[0].submit();
	}
	function checkvalue()
	{
		//alert("check val");
		if(document.getElementsByName('roomCode')[0].value=="-1")
		alert("Select a Room");
	else
		{
		//document.getElementsByName('departmentdiv')[0].value="1";
		//alert("true");
		submitTile('ADDROOM'); 
 		}
 	}
	/*
	function checkval(){
	element=document.getElementsByName("capacity");
	for(i=0;i<element.length;i++)
		{
			if(element[i].value.length==0)
				{				
				alert("Please enter the capacity");
				element[i].focus();
				return false;
				}
				else{
				  alert("True");				
				}
		}
		
}		*/
</script>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	

<body>
<html:form action="/master/addRoomToUnit.cnt">
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>



<his:TitleTag name="Add Rooms To Unit">			
	<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	       
     </font></b>
</his:TitleTag> 
 
<his:ContentTag>
  <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
  <tr>
      <td width="25%"  class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="department"/></b>
	              </font>
	            </div>
      </td>                        
      
     <td width="25%" class="tdfont">
	  <div align="left">
      
      <logic:equal name="AddRoomToUnitFB" property="source" value="fromview">
         <bean:write name="AddRoomToUnitFB" property="departmentName"/>
         <html:hidden name="AddRoomToUnitFB" property="departmentName"/>      
	     <html:hidden name="AddRoomToUnitFB" property="departmentCode"/>      
      </logic:equal>
      </div>
      <logic:notEqual name="AddRoomToUnitFB" property="source" value="fromview">
		  <html:select name="AddRoomToUnitFB" tabindex="1" property="departmentCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>
            </td>
  
      <td width="25%"  class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="unit"/></b>
	              </font>
	            </div>
      </td> 
      <td width="25%" class="tdfont">
	  <div align="left">     
      <logic:equal name="AddRoomToUnitFB" property="source" value="fromview">
         <bean:write name="AddRoomToUnitFB" property="unitName"/>
         <html:hidden name="AddRoomToUnitFB" property="unitName"/>      
	     <html:hidden name="AddRoomToUnitFB" property="unitCode"/>      
      </logic:equal>
     
      <logic:notEqual name="AddRoomToUnitFB" property="source" value="fromview">
		  <html:select name="AddRoomToUnitFB" tabindex="1" property="unitCode" styleClass="registrationCmb">
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
 <logic:notEmpty name="AddRoomToUnitFB" property="existingRoomName">
 <his:SubTitleTag name="Existing Rooms to Unit"> 
 </his:SubTitleTag> 
 <his:ContentTag>
 <table width="100%" border="0"  cellspacing="1" cellpadding="0"> 
 
 <%System.out.println("existing rooms found");%>  
 <tr>
  <td width="25%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomName"/></b>
		              </font>
		            </div>
 </td>     

  <td width="25%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomSequence"/></b>
		              </font>
		            </div>
 </td>      
 
 <%-- 

  <td width="25%"  class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveFrom"/></b>
		              </font>
		            </div>
   </td> 

   <td width="25%"  class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveTo"/></b>
		              </font>
		            </div>
   </td>
   
--%>   
   
   
 </tr>   
  <logic:iterate name="AddRoomToUnitFB" property="existingRoomName" id="roomName" indexId="idx">
  <bean:define name="AddRoomToUnitFB" property="existingsequenceNo" id="sequenceNo" type="java.lang.String[]"/> 
  <bean:define name="AddRoomToUnitFB" property="existingEffectiveFrom" id="existingEffFrom" type="java.lang.String[]"/> 
  <bean:define name="AddRoomToUnitFB" property="existingEffectiveTo" id="existingEffTo" type="java.lang.String[]"/>     
  
  <tr>
       <%String i= idx.toString(); 
       try{
       %>	     
	   
	   <td width="25%"  class="tdfont">
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		        		             	             
		             <bean:write name="roomName" />
		             </font>
		            </div>
		            
		            <input type="hidden" name="existingRoomName"  value ='<bean:write name="roomName" />'/>
	   </td> 
	   <td width="25%"  class="tdfont">
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		        
		              <%=sequenceNo[idx.intValue()] %>		
		              </font>
		            </div>
		            <html:hidden name="AddRoomToUnitFB" property="existingsequenceNo" value="<%=sequenceNo[idx.intValue()] %>"/> 
	   </td>		   
	   	   
	   
	   <%-- 	   <td width="25%"  class="tdfont">
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		        
		              <%=existingEffFrom[idx.intValue()] %>		
		              </font>
		            </div>
		            <html:hidden name="AddRoomToUnitFB" property="existingEffectiveFrom" value="<%=existingEffFrom[idx.intValue()] %>"/> 
	   </td>	
	   	
	   <td width="25%"  class="tdfont">	   
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		        
		              <%=existingEffTo[idx.intValue()] %>		
		              </font>
		            </div>
		            <html:hidden name="AddRoomToUnitFB" property="existingEffectiveTo" value="<%=existingEffTo[idx.intValue()] %>"/> 
	   </td>	
	  --%>
	   
	   	 
	   <%
       }
		   catch(Exception e){
			   e.printStackTrace();
			   } 
	   
	   
	   %>
	 </tr>
  </logic:iterate>
</table>
 </his:ContentTag>
  </logic:notEmpty>
 <his:SubTitleTag name="Add Rooms">
 </his:SubTitleTag>
 <his:ContentTag>
  
 <table>
  <tr>      
      <td width="25%"  class="tdfont">
	         <div align="center">	           
	             <html:select name="AddRoomToUnitFB" tabindex="1" property="roomCode" styleClass="registrationCmb">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=RegistrationConfig.ALL_ROOMS%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
	  </td>  
	  <td width="25%" class="tdfont">
			<div align="center">   
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' alt="Add Rooms" title="Add Rooms" onkeypress="if(event.keyCode==13) checkvalue();" onclick="checkvalue();" tabindex="1">
			</div>
      </td> 
      
         <td width="25%"  class="tdfont"></td>
         <td width="25%"  class="tdfont"></td>
         <td width="25%"  class="tdfont"></td>
 </tr> 
 
 <logic:notEmpty name="<%=RegistrationConfig.COLLECTION_NEW_ROOMS_TO_UNIT%>" >
 <tr>      
	  <td width="25%" class="tdfonthead">
			<div align="center">   
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="roomName"/></b>
    		</font>
			</div>
      </td> 
      <td width="25%" class="tdfonthead">
			<div align="center">   
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
    		<b>
    		<bean:message key="sequence"/>
    		</b>
    		</font>
			</div>
      </td>       
     
      <td width="25%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>
		              <bean:message key="capacity"/></b>
		              </font>
		            </div>
	  </td> 
      
      <td width="25%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>
		              <bean:message key="daywiseCapacityMode"/></b>
		              </font>
		            </div>
	  </td> 
      
<%--       
	  
	   <td width="25%"  class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveFrom"/></b>
		              </font>
		            </div>
	   </td> 
	      <td width="25%"  class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveTo"/></b>
		              </font>
		            </div>
		  </td>
--%>		  

		 <td width="25%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="remove"/></b>
		              </font>
		            </div>
	   </td>     
 </tr> 
 
 <bean:define name="AddRoomToUnitFB" property="effectiveFrom" id="effFrom" type="java.lang.String[]"/>                   
 <bean:define name="AddRoomToUnitFB" property="effectiveTo" id="effTo" type="java.lang.String[]"/>                   
 <bean:define name="AddRoomToUnitFB" property="capacity" id="capacity" type="java.lang.String[]"/> 
 <bean:define name="AddRoomToUnitFB" property="capacityMode" id="capacityMode" type="java.lang.String[]"/>
 
 <%varStatus="InProcess";%> 
 <%
   List lst=(List)session.getAttribute(RegistrationConfig.COLLECTION_NEW_ROOMS_TO_UNIT);
 
 
 for(int i=0;i<lst.size();i++){
	 System.out.println("................in for"+lst.size());
	DeptUnitRoom deptUnitRoom= (DeptUnitRoom)lst.get(i); 
	System.out.println("deptUnitRoom.getRoomCode()"+deptUnitRoom.getRoomCode());
	 %>
	  <%
         if(effFrom[i]==null||effFrom[i].equalsIgnoreCase("")){ 
        	 
        	
        	 effFrom[i] =sysDate;
         }%>
	 <%
         if(effTo[i]==null||effTo[i].equalsIgnoreCase("")){ 
        	 
        	
        	 effTo[i] = "";
         }%>                  
   
	 <tr>
	  <%
	System.out.println("deptUnitRoom.getRoomCode()"+deptUnitRoom.getRoomCode()); %>
	 <td width="10%"  class="tdfont">
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                  <%= deptUnitRoom.getRoomName()%>
		              </font>
		            </div>

	  </td>
	 <%
	System.out.println("deptUnitRoom.getRoomCode()"+deptUnitRoom.getRoomCode()); %>  
	  <td width="10%"  class="tdfont">
		           <div align="center">	           
		             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                  <%= deptUnitRoom.getRoomsequence()%>
		              </font>
		            </div>
	  </td> 
	   <%
	System.out.println("deptUnitRoom.getRoomCode()"+deptUnitRoom.getRoomCode());
	   %>	     
	   
	  <td width="10%" class="tdfont" nowrap>
	         <div align="center">	           
	         	<html:text name="AddRoomToUnitFB" maxlength="2" onkeypress="return validateNumeric(event)"  property="capacity" value="<%=capacity[i]%>" tabindex="1"/>
	        
	        <html:hidden name="AddRoomToUnitFB" property="effectiveFrom" value=""/>
	        <html:hidden name="AddRoomToUnitFB" property="effectiveTo" value=""/>
	        
	         </div>
      </td>                                           

	   
	   <td width="10%" class="tdfont" nowrap>
	         <div align="center">	     
	        
	         	 <his:checkbox property="capacityMode" name="AddRoomToUnitFB"  value="<%=Integer.toString(i)%>"/>	  
	         	 
	         </div>
      </td> 
      
	   <%--  
	   <td nowrap>
	   		  <div align="center">          
	          <his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom[i]%>" id='<%="effectiveFrom"+i%>'/>	       	    	
	   		  </div>
	   </td>    
	   
      
	   <td nowrap>		    	  
	       <div align="center">
	       <his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y" value="<%=effTo[i]%>" id='<%="effectiveTo"+i%>'/>	      	      
		   </div>	   
	   </td> 
	  --%>   
	   
	   <td width="15%" class="tdfont">
			<div align="center">   
			   <img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-minus.png"/>' alt="Remove Rooms" title="Remove Rooms" onkeypress="if(event.keyCode==13) removeRooms('<%=String.valueOf(i)%>','REMOVEROOM')" onclick="removeRooms('<%=String.valueOf(i)%>','REMOVEROOM')" tabindex="1">
			</div>
      </td> 	            
  </tr>  
	    <%
	System.out.println("deptUnitRoom.getRoomCode()"+deptUnitRoom.getRoomCode()); %>
<% 	 
 } 
  System.out.println("after for:::::::"); %>
</logic:notEmpty>
  
  
</table>
 </his:ContentTag> 	 
</his:statusInProcessWithJsp>
 <html:hidden name="AddRoomToUnitFB" property="transactionMode"/>
  <html:hidden name="AddRoomToUnitFB" property="source"/>
 <html:hidden name="AddRoomToUnitFB" property="removeRoom"/> 
 <html:hidden name="AddRoomToUnitFB" property="entryDate" value="<%=sysDate %>"/> 
 	 <his:ButtonToolBarTag>
 	 <span id="saveDiv">
 	 <%if(varStatus.equals("InProcess")){%>
          <div align="center">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateDeptUnitRoomMaster()" tabindex="1" onclick="validateDeptUnitRoomMaster()">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	 <%} else{ %>
           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  <%} %>
	  </span>
	  <span id="defaultSequence" style='display:none'>
        <b><font color="#FF0000">Do you Want to continue with default sequence alloted </b>
          <img class="button" src='<his:path src="/hisglobal/images/btn-yes.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('SAVE')" onkeypress="if(event.keyCode==13) submitTile('SAVE')">
          <img class="button" src='<his:path src="/hisglobal/images/btn-no.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('SAVESEQUENCE')" onkeypress="if(event.keyCode==13) submitTile('SAVESEQUENCE');">
                 
	  </span>
	</his:ButtonToolBarTag>	
 </his:TransactionContainer>
 </html:form>
 <his:status/>
 </body>
 </html>
 <%
}catch(Exception e){e.printStackTrace();}
 %>