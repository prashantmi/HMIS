<%-- 
 Global Group Master Modify jsp
 author: Debashis Sardar
  Created on : 26-Aug-2011
  --%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Global Sub Group Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript" src="../../billing/js/GlobalSubGroupMst.js"></script>
<style type="text/css">
</style>
</head>

<body>
<html:form name="subgroupBean" action="/masters/CNTSubGroupMst" type="billing.masters.controller.fb.GlobalSubGroupMstFB">
    <div class="errMsg"><bean:write name="subgroupBean" property="strErrorMsg"/></div> 
	<div class="warningMsg"><bean:write name="subgroupBean" property="warnings"/></div>
    <div class="normalMsg" id='normalMsg'><bean:write name="subgroupBean" property="msg"/></div> 
<tag:tab tabLabel="Modify Global Sub Group" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
 <tr class ="HEADER">
   <td colspan ="2" >Global Sub Group Master&gt;&gt; Modify</td>
 </tr>
 <tr>
			<td class="LABEL" width="50%">Group Name</td>
			<td class="CONTROL" width="50%">
			<div id="GroupId">
			<bean:write name="subgroupBean" property="strgroupName" filter="false" /></div>
			</td>
 </tr>    
 <tr >
    <td class="LABEL"><font color="red">*</font>Sub Group Code</td>
    <td width="50%" class ="CONTROL"><input type="text" name="strsubgroupCode" value ="${subgroupBean.strsubgroupCode}" maxlength="10" onkeypress="return validateData(event,9);"> </td>
   </tr>           
   <tr >
    <td class="LABEL"><font color="red">*</font>Sub Group Name</td>
    <td width="50%" class ="CONTROL"><input type="text" name="strsubgroupName" value ="${subgroupBean.strsubgroupName}" maxlength="35" onkeypress="return validateData(event,9);"> </td>
   </tr>

    <tr >
    <td width ="45%" class ="LABEL"><font color="red">*</font>Record Status </td>
    <td width ="45%" class ="CONTROL" ><div id="allowedValid">
    <html:radio name="subgroupBean" property="strisValid" value="1" />Active<span class="LABEL">
    <html:radio name="subgroupBean" property="strisValid" value="2" />InActive
  
 </span></div><div id="nAllowedValid"></div></td>
    </tr>
   
   <tr class ="FOOTER" >
  <td colspan ="2" ><font color="red">*</font>Mandatory Field</td>
  </tr>
</table>
	 <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<!-- <td align="right"><img style="cursor:pointer;cursor:hand" title="Save Record" name="save" id="save"   src="../../hisglobal/images/btn-sv.png" onclick="return validate2();"></td>
			<td align="left"><img  style="cursor:pointer;cursor:hand" title="Cancel Process"    name="cancel"   src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();"></td> -->
		<br><td align="right"><a href="#" class="button" id="" onClick="return validate2();" ><span class="save">Save</span></a>
		<td align="left"><a href="#" class="button" onclick="cancelPage();"><span class="cancel">Cancel</span></a>	
		</tr>
	</table>

 
 		<html:hidden name="subgroupBean"  property="chk1" />
        <input type="hidden" name="chk" value="${subgroupBean.chk[0]}">
        <input type="hidden" name="strGroupId" value="${tariffBean.strGroupId}" />
        <input type="hidden" name="strhospitalcode" value ="${subgroupBean.strhospitalcode}" />
	   	<input type="hidden" name="hmode">
	   	<cmbPers:cmbPers/>
	   	<script>
	   	var checkBox = document.getElementsByName("chk");
	   	if(parseInt(checkBox[0].value.split("@")[1].split("$")[0])<121){
	   	
	   		document.getElementById("allowedValid").style.display="none";
	   		
	   		document.getElementById("nAllowedValid").innerHTML=document.getElementsByName("strisValid")[0].checked?"Active":"Inactive";
	   		
	   	}
	   	</script>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	
			
			