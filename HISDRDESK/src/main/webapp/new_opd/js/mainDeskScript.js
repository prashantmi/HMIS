$(document).ready(function(){
	if($('.prescWithoutLst').is(':checked')) 
	$('.prescriptionColBtn').eq(0).click(); 
	});



function onChangePrescWithoutLst(e){
	$('input[name=prescMode][value=2]').attr('checked','true'); 
	// $('input[name=prescMode]').val(2); 
	if($('input[name=prescMode]:checked').val() == 2) 
	{
		if($('.prescWithoutLst').is(':checked')) 
			$('.prescriptionColBtn').eq(0).click(); 
		else
			$('.rightPanelClose').click();
	}
}

function getDeltList(e)
 {
	//alert('1');
	//alert(e.value);
	$(".loader").css("display","");
	 $('#tree').append('<font id="treeMsgP" style="color:#1e87f0"><i class="fa fa-spinner fa-spin" style="font-size:18px"></i> Loading prescription....</font>'); // Do not comment !!! Not In use. 
	document.forms[0].strHiddenDeptCode.value=document.forms[0].deptUnitName.value ;
	document.forms[0].hmode.value="NEW";
	document.forms[0].submit();
 }

 function getPatientData(e)
 {
	//alert('1');
	//alert(e.value); 
	 $(".loader").css("display","");
	 $('#tree').append('<font id="treeMsgP" style="color:#1e87f0"><i class="fa fa-spinner fa-spin" style="font-size:18px"></i> Loading prescription....</font>'); // Do not comment !!! Not In use. 
		
	 if(document.forms[0].deptUnitName.value == '0#0#0')
		 document.forms[0].hmode.value="unspecified"; 
	 else
	document.forms[0].hmode.value="NEW";
	document.forms[0].submit();
 }

 function hideOverlay(){
	$('.overlay').toggleClass('overlayHide');
	$('#hamburger').toggleClass('hamburgerWithoutOverlay');
 }

 function resizeText(multiplier) {
  if (document.body.style.fontSize == "") {
    document.body.style.fontSize = "0.8em";
  }
  document.body.style.fontSize = parseFloat(document.body.style.fontSize) + (multiplier * 0.2) + "em";
}

 $(document).ready(function(){
		$('#patSideListBtn1').click(function(){
			//	var waitingPatCount = 0;
				//var attendedPatCount = 0;
				$('#waitingPatList1').empty();
				$('#visitedPatList1').empty();
				$('#patSideListId1').toggle("slow","swing"); 
				/* $('.patCatHeader .badge').text($('.totalPatCount').text()-$('.visitedPatCount').text()); */
				//$('#patSideListId > legend >span').text($('.totalPatCount').text());
		});
		
		$('.closee-consultancy').click(function(){
			//	var waitingPatCount = 0;
				//var attendedPatCount = 0;
				$('#waitingPatList1').empty();
				$('#visitedPatList1').empty();
				$('#patSideListId1').toggle("slow","swing"); 
				/* $('.patCatHeader .badge').text($('.totalPatCount').text()-$('.visitedPatCount').text()); */
				//$('#patSideListId > legend >span').text($('.totalPatCount').text());
		});
		
		
		
		

		$('#patSideListBtn2').click(function(){
			//	var waitingPatCount = 0;
				//var attendedPatCount = 0;
				//$('#waitingPatList2').empty();
				//$('#visitedPatList2').empty();
				$('#patSideListId2').toggle("slow","swing"); 
				/* $('.patCatHeader .badge').text($('.totalPatCount').text()-$('.visitedPatCount').text()); */
				//$('#patSideListId > legend >span').text($('.totalPatCount').text());
		});
		
		$('.closee-consultancy1').click(function(){
			//	var waitingPatCount = 0;
				//var attendedPatCount = 0;
				//$('#waitingPatList2').empty();
				//$('#visitedPatList2').empty();
				$('#patSideListId2').toggle("slow","swing"); 
				/* $('.patCatHeader .badge').text($('.totalPatCount').text()-$('.visitedPatCount').text()); */
				//$('#patSideListId > legend >span').text($('.totalPatCount').text());
		});
		
		
		
		
		
 
		
		
 });
 
 
$(document).ready(function(){
	$('#patSideListBtn').click(function(){
			var waitingPatCount = 0;
			var attendedPatCount = 0;
			$('#waitingPatList').empty();
			$('#visitedPatList').empty();
			$('#patSideListId').toggle("slow","swing"); 
			/* $('.patCatHeader .badge').text($('.totalPatCount').text()-$('.visitedPatCount').text()); */
			$('#patSideListId > legend >span').text($('.totalPatCount').text());
			$('.patientListBlock').each(function(){
			    if($(this).hasClass('isAttended_1'))
				{
			    	waitingPatCount++;
					$('#waitingPatList').append('<li><img class="img-circle img-responsive" src="/HISDRDESK/new_opd/img/'+($(this).find('.patGenAgeCat').text().split('/')[0]=='M' ? 'img_avatar.png' : 'img_avatar3.png')+'" style="margin: 3% 2%;float:left; width: 30px; min-width: 20px; box-shadow: 0px 0px 0px 0px rgba(0,0,0,0.2); border: 1px solid #fff;"><h5 style="padding: 0px; margin:0px;">'+$(this).find('.patName').text()+'</h5><p>'+$(this).find('.patCrNo').text()+'</p></li>'); /* 0px 5px 25px 0px rgba(0,0,0,0.2) */
				}
			    else
				{
			    	attendedPatCount++;
					$('#visitedPatList').append('<li><img class="img-circle img-responsive" src="/HISDRDESK/new_opd/img/'+($(this).find('.patGenAgeCat').text().split('/')[0]=='M' ? 'img_avatar.png' : 'img_avatar3.png')+'" style="margin: 3% 2%;float:left; width: 30px; min-width: 20px; box-shadow: 0px 0px 0px 0px rgba(0,0,0,0.2); border: 1px solid #fff;"><h5 style="padding: 0px; margin:0px;">'+$(this).find('.patName').text()+'</h5><p>'+$(this).find('.patCrNo').text()+'</p></li>'); /* 0px 5px 25px 0px rgba(0,0,0,0.2) */
				} 
		});


		$('#waitingPatList li').click(function(){ 
			var patCrNo = $(this).find('p').text();
			 count=0;
			    $('.patientListBlock').each(function(index){
			      console.log('Count:::'+count+':::: Cr :::'+patCrNo);
			      if($(this).is(':contains('+patCrNo+')'))
			      { hidePrescription(this);
				     $(this).find('.prescriptionColBtn').click();
				     $('#patSideListId').hide(); 
			        return false; }
			      else
			        count++;
			    });
			});
	  	$('#visitedPatList li').click(function(){ 
			var patCrNo = $(this).find('p').text();
			 count=0;
			    $('.patientListBlock').each(function(index){
			      console.log('Count:::'+count+':::: Cr :::'+patCrNo);
			      if($(this).is(':contains('+patCrNo+')'))
			      { hidePrescription(this);
				     $(this).find('.prescriptionColBtn').click();
				     $('#patSideListId').hide(); 
			        return false; }
			      else
			        count++;
			    });
			}); 
		$('.waitingPatHeader > span').text(waitingPatCount);
		$('.visitedPatHeader > span').text(attendedPatCount);
		
		$('.showPatientDtl').not('#patSideListId').click(function(){
			$('#patSideListId').hide(); 
			});
	});

	 
	$('.rightPanelClose').click(function(){
		$('#patSideListId').hide(); 
		});
});


 $(document).ready(function() {
       /* $('#example').DataTable();*/
       var api = null ;
       
   $('#example').addClass( 'nowrap' ); 
      var table = $('#example').DataTable({
          "processing": true,
          /* responsive: {
              details: true
          }, */ 
          responsive: true,
          columnDefs: [
              { targets: [-1, -3], className: 'dt-body-right' } ,
              { responsivePriority: 1, targets: 0 },
              { responsivePriority: 2, targets: -3 },
              { responsivePriority: 3, targets: -2 }
            ] ,
        
          
           "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
          "initComplete": function () {
             api = this.api();
             $('#mainDeskLoadingMsgDiv').hide();
          },
          "order": [[ 2, "desc" ]]
        });
     // table.columns([1,2]).visible(false);  
    //  console.log('UMIDFlagVal UMIDFlagVal UMIDFlagVal  '+$('#strRailTailFlgId').val());
      if($('#strRailTailFlgId').val() == '1'){
    	  table.columns([8]).visible(false);
      }else{
    	  console.log('LLLLLLLLLLLLL  ')
    	  table.column(7).visible(false);
      }
      table.page.len( 50 ).draw();
          
      
      $('.prescriptionColBtn').on( 'click', function () { 
          table.page.len( -1 ).draw();
      } );
       
      $('.rightPanelClose').on( 'click', function () {
          table.page.len( 50 ).draw();
      } );

      $('.prescriptionColBtn').on( 'click', function () {
    	  api.search('').draw();
      } );

     
      
    } );


 $(document).ready(function(){
 	$('.patDtlMdlTrgr').click(function(){
      var patName = $(this).find('.patName').text();
      var patCrNo = $(this).parent().parent().find('.patCrNo').text();
      $('#patientDtlModal').find('.patName').text(patName);
      $('#patientDtlModal').find('.patCrNo').text(patCrNo);
    });
 });


