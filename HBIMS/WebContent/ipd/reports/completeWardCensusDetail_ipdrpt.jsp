<!DOCTYPE html>


<!--
/**
 * 
 * @author: Vivek Aggarwal
 * @Date: 12-July-2011
 * @Module:	ADT
 */
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset="utf-8" />
<title>Ward Census Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
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

function onLoadFunction()
{
	document.forms[0].strAdmittedDetailRequired.checked=true;
	document.forms[0].strReceivedTransferFromOtherWard.checked=true;
	document.forms[0].strDischargedByTransferToOtherWard.checked=true;
	document.forms[0].strStillBirth.checked=true;
	document.forms[0].strDischargeDetail.checked=true;
	document.forms[0].strDiedDetail.checked=true;
	document.forms[0].strSummary.checked=true;

}

function validateGenerate()
{
	var hisValidator = new HISValidator("completeWardCensusDetailBean");

	//hisValidator.addValidation("strDeptCode","dontselect=0","Please select a value from Department Combo");
	hisValidator.addValidation("strWardCodeAll","dontselect=0","Please select a value from Ward Combo");
	hisValidator.addValidation("strDate", "date","From Date is a mandatory field");
	hisValidator.addValidation("strDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select From Date Less Than or Equal To Current Date");
	
	
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
		if(retVal)
		{
		
				if((document.getElementsByName("strAdmittedDetailRequired")[0].checked==true) || (document.getElementsByName("strReceivedTransferFromOtherWard")[0].checked==true)
					|| (document.getElementsByName("strDischargedByTransferToOtherWard")[0].checked==true) || (document.getElementsByName("strStillBirth")[0].checked==true)
					|| (document.getElementsByName("strDischargeDetail")[0].checked==true) || (document.getElementsByName("strDiedDetail")[0].checked==true)
					|| (document.getElementsByName("strSummary")[0].checked==true) )
				
				{
				   
				   
					document.forms[0].hmode.value = "SHOWRPT";
					if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
					{
						document.forms[0].target = "_self";
					}
					else
					{
						//document.forms[0].target = "_blank";
						document.forms[0].target = "_self";
					}
						document.forms[0].strWardName.value=document.forms[0].strWardCodeAll[document.forms[0].strWardCodeAll.selectedIndex].text;
						document.forms[0].submit();
				}
				else
				{
					alert("Select Atleast One CheckBox For Generating The Report");
					return false;
				}
			}	
			else
			{
				return false;
			}
			
		
			
}
function getUnitCombo(obj)
{
 		if(obj.value=='0')
 		{
 			var objVal1= document.getElementById("unitDivId");
			objVal1.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'><option value='0'>All</option></select>";
			var objVal2= document.getElementById("wardDivId");
			objVal2.innerHTML = "<select name ='strWardCode'  class='comboNormal'><option value='0'>Select Value</option></select>";
 		}
 		else
 		{
 			var url ="CompleteWardCensusDetailRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value; 		 		
 			ajaxFunction(url,"1");
 		}
}
function getWardCombo(obj)
{ 
 		if(obj.value=='0')
 		{
	 		var url="CompleteWardCensusDetailRptCNT.cnt?hmode=WARDCMB&deptCode="+document.forms[0].strDeptCode.value;
			ajaxFunction(url,"2");
 		}
 		else
 		{
	 		var url="CompleteWardCensusDetailRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
			ajaxFunction(url,"2");
		}
}
function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{   
		var objVal1= document.getElementById("unitDivId");
		objVal1.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		var objVal2= document.getElementById("wardDivId");
		objVal2.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
	}
	if(mode=="2")
	{   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res + "</select>";
	}
}
function cancel()
{
	 	document.forms[0].hmode.value = "CANCEL";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
}
function clearPage()
{
	 	document.forms[0].hmode.value = "CLEAR";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
}
</script>
</head>
<body onload="onLoadFunction();">
<html:form action="/reports/CompleteWardCensusDetailRptCNT.cnt" method="post">	
<div class="normalMsg" id="normalMsg"></div>
		
