<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="org.apache.struts.tiles.ComponentContext,registration.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title> 
  <tiles:getAsString name='title'  /> </title> 
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
 </head>
<script>



</script>

<body>


	
		<table>
 		<tr>	
            <td width="5%" class="tdfonthead" >
 			<div align="left">
 			<tiles:insert attribute='leftmenu'/>
		    
		    </div>
            </td>
            <td width="20%" class="tdfonthead" >
 			
		    <div align="right">
 			<tiles:insert attribute='body'/>
		    
		    </div>
            </td>
            <td width="5%" class="tdfonthead" >
 			
		    <div align="right">
 			<tiles:insert attribute='menu'/>
		    
		    </div>
            </td>
            </tr>
	 </table>	
	      	    
	   
	  
	    
</body>


</html>