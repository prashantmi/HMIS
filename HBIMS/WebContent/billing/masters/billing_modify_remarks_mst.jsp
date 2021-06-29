<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset=utf-8>
<title>Global Remarks Master Modify Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script type="text/javascript">
	function validate1(){	
	
		var hisValidator = new HISValidator("remarkBean");
		hisValidator.addValidation("strRemarksDesc", "req","Remarks Description is a Mandatory Field");
		hisValidator.addValidation("strRemarksDesc", "maxlen=50", "Remarks Description should have less than or equal to 50 Characters" );
		hisValidator.addValidation("strRemarksFor", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
		var retVal = hisValidator.validate(); 
			
		if(retVal){
				document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
		}else{
		
				return false;
		}
		
	}
	
	function cancelPage()
	{
	
	document.forms[0].hmode.value='LIST';
	document.forms[0].submit();
	}

</script>


</head>
<body onLoad="document.forms[0].strRemarksDesc.focus();">

<html:form action="/masters/CNTRemarksMst.cnt"
	type="billing.masters.controller.fb.GlobalRemarksMstFB" name="remarkBean">
	

	<div class="errMsg"><bean:write name="remarkBean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="remarkBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="remarkBean" property="strWarning"/></div>
	
	
	
	<center>
	<tag:tab tabLabel="Modify Global Remarks" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	</center>
<table class="TABLEWIDTH" align="center">  <tr class="HEADER"> 

    <td colspan="2" >Global Remarks Master&gt;&gt; Modify</td>
  </tr>
  <tr> 
    <td width="50%" class="LABEL"> Remarks For</td>
    <td width="50%" class="CONTROL"><bean:write name="remarkBean" property="strRemarksType"/> </td>
  </tr>
  <tr > 
    <td  class="LABEL"> <font color="red">*</font>Remarks Description</td>
    <td class="CONTROL">  
        <textarea name="strRemarksDesc" cols="20" rows="2"><bean:write name="remarkBean" property="strRemarksDesc"/> </textarea>
      </td>
  </tr>
  <tr> 
    <td class="LABEL"><font color="red">*</font>Effective From</td>
    <td class="CONTROL"><font color="black"><bean:write name="remarkBean" property="strEffectiveFrom"/></font></td>
    
  </tr>
  <tr> 
    <td class="LABEL">Remarks(if any)</td>
    <td class="CONTROL"> 
        <textarea name="strRemarksFor" cols="20" rows="2" id="remarks"><bean:write name="remarkBean" property="strRemarksFor"/></textarea>
    </td>
  </tr>
  <tr> 
    <td class="LABEL"><font color="red">*</font>Record Status</td>
    <td  class="CONTROL" > 
    
    <html:radio name="remarkBean" property="strValid" value="1" />Active
    <html:radio name="remarkBean" property="strValid" value="2" />InActive
     </td>
  </tr>
  <tr class="FOOTER"> 
    <td colspan="2" align="right"><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>
 <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<!-- <td align="right"><img style="cursor:pointer;cursor:hand" title="To Save Record" name="save" id="save"   src="../../hisglobal/images/btn-sv.png" onclick="return validate1();"></td>
			<td align="left"><img style="cursor:pointer;cursor:hand" title="To Cancel and return to List Page" name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancelPage();"></td>
			 -->
			<br>
				<td align="right"><a href="#" class="button" id="" onclick='return validate1();'><span class="save">Save</span></a></td>
				<td align="left"><a href="#" class="button" onclick="cancelPage();"><span class="cancel">Cancel</span></a></td>
		</tr>
	</table>

     <input type="hidden" name="chk" value="${remarkBean.chk[0]}" />
	 <input type="hidden" name="hmode">
	 
	 <cmbPers:cmbPers></cmbPers:cmbPers>
	 
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>