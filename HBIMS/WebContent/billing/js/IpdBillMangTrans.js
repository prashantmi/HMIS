
		/**
		 * desableCrNo
		 * @param {}  
		 */
		 function desableCrNo() {
		 	
		 //	document.forms[0].strCrNo.disabled = true;
		 	
		 }

	/**
	 * setTreatCatAndWard
	 * @param {}  
	 */
	 function setTreatCatAndWard() 
	 {	 	
		 if(document.forms[0].strNewTreatmentCategory.length>0)
		 {
			 document.forms[0].strTempTreatCat.value = 	document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value;
		 }
		 	 
		 if(document.getElementsByName("strAdmissionDtlHidVal").length > 0)
		 {		
			 var deptCode = document.forms[0].strAdmissionDtlHidVal.value.split('^')[5];
			 if(document.forms[0].strDepartment!=undefined)
			 {
				 document.forms[0].strDepartment.value = deptCode.substring(0,3);		
				 getSpecialWardDtls();
			 }
		 }	
		 if(document.forms[0].strWardType!=undefined && document.getElementsByName("strWardType").length > 0 && document.forms[0].strWardType.length>0)
		 {		
			 document.forms[0].strTempWardCode.value = 	document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;		 	
		 }
	 }


	/**
	 * setRateValue
	 * @param {String} index 
	 */
	 function setRateValue(index) {
	 	
	 		_id("strOfflineTariffRateUnit"+index).value = _id("strOfflineTempTariffRateUnit"+index).value; 
	 	
	 	calcOffLineTariffNetAmount(index);
	 	
	 }

 
	 /**
	  * getSpecialWardDtls
	  * @param {String} deptId 
	  */
	  function getSpecialWardDtls() 
	  {
	 	  	if(document.forms[0].strDepartment!=undefined)
	 	  	{
	 	  		var deptId = document.forms[0].strDepartment[document.forms[0].strDepartment.selectedIndex].value;		 	  	
		  		var hmode = "SPLWARDDTL"; 
				var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&strDept="+deptId;																
				ajaxFunction(url,"17");
	 	  	}	  	
	  }
	  
function getWardDtls(obj) 
{	  	
 	  	var deptId = obj.value;	 	  	
  		var hmode = "GETCONSULTANT"; 
  		var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&strUnit="+deptId;
  		if(obj.name=='strUnitold')
  			ajaxFunction(url,"21");
  		else
  			ajaxFunction(url,"22");
}
function getUnitdtls(obj) 
{	  	
 	  	var deptId = obj.value;	 	  	
  		var hmode = "GETUNITDTLS"; 
  		var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&strDept="+deptId;
  		if(obj.name=='strDepartmentOld')
  			ajaxFunction(url,"23");
  		else
  			ajaxFunction(url,"24");
}
	/**
	 * setFocusToTariffCode
	 * @param {Event} e 
	 */
	 function setFocusToTariffCode(e) 
	 {
	 		if(e.keyCode == 13 )
	 		{	 			
	 			document.forms[0].strTariffCode.focus();
	 			return false;
	 		}
	 }


 
	 /**
	  * getTariffByCode
	  * @param {Object} teriffCode 
	  * @param {event} e
	  */
	  function getTariffByCode(teriffCode , e) 
	  {
	  	if(e.keyCode == 13 && document.forms[0].strTariffCode.value.length > 0)
	  	{
			var hmode = "TARIFFCODEDTLS"; 
		 	var treatCat = "0";
	  	 	if(document.getElementsByName("strTreatmentCategory").length > 0)
	  	 	{
	 			treatCat = document.forms[0].strTreatmentCategory.value;	
	 		}
	  	  	if(document.getElementsByName("strNewTreatmentCategory").length > 0)
	  	  	{
	 			treatCat =  document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value ;	
	 		}
	  		var wardType = "0";
	  		 
	  		if(document.getElementsByName("strWardType").length > 0 )
	  		{
	  		 		wardType = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;	  		 	
	  		}
	  		else
	  		{
	  		 		wardType = document.forms[0].strWardCode.value;
	  		}
	  		
	  		var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService=2"+"&treatmentCat="+treatCat+"&ward="+wardType+"&tariffCode="+document.forms[0].strTariffCode.value+"&strEndDate="+document.forms[0].strEndDate.value;
		 
			ajaxFunction(url,"14");
			return false;
		}	   	
	  	
	  	return false;
	  }
	 

	/**
	 * setPartPaymentAmount
	 * @param {}  
	 */
	 function setPartPaymentAmount() {
	 	 
	 	
	 	var hmode = "PARTPAYMENTAMTDTL"; 
				
			  
	  		  	var treatCat = "0";
	  		  	
	  		 if(document.getElementsByName("strTreatmentCategory").length > 0){
	 	
	 			treatCat = document.forms[0].strTreatmentCategory.value;	
	 	}
	  		  	
	  		  	
	  		  	if(document.getElementsByName("strNewTreatmentCategory").length > 0){
	 	
	 			treatCat =  document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value ;	
	 	}
	  		  	
	  		 var wardType = "0";
	  		 
	  		 if(document.getElementsByName("strWardType").length > 0 ){
	  		 	
	  		 		wardType = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;
	  		 	
	  		 }else{
	  		 	
	  		 		wardType = document.forms[0].strWardCode.value;
	  		 	
	  		 }
	  		  	 
				
			var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&treatmentCat="+treatCat+"&ward="+wardType;
		 
						
			ajaxFunction(url,"16");
								 
	 	
	 }


	/**
	 * validateTariffReset
	 * @param {}  
	 */
	 function validateTariffReset() {
	 	
	  
	 			 
	 	   		setTreatCatAndWard();
	 	   		
	 	   		resetMultiRow('1');
	 	   		 generateRows();
	 	   	 	   			 	   		
	 	 		   if(document.getElementsByName("strSpecialWardType").length > 0){
			
					  getDefaultTariffList();  	
	 	 		   }
	 			 
	 	
	 }

	
	 /**
	  * getTariffByCode
	  * @param {Object} teriffCode 
	  * @param {event} e
	  */
	  function getDefaultTariffList() {
	  		  	
	  		// alert("start date : "+document.forms[0].strStartDate.value);
	  		// alert("end Date : "+document.forms[0].strEndDate.value);
	  		 
	  		 var hisValidator = new HISValidator("ipdBillManagementTransBean");  
				hisValidator.addValidation("strStartDate", "dtltet="+document.forms[0].strEndDate.value, "End Date Should be Grater than or Equal to Start Date" );
		
			var retVal = hisValidator.validate(); 
			 hisValidator.clearAllValidations();
				if(!retVal ){
				 
				 	 		 	
				
					
					return false;
					
				} 
	  		 	  		  	
	  		  	var treatCat = "0";
	  		  	
	  		  	  	if(document.getElementsByName("strTreatmentCategory").length > 0){
	 	
	 			treatCat = document.forms[0].strTreatmentCategory.value;	
	 	}
	  		  	
	  		  	
	  		  	if(document.getElementsByName("strNewTreatmentCategory").length > 0){
	 	
	 			treatCat =  document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value ;	
	 	}
	  		  	
	  		 var wardType = "0";
	  		 
	  		 if(document.getElementsByName("strWardType").length > 0 ){
	  		 	
	  		 		wardType = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;
	  		 	
	  		 }else{
	  		 	
	  		 		wardType = document.forms[0].strWardCode.value;
	  		 	
	  		 }
	  		  	
	  		 
	  		 var splWardType = document.forms[0].strSpecialWardType[document.forms[0].strSpecialWardType.selectedIndex].value; 	
	  		  	
	   						 
			var hmode = "DEFULTTARIFFLIST"; 
				 
				
			var url ="IpdBillManagementTransCNT.cnt?hmode="+hmode+"&hService=2&treatmentCat="+treatCat+"&ipdCharge="+wardType+"&ward="+splWardType+"&startDate="+document.forms[0].strStartDate.value+"&endDate="+document.forms[0].strEndDate.value+"&wardType="+wardType;
			
						
			ajaxFunction(url,"15");
								 				 			 
	  }
	 
	 

	/**
	 * setTreatmentCategory
	 */
	 function setInitValues() {
	 	
	 	if(document.getElementsByName("strTreatmentCategory").length > 0){
	 	 	
	 			document.forms[0].strNewTreatmentCategory.value = document.forms[0].strTreatmentCategory.value;	
	 	}
	 	
	 	/*	if(document.getElementsByName("strActOpenDate").length > 0 && document.getElementsByName("strStartDate").length > 0){
	 	
	 			document.forms[0].strStartDate.value = document.forms[0].strActOpenDate.value;	
	 	} */
	 		 	
	 }


	
	/**
	 * getPreviousDetails
	 * @param {Object} dateObj 
	 */
	 function getPreviousDetails(dateObj) {
	 	 
	 	
	 	if(dateObj.value != 0){
	 		
	 		var accNo = document.forms[0].strAcctNo.value;
	 		var dateRang = dateObj.value.replace("#" , "^");
	 		
	 		
	 			var hmode = "PREVDTL"; 
				 
				
			var url ="IpdBillManagementTransCNT.cnt?hmode="+hmode+"&strAccNo="+accNo+"&strPreviousDates="+dateRang;
			 					
									
			ajaxFunction(url,"18");
	 		
	 		
	 		
	 	}else{
	 		
	 		_id("previousDtlsContentDiv").innerHTML = "";
	 		_id("previousDtlsContentDiv").style.display = "none";
	 		
	 		_id("deleteImg").style.display = "none";
	 			 		
	 		
	 	}
	 	
	 }
	

	/**
	 * closePreviousPopup
	 * @param {}  
	 */
	 function closePreviousPopup() {
	 	
	 	hide_popup('previousDtls');
	 	
	 	document.forms[0].strPreviousDates.selectedIndex = 0;
	 	_id("previousDtlsContentDiv").innerHTML = "";
	 		_id("previousDtlsContentDiv").style.display = "none";
	 		
	 		_id("deleteImg").style.display = "none";
	 		 	
	 	
	 }

		
	/**
	 * deletePreviousDtls
	 * @param {}  
	 */
	 function deletePreviousDtls() {
		 
	 	
	 		var prevValues = "";
	 		var date = "";
	 		var rate="";
	 		var qty="";
	 		var flag = false;
	 		var temp1="";
	 	
	 		var prevCheckObj = document.getElementsByName("strPreviousCheck");
	 		
	 		//alert(prevCheckObj);
	 		for(var i=0; i<prevCheckObj.length; i++) {
	 		
		 		if(prevCheckObj[i].checked){
		 			
		 				if(!flag){
		 				//alert(i);
		 				//alert(_id("date"+i).value);
		 				date=_id("date"+i).value;
		 				
		 				rate=_id("rate"+i).value;
		 				qty=_id("qty"+i).value;
		 				prevValues = prevCheckObj[i].value;
		 		     	temp1=prevValues.split('##')[0];
		 				//alert(temp1);
		 				prevValues=temp1+"^"+date+"^"+rate+"^"+qty;
		 				//alert(prevValues);
		 				flag = true; 					
		 			
				 		}else{
				 			
				 			prevValues = prevValues +"$"+prevCheckObj[i].value+"$"+date+"$"+rate+"$"+qty;
				 			
				 		}
		 		
		 	}	
	 	
	 	}
	 		_id("deleteImg").style.display = "none";
	 	
	 		if(confirm("Are You Sure to Modify the Selected Record(s)?")){
	 			
	 			var hmode = "DELPREVDTL"; 
				var url ="IpdBillManagementTransCNT.cnt?hmode="+hmode+"&strPreviousVals="+prevValues;
			 					
				ajaxFunction(url,"19");
	 			
	 		}
	 		else
	 			_id("deleteImg").style.display = "block";
	 	
	 	
	 		
	 	
	}		


	/**
	 * deleteButtonVisibility
	 * @param {}  
	 */
	 function deleteButtonVisibility(obj) {
		// alert(obj.id.substr(16,20));
		 var temp1=obj.id.substr(16,20);
		 var temp2=obj.id.substr(16,20);
		 var temp3=obj.id.substr(16,20);
		 var textboxid="txtbox"+temp1;
		 var textboxid1="txtbox1"+temp2;
		 var textboxid2="txtbox2"+temp3;
		 //alert(textboxid);
		 
	 	
	 	var prevCheckObj = document.getElementsByName("strPreviousCheck");
	 	
	 	_id("deleteImg").style.display = "none";
	 	
	 	for(var i=0; i<prevCheckObj.length; i++) {
	 		
	 		if(prevCheckObj[i].checked){
	 			
	 			_id(textboxid).innerHTML = "<input type='textbox' name='qty"+i+"' id='qty"+i+"' style='width: 23px;' value='' >";
	 			_id(textboxid1).innerHTML = "<input type='textbox' name='date"+i+"' id='date"+i+"'style='width: 75px;' value='' >";
	 			var temp=prevCheckObj[i].value.split('##');
	 			//alert(temp[1]);
	 			//alert(temp[2]);
	 	   	if(temp[1]=='1')
	 				{
	 					_id(textboxid2).innerHTML = "<input type='textbox' name='rate"+i+"' id='rate"+i+"' style='width: 40px;' value='' >";
	 				}
	 	   	
	 	   	else
	 	   		{
	 	    	
	 	   		_id(textboxid2).innerHTML = "<input type='hidden' name='rate"+i+"' id='rate"+i+"' style='width: 40px;' value='"+temp[2]+"' ><td class='multiControl' width='15%'>"+temp[2]+"</td>";
	 	   		}
	 			_id("deleteImg").style.display = "block";
	 			return;
	 			
	 		}
	 		
	 	}	
	 	
	 	
	 }
	


function validate(){

	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	hisValidator.addValidation("strBillTypeCombo", "dontselect=0", "Bill Type is a Mandatory Field" );
		
	var retVal = hisValidator.validate(); 
	 hisValidator.clearAllValidations();
		if(retVal){
		
	//	document.forms[0].strCrNo.disabled = false;		
		
		document.forms[0].hmode.value = "SHOWRPT";
		document.forms[0].submit();
		
		}else{
		
		return false;
		}

	}
	
	
	function generateRows(){
		if(!showAlert())
			return false;
			addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffDate','strPriority','strDiscount','strDiscountType','strDiscountAmt','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','s','d','s','t','s','t','t','t','t','t'),'1','1','R');
			
			
			var tempVal = "strOfflineTariffName1-"+document.multirow.rowIndex1.value;
	 
			var tempObj = _id(tempVal);
		
		tempObj.select();
		tempObj.focus();
			
			
	}

	function buttonLogicsOnChangeCombos(){
		
		 	   
		 	   disableButton("Add Service");
			   disableButton("Part Payment");
			   disableButton("Update Account");
			   disableButton("Client Approval");
			   disableButton("Bill Approval");
			   disableButton("View Bill");
			   //enableButton("View Charges");	
		
	}


	function buttonLogicsOnRecordCheck(){  
   	 
   	 var chkObj = document.getElementsByName("chk");
   	 
   	 var count = parseInt("0");
   	 
   	 	for(var i=0; i<chkObj.length; i++) {
   	 	
   	 		if(chkObj[i].checked){
   	 			
   	 			count = count + 1;
   	 			
   	 		}		
   	 		
   	 	}
   	 if(count != 1){
   	 	
   	 	buttonLogicsOnChangeCombos();
   	 	return false;
   	 }
   	 
   	if(_id("comboid0") != null){    	
   	   
   	   	
   	   if(_id("comboid0").value == '0') 
   	   {
   	   	alert("Please Select Account Status");
   	   	return false;
   	   }
   	  
	if(_id("comboid0").value == '1' || _id("comboid0").value == '2')
	{ 
	            enableButton("Add Service");
				enableButton("Part Payment");
				enableButton("Update Account");
				enableButton("Client Approval");
				enableButton("Bill Approval");
				enableButton("View Bill");
				//enableButton("View Charges");
							
	}
    else
    {
               disableButton("Add Service");
			   disableButton("Part Payment");
			   enableButton("Update Account");
			   disableButton("Client Approval");
			   enableButton("View Bill");
			   disableButton("Bill Approval");
			    
    }
  
  
  		if(_id("comboid1").value == '1' || _id("comboid2").value == '1'){
  			
  				 disableButton("Part Payment");  			
  		}else{
  				enableButton("Part Payment");
  		}
  		
  		
  		
  
  
   	}else{
   		
   		 		enableButton("Add Service");
				enableButton("Part Payment");
				enableButton("Update Account");
				enableButton("Client Approval");
				enableButton("Bill Approval");
				enableButton("View Bill");
				//enableButton("View Charges");
   		
   		
   	}
  
}


	
	function buttonLogicsOnClick(modeNo, mode , display)
	{		
		if(modeNo != 6)
		{
			if(_id("comboid1") != null && _id("comboid1").value =="0")
			{
				alert("Please Select A Department Name");
				_id("comboid1").focus();
				return;
			}			
			if(_id("comboid2") != null && _id("comboid2").value =="0")
			{
				alert("Please Select A Ward Name");
				_id("comboid2").focus();
				return;
			}			
			 var chkObj = document.getElementsByName("chk");   	 
   			 var count = parseInt("0");
   	 
   	 			for(var i=0; i<chkObj.length; i++) 
   	 			{   	 	
   	 				if(chkObj[i].checked)
   	 				{   	 			
   	 					count = count + 1;   	 			
   	 				}   	 		
   	 			}
   	 
	   		 if(count != 1)
	   		 {   	 	
	   	 		alert("Please Select A Record");
	   	 		return false;
	   		 }			
			 add(mode);			
		}
		else
		{			
			 add(mode);
		}		
	}



function userDefinedOnLoadFunc()
{
	showMenu();
}
//-------------Add Services(Pateint Approval Dtl)-------------//
function checkPatientApprovalDtl()
{
	  var ipdRoundOffObj = document.getElementsByName("strIpdRoundOff");
	  if(ipdRoundOffObj.length > 0)
	  {  	
	  	 var strIpdRoundOff = ipdRoundOffObj[0].value;  	 
		  if(strIpdRoundOff == 1)
		  {
		    var Amt = document.forms[0].totalRecAmtDivId1.value ;
		    var rVal = roundValue(Amt,0);
		    //_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+rVal+"</u></font></a>";
		    _id("totalRecAmtDivId").innerHTML = "<font color='red'>"+rVal+"</font>";
		  }  
	  } 
	  var value = "0";  
	  if(document.getElementsByName("strIpdThirdPartyBilling").length > 0)
	  {  	
	  	value = document.getElementsByName("strIpdThirdPartyBilling")[0].value;  	
	  }  
	  if(value == 1)
	  {	   
		  if(_id("detailsdividDummy") != null)
		  {	   	
			  _id("detailsdividDummy").style.display = "block";	   	
		  }	   	
	  }

	  if(document.forms[0].strStartDate!=undefined && document.forms[0].strEndDate!=undefined) 
	  {
		  var obj = compareDate(document.forms[0].strStartDate.value , document.forms[0].strEndDate.value);	
		  /*alert(document.forms[0].strStartDate.value);
		  alert(document.forms[0].strEndDate.value);
		  alert(obj.mode);*/
		  if(obj.mode == '2')
		  {	  	
		  	document.forms[0].strStartDate.value = document.forms[0].strEndDate.value;	  	
		  }
		  //alert(document.forms[0].strStartDate.value);
	  }
	  if(document.forms[0].strNewTreatmentCategory!=undefined)
	  {
		  setTreatCatAndWard();
	  }
	  if(document.forms[0].strTariffCode!=undefined)
	  {
		  document.forms[0].strTariffCode.focus();  
	  }	
	  
	  document.forms[0].strNewTreatmentCategory.disabled=true;
	  document.forms[0].strWardType.disabled=true;

} 


 /**
  * goFuncValidation - used in Add Services 
  * @param {}  
  */
  function goFuncValidation() {
  	
  	var hisValidator = new HISValidator("ipdBillManagementTransBean"); 
  	
  		hisValidator.addValidation("strDepartment","dontselect=0", "Please Select a Department" );
  		hisValidator.addValidation("strNewTreatmentCategory","dontselect=0", "Please Select a Tariff Category" );
  		hisValidator.addValidation("strWardType","dontselect=0", "Please Select a Charge Type" );
  		hisValidator.addValidation("strStartDate", "req","Start Date is a Mandaotry Field");
  		hisValidator.addValidation("strEndDate", "req","End Date is a Mandaotry Field");
	//	hisValidator.addValidation("strActOpenDate", "dtltet="+document.forms[0].strStartDate.value,"Start Date should be Grater than or Equal to Account Open Date ");
		hisValidator.addValidation("strStartDate", "dtltet="+document.forms[0].strEndDate.value,"Start Date should be Lesser than or Equal to End Date");
		
		
  		if(document.getElementsByName("strIpdBillManagementMode").length > 0 && document.getElementsByName("strIpdBillManagementMode")[0].value == '2'){
  			
  			hisValidator.addValidation("strSpecialWardType","dontselect=0", "Please Select a Ward Name" );
  			
  		}
  		
  		
  		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal){
				 
			_id("otherDetailsDivId").style.display = "none"; 
			
				 _id("deptLabelDivId").innerHTML = document.forms[0].strDepartment[document.forms[0].strDepartment.selectedIndex].text;
				 _id("deptLabelDivId").style.display = "block";
				 _id("deptComboDivId").style.display = "none";
				 
				 _id("treatCatLabelDivId").innerHTML = document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].text;
				 _id("treatCatLabelDivId").style.display = "block";
				 _id("treatCatComboDivId").style.display = "none";
				 
				 _id("wardTypeLabelDivId").innerHTML = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].text;
				 _id("wardTypeLabelDivId").style.display = "block";
				 _id("wardTypeDivId").style.display = "none";
				 
				  _id("startDateLabelDivId").innerHTML = document.forms[0].strStartDate.value;
				 _id("startDateLabelDivId").style.display = "block";
				 _id("startDateDivId").style.display = "none";
				 
				 
				  _id("endDateLabelDivId").innerHTML = document.forms[0].strEndDate.value;
				 _id("endDateLabelDivId").style.display = "block";
				 _id("endDateDivId").style.display = "none";
				 
				 
				 
				 if(document.getElementsByName("strIpdBillManagementMode").length > 0 && document.getElementsByName("strIpdBillManagementMode")[0].value == 2){
				 	
				 	 _id("specialWardTypeLabelDivId").innerHTML = document.forms[0].strSpecialWardType[document.forms[0].strSpecialWardType.selectedIndex].text;
				 	 _id("specialWardTypeLabelDivId").style.display = "block";
				 	 _id("specialWardTypeDivId").style.display = "none";
				 	
				 }
				 
				 	 _id("goButtonId").style.display = "none";
				
					validateTariffReset();
				
				 
				 
				 
		}else{
		
		return false;
		}
  		
  	
  	
  }



/*----------------Method Used to get Process & Un-Process Details -------------------*/
/*------------------------------Bill Approval---------------------------------------*/
 
 
 function getBillApprovalDtl(obj,grpId,acctNo,index)
 { 	
 	  _id("errMsg").innerHTML = ""; 	    
 	  var obj = document.getElementsByName("flagConfig");
 	  for(var i = 0; i < obj.length ; i++)
 	  {		
		if(i != index)
		_id("billapprovalId"+i).style.display = "none";		
 	  }	
 	  var flg = _id("delindex"+index).value;
 	  if(flg == '0')
 	  {
			gblPartIndex = index;
			_id("delindex"+index).value = '1';		
			fetchPopUpData13(grpId,acctNo,index);		
 	  }
 	  else
 	  {		
		var ptlObj = _id("billapprovalId"+index);
		if(ptlObj.style.display == "block")
		{			
			ptlObj.style.display = "none";
		}
		else
		{
			ptlObj.style.display = "block";
		}		
 	  } 	
 }
 
 
 /*
 function getBillApprovalDtl(obj,grpId,acctNo,index) 
 {
   
   _id("errMsg").innerHTML = "";
   if(_id("addButton"+index)!=null){
   	
    // alert("Button Value-->"+_id("addButton"+index).value); 
     if(_id("addButton"+index).value == 0){
        alert("Plz Save Previous Selected Un-Processed Record !!!");
      }else{
      	
      	var currId = document.forms[0].currId.value;
      	var flg=document.getElementsByName("flag");
      	var flag = 0;
      	var obj = _id("billapprovalId"+currId);
      	
  	  if(currId >= 0 && index != currId){
    	obj.style.display="none";
      }else{
       //same button is clicked
		if(index == currId){
		 // alert((obj.style.display).toUpperCase());
			if((obj.style.display).toUpperCase() == "BLOCK") {
				obj.style.display="none";
				flag = 1;
			}
		}
	  
      }
      
      if(flg[index].value == "0"){
      //_id("id"+currId).style.display="none";
        fetchPopUpData13(grpId,acctNo,index);
        document.forms[0].flag[index].value = "1";
        
      }else{
      	
  	   if(flag == 0){ 
  	   	_id("billapprovalId"+index).style.display="block";
     	_id("headerid0"+index).style.display = "block";
        _id("headerid1"+index).style.display = "block";
       
       }
      }
      
      if(flag == 1){
         document.forms[0].currId.value = -1;		//all div closed
      }else{	
 	    document.forms[0].currId.value = index;	//particular div is open
      }
    }
  }else{
  	
   var currId = document.forms[0].currId.value;
   var flg=document.getElementsByName("flag");
   var flag = 0;
   var obj = _id("billapprovalId"+currId);
   
   if(currId >= 0 && index != currId) 
   {
    	obj.style.display="none";
   }
   else 
   {
     
    //same button is clicked
		if(index == currId)
		 {
		 // alert((obj.style.display).toUpperCase());
			if((obj.style.display).toUpperCase() == "BLOCK") 
			{
				obj.style.display="none";
				flag = 1;
			}
		}
	  
   }
   if(flg[index].value == "0")
   {
    //_id("id"+currId).style.display="none";
      fetchPopUpData13(grpId,acctNo,index);
      document.forms[0].flag[index].value = "1";
      
   }
   else
   {
  	 if(flag == 0) 
  	 { 
  	   	_id("billapprovalId"+index).style.display="block";
     	_id("headerid0"+index).style.display = "block";
        _id("headerid1"+index).style.display = "block";
        
     }
   }
   if(flag == 1)
       
 		document.forms[0].currId.value = -1;		//all div closed
 	else	
 	    
 		document.forms[0].currId.value = index;	//particular div is open
  }
} 
*/
/*-------------------Ajax Function For Bill Approval ---------------------*/
 function fetchPopUpData13(grpId,acctNo,index)
 { 
     var mode="UNITVAL13";
     var modeVal = grpId+"^"+acctNo+"^"+index;
     var url="IpdBillManagementTransCNT.cnt?hmode="+mode+"&modName="+modeVal;
     ajaxFunction(url,"13");
 }
 
