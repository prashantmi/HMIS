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
<title>Invoice Page</title>
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

function validate1(){
	
	alert("strCrNo :"+document.forms[0].strCrNo.value);
    alert("Payment Type:"+document.clientverificTransBean.strPaymentType.value);
    var retVal = false;
	var hisValidator = new HISValidator("clientverificTransBean");
		
			
	hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
	
	retVal = hisValidator.validate(); 
		
		if(retVal && (document.clientverificTransBean.strPaymentType.value =="PRE-PAID"))
		 {
		        
		        document.forms[0].hmode.value = "INSERTPOST";
				alert("Mode In->"+document.forms[0].hmode.value);
				document.forms[0].submit();
		}
		else
		{
		        document.forms[0].hmode.value = "INSERTPOST";
		        alert("Mode->"+document.forms[0].hmode.value);
				document.forms[0].submit();
		}
	}
	function changeToPackage()
    {
    alert("Inside Function Payment Type->"+document.clientverificTransBean.strPaymentType.value);
    
	if(document.clientverificTransBean.strPaymentType.value=="PRE-PAID")
	{
		document.getElementById("id1").style.display="block";				
	}	
	else
	{
	    document.getElementById("id1").style.display="none";
    }
}
 
 </script>
 
 
 
  
</head>

<body onload="changeToPackage()";>
<html:form action="billing/transactions/ClientVerificTransCNT.cnt" method="post">
     <div id="errMsg"     class="errMsg"><bean:write name="clientverificTransBean" property="strMsgType"/></div> 
     <div id="warningMsg" class="warningMsg"><bean:write name="clientverificTransBean" property="strWarning"/></div>
     <div id="normalMsg"  class="normalMsg"><bean:write  name="clientverificTransBean" property="strMsg"/></div>
 
 
 
 <tag:tab tabLabel="Client Verification" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>


 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Client&gt;&gt;Invoice</td>
  </tr>
  <tr>
      <td>
      <cDtl:clientDtl chk="${clientverificTransBean.strChk}"></cDtl:clientDtl>
     </td>
  </tr>
  
  </table>
   

  <div id = "id1" style="display:none;">
  <table class="TABLEWIDTH" align="center">
   <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>CR No:</td>
     <td width="50%" class="CONTROL">
          <div align="left" id ="strCrNo">
               <select name="strCrNo" class='comboNormal'>
                  <bean:write name="clientverificTransBean" property="strCrComboDtl" filter="false"/>
               </select>
      </div></td> 
	   </tr>
   
  
   </table>
   </div>

  <table class="TABLEWIDTH" border="0" align="center" >
  
  <tr>
    <td colspan="2" width="50%" class="LABEL">Remarks</td>
    <td colspan="2" width="50%" class="CONTROL"><textarea rows="2" cols="20" name="strRemarks"></textarea></td> 
  </tr>
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img name="save"   src="../../hisglobal/images/view_tab.gif" onclick=" return validate1();"></td>
			<td align="left"><img name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"></td>
		</tr>
	</table>

	<input type="hidden" name="hmode">
	
	

</html:form>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>