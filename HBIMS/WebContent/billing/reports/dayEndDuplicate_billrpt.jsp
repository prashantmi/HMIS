<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Day End Report(Duplicate)</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">



function getVisibilityDetails(chkObj){

	document.forms[0].strIsPayModeRequired.checked = false;
	document.forms[0].strIsPayDetailRequired.checked = false;

	if(chkObj.checked){
		
		document.getElementById("paymentDetailsRequiredDivId").style.display = "block";			
		
	}else{
	
		document.getElementById("paymentDetailsRequiredDivId").style.display = "none";
	
	}


}


function GetIndex(index,endVal)
  {
   
          for(var i = 1; i <= endVal ; i++)
		  {
		    document.getElementById("DivId"+i).style.display="none";
		  }
	
	
	initObj = document.getElementsByName("linId");
		
		if(initObj.length > 0){
			
			for(var i = 0 , len = initObj.length ; i < len ; i ++ ){
				
				initObj[i].style.color = "blue";
			}
			
	}
			
		obj = document.getElementById("linId"+index);
		if(obj != null) obj.style.color = "red";
	
	
	
		  document.getElementById("DivId"+index).style.display="block";
	
			 
  }



  
function dateDiff(date_1,date_2){
 
	var retVal=compareDate(date_1,date_2);

	if(retVal.mode==0){
			var ret=parse_Date(date_1,"-");
			var ret1=parse_Date(date_2,"-");
			var dt1=ret.month+"-"+ret.day+"-"+ret.year;
			var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
			date1 = new Date();
			date2 = new Date();
			diff  = new Date();
 	
 
			var myDate1=new Array();
			myDate1=dt1.split("-");
			date1temp = new Date(myDate1[2],myDate1[0],myDate1[1]);
			date1.setTime(date1temp.getTime());

  
			var myDate2=new Array();
			myDate2=dt2.split("-");
			date2temp = new Date(myDate2[2],myDate2[0],myDate2[1]); 		
			date2.setTime(date2temp.getTime());
 
 
		diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
		timediff = diff.getTime();

		weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
		timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
		days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
		timediff -= days * (1000 * 60 * 60 * 24);
		days=parseInt(weeks)*7+days;
 
 		return days;
 
	}
}
	  
function parse_Date(dtStr,seprator)
{
	var pos1,pos2,tempLen=0;
	var tempStr = "";
	var intDay = 0,intMon = 0,intYear = 0;
	var retValue = false;
	pos1 = dtStr.indexOf(seprator);
	pos2 = dtStr.indexOf(seprator,pos1+1);
	if(pos1 > 0 && pos2 > (pos1 + 1))
	{
		//day
		tempStr = dtStr.substring(0,pos1);
		tempLen = tempStr.length;
		if(tempLen > 0 && tempLen <=2)
		{
			intDay = parseInt(tempStr,'10');
			//month
			tempStr = dtStr.substring(pos1+1,pos2);
			tempLen = tempStr.length;
			if(tempLen > 0 && tempLen <= 3)
			{
				//format given as dd/mmm/yyyy
				if(tempLen == 3) tempStr = get_MonthInt(tempStr);	
				intMon = parseInt(tempStr,'10');
				if(intMon > 0) 
				{
					//year
					tempStr = dtStr.substring(pos2+1);
					tempLen = tempStr.length;
					if(tempLen == 4)
					{
						intYear = parseInt(tempStr,'10');
						if (intYear >= 1900 && intYear <= 9900)
						{
							if (intMon >= 1 && intMon <= 12)
							{
								if (intDay > 0 && intDay <= Days_InMonth(intMon, intYear))
									retValue = true;
							}
						}
					}
				}
			}
		}
	}
	
	return {cancelKey :retValue,day:intDay,month:intMon,year:intYear};
}
	
	
	
