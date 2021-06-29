<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

    <style type="text/css">
<!--
.style4 {font-size: 18%}
-->
    </style>
	<table>		  
		  <tr>
		  <td>
		  <h1><html:button property="buttonMode" value="        Permanent Closure       "onclick="submitTile('PERMANEMTCLOSURE')"/></h1></td></tr>		  
		  <tr>
		  <td>
		  <h1><html:button property="buttonMode" value="        Temporary Closure       "onclick="submitTile('TEMPCLOSURE')"/></h1></td></tr>
		  <tr>
		  <td>
		  <h1><html:button  property="buttonMode" value="        Change Capacity         " onclick="submitTile('CAPACITY')"/></h1></td></tr>		  
		  <tr>
		  <td>
		  <h1><html:button  property="buttonMode" value="         Change Sequence      "onclick="submitTile('SEQUENCE')"/></h1></td></tr>		
</table>