$(document).ready(function(){
	localStorage.setItem("flag","0");
	$("#printPrescriptionMainDeskModal").on('hide.bs.modal', function () {
		$('#printPrescFrameOnMainDeskId').remove();
		localStorage.setItem("flag","0");
		localStorage.removeItem("ajxRqstPatDtl");
	 });  
});


function printPrescriptionMainDeskFun(e){ 
  	if($('#printPrescFrameOnMainDeskId'))
	  	$('#printPrescFrameOnMainDeskId').remove();
	var crNo = $(e).closest('.patientListBlock').find('.patCrNo').text();
	var episodeCode = $(e).closest('.patientListBlock').find('.patEpisodeCode').text();
	var visitNo = $(e).closest('.patientListBlock').find('.patEpisodeVisitNo').text();
	var seatId = $(e).closest('.patientListBlock').find('.patSeatId').text();
	var visitDate = $(e).closest('.patientListBlock').find('.patLastVisitDate').text();
	var hospCode = $(e).closest('.patientListBlock').find('.patHospitalCode').text(); 
	localStorage.setItem("ajxRqstPatDtl",crNo+'#'+episodeCode+'#'+visitNo+'#'+seatId+'#'+(visitDate==''?$("#listDateFilter").val():visitDate.split(" ")[0])+'#'+hospCode);
	localStorage.setItem("flag","2");
	$('#printPrescriptionMainDeskModal .modal-body').prepend('<iframe id="printPrescFrameOnMainDeskId" style="width:100%;height:80vh;" src="/HISDRDESK/new_opd/transaction/DoctorDeskAction.cnt?hmode=PRINTPRESC"></iframe>');
	$('#printPrescriptionMainDeskModal').modal('show'); 
} 


$(document).ready(function(){
	$('.emrLink').click(function(e){
		var patName = $(this).parent().parent().find('.patName').text();
	    var patCrNo = $(this).parent().parent().find('.patCrNo').text();  
	    $('.emrPatName').html(patName);
	    $('.emrPatCrNo').html(patCrNo);
	    $('#emrModalTriggerBtn').click();
	});
});



$('#jstree_demo_div').jstree();
$('#jstree_demo_div').on("changed.jstree", function (e, data) {
	  console.log(data.selected);
	});  
$('#jstree_demo_div').on('ready.jstree', function() {
    $("#jstree_demo_div").jstree("open_all");          
});

  $('#jstree_demo').jstree({
    "core" : {
      "animation" : 0,
      "check_callback" : true,
      "themes" : { "stripes" : true },
      'data' : {
        'url' : function (node) {
          return node.id === '#' ?
            'ajax_demo_roots.json' : 'ajax_demo_children.json';
        },
        'data' : function (node) {
          return { 'id' : node.id };
        }
      }
    },
    "types" : {
      "#" : {
        "max_children" : 1,
        "max_depth" : 4,
        "valid_children" : ["root"]
      },
      "root" : {
        "icon" : "/static/3.3.6/assets/images/tree_icon.png",
        "valid_children" : ["default"]
      },
      "default" : {
        "valid_children" : ["default","file"]
      },
      "file" : {
        "icon" : "glyphicon glyphicon-file",
        "valid_children" : []
      }
    },
    "plugins" : [
      "contextmenu", "dnd", "search",
      "state", "types", "wholerow"
    ]
  });


 $(document).ready(function(){
	$('body').mousemove(function(event){
		var strmsgX =  event.pageX;   
		/*if(strmsgX<=0)
		$('.hamburger').click();*/
	});
});

function collapsePanel(e){
	$(e).parent().css('display','none');
	$(e).closest('.patientListBlock').children('.leftPanel').removeClass('col-sm-6').toggleClass('col-sm-12');
	$('body').append("<button id='rightNavBtn1' class='btn btn-info btn-sm' type='button' onclick='showPanel(this,event)' style='position: fixed; right: -10px; top:15vh;'>Right Panel</button>");
}
function showPanel(e){
	$('.rightPanel').css('display','');
	$('.leftPanel').removeClass('col-sm-12').toggleClass('col-sm-6');
	$('#rightNavBtn1').remove();
}


