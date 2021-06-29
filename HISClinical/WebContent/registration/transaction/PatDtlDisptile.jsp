<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<table border="0" cellpadding="0" cellspacing="0" width = "100%" border="1" bordercolor="#000000" style="clear:both; border-left:1px solid #003366;">
  <tr> 
    <td valign="top"> 
		<table width = "100%"  border="0" cellpadding="0" cellspacing="1">         
          <tr> 
            <td colspan="2" class="header">
	             <bean:message key="patient"/>
	             <bean:message key="detail"/>
             </td>
             <td colspan="2" class="HEADER">
             	<div align="right">	     
		     <bean:message key="crNo"/>
		        <bean:write name="PatDtlDispFB" property="crNo1"/>		     
		         <bean:message key="line"/>
		        <bean:write name="PatDtlDispFB" property="crNo2"/>	
       		     <bean:message key="line"/>
		        <bean:write name="FB" property="crNo3"/>     	
		        	</div>         
		</td>                          
          </tr>
          <tr> 
          <td class="tdfonthead" width = "25%" nowrap>
          <div align="right">
          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:message key="name"/>
          </font>
          </div>
          </td>
            
          <td width = "25%"  class="tdfont" nowrap>
          <b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:write name="patientDetailFB"  property="patFirstName"/>
          <bean:write name="patientDetailFB"  property="patMiddleName"/>
          <bean:write name="patientDetailFB"  property="patLastName"/>
          </font>
          </b>
          </td>
          
          <td width = "25%" class="tdfonthead" nowrap>
          <div align="right">
          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:message key="patient"/> 
          <bean:message key="category"/>
          </font>
          </div>
          </td>
          
          <td width = "25%" class ="tdfont" nowrap>
          <b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:write name="patientDetailFB"  property="patPrimaryCat"/> 
          <logic:notEqual name="PatDtlDispFB"  property="patSecondaryCat" value="">/</logic:notEqual>
          <bean:write name="PatDtlDispFB"  property="patSecondaryCat"/></font></b></td>                        
          </tr>
          <%
          System.out.println("");
          %>
          <tr> 
          <td width = "25%"  class="tdfonthead" nowrap>
          <div align="right">
          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:message key="age"/>
          <bean:message key="gender"/>
          </font>
          </div>
          </td>
            
          <td width = "25%"  nowrap class="tdfont">
          <b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:write name="patientDetailFB"  property="patAge"/>
          <bean:write name="patientDetailFB"  property="patAgeUnit"/>
          <logic:notEqual name="patientDetailFB"  property="patGender" value=""></logic:notEqual>
          <bean:write name="patientDetailFB"  property="patGender"/>
          </font>
          </b>
          </td>
          
          <td class="tdfonthead" width = "25%"  nowrap>
          <div align="right">
          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      <bean:message key="guardian"/> 
	      <bean:message key="name"/>
          </font>
          </div>
          </td>
          
          <td width = "25%"  class ="tdfont" nowrap>
          <b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:write name="patientDetailFB"  property="patGuardianName"/>
          </font>
          </b>
          </td>
          </tr>      
      </table>          
    </td>    
  </tr>
 </table>