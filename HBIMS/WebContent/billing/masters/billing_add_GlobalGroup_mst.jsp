<%-- 
 Global Group Master ADD jsp
 author: Debashis Sardar
  Created on : 26-Aug-2011
  --%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Global Group Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript" src="../../billing/js/GlobalGroupMst.js"></script>
<style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
.style2 {color: #FF0000}
-->
</style>
</head>
<body>
<html:form name="groupBean" action="/masters/CNTGroupMst" type="billing.masters.controller.fb.GlobalGroupMstFB">
   <div class="errMsg"><bean:write name="groupBean" property="strErrorMsg"/></div>
	<div class="warningMsg"><bean:write name="groupBean" property="warnings"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="groupBean" property="msg"/></div>
<tag:tab tabLabel="Add Global Group" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
   <tr class ="HEADER">
  <td colspan ="2" >Global Group Master&gt;&gt; Add</td>
  </tr> 
   <tr >
    <td class="LABEL"><span class="style1">*</span>Group Code</td>
    <td width="50%" class ="CONTROL"><input type="text" name="strgroupCode" value ="" maxlength="10" onkeypress="return validateData(event,9);"> </td>
  </tr>              
   <tr >
    <td class="LABEL"><span class="style1">*</span>Group Name</td>
    <td width="50%" class ="CONTROL"><input type="text" name="strgroupName" value ="" maxlength="50" onkeypress="return validateData(event,9);"> </td>
  </tr>
 
   <tr class ="FOOTER" >
  <td colspan ="2" ><span class="style1">*</span>Mandatory Field</td>
  </tr>
</table>
	 <table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="Save Record" name="save" id="save"    src="../../hisglobal/images/btn-sv.png" onclick="return validate1();">
			<img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-clr.png" title="Reset Content" onClick="document.forms[0].reset(),document.forms[0].strgroupName.focus();" />
			<input style="cursor:pointer;cursor:hand" title="Cancel Process" name="cancel" value="cancel" type="image" src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');">
			 -->
				<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>	
			</td>
		</tr>
	</table>
	    
	   	<input type="hidden" name="hmode">
	 	<input type="HIDDEN" name="strgroupId">
	 	<input type="hidden" name="strhospitalcode" value ="${groupBean.strhospitalcode}" />
	 	<cmbPers:cmbPers/>
       
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>