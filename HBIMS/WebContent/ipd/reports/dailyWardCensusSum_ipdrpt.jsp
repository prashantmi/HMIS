<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<title>Daily Ward Census Summary Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function validate()
{
		var hisValidator = new HISValidator("dailyWardCensusSumRpt");
		document.forms[0].hmode.value = "SHOWRPT";		
		/*if(document.forms[0].strDeptCode.value == "0"){
			alert("Department is a mandatory field");
			return false;
		} */
		
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");

	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date-1");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
		
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
function getUnitCombo(obj)
{
 		if(obj.value=='0')
 		{
 			var objVal= document.getElementById("unitDivId");
			objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'><option value='0'>All</option></select>";
			objVal= document.getElementById("wardDivId");
			objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'><option value='0'>All</option></select>";
 		}
 		else
 		{
	 		var url ="DailyWardCensusSumRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
	 		ajaxFunction(url,"1");
 		}
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
		document.getElementById("datWiseDivId").style.display = "none";	
		//document.getElementById("wardDivId").style.display = "block";
		//document.getElementById("unitDisDivId").style.display = "none";
		//document.getElementById("deptDivId").style.display = "";
		//document.getElementById("unitDisplayDivId").style.display = "";
		//document.getElementById("catDivId").style.display = "none";
		//document.forms[0].strUnitNameDisplay.value="0";
	}
	else if(obj.value == 2)
	{
		//document.getElementById("catDivId").style.display = "block";
		//document.getElementById("wardDivId").style.display = "none";
		//document.getElementById("unitDisDivId").style.display = "none";
		//document.getElementById("deptDivId").style.display = "none";
		//document.getElementById("unitDisplayDivId").style.display = "none";
		//document.forms[0].strUnitNameDisplay.value="0";
		document.getElementById("datWiseDivId").style.display = "";
	}	
}


function getWardCombo(obj)
{		
	 		var url="DailyWardCensusSumRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value+"&deptCode="+document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].value;
	 		ajaxFunction(url,"2");
	 	
}
function getAjaxResponse(res,mode)
{	
	if(mode=="1")
	{   
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		//getWardCombo(document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex]);
		objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
	}	
	if(mode=="2")
	{   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res + "</select>";
	}
}
function cancel()
{
	 	showMenuFrame();
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
function changeRadio(obj)
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
		document.getElementById("deptDivId").style.display = "";
		document.getElementById("wardDivId2").style.display = "none";
		document.getElementById("wardTypeDivId2").style.display = "none";
		document.getElementById("TRNEWBORN").style.display = "";
	}
	else if(obj.value == 2)
	{	
		document.getElementById("deptDivId").style.display = "none";
		document.getElementById("wardDivId2").style.display = "";
		document.getElementById("wardTypeDivId2").style.display = "none";
		document.getElementById("TRNEWBORN").style.display = "";
	}	
	else if(obj.value == 3)
	{
		document.getElementById("deptDivId").style.display = "none";
		document.getElementById("wardDivId2").style.display = "none";
		document.getElementById("wardTypeDivId2").style.display = "";
		document.getElementById("TRNEWBORN").style.display = "none";
	}	
}
function changeRadioDateMonth(obj)
{
	for(var i = 0 ; i < obj.length ; i++)
	{
		if(obj[i].checked)
		{
			obj = obj[i];
			break;
		}		
	}	
	if(obj.value == 1)//Date Wise
	{
		document.getElementById("datWiseDivId").style.display = "";
		document.getElementById("dateYearDivId").style.display = "none";		
	}
	else if(obj.value == 2)//Month Wise
	{	
		document.getElementById("datWiseDivId").style.display = "none";
		document.getElementById("dateYearDivId").style.display = "";
	}	
	
}
function setRadio()
{
	document.getElementsByName("strCase")[1].checked="true";
}

