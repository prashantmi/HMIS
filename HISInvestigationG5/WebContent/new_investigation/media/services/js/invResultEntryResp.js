
/*------------------------------------------Event Actions starts----------------------------------------------------------------------------------------------*/

/*----------On Click Getdata Button starts----------------------------------------------------*/
$(document).ready(function() {
  $('#getData').click(function() {
	  
    showLoading(true);
    enableEntryModifyBtn(false);
    setglobalSearchParam();
    changesBasedOnSelectedLab();
    
    var searchType = $('#inputFilterType').val();
    var dateFiltersOrBoth = $(".dateFiltersOrBothRange").val();
    
    var flagGoodToGo = false;
    if (dateFiltersOrBoth == "1" || dateFiltersOrBoth == "2") {
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
      if (flagGoodToGo == true) {
        $('.allEntryReqnList').addClass("d-none");
        $('#patDetails').removeClass("d-none");
        $('#EntryReqnListContr').removeClass("d-none");
        $('.patEntryReqnList').removeClass("d-none");
        $('#DataTable11').DataTable().clear().destroy();
        $('#DataTable12').DataTable().clear().destroy();
        $('#DataTable21').DataTable().clear().destroy();
        $('#DataTable22').DataTable().clear().destroy();
        var table0 = $('#DataTable12').DataTable({});
        var table1 = $('#DataTable22').DataTable({});
        showEntryModifyBtn();
        AjaxGetPatDetails(globalSearchParam)
        AjaxGetEntryReqnList(globalSearchParam);
        AjaxGetMachnEntryReqnList(globalSearchParam);
      }
    } else {
      $('#patDetails').addClass("d-none");
      $('.patEntryReqnList').addClass("d-none");
      $('#EntryReqnListContr').removeClass("d-none");
      $('.allEntryReqnList').removeClass("d-none");
      $('#DataTable11').DataTable().clear().destroy();
      $('#DataTable12').DataTable().clear().destroy();
      $('#DataTable21').DataTable().clear().destroy();
      $('#DataTable22').DataTable().clear().destroy();
      var table0 = $('#DataTable11').DataTable({});
      var table1 = $('#DataTable21').DataTable({});
      showEntryModifyBtn();
      AjaxGetEntryReqnList(globalSearchParam);
      AjaxGetMachnEntryReqnList(globalSearchParam);
    }
  });
});


$(document).ready(function() {
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		var tabId="#"+$(this).attr("aria-controls");
		var tableSelector=$('table.table');
		var tableList=$(tabId).find( tableSelector )
			
			for (var i=0; i<=tableList.length; i++){
				var tableId=$(tableList[i]).attr("id");
				if(tableId){
					if(tableId.includes("DataTable")){
						$(tableId).DataTable().columns.adjust().responsive.recalc();
						$(tableId).DataTable().columns.adjust().draw();
					}
				}
			}

		//$('#labSelectInput').children("option:selected")
	    //$.fn.dataTable.tables( { visible: true, api: true } ).buttons.resize();
	});

});
/*------------------------------------------------------*/


function changesBasedOnSelectedLab(){
	var labDetails = jQuery.parseJSON($('#labSelectInput').children("option:selected").attr("key"));
	
	//change datatable thead & hide machine tab based on lab 
	if (labDetails.labTypeCode=="2") { 
		$('.sampleNAccessionTh').html("Accession No.");
		$('.resultEntry-nav_Mchn').addClass("d-none");
	} else if (labDetails.labTypeCode=="0" || labDetails.labTypeCode=="1") {
		$('.sampleNAccessionTh').html("sample/Accession No.");
		$('.resultEntry-nav_Mchn').removeClass("d-none");
	}
}

//copy from clipboard function
const copyToClipboard = copiedValStr => {
  const el = document.createElement('textarea');
  el.value = copiedValStr;
  el.setAttribute('readonly', '');
  el.style.position = 'absolute';
  el.style.left = '-9999px';
  document.body.appendChild(el);
  el.select();
  document.execCommand('copy');
  document.body.removeChild(el);
};


