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
	<his:javascript src="/registration/js/dateFunctions.js"/>
	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

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

function validateModifyDeptUnitRoomMaster()
{
	var valid=true;
       effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	   effectiveTo = document.getElementsByName("effectiveTo")[0];
	   entryDate = document.getElementsByName("entryDate")[0];
   
   
//    	alert("effective from="+ effectiveFrom.value)
// 	    alert("effective To="+ document.getElementsByName("effectiveTo")[0].value)
//      alert("entry Date="+ document.getElementsByName("entryDate")[0].value)    
    if(
    isEmpty(document.forms[0].capacity,"Capacity")// && 
    //isEmptyWithoutFocus(document.forms[0].effectiveFrom,"Effective From Date") &&
     //   compareDateCall(entryDate, effectiveFrom ,2,"Current Date","Effective From Date") &&
	 // validateDate() 
	 ){
       valid=true;
       submitTile("SAVE");
    	}
    else
    valid=false;
    
    
  
	

}
function save(mode){ 
   savemode=document.getElementsByName("saveMode")[0].value; 
   document.getElementsByName("saveMode")[0].value=savemode;
   //alert("saveMode::::"+document.getElementsByName("saveMode")[0].value);
   document.getElementsByName("transactionMode")[0].value="SAVE";      
    document.forms[0].submit();
 }

function submitTile(mode){
   document.getElementsByName("transactionMode")[0].value=mode;         
   //alert("transactionMode"+document.getElementsByName("transactionMode")[0].value);
   document.forms[0].submit();
   
}

function checkval(mode){
element=document.getElementsByName("capacity")[0];
if(element.value.length==0)
{ 
	alert("Please enter the capacity");
	element.focus();
	return false;
}
else{
	//alert("True");
	submitTile(mode); 
 }
}

</script>
<%System.out.println("dsfsdhgfsdjg"); %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	  

<body>
<html:form action="/master/modifyRoomToUnit.cnt">
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>


<his:TitleTag name="Modification of rooms to Unit">			
	<b>
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        
    </font>
    </b>
</his:TitleTag> 

<%System.out.println("....1");%>  
<his:ContentTag>
  <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
  <tr>
      <td width="25%" class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="department"/></b>
	              </font>
	            </div>
      </td>                        
      
     <td width="25%" class="tdfont">
	  <div align="left">
      
<%--    <logic:equal name="ModifyRoomToUnitFB" property="source" value="fromview">  --%>
         <bean:write name="ModifyRoomToUnitFB" property="departmentName"/>
         <html:hidden name="ModifyRoomToUnitFB" property="departmentName"/>      
	     <html:hidden name="ModifyRoomToUnitFB" property="departmentCode"/>      
 <%--     </logic:equal>  --%>
      </div>
  <%--    <logic:notEqual name="ModifyRoomToUnitFB" property="source" value="fromview">
		  <html:select name="ModifyRoomToUnitFB" tabindex="1" property="departmentCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>--%>
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
<%--       <logic:equal name="ModifyRoomToUnitFB" property="source" value="fromview">--%>
         <bean:write name="ModifyRoomToUnitFB" property="unitName"/>
         <html:hidden name="ModifyRoomToUnitFB" property="unitName"/>      
	     <html:hidden name="ModifyRoomToUnitFB" property="unitCode"/>    
 <%--      </logic:equal>
     
<%--      <logic:notEqual name="ModifyRoomToUnitFB" property="source" value="fromview">
		  <html:select name="ModifyRoomToUnitFB" tabindex="1" property="unitCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual> 
	  --%>
	   </div>
	   </td>            
</tr>
 
 <tr>
 	   
	                                      
	    <td width="25%"  class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="room"/></b>
	              </font>
	            </div>
      </td>      
      <td width="25%"  class="tdfont">
	         <div align="left">	           
