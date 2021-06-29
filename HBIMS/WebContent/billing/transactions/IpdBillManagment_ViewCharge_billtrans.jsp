<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>IPD Bill Management</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script>
<script type="text/javascript">
function myCancel()
{
document.forms[0].hmode.value="LIST"; 
		document.forms[0].submit();
}
</script>
  
</head>
<body onload=eEnable1();getGroupCombo();desableCrNo();>
<html:form action="transactions/IpdBillManagementTransCNT.cnt" method="post">
     
     <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>
 
 <tag:tab tabLabel="IPD View Charges" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="HEADER">  
    <td colspan="4">IPD&gt;&gt;View Charges</td>
  </tr>
  <tr class="HEADER">  
    <td colspan="4">Criteria</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Hospital Service:</td>
    <td width="25%" class="CONTROL">
       <select name="strHospitalServiceCombo" class="comboNormal" onChange="eEnable();getGroupCombo();">
           <bean:write name="ipdBillManagementTransBean" property="strHospitalServiceCombo" filter="false"/>
       </select>
    </td> 
   <td width="25%" class="LABEL"><font color="red">*</font>Group Name:</td>
    <td width="25%" class="CONTROL">   
    <div align="left" id="GrpNameId">
         <select name="strGroupNameCombo" class='comboNormal' id="strGroupNameCombo"><option value="0">Select Value</option></select>
      </div>
    
     </td> 
    
   </tr>
  <tr> 
   <td width="25%" class="LABEL"><font color="red">*</font>Tariff Name:</td>
    <td width="25%" class="CONTROL">
    
      <div align="left" id="TariffId">
         <select name="strTariffNameCombo" class='comboNormal' id="strTariffNameCombo"><option value="0">Select Value</option></select>
      </div>
      </td>
    <td width="25%" class="LABEL"><font color="red">*</font>Patient Category:</td>
    <td width="25%" class="CONTROL"><select name="strPatientCategoryCombo" class="comboNormal" onchange="resetViewCharge();">
    <bean:write name="ipdBillManagementTransBean" property="strPatientCategoryCombo" filter="false"/>
    </select></td> 
   </tr>
   
   <tr> 
    <td width="25%" class="LABEL">Ward Name:</td>
    <td width="25%" class="CONTROL"><select name="strWardnameCombo" onchange="resetViewCharge();" class="comboNormal" disabled="disabled">
    <bean:write name="ipdBillManagementTransBean" property="strWardnameCombo" filter="false"/>
    </select></td>
    <td width="25%" class="LABEL"><input type='checkbox' name='strChk_value' checked  onClick="ftnCheck()"></td>
    <td width="25%" class="CONTROL">Current Rate:</td>   
   </tr>
  </table>
   <div id="id1" style="display:none">	
  	<table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  	<tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Date:</td>
    <td width="50%" class="CONTROL">
    <dateTag:date name="strEffectiveFrmDate" id="effFrmDateId" value="${ipdBillManagementTransBean.strEffectiveFrmDate}"/></td>
    </table>
  	</div>
   
  <div id="viewCharges" style="display:block">	 
  <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
    <tr>
         <bean:write name="ipdBillManagementTransBean" property="strPkgBreakUp" filter="false"/>
    </tr>
  </table>
  </div>
   
  <table class="TABLEWIDTH" border="0" align="center" border="0" cellspacing ="1px">
    <tr class="HEADER">  
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
    </tr>
  </table>	
  <table border="0" class="TABLEWIDTH" align="center">
		<tr>
        	<td align="center">
        	<!--<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-generate.png" onclick="getView('VIEWGO');"/>
        	<img style="cursor:pointer;cursor:hand" name="cancel1"   src="../../hisglobal/images/btn-ccl.png" onClick="myCancel();">
        	<img src="../../hisglobal/images/btn-clr.png" style="cursor:pointer;cursor:hand" onclick="getInvisble();"/>-->
        					<br><a href="#" class="button" id="" onclick='getView('VIEWGO');'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="getInvisble();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="myCancel();"><span class="cancel">Cancel</span></a>
        	
        	</td>
    	</tr>
	</table>

	<input type="hidden" name="hmode">
	
		<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
	
	
   <cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>