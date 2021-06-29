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


var globalSearchParam = { hospitalCode:"", crNo:"", billNo:"", requisitionNo:"", requisitionDNo:"", searchType:"", testCode:"", groupCode:"", isGroup:"",
		forTestOrGroupOrAll:"", dataFromArchival:"", fromDate:"", toDate: "", hmode:""};

function setGlobalSearchParam(callByAutoOrEvent){
	var hospitalCode=null;
	var crNo=null;
	var billNo=null;
	var sampleNo=null; 
	var labNo=null;
	var requisitionNo=null;
	var requisitionDNo=null;
	var searchType=null;
	var testCode=null;
	var groupCode=null;
	var isGroup=null;
	var forTestOrGroupOrAll=null;
	
	if(callByAutoOrEvent=="1") {
		
		hospitalCode=$('[name="hospitalCode"]')[0].value;
		crNo=$('[name="crNo"]')[0].value;
		billNo=$('[name="billNo"]')[0].value;
		sampleNo=$('[name="sampleNo"]')[0].value;
		labNo=$('[name="labNo"]')[0].value;
		requisitionNo=$('[name="requisitionNo"]')[0].value;
		requisitionDNo=$('[name="requisitionDNo"]')[0].value;
		
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
		
	} else if (callByAutoOrEvent=="2") {
		
		hospitalCode=$('[name="hospitalCode"]')[0].value;
		crNo=$('#crNoInput').val();
		billNo=$('#billNoInput').val();
		sampleNo=$('#sampleNoInput').val();
		labNo=$('#labNoInput').val();
		requisitionNo=$('#requisitionNoInput').val();
		requisitionDNo=$('#requisitionDNoInput').val();
		searchType=$('#inputTypeBC').val();
		testCode=null;
		groupCode=null;
		isGroup=null;
		forTestOrGroupOrAll=0;
	}
	
	globalSearchParam.hospitalCode= hospitalCode;
	globalSearchParam.crNo= crNo;
	globalSearchParam.billNo= billNo;
	globalSearchParam.requisitionNo= requisitionNo;
	globalSearchParam.requisitionDNo= requisitionDNo;
	globalSearchParam.searchType= searchType;
	globalSearchParam.testCode= testCode;
	globalSearchParam.groupCode= groupCode;
	globalSearchParam.isGroup= isGroup;
	globalSearchParam.forTestOrGroupOrAll= forTestOrGroupOrAll;
	
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
		UrlImplicitCallHandler();
	} else {
		UrlImplicitCallHandler();
	}
});

function UrlSimpleCallHandler(){

}