/*-------------------------Add Service Method------------------------------*/   
function getInvisbleforAddService()
{   
 
 	document.forms[0].hmode.value="ADDSERVICE";
	document.forms[0].submit();
    
    
}
 
 function hideGrpDetails(index,k)
  {
  	
    	
    var findex= k+""+index;
    resetRow(k,index); 
   // _id("billapprovalId"+index).style.display = "none";
    
    _id("detailsdivid0"+index).style.display = "none";
    _id("detailsdivid1"+index).style.display = "none";
    
   // _id("headerid0"+index).style.display = "none";
    //_id("headerid1"+index).style.display = "none";
    _id("minus10"+index).style.display="none";
    _id("plus10"+index).style.display="block";
     document.forms[0].button10.value = 0;
    _id("minus30"+index).style.display="none";
    _id("plus30"+index).style.display="block";
    document.forms[0].button30.value = 0;
  }
  function resetRow(k,i)
  {
   /* k is Un Processed Record */
   /* i is Index of Process    */
  
   
   var checkBoxLength = document.getElementsByName("chkBox1");
   var RefundQty = document.getElementsByName("strRefundQty");
   var tmpFinalAmt  = _id("strTotalFinalAmtHid"+i).value;
   _id("strTotalFinalAmt"+i).value = tmpFinalAmt;
    _id("strTotalFinalAmtDivId"+i).innerHTML = tmpFinalAmt;
     
	     var findex= k+""+i;
	//     alert("Un-ProcessQty Value is-->"+_id("strUnProcessQty"+findex).value);
	     if(_id("chkBox"+findex) != null)     // Look Here
         {   
            _id("strNetAmt"+findex).value = 0;
            _id("strNetAmt"+findex).disabled  = true;
            _id("strRefundQty"+findex).value  = 0;
            _id("strRefundQty"+findex).disabled  = true;
             _id("strUnitCombo"+findex).selectedIndex = 0;
            _id("strUnitCombo"+findex).disabled  = true;
            
            
            
            _id("chkBox"+findex).checked = false;
            _id("chkBox"+findex).value = findex;
         } 
         else
         {
            _id("strNetAmt"+findex).value = 0;
            _id("strNetAmt"+findex).disabled  = true;
            _id("strRefundQty"+findex).disabled  = true;
              _id("strUnitCombo"+findex).disabled  = true;
            _id("strRefundQty"+findex).value  = 0;
            _id("chkBox"+findex).checked = false;
            _id("chkBox"+findex).value = findex; 
         }
       
          
   finalAmtCalculation2();
  }
  /*************Un-Process Service's Refund Quantity Text Box**********************/
  
/***Testing Function***/
function isInteger(k,i)
{   
    var findex= k+""+i;
    var strRefundQty    = _id("strRefundQty"+findex).value;
    var i;
    for (i = 0; i < strRefundQty.length; i++)
    {   
        // Check that current character is number.
        var c = strRefundQty.charAt(i);
        if (((c < "0") || (c > "9"))) 
      //  alert("Character");
        return false;
    }
    // All characters are numbers.
  //  alert("No is");
    return true;
}
function chkUnProcessService(i,k)
{
    var findex= k+""+i;
    
    var flg = false;
    
    
    var checkBoxLength = document.getElementsByName("chkBox"+i);
    for(var j = 0 ; j < checkBoxLength.length; j ++)
    {
    //   alert("checkBoxLength["+i+"].value--->>"+checkBoxLength[j].value);
       if(checkBoxLength[j].checked)
       {
         flg = true;
       }
       
    }

	return flg;

}



/****************ADD BUTTON for Un-Process Services(BILL APPROVAL TRANSACTION)************************/
function getAddUnProcess(i,k)
 {
   var findex= k+""+i;
   
   var checkBoxLength = document.getElementsByName("chkBox1");
   
   if(_id("chkBox"+findex)!= null)
   {
      var retVal = chkUnProcessService(i,k);
      if(retVal)
      {
         if(_id("addButton"+i).value == 1)
         {
           _id("addButton"+i).value = 0;
         }
         else
         {
           _id("addButton"+i).value = 1;
         }         
         hideGrpUnProcessDetails(i,k);
      }
      else
      {
         alert("Please Select At Least One Record!!!");
      }
     
   
    /*
     if(checkBoxLength[i].value == 1)
     {
       if(_id("addButton"+i).value == 1)
       {
         _id("addButton"+i).value = 0;
       }
       else
       {
         _id("addButton"+i).value = 1;
       }
       hideGrpUnProcessDetails(i,k);
     }
     
     */
  }
  else
  {
    //   alert("Record is Not Avaliable !!!!");
      
       _id("addButton"+i).value = 1;
        _id("detailsdivid0"+index).style.display = "none";
    _id("detailsdivid1"+index).style.display = "none";
    
   // _id("headerid0"+index).style.display = "none";
    //_id("headerid1"+index).style.display = "none";
    _id("minus10"+index).style.display="none";
    _id("plus10"+index).style.display="block";
     document.forms[0].button10.value = 0;
    _id("minus30"+index).style.display="none";
    _id("plus30"+index).style.display="block";
    document.forms[0].button30.value = 0;
       
       
  }
    
 //   alert("Add Value After Opn-->"+_id("addButton"+i).value );
}
/////////////////////hideGrpDetail for Un-Process///////////////////

function hideGrpUnProcessDetails(index,k)
  {
    var findex= k+""+index;
   // resetRow1(k,index); 
    
     finalAmtCalculation22(index);
    
    _id("detailsdivid0"+index).style.display = "none";
    _id("detailsdivid1"+index).style.display = "none";
    
   // _id("headerid0"+index).style.display = "none";
    //_id("headerid1"+index).style.display = "none";
    _id("minus10"+index).style.display="none";
    _id("plus10"+index).style.display="block";
     document.forms[0].button10.value = 0;
    _id("minus30"+index).style.display="none";
    _id("plus30"+index).style.display="block";
    document.forms[0].button30.value = 0;
  }
  function resetRow1(k,i)
  {
   /* k is Un Processed Record */
   /* i is Index of Process    */
  
   var checkBoxLength = document.getElementsByName("chkBox1");
   var RefundQty = document.getElementsByName("strRefundQty");
  
	  var findex= k+""+i;
	  if(_id("chkBox"+findex).checked)     // Look Here
      {   
       _id("strNetAmt"+findex).value = 0;
       _id("strNetAmt"+findex).disabled  = true;
       _id("strRefundQty"+findex).value  = 0;
       _id("strRefundQty"+findex).disabled  = true;
       
           _id("strUnitCombo"+findex).selectedIndex = 0;
            _id("strUnitCombo"+findex).disabled  = true;
       
       _id("chkBox"+findex).checked = false;
       _id("chkBox"+findex).value = findex;
      } 
      else
      {
       _id("strNetAmt"+findex).value = 0;
       _id("strNetAmt"+findex).disabled  = true;
       _id("strRefundQty"+findex).disabled  = true;
       _id("strRefundQty"+findex).value  = 0;
       _id("chkBox"+findex).checked = false;
       _id("chkBox"+findex).value = findex; 
      } 
   finalAmtCalculation22(i);
  }
/**************Final Amt Calculation for Un-Process*******************/
function finalAmtCalculation22(i)
{ 
   //  var depositAmt   = _id("strExpDepositAmt").value;
	// var finalAmt     = _id("strNetAmt").value;
     var FinalAmtCol  = _id("strTotalFinalAmt"+i).value;
     var finalAmt     = roundValue(_id("strFinalCalAmt").value,0);
   
    // alert("Fian AmtCol-->"+FinalAmtCol+"<-finalAmt->"+finalAmt);
     _id("strFinalCalAmt").value = finalAmt; 
      _id("strFinalCalAmtDivId").innerHTML = finalAmt; 
      if(_id("strFinalAmt") != null){
     _id("strFinalAmt").value    = finalAmt;
     _id("strFinalAmtDivId").innerHTML    = finalAmt;
      }else{
      	
      	 _id("strFinalAmt").value    = 0;
     _id("strFinalAmtDivId").innerHTML    = "0.00";
      }
  
   //_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+finalAmt+"</u></font></a>";
   _id("totalRecAmtDivId").innerHTML = "<font color='red'>"+finalAmt+"</font>";
   _id("totalRecAmtDivId1").value    = finalAmt;
 //  var tempVar = _id("strTotalDisAmt").value;
 //  calcNetAmountForBillApprovalTwo(tempVar);
  
}
 
///////////////////Discount Amt POP-UP IN Bill Approval///////////////////
function Box(k,i)
{
  var findex= k+""+i;
  var FinalAmtCol  = _id("strTotalFinalAmt"+i).value;
  var tmpFinalAmt  = _id("strTotalFinalAmtHid"+i).value;
  var temp         = _id("strRefundQty"+findex).value;
  var temp1        = _id("strUnitName"+findex).value;
  var remainQty    = _id("strRemainedQty"+findex).value;
  var comboObj     = _id("strUnitCombo"+findex);
  var selectedComboVal = comboObj[comboObj.selectedIndex].value;
  
  /*  Preserve Value  */
  var totalReceivedAmt = _id("totalRecAmtDivId1").value;
  var FinalCalAmt      = _id("strFinalCalAmt").value;
  var tmpforLess       = _id("strFinalCalAmt").value;
  var FinalAmt         = _id("strFinalAmt").value;
  
  var ratVal  = selectedComboVal.split('^'); 
  var baseVal = ratVal[1];        
  var finalValue = baseVal*temp;
 
  var temp2           = _id("strTariffRate"+findex).value.split(' ');
  var temp3           = _id("strUnProcessQty"+findex).value.split(' ');
  var strUnProcessQty = _id("strUnProcessQty"+findex).value.split(' ');
  var strRefundQty    = _id("strRefundQty"+findex).value;
 // alert("Refund Qty-->>"+strRefundQty);
   
  if(strRefundQty=="")
  {
     strRefundQty = 0;
     _id("strRefundQty"+findex).value = 0;
  }   
  else
  {
    if(parseInt(strRefundQty) > parseInt(strUnProcessQty[0]))
    {
      alert("Refund Qty is Always Less Than Un-Processed Qty!!!");
      var total = 0;
      var tmpFinalAmt  = _id("strTotalFinalAmtHid"+i).value;
      var netAmt       = _id("strNetAmt"+findex).value.split('-');
     /* 
       _id("strRefundQty"+findex).value    = 0;
       _id("strNetAmt"+findex).value       = 0;
       _id("strUnitCombo"+findex).value    = 0;
       _id("strTotalFinalAmt"+i).value = tmpFinalAmt; 
       total =  manipulateValue(parseFloat(netAmt[1]),parseFloat(finalAmt),0);
       alert("total amt-->>"+total);     
       _id("strFinalCalAmt").value = total; 
       _id("strFinalAmt").value    = total;
       _id("totalRecAmtDivId").innerHTML = total;
       _id("totalRecAmtDivId1").value    = total;   
     */
       var a = document.getElementsByName("strNetAmt");
       var s=0;
       var bo  = 0 ;
       
       for(var i = 0 ; i < a.length - 1; i ++)
       {
          a[i].disabled = false;
          bo = a[i].value.split('-');   // Here We Split '-' Sign from Net Amt 
          
          if(bo[1]=="")
          {
		    s=parseFloat(s);
		  } 
		  else
		  {
		     if(typeof(bo[1])=="undefined")
		      {
		        bo[1] = 0;
		        s=parseFloat(s)+parseFloat(bo[1]);
		        //alert("BCZ Undefined::::"+s);
		      }
		      else
		      {
	           s=parseFloat(s)+parseFloat(bo[1]);
	          // alert("If Not Undefined::::"+s);
	          } 
	      } 
	     a[i].disabled = true;
       }
       //alert("Outside Loop Value of 'S' is::::"+s);
       var finalValue = s - netAmt[1];
      // alert("After Calculation Sum is:::"+finalValue);
       
       _id("strRefundQty"+findex).disabled = true;
       _id("strUnitCombo"+findex).disabled = true;
     
       var aftrCal = parseFloat(tmpFinalAmt) -  parseFloat(finalValue);
     
       _id("strTotalFinalAmt"+i).value = aftrCal;
       _id("strTotalFinalAmtDivId"+i).innerHTML = aftrCal;
       //alert("Fina Amt Of Corresponding Column::::-->"+tmpFinalAmt);
      
       _id("strNetAmt"+findex).disabled    = true;
       _id("strRefundQty"+findex).value    = 0;
       _id("chkBox"+findex).value = findex; 
       _id("chkBox"+findex).checked =  false;
       
       var netAmt         = temp2[0]*temp[0];
       var allTrfNetCost  = 0.0;
       var finaltotal     = 0.0;
     
       _id("strNetAmt"+findex).value = "-"+temp2[0]* temp[0];
       var finalAmt     = _id("strFinalCalAmt").value;
        
       
        
       allTrfNetCost  =  manipulateValue(parseFloat(finalAmt),parseFloat(netAmt),0);	
       _id("strNetAmt"+findex).value = 0;     
       _id("strFinalCalAmt").value   = allTrfNetCost; 
       _id("strFinalCalAmtDivId").innerHTML   = allTrfNetCost;
       
       _id("strFinalAmt").value      = allTrfNetCost;
       _id("strFinalAmtDivId").innerHTML      = allTrfNetCost;
       
       //_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+allTrfNetCost+"</u></font></a>";
       _id("totalRecAmtDivId").innerHTML = "<font color='red'>"+roundValue(allTrfNetCost,2)+"</font>";
       _id("totalRecAmtDivId1").value    = roundValue(allTrfNetCost,2);
    }
    if(parseInt(strRefundQty) == parseInt(strUnProcessQty[0]))
    {
      var  netAmt         = temp2[0]*temp[0];
      var  allTrfNetCost  = 0.0;
      var  finaltotal     = 0.0;
      var  changeValue    = _id("strTotalFinalAmt"+i).value;
	  var  finalAmt       = _id("strFinalCalAmt").value;
      _id("strNetAmt"+findex).value = "-"+temp2[0]* temp[0];
      finaltotal =  manipulateValue(FinalAmtCol,netAmt,1);
      _id("strTotalFinalAmt"+i).value = finaltotal;
      _id("strTotalFinalAmtDivId"+i).innerHTML = finaltotal;
      _id("strUnProcNetAmt"+findex).value = temp2[0]*temp[0];
      allTrfNetCost = manipulateValue(parseFloat(finalAmt),parseFloat(netAmt),1);	
      _id("strFinalCalAmt").value = allTrfNetCost; 
      _id("strFinalCalAmtDivId").innerHTML = allTrfNetCost;
      
      _id("strFinalAmt").value = allTrfNetCost;
      _id("strFinalAmtDivId").innerHTML = allTrfNetCost;
      
      //_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+allTrfNetCost+"</u></font></a>";
      _id("totalRecAmtDivId").innerHTML = "<font color='red'>"+roundValue(allTrfNetCost,2)+"</font>";
      _id("totalRecAmtDivId1").value    = roundValue(allTrfNetCost,2);
     }
     if( parseInt(strUnProcessQty[0])>parseInt(strRefundQty))
     {
       var netAmt = temp2[0]* temp[0];
       _id("strNetAmt"+findex).value = "-"+temp2[0]* temp[0];
       var finaltotal =  manipulateValue(FinalAmtCol,netAmt,1);
      // alert("After manipulation-->"+finaltotal);
       _id("strTotalFinalAmt"+i).value = finaltotal;
       _id("strTotalFinalAmtDivId"+i).innerHTML = finaltotal;
       _id("strUnProcNetAmt"+findex).value = temp2[0]*temp[0];
   // finalAmtCalculation(i);
  }
 }
}
  
  
  /*************************END**********************************/
 function chkBox(k,i)
 {
    var findex= k+""+i;
    var allTrfNetCost = 0.0;
        
    var unProcessQty  = _id("strUnProcessQty"+findex).value.split(' ')[0];
    var rate = _id("strTariffRate"+findex).value.split(' ')[0];
   
    var FinalAmtCol  = _id("strTotalFinalAmt"+i).value;
    var tmpFinalAmt  = _id("strTotalFinalAmtHid"+i).value;
    var refQty        = _id("strRefundQty"+findex).value;
	var unitBaseVal  = _id("strUnitCombo"+findex)[_id("strUnitCombo"+findex).selectedIndex].value.split('^')[1];
    
 
    var finalAmt     = _id("strFinalCalAmt").value;
      
     var actRate    = _id("strActTariffRate"+findex).value;  
     var serviceTax =  _id("strServiceTax"+findex).value;
  	 var discUnit 	 = _id("strDiscUnit"+findex).value;
  	 var discType 	 = _id("strDiscType"+findex).value;
 	 var rateBaseVal = _id("strRateBaseVal"+findex).value;
 	
   		  		
  	 var trfDtls = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseVal,discUnit,discType,refQty,unitBaseVal,serviceTax,0,1);
  	 
   	
  		var tariffAmt = trfDtls.oTrfAmt;
  		var disAmt    = trfDtls.oDisAmt;
  		var serAmt    = trfDtls.oSerAmt;
  		var netAmt    = trfDtls.oNetTrfAmt;
  		var actTariffAmt = trfDtls.oActTrfAmt
  		

  		  		
  		if(_id("chkBox"+findex).checked){
  			
  			 _id("strRefundQty"+findex).disabled = false;
     		 _id("strUnitCombo"+findex).disabled = false;
     		 _id("strNetAmt"+findex).disabled    = false;
  			 _id("strNetAmt"+findex).value = netAmt;
  			
  			_id("netAmountDivId"+findex).innerHTML = netAmt;
  			
  			 _id("strActualTariffNetAmt"+findex).disabled    = false;
  			 _id("strActualTariffNetAmt"+findex).value = actTariffAmt;
  			  			
  			
  			_id("strServiceTax"+findex).disabled = false;
  			
  			  _id("strSeviceTaxAmt"+findex).disabled = false;	
  			  _id("strSeviceTaxAmt"+findex).value = serAmt;	
  			
  			 _id("strDiscAmt"+findex).disabled = false;	
  			  _id("strDiscAmt"+findex).value = disAmt;	
  			
  			_id("chkBoxHidden"+findex).value = 1;	
  			_id("chkBoxHidden"+findex).disabled = false;
  		
  			
  		
  			
  		}else{
  			
  			
  			 _id("strRefundQty"+findex).disabled = true;
  		     _id("strUnitCombo"+findex).disabled = true;
		    // _id("strTotalFinalAmt"+i).value = tmpFinalAmt;
		     _id("strNetAmt"+findex).disabled    = true;
		     //_id("strRefundQty"+findex).value    = 0;
		     _id("chkBox"+findex).value = findex; 
  			 _id("strNetAmt"+findex).value = 0;
  				  					  				  			  		
			_id("netAmountDivId"+findex).innerHTML = "0.00";	  				  					  				  			  		
  				  					  				  			  			
			 _id("strActualTariffNetAmt"+findex).disabled    = true;
  			 _id("strActualTariffNetAmt"+findex).value = 0;  				  					  				  			  			

			_id("strServiceTax"+findex).disabled = true;  				  					  				  			
  				  					  				  			  			
			_id("strSeviceTaxAmt"+findex).disabled = true;	
  			  _id("strSeviceTaxAmt"+findex).value = 0;	
  			  				  					  				  			  			
  				  					  				  			  			
			_id("strDiscAmt"+findex).disabled = true;	
  			  _id("strDiscAmt"+findex).value = 0;	  				  					  				  			  			
  				  					  				  			  			
  		 	_id("chkBoxHidden"+findex).value = 0;	  			
			_id("chkBoxHidden"+findex).disabled = true;  				  					  				  			  				  			
  				  					  				  			  				  			
  		}
  	  		
  	
  }
  
  
  /**
   * addUnProcessDetails
   * @param {String} index

   */
   function addUnProcessDetails(index) {
   	
   	 
   	 var checkBoxLength = document.getElementsByName("chkBox"+index);
   	 var flag = false; 
   		
   		for(var i= 1; i<= checkBoxLength.length; i++) {
   			
   			if(_id("chkBox"+i+""+index).checked){
   				   				
   				 var refQty = parseFloat(_id("strRefundQty"+i+""+index).value);

			 		 if(refQty <= 0){
	      	
	      			alert("Refund Quantity must be Greater than Zero");
	      			return false;
	     		 }        
   				
   				flag = true;
   				
   			}
   			
   		}
   	
      if(flag){
      	
     
              
     calcBillApprovalDetails(index);
     
     
 
        _id("detailsdivid0"+index).style.display = "none";
 	   _id("detailsdivid1"+index).style.display = "none";
    
 
    _id("minus10"+index).style.display="none";
    _id("plus10"+index).style.display="block";
     document.forms[0].button10.value = 0;
    _id("minus30"+index).style.display="none";
    _id("plus30"+index).style.display="block";
    document.forms[0].button30.value = 0;
     
     
         
      }else{
         alert("Please Select At Least One Record");
         return false;
      }
   	
   }
  
  
  	/**
  	 * cancelUnProcessDetails
  	 * @param {String} index 
  	 */
  	 function cancelUnProcessDetails(index) {
  	 	
  	 		  	 	
  	 	 var checkBoxLength = document.getElementsByName("chkBox"+index);
   	
   		
   		for(var i= 1; i<= checkBoxLength.length; i++) {
   			
   			_id("chkBox"+i+""+index).checked = false;

			 _id("strRefundQty"+i+""+index).disabled = true;
  		     _id("strUnitCombo"+i+""+index).disabled = true;
		  //   _id("strTotalFinalAmt"+i).value = tmpFinalAmt;
		     _id("strNetAmt"+i+""+index).disabled    = true;
		     _id("strRefundQty"+i+""+index).value    = 0;
		     _id("chkBox"+i+""+index).value = i+""+index; 
  			 _id("strNetAmt"+i+""+index).value = 0;   			
   			_id("netAmountDivId"+i+""+index).innerHTML = "0.00";	  	
   			
   			 _id("strSeviceTaxAmt"+i+""+index).disabled = true;	
  			  _id("strSeviceTaxAmt"+i+""+index).value = 0;	
   			
   		}
  	 	
  	 	
  	 	
  	  _id("detailsdivid0"+index).style.display = "none";
 	  _id("detailsdivid1"+index).style.display = "none";
    
 
  	  _id("minus10"+index).style.display="none";
      _id("plus10"+index).style.display="block";
      document.forms[0].button10.value = 0;
      _id("minus30"+index).style.display="none";
      _id("plus30"+index).style.display="block";
      document.forms[0].button30.value = 0;
  	 	
  	 	
  	 	
  	 	 calcBillApprovalDetails(index);
  	 	
  	 }
  
  
  
  /**
   * calcBillApprovalDetails
   * @param {String} index
   */
   function calcBillApprovalDetails(index) 
   {
   	   	
   		// Calculation of Unprocessed Net Amount's
   		var totalUnProcessAmt = 0;   	
   		var checkBoxLength = document.getElementsByName("chkBox"+index);
   		for(var i= 1; i<= checkBoxLength.length; i++) 
   		{   			
   			var netAmountVal = parseFloat(_id("strNetAmt"+i+""+index).value);   				
   			totalUnProcessAmt = manipulateValue(totalUnProcessAmt,netAmountVal,0);   			   			
   		}   
   	
   		// Adding the setting final Amount.		
   		var calculatedAmt = parseFloat(_id("strTotalFinalCalAmtHid"+index).value);
   		var finalAmtHidObj = _id("strTotalFinalAmtHid"+index);
   		var finalAmtObj = _id("strTotalFinalAmt"+index);
   		
   		var currentCalculatedFinalAmount = manipulateValue(totalUnProcessAmt,calculatedAmt,0);
   		
   		finalAmtHidObj.value = currentCalculatedFinalAmount;
   		finalAmtObj.value = currentCalculatedFinalAmount;
   		_id("strTotalFinalAmtDivId"+index).innerHTML = roundValue(currentCalculatedFinalAmount,2);

   		calcGrantTotalExpenseAmount();   	
   }
  
  
  
  function calcGrantTotalExpenseAmount()
  { 	
  	   	// Calc Final Amount Grand Total	
  		var finalAmountGrandTotal = calAllTariffNetCost("strTotalFinalAmtHid");  	
  		document.forms[0].strFinalAmt.value = roundValue(finalAmountGrandTotal , 2);
   		_id("strFinalAmtDivId").innerHTML = roundValue(finalAmountGrandTotal , 2);
   		
   		// calculate net tariff Amount    		
   		var netTariffCost = calAllTariffNetCost("strOfflineTariffNetAmount");
   		  		
   		// Re-Calculate Grand Total Final Amount;   		
   		finalAmountGrandTotal = manipulateValue(finalAmountGrandTotal,netTariffCost,0);	
   		finalAmountGrandTotal = roundValue(finalAmountGrandTotal , 0);
   			  	
   		// set Grand Final Amount Grand Total to Net Final Amount Text
   	
		document.forms[0].totalRecAmtDivId1.value = finalAmountGrandTotal;
		//_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+finalAmountGrandTotal+"</u></font></a>";    		
		_id("totalRecAmtDivId").innerHTML = "<font color='red'>"+finalAmountGrandTotal+"</font>";
   		
   		calcNetAmountForBillApprovalTwo() ; 	
  }
  
  
  
   function onRefundQtyAndUnitChange(k,i)
 {
    var findex= k+""+i;
    var allTrfNetCost = 0.0;       
    var unProcessQty  = _id("strUnProcessQty"+findex).value.split(' ')[0];
    var rate = _id("strTariffRate"+findex).value.split(' ')[0];
    
    var FinalAmtCol  = _id("strTotalFinalAmt"+i).value;
    var tmpFinalAmt  = _id("strTotalFinalAmtHid"+i).value;
    var refQty        = _id("strRefundQty"+findex).value;
	var unitBaseVal  = _id("strUnitCombo"+findex)[_id("strUnitCombo"+findex).selectedIndex].value.split('^')[1];
    
    if(refQty > unProcessQty) {
    	
    	alert("Refund Quantity Cannot be Greater than Un-Processed Quantity");
    	_id("strRefundQty"+findex).value = 0;
        	
    	onRefundQtyAndUnitChange(k,i);
    	
    	return false;
    }
    
    var finalAmt     = _id("strFinalCalAmt").value;
       
     
       var actRate    = _id("strActTariffRate"+findex).value;  
       
     var serviceTax =  _id("strServiceTax"+findex).value;
  	 var discUnit 	 = _id("strDiscUnit"+findex).value;
  	 var discType 	 = _id("strDiscType"+findex).value;
 	 var rateBaseVal = _id("strRateBaseVal"+findex).value;
  	
  	  		  		
  			  		
  	 var trfDtls = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseVal,discUnit,discType,refQty,unitBaseVal,serviceTax,0,1);
  	 
   	
  		var tariffAmt = trfDtls.oTrfAmt;
  		var disAmt    = trfDtls.oDisAmt;
  		var serAmt    = trfDtls.oSerAmt;
  		var netAmt    = trfDtls.oNetTrfAmt;
  		var actTariffAmt = trfDtls.oActTrfAmt
  		  		
  		
  		  		
  		if(_id("chkBox"+findex).checked){
  			
  			 _id("strRefundQty"+findex).disabled = false;
     		 _id("strUnitCombo"+findex).disabled = false;
     		 _id("strNetAmt"+findex).disabled    = false;
  			 _id("strUnProcNetAmt"+findex).value = netAmt;
  			 _id("strNetAmt"+findex).value = netAmt;  		
  			 _id("netAmountDivId"+findex).innerHTML = netAmt;	  	
  			 
  			  _id("strActualTariffNetAmt"+findex).disabled    = false;
  			 _id("strActualTariffNetAmt"+findex).value = actTariffAmt;
  			 
  			 _id("strServiceTax"+findex).disabled = false;	
  			 
  			  _id("strSeviceTaxAmt"+findex).disabled = false;	
  			  _id("strSeviceTaxAmt"+findex).value = serAmt;	
  			 
  			
  		}else{
  			
  			
  			 _id("strRefundQty"+findex).disabled = true;
  		     _id("strUnitCombo"+findex).disabled = true;
		     _id("strTotalFinalAmt"+i).value = tmpFinalAmt;
		     _id("strTotalFinalAmtDivId"+i).innerHTML = tmpFinalAmt;
		     _id("strNetAmt"+findex).disabled    = true;
		     _id("strRefundQty"+findex).value    = 0;
		     _id("chkBox"+findex).value = findex; 
  			 _id("strUnProcNetAmt"+findex).value = 0;
  			 _id("strNetAmt"+findex).value = 0;     
  	  		 _id("netAmountDivId"+findex).innerHTML = "0.00";	 		  			
  	
  	 		 _id("strActualTariffNetAmt"+findex).disabled    = true;
  			 _id("strActualTariffNetAmt"+findex).value = 0;
  	
  		 _id("strServiceTax"+findex).disabled = true;	
  	
  			 _id("strSeviceTaxAmt"+findex).disabled = true;	
  			 _id("strSeviceTaxAmt"+findex).value = 0;			  			
	  			  			
  		}
  		
  		
  }
  
  
  