$(document).ready(function () {
  var trigger = $('.hamburger'),
      overlay = $('.overlay'),
     isClosed = false;

    trigger.click(function () {
      hamburger_cross();      
      $("body").click(function(){

      });
    });

function hamburger_cross() {
      if (isClosed == true) {          
        overlay.hide();
        trigger.removeClass('is-open');
        trigger.addClass('is-closed');
        isClosed = false;
      } else {   
      	overlay.removeClass('overlayHide');
        overlay.show();
        trigger.removeClass('hamburgerWithoutOverlay');
        trigger.removeClass('is-closed');
        trigger.addClass('is-open');
        isClosed = true;
      }
} 

function sideNavClose() {
        $('#wrapper').toggleClass('toggled');
        hamburger_cross();
} 

$('[data-toggle="offcanvas"]').click(function () {
      $('#wrapper').toggleClass('toggled');
  }); 
  $('.overlay').click(function(){
  	sideNavClose();
  });

  $('.sidebar-nav li:not(:first-child)').click(function(){
  	sideNavClose();
  }); 

});











	var count = 0;
	var saveCount = 0;
	var totalCount = 0;
	var str = '<div class="showPatientDtl"> </div>';
	var str1 = '<div class="showPatientDtl" id="showPatientDtlTileID" style="display:none"> </div>';
	
	
	
	$(document).ready(function(){    
			   $('.patientListBlock .templatePrescriptionBtn').click(function(){
			   		inTilePresc(this,'','','','');
			   });  
	});
	function inTilePresc(e,patName,patCrNo,episodeCode,patGenAgeCat,patCompleteGeneralDtl){  
			   	  $(e).parent().parent().css('background-color','#f2f2f2');
			      $(e).parent().parent().css('box-shadow','0 0 4px 1px black');
			      $(e).parent().parent().css('margin-bottom','5px');
			     /* $(this).parent().parent().css('border-left','10px solid #91061D'); */
					var temp = $(e).parent().parent().parent().attr('clicked');
					if(temp == 0){
						var effect = function() {
						  return $('.showPatientDtl').remove();
						};   
						$.when( effect() ).done(function() {
						    var topY = $(e).offset(); 
						      $('.leftPanel').animate({
							        scrollTop: topY.top	}, 0);
							  }); 
			            $('.patientListBlock').attr('clicked','0');
			            $(e).parent().parent().parent().append(str1); 
			            $(e).parent().parent().parent().find('.showPatientDtl').load('/HISDRDESK/new_opd/prescription.jsp', function(){
				      		$('#patNamePrescriptionPanel').text(patName);
				      		$('#patCrNoPrescriptionPanel').text(patCrNo);
				      		$('#patGenAgeCatPrescriptionPanel').text(patGenAgeCat);
				      		$('#patEpisodeCodePrescriptionPanel').text(episodeCode);
				      		$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val(patCompleteGeneralDtl);
				      		$('input[name=patGeneralDtl]').val(patName+'#'+patCrNo+'#'+episodeCode+'#'+patGenAgeCat);
				      		/* alert('inTileFunc::'+episodeCode); */
				      		$('.showPatientDtl').slideDown('slow'); 
				      		count=0;
							$('.patientListBlock').each(function(index){
								console.log('patientListBlock::::>>>>> '+count); 
								if($(this).find('.patCrNo').text().trim() === $('#patCrNoPrescriptionPanel').text().trim())
									return false;
									count++;
								}); 	
			      		});
						$(e).parent().parent().parent().attr('clicked','1');
					}
					if(temp == 1) 
					{
            $(e).parent().parent().parent().children('.showPatientDtl').toggleClass('hideCls'); 
					}
				}
 
	
	function showPrescription(e){  
		
			localStorage.setItem('refWindowFlag','0'); 	// Change added by Timsi to support Patient Referal Process
			localStorage.setItem('patReferedOrNotFlag', 0);	// Change added by Timsi to support Patient Referal Process
			var patName = $(e).closest('.patientListBlock').find('.patName').text();
			var patCrNo = $(e).closest('.patientListBlock').find('.patCrNo').text();
			var patEpisodeVisitNo = $(e).closest('.patientListBlock').find('.patEpisodeVisitNo').text();
			
			/* // Added By Timsi to format date(dd-mm-yyyy) in Patient Summary div
			var temp=$(e).closest('.patientListBlock').find('.patLastVisitDate').text();
			var patLastVisitDate;
			if(temp!=""){
				var date1=new Date(temp);
				var month = date1 .getMonth() + 1;
			    var day = date1 .getDate();
			    var year = date1 .getFullYear();
			    patLastVisitDate= day + "-" + month + "-" + year;
			}
			else{
				patLastVisitDate='First Visit';
			}
			*/
			
			var patLastVisitDate = $(e).closest('.patientListBlock').find('.patLastVisitDate').text()==='' ? 'First Visit' : $(e).closest('.patientListBlock').find('.patLastVisitDate').text(); //Changed By Timsi Kataria as suggested by Priyesh Sir
			var patVisitType = $(e).closest('.patientListBlock').find('.patVisitType').text();
			var patGaurdianName = $(e).closest('.patientListBlock').find('.patGaurdianName').text(); 
			var patDeptName = $(e).closest('.patientListBlock').find('.patDeptUnit').text();
			var nextPatName = $(e).closest('.patientListBlock').next().find('.patName').text(); 
			var prevPatName = $(e).closest('.patientListBlock').prev().find('.patName').text();
			var patGenAgeCat = $(e).closest('.patientListBlock').find('.patGenAgeCat').text();
			var episodeCode = $(e).closest('.patientListBlock').find('.patEpisodeCode').text();
			var hospitalCode = $(e).closest('.patientListBlock').find('.patHospitalCode').text();
			var seatId = $(e).closest('.patientListBlock').find('.patSeatId').text(); 
			var patConsultantName = $(e).closest('.patientListBlock').find('.patConsultantName').text();  
			var patCompleteGeneralDtl = $(e).closest('.patientListBlock').find('input[name=patCompleteGeneralDtl]').val();
			var patCompleteGeneralDtl1 = $(e).closest('.patientListBlock').find('input[name=patCompleteGeneralDtl1]').val();
			var teleConsultancyRequestId = $(e).closest('.patientListBlock').find('input[name=teleConsultancyRequestId]').val();
			
			var patOthersDetails = $(e).closest('.patientListBlock').find('.patOthersDetails').text();
			
			console.log('=============patOthersDetails==================');
			
			//console.log(JSON.parse(patOthersDetails).umid_no);
			/*------------------------------Reason for Visit from Registration------------*/
			var ReasonForVisitFormRegistrationvar = $(e).closest('.patientListBlock').find('input[name=ReasonForVisitFormRegistration]').val();
			/*------------------------------Reason For Visit from registration end--------------*/
			console.log('showPrescription::>>> '+patName+patCrNo+patEpisodeVisitNo+patLastVisitDate);
			/* alert('frst::'+episodeCode); */
			var prescMode = $('input[name=prescMode]:checked').val(); 
	        console.log('Presc in showPrescription() : '+prescMode);
	        
	        console.log('patCompleteGeneralDtl1  '+patCompleteGeneralDtl1);

			if(prescMode==1) //Not in use !! Do not comment.
			{	console.log('tile mode presc'+prescMode);
				inTilePresc(e, patName, patCrNo, episodeCode, patGenAgeCat, (patCompleteGeneralDtl+'^^^'+patCompleteGeneralDtl1)); //Not in use !! Do not comment.
			}
			else if(prescMode==2)
	        { console.log('page mode presc'+prescMode);
				$('.showPatientDtl').remove();
				$('.patientListBlock').attr('clicked','0');   //Not in use !! Do not comment.
				$('.rightPanel').append(str);
				$('.leftPanelHeader').toggleClass('col-sm-12 hideCls');
				$('.leftPanel').toggleClass('col-sm-12 hideCls');
				$('.prescriptionColShow').hide('slow');  //Not in use !! Do not comment.
				$('.mainTopHeader').hide();
				
				
				$('.rightPanel').parent().append('<h2 id="rightPanelLoadMsgId" style="margin-top:20%; margin-left:5%"><i class="fa fa-spinner fa-spin"></i> Loading Patient Prescription....</h3>');
				 $.ajax({
					url: '/HISDRDESK/new_opd/prescription.jsp',
					async:true,
					success: function(result){
						$('.rightPanel .showPatientDtl').html(result);
							$('#patNamePrescriptionPanel').text(patName);
				      		$('#patCrNoPrescriptionPanel').text(patCrNo);
				      		
				      		if(patOthersDetails != '{}'){
				      			
				      		$('#patOthersDetailsPrescriptionPanel').text(patOthersDetails);
				      		$('#patUmidPrescriptionPanel').text(JSON.parse(patOthersDetails).umid_no);
				      		$('#patRecentUmidTestPrescriptionPanel').text(JSON.parse(patOthersDetails).umid_no);
				      		
				      		$('#patRecentEmailTestPrescriptionPanel').text(JSON.parse(patOthersDetails).email_id);
				      		$('#patRecentValidityTestPrescriptionPanel').text(JSON.parse(patOthersDetails).validity);
				      		$('#patRecentAgeTestPrescriptionPanel').text(JSON.parse(patOthersDetails).age);
				      		$('#patRecentBloodTestPrescriptionPanel').text(JSON.parse(patOthersDetails).blood_group);
				      		$('#patRecentHandicappedTestPrescriptionPanel').text(JSON.parse(patOthersDetails).handicap_status);
				      		$('#patRecentLevelOfEntitlementTestPrescriptionPanel').text(JSON.parse(patOthersDetails).level_of_entitilment);
				      		
				      		$('#patRecentStatusTestPrescriptionPanel').text(JSON.parse(patOthersDetails).current_status);
				      		
				      		
				      		$('#patRecentDepartmentTestPrescriptionPanel').text(JSON.parse(patOthersDetails).department);
				      		$('#patRecentOPDElegibilityTestPrescriptionPanel').text(JSON.parse(patOthersDetails).opd_eligibility);
				      		$('#patRecentIPDElegibilityTestPrescriptionPanel').text(JSON.parse(patOthersDetails).ipd_eligibility);
				      		//$('#patRecentIPDElegibilityTestPrescriptionPanel').text(JSON.parse(patOthersDetails).blood_group);
				      		$('#patRecentBeneficiaryTestPrescriptionPanel').text(JSON.parse(patOthersDetails).beneficiary_type);
				      		$('#patOccupationTestPrescriptionPanel').text(JSON.parse(patOthersDetails).designation);
				      		$('#patStationTestPrescriptionPanel').text(JSON.parse(patOthersDetails).custom_unit);
				      		
				      		$('#patdesignationPrescriptionPanel').text(JSON.parse(patOthersDetails).designation);
				      		$('#patStationPrescriptionPanel').text(JSON.parse(patOthersDetails).custom_unit);
				      		
				      		
				      		
				      		
				      		// patIdImageforRailDtl
				      		$("#patSummaryTileImg").attr("src", JSON.parse(patOthersDetails).profile_pic);
				      		}else{
				      			$('#patOthersDetailsPrescriptionPanel').text('{}');
				      			$('#patUmidPrescriptionPanelId').css('display','none');
				      			$('#patUmidPrescriptionPanel').css('display','none');
				      			$('#patUmidPrescriptionPanel').text('');
				      			$('#patdesignationPrescriptionPanel').text('');
					      		$('#patStationPrescriptionPanel').text('');
					      			
				      			$('#patRecentUmidTilesPrescriptionPanel').css('display','none');
				      			$('#patRecentIPDElegibilityTestPrescriptionPanel').css('display','none');
				      			$('#patRecentOPDElegibilityTestPrescriptionPanel').css('display','none');
				      			$('#patRecentDepartmentTestPrescriptionPanel').css('display','none');
				      			$('#patRecentLevelOfEntitlementTestPrescriptionPanel').css('display','none');
				      			$('#patRecentHandicappedTestPrescriptionPanel').css('display','none');
				      			$('#patRecentBloodTestPrescriptionPanel').css('display','none');
				      			$('#patRecentAgeTestPrescriptionPanel').css('display','none');
				      			$('#patRecentStatusTestPrescriptionPanel').css('display','none');
				      			
				      			$('#patRecentValidityTestPrescriptionPanel').css('display','none');
				      			$('#patRecentEmailTestPrescriptionPanel').css('display','none');
				      			
				      			$('#patSummaryTileImg').attr('src',$(e).closest('.patientListBlock').find('.patProfileImg').attr('src'));
				      		}
				      		//$('#patSummaryTileImg').attr('src',$(e).closest('.patientListBlock').find('.patProfileImg').attr('src'));
				      		$('#patEpisodeVisitNoPrescriptionPanel').text(patEpisodeVisitNo);
				      		$('#patLastVisitDatePrescriptionPanel').text(patLastVisitDate);
				      		$('#patVisitTypePrescriptionPanel').text(patVisitType);
				      		$('#prescriptionPanelPatStatus').text($(e).closest('.patientListBlock').hasClass('isAttended_1')?'':'Attended').css('color',$(e).closest('.patientListBlock').hasClass('isAttended_1')?'#1bbf23':'red');
				      		$('#patGaurdianNamePrescriptionPanel').text(patGaurdianName);
				      		$('#patDeptName').text(patDeptName);
				      		$('#patGenAgeCatPrescriptionPanel').html(patGenAgeCat);
				      		$('#patHospitalCodePrescriptionPanel').html(hospitalCode); 
				      		$('#patSeatIdPrescriptionPanel').html(seatId); 
				      		$('#patConsultantNamePrescriptionPanel').html(patConsultantName); 
				      		if(nextPatName.trim()!='')
				      		$('#nextPatNamePrescriptionPanel').html(nextPatName.split("  ")[0].length>3 ? '('+nextPatName.split("  ")[0]+')' : '('+nextPatName.split("  ")[0]+' '+nextPatName.split("  ")[1]+')'); 
				      	  	if(prevPatName.trim()!='')
				      		$('#prevPatNamePrescriptionPanel').html(prevPatName.split("  ")[0].length>3 ? '('+prevPatName.split("  ")[0]+')' : '('+prevPatName.split("  ")[0]+' '+prevPatName.split("  ")[1]+')'); 
				      		$('#patEpisodeCodePrescriptionPanel').text(episodeCode);
				      		$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val(patCompleteGeneralDtl+'^^^'+patCompleteGeneralDtl1);
				      		$('input[name=eTeleconsultancyreq]').val(teleConsultancyRequestId);
				      		$('input[name=eTeleconsultancyreq]').val(teleConsultancyRequestId);
				      				      		
				      		/*--------------------------Added For Reason For Visit for registration----------------------*/
				      		if(ReasonForVisitFormRegistrationvar == '--'){
				      		$('#RegReasonForVisitId').html(ReasonForVisitFormRegistrationvar).hide();
				      		}else{
				      			$('#ReasonVisitFromRegistrationId').html(ReasonForVisitFormRegistrationvar);
				      		}
				   
				      		/*--------------------------Added For Reason For Visit for registration end----------------------*/ 
				      		$('input[name=patGeneralDtl]').val(patName+'#'+patCrNo+'#'+episodeCode+'#'+patGenAgeCat);
							$('.rightPanel').show();
							$('body').css('padding-top','0px');
							$('body .container-fluid:first-child').css('margin','0px');
							count=0;
							$('.patientListBlock').each(function(index){
								console.log('patientListBlock::::>>>>> '+count); 
								if($(this).find('.patCrNo').text().trim() === $('#patCrNoPrescriptionPanel').text().trim())
									return false;
									count++;
								}); 	
							$('#rightPanelLoadMsgId').remove();
						}
					}); 
				 /* $('.rightPanel .showPatientDtl').load('/HISDRDESK/new_opd/prescription.jsp', function(){
			      		$('#patNamePrescriptionPanel').text(patName);
			      		$('#patCrNoPrescriptionPanel').text(patCrNo);
			      		$('#patSummaryTileImg').attr('src',$(e).closest('.patientListBlock').find('.patProfileImg').attr('src'));
			      		$('#patEpisodeVisitNoPrescriptionPanel').text(patEpisodeVisitNo);
			      		$('#patLastVisitDatePrescriptionPanel').text(patLastVisitDate);
			      		$('#patVisitTypePrescriptionPanel').text(patVisitType);
			      		$('#prescriptionPanelPatStatus').text($(e).closest('.patientListBlock').hasClass('isAttended_1')?'':'Attended').css('color',$(e).closest('.patientListBlock').hasClass('isAttended_1')?'#1bbf23':'red');
			      		$('#patGaurdianNamePrescriptionPanel').text(patGaurdianName);
			      		$('#patDeptName').text(patDeptName);
			      		$('#patGenAgeCatPrescriptionPanel').html(patGenAgeCat);
			      		$('#patHospitalCodePrescriptionPanel').html(hospitalCode); 
			      		$('#patSeatIdPrescriptionPanel').html(seatId); 
			      		$('#patConsultantNamePrescriptionPanel').html(patConsultantName); 
			      		if(nextPatName.trim()!='')
			      		$('#nextPatNamePrescriptionPanel').html(nextPatName.split("  ")[0].length>3 ? '('+nextPatName.split("  ")[0]+')' : '('+nextPatName.split("  ")[0]+' '+nextPatName.split("  ")[1]+')'); 
			      	  	if(prevPatName.trim()!='')
			      		$('#prevPatNamePrescriptionPanel').html(prevPatName.split("  ")[0].length>3 ? '('+prevPatName.split("  ")[0]+')' : '('+prevPatName.split("  ")[0]+' '+prevPatName.split("  ")[1]+')'); 
			      		$('#patEpisodeCodePrescriptionPanel').text(episodeCode);
			      		$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val(patCompleteGeneralDtl);
			      		$('input[name=patGeneralDtl]').val(patName+'#'+patCrNo+'#'+episodeCode+'#'+patGenAgeCat);
						$('.rightPanel').show();
						$('body').css('padding-top','0px');
						$('body .container-fluid:first-child').css('margin','0px');
						count=0;
						$('.patientListBlock').each(function(index){
							console.log('patientListBlock::::>>>>> '+count); 
							if($(this).find('.patCrNo').text().trim() === $('#patCrNoPrescriptionPanel').text().trim())
								return false;
								count++;
							}); 	
						$('#rightPanelLoadMsgId').remove();
		      		});    */  
			}
			else if(prescMode==3)//Not in use !! Do not comment.
	        { console.log('modal mode presc'+prescMode);
				$('.showPatientDtl').remove();
				$('.patientListBlock').attr('clicked','0'); 
				$('.prescModalBody').append(str);
	      		$('.prescModalBody .showPatientDtl').load('/HISDRDESK/new_opd/prescription.jsp', function(){
			      		$('#patNamePrescriptionPanel').text(patName);
			      		$('#patCrNoPrescriptionPanel').text(patCrNo);
			      		$('#patGenAgeCatPrescriptionPanel').text(patGenAgeCat);
			      		$('#patEpisodeCodePrescriptionPanel').text(episodeCode);
			      		$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val(patCompleteGeneralDtl);
			      		$('input[name=patGeneralDtl]').val(patName+'#'+patCrNo+'#'+episodeCode+'#'+patGenAgeCat);
		      		});   
				$('#prescModalTriggerBtn').click();
			}  
	}

	function hidePrescription(e){
		$('.leftPanelHeader').toggleClass('col-sm-12 hideCls');
		$('.leftPanel').toggleClass('col-sm-12 hideCls');
		$('.prescriptionColShow').show();
		$('.rightPanel').hide();
		$('.mainTopHeader').slideDown();
		$('body').css('padding-top','15px');
		$('body .container-fluid:first-child').css('margin','5px 20px'); 
		//document.forms[0].hmode.value="NEW";
		//document.forms[0].submit();
	}

	function refreshListPage(){
		document.forms[0].hmode.value="NEW";
		document.forms[0].submit();
	}
 






