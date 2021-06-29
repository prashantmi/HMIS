<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
Bill Enquiry Report
author: Debashis Sardar
dated: 05/03/2012-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head><meta charset=utf-8>
<title>Bill Enquiry Report</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<style type='text/css'>

</style>

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script type="text/javascript">
function showPatientDetails()
{
 	document.forms[0].strCrNo.focus();
 	document.forms[0].strCrNo.select();
 	SetCursorToTextEnd('strCrNoId');
 	if(document.forms[0].strCrNo.value!="")
 	{
		document.getElementById("details").style.display="block";
		document.getElementById("patdtltld").style.display="block";
		document.getElementById("billdtls").style.display="block";
		document.getElementById("lastbuttons").style.display="block";
		document.getElementById("onlyClearbutton").style.display="none";		
	}
	else
	{
		document.forms[0].strCrNo.focus();
		document.getElementById("details").style.display="none";
		document.getElementById("CONSOLIDATEDREPORT").style.display="none";
	}
}
function chageReportFormat(obj)
{
	if(obj.checked)
	{
		document.getElementById("pdfDiv").style.display="";
		document.getElementById("htmlDiv").style.display="none";
		document.getElementsByName("strReportFormat")[1].checked=true;
		document.getElementById("CRWISE").style.display="none";
		document.getElementById("CR").style.display="";
		document.getElementById("CONSOLIDATEDREPORT").style.display="none";
		document.getElementById("go").style.display="none";
	}
	else
	{
		document.getElementById("htmlDiv").style.display="";
		document.getElementById("pdfDiv").style.display="none";
		document.getElementsByName("strReportFormat")[0].checked=true;
		document.getElementById("CRWISE").style.display="";
		document.getElementById("CR").style.display="none";
		document.getElementById("CONSOLIDATEDREPORT").style.display="none";
		document.getElementById("go").style.display="";
	}
}
function initGoFunc(eve)
{
	    var flag=validateData(eve,5);
  		 if(flag)
  		 {
	   		if(eve.keyCode == 13)
	   		{
				goFunc();
				return false;
			}
  		}
  		else
  		{
	   		return false;
	   	}			
}
function getRadioValue() 
{
  var index=0;
			for (index=0; index < document.forms[0].strReportType.length; index++) 
			{
				if (document.forms[0].strReportType[index].checked) 
				{
					var radioValue = document.forms[0].strReportType[index].value;
					break;
				}
			}
			return radioValue;
}

function showReport()
{
	if(document.forms[0].strConsolidatedCreditReport.checked)
	{
		document.forms[0].hmode.value = "SHOWCONSOLIDATEDCREDITREPORT";
	}
	else
	{	
		var obj=getRadioValue();
		document.forms[0].hmode.value = "SHOWRPT";
		if(obj==1)	
			document.forms[0].target = "_self";
		else if(obj==2)
			document.forms[0].target = "_blank";		z
	}
	document.forms[0].submit();
}	
function generate()
{
	if(document.forms[0].strConsolidatedCreditReport.checked)
	{
		/*var hisValidator = new HISValidator("billEnquiryRpt");  
		
		hisValidator.addValidation("strCrNo1", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo1", "minlen="+document.forms[0].strCrNo1.maxLength, "CR No. must be "+document.forms[0].strCrNo1.maxLength+" Digits" );
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		
		if(retVal)
		{*/
			if(document.getElementsByName("strReportFormat")[0].checked)//HTML
			{
				alert("Consolidated Credit Report Can Be generated in PDF Only!");
				document.getElementsByName("strReportFormat")[1].checked=true;
				return;
			}		
			document.forms[0].hmode.value = "SHOWCONSOLIDATEDCREDITREPORT";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
		/*}
		else
		{
			return false;
		}*/
	}
	else
	{
		var counter=0;
		for(var i=0;i<document.getElementsByName("chk").length;i++)
		{
				if(document.getElementsByName("chk")[i].checked)
				counter++;			
		}
		document.forms[0].hmode.value = "GENERATEREPORT";
		if(document.getElementsByName("strReportFormat")[0].checked)//HTML
		{
			document.forms[0].target = "_self";
		}
		else
		{
			document.forms[0].target = "_blank";
		}
		document.getElementsByName("strCrNo")[0].disabled=false;
		document.getElementsByName("strBillType")[0].disabled=false;
		
		if(counter>0)
			document.forms[0].submit();
		else
			alert("Please Select a Bill No...");
	}
	
}
function goFunc()
{
	document.getElementsByName("strCrNo")[0].disabled=false;
	document.getElementsByName("strBillType")[0].disabled=false;
	if(document.forms[0].strCrNo.disabled == false)
	{
		var hisValidator = new HISValidator("billEnquiryRpt");  
	
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		
		hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strEffectiveFrom","dtltet="+document.forms[0].strCurrentDate.value,"Please Select From Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		
			if(retVal)
			{
				if(validateFunc(document.forms[0].strCrNo,5))
					{
				var temp = document.forms[0].strBillType[document.forms[0].strBillType.selectedIndex].value ;
				document.forms[0].strBillTypeName.value=document.forms[0].strBillType[document.forms[0].strBillType.selectedIndex].text ;
				var strCrNo=document.forms[0].strCrNo.value;
				var strEffectiveFrom=document.forms[0].strEffectiveFrom.value;
				var strEffectiveTo=document.forms[0].strEffectiveTo.value;
				var mode="GO";
			    var url="BillEnquiryRptCNT.cnt?hmode="+mode+"&strBillType="+temp+"&strCrNo="+strCrNo+"&strEffectiveFrom="+strEffectiveFrom+"&strEffectiveTo="+strEffectiveTo;
				
			    ajaxFunction(url,"1");
			}
			}
			else
			{
				return false;
			}
	}
	else
	{
		return false;
	}
}	

