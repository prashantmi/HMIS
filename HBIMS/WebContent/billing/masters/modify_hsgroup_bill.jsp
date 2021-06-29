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
<title>Hospital Service Group Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript">
function validate1(){   
    
      var hisValidator = new HISValidator("hsgroupBean");
            //alert("Objec :"+hisValidator);  
          
		   
            hisValidator.addValidation("effectiveFrom", "date","Effective From Date is a mandatory field");
			hisValidator.addValidation("effectiveFrom", "date","Effective From Date should be a valid Date");
            hisValidator.addValidation("remark", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );     
            var retVal = hisValidator.validate(); 
     //    alert(retVal);
          if(retVal){
                  document.forms[0].hmode.value = "UPDATE";
         //alert("inside if");
                        document.forms[0].submit();
            }else{

           return false;

          }
    }
</script>
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
<body onload="document.forms[0].effectiveFrom.focus();">
<html:form name="hsgroupBean" action="/masters/CNTHospServiceGroupMst" type="billing.masters.controller.fb.VOHospServiceGroupMst" >
   <div class="errMsg"><bean:write name="hsgroupBean" property="strErrorMsg"/></div>
	<div class="warningMsg"><bean:write name="hsgroupBean" property="warnings"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="hsgroupBean" property="msg"/></div>
<tag:tab tabLabel=" Modify Hospital Service" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
   <tr class ="HEADER">
   <td colspan ="2" >Hospital Service Group Master&gt;&gt; Modify</td>
 </tr>                 
   <tr >
    <td class="LABEL">Service Name</td>
    <td width="50%" class ="CONTROL"><input type="text" name="hserviceName"   value ="${hsgroupBean.hserviceName}" readonly></td>
  </tr>
  
  <tr >
    <td class="LABEL">Group Name</td>
    <td width="50%" class ="CONTROL"><input type="text" name="rgroupName" value ="${hsgroupBean.rgroupName[0]}" readonly> </td>
  </tr>
   <tr >
    <td class ="LABEL" width ="50%"> <span class="style1">*</span> Effective From</td>
    <td class ="CONTROL"><date:date name="effectiveFrom" value ="${hsgroupBean.effectiveFrom}"></date:date></td>
  </tr>
  <tr >
    <td width ="50%" class ="LABEL"valign="top">Remarks if Any </td>
    <td width ="50%" class ="CONTROL"><textarea  name="remark" cols="25" rows="2"><bean:write name="hsgroupBean" property="remark"/></textarea></td>
  </tr>
   
    <tr >
    <td width ="45%" class ="LABEL"><span class="style2">*</span> Record Status </td>
    <td width ="45%" class ="CONTROL" >
    <html:radio name="hsgroupBean" property="isValid" value="1" />Active<span class="LABEL">
<html:radio name="hsgroupBean" property="isValid" value="2" />InActive
 </span></td>
    </tr>
    
   <tr class ="FOOTER" >
 <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>
	
	    <div align="center">
	<!--     
	<img style="cursor:pointer;cursor:hand" title="Save Record" src="../../hisglobal/images/btn-sv.png" onClick="return validate1();"/>
	<img style="cursor:pointer;cursor:hand" title="Cancel the Process"  src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" >
	 -->
	<br><td align="right"><a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a></td>
				<td align="left"><a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a></td>
	
	</div>
	    
	   	<input type="hidden" name="hmode">
	 	<input type="hidden" name="chk" value="${hsgroupBean.chk[0]}">
	 	<cmbPers:cmbPers/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	
			
			