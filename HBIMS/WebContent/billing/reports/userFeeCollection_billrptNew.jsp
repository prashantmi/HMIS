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
<title>Refund Detail Report</title>
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
	function multiSelHosp() {

		var tempObj = document.getElementsByName('strHospitalCode')[0];
		var countSel = 0;

		for (var i = 0; i < tempObj.options.length; i++) {
			if (tempObj.options[i].selected) {
				countSel += 1;
			}
		}

		return countSel;

	}

	function validate() {
		var tempObj = document.getElementsByName('strHospitalCode')[0];
		var countSel = 0;
		var allHospCodes = 'testCode';
		countSel = multiSelHosp();
		var allSelFlag = '0'; // 0 all not sel,1 all sel

		//alert("sel val count is::"+countSel+"::"+tempObj.value);	
		if (countSel > 1)//multiple hospitals are selected and all is also selected..
		{
			for (var i = 0; i < tempObj.options.length; i++) {
				if (tempObj.options[0].selected)//All selected
				{

					tempObj.options[i].selected = false;
					if (allHospCodes == 'testCode') {
						allHospCodes = tempObj.options[i].value;
					} else {
						allHospCodes = allHospCodes + ','
								+ tempObj.options[i].value;
					}

					allSelFlag = '1';
				} else {
					if (tempObj.options[i].selected) {
						if (allHospCodes == 'testCode') {
							allHospCodes = tempObj.options[i].value;
						} else {
							allHospCodes = allHospCodes + ','
									+ tempObj.options[i].value;
						}
					}
				}
			}

			if (allSelFlag == '1') {
				tempObj.options[0].selected = true;

				alert("Cannot Proceed.\nEither All or multiple hospitals can be selected.Both the options cannot be exercised together.");

				return false;
			} else {

			}

		}

		if (countSel == 1)//either All or single hospital is selected..
		{
			for (var i = 0; i < tempObj.options.length; i++) {

				if (tempObj.options[0].selected)//All selected
				{
					if (allHospCodes == 'testCode') {

						allHospCodes = tempObj.options[i].value;
					} else {

						allHospCodes = allHospCodes + ','
								+ tempObj.options[i].value;
					}
				}
			}

		}

		if (allHospCodes == 'testCode') {
			document.getElementsByName('strAllHospCode')[0].value = 'testCode';

		} else {
			document.getElementsByName('strAllHospCode')[0].value = allHospCodes;
		}

		//alert("strAllHospCode::"+document.getElementsByName('strAllHospCode')[0].value);
		var hisValidator = new HISValidator("userFeeCollRpt");
		/*if(document.forms[0].strHospitalCode.value == "0")
		{
				alert("Hospital Name is a mandatory field");
				return false;
		} */
		hisValidator.addValidation("strEffectiveFrom", "date",
				"From Date is a mandatory field");
		hisValidator.addValidation("strEffectiveTo", "date",
				"To Date is a mandatory field");
		hisValidator.addValidation("strEffectiveTo", "dtltet="
				+ document.forms[0].strCurrentDate.value,
				"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strEffectiveTo", "dtgtet="
				+ document.forms[0].strEffectiveFrom.value,
				"Please Select To Date Greater Than Or Equal To From Date");
		var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();

		if (retVal) {
			document.forms[0].hmode.value = "SHOWRPT";
			if (document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html") {
				document.forms[0].target = "_self";
			} else {
				document.forms[0].target = "_blank";
			}
			document.forms[0].submit();
		} else {
			return false;
		}
	}

	function getCombo(obj) {
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].checked) {
				obj = obj[i];
				break;
			}
		}
		if (obj.value == 1) {
			//document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";
			document.forms[0].strCounterName.value = "0";
			document.getElementById("counterDivId").style.display = "";
			document.getElementById("conterDivId2").innerHTML = "<select name ='strCounterName' class='browser-default custom-select'><option value='0'>All</option></select>";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "";
		} else if (obj.value == 2) {
			document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";
			document.forms[0].strFeeClerkName.value = "0";
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "";
			document.getElementById("feeclerkDivId2").innerHTML = "<select name ='strFeeClerkName' class='browser-default custom-select'><option value='0'>All</option></select>";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "";
		} else if (obj.value == 3) {
			document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";
			document.forms[0].strDeptName.value = "0";
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "";
			document.getElementById("deptDivId2").innerHTML = "<select name ='strDeptName' class='browser-default custom-select'><option value='0'>All</optipn></select>";
			document.getElementById("dateDivId").style.display = "";
		} else if (obj.value == 4) {
			document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "";
		}
	}

	function getCombo1(obj1) {
		var obj = document.getElementsByName("strCase");
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].checked) {
				obj = obj[i];
				break;
			}
		}
		if (obj.value == 1) {
			//Counter	
			if (document.forms[0].strHospitalCode.value == "0") {

				var objVal = document.getElementById("conterDivId2");
				objVal.innerHTML = "<select name ='strCounterName' class='browser-default custom-select'><option value='0'>All</option></select>";
			} else {
				var url;
				url = "UserFeeCollectionRptBSCNT.cnt?hmode=CNTCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "1");
			}
		} else if (obj.value == 2) {//Cashier
			if (document.forms[0].strHospitalCode.value == "0") {
				var objVal = document.getElementById("feeclerkDivId2");
				objVal.innerHTML = "<select name ='strFeeClerkName' class='browser-default custom-select'><option value='0'>All</option></select>";
			} else {
				var url;
				url = "UserFeeCollectionRptBSCNT.cnt?hmode=CASHCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "2");
			}
		} else if (obj.value == 3) {//Department
			if (document.forms[0].strHospitalCode.value == "0") {
				var objVal = document.getElementById("deptDivId2");
				objVal.innerHTML = "<select name ='strDeptName' class='browser-default custom-select'><option value='0'>All</option></select>";
			} else {
				var url;
				url = "UserFeeCollectionRptBSCNT.cnt?hmode=DEEPTCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "3");
			}
		} else if (obj.value == 4) {//Date	
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "";
			document.getElementById("tariffCheckBoxDivId").style.display = "none";
			document.getElementById("tariffDivId").style.display = "none";
		}
	}

	function getAjaxResponse(res, mode) {
		if (mode == "1") {
			var objVal = document.getElementById("conterDivId2");
			objVal.innerHTML = "<select name ='strCounterName' class='browser-default custom-select'>"
					+ res + "</select>";
		}
		if (mode == "2") {
			var objVal = document.getElementById("feeclerkDivId2");
			objVal.innerHTML = "<select name ='strFeeClerkName' class='browser-default custom-select'>"
					+ res + "</select>";
		}
		if (mode == "3") {
			var objVal = document.getElementById("deptDivId2");
			objVal.innerHTML = "<select name ='strDeptName' class='browser-default custom-select'>"
					+ res + "</select>";
		}
	}
	function cancelPage() {
		document.forms[0].hmode.value = "CANCEL";
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
	onload="getCombo(document.forms[0].strCase);getCombo1(document.forms[0].strHospitalCode);">
	<html:form action="/reports/UserFeeCollectionRptBSCNT.cnt"
		method="post">

			<div class="viewport" id="nonPrintable">
				<div class="container-fluid">

					<div class="errMsg" id="errMsg">
						<bean:write name="userFeeCollRpt" property="strErrMsg" />
					</div>
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
                                          <div class="col-sm-8">
                                          <p class="subHeaders"><button  type="button" class="btn btn-outline-success btn-circle1 " >
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button>&nbsp;Refund Details Report</p>
                                          <!-- <button  type="button" class="btn btn-outline-success mt-1  savebtn" onclick="cancelFunc();">
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button> -->
                                          </div>
                                          <div class="col-sm-1">
                                          <div class="custom-control custom-radio">
                                                   <input type="radio" id='customRadio' class="custom-control-input" checked="checked" name="strCase" value="1" onclick="getCombo(this);">
                                                    <label class="custom-control-label" for="customRadio">Date</label>
                                                 </div>
                                          </div>
                                          <div class="col-sm-1">
                                          <div class="custom-control custom-radio">
                                                   <input type="radio" id='customRadio1' class="custom-control-input" name="strCase" value="2" onclick="getCombo(this);">
                                                    <label class="custom-control-label" for="customRadio1">Cashier</label>
                                                 </div>
                                          </div>
                                          <div class="col-sm-1">
                                          <div class="custom-control custom-radio">
                                                   <input type="radio" id='customRadio2' class="custom-control-input" name="strCase" value="3" onclick="getCombo(this);">
                                                    <label class="custom-control-label" for="customRadio2">Department</label>
                                                 </div>
                                          </div>
                                          <div class="col-sm-1">
                                          <div class="custom-control custom-radio">
                                                   <input type="radio" id='customRadio3' class="custom-control-input" name="strCase" value="4" onclick="getCombo(this);">
                                                    <label class="custom-control-label" for="customRadio3">Date</label>
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
										<bean:write name="userFeeCollRpt"
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
											<bean:write name="userFeeCollRpt"
												property="strHospSerValues" filter="false" />
										</select>
								</div>
								<div class="col-sm-1"></div>
							</div>
							
							
							
							
                            
									
									<div class="row rowFlex reFlex" id="counterDivId">
								<div class="col-sm-2">
									<label>Counter Name</label>
								</div>
								<div class="col-sm-3" id="conterDivId2">
									<select name="strCounterName"
										class="browser-default custom-select">
										<bean:write name="userFeeCollRpt"
											property="strCounterValues" filter="false" />
										<option value="0">All</option>
									</select>

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
							
								
									
					<div class="row rowFlex reFlex" id="feeclerkDivId" style="display: none">
								<div class="col-sm-2">
									<label>Fee Clerk Name</label>
								</div>
								<div class="col-sm-3" id="feeclerkDivId2">

									<select name="strFeeClerkName"
										class="browser-default custom-select">
										<bean:write name="userFeeCollRpt"
											property="strFeeClerkValues" filter="false" />
										<option value="0">All</option>
									</select>
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

									
                        <div class="row rowFlex reFlex" id="deptDivId"  style="display: none">
								<div class="col-sm-2">
									<label>Department Name</label>
								</div>
								<div class="col-sm-3" id="deptDivId2">
									<select name="strDeptName"
										class="browser-default custom-select">
										<bean:write name="userFeeCollRpt" property="strDeptValues"
											filter="false" />
										<option value="0">All</option>
									</select>
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
							
									<div class="row rowFlex reFlex" style="display: none;">
										<div class="col-sm-3"></div>
										<div class="col-sm-2">
											<label>Footer Required</label>
										</div>
										<div class="col-sm-3">
											<html:checkbox property="strIsFooter" name="userFeeCollRpt"
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
									name="strCurrentDate" value="${userFeeCollRpt.strCurrentDate}" />
								<input type="hidden" name="strAllHospCode"
									value="${paymentDtlRpt.strAllHospCode}" />

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