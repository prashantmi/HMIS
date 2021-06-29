
/**
 * showFirstPage()
 * function called on clear button to come back on first page 
 */
 function showFirstPage() {
 	
 	document.forms[0].strCrNo.value = "";
 	document.forms[0].CrNo.value = "";
 	document.forms[0].hmode.value = "REFUND";
				document.forms[0].submit();
 }	

/**
  * setSelectedCrNo
  * @param {String} crNo 
  */
  function setSelectedCrNo(crNo)
   {
   	document.forms[0].strCrNo.value = crNo;
  	//document.forms[0].strCrNo.focus();
  	goFunc();
  }
  
/**
 *  function to show patient demographic details on click of +sign
 */
function showinfo(){
	document.getElementById("plus").style.display="none";
	document.getElementById("minus").style.display="block";
	document.getElementById("patdtltld").style.display="block";
}

/**
 *  function to hide patient demographic details on click of -sign 
 */
function hideinfo(){
	document.getElementById("plus").style.display="block";
	document.getElementById("minus").style.display="none";
	document.getElementById("patdtltld").style.display="none";
}
/**
 * goFunc
 * @param {type}  
 * function called on GO Button to call GO method of CNT
 */
 function goFunc() {
  
  		if(document.getElementById("errMsg").innerHTML.length > 0){
  			
  			alert(document.getElementById("errMsg").innerHTML);
  			return false;
  		}
  
  
 if(document.forms[0].strCrNo.disabled == false){
 
 	var hisValidator = new HISValidator("refundApprovalTransBean");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	   // hisValidator.addValidation("strCrNo", "minlen=13", "CR No. must be 13 Digits" );
	     hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].CrNo.value=document.forms[0].strCrNo.value;
	    if(retVal){
	    		//alert("gofunc");
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}else{
		
		return false;
		}
 }else{
 	return false;
 }
 }
 
 /**
	   * initGoFunc
	   * @param {Event} eve 
	   */
	   function initGoFunc(eve){
	   
	    var flag=validateData(eve,5);
  		 if(flag){
	   	
	   	if(eve.keyCode == 13){
												
				goFunc();
				
				return false;
			}	   	

  }else{
	   		return false;
	   }			

	   }
	 
 
/**
 * function called on onLoad to show all the fields if cr no. is not null
 * showPatientDetails
 * @param {type}  
 */
 function showPatientDetails()
  {
	SetCursorToTextEnd('strCrNoId');
 	if(document.forms[0].CrNo.value!="")
 	{
		document.forms[0].strCrNo.readOnly=true;
		document.getElementById("details").style.display="block";
		document.getElementById("patdtltld").style.display="block";
		document.getElementById("billdtls").style.display="block";
		var billDtl = document.getElementsByName("bServ_id");
		billDtl[0].checked='true';
		groupCheck(document.getElementById("radio0"));
		document.getElementById("combo").style.display="block";
		document.getElementById("lastbuttons").style.display="block";
		document.getElementById("onlyClearbutton").style.display="none"; 
		document.forms[0].strOtherReason.value=document.forms[0].refRsn[document.forms[0].refRsn.selectedIndex].text;
		document.forms[0].strOtherReason.readOnly=true;  
		
	}
	else
	{
		document.forms[0].strCrNo.focus();
	}
 }
 
 /**
 * function to show the selected option of combo in text field
 */
	
function showText(){
	if(document.forms[0].refRsn.value == "0"){	
		document.forms[0].strOtherReason.value="";
		document.forms[0].strOtherReason.focus();
		document.forms[0].strOtherReason.readOnly=false;  
	}
	else{
	document.forms[0].strOtherReason.value=document.forms[0].refRsn[document.forms[0].refRsn.selectedIndex].text;
	document.forms[0].strOtherReason.readOnly=true;  
	}
}


