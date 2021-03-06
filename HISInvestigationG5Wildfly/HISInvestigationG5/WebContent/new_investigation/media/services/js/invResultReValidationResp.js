/*------------------------------------------Set Automaticly---------------------------------------------------------------------------------------------------*/

/*-Set Date time picker on from , to date-*/

$(document).ready(function() {
  $('#datetimepicker-from').datetimepicker({
    format: 'DD-MMM-YYYY',
    showTodayButton: true,
    //showClear: true,
    icons: {
      today: 'fas fa-calendar-day',
      previous: "fa fa-caret-left",
      next: "fa fa-caret-right ",
      //up: "fa fa-arrow-up",
      //down: "fa fa-arrow-down",
      //date: "fa fa-calendar",
      //clear: "fa fa-clear",
      //close: "fa fa-close"
    },
    useCurrent: true //may cause problem see datetimepicker issue #1075
  });

  $('#datetimepicker-to').datetimepicker({
    format: 'DD-MMM-YYYY',
    showTodayButton: true,
    //showClear: true,
    icons: {
      today: 'fas fa-calendar-day',
      previous: "fa fa-caret-left",
      next: "fa fa-caret-right ",
    },
    useCurrent: true
  });

  $("#datetimepicker-from").on("dp.change", function(e) {
    $('#datetimepicker-to').data("DateTimePicker").minDate(e.date);
  });
  $("#datetimepicker-to").on("dp.change", function(e) {
    $('#datetimepicker-from').data("DateTimePicker").maxDate(e.date);
  });
});

/*-Returns system date-*/
function setDate() {
  var today = new Date();
  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

  var DD = today.getDate();
  var M = months[today.getMonth()];
  var YYYY = today.getFullYear();

  today = DD + "-" + M + "-" + YYYY;
  return today;
  //$(".todayDate")[0].val(today);
  //$(".todayDate")[1].val(today);
}



/*------------------------------------------Set Automaticly---------------------------------------------------------------------------------------------------*/


/*------------------------------------------Event Actions starts----------------------------------------------------------------------------------------------*/

/*----------On Change in input type Cr.NO Or Bill No. Or Sample No. starts--------------------*/
$(document).ready(function() {

  $('#inputFilterType').change(function() {
    var inputTypeBCSL = $(this).val();

    /*search made on bill no.*/
    if (inputTypeBCSL == "1") {
    	$('#billNoInput').addClass("d-none");
        $('#crNoInput').removeClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#inputFilterTypeHelp').html("Search Based On Cr No.");
        showLoading(false);
    }
    /*search made on Cr. no.*/
    else if (inputTypeBCSL == "2") {
    	$('#billNoInput').removeClass("d-none");
        $('#crNoInput').addClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#inputFilterTypeHelp').html("Search Based On Bill No.");
        showLoading(false);
    }
    /*search made on sample. no.*/
    else if (inputTypeBCSL == "3") {
      $('#billNoInput').addClass("d-none");
      $('#crNoInput').addClass("d-none");
      $('#sampleNoInput').removeClass("d-none");
      $('#labNoInput').addClass("d-none");
      $('#inputFilterTypeHelp').html("Search Based On Sample No.");
      showLoading(false);
    }
    /*search made on sample. no.*/
    else if (inputTypeBCSL == "4") {
      $('#billNoInput').addClass("d-none");
      $('#crNoInput').addClass("d-none");
      $('#sampleNoInput').addClass("d-none");
      $('#labNoInput').removeClass("d-none");
      $('#inputFilterTypeHelp').html("Search Based On Lab No.");
      showLoading(false);
    }
  });
});
/*--------------------------------------------------------------------------------------------*/
/*----------On Click Getdata Button starts----------------------------------------------------*/
$(document).ready(function() {

  $('#getData').click(function() {

    showLoading(true);
    saveBtnsAnimations(false, false);
    var searchType = $('#inputFilterType').val();
    
    var dateOrFilter="0";
    if($( "input[id=dateOrFilterCheck]:checked" ).length) { dateOrFilter="1" }
    setglobalSearchParam();
    
    var flagGoodToGo = false;
    
    if(dateOrFilter=="1"){
	    /*1 for cr.no.*/
	    if (searchType == 1) {
	    	
	    	var crNo = $('#crNoInput').val();
	        if (crNo.length == 15) {
	        	flagGoodToGo = true;
	        } else {
	        	alert("Cr. No. Should Be Of 15 Digits");
		        $('#crNoInput').focus();
		        flagGoodToGo = false;
		        showLoading(false);
	        }
	    }
	    /*2 for bill.no.*/
	    else if (searchType == 2) {
	    	
	    	var billNo = $('#billNoInput').val();
	        if (billNo.length == 15) {
	        	flagGoodToGo = true;
	        } else {
	          alert("Bill No. Should Be Of 15 Digits");
	          $('#billNoInput').focus();
	          flagGoodToGo = false;
	          showLoading(false);
	        }
	    }
	    /*3 for sample.no.*/
	    else if (searchType == 3) {
	    	
	    	var sampleNo = $('#sampleNoInput').val();
	    	if (sampleNo.length <= 10) {
	    		flagGoodToGo = true;
	    	} else {
	    		alert("Sample No. Should Be Less Then 10 Digits");
	    		$('#sampleNoInput').focus();
	    		flagGoodToGo = false;
	    		showLoading(false);
	    	}
	    }
	    /*4 for Lab.no.*/
	    else if (searchType == 4) {
	    	
	    	var labNo = $('#labNoInput').val();
	    	if (labNo.length <= 10) {
	    		flagGoodToGo = true;
	    	} else {
	    		alert("Lab No. Should Be Less Then 10 Digits");
	    		$('#sampleNoInput').focus();
	    		flagGoodToGo = false;
	    		showLoading(false);
	    	}
	    }
	    
	    if(flagGoodToGo==true){
	    	$('#allValReqnList').addClass("d-none");
	    	
	    	$('#patDetails').removeClass("d-none");
	    	$('#reValidationReqnListCont').removeClass("d-none");
        	$('#patValReqnList').removeClass("d-none");
        	
        	$('#DataTable11').DataTable().clear().destroy();
        	$('#DataTable12').DataTable().clear().destroy();
        	var table=$('#DataTable12').DataTable({});
        	
	    	AjaxGetPatDetails(globalSearchParam);
        	AjaxGetReValidationReqnList(globalSearchParam);
	    }
	    
  	} else {
  		$('#patDetails').addClass("d-none");
  		$('#patValReqnList').addClass("d-none");
  		
  		$('#reValidationReqnListCont').removeClass("d-none");
  		$('#allValReqnList').removeClass("d-none");
  		
    	$('#DataTable11').DataTable().clear().destroy();
    	$('#DataTable12').DataTable().clear().destroy();
    	var table=$('#DataTable11').DataTable({});
    	
    	AjaxGetReValidationReqnList(globalSearchParam);
  	}
  });
});
/*------------------------------------------------------*/


