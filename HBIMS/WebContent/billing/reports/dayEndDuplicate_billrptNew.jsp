<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Day End Report(Duplicate)</title>
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css"
	rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css"
	rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css"
	rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet"
	type="text/css">
<link
	href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>
	
	
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
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
	 	   var url="DayEndDuplicateRptBSCNT.cnt?hmode="+mode+"&fromDate="+temp+"&toDate="+temp1+"&billModuleId="+3+"&strUserMode="+temp3+"&hospCode="+document.forms[0].strHospitalCode.value;
	 	   
	 	  	 	   
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
	//$("summaryDateSearchId").load(_these,'summaryDateSearch','90','100');
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
	$(".modal-backdrop").eq(0).css({"display":"none"});

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
				var url = "DayEndDuplicateRptBSCNT.cnt?hmode="+hmode;
											
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

	 function modalDataTable(){
			$(document).ready(function() {
		$('#dtable').DataTable({
			  language: {
			    paginate: {
			      next: "<i class='fas fa-angle-right'>",
			      previous: "<i class='fas fa-angle-left'>"
			    }
			  },
		         
		          "scrollY":"200px",
		          "ordering": false,
		          "info":true,
		          "paging":true
		  
			});	
		             $(".dataTables_scrollHeadInner").css({"width":"100%"});
		             $(".table ").css({"width":"100%"});
		                $('#dtable_filter input').css('border-radius','5px');
		                
		                
			});
		}
	 function selectFunction(a) {
		    var printStr = document.getElementById("reportTypeId").options[0].value
		 	document.getElementById("reportTypeId").selectedIndex = a;
		 	 if(a == 0){
		 		$("#pdfId").addClass("changeImgGray");
		 		$("#htmlId").removeClass("changeImgGray");
		 		$("#excelId").removeClass("changeImgGray");
		 	}
		 	else if(a == 1) {
		 		$("#htmlId").addClass("changeImgGray");
		 		$("#pdfId").removeClass("changeImgGray");
		 		$("#excelId").removeClass("changeImgGray");
		    }
		 	else {
		 		$("#excelId").addClass("changeImgGray");
		 		$("#pdfId").removeClass("changeImgGray");
		 		$("#htmlId").removeClass("changeImgGray");
		    } 
		}

</script>
</head>
<body onload="onBodyLoad();">
<html:form action="/reports/DayEndDuplicateRptBSCNT.cnt" method="post">
	

		
			<div class="viewport" id="nonPrintable">
				<div class="container-fluid">

					<div class="errMsg" id="errMsg"><bean:write name="dayEndDuplicateRpt" property="strErrMsg"/></div>
                    <div class="normalMsg" id="normalMsg"><bean:write name="dayEndDuplicateRpt" property="strNormalMsg"/></div>
                    <div class="warningMsg" id="warningMsg"><bean:write name="dayEndDuplicateRpt" property="strWarningMsg"/></div>


					<div class="normalMsg" id="normalMsg"></div>
					<div class="row justify-content-center">
						<div class="col-sm-12">
						<br>
							<div class="prescriptionTile">

                         <div class="row rowFlex reFlex" >
									<div class="legend2" id='nonPrintableLegend2'>
										<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
											<i class="fas fa-ban iround"  title="Cancel"></i>
										</button>	
										<button type="button" class=" btn btn-secondary btn-circle" onclick="document.forms[0].reset();" style="background: #b9b9b9; border-color:#b9b9b9;margin-top: 0.25rem !important;">
														 	<img  src="/HIS/hisglobal/images/clear3.png" title="Clear" style="width: 23px; color: #fff;">
														</button>
									    <button  type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' onClick="return validate();" name="patientAdmissionModiTransBean"  style="background-color: #5cb85c;">					
											<i class="fa fa-download iround"  title="Generate" ></i>
										</button>
										
									   
																		                 
									  </div> 
									</div>
	 								
	                                    <div class="row rowFlex reFlex" >
                                           <div class="col-sm-12">
                                          <p class="subHeaders"><button  type="button" class="btn btn-outline-success btn-circle1 " >
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button>&nbsp;Scroll/Day End Report(Duplicate)</p>
                                          
                                          </div>
                                        
                                          </div>
             
		                   <div class="row rowFlex reFlex" >
								<div class="col-sm-2">
									<label>Hospital Name</label>
								</div>
								<div class="col-sm-3">
											<select name="strHospitalCode" onclick="getCombo1(this);"
											class='browser-default custom-select'>
											<bean:write name="dayEndDuplicateRpt"
												property="strHospNameValues" filter="false" />
										</select>
											</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<font color="red">*</font>Scroll No.
								</div>
								<div class="col-sm-2">
										<input class="form-control" type="text" name="strSummaryNo" disabled="disabled">
								</div>
								<div class="col-sm-2">

											<span class="fas fa-search"
												style="cursor: pointer; cursor: hand;padding-top: 10px;"
												title="SummaryNo Search"
												onkeypress="return validateData(event,5);"
												data-toggle="modal" data-target="#searchModel"
												onclick="displayPopUp(this);"></span>
										</div>
							</div>
                        
                  
		
 	 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 	 
 	 	<tr style="display:none">
			<td width="50%" class="LABEL"  align="center">Details Required</td>
			<td width="50%" class="CONTROL" >
				<html:checkbox property="strIsDetailsRequired" value="1" name="dayEndDuplicateRpt" onclick="getVisibilityDetails(this);" ></html:checkbox>
			</td>
		</tr>
		
		</table>
		
		                <div id="paymentDetailsRequiredDivId">	
		                      <div class="row rowFlex reFlex">
										<div class="col-sm-2">
											<label>Is Payment Mode Required</label>
										</div>
										<div class="col-sm-3">
											<html:checkbox property="strIsPayModeRequired" value="1" name="dayEndDuplicateRpt" ></html:checkbox>
										</div>
										<div class="col-sm-1"></div>
									
										<div class="col-sm-2" >
											<label>Is Payment Detail Required</label>
										</div>
										<div class="col-sm-3">
                                            <html:checkbox property="strIsPayDetailRequired" value="1" name="dayEndDuplicateRpt" ></html:checkbox>
										</div>
										<div class="col-sm-1"></div>
									</div>			
	                        	</div>
	
		
		                         <div class="row rowFlex reFlex" style='display:none;'>
										<div class="col-sm-4"></div>
										<div class="col-sm-2">
											<label>Footer Required</label>
										</div>
										<div class="col-sm-2">
											<html:checkbox property="strIsFooter" name="dayEndDuplicateRpt"
												value="1"></html:checkbox>
										</div>
										<div class="col-sm-4"></div>
									</div>
									<div class="row rowFlex reFlex" >
										<div class="col-sm-2" align="right">
											<label>User Remarks</label>
										</div>
										<div class="col-sm-3">
											<input class="form-control" type="text" name="strUserRemarks">
										</div>
										<div class="col-sm-7"></div>
									</div>

	                       	<br>
							<div class="row rowFlex reFlex">
								<div class="col-sm-12" align="right">

									<img src="/HBIMS/hisglobal/images/html-512.png" id="htmlId"
										class="changeImg changeImgGray" alt="Html Report"
										onclick="selectFunction(1)" tabindex="0" selected="selected"
										style="width: 40px; color: #fff;" title="Html">
									&nbsp;&nbsp;&nbsp;<img  src="/HBIMS/hisglobal/images/pdf-512.png" title="Pdf"
										id="pdfId" class="changeImg" alt="Acrobat Reader"
										onclick="selectFunction(0)" tabindex="0" style="width: 40px; color: #fff;">
									&nbsp;&nbsp;&nbsp;<img src="/HBIMS/hisglobal/images/excel-521.png"
										title="Excel" id="excelId" class="changeImg"
										alt="Excel Report" onclick="selectFunction(2)" tabindex="0"
										style="width: 40px; color: #fff;"> 
										<select name="strReportFormat" id="reportTypeId"
										class="form-control validatebox-text" style="display: none;">
										<option value="pdf">Acrobat Reader</option>
										<option value="html" selected="selected">HTML</option>
										<option value="excel">Excel</option>
									    </select>
								</div>
								</div>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${dayEndDuplicateRpt.strCurrentDate}"/>