/**
 * function groupCheck(chkObj,index)
{ 
 * @param {type} param 
 */
 function groupCheck(chkObj) {
	 
 	document.forms[0].strBillNo.value= chkObj.value;
    document.forms[0].strBillDate.value= chkObj.value.split("^")[17];
 	document.forms[0].chkValue.value = chkObj.value;
 	var j;
     var mode="TrfDtl";
     var url="RefundApprovalTransCNT.cnt?hmode="+mode+"&modName="+chkObj.value;
     ajaxFunction(url,"1");
 }
 
 function getAjaxResponse(res,mode)
 {
 
    // alert("Res Of Ajax is-->"+res);
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
   
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 
      if(mode == '1')
      {
         var objEle = document.getElementById("trfDtls");
         objEle.innerHTML = res;
         var radios = document.getElementsByName('bServ_id');
         for (var i = 0; i < radios.length; i++) {
             if (radios[i].type === 'radio' && radios[i].checked) {
            	 paymentmodval=document.getElementById("PaymentModeflg"+i).value;
                 if(paymentmodval == '4' || paymentmodval=='5')
                	 {
                	 document.getElementById("td1").style.display="";
                	 document.getElementById("td2").style.display="";
                	 }else
                		 {
                		 document.getElementById("td1").style.display="none";
                    	 document.getElementById("td2").style.display="none";
                		 }
             }
         }
         //alert(document.forms[0].PaymentModeflg.value);
      }
	  if(mode == '2')
	  {
		 var objEle = document.getElementById("menu1");
	     objEle.innerHTML = res;
      	 objEle.style.display="none";
		 display_popup_menu(pWindow,"menu1","","");
      }
 }
 
 
