var row = {crNo:"", requisitionNo:"", requisitionDNo:"", testCode:"", groupCode:""};
function ff(){
	var crTrackingElm=setInvTrackingByCrNoBtnData(data, type, row);
	$(".").after(crTrackingElm);
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


if ($('.invTrackingByCrNoBtn').length) {
	$('.invTrackingByCrNoBtn').off("click").on("click", function() {
      var invTrackingByCrNoBtnVal = jQuery.parseJSON($(this).attr("key"));
      openInvTrackingByCrNoIFrame(invTrackingByCrNoBtnVal);
    });
}

if ($('.invTrackingTestCompareBtn').length) {
    $('.invTrackingTestCompareBtn').off("click").on("click", function() {
      var invTrackingTestCompareBtnVal = jQuery.parseJSON($(this).attr("key"));
      openInvTrackingTestCompareIFrame(invTrackingTestCompareBtnVal);
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
    
$(document).ready(function() {
	
	if($('.fancyBoxIFrame').length==0){
		$( "body" ).after( '<a data-fancybox data-type="iframe" id="fancyBoxIFrame" class="fancyBoxIFrame"></a>' );	
	}
	
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