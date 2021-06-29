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

/*-Initialise patient deatils datatable before ajax response-*/
$(document).ready(function() {
  var table = $('#DataTable1').DataTable({
    responsive: true,
    "ordering": false,
    "dom": "t",
    lengthChange: false,
  });
});



/*-Redraw hideen datatables on tab change-*/
$(document).ready(function() {
  $("[aria-controls='sampleBasedTab']").click(function() {
    $('#DataTable4').DataTable().columns.adjust().responsive.recalc();
  });
  $("[aria-controls='patientBasedTab']").click(function() {
    $('#DataTable5').DataTable().columns.adjust().responsive.recalc();
  });
  $("[aria-controls='graphTab']").click(function (){
	 $('#testParamTable').DataTable().columns.adjust().responsive.recalc();
	//$(".graphTab-testBtns")[0].click();
  });
});

$(document).ready(function() {
  $(".fancyBoxAdvisedNotes").fancybox({
    src: '.advisedNotesContainer',
    type: 'inline',
    toolbar: false,
    smallBtn: true,
    transitionEffect: "zoom-in-out",
    //transitionDuration: 366,
    btnTpl: {
      close: '<button data-fancybox-close type="button"  class="fancybox-button fancybox-button--close" title="{{CLOSE}}">' +
        '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
        "</button>",
      smallBtn: '<button data-fancybox-close type="button"  class="fancybox-button fancybox-close-small " title="{{CLOSE}}">' +
        '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
        "</button>"
    },
    afterClose: function(instance, current) {}
  });
});

var globalSearchParam = { hospitalCode:"", dateFiltersOrBoth: "", dateTypeCode: "", searchType: "", 
		fromDate: "", toDate: "", crNo:"", billNo:"", requisitionNo:"", requisitionDNo:"", testCode:"", groupCode:"", isGroup:"",
		forTestOrGroupOrAll:"", dataFromArchival:"", hmode:""};


function setGlobalSearchParam(callByAutoOrEvent){
	var hospitalCode=null;
	var requisitionStatusCode=null;
	var dateFiltersOrBoth=null;
	var dateTypeCode=null;
	var searchType=null;
	var crNo=null;
	var billNo=null;
	var sampleNo=null; 
	var labNo=null;
	var requisitionNo=null;
	var requisitionDNo=null;
	var testCode=null;
	var groupCode=null;
	var isGroup=null;
	var forTestOrGroupOrAll=null;
	
	if(callByAutoOrEvent=="1") {
		
		hospitalCode=$('[name="hospitalCode"]')[0].value;
		requisitionStatusCode="";
		crNo=$('[name="crNo"]')[0].value;
		billNo=$('[name="billNo"]')[0].value;
		sampleNo=$('[name="sampleNo"]')[0].value;
		labNo=$('[name="labNo"]')[0].value;
		requisitionNo=$('[name="requisitionNo"]')[0].value;
		requisitionDNo=$('[name="requisitionDNo"]')[0].value;

		dateTypeCode = $('[name="dateTypeCode"]').val();
		fromDate = $('[name="fromDate"]').val();
	    toDate = $('[name="toDate"]').val();

		searchType=$('[name="searchType"]')[0].value;
		if(searchType==null || searchType==""){
			if(crNo!=null && crNo!="" ){ searchType="1"; }
			else if(billNo!=null && billNo!=""){ searchType="2"; }
			else if(sampleNo!=null && sampleNo!=""){ searchType="3"; }
			else if(labNo!=null && labNo!=""){ searchType="4"; }
			else if(requisitionNo!=null && requisitionNo!=""){ searchType="5"; }
			else if(requisitionDNo!=null && requisitionDNo!=""){ searchType="6"; }
		}
		
		testCode=$('[name="testCode"]')[0].value;
		groupCode=$('[name="groupCode"]')[0].value;
		isGroup=$('[name="isGroup"]')[0].value;
		
		forTestOrGroupOrAll=null;
		if(isGroup=="0" && testCode!=null && testCode!=""){ forTestOrGroupOrAll="1" }
		else if(isGroup=="1" && groupCode!=null && groupCode!=""){ forTestOrGroupOrAll="2"; }
		else { forTestOrGroupOrAll="0"; }
		
		requisitionStatusCode=null;
		
	} else if (callByAutoOrEvent=="2") {
		
		hospitalCode=$('[name="hospitalCode"]')[0].value;
		dateFiltersOrBoth = $('.dateFiltersOrBothRange').val();
		dateTypeCode = $('#dateTypeSelectInput').val();
		searchType=$('#inputFilterType').val();
		fromDate = $('.fromDateInput').val();
	    toDate = $('.toDateInput').val();
	    
		crNo=$('#crNoInput').val();
		billNo=$('#billNoInput').val();
		sampleNo=$('#sampleNoInput').val();
		labNo=$('#labNoInput').val();
		requisitionNo=$('#requisitionNoInput').val();
		requisitionDNo=$('#requisitionDNoInput').val();
		
		requisitionStatusCode=null;
		testCode=null;
		groupCode=null;
		isGroup=null;
		forTestOrGroupOrAll=0;
	}
	
	globalSearchParam.hospitalCode= hospitalCode;
	globalSearchParam.requisitionStatusCode= requisitionStatusCode;
	globalSearchParam.dateFiltersOrBoth = dateFiltersOrBoth;
	globalSearchParam.dateTypeCode = dateTypeCode;
	globalSearchParam.searchType= searchType;
	globalSearchParam.fromDate = fromDate;
    globalSearchParam.toDate = toDate;
    
	globalSearchParam.crNo= crNo;
	globalSearchParam.billNo= billNo;
	globalSearchParam.requisitionNo= requisitionNo;
	globalSearchParam.requisitionDNo= requisitionDNo;
	
	globalSearchParam.testCode= testCode;
	globalSearchParam.groupCode= groupCode;
	globalSearchParam.isGroup= isGroup;
	globalSearchParam.forTestOrGroupOrAll= forTestOrGroupOrAll;
	
	console.log("%csetglobalSearchParam() | globalSearchParam: Is Below", "color:green;");
	console.log(globalSearchParam)   
	
}

function startComponentsAjaxCalls(){
    $('#patDetails').removeClass("d-none");
    $('#patReqnList').removeClass("d-none");

    AjaxGetPatDetails(globalSearchParam);
    AjaxGetReqnList(globalSearchParam);
    AjaxGetReqnTestParamList(globalSearchParam);
}

$(document).ready(function(){
	var hmode=$('[name="hmode"]')[0].value;
	
	if(hmode=="UrlExternalCall"){
		UrlCallParamHandler();
	} else {
		UrlCallParamHandler();
	}
});


