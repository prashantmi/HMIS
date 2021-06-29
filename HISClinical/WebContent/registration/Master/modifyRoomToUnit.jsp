
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
    

<%String label=""; %>
<logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="VIEW" > 	          
<% label="Details";%>
</logic:equal> 
<logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="CAPACITY" > 
<% label="Capacity Change";%>
</logic:equal>
<logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="PERMANEMTCLOSURE" > 
<% label="Permanent Closure";%>
</logic:equal>  
<logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="TEMPCLOSURE" > 
<% label="Temporary Closure";%>
</logic:equal>

 <his:SubTitleTag name="<%=label%>"> 
 </his:SubTitleTag> 
 <his:ContentTag>
 <table>
 <%
 boolean readonly =true;	 
 boolean disabled =true;
 System.out.println("sdddddddddddddddddddddddddd");
 %>
 <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="VIEW" > 
	 <%readonly=true;%>
	 <%disabled=true;%> 
 </logic:equal>
 
  
  <%System.out.println("....5");%>  
  <tr>
	   <td width="65%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomSequence"/></b>
		              </font>
		            </div>
	   </td> 
	   <td class="tdfont">	   
	     <bean:write name="ModifyRoomToUnitFB" property="sequenceNo"/>
	     <html:hidden name="ModifyRoomToUnitFB" property="sequenceNo"/>	 
	   </td>	     
    </tr>
    <tr>
	   <td width="65%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="CAPACITY" > 
		              <font color="#FF0000">*</font>
		              </logic:equal>
		              <b><bean:message key="capacity"/></b>
		              </font>
		            </div>
	   </td> 
	   <td class="tdfont">
             <logic:notEqual name="ModifyRoomToUnitFB" property="saveMode" value="CAPACITY" > 
	                <bean:write name="ModifyRoomToUnitFB" property="capacity"/>
	                <html:hidden name="ModifyRoomToUnitFB" property="capacity"/>	 
             </logic:notEqual> 	   	   	
             <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="CAPACITY" > 
	               <html:text name="ModifyRoomToUnitFB" property="capacity" maxlength="2" onkeypress="return validateNumeric(event)"/>	         
             </logic:equal> 
	   </td>	     
  </tr>
    
    
  <bean:define name="ModifyRoomToUnitFB" property="effectiveDate" id="effDate" type="java.lang.String"/> 
  <bean:define name="ModifyRoomToUnitFB" property="effectiveFrom" id="effFrom" type="java.lang.String"/> 
  <bean:define name="ModifyRoomToUnitFB" property="effectiveTo" id="effTo" type="java.lang.String"/>        
 
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		                 <b><bean:message key="effectiveFrom"/></b>
		              </font>
		            </div>
	   </td> 
	     <td align="left" width="65%" class="tdfont">
	
	   <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="VIEW" > 
	          <bean:write name="ModifyRoomToUnitFB" property="effectiveFrom"/>
	          <html:hidden name="ModifyRoomToUnitFB" property="effectiveFrom"/>
       </logic:equal> 
       <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="CAPACITY" > 
	        <bean:write name="ModifyRoomToUnitFB" property="effectiveFrom"/>
	          <html:hidden name="ModifyRoomToUnitFB" property="effectiveFrom"/>
       </logic:equal>
       <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="PERMANEMTCLOSURE" > 
	       <his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
       </logic:equal>       
       <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="TEMPCLOSURE" > 
	        <his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
       </logic:equal>     
	    	
	   </td>
  </tr>
  <%System.out.println("....8");%>  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		                <b><bean:message key="effectiveTo"/></b>
		              </font>
		            </div>
	   </td> 
	   <td align="left" width=65% class="tdfont"> 
	   <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="VIEW" > 
	         <bean:write name="ModifyRoomToUnitFB" property="effectiveTo"/>
	          <html:hidden name="ModifyRoomToUnitFB" property="effectiveTo"/>
       </logic:equal> 
       <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="CAPACITY" > 
	     	  <bean:write name="ModifyRoomToUnitFB" property="effectiveTo"/>
	          <html:hidden name="ModifyRoomToUnitFB" property="effectiveTo"/>
       </logic:equal>  
       <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="PERMANEMTCLOSURE" > 
	        <bean:write name="ModifyRoomToUnitFB" property="effectiveTo"/>
	          <html:hidden name="ModifyRoomToUnitFB" property="effectiveTo"/>
       </logic:equal> 
       <logic:equal name="ModifyRoomToUnitFB" property="saveMode" value="TEMPCLOSURE" > 
	        <his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y" value="<%=effTo %>" />
       </logic:equal>   
	   </td>
  </tr>  
</table>
</his:ContentTag>
</html>