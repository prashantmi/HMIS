 /**
	   * initGoBillFunc
	   * @param {Event} eve 
	   */
	   function initGoFunc(eve){
	   	
	   	var flag=validateData(eve,5);
  		 if(flag){
	   		
	   		if(eve.keyCode == 13){									
				return GO();
			}	   	
	   }else{
	   		return false;
	   }
	   		
	   }

/**
 *  function called on onLoad to show all the fields
 *  (IN CASE 1 )if CR no. is not null
 * (IN CASE 2 )if Guarantor Name is not null  
 */
function showPatientDetails(obj){
// chkCriteria();
SetCursorToTextEnd('strCrNoId');
 var criteria = document.getElementsByName("strCriteria");
 //alert("showPatientDetails");
  		criteria[0].checked=true;
  		criteria[1].checked=false;
  		//alert(document.getElementsByName("crbill")[0].value);
  		if(document.getElementsByName("crbill")[0].value!="1")
	 		document.getElementsByName("crbill")[0].checked=false;
	 	else
	 		document.getElementsByName("crbill")[0].checked=true;
document.forms[0].strOtherReason.value=document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text;
if(obj == "document.forms[0].strCrNo"){
	
	if(document.forms[0].CrNo.value!=""){
	//	document.getElementById("criteria2").style.display="none";
		if(document.forms[0].strCRNoSatus.value=="0")
		{
		document.getElementById("onlyClearbutton").style.display="block"; 
		document.getElementById("lastbuttons").style.display="none"; 
		}
		else{
		document.forms[0].strCrNo.readOnly=true;
		document.getElementById("details").style.display="block";
		document.getElementById("demographicInfo").style.display="block";
		document.getElementById("goid").style.display="none";
		document.getElementById("billdtls").style.display="block";
		// document.getElementById("combo").style.display="block";
		document.getElementById("lastbuttons").style.display="block";
		var billDtl = document.getElementsByName("crchk");
		if(document.getElementsByName("crbill")[0].checked==true)
			billDtl[0].checked=true;
		groupCheck(document.getElementById("crchk0"));
		document.forms[0].strOtherReason.value=document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text;
		document.forms[0].strOtherReason.readOnly=true; 
		} 
		
	}
	
	else{
		document.forms[0].strCrNo.focus();
	}
}
if(obj=="document.forms[0].strGuarantorName"){
	
	if(document.forms[0].GrntrName.value!=""){
	
	if(document.forms[0].strCRNoSatus.value=="0")
		{
		document.getElementById("onlyClearbutton").style.display="block"; 
		document.getElementById("lastbuttons").style.display="none";
		document.forms[0].strErrMsg.value ="Invalid Gaurantor Name";
		}
		else{
		document.forms[0].strGuarantorName.readOnly=true;
		document.getElementById("billdtls").style.display="block";
		//document.getElementById("combo").style.display="block";
		document.getElementById("lastbuttons").style.display="block";
		document.forms[0].strOtherReason.value=document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text;
		document.forms[0].strOtherReason.readOnly=true;  
		}
		
	}else{
		document.forms[0].strGuarantorName.focus();
	}
}
	
	
}

/**
 *  function to show patient demographic details on click of +sign
 */
function showinfo(){
	document.getElementById("plus").style.display="none";
	document.getElementById("minus").style.display="block";
	document.getElementById("demographicInfo").style.display="block";
}

/**
 *  function to hide patient demographic details on click of -sign 
 */
function hideinfo(){
	document.getElementById("plus").style.display="block";
	document.getElementById("minus").style.display="none";
	document.getElementById("demographicInfo").style.display="none";
}

/**
 *  function to call GO method of CNT on submit the page after entering cr no.
 */
