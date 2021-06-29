<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
	
	<his:css src="/css/Color.css"/>
	<his:css src="/css/master.css"/>
	<his:css src="/css/hisStyle.css"/>
	<his:css src="/css/hisStyleExt.css"/>
	<his:css src="/css/calendar-blue2.css"/>
	 	
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	
	
	<table width="100%" cellpadding="0" height="100%" cellspacing="0">
	<tr >
	<td width="40%" align="center" nowrap class="tdfonthead">
	<div align="center" >
	<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="clickOnLeftMenuToViewReport" /> </font></b> 
		</div>
		
		</td>
	</tr>
	
	
	</table>
	<html:hidden name="commonReportFB" property="mode"/>
	<html:hidden name="commonReportFB" property="reportMode"/>
	