
	// Global Variables	
	var defaultColor = "blue";
	var setColor = "red";
			var child = null;
			var popIndex = 0;
			var gblCntrlObj = null;
	
	var gblRowPerPage = "10";
	

 function showPatientListingWindow( obj , userJsFuncName) 
 {
   
 if(obj.value!="") 
 {
 	alert("IPD Bill No. field should be blank to search patient records" );
 }
  	if(obj.value == "" && obj.disabled == false)
  	{
 				var hmode = "RECEIPTDETAILS"; 
				
				var url = 'BillingCNT.cnt?hmode='+hmode+"&usrFuncName="+userJsFuncName;				
					
				//openPopUp(url,'700','400','1',null);
				fetchPatientList_BS(1,1);
						
 	}
				
 }
 
 /**
  * fetchPatientList
  * @param {String} patListType 
  */
  function fetchPatientList(fromRows,blockSet) 
  { 		
  		var convString = "";  		
  		var searchString = document.forms[0].strSearchString.value ;
  		var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1)
  		{  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "^" + searchArr[i];
  		}  		
  		
  		var hmode = "FETCHRECEIPTDETAILS";  
  		var url = "BillingCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
		ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");  	
  }
  
  function fetchPatientList_BS(fromRows,blockSet) 
  {
  	
  		var convString = "";
  		var searchString="";
  		var searchType="";
  		
  		var hmode = "FETCHPATIENTLISTINGBS"; 
  	  //alert(document.forms[0].patListType.value);
  		var url = "BillingCNT.cnt?hmode="+hmode+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
  		
  	       $("#fetchRecordDivId").load(createFHashAjaxQuery(url));
  			//ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
  	  	
  }
  
  /**
   * getPatientListDetailAjaxResponse
   * @param {String} res
   * @param {String} mode  
   */
   function getPatientListDetailAjaxResponse(res , mode) {
   	
   		if(mode == '1'){

			var objEle = document.getElementById("fetchRecordDivId");
						
			var resultVal = res.split("####");
						
			if(resultVal[0] == "ERROR"){
				
				objEle.innerHTML = "<table width='100%'align='center' cellspacing='1px'><tr><td class='multiControl'><b><font color='red'>"+resultVal[1]+"</font></b></td></tr></table>";
				
			}else{
				objEle.innerHTML = res;
			}
			
			
		}
   	
   }
   
  /**
   	 * setRcptNo
   	 * @param {String} result 
   	 */
   	 function setRcptNo() {
   	 		
   	 		var strRcptNoObj = document.getElementsByName("strRcptNo");
   	 		
   	 		var flag = false;
   	 		for(var i = 0, end = strRcptNoObj.length ; i < end ; i++){
   	 			
   	 			if(strRcptNoObj[i].checked){
   	 				
   	 				var result = strRcptNoObj[i].value;
   	 				flag = true;
   	 				 window.close();
					window.opener.eval(document.forms[0].userJsFunctionName.value+"('"+result+"')");
   	 			}
   	 			
   	 		}
   	 		
   			if(! flag) alert("Please Select a Record");
	
   	 }
   
   
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
	
	
	/**
	 * getSearchPatListBySearch
	 * @param {Event} eve 
	 */
	 function getSearchPatListBySearch() {
	 	
	 	var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
	 	
	 	if (searchType == "1") {
	 		
	 				var hisValidator = new HISValidator("billingBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter CR No" );
					hisValidator.addValidation("strSearchString", "numgt=0", "CR No Should be a Number" );
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}else if (searchType == "2"){
	 			
	 			var hisValidator = new HISValidator("billingBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Patient Name" );
				
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}
	 		else if (searchType == "3"){
	 			
	 			var hisValidator = new HISValidator("billingBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Admission No" );
				
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}
	 		else if (searchType == "0"){
	 			
	 			
		
					
						fetchPatientList('1','1');
	 				
	 		}
	 		 return false;
	 		
	 	} 

	 
	 
	 function validateSearchText(eve){
	 	
	 		if(eve.keyCode == 13){
	 				
	 			var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  	
	 			if (searchType == "1") {
	 		
	 				var hisValidator = new HISValidator("billingBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Bill Number" );
					hisValidator.addValidation("strSearchString", "numgt=0", "Bill Number Should be a Number" );
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}
	 		 return false;
		 }
		 
		
	 }
	 
	 /**
	 * showCltDetails - shows the given divId by showing the minus image and hiding the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function showHelpDetails(divId){
		
		document.getElementById(divId).style.display="block";
				
		document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		
	}
	
	
	
	/**
	 * hideCltDetails - hide the given divId by hiding the minus image and showing the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function hideHelpDetails(divId){
		document.getElementById(divId).style.display="none";
			
		document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
		
	}
	
	
	/**
	 * configPatListType
	 */
	 function configPatListType() 
	 {	 	
	 	var patListVal = document.forms[0].patListType.value;	 	
	 	var searchType =  document.forms[0].strSearchType;
	 	
	 	if(patListVal == '7')//
	 	{	 		
	 		searchType.selectedIndex = 1;
	 		searchType.disabled = true;	 				 	
	 	}
	 	else
	 	{
	 		searchType.selectedIndex = 0;
	 		searchType.disabled = false;
	 	}	 	
	 }
	 
	 
	 /**
	  * getPatListOnEnter
	  * @param {Event} eve 
	  */
	  function getPatListOnEnter(eve) {
	  	
	  	if(eve.keyCode == 13){

  		 var val = getSearchPatListBySearch();
  		 
  		 document.getElementsByName("strSearchType")[0].focus();
  		  		  		
	  		return false;
	  	}else{
	  		
	  		return true;
	  	} 
	  	
	  }
	 
	  /**
	  * getPatListOnEnterAtRadio
	  * @param {Event} eve 
	  */
	  function getPatListOnEnterAtRadio(eve) {
	  	
	  	if(eve.keyCode == 13){
	  		 
	  		 setRcptNo();
	  		
	  		return false;
	  	}else{
	  		
	  		return true;
	  	} 
	  	
	  }
	