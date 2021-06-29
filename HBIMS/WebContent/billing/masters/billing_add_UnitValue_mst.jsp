<%-- 
 Unit Value Master ADD jsp
 author: Debashis Sardar
  Created on : 16-Sep-2011
  --%>
 <!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
		<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
		<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
		<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
	    <%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
	    <%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
		
<html>
<head>
<meta charset=utf-8>
<title>Unit Value Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../billing/js/UnitValueMst.js"></script>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</head>
<body >
<html:form name="unitvalueBean" action="/masters/CNTUnitValueMst" type="billing.masters.controller.fb.UnitValueMstFB">
<div class="errMsg"><bean:write name="unitvalueBean" property="strErrorMsg"/></div>
<div class="warningMsg"><bean:write name="unitvalueBean" property="strWarning"/></div>
<div class="normalMsg" id='normalMsg'><bean:write name="unitvalueBean" property="strMsg"/></div>



<tag:tab tabLabel="Add Unit Value" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
   <tr class="HEADER"> 
    <td colspan="2">Unit Value Master&gt;&gt;Add</td>
  </tr>               
   <tr>
    <td class="LABEL"><span class="style1">*</span>Module Name </td>
    <td width="50%" class ="CONTROL">
    <select name ="strmoduleName" class="comboNormal" onChange="combo1('FROMUNITVAL');">
	<bean:write name="unitvalueBean" property="strCmbval" filter="false"/>
	</select></td>
  </tr>
 
  <tr>
    <td class="LABEL"><span class="style1">*</span>From Unit </td>
    <td width="50%" class ="CONTROL"><div id ="fromUnitId"><select name ="strfromUnit" class="comboNormal" ><option value="0">Select Value</option></select> </div>
    </td>
  </tr>
   <tr >
    <td class="LABEL"><span class="style1">*</span>To Unit </td>
    <td width="50%" class ="CONTROL"><div id ="toUnitId">
      <select name ="strtoUnit" class="comboNormal">
        <option value ="0">Select Value</option>
      </select>
    </div></td>
  </tr>
   <tr >
    <td class="LABEL"><span class="style1">*</span>Converted Value </td>
    <td width="50%" class ="CONTROL"><input type="text" class="txtFldMax" name="strconvertedValue" value ="" maxlength="10" onKeypress ='return validateData(event,7);'> </td>
  </tr>
  <tr >
    <td class ="LABEL" width ="50%"><div align="right"><span class="style1">*</span> Effective From</div></td>
    <td class ="CONTROL"><date:date name="streffectiveFrom" value ="${unitvalueBean.strCtDate}"> </date:date></td>
  </tr>
  <tr >
    <td width ="50%" class ="LABEL"valign="top">Remarks if Any </td>
    <td width ="50%" class ="CONTROL"><textarea  name="strremark" ></textarea></td>
  </tr>
    <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
   </tr>
</table>
	 
	    <table CLASS ="TABLEWIDTH" align="center">
	      <tr> 
	<td align="center">
		<!-- <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-sv.png" title="Save Record" onClick=" return validate1(); "/>
		<img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-clr.png" title="Reset Content" onClick="document.forms[0].reset(),document.forms[0].strmoduleName.focus();" />
		<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" title="Cancel Process" onClick ="cancel('LIST');"/> -->
	   
	   <br>					 
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
				<a href="#" class="button"	onclick="document.forms[0].reset(),document.forms[0].strmoduleName.focus();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	    </td>
	      </tr>
	    </table>
	    
	   	<input type="hidden" name="hmode">
	   	<input type="hidden" name="StrHospitalCode" value =""/>
	   	<input type="hidden" name="StrModuleId" value =""/>
	   	<cmbPers:cmbPers/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	
			
			