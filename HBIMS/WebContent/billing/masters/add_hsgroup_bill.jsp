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
<title>Hospital Service Group Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>

<script language ="javaScript">
function validate1()
{
	var hisValidator = new HISValidator("hsgroupBean");
    hisValidator.addValidation("hserviceName","dontselect=0","Please select a value from Service Combo");
    hisValidator.addValidation("rgroupName","dontselect=0","Group name is a mandatory field");
    hisValidator.addValidation("effectiveFrom", "date","Effective Date is a mandatory field");
	hisValidator.addValidation("effectiveFrom", "dtgtet=${hsgroupBean.strCtDate}" , "Effective From should be Greater than or Equal to Current Date");        
    hisValidator.addValidation("remark", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
    var retVal = hisValidator.validate(); 

    if(retVal)
    {
    	selectListRecords("rgroupName");
   		document.forms[0].hmode.value = "SAVE";
        document.forms[0].submit();
    }
    else
    {
        return false;
    }
}

function List()
{
	if(document.forms[0].hserviceName.value != 0)
	{
		var mode="SERVICE";
		var url="CNTHospServiceGroupMst.cnt?hmode=SERVICE&hserviceName="+document.forms[0].hserviceName.value;
		ajaxFunction(url,"1");
	}
	else
	{
		var objVal = document.getElementById("lgroupNameId");
		objVal.innerHTML = "<select name ='lgroupName' size='8' multiple style='width: 120px;'></select>";
	}
}
function getAjaxResponse(res,mode)
{
	if(mode==1)
	{	
		var objVal = document.getElementById("lgroupNameId");
		objVal.innerHTML = "<select name ='lgroupName' size='8' multiple style='width: 120px;'>" + res + "</select>";
    }
}
function clearFields()
{
	var objVal = document.getElementById("lgroupNameId");
	objVal.innerHTML = "<select name ='lgroupName' size='8' multiple style='width: 120px;'>" + "" + "</select>";
	
	var objVal = document.getElementById("rgroupNameId");
	objVal.innerHTML = "<select name ='rgroupName' size='8' multiple style='width: 120px;'>" + "" + "</select>";
	
	document.forms[0].reset();
	document.forms[0].hserviceName.focus();
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
<body >
<html:form name="hsgroupBean" action="/masters/CNTHospServiceGroupMst" type="billing.masters.controller.fb.VOHospServiceGroupMst">
  
   <div class="errMsg"><bean:write name="hsgroupBean" property="strErrorMsg"/></div>
	<div class="warningMsg"><bean:write name="hsgroupBean" property="warnings"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="hsgroupBean" property="msg"/></div>
	
<tag:tab tabLabel=" Add Hospital Service " selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
  <tr class ="HEADER">
   <td colspan ="2" >Hospital Service Group Master&gt;&gt; Add</td>
  </tr>                
  <tr>
    <td class="LABEL" colspan="2"><span class="style1">*</span>Service Name 
    <select name ="hserviceName" class="comboNormal" onChange ="List();">
	<bean:write name="hsgroupBean" property="cmbval" filter="false"/>
	</select></td>
  </tr>
  <tr>
  
 <td colspan="2" class="CONTROL">
 <div align="center">
  <table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px">
   <tr>
   <td  COLSPAN ="4" class="LABEL">
     <DIV ALIGN ="CENTER">Group Names</DIV>
   </td>
   
   </tr>
   <tr > <td  WIDTH ="40%" class ="CONTROL" rowspan="4" colspan="1"><div id ="lgroupNameId" align ="right">
     <select name ="lgroupName" size=8 multiple style="width: 120px;">
     </select>
   </div> </td>
  <td width="20%"  class="CONTROL" colspan ="1" > <div align="center">
  <p><img src="../../hisglobal/images/forward3.gif" width="35" height="31" align="top" onClick='shiftToRight("lgroupName","rgroupName",1);'></p>
  </div></td>
   <td rowspan="4" colspan ="1" class ="CONTROL" WIDTH ="40%"><div id ="rgroupNameId" align="left">
     <select name="rgroupName" size ="8" multiple style="width: 120px;">
   </select> 
     </div>
    </td>
  </tr>
 <tr>
  <td width="20%" colspan ="1" class="CONTROL" > <div align="center">
  <p><img src="../../hisglobal/images/forwardward.gif" width="35" height="31" align="middle" onClick='shiftAllToRight("lgroupName","rgroupName",1);'></p>
  </div></td>
  </tr> 
  <tr>
  <td width="20%" colspan ="1" class="CONTROL" > <div align="center">
  <p><img src="../../hisglobal/images/backward.gif" width="35" height="31" onClick='shiftAllToLeft("lgroupName","rgroupName",1);'></p>
  </div></td>
  </tr> 
   <tr>
  <td width="20%" colspan ="1" class="CONTROL" > <div align="center">
  <p><img src="../../hisglobal/images/back3.gif" width="35" height="31" onClick='shiftToLeft("lgroupName","rgroupName",1);'></p>
  </div></td>
  </tr> 
 </table>
  </div>
  </td>
   </tr>
   
   <tr >
    <td class ="LABEL" width ="50%"><span class="style1">*</span> Effective From</td>
    <td class ="CONTROL"><date:date name="effectiveFrom" value ="${hsgroupBean.strCtDate}"> </date:date></td>
  </tr>
  <tr > 
    <td width ="50%" class ="LABEL"valign="top">Remarks if Any </td>
    <td width ="50%" class ="CONTROL"><textarea  name="remark" cols="25" rows="2"></textarea></td>
  </tr>
 
   <tr class ="FOOTER" >
  <td colspan ="2" ><span class="style1">*</span>Mandatory Field</td>
  </tr>
</table>
	 <div align="center">
	    <table CLASS ="TABLEWIDTH">
	    <tr> <td >
<div align="center">
<!-- 
<img style="cursor:pointer;cursor:hand" title="Save Record" src="../../hisglobal/images/btn-sv.png" onClick="return validate1('SAVE');" />
<img style="cursor:pointer;cursor:hand" title="Reset Content" src="../../hisglobal/images/btn-clr.png" onClick="clearFields();" />
<img style="cursor:pointer;cursor:hand" title="Cancel Process" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />
 -->
<br>
			<a href="#" class="button" id="" onclick=" return validate1('SAVE');"><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearFields();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
</div></td>
      </tr>
	    </table>
	 	<input type="hidden" name="hmode">
	 	<input type="hidden" name="strHospitalCode" value ="${hsgroupBean.strHospitalCode}" />
	 	<cmbPers:cmbPers/>
</div>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	