<%-- 	     <logic:equal name="ModifyRoomToUnitFB" property="source" value="fromview">--%>
         <bean:write name="ModifyRoomToUnitFB" property="roomName"/>
         <html:hidden name="ModifyRoomToUnitFB" property="roomName"/>      
	     <html:hidden name="ModifyRoomToUnitFB" property="roomCode"/>      
  <%--     </logic:equal>
     
      <logic:notEqual name="ModifyRoomToUnitFB" property="source" value="fromview">
		  <html:select name="ModifyRoomToUnitFB" tabindex="1" property="unitCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>--%>
	          </div>
	   </td>
	   
	    <td width="25%"  class="tdfonthead"></td>
	   
	    <td width="25%"  class="tdfont"></td>   
	   
 </tr> 
 
 </table>
 </his:ContentTag>
 
<his:statusInProcessWithJsp>

 
 <his:ContentTag>
  <table width="100%" border="0"  cellspacing="1" cellpadding="0">  
	<tr>
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomSequence"/></b>
		              </font>
		            </div>
	   </td> 
	   <td class="tdfont">
             <logic:notEqual name="ModifyRoomToUnitFB" property="saveMode" value="SEQUENCE" > 
	                <bean:write name="ModifyRoomToUnitFB" property="sequenceNo"/>
	                <html:hidden name="ModifyRoomToUnitFB" property="sequenceNo"/>	 
             </logic:notEqual> 	   	   	
             <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="SEQUENCE" > 
	               <html:text name="ModifyRoomToUnitFB" property="sequenceNo" maxlength="2" onkeypress="return validateNumeric(event)"/>	         
             </logic:equal> 
	   </td>
	   <td>
		  <h1></h1>
		  </td>
	  </tr>		
 	     
  
    <tr>
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		            
		              <font color="#FF0000">*</font>
		            
		              <b><bean:message key="capacity"/></b>
		              </font>
		            </div>
	   </td> 
	   <td class="tdfont">
           
	               <html:text name="ModifyRoomToUnitFB" property="capacity" maxlength="2" onkeypress="return validateNumeric(event)"/>	         
          
	   </td>	     
  </tr>
    
    <tr>
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		            
		              <font color="#FF0000">*</font>
		            
		              <b><bean:message key="daywiseCapacityMode"/></b>
		              </font>
		            </div>
	   </td> 
	   <td class="tdfont">
           
            <his:checkbox property="capacityMode" name="ModifyRoomToUnitFB"  value="2"/>
	                       
          
	   </td>	     
  </tr> 
  
  <bean:define name="ModifyRoomToUnitFB" property="effectiveFrom" id="effFrom" type="java.lang.String"/> 
  <bean:define name="ModifyRoomToUnitFB" property="effectiveTo" id="effTo" type="java.lang.String"/>        

<%--  
  <tr>
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		                 <b><font color="#FF0000">*</font><bean:message key="effectiveFrom"/></b>
		              </font>
		            </div>
	   </td> 
	     <td align="left" width="50%" class="tdfont">
	
	   
	        <his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
        
	    	
	   </td>
  </tr>
  
  <tr>
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		                <b><bean:message key="effectiveTo"/></b>
		              </font>
		            </div>
	   </td> 
	   <td align="left" width="50%" class="tdfont"> 
	 
	        <his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y" value="<%=effTo %>" />
       
	   </td>
  </tr> 
--%>
	   
 </table>
       
 </his:ContentTag>
</his:statusInProcessWithJsp>
 <html:hidden name="ModifyRoomToUnitFB" property="transactionMode"/>
 <html:hidden name="ModifyRoomToUnitFB" property="source"/> 
 <html:hidden name="ModifyRoomToUnitFB" property="saveMode"/>
 <html:hidden name="ModifyRoomToUnitFB" property="entryDate" value="<%=sysDate%>"/>
 
 <his:ButtonToolBarTag>
     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateModifyDeptUnitRoomMaster()" tabindex="1" onclick="validateModifyDeptUnitRoomMaster()" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13)submitTile('VIEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
          <html:button  property="buttonMode" value="Change Sequence"onclick="submitTile('SEQUENCE')"/>
         
 </his:ButtonToolBarTag> 
 </his:TransactionContainer>
 </html:form>
 <his:status/>
 </body>
 </html>
