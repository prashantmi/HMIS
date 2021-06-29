<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Global Room Type  Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript">
function validate1()
{
      		var hisValidator = new HISValidator("globalRoomtypeBean");
      		hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
            hisValidator.addValidation("strGlobalRoomType", "req", "Global Room Type is a Mandatory Field" );
            //hisValidator.addValidation("strEffectiveDate", "date","Effective Date is a mandatory field");
			//hisValidator.addValidation("strEffectiveDate", "date","Effective Date should be a valid Date");
            //hisValidator.addValidation("strEffectiveDate", "dtgtet="+"${globalRoomtypeBean.strCtDate}" , "Effective Date should be Greater than or Equal to Current Date");         
            var retVal = hisValidator.validate(); 
          if(retVal)
          {
             document.forms[0].hmode.value = "UPDATE";
             document.forms[0].submit();
           }
           else
           {
             return false;
           }
    }
</script>
</head>
<body onLoad="document.forms[0].strGlobalRoomType.focus()">
<html:form action="/masters/GlobalRoomTypeMstACTION">
   <div class="normalMsg" id="normalMsg"><bean:write name="globalRoomtypeBean" property="strNormalMsg"/></div>  
     <div  class="errMsg"><bean:write name="globalRoomtypeBean" property="strerrorMsg"/></div>
<tag:tab tabLabel="Modify Global Room Type" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table CLASS ="TABLEWIDTH" align ="center" cellspacing="1px">
 <tr class ="HEADER">
  <td colspan="2">Global Room Type Master&gt;&gt; Modify</td>
 </tr>             
 <tr >
    <td class="LABEL"><font color="red">*</font>Room Type</td>
    <td width="50%" class ="CONTROL">
    <input type="text" onkeyup="lTrim(this);" onblur="Trim(this);" name="strGlobalRoomType" 
    value ="${globalRoomtypeBean.strGlobalRoomType}" onkeypress="return validateData(event,9);" maxlength="40"> </td>
 </tr>
 <!--<tr >
    <td class ="LABEL" width ="50%"><font color="red">*</font>Effective Date</td>
    <td class ="CONTROL"><date:date name="strEffectiveDate" value ="${globalRoomtypeBean.strEffectiveDate}"></date:date></td>
 </tr> -->
 <tr style="display: none;">
      <td class="LABEL"><div align="right">Remarks</div></td>
      <td class="LABEL">
	  <div align="left">
  <textarea  name="strRemarks" onkeyup="lTrim(this);" onblur="Trim(this);" cols="25" rows="2" ><bean:write name="globalRoomtypeBean" property="strRemarks"/></textarea>
      </div></td>
 </tr>
 <tr >
    <td width ="45%" class ="LABEL">Record Status </td>
    <td width ="45%" class ="CONTROL" >
    <html:radio name="globalRoomtypeBean" property='strIsValid' value='1'></html:radio>
	   Active <span class="LABEL">
    <html:radio name="globalRoomtypeBean" property='strIsValid'  value='2'></html:radio>
	   InActive </span></td>
    </tr>
    <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font>Mandatory Fields</td>
 </tr>
</table>																														
	 <div align="center">
<table CLASS ="TABLEWIDTH" cellspacing="1px">
	<tr> 
	      <td align="center">
	      <!--  <img name="save" style="cursor:pointer" src="../../hisglobal/images/btn-sv.png"   onClick="return validate1(); "/>
    	   <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');"/>
    	    -->
    	   <br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	    </td>
	</tr>
</table>
	    <input type="hidden" name="chk" value="${globalRoomtypeBean.chk[0]}">
	   	<input type="hidden" name="hmode">
   </div>
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>