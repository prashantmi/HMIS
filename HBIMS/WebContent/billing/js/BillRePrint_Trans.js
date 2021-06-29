// Global Variables
	
			var child = null;
			var popIndex = 0;
			var gblCntrlObj = null;

/**
	   * initGoBillFunc
	   * @param {Event} eve 
	   */
	   function initGoFunc(eve){
	   	
	   	 var flag=validateData(eve,5);
  		 if(flag){
	   	
	   	if(eve.keyCode == 13){
												
				getSearchBillListBySearch('strCrNo');
				
				return false;
			}	   	
			
  }else{
	   		return false;
	   }
	   }
	   
/**
 *  function called on loading of main page 
 *  So this function will be called twice
 *  1st to enter a bill no
 *  2nd after entering to display bill details of entered bill no.
 */
function showBillDetails(){


var normalMsg = document.getElementById("normalMsg").innerHTML;
		 

	if(document.forms[0].strTransNo.value!="" )
	{
		if(document.forms[0].strTransNo.value.length==10){	
		document.forms[0].strTransNo.disabled=true;
	
		}else{
			alert("Receipt No. should have 10 digits in first field");
		}
	}
	else
	{
		document.forms[0].strTransNo.focus();
	}
}



	var gblPrintLimitVal = 0;
	
	
	/**
	 * reprintContents
	 */
	 function reprintContents() 
	 {
	 	if(gblPrintLimitVal != 0)
	 	{	 			
	 			var hmode = "REPRINT"; 
				var url = "BillRePrintTransCNT.cnt?hmode="+hmode;											
				ajaxFunction3(url,"1","rePrintStatus");
		}	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 	 
	 function rePrintStatus() 
	 {
	 	gblPrintLimitVal = gblPrintLimitVal - 1;	 	
	 	var confirmFlag = confirm("Whether Receipt Printed Successfully?");
					
		if(!confirmFlag)
		{							
							reprintContents();							
		}
		else
		{								
								var billNo   = document.forms[0].strTempBillNo.value;
								var recNo    = document.forms[0].strTempReceiptNo.value;
								var printFlg = document.forms[0].strPrintFlag.value;
								var appendedVal = billNo +"^"+ recNo +"^"+printFlg;
								var hmode     = "UPDATEPRINTSTATUS";
								//alert("reprintcontent");
								//alert(printFlg);
							    var url       = "BillRePrintTransCNT.cnt?hmode="+hmode+"&modName="+appendedVal; 
								ajaxFunction(url,"26");	
								
		}	 	
	 }					
				


/**
 * focusNext
 * @param {type} param 
 * function to focus into next text-box after entering 10 digits in first box of bill no.
 */
 function focusNext(obj) {
 
  	 if(obj.value.length == '10') document.forms[0].strRcptNo.focus();
 }
 /**
 *  function called on print button on submit the page after entering bill no.
 */
function PRINT()
{
	if(document.forms[0].strTransNo.value!="" )
	{
	    if(document.forms[0].strTransNo.value.length==10)
		{
			document.forms[0].hmode.value = "PRINT";
			
 	        var hisValidator = new HISValidator("BillRePrintTransBean");
 	        if(document.getElementById("BillType").style.display == 'block')
	        {
	          hisValidator.addValidation("strBillTypeCombo","dontselect=0", "Please Select Bill Type !!!" );
	        } 
	        var retVal = hisValidator.validate(); 
	        if(retVal)
	        {
	          document.forms[0].strTransNo.disabled = false;
 	        document.forms[0].strRcptNo.disabled = false;
 	           
	           	document.forms[0].submit();
		     }
		     else
		     {
		        return false;
		     }
	     }
    }
	else
	{
		alert("Please enter a Receipt No.");
		document.forms[0].hmode.value = "init";
		document.forms[0].submit();
	}
 }
	
/**
 * function called on click of search button on main page to 
 * call BillSearchPopup method of CNT which loads the search page(or popup screen)
 */
 function showBillSearchPopup() 
 {
        var err = document.getElementById("errMsg");
        err.innerHTML = "";
       	if(document.forms[0].strTransNo.value =="" && document.forms[0].strRcptNo.value =="")
	    {
	      document.getElementById("billDtl").style.display  = "none";
     	  document.getElementById("BillType").style.display = "none";
     	  url = 'BillRePrintTransCNT.cnt?hmode=BILLSEARCHPOPUP';
     	  openPopUp(url,'700','400','1',null);
     	}
     	else
     	{
     	   alert("Plz Clear Bill No Recipt No First!!!");
       	}
	 	//window.open(url,"popupWindow","width=610,height=390,scrollbars=yes");
 }
/**
 *  function to show patient demographic details on click of +sign
 */
 function showinfo()
 {
	document.getElementById("plus").style.display="none";
	document.getElementById("minus").style.display="block";
	
 }
/**
 *  function to hide patient demographic details on click of -sign 
 */
 function hideinfo()
 {
	document.getElementById("plus").style.display="block";
	document.getElementById("minus").style.display="none";
	
 }
/**
  * clearCrNo
  * function called on clear button of popup screen
  * @param {type}  
  */
  function clearCrNo() 
  {
  	document.forms[0].strSearchString.disabled=false;
  	document.forms[0].strSearchString.value="";
  	document.forms[0].strSearchString.focus();
  	document.forms[0].strFromDate.value=document.forms[0].ctDate.value;
	document.forms[0].strToDate.value=document.forms[0].ctDate.value;
 
	document.getElementById("billdtls").style.display="none";
	
  //	showBillSearchPopup();
  }
  
  /**
   * cancelPopup
   * function called on cancel button of popup screen to close the search window
   * @param {type}  
   */
   function cancelPopup() 
   {
   	window.close();
   	window.opener.document.forms[0].strTransNo.focus();
   }
	
/**
 * clearBillNo
 * function called on clear button of main page to clear the entered bill no.
 * @param {type}  
 */
 function clearBillRePrint()
 {
 	document.forms[0].strTransNo.disabled=false;
 	document.forms[0].strTransNo.value="";
 	 	
 	document.forms[0].strRcptNo.disabled=false;
 	document.forms[0].strRcptNo.value="";
 	
 	var objVal = document.getElementById("billDtl");
    objVal.innerHTML ="";
	objVal.style.display="none";
 	document.forms[0].strTransNo.focus();
 }	
 /**
 * clearBillNoWith
 * function called on clear button of main page to clear the entered bill no.
 * @param {type}  
 */
 function clearWithOutBillRePrint()
 {
   	document.forms[0].strTransNo.disabled = false;
   	document.forms[0].strTransNo.value="";
   	   	
 	document.forms[0].strRcptNo.disabled = false;
 	document.forms[0].strRcptNo.value="";
 	
 
 	var objVal = document.getElementById("billDtl");
    objVal.innerHTML ="";
	objVal.style.display="none";
	var objVal1 = document.getElementById("withoutPrint");
	objVal1.style.display="block";
	var objVal2 = document.getElementById("withPrint");
	objVal2.style.display="none";    
	var objVal3 = document.getElementById("BillType");
	objVal3.style.display="none"; 
	    
	document.forms[0].strTransNo.focus();
 	
 }	
/**
  * fetchBillList
  * called on popup go button to show bill dtls by guarantor name(in case 2)
  * @param {String} 
  */
  function fetchBillList(fromRows,blockSet) 
  {
     	var convString = "";
  	
  	var searchString = "";
  	var searchType = "";
  	
  	 	if(document.forms[0].strSearchString.value.length > 0)
  		{ 
  		  searchString = document.forms[0].strSearchString.value ;
  	 	  searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value ; 
  	 
  		var fromDate = document.forms[0].strFromDate.value ;
  		var toDate = document.forms[0].strToDate.value ;
  		convString = searchString;
  		if(convString !="")
  		{
  		if(searchString.length > 0 && searchString.indexOf('%') != -1)
  		{
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "^" + searchArr[i];
  		}
  		 		
  	var hmode = "FETCHBILLLISTING"; 
  	
  	var url = "BillRePrintTransCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+
  	"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet+"&fromDate="+fromDate+"&toDate="+toDate+"&CASE=2";
			
		ajaxFunction3(url,"1","getBillListDetailAjaxResponse");
  		}
 }
 
  }
    
/**
   * getPatientListDetailAjaxResponse
   * @param {String} res
   * @param {String} mode  
   */
   function getBillListDetailAjaxResponse(res , mode) 
   {
   	
     	
   		if(mode == '1')
   		{
	    	temp=res.split("@");
			var objEle = document.getElementById("fetchRecordDivId");
			objEle.innerHTML = temp[0];
			
			document.getElementById("fetchRecordDivId").style.display="block";
			 			
		}
   	
   }
   
   
     	
function goRetFunc(obj)
{

	var flag=validateData(obj,5);
	
	if(flag){
			if(obj.keyCode==13)
			{
				var flag1=goFunc();
				if(flag1)
				{
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
				}
				else
				{
					return false;
				}
				
			}
		}
		else
		{
			return false;
		}
}
    
   
    
    /**
 * called on when press Go Button 
 * 
 */
    
    function goFunc()                //  for CR No. field validation
    {
        var err = document.getElementById("errMsg");
        err.innerHTML = "";
		var hisValidator = new HISValidator("BillRePrintTransBean");  
	 	hisValidator.addValidation("strTransNo", "req", "Receipt No. is a Mandatory Field !!!!" );
	    hisValidator.addValidation("strTransNo", "minlen=10", "Receipt No. must be 10 Digits !!!!" );
	    hisValidator.addValidation("strRcptNo", "req", "Receipt No. is a Mandatory Field !!!!" );
	    var retVal = hisValidator.validate(); 
	    if(retVal)
	    {
	           	getGo();
		}
		else
		{
		  return false;
		}
   }
    
  function getGo()
  { 
    var mode="GO";
    var appended  = document.forms[0].strTransNo.value +"^"+ document.forms[0].strRcptNo.value;
    var url       = "BillRePrintTransCNT.cnt?hmode="+mode+"&modName="+appended;
    document.forms[0].strRcptNo.disabled  = true;
    document.forms[0].strTransNo.disabled = true;
    ajaxFunction(url,"1");
  }
  function getAjaxResponse(res,mode)
  {
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	  {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	document.forms[0].strRcptNo.disabled  = false;
                    document.forms[0].strTransNo.disabled = false;
		          	document.forms[0].strRcptNo.value  = "";
                    document.forms[0].strTransNo.value = "";
		          	return;
	  }
      if(mode=="1")
      {
        var objVal = document.getElementById("billDtl");
        objVal.innerHTML =res;
        objVal.style.display="block";
	    var objVal1 = document.getElementById("withoutPrint");
	    objVal1.style.display="none";
	    var objVal2 = document.getElementById("withPrint");
	    objVal2.style.display="block";
	    var bServiceID = document.forms[0].strBServiceId.value;
	    var recType = document.forms[0].strRcptType.value;
	    
	    /* 
	      This Logic is Show Bill Type Div only in Case of Final Adjustment Service's 
	    */
	    
	    if(bServiceID == "21" && recType != "3")
	    {
	        var objVal3 = document.getElementById("BillType");
	        objVal3.style.display="block";
	    }
	    
	 }
	 if(mode == '26')
	 {
	        document.getElementById("normalMsg").innerHTML = "";
			document.getElementById("normalMsg").style.display = "none";
	        var temp = res.split("####");
            if(temp[0] == "ERROR")
		    {
		          	err.innerHTML = temp[1];
		          	err.style.display = "block";
		          	return;
		    }
		    //alert("Please Go to the Re Print Service to Re-Print the Bill");
			
	 }	

   } 

var keyupTimer;
   
   /**
  *  This function is called when
  *  when page load.  
  */
   function withoutCrNoOnLoadLogics() 
   {
   		var normalMsgVal = document.getElementById("normalMsg").innerHTML;	
		if(normalMsgVal.length > 1)
		{
			   document.forms[0].strRcptNo.value  = "";
               document.forms[0].strTransNo.value = "";
               document.getElementById("normalMsg").innerHTML = "";
               document.getElementById("errMsg").innerHTML = "";
               
                var confirmFlag = confirm("Whether Bill Printed Successfully?");
               if(!confirmFlag)//Cancel
               {
						var printLimit = document.forms[0].strPrintMessageLimit.value;					
						
						if(printLimit.length <0) printLimit = "0";						
						printLimit = parseInt(printLimit);
							
						if(printLimit > 0)
						{
							gblPrintLimitVal = printLimit;
							reprintContents();								
						} 
						else
						{
								var billNo   = document.forms[0].strTempBillNo.value;
								var recNo    = document.forms[0].strTempReceiptNo.value;
								var printFlg = 0;
								var appendedVal = billNo +"^"+ recNo +"^"+printFlg;
								var hmode     = "UPDATEPRINTSTATUS";
								//alert("normal");
								//alert(printFlg);
							    var url       = "BillRePrintTransCNT.cnt?hmode="+hmode+"&modName="+appendedVal; 
								ajaxFunction(url,"26");	
								
						}											
				}
				else
				{
						var billNo   = document.forms[0].strTempBillNo.value;
						var recNo    = document.forms[0].strTempReceiptNo.value;
						var printFlg = document.forms[0].strPrintFlag.value;
						var appendedVal = billNo +"^"+ recNo +"^"+printFlg;
						var hmode     = "UPDATEPRINTSTATUS";
						//alert(printFlg);
						var url       = "BillRePrintTransCNT.cnt?hmode="+hmode+"&modName="+appendedVal; 
						ajaxFunction(url,"26");								
				}
				//clearTimeout(keyupTimer);
				//keyupTimer = setTimeout("showNormalMsg()",3000); 
                
		    }
    }	
		
function showNormalMsg()
{
	document.getElementById("normalMsg").style.display="block";//showing normal msg
}   
   
   function resetFormValues(){
   
   	document.forms[0].strSearchString.value = "";
   
   }
   
    
    function resetFormValues2(){
   
   	document.forms[0].strSearchString.value = "";
   
   }
    
    
/**
 * chkCase
 * @param {type} obj 
 * called on select any radio button 
 * this will submit the page and call cnt method according to case
 */
 function chkCase(obj) 
 {
 	if(obj.value=="1")
 	{
 		document.forms[0].hmode.value = "CASE1";
 		 
	}
	else
	{
 		document.forms[0].hmode.value = "CASE2";
 		 
 	}
 	document.forms[0].submit();
 }    
 
/**
 * showCltDetails - shows the given divId by showing the minus image and hiding the plus image.
 * @param {object} divId - div id which should be shown.
 */
function showHelpDetails(divId)
{
	document.getElementById(divId).style.display="block";
	document.getElementById('minus'+divId).style.display="block";
	document.getElementById('plus'+divId).style.display="none";
}

/**
 * hideCltDetails - hide the given divId by hiding the minus image and showing the plus image.
 * @param {object} divId - div id which should be shown.
 */
function hideHelpDetails(divId)
{
	document.getElementById(divId).style.display="none";
	document.getElementById('minus'+divId).style.display="none";
	document.getElementById('plus'+divId).style.display="block";
}
/**
 * clearGrntrNo
 * @param {type} param 
 */
 function clearGrntrNo() 
 {
 	document.forms[0].strSearchString.disabled=false;
  	document.forms[0].strSearchString.value="";
  	document.forms[0].strSearchString.focus();
  	document.forms[0].strFromDate.value=document.forms[0].ctDate.value;
	document.forms[0].strToDate.value=document.forms[0].ctDate.value;
	document.getElementById("fetchRecordDivId").style.display="none";
	document.getElementById("plusHelpId").style.display="none";
	document.getElementById("help").style.display="none";
 }
	 
 /**
   	 * setBillNo
   	 */
   	 function setBillNo() 
   	 {
   	  		var billNoObj = document.getElementsByName("strBillNo");
   	 		var flag = false;
   	 		for(var i = 0, end = billNoObj.length ; i < end ; i++)
   	 		{
   	 		    if(billNoObj[i].checked)
   	 		    {
   	 				var result = billNoObj[i].value;
   	 				flag = true;
   	 			    window.close();
					window.opener.eval(document.forms[0].userJsFunctionName.value+"('"+result+"')");
   	 			}
   	 			
   	 		}
 		
 		if(! flag) alert("Please Select a Record");
	
   	 }
   
   function validateSearchText(eve)
   {
	 if(eve.keyCode == 13)
	 {
	 				var hisValidator = new HISValidator("BillRePrintTransBean");  
					hisValidator.addValidation("strSearchString", "req", "Please Enter Search String" );
					hisValidator.addValidation("strToDate", "dtgtet="+document.forms[0].strFromDate.value,"To Date should be Greater than or Equal to From Date");	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
					if(retVal)
					{
						fetchBillList('1','1');
	 				}
	 				else
	 				{
						return false;
					}
	 		
	 		 return false;
		 }
  }
   
   
   function getSearchBillListBySearch(obj) 
   {
	 	//alert("getSearchBillListBySearch");
	 	var hisValidator = new HISValidator("BillRePrintTransBean");  
	// 	alert(obj);
		if( obj == 'strGuarantorName')
		{
			var seatchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
			
			if(seatchType == 1){
		
			hisValidator.addValidation("strSearchString", "req", "Previous CR. No. is a Mandatory Field" );
				
			}else{
				
					hisValidator.addValidation("strSearchString", "req", "Patient Name is a Mandatory Field" );
				
			}
		
			
			hisValidator.addValidation("strToDate", "dtgtet="+document.forms[0].strFromDate.value,"To Date should be Greater than or Equal to From Date");
			var retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
			if(retVal)
			{
					fetchBillList('1','1');
	 		}
	 		else
	 		{
						return false;
					}
	 	
		}
		else
		{
		
		var seatchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
		if(seatchType == 1){
		
			hisValidator.addValidation("strSearchString", "req", "CR. No. is a Mandatory Field" );
				
			}else{
				
					hisValidator.addValidation("strSearchString", "req", "Patient Name is a Mandatory Field" );
				
			}
			
			hisValidator.addValidation("strToDate", "dtgtet="+document.forms[0].strFromDate.value,"To Date should be Greater than or Equal to From Date");
			var retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
		
			if(retVal){
					fetchBillList2('1','1');
	 				}else{
						return false;
					}
		}
		
	 
	 		
	 	} 
	 
	 
 /**
	  * setSelectedBillNo
	  * @param {String} billNo 
	  */
	  function setSelectedBillNo(billNo) {
	  	
	  	document.forms[0].strBillNo.value = billNo;
	  	
	  	goBillFunc();
	  }
	 
	 
	 // gblRowPerPage - which determines how many record should be shown in a Page, also determines how many record should be fetch from 
	var gblRowPerPage = "10";
	 
	 /**
 * showBillListingWindow
 * @param {String} mode 
 * @param {String} userJsFunctionName 
 */
 function showBillListingWindow(userJsFuncName) {
 	
 	if(document.forms[0].strBillNo.value == ""){
 	
 				var hmode = "BILLLISTING"; 
				
				var featuresList = "width=700,height=400,ALIGN=CENETER,left=200,top=200,scrollbars=yes"
					
				var myWindow = window.open('BillRePrintTransCNT.cnt?hmode='+hmode+"&usrFuncName="+userJsFuncName,'popupWindow',featuresList); 
		
					if(! myWindow.opener){
						myWindow.opener = window;
					}
 	}
			
}		
/**
  * fetchBillList2
  * called on popup GO button to show bill dtls by CR No.(in case 1)
  * @param {String} 
  */
  function fetchBillList2(fromRows,blockSet) {
   
   
   var convString = "";
  	
  	var searchString = "";
  	var searchType = "";
  	 
  			  
	            document.getElementById("billdtls").style.display="block";
	 
  		
  		  searchString = document.forms[0].strSearchString.value ;
  	 	  searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value ; 
  	 
  		var fromDate = document.forms[0].strFromDate.value ;
  		var toDate = document.forms[0].strToDate.value ;
  		
  		convString = searchString;
  		
  		if(convString !="")
  		{
  		
  		if(searchString.length > 0 && searchString.indexOf('%') != -1)
  		{
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "^" + searchArr[i];
  		}
  		 		
				var hmode = "FETCHBILLLISTING"; 
  				var url = "BillRePrintTransCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+
  						"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet+"" +
  						"&fromDate="+fromDate+"&toDate="+toDate+"&CASE=1";
						ajaxFunction3(url,"1","getBillListDetailAjaxResponse2");
			

  		}else{
  		
  				document.forms[0].strSearchString.focus();
  		
  		}
    
		 
  
}

/**
   * getBillListDetailAjaxResponse2
   * @param {String} res
   * @param {String} mode  
   */
   function getBillListDetailAjaxResponse2(res , mode) {
   	
   	  
   		if(mode == '1'){
			
			temp=res.split("@");
			 
			 
			var objEle2 = document.getElementById("billdtls");
			objEle2.innerHTML = temp[0];
			objEle2.style.display = "block"; 
			
   		}
   	
   }
   

/**
 * validate
 * called on ok button of popup screen to validate mandatory fields
 */
 function validate(obj2) 
 {
   var nameVal = "";
    var obj2
	 
 	if(obj2.value == ""){
 		alert("Please Select a Record");
 		obj2.focus();
 	}
 	else{
 		var i;
 		var billNoObj = document.getElementsByName("radio");
   	 		var flag = false;
   	 		for(i = 0, end = billNoObj.length ; i < end ; i++)
   	 		{
   	 			if(billNoObj[i].checked)
   	 			{
   	 				break;
   	 			}
   	 		}
   	 		if(i==billNoObj.length)
   	 		{
   	 			alert("Select a Record");
   	 		}
   	 		else
   	 		{
   	 			var result = billNoObj[i].value;
   	 			temp = result.split("^");
  			
  			var appended  = temp[0]+"^"+temp[1];		  		
  			window.opener.document.forms[0].strTransNo.value = temp[0];
  			window.opener.document.forms[0].strRcptNo.value  = temp[1];
  			window.opener.document.forms[0].strTransNo.disabled=true;
  			window.opener.document.forms[0].strRcptNo.disabled=true;
  			
  			
  			
  			window.opener.getGo1(appended);
  			
  			flag = true;
  			window.opener.closePopUp(); 
   	 		}
 	}
 }	
 	 

 function getGo1(appended)
  { 
    var mode="GO";
    var url       = "BillRePrintTransCNT.cnt?hmode="+mode+"&modName="+appended;
    ajaxFunction(url,"1");
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
 
    var defaultColor = "blue";
	var setColor = "red";
 
 
    function layerIndexNavigator(index,endVal)
    {
		hideAll(endVal);
		
		initObj = document.getElementsByName("linId");
		
		if(initObj.length > 0)
		{
			
			for(var i = 0 , len = initObj.length ; i < len ; i ++ )
			{
				
				initObj[i].style.color = defaultColor;
			}
			
	}
    obj = document.getElementById("linId"+index);
	if(obj != null) obj.style.color = setColor;
		
	document.getElementById("tariffDivId"+index).style.display="block";
	}
 
  /* function layerIndexNavigator(index,endVal){
		
			hideAll(endVal);
		
		document.getElementById("tariffDivId"+index).style.display="block";
	}
   */
   function hideAll(endVal)
   {
			for(var i = 1; i <= endVal ; i++)
			{
		    	document.getElementById("tariffDivId"+i).style.display="none";
		    }
		
	}   
 
			
/**
* ajaxFunction is the global function to retrieve data using ajax.
* param 1. myurl - url to be given by the user.
* param 2. mode - there will be hardcoded function i.e. getAjaxResponse(res,mode). This function will be defined by
* param 3. resFunctionName -  function name in which user need ajax Response
the developer. Mode specifies unique value provided by user at the time of calling ajaxfunction().
* This function will recieve the final response(to be defined at user end).
*/

var gblResFunctionName = "";

function ajaxFunction3(myurl,mode,resFunctionName)
{		
	userMode = mode;
	gblResFunctionName = resFunctionName;
// security feature added on 11/05/2018
	myurl = createFHashAjaxQuery(myurl);
	// checking browser 
	if (navigator.userAgent.indexOf("Opera")>=0){
		alert("This example doesn't work in Opera"); 
		return; 
	}
	if (navigator.userAgent.indexOf("MSIE")>=0) { 
		var strName="Msxml2.XMLHTTP"
		
		if (navigator.appVersion.indexOf("MSIE 5.5")>=0){
			strName="Microsoft.XMLHTTP"
		} 

		try { 
			objXmlHttp=new ActiveXObject(strName)
			objXmlHttp.onreadystatechange=sendReqUsingMethodName2
		} 
		catch(e){ 
			alert("Error. Scripting for ActiveX might be disabled");
			return; 
		} 
	}
	else {
		if (navigator.userAgent.indexOf("Mozilla")>=0){
			objXmlHttp=new XMLHttpRequest();
			objXmlHttp.onload=sendReqUsingMethodName2
			objXmlHttp.onerror=sendReqUsingMethodName2
		}
	}

	objXmlHttp.open("GET",myurl,true)
	objXmlHttp.send(null)
} 

//internal function called from ajaxFunction() function
function sendReqUsingMethodName2(){
	
	
	function adt_create_loading_msg(){var qh=80;var qw=300;var dh=0;var dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div id="qmvi_loading_div" style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16 height=16><br><br><font size="2" weight="bold">Fetching Details.Please Wait!</font></div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}
	
	if (objXmlHttp.readyState==4 || objXmlHttp.readyState==200) {
		var res = objXmlHttp.responseText; 
		
		eval(gblResFunctionName + '(res' + "," + userMode + ")"); // evel is a keyword - it calls the function passed in it as argument.
		
		 //document.getElementById("normalMsg").style.display="none";//hiding normal msg
		 try{
	     	autoTabIndexing();
	     }catch(_Err){
	     	//alert("Application Error[TabIndex not Included] Please Contact System Administrator");
	     }
		 try{
	     	eventElementObj.focus();
	     }catch(_Err){
	     	
	     }
	} 
} 