$(document).ready(function (){
	$( "input[id=dateOrFilterCheck]" ).off().on("click", function(){
		if($(this).is(":checked")){
			$("#inputFilterType").attr({"disabled":false});
			$("#crNoInput").attr({"disabled":false});
			$("#billNoInput").attr({"disabled":false});
			
			 var inputTypeBCSL =  $('#inputFilterType').val();

			    /*search made on bill no.*/
			if (inputTypeBCSL == "1") {
				$("#inputFilterTypeHelp").html("Search Based On Cr No.");
			} else if (inputTypeBCSL == "2") {
				$("#inputFilterTypeHelp").html("Search Based On Bill No.");
			} else if (inputTypeBCSL == "3") {
				$("#inputFilterTypeHelp").html("Search Based On Sample No.");
			} else if (inputTypeBCSL == "4") {
				$("#inputFilterTypeHelp").html("Search Based On Lab No.");
			}
			//$("#inputFilterTypeHelp").html("Search Based On Bill No.");
			
			//$( "input[id=dateOrFilterCheck]:checked" ).length)
		} else if ($(this).is(":not(:checked)")){
			$("#inputFilterType").attr({"disabled":true}); 
			$("#crNoInput").attr({"disabled":true});
			$("#billNoInput").attr({"disabled":true});
			$("#inputFilterTypeHelp").html("Disabled");
		}
	});
});


/*----------Function to Set Events Listeners After Requisition List Table Fetched Starts--------*/

function setEventListenersAfterReqnListTableDrawn(){
	
  if($('.viewTemplBtn').length){
  	$('.viewTemplBtn').off().on("click", function (){
  		var viewTemplBtnVal = jQuery.parseJSON( $(this).attr("key") );
  		 openTemplateIFrame(viewTemplBtnVal);
  	});
  }

  if($('.compareBtn').length){
  	$('.compareBtn').off().on("click", function (){
  		var compareBtnVal = jQuery.parseJSON( $(this).attr("key") );
  		openInvTrackCompareIFrame(compareBtnVal);
  	});
  }
  
  if($('.rowCheckBoxes').length){
	  $('.rowCheckBoxes').off().on("click", function (){
		  if($(".rowCheckBoxes:checked").length){
			  saveBtnsAnimations(true, false);
		  } else {
			  saveBtnsAnimations(false, false);
		  }
	  });
  }
  

}

function saveBtnsAnimations(enableFlag, animateFlag){
	if(enableFlag==true){
		$('#saveReValidateBtn').attr({"disabled":false});
		  $('#modifyBtn').attr({"disabled":false});
		  
		  tippyLoadingUtlInstance.saveValBtnDiv.setContent('Click to ReValidate Selected Requisition');
		  tippyLoadingUtlInstance.modifyValBtnDiv.setContent('Click to Modify Selected Requisition');
	}else{
		$('#saveReValidateBtn').attr({"disabled":true});
		  $('#modifyBtn').attr({"disabled":true});
		  
		  tippyLoadingUtlInstance.saveValBtnDiv.setContent('Please select atleast one Requisition to ReValidate');
		  tippyLoadingUtlInstance.modifyValBtnDiv.setContent('Please select atleast one Requisition to Modify');
	}
}



/*----------Function to Set Events Listeners After Requistion List Table Fetched Starts--------*/
var globalSearchParam={collAreaCode:"", labCode:"", reValidationStatusCode:"", dateOrFilter:"", dateTypeCode:"",
						searchType:"", fromDate:"", toDate:"", crNo:"", billNo:"", sampleNo:"", labNo:"" };

function setglobalSearchParam(){
	globalSearchParam.collAreaCode = $('#collAreaSelectInput').val();
	globalSearchParam.labCode = $('#labSelectInput').val();
	globalSearchParam.reValidationStatusCode = $('#valStatusSelectInput').val();

	var dateOrFilter="0";
	if($( "input[id=dateOrFilterCheck]:checked" ).length) { dateOrFilter="1" }
	globalSearchParam.dateOrFilter = dateOrFilter;
	
	globalSearchParam.dateTypeCode = $('#dateTypeSelectInput').val();
	globalSearchParam.searchType = $('#inputFilterType').val();
	globalSearchParam.fromDate = $('.fromDateInput').val();
	globalSearchParam.toDate = $('.toDateInput').val();
	globalSearchParam.crNo = $('#crNoInput').val();

	console.log(globalSearchParam);

}

$(document).ready(function(){

	var tippy_labSelectInputGroup=tippy('.labSelectInputGroup', {
					    delay: 100,
					    arrow: true,
					    arrowType: 'round',
					    size: 'large',
					    duration: 500,
					    animation: 'shift-away-extreme',
					    placement: 'bottom',
					    allowHTML: true,
					    content: '',
					});

	var tippy_saveValBtn=tippy('.saveReValidateBtnDiv', {
					    delay: 100,
					    arrow: true,
					    arrowType: 'round',
					    size: 'large',
					    duration: 500,
					    animation: 'shift-away-extreme',
					    placement: 'top',
					    allowHTML: true,
					    content: 'Please select tleast one Requisition to ReValidate',
					});
	
	var tippy_modifyValBtn=tippy('.modifyBtnDiv', {
					    delay: 100,
					    arrow: true,
					    arrowType: 'round',
					    size: 'large',
					    duration: 500,
					    animation: 'shift-away-extreme',
					    placement: 'top',
					    allowHTML: true,
					    content: 'Please select atleast one Requisition to Modify',
					});
	
	var tippy_dateOrFilterCheck=tippy('.dateOrFilterDiv', {
	    delay: 100,
	    arrow: true,
	    arrowType: 'round',
	    size: 'small',
	    duration: 300,
	    animation: 'shift-away-extreme',
	    placement: 'top',
	    allowHTML: true,
	    content: 'Turn On/Off filtered Search',
	});
	
	
	tippy_labSelectInputGroup[0].disable();

	tippyLoadingUtlInstance.labSelectInputGroup=tippy_labSelectInputGroup[0];
	
	tippyLoadingUtlInstance.saveValBtnDiv=tippy_saveValBtn[0];
	tippyLoadingUtlInstance.modifyValBtnDiv=tippy_modifyValBtn[0];

	//tippyLoadingUtlInstance.tippy_labSelectInputGroup.enable();
	//tippyLoadingUtlInstance.tippy_getDataBtnDiv.enable();
	//tippyLoadingUtlInstance.tippy_labSelectInputGroup.show();
});

