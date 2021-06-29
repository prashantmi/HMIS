<%-- 
 Group Master Modify jsp
 author: Debashis Sardar
  Created on : 09-Sep-2011
  --%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Group Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language ="javaScript" src="../../billing/js/GroupMst.js"></script>
<style type="text/css">
</style>
</head>

<body onLoad="document.forms[0].strgroupName.focus(), document.getElementById('forhospitalservice').style.display='block' ">
<html:form name="grpBean" action="/masters/CNTGrpMst" type="billing.masters.controller.fb.GroupMstFB">

    <div class="errMsg"><bean:write name="grpBean" property="strErrorMsg"/></div> 
	<div class="warningMsg"><bean:write name="grpBean" property="warnings"/></div>
    <div class="normalMsg" id='normalMsg'><bean:write name="grpBean" property="msg"/></div> 
	<tag:tab tabLabel="Modify Group" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
	
	<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
	 <tr class ="HEADER">
	   <td colspan ="2" >Group Master&gt;&gt; Modify</td>
	 </tr>  
	 <tr>
	    <td class="LABEL"><font color="red">*</font>Group Name</td>
	    <td width="50%" class ="CONTROL"><input type="text" name="strgroupName" class='txtFldMax'  value ="${grpBean.strgroupName}" maxlength="35" onkeypress="return validateData(event,9);"> </td>
     </tr>
	  </table>
	  
	  <div id="forhospitalservice" style="display: none" align="center" onclick="checkboxmodify()"> 
	    <bean:write name="grpBean" property="strHospitalServiceCheckbox" filter="false" />
	  </div>
		   
	  <table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
	 <tr style="display: none;">
	    <td  class ="LABEL" >Whether Packaged</td>
	    <td class="CONTROL" width="50%"><div id="allowedPackage"><html:checkbox name="grpBean" property="strisPackage" value="1" /></div><div id="nAllowedPackage"></div></td>
	 </tr>
	 <tr style="display: none;">
	    <td class ="LABEL" width ="50%"><div align="right"><font color="red">*</font> Effective From</div></td>
	    <td class ="CONTROL"><div id="allowedEffectiveFrom"> <bean:write name="grpBean" property="strEffectiveFrom" /></div></td>
	 </tr>
	 <tr>
	    <td width ="50%" class ="LABEL"valign="top">Remarks</td>
	    <td width ="50%" class ="CONTROL"><textarea  name="strremark" cols="25" rows="2"><bean:write name="grpBean" property="strremark"/></textarea></td>
	 </tr>
	 <tr style="display: none;">  
	    <td width ="50%" class ="LABEL" >Is Visible</td>
	    <td class="CONTROL" width="50%"><html:checkbox name="grpBean" property="strisVisible" value="1" /></td>
	 </tr>
	 <tr>
	    <td width ="45%" class ="LABEL"><font color="red">*</font>Record Status </td>
	    <td width ="45%" class ="CONTROL" ><div id="allowedValid">
	    <html:radio name="grpBean" property="strisValid" value="1" />Active<span class="LABEL">
	    <html:radio name="grpBean" property="strisValid" value="2" />InActive
	 	</span></div><div id="nAllowedValid"></div></td>
	 </tr>
	 <tr style="display: none;">
	    <td width ="50%" class ="LABEL">Consumable Charge(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
	    <td class ="CONTROL"><input type="text" name="strconsumeableCharge" value ="${grpBean.strconsumeableCharge}"  onkeypress="return validateData(event,7);" maxlength="5"></td>
	 </tr>
	 <tr class ="FOOTER" >
	  <td colspan ="2" ><font color="red">*</font>Mandatory Field</td>
	 </tr>
	</table>
	
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<!-- <td align="right"><img style="cursor:pointer;cursor:hand" title="Save Record" name="save" id="save"   src="../../hisglobal/images/btn-sv.png" onclick="return validate2();"></td>
			<td align="left"><img  style="cursor:pointer;cursor:hand" title="Cancel Process"    name="cancel"   src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();"></td>
		 -->
			<br><td align="right"><a href="#" class="button" id="" onclick=' return validate2();'><span class="save">Save</span></a></td>
				<td align="left"><a href="#" class="button" onclick="cancelPage()"><span class="cancel">Cancel</span></a></td>
		</tr>
	</table>     
      	
      	<input type="hidden" name="chk1" value="${grpBean.chk1}">
        <input type="hidden" name="chk" value="${grpBean.chk[0]}">
        <input type="hidden" name="strCrntDate" value="${grpBean.strCrntDate}">
	   	<input type="hidden" name="hmode">
	   	<cmbPers:cmbPers/>
	   	<script>
	   	var checkBox = document.getElementsByName("chk");
	   	if(parseInt(checkBox[0].value.split("@")[1].split("$")[0])<121)
	   	{
	   	
	   		document.getElementById("allowedValid").style.display="none";
	   		document.getElementById("allowedEffectiveFrom").style.display="none";
	   		document.getElementById("allowedRemark").style.display="none";	   		
	   		document.getElementById("allowedPackage").style.display="none";
	   		
	   		document.getElementById("nAllowedEffectiveFrom").innerHTML=document.getElementsByName("strEffectiveFrom")[0].value;
	   		document.getElementById("nAllowedRemark").innerHTML=document.getElementsByName("strremark")[0].value;
	   		document.getElementById("nAllowedPackage").innerHTML=document.getElementsByName("strisPackage")[0].checked?"Yes":"No";
	   		document.getElementById("nAllowedValid").innerHTML=document.getElementsByName("strisValid")[0].checked?"Active":"Inactive";
	   		
	   	}
	   	</script>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>