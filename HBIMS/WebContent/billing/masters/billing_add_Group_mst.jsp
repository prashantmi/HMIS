<%-- 
 Group Master ADD jsp
 author: Debashis Sardar
  Created on : 09-Sep-2011
  --%>

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Group Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../billing/js/GroupMst.js"></script>

<style type="text/css">
	.style1 
	{
		color: #FF0000;
		font-weight: bold;
	}
	.style2 {color: #FF0000}
</style>
</head>
<body onLoad="document.forms[0].strgroupName.focus(),document.getElementById('forhospitalservice').style.display='block'">
<html:form name="grpBean" action="/masters/CNTGrpMst" type="billing.masters.controller.fb.GroupMstFB">
    
    <div class="errMsg"><bean:write name="grpBean" property="strErrorMsg"/></div>
	<div class="warningMsg"><bean:write name="grpBean" property="warnings"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="grpBean" property="msg"/></div>
	<tag:tab tabLabel="Add Group" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>

	<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
	   <tr class ="HEADER">
	  		<td colspan ="2" >Group Master&gt;&gt; Add</td>
	  </tr> 
	  <tr>
	  		<td class="LABEL"><div id="strgroupId"><span class="style1">*</span>Global Group Name   </div></td>
			 <td class ="CONTROL">
			 <select name="strGlobalGroupName" onchange="groupname()" class='comboNormal'>
					<bean:write name="grpBean" property="strGroupCombo" filter="false" />
				</select> </td>
	  </tr>
	  <tr>
	  		<td class="LABEL"><div id="strgroupId">Group Code   </div></td>
			 <td class ="CONTROL">
			 <input type="text" name="strGroupCode" class='txtFldMax' value ="" maxlength="10" onkeypress="return validateData(event,9);" readOnly> </td>
	  </tr>			           
	  <tr>
	    <td class="LABEL"><span class="style1">*</span>Group Name</td>
	    <td width="50%" class ="CONTROL"><input type="text" name="strgroupName" class='txtFldMax' value ="" maxlength="35" onkeypress="return validateData(event,9);"> </td>
	  </tr>
	  </table>
	  
	  <div id="forhospitalservice" style="display: none" align="center" onclick="checkboxname()">  
	    <bean:write name="grpBean" property="strHospitalServiceCheckbox" filter="false" />
	  </div>
	    
	  <table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
	  <tr style="display: none;">
	    <td  class ="LABEL" >Whether Packaged</td>
	    <td class="CONTROL" width="50%"><html:checkbox name="grpBean" property="strisPackage" value ="1"/></td>    
	  </tr>
	  <tr style="display: none;">
	    <td class ="LABEL" width ="50%"><div align="right"><span class="style1">*</span> Effective From</div></td>
	    <td class ="CONTROL"><date:date name="strEffectiveFrom" value ="${grpBean.strCrntDate}"></date:date></td>
	  </tr>
	  <tr>
	    <td width ="50%" class ="LABEL"valign="top">Remarks</td>
	    <td width ="50%" class ="CONTROL"><textarea  name="strremark" cols="25" rows="2" ></textarea></td>
	  </tr>
	   <tr style="display: none;">
	    <td width ="50%" class ="LABEL" align="right">Is Visible</td>
	    <td class="CONTROL" width="50%"><html:checkbox name="grpBean" property="strisVisible" value ="1"/></td>
	  </tr>
	  <tr style="display: none;">
	    <td width ="50%" class ="LABEL">Consumable Charge(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>
	    <td class ="CONTROL"><input type="text" name="strconsumeableCharge" value ="${grpBean.strconsumeableCharge}" onkeypress="return validateData(event,7);" maxlength="5"></td>
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
							<br><a href="#" class="button" id="" onclick=" return validate1();"><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset(),document.forms[0].counterName.focus();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
				
				</td>
		</tr>
	</table>

	    
	   	<input type="hidden" name="hmode">
	 	<input type="HIDDEN" name="strgroupId" value="${grpBean.strgroupId}" />
	 	<input type="hidden" name="strhospitalcode" value ="${grpBean.strhospitalcode}" />
	 	
	 	<cmbPers:cmbPers/>
       
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>