function GO(){
//	document.getElementById("criteria2").style.display="none";
	/*	if(document.forms[0].strCrNo.value!=""){
			document.forms[0].CrNo.value = document.forms[0].strCrNo.value;
			document.forms[0].hmode.value = "GO";
					document.forms[0].submit();
		}
		else{
			alert("Enter a CR No.");
			document.forms[0].strCrNo.focus();
			document.forms[0].hmode.value = "init";
					document.forms[0].submit();
		}*/
	   
		if(document.forms[0].strCrNo.disabled == false){
		var hisValidator = new HISValidator("BillingCancellationTransBean");  
	
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		
			if(retVal){
				if(document.getElementsByName("crbill")[0].checked)	
					document.forms[0].hmode.value="GOCRBILL";
				else
					document.forms[0].hmode.value="GO";
				//alert(document.forms[0].hmode.value);
				document.forms[0].submit();
				
			}else{
				return false;
			}
	}else{
		return false;
	}
	}
	
	
/**
 *  function to call GO2 method of CNT on submit the page after entering Guarantor name.
 */
function GO2(){
		if(document.forms[0].strGuarantorName.value!=""){
			document.forms[0].GrntrName.value = document.forms[0].strGuarantorName.value;
			document.forms[0].strGuarantorName.readOnly=true;
			document.forms[0].hmode.value = "GO2";
					document.forms[0].submit();
		}
		else{
			alert("Enter Guarantor name");
			document.forms[0].strGuarantorName.focus();
			document.forms[0].hmode.value = "CASE2";
					document.forms[0].submit();
		}
	}	
	
/**
 *  function to show the selected option of combo in text field 
 */	
function showText(){
	document.forms[0].strOtherReason.blur();
	if(document.forms[0].strCancelReason.value == "0"){	
		document.forms[0].strOtherReason.value="";
		 document.forms[0].strOtherReason.readOnly=false;
		 document.forms[0].strOtherReason.focus();    
	}
	else{
	document.forms[0].strOtherReason.value=document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text;
	document.forms[0].strOtherReason.readOnly=true;

	}
}		

/**
 *  global variable 
 */
var parentPopup="";

/**
 *  function to call AJAX function to show the pop up window 
 * if any link is clicked on first time it will pass mode=1 
 	and GETPOPUP method of CNT as url with req no. and its index as parameter,
 * else(if any link is clicked again) it will pass mode=2 
 	and GETPOPUPDATA method of CNT as url with corresponding flag value i.e
 	concatenated string of pop up data
 */
	function showPopUp(obj,i){	
		parentPopup=obj;
	if(document.getElementById('flag'+i).value=="0"){	
		 var mode = "GETPOPUP";
		 var url = "BillingCancellationTransBSCNT.cnt?hmode="+mode+"&billNo="+document.getElementById("billNo"+i).value+"&accNo="+document.getElementById("patAccNo"+i).value+"&index="+i+"&CHK="+document.getElementById("chk"+i).value;
		 
			 
		 ajaxFunction(url,"1");
	}
	else{	
		var hidVal = document.getElementById('flag'+i).value;
	    var mode = "GETPOPUPDATA";
		var url = "BillingCancellationTransBSCNT.cnt?hmode="+mode+"&popupData="+hidVal+"&CHK="+document.getElementById("chk"+i).value;
		 
		ajaxFunction(url,"2");
	}
}

/**
 *  Function called by ajaxFunction 
 
 *  With mode=1 , it will call the GETPOPUP method of CNT 
	which will use procedure to get the output
	and set the flag value as concatenated output string with corresponding index.
	In response it will get 3 things 	
	* temp[0]=whole pop up window
	* temp[1]=concatenated string of all data values
	* temp[2]=index of the corresponding link	 
	
 *	With mode=1 ,it will call the GETPOPUPDATA method of CNT 
	which will use value of request parameter(flag) to get the output 
	
 */
