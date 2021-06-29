	// Global Variables
			var child = null;
			var popIndex = 0;
			var gblCntrlObj = null;
			
			var defaultColor = "blue";
			var setColor = "red";
			
			
	// gblRowPerPage - which determines how many record should be shown in a Page, also determines how many record should be fetch from 
	var gblRowPerPage = "10";
	var gblCRValue="";
	
	
	
	
	
/**
 * showPatientListingWindow-Onclick of Search Button
 * @param {String} mode  -  1 Leave.
 * 							2 is Leave Approval.
 * 							3 is Leave Record Entry.
 * 							4 Patient Admission Process
 * 							5 Patient Final Discharge Process.
 * 							6 New Born Baby Admission Process
 * 							8 Adission Modification & Cancellation Process
 *                          9 MMS
 *  @param {String} Obj - Cr Number Text Box
 * @param {String} userJsFunctionName - User Defined Function. 
 */
 function showPatientListingWindowBootstrap(mode , obj , userJsFuncName,flag) 
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
 	if(obj.value == "")
  	{
 				var hmode = "PATIENTLISTING"; 
				var url = "IpdCNT.cnt?hmode="+hmode+"&patList="+mode+"&usrFuncName="+userJsFuncName+"&gblCRValue="+gblCRValue;
				//openPopUp(createFHashAjaxQuery(url),'700','220','1','',null);
				 $('#serachBody').load(createFHashAjaxQuery(url),'700','220','1','',null);
				//var featuresList = "width=600,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes";
               // myWindow = window.open(url,'popupWindow',featuresList); 
				 
 	}
 }
 function showPatientListingWindow(mode , obj , userJsFuncName,flag) 
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
 	if(obj.value == "")
  	{
 				var hmode = "PATIENTLISTING"; 
				var url = "IpdCNT.cnt?hmode="+hmode+"&patList="+mode+"&usrFuncName="+userJsFuncName+"&gblCRValue="+gblCRValue;
				openPopUp(createFHashAjaxQuery(url),'700','220','1','',null);
				 
				//var featuresList = "width=600,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes";
               // myWindow = window.open(url,'popupWindow',featuresList); 
				 
 	}
 }
 //Used In Admission Modification Process-mode=8
 function showPatientListingWindowforPage(mode , obj)
 { 
 	if(obj.value == "" || checkCrdef(obj)==true)//No Need To Again Get The List
  	{  	
		var hmode = "FETCHPATIENTLISTINGMODIFICATION"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+mode+"&searchString="+''+"&searchType="+1+"&fromRow="+1+"&rowPerPage="+10+"&ctBlockSet="+1;
		ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");		 
 	} 	
 }
 
 //Used In Admission Modification Process-mode=8
 function showPatientListingWindowforPageBS(mode , obj)
 {
    if(obj.value == "" || checkCrdef(obj)==true)//No Need To Again Get The List
  	{  	
		var hmode = "FETCHPATIENTLISTINGMODIFICATIONBS"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+mode+"&searchString="+''+"&searchType="+1+"&fromRow="+1+"&rowPerPage="+10+"&ctBlockSet="+1;
		ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");		 
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
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+document.forms[0].patListType.value+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
	  	ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
  }

//Used In Patient Admission Modification Process
  function fetchPatientListmodification(fromRows,blockSet) 
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
	  	
	  	var hmode = "FETCHPATIENTLISTINGMODIFICATION"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+8+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
	  	ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
  }
  
  /**
   * getPatientListDetailAjaxResponse
   * @param {String} res
   * @param {String} mode  
   */
   function getPatientListDetailAjaxResponse(res , mode) 
   {
		var fun1="openMenu('ADMNEW','')";
		var fun2="openMenu('NEWBORNBABYADM','')";
		var menu=   "<div class='dropdown'>"+
			"<button class='btn btn-primary dropdown-toggle' style='background: #3ac9d6 !important;border:0;' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"+
			"Add New"+
			"</button>"+
			"<div class='dropdown-menu' style='background: rgb(238, 242, 242)' aria-labelledby='dropdownMenuButton'>"+			
			"<a class='dropdown-item' onclick="+fun1+" href='#'><i class='fa fa-user-plus'></i>&nbsp; New Admission</a>"+			
			"<div class='dropdown-divider'></div>"+
			"<a class='dropdown-item' onclick="+fun2+" href='#'><i class='fa fa-child'></i>&nbsp;New Born Baby Admission</a>"+
			"</div>"+			
			"</div>"+
			"</div>";
	   
	   
   		if(mode == '1')
   		{
			var objEle = document.getElementById("fetchRecordDivId");
			objEle.innerHTML = res;
			
			//$("#Datatable").DataTable();
			
			
			$(document).ready(function() {
				
			
				
			    $('#Datatable').DataTable({
			    	
			   	dom: '<<"adm">f<tp>l>',
			    	
			    	fnInitComplete: function(){
			    		
			    		$('div.adm').html(menu);
			          /*  $('div.adm').html('<span id="admissionSpan"><button style="background: #3ac9d6 !important;border:0;" class="btn btn-primary" onclick='+fun1+' >New Admission <i class="fas fa-plus-circle"></button></i></span>'+
			            		'<span id="NewBornSpan" style="margin-left:10px;"><button style="background: #3ac9d6 !important;border:0;" class="btn btn-primary" onclick='+fun2+' >New Born Baby Admission <i class="fas fa-plus-circle"></button></i></span>');*/
			          },
			    	
			    	
			    	language: {
			    	    paginate: {
			    	      next: "<i class='fas fa-angle-right'>",
			    	      previous: "<i class='fas fa-angle-left'>"
			    	    }
			    	  },
			             
			              "scrollY":"25rem",
			              "ordering": false,
			              "info":true,
			              "paging":true,
			              
			      
			    	});	
			                 $(".dataTables_scrollHeadInner").css({"width":"98.85%"});
			                 $(".table ").css({"width":"100%"});
			                 $("#Datatable_info ").css({"padding":"0.6rem"});
			                 
			                 $('#dtable_filter input').css('border-radius','5px');
			                 $('div.adm').css('float','left');
			                 
			                 

			                 /*popup toast*/
			                 $(".popupToast").hover(function () {
			                 	  var popup=$(this).find(".popuptextToast")[0];
			                 	  popup.classList.toggle("show");
			                 	 /* 
			                 	  * for dynamic arrow --check later
			                 	  * 
			                 	  * if(popup.offsetWidth>100)
			                 		  	popup.style.marginLeft=-(popup.offsetWidth/2);*/
			                 	});

			                 /* end popup toast*/
			                
			                    
			                   
			                
			    });
		
		//	resize_popUp_Search();
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
					var allImageObj = window.opener.document.getElementsByTagName("img");
					for(var nTmpI=0; nTmpI<allImageObj.length; nTmpI++){
						if(allImageObj[nTmpI].src.split("/")[allImageObj[nTmpI].src.split("/").length-1]=="Go.png"){
							allImageObj[nTmpI].onclick();
						}
					}
					
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
	 * getSearchPatListBySearch-On click of Serach Button on Listing popup
	 * @param {Event} eve 
	 */
	 function getSearchPatListBySearch() 
	 {
	 	var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
	 	if (searchType == "1") 
	 	{
	 		
	 				var hisValidator = new HISValidator("ipdBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter CR Number" );
					//hisValidator.addValidation("strSearchString", "numgt=0", "CR Number Should be a Number" );
					var retVal = hisValidator.validate(); 
					if(!validatePositiveIntegerValue(document.getElementsByName("strSearchString")[0].value))
					{
						alert("CR No. Should be Numeric Only");
						document.getElementsByName("strSearchString")[0].focus();
						retVal=false;
					}
					hisValidator.clearAllValidations();
		
					if(retVal)
					{
						fetchPatientList('1','1');
	 				}
	 				else
	 				{
						return false;
					}
	 		}
	 		else
	 		{	 			
	 				var hisValidator = new HISValidator("ipdBean");	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Patient Name" );
				
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal)
					{
						if(validateThroughRegExp(document.forms[0].strSearchString,2))
							fetchPatientList('1','1');
	 				}
	 				else
	 				{
						return false;
					}
	 		}
	 		 return false;	 		
	 	} 



	 function getSearchPatListBySearchforpatientmodification() {
	 	var searchType =  document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
	 	
	 	if (searchType == "1") {
	 		
	 				var hisValidator = new HISValidator("patientAdmissionModiTransBean");  
	
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
						fetchPatientListmodification('1','1');
	 				}else{
						return false;
					}
	 		}else{
	 			
	 			var hisValidator = new HISValidator("patientAdmissionModiTransBean");  
	
					hisValidator.addValidation("strSearchString", "req", "Please Enter Patient Name" );
				
	
					var retVal = hisValidator.validate(); 
					hisValidator.clearAllValidations();
		
					if(retVal){
					if(validateThroughRegExp(document.forms[0].strSearchString,2))
						fetchPatientListmodification('1','1');
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
	 		
	 				var hisValidator = new HISValidator("patientAdmissionModiTransBean");  
	
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
function closePopup()
{
	 //alert(gblCRValue);
	 //alert(document.getElementsByName("gblCRValue")[0].value);
	 window.opener.document.getElementsByName("strCrNo")[0].value=document.getElementsByName("gblCRValue")[0].value;	
	 window.opener.SetCursorToTextEnd('strCrNoId');
	 window.close();
}


function fetchPatientList_BS(fromRows,blockSet) 
{
	
		var convString = "";
		var searchString="";
		var searchType="";
		/*var searchString = document.forms[0].strSearchString.value ;
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
		 //	ajaxFunction2(url,"1","getPatientListDetailAjaxResponse");
		 	*/	
	  	
	  	var hmode = "FETCHPATIENTLISTINGBS"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+8+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
	
	  	$("#fetchRecordDivId").load(createFHashAjaxQuery(url));

}



function fetchPatientList_BS1(fromRows,blockSet,menu) 
{
	
		var convString = "";
		var searchString="";
		var searchType="";
		var patlist="8";
	
		
		if(menu=="Discharge")
			{
				patlist="5";
			}
	  	
	  	var hmode = "FETCHPATIENTLISTINGBS"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+patlist+"&searchString="+convString+"&searchType="+searchType+"&fromRow="+fromRows+"&rowPerPage="+gblRowPerPage+"&ctBlockSet="+blockSet;
	
	  	$("#fetchRecordDivId").load(createFHashAjaxQuery(url));

}

function setCrNoModal() {
  	
		var crNoObj = document.getElementsByName("strCrNoBs");
		
		
		for(var i=0;i<crNoObj.length;i++)
			{
				if(crNoObj[i].checked==true)
					{
						document.getElementById("strCrNoId").value=  crNoObj[i].value;
						goFunc();
					}
					 
			}
	
		
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
         
          "scrollY":"12rem",
          "ordering": false,
          "info":true,
          "paging":true
  
	});	
             $(".dataTables_scrollHeadInner").css({"width":"100%"});
             $(".table ").css({"width":"100%"});
                $('#dtable_filter input').css('border-radius','5px');
                $('.table td').css('padding','0.5rem');
                $('.table tr').hover(function(){
                	$(this).css('background','rgb(196, 242, 242,0.4)');
                }, function(){
                	$(this).css('background','none');
                });
                
            
               
                
	});
}