$(function () {
  $('[data-toggle="tooltip"]').tooltip({template:'<div class="tooltip" role="tooltip"><div class="arrow"></div><div class="tooltip-inner"></div></div>'});
});

$(document).ready(function(){

	
	
});




function globalSearch(e){
	$('#searchPatLst').empty();
	var txt = e.value;
	txt = txt.toUpperCase(); 
	/*alert(txt);*/
	console.log('txt::::>>>'+txt);
	$('.patientListBlock:contains('+txt+')').hide('slow');
	$('.patientListBlock').each(function(index){
		console.log('each');
		if($(this).find('.patCrNo').text().toUpperCase().indexOf(txt)>='0' && txt!='')
			{
					console.log('matched');
					$('#searchPatLst').append('<option value="'+$(this).find('.patCrNo').text()+'"></option>'); 
			}
		});
	$('.patientListBlock:not(:contains('+txt+'))').hide('slow');
	$('.patientListBlock:contains('+txt+')').show('slow');
	/* $('.patientListBlock:contains('+txt+')').find('.patName').each(function(index){
		     var patNameforLst = $(this).text();
		     $('#searchPatLst').append('<option value="'+patNameforLst+'"></option>');
	});  */
	
}
function globalSearch2(e){
	$('#searchPatLst2').empty();
	var txt = e.value;
	txt = txt.toUpperCase(); 
	/*alert(txt);*/
	console.log('txt::::>>>'+txt);
	/* $('.patientListBlock:contains('+txt+')').hide('slow'); */
	$('.patientListBlock').each(function(index){
		console.log('each');
		if($(this).find('.patCrNo').text().toUpperCase().indexOf(txt)>='0' && txt!='')
			{
					console.log('matched');
					$('#searchPatLst2').append('<option value="'+$(this).find('.patCrNo').text()+'"></option>'); 
			}
		});
	/* $('.patientListBlock:not(:contains('+txt+'))').hide('slow'); */
	/* $('.patientListBlock:contains('+txt+')').show('slow'); */
	/* $('.patientListBlock:contains('+txt+')').find('.patName').each(function(index){
		     var patNameforLst = $(this).text();
		     $('#searchPatLst').append('<option value="'+patNameforLst+'"></option>');
	});  */
	
}