function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].submit();

}  
function clearPage()
{
	document.forms[0].hmode.value="CLEAR";
	document.forms[0].submit();
}
function showPatDetails(divId)
{  
	 document.getElementById(divId).style.display='block';  
	 document.getElementById('minusId'+divId).style.display='block'; 
	 document.getElementById('plusId'+divId).style.display='none';	
} 
  
function hidePatDetails(divId)
{  
	 document.getElementById(divId).style.display='none';  	
	 document.getElementById('minusId'+divId).style.display='none';   
	 document.getElementById('plusId'+divId).style.display='block';
} 
function getAjaxResponse(res,mode)
{
	if(mode=="1")
    {
      if(res=="ERROR")
      {
	      	document.forms[0].hmode.value = "unspecified";
	      	document.forms[0].strErrMsg.value = "Invalid CR No.";
			document.forms[0].submit();
      }
      else
      {
		    document.getElementById("ID_HEADER").style.display="none";	    
		    document.getElementById("onlyClearbutton").style.display="none";
		    document.getElementById("ID_BILL_LIST_HEADER").style.display="block";
		    var objVal=document.getElementById("ID_BILL_LIST");
	    
	    	var res1=res.split('^')[0];
	    	var res2=res.split('^')[1];
	    	objVal.innerHTML = res1;
	    	document.getElementById("patView").style.display="block";
	    	document.getElementById("details").style.display="block";
	    	document.getElementById("patView").innerHTML=res2;
	    	document.getElementsByName("strCrNo")[0].disabled=true;
	    	document.getElementsByName("strBillType")[0].disabled=true;
  	  }
  }
  
  if(mode=="2")
    {
     	    //alert("res is::"+res);
     	    document.getElementById("viewReportInner").innerHTML=res;	    
		   
	 		popup('viewReport' , '250','250');
  	 
  }
}

function viewBill(event,strBillNo,strRcpt)
{
	 var mode="RETURNHTML";
	 var url="BillEnquiryRptCNT.cnt?hmode="+mode+"&strBillNo="+strBillNo+"&strReceiptNo="+strRcpt;
	 
	 document.forms[0].strTransNo.value=strBillNo;
	 document.forms[0].strRcptNo.value=strRcpt;
	  					
	 ajaxFunction(url,"2");
			    
	  
}
function checkall(obj)
{
	if(obj.checked)
	{
		for(var i=0;i<document.getElementsByName("chk").length;i++)
		{
			document.getElementsByName("chk")[i].checked=true;		
		}
	}
	else
	{
		for(var i=0;i<document.getElementsByName("chk").length;i++)
		{
			document.getElementsByName("chk")[i].checked=false;		
		}
	}
}

function checkallevent(eve,obj)
{
	if(eve.keyCode='32' && obj.checked)
	{
		for(var i=0;i<document.getElementsByName("chk").length;i++)
		{
			document.getElementsByName("chk")[i].checked=true;		
		}
	}
	else
	{
		for(var i=0;i<document.getElementsByName("chk").length;i++)
		{
			document.getElementsByName("chk")[i].checked=false;		
		}
	}
}

