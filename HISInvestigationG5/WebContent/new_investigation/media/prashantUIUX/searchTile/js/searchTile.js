/*------------------------------------------Set Automatically-----------------------------------------------*/

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


/*------On Button Click Show From to Months for archival search Starts---------*/
$(document).ready(function() {
  $('#btnDataFromArchival1').click(function() {
    $(".dateTypeSelectDiv").removeClass("d-none");
    $('.inputDates').removeClass("d-none");
  });
  $('#btnDataFromArchival2').click(function() {
    $(".dateTypeSelectDiv").addClass("d-none");
    $('.inputDates').addClass("d-none");
  });
});


$(document).ready(function() {
    var tippy_labSelectInputGroup = tippy('.labSelectInputGroup', {
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
    
    var tippy_dateFiltersOrBothRange = tippy('.custom-range-container', {
      delay: 100,
      arrow: true,
      arrowType: 'round',
      size: 'small',
      duration: 300,
      animation: 'shift-away-extreme',
      placement: 'top',
      allowHTML: true,
      content: 'Search Criteria <span style="color: aqua;"> Date| Both| CR.NO.</span>',
    });
    var tippy_inputFilterType = tippy('.inputFiltersDiv', {
      delay: 100,
      arrow: true,
      arrowType: 'round',
      size: 'small',
      duration: 300,
      animation: 'shift-away-extreme',
      placement: 'top',
      allowHTML: true,
      content: '<span>Drop slider on the left to enable<span style="color: aqua;"> this Filter </span></span>',
    });
    
    if(tippy_labSelectInputGroup.length>0)
    tippy_labSelectInputGroup[0].disable();
    tippyLoadingUtlInstance.labSelectInputGroup = tippy_labSelectInputGroup[0];
    tippyLoadingUtlInstance.inputFilterType = tippy_inputFilterType[0];
    //tippyLoadingUtlInstance.tippy_labSelectInputGroup.enable();
    //tippyLoadingUtlInstance.tippy_getDataBtnDiv.enable();
    //tippyLoadingUtlInstance.tippy_labSelectInputGroup.show();
  });


$(document).ready(function() {
  $(".dateFiltersOrBothRange").off().on("click", function() {
    if ($(this).val() == "0") {
    	
    	$("#inputFilterType").attr({"disabled": true});
    	enableInputFilters(false);
    	setInputFilterTypeHelp("0");
    	$(".dateTypeSelectDiv").removeClass("d-none");
    	$(".inputDates").removeClass("d-none");
    	$(".divArchivalmsgCol").addClass("d-none");
    	    	
    	tippyLoadingUtlInstance.inputFilterType.enable();

    	//$("#crNoInput").attr({"disabled": true});
    	//$("#billNoInput").attr({"disabled":true});
    	//$("#sampleNoInput").attr({"disabled":true});
    	//$("#labNoInput").attr({"disabled":true});
       	
    } else if ($(this).val() == "1") {
    	
    	$("#inputFilterType").attr({"disabled": false});
        enableInputFilters(true);
    	var inputFilterType = $('#inputFilterType').val();
    	setInputFilterTypeHelp(inputFilterType);
        $(".dateTypeSelectDiv").removeClass("d-none");
        $(".inputDates").removeClass("d-none");
        $(".divArchivalmsgCol").addClass("d-none");
              
        tippyLoadingUtlInstance.inputFilterType.disable();
 
    } else if ($(this).val() == "2") {
      
    	$("#inputFilterType").attr({"disabled": false});
    	enableInputFilters(true);
    	var inputFilterType = $('#inputFilterType').val();
    	setInputFilterTypeHelp(inputFilterType);
    	$(".dateTypeSelectDiv").addClass("d-none");
    	$(".inputDates").addClass("d-none");
    	$(".divArchivalmsgCol").removeClass("d-none");
    	
    	tippyLoadingUtlInstance.inputFilterType.disable();
      
    }
  });
});


/*--On Change in input type Cr.NO Or Bill No. Or Sample No., etc. starts---*/

$(document).ready(function() {
  $('#inputFilterType').change(function() {
    var inputFilterType = $(this).val();
    
    clearInputFilters();
    setInputFilterTypeHelp(inputFilterType);
    
    /*search made on bill no.*/
    if (inputFilterType == "1") {
    	$('#crNoInput').removeClass("d-none");
    	$('#billNoInput').addClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#requisitionNoInput').addClass("d-none");
        $('#requisitionDNoInput').addClass("d-none");
    }
    /*search made on Bill no.*/
    else if (inputFilterType == "2") {
    	$('#crNoInput').addClass("d-none");
    	$('#billNoInput').removeClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#requisitionNoInput').addClass("d-none");
        $('#requisitionDNoInput').addClass("d-none");
    }
    /*search made on sample. no.*/
    else if (inputFilterType == "3") {
        $('#crNoInput').addClass("d-none");
        $('#billNoInput').addClass("d-none");
        $('#sampleNoInput').removeClass("d-none");
        $('#labNoInput').addClass("d-none");
        $('#requisitionNoInput').addClass("d-none");
        $('#requisitionDNoInput').addClass("d-none");
        
        showLoading(false);
    }
    /*search made on sample. no.*/
    else if (inputFilterType == "4") {
    	$('#crNoInput').addClass("d-none");
        $('#billNoInput').addClass("d-none");
        $('#sampleNoInput').addClass("d-none");
        $('#labNoInput').removeClass("d-none");
        $('#requisitionNoInput').addClass("d-none");
        $('#requisitionDNoInput').addClass("d-none");
    }
    /*search made on requisition no.*/
    else if (inputFilterType == "5") {
	  $('#crNoInput').addClass("d-none");
	  $('#billNoInput').addClass("d-none");
      $('#sampleNoInput').addClass("d-none");
      $('#labNoInput').addClass("d-none");
      $('#requisitionNoInput').removeClass("d-none");
      $('#requisitionDNoInput').addClass("d-none");
    }
    /*search made on requisition Dno.*/
    else if (inputFilterType == "6") {
      $('#crNoInput').addClass("d-none");
      $('#billNoInput').addClass("d-none");
      $('#sampleNoInput').addClass("d-none");
      $('#labNoInput').addClass("d-none");
      $('#requisitionNoInput').addClass("d-none");
      $('#requisitionDNoInput').removeClass("d-none");
    }
    
    showLoading(false);
    
  });
});

$(document).ready(function() {
    $('#collAreaSelectInput').change(function() {
      if (globalSearchParam.collAreaCode == $(this).val()) {
        $('#labSelectInput').removeAttr("disabled");
        $('#getData').removeAttr("disabled");
        tippyLoadingUtlInstance.labSelectInputGroup.disable();
        tippyLoadingUtlInstance.getDataBtnDiv.disable();
      } else if ($(this).val() == "-1") {
        $('#labSelectInput').attr({
          "disabled": "disabled"
        });
        $('#getData').attr({
          "disabled": "disabled"
        });
        showLoading(false);
        tippyLoadingUtlInstance.labSelectInputGroup.setContent('<strong>Select Any Collection <span style="color: aqua;">Area </span>To Enable This</strong>');
        tippyLoadingUtlInstance.labSelectInputGroup.enable();
        tippyLoadingUtlInstance.getDataBtnDiv.setContent('<strong>Select Any Collection <span style="color: aqua;">Area </span>To Enable This</strong>');
        tippyLoadingUtlInstance.getDataBtnDiv.enable();
      } else {
        $('#labSelectInput').removeAttr("disabled");
        $('#getData').removeAttr("disabled");
        showLoading(true);
        enableEntryModifyBtn(false);
        tippyLoadingUtlInstance.labSelectInputGroup.disable();
        $('#labSelectInput').empty();
        globalSearchParam.collAreaCode = $(this).val();
        AjaxGetLabList(globalSearchParam);
      }
    });
  });
  $(document).ready(function() {
    $('#labSelectInput').change(function() {
      if (globalSearchParam.labCode == $(this).val()) {
        $('#getData').removeAttr("disabled");
        tippyLoadingUtlInstance.getDataBtnDiv.disable();
      } else if ($(this).val() == "-1") {
        $('#getData').attr({
          "disabled": "disabled"
        });
        showLoading(false);
        tippyLoadingUtlInstance.getDataBtnDiv.setContent('<strong>Select Any<span style="color: aqua;"> Lab Name </span>To Enable This</strong>');
        tippyLoadingUtlInstance.getDataBtnDiv.enable();
      } else {
        $('#getData').removeAttr("disabled");
        tippyLoadingUtlInstance.getDataBtnDiv.disable();
        globalSearchParam.labCode = $(this).val();
      }
    });
  });
/*------------------------------------------Ajax Calls Starts-------------------------------------------------------------------------------------------------*/
var globalCollAreaList = null;
var globalLabList = null;
var globalAjaxSearchTileObjectArr = {};

function AajaxGetCollAreaNLabList() {
  globalCollAreaList = null;
  var _mode = "AajaxGetCollAreaNLabList";
  var url = "/HISInvestigationG5/new_investigation/invResultValidationResp.cnt?hmode=" + _mode;
  globalAjaxSearchTileObjectArr.AajaxGetCollAreaList = $.getJSON(url, function(data) {
      if (!$.isEmptyObject(data)) {
        if (data.isSuccess == "1") {
          globalCollAreaList = data.collAreaList;
          globalLabList = data.labList;
          setCollAreaList(data);
          setLabList(data);
        } else {
          console.log("%cAajaxGetCollAreaNLabList Failed | ResponseError Is Below", "color:red;");
          console.log(data.error);
        }
      }
    })
    .done(function(data) {
      console.log("%cAajaxGetCollAreaNLabList success | ResponseData Is Below", "color:green;");
      console.log(data);
    })
    .fail(function(jqxhr, textStatus, error) {
      console.log("%cAajaxGetCollAreaNLabList Failed | ResponseError Is Below", "color:red;");
      var err = textStatus + ", " + error;
      console.log("Request Failed: " + err);
    });
}

function AjaxGetLabList(globalSearchParam) {
  var collAreaCode = globalSearchParam.collAreaCode;
  globalLabList = null;
  var _mode = "AjaxGetLabList";
  var url = "/HISInvestigationG5/new_investigation/invResultValidationResp.cnt?hmode=" + _mode + "&collAreaCode=" + collAreaCode;
  globalAjaxSearchTileObjectArr.AjaxGetLabList = $.getJSON(url, function(data) {
      if (!$.isEmptyObject(data)) {
        if (data.isSuccess == "1") {
          globalLabList = data.labList;
          setLabList(data);
        } else {
          console.log("%cAjaxGetLabList Failed | ResponseError Is Below", "color:red;");
          console.log(data.error);
        }
      }
    })
    .done(function(data) {
      console.log("%cAjaxGetLabList success | ResponseData Is Below", "color:green;");
      console.log(data);
    })
    .fail(function(jqxhr, textStatus, error) {
      console.log("%cAjaxGetLabList Failed | ResponseError Is Below", "color:red;");
      var err = textStatus + ", " + error;
      console.log("Request Failed: " + err);
    });
}


/*------------------------------------------functions Starts-------------------------------------------------------------------------------------------------*/

function clearInputFilters(){
	if($('.inputFiltersDiv').children("input")){
	$('.inputFiltersDiv').children("input").each(function() {
		  $( this ).val( "" );
		});
	}
}


function enableInputFilters(enableFlag){
	if(enableFlag){
		if($('.inputFiltersDiv').children("input")){
			$('.inputFiltersDiv').children("input").each(function() {
				  $( this ).attr({"disabled": false});
				});
			}
	}else {
		if($('.inputFiltersDiv').children("input")){
			$('.inputFiltersDiv').children("input").each(function() {
				  $( this ).attr({"disabled": true});
				});
			}
	}
}


function setInputFilterTypeHelp(inputFilterType){
	//var inputFilterType = $('#inputFilterType').val();
	
	if (inputFilterType == "0"){
		$("#inputFilterTypeHelp").html("Disabled");
	} else if (inputFilterType == "1") {
		$("#inputFilterTypeHelp").html("Search Based On Cr No.");
	} else if (inputFilterType == "2") {
		$("#inputFilterTypeHelp").html("Search Based On Bill No.");
	} else if (inputFilterType == "3") {
		$("#inputFilterTypeHelp").html("Search Based On Sample No.");
	} else if (inputFilterType == "4") {
		$("#inputFilterTypeHelp").html("Search Based On Lab No.");
	} else if (inputFilterType == "5") {
		$("#inputFilterTypeHelp").html("Search Based On Requisition No.");
	} else if (inputFilterType == "6") {
		$("#inputFilterTypeHelp").html("Search Based On Requisition DNo.");
	}
}

function setCollAreaList(data) {
    var collAreaList = data.collAreaList;
    $('#collAreaSelectInput').empty();
    var collectionAreaCount = Object.keys(collAreaList).length;
    if (collectionAreaCount == "1") {
      for (i in collAreaList) {
        for (key in collAreaList[i]) {
          $('#collAreaSelectInput').append('<option selected value="' + key + '">' + collAreaList[i][key] + '</option>');
        }
      }
    } else {
      $('#collAreaSelectInput').append('<option value="-1">Choose Collection Area</option>');
      $('#collAreaSelectInput').append('<option selected value="0">All Collection Area</option>');
      for (i in collAreaList) {
        for (key in collAreaList[i]) {
          $('#collAreaSelectInput').append('<option value="' + key + '">' + collAreaList[i][key] + '</option>');
        }
      }
    }
  }

function setLabList(data) {
    var labListDetails = data.labListDetails;
    var selectAllLabs = data.selectAllLabs;
    $('#labSelectInput').empty();
    var labCount = labListDetails.length;
    if (labCount == "1") {
    	for(key in labListDetails){
    		var labKeyStr=JSON.stringify(labListDetails[key]);
    		labKeyStr=labKeyStr.replace(/ /g, "&nbsp;")
    		$('#labSelectInput').append('<option selected value="'+labListDetails[key].labCode+'" key='+labKeyStr+'>' + labListDetails[key].departmentName+"-"+labListDetails[key].labName + '</option>');
    	}
    } else {
    	var selectAllLabsStr=JSON.stringify(selectAllLabs);
    	selectAllLabsStr=selectAllLabsStr.replace(/ /g, "&nbsp;")
    	$('#labSelectInput').append('<option value="-1">Choose Lab</option>');
    	$('#labSelectInput').append('<option selected="" value="0" key='+selectAllLabsStr+'>All Labs</option>');
    	for(key in labListDetails){
    		var labKeyStr=JSON.stringify(labListDetails[key]);
    		labKeyStr=labKeyStr.replace(/ /g, "&nbsp;");
    		$('#labSelectInput').append('<option value="'+labListDetails[key].labCode+'" key='+labKeyStr+'>' + labListDetails[key].departmentName+"-"+labListDetails[key].labName + '</option>');
  	  	}
    }
    showLoading(false);
}