$(document).ready(function(){
	$('#patientSearchModal').on('hidden.bs.modal',function(){
		$('#uk-search-input2').val('');
	});	 
});	


function patLstColorSrchOther(e){
	 if($(e).is(':checked'))
	 {
	 	$('.otherSrch').show('slow');
	 }
	 else{
	 	$('.otherSrch').hide('slow');
	 }
}
function patLstColorSrchNewVisit(e){
	 if($(e).is(':checked'))
	 {
	 	$('.newVisitSrch').show('slow');
	 }
	 else{
	 	$('.newVisitSrch').hide('slow');
	 }
}

function patLstColorSrchReffered(e){
	 if($(e).is(':checked'))
	 {
	 	$('.refferedSrch').show('slow');
	 }
	 else{
	 	$('.refferedSrch').hide('slow');
	 }
}
function patLstColorSrchAppointment(e){
	 if($(e).is(':checked'))
	 {
	 	$('.appointmentBasedSrch').show('slow');
	 }
	 else{
	 	$('.appointmentBasedSrch').hide('slow');
	 }
}





 $(document).ready(function(){
	var count = 0;
	$('.patientListBlock').each(function(){
			count++;
		});
	$('.isAttended_2').each(function(){
		saveCount++;
	});
	totalCount = count;
	$('.visitedPatCount').text(saveCount);
	$('.totalPatCount').text(count); 
});

 
 $(document).ready(function(){
	 new PerfectScrollbar('.containerNav');
	  new PerfectScrollbar('.leftPanel');
	  new PerfectScrollbar('.prescModalBody');
	  new PerfectScrollbar('#patSideListId');
 });
 
 