function getAjaxResponse(res,mode)
{
	//alert("res : "+res);
	
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	  {
          	err.innerHTML = temp[1];
          	return;
      } 
       
 
	 if(mode==1)
	 {
	 	
	 	
	 	    var obj=document.getElementById("popup");
           	temp=res.split("@");
	 		i=temp[2];
	 	 
	 		document.getElementById('flag'+i).value=temp[1];
	 		obj.innerHTML  = temp[0];
			display_popup_menu(parentPopup,'popup','',''); 
         
	 	
	 
	}
	
	if(mode==2)	
	{
		var obj=document.getElementById("popup");
        obj.innerHTML  = res;
		display_popup_menu(parentPopup,'popup','',''); 
	
	}
	if(mode == 3)
    {
       var objEle = document.getElementById("trfDtls");
       objEle.innerHTML = res;
    }
}
/**
 * showFirstPage()
 * function called on cancel button to come back on first page 
 */
 function showFirstPage() {
 	
 	  var criteria = document.getElementsByName("strCriteria");
 	if( criteria[0].checked==true)
 	{
 	document.forms[0].strCrNo.value = "";
 	document.forms[0].CrNo.value = "";
 	document.forms[0].hmode.value = "init";
	document.forms[0].submit();
 	}
 	if( criteria[1].checked==true)
 	{
 		document.forms[0].strTransNo.value = "";
 		document.forms[0].strRcptNo.value = "";
 		document.forms[0].strCancelledBy.value = "0";
 		document.forms[0].strCancelReason.value = "2012";
 		document.forms[0].strOtherReason.value=document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text;
 		
 		
 	}
 	//document.forms[0].crbill.value="0";
 }	
 
 /**
 * showFirstPage2()
 * function called on cancel button to come back on first page 
 */
 function showFirstPage2() {
 	
 	document.forms[0].strGuarantorName.value = "";
 	document.forms[0].GrntrName.value = "";
 	document.forms[0].hmode.value = "CASE2";
				document.forms[0].submit();
 }	
 
  
 /**
  * validate1
  * called on save button to validate mandatory fields IN CASE1 1st JSP
  */
  function validate1() {
  	
    	
  	var criteria = document.getElementsByName("strCriteria");
  	
  	 if(criteria[0].checked==true){
  	 
  	 var CrCase = document.getElementsByName("strCase");
  	 
  	 
  	 	if(CrCase[0].checked==true){
  	 	
  	 	var crNoObj = document.forms[0].strCrNo;
  	 	
  	 	
  	 	if(crNoObj.value.length < 1){
  	 		
  	 		alert("CR No. is a Mandatory Field");
  	 		crNoObj.focus();
  	 		return false;
  	 		
  	 	}else if(crNoObj.value.length != crNoObj.maxLength){
  	 		
  	 		alert("CR No. must be a "+crNoObj.maxLength+" Digit Number");
  	 		crNoObj.focus();
  	 		return false;
  	 		
  	 	}
  	 	}
  	 	else{
  	 		var GaurantorObj = document.forms[0].strGuarantorName;
  	 		if(GaurantorObj.value.length < 1){
  	 		
  	 		alert("Guarantor Name is a Mandatory Field");
  	 		GaurantorObj.focus();
  	 		return false;
  	 		}
  	 	}
  	 	//alert(document.getElementsByName("crbill")[0].value);
  	 	if(document.getElementsByName("crbill")[0].value=="0")
  	 	{
  	 	var chkObj = document.getElementsByName("chk");
  	 	
  	 	if(chkObj.length > 0){
  	 		
  	 		var count = parseInt("0");
  	 		
  	 		for(var i=0; i<chkObj.length; i++) {
  	 			
  	 			if(chkObj[i].checked){
  	 				
  	 				count = count + 1;
  	 			}
  	 		}
  	 		
  	 		if(count < 1){
  	 			
  	 			alert("Please Select At Least One Receipt Detail");
  	 			return false;
  	 			
  	 		}
  	 		  	 		
  	 	}else{
  	 		
  	 		alert("No Receipt Detail(s) Available");
  	 		return false;
  	 		
  	 	}
  	 	}
  	 	else
  	 	{
        var crchkObj = document.getElementsByName("cehk");
  	 	
  	 	if(crchkObj.length > 0){
  	 		
  	 		var cnt = parseInt("0");
  	 		
  	 		for(var i=0; i<crchkObj.length; i++) {
  	 			
  	 			if(crchkObj[i].checked){
  	 				
  	 				cnt = cnt + 1;
  	 			}
  	 		}
  	 		
  	 		if(cnt < 1){
  	 			
  	 			alert("Please Select At Least One Credit Tariff Detail");
  	 			return false;
  	 			
  	 		}
  	 		if(cnt==crchkObj.length)
  	 			document.forms[0].billcancelflag.value=1;
  	 		  	 		
  	 	}else{
  	 		
  	 		alert("No Credit Receipt Detail(s) Available");
  	 		return false;
  	 		
  	 	}
  	 	}
  	 	
  	 	
  	 }else{
  	 	
  	 	var billObj = document.forms[0].strTransNo;
  	 	var recObj = document.forms[0].strRcptNo;
  	 	
  	 	if(billObj.value.length < 1 || recObj.value.length < 1){
  	 		
  	 		alert("Receipt No. is a Mandatory Field");
  	 		billObj.focus();
  	 		return false;
  	 		
  	 	}else if(billObj.value.length != 15 || recObj.value.length < 1 || recObj.value.length > 2){
  	 		
  	 		alert("Receipt No. should be a 15 digit / 1 or 2 digit Number");
  	 		billObj.focus();
  	 		return false;
  	 		
  	 	}
  	 	
  	 	
  	 }
  	
  	  	
   	var hisValidator = new HISValidator("BillingCancellationTransBean"); 
  	
   	hisValidator.addValidation("strCancelledBy","dontselect=0","Please select a value from Cancelled By Combo");
   	if(document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text=="others"){
   		hisValidator.addValidation("strOtherReason", "req", "Please give a cancellation reason" );
   	}
   	
   	var retVal = hisValidator.validate();
  	hisValidator.clearAllValidations();
   	
   		if(retVal){
   			
   			$( function() {
   			    $( "#dialog-message" ).dialog({
   			      modal: true,
   			      position: 'top',
   			      dialogClass: 'no-close',
   			      buttons: {
   			        Ok: function() {
   			          $( this ).dialog( "close" );
   			       document.forms[0].hmode.value = "addData";
	   			    if(document.getElementById("errMsg").innerHTML.length > 0 && document.getElementById("errMsg").style.display == 'block')
		 		 	{
						alert("Cannot Save due to Error:"+document.getElementById("errMsg").innerHTML);
						return false;	 		 		
		 		 	}		
   				   
   			        },
   			        Close: function() {
   			          $( this ).dialog( "close" );
   			          return false;
   			        }
   			      }
   			    });
   			  } );
   		 
  	 		/*var conf = confirm("Are you sure to cancel the selected Receipt");
  	 		if(conf == true){


			document.forms[0].hmode.value = "addData";
				document.forms[0].submit();
}
  	 	
  			else{
   			
   			return false;
   		}*/
   			
   		}else{
   			
   			return false;
   		}
   	
   	
   /*	
  var criteria = document.getElementsByName("strCriteria");
  if(criteria[0].checked==true){
  	 chkCriteria();
  	hisValidator.addValidation("CrNo", "req", "Please select a Bill After Entering CR No." );
  	var retVal = hisValidator.validate();
  	hisValidator.clearAllValidations();
  	if(retVal){
  			var noOfRows = parseInt(document.getElementById("noOfRows").value);
  		
  			for(var i=0;i<noOfRows;i++){
  				if(document.getElementById("chk"+i).checked==true){
  				//	alert("hidden values--"+document.getElementById("chk"+i).value);
  					break;
  			}
  			}
  			if(i==noOfRows){
  				alert("please select atleast one Bill Detail");
  			}
  		
  				//	var flag = true;
  			var selectedChk =""; 
  			for(var i=0;i<noOfRows;i++){
  				if(document.getElementById("chk"+i).checked==true){
  					var value = document.getElementById("chk"+i).value;
  			    	selectedChk = selectedChk+","+value;
  			   	}
  	 		}
  	 		if(selectedChk!=""){
  	 			alert("selected chk--"+selectedChk);
  	 		document.forms[0].chkValue.value = selectedChk;
  	 		
  	 		  	 
  	 		var conf = confirm("Are you sure to cancel the selected Bill");
  	 		if(conf == true){	addData();}
  	 		}
  			
  	 		
  }  	 		
  	else{
  		return false;
  		}
 } 
 
 if(criteria[1].checked==true){
 	 chkCriteria();
 	 var retVal = hisValidator.validate();
 	 hisValidator.clearAllValidations();
 	if(retVal){
 	
 	if(document.forms[0].strTransNo.value!="" ){
		if(document.forms[0].strTransNo.value.length==10 && document.forms[0].strRcptNo.value!=""){
			document.forms[0].chkValue.value="";
			document.forms[0].CrNo.value!=""
			document.forms[0].strCrNo.value="";
			chkCriteria();
			addData();
		}else{
			alert("Enter a complete Bill No. (trans no./ reciept no.)(trans no. should have 10 digits)");
			document.forms[0].strTransNo.focus();
			}
	}else{
		alert("Please enter a Bill No.");
		document.forms[0].strTransNo.focus();
		}
 	}
  	}
  
  	*/	
 }
  
  
  /**
  * validate2
  * called on save button to validate mandatory fields IN CASE OF 2nd JSP
  */
  /*function validate2() {
  
  	var hisValidator = new HISValidator("BillingCancellationTransBean"); 
   	hisValidator.addValidation("strCancelledBy","dontselect=0","Please select a value from Cancelled By Combo");
  	if(document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text=="others"){
   		hisValidator.addValidation("strOtherReason", "req", "Please give a cancellation reason" );
   	}
  	
  	var criteria = document.getElementsByName("strCriteria");
  if(criteria[0].checked==true){
  	chkCriteria2();
  	hisValidator.addValidation("GrntrName", "req", "Please select a Bill after Entering Guarantor Name" );
  	var retVal = hisValidator.validate(); 
  	hisValidator.clearAllValidations();
  	if(retVal)
  	{
  		var noOfRows = parseInt(document.getElementById("noOfRows").value);
  		
  		for(var i=0;i<noOfRows;i++)
  		{
  			if(document.getElementById("chk"+i).checked==true)
  			{
  			//	alert("hidden values--"+document.getElementById("chk"+i).value);
  				break;
  			}
  		}
  		if(i==noOfRows)
  		{
  			alert("please select atleast one Bill Detail");
  		}
  		
  	//	var flag = true;
  		var selectedChk =""; 
  		for(var i=0;i<noOfRows;i++)
  		{
  				if(document.getElementById("chk"+i).checked==true)
  				{
  					var value = document.getElementById("chk"+i).value;
  			    	selectedChk = selectedChk+","+value;
  			   	}
  	 		}
  	 	if(selectedChk!=""){
  	 			alert("selected chk--"+selectedChk);
  	 		document.forms[0].chkValue.value = selectedChk;
  	 		
  	 		var conf = confirm("Are you sure to cancel the selected Bill");
  	 		if(conf == true){	addData();}
  	 		}
  			
  	 		
  }  	 		
  	else{
  		return false;
  		}
 } if(criteria[1].checked==true){
 	chkCriteria2();
 	 var retVal = hisValidator.validate();
 	 hisValidator.clearAllValidations();
 	if(retVal){
 	
 	if(document.forms[0].strTransNo.value!="" ){
		if(document.forms[0].strTransNo.value.length==10 && document.forms[0].strRcptNo.value!=""){
			document.forms[0].chkValue.value="";
			document.forms[0].CrNo.value!=""
			document.forms[0].strCrNo.value="";
			addData();
		}else{
			alert("Enter a complete Bill No. (trans no./ reciept no.)(trans no. should have 10 digits)");
			document.forms[0].strTransNo.focus();
			}
	}else{
		alert("Please enter a Bill No.");
		document.forms[0].strTransNo.focus();
		}
 	}
  	}
  	
  }
  */
  
  /**
   * setSelectedCrNo
   * @param {String} crNo 
   */
   function setSelectedCrNo(crNo) {
   		document.forms[0].strCrNo.value = crNo;
   		document.forms[0].strCrNo.focus();
  		GO();
  	
   }
   
   
