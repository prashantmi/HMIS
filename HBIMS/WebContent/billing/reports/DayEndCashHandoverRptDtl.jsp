<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
<script language="Javascript"
	src="../../hisglobal/js/validationBootstrap.js"></script>
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
		var hisValidator = new HISValidator("cashHandoverRpt");
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
			/*document.forms[0].hmode.value = "SHOWRPT";
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
			}*/
			if (document.getElementsByName("strTariffWise")[0].checked) {

				if (((document.getElementsByName("strTariffName")[0].value == '1040002') || (document
						.getElementsByName("strTariffName")[0].value == '1040001'))
						&& ((document.getElementsByName("strHospSerName")[0].value == '1') || (document
								.getElementsByName("strHospSerName")[0].value == '3'))) {
					alert("Only Ipd service can be availed for 'Poor free grant' or 'Poor free welfare fund'");
				} else {

					document.forms[0].hmode.value = "SHOWRPT";
					if (document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html") {

						document.forms[0].target = "_self";
					} else {
						document.forms[0].target = "_blank";
					}

					var obj = document.getElementsByName('strCase');
					for (var i = 0; i < obj.length; i++) {

						if (obj[i].checked) {
							obj = obj[i];
							break;
						}

					}
					if (obj.value == 9) {

						var obj = document
								.getElementsByName('strTariffSelection');
						for (var i = 0; i < obj.length; i++) {

							if (obj[i].checked) {
								obj = obj[i];
								break;
							}

						}

						if (obj.value == 2) {

							var count = selectListRecords("strRightMenuNames");
							if (count == 0) {
								alert("Please select atleast one Tariff Name !");
								return false;
							}

							var strSelectedTariffIds = "";
							for (var x = 0; x < document
									.getElementsByName("strRightMenuNames")[0].length; x++) {
								if (strSelectedTariffIds == "")
									strSelectedTariffIds = document
											.getElementsByName("strRightMenuNames")[0].options[x].value;
								else {

									strSelectedTariffIds = strSelectedTariffIds
											+ ","
											+ document
													.getElementsByName("strRightMenuNames")[0].options[x].value;
								}

							}

							// to ensure that no other tariff could be clubbed with 'porr free or poor welfare' in tariff+date..
							var tempArr = strSelectedTariffIds.split(",");
							var numOfTariffsAdded = 0;
							var poorFreeFound = false;

							for (var i = 0; i < tempArr.length; i++) {
								// poor free grant=1040002 and poor free welfare fund=1040001
								if ((tempArr[i] == "1040002")
										|| (tempArr[i] == "1040001")) {

									numOfTariffsAdded = tempArr.length;
									poorFreeFound = true;
									break;
								}
							}

							if (poorFreeFound) {
								if (numOfTariffsAdded > 1) {
									alert("No tariff can be clubbed with 'Poor free grant' or 'Poor free welfare fund'.");
									return false;
								}
							} else {
								//alert("proceed");
							}
							document.forms[0].strSelectedTariffIds.value = strSelectedTariffIds;
						}

					}

					document.forms[0].submit();
				}
			} else {
				document.forms[0].hmode.value = "SHOWRPT";
				if (document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html") {

					document.forms[0].target = "_self";
				} else {
					document.forms[0].target = "_blank";
				}

				var obj = document.getElementsByName('strCase');
				for (var i = 0; i < obj.length; i++) {

					if (obj[i].checked) {
						obj = obj[i];
						break;
					}

				}
				if (obj.value == 9) {

					var obj = document.getElementsByName('strTariffSelection');
					for (var i = 0; i < obj.length; i++) {

						if (obj[i].checked) {
							obj = obj[i];
							break;
						}

					}

					if (obj.value == 2) {

						var count = selectListRecords("strRightMenuNames");
						if (count == 0) {
							alert("Please select atleast one Tariff Name !");
							return false;
						}

						var strSelectedTariffIds = "";
						for (var x = 0; x < document
								.getElementsByName("strRightMenuNames")[0].length; x++) {
							if (strSelectedTariffIds == "")
								strSelectedTariffIds = document
										.getElementsByName("strRightMenuNames")[0].options[x].value;
							else
								strSelectedTariffIds = strSelectedTariffIds
										+ ","
										+ document
												.getElementsByName("strRightMenuNames")[0].options[x].value;
						}
						document.forms[0].strSelectedTariffIds.value = strSelectedTariffIds;
					}

				}

				document.forms[0].submit();
			}
		}
		///////////////

		else {

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
			//Counter	
			//document.forms[0].strHospitalCode.value = "0";
			/*document.forms[0].strHospSerName.value = "0";
			document.forms[0].strCounterName.value = "0";*/
			document.getElementById("counterDivId").style.display = "block";
			//document.getElementById("conterDivId2").innerHTML= "<select name ='strCounterName' class='comboNormal'><option value='0'>All</option></select>";    							
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "none";
			document.getElementById("tariffDateDivId").style.display = "none";
			document.getElementById("tariffDateDivId2").style.display = "none";
			document.getElementById("tariffCheckBoxDivId").style.display = "none";
			document.getElementById("tariffMulComboDivId").style.display = "none";
		} else if (obj.value == 2) {
			//Cashier
			/*document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";
			document.forms[0].strFeeClerkName.value = "0";*/
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "block";
			//document.getElementById("feeclerkDivId2").innerHTML= "<select name ='strFeeClerkName' class='comboNormal'><option value='0'>All</option></select>"; 
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "none";
			document.getElementById("tariffDateDivId").style.display = "none";
			document.getElementById("tariffDateDivId2").style.display = "none";
			document.getElementById("tariffCheckBoxDivId").style.display = "none";
			document.getElementById("tariffMulComboDivId").style.display = "none";
		} else if (obj.value == 3) {
			//Department
			/*document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";
			document.forms[0].strDeptName.value = "0";*/
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "block";
			//document.getElementById("deptDivId2").innerHTML= "<select name ='strDeptName' class='comboNormal'><option value='0'>All</optipn></select>";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDateDivId").style.display = "none";
			document.getElementById("tariffDateDivId2").style.display = "none";
			document.getElementById("tariffDivId").style.display = "none";
			document.getElementById("tariffMulComboDivId").style.display = "none";
			//document.getElementById("tariffCheckBoxDivId").style.display = "block";	
		} else if (obj.value == 4) {
			//Date
			/*document.forms[0].strHospitalCode.value = "0";
			document.forms[0].strHospSerName.value = "0";*/
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffCheckBoxDivId").style.display = "none";
			document.getElementById("tariffDateDivId").style.display = "none";
			document.getElementById("tariffDateDivId2").style.display = "none";
			document.getElementById("tariffDivId").style.display = "none";
			document.getElementById("tariffMulComboDivId").style.display = "none";
		} else if (obj.value == 5 && obj.checked) {//Is Tariff Wise	
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "block";
			//document.getElementById("deptDivId2").innerHTML= "<select name ='strDeptName' class='comboNormal'><option value='0'>All</optipn></select>";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "block";
			//document.getElementById("tariffDivId").innerHTML= "<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px' ><tr>"+ 
			//"<td width='50%' class='LABEL'>Tariff Name</td>"+
			//"<td width='50%' class='CONTROL'>"+
			//"<select name ='strTariffName' class='comboNormal'><option value='0'>All</optipn></select></td></tr></table>";
		} else if (obj.value == 5 && !obj.checked) {//Is Tariff Wise
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "block";
			//document.getElementById("deptDivId").innerHTML= "<select name ='strDeptName' class='comboNormal'><option value='0'>All</optipn></select>";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "none";
			//document.getElementById("tariffDivId").innerHTML= "<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px' ><tr>"+ 
			//"<td width='50%' class='LABEL'>Tariff Name</td>"+
			//"<td width='50%' class='CONTROL'>"+
			//"<select name ='strTariffName' class='comboNormal'><option value='0'>All</optipn></select></td></tr></table>";
		} else if (obj.value == 9) {//Tariff + Date
			document.getElementsByName("strTariffWise")[0].checked = false;
			//document.getElementsByName("strTariffWise")[1].checked=false;
			//document.getElementById("paymentDivId").style.display="none"; 
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "block";
			//document.getElementById("feeclerkDivId2").innerHTML= "<select name ='strFeeClerkName' class='comboNormal'><option value='0'>All</option></select>"; 
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("tariffDateDivId").style.display = "none";
			//document.getElementById("grpDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "none";
			//document.getElementById("tariffTypeDivId").style.display = "none";
			document.getElementsByName("strTariffWise")[0].checked = true;
			//document.getElementById("msgId").style.display = "none";
			document.getElementById("tariffDateDivId2").style.display = "block";
			document.getElementById("tariffMulComboDivId").style.display = "none";

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
		if (obj.value == 1) {//Counter	
			if (document.forms[0].strHospitalCode.value == "0") {
				var objVal = document.getElementById("conterDivId2");
				objVal.innerHTML = "<select name ='strCounterName' class='comboNormal'><option value='0'>All</option></select>";
			} else {

				/*document.forms[0].strHospitalCode.value = "0";
				document.forms[0].strHospSerName.value = "0";
				document.forms[0].strCounterName.value = "0";
				document.getElementById("counterDivId").style.display = "block";
				document.getElementById("feeclerkDivId").style.display = "none";
				document.getElementById("deptDivId").style.display = "none";
				document.getElementById("dateDivId").style.display = "block";
				document.getElementById("tariffDivId").style.display = "none";
				document.getElementById("tariffCheckBoxDivId").style.display = "none"; */

				var url;
				url = "DayEndCashHandoverRptCNT.cnt?hmode=CNTCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "1");

			}
		} else if (obj.value == 2) {//Cashier
			if (document.forms[0].strHospitalCode.value == "0") {
				var objVal = document.getElementById("feeclerkDivId2");
				objVal.innerHTML = "<select name ='strFeeClerkName' class='comboNormal'><option value='0'>All</option></select>";
			} else {
				/* document.forms[0].strHospitalCode.value = "0";
				document.forms[0].strHospSerName.value = "0";
				document.forms[0].strFeeClerkName.value = "0";
				document.getElementById("counterDivId").style.display = "none";
				document.getElementById("feeclerkDivId").style.display = "block";
				document.getElementById("deptDivId").style.display = "none";
				document.getElementById("dateDivId").style.display = "block";
				document.getElementById("tariffDivId").style.display = "none";
				document.getElementById("tariffCheckBoxDivId").style.display = "none";	*/

				var url;
				url = "DayEndCashHandoverRptCNT.cnt?hmode=CASHCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "2");
			}
		} else if (obj.value == 3) {//Department	
			if (document.forms[0].strHospitalCode.value == "0") {
				var objVal = document.getElementById("deptDivId2");
				objVal.innerHTML = "<select name ='strDeptName' class='comboNormal'><option value='0'>All</option></select>";
			} else {
				/* document.forms[0].strHospitalCode.value = "0";
				document.forms[0].strHospSerName.value = "0";
				document.forms[0].strDeptName.value = "0";
				document.getElementById("counterDivId").style.display = "none";
				document.getElementById("feeclerkDivId").style.display = "none";
				document.getElementById("deptDivId").style.display = "block";
				document.getElementById("dateDivId").style.display = "block";
				document.getElementById("tariffDivId").style.display = "none";
				document.getElementById("tariffCheckBoxDivId").style.display = "block";	*/

				var url;
				url = "DayEndCashHandoverRptCNT.cnt?hmode=DEEPTCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "3");
			}
		} else if (obj.value == 4) {//Date	
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "none";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffCheckBoxDivId").style.display = "none";
			document.getElementById("tariffDivId").style.display = "none";

		} else if (obj.value == 5 && obj.checked) {//Is Tariff Wise
			var url;
			url = "DayEndCashHandoverRptCNT.cnt?hmode=TARIFFCMB&hospCode="
					+ obj1.value;
			ajaxFunction(url, "4");
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "block";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "block";
		} else if (obj.value == 5 && !obj.checked) {//Is Tariff Wise
			document.getElementById("counterDivId").style.display = "none";
			document.getElementById("feeclerkDivId").style.display = "none";
			document.getElementById("deptDivId").style.display = "block";
			document.getElementById("dateDivId").style.display = "block";
			document.getElementById("tariffDivId").style.display = "none";
		} else if (obj.value == 9) {//Tariff+Date
			if (document.forms[0].strHospitalCode.value == "0") {
				var objVal = document.getElementById("feeclerkDivId2");
				objVal.innerHTML = "<select name ='strFeeClerkName' class='comboNormal'><option value='0'>All</option></select>";
			} else {
				/* document.forms[0].strHospitalCode.value = "0";
				document.forms[0].strHospSerName.value = "0";
				document.forms[0].strFeeClerkName.value = "0";
				document.getElementById("counterDivId").style.display = "none";
				document.getElementById("feeclerkDivId").style.display = "block";
				document.getElementById("deptDivId").style.display = "none";
				document.getElementById("dateDivId").style.display = "block";
				document.getElementById("tariffDivId").style.display = "none";
				document.getElementById("tariffCheckBoxDivId").style.display = "none";	*/

				var url;
				url = "DayEndCashHandoverRptCNT.cnt?hmode=CASHCMB&hospCode="
						+ obj1.value;
				ajaxFunction(url, "2");
			}
		}
	}
	function getAjaxResponse(res, mode) {
		if (mode == "1") {
			var objVal = document.getElementById("conterDivId2");
			objVal.innerHTML = "<select name ='strCounterName' class='comboNormal'>"
					+ res + "</select>";
		}
		if (mode == "2") {
			var objVal = document.getElementById("feeclerkDivId2");
			objVal.innerHTML = "<select name ='strFeeClerkName' class='comboNormal'>"
					+ res + "</select>";
		}
		if (mode == "3") {
			var objVal = document.getElementById("deptDivId2");
			objVal.innerHTML = "<select name ='strDeptName' class='comboNormal'>"
					+ res.split('^')[0] + "</select>";

			var objVal = document.getElementById("tariffDivId");
			objVal.innerHTML = "<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px' ><tr>"
					+ "<td width='50%' class='LABEL'>Tariff Name</td>"
					+ "<td width='50%' class='CONTROL'>"
					+ "<select name ='strTariffName' class='comboNormal'>"
					+ res.split('^')[1] + "</select></td></tr></table>";
		}
		if (mode == "4") {
			var objVal = document.getElementById("tariffDivId");
			objVal.innerHTML = "<select name ='strTariffName' class='comboNormal'>"
					+ res + "</select>";
		}
		if (mode == "5") {

			var objVal = document.getElementById("tariffName");
			objVal.innerHTML = "<select name = 'strTariffName' class='comboNormal' >"
					+ res + "</select>";

		}
		if (mode == "6") {
			var obj1 = document.getElementById("LeftMenuId");
			obj1.innerHTML = "<select name='strLeftMenuNames' size='8' multiple style='width: 280px'>"
					+ res + "</select>";

		}
	}
	function cancelPage() {
		document.forms[0].hmode.value = "CANCEL";
		document.forms[0].target = "_self";
		document.forms[0].submit();

	}
	function getTariff(obj) {
		var temp = "0";

		var mode = "TARIFFCMB";
		var url = "DayEndCashHandoverRptCNT.cnt?hmode=" + mode + "&groupId="
				+ temp + "&tariffType=" + obj.value;
		ajaxFunction(url, "5");
	}
	function setTariffSelRadio() {
		document.forms[0].strTariffSelection.value = "1";

	}
	function LeftListTransfer() {
		var ob1 = document.forms[0].strLeftMenuNames.value;
		var ob = document.getElementById("LeftMenuId");
		shiftToRight("strLeftMenuNames", "strRightMenuNames", 1);
	}
	function ajaxFunLeftList() {
		var temp = "0";
		var mode = "TARIFFMULTISELCMB";
		var url = "DayEndCashHandoverRptCNT.cnt?hmode=" + mode + "&groupId="
				+ temp + "&tariffType="
				+ document.forms[0].strTariffTypeNew.value;
		ajaxFunction(url, "6");

	}
	function setTariffSelection(obj) {
		for (var i = 0; i < obj.length; i++) {

			if (obj[i].checked) {
				obj = obj[i];
				break;
			}

		}
		if (obj.value == 2) {
			document.forms[0].strSearch.value = "";
			shiftAllToLeft('strLeftMenuNames', 'strRightMenuNames', 1);
			ajaxFunLeftList();
			document.getElementById("tariffMulComboDivId").style.display = "";

		} else {
			shiftAllToLeft('strLeftMenuNames', 'strRightMenuNames', 1);
			document.getElementById("tariffMulComboDivId").style.display = "none";
			document.forms[0].strSearch.value = "";
		}

	}
	function getMulTariffComboSelection() {
		var obj = document.getElementsByName('strTariffSelection');
		setTariffSelection(obj);

	}
	function searchItemsInPopup(e, txtObj) {

		try {

			if (e.keyCode == 13) {

				shiftToRightLogic(gblMode);
				txtObj.value = "";
				return;

			}

			var searchMode = "1";

			var value = txtObj.value;
			var list_name = "strLeftMenuNames";

			var searchType = 0;

			if (value.indexOf("%") == 0) {

				if (value.length == 1)
					gblItemSelectedIndex = -1;

				searchType = 1;

				value = value.substring(1);
			}

			if (value != "") {

				//getting object
				lobj = document.getElementsByName(list_name);

				if (lobj.length > 0) //list box exists
				{

					if (e.keyCode == 40) { // down arrow key code

						init = parseInt(gblItemSelectedIndex) + 1;
						total = lobj[0].length;

						searchItemListValues(init, total, lobj, value,
								searchMode, searchType, 2);

					} else if (e.keyCode == 38) { // up arrow key code 

						init = parseInt(gblItemSelectedIndex) - 1;
						total = 0;

						searchItemListValues(init, total, lobj, value,
								searchMode, searchType, 3);

					} else {

						var init = 0;
						var total = lobj[0].length;

						searchItemListValues(init, total, lobj, value,
								searchMode, searchType, 1);

					}

				}// end of lobj.length if 		 

			}

		} catch (err) {
			alert(err);
		}

	}
	/**
	 * searchItemListValues
	 * @param {String} init - for init value
	 * @param {String} total - for total value
	 * @param {Object} lobj -  list object value 
	 * @param {String} searchMode -  1 - Search by Name, 2 - Search by Code
	 * @param {String} value - Search Value 
	 * @param {String} searchType - 0 - Direct Search, 1 - Search with %
	 * @param {String} mode -  1 - Normal , 2 - when down key pressed , 3 - when up key pressed
	 */
	function searchItemListValues(init, total, lobj, value, searchMode,
			searchType, mode) {

		var listValue;

		if (mode == 3) {

			for (i = init; i >= total; i--) {
				if (searchMode == 1) {

					listValue = (lobj[0].options[i].text).toUpperCase();

				} else {

					var listTempVal = lobj[0].options[i].value.split('^');

					listValue = (listTempVal[listTempVal.length - 1])
							.toUpperCase();

				}

				if (searchType == 1) {

					if (listValue.indexOf((value.toString()).toUpperCase()) >= 0) { //matched
						lobj[0].selectedIndex = i;
						retValue = true;

						gblItemSelectedIndex = i;

						break;
					}

				} else {

					if (listValue.indexOf((value.toString()).toUpperCase()) == 0) { //matched
						lobj[0].selectedIndex = i;
						retValue = true;

						gblItemSelectedIndex = i;

						break;
					}

				}

			} // end of for 

		} else {

			for (i = init; i < total; i++) {

				if (searchMode == 1) {

					listValue = (lobj[0].options[i].text).toUpperCase();

				} else {

					var listTempVal = lobj[0].options[i].value.split('^');

					listValue = (listTempVal[listTempVal.length - 1])
							.toUpperCase();

				}

				if (searchType == 1) {

					if (listValue.indexOf((value.toString()).toUpperCase()) >= 0) { //matched
						lobj[0].selectedIndex = i;
						retValue = true;

						gblItemSelectedIndex = i;

						break;
					}

				} else {

					if (listValue.indexOf((value.toString()).toUpperCase()) == 0) { //matched
						lobj[0].selectedIndex = i;
						retValue = true;

						gblItemSelectedIndex = i;

						break;
					}

				}

			} // end of for 

		}

	}
	function selectFunction(a) {
		var printStr = document.getElementById("reportTypeId").options[0].value
		document.getElementById("reportTypeId").selectedIndex = a;
		if (a == 0) {
			$("#pdfId").addClass("changeImgGray");
			$("#htmlId").removeClass("changeImgGray");
			$("#excelId").removeClass("changeImgGray");
		} else if (a == 1) {
			$("#htmlId").addClass("changeImgGray");
			$("#pdfId").removeClass("changeImgGray");
			$("#excelId").removeClass("changeImgGray");
		} else {
			$("#excelId").addClass("changeImgGray");
			$("#pdfId").removeClass("changeImgGray");
			$("#htmlId").removeClass("changeImgGray");
		}
	}