$(document).ready(function(){
	 /* new PerfectScrollbar('.containerNav');
	  new PerfectScrollbar('.leftPanel');
	  new PerfectScrollbar('.prescModalBody');
	  new PerfectScrollbar('#patSideListId');
	 */ 
	  
	  tippy('.opdVitalBtn',{
		  content: 'Click for patient Vitals',
		     delay: 100,
		     arrow: true,
		     arrowType: 'round',
		     size: 'large',
		     duration: 500,
		     animation: 'scale'
	  });
	  tippy('.prescriptionColBtn', {
		     content: 'Click for patient Prescription',
		     delay: 100,
		     arrow: true,
		     arrowType: 'round',
		     size: 'large',
		     duration: 500,
		     animation: 'scale'
		 });
		 tippy('.printPrescriptionMainDeskTriggerBtn', {
		     content: 'Patient Prescription Reprint',
		     delay: 100,
		     arrow: true,
		     arrowType: 'round',
		     size: 'large',
		     duration: 500,
		     animation: 'scale'
		 }); 
		 tippy('.emrLink', {
		      content: 'Click for patient EMR',
		      delay: 100,
		      arrow: true,
		      arrowType: 'round',
		      size: 'large',
		      duration: 500,
		      animation: 'scale'
		  });
		 tippy('.fullScreenDeskBtn', {
		     content: 'Responsive View',
		     delay: 100,
		     arrow: true,
		     arrowType: 'round',
		     size: 'large',
		     duration: 500,
		     animation: 'scale'
		 });
		 tippy('.shortcutInfoRightPanel', {
		     content: 'Shortcut Keys',
		     delay: 100,
		     arrow: true,
		     arrowType: 'round',
		     size: 'large',
		     duration: 500,
		     animation: 'scale'
		 });
		 tippy('.rightPanelClose', {
		     content: 'Close Prescription',
		     delay: 100,
		     arrow: true,
		     arrowType: 'round',
		     size: 'large',
		     duration: 500,
		     animation: 'scale'
		 }); 
});