var setIntervalTestParamTable1=null;
var setIntervalTestParamTable2=null;
function UrlImplicitCallHandler(){
	
	showLoading(true);
    setGlobalSearchParam("1");
    var flagGoodToGo = false;
    	
	/*---------------------------------------------------*/
	if(globalSearchParam.searchType=="1" && globalSearchParam.crNo.length=="15") {
		$('#inputTypeBC').val("1");
		$('#inputTypeBC').trigger("change");
		$('#crNoInput').val(globalSearchParam.crNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="2" && globalSearchParam.billNo.length=="15"){
		$('#inputTypeBC').val("2");
		$('#inputTypeBC').trigger("change");
		$('#billNoInput').val(globalSearchParam.billNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="3" && globalSearchParam.sampleNo.length=="15"){
		$('#inputTypeBC').val("3");
		$('#inputTypeBC').trigger("change");
		$('#sampleNoInput').val(globalSearchParam.sampleNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="4" && globalSearchParam.labNo.length=="15"){
		$('#inputTypeBC').val("4");
		$('#inputTypeBC').trigger("change");
		$('#labNoInput').val(globalSearchParam.labNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="5" && globalSearchParam.requisitionNo.length=="15"){
		$('#inputTypeBC').val("5");
		$('#inputTypeBC').trigger("change");
		$('#requisitionNoInput').val(globalSearchParam.requisitionNo);
		flagGoodToGo = true;
	} else if (globalSearchParam.searchType=="6" && globalSearchParam.requisitionDNo.length=="15"){
		$('#inputTypeBC').val("6");
		$('#inputTypeBC').trigger("change");
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
	     
	     $.when( ajaxObjectArr.AjaxGetReqnTestParamList ).then(function( data, textStatus, jqXHR ) {
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

/*----------On Change in input type Cr.NO Or Bill No. Or Sample No. starts--------------------*/
$(document).ready(function() {

  $('#inputTypeBC').change(function() {
    var inputTypeBCSL = $(this).val();

    /*inputTypeBC 1 is that search made on Cr no.*/
    if (inputTypeBCSL == "1") {
    	$('#crNoInput').removeClass("d-none");
    	$('#billNoInput').addClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#requisitionNoInput').addClass("d-none");
        $('#requisitionDNoInput').addClass("d-none");
        showLoading(false);
    }
    /*inputTypeBC 2 search made on Bill no.*/
    else if (inputTypeBCSL == "2") {
    	$('#crNoInput').addClass("d-none");
    	$('#billNoInput').removeClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#requisitionNoInput').addClass("d-none");
        $('#requisitionDNoInput').addClass("d-none");
        showLoading(false);
    }
    /*inputTypeBC 3 search made on sample. no.*/
    else if (inputTypeBCSL == "3") {
      $('#crNoInput').addClass("d-none");
      $('#billNoInput').addClass("d-none");
      $('#sampleNoInput').removeClass("d-none");
      $('#labNoInput').addClass("d-none");
      $('#requisitionNoInput').addClass("d-none");
      $('#requisitionDNoInput').addClass("d-none");
      showLoading(false);
    }
    /*inputTypeBC 3 search made on lab no.*/
    else if (inputTypeBCSL == "4") {
      $('#crNoInput').addClass("d-none");
      $('#billNoInput').addClass("d-none");
      $('#sampleNoInput').addClass("d-none");
      $('#labNoInput').removeClass("d-none");
      $('#requisitionNoInput').addClass("d-none");
      $('#requisitionDNoInput').addClass("d-none");
      showLoading(false);
    }
    /*inputTypeBC 3 search made on requisition no.*/
    else if (inputTypeBCSL == "5") {
	  $('#crNoInput').addClass("d-none");
	  $('#billNoInput').addClass("d-none");
      $('#sampleNoInput').addClass("d-none");
      $('#labNoInput').addClass("d-none");
      $('#requisitionNoInput').removeClass("d-none");
      $('#requisitionDNoInput').addClass("d-none");
      showLoading(false);
    }
    /*inputTypeBC 3 search made on requisition Dno.*/
    else if (inputTypeBCSL == "6") {
      $('#crNoInput').addClass("d-none");
      $('#billNoInput').addClass("d-none");
      $('#sampleNoInput').addClass("d-none");
      $('#labNoInput').addClass("d-none");
      $('#requisitionNoInput').removeClass("d-none");
      $('#requisitionDNoInput').addClass("d-none");
      showLoading(false);
    }
  });
});
/*--------------------------------------------------------------------------------------------*/
/*----------On Click Getdata Button starts----------------------------------------------------*/
$(document).ready(function() {

  $('#getData').click(function() {

    showLoading(true);
    setGlobalSearchParam("2");
    var flagGoodToGo = false;
    var searchType = $('#inputTypeBC').val();
   
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

/*----------On Button Click Show From to Months for archival search Starts--------------------*/
$(document).ready(function() {
  $('#btnDataFromArchival1').click(function (){
      $('.inputDates').removeClass("d-none");
      $('.divArchivalbtn1').addClass("d-none");
      $('.divArchivalbtn2').removeClass("d-none");
  });
  $('#btnDataFromArchival2').click(function (){
      $('.inputDates').addClass("d-none");
      $('.divArchivalbtn2').addClass("d-none");
      $('.divArchivalbtn1').removeClass("d-none");
  });
});
/*------------------------------------------------------*/





/*----------Function to Set Events Listeners After Requisition List Table Fetched Starts--------*/

function setEventListenersAfterReqnListTableDrawn(){

/*-----Click Event on PDF button click---*/

if($('.showReportPdfBtn').length){
	$(".showReportPdfBtn").off().click(function (){
		var pdfName=this.attributes.value.value;

		pdfName+="@@";
		//var obj=document.getElementsByName("reportt");
		// var pdfname="";
		/*for(var a=0;a<obj.length;a++)
		   {if(obj[a].checked==true){pdfname+=obj[a].value+"@@";}}*/

		var _mode = "AjaxGetPDFReport";
		var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&selectedPDFName="+pdfName;

		if($('#showReportPdfModalIframe'))
		$('#showReportPdfModalIframe').remove();

		$('#showReportPdfModal .modal-body').prepend('<iframe id="showReportPdfModalIframe" style="width:100%;height:75vh;" src="'+ url+'"></iframe>');
		$('#showReportPdfModal').modal('show');
	});


 	tippy('.showReportPdfBtn', {
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

	tippy('.showParamBtn', {
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

}

}


/*----------Function to Set Events Listeners After Requstion List Table Fetched Starts--------*/

/*------------------------------------------Event Actions Ends------------------------------------------------------------------------------------------------*/


/*------------------------------------------Ajax Calls Starts-------------------------------------------------------------------------------------------------*/

var globalPatientDetails = null;

var globalSampleBasedReqnList = null;
var globalPatientBasedReqnList = null;
var deferredObjectArr =null;
var ajaxObjectArr ={};


function AjaxGetReqnList(globalSearchParam) {
  globalSampleBasedReqnList = null;
  globalPatientBasedReqnList = null;

  var _mode = "AjaxGetReqnList";

  /*var crNo=globalSearchParam.crNo;
  var billNo=globalSearchParam.billNo;
  var searchType=globalSearchParam.searchType;
  var forTestOrGroupOrAll=globalSearchParam.forTestOrGroupOrAll;
  var testCode=globalSearchParam.testCode;
  var groupCode=globalSearchParam.groupCode;*/

  var dataFromArchival="0";
  if($('.inputDates').is(":hidden")){
    dataFromArchival="0";
  } else {
    dataFromArchival="1";
  }

  var fromDate = document.getElementsByClassName("fromDateInput")[0].value;
  var toDate = document.getElementsByClassName("toDateInput")[0].value;
 
  globalSearchParam.hmode = _mode;
  globalSearchParam.dataFromArchival = dataFromArchival;
  globalSearchParam.fromDate = fromDate;
  globalSearchParam.toDate = toDate;
  
  var url = "/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?"+ $.param(globalSearchParam);
  
  /*"&crNo=" + crNo + "&billNo=" + billNo + "&dataFromArchival=" +dataFromArchival+
  "&fromDate=" +fromDate+ "&toDate=" +toDate+"&searchType="+searchType
  +"&forTestOrGroupOrAll="+forTestOrGroupOrAll+"&testCode="+testCode+"&groupCode="+groupCode;*/

  ajaxObjectArr.AjaxGetReqnList=$.getJSON(url, function(data) {
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

/*------------------------------------------Ajax Calls Ends---------------------------------------------------------------------------------------------------*/



/*------------------------------------------DataTables Initialization starts----------------------------------------------------------------------------------*/

function dataTableSampleBasedReqnList(sampleBasedReqnList) {
  var groupColumn = 1;
  $('#DataTable4').DataTable().clear().destroy();

  $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
  var table = $('#DataTable4').DataTable({

	  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
	  processing: true,
	  "scrollY":        "300px",
	  "scrollCollapse": true,
	  "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	  "columnDefs": [
		  { "orderable": false, "targets": 0 },
		  { "visible": false, "targets": groupColumn },
		  { "render": function ( data, type, row )
			  { return decideReqnStatusColor(data, type, row); },
			className: "reqnStaus",
			"targets": 5
		  },
		  { "render": function ( data, type, row )
			  { return decideTurnAroundTimeIcon(data, type, row); },
            className: "reqnTat",
            "targets": 6
		  },
		  {	"render": function ( data, type, row )
			  { return customNoteData(data, type, row); },
            "targets": 10
		  },
		  { "render": function ( data, type, row )
			  { return customGroupTestNameData(data, type, row); },
		  	"targets": 4
		  },
      ],
      "order": [[ 1, "desc" ]],
      "language": { "emptyTable": "No Data Is Available " },
      "order": [ [groupColumn, 'desc'] ],
      "drawCallback": function(settings) {
    	  var api = this.api();
    	  var rows = api.rows({ page: 'current' }).nodes();
    	  var last = null;
    	  // Output the data for the visible rows to the browser's console
    	  //console.log( api.rows( {page:'current'} ).data() );
    	  //Group rows On every table draw event
    	  api.column(groupColumn, { page: 'current' }).data().each(function(group, i) {
    		  if (last !== group) {
    			  $(rows).eq(i).before(
    					  '<tr class="group"><td colspan="10" class="rowGroupReqnDate"><strong>Requisition Date : </strong>' + api.context[0].aoData[i]._aData.requisitionDate + '</td></tr>'
    			  );
    			  last = group;
    		  }
    	  });
    	  //set events active on every table draw event
    	  setEventListenersAfterReqnListTableDrawn();
      },
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
    	  { "data": 'sno' },
          { "data": 'requisitionNo' },
          { "data": 'labName' },
          { "data": 'advisedByDept' },
          { "data": 'groupNameTestName' },
          { "data": 'requisitionStatus' },
          { "data": 'turnAroundTime.turnAroundTime' },
          { "data": 'sampleNo' },
          { "data": 'testRate' },
          { "data": 'billedUnbilled' },
          { "data": 'testNote' },
          { "data": 'requisitionDate' },
          { "data": 'requisitionBy' },
          { "data": 'advisedByDoc' },
          { "data": 'groupNameTestName' },
          { "data": 'appointmentDate' },
          { "data": 'billNo' },
          { "data": 'billDate' },
          { "data": 'sampleName' },
          { "data": 'sampleNo' },
          { "data": 'sampleCollDate' },
          { "data": 'sampleCollBy' },
          { "data": 'packingListNo' },
          { "data": 'labNo' },
          { "data": 'packingListDateTime' },
          { "data": 'packingListBy' },
          { "data": 'labNo' },
          { "data": 'sampleAccepDate' },
          { "data": 'sampleAccepBy' },
          { "data": 'sampleAccepMode' },
          { "data": 'machineName' },
          { "data": 'sampleRejecDate' },
          { "data": 'sampleRejecBy' },
          { "data": 'sampleRejecReason' },
          { "data": 'resultEntryDate' },
          { "data": 'resultEntryBy' },
          { "data": 'resultValidDate' },
          { "data": 'resultValidBy' },
          { "data": 'reportGenerationDate' },
          { "data": 'reportPrintDate' },
          { "data": 'reportPrintBy' }
      ],
      "sPaginationType": "full_numbers",
      "bJQueryUI": true,
      responsive: {
    	  details: {
    		  renderer: function(api, rowIdx, columns) {
    			  var data = customeRowSmType2(api, rowIdx, columns);
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
      setEventListenersAfterReqnListTableDrawn();
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
    });
  
  //Set Tab Notification Badge
  $('.sampleTestBadgeCount').text(table.rows().count());

  // Handle click on "Epand All" button
  $('table .expandButton').on('click', function() {
    expandColapseRow(true, table);
  });

  // Handle click on "Collapse All" button
  $('table .collapseButton').on('click', function() {
    expandColapseRow(false, table);
  });

  // Order by the grouping
  $('#DataTable4 tbody').on('click', 'tr.group', function() {
    var currentOrder = table.order()[0];
    if(currentOrder=="undefined" || currentOrder==undefined)
    {table.order([groupColumn, 'asc']).draw();
    }else{
	    if (currentOrder[0] === groupColumn && currentOrder[1] === 'asc') {
	      table.order([groupColumn, 'desc']).draw();
	    } else {
	      table.order([groupColumn, 'asc']).draw();
	    }
    }
  });

}

function dataTablePatientBasedReqnList(patientBasedReqnList) {
  var groupColumn = 1;
  $('#DataTable5').DataTable().clear().destroy();

  $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
  var table = $('#DataTable5').DataTable({

	  "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
	  processing: true,
	  "scrollY":        "300px",
	  "scrollCollapse": true,
	  "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	  "columnDefs": [
		  { "orderable": false, "targets": 0 },
		  { "visible": false, "targets": groupColumn },
		  { "render": function ( data, type, row )
			  { return decideReqnStatusColor(data, type, row); },
            className: "reqnStaus",
            "targets": 5
		  },
		  { "render": function ( data, type, row )
			  { return decideTurnAroundTimeIcon(data, type, row); },
            className: "reqnTat",
            "targets": 6
		  },
		  {	"render": function ( data, type, row )
			  { return customNoteData(data, type, row); },
            "targets": 10
          },
          {	"render": function ( data, type, row )
        	  { return customGroupTestNameData(data, type, row); },
            "targets": 4
          },
      ],
      "order": [[ 1, "desc" ]],
      "language": { "emptyTable": "No Data Is Available " },
      "order": [ [groupColumn, 'desc'] ],
      "drawCallback": function(settings) {
    	  var api = this.api();
    	  var rows = api.rows({ page: 'current' }).nodes();
    	  var last = null;
    	  //Group rows On every table draw event
    	  api.column(groupColumn, { page: 'current' }).data().each(function(group, i) {
    		  if (last !== group) {
    			  $(rows).eq(i).before(
    					  '<tr class="group"><td colspan="10" class="rowGroupReqnDate"><strong>Requisition Date : </strong>' + api.context[0].aoData[i]._aData.requisitionDate + '</td></tr>'
    			  );
    			  last = group;
    		  }
    	  });
    	  //set events active on every table draw event
    	  setEventListenersAfterReqnListTableDrawn();
      },
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
    	  { "data": 'sno' },
          { "data": 'requisitionNo' },
          { "data": 'labName' },
          { "data": 'advisedByDept' },
          { "data": 'groupNameTestName' },
          { "data": 'requisitionStatus' },
          { "data": 'turnAroundTime.turnAroundTime' },
          { "data": 'accessionNo' },
          { "data": 'testRate' },
          { "data": 'billedUnbilled' },
          { "data": 'testNote' },
          { "data": 'requisitionDate' },
          { "data": 'requisitionBy' },
          { "data": 'advisedByDoc' },
          { "data": 'groupNameTestName' },
          { "data": 'appointmentDate' },
          { "data": 'billNo' },
          { "data": 'billDate' },
          { "data": 'accessionNo' },
          { "data": 'patientAccepDate' },
          { "data": 'patientAccepBy' },
          { "data": 'patientAccepMode' },
          { "data": 'machineName' },
	      { "data": 'patientRejecDate' },
	      { "data": 'patientRejecBy' },
	      { "data": 'patientRejecReason' },
	      { "data": 'resultEntryDate' },
	      { "data": 'resultEntryBy' },
	      { "data": 'resultValidDate' },
	      { "data": 'resultValidBy' },
	      { "data": 'reportGenerationDate' },
	      { "data": 'reportPrintDate' },
	      { "data": 'reportPrintBy' }
	  ],
	  "sPaginationType": "full_numbers",
	  "bJQueryUI": true,
	  responsive: {
		  details: {
			  renderer: function(api, rowIdx, columns) {
				  var data = customeRowPtType2(api, rowIdx, columns);
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
      setEventListenersAfterReqnListTableDrawn();
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
    });
  
  //Set Tab Notification Badge
  $('.patientTestBadgeCount').text(table.rows().count());

  // Handle click on "Epand All" button
  $('table .expandButton').on('click', function() {
    expandColapseRow(true, table);
  });

  // Handle click on "Collapse All" button
  $('table .collapseButton').on('click', function() {
    expandColapseRow(false, table);
  });

  // Order by the grouping
  $('#DataTable5 tbody').on('click', 'tr.group', function() {
    var currentOrder = table.order()[0];
    if(currentOrder=="undefined" || currentOrder==undefined)
    {table.order([groupColumn, 'asc']).draw();
    }else{
	    if (currentOrder[0] === groupColumn && currentOrder[1] === 'asc') {
	      table.order([groupColumn, 'desc']).draw();
	    } else {
	      table.order([groupColumn, 'asc']).draw();
	    }
    }
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
				stIcon='<span class="stIcon ml-auto"> <a class=" fas fa-align-left showParamBtn" key='+strKey+'></a> </span>';		break;
	case "8":  statusColorClass="stResultvalidColor";
				stIcon='<span class="stIcon ml-auto"> <a class=" fas fa-align-left showParamBtn" key='+strKey+'></a> </span>';		break;
	case "9":  statusColorClass="stPatRejectcolor"; 		       										break;
	case "10": statusColorClass="stTestResceduleColor"; 	     										break;
	case "11": statusColorClass="stTestResceduleColor"; 	     										break;
	case "12": statusColorClass="stSampleCancleColor"; 	       											break;
	case "13": statusColorClass="stWaitReportPntColor";
				stIcon='<span class="stIcon ml-auto"> <a class=" fas fa-align-left showParamBtn" key='+strKey+'></a> </span>';		break;
	case "14": statusColorClass="stResultPrintColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='<a  class=" far fa-file-pdf showReportPdfBtn" value="'+row.reportURL+'">';
				stIcon+='</a></span>';																break;
	case "15": statusColorClass="stTestCancleColor";           											break;
	case "16": statusColorClass="stTestDeleteColor"; 		   											break;
	case "17": statusColorClass="stMachineProcessColor"; 	     										break;
	case "18": statusColorClass="stMachineProcessColor"; 	     										break;
	case "26": statusColorClass="stReportGenColor";
				stIcon='<span class="stIcon ml-auto">';
				stIcon+='<a class=" fas fa-align-left showParamBtn" key='+strKey+'>&nbsp;</a>';
				stIcon+='<a  class=" far fa-file-pdf showReportPdfBtn" value="'+row.reportURL+'">';
				stIcon+='</a></span>';																	break;
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
function customeRowSmType2(api, rowIdx, columns) {
  var tbdat = '';
  var w = window.innerWidth;
  var h = window.innerHeight;
  if (w >= 1100) {
    tbdat = customeRowSmType21(api, rowIdx, columns);
  } else if (w < 1100 && w > 800) {
    tbdat = customeRowSmType22(api, rowIdx, columns);
  } else if (w < 800 && w > 600) {
    tbdat = customeRowSmType23(api, rowIdx, columns);
  } else if (w <= 600) {
    tbdat = customeRowSmType24(api, rowIdx, columns);
  }
  return tbdat;
}

function customeRowPtType2(api, rowIdx, columns) {
  var tbdat = '';
  var w = window.innerWidth;
  var h = window.innerHeight;
  if (w >= 1100) {
    tbdat = customeRowPtType21(api, rowIdx, columns);
  } else if (w < 1100 && w > 800) {
    tbdat = customeRowPtType22(api, rowIdx, columns);
  } else if (w < 800 && w > 600) {
    tbdat = customeRowPtType23(api, rowIdx, columns);
  } else if (w <= 600) {
    tbdat = customeRowPtType24(api, rowIdx, columns);
  }
  return tbdat;
}

function customeRowSmType21(api, rowIdx, columns) {
  var tbdat = '';

  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';

  tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 18, 21) + '</table></td>';

  tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 22, 25) + '</table></td>';

  tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 30) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 31, 33) + '</table></td>';

  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 34, 37) + '</table></td>';

  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 38, 40) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowSmType22(api, rowIdx, columns) {
  var tbdat = '';

  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';

  tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 18, 21) + '</table></td>';

  tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 22, 25) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 30) + '</table></td>';

  tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 31, 33) + '</table></td>';

  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 34, 37) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 38, 40) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowSmType23(api, rowIdx, columns) {
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
    tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, 18, 21) + '</table></td>';

    tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 22, 25) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 30) + '</table></td>';

    tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 31, 33) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 34, 37) + '</table></td>';

    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 38, 40) + '</table></td>';
    tbdat += '</tr>'

  } else {
    /*":when none of the default Essential columns are hidden*/

    tbdat += '<tr>'
    tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';

    tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, 18, 21) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 22, 25) + '</table></td>';

    tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 30) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 31, 33) + '</table></td>';

    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 34, 37) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 38, 40) + '</table></td>';
    tbdat += '</tr>'
  }
  return tbdat;
}

function customeRowSmType24(api, rowIdx, columns) {
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
  tbdat += '<td class=""><table>' + dataRow(columns, 7, 13) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleCollColor"><div class="vertical-text">Sample Collection</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 18, 21) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded packListGenColor"><div class="vertical-text">PackingList Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 22, 25) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleAccepColor"><div class="vertical-text">Sample Acceptance</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 30) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded sampleRejeColor"><div class="vertical-text">Sample Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 31, 33) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 34, 37) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 38, 40) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}