</script>
</head>
<body
	onload="getCombo(document.forms[0].strCase);getCombo1(document.forms[0].strHospitalCode);setTariffSelRadio();">
	<html:form action="/reports/DayEndCashHandoverRptCNT.cnt" method="post">


		<div class="viewport" id="nonPrintable">
			<div class="container-fluid">

				<div class="errMsg" id="errMsg">
					<bean:write name="cashHandoverRpt" property="strErrMsg" />
				</div>
				<div class="normalMsg" id="normalMsg"></div>
				<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="document.forms[0].reset();"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									<button type="button" id="savebutton"
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2' onClick="return validate();"
										name="patientAdmissionModiTransBean"
										style="background-color: #5cb85c;">
										<i class="fa fa-download iround" title="Generate"></i>
									</button>
								</div>
							</div>
							<div class="row rowFlex reFlex">
								<div class="col-sm-8">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Day End Cash Handover Report
									</p>
									<!-- <button  type="button" class="btn btn-outline-success mt-1  savebtn" onclick="cancelFunc();">
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button> -->
								</div>
								<div class="col-sm-2">
									<div class="custom-control custom-radio">
										<input type="radio" id='customRadio'
											class="custom-control-input" checked="checked" name="strCase"
											value="1" onclick="getCombo(this);"> <label
											class="custom-control-label" for="customRadio">ScrollNo.Wise</label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="custom-control custom-radio">
										<input type="radio" id='customRadio1'
											class="custom-control-input" name="strCase" value="2"
											onclick="getCombo(this);"> <label
											class="custom-control-label" for="customRadio1">Consolidated</label>
									</div>
								</div>
								<div class="col-sm-1"></div>

							</div>







							<div class="row rowFlex reFlex">
								<div class="col-sm-2">
									<label>Hospital Name</label>
								</div>
								<div class="col-sm-3">
									<select name="strHospitalCode" onchange="getCombo1(this);"
										class='browser-default custom-select'>
										<bean:write name="cashHandoverRpt"
											property="strHospNameValues" filter="false" />
									</select>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Cost Type</label>
								</div>
								<div class="col-sm-3">
									<select name="strCostType" onchange=""
										class='browser-default custom-select'>
										<bean:write name="cashHandoverRpt" property="strCostTypeValue"
											filter="false" />
									</select>
								</div>
								<div class="col-sm-1"></div>
							</div>

							<div id="dateDivId" style="display:">
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
											class="form-control modiDatpicker"
											style="color: rgba(113, 111, 111, 0.87);">
									</div>
									<div class="col-sm-1"></div>
								</div>
							</div>


							<div class="row rowFlex reFlex" style="display: none;">
								<div class="col-sm-4"></div>
								<div class="col-sm-2">
									<label>Footer Required</label>
								</div>
								<div class="col-sm-2">
									<html:checkbox property="strIsFooter" name="cashHandoverRpt"
										value="1"></html:checkbox>
								</div>
								<div class="col-sm-4"></div>
							</div>

							<div class="row rowFlex reFlex">
								<div class="col-sm-2">
									<label>User Remarks</label>
								</div>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="strUserRemarks">
								</div>
								<div class="col-sm-7"></div>
							</div>

							<%-- 	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="1">Fee Collection Report</td>
			<td colspan="1"><div align='right'>Report show Data of Billing & Reg All Payment Modes Except Advance & Part Payment of Credit(CM Fund)</div></td>
	</tr>
	<tr style="display: none;">
	    <td class="LABEL" colspan="2">
	    <div align="right">
	    <html:radio property="strCase" name="cashHandoverRpt" value="1" onclick="getCombo(this);" >Counter</html:radio>
	    <html:radio property="strCase" name="cashHandoverRpt" value="2" onclick="getCombo(this);" >Cashier</html:radio>
	   
	    <html:radio property="strCase" name="cashHandoverRpt" value="3" onclick="getCombo(this);" >Department</html:radio>
	    <html:radio property="strCase" name="cashHandoverRpt" value="4" onclick="getCombo(this);" >Date</html:radio>
	    <html:radio property="strCase" name="cashHandoverRpt" value="9" onclick="getCombo(this);" >Tariff+Date</html:radio>
	    </div>
	    </td>
 	 </tr> 	 	
 	 <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" class="CONTROL">
				<select name="strHospitalCode" onchange="getCombo1(this);" class='comboNormal'>
						<bean:write name="cashHandoverRpt" property="strHospNameValues" filter="false"/>
				</select>
			</td>			
	</tr> 	 	
 	<tr> 
	    <td width="50%" class="LABEL">Hospital Service</td>
	    <td width="50%" class="CONTROL">
	     <div id="hospSerDivId">
	    <select name="strHospSerName" class="comboNormal" onchange="getGroupCombo(this);" >
	           <bean:write name="cashHandoverRpt" property="strHospSerValues" filter="false"/>
	           </select> 
	           </div> </td>
  </tr>
 </table> --%>
							<div id="counterDivId" style="display: block">
								<%--  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
	    <td width="50%" class="LABEL">Counter Name</td>
	    <td width="50%" class="CONTROL">
	    <div id="conterDivId2">
	    <select name="strCounterName" class="comboNormal" >
	        <bean:write name="cashHandoverRpt" property="strCounterValues" filter="false"/>
	       <option value="0">All</option></select></div> </td> 
  	 </tr>
  	</table> --%>
							</div>

							<div id="feeclerkDivId" style="display: none">
								<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
	 	  <tr> 
		    <td width="50%" class="LABEL">Fee Clerk Name</td>
		    <td width="50%" class="CONTROL">
		     <div id="feeclerkDivId2">
		    <select name="strFeeClerkName" class="comboNormal" >
		           <bean:write name="cashHandoverRpt" property="strFeeClerkValues" filter="false"/>
		       <option value="0">All</option> </select></div> </td> 
  		 </tr>
  		</table> --%>
							</div>

							<div id="deptDivId" style="display: none">
								<table class="TABLEWIDTH" align="center" cellspacing="1px"
									cellpadding="1px">
									<tr>
										<td width="50%" class="LABEL">Department Name</td>
										<td width="50%" class="CONTROL">
											<div id="deptDivId2">
												<select name="strDeptName" class="comboNormal">
													<bean:write name="cashHandoverRpt" property="strDeptValues"
														filter="false" />
													<%-- <option value="0">All</option></select> </div>--%>
										</td>
									</tr>
								</table>
							</div>

							<div id="tariffDateDivId" style="display: none">
								<table class="TABLEWIDTH" align="center" cellspacing="1px"
									cellpadding="1px">
									<tr>
										<td width="50%" class="LABEL">Tariff Type</td>
										<td width="50%" class="CONTROL"><div id="tariffTypeNew">
												<select name="strTariffTypeNew" class="comboNormal"
													onChange="getMulTariffComboSelection();">
													<option value="0">All</option>
													<option value="1">Only Hospital Cost</option>
													<option value="2">Only Package Cost</option>
												</select>
											</div></td>
									</tr>
								</table>
							</div>

							<div id="tariffCheckBoxDivId" style="display: none">
								<table class="TABLEWIDTH" align="center" cellspacing="1px"
									cellpadding="1px">
									<tr>
										<td width="50%" class="LABEL">Is Tariff Wise</td>
										<td width="50%" class="CONTROL"><html:checkbox
												property="strTariffWise" name="cashHandoverRpt" value="5"
												onclick="getCombo(this);"></html:checkbox></td>
									</tr>
								</table>
							</div>
							<div id="tariffDivId" style="display: none">
								<table class="TABLEWIDTH" align="center" cellspacing="1px"
									cellpadding="1px">
									<tr>
										<td width="50%" class="LABEL">Tariff Name</td>
										<td width="50%" class="CONTROL"><select
											name="strTariffName" class="comboNormal">
												<bean:write name="cashHandoverRpt"
													property="strTariffValues" filter="false" />
										</select></td>
									</tr>
								</table>
							</div>

							<div id="tariffDateDivId2" style="display: none">
								<table class="TABLEWIDTH" align="center" cellspacing="1px"
									cellpadding="1px">
									<tr>
										<td width="50%" class="LABEL">Tariff Selection</td>
										<td width="50%" class="CONTROL"><html:radio
												property="strTariffSelection" name="cashHandoverRpt"
												value="1" onclick="setTariffSelection(this);">All</html:radio>
											<html:radio property="strTariffSelection"
												name="cashHandoverRpt" value="2"
												onclick="setTariffSelection(this);">Customize</html:radio>
										</td>
									</tr>
								</table>

							</div>

							<div id="tariffMulComboDivId" style="display: none">
								<table class="TABLEWIDTH" align="center" cellpadding="1px"
									cellspacing="1px">
									<tr>
										<td class="LABEL" width="15%"><div align="left">Tariff
												Names</div></td>
										<td class="CONTROL" width="35%"><div align="left">
												<input type="text"
													onkeyup="searchItemsInPopup(event , this );" tabindex="1"
													name="strSearch" class="txtFldMax" onblur="Trim(this)" />
											</div></td>
										<td class="LABEL" width="50%"></td>
									</tr>
								</table>
								<table class="TABLEWIDTH" align="center" cellpadding="1px"
									cellspacing="1px">
									<tr>
										<td class="CONTROL" colspan="3">

											<div id="LeftMenuId" align="right">
												<select name="strLeftMenuNames" size="8" multiple
													style="width: 280px"><bean:write
														name="cashHandoverRpt" property="strLeftMenuList"
														filter="false" /></select>
											</div>

										</td>
										<td width="6%" class="CONTROL" colspan="2">

											<center>
												<img src="../../hisglobal/images/forward3.gif" width="35"
													height="31" onclick="LeftListTransfer();">
											</center>

											<center>
												<img src="../../hisglobal/images/forwardward.gif" width="35"
													height="31" align="middle"
													onClick="shiftAllToRight('strLeftMenuNames','strRightMenuNames',1);" />
											</center> <br />
											<center>

												<img src="../../hisglobal/images/backward.gif" width="35"
													height="31"
													onClick='shiftAllToLeft("strLeftMenuNames","strRightMenuNames",1);'>
											</center>

											<center>
												<img src="../../hisglobal/images/back3.gif" width="35"
													height="31"
													onclick="shiftToLeft('strLeftMenuNames','strRightMenuNames',1);" />
											</center>
										</td>

										<td colspan="3" class="CONTROL">
											<div id="RightMenuId" align="left">
												<select name="strRightMenuNames" size="8" multiple
													style="width: 280px">
												</select>
											</div>
										</td>
									</tr>
								</table>



							</div>

							<div id="dateDivId" style="display: block">
								<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">  
  		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${cashHandoverRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${cashHandoverRpt.strCurrentDate}" /></td>
		</tr>
  		</table> --%>
							</div>
							<table class="TABLEWIDTH" align="center" cellspacing="0"
								cellpadding="1px">
								<!-- 		<tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
				<select name="strReportFormat"  onchange="">
				<option value="html">Html</option>
				<option value="pdf">Pdf</option>
				<option value="excel">Excel</option> </select>
			</td>		
		</tr>	 -->
								<%-- <tr style="display: none">
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="cashHandoverRpt" value="1"></html:checkbox>
			</td>			
		</tr>
		<tr style="display: none">
			<td width="50%" class="LABEL">User Remarks</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>			
		</tr> --%>
								<!-- <tr class="FOOTER">
			<td colspan="1"><div align="left"><font size="2" color="red">#</font>Default Hospital Selected-Looged In Hospital  </div></td>
			<td colspan="1"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr> -->
							</table>


							<br>
							<div class="row rowFlex reFlex">
								<div class="col-sm-12" align="right">

									<img src="/HBIMS/hisglobal/images/html-512.png" id="htmlId"
										class="changeImg changeImgGray" alt="Html Report"
										onclick="selectFunction(1)" tabindex="0" selected="selected"
										style="width: 40px; color: #fff;" title="Html">
									&nbsp;&nbsp;&nbsp;<img
										src="/HBIMS/hisglobal/images/pdf-512.png" title="Pdf"
										id="pdfId" class="changeImg" alt="Acrobat Reader"
										onclick="selectFunction(0)" tabindex="0"
										style="width: 40px; color: #fff;"> &nbsp;&nbsp;&nbsp;<img
										src="/HBIMS/hisglobal/images/excel-521.png" title="Excel"
										id="excelId" class="changeImg" alt="Excel Report"
										onclick="selectFunction(2)" tabindex="0"
										style="width: 40px; color: #fff;"> <select
										name="strReportFormat" id="reportTypeId"
										class="form-control validatebox-text" style="display: none;">
										<option value="pdf">Acrobat Reader</option>
										<option value="html" selected="selected">HTML</option>
										<option value="excel">Excel</option>
									</select>

								</div>
							</div>
							<input type="hidden" name="hmode" /> <input type="hidden"
								name="strCurrentDate" value="${cashHandoverRpt.strCurrentDate}" />
							<input type="hidden" name="strAllHospCode"
								value="${cashHandoverRpt.strAllHospCode}" /> <input
								type="hidden" name="strSelectedTariffIds"
								value="${cashHandoverRpt.strSelectedTariffIds}" />

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