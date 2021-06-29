<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/clientDtl.tld" prefix="cDtl"%>
      
<html>
<head>
<title>Renew/Extend Page</title>
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
	
	

	var retVal = false;
	var hisValidator = new HISValidator("clientverificTransBean");
		
	hisValidator.addValidation("strDepositAmt", "req", "Advance Amount is a Mandatory Field");
    hisValidator.addValidation("strDepositAmt", "amount=9,2", "Enter a Valid Deposit Amount in one of the Format [0000000.00] or [000000]" );
   
    hisValidator.addValidation("strReorderLevel", "req", "Re-Order Level is a Mandatory Field" );
    hisValidator.addValidation("strReorderLevel", "amount=9,2", "Enter a Valid Re-Order Level in one of the Format [00000000.00] or [000000]" );
    
    hisValidator.addValidation("strBgAmount", "req", "BG Amt. is a Mandatory Field" );
    hisValidator.addValidation("strBgAmount", "amount=9,2", "Enter a Valid BG Amt. in one of the Format [00000000.00] or [000000]" );

    hisValidator.addValidation("strOPDDiscount", "req", "OPD Discount is a Mandatory Field" );
    hisValidator.addValidation("strOPDDiscount", "amount=9,2", "Enter a Valid OPD Discount  in one of the Format [00000000.00] or [000000]" );

    hisValidator.addValidation("strIPDDiscount", "req", "IPD Discount is a Mandatory Field" );
    hisValidator.addValidation("strIPDDiscount", "amount=9,2", "Enter a Valid IPD Discount in one of the Format [00000000.00] or [000000]" );

    hisValidator.addValidation("strEMEDiscount", "req", "EME Discount is a Mandatory Field" );
    hisValidator.addValidation("strEMEDiscount", "amount=9,2", "Enter a Valid EME Discount in one of the Format [00000000.00] or [000000]" );

   
    hisValidator.addValidation("strEffectiveFrmDate", "req","Effective From is a Madatory Field");
    hisValidator.addValidation("strEffectiveFrmDate", "date","Effective From should be a valid Date");
		
	hisValidator.addValidation("strPaymentModeDtl", "req","Payment Mode is a Mandatory Field");
	hisValidator.addValidation("strReorderLevel", "req","Re-Order Level is a Mandatory Field");
		
	hisValidator.addValidation("strPaymentMode", "dontselect=0","Please Select a Payement Mode");
	hisValidator.addValidation("strCrBilling",   "dontselect=0","Please Select a CR Billing(Y/N)");
	hisValidator.addValidation("strEffectiveFrom", "date","Effective From Date is a Mandatory field");	

	hisValidator.addValidation("strOPDDiscount", "req","OPD Discount is a Mandatory Field");
	hisValidator.addValidation("strIPDDiscount", "req","IPD Discount is a Mandatory Field");
	hisValidator.addValidation("strEMEDiscount", "req","EME Discount is a Mandatory Field");
		
	hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
	
	hisValidator.addValidation("strEffectiveFrmDate","dtgt="+document.forms[0].strOldExpiryDate.value,"Effective From Date should be Greater than to Old Expiry Date");
	hisValidator.addValidation("strExpiryDate","dtgtet="+document.forms[0].strEffectiveFrmDate.value,"Expiry Date should be Greater than or Equal to Effective From Date");
	
	retVal = hisValidator.validate(); 
		
		if(retVal)
		 {
			//	document.forms[0].hmode.value = "INSERT";
				document.forms[0].submit();
		}
		else
		{
		
		return false;
		}
	}
function ftn1()
{     
    alert("Inside Function");
     document.getElementById("id11").style.display="block";
        
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
		      var objVal = document.getElementById("id2");
		      objVal.innerHTML = "";
		    }  
		 
		 }
}
function getAjaxResponse(res,mode)
{
 		 if(mode=="1")
 		 {
		     var objVal = document.getElementById("id2");
		     objVal.innerHTML = res; 
           //objVal.innerHTML = "<select name = 'strRoomId' class='comboNormal' onChange=\"groupCombo3('UNITVAL3');\">" + res + "</select>";
      	   //groupCombo1('UNITVAL1');
         }
}         
  
</script>

</head>
<body>
<html:form action="billing/transactions/ClientVerificTransCNT.cnt"  method="post">
 <tag:tab tabLabel="Client Verification" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	
<table class="TABLEWIDTH" align="center">	
   <tr class="HEADER"> 
    <td colspan="4">Client Verification &gt;&gt;Renew\Extend</td>
  </tr>
   <tr class="HEADER">
       <td width=75%></td>
                <td width="25%"><select name="strCrBilling" class="comboMin"  onChange="ftn1();">
				<option value="0" selected>Re-New</option>
				<option value="1">Extend</option>
				</select>
				</td>
	</tr>
	
 	
