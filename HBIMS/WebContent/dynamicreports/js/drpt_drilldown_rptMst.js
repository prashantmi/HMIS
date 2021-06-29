

function checkIsValidDrillDown(){
	
	var isLast = document.forms[0].strIsLast.value;
	var flag = false;
	if(isLast != 1){
		
		var strIsHidden = document.getElementsByName("strIsHidden");
		
		 
		
		if(strIsHidden.length == 0){
			
			
			document.getElementById("errMsg").innerHTML = "Procedure is In-Valid for the Drill Down Report, since there is no HIDDEN_VAL or HIDDEN_VALUE column Available";
					
			
		}else{
			document.getElementById("errMsg").innerHTML = "";
		}
	}
	
	
}


function validateInsert() {

		var errMsgVal = document.getElementById("errMsg").innerHTML;
				
		if( errMsgVal.length > 10){
			
			alert("Data cannot be Saved due to the Error :\n ' "+errMsgVal+" '");
			return false;
		}
		
	
		var hisValidator = new HISValidator("rptparamBean");

		hisValidator.addValidation("strOutColumnDisplayName", "req",
				"Display Name is a Mandatory Field");
		hisValidator.addValidation("strOutColumnIndex", "req",
				"Column Index is a Mandatory Field");
		hisValidator.addValidation("strOutColumnWidth", "req",
				"Column Width is a Mandatory Field");

		if (document.forms[0].strReportHeaderParamReq.value == 1) {

			if (document.forms[0].strIsMergeIntermediate.value == 1) {

				hisValidator.addValidation("strReportHeaderParamType",
						"dontselect=0",
						"Please Select an Header Parameter Parameter Type");

			}
			hisValidator
					.addValidation("strReportHeaderParamId", "dontselect=0",
							"Please Select an Header Parameter Mapping");

		}

		var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();

		var mode = "DRILLDOWNSAVE";

		if (retVal) {

			var colWidth = document.getElementsByName("strOutColumnWidth");
			var status = document.getElementsByName("strGrpReqStatus");

			var reqCheck = document.getElementsByName("strColumnRequired");
			var hlinkMap = document
					.getElementsByName("strHyperlinkMappingValue");

			for ( var j = 0; j < reqCheck.length; j++) {

				if (reqCheck[j].checked == true) {

					hlinkMap[j].disabled = false;

				}

			}

			var colWidthVal = parseFloat("0");

			for ( var i = 0; i < status.length; i++) {

				if (colWidth[i].disabled == false) {

					colWidthVal = colWidthVal + parseFloat(colWidth[i].value);

				}

				if (status[i].value == 2) {

					colWidth[i].disabled = false;

				}

			}

			if (colWidthVal > 100) {

				alert("Column Width Sum cannot be greater than 100");
				return false;
			}

			document.forms[0].hmode.value = mode;
			document.forms[0].submit();
		} else {
			return false;
		}
	}

	function getHeaderParamDtls(obj) {

		if (obj.value != '0') {

			if (obj.value == '1') {

				document.getElementById("strReportHeaderParamDivId").innerHTML = "<select name='strReportHeaderParamId' class='comboNormal'>"
						+ document
								.getElementById("strInparamValuesContentDivId").innerHTML
						+ "</select>";

			} else {

				document.getElementById("strReportHeaderParamDivId").innerHTML = "<select name='strReportHeaderParamId' class='comboNormal'>"
						+ document
								.getElementById("strOutparamValuesContentDivId").innerHTML
						+ "</select>";

			}

		} else {

			document.getElementById("strReportHeaderParamDivId").innerHTML = "<select name='strReportHeaderParamId' class='comboNormal'><option value='0'>Select Value</option></select>";
			;

		}

	}

	function enableValues(obj, index) {

		if (obj.checked == true) {

			document.getElementById("strOutColumnName" + index).disabled = false;
			document.getElementById("strOutColumnDisplayName" + index).value = "";
			document.getElementById("strOutColumnDisplayName" + index).disabled = false;
			document.getElementById("strOutColumnActualIndex" + index).disabled = false;
			document.getElementById("strOutColumnIndex" + index).disabled = false;
			document.getElementById("strOutColumnDisplayName" + index).disabled = false;
			document.getElementById("strOutColumnWidth" + index).disabled = false;

			document.getElementById("strHyperlinkRequired" + index).disabled = false;
			document.getElementById("strGroupByRequired" + index).disabled = false;
			document.getElementById("strTotalRequired" + index).disabled = false;
			document.getElementById("strOrderByRequired" + index).disabled = false;

			document.getElementById("strHyperlinkRequiredValue" + index).disabled = false;
			document.getElementById("strGroupByRequiredValue" + index).disabled = false;
			document.getElementById("strTotalRequiredValue" + index).disabled = false;
			document.getElementById("strOrderByRequiredValue" + index).disabled = false;

			document.getElementById("strHyperlinkRequired" + index).checked = false;

			document.getElementById("strGroupByRequired" + index).checked = false;
			document.getElementById("strTotalRequired" + index).checked = false;
			document.getElementById("strOrderByRequired" + index).checked = false;

			document.getElementById("strHyperlinkRequiredValue" + index).value = 0;
			document.getElementById("strGroupByRequiredValue" + index).value = 0;
			document.getElementById("strTotalRequiredValue" + index).value = 0;
			document.getElementById("strOrderByRequiredValue" + index).value = 0;

			document.getElementById("strOutColumnAlign" + index).disabled = false;

			document.getElementById("strGrpReqStatus" + index).value = 1;

		} else {

			document.getElementById("strOutColumnName" + index).disabled = true;
			document.getElementById("strOutColumnDisplayName" + index).value = " ";
			document.getElementById("strOutColumnDisplayName" + index).disabled = true;
			document.getElementById("strOutColumnActualIndex" + index).disabled = true;
			document.getElementById("strOutColumnIndex" + index).disabled = true;
			document.getElementById("strOutColumnDisplayName" + index).disabled = true;
			document.getElementById("strOutColumnWidth" + index).disabled = true;

			document.getElementById("strHyperlinkRequired" + index).disabled = true;
			document.getElementById("strGroupByRequired" + index).disabled = true;
			document.getElementById("strTotalRequired" + index).disabled = true;
			document.getElementById("strOrderByRequired" + index).disabled = true;

			document.getElementById("strHyperlinkRequiredValue" + index).disabled = true;

			document.getElementById("strHyperlinkMappingValue" + index).selectedIndex = 0;
			document.getElementById("strHyperlinkMappingValue" + index).disabled = true;

			document.getElementById("strGroupByRequiredValue" + index).disabled = true;
			document.getElementById("strTotalRequiredValue" + index).disabled = true;
			document.getElementById("strOrderByRequiredValue" + index).disabled = true;

			document.getElementById("strOutColumnAlign" + index).disabled = true;

			document.getElementById("strGrpReqStatus" + index).value = 0;

		}

		setIndexAndWidthValues();

	}

	function setIndexAndWidthValues() {

		var status = document.getElementsByName("strGrpReqStatus");
		var reqObj = document.getElementsByName("strOutColumnIndex");
		var colWidthObj = document.getElementsByName("strOutColumnWidth");

		var grpCount = parseInt("0");
		for ( var k = 0; k < status.length; k++) {

			if (status[k].value == 2) {

				grpCount = grpCount + 1;

			}

		}

		var count = grpCount + 1;

		for ( var i = 0; i < status.length; i++) {

			if (status[i].value == 1) {

				reqObj[i].value = count;
				document.getElementById("colDisplayDivId" + (i + 1)).innerHTML = count;
				count = count + 1;

			} else if (status[i].value == 0) {

				reqObj[i].value = 0;
				document.getElementById("colDisplayDivId" + (i + 1)).innerHTML = 0;

			} else {

			}

		}

		var colWidthVal = Math.floor(100 / parseFloat((count - grpCount - 1)));

		for ( var i = 0; i < colWidthObj.length; i++) {

			if (status[i].value == 1) {

				colWidthObj[i].value = colWidthVal;

			} else if (status[i].value == 0) {

				colWidthObj[i].value = 0;

			} else {

			}

		}

	}

	function changeGroupByStatus(obj, index) {

		if (obj.checked == true) {

			document.getElementById("reqDivId" + index).style.display = "none";
			document.getElementById("tickIconDivId" + index).style.display = "block";

			document.getElementById("colIndexDivId" + index).style.display = "none";
			document.getElementById("colDisplayDivId" + index).style.display = "block";

			document.getElementById("strOrderByRequired" + index).checked = true;

			document.getElementById("strGrpReqStatus" + index).value = 2;

			document.getElementById("strGroupByRequiredValue" + index).value = 1;
			document.getElementById("strOrderByRequiredValue" + index).value = 1;

		} else {

			document.getElementById("reqDivId" + index).style.display = "block";
			document.getElementById("tickIconDivId" + index).style.display = "none";

			document.getElementById("colIndexDivId" + index).style.display = "block";
			document.getElementById("colDisplayDivId" + index).style.display = "none";

			document.getElementById("strOrderByRequired" + index).checked = false;

			document.getElementById("strGrpReqStatus" + index).value = 1;

			document.getElementById("strGroupByRequiredValue" + index).value = 0;
			document.getElementById("strOrderByRequiredValue" + index).value = 0;

		}

		var status = document.getElementsByName("strGrpReqStatus");
		var grpCount = parseInt("1");
		for ( var k = 0; k < status.length; k++) {

			if (status[k].value == 2) {

				document.getElementById("strOutColumnIndex" + (k + 1)).value = grpCount;
				document.getElementById("colDisplayDivId" + (k + 1)).innerHTML = grpCount;

				document.getElementById("strOutColumnWidth" + (k + 1)).value = 0;
				document.getElementById("strOutColumnWidth" + (k + 1)).disabled = true;

				grpCount++;

			} else {
				if (status[k].value == 1)
					document.getElementById("strOutColumnWidth" + (k + 1)).disabled = false;

			}

		}

		setIndexAndWidthValues();

	}

	function changeOrderByStatus(obj, index) {

		if (document.getElementById("strGroupByRequired" + index).checked) {

			document.getElementById("strOrderByRequired" + index).checked = true;

			alert("Group By Column should be Order By");
			return false;

		}

	}

	function hiddenCheck(obj, desobjid) {

 
		
		if (obj.checked) {

			document.getElementById(desobjid).value = 1;

		} else {

			document.getElementById(desobjid).value = 0;
		}

	}
	
	
	function hyperlinkMappingValue(obj, index , colCount) {

		 		
		if (obj.checked) {

			document.getElementById("strHyperlinkMappingValue" + index).value = colCount;
			
		} else {

			document.getElementById("strHyperlinkMappingValue" + index).value = 0;
		}

	}
	
/*
	function hyperlinkMappingValue(obj, index) {

		if (obj.checked) {

			document.getElementById("strHyperlinkMappingValue" + index).disabled = false;

		} else {

			document.getElementById("strHyperlinkMappingValue" + index).selectedIndex = 0;
			document.getElementById("strHyperlinkMappingValue" + index).disabled = true;

		}

	}*/