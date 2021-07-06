

$(document).ready(function(){
	
	$("#OpenTemplateModal").on('hide.bs.modal', function () {
		$('#OpenTemplateModalFrameId').remove();
	 });
});

function OpenTemplate(strTemplateTypeId)
{
	$('#OpenTemplateModalFrameId').remove();
	console.log('Open Template'+strTemplateTypeId.value);
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var strHospitalName = $('input[name=strHospitalName]').val();
	var strHospitalAddres = $('input[name=strHospitalAddres]').val();
	var strUSerName = $('input[name=strConsultantName]').val(); 
	
	var vitalJSON = { 
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3] ,
			 "episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			 "Hosp_Code": $('#patHospitalCodePrescriptionPanel').text() ,
			 "patGeneralDtl" :$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val() ,
			 "patconsultantname" : $('#patConsultantNamePrescriptionPanel').text() ,
			 "strHospitalAddres"	: strHospitalAddres  ,
			 "strHospitalName"		: strHospitalName,
			 "strUSerName"			: strUSerName
			 }
	
	
	  var data = JSON.stringify(vitalJSON); 
		console.log(data);
		
		
		if(localStorage.getItem("PatDtlsJsonData"))
			localStorage.removeItem("PatDtlsJsonData");
			localStorage.setItem("PatDtlsJsonData", JSON.stringify(vitalJSON));
	var templateypename=''		
	if((strTemplateTypeId.value).split('#')[0] == '49'){
		templateypename='Other Templates';
	}
	if((strTemplateTypeId.value).split('#')[0] == '48'){
		templateypename='Complaint Templates';
	}
	if((strTemplateTypeId.value).split('#')[0] == '46'){
		templateypename='History Templates';
	}
	if((strTemplateTypeId.value).split('#')[0] == '47'){
		templateypename='Examination Templates';
	}
					
	$("#OpenTemplateModal").find(".modal-header").html('<input type="text" placeholder="Search Template" class="form-control tmp-search">'+templateypename+'<button type="button" style="position:absolute;right:10px;" class="close" data-dismiss="modal">&times;</button> ');
	$('#OpenTemplateModal .modal-body').prepend('<iframe id="OpenTemplateModalFrameId" style="width:100%;height:80vh;" src="/HISDRDESK/new_opd/transaction/DoctorDeskAction.cnt?hmode=TEMPLATESAVE&strTemplatetype='+(strTemplateTypeId.value).split('#')[0]+'&strtype='+(strTemplateTypeId.value).split('#')[1]+'"></iframe>');
	$('#OpenTemplateModal').modal('show');
	$("#OpenTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
}