/*----------Function to Set Events Listeners After Requisition List Table Fetched Starts--------*/
function setEventListenersAfterReqnListTableDrawn(datatableTable, groupOrderOnColumn) {
  //Event listener for CrNo button----------------------------------------------------------
  if ($('.invTrackingByCrNoBtn').length) {
    $('.invTrackingByCrNoBtn').off("click").on("click", function() {
      var invTrackingByCrNoBtnVal = jQuery.parseJSON($(this).attr("key"));
      openInvTrackingByCrNoIFrame(invTrackingByCrNoBtnVal);
    });
    $('.invTrackingByCrNoBtn').off("mouseover").on("mouseover", function() {
      var invTrackingByCrNoBtnVal = jQuery.parseJSON($(this).attr("key"));
      copyToClipboard(invTrackingByCrNoBtnVal.crNo);
    });
    if (tippyLoadingUtlInstance.invTrackingByCrNoBtn) {
      tippyLoadingUtlInstance.invTrackingByCrNoBtn.forEach(function(elementTipyInst) {
        elementTipyInst.destroy();
      });
    }
    //if(tippyLoadingUtlInstance.invTrackingByCrNoBtn){ } else {
    const tippy_invTrackingByCrNoBtn = tippy('.invTrackingByCrNoBtn', {
      delay: 100,
      arrow: true,
      arrowType: 'round',
      size: 'small',
      duration: 300,
      animation: 'shift-away-extreme',
      placement: 'top',
      allowHTML: true,
      content: '<span>Open<span style="color: aqua;"> Investigation Tracking </span></span>',
    });
    tippyLoadingUtlInstance.invTrackingByCrNoBtn = tippy_invTrackingByCrNoBtn;
    //}
  }

  if($('.addRemoveReqnBtn').length){
	  	$('.addRemoveReqnBtn').off("click").on("click", function (){
	  		var addRemoveReqnBtnVal = jQuery.parseJSON( $(this).attr("key") );
	  		 openReqnRaisingIFrame(addRemoveReqnBtnVal);
	  	});
	  	if (tippyLoadingUtlInstance.addRemoveReqnBtn) {
	        tippyLoadingUtlInstance.addRemoveReqnBtn.forEach(function(elementTipyInst) {
	          elementTipyInst.destroy();
	        });
	      }
	      const tippy_viewTemplBtn = tippy('.addRemoveReqnBtn', {
	        delay: 100,
	        arrow: true,
	        arrowType: 'round',
	        size: 'small',
	        duration: 300,
	        animation: 'shift-away-extreme',
	        placement: 'top',
	        allowHTML: true,
	        content: '<span>Add/Remove<span style="color: aqua;"> Test </span></span>',
	      });
	      tippyLoadingUtlInstance.viewTemplBtn = tippy_viewTemplBtn;
	  }

  //Event Listener for Test name button -------------------------------------------------------------
  if ($('.invTrackingTestCompareBtn').length) {
    $('.invTrackingTestCompareBtn').off("click").on("click", function() {
      var invTrackingTestCompareBtnVal = jQuery.parseJSON($(this).attr("key"));
      openInvTrackingTestCompareIFrame(invTrackingTestCompareBtnVal);
    });
    if (tippyLoadingUtlInstance.invTrackingTestCompareBtn) {
      tippyLoadingUtlInstance.invTrackingTestCompareBtn.forEach(function(elementTipyInst) {
        elementTipyInst.destroy();
      });
    }
    //if(tippyLoadingUtlInstance.invTrackingTestCompareBtn){ } else {
    const tippy_invTrackingTestCompareBtn = tippy('.invTrackingTestCompareBtn', {
      delay: 100,
      arrow: true,
      arrowType: 'round',
      size: 'small',
      duration: 300,
      animation: 'shift-away-extreme',
      placement: 'top',
      allowHTML: true,
      content: '<span><span style="color: aqua;"> Repeated Test </span>compare with previous  <span style="color: aqua;">Parameters</span></span>',
    });
    tippyLoadingUtlInstance.invTrackingTestCompareBtn = tippy_invTrackingTestCompareBtn;
    //}
  }
  //Event listener for Row check box to validate test requisition-----------------------------------------
  if ($('.rowCheckBoxes').length) {
    $('.rowCheckBoxes').off("click").on("click", function() {
      if ($(".rowCheckBoxes:checked").length) {
        enableEntryModifyBtn(true);
      } else {
        enableEntryModifyBtn(false);
      }
    });
  }
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
		
	 /*$.when(globalAjaxObjectArr.AjaxGetPatPDFReport).then(function( data, textStatus, jqXHR ) {
		var pdfNameArr= data.reportDtl.pdfNameArr;
		 for(key in pdfNameArr){
			 
			 var pdfName=pdfNameArr[key];
				pdfName+="@@";
				
				var _mode = "AjaxGetPDFReport";
				var url="/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode="+_mode+"&selectedPDFName="+pdfName;

				var pdfModalIframeId="showReportPdfModalIframe"+key
				$('#showReportPdfModal .modal-body').prepend('<iframe class="showReportPdfModalIframe" id='+pdfModalIframeId+' style="width:100%;height:75vh;" src="'+ url+'"></iframe>');
				
		 }
		 $('#showReportPdfModal').modal('show');*/
		
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
}

	function showEntryModifyBtn(){
		if(globalSearchParam.resultEntryStatusCode=="1"){
			$(".enterResultBtnDiv").removeClass("d-none");
			$(".modifyEntryBtnDiv").addClass("d-none");
		} else {
			$(".enterResultBtnDiv").addClass("d-none");
			$(".modifyEntryBtnDiv").removeClass("d-none");
		}
	}

  function enableEntryModifyBtn(enableFlag) {
    if (enableFlag == true) {
      $('#enterResultBtn').attr({
        "disabled": false
      });
      $('#modifyEntryBtn').attr({
        "disabled": false
      });
      tippyLoadingUtlInstance.enterResultBtnDiv.setContent('Please select atleast one Requisition to Enter');
      tippyLoadingUtlInstance.modifyEntryBtnDiv.setContent('Please select atleast one Requisition to Modify');
      enableRowCheckBoxes(true);
    } else if (enableFlag == false) {
      $('#enterResultBtn').attr({
        "disabled": true
      });
      $('#modifyEntryBtn').attr({
        "disabled": true
      });
      tippyLoadingUtlInstance.enterResultBtnDiv.setContent('Please select atleast one Requisition to Enter');
      tippyLoadingUtlInstance.modifyEntryBtnDiv.setContent('Please select atleast one Requisition to Modify');
      enableRowCheckBoxes(true);
    }
  }

  function enableRowCheckBoxes(enableRowChkBxFlag) {
    if (enableRowChkBxFlag == true) {
      if ($('.rowCheckBoxes').length) {
        $('.rowCheckBoxes').attr({
          "disabled": false
        });
      }
      if ($('.selectAllDtCheck1').length) {
        $('.rowCheckBoxes').attr({
          "disabled": false
        });
      }
      if ($('.selectAllDtCheck2').length) {
        $('.rowCheckBoxes').attr({
          "disabled": false
        });
      }
    } else {
      if ($('.rowCheckBoxes').length) {
        $('.rowCheckBoxes').attr({
          "disabled": true
        });
      }
      if ($('.selectAllDtCheck1').length) {
        $('.rowCheckBoxes').attr({
          "disabled": true
        });
      }
      if ($('.selectAllDtCheck2').length) {
        $('.rowCheckBoxes').attr({
          "disabled": true
        });
      }
    }
  }
  
  //tab events 
  $(function() {
	  $('#entryReqnListTabs #manEntry-tab').on('click', function (e) {
		  //e.preventDefault()
		  //$(this).tab('show')
		  $('.entryReqnListFltr_Mchn').addClass('d-none');
		  $('.entryReqnListFltr_Man').removeClass('d-none');
		});

		$('#entryReqnListTabs #mchnEntry-tab').on('click', function (e) {
		  //e.preventDefault()
		  //$(this).tab('show')
		  $('.entryReqnListFltr_Man').addClass('d-none');
		  $('.entryReqnListFltr_Mchn').removeClass('d-none');
		});
	});
  
	
  /*----------Function to Set Events Listeners After Requistion List Table Fetched Starts--------*/
  var globalSearchParam = { collAreaCode: "", labCode: "", labDetails:"", resultEntryStatusCode: "", dateFiltersOrBoth: "", dateTypeCode: "",
		  					searchType: "", fromDate: "", toDate: "", crNo: "", billNo: "", sampleNo: "", labNo: ""
  };

  function setglobalSearchParam() {
    globalSearchParam.collAreaCode = $('#collAreaSelectInput').val();
    globalSearchParam.labCode = $('#labSelectInput').val();
    globalSearchParam.labDetails = jQuery.parseJSON($('#labSelectInput').children("option:selected").attr("key"));
    globalSearchParam.resultEntryStatusCode = $('#valStatusSelectInput').val();
    globalSearchParam.dateFiltersOrBoth = $('.dateFiltersOrBothRange').val();
    globalSearchParam.dateTypeCode = $('#dateTypeSelectInput').val();
    globalSearchParam.searchType = $('#inputFilterType').val();
    globalSearchParam.fromDate = $('.fromDateInput').val();
    globalSearchParam.toDate = $('.toDateInput').val();
    globalSearchParam.crNo = $('#crNoInput').val();
	console.log("%csetglobalSearchParam() | globalSearchParam: Is Below", "color:green;");
	console.log(globalSearchParam)    

  }
  
  
  $(document).ready(function() {
    var tippy_enterResultBtn = tippy('.enterResultBtnDiv', {
      delay: 100,
      arrow: true,
      arrowType: 'round',
      size: 'large',
      duration: 500,
      animation: 'shift-away-extreme',
      placement: 'top',
      allowHTML: true,
      content: 'Please select tleast one Requisition to Enter Result',
    });
    var tippy_modifyEntryBtn = tippy('.modifyEntryBtnDiv', {
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
    tippyLoadingUtlInstance.enterResultBtnDiv = tippy_enterResultBtn[0];
    tippyLoadingUtlInstance.modifyEntryBtnDiv = tippy_modifyEntryBtn[0];
});

  function getCollAreaNLabListOnLoad() {
    AajaxGetCollAreaNLabList();
  }

  /*------------------------------------------Event Actions Ends------------------------------------------------------------------------------------------------*/
  /*------------------------------------------Ajax Calls Starts-------------------------------------------------------------------------------------------------*/
  var globalEntryReqnList = null;
  var globalMachnEntryReqnList = null;
  var globalAjaxObjectArr = {};

  
  function AjaxGetEntryReqnList(globalSearchParam) {
    var collAreaCode = globalSearchParam.collAreaCode;
    var labCode = globalSearchParam.labCode;
    var resultEntryStatusCode = globalSearchParam.resultEntryStatusCode;
    var dateFiltersOrBoth = globalSearchParam.dateFiltersOrBoth;
    var dateTypeCode = globalSearchParam.dateTypeCode;
    var fromDate = globalSearchParam.fromDate;
    var toDate = globalSearchParam.toDate;
    var crNo = globalSearchParam.crNo;
    var isMachineBasedEntry = "0";
    
    var tableDtlJson = {};
    tableDtlJson.tblSuffix="_Man";
    
    globalEntryReqnList = null;
    var _mode = "AjaxGetEntryReqnList";
    var url = "/HISInvestigationG5/new_investigation/invResultEntryResp.cnt?hmode=" + _mode + "&collAreaCode=" + collAreaCode + "&labCode=" +
      labCode + "&resultEntryStatusCode=" + resultEntryStatusCode + "&dateFiltersOrBoth=" + dateFiltersOrBoth + "&dateTypeCode=" + dateTypeCode + "&fromDate=" + fromDate +
      "&toDate=" + toDate + "&crNo=" + crNo + "&isMachineBasedEntry=" + isMachineBasedEntry;

    globalAjaxObjectArr.AjaxGetEntryReqnList = $.getJSON(url, function(data) {
        if (!$.isEmptyObject(data)) {
          if (data.isSuccess == "1") {
            globalEntryReqnList = data.EntryReqnList;
            if (globalSearchParam.dateFiltersOrBoth == "1" || globalSearchParam.dateFiltersOrBoth == "2") {
              tableDtlJson.tableId="#DataTable12";
              dataTablePatEntryReqnList(data.resultEntryReqnList, tableDtlJson);
            } else {
              tableDtlJson.tableId="#DataTable11";
              dataTableEntryReqnList(data.resultEntryReqnList, tableDtlJson);
            }
          } else {
            console.log("%cAjaxGetEntryReqnList Failed | ResponseError Is Below", "color:red;");
            console.log(data.error);
          }
        }
      })
      .done(function(data) {
        console.log("%cAjaxGetEntryReqnList success | ResponseData Is Below", "color:green;");
        console.log(data);
      })
      .fail(function(jqxhr, textStatus, error) {
        console.log("%cAjaxGetEntryReqnList Failed | ResponseError Is Below", "color:red;");
        var err = textStatus + ", " + error;
        console.log("Request Failed: " + err);
      });
  }

  function AjaxGetMachnEntryReqnList(globalSearchParam) {
    var collAreaCode = globalSearchParam.collAreaCode;
    var labCode = globalSearchParam.labCode;
    var resultEntryStatusCode = globalSearchParam.resultEntryStatusCode;
    var dateFiltersOrBoth = globalSearchParam.dateFiltersOrBoth;
    var dateTypeCode = globalSearchParam.dateTypeCode;
    var fromDate = globalSearchParam.fromDate;
    var toDate = globalSearchParam.toDate;
    var crNo = globalSearchParam.crNo;
    var isMachineBasedEntry = "1";
    
    var tableDtlJson = {};
    tableDtlJson.tblSuffix="_Mchn";
    
    globalMachnEntryReqnList = null;
    var _mode = "AjaxGetEntryReqnList";
    var url = "/HISInvestigationG5/new_investigation/invResultEntryResp.cnt?hmode=" + _mode + "&collAreaCode=" + collAreaCode + "&labCode=" +
      labCode + "&resultEntryStatusCode=" + resultEntryStatusCode + "&dateFiltersOrBoth=" + dateFiltersOrBoth + "&dateTypeCode=" + dateTypeCode + "&fromDate=" + fromDate +
      "&toDate=" + toDate + "&crNo=" + crNo + "&isMachineBasedEntry=" + isMachineBasedEntry;

    globalAjaxObjectArr.AjaxGetMachnEntryReqnList = $.getJSON(url, function(data) {
        if (!$.isEmptyObject(data)) {
          if (data.isSuccess == "1") {
            globalMachnEntryReqnList = data.EntryReqnList;
            if (globalSearchParam.dateFiltersOrBoth == "1" || globalSearchParam.dateFiltersOrBoth == "2") {
              tableDtlJson.tableId="#DataTable22";
              dataTablePatEntryReqnList(data.resultEntryReqnList, tableDtlJson);
            } else {
                tableDtlJson.tableId="#DataTable21";
              dataTableEntryReqnList(data.resultEntryReqnList, tableDtlJson);
            }
          } else {
            console.log("%cAjaxGetMachnEntryReqnList Failed | ResponseError Is Below", "color:red;");
            console.log(data.error);
          }
        }
      })
      .done(function(data) {
        console.log("%cAjaxGetMachnEntryReqnList success | ResponseData Is Below", "color:green;");
        console.log(data);
      })
      .fail(function(jqxhr, textStatus, error) {
        console.log("%cAjaxGetMachnEntryReqnList Failed | ResponseError Is Below", "color:red;");
        var err = textStatus + ", " + error;
        console.log("Request Failed: " + err);
      });
  }

  function openInvTrackingByCrNoIFrame(invTrackingByCrNoBtnVal) {
    var crNo = invTrackingByCrNoBtnVal.crNo;
    var requisitionNo = invTrackingByCrNoBtnVal.requisitionNo;
    var requisitionDNo = invTrackingByCrNoBtnVal.requisitionDNo;
    var testCode = invTrackingByCrNoBtnVal.testCode;
    var groupCode = invTrackingByCrNoBtnVal.groupCode;
    var _mode = "UrlExternalCall";
    var url = "/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode=" + _mode + "&crNo=" + crNo + "&searchType=1";
    $.fancybox.destroy();
    $(".fancyBoxIFrame").attr({
      "href": url
    });
    $(".fancyBoxIFrame").click();
  }

  function openInvTrackingTestCompareIFrame(invTrackingTestCompareBtnVal) {
    var crNo = invTrackingTestCompareBtnVal.crNo;
    var requisitionNo = invTrackingTestCompareBtnVal.requisitionNo;
    var requisitionDNo = invTrackingTestCompareBtnVal.requisitionDNo;
    var testCode = invTrackingTestCompareBtnVal.testCode;
    var groupCode = invTrackingTestCompareBtnVal.groupCode;
    var isGroup = "0";
    if (groupCode != "" && groupCode != null && groupCode != "null") {
      isGroup = "1"
    }
    var _mode = "UrlExternalCall";
    var url = "/HISInvestigationG5/new_investigation/InvestigationTrackingReport.cnt?hmode=" + _mode + "&crNo=" + crNo + "&searchType=1" + "&isGroup=" + isGroup +
      "&testCode=" + testCode + "&groupCode=" + groupCode + "&requisitionDNo=" + requisitionDNo + "&requisitionNo=" + requisitionNo + "&showGraph=0";
    $.fancybox.destroy();
    $(".fancyBoxIFrame").attr({
      "href": url
    });
    $(".fancyBoxIFrame").click();
  }

  function openReqnRaisingIFrame(addRemoveReqnBtnVal) {
    var crNo = addRemoveReqnBtnVal.crNo;
    var requisitionNo = addRemoveReqnBtnVal.requisitionNo;
    var requisitionDNo = addRemoveReqnBtnVal.requisitionDNo;
    var testCode = addRemoveReqnBtnVal.testCode;
    var groupCode = addRemoveReqnBtnVal.groupCode;
    var labCode = addRemoveReqnBtnVal.labCode;
    var isGroup = "0";
    if (groupCode != "" && groupCode != null && groupCode != "null") {
      isGroup = "1"
    }
    var res = crNo + "$" + requisitionNo + "$" + requisitionDNo + "$" + groupCode + "$" + testCode + "$" + labCode + "$";
    var hmode = "GETPATDTL";
    var isPatDetailPage = "1";
    var ispreview = "2";
    var url = "/HISInvestigationG5/new_investigation/invResultEntryResp.cnt?hmode=" + hmode + "&selectedCheckbox=" + res;
    $.fancybox.destroy();
    $(".fancyBoxIFrame").attr({
      "href": url
    });
    $(".fancyBoxIFrame").click();
  }

  function openResultEntryIFrame(globalSelectedReqnData) {
    var concatSelectedReqnData = globalSelectedReqnData.concatSelectedReqnData;
    var concatChkSendToMachine = globalSelectedReqnData.concatChkSendToMachine;
    var valstusCod = globalSearchParam.resultEntryStatusCode;
    var requestData = {
      hmode: "GetReqnDetailsEditable",
      isPatDetailPage: "1",
      selectedCheckbox: concatSelectedReqnData,
      chkSendToMachine: concatChkSendToMachine,
      newEntry: valstusCod
    };
    var requestDataStrlUrlEncoded = $.param(requestData);
    //alert(requestDataStrlUrlEncoded);
    var url = "/HISInvestigationG5/new_investigation/invResultEntryResp.cnt?";
    url += requestDataStrlUrlEncoded;
    $.fancybox.destroy();
    $(".fancyBoxIFrame").attr({
      "href": url
    });
    $(".fancyBoxIFrame").click();
  }

  function openModifyResultIFrame(globalSelectedReqnData) {
    var concatSelectedReqnData = globalSelectedReqnData.concatSelectedReqnData;
    var concatChkSendToMachine = globalSelectedReqnData.concatChkSendToMachine;
    var concatChkSendToMachinetest = globalSelectedReqnData.concatChkSendToMachinetest;
    var valstusCod = globalSearchParam.resultEntryStatusCode;
    var requestData = {
      hmode: "GetReqnDetailsEditable",
      isPatDetailPage: "1",
      selectedCheckbox: concatSelectedReqnData,
      chkSendToMachine: concatChkSendToMachine,
      chkSendToMachinetest: concatChkSendToMachinetest,
      newEntry: valstusCod
    };
    var requestDataStrlUrlEncoded = $.param(requestData);
    //alert(requestDataStrlUrlEncoded);
    var url = "/HISInvestigationG5/new_investigation/invResultEntryResp.cnt?";
    url += requestDataStrlUrlEncoded;
    $.fancybox.destroy();
    $(".fancyBoxIFrame").attr({
      "href": url
    });
    $(".fancyBoxIFrame").click();
  }
  $(document).ready(function() {
    $(".fancyBoxIFrame").fancybox({
      //closeExisting: false,
      type: 'iframe',
      toolbar: false,
      smallBtn: true,
      transitionEffect: "zoom-in-out",
      transitionDuration: 366,
      iframe: {
        // Iframe template
        //tpl: '<iframe id="fancybox-frame{rnd}" name="fancybox-frame{rnd}" class="fancybox-iframe" allowfullscreen allow="autoplay; fullscreen" src=""></iframe>',
        preload: true,
        css: {
          //				'width':$fancyboxWidth,
          //				'height':$fancyboxHeight,
          'width': '100%',
          'height': '100%',
        }
      },
      btnTpl: {
        close: '<button data-fancybox-close type="button"  class="fancybox-button fancybox-button--close" title="{{CLOSE}}">' +
          '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
          "</button>",
        smallBtn: '<button data-fancybox-close type="button"  class="fancybox-button fancybox-close-small " title="{{CLOSE}}">' +
          '<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
          "</button>"
      },
      afterClose: function(instance, current) {
        actionOnFancyClose();
      }
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
  
  

  /*---------------Ajax call for generating PDF Report-------------------------*/
  function AjaxGetPatPDFReport(pdfReportParam) {

  	  var _mode = "AjaxGetPatPDFReport";

  	  
  	  /*var hospitalCode = globalSearchParam.hospitalCode;
  	  var crNumber = globalSearchParam.crNo;
  	  var labNo = row.labNo;
  	  var requisitionNo = row.requisitionNo;*/
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


  
  var globalDatablesObject = {};

  function dataTableEntryReqnList(EntryReqnList, tableDtlJson) {
    var groupColumn = 3;
    var groupOrderOnColumn = 2;
    $(tableDtlJson.tableId).DataTable().clear().destroy(); //'#DataTable11'
    $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
    var table = $(tableDtlJson.tableId).DataTable({
      "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
      "processing": true,
      "scrollY": "450px",
      "scrollCollapse": true,
      "lengthMenu": [
        [10, 25, 50, 100, -1],
        [10, 25, 50, 100, "All"]
      ],
      "language": {
        "emptyTable": "No Data Is Available "
      },
      "select": {
        style: 'multi',
        /*multi+shift | single | api | os | multi*/
        selector: 'td:nth-child(4) .rowCheckBoxes',
        blurable: true,
      },
      "sPaginationType": "full_numbers",
      "bJQueryUI": true,
      buttons: [{
        extend: 'collection',
        text: '',
        className: "fas fa-cogs text-primary btn-lg bg-white btn-outline-light",
        buttons: [{
            extend: 'excel',
            title: 'Investigation Result Entry',
            text: ' Download Excel',
            className: "fas fa-file-excel text-primary bg-white btn-outline-light",
            exportOptions: {
              columns: [1, 2, 5, 6, 7, 8, 9, 10, 11, 12]
            },
          },
          {
            extend: 'pdfHtml5',
            className: "fas fa-file-pdf text-primary bg-white btn-outline-light",
            title: 'Investigation Result Entry Report',
            text: ' Download Pdf',
            pageMargins: [0, 0, 0, 0],
            margin: [0, 0, 0, 0],
            alignment: 'center',
            exportOptions: {
              columns: [1, 2, 5, 6, 7, 8, 9, 10, 11, 12]
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
      "aaData": EntryReqnList,
      "columns": [
        /*requisitionNo requisitionDate requisitionDNo testCode
    	groupCode testName groupName accessionNo
			tempSampleNo isMachineBasedTest testParameterName
			finalRemarkReqd finalRemarkString reportChangeFlag
			isRepeatCount patAge patGender machineCode*/
        {
          "className": 'control details-control',			/*0*/
          "orderable": false,
          "data": null,
          "defaultContent": ''
        }, /*details-control*/
        { "data": 'sno' },									/*1*/
        { "data": 'requisitionDate' },						/*2*/
        { "data": 'reqnDataForGrouping' },					/*3*/
        { "data": 'reqnCheckBox' },							/*4*/
        { "data": 'crNo' },									/*5*/
        { "data": 'patName' },								/*6*/
        { "data": 'patAgeGender' },							/*7*/
        { "data": 'patUnitName' },							/*8*/
        { "data": 'testNameOrGroupName' },					/*9*/
        { "data": 'patLabName' },							/*10*/
        { "data": 'tempSampleOrAccessionNo' },				/*11*/
        { "data": 'resultEnteredBy' },						/*12*/
        { "data": 'patStatus' },							/*13*/
        { "data": 'machineCode' },							/*14*/
        { "data": 'addModify' },							/*15*/
        { "data": 'reqnPriority' },							/*16*/
        { "data": 'sampleCollDate' },						/*17*/
        { "data": 'sampleAccepDate' },						/*18*/
        { "data": 'patAccepDate' },							/*19*/
        { "data": 'resultEntryDate' },						/*20*/
        { "data": null,
          'defaultContent': 'extraTd' },					/*21*/
      ],
      "order": [
        [1, 'asc']
      ],
      "columnDefs": [{
          "targets": 0,
          "orderable": false,
          className: "control details-control"
        },
        {
          "targets": groupColumn,
          "visible": false
        },
        {
          "targets": 2,
          "visible": false
        },
        {
          "targets": 4,
          "orderable": false
        },
        {
          "targets": 4,
          "render": function(data, type, row) {
            return rowCheckBox(data, type, row);
          },
        },
        {
          "targets": 5,
          "createdCell": function(td, cellData, rowData, rowIndex, collIndex) {
            $(td).attr('data-search', rowData.crNo);
            $(td).attr('data-order', rowData.crNo);
          },
          "render": function(data, type, row) {
            return setInvTrackingByCrNoBtnData(data, type, row);
          },
        },
        {
          "targets": 8,
          "render": function(data, type, row) {
            return customDepartment(data, type, row);
          },
        },
        {
          "targets": 9,
          "createdCell": function(td, cellData, rowData, rowIndex, collIndex) {
            $(td).attr('data-search', rowData.testNameOrGroupName);
            $(td).attr('data-order', rowData.testNameOrGroupName);
          },
          "render": function(data, type, row) {
            return setInvTrackingTestCompareBtnData(data, type, row);
          },
        },
        {
          "targets": 15,
          "render": function(data, type, row) {
            return setAddRemoveReqnBtnVal(data, type, row);
          },
        },
        {
          "targets": 16,
          "visible": false
        },
        {
          "targets": 17,
          "visible": false
        },
        {
          "targets": 18,
          "visible": false
        },
        {
          "targets": 19,
          "visible": false
        },
        {
          "targets": 20,
          "visible": false
        },
      ],
      /*Group rows On every table draw event*/
      "drawCallback": function(settings) {
        var api = this.api();
        var rows = api.rows({
          page: 'current'
        }).nodes();
        var last = null;
        /*groupColumn is the Json response column index on which grouping needs to be done*/
        api.column(groupColumn, {
          page: 'current'
        }).data().each(function(reqnDataForGroupn, i) {
          if (last !== reqnDataForGroupn.requisitionNo) {
            var priorityElmnt = "";
            if (reqnDataForGroupn.priorityCode == "2") {
              priorityElmnt = '<span class="urgentGradient">Urgent</span>'
            }
            
            var keyStr = JSON.stringify(reqnDataForGroupn.reqnAdvisedNotes);
            var strPDFGenKey = JSON.stringify(reqnDataForGroupn.reqnDataForPDFReport);
            
            var generatePdfReportElm='';
            var waitGeneratePdfReportElm='';
            if(globalSearchParam.resultEntryStatusCode!="1"){
            	generatePdfReportElm='<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  generateReportPdfBtn " key=' + strPDFGenKey + '>' +
						                '<i class="far fa-file-pdf" style="color:#007BFF;"></i>' +
						                '</button>';
				waitGeneratePdfReportElm = '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1 text-nowrap d-none waitGenerateReportPdfBtn " disabled>' +
											'<span class="spinner-border text-danger spinner-border-sm mb-2 "></span>' + '<i class="text-warning" style:"font-size: 0.7rem;">Loading..</i>' +
											'</button>';
            }
            
            var rowGroupElmnt = '<tr class="group"><td colspan="12" class="rowGroupReqnDate"><span>' + reqnDataForGroupn.filterDateTypeName + ' : </span>' + reqnDataForGroupn.filterDateTypeDate +
              '<button type="button" class="btn btn-light btn-circle btn-circle-reqnGroupRowSort ml-1 rowGroupReqnDateSort ">' +
              '<i class="fas fa-sort-amount-up reqnDtGrupSortUp" ></i>' +
              '<i class="fas fa-sort-amount-down-alt reqnDtGrupSortDown d-none" ></i>' +
              '</button>' +
              '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  showAdvisedNotes " key=' + keyStr + '>' +
              '<i class="far fa-comment-alt" style="color:#FD7E14;"></i>' +
              '</button>' +
              generatePdfReportElm +
              waitGeneratePdfReportElm +
              priorityElmnt +
              '</td></tr>';
            $(rows).eq(i).before(rowGroupElmnt);
            last = reqnDataForGroupn.requisitionNo;
          }
          //not need of writing this cose in loope but no option for now
          if (reqnDataForGroupn.filterDateTypeCode == "1") {
            groupOrderOnColumn = 2
          } else if (reqnDataForGroupn.filterDateTypeCode == "2") {
            groupOrderOnColumn = 17
          } else if (reqnDataForGroupn.filterDateTypeCode == "3") {
            groupOrderOnColumn = 19
          } else if (reqnDataForGroupn.filterDateTypeCode == "4") {
            groupOrderOnColumn = 20
          }
        });
        //set events active on every table draw event
        setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
      },
      "rowCallback": function(row, data, displayNum, displayIndex, dataIndex) {
        var index = displayIndex + 1;
        if (updateRowFlag) {
          var oSettings = this.fnSettings();
          // $('td:first', row).html(oSettings._iDisplayStart+displayIndex +1);
          // $('td:eq(0)',row).html(index);
        }
        return row;
      },
      responsive: {
        details: {
          renderer: function(api, rowIdx, columns) {
            var data = $.map(columns, function(col, i) {
              return col.hidden ? entryCustomDataOnScreenSize(col) : '';
            }).join('');
            return data ? entryCustomRowAppend(api, rowIdx, columns, data) : false;
          }
        }
      },
      "initComplete": function(settings, json) {
    	  var departNameFilterCls= ".departNameFilter"+tableDtlJson.tblSuffix;
		  var groupTestFilterCls= ".groupTestFilter"+tableDtlJson.tblSuffix;
		  var labNameFilterCls= ".labNameFilter"+tableDtlJson.tblSuffix;
		  var patStatusFilterCls= ".patStatusFilter"+tableDtlJson.tblSuffix;
		  var machineNameFilterCls= ".machineNameFilter"+tableDtlJson.tblSuffix;
		  var priorityFilterCls= ".priorityFilter"+tableDtlJson.tblSuffix;
		  
        $(departNameFilterCls).removeClass("d-none");
        $(groupTestFilterCls).removeClass("d-none");
        $(labNameFilterCls).removeClass("d-none");
        $(patStatusFilterCls).addClass("d-none");
        $(machineNameFilterCls).removeClass("d-none");
        $(priorityFilterCls).removeClass("d-none");
        
        this.api().columns([8]).every(function() {
          var column = this;
          var departFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected >Department Filter</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(departNameFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Department Filter</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            departFltrCount++;
          });
          if (departFltrCount <= 1) {
            $(departNameFilterCls).addClass("d-none");
          }
        });
        this.api().columns([9]).every(function() {
          var column = this;
          var groupTestFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected >Test|Group Filter</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(groupTestFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Test|Group Filter</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            groupTestFltrCount++;
          });
          if (groupTestFltrCount <= 1) {
            $(groupTestFilterCls).addClass("d-none");
          }
        });
        this.api().columns([10]).every(function() {
          var column = this;
          var labNameFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected >Lab Filter</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(labNameFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Lab Filter</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            labNameFltrCount++;
          });
          if (labNameFltrCount <= 1) {
            $(labNameFilterCls).addClass("d-none");
          }
        });
        this.api().columns([13]).every(function() {
          var column = this;
          var patStatusFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected >Patient Status</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(patStatusFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Patient Status</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            patStatusFltrCount++;
          });
          if (patStatusFltrCount <= 1) {
            $(patStatusFilterCls).addClass("d-none");
          }
        });
        this.api().columns([14]).every(function() {
          var column = this;
          var machineNameFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected disabled>Machine</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(machineNameFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Machine</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            machineNameFltrCount++;
          });
          if (machineNameFltrCount <= 1) {
            $(machineNameFilterCls).addClass("d-none");
          }
        });
        this.api().columns([16]).every(function() {
          var column = this;
          var priorityFltrCount = 0;
          var select = $(priorityFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Priority</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            priorityFltrCount++;
          });
          if (priorityFltrCount <= 1) {
            $(priorityFilterCls).addClass("d-none");
          }
        });
        showLoading(false);
        $('#container2ExpandBtn').click();
        //$("#container1Row1").collapse('hide');
      }
    });
    table.on('responsive-display', function(e, datatable, row, showHide, update) {
      setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
      if (showHide) {
        var testParamTableID = "#testParamTable" + row.index();
        //console.log(testParamTableID);
        $(testParamTableID).DataTable().columns.adjust().responsive.recalc();
      }
    });
    
    //Set Tab Notification Badge
    var notifBadgeCls=".testBadgeCount"+tableDtlJson.tblSuffix;
    $(notifBadgeCls).text(table.rows().count());
    
    //Add event listener for opening and closing details
    /* $('DataTable11 tbody').on('click', 'td.details-control', function () {
         var tr = $(this).closest('tr');
         var row = table.row( tr );
         if ( row.child.isShown() ) {
             // This row is already open - close it
             row.child.hide();
             tr.removeClass('shown');
         }
         else {
             // Open this row
             row.child(format(row.data())).show();
         	//  row.child().show();
             tr.addClass('shown');
         }
     } );*/
    setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
    // Handle click on "Epand All" button
    $('table .expandButton').on('click', function() {
      expandColapseRow(true, table);
    });
    // Handle click on "Collapse All" button
    $('table .collapseButton').on('click', function() {
      expandColapseRow(false, table);
    });
    //Select all check box
    $("#selectAllDtCheck1").off().on("change", function(e) {
      if ($(this).is(":checked")) {
        sellectAllDtCheck(true, table);
      } else {
        sellectAllDtCheck(false, table);
      }
      if ($(".rowCheckBoxes:checked").length) {
        enableEntryModifyBtn(true);
      } else {
        enableEntryModifyBtn(false);
      }
    });
    globalDatablesObject.dataTableEntryReqnList = table;
  }
  var updateRowFlag = false;

  function dataTablePatEntryReqnList(patEntryReqnList, tableDtlJson) {
    var groupColumn = 3;
    var groupOrderOnColumn = 2;
    $(tableDtlJson.tableId).DataTable().clear().destroy(); //'#DataTable12'
    $.fn.dataTable.moment('DD-MMM-YYYY HH:mm');
    var table = $(tableDtlJson.tableId).DataTable({
      "dom": '<"row"<"col-6 col-sm-5 col-lg-2 "l><"col-6 col-sm-2 col-lg-1"B><"col-6 col-sm-5 col-lg-3 "f><"col-6 col-sm-12 col-lg-6"p>>rt<"row"<"col-12"i>><"clear">',
      "processing": true,
      "scrollY": "350px",
      "scrollCollapse": true,
      "lengthMenu": [
        [10, 25, 50, 100, -1],
        [10, 25, 50, 100, "All"]
      ],
      "language": {
        "emptyTable": "No Data Is Available "
      },
      "select": {
        style: 'multi',
        /*multi+shift | single | api | os | multi*/
        selector: 'td:nth-child(4) .rowCheckBoxes',
        blurable: true,
      },
      "sPaginationType": "full_numbers",
      "bJQueryUI": true,
      buttons: [{
        extend: 'collection',
        text: '',
        className: "fas fa-cogs text-primary btn-lg bg-white btn-outline-light",
        buttons: [{
            extend: 'excel',
            title: 'Investigation Result Entry',
            text: ' Download Excel',
            className: "fas fa-file-excel text-primary bg-white btn-outline-light",
            exportOptions: {
              columns: [1, 2, 5, 6, 7, 8]
            },
          },
          {
            extend: 'pdfHtml5',
            className: "fas fa-file-pdf text-primary bg-white btn-outline-light",
            title: 'Investigation Result Entry Report',
            text: ' Download Pdf',
            pageMargins: [0, 0, 0, 0],
            margin: [0, 0, 0, 0],
            alignment: 'center',
            exportOptions: {
              columns: [1, 2, 5, 6, 7, 8]
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
      "aaData": patEntryReqnList,
      "columns": [
        /*requisitionNo requisitionDate requisitionDNo testCode
        	groupCode testName groupName accessionNo
        	tempSampleNo isMachineBasedTest testParameterName
        	finalRemarkReqd finalRemarkString reportChangeFlag
        	isRepeatCount patAge patGender machineCode
         */
        {
          "className": 'control details-control',			/*0*/
          "orderable": false,
          "data": null,
          "defaultContent": ''
        }, /*details-control*/
        { "data": 'sno' },									/*1*/
        { "data": 'requisitionDate' },						/*2*/
        { "data": 'reqnDataForGrouping' },					/*3*/
        { "data": 'reqnCheckBox' },							/*4*/
        { "data": 'patUnitName' },							/*5*/
        { "data": 'testNameOrGroupName' },					/*6*/
        { "data": 'patLabName' },							/*7*/
        { "data": 'tempSampleOrAccessionNo' },				/*8*/
        { "data": 'resultEnteredBy' },						/*9*/
        { "data": 'machineCode' },							/*10*/
        { "data": 'addModify' },							/*11*/
        { "data": 'reqnPriority' },							/*12*/
        { "data": 'sampleCollDate' },						/*13*/
        { "data": 'sampleAccepDate' },						/*14*/
        { "data": 'patAccepDate' },							/*15*/
        { "data": 'resultEntryDate' },						/*16*/
        { "data": null,										/*17*/
          'defaultContent': 'extraTd'
        }
      ],
      "order": [
        [1, 'asc']
      ],
      "columnDefs": [{
          "targets": 0,
          "orderable": false,
          className: "control details-control"
        },
        {
          "targets": groupColumn,
          "visible": false
        },
        {
          "targets": 2,
          "visible": false
        },
        {
          "targets": 4,
          "orderable": false
        },
        {
          "targets": 4,
          "render": function(data, type, row) {
            return rowCheckBox(data, type, row);
          },
        },
        {
          "targets": 5,
          "render": function(data, type, row) {
            return customDepartment(data, type, row);
          },
        },
        {
          "targets": 6,
          "createdCell": function(td, cellData, rowData, rowIndex, collIndex) {
            $(td).attr('data-search', rowData.testNameOrGroupName);
            $(td).attr('data-order', rowData.testNameOrGroupName);
          },
          "render": function(data, type, row) {
            return setInvTrackingTestCompareBtnData(data, type, row);
          },
        },
        {
          "targets": 11,
          "render": function(data, type, row) {
            return setAddRemoveReqnBtnVal(data, type, row);
          },
        },
        {
          "targets": 12,
          "visible": false
        },
        {
          "targets": 13,
          "visible": false
        },
        {
          "targets": 14,
          "visible": false
        },
        {
          "targets": 15,
          "visible": false
        },
        {
          "targets": 16,
          "visible": false
        },
      ],
      "drawCallback": function(settings) {
        var api = this.api();
        var rows = api.rows({
          page: 'current'
        }).nodes();
        var last = null;
        //Group rows On every table draw event
        api.column(groupColumn, {
          page: 'current'
        }).data().each(function(reqnDataForGroupn, i) {
          if (last !== reqnDataForGroupn.requisitionNo) {
            var priorityElmnt = "";
            if (reqnDataForGroupn.priorityCode == "2") {
              priorityElmnt = '<span class="urgentGradient ml-1">Urgent</span>';
            }
            
            var keyStr = JSON.stringify(reqnDataForGroupn.reqnAdvisedNotes);
            var strPDFGenKey = JSON.stringify(reqnDataForGroupn.reqnDataForPDFReport);
            
            var generatePdfReportElm='';
            var waitGeneratePdfReportElm='';
            if(globalSearchParam.resultEntryStatusCode!="1"){
            	generatePdfReportElm='<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  generateReportPdfBtn " key=' + strPDFGenKey + '>' +
						                '<i class="far fa-file-pdf" style="color:#007BFF;"></i>' +
						                '</button>';
				waitGeneratePdfReportElm = '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1 text-nowrap d-none waitGenerateReportPdfBtn " disabled>' +
											'<span class="spinner-border text-danger spinner-border-sm mb-2 "></span>' + '<i class="text-warning" style:"font-size: 0.7rem;">Loading..</i>' +
											'</button>';
            }
            
            var rowGroupElmnt = '<tr class="group"><td colspan="12" class="rowGroupReqnDate"><span>' + reqnDataForGroupn.filterDateTypeName + ' : </span>' + reqnDataForGroupn.filterDateTypeDate +
              '<button type="button" class="btn btn-light btn-circle btn-circle-reqnGroupRowSort ml-1 rowGroupReqnDateSort ">' +
              '<i class="fas fa-sort-amount-up reqnDtGrupSortUp" ></i>' +
              '<i class="fas fa-sort-amount-down-alt reqnDtGrupSortDown d-none" ></i>' +
              '</button>' +
              '<button type="button" class="btn btn-light btn-circle btn-circle-comment ml-auto ml-md-1  showAdvisedNotes " key=' + keyStr + '>' +
              '<i class="far fa-comment-alt" style="color:#FD7E14;"></i>' +
              '</button>' +
              generatePdfReportElm +
              waitGeneratePdfReportElm + 
              priorityElmnt +
              '</td></tr>';
            $(rows).eq(i).before(rowGroupElmnt);
            last = reqnDataForGroupn.requisitionNo;
          }
          //not need of writing this cose in loope but no option for now
          if (reqnDataForGroupn.filterDateTypeCode == "1") {
            groupOrderOnColumn = 2
          } else if (reqnDataForGroupn.filterDateTypeCode == "2") {
            groupOrderOnColumn = 13
          } else if (reqnDataForGroupn.filterDateTypeCode == "3") {
            groupOrderOnColumn = 15
          } else if (reqnDataForGroupn.filterDateTypeCode == "4") {
            groupOrderOnColumn = 16
          }
        });
        //set events active on every table draw event
        setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
      },
      responsive: {
        details: {
          renderer: function(api, rowIdx, columns) {
            var data = $.map(columns, function(col, i) {
              return col.hidden ? entryCustomDataOnScreenSize(col) : '';
            }).join('');
            return data ? entryCustomRowAppend(api, rowIdx, columns, data) : entryCustomRowAppend(api, rowIdx, columns, data);
          }
        }
      },
      "initComplete": function(settings, json) {
		  var departNameFilterCls= ".departNameFilter"+tableDtlJson.tblSuffix;
		  var groupTestFilterCls= ".groupTestFilter"+tableDtlJson.tblSuffix;
		  var labNameFilterCls= ".labNameFilter"+tableDtlJson.tblSuffix;
		  var patStatusFilterCls= ".patStatusFilter"+tableDtlJson.tblSuffix;
		  var machineNameFilterCls= ".machineNameFilter"+tableDtlJson.tblSuffix;
		  var priorityFilterCls= ".priorityFilter"+tableDtlJson.tblSuffix;
		  
        $(departNameFilterCls).removeClass("d-none");
        $(groupTestFilterCls).removeClass("d-none");
        $(labNameFilterCls).removeClass("d-none");
        $(patStatusFilterCls).addClass("d-none");
        $(machineNameFilterCls).removeClass("d-none");
        $(priorityFilterCls).removeClass("d-none");
        
        this.api().columns([5]).every(function() {
          var column = this;
          var departNameFltrCount = 0;
          //var select = $('<select class="form-control custom-select"><option class="text-light bg-dark" value="" selected>Department Filter</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(departNameFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Department Filter</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            departNameFltrCount++;
          });
          if (departNameFltrCount <= 1) {
            $(departNameFilterCls).addClass("d-none");
          }
        });
        this.api().columns([6]).every(function() {
          var column = this;
          var groupTestFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected>Test|Group Filter</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(groupTestFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Test|Group Filter</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            groupTestFltrCount++;
          });
          if (groupTestFltrCount <= 1) {
            $(groupTestFilterCls).addClass("d-none");
          }
        });
        this.api().columns([7]).every(function() {
          var column = this;
          var labNameFltrCount = 0;
          //var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected>Lab Filter</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(labNameFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Lab Filter</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            labNameFltrCount++;
          });
          if (labNameFltrCount <= 1) {
            $(labNameFilterCls).addClass("d-none");
          }
        });
        this.api().columns([10]).every(function() {
          var column = this;
          var machineNameFltrCount = 0;
          // var select = $('<select class="form-control custom-select" ><option class="text-light bg-dark" value="" selected>Machine</option></select>').appendTo( $(column.footer()).empty() )
          var select = $(machineNameFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Machine</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            machineNameFltrCount++;
          });
          if (machineNameFltrCount <= 1) {
            $(machineNameFilterCls).addClass("d-none");
          }
        });
        this.api().columns([12]).every(function() {
          var column = this;
          var priorityFltrCount = 0;
          var select = $(priorityFilterCls).empty().off().on('change', function() {
            //var val = $.fn.dataTable.util.escapeRegex( $(this).val() );
            //column.search( val ? '^'+val+'$' : '', true, false ).draw();
            var val = $(this).val();
            column.search(val).draw();
          });
          select.append('<option class="text-light bg-dark" value="" selected>Priority</option>');
          column.data().unique().sort().each(function(d, j) {
            select.append('<option value="' + d + '">' + d + '</option>')
            priorityFltrCount++;
          });
          if (priorityFltrCount <= 1) {
            $(priorityFilterCls).addClass("d-none");
          }
        });
        showLoading(false);
        $('#container2ExpandBtn').click();
        //$("#container1Row1").collapse('hide');
      }
    });
    table.on('responsive-display', function(e, datatable, row, showHide, update) {
      setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
      //console.log( 'Details for row '+row.index()+' '+(showHide ? 'shown' : 'hidden') );
      if (showHide) {
        var testParamTableID = "#testParamTable" + row.index();
        //console.log(testParamTableID);
        $(testParamTableID).DataTable().columns.adjust().responsive.recalc();
      }
      table.processing(false);
    });
    setEventListenersAfterReqnListTableDrawn(table, groupOrderOnColumn);
    
    //Set Tab Notification Badge
    var notifBadgeCls=".testBadgeCount"+tableDtlJson.tblSuffix;
    $(notifBadgeCls).text(table.rows().count());
    
    // Handle click on "Epand All" button
    $('table .expandButton').on('click', function() {
      expandColapseRow(true, table);
    });
    // Handle click on "Collapse All" button
    $('table .collapseButton').on('click', function() {
      expandColapseRow(false, table);
    });
    
    $("#selectAllDtCheck2").off().on("change", function(e) {
      if ($(this).is(":checked")) {
        sellectAllDtCheck(true, table);
      } else {
        sellectAllDtCheck(false, table);
      }
      if ($(".rowCheckBoxes:checked").length) {
        enableEntryModifyBtn(true);
      } else {
        enableEntryModifyBtn(false);
      }
    });
    globalDatablesObject.dataTableEntryReqnList = table;
  }
  /*------------------------------------------DataTables Initialization Ends------------------------------------------------------------------------------------*/
  /*------------------------------------------DataTables Custom Rows Creation Starts----------------------------------------------------------------------------*/
  /*var rowSerialNoCount=0;
  function rowSerialNo(data, type, row){
  	return ++rowSerialNoCount;
  }
  */
  var rowChkCount = 0;

  function rowCheckBox(data, type, row) {
    //console.log(row.reqnTestParamJson);
    rowChkCount++;
    var rowChkVal = {
      "crNo": row.crNo,
      "requisitionNo": row.requisitionNo,
      "requisitionDNo": row.requisitionDNo,
      "testCode": row.testCode,
      "groupCode": row.groupCode
    };
    var rowChkData = '<div class="custom-control custom-checkbox ">';
    rowChkData += '<input type="checkbox" class="custom-control-input rowCheckBoxes d-none" value=' + JSON.stringify(rowChkVal) + ' id="rowCheck' + rowChkCount + '">';
    rowChkData += '<label class="custom-control-label" for="rowCheck' + rowChkCount + '"></label>';
    rowChkData += '</div>';
    return rowChkData;
  }

  function setAddRemoveReqnBtnVal(data, type, row) {
    var addRemoveReqnBtnVal = {
      "crNo": row.crNo,
      "requisitionNo": row.requisitionNo,
      "requisitionDNo": row.requisitionDNo,
      "testCode": row.testCode,
      "groupCode": row.groupCode,
      "labCode": row.labCode
    };
    var addRemoveReqnBtnData = '<a href="#" class=" text-decoration-none addRemoveReqnBtn" key=' + JSON.stringify(addRemoveReqnBtnVal) + '>';
    addRemoveReqnBtnData += '<span class=""><img height="25" width="25" src="media/images/report2.svg"></span></a>';
    return addRemoveReqnBtnData;
  }

  function setInvTrackingByCrNoBtnData(data, type, row) {
    var invTrackingByCrNoBtnVal = {
      "crNo": row.crNo,
      "requisitionNo": row.requisitionNo
    };
    var invTrackingByCrNoBtnData = "";
    invTrackingByCrNoBtnData += '<a class=" invTrackingByCrNoBtn btn btn-outline-info d-flex" key=' + JSON.stringify(invTrackingByCrNoBtnVal) + '>';
    invTrackingByCrNoBtnData += '<span class="text-wrap">' + data + '&nbsp;</span>';
    invTrackingByCrNoBtnData += '<span class="btn btn-light btn-circle btn-circle-comparebtn ml-auto"><img height="15" width="15" src="media/images/reportGradiant.svg"></span>&nbsp;</a>';
    return invTrackingByCrNoBtnData;
  }

  function setInvTrackingTestCompareBtnData(data, type, row) {
    var invTrackingTestCompareBtnVal = {
      "crNo": row.crNo,
      "requisitionNo": row.requisitionNo,
      "requisitionDNo": row.requisitionDNo,
      "testCode": row.testCode,
      "groupCode": row.groupCode
    };
    var invTrackingTestCompareBtnData = "";
    if (row.isRepeatCount == "0" || row.isRepeatCount == "1") {
      //invTrackingCompareBtnData +='<span class="text-wrap">'+data+'</span>';
      invTrackingTestCompareBtnData += '<span class="">' + data + '</span>';
    } else {
      invTrackingTestCompareBtnData += '<a class=" invTrackingTestCompareBtn btn btn-outline-info d-flex" key=' + JSON.stringify(invTrackingTestCompareBtnVal) + '>';
      invTrackingTestCompareBtnData += '<span class="text-wrap">' + data + '&nbsp;</span>';
      invTrackingTestCompareBtnData += '<span class="btn btn-light btn-circle btn-circle-comparebtn ml-auto"><img height="15" width="15" src="media/images/compare.svg"></span>&nbsp;</a>';
    }
    return invTrackingTestCompareBtnData;
  }

  function customDepartment(data, type, row) {
    var departName = '<span class="text-wrap">' + data + '</span>';
    return departName;
  }
  /*--Functions For Returning custom Cell with css color class Ends-----------------*/
  /*--Functions For Returning custom Table Rows According To Screen Size Type1 patientDetails Starts---*/
  var isEntryFirstRow = 0;
  var entryCellCount = 0;

  function entryCustomDataOnScreenSize(col) {
    var tbdat = '';
    if (col.data != "extraTd") {
      var w = window.innerWidth;
      var h = window.innerHeight;
      if (w >= 1100) {
        tbdat = entryCustomData(col, 5);
      } else if (w < 1100 && w > 880) {
        tbdat = entryCustomData(col, 3);
      } else if (w < 880 && w > 600) {
        tbdat = entryCustomData(col, 2);
      } else if (w <= 600) {
        tbdat = entryCustomData(col, 1);
      }
    }
    return tbdat;
  }

  function entryCustomData(col, noOfThPairInOneRow) {
    var tbdat = '';
    if (entryCellCount >= noOfThPairInOneRow) {
      tbdat = '</tr>';
      entryCellCount = 0;
      isEntryFirstRow = 0;
    }
    if (isEntryFirstRow == 0) {
      tbdat = '<tr data-dt-row="' + col.rowIndex + '" data-dt-column="' + col.columnIndex + '">';
      isEntryFirstRow++;
    }
    tbdat += '<th>' + col.title + ':' + '</th> ' +
      '<td>' + col.data + '</td>';
    entryCellCount++;
    return tbdat;
  }
  /*--Functions For Returning custom Table Rows According To Screen Size Type1 patientDetails Ends-----*/
  function entryCustomRowAppend(api, rowIdx, columns, data) {
    isEntryFirstRow = 0;
    entryCellCount = 0;
    //data+='</tr>';
    var entryChildRow = $('<table></table>').append(data);
    var entryAddiDt = entryAdditionalRow(api, rowIdx, columns);
    if (entryAddiDt != "" || entryAddiDt != null) {
      entryChildRow = $(entryChildRow).add($(entryAddiDt));
    }
    if (data == "") {
      return entryAddiDt;
    }
    return entryChildRow;
  }

  function entryAdditionalRow(api, rowIdx, columns) {
    //addCode to return additional Row
    return "";
  }
  $(document).ready(function() {
    $('#enterResultBtn').off().on("click", function() {
      enterReqnResult();
    });
    $('#modifyEntryBtn').off().on("click", function() {
      modifyReqnResult();
    });
  });

  function enterReqnResult() {
    if ($(".rowCheckBoxes:checked").length) {
      setGlobalSelectedReqnData();
      openResultEntryIFrame(globalSelectedReqnData);
    }
  }

  function modifyReqnResult() {
    if ($(".rowCheckBoxes:checked").length) {
      setGlobalSelectedReqnData();
      openModifyResultIFrame(globalSelectedReqnData);
    }
  }
  var globalSelectedReqnData = {
    selectedReqnDataJson: [],
    selectedReqnElement: [],
    concatSelectedReqnData: "",
    chkSendToMachineJson: [],
    concatChkSendToMachine: "",
    chkSendToMachineTestJson: [],
    concatChkSendToMachinetest: ""
  };

  function setGlobalSelectedReqnData() {
    globalSelectedReqnData.concatSelectedReqnData = "";
    globalSelectedReqnData.selectedReqnDataJson.length = 0;
    globalSelectedReqnData.concatChkSendToMachine = "";
    globalSelectedReqnData.chkSendToMachineJson.length = 0;
    globalSelectedReqnData.chkSendToMachineTestJson.length = 0;
    globalSelectedReqnData.concatChkSendToMachinetest = "";
    /*--------------------------------------------------*/
    var concatSelectedReqnData = "";
    var rcbs = $(".rowCheckBoxes:checked");
    for (var i = 0; i < rcbs.length; i++) {
      var rowChkBoxKeyJson = jQuery.parseJSON($(rcbs[i]).val());
      globalSelectedReqnData.selectedReqnDataJson.push(rowChkBoxKeyJson);
      globalSelectedReqnData.selectedReqnElement.push((rcbs[i]));
      concatSelectedReqnData += rowChkBoxKeyJson.crNo + "#" + rowChkBoxKeyJson.requisitionNo + "#" + rowChkBoxKeyJson.requisitionDNo + "#" + rowChkBoxKeyJson.groupCode;
      concatSelectedReqnData += '@';
    }
    globalSelectedReqnData.concatSelectedReqnData = concatSelectedReqnData;
    /*--------------------------------------------------*/
    var concatChkSendToMachine = "";
    var mcbs = $(".machineCheckBoxes:checked");
    for (var i = 0; i < mcbs.length; i++) {
      var mchnChkBoxKeyJson = jQuery.parseJSON($(mcbs[i]).val());
      globalSelectedReqnData.chkSendToMachineJson.push(mchnChkBoxKeyJson);
      concatChkSendToMachine += mchnChkBoxKeyJson.crNo + "#" + mchnChkBoxKeyJson.requisitionNo + "#" + mchnChkBoxKeyJson.requisitionDNo + "#" + mchnChkBoxKeyJson.groupCode;
      concatChkSendToMachine += '@';
    }
    globalSelectedReqnData.concatChkSendToMachine = concatChkSendToMachine;
    /*--------------------------------------------------*/
    var concatChkSendToMachinetest = "";
    var tmcbs = $(".mchnTestParamChkBoxes:checked");
    for (var i = 0; i < tmcbs.length; i++) {
      var mchnTestChkBoxKeyJson = jQuery.parseJSON($(tmcbs[i]).val());
      globalSelectedReqnData.chkSendToMachineTestJson.push(mchnTestChkBoxKeyJson);
      concatChkSendToMachinetest += mchnTestChkBoxKeyJson.paramTestCrNo + "#" + mchnTestChkBoxKeyJson.paramRequisitionNo + "#" + mchnTestChkBoxKeyJson.paramRequisitionDNo + "#" + mchnTestChkBoxKeyJson.paramTestGroupCode + "#" + mchnTestChkBoxKeyJson.paramTestCode;
      concatChkSendToMachinetest += '@';
    }
    globalSelectedReqnData.concatChkSendToMachinetest = concatChkSendToMachinetest;
  }

  function removeDatatableRowsOnSave() {
    var savedReqnElmList = globalSelectedReqnData.selectedReqnElement;
    var selectedReqnDataTable = null;
    if (savedReqnElmList.length) {
      selectedReqnDataTable = $(savedReqnElmList[0].closest(".dataTable")).DataTable();
    }
    for (var i = 0; i < savedReqnElmList.length; i++) {
      $(savedReqnElmList[i]).closest("tr").addClass("slideRemove-anime");
      //globalDatablesObject.dataTableEntryReqnList.row( $(".slideRemove-anime") ).remove();
      //globalDatablesObject.dataTableEntryReqnList.column(0).nodes()
      selectedReqnDataTable.row($(".slideRemove-anime")).remove();

      console.log("%c selectedReqnDataTable.column(1).nodes()== undefined :  "+selectedReqnDataTable.column(1).nodes()== undefined, "color:red;");      
		if(selectedReqnDataTable.column(1).nodes()!== undefined)
	     selectedReqnDataTable.column(1).nodes().each(function(cell, k) { cell.innerHTML = k + 1; });
      
      setTimeout(function() {
        $(".slideRemove-anime").remove();
        //globalDatablesObject.dataTableEntryReqnList.draw();
        selectedReqnDataTable.draw();
      }, 1500);
    }
    tableId = "." + $(savedReqnElmList[0].closest(".dataTable")).attr("id") + "Caption";
    $(tableId).html('<span class="py-2 px-4 rounded resultEnteredGradient">Test Result ResultEntered</span>');
    globalSelectedReqnData.selectedReqnElement.length = 0;
  }
  //addEventListener support for IE8
  function bindEvent(eventName, eventHandler) {
    if (window.document.addEventListener) {
      window.document.addEventListener(eventName, eventHandler, false);
    } else if (window.document.attachEvent) {
      window.document.attachEvent('on' + eventName, eventHandler);
    }
  }
  //Listen to message from child window
  bindEvent('iframeCustomEventForParent', function(e) {
    $("[name=jsonResponse]").val(JSON.stringify(e.detail));
  });

  function actionOnFancyClose() {
    var jsonResponse = jQuery.parseJSON($("[name=jsonResponse]").val());
    $("[name=jsonResponse]").val('{"responseFromProcess":" ","isSuccess":"0","errorMsg":" "}');
    
    if (jsonResponse != null && jsonResponse.responseFromProcess == "saveResultEntry") {
      if (jsonResponse.isSuccess == "1") {
        removeDatatableRowsOnSave();
        showToastifyMsg("Test Result Entered", true, 4000);
      } else if (jsonResponse.isSuccess == "0") {
        showToastifyMsg("Test Result Entry Failed", false, 4000);
      }
   } else if (jsonResponse != null && jsonResponse.responseFromProcess == "modifyResultEntry") {
      if (jsonResponse.isSuccess == "1") {
          removeDatatableRowsOnSave();
          showToastifyMsg("Test Result Modified", true, 4000);
        } else if (jsonResponse.isSuccess == "0") {
          showToastifyMsg("Test Result Modification Failed", false, 4000);
        }
      }
  }

  function showToastifyMsg(msgText, successFlag, timeOut) {
    if (successFlag) {
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
        onClick: function() {} // Callback after click
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
        onClick: function() {} // Callback after click
      }).showToast();
    }
  }
  /*--Functions For Returning custom Table Rows According To Screen Size Type1,2 Ends-----------------*/
  /*--------------------------------------------DataTables Custom Rows Creation Ends----------------------------------------------------------------------------*/