////////////////////////////////////////////////////////////////////////

function  calcNetAmountForBillApprovalTwo() 
 {
 
		var thirdPartyBilling = document.forms[0].strIpdThirdPartyBilling.value;
       var disAmt = 0.0;
       var serAmt = 0.0;
       var netAmt = 0.0;
       var discountAmt        = 0.0;
       var qty                = 1;
       var qty_base_value     = 1;
       var dis_unit           = 0;
       var dis_type           = 0;
       var max_client_benefit1= 0;
       var trfNetAmt1          = document.forms[0].totalRecAmtDivId1.value;
       var ser_tax1            = document.forms[0].strTotalServiceTaxTemp.value;
       
      
      if(document.forms[0].strOffLineTariffDiscountUnit.value != ''){
      	
      	dis_unit = document.forms[0].strOffLineTariffDiscountUnit.value;
      	dis_type = document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value;
      }
      
      if(document.forms[0].strMaxBenifitAmt!=undefined)
      {
    	  if(document.forms[0].strMaxBenifitAmt != null && thirdPartyBilling != 1)
          {
             max_client_benefit1 = document.forms[0].strMaxBenifitAmt.value;
           }
           else
           {
             max_client_benefit1 = 0;
        	   //max_client_benefit1 = document.forms[0].strMaxBenifitAmt.value;
           } 
      }
      
       var recAmt1             = document.forms[0].strReceivedAmt.value; 
       //alert(max_client_benefit1);
     
       var trfNetAmt = parseFloat(trfNetAmt1);
       var ser_tax   = parseFloat(ser_tax1);
       var max_client_benefit = parseFloat(max_client_benefit1);
       var recAmt   = parseFloat(recAmt1);
                             
          /*
          alert("trfNetAmt : "+trfNetAmt);
          alert("dis_unit : "+dis_unit);
          alert("ser_tax : "+ser_tax);
          alert("max_client_benefit : "+max_client_benefit);
          alert("recAmt : "+recAmt);
          */            
                                 
       /*
        Return Value >> Discount Amount, Service Tax Amount and Net Amount
        Return Parameter >> oDisAmt for Discount Amount
			 >> oSerAmt for Service Tax Amount
			 >> oNetAmt for Net Amount
			 discountAmt =  calNetAmount(trfNetAmt,dis_unit,dis_type,ser_tax,max_client_benefit,recAmt)
       */  
           var funCalAmt = calNetAmount(trfNetAmt,dis_unit,dis_type,ser_tax,max_client_benefit,recAmt); 
                  disAmt = funCalAmt.oDisAmt;
                  serAmt = funCalAmt.oSerAmt;
                  netAmt = funCalAmt.oNetAmt;
          // alert("disAmt->"+disAmt+"<-SerTax->"+serAmt+"<-NetAmt->"+netAmt);
          
           serAmt = roundValue(serAmt,2);
          
           _id("strTotalServiceTax").value = serAmt;
           _id("strTotalServiceTaxDivId").innerHTML = serAmt;
           
           netAmt = roundValue(netAmt,2);
           //alert("netAmt"+netAmt);
           _id("strNetPaybleAmt").value    = netAmt;
           _id("strNetPaybleAmtDivId").innerHTML    = netAmt*-1;
           
             disAmt = roundValue(disAmt,2);
           
           _id("strTotalDisAmt").value     = disAmt;
           _id("strTotalDisAmtDivId").innerHTML     = disAmt;
               
}
             

function calTariffAmtForBillApproval()
{
   var rate = 0.0;
   var rate_base_value = 0.0;
   var dis_unit = 0.0;
   var dis_type = 0.0;
   var qty  = 0;
   var qty_unit = 0;
   var ser_tax = 0;
   var penelty = 0;
   var mode = 0;
   var funCalValue =  calTrfNetAmount(rate,rate_base_value,dis_unit,dis_type,qty,qty_unit,ser_tax,penelty,mode);

}


function finalAmtCalculation(i)
{  
    var allTrfNetCost = 0.0;
	var counter = 0;
	var amtValue = 0.0;
	var amtObj;
	var len ;
		
  //Business Logic
	// alert("1::");
	 amtObj = document.getElementsByName("strFinalCalAmt");
	 len = amtObj.length;
	 var  changeValue   = _id("strTotalFinalAmt"+i).value;
	 var  finalAmt      = _id("strFinalCalAmt").value;
     var  allTrfNetCost = manipulateValue(parseFloat(changeValue),parseFloat(finalAmt),0);		
  	 	
	 _id("strFinalCalAmt").value = allTrfNetCost; 
	 _id("strFinalCalAmtDivId").innerHTML = allTrfNetCost;
	 
     _id("strFinalAmt").value = allTrfNetCost;
     _id("strFinalAmtDivId").innerHTML = allTrfNetCost;
     
     //_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+allTrfNetCost+"</u></font></a>";
     _id("totalRecAmtDivId").innerHTML = "<font color='red'>"+roundValue(allTrfNetCost,2)+"</font>";
     _id("totalRecAmtDivId1").value    = roundValue(allTrfNetCost,2);
     
      _id("totalRecAmtTempDivId1").value    = allTrfNetCost;
     
     
     var tempVar = _id("strTotalDisAmt").value;
     calcNetAmountForBillApprovalTwo(tempVar);
  
}


function finalAmtCalculation2()
{ 
   //  alert("For Bill Approval ::1");
  /*   var depositAmt = _id("strExpDepositAmt").value;
	 var finalAmt   = _id("strNetAmt").value;
	
	
	
	 var amtDeposit = manipulateValue(parseFloat(finalAmt),parseFloat(depositAmt),1);
    
     _id("strFinalCalAmt").value = amtDeposit; 
     _id("strFinalAmt").value    = amtDeposit;
   
  // _id("strFinalAmt").value = totalFinalAmt;
   _id("totalRecAmtDivId").innerHTML = amtDeposit;
   _id("totalRecAmtDivId1").value    = amtDeposit;
   */
   var tempVar = _id("strTotalDisAmt").value;
   calcNetAmountForBillApprovalTwo(tempVar);
  
}


	
/**
 * getSumOfAmount 
 */
 function getSumOfAmount() 
 {	 
 	var clientAmt = parseFloat("0");
 	var strCltApprBalanceAmt = 0;
 	
 	var expAmt = parseFloat("0");
 	
 	if(document.getElementsByName("strFinalAmt"))
 	{ 			
 		expAmt = parseFloat(document.getElementsByName("strFinalAmt")[0].value); 		
 	}
 	
 	var recivedAmt = parseFloat(document.getElementsByName("strReceivedAmt")[0].value);
 	var disAmt =  parseFloat(document.getElementsByName("strTotalDisAmt")[0].value);
 	var serviceTaxAmt =  parseFloat(document.getElementsByName("strTotalServiceTax")[0].value);
 		
 	if(document.forms[0].strCltApprBalanceAmt!=undefined)
    {
 		var strCltApprBalanceAmtObj = document.getElementsByName("strCltApprBalanceAmt");
 		if(strCltApprBalanceAmtObj.length > 0)
 	 	{ 		
 	 		strCltApprBalanceAmt = parseFloat(strCltApprBalanceAmtObj[0].value);
 	 	}
    }
 	
 	if(document.forms[0].strMaxBenifitAmt!=undefined)
    {
 		var clientAmtObj = document.getElementsByName("strMaxBenifitAmt");
 		
 		if(expAmt >= strCltApprBalanceAmt)
 	 	{ 		
 	 		clientAmtObj[0].value = strCltApprBalanceAmt; 	
 	 		clientAmt = strCltApprBalanceAmt; 		
 	 	}
 	 	else
 	 	{ 		
 	 		clientAmtObj[0].value  = expAmt; 		
 	 		clientAmt = expAmt; 		
 	 	}
    }		
 	
 	
 	//	var cltAmt = calClientAmount(strCltApprBalanceAmt,0,expAmt);
 	
 	
 	//alert("expAmt"+expAmt);
 	//alert("clientAmt"+clientAmt);
 	//alert("strCltApprBalanceAmt"+strCltApprBalanceAmt);
 	expAmt = expAmt - clientAmt; 
 	var balanceAmt =  expAmt + recivedAmt + disAmt + serviceTaxAmt ; 	
 	serviceTaxAmt = roundValue(serviceTaxAmt,2); 	
 	_id("strTotalServiceTax").value = serviceTaxAmt;
 	_id("strTotalServiceTaxDivId").innerHTML = serviceTaxAmt; 	 
 	balanceAmt = roundValue(balanceAmt,0); 	
 	//alert("balanceAmt"+balanceAmt);
    /*_id("strNetPaybleAmt").value    = balanceAmt;
    _id("strNetPaybleAmtDivId").innerHTML    = balanceAmt;*/
    
     
    // _id("strTotalDisAmt").value     = disAmt;
 
 	
 }	
	
	

 /*
 
 function getSumOfAmount(){
  // alert("BillApproval->getSumOfAmount():: 2");
   var value = document.forms[0].strIpdThirdPartyBilling.value;
   var val = document.getElementsByName("strTotalFinalAmtHid");
   var length  = val.length;
   var deposit = val[length-1].value;
   var s=0;
	for(var j=0;j<val.length;j++)
	{
		if(val[j].value=="")
		  s=parseFloat(s);
		else
		   s=parseFloat(s)+parseFloat(val[j].value);
	          
	}
	var  AfterCal = manipulateValue(parseFloat(s),parseFloat(deposit),1);
			
   if(value == 1)
   {
     var depositAmt = _id("strExpDepositAmt").value;
	 var finalAmt   = _id("strNetAmt").value;
	 var amtDeposit1 = manipulateValue(parseFloat(finalAmt),parseFloat(depositAmt),1);
	 
	  //var amtDeposit = roundValue(amtDeposit1,0)  // Orignal Value Replace With New One
	 
	  var amtDeposit = roundValue(AfterCal,0);
	 
	 
     _id("strFinalCalAmt").value = amtDeposit; 
     _id("strFinalAmt").value = amtDeposit;
      
     _id("strReceivedAmt").value    = amtDeposit;
    // _id("strMaxBenifitAmt").value  = amtDeposit;
     _id("totalRecAmtDivId").innerHTML  = amtDeposit;
     _id("totalRecAmtDivId1").value     = amtDeposit;
     _id("strNetPaybleAmt").value     = amtDeposit;
   }
   else
   {
     var depositAmt = _id("strExpDepositAmt").value;
	 var finalAmt   = _id("strNetAmt").value;
	 	 
	 //var amtDeposit = manipulateValue(parseFloat(finalAmt),parseFloat(depositAmt),1);  //Orignal Value
	 
	  var amtDeposit = AfterCal;
	 
	 _id("strFinalCalAmt").value = amtDeposit; 
     _id("strFinalAmt").value = amtDeposit;
      
     _id("strReceivedAmt").value    = amtDeposit;
     _id("strMaxBenifitAmt").value  = amtDeposit;
     _id("totalRecAmtDivId").innerHTML  = amtDeposit;
     _id("totalRecAmtDivId1").value     = amtDeposit;
     _id("strNetPaybleAmt").value     = amtDeposit;
   }  
   
}*/

///////////////////////////////////////////////////////////////////


function unProcessDtl(parent,index,unProcessDtl) // Combine Value in unProcessDtl ->>> ServiceTax ^ TariffRate ^ DisUnit ^ DisType ^ Unit Name
{        
      
      var sTax = "  ----";
      var tRate = "----";
      var disUnit = "----";
      var disType =  "0";
      var unitName =  "=----";
      var DiscountType = "";
      
       var myArray=unProcessDtl.split("^");
               
        if(myArray[0].length > 0)
        {
          sTax = myArray[0];
        }
        
       
       
        if(myArray[1].length > 0)
        {
          tRate = myArray[1];
        }
       
       
       
        if(myArray[2].length > 0)
        {
           disUnit = myArray[2];
        }
       
       
       
        if(myArray[3].length > 0)
        {
          disType = myArray[3];
        }
        
       
       
        if(myArray[4].length > 0)
        {
          unitName = myArray[4];
        }
        
                  
        if(disType == 1)
        {
           DiscountType =  "Fixed"; 
        }else if(disType == 2){
        	
          DiscountType = "%";
          
        }
        
        var objVal1 = _id("UpDtl1");
        objVal1.innerHTML = tRate+"/"+unitName;
        var objVal2 = _id("UpDtl2");
        objVal2.innerHTML = disUnit+"  "+DiscountType;        
        var objVal3 = _id("UpDtl3");
        objVal3.innerHTML = sTax;        
                
		display_popup_menu(parent,'UnProcessServiceDtl','300','');
}

function ProServiceDtl(parent,index,ProcessDtl) // Combine Value of ProcessDtl ----->>>> sTax ^ tRate ^ tUnit ^ disUnit ^ disType ^ pEnlty ^ unitName;
{        
	
		var sTax = "  ----";
      var tRate = "----";
      var disUnit = "----";
       var pEnlty =  "=----";
      var disType =  "0";
      var unitName =  "=----";
      var DiscountType = "";
	
       var myArray = ProcessDtl.split("^");
    
       if(myArray[0].length > 0)
        {
          sTax = myArray[0];
        }
        
       
       
        if(myArray[1].length > 0)
        {
          tRate = myArray[1];
        }
        
       
       
        if(myArray[2].length > 0)
        {
          tUnit = myArray[2];
        }
        
       
        if(myArray[3].length > 0)
        {
           disUnit = myArray[3];
        }
       
       
       
        if(myArray[4].length > 0)
        {
           disType = myArray[4];
        }
        
               
        if(myArray[5].length > 0)
        {
         pEnlty = myArray[5];
        }
       
        
        if(myArray[6].length > 0)
        {
           unitName = myArray[6];
        }
              
       if(disType == 1)
        {
         DiscountType = "Fixed"; 
        }
        
        if(disType == 2)
        {
         DiscountType = "%";
        }
        
        
        var objVal1 = _id("PrDtl1");
        objVal1.innerHTML = tRate+"/"+unitName;
        var objVal2 = _id("PrDtl2");
        objVal2.innerHTML = disUnit+"  "+DiscountType;        
        var objVal3 = _id("PrDtl3");
        objVal3.innerHTML = sTax;  
        var objVal4 = _id("PrDtl4");
        objVal4.innerHTML = pEnlty;      
                
		display_popup_menu(parent,'ProcessServiceDtl','','');
}




function hidePayDetails(divId)
{
      hide_popup_menu(divId);
}
/*            Validate Method For Discount Pop-Up of Bill Approval Window           */

    /*
     *
	 * validateTariffDiscountDetails - This Method For Discount Detail PopUP in Add Service 
	 */
	 function validateTariffDiscountDtlForBillApproval()
	  {
	  	var hisValidator = new HISValidator("ipdBillManagementTransBean");
	  	
	    hisValidator.addValidation("strOffLineTariffDiscountUnit", "req", "Tariff Discount Unit is a Mandatory Field" );
	    if(document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value == 2)
	    {
	      hisValidator.addValidation("strOffLineTariffDiscountUnit", "numltet=100", "Percentage Cannot be Greater than 100" );
	    }
	    else
	    {
		  hisValidator.addValidation("strOffLineTariffDiscountUnit", "amount=8,2", "Please Enter a Valid Amount" );
	    }
	    hisValidator.addValidation("strOffLineTariffDiscountBy","dontselect=0", "Please Select a Value from Tariff Discount By" );
		
          hisValidator.addValidation("strOffLineTariffDiscountReasonText","req", "Please Enter the Discount Reason" );
		
		hisValidator.addValidation("strOffLineTariffDiscountDate", "req", "Tariff Discount Date is a Mandatory Field" );
		hisValidator.addValidation("strOffLineTariffDiscountDate", "date", "Tariff Discount Date must be a Valid Date" );
		var retVal = hisValidator.validate(); 
	    hisValidator.clearAllValidations();
	    if(document.forms[0].strAvailedPackageId.value!="0")
	    {
	    	alert("Discount cannot be availed when Package is present");
	    	return false;
	    }
		if(retVal)
		{
				var discountUnit = document.forms[0].strOffLineTariffDiscountUnit.value;
				var discountType = document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value;
				var discountBy = document.forms[0].strOffLineTariffDiscountBy[document.forms[0].strOffLineTariffDiscountBy.selectedIndex].value;
				var discountReason = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
				var discountDate = document.forms[0].strOffLineTariffDiscountDate.value;				
				var discountText = document.forms[0].strOffLineTariffDiscountReasonText.value;
				
	document.forms[0].strTariffDiscountAmtConfgDtlBillApproval.value = discountUnit+","+discountType+","+discountBy+","+discountReason+","+discountText+","+discountDate;
				
				hide_popup('tariffDiscountDtlsForBillApproval');
				calcNetAmountForBillApproval(discountUnit,discountType); 
				
		}
		else
		{
			return false;
		}
	 		
  } 
             
      
  function  calcNetAmountForBillApproval(discountUnit,discountType) 
  {	                 
			 var max_client_benefit = "0";                           
                             
               var disAmt = 0.0;
	           var serAmt = 0.0;
	           var netAmt = 0.0;
               var discountAmt        = 0.0;
               var qty                = 1;
               var qty_base_value     = 1;
               var dis_unit           = discountUnit;
               var dis_type           = discountType;
               var trfNetAmt          = document.forms[0].totalRecAmtDivId1.value;
               var ser_tax            = document.forms[0].strTotalServiceTaxTemp.value;        
               var recAmt             = document.forms[0].strReceivedAmt.value; 
               
               
               var cltObj = document.getElementsByName("strMaxBenifitAmt");
               
               if(cltObj.length > 0)
               {               	
               		max_client_benefit = document.forms[0].strMaxBenifitAmt.value ;               	
               }
                          
                                            
               /*
                Return Value >> Discount Amount, Service Tax Amount and Net Amount
	            Return Parameter >> oDisAmt for Discount Amount
					 >> oSerAmt for Service Tax Amount
					 >> oNetAmt for Net Amount
					 discountAmt =  calNetAmount(trfNetAmt,dis_unit,dis_type,ser_tax,max_client_benefit,recAmt)
               */  
                   var funCalAmt = calNetAmount(trfNetAmt,dis_unit,dis_type,ser_tax,max_client_benefit,recAmt); 
                          disAmt = funCalAmt.oDisAmt;
                          serAmt = funCalAmt.oSerAmt;
                          netAmt = funCalAmt.oNetAmt;
                
                //          alert("disAmt->"+disAmt+"<-SerTax->"+serAmt+"<-NetAmt->"+netAmt);
                
                  serAmt = roundValue(serAmt,2);
                
                   _id("strTotalServiceTax").value = serAmt;
                   _id("strTotalServiceTaxDivId").innerHTML = serAmt;
                   
                    netAmt = roundValue(netAmt,2);
                   
                    //alert("netAmtX"+netAmt);
                   _id("strNetPaybleAmt").value    = roundValue(netAmt,2);
                    _id("strNetPaybleAmtDivId").innerHTML    = roundValue(netAmt,2);
                   
                    disAmt = roundValue(disAmt,2);
                   
                   _id("strTotalDisAmt").value = disAmt;
                   _id("strTotalDisAmtDivId").innerHTML = disAmt;
               
             }
 
             
          /*               End Discount Calculation of For Bill Approval Pop-Up            */
  /**
	 * calcTotalRecAmount 
	 */
	 function calcTotalRecAmount()
	 {	
	 	var total = 0;
	 	var roundTotal = 0;
	 	 total = calAllTariffNetCost("strOfflineTariffNetAmount"); 	

	    /*----------------Final For Bill Approval-----------------*/
	    if(document.getElementsByName("strFinalAmt").length > 0)
	    {
	        var expAmt  = document.forms[0].strFinalAmt.value;
	     //   var tempVar = _id("strTotalDisAmt").value;
	 	    finalExpAmt = parseInt(expAmt);
	 	  	 	    
	 	    total1 =  manipulateValue(total,finalExpAmt,0);
	 	    
	   	   // total1 = total + finalExpAmt;
	   	    
	  	    roundTotal = roundValue(total1,2);
	  	    
	  	    //adding consumable to all cahrges
		 
		 	//var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value;
		 	document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=roundTotal;
		 	//var totalCharge=roundValue(parseFloat(roundTotal)+parseFloat(consumableCharge),2);
		 	var totalCharge=roundValue(parseFloat(roundTotal),0);
	  	    
	  	    /*_id("totalRecAmtDivId").innerHTML = roundTotal;
	  	    _id("totalRecAmtDivId1").value    = roundTotal;*/
	  	    	  	    
	  	    //_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+totalCharge+"</u></font></a>";
	  	    _id("totalRecAmtDivId1").value    = totalCharge;
	  	    
	  	    calcNetAmountForBillApprovalTwo();
	  	}
	  	else
	  	{	
	  	    var roundTotal = roundValue(total,2);
	  	   //adding consumable to all cahrges
		 
		 	//var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value;
		 	document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=roundTotal;
		 	//var totalCharge=roundValue(parseFloat(roundTotal)+parseFloat(consumableCharge),2);
		 	var totalCharge=roundValue(parseFloat(roundTotal),0);
		 	
            /*_id("totalRecAmtDivId").innerHTML = roundTotal;
	    	_id("totalRecAmtDivId1").value    = roundTotal;*/
	    	
	    	//_id("totalRecAmtDivId").innerHTML = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+totalCharge+"</u></font></a>";
	    	_id("totalRecAmtDivId").innerHTML = "<font color='red'>"+totalCharge+"</font>";
	  	    _id("totalRecAmtDivId1").value    = totalCharge;
	    
	    	
	    }
	    
	    
	 	
	 }
function getDiscountDtlForBillApproval(parent){
	
	
				var ipdDisc = 0;
				
				if(document.getElementsByName("strIsIpdDiscount").length > 0){
					
					ipdDisc = document.getElementsByName("strIsIpdDiscount")[0].value;
					
				}
				
				
				if(ipdDisc == '0'){
					
					alert("Clerk Discount is not Applicable");
					return false;
					
				}
	
				var totExpAmt = parseFloat(document.forms[0].totalRecAmtDivId1.value);
				//var totRecAmt = parseFloat(document.forms[0].strReceivedAmt.value);
	
			//var balAmt = manipulateValue(totExpAmt,totRecAmt,0);
	
		if(totExpAmt <= 0){
			
			alert("Discount Not Applicable for the Amount (Rs.) "+totExpAmt);
			return false;	
		}
	

			if(_id("strTariffDiscountAmtConfgDtlBillApproval").value.length > 1){
				
				var temp =  _id("strTariffDiscountAmtConfgDtlBillApproval").value.split(",");
					
				document.forms[0].strOffLineTariffDiscountUnit.value = temp[0];
				document.forms[0].strOffLineTariffDiscountType.value = temp[1];
				document.forms[0].strOffLineTariffDiscountBy.value = temp[2];
				document.forms[0].strOffLineTariffDiscountReason.value = temp[3];
				document.forms[0].strOffLineTariffDiscountReasonText.value = temp[4];
				document.forms[0].strOffLineTariffDiscountDate.value = temp[5];	
							
			}else{
			
				setReasonText();
			}

			popup('tariffDiscountDtlsForBillApproval', '250','250');

			//display_popup_menu(parent,'tariffDiscountDtlsForBillApproval','400','');
					
			document.forms[0].strOffLineTariffDiscountUnit.focus();
		
}
function hideDiscountDtlForBillApproval(divId)
{
 /*
				document.forms[0].strOffLineTariffDiscountUnit.value = "";
				document.forms[0].strOffLineTariffDiscountType.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountBy.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReason.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReasonText.value = "";
 */												
						
		hide_popup(divId);
}
/*function validateTariffDiscountDtlForBillApproval() 
{
   	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
		if(retVal)
		{
				
				calcNetDiscAmount(gblTariffDiscountDtlsId);
				
		}
		else
		{
			return false;
		}
 }
	 function calcNetDiscAmount(index) 
	 {
	 		
	 		var check = discountVal.indexOf('%');
	 	
	 		if(check!= -1){
	 			discountVal = discountVal.substring(0,check);
	 			discountPercentageFlag = true;
	 		}
	 	
	 		if(!isNaN(discountVal)){
	 			discountUnit = parseInt(discountVal);
	 		} 
	 	 		
	 		if(!isNaN(serviceVal)){
	 			serviceTaxAmt = parseInt(serviceVal);
	 		}
	 		 	
	 		total = (rate / baseValue)*(qty * qtyBaseValue);
			 	
				 	
			if(discountUnit > 0){
			
				if(discountPercentageFlag){
					discountAmt = total * discountUnit / 100
				}else{
					discountAmt = qty * qtyBaseValue * discountUnit ;	
				}
				
			}	 	
			 			 	
			balanceAmt = manipulateValue(total,discountAmt,1);
		
			if(serviceTaxAmt > 0)
			{
				serviceTaxAmt = total * (serviceTaxAmt / 100);
			} 			 	
			 		 		 		 				 		 		 		 	
			    netAmt = manipulateValue(total , serviceTaxAmt , 0);	 		 		 		 	
	 		 		 		 			 		 		 	
	 	
	 		if(netAmt < 0)
	 		{
	 			alert("Net Amount Value in Negative, Plese Check the Discount Amount");
	 			return false;
	 		}	 		 		 	
			calcTotalNetAmount();	
			 	
	 }
	 function calcTotalNetAmount()
	  {
	 	var obj     = document.getElementsByName("strOfflineTariffNetAmount");
	 	var total   = 0;
	 	
	 	var amt = 0;
	 	if(obj.length > 1)
	 	{
	 			for(var index=0 , stop = obj.length -1 ; index<stop; index++) 
	 			{
	 			  amt = parseFloat(obj[index].value);
	 			  total = manipulateValue(total , amt , 0);
	 			  alert("calcTotalNetAmount..!!"+total);
	 			}
	 		
	 	}
	 	// Change To Get Net Amt for Bill Approval Page
	 	var expAmt  = _id("strTotalExpAmt1").value;
	 	alert("Expense Amt->"+expAmt);
	 	finalExpAmt = parseInt(expAmt);
	  	total1 = total + finalExpAmt;
	  	alert("Total Amt->"+total1);
	 	_id("totalRecAmtDivId").innerHTML = total1;
	 	
	 } */
	

