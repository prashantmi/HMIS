<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>


<html>
<head>
<title>Rate Contract Details</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../js/rate_contract_trans.js"></script>



<script language="javaScript">


function validateRenew()
{   
      var hisValidator = new HISValidator("rateContractDtlBean"); 
       var retVal=true;
         
            hisValidator.addValidation("strDeliveryDays",     "req", "Delivery Day(s) is a Mandatory Field" );
             if(parseInt(document.forms[0].strDeliveryDays.value)==0)
             {
             	alert("Please Enter Delivery Day(s) Greater than Zero!!!");
             	return false;
             }
            
            hisValidator.addValidation("strContractFromDate", "req", "Contract From Date is a Mandatory Field" );
	        hisValidator.addValidation("strContractToDate",   "req", "Contract To Date is a Mandatory Field" ); 
            hisValidator.addValidation("strTenderDate",       "req", "Tender Date is a Mandatory Field" );
            hisValidator.addValidation("strContractFromDate", "dtgt=${rateContractDtlBean.strPreviousContractFromDate}", "Contract From Date should be greater than to Previous Contract from Date");
            hisValidator.addValidation("strContractFromDate", "dtgt=${rateContractDtlBean.strCtDate}", "Contract from Date should be greater than Current Date");
            hisValidator.addValidation("strContractToDate",   "dtgt=${rateContractDtlBean.strNextContractFromDate}", "Contract To Date should be greater than Previous Contract To Date");
            
            hisValidator.addValidation("strTenderDate",       "dtltet="+document.forms[0].strContractFromDate.value+"", "Tender Date should be Less than or Equal to Contract From Date");
            hisValidator.addValidation("strRemarks",          "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            hisValidator.addValidation("strRenewRate",        "req", "Rate is a Mandatory Field" );  
            hisValidator.addValidation("strRenewRate",        "amount=11,2", "Rate should be in format 000000000.00" );
            hisValidator.addValidation("strRenewRateUnitId",  "dontselect=0", "Please select a Unit" );  
            hisValidator.addValidation("strRenewRateContQty",       "req",  "Qty is a Mandatory Field" );  
            hisValidator.addValidation("strRenewSecurityAmtPercent", "req", "Security Amount is a Mandatory Field" ); 
            retVal = hisValidator.validate(); 
            hisValidator.clearAllValidations();

           
            if(document.forms[0].strRCChk.checked)
            	document.forms[0].strRCChk.value = "1";
            else
            	document.forms[0].strRCChk.value = "0";
           if(retVal)
           {
          				 document.forms[0].hmode.value = "INSERTRENEW";
                        document.forms[0].submit();
            }
            else
            {
					return false;
			}
    }

function CalculateSecurityAmt1()
{
	  	var  rate  = "";    
	  	var  qty   = "";
	  	var  finalRate  = 0.00;
	  	var  cost  = 0.00;
	  	var  rate_unit_base_value = "";
        
        rate    = trimAll(document.getElementById("strRenewRate").value);
        qty     = trimAll(document.getElementById("strRenewRateContQty").value);
       
		if(rate == "" || rate.length <=0)
		{
			rate = "0";
		}
		if(qty == "" || qty.length <=0)
		{
			qty = "0";
		}		
		if(document.getElementById("strRenewRateUnitId").value!="0")
        {
          rate_unit_base_value = document.getElementById("strRenewRateUnitId").value.split("^")[1];
          cost = parseFloat((qty  * rate)/rate_unit_base_value);
		}
		else
		{
		   cost = parseFloat(qty  * rate);
		}
		var  amtPerecentage = "";
		var  amtPerecentage = trimAll(document.getElementById("strRenewSecurityAmtPercent").value);
			   
	    if(amtPerecentage != "" && amtPerecentage.length > 0)
	    {
	    	finalRate = ( cost * parseFloat(amtPerecentage))/100;
	    }
	    else
	    {
	        finalRate  = cost;
	    }
	    
	    document.getElementById("strRenewSecurityAmount").value = roundValue(finalRate, 2);	    
} 

function showPrevRenewDtls()
{
    
	if(document.forms[0].strPrevRenewFlag.value==1)
	
	document.getElementById("previousRenewDtlsDiv").style.display="block";
	
	//document.forms[0].strContractToDate.focus();
	
	//if(document.forms[0].strTaxTypeNumValue.value==1)
	//{
	   document.getElementById("taxTypeDiv").innerHTML ="<select name='strTaxType' class='comboMin'><option value='1' >GST</option></select>";				
					    
    /*}
    else
    {
       document.getElementById("taxTypeDiv").innerHTML ="<select name='strTaxType' class='comboMin'><option value='1'>GST</option></select>";				
    }*/
}
</script>

</head>
<body onLoad="showPrevRenewDtls();">
<html:form name="rateContractDtlBean" action="/transactions/RateContractDtlTransCNT"
	type="mms.transactions.controller.fb.RateContractDtlTransFB">

<center>
	<div id="errMsg" class="errMsg"><bean:write name="rateContractDtlBean"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write name="rateContractDtlBean"
		property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="rateContractDtlBean"
		property="strMsg" /></div>


	<tag:tab tabLabel="Rate Contract Details"
				selectedTab="FIRST" align="center" width="TABLEWIDTH">
			</tag:tab>
			</center>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">

		<tr class="HEADER">
			<td colspan="4">Rate Contract Details&gt;&gt; Renew</td>
		</tr>

		<tr>
			<td class="LABEL" width="25%" >Supplier Name</td>
			<td width="25%" class="CONTROL" ><bean:write name="rateContractDtlBean"
				property="strSupplierName" filter="false" /></td>
				
				<td class="LABEL" width="25%" >Contract Type</td>
			<td width="25%" class="CONTROL" ><bean:write name="rateContractDtlBean"
				property="strContractType" filter="false" /></td>
				

		</tr>
		<tr>
			<td class="LABEL" width="25%" >Drug Category</td>
			<td class="CONTROL" colspan="3"><bean:write name="rateContractDtlBean"
				property="strItemCategoryName" filter="false" /></td>
				
				

		</tr>
		</table>
		<div id="previousRenewDtlsDiv" style="display: none" >
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="TITLE"  colspan="4">Renew details</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Renew Date</td>
			<td class="CONTROL" ><bean:write name="rateContractDtlBean" property ="strPrevRenewDate" filter="false"/></td>

			<td class="LABEL" width="25%"></td>
			<td class="CONTROL" ></td>

		</tr>
		
		<tr>
				<td width="25%" class="LABEL" >Contract From</td>
				<td width="25%" class="CONTROL"><bean:write name="rateContractDtlBean" property ="strPrevRenewContractFromDate" filter="false"></bean:write></td>
					<td width="25%" class="LABEL">Contract To</td>
				<td width="25%" class="CONTROL"><bean:write name="rateContractDtlBean" property ="strPrevRenewContractToDate" filter="false"></bean:write></td>
			</tr>
			
		     <tr>
				<td width="25%" class="LABEL">Tender No.</td>
				<td width="25%" class="CONTROL"><div style="pointer-events: none;"><bean:write name="rateContractDtlBean" property ="strPrevRenewTenderNo" filter="false"></bean:write></div>
				</td>
					<td width="25%" class="LABEL">Quotation No.</td>
				<td width="25%" class="CONTROL"><bean:write name="rateContractDtlBean" property ="strPrevRenewQuotationNo" filter="false"></bean:write>
				</td>
			</tr>
			
			 <tr>
				<td width="25%" class="LABEL">Tender Date.</td>
				<td width="25%" class="CONTROL"><bean:write name="rateContractDtlBean" property ="strPrevTenderDate" filter="false"></bean:write>
				</td>
					<td width="25%" class="LABEL">Quotation Date.</td>
				<td width="25%" class="CONTROL"><bean:write name="rateContractDtlBean" property ="strPrevQuotationDate" filter="false"></bean:write>
				</td>
			</tr>
			
			<tr>
				<td width="25%" class="LABEL">Tax(%).</td>
				<td width="25%" class="CONTROL"><bean:write name="rateContractDtlBean" property ="strPrevTaxWithType" filter="false"></bean:write>
				</td>
					<td width="25%" class="LABEL"></td>
				<td width="25%" class="CONTROL"></td>
			</tr>
			
		 
		
			<tr>
				
				
				<td width="25%" class="LABEL">Remarks</td>
				<td  class="CONTROL" >
				<bean:write name="rateContractDtlBean" property ="strPrevRenewRemarks" filter="false"></bean:write>
				</td>
				
				<td width="25%" class="LABEL"></td>
				<td  class="CONTROL" ></td>
			</tr>
			</table> 
			<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
				<tr>
			     <td class="TITLE" colspan="4">Renew Drug Details</td>
		        </tr>
				<tr>
				<td width="25%" class="LABEL">Drug Name</td>
				<td width="25%" class="CONTROL">
				    <bean:write name="rateContractDtlBean" property ="strPrevRenewItemName" filter="false"></bean:write>
				</td>
			    <td width="25%" class="LABEL">Rate/Unit</td>
				<td width="25%" class="CONTROL">
				    <bean:write name="rateContractDtlBean" property ="strPrevRenewRate" filter="false"></bean:write> <bean:write name="rateContractDtlBean" property ="strPrevRenewRateUnit" filter="false"></bean:write>
				</td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Security Amount(%)</td>
				<td width="25%" class="CONTROL">
				   <bean:write name="rateContractDtlBean" property ="strPrevSecurityAmt" filter="false"></bean:write>
				</td>
				<td width="25%" class="LABEL">Rate Contract Qty</td>
				<td width="25%" class="CONTROL">
				   <bean:write name="rateContractDtlBean" property ="strPrevContractQty" filter="false"></bean:write>
				</td>
			</tr>
		  </table> 
		</div>
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="TITLE"  colspan="4">Current Contract details</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Renew Date</td>
			<td class="CONTROL" ><bean:write name="rateContractDtlBean" property ="strCtDate" filter="false"/></td>

			<td class="LABEL" width="25%">Current Validity</td>
			<td class="CONTROL" ><bean:write name="rateContractDtlBean" property ="strContractValidity" filter="false"/></td>

		</tr>
		
		<tr>
				<td width="25%" class="LABEL" ><font  color="red">*</font>Contract From</td>
				<td width="25%" class="CONTROL">
				<dateTag:date name="strContractFromDate" value =""></dateTag:date>
				</td>
				
				<td width="25%" class="LABEL"><font color="red">*</font>Contract To</td>
				<td width="25%" class="CONTROL">
				<dateTag:date name="strContractToDate" value =""></dateTag:date>
				</td>
				
			</tr>
			
			 <tr>
				
				<td width="25%" class="LABEL"><font  color="red">*</font>Tender No.</td>
				<td width="25%" class="CONTROL">
				<input type="text" class="txtFldMax" name="strTenderNo" maxlength="50" value="${rateContractDtlBean.strTenderNo}"	onkeypress="return validateData(event,8);" readonly="readonly">
				</td>
					
					
				<td width="25%" class="LABEL">Quotation No.</td>
				<td width="25%" class="CONTROL">
				<input type="text" class="txtFldMax" name="strQuotationNo" maxlength="50" value="${rateContractDtlBean.strQuotationNo}"	onkeypress="return validateData(event,8);"></td>
			</tr>
			
			<tr>
				<td class="LABEL" width="25%" colspan='1'><font  color="red">*</font>Tender Date:</td>
				<td class="CONTROL" width="25%" colspan='1'><div style="pointer-events: none;"><dateTag:date name="strTenderDate" value="${rateContractDtlBean.strTenderDate}" ></dateTag:date></div> </td>
				
				<td class="LABEL" width="25%" colspan='1'>Quotation Date</td>
				<td class="CONTROL" width="25%"><dateTag:date name="strQuotationDate" value="${rateContractDtlBean.strQuotationDate}"></dateTag:date> </td>			    
			</tr>
		
		    <tr>
				<td class="LABEL" width="25%" colspan='1'>Tax Type:</td>
				<td class="CONTROL" width="25%"  colspan='1'>
				<div id="taxTypeDiv">
						<select name="strTaxType" class="comboMin">
						<option value="0">Select Value</option>
									
					    </select>
				</div>	    
			   </td>
				
				<td class="LABEL" width="25%" colspan='1'><font  color="red">*</font>TAX(%)</td>
				<td class="CONTROL" width="25%"><input type="text" class="txtFldMin"
					name="strTax" maxlength="4" value="${rateContractDtlBean.strTax}"
					onkeypress="return validateData(event,7);" onkeyup="notGreaterThanCent(this);"></td>			    
			</tr>
			<tr>
			<td class="LABEL" width="25%"><font  color="red">*</font>Delivery Day(s)</td>
			<td class="CONTROL" >
			   <input type="text" class="txtFldMax"
					name="strDeliveryDays" maxlength="3" value="${rateContractDtlBean.strDeliveryDays}"
					onkeypress="return validateData(event,5);">
			</td>
			<td class="LABEL" width="25%"></td>
			<td class="LABEL" width="25%"></td>
			</tr>		    				
			<tr>
			<td class="TITLE" colspan="4">Drug Details</td>
		</tr>
	
		</table>
		
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px" bgcolor='#126ea8' cellspacing="1px">
			<tr>
			<td width="15%" class="multiRPTLabel">Drug Name</td>
			<td width="10%" class="multiRPTLabel">Last Purchase Rate/Unit</td>
			<td width="10%" class="multiRPTLabel"><font color="red">*</font>Rate</td>
			<td width="10%" class="multiRPTLabel"><font color="red">*</font>Rate Unit</td>
			<td width="10%" class="multiRPTLabel"><font color="red">*</font>Qty.</td>					
			<td width="10%" class="multiRPTLabel"><font color="red">*</font>Security Amt(%)</td>			
			<td width="10%" class="multiRPTLabel"><font color="red">*</font>Security Amt.</td>
			<td width="7%" class="multiRPTLabel"><font color="red">*</font>Shelf Life (In Days)</td>
			<td width="10%" class="multiRPTLabel"><font color="red">*</font>Level</td>
			<td width="8%" class="multiRPTLabel"><font color="black">***</font>Excl. tax</td>
			<td width="8%" class="multiRPTLabel">Pack Size</td>
			</tr>
			
			
			<tr>
		
			<td width="15%" class="multiPOControl">
			<bean:write name="rateContractDtlBean" property="strItemBrandName" filter="false"></bean:write>
			</td>
		
			<td width="10%" class="multiPOControl"><bean:write name="rateContractDtlBean" property="strLastPurchaseRate" filter="false"></bean:write>
			<bean:write name="rateContractDtlBean" property="strLastPurchaseRateUnit" filter="false"></bean:write>
			</td>
		
			<td width="10%" class="multiPOControl">
			<input type="text" name="strRenewRate" id="strRenewRate" maxlength="12" class="txtFldNormal" maxlength="8" onkeypress="return validateData(event,7);" onkeyup="CalculateSecurityAmt1();"   value="${rateContractDtlBean.strRenewRate}" >
			</td>
			
			<td width="10%" class="multiPOControl">
			  <select name="strRenewRateUnitId" id="strRenewRateUnitId" class="comboNormal" onChange="CalculateSecurityAmt1();">
			   <bean:write name="rateContractDtlBean" property="strUnitCmbValues" filter="false"></bean:write>
			 </select>
			</td>
			
			<td width="10%" class="multiPOControl">			    
			    <input type="text" name="strRenewRateContQty"  id="strRenewRateContQty" maxlength="12" class="txtFldNormal" maxlength="8" onkeypress="return validateData(event,7);" onkeyup="CalculateSecurityAmt1();" value="${rateContractDtlBean.strRenewRateContQty}">
			</td>
					
			
			<td width="10%" class="multiPOControl">
			    <input type="text" name="strRenewSecurityAmtPercent"  id="strRenewSecurityAmtPercent"  class="txtFldNormal"  maxlength="5" onkeypress="return validateData(event,7);" onkeyup="notGreaterThanCent(this);CalculateSecurityAmt1();">
			</td>
			
			<td width="10%" class="multiPOControl">
			   <input type="text" name="strRenewSecurityAmount"   id="strRenewSecurityAmount" maxlength="12" class="txtFldNormal" readonly>
			</td>
	
			<td width="7%" class="multiPOControl">
			   <input type="text" name="strRenewShelfLife"   id="strRenewShelfLife" value ="${rateContractDtlBean.strRenewShelfLife}" maxlength="3" class="txtFldMin" onkeypress="return validateData(event,5);">
			</td>
			
			<td class="multiPOControl"  width="10%">
			  <select name='strRenewLevel' id="strRenewLevel" class='comboMin'>
			         <option value='L1'>L1</option>
			         <option value='L2'>L2</option>
			         <option value='L3'>L3</option>
			         <option value='L4'>L4</option>
			         <option value='L5'>L5</option>
			         <option value='L6'>L6</option>
			         <option value='L7'>L7</option>
			         <option value='L8'>L8</option>
			         <option value='L9'>L9</option>
			        
			  </select>
			</td>
			
			<td class="multiPOControl"  width="10%">
			 <input type='checkbox' name='strRCChk' id='strRCChk' value="0" >
			</td>
			
			<td width="7%" class="multiPOControl">
			   <input type="text" name="strPackSize"   id="strPackSize" value ="${rateContractDtlBean.strRenewPackSize}" maxlength="10" class="txtFldNormal" onkeypress="return validateData(event,9);">
			</td>
			</tr>
			
			
			</table>
			<div id="id1"></div>
			<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
			<tr>
			<td class="TITLE" colspan="4"></td>
		</tr>
			<tr >
    <td width ="50%" class ="LABEL" valign="middle" colspan="2">Remarks</td>
    <td width ="50%" class ="CONTROL" colspan="2"><textarea  name="strRemarks" cols="25" rows="2" onkeypress="return validateData(event,9);"><bean:write name="rateContractDtlBean" property="strRemarks" filter="false"></bean:write></textarea></td>
  </tr>
		<tr class="FOOTER">
			<td colspan="2"><div align='left'><font size="2" color="black">***</font>RC Rate will pe picked exclusive of (5% Tax) for PO Value for this item.</div></td>
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<!-- <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center">
			<img style="cursor: pointer; "
src="../../hisglobal/images/btn-sv.png"
onClick=" return validateRenew();" title="Save Record"/> <img
style="cursor: pointer; " title="Clear Content"
src="../../hisglobal/images/btn-clr.png"
onClick="document.forms[0].reset(),document.forms[0].strContractToDate.focus();" />
<img style="cursor: pointer; " title="Click Here Go Back To Main Desk" 
src="../../hisglobal/images/back_tab.png" onClick="cancel();" />
			
			</td>
		</tr>
	</table> -->
		<br>
	<div align="center" id=" ">					 
					 	<a href="#" class="button" id="submitId" onclick=' return validateRenew();'><span class="save">Save</span></a>
						<a href="#" class="button"	onclick="document.forms[0].reset(),document.forms[0].strContractToDate.focus();"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
					</div> 
<input type="hidden" name="hmode"/>
<input type="hidden" name="ctDate" value="${rateContractDtlBean.strCtDate}"/>
<input type="hidden" name="strSupplierId" value="${rateContractDtlBean.strSupplierId}"/>
<input type="hidden" name="strSupplierName" value="${rateContractDtlBean.strSupplierName}"/>
<input type="hidden" name="strContractTypeID" value="${rateContractDtlBean.strContractTypeID}"/>
<input type="hidden" name="strContractType" value="${rateContractDtlBean.strContractType}"/>
<input type="hidden" name="strItemCategoryNo" value="${rateContractDtlBean.strItemCategoryNo}"/>
<input type="hidden" name="strItemCategoryName" value="${rateContractDtlBean.strItemCategoryName}"/>
<input type="hidden" name="strItemID" value="${rateContractDtlBean.strItemID}"/>
<input type="hidden" name="strItemBrandID" value="${rateContractDtlBean.strItemBrandID}"/>
<input type="hidden" name="strSlNo" value="${rateContractDtlBean.strSlNo}"/>
<input type="hidden" name="strTenderNo" value="${rateContractDtlBean.strTenderNo}"/>
<input type="hidden" name="strQuotationNo" value="${rateContractDtlBean.strQuotationNo}"/>
<input type="hidden" name="strDeiveryLeadTime" value="${rateContractDtlBean.strDeiveryLeadTime}"/>
<input type="hidden" name="strDeiveryLeadTimeUnit" value="${rateContractDtlBean.strDeiveryLeadTimeUnit}"/>

<input type="hidden" name="strChk1" value="${rateContractDtlBean.strChk1}"/>
<input type="hidden" name="comboValue" value="${rateContractDtlBean.comboValue}"/>
<input type="hidden" name="strPrevRenewFlag" value="${rateContractDtlBean.strPrevRenewFlag}"/>

<input type="hidden" name="strTaxTypeNumValue" value="${rateContractDtlBean.strTaxType}"/>
<input type="hidden" name="strPreviousContractFromDate" value="${rateContractDtlBean.strPreviousContractFromDate}"/>
<input type="hidden" name="strRcTenderSno" value="${rateContractDtlBean.strRcTenderSno}"/>

<div id="blanket" style="display:none;"></div>
<div class="popUpDiv" id="popUpDiv" style="display:none;">
<table bgcolor="white">
<tr>
<td>
<div id="searchItemsDtlsDivId" style="display:block;"></div>
</td>
</tr>
</table>
</div>

<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>