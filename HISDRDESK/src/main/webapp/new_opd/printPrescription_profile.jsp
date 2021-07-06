<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Print Prescription</title>
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/fontawesome/css/all.css">
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/fontawesome/css/fontawesome.min.css">
	 <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/bootstrap/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script> 
	 
	<script src="/HIS/hisglobal/drDeskAssets/qrcodejs/qrcode.min.js"></script> 
	
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.css">
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.css.map">
    <script src="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.js"></script>
	<script type="text/javascript">


   
		
	</script>
	<style>
	ul#menu li {
	display:inline;
	margin: 0px !important;
	font-size: 14px;
	letter-spacing: inherit !important;
	color: #5a5a5a !important;
	text-align: justify;
	}
	</style>
	<style>
		body{
			padding-bottom:80px; 
		} 
		#printPrescPage{
			margin-bottom:45px; 
		} 
		.investigation{
			margin:0px !important;
		}
		
		.examination{
			margin:0px !important;
		}
		.investigation li{
			text-align:left !important;
		} 
		.investigation li p{
			margin:0px !important;
			font-size:14px;
		}
		.examination li{
			text-align:left !important;
		} 
		.examination li p{
			margin:0px !important;
			font-size:14px;
		}
		.printPrescTreatmentLst{
			margin:0px !important;
		} 
		.printPrescTreatmentLst li{
			text-align:left !important;
		} 
		.printPrescTreatmentLst li p{
			margin:0px !important;
			font-size:14px;
		}
		.printPrescPage{
			color:black !important;
		}
		.printPrescPage p{
			letter-spacing: inherit !important;
			color: #5a5a5a !important;
			font-weight:600;
			text-align: justify;
		}
		.printPrescPage p small{
		font-weight: 400 !important;
		font-size: 14px;
		}
		.printPrescPatDtlTbl tr td{
			border-top: 0px solid #ddd !important;
			padding: 2px 5px;
		}
		.printPrescPatDtlTbl tr th{
			border-top: 0px solid #ddd !important;
			padding: 2px 5px;
		} 
		.printPrescPatDtlTbl{ 
			font-size: 13px;
			margin:10px 0;
		}
		.printPrescTreatmentTbl{
			font-size: 13px
		}
		.printPrescTreatmentTbl tr td{
		border-top: 0px solid #ddd !important;
		}
		.printPrescTreatmentTbl tr th{
		border-top: 0px solid #ddd !important;
		border-bottom: 0px solid #ddd !important;
		}  
		
		
		
		#watermark
		{
		 position:fixed;
		 top:42%;
		 left:25%;
		 opacity:0.1;
		 font-size: 90px;
		 z-index:-1;
		 color: black;
		 transform: rotate(-20deg);
		}
		/* div.header {
		    display: block; text-align: center; 
		    position: running(header);
		}
		div.footer {
		    display: block; text-align: center;
		    position: running(footer);
		} */
		/* @page {
		    @top-center { content: element(header) }
		}
		@page { 
		    @bottom-center { content: element(footer) }
		} */
		#header {visibility: hidden;position:fixed; top:0}
		#footer{visibility: hidden;position:fixed;bottom:0;left:10px;color: #7e7e7e;}
		@media print {
		    #header, #footer {visibility: visible;}
		    .prescPrintBtn{display: none;}
		    .prescSaveBtn{display: none;}
		   /*  .divPrintPrescPatDtlTbl{visibility: hidden;} 
			.Logoimg{visibility: hidden;}  */
			 .withHeaderLabel{display:none;) 
		  } 
	</style>
</head>
<body id="printPrescFrameBody" onload="">
	<div id="header"> </div>

			<div style="right:8px; position:fixed">
				<label class="withHeaderLabel" style="z-index: 99999;display:none"><input type="checkbox" name="withHeaderCheck" value="1" >&nbsp  Without Header</label><br><!--With Header  -->
				<button class="btn btn-success prescSaveBtn" style="z-index:9999;" type="button" onclick="">Save</button>
				<button class="btn btn-info prescPrintBtn" style="z-index:9999;display:none" type="button" onclick="">Print</button>
			</div> 
			<!-- <div style="right:23px; position:fixed">
				<button class="btn btn-info bookmarkbtn" style="z-index:9999;padding-top: 5px;margin-top: 81px;" type="button" data-toggle="modal" data-target="#bookmarkmodal">BookMark</button>
			</div> --> 