</table>
<table class="TABLEWIDTH" align="center">	 
  <tr>
      <td>
     
     </td>
  </tr>
  
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Balance Amount:</td>
    <td width="25%" class="CONTROL"><input type="text" class="txtFldNormal" name="strBalanceAmt" value ="${clientverificTransBean.strBalanceAmt}" readonly></td>   
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="CONTROL"></td>    
   </tr>
  
  </table>
   
  <logic:equal  name="clientverificTransBean" property="strPaymentType" value="1"> 
  
  <table class="TABLEWIDTH" align="center">
   <tr> 
    <td colspan="4" class="TITLE" >Deposit Detail</td>
   </tr>
   
   <tr> 
   
    <td width="25%" class="LABEL"><font color="red">*</font>Deposit Amount:</td>
    <td width="25%" class="CONTROL"><input type="text" name="strDepositAmt" value ="" class="txtFldNormal" onkeypress="return validateData(event,7);"></td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Payment Mode:</td>
    <td width="25%" class="CONTROL"><select name="strPaymentMode" class='comboMin' onChange="groupCombo('UNITVAL');">
				<option value="0" selected >Select</option>
				<option value="1">CASH</option>
				<option value="2">CHEUQE</option>
				<option value="3">DD</option>
				<option value="4">DEBIT CARD</option>
				<option value="5">CREDIT CARD</option>
			</select></td>   
   </tr>
   </table>
    
       <div id="id2"></div>
     
   <table class="TABLEWIDTH" align="center">
   <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>PyamentMode Details:</td>
    <td width="25%" class="CONTROL"><input type="text" name="strPaymentModeDtl" value ="" class="txtFldNormal"  onkeypress="return validateData(event,9);"></td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Re-Order Level:</td>
    
    <td width="25%" class="CONTROL"><input type="text" name="strReorderLevel" value ="" class="txtFldNormal" onkeypress="return validateData(event,7);"></td>   
   </tr>
   <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR Billing Allow(Y/N):</td>
    <td width="25%" class="CONTROL">
                <select name="strCrBilling" class='comboMin'>
				<option value="0" selected>Select</option>
				<option value="1">YES</option>
				<option value="2">NO</option>
			    </select></td>   
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="CONTROL"></td>   
   </tr>
   <tr>
        
   </tr>
   </table>
  </logic:equal>
     
  <table class="TABLEWIDTH" align="center">
  <tr> 
    <td colspan="4" class="TITLE">Bank Guarntee Details</td>
   
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>BG Amount</td>
    
    <td width="25%" class="CONTROL"><input type="text" name="strBgAmount" value ="${clientverificTransBean.strBgAmount}" class="txtFldNormal" readonly onkeypress="return validateData(event,7);"></td>   
    <td width="25%" class="LABEL">Renewal Date</td>
    <td width="25%" class="CONTROL"><input type="text" name="strBgReNewalDate" value ="${clientverificTransBean.strBgReNewalDate}" class="txtFldNormal" readonly></td>    
   </tr>
  <tr> 
    <td width="25%" class="LABEL">Bank Name</td>
    <td width="25%" class="CONTROL"><input type="text" name="strBankName" value ="${clientverificTransBean.strBankName}" class="txtFldNormal"readonly onkeypress="return validateData(event,4);"></td>   
    <td width="25%" class="LABEL">Bank Address</td>
    <td width="25%" class="CONTROL"><textarea name="strBankAddress" cols="15" rows="1"  id ="strBankAddress"  readonly onkeypress="return validateData(event,3);">${clientverificTransBean.strBankAddress}</textarea></td>   
   </tr>
   
   </table>
   
    <table class="TABLEWIDTH" border="0" align="center" >
      <tr> 
        <td colspan="3" class="TITLE">Discount By Hospital(%)  
       </td>
     </tr>
	 <tr>
	   	<td width="33%" class="multiLabel" align='center'><font color="red">*</font>OPD</td>
		<td width="33%" class="multiLabel" align="center"><font color="red">*</font>IPD</td>
		<td width="33%" class="multiLabel" align="center">Emergency Covers</td>
	 </tr>
	 <tr>
		<td width="33%" class="multiControl" ><input type="text" align="middle" size="8" class="txtFldNormal" name="strOPDDiscount" value="${clientverificTransBean.strOPDDiscount}" onkeypress="return validateData(event,7);"></td>
		<td width="33%" class="multiControl" ><input type="text" align="middle" size="8" class="txtFldNormal" name="strIPDDiscount" value="${clientverificTransBean.strIPDDiscount}" onkeypress="return validateData(event,7);"></td>
		<td width="33%" class="multiControl" ><input type="text" align="middle" size="8" class="txtFldNormal" name="strEMEDiscount" value="${clientverificTransBean.strEMEDiscount}" onkeypress="return validateData(event,7);"></td>
	 </tr>
    </table>
   
   <table class="TABLEWIDTH" border="0" align="center" >
   <tr> 
    <td width="25%" class="LABEL">Old Effective From Date:</td>
    <td class="CONTROL"><input type="text" name="strEffectiveFrmDate" class="txtFldNormal" value ="${clientverificTransBean.strEffectiveFrmDate}" readonly></td> 
    <td width="25%" class="LABEL">Old Expiry Date:</td>
    <td class="CONTROL"><input type="text" name="strOldExpiryDate" class="txtFldNormal" value ="${clientverificTransBean.strOldExpiryDate}" readonly></td> 
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Effective From Date:</td>
    <td class="CONTROL"><date:date name="strEffectiveFrmDate" value=""></date:date></td> 
    <td width="25%" class="LABEL"><font color="red">*</font>Expiry Date:</td>
    <td class="CONTROL"><date:date name="strExpiryDate" value=""></date:date></td> 
  </tr>
  
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
</body>
</html>