/**
   * setSelectedGrntrName
   * @param {String} crNo 
   */
   function setSelectedGrntrName(GrntrNo) {
   		document.forms[0].strGuarantorName.value = GrntrNo;
  		document.forms[0].strGuarantorName.focus();
  		GO2();
   }
   

 /**
  * insertData
  * called on save button to insert data into database
  * @param {type}  
  */
  function addData() {
  	document.forms[0].hmode.value = "addData";
  	if(document.getElementById("errMsg").innerHTML.length > 0 && document.getElementById("errMsg").style.display == 'block')
	 	{
		alert("Cannot Save due to Error:"+document.getElementById("errMsg").innerHTML);
		return false;	 		 		
	 	}
				document.forms[0].submit();
  }   
  
  
/**
 * chkCase
 * @param {type} obj 
 * called on select any radio button CASE 1 AND CASE2
 * this will submit the page and call cnt method according to case
 */
 function chkCase(obj) {
 	if(obj.value=="1"){
 		document.forms[0].hmode.value = "CASE1";
	}else{
 		document.forms[0].hmode.value = "CASE2";
 	}
 	document.forms[0].submit();
 }    
 
/**
 * chkCriteria
 * @param {type} obj 
 * called on select any radio button on 1st JSP
 * this will submit the page and call cnt method according to case
 */
 function chkCriteria(caseVal) {
 	 	
 	 	if(caseVal=='1')
 	 	{
 	 	 var criteria = document.getElementsByName("strCriteria");
  		if(criteria[0].checked==true)
 	 	//if(obj.value=="1")
 	 	{
  			
 	 		document.forms[0].strTransNo.value="";
 	 		document.forms[0].strRcptNo.value="";
 	 	
 		document.getElementById("c2body").style.display="none";
 		document.getElementById("criteria1").style.display="block";
		}
		if(criteria[1].checked==true)
		//if(obj.value=="2")
		{
			//alert("case 1 criteria2");
			document.forms[0].CrNo.value="";
			document.forms[0].strCrNo.value="";
			document.forms[0].strCrNo.readOnly=false;
			document.forms[0].chkValue.value="";
		document.getElementById("billdtls").style.display="none";
		document.getElementById("details").style.display="none";	
		document.getElementById("demographicInfo").style.display="none";
		
		document.getElementById("criteria1").style.display="none";
 		document.getElementById("c2body").style.display="block";
 		document.getElementById("onlyClearbutton").style.display="none";
 		document.getElementById("lastbuttons").style.display="block";
 		document.forms[0].strTransNo.focus();
 	}
 	}
 	else{
 		
 	  var criteria = document.getElementsByName("strCriteria");
  		if(criteria[0].checked==true){
  			document.forms[0].strTransNo.value="";
 	 		document.forms[0].strRcptNo.value="";
 	 	
 		document.getElementById("c2body").style.display="none";
 		document.getElementById("criteria1").style.display="block";
		}
	if(criteria[1].checked==true){
		//alert("bill no");
			document.forms[0].GrntrName.value="";
			document.forms[0].strGuarantorName.value="";
			document.forms[0].strGuarantorName.readOnly=false;
			document.forms[0].chkValue.value="";
		document.getElementById("billdtls").style.display="none";
		document.getElementById("criteria1").style.display="none";
 		document.getElementById("c2body").style.display="block";
 		document.getElementById("onlyClearbutton").style.display="none";
 		document.getElementById("lastbuttons").style.display="block";
 		
 		document.forms[0].strTransNo.focus();
 		
 	}
 	}
 	
 }   

 /**
 * chkCriteria
 * @param {type} obj 
 * called on select any radio button ON 2nd JSP
 * this will submit the page and call cnt method according to case
 */
  /*function chkCriteria2() {
 	 	
 	  var criteria = document.getElementsByName("strCriteria");
  		if(criteria[0].checked==true){
  			document.forms[0].strTransNo.value="";
 	 		document.forms[0].strRcptNo.value="";
 	 	
 		document.getElementById("c2body").style.display="none";
 		document.getElementById("criteria1").style.display="block";
		}
	if(criteria[1].checked==true){
		//alert("bill no");
			document.forms[0].strTransNo.focus();
			document.forms[0].GrntrName.value="";
			document.forms[0].strGuarantorName.value="";
			document.forms[0].strGuarantorName.readOnly=false;
			document.forms[0].chkValue.value="";
		document.getElementById("billdtls").style.display="none";
		document.getElementById("criteria1").style.display="none";
 		document.getElementById("c2body").style.display="block";
 	}
 	
 }   */
 /**
 * focusNext
 * @param {type} param 
 * function to focus into next text-box after entering 10 digits in first box of bill no.
 */
 function focusNext(obj) {
 
 //	alert(obj.value.length);
 	 if(obj.value.length == '15') document.forms[0].strRcptNo.focus();
 }
   
   
