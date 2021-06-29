// Global Variables
	
			var child = null;
			var popIndex = 0;
			var gblCntrlObj = null;

// change by bala

/**
	   * initGoBillFunc
	   * @param {Event} eve 
	   */
	   function initGoFunc(eve){
	   	
	   	 var flag=validateData(eve,5);
  		 if(flag){
	   	
		   	if(eve.keyCode == 13){
													
					return getSearchBillListBySearch('strCrNo');
					
				}	   	
	
	  		}else{
		   		return false;
		   }			

	   }

var keyupTimer;	   
/**
 *  function called on loading of main page 
 *  So this function will be called twice
 *  1st to enter a bill no
 *  2nd after entering to display bill details of entered bill no.
 */
function showBillDetails()
{
		document.getElementsByName('strReceiptTypeLabel')[0].value=document.getElementsByName('strReceiptType')[0].options[0].text;
		
		document.forms[0].strTransNo.focus();		
		var normalMsg = document.getElementById("normalMsg").innerHTML;
		
		if(normalMsg.length>1)
		{
		 //commented cz bill popup is open in new window for printing..
		/* var confirmFlag = confirm("Receipt Printed Successfully?");		 
		 
		 	if(!confirmFlag)
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
								var billNo = document.forms[0].strTempBillNo.value;
								var recNo  = document.forms[0].strTempReceiptNo.value;
											
								 var mode="UPDATE";
  		 						 var url="BillDupPrintTransCNT.cnt?hmode="+mode+"&billNo="+billNo+"&recieptNo="+recieptNo;
    							ajaxFunction(url,"2");								
						}											
			}
			else
			{							
							document.forms[0].strTransNo.value = "";
			} */
			//clearTimeout(keyupTimer);
			//keyupTimer = setTimeout("showNormalMsg()",3000);
		}	
}
function showNormalMsg()
{
	document.getElementById("normalMsg").style.display="block";//showing normal msg
} 
/**
 * focusNext
 * @param {type} param 
 * function to focus into next text-box after entering 10 digits in first box of bill no.
 */
 function focusNext(obj) {
 
  	 if(obj.value.length == '15') document.forms[0].strRcptNo.focus();
 }

 
 /**
 *  function called on print button on submit the page after entering bill no.
 */
function PRINT()
{
	var hisValidator = new HISValidator("BillDupPrintTransBean"); 
  	
  	hisValidator.addValidation("strTransNo", "req", "Please Enter a Receipt No." );
  	var retVal = hisValidator.validate();
  	hisValidator.clearAllValidations();
   	
   	if(retVal)
   	{	
	    if(document.forms[0].strTransNo.value.length==15)
		{
			document.forms[0].hmode.value = "PRINT";
			document.forms[0].strTransNo.disabled = false;
 	        document.forms[0].strRcptNo.disabled = false;
 	        
 	    	document.forms[0].submit();
 	   }
 	   else
 	   {
	 	  	alert("Receipt No. should be 15 digit No.");
	 	  	retVal= false;
 	   }
 	}
 	  return retVal;   
 }
	