<h2 align="center">Rx Bookmark Preview</h2>
	<h2 id="watermark"></h2>
		<div style="z-index: -1">
	      <div id="printPrescPage" class="container printPrescPage"> 
           <div class="row Logoimg" id="LogoimgId" style="display: none;">
				<div style="float:left">
					<img alt="" src="/HIS/hisglobal/drDeskAssets/img/logo.jpg" style="height: 120px"></div><br>
			      <div style="text-align:center">
			       <h4><b>${DoctorDeskFB.strHospitalName}</b></h4>
			       <h5>${DoctorDeskFB.strHospitalAddres}</h5> 
			     </div>
	</div>  
	<div style="display: none;">
			<div class="row divPrintPrescPatDtlTbl" id="divPrintPrescPatDtlTblId"  style="border-top: 1px solid grey; border-bottom: 1px solid grey;">
				<table class="table table-condensed printPrescPatDtlTbl">
					<tbody>
					<tr>
						 <th colspan="5">
							<table align="center">
								<tr><td></td><td><h4><font color="blue">OPD Prescription</font></h4></td><td></td></tr>
							</table>
						</th>
						</tr>
						<tr>
							<th>Name</th><td class="patName"></td><th>CR No.</th><td class="patCrNo"></td>
						</tr>
						<tr>
							<th>Age/Gender</th><td class="patAgeGen"></td><th>Patient Category</th><td class="patCat"></td>
						</tr>
						<tr>
							<th>Father/Spouse Name</th><td class="patRelName"></td><th>Department(Unit/Consultant)</th><td class="patDeptU"></td>
						</tr>
						<tr>
							<th>Visit Date</th><td class="patVisitDate"></td></td><th>Unit Head</th><td class="consultantName"></td>
						</tr>
						<tr>
							<th>Occupation</th><td class="occupation"></td></td><th>Mobile No.</th><td class="mobileno"></td>
						</tr>
						
						<tr id="strUmidtblID">
							<th>UMID</th><td class="umidId"></td>
							<th>Designation</th><td class="strDesignationID"></td>
							
							
						</tr>
						
						
						
						<tr id="strStationID">
							<th>Station</th><td class="strStationID"></td></td><th></th><td class=""></td>
						</tr>
						
					</tbody>
				</table>
			</div>
			
			 </div>
			<br>
			&nbsp
			<div class="row" id="reasonOfVisitDivId" >
				<div class="col-sm-12" style="">
					<p>CHIEF COMPLAINT   : <small class="reasonOfVisit"></small></p>
				</div>
			</div>
			<br>
			<div class="row" id="historyIllnessNotesDivId" >
				<div class="col-sm-12" style="z-index: -1">
					<p style="text-align: justify;">HISTORY OF PRESENT ILLNESS : <small class="historyIllnessNotes"></small></p>
				</div>
			</div>
			<br>
			<div class="row" id="vitalsdtlsDivId" >
				<div class="col-sm-12" style="">
					<p style="text-align: justify;">VITALS/GE : <small class="vitalsdtls"></small></p>
				</div>
			</div>
			<br>
			<div class="row" id="examinationDivId" >
				<div class="col-sm-12" style="">
					<p>EXAMINATION :</p>
					<ul class="examination1">
					</ul>
					
					<ul class="examination">
					</ul>
					
					
					
				</div>
			</div>
			<br>
			&nbsp
			<div class="row" id="completeHistoryDivId" >
				<div class="col-sm-12" style="">
					<p>COMPLETE HISTORY :</p>
					<ul class="completeHistory">
					</ul>
					
					<!-- <ul class="completeHistory">
					</ul> -->
					
					
					
				</div>
			</div>
			<br>
			
			<div class="row" id="chronicDiseaseDivId" >
				<div class="col-sm-12" style="">
					<p>CHRONIC DISEASE : <small class="chronicDisease"></small></p>
				</div>
			</div>
			<br>
			&nbsp
			<div class="row" id="diagnosisDivId" >
				<div class="col-sm-12" style="">
					<p>DIAGNOSIS : <small class="diagnosis"></small></p>
				</div>
				
			</div>
			
			<p class="diagnosisNoteID1" style="margin-left: 2%;">Diagnosis Advice : <small class="diagnosisNoteID"></small></p>
			
			<div class="row" id="investigationDivId" >
				<div class="col-sm-12" style="">
					<p>INVESTIGATIONS ADVISED : </p>
					<!-- <p><small class="investigation"></small></p> -->
					<ul class="investigation" id="menu">
					</ul>
				</div>
			</div>
			&nbsp
			<p class="investigationNoteID1" style="margin-left: 0%;">Investigation Advice : <small class="investigationNoteID"></small></p>
			<br>
			<div class="row" id="ProceduresidDivId" >
				<div class="col-sm-12" style="">
					<p>PROCEDURES :</p> 
					<!-- <small class="Proceduresid"></small> -->
					<ol class="Proceduresid">
					</ol>
					
				</div>
			</div>
			
			<br>
			
			<div class="row" id="treatmentadviceDivId" >
				<div class="col-sm-12" >
					<p>TREATMENT ADVICE : <small class="treatmentadvice"></small></p>
				</div>
			</div>
			
			<br>
			
			<div class="row" id="rxDivId" >
				<div class="col-sm-12" style="" align="center">
					<!-- <p class="text-left" style="margin-bottom: 0px;"> TREATMENT ADVISED :</p> -->
					<p style="text-align: left;font-size: small;font-family: cursive;margin-bottom: 0px;">Rx</p>
					<ol class="printPrescTreatmentLst1"> 
					</ol>
					<ol class="printPrescTreatmentLst"> 
					</ol>
					<!-- <table class="table table-condensed printPrescTreatmentTbl"> 
						<tbody> 
						</tbody>
					</table> -->
				</div>
			</div>
			
			
			&nbsp
			<br>
			
			
			<div class="row" id="NoDrugDivId" >
				<div class="col-sm-12" style="">
					<p><small class="NoDrugDivIdClass"></small></p>
				</div>
			</div>
			<br>
			
			&nbsp
			<div class="row" id="clinicalNoteDivId" >
				<div class="col-sm-12" style="">
					<p style="text-align: justify;">CLINICAL NOTE : <small class="clinicalNote"></small></p>
				</div>
			</div>
			<br>
			<div class="row" id="drugallergyDivId" >
				<div class="col-sm-12" style="">
					<p>ALLERGY : </p>
					<!-- <p><small class="drugallergy"></small></p> -->
					<ul class="drugallergy" id="menu">
					</ul>
				</div>
			</div>
			<br> 
			&nbsp
			
			<div class="row" id="NoAllergyDivId" >
				<div class="col-sm-12" style="">
					<p><small class="NoAllergyDivIdClass"></small></p>
				</div>
			</div>
			<br>
			
			&nbsp
			<div class="row" id="OtherAllergyDivId" >
				<div class="col-sm-12" style="">
					<p style="text-align: justify;">OTHER ALLERGY : <small class="OtherAllergy"></small></p>
				</div>
			</div>
			&nbsp
			
			<div class="row" id="followUpDivId" >
				<div class="col-sm-12" style="">
					<p>FOLLOW UP : <small class="followUp"></small></p>
				</div>
			</div>
			&nbsp
			<br> 
			
			<div class="row" id="refferPatientDeptDivId" >
				<div class="col-sm-12" style="">
					<p> REFER TO : <ul class="refferPatientDept"></ul></p>
				</div>
			</div>
			&nbsp
			<br> 
			
			<div class="row" id="admissionDeptDivId" >
				<div class="col-sm-12" style="">
					<p> Admission Advice To : <ul class="admissionPatientDept"></ul></p>
				</div>
			</div>
			&nbsp
			<div style="display: none;">
			<br> 
			<div id="patQrCode" style="" class="pull-right"></div>  
			<br>
			<br>
			<h5 style="font-weight:bold">Signature of Consultant / Resident :</h5> 
			<%--<font class="loginConsultantName" style="display:none"><%=request.getSession().getAttribute("UserName").toString() %></font> --%>					                      
			<h5 style="margin-top:1" id="strUserNameId"><%=request.getSession().getAttribute("UserName").toString() %> </h5> 
			<h5 class="visitDateonsultannt" style="margin-top:0"></h5> 
			<!-- <th>Consultant Name</th><td class="consultantName"></td> -->
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$('input[name=withHeaderCheck]').on('change', function(){
			if($(this).is(':checked'))
			{
				$('#printStyleId').text('');
				 //$(".divPrintPrescPatDtlTbl").addcss("visibility: hidden;");
				 $("#divPrintPrescPatDtlTblId").css("visibility", "hidden");
				 $("#LogoimgId").css("visibility", "hidden");
				 
			}
			else
			{
				$('#printStyleId').text('#printPrescPage{ margin-top:80px;}');
				
				$("#divPrintPrescPatDtlTblId").css("visibility", "");
				$("#LogoimgId").css("visibility", "");
				
			}
			});
		var myJSON;
		var  datasave;
		var  userJson;
		var struserflg=0;
	  if(localStorage.getItem("flag")=="0")
	  {
		  struserflg=1;
		myJSON = localStorage.getItem("myJSON");
		myJSON = JSON.parse(myJSON); 
	  }
	  else
	  {
		  $('.prescSaveBtn').hide();
		  var ajxRqstPatDtl = localStorage.getItem("ajxRqstPatDtl").split('#');
		  var url = '/HISDRDESK/services/restful/patdata/getPatData?Modval=1&CrNo='+ajxRqstPatDtl[0]+'&episodeCode='+ajxRqstPatDtl[1]+'&visitNo='+ajxRqstPatDtl[2]+'&seatId=&Entrydate='+ajxRqstPatDtl[4]+'&hosp_code='+ajxRqstPatDtl[5];
			$.ajax({url: url, /*  '/HISDRDESK/services/restful/patdata/getPatData?Modval=1&CrNo=331011800028649&episodeCode=10212001&visitNo=1&seatId=10038&Entrydate=27-DEC-2018&hosp_code=33101', */ 
				async:false,
				success: function(result){
					if(JSON.stringify(result)!="{}")
					{
						console.log(JSON.stringify(result)); 
						console.log(result);
						if(result.pat_details!=undefined)
						{
							if(result.pat_details.length>0)
							{ 
								myJSON = result.pat_details[0].HRSTR_JSON_DATA;
								userJson = result.pat_details[0].USER_NAME;
								datasave = result.pat_details[0].DATASAVE_TIME;
							}
							else
							{ 
								swal({
									  title: "Prescription not saved through lite version !!",
									  text: "please save again to be printed",
									  icon: "warning", 
									}).then(function(willDelete) {
										window.parent.closePopUpIframe();
									});
							}
						}
						else
						{ 
							swal({
								  title: "Prescription not saved through lite version !!",
								  text: "please save again to be printed",
								  icon: "warning", 
								}).then(function(willDelete) {
									window.parent.closePopUpIframe();
								});
						}
						console.log(myJSON);
					}
					}
				});
	  }
	  console.log('myJSON::::>>>>>'+myJSON);
	  if(myJSON==undefined)
		  return false;
		data = myJSON; 
		var tempObj = data;
		/* tempObj.InvTestCode = jQuery.grep(
				tempObj.InvTestCode, 
                function (item,index) { 
                  return index <  "2"; 
                });
		tempObj.InvTestCodeToPrint = jQuery.grep(
				tempObj.InvTestCodeToPrint, 
                function (item,index) { 
                  return index <  "0"; 
                });
		tempObj.DrugCodeCat = jQuery.grep(
				tempObj.DrugCodeCat, 
                function (item,index) { 
                  return index <  "2"; 
                });
		tempObj.ReasonOfVisit = jQuery.grep(
				tempObj.ReasonOfVisit, 
                function (item,index) { 
                  return index <  "2"; 
                });
		tempObj.Diagnosis = jQuery.grep(
				tempObj.Diagnosis, 
                function (item,index) { 
                  return index <  "2"; 
                });
		tempObj.PatCompleteGeneralDtl = jQuery.grep(
				tempObj.PatCompleteGeneralDtl, 
                function (item,index) { 
                  return index <  "0"; 
                }); */
		var qrcode = new QRCode(document.getElementById("patQrCode"), {
		    text: data.CR_No,      /*data.CR_No+'#'+data.pat_Name+'#'+data.patAge*/            /* JSON.stringify(myJSON), */
		    width: 120,
		    height: 120
		});
		$('.prescPrintBtn').click(function(){
			$(this).hide();
			var date = new Date();
			document.title=data.CR_No.toString()+date.getDate()+(date.getMonth()+1)+date.getFullYear();
			console.log('Date:::::::::>>>>>>>'+date.getDate()+(date.getMonth()+1)+date.getFullYear());
			console.log('data.CR_No.toString()+(date.getDate()+date.getMonth()+date.getFullYear())::::::::::>>>>>>>'+data.CR_No.toString()+date.getDate()+(date.getMonth()+1)+date.getFullYear());
			window.print();
			if(localStorage.getItem("flag")=="0")
			setTimeout(function(){ 
				$('.prescPrintBtn').show();
				window.parent.saveFromIframe(JSON.parse(localStorage.getItem("myJSON")) ,JSON.parse(localStorage.getItem("FormattedJson"))); 
			}, 1000); 
		});
		$('.prescSaveBtn').click(function(){
			window.parent.saveFromIframe(JSON.parse(localStorage.getItem("myJSON")),JSON.parse(localStorage.getItem("FormattedJson"))); 
			});
		$('.printPrescPage .visitDateonsultannt').text(new Date().getDate()+'/'+(new Date().getMonth()+1)+'/'+new Date().getFullYear()+' '+ new Date().getHours()+':'+new Date().getMinutes() );
		
		console.log('struserflg struserflg '+ (struserflg == 0 || struserflg == '0'));
		if(struserflg == 0 || struserflg == '0'){
			$('#strUserNameId').html(userJson);
			$('.printPrescPage .visitDateonsultannt').text(datasave );
		}
		$('.printPrescPage .visitDateonsultannt').text(datasave );
		$('.printPrescPage .patName').text(data.pat_Name);
		$('.printPrescPage .patCrNo').text(data.CR_No); 
		$('.footerCrNo').text(data.CR_No);
		$('.printPrescPage .patAgeGen').text(data.patAge+'/'+data.patGender);
		$('.printPrescPage .patCat').text(data.patCat);
		$('.printPrescPage .patRelName').text(data.patGaurdianName);
		$('.printPrescPage .patDeptU').text(data.patDept);
		var datef=(data.PatCompleteGeneralDtl).split('#')[12];
		$('.printPrescPage .patVisitDate').text(datef.split('/')[2]+'/'+datef.split('/')[1]+'/'+datef.split('/')[0] );
		//$('.printPrescPage .visitDateonsultannt').text(new Date().getDate()+'/'+(new Date().getMonth()+1)+'/'+new Date().getFullYear()+' '+ new Date().getHours()+':'+new Date().getMinutes() );
		$('.printPrescPage .consultantName').text(data.patConsultantName);
		$('.printPrescPage .mobileno').text((data.PatCompleteGeneralDtl).split('#')[14]);
		$('.printPrescPage .occupation').text((data.PatCompleteGeneralDtl).split('#')[17]);

		if(data.ReasonOfVisit.length == 0)
			{
			$( "#reasonOfVisitDivId" ).parent().find('br').remove();
			$( "#reasonOfVisitDivId" ).remove();
			}
		for(var i=0;i<data.ReasonOfVisit.length;i++)
		{
			var VisitReason='';
			console.log('Visit Reason Length:::::::'+data.ReasonOfVisit[i].split('^').length);
			if(data.ReasonOfVisit[i].split('^').length == 2)
				{
				VisitReason=data.ReasonOfVisit[i]+'^0^^0';
				}else{
					VisitReason=data.ReasonOfVisit[i];
					}
			var x=VisitReason.split('^')[2];
			switch (parseInt(x)) {
			  case 0:
			    text = "";
			    break;
				case 1:
			    text = "NR";
			    break;
				case 2:
			    text = "Left";
			    break;
				case 3:
			    text = "Right";
			    break;
				case 4:
			    text = "Bilateral";
			    break;
				default:
			    text = "";
			}
			var y=VisitReason.split('^')[4];
			switch (parseInt(y)) {
			  case 0:
			    text1 = "";
			    break;
				case 1:
			    text1 = "Day/s";
			    break;
				case 2:
			    text1 = "Week/s";
			    break;
				case 3:
			    text1 = "Month/s";
			    break;
				case 4:
			    text1 = "Year/s";
			    break;
				default:
			    text1 = "";
			}
			var temp='';

			if(VisitReason.split('^')[1].trim() != '' && VisitReason.split('^')[2] != '0'  && VisitReason.split('^')[3] != '' && VisitReason.split('^')[5] != '')
				temp=VisitReason.split('^')[1].trim()+'('+text+','+VisitReason.split('^')[3].trim()+' '+text1+') '+VisitReason.split('^')[5];
			else if( VisitReason.split('^')[1].trim() != '' && VisitReason.split('^')[2] != '0'  && VisitReason.split('^')[3] != '' )
				temp=VisitReason.split('^')[1].trim()+'('+text+','+VisitReason.split('^')[3].trim()+' '+text1+') ';
			else if( VisitReason.split('^')[1].trim() != '' && VisitReason.split('^')[2] != '0' )
				temp=VisitReason.split('^')[1].trim()+'('+text+') ';
			else if( VisitReason.split('^')[1].trim() != ''   && VisitReason.split('^')[3] != '' )
				temp=VisitReason.split('^')[1].trim()+'('+VisitReason.split('^')[3].trim()+' '+text1+') ';
			else if( VisitReason.split('^')[1].trim() != '' )
				temp=VisitReason.split('^')[1].trim() + ( VisitReason.split('^')[5] == '' ? '' : '('+ VisitReason.split('^')[5] +')' );



			
			/* if(text =='')
			temp=VisitReason.split('^')[1].trim()+'('+VisitReason.split('^')[3].trim() +text1+') '+VisitReason.split('^')[5];
			else if(VisitReason.split('^')[3].trim() == '')
			temp=VisitReason.split('^')[1].trim()+'('+text+') '+VisitReason.split('^')[5];	
			else if(text !='' && VisitReason.split('^')[3].trim() != '')
			temp=VisitReason.split('^')[1].trim()+'('+text+','+VisitReason.split('^')[3].trim()+' '+text1+') '+VisitReason.split('^')[5]; */
			
			$('.printPrescPage .reasonOfVisit').append(temp+', ');
		}
		console.log(':::::::::::'+ "strHistoryOfPresentIllNess" in data);
		if("strHistoryOfPresentIllNess" in data){
		if(data.strHistoryOfPresentIllNess != '')
			{
			$('.printPrescPage .historyIllnessNotes').append(data.strHistoryOfPresentIllNess);
			}else{
				$( "#historyIllnessNotesDivId" ).parent().find('br').remove();
				$( "#historyIllnessNotesDivId" ).remove();
				
				}
		}else{
			$( "#historyIllnessNotesDivId" ).parent().find('br').remove();
			$( "#historyIllnessNotesDivId" ).remove();
			
			}
		if("strVitalsChart" in data){
		if(data.strVitalsChart != '')
		{
		$('.printPrescPage .vitalsdtls').append(data.strVitalsChart);
		}else{
			$( "#vitalsdtlsDivId" ).parent().find('br').remove();
			$( "#vitalsdtlsDivId" ).remove();
			
			}
		}else{
			$( "#vitalsdtlsDivId" ).parent().find('br').remove();
			$( "#vitalsdtlsDivId" ).remove();
			
			}
		if(data.InvTestCodeToPrint.length == 0){
			$( "#investigationDivId" ).parent().find('br').remove();
			$( "#investigationDivId" ).remove();
			}
		if(data.strDrugAllergy != ''){
			$( "#NoAllergyDivId" ).remove();
			for(var i=0;i<data.strDrugAllergy.length;i++)
			{ 
				/*$('.printPrescPage .investigation').append(data.InvTestCode[i].split('^')[4].trim()+', ');*/
				console.log('data.strDrugAllergy[i].strAllergyName....................'+data.strDrugAllergy[i].strAllergyName);
				$('.printPrescPage .drugallergy').append(' <li> '+ (i+1)+') ' +data.strDrugAllergy[i].strAllergyName+' ,</li> ');
			}
		}else{
			$( "#drugallergyDivId" ).parent().find('br').remove();
			$( "#drugallergyDivId" ).remove();
		//	$('.printPrescPage .NoAllergyDivIdClass').append('No Known Drug Allergy');
			}
			
		for(var i=0;i<data.InvTestCodeToPrint.length;i++)
		{ 
			/*$('.printPrescPage .investigation').append(data.InvTestCode[i].split('^')[4].trim()+', ');*/
			//console.log('data.InvTestCodeToPrint[i]....................'+data.InvTestCodeToPrint[i].split('^').length);
		//	$('.printPrescPage .investigation').append(' <li> '+ (i+1)+') ' +(data.InvTestCodeToPrint[i].split('^').length<=5?data.InvTestCodeToPrint[i].split('^')[4].trim():data.InvTestCodeToPrint[i].split('^')[5].trim())+' ,</li> ');
			$('.printPrescPage .investigation').append(' <li> '+ (i+1)+') ' +(data.InvTestCodeToPrint[i].split('^').length ==6 ?  data.InvTestCodeToPrint[i].split('^')[5].trim():data.InvTestCodeToPrint[i].split('^')[7].trim())+' ,</li> ');
		}

		
		console.log('data.strSystematicExamniation.length::::');
		console.log("strSystematicExamniation" in data );

	//	struserflg=0;
		if("strSystematicExamniation" in data){
			if(data.strSystematicExamniation.strLocalExamn != '' || data.strSystematicExamniation.strcvs != '' || data.strSystematicExamniation.strrs != '' || data.strSystematicExamniation.strcns != '' || data.strSystematicExamniation.strpA != '' || data.strSystematicExamniation.strmuscularExamn !='' || data.strSystematicExamniation.strotherExamn != '' || data.strpiccle.strpallor =='1' || data.strpiccle.strpallor =='1' || data.strpiccle.strcyanosis =='1' || data.strpiccle.strclubbing =='1' || data.strpiccle.strclubbing =='1' || data.strpiccle.stredema =='1'){
					
				} else{
					$( "#examinationDivId" ).parent().find('br').remove();
					$( "#examinationDivId" ).remove();
					}
			}else{
				$( "#examinationDivId" ).parent().find('br').remove();
				$( "#examinationDivId" ).remove();
				}
			console.log("strSystematicExamniation::::::");
			console.log("strSystematicExamniation" in data);
			if("strSystematicExamniation" in data){
			if(data.strSystematicExamniation.strcvs != '') 
			$('.printPrescPage .examination').append('<li><p>CVS: <small> '+(data.strSystematicExamniation.strcvs)+'</small></p></li>');

			if(data.strSystematicExamniation.strrs != '') 
				$('.printPrescPage .examination').append('<li><p>RS: <small> '+(data.strSystematicExamniation.strrs)+'</small></p></li>');

			if(data.strSystematicExamniation.strcns != '') 
				$('.printPrescPage .examination').append('<li><p>CNS: <small> '+(data.strSystematicExamniation.strcns)+'</small></p></li>');

			if(data.strSystematicExamniation.strpA != '') 
				$('.printPrescPage .examination').append('<li><p>P/A: <small> '+(data.strSystematicExamniation.strpA)+'</small></p></li>');

			console.log("data.strSystematicExamniation"+"strmuscularExamn" in  data.strSystematicExamniation);

			if(data.strSystematicExamniation.strotherExamn != '') 
				$('.printPrescPage .examination').append('<li><p>General Examination:<small> '+(data.strSystematicExamniation.strotherExamn)+'</small></p></li><br>');

			if("strmuscularExamn" in  data.strSystematicExamniation){
			if(data.strSystematicExamniation.strmuscularExamn != '') {
				$('.printPrescPage .examination').append('<li><p> Muscular Examination: <small> '+(data.strSystematicExamniation.strmuscularExamn)+'</small></p></li>');
			}
			}

			if("strLocalExamn" in  data.strSystematicExamniation){
				if(data.strSystematicExamniation.strLocalExamn != '') {
					$('.printPrescPage .examination').append('<li><p> Local Examination: <small> '+(data.strSystematicExamniation.strLocalExamn)+'</small></p></li>');
				}
			}

			
			
			}
			if("strpiccle" in data){
			if(data.strpiccle.strpallor =='1')
				$('.printPrescPage .examination1').append('Pallor &nbsp;&nbsp;&nbsp;');

			if(data.strpiccle.stricterus =='1')
				$('.printPrescPage .examination1').append('Icterus &nbsp;&nbsp;&nbsp;');

			if(data.strpiccle.strcyanosis =='1')
				$('.printPrescPage .examination1').append('Cyanosis &nbsp;&nbsp;&nbsp;');

			if(data.strpiccle.strclubbing =='1')
				$('.printPrescPage .examination1').append('Clubbing &nbsp;&nbsp;&nbsp;');

			if(data.strpiccle.striymphadenopathyId =='1')
				$('.printPrescPage .examination1').append('Lymphadenopathy &nbsp;&nbsp;&nbsp;');

			if(data.strpiccle.stredema =='1')
				$('.printPrescPage .examination1').append('Edema &nbsp;&nbsp;&nbsp;');
			//$('.printPrescPage .examination').append('<br>');
			}




			if("strCompleteHistory" in data){
				if(data.strCompleteHistory.strpastHistory != '' || data.strCompleteHistory.strpersonalHistory != '' || data.strCompleteHistory.strfamilyHistory != '' || data.strCompleteHistory.strtreatmentHistory != '' || data.strCompleteHistory.strsurgicalHistory != '' ){
						
					} else{
						$( "#completeHistoryDivId" ).parent().find('br').remove();
						$( "#completeHistoryDivId" ).remove();
						}
				}else{
					$( "#completeHistoryDivId" ).parent().find('br').remove();
					$( "#completeHistoryDivId" ).remove();
					}
				console.log("completeHistoryDivId::::::");
				console.log("strSystematicExamniation" in data);
				if("strSystematicExamniation" in data){
				if(data.strCompleteHistory.strpastHistory != '') 
				$('.printPrescPage .completeHistory').append('<li><p>Past History: <small> '+(data.strCompleteHistory.strpastHistory)+'</small></p></li>');

				if(data.strCompleteHistory.strpersonalHistory != '') 
					$('.printPrescPage .completeHistory').append('<li><p>Personal History: <small> '+(data.strCompleteHistory.strpersonalHistory)+'</small></p></li>');

				if(data.strCompleteHistory.strfamilyHistory != '') 
					$('.printPrescPage .completeHistory').append('<li><p>Family History: <small> '+(data.strCompleteHistory.strfamilyHistory)+'</small></p></li>');

				if(data.strCompleteHistory.strtreatmentHistory != '') 
					$('.printPrescPage .completeHistory').append('<li><p>Treatment History: <small> '+(data.strCompleteHistory.strtreatmentHistory)+'</small></p></li>');

				if(data.strCompleteHistory.strsurgicalHistory != '') 
					$('.printPrescPage .completeHistory').append('<li><p>Surgical History:<small> '+(data.strCompleteHistory.strsurgicalHistory)+'</small></p></li>');
				}



				if(data.strChronicDisease.length == 0)
				{
				$( "#chronicDiseaseDivId" ).parent().find('br').remove();
				$( "#chronicDiseaseDivId" ).remove();
				}




				for(var i=0;i<data.strChronicDisease.length;i++)
				{
					var temp ='' ; 
				 if(data.strChronicDisease[i].CronicDiseaseName !='' && data.strChronicDisease[i].CronicDiseaseDuration !='' && data.strChronicDisease[i].CronicDiseaseRemarks !=''){
							temp= data.strChronicDisease[i].CronicDiseaseName +'('+data.strChronicDisease[i].CronicDiseaseDuration+'yrs)'+ '('+ data.strChronicDisease[i].CronicDiseaseRemarks +')';
							} 
				 else if(data.strChronicDisease[i].CronicDiseaseName != '' && data.strChronicDisease[i].CronicDiseaseDuration !='' ){
						temp= data.strChronicDisease[i].CronicDiseaseName +'('+data.strChronicDisease[i].CronicDiseaseDuration+'yrs)';
						}
					else if(data.strChronicDisease[i].CronicDiseaseName != '' &&  data.strChronicDisease[i].CronicDiseaseRemarks !='' ){
								temp =data.strChronicDisease[i].CronicDiseaseName  + '('+ data.strChronicDisease[i].CronicDiseaseRemarks +')';
								}else{
									temp =data.strChronicDisease[i].CronicDiseaseName ;
									}
					
					$('.printPrescPage .chronicDisease').append(temp+', ');
				}


			
		if("strInvestgationNote" in data){
		if(data.strInvestgationNote != '')
			{
			$('.printPrescPage .investigationNoteID').append(data.strInvestgationNote);
			}
		else{
			$( ".printPrescPage .investigationNoteID1" ).remove();
			}
		
		} 
		
		if("strClinicalProcedure" in data){
		for(var i=0;i<data.strClinicalProcedure.length;i++)
		{ 
			var x=data.strClinicalProcedure[i].split('#')[2];
			switch (parseInt(x)) {
			  case 0:
			    text = "";
			    break;
				case 1:
			    text = "NR";
			    break;
				case 2:
			    text = "Left";
			    break;
				case 3:
			    text = "Right";
			    break;
				case 4:
			    text = "Bilateral";
			    break;
				default:
			    text = "";
			}
			
			console.log('data.Proceduresid[i]....................'+data.strClinicalProcedure[i].split('^').length);
			/* $('.printPrescPage .Proceduresid').append('<li><p>'); */
			if(text !="")
			text ='('+text+')';
			if(data.strClinicalProcedure[i].split('#').length == 7){
				var remarksprocedure=data.strClinicalProcedure[i].split('#')[4] ;
				//commented by ashutoshk for hiding service area name 
				//$('.printPrescPage .Proceduresid').append('<li><p>'+ data.strClinicalProcedure[i].split('#')[6] +' ['+data.strClinicalProcedure[i].split('#')[0]+']' + (text != '' ? text : '' )+ (remarksprocedure != '' ? '('+remarksprocedure +')'	 : '' ));
				$('.printPrescPage .Proceduresid').append('<li><p>'+' ['+data.strClinicalProcedure[i].split('#')[0]+']' + (text != '' ? text : '' )+ (remarksprocedure != '' ? '('+remarksprocedure +')'	 : '' ));
			}else{
			var remarksprocedure=data.strClinicalProcedure[i].split('#')[4] ;
			
			$('.printPrescPage .Proceduresid').append('<li><p>'+data.strClinicalProcedure[i].split('#')[0] + (text != '' ? text : '' )+ (remarksprocedure != '' ? '('+remarksprocedure +')'	 : '' ));
			}
			/* if(text != '')
			$('.printPrescPage .Proceduresid').append('('+text+')');
			
			if(data.strClinicalProcedure[i].split('#')[4] != '')
			$('.printPrescPage .Proceduresid').append('( '+data.strClinicalProcedure[i].split('#')[4]+' )');
 */
			$('.printPrescPage .Proceduresid').append('</p></li>');
		}
		}
		if("strClinicalProcedure" in data){
		if(data.strClinicalProcedure.length == 0)
			{
			$( "#ProceduresidDivId" ).parent().find('br').remove();
			$( "#ProceduresidDivId" ).remove();
			}
		}else{
			$( "#ProceduresidDivId" ).parent().find('br').remove();
			$( "#ProceduresidDivId" ).remove();
			}
		if(data.Diagnosis.length == 0){
			$( "#diagnosisDivId" ).parent().find('br').remove();
			$( "#diagnosisDivId" ).remove();
			}
		
		for(var i=0;i<data.Diagnosis.length;i++)
		{  
			var DiagnosisType='';
			console.log('DiagnosisType Length:');
			console.log(data.Diagnosis[i].split('#').length);
			if(data.Diagnosis[i].split('#').length == 8)
				{
				DiagnosisType=data.Diagnosis[i]+'#0##0#' ;
				}else{
					DiagnosisType=data.Diagnosis[i];	
					}
			var x=DiagnosisType.split('#')[4];
			switch (parseInt(x)) {
			  case 0:
			    text = "";
			    break;
				case 1:
			    text = "NR";
			    break;
				case 2:
			    text = "Left";
			    break;
				case 3:
			    text = "Right";
			    break;
				case 4:
			    text = "Bilateral";
			    break;
				default:
			    text = "";
			}
			if(data.Diagnosis[i].split('#')[0] =='0')
				{
				$('.printPrescPage .diagnosis').append(DiagnosisType.split('^')[1].trim().split('#')[0]+', ');
				}else{
			if(DiagnosisType.split('^')[1].trim().split('#')[1]!=undefined && DiagnosisType.split('^')[0].trim().split('#')[1]!=0)
			$('.printPrescPage .diagnosis').append(' '+DiagnosisType.split('^')[1].trim().split('#')[0]);

			if(DiagnosisType.split('#')[1] != '')
				$('.printPrescPage .diagnosis').append('(<i>'+DiagnosisType.split('^')[1].trim().split('#')[1]+', </i>');	

			if(text != "")
				$('.printPrescPage .diagnosis').append(text +', ');	
			
			console.log('DiagnosisType.split'+DiagnosisType);
			if(DiagnosisType.split('#')[7] != '')
			$('.printPrescPage .diagnosis').append(' '+DiagnosisType.split('#')[7] );

			$('.printPrescPage .diagnosis').append(')');

			}
			/* else
			$('.printPrescPage .diagnosis').append(DiagnosisType.split('^')[1].trim().split('#')[0]+', '); */
		}
		//if("strDiagnosisNote" in data){
		if(data.strDiagnosisNote != '')
		{
			$('.printPrescPage .diagnosisNoteID').append(data.strDiagnosisNote);
			$('.printPrescPage .diagnosisNoteID').append('<br>');
		} else{
			$( ".printPrescPage .diagnosisNoteID1" ).remove();
		}
		
		
		
		if("strotherAllergies" in data){
			if(data.strotherAllergies != '')
			{
			  $('.printPrescPage .OtherAllergy').append(data.strotherAllergies);
				//$('.printPrescPage .treatmentadvice').append(data.strtreatmentAdvice);
			}else{
				$( "#OtherAllergyDivId" ).parent().find('br').remove();
				$( "#OtherAllergyDivId" ).remove();
				}
			}else{
				$( "#OtherAllergyDivId" ).parent().find('br').remove();
				$( "#OtherAllergyDivId" ).remove();
				}
		
		
		
		
		//}
		if(data.FOLLOW_UP[0].progressNote !='')
		$('.printPrescPage .clinicalNote').text(data.FOLLOW_UP[0].progressNote);
		else{
			$( "#clinicalNoteDivId" ).parent().find('br').remove();
			$( "#clinicalNoteDivId" ).remove();
			}
		if("strtreatmentAdvice" in data){
		if(data.strtreatmentAdvice != '')
		{
		  $('.printPrescPage .treatmentadvice').append('<pre style="background-color:white;border: 0px solid #ccc; text-align: justify;  width: 650px; font-weight: 400 !important;font-size: 14px; overflow: hidden; ">'+data.strtreatmentAdvice+'</pre>');
			//$('.printPrescPage .treatmentadvice').append(data.strtreatmentAdvice);
		}else{
			$( "#treatmentadviceDivId" ).parent().find('br').remove();
			$( "#treatmentadviceDivId" ).remove();
			}
		}else{
			$( "#treatmentadviceDivId" ).parent().find('br').remove();
			$( "#treatmentadviceDivId" ).remove();
			}
		if(data.DrugCodeCat.length == 0){
			$( "#rxDivId" ).parent().find('br').remove();
			$( "#rxDivId" ).remove();
		//	$('.printPrescPage .NoDrugDivIdClass').append('No drugs Prescribed');
			}
		for(var i=0;i<data.DrugCodeCat.length;i++)
		{ 
			$( "#NoDrugDivId" ).remove();
		/* Not in Use *//*  $('.printPrescPage .printPrescTreatmentTbl tbody').append('<tr><td>'+data.DrugCodeCat[i].split('&&')[0].trim()+'</td><td>'+data.DrugCodeCat[i].split('&&')[2].trim()+'</td><td>'+data.DrugCodeCat[i].split('&&')[4].trim()+'</td><td>'+data.DrugCodeCat[i].split('&&')[7].trim()+'</td><td>'+data.DrugCodeCat[i].split('&&')[8].trim()+'</td><tr>');*/
			 if(data.DrugCodeCat[i].split('&&')[8].trim()!='' && data.DrugCodeCat[i].split('&&')[8].trim() != null)
			{
					var temp =data.DrugCodeCat[i].split('&&')[6].trim();
					var tmpDate=temp.split('-')[2]+'-'+temp.split('-')[1]+'-'+temp.split('-')[0];
					//alert("line 932 :"+tmpDate);
				if(data.DrugCodeCat[i].split('&&')[7].trim()!='' && data.DrugCodeCat[i].split('&&')[7].trim() !=null)
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim()+', <small>'+data.DrugCodeCat[i].split('&&')[2].trim()+', '+data.DrugCodeCat[i].split('&&')[4].trim()+', '+data.DrugCodeCat[i].split('&&')[7].trim().split('#')[0]+' Days,'+ tmpDate +' ('+data.DrugCodeCat[i].split('&&')[8].trim()+') </small></p></li>');
				else
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim()+', <small>'+data.DrugCodeCat[i].split('&&')[2].trim()+', '+data.DrugCodeCat[i].split('&&')[4].trim()+', '+data.DrugCodeCat[i].split('&&')[8].trim()+') </small></p></li>');
			}
			else
			{
				var temp =data.DrugCodeCat[i].split('&&')[6].trim();
				var tmpDate=temp.split('-')[2]+'-'+temp.split('-')[1]+'-'+temp.split('-')[0];
				//alert("line 942 :"+tmpDate);
				if(data.DrugCodeCat[i].split('&&')[7].trim()!='' && data.DrugCodeCat[i].split('&&')[7].trim() !=null)
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim()+', <small>'+data.DrugCodeCat[i].split('&&')[2].trim()+', '+data.DrugCodeCat[i].split('&&')[4].trim()+', '+data.DrugCodeCat[i].split('&&')[7].trim().split('#')[0]+' Days, '+ tmpDate +' </small></p></li>');
				else
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim()+', <small>'+data.DrugCodeCat[i].split('&&')[2].trim()+', '+data.DrugCodeCat[i].split('&&')[4].trim()+', </small></p></li>');
				}

			/* if(data.DrugCodeCat[i].split('&&')[8].trim()!='' && data.DrugCodeCat[i].split('&&')[8].trim() != null)
			{
				if(data.DrugCodeCat[i].split('&&')[7].trim()!='' && data.DrugCodeCat[i].split('&&')[7].trim() !=null)
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim().split('(')[0]+'/</p></li>');
				else
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim().split('(')[0]+'</p></li>');
			}
			else
			{
				if(data.DrugCodeCat[i].split('&&')[7].trim()!='' && data.DrugCodeCat[i].split('&&')[7].trim() !=null)
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim().split('(')[0]+'</p></li>');
				else
					$('.printPrescPage .printPrescTreatmentLst').append('<li><p>'+data.DrugCodeCat[i].split('&&')[0].trim().split('(')[0]+'/</p></li>');
				} */


			
		}
		
		if("strUmidNo" in data ){
			
			if(data.strUmidNo != ''){
				
				$('.printPrescPage .umidId').text(data.strUmidNo);
				}
			else{
				$('#strUmidtblID').css('display','none');
			}
			
		}else{
			$('#strUmidtblID').css('display','none');
		}
		