$(document).ready(function(){
	//alert($('input[name=strInitialMode]').val());
	if($('input[name=strInitialMode]').val() == '1')
	{
		sessionStorage.removeItem('selectedDate');
		
	}
	 var datepicker = $('#listDateFilter').datepicker({
		 
		     uiLibrary: 'bootstrap',
		     format: 'dd mmmm yyyy',
		     maxDate: function() {
		         var date = new Date();
		         date.setDate(date.getDate());
		         return new Date(date.getFullYear(), date.getMonth(), date.getDate());
		     },
		     icons: {
		         rightIcon: '<span class="glyphicon glyphicon-calendar"></span>'
		     },
		     showRightIcon: false,
		     close: function(e){
		     	getPatientData(e);
		     },
		     change: function (e) {
		    	 sessionStorage.setItem('selectedDate',$('#listDateFilter').val().toString());
		     }
		 });  
	var date = '';
	console.log(typeof date+'date:::::::::>>>>>>>>>>>>>>>'+(date!=''?date:'no date'));
	if(date!='')
	{
		datepicker.value(date);
	}
	else
	{
		if(sessionStorage.getItem('selectedDate'))
		{
			datepicker.value(new Date(sessionStorage.getItem('selectedDate')));
		}
		else
		{
			var date = new Date();
	        date.setDate(date.getDate());
	        datepicker.value(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
	        sessionStorage.setItem('selectedDate',new Date(date.getFullYear(), date.getMonth(), date.getDate()).toString());
		}
	} 
}); 

$(document).ready(function(){ 
	
	/*
	  if (elem.requestFullscreen) {
	    elem.requestFullscreen();
	  } else if (elem.webkitRequestFullscreen) { // Safari
	    elem.webkitRequestFullscreen();
	  } else if (elem.msRequestFullscreen) { // IE11 
	    elem.msRequestFullscreen();
	  }*/

	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		localStorage.removeItem("varSSOTicketGrantingTicketStoreage");
		localStorage.setItem("varSSOTicketGrantingTicketStoreage", varSSOTicketGrantingTicket);
	
	$('.fullScreenDeskBtn').click(function(){
		var elem = document.documentElement;
	
		/*	  if (elem.requestFullscreen) {
			    elem.requestFullscreen();
			  } else if (elem.webkitRequestFullscreen) { // Safari 
			    elem.webkitRequestFullscreen();
			  } else if (elem.msRequestFullscreen) { // IE11 
			    elem.msRequestFullscreen();
			  } */
		

		var win = window.open(window.location.href,'_blank');
		var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
		
		if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
			localStorage.removeItem("varSSOTicketGrantingTicketStoreage");
			localStorage.setItem("varSSOTicketGrantingTicketStoreage", varSSOTicketGrantingTicket);
		localStorage.setItem('isFullScreen','1'); 
	});
	
	if(localStorage.getItem('isFullScreen')=='1')
	{
		//$('.toggleForFullScreenDeskBtn').toggleClass('col-sm-4 col-sm-5');	//Commented by Timsi to improve responsiveness of Date Field in full screen mode.
		$('.fullScreenDeskBtn').hide();
	}
	$(window).on("beforeunload", function() { 
	    return localStorage.setItem('isFullScreen','0');
	});
	$('.fullScreenDeskBtn').click(function(){
		/* $('.fullScreenDeskBtn').hide(); */
		});
	if(!localStorage.getItem('isFullScreen'))
		$('.fullScreenDeskBtn:first-child').click();
	
	$("#uk-search-input2").on('keypress', function (e) {
		if (!e) e = window.event;
		    var keyCode = e.keyCode || e.which;
	    var val = this.value;
		console.log('keyCode::::>>>'+keyCode);
	    if(keyCode == 38 && keyCode == 40)
		    return false;
		    if (keyCode == '13' && val.trim()!='' && $('.patientListBlock').is(':contains('+val.trim()+')')){ 
		    if($('#searchPatLst2 option').filter(function(){
		        return this.value === val;        
		    }).length) {   
			    if(val == $('#patCrNoPrescriptionPanel').text().trim())
				{
			    	swal("Already open!!");
					return false;
				}
		    	hidePrescription(this);
				$('.patientListBlock:contains('+val+')').find('.prescriptionColBtn')[0].click();
				$('#patientSearchModal').find('.close').click();
				$(this).val('');
		    }
		    else{
		    	hidePrescription(this);
				$('.patientListBlock:contains('+val+')').find('.prescriptionColBtn')[0].click();
				$('#patientSearchModal').find('.close').click();
				$(this).val('');
			    }
		    }
	});
	
	/*$('.videocallList').click(function(){
		console.log('videocallList');
		
		var patCompleteGeneralDtl = $(e).closest('.patientListBlock').find('input[name=patCompleteGeneralDtl]').val();
		
		
		console.log('patCompleteGeneralDtl'+patCompleteGeneralDtl);
		
		
	});*/
});
function videocallList(e){
	console.log('videocallList');
	
	
	//console.log($('input[name=patCompleteGeneralDtlPrescriptionPanel]').val());
	var patCompleteGeneralDtl = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val();
	var reqFlg=patCompleteGeneralDtl.split('#')[18];
	var reqId=patCompleteGeneralDtl.split('#')[19];
	console.log('patCompleteGeneralDtl'+patCompleteGeneralDtl);
	var tele_data=patCompleteGeneralDtl.split('^^^')[1];
	//console.log(reqFlg + ' ===  '+ reqId);
	console.log('tele_data'+tele_data);
	//console.log(JSON.parse(JSON.stringify(patCompleteGeneralDtl.split('^^^')[1])));
	
	//var patCompleteGeneralDtl1 = $(e).closest('.patientListBlock').find('input[name=patCompleteGeneralDtl1]').val();
	
	//console.log("patCompleteGeneralDtl1::::"+patCompleteGeneralDtl1)
	 	
	//console.log('patCompleteGeneralDtl'+patCompleteGeneralDtl);
	console.log(JSON.parse(tele_data).hrgstr_consultant_name);
	var jsondata ={
        "data" : {
                    "title": "eConsultation Doctor Call",
                    "content": JSON.parse(tele_data).hrgstr_consultant_name +" is calling you for eConsultation. Please join the call using the �Join Video Call� link shown in the �Consultation and Status� page of the app.",
                    "navigateTo": JSON.parse(tele_data).hrgstr_req_id  
        },
        "to": JSON.parse(tele_data).patient_token 
		}

	
	
	$.ajax({
		  type: "POST",
		  beforeSend: function(request) { 
		      //Nagpur
			  // request.setRequestHeader('Authorization', 'key=AAAASo__iUw:APA91bF-hl9D6cH9SBNPfXqOtUrHS6P3CBXzNfsM66h4KpOMQjyvbFLSM7p_ahIrVTxwx9obp4UNhkSnccE59qgX4jXIwdouSS5Xwf_QuShGmTvJGmbt2RYvUwgcF-vFPthQSW_dT91o');
			  
			  //Mangalgiri
			 //  request.setRequestHeader('Authorization', 'key=AAAA_wN054A:APA91bFz4gupSTKaqoq-7fNsk6n-U5wRWeaPo50UNf1lbevOToha4Ac8jqewJKk5C5r4YaHY2Eu3cL3M2Ue9-F6cmztdgNF7ZNl33SnGVQm7SOMpKR0I2hqvXCBBpsJ_h5WrdQ7_ati-');
			  
			  //Bhubneshwar
			//    request.setRequestHeader('Authorization', 'key=AAAABolJZwI:APA91bH1HpICwhpcsZkPoOYqDmFQ0M0jQ3UEW_Bhr1-pZoy9QI05wmQRpW5uAKujbGedHf2uV_Av-KWoeBM7ZlU3e6qDZjyYb4qChlU0BTuyqxc_mwPemnX82mPEEnKDONpXKz_vNOou');
			  
			  //Patna
			  //request.setRequestHeader('Authorization', 'key=AAAA7BQ8QWs:APA91bH3_lsDHE9ozOnVcOI_FZUbDujnINqsfHr0PWPDwG0KdlXBDizVA4wMR9146_a5Cw2ZC3IbQvb9CabW2Kf5twmSNjjIBaPl9VFHK6ARIkaygNipyYMNfeBoC76u2PgER6l_7Xmo');
		      
			  //Raipur
			  //request.setRequestHeader('Authorization', 'key=AAAARpXMVTU:APA91bG8K04eMkgnu2A_drRCpTNRTH17p-ZDAf5MXymIJuoFF4_xPwQMRQM1--L8adeeYXgu56gFee5GfEnR6gfXEHG2G2wpHIKi4ZGd7P0WKYw0XFfJVHjjssG1whyxxf-NsCCj_wcc');

			  //Railway
			  request.setRequestHeader('Authorization', 'key= AAAAsUIV2YQ:APA91bF1PG-537KH8M5OSnCSy22g-VTlrE4wERTNZrYoPhTBU9mvAFIm8kEDPOpzff15Clym48B_eWiFuoavLMug_PdNQ4ti0HY-XR3dmuyBq1Lz_MTyfJhs0ZfGb37683YSgw32BRMy');
			 
			  
		      //Bhatinda
			 // request.setRequestHeader('Authorization', 'key=  AAAAF-tosS8:APA91bEzfvW8wrC2SZUd_r4DdzuE_NZe2qjav2zQEvQc7r0ctJYiQomelj5rQvIKJSVK443mXyzhNifWa0wTbjt1s_eIwwj6hi302ReYIHrj5Xj5zaPn0IoT5VJ4hb9uvmb-XutFxtHP');
			  
			  //Gorakhpur
			//  request.setRequestHeader('Authorization', 'key=  AAAA_dvcwEA:APA91bEjkUXeBdAy5OsXytkmr-FJ5b10YDXXGfSZ1lCk9qZaNi53ZHiCwhTEJGVHDtkM23bvyEUdFgZC3Ta-SEa0fWuxdwAZRERGCOXnl-v0DsPLPGDCH5Ju5uREFpJDGP-TZUtLsOO1');
			  
		  
		  },
		  url: "https://fcm.googleapis.com/fcm/send",
		  data: JSON.stringify(jsondata),
		  processData: false,
		  contentType: 'application/json',
          dataType: 'json',
		  success: function(msg) {
			console.log('Notofication Send');
		  }
		});
	
	
	
	
	var url='https://mconsultancy.uat.dcservices.in/'+reqId ;
	//window.open(url , "_blank");
	window.open(url, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=200,bottom=200,left=200,right=200,width=900,height=700");
	/*$('#OpeneConsultancyModal .modal-body').prepend('<iframe id="OpeneConsultancyModalFrameId" style="width:100%;height:80vh;" src="'+url+'"></iframe>');
	$('#OpeneConsultancyModal').modal('show');
	$("#OpeneConsultancyModal").find(".modal-backdrop").css({"z-index": "-1"});*/
	
}

$(document).ready(function(){
	
	console.log('showpatientCountId'+$('#showpatientCountId').val());
	
	
	
	 /*$(function () {
	     
	     $( document ).tooltip();
	     $('#patSideListBtn1').tooltip({
	         content: 'TOOLTIP CONTENT'
	     });
	 });*/
	 
	/*$('[data-toggle="tooltip"]').tooltip();
	$('#patSideListBtn1').tooltip({
        placement:"bottom"
      });*/
	//$('#patSideListBtn1').tooltip( "option", "content", "TOOLTIP CONTENT" );
	/*
	 * */
	//$("#patSideListBtn1").hover(function(){
		//$('#patSideListBtn1').tooltip( "option", "content", "TOOLTIP CONTENT" );
		$("#patSideListBtn1").attr("title",$('#showpatientCountId').val()+" Patient is Pending for Approval ");
		//$('#patSideListBtn1').tooltip();
		//$('[data-toggle="tooltip"]').tooltip();
		//$("#patSideListBtn1").tooltip();
		$('#patSideListBtn1').tooltip({
	        placement:"bottom" ,
	        container: 'body'
	      });
		
//	});
	
	
	
	});

$(document).ready(function(){
	
	$("#OpeneConsultancyModal").on('hide.bs.modal', function () {
		$('#OpeneConsultancyModalFrameId').remove();
	 });
});


$(document).ready(function() {
	$('#table panel data').click(function(){

	       /* $('#example').DataTable();*/
	       var api = null ;
	       
	   $('#example').addClass( 'nowrap' ); 
	      var table = $('#example').DataTable({
	          "processing": true,
	          /* responsive: {
	              details: true
	          }, */ 
	          responsive: true,
	          columnDefs: [
	              { targets: [-1, -3], className: 'dt-body-right' } ,
	              { responsivePriority: 1, targets: 0 },
	              { responsivePriority: 2, targets: -3 },
	              { responsivePriority: 3, targets: -2 }
	            ] ,
	        
	          
	           "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	          "initComplete": function () {
	             api = this.api();
	             $('#mainDeskLoadingMsgDiv').hide();
	          },
	          "order": [[ 2, "desc" ]]
	        });
	     // table.columns([1,2]).visible(false);  
	    //  console.log('UMIDFlagVal UMIDFlagVal UMIDFlagVal  '+$('#strRailTailFlgId').val());
	      if($('#strRailTailFlgId').val() == '1'){
	    	  table.columns([8]).visible(false);
	      }else{
	    	  console.log('LLLLLLLLLLLLL  ')
	    	  table.column(7).visible(false);
	      }
	      table.page.len( 50 ).draw();
	          
	      
	      $('.prescriptionColBtn').on( 'click', function () { 
	          table.page.len( -1 ).draw();
	      } );
	       
	      $('.rightPanelClose').on( 'click', function () {
	          table.page.len( 50 ).draw();
	      } );

	      $('.prescriptionColBtn').on( 'click', function () {
	    	  api.search('').draw();
	      } );
	      if(false){

	    		var tempName=$(id).parent().parent().find('a').text();
	    		console.log("tempName:::::::::::::::::::::"+tempName);
	    		var parametreArr=[];
	    		var elVal="";
	    		var elId="";
	    		var jsonForm={};
	    		var j;
	    		for(var i=0;i<$('#'+templateid).find('#subContDivLeft').find(".addedEl").length;i++)
	    			{
	    			j=i;
	    			elId=$('#'+templateid).find('#subContDivLeft').find(".addedEl")[i].id;
	    			
	    				if($($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).hasClass("addedElGrp"))
	    					elVal=$($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).find("input:checked").val();
	    				else if($($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).hasClass("actPic"))
	    					elVal=$($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).parent().parent().parent().find(".ImgUpDiv img").attr("src");
	    				else if($($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).hasClass("input-group")){
	    					elVal=$($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).find("input").val()+"#"+$($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).find("button").html();
	    				
	    				}
	    				else if($($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).prop("id").split("-")[0]=="MultiAc"){
	    					let values=[];
	    					console.log('if @@@@@@@@@@@@');
	    					$($('#'+templateid).find('#subContDivLeft').find(".addedEl")[i]).closest(".contDrag ").find(".addedElHelper .MultiHelper input").each((j,b)=>{
	    						if(j>0){
	    							values[j]=b.value;
	    						}

	    						
	    					});
	    					elVal=values;
	    				}
	    				else	{
	    					elVal=$("#"+elId).val();
	    					console.log("ELSE");
	    					console.log(elVal);
	    				}		
	    						
	    				
	    				
	    				console.log("-----------");
	    				console.log(elId);
	    				console.log(elVal);
	    				console.log("-----------");
	    				 jsonForm[elId ] = elVal;
	    				
	    			}
	    		
	    		 parametreArr[0]=jsonForm;
	    		
	    		 console.log(':::::::::::::'+ parametreArr[0]);
	    		// console.log(JSON.stringify(parametreArr));
	    		
	    		
	    		
	    		
	    		console.log($('#strTemplatecodeId').val());
	    		//var formid = $('form').attr('id');
	    		//console.log('Length of the form is ::  '+formid);

	    		/*var parametreArr=[];
	    		for(var j=0 ;j < formId.split('#').length-1 ;j++ )
	    			{
	    			var jsonForm={};
	    			var formID1='#'+formId.split('#')[j];
	    			//console.log(formID1);
	    			 var queryFrom = $(formID1).serializeArray();

	    			   
	    			 for (i in queryFrom) {
	    				//console.log('1  '+queryFrom[i].value);
	    			   jsonForm[queryFrom[i].name] = queryFrom[i].value;
	    			   if(!$(a).hasClass("addedElGrp"))
	    			   
	    			   jsonForm[$('input[name="' + queryFrom[i].name + '"]').attr("id") ] = queryFrom[i].value;
	    			  }
	    			 //console.log(JSON.stringify(jsonForm));
	    			 parametreArr[j]=jsonForm;
	    			}
	    		*/
	    		// return jsonForm;
	    		console.log(':::::::::::::');
	    		//console.log(JSON.stringify(parametreArr));
	    		 var myJSON='';
	    		 if(localStorage.getItem("PatDtlsJsonData"))
	    		 myJSON = localStorage.getItem("PatDtlsJsonData");
	    			myJSON = JSON.parse(myJSON);

	    		//console.log('Save Html Template'+ JSON.stringify(jsonForm));
	    		  var vitalJSON = { 
	    				  'strSaveData' : "1" ,
	    				  "strPatJsonData" : myJSON ,
	    				  "strTemplateId"  : templateid.toString(),
	    				  'strFormData'	: parametreArr
	    				  } ;
	    				  var data = JSON.stringify(vitalJSON); 
	    		  		console.log(data);
	    		  		//$('.opdTemplatePres').hide();	
	    		  		console.log("  Console.log  ");
	    		  		$('#OpenTemplateModal').modal('hide');
	    		$.ajax({url:'',type:'POST',data:{JsonData:data},success:function(result)
	    	    	{
	    			
	    			console.log('   ::::::::::::::::::::::::::::::::  ');

	    			swal("Data Save Successfully");
	    			
	    			$("#OpenTemplateModal").on('hide.bs.modal', function () {
	    				$('#OpenTemplateModalFrameId').remove();
	    			 });
	    			
	    			printDiv(templateid ,tempName);
	    			$('#OpenTemplateModalFrameId').remove();
	    			$('#OpenTemplateModal').modal('hide');
	    			
	    				$('#OpenTemplateModal').modal('hide');$('#OpenTemplateModalFrameId').remove();
	    	    	}
	    		});
	    		

	      }

	     
	      
	    
		
		
	})
	
});



$(document).ready(function() {
	
	if($('#strRailTailFlgId').val() == '0'){
		$('#patSideListBtn3').hide()
	}else{
		
		$("#patSideListBtn3").attr("title","Click here for Referral Stamping ");
		//$('#patSideListBtn1').tooltip();
		//$('[data-toggle="tooltip"]').tooltip();
		//$("#patSideListBtn1").tooltip();
		$('#patSideListBtn3').tooltip({
	        placement:"bottom" ,
	        container: 'body'
	      });
	}
	
});
function opnedrugbookmark(){
	var drugInstructionTags = [ "After Food","Before Food","After Breakfast","Before Breakfast","Bed Rest","Local application"];
	//alert(availableTags);
	$('#strDrugsRemarksId').val('');
	$("#strDrugsRemarksId").autocomplete({
        source: drugInstructionTags,
        appendTo : '#drugAdvicesInstructionsModal1'
    });
	
	$('#drugAdvicesInstructionsModal1').modal('show');
}
function opneExtdrugbookmark(){
	var drugInstructionTags = [ "After Food","Before Food","After Breakfast","Before Breakfast","Bed Rest","Local application"];
	$('#strDrugsRemarksId1').val('');
	//alert(drugInstructionTags);
	$("#strDrugsRemarksId1").autocomplete({
        source: drugInstructionTags,
        appendTo : '#drugAdvicesInstructionsModal2'
    });
	
	$('#drugAdvicesInstructionsModal2').modal('show');
}