/**
 * initPage()
 * @param {type}  
 */
 function initPage() {
 	document.forms[0].hmode.value = "cancel";
  	document.forms[0].submit();
 	
 }     
 function groupCheck(chkObj) {
	 	
	 	document.forms[0].chkValue.value = chkObj.value;
	 	
	 	 var j;
	     var mode="TrfDtl";
	     var url="BillingCancellationTransBSCNT.cnt?hmode="+mode+"&modName="+chkObj.value;
	     ajaxFunction(url,"3");
	 }

 //calculate net refund amount
 function calTotalRefundAmt(obj)
 {
	 /*alert(obj.value);
	 alert(obj.checked);*/
	 var qty=obj.value.split('^')[1];
	 var rate=obj.value.split('^')[3];
	 var netCostHiddObj = document.getElementById("trf_grossRefund");  //hidden
	 var netCostObj = document.getElementById("netRefCost"); //div
	 	
	 	var netCost=qty*rate;
	 	var count = parseFloat(netCostHiddObj.value);
	 	if(obj.checked)
	 	{
	 		netCostHiddObj.value=count+parseFloat(netCost);
		 	netCostObj.innerHTML=count+parseFloat(netCost);
	 	}
	 	else
	 	{
	 		netCostHiddObj.value=count-parseFloat(netCost);
	 	    netCostObj.innerHTML=count-parseFloat(netCost);
	 	}
	 		
 
	 //var chkObj = document.getElementsByName("cehk");
	 	
	 	/*if(chkObj.length > 0){
	 		
	 		var count = parseFloat(netCostHiddObj.value);
	 		var cnt=parseInt("0");
	 		alert(count);
	 		//alert(parseFloat(netCost));
	 		//alert(chkObj.length);
	 		for(var i=0; i<chkObj.length; i++) {
	 			//alert(chkObj[i].checked);
	 			if(chkObj[i].checked){
				 	netCostHiddObj.value=count+parseFloat(netCost);
				 	netCostObj.innerHTML=count+parseFloat(netCost);
	 			}
	 			cnt++;
	 			/*else
	 			{
	 				netCostHiddObj.value= count-parseFloat(netCost);
				 	netCostObj.innerHTML= count-parseFloat(netCost);
	 			}*/
	 		//}	
	 		
	 		//}
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