/**
 * function called on click of search button on main page to 
 * call BillSearchPopup method of CNT which loads the search page(or popup screen)
 */
 function showBillSearchPopup() 
 {
 if(document.forms[0].strTransNo.value=="")
 {
     	url = 'BillDupPrintTransCNT.cnt?hmode=BILLSEARCHPOPUP';
     		openPopUp(createFHashAjaxQuery(url),'1050','400','1',null);
	 	//window.open(url,"popupWindow","width=610,height=390,scrollbars=yes");
	 	}
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
  	document.forms[0].strSearchString.disabled=false;
  	document.forms[0].strSearchString.value="";
  	document.forms[0].strSearchString.focus();
  	document.forms[0].strFromDate.value=document.forms[0].ctDate.value;
	document.forms[0].strToDate.value=document.forms[0].ctDate.value;
 
	document.getElementById("billdtls").style.display="none";
	 var errObj = document.getElementById("errMsg");
      errObj.innerHTML="";
     
	
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
 	document.forms[0].strTransNo.value="";
 	document.forms[0].strRcptNo.value="";
 	document.forms[0].strTransNo.disabled=false;
 	document.forms[0].strRcptNo.disabled=false;
 	var objVal = document.getElementById("billDtl");
    objVal.innerHTML ="";
 
 	document.forms[0].strTransNo.focus();
 	var errObj = document.getElementById("errMsg");
      errObj.innerHTML="";
 }	
 
 function clearWithOutBillRePrint()
 {
 	document.forms[0].strTransNo.value="";
 	document.forms[0].strRcptNo.value="";
 	document.forms[0].strTransNo.disabled=false;
 	document.forms[0].strRcptNo.disabled=false;
 	var objVal = document.getElementById("billDtl");
    objVal.innerHTML ="";
	
	    
	document.forms[0].strTransNo.focus();
	//var errObj = document.getElementById("errMsg");
     // errObj.innerHTML="";
	//document.forms[0].strErrMsg.value="";
 	
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
  	
  	var url = "BillDupPrintTransCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+
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
			objEle.style.display = "block";
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
		var hisValidator = new HISValidator("BillDupPrintTransBean");  
	 	hisValidator.addValidation("strTransNo", "req", "Receipt No. is a Mandatory Field" );
	    hisValidator.addValidation("strTransNo", "minlen=10", "Receipt No. must be 10 Digits" );
	    
	    if(document.forms[0].strModeVal.value == '2'){
	    	
	    	hisValidator.addValidation("strRcptNo", "req", "Receipt No. is a Mandatory Field" );
	    	
	    }
	    
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
    var url="BillDupPrintTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strTransNo.value+"&recNo="+document.forms[0].strRcptNo.value;
    ajaxFunction(url,"1");
  }
  
  
  
  function getAjaxResponse(res,mode)
  {
    
      var errObj = document.getElementById("errMsg");
      var temp = res.split("####");
      if(temp[0]=="ERROR")
     	 {
     	 errObj.innerHTML=temp[1];
     	 }
     	 else{
      if(mode=="1")
       {
        var objVal = document.getElementById("billDtl");
        objVal.style.display="block";
        objVal.innerHTML =res;
          
        document.forms[0].strTransNo.disabled=true;
	    objVal.style.display="block";
	    var objVal1 = document.getElementById("withoutPrint");
	    objVal1.style.display="none";
	    var objVal2 = document.getElementById("withPrint");
	    objVal2.style.display="block";
	    var bServiceID = document.forms[0].strBServiceId.value;
	    
	    if(bServiceID == "21"){
	        var objVal3 = document.getElementById("BillType");
	        objVal3.style.display="block";
	    }
	   
      }
       errObj.innerHTML="";
      }
      if(mode=="2")
       {
        var errObj = document.getElementById("errMsg");
     	 var temp = res.split("####");
     	 if(temp[0]=="ERROR")
     	 {
     	 errObj.innerHTML=temp[1];
     	 }
     	 else{
     	 
     	 var reprintChargeVal = document.forms[0].strTempRePrintChg.value;
     	 
     	 reprintChargeVal.length <= 0 ? reprintChargeVal = parseInt("0") : reprintChargeVal = parseInt(reprintChargeVal);
     	 
     	 if(reprintChargeVal > 0){
     	 	 alert("PLEASE GO TO RE-PRINT OPTION TO RE-PRINT THE BILL");
     	 }else{
     	 	 alert("PLEASE PRINT AGAIN");
     	 }
     	 
     	
     	 
     	 }
     	
	    }
	 
      
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
	 var errObj = document.getElementById("errMsg");
      errObj.innerHTML="";
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
	 				var hisValidator = new HISValidator("BillDupPrintTransBean");  
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
	 	var hisValidator = new HISValidator("BillDupPrintTransBean");  
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
					
				var myWindow = window.open(createFHashAjaxQuery('BillDupPrintTransCNT.cnt?hmode='+hmode+"&usrFuncName="+userJsFuncName,'popupWindow',featuresList)); 
		
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
  	// alert("fetchBillList2");
  
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
  				var url = "BillDupPrintTransCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+
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
   		var errObj = 	document.getElementById("errMsg");
			
			temp=res.split("@");
			 
			document.forms[0].strSearchString.disabled=true;
			errObj.innerHTML = "";
			 
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
     
	 
 	if(obj2.value == ""){
 		alert("Please select a Record");
 		obj2.focus();
 	}
 	else{
 		var i;
 		var billNoObj = document.getElementsByName("radio");
   	 		var flag = false;
   	 		for(i = 0, end = billNoObj.length ; i < end ; i++){
   	 		
   	 			if(billNoObj[i].checked){
   	 				break;
   	 			}
   	 		}if(i==billNoObj.length){
   	 			alert("Select a Record");
   	 		}else{
   	 			var result = billNoObj[i].value;
  		
  					temp = result.split("^");
  					  		
  			window.opener.document.forms[0].strTransNo.value=temp[0];
  			window.opener.document.forms[0].strRcptNo.value=temp[1];
  			window.opener.document.forms[0].strTransNo.disabled=true;
  			window.opener.document.forms[0].strRcptNo.disabled=true;
  			flag = true;
  			
  			window.opener.closePopUp(); 
  			
  			window.opener.getGo();
  			//getGoOnOK();
  			
  			
  				
   	 		}
 	}
 	
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
 
 
    function layerIndexNavigator(index,endVal){
		
			hideAll(endVal);
		
		initObj = document.getElementsByName("linId");
		
		if(initObj.length > 0){
			
			for(var i = 0 , len = initObj.length ; i < len ; i ++ ){
				
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
   function hideAll(endVal){
	
		for(var i = 1; i <= endVal ; i++){
			document.getElementById("tariffDivId"+i).style.display="none";
		}
		
	}   
	
 
 
 
	var gblPrintLimitVal = 0;
	
	
	/**
	 * reprintContents
	 */
	 function reprintContents() {
	 	
	 		 	
	 		if(gblPrintLimitVal != 0){
	 			
	 			var hmode = "REPRINT"; 
				var url = "BillDupPrintTransCNT.cnt?hmode="+hmode;
											
				ajaxFunction3(url,"1","rePrintStatus");	
	 			
	 		} 
	 		
	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 function rePrintStatus() {
	 	
	 	gblPrintLimitVal = gblPrintLimitVal - 1;
	 	
	 		var confirmFlag = confirm("Whether Receipt Printed Successfully?");
					
						if(!confirmFlag){
							
							reprintContents();
							
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
function showPrintableSlip()
{
	//alert("show");
	document.getElementById("printableSlip").style.display=""; 
}
function hidePrintableSlip()
{
	//alert("hide");
	document.getElementById("printableSlip").style.display="none"; 
}
function openPrintPopUp()
{
	if(document.forms[0].isOpenPopUp.value==1)//Printing Over Laser Printer
	{
			window.print();
			//window.close();
		
		/*var url='CashCollectionOfflineTransCNT.cnt?hmode=PRINTSLIP&filePath='+document.forms[0].filePath.value
		  child = window.open(createFHashAjaxQuery(url,'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100'));  
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

function changeReceiptTypelabel(obj)
{
	document.getElementsByName('strReceiptTypeLabel')[0].value=document.getElementsByName('strReceiptType')[0].options[obj.selectedIndex].text;
	
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