function getCollAreaNLabListOnLoad(){
	AajaxGetCollAreaNLabList();
}

$(document).ready(function(){
	$('#collAreaSelectInput').change(function(){
		if(globalSearchParam.collAreaCode==$(this).val()){
			$('#labSelectInput').removeAttr("disabled");
			$('#getData').removeAttr("disabled");
			tippyLoadingUtlInstance.labSelectInputGroup.disable();
			tippyLoadingUtlInstance.getDataBtnDiv.disable();
		}else if($(this).val()=="-1"){
			$('#labSelectInput').attr({"disabled":"disabled"});
			$('#getData').attr({"disabled":"disabled"});
			showLoading(false);

			tippyLoadingUtlInstance.labSelectInputGroup.setContent('<strong>Select Any Collection <span style="color: aqua;">Area </span>To Enable This</strong>');
			tippyLoadingUtlInstance.labSelectInputGroup.enable();

			tippyLoadingUtlInstance.getDataBtnDiv.setContent('<strong>Select Any Collection <span style="color: aqua;">Area </span>To Enable This</strong>');
			tippyLoadingUtlInstance.getDataBtnDiv.enable();

		}else{
			$('#labSelectInput').removeAttr("disabled");
			$('#getData').removeAttr("disabled");
			showLoading(true);
			saveBtnsAnimations(false, false);

			tippyLoadingUtlInstance.labSelectInputGroup.disable();

			$('#labSelectInput').empty();

			globalSearchParam.collAreaCode=$(this).val();
			AjaxGetLabList(globalSearchParam);
		}
	});
});

$(document).ready(function(){
	$('#labSelectInput').change(function(){
		if(globalSearchParam.labCode==$(this).val()){
			$('#getData').removeAttr("disabled");
			tippyLoadingUtlInstance.getDataBtnDiv.disable();
		}else if($(this).val()=="-1"){
			$('#getData').attr({"disabled":"disabled"});
			showLoading(false);
			tippyLoadingUtlInstance.getDataBtnDiv.setContent('<strong>Select Any<span style="color: aqua;"> Lab Name </span>To Enable This</strong>');
			tippyLoadingUtlInstance.getDataBtnDiv.enable();
		}else{
			$('#getData').removeAttr("disabled");
			tippyLoadingUtlInstance.getDataBtnDiv.disable();
			globalSearchParam.labCode=$(this).val();
		}
	});
});



/*------------------------------------------Event Actions Ends------------------------------------------------------------------------------------------------*/


/*------------------------------------------Ajax Calls Starts-------------------------------------------------------------------------------------------------*/


var globalCollAreaList = null;
var globalLabList = null;
var globalReValidationReqnList = null;
var globalAjaxObjectArr ={};

function AajaxGetCollAreaNLabList(){
	globalCollAreaList = null;
	var _mode = "AajaxGetCollAreaNLabList";

	  var url = "/HISInvestigationG5/new_investigation/invResultValidationResp.cnt?hmode=" + _mode;

	  globalAjaxObjectArr.AajaxGetCollAreaList=$.getJSON(url, function(data) {
		  if(!$.isEmptyObject(data)) {

			  if(data.isSuccess=="1"){
				  globalCollAreaList = data.collAreaList;
				  globalLabList = data.labList;

				  setCollAreaList(data);
				  setLabList(data);
			  }else{
				  console.log("%cAajaxGetCollAreaNLabList Failed | ResponseError Is Below", "color:red;");
			      console.log(data.error);
			  }

	      }
	    })
	    .done(function(data) {
	      console.log("%cAajaxGetCollAreaNLabList success | ResponseData Is Below", "color:green;");
	      console.log(data);
	    })
	    .fail(function( jqxhr, textStatus, error ) {
	      console.log("%cAajaxGetCollAreaNLabList Failed | ResponseError Is Below", "color:red;");
	      var err = textStatus + ", " + error;
	      console.log( "Request Failed: " + err );
	    });
}

function AjaxGetLabList(globalSearchParam){
	var collAreaCode=globalSearchParam.collAreaCode;

	globalLabList = null;
	var _mode = "AjaxGetLabList";

	  var url = "/HISInvestigationG5/new_investigation/invResultValidationResp.cnt?hmode=" + _mode + "&collAreaCode=" + collAreaCode;

	  globalAjaxObjectArr.AjaxGetLabList=$.getJSON(url, function(data) {
	    if(!$.isEmptyObject(data)) {

			  if(data.isSuccess=="1"){
				  globalLabList = data.labList;

				  setLabList(data);
			  }else{
				  console.log("%cAjaxGetLabList Failed | ResponseError Is Below", "color:red;");
			      console.log(data.error);
			  }

	      }
	    })
	    .done(function(data) {
	      console.log("%cAjaxGetLabList success | ResponseData Is Below", "color:green;");
	      console.log(data);
	    })
	    .fail(function( jqxhr, textStatus, error ) {
	      console.log("%cAjaxGetLabList Failed | ResponseError Is Below", "color:red;");
	      var err = textStatus + ", " + error;
	      console.log( "Request Failed: " + err );
	    });
}


function AjaxGetReValidationReqnList(globalSearchParam){
	var collAreaCode=globalSearchParam.collAreaCode;
	var labCode = globalSearchParam.labCode;
	var reValidationStatusCode = globalSearchParam.reValidationStatusCode
	var dateOrFilter= globalSearchParam.dateOrFilter
	var dateTypeCode= globalSearchParam.dateTypeCode
	var fromDate= globalSearchParam.fromDate
	var toDate= globalSearchParam.toDate
	var crNo= globalSearchParam.crNo

	globalReValidationReqnList = null;
	var _mode = "AjaxGetReValidationReqnList";

	  var url = "/HISInvestigationG5/new_investigation/invResultReValidationResp.cnt?hmode=" + _mode + "&collAreaCode=" + collAreaCode + "&labCode="
	  + labCode + "&reValidationStatusCode=" + reValidationStatusCode + "&dateOrFilter=" + dateOrFilter + "&dateTypeCode=" + dateTypeCode + "&fromDate=" + fromDate
	  + "&toDate=" + toDate + "&crNo=" + crNo;

	  globalAjaxObjectArr.AjaxGetReValidationReqnList=$.getJSON(url, function(data) {
	    if(!$.isEmptyObject(data)) {

			  if(data.isSuccess=="1"){
				  globalReValidationReqnList = data.reValidationReqnList;
				  
				  if(globalSearchParam.dateOrFilter=="1"){
					  dataTablePatReValidationReqnList(data.reValidationReqnList);
				  } else {
					  dataTableReValidationReqnList( data.reValidationReqnList);  
				  }
			  }else{
				  console.log("%cAjaxGetReValidationReqnList Failed | ResponseError Is Below", "color:red;");
			      console.log(data.error);
			  }

	      }
	    })
	    .done(function(data) {
	      console.log("%cAjaxGetReValidationReqnList success | ResponseData Is Below", "color:green;");
	      console.log(data);
	    })
	    .fail(function( jqxhr, textStatus, error ) {
	      console.log("%cAjaxGetReValidationReqnList Failed | ResponseError Is Below", "color:red;");
	      var err = textStatus + ", " + error;
	      console.log( "Request Failed: " + err );
	    });
}