///////////////////////////////END/////////////////////////////////


function goFuncBillApproval()
{	
	//alert("value="+document.forms[0].serviceFlag.value);
	if(document.forms[0].serviceFlag.value!=0)
	{
	//alert("value="+document.forms[0].serviceFlag.value);
		alert("Bill has already finalized!");
		return false;
	}
	if(document.forms[0].finalBillFlag.value=="90" || document.forms[0].finalBillFlag.value!="91")
	{
		alert("Bill cannot be Approved/Rejected since Bed Transfer Flag is still unchecked");
		return false;
	}
	if(document.forms[0].strNetAmt != null)
	{	
	    document.forms[0].strNetAmt.disabled = false;
	    document.forms[0].strTotalDisAmt.disabled = false;
	    document.forms[0].strNetPaybleAmt.disabled = false;
	}		 		
	
	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	hisValidator.clearAllValidations();	
	 	
	//hisValidator.addValidation("strApprovedByCombo",   "dontselect=0", "Approval By is a Mandatory Field" );
	
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	var strCatGrp=document.forms[0].strCatGrp.value;
	var strTariffNetAmt=document.forms[0].strTariffNetAmt.value;
	var strSancAmt=document.forms[0].strSancAmt.value;
    var strRecievedAmount= document.forms[0].strReceivedAmt.value;
	//alert(strCatGrp + '   ' +strTariffNetAmt +'  '+  strSancAmt +'  '+ (-parseInt(strRecievedAmount)));// CLIENT+CASH
	if((parseInt(strCatGrp)==4 || parseInt(strCatGrp)==3) && parseInt(strTariffNetAmt) > parseInt(strSancAmt)+(- parseInt(strRecievedAmount)) )
	{
		alert('Final Bill Amount is Greater than the Client Sanctioned Amount.Please Deposit Part Payment!');
		return false;
	}
	
		if(retVal)
		{
				if(document.getElementsByName('isApproved')[1].checked)
				{
					if(document.getElementsByName('strRemarks')[0].value==null || document.getElementsByName('strRemarks')[0].value=="")
					{
						alert("Please Enter Remarks")
						document.getElementsByName('strRemarks')[0].focus()
						return false;
					}
				}
				if(document.forms[0].dosssetstat.value>0)
				{
					alert("Bill can't be finalized since Dossier Settlement is pending");
					return false;
				}
			
				if(document.forms[0].strCatGrp.value==3 || document.forms[0].strCatGrp.value==4)
				{
					document.forms[0].strTotalDisAmt.disabled = false;
					document.forms[0].strOffLineTariffDiscountReasonText.disabled = false;
					document.forms[0].primaryCategoryCode.value=document.forms[0].strCatgoryCode.value;
					document.forms[0].hmode.value="BILLAPPROVALINSERT";
					document.forms[0].submit();	
				}
				else
				{	
					if(document.forms[0].totalRecAmtDivId1.value!=0)
					calcBillApprovalDetails(0);
					
					var gtExp=document.forms[0].totalRecAmtDivId1.value;
					var recAmt=document.forms[0].strReceivedAmt.value;
					var maxCleintBenefit=0;
					var disAmt=document.forms[0].strTotalDisAmt.value;
					
		            if(document.forms[0].strMaxBenifitAmt!=undefined)
		            {
		            	var maxCleintBenefit=document.forms[0].strMaxBenifitAmt.value;
		            }
					var totalDeposit=parseInt(recAmt*-1) + parseInt(maxCleintBenefit);
					var bal=totalDeposit-(parseInt(gtExp)+parseInt(disAmt));
					 
					
					if(confirm("Please Verify Amount As\nRecieved:="+totalDeposit+"\nExpenditure:="+gtExp+"\nDiscount:="+disAmt+"\nBalance:="+bal))
					{		 			
						document.forms[0].strTotalDisAmt.disabled = false;
						document.forms[0].strOffLineTariffDiscountReasonText.disabled = false;
						document.forms[0].primaryCategoryCode.value=document.forms[0].strCatgoryCode.value;
						document.forms[0].hmode.value="BILLAPPROVALINSERT";
						document.forms[0].submit();		 			
			 		}
					else
						return false;
				}
				
		}
		else
		{
			return false;
		}
		
}


/**
	 * deleteUnwantedRows
	 * @param {String} fieldName 
	 * @param {String} layerIndex
	 */
	 function deleteUnwantedRows(fieldName , layerIndex , minRows) {
	 	
	 		var fieldObjectVal = document.getElementsByName(fieldName);
	 	
	 		var len = fieldObjectVal.length - 1;
	 	
	 		for(var k=0; k< len ; k++) {
	 		
	 		  		 
	 			if(fieldObjectVal[k].value.length == 0  ){
	 				 		 	 			
	 				var indexVal = fieldObjectVal[k].id.split('-')[1];
	 		 	 
	 				 deleteRow("1-"+indexVal,layerIndex, minRows);
	 			  
	 					 len = len - 1;
	 					 k = k-1;
	 			 
	 			}
	 		
	 	}
	 }
	


/**
 * checkMandatory
 * @param {}  
 */
 function checkMandatory() 
 { 	
	 var flag = false;		
	 var compChk = document.getElementsByName("strCompChargeCheck");
	 for(var index=0; index<compChk.length; index++) 
	 {
		 if(compChk[index].checked)
		 {
			 flag = true;
			 return flag;
		 }
	 }
	 var splChk = document.getElementsByName("strSpecialChargeCheck");
	 for(var index=0; index<splChk.length; index++) 
	 {
		if(splChk[index].checked)
		{
			flag = true;						
			return flag;
		}
	 }			
	 	return flag; 	
 }


function goFuncAddService()
{	
	// COMMENTED AFTER SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE  22092017 BY AMIT ATERIA
		/*if(_id("offlineTariffDivId").style.display == 'none')
		{			
			alert("Please Click the Go button to get the Tariff Details");
			return false;			
		}*/
	   // alert("value="+document.forms[0].serviceFlag.value);
		if(document.forms[0].serviceFlag.value!=0){
			//alert("value="+document.forms[0].serviceFlag.value);
			alert("Bill has already finalized!");
			return false;
		}
		if(document.forms[0].strIpdBillManagementMode.value == 2)
		{			
			deleteUnwantedRows("strOfflineTariffName" , "1" , '0');
		}
		else
		{			
			deleteUnwantedRows("strOfflineTariffName" , "1" , '1');			
		}
	
		var hisValidator = new HISValidator("ipdBillManagementTransBean");  
		/*hisValidator.addValidation("strOfflineTariffName", "req", "Tariff Name is a Mandatory Field" );
		hisValidator.addValidation("strOfflineTariffQty",  "req", "Tariff Qty is a Mandatory Field" );
		hisValidator.addValidation("strOfflineTariffQty",  "maxlen=3", "Tariff Qty cannot be greater than 999" );*/
		
		//hisValidator.addValidation("strOfflineTariffUnit", "req", "Tariff Unit is a Mandatory Field" );	
		//hisValidator.addValidation("strOfflineTariffName", "maxlen=50", "Offline Tariff Name should have less than or equal to 50 Characters" );
	
	
		var retVal = hisValidator.validate(); 
	
		if(retVal)
		{						
					// COMMENTED AFTER SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE  22092017 BY AMIT ATERIA
					/*if(document.forms[0].strIpdBillManagementMode.value == 2)
					{
						var cm = checkMandatory();																
						/*if( cm == false && document.getElementsByName("strOfflineTariffUnit")[document.getElementsByName("strOfflineTariffUnit").length - 2].id.length < "strOfflineTariffUnit1-1".length )
						{														
							alert("Please Select or Add, At Least One Tariff");
							generateRows();
							return false;						
						}*/						
					/*}*/		
						
					var x=document.getElementsByName("strOfflineTariffName");
					var a=document.getElementsByName("strOfflineTariffDate");
					var DD=document.getElementsByName("trfDateDD");
					var MM=document.getElementsByName("trfDateMM");
					var YYYY=document.getElementsByName("trfDateYYYY");
					//var deleteFlag=document.getElementsByName("deleteFlag");
					
					if(x.length>1)
				    {
						for(var i=0;i<x.length-1;i++)
						{
							//alert(deleteFlag[i].value);
							if(x[i].type!="hidden")
							{
								// SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE 22092017 BY AMIT ATERIA
								a[i].value=DD[i].value+"-"+MM[i].value+"-"+YYYY[i].value;
								//alert(a[i].value);
								if(a[i].value == "" && !x[i].value == "")
								{
									alert("Please Enter Tariff Date");
									return false;
								}
								if(a[i].value != "" && !x[i].value == "")
								{
									var ret=compareDate(a[i].value,document.forms[0].strCtDate.value);
									if(ret.mode==2)
									{
										alert("Tariff Date Can't Be Greater Than Current Date");
										return false;
									}
									var ret=compareDate(a[i].value,document.forms[0].strAcctOpngDate.value);
									if(ret.mode==0)
									{
										alert("Tariff Date Can't Be Less Than Account Opening Date");
										return false;
									}
								}   
							}
			            }
				    }
					
				var creditLimit = parseFloat(document.forms[0].strExcessCreditLimit.value);				
				var amt = parseFloat(document.forms[0].strAccountBalanceAmt.value) + parseFloat(document.forms[0].totalRecAmtDivId1.value);
					
				/*if(creditLimit > 0 && amt > 0)
				{						
					if(Math.abs(amt) > creditLimit)
					{						
						alert("Credit Limit Exceeds !");
						generateRows();
						return false;						
					}											
				}*/
				/*alert("strSancAmt"+document.forms[0].strSancAmt.value);
				alert("strTariffNetAmt"+document.forms[0].strTariffNetAmt.value);
				alert("strCatGrp"+document.forms[0].strCatGrp.value);
				alert("document.getElementsByName"+document.getElementsByName("totalRecAmtDivId1")[0].value);*/
				//var tariffamt=parseFloat(document.forms[0].strTariffNetAmt.value) + parseFloat(document.getElementsByName("totalRecAmtDivId1")[0].value);
				var tariffamt=parseFloat(document.getElementsByName("totalRecAmtDivId1")[0].value);
				//alert(tariffamt);
				var strArogyaIpdCreditLimit=document.forms[0].strArogyaIpdCreditLimit.value;
				var strSancAmt=document.forms[0].strSancAmt.value;
			if(document.forms[0].strCatGrp.value==4 )
			   {
				if(strSancAmt < tariffamt)
				{
				 if(strArogyaIpdCreditLimit > tariffamt)
					 {
					  alert('Credit Limit Is Less Than Expense Amount Please Deposit Advance Or Part Payment');
					  return false;
					 }else
						 {
						 if(parseInt(strArogyaIpdCreditLimit) == parseInt(strSancAmt))
							 {
							 
							 }else
								 {
									 alert('Credit Limit Is Less Than Expense Amount Please Deposit Advance Or Bring Letter');
									  return false;
								 }
						  
						 }
				}
			}
				
				/*if(document.forms[0].strCatGrp.value==4 && tariffamt > document.forms[0].strSancAmt.value )
				{
					alert('Tariff Amount Must Be Less Than Sanction Amount');
					return false;
				}else
					{*/
				
				if(document.forms[0].strCatGrp.value==3 || document.forms[0].strCatGrp.value==4)
				{
					if(document.forms[0].strSancAmt.value==0.00)
					{
						if(!confirm("No Amount Sanctioned By Client. Do you want to proceed ??"))
							return false;
					}
					if(tariffamt>document.forms[0].strSancAmt.value)
					{
						if(!confirm("Final Bill Amount is Greater than the client Sanctioned Amount. Do you want to continue or cancel for part payment of Remaining Amount ??"))
							return false;
					}
					if(document.forms[0].strCatGrp.value==4 && tariffamt<document.forms[0].strSancAmt.value)
					{
						if(!confirm("Final Bill Amount is Less than the client Sanctioned Amount. Do you want to continue or cancel for adding additional tariffs of Remaining Amount ??"))
							return false;
					}
				}
				
			//}
				
				if(confirm("Are You Sure to Save it?"))
				{	
					
					 document.forms[0].strNewTreatmentCategory.disabled=false;
					 document.forms[0].strWardType.disabled=false;
					 
					
					////code for adding hidden tariff of consumable charges		
	 		 		
	 	 			/*var consumableCharges=document.getElementsByName("strConsumableCharge")[0].value
	 				var consumablechargeGroupId=document.forms[0].strConsumableChargesGroupId.value;
	 				var consumablechargeTariffId=document.forms[0].strConsumableChargesTariffCode.value;
				 	var hiddenfieldvalue=consumablechargeTariffId+"^"+consumablechargeGroupId+"^"+"0"+"^"+consumableCharges+"^"+consumableCharges+"^"+"1701"+"^"+"1"+"^"+"0"+"^"+"0"+"^"+"0"+"^"+consumableCharges+"^"+consumableCharges+"^"+"0"+"^"+"0"+"^"+"Each"+"^"+"0"+"^"+"0"+"^"+"0"+"^"+consumableCharges;
				 	if(parseInt(consumableCharges)>0)
				 	{
					 	var divInnerHtml="<input type='hidden' name='strOfflineTariffDetailsHidden' value='"+hiddenfieldvalue+"'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffName' value='Consumable Charges - (Con)'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffQty' value='1'/>";
					 	//divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffDiscount' value='0'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffServiceTax' value='0'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffNetAmount' value='"+consumableCharges+"'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffUnit' value='1701^1'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffServiceTaxAmtVal' value='0'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffDiscountAmtVal' value='0'/>";
					 	divInnerHtml=divInnerHtml+"<input type='hidden' name='strOfflineTariffRateUnit' value='"+consumableCharges+"/Each'/>";
				 	
				 		_id("consumableChargeDiv").innerHTML=divInnerHtml;
					}*/
					document.forms[0].hmode.value="ADDSERVICEINSERT";
					document.forms[0].submit();					
				}
				else
				{					
					// COMMENTED AFTER SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE  22092017 BY AMIT ATERIA
					//generateRows();
					document.forms[0].strTariffCode.focus();
					return false;					
				}
			
			
		}
		else
		{		
			// COMMENTED AFTER SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE  22092017 BY AMIT ATERIA
			//generateRows();
			document.forms[0].strTariffCode.focus();
			return false;
		}		
}


var gblTempQtyMainVal = "0";

/**
 * checkAllCompCharges
 * @param {Object} chkObj
 * @param {String} checkName  
 * @param {String} stIndex
 */
 function checkAllCompCharges(chkObj , checkName , stIndex) {
 	
 	 	var chkObjValues = document.getElementsByName(checkName);
 	 	
 	 	var endIndex = chkObjValues.length;
 	 	
 	 	if(checkName == 'strSpecialChargeCheck'){
 	 		
 	 		endIndex = endIndex + parseInt(stIndex) - 1;
 	 	}
 	 	
 	if(chkObj.checked){
 				
 		for(var index=stIndex; index <= endIndex; index++) {
 			
 				_id(checkName+""+index).checked = true;
 				_id("strOfflineTariffName"+index).disabled = false;
	 			_id("strOfflineTariffDetailsHidden"+index).disabled = false;
	 			_id("strOfflineTariffRateUnit"+index).disabled = false;
	 			
	 			if(_id("strOfflineTariffName"+index).value.toUpperCase().indexOf("ADMISSION CHARGE") != -1){
	 				_id("strOfflineTariffQty"+index).value = "1";
	 			}else{
	 				_id("strOfflineTariffQty"+index).value = gblTempQtyMainVal;
	 			}
	 				 			
	 			_id("strOfflineTariffQty"+index).disabled = false;
	 			_id("strOfflineTariffUnit"+index).disabled = false;
	 			_id("strOfflineTariffServiceTax"+index).disabled = false;
	 			_id("strOfflineTariffServiceTaxAmtVal"+index).disabled = false;
	 			_id("strOfflineActualTariffAmtVal"+index).disabled = false;
	 			_id("strOfflineTariffNetAmount"+index).disabled = false;
 			
 			calcOffLineTariffNetAmount(index);
 		}
 		
 		
 			
 		
 	}else{
 		
  		
 		for(var index=stIndex; index <= endIndex; index++) {
 		
 	 		
 				_id(checkName+""+index).checked = false;
 				_id("strOfflineTariffName"+index).disabled = true;
	 			_id("strOfflineTariffDetailsHidden"+index).disabled = true;
	 			_id("strOfflineTariffRateUnit"+index).disabled = true;
	 			_id("strOfflineTariffQty"+index).value = 0;
	 			_id("strOfflineTariffQty"+index).disabled = true;
	 			_id("strOfflineTariffUnit"+index).disabled = true;
	 			_id("strOfflineTariffServiceTax"+index).disabled = true;
	 			_id("strOfflineTariffServiceTaxAmtVal"+index).disabled = true;
	 			_id("strOfflineActualTariffAmtVal"+index).disabled = true;
	 			_id("strOfflineTariffNetAmount"+index).value = 0;
	 			_id("strOfflineTariffNetAmount"+index).disabled = true;
	 			_id("strOfflineTariffNetAmountDivId"+index).innerHTML = "0.00";
 			
 			calcOffLineTariffNetAmount(index);
 		}
 		
 		
 		
 	}
 	
 }



	/**
	 * manageCompalsaryChargesTariff
	 * @param {Object} chkObj 
	 * @param {String} index	
	 */
	 function manageCompalsaryChargesTariff(chkObj , index) {
	 	
	 	if(chkObj.checked){
	 		
	 			
	 	//	var qty = getDateDifference(document.forms[0].strStartDate.value , document.forms[0].strEndDate.value);
	 					
	 	 var qty = gblTempQtyMainVal;
	 					
	 			_id("strOfflineTariffName"+index).disabled = false;
	 			_id("strOfflineTariffDetailsHidden"+index).disabled = false;
	 			_id("strOfflineTariffRateUnit"+index).disabled = false;
	 			
	 			if(_id("strOfflineTariffName"+index).value.toUpperCase().indexOf("ADMISSION CHARGE") != -1){
	 				_id("strOfflineTariffQty"+index).value = "1";
	 			}else{
	 				_id("strOfflineTariffQty"+index).value = qty;
	 			}
	 			
	 			_id("strOfflineTariffQty"+index).disabled = false;
	 			_id("strOfflineTariffUnit"+index).disabled = false;
	 			_id("strOfflineTariffServiceTax"+index).disabled = false;
	 			_id("strOfflineTariffServiceTaxAmtVal"+index).disabled = false;
	 			_id("strOfflineActualTariffAmtVal"+index).disabled = false;
	 			_id("strOfflineTariffNetAmount"+index).disabled = false;
	 				 					
	 		
	 	}else{
	 		
	 		
	 			_id("strOfflineTariffName"+index).disabled = true;
	 			_id("strOfflineTariffDetailsHidden"+index).disabled = true;
	 			_id("strOfflineTariffRateUnit"+index).disabled = true;
	 			_id("strOfflineTariffQty"+index).value = 0;
	 			_id("strOfflineTariffQty"+index).disabled = true;
	 			_id("strOfflineTariffUnit"+index).disabled = true;
	 			_id("strOfflineTariffServiceTax"+index).disabled = true;
	 			_id("strOfflineTariffServiceTaxAmtVal"+index).disabled = true;
	 			_id("strOfflineActualTariffAmtVal"+index).disabled = true;
	 			_id("strOfflineTariffNetAmount"+index).value = 0;
	 			_id("strOfflineTariffNetAmount"+index).disabled = true;
	 			_id("strOfflineTariffNetAmountDivId"+index).innerHTML = "0.00";
	 				 		
	 	}
	 	
	 	calcOffLineTariffNetAmount(index);
	 	
	 }


	/**
	 * checkQtyValue
	 * @param {String} index 
	 */
	 function checkQtyValue(index) {
	 	
	 	var qtyVal = parseInt(_id("strOfflineTariffQty"+index).value);
	 	
	 		if(_id("strOfflineTariffName"+index).value.toUpperCase().indexOf("ADMISSION CHARGE") != -1){
	 			
	 			
	 			if(qtyVal > 1){
	 				
	 				alert("Admission Charge Quatity cannot be greater than 1");
	 				_id("strOfflineTariffQty"+index).value = 1;
	 			}
	 				 			
	 		}else{
	 			
	 			
	 			if(qtyVal > parseInt(gblTempQtyMainVal)){
	 				
	 				alert("Compulsary Charges Quatity cannot be greater than Difference of Date Range from "+document.forms[0].strStartDate.value+" to "+document.forms[0].strEndDate.value +" = "+gblTempQtyMainVal);
	 				_id("strOfflineTariffQty"+index).value = gblTempQtyMainVal;
	 			}
	 			
	 		}
	 		
	 		
	 		calcOffLineTariffNetAmount(index);
	 	
	 }


	/**
	 * getDateDifference(date1 , dte2);
	 * @param {String} date1
	 * @param {String} date2
	 */
	 function getDateDifference(date1 , date2){
	 	
	 		var temp_stDate = parseDate(date1 , "-");
	 		var temp_endDate = parseDate(date2 , "-");
	 		
	 		var startDate = new Date(temp_stDate.year , temp_stDate.date , temp_stDate.month);
	 		var endDate = new Date(temp_endDate.year , temp_endDate.date , temp_endDate.month);
	 			
	 		//alert("st Date : "+startDate );
	 		//alert("end Date : "+endDate);
	 		
	 		var ONE_DAY = 1000 * 60 * 60 * 24 ;
	 	
	 		var startDate_Ms = startDate.getTime();
	 		
	 		//alert("st Date Ms : "+startDate_Ms);
	 		
	 		var endDate_Ms = endDate.getTime();
	 		
	 		//alert("end Date Ms : "+endDate_Ms);
	 		
	 		var diff_Date = Math.abs(startDate_Ms - endDate_Ms);
	 	
	 		//alert("diff_Date : "+diff_Date);
	 		
	 		//alert("return Date : "+Math.round(diff_Date / ONE_DAY));
	 	
	 		return Math.round(diff_Date / ONE_DAY);
	 			 	
	 }
	
	function dateDiff(date_1,date_2) 
	{
		var retVal=compareDate(date_1,date_2);
		if(retVal.mode==0 || retVal.mode==1)
		{
			var ret=parseDate(date_1,"-");
			var ret1=parseDate(date_2,"-");
			var dt1=ret.month+"-"+ret.day+"-"+ret.year;
			var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
			date1 = new Date();
			date2 = new Date();
			diff = new Date();
			// Validates first date 
			var myDate1=new Array();
			myDate1=dt1.split("-");
			date1temp = new Date(myDate1[2],myDate1[0],myDate1[1]);
			date1.setTime(date1temp.getTime());
			
			// Validates second date 
			var myDate2=new Array();
			myDate2=dt2.split("-");
			date2temp = new Date(myDate2[2],myDate2[0],myDate2[1]);
			date2.setTime(date2temp.getTime());
		
			// sets difference date to difference of first date and second date
			diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
			timediff = diff.getTime();
			weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
			timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
			days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
			timediff -= days * (1000 * 60 * 60 * 24);
			days=parseInt(weeks)*7+days;
			//var diff = /*weeks + " weeks, " +*/ (days+1) + " days " ;
			var diff =(days+1);
			//alert("date diff->"+diff);
			return diff;
		
		}
	}

///////////////////////////UTILITY ///////////////////////////////////////////////

var gblTariffDiscountDtlsId = "";


// to add multiple row  in one click
 
	  /**
	   * addTariffRows
	   * @param {Object} obj - Text Box Object
	   * @param {Event} e - 
	   */
	   function addTariffRows(obj,e,divId) {
	
			if(e.keyCode == 13){
								
				if(obj.value > 20){
					
					alert("Please Enter No. of Rows Less than 20");
					return false;
				}
				
				addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffDate','strPriority','strDiscount','strDiscountType','strDiscountAmt','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','s','d','s','t','s','t','t','t','t','t'),'1',obj.value,'R');
				//addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTempTariffRateUnit','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffDate','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineActualTariffAmtVal','strOfflineTariffNetAmount'),new Array('t','t','t','t','t','s','d','t','t','t','t'),'1',obj.value,'R');
				 hideMultiRowAdder(divId);
				return false;
			}	   	
			return true;
	   }
	  
	  /**
	   * showMultiRowAdder 
	   */
	   function showMultiRowAdder(evt) {
	   	
	   	 evt = (evt) ? evt : ((window.event) ? event : null);
		
		 if (evt) {
		 	
        	if (evt.keyCode == 82 && evt.ctrlKey && evt.altKey) {
	   	
	   		popup('multiRowAdderDivId','250','250');
	   		document.forms[0].strOffLineTariffNoOfRows.focus();
        	}
		 }
		
	   }
	
		/**
		 * hideMultiRowAdder
		 * @param {String} divid 
		 */
		 function hideMultiRowAdder(divid) {
		 	 	document.forms[0].strOffLineTariffNoOfRows.value = "";
		 	 hide_popup(divid);
		 }
	