//this is internal function that converts the month(in string) into month(in integer)
function get_MonthInt(str)
{
	var month = "0";
	
	switch(str.toUpperCase())
	{
		case "JAN":
			month = "1";
			break;
		case "FEB":
			month = "2";
			break;
		case "MAR":
			month = "3";
			break;
		case "APR":
			month = "4";
			break;
		case "MAY":
			month = "5";
			break;
		case "JUN":
			month = "6";
			break;
		case "JUL":
			month = "7";
			break;
		case "AUG":
			month = "8";
			break;
		case "SEP":
			month = "9";
			break;
		case "OCT":
			month = "10";
			break;
		case "NOV":
			month = "11";
			break;
		case "DEC":
			month = "12";
			break;
	}
	return month;
}

//this is internal function that returns no of days in a month for the specified year
function Days_InMonth(mon, year) 
{
	var retVal;
	
	retVal = 31;
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {retVal = 30;}
	if (mon == 2) {retVal = days_InFebruary(year);}
   	return retVal;
}

//this is internal function that returns day in feb month for specified year
function days_InFebruary (year)
{
	return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
	   
	  


function validate()
{
	var hisValidator = new HISValidator("dayEndDuplicateRpt");	
	hisValidator.addValidation("strSummaryNo", "req","Summary No. is a mandatory field");
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	
	if(retVal)
	{
	
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value != "pdf")
		{
			document.forms[0].target = "_self";
		}
		else
		{
			document.forms[0].target = "_blank";
		}
		document.forms[0].strSummaryNo.disabled = false;
		document.forms[0].submit();
	}
	else
	{
			return false;
	}
}

function getASummaryDetails(){    
              
           if(document.getElementById('summaryDtlId').style.display == 'none'){
           		document.getElementById('summaryDtlId').style.display = 'block';
           }
           
           
           var hisValidator = new HISValidator("dayEndDuplicateRpt");
	
		hisValidator.addValidation("strHospitalCode", "dontselect=0","From Date must be a Valid Date");
		hisValidator.addValidation("strFromDate", "date","From Date must be a Valid Date");
		hisValidator.addValidation("strToDate", "date","to Date must be a Valid Date");
		hisValidator.addValidation("strToDate", "dtltet="+document.forms[0].strCurrentDate.value ,"To Date Should be Lesser than or Equal to Current Date");
		hisValidator.addValidation("strFromDate", "dtltet="+document.forms[0].strToDate.value ,"From Date Should be Lesser than or Equal to To Date");
			
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	
	 	
	if(retVal){
           
        var noOfDay =  dateDiff(document.forms[0].strFromDate.value , document.forms[0].strToDate.value  )
           
           
           if(parseInt(noOfDay)> 29  ){
           
           		alert("The Date Difference should be Less than or Equal to 30");
           		return false;
           }
           
           
           var temp = document.forms[0].strFromDate.value;
           var temp1 = document.forms[0].strToDate.value;
           var temp2 = document.forms[0].strBillModuleId.value;
           var temp3 = document.forms[0].strUserMode.value;
                     
           var mode="GETSUMMARYDETAILS";
	 	   var url="DayEndDuplicateRptCNT.cnt?hmode="+mode+"&fromDate="+temp+"&toDate="+temp1+"&billModuleId="+temp2+"&strUserMode="+temp3+"&hospCode="+document.forms[0].strHospitalCode.value;
	 	   
	 	  	 	   
		   ajaxFunction(url,"1");
           
           
    }else{
		return false;
		}
           
	  
}


function getAjaxResponse(res,mode){
	
	if(mode == '1')	
	{	
		var objVal = document.getElementById("summaryDtlId");
		objVal.innerHTML = res;
		
	}
	
}	


function hideSummDetails(divId)
{
      hide_popup_menu(divId);
}

function displayPopUp(_these)
{
	if(document.forms[0].strHospitalCode.value == "0")
	{
			alert("Hospital Name is a mandatory field");
			return false;
	}
	var objVal = document.getElementById("summaryDtlId");
	objVal.innerHTML = "";
	display_popup_menu(_these,'summaryDateSearch','90','100');
}

