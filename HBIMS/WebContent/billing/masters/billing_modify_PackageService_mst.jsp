<%-- 
 Package Service Master Modify jsp
 author: Debashis Sardar
  Created on : 01-Sep-2011
  --%>
 <!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Package/Estimation Tariff Mapping Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<style type="text/css">@import url(../../hisglobal/css/calendar-tas.css);</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script> 
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../billing/js/PackageServiceMst.js"></script>
</head>
<body>

<html:form action="/masters/CNTpackservMst.cnt" type="billing.masters.controller.fb.PackageServiceMstFB" name="packservBean">	
<div class="errMsg"><bean:write name="packservBean" property="errmsg"/></div>
<div class="warningMsg"><bean:write name="packservBean" property="warningMsg"/></div>
<div class="normalMsg" id='normalMsg'><bean:write name="packservBean" property="normalMsg"/></div>
<tag:tab tabLabel="Modify Page" align="center" width="TABLEWIDTH"></tag:tab>
		
  <table class="TABLEWIDTH"  border="0" align="center" cellpadding="1px" cellspacing="1px">
    <tr class="HEADER">
      <td class="HEADER" colspan="2">Package/Estimation Tariff Mapping Master&gt;&gt;Modify </td>
    </tr>
    <tr>
      <td class="LABEL" width="45%"><div align="right">Package Name </div></td>
      <td class="CONTROL" width="45%"><div align="left">
       <bean:write name="packservBean" property="strpackageName" filter="false"/>
      </div></td>
    </tr>
    
     <tr>
      <td class="LABEL" width="45%"><div align="right">Tariff Name </div></td>
      <td class="CONTROL" width="45%">
      <div align="left">
        <bean:write name="packservBean" property="strtariffName" filter="false"/>
      </div></td>
    </tr>
    
    <tr>
      <td class="LABEL"><div align="right"><font color="red">*</font>Quantity</div></td>
      <td class="CONTROL">
      <div align="left">
        <input type="text" name="strqty" maxlength="2" value="${packservBean.strqty}" onkeypress="return validateData(event,5);">
		<%//System.out.println("siddd=" + bean.getIpAddress()); %>
      </div></td>
    </tr>
    
		<tr>
			<td class="LABEL" width="45%">
			<div align="right"><font color="red">*</font>Unit Name</div>
			</td>
			<td class="CONTROL" width="45%">
		<select name="strunitId" >
				<bean:write name="packservBean" property="strpackunitModuleCombo"
					filter="false" />
			</select>
			</td>
		</tr>
    
    
    <tr style="display: none;">
      <td class="LABEL"><div align="right"><font color="red">* </font> Effective From </div></td>
      <td class="CONTROL">
      <div align="left">
      <bean:write name="packservBean" property="streffectiveFrm" />
      
      </div></td>
    </tr>
	<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea name="strremarks" rows="2"><bean:write name="packservBean" property="strremarks"/></textarea></td>
		</tr>
   <tr >
    <td width ="45%" class ="LABEL"><font color="red">* </font> Record Status </td>
    <td width ="45%" class ="CONTROL" >
   <html:radio name="packservBean" property="strisValid" value="1">Active</html:radio>
    <html:radio name="packservBean" property="strisValid" value="2">Inactive</html:radio>
    
   </td>
    </tr>
	<tr class="FOOTER">
			<td colspan="2"><font color="red">* </font> Mandatory Field</td>
		</tr>
  </table>
<div align="center">
	<!-- <img style="cursor:pointer;cursor:hand" title="Save Record" src="../../hisglobal/images/btn-sv.png" onClick="submitDataModify('UPDATE');"/>
	<img style="cursor:pointer;cursor:hand" title="Cancel the Process"  src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
	 -->
	<br><a href="#" class="button" id="" onclick="submitDataModify('UPDATE');"><span class="save">Save</span></a>
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
</div>
<input type="hidden" name="hmode" value=""/>
<input type="hidden" name="strpackageId" value="${packservBean.strpackageId}"/>
<input type="hidden" name="strtariffId" value="${packservBean.strtariffId}"/>
 <input type="hidden" name="chk" value="${packservBean.strChk1}">
 
 <cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>