// tariff total calculation
          /** calcOffLineTariffNetAmount

             * @param {Object} qtyObj - Quatity Text Box object 

             * @param {String} index 

             */

function calcOffLineTariffNetAmount(index) 
{
             	   	
            var tariff =  _id("strOfflineTariffName"+index).value;	 		 	
	 		var a = _id("strOfflineTariffDetailsHidden"+index).value.split('^');
			var b=true;
			var urg=document.forms[0].strUrgSur.value;
			var rate;
			var actRate;
			for(var i = 0 ; i<a.length; i++)
			if(a[i]!="0")
			{
				b=false;
				break;
			}
			
	
			if(b ||tariff != '')
			{	 		
			 	
	 		var temp = _id("strOfflineTariffDetailsHidden"+index).value.split('^');
	 		if(_id("strPriority"+index).value=="1")
	 		{
	 			rate = temp[4];	 
		 		actRate = temp[11];
	 		}
	 		else
	 		{
	 			rate = temp[4]*(100+parseFloat(urg))/100;	 
		 		actRate = temp[11]*(100+parseFloat(urg))/100;
	 		}
	 		
	 		if(temp[17] == '1')
	 		{
	 			if(_id("strPriority"+index).value=="1")
		 		{
		 			rate = _id("strOfflineTempTariffRateUnit"+index).value;
		 			actRate = rate;//ACTURAL RATE AND USER ENTRY RATE ALL SHOULD BE SAME.CHANGED ON 29.05.17 BY AJAY DESHWAL
		 		}
	 			else
	 			{
	 				rate = _id("strOfflineTempTariffRateUnit"+index).value*(100+parseFloat(urg))/100;
		 			actRate = rate*(100+parseFloat(urg))/100;
	 			}
	 		}
	 		_id("strOfflineTariffRateUnit"+index).value=Math.round(rate);
	 		var rateBaseValue = temp[6];
	 		var qtyBaseValue = "0";
	 		var qtyVal =  _id("strOfflineTariffQty"+index).value;
	 		var discountVal = 0;
	 		var serviceVal = _id("strOfflineTariffServiceTax"+index).value;
	 		var discountType = 0;
	 		
	 		var netAmt = 0;
	 		
	 		
	 		
	 		if(_id("strOfflineTariffUnit"+index).selectedIndex != null){
	 			
	 			qtyBaseValue = _id("strOfflineTariffUnit"+index).options[_id("strOfflineTariffUnit"+index).selectedIndex].value;
	 			
	 		}else{
	 			
	 			qtyBaseValue = _id("strOfflineTariffUnit"+index).value+"^1";
	 			
	 		}
	 		
	 		 		
	 		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0); 
	 		
	 		
	  		 			 		 		 	
	 	
	 	netAmt = calAmt.oNetTrfAmt;
	 	
	 	
	 	var groupid =temp[1];
	 	//var groupConsumableCharge =temp[18];
	 	//addConsumableCharge(groupid,groupConsumableCharge);
	 	
	 			_id("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
	 			_id("strOfflineActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
	 			_id("strOfflineTariffNetAmount"+index).value = Math.round(netAmt);
	 			_id("strOfflineTariffNetAmountDivId"+index).innerHTML = Math.round(netAmt);
	 			
	 			
                            
                                             
                            var recObj = document.getElementsByName("strReceivedAmt");
                            
                            if(recObj.length > 0){
                            	
                            	calcGrantTotalExpenseAmount(); 
                            	
                            }else{
                            	
                            	calcTotalRecAmount();
                            	
                            }
                            
                        }                     
             }
 
	
	/**
	 * removeTariffRow
	 * @param {Object} index 
	 */
	 function removeTariffRow(index) {
		if(!showAlert())
			return false;
	 	
	 	
	 	var temp = _id("strOfflineTariffDetailsHidden"+index).value.split('^');
	 	var groupid =temp[1];
	 	var groupConsumableCharge =temp[18];
	 	
	 	removeConsumableAmount(groupid,groupConsumableCharge);
	 	deleteRow(index,'1','0');
	 	
	 	  var recObj = document.getElementsByName("strReceivedAmt");
                            
            if(recObj.length > 0){
            	
            	calcGrantTotalExpenseAmount(); 
            	
            }else{
            	
            	calcTotalRecAmount();
            	
            }
	 	
	 }
	 
	 /**
	  * setPartPaymentReasonText
	  * @param {}  
	  */
	  function setPartPaymentReasonText() {
	  	
	  	  	
	  	var val = document.forms[0].dr[document.forms[0].dr.selectedIndex].value;
	  	var content = document.forms[0].dr[document.forms[0].dr.selectedIndex].text;
	  	
	  	if(val != 0){
	  	document.forms[0].strRemarksCombo2.disabled = true;
	  	document.forms[0].strRemarksCombo2.value = content;
	  	
	  	}else{
	  		document.forms[0].strRemarksCombo2.value = "";
	  		document.forms[0].strRemarksCombo2.disabled = false;
	  	}
	  	
	  }
	 

 /**
	  * setReasonText
	  */
	  function setReasonText() {
	  	
	 	  	
	  	var val = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
	  	var content = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].text;
	  	
	  	if(val != 0){
	  	document.forms[0].strOffLineTariffDiscountReasonText.disabled = true;
	  	document.forms[0].strOffLineTariffDiscountReasonText.value = content;
	  	
	  	}else{
	  		document.forms[0].strOffLineTariffDiscountReasonText.value = "";
	  		document.forms[0].strOffLineTariffDiscountReasonText.disabled = false;
	  	}
	  }
	  


var trfdelIndex ;
	var trfObj;
	var trfPreviousData = "";
	var tempCode = "";

function getTariffDtls(obj , eve , delIndex)
{  
		trfdelIndex = delIndex;
		trfObj = obj;
		filterGroup();
	 
		var key;
		var previousDate = obj.value;
		
		if(window.event)
		key = window.event.keyCode;
		else
		{
			if (eve)
			key = eve.which;
		 
		}		
	
	tempCode = key;
	
	var temp = _id("strOfflineTariffDetailsHidden"+delIndex).value.split('^');
 
	if(temp[17] !='1')
	{
 
        if(tempCode == 222){
 			
 			var input = _id('strOfflineTariffName'+delIndex).value;
 			
 			_id('strOfflineTariffName'+delIndex).value = input.substring(0,input.length-1);
 			return false;
 			
 		}
 
	 

			if(obj.value.length >= 1 && obj.value.length <= 3 && trfPreviousData != previousDate){
				
							
				var hmode = "TARIFFDTLS"; 
				
				
				 
				 	var treatCat = "0";
	  		  	
	  		  	  	if(document.getElementsByName("strTreatmentCategory").length > 0){
	 	
	 			treatCat = document.forms[0].strTreatmentCategory.value;	
	 	}
	  		  	
	  		  	
	  		  	if(document.getElementsByName("strNewTreatmentCategory").length > 0){
	 	
	 			treatCat =  document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value ;	
	 	}
	  		  	
	  		 var wardType = "0";
	  		 
	  		 if(document.getElementsByName("strWardType").length > 0 ){
	  		 	
	  		 		wardType = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;
	  		 	
	  		 }else{
	  		 	
	  		 		wardType = document.forms[0].strWardCode.value;
	  		 	
	  		 }
	  		  	 
				
			var url = "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService=2"+"&treatmentCat="+treatCat+"&ward="+wardType+"&searchLetter="+obj.value+"&strEndDate="+document.forms[0].strEndDate.value;
		 
				 
			
			ajaxFunction(url,"7");
				
			}else{
				

			if(_id("dropdown1").innerHTML.length <=0){
					
					var input = _id('strOfflineTariffName'+delIndex).value;
					_id('strOfflineTariffName'+delIndex).value = input.substring(0,input.length-1);
 					return false;
					
				}
				

			searchSel(eve,'strOfflineTariffName'+delIndex,'1',obj);		

				
			}
	}
			
	}
	



 


	/**
		 * getOffLineTariffUnit
		 * @param {String} unitId
		 * @param {String} baseValue
		 * @param {String} index 
		 */
		 function getOffLineTariffUnit(unitId,baseValue,index) 
		 {
		 	if(unitId!=0 && baseValue!=0){
		 	var hmode = "TRFUNIT"; 
		 	var modeVal = unitId+"^"+baseValue+"^"+index;
		    var url="IpdBillManagementTransCNT.cnt?hmode="+hmode+"&modName="+modeVal;
			ajaxFunction(url,"10");
			}else{
		 		_id("offlineTariffUnitDivId"+index).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+index+"' class='comboMin' tabindex='2' onChange='calcOffLineTariffNetAmount(\""+index+"\");' ><option value=0>Select Value</option></select>";				
		 		calcOffLineTariffNetAmount(index);
		 	}
		 	
		 	
		 }
	