var setIntervalTestParamTable1=null;
var setIntervalTestParamTable2=null;
function UrlCallParamHandler(){
	
	showLoading(true);
    setGlobalSearchParam("1");
    var flagGoodToGo = false;
    	
	/*---------------------------------------------------*/
	if(globalSearchParam.searchType=="1" && globalSearchParam.crNo.length=="15") {
		$('#inputFilterType').val("1");
		$('#inputFilterType').trigger("change");
		$('#crNoInput').val(globalSearchParam.crNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="2" && globalSearchParam.billNo.length=="15"){
		$('#inputFilterType').val("2");
		$('#inputFilterType').trigger("change");
		$('#billNoInput').val(globalSearchParam.billNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="3" && globalSearchParam.sampleNo.length=="15"){
		$('#inputFilterType').val("3");
		$('#inputFilterType').trigger("change");
		$('#sampleNoInput').val(globalSearchParam.sampleNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="4" && globalSearchParam.labNo.length=="15"){
		$('#inputFilterType').val("4");
		$('#inputFilterType').trigger("change");
		$('#labNoInput').val(globalSearchParam.labNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="5" && globalSearchParam.requisitionNo.length=="15"){
		$('#inputFilterType').val("5");
		$('#inputFilterType').trigger("change");
		$('#requisitionNoInput').val(globalSearchParam.requisitionNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="6" && globalSearchParam.requisitionDNo.length=="15"){
		$('#inputFilterType').val("6");
		$('#inputFilterType').trigger("change");
		$('#requisitionDNoInput').val(globalSearchParam.requisitionDNo);
		flagGoodToGo = true;
	}
	
	if(flagGoodToGo){
		startComponentsAjaxCalls();
	     
	    var testUniqueCode=null;
	 	var reqnUniqueCode=null;
	 	if(globalSearchParam.isGroup=="1"){ 
	 		testUniqueCode=globalSearchParam.groupCode+"_g"; reqnUniqueCode=globalSearchParam.requisitionNo+globalSearchParam.groupCode; 
	 	} else if(globalSearchParam.isGroup=="0"){ testUniqueCode=globalSearchParam.testCode+"_t"; reqnUniqueCode=globalSearchParam.requisitionNo+globalSearchParam.testCode; }
	 	
	 	 var jsonTestKey = { "isGroup": globalSearchParam.isGroup,"groupCode": globalSearchParam.groupCode,
	           	  "testCode": globalSearchParam.testCode, "testUniqueCode": testUniqueCode,
	           	  "reqnUniqueCode": reqnUniqueCode
	 				};
	     
	     $.when( globalAjaxObjectArr.AjaxGetReqnTestParamList ).then(function( data, textStatus, jqXHR ) {
	    	 if(reqnUniqueCode!=null && reqnUniqueCode!=""){
	    		 modifyTestParamHighchartJson(jsonTestKey);
	    	 }

	    	 if(testUniqueCode!=null && testUniqueCode!=""){
	    	 vm.showGraphTab();
		     for(i=0; i<$(".graphTab-testBtns").length; i++){
		    	 var btnJsonKey = jQuery.parseJSON($(".graphTab-testBtns")[i].getAttribute("key") );
		    	 if(testUniqueCode==btnJsonKey.testUniqueCode){
		    		$(".graphTab-testBtns")[i].click();
		    		 break;
		    	 }}
	    	 }

		     if($("#testParamTable").length){
		    	 if(setIntervalTestParamTable1){clearInterval(setIntervalTestParamTable1)};
		    	 setIntervalTestParamTable1=window.setInterval(redrawTestParamTable1,1000);
		    	 }
	    	});
	} else {
		showLoading(false);
	}
}

function redrawTestParamTable1(){
	if($("#graphTab").length && $("#graphTab").attr("aria-hidden")=="false"){
		 $('#testParamTable').DataTable().columns.adjust().responsive.recalc();
		 clearInterval(setIntervalTestParamTable1);
	}}


/*------------------------------------------Set Automaticly---------------------------------------------------------------------------------------------------*/


/*------------------------------------------Event Actions starts----------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------------------------------------------*/
/*----------On Click Getdata Button starts----------------------------------------------------*/
$(document).ready(function() {

  $('#getData').click(function() {

    showLoading(true);
    setGlobalSearchParam("2");
    var flagGoodToGo = false;
    var searchType = $('#inputFilterType').val();
   
    /*searchType 1 for cr.no.*/
    if (searchType == 1) {
    	var crNo = $('#crNoInput').val();

        if (crNo.length == 15) {
        	flagGoodToGo = true;
        } else {
        	flagGoodToGo = false;
        	alert("Cr. No. Should Be Of 15 Digits");
	        $('#crNoInput').focus();
        }
    }
    /*searchType 2 for bill.no.*/
    else if (searchType == 2) {
    	var billNo = $('#billNoInput').val();

        if (billNo.length == 15) {
        	flagGoodToGo = true;
        } else {
          flagGoodToGo = false;
          alert("Bill No. Should Be Of 15 Digits");
          $('#billNoInput').focus();
        }
    }
    /*searchType 3 for sample.no.*/
    else if (searchType == 3) {
    	var sampleNo = $('#sampleNoInput').val();

    	if (sampleNo.length <= 10) {
        	flagGoodToGo = true;
    	} else {
            flagGoodToGo = false;
    		alert("Sample No. Should Be Less Then 10 Digits");
    		$('#sampleNoInput').focus();
    	}
    }
    /*searchType 4 for Lab.no.*/
    else if (searchType == 4) {
    	var labNo = $('#labNoInput').val();

    	if (labNo.length <= 10) {
        	flagGoodToGo = true;
    	} else {
            flagGoodToGo = false;
    		alert("Lab No. Should Be Less Then 10 Digits");
    		$('#labNoInput').focus();
    	}
 
    }
    /*searchType 5 for requisitionNo.*/
    else if (searchType == 5) {
    	var requisitionNo = $('#requisitionNoInput').val();

        if (requisitionNo.length == 15) {
        	flagGoodToGo = true;
        } else {
          flagGoodToGo = false;
          alert("requisitionNo No. Should Be Of 15 Digits");
          $('#requisitionNoInput').focus();
        }
    }
    /*searchType 6 for requisitionDNo.*/
    else if (searchType == 6) {
    	var requisitionDNo = $('#requisitionDNoInput').val();

        if (requisitionDNo.length == 15) {
        	flagGoodToGo = true;
        } else {
          flagGoodToGo = false;
          alert("requisitionDNo No. Should Be Of 15 Digits");
          $('#requisitionDNoInput').focus();
        }
    }
    
    if(flagGoodToGo){
		startComponentsAjaxCalls();
	} else {
		showLoading(false);
	}
  });
});
/*------------------------------------------------------*/

/*----------On Change in Archival Radio Show From to Months for archival search Starts--------*/
$(document).ready(function() {
  $('.dataFromArchival').change(function() {
    var dataFromArchival = $('.dataFromArchival:checked').val();
    if (dataFromArchival == 0) {
      $('.inputDates').addClass("d-none");
    }
    else if (dataFromArchival == 1) {
      $('.inputDates').removeClass("d-none");
    }
  });
  // $("input[name=dataFromMonth]").change(function ()
  // { alert("type2 "+this.value); });
});
/*------------------------------------------------------*/




/*----------Function to Set Events Listeners After Requisition List Table Fetched Starts--------*/

function setEventListenersAfterReqnListTableDrawn(datatableTable, groupOrderOnColumn){

	//Event listener for button for order by the grouping ---------------------------------------------------------------------------------------
	  if ($('button.rowGroupReqnDateSort').length) {
	    $('button.rowGroupReqnDateSort').on('click', function() {
	      if (datatableTable) {
	        var currentOrder = datatableTable.order()[0];
	        if (currentOrder == "undefined" || currentOrder == undefined) {
	          datatableTable.order([groupOrderOnColumn, 'desc']).draw();
	          $(".reqnDtGrupSortUp").removeClass("d-none");
	          $(".reqnDtGrupSortDown").addClass("d-none");
	        } else {
	          if (currentOrder[0] === groupOrderOnColumn && currentOrder[1] === 'asc') {
	            datatableTable.order([groupOrderOnColumn, 'desc']).draw();
	            $(".reqnDtGrupSortDown").removeClass("d-none");
	            $(".reqnDtGrupSortUp").addClass("d-none");
	          } else {
	            datatableTable.order([groupOrderOnColumn, 'asc']).draw();
	            $(".reqnDtGrupSortUp").removeClass("d-none");
	            $(".reqnDtGrupSortDown").addClass("d-none");
	          }
	        }
	      }
	    });
	  }
	  
	  //Event listener for button for doctor advised notes----------------------------------------------
	  if ($('.showAdvisedNotes').length) {
	    $('.showAdvisedNotes').off("click").on("click", function() {
	      var reqnAdvisedNotes = JSON.parse($(this).attr("key"));
	      $(".instrucGenTr td").empty().append(reqnAdvisedNotes.instructionGeneric);
	      $(".instrucFlgTr td").empty().append(reqnAdvisedNotes.instructionFlag);
	      $(".chfComplntTr td").empty().append(reqnAdvisedNotes.chiefComplaintsName);
	      $(".diagnosisTr td").empty().append(reqnAdvisedNotes.diagnosisName);
	      $(".notesTr td").empty().append(reqnAdvisedNotes.advisedNote);
	      $(".advisedByTr td").empty().append(reqnAdvisedNotes.advisedByDocName);
	      $.fancybox.destroy();
	      $(".fancyBoxAdvisedNotes").click();
	    });
	    if (tippyLoadingUtlInstance.showAdvisedNotes) {
	      tippyLoadingUtlInstance.showAdvisedNotes.forEach(function(elementTipyInst) {
	        elementTipyInst.destroy();
	      });
	    }
	    //if(tippyLoadingUtlInstance.showAdvisedNotes){ } else {
	    const tippy_showAdvisedNotes = tippy('.showAdvisedNotes', {
	      delay: 100,
	      arrow: true,
	      arrowType: 'round',
	      size: 'small',
	      duration: 300,
	      animation: 'shift-away-extreme',
	      placement: 'top',
	      allowHTML: true,
	      content: '<span>Advised<span style="color: aqua;"> Notes </span></span>',
	    });
	    tippyLoadingUtlInstance.showAdvisedNotes = tippy_showAdvisedNotes;
	    //}
	  }
	  
	  
	//Event listener for button provisional PDF Report----------------------------------------------
	  if ($('.generateReportPdfBtn').length) {
	    $('.generateReportPdfBtn').off("click").on("click", function() {
	    
	    var genReportBtnElm=$(this).addClass("d-none");
   		var waitGenReportBtnElm=$(this).siblings('.waitGenerateReportPdfBtn').removeClass("d-none");	 
   	
	      var pdfReportParam = JSON.parse($(this).attr("key"));
	      
	      AjaxGetPatPDFReport(pdfReportParam);
			
			if($('#showReportPdfModalIframe'))
				$('#showReportPdfModalIframe').remove();
			
			if($('.showReportPdfModalIframe'))
				$('.showReportPdfModalIframe').remove();
			
			$.when(globalAjaxObjectArr.AjaxGetPatPDFReport).then(function( data, textStatus, jqXHR ) {
				 
				 $(genReportBtnElm).removeClass("d-none");
    	 		 $(waitGenReportBtnElm).addClass("d-none");
    	 
				 var pdfNameArr= data.reportDtl.pdfNameArr;
				 var pdfName="";
				 for(key in pdfNameArr){
					 pdfName+=pdfNameArr[key];
						pdfName+="@@";
				 }
				 var _mode = "AjaxGetPDFReport";
				 var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&selectedPDFName="+pdfName;

				 $('#showReportPdfModal .modal-body').prepend('<iframe class="showReportPdfModalIframe" style="width:100%;height:75vh;" src="'+ url+'"></iframe>');
				 $('#showReportPdfModal').modal('show');
			 });
		    });
	    
	    if (tippyLoadingUtlInstance.generateReportPdfBtn) {
	      tippyLoadingUtlInstance.generateReportPdfBtn.forEach(function(elementTipyInst) {
	        elementTipyInst.destroy();
	      });
	    }
	    const tippy_generateReportPdfBtn = tippy('.generateReportPdfBtn', {
	      delay: 100,
	      arrow: true,
	      arrowType: 'round',
	      size: 'small',
	      duration: 300,
	      animation: 'shift-away-extreme',
	      placement: 'top',
	      allowHTML: true,
	      content: 'Generate provisional Report PDF'
	    });
	    tippyLoadingUtlInstance.generateReportPdfBtn = tippy_generateReportPdfBtn;
	  }
	  /*
	  tippy('.generateReportPdfBtn', {
		    delay: 100,
		    arrow: true,
		    arrowType: 'round',
		    size: 'large',
		    duration: 500,
		    animation: 'shift-away-extreme',
		    placement: 'bottom',
		    allowHTML: true,
		    content: 'Generate provisional Report PDF'
		});
		*/

/*-----Click Event on PDF button click---*/

if($('.showReportPdfBtn').length){
	$(".showReportPdfBtn").off().click(function (){
		var pdfName=this.attributes.value.value;

		pdfName+="@@";

		var _mode = "AjaxGetPDFReport";
		var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&selectedPDFName="+pdfName;

		if($('#showReportPdfModalIframe'))
		$('#showReportPdfModalIframe').remove();

		$('#showReportPdfModal .modal-body').prepend('<iframe id="showReportPdfModalIframe" style="width:100%;height:75vh;" src="'+ url+'"></iframe>');
		$('#showReportPdfModal').modal('show');
	});

	if (tippyLoadingUtlInstance.showReportPdfBtn) {
	      tippyLoadingUtlInstance.showReportPdfBtn.forEach(function(elementTipyInst) {
	        elementTipyInst.destroy();
	      });
	    }
	    //if(tippyLoadingUtlInstance.showAdvisedNotes){ } else {
	    const tippy_showReportPdfBtn = tippy('.showReportPdfBtn', {
		    delay: 100,
		    arrow: true,
		    arrowType: 'round',
		    size: 'large',
		    duration: 500,
		    animation: 'shift-away-extreme',
		    placement: 'bottom',
		    allowHTML: true,
		    content: 'Show Report PDF'
		});
	    tippyLoadingUtlInstance.showReportPdfBtn = tippy_showReportPdfBtn;
	    
}

	if($('.showParamBtn').length){
	$(".showParamBtn").off().click(function (){

		var jsonTestKey = jQuery.parseJSON( $(this).attr("key") );
		modifyTestParamHighchartJson(jsonTestKey);
		vm.showGraphTab();
		var testUniqueCode=jsonTestKey.testUniqueCode;

		for(i=0; i<$(".graphTab-testBtns").length; i++){
			var btnJsonKey = jQuery.parseJSON($(".graphTab-testBtns")[i].getAttribute("key") );
			if(testUniqueCode==btnJsonKey.testUniqueCode){
				$(".graphTab-testBtns")[i].click();
				break;
			}
		}
	});

	if (tippyLoadingUtlInstance.showParamBtn) {
	      tippyLoadingUtlInstance.showParamBtn.forEach(function(elementTipyInst) {
	        elementTipyInst.destroy();
	      });
	    }
	    //if(tippyLoadingUtlInstance.showAdvisedNotes){ } else {
	    const tippy_showParamBtn = tippy('.showParamBtn', {
		    delay: 100,
		    arrow: true,
		    arrowType: 'round',
		    size: 'large',
		    duration: 500,
		    animation: 'shift-away-extreme',
		    placement: 'bottom',
		    allowHTML: true,
		    content: 'Show Test Parameters'
		});
	    tippyLoadingUtlInstance.showParamBtn = tippy_showParamBtn;
	}

}


/*----------Function to Set Events Listeners After Requstion List Table Fetched Starts--------*/

/*------------------------------------------Event Actions Ends------------------------------------------------------------------------------------------------*/


/*------------------------------------------Ajax Calls Starts-------------------------------------------------------------------------------------------------*/

var globalPatientDetails = null;

var globalSampleBasedReqnList = null;
var globalPatientBasedReqnList = null;
var deferredObjectArr =null;
var globalAjaxObjectArr ={};


function AjaxGetReqnList(globalSearchParam) {
  globalSampleBasedReqnList = null;
  globalPatientBasedReqnList = null;

  var _mode = "AjaxGetReqnList";
  globalSearchParam.hmode = _mode;
  
  var url = "/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?"+ $.param(globalSearchParam);

  globalAjaxObjectArr.AjaxGetReqnList=$.getJSON(url, function(data) {
		if(!$.isEmptyObject(data)) {
			if(data.isSuccess=="1") {
				globalSampleBasedReqnList = data.sampleBasedReqnList;
		        globalPatientBasedReqnList = data.patientBasedReqnList;

		        dataTableSampleBasedReqnList(data.sampleBasedReqnList);
		        dataTablePatientBasedReqnList(data.patientBasedReqnList);
			} else {
			  console.log("%cAjaxGetReqnList Failed | ResponseError Is Below", "color:red;");
			  console.log(data.error);
			}
		}
	})
	.done(function(data) {
	  console.log("%cAjaxGetReqnList success | ResponseData Is Below", "color:green;");
	  console.log(data);
	})
	.fail(function( jqxhr, textStatus, error ) {
	  console.log("%cAjaxGetReqnList Failed | ResponseError Is Below", "color:red;");
	  var err = textStatus + ", " + error;
	  console.log( "Request Failed: " + err );
	});
}


function AjaxGetPatPDFReport(pdfReportParam) {

	  var _mode = "AjaxGetPatPDFReport";
	  pdfReportParam.hmode = _mode;
	  
	  var url = "/HISInvestigationG5/new_investigation/InvReportGenerator.cnt?"+ $.param(pdfReportParam);
	  
	  globalAjaxObjectArr.AjaxGetPatPDFReport=$.getJSON(url, function(data) {
			if(!$.isEmptyObject(data)) {
				if(data.isSuccess=="1") {
					//globalSampleBasedReqnList = data.sampleBasedReqnList;
			        //globalPatientBasedReqnList = data.patientBasedReqnList;
				} else {
				  console.log("%cAjaxGetPatPDFReport Failed | ResponseError Is Below", "color:red;");
				  console.log(data.error);
				}
			}
		})
		.done(function(data) {
		  console.log("%cAjaxGetPatPDFReport success | ResponseData Is Below", "color:green;");
		  console.log(data);
		})
		.fail(function( jqxhr, textStatus, error ) {
		  console.log("%cAjaxGetPatPDFReport Failed | ResponseError Is Below", "color:red;");
		  var err = textStatus + ", " + error;
		  console.log( "Request Failed: " + err );
		});
	}

/*------------------------------------------Ajax Calls Ends---------------------------------------------------------------------------------------------------*/



/*------------------------------------------DataTables Initialization starts----------------------------------------------------------------------------------*/

function dataTableSampleBasedReqnList(sampleBasedReqnList) {

  var columnIndexFromTo={ 
	"reqnRaising":{"from": 13,"to":19},
	 "sampleColl":{"from": 20,"to":24},
	 "packListGen":{"from": 25,"to":28},
	 "sampleAccep":{"from": 29,"to":33},
	 "sampleReje":{"from": 34,"to":36},
	 "resultEnt":{"from": 37,"to":42},
	 "reportGen":{"from": 43,"to":45},
	}
	
  var groupColumn = 2;
  var groupOrderOnColumn = 13;
  $('#DataTable4').DataTable().clear().destroy();

  $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
  var table = $('#DataTable4').DataTable({

	  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
	  processing: true,
	  "scrollY":        "300px",
	  "scrollCollapse": true,
	  "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	  "order": [[1, "asc"], [groupOrderOnColumn, "desc"]],
      "language": { "emptyTable": "No Data Is Available " },
      "sPaginationType": "full_numbers",
      "bJQueryUI": true,
	  buttons: [{
    	  extend: 'collection',
    	  text: '',
    	  className: "fas fa-cogs text-primary btn-lg bg-white btn-outline-light",
    	  buttons: [
    		  {
    			  extend: 'excel',
    			  title: 'Investigation Tracking Report',
    			  text: ' Download Excel',
    			  className: "fas fa-file-excel text-primary bg-white btn-outline-light"
    		  },
    		  {
    			  extend: 'pdfHtml5',
    			  className: "fas fa-file-pdf text-primary bg-white btn-outline-light",
    			  title: 'Investigation Tracking Report',
    			  text: ' Download Pdf',
    			  pageMargins: [0, 0, 0, 0],
    			  margin: [0, 0, 0, 0],
    			  alignment: 'center',
    			  exportOptions: {
    				  columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
    					  		17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
    					  		32, 33, 34, 35, 36, 37, 38, 39, 40
    					  		]
    			  },
    			  orientation: 'landscape',
    			  pageSize: 'A2',
    			  customize: function(doc) {
    				  doc.styles.tableHeader.fontSize = 6;
    				  doc.defaultStyle.fontSize = 5;
    				  doc.defaultStyle.alignment = 'left';
    			  }
    		  }
          ]
      }],
      "aaData": sampleBasedReqnList,
      "columns": [
    	  { "className": 'control details-control',			/*0*/
	        "orderable": false,
	        "data": null,
	        "defaultContent": ''
	      },
    	  { "data": 'sno' },								/*0*/
    	  { "data": 'reqnDataForGrouping' },				/*1*/
          { "data": 'requisitionNo' },						/*2*/
          { "data": 'labName' },							/*3*/
          { "data": 'advisedByDept' },						/*4*/
          { "data": 'groupNameTestName' },					/*5*/
          { "data": 'requisitionStatus' },					/*6*/
          { "data": 'turnAroundTime.turnAroundTime' },		/*7*/
          { "data": 'sampleNo' },							/*8*/
          { "data": 'testRate' },							/*9*/
          { "data": 'billedUnbilled' },						/*10*/
          { "data": 'testNote' },							/*11*/
          
          { "data": 'requisitionDate' },					/*12*/
          { "data": 'requisitionBy' },						/*13*/
          { "data": 'advisedByDoc' },						/*14*/
          { "data": 'groupNameTestName' },					/*15*/
          { "data": 'appointmentDate' },					/*16*/
          { "data": 'billNo' },								/*17*/
          { "data": 'billDate' },							/*18*/
          
          { "data": 'sampleName' },							/*19*/
          { "data": 'sampleNo' },							/*20*/
          { "data": 'sampleCollDate' },						/*21*/
          { "data": 'sampleCollArea' },						/*22*/
          { "data": 'sampleCollBy' },						/*23*/
          
          { "data": 'packingListNo' },						/*24*/
          { "data": 'labNo' },								/*25*/
          { "data": 'packingListDateTime' },				/*26*/
          { "data": 'packingListBy' },						/*27*/
          
          { "data": 'labNo' },								/*28*/
          { "data": 'sampleAccepDate' },					/*29*/
          { "data": 'sampleAccepBy' },						/*30*/
          { "data": 'sampleAccepMode' },					/*31*/
          { "data": 'machineName' },						/*32*/
          
          { "data": 'sampleRejecDate' },					/*33*/
          { "data": 'sampleRejecBy' },						/*34*/
          { "data": 'sampleRejecReason' },					/*35*/
          
          { "data": 'resultEntryDate' },					/*36*/
          { "data": 'resultEntryBy' },						/*37*/
          { "data": 'resultValidDate' },					/*38*/
          { "data": 'resultValidBy' },						/*39*/
          { "data": 'resultReValidDate' },					/*40*/
          { "data": 'resultReValidBy' },					/*41*/
          
          { "data": 'reportGenerationDate' },				/*42*/
          { "data": 'reportPrintDate' },					/*43*/
          { "data": 'reportPrintBy' }						/*44*/
      ],
      "columnDefs": [
		  { "targets": 0, "orderable": false },
		  { "targets": groupColumn, "visible": false },
		  { "targets": 3, "visible": false },
		  { "render": function ( data, type, row )
			  { return customGroupTestNameData(data, type, row); },
		  	"targets": 6
		  },
		  { "render": function ( data, type, row )
			  { return decideReqnStatusColor(data, type, row); },
			className: "reqnStaus",
			"targets": 7
		  },
		  { "render": function ( data, type, row )
			  { return decideTurnAroundTimeIcon(data, type, row); },
            className: "reqnTat",
            "targets": 8
		  },
		  {	"render": function ( data, type, row )
			  { return customNoteData(data, type, row); },
            "targets": 12
		  },
      ],
      "drawCallback": function(settings) {
          var api = this.api();
          var rows = api.rows({ page: 'current' }).nodes();
          var last = null;
          //Group rows On every table draw event
          api.column(groupColumn, { page: 'current' }).data().each(function(reqnDataForGroupn, i) {
        	  if (last !== reqnDataForGroupn.requisitionNo) {
              var priorityElmnt = "";
              if (reqnDataForGroupn.priorityCode == "2") {
                priorityElmnt = '<span class="urgentGradient">Urgent</span>'
              }
              
              var keyStr = JSON.stringify(reqnDataForGroupn.reqnAdvisedNotes);
              
              var strPDFGenKey = JSON.stringify(reqnDataForGroupn.reqnDataForPDFReport); 
              var pdfReportElm ='';
              var reqnStus=reqnDataForGroupn.requisitionStatusCode;
              if(reqnStus=="7" || reqnStus=="8" || reqnStus=="13"){
            	  pdfReportElm= '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  generateReportPdfBtn " key=' + strPDFGenKey + '>' +
              				 			'<i class="far fa-file-pdf" style="color:#007BFF;"></i>' +
              				 			'</button>';
              } else if (reqnStus=="14" || reqnStus=="26"){
            	  pdfReportElm= '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  showReportPdfBtn" value="'+reqnDataForGroupn.reportURL+'">' +
		 			'<i class="far fa-file-pdf" style="color:#007BFF;"></i>' +
		 			'</button>';
              }
              var waitGeneratePdfReportElm = '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1 text-nowrap d-none waitGenerateReportPdfBtn " disabled>' +
											 '<span class="spinner-border text-danger spinner-border-sm mb-2 "></span>' + '<i class="text-warning" style:"font-size: 0.7rem;">Loading..</i>' +
											 '</button>';
              
              var rowGroupElmnt = '<tr class="group"><td colspan="12" class="rowGroupReqnDate"><span>' + reqnDataForGroupn.filterDateTypeName + ' : </span>' + reqnDataForGroupn.filterDateTypeDate +
              '<button type="button" class="btn btn-light btn-circle btn-circle-reqnGroupRowSort ml-1 rowGroupReqnDateSort ">' +
              '<i class="fas fa-sort-amount-up reqnDtGrupSortUp" ></i>' +
              '<i class="fas fa-sort-amount-down-alt reqnDtGrupSortDown d-none" ></i>' +
              '</button>' +
              '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  showAdvisedNotes " key=' + keyStr + '>' +
              '<i class="far fa-comment-alt" style="color:#FD7E14;"></i>' +
              '</button>' +
              pdfReportElm +
              waitGeneratePdfReportElm +
              priorityElmnt +
              '</td></tr>';
              $(rows).eq(i).before(rowGroupElmnt);
              last = reqnDataForGroupn.requisitionNo;
            }
            //not need of writing this cose in loope but no option for now
            if (reqnDataForGroupn.filterDateTypeCode == "1") {
              groupOrderOnColumn = 13
            } else if (reqnDataForGroupn.filterDateTypeCode == "2") {
              groupOrderOnColumn = 22
            } else if (reqnDataForGroupn.filterDateTypeCode == "3") {
              groupOrderOnColumn = 30
            } else if (reqnDataForGroupn.filterDateTypeCode == "4") {
              groupOrderOnColumn = 37
            } else if (reqnDataForGroupn.filterDateTypeCode == "5") {
              groupOrderOnColumn = 39
            } else if (reqnDataForGroupn.filterDateTypeCode == "6") {
              groupOrderOnColumn = 44
            }
          });
          //set events active on every table draw event
          setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
        },
       responsive: {
    	  details: {
    		  renderer: function(api, rowIdx, columns) {
    			  var data = customeRowSmType2(api, rowIdx, columns, columnIndexFromTo);
    			  return data ? customeRowDataAppend(data) : false;
    		  }
    	  }
      },
      "initComplete": function(settings, json) {
    	  showLoading(false);
    	  $('#container2ExpandBtn').click();
		  //$("#container2Row1").collapse('hide');
      }
  });
  
  table.on( 'responsive-display', function ( e, datatable, row, showHide, update ) {
      setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
    });
  
  //Set Tab Notification Badge
  $('.sampleTestBadgeCount').text(table.rows().count());
  setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
  // Handle click on "Epand All" button
  $('table .expandButton').on('click', function() {
    expandColapseRow(true, table);
  });

  // Handle click on "Collapse All" button
  $('table .collapseButton').on('click', function() {
    expandColapseRow(false, table);
  });

}

function dataTablePatientBasedReqnList(patientBasedReqnList) {
  var columnIndexFromTo={ 
	"reqnRaising":{"from": 13,"to":19},
	"patAccep":{"from": 20,"to":25},
	"patReje":{"from": 26,"to":28},
	"resultEnt":{"from": 29,"to":34},
	"reportGen":{"from": 35,"to":37},
	}
  var groupColumn = 2;
  var groupOrderOnColumn = 13;
  $('#DataTable5').DataTable().clear().destroy();

  $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
  var table = $('#DataTable5').DataTable({

	  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
	  processing: true,
	  "scrollY":        "300px",
	  "scrollCollapse": true,
	  "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	  "order": [[1, "asc"], [groupOrderOnColumn, "desc"]],
      "language": { "emptyTable": "No Data Is Available " },
	  "sPaginationType": "full_numbers",
	  "bJQueryUI": true,
      buttons: [{
    	  extend: 'collection',
    	  text: '',
    	  className: "fas fa-cogs text-primary btn-lg bg-white btn-outline-light",
    	  buttons: [
    		  {
    			  extend: 'excel',
    			  title: 'Investigation Tracking Report',
    			  text: ' Download Excel',
    			  className: "fas fa-file-excel text-primary bg-white btn-outline-light"
    		  },
    		  {
    			  extend: 'pdfHtml5',
    			  className: "fas fa-file-pdf text-primary bg-white btn-outline-light",
    			  title: 'Investigation Tracking Report',
    			  text: ' Download Pdf',
    			  pageMargins: [0, 0, 0, 0],
    			  margin: [0, 0, 0, 0],
    			  alignment: 'center',
    			  exportOptions: {
    				  columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
    					  		17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32
    					  		]
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
      "aaData": patientBasedReqnList,
      "columns": [
    	  { "className": 'control details-control',		/*0*/
  	        "orderable": false,
  	        "data": null,
  	        "defaultContent": ''
  	      },
    	  { "data": 'sno' },							/*0*/
    	  { "data": 'reqnDataForGrouping' },			/*1*/
          { "data": 'requisitionNo' },					/*2*/
          { "data": 'labName' },						/*3*/
          { "data": 'advisedByDept' },					/*4*/
          { "data": 'groupNameTestName' },				/*5*/
          { "data": 'requisitionStatus' },				/*6*/
          { "data": 'turnAroundTime.turnAroundTime' },	/*7*/
          { "data": 'accessionNo' },					/*8*/
          { "data": 'testRate' },						/*9*/
          { "data": 'billedUnbilled' },					/*10*/
          { "data": 'testNote' },						/*11*/
          
          { "data": 'requisitionDate' },				/*12*/
          { "data": 'requisitionBy' },					/*13*/
          { "data": 'advisedByDoc' },					/*14*/
          { "data": 'groupNameTestName' },				/*15*/
          { "data": 'appointmentDate' },				/*16*/
          { "data": 'billNo' },							/*17*/
          { "data": 'billDate' },						/*18*/
          
          { "data": 'accessionNo' },					/*19*/
          { "data": 'patientAccepDate' },				/*20*/
          { "data": 'patientAccepArea' },				/*21*/
          { "data": 'patientAccepBy' },					/*22*/
          { "data": 'patientAccepMode' },				/*23*/
          { "data": 'machineName' },					/*24*/
          
	      { "data": 'patientRejecDate' },				/*25*/
	      { "data": 'patientRejecBy' },					/*26*/
	      { "data": 'patientRejecReason' },				/*27*/
	      
	      { "data": 'resultEntryDate' },				/*28*/
	      { "data": 'resultEntryBy' },					/*29*/
	      { "data": 'resultValidDate' },				/*30*/
	      { "data": 'resultValidBy' },					/*31*/
          { "data": 'resultReValidDate' },				/*32*/
          { "data": 'resultReValidBy' },				/*33*/
          
	      { "data": 'reportGenerationDate' },			/*34*/
	      { "data": 'reportPrintDate' },				/*35*/
	      { "data": 'reportPrintBy' }					/*36*/
	  ],
	  "columnDefs": [
		  { "targets": 0, "orderable": false },
		  { "targets": groupColumn, "visible": false },
		  { "targets": 3, "visible": false },
		  {	"render": function ( data, type, row )
        	  { return customGroupTestNameData(data, type, row); },
            "targets": 6
          },
		  { "render": function ( data, type, row )
			  { return decideReqnStatusColor(data, type, row); },
            className: "reqnStaus",
            "targets": 7
		  },
		  { "render": function ( data, type, row )
			  { return decideTurnAroundTimeIcon(data, type, row); },
            className: "reqnTat",
            "targets": 8
		  },
		  {	"render": function ( data, type, row )
			  { return customNoteData(data, type, row); },
            "targets": 12
          },
      ],
      "drawCallback": function(settings) {
          var api = this.api();
          var rows = api.rows({page: 'current'}).nodes();
          var last = null;
          //Group rows On every table draw event
          api.column(groupColumn, { page: 'current' }).data().each(function(reqnDataForGroupn, i) {
            if (last !== reqnDataForGroupn.requisitionNo) {
              var priorityElmnt = "";
              if (reqnDataForGroupn.priorityCode == "2") {
                priorityElmnt = '<span class="urgentGradient">Urgent</span>'
              }
              var keyStr = JSON.stringify(reqnDataForGroupn.reqnAdvisedNotes);
              var strPDFGenKey = JSON.stringify(reqnDataForGroupn.reqnDataForPDFReport); 
              var pdfReportElm ='';
              var reqnStus=reqnDataForGroupn.requisitionStatusCode
              if(reqnStus=="7" || reqnStus=="8" || reqnStus=="13"){
            	  pdfReportElm= '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  generateReportPdfBtn " key=' + strPDFGenKey + '>' +
              				 			'<i class="far fa-file-pdf" style="color:#007BFF;"></i>' +
              				 			'</button>';
              } else if (reqnStus=="14" || reqnStus=="26"){
            	  pdfReportElm= '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  showReportPdfBtn" value="'+reqnDataForGroupn.reportURL+'">' +
		 			'<i class="far fa-file-pdf" style="color:#007BFF;"></i>' +
		 			'</button>';
              }
              
              var waitGeneratePdfReportElm = '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1 text-nowrap d-none waitGenerateReportPdfBtn " disabled>' +
											 '<span class="spinner-border text-danger spinner-border-sm mb-2 "></span>' + '<i class="text-warning" style:"font-size: 0.7rem;">Loading..</i>' +
											 '</button>';
			 
              var rowGroupElmnt = '<tr class="group"><td colspan="12" class="rowGroupReqnDate"><span>' + reqnDataForGroupn.filterDateTypeName + ' : </span>' + reqnDataForGroupn.filterDateTypeDate +
              '<button type="button" class="btn btn-light btn-circle btn-circle-reqnGroupRowSort ml-1 rowGroupReqnDateSort ">' +
              '<i class="fas fa-sort-amount-up reqnDtGrupSortUp" ></i>' +
              '<i class="fas fa-sort-amount-down-alt reqnDtGrupSortDown d-none" ></i>' +
              '</button>' +
              '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  showAdvisedNotes " key=' + keyStr + '>' +
              '<i class="far fa-comment-alt" style="color:#FD7E14;"></i>' +
              '</button>' +
              pdfReportElm +
              waitGeneratePdfReportElm + 
              priorityElmnt +
              '</td></tr>';
              $(rows).eq(i).before(rowGroupElmnt);
              last = reqnDataForGroupn.requisitionNo;
            }
            //not need of writing this cose in loope but no option for now
            if (reqnDataForGroupn.filterDateTypeCode == "1") {
                groupOrderOnColumn = 13
            } else if (reqnDataForGroupn.filterDateTypeCode == "2") {
                groupOrderOnColumn = 21
            } else if (reqnDataForGroupn.filterDateTypeCode == "3") {
                groupOrderOnColumn = 21
            } else if (reqnDataForGroupn.filterDateTypeCode == "4") {
                groupOrderOnColumn = 29
            } else if (reqnDataForGroupn.filterDateTypeCode == "5") {
                groupOrderOnColumn = 31
            } else if (reqnDataForGroupn.filterDateTypeCode == "6") {
                groupOrderOnColumn = 36
            }
          });
          //set events active on every table draw event
          setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
        },
	  responsive: {
		  details: {
			  renderer: function(api, rowIdx, columns) {
				  var data = customeRowPtType2(api, rowIdx, columns, columnIndexFromTo);
				  return data ? customeRowDataAppend(data) : false;
			  }
		  }
	  },
	  "initComplete": function(settings, json) {
		  showLoading(false);
		  $('#container2ExpandBtn').click();
		  //$("#container2Row1").collapse('hide');
	  }
  });
  
  table.on( 'responsive-display', function ( e, datatable, row, showHide, update ) {
      setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
    });
  
  //Set Tab Notification Badge
  $('.patientTestBadgeCount').text(table.rows().count());
  setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
  // Handle click on "Epand All" button
  $('table .expandButton').on('click', function() {
    expandColapseRow(true, table);
  });

  // Handle click on "Collapse All" button
  $('table .collapseButton').on('click', function() {
    expandColapseRow(false, table);
  });

}

/*------------------------------------------DataTables Initialization Ends------------------------------------------------------------------------------------*/


/*------------------------------------------DataTables Custom Rows Creation Starts----------------------------------------------------------------------------*/

/*--Functions For Returning custom Requsition Status Cell with css color class Starts-----------------*/
function decideReqnStatusColor(data, type, row){
	var statusColorClass="";
	var stIcon="";

	var testCode = row.testCode;
	var groupCode = row.groupCode;
	var isGroup = row.isGroup;
	var reqnUniqueCode ="";
	var testUniqueCode ="";
	if(isGroup=="1"){
		reqnUniqueCode = row.requisitionNo+groupCode
		testUniqueCode = groupCode+"_g";
	} else{
		  groupCode="-";
		  reqnUniqueCode = row.requisitionDNo+testCode
		  testUniqueCode = testCode+"_t";
		  }

	var jsonKey={"testCode":testCode, "reqnUniqueCode":reqnUniqueCode, "isGroup":isGroup, "groupCode":groupCode, "testUniqueCode":testUniqueCode};
	strKey=JSON.stringify(jsonKey);
	
	switch(row.requisitionStatusCode){
	case "2":  statusColorClass="stReqnRaisedColor"; 		       										break;
	case "5":  statusColorClass="stReqnRaisedColor";		       										break;
	case "3":  statusColorClass="stStsampleCollectedColor";    											break;
	case "4":  statusColorClass="stPackListGenColor"; 		     										break;
	case "6":  statusColorClass="stSampleAcceptColor"; 		     										break;
	case "7":  statusColorClass="stResultEnteredColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='</span>';																	break;
	case "8":  statusColorClass="stResultvalidColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='</span>';																	break;
	case "9":  statusColorClass="stPatRejectcolor"; 		       										break;
	case "10": statusColorClass="stTestResceduleColor"; 	     										break;
	case "11": statusColorClass="stTestResceduleColor"; 	     										break;
	case "12": statusColorClass="stSampleCancleColor"; 	       											break;
	case "13": statusColorClass="stWaitReportPntColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='</span>';																	break;
	case "14": statusColorClass="stResultPrintColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='</span>';																	break;
	case "15": statusColorClass="stTestCancleColor";           											break;
	case "16": statusColorClass="stTestDeleteColor"; 		   											break;
	case "17": statusColorClass="stMachineProcessColor"; 	     										break;
	case "18": statusColorClass="stMachineProcessColor"; 	     										break;
	case "26": statusColorClass="stReportGenColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='</span>';																	break;
	case "27": statusColorClass="stDraftResultEnteredColor";
				stIcon='<span class="stIcon ml-auto"> <a class=" fas fa-align-left showParamBtn" key='+strKey+'></a> </span>';		break;
	case "28": statusColorClass="stDraftResultValidColor";
				stIcon='<span class="stIcon ml-auto"> <a class=" fas fa-align-left showParamBtn" key='+strKey+'></a> </span>'; 		break;
	case "55": statusColorClass="stTestAdvisedColor"; 		     										break;

	default: statusColorClass="";
	}

	var customCell="<span class='reqnStausA d-flex font-weight-bold "+statusColorClass+"'>"+"  "+data+"&nbsp;&nbsp; "+stIcon+" &nbsp;&nbsp;</span>";
return customCell;
};

function decideTurnAroundTimeIcon(data, type, row){
	var tatColorClass="";
	var stIcon="";
	switch(row.turnAroundTime.totDecisionCode){
	case "0":  tatColorClass="tatNothingColor";
				tatIcon='<span class="tatIcon ml-auto"><i class=" "></i></span>';                             break;

	case "1":  tatColorClass="tatGenBeforeColor";
				tatIcon='<span class="tatIcon ml-auto"><i class="text-success far fa-check-circle"></i><img class="tatImg" height="27" width="27" src="media/images/medal1Green.svg"></span>';  break;

	case "2":  tatColorClass="tatGenWithinColor";
				tatIcon='<span class="tatIcon ml-auto"><i class="text-success far fa-check-circle"></i><img class="tatImg" height="27" width="27" src="media/images/medal2.svg"></span>';	       break;

	case "3":  tatColorClass="tatGenDelayColor";
				tatIcon='<span class="tatIcon ml-auto"><i class="text-success far fa-check-circle"></i><img class="tatImg" height="23" width="23" src="media/images/delayMahroon.svg"></span>';	break;

	case "4":  tatColorClass="tatNotGenWithinColor";
				tatIcon='<span class="tatIcon ml-auto"><i class="text-danger  fas fa-exclamation"></i> <img class="tatImg" height="23" width="23" src="media/images/hourglassYellow.svg"></span>';		    break;

	case "5":  tatColorClass="tatNotGenDelayColor";
			   	tatIcon='<span class="tatIcon ml-auto"><i class="text-danger  fas fa-exclamation"></i> <img class="tatImg" height="23" width="23" src="media/images/delayRed.svg"></span>';  break;

	default:   tatColorClass="";
	}
	var customCell="<a class='reqnTatA d-flex "+tatColorClass+"'>"+"  "+data+"&nbsp;&nbsp;"+tatIcon+"&nbsp;&nbsp;</a>";
return customCell;
};

function customNoteData(data, type, row){
	var noteDate='<span class="text-wrap">'+data+'</span>';
	return noteDate;
}

function customGroupTestNameData(data, type, row){
	var testNameData='<span class="text-wrap">'+data+'</span>';
	return testNameData;
}


/*--Functions For Returning custom Requsition Status Cell with css color class Ends-----------------*/

/*--Functions For Returning custom Table Rows According To Screen Size Type1 patientDetails Starts---*/
var crcount = 0;
var crcount2 = 0;

function customeRowType1(col) {
  var tbdat = '';
  var w = window.innerWidth;
  var h = window.innerHeight;
  if (w >= 1100) {
    tbdat = customeRowType11(col, 5);
  } else if (w < 1100 && w > 880) {
    tbdat = customeRowType11(col, 3);
  } else if (w < 880 && w > 600) {
    tbdat = customeRowType11(col, 2);
  } else if (w <= 600) {
    tbdat = customeRowType11(col, 1);
  }
  return tbdat;
}


// function customeRowType11(col) {
// 	  var tbdat = '';
// 	  if (crcount == 0) {
// 	    tbdat = '<tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">';
// 	    crcount++;
// 	  }
// 	  if (crcount2 >= 5) {
// 	    tbdat = '</tr><tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">';
// 	    crcount2 = 0;
// 	  }
// 	  tbdat += '<th>' + col.title + ':' + '</th> ' +
// 	    '<td>' + col.data + '</td>';
// 	  crcount2++;
// 	  return tbdat;
// 	}


// function customeRowType14(col) {
//   var tbdat = '<tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">' +
//     '<th>' + col.title + ':' + '</th> ' +
//     '<td>' + col.data + '</td>' +
//     '</tr>';
//   return tbdat;
// }

function customeRowType11(col, noOfThPairInOneRow) {
	  var tbdat = '';
		if (crcount2 >= noOfThPairInOneRow) {
	    tbdat = '</tr>'; crcount2 = 0; crcount = 0;
	  }
		if (crcount == 0) {
	    tbdat = '<tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">';
	    crcount++;
	  }
	  tbdat += '<th>' + col.title + ':' + '</th> ' +
	    			 '<td>' + col.data + '</td>';
	  crcount2++;
	  return tbdat;
	}

/*--Functions For Returning custom Table Rows According To Screen Size Type1 patientDetails Ends-----*/


/*--Functions For Returning custom Table Rows According To Screen Size Type2 SampleBasedReqn Starts--*/
function customeRowSmType2(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';
  var w = window.innerWidth;
  var h = window.innerHeight;
  if (w >= 1100) {
    tbdat = customeRowSmType21(api, rowIdx, columns, columnIndexFromTo);
  } else if (w < 1100 && w > 800) {
    tbdat = customeRowSmType22(api, rowIdx, columns, columnIndexFromTo);
  } else if (w < 800 && w > 600) {
    tbdat = customeRowSmType23(api, rowIdx, columns, columnIndexFromTo);
  } else if (w <= 600) {
    tbdat = customeRowSmType24(api, rowIdx, columns, columnIndexFromTo);
  }
  return tbdat;
}

function customeRowPtType2(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';
  var w = window.innerWidth;
  var h = window.innerHeight;
  if (w >= 1100) {
    tbdat = customeRowPtType21(api, rowIdx, columns, columnIndexFromTo);
  } else if (w < 1100 && w > 800) {
    tbdat = customeRowPtType22(api, rowIdx, columns, columnIndexFromTo);
  } else if (w < 800 && w > 600) {
    tbdat = customeRowPtType23(api, rowIdx, columns, columnIndexFromTo);
  } else if (w <= 600) {
    tbdat = customeRowPtType24(api, rowIdx, columns, columnIndexFromTo);
  }
  return tbdat;
}

function customeRowSmType21(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';

  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';

  tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.sampleColl.from, columnIndexFromTo.sampleColl.to) + '</table></td>';

  tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.packListGen.from, columnIndexFromTo.packListGen.to) + '</table></td>';

  tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleAccep.from, columnIndexFromTo.sampleAccep.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleReje.from, columnIndexFromTo.sampleReje.to) + '</table></td>';

  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';

  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowSmType22(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';
  
  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';

  tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.sampleColl.from, columnIndexFromTo.sampleColl.to) + '</table></td>';

  tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.packListGen.from, columnIndexFromTo.packListGen.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleAccep.from, columnIndexFromTo.sampleAccep.to) + '</table></td>';

  tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleReje.from, columnIndexFromTo.sampleReje.to) + '</table></td>';

  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowSmType23(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';

  /*0 to 6 as columns 0 to 6 are the columns with class not having none and
  are the max. columns defined to shown on full width screen*/
  var colHidArray = [];
  var colHidStart = "";
  var colHidEnd = "";
  var colHidBoolean = false;
  for (i = 0; i <= 10; i++) {
    if (columns[i].hidden) {
      colHidBoolean = true;
      colHidArray.push(i);
    }
  }
  colHidStart = colHidArray[0];
  colHidEnd = colHidArray[colHidArray.length - 1];

  if (colHidBoolean) {
    tbdat += '<tr>'
    tbdat += '<td class="rounded essenDataColor"><div class="vertical-text">Essential Data</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, colHidStart, colHidEnd) + '</table></td>';
    
    tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.sampleColl.from, columnIndexFromTo.sampleColl.to) + '</table></td>';

    tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.packListGen.from, columnIndexFromTo.packListGen.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleAccep.from, columnIndexFromTo.sampleAccep.to) + '</table></td>';

    tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleReje.from, columnIndexFromTo.sampleReje.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';

    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
    tbdat += '</tr>'

  } else {
    /*":when none of the default Essential columns are hidden*/
    tbdat += '<tr>'
    tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';

    tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.sampleColl.from, columnIndexFromTo.sampleColl.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.packListGen.from, columnIndexFromTo.packListGen.to) + '</table></td>';

    tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleAccep.from, columnIndexFromTo.sampleAccep.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleReje.from, columnIndexFromTo.sampleReje.to) + '</table></td>';

    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
    tbdat += '</tr>'
  }
  return tbdat;
}

