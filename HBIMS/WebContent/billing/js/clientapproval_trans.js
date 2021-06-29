
/**
 * showFirstPage()
 * function called on clear button to clear all the fields    
 */ 
 function ClearPage()
 {
 	 document.forms[0].strCrNoStatus.value = "";
 	// document.forms[0].strCrNo2.value = "";
 	document.forms[0].strCrNo.disabled = false;  
 	document.forms[0].strCrNo.value = "";
 	document.forms[0].strCrNo2.value = "";
 	document.forms[0].strClientName.value = "0";
	document.getElementById("approvalid").style.display="none";
	document.getElementById("cltdtlid").style.display="none";
    document.getElementById("strRegistrationNo").innerHTML = "";
    document.getElementById("strClientType").innerHTML = "";
    document.getElementById("strClientAddress").innerHTML = "";
    document.getElementById("strContactPerson").innerHTML = "";
    document.getElementById("strEmailId").innerHTML = "";
    document.getElementById("strContactNo").innerHTML = "";
    document.getElementById("errMsg").innerHTML = "";
    document.forms[0].strCardNumber.value = "";
    document.forms[0].strCardHolderName.value = "";
    document.forms[0].strSanctionAmount.value = "";
  //  document.forms[0].strApprovalType.value = 1;
    document.forms[0].strCrNo.focus(); 
   
 }	
 




var comValue = "1";

// called on APPROVAL
function WithoutCheckAnyOne(form1,mode,display)
 {
  add("APPROVAL"); 
}

/* global Function for all Transaction Screens Starts  */
// called on RE-APPROVAL,CLOSE & VIEW
 function checkAnyOne(form1,mode,display)
 {

	var cmbVal = "";
	var checkCount=0;
	with(form1)
	{  
	 	    var check=document.getElementsByName("chk");
	        for(i=0;i<check.length;i++)
	        {
		     if(check[i].checked==true)
		     {
			    checkCount++;
			 }			
	        }
	        if(checkCount>0)
	        {
	          if(checkCount>1)
	           {
	             alert("Please Check Only One record at a time!!!!");
	           }
	           else
	           {
	           if(mode=="CLOSE")
	           {
	           	var ret = confirm("Do You want to close the record");
	           	if(ret)
	           	add(mode);
	           	else
	           	return false;
	           	
	           }
	           else{
	             add(mode);
	             }
	           }   
	        }
	        else
	        {
	         alert("Please Check At Least One record!!!!");
	        }  
			
	}
	}

	/*function checkAccessible(mode){
	
		if(comValue == "1" && mode == "REFUND")
		{
			
			alert("Cannot Refund in Approval Status");
			
			return false;
			
		}
		else
		 if(comValue == "2" && mode == "APPROVAL")
		 {
			
			alert("Cannot Approve in Status To be Refund");
			
			return false;
			
		}
		else 
		if(comValue == "2" && mode == "REAPPROVAL"){
			
			alert("Cannot Re-Approve in Status To be Refund");
			
			return false;
			
		}
		else 
		if(comValue == "2" && mode == "CLOSE")
		{
			
			alert("Cannot Close Record in Status To be Refund");
			
			return false;
			
		}
		else
		{
		
				return true;
		}
	
	
	}*/
	/***************Function Use to Find(Check box is Check or Not)********************/