function onOKButton()
{
	var radioArray = document.getElementsByName('strSummNoVal');
	var strScrollDateArr = document.getElementsByName('strScrollDate');		
	if(radioArray.length <= 0)
	{	
		alert("No Summary Details Available");
		return false;	
	}	
	var count = 0;	
	for(var i = 0; i < radioArray.length; i++)
	{
		if(radioArray[i].checked == true)
		{
			var radioVal = radioArray[i].value;
			count = 1;
			var strDayEndDateVal=strScrollDateArr[i].value;
			break;
		}	
	}	
	if(count == 0)
	{	
		alert("Please Select a Summary No.");
		return false;	
	}	
	
	document.forms[0].strSummaryNo.value = radioVal;
	document.forms[0].strDayEndDate.value=strDayEndDateVal;
	hideSummDetails('summaryDateSearch');
	//document.forms[0].strSummaryNo.disabled = true;
}


function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	 	document.forms[0].target = "_self";
		document.forms[0].submit();

}

function onClearButton(){

	document.getElementById('summaryDtlId').style.display = "none";
	document.forms[0].strSummaryNo.value = "";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	//document.forms[0].strSummaryNo.disabled = false;
}


 var gblPrintLimitVal = 0;
 
function onBodyLoad(){

		var normalMsgVal = document.getElementById("normalMsg").innerHTML;	
				
			if(normalMsgVal.length > 1){
	
				var confirmFlag = confirm("Whether Day End Report Printed Successfully.\nIf Not Click the Cancel button to Re-Print");
				
				if(!confirmFlag){
							 
					 var printLimit = document.forms[0].strPrintCount.value;
								
									if(printLimit.length <0) printLimit = "0";
									
									printLimit = parseInt(printLimit);
										
										if(printLimit > 0){
											
											gblPrintLimitVal = printLimit;
																			
											reprintContents();
										}
										
				}

		}

}