function AjaxReValidateReqnResult(globalSelectedReqnData){
	var concatSelectedReqnData = globalSelectedReqnData.concatSelectedReqnData;
	var concatChkSendToMachine = globalSelectedReqnData.concatChkSendToMachine;
	//var isPatDetailPage = "1";
	
	var requestData = {hmode:"AjaxReValidateReqnResult", selectedCheckbox:concatSelectedReqnData, chkSendToMachine:concatChkSendToMachine}
			
	var url = "/HISInvestigationG5/new_investigation/invResultReValidationResp.cnt?";
	
	  globalAjaxObjectArr.AjaxReValidateReqnResult=$.getJSON(url, requestData, function(data) {
	    if(!$.isEmptyObject(data)) {

			  if(data.isSuccess=="1") {
				  removeDatatableRowsOnSave();
				  showToastifyMsg("Test Result ReValidated", true, 4000);
				 
			  } else {
				  showToastifyMsg("Test Result ReValidation Failed", false, 4000);
				  console.log("%cAjaxReValidateReqnResult Failed | ResponseError Is Below", "color:red;");
			      console.log(data.error);
			  }

	      }
	    })
	    .done(function(data) {
	      console.log("%cAjaxReValidateReqnResult success | ResponseData Is Below", "color:green;");
	      console.log(data);
	    })
	    .fail(function( jqxhr, textStatus, error ) {
	      console.log("%cAjaxReValidateReqnResult Failed | ResponseError Is Below", "color:red;");
	      var err = textStatus + ", " + error;
	      console.log( "Request Failed: " + err );
	    });
	  
}


function openInvTrackCompareIFrame(compareBtnVal){
	var crNo=compareBtnVal.crNo;
	var requisitionNo=compareBtnVal.requisitionNo;
	var requisitionDNo=compareBtnVal.requisitionDNo;
	var testCode=compareBtnVal.testCode;
	var groupCode=compareBtnVal.groupCode;

	var isGroup="0";
  if(groupCode!="" && groupCode!=null && groupCode!="null"){isGroup="1"}

  var _mode = "UrlExternalCall";
  var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&crNo="+crNo+"&searchType=1"+"&isGroup="+isGroup
  +"&testCode="+testCode+"&groupCode="+groupCode+"&requisitionDNo="+requisitionDNo+"&requisitionNo="+requisitionNo+"&showGraph=0";
  $.fancybox.destroy();
  $(".fancyBoxIFrame").attr({"href":url});
  $(".fancyBoxIFrame").click();

}

function openTemplateIFrame(viewTemplBtnVal){
	var crNo=viewTemplBtnVal.crNo;
	var requisitionNo=viewTemplBtnVal.requisitionNo;
	var requisitionDNo=viewTemplBtnVal.requisitionDNo;
	var testCode=viewTemplBtnVal.testCode;
	var groupCode=viewTemplBtnVal.groupCode;

	var isGroup="0";
	if(groupCode!="" && groupCode!=null && groupCode!="null"){isGroup="1"}

	var res = crNo+"%23"+requisitionNo+"%23"+requisitionDNo+"%23"+groupCode;
 	var hmode="GetReqnDetailsNonEditable";
	var isPatDetailPage="1";
	var ispreview="2";
	var url="/HISInvestigationG5/new_investigation/invResultReValidationResp.cnt?hmode="+hmode+"&isPatDetailPage="+isPatDetailPage+"&selectedCheckbox="+res+"&ispreview="+ispreview;
	$.fancybox.destroy();
  $(".fancyBoxIFrame").attr({"href":url});
  $(".fancyBoxIFrame").click();

}

function openModifyResultIFrame(globalSelectedReqnData){
	var concatSelectedReqnData = globalSelectedReqnData.concatSelectedReqnData;
	var concatChkSendToMachine= globalSelectedReqnData.concatChkSendToMachine;
	
	var valstusCod = globalSearchParam.reValidationStatusCode;
	var requestData = {hmode:"GetReqnDetailsEditable", selectedCheckbox:concatSelectedReqnData, chkSendToMachine:concatChkSendToMachine, newEntry:valstusCod};
	var requestDataStrlUrlEncoded = $.param(requestData);
	//alert(requestDataStrlUrlEncoded);
	var url = "/HISInvestigationG5/new_investigation/invResultReValidationResp.cnt?";
	url += requestDataStrlUrlEncoded;
	$.fancybox.destroy();
  $(".fancyBoxIFrame").attr({"href":url});
  $(".fancyBoxIFrame").click();

}


$(document).ready(function() {
    $(".fancyBoxIFrame").fancybox({
    	//closeExisting: false,
    	type: 'iframe',
    	toolbar  : false,
    	smallBtn : true,
    	transitionEffect: "zoom-in-out",
    	transitionDuration: 366,
    	iframe : {
    		// Iframe template
    	    //tpl: '<iframe id="fancybox-frame{rnd}" name="fancybox-frame{rnd}" class="fancybox-iframe" allowfullscreen allow="autoplay; fullscreen" src=""></iframe>',
    		preload : true,
    		css : {
//    			'width':$fancyboxWidth,
//    	        'height':$fancyboxHeight,
    	        'width'  : '100%',
    	        'height' : '100%',
            	}
    	},
    	btnTpl: {
    		    close:
    		      '<button data-fancybox-close type="button"  class="fancybox-button fancybox-button--close" title="{{CLOSE}}">' +
    		      '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
    		      "</button>",

    		      smallBtn:
    		          '<button data-fancybox-close type="button"  class="fancybox-button fancybox-close-small " title="{{CLOSE}}">' +
    		          '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
    		          "</button>"
    	},
    	afterClose: function( instance, current ) {
    		actionOnFancyClose();
    	}
    });
});
/*------------------------------------------Ajax Calls Ends---------------------------------------------------------------------------------------------------*/



/*------------------------------------------DataTables Initialization starts----------------------------------------------------------------------------------*/