/*	function callMe(form1,mode,display)
    {
	   var cmbVal = "";
    	
	   with(form1)
	   {  
	 	if((document.getElementById("comboid0").value == "0"))
		{
			alert("Please Select All Combo!!!");
			return;
		}
		else
		{
	        ClientApprovalcheckColor(mode,display);
		}	
	  }
	
	}
	*/
	////////////////////////// CHK COLOR////////////////////////////////////
	/*function ClientApprovalcheckColor(mode,display)
	{
	   	        if( mode == "APPROVAL" && document.getElementById("approvalBtId").style.color == "blue")
				{
				 add(mode);
			    }
			    
			    if( mode == "REAPPROVAL" && document.getElementById("reapprovalBtId").style.color == "blue")
				{
				 add(mode);
			    }
			    if( mode == "CLOSE" && document.getElementById("closeBtId").style.color == "blue")
				{
				  var resVal  = document.forms[0].chk.value.split('@');
				  var resVal3 = resVal[2].split('$');
				  var conf = confirm("Approval for Cr No" +resVal3[0]+ "is being Closed \n Are you sure");
                  if(conf == true)
                   {
                       var conf1 = confirm("Are you sure !!!");
                       if(conf1 == true)
                       {
                         add(mode);
                       }
                      else
                       {
                         return false;
                       }
                   }
                  else
                   {
                         return false;
                   }
  	    		 }			    			
			   			
		       if(mode == "UPDATEACCOUNTSTATUS" && document.getElementById("viewBtId").style.color == "blue")
				{				
				  add(mode);
			    }
			    
	}
	
	
	*/
    /*******************OnClickFunction*******************/
   /* function testFunction(obj)
    {  
     var totalChk = 0;
	// alert("inside Client test function....!" + obj.value);
	// alert("Chk is Checked or Not...!"+document.forms[0].chk.checked);
	// alert("Combo selected...!"+document.forms[0].combo.value);
	 comValue = document.forms[0].combo.value ;
	 if(document.forms[0].chk.checked == true && comValue == 1)
	 { 
        document.getElementById("approvalBtId").style.color = "blue";
		document.getElementById("reapprovalBtId").style.color = "blue";
		document.getElementById("closeBtId").style.color = "blue";
		document.getElementById("viewBtId").style.color = "blue";
	 }
    } 
*/

/*	function changeRecordStatus1(obj)
	{
		comValue = obj.value;
		
        if(comValue == 1)
		{
		document.getElementById("approvalBtId").style.color = "blue";
		document.getElementById("reapprovalBtId").style.color = "blue";
		document.getElementById("closeBtId").style.color = "blue";
		document.getElementById("viewBtId").style.color = "blue";
	
		}
        
		if(comValue == 2)
		{
		document.getElementById("approvalBtId").style.color = "red";
		document.getElementById("reapprovalBtId").style.color = "red";
		document.getElementById("closeBtId").style.color = "red";
		document.getElementById("viewBtId").style.color = "blue";
	
		}
	}*/

 function changeRecordStatus(obj)
 {
	if(document.getElementById("comboid0").value == '1')
	{ 
	            enableButton("Approval");
				enableButton("Re-Approval");
				enableButton("Close");
				enableButton("View");
	}
   if(document.getElementById("comboid0").value == '2')
    {
               disableButton("Approval");
			   disableButton("Re-Approval");
			   disableButton("Close");
			   enableButton("View");
			   
			    
    }
 }