/*--Functions For Returning custom Table Rows According To Screen Size Type2 SampleBasedReqn Ends----*/

/*--Functions For Returning custom Table Rows According To Screen Size Type2 PatientBasedReqn Starts-*/
function customeRowPtType21(api, rowIdx, columns) {
  var tbdat = '';

  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';

  tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 18, 22) + '</table></td>';

  tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 23, 25) + '</table></td>';

  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 29) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 30, 32) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowPtType22(api, rowIdx, columns) {
  var tbdat = '';

  tbdat += '<tr>'
  tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';

  tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 18, 22) + '</table></td>';

  tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 23, 25) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 29) + '</table></td>';

  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 30, 32) + '</table></td>';
  tbdat += '</tr>'

  return tbdat;
}

function customeRowPtType23(api, rowIdx, columns) {
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
    tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, 18, 22) + '</table></td>';

    tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 23, 25) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 29) + '</table></td>';

    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 30, 32) + '</table></td>';
    tbdat += '</tr>'

  } else {
    /*case when none of the default Essential columns are hidden*/
    tbdat += '<tr>'
    tbdat += '<td class="rounded reqnRaisingColor"><div class="vertical-text">Requisition Raising</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';

    tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
    tbdat += '<td class=""><table>' + dataRow(columns, 18, 22) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 23, 25) + '</table></td>';

    tbdat += '<td class="rounded resultEntColor"><div class="vertical-text">Result Entry/Validation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 29) + '</table></td>';
    tbdat += '</tr>'

    tbdat += '<tr>'
    tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
    tbdat += '<td class="rounded "><table>' + dataRow(columns, 30, 32) + '</table></td>';
    tbdat += '</tr>'
  }
  return tbdat;
}

function customeRowPtType24(api, rowIdx, columns) {
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
  tbdat += '<td class=""><table>' + dataRow(columns, 11, 17) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded patAccepColor"><div class="vertical-text">Patient Acceptance</div></td>';
  tbdat += '<td class=""><table>' + dataRow(columns, 18, 22) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded patRejeColor"><div class="vertical-text">Patient Rejection</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 23, 25) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded "><div class="vertical-text">Result Entry/Validation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 26, 29) + '</table></td>';
  tbdat += '</tr>'

  tbdat += '<tr>'
  tbdat += '<td class="rounded reportGenColor"><div class="vertical-text">Report Generation</div></td>';
  tbdat += '<td class="rounded "><table>' + dataRow(columns, 30, 32) + '</table></td>';
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