function forceDownloadPdf(strBillNo,strRcpt)
{
	document.forms[0].strTransNo.value=strBillNo;
	document.forms[0].strRcptNo.value=strRcpt;
	document.forms[0].strReportingFormat.value="pdf";
	//alert("values are::"+document.forms[0].strTransNo.value+"::"+document.forms[0].strRcptNo.value+"::"+document.forms[0].strReportingFormat.value);
	document.forms[0].hmode.value = "SHOWRPT";
	document.forms[0].submit();
}	
</script>
</head>
<body onLoad="showPatientDetails();">
<html:form action="reports/BillEnquiryRptCNT.cnt"
	 method="post">
	   
    <div id='errMsg' class="errMsg"><bean:write name="billEnquiryRpt" property="strErrMsg" filter="false"/></div>   
	<tag:tab tabLabel="Bill Enquiry Report" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<div class="normalMsg" id="normalMsg"></div>
	<table class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr class="HEADER" bgcolor="">
			<td colspan="3" width="90%">Bill Enquiry Report</td>
			<td colspan="1" width="10%"><div align='right'>Consolidated CM Relief Fund Report&nbsp;<input type="checkbox" name='strConsolidatedCreditReport' value="1" onchange="chageReportFormat(this);"></div></td>
		</tr>
		<tr id="CONSOLIDATEDREPORT" style="display: none;">			
			<td width="25%" class="LABEL" colspan="2"><font color="red">*</font>Organization</td>
		    <td width="25%" class="CONTROL" colspan="2">
			     <select name="strOrgCode" class="comboNormal" >
			    <option value="0">All</option>    
			    </select>  
			    <select name="strOrgCode" id="strOrgCode" class="comboNormal">
			    <bean:write name="billEnquiryRpt" property="strOrgCodeValues" filter="false"/></select>
		    </td>
	  	</tr>
		<tr id="CRWISE">			
			<td width="25%" class="LABEL" ><font color="red">*</font>CR No.</td>
			<td width="25%" class="CONTROL" >
                <crNo:crNo name="strCrNo" id="strCrNoId" value="${billEnquiryRpt.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
			</td>	
		    <td width="25%" class="LABEL">Bill Type</td>
		    <td width="25%" class="CONTROL"><select name="strBillType" class="comboNormal" >
		        <bean:write name="billEnquiryRpt" property="strBillTypeValues" filter="false"/></select> </td>
	  	</tr>
	  	<tr id="CR" style="display: none;">			
			<td width="25%" class="LABEL" >CR No.</td>
			<td width="25%" class="CONTROL" >
                <crNo:crNo name="strCrNo1" id="strCrNoId1" value="${billEnquiryRpt.strCrNo1}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
			</td>
	  	</tr>
	 </table>
	 <table class="TABLEWIDTH" cellspacing="1px" align="center">
	  	<tr>
			<td width="25%" class="LABEL"> From Date</td>
			<td width="25%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${billEnquiryRpt.strCurrentDate}" /></td>
			<td width="25%" class="LABEL"> To Date</td>
			<td width="25%" class="CONTROL"><dateTag:date name="strEffectiveTo" value="${billEnquiryRpt.strCurrentDate}" />
				<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go" id="go" onclick="return goFunc();" onkeypress="return goFunc();"/>
		</td>
		</tr>
		<tr>
				<td width="25%" class="LABEL" colspan="2"><font color="red">*</font>Report Type</td>
				<td class="CONTROL">
					<div id="htmlDiv"> <html:radio property="strReportFormat" name="billEnquiryRpt" value="HTML">HTML</html:radio> </div>
                    <div id="pdfDiv" style="display:none;">   <html:radio property="strReportFormat" name="billEnquiryRpt" value="PDF">PDF</html:radio> </div>
				</td>
				<td width="25%" class="LABEL" colspan="2"></td>
		</tr>
	</table>
	
	<div  id="details" style="display: none">
			<div id="patView" style="display: none"></div>
 	</div>	
	<div id="blanket" style="display: none;"></div>
	<div class="popUpDiv" id="viewReport" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>
			<div class='popup' id='viewReportInner' style="display: block">			
			<table width='300' border='0' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
						<br>
						<a href="#" class="button" id="" onclick='showReport();'><span class="generate">Generate</span></a>
						<a href="#" class="button"	onClick="hide_popup('viewReport');"><span class="cancel">Cancel</span></a> 
						
						
					</td>
				</tr>
			</table>
			
			</div>
			</td>
		</tr>
	</table>
	</div>	
	
<div id="ID_BILL_LIST_HEADER" style="display: none">
<table class="TABLEWIDTH" cellspacing="1px" align="center">
			<tr class="TITLE">
				<td colspan="3">Bill Details</td>				
			</tr>			
		</table>
		<div id="ID_BILL_LIST"></div>		
	 </div>	
	
<div id="ID_HEADER" style="">
<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
	<tr class="FOOTER">
		<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
	</tr>
</table>
</div>
	
<div id="onlyClearbutton" style="display:block">	
<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">	
				<br>			
				<a href='#' class='button' onClick='generate();'><span class='generate'>Generate</span></a>
				<a href="#" class="button"	onclick="clearPage();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
</table>
</div>
	
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="CrNo" value="${billEnquiryRpt.strCrNo }"/>
	<input type="hidden" name="CrNo1" value="${billEnquiryRpt.strCrNo1 }"/>
    <input type="hidden" name="strCurrentDate" value="${billEnquiryRpt.strCurrentDate}"/>
    <input type="hidden" name="strTransNo" />	
    <input type="hidden" name="strRcptNo" />	
     <input type="hidden" name="strBillTypeName" />	
     <input type="hidden" name="strReportingFormat" value="${billEnquiryRpt.strReportingFormat }"/>
     <input type="hidden" name="strErrMsg" />	
     
    
	</html:form>
	<tag:autoIndex></tag:autoIndex>  
</body>
</html>