/*
function myFunc(mode)
{

		if(mode == '1')
		{
			var hmode = "TARIFFLIST"; 
			var url = "ClientApprovalDetailsTransCNT.cnt?hmode="+hmode+"&gName="+document.getElementById("strGroupName"+genIndex).value;
			ajaxFunction(url,"1");
	
		}
		else 
		  if(mode == '2')
		  {
			var hmode = "TARIFFDTLS"; 
			var url = "ClientApprovalDetailsTransCNT.cnt?hmode="+hmode+"&gName="+document.getElementById("strGroupName"+genIndex).value+"&tariffDetails="+document.getElementById("strTariffDetails"+genIndex).value;
			ajaxFunction(url,"2");
	      }
	      else 
	      if(mode == '3')
	      {
			var hmode = "PAYMENTMODE"; 
			var url = "ClientApprovalDetailsTransCNT.cnt?hmode="+hmode+"&payMode="+document.forms[0].strPaymentMode.value;
			ajaxFunction(url,"3");
	      }
	
	}
	*/
    function getAjaxResponse(res,mode)
    {
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
   
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 
       
	
		if(mode == '1')
		{
			var objEle = document.getElementById("tariffOptionsId");
		    objEle.innerHTML = "<select name='strTariffName' class='comboNormal' id='strTariffName#delIndex#'>"+res+"</select>";
		
		}
		else
		if(mode == '2')
		{
		//    alert("Response in--->"+resVal);
		//	var resVal = res.split('^');
		//	var objEle = document.getElementById("id2");
		//	objEle.innerHTML = resVal[0];
		//	document.multirow.rowIndex2.value  = resVal[1];
		//	document.multirow.rowLength2.value = resVal[1];
				
		}
		else 
		if(mode == '3')
		{
			alert(res);
			document.getElementById("paymentModeId").innerHTML = res;
			
		}
		else if(mode == '5')
		{
		    
			var resVal = res.split('^');
			// alert("resVal :"+resVal[6]);
			if(res != 0)
            {
            // alert("Clt No-->"+document.forms[0].strClientName.value);
             document.forms[0].strCltNo.value=document.forms[0].strClientName.value; 
             document.getElementById("strRegistrationNo").innerHTML = resVal[1];
             document.getElementById("strClientType").innerHTML = resVal[2];
             document.getElementById("strClientAddress").innerHTML = resVal[0];
             document.getElementById("strContactPerson").innerHTML = resVal[3];
             document.getElementById("strEmailId").innerHTML = resVal[5];
             document.getElementById("strContactNo").innerHTML = resVal[4];
             document.getElementById("cltdtlid").style.display="block";
            
             
            }
            else
            {
             document.getElementById("cltdtlid").style.display="none";
             document.getElementById("strRegistrationNo").innerHTML = "";
             document.getElementById("strClientType").innerHTML = "";
            document.getElementById("strClientAddress").innerHTML = "";
             document.getElementById("strContactPerson").innerHTML = "";
             document.getElementById("strEmailId").innerHTML = "";
             document.getElementById("strContactNo").innerHTML = "";
             
            }
	 }
		
  }
  /*
  function getTariffDtls()
  {
    		var mode = "TARIFFDTLS"; 
    		alert("Value is-->"+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value);
			var url = "ClientApprovalDetailsTransCNT.cnt?hmode="+document.forms[0].strOffLineGroup[document.forms[0].strOffLineGroup.selectedIndex].value;
			ajaxFunction(url,"2");
  }
	*/
  /**
	 * removeTariffRow
	 * @param {Object} index 
	 */
	/* function removeTariffRow(index) {
	 		 	
	 	deleteRow(index,'1','0');
	 	
	 }*/
	// called on change of Client Name to get client details
  function getClientDtl(obj)
  {
  // alert("Inside getCltDtl Clt No"+document.forms[0].strClientName.value);
   var mode="UNITVAL22";
   var url="ClientApprovalDetailsTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strClientName.value;
   ajaxFunction(url,"5");
 }	 
  
 function getClientAddress(obj)
 {
  var res = document.forms[0].strClientName.value;
  getClientDtl(document.forms[0].strClientName.value);
  //alert("res is->"+res);
  var resVal = res.split('^');
  if(res != 0)
  {
    document.getElementById("strRegistrationNo").innerHTML = resVal[3];
    document.getElementById("strClientType").innerHTML = resVal[2];
    document.forms[0].strClientAddress.value = resVal[1];
    document.getElementById("strContactPerson").innerHTML = resVal[4];
    document.getElementById("strEmailId").innerHTML = resVal[6];
    document.getElementById("strContactNo").innerHTML = resVal[5];
    document.getElementById("cltdtlid").style.display="block";
  }
  else
  {
    document.getElementById("cltdtlid").style.display="none";
    document.getElementById("strRegistrationNo").innerHTML = "";
    document.getElementById("strClientType").innerHTML = "";
    document.forms[0].strClientAddress.value = "";
    document.getElementById("strContactPerson").innerHTML = "";
    document.getElementById("strEmailId").innerHTML = "";
    document.getElementById("strContactNo").innerHTML = "";
   
  }
 }
 

 function getApprovalFor(obj)
 {
  var mode="UNITVAL2";
  var url="ClientApprovalDetailsTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strHospitalServiceCombo.value;
  ajaxFunction(url,"5");
 }

/*function ftn33(obj)
 {     
 alert("function ftn33");
  if(document.forms[0].strCrNo2.value == "" || document.forms[0].strCrNo2.value == null)
  {
     alert("Please Enter CR No...!!");
  }
  else
  { 
   if(document.forms[0].button3.value != 1)
   {
    document.getElementById("detailsdivid3").style.display="block";
    document.getElementById("minus3").style.display="block";
    document.getElementById("plus3").style.display="none";
    document.forms[0].button3.value = 1;
   }
   else
   {
    document.getElementById("minus3").style.display="none";
    document.getElementById("plus3").style.display="block";
    document.getElementById("detailsdivid3").style.display="none";
    document.forms[0].button3.value = 0;
   }
  } 
 }
 */