/**
 * trfChk
 * Dummy Method
 * @param {type} index,qtyVal 
 */
 
 function trfChk1(index,isPack,unitid,rate,baseVal,dis,disUnit,serTax)
 {
   // alert("CheckBox Hidd value:::->"+document.getElementById("chkHidd"+index).value);
   //alert("chkRefundQty(index)-->>>"+chkRefundQty(index).value);
  var chk    = document.getElementsByName("chk");
  var chkVal = document.getElementById("chk"+index).value;
  // alert("ChkBox Value is::::"+chkVal);
  if(typeof(chkRefundQty(index))=="undefined")
  {
        alert("Inside if chkRefundQty(index) is Undefeiende!!!!");
        document.getElementById("qty"+index).readOnly=true;
 		document.getElementById("unit"+index).disabled=true;
 		document.getElementById("refundType"+index).disabled=true;
 	 //	document.getElementById("refundAmt"+index).value = refAmt.oNetTrfAmt;
 		
     // var refDiv = document.getElementById("refCost"+index);
 	 //	refDiv.innerHTML =refAmt.oNetTrfAmt;
 		
 		var refDiv = document.getElementById("refCost"+index);
 		refDiv.innerHTML = '0.00';
 		
 		var grossAmt =calAllTariffNetCost('refundAmt');
  		document.forms[0].trf_grossRefund.value = grossAmt;
  		
  		var netRef = document.getElementById("netRefCost");
  		netRef.innerHTML=grossAmt;
  }
  else
  {
   if(chkRefundQty(index))
   {
     var qty = document.getElementById("qty"+index).value;
     var unit = document.getElementById("unit"+index).value;
     var penalty = document.getElementById("trf_penalty"+index).value;
     var refAmt = calTrfNetAmount(rate,baseVal,dis,disUnit,qty,unit,serTax,penalty,1);
     if(chkVal.length > 2)
 	 {
  	   	document.getElementById("qty"+index).readOnly=false;
 		document.getElementById("unit"+index).disabled=false;
 		document.getElementById("refundType"+index).disabled=false;
 		document.getElementById("refundAmt"+index).value = refAmt.oNetTrfAmt;
 		
 		var refDiv = document.getElementById("refCost"+index);
 		refDiv.innerHTML =refAmt.oNetTrfAmt;
 		
 		var grossAmt =calAllTariffNetCost('refundAmt');
  		document.forms[0].trf_grossRefund.value = grossAmt;
  		
  		var netRef = document.getElementById("netRefCost");
  		netRef.innerHTML=grossAmt;
 		
 	  }
 	  else
 	  {
 	       	document.getElementById("trf_penalty"+index).value=0;
 			document.getElementById("qty"+index).value=0;
 			document.getElementById("qty"+index).readOnly=true;
 			document.getElementById("unit"+index).disabled=true;
 			document.getElementById("refundType"+index).disabled=true;
 			var netAmt = parseFloat(document.forms[0].trf_grossRefund.value);
 			var refMinus =  parseFloat(document.getElementById("refundAmt"+index).value);
 			document.getElementById("refundAmt"+index).value ='0.00';
 			
 			var refDiv = document.getElementById("refCost"+index);
 			refDiv.innerHTML = '0.00';
 			
 			var sub = manipulateValue(netAmt,refMinus,1);
 			document.forms[0].trf_grossRefund.value=roundValue(sub,2);
 			
 			var netRef = document.getElementById("netRefCost");
   			netRef.innerHTML=roundValue(sub,2);
 	  }
 	}
   }  
 }
 /*--------------Method in Use for Tariff Chk-------------------------*/
 function trfChk(index,isPack,unitid,rate,baseVal,dis,disUnit,serTax)
 {
  var chk    = document.getElementsByName("chk");
  var chkVal = document.getElementById("chk"+index).value;
  var temp = chkVal.split("^");
  
  var qty     = temp[1];
  var unit    = document.getElementById("unit"+index).value;
  var penalty = temp[16];
  if(document.getElementById("chkHidd"+index).value == '0')
  {
     document.getElementById("chkHidd"+index).value = '1';
     var refAmt = calTrfNetAmount(rate,baseVal,dis,disUnit,qty,unit,serTax,penalty,1);
     document.getElementById("qty"+index).readOnly=false;
 	 document.getElementById("unit"+index).disabled=false;
 	 document.getElementById("refundType"+index).disabled=false;
 	 document.getElementById("refundAmt"+index).value = refAmt.oNetTrfAmt;
 		
 	 var refDiv = document.getElementById("refCost"+index);
 	 refDiv.innerHTML =refAmt.oNetTrfAmt;
 	
 	 var grossAmt =calAllTariffNetCost('refundAmt');
  	 document.forms[0].trf_grossRefund.value = grossAmt;
  		
  	 var netRef = document.getElementById("netRefCost");
  	 netRef.innerHTML=grossAmt;
  	 
  	 var objEle = document.getElementById("refCostDiv"+index);
	     objEle.style.display="none";
  	 
  }
  else
  {
     document.getElementById("chkHidd"+index).value = '0';
     var qty = document.getElementById("qty"+index).value;
     var unit = document.getElementById("unit"+index).value;
     var penalty = document.getElementById("trf_penalty"+index).value;
     var refAmt = calTrfNetAmount(rate,baseVal,dis,disUnit,qty,unit,serTax,penalty,1);
     
     document.getElementById("trf_penalty"+index).value=0;
 	 document.getElementById("qty"+index).value=0;
 	 document.getElementById("qty"+index).readOnly=true;
 	 document.getElementById("unit"+index).disabled=true;
 	 document.getElementById("refundType"+index).disabled=true;
 	
 	 var netAmt = parseFloat(document.forms[0].trf_grossRefund.value);
 	 var refMinus =  parseFloat(document.getElementById("refundAmt"+index).value);
 	 
 	 document.getElementById("refundAmt"+index).value ='0.00';
 			
 	 var refDiv = document.getElementById("refCost"+index);
 	 refDiv.innerHTML = '0.00';
 			
 	 var sub = manipulateValue(netAmt,refMinus,1);
 	 document.forms[0].trf_grossRefund.value=roundValue(sub,2);
 			
 	 var netRef = document.getElementById("netRefCost");
   	 netRef.innerHTML=roundValue(sub,2);
   	 
   	 //var objEle = document.getElementById("refCostDiv"+index);
	 //objEle.style.display="block";
  }
 }
 
 //calculate refund cost
 function calRefundCost(index,isPack,remQty,rate,baseVal,dis,disUnit,serTax,unitid)
 {
 	var chkVal = document.getElementById("chk"+index);	//checkbox
 	var chkHiddObj = document.getElementById("chkHidd"+index) //identify that which check box is seleceted
 	var qtyObj = document.getElementById("qty"+index);	//refund qty
 	var preQtyObj = document.getElementById("HidRefundQty"+index);  //initial refund qty
 	var penObj = document.getElementById("trf_penalty"+index);
 	var refundTypeObj = document.getElementById("refundType"+index);
 	//var unitObj = document.getElementById("unit"+index);
 	var refCostObj = document.getElementById("refCost"+index);
 	var refCostHiddObj = document.getElementById("refundAmt"+index);
 	
 	penObj.value = "0";
 	refundTypeObj.selectedIndex = 0;
 		
 	//checked
 	if(chkVal.checked == true)
 	{
 		chkHiddObj.value = "1";
 		qtyObj.readOnly = false;
 		qtyObj.value = preQtyObj.value;
 		
 		//calculate tariff refund amt
 		calTariffRefundCost(index,isPack,remQty,rate,baseVal,dis,disUnit,serTax,unitid);
 	}
 	else
 	{
 		chkHiddObj.value = "0";
 		qtyObj.readOnly = true;
 		qtyObj.value = "0";
 		refCostObj.innerHTML = "0";
 		refCostHiddObj.value = "0";
 		
 		//calculate net cost
 		calTotalRefundAmt();
 	}
 }
 
 //calculate a tariff's refund cost
 function calTariffRefundCost(index,isPack,remQty,rate,baseVal,disUnit,disType,serTax,unitid)
 {
 	var penelty = 0;
 	var qty = 0;
 	var unitVal;
 	var tempQty = 0.0;
 	var tempVal = "";
 	
 	var chkVal = document.getElementById("chk"+index);	//checkbox
 	var unitObj = document.getElementById("unit"+index);
 	var refundTypeObj = document.getElementById("refundType"+index);
 	var penObj = document.getElementById("trf_penalty"+index);
 	var penHiddObj = document.getElementById("penalty"+index);
 	var qtyObj = document.getElementById("qty"+index);	//refund qty
 	var refCostObj = document.getElementById("refCost"+index);
 	var refCostHiddObj = document.getElementById("refundAmt"+index);
 	
 	//alert("disUnit"+disUnit);
 	//alert("disType"+disType);
 	
 	if(chkVal.checked == true)
 	{
 		if(parseInt(refundTypeObj.value) == 2)
 			penObj.value = penHiddObj.value;
 		else
 			penObj.value = "0";
 		
 		penelty = penObj.value;
 		qty = qtyObj.value;
 		unitVal = unitObj.value;
 		
 		if(unitVal != "0")
 		{
 			tempVal = unitVal.split("^");
 			tempQty = parseFloat(qty) * tempVal[1];
 		}
 			 
 		if(tempQty > parseFloat(remQty))
 		{
 			alert("Refund Qty can not be greater than Remained qty !!");
 			qtyObj.value = "0";
 			qty = 0;
 		}
 		
 		var totAmt = calTrfNetAmount(rate,baseVal,disUnit,disType,qty,unitVal,serTax,penelty,1);
 		
 		refCostObj.innerHTML = totAmt.oNetTrfAmt;
 		refCostHiddObj.value = totAmt.oNetTrfAmt;
 	}
 	else
 	{
 		unitObj.selectedIndex = 0;
 		refundTypeObj.selectedIndex = 0;
 	}
 	
 	//calculate net cost
 	calTotalRefundAmt();
 }
 
 //calculate net refund amount
 function calTotalRefundAmt()
 {
 	var netCostHiddObj = document.getElementById("trf_grossRefund");  //hidden
 	var netCostObj = document.getElementById("netRefCost"); //div
 	
 	var netCost = calAllTariffNetCost("refundAmt");
 	
 	netCostHiddObj.value = netCost;
 	netCostObj.innerHTML = netCost;
 	
 }
 
 /**
 * function called on save to check refund qty- it should not be greater than unprocessed qty
 * chkRefundQty
 * @param {type} param 
 */
 function chkRefundQty(index) 
 {
        
 	//alert("chkRefundQty");
 	var chkArr = document.getElementsByName("chk");
 	var isPack = document.getElementsByName("isPack");
 	
 	for(i=0;i<chkArr.length;i++)
 	{
 	
 		if(chkArr[i].checked==true)
 		{
 			var chkVal =  chkArr[i].value;
 			var temp = chkVal.split('^');
 			var unProcessedQty = temp[1];
 			var refundQty = document.getElementById("qty"+i).value;
 			var unitcmb = document.getElementById("unit"+i);
 			var selectedUnit = unitcmb[unitcmb.selectedIndex].value;
 			var temp1 = selectedUnit.split('^');
 			var unit = temp1[1];
 			var rQty  = parseInt(refundQty);
 			var refQty = rQty * unit ;
 			if(refQty > unProcessedQty )
 			{
 			   			  
 			  	alert("Refund Quantity cannot be greater than Unprocessed Qty");
 			  	document.getElementById("qty"+index).value = document.getElementById("HidRefundQty"+index).value;
 			   	return false;
 			}
 			else
 			{
 			 	 return true;
 			} 	 
 		}
 		
 	}
 	
 }
 
 
 /**
  * totalRefAmt
  * @param {type} param 
  */
  function totalRefAmt(index,isPack,rate,baseVal,dis,disUnit,serTax) {
  var flag =  chkRefundQty();
 
 if(flag)
 {
 	var qty = document.getElementById("qty"+index).value;
 	var unit = document.getElementById("unit"+index).value;
 	var penalty = document.getElementById("trf_penalty"+index).value;
 	var refAmt = calTrfNetAmount(rate,baseVal,dis,disUnit,qty,unit,serTax,penalty,1);
 	var chk = document.getElementsByName("chk");
 	
 	  if(chk[index].checked==true){
 		
 		document.getElementById("qty"+index).readOnly=false;
 		document.getElementById("unit"+index).disabled=false;
 		document.getElementById("refundType"+index).disabled=false;
 		document.getElementById("refundAmt"+index).value= refAmt.oNetTrfAmt;
 		
 		var grossAmt =calAllTariffNetCost('refundAmt');
  		document.forms[0].trf_grossRefund.value = grossAmt;
  		
  		var netRef = document.getElementById("netRefCost");
  		netRef.innerHTML=grossAmt;
  		
  		}
 		else{
 		
 			document.getElementById("trf_penalty"+index).value=0;
 			document.getElementById("qty"+index).value=0;
 			document.getElementById("qty"+index).readOnly=true;
 			document.getElementById("unit"+index).disabled=true;
 			document.getElementById("refundType"+index).disabled=true;
 			var netAmt = parseFloat(document.forms[0].trf_grossRefund.value);
 			var refMinus =  parseFloat(document.getElementById("refundAmt"+index).value);
 			document.getElementById("refundAmt"+index).value ='0';
 			
 			var sub = manipulateValue(netAmt,refMinus,1);
 			
 			document.forms[0].trf_grossRefund.value=roundValue(sub,2);
 			
 			var netRef = document.getElementById("netRefCost");
   			netRef.innerHTML=roundValue(sub,2);
 		}
 		
 	}else
 			return false;
 }
 
 
 /**
  * function called on change of refund type combo to set the value of penalty
  * pkg_penaltyCalc
  * @param {type} obj,index 
  */
  /*
  function pkg_penaltyCalc(obj,index,isPack,unitid,rate,baseVal,dis,disUnit,servTax) {
  	
   		if(obj.value == 1)
  		{
  		document.getElementById("trf_penalty"+index).value = 0;
  		}
  		if(obj.value == 2)
  		{
  			
  			var penaltyObj = document.getElementById("trf_penalty"+index);
  			
  			
  			
  			
  			
  		}
  		
  		
  		
  	//trfChk(index,isPack,unitid,rate,baseVal,dis,disUnit,servTax);
  }
  */
 
 /**
  * popUp
  * @param {type} param 
  */
  function popUp(obj,index,id) {
 	 	
 	 var j;
     var mode="PopUp";
     var url="RefundApprovalTransCNT.cnt?hmode="+mode+"&modName="+obj.value;
     ajaxFunction(url,"4");
 } 
 
 
  var pWindow ="";
