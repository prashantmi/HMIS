<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset="utf-8">
<title>Process Letter Type Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Process Letter Type Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language ="javaScript">

function validate1(){   
     
            var hisValidator = new HISValidator("ProcessLetterTypeMstBean");
            hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            hisValidator.addValidation("strEffectiveFrom", "date","Effective Date is a mandatory field");
		    // hisValidator.addValidation("strEffectiveFrom", "dtgtet=${ProcessLetterTypeMstBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");
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
<body >
<html:form name="ProcessLetterTypeMstBean" action="/masters/ProcessLetterTypeMstCNT" type="mms.masters.controller.fb.ProcessLetterTypeMstFB">
 	
 	<div class="errMsg"><bean:write name="ProcessLetterTypeMstBean" property="strErrorMsg"/></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="ProcessLetterTypeMstBean" property="strMsg"/></div>
	<div class="warningMsg"><bean:write name="ProcessLetterTypeMstBean" property="strWarning"/></div>
	

	<tag:tab tabLabel="Process Letter Type Master" selectedTab="FIRST" align="center" width="TABLEWIDTH">
               </tag:tab>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
         cellspacing="1px">   
	<tr class="HEADER">
			<td colspan="8">Process Letter Type Master&gt;&gt; Add</td>
	</tr>

	
		<tr>
			<td colspan="4"  width="50%" class="LABEL">Process Name</td>
			<td colspan="4"  width="50%" class="CONTROL">
			<bean:write name="ProcessLetterTypeMstBean" property="strProcessName" filter="false"/>
		</tr>
		
		<tr>
			<td colspan="4"  width="50%" class="LABEL">Letter Type Name</td>
			<td colspan="4"  width="50%" class="CONTROL">
			<bean:write name="ProcessLetterTypeMstBean" property="strLetterTypeName" filter="false"/>
		</tr>
		
		<tr>
			<td colspan="4" width="50%" class="LABEL">
			Remarks
			</td>
			<td colspan="4" width="50%" class="CONTROL">
			  <div align="left">
        		<textarea name="strRemarks" rows="2"><bean:write name="ProcessLetterTypeMstBean" property="strRemarks"/></textarea>
              </div>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="50%" class="LABEL">
			<font color="red">*</font>Effective From
			</td>
			<td colspan="4" class ="CONTROL">
			<date:date name="strEffectiveFrom" value ="${ProcessLetterTypeMstBean.strEffectiveFrom}"></date:date></td>
		</tr>
		<tr >
           <td colspan="4" width ="45%" class ="LABEL">Record Status</td>
           <td colspan="4" width ="45%" class ="CONTROL" >
           <html:radio name="ProcessLetterTypeMstBean" property="strIsValid" value="1">Active</html:radio>
           <html:radio name="ProcessLetterTypeMstBean" property="strIsValid" value="2">Inactive</html:radio></td>
    	</tr>
		
		<tr class="FOOTER">
			 <td colspan="8"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
         cellspacing="1px">
		<tr>

			<td align="center">
			<img style="cursor: pointer; " src="../../hisglobal/images/btn-sv.png" title="Save Record" onClick=" return validate1();"/> 
			
			<img style="cursor: pointer; " src="../../hisglobal/images/btn-ccl.png" title="Cancel Process" onClick="cancel('LIST');"/>
			
			</td>
		</tr>
	</table>
	<input type="hidden" name="strProcessId" value="${ProcessLetterTypeMstBean.combo}"/>
	<input type="hidden" name="strProcessName" value="${ProcessLetterTypeMstBean.strProcessName}"/>
	<input type="hidden" name="chk" value="${ProcessLetterTypeMstBean.strChk }">
<input type="hidden" name="hmode"/>
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>