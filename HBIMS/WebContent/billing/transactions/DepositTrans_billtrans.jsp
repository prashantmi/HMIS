<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/clientDtl.tld" prefix="cDtl"%>
      
      <%
        request.getAttribute("clientverificTransBean");
      %>
<html>
<head>
<title>Deposit Page</title>
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
<script type="text/javascript">


function groupCombo1(mode)
{ 
 // alert("Ajax Function 2-->"+document.forms[0].strInvoiceNo.value);
  		if(mode=="UNITVAL1")
  		 {
	 	   var mode="UNITVAL1";
	 	   if(document.forms[0].strInvoiceNo.value != 0)
	 	    {
		     var url="ClientVerificTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strInvoiceNo.value;
		     ajaxFunction(url,"2");
		    }
		    else
		    {
		       var objVal = document.getElementById("id1");
		       var objVal1 = document.getElementById("id2");
		       objVal.innerHTML = "";
		       objVal1.innerHTML = "";
		    }  
		 }
}

function groupCombo(mode)
{ 
  alert("Ajax Function");
  		if(mode=="UNITVAL")
  		 {
	 	   var mode="UNITVAL";
	 	   if(document.forms[0].strPaymentMode.value!=0 && document.forms[0].strPaymentMode.value!=1)
	 	    {
		     var url="ClientVerificTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strPaymentMode.value;
		     ajaxFunction(url,"1");
		    }
		    else
		    {
		      var objVal = document.getElementById("id1");
		      objVal.innerHTML = "";
		    }  
		 
		 }
}
function getAjaxResponse(res,mode)
{
 		 if(mode=="1")
 		 {
		     var objVal = document.getElementById("id1");
		     objVal.innerHTML = res; 
         }
         if(mode=="2")
 		 {
 		     alert("In Mode");
 		     var objVal = document.getElementById("id2");
		     objVal.innerHTML = res; 
 		   //document.getElementById("id2").style.display="block";
         }
}         
</script>
</head>
<html:form action="billing/transactions/ClientVerificTransCNT.cnt" method="post">
   
        <div id="errMsg" class="errMsg"><bean:write name="clientverificTransBean" property="strErrMsg" /></div>
	    <div id="warningMsg" class="warningMsg"><bean:write name="clientverificTransBean" property="strWarning"/></div>
        <div id="normalMsg" class="normalMsg"><bean:write  name="clientverificTransBean" property="strMsg"/></div>
 
    
    
    
 <tag:tab tabLabel="Client Verification" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client Verification &gt;&gt;Add</td>
  </tr>
  <tr>
      <td>
          <cDtl:clientDtl chk="${clientverificTransBean.strChk}"></cDtl:clientDtl>
     </td>
  </tr>
  
  </table>
   
  <table class= "TABLEWIDTH"  align="center">
	 <tr> 
    <td colspan="4" class="TITLE">Deposit Details</td>
   </tr>
   <tr>
     <td width="25%" class="LABEL">Invoice No:</td>
     <td width="25%" class="CONTROL"><select name="strInvoiceNo" class='comboNormal' onChange="groupCombo1('UNITVAL1');"><bean:write name="clientverificTransBean" property="strInvoiceComboDtl" filter="false"/></select></td>
     <td width="25%" class="LABEL"></td>
     <td width="25%" class="CONTROL"></td>   
        
   </tr>
  </table>
   <div id="id2"></div>

      <div id="id1"></div>
  <table class="TABLEWIDTH" border="0" align="center" >
   
    <tr>
  <td colspan="2" width="50%" class="LABEL">Remarks</td>
    <td colspan="2" width="50%" class="CONTROL"> <textarea rows="2" cols="20" name="strRemarks"></textarea></td> 
  </tr>
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img name="save"   src="../../hisglobal/images/btn-sv.png" onclick=" return validate1();"></td>
			<td align="left"><img name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"></td>
		</tr>
	</table>

	<input type="hidden" name="hmode">
</html:form>
<tag:autoIndex></tag:autoIndex> 
</html>