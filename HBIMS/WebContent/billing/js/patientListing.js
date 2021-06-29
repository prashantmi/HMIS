

	// Global Variables
	
	var defaultColor = "blue";
	var setColor = "red";
			var child = null;
			var popIndex = 0;
			var gblCntrlObj = null;
	
	// gblRowPerPage - which determines how many record should be shown in a Page, also determines how many record should be fetch from 
	var gblRowPerPage = "10";
	var gblCRValue="";
	
/**
 * showPatientListingWindow
 * @param {String} mode  -  1 is Cash Collection.
 * 							2 is Discount Approval.
 * 							3 is Refund Approval.
 * 							4 is Billing Reconcillation.
 * 							5 is Online Request Cancellation.
 * 							6 Online Bill Cancellation.
 *							7 Guarantor Name List.
 * 
 *  @param {String} Obj - Cr Number Text Box
 * @param {String} userJsFunctionName - User Defined Function. 
 */
 function showPatientListingWindow(mode , obj , userJsFuncName) 
 {
	 if(obj.value!="") 
	 {
	 	//alert("CR No. field should be blank to search patient records" );
		 gblCRValue=obj.value;
		 //alert(gblCRValue);
		 obj.value="";
		 if(document.getElementsByName("gblCRValue")!=undefined)
				document.getElementsByName("gblCRValue")[0].value=gblCRValue;
		
	 }
  	if(obj.value == "" && obj.disabled == false)
  	{
		var hmode = "PATIENTLISTING"; 				
		var url = 'BillingCNT.cnt?hmode='+hmode+"&patList="+mode+"&usrFuncName="+userJsFuncName+"&gblCRValue="+gblCRValue;		
		
		//$("#fetchRecordDivId").load(url);
	openPopUp(createFHashAjaxQuery(url),'800','400','1',null);				
 	}				
 }
 
 
 
