<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Discount Detail Report</title>

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




<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">



function validate()
{
	var hisValidator = new HISValidator("discountDtlRpt");

	hisValidator.addValidation("strHospitalCode", "dontselect=0","Hospital Name is a mandatory field");
	//hisValidator.addValidation("strHospService", "dontselect=0","Hospital Service is a mandatory field");
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if(retVal)
	{
	
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html"){
			
			document.forms[0].target = "_self";
		}else{
			document.forms[0].target = "_blank";
		}

		document.forms[0].submit();
		}else{
		return false;
		}
}
function getConsultantCombo(obj){ 

 		var url="DiscountDetailsRptBSCNT.cnt?hmode=CONSULTANTCMB&hospCode="+obj.value;
 		ajaxFunction(url,"1");
		
	}
function getHospitalServiceCombo(obj)
{ 
 		var url;
 		url="DiscountDetailsRptBSCNT.cnt?hmode=HOSPSERVICECMB&hospCode="+obj.value;
		ajaxFunction(url,"2");
}


function getAjaxResponse(res,mode)
{

if(mode=="1")
	{   
		var objVal= document.getElementById("consultDivId2");
		objVal.innerHTML = "<select name ='strConsult' class='browser-default custom-select'>" + res + "</select>";
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
		//document.forms[0].strHospitalCode.value = "0"
		document.getElementById("consultDivId").style.display = "";
		//document.getElementById("consultDivId2").innerHTML= "<select name ='strConsult' class='browser-default custom-select'><option value='0'>All</option></select>";
		document.getElementById("reasonDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "";	
	}
	else if(obj.value == 2)
	{	
		document.forms[0].strHospitalCode.value = "0"
		document.getElementById("consultDivId").style.display = "none";
		document.getElementById("reasonDivId").style.display = "";
		document.getElementById("dateDivId").style.display = "";
	}
	else if(obj.value == 3)
	{	
		//document.forms[0].strHospitalCode.value = "0"
		document.getElementById("consultDivId").style.display = "none";
		document.getElementById("reasonDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "";	
	}
}
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

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
<body
	onload="getCombo(document.forms[0].strCase);getConsultantCombo(document.forms[0].strHospitalCode);">
	<html:form action="/reports/DiscountDetailsRptBSCNT.cnt" method="post">
		
			<div class="viewport" id="nonPrintable">
				<div class="container-fluid">

					<div class="errMsg" id="errMsg">
						<bean:write name="discountDtlRpt" property="strErrMsg" />
					</div>
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
                                          <div class="col-sm-10">
                                          <p class="subHeaders"><button  type="button" class="btn btn-outline-success btn-circle1 " >
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button>&nbsp;Discount Detail Report</p>
                                          <!-- <button  type="button" class="btn btn-outline-success mt-1  savebtn" onclick="cancelFunc();">
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button> -->
                                          </div>
                                        <div class="col-sm-1">
                                          <div class="custom-control custom-radio">
                                                   <input type="radio" id='customRadio' class="custom-control-input" checked="checked" name="strCase" value="1" onclick="getCombo(this);">
                                                    <label class="custom-control-label" for="customRadio">Consultant</label>
                                                 </div>
                                          </div>
                                          <div class="col-sm-1">
                                          <div class="custom-control custom-radio">
                                                   <input type="radio" id='customRadio1' class="custom-control-input" name="strCase" value="3" onclick="getCombo(this);">
                                                    <label class="custom-control-label" for="customRadio1">Date</label>
                                                 </div>
                                          </div>
                                          </div>
                                          
                             
									
									 <div class="row rowFlex reFlex">
								<div class="col-sm-2">
									<label>Hospital Name</label>
								</div>
								<div class="col-sm-3">
									<select name="strHospitalCode" onchange="getCombo1(this);"
										class='browser-default custom-select'>
										<bean:write name="discountDtlRpt"
											property="strHospNameValues" filter="false" />
									</select>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Hospital Service</label>
								</div>
								<div class="col-sm-3">
										<select name="strHospSerName"
											class="browser-default custom-select"
											onchange="getGroupCombo(this);">
											<bean:write name="discountDtlRpt"
												property="strHospSerValues" filter="false" />
										</select>
								</div>
								<div class="col-sm-1"></div>
							</div>
									
									
							
							<div class="row rowFlex reFlex" id="consultDivId">
								<div class="col-sm-2">
									<label>Consultant Name</label>
								</div>
								<div class="col-sm-3">
									<div id="consultDivId2">
													<select name="strConsult"
														class="browser-default custom-select">
														<option value="0">Select Value</option>
														<bean:write name="discountDtlRpt"
															property="strConsultValues" filter="false" />
													</select>
												</div>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>User Remarks</label>
								</div>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="strUserRemarks">
								</div>
								<div class="col-sm-1"></div>
							</div>
							
							
									<div id="dateDivId" style="display:;">
								<div class="row rowFlex reFlex">
									<div class="col-sm-2">
										<label><font color="red">*</font>From Date</label>
									</div>
									<div class="col-sm-3">
										<input id='datepicker' name="strEffectiveFrom"
											class="form-control"
											style="color: rgba(113, 111, 111, 0.87);">
									</div>
									<div class="col-sm-1"></div>

									<div class="col-sm-2">
										<label><font color="red">*</font>To Date</label>
									</div>
									<div class="col-sm-3">
										<input id='datepicker1' name="strEffectiveTo"
											class="form-control"
											style="color: rgba(113, 111, 111, 0.87);">
									</div>
									<div class="col-sm-1"></div>
								</div>
							</div>
							
							
									<div id="reasonDivId" style="display: none">

										<div class="row rowFlex reFlex">
											<div class="col-sm-4"></div>
											<div class="col-sm-2">
												<label>Reason</label>
											</div>
											<div class="col-sm-2">
												<select name="strReason"
													class="browser-default custom-select">
													<bean:write name="discountDtlRpt"
														property="strReasonValues" filter="false" />
												</select>
											</div>
											<div class="col-sm-4"></div>
										</div>
									</div>
									
									<div class="row rowFlex reFlex" style="display: none;">
										<div class="col-sm-4"></div>
										<div class="col-sm-2">
											<label>Footer Required</label>
										</div>
										<div class="col-sm-2">
											<html:checkbox property="strIsFooter" name="discountDtlRpt"
												value="1"></html:checkbox>
										</div>
										<div class="col-sm-4"></div>
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
								<input type="hidden" name="hmode" /> <input type="hidden"
									name="strCurrentDate" value="${discountDtlRpt.strCurrentDate}" />
							</div>
						</div>
					</div>
				</div>
			</div>
	</html:form>
	<tag:autoIndex></tag:autoIndex>
	
	<script type="text/javascript">
	$('#datepicker').datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	$('#datepicker1').datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	var today=new Date();
	var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var mmm=arr[today.getMonth()];
	var hrs=today.getHours();
	var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
	$('#datepicker1').val(dd);
	$('#datepicker').val(dd);
	</script>
	
</body>
</html>