<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<html>
<head><meta charset=utf-8>
<title>Discount Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript"><!--

function goFunc()
{
	
	var hisValidator = new HISValidator("discountApprovalTransBean");
	var strError = "Syntax Length Kum Hai Yaar";  
	alert("Value->"+document.forms[0].strCrNo.value);
	alert("Name->"+document.forms[0].strCrNo.name);
	var str = document.forms[0].strCrNo.value;
	var name = document.forms[0].strCrNo.name;
	alert("Length->"+str.length);
	var length = str.length;
	var cmdvalue = 10;
	alert("Defined Length->"+cmdvalue);
	
	if(eval(length) <  eval(cmdvalue))
			{
				if(!strError || strError.length ==0)
					strError = name + " : " + cmdvalue + " characters minimum  ";
				
				alert(strError + "\n[Current length = " + length+ " ]"); 
				return false;
			}
	
	
	hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
//	hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	
	hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	
	var retVal = hisValidator.validate(); 
	
		if(retVal){
				document.forms[0].hmode.value="GO";
			
				document.forms[0].submit();
				
		}else{
		
		return false;
		}
		 
		
	}
	
function groupCheck(chkObj)
{ 
 document.getElementById("save").disabled=false;

 alert("value : "+chkObj.value);
 document.forms[0].strValue1.value=chkObj.value;

 /*document.forms[0].hmode.value="BId";
  
 
  		if(mode=="BId")
  		 {
	 	   var mode="BId";
	 	   
		     var url="DiscountApprovalTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strValue1.value;
		     ajaxFunction(url,"1");
		  
		 document.forms[0].submit();
		 }*/
		 alert(document.forms[0].strValue1.value);
		 document.getElementById("servid").style.display="block";
		
}

function groupCombo(){

  if(document.forms[0].dr.value == 3)
  {
  document.forms[0].drt.value = "";
    document.forms[0].drt.disabled=false;
    
  }
  else
  {
     document.forms[0].drt.value= document.forms[0].dr[document.forms[0].dr.selectedIndex].text;
     document.forms[0].drt.disabled=true;
     }
}

function butdis()
{
 document.getElementById("save").disabled=true;
}

 --></script>
 
 
 
  
</head>
<body onLoad="butdis()">
<html:form action="transactions/DiscountApprovalTransCNT.cnt" method="post">
      
    <div id ="errMsg" class="errMsg"><bean:write name="discountApprovalTransBean" property="strErrMsg"/></div> 
    <div id ="warningMsg" class="warningMsg"><bean:write name="discountApprovalTransBean" property="strWarning"/></div>
    <div id ="normalMsg" class="normalMsg"><bean:write  name="discountApprovalTransBean" property="strMsg"/></div>
      
 
 
 <tag:tab tabLabel="Discount Approval" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>


 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Discount&gt;&gt;Approval</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
    <td colspan="3" class="CONTROL">
   <input type="text" class="txtFldMax" name="strCrNo" value="${discountApprovalTransBean.strCrNo}" maxlength="13" onkeypress="return validateData(event,5);"> <input type="button" name="go" value="Go" onClick="return goFunc();">
    </td>   
  </tr>
  <tr>
     
  </tr>
  </table>
    		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img id="save" name="save"   src="../../hisglobal/images/btn-sv.png" onclick=" return goFunc();"></td>
			<td align="left"><img name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"></td>
		</tr>
	</table>
	
	

	<input type="hidden" name="hmode">
	<input type="hidden" name="strCRNoSatus" value="${discountApprovalTransBean.strCRNoSatus}">
	


</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>