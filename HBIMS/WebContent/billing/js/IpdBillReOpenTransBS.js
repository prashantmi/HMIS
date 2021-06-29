function showPatientDetails()
  {
 	
 	if(document.forms[0].RcptNo.value!="")
 	{
		
		document.forms[0].strRcptNo.readOnly=true;
		document.getElementById("details").style.display="block";
		document.getElementById("patdtltld").style.display="block";
		document.getElementById("viewBil").style.display="block";
		
		document.getElementById("billdtlId").style.display="block";
		 document.getElementById("billDetails").style.display="block";
		document.getElementById("lastbuttons").style.display="block";
		document.getElementById("goid").style.display="none";
		document.getElementById("onlyClearbutton").style.display="none"; 
	}
	else
	{
		document.forms[0].strRcptNo.focus();
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
 * goFunc
 * @param {type}  
 * function called on GO Button to call GO method of CNT
 */
 function goFunc() {
  
  		if(document.getElementById("errMsg").innerHTML.length > 0){
  			
  			alert(document.getElementById("errMsg").innerHTML);
  			return false;
  		}
  
  
 if(document.forms[0].strRcptNo.disabled == false){
 
 	var hisValidator = new HISValidator("IpdBillReOpenTransBean");  
	 	hisValidator.addValidation("strRcptNo", "req", "IPD Bill No. is a Mandatory Field" );
	     hisValidator.addValidation("strRcptNo", "minlen="+document.forms[0].strRcptNo.maxLength,"IPD Bill No. must be "+document.forms[0].strRcptNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].RcptNo.value=document.forms[0].strRcptNo.value;
	    
	    if(retVal){
	    		
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}else{
		
		return false;
		}
 }else{
 	return false;
 }
 }
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
 * showFirstPage()
 * function called on clear button to come back on first page 
 */
 function showFirstPage() {
 	
 	document.forms[0].strRcptNo.value = "";
 	document.forms[0].RcptNo.value = "";
 	document.forms[0].hmode.value = "BILLREOPEN";
				document.forms[0].submit();
 }	
 /**CALLED ON CANCEL BUTTON
 * initPage()
 * @param {type}  
 */
 function initPage() 
 {
 	/*document.forms[0].hmode.value = "CANCEL";
  	document.forms[0].submit();*/
	 cancelFunc();
 	
 }    
 function validate1() {
addData();
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
  			
  			showPatientListingWindow(document.forms[0].strRcptNo,'setSelectedRcptNo');
  			
  		}
 	 	
 	 }
 	 /**
  * setSelectedRcptNo
  * @param {String} RcptNo 
  */
  function setSelectedRcptNo(RcptNo)
   {
   	document.forms[0].strRcptNo.value = RcptNo;
  	//document.forms[0].strCrNo.focus();
  	goFunc();
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