	// Global Variables
			var child = null;
			var popIndex = 0;
			var gblCntrlObj = null;
			
			var defaultColor = "blue";
			var setColor = "red";
			
			
	// gblRowPerPage - which determines how many record should be shown in a Page, also determines how many record should be fetch from 
	var gblRowPerPage = "10";
	
	
	
	
	
/**
 * showPatientListingWindow
 * @param {String} mode  -  1 is Admission Process.
 * 							2 is Leave Approval.
 * 							3 is Leave Record Entry.
 * 							4 is Join Record Entry.
 * 							5 is Admission Cancellation.	
 * 
 *  @param {String} Obj - Cr Number Text Box
 * @param {String} userJsFunctionName - User Defined Function. 
 */
 function showPatientListingWindow(mode , obj , userJsFuncName) 
 {
  	if(obj.value == "")
  	{
 				var hmode = "PATIENTLISTING"; 
				var url = "IpdCNT.cnt?hmode="+hmode+"&patList="+mode+"&usrFuncName="+userJsFuncName;				
				openPopUp(url,'700','220','1','',null);
				//var featuresList = "width=600,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes";
               // myWindow = window.open(url,'popupWindow',featuresList); 
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
	  	
	  	var hmode = "FETCHPATIENTLISTING"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+"9"+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
		ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
  }
  
  /**
   * getPatientListDetailAjaxResponse
   * @param {String} res
   * @param {String} mode  
   */
   function getPatientListDetailAjaxResponse(res , mode) {
   	
   		if(mode == '1'){

			var objEle = document.getElementById("fetchRecordDivId");
			objEle.innerHTML = res;
			resize_popUp_Search();
		}
   	
   }
   
  /**
   	 * setCrNo
   	 * @param {String} result 
   	 */
   	 function setCrNo() {
   	 		
   	 		var crNoObj = document.getElementsByName("strCrNo");
   	 		var flag = false;
   	 		for(var i = 0, end = crNoObj.length ; i < end ; i++){
   	 			if(crNoObj[i].checked){
   	 				var result = crNoObj[i].value;
   	 				flag = true;
   	 				 
   	 				window.opener.document.forms[0].strCrNo.value=result;
					var allImageObj = window.opener.document.getElementsByTagName("img");
					//alert(allImageObj);
					for(var nTmpI=0; nTmpI<allImageObj.length; nTmpI++){
						//alert(allImageObj[nTmpI].src.split("/")[allImageObj[nTmpI].src.split("/").length-1]);
						if(allImageObj[nTmpI].src.split("/")[allImageObj[nTmpI].src.split("/").length-1]=="Go.png"){
						//	alert("Go");
							allImageObj[nTmpI].onclick();
						}
					}
					window.close();
   	 			}
   	 		}
   	 		window.opener.goFunc();
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
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
 * By Pragya
*/
function validatePositiveIntegerValue(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
}
	
	
	/**
	 * getSearchPatListBySearch
	 * @param {Event} eve 
	 */
	 function getSearchPatListBySearch() {
	 	var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
	 	
	 	if (searchType == "1") {
	 		
	 				var hisValidator = new HISValidator("ipdBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter CR Number" );
					//hisValidator.addValidation("strSearchString", "numgt=0", "CR Number Should be a Number" );
					var retVal = hisValidator.validate(); 
					if(!validatePositiveIntegerValue(document.getElementsByName("strSearchString")[0].value))
					{
						alert("Cr No. should be Numeric Only");
						document.getElementsByName("strSearchString")[0].focus();
						retVal=false;
					}
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}else{
	 			
	 			var hisValidator = new HISValidator("ipdBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Patient Name" );
				
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
					if(validateThroughRegExp(document.forms[0].strSearchString,2))
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}
	 		 return false;
	 		
	 	} 

	 
	 
	 function validateSearchText(eve){
	 	
	 		if(eve.keyCode == 13){
	 				
	 			var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  	
	 			if (searchType == "1") {
	 		
	 				var hisValidator = new HISValidator("ipdBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter CR Number" );
					hisValidator.addValidation("strSearchString", "numgt=0", "CR Number Should be a Number" );
	
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
	 function configPatListType() {
	 	
	 	var patListVal = document.forms[0].patListType.value;
	 	
	 	var searchType =  document.forms[0].strSearchType;
	 	
	 	if(patListVal == '7'){	 		
	 		searchType.selectedIndex = 1;
	 		searchType.disabled = true;
	 				 	
	 	}else{
	 		searchType.selectedIndex = 0;
	 		searchType.disabled = false;
	 	}
	 	
	 }
	
function setSelectedCrNo(crNo) {
  	document.forms[0].strCrNo.value = crNo;
  //	goFunc();
  }
  
function resize_popUp_Search()
{
 var newHeight=document.forms[0].strWin_Resize.value;
 window.resizeTo('700',newHeight);
}