<input type="hidden" name="strBillModuleId" value="${dayEndDuplicateRpt.strBillModuleId}"/>
<input type="hidden" name="strUserMode" value="${dayEndDuplicateRpt.strUserMode}"/>
<input type="hidden" name="strPrintCount" value="${dayEndDuplicateRpt.strPrintCount}">
<input type="hidden" name="strDayEndDate"/>

<div style="display: none">
<input type="text" name="s">
</div>
</div>
</div>
</div>
</div>
</div>
</fieldset>
<div id="summaryDateSearch">
<div id="searchModel" class="modal" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
      <h4 class="modal-title">Scroll Details</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body" id="summaryDateSearchId">
						 <div class="row rowFlex reFlex">
											<div class="col-sm-2">
												<label><font color="red">*</font>From Date</label>
											</div>
											<div class="col-sm-3">
												<input id='datepicker' name="strFromDate"
													class="form-control">
											</div>
										
											<div class="col-sm-2">
												<label><font color="red">*</font>To Date</label>
											</div>
											<div class="col-sm-3">
												<input id='datepicker1' name="strToDate"
													class="form-control">
											</div>
											<div class="col-sm-2">
											<img style="cursor:pointer;" title="Summary Popup" align="top" src ="../../hisglobal/images/Go.png" name="go" onclick="return getASummaryDetails();" >
										</div>
										</div>	     		     
		     <div id="summaryDtlId"></div>		     
		
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onClick='onOKButton();' >Ok</button>
      </div>
    </div>

  </div>
</div>
</div>
</html:form>
<tag:autoIndex></tag:autoIndex>
<script type="text/javascript">
		$('#datepicker').datepicker({
			modal : true,
			header : true,
			footer : true,
			format : 'dd-mmm-yyyy'
		});
		$('#datepicker1').datepicker({
			modal : true,
			header : true,
			footer : true,
			format : 'dd-mmm-yyyy'
		});
		var today = new Date();
		var arr = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
				"Sep", "Oct", "Nov", "Dec" ];
		var mmm = arr[today.getMonth()];
		var hrs = today.getHours();
		var dd = today.getDate() + "-" + mmm + "-" + today.getFullYear();
		$('#datepicker1').val(dd);
		$('#datepicker').val(dd);
		
		
	</script>

</body>
</html>