/* global Function for all Transaction Screens Ends  */
/*
	function groupWardWiseBlock(obj)
	{
	  if(document.forms[0].strCrNo2.value == '')
	  {
		alert("Please Enter Cr No First!!!");
		document.forms[0].strApprovalType.value = 0;
		document.forms[0].strCrNo2.focus();
	  }
	  else
	  {
	    if(document.forms[0].strApprovalType.value == 1 ||document.forms[0].strApprovalType.value == 2)
		{
			document.getElementById("Show").style.display="block";
			document.forms[0].strSanctionAmount.disabled = true;
		//	document.forms[0].strOfflineTariffQty.disabled = true;
		}
		else
		{ 
			document.getElementById("Show").style.display="none";
			document.forms[0].strSanctionAmount.disabled = false;
			document.forms[0].strOfflineTariffQty.disabled = false;
		}
	  }	
	}
	*/
	/*
	function getCloseApproval()
	{
	// alert("OnLoad IPD Type::"+document.forms[0].IPD.value);
	  if(document.forms[0].IPD.value == 0)
		{
			document.getElementById("closeapproval").style.display="block";
		}
		else
		{ 
			document.getElementById("closeapproval").style.display="none";
		}
	}

*/
	function onLoadLogicsReapproval()
	{
	 //alert("onload onLoadLogicsReapproval");
	 if(document.forms[0].OPD.value=="1")
	 {
		document.forms[0].strOPD.checked=true;
		document.forms[0].strOPD.disabled='disabled';  
	 }
	  if(document.forms[0].IPD.value=="1")
	 {
		document.forms[0].strIPD.checked=true;
		document.forms[0].strIPD.disabled='disabled'; 
	 }
	  if(document.forms[0].EME.value=="1")
	 {
		document.forms[0].strEME.checked=true; 
		document.forms[0].strEME.disabled='disabled';    
	 }
	
	
	
	}
	 
	function onLoadLogics()
	{
	document.forms[0].strCrNo.focus();  
	//alert("onload ");
	// alert("document.forms[0].strCrNoStatus.value-"+document.forms[0].strCrNoStatus.value); 
	 
	 if(document.forms[0].strCrNoStatus.value == '0') 
	 {
		 document.getElementById("divFromClientDetails").style.display="none"; 
		 document.getElementById("buttons1").style.display="none"; 
		  document.getElementById("buttons2").style.display="block";
		  
		   document.getElementById("detailsdivid3").style.display="none";   
		  
		   document.getElementById("patDtl").style.display="none"; 
		   //alert("check1");
		  document.forms[0].strCrNo.value = "";
		 document.forms[0].strCrNo.disabled = false; 
		  //   alert("check2");
		  //   document.getElementById("approvalid").style.display="none";  
		 
	 }
	 
	if( document.forms[0].strCrNo.value !="" )
	{
	 document.forms[0].strCrNo.disabled=true;   
	}
	document.forms[0].OPD.checked=false;
	document.forms[0].IPD.checked=false;
	document.forms[0].EME.checked=false;
	document.forms[0].strOneTimeService.checked=false; 
	
	
	var statusObj =  document.getElementById("strPatientStatus");
	
	if(statusObj != null) 
	{
	// alert("statusObj not null "+statusObj);
	 if(document.forms[0].strPatientStatus.value=='13' || document.forms[0].strPatientStatus.value==' ')
	 {
		 document.getElementById("divFromClientDetails").style.display="none"; 
		 var errObj = document.getElementById("errMsg");
	  	errObj.innerHTML = "Patient Is Dead";
	 }
	
	  if(document.forms[0].strApprovalStatus.value !='0' ) 
	 {
		document.getElementById("divFromClientDetails").style.display="none"; 
		document.getElementById("buttons1").style.display="none"; 
		document.getElementById("buttons2").style.display="block";  
		document.forms[0].strCrNo.value = " ";
		 document.forms[0].strCrNo.disabled = false;
	 }
	 
	 }
	 if(document.forms[0].strMsgType.value != 1)
	 {
	  
	   if(document.forms[0].strCrNo2.value == '')
	   {
		 document.getElementById("approvalid").style.display="none";
	   }
	   else
	   {
		   document.getElementById("approvalid").style.display="block";
		 
		  if(statusObj != null) 
		{
          if(document.forms[0].strPatientStatus.value!='13')
          {
       		  if(document.forms[0].strApprovalStatus.value == 1)
         	 {
         	   document.getElementById("divFromClientDetails").style.display="none"; 
         	   document.forms[0].strCrNo.value = " ";
		 	document.forms[0].strCrNo.disabled = false;
         	 }  
        	  else
         	 {
          	 document.getElementById("divFromClientDetails").style.display="block"; 
        	  }
          }
        }
	   }
	 }
	
	
	 
    }
    
	function goFuncClientApproval()
	{
	// alert("goFuncClientApproval 1");
	    var hisValidator = new HISValidator("clientApprovalDetailTransBean");  
	     document.forms[0].strCrNo2.value =   document.forms[0].strCrNo.value; 
	    hisValidator.addValidation("strCrNo","req", "CR No. is a Mandatory Field" );
	   hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	    
	    var retVal = hisValidator.validate(); 
	   //  alert("goFuncClientApproval 2 retVal-"+retVal); 
	    if(retVal)
		{
		// document.forms[0].strCrNo.disabled=true;  
		  document.getElementById("Show").style.display="none";
		  document.forms[0].hmode.value="GO";
	      document.forms[0].submit();
		}
		else
		{
		 document.forms[0].strCrNo.focus(); 
		 document.getElementById("detailsdivid3").style.display="none";
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
												
				goFuncClientApproval();
				
				return false;
			}	   	
	  }else{
	   		return false;
	   }
	   }
	 
	
	function ClientApprovalSave()
{  
	//alert("save");
      
	       
	    var hisValidator = new HISValidator("clientApprovalDetailTransBean");  
	    hisValidator.addValidation("strCrNo","req", "CR No. is a Mandatory Field" );
	    hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	        
	    if(document.getElementById("divFromClientDetails").style.display == 'block'){
	    
	      hisValidator.addValidation("strClientName","dontselect=0", "Please Select Client Name " );
	    hisValidator.addValidation("strCardNumber","req", "Card No. is a Mandatory Field" );
	    hisValidator.addValidation("strCardHolderName","req", "Card Holder Name is a Mandatory Field" );
	    
	    if(document.forms[0].strCardExpiryDate.value.length > 1  )  
		{
 		hisValidator.addValidation("strCardExpiryDate", "dtgtet="+document.forms[0].strCtDate.value ,"Card Expiry Date Should be Greater than or Equal to Current Date"); 
		}
	    	    
	    hisValidator.addValidation("strAuthenticationNumber", "req", "Authentication No. is a Mandatory Field" );
	    
		hisValidator.addValidation("strAuthenticationDate", "req", "Authentication Date is a Mandatory Field" );  
		hisValidator.addValidation("strAuthenticationDate", "dtltet="+document.forms[0].strCtDate.value ,"Authentication Date Should be less than or Equal to Current Date");
	
		if(document.forms[0].strExpiryDate.value.length > 1  )  
		{
 		hisValidator.addValidation("strExpiryDate", "dtgtet="+document.forms[0].strCtDate.value ,"Expiry Date Should be Greater than or Equal to Current Date"); 
		}
	   hisValidator.addValidation("strSanctionAmount", "req", "Sanction Amt is a Mandatory Field" );
	  
	    hisValidator.addValidation("strSanctionAmount", "amount=9,2", "Sanction Amt should be in format 0000000.00" );
	    
	    }else{
	    
	    	alert("Please Click the Go Button to get Client Details");
	    	return false;
	    }
	    
	     
	    var retVal = hisValidator.validate();
		 hisValidator.clearAllValidations();
		 if(retVal) 
		 {
		  if( parseInt(document.forms[0].strSanctionAmount.value)  == "0" )  
		{
			alert("Sanction Amount cannot be 0.00");
			retVal = false;
			
		}
		  if(document.forms[0].OPD.checked==false && document.forms[0].IPD.checked==false && document.forms[0].EME.checked==false)
			{
			alert("Please Check at least One Service (OPD, IPD ,EMERGENCY)"); 
			retVal=false ;
		
			}
			}
		 if(retVal)
		 {
		 	
		 	 document.forms[0].strCrNo.disabled = false;
		 	
		  document.forms[0].hmode.value="APPROVALINSERT";
	      document.forms[0].submit();
		 }
		 else
		 {
		  return false;
		 }
	
	  
	}
	
	
	
	/*

	var genIndex = 0;

	function showTariffPopup(obj,indexVal)
	{
		genIndex = indexVal;
		
		if(document.getElementById("strGroupName"+genIndex).value != 0)
		{
			myFunc('1');
			if(document.getElementById("strSancAmt"+genIndex).value == "")
			{
	
				document.multirow.rowIndex2.value = 0;
				document.multirow.rowLength2.value= 0;
			
				document.getElementById("id2").innerHTML="";
				display_popup_menu(obj,'menu1','450','');
			}
			else
			{
				myFunc('2');
				display_popup_menu(obj,'menu1','450','');
			}
			
		
		
		}else{
			
			alert("Please Select A Group Name");
			
			document.getElementById("strGroupName"+genIndex).focus();
			return false;
		}
	}
	
	function cancelTariffPopup(){

			document.multirow.rowIndex2.value = 0;
			document.multirow.rowLength2.value= 0;
			
		//	changeButtonColor();
			
			document.getElementById("id2").innerHTML="";		
			
		hide_popup_menu('menu1');
	}
	
	function changeButtonColor()
	{
	  if(document.getElementById("strSancAmt"+genIndex).value != "")
	  {
		document.getElementById("strTariffWise"+genIndex).style.background="red";
		//document.multirow..style.background="#FF0000";
	  }
	  else
	  {
		document.getElementById("strTariffWise"+genIndex).style.background="none";
	  }
	}
	
	function saveTariffPopupValues(){
		
		var tarVal = document.getElementsByName("strTariffName");
		var sancAmtVal = document.getElementsByName("strTariffSancAmt");
		
		var tariffDetails = "";
		var amtTotal = parseInt("0");
		
		for(i = 0 ; i < tarVal.length-1; i++){
			
				if(i == 0){
			tariffDetails = tarVal[i].value+"|"+sancAmtVal[i].value;
			amtTotal = amtTotal+parseInt(sancAmtVal[i].value);
			}else{
				tariffDetails = tariffDetails+"^"+tarVal[i].value+"|"+sancAmtVal[i].value;
				amtTotal = amtTotal+parseInt(sancAmtVal[i].value);
			}
		}
		
		
		
		 document.getElementById("strTariffDetails"+genIndex).value = tariffDetails;
		 
		 if(amtTotal != 0){
		 document.getElementById("strSancAmt"+genIndex).value = amtTotal;
		 }else{
		 document.getElementById("strSancAmt"+genIndex).value = "";
		 }
					
		var tAmount = parseInt("0");
			
		var sancElement =  document.getElementsByName("strSancAmt");
			
			for(i = 0; i < sancElement.length - 1; i++){
				
				tAmount = parseInt(tAmount) + parseInt(sancElement[i].value);
					
			}
			
			
			if(tAmount != 0){
			document.forms[0].strSanctionAmount.value = tAmount;
			}else{
				document.forms[0].strSanctionAmount.value = "";
			}
		//}
		
		cancelTariffPopup();
	}
	
	function showAddress(objVal){
		
		if(objVal.value != 0){
			
			var addressVal = objVal.value.split('^');
			document.getElementById("addressId").innerHTML= addressVal[1];
			
		}else{
			document.getElementById("addressId").innerHTML="";
		}
	}
	
	
	
	
	function getPaymentModeDtls(payModeObj){
			
		myFunc('3');	
	}
	*/
	
	function setApprovalDetails(radioObj){
		
		var radioVal = radioObj.value;
		
		var temp = radioVal.split("^");
		
		document.forms[0].strSanctionAmount.value = temp[1];
		document.forms[0].strAmountReceivedFromClient.value = temp[2];
		document.forms[0].strAmountReceivedFromPatient.value = temp[3];
		document.forms[0].strClientExpenseAmount.value = temp[4];
		document.forms[0].strRefundAmount.value = temp[5];	
			
	}
	
	
	
	
	