/**
	 * reprintContents
	 */
	 function reprintContents() {
	 	
	 		 	
	 		if(gblPrintLimitVal != 0){
	 			
	 			var hmode = "REPRINT"; 
				var url = "DayEndDuplicateRptCNT.cnt?hmode="+hmode;
											
				ajaxFunction2(url,"1","rePrintStatus");	
	 			
	 		} else{
	 		
	 			alert("Sorry, No More Re-Print");
	 		
	 		}
	 		
	 		
	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 function rePrintStatus() {
	 	
	 	gblPrintLimitVal = gblPrintLimitVal - 1;
	 	
	 		if(gblPrintLimitVal != 0){
	 	
	 			var confirmFlag = confirm("Whether Day End Report Printed Successfully.\nIf Not Click the Cancel button to Re-Print");
				
				if(!confirmFlag){
							 
					 	reprintContents();
										
				}
				
				} 

	 	
	 }			


 
</script>
</head>
<body onload="onBodyLoad();">
<html:form action="/reports/DayEndDuplicateRptCNT.cnt" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="dayEndDuplicateRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="dayEndDuplicateRpt" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="dayEndDuplicateRpt" property="strWarningMsg"/></div>

	<tag:tab tabLabel="Scroll/Day End Report(Duplicate)" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
             
            
               
	   <div class='popup' id='summaryDateSearch' style="display:none">
			<table width='600' border="0" cellspacing ="0px">
				<tr class="HEADER"> 
					<th colspan='5' align='left'></th>
					<th align='right'><img  style='cursor:pointer;' src='../../hisglobal/images/stop.png'
						onClick="hideSummDetails('summaryDateSearch');"></th>
			    </tr>
			 </table>			 
			 <table width='600' border="0" cellspacing ="1px" cellpadding="1px">
				<tr>
					<td width="20%" class="LABEL"><font color="red">*</font> From Date</td>
					<td width="25%" class="CONTROL"><dateTag:date name="strFromDate" value="${dayEndDuplicateRpt.strCurrentDate}" /></td>
					<td width="20%" class="LABEL"><font color="red">*</font> To Date</td>
					<td width="25%" class="CONTROL"><dateTag:date name="strToDate" value="${dayEndDuplicateRpt.strCurrentDate}" /></td>
					<td width="10%" class="CONTROL">
						<img style="cursor:pointer;" title="Summary Popup" align="top" src ="../../hisglobal/images/Go.png" name="go" onclick="return getASummaryDetails();" >
					</td>					
				</tr>				
		     </table>		     		     
		     <div id="summaryDtlId"></div>		     
		     <table width='600' align='center' cellspacing='1px'>
				<tr class='FOOTER'>
					<td colspan='6'></td>
				</tr>
				<tr>
					<td colspan='6' align='center' >
						<img align='center' style='cursor: hand;cursor: pointer' src='../../hisglobal/images/ok.gif' onClick='onOKButton();'>
					</td>
				</tr>				
			</table>
		</div>          
             
                  
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
			<tr class="HEADER">
				<td colspan="2">Scroll/Day End Report(Duplicate)</td>
			</tr>
			<tr>
				<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
				<td width="50%" class="CONTROL">
					<select name="strHospitalCode" class="comboNormal" >
					<bean:write name="dayEndDuplicateRpt" property="strHospNameValues" filter="false"/>
					</select>
				</td>			
			</tr>	 	 	
	 	  	<tr> 
			    <td width="50%" class="LABEL"><font color="red">*</font>Scroll No.</td>
			    <td width="50%" class="CONTROL">
			        <input class="txtFldMax" type="text" name="strSummaryNo" disabled="disabled">
			        <img style="cursor:pointer;"  src="../../hisglobal/images/viewDetails.gif"  onkeypress="return validateData(event,5);"
		        title="SummaryNo Search"  onclick="displayPopUp(this);"/>
		        </td>
		        
	 	 	</tr>
	  </table>
 	 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 	 
 	 	<tr style="display:none">
			<td width="50%" class="LABEL"  align="center">Details Required</td>
			<td width="50%" class="CONTROL" >
				<html:checkbox property="strIsDetailsRequired" value="1" name="dayEndDuplicateRpt" onclick="getVisibilityDetails(this);" ></html:checkbox>
			</td>
		</tr>
		
		</table>
		
		<div id="paymentDetailsRequiredDivId">			
			<table cellpadding="1px" cellspacing="1px"  class="TABLEWIDTH" align="center">
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">Is Payment Mode Required</td>
					<td width="50%" class="CONTROL" colspan="2">
					<html:checkbox property="strIsPayModeRequired" value="1" name="dayEndDuplicateRpt" ></html:checkbox>
					</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL" colspan="2" align="center">Is Payment Detail Required</td>
					<td width="50%" class="CONTROL" colspan="2">
					<html:checkbox property="strIsPayDetailRequired" value="1" name="dayEndDuplicateRpt" ></html:checkbox>
					</td>
				</tr>
			</table>				
		</div>
		
		<table cellpadding="1px" cellspacing="1px"  class="TABLEWIDTH" align="center">		 
			<tr>
				<td width="50%" class="LABEL">Report Format</td>
				<td width="50%" class="CONTROL">
					<select name="strReportFormat"  onchange="">
					<option value="html">HTML</option>
					<option value="pdf">PDF</option>
					<option value="excel">Excel</option></select>
				</td>			
			</tr>		
			<tr style='display:none;'>
				<td width="50%" class="LABEL">Footer Required</td>
				<td width="50%" class="CONTROL">
					<html:checkbox property="strIsFooter" name="dayEndDuplicateRpt" value="1"></html:checkbox>
				</td>			
			</tr>
			<tr>
				<td width="50%" class="LABEL">User Remarks</td>
				<td width="50%" class="CONTROL">
					<input class="txtFldMax" type="text" name="strUserRemarks" >
				</td>			
			</tr>
			<tr class="FOOTER">
				 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
			</tr>
		</table>
		<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
			<tr>
				<td align="center">
					<!-- <img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
					<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="onClearButton();" >
					<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
					 -->
					<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</td>			
			</tr>
		</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${dayEndDuplicateRpt.strCurrentDate}"/>
<input type="hidden" name="strBillModuleId" value="${dayEndDuplicateRpt.strBillModuleId}"/>
<input type="hidden" name="strUserMode" value="${dayEndDuplicateRpt.strUserMode}"/>
<input type="hidden" name="strPrintCount" value="${dayEndDuplicateRpt.strPrintCount}">
<input type="hidden" name="strDayEndDate"/>

<div style="display: none">
<input type="text" name="s">
</div>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>