/*$(document).ready(function() {
	$('#Datatable').DataTable();	
	            
	 });*/
function openMenu(hmode,cr){
	console.log("cr:::::::"+cr);
	document.forms[0].hmode.value=hmode;
	if(document.forms[0].isSingleMenu.value!="1")
		document.forms[0].strPatientCrNo.value=cr;
	document.forms[0].submit();
	}



function setSingleWindow(){
	console.log("single window");
	/*$("#cancelButton").prop("onclick", null).off("click");*/
	$("#cancelButton").attr("onclick","openMenu('SIGLEMENUHOME','')");
	
}


function tmp(i){
	//console.log("called:::::::::::::");
	//swal(i);
}
//Used In Admission Modification Process-mode=8
function jobTracking(mode)
{
		var hmode = "JOBTRACKING"; 
	  	var url = "IpdCNT.cnt?hmode="+hmode+"&patListType="+mode+"&searchString="+''+"&searchType="+1+"&fromRow="+1+"&rowPerPage="+10+"&ctBlockSet="+1;
		ajaxFunction2(url,"1","jobTrackingResponse");	 	
}

function jobTrackingResponse(res , mode) 
{
		if(mode == '1')
		{
			var objEle = document.getElementById("fetchRecordDivId");
			objEle.innerHTML = res;
			
			$(document).ready(function() 
			{				
			    $('#dtable').DataTable(
			    {
			    	
				    	language: 
				    	{
				    	    paginate: 
				    	    {
				    	      next: "<i class='fas fa-angle-right'>",
				    	      previous: "<i class='fas fa-angle-left'>"
				    	    }
				    	  },			             
			              "scrollY":"25rem",
			              "ordering": false,
			              "info":true,
			              "paging":true,			      
			    	});	
			                 $(".dataTables_scrollHeadInner").css({"width":"98.85%"});
			                 //$(".table ").css({"width":"100%"});
			                 $(".table ").css({"border-collapse": "collapse"});
			                 $("#Datatable_info ").css({"padding":"0.6rem"});
			                 
			                 //$('#dtable_filter input').css('border-radius','5px');
			                 //$('div.adm').css('float','left');
			                 
			                 

			                 /*popup toast*/
			                 $(".popupToast").hover(function () {
			                 	  var popup=$(this).find(".popuptextToast")[0];
			                 	  popup.classList.toggle("show");
			                 	 /* 
			                 	  * for dynamic arrow --check later
			                 	  * 
			                 	  * if(popup.offsetWidth>100)
			                 		  	popup.style.marginLeft=-(popup.offsetWidth/2);*/
			                 	});

			                 /* end popup toast*/
			                
			                    
			                   
			                
			    });
		
		//	resize_popUp_Search();
		}   	
}