function setCollAreaList(data){
	var collAreaList=data.collAreaList;

	$('#collAreaSelectInput').empty();

	var collectionAreaCount = Object.keys(collAreaList).length;

	if(collectionAreaCount=="1"){
		for(i in collAreaList){
			for(key in collAreaList[i]){
				$('#collAreaSelectInput').append('<option selected value="'+key+'">'+collAreaList[i][key]+'</option>');
			}
		}
	}else{
		$('#collAreaSelectInput').append('<option value="-1">Choose Collection Area</option>');
		$('#collAreaSelectInput').append('<option selected value="0">All Collection Area</option>');

		for(i in collAreaList){
			for(key in collAreaList[i]){
				$('#collAreaSelectInput').append('<option value="'+key+'">'+collAreaList[i][key]+'</option>');
		}}
	}
}

function setLabList(data){
	var labList = data.labList;

	$('#labSelectInput').empty();

	//var labCount = Object.keys(labList).length;
	var labCount = labList.length;

	if(labCount=="1"){
		for(i in labList){
			for(key in labList[i]){
				$('#labSelectInput').append('<option selected value="'+key+'">'+labList[i][key]+'</option>');
			}
		}
	}else{
		$('#labSelectInput').append('<option value="-1">Choose Lab</option>');
		$('#labSelectInput').append('<option selected value="0">All Labs</option>');

		for(i in labList){
			for(key in labList[i]){
				$('#labSelectInput').append('<option value="'+key+'">'+labList[i][key]+'</option>');
		}}
	}
	showLoading(false);
}



var globalDatablesObject ={};
function dataTableReValidationReqnList(reValidationReqnList) {
	  var groupColumn = 2;
	  $('#DataTable11').DataTable().clear().destroy();

	  $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
var table = $('#DataTable11').DataTable({

		  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
      processing: true,
		  "scrollY":        "450px",
		  "scrollCollapse": true,
		  "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	    "language": { "emptyTable": "No Data Is Available " },
      "select": {
          style:    'multi', /*multi+shift | single | api | os | multi*/
          selector: 'td:nth-child(2) .rowCheckBoxes',
          blurable: true,
        },
      "sPaginationType": "full_numbers",
		  "bJQueryUI": true,
      buttons: [{
	    	  extend: 'collection',
	    	  text: '',
	    	  className: "fas fa-cogs text-primary btn-lg bg-white btn-outline-light",
	    	  buttons: [
	    		  { extend: 'excel',
	    			  title: 'Investigation ReValidation',
	    			  text: ' Download Excel',
	    			  className: "fas fa-file-excel text-primary bg-white btn-outline-light"
	    		  },
	    		  { extend: 'pdfHtml5',
	    			  className: "fas fa-file-pdf text-primary bg-white btn-outline-light",
	    			  title: 'Investigation ReValidation',
	    			  text: ' Download Pdf',
	    			  pageMargins: [0, 0, 0, 0],
	    			  margin: [0, 0, 0, 0],
	    			  alignment: 'center',
	    			  exportOptions: {
	    				  columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ]
	    			      },
	    			  orientation: 'landscape',
	    			  pageSize: 'A3',
	    			  customize: function(doc) {
	    				  doc.styles.tableHeader.fontSize = 6;
	    				  doc.defaultStyle.fontSize = 5;
	    				  doc.defaultStyle.alignment = 'left';
	    			      }
	    		  }
	    	  ]
	      }],
        "aaData": reValidationReqnList,
	      "columns": [
    	  	/*requisitionNo requisitionDNo testCode
			groupCode testName groupName accessionNo
			tempSampleNo isMachineBasedTest testParameterName
			finalRemarkReqd finalRemarkString reportChangeFlag
			isRepeat patAge patGender machineCode*/
    	  { "data": 'sno' },
    	  { "data": 'reqnCheckBox' },
          { "data": 'crNo' },
          { "data": 'patName' },
          { "data": 'patAgeGender'},
          { "data": 'patUnitName' },
          { "data": 'testNameOrGroupName' },
          { "data": 'patLabName' },
          { "data": 'tempSampleOrAccessionNo' },
          { "data": 'patStatus' },
          /*{ "data": 'sendToMachineCheckBox' },*/
          { "data":  'machineCode'},
          { "data": 'view' },
          { "data": 'compareBtn' }
		    ],
		  "order": [[ 0, "asc" ]],
	      "columnDefs":
					[ { "targets": 0, "orderable": true},
					  { "targets": 1, "orderable": false},
					  { "targets": 1,
						"render": function ( data, type, row )
									{ return rowCheckBox(data, type, row); },
					  },
					  { "targets": 5,
						"render": function ( data, type, row )
									{ return customDepartment(data, type, row); },
					  },
					  /*{ "targets": 10,
	    				"createdCell": function (td, cellData, rowData, rowIndex, collIndex)
                             			{ $(td).attr('data-search', rowData.machineCode); },//$(td).css('color', 'red');
					  },
					  { "targets": 10,
	  					"render": function ( data, type, row )
									{ return sendToMachineCheckBox(data, type, row); },
					  },*/
					  { "targets": 11,
						 "render": function ( data, type, row )
									{ return setVewTemplateVal(data, type, row); },
					  },
					  { "targets": 12,
						 "render": function ( data, type, row )
									{ return setInTrackingCompareBtnVal(data, type, row); },
					  },
					],
	      "drawCallback": function(settings) {
	    	  var api = this.api();
	    	  var rows = api.rows({page: 'current'}).nodes();

	    	  //set events active on every table draw event
	    	  setEventListenersAfterReqnListTableDrawn();
	      },
	      "rowCallback" : function( row, data, displayNum, displayIndex, dataIndex ) { 
	    	  var index = displayIndex +1;
	    	if(updateRowFlag){
    		  var oSettings = this.fnSettings ();
    		    // $('td:first', row).html(oSettings._iDisplayStart+displayIndex +1);
    		    // $('td:eq(0)',row).html(index);
	      		}
	    	  return row;
	      },
	      
	      responsive: {
	    	  details: {
  	    		renderer: function(api, rowIdx, columns) {
	  	    			var data = $.map(columns, function(col, i) {
	  	    				return col.hidden ? valiCustomDataOnScreenSize(col) : '';
	  	    				}).join('');
	  	    			  return data ? valiCustomRowAppend(api, rowIdx, columns, data) : false;
  	    		  }
  	    	}
	      },
  		  "initComplete": function(settings, json) {
  			  showLoading(false);
  			  $('#container2ExpandBtn').click();
  			  //$("#container1Row1").collapse('hide');
  		  }
    });

    table.on( 'responsive-display', function ( e, datatable, row, showHide, update ) {
      setEventListenersAfterReqnListTableDrawn();
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
      
      if(showHide){
    	  var testParamTableID= "#testParamTable"+row.index();
    	  //console.log(testParamTableID);
    	  $(testParamTableID).DataTable().columns.adjust().responsive.recalc();
      }
      
    });
	  //Set Tab Notification Badge
	  //$('.patientTestBadgeCount').text(table.rows().count());

	  // Handle click on "Epand All" button
	  $('table .expandButton').on('click', function() {
	    expandColapseRow(true, table);
	  });

	  // Handle click on "Collapse All" button
	  $('table .collapseButton').on('click', function() {
	    expandColapseRow(false, table);
	  });

	  $("#selectAllDtCheck1").off().on( "change", function(e) {
		    if ($(this).is( ":checked" )) {
		    	sellectAllDtCheck(true, table);
		    } else {
		    	sellectAllDtCheck(false, table);
		    }
		    
			if($(".rowCheckBoxes:checked").length){
				 saveBtnsAnimations(true, false);
			} else {
				saveBtnsAnimations(false, false);
			}
		});
	  
	  globalDatablesObject.dataTableReValidationReqnList = table;

	}