<tag:tab tabLabel="Ward Census Report" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center">   
		<tr class="HEADER">
			<td colspan="2">Ward Census Report</td>
		</tr>
		
  		<tr style="display: none;">
			 <td width="50%" class="LABEL"><font color="red">*</font>Ward</td>
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" onchange="">
    			<option value="0">Select Value</option>
          	 </select> </div>
      		 </td>
		</tr>
		
		   
   		
	</table>
	
	<div id="wardDivId2" style="">
		 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  
		  	<tr> 
		    <td width="50%" class="LABEL"><font color="red">*</font>Ward</td>
		    <td width="50%" class="CONTROL"><div Id = "wardId2">
		    <select name="strWardCodeAll" class="comboNormal" onchange="">
		    <bean:write name="completeWardCensusDetailBean" property="strWardAllValues" filter="false"/>
		     </select></div></td>
		  	</tr>
  	     </table>
        </div>
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
   		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strDate" value="${completeWardCensusDetailBean.strCurrentDate}" /></td>
		</tr>
	</table>
					
<table class="TABLEWIDTH" align="center">   
		<tr class="HEADER"  style="display: none;">
			<td colspan="2">Details Required in Report</td>
		</tr>			
		
<!--Check Boxes		-->
		<tr  style="display: none;">
				<td width="50%" class="LABEL" >
					<div align="right">
						<html:checkbox property="strAdmittedDetailRequired" name="completeWardCensusDetailBean"  value="1"></html:checkbox>
					</div>	
				</td>
	
				<td width="50%" class="CONTROL" >
					<div align="left">Admitted Detail Required</div>
				</td>
				
		</tr>
				
		<tr  style="display: none;">		
				<td width="50%" class="LABEL">
					<div align="right">
						<html:checkbox property="strReceivedTransferFromOtherWard" name="completeWardCensusDetailBean"  value="0"></html:checkbox>
					</div>	
				</td>
				
				<td width="50%" class="CONTROL" >
					<div align="left">Received Transfer From Other Ward</div>
				</td>
		</tr>
		
		<tr  style="display: none;">				
				<td width="50%" class="LABEL">
					<div align="right">
						<html:checkbox property="strDischargedByTransferToOtherWard" name="completeWardCensusDetailBean"  value="0"></html:checkbox>
					</div>	
				</td>
				
				<td width="50%" class="CONTROL" ><div align="left">Discharged By Transfer To Other Ward</div>
				</td>
		</tr>
		
		<tr  style="display: none;">
				<td width="50%" class="LABEL">
					<div align="right">
						<html:checkbox property="strStillBirth" name="completeWardCensusDetailBean"  value="0"></html:checkbox>
					</div>	
				</td>
				
				<td width="50%" class="CONTROL" ><div align="left">Still Birth</div>
				</td>
		</tr>
		
		<tr  style="display: none;">		
				<td width="50%" class="LABEL">
					<div align="right">
						<html:checkbox property="strDischargeDetail" name="completeWardCensusDetailBean"  value="0"></html:checkbox>
					</div>
				</td>
				
				<td width="50%" class="CONTROL" ><div align="left">Discharge Detail</div>
				</td>
		</tr>
		
		<tr  style="display: none;">		
				<td width="50%" class="LABEL">
					<div align="right">
						<html:checkbox property="strDiedDetail" name="completeWardCensusDetailBean"  value="0"></html:checkbox>
					</div>
				</td>
				
				<td width="50%" class="CONTROL" ><div align="left">Died Detail</div>
				</td>
		</tr>
		
		<tr  style="display: none;" >		
				<td width="50%" class="LABEL" >
					<div align="right">
						<html:checkbox property="strSummary" name="completeWardCensusDetailBean"  value="1"></html:checkbox>
					</div>
				</td>
				
				<td width="50%" class="CONTROL" ><div align="left">Summary</div>
				</td>
		</tr>		
				
				
				
   		<tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
			<select name="strReportFormat" class="comboSmall">
			<option value="html">HTML</option>
			<option value="pdf">PDF</option>
			<option value="excel">EXCEL</option>
			</select>
			</td>			
		</tr> 
		<tr>
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="completeWardCensusDetailBean" value="1"></html:checkbox>
			</td>			
		</tr>
		<tr>
			<td width="50%" class="LABEL">User Remarks</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"> </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validateGenerate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();clearPage();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			 -->
			<br><a href="#" class="button" id="" onClick="return validateGenerate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();clearPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
			</td>			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strWardName"/>
<input type="hidden" name="strCurrentDate" value="${completeWardCensusDetailBean.strCurrentDate}"/>
<input type="hidden" name="strHospitalCode" value="${completeWardCensusDetailBean.strHospitalCode}"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>