function customeRowSmType24(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';

  /*0 to 6 as columns 0 to 6 are the columns with class not having none and
  are the max. columns defined to shown on full width screen*/
  var colHidArray = [];
  var colHidStart = "";
  var colHidEnd = "";
  var colHidBoolean = false;
  for (i = 0; i <= 10; i++) {
    if (columns[i].hidden) {
      colHidBoolean = true;
      colHidArray.push(i);
    }
  }
  colHidStart = colHidArray[0];
  colHidEnd = colHidArray[colHidArray.length - 1];

  if (colHidBoolean) {
    tbdat += '<tr>'
    tbdat += '<td class="rounded essenDataColor"><div class="vertical-text">Essential Data</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, colHidStart, colHidEnd) + '</table></td>';
    tbdat += '</tr>'
  }
 
  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.sampleColl.from, columnIndexFromTo.sampleColl.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.packListGen.from, columnIndexFromTo.packListGen.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleAccep.from, columnIndexFromTo.sampleAccep.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.sampleReje.from, columnIndexFromTo.sampleReje.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}
/*--Functions For Returning custom Table Rows According To Screen Size Type2 SampleBasedReqn Ends----*/

/*--Functions For Returning custom Table Rows According To Screen Size Type2 PatientBasedReqn Starts-*/
function customeRowPtType21(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';
  
  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';

  tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.patAccep.from, columnIndexFromTo.patAccep.to) + '</table></td>';

  tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.patReje.from, columnIndexFromTo.patReje.to) + '</table></td>';

  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowPtType22(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';
  
  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';

  tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.patAccep.from, columnIndexFromTo.patAccep.to) + '</table></td>';

  tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.patReje.reqnRaising.from, columnIndexFromTo.patReje.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';

  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowPtType23(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';

  /*0 to 6 as columns 0 to 6 are the columns with class not having none and
  are the max. columns defined to shown on full width screen*/
  var colHidArray = [];
  var colHidStart = "";
  var colHidEnd = "";
  var colHidBoolean = false;
  for (i = 0; i <= 10; i++) {
    if (columns[i].hidden) {
      colHidBoolean = true;
      colHidArray.push(i);
    }
  }
  colHidStart = colHidArray[0];
  colHidEnd = colHidArray[colHidArray.length - 1];
  
  if (colHidBoolean) {
    tbdat += '<tr>'
    tbdat += '<td class="rounded essenDataColor"><div class="vertical-text">Essential Data</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, colHidStart, colHidEnd) + '</table></td>';

    tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.patAccep.from, columnIndexFromTo.patAccep.to) + '</table></td>';

    tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.patReje.from, columnIndexFromTo.patReje.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';

    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
    tbdat += '</tr>'

  } else {
    /*case when none of the default Essential columns are hidden*/
	 
    tbdat += '<tr>'
    tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';

    tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.patAccep.from, columnIndexFromTo.patAccep.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.patReje.from, columnIndexFromTo.patReje.to) + '</table></td>';

    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
    tbdat += '</tr>'
  }
  return tbdat;
}