/**
 * showPatientListingWindow
 * @param {String} mode  -  1 is Cash Collection.
 * 							2 is Discount Approval.
 * 							3 is Refund Approval.
 * 							4 is Billing Reconcillation.
 * 							5 is Online Request Cancellation.
 * 							6 Online Bill Cancellation.
 *							7 Guarantor Name List.
 * 
 *  @param {String} Obj - Cr Number Text Box
 *  @param {String} userJsFunctionName - User Defined Function. 
 * @param {String} deptCode - Department code.
 */
 
  
 function showPatientListingWindowWithDeptCode(mode , obj , userJsFuncName , deptCode) 
 {
  	if(obj.value == "" && obj.disabled == false)
  	{
 				var hmode = "PATIENTLISTING"; 
				
				var url = 'BillingCNT.cnt?hmode='+hmode+"&patList="+mode+"&usrFuncName="+userJsFuncName+"&deptCode="+deptCode;				
						
				openPopUp(createFHashAjaxQuery(url),'700','400','1',null);
						
 	}
				
 }
 
 
 
 /**
  * fetchPatientList
  * @param {String} patListType 
  */
  function fetchPatientList(fromRows,blockSet) {
  	
  		
  		var convString = "";
  		
  		var searchString = document.forms[0].strSearchString.value ;
  		var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "^" + searchArr[i];
  		}
  		
  		
  	var hmode = "FETCHPATIENTLISTING"; 
  
  	var url = "BillingCNT.cnt?hmode="+hmode+"&patListType="+document.forms[0].patListType.value+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet+"&deptCode="+document.forms[0].deptCode.value;
	
		ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
  	
  	
  }
  
  function fetchPatientList_BS(fromRows,blockSet) 
  {
  	
  		var convString = "";
  		var searchString="";
  		var searchType="";
  		
  		var hmode = "FETCHPATIENTLISTINGBS"; 
  	  //alert(document.forms[0].patListType.value);
  	  	var url = "BillingCNT.cnt?hmode="+hmode+"&patListType="+2+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
  		
  	       $("#fetchRecordDivId").load(createFHashAjaxQuery(url));
  			//ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
  	  	
  }
  
  
  function fetchPatientList_BS1(fromRows,blockSet,patlist) 
  {
  	
  		var convString = "";
  		var searchString="";
  		var searchType="";
  		
  	  	var hmode = "FETCHPATIENTLISTINGBS"; 
  	  	
  	  	var url = "BillingCNT.cnt?hmode="+hmode+"&patListType="+patlist+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;

  	
	       $("#fetchRecordDivId").load(createFHashAjaxQuery(url));

  }
  
  
  function setCrNoModal() {
    	
  		var crNoObj = document.getElementsByName("strCrNoBs");
  		
  		
  		for(var i=0;i<crNoObj.length;i++)
  			{
  				if(crNoObj[i].checked==true)
  					{
  						document.getElementById("strCrNoId").value= crNoObj[i].value;
  						goFunc();
  					}
  					 
  			}
  	
  		
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
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Cr Number" );
					hisValidator.addValidation("strSearchString", "numgt=0", "Cr Number Should be a Number" );
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
						fetchPatientList('1','1');
	 				}else{
						return false;
					}
	 		}else{
	 			
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
	 		 return false;
	 		
	 	} 

	 
	 
	 function validateSearchText(eve){
	 	
	 		if(eve.keyCode == 13){
	 				
	 			var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  	
	 			if (searchType == "1") {
	 		
	 				var hisValidator = new HISValidator("billingBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Cr Number" );
					hisValidator.addValidation("strSearchString", "numgt=0", "Cr Number Should be a Number" );
	
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
		//alert('show'+divId);
		var divId='HelpId';
		document.getElementById(divId).style.display="block";
				
		document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		
	}
	
	
	
	/**
	 * hideCltDetails - hide the given divId by hiding the minus image and showing the plus image.
	 * @param {object} divId - div id which should be shown.
	 */
	function hideHelpDetails(divId){
		var divId='HelpId';
		//alert('show'+divId);
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
	 
	 
	 /**
	  * getPatListOnEnter
	  * @param {Event} eve 
	  */
	  function getPatListOnEnter(eve) 
	  {		  
		  var flag=validateData(eve,9);
		  if(flag)
		  {		   		
		   		if(eve.keyCode == 13)
		   		{									
		   			var val = getSearchPatListBySearch();		     		 
		     		document.getElementsByName("strSearchType")[0].focus();		     		  		  		
		   	  		return false;
				}
		   }
		  else
		   {
		   		return false;
		   }	  	
	  }
	 
	  /**
	  * getPatListOnEnterAtRadio
	  * @param {Event} eve 
	  */
	  function getPatListOnEnterAtRadio(eve) {
	  	
	  	if(eve.keyCode == 13){
	  		 
	  		 setCrNo();
	  		
	  		return false;
	  	}else{
	  		
	  		return true;
	  	} 
	  	
	  }
	
  function closePopup()
  {
 	 //alert(gblCRValue);
 	 //alert(document.getElementsByName("gblCRValue")[0].value);
 	 window.opener.document.getElementsByName("strCrNo")[0].value=document.getElementsByName("gblCRValue")[0].value;	
 	 window.opener.SetCursorToTextEnd('strCrNoId');
 	 window.close();
  }
  
  
  function modalDataTable(){
		$(document).ready(function() {
	$('#dtable').DataTable({
		  language: {
		    paginate: {
		      next: "<i class='fas fa-angle-right'>",
		      previous: "<i class='fas fa-angle-left'>"
		    }
		  },
	         
	          "scrollY":"200px",
	          "ordering": false,
	          "info":true,
	          "paging":true
	  
		});	
	             $(".dataTables_scrollHeadInner").css({"width":"100%"});
	             $(".table ").css({"width":"100%"});
	                $('#dtable_filter input').css('border-radius','5px');
	                
	                
		});
	}
  
  function modalDataTableOnlineReq(){
		$(document).ready(function() {
	$('#datatable').DataTable({
		  language: {
		    paginate: {
		      next: "<i class='fas fa-angle-right'>",
		      previous: "<i class='fas fa-angle-left'>"
		    }
		  },
	         
	          "scrollY":"160px",
	          "ordering": false,
	          "info":true,
	          "paging":true,
	           "lengthChange":false,
	           "searching":false
		});	
	             $(".dataTables_scrollHeadInner").css({"width":"100%"});
	             $(".table ").css({"width":"100%"});
	                $('#dtable_filter input').css('border-radius','5px');
	                
	                
		});
	}
