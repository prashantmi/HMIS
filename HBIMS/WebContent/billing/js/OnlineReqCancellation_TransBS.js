 /**
	   * initGoBillFunc
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
 */
function showPatientDetails(){
	SetCursorToTextEnd('strCrNoId');
	if(document.forms[0].CrNo.value!=""){
		
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
		document.getElementById("onlinerequest").style.display="block";
		document.getElementById("combo").style.display="block";
		document.getElementById("lastbuttons").style.display="block";
		document.getElementById("onlyClearbutton").style.display="none";  
		document.forms[0].strOtherReason.value=document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text;
		document.forms[0].strOtherReason.readOnly=true;  
		}
		
	}
	else{
		document.forms[0].strCrNo.focus();
	}
}

/**
 * function to show patient demographic details on click of +sign
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
 * function to call GO method of CNT on submit the page after entering cr no.
 */
function goFunc(){
	    /*		if(document.forms[0].strCrNo.value!=""){
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
		var hisValidator = new HISValidator("OnlineRequestCancellationTransBean");  
	
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		
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
	
/**
 * function to show the selected option of combo in text field
 */
function showText(){
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
 * global variable 
 */
var parentPopup="";

/**
 *  function to call AJAX function to show the pop up window 
 *  if any link is clicked on first time it will pass mode=1 
 	and GETPOPUP method of CNT as url with req no. and its index as parameter,
 * else(if any link is clicked again) it will pass mode=2 
 	and GETPOPUPDATA method of CNT as url with corresponding flag value i.e
 	concatenated string of pop up data
 */
	function showPopUp(obj,i){	
	parentPopup=obj;
	if(document.getElementById('flag'+i).value=="0"){	
		 var mode = "GETPOPUP";
		 var url = "OnlineRequestCancellationTransCNT.cnt?hmode="+mode+"&reqNo="+document.getElementById("hideNo"+i).value+"&index="+i;
 		 ajaxFunction(url,"1");
		
	}
	else{	
		 var hidVal = document.getElementById('flag'+i).value;
	 	 var mode = "GETPOPUPDATA";
		 var url = "OnlineRequestCancellationTransCNT.cnt?hmode="+mode+"&popupData="+hidVal;
		 ajaxFunction(url,"2");
	}
}

/**
 *  Function called by ajaxFunction
 * With mode=1 , it will call the GETPOPUP method of CNT 
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
	    var obj=document.getElementById("popup");
	    obj.innerHTML  = temp[0];
	    display_popup_menu(parentPopup,'popup','',''); 
        
	}
	
	if(mode==2)	
	{
		
	    var obj=document.getElementById("popup");
        obj.innerHTML  = res;
		display_popup_menu(parentPopup,'popup','',''); 
	    
	}
}

/**
 * showFirstPage()
 * function called on cancel button to come back on first page 
 */
 function clearPage() 
 {
 	
 	document.forms[0].strCrNo.value = "";
 	document.getElementById("errMsg").innerHTML = "";
 	document.forms[0].CrNo.value = "";
 	document.forms[0].hmode.value = "init";
				document.forms[0].submit();
 }	
 
 /**
  * validate1
  * called on save button to validate mandatory fields
  */
  function validate1() {
  
  	var noOfRows = parseInt(document.getElementById("noOfRows").value);
  		
  		for(var i=0;i<noOfRows;i++)
  		{
  			if(document.getElementById("chk"+i).checked==true)
  			{
  				break;
  			}
  		}
  		if(i==noOfRows)
  		{
  			alert("Please Select At Least One Request");
  			return false;
  		}
  	
  		var hisValidator = new HISValidator("OnlineRequestCancellationTransBean"); 
   		hisValidator.addValidation("strCancelledBy","dontselect=0","Please select a value from Cancelled By Combo");
  		if(document.forms[0].strCancelReason[document.forms[0].strCancelReason.selectedIndex].text=="others")
  		{
   		hisValidator.addValidation("strOtherReason", "req", "Please give a cancellation reason" );
   		}
  		var retVal = hisValidator.validate();
  
  	
  	if(retVal)
  	{
  		var selectedChk =""; 
  			for(var i=0;i<noOfRows;i++){
  				if(document.getElementById("chk"+i).checked==true)
  				{
  					var value = document.getElementById("chk"+i).value;
  			    	selectedChk = selectedChk+","+value;
  			    	
  			   	}
  	 		}
  	 		
  	 		//alert("selected chks--"+selectedChk);
  	 		document.forms[0].chkValue.value = selectedChk;
  	 		var conf = confirm("Are you sure to cancel the selected requests");
  	 		if(conf == true)
  	 		{
  	 			addData();
  	 		}
  	}
  	
  	else{
  		return false;
  	} 
  }

 /**
  * setSelectedCrNo
  * @param {String} crNo 
  */
  function setSelectedCrNo(crNo) {
  	
  	document.forms[0].strCrNo.value = crNo;
  	document.forms[0].strCrNo.focus();
  	goFunc();
  }
	
	
/**
  * insertData
  * called on save button to insert data into database
  * @param {type}  
  */
  function addData() {
  	document.forms[0].hmode.value = "addData";
  	document.forms[0].submit();
  }  	
  
/**CALLED ON CANCEL BUTTON
 * initPage()
 * @param {type}  
 */
 function initPage() 
 {
 	document.forms[0].hmode.value = "cancel";
  	document.forms[0].submit();
 	
 }  