window.closePopUpIframe = function(){
	$('#OpenTemplateModal').modal('hide');$('#OpenTemplateModalFrameId').remove();
};
function saveHtmlData(templateid,id){
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
			  
	  		$('#OpenTemplateModal').modal('hide');
	$.ajax({url:'/HISDRDESK/services/restful/Template/Save/',type:'POST',data:{JsonData:data},success:function(result)
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


/*function printDiv(varid)
{
			var $elem = $( "#"+varid ).data( "arr", [ 1 ] ),$clone = $elem.clone( true ).data( "arr", $.extend( [], $elem.data( "arr" ) ) );
			$("#printSection").empty();
			$("#printSection").append( $elem);
			window.print();	
			var $elem1 = $( "#printSection #"+varid ).data( "arr1", [ 1 ] ),$clone1 = $elem1.clone( true ).data( "arr1", $.extend( [], $elem1.data( "arr1" ) ) );
			$("#collapse"+varid).find(".formSection .row").append($elem1);
			
}*/


/*function printDiv(varid) { 
	console.log(varid);
    var divContents = document.getElementById(varid).innerHTML; 
    var a = window.open('', '', 'height=500, width=800'); 
    a.document.write('<html>'); 
    a.document.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>'); 
    a.document.write('<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css">');
    a.document.write('<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">');
    a.document.write('<link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/opdtemplate.css">');
    a.document.write('<link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/fb.css">');
    a.document.write('<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>'); 
    a.document.write('<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>'); 
    a.document.write('<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/js/all.min.js"></script>');
    a.document.write('<script src="/HISDRDESK/new_opd/js/fb.js"></script>');
    a.document.write('<body  onload="window.print()" >'); 
    a.document.write(divContents); 
    a.document.write('</body></html>'); 
    a.document.close(); 
  //  a.print(); 
} */

function togBtn(id,obj){
	
	$(".btnCol").css("display","none");
	$(".printCol").css("display","none");
	$(".tmp-container .card-header").css("background","linear-gradient(to bottom right,#b1dbff8a,#5f9ea000)");
	
	
	if(!$("#collapse"+id).hasClass("show")){
		$("#btn"+id).css("display","");
		$($("#collapse"+id)).closest(".card-header")
		$(obj).css("background","#5f9ea05e");
		//$("#print"+id).css("display","");
	}else{
		$("#btn"+id).css("display","none");
		//$("#print"+id).css("display","none");
		$(obj).css("background","linear-gradient(to bottom right,#b1dbff8a,#5f9ea000)");
	}
	$(".collapse").collapse("hide");
	
	

	$("#collapse"+id).collapse("show");

}



function printDiv(varid ,tempName){
	
	
	let myJSON='';
	 if(localStorage.getItem("PatDtlsJsonData"))
	 myJSON = localStorage.getItem("PatDtlsJsonData");
		myJSON = JSON.parse(myJSON);
		myJSON.tempName=tempName;
		
		console.log(myJSON);
		
		
		
		let printData	="";
		let  imgs = {}; 
		let images=[];
		//let sizes=[];
		l=0;
		let align="valign='top'";
		
		printData+="<table width='100%' >";
		
		console.clear();
		let large=0; //row with largest column calculated below	
		$("#"+varid +" #subContDivLeft .contDragCont").each((i,a)=>{			
					if(large<$(a).find(".contDrag").length)
						large=$(a).find(".contDrag").length;
						
			});


			
			$("#"+varid +" #subContDivLeft .contDragCont").each((i,a)=>{
			//jsonData[i]={};
		
				
				console.log("row no:::::"+(i+1));
				
			printData+="<tr>";
			
			//length of no of columns in current row
			let curnt=$(a).find(".contDrag").length;
			
			console.log(curnt +"  no of columns of row::"+(i+1))
			

			
			if(curnt!=large){
				
				console.log("normal row")
				let wid;
				printData+="<td "+align+" colspan='"+large+"' style='font-family:Arial;font-size:medium'><table width='100%'><tr>";
				$(a).find(".contDrag").each((j,b)=>{
				wid=Math.floor(100/curnt);
				
			
			console.log("column::"+$(b).prop("id"));
			

				if($(b).find(".mainElDiv").length>0)
				{
					
					console.log("here2");
					
					//$(b).find('.mainElDiv').next().find('img').prop('src')
							if($(b).find(".mainElDiv .labelElDiv").length>0){
							let vall="";
								if($(b).find(".mainElDiv .labelElDiv").css("display")!="none"){
								 vall="<td "+align+"><table><tr>";
									//if($(b).find(".mainElDiv .labelElDiv label font").css("display")=="none")
										vall+="<td ><b>"+$(b).find(".mainElDiv .labelElDiv label").text()+"</b></td>";
									//else
									//	vall+="<td ><b>"+$(b).find(".mainElDiv .labelElDiv label").text().split("*")[0]+"<font color='red'>* </font></b></td>";	
									vall+="<td "+align+">";
									if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Unit_Based"){
										vall+=$(b).find(".mainElDiv .addedEl input").val() +" "+	
									$(b).find(".mainElDiv .addedEl .input-group-append button").text();
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Autocomplete"){
										vall+=$(b).find(".autoCompHelper").val().split("_attach_")[1];	
									}
									  else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="MultiAc"){
											
											
											$(b).find(".addedElHelper .MultiHelper input").each((j,c)=>{
												if(j>0){
													if(j==1)
														vall+=c.value.split("_attach_")[1];
													else
														vall+=","+c.value.split("_attach_")[1];
												}
											});
									
										}
									  else if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Image" ){
										  
										  if(!($(b).find('.mainElDiv').next().find('img').prop('src').startsWith("http:"))){
												vall+="<br><img  height='"+(250/parseInt(curnt))+"' width='"+(515/parseInt(curnt)-8)+"' src='imgTmp_"+l+"'>"
														images[l]=$(b).find('.mainElDiv').next().find('img').prop('src');
															l++;
										  }
										  console.log("image");
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Select"){
										if($(b).find(".mainElDiv .addedEl option:selected").text() !="Select Value" && $(b).find(".mainElDiv .addedEl option:selected").val() !="0" && $(b).find(".mainElDiv .addedEl option:selected").val() !="-1")
												vall+=$(b).find(".mainElDiv .addedEl option:selected").text()+" ";
										else
											vall+="---";
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="DependentField"){
										
										if($(b).find(".mainElDiv .addedEl option:selected").text() !="Select Value" && $(b).find(".mainElDiv .addedEl option:selected").val()!="0" && $(b).find(".mainElDiv .addedEl option:selected").val() !="-1")
											vall+=$(b).find(".mainElDiv .addedEl option:selected").text()+" ";
										else
											vall+="---";
										
									}
									else
										vall+=$(b).find(".mainElDiv .addedEl").val();
									vall+="</td>";
								vall+="</tr></table></td>";
								
								
								}else{
										vall+="<td "+align+" width='"+wid+"%'>";
									if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Image" ){
										if(!($(b).find('.mainElDiv').next().find('img').prop('src').startsWith("http:"))){
											vall+="<img  height='"+(250/parseInt(curnt))+"' width='"+(515/parseInt(curnt)-8)+"' src='imgTmp_"+l+"'>"
									
											images[l]=$(b).find('.mainElDiv').next().find('img').prop('src');
										//sizes[l]=(250/parseInt(curnt))+"-"+(515/parseInt(curnt)-8);
										//sizes[l]=+$(b).find('.mainElDiv').next().find('img')[0].height+"-"+$(b).find('.mainElDiv').next().find('img')[0].width;
										l++;
										}
									}
									 
									else{
										vall+=$(b).find(".mainElDiv .addedEl").val();
									}	
										vall+="</td>";
								}
							//vall+="</tr></table>";
							printData+=vall;
						}else{
								printData+="<td "+align+" width='"+(Math.floor(large/curnt)*100)+"%'>";
								if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Label"){
										printData+=$(b).find(".mainElDiv .addedElDiv").html();
									}
								printData+="</td>";
						}
						
						
						
						//jsonData[i].cell[j].data=vall;
						
				}else{
					
					console.log("without main element div");
				if($(b).find(".addedElGrp").length>0){
					console.log("el group data");
														printData+="<td "+align+">";

					if( $(b).find(".addedElGrp").prop("id").split("-")[0]=="radio"){
						if($(b).find(".addedElGrp").find(".mainLabel").length>0)
						{
							printData+=chkGrpLabel(b)
						}
						
						printData+=$(b).find(".addedElGrp").find("input[name='optradio-"+$(b).find(".addedElGrp").prop("id").split("radio-")[1]+"' ]:checked").parent('label').text();
											}
					if( $(b).find(".addedElGrp").prop("id").split("-")[0]=="checkbox"){	
						
						if($(b).find(".addedElGrp").find(".mainLabel").length>0)
							{
								printData+=chkGrpLabel(b)
							}
	
							
							let chkval=$(b).find(".addedElGrp").find("input[name='optcheck-"+$(b).find(".addedElGrp").prop("id").split("checkbox-")[1]+"' ]:checked");
							let mapchkVal="";
							$.map( chkval, function( val, i ) {
								if(i==0)
									mapchkVal+=$(val).parent('label').text();
								else
									mapchkVal+=","+$(val).parent('label').text();
							 
							});
							printData+=mapchkVal;
	
											}
											printData+="</td>";
					}else{
						console.log("Blank Column");
						printData+="<td "+align+" width='"+wid+"%'></td>";
					}
															

				}
		
					
			});
				printData+="</tr></table></td>";
			
			}else{
			
				console.log("row with largest columns");

			$(a).find(".contDrag").each((j,b)=>{
			
				console.log("column::"+$(b).prop("id"));
			
			
			printData+="<td "+align+" width='"+Math.floor(100/curnt)+"%' style='font-family:Arial;font-size:medium'>";
			
			if($(b).find("div[class^='Dep-']").css("display") != "none")
			{
				
				
				if($(b).find(".mainElDiv").length>0)
				{
					
					
					//$(b).find('.mainElDiv').next().find('img').prop('src')
							if($(b).find(".mainElDiv .labelElDiv").length>0){
							let vall="";
							console.log("with label");

								if($(b).find(".mainElDiv .labelElDiv").css("display")!="none"){
									console.log("with shown label");
								 vall="<table><tr>";
								//	if($(b).find(".mainElDiv .labelElDiv label font").css("display")=="none")
										vall+="<td "+align+"><b>"+$(b).find(".mainElDiv .labelElDiv label").text()+"</b></td>";
									//else
									//	vall+="<td "+align+">"+$(b).find(".mainElDiv .labelElDiv label").text().split("*")[0]+"<font color='red'>* </font></td>";
									
									
									
									
									vall+="<td "+align+">";
									if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Unit_Based"){
									console.log($(b).find(".mainElDiv .addedEl .input-group-append button"));
										vall+=$(b).find(".mainElDiv .addedEl input").val() +" "+$(b).find(".mainElDiv .addedEl .input-group-append button").text();
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Autocomplete"){
										vall+=$(b).find(".autoCompHelper").val().split("_attach_")[1];
								
									}
									  else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="MultiAc"){
											
											
											$(b).find(".addedElHelper .MultiHelper input").each((j,c)=>{
												if(j>0){
													if(j==1)
														vall+=c.value.split("_attach_")[1];
													else
														vall+=","+c.value.split("_attach_")[1];
												}
											});
									
										}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Select"){
										
										if($(b).find(".mainElDiv .addedEl option:selected").text() !="Select Value" && $(b).find(".mainElDiv .addedEl option:selected").val()!="0" && $(b).find(".mainElDiv .addedEl option:selected").val() !="-1")
											vall+=$(b).find(".mainElDiv .addedEl option:selected").text()+" ";
										else
											vall+="---";
									}
									
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="DependentField"){
										
										if($(b).find(".mainElDiv .addedEl option:selected").text() !="Select Value" && $(b).find(".mainElDiv .addedEl option:selected").val()!="0" && $(b).find(".mainElDiv .addedEl option:selected").val() !="-1")
											vall+=$(b).find(".mainElDiv .addedEl option:selected").text()+" ";
										else
											vall+="---";
									}								
									else
										vall+=$(b).find(".mainElDiv .addedEl").val();
								vall+="</td></tr></table>";								
								}else{
									console.log("with hidden label");
									if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Image" ){
										if(!($(b).find('.mainElDiv').next().find('img').prop('src').startsWith("http:"))){
											vall+="<img  height='"+(250/parseInt(curnt))+"' width='"+(515/parseInt(curnt)-8)+"' src='imgTmp_"+l+"'>"

										images[l]=$(b).find('.mainElDiv').next().find('img').prop('src');
										//sizes[l]=+$(b).find('.mainElDiv').next().find('img')[0].height+"-"+$(b).find('.mainElDiv').next().find('img')[0].width;
										//sizes[l]=$(b).find('.mainElDiv').next().find('img')[0].height+"-"+(515/parseInt(curnt)-8);
										console.log($(b).find('.mainElDiv').next().find('img').prop('src'));
									l++;
										}
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Select"){
										
										if($(b).find(".mainElDiv .addedEl option:selected").text() !="Select Value" && $(b).find(".mainElDiv .addedEl option:selected").val()!="0" && $(b).find(".mainElDiv .addedEl option:selected").val() !="-1")
											vall+=$(b).find(".mainElDiv .addedEl option:selected").text()+" ";
										else
											vall+="---";
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Unit_Based"){
										vall+=$(b).find(".mainElDiv .addedEl input").val() +" "+	
									$(b).find(".mainElDiv .addedEl .input-group-append button").text();
									}
									else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="Autocomplete"){
										vall+=$(b).find(".autoCompHelper").val().split("_attach_")[1];
								
									}
								  else if($(b).find(".mainElDiv .addedEl").prop("id").split("-")[0]=="MultiAc"){
										
										
										$(b).find(".addedElHelper .MultiHelper input").each((j,c)=>{
											if(j>0){
												if(j==1)
													vall+=c.value.split("_attach_")[1];
												else
													vall+=","+c.value.split("_attach_")[1];
											}
										});
								
									}
									 
									else{
										vall+=$(b).find(".mainElDiv .addedEl").val();
									}	
								
								}
							//vall+="</tr></table>";
							printData+=vall;
						}else{
								if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Label"){
										printData+=$(b).find(".mainElDiv .addedElDiv").html();
									}
								if($(b).find(".addedElGrp").length>0)
								{
									printData+="<td "+align+">";

									if( $(b).find(".addedElGrp").prop("id").split("-")[0]=="radio"){
								printData+=$(b).find(".addedElGrp").find("input[name='optradio-"+$(b).find(".addedElGrp").prop("id").split("radio-")[1]+"' ]:checked").parent('label').text();
													}
													printData+="</td>";
								}
								if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Header"){
									printData+=$(b).find(".mainElDiv .addedElDiv").html();
								}
								if( $(b).find(".mainElDiv").find(".addedEl").prop("id").split("-")[0]=="Paragraph"){
									printData+=$(b).find(".mainElDiv .addedElDiv").html();
								}
						
						}
						
						
						
						
				}else{
					
					console.log("without main element div");
					if($(b).find(".addedElGrp").length>0){
						console.log("el group data");
														//	printData+="<td>";

						if( $(b).find(".addedElGrp").prop("id").split("-")[0]=="radio"){
							if($(b).find(".addedElGrp").find(".mainLabel").length>0)
							{
								printData+=chkGrpLabel(b)
							}
							
							printData+=$(b).find(".addedElGrp").find("input[name='optradio-"+$(b).find(".addedElGrp").prop("id").split("radio-")[1]+"' ]:checked").parent('label').text();
												}
						if( $(b).find(".addedElGrp").prop("id").split("-")[0]=="checkbox"){	
							
							if($(b).find(".addedElGrp").find(".mainLabel").length>0)
								{
									printData+=chkGrpLabel(b)
								}
							
								let chkval=$(b).find(".addedElGrp").find("input[name='optcheck-"+$(b).find(".addedElGrp").prop("id").split("checkbox-")[1]+"' ]:checked");  ///.parent('label').text();
								let mapchkVal="";
								$.map( chkval, function( val, i ) {
									if(i==0)
										mapchkVal+=$(val).parent('label').text();
									else
										mapchkVal+=","+$(val).parent('label').text();
								 
								});
								printData+=mapchkVal;
												}
												//printData+="</td>";
						}else{
							console.log("Blank Column");
							//printData+="<td width='"+wid+"%'></td>";
						}
							

						//printData+="<table><tr><td></td></tr></table>";
				}
				
				
			}
						
				printData+="</td>";	
			});
			
			
			}
			
			
			
			

				printData+="</tr>";
				
				//	console.log("curnt:::::::::"+curnt);
				//console.log(sizes);
			});
			
			printData+="</table>";
			
			imgs.images=images;
			
			console.log(imgs);

			/*if(images.length<1)
				images[0]="not require"*/
			
			console.log(printData);

			

		$.ajax({url:'/HISDRDESK/services/restful/Template/printTemplate/',type:'POST',data:{printData:printData ,myJSON : JSON.stringify(myJSON) , varid : varid,imgs:JSON.stringify(imgs)},success:function(result)
	    	{
			console.log("----------------------");

			base64toPDF(result.base64);

			
		
			
	    	}
		});
}



function base64toPDF(data) {
    var bufferArray = base64ToArrayBuffer(data);
    var blobStore = new Blob([bufferArray], { type: "application/pdf" });
    swal.close();
    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
        window.navigator.msSaveOrOpenBlob(blobStore);
        return;
    }

    var data = window.URL.createObjectURL(blobStore);
    window.open(data);
   /* var link = document.createElement('a');
    document.body.appendChild(link);
    link.href = data;
    link.download = "file.pdf";
    link.click();
    window.URL.revokeObjectURL(data);
    link.remove();*/
}

function base64ToArrayBuffer(data) {
    var bString = window.atob(data);
    var bLength = bString.length;
    var bytes = new Uint8Array(bLength);
    for (var i = 0; i < bLength; i++) {
        var ascii = bString.charCodeAt(i);
        bytes[i] = ascii;
    }
    return bytes;
};

function chkGrpLabel(obj){	return  "<b>"+$(obj).find(".addedElGrp .mainLabel").text()+" </b> ";	}
