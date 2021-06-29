<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
		
<html>
<head>
<meta charset="utf-8">
<title>Sub Group Type Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript">

function validate1(){   
     
             var hisValidator = new HISValidator("subgroupBean");
       
       hisValidator.addValidation("strEffectiveFrom", "req", "Effective from is a Mandatory Field" );  
       		hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            hisValidator.addValidation("strSubGroupName", "req","SubGroup Name is a mandatory field");
          hisValidator.addValidation("strEffectiveFrom", "req", "Effective from is a Mandatory Field" );   
		   //  hisValidator.addValidation("strEffectiveFrom", "dtgtet=${subgroupBean.strCtDate}" , "Effective From should be Greater than or Equal to Current Date");
            var retVal = hisValidator.validate(); 

          if(retVal){
                 	   document.forms[0].hmode.value = "UPDATE";
                       document.forms[0].submit();
          }else{
             return false;
          }
}

</script>
</head>
<body>

<!-- <body onLoad="document.forms[0].strStoreTypeName.focus()">-->
<html:form name="subgroupBean" action="/masters/StoreSubGroupMstCNT" type="mms.masters.controller.fb.StoreSubGroupMstFB">
 	<CENTER>
 	<div class="errMsg"><bean:write name="subgroupBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="subgroupBean" property="strWarning"/></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="subgroupBean" property="strMsg"/></div>

	<tag:tab tabLabel="SubGroup Master"
				selectedTab="FIRST" align="center" width="TABLEWIDTH">
			</tag:tab>
			</CENTER>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="2">Sub Group Type Master&gt;&gt;Modify</td>
  </tr>
  <tr> 
    <td class="LABEL">Drug Category Name</td>
    <td width="50%"  class="CONTROL"><bean:write name="subgroupBean" property="strItemCatName"/></td>   
  </tr>
  
  <tr> 
    <td class="LABEL">Group Name</td>
    <td width="50%"  class="CONTROL"><bean:write name="subgroupBean" property="strGroupName"/></td>   
  </tr>
     
  <tr >
    <td class="LABEL"><font color="red">*</font>Sub Group Name</td>
    <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' maxlength="100" name="strSubGroupName"  value ="${subgroupBean.strSubGroupName}" maxlength="100" onkeypress="return validateData(event,18);"> </td>
  </tr>
  
   <tr >
    <td class ="LABEL" width ="50%"><font color="red">*</font>Effective From</td>
    <td class ="CONTROL"><bean:write name="subgroupBean" property="strEffectiveFrom"/></td>
  </tr>
    
    <tr>
      <td class="LABEL"><div align="right"> Remarks </div></td>
      <td class="CONTROL">
	  <div align="left">
        <textarea name="strRemarks" rows="2"><bean:write name="subgroupBean" property="strRemarks"/></textarea>
      </div></td>
    </tr>
      
    
    
        <tr >
    <td width ="50%" class ="LABEL">Record Status </td>
    <td width ="50%" class ="CONTROL" >
   <html:radio name="subgroupBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="subgroupBean" property="strIsValid" value="2">Inactive</html:radio>
    
   </td>
    </tr>
    
    
   
   <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>
	 
	   <table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
		<br>
			<td align="right">
			<!-- <img style="cursor: pointer; " title="Save Record"
				src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" />
			 -->
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>

			</td>
			<td align="left">
			<!-- <img style="cursor: pointer; " title="Cancel Process" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" > -->
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	      <input type="hidden" name="chk" value="${subgroupBean.strChk1}">
	      <input type="hidden" name="comboValue" value="${subgroupBean.strItemCatName}">
	      <input type="hidden" name="storeTypeId" value="${subgroupBean.strItemCatId }">
	      <input type="hidden" name="hmode" />
	      <input type="hidden" name="strEffectiveFrom" value ="${subgroupBean.strEffectiveFrom}">
<!--	    <input type="hidden" name="combo" />-->
	    
	<cmbPers:cmbPers/>    
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	