var updateRowFlag=false;
function dataTablePatReValidationReqnList(patReValidationReqnList) {
	  $('#DataTable12').DataTable().clear().destroy();

	  $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
	  var table = $('#DataTable12').DataTable({

		  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
		  processing: true,
		  "scrollY":        "350px",
		  "scrollCollapse": true,
		  "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		  "language": { "emptyTable": "No Data Is Available " },
		  "select": {
	        style:    'multi', /*multi+shift | single | api | os | multi*/
	        selector: 'td:nth-child(2) .rowCheckBoxes',
	        blurable: true,
	      },
	      "sPaginationType": "full_numbers",
		  "bJQueryUI": true,
		  buttons: [{
			    	  extend: 'collection',
			    	  text: '',
			    	  className: "fas fa-cogs text-primary btn-lg bg-white btn-outline-light",
			    	  buttons: [
			    		  { extend: 'excel',
			    			  title: 'Investigation Tracking Report',
			    			  text: ' Download Excel',
			    			  className: "fas fa-file-excel text-primary bg-white btn-outline-light"
			    		  },
			    		  { extend: 'pdfHtml5',
			    			  className: "fas fa-file-pdf text-primary bg-white btn-outline-light",
			    			  title: 'Investigation Tracking Report',
			    			  text: ' Download Pdf',
			    			  pageMargins: [0, 0, 0, 0],
			    			  margin: [0, 0, 0, 0],
			    			  alignment: 'center',
			    			  exportOptions: {
			    				  columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ]
			    			      },
			    			  orientation: 'landscape',
			    			  pageSize: 'A3',
			    			  customize: function(doc) {
			    				  doc.styles.tableHeader.fontSize = 6;
			    				  doc.defaultStyle.fontSize = 5;
			    				  doc.defaultStyle.alignment = 'left';
			    			      }
			    		  }
			    	  ]
		  }],
		  "aaData": patReValidationReqnList,
		  "columns": [
	    	  /*requisitionNo requisitionDNo testCode
				groupCode testName groupName accessionNo
				tempSampleNo isMachineBasedTest testParameterName
				finalRemarkReqd finalRemarkString reportChangeFlag
				isRepeat patAge patGender machineCode
	    	  */
	    	  { "data": 'sno' },
	    	  { "data": 'reqnCheckBox' },
	    	  { "data": 'patUnitName' },
	    	  { "data": 'testNameOrGroupName' },
	    	  { "data": 'patLabName' },
	    	  { "data": 'tempSampleOrAccessionNo' },
	    	  /*{ "data": 'sendToMachineCheckBox' },*/
	          { "data":  'machineCode'},
	    	  { "data": 'view' },
	    	  { "data": 'compareBtn' },
	    	  { "data": null, 'defaultContent': 'extraTd'}
	    	],
	    	"order": [[ 0, "asc" ]],
	    	"columnDefs":
	    		[ { "orderable": false, "targets": 0 },
	    		  { "orderable": false, "targets": 1 },
				  { "targets": 1,
					"render": function ( data, type, row )
								{ return rowCheckBox(data, type, row); },
				  },
				  { "targets": 2,
					"render": function ( data, type, row )
								{ return customDepartment(data, type, row); },
				  },
				  /*{ "targets": 6,
    				"createdCell": function (td, cellData, rowData, rowIndex, collIndex)
                       			{ $(td).attr('data-search', rowData.machineCode); },//$(td).css('color', 'red');
				  },
				  { "targets": 6,
  					"render": function ( data, type, row )
								{ return sendToMachineCheckBox(data, type, row); },
				  },*/
				  { "targets": 7,
					 "render": function ( data, type, row )
								{ return setVewTemplateVal(data, type, row); },
				  },
				  { "targets": 8,
					 "render": function ( data, type, row )
								{ return setInTrackingCompareBtnVal(data, type, row); },
				  },
				],
			"drawCallback": function(settings) {
	    	  var api = this.api();
	    	  var rows = api.rows({
	    		  page: 'current'
	    	  }).nodes();
	    	  var last = null;
	    	  //set events active on every table draw event
	    	  setEventListenersAfterReqnListTableDrawn();
			},
	      	responsive: {
		    	details: {
		    		renderer: function(api, rowIdx, columns) {
		    			var data = $.map(columns, function(col, i) {
		    				return col.hidden ? valiCustomDataOnScreenSize(col) : '';
		    				}).join('');
		    			  return data ? valiCustomRowAppend(api, rowIdx, columns, data) : valiCustomRowAppend(api, rowIdx, columns, data);
		    		  }
		    	}
	      	},
	      	"initComplete": function(settings, json) {
				  showLoading(false);
				  $('#container2ExpandBtn').click();
				  //$("#container1Row1").collapse('hide');
			 }
  });

  table.on( 'responsive-display', function ( e, datatable, row, showHide, update ) {
    setEventListenersAfterReqnListTableDrawn();
    //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
    
    if(showHide){
  	  var testParamTableID= "#testParamTable"+row.index();
  	  //console.log(testParamTableID);
  	  $(testParamTableID).DataTable().columns.adjust().responsive.recalc();
    }
    
  });
	  //Set Tab Notification Badge
	  //$('.patientTestBadgeCount').text(table.rows().count());

	  // Handle click on "Epand All" button
	  $('table .expandButton').on('click', function() {
	    expandColapseRow(true, table);
	  });

	  // Handle click on "Collapse All" button
	  $('table .collapseButton').on('click', function() {
	    expandColapseRow(false, table);
	  });

	  $("#selectAllDtCheck2").off().on( "change", function(e) {
		    if ($(this).is( ":checked" )) {
		    	sellectAllDtCheck(true, table);
		    } else {
		    	sellectAllDtCheck(false, table);
		    }
		    
		    if($(".rowCheckBoxes:checked").length){
				 saveBtnsAnimations(true, false);
			} else {
				saveBtnsAnimations(false, false);
			}
		});
	  globalDatablesObject.dataTableReValidationReqnList = table;
	}