function myFunc(obj,index,id)
{
 pWindow = obj;
 if(id=="1")
 {
   if(isNaN(document.forms[0].lnkVal.length))
     var linkVal=document.forms[0].lnkVal.value;
   else
     var linkVal="1^"+document.forms[0].lnkVal[index].value;
 }
 else
 {
   var myArray=new Array(),myDivIndx=new Array(),s=0,divIndx,linkVal;
   divIndx=parseInt(id)-2;
   if(!isNaN(document.forms[0].ct.length))
   {
     for(var i=0;i<document.forms[0].ct.length;i++)
     {
      myDivIndx=(document.forms[0].ct[i].value).split("^");
      if(myDivIndx[0]==divIndx && s==0)
      {
       index=index+i;
       s=1;
      }
     }
   }  
  var c_obj=document.getElementsByName("c");
  var refundVal_obj=document.getElementsByName("refundVal");
  myArray=(refundVal_obj[index].value).split("^");
  linkVal="2^"+myArray[4]+"^"+c_obj[divIndx-1].value;
 }
 document.forms[0].popId.value="1";
 var mode = "PopUp"; 
 var objVal1 = document.getElementById("menu1");
 var url="RefundApprovalTransCNT.cnt?hmode="+mode+"&modName="+linkVal;
 ajaxFunction(url,"2"); 
}
 
 	/**
 	 * checkForError
 	 * @param {}  
 	 */
 	 function checkForError() {
 	 	
 	 		if(document.getElementById("errMsg").innerHTML.length > 0){
  			
  			alert(document.getElementById("errMsg").innerHTML);
  			return false;
  		}else{
  			
  			showPatientListingWindow('3',document.forms[0].strCrNo,'setSelectedCrNo');
  			
  		}
 	 	
 	 }
 
 
 /**
  * validate1
  * called on save button to validate mandatory fields
  */
  function validate1() {
 	
  		//alert("Inside Validate1()");
	  var paymentmodval='';
	  var radios = document.getElementsByName('bServ_id');
      for (var i = 0; i < radios.length; i++) {
          if (radios[i].type === 'radio' && radios[i].checked) {
         	 paymentmodval=document.getElementById("PaymentModeflg"+i).value;
              
          }
      }
	  if(paymentmodval == '4' || paymentmodval=='5')
		  {
	  var chkObj = document.getElementsByName("chk");
	  if(chkObj.length > 0){
		 for(var i=0; i<chkObj.length; i++) {
		  	if(chkObj[i].checked){
		  		var penlatyamt= document.getElementById("trf_penalty"+i).value;
		  		if(penlatyamt>0)
		  			{
		  				alert('Penalty Amount Can\'nt Be Greater Than Zero');
		  				return false;
		  			}
		  	}
	  	}
	  }
	}
	 
	  
	  
  		var count1 = parseInt("0");
  		var radioObj = document.getElementsByName("bServ_id");
  		
  		if(radioObj.length > 0){
  			    			  
  			  for(var i=0; i<radioObj.length; i++) {
  			  	 			  	
  			  	if(radioObj[i].checked){
  			  		
  			  		count1 = count1 + 1;
  			  	}
  			  }
  			  		
  			if(count1 <= 0){
  				
  				alert("Please Select a Bill Detail");
  				return false;
  			}
  			  		
  			  			
  		}else{
  			
  			alert("No Bill Detail(s) Available");
  			return false;
  			
  		}
  		 
  		 
  	var count2 = parseInt("0");
  		var chkObj = document.getElementsByName("chk");
  		
  		
  		//alert("length of Check Box"+chkObj.length);
  		
  		
  		//var temp = chkValue.replace('^','#').split("#");
  		
  		//var remQty=temp[1];
  		
  		//alert("Remaining Qty ="+remQty)
  		
  		if(chkObj.length > 0){
  			    			  
  			  for(var i=0; i<chkObj.length; i++) {
  			  	//alert("Check box ("+i+") checked "+chkObj[i].checked); 			  	
  			  	if(chkObj[i].checked){
  			  		
  			  		var arrChkValue=chkObj[i].value;
  			  		var temp = arrChkValue.split("^");
  			  		//alert("temp[1] value= "+temp[1]);
  			  		var remQty=temp[1];
  			  		//alert("Remaining Qty ="+remQty)
  			  		var refundQty = document.getElementById("qty"+i).value;
  			  		//alert("Refund Quantity ("+i+") = "+refundQty);
  			  		if(refundQty=="")
  			  		{
	  			  		alert("Enter the Refund Qty!!!");
	  			  		document.getElementById("qty"+i).focus();
	  			  		return false;
  			  		} 
  			  		if(parseInt(refundQty)<=0)
  			  		{
  			  		alert("Refund Qty Should be greater than Zero!!!");
  			  		document.getElementById("qty"+i).focus();
  			  		return false;
  			  		}
  			  		if(parseInt(refundQty)>remQty)
  			  		{
  			  		alert("Refund Qty Should be Less than than Remaining Quantity!!!");
  			  		document.getElementById("qty"+i).focus();
  			  		return false;
  			  		}
  			  		var unit = document.getElementById("unit"+i).value;
  			  		if(unit==0)
  			  		{
  			  		alert("Please select a unit!!");
  			  		document.getElementById("unit"+i).focus();
  			  		return false;
  			  		}
  			  		
  			  		count2 = count2 + 1;
  			  	}
  			  }
  			  		
  			if(count2 <= 0){
  				
  				alert("Please Select atleast One Tariff(s) Detail");
  				return false;
  			}
  			  		  			
  		}else{
  			
  			alert("No Tariff Detail(s) Available");
  			return false;
  			
  		}
  		
  		
  		var trfAmt=document.forms[0].trf_grossRefund.value;
  		var peneltyAmt=document.forms[0].strProcessFeePenalty.value
  		//alert(document.forms[0].trf_grossRefund.value);
  		//alert(document.forms[0].strProcessFeePenalty.value);
  		if((parseInt(trfAmt)*-1) < parseInt(peneltyAmt))
  			{
  			alert(" Processing Fee Penalty Amount Must Be Less Than Net Refund Amount");
  			return false;
  			}
  
   	var hisValidator = new HISValidator("refundApprovalTransBean"); 
   	
   	hisValidator.addValidation("strRefBy","dontselect=0","Please select a value from Refund By Combo");
  	hisValidator.addValidation("strOtherReason", "req", "Refund Reason is a Mandatory Field" );
   	
  	var retVal = hisValidator.validate(); 
  	
  	if(retVal){
  		
  		$( function() {
		    $( "#dialog-message" ).dialog({
		      modal: true,
		      position: 'top',
		      dialogClass: 'no-close',
		      buttons: {
		        Ok: function() {
		          $( this ).dialog( "close" );
		          addData();
		        },
		        Close: function() {
		          $( this ).dialog( "close" );
		          return false;
		        }
		      }
		    });
		  } );
  		
  			/*var conf = confirm("Are you sure to refund for selected tariff(s)");
				if(conf == true){
					
					addData();
					
				}else{
					
					return false;
				}*/
  		  	   		
  		}else{
  			return false;
  		}
  }
  	 		
/**
  * insertData
  * called on save button to insert data into database
  * @param {type}  
  */
  function addData() {
  
  	document.forms[0].hmode.value = "ADDDATA";
  	document.forms[0].submit();
  }  	
  
/**CALLED ON CANCEL BUTTON
 * initPage()
 * @param {type}  
 */
 function initPage() 
 {
 	document.forms[0].hmode.value = "CANCEL";
  	document.forms[0].submit();
 	
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
 	 //document.getElementById("printableSlip").style.display="none"; 
 }

 function hidePrintableSlip()
 {
 	//alert("hide");
 	document.getElementById("printableSlip").style.display="none"; 
 }
 function printLastBill()
 {
 	//alert("show");
 	//document.getElementById("printableSlip").style.display="";
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
 	document.getElementById("printableSlip").style.display=""; 
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