if("strDesignation" in data ){
			
			if(data.strDesignation != ''){
				
				$('.printPrescPage .strDesignationID').text(data.strDesignation);
				}
			else{
				$('#strDesignationID').css('display','none');
			}
			
		}	else{
			$('#strDesignationID').css('display','none');
		}
	
		
if("strStation" in data ){
	
	if(data.strStation != ''){
		
		$('.printPrescPage .strStationID').text(data.strStation);
		}
	else{
		$('#strStationID').css('display','none');
	}
	
}else{
	$('#strStationID').css('display','none');
}
		
		
		
		if("admissionadviceDeptName" in data ){
			if(data.admissionadviceDeptName != ''){
				
				$('.printPrescPage .admissionPatientDept').append('<p>Deparment :'+data.admissionadviceDeptName+'  , Ward :'+data.admissionadviceWardName+'  Notes :'+data.admissionadviceNotes+'</p>');
				
				}else{
					$( "#admissionDeptDivId" ).remove();
				}
			}else{
				$( "#admissionDeptDivId" ).remove();
			}
		
	if("strReferal" in data ){
	console.log('data.strReferal.lengthdata.strReferal.length'+data.strReferal.length)
	if(data.strReferal.length == 0){
		$( "#refferPatientDeptDivId" ).parent().find('br').remove();
		$( "#refferPatientDeptDivId" ).remove();
		}
	 for(var j = 0 ; j< data.strReferal.length ; j++){

		 var strrefertext = '';
		 if("strShowData" in data.strReferal[j]){
		 if(data.strReferal[j].strShowData != ''){
			 strrefertext =data.strReferal[j].strShowData ;
		 if(data.strReferal[j].strReffralReason !='')
			 strrefertext = strrefertext + '( ' +data.strReferal[j].strReffralReason +')' ;
		 if(data.strReferal[j].strreferralType !='0')
			 strrefertext = strrefertext + '[ ' +data.strReferal[j].strreferralTypeName +']' ;
			 
		/* if(data.strReferal[j].strReffralReason !='') */
			$('.printPrescPage .refferPatientDept').append('<li><p>'+strrefertext+ ' </li></p>');	 
		/* else if(data.strReferal[j].strreferralType != '0')
			$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext+ '['+data.strReferal[j].strreferralTypeName+']'+' </li></p>');
		else
			$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext +' </li></p>'); */
		 }else{
			 if(data.strReferal[j].strReffralDepttext != '')
				 strrefertext =data.strReferal[j].strReffralDepttext ;
			 if(data.strReferal[j].strReffralReason !='')
				 strrefertext = strrefertext + '( ' +data.strReferal[j].strReffralReason +')' ;
			 if(data.strReferal[j].strreferralType !='0')
				 strrefertext = strrefertext + '[ ' +data.strReferal[j].strreferralTypeName +']' ;
				 
			/* if(data.strReferal[j].strReffralReason !='') */
				$('.printPrescPage .refferPatientDept').append('<li><p>'+strrefertext+ ' </li></p>');	 
			/* else if(data.strReferal[j].strreferralType != '0')
				$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext+ '['+data.strReferal[j].strreferralTypeName+']'+' </li></p>');
			else
				$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext +' </li></p>'); */
			
				
			 }
		 }
		 }

		/* if(data.strReffralReason !='')
			$('.printPrescPage .refferPatientDept').append('<br>('+data.strReffralReason+')');	 */
			}else{
				$( "#refferPatientDeptDivId" ).parent().find('br').remove();
				$( "#refferPatientDeptDivId" ).remove();
				}
	
		if(data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitSos!='' && data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitSos!=null)
		$('.printPrescPage .followUp').append(data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitSos);
		if(data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDays!='' && data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDays!=null)
		$('.printPrescPage .followUp').append('<br>'+'After '+data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDays+' Days');
		if(data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDate!='' && data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDate!=null)
		$('.printPrescPage .followUp').append('<br>'+'On '+data.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDate);
		
		$('.printPrescPage .date').text(new Date().toDateString());
 
		
		});
	
	</script>
	
<!-- <div id="footer">CRNo. <font class="footerCrNo"></font></div> -->
<style type="text/css" media="print" id="printStyleId"></style>



  <input type="hidden" name="strHospitalAddres" value="${DoctorDeskFB.strHospitalAddres}"/>
<input type="hidden" name="strHospitalName" value="${DoctorDeskFB.strHospitalName}"/>
  
</body>
</html>