/*------------------------------------------DataTables Initialization Ends------------------------------------------------------------------------------------*/


/*------------------------------------------DataTables Custom Rows Creation Starts----------------------------------------------------------------------------*/
/*var rowSerialNoCount=0;
function rowSerialNo(data, type, row){
	return ++rowSerialNoCount;
}
*/



var rowChkCount=0;
function rowCheckBox(data, type, row){
	//console.log(row.reqnTestParamJson);
	rowChkCount++;
	var rowChkVal= {"crNo":row.crNo, "requisitionNo":row.requisitionNo, "requisitionDNo":row.requisitionDNo,
	   										"testCode":row.testCode, "groupCode":row.groupCode };

	var rowChkData='<div class="custom-control custom-checkbox ">';
			rowChkData+='<input type="checkbox" class="custom-control-input rowCheckBoxes" value='+JSON.stringify(rowChkVal)+' id="rowCheck'+rowChkCount+'">';
			rowChkData+='<label class="custom-control-label" for="rowCheck'+rowChkCount+'"></label>';
			rowChkData+='</div>';
	return rowChkData;
}


var sndToMachineChkCount=0;
function sendToMachineCheckBox(data, type, row){

	var isMachineBasedTest = row.isMachineBasedTest;
	var sndToMachineChkVal= {"crNo":row.crNo, "requisitionNo":row.requisitionNo, "requisitionDNo":row.requisitionDNo,
	   											"testCode":row.testCode, "groupCode":row.groupCode, "tempSampleNo":row.tempSampleNo, "machineCode":row.machineCode };
	var sndToMachineChkData = '';
	if(isMachineBasedTest=="1") {
		sndToMachineChkCount++;
		sndToMachineChkData='<div class="custom-control custom-checkbox ">';
		sndToMachineChkData+='<input type="checkbox" class="custom-control-input machineCheckBoxes" value='+JSON.stringify(sndToMachineChkVal)+' id="machineCheck'+sndToMachineChkCount+'">';
		sndToMachineChkData+='<label class="custom-control-label" for="machineCheck'+sndToMachineChkCount+'">'+row.machineCode+'</label>';
		sndToMachineChkData+='</div>';
	 } else {
	 	sndToMachineChkData = '--';
	 }
	return sndToMachineChkData;
}

function setVewTemplateVal(data, type, row){

	var viewTemplateVal = {"crNo":row.crNo, "requisitionNo":row.requisitionNo, "requisitionDNo":row.requisitionDNo,
	   										"testCode":row.testCode, "groupCode":row.groupCode };
	var viewTemplateData = '<a href="#" class=" text-decoration-none viewTemplBtn" key='+JSON.stringify(viewTemplateVal)+'>';
	viewTemplateData += '<span class=""><img height="25" width="25" src="media/images/report2.svg"></span></a>';
	return viewTemplateData;
}

function setInTrackingCompareBtnVal(data, type, row){

	var inTrackingCompareBtnVal = {"crNo":row.crNo, "requisitionNo":row.requisitionNo, "requisitionDNo":row.requisitionDNo,
	   										"testCode":row.testCode, "groupCode":row.groupCode };
	var inTrackingCompareBtnData = '<a class="text-decoration-none compareBtn" key='+JSON.stringify(inTrackingCompareBtnVal)+'>';
	    inTrackingCompareBtnData += '<span class=""><img height="25" width="25" src="media/images/compare.svg"></span></a>';
	return inTrackingCompareBtnData;
}

function customDepartment(data, type, row){
	var departName='<span class="text-wrap">'+data+'</span>';
	return departName;
}
/*--Functions For Returning custom Cell with css color class Ends-----------------*/

/*--Functions For Returning custom Table Rows According To Screen Size Type1 patientDetails Starts---*/


var isValiFirstRow = 0;
var valiCellCount = 0;

function valiCustomDataOnScreenSize(col) {
	var tbdat = '';
	if(col.data!="extraTd"){
		  var w = window.innerWidth;
		  var h = window.innerHeight;
		  if (w >= 1100) {
		    tbdat = valiCustomData(col, 5);
		  } else if (w < 1100 && w > 880) {
		    tbdat = valiCustomData(col, 3);
		  } else if (w < 880 && w > 600) {
		    tbdat = valiCustomData(col, 2);
		  } else if (w <= 600) {
		    tbdat = valiCustomData(col, 1);
		  }
	}
	 return tbdat;
}

function valiCustomData(col, noOfThPairInOneRow) {
	  var tbdat = '';
		if (valiCellCount >= noOfThPairInOneRow) {
	    tbdat = '</tr>'; valiCellCount = 0; isValiFirstRow = 0;
	  }
		if (isValiFirstRow == 0) {
	    tbdat = '<tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">';
	    isValiFirstRow++;
	  }
	  tbdat += '<th>' + col.title + ':' + '</th> ' +
	    			 '<td>' + col.data + '</td>';
	  valiCellCount++;
	  return tbdat;
	}
/*--Functions For Returning custom Table Rows According To Screen Size Type1 patientDetails Ends-----*/



function valiCustomRowAppend(api, rowIdx, columns, data) {
	
  isValiFirstRow = 0;
  valiCellCount = 0;
  //data+='</tr>';
  var rowChildRow = $('<table></table>').append(data);
  
  var testParamTbl=customRowTestParameter(api, rowIdx, columns);
 
  rowChildRow = $(rowChildRow).add($(testParamTbl));
  
	if(data==""){
		return testParamTbl;
	}
	return rowChildRow;
}

function customRowTestParameter(api, rowIdx, columns){
	
	var testPramTableHead='<thead><tr><th>Test Param Name</th><th>Test Param Value</th><th>Reference Range</th></tr></thead>';
	testPramTableHead+='<tbody></tbody>';
	
	var testParamTable = document.createElement("table");
	$(testParamTable).addClass("table  dt-responsive table-sm rounded-lg overflow-hidden shadow border border-primary testParamTables"); /*table-bordered*/
	$(testParamTable).attr({"id":"testParamTable"+rowIdx});
	$(testParamTable).css({"width":"100%"});  //,"background":"#89D0FF"
	$(testParamTable).append(testPramTableHead);

var testParamDataTable = $(testParamTable).DataTable({
	    "dom": "t",
	    responsive: true,
	    "ordering": false,
	    lengthChange: false,
	    "language": { "emptyTable": "No Data Is Available " },
	    "aaData": api.context[0].aoData[rowIdx]._aData.reqnTestParamJson,
	    /*"aaData": globalReValidationReqnList[rowIdx].reqnTestParamJson,*/
	    "columns": [
	    	  { "data": 'paraName' },
	    	  { "data": 'paraValue' },
	    	  { "data": 'refRange' },
	    	  ]
	  }); 
	
	return testParamTable;
}