function customeRowPtType24(api, rowIdx, columns, columnIndexFromTo) {
  var tbdat = '';

  /*0 to 6 as columns 0 to 6 are the columns with class not having none and
  are the max. columns defined to shown on full width screen*/
  var colHidArray = [];
  var colHidStart = "";
  var colHidEnd = "";
  var colHidBoolean = false;
  for (i = 0; i <= 10; i++) {
    if (columns[i].hidden) {
      colHidBoolean = true;
      colHidArray.push(i);
    }
  }
  colHidStart = colHidArray[0];
  colHidEnd = colHidArray[colHidArray.length - 1];

  if (colHidBoolean) {
    tbdat += '<tr>'
    tbdat += '<td class="rounded essenDataColor"><div class="vertical-text">Essential Data</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, colHidStart, colHidEnd) + '</table></td>';
    tbdat += '</tr>'
  }
 
  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.reqnRaising.from, columnIndexFromTo.reqnRaising.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, columnIndexFromTo.patAccep.from, columnIndexFromTo.patAccep.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.patReje.from, columnIndexFromTo.patReje.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded "><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.resultEnt.from, columnIndexFromTo.resultEnt.to) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, columnIndexFromTo.reportGen.from, columnIndexFromTo.reportGen.to) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}


function dataRow(columns, iStart, iEnd) {
  var dataRow = '';

  for (i = iStart; i <= iEnd; i++) {
    var col = columns[i];

    dataRow += '<tr class="rowhover" data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">';
    dataRow += '<th>' + col.title + ':' + '</th> ' + '<td>' + col.data + '</td>';
    dataRow += '</tr>';
  }
  return dataRow;
}
/*--Functions For Returning custom Table Rows According To Screen Size Type2 PatientBasedReqn Ends--*/

function customeRowDataAppend(data) {
  crcount = 0;
  crcount2 = 0;
  //data+='</tr>';
  var rt = $('<table/>').append(data);
  return rt;
}
/*--Functions For Returning custom Table Rows According To Screen Size Type1,2 Ends-----------------*/


/*--------------------------------------------DataTables Custom Rows Creation Ends----------------------------------------------------------------------------*/