function hideMenuFrame()
{	
//alert("parent.document.getElementById('fs2').cols="+parent.document.getElementById("fs2").cols)
	if(window.XMLHttpRequest) // Mozilla
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}
	//if (window.ActiveXObject)
	else
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}	
}
function showMenuFrame()
{	
//alert("showMenuFrame in reg");
	if(window.XMLHttpRequest) // Mozilla
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}
	//if (window.ActiveXObject)
	else
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}	
}
</script>
</head>
<body onLoad="setRadio();hideMenuFrame();">
<html:form action="/reports/DailyWardCensusSumRptCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
	<tag:tab tabLabel="Daily Ward Census&nbsp;(Summary Report)" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
		<tr class="HEADER">
			<td colspan="2">Daily Ward Census&nbsp;(Summary Report)</td>
		</tr>
		<!-- <tr >
    			<td class="LABEL" colspan="2">
    			<div align="right">
    				<html:radio property="strCase" name="dailyWardCensusSumRpt" value="1" onclick="getCombo(this);" >Consolidated</html:radio>
    				<html:radio property="strCase" name="dailyWardCensusSumRpt" value="2" onclick="getCombo(this);" >Date Wise</html:radio>
    			</div>
    			</td>
 		 </tr>  -->
 		 <tr>
		    <td class="LABEL" colspan="2">
		    <div align="right">
		   <%--  <html:radio property="strDateMonthWiseCase" name="dailyWardCensusSumRpt" value="1" onclick="changeRadioDateMonth(this);">Date Wise</html:radio>
		    <html:radio property="strDateMonthWiseCase" style='display:none;' name="dailyWardCensusSumRpt" value="2" onclick="changeRadioDateMonth(this);"><div style='display:none;'>Month Wise</div></html:radio>		     --%>
		    </div>
		    </td>
        </tr>
 		 <tr>
		    <td class="LABEL" colspan="2">
		    <div align="right">
		    <%-- <html:radio property="strCase" name="dailyWardCensusSumRpt" value="1" onclick="changeRadio(this);" disabled="true">Department</html:radio>--%>
		    <html:radio property="strCase" name="dailyWardCensusSumRpt" value="2" onclick="changeRadio(this);" >Ward</html:radio>
		    <%--<html:radio property="strCase" name="dailyWardCensusSumRpt" value="3" onclick="changeRadio(this);" disabled="true">Ward Type</html:radio>--%>
		    </div>
		    </td>
        </tr>
        <tr id='TRNEWBORN' style="display:none;">
			<td width="100%" class="LABEL" colspan="2">
			 <div align="right"> Only New Born <html:checkbox property="strNewBornOnly" name="dailyWardCensusSumRpt" value="1"></html:checkbox></div>
			</td>
		</tr>
        </table>
 		 <!--<tr>
		<td width="50%" class="LABEL" >
		</td>
		  <td width="50%" class="CONTROL" >
 	      <html:checkbox property="strGrandTotalRequired" name="dailyWardCensusSumRpt" onclick="getGrandTotal(this);">Whether Grand Total Required</html:checkbox>
 	      </td>
		</tr>
		 <tr>
		<td width="50%" class="LABEL" >
		</td>
		  <td width="50%" class="CONTROL" >
 	      <html:checkbox property="strDeptCheckRequired" name="dailyWardCensusSumRpt" value="1">Department Check Required</html:checkbox>
 	      </td>
		</tr> -->
	<div id="deptDivId" style="display:none">
      <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		<tr> 
    		<td width="50%" class="LABEL">Department</td>
    		<td width="50%" class="CONTROL"><select name="strDeptCode" class="comboNormal" onchange="getUnitCombo(this);" >
        	<bean:write name="dailyWardCensusSumRpt" property="strDeptValues" filter="false"/> </select> </td>
  		</tr>
  		<tr >
			<td width="50%" class="LABEL">Unit</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">All</option></select>
			</div>
			</td>			
		</tr>
  		<tr>
			 <td width="50%" class="LABEL">Ward</td>
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" >
    		<option value="0">All</option>
          	 </select>
          	 </div>
     		 </td>
		</tr>
		</table>
		</div>
		
	 <div id="wardDivId2" style="">
		 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  
		  	<tr> 
		    <td width="50%" class="LABEL">Ward</td>
		    <td width="50%" class="CONTROL"><div Id = "wardId2">
		    <select name="strWardCodeAll" class="comboNormal" onchange="">
		    <bean:write name="dailyWardCensusSumRpt" property="strWardAllValues" filter="false"/>
		     </select></div></td>
		  	</tr>
  	     </table>
        </div>
        
        <div id="wardTypeDivId2" style="display:none">
		 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  
		  	<tr> 
		    <td width="50%" class="LABEL">Ward Type</td>
		    <td width="50%" class="CONTROL"><div Id = "wardTypeId2">
		    <select name="strWardTypeVal" class="comboNormal" onchange="">
		    <bean:write name="dailyWardCensusSumRpt" property="strWardTypeVal" filter="false"/>
		     </select></div></td>
		  	</tr>
  	     </table>
        </div>
        
		<div id="datWiseDivId" style="">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
   		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${dailyWardCensusSumRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${dailyWardCensusSumRpt.strCurrentDate}" /></td>
		</tr>
		</table>
		</div>
		
		<div id="dateYearDivId" style="display:none;">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">  
		  		<tr>
					<td width="50%" class="LABEL"><font color="red">*</font>Month</td>
					<td width="50%" class="CONTROL">
					<select class="comboNormal" name="strMonthValue">
					<option value="Jan">January</option>
					<option value="Feb">February</option>
					<option value="Mar">March</option>
					<option value="Apr">April</option>
					<option value="May">May</option>
					<option value="Jun">June</option>
					<option value="Jul">July</option>
					<option value="Aug">August</option>
					<option value="Sep">September</option>
					<option value="Oct">October</option>
					<option value="Nov">November</option>
					<option value="Dec">December</option>
					</select>
					</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font>Year</td>
					<td width="50%" class="CONTROL">
						<select  name="strYear" class="comboNormal">
					 		<bean:write name="dailyWardCensusSumRpt" property="strYearList" filter="false"/>
						</select>
					</td>
				</tr>
			</table>
		</div>
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr style="display:none">
			<td width="50%" class="LABEL">Report Date</td>
			<td width="50%" class="CONTROL">
			<dateTag:date name="strReportDate" value="${dailyWardCensusSumRpt.strCurrentDate}"></dateTag:date></td>			
		</tr>
  		<tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
			<select name="strReportFormat" class="comboSmall" >
			<option value="html">HTML</option>
			<option value="pdf">PDF</option>
			<option value="excel">EXCEL</option>
			</select>
			</td>			
		</tr>
		<tr>
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="dailyWardCensusSumRpt" value="1"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">User Remarks</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" >  
		<tr class="FOOTER">
			 <td colspan="1"><div align='left'><font size="2" color="red">#</font>Scheduler Based Report-Will Show Data Till Dates Less Than Current Date</div></td>
			 <td colspan="1"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();clearPage();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			</td>			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${dailyWardCensusSumRpt.strCurrentDate}"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>