<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Payment Detail Report</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function multiSelHosp()
{
	
	var tempObj=document.getElementsByName('strHospitalCode')[0];
	var countSel=0;
	
	for(var i=0;i<tempObj.options.length;i++)
	{
			if(tempObj.options[i].selected)
			{
				countSel+=1;
			}		
	}
	
	return 	countSel;
	
}
function validate()
{
	var tempObj=document.getElementsByName('strHospitalCode')[0];
	var countSel=0;
	var allHospCodes='testCode';
	countSel=multiSelHosp();
	var allSelFlag='0'; // 0 all not sel,1 all sel
	
	//alert("sel val count is::"+countSel+"::"+tempObj.value);	
	if(countSel > 1)//multiple hospitals are selected and all is also selected..
	{
		for(var i=0;i<tempObj.options.length;i++)
		{
			if(tempObj.options[0].selected)//All selected
			{
				
				tempObj.options[i].selected=false;	
				if(allHospCodes=='testCode')
				{
					allHospCodes=tempObj.options[i].value;
				}
				else
				{
					allHospCodes=allHospCodes+','+tempObj.options[i].value;
				}
				
				allSelFlag='1';
			}
			else
			{
				if(tempObj.options[i].selected)
				{
					if(allHospCodes=='testCode')
					{
						allHospCodes=tempObj.options[i].value;
					}
					else
					{
						allHospCodes=allHospCodes+','+tempObj.options[i].value;
					}
				}
			}
		}
		
		if(allSelFlag=='1')
		{
			tempObj.options[0].selected=true;
		
			alert("Cannot Proceed.\nEither All or multiple hospitals can be selected.Both the options cannot be exercised together.");
			
			return false;	
		}
		else
		{
			
		}
		
		
			
	}
	
	if(countSel == 1)//either All or single hospital is selected..
	{
		for(var i=0;i<tempObj.options.length;i++)
		{
			
			if(tempObj.options[0].selected)//All selected
			{
				if(allHospCodes=='testCode')
				{
					
					allHospCodes=tempObj.options[i].value;
				}
				else
				{
					
					allHospCodes=allHospCodes+','+tempObj.options[i].value;
				}
			}
		}
				
	}
	
	
		if(allHospCodes=='testCode')
		{
			document.getElementsByName('strAllHospCode')[0].value='testCode';
			
		}
		else
		{
			document.getElementsByName('strAllHospCode')[0].value=allHospCodes;
		}
	
	//alert("strAllHospCode::"+document.getElementsByName('strAllHospCode')[0].value);
	var hisValidator = new HISValidator("paymentDtlRpt");
	
		/*if(document.forms[0].strCase[0].checked == true)
		{
				hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		}*/
		/* else if(document.forms[0].strCase[1].checked == true)
		{
				hisValidator.addValidation("strPatName", "req", "Patient Name is a Mandatory Field" );			
		} */
			
		/* if(document.forms[0].strHospitalCode.value == "0")
		{
			alert("Hospital Name is a mandatory field");
			return false;
		} */
		
		if(document.getElementById('dateDivId').style.display != 'none')
		{
			
			hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
			hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
			hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
			hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
		}
		else
		{
			hisValidator.addValidation("strBillNo", "req", "Bill No is a Mandatory Field" );	
			hisValidator.addValidation("strReceiptNo", "req", "Receipt No is a Mandatory Field" );	
			
		}
		
		
		var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();
	
	if(retVal)
	{	
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
		{			
			document.forms[0].target = "_self";
		}
		else
		{
			document.forms[0].target = "_blank";
		}
		document.forms[0].submit();
		}
		else
		{
			return false;
		}
}
function getHospitalServiceCombo(obj)
{ 
 		var url;
 		url="PaymentDtlRptCNT.cnt?hmode=HOSPSERVICECMB&hospCode="+obj.value;
		ajaxFunction(url,"2");
}
function getCombo(obj)
{	
	for(var i = 0 ; i < obj.length ; i++)
	{
		if(obj[i].checked)
		{
			obj = obj[i];
			break;
		}		
	}	
	if(obj.value == 1)
	{
		document.forms[0].strHospitalCode.value = "0";
		document.getElementById("crnoDivId").style.display = "block";
		document.getElementById("patDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "none";
		document.getElementById("billNoDivId").style.display = "none";		
		document.forms[0].strHospSerName.value = '0';	
	}
	else if(obj.value == 2)
	{
		document.forms[0].strHospitalCode.value = "0";
		document.getElementById("crnoDivId").style.display = "none";
		document.getElementById("patDivId").style.display = "block";
		document.getElementById("dateDivId").style.display = "block";
		document.getElementById("billNoDivId").style.display = "none";	
		document.forms[0].strHospSerName.value = '0';
		document.forms[0].strCrNo.value = '';	
	}
	else if(obj.value == 3)
	{	
		//document.forms[0].strHospitalCode.value = "0";
		document.getElementById("crnoDivId").style.display = "none";
		document.getElementById("patDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
		document.getElementById("billNoDivId").style.display = "none";		
		document.forms[0].strHospSerName.value = '0';
		document.forms[0].strPatName.value = '';	
		document.getElementById("feeClerkDiv").style.display = "none";
	}
	else if(obj.value == 4)
	{	
		document.forms[0].strHospitalCode.value = "0";
		document.getElementById("crnoDivId").style.display = "none";
		document.getElementById("patDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "none";
		document.getElementById("billNoDivId").style.display = "";	
			
		
			
		document.forms[0].strHospSerName.value = '0';
		document.forms[0].strBillNo.value = '';	
		document.forms[0].strReceiptNo.value = '';	
	}
	else if(obj.value == 5)
	{	
		document.getElementById("crnoDivId").style.display = "none";
		document.getElementById("patDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
		document.getElementById("billNoDivId").style.display = "none";		
		document.forms[0].strHospSerName.value = '0';
		document.forms[0].strPatName.value = '';
		document.getElementById("feeClerkDiv").style.display = "";
	}
}
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}
</script>
</head>
<body onload="getCombo(document.forms[0].strCase);">
<html:form action="/reports/PaymentDtlRptCNT.cnt" method="post">
	
	<div class="errMsg"><bean:write name="paymentDtlRpt" property="strErrMsg"/></div>

	<tag:tab tabLabel="Payment Detail"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="1" width='35%'>Payment Details</td>
			<td colspan="1" width='65%'><div align='right'>Report show Data of Billing Only All Payment Modes Except Advance & Part Payment of Credit(CM Fund)</div></td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    	<html:radio property="strCase" name="paymentDtlRpt" value="3" onclick="getCombo(this);" >Date</html:radio>
    	<html:radio property="strCase" name="paymentDtlRpt" value="5" onclick="getCombo(this);" >Cashier</html:radio>
    	<div style="display:none">
    	<html:radio property="strCase" name="paymentDtlRpt" value="4" onclick="getCombo(this);" >Bill No</html:radio>
    	</div>
    </div>
    </td>
 	 </tr>
 	 
 	 <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" onclick="multiSelHosp();" class='comboNormal'>
			<bean:write name="paymentDtlRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
		<tr style="display:none;" id="feeClerkDiv">	
			<td  class="LABEL" ><font color="red">*</font>Fee Clerk</td>
			<td  class="CONTROL">
			<select name="strFeeClerk"  class='comboNormal'>
			<bean:write name="paymentDtlRpt" property="strFeeClerkValues" filter="false"/>
			</select>
			</td>			
		</tr> 	 
 	 <tr> 
    <td width="50%" class="LABEL">Hospital Service</td>
    <td width="50%" class="CONTROL"><select name="strHospSerName" class="comboNormal" >
        <bean:write name="paymentDtlRpt" property="strHospSerValues" filter="false"/></select> </td>
        
        
  </tr>
 <!--  added for: 'payment mode' combo,  by: manisha gangwar dt: 23.8.18 -->
  <tr> 
    <td width="50%" class="LABEL">Payment Mode</td>
    <td width="50%" class="CONTROL"><select name="strPaymentMode" multiple >   		
		<bean:write name="paymentDtlRpt" property="strPaymentModeContents" filter="false"/>	</select></td> 
        
  </tr>
   <!-- end -->
   
 	 </table>
 	  
  
  
  <div id="crnoDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
    <tr>
    	<td colspan="1" width="50%" class="LABEL"><font color="red">*</font>CR No.</td>
       	<td colspan="1" width="50%" class="CONTROL" >
       		<crNo:crNo value="${paymentDtlRpt.strCrNo}" js="onkeypress='return validateData(event,5)';"></crNo:crNo>
       	</td>
  	</tr>
  		</table>
  </div>
  
  <div id="billNoDivId" style="display:none;">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
    <tr>
    	<td colspan="1" width="50%" class="LABEL"><font color="red">*</font>Bill/Receipt No.</td>
       	<td colspan="1" width="50%" class="CONTROL" >
       		<input type="text" name="strBillNo" class="txtFldMax" onkeypress="return validateData(event,5)"/>
       		<input type="text" name="strReceiptNo" class="txtFldMax" onkeypress="return validateData(event,5)"/>
       	</td>
  	</tr>
  		</table>
  </div>
  
  
  <div id="patDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Patient Name</td>
    <td width="50%" class="CONTROL" >
        <input type="text" name="strPatName" class="txtFldMax" onkeypress="return validateData(event,11)"/>
    </td>
  </tr>
  </table>
  </div>
  
  
 	  <div id="dateDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${paymentDtlRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${paymentDtlRpt.strCurrentDate}" /></td>
		</tr>
		
  </table>
  </div>
  <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="1px"> 
		
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat"  onchange="">
		<option value="html">Html</option>
		<option value="pdf">Pdf</option>
		<option value="excel">Excel</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="paymentDtlRpt" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			User Remarks
			</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		<tr class="FOOTER">
			 <td colspan="1"><div align="left"><font size="2" color="red">#</font>Default Hospital Selected-Looged In Hospital  </div></td>
			 <td colspan="1"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<!-- <img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			 -->
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${paymentDtlRpt.strCurrentDate}"/>
<input type="hidden" name="strAllHospCode" value="${paymentDtlRpt.strAllHospCode}"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>