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
    
<%@ page import ="registration.*" %>

 <%String label=""; %>
<logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="VIEW" > 	          
<% label="Unit Details";%>
</logic:equal> 
<logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="ADMINMODIFY" > 
<% label="Administrative Modification";%>
</logic:equal>
<logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="PERMANEMTCLOSURE" > 
<% label="Permanent Closure";%>
</logic:equal>  
<logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="TEMPCLOSURE" > 
<% label="Temporary Closure";%>
</logic:equal>
<logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="MOD" > 
<% label="Modification";%>
</logic:equal>

 <his:SubTitleTag name="<%=label%>"> 
 </his:SubTitleTag> 
 <his:ContentTag>

 <table>
 <%
 boolean readonly =true;	 
 boolean disabled =true;
 %>
 <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="VIEW" > 
	 <%readonly=true;%>
	 <%disabled=true;%> 
 </logic:equal> 
 
  <tr>
      <td width="20%"  class="tdfonthead">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b>
	              <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="ADMINMODIFY">
	                <font color="#FF0000">*</font>
	              </logic:equal>
	              <bean:message key="unit"/>
	              </b>
	              </font>
	            </div>
      </td>      
      <td width="65%">
       <div align="left">	      
      <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="TEMPCLOSURE">
         	  <bean:write name="ModifyUnitLayoutFB" property="unitName"/>
              <html:hidden name="ModifyUnitLayoutFB" property="unitName"/>
      </logic:equal> 
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="VIEW">
         	  <bean:write name="ModifyUnitLayoutFB" property="unitName"/>
              <html:hidden name="ModifyUnitLayoutFB" property="unitName"/>
      </logic:equal> 
      <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="PERMANEMTCLOSURE">
         	  <bean:write name="ModifyUnitLayoutFB" property="unitName"/>
              <html:hidden name="ModifyUnitLayoutFB" property="unitName"/>
      </logic:equal>           
      
	  <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="ADMINMODIFY">
          <html:text name="ModifyUnitLayoutFB" property="unitName" maxlength="50"/>	         
      </logic:equal> 
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="MOD">
          <html:text name="ModifyUnitLayoutFB" property="unitName" maxlength="50"/>	         
      </logic:equal>           	
	   </div>
      </td>                                           
  </tr> 
  
  <%System.out.println("....5");%>  
  <tr>
	   <td width="65%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveDate"/></b>
		              </font>
		            </div>
	   </td>   
	   
  <bean:define name="ModifyUnitLayoutFB" property="effectiveDate" id="effDate" type="java.lang.String"/> 
  <bean:define name="ModifyUnitLayoutFB" property="inactiveFromDate" id="inacFromDate" type="java.lang.String"/> 
  <bean:define name="ModifyUnitLayoutFB" property="inactiveTillDate" id="inacTillDate" type="java.lang.String"/>        
	   <td align="left" width="50%">       
        <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="TEMPCLOSURE" > 
	        <his:date name='<%="effectiveDate"%>' dateFormate="%d-%b-%Y" value="<%=effDate %>" />
       </logic:equal> 
        <logic:notEqual name="ModifyUnitLayoutFB" property="saveMode" value="TEMPCLOSURE" > 
	        <bean:write name="ModifyUnitLayoutFB" property="effectiveDate"/>
            <html:hidden name="ModifyUnitLayoutFB" property="effectiveDate"/>
       </logic:notEqual> 
	      
	   </td>
  </tr>
  <%System.out.println("....6");%>  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b>
		              <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="ADMINMODIFY">
		                 <font color="#FF0000">*</font>
		              </logic:equal>
		              <bean:message key="unitType"/>
		              </b>
		              </font>
		            </div>
	   </td>   
	   <td width="65%" >
	    <div align="left">	    	   
	      <logic:notEqual name="ModifyUnitLayoutFB" property="saveMode" value="MOD" >
	      <bean:write name="<%=RegistrationConfig.TYPE_OF_UNIT%>"/>
          </logic:notEqual>     
         <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="MOD">
              <html:select name="ModifyUnitLayoutFB" tabindex="1" property="isGeneral" styleClass="registrationCmb">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNITTYPE%>" property="value" labelProperty="label" />
	   		 	</html:select>
      </logic:equal>

      </div>
       </td>                                                 
  </tr>
  <%System.out.println("....7");%>    
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		                 <b><bean:message key="inactiveFrom"/></b>
		              </font>
		            </div>
	   </td> 
	     <td align="left" width="65%">
	
	   <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="VIEW" > 	    
	           <bean:write name="ModifyUnitLayoutFB" property="inactiveFromDate"/>
              <html:hidden name="ModifyUnitLayoutFB" property="inactiveFromDate"/>
       </logic:equal> 
      
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="PERMANEMTCLOSURE" > 
	        <his:date name='<%="inactiveFromDate"%>' dateFormate="%d-%b-%Y" value="<%=inacFromDate %>" />
       </logic:equal> 
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="ADMINMODIFY" > 
	        <bean:write name="ModifyUnitLayoutFB" property="inactiveFromDate"/>
            <html:hidden name="ModifyUnitLayoutFB" property="inactiveFromDate"/>
       </logic:equal>  
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="MOD" > 
	        <bean:write name="ModifyUnitLayoutFB" property="inactiveFromDate"/>
            <html:hidden name="ModifyUnitLayoutFB" property="inactiveFromDate"/>
       </logic:equal>     
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="TEMPCLOSURE" > 
	        <his:date name='<%="inactiveFromDate"%>' dateFormate="%d-%b-%Y" value="<%=inacFromDate %>" />
       </logic:equal>     
       
       
	    	
	   </td>
  </tr>
  <%System.out.println("....8");%>  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="inactiveTill"/></b>
		              </font>
		            </div>
	   </td> 
	   <td align="left" width=65%> 
	   <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="VIEW" > 
	          <bean:write name="ModifyUnitLayoutFB" property="inactiveTillDate"/>
            <html:hidden name="ModifyUnitLayoutFB" property="inactiveTillDate"/>
       </logic:equal> 
       
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="PERMANEMTCLOSURE" > 	          
       </logic:equal> 
       <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="TEMPCLOSURE" > 
	        <his:date name='<%="inactiveTillDate"%>' dateFormate="%d-%b-%Y" value="<%=inacTillDate %>" />
       </logic:equal>      
        <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="ADMINMODIFY" > 
            <bean:write name="ModifyUnitLayoutFB" property="inactiveTillDate"/>
            <html:hidden name="ModifyUnitLayoutFB" property="inactiveTillDate"/>
       </logic:equal> 
        <logic:equal name="ModifyUnitLayoutFB" property="saveMode" value="MOD" > 
            <bean:write name="ModifyUnitLayoutFB" property="inactiveTillDate"/>
            <html:hidden name="ModifyUnitLayoutFB" property="inactiveTillDate"/>
       </logic:equal>  
	    	
	   </td>
  </tr>  
</table>
</his:ContentTag>
</html>