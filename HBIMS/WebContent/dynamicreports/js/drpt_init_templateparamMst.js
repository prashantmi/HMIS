
	function validate() {

		var rptType = document.forms[0].strReportTypeId[document.forms[0].strReportTypeId.selectedIndex].value;
		
		var hisValidator = new HISValidator("rptparamBean");

		hisValidator.addValidation("strReportTypeId", "dontselect=0", "Report Type is a Mandatory Field");
		hisValidator.addValidation("strReportTemplateId", "dontselect=0", "Report Name is a Mandatory Field");
		hisValidator.addValidation("strProcedureName", "dontselect=0", "Procedure Name is a Mandatory Field");
		
		if(rptType == '3' || rptType == '4'){
			
			hisValidator.addValidation("strReportLevelDisplayName", "req", "Report Display Name is a Mandatory Field");
		}
		
		var prevLevelObj = document
		.getElementById("previousLevelsDivId").innerHTML

		if (prevLevelObj == "" || prevLevelObj.length == 0) {
			hisValidator.addValidation("strInDisplayName", "req", "Display Name is a Mandatory Field");
		}
		
	
		hisValidator.addValidation("strInConstantValue", "req", "Default Value is a Mandatory Field");

		var retVal = hisValidator.validate();
 
		hisValidator.clearAllValidations();
		
		
		if (retVal) {
			
					var mode = "";
					
				
							
					switch(rptType){
					
					case '1' : mode = "ROWBASEDRPT"; break;
					
					case '2' : mode = "COLUMNBASEDRPT"; break;
					
					case '3' : mode = "DRILLDOWNRPT"; break;
		
					default : mode = "MERGERPT"; 
					
					}
			
					document.forms[0].strReportTypeName.value = document.forms[0].strReportTypeId[document.forms[0].strReportTypeId.selectedIndex].text;
					document.forms[0].strReportName.value = document.forms[0].strReportTemplateId[document.forms[0].strReportTemplateId.selectedIndex].text;
										
					
					var defaultValObj = document.getElementsByName("strInConstantValue");
					
					for(var i = 0; i < defaultValObj.length ; i++){
						
						defaultValObj[i].disabled = false;
						
					}
					
					
					
			document.forms[0].hmode.value = mode;
			document.forms[0].submit();
		} else {
			return false;
		}
	}
	
	

	
	
	
	
	function getReportList(obj) {
		
		if (obj.value != '0') {

			var mode = 'GETREPORTLIST';
			var strReportTypeId = obj.value;

			var url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
					+ "&strReportTypeId=" + strReportTypeId;
			ajaxFunction(url, "1");

		} else {

			document.getElementById("reportNameDivId").innerHTML = "<select name='strReportTemplateId' class='comboMax'><option value='0'>Select Value</option></select>";
					
		}
		 
		
		document.forms[0].strProcedureName.selectedIndex = 0;
		
		document.forms[0].strIsCombo.checked = false;
		document.forms[0].strIsLast.checked = false;
		
		
		
		
		document.getElementById("reportDetaildDivId").style.display = "none";
		document.getElementById("previousLevelsDivId").style.display = "none";
		document.getElementById("procedureDetaildDivId").style.display = "none";
		document.getElementById("reportParamDivId").style.display = "none";
		document.getElementById("rptDisplayDetaildDivId").style.display = "none";
		
		
	}

	
	function getProcInParamList(obj) {
		
				
		
		if (obj.value != '0') {

			
			var mode = 'GETPROCINPARAMLIST';
			var trProcedureName = obj.value;

			var url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
					+ "&strProcedureName=" + trProcedureName;
			
			var reportType = document.forms[0].strReportTypeId[document.forms[0].strReportTypeId.selectedIndex].value;
			var rptId =  document.forms[0].strReportTemplateId[document.forms[0].strReportTemplateId.selectedIndex].value.split('^')[0];
			
			var prevRptDtls = document.getElementById("previousLevelsDivId").innerHTML;
			
			
			if(reportType == '3' || prevRptDtls.length == 0 ){
				
				
				
				document.forms[0].strIsColumnBaseRpt.checked = false;
				document.forms[0].strIsColumnBaseRpt.disabled = true;
				
			}else{
				
				document.forms[0].strIsColumnBaseRpt.checked = false;
				document.forms[0].strIsColumnBaseRpt.disabled = false;
							
			}
			
			
		
			
			if (reportType == '3' && prevRptDtls.length > 0) {
				
				mode = 'GETDDINPARAMLIST';
				
				url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
				+ "&strProcedureName=" + trProcedureName+"&strReportTemplateId="+rptId;
				
			 
				
			} 
			
			
			
			if (reportType == '4' && prevRptDtls.length > 0) {
			
				mode = 'GETMERGEINPARAMLIST';
							
				url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
				+ "&strProcedureName=" + trProcedureName+"&strReportTemplateId="+rptId;
				
			} 
			
			
			ajaxFunction(url, "2");

		} else {

			document.getElementById("reportParamDivId").style.display = "none";
					
		}
		
		 
	}
	
	
	

	function getPreviousReportDetails(obj) {
		
				
		if (obj.value != '0') {

		
			var mode = 'GETPRERPTDTL';
			var trProcedureName = obj.value;

			var url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
					+ "&strReportTemplateId=" + obj.value.split('^')[0];
			ajaxFunction(url, "3");

		} else {

			document.getElementById("previousLevelsDivId").style.display = "none";
					
		}
		
		 
	}
	
	
	
	
	function displayReportDetails() {

		var reportType = document.forms[0].strReportTypeId[document.forms[0].strReportTypeId.selectedIndex].value;
		var obj = document.forms[0].strReportTemplateId[document.forms[0].strReportTemplateId.selectedIndex];
		
		if (obj.value != '0') {

			var rptDtls = obj.value.split('^');

			document.getElementById("reportDisplayNameDivId").innerHTML = rptDtls[1];
			document.getElementById("reportWidthDivId").innerHTML = rptDtls[2];

			document.getElementById("reportHeaderTypeDivId").innerHTML = rptDtls[4];
			document.getElementById("reportBorderDivId").innerHTML = rptDtls[6];
					
			document.getElementById("reportDetaildDivId").style.display = "block";
			document.getElementById("reportButtonsDivId").style.display = "block";
			
			
			document.forms[0].strProcedureName.selectedIndex = 0;
			
			
			document.forms[0].strReportHeaderTypeId.value = rptDtls[3];
			document.forms[0].strReportHeaderParamReq.value = rptDtls[5];
			
						
			document.forms[0].strIsCombo.checked = false;
			document.forms[0].strIsLast.checked = false;

			if (reportType != '1' && reportType != '2') {

				document.getElementById("previousLevelsDivId").style.display = "block";
				document.getElementById("rptDisplayDetaildDivId").style.display = "block";

							
				var prevLevelObj = document
						.getElementById("previousLevelsDivId").innerHTML

					 						
				if (prevLevelObj == "" || prevLevelObj.length == 0) {

					document.getElementById("isComboDivId").style.display = "block";
					document.getElementById("isLastDivId").style.display = "none";

					document.getElementById("isComboCheckDivId").style.display = "block";
					document.getElementById("isLastCheckDivId").style.display = "none";

					document.forms[0].strIsMergeIntermediate.value = "0";
					
				} else {

					document.getElementById("isComboDivId").style.display = "none";
					document.getElementById("isLastDivId").style.display = "block";

					document.getElementById("isComboCheckDivId").style.display = "none";
					document.getElementById("isLastCheckDivId").style.display = "block";

					document.forms[0].strIsMergeIntermediate.value = "1";
					
				}
 
			} else {

				document.getElementById("previousLevelsDivId").style.display = "none";
				document.getElementById("rptDisplayDetaildDivId").style.display = "none";

				document.getElementById("isComboDivId").style.display = "block";
				document.getElementById("isLastDivId").style.display = "none";

				document.getElementById("isComboCheckDivId").style.display = "block";
				document.getElementById("isLastCheckDivId").style.display = "none";

			}

			document.getElementById("procedureDetaildDivId").style.display = "block";
			
		} else {

			document.getElementById("reportDetaildDivId").style.display = "none";
			document.getElementById("procedureDetaildDivId").style.display = "none";
			document.getElementById("previousLevelsDivId").style.display = "none";
			document.getElementById("rptDisplayDetaildDivId").style.display = "none";
			

			document.forms[0].strReportHeaderTypeId.value = 0;
			document.forms[0].strReportHeaderParamReq.value = 0;
			

			  
		}

		document.getElementById("reportParamDivId").style.display = "none";
		
	}
	
	
	

	var gblIndexVal = "";

	function getQuery(obj , index){

	gblIndexVal = index;

	if(obj.value == 3){
		
		 document.getElementById("strInConstantValueDivId"+gblIndexVal).value = "SYSDATE";
		 document.getElementById("strInConstantValueDivId"+gblIndexVal).disabled = true;
		 
	}else{
		
		document.getElementById("strInConstantValueDivId"+gblIndexVal).value = "0";
		 document.getElementById("strInConstantValueDivId"+gblIndexVal).disabled = false;
		
	}
	
	if(obj.value == 2){
	
	document.forms[0].strTempQueryVal.focus();
	popup('popUpDiv', '80', '100');
		
	}else{
				
		document.forms[0].strTempQueryVal.value = "";
		
	}

	}
	
	
	function displayQuery(index){
		
		gblIndexVal = index;
		

		document.forms[0].strTempQueryVal.value = document.getElementById("strComboQuery"+gblIndexVal).value;
		document.forms[0].strTempQueryVal.focus();
		popup('popUpDiv', '80', '100');
		
	}
	
	

		function setQuery(mode){
		
			if(mode == 1){
			
			  var hisValidator = new HISValidator("rptparamBean");
	            
	          	  hisValidator.addValidation("strTempQueryVal", "req", "Combo Content is a Mandatory Field" );
	          	  hisValidator.addValidation("strTempQueryVal", "maxlen=1000", "Combo Content cannot be more then 1000 Characters" );
	          	                  
	            var retVal = hisValidator.validate(); 
				hisValidator.clearAllValidations();
	          if(retVal){
			
	        	    document.getElementById("strInConstantValueDivId"+gblIndexVal).disabled = true;
	        	  
					document.getElementById("strComboQuery"+gblIndexVal).value = document.forms[0].strTempQueryVal.value;
					
					document.getElementById("colNameDivId"+gblIndexVal).innerHTML = "<a href='#' title='click to view or edit the Combo Query' onclick='displayQuery(\""+gblIndexVal+"\");'>"+document.getElementById("strProcInColumnDtls"+gblIndexVal).value+"</a>";
										
				}else{
				
					return false;
				}
			}else{
				
				var cVal = confirm("Cancel will Clear the Query, Do you want to continue...");
				
				if(cVal){
					

					document.getElementById("strInConstantValueDivId"+gblIndexVal).disabled = false;
					document.forms[0].strTempQueryVal.value = "";
					document.getElementById("colNameDivId"+gblIndexVal).innerHTML = document.getElementById("strProcInColumnDtls"+gblIndexVal).value;
					
					document.getElementById("strParamCompType"+gblIndexVal).selectedIndex = 0;
					document.getElementById("strParamCompType"+gblIndexVal).focus();
					
				}else{
					
					return false;
				}
				
							
			}
			
			
			
			document.forms[0].strTempQueryVal.value = "";
				hide_popup('popUpDiv');
		}

	
	
		var gblIndex = "0";
		
	function getPreInParamValues(obj , index){
		
		gblIndex = index;
		
		var strReportTemplateId = document.forms[0].strReportTemplateId[document.forms[0].strReportTemplateId.selectedIndex].value;
		
		if (obj.value != '0') {

			
			var mode = 'GETINOUTCOMBO';
			var trProcedureName = obj.value;

			var url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
					+ "&strReportTemplateId=" + strReportTemplateId.split('^')[0]+"&strParamTypeId="+obj.value;
			ajaxFunction(url, "4");

		} else {

			document.getElementById("strPreInParamValuesDivId"+index).innerHTML = "<select name='strPreInParamValues' class='comboNormal'><option value='0'>Select Value</option></select>";
					
		}
		
				
		
		
	}
	
	
	function cancelReport(tempId , procId){
		
		
		var conf =  confirm("Report Cancellation, Are you Sure ?"); 
		
		if(conf){
			
			var reConf =  confirm("Canceling the Report will Permenently Delete the Report");
			
			if(reConf){
				

				var mode = 'CANCELRPT';
		
				var url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
						+ "&strReportTemplateId=" + tempId+"&strReportProcId="+procId;
				
				 				
				ajaxFunction(url, "5");

				
								
			}
			
			
		}
		
		
		
	}
	
	

	function viewReport(tempId , procId , displayName , reportType){
		 
				var mode = 'VIEWRPT';
		
				var url = "DynamicReportParamMstCNT.cnt?hmode=" + mode
						+ "&strReportTemplateId=" + tempId+"&strReportProcId="+procId+"&strReportTypeId="+reportType+"&strReportDisplayName="+displayName;
				
							
				ajaxFunction(url, "6");
  		
	}
	 
	
	function getAjaxResponse(res, mode) {
		var objVal1;
		var err = document.getElementById("errMsg");
		var temp1 = res.split("####");

		if (temp1[0] == "ERROR") {
			
			
			err.innerHTML = temp1[1];
		} else {

			 
			if (mode == "1") {

				
				document.getElementById("reportNameDivId").innerHTML = "<select name='strReportTemplateId' class='comboMax' onchange='getPreviousReportDetails(this);' >"
						+ res + "</select>";
								

				document.getElementById("reportDetaildDivId").style.display = "none";
				document.getElementById("previousLevelsDivId").style.display = "none";
				document.getElementById("procedureDetaildDivId").style.display = "none";
				document.getElementById("reportParamDivId").style.display = "none";
				document.getElementById("rptDisplayDetaildDivId").style.display = "none";
					
						
			}
			
			if (mode == "2") {

			 
				$("#reportParamDivId").slideUp('fast' , function(){
				
					document.getElementById("reportParamDivId").innerHTML = res;
				});
				
				//document.getElementById("reportParamDivId").style.display = "block";
				
				$("#reportParamDivId").slideDown('slow');
				
			}
			
			if (mode == "3") {

				var temp = res.split("@@");

				
				document.getElementById("previousLevelsDivId").innerHTML = temp[0];
				
				$("#previousLevelsDivId").slideUp('fast');
				
				//document.getElementById("previousLevelsDivId").style.display = "block";
				
				$("#previousLevelsDivId").slideDown('slow');
				
				 
				if(temp[1] == '1'){
					
					document.getElementById("procedureDetaildDivId").style.display = "none";
					document.getElementById("rptDisplayDetaildDivId").style.display = "none";
					document.getElementById("reportParamDivId").style.display = "none";
					document.getElementById("reportButtonsDivId").style.display = "none";
					
					
					
				}else{
					
					displayReportDetails();					
				}
				
			}
						
			
			if (mode == "4") {

				document.getElementById("strPreInParamValuesDivId"+gblIndex).innerHTML = "<select name='strPreInParamValues' id='strPreInParamValues"+gblIndex+"' class='comboNormal' onchange='setDefaultValues(this, \""+gblIndex+"\");' >"+res+"</select>";
				 
				
			}
			
			if(mode == "5"){
				 
				getPreviousReportDetails(document.forms[0].strReportTemplateId[document.forms[0].strReportTemplateId.selectedIndex]);
				
			}
			
			
			if(mode == "6"){
				
				
				 
				document.getElementById("viewContentDivId").innerHTML = res;
				popup('viewContentPopupDivId', '120', '120');
				
			}
			
			
						
		}
	}
	
	
	function setDefaultValues( obj , index){
		
		if(obj.value == '0'){

			document.getElementById("strInConstantValueDivId"+index).value = 0;
			document.getElementById("strInConstantValueDivId"+index).disabled = false;
			
			document.getElementById("strInDisplayName"+index).value =  document.getElementById("strProcInColumnDtls"+index).value;
			
			if(document.getElementById("strPreInParamId"+index)!= null)
			document.getElementById("strPreInParamId"+index).value = "";
			
			
		}else{
			
			document.getElementById("strInConstantValueDivId"+index).value = obj.value.split('^')[2];
			document.getElementById("strInConstantValueDivId"+index).disabled = true;
			
			document.getElementById("strInDisplayName"+index).value = obj.value.split('^')[3];
			
			if(document.getElementById("strPreInParamId"+index)!= null)
			document.getElementById("strPreInParamId"+index).value = obj.value.split('^')[1];
			
			
		}
		
	}