function getOffLineTariffDiscountDetails(divId,parent){
			
										
			gblTariffDiscountDtlsId = divId;
			
			var tariff =  _id("strOfflineTariffName"+divId).value;	 		 	
	 		 	
		if(tariff != ''){	
			
			if(_id("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value.length > 0){
				
				var temp =  _id("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value.split(",");
					
				document.forms[0].strOffLineTariffDiscountUnit.value = temp[0];
				document.forms[0].strOffLineTariffDiscountType.value = temp[1];
				document.forms[0].strOffLineTariffDiscountBy.value = temp[2];
				document.forms[0].strOffLineTariffDiscountReason.value = temp[3];
				document.forms[0].strOffLineTariffDiscountReasonText.value = temp[4];
				document.forms[0].strOffLineTariffDiscountDate.value = temp[5];	
							
			}else{
			
				setReasonText();
			}
			
				display_popup_menu(parent,'tariffDiscountDtls','400','');
				document.forms[0].strOffLineTariffDiscountUnit.focus();
		}else{
			alert("Please Select a Tariff");
		}
	}

function hideOffLineTariffDiscountDetails(divId)
{
				document.forms[0].strOffLineTariffDiscountUnit.value = "";
				document.forms[0].strOffLineTariffDiscountType.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountBy.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReason.selectedIndex = 0;
				document.forms[0].strOffLineTariffDiscountReasonText.value = "";								
						
		hide_popup_menu(divId);
}

/**
	 * validateTariffDiscountDetails - This Method For Discount Detail PopUP in Add Service 
	 */
	 function validateTariffDiscountDetails()
	  {
	  	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	    hisValidator.addValidation("strOffLineTariffDiscountUnit", "req", "Tariff Discount Unit is a Mandatory Field" );
	    if(document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value == 2)
	    {
		  hisValidator.addValidation("strOffLineTariffDiscountUnit", "numltet=100", "Percentage Cannot be Greater than 100" );
	    }
	    else
	    {
		  hisValidator.addValidation("strOffLineTariffDiscountUnit", "amount=8,2", "Please Enter a Valid Amount" );
	    }
	    hisValidator.addValidation("strOffLineTariffDiscountBy","dontselect=0", "Please Select a Value from Tariff Discount By" );
		if(document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value == 0)
		{
          hisValidator.addValidation("strOffLineTariffDiscountReasonText","req", "Please Enter the Discount Reason" );
		}
		hisValidator.addValidation("strOffLineTariffDiscountDate", "req", "Tariff Discount Date is a Mandatory Field" );
		var retVal = hisValidator.validate(); 
	    hisValidator.clearAllValidations();
		if(retVal)
		{
				var discountUnit = document.forms[0].strOffLineTariffDiscountUnit.value;
				var discountType = document.forms[0].strOffLineTariffDiscountType[document.forms[0].strOffLineTariffDiscountType.selectedIndex].value;
				var discountBy = document.forms[0].strOffLineTariffDiscountBy[document.forms[0].strOffLineTariffDiscountBy.selectedIndex].value;
				var discountReason = document.forms[0].strOffLineTariffDiscountReason[document.forms[0].strOffLineTariffDiscountReason.selectedIndex].value;
				var discountDate = document.forms[0].strOffLineTariffDiscountDate.value;				
				var discountText = document.forms[0].strOffLineTariffDiscountReasonText.value;
				_id("strOfflineTariffDiscountConfigDetails"+gblTariffDiscountDtlsId).value = discountUnit+","+discountType+","+discountBy+","+discountReason+","+discountText+","+discountDate;
				var displayResult = "";
				if(discountType == 2)
				{
				   displayResult = discountUnit+"%";										
				}
				else
				{
				   displayResult = discountUnit;
				}
				_id("strOfflineTariffDiscount"+gblTariffDiscountDtlsId).value = displayResult;					
				hideOffLineTariffDiscountDetails('tariffDiscountDtls');
				calcOffLineTariffNetAmount(gblTariffDiscountDtlsId);
				
		}
		else
		{
			return false;
		}
	 		
  }
 function setSelectedTariff(userValue ,resultText, resultValue)
 {
	     varObj = document.getElementsByName("strOfflineTariffDetailsHidden");		       		 
  		 for(var i=0; i<varObj.length - 1; i++) 
  		 {
  		 	    if(varObj[i].value.split('^')[0] == resultValue.split('^')[0])
  		 	    {
  		 			alert("Tariff Name Already Exists");
  		 			document.forms[0].strTariffCode.value = '';
  					document.forms[0].strTariffCode.focus();		      			
  		 			return false;
  		 		}
  		 }
		
			tariffObj = _id("strOfflineTariffName"+userValue);
			tariffObj.value = resultText;
			tariffObj.readOnly="readOnly";
			var temp = resultValue.split('^');
			
			if(temp[17] == '1')
			{
				tariffObj.readOnly=false;				
				_id("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";				
				_id("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"'  maxlength='8' onkeypress='return validateData(event,7);' tabindex='1' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];				
				_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";				
				_id("strOfflineTempTariffRateUnit"+userValue).value = temp[4];				
			}
			else
			{
				_id("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
				_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
			}
			
			_id("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
			_id("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];				
			_id("strOfflineTariffServiceTax"+userValue).value = temp[13];
			_id("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];			
			_id("strOfflineTariffQty"+userValue).value = 1;			
			_id("strOfflineTariffDate"+userValue).value=document.forms[0].strCtDate.value;				
			_id("strOfflineTariffNetAmount"+userValue).value = 0;
			_id("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;		
			_id("strOfflineTariffDetailsHidden"+userValue).value = resultValue;			
			getOffLineTariffUnit(temp[5],temp[6],userValue);						
			if(_id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value) != null && _id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value).value.length > 0)
			{	 			 	
				generateRows();				 	 
			}			 	
	 		if(_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display == "block")
	 		{
	 			_id("strOfflineTempTariffRateUnit"+userValue).select();
	  	  	  	_id("strOfflineTempTariffRateUnit"+userValue).focus();					
	 		}
	 		else
	 		{
	 			_id("strOfflineTariffQty"+userValue).select();
	  	  	  	_id("strTariffCode").focus();
	 		}
	}

	function setDropDownSelectedTariff(userValue , resultValue){
		var a = resultValue.split('^');
		var b=true;
		for(var i = 0 ; i<a.length; i++)
			if(a[i]!="0"){
				b=false;
				break;
			}
		
		_id("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
		if(b || checkForDuplicate_select('strOfflineTariffDetailsHidden',_id("strOfflineTariffDetailsHidden"+userValue),'Tariff Name')){
					
		var temp = resultValue.split('^');
			
			
			if(temp[17] == '1'){
				
				_id("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";	
				
				_id("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"' tabindex='1'  maxlength='8' onkeypress='return validateData(event,7);' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];
				
				_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";
				
				_id("strOfflineTempTariffRateUnit"+userValue).value = temp[4];
				
				
			}else{
				
				_id("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
				_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
				_id("strOfflineTariffName"+userValue).readOnly="readOnly";
			}
			
			_id("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
			_id("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];
				
			_id("strOfflineTariffServiceTax"+userValue).value = temp[13];
			_id("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];
			
			_id("strOfflineTariffQty"+userValue).value = 1;
			
			_id("strOfflineTariffDate"+userValue).value=document.forms[0].strCtDate.value;	
			
			_id("strOfflineTariffNetAmount"+userValue).value = 0;
			_id("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;
		
			_id("strOfflineTariffDetailsHidden"+userValue).value = resultValue;
											
									
											
			getOffLineTariffUnit(temp[5],temp[6],userValue);
			
				if(_id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value) != null && _id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value).value.length > 0){
	 			 	
				generateRows();
				 	 
				//_id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value ).focus();
				
				
				if(_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display == "block"){
					
					 _id("strOfflineTempTariffRateUnit"+userValue).select();
	  		  	  	  _id("strOfflineTempTariffRateUnit"+userValue).focus();
					
				}else{
					
					  _id("strOfflineTariffQty"+userValue).select();
	  		  	  	  //_id("strOfflineTariffQty"+userValue).focus();
	  		  	  	  _id("strTariffCode").focus();
					
				}
				
				
				 	 
			 	}
	    
	  		
	    
			
		}
		else
		{
			_id("strOfflineTariffDetailsHidden"+userValue).value = "";
			_id("strOfflineTariffName"+userValue).value = "";
			_id("strOfflineTariffName"+userValue).readOnly=false;
			_id("strOfflineTariffName"+userValue).focus();
		}
				
	//	_id("tariffFullNameDiv").innerHTML = "";
		
		
	}
/*
function showTariffSearchPopup(e,compName,index){
	
	var groupId = "";
	var wardCode = "";
		
		
	var groupId = document.forms[0].strOffLineGroup.value;
	var temp = document.forms[0].strHidden.value.split('^');
			
				
		if(_id("wardDivId").style.display == "block" ){
			wardCode = document.forms[0].strOffLineWard.value;
		}
		
		var chargeTypeId = document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value
		var wardId = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value.split('^')[0];
		
//	tariffSearchPopUp(e,compName,"2","",wardCode,groupId,'setSelectedTariff',index);
	tariffSearchPopUp(e,compName,temp[0],chargeTypeId,wardId,groupId,'setSelectedTariff',index);
}*/



/**
 * showTariffSearchPopupOnClick
 */
 function showTariffSearchPopupOnClick() {
 	
 	
 	 var groupId = "";
	var wardCode = "";
		
		
	var groupId = document.forms[0].strOffLineGroup.value;
	var temp = document.forms[0].strHidden.value.split('^');
 	 
 	 	var chargeTypeId = document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value
		var wardId = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value.split('^')[0];
		
 	 
		var compName = document.getElementsByName("strOfflineTariffName")[document.getElementsByName("strOfflineTariffName").length - 2].id;
 		
 		var index = document.getElementsByName("strOfflineTariffName")[document.getElementsByName("strOfflineTariffName").length - 2].id.split("-")[1];
 		
 		var index = "1-"+index;
 		
 	
 	tariffSearchPopUpWithoutEvent(compName,temp[0],chargeTypeId,wardId,groupId,'setSelectedTariff',index);
 	
 }

function showTariffCodeSearchPopup(e,compName,index){
		
		
	var groupId = document.forms[0].strOffLineGroup.value;
	var temp = document.forms[0].strHidden.value.split('^');
		
		var chargeTypeId = document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value;
		var wardId = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value.split('^')[0];
		var wardCode = document.forms[0].strSpecialWardType.value;
		
	tariffCodeSearchPopUp1(e,compName,temp[0],chargeTypeId,wardId,groupId,'setSelectedTariffCode',index,wardCode);
}


	function setSelectedTariffCode(userValue ,resultText, resultValue){
		//alert(resultText);
		if(resultText.includes("^"))
		 {
			 document.forms[0].strTariffCode.value=resultText.split("^")[0];
			 document.forms[0].grpid.value=resultText.split("^")[1];
			 document.forms[0].strTariffCode.focus();
		 }
		 else
		 {
			 document.forms[0].grpid.value="0";
			 document.forms[0].strTariffCode.value = resultText;
			 document.forms[0].strTariffCode.focus();
		 }
	}


/////////////////////////////////////////////////////////////////////////////////
//             VIEW-BILL                   //
//            Pop-Window in                  //
/*function printData()
{
   alert("Inside Print Data..!!"+document.forms[0].hiddenString.value); 
 }*/
//function tariffDtl(parent,index,a,b,c,d,e) //(this,index,ServiceTax,Discount,Penlty,TariffRate,Unit Name)
  function tariffDtl(parent,index,a)
  {        
        var temp = a.split('^');
        var objVal1 = _id("1");
        objVal1.innerHTML = temp[3]+"/"+temp[4];
        var objVal2 = _id("2");
        objVal2.innerHTML = temp[1];        
        var objVal3 = _id("3");
        objVal3.innerHTML = temp[0];        
        var objVal4 = _id("4");
        objVal4.innerHTML = temp[2];   
     /*
        var objVal1 = _id("1");
        objVal1.innerHTML = d+"/"+e;
        var objVal2 = _id("2");
        objVal2.innerHTML = b;        
        var objVal3 = _id("3");
        objVal3.innerHTML = a;        
        var objVal4 = _id("4");
        objVal4.innerHTML = c;
       */         
       /*
         Display a named menu, at the position of another object
         parent -> the parent window in which popup should appear.(in general "this")
	     divId  -> id of the div used in the file. 
	     leftPos -> left position of the popup. 
	     topPos -> top position of the popup.
	     display_popup_menu(parent,divId,leftPos,topPos) 
       */  
      //alert(_id("tariffDtl").value);
       //alert(parent.value);
         display_popup_menu(parent,'tariffDtl','300','');
}

function hidePayDetails(divId)
{
      hide_popup_menu(divId);
}

  function ftn10(index)
  {   
   if(document.forms[0].button10.value != 1)
   {
    _id("detailsdivid0"+index).style.display="block";
    _id("minus10"+index).style.display="block";
    _id("plus10"+index).style.display="none";
    document.forms[0].button10.value = 1;
   }
   else
   {
    _id("minus10"+index).style.display="none";
    _id("plus10"+index).style.display="block";
    _id("detailsdivid0"+index).style.display="none";
   
     document.forms[0].button10.value = 0;
   
   } 
}
function ftn20(obj)
 {     
  if(document.forms[0].button20.value != 1)
   {
    _id("detailsdivid20").style.display="block";
    _id("minus20").style.display="block";
    _id("plus20").style.display="none";
    document.forms[0].button20.value = 1;
   }
   else
   {
    _id("minus20").style.display="none";
    _id("plus20").style.display="block";
    _id("detailsdivid20").style.display="none";
    document.forms[0].button20.value = 0;
   } 
     
 }
 function ftn30(index)
 {     
   if(document.forms[0].button30.value != 1)
   {
    _id("detailsdivid1"+index).style.display="block";
    _id("minus30"+index).style.display="block";
    _id("plus30"+index).style.display="none";
    document.forms[0].button30.value = 1;
    
    if( _id("addButton"+index) != null)
    _id("addButton"+index).value == 0; // Change Value
   
   
   }
   else
   {
    _id("minus30"+index).style.display="none";
    _id("plus30"+index).style.display="block";
    _id("detailsdivid1"+index).style.display="none";
    document.forms[0].button30.value = 0;
   } 
 }
 
 
 
 function resetParticulatDtlsView(){
 	
 	var obj = document.getElementsByName("flagConfig");

	for(var i = 0; i < obj.length ; i++){
		
		_id("flagConfig"+i).value = '0';
		
	}
 	
 }
 
 
 var gblPartIndex = "";
 
 function getParticularsDetails(grpId,accNo,index)
 {	
	var obj = document.getElementsByName("flagConfig");
	for(var i = 0; i < obj.length ; i++)
	{		
		if(i != index)
		_id("particularDtlsId"+i).style.display = "none";		
	}	
	var flg = _id("flagConfig"+index).value;		
	if(flg == '0')
	{
		gblPartIndex = index;		
		_id("flagConfig"+index).value = '1';		
		var mode="GETPARTDTLS";
		var url="IpdBillManagementTransCNT.cnt?hmode="+mode+"&accNo="+accNo+"&grpId="+grpId;
		ajaxFunction(url,"11");		
	}
	else
	{		
		var ptlObj = _id("particularDtlsId"+index);			
		if(ptlObj.style.display == "block")
		{			
			ptlObj.style.display = "none";			
		}
		else
		{
			ptlObj.style.display = "block";			
		}	
	}		
 }
 
 
 
  ////////////////////////////////////////////////////////////////
//         This Method For View Bill 
/*  function getParticularsDetails(obj,grpId,acctNo,index) 
{
   _id("errMsg").innerHTML = "";
   var currId = document.forms[0].currId.value;
   var flg=document.getElementsByName("flag");
   var flag = 0;
   var obj = _id("particularDtlsId"+currId);
  	
  if(currId >= 0 && index != currId) 
  {
    	obj.style.display="none";
  }
  else 
  {
		//same button is clicked
		if(index == currId)
		 {
		 // alert((obj.style.display).toUpperCase());
			if((obj.style.display).toUpperCase() == "BLOCK") 
			{
				obj.style.display="none";
				flag = 1;
			}
		}
		
  }
  if(flg[index].value == "0")
  {
    //_id("id"+currId).style.display="none";
    fetchPopUpData(grpId,acctNo,index);
    document.forms[0].flag[index].value = "1";
  }
  else
  {
  	 if(flag == 0) 
  	 { 
     	_id("particularDtlsId"+index).style.display="block";
     }
  }
   if(flag == 1)
 		document.forms[0].currId.value = -1;		//all div closed
 	else	
 		document.forms[0].currId.value = index;	//particular div is open
 }
 
 //        Ajax Function in Response of Pop-Up       //
 function fetchPopUpData(grpId,acctNo,index)
 { 
     
     var mode="UNITVAL12";
     var modeVal = grpId+"^"+acctNo+"^"+index;
     var url="IpdBillManagementTransCNT.cnt?hmode="+mode+"&modName="+modeVal;
     ajaxFunction(url,"11");
  }
 
 * */

///////////////////VIEW - CHARGES///////////////////////

function getInvisble()
{
  _id("viewCharges").style.display="none";
  document.forms[0].reset();
  document.forms[0].strWardnameCombo.value = "0";
  document.forms[0].strHospitalServiceCombo.value = "0";
   _id("id1").style.display="none"; 
  
}
function getGroupCombo()
{ 
   var mode="UNITVAL1";
   var url="IpdBillManagementTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strHospitalServiceCombo.value;
   ajaxFunction(url,"3");
  
}
function getCombo(mode)
{
  if(mode=="UNITVAL")
  {
   var mode="UNITVAL";
   var url="IpdBillManagementTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strGroupNameCombo.value;
   
   ajaxFunction(url,"1");
  }
}
function getView(mode)
{ 
	
	
	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	hisValidator.addValidation("strHospitalServiceCombo", "dontselect=0", "Please Select A Hospital Service" );
	hisValidator.addValidation("strGroupNameCombo", "dontselect=0", "Please Select A Group" );
	hisValidator.addValidation("strTariffNameCombo", "dontselect=0", "Please Select A Tariff" );
	hisValidator.addValidation("strPatientCategoryCombo", "dontselect=0", "Please Select A Patient Category" );
	
	if(document.forms[0].strHospitalServiceCombo.value == '2'){
	
		hisValidator.addValidation("strWardnameCombo", "dontselect=0", "Please Select A Ward" );
	}
		
	 var retVal = hisValidator.validate();
	 hisValidator.clearAllValidations();

	
 if(retVal)
  {
  if(mode=="VIEWGO")
  {
    if(document.forms[0].strChk_value.value == "0" || document.forms[0].strChk_value.checked == true)
    {
     var date = "0";
     var modeName = document.forms[0].strWardnameCombo.value+"^"+document.forms[0].strHospitalServiceCombo.value+"^"+document.forms[0].strPatientCategoryCombo.value+"^"+document.forms[0].strTariffNameCombo.value+"^"+document.forms[0].strGroupNameCombo.value+"^"+date;
    }
    else
    { 
     var modeName = document.forms[0].strWardnameCombo.value+"^"+document.forms[0].strHospitalServiceCombo.value+"^"+document.forms[0].strPatientCategoryCombo.value+"^"+document.forms[0].strTariffNameCombo.value+"^"+document.forms[0].strGroupNameCombo.value+"^"+document.forms[0].strEffectiveFrmDate.value;
    }
    
    var mode="VIEWGO";
    var url="IpdBillManagementTransCNT.cnt?hmode="+mode+"&modName="+modeName;
    ajaxFunction(url,"2");
  }
 }
}
function getAjaxResponse(res,mode)
{
      var err = _id("errMsg");
      var temp = res.split("####");
   
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 
       
  	if(mode == '7')
	{
	 
			
	  _id("dropdown1").innerHTML = res; 
	  
	  
	  	searchSelWithCode(tempCode,'strOfflineTariffName'+trfdelIndex,'1',trfObj);
	  
    }
  	if(mode == '10')
  	{       
		var temp = res.split('@');
		_id("offlineTariffUnitDivId"+temp[0]).innerHTML = "<select name='strOfflineTariffUnit' id='strOfflineTariffUnit"+temp[0]+"' class='comboMin' onChange='calcOffLineTariffNetAmount(\""+temp[0]+"\");'>"+temp[1]+"</select>";
		calcOffLineTariffNetAmount(temp[0]);   // This Methdo is Called When we Add Value In DropDown Utilty
	}
    if(mode=="13")
    { 
        var rateVal = res.split('@');     //Here We Split the Response from Index
        var objVal = _id("billapprovalId"+rateVal[1]);
        objVal.innerHTML = rateVal[0];
	    objVal.style.display="block";
    }
   if(mode=="11")
   { 
       
       //Here We Split the Response from Index to Get Exact Location Index
       var objVal = _id("particularDtlsId"+gblPartIndex);
       objVal.innerHTML = res;
       objVal.style.display="block";
	   
	}
   if(mode=="1")
   {
	   
	   _id("viewCharges").innerHTML = "";
	   
       var rateVal = res.split('#');
       if(rateVal[1] == null || rateVal[1]=='')
       {
        var objVal = _id("TariffId");
        objVal.innerHTML = "<select name = 'strTariffNameCombo' onchange='resetViewCharge();' >" + rateVal[0] + "</select>";
       } 
       else
       {
         var objVal = _id("errMsg");
         objVal.innerHTML = rateVal[1];
       }
   }
   if(mode=="2")
   {
     //  var objVal = _id("viewCharges");
     //  _id("viewCharges").style.display="block";
     //  objVal.innerHTML = res;
       
       var rateVal = res.split('@');
       
       if(rateVal[1] == null || rateVal[1]=='')
       {
         var objVal = _id("viewCharges");
         _id("viewCharges").style.display="block";
         //alert("Response is :"+rateVal[0]);
         objVal.innerHTML = rateVal[0];

       } 
       else
       {
         var objVal = _id("errMsg");
         objVal.innerHTML = rateVal[1];
       }
       
       
   }
   if(mode=="3")
   {
	   
	   _id("viewCharges").innerHTML = "";
	   
      // var objVal = _id("GrpNameId");
      // objVal.innerHTML = "<select name = 'strGroupNameCombo' onChange=\"getCombo('UNITVAL');\">" + res + "</select>";
       
       var rateVal = res.split('#');
       if(rateVal[1] == null || rateVal[1]=='')
       {
        var objVal = _id("GrpNameId");
        objVal.innerHTML = "<select name = 'strGroupNameCombo' onChange=\"getCombo('UNITVAL');\">" + rateVal[0] + "</select>";

       } 
       else
       {
         var objVal = _id("errMsg");
         objVal.innerHTML = rateVal[1];
       }
     }
     
     
     if(mode == '14')
     {
			var temp = res.split("####");
   	       	if(temp[0] == "ERROR")
   	       	{
		       	_id("normalMsg").style.display ="none";
		       	err.innerHTML = temp[1];
		       	err.style.display = "block";
		       	return;
		    }
		    else
		    {
		      var tempVal = res.split("@@");
		      if(tempVal[0] == '0')
		      {
	      			alert("No Tariff Found to The Corresponding Tariff Code ");
	      			document.forms[0].strTariffCode.value = '';
	      			document.forms[0].strTariffCode.focus();
	      			return false;		      		
		      }
		      else
		      {
		      		var indexVal = '1-'+document.multirow.rowIndex1.value;
		      		varObj = document.getElementsByName("strOfflineTariffDetailsHidden");
		      		for(var i=0; i<varObj.length - 1; i++) 
		      		{
	      		 		if(varObj[i].value.split('^')[0] == tempVal[2].split('^')[0])
	      		 		{
	      		 			alert("Tariff Name Already Exists");
	      		 			document.forms[0].strTariffCode.value = '';
	      					document.forms[0].strTariffCode.focus();		      					
	      		 			return false;
	      		 		}		      		 	
		      		}
		      		document.forms[0].strTariffCode.value = '';
		      		//setSelectedTariff(indexVal ,tempVal[1], tempVal[2] );
		      		setSelectedTariffNew(indexVal ,tempVal[1], tempVal[2] );
		      	}		       	
		    }	
		}	
		
		
		 if(mode=="15"){
	   	   
	   	 var temp = res.split("####");
   
		       if(temp[0] == "ERROR"){
		       	
		       	_id("normalMsg").style.display ="none";
		       	
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }else{
	   
	   var tempRes = res.split("@@@@");
	   
	   			_id("compulsoryChargesDetailsDivId").innerHTML = tempRes[0];
	   			_id("compulsoryChargesDivId").style.display = "none";
	   			
	   			_id("specialChargesDetailsDivId").innerHTML = tempRes[1];
	   			_id("specialChargesDivId").style.display = "none";
	   			_id("AvailedTariffDtlsId").style.display = "block";
	   			
	   			
	   			_id("offlineTariffDivId").style.display = "block";
	   			
	   			document.forms[0].strOfflineTariffDate.value=document.forms[0].strCtDate.value;	   			
	   			
	   			var netTariffValue = calAllTariffNetCost("strOfflineTariffNetAmount");
	   			
	   			_id("totalRecAmtDivId1").value = roundValue(netTariffValue,2);
	   			//_id("totalRecAmtDivId").innerHTML  = "<a style='cursor: pointer;' onclick='showChargeDtl(300,500)' title='Charge Details'><font color='blue'><u>"+netTariffValue+"</u></font></a>";
	   			_id("totalRecAmtDivId").innerHTML  = "<font color='red'>"+roundValue(netTariffValue,2)+"</font>";
	   			hideDietCharges();
	   			
	   			
	   			if(_id("strOfflineTariffQty4") != null){
	   				
	   				//gblTempQtyMainVal = _id("strOfflineTariffQty4").value;
	   				gblTempQtyMainVal= dateDiff(document.forms[0].strStartDate.value , document.forms[0].strEndDate.value);
	   				
	   			}
	   			else
	   			{
	   				gblTempQtyMainVal= dateDiff(document.forms[0].strStartDate.value , document.forms[0].strEndDate.value);
	   			}
	   			
	   			
	   			
	   			
		       }
		       
		       document.forms[0].strTariffCode.value = '';
		       document.forms[0].strTariffCode.focus();
		       
		    //   getTariffDtls();
		       
  	 }
		
		
			if(mode == '16'){
	  
	  			
	  			 var temp = res.split("####");
   
		       if(temp[0] == "ERROR"){
		       	
		       	_id("normalMsg").style.display ="none";
		       	
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }else{
	     	   			
	   			  document.getElementsByName("strdummypartpayment")[0].value = res;
	   			  document.getElementsByName("strPartpayment")[0].value = res;
	   			 
		       }
	  		
	  		ftnT();
	  
    		}
		
		
		if(mode == '17')
		{			 			 
			  var temp = res.split("####");   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
		       _id("specialWardTypeDivId").innerHTML = "<select class='comboNormal' name='strSpecialWardType' >"+res+"</select>";			
		       if(document.getElementsByName("strAdmissionDtlHidVal").length > 0)
		       {								
		    	   document.forms[0].strSpecialWardType.value = document.forms[0].strAdmissionDtlHidVal.value.split('^')[6];
		       }
		       document.forms[0].strTariffCode.focus();
		}	
		if(mode == '18')
		{			 			 
		   var temp = res.split("####");   
	       if(temp[0] == "ERROR")
		   {
	          	err.innerHTML = temp[1];
	          	err.style.display = "block";
	          	return;
	       }			
	       _id("previousDtlsContentDiv").innerHTML = res;	
	       _id("previousDtlsContentDiv").style.display = "none";	 
		}     
     	if(mode == '19')
     	{     		
     			document.forms[0].strPreviousDates.selectedIndex = 0;
	 			_id("previousDtlsContentDiv").innerHTML = "";
	 			_id("previousDtlsContentDiv").style.display = "none";	 		
	 			_id("deleteImg").style.display = "none";     		
     	}     
     	if(mode == '20')
        {
   			var temp = res.split("####");
      	    if(temp[0] == "ERROR")
      	    {
   		       	_id("normalMsg").style.display ="none";
   		       	err.innerHTML = temp[1];
   		       	err.style.display = "block";
   		       	return;
   		    }
   		    else
   		    {
   		      if(res== '0')
   		      {
   	      			alert("No Tariff Found to The Corresponding Tariff Code ");
   	      			document.forms[0].strTariffCode.value = '';
   	      			document.forms[0].strTariffCode.focus();
   	      			return false;		      		
   		      }
   		      else
   		      {
   		    	var table = _id("TABLEALLTARIFFHLP");
   		    	var rowCount = table.rows.length;
   		    	//tbody = table.tbodies[0];
   		    	var row = table.insertRow(rowCount);
   		    	//var row = table.insertRow(0);
   		    	//var row = table.insertRow(0);
   		    	var strNumRowsUpd=document.forms[0].strNumRows.value;
   		    	var strNumRows=strNumRowsUpd
   		    	row.id="TRALLTARIFFHLP"+strNumRowsUpd;
   		    	row.innerHTML=res;
   		    	document.forms[0].strNumRows.value=++strNumRowsUpd;
   		    	document.forms[0].strTariffCode.value = '';
	      		//document.forms[0].strTariffCode.focus();
	      		calcOffLineTariffNetAmountNew(null,strNumRows) ;
	      		_id("groupName"+strNumRows).focus(); 
	      		filterGroup();
   		      }		       	
   		    }	
   		}
     	if(mode == '21')
		{			 			 
			   var temp = res.split("####");   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
		       _id("wardOldDivId").innerHTML = "<select class='comboNormal' name='strWardOld' onchange='setChargeType(this)'>"+res.split('@@')[0]+"</select>";
		       _id("ConsultantTypeDivId").innerHTML = "<select class='comboNormal' name='strDocOld' >"+res.split('@@')[1]+"</select>";
		}
     	if(mode == '22')
		{			 			 
			   var temp = res.split("####");   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
		       _id("wardNewDivId").innerHTML = "<select class='comboNormal' name='strWardNew' id='strWardNew' onchange='setChargeType(this)'>"+res.split('@@')[0]+"</select>";
		       _id("ConsultantTypeDivId1").innerHTML = "<select class='comboNormal' name='strDocNew' id='strDocNew'>"+res.split('@@')[1]+"</select>";
		}
     	
     	if(mode == '23')
		{			 			 
			   var temp = res.split("####");   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
		       _id("UnitComboDivId").innerHTML = "<select class='comboNormal' name='strUnitold' onchange='getWardDtls(this)'>"+res+"</select>";
		       _id("wardOldDivId").innerHTML = "<select class='comboNormal' name='strWardOld' onchange='setChargeType(this)'><option value='0'>Select Value</option></select>";
		       _id("ConsultantTypeDivId").innerHTML = "<select class='comboNormal' name='strDocOld' ><option value='0'>Select Value</option></select>";
		}
     	if(mode == '24')
		{			 			 
			   var temp = res.split("####");   
		       if(temp[0] == "ERROR")
			   {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		       }
		       _id("unitComboDivId1").innerHTML = "<select class='comboNormal' name='strUnitNew' id='strUnitNew' onchange='getWardDtls(this)'>"+res+"</select>";
		       _id("wardNewDivId").innerHTML = "<select class='comboNormal' name='strWardNew' id='strWardNew' onchange='setChargeType(this)'><option value='0'>Select Value</option></select>";
		       _id("ConsultantTypeDivId1").innerHTML = "<select class='comboNormal' name='strDocNew' id='strDocNew'><option value='0'>Select Value</option></select>";
		}
     	if(mode == '25')
        {
   			var temp = res.split("####");
   			var strCurrentRowIndex=document.forms[0].strCurrentRowIndex.value;
 
      	    if(temp[0] == "ERROR")
      	    {
   		       	_id("normalMsg").style.display ="none";
   		       	err.innerHTML = temp[1];
   		       	err.style.display = "block";
   		       	return;
   		    }
   		    else
   		    {
   		      if(res== '0')
   		      {
   	      			alert("No Tariff Found to The Corresponding Tariff Code ");
   	      			_id("trfCode"+strCurrentRowIndex).value =  _id("trfCodeLabel"+strCurrentRowIndex).innerHTML;   	      			
   	      			//document.forms[0].strTariffCode.value = '';
   	      			//document.forms[0].strTariffCode.focus();
   	      			//return false;
   	      			return;
   		      }
   		      else
   		      {
   		    	var table = _id("TABLEALLTARIFFHLP"); 
   	 			var table1 = _id("revtab");
   	 		    //alert(_id("deleteFlag"+strCurrentRowIndex).value);
			    if(_id("deleteFlag"+strCurrentRowIndex).value==2)
			    	_id("deleteFlag"+strCurrentRowIndex).value="1";
			    //alert(_id("deleteFlag"+strCurrentRowIndex).value);
   	 			var currentRowObj=_id("TRALLTARIFFHLP"+strCurrentRowIndex);
   	 			var currentRowObjOuterHTML=currentRowObj.outerHTML;
   	 			var rowCount = table1.rows.length;
   			    	//tbody = table.tbodies[0];
   	 			 //alert("rowCount"+rowCount);
   			    	
   			    var row = table1.insertRow(rowCount);
   			     	//var row = table.insertRow(0);
   			    	//var row = table.insertRow(0);
   			    	
   			    row.id="revtab"+strCurrentRowIndex;
   			    //alert("row.id"+row.id);
   			    	//var newSlNo="slNo"+strNumRows;
   			   
   			    row.innerHTML=currentRowObjOuterHTML;
   		
   			    currentRowObj.innerHTML="";
   			  
   			    _id("deleteFlag"+strCurrentRowIndex).value =  1;
   		    	//alert(_id("deleteFlag"+strCurrentRowIndex).value);
   		    	currentRowObj.outerHTML=res;
   		    	//alert(currentRowObj.outerHTML);
   		    	var newSlNo="slNo"+strCurrentRowIndex;
   		    	var strNumRowsUpd=document.forms[0].strNumRows.value;
   		    	var strNumRows=strNumRowsUpd;
   		    	//alert(strCurrentRowIndex);
   		    	calcOffLineTariffNetAmountNew(null,strCurrentRowIndex) ;
   		    	_id(newSlNo).value=++strCurrentRowIndex;
   		    	filterGroup();
   		    	
	      		 
   		      }		       	
   		    }	
   		}
}

function hideDietCharges() 
{
	
	if(document.forms[0].strAgeInMonths.value<6)
	{
		for(var i=0;i<document.getElementsByName("strOfflineTariffDetailsHidden").length;i++)
		{
			if(document.getElementsByName("strOfflineTariffDetailsHidden")[i].value.split("^")[0]==document.forms[0].dietChargeId.value)
			{
				document.getElementsByName("strCompChargeCheck")[i].checked=false;
				manageCompalsaryChargesTariff(document.getElementsByName("strCompChargeCheck")[i],i+1);
			}
		}
	}
}

function resetViewCharge() {
	
	_id("viewCharges").innerHTML = "";
	
}

function eEnable()
{
	
  document.forms[0].strWardnameCombo.disabled=false;
  if(document.forms[0].strHospitalServiceCombo.value == 0 || document.forms[0].strHospitalServiceCombo.value == 1 || document.forms[0].strHospitalServiceCombo.value ==3)
  {
	  document.forms[0].strWardnameCombo.selectedIndex = 0;
   document.forms[0].strWardnameCombo.disabled=true;
  }
}
function eEnable1()
{
document.forms[0].strHospitalServiceCombo.value = "0"; 
    document.forms[0].strWardnameCombo.disabled=false;
}


//////////////////////UPDATE-ACCOUNT STATUS////////////////////
function groupComboUpdateAcctStatus()
{
  var len = document.forms[0].dr.length;
  if(document.forms[0].dr.value == 0)
  {
    document.forms[0].strRemarksCombo2.value = "";
    document.forms[0].strRemarksCombo2.disabled=false;
  }
  else
  {  
     document.forms[0].strRemarksCombo2.value= document.forms[0].dr[document.forms[0].dr.selectedIndex].text;
     document.forms[0].strRemarksCombo2.disabled=true;
  }
}



function goFuncUpdateAcctStatus()
{
	
	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	
	if(document.forms[0].strNewAcctStatus.value == 0){
	    alert("New Status is a Mandatory Field");
 	}
 	else{
 		var presentAccStatus=document.getElementsByName('strPresentAcctStatusCode')[0].value
 		var newAccStatus=document.getElementsByName('strNewAcctStatus')[0].value
 		
 		if(presentAccStatus!='1' && presentAccStatus!='3' && presentAccStatus!='4')
 		{
 			alert("Account Status Cannot Be Updated")
 			return false
 		}
 		if(newAccStatus!='1' && newAccStatus!='3' && newAccStatus!='4')
 		{
 			alert("New Account Status Can Only Be Active, On-Hold or Dormant")
 			return false
 		}
 		if(newAccStatus==presentAccStatus)
 		{
 			alert("New Account Status Can Not Be Same As Present Account Status")
 			return false
 		}
 		//alert(document.forms[0].finalBillFlag.value);
 		if(newAccStatus=='4' && document.forms[0].finalBillFlag.value=='90')
 		{
 			alert("Bed Transfer needs to be Finalized for making Account Dormant. Kindly check the Bed Transfer Flag");
 			return false;
 		}
 	
		    document.forms[0].strRemarksCombo2.disabled = false;
		 
	        hisValidator.addValidation("strRemarksCombo2","req","Remarks is a Mandatory Field" );
		    hisValidator.addValidation("strApprovedByCombo","dontselect=0","Approved By is a Mandatory Field" );
		    retVal = true; 
		    var retVal = hisValidator.validate();
		}   
	
 //   hisValidator.clearAllValidations();
		if(retVal)
		{
		       	      
		       
				document.forms[0].hmode.value="UPDACCTSTATUSINSERT";
				document.forms[0].submit();
				
		}
		else
		{
			return false;
		}
		
}

//////////////////////ADD-SERVICES/////////////////////////////
function getTariffRate(index,rate)
{
 	var rateVal = rate.split('^');
 //	alert("Inside-getTariffrate()->"+rateVal);
 	_id("strTariffRate"+index).value = rateVal[3];
 
 }
 function enterQty(quaObj,rateObj,costObj)
 {
			var retVal = false;
			var qty= quaObj.value;	
			var rate   = rateObj.value;
			var total  = 0;
		
			if(isNaN(rate) || rate=="") rate = "0";
			if(isNaN(qty)  || qty=="") qty = "0";
			
					total = rate*qty;
					costObj.value = total;
					retVal = true;
				
		
		return retVal;
}
 

//////////////////////PART-PAYMENT//////////////////////////////


function ftnT()
{     
   if(document.forms[0].strPartpayment.value != 0)
   {   	
    _id("id11").style.display="block";
     document.forms[0].strPartpayment.disabled= true;
    document.forms[0].strChk_value.value = 1;
   }
   else
   {
    _id("id11").style.display="none";
    document.forms[0].strPartpayment.disabled= false;
    document.forms[0].strChk_value.value = 0;
   } 
   
     
}



	 /**
	 * showDetails - shows the given divId by showing the minus image and hiding the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function showDetails(divId){
		
		_id(divId).style.display="block";
				
		_id('minus'+divId).style.display="block";
		_id('plus'+divId).style.display="none";
		
	}
	
	
	
	/**
	 * hideDetails - hide the given divId by hiding the minus image and showing the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function hideDetails(divId){
		_id(divId).style.display="none";
			
		_id('minus'+divId).style.display="none";
		_id('plus'+divId).style.display="block";
		
	}
	 



///////////////////PART PAYMENT END//////////////////////////

function ftn11(obj)
 {     
    
   if(document.forms[0].button1.value != 1)
   {
    _id("detailsdivid1").style.display="block";
    _id("minus1").style.display="block";
    _id("plus1").style.display="none";
    document.forms[0].button1.value = 1;
   }
   else
   {
    _id("minus1").style.display="none";
    _id("plus1").style.display="block";
    _id("detailsdivid1").style.display="none";
    document.forms[0].button1.value = 0;
   } 
}
function ftn22(obj)
 {     
  
   if(document.forms[0].button2.value != 1)
   {
    _id("detailsdivid2").style.display="block";
    _id("minus2").style.display="block";
    _id("plus2").style.display="none";
    document.forms[0].button2.value = 1;
   }
   else
   {
    _id("minus2").style.display="none";
    _id("plus2").style.display="block";
    _id("detailsdivid2").style.display="none";
    document.forms[0].button2.value = 0;
   } 
     
 }
 function ftn33(obj)
 {     
   
   if(document.forms[0].button3.value != 1)
   {
    _id("detailsdivid3").style.display="block";
    _id("minus3").style.display="block";
    _id("plus3").style.display="none";
    document.forms[0].button3.value = 1;
   }
   else
   {
    _id("minus3").style.display="none";
    _id("plus3").style.display="block";
    _id("detailsdivid3").style.display="none";
    document.forms[0].button3.value = 0;
   } 
 }
  function ftn44(obj)
 {     
   
   if(document.forms[0].button4.value != 1)
   {
    _id("detailsdivid4").style.display="block";
    _id("minus4").style.display="block";
    _id("plus4").style.display="none";
    document.forms[0].button4.value = 1;
   }
   else
   {
    _id("minus4").style.display="none";
    _id("plus4").style.display="block";
    _id("detailsdivid4").style.display="none";
    document.forms[0].button4.value = 0;
   } 
 }
  
function ftnTick()
{
   if(document.forms[0].strChk_value.value != 0)
   {
   	
    document.forms[0].strPartpayment.disabled=false;
    _id("combo").style.display="block";
    document.forms[0].strChk_value.value = 0;
   }
   else
   {
   //alert("else PartPayment"+document.forms[0].strdummypartpayment.value);
    document.forms[0].strPartpayment.disabled =true;
    document.forms[0].strPartpayment.value = document.forms[0].strdummypartpayment.value;
    _id("combo").style.display="none";
    document.forms[0].strChk_value.value = 1;
   } 

setPartPaymentReasonText();

}
function groupComboPartPayment()
{

  if(document.forms[0].dr[document.forms[0].dr.selectedIndex].value == 0){
    document.forms[0].strRemarksCombo2.value = "";
    document.forms[0].strRemarksCombo2.disabled=false;
  }
  else{  
     document.forms[0].strRemarksCombo2.value= document.forms[0].dr[document.forms[0].dr.selectedIndex].text;
     document.forms[0].strRemarksCombo2.disabled=true;
  }
}

function goFunctionPartPayment()
{
	var hisValidator = new HISValidator("ipdBillManagementTransBean"); 
	
//	alert("CHK-->"+document.forms[0].strChk_value.value);
	if(document.forms[0].strChk_value.checked)
	{
	  

	  hisValidator.addValidation("strPartpayment", "req", "Part Payment Amount is a Mandatory Field Please Enter Greater Than Zero Value" );
	  hisValidator.addValidation("strPartPayment", "amount=8,2", "Enter a Valid Part Payment Amount in one of the Format [000000.00] or [000000]" );
	  
	   hisValidator.addValidation("strApprovedByCombo", "dontselect=0", "Please Select Approved By" ); 
	    hisValidator.addValidation("strRemarksCombo2","req","Remarks is Mandatory Field" );
      var retVal = hisValidator.validate();
      hisValidator.clearAllValidations();
    }
    else
    {
         if(document.forms[0].strPartpayment.value == 0)
	     {
	        alert("PartPayment Should Be Greater than Zero!!");
	     }
	     else
	     {
	        hisValidator.addValidation("strPartpayment", "req", "Part Payment Amount is a Mandatory Field" );
	        hisValidator.addValidation("strPartpayment", "amount=8,2","Enter a Valid Part Payment Amount in one of the Format [000000.00] or [000000]" );
	       
	        var retVal = hisValidator.validate();
	     }
	      hisValidator.clearAllValidations();
   	 
	}
	if(retVal)
	{
		
		
	     document.forms[0].strRemarksCombo2.disabled = false;
 
	     document.forms[0].strPartpayment.disabled = false;
		
		 
		
				document.forms[0].hmode.value="PARTPAYMENTINSERT";
			//	alert("Chk-->"+document.forms[0].strChk_value.value+"whent retVal->"+retVal+"<--document.forms[0].hmode.value-->"+document.forms[0].hmode.value);
				document.forms[0].submit();
	}
	else
	{
	 return false;
	}	
}
/////////////////////////VIEW-CHARGE////////////////////////////
function ftnCheck()
{     
  // alert("Inside ftnl()-->"+document.forms[0].strChk_value.value);
   if(document.forms[0].strChk_value.value != 1)
   {
     _id("id1").style.display="block";
     document.forms[0].strChk_value.value = 1;
   }
   else
   {
    _id("id1").style.display="none";
   
    document.forms[0].strChk_value.value = 0;
   } 
}


function goFuncViewCharges()
{
	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	_id("id2").style.display="block";
	//hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	
	var retVal = hisValidator.validate(); 
	
		if(retVal)
		{
			//	document.forms[0].strCrNo.disabled = false;		
				document.forms[0].hmode.value="VIEWGO";
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
}
/////////////////////ADD CLIENT APPROVAL//////////////////////////////

function goFunctionAddClientApproval()
{
	var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	
	var retVal = hisValidator.validate(); 
	
		if(retVal){
			
		//	document.forms[0].strCrNo.disabled = false;		
			
				document.forms[0].hmode.value="SAVECLTAPPROVAL";
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
}

function cancel1(mode)
{
	if(document.forms[0].strIsCalledFromIpdBillNew.value==1)
	{
	  document.forms[0].hmode.value="CANCELTOIPDDESKNEW";
	  document.forms[0].submit();
	}
	else
	{
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	}
}


//////////////////////////////////////////////////////////////////

	/////////////////////////IPD Bill Managment//////////////////////
	
	function IpdRecordStatus(obj)
	{
	    comValue = obj.value;
		com = obj.id
		var len = com.length;
		var ch  = com.charAt(len-1);
    
	    if(ch==0 && comValue == 2)
      	{
      	  _id("BillApproval").style.color = "red";
		  _id("ViewBill").style.color = "blue";
		  _id("ViewCharges").style.color = "blue";
		  _id("AddService").style.color = "blue";
		  _id("PartPayRequest").style.color = "blue";
		  _id("AddCltApproval").style.color = "blue";
		  _id("UpdateAccountStatus").style.color = "blue";
	 	}
	 	if(ch==0 && comValue == 5)
      	{
      	    
	  	    _id("BillApproval").style.color = "blue";
		    _id("ViewBill").style.color = "blue";
		    _id("ViewCharges").style.color = "blue";
		    _id("AddService").style.color = "red";
		    _id("PartPayRequest").style.color = "red";
		    _id("AddCltApproval").style.color = "red";
		    _id("UpdateAccountStatus").style.color = "red";
	 	}
	 	
	 	
	//	alert("Plz Select Value from All Combo..");
 }
// Function Fired When Check Box is Click 
function testFunction(obj)
{  
    var totalChk = 0;
	//alert("inside Client test function....!" + obj.value);
	// alert("Chk is Checked or Not...!"+document.forms[0].chk.checked);
	 //alert("In Chk(comboid0).value>>"+_id("comboid0").value);
	if(_id("comboid0").value == 2)
	{ 
	        _id("AddService").style.color = "blue";
		    _id("PartPayRequest").style.color = "blue";
		    _id("AddCltApproval").style.color = "blue";
		    _id("UpdateAccountStatus").style.color = "blue"; 
		    _id("BillApproval").style.color = "blue";  
            _id("ViewBill").style.color = "blue";
	}
    
  
}
/////////////////function to Check selected combo value on next page
function callMe1(form1,mode,display)
{
  add(mode);
}

//////////////////////////IPD CHK COLOR////////////////////////////////////
function IpdcheckColor(mode,display)
{
        
   	        if( mode == "ADDCLTAPPROVAL" && _id("AddService").style.color == "blue")
			{
			 add(mode);
		    }
		    
		    if( mode == "ADDSERVICE" && _id("AddService").style.color == "blue")
			{
			 add(mode);
		    }
		    if( mode == "PARTPAYMENTREQUEST" && _id("PartPayRequest").style.color == "blue")
			{
			 add(mode);
		    }			    			
		   if(mode == "VIEWCHARGES")
			{		
			 add(mode);
		    } 			
	       if(mode == "UPDATEACCOUNTSTATUS" && _id("UpdateAccountStatus").style.color == "blue")
			{				
			  add(mode);
		    }
	//	    if(mode == "BILLAPPROVAL" && _id("BillApproval").style.color == "blue")
	        if(mode == "BILLAPPROVAL")
			{
			//  alert("Color is::"+_id("BillApproval").style.color);				
			  add(mode);
		    }
		    if(mode == "VIEWBILL" && _id("ViewBill").style.color == "blue")
			{				
			  
			 add(mode);
		    }
}
function displayCreditDetails(parent,obj)
{	
	_id("creditDtlsDivId").style.display = "";
	_id("plusCreditDtlsId").style.display = "none";
	_id("minusCreditDtlsId").style.display = "";
	//popup('creditDtlsDivId' , '300','300');	
}
function hideCreditDetails(divId)
{
	_id("creditDtlsDivId").style.display = "none";
	_id("plusCreditDtlsId").style.display = "";
	_id("minusCreditDtlsId").style.display = "none";
	//hide_popup('creditDtlsDivId');
	
}
function onEntDiscount(id)
{
	
	var flag = 1;
	var disUnitObj = _id("strDiscount"+id);
	var disTypeObj = _id("strDiscountType"+id); 
	var urg=document.forms[0].strUrgSur.value;
	var strDiscountUnit = disUnitObj.value;
 	var strDiscountType = disTypeObj.value; 
 	if(strDiscountType == "" || strDiscountType == null) strDiscountType = "0";
 	strDiscountType = parseInt(strDiscountType,10);
 	
 	if(strDiscountUnit == "" || strDiscountUnit == null) strDiscountUnit = "0";
 	strDiscountUnit = parseFloat(strDiscountUnit);
 	
 	
 	if (strDiscountType == 2)
 	{
 		if (strDiscountUnit > 100)
 		{
 			alert("Discount can not be greater than 100%");
 			disUnitObj.value = "0.00";
 			flag = 0;
 		}
 	}
 	
	var strReqQty=_id("strOfflineTariffQty"+id).value;
	var strTrfRate=_id("strOfflineTariffRateUnit"+id).value.split(' /')[0];
	/*if(_id("strPriority"+id).value=="1")
	{
	    strTrfRate=_id("strOfflineTariffRateUnit"+id).value.split(' /')[0];
	    //_id("strOfflineTariffRateUnit"+id).value=Math.round(strTrfRate)+" / No.";
	}
	else
	{
		strTrfRate=_id("strOfflineTariffRateUnit"+id).value.split(' /')[0]*(100+parseFloat(urg))/100;
		//_id("strOfflineTariffRateUnit"+id).value=Math.round(strTrfRate)+" / No.";
	}  */ 
	var strTrfAmt = calTariffCost(strReqQty,1,strTrfRate,1,0); 
	//alert(strTrfAmt);
	if (flag == 1)
 	{		 	
	 	var discountAmt = calTrfDiscountCost(strTrfAmt,strReqQty,1,strDiscountUnit,strDiscountType,1);
	 	
	 	if (parseFloat(strTrfAmt) - Math.abs(parseFloat(discountAmt)) < 0)
	 	{
			alert("Discount Amt can not be greater than tariff total amount !!");
	 		disUnitObj.value = "0.00";
	 		discountAmt = 0;
	 		_id("strDiscountAmt"+id).value=discountAmt;
	 		
	    }
 	}
	else
	 	 discountAmt = 0;
	
	if(document.forms[0].strAvailedPackageId.value!="0")
    {
    	alert("Discount cannot be availed when Package is present");
    	disUnitObj.value = "0.00";
 		discountAmt = 0;
 		_id("strDiscountAmt"+id).value=discountAmt;
    }
	//alert(parseInt(_id("strDisActAmt"+id).value)+parseInt(_id("strDiscountAmt"+id).value));
	_id("strDiscountAmt"+id).value=discountAmt;
	//_id("strDiscountAmtDivId"+id).innerHTML = discountAmt;
	var x=parseFloat(strTrfAmt)+parseFloat(discountAmt);
	_id("strOfflineTariffNetAmount"+id).value=roundValue(Math.round(parseFloat(x)),2);
	//_id("strOfflineTariffNetAmountDivId"+id).innerHTML=Math.round(parseFloat(x));
	//_id("deleteFlag"+id).value =  3;//Deleted Flag=2 Means-New Row,1 Means -Deleted Row,0-Means No Change, 3 Means- Modify Data
	calcTotalRecAmount();
	checkAmtChanged(id);
	
}
function calActualDisAmt(id)
{
	
	var amt=(-1 * parseInt(_id("strDisActAmtTmp"+id).value))+parseInt(_id("strDiscountAmt"+id).value);
	//alert(amt);
	_id("strDisActAmt"+id).value=amt;
}
function moveUpDown(e,obj,index)
{
	//alert(e.keyCode);
	if(e.keyCode == 38 )//Up Arrow
	{
		var newIndex=--index;
		var nextElementId=obj.name+""+newIndex;
		var objLen=document.getElementsByName("deleteFlag").length;
		//alert(objLen);
		//alert(index);
		for(var i=index;i>=0;i--)
		{
			//alert(_id("deleteFlag"+i).value);
			//if(_id("deleteFlag"+i).value==0)//Not Deleted
			//{
				/*if(_id(nextElementId)!=null || _id(nextElementId)!=undefined)
				{
					_id(nextElementId).focus();
					_id(nextElementId).select();
					return;
				}*/
				if(_id(obj.name+""+i)!=null || _id(obj.name+""+i)!=undefined)
				{
					_id(obj.name+""+i).focus();
					//_id(obj.name+""+i).select();
					return;
				}
			//}
		}
	}
	if(e.keyCode == 40 )//Down Arrow
	{ 			
		var newIndex=++index;
		var nextElementId=obj.name+""+newIndex;
		//alert(nextElementId);
		//alert(_id(nextElementId));
		var objLen=document.getElementsByName("deleteFlag").length;
		//alert(objLen);
		//alert(index);
		for(var j=index;j<objLen;j++)
		{
			//alert(_id("deleteFlag"+j).value);
			//if(_id("deleteFlag"+j).value==0)//Not Deleted
			//{
				if(_id(obj.name+""+j)!=null || _id(obj.name+""+j)!=undefined)
				{
					_id(obj.name+""+j).focus();
					//_id(obj.name+""+j).select();
					return;
				}
			//}
		}
	}
}

var masterHotKeyCode=18; // Key :: ALT 
var cntrlKey=false;
var exceptionKeysCombo=false; // ALT[17] + CTRL[18] + R[82]/S[83] In case of Billing
var _helpKeyCode=112; // Key :: F1
var enableShortCutKey=true;


//Function <checkFirstKey> is where Hot Key is tracked
function checkFirstKey(e)
{
	//alert(e.keyCode);
	if(e.keyCode==masterHotKeyCode)
	{
	    first_key_Down=true;	    
	}  
	if(first_key_Down && cntrlKey==false)
	{
		if(e.keyCode==18)
		{
			cntrlKey=true;
		}
	}
	if(first_key_Down && cntrlKey)
	{
		if(e.keyCode==82 || e.keyCode==83)
		{
		   exceptionKeysCombo=true;
		}   
	}		
}

function deleteRow(e,obj,index,trIndex)
{
	//alert(e.keyCode);
	//alert(masterHotKeyCode);
	//alert(first_key_Down);
	//alert(e.keyCode);
	
	if(e.keyCode==masterHotKeyCode || exceptionKeysCombo)
	{
	    first_key_Down  = false;	    
	    exceptionKeysCombo = false;
	}
	else
	{
		 if(first_key_Down == true && enableShortCutKey)
	 	 {
	 		if(e.keyCode == 110 )//Delete Numpad . Key
	 		{
	 			 if(_id("strServTypeId"+index).value!="0")//Online or Reverse Entry
	 		 	 {
	 		 		 alert("Online Tariffs Can Not Be Deleted...");
	 		 		 return false;
	 		 	 }
	 			else
	 			{
		 			_id("deleteFlag"+index).value=1; 
		 			//_id(trIndex).style.display="none";
		 			_id(trIndex).className ="strikeout";
		 			
		 			var newIndex=++index;
		 			var nextElementId=obj.name+""+newIndex;
		 			var objLen=document.getElementsByName("deleteFlag").length;
		 			//alert(objLen);
		 			//alert(index);
		 			for(var i=index;i<objLen;i++)
		 			{
		 				//alert(_id("deleteFlag"+i).value);
		 				if(_id("deleteFlag"+i).value==0)
		 				{
		 					if(_id(nextElementId)!=null || _id(nextElementId)!=undefined)
		 					{
		 						_id(nextElementId).focus();
		 						_id(nextElementId).select();
		 						return;
		 					}
		 				}			
		 			}
	 			}
	 		}
	 		if(e.keyCode == 85 )//U
	 		{
	 			 if(_id(trIndex).className =="strikeout")//Already Deleted
	 		 	 {
	 				_id("deleteFlag"+index).value=0; 
		 			_id(trIndex).className ="";
	 		 	 }
	 		}
	 		if(e.keyCode == 107 )//+Focus Trf Code
	 		{
	 			document.forms[0].strTariffCode.focus();	 			
	 		}
	 	 }	 		 
	 }
	calcOffLineTariffNetAmountNew(obj,index) ;
	first_key_Down=false;		
} 

function deleteRow1(e,obj,index,trIndex)
{
	//alert(e.keyCode);
	//alert(masterHotKeyCode);
	//alert(first_key_Down);
	//alert(e.keyCode);
	
	if(e.keyCode==masterHotKeyCode || exceptionKeysCombo)
	{
	    first_key_Down  = false;	    
	    exceptionKeysCombo = false;
	}
	else
	{
		 if(first_key_Down == true && enableShortCutKey)
	 	 {
	 		if(e.keyCode == 110 )//Delete Numpad . Key
	 		{
	 			/* if(_id("strServTypeId"+index).value!="0")//Online or Reverse Entry
	 		 	 {
	 		 		 alert("Online Tariffs Can Not Be Deleted...");
	 		 		 return false;
	 		 	 }
	 			else
	 			{*/
		 			_id("deleteFlag"+index).value=1; 
		 			//_id(trIndex).style.display="none";
		 			_id(trIndex).className ="strikeout";
		 			
		 			var newIndex=++index;
		 			var nextElementId=obj.name+""+newIndex;
		 			var objLen=document.getElementsByName("deleteFlag").length;
		 			//alert(objLen);
		 			//alert(index);
		 			for(var i=index;i<objLen;i++)
		 			{
		 				//alert(_id("deleteFlag"+i).value);
		 				if(_id("deleteFlag"+i).value==0)
		 				{
		 					if(_id(nextElementId)!=null || _id(nextElementId)!=undefined)
		 					{
		 						_id(nextElementId).focus();
		 						_id(nextElementId).select();
		 						return;
		 					}
		 				}			
		 			}
	 		//	}
	 		}
	 		if(e.keyCode == 85 )//U
	 		{
	 			 if(_id(trIndex).className =="strikeout")//Already Deleted
	 		 	 {
	 				_id("deleteFlag"+index).value=0; 
		 			_id(trIndex).className ="";
	 		 	 }
	 		}
	 		
	 	 }	 		 
	 }
	//calcOffLineTariffNetAmountNew(obj,index) ;
	first_key_Down=false;		
}
function setSelectedTariffNew(userValue ,resultText, resultValue)
{
	 varObj = document.getElementsByName("strOfflineTariffDetailsHidden");		       		 
	 for(var i=0; i<varObj.length - 1; i++) 
	 {
	 	    if(varObj[i].value.split('^')[0] == resultValue.split('^')[0])
	 	    {
	 			alert("Tariff Name Already Exists");
	 			document.forms[0].strTariffCode.value = '';
				document.forms[0].strTariffCode.focus();		      			
	 			return false;
	 		}
	 }	
	tariffObj = _id("strOfflineTariffName"+userValue);
	tariffObj.value = resultText;
	tariffObj.readOnly="readOnly";
	var temp = resultValue.split('^');
	
	if(temp[17] == '1')
	{
		tariffObj.readOnly=false;				
		_id("strOfflineTariffRateUnitDivId"+userValue).style.display = "none";				
		_id("strOfflineTempTariffRateUnitDivId"+userValue).innerHTML= "<input type='text' name='strOfflineTempTariffRateUnit' id='strOfflineTempTariffRateUnit"+userValue+"' class='txtFldMin' value='"+temp[4]+"'  maxlength='8' onkeypress='return validateData(event,7);' tabindex='1' onkeyup='setRateValue(\""+userValue+"\");' >" +" / "+temp[14];				
		_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "block";				
		_id("strOfflineTempTariffRateUnit"+userValue).value = temp[4];				
	}
	else
	{
		_id("strOfflineTariffRateUnitDivId"+userValue).style.display = "block";
		_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display = "none";
	}
	
	_id("strOfflineTariffRateUnit"+userValue).value = temp[4]+" / "+temp[14];
	_id("strOfflineTariffRateUnitDivId"+userValue).innerHTML = temp[4]+" / "+temp[14];				
	_id("strOfflineTariffServiceTax"+userValue).value = temp[13];
	_id("strOfflineTariffServiceTaxDivId"+userValue).innerHTML = temp[13];			
	_id("strOfflineTariffQty"+userValue).value = 1;			
	_id("strOfflineTariffDate"+userValue).value=document.forms[0].strCtDate.value;				
	_id("strOfflineTariffNetAmount"+userValue).value = 0;
	_id("strOfflineTariffNetAmountDivId"+userValue).innerHTML = 0;		
	_id("strOfflineTariffDetailsHidden"+userValue).value = resultValue;			
	getOffLineTariffUnit(temp[5],temp[6],userValue);						
	if(_id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value) != null && _id("strOfflineTariffName1-"+ document.multirow.rowIndex1.value).value.length > 0)
	{	 			 	
		generateRows();				 	 
	}			 	
	if(_id("strOfflineTempTariffRateUnitDivId"+userValue).style.display == "block")
	{
		_id("strOfflineTempTariffRateUnit"+userValue).select();
  	  	_id("strOfflineTempTariffRateUnit"+userValue).focus();					
	}
	else
	{
		_id("strOfflineTariffQty"+userValue).select();
  	  	_id("strTariffCode").focus();
	}
}

/**
 * getTariffByCode
 * @param {Object} teriffCode 
 * @param {event} e
 */
 function getTariffByCodeNew(teriffCode , e,index) 
 {
 //if(e.keyCode == 13 && document.forms[0].strTariffCode.value.length > 0)
	 var obj=teriffCode;
 	if(e.keyCode == 13 && obj.value.length > 0)
 	{
 		var hmode;
 		if(document.forms[0].grpid.value!="107")
 			hmode = "TARIFFCODEDTLSNEW";
 		else
 			hmode = "TARIFFCODEDTLSNEWDRUG";
	 	var treatCat = "0";
 	 	if(document.getElementsByName("strTreatmentCategory").length > 0)
 	 	{
			treatCat = document.forms[0].strTreatmentCategory.value;	
		}
 	  	if(document.getElementsByName("strNewTreatmentCategory").length > 0)
 	  	{
			treatCat =  document.forms[0].strNewTreatmentCategory[document.forms[0].strNewTreatmentCategory.selectedIndex].value ;	
		}
 		var wardType = "0";
 		 
 		if(document.getElementsByName("strWardType").length > 0 )
 		{
 		 	wardType = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;
 		}
 		else
 		{
 		 	wardType = document.forms[0].strWardCode.value;
 		}
 		
 		var url;
 	
	    //alert(document.forms[0].strSpecialWardType.value);
 		if(obj.name=='strTariffCode')//If Search By Tariff COde Text Box
 		{
 			url= "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService=2"+"&treatmentCat="+treatCat+"&ward="+wardType+"&tariffCode="+obj.value+"&strNumRows="+document.forms[0].strNumRows.value+"&strCurrindex="+-1+"&wardCode="+document.forms[0].strSpecialWardType.value;
 			document.forms[0].strCurrentRowIndex.value=index;
 			document.forms[0].strCurrentRowTrfCode.value=obj.value;
 			ajaxFunction(url,"20");
 			
 		}
 		else//If Search By Existing Entered Tariff COde-Modify Tariff
 		{
 			url= "IpdBillManagementTransCNT.cnt?hmode="+hmode+"&groupId="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value+"&hService=2"+"&treatmentCat="+treatCat+"&ward="+wardType+"&tariffCode="+obj.value+"&strNumRows="+document.forms[0].strNumRows.value+"&strCurrindex="+index+"&wardCode="+document.forms[0].strSpecialWardType.value;
 			document.forms[0].strCurrentRowIndex.value=index;
 			document.forms[0].strCurrentRowTrfCode.value=obj.value;
 			ajaxFunction(url,"25");
 			
 		}
 			
 		document.forms[0].grpid.value="0";
		return false;
	}	
 	
 	return false;
 }
 
 function setCurrentRowTrfCode(obj,index)
 {
	 document.forms[0].strCurrentRowTrfCode.value=obj.value;
 }
 
 function calcOffLineTariffNetAmountNew(obj,index) 
 { 
	var tariff =  _id("strOfflineTariffName"+index).value;	 		 	
	var a = _id("strOfflineTariffDetailsHidden"+index).value.split('^');
	var b=true;
	var urg=document.forms[0].strUrgSur.value;
	var rate;
	var actRate;
	for(var i = 0 ; i<a.length; i++)
	if(a[i]!="0")
	{
		b=false;
		break;
	}	
	if(b ||tariff != '')
	{
		var temp = _id("strOfflineTariffDetailsHidden"+index).value.split('^');
		if(_id("strPriority"+index).value=="1")//NORMAL(N)-1 ,URGENT(E) -2
		{
			rate = temp[4];	 
	 		actRate = temp[11];
		}
		else
		{
			rate = temp[4]*(100+parseFloat(urg))/100;	 
	 		actRate = temp[11]*(100+parseFloat(urg))/100;
		}	
		if(temp[17] == '1')
		{
			if(_id("strPriority"+index).value=="1")
	 		{
	 			rate = _id("strOfflineTempTariffRateUnit"+index).value;
	 			actRate = rate;//ACTUAL RATE AND USER ENTRY RATE ALL SHOULD BE SAME.CHANGED ON 29.05.17 BY AJAY DESHWAL
	 		}
			else
			{
				rate = _id("strOfflineTempTariffRateUnit"+index).value*(100+parseFloat(urg))/100;
	 			actRate = rate*(100+parseFloat(urg))/100;
			}
		}
		if(_id("strServTypeId"+index).value == '1')
		{
			_id("strOfflineTariffRateUnit"+index).value=roundValue(Math.round(rate),2);
		}else
			{
			rate=_id("strOfflineTariffRateUnit"+index).value;
			}
		var rateBaseValue = temp[6];
		var qtyBaseValue = "0";
		var qtyVal =  _id("strOfflineTariffQty"+index).value;
		var discountVal = _id("strDiscount"+index).value;
		var serviceVal = _id("strOfflineTariffServiceTax"+index).value;
		var discountType = _id("strDiscountType"+index).value;
		var netAmt = 0;
	
		if(_id("strOfflineTariffUnit"+index).selectedIndex != null)
		{		
			qtyBaseValue = _id("strOfflineTariffUnit"+index).options[_id("strOfflineTariffUnit"+index).selectedIndex].value;
		}
		else
		{		
			qtyBaseValue = _id("strOfflineTariffUnit"+index).value+"^1";		
		}
		
		var calAmt = calTrfNetAmountWithActTrfAmt(rate, actRate ,rateBaseValue,discountVal,discountType,qtyVal,qtyBaseValue,serviceVal,0,0);	
		netAmt = calAmt.oNetTrfAmt;
		var groupid =temp[1];
		//var groupConsumableCharge =temp[18];
		//addConsumableCharge(groupid,groupConsumableCharge);
		
		_id("strOfflineTariffServiceTaxAmtVal"+index).value = calAmt.oSerAmt;
		_id("strOfflineActualTariffAmtVal"+index).value = calAmt.oActTrfAmt;
		_id("strOfflineTariffNetAmount"+index).value = roundValue(Math.round(netAmt),2);
		_id("strDiscountAmt"+index).value = calAmt.oDisAmt;
		//_id("strOfflineTariffNetAmountDivId"+index).innerHTML = Math.round(netAmt);
		checkAmtChanged(index);
		var recObj = document.getElementsByName("strReceivedAmt");                     
		if(recObj.length > 0)
		{
			calcGrantTotalExpenseAmount(); 
		}
		else
		{
			calcTotalRecAmountNew();                     	
		}            
		calActualDisAmt(index);
    }                     
}

 function calcTotalRecAmountNew()
 {	
 	var total = 0;
 	var roundTotal = 0;
 	total = calAllTariffNetCostWithDeletedFlag("strOfflineTariffNetAmount"); 	
    if(document.getElementsByName("strFinalAmt").length > 0)
    {
    	var expAmt  = document.forms[0].strFinalAmt.value;
        finalExpAmt = parseInt(expAmt);
 	    total1 =  manipulateValue(total,finalExpAmt,0);
 	    roundTotal = roundValue(total1,2);
  	 	document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=roundTotal;
	 	var totalCharge=roundValue(parseFloat(roundTotal),0);
  	    _id("totalRecAmtDivId1").value    = totalCharge;
  	    calcNetAmountForBillApprovalTwo();
  	}
  	else
  	{	
  	    var roundTotal = roundValue(total,2);
  	   	document.getElementsByName("strOfflineTotalPayAmountWithoutConsumable")[0].value=roundTotal;
	 	var totalCharge=roundValue(parseFloat(roundTotal),0);
	 	_id("totalRecAmtDivId").innerHTML = "<font color='red'>"+totalCharge+"</font>";
  	    _id("totalRecAmtDivId1").value    = totalCharge;    	
    } 	
 }
 function checkAmtChanged(index)
 {
	//alert(_id("strOrigActAmt"+index).value);
	//alert(_id("strOfflineTariffNetAmount"+index).value);
	
	if(_id("strOrigActAmt"+index).value!=_id("strOfflineTariffNetAmount"+index).value && _id("deleteFlag"+index).value!="2")
	{
		_id("deleteFlag"+index).value =  3;//Deleted Flag=2 Means-New Row,1 Means -Deleted Row,0-Means No Change, 3 Means- Modify Data
	}
 }
 function setRequestDate(index)
 {
	 //alert('1');
	 _id("deleteFlag"+index).value =  3;//Deleted Flag=2 Means-New Row,1 Means -Deleted Row,0-Means No Change, 3 Means- Modify Data
 }
function setChargeType(obj)
{
	var setVal=obj.value.split('^')[1];
	if(obj.name=='strWardOld')
		document.forms[0].strChargeTypeOld.value=setVal+'^'+setVal;
	else
		document.forms[0].strChargeTypeNew.value=setVal+'^'+setVal;
}
function sortDates(a, b)
{
    return new Date(a) - new Date(b);
}
var gblchk=0;
var check='0';
var strchargetype='';
function addBedTransfer()
{
	var i=0;
	var chkval=0;
	var dates = [];
	var dates1 = [];
	var outdate= document.getElementsByName('strOutDate');
	var indate= document.getElementsByName('strOutDate');
	strchargetype= document.getElementsByName('strTransferChargeType');
	var strchartypeid=(document.getElementsByName('strTransferChargeType')[strchargetype.length-1].value).split('^')[0];
	var strchargetypecmb=(document.forms[0].strChargeTypeNew.value).split('^')[0];
	for(i=0;i<outdate.length;i++)
		{
		gblchk=outdate.length;
		var temp =document.getElementsByName('strOutDate')[i].value;
		if(temp=='')
			{
			chkval++;
			
			var datetemp =new Date();
			temp= datetemp.getDate()+'-'+(datetemp.getMonth()+1)+'-'+datetemp.getFullYear()+' '+datetemp.getHours()+':'+datetemp.getMinutes()+':00';
			}
		var temp1=temp.split('-')[2];
		dates.push(temp);
		
		var temp2 =document.getElementsByName('strInDate')[i].value;
		var temp3=temp.split('-')[2];
		dates1.push(temp2);
		}
	var sorted = dates.sort(sortDates);
	var maxDate = sorted[sorted.length-1];
	//alert(maxDate);
	var tmp1=maxDate.split(" ")[0];
    var tmp12=maxDate.split(" ")[1];
    //alert(temp12.split('-')[2]+'-'+temp12.split('-')[1]+'-'+temp12.split('-')[0]+'T'+temp123.split(":")[0]+':'+temp123.split(":")[1]);
	var maxDate1234=new Date(tmp1.split('-')[2]+'-'+tmp1.split('-')[1]+'-'+tmp1.split('-')[0]+'T'+tmp12.split(":")[0]+':'+tmp12.split(":")[1]);
	
	
	
	var sorted1 = dates1.sort(sortDates);
	var maxDate1 = sorted1[sorted1.length-1];
	//alert(maxDate1);
	var temp12=maxDate1.split(" ")[0];
    var temp123=maxDate1.split(" ")[1];
    //alert(temp12.split('-')[2]+'-'+temp12.split('-')[1]+'-'+temp12.split('-')[0]+'T'+temp123.split(":")[0]+':'+temp123.split(":")[1]);
	var maxDate12=new Date(temp12.split('-')[2]+'-'+temp12.split('-')[1]+'-'+temp12.split('-')[0]+'T'+temp123.split(":")[0]+':'+temp123.split(":")[1]);
	
	
	
	var oldstartDate = new Date(document.forms[0].strOldStartDateYYYY.value+'-'+document.forms[0].strOldStartDateMM.value+'-'+document.forms[0].strOldStartDateDD.value+'T'+document.forms[0].strOldStartDateHH.value+':'+document.forms[0].strOldStartDateSS.value);
	var oldstartDate1 = new Date(document.forms[0].strOldStartDateYYYY.value+'/'+document.forms[0].strOldStartDateMM.value+'/'+document.forms[0].strOldStartDateDD.value);
	var olddate = new Date(document.forms[0].strOldEndDateYYYY.value+'-'+document.forms[0].strOldEndDateMM.value+'-'+document.forms[0].strOldEndDateDD.value+'T'+document.forms[0].strOldEndDateHH.value+':'+document.forms[0].strOldEndDateSS.value);
	var olddate1 = new Date(document.forms[0].strOldEndDateYYYY.value+'/'+document.forms[0].strOldEndDateMM.value+'/'+document.forms[0].strOldEndDateDD.value);
   
	var newdate = new Date(document.forms[0].strNewStartDateYYYY.value+'-'+document.forms[0].strNewStartDateMM.value+'-'+document.forms[0].strNewStartDateDD.value+'T'+document.forms[0].strNewStartDateHH.value+':'+document.forms[0].strNewStartDateSS.value);
    
    var newdate1 = new Date(document.forms[0].strNewStartDateYYYY.value+'/'+document.forms[0].strNewStartDateMM.value+'/'+document.forms[0].strNewStartDateDD.value);
    var newwardEndDate = new Date(document.forms[0].strNewEndDateYYYY.value+'-'+document.forms[0].strNewEndDateMM.value+'-'+document.forms[0].strNewEndDateDD.value+'T'+document.forms[0].strNewEndDateHH.value+':'+document.forms[0].strNewEndDateSS.value);
    var cureentDate = new Date();
    //alert(newwardEndDate);
    /*alert(document.forms[0].strNewStartDateYYYY.value);
    alert(document.forms[0].strNewStartDateMM.value);
    alert(document.forms[0].strNewStartDateDD.value);*/
   // alert(newdate1);
    
    //alert(oldstartDate);
    
   if((strchartypeid == '2' || strchartypeid == '4' || strchartypeid == '4') && (strchargetypecmb == '6' ))
	 {
	   check='1';
	 }else
		 {
		 check='0';
		 }
    //alert(check);
    if(newdate=='Invalid Date')
    	{
    	alert('Please Enter Valid Date');
    	return false;
    	}
    if(newwardEndDate == 'Invalid Date')
    	{
    	alert('Please Enter Valid Date');
    	return false;
    	}
    
    if(check == '2')
    	{
		    if(oldstartDate - maxDate12 ===0)
			{
		    	
			}else
				{
				alert('OLD WARD/BED Start Date Must Be Equal To In Date');
		    	return false;
				}
		    if(chkval == '0')
		    	{
		    if(olddate - maxDate1234 ===0)
			{
		    	
			}else
				{
					alert('OLD WARD/BED End Date Must Be Equal To Out Date');
			    	return false;
				}
		    }
   }
    
    if(oldstartDate > olddate)
    {
    	alert('OLD WARD/BED End Date Must Be Greater Than OLD WARD/BED Start Date');
    	return false;
	}
    //alert(olddate);
    //alert(newdate);
 /*   if(olddate  - newdate === 0)
	{
    	alert('NEW WARD/BED Strat Date Must Be Greater Than OLD WARD/BED End Date');
    	return false;
	}*/ 
    
    if(newdate > cureentDate)
    {
    	alert('NEW WARD/BED Start Date Must Be less than Current Date');
    	return false;
    }	
    if(newwardEndDate > cureentDate)
    {
    	alert('NEW WARD/BED End Date Must Be Less Than Current Date');
    	return false;
    }	
    if(check == '0')
	{
	    if(olddate  > newdate)
		{
		alert('NEW WARD/BED Strat Date Must Be Greater Than OLD WARD/BED End Date');
		return false;
		}
	}
    if(newwardEndDate < newdate)
	{
    	alert('NEW WARD/BED Strat Date Must Be Greater Than NEW WARD/BED End Date');
    	return false;
	}
    if(newwardEndDate - newdate ===0)
	{
    	alert('NEW WARD/BED Strat Date Must Be Greater Than NEW WARD/BED End Date');
    	return false;
	}
   
	addBedTransferRow();
   	
   	//var row = table.insertRow(0);
   	//var row = table.insertRow(0);
   	//var strNumRowsUpd=document.forms[0].strNumRows.value;
   	//var strNumRows=strNumRowsUpd
   	//row.id="TRBEDTRANSFERHLP"+strNumRowsUpd;
   	//row.innerHTML=res;
   	//document.forms[0].strNumRows.value=++strNumRowsUpd; 
}
function addBedTransferRow() 
{
	var classname='';
	var consulatntname='';
	var isdobuleoc='';
	 if(document.forms[0].strDocNew.value=='0')
		{
		 consulatntname='--';
		}else
			{
			consulatntname=document.forms[0].strDocNew[document.forms[0].strDocNew.selectedIndex].text;
			}
	if(check == '1')
		{
		//classname='editor-texts2';
		classname='editor-texts';
		isdobuleoc='D.O.';
		document.getElementsByName('trfType')[strchargetype.length-1].value='D.O.';
		document.getElementsByName('strIsDoubleOc')[strchargetype.length-1].value='1';
		document.getElementsByName('strDblOccDate')[strchargetype.length-1].value=document.forms[0].strNewEndDateDD.value+"-"+document.forms[0].strNewEndDateMM.value+"-"+document.forms[0].strNewEndDateYYYY.value+" "+document.forms[0].strNewEndDateHH.value+':'+document.forms[0].strNewEndDateSS.value+':00';;
		}else
			{
			classname='editor-texts';
			isdobuleoc='Offline';
			}
	
	var table = _id("TABLEBEDTRANSFERHLP");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var cell = row.insertCell(0);
	var element = document.createElement("input");
	element.type = "text";
	element.name="strMovNo";
	element.value=rowCount;
	//element.accessKey="deleteRow1(event,this,'"+rowCount+"','TRBEDTRANSFERHLP'"+rowCount+"' ')";
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);

	/*
	sb.append("<tr id='"+trId+"' clas='style_x'> ");
	sb.append("<td class='trfTd' width='5%'><input type='text'  tabindex='1' name='movNo'      id='movNo"+idx+"'  	 onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ ++sl +"' readonly></td> ");
	sb.append("<td class='trfTd' width='15%'><input type='text' tabindex='1' name='deptName'   id='deptName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(3)+"' readonly></td> ");
	sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='unitName'   id='unitName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(5)+"' readonly></td>  ");			
	sb.append("<td class='trfTd' width='15%'><input type='text' tabindex='1' name='wardName'   id='wardName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(7)+"' readonly></td> ");
	sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='roomName'   id='roomName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(9)+"' readonly></td> ");
	sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='bedName'    id='bedName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(11)+"' readonly></td> ");
	sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='inDate'     id='inDate"+idx+"' 	 onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(12)+"' readonly></td> ");
	sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='outDate'    id='outDate"+idx+"' 	 onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(13)+"' readonly></td>");
	sb.append("<td class='trfTd' width='5%'><input type='text'  tabindex='1' name='trfType'    id='trfType"+idx+"' 	 onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(14)+"' readonly/></td>");
	
	*/
	
	var cell = row.insertCell(1);
	var element = document.createElement("input");
	element.type = "text";
	element.name="deptName";
	//element.value=document.forms[0].strDepartmentNew[document.forms[0].strDepartmentNew.selectedIndex].text;
	element.value=_id("strDepartmentNew")[_id("strDepartmentNew").selectedIndex].text;
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);
	
	var cell = row.insertCell(2);
	var element = document.createElement("input");
	element.type = "text";
	element.name="unitName";
	//element.value=document.forms[0].strUnitNew[document.forms[0].strUnitNew.selectedIndex].text;;
	element.value=_id("strUnitNew")[_id("strUnitNew").selectedIndex].text;
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);

	var cell = row.insertCell(3);
	var element = document.createElement("input");
	element.type = "text";
	element.name="wardName";
	//element.value=document.forms[0].strWardNew[document.forms[0].strWardNew.selectedIndex].text;
	element.value=_id("strWardNew")[_id("strWardNew").selectedIndex].text;
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);
	
	var cell = row.insertCell(4);
	var element = document.createElement("input");
	element.type = "text";
	element.name="docName";
	if(document.forms[0].strDocNew.value=='0')
	    element.value=consulatntname;
	else
		element.value=_id("strDocNew")[_id("strDocNew").selectedIndex].text;
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);
	/*
	var cell = row.insertCell(5);
	var element = document.createElement("input");
	element.type = "text";
	element.name="bedName";
	element.value="";
	cell.className="trfTd";
	element.className="editor-texts";
	cell.appendChild(element);
	*/
	var cell = row.insertCell(5);
	var element = document.createElement("input");
	element.type = "text";
	element.name="inDate";
	element.value=document.forms[0].strNewStartDateDD.value+"-"+document.forms[0].strNewStartDateMM.value+"-"+document.forms[0].strNewStartDateYYYY.value+" "+document.forms[0].strNewStartDateHH.value+":"+document.forms[0].strNewStartDateSS.value+':00';
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);
	
	var cell = row.insertCell(6);
	var element = document.createElement("input");
	element.type = "text";
	element.name="outDate";
	element.value=document.forms[0].strNewEndDateDD.value+"-"+document.forms[0].strNewEndDateMM.value+"-"+document.forms[0].strNewEndDateYYYY.value+" "+document.forms[0].strNewEndDateHH.value+":"+document.forms[0].strNewEndDateSS.value+':00';
	cell.className="trfTd";
	element.className=classname;
	cell.appendChild(element);
	
	/*var cell = row.insertCell(7);
	var element = "<input type='text' name='trfDateDD' maxlength='2'  id='trfDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='editor-textsmall' value=''>-" +
				  "<input type='text' name='trfDateMM' maxlength='2'  id='trfDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='editor-textsmall' value=''>"+
				  "<input type='text' name='trfDateYYYY' maxlength='2'  id='trfDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='editor-textsmall' value=''>";
	
	cell.appendChild(element);*/
	
	var cell = row.insertCell(7);
	var element = document.createElement("div");
	//element.type = "text";
	//element.name="trfType";
	//element.value="Offline";
	cell.className="trfTd";
	//element.className="editor-texts";
	var dept=document.forms[0].strDepartmentNew.value;
	var ward=document.forms[0].strWardNew.value;
	var chargeType=document.forms[0].strChargeTypeNew.value;
	var dept=document.forms[0].strDepartmentNew.value;
	var unit=document.forms[0].strUnitNew.value;
	var docName=document.forms[0].strDocNew.value;
	var inDate=document.forms[0].strNewStartDateDD.value+"-"+document.forms[0].strNewStartDateMM.value+"-"+document.forms[0].strNewStartDateYYYY.value+" "+document.forms[0].strNewStartDateHH.value+':'+document.forms[0].strNewStartDateSS.value+':00';
	var outDate=document.forms[0].strNewEndDateDD.value+"-"+document.forms[0].strNewEndDateMM.value+"-"+document.forms[0].strNewEndDateYYYY.value+" "+document.forms[0].strNewEndDateHH.value+':'+document.forms[0].strNewEndDateSS.value+':00';
	
	//alert(strchargetype.length);
	
	element.innerHTML = "<input name='trfType' class='"+classname+"' type='text' value='"+isdobuleoc+"'>"+
			"<input type='hidden' name='strTransferDeptCode' id='strTransferDeptCode' value='"+dept+"'>" +
			"<input type='hidden' name='strTransferWardCode' id='strTransferWardCode' value='"+ward+"'>"+
			"<input type='hidden' name='strTransferChargeType' id='strTransferChargeType' value='"+chargeType+"'>"+
			"<input type='hidden' name='strInDate' id='strInDate' value='"+inDate+"'>"+
			"<input type='hidden' name='strOutDate' id='strOutDate' value='"+outDate+"'>"+
			"<input type='hidden' name='strSaveFlag' id='strSaveFlag' value='1'>"+
			"<input type='hidden' name='strUnitCode' id='strUnitCode' value='"+unit+"'>"+
			"<input type='hidden' name='strConsultant' id='strConsultant' value='"+docName+"'>"+
			"<input type='hidden' name='deleteFlag' id='deleteFlag' value='0'>"+
			"<input type='hidden' name='adtOnlineFlag' id='adtOnlineFlag'  value='1'>"+// DATA ENETRED FROM IPD BILL DESK
			"<input type='hidden' name='strIsDoubleOc' id='strIsDoubleOc' value='"+check+"'>"+
			"<input type='hidden' name='strDblOccDate' id='strDblOccDate' value='"+outDate+"'>"
			;
	cell.appendChild(element);
	/*
	var cell = row.insertCell(9);
	var element = document.createElement("div");
	element.innerHTML = "<input type='text' name='trfDateDD' maxlength='2'  id='trfDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='editor-textsmall' value=''>-" +
				  "<input type='text' name='trfDateMM' maxlength='2'  id='trfDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='editor-textsmall' value=''>"+
				  "<input type='text' name='trfDateYYYY' maxlength='2'  id='trfDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='editor-textsmall' value=''>";
	
	cell.appendChild(element);*/
	//alert(parseInt(rowCount-1));
	//alert(gblchk);
	var temp =document.getElementsByName('strOutDate')[gblchk-1].value;
	//alert(temp);
	if(temp == '')
	{
		//alert('INSIDE');
		_id("strOutDate"+(rowCount-2)).value=document.forms[0].strOldEndDateDD.value+"-"+document.forms[0].strOldEndDateMM.value+"-"+document.forms[0].strOldEndDateYYYY.value+" "+document.forms[0].strOldEndDateHH.value+":"+document.forms[0].strOldEndDateSS.value+":00";
		_id("strTransferChargeType"+(rowCount-2)).value=document.forms[0].strChargeTypeOld.value;
		
	}

	document.forms[0].strDepartmentOld.value=document.forms[0].strDepartmentNew.value;
	document.forms[0].strWardOld.value=document.forms[0].strWardNew.value;
	document.forms[0].strChargeTypeOld.value=document.forms[0].strChargeTypeNew.value;
	document.forms[0].strOldStartDateDD.value=document.forms[0].strNewStartDateDD.value;
	document.forms[0].strOldStartDateMM.value=document.forms[0].strNewEndDateMM.value;
	document.forms[0].strOldStartDateYYYY.value=document.forms[0].strNewEndDateYYYY.value;
	document.forms[0].strOldEndDateDD.value=document.forms[0].strNewEndDateDD.value;
	document.forms[0].strOldEndDateMM.value=document.forms[0].strNewEndDateMM.value;
	document.forms[0].strOldEndDateYYYY.value=document.forms[0].strNewEndDateYYYY.value;
	document.forms[0].strOldStartDateHH.value=document.forms[0].strNewStartDateHH.value;
	document.forms[0].strOldStartDateSS.value=document.forms[0].strNewStartDateSS.value;
	document.forms[0].strOldEndDateHH.value=document.forms[0].strNewEndDateHH.value;
	document.forms[0].strOldEndDateSS.value=document.forms[0].strNewEndDateSS.value;
	
	var currentDate= new Date();
	document.forms[0].strNewStartDateDD.value=document.forms[0].strOldEndDateDD.value;
	document.forms[0].strNewStartDateMM.value=document.forms[0].strOldEndDateMM.value;
	document.forms[0].strNewStartDateYYYY.value=document.forms[0].strOldEndDateYYYY.value;
}
function setNewStartDate(obj)
{
	var d = new Date(document.forms[0].strOldEndDateYYYY.value+'-'+document.forms[0].strOldEndDateMM.value+'-'+document.forms[0].strOldEndDateDD.value);
	d.setDate(d.getDate() + 1);
	document.forms[0].strNewStartDateDD.value=d.getDate();
	document.forms[0].strNewStartDateMM.value=d.getMonth()+1;
	document.forms[0].strNewStartDateYYYY.value=d.getFullYear();
}

function goFuncBedTransfer()
{	
		var hisValidator = new HISValidator("ipdBillManagementTransBean");  
		var retVal = hisValidator.validate(); 
		var bedTransFlg =_names("isBillFinal");
	
		if(retVal)
		{	
			
			//alert(document.getElementsByName('strInDate').length);
			var x=document.getElementsByName('strInDate').length-1;
			if(document.getElementsByName('strInDate')[x].value=="")
			{
				alert("Patient is currently On Transit. Kindly Accept the patient or Cancel Transfer");
				return false;
			}
			//alert(table+rowCount.value);
			/*if(confirm("Bed Charges will not be applied. Press Cancel to apply"))
			    document.forms[0].strbcflag.value="0";	
			else
				document.forms[0].strbcflag.value="1";	*/
			if(bedTransFlg[0].checked !=true)
			{
				alert("Bed transfer Flag is Unchecked");
				return false;
			}
			
				if(confirm("Are You Sure to Save it? \n After Save No Modification Allowed"))
				{	
					document.forms[0].strNewTreatmentCategory.disabled=false;
					document.forms[0].strChargeTypeOld.disabled=false;
					document.forms[0].strChargeTypeNew.disabled=false;
					
					document.forms[0].hmode.value="BEDTRANSFERINSERT";
					document.forms[0].submit();					
				}
				else
				{					
					// COMMENTED AFTER SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE  22092017 BY AMIT ATERIA
					//generateRows();
					document.forms[0].strTariffCode.focus();
					return false;					
				}
			
			
		}
		else
		{		
			// COMMENTED AFTER SINGLE SCREEN SUGGESSTIONS IN NIMS UAT CLOSURE  22092017 BY AMIT ATERIA
			//generateRows();
			document.forms[0].strTariffCode.focus();
			return false;
		}		
}
function noduesprint1()
{
	var obj=document.getElementsByName("chk");
	var c=0;
	for(var i=0;i<obj.length;i++)
	{
		if(_id("chk"+i).checked==true)
			c++;
	}
	if(c==0)
	{
		alert("Kindly select an admission detail");
		return false;
	}
    if(document.forms[0].printFlag.value!="1")
    {
    	alert("No Dues can be printed only when final bill is printed");
    }
    else
    {
    	//document.forms[0].isOpenPopUp.value=1;
    	document.forms[0].hmode.value="NODUESPRINTSAVE";
    	document.forms[0].submit();
    }
    	
}
function getgoDetails(obj)
{
	//alert(obj.checked);
	//alert(obj.value);
	var obj=document.getElementsByName("chk");
	var d=0;
	for(var i=0;i<obj.length;i++)
	{
		if(_id("chk"+i).checked==true)
			d++;
	}
	if(d==0)
	{
		_id("chk"+0).checked=true;
		_id("chk"+0).focus();
	}
	for(var i=0;i<obj.length;i++)
	{
		if(_id("chk"+i).checked==true)//if(obj.checked)
		{  
			document.forms[0].strAddmissionNo.value=_id("chk"+i).value.split('^')[0];
			document.forms[0].strAdmissionDate.value=_id("chk"+i).value.split('^')[1];
			document.forms[0].strDischargeDate.value=_id("chk"+i).value.split('^')[2];
			document.forms[0].printFlag.value=_id("chk"+i).value.split('^')[3];
			document.forms[0].strAccountNo.value=_id("chk"+i).value.split('^')[4];
			document.forms[0].strCrNo.value=_id("chk"+i).value.split('^')[5];
			document.forms[0].strTreatmentCategory.value=_id("chk"+i).value.split('^')[6];
			document.forms[0].strDeptId.value=_id("chk"+i).value.split('^')[7];
			document.forms[0].strWardCode.value=_id("chk"+i).value.split('^')[8];
			document.forms[0].strBillNo.value=_id("chk"+i).value.split('^')[9];
		}
	}
	/*else
		document.forms[0].printFlag.value="0";*/
}
function openPrintPopUp()
{
	if(document.forms[0].isOpenPopUp.value==1 && document.forms[0].printMode.value==1)//Printing Over Laser Printer
	{
		window.print();
			//window.close();
		
		/*var url='CashCollectionOfflineTransCNT.cnt?hmode=PRINTSLIP&filePath='+document.forms[0].filePath.value
		  child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');  
		  child.moveTo(900,250);
		  child.focus();*/
	
	 }
	
	//alert(window.matchMedia('print'));
	 document.forms[0].isOpenPopUp.value=0;
	 //window.onbeforeprint = beforePrint;
	 //window.onafterprint = hidePrintableSlip();
	 //setTimeout("hidePrintableSlip()",2000);
	 //_id("printableSlip").style.display="none"; 
}
function hidePrintableSlip()
{
	//alert("hide");
	_id("printableSlip").style.display="none"; 
}
function printLastBill()
{
	//alert("show");
	//_id("printableSlip").style.display="";
	//alert(document.forms[0].filePath.value);
	if(document.forms[0].filePath.value=="")
	{
		alert("No Bill Generated Yet.");
		return;
	}
	else
	window.print();
}
function showPrintableSlip()
{
	//alert("show");
	_id("printableSlip").style.display=""; 
}
var globalCnt=0;
String.prototype.replaceAll=function(target, replacement) 
{
	  return this.split(target).join(replacement);
}
function printSlip()
{
	window.print();
	if(parseInt(globalCnt)<=2)
		var t=setTimeout("printSlip1()",2000);
}

function printSlip1()
{
	    if (confirm("Whether Printed Successfully?")) 
        {
            	window.close();
				self.close();
        }
        else
        {
        	globalCnt++;
            printSlip();
        }
}
function deleteRowClick(obj,index,trIndex)
{
	if(_id("strServTypeId"+index).value!="0")//Online or Reverse Entry
 	 {
 		 alert("Online Tariffs Can Not Be Deleted...");
 		 return false;
 	 }
	else
	{
		_id("deleteFlag"+index).value=1; 
		//_id(trIndex).style.display="none";
		_id(trIndex).className ="strikeout";
		
		var newIndex=++index;
		var nextElementId=obj.name+""+newIndex;
		var objLen=document.getElementsByName("deleteFlag").length;
		//alert(objLen);
		//alert(index);
		for(var i=index;i<objLen;i++)
		{
			//alert(_id("deleteFlag"+i).value);
			if(_id("deleteFlag"+i).value==0)
			{
				if(_id(nextElementId)!=null || _id(nextElementId)!=undefined)
				{
					_id(nextElementId).focus();
					_id(nextElementId).select();
					return;
				}
			}			
		}
	}
}

function filterGroup()
{
	var tableobj=_id("TABLEALLTARIFFHLP");
	var trObj;
	var groupName = document.forms[0].strOffLineGroup;
	var groupNameText=groupName.options[groupName.selectedIndex].text
	
	if(groupName.value != 0)
	{
		for(var i=0;i<tableobj.rows.length;i++)
		{
				var trObj=_id("TRALLTARIFFHLP"+i);
				var grpId=_id("strOfflineTariffDetailsHidden"+i).value.split('^')[1];//value="1071222^109^1.17^1.17^1.17^1701^1^0^0^1^1.17^1.17^0.00^0^NO.^5^10100595^0^0^Dossier^10"
				//alert(grpId);
				var txt = trObj.querySelectorAll('td')[1].querySelector('input').value;

				if(txt==groupNameText || grpId==groupName.value)
				{
					trObj.style.display = 'block';
				}
				else{
					trObj.style.display = 'none';
				}
			}
		}
	else
		{
			for(var i=0;i<tableobj.rows.length;i++)
			{
				var trObj=_id("TRALLTARIFFHLP"+i);
				trObj.style.display = 'block';
			}
		
		}
	}
//Anonymous isBillFinal
//for code optimization.Add it in gloabal js for Proper otimisation for getElementsByName,id and others.
function _id(str)
{		return document.getElementById(str);}
function _names(str)
{		return document.getElementsByName(str);}