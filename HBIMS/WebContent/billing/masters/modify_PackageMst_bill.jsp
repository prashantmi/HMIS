<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Package Service Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<style type="text/css">@import url(../../hisglobal/css/calendar-tas.css);</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script> 
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript">
function submitData(mode)
{

   var hisValidator = new HISValidator("packservBean");
   	 hisValidator.addValidation("qty", "req", "Quantity is a Mandatory Field" );
     hisValidator.addValidation("unitId","dontselect=0","Please select a value from Unit Combo");
     hisValidator.addValidation("effectiveFrm", "req", "Effective From Date is a Mandatory Field" ); 
	 hisValidator.addValidation("effectiveFrm","dtgtet="+"${packservBean.strCtDate}","Effective From Date must be Greater Than Or Equal To Current Date");
	hisValidator.addValidation("remarks","maxlen=50","Remarks should not be greater than 50 characters");
  
   var retVal = hisValidator.validate();
   
   if(retVal){	
		if(document.forms[0].qty.value=="0")
		{
			alert("Quantity cannot be zero");
			retVal = false;
		}
	}
	if(retVal){
	 	document.forms[0].hmode.value=mode;			
         document.forms[0].submit();
  }else{		
		return false;
	}	
}
</script>
</head>
<body>

<html:form name="packservBean"
			action="/masters/CNTpackservMst.cnt"
			type="billing.masters.VOpackservMst">
			
<div class="errMsg"><bean:write name="packservBean" property="errmsg"/></div>
<div class="warningMsg"><bean:write name="packservBean" property="warningMsg"/></div>
<div class="normalMsg" id='normalMsg'><bean:write name="packservBean" property="normalMsg"/></div>
<tag:tab tabLabel="Modify Package" align="center" width="TABLEWIDTH"></tag:tab>
		
  <table class="TABLEWIDTH"  border="0" align="center" cellpadding="1px" cellspacing="1px">
    <tr class="HEADER">
      <td class="HEADER" colspan="2">Package Service Master&gt;&gt;Modify </td>
    </tr>
    <tr>
      <td class="LABEL" width="45%"><div align="right">Package Name </div></td>
      <td class="CONTROL" width="45%"><div align="left">
       <bean:write name="packservBean" property="packageName" filter="false"/>
      </div></td>
    </tr>
    
     <tr>
      <td class="LABEL" width="45%"><div align="right">Tariff Name </div></td>
      <td class="CONTROL" width="45%">
      <div align="left">
        <bean:write name="packservBean" property="tariffName" filter="false"/>
      </div></td>
    </tr>
    
    <tr>
      <td class="LABEL"><div align="right"><font color="red">*</font>Quantity</div></td>
      <td class="CONTROL">
      <div align="left">
        <input type="text" name="qty" maxlength="2" value="${packservBean.qty}" onkeypress="return validateData(event,5);">
		<%//System.out.println("siddd=" + bean.getIpAddress()); %>
      </div></td>
    </tr>
    
		<tr>
			<td class="LABEL" width="45%">
			<div align="right"><font color="red">*</font>Unit Name</div>
			</td>
			<td class="CONTROL" width="45%">
		<select name="unitId" >
				<bean:write name="packservBean" property="packunitModuleCombo"
					filter="false" />
			</select>
			</td>
		</tr>
    
    
    <tr>
      <td class="LABEL"><div align="right"><font color="red">* </font> Effective From </div></td>
      <td class="CONTROL">
      <div align="left">
      <dateTag:date name="effectiveFrm" value="${packservBean.effectiveFrm}"/>
      </div></td>
    </tr>
	<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea name="remarks" rows="2"><bean:write name="packservBean" property="remarks"/></textarea></td>
		</tr>
   <tr >
    <td width ="45%" class ="LABEL"><font color="red">* </font> Record Status </td>
    <td width ="45%" class ="CONTROL" >
   <html:radio name="packservBean" property="isValid" value="1">Active</html:radio>
    <html:radio name="packservBean" property="isValid" value="2">Inactive</html:radio>
    
   </td>
    </tr>
	<tr class="FOOTER">
			<td colspan="2"><font color="red">* </font> Mandatory Field</td>
		</tr>
  </table>
<div align="center">
	<img style="cursor:pointer;cursor:hand" title="Save Record" src="../../hisglobal/images/btn-sv.png" onClick="submitData('UPDATE');"/>
	<img style="cursor:pointer;cursor:hand" title="Cancel the Process"  src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
</div>
<input type="hidden" name="hmode" value=""/>
<input type="hidden" name="packageId" value="${packservBean.packageId}"/>
<input type="hidden" name="tariffId" value="${packservBean.tariffId}"/>
 <input type="hidden" name="chk" value="${packservBean.strChk1}">
 <cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>