$(document).ready(function (){
	$('#saveReValidateBtn').off().on("click", function (){
		reValidateReqnResult();
	});
	$('#modifyBtn').off().on("click", function (){
		modifyReqnResult();
	});
});


function reValidateReqnResult() {
	if($( ".rowCheckBoxes:checked" ).length){
		setGlobalSelectedReqnData();
		AjaxReValidateReqnResult(globalSelectedReqnData);
	}
}


function modifyReqnResult() {	
	if($( ".rowCheckBoxes:checked" ).length){
		setGlobalSelectedReqnData();
		openModifyResultIFrame(globalSelectedReqnData);
	}
}

var globalSelectedReqnData= {selectedReqnDataJson:[], selectedReqnElement:[], concatSelectedReqnData:"", chkSendToMachineJson:[], concatChkSendToMachine:""};

function setGlobalSelectedReqnData(){
	globalSelectedReqnData.concatSelectedReqnData= "";
	globalSelectedReqnData.selectedReqnDataJson.length=0;
	
	globalSelectedReqnData.concatChkSendToMachine= "";
	globalSelectedReqnData.chkSendToMachineJson.length=0;
	
	/*--------------------------------------------------*/
	var concatSelectedReqnData=""; 
	var rcbs = $( ".rowCheckBoxes:checked" );
	for(var i=0; i < rcbs.length; i++) {	
    	var rowChkBoxKeyJson=jQuery.parseJSON($(rcbs[i]).val());
    	globalSelectedReqnData.selectedReqnDataJson.push(rowChkBoxKeyJson);
    	globalSelectedReqnData.selectedReqnElement.push((rcbs[i]));
    	
    	concatSelectedReqnData += rowChkBoxKeyJson.crNo+"#"+rowChkBoxKeyJson.requisitionNo+"#"+rowChkBoxKeyJson.requisitionDNo+"#"+rowChkBoxKeyJson.groupCode;
    	concatSelectedReqnData   +='@';
      }
	
	globalSelectedReqnData.concatSelectedReqnData=concatSelectedReqnData;
	
	/*--------------------------------------------------*/
	var concatChkSendToMachine="";
	var mcbs = $( ".machineCheckBoxes:checked" );
	for(var i=0; i < mcbs.length; i++) {	
    	var mchnChkBoxKeyJson=jQuery.parseJSON($(mcbs[i]).val());
    	globalSelectedReqnData.chkSendToMachineJson.push(mchnChkBoxKeyJson);
    	
    	concatChkSendToMachine += mchnChkBoxKeyJson.crNo+"#"+mchnChkBoxKeyJson.requisitionNo+"#"+mchnChkBoxKeyJson.requisitionDNo+"#"+mchnChkBoxKeyJson.groupCode;
    	concatChkSendToMachine   +='@';
      }
	
	globalSelectedReqnData.concatChkSendToMachine=concatChkSendToMachine;
	
}



function removeDatatableRowsOnSave() {
var savedReqnElmList = globalSelectedReqnData.selectedReqnElement;
var selectedReqnDataTable = null;
if(savedReqnElmList.length){
	selectedReqnDataTable = $(savedReqnElmList[0].closest(".dataTable")).DataTable();
}

for(var i=0; i < savedReqnElmList.length; i++) {
	  $(savedReqnElmList[i]).closest("tr").addClass("slideRemove-anime");
	  //globalDatablesObject.dataTableReValidationReqnList.row( $(".slideRemove-anime") ).remove();
	  //globalDatablesObject.dataTableReValidationReqnList.column(0).nodes()
	  selectedReqnDataTable.row( $(".slideRemove-anime") ).remove();
	  selectedReqnDataTable.column(0).nodes()
	  .each( function (cell, k) { cell.innerHTML = k+1; });
	
	  setTimeout(function() {
		  $(".slideRemove-anime").remove();
		  //globalDatablesObject.dataTableReValidationReqnList.draw();
		  selectedReqnDataTable.draw();
	  }, 1500);
}

tableId="."+$(savedReqnElmList[0].closest(".dataTable")).attr("id")+"Caption";
$(tableId).html('<span class="py-2 px-4 rounded reValidatedGradient">Test Result ReValidated</span>');
globalSelectedReqnData.selectedReqnElement.length=0;
}


//addEventListener support for IE8
function bindEvent(eventName, eventHandler) {
    if (window.document.addEventListener){
    	window.document.addEventListener(eventName, eventHandler, false);
    } else if (window.document.attachEvent) {
    	window.document.attachEvent('on' + eventName, eventHandler);
    }
}

//Listen to message from child window
bindEvent('reValidationResponse', function (e) {
	$("[name=jsonResponse]").val(JSON.stringify(e.detail));
});




function actionOnFancyClose(){
	var jsonResponse = jQuery.parseJSON($("[name=jsonResponse]").val());
	if(jsonResponse != null && jsonResponse.isReValidationResponse=="1") {
		
		if(jsonResponse.isReValidationResponse=="1" && jsonResponse.isReValidated=="1"){
			removeDatatableRowsOnSave();
			showToastifyMsg("Test Result ReValidated", true,  4000);
		} else if (jsonResponse.isReValidationResponse=="1" && jsonResponse.isReValidated=="0"){
			showToastifyMsg("Test Result ReValidation Failed", false, 4000);
		}
		$("[name=jsonResponse]").val('{"isReValidated":"0","isReValidationResponse":"0","errorMsg":" "}');
	}
}


function showToastifyMsg(msgText, successFlag, timeOut){
	
	if(successFlag){
		Toastify({
			  text: msgText,
			  duration: timeOut,
			  destination: "",
			  newWindow: true,
			  close: true,
			  gravity: "bottom", // `top` or `bottom`
			  position: 'right', // `left`, `center` or `right`
			  backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)",
			  className: "info",
			  stopOnFocus: true, // Prevents dismissing of toast on hover
			  onClick: function(){} // Callback after click
			}).showToast();
	} else {
		Toastify({
			  text: msgText,
			  duration: timeOut,
			  destination: "",
			  newWindow: true,
			  close: true,
			  gravity: "bottom", // `top` or `bottom`
			  position: 'right', // `left`, `center` or `right`
			  backgroundColor: "linear-gradient(to right, #ff5f6d, #ffc371)",
			  className: "info",
			  stopOnFocus: true, // Prevents dismissing of toast on hover
			  onClick: function(){} // Callback after click
			}).showToast();
	}
	
}
/*--Functions For Returning custom Table Rows According To Screen Size Type1,2 Ends-----------------*/


/*--------------------------------------------DataTables Custom Rows Creation Ends----------------------------------------------------------------------------*/
