var globalPatPresc = ''; 
$(document).ready(function(){
	
	
	$('.prescPrintBtn').click(function(){
		
		var ResonOfVisitJsonArray =[];
		var j;
		var BoormarkNameVal='' ;
		$('input[name=visitReason]:checked').each(function()
						{
							var jsonString=JSON.parse($(this).parent().find('i').text());
							console.log(jsonString.VisitReasonName);
							if(BoormarkNameVal == '')
							BoormarkNameVal=jsonString.VisitReasonName
							else	
							BoormarkNameVal = BoormarkNameVal +' + '+jsonString.VisitReasonName;
						});
		$('input[name=diagnosisAdded]:checked').each(function()
				{
					var jsonString=JSON.parse($(this).parent().find('i').text());
					//console.log(jsonString.VisitReasonName);
					if(BoormarkNameVal == '')
						BoormarkNameVal=jsonString.DiagnosisName
					else
					BoormarkNameVal = BoormarkNameVal +' + '+jsonString.DiagnosisName;
			
					/*console.log('diagnosisAdded::>>> '+$(this).val());
					Diagnosis[j]=$(this).val();    //+'^'+$(this).parent().text()+'#'+$(this).attr('diagnosisName');
					DiagnosisJsonArray[j]=JSON.parse($(this).parent().find('i').text());
					j++; */
				});
		
		$('#PresCriptionBookmarkNameId').val(BoormarkNameVal);
		console.log(ResonOfVisitJsonArray);
		
		if($('#bookmarkmodal').is(':visible') == true){
			console.log(':::::::::::::');
			$('#bookmarkmodal').modal('show');
		}else{
			$('#bookmarkmodal').modal('show');
		}
	});
	
	
	$('.rxprofilebtn').click(function(){
		if($('#PresCriptionModal').is(':visible') == true){
			console.log(':::::::::::::');
			$('#PresCriptionModal').modal('show');
		}else{
			$('#PresCriptionModal').modal('show');
		}
	});
	
	
	$(".cleaarAll1").on("click",function(){
		$("#drugAdviceListTable tbody").empty();
	});
	
	
	
	
	
	$('.checkboxcls').click(function(){
	    $('.checkboxcls').each(function(){
	        $(this).prop('checked', false); 
	    }); 
	    $(this).prop('checked', true);
	});

	 $('.reasonOfVisitCleanBtn').click(function(){
		 $(this).parent().parent().find('#txt-snomed-ct-search_VR').val('');
		 $(this).parent().parent().find('#txt-snomed-ct-search_VR').attr('reasonOfVisitCode','');
	 });
	 
	 $('.diagnosisCleanBtn').click(function(){
		 $(this).parent().parent().find('#txt-snomed-ct-search_VR2').val('');
		 $(this).parent().parent().find('#txt-snomed-ct-search_VR2').attr('diagnosisCode','');
	 });

 	var totalTabMaxWidth = $('.navDeskTabList').width(); 
 	var totalTabActualWidth = 0;
 	var flag = 0;
 	$('.navDeskTabList').find('li').each(function(index){
 		            totalTabActualWidth += $(this).width();
 		           if(totalTabActualWidth>(totalTabMaxWidth - 50))
 		           {	
 		           		$(this).hide();
 		           		$(this).addClass('extraTags');
 		           		flag = 1;
 		           }
 	});
 	if(flag == 1)
 	{
 		$('.navDeskTabList').append('<li class="moreTagsView"><a style="text-decoration:none;">...more</a></li>');
 	}
 	$('.moreTagsView').click(function(){
 		$('.extraTags').toggle(); 
 	}); 

 	$('.addMacroToProgressNoteBtn').click(function(e){
 		
 		//var test=this.id;
 		var id = $(this).attr('id');
 		console.log('this.id::::'+id);
 		console.log('12::::::::::::::::'+$('#macrisHiddenId').val());
 		var txt = $('input[name=macroNoteVal]:checked').val();
 		console.log(txt);
 		//alert(txt);
 		if(txt != null)
 		{	
 			//$(this).parent().parent().val(txt);
 			var temp=($('#macrisHiddenId').val()).replace("Div", "");
 			var temp1=($('#macrisHiddenIdval').val()).replace("Div", "");
 			console.log('temp       '+temp);
 			console.log('temp1       '+temp1);
 			$('#'+temp).val(txt);
 			//$('textarea[name=progressNote]').val(txt);
 			$('#'+temp1).modal('hide');
 			$('#macrisHiddenId').val('');
 			$('#macrisHiddenIdval').val('');
 		}
 	}); 

	 	
	$('.progressNoteMacroBtn').click(function(e){
	 		
			//$('#progressNoteMacroModal').modal('show');
	 		var test=this.id;
	 		$('#macrisHiddenId').val(test);
	 		$('#macrisHiddenIdval').val(this.value);
	 		
	 		console.log(this.value+'::::::::::::::::'+test);
	 		/*console.log('::::::::::::::::'+test)
	 		var txt = $('input[name=macroNoteVal]:checked').val();
	 		//alert(txt);
	 		if(txt != null)
	 		{	
	 			$(this).parent().parent().val(txt);
	 			$('textarea[name=progressNote]').val(txt);
	 			$('#progressNoteMacroModal').modal('hide');
	 		}*/
	 	}); 
	

 	$('.clearLnk').on('click',function(e){
/* 		$(this).parent().parent().parent().find('.checkedInput:checked').parent().parent().remove();*/
		if($(this).parent().find('.checkedInput').is(':checked'))
		$(this).parent().find('.checkedInput:checked').prop('checked',false);
		else
		$(this).parent().find('.checkedInput').prop('checked',true);
 	});
 	$('.clearLnkDrug').on('click',function(e){  
		$(this).parent().parent().parent().find('.checkedInput:checked').prop('checked',false);
 	});

 	$('.reasonOfVisitAdd').click(function()
 	{
 		//console.log('Index ZVal()'+$(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim().indexOf(';'));
 		if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim().indexOf(';')>='0')
 		{ 		//console.log('true::::::::');	
		 		if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim()!='')
		 		{  
		 			var reasonOfVisitVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().split(';');
			 	   var reasonOfVisitCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode').split(';');
			 	   var visitReasoncount=0;
			 	   for(var i=0;i<(reasonOfVisitVAl.length-1);i++)
		 			{
		 			  var tmp = 0; 
						$('.reasonOfVisitAdded').find('label .text').each(function(index){ 
							if($(this).text().trim().toUpperCase()===reasonOfVisitVAl[i].trim().toUpperCase()) 
							{	tmp = 1; 
								return false;  }
						});
						if(tmp==1)
						{
		
							swal(reasonOfVisitVAl[i].trim()+", Already Added!!");
							continue;
						}
						visitReasoncount ++;
						var visitReasonId=reasonOfVisitVAl[i]+'^'+$("#chiefComplaintSiteId").val()+'^'+$("#chiefComplaintNoOfDaysId").val()+'^'+$("#chiefComplaintDurationId").val()+'^'+$("#reasonofvisitRemarksId").val();
						console.log('visitReasonId'+$('#chiefComplaintSiteId option:selected').text());
						 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left:5px"><label><input class="checkedInput" type="checkbox" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked> '+reasonOfVisitVAl[i]+'</label></a>');
						var chiefComplaintJson ={
							"IsExternalVisit"	:		"1" ,
							"VisitReasonName" : 		 reasonOfVisitVAl[i],
							"VisitReasonCode" :			 reasonOfVisitCode[i],
							"VisitReasonSideCode" : 		 $("#chiefComplaintSiteId").val() ,
							"VisitReasonSideName" :			$('#chiefComplaintSiteId option:selected').text(),
							"VisitReasonNoOfDays" : 	 $("#chiefComplaintNoOfDaysId").val() ,
							"VisitComplaintDurationCode" : $("#chiefComplaintDurationId").val(),
							"VisitComplaintDurationName" : $('#chiefComplaintDurationId option:selected').text(),
							"VisitReasonRemarks"		: $("#reasonofvisitRemarksId").val()
						};
						
						console.log(JSON.stringify(chiefComplaintJson));
						
						var temp='';
						console.log($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 );
						
						if($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 ) 
						 temp=$('#chiefComplaintSiteId option:selected').text() + '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ; 
						else if($("#chiefComplaintSiteId").val() != 0)
							 temp=$('#chiefComplaintSiteId option:selected').text() ;
						else 
							 temp= '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ;	
						
						
						
						if($("#reasonofvisitRemarksId").val() != ''){
							temp = temp + $("#reasonofvisitRemarksId").val() ;
		 			}
						
						console.log('temp temp temp '+temp);
						$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs reasonOfVisitAdd " data-toggle="tooltip" title='+ temp +'>'+
					    	 		'<input type="checkbox" class="checkedInput" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked="">  '+
					    	 		'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
					    	 		'<span class="text">'+reasonOfVisitVAl[i]+' </span>'+
					    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
					    	 		'</button></label>');
		 			   }
					 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val('');
			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode','');
			 		 $(this).parent().parent().find('input[name=chiefComplaintNoOfDays]').val('');
			 		 $(this).parent().parent().find('input[name=reasonofvisitRemarks]').val('');
			 		$(this).parent().parent().find('textarea[name=reasonofvisitRemarks]').val('');
			 		$(this).parent().parent().find('select[name=chiefComplaintSite] ').val('0');
			 		$(this).parent().parent().find('select[name=chiefComplaintDuration] ').val('1');
			 		
		 		 }
		 		else
		 			 swal('Please Select atleast one reason');
 		}
 		else if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim() != ''){
            var generalComplaintVal = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val() ;  //$(this).parent().parent().find('input[name=generalComplaint]').val();
            if(generalComplaintVal.trim()!='')
             {  
               var tmp = 0; 
               $('.reasonOfVisitAdded').find('label .text').each(function(index){ 
                 if($(this).text().split("*")[0].trim().toUpperCase()===generalComplaintVal.trim().toUpperCase()) 
                 { tmp = 1; 
                   return false;  }
               });
               if(tmp==1)
               {
                 swal("Already Added!!");
                 $('#generalComplaintId').val('');
                 return false;
               }
               else
               {
                 var chiefComplaintNoOfDays=$(this).parent().parent().find('input[name="chiefComplaintNoOfDays"]').val();
                 var siteId=$(this).parent().parent().find('select[name="chiefComplaintSite"] option:selected').val();
                 var chiefComplaintDuration=$(this).parent().parent().find('select[name="chiefComplaintDuration"] option:selected').val();

                 if(siteId != '0' || (chiefComplaintDuration != '0' && chiefComplaintNoOfDays !='')){
                     if(siteId != '0' && (chiefComplaintDuration != '0' && chiefComplaintNoOfDays !=''))
                       var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^'+siteId+'^'+chiefComplaintNoOfDays+'^'+chiefComplaintDuration+'^'+$("#reasonofvisitRemarksId").val();
                     else if(siteId != '0')
                       var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^'+siteId+'^0^0'+'^'+$("#reasonofvisitRemarksId").val(); 
                     else if(chiefComplaintDuration != '0' && chiefComplaintNoOfDays !='')
                       var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^0^'+chiefComplaintNoOfDays+'^'+chiefComplaintDuration+'^'+$("#reasonofvisitRemarksId").val();
                     else{
                       swal('Please select all fields');
                       return false;
                     }
                 }
               /*  else if(chiefComplaintDuration != '0' &&  chiefComplaintNoOfDays == ''){
                   swal('Please enter no of days.');
                   return false;
                 }*/
                /* else if(chiefComplaintDuration == '0' &&  chiefComplaintNoOfDays != ''){
                   swal('Please select duration.');
                   return false;
                 }*/
                 else{
                   var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^0^0^0'+'^'+$("#reasonofvisitRemarksId").val();  
                 }
                 
                 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="visitReason" value="'+generalComplaintChkBoxVal+'"  checked> '+generalComplaintVal+'(ext)</label></a>');
                 var temp='';
					console.log($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 );
					
					if($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 ) 
					 temp=$('#chiefComplaintSiteId option:selected').text() + '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ; 
					else if($("#chiefComplaintSiteId").val() != 0)
						 temp=$('#chiefComplaintSiteId option:selected').text() ;
					else 
						 temp= '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ;	
					
					
					if($("#reasonofvisitRemarksId").val() != '')
						temp =temp + $("#reasonofvisitRemarksId").val() ;
					
					var chiefComplaintJson ={
							"IsExternalVisit"	:		"1" ,
							"VisitReasonName" : 		 generalComplaintVal,
							"VisitReasonCode" :			 "0",
							"VisitReasonSideCode" : 	 $("#chiefComplaintSiteId").val() ,
							"VisitReasonSideName" :		$('#chiefComplaintSiteId option:selected').text(),
							"VisitReasonNoOfDays" : 	 $("#chiefComplaintNoOfDaysId").val() ,
							"VisitComplaintDurationCode" : $("#chiefComplaintDurationId").val(),
							"VisitComplaintDurationName" : $('#chiefComplaintDurationId option:selected').text(),
							"VisitReasonRemarks"		: $("#reasonofvisitRemarksId").val()
						};
						
						console.log(JSON.stringify(chiefComplaintJson));
					
                 $(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs"  data-toggle="tooltip" title='+ temp +'>'+
			    	 		'<input type="checkbox" class="checkedInput" name="visitReason" value="'+generalComplaintChkBoxVal+'" checked="">  '+
			    	 		'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
			    	 		'<span class="text">'+generalComplaintVal+'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');

               }
              
              //$(this).parent().parent().find('input[name=generalComplaint]').val('');
               $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val('');
		 	  $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode','');
              $(this).parent().parent().find('input[name="chiefComplaintNoOfDays"]').val('');
              $(this).parent().parent().find('input[name=reasonofvisitRemarks]').val('');
              $(this).parent().parent().find('select[name="chiefComplaintSite"]').val('0');
              $(this).parent().parent().find('select[name="chiefComplaintDuration"] ').val('1');
             }
             else{
               swal('Please enter other general complaint to be added');
             }
     }
 		else
 			 {swal('Please Select atleast one reason');}
 		$('[data-toggle="tooltip"]').tooltip();
 	}); 

 	$("input[name=txt-snomed-ct-search_VR2]").on('keypress', function (e) {
 		console.log('1');
 		if (!e) e = window.event;
 	    var keyCode = e.keyCode || e.which;
 	    if (keyCode == '13'){
 	    	console.log('2');	

 	 		if($('input[name=diagnosisDiseaseReference]').is(':checked'))
 	 		{
 	 			if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim().indexOf(';')>='0')
 	 	 		{
 	 				if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim()!='')
 	 		 		{  var diagnosisVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().split(';');
 	 			 	   var diagnosisCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode').split(';');
 	 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
 	 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
 	 			 	   console.log("diagnosisVAl -->> "+diagnosisVAl);
 	 			 	   console.log("diagnosisCode --->> "+diagnosisCode);
 	 			 	  console.log('diagnosisType ::: ::::: :::: '+diagnosisTypeCode+diagnosisTypeName);
 	 		 		   for(var i=0;i<(diagnosisVAl.length-1);i++)
 	 		 		   	{
 	 		 			  var tmp = 0; 
 	 		 				$('.diagnosisAdded').find('label .text').each(function(index){ 
 	 		 					if($(this).text().trim().toUpperCase()===diagnosisVAl[i].trim().toUpperCase()) 
 	 		 					{	tmp = 1; 
 	 		 						return false;  }
 	 		 				});
 	 		 				if(tmp==1)
 	 						{ 
 	 							swal(diagnosisVAl[i].trim()+", Already Added!!");
 	 							continue;
 	 						}
 	 		 				var strDiagnosisId=diagnosisCode[i]+'#'+diagnosisTypeCode+'#1^'+diagnosisVAl[i]+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteId").val()+'#'+$('input[name=diagnosisNoOfDays]').val()+'#'+$("#diagnosisDurationId").val()+'#'+$("#diagnosisRemarksId").val();
 	 				 		//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+diagnosisVAl[i]+'</label></a>');
 	 				 		
 	 		 				var temp=''; 
 	 		 				if($("#diagnosisSiteId").val()!= 0 && diagnosisTypeCode !=0)
 	 		 					temp=diagnosisTypeName +'['+ $("#diagnosisSiteId option:selected").text() +']';
 	 		 				else if($("#diagnosisSiteId").val()!= 0)
 	 		 					temp=+'['+ $("#diagnosisSiteId option:selected").text() +']';
 	 		 				else if(diagnosisTypeCode !=0 )
 	 		 					temp=diagnosisTypeName ;
 	 		 				
 	 		 				if($("#diagnosisRemarksId").val() !=''){
 	 		 					temp= temp+ $("#diagnosisRemarksId").val() ;
 	 		 				}
 	 		 				var DiagnosisJson ={
 	 		 						"IsSnomed"				:			"1" ,
 	 								"DiagnosisName" 		: 		 	diagnosisVAl[i],
 	 								"DiagnosisCode" 		:			diagnosisCode[i],
 	 								"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
 	 								"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
 	 								"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
 	 								"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
 	 								"DiagnosisRemarks"	:			$("#diagnosisRemarksId").val()
 	 							};
 	 							
 	 		 				console.log(JSON.stringify(DiagnosisJson));
 	 		 				
 	 				 		$('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs" title='+temp+' data-toggle="tooltip" >'+
 	 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
 	 				    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
 	 				    	 		'<span class="text">'+diagnosisVAl[i]+' </span>'+
 	 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 	 				    	 		'</button></label>');
 	 		 		   	}
 	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val('');
 	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode','');
 	 			 		$(this).parent().parent().find('select[name=diagnosisSite] ').val('0');
 	 			 		$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
 	 			 		$(this).parent().parent().find('textarea[name=diagnosisRemarks]').val('');
 	 			 		 
 	 		 		 }
 	 				else
 	 		 			 swal('Please Select atleast one reason');
 	 	 		}
 		 		else
 		 			 swal('Please Select atleast one reason');
 	 		}
 	 		else{
 	 			 var investigationVAl = $(this).parent().parent().find('input[name=icdCodeInput]').val();  
 	 		 	 var isValid = 0;
 	 		 	 var invObj = localStorage.getItem('icdJsonObj'); 
 	 	 		 invObj = JSON.parse(invObj.toString()); 
 	 	 		 for(var v=0; v<invObj.length;v++)
 	 	 		 { 
 	 	 			if (invObj[v].icdCode.toUpperCase() == investigationVAl.toUpperCase()) {
 	 	 				isValid=1;
 	 			        break;
 	 			    } 
 	 	 		  } 
 	 			if($(this).parent().parent().find('input[name=diseaseInput]').val().trim()!='')
 		 		{  
 	 				var icdCode = $(this).parent().parent().find('input[name=icdCodeInput]').val();
 	 				var icdCodeVal = $(this).parent().parent().find('input[name=diseaseInput]').val(); 
 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
 			 	   console.log('icdCodeVal ::: ::::: :::: '+icdCode+icdCodeVal); 
 			 	   
 			 	   var tmp = 0; 
 					$('.diagnosisAdded').find('label .text').each(function(index){ 
 						if($(this).text().trim().toUpperCase()===icdCodeVal.trim().toUpperCase()) 
 						{	tmp = 1; 
 							return false;  }
 					});
 					if(tmp==1)
 					{ 
 						swal(icdCodeVal.trim()+", Already Added!!"); 
 					}
 					else
 					{					  
 						var strDiagnosisId=icdCode+'#'+diagnosisTypeCode+'#0^'+icdCodeVal+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteIcdId").val()+'#'+$("#diagnosisNoOfDaysIcdId").val()+'#'+$("#diagnosisDurationIcdId").val()+'#'+$("#diagnosisRemarksIcdId").val();
 						//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+icdCodeVal+'</label></a>');
 						
 						var DiagnosisJson ={
 			 						"IsSnomed"				:			"2" ,
 									"DiagnosisName" 		: 		 	icdCodeVal,
 									"DiagnosisCode" 		:			icdCode,
 									"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
 									"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
 									"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
 									"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
 									"DiagnosisRemarks"	:			$("#diagnosisRemarksIcdId").val()
 								};
 								
 						console.log(JSON.stringify(DiagnosisJson));
 						var temptoggle='';
 						if(diagnosisTypeName != '' ) 
 							temptoggle =diagnosisTypeName ;
 						if($('#diagnosisSiteId option:selected').text() !='')
 							temptoggle =diagnosisTypeName +'['+$('#diagnosisSiteId option:selected').text() +']' ;
 							
 						if($("#diagnosisRemarksIcdId").val() !=''){
 							temptoggle = temptoggle  +$("#diagnosisRemarksIcdId").val() ; 
 						}
 						$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" title='+temptoggle+' data-toggle="tooltip" type="button" class="value btn btn-xs">'+
 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
 				    			'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
 				    	 		'<span class="text">'+icdCodeVal+' </span>'+
 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 				    	 		'</button></label>');
 				 	}
 					 $(this).parent().parent().find('input[name=diseaseInput]').val('');
 			 		 $(this).parent().parent().find('input[name=icdCodeInput]').val('');  
 			 		$(this).parent().parent().find('select[name=diagnosisSiteIcd] ').val('0');
 				 	$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
 				 	$(this).parent().parent().find('textarea[name=diagnosisRemarksIcd]').val('');
 				 		
 		 		 }
 		 		else
 		 			 swal('Please Select atleast one reason');
 	 			 
 	 			
 	 			
 	 			 
 	 			
 	 		}
 	 		$('[data-toggle="tooltip"]').tooltip();
 	 		
 	    }
 	});
 	
 	var my_field = document.getElementById('icdCodeInputId');
 	
 	my_field.addEventListener('keyup',function(e){
 	//$("input[name=flexdatalist-diseaseInput]").on('keypress', function (e) {
 		console.log('15635555...............');
 		if (!e) e = window.event;
 	    var keyCode = e.keyCode || e.which;
 	    console.log(keyCode)
 	    if (keyCode == '13'){
 	    	console.log('2');	

 	 		if($('input[name=diagnosisDiseaseReference]').is(':checked'))
 	 		{
 	 			if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim().indexOf(';')>='0')
 	 	 		{
 	 				if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim()!='')
 	 		 		{  var diagnosisVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().split(';');
 	 			 	   var diagnosisCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode').split(';');
 	 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
 	 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
 	 			 	   console.log("diagnosisVAl -->> "+diagnosisVAl);
 	 			 	   console.log("diagnosisCode --->> "+diagnosisCode);
 	 			 	  console.log('diagnosisType ::: ::::: :::: '+diagnosisTypeCode+diagnosisTypeName);
 	 		 		   for(var i=0;i<(diagnosisVAl.length-1);i++)
 	 		 		   	{
 	 		 			  var tmp = 0; 
 	 		 				$('.diagnosisAdded').find('label .text').each(function(index){ 
 	 		 					if($(this).text().trim().toUpperCase()===diagnosisVAl[i].trim().toUpperCase()) 
 	 		 					{	tmp = 1; 
 	 		 						return false;  }
 	 		 				});
 	 		 				if(tmp==1)
 	 						{ 
 	 							swal(diagnosisVAl[i].trim()+", Already Added!!");
 	 							continue;
 	 						}
 	 		 				var strDiagnosisId=diagnosisCode[i]+'#'+diagnosisTypeCode+'#1^'+diagnosisVAl[i]+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteId").val()+'#'+$('input[name=diagnosisNoOfDays]').val()+'#'+$("#diagnosisDurationId").val()+'#'+$("#diagnosisRemarksId").val();
 	 				 		//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+diagnosisVAl[i]+'</label></a>');
 	 				 		
 	 		 				var temp=''; 
 	 		 				if($("#diagnosisSiteId").val()!= 0 && diagnosisTypeCode !=0)
 	 		 					temp=diagnosisTypeName +'['+ $("#diagnosisSiteId option:selected").text() +']';
 	 		 				else if($("#diagnosisSiteId").val()!= 0)
 	 		 					temp=+'['+ $("#diagnosisSiteId option:selected").text() +']';
 	 		 				else if(diagnosisTypeCode !=0 )
 	 		 					temp=diagnosisTypeName ;
 	 		 				
 	 		 				if($("#diagnosisRemarksId").val() !=''){
 	 		 					temp= temp+ $("#diagnosisRemarksId").val() ;
 	 		 				}
 	 		 				var DiagnosisJson ={
 	 		 						"IsSnomed"				:			"1" ,
 	 								"DiagnosisName" 		: 		 	diagnosisVAl[i],
 	 								"DiagnosisCode" 		:			diagnosisCode[i],
 	 								"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
 	 								"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
 	 								"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
 	 								"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
 	 								"DiagnosisRemarks"	:			$("#diagnosisRemarksId").val()
 	 							};
 	 							
 	 		 				console.log(JSON.stringify(DiagnosisJson));
 	 		 				
 	 				 		$('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs" title='+temp+' data-toggle="tooltip" >'+
 	 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
 	 				    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
 	 				    	 		'<span class="text">'+diagnosisVAl[i]+' </span>'+
 	 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 	 				    	 		'</button></label>');
 	 		 		   	}
 	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val('');
 	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode','');
 	 			 		$(this).parent().parent().find('select[name=diagnosisSite] ').val('0');
 	 			 		$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
 	 			 		$(this).parent().parent().find('textarea[name=diagnosisRemarks]').val('');
 	 			 		 
 	 		 		 }
 	 				else
 	 		 			 swal('Please Select atleast one reason');
 	 	 		}
 		 		else
 		 			 swal('Please Select atleast one reason');
 	 		}
 	 		else{
 	 			 var investigationVAl = $(this).parent().parent().find('input[name=icdCodeInput]').val();  
 	 		 	 var isValid = 0;
 	 		 	 var invObj = localStorage.getItem('icdJsonObj'); 
 	 	 		 invObj = JSON.parse(invObj.toString()); 
 	 	 		 for(var v=0; v<invObj.length;v++)
 	 	 		 { 
 	 	 			if (invObj[v].icdCode.toUpperCase() == investigationVAl.toUpperCase()) {
 	 	 				isValid=1;
 	 			        break;
 	 			    } 
 	 	 		  } 
 	 			if($(this).parent().parent().find('input[name=diseaseInput]').val().trim()!='')
 		 		{  
 	 				var icdCode = $(this).parent().parent().find('input[name=icdCodeInput]').val();
 	 				var icdCodeVal = $(this).parent().parent().find('input[name=diseaseInput]').val(); 
 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
 			 	   console.log('icdCodeVal ::: ::::: :::: '+icdCode+icdCodeVal); 
 			 	   
 			 	   var tmp = 0; 
 					$('.diagnosisAdded').find('label .text').each(function(index){ 
 						if($(this).text().trim().toUpperCase()===icdCodeVal.trim().toUpperCase()) 
 						{	tmp = 1; 
 							return false;  }
 					});
 					if(tmp==1)
 					{ 
 						swal(icdCodeVal.trim()+", Already Added!!"); 
 					}
 					else
 					{					  
 						var strDiagnosisId=icdCode+'#'+diagnosisTypeCode+'#0^'+icdCodeVal+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteIcdId").val()+'#'+$("#diagnosisNoOfDaysIcdId").val()+'#'+$("#diagnosisDurationIcdId").val()+'#'+$("#diagnosisRemarksIcdId").val();
 						//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+icdCodeVal+'</label></a>');
 						
 						var DiagnosisJson ={
 			 						"IsSnomed"				:			"2" ,
 									"DiagnosisName" 		: 		 	icdCodeVal,
 									"DiagnosisCode" 		:			icdCode,
 									"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
 									"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
 									"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
 									"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
 									"DiagnosisRemarks"	:			$("#diagnosisRemarksIcdId").val()
 								};
 								
 						console.log(JSON.stringify(DiagnosisJson));
 						var temptoggle='';
 						if(diagnosisTypeName != '' ) 
 							temptoggle =diagnosisTypeName ;
 						if($('#diagnosisSiteId option:selected').text() !='')
 							temptoggle =diagnosisTypeName +'['+$('#diagnosisSiteId option:selected').text() +']' ;
 							
 						if($("#diagnosisRemarksIcdId").val() !=''){
 							temptoggle = temptoggle  +$("#diagnosisRemarksIcdId").val() ; 
 						}
 						$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" title='+temptoggle+' data-toggle="tooltip" type="button" class="value btn btn-xs">'+
 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
 				    			'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
 				    	 		'<span class="text">'+icdCodeVal+' </span>'+
 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 				    	 		'</button></label>');
 				 	}
 					 $(this).parent().parent().find('input[name=diseaseInput]').val('');
 			 		 $(this).parent().parent().find('input[name=icdCodeInput]').val('');  
 			 		$(this).parent().parent().find('select[name=diagnosisSiteIcd] ').val('0');
 				 	$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
 				 	$(this).parent().parent().find('textarea[name=diagnosisRemarksIcd]').val('');
 				 		
 		 		 }
 		 		else
 		 			 swal('Please Select atleast one reason');
 	 			 
 	 			
 	 			
 	 			 
 	 			
 	 		}
 	 		$('[data-toggle="tooltip"]').tooltip();
 	 		
 	    }
 	}, false);
 	
 	
	$("input[name=txt-snomed-ct-search_VR]").on('keypress', function (e) {
 		if (!e) e = window.event;
 	    var keyCode = e.keyCode || e.which;
 	    if (keyCode == '13'){
 	    	

 	 		console.log('Index ZVal()'+$(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim().indexOf(';'));
 	 		if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim().indexOf(';')>='0')
 	 		{ 		console.log('true::::::::');	
 			 		if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim()!='')
 			 		{  
 			 			var reasonOfVisitVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().split(';');
 				 	   var reasonOfVisitCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode').split(';');
 				 	   for(var i=0;i<(reasonOfVisitVAl.length-1);i++)
 			 			{
 			 			  var tmp = 0; 
 							$('.reasonOfVisitAdded').find('label .text').each(function(index){ 
 								if($(this).text().trim().toUpperCase()===reasonOfVisitVAl[i].trim().toUpperCase()) 
 								{	tmp = 1;
 								
 									return false;  }
 							});
 							if(tmp==1)
 							{
 			
 								swal(reasonOfVisitVAl[i].trim()+", Already Added!!");
 								continue;
 							}
 							//var visitReasonId=reasonOfVisitVAl[i]+'^'+$("#chiefComplaintSiteId").val()+'^'+$("#chiefComplaintNoOfDaysId").val()+'^'+$("#chiefComplaintDurationId").val()+"^";
 							var visitReasonId=reasonOfVisitVAl[i]+'^'+$("#chiefComplaintSiteId").val()+'^'+$("#chiefComplaintNoOfDaysId").val()+'^'+$("#chiefComplaintDurationId").val()+'^'+$("#reasonofvisitRemarksId").val();

 							console.log('visitReasonId'+$('#chiefComplaintSiteId option:selected').text());
 							 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left:5px"><label><input class="checkedInput" type="checkbox" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked> '+reasonOfVisitVAl[i]+'</label></a>');
 							var temp='';
 							console.log($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 );
 							
 							if($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 ) 
 							 temp=$('#chiefComplaintSiteId option:selected').text() + '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ; 
 							else if($("#chiefComplaintSiteId").val() != 0)
 								 temp=$('#chiefComplaintSiteId option:selected').text() ;
 							else 
 								 temp= '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ;
 							
 							
 							
 							var chiefComplaintJson ={
 									"IsExternalVisit"	:		"1" ,
 									"VisitReasonName" : 		 reasonOfVisitVAl[i],
 									"VisitReasonCode" :			 reasonOfVisitCode[i],
 									"VisitReasonSideCode" : 		 $("#chiefComplaintSiteId").val() ,
 									"VisitReasonSideName" :			$('#chiefComplaintSiteId option:selected').text(),
 									"VisitReasonNoOfDays" : 	 $("#chiefComplaintNoOfDaysId").val() ,
 									"VisitComplaintDurationCode" : $("#chiefComplaintDurationId").val(),
 									"VisitComplaintDurationName" : $('#chiefComplaintDurationId option:selected').text(),
 									"VisitReasonRemarks"		: $("#reasonofvisitRemarksId").val()
 								};
 								
 								console.log(JSON.stringify(chiefComplaintJson));
 								
 								
 								'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
 								
 							$(this).parent().parent().parent().parent().find('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs reasonOfVisitAdd " data-toggle="tooltip" title='+ temp +'>'+
 						    	 		'<input type="checkbox" class="checkedInput" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked="">  '+
 						    	 		'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
 						    	 		'<span class="text">'+reasonOfVisitVAl[i]+' </span>'+
 						    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 						    	 		'</button></label>');
 			 			   }
 						 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val('');
 				 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode','');
 			 		 }
 			 		else
 			 			 swal('Please Select atleast one reason');
 	 		}
 	 		else if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim() != ''){
 	 			console.log(';;;;;;;;;15;;;;'+$(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim());
 	            var generalComplaintVal = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val() ;  //$(this).parent().parent().find('input[name=generalComplaint]').val();
 	            if(generalComplaintVal.trim()!='')
 	             {  
 	               var tmp = 0; 
 	               $('.reasonOfVisitAdded').find('label .text').each(function(index){ 
 	                 if($(this).text().split("*")[0].trim().toUpperCase()===generalComplaintVal.trim().toUpperCase()) 
 	                 { tmp = 1; 
 	                   return false;  }
 	               });
 	               if(tmp==1)
 	               {
 	                 swal("Already Added!!");
 	                 $('#generalComplaintId').val('');
 	                 return false;
 	               }
 	               else
 	               {
 	            	  var generalComplaintChkBoxVal='';
 	                 var chiefComplaintNoOfDays=$(this).parent().parent().parent().find('input[name="chiefComplaintNoOfDays"]').val();
 	                 var siteId=$(this).parent().parent().parent().find('select[name="chiefComplaintSite"] option:selected').val();
 	                 var chiefComplaintDuration=$(this).parent().parent().parent().find('select[name="chiefComplaintDuration"] option:selected').val();
 	                 if(siteId != '0' || (chiefComplaintDuration != '0' && chiefComplaintNoOfDays !='')){
 	                     if(siteId != '0' && (chiefComplaintDuration != '0' && chiefComplaintNoOfDays !=''))
 	                        generalComplaintChkBoxVal='0^'+generalComplaintVal+'^'+siteId+'^'+chiefComplaintNoOfDays+'^'+chiefComplaintDuration;
 	                     else if(siteId != '0')
 	                        generalComplaintChkBoxVal='0^'+generalComplaintVal+'^'+siteId+'^0^0'; 
 	                     else if(chiefComplaintDuration != '0' && chiefComplaintNoOfDays !='')
 	                        generalComplaintChkBoxVal='0^'+generalComplaintVal+'^0^'+chiefComplaintNoOfDays+'^'+chiefComplaintDuration;
 	                     else{
 	                       swal('Please select all fields');
 	                       return false;
 	                     }
 	                 }
 	               /*  else if(chiefComplaintDuration != '0' &&  chiefComplaintNoOfDays == ''){
 	                   swal('Please enter no of days.');
 	                   return false;
 	                 }
 	                 else if(chiefComplaintDuration == '0' &&  chiefComplaintNoOfDays != ''){
 	                   swal('Please select duration.');
 	                   return false;
 	                 }*/
 	                 else{
 	                   var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^0^^0';  
 	                 }
 	                 
 	                 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="visitReason" value="'+generalComplaintChkBoxVal+'"  checked> '+generalComplaintVal+'(ext)</label></a>');
 	                 var temp='';
 						console.log($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 );
 						
 						if($("#chiefComplaintSiteId").val() != 0 && $("#chiefComplaintDurationId").val() != 0 ) 
 						 temp=$('#chiefComplaintSiteId option:selected').text() + '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ; 
 						else if($("#chiefComplaintSiteId").val() != 0)
 							 temp=$('#chiefComplaintSiteId option:selected').text() ;
 						else 
 							 temp= '[' + $("#chiefComplaintNoOfDaysId").val() + $('#chiefComplaintDurationId option:selected').text() +']' ;	
 						
 						console.log('temp::::::::::'+temp);
 						console.log('generalComplaintVal::::::::::'+generalComplaintVal);
 						console.log('generalComplaintChkBoxVal::::::::::'+generalComplaintChkBoxVal);
 						
 						var chiefComplaintJson ={
 								"IsExternalVisit"	:		"1" ,
 								"VisitReasonName" : 		 generalComplaintVal,
 								"VisitReasonCode" :			 "0",
 								"VisitReasonSideCode" : 	 $("#chiefComplaintSiteId").val() ,
 								"VisitReasonSideName" :		$('#chiefComplaintSiteId option:selected').text(),
 								"VisitReasonNoOfDays" : 	 $("#chiefComplaintNoOfDaysId").val() ,
 								"VisitComplaintDurationCode" : $("#chiefComplaintDurationId").val(),
 								"VisitComplaintDurationName" : $('#chiefComplaintDurationId option:selected').text(),
 								"VisitReasonRemarks"		: $("#reasonofvisitRemarksId").val()
 							};
 							
 							console.log(JSON.stringify(chiefComplaintJson));
 							
 						$(this).parent().parent().parent().parent().find('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs"  data-toggle="tooltip" title='+ temp +'>'+
 				    	 		'<input type="checkbox" class="checkedInput" name="visitReason" value="'+generalComplaintChkBoxVal+'" checked="">  '+
 				    	 		'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
 				    	 		'<span class="text">'+generalComplaintVal+'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 				    	 		'</button></label>');

 	               }
 	              
 	              //$(this).parent().parent().find('input[name=generalComplaint]').val('');
 	               $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val('');
 			 	  $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode','');
 	              $(this).parent().parent().find('input[name="chiefComplaintNoOfDays"]').val('');
 	              $(this).parent().parent().find('select[name="chiefComplaintSite"]').val('0');
 	              $(this).parent().parent().find('select[name="chiefComplaintDuration"] ').val('1');
 	             }
 	             else{
 	               swal('Please enter other general complaint to be added');
 	             }
 	     }
 	 		else
 	 			 {swal('Please Select atleast one reason');}
 	 		$('[data-toggle="tooltip"]').tooltip();
 	 	
 	    	
 	    	
 	    	
 	    	
 	    }
	});
	
 	$('.diagnosisAdd').click(function(){
 		if($('input[name=diagnosisDiseaseReference]').is(':checked'))
 		{
 			if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim().indexOf(';')>='0')
 	 		{
 				if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim()!='')
 		 		{  var diagnosisVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().split(';');
 			 	   var diagnosisCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode').split(';');
 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
 			 	   console.log("diagnosisVAl -->> "+diagnosisVAl);
 			 	   console.log("diagnosisCode --->> "+diagnosisCode);
 			 	  console.log('diagnosisType ::: ::::: :::: '+diagnosisTypeCode+diagnosisTypeName);
 		 		   for(var i=0;i<(diagnosisVAl.length-1);i++)
 		 		   	{
 		 			  var tmp = 0; 
 		 				$('.diagnosisAdded').find('label .text').each(function(index){ 
 		 					if($(this).text().trim().toUpperCase()===diagnosisVAl[i].trim().toUpperCase()) 
 		 					{	tmp = 1; 
 		 						return false;  }
 		 				});
 		 				if(tmp==1)
 						{ 
 							swal(diagnosisVAl[i].trim()+", Already Added!!");
 							continue;
 						}
 		 				var strDiagnosisId=diagnosisCode[i]+'#'+diagnosisTypeCode+'#1^'+diagnosisVAl[i]+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteId").val()+'#'+$('input[name=diagnosisNoOfDays]').val()+'#'+$("#diagnosisDurationId").val()+'#'+$("#diagnosisRemarksId").val();
 				 		//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+diagnosisVAl[i]+'</label></a>');
 				 		
 		 				var temp=''; 
 		 				if($("#diagnosisSiteId").val()!= 0 && diagnosisTypeCode !=0)
 		 					temp=diagnosisTypeName +'['+ $("#diagnosisSiteId option:selected").text() +']';
 		 				else if($("#diagnosisSiteId").val()!= 0)
 		 					temp=+'['+ $("#diagnosisSiteId option:selected").text() +']';
 		 				else if(diagnosisTypeCode !=0 )
 		 					temp=diagnosisTypeName ;
 		 				
 		 				if($("#diagnosisRemarksId").val() !=''){
 		 					temp= temp+ $("#diagnosisRemarksId").val() ;
 		 				}
 		 				var DiagnosisJson ={
 		 						"IsSnomed"				:			"1" ,
 								"DiagnosisName" 		: 		 	diagnosisVAl[i],
 								"DiagnosisCode" 		:			diagnosisCode[i],
 								"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
 								"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
 								"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
 								"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
 								"DiagnosisRemarks"	:			$("#diagnosisRemarksId").val()
 							};
 							
 		 				console.log(JSON.stringify(DiagnosisJson));
 		 				
 				 		$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs" title='+temp+' data-toggle="tooltip" >'+
 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
 				    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
 				    	 		'<span class="text">'+diagnosisVAl[i]+' </span>'+
 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 				    	 		'</button></label>');
 		 		   	}
 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val('');
 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode','');
 			 		$(this).parent().parent().find('select[name=diagnosisSite] ').val('0');
 			 		$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
 			 		$(this).parent().parent().find('textarea[name=diagnosisRemarks]').val('');
 			 		 
 		 		 }
 				else
 		 			 swal('Please Select atleast one reason');
 	 		}
	 		else
	 			 swal('Please Select atleast one reason');
 		}
 		else{
 			 var investigationVAl = $(this).parent().parent().find('input[name=icdCodeInput]').val();  
 		 	 var isValid = 0;
 		 	 var invObj = localStorage.getItem('icdJsonObj'); 
 	 		 invObj = JSON.parse(invObj.toString()); 
 	 		 for(var v=0; v<invObj.length;v++)
 	 		 { 
 	 			if (invObj[v].icdCode.toUpperCase() == investigationVAl.toUpperCase()) {
 	 				isValid=1;
 			        break;
 			    } 
 	 		  } 
 			if($(this).parent().parent().find('input[name=diseaseInput]').val().trim()!='')
	 		{  
 				var icdCode = $(this).parent().parent().find('input[name=icdCodeInput]').val();
 				var icdCodeVal = $(this).parent().parent().find('input[name=diseaseInput]').val(); 
		 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
		 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
		 	   console.log('icdCodeVal ::: ::::: :::: '+icdCode+icdCodeVal); 
		 	   
		 	   var tmp = 0; 
				$('.diagnosisAdded').find('label .text').each(function(index){ 
					if($(this).text().trim().toUpperCase()===icdCodeVal.trim().toUpperCase()) 
					{	tmp = 1; 
						return false;  }
				});
				if(tmp==1)
				{ 
					swal(icdCodeVal.trim()+", Already Added!!"); 
				}
				else
				{					  
					var strDiagnosisId=icdCode+'#'+diagnosisTypeCode+'#0^'+icdCodeVal+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteIcdId").val()+'#'+$("#diagnosisNoOfDaysIcdId").val()+'#'+$("#diagnosisDurationIcdId").val()+'#'+$("#diagnosisRemarksIcdId").val();
					//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+icdCodeVal+'</label></a>');
					
					var DiagnosisJson ={
		 						"IsSnomed"				:			"2" ,
								"DiagnosisName" 		: 		 	icdCodeVal,
								"DiagnosisCode" 		:			icdCode,
								"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
								"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
								"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
								"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
								"DiagnosisRemarks"	:				$("#diagnosisRemarksIcdId").val()
							};
							
					console.log(JSON.stringify(DiagnosisJson));
					var temptoggle='';
					if(diagnosisTypeName != '' ) 
						temptoggle =diagnosisTypeName ;
					if($('#diagnosisSiteId option:selected').text() !='')
						temptoggle =diagnosisTypeName +'['+$('#diagnosisSiteId option:selected').text() +']' ;
						
					if($("#diagnosisRemarksIcdId").val() !=''){
						temptoggle = temptoggle  +$("#diagnosisRemarksIcdId").val() ; 
					}
					$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" title='+temptoggle+' data-toggle="tooltip" type="button" class="value btn btn-xs">'+
			    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
			    			'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
			    	 		'<span class="text">'+icdCodeVal+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
			 	}
				 $(this).parent().parent().find('input[name=diseaseInput]').val('');
		 		 $(this).parent().parent().find('input[name=icdCodeInput]').val('');  
		 		$(this).parent().parent().find('select[name=diagnosisSiteIcd] ').val('0');
			 	$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
			 	$(this).parent().parent().find('textarea[name=diagnosisRemarksIcd]').val('');
			 		
	 		 }
	 		else
	 			 swal('Please Select atleast one reason');
 			 
 			
 			
 			 
 			
 		}
 		$('[data-toggle="tooltip"]').tooltip();
 	});
 	
 	 $("input[name=diseaseInput]").on('select:flexdatalist', function (e, set, options) {
	  	    var val = set.diagnosisName;
	  	    /*if($('#ICDcodeLst option').filter(function(){
	  	        return this.value === val;        
	  	    }).length) {
	  	    	var diseaseInputVAl1 = $(this).parent().find('#ICDcodeLst option[value="'+val+'"]').attr('id');*/  
	  	    	var icdCodeInputVAl1 = set.icdCode;
	  	    	$(this).parent().parent().parent().find('input[name=icdCodeInput]').val(icdCodeInputVAl1); 
	  	    /*}*/
	  	    	

	  	   	//$("input[name=flexdatalist-diseaseInput]").on('keypress', function (e) {
	  	   		console.log('15635555...............');
	  	   		if (!e) e = window.event;
	  	   	    var keyCode = e.keyCode || e.which;
	  	   	    console.log(keyCode)
	  	   	    if (true){
	  	   	    	console.log('2');	

	  	   	 		if($('input[name=diagnosisDiseaseReference]').is(':checked'))
	  	   	 		{
	  	   	 			if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim().indexOf(';')>='0')
	  	   	 	 		{
	  	   	 				if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim()!='')
	  	   	 		 		{  var diagnosisVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().split(';');
	  	   	 			 	   var diagnosisCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode').split(';');
	  	   	 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
	  	   	 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
	  	   	 			 	   console.log("diagnosisVAl -->> "+diagnosisVAl);
	  	   	 			 	   console.log("diagnosisCode --->> "+diagnosisCode);
	  	   	 			 	  console.log('diagnosisType ::: ::::: :::: '+diagnosisTypeCode+diagnosisTypeName);
	  	   	 		 		   for(var i=0;i<(diagnosisVAl.length-1);i++)
	  	   	 		 		   	{
	  	   	 		 			  var tmp = 0; 
	  	   	 		 				$('.diagnosisAdded').find('label .text').each(function(index){ 
	  	   	 		 					if($(this).text().trim().toUpperCase()===diagnosisVAl[i].trim().toUpperCase()) 
	  	   	 		 					{	tmp = 1; 
	  	   	 		 						return false;  }
	  	   	 		 				});
	  	   	 		 				if(tmp==1)
	  	   	 						{ 
	  	   	 							swal(diagnosisVAl[i].trim()+", Already Added!!");
	  	   	 							continue;
	  	   	 						}
	  	   	 		 				var strDiagnosisId=diagnosisCode[i]+'#'+diagnosisTypeCode+'#1^'+diagnosisVAl[i]+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteId").val()+'#'+$('input[name=diagnosisNoOfDays]').val()+'#'+$("#diagnosisDurationId").val()+'#'+$("#diagnosisRemarksId").val();
	  	   	 				 		//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+diagnosisVAl[i]+'</label></a>');
	  	   	 				 		
	  	   	 		 				var temp=''; 
	  	   	 		 				if($("#diagnosisSiteId").val()!= 0 && diagnosisTypeCode !=0)
	  	   	 		 					temp=diagnosisTypeName +'['+ $("#diagnosisSiteId option:selected").text() +']';
	  	   	 		 				else if($("#diagnosisSiteId").val()!= 0)
	  	   	 		 					temp=+'['+ $("#diagnosisSiteId option:selected").text() +']';
	  	   	 		 				else if(diagnosisTypeCode !=0 )
	  	   	 		 					temp=diagnosisTypeName ;
	  	   	 		 				
	  	   	 		 				if($("#diagnosisRemarksId").val() !=''){
	  	   	 		 					temp= temp+ $("#diagnosisRemarksId").val() ;
	  	   	 		 				}
	  	   	 		 				var DiagnosisJson ={
	  	   	 		 						"IsSnomed"				:			"1" ,
	  	   	 								"DiagnosisName" 		: 		 	diagnosisVAl[i],
	  	   	 								"DiagnosisCode" 		:			diagnosisCode[i],
	  	   	 								"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
	  	   	 								"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
	  	   	 								"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
	  	   	 								"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
	  	   	 								"DiagnosisRemarks"	:			$("#diagnosisRemarksId").val()
	  	   	 							};
	  	   	 							
	  	   	 		 				console.log(JSON.stringify(DiagnosisJson));
	  	   	 		 				
	  	   	 				 		$('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs" title='+temp+' data-toggle="tooltip" >'+
	  	   	 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
	  	   	 				    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
	  	   	 				    	 		'<span class="text">'+diagnosisVAl[i]+' </span>'+
	  	   	 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	  	   	 				    	 		'</button></label>');
	  	   	 		 		   	}
	  	   	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val('');
	  	   	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode','');
	  	   	 			 		$(this).parent().parent().find('select[name=diagnosisSite] ').val('0');
	  	   	 			 		$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
	  	   	 			 		$(this).parent().parent().find('textarea[name=diagnosisRemarks]').val('');
	  	   	 			 		 
	  	   	 		 		 }
	  	   	 				else
	  	   	 		 			 swal('Please Select atleast one reason');
	  	   	 	 		}
	  	   		 		else
	  	   		 			 swal('Please Select atleast one reason');
	  	   	 		}
	  	   	 		else{
	  	   	 			 var investigationVAl = $(this).parent().parent().find('input[name=icdCodeInput]').val();  
	  	   	 		 	 var isValid = 0;
	  	   	 		 	 var invObj = localStorage.getItem('icdJsonObj'); 
	  	   	 	 		 invObj = JSON.parse(invObj.toString()); 
	  	   	 	 		 for(var v=0; v<invObj.length;v++)
	  	   	 	 		 { 
	  	   	 	 			if (invObj[v].icdCode.toUpperCase() == investigationVAl.toUpperCase()) {
	  	   	 	 				isValid=1;
	  	   	 			        break;
	  	   	 			    } 
	  	   	 	 		  } 
	  	   	 			if($(this).parent().parent().find('input[name=diseaseInput]').val().trim()!='')
	  	   		 		{  
	  	   	 				var icdCode = $(this).parent().parent().find('input[name=icdCodeInput]').val();
	  	   	 				var icdCodeVal = $(this).parent().parent().find('input[name=diseaseInput]').val(); 
	  	   			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
	  	   			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
	  	   			 	   console.log('icdCodeVal ::: ::::: :::: '+icdCode+icdCodeVal); 
	  	   			 	   
	  	   			 	   var tmp = 0; 
	  	   					$('.diagnosisAdded').find('label .text').each(function(index){ 
	  	   						if($(this).text().trim().toUpperCase()===icdCodeVal.trim().toUpperCase()) 
	  	   						{	tmp = 1; 
	  	   							return false;  }
	  	   					});
	  	   					if(tmp==1)
	  	   					{ 
	  	   						swal(icdCodeVal.trim()+", Already Added!!"); 
	  	   					}
	  	   					else
	  	   					{					  
	  	   						var strDiagnosisId=icdCode+'#'+diagnosisTypeCode+'#0^'+icdCodeVal+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteIcdId").val()+'#'+$("#diagnosisNoOfDaysIcdId").val()+'#'+$("#diagnosisDurationIcdId").val()+'#'+$("#diagnosisRemarksIcdId").val();
	  	   						//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+icdCodeVal+'</label></a>');
	  	   						
	  	   						var DiagnosisJson ={
	  	   			 						"IsSnomed"				:			"2" ,
	  	   									"DiagnosisName" 		: 		 	icdCodeVal,
	  	   									"DiagnosisCode" 		:			icdCode,
	  	   									"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
	  	   									"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
	  	   									"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
	  	   									"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
	  	   									"DiagnosisRemarks"	:			$("#diagnosisRemarksIcdId").val()
	  	   								};
	  	   								
	  	   						console.log(JSON.stringify(DiagnosisJson));
	  	   						var temptoggle='';
	  	   						if(diagnosisTypeName != '' ) 
	  	   							temptoggle =diagnosisTypeName ;
	  	   						if($('#diagnosisSiteId option:selected').text() !='')
	  	   							temptoggle =diagnosisTypeName +'['+$('#diagnosisSiteId option:selected').text() +']' ;
	  	   							
	  	   						if($("#diagnosisRemarksIcdId").val() !=''){
	  	   							temptoggle = temptoggle  +$("#diagnosisRemarksIcdId").val() ; 
	  	   						}
	  	   						$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" title='+temptoggle+' data-toggle="tooltip" type="button" class="value btn btn-xs">'+
	  	   				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
	  	   				    			'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
	  	   				    	 		'<span class="text">'+icdCodeVal+' </span>'+
	  	   				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	  	   				    	 		'</button></label>');
	  	   				 	}
	  	   					 $(this).parent().parent().find('input[name=diseaseInput]').val('');
	  	   			 		 $(this).parent().parent().find('input[name=icdCodeInput]').val('');  
	  	   			 		$(this).parent().parent().find('select[name=diagnosisSiteIcd] ').val('0');
	  	   				 	$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
	  	   				 	$(this).parent().parent().find('textarea[name=diagnosisRemarksIcd]').val('');
	  	   				 		
	  	   		 		 }
	  	   		 		else
	  	   		 			 swal('Please Select atleast one reason');
	  	   	 			 
	  	   	 			
	  	   	 			
	  	   	 			 
	  	   	 			
	  	   	 		}
	  	   	 		$('[data-toggle="tooltip"]').tooltip();
	  	   	 		
	  	   	    }
	  	   	
	  	});
 	 
	/*$("input[name=txt-snomed-ct-search_VR2]").on('keypress', function (e) {
 		if (!e) e = window.event;
 	    var keyCode = e.keyCode || e.which;
 	    if (keyCode == '13'){ 
 	 		if($(this).val().trim().indexOf(';')>='0')
 	 		{  var diagnosisVAl = $(this).val().split(';');
		 	   var diagnosisCode = $(this).attr('diagnosisCode').split(';');
		 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
		 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
		 	   console.log('diagnosisType ::: ::::: :::: '+diagnosisTypeCode+diagnosisTypeName);
	 		   for(var i=0;i<(diagnosisVAl.length-1);i++)
	 			   {
		 			  var tmp = 0; 
		 				$('.diagnosisAdded').find('label .text').each(function(index){ 
		 					if($(this).text().trim().toUpperCase()===diagnosisVAl[i].trim().toUpperCase()) 
		 					{	tmp = 1; 
		 						return false;  }
		 				});
		 				if(tmp==1)
						{ 
							swal(diagnosisVAl[i].trim()+", Already Added!!");
							continue;
						}
				 		//$(this).parent().parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+diagnosisCode[i]+'#'+diagnosisTypeCode+'" checked> '+diagnosisVAl[i]+'</label></a>');
				 		 
		 				$(this).parent().parent().parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+diagnosisCode[i]+'#'+diagnosisTypeCode+'" checked="">  '+
 				    	 		'<span class="text">'+diagnosisVAl[i]+' </span>'+
 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 				    	 		'</button></label>');
	 			   }
		 		 $(this).val('');
		 		 $(this).attr('diagnosisCode','');
 	 		 }
 	 		else
 	 			 swal('Please Select atleast one reason');
 	      return true;
 	    }
	});*/

 	document.getElementById('externalDrugStartDate').valueAsDate = new Date();
 	document.getElementById('drugStartDate').valueAsDate = new Date();
 	var dtVal = new Date(); 
 	var curDate = dtVal.getDate();
 	curDate = curDate.toString().length>1 ? curDate : "0"+curDate;
 	var curMon = dtVal.getMonth() + 1;
 	curMon = curMon.toString().length>1 ? curMon : "0"+curMon;
 	//alert(curDate+'------: '+curMon);
 	$('#drugStartDate').attr('min',dtVal.getFullYear()+"-"+curMon +"-"+curDate);
 	$('input[name=followUpPlannedVisitDate]').attr('min',dtVal.getFullYear()+"-"+curMon +"-"+curDate);
 	

 	
 	$('input[name=drugName]').on('change:flexdatalist',function(){
 		var drugName = $(this).val();
 		console.log(drugName);
 		let f = 0;
 		$.each(JSON.parse(localStorage.getItem('drugJsonObj')), function(i, v) {
		    if (v.drugName.toUpperCase() == drugName.toUpperCase()) {
		        console.log(v.drugId+'::::::::>>>>'+v.drugName);
		        
		        var intemtype=(v.drugId).split('#')[1];
		        console.log( $('#drugDosageId').html());
		        var options = $('#drugDosageId option');
		        var tempcount=0;
		        var values = $.map(options ,function(option) {
		        	console.log(intemtype == (option.value).split('^')[3]);
		        	if(intemtype == (option.value).split('^')[3])
		        		{
		        			$("#drugDosageId").val(option.value);
		        			tempcount =1 ;
		        			 
		        		}else{
		        			//$(this).remove();
		        			//tempcount=0;
		        			$("#drugDosageId option[value='"+option.value+"']").remove();
		        		}
		        	$("#drugDosageId").prop("selectedIndex", 0);
		        	//console.log(':::::::1222::'+'#drugDosageId').html());
		        	/*if(tempcount == 0)
		        		{
		        		$("#drugDosageId").html('<option value="0" >select </option>');
		        		}
		        	*/
		        	/*else{
		        			$("#drugDosageId").val('0');
		        		}*/
		            return option.value;
		        });
		        //console.log(tempcount + 'tempcounttempcounttempcount');
		        if(tempcount == 0){
		        	
		        	$("#drugDosageId").empty().append('<option value="0">Select</option>');
		        }
		        	
		        console.log(values);
		        //console.log($('select[name=drugDosage]') + ' intemtype ' +intemtype);
		        
		        f=1;
		        return;
		    } 
		 });
 		if(f==1)
 			$('input[name=flexdatalist-drugName]').css('background-color','#FFFFFF');
 		else 
 			$('input[name=flexdatalist-drugName]').css('background-color','#FBE2E2');
 			
 		/*if($('#drugNameLst option').filter(function(){
	 		   return $(this).val().split('(')[0] === drugName.split('(')[0];        
	 		  }).length) 
 			{
				$(this).removeAttr('style');
 				return true;
 			}
 		else
 			{
 				$(this).css('background-color','#FBE2E2');
 			}*/
 	});
 	$('.drugsAdviceAdd').click(function(){	
 		 var drugName = $(this).parent().parent().parent().find('input[name=drugName]').val();
 		 var drugId = '';
 		 var isValid = 0;
	 		$.each(JSON.parse(localStorage.getItem('drugJsonObj')), function(i, v) {
			    if (v.drugName.toUpperCase() == drugName.toUpperCase()) {
			        console.log(v.drugId+'::::::::>>>>'+v.drugName); 
			 	    drugId = v.drugId;
			 	    isValid=1;
			        return;
			    } 
			 });
 		 var drugDosage1 = $(this).parent().parent().find('select[name=drugDosage] option:selected').text();
 		 if(drugDosage1 =='Select')
 			drugDosage ='--';
 		 else
 			drugDosage=drugDosage1;
 		 var drugDosageID = $(this).parent().parent().find('select[name=drugDosage]  option:selected').val().split('^')[0];
 		 var drugFrequencyId = $(this).parent().parent().find('select[name=drugFrequency]').val();
 		 var drugFrequency1 = $(this).parent().parent().find('select[name=drugFrequency] option:selected').text();
 		 if(drugFrequency1 =='Select')
 			drugFrequency='--';
 		 else
 			drugFrequency=drugFrequency1;
 		 // alert(drugFrequency+drugFrequencyId);
 		 var drugStartDate = $(this).parent().parent().find('input[name=drugStartDate]').val();
 		 var drugDays = $(this).parent().parent().find('input[name=drugDays]').val();
 		 var drugQuantity = $(this).parent().parent().find('input[name=drugQuantity]').val(); 
 		 var drugInstructions = $(this).parent().parent().find('input[name=drugInstructions]').val();
 		 var tmp = 0; 
			$('#drugAdviceListTable tbody').find('tr').each(function(index){
				if($(this).find('td').eq(0).children('input').val().split('&&')[1].split('#')[0]==drugId.trim().split('#')[0]) 
				{	tmp = 1; 
					return false;  }
			});
			if(drugQuantity=='' || drugQuantity=='0' ){
				swal("Quantity Must be Greater than Zero!!");
				return false;
			}
		if(tmp==2)  //if(tmp==1) due to progressive drug doase
		{ 
			 swal("Already Added!!");
			 $(this).parent().parent().parent().find('input[name=drugName]').val(''); 
			 $(this).parent().parent().find('select[name=drugDosage]').val('');   
			 /*$(this).parent().parent().find('input[name=drugStartDate]').val('');*/
		 	 document.getElementById('drugStartDate').valueAsDate = new Date();
			 $(this).parent().parent().find('input[name=drugDays]').val('');
			 $(this).parent().parent().find('input[name=drugQuantity]').val('');
			 $(this).parent().parent().find('input[name=drugInstructions]').val('');
			return false;
		} 
 		 
 		 if(drugName.trim()!='')   // && drugInstructions.trim()!=''
 		 {    
  	 		if(isValid==1) 
  	 		{
  	 			console.log('drugDays::::::::'+drugDays);
  	 			drugDays= (drugDays=='') ? '0' : drugDays ;
  	 			console.log('drugDays::::::::'+drugDays);
  	 			drugQuantity= (drugQuantity == '') ? '0' :drugQuantity;
 			var HiddenDrugAdvice=drugName+'&&'+drugId+'&&'+drugDosage+'&&'+drugDosageID+'&&'+drugFrequency+'&&'+drugFrequencyId+'&&'+drugStartDate+'&&'+drugDays+'#'+drugQuantity+'&&'+drugInstructions; //drugInstructions
	 		
 			var DrugJson ={
 					"IsExterNal"	:		"0" ,	
 					"DrugName"		 :	 drugName ,
 					"DrugCode"		 :	 drugId ,
 					"DoaseName"		:	drugDosage ,
 					"DoaseCode"		:	drugDosageID ,
 					"FrequencyName"	:	drugFrequency ,
 					"FrequencyCode" :	drugFrequencyId ,
 					"StartDate"		:	drugStartDate ,
 					"DrugDays"		:	drugDays ,
 					"DrugQuantity"	:	drugQuantity ,
 					"DrugInstruction" :	drugInstructions
 					
 			}
 			
 			console.log(JSON.stringify(DrugJson));
 			
 			// alert(HiddenDrugAdvice);
	 		 /*$(this).parent().parent().parent().find('.drugsAdvicesAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="drugsAdvices" checked> '+drugName+' '+drugDosage+' '+drugFrequency+'</label></a>');*/
/*	 		 $(this).parent().parent().parent().parent().find('.table').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+HiddenDrugAdvice+'"  checked></td><td>'+drugName+'</td><td>'+drugDosage+'</td><td>'+drugFrequency+'</td><td>'+drugStartDate+'</td><td>'+drugDays+'</td><td><a style="color: #109f1c" data-toggle="modal" data-target="#drugAdvicesInstructionsModal">'+drugInstructions+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td></tr>');*/
   		     $(this).parent().parent().parent().parent().find('#drugAdviceListTable').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+HiddenDrugAdvice+'"  checked><i class="text1" style="display :none">'+JSON.stringify(DrugJson)+' </i></td><td>'+drugName+'</td><td>'+drugDosage+'</td><td>'+drugFrequency+'</td><td>'+drugStartDate+'</td><td>'+drugDays+'</td><td>'+drugQuantity+'</td><td><a class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+drugInstructions+'" onclick="$(\'#drugAdvicesInstructionsModal\').modal(\'show\');">'+drugInstructions.substring(0, 4)+'..'+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td><td><button class="btn btn-xs monogrambtn" type="button" data-toggle="modal" data-target="CimsMonographId"><i class="fas fa-capsules" style="color: Crimson;"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs Genericbarndbtn" type="button"  data-toggle="modal" data-target="GenericMonogramId"><i class="fas fa-tablets" style="color: DeepSkyBlue;"></i></button></td></tr>'); // drugInstructions   drugInstructions.substring(0, 4)
   		     $('.drugAdvicesInstructionsModalBtn').click(function(){ 
   			   $('#drugAdvicesInstructionsModal .modal-body p').text($(this).attr('drugInstructions'));
   		  	 });
   		     $('.drugEditBtn').click(function(){
			    if($('input[name=drugName]').val()!='')
			    	return false;
		 		var drugName = $(this).parent().parent().find('td').eq(1).text();
		 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
		 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
		 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
		 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
		 		var drugQuantity = $(this).parent().parent().find('td').eq(6).text(); 
		 		var instructions = $(this).parent().parent().find('td').eq(7).text(); 
		 		
		 		$(this).parent().parent().remove();
		 		$('input[name=drugName]').val(drugName);
		 		/*$('select[name=drugDosage]').val(drugDosage);*/
		 		$('select[name=drugDosage]').val($('select[name=drugDosage] option:contains('+drugDosage+')').val());
		 		$('select[name=drugFrequency]').val($('select[name=drugFrequency] option:contains('+drugFrequency+')').val());
		 		/*$('input[name=drugStartDate]').val(drugStartDate); */
		 		document.getElementById('drugStartDate').valueAsDate = new Date();
		 		$('input[name=drugDays]').val(drugDays);
		 		$('input[name=drugQuantity]').val(drugQuantity);
		 		$('textarea[name=drugInstructions]').val(instructions); 
		 		//$('input[name=drugName]').focus();
			}); 

   		  $('.monogrambtn').click(function(){
	   		  var data1=$(this).parent().parent().find('.checkedInput').val();
	   		var ref_id= (data1.split('#')[3]).split('!')[0] ;
	   		   console.log(data1); 
	   		  
	   		  $.ajax({
	   		  	url: "/HISDRDESK/services/restful/cims/getMonographCimsData",
	   		  	dataType: "text",
	   		  	type: "POST",
	   		  	async: false,
	   		  	 crossDomain:true,
	   		  	data: {
	   		  		data: '"' + ref_id +'"' ,
	   		  		cimstype : (data1.split('#')[5]).split('&&')[0]
	   		  	},
	   		  	success: function(data) {
	   		  		console.log(data);
	   		  		$('#MonographResponse').html(data);
	   		  		$("#CimsMonographId").modal();
	   		  	},
	   		  	error: function(XMLHttpRequest, textStatus, errorThrown) {
	   		  		alert(errorThrown);
	   		  	}
	   		  });
	   		 });
   		  
   		  
   		 $('.Genericbarndbtn').click(function(){

   		  var data1=$(this).parent().parent().find('.checkedInput').val();
	   		var ref_id= (data1.split('#')[2]).split('!')[0] ;

   		  $.ajax({
   		  	url: "http://aiimsmanglagiri.uat.dcservices.in/dis/generic/"+ref_id,
   		  	dataType: "json",
   		  	type: "GET",
   		  	async: false,
   		  	 crossDomain:true, 
   		  	success: function(data) {
   		  		console.log(data);
   		  		/* var myJSON = JSON.stringify(data); */
   		  	 var html1 ='';
   		  		for (var i=0; i<data.length; i++) {
   		  		html1 = '<div class="row"><div class="col-sm-12 genericDesc">' + data[i].genericDescriptions[1].genericName+ '</div></div><div class="row">';
   		        		
   		        		for (var j=0; j<data[i].drugTypes.length; j++) {
   		        			html1 =html1+'<div class="col-sm-12 drugTypeName">' + data[i].drugTypes[j].typeName + '&nbsp; | &nbsp; </div>'  ; 
   		        		}
   		        		
   		        		html1 =html1+'</div><br /><div class="row"><div class="col-sm-6"><strong style="color:blue">Dose Form:&nbsp;&nbsp;</strong> '+ data[i].doseForm +' ';
   		        		html1 =html1+'</div><div class="col-sm-6"><strong style="color:blue">Route Of Administration:&nbsp;&nbsp;</strong>'+data[i].routeOfAdministration + '</div></div>' ;
   		        		html1 =html1+'<br /><div class="row"><div class="col-sm-12"><strong style="color:blue">ContraIndications:&nbsp;&nbsp;</strong>'+ data[i].contraIndications + '' ;
   		        		html1 =html1+'</div></div><br /><div class="row"><div class="col-sm-12"><strong style="color:blue">Indications:&nbsp;&nbsp;</strong>'+ data[i].indications + '</div></div>' ;
   		       
   		        $('#GenericMonogramResponse').append(html1);
   		    }
   		    console.log(html1);

   		    
   		  		$('#GenericMonogramResponse').html(html1);  
   		  		$("#GenericMonogramId").modal();
   		  		
   		  	},
   		  	error: function(XMLHttpRequest, textStatus, errorThrown) {
   		  		//alert(errorThrown);
   		  	    //alert(XMLHttpRequest.responseText);
   		  	alert("Description Not Mapped By SNOMEDCT");
   		  	}
   		  });
   		  
   		 });
   		     
   		     
			 $(this).parent().parent().parent().find('input[name=drugName]').val(''); 
	 		 $('input[name=flexdatalist-drugName]').css('background-color','#FFFFFF');
			 $(this).parent().parent().find('select[name=drugDosage]').val('0');   
			 $(this).parent().parent().find('select[name=drugFrequency]').val('0');   
			/* $(this).parent().parent().find('input[name=drugStartDate]').val('');*/
		 	 document.getElementById('drugStartDate').valueAsDate = new Date();
			 $(this).parent().parent().find('input[name=drugDays]').val('');
			 $(this).parent().parent().find('input[name=drugQuantity]').val('');
			 $(this).parent().parent().find('input[name=drugInstructions]').val('');
			 
			 $('#drugDosageId').html(doaseCmbValue);
  	 		}
  	 		else
  	 			swal('Please select drug name from list');
 			} 
 		else
 		 swal('Please fill all mandatory fields');
 	});
 	
 	$("textarea[name=drugInstructions]").on('keypress', function (e) {
 		if (!e) e = window.event;
 	    var keyCode = e.keyCode || e.which;
 	    if (keyCode == '13'){ 
 	    	var drugName = $(this).parent().parent().parent().find('input[name=drugName]').val();
 	 		 var drugId = '';
 	 		 var isValid = 0;
 		 		$.each(JSON.parse(localStorage.getItem('drugJsonObj')), function(i, v) {
 				    if (v.drugName.toUpperCase() == drugName.toUpperCase()) {
 				        console.log(v.drugId+'::::::::>>>>'+v.drugName); 
 				 	    drugId = v.drugId;
 				 	    isValid=1;
 				        return;
 				    } 
 				 });
 	 		 var drugDosage = $(this).parent().parent().find('select[name=drugDosage] option:selected').text();
 	 		 var drugDosageID = $(this).parent().parent().find('select[name=drugDosage] option:selected').val().split('^')[0];
 	 		 var drugFrequencyId = $(this).parent().parent().find('select[name=drugFrequency]').val();
 	 		 var drugFrequency = $(this).parent().parent().find('select[name=drugFrequency] option:selected').text(); 
 	 		 var drugStartDate = $(this).parent().parent().find('input[name=drugStartDate]').val();
 	 		 var drugDays = $(this).parent().parent().find('input[name=drugDays]').val(); 
 	 		 var drugQuantity = $(this).parent().parent().find('input[name=drugQuantity]').val();
 	 		 var drugInstructions = $(this).val();
 	 		 
 	 		var tmp = 0; 
 			$('#drugAdviceListTable tbody').find('tr').each(function(index){
 				if($(this).find('td').eq(0).children('input').val().split('&&')[1].split('#')[0]==drugId.trim().split('#')[0]) 
 				{	tmp = 1; 
 					return false;  }
 			});
 			if(tmp==1)
			{ 
				swal("Already Added!!");
				 $(this).parent().parent().parent().find('input[name=drugName]').val(''); 
				 $(this).parent().parent().find('select[name=drugDosage]').val('');   
				 /*$(this).parent().parent().find('input[name=drugStartDate]').val('');*/
			 	 document.getElementById('drugStartDate').valueAsDate = new Date();
				 $(this).parent().parent().find('input[name=drugDays]').val('');  
				 $(this).parent().parent().find('input[name=drugQuantity]').val(''); 
				 $(this).parent().parent().find('textarea[name=drugInstructions]').val('');
				return false;
			} 
 	 		if($(this).parent().parent().find('input[name=drugName]').val().trim()!='' &&  $(this).parent().parent().find('select[name=drugDosage]').val().trim()!=''&& $(this).parent().parent().find('input[name=drugDays]').val().trim()!='') // && $(this).val().trim()!=''     
 	 		{   
 	 		if(isValid==1)
 	 		{ 
 	 			
 	 		  var HiddenDrugAdvice=drugName+'&&'+drugId+'&&'+drugDosage+'&&'+drugDosageID+'&&'+drugFrequency+'&&'+drugFrequencyId+'&&'+drugStartDate+'&&'+drugDays+'#'+drugQuantity+'&&'+drugInstructions; //drugInstructions
	 		 
 	 		var DrugJson ={
 					"IsExterNal"	:		"0" ,	
 					"DrugName"		 :	 drugName ,
 					"DrugCode"		 :	 drugId ,
 					"DoaseName"		:	drugDosage ,
 					"DoaseCode"		:	drugDosageID ,
 					"FrequencyName"	:	drugFrequency ,
 					"FrequencyCode" :	drugFrequencyId ,
 					"StartDate"		:	drugStartDate ,
 					"DrugDays"		:	drugDays ,
 					"DrugQuantity"	:	drugQuantity ,
 					"DrugInstruction" :	drugInstructions
 			}
 			
 			console.log(JSON.stringify(DrugJson));
 	 		  
 	 		  $(this).parent().parent().parent().find('.table').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+HiddenDrugAdvice+'"  checked><i class="text1" style="display :none">'+JSON.stringify(DrugJson)+' </i></td><td>'+drugName+'</td><td>'+drugDosage+'</td><td>'+drugFrequency+'</td><td>'+drugStartDate+'</td><td>'+drugDays+'</td><td>'+drugQuantity+'</td><td><a class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+drugInstructions+'" onclick="$(\'#drugAdvicesInstructionsModal\').modal(\'show\');">'+drugInstructions.substring(0, 4)+'..'+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td><td><button class="btn btn-xs monogrambtn" type="button" data-toggle="modal" data-target="CimsMonographId"><i class="fas fa-capsules" style="color: Crimson;"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs Genericbarndbtn" type="button"  data-toggle="modal" data-target="GenericMonogramId"><i class="fas fa-tablets" style="color: DeepSkyBlue;"></i></button></td></tr>');// drugInstructions   drugInstructions.substring(0, 4)
	 		  $('.drugAdvicesInstructionsModalBtn').click(function(){
	 			console.log('drugInstructions:::>>>'+$(this).attr('drugInstructions'));
	 			$('#drugAdvicesInstructionsModal .modal-body p').text($(this).attr('drugInstructions'));
	 		  });
	   		  $('.drugEditBtn').click(function(){
				    if($('input[name=drugName]').val()!='')
				    	return false;
			 		var drugName = $(this).parent().parent().find('td').eq(1).text();
			 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
			 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
			 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
			 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
			 		var drugQuantity = $(this).parent().parent().find('td').eq(6).text(); 
			 		var instructions = $(this).parent().parent().find('td').eq(7).text(); 
			 		
			 		$(this).parent().parent().remove();
			 		$('input[name=drugName]').val(drugName);
			 		/*$('select[name=drugDosage]').val(drugDosage);*/
			 		$('select[name=drugDosage]').val($('select[name=drugDosage] option:contains('+drugDosage+')').val());
			 		$('select[name=drugFrequency]').val($('select[name=drugFrequency] option:contains('+drugFrequency+')').val());
			 		/*$('input[name=drugStartDate]').val(drugStartDate);*/
				 	document.getElementById('drugStartDate').valueAsDate = new Date(); 
			 		$('input[name=drugDays]').val(drugDays); 
			 		$('input[name=drugQuantity]').val(drugQuantity); 
			 		$('textarea[name=drugInstructions]').val(instructions); 
			 		//$('input[name=drugName]').focus();
				}); 
	
				 $(this).parent().parent().parent().find('input[name=drugName]').val(''); 
		 		 $('input[name=flexdatalist-drugName]').css('background-color','#FFFFFF');
				 $(this).parent().parent().find('select[name=drugDosage]').val('');   
				 /*$(this).parent().parent().find('input[name=drugStartDate]').val('');*/
			 	 document.getElementById('drugStartDate').valueAsDate = new Date();
				 $(this).parent().parent().find('input[name=drugDays]').val('');  
				 $(this).parent().parent().find('input[name=drugQuantity]').val('');
				 $(this).parent().parent().find('textarea[name=drugInstructions]').val('');
 	 			}
  		     else
	 			swal('Please select from list');
 	 		 }
 	 		else
 	 			{
	 			 swal('Please fill all mandatory fields'); 
	 			/* $(this).parent().parent().find('input[value=""]').focus(); */
 	 			}
 	      return true;
 	    }
	});
 	
 		$('.externalDrugsAdviceAdd').click(function(){
 		 var drugName1 = $(this).parent().parent().parent().find('input[name=externalDrugName]').val();
 		var drugName =  drugName1.toUpperCase(); 
 		 //var drugDosage = $(this).parent().parent().find('select[name=externalDrugDosage] option:selected').text();
 		 var drugDosage1 = $(this).parent().parent().find('select[name=externalDrugDosage] option:selected').text();
 		 if(drugDosage1 =='Select')
 			drugDosage ='--';
 		 else
 			drugDosage=drugDosage1;
 		 
 		 var drugDosageID = $(this).parent().parent().find('select[name=externalDrugDosage] option:selected').val().split('^')[0];
 		 var drugFrequencyId = $(this).parent().parent().find('select[name=externalDrugFrequency]').val();
 		 //var drugFrequency = $(this).parent().parent().find('select[name=externalDrugFrequency] option:selected').text();
 		 
 		var drugFrequency1 = $(this).parent().parent().find('select[name=externalDrugFrequency] option:selected').text();
		 if(drugFrequency1 =='Select')
			drugFrequency='--';
		 else
			drugFrequency=drugFrequency1;
		 
 		// alert(drugFrequency+drugFrequencyId);
 		 var drugStartDate = $(this).parent().parent().find('input[name=externalDrugStartDate]').val();
 		 var drugDays = $(this).parent().parent().find('input[name=externalDrugDays]').val();   
 		 var externalDrugQuantity = $(this).parent().parent().find('input[name=externalDrugQuantity]').val(); 
 		 var drugInstructions = $(this).parent().parent().find('input[name=externalDrugInstructions]').val();
 		 
 		 if(drugName.trim()!='' ) //&& drugInstructions.trim()!='' && drugDosage.trim()!='' && drugStartDate.trim()!='' && drugDays.trim()!=''
 		 {  
 			console.log('drugDays::::::::'+drugDays);
 			drugDays= (drugDays=='') ? '0' : drugDays ;
	 			console.log('drugDays::::::::'+drugDays);
	 			externalDrugQuantity= (externalDrugQuantity == '') ? '0' :externalDrugQuantity;
	 			
	 			
 			var HiddenDrugAdvice=drugName+'(ext)'+'&&'+'100#100#100#100#100'+'&&'+drugDosage+'&&'+drugDosageID+'&&'+drugFrequency+'&&'+drugFrequencyId+'&&'+drugStartDate+'&&'+drugDays+'#'+externalDrugQuantity+'&&'+drugInstructions; //drugInstructions
	 		
 			
 			var DrugJson ={
 					"IsExterNal"	:		"1" ,	
 					"DrugName"		 :	 drugName+'(ext)' ,
 					"DrugCode"		 :	 "100#100#100#100#100" ,
 					"DoaseName"		:	drugDosage ,
 					"DoaseCode"		:	drugDosageID ,
 					"FrequencyName"	:	drugFrequency ,
 					"FrequencyCode" :	drugFrequencyId ,
 					"StartDate"		:	drugStartDate ,
 					"DrugDays"		:	drugDays ,
 					"DrugQuantity"	:	externalDrugQuantity ,
 					"DrugInstruction" :	drugInstructions
 					
 			}
 			
 			// alert(HiddenDrugAdvice);
	 		 /*$(this).parent().parent().parent().find('.drugsAdvicesAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="drugsAdvices" checked> '+drugName+' '+drugDosage+' '+drugFrequency+'</label></a>');*/
/*	 		 $(this).parent().parent().parent().parent().find('.table').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+HiddenDrugAdvice+'"  checked></td><td>'+drugName+'</td><td>'+drugDosage+'</td><td>'+drugFrequency+'</td><td>'+drugStartDate+'</td><td>'+drugDays+'</td><td><a style="color: #109f1c" data-toggle="modal" data-target="#drugAdvicesInstructionsModal">'+drugInstructions+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td></tr>');*/
   		     $(this).parent().parent().parent().parent().find('#drugAdviceListTable').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+HiddenDrugAdvice+'"  checked><i class="text1" style="display :none">'+JSON.stringify(DrugJson)+' </i></td><td>'+drugName+'</td><td>'+drugDosage+'</td><td>'+drugFrequency+'</td><td>'+drugStartDate+'</td><td>'+drugDays+'</td><td>'+externalDrugQuantity+'</td><td><a class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+drugInstructions+'" data-toggle="modal" data-target="#drugAdvicesInstructionsModal">'+drugInstructions.substring(0, 4)+'..'+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td></tr>'); // drugInstructions   drugInstructions.substring(0, 4)
   		     $('.drugAdvicesInstructionsModalBtn').click(function(){ 
   			   $('#drugAdvicesInstructionsModal .modal-body p').text($(this).attr('drugInstructions'));
   		  	 });
   		  $('.drugEditBtn').click(function(){
		 		var drugName = $(this).parent().parent().find('td').eq(1).text();
		 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
		 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
		 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
		 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
		 		var externalDrugQuantity = $(this).parent().parent().find('td').eq(6).text();   
		 		var instructions = $(this).parent().parent().find('td').eq(7).text(); 
		 		
		 		$(this).parent().parent().remove();
		 		$('input[name=externalDrugName]').val(drugName);
		 		/*$('input[name=externalDdrugDosage]').val(drugDosage);*/
		 		$('select[name=externalDdrugDosage]').val($('select[name=externalDdrugDosage] option:contains('+drugDosage+')').val());
		 		$('select[name=externalDrugFrequency]').val($('select[name=externalDrugFrequency] option:contains('+drugFrequency+')').val());
		 		/*$('input[name=externalDrugStartDate]').val(drugStartDate);*/
		 		document.getElementById('externalDrugStartDate').valueAsDate = new Date();
		 		$('input[name=externalDrugDays]').val(drugDays); 
		 		$('input[name=externalDrugQuantity]').val(externalDrugQuantity);   
		 		$('input[name=externalDrugInstructions]').val(instructions); 
		 		//$('input[name=externalDrugName]').focus();
			}); 
	
			 $(this).parent().parent().parent().find('input[name=externalDrugName]').val(''); 
			 $(this).parent().parent().find('select[name=externalDrugDosage]').val('0');   
			 $(this).parent().parent().find('select[name=externalDrugFrequency]').val('0');   
			 /*$(this).parent().parent().find('input[name=externalDrugStartDate]').val('');*/
			 document.getElementById('externalDrugStartDate').valueAsDate = new Date();
			 $(this).parent().parent().find('input[name=externalDrugDays]').val('');
			 $(this).parent().parent().find('input[name=externalDrugQuantity]').val('');
			 $(this).parent().parent().find('input[name=externalDrugInstructions]').val(''); 
 		 }
 		 else
 			 swal('Please fill all mandatory fields');
 	});

/* 	$('.drugEditBtn').click(function(){
 		var drugName = $(this).parent().parent().find('td').eq(1).text();
 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
 		var drugInstructions = $(this).parent().parent().find('td').eq(6).text(); 
 		$(this).parent().parent().remove();
 		$('input[name=drugName]').val(drugName);
 		$('select[name=drugDosage]').val(drugDosage);
 		$('select[name=drugFrequency]').val(drugFrequency);
 		$('input[name=drugStartDate]').val(drugStartDate);
 		$('input[name=drugDays]').val(drugDays);
 		$('textarea[name=drugInstructions]').val(drugInstructions);
 		$('input[name=drugName]').focus();

 	});*/

 	$('input[name=drugsAdvicesAll]').on('change',function(){   
 		if($(this).is(':checked'))
 		{ 
 			$(this).parent().parent().parent().parent().find('.checkedInput').prop('checked',true);
 		}
 		else
 		{	 
 			$(this).parent().parent().parent().parent().find('.checkedInput:checked').prop('checked',false);
 		}
 	});
 	
 	/*$('input[name=investigation]').on('input',function(){
 		var investigation = $(this).val();
 		if($('#investigationLstTest option').filter(function(){
	 		   return this.value === investigation;        
	 		  }).length) 
 			{
				$(this).removeAttr('style');
 				return true;
 			}
 		else
 			{
 				$(this).css('background-color','#FBE2E2');
 			} 
 	});*/
 	$('input[name=investigation]').on('change:flexdatalist',function(){
 		var investigation = $(this).val(); 
 		let f = 0;
 		var invObj = localStorage.getItem('testJsonObj'); 
 		invObj = JSON.parse(invObj); 
 		for(var v=0; v<invObj.length;v++)
 		{ 
 			if (invObj[v].testName.toUpperCase() == investigation.toUpperCase()) {
		        console.log(invObj[v].testName+'::::::::>>>>'+invObj[v].testId);
		        f=1;
		        break;
		    } 
 		} 
 		if(f==1)
 			$('input[name=flexdatalist-investigation]').css('background-color','#FFFFFF');
 		else 
 			$('input[name=flexdatalist-investigation]').css('background-color','#FBE2E2'); 
 	});
 	
 	$('input[name=clinicalProcedureName]').on('change:flexdatalist',function(){
 		var investigation = $(this).val(); 
 		let f = 0;
 		var invObj = localStorage.getItem('ClinicalProcedureObj'); 
 		invObj = JSON.parse(invObj); 
 		for(var v=0; v<invObj.length;v++)
 		{ 
 			if (invObj[v].testName.toUpperCase() == investigation.toUpperCase()) {
		        console.log(invObj[v].testName+'::::::::>>>>'+invObj[v].testId);
		        f=1;
		        break;
		    } 
 		} 
 		if(f==1)
 			$('input[name=flexdatalist-clinicalProcedureName]').css('background-color','#FFFFFF');
 		else 
 			$('input[name=flexdatalist-clinicalProcedureName]').css('background-color','#FBE2E2'); 
 	});
 	
 	
 	$('.investigationAdd').click(function(){
 		
		 var investigationVAl = $(this).parent().parent().find('input[name=investigation]').val();  
	 	 var isValid = 0;
	 	 var invObj = localStorage.getItem('testJsonObj'); 
 		 invObj = JSON.parse(invObj.toString()); 
 		 for(var v=0; v<invObj.length;v++)
 		 { 
 			if (invObj[v].testName.toUpperCase() == investigationVAl.toUpperCase()) {
 				isValid=1;
		        break;
		    } 
 		  } 
 		 if(investigationVAl.trim()=='' &&  $('input[name=externalInvestigation]').val().trim() =='') 
		{
		  swal('Please enter investigation to be added');
		  return false; 
		}
	 
	  if(isValid==1)
	  {  
		    var tmp = 0; 
			$('.investigationAdded').find('label .text').each(function(index){ 
				if($(this).text().trim().toUpperCase()===investigationVAl.trim().toUpperCase()) 
				{	tmp = 1; 
					return false;  }
			});
			if(tmp==1)
			{
				swal("Already Added!!");
				return false;
			}
			if(isValid==1) {   
			 var investigationVAl1='';
		 	 var invObj = localStorage.getItem('testJsonObj'); 
	 		 invObj = JSON.parse(invObj.toString()); 
			 for(var v=0; v<invObj.length;v++)
	 		 { 
	 			if (invObj[v].testName.toUpperCase() == investigationVAl.toUpperCase()) {
	 				investigationVAl1 = invObj[v].testId;  /*+'^'+$("#investigationSiteId").val()+'^'+$("#investigationRemarksId").val();;*/ 
			        break;
			    } 
	 		  }   
 		//$(this).parent().parent().parent().find('.investigationAdded').append('<a style="padding-left: 5px;" class="'+investigationVAl1.trim().split('^')[4].trim().split(' ').join('_')+'"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+investigationVAl1+'"  checked> '+investigationVAl+'</label></a>');
			 var siteId=$(this).parent().parent().find('select[name="investigationSite"] option:selected').text();
			 var InvestigationDtlsJson = {
					 "IsExternal"   :   "0" ,
					 "TestName" 	:   investigationVAl ,
		    		 "TestCode"		:	investigationVAl1.split('^')[0] ,
		    		 "LabCode"		:	investigationVAl1.split('^')[1] ,
		    		 "SampleCode"	:	investigationVAl1.split('^')[2] ,
		    		 "SampleName"	:	investigationVAl1.split('^')[3] ,
		    		 "LabName"		:	investigationVAl1.split('^')[4] ,
		    		 "IsTestGroup"	:	investigationVAl1.split('^')[6] ,
		    		 "SideCode"		:	$("#investigationSiteId").val() ,
		    		 "SideName"		:	siteId ,
		    		 "SideRemarks"	:	$("#InvestigationRemarksId").val()
		     }
			 var sideremks=' ';
			 if($("#investigationSiteId").val() !='' && $("#investigationSiteId").val() !='0')
				 sideremks = sideremks + '('+ siteId + ')' ;
			 if($("#InvestigationRemarksId").val() != '')
				 sideremks = sideremks + ' ('+ $("#InvestigationRemarksId").val() +')' ;
			 
			 console.log(JSON.stringify(InvestigationDtlsJson));
			 
 		$(this).parent().parent().parent().find('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs '+investigationVAl1.trim().split('^')[4].trim().split(' ').join('_')+'">'+
 		 		'<input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+investigationVAl1+'" checked="">  '+
 		 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
 		 		'<span class="text text1">'+investigationVAl + sideremks +' </span>'+
 		 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 		 		'</button></label>');
 		 
 		tippy('.'+investigationVAl1.trim().split('^')[4].trim().split(' ').join('_'), {
            content: investigationVAl1.trim().split('^')[4].trim(),
            delay: 50,
            arrow: true,
            arrowType: 'round',
            size: 'medium',
            duration: 500,
            animation: 'scale'
        });
     	 $('input[name=investigation]').val(''); 
     	 $(this).parent().parent().find('select[name=investigationSite]').val('0');
		 $(this).parent().parent().find('#InvestigationRemarksId').val('');
     	 $('input[name=flexdatalist-investigation]').css('background-color','#ffffff');
			}
		else
			{ 
		    	 $('input[name=investigation]').val(''); 
				 swal('Please select test from list');
		     	 $('input[name=flexdatalist-investigation]').css('background-color','#ffffff');
			} 
	  }
	  else{
		  var tmp = 0; 
			$('.investigationAdded').find('label .text').each(function(index){ 
				if($(this).text().split('*')[0].trim().toUpperCase()===$('input[name=investigation]').val().trim().toUpperCase()) 
				{	tmp = 1; 
					return false;  }
			});
			if(tmp==1)
			{
				swal("Already Added!!");
				 $('input[name=investigation]').val(''); 
				return false;
			}
		  //$(this).parent().parent().parent().find('.investigationAdded').append('<a style="padding-left: 5px;" class="'+$('input[name=externalInvestigation]').val().trim()+'"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="0^0^0^bld"  checked> '+$('input[name=externalInvestigation]').val().trim()+'</label></a>');
			 var siteId=$(this).parent().parent().find('select[name="investigationSite"] option:selected').text();
			 var InvestigationDtlsJson = {
					 "IsExternal"   :   "1" ,
					 "TestName" 	:   $('input[name=investigation]').val().trim() ,
		    		 "TestCode"		:	"0" ,
		    		 "LabCode"		:	"0" ,
		    		 "SampleCode"	:	"0" ,
		    		 "SampleName"	:	"0" ,
		    		 "LabName"		:	"0",  
		    		 "IsTestGroup"	:	"0"	,
		    		 "SideCode"		:	$("#investigationSiteId").val() ,
		    		 "SideName"		:	siteId ,
		    		 "SideRemarks"	:	$("#InvestigationRemarksId").val()
		     }
			 console.log(JSON.stringify(InvestigationDtlsJson));
			 var sideremks=' ';
			 if($("#investigationSiteId").val() !='' && $("#investigationSiteId").val() !='0')
				 sideremks = sideremks + '('+ siteId + ')' ;
			 if($("#InvestigationRemarksId").val() != '')
				 sideremks = sideremks + ' ('+ $("#InvestigationRemarksId").val() +')' ;
			
		  $(this).parent().parent().parent().find('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs '+$('input[name=investigation]').val().trim()+'">'+
	    	 		'<input type="checkbox" class="checkedInput" name="reasonOfVisit" value="0^0^0^0^" checked="">  '+
	    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
	    	 		'<span class="text text1">'+$('input[name=investigation]').val().trim()+ sideremks +'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
	    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	    	 		'</button></label>');
		  $('input[name=investigation]').val(''); 
		  $(this).parent().parent().find('select[name=investigationSite]').val('0');
			 $(this).parent().parent().find('#InvestigationRemarksId').val('');
		/*  swal('Please enter investigation to be added');*/
	  }
 	});
 	
 	/* On key Press Enter */
 	
 	
 	

 	$('.externalInvestigationAdd').click(function(){
		 var investigationVAl = $(this).parent().parent().parent().find('input[name=externalInvestigation]').val(); 
	  if(investigationVAl.trim()!='')
	  {  
		    var tmp = 0; 
			$('.investigationAdded').find('label').each(function(index){ 
				if($(this).text().split("(")[0].trim().toUpperCase()===investigationVAl.trim().toUpperCase()) 
				{	tmp = 1; 
					return false;  }
			});
			if(tmp==1)
			{
				swal("Already Added!!");
				return false;
			}
		  
		var investigationVAl1 = '0^0^0^0^0';
 		// alert(investigationVAl1);
 		 $(this).parent().parent().parent().find('.investigationAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+investigationVAl1+'"  checked> '+investigationVAl+'<sup style="color:red; font-weight:bold;">*</sup></label></a>');

		 $(this).parent().parent().parent().find('input[name=externalInvestigation]').val(''); 
	  }
	  else{
		  swal('Please enter external investigation to be added');
	  }
 	});
 	
 	$("input[name=externalInvestigation]").on('keypress', function (event) {
 		if (event.which != 13) 
 	     return true;
	     
	    var val = this.value;
	    console.log('val:::::::><>>>>'+val);
	    var tmp = 0; 
		$('.investigationAdded').find('label .text').each(function(index){ 
			if($(this).text().trim().toUpperCase()===val.trim().toUpperCase()) 
			{	tmp = 1; 
				return false;  }
		});
		if(tmp==1)
		{
			swal("Already Added!!");
			$(this).val('');
			return false;
		} 
	    	var investigationVAl1 = '0^0^0^0^0';
	    	
	    	 var InvestigationDtlsJson = {
					 "IsExternal"   :   "1" ,
					 "TestName" 	:   $('input[name=externalInvestigation]').val().trim() ,
		    		 "TestCode"		:	"0" ,
		    		 "LabCode"		:	"0" ,
		    		 "SampleCode"	:	"0" ,
		    		 "SampleName"	:	"0" ,
		    		 "LabName"		:	"0",  
		    		 "IsTestGroup"	:	"0"	
		     }
	    	 console.log(JSON.stringify(InvestigationDtlsJson));
	    	//$(this).parent().parent().parent().parent().find('.investigationAdded').append('<a style="padding-left: 5px;" class="'+investigationVAl1.trim().split('^')[4].trim().split(' ').join('_')+'"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+investigationVAl1+'"  checked> '+val+'</label></a>');
	    	
	    	$(this).parent().parent().parent().parent().find('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs '+$('input[name=externalInvestigation]').val().trim()+'">'+
	    	 		'<input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+investigationVAl1+'" checked="">  '+
	    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
	    	 		'<span class="text text1">'+$('input[name=externalInvestigation]').val().trim()+'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
	    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	    	 		'</button></label>');
	    	
	    	$(this).val(''); 
	});
 	


 	$('.investigationBookmarkAddBtn').click(function(){
		 $(this).parent().parent().find('input[name=bookmarkSubTest]:checked').each(function(index){
		     var subTestName = $(this).parent().text();  
			    var tmp = 0; 
				$('.investigationAdded').find('label .text').each(function(index){ 
					if($(this).text().trim().toUpperCase()===subTestName.trim().toUpperCase()) 
					{	tmp = 1; 
						//return false;
						}
				});
				if(tmp==1)
					{
					}//return false;
				else{
		     var subTestNameVal = $(this).val(); 
			     console.log('subTestName:::'+subTestName+':::subTestNameVal:::'+subTestNameVal);
			     
			     var InvestigationDtlsJson = {
			    		 "IsExternal"   :   "0" ,
						 "TestName" 	:   subTestName ,
			    		 "TestCode"		:	subTestNameVal.split('^')[0] ,
			    		 "LabCode"		:	subTestNameVal.split('^')[1] ,
			    		 "SampleCode"	:	subTestNameVal.split('^')[2] ,
			    		 "SampleName"	:	subTestNameVal.split('^')[3] ,
			    		 "LabName"		:	subTestNameVal.split('^')[4] ,
			    		 "IsTestGroup"	:	subTestNameVal.split('^')[6] 
			     }
			     console.log(JSON.stringify(InvestigationDtlsJson));
			     
				 //$('.investigationAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+subTestNameVal+'"  checked> '+subTestName+'</label></a>');
				 
				 $('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
			    	 		'<input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+subTestNameVal+'" checked="">  '+
			    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
			    	 		'<span class="text text1">'+subTestName+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
				}
 	 	});
		 $(this).parent().parent().parent().find('.close').click();
		 
 	}); 

 	$('.investigationTestTriggerBtn').click(function(){
 		 var testName = $(this).text(); 
 		 $('#investigationTestBundle .bookmarkTestName').text(testName); 
 	});

 	$('input[name=investigationSearchOn]').on('change',function(){
 		if($(this).val()==2)
 			$('input[name=investigation]').attr('list','investigationLstTestCode');
 		else
 			$('input[name=investigation]').attr('list','investigationLstTest');

 	});

 	$('input[name=bookmarksToAdd]').on('change', function(){
 		if($(this).val() == 1)
 		{
	 		$(this).parent().parent().parent().parent().find('.LabWiseTestButtons').slideUp();
			$(this).parent().parent().parent().parent().find('.AddToBookMarkButtons').slideDown();
 	 	}
 		else
 		{
			$(this).parent().parent().parent().parent().find('.AddToBookMarkButtons').slideUp();
	 		$(this).parent().parent().parent().parent().find('.LabWiseTestButtons').slideDown();
 	 	}
 	 });

 	$('.nextPatientPresc').click(function(e){
 			var maxLength = $('.patientListBlock').length;
		    console.log('maxLength:::'+maxLength+'::: Count :::'+count);
			if(count>=(maxLength-1)){ 
				return false;
			}
 			var prescMode = $('input[name=prescMode]:checked').val(); 
		    console.log('NextLink Click PrescVal : '+prescMode);
 			if(prescMode==1)
			{	 
				$('.prescriptionColBtn').eq(++count).click();
			}
			else if(prescMode==2)
			{   
		    	console.log('NextLink count: '+(++count));
 				hidePrescription(e);  
				$('.prescriptionColBtn').eq(count).click();
			}
			else{
				 var effect = function() {
						  return $('.prescModalCloseBtn').click();
						};   
						$.when( effect() ).done(function() {
				 			$('.prescriptionColBtn').eq(++count).click();
							  }); 
			}
 		}); 
			var confirmHandler = function(){
				confirm('Are you sure to save the data');
			}; 
		if($('input[name=autoSave]').is(':not(:checked)'))
			$('.nextPatientPresc').on('click', confirmHandler); 
		$('input[name=autoSave]').on('change', function(){
			if($(this).is(':checked'))
				$('.nextPatientPresc').unbind('click', confirmHandler);
			else
				$('.nextPatientPresc').bind('click', confirmHandler);   
		});
		

	 	$('.prevPatientPresc').click(function(e){
	 			var maxLength = 1;
			    console.log('maxLength:::'+maxLength+'::: Count :::'+count);
				if(count<=(maxLength-1)){ 
					return false;
				}
	 			var prescMode = $('input[name=prescMode]:checked').val(); 
			    console.log('NextLink Click PrescVal : '+prescMode);
	 			if(prescMode==1)
				{	 
					$('.prescriptionColBtn').eq(--count).click();
				}
				else if(prescMode==2)
				{   
			    	console.log('NextLink count: '+(--count));
	 				hidePrescription(e);  
					$('.prescriptionColBtn').eq(count).click();
				}
				else{
					 var effect = function() {
							  return $('.prescModalCloseBtn').click();
							};   
							$.when( effect() ).done(function() {
					 			$('.prescriptionColBtn').eq(--count).click();
								  }); 
				}
	 		}); 

		$('input[name=diagnosisDiseaseReference]').on('change',function(){
			if($(this).is(':checked'))
			{
				$('.ICDCodeDiseaseView').slideUp();
				$('.snomedCtDiseaseView').slideDown();
			}
			else
			{ 
				$('.snomedCtDiseaseView').slideUp();
				$('.ICDCodeDiseaseView').slideDown();
			}
		});

		/*$('input[name=icdCodeInput]').change(function(){ 
				$(this).parent().parent().find('input[name=diseaseInput]').val('Colera due to Vibrio cholerae 01.'); 
		});*/
		
		$("input[name=icdCodeInput]").on('select:flexdatalist', function (e, set, options) {
		    var val = set.icdCode;
		    /*if($('#ICDcodeLst option').filter(function(){
		        return this.value === val;        
		    }).length) {
		    	var diseaseInputVAl1 = $(this).parent().find('#ICDcodeLst option[value="'+val+'"]').attr('id');*/  
		    	var diseaseInputVAl1 = set.diagnosisName;
		    	$(this).parent().parent().parent().find('input[name=diseaseInput]').val(diseaseInputVAl1); 

		     	//$("input[name=flexdatalist-diseaseInput]").on('keypress', function (e) {
		     		console.log('15635555...............');
		     		if (!e) e = window.event;
		     	    var keyCode = e.keyCode || e.which;
		     	    console.log(keyCode)
		     	    if (keyCode == '13'){
		     	    	console.log('2');	

		     	 		if($('input[name=diagnosisDiseaseReference]').is(':checked'))
		     	 		{
		     	 			if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim().indexOf(';')>='0')
		     	 	 		{
		     	 				if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().trim()!='')
		     	 		 		{  var diagnosisVAl = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val().split(';');
		     	 			 	   var diagnosisCode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode').split(';');
		     	 			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
		     	 			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
		     	 			 	   console.log("diagnosisVAl -->> "+diagnosisVAl);
		     	 			 	   console.log("diagnosisCode --->> "+diagnosisCode);
		     	 			 	  console.log('diagnosisType ::: ::::: :::: '+diagnosisTypeCode+diagnosisTypeName);
		     	 		 		   for(var i=0;i<(diagnosisVAl.length-1);i++)
		     	 		 		   	{
		     	 		 			  var tmp = 0; 
		     	 		 				$('.diagnosisAdded').find('label .text').each(function(index){ 
		     	 		 					if($(this).text().trim().toUpperCase()===diagnosisVAl[i].trim().toUpperCase()) 
		     	 		 					{	tmp = 1; 
		     	 		 						return false;  }
		     	 		 				});
		     	 		 				if(tmp==1)
		     	 						{ 
		     	 							swal(diagnosisVAl[i].trim()+", Already Added!!");
		     	 							continue;
		     	 						}
		     	 		 				var strDiagnosisId=diagnosisCode[i]+'#'+diagnosisTypeCode+'#1^'+diagnosisVAl[i]+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteId").val()+'#'+$('input[name=diagnosisNoOfDays]').val()+'#'+$("#diagnosisDurationId").val()+'#'+$("#diagnosisRemarksId").val();
		     	 				 		//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+diagnosisVAl[i]+'</label></a>');
		     	 				 		
		     	 		 				var temp=''; 
		     	 		 				if($("#diagnosisSiteId").val()!= 0 && diagnosisTypeCode !=0)
		     	 		 					temp=diagnosisTypeName +'['+ $("#diagnosisSiteId option:selected").text() +']';
		     	 		 				else if($("#diagnosisSiteId").val()!= 0)
		     	 		 					temp=+'['+ $("#diagnosisSiteId option:selected").text() +']';
		     	 		 				else if(diagnosisTypeCode !=0 )
		     	 		 					temp=diagnosisTypeName ;
		     	 		 				
		     	 		 				if($("#diagnosisRemarksId").val() !=''){
		     	 		 					temp= temp+ $("#diagnosisRemarksId").val() ;
		     	 		 				}
		     	 		 				var DiagnosisJson ={
		     	 		 						"IsSnomed"				:			"1" ,
		     	 								"DiagnosisName" 		: 		 	diagnosisVAl[i],
		     	 								"DiagnosisCode" 		:			diagnosisCode[i],
		     	 								"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
		     	 								"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
		     	 								"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
		     	 								"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
		     	 								"DiagnosisRemarks"	:			$("#diagnosisRemarksId").val()
		     	 							};
		     	 							
		     	 		 				console.log(JSON.stringify(DiagnosisJson));
		     	 		 				
		     	 				 		$('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs" title='+temp+' data-toggle="tooltip" >'+
		     	 				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
		     	 				    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
		     	 				    	 		'<span class="text">'+diagnosisVAl[i]+' </span>'+
		     	 				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
		     	 				    	 		'</button></label>');
		     	 		 		   	}
		     	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').val('');
		     	 			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('diagnosisCode','');
		     	 			 		$(this).parent().parent().find('select[name=diagnosisSite] ').val('0');
		     	 			 		$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
		     	 			 		$(this).parent().parent().find('textarea[name=diagnosisRemarks]').val('');
		     	 			 		 
		     	 		 		 }
		     	 				else
		     	 		 			 swal('Please Select atleast one reason');
		     	 	 		}
		     		 		else
		     		 			 swal('Please Select atleast one reason');
		     	 		}
		     	 		else{
		     	 			 var investigationVAl = $(this).parent().parent().find('input[name=icdCodeInput]').val();  
		     	 		 	 var isValid = 0;
		     	 		 	 var invObj = localStorage.getItem('icdJsonObj'); 
		     	 	 		 invObj = JSON.parse(invObj.toString()); 
		     	 	 		 for(var v=0; v<invObj.length;v++)
		     	 	 		 { 
		     	 	 			if (invObj[v].icdCode.toUpperCase() == investigationVAl.toUpperCase()) {
		     	 	 				isValid=1;
		     	 			        break;
		     	 			    } 
		     	 	 		  } 
		     	 			if($(this).parent().parent().find('input[name=diseaseInput]').val().trim()!='')
		     		 		{  
		     	 				var icdCode = $(this).parent().parent().find('input[name=icdCodeInput]').val();
		     	 				var icdCodeVal = $(this).parent().parent().find('input[name=diseaseInput]').val(); 
		     			 	   var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
		     			 	   var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
		     			 	   console.log('icdCodeVal ::: ::::: :::: '+icdCode+icdCodeVal); 
		     			 	   
		     			 	   var tmp = 0; 
		     					$('.diagnosisAdded').find('label .text').each(function(index){ 
		     						if($(this).text().trim().toUpperCase()===icdCodeVal.trim().toUpperCase()) 
		     						{	tmp = 1; 
		     							return false;  }
		     					});
		     					if(tmp==1)
		     					{ 
		     						swal(icdCodeVal.trim()+", Already Added!!"); 
		     					}
		     					else
		     					{					  
		     						var strDiagnosisId=icdCode+'#'+diagnosisTypeCode+'#0^'+icdCodeVal+'#'+diagnosisTypeName+'#'+$("#diagnosisSiteIcdId").val()+'#'+$("#diagnosisNoOfDaysIcdId").val()+'#'+$("#diagnosisDurationIcdId").val()+'#'+$("#diagnosisRemarksIcdId").val();
		     						//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisName="'+diagnosisTypeName+'" value="'+strDiagnosisId+'" checked> '+icdCodeVal+'</label></a>');
		     						
		     						var DiagnosisJson ={
		     			 						"IsSnomed"				:			"2" ,
		     									"DiagnosisName" 		: 		 	icdCodeVal,
		     									"DiagnosisCode" 		:			icdCode,
		     									"DiagnosisSideCode" 	: 			$("#diagnosisSiteId").val() ,
		     									"DiagnosisSideName" 	:			$('#diagnosisSiteId option:selected').text(),
		     									"DiagnosisTypeCode" 	: 	 		diagnosisTypeCode ,
		     									"DiagnosisTypeNamee" 	: 			diagnosisTypeName,
		     									"DiagnosisRemarks"	:			$("#diagnosisRemarksIcdId").val()
		     								};
		     								
		     						console.log(JSON.stringify(DiagnosisJson));
		     						var temptoggle='';
		     						if(diagnosisTypeName != '' ) 
		     							temptoggle =diagnosisTypeName ;
		     						if($('#diagnosisSiteId option:selected').text() !='')
		     							temptoggle =diagnosisTypeName +'['+$('#diagnosisSiteId option:selected').text() +']' ;
		     							
		     						if($("#diagnosisRemarksIcdId").val() !=''){
		     							temptoggle = temptoggle  +$("#diagnosisRemarksIcdId").val() ; 
		     						}
		     						$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" title='+temptoggle+' data-toggle="tooltip" type="button" class="value btn btn-xs">'+
		     				    	 		'<input type="checkbox" diagnosisName="'+diagnosisTypeName+'" class="checkedInput" name="diagnosisAdded" value="'+strDiagnosisId+'" checked="">  '+
		     				    			'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
		     				    	 		'<span class="text">'+icdCodeVal+' </span>'+
		     				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
		     				    	 		'</button></label>');
		     				 	}
		     					 $(this).parent().parent().find('input[name=diseaseInput]').val('');
		     			 		 $(this).parent().parent().find('input[name=icdCodeInput]').val('');  
		     			 		$(this).parent().parent().find('select[name=diagnosisSiteIcd] ').val('0');
		     				 	$(this).parent().parent().find('select[name=diagnosisType] ').val('11');
		     				 	$(this).parent().parent().find('textarea[name=diagnosisRemarksIcd]').val('');
		     				 		
		     		 		 }
		     		 		else
		     		 			 swal('Please Select atleast one reason');
		     	 			 
		     	 			
		     	 			
		     	 			 
		     	 			
		     	 		}
		     	 		$('[data-toggle="tooltip"]').tooltip();
		     	 		
		     	    }
		     	
		    /*}*/
		});
		 
		var CR_No = $('#patCrNoPrescriptionPanel').text();  
		var episodeCode = $('#patEpisodeCodePrescriptionPanel').text();
		var hospitalCode = $('#patHospitalCodePrescriptionPanel').text();
		var lastVisitDate = $('#patLastVisitDatePrescriptionPanel').text();
		var visitNo=$('#patEpisodeVisitNoPrescriptionPanel').text();
		//alert($('#patEpisodeVisitNoPrescriptionPanel').text());
		
		console.log(visitNo);
		console.log('episodeCode::'+episodeCode+'::CR_No::'+CR_No);
		console.log('visitNo:::: '+ visitNo);
		console.log('eTeleConsultancyreq Id'+$('input[name=eTeleconsultancyreq]').val());
		getPrevData(CR_No,episodeCode,hospitalCode,lastVisitDate,visitNo ,$('input[name=eTeleconsultancyreq]').val());
		
		
		$('.followUpPlannedVisitRefresh').click(function(){
			/*$('.followUpPlannedVisit').find('input').removeAttr('disabled');*/
			$('.followUpPlannedVisit').find('input').eq(0).removeAttr('checked');
			$('.followUpPlannedVisit').find('input').eq(1).val('');
			$('.followUpPlannedVisit').find('input').eq(2).val('');
		});
		
		/*$('.followUpPlannedVisit').find('input').on('focus',function(){
			$('.followUpPlannedVisit').find('input').attr('disabled','true');
			var index = $('.followUpPlannedVisit').find('input').index(this);
			if(index=='0')
			{ 
				$('.followUpPlannedVisit').find('input').eq(1).val('');
				$('.followUpPlannedVisit').find('input').eq(2).val('');
			}
			else if(index=='1')
			{
				$('.followUpPlannedVisit').find('input').eq(0).removeAttr('checked'); 
				$('.followUpPlannedVisit').find('input').eq(2).val('');
			}
			else
			{
				$('.followUpPlannedVisit').find('input').eq(0).removeAttr('checked');
				$('.followUpPlannedVisit').find('input').eq(1).val(''); 
			}
			$(this).removeAttr('disabled');  
		});*/
		 
		
		$('select[name=drugDosage]').on('change',function(){
			calculateQuantity();
		});
		$('select[name=drugFrequency]').on('change',function(){
			calculateQuantity();
		});
		$('input[name=drugDays]').on('keyup',function(){
			calculateQuantity();
		});

		$('select[name=externalDrugDosage]').on('change',function(){
			calculateExternalQuantity();
		});
		$('select[name=externalDrugFrequency]').on('change',function(){
			calculateExternalQuantity();
		});
		$('input[name=externalDrugDays]').on('keyup',function(){
			calculateExternalQuantity();
		});
		

		$('#allergiesModal .close').click(function(){
		        $('#allergicOrNotId').prop('checked', true);
		      });

		 $('.investigationsDiv').find('input').on('focus',function(){
		        var index = $('.investigationsDiv').find('input').index(this);
		        //alert(index);
		        if (index == '1') {
		          $('.investigationsDiv').find('input').eq(2).val('');
		        } else if (index == '2') {
		          $('.investigationsDiv').find('input').eq(1).val('');
		        } 
		      });
	
		 $('.chiefComplaintDiv').find('input').on('focus',function(){
		        var index = $('.chiefComplaintDiv').find('input').index(this);
		        if (index == '0') {
		          $('.chiefComplaintDiv').find('input').eq(2).val('');
		        } else if (index == '2') {
		          $('.chiefComplaintDiv').find('input').eq(0).val('');
		        } 
		      });
		 
		 $('.ClinicalPrcoeduresDiv').find('input').on('focus',function(){
		        var index = $('.ClinicalPrcoeduresDiv').find('input').index(this);
		        if (index == '0') {
		          $('.ClinicalPrcoeduresDiv').find('input').eq(2).val('');
		        } else if (index == '2') {
		          $('.ClinicalPrcoeduresDiv').find('input').eq(0).val('');
		        } 
		      });
 });	 
function calculateExternalQuantity()
{ 
var freq=$('select[name=externalDrugFrequency] option:selected').val();
var freqname= $('select[name=externalDrugFrequency] option:selected').text();
var dos=$('select[name=externalDrugDosage] option:selected').val();         
var days=$('input[name=externalDrugDays]').val();
var calcquan="1"; 

	if(freq!= -1 && days!="")
	{
  
		if (freq =="11" || freqname =="OD" )
				freq="1";
			
		else if (freq =="12" || freqname =="BD" )
				freq="2";

		else if (freq =="13" || freqname =="TDS" )
				freq="3";

		else if (freq =="15" || freqname =="QID" )
				freq="4";
		else 
			freq="1";

		if(dos.split('^')[1]==1)
		{
			dos = dos.split('^')[2];
		}
		else
			{
			dos='1';
			days='1';
			freq="1";
			}

		//$('input[name=externalDrugQuantity]').attr('readonly','false');	
		
		calcquan=freq*dos*days; 
		$('input[name=externalDrugQuantity]').val(calcquan);
	}
	 
}

function calculateQuantity()
{ 
	console.log('calc');
	var freq=$('select[name=drugFrequency] option:selected').val();
	var freqname= $('select[name=drugFrequency] option:selected').text();
	var dos=$('select[name=drugDosage] option:selected').val();         
	var days=$('input[name=drugDays]').val();
	var calcquan="1"; 

	if(freq!= -1 && days!="")
	{
  
		if (freq =="11" || freqname =="OD" )
				freq="1";
			
		else if (freq =="12" || freqname =="BD" )
				freq="2";

		else if (freq =="13" || freqname =="TDS" )
				freq="3";

		else if (freq =="15" || freqname =="QID" )
				freq="4";
		else 	
			freq="1";

		if(dos.split('^')[1]==1)
		{
			dos = dos.split('^')[2];
		}
		else
			{
			dos='1';
			days='1';
			freq="1";
			}

		//$('input[name=drugQuantity]').attr('readonly','false');	
		
		calcquan=freq*dos*days; 
		$('input[name=drugQuantity]').val(calcquan);
	}
	else
		{
		$('input[name=drugQuantity]').val(''); 
		}
	 
}

	 function isNumber(evt)
	{	 
	   var charCode = evt.which; 
	   if (charCode > 32 && (charCode < 48 || charCode > 57)) {
	      return false;
	    } 
	    return true;
	}

	 function SaveRxProfileData(mode,e , flg){
		 
		 if($('#PresCriptionBookmarkNameId').val() == ''){
			 alert('Please Enter Bookmark Name');
		 return false;
		 }
		 if($('#PresCriptionBookmarkDescId').val() == ''){
			 alert('Please Enter Bookmark Description');
		 return false;
		 }
		 var check=document.getElementsByName("AllDept");
			var strcount=0;
			console.log('checkBox.length'+check.length);
			for(var i=0;i<check.length;i++){
				if(check[i].checked==true){
					strcount++;
				}			
			}
			/*if(strcount == 0){
				alert('Please Select checkbox');
				return false;
			}*/
			if($('#bookmarkmodal').hasClass('in') == true){
				$('#bookmarkmodal').modal('hide');
			}else{
				$('#bookmarkmodal').modal('show');
			}	
		 
		 Save(mode,e, '1');
		 
	 }
	 
	 
	function Save(mode,e , flg)
	{
		//alert('1');
		/*console.log("kkkk:::::::::: "+  $('#bookmarkmodal').hasClass('in'));
		if($('#bookmarkmodal').hasClass('in') == true){
			$('#bookmarkmodal').modal('hide');
		}else{
			$('#bookmarkmodal').modal('show');
		}*/
		
		var InvTestCode=[];
		var InvTestCodeToPrint=[]; 
		var  i=0;
		var DrugCodeCat=[]; 
		var ReasonOfVisit=[];
		var Diagnosis=[];
		var j=0; 
		var ChronicDisease=[];
		var drugAllergy=[];
		var ClinicalProcedure=[];
		var k=0;
		var l=0;
		var m=0;
		var z=0;
		var Referal=[];
		var hospCode = $('#patHospitalCodePrescriptionPanel').text();
		var seatId = $('#patSeatIdPrescriptionPanel').text();
		
		var ResonOfVisitJsonArray =[];
		var DiagnosisJsonArray =[];
		var InvestigationJsonArray =[];
		var ClinicalProcedureJsonArray =[];
		var DrugJsonArray =[];
		
		$('input[name=reasonOfVisit]:checked').each(function()
				{
					console.log($(this).val());
					if($(this).val().split('^')[0]!='0')
					InvTestCode[i]=$(this).val()+'^'+$(this).parent().find('.text1').text();
					i++;
					
				}); 
		console.log(InvTestCode);
		i=0;
		$('input[name=reasonOfVisit]:checked').each(function()
				{
					console.log($(this).val());
					InvTestCodeToPrint[i]=$(this).val()+'^'+$(this).parent().find('.text1').text();
					InvestigationJsonArray[i]=JSON.parse($(this).parent().find('i').text());
					i++; 
				}); 
		console.log("Inv code to print --->> "+InvTestCodeToPrint);
		
		
		
		$('input[name=drugsAdvices]:checked').each(function()
				{
					//console.log('name=drugsAdvices::::>>>'+$(this).val());
					if($(this).val().trim().split('&&')[1]!=100) 
					{
						DrugCodeCat[j]=$(this).val();
						//console.log("DrugJson::::::: "+JSON.parse($(this).parent().find('i').text()));
						DrugJsonArray[j]=JSON.parse($(this).parent().find('i').text());
						j++;
					}
					
				});
		console.log('DrugCodeCat');
		console.log(DrugCodeCat);
		j=0;
		$('input[name=visitReason]:checked').each(function()
				{
					console.log('ReasonOfVisit::>>> '+$(this).val());
					ReasonOfVisit[j]=$(this).val();   //+'^'+$(this).parent().text();
					ResonOfVisitJsonArray[j]=JSON.parse($(this).parent().find('i').text());
					//console.log('DDDDDDDDD  '+$(this).parent().find('i').text());
					//console.log('DDDDDDDDD '+document.getElementsByName("visitReasonHidden")[j].value);
					j++; 
				});
		console.log('ReasonOfVisit');
		console.log(ReasonOfVisit); 
		j=0;
		$('input[name=diagnosisAdded]:checked').each(function()
				{
					console.log('diagnosisAdded::>>> '+$(this).val());
					Diagnosis[j]=$(this).val();    //+'^'+$(this).parent().text()+'#'+$(this).attr('diagnosisName');
					DiagnosisJsonArray[j]=JSON.parse($(this).parent().find('i').text());
					j++; 
				});
		console.log('Diagnosis');
		console.log(Diagnosis);
		
		
		
		k=0;
		$('input[name=chronicDiseaseChk]:checked').each(function()
				{
					//Malaria;^61462000^100^asdasd
					//Chronic Disease^Chronic Disease ID ^ Duration(yrs) ^ Remarks
					console.log('chronicDiseaseChk::>>> '+$(this).val());
					var disease=$(this).val();
					
					var Json={
							"CronicDiseaseName" : disease.split('^')[0],
							"CronicDiseaseId" : disease.split('^')[1],
							"CronicDiseaseDuration" : disease.split('^')[2],
							"CronicDiseaseRemarks" : disease.split('^')[3],
					};
					
					ChronicDisease[k]=Json;
					k++; 
				});
		console.log('ChronicDisease');
		console.log(ChronicDisease);
		
		
		l=0;
		$('input[name=allergiesDtlChk]:checked').each(function()
				{
					//Malaria;^61462000^100^asdasd
					//Chronic Disease^Chronic Disease ID ^ Duration(yrs) ^ Remarks
					var allergy=$(this).val();
					//console.log('chronicDiseaseChk::>>> '+$(this).val());
					//console.log('Parse Jsom'+JSON.parse($(this).val()));
					//JSON.parse();
					//console.log('chronicDiseaseChk::>>> '+JSON.parse(allergy));
					//console.log('chronicDiseaseChk:Test:>>> '+JSON.stringify(JSON.parse($(this).val())));
					//var allergy=$(this).val();
					drugAllergy[l]=JSON.parse(allergy);
					l++; 
				});
		
		console.log('drugAllergy');
		console.log(drugAllergy);
		
		
		 m=0;
		$('input[name=clinicalProc]:checked').each(function()
				{
					console.log('clinicalProc::>>> '+$(this).val());
					ClinicalProcedure[m]=$(this).val();    //+'^'+$(this).parent().text()+'#'+$(this).attr('diagnosisName');
					ClinicalProcedureJsonArray[m]=JSON.parse($(this).parent().find('i').text());
					
					m++; 
				});
		console.log('ClinicalProcedure');
		console.log(ClinicalProcedure);
		
		 z=0;
			$('input[name=referalchk]:checked').each(function()
					{
						console.log('referalchk::>>> '+$(this).val());
						var strrefrealId=$(this).val();
						Referal[z]= JSON.parse(strrefrealId); 
						z++; 
					});
			console.log("Referal");
			console.log(Referal);
		
		var CR_No = $('#patCrNoPrescriptionPanel').text();
		var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
		var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
		console.log('SeatID:::::::::::  '+ $('input[name=patGeneralDtl]').val())
		var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val();
		
		var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
		//alert(patCompleteGeneralDtlPrescriptionPanel);
		
		var episodeCode =$('#patEpisodeCodePrescriptionPanel').text();
		//alert("episode"+episodeCode);
		
		var endTreatment = $('input[name=endTreatment]:checked').val();
		var plannedVisitSos = $('input[name=followUpPlannedVisitSos]:checked').parent().text();
		var plannedVisitDays = $('input[name=followUpPlannedVisitDays]').val();
		var plannedVisitDate = $('input[name=followUpPlannedVisitDate]').val(); 
		var progressNote = $('textarea#progressNote').val();
		console.log('progressNote:::>>'+progressNote);
		
		
		var pastHistoryId	= $('#pastHistoryId').val();
		var personalHistoryId	= $('#personalHistoryId').val();
		var familyHistoryId	= $('#familyHistoryId').val();
		var treatmentHistoryId	= $('#treatmentHistoryId').val();
		var surgicalHistoryId	= $('#surgicalHistoryId').val();

		var cpmpleteHistory = {
		"strpastHistory"    	:pastHistoryId ,
		"strpersonalHistory"	:personalHistoryId ,
		"strfamilyHistory"	:familyHistoryId ,
		"strtreatmentHistory"	:treatmentHistoryId ,
		"strsurgicalHistory"	:surgicalHistoryId 
		} ;

		var cpmpleteHistoryJSON = JSON.stringify(cpmpleteHistory); 
		console.log(cpmpleteHistoryJSON); 
		

		var cvsId	= $('#cvsId').val();
		var rsId	= $('#rsId').val();
		var cnsId	= $('#cnsId').val();
		var pAId	= $('#pAId').val();
		var otherExamnId= $('#otherExamnId').val();
		var muscularExamnId= $('#muscularExamnId').val();
		var LocalExamnId= $('#LocalExamnId').val();
		var pallorId ='' ;
		var icterusId ='';
		var cyanosisId='';
		var clubbingId='';
		var iymphadenopathyId='';
		var edemaID='';
		if($('#pallorId').is(":checked"))
		pallorId = '1';
		else
		pallorId = '0';	
		
		if($('#icterusId').is(":checked"))
			icterusId = '1';
			else
			icterusId = '0';
		
		if($('#cyanosisId').is(":checked"))
			cyanosisId = '1';
			else
			cyanosisId = '0';
		
		if($('#clubbingId').is(":checked"))
			clubbingId = '1';
			else
			clubbingId = '0';
		
		if($('#iymphadenopathyId').is(":checked"))
			iymphadenopathyId = '1';
			else
			iymphadenopathyId = '0';
		
		if($('#edemaID').is(":checked"))
			edemaID = '1';
			else
			edemaID = '0';
		
		
		console.log('pallorId'+pallorId);
		
		var piccle = {
				"strpallor" :pallorId,
				"stricterus":icterusId,
				"strcyanosis":cyanosisId,
				"strclubbing":clubbingId,
				"striymphadenopathyId":iymphadenopathyId,
				"stredema" :edemaID
				
				
		}

		var addSystematicExamniation = {
		"strcvs" 	: cvsId ,
		"strrs"		: rsId ,
		"strcns"	: cnsId ,
		"strpA"		: pAId ,
		"strotherExamn"	: otherExamnId ,
		"strmuscularExamn" :muscularExamnId ,
		"strLocalExamn"  : LocalExamnId 
		} ; 

		var addSystematicExamniationJson = JSON.stringify(addSystematicExamniation); 
		console.log(addSystematicExamniationJson); 
		
		var HistoryOfPresentIllNess=$("#hopiId").val();
		var DiagnosisNote=$("#diagnosisNoteId").val();
		var investigationNote=$("#investigationNoteId").val();
		var otherAllergiesId=$("#otherAllergiesId").val();
		var treatmentAdviceId=$("#treatmentAdviceId").val();
		var strVitalsChartId=$("#vitalHiddenValId").val();
		var strReffralDeptCmb= [] ;  // ($('select[name=refferlPatientDept] option:selected').val()).split('#')[0];
		var strReffralDepttext=  []; // ($('select[name=refferlPatientDept] option:selected').text());
		//var strReffralReason=$('input[name=refferalResonId]').val(); ------ commented by ashutoshk for changing input tag to textarea tag under refer section
		var strReffralReason=$("#refferalResonId").val();
		var strReffralDeptDone = []; //($('select[name=refferlPatientDept] option:selected').val());
		var strConfidentialsInfo=$("#ConfidentialInfoId").val();
		var refferlCmblength= $('select[name=refferlPatientDept] option:selected') ;
		
		var DeptIdflg='0';
		if($('#deptonlyid').is(":checked"))
			DeptIdflg='1';
		
		
		var check=document.getElementsByName("AllDept");
		
		console.log('checkBox.length'+check.length);
		var AllDeptIdflg='0';
		for(var i=0;i<check.length;i++){
			if(check[i].checked==true){
				AllDeptIdflg=check[i].value;
			}			
		}
		
		
		//if($('#ALlDeptId').is(":checked"))
			//AllDeptIdflg=$('#ALlDeptId').val();
		
		var PresCriptionBookmarkNameval= $('#PresCriptionBookmarkNameId').val();
		var PresCriptionBookmarkDescVal= $('#PresCriptionBookmarkDescId').val();
		
		/*console.log('DeptIdflg'+DeptIdflg);
		console.log('AllDeptIdflg'+AllDeptIdflg);
		console.log('PresCriptionBookmarkNameval'+PresCriptionBookmarkNameval);
		*/
		
		
		
		/*var t2=0;
		var t1 =0;
		$("#refferlPatientDeptId option:selected").each(function () {
			   var $this = $(this);
			   if ($this.length) {
			    var t1 = $this.text();
			    strReffralDepttext[t1]  = t1 ;
			    console.log(t1);
			    console.log('vvvvvvvvvv  '+$this.val());
			    var alert1="Alert !!! "+ t1;
			    if($this.val().split('#')[0] == fromdeptCode){
			    	t2=1;
			    	$.alert({
				        title: alert1,
				        content: 'Patient Cannot Reffer in same Department',
				    });
			    	 false;
			    }
			    t1++;
			   }
			});*/
		
		/*console.log('fromdeptCode:::::::::::'+fromdeptCode);
		var refferlCmblength1 = $('select[name=refferlPatientDept] option:selected') ;
		for (var i = 0 ; i < refferlCmblength1.length ; i++){
			if( ( $('select[name=refferlPatientDept] option:selected')[i].value).split('#')[0] == fromdeptCode){
				alert('1');
				$.alert({
			        title: 'No Record!',
			        content: 'Data No Avilable',
			    });
			}
		}*/
		
		var admissionadviceDeptName='';
		var admissionadviceWardName='';
		var admissionadviceNotes='';
		$.ajax({url:'/HISDRDESK/services/restful/patdata/getAdmissionAdvice?Modval=7&CrNo='+patGeneralDtl[1]+'&episodeCode='+patGeneralDtl[2] +'&visitNo='+$('#patEpisodeVisitNoPrescriptionPanel').text()+'&seatId=&Entrydate=&hosp_code='+hospCode+'',
			async:false,
			success:function(result){ 
				console.log('getAdmissionAdvice  getAdmissionAdvice  :::::::::::::::'+result);
				for(var len =0 ; len < result.length ; len ++){
					console.log(result[len]);
					 admissionadviceDeptName= result[len].GETDEPTUNITNAME;
					 admissionadviceWardName=result[len].GETWARDNAME;
					 admissionadviceNotes=result[len].HIPSTR_ADVICE_NOTE;
					
				}
			}
		});
		
		
		var prescJSON = {
			'InvTestCode':InvTestCode,
			'InvTestCodeToPrint':InvTestCodeToPrint,
			'DrugCodeCat':DrugCodeCat,
			'ReasonOfVisit':ReasonOfVisit,
			'Diagnosis':Diagnosis,
			'FOLLOW_UP':[
				{'endTreatment':endTreatment, 
				'Planned_Visit':[
					{'plannedVisitSos':plannedVisitSos,
					'plannedVisitDays':plannedVisitDays,
					'plannedVisitDate':		plannedVisitDate =="" ? "" : plannedVisitDate.split('-')[2]+'-'+plannedVisitDate.split('-')[1]+'-'+plannedVisitDate.split('-')[0]
					}
					],
				'progressNote':progressNote
				}],
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			"currentVisitDate":new Date().getDate()+'/'+(new Date().getMonth()+1)+'/'+new Date().getFullYear(),
			"patVisitType":$('#patVisitTypePrescriptionPanel').text(),
			"lastVisitDate":$('#patLastVisitDatePrescriptionPanel').text(),
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3],
			"hosp_code":hospCode,	
			"seatId": $('input[name=strSeatId]').val(),
			"hrgnum_is_docuploaded":0,
			"patConsultantName":$('#patConsultantNamePrescriptionPanel').text(),
			"patDept":$('#patDeptName').text(),
			"patGaurdianName":$('#patGaurdianNamePrescriptionPanel').text(),
			"PatCompleteGeneralDtl":patCompleteGeneralDtlPrescriptionPanel ,
			"strCompleteHistory":cpmpleteHistory, // From Here
			"strSystematicExamniation":addSystematicExamniation,
			"strChronicDisease":ChronicDisease ,
			"strHistoryOfPresentIllNess":HistoryOfPresentIllNess,
			"strDiagnosisNote":DiagnosisNote ,
			"strDrugAllergy" : drugAllergy ,
			"strInvestgationNote" :investigationNote ,
			"strotherAllergies":otherAllergiesId ,
			"strClinicalProcedure":ClinicalProcedure ,
			"strtreatmentAdvice" :treatmentAdviceId ,
			"strVitalsChart"	  :strVitalsChartId ,
			"strpiccle"			  :piccle ,
			"strConfidentialsInfo" : strConfidentialsInfo ,
			"strReferal" :Referal ,
			"strDeptIdflg" :flg.toString() ,
			"strAllDeptIdflg" :AllDeptIdflg.toString() ,
			"strPresCriptionBookmarkNameval" :PresCriptionBookmarkNameval ,
			"strPresCriptionBookmarkDescVal" :PresCriptionBookmarkDescVal ,
			"strUmidNo" : $('#patUmidPrescriptionPanel').text() ,
			"admissionadviceDeptName": admissionadviceDeptName ,
			"admissionadviceWardName" : admissionadviceWardName , 
			"admissionadviceNotes" : admissionadviceNotes,
			"strDesignation" : $('#patdesignationPrescriptionPanel').text() ,
			"strStation" : $('#patStationPrescriptionPanel').text() ,
			
			
			
		};
 
		var FormattedJson ={
				"Patient_Name"					:	patGeneralDtl[0].trim(),
				"CR_No"							:	patGeneralDtl[1],
				"EpisodeCode"					:	patGeneralDtl[2],
				"EpisodeVisitNo"				:	$('#patEpisodeVisitNoPrescriptionPanel').text(),
				"CurrentVisitDate"				:	new Date().getDate()+'/'+(new Date().getMonth()+1)+'/'+new Date().getFullYear(),
				"PatVisitType"					:	$('#patVisitTypePrescriptionPanel').text(),
				"LastVisitDate"					:	$('#patLastVisitDatePrescriptionPanel').text(),
				"PatientGender"					:	patGeneralDtl[3].split('/')[0],
				"PatientAge"					:	patGeneralDtl[3].split('/')[1],
				"PatientCat"					:	patGeneralDtl[3].split('/')[2],
				"PatientQueueNo"				:	patGeneralDtl[3].split('/')[3],
				"hosp_code"						:	hospCode,	
				"hrgnum_is_docuploaded"			:	0,
				"ConsultantName"				:	$('#patConsultantNamePrescriptionPanel').text(),
				"PatientDept"					:	$('#patDeptName').text(),
				"patGaurdianName"				:	$('#patGaurdianNamePrescriptionPanel').text(),
				"PatCompleteGeneralDtl"			:	patCompleteGeneralDtlPrescriptionPanel ,
				"seatId"						:   $('input[name=strSeatId]').val(),
				"HistoryOfPresentIllNess"		:	HistoryOfPresentIllNess,
				"DiagnosisNote"					:	DiagnosisNote ,
				"InvestgationNote" 				:	investigationNote ,
				"OtherAllergies"				:	otherAllergiesId ,
				"ReasonOfVisitJsonArray"		:	ResonOfVisitJsonArray ,
				"DiagnosisJsonArray"			:	DiagnosisJsonArray,
				"InvestigationJsonArray"		: 	InvestigationJsonArray,
				"CompleteHistoryJaonArray" 		:	cpmpleteHistory ,
				"SystematicExamniationArray" 	: 	addSystematicExamniation ,
				"ChronicDiseaseArray" 			:	ChronicDisease ,
				"PiccleArray"			  		:	piccle ,
				"ClinicalProcedureJsonArray"	:	ClinicalProcedureJsonArray ,
				"DrugJsonArray"					:	DrugJsonArray ,
				"PatientRefrel"					:	Referal ,
				"strpiccle"			 			:	piccle ,
				"strtreatmentAdvice" 			:	treatmentAdviceId ,
				"strConfidentialsInfo" 			: 	strConfidentialsInfo ,
				"strVitalsChart"	 			: 	strVitalsChartId ,
				'FOLLOW_UP'						:	[
													{'endTreatment':endTreatment, 
													'Planned_Visit':[
														{'plannedVisitSos':plannedVisitSos,
														'plannedVisitDays':plannedVisitDays,
														'plannedVisitDate':plannedVisitDate}
														],
													'progressNote':progressNote
													}],
													
				"strDeptIdflg" :flg.toString(),
				"strAllDeptIdflg" :AllDeptIdflg.toString() ,
				"strPresCriptionBookmarkNameval" :PresCriptionBookmarkNameval  ,
				"strPresCriptionBookmarkDescVal" :PresCriptionBookmarkDescVal ,
				"strUmidNo" : $('#patUmidPrescriptionPanel').text() ,
				"admissionadviceDeptName": admissionadviceDeptName ,
				"admissionadviceWardName" : admissionadviceWardName , 
				"admissionadviceNotes" : admissionadviceNotes,
				"strDesignation" : $('#patdesignationPrescriptionPanel').text() ,
				"strStation" : $('#patStationPrescriptionPanel').text() ,
				
			
		}
			console.log('FormattedJson');
			console.log(JSON.stringify(FormattedJson));
			
			var myJSON = JSON.stringify(prescJSON); 
			//console.log("myJSON ashutoshk "+myJSON); 
			if(localStorage.getItem("myJSON"))
				localStorage.removeItem("myJSON");
				localStorage.setItem("myJSON", myJSON);
				
				if(localStorage.getItem("FormattedJson"))
					localStorage.removeItem("FormattedJson");
					localStorage.setItem("FormattedJson", JSON.stringify(FormattedJson));
				
			if(localStorage.getItem("flag"))
				localStorage.removeItem("flag");
			localStorage.setItem("flag", "0");
				
			if(mode!='printSave')
			swal({
				  title: "Are you sure to Save?",
				  text: "Press ok to save!",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
			.then(function(willDelete) {
			  if (willDelete) { 
						SaveData(myJSON ,JSON.stringify(FormattedJson) ,e); 
			  } else {
			     return false;
			  }
			});
			else 
			{
				//alert( $(window).width());
				//alert('1'+ $(window).width()< 1100);
				if($(window).width() <  850){
					//alert('2');
				 // $('#printPrescriptionModalId12').removeClass('modal-dialog modal-lg');
				 //alert(document.getElementById("printPrescriptionModalId12").className);
				 document.getElementById("printPrescriptionModalId12").className='';
				 // alert(document.getElementById("printPrescriptionModalId12").className);
				 }else{
					 document.getElementById("printPrescriptionModalId12").className='modal-dialog modal-lg';
				 }
				
				$('#printPrescriptionModal .modal-body').prepend('<iframe id="printPrescFrameId" style="width:100%;height:80vh;" src="/HISDRDESK/new_opd/transaction/DoctorDeskAction.cnt?hmode=PRINTPRESC"></iframe>');
				$('#printPrescriptionModal').modal('show');
				//$("#printPrescriptionModal").find(".modal-backdrop").css({"z-index": "-1"});
				
				/*$(e).hide(); */							
			}
	} 

	
	//Code Added By Timsi Kataria as suggested by Priyesh Sir
	
	$('.generalComplaintAdd').click(function(){ 
 		
		 var generalComplaintVal = $(this).parent().parent().parent().find('input[name=generalComplaint]').val(); 
		  if(generalComplaintVal.trim()!='')
		  {  
			    var tmp = 0; 
				$('.reasonOfVisitAdded').find('label').each(function(index){ 
					if($(this).text().split("(")[0].trim().toUpperCase()===generalComplaintVal.trim().toUpperCase()) 
					{	tmp = 1; 
						return false;  }
				});
				if(tmp==1)
				{
					swal("Already Added!!");
					return false;
				}
				else
				{
					$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="visitReason" value="'+0+'"  checked> '+generalComplaintVal+'<sup style="color:red; font-weight:bold;">*</sup></label></a>');

				}
	 		 
			 $(this).parent().parent().parent().find('input[name=generalComplaint]').val(''); 
		  }
		  else{
			  swal('Please enter other general complaint to be added');
		  }
	}); 
	
	$('.diagnosisAddExt').click(function(){ 
 		
		 var externalDiagnosisVal = $(this).parent().parent().parent().find('input[name=externalDiagnosisTxt]').val(); 
		  if(externalDiagnosisVal.trim()!='')
		  {  
			    var tmp = 0; 
				$('.diagnosisAdded').find('label .text').each(function(index){ 
					if($(this).text().split("*")[0].trim().toUpperCase()===externalDiagnosisVal.trim().toUpperCase()) 
					{	tmp = 1; 
						return false;  }
				});
				if(tmp==1)
				{
					swal("Already Added!!");
					$(this).parent().parent().parent().find('input[name=externalDiagnosisTxt]').val('');
					return false;
				}
				else
				{
					//$(this).parent().parent().parent().find('.diagnosisAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="diagnosisAdded" value="0#0#2^'+externalDiagnosisVal+'#0#0##0#0"  checked> '+externalDiagnosisVal+'(ext)</label></a>');
					var DiagnosisJson ={
	 						"IsSnomed"				:			"3" ,
							"DiagnosisName" 		: 		 	externalDiagnosisVal,
							"DiagnosisCode" 		:			"0",
							"DiagnosisSideCode" 	: 			"0" ,
							"DiagnosisSideName" 	:			"0",
							"DiagnosisTypeCode" 	: 	 		"0" ,
							"DiagnosisTypeNamee" 	: 			"0",
							"DiagnosisRemarks"		:			"External Diagnosis"
						};
					console.log(JSON.stringify(DiagnosisJson));
					$(this).parent().parent().parent().find('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
			    	 		'<input type="checkbox" class="checkedInput" name="diagnosisAdded" value="0#0#2^'+externalDiagnosisVal+'#0#0##0#0" checked="">  '+
			    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
			    	 		'<span class="text">'+externalDiagnosisVal+'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');

				}
	 		 
			 $(this).parent().parent().parent().find('input[name=externalDiagnosisTxt]').val(''); 
		  }
		  else{
			  swal('Please enter other diagnosis to be added');
		  }
	}); 
	

	/* New Code for Past Rx Button on Prescription Page */
	$(document).ready(function(){
		
		
		
		  window.patCrNo = $('#patCrNoPrescriptionPanel').text();
		  window.episodeCode = $('#patEpisodeCodePrescriptionPanel').text();
		  window.visitNo = $('#patEpisodeVisitNoPrescriptionPanel').text();
		  window.seatId = $('#patSeatIdPrescriptionPanel').text();
		  window.hospCode = $('#patHospitalCodePrescriptionPanel').text();
		  window.deptName = $('#patDeptName').text();
		  
		     var todaysys = new Date(); 
			 var dd = todaysys.getDate();
			 dd = dd.toString().length>1 ? dd : "0"+dd;
			 var mm = todaysys.getMonth() + 1;
			 mm = mm.toString().length>1 ? mm : "0"+mm;
			 var yyyy=todaysys.getFullYear();
			 //todaysys = dd+'/'+mm+'/'+yyyy;
			 todaysys=new Date().getDate()+'/'+(new Date().getMonth()+1)+'/'+new Date().getFullYear()
		  
		  //alert(window.patCrNo+" "+window.episodeCode+" "+window.visitNo+" "+window.deptName);
		  
		$.ajax({url:'/HISDRDESK/services/restful/patdata/getPatDataForPastPrescription?Modval=2&CrNo='+window.patCrNo+'&episodeCode='+window.episodeCode +'&visitNo='+window.visitNo+'&seatId=&Entrydate=&hosp_code='+window.hospCode+'',
			async:true,
			beforesend : $('.patPreviousPrescriptionList').parent().append('<p id="prescriptionListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
				success:function(data){ 
					var str = '';
					var strHistory = '' , strExaminations = '' , strChronics = '' , strAllergies = '' , strClinicalProc = '' , strVitalsInfo = '' ;
					var menuId = 'opdEMRModalNavMenuItem';
					//alert(Object.keys(data).length);
					//alert(Object.keys(data.pat_details).length);
					console.log('======:::::::::::::::::::::::::::::::::::::::::::::::======data.pat_details'+Object.keys(data.pat_details).length);
					console.log(data);
					if(Object.keys(data.pat_details).length > 0){
						
						$('.opdEmrModalNavMenu').children('li').eq(0).remove();
						$('.opdEmrModalNavMenuContent').children('div').eq(0).remove();
						
						$('#prescriptionListMsg').remove();
						for(var i=0;i<Object.keys(data.pat_details).length;i++)
						{   
							var visitDate = data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate.trim();
							var temp = visitDate.split("/");
							var months = new Array(12);
						 	months[1] = "Jan";
						 	months[2] = "Feb";
						 	months[3] = "Mar";
						 	months[4] = "Apr";
						 	months[5] = "May";
						 	months[6] = "Jun";
						 	months[7] = "Jul";
						 	months[8] = "Aug";
						 	months[9] = "Sep";
						 	months[10] = "Oct";
						 	months[11] = "Nov";
						 	months[12] = "Dec";
						    var tempdate =	data.pat_details[i].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim().split('#')[7].split('-');
						 	var maxDate = temp[0]+" "+months[temp[1]] +", "+temp[2];
						 	var maxDate1 =    tempdate[0]+"-"+tempdate[1] +"-"+tempdate[2].substring(2,4);

						 	var today = new Date();
							var printDate = today.getDate()+'-'+months[today.getMonth()+1]+'-'+today.getFullYear();
							var printTime = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
							
							str = '';
							strHistory = '' , strExaminations = '' , strChronics = '' , strAllergies = '' . strVitalsInfo = '' , strClinicalProc = '' ;
							
							////////////////////////////////////////////////////////////////////////////////////////////////////
							
							$.ajax({url:'/HISDRDESK/services/restful/patdata/getPatVitalDataForDetailedPrescription?Modval=3&CrNo='+window.patCrNo+'&episodeCode='+window.episodeCode +'&visitNo='+window.visitNo+'&seatId=&Entrydate=&hosp_code='+window.hospCode+'',
								async:false,
								success:function(result){ 
									
										if(Object.keys(result.pat_vital_details).length > 0){
											
											for(var j=0;j<Object.keys(result.pat_vital_details).length;j++){   
												
												strVitalsInfo = '' ;
												
												if(result.pat_vital_details[j].TO_CHAR == data.pat_details[i].TO_CHAR){
													if(j<7){
												 		strVitalsInfo+='<div class="row"><div class="col-sm-12"><p><b> VITALS : </b><small class="vitalsInfo">';
												 		
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strWeight != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strWeight != '0.00'){
								      						strVitalsInfo+='Weight : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strWeight+' kgs , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strHeight != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strHeight != '0.00'){
								      						strVitalsInfo+='Height : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strHeight+' cms , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strBmid != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strBmid != '0.00'){
								      						strVitalsInfo+='BMI : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strBmid+' , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strTempreature != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strTempreature != '0.00' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strTempreature != '0.0'){
								      						strVitalsInfo+='Temperature : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strTempreature+' <sup>o</sup>F , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strrespRate != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strrespRate != '0'){
								      						strVitalsInfo+='Respiration Rate : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strrespRate+' br/m , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strhaemoglobin != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strhaemoglobin != '0.00'){
								      						strVitalsInfo+='Haemoglobin : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strhaemoglobin+' gm/dL , ';
								      					}
								      					
								      					if((result.pat_vital_details[j].HOPLSTR_JSON_DATA.strsystolic != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strdiastolic != '') && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strsystolic != '0.0' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strdiastolic != '0.0'){
								      						strVitalsInfo+='B.P : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strsystolic+'/'+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strdiastolic+' mm/HG , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strsystolic1 != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strdiastolic1 != ''){
								      						strVitalsInfo+='B.P : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strsystolic1+'/'+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strdiastolic1+' mm/HG , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strfasting != ''){
								      						strVitalsInfo+='B.S. Fast : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strfasting+' mg/dL , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strRateId != ''){
								      						strVitalsInfo+='PP : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strRateId+' mg/dL , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strhba1c != ''){
								      						strVitalsInfo+='HBA1C : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strhba1c+' % , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strbloodGroup != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strbloodGroup != '0'){
								      						strVitalsInfo+='Blood Group : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strbloodGroup+', ';
								      					}
								      					
								      					/*-----------Added for cancer screening---------------*/
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strcancerScreening != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strcancerScreening != '0'){
								      						strVitalsInfo+='Cancer screening : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strcancerScreening+', ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strcurcumference != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strcurcumference != '0'){
								      						strVitalsInfo+='Head Circumference : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strcurcumference+' cms , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strmuac != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strmuac != '0'){
								      						strVitalsInfo+='MUAC : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strmuac+' cms , ';
								      					}
								      					
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strDisability != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strDisability != '0'){
								      						strVitalsInfo+='Disability : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strDisability+' , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strSmoking != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strSmoking != '0'){
								      						strVitalsInfo+='Smoking'+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strSmoking+'  , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strAnemic != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strAnemic != '0'){
								      						strVitalsInfo+='Anemic : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strAnemic+'  , ';
								      					}
								      					
								      					if(result.pat_vital_details[j].HOPLSTR_JSON_DATA.strPregnancy != '' && result.pat_vital_details[j].HOPLSTR_JSON_DATA.strPregnancy != '0'){
								      						strVitalsInfo+='Pregnancy : '+result.pat_vital_details[j].HOPLSTR_JSON_DATA.strPregnancy+'  , ';
								      					}
								      					strVitalsInfo+='</small></p></div></div>';
												 	}
												}
												else{
													strVitalsInfo+='<div class="row"><div class="col-sm-12<p><b> VITALS : </b><small class="vitalsInfo">';
													/*strVitalsInfo+='No Record Found';*/
													strVitalsInfo+='</small></p></div></div>';
												}

											}
											
										}
										
									}
								
							});
											
							console.log('1::::::::::Vital End');
							//////////////////////////////////////////////////////////////////////////////////////////////////
							
							/*$('.opdEmrModalNavMenu').append('<li><a href="#'+menuId+(i+1)+'" data-toggle="tab">'+data.pat_details[i].TO_CHAR+'</a></li>');*/
							$('.opdEmrModalNavMenu').append('<li><a href="#'+menuId+(i+1)+'" data-toggle="tab">'+maxDate1+'</a></li>'); 
							console.log(data.pat_details[i].TO_CHAR);
							console.log(data.pat_details[i].HRSTR_JSON_DATA);
							str+='<div style="right:8px; position:fixed">'+
							'<input type="button" onclick="printDiv(\''+menuId+(i+1)+'\');" value="Print" class="btn btn-info previousPrescPrintBtn pull-right" id="printPastPrescBtn'+(i+1)+'"/>'+
								/*'<button onclick="printDiv(\''+menuId+(i+1)+'\');" class="btn btn-info previousPrescPrintBtn pull-right" id="printPastPrescBtn'+(i+1)+'" style="z-index:9999;" type="button">Print</button>'+*/
								'</div>'; 
							/*var divId = $(this).parent().parent().attr('id').val();
							alert(divId);*/
														
							
							/*str+='<h2 id="watermark">e-Sushrut</h2>';*/
							/*str+='<div id="printPrescPage_tabContent'+(i+1)+'" class="printPreviousPrescPage">';
							str+='<div class="row"><h4 class="text-center" style="font-weight:bold">AIIMS PATNA OPD PRESCRIPTION</h4>'+
							'</div>'+  
							'<div class="" style="border-top: 1px solid grey; border-bottom: 1px solid grey;">'+
							'<table class="table table-condensed printPrescPatDtlTbl table-responsive">'+
							'<tbody>'+
							'<tr>'+
							'<th>Name</th><td class="patName">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.pat_Name.trim();
							str+='</td><th>CR No.</th><td class="patCrNo">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.CR_No.trim();
							str+='</td></tr>';
							str+='<tr>'+
							'<th>Department (Unit)</th><td class="patDeptU">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.patDept.trim();
							str+='</td><th>Reprinted On</th><td class="printedOn">';
							str+=printDate+' / '+printTime;
							str+='</td></tr>';
							str+='</tbody></table></div>';*/
							
							
							str+='<div id="printPrescPage_tabContent'+(i+1)+'" class="printPreviousPrescPage">';
							str+='<div class="row"><div style="float:left"> <h4 class="text-center" style="font-weight:bold"> '+
										'<img alt="" src="/HIS/hisglobal/drDeskAssets/img/logo.jpg" style="height: 100px"></h4> </div><br>'+
							 			'<div style="float:left;text-align:center">'+
							 			  '<h4><b>'+$('input[name=strHospitalName]').val()+'</b></h4> '+
									      ' <h5>'+$('input[name=strHospitalAddres]').val()+'</h5> '+ 
									     '</div> '+
									'</div>'+  
									'<div class="" style="border-top: 1px solid grey; border-bottom: 1px solid grey;">'+
									'<table class="table table-condensed printPrescPatDtlTbl table-responsive">'+
									'<tbody><tr>'+
									' <th colspan="5">'+
									'<table align="center">'+
									'	<tr><td></td><td><h4><font color="blue">OPD Prescription</font></h4></td><td></td></tr>'+
									'</table>'+
								'</th>'+
									'<tr>'+
									'<th>Name</th><td class="patName">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.pat_Name.trim();
							str+='</td><th>CR No.</th><td class="patCrNo">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.CR_No.trim();
							str+='</td></tr><tr><th>Age/Gender</th><td class="patAgeGen">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.patAge.trim()+'/'+data.pat_details[i].HRSTR_JSON_DATA.patGender.trim();
							str+='</td><th>Patient Category</th><td class="patCat">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.patCat.trim();
							str+='</td></tr><tr>'+
								'<th>Father/Spouse Name</th><td class="patRelName">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.patGaurdianName.trim();
							str+='</td><th>Department(Unit/Consultant)</th><td class="patDeptU">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.patDept.trim();
							str+='</td></tr><tr><th>Visit Date</th><td class="patVisitDate">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim().split('#')[7];
							str+='</td><th>Unit Head</th><td class="consultantName">';
							str+=data.pat_details[i].HRSTR_JSON_DATA.patConsultantName.trim();
							str+='</td></tr></tbody></table></div>'; 
							
							if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit !=''){
							str+='<div class="row">'+
							'<div class="col-sm-12">'+
								'<p><b>CHIEF COMPLAINT : </b><small class="reasonOfVisit">';
								for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit.length;j++){
									
									
									
									
									
									
									
									
									var VisitReason='';
									var text='';
									var text1='';
									console.log('Visit Reason Length:::::::'+data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^').length);
									if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^').length == 2)
										{
										VisitReason=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j]+'^0^^0';
										}else{
											VisitReason=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j];
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
									var remarks_temp ='';
									if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^').length == 6){
									if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[5].trim() != '' )
										{
										remarks_temp =' '+data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[5].trim();
										}else{
											remarks_temp='';
										}
									}
									str+=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[1].trim()+(text == '' ? '' : ' ('+text+','+(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[3]=='' ? '' : +data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[3].trim()+' '+ text1) +')')+remarks_temp+', '
								}
								str+='</small></p>'+
										'</div>'+
									'</div>'+
									'<br>';
							}
							
							console.log('1::::::::::strHistoryOfPresentIllNess');	
								if("strHistoryOfPresentIllNess" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									if(data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess != ''){
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
										'<p style="text-align: justify;"><b>HISTORY OF PRESENT ILLNESS : </b><small>';
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess.length;j++){
										str+=data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess[j];
									}
									str+='</small></p>'+
										'</div>'+
										'</div>'+
										'<br>';
									}
								}
								
							str+=strVitalsInfo;
							
						
								
								
								/*else{
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>HISTORY OF PRESENT ILLNESS : </b><small>';
									str+='No Record Found';
									str+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';
								}*/
								
								
								
								if("strSystematicExamniation" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									
								if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs != '' || data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns != ''
								|| 	data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs != '' || data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA != ''
								|| data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn != ''
								)	{
									strExaminations+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>EXAMINATION : </b><ul>';
								
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs != ''){
										strExaminations+='<li><b style="color: #5a5a5a !important;">CVS : </b><small>';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs;
										strExaminations+='</small></li>';
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns != ''){
										strExaminations+='<li><b style="color: #5a5a5a !important;">CNS : </b><small>';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns;
										strExaminations+='</small></li>';
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs != ''){
										strExaminations+='<li><b style="color: #5a5a5a !important;">RS : </b><small>';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs;
										strExaminations+='</small></li>';
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									
										
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA != ''){
										strExaminations+='<li><b style="color: #5a5a5a !important;">P/A : </b><small>';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA;
										strExaminations+='</small></li>';
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn != ''){
										strExaminations+='<li><b style="color: #5a5a5a !important;">General Examination : </b><small >';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn;
										strExaminations+='</small></li>';
										
									}
									
									if("strmuscularExamn" in data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation){
										if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn != ''){
											strExaminations+='<li><b style="color: #5a5a5a !important;">Muscular Examination : </b><small >';
											strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn;
											strExaminations+='</small></li>';
											
										}
									}
									
									if("strLocalExamn" in data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation){
										if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn != ''){
											strExaminations+='<li><b style="color: #5a5a5a !important;">Local Examination : </b><small >';
											strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn;
											strExaminations+='</small></li>';
											
										}
									}
									
									
									
									
									strExaminations+='</ul></p></div></div>';
									/*else{
										strExaminations+='No Record Found';
									}*/
									
									}
								}
								/*else
								{
									strExaminations+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>SYSTEMATIC EXAMINATION : </b><small>';
									strExaminations+='No Record Found';
									strExaminations+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';	
								}
								*/
								str+=strExaminations;
								
								
								if("strCompleteHistory" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory != '' || data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory != '' ||
											data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory != '' || 	data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory != '' ||
											data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory != '' ){
									strHistory+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>COMPLETE HISTORY : </b><ul>';
									
									strHistory+='<li> <p style="letter-spacing: inherit !important; color: #5a5a5a !important; font-weight: 600; text-align: justify;"><b style="color: #5a5a5a !important;">PAST HISTORY : </b><small style="font-weight: 400 !important;font-size: 14px;">';
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory != ''){
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory;
									}
									
									strHistory+='</small><p/></li>';
									
									strHistory+='<li><p style="letter-spacing: inherit !important; color: #5a5a5a !important; font-weight: 600; text-align: justify;"><b style="color: #5a5a5a !important;">PERSONAL HISTORY : </b><small style="font-weight: 400 !important;font-size: 14px;">';
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory != ''){
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory;
									}
									
									
									strHistory+='</small></p></li>';
									
									strHistory+='<li><p style="letter-spacing: inherit !important; color: #5a5a5a !important; font-weight: 600; text-align: justify;"><b style="color: #5a5a5a !important;">FAMILY HISTORY : </b><small style="font-weight: 400 !important;font-size: 14px;">';
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory != ''){
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory;
									}
									
									strHistory+='</small></p></li>';	
									
									
									strHistory+='<li><p style="letter-spacing: inherit !important; color: #5a5a5a !important; font-weight: 600; text-align: justify;"><b style="color: #5a5a5a !important;">TREATMENT HISTORY : </b><small style="font-weight: 400 !important;font-size: 14px;">';
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory != ''){
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory;
									}
									
									strHistory+='</small></p></li>';
									
									
									
									strHistory+='<li><p style="letter-spacing: inherit !important; color: #5a5a5a !important; font-weight: 600; text-align: justify;"><b style="color: #5a5a5a !important;">SURGICAL HISTORY : </b><small style="font-weight: 400 !important;font-size: 14px;">';
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory != ''){
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory;
									}
									
									strHistory+='</small></p></li>';
									strHistory+='</ul></p></div></div>';
								}
							}
								
								str+=strHistory;
								
								
								
								
								if("strChronicDisease" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									if(data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease.length > 0){
										strChronics+='<div class="row">'+
										'<div class="col-sm-12">'+
										'<p><b>CHRONIC DISEASES : </b></p>';
										//strChronics+='<ul>';
										for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease.length;j++){
											var CronicDiseaseName=data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease[j].CronicDiseaseName.split(';')[0];
											var CronicDiseaseDuration=data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease[j].CronicDiseaseDuration;
											var CronicDiseaseRemarks=data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease[j].CronicDiseaseRemarks;

											if(CronicDiseaseDuration != '' && CronicDiseaseRemarks != '')
												strChronics+=''+CronicDiseaseName+' ('+CronicDiseaseDuration+' yrs ) ('+CronicDiseaseRemarks+')';
											else if(CronicDiseaseDuration != '')
												strChronics+=''+CronicDiseaseName+' ('+CronicDiseaseDuration+' yrs )';
											else if(CronicDiseaseRemarks != '')
												strChronics+=''+CronicDiseaseName+' ( '+CronicDiseaseRemarks+')';
											else
												strChronics+=''+CronicDiseaseName+' ';
										}
										//strChronics+='</ul>';
										strChronics+='</div></div>';
									}
									/*else{
										strChronics+='<div class="row">'+
										'<div class="col-sm-12">'+
										'<p><b>CHRONIC DISEASES : </b><small>';
										strChronics+='No Record Found';
										strChronics+='</small></p>'+
										'</div>'+
										'</div>'+
										'<br>';
									}*/
									
								}
								/*else
								{
									strChronics+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>CHRONIC DISEASES : </b><small>';
									strChronics+='No Record Found';
									strChronics+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';	
								}*/
								
								str+=strChronics;
								
							
							/*str+='<div class="row">'+
							'<div class="col-sm-12">'+
								'<p><b>EXAMINATION </b><small class="">';
							
							str+='</small></p>'+
									'</div>'+
								'</div>'+
								'<br>';*/
							console.log("Diagnosis.log::::::::::::::::::1");
								if(data.pat_details[i].HRSTR_JSON_DATA.Diagnosis !=''){
								str+='<div class="row">'+
								'<div class="col-sm-12">'+
									'<p><b>DIAGNOSIS : </b><small class="diagnosis">';
								for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.Diagnosis.length;j++){
									
									var x=data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split('#')[4];
									var text='';
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
									
									
									var remeaksTemp= '';
									if(data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split("#")[7] != ''){
										remeaksTemp=' '+ data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split("#")[7]+' ' ;
									}else{
										remeaksTemp= '';
									}
									var temp=data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].replace("^","#").split("#");
									str+=temp[3].trim()+'( ' + data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split('#')[3]+ ' ' + (text == '' ? '' :  text)+' '+ remeaksTemp+ ' ), '; 
								}
								str+='</small></p>'+
										'</div>'+
									'</div>'+
									'<br>';
								}
								console.log("Diagnosis.log::::::::::::::::::2");
								
								if("strDiagnosisNote" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									if(data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote !=''){
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
										'<p><b></b><small>';
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote.length;j++){
										str+=data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote[j];
									}
									str+='</small></p>'+
										'</div>'+
										'</div>'+
										'<br>';
									}
								}
								/*else{
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>DIAGNOSIS NOTE : </b><small>';
									str+='No Record Found';
									str+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';
								}*/
								
								if(data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint !=''){
								str+='<div class="row">'+
								'<div class="col-sm-12">'+
									'<p><b>INVESTIGATIONS ADVISED : </b></p>'+
									'<ul class="investigation" id="menu1">';
								for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint.length;j++){
									var temp=data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split("^");
									var tempinvs=(data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split('^').length ==6 ?  data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split('^')[5].trim():data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split('^')[7].trim()) ;
									str+='<li>'+ (j+1)+') ' +tempinvs +' ,</li>';
								}
								str+='</ul>'+
										'</div>'+
									'</div>'+
									'<br>';
								}
								/*if("strInvestgationNote" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
										'<p><b>INVESTIGATION NOTE : </b><small>';
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strInvestgationNote.length;j++){
										str+=data.pat_details[i].HRSTR_JSON_DATA.strInvestgationNote[j].trim();
									}
									str+='</small></p>'+
										'</div>'+
										'</div>'+
										'<br>';
								}
								else{
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>INVESTIGATION NOTE : </b><small>';
									str+='No Record Found';
									str+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';
								}*/
								
								if("strClinicalProcedure" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									if(data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure.length > 0 && data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure !=''){
										strClinicalProc+='<div class="row">'+
										'<div class="col-sm-12">'+
										'<p><b>PROCEDURES : </b></p>';
										strClinicalProc+='<ul>';
										for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure.length;j++){
											
											if(data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#").length == 7){
												
												var strClinicalProcedureName=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[0];
												var strClinicalProcedureSite=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[3];
												var strClinicalProcedureRemarks=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[4];
												var strServicereaName=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[4];
												
												if(strClinicalProcedureSite.trim() != 'Site' && strClinicalProcedureRemarks!='')
													strClinicalProc+='<li>'+strServicereaName+' ['+strClinicalProcedureName+']'+', '+strClinicalProcedureSite+', '+strClinicalProcedureRemarks+'</li>';
												else if(strClinicalProcedureSite.trim() != 'Site')
													strClinicalProc+='<li>'+strServicereaName+' ['+strClinicalProcedureName+']'+', '+strClinicalProcedureSite+'</li>';
												else if(strClinicalProcedureRemarks != '')
													strClinicalProc+='<li>'+strServicereaName+' ['+strClinicalProcedureName+']'+', '+strClinicalProcedureRemarks+'</li>';
												else
													strClinicalProc+='<li>'+strServicereaName+' ['+strClinicalProcedureName+']'+'</li>';
												
												
											}else{
											var strClinicalProcedureName=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[0];
											var strClinicalProcedureSite=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[3];
											var strClinicalProcedureRemarks=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[4];
											
											if(strClinicalProcedureSite.trim() != 'Site' && strClinicalProcedureRemarks!='')
												strClinicalProc+='<li>'+strClinicalProcedureName+', '+strClinicalProcedureSite+', '+strClinicalProcedureRemarks+'</li>';
											else if(strClinicalProcedureSite.trim() != 'Site')
												strClinicalProc+='<li>'+strClinicalProcedureName+', '+strClinicalProcedureSite+'</li>';
											else if(strClinicalProcedureRemarks != '')
												strClinicalProc+='<li>'+strClinicalProcedureName+', '+strClinicalProcedureRemarks+'</li>';
											else
												strClinicalProc+='<li>'+strClinicalProcedureName+'</li>';
											}
											
										}
										strClinicalProc+='</div></div>';
									}
									/*else{
										strClinicalProc+='<div class="row">'+
										'<div class="col-sm-12">'+
										'<p><b>PROCEDURES : </b><small>';
										strClinicalProc+='No Record Found';
										strClinicalProc+='</small></p>'+
										'</div>'+
										'</div>'+
										'<br>';
									}*/
									
								}
								/*else
								{
									strClinicalProc+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>CLINICAL PROCEDURES : </b><small>';
									strClinicalProc+='No Record Found';
									strClinicalProc+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';	
								}*/
								
								str+=strClinicalProc;
								if(data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice.trim() != ''){
								str+='<div class="row">'+
								'<div class="col-sm-12">'+
									'<p style="text-align: justify;"><b>TREATMENT ADVICE : </b><small class="clinicalNote">';
						//for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice.length;j++){
							
							str+=data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice.trim();
						//}
						
						str+='</small></p>'+
								'</div>'+
							'</div>'+
							'<br>';
						}
						
								if(data.pat_details[i].HRSTR_JSON_DATA.DrugCodeCat !=''){
								str+='<div class="row">'+
								'<div class="col-sm-12" align="center">'+
									'<p style="text-align: left;font-size: small;font-family: cursive;margin-bottom: 0px;">Rx</p>'+
									'<ol class="printPrescTreatmentLst"> ';
								for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.DrugCodeCat.length;j++){
									
									var temp=data.pat_details[i].HRSTR_JSON_DATA.DrugCodeCat[j].split("&&");
									if(temp[8].trim() !='')
									str+='<li>'+temp[0].trim()+', '+temp[2].trim()+', '+temp[4].trim()+', '+temp[7].trim().split('#')[0]+' Days ,'+temp[6].trim()+', ('+temp[8].trim()+') </li>';
									else
										str+='<li>'+temp[0].trim()+', '+temp[2].trim()+', '+temp[4].trim()+', '+temp[7].trim().split('#')[0]+' Days ,'+temp[6].trim()+'</li>';	
									
									//str+='<li><b>'+temp[0].trim()+'</b></li>';
								}
								
								str+='</ol>'+
										'</div>'+
									'</div>'+
								'<br>';
								}
								/// Commented By Timsi. Do Not Remove. ///
								/*if("strDrugAllergy" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									if(data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy.length > 0){
										strAllergies+='<div class="row">'+
										'<div class="col-sm-12">'+
										'<p><b>ALLERGIES : </b></p>';
										strAllergies+='<ul>';
										for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy.length;j++){
											var strAllergyName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergyName.split(';')[0];
											var strAllergySytmptomsName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergySytmptomsName.split(';')[0];
											var strSensivityName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strSensivityName;
											var strAllergysiteName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergysiteName.split(';')[0];
											var stDurationTime=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].stDurationTime;
											var strAllergyRemarks=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergyRemarks;
		
											if(strAllergysiteName != '' && strSensivityName!='')
												strAllergies+='<li>'+strAllergyName+'('+strSensivityName+' on '+strAllergysiteName+'), '+strAllergySytmptomsName+', '+stDurationTime+' yrs, '+strAllergyRemarks+'</li>';
											else
												strAllergies+='<li>'+strAllergyName+', '+strAllergySytmptomsName+', '+stDurationTime+' yrs, '+strAllergyRemarks+'</li>';
											
										}
										strAllergies+='</div></div>';
									}
									else{
										strAllergies+='<div class="row">'+
										'<div class="col-sm-12">'+
										'<p><b>ALLERGIES : </b><small>';
										strAllergies+='No Record Found';
										strAllergies+='</small></p>'+
										'</div>'+
										'</div>'+
										'<br>';
									}
									
								}
								else
								{
									strAllergies+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p><b>ALLERGIES : </b><small>';
									strAllergies+='No Record Found';
									strAllergies+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';	
								}
								
								str+=strAllergies;
							*/
							
							//Put Treatment Advice Here
							if("strDrugAllergy" in (data.pat_details[i].HRSTR_JSON_DATA)){	
								if(data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy !=''){
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
										'<p><b>DRUG ALLERGY : </b></p>'+
										'<ul class="drugallergy" id="menu1">';
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy.length;j++){
										var temp=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergyName;
										str+='<li>'+ (j+1)+') ' +temp+' ,</li>';
									}
									str+='</ul>'+
											'</div>'+
										'</div>'+
										'<br>';
									}else{
										
								/*		str+='<div class="row">'+
										'<div class="col-sm-12">'+
											'<p><b>DRUG ALLERGY : </b></p>'+
											'<ul class="drugallergy" id="menu1">';
										str+='<li> No Known Drug Allergy </li>';
										
										str+='</ul>'+
												'</div>'+
											'</div>'+
											'<br>'; */
										
										
										str+='<div class="row">'+
										'<div class="col-sm-12">';
											
									//	str+='<li> No Known Drug Allergy </li>';
  								    	
										str+='</div>'+
											'</div>'+
											'<br>';
									}
						}else{
							
						/*	str+='<div class="row">'+
							'<div class="col-sm-12">'+
								'<p><b>DRUG ALLERGY : </b></p>'+
								'<ul class="drugallergy" id="menu1">';
							str+='<li> No Known Drug Allergy </li>';
							
							str+='</ul>'+
									'</div>'+
								'</div>'+
								'<br>'; */
							
							str+='<div class="row">'+
							'<div class="col-sm-12">';
								
					//		str+='<li> No Known Drug Allergy </li>';
							
							str+='</div>'+
								'</div>'+
								'<br>';
							
						}
				
							
								
								
								if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP !=''){
								
								for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP.length;j++){
									if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].progressNote.trim() !=''){
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
									'<p style="text-align: justify;"><b>CLINICAL NOTE : </b><small class="clinicalNote">';
									str+=data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].progressNote.trim();
									str+='</small></p>'+
									'</div>'+
									'</div>'+
									'<br>';
									 }
									}
								}
								
								/*if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory != ''){
									//console.log('result.strHistoryOfPresentIllNess'+data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess);
									  $('#pastHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory[0].strsurgicalHistory);
								}*/
						
								//Showing HOPI of last visit ,if not required should be commented	
								if(data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess != ''){
									//console.log('result.strHistoryOfPresentIllNess'+data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess);
									
									if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
									/*	var today = new Date(); 
										 var dd = today.getDate();
										 dd = dd.toString().length>1 ? dd : "0"+dd;
										 var mm = today.getMonth() + 1;
										 mm = mm.toString().length>1 ? mm : "0"+mm;
										 var yyyy=today.getFullYear();
										 today = dd+'/'+mm+'/'+yyyy; */
										console.log('----------------------------------->'+todaysys);
										console.log('----------------------------------->'+new Date().getDate()+'/'+(new Date().getMonth()+1)+'/'+new Date().getFullYear());
										 console.log('----------------------------------->'+data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate);
										 console.log(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate); 
										 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										 $('#hopiId').val(data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess);
										
									}																																
									
								}
							
								
								if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP !=''){
							
								for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP.length;j++){
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
										'<p style="text-align: justify;"><b>FOLLOW UP : </b><small class="followUp">';
									
									if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitSos!='' && data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitSos!=null){
										str+=data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitSos;
										
									}
									else if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDays!='' && data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDays!=null){
										str+='After '+data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDays+' Days';
										$('#followUpPlannedVisitDaysId').val(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDays);
										
									}
									else if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDate!='' && data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDate!=null){
										str+='On '+data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDate;
										//$('#followUpPlannedVisitDateId').val('data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].Planned_Visit[0].plannedVisitDate');
									}
									str+='</small></p>'+
									'</div>'+
								'</div><br>';
								}
							}
								
								
								
								if("strReferal" in data.pat_details[i].HRSTR_JSON_DATA ){
								if(data.pat_details[i].HRSTR_JSON_DATA.strReferal.length > 0){
									
									str+='<div class="row">'+
									'<div class="col-sm-12">'+
										'<p style="text-align: justify;"><b>Refer To.: </b><small class="clinicalNote"><br>';
									
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strReferal.length;j++){
									
										if("strShowData" in data.pat_details[i].HRSTR_JSON_DATA.strReferal[j]){
											var strrefertext='';
											if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strShowData != ''){
												 strrefertext =data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strShowData ;
											 if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strReffralReason !='')
												 strrefertext = strrefertext + '( ' +data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strReffralReason +')' ;
											 if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strreferralType !='0')
												 strrefertext = strrefertext + '[ ' +data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strreferralTypeName +']' ;
												 
											/* if(data.strReferal[j].strReffralReason !='') */
											//	$('.printPrescPage .refferPatientDept').append('<li><p>'+strrefertext+ ' </li></p>');	 
											/* else if(data.strReferal[j].strreferralType != '0')
												$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext+ '['+data.strReferal[j].strreferralTypeName+']'+' </li></p>');
											else
												$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext +' </li></p>'); */
											str+=strrefertext+'<br>'; 
											}
											
											
										}else{
										
									if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strReffralReason.trim() != '')
									str+=data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strReffralDepttext.trim()+" ("+data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strReffralReason.trim()+" ) , ";
									else
									str+=data.pat_details[i].HRSTR_JSON_DATA.strReferal[j].strReffralDepttext.trim()+", ";	
								
										}
								
										
									}
									
									
									str+='</small></p>'+
									'</div>'+
								'</div>'+
								'<br>';
									
							}
						}
								/*'<br>'+ 
								'<div id="patQrCode" class="pull-right"></div>'+  
								'<br>'+*/
								str+='<br>'+
								'<h5 style="font-weight:bold; margin-top:0;margin-bottom:0;">Signature of Consultant / Resident:</h5>'+'<br>'+ 
								'<h5 class="consultantName" style="margin-top:0">'+data.pat_details[i].USER_NAME.trim()+'<br>'+data.pat_details[i].DATASAVE_TIME.trim()+'</h5>'
								
								
							'</div>';
							
							 var date = new Date();
							 document.title=window.patCrNo+date.getDate()+(date.getMonth()+1)+date.getFullYear();
							 console.log('Date:::::::::>>>>>>>'+date.getDate()+(date.getMonth()+1)+date.getFullYear());
							 console.log('time ::>>>>>>>>>>>>>>'+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds());
							 //console.log('data.CR_No.toString()+(date.getDate()+date.getMonth()+date.getFullYear())::::::::::>>>>>>>'+data.CR_No.toString()+date.getDate()+(date.getMonth()+1)+date.getFullYear());
								
							 //str+='<table><thead></thead><tbody></tbody><tfoot><tr><td><div class="page-footer-space"></div></td></tr></tfoot>';
							 /*var dateTime=date.getDate()+'-'+(date.getMonth()+1)+'-'+date.getFullYear()+' at '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
							 str+='<div class="footerPastPresc">CRNo. <font class="footerCrNo">'+window.patCrNo+'</font> Printing Date : <font class="printDateAndTime>'+dateTime+'</font></div>';*/
							$('.opdEmrModalNavMenuContent').append('<div class="tab-pane" id="'+menuId+(i+1)+'">'+str+'</div>');
							
						}
						$('.opdEmrModalNavMenu').children('li').eq(0).addClass('active');
						/*var temp = $('.opdEmrModalNavMenu li.active a').attr('href');
						alert(temp);*/
						$('.opdEmrModalNavMenuContent').children('div').eq(0).addClass('active');
						
					}
					else{
						$('#prescriptionListMsg').text('No Past Precriptions Found !');	
					}
				},
				complete: $('#prescriptionListMsg').text('Error !!!')
		});
		
		  $('#followUpPlannedVisitRefreshId').tooltip({
	        placement:"top"
	      });
		  $('#treeStructurePrescBtnId').tooltip({
	        placement:"top"
	      });
	      $('#opdEmrBtnId').tooltip({
	        placement:"top"
	      });
	      $('#bmiIdValue').tooltip({
	        placement:"top"
	      });
	      $('#temperatureIdValue').tooltip({
	        placement:"top"
      	  });
	      $('#bloodPressureIdValue').tooltip({
	        placement:"top"
      	  });
	      $('#pulseIdValue').tooltip({
	        placement:"top"
	      });
	      $('#drugFrequency').tooltip({
	        placement:"top"
	      });
	      $('#drugInstructions').tooltip({
	        placement:"top"
	      });
	      $('#externalDrugFrequency').tooltip({
	        placement:"top"
	      });
	      $('#externalDrugInstructions').tooltip({
	        placement:"top"
	      });
	      $('#vitalModify').tooltip({
	        placement:"top"
	      });
	      $('.clearAllValues').tooltip({
	        placement:"top"
	      });
	      $('#respRateIdValue').tooltip({
		        placement:"top"
		      });
	      $('.reasonOfVisitAdd').tooltip({
		        placement:"top"
		      });
	      
	      
	      $(window).bind("resize", function () {
	          if($(window).width() <= 576){
	            $('.siteDiv').removeClass('paddingLeftRightZero');
	            $('.itemTypeDiv').removeClass('paddingLeftRightZero');
	            $('.chiefComplaintDuration').removeClass('alignLeftPaddingLeftZero');
	            $('.chiefComplaintNoOfDays').removeClass('paddingRightZero');
	            $('.chiefComplaintDuration').addClass('paddingLeftRightZero');
	            $('.chiefComplaintNoOfDays').addClass('paddingLeftRightZero');
	            $('.quantityDiv').removeClass('paddingRightZero');
	          }
	        }).trigger('resize');

	        $(window).bind("resize", function () {
	          if($(window).width() <= 767){
	            $('.siteDiv').removeClass('paddingLeftRightZero');
	            $('.itemTypeDiv').removeClass('paddingLeftRightZero');
	            $('.chiefComplaintDuration').removeClass('alignLeftPaddingLeftZero');
	            $('.chiefComplaintNoOfDays').removeClass('paddingRightZero');
	            $('.chiefComplaintDuration').addClass('paddingLeftRightZero');
	            $('.chiefComplaintNoOfDays').addClass('paddingLeftRightZero');
	            $('.quantityDiv').removeClass('paddingRightZero');
	          }
	        }).trigger('resize');

	        $(window).bind("resize", function () {
	          if($(window).width() > 768){
	            $('.siteDiv').addClass('paddingLeftRightZero');
	            $('.itemTypeDiv').addClass('paddingLeftRightZero');
	            $('.chiefComplaintDuration').addClass('alignLeftPaddingLeftZero');
	            $('.chiefComplaintNoOfDays').addClass('paddingRightZero');
	            $('.chiefComplaintDuration').removeClass('paddingLeftRightZero');
	            $('.chiefComplaintNoOfDays').removeClass('paddingLeftRightZero');
	            $('.quantityDiv').addClass('paddingRightZero');
	          }
	        }).trigger('resize');
	        
	        $(window).bind("resize", function () {
	            if($(window).width() < 602){
	              $('.AddToggleClass').addClass('btn-block');
	            }
	            else{
	              $('.AddToggleClass').removeClass('btn-block');
	            }
	          }).trigger('resize');
	        
	        /* this is use to EMR / History Data */
	        var strEpisodeCodeChk=window.episodeCode;
	      $.ajax({url:'/HISDRDESK/services/restful/patdata/getPatDataForPastPrescription?Modval=2&CrNo='+window.patCrNo+'&episodeCode='+window.episodeCode +'&visitNo='+window.visitNo+'&seatId=&Entrydate=&hosp_code='+window.hospCode+'',
				async:true,
				beforesend : $('.TreeStructurePrescriptionModalErrMsg').parent().append('<p id="TreeStructurePrescriptionListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
					success:function(data){ 
						var strProfileInfo = '' , strChiefComplaint = '' , strDiagnosis = '' , strInvestigations = '' , strDrugsAndAdvices = '' , strClinicalNote = '';
						var strHistory = '' , strExaminations = '' , strAllergies = '' , strChronics = '' , strClinicalProc = '' ;
						var strtreatmentAdvice123 ='';
						var strClinicalProcedure ='';
						var strTreatmentAdviceService ='';
						var str = '';
						var strconfidentialInfo ='';
						
						//alert(Object.keys(data).length);
						//alert(Object.keys(data.pat_details).length);
						if(Object.keys(data.pat_details).length > 0){
							
							var strmodalNavContent ='<li><a href="#TreeStructurePrescriptionModalTab2" data-toggle="tab"><i class="far fa-address-card"></i> Profile</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab3" data-toggle="tab"><i class="fa fa-heartbeat" aria-hidden="true"></i> Vitals / GE</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab9" data-toggle="tab" aria-expanded="false"><i class="fas fa-pen-square"></i> History</a></li>'+
			               // '<li><a href="#TreeStructurePrescriptionModalTab9" data-toggle="tab">History</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab10" data-toggle="tab"><i class="fas fa-stethoscope"></i> Examinations</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab11" data-toggle="tab"><i class="fas fa-allergies"></i> Allergies</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab12" data-toggle="tab"><i class="fas fa-x-ray"></i> Chronic Diseases</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab4" data-toggle="tab"><i class="fas fa-user-md"></i> Chief Complaint</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab5" data-toggle="tab"><i class="fas fa-diagnoses"></i> Diagnosis</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab6" data-toggle="tab"><i class="fas fa-microscope"></i> Investigations</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab13" data-toggle="tab"><i class="fas fa-procedures"></i> Clinical Procedures</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab15" data-toggle="tab"><i class="fas fa-prescription"></i> Treatment Advices</a></li>' +
			                '<li><a href="#TreeStructurePrescriptionModalTab7" data-toggle="tab"><i class="fas fa-pills"></i> Drug/Advices</a></li>'+
			                //'<li><a href="#TreeStructurePrescriptionModalTab7" data-toggle="tab">Drug/ Advices</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab16" data-toggle="tab"><i class="fas fa-ambulance"></i> Patient Ref.</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab17" data-toggle="tab"><i class="fas fa-ambulance"></i>Confidential info.</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab18" data-toggle="tab"><i class="fas fa-user-shield"></i> Template Info.</a></li> '+
			                '<li><a href="#TreeStructurePrescriptionModalTab19" data-toggle="tab"><i class="fas fa-user-shield"></i>Procedure Administration</a></li>'+
			                '<li><a href="#TreeStructurePrescriptionModalTab20" data-toggle="tab"><i class="fas fa-user-shield"></i>Patient Document</a></li>';
		
							$('.TreeStructurePrescriptionModalNavMenu').append(strmodalNavContent);
		
							$('#TreeStructurePrescriptionListMsg').remove();
							
							$('.TreeStructurePrescriptionModalNavMenu').children('li').eq(0).remove();
							$('.TreeStructurePrescriptionModalNavMenuContent').children('div').eq(0).remove();
							
							var months1 = new Array(12);
						 	months1[1] = "Jan";
						 	months1[2] = "Feb";
						 	months1[3] = "Mar";
						 	months1[4] = "Apr";
						 	months1[5] = "May";
						 	months1[6] = "Jun";
						 	months1[7] = "Jul";
						 	months1[8] = "Aug";
						 	months1[9] = "Sep";
						 	months1[10] = "Oct";
						 	months1[11] = "Nov";
						 	months1[12] = "Dec";
						 	
							var today = new Date();
							var printDate = today.getDate()+'-'+months1[today.getMonth()+1]+'-'+today.getFullYear();
							var printTime = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
							
							str+='<div class="row"><h4 class="text-center" style="font-weight:bold">PATIENT CLINICAL DATA</h4>'+
							'</div>'+  
							'<div class="" style="border-top: 1px solid grey; border-bottom: 1px solid grey;">'+
							'<table class="table table-condensed printPrescPatDtlTbl table-responsive">'+
							'<tbody>'+
							'<tr>'+
							'<th>Name</th><td class="patName">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.pat_Name.trim();
							str+='</td><th>CR No.</th><td class="patCrNo">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.CR_No.trim();
							str+='</td></tr>';
							str+='<tr>'+
							'<th>Department(Unit/Consultant)</th><td class="patDeptU">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.patDept.trim();
							str+='</td><th>Reprinted On</th><td class="printedOn">';
							str+=printDate+' / '+printTime;
							str+='</td></tr>';
							
							str+='<tr>'+
							'<th>Mobile No</th><td class="patDeptU">';
							if((data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[14].trim())
							str+=(data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[14].trim();
							
							if($('#strRailTailFlgId').val() == '0'){
							
							str+='</td><th>Occupation</th><td class="printedOn">';
							str+=(data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[17].trim();
							str+='</td></tr>';
							}else{
								var patOthersDetails1=$('#patOthersDetailsPrescriptionPanel').html()
								if(patOthersDetails1 !='{}'){
								str+='</td><th>Designation</th><td class="printedOn">';
								str+=(JSON.parse(patOthersDetails1).designation);
								str+='</td></tr>'
									
									str+='<tr>'+
									'<th>Station</th><td class="patDeptU">';
								
								str+=(JSON.parse(patOthersDetails1).custom_unit);
								str+='</td><th></th><td></td></tr>'
								} else{
									str+='</td><th>Occupation</th><td class="printedOn">';
									str+=(data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[17].trim();
									str+='</td></tr>';
									
								}
									
							}
							
							//console.log("patOthersDetailsPrescriptionPanel ::::::::::  "  +$('#patOthersDetailsPrescriptionPanel').html());
							
							str+='</tbody></table></div>';
							
							/*str+='<div class="row"><h4 class="text-center" style="font-weight:bold">AIIMS PATNA OPD PRESCRIPTION</h4>'+
							'</div>'+  
							'<div class="" style="border-top: 1px solid grey; border-bottom: 1px solid grey;">'+
							'<table class="table table-condensed printPrescPatDtlTbl table-responsive">'+
							'<tbody>'+
							'<tr>'+
							'<th>Name</th><td class="patName">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.pat_Name.trim();
							str+='</td><th>CR No.</th><td class="patCrNo">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.CR_No.trim();
							str+='</td></tr><tr><th>Age/Gender</th><td class="patAgeGen">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.patAge.trim()+'/'+data.pat_details[0].HRSTR_JSON_DATA.patGender.trim();
							str+='</td><th>Patient Category</th><td class="patCat">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.patCat.trim();
							str+='</td></tr><tr>'+
								'<th>Father/Spouse Name</th><td class="patRelName">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.patGaurdianName.trim();
							str+='</td><th>Consultant Name</th><td class="consultantName">';
							str+=data.pat_details[0].HRSTR_JSON_DATA.patConsultantName.trim();
							str+='</td></tr></tbody></table></div>';*/
							
							strProfileInfo+='<div class="row"><div class="profileInfo col-md-6">'+
							'<table class="table table-condensed printPrescPatDtlTbl table-responsive">'+
							'<tbody>'+
							'<tr>'+
							'<th>Patient Name : </th><td class="patName">';
							strProfileInfo+=data.pat_details[0].HRSTR_JSON_DATA.pat_Name.trim();
							strProfileInfo+='</td></tr><tr><th>CR No. : </th><td class="patCrNo">';
							strProfileInfo+=data.pat_details[0].HRSTR_JSON_DATA.CR_No.trim();
							strProfileInfo+='</td></tr><tr><th>Gender : </th><td class="patGen">';
							
							if(data.pat_details[0].HRSTR_JSON_DATA.patGender.trim() == 'M'){
								strProfileInfo+='Male';
							}
							else if(data.pat_details[0].HRSTR_JSON_DATA.patGender.trim() == 'F'){
								strProfileInfo+='Female';
							}
							
							strProfileInfo+='</td></tr><tr><th>Age : </th><td class="patAge">';
							strProfileInfo+=data.pat_details[0].HRSTR_JSON_DATA.patAge.trim();
							strProfileInfo+='</td></tr><tr><th>Category : </th><td class="patCat">';
							strProfileInfo+=data.pat_details[0].HRSTR_JSON_DATA.patCat.trim();
							strProfileInfo+='</td></tr>';
							/*strProfileInfo+='<tr><th>Refered By : </th><td>';
							strProfileInfo+=''; // Fill in the refered by Consultant name
							strProfileInfo+='</td></tr>';
							strProfileInfo+='<tr>'+*/
							strProfileInfo+='<th>Father/Spouse Name : </th><td class="patRelName">';
							strProfileInfo+=data.pat_details[0].HRSTR_JSON_DATA.patGaurdianName.trim();
							strProfileInfo+='</td></tr> '; 
							
							strProfileInfo+='</tr><tr>'+
						    '<th>Mobile No : </th><td class="patRelName">';
							strProfileInfo+=(data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[14];
							strProfileInfo+='</td></tr> ';
							if($('#strRailTailFlgId').val() == '0'){
							
							strProfileInfo+='</tr><tr>'+
						    '<th>Occupation : </th><td class="patRelName">';
							strProfileInfo+=(data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[17];
							strProfileInfo+='</td></tr> ';
							}else{
								var patOthersDetails1=$('#patOthersDetailsPrescriptionPanel').html();
								if(patOthersDetails1 !='{}'){
								strProfileInfo+='</tr><tr>'+
							    '<th>Designation : </th><td class="patRelName">';
								strProfileInfo+=(JSON.parse(patOthersDetails1).designation);
								strProfileInfo+='</td></tr> ';
								
								strProfileInfo+='</tr><tr>'+
							    '<th>Station : </th><td class="patRelName">';
								strProfileInfo+=(JSON.parse(patOthersDetails1).custom_unit);
								strProfileInfo+='</td></tr> ';
								}else{
									strProfileInfo+='</tr><tr>'+
								    '<th>Occupation : </th><td class="patRelName">';
									strProfileInfo+=(data.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[17];
									strProfileInfo+='</td></tr> ';
									
								}
							}
							
							strProfileInfo+='</tbody></table></div>';
							
							if(data.pat_details[0].HRSTR_JSON_DATA.patGender.trim() == 'M'){
								strProfileInfo+='<div class="col-md-3" style="">'+
								  '<img src="/HISDRDESK/hisglobal/images/MaleIcon.png" style="height:108px;width:108px;">'+
								'</div>';
							}
							else if(data.pat_details[0].HRSTR_JSON_DATA.patGender.trim() == 'F'){
								strProfileInfo+='<div class="col-md-3" style="">'+
								  '<img src="/HISDRDESK/hisglobal/images/FemaleIcon.png" style="height:108px;width:108px;">'+
								'</div>';
							}
							
							strProfileInfo+='</div>'; //End of row
							strProfileInfo+='<p>OPD Visit Summary:  (Last 5)</p>';
							
							for(var i=0;i<Object.keys(data.pat_details).length;i++){  
								
								var visitDate = data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate.trim();
								var temp = visitDate.split("/");
								var months = new Array(12);
							 	months[1] = "Jan";
							 	months[2] = "Feb";
							 	months[3] = "Mar";
							 	months[4] = "Apr";
							 	months[5] = "May";
							 	months[6] = "Jun";
							 	months[7] = "Jul";
							 	months[8] = "Aug";
							 	months[9] = "Sep";
							 	months[10] = "Oct";
							 	months[11] = "Nov";
							 	months[12] = "Dec";

							 	var maxDate = temp[0]+" "+months[temp[1]] +", "+temp[2];
							 	
							 	if(i<7){ //For details of last 7 visits
							 	strProfileInfo+='<table class="table table-condensed PatVisitSummaryDtlTbl table-responsive">'+
								'<tbody>'+
								'<tr><th>Dept: </th><td class="dept"><td>';
							 	strProfileInfo+=data.pat_details[i].HRSTR_JSON_DATA.patDept.trim();
							 	strProfileInfo+='</td><th>Visit Date: </th><td class="visitDate">';
							 	strProfileInfo+=data.pat_details[i].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim().split('#')[7];
							 	strProfileInfo+='</td><th>Unit Head: </th><td>';
							 	strProfileInfo+=data.pat_details[i].HRSTR_JSON_DATA.patConsultantName.trim();
							 	strProfileInfo+='</td></tr></table>';
							 	
							 	/*strProfileInfo+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
							 	strProfileInfo+='<p>Consultant Name : <small>';
								strProfileInfo+=data.pat_details[i].HRSTR_JSON_DATA.patConsultantName.trim();
								strProfileInfo+='</small></p>';*/
							 	}
							 	
							 	strChiefComplaint+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
								strChiefComplaint+='<p><small>';
								if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit.length > 0){
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit.length;j++){
										
										
										var text='';
										var text1='';
										console.log('Visit Reason Length:::::::'+data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^').length);
										if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^').length == 2)
											{
											VisitReason=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j]+'^0^^0';
											}else{
												VisitReason=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j];
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
										
										
										
										
										var remarks_temp ='';
										if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^').length == 6){
										if(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[5].trim() != '' )
											{
											remarks_temp =' '+data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[5].trim()+' ';
											}else{
												remarks_temp='';
											}
										}
										//strChiefComplaint+=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[1].trim()+remarks_temp+', ';
										strChiefComplaint+=data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[1].trim()+(text == '' ? '' : ' ('+text+','+(data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[3].trim()==''? '' : +data.pat_details[i].HRSTR_JSON_DATA.ReasonOfVisit[j].split('^')[3].trim()+' '+ text1) +')')+remarks_temp+', '
									}
								}
								else{
									strChiefComplaint+='No Record Found';
								}
								
								strChiefComplaint+='</small></p>';
								
								
								strDiagnosis+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</p></u>';
								//console.log('strEpisodeCodeChk  '+strEpisodeCodeChk);
								//console.log('strEpisodeCodeChk1  '+data.pat_details[i].HRSTR_JSON_DATA.episodeCode);
								if(data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote != ''){
									strDiagnosis+='<p><small><b>Diagnosis Note</b> : ';
									strDiagnosis+=data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote;
									strDiagnosis+='</small></p>';
									//Past diagnosis note
									//if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode)
									//$('#diagnosisNoteId').val(data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote);
									
									if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
										
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#diagnosisNoteId').val(data.pat_details[i].HRSTR_JSON_DATA.strDiagnosisNote);
											
										}	
									
								}
								
								
								strDiagnosis+='<p><small>';
								if(data.pat_details[i].HRSTR_JSON_DATA.Diagnosis.length > 0){
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.Diagnosis.length;j++){
										
										var x=data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split('#')[4];
										var text='';
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
										
										
										var remeaksTemp= '';
										if(data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split("#").length == 8){
										if(data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split("#")[7] != ''){
											remeaksTemp=' '+ data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split("#")[7]+' ' ;
										}else{
											remeaksTemp= '';
										}
									}
										var temp=data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].replace("^","#").split("#");
										//strDiagnosis+=temp[3].trim()+remeaksTemp+', ';
										
										strDiagnosis+=temp[3].trim()+'( ' + data.pat_details[i].HRSTR_JSON_DATA.Diagnosis[j].split('#')[3]+ ' ' + (text == '' ? '' :  text)+' '+ remeaksTemp+ ' ), '; 
									}
								}
								else{
									strDiagnosis+='No Record Found';
								}
								strDiagnosis+='</small></p>';
								
								
								
								strInvestigations+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</p></u>';
								if(data.pat_details[i].HRSTR_JSON_DATA.strInvestgationNote != ''){
									strInvestigations+='<p><small><b>Investigation Note</b> : ';
									strInvestigations+=data.pat_details[i].HRSTR_JSON_DATA.strInvestgationNote;
									strInvestigations+='</small></p>';
									//past investigation note
									//if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode)
									//$('#investigationNoteId').val(data.pat_details[i].HRSTR_JSON_DATA.strInvestgationNote);
									if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
										
										 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
									     $('#investigationNoteId').val(data.pat_details[i].HRSTR_JSON_DATA.strInvestgationNote);
										
									}
								}
								
								if(data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint.length > 0){
									strInvestigations+='<p><ol class="investigation">';
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint.length;j++){
										var temp=data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split("^");
										var tempinvs=(data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split('^').length ==6 ?  data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split('^')[5].trim():data.pat_details[i].HRSTR_JSON_DATA.InvTestCodeToPrint[j].split('^')[7].trim()) ;
										strInvestigations+='<li>'+tempinvs.trim()+'</li>';
									}
									strInvestigations+='</ol></p>';
								}
								else{
									strInvestigations+='<p><small>';
									strInvestigations+='No Record Found';
									strInvestigations+='</small></p>';
								}
								
								
								
								strDrugsAndAdvices+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</p></u>';
								if(data.pat_details[i].HRSTR_JSON_DATA.DrugCodeCat.length > 0){
									strDrugsAndAdvices+='<p><ol class="printPrescTreatmentLst">';
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.DrugCodeCat.length;j++){
										var temp=data.pat_details[i].HRSTR_JSON_DATA.DrugCodeCat[j].split("&&");
										if(temp[8].trim() !='')
										strDrugsAndAdvices+='<li>'+temp[0].trim()+', '+temp[2].trim()+', '+temp[4].trim()+', '+temp[7].trim().split('#')[0]+' Days,'+temp[6].trim() + '('+temp[8].trim()+') </li>';
										else
											strDrugsAndAdvices+='<li>'+temp[0].trim()+', '+temp[2].trim()+', '+temp[4].trim()+', '+temp[7].trim().split('#')[0]+' Days,'+ temp[6].trim()+'</li>';
									}
									strDrugsAndAdvices+='</ol></p>';
								}
								else{
									strDrugsAndAdvices+='<p><small>';
									strDrugsAndAdvices+='No Record Found';
									strDrugsAndAdvices+='</small></p>';
								}
								
								
								strClinicalNote+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</p></u>';
								strClinicalNote+='<p><small>';
								if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP.length > 0){
									for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP.length;j++){
										if(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].progressNote.trim() != ''){
											strClinicalNote+=data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].progressNote.trim();
											//Past Data fill Clinical progress Note
										//	if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode)
										//	$('#progressNote').val(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].progressNote.trim());
											if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
												
												 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
												 $('#progressNote').val(data.pat_details[i].HRSTR_JSON_DATA.FOLLOW_UP[j].progressNote.trim());
												
											}
											
										}
										/*else{
											strClinicalNote+='No Record Found';
										}*/
									}
								}
								else{
									strClinicalNote+='No Record Found';
								}
								strClinicalNote+='</small></p>';
								
								
								if("strCompleteHistory" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									
									strHistory+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
								
									if(data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess != ''){
										strHistory+='<p><small><b>HOPI</b> : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strHistoryOfPresentIllNess;
										strHistory+='</small></p>';
									}
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory != ''){
										strHistory+='<p><small><b>Surgical History</b> : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory;
										strHistory+='</small></p>';
										// $('#surgicalHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory);
										 
										 if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
												
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#surgicalHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strsurgicalHistory);
											
										}
										
									}
									/*else{
										strHistory+='No Record Found';
									}*/
									
									/*if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory != ''){
										strHistory+='<p><small>Treatment History : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory;
										strHistory+='</small></p>';
									}*/
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory != ''){
										strHistory+='<p><small><b>Personal History</b> : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory;
										strHistory+='</small></p>';
									//	$('#personalHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory);
										 if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
												
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#personalHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpersonalHistory);
											
										}
									}
									/*else{
										strHistory+='No Record Found';
									}*/
									
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory != ''){
										strHistory+='<p><small><b>Treatment History</b> : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory;
										strHistory+='</small></p>';	
										//$('#treatmentHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory);
										 if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
												
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#treatmentHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strtreatmentHistory);
											
										}
									}
									/*else{
										strHistory+='No Record Found';
									}*/
									
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory != ''){
										strHistory+='<p><small><b>Past History</b> : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory;
										strHistory+='</small></p>';
										// $('#pastHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory);
										 if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
												
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#pastHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strpastHistory);
											
										}
									}
									/*else{
										strHistory+='No Record Found';
									}*/
									
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory != ''){
										strHistory+='<p><small><b>Family History</b> : ';
										strHistory+=data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory;
										strHistory+='</small></p>';
										//$('#familyHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory);
										if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
											
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#familyHistoryId').val(data.pat_details[i].HRSTR_JSON_DATA.strCompleteHistory.strfamilyHistory);
											
										}
									}
								/*	else{
										strHistory+='No Record Found';
									}*/
									
									
								}
								else
								{
									strHistory+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strHistory+='<p><small>';
									strHistory+='No Record Found';
									strHistory+='</small></p>';	
								}
								
								
								if("strSystematicExamniation" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									
									strExaminations+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs != ''){
										strExaminations+='<p><small><b>CVS</b> : ';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs;
										strExaminations+='</small></p>';
										//$('#cvsId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs);
										if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
											
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#cvsId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs);
											
										}
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns != ''){
										strExaminations+='<p><small><b>CNS</b> : ';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns;
										strExaminations+='</small></p>';
										// $('#cnsId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns);
										if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
											
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#cnsId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns);
											
										}
									}
									/*else{
										strExaminations+='No Record Found';
									}
									
									strExaminations+='</small></p>';*/
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs != ''){
										strExaminations+='<p><small><b>RS</b> : ';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs;
										strExaminations+='</small></p>';	
										// $('#rsId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs);
										if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
											
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#rsId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs);
											
										}
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									
									
									
									
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA != ''){
										strExaminations+='<p><small><b>P/A</b> : ';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA;
										strExaminations+='</small></p>';
										//$('#pAId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA);
										if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
											
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#pAId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA);
											
										}
									}
									/*else{
										strExaminations+='No Record Found';
									}*/
									if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn != ''){
										strExaminations+='<p><small><b>General Examination</b> : ';
										strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn;
										strExaminations+='</small></p>';
										// $('#otherExamnId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn);
										if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
											
											 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
										     $('#otherExamnId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn);
											
										}
									}
									
									
									if("strmuscularExamn" in data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation){
										if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn != ''){
											strExaminations+='<p><small><b>Muscular Examination</b> : ';
											strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn;
											strExaminations+='</small></p>';
											// $('#muscularExamnId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn);
											if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
												
												 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
											     $('#muscularExamnId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn);
												
											}
										}
									}
									
									if("strLocalExamn" in data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation){
										if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn != ''){
											strExaminations+='<p><small><b>Locl Examination</b> : ';
											strExaminations+=data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn;
											strExaminations+='</small></p>';
											// $('#LocalExamnId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn);
											 if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
													
												 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
											     $('#LocalExamnId').val(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn);
												
											}
										}
									}
									
									
									else{
										strExaminations+='<p><small>';
										strExaminations+='No Record Found';
										strExaminations+='</small></p>';
									}
									
									
								}
								else
								{
									strExaminations+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strExaminations+='<p><small>';
									strExaminations+='No Record Found';
									strExaminations+='</small></p>';	
								}
								
					/*			if(data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcvs != '' && data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strcns != '' && data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strrs != '' && data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strpA != '' && data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strotherExamn != '' && data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strmuscularExamn != '' && data.pat_details[i].HRSTR_JSON_DATA.strSystematicExamniation.strLocalExamn != '' ){
									strExaminations+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strExaminations+='<p><small>';
									strExaminations+='No Record Found';
									strExaminations+='</small></p>';	
								}*/
								
								/*console.log('Treatment Advice'+data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice);
								console.log('Treatment Advice length:::::'+data.pat_details[i].length);*/
								
								
								
								if(data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice != ''){
									console.log('Inside Treatment Advice');
									strtreatmentAdvice123+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strtreatmentAdvice123+='<p><small>';
									strtreatmentAdvice123+=data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice;
									strtreatmentAdvice123+='</small></p>';
									//past treatment advice
									//if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode)
									//$('#treatmentAdviceId').val(data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice);
									
									if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
										
										 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
									     $('#treatmentAdviceId').val(data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice);
										
									}
									
								}else{
									strtreatmentAdvice123+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strtreatmentAdvice123+='<p><small>';
									strtreatmentAdvice123+='No Record Found';
									strtreatmentAdvice123+='</small></p>';
								}
								
								
								if(data.pat_details[i].HRSTR_JSON_DATA.strConfidentialsInfo != ''){
									console.log('Inside conf info');
									strconfidentialInfo+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strconfidentialInfo+='<p><small>';
									strconfidentialInfo+=data.pat_details[i].HRSTR_JSON_DATA.strConfidentialsInfo;
									strconfidentialInfo+='</small></p>';
									//$('#treatmentAdviceId').val(data.pat_details[i].HRSTR_JSON_DATA.strtreatmentAdvice);
									if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
										
										 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
									     $('#ConfidentialInfoId').val(data.pat_details[i].HRSTR_JSON_DATA.strConfidentialsInfo);
										
									}
									
								}else{
									strconfidentialInfo+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strconfidentialInfo+='<p><small>';
									strconfidentialInfo+='No Record Found';
									strconfidentialInfo+='</small></p>';
								}
								
								if("strReferal" in data.pat_details[i].HRSTR_JSON_DATA){
								if(data.pat_details[i].HRSTR_JSON_DATA.strReffralDepttext != '' && data.pat_details[i].HRSTR_JSON_DATA.strReferal.length){
									//console.log('Inside referral Advice');
									strClinicalProcedure+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strClinicalProcedure+='<p><small>';
									
									for(var r=0 ; r < data.pat_details[i].HRSTR_JSON_DATA.strReferal.length ; r++){
										//if( (data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strReffralReason) =! '' )
										if("strShowData" in data.pat_details[i].HRSTR_JSON_DATA.strReferal[r]){
										var strrefertext='';
											if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strShowData != ''){
												 strrefertext =data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strShowData ;
											 if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strReffralReason !='')
												 strrefertext = strrefertext + '( ' +data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strReffralReason +')' ;
											 if(data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strreferralType !='0')
												 strrefertext = strrefertext + '[ ' +data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strreferralTypeName +']' ;
												 
											/* if(data.strReferal[j].strReffralReason !='') */
											//	$('.printPrescPage .refferPatientDept').append('<li><p>'+strrefertext+ ' </li></p>');	 
											/* else if(data.strReferal[j].strreferralType != '0')
												$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext+ '['+data.strReferal[j].strreferralTypeName+']'+' </li></p>');
											else
												$('.printPrescPage .refferPatientDept').append('<li><p>'+data.strReferal[j].strReffralDepttext +' </li></p>'); */
											 }
											
											strClinicalProcedure+=strrefertext+' ,<br>';	
										
										}else
											strClinicalProcedure+=data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strReffralDepttext.trim()+'('+data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strReffralReason.trim()+') ,';
										//else
										//	strClinicalProcedure+=data.pat_details[i].HRSTR_JSON_DATA.strReferal[r].strReffralDepttext.trim()' ,';	
											
									}
									//if(data.pat_details[i].HRSTR_JSON_DATA.strReffralReason !='')
									//strClinicalProcedure+= '('+data.pat_details[i].HRSTR_JSON_DATA.strReffralReason+')';
									
									strClinicalProcedure+='</small></p>';
									
								}else{
									strClinicalProcedure+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strClinicalProcedure+='<p><small>';
									strClinicalProcedure+='No Record Found';
									strClinicalProcedure+='</small></p>';
								}
							}else{
								strClinicalProcedure+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
								strClinicalProcedure+='<p><small>';
								strClinicalProcedure+='No Record Found';
								strClinicalProcedure+='</small></p>';
							}
								console.log('strtreatmentAdvice123 :::::::: '+strtreatmentAdvice123);
								if("strChronicDisease" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									
									strChronics+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									if(data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease.length > 0){
										strChronics+='<p><small><ul>';
										for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease.length;j++){
											var CronicDiseaseName=data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease[j].CronicDiseaseName.split(';')[0];
											var CronicDiseaseDuration=data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease[j].CronicDiseaseDuration;
											var CronicDiseaseRemarks=data.pat_details[i].HRSTR_JSON_DATA.strChronicDisease[j].CronicDiseaseRemarks;

											if(CronicDiseaseDuration != '' && CronicDiseaseRemarks != '')
												strChronics+='<li>'+CronicDiseaseName+', '+CronicDiseaseDuration+' yrs, '+CronicDiseaseRemarks+'</li>';
											else if(CronicDiseaseDuration != '')
												strChronics+='<li>'+CronicDiseaseName+', '+CronicDiseaseDuration+' yrs </li>';
											else if(CronicDiseaseRemarks != '')
												strChronics+='<li>'+CronicDiseaseName+', '+CronicDiseaseRemarks+' </li>';
											else
												strChronics+='<li>'+CronicDiseaseName+' </li>';
										}
										strChronics+='</ul></small></p>';
									}
									else{
										strChronics+='<p><small>';
										strChronics+='No Record Found';
										strChronics+='</small></p>';
									}
									
								}
								else
								{
									strChronics+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strChronics+='<p><small>';
									strChronics+='No Record Found';
									strChronics+='</small></p>';	
								}
								
								if("strDrugAllergy" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									
									strAllergies+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									if(data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy.length > 0){
										strAllergies+='<p><small><ul>';
										for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy.length;j++){
											var strAllergyName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergyName.split(';')[0];
											var strAllergySytmptomsName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergySytmptomsName.split(';')[0];
											var strSensivityName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strSensivityName;
											var strAllergysiteName=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergysiteName.split(';')[0];
											var stDurationTime=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].stDurationTime;
											var strAllergyRemarks=data.pat_details[i].HRSTR_JSON_DATA.strDrugAllergy[j].strAllergyRemarks;

											if(strAllergysiteName != '' && strSensivityName!='')
												strAllergies+='<li>'+strAllergyName+'('+strSensivityName+' on '+strAllergysiteName+'), '+strAllergySytmptomsName+', '+stDurationTime+' yrs, '+strAllergyRemarks+'</li>';
											else
												strAllergies+='<li>'+strAllergyName+', '+strAllergySytmptomsName+', '+stDurationTime+' yrs, '+strAllergyRemarks+'</li>';
											
										}
										strAllergies+='</ul></small></p>';
										
										strAllergies+='<br>';
										if("strotherAllergies" in (data.pat_details[i].HRSTR_JSON_DATA)){
											if(data.pat_details[i].HRSTR_JSON_DATA.strotherAllergies != '')
											{
												strAllergies+='<li> OTHER ALLERGY : '+ data.pat_details[i].HRSTR_JSON_DATA.strotherAllergies+'</li>';
												//$('.printPrescPage .treatmentadvice').append(data.strtreatmentAdvice);
											}
										}
										
										
									} else if("strotherAllergies" in (data.pat_details[i].HRSTR_JSON_DATA)){
										if(data.pat_details[i].HRSTR_JSON_DATA.strotherAllergies != '')
										{
											strAllergies+='<li> OTHER ALLERGY : '+ data.pat_details[i].HRSTR_JSON_DATA.strotherAllergies+'</li>';
											//$('.printPrescPage .treatmentadvice').append(data.strtreatmentAdvice);
										}
									}
									else{
										strAllergies+='<p><small>';
										strAllergies+='No Record Found';
										strAllergies+='</small></p>';
									}
									
								}
								else
								{
									strAllergies+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strAllergies+='<p><small>';
									strAllergies+='No Record Found';
									strAllergies+='</small></p>';	
								}
								
								if("strClinicalProcedure" in (data.pat_details[i].HRSTR_JSON_DATA))
								{
									
									strClinicalProc+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									if(data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure.length > 0){
										strClinicalProc+='<p><small><ul>';
										for(var j=0;j<data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure.length;j++){
											if(data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#").length == 7)
											{
											var strClinicalProcedureName=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[0];
											var strClinicalProcedureSite=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[3];
											var strClinicalProcedureRemarks=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[4];
											var strServiceAreaName=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[6];
											
											if(strClinicalProcedureSite.trim() != 'Site' && strClinicalProcedureRemarks!='')
												strClinicalProc+='<li>'+strServiceAreaName +' [ '+strClinicalProcedureName+']'+', '+strClinicalProcedureSite+', '+strClinicalProcedureRemarks+'</li>';
											else if(strClinicalProcedureSite.trim() != 'Site')
												strClinicalProc+='<li>'+strServiceAreaName +' [ '+strClinicalProcedureName+']'+', '+strClinicalProcedureSite+'</li>';
											else if(strClinicalProcedureRemarks != '')
												strClinicalProc+='<li>'+strServiceAreaName +' [ '+strClinicalProcedureName+']'+', '+strClinicalProcedureRemarks+'</li>';
											else
												strClinicalProc+='<li>'+strServiceAreaName +' [ '+strClinicalProcedureName+']'+'</li>';
											
											}else{
												
												
												var strClinicalProcedureName=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[0];
												var strClinicalProcedureSite=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[3];
												var strClinicalProcedureRemarks=data.pat_details[i].HRSTR_JSON_DATA.strClinicalProcedure[j].split("#")[4];
												
												
												if(strClinicalProcedureSite.trim() != 'Site' && strClinicalProcedureRemarks!='')
													strClinicalProc+='<li>'+strClinicalProcedureName+', '+strClinicalProcedureSite+', '+strClinicalProcedureRemarks+'</li>';
												else if(strClinicalProcedureSite.trim() != 'Site')
													strClinicalProc+='<li>'+strClinicalProcedureName+', '+strClinicalProcedureSite+'</li>';
												else if(strClinicalProcedureRemarks != '')
													strClinicalProc+='<li>'+strClinicalProcedureName+', '+strClinicalProcedureRemarks+'</li>';
												else
													strClinicalProc+='<li>'+strClinicalProcedureName+'</li>';
											}
										}
										strClinicalProc+='</ul></small></p>';
									}
									else{
										strClinicalProc+='<p><small>';
										strClinicalProc+='No Record Found';
										strClinicalProc+='</small></p>';
									}
									
								}
								else
								{
									strClinicalProc+='<p><u>'+maxDate+' ('+data.pat_details[i].HRSTR_JSON_DATA.patDept.trim()+')</u></p>';
									strClinicalProc+='<p><small>';
									strClinicalProc+='No Record Found';
									strClinicalProc+='</small></p>';	
								}
								
							}
							
							var date = new Date();
							document.title=window.patCrNo+date.getDate()+(date.getMonth()+1)+date.getFullYear();
							console.log('Date:::::::::>>>>>>>'+date.getDate()+(date.getMonth()+1)+date.getFullYear());
							var fileName='';
							var fileName1='';
							if(Object.keys(data.Template).length > 0){	
								//for(var k=0;k<Object.keys(data.Template).length;k++){
							//	var dt=data.Template[k].GDT_ENTRY_DATE ;
								for(var j=0;j<Object.keys(data.Template).length;j++){
									
									/*if(data.Template[k].GDT_ENTRY_DATE != data.Template[j].GDT_ENTRY_DATE)
									{
										fileName+='<p><u>'+data.Template[j].GDT_ENTRY_DATE+' ('+data.Template[j].DEPT_UNIT_NAME.trim()+')</u></p>';
									}*/
									if(data.Template[j].TEMPLATE_TYPE == '2'){
										fileName1+='<p><u>'+data.Template[j].GDT_ENTRY_DATE+' ('+data.Template[j].DEPT_UNIT_NAME.trim()+')</u></p>';
										//fileName+='<p><u>'+data.Template[j].GDT_ENTRY_DATE+' ('+data.Template[j].DEPT_UNIT_NAME.trim()+')</u></p>';
										console.log('data.Template[j].FILE_NAME'+data.Template[j].FILE_NAME);
										//fileName +='<li value='+data.Template[j].FILE_NAME+'><a href="#" value='+data.Template[j].FILE_NAME+' onclick="getpdffile(this)">' + data.Template[j].FILE_NAME +'</a></li>'
										fileName1 +='<li>  <button type="button" class="btn btn-link"  value='+data.Template[j].FILE_NAME+'#'+data.Template[j].GNUM_HOSPITAL_CODE+' onclick="getpdffile(this)">'+data.Template[j].TEMPLATE_NAME+'</button></li> '
									//	fileName +='<li> '+data.Template[j].TEMPLATE_NAME+' <a     onclick="getpdffile('+data.Template[j].FILE_NAME+'#'+data.Template[j].GNUM_HOSPITAL_CODE+',this)" href="#">Get Pdf</a></li> '
										
									}else{
										fileName+='<p><u>'+data.Template[j].GDT_ENTRY_DATE+' ('+data.Template[j].DEPT_UNIT_NAME.trim()+')</u></p>';
										//fileName+='<p><u>'+data.Template[j].GDT_ENTRY_DATE+' ('+data.Template[j].DEPT_UNIT_NAME.trim()+')</u></p>';
										console.log('data.Template[j].FILE_NAME'+data.Template[j].FILE_NAME);
										//fileName +='<li value='+data.Template[j].FILE_NAME+'><a href="#" value='+data.Template[j].FILE_NAME+' onclick="getpdffile(this)">' + data.Template[j].FILE_NAME +'</a></li>'
										fileName +='<li>  <button type="button" class="btn btn-link"  value='+data.Template[j].FILE_NAME+'#'+data.Template[j].GNUM_HOSPITAL_CODE+' onclick="getpdffile(this)">'+data.Template[j].TEMPLATE_NAME+'</button></li> '
									//	fileName +='<li> '+data.Template[j].TEMPLATE_NAME+' <a     onclick="getpdffile('+data.Template[j].FILE_NAME+'#'+data.Template[j].GNUM_HOSPITAL_CODE+',this)" href="#">Get Pdf</a></li> '
									}
								}
								//}
							}
							
							
							var strPatientDocumentUpload='';
							if(Object.keys(data.PaptientDoc).length > 0){	
								//for(var k=0;k<Object.keys(data.Template).length;k++){
							//	var dt=data.Template[k].GDT_ENTRY_DATE ;
								for(var j=0;j<Object.keys(data.PaptientDoc).length;j++){
									
								
									strPatientDocumentUpload+='<p><u>'+data.PaptientDoc[j].GDT_ENTRY_DATE+' ('+data.PaptientDoc[j].HRGSTR_FILE_NAME.trim()+')</u></p>';
										//fileName+='<p><u>'+data.Template[j].GDT_ENTRY_DATE+' ('+data.Template[j].DEPT_UNIT_NAME.trim()+')</u></p>';
										console.log('data.Template[j].FILE_NAME'+data.PaptientDoc[j].HRGSTR_FILE_NAME);
										//fileName +='<li value='+data.Template[j].FILE_NAME+'><a href="#" value='+data.Template[j].FILE_NAME+' onclick="getpdffile(this)">' + data.Template[j].FILE_NAME +'</a></li>'
										strPatientDocumentUpload +='<li>  <button type="button" class="btn btn-link"  value='+data.PaptientDoc[j].HRGNUM_PUK+'#'+data.PaptientDoc[j].HRGSTR_FILE_NAME+' onclick="getpdffile1(this)">'+data.PaptientDoc[j].HRGSTR_FILE_NAME+'</button></li> '
									//	fileName +='<li> '+data.Template[j].TEMPLATE_NAME+' <a     onclick="getpdffile('+data.Template[j].FILE_NAME+'#'+data.Template[j].GNUM_HOSPITAL_CODE+',this)" href="#">Get Pdf</a></li> '
										
									
								}
								//}
							}
							
							$('.TreeStructurePrescriptionModalNavMenu').children('li').eq(0).addClass('active');
							$('.TreeStructurePrescriptionModalNavMenuContent').children('div').eq(0).addClass('active');
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab2').append(strProfileInfo);

							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab4').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab4').append(strChiefComplaint);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab5').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab5').append(strDiagnosis);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab6').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab6').append(strInvestigations);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab7').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab7').append(strDrugsAndAdvices);

							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab8').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab8').append(strClinicalNote);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab9').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab9').append(strHistory);
							
						
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab10').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab10').append(strExaminations);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab11').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab11').append(strAllergies);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab12').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab12').append(strChronics);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab13').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab13').append(strClinicalProc);
							//alert(str);
							//alert(strtreatmentAdvice123);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab15').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab15').append(strtreatmentAdvice123);
							
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab16').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab16').append(strClinicalProcedure);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab17').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab17').append(strconfidentialInfo);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab18').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab18').append(fileName);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab19').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab19').append(fileName1);
							
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab20').append(str);
							$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab20').append(strPatientDocumentUpload);
							
							
							
						}
						else{
							$('#TreeStructurePrescriptionListMsg').text('No Details Found !');	
						}
					},
	  				complete: $('#TreeStructurePrescriptionListMsg').text('Error !!!')
			});
	      
	      $.ajax({url:'/HISDRDESK/services/restful/patdata/getPatDataForPastPrescription?Modval=2&CrNo='+window.patCrNo+'&episodeCode='+window.episodeCode +'&visitNo='+window.visitNo+'&seatId=&Entrydate=&hosp_code='+window.hospCode+'',
				async:true,
				//beforesend : $('.TreeStructurePrescriptionModalErrMsg').parent().append('<p id="TreeStructurePrescriptionListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
					success:function(data){
						
						//alert(Object.keys(data).length);
						//alert(Object.keys(data.pat_details));
						if(Object.keys(data.pat_details).length > 0){
							
							for(var i=0;i<Object.keys(data.pat_details).length;i++){  
							
								
								//alert(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.strpallor);
								//console.log("strEpisodeCodeChk "+strEpisodeCodeChk);
								//console.log("data.pat_details[i].HRSTR_JSON_DATA.episodeCode "+data.pat_details[i].HRSTR_JSON_DATA.episodeCode);
								//console.log("ashu todaysys "+todaysys);
								//console.log("ashu data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate "+data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate);
								//console.log(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate);
								
								if(strEpisodeCodeChk == data.pat_details[i].HRSTR_JSON_DATA.episodeCode){
									
									 if(todaysys == data.pat_details[i].HRSTR_JSON_DATA.currentVisitDate)
								     {
										 
										if(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.strpallor == 1)
											 $("#pallorId").prop("checked", true)
											 
										if(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.stricterus == 1)
											 $("#icterusId").prop("checked", true)
											 
										if(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.strcyanosis == 1)
											 $("#cyanosisId").prop("checked", true)
											 
										if(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.strclubbing == 1)
											 $("#clubbingId").prop("checked", true)
											 
										if(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.striymphadenopathyId == 1)
											 $("#iymphadenopathyId").prop("checked", true)	 
											 
										if(data.pat_details[i].HRSTR_JSON_DATA.strpiccle.stredema == 1)
											$("#edemaID").prop("checked", true)
								     }
									
								}	
								
									 
								
							}
							
						}
					}
	      });
	      
		      
	      var colorArray = ['36a2eb','ff6384','4bc0c0','ffcd56','9966ff','ff9f40','ffcf9f','ffe6aa','a5dfdf','9ad0f5','a0cece','c9cbcf','8afdfd','f67019','4dc9f6'];
	      
		      $.ajax({url:'/HISDRDESK/services/restful/patdata/getPatVitalDataForDetailedPrescription?Modval=3&CrNo='+window.patCrNo+'&episodeCode='+window.episodeCode +'&visitNo='+window.visitNo+'&seatId=&Entrydate=&hosp_code='+window.hospCode+'',
					async:true,
					//beforesend : $('.TreeStructurePrescriptionModalErrMsg').parent().append('<p id="TreeStructurePrescriptionVitalsListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
						success:function(data){ 
							var strVitalsChart = '';
							
							var strWeight='', strHeight='', strBmid='', strTemperature='', strrespRate='', strhaemoglobin='', strBloodPressure='',strBloodPressure1='', strfasting='', strRateId='', strhba1c='';
							var strPulseRateArr=[] , strCircumferenceArr=[], strMuac ='';
							var strPulseRate='' ,  strBloodGroup ='',  strBloodGroup ='' ,strCancerScreening = '', strCircumference='', strMuac ='';
							var strBMIArr=[] , strTemperatureArr=[] , strRespRateArr=[] , strHaemoglobinArr=[] , strBloodPressureArr=[] , strBloodPressureArr1=[] , strFastingArr=[] , strRateIdArr=[] , strHba1cArr=[] ;
							var strBloodGroupArr =[] ,strCancerScreeningArr=[],strcurcumferenceArr=[] ,strmuacArr=[] ;
							var strBloodGroup='' ,strcurcumference ='',strmuac='' ;
							/*----------------------added for cancer screening---------------*/
							var strCancerScreening ='';
							var strDateArr=[] ;
							var strHeightArr=[] , strWeightArr=[] ;
							var strDisabilityArr=[] ,strSmokingArr=[] , strAnemicArr=[] , strPregnancyArr=[] ; 
							var strDisability='' ,strSmoking='' , strAnemic='' , strPregnancy='' ; 
							var menuId = 'TreeStructureVitalsDetails';
							
							var profileInfoString = '';
							////////////////////////////////////////////////////
							$.ajax({url:'/HISDRDESK/services/restful/patdata/getPatDataForPastPrescription?Modval=2&CrNo='+window.patCrNo+'&episodeCode='+window.episodeCode +'&visitNo='+window.visitNo+'&seatId=&Entrydate=&hosp_code='+window.hospCode+'',
								async:false,
								success:function(result){ 

											if(Object.keys(result.pat_details).length > 0){
											
												var months = new Array(12);
											 	months[1] = "Jan";
											 	months[2] = "Feb";
											 	months[3] = "Mar";
											 	months[4] = "Apr";
											 	months[5] = "May";
											 	months[6] = "Jun";
											 	months[7] = "Jul";
											 	months[8] = "Aug";
											 	months[9] = "Sep";
											 	months[10] = "Oct";
											 	months[11] = "Nov";
											 	months[12] = "Dec";

											 	var today = new Date();
												var printDate = today.getDate()+'-'+months[today.getMonth()+1]+'-'+today.getFullYear();
												var printTime = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
												
												profileInfoString+='<div class="row"><h4 class="text-center" style="font-weight:bold">PATIENT CLINICAL DATA</h4>'+
												'</div>'+  
												'<div class="" style="border-top: 1px solid grey; border-bottom: 1px solid grey;">'+
												'<table class="table table-condensed printPrescPatDtlTbl table-responsive">'+
												'<tbody>'+
												'<tr>'+
												'<th>Name</th><td class="patName">';
												profileInfoString+=result.pat_details[0].HRSTR_JSON_DATA.pat_Name.trim();
												profileInfoString+='</td><th>CR No.</th><td class="patCrNo">';
												profileInfoString+=result.pat_details[0].HRSTR_JSON_DATA.CR_No.trim();
												profileInfoString+='</td></tr>';
												profileInfoString+='<tr>'+
												'<th>Department(Unit/Consultant)</th><td class="patDeptU">';
												profileInfoString+=result.pat_details[0].HRSTR_JSON_DATA.patDept.trim();
												profileInfoString+='</td><th>Reprinted On</th><td class="printedOn">';
												profileInfoString+=printDate+' / '+printTime;
												profileInfoString+='</td></tr>';
												
												profileInfoString+='<tr>'+
												'<th>Mobile No</th><td class="patDeptU">';
												profileInfoString+=(result.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[14].trim();
												if($('#strRailTailFlgId').val() == '0'){
												profileInfoString+='</td><th>Occupation</th><td class="printedOn">';
												profileInfoString+=(result.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[17].trim();
												profileInfoString+='</td></tr>';
												}else{
													var patOthersDetails1=$('#patOthersDetailsPrescriptionPanel').html();
													if(patOthersDetails1 !='{}'){
													profileInfoString+='</td><th>Designation</th><td class="printedOn">';
													profileInfoString+=(JSON.parse(patOthersDetails1).designation);
													profileInfoString+='</td></tr>';
													
													profileInfoString+='<tr>'+
													'<th>Station</th><td class="patDeptU">';
													profileInfoString+=(JSON.parse(patOthersDetails1).custom_unit);
													profileInfoString+='</td><th></th><td></td></tr>';
													
													}else{
														profileInfoString+='</td><th>Occupation</th><td class="printedOn">';
														profileInfoString+=(result.pat_details[0].HRSTR_JSON_DATA.PatCompleteGeneralDtl.trim()).split('#')[17].trim();
														profileInfoString+='</td></tr>';
														
													}
												}
												profileInfoString+='</tbody></table></div>';
											}
									}
							});
							
							///////////////////////////////////////////////////
							//alert(Object.keys(data).length);
							//alert(Object.keys(data.pat_vital_details).length);
							if(Object.keys(data.pat_vital_details).length > 0){
								//$('#TreeStructurePrescriptionVitalsListMsg').remove();
								
								strVitalsChart+='<div class="table-responsive"><table class="table table-hover table-striped table-condensed" id="VitalDataTreeRow">';
							 	strVitalsChart+='<thead><tr style="color:#6d6db7"><th>Parameter/Date</th>';	
							 	
								for(var i=0;i<Object.keys(data.pat_vital_details).length;i++){   
									
									//console.log(i+" ** "+data.pat_vital_details[i].TO_CHAR);
									strDateArr[i]=data.pat_vital_details[i].TO_CHAR;
									console.log("date array -->> "+i+" -->> "+strDateArr[i]);
								 	//console.log(i+" ** "+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strfasting);
								 	
								 	if(i<7){
								 		strVitalsChart+='<th>'+data.pat_vital_details[i].TO_CHAR+'</th>';
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strWeight != ''){
				      						strWeight+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strWeight+'</td>';
				      						strWeightArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strWeight;
				      					}
				      					else{
				      						strWeight+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strHeight != ''){
				      						strHeight+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strHeight+'</td>';
				      						strHeightArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strHeight;
				      					}
				      					else{
				      						strHeight+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strBmid != ''){
				      						strBmid+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strBmid+'</td>';
				      						strBMIArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strBmid;
				      						console.log("BMI array -->> "+i+" -->> "+strBMIArr[i]);
				      					}
				      					else{
				      						strBmid+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strTempreature != ''){
				      						strTemperature+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strTempreature+'</td>';
				      						strTemperatureArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strTempreature
				      					}
				      					else{
				      						strTemperature+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strrespRate != ''){
				      						strrespRate+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strrespRate+'</td>';
				      						strRespRateArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strrespRate;
				      					}
				      					else{
				      						strrespRate+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strhaemoglobin != ''){
				      						strhaemoglobin+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strhaemoglobin+'</td>';
				      						strHaemoglobinArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strhaemoglobin;
				      					}
				      					else{
				      						strhaemoglobin+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strsystolic != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strdiastolic != ''){
				      						strBloodPressure+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strsystolic+'/'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strdiastolic+'</td>';
				      						strBloodPressureArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strsystolic+'/'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strdiastolic;
				      					}
				      					else{
				      						strBloodPressure+='<td style="color:green">--</td>';
				      					}
				      					
				      					if( ( "strsystolic1" in (data.pat_vital_details[i].HOPLSTR_JSON_DATA))  && ( "strdiastolic1" in (data.pat_vital_details[i].HOPLSTR_JSON_DATA))  ){
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strsystolic1 != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strdiastolic1 != ''){
				      						strBloodPressure1+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strsystolic1+'/'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strdiastolic1+'</td>';
				      						strBloodPressureArr1[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strsystolic1+'/'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strdiastolic1;
				      					}
				      					else{
				      						strBloodPressure1+='<td style="color:green">--</td>';
				      					}
				      					}else{
				      						strBloodPressure1+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strfasting != ''){
				      						strfasting+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strfasting+'</td>';
				      						strFastingArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strfasting;
				      					}
				      					else{
				      						strfasting+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strRateId != ''){
				      						strRateId+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strRateId+'</td>';
				      						strRateIdArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strRateId;
				      					}
				      					else{
				      						strRateId+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strhba1c != ''){
				      						strhba1c+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strhba1c+'</td>';
				      						strHba1cArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strhba1c;
				      					}
				      					else{
				      						strhba1c+='<td style="color:green">--</td>';
				      					}
				      					
				      					
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strbloodGroup != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strbloodGroup != '0'){
				      						strBloodGroup+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strbloodGroup+'</td>';
				      						strBloodGroupArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strbloodGroup;
				      					}
				      					else{
				      						strBloodGroup+='<td style="color:green">--</td>';
				      					}
				      					
				      					/*--------------Added for cancer screening-----------------*/
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcancerScreening != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcancerScreening != '0'){
				      						strCancerScreening+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcancerScreening+'</td>';
				      						strCancerScreeningArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcancerScreening;
				      					}
				      					else{
				      						strCancerScreening+='<td style="color:green">--</td>';
				      					}
				      					
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcurcumference != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcurcumference != '0'){
				      						strcurcumference+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcurcumference+'</td>';
				      						strcurcumferenceArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strcurcumference;
				      					}
				      					else{
				      						strcurcumference+='<td style="color:green">--</td>';
				      					}
				      					
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strmuac != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strmuac != '0'){
				      						strmuac+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strmuac+'</td>';
				      						strmuacArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strmuac;
				      					}
				      					else{
				      						strmuac+='<td style="color:green">--</td>';
				      					}
				      					
				      					/**/
				      					if("strDisability" in (data.pat_vital_details[i].HOPLSTR_JSON_DATA)){
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strDisability != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strDisability != '0'){
				      						strDisability+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strDisability+'</td>';
				      						strDisabilityArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strDisability;
				      					}
				      					else{
				      						strDisability+='<td style="color:green">--</td>';
				      					}
				      				}else{
			      						strDisability+='<td style="color:green">--</td>';
			      					}
				      					if("strSmoking" in (data.pat_vital_details[i].HOPLSTR_JSON_DATA)){
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strSmoking != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strSmoking != '0'){
				      						strSmoking+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strSmoking+'</td>';
				      						strSmokingArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strSmoking;
				      					}
				      					else{
				      						strSmoking+='<td style="color:green">--</td>';
				      					}
				      				}else{
			      						strSmoking+='<td style="color:green">--</td>';
			      					}
				      					if("strAnemic" in (data.pat_vital_details[i].HOPLSTR_JSON_DATA)){
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strAnemic != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strAnemic != '0'){
				      						strAnemic+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strAnemic+'</td>';
				      						strAnemicArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strAnemic;
				      					}
				      					else{
				      						strAnemic+='<td style="color:green">--</td>';
				      					}
				      				}else{
			      						strAnemic+='<td style="color:green">--</td>';
			      					}
				      					if("strPregnancy" in (data.pat_vital_details[i].HOPLSTR_JSON_DATA)){
				      					if(data.pat_vital_details[i].HOPLSTR_JSON_DATA.strPregnancy != '' && data.pat_vital_details[i].HOPLSTR_JSON_DATA.strPregnancy != '0'){
				      						strPregnancy+='<td>'+data.pat_vital_details[i].HOPLSTR_JSON_DATA.strPregnancy+'</td>';
				      						strPregnancyArr[i]=data.pat_vital_details[i].HOPLSTR_JSON_DATA.strPregnancy;
				      					}
				      					else{
				      						strPregnancy+='<td style="color:green">--</td>';
				      					}
				      				}else{
			      						strPregnancy+='<td style="color:green">--</td>';
			      					}
				      					
								 	}
			      					

								}
								
								strVitalsChart+='</tr></thead>'; 
		      					strVitalsChart+='<tbody>';
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Weight(kgs)</td>';
		      					strVitalsChart+=strWeight;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Height(cms)</td>';
		      					strVitalsChart+=strHeight;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">BMI(kg/m<sup>2</sup>)</td>';
		      					strVitalsChart+=strBmid;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Temperature(F)</td>';
		      					strVitalsChart+=strTemperature;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Respiration  Rate(braths/min)</td>';
		      					strVitalsChart+=strrespRate;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Haemoglobin(gm/dL)</td>';
		      					strVitalsChart+=strhaemoglobin;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Blood Pressure(mm/Hg)</td>';
		      					strVitalsChart+=strBloodPressure;
		      					strVitalsChart+='</tr>';
		      					
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Blood Pressure(mm/Hg)</td>';
		      					strVitalsChart+=strBloodPressure1;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Blood Sugar (Fasting)(mg/dL)</td>';
		      					strVitalsChart+=strfasting;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">HBA1C(%)</td>';
		      					strVitalsChart+=strhba1c;
		      					
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Pulse Rate (beats/min)</td>';
		      					strVitalsChart+=strRateId;
		      					strVitalsChart+='</tr>';
		      					
		      					/*----------------added for Cancer Screnning-------------*/
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Cancer Screening</td>';
		      					strVitalsChart+=strCancerScreening;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Blood Group</td>';
		      					strVitalsChart+=strBloodGroup;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Head Circumference (cms)</td>';
		      					strVitalsChart+=strcurcumference;
		      					strVitalsChart+='</tr>';
		      					
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Disability</td>';
		      					strVitalsChart+=strDisability;
		      					strVitalsChart+='</tr>';
		      					
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Smoking</td>';
		      					strVitalsChart+=strSmoking;
		      					strVitalsChart+='</tr>';
		      					
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Anemic</td>';
		      					strVitalsChart+=strAnemic;
		      					strVitalsChart+='</tr>';
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">Pregnancy</td>';
		      					strVitalsChart+=strPregnancy;
		      					strVitalsChart+='</tr>';
		      					
		      					
		      					
		      					
		      					strVitalsChart+='<tr><td style="font-weight:bold;">MUAC(cms)</td>';
		      					strVitalsChart+=strmuac;
		      					strVitalsChart+='<td class="text-right"><button id="parameterVitalsTrendBtn" class="btn btn-sm btn-info testTrendsBtn" type="button">Trends</button></td>';
		      					strVitalsChart+='</tr>';
		      					
		      					/*strVitalsChart+='<tr><td style="font-weight:bold;">Blood Sugar (PP)</td>';
		      					strVitalsChart+=strRateId;
		      					strVitalsChart+='</tr>';*/
		      					
		      					
		      					
		      					strVitalsChart+='</tbody></table></div>';
		      					
		      					strVitalsChart+='<h5 class="text-left cumulativeTestChartHeadingForVitalsTreePresc"  style="display:none;"></h5>';
		      					strVitalsChart+='<div class="col-sm-12" style="position: relative;"><canvas id="'+menuId+'_chart"></canvas></div>';
		      					
		      					var date = new Date();
								document.title=window.patCrNo+date.getDate()+(date.getMonth()+1)+date.getFullYear();
								console.log('Date:::::::::>>>>>>>'+date.getDate()+(date.getMonth()+1)+date.getFullYear());
								console.log('time ::>>>>>>>>>>>>>>'+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds());
								 
		      					$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab3').append(profileInfoString);
		      					$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab3').append("<br>");
								$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab3').append(strVitalsChart);
								
							}
							else{
								$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab3').append(profileInfoString);
		      					$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab3').append("<br>");
		      					$('.TreeStructurePrescriptionModalNavMenuContent #TreeStructurePrescriptionModalTab3').append(strVitalsChart);
								//$('#TreeStructurePrescriptionVitalsListMsg').text('No Record Found !');	
							}
							
							window.chartColors = {
									red: 'rgb(255, 99, 132)',
									orange: 'rgb(255, 159, 64)',
									yellow: 'rgb(255, 205, 86)',
									green: 'rgb(75, 192, 192)',
									blue: 'rgb(54, 162, 235)',
									purple: 'rgb(153, 102, 255)',
									grey: 'rgb(201, 203, 207)',
									olive: 'rgb(115, 153, 0)'
								};

							for(var k=0; k<strDateArr.length;k++){
								var config = {
										type: 'line',
										data: {
											labels: strDateArr,
											datasets: [{
												label: 'Weight',
												backgroundColor: window.chartColors.red,
												borderColor: window.chartColors.red,
												data: strWeightArr,
												fill: false,
											},{
												label: 'Height',
												backgroundColor: window.chartColors.red,
												borderColor: window.chartColors.red,
												data: strHeightArr,
												fill: false,
											}, {
												label: 'BMI',
												backgroundColor: window.chartColors.red,
												borderColor: window.chartColors.red,
												data: strBMIArr,
												fill: false,
											}, {
												label: 'Temperature',
												fill: false,
												backgroundColor: window.chartColors.blue,
												borderColor: window.chartColors.blue,
												data: strTemperatureArr,
												fill: false,
											},
											{
												label: 'Respiration rate',
												fill: false,
												backgroundColor: window.chartColors.yellow,
												borderColor: window.chartColors.yellow,
												data: strRespRateArr,
												fill: false,
											},
											{
												label: 'Haemoglobin',
												fill: false,
												backgroundColor: window.chartColors.green,
												borderColor: window.chartColors.green,
												data: strHaemoglobinArr,
												fill: false,
											},
											/*{
												label: 'Blood Pressure',
												fill: false,
												backgroundColor: window.chartColors.orange,
												borderColor: window.chartColors.orange,
												data: strBloodPressureArr,
												fill: false,
											},*/
											{
												label: 'Blood Sugar Fasting',
												fill: false,
												backgroundColor: window.chartColors.grey,
												borderColor: window.chartColors.grey,
												data: strFastingArr,
												fill: false,
											},
											{
												label: 'Blood Sugar PP',
												fill: false,
												backgroundColor: window.chartColors.purple,
												borderColor: window.chartColors.purple,
												data: strRateIdArr,
												fill: false,
											},
											{
												label: 'HBA1C',
												fill: false,
												backgroundColor: window.chartColors.olive,
												borderColor: window.chartColors.olive,
												data: strHba1cArr,
												fill: false,
											}
											,
											{ 
												label: 'Blood Group',
												fill: false,
												backgroundColor: window.chartColors.olive,
												borderColor: window.chartColors.olive,
												data: strBloodGroupArr,
												fill: false,
											},
											{  /*----------------------added for cancer screening---------------*/
												label: 'Cancer Screening',
												fill: false,
												backgroundColor: window.chartColors.olive,
												borderColor: window.chartColors.olive,
												data: strCancerScreeningArr,
												fill: false,
											},
											{
												label: 'Head Circumference',
												fill: false,
												backgroundColor: window.chartColors.pink,
												borderColor: window.chartColors.pink,
												data: strcurcumferenceArr,
												fill: false,
											},
											{
												label: 'MUAC',
												fill: false,
												backgroundColor: window.chartColors.blue,
												borderColor: window.chartColors.blue,
												data: strmuacArr,
												fill: false,
											}
											
											]
										},
										options: {
											responsive: true,
											title: {
												display: true,
												text: 'Vitals Chart'
											},
											tooltips: {
												mode: 'index',
												intersect: false,
											},
											hover: {
												mode: 'nearest',
												intersect: true
											},
											scales: {
												xAxes: [{
													display: true,
													scaleLabel: {
														display: true,
														labelString: 'Date'
													}
												}],
												yAxes: [{
													display: true,
													scaleLabel: {
														display: true,
														labelString: 'Values'
													},
													ticks: {
										                  min: 0,
										                  scaleSteps : 10,
										                  stepSize: 100,
										              }
												}]
											}
										}
									};
								$('#parameterVitalsTrendBtn').click(function(){
									$('.cumulativeTestChartHeadingForVitalsTreePresc').show();
									var ctx = document.getElementById(menuId+'_chart').getContext('2d');
									window.myLine = new Chart(ctx, config);
								});
								
								
							}
							
						}
		      
				      
		      			//,complete: $('#TreeStructurePrescriptionVitalsListMsg').text('Error !!!')
				});
		      $('.investigationsDiv').find('input').on('focus',function(){
		          var index = $('.investigationsDiv').find('input').index(this);
		          //alert(index);
		          if (index == '1') {
		            $('.investigationsDiv').find('input').eq(2).val('');
		          } else if (index == '2') {
		            $('.investigationsDiv').find('input').eq(1).val('');
		          } 
		        });

	});
	
	
	$('.clinicalProceduresAdd').click(function(){
		var clinicalProcedureVal = $(this).parent().parent().parent().find('input[name=clinicalProcedureName]').val();
		var otherProceduresVal = clinicalProcedureVal ; //$(this).parent().parent().parent().find('input[name=otherProcedures]').val();
		var ServiceAreaName =	$('select[name=clinicalServiceArea] option:selected').text();
		console.log('clinicalProcedureVal'+clinicalProcedureVal+ 'ServiceAreaName'+  ServiceAreaName);  
		if(clinicalProcedureVal.trim()!='')
		  {  
			   /* var tmp = 0; 
				$('.clinicalProceduresAdded').find('label .text').each(function(index){ 
					if($(this).text().split("(")[0].trim().toUpperCase()===clinicalProcedureVal.trim().toUpperCase()) 
					{	tmp = 1; 
						return false;  }
				});
				if(tmp==1)
				{
					swal("Already Added!!");
					//$(this).parent().parent().parent().find('input[name=txt-snomed-ct-search_VR7]').val(''); 
					return false;
				}*/
				var isValid = 0;
			 	 var invObj = localStorage.getItem('ClinicalProcedureObj'); 
		 		 invObj = JSON.parse(invObj.toString()); 
		 		 for(var v=0; v<invObj.length;v++)
		 		 { 
		 			if (invObj[v].testName.toUpperCase() == clinicalProcedureVal.toUpperCase()) {
		 				isValid=1;
				        break;
				    } 
		 		  } 
		 		 if(clinicalProcedureVal.trim()=='' &&  $('input[name=clinicalProcedureVal]').val().trim() =='') 
				{
				  swal('Please enter investigation to be added');
				  return false; 
				}
				else
				{
					var diagnosisCode ='';
					 for(var v=0; v<invObj.length;v++)
			 		 { 
			 			if (invObj[v].testName.toUpperCase() == clinicalProcedureVal.toUpperCase()) {
			 			diagnosisCode  = invObj[v].testId;	//+'^'+invObj[v].labName ;  /*+'^'+$("#investigationSiteId").val()+'^'+$("#investigationRemarksId").val();;*/ 
					        break;
					    } 
			 		 }
					//var diagnosisCode = '0'; //$(this).parent().parent().find('input[name=txt-snomed-ct-search_VR7]').attr('clinicalprocedurecode');
	 			 	//var diagnosisTypeCode = $(this).parent().parent().find('select[name=diagnosisType]').val();
	 			 	//var diagnosisTypeName = $(this).parent().parent().find('select[name=diagnosisType] option:selected').text();
	 			 	   console.log('diagnosisCode'+diagnosisCode);
	 			 	   if(diagnosisCode != ''){
	 			 	 	 var siteId=$(this).parent().parent().find('select[name="clinicalProceduresSite"] option:selected').text();
	 					clinicalProcValue =clinicalProcedureVal+'#'+diagnosisCode+'#'+$("#clinicalProceduresSiteId").val()+'#'+siteId+'#'+$("#clinicalProceduresRemarksId").val()+'#'+$('#clinicalServiceAreaId').val()+'#'+ServiceAreaName;
	 					
	 					var ClinicalProcedureJson = {
	 							"IsExternal"  			:  "0" ,
	 							"ProceduresName"		:	clinicalProcedureVal ,
	 							"ProcedureCode"			:	diagnosisCode ,
	 							"ProcedureSideCode"		:	$("#clinicalProceduresSiteId").val() ,
	 							"ProcedureSideName"		:	siteId ,
	 							"ProcedureSideRemarks"	:	$("#clinicalProceduresRemarksId").val() ,
	 							"ServiceAreaCode"		:	$('#clinicalServiceAreaId').val(),
	 							"ServiceAreaName"		:	$('select[name=clinicalServiceArea] option:selected').text()
	 					}
	 					
	 					var temp='';
	 					if(clinicalProcedureVal !='' && $("#clinicalProceduresSiteId").val() !='0'){
	 						temp= '[' +  siteId + ']'  ;
	 					}
	 					if($("#clinicalProceduresRemarksId").val() != '' ){
	 						temp=temp + $("#clinicalProceduresRemarksId").val() ;
	 					}
	 					 console.log('ClinicalProcedureJson -- '+JSON.stringify(ClinicalProcedureJson));
	 					 
	 					//commented by ashutoshk to hide ServiceAreaName 
	 					$(this).parent().parent().parent().find('.clinicalProceduresAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs"   data-toggle="tooltip" title="'+ temp +'" >'+
	 			    	 		'<input type="checkbox" class="checkedInput" name="clinicalProc" value="'+clinicalProcValue+'" checked="">  '+
	 			    	 		'<i class="" style="display :none">'+JSON.stringify(ClinicalProcedureJson)+' </i>'+
	 			    	 		//'<span class="text">'+ServiceAreaName +' ['+ clinicalProcedureVal+ ']' +' </span>'+
	 			    	 		'<span class="text">' +'['+ clinicalProcedureVal+ ']' +' </span>'+
	 			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	 			    	 		'</button></label>');
	 			 	   }else{
	 			 		 var siteId=$(this).parent().parent().find('select[name="clinicalProceduresSite"] option:selected').text();
	 					otherProceduresValue =otherProceduresVal+'#0^0#'+$("#clinicalProceduresSiteId").val()+'#'+siteId+'#'+$("#clinicalProceduresRemarksId").val()+'#'+$('#clinicalServiceAreaId').val()+'#'+ServiceAreaName;
	 					var ClinicalProcedureJson = {
	 							"IsExternal"  			:  "1" ,
	 							"ProceduresName"		:	otherProceduresVal ,
	 							"ProcedureCode"			:	"0^0" ,
	 							"ProcedureSideCode"		:	$("#clinicalProceduresSiteId").val() ,
	 							"ProcedureSideName"		:	siteId ,
	 							"ProcedureSideRemarks"	:	$("#clinicalProceduresRemarksId").val(),
	 							"ServiceAreaCode"		:	$('#clinicalServiceAreaId').val(),
	 							"ServiceAreaName"		:	$('select[name=clinicalServiceArea] option:selected').text()
	 					}
	 					 console.log('ClinicalProcedureJson '+JSON.stringify(ClinicalProcedureJson));
	 					
	 					var temp='';
	 					if(otherProceduresVal !='' && $("#clinicalProceduresSiteId").val() !='0'){
	 						temp=  '[' +  siteId + ']'  ;
	 					}
	 					if($("#clinicalProceduresRemarksId").val() != '' ){
	 						temp=temp + $("#clinicalProceduresRemarksId").val() ;
	 					}
	 					
	 					$(this).parent().parent().parent().find('.clinicalProceduresAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs" data-toggle="tooltip" title='+ temp +' >'+
	 			    	 		'<input type="checkbox" class="checkedInput" name="clinicalProc" value="'+otherProceduresValue+'" checked="">  '+
	 			    	 		'<i class="" style="display :none">'+JSON.stringify(ClinicalProcedureJson)+' </i>'+
	 			    	 		//'<span class="text">'+ServiceAreaName +'['+ otherProceduresVal+ ']' +'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
	 			    	 		'<span class="text">'+'['+ otherProceduresVal+ ']' +'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
	 			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	 			    	 		'</button></label>');
	 			 	   }
	 			

				}
		 		$(this).parent().parent().parent().find('input[name=clinicalProcedureName]').val('');
			 $(this).parent().parent().parent().find('input[name=flexdatalist-clinicalProcedureName]').val(''); 
			 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR2]').attr('clinicalprocedurecode','');
			 $(this).parent().parent().find('select[name=clinicalProceduresSite]').val('0');
			 $(this).parent().parent().find('#clinicalProceduresRemarksId').val('');
			 $('[data-toggle="tooltip"]').tooltip();
		  }
		else if(otherProceduresVal != ''){
			var tmp = 0; 
			$('.clinicalProceduresAdded').find('label .text').each(function(index){ 
				if($(this).text().split("*")[0].trim().toUpperCase()===otherProceduresVal.trim().toUpperCase()) 
				{	tmp = 1; 
					return false;  }
			});
			if(tmp==1)
			{
				swal("Already Added!!");
				$(this).parent().parent().parent().find('input[name=otherProcedures]').val(''); 
				return false;
			}
			else
			{
				var siteId=$(this).parent().parent().find('select[name="clinicalProceduresSite"] option:selected').text();
			     otherProceduresValue =otherProceduresVal+'#'+$("#clinicalProceduresSiteId").val()+'#'+siteId+'#'+$("#clinicalProceduresRemarksId").val();
				
			   $(this).parent().parent().parent().find('.clinicalProceduresAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
		    	 		'<input type="checkbox" class="checkedInput" name="clinicalProc" value="'+otherProceduresValue+'" checked="">  '+
		    	 		'<span class="text">'+otherProceduresVal+'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
		    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
		    	 		'</button></label>');

			}
			$(this).parent().parent().parent().find('input[name=clinicalProcedureName]').val('');
			 $(this).parent().parent().parent().find('input[name=otherProcedures]').val(''); 
			 $(this).parent().parent().find('select[name=clinicalProceduresSite]').val('0');
			 $(this).parent().parent().find('#clinicalProceduresRemarksId').val('');
			 $('[data-toggle="tooltip"]').tooltip();
		}
		  else{
			  swal('Please enter clinical procedures to be added');
		  }
	});
	
	$('.drugBookmarkAddBtn').click(function(){
		console.log(':::::::::');
		
		 $(this).parent().parent().find('input[name=drugsAdvicesBook]:checked').each(function(index){
			 var drugsAdvicesBookName = $(this).parent().text();
			 console.log($(this).val());
			 var hiddenVal=$(this).val();
			 
			 var temp1=0;
			 $('#drugAdviceListTable tbody').find('tr').each(function(index){
				 console.log('=='+$(this).find('td').eq(0).children('input').val().split('&&')[0]);
				 console.log('=='+hiddenVal.split('&&')[0]);
				 console.log('======'+($(this).find('td').eq(0).children('input').val().split('&&')[0]).toUpperCase() == (hiddenVal.split('&&')[0]).toUpperCase());
					if(($(this).find('td').eq(0).children('input').val().split('&&')[0]).toUpperCase() == (hiddenVal.split('&&')[0]).toUpperCase()) 
					{	
						console.log('1111');
						temp1 = 1; 
						//return false; 
					}
				});
			 
			 //console.log($(this).find('tr').html());
			 console.log(hiddenVal.split('&&')[8]);
			
			 if(temp1 == 1)
				 {}else{
					 
				 
				 
					 var DrugJson ={
			 					"IsExterNal"	:		"0" ,	
			 					"DrugName"		 :	 hiddenVal.split('&&')[0] ,
			 					"DrugCode"		 :	 hiddenVal.split('&&')[1] ,
			 					"DoaseName"		:	hiddenVal.split('&&')[2] ,
			 					"DoaseCode"		:	hiddenVal.split('&&')[3] ,
			 					"FrequencyName"	:	hiddenVal.split('&&')[4] ,
			 					"FrequencyCode" :	hiddenVal.split('&&')[5] ,
			 					"StartDate"		:	hiddenVal.split('&&')[6] ,
			 					"DrugDays"		:	(hiddenVal.split('&&')[7]).split('#')[0] ,
			 					"DrugQuantity"	:	(hiddenVal.split('&&')[7]).split('#')[1] ,
			 					"DrugInstruction" :	hiddenVal.split('&&')[8]
			 					
			 			}
			 			
			 			console.log(JSON.stringify(DrugJson));
					 
			 $('#drugAdviceListTable').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+hiddenVal+'"  checked><i class="text" style="display :none">'+JSON.stringify(DrugJson)+' </i></td><td>'+hiddenVal.split('&&')[0]+'</td><td>'+hiddenVal.split('&&')[2]+'</td><td>'+hiddenVal.split('&&')[4]+'</td><td>'+hiddenVal.split('&&')[6]+'</td><td>'+(hiddenVal.split('&&')[7]).split('#')[0]+'</td><td>'+(hiddenVal.split('&&')[7]).split('#')[1]+'</td><td><a class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+hiddenVal.split('&&')[8]+'" onclick="$(\'#drugAdvicesInstructionsModal\').modal(\'show\');">'+(hiddenVal.split('&&')[8]).substring(0, 4)+'..'+'</a></td><td><button  class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td><td><button class="btn btn-xs monogrambtn" type="button" data-toggle="modal" data-target="CimsMonographId"><i class="fas fa-capsules" style="color: Crimson;"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs Genericbarndbtn" type="button" data-toggle="modal" data-target="GenericMonogramId"><i class="fas fa-tablets" style="color: DeepSkyBlue;"></i></button></td></tr>');
			 
			 
			 $('.drugEditBtn').click(function(){
				    if($('input[name=drugName]').val()!='')
				    	return false;
			 		var drugName = $(this).parent().parent().find('td').eq(1).text();
			 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
			 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
			 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
			 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
			 		var drugQuantity = $(this).parent().parent().find('td').eq(6).text(); 
			 		var instructions = $(this).parent().parent().find('td').eq(7).text(); 
			 		
			 		$(this).parent().parent().remove();
			 		$('input[name=drugName]').val(drugName);
			 		/*$('select[name=drugDosage]').val(drugDosage);*/
			 		$('select[name=drugDosage]').val($('select[name=drugDosage] option:contains('+drugDosage+')').val());
			 		$('select[name=drugFrequency]').val($('select[name=drugFrequency] option:contains('+drugFrequency+')').val());
			 		/*$('input[name=drugStartDate]').val(drugStartDate); */
			 		document.getElementById('drugStartDate').valueAsDate = new Date();
			 		$('input[name=drugDays]').val(drugDays);
			 		$('input[name=drugQuantity]').val(drugQuantity);
			 		$('textarea[name=drugInstructions]').val(instructions); 
			 		//$('input[name=drugName]').focus();
				}); 
				 }
				$('.monogrambtn').click(function(){
					  
					  var investigation =hiddenVal.split('&&')[0]; 
					  var data1='';
				 		let f = 0;
				 		var invObj = localStorage.getItem('drugJsonObj'); 
				 		invObj = JSON.parse(invObj); 
				 		for(var v=0; v<invObj.length;v++)
				 		{ 
				 			if (invObj[v].drugName.toUpperCase() == investigation.toUpperCase()) {
						        console.log(invObj[v].drugName+'::::::::>>>>'+invObj[v].drugId);
						        data1=invObj[v].drugId;
						        f=1;
						        break;
						    } 
				 		} 
					  
					  
			   		  
			   		var ref_id= (data1.split('#')[3]).split('!')[0] ;
			   		   console.log(data1); 
			   		  
			   		  $.ajax({
			   		  	url: "/HISDRDESK/services/restful/cims/getMonographCimsData",
			   		  	dataType: "text",
			   		  	type: "POST",
			   		  	async: false,
			   		  	 crossDomain:true,
			   		  	data: {
			   		  		data: '"' + ref_id +'"' ,
			   		  		cimstype : (data1.split('#')[5]).split('&&')[0]
			   		  	},
			   		  	success: function(data) {
			   		  		console.log(data);
			   		  		$('#MonographResponse').html(data);
			   		  		$("#CimsMonographId").modal();
			   		  		
			   		  	},
			   		  	error: function(XMLHttpRequest, textStatus, errorThrown) {
			   		  		alert(errorThrown);
			   		  	}
			   		  });
			   		 });
				  	
				  	 $('.Genericbarndbtn').click(function(){
			   			 
			   			 var investigation =item.DrugName; 
						  var data1='';
					 		let f = 0;
					 		var invObj = localStorage.getItem('drugJsonObj'); 
					 		invObj = JSON.parse(invObj); 
					 		for(var v=0; v<invObj.length;v++)
					 		{ 
					 			if (invObj[v].drugName.toUpperCase() == investigation.toUpperCase()) {
							        console.log(invObj[v].drugName+'::::::::>>>>'+invObj[v].drugId);
							        data1=invObj[v].drugId;
							        f=1;
							        break;
							    } 
					 		} 
						  
						  

			   		 // var data1=$(this).parent().parent().find('.checkedInput').val();
				   		var ref_id= (data1.split('#')[2]).split('!')[0] ;

			   		  $.ajax({
			   		  	url: "http://aiimsmanglagiri.uat.dcservices.in/dis/generic/"+ref_id,
			   		  	dataType: "json",
			   		  	type: "GET",
			   		  	async: false,
			   		  	 crossDomain:true, 
			   		  	success: function(data) {
			   		  		console.log(data);
			   		  		/* var myJSON = JSON.stringify(data); */
			   		  	 var html1 ='';
			   		  		for (var i=0; i<data.length; i++) {
			   		  		html1 = '<div class="row"><div class="col-sm-12 genericDesc">' + data[i].genericDescriptions[1].genericName+ '</div></div><div class="row">';
			   		        		
			   		        		for (var j=0; j<data[i].drugTypes.length; j++) {
			   		        			html1 =html1+'<div class="col-sm-12 drugTypeName">' + data[i].drugTypes[j].typeName + '&nbsp; | &nbsp; </div>'  ; 
			   		        		}
			   		        		
			   		        		html1 =html1+'</div><br /><div class="row"><div class="col-sm-6"><strong style="color:blue">Dose Form:&nbsp;&nbsp;</strong> '+ data[i].doseForm +' ';
			   		        		html1 =html1+'</div><div class="col-sm-6"><strong style="color:blue">Route Of Administration:&nbsp;&nbsp;</strong>'+data[i].routeOfAdministration + '</div></div>' ;
			   		        		html1 =html1+'<br /><div class="row"><div class="col-sm-12"><strong style="color:blue">ContraIndications:&nbsp;&nbsp;</strong>'+ data[i].contraIndications + '' ;
			   		        		html1 =html1+'</div></div><br /><div class="row"><div class="col-sm-12"><strong style="color:blue">Indications:&nbsp;&nbsp;</strong>'+ data[i].indications + '</div></div>' ;
			   		       
			   		        $('#GenericMonogramResponse').append(html1);
			   		    }
			   		    console.log(html1);

			   		    
			   		  		$('#GenericMonogramResponse').html(html1);  
			   		  		$("#GenericMonogramId").modal();
			   		  		
			   		  	},
			   		  	error: function(XMLHttpRequest, textStatus, errorThrown) {
			   		  		//alert(errorThrown);
			   		  	    //alert(XMLHttpRequest.responseText);
			   		  	alert("Description Not Mapped By SNOMEDCT");
			   		  	}
			   		  });
			   		  
			   		 });
		 });
		
		
		
		 $(this).parent().parent().parent().find('.close').click();
		// $(this).parent().parent().parent().parent().find('#drugAdviceListTable').children('tbody').append('<tr> <td><input type="checkbox" class="checkedInput" name="drugsAdvices" value="'+HiddenDrugAdvice+'"  checked></td><td>'+drugName+'</td><td>'+drugDosage+'</td><td>'+drugFrequency+'</td><td>'+drugStartDate+'</td><td>'+drugDays+'</td><td>'+drugQuantity+'</td><td><a class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+drugInstructions+'" onclick="$(\'#drugAdvicesInstructionsModal\').modal(\'show\');">'+drugInstructions.substring(0, 4)+'..'+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td></tr>'); // drugInstructions   drugInstructions.substring(0, 4)
	});
	
	 var strrefcount=0;
	 $( document ).ready(function() {
	 $('input[name=referalchk]').each(function()
				{
		 ++strrefcount;
				})
	 });
	$('.refferaladdBtn').click(function(){
			//console.log('"""""""""""""""""""refferaladdBtn');
			
			//console.log(" ---- "+$("#strreferalchkId1").val());
		
			$('#strOtherHospitalNameDivId').hide();
			var strReffralDeptCmb= ($('select[name=refferlPatientDept] option:selected').val()).split('#')[0];
			var strReffralDepttext=($('select[name=refferlPatientDept] option:selected').text());
			
			var strreferralType= ($('select[name=referralType] option:selected').val());
			var strreferralTypeName=($('select[name=referralType] option:selected').text());
			
			//var strReffralReason=$('input[name=refferalResonId]').val(); ------ commented by ashutoshk for changing input tag to textarea tag under refer section
			var strReffralReason= $("#refferalResonId").val();
			var strReffralDeptDone =($('select[name=refferlPatientDept] option:selected').val());
			var strExternalHospital='';
			var strExternalDepartment='' , strExternalrefferalInstitute='';
			var strtooltiplData='';
			var strShowData='';
			var strExternalHospitalName1='';
			var strExternalDepartmentName='' , strExternalrefferalInstituteName='', strOtherAssociateHospitalName='';
			if(strreferralType == '0'){
				 swal('Please select Referal Combo to be added');
				  return false; 
			
			}
			
			if(strreferralType == 4){
				 strExternalHospital= ($('select[name=strExternalrefferalHospital] option:selected').val());
				 strExternalHospitalName1=($('select[name=strExternalrefferalHospital] option:selected').text())
				 strShowData = strExternalHospitalName1 ; 
				 if(strExternalHospital=='0'){
					 swal('Please select External Hospital Combo to be added');
					  return false; 
				 }
				 strExternalDepartment= ($('select[name=strExternalDepartmentList] option:selected').val());
				 strExternalDepartmentName=($('select[name=strExternalDepartmentList] option:selected').text());
				 strShowData +=  '['+ strExternalDepartmentName +']' ;
				 console.log(strShowData);
				 if(strExternalDepartment=='0'){
					 swal('Please select External Department Combo to be added');
					  return false; 
				 }
			}
			
			if(strreferralType == 5){
				strExternalrefferalInstituteName=  ($('select[name=strExternalrefferalInstitute] option:selected').text());
				strExternalrefferalInstitute= ($('select[name=strExternalrefferalInstitute] option:selected').val());
				strShowData = strExternalrefferalInstituteName;
				if(strExternalrefferalInstitute=='-1'){
					 swal('Please select External Institute Combo to be added');
					  return false; 
				 }
				 //strOtherAssociateHospitalName
				 if(strExternalrefferalInstitute == '0'){
					 strOtherAssociateHospitalName= $('#strOtherHospitalNameId').val();
					 strShowData += '['+ strOtherAssociateHospitalName + ']'
					 
				 }else{
				 strExternalDepartmentName=($('select[name=strExternalDepartmentList] option:selected').text());
				 strExternalDepartment= ($('select[name=strExternalDepartmentList] option:selected').val());
				 strShowData += '['+  strExternalDepartmentName +']'
				 if(strExternalDepartment=='0'){
					 swal('Please select External Department Combo to be added');
					  return false; 
				 }
			  }
			}
			//console.log(strReffralDeptCmb +' ::::: '+strReffralDepttext+' ::::::: '+ strreferralType + '   ::::: '+ strreferralTypeName +'  ::::::' + strReffralReason +':::::::'+ strReffralDeptDone);
			if(strreferralType == 1 || strreferralType == 2  || strreferralType == 3){
				strShowData = ($('select[name=refferlPatientDept] option:selected').text()) ;
			 if(strReffralDeptCmb.trim()=='0' ) 
				{
				  swal('Please select Department to be added');
				  return false; 
				}
			}
			 var chk=0;
			
			 $('.refferalAdded').find('label .text').each(function(index){
				 //console.log("strReffralDepttext "+$(this).text().trim().toUpperCase());
				// console.log("strReffralDepttext upper "+$(this).text().trim().toUpperCase());
				 
				// console.log("check : "+$(this).text().trim().toUpperCase()==strReffralDepttext.trim().toUpperCase());
					if($(this).text().trim().toUpperCase()===strReffralDepttext.trim().toUpperCase()) 
					{	
						chk = 0; 
						return false; 
					}
				});
			 
			 
			/* $('.refferalAdded').find('input id').each(function(index){
				 console.log("refferalAdded id "+$(this));
				// console.log("strReffralDepttext upper "+$(this).text().trim().toUpperCase());
				 
				 //console.log("check : "+$(this).text().trim().toUpperCase()==strReffralDepttext.trim().toUpperCase());
					if($(this).text().trim().toUpperCase()===strReffralDepttext.trim().toUpperCase()) 
					{	
						chk = 0; 
						return false; 
					}
				});*/
			 
			 $('input[name=referalchk]:checked').each(function()
						{
							//console.log('referalchk::>>> '+$(this).val());
							var strrefrealId=JSON.parse($(this).val());
							//console.log(strrefrealId.strReffralDeptCmb);
							//console.log(" current dept combo "+strReffralDeptCmb);
							//console.log(" current strreferralType "+strreferralType);
							
							if(strreferralType == '1' || strreferralType == '2'){
								
								if(strReffralDeptCmb == strrefrealId.strReffralDeptCmb) 
								{	
									chk = 1; 
								}
							}
							
						});
					
					
					
					if(chk ==1 ){
						swal('Department already added');
						return false; 
					}
					else{
					var reffralJson={
							"strReffralDeptCmb" : strReffralDeptCmb ,
							"strReffralDepttext" : strReffralDepttext , 
							"strreferralType"    : strreferralType ,
							"strreferralTypeName" : strreferralTypeName ,
							"strReffralReason"   :  strReffralReason ,
							"strReffralDeptDone" : strReffralDeptDone ,
							
							"strExternalHospitalcode" : strExternalHospital ,
							"strExternalDepartmentcode"   :  strExternalDepartment ,
							"strExternalrefferalInstitutecode" : strExternalrefferalInstitute ,
							
							"strExternalHospitalName" : strExternalHospitalName1 ,
							"strExternalDepartmentName"   :  strExternalDepartmentName ,
							"strExternalrefferalInstituteName" : strExternalrefferalInstituteName ,
							'strOtherAssociateHospitalName'		: strOtherAssociateHospitalName ,
							'strShowData'  						: strShowData
					} ;
					console.log(reffralJson);
					strrefcount++;
					var tooltipdata ='-';
					if(strreferralType != 0)
						tooltipdata =strreferralTypeName
					if(strReffralReason != '')
						tooltipdata =strreferralTypeName +'(' + strReffralReason +')';
					//console.log(JSON.stringify((reffralJson.toString()));
					$(this).parent().parent().parent().find('.refferalAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button"  data-toggle="tooltip" title='+ tooltipdata +'  class="value btn btn-xs '+strReffralDepttext.trim()+'">'+
			    	 		'<input type="checkbox" class="checkedInput" name="referalchk" id="strreferalchkId'+strrefcount+'"  value="" checked="">  '+
			    	 		'<span class="text">'+strShowData.trim()+'<sup style="color:red; font-weight:bold;"></sup> </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
					
					 var temp='#strreferalchkId'+strrefcount;
		                $(temp).val(JSON.stringify(reffralJson));
		                
				}
					$('#refferlPatientDeptId').html(refferlPatientDeptVal1);
					
					$('select[name=refferlPatientDept]').val('0#0#0#0#0#0#0#0');
					$('select[name=referralType]').val('0');
					$('#refferalResonId').val('');
					
					$('select[name=strExternalrefferalInstitute]').val('-1');
					$('select[name=strExternalrefferalHospital]').val('0');
					
					
					
					$('[data-toggle="tooltip"]').tooltip();
			 
			
	});
	var doaseCmbValue='';
	var refferlPatientDeptVal1 = '';
	var strExternalDepartmentListhtml = '';
	$( document ).ready(function() {
	    doaseCmbValue= $('#drugDosageId').html();
	    refferlPatientDeptVal1 = $('#refferlPatientDeptId').html();
	    strExternalDepartmentListhtml = $('#strExternalDepartmentListId').html();
	});
	
	
	$(function() {
        $(".AddTogleclassBtn").on("click", function() {
            $('html, body').animate({
                scrollTop: $(".AddTogleclassBtn").offset().top + 50
            }, 1000);

        });
	 });
	
	$('.drugNameCleanBtn').click(function(){
		$('#drugDosageId').html(doaseCmbValue);
	});
	
	
	$('#referralTypeId').change(function(){
		 	
		 var strFlag=  ($('select[name=referralType] option:selected').val());
		 if(strFlag ==4 || strFlag == 5){
		
		
		 if(strFlag == 5 ){
			 $('#refferlPatientDeptDivId').hide();
			 $('#strExternalDepartmentListDiv').show();
				$('#strExternalrefferalInstituteDivId').show(); 
			}else{
				$('#strExternalrefferalInstituteDivId').hide(); 
				$('#strExternalDepartmentListDiv').hide();
				$('#refferlPatientDeptDivId').show();
			}
		 
 if(strFlag == 4 ){
			 
			 $('#refferlPatientDeptDivId').hide();
			 $('#strExternalDepartmentListDiv').show();
			 $('#strExternalrefferalHospitalDivId').show(); 
					
			
		}else{
			$('#strExternalrefferalHospitalDivId').hide();
			$('#strExternalDepartmentListDiv').hide();
			$('#refferlPatientDeptDivId').show();
		}
			 
		 }else{
			 
				$('#strExternalrefferalHospitalDivId').hide();
				$('#strExternalDepartmentListDiv').hide();
				$('#strOtherHospitalNameDivId').hide();
				$('#strExternalrefferalInstituteDivId').hide();
				$('#refferlPatientDeptDivId').show();
				
		 $('#refferlPatientDeptId').html(refferlPatientDeptVal1);
		  var intemtype1=  ($('select[name=referralType] option:selected').val());
		  if(intemtype1 == 0)
			  $('#refferlPatientDeptId').html(refferlPatientDeptVal1);
		  
	       // console.log( $('#refferlPatientDeptId').html());
	        var options = $('#refferlPatientDeptId option');
	        var tempcount=0;
	       
	        if(intemtype1 == 3)
	        	intemtype = 2 ;
	        else
	        	intemtype = 1 ;
	        if(intemtype == 2){
	        	console.log('intemtype  intemtype' +intemtype);
	        	//$('#refferlPatientDeptId').html(refferlPatientDeptVal1);
	        	var values = $.map(options ,function(option) {
	        	console.log(intemtype == (option.value).split('#')[8]);
	        	if(intemtype == (option.value).split('#')[8])
	        		{
	        		$("#refferlPatientDeptId").val(option.value);	
	        			
	        			tempcount =1 ;
	        			 
	        		}else{
	        			$("#refferlPatientDeptId option[value='"+option.value+"']").remove();
		        		
	        			//$(this).remove();
	        			//tempcount=0;
	        			
	        		}
	        	$("#refferlPatientDeptId").prop("selectedIndex", 0);
	        	//console.log(':::::::1222::'+'#drugDosageId').html());
	        	/*if(tempcount == 0)
	        		{
	        		$("#drugDosageId").html('<option value="0" >select </option>');
	        		}
	        	*/
	        	/*else{
	        			$("#drugDosageId").val('0');
	        		}*/
	            return option.value;
	        });
	       }else{
	    	   //$('#refferlPatientDeptId').html(refferlPatientDeptVal1);
	    	   console.log('intemtype  intemtype' +intemtype);
	    	   var values = $.map(options ,function(option) {
		        	console.log(intemtype == (option.value).split('#')[8]);
		        	if(intemtype == (option.value).split('#')[8])
		        		{
		        		$("#refferlPatientDeptId").val(option.value);	
		        			
		        			tempcount =1 ;
		        			 
		        		}else{
		        			$("#refferlPatientDeptId option[value='"+option.value+"']").remove();
			        		
		        			//$(this).remove();
		        			//tempcount=0;
		        			
		        		}
		        	$("#refferlPatientDeptId").prop("selectedIndex", 0);
		        	//console.log(':::::::1222::'+'#drugDosageId').html());
		        	/*if(tempcount == 0)
		        		{
		        		$("#drugDosageId").html('<option value="0" >select </option>');
		        		}
		        	*/
		        	/*else{
		        			$("#drugDosageId").val('0');
		        		}*/
		            return option.value;
		        });
	    	}
		 }
		//$('#refferlPatientDeptId').html(refferlPatientDeptVal1);
	});
	
	
	$('#strExternalrefferalHospitalId').change(function(){
		
		$('#strExternalDepartmentListId').html(strExternalDepartmentListhtml);
		var tempcount1=0;
		var intemtype=  ($('select[name=strExternalrefferalHospital] option:selected').val());
		
		var options = $('#strExternalDepartmentListId option');
        
		var values = $.map(options ,function(option) {
        	console.log(intemtype +"  "+    (option.value).split('#')[10]);
        	if(intemtype == (option.value).split('#')[10])
        		{
        		$("#strExternalDepartmentListId").val(option.value);	
        			
        		tempcount1 = 1 ;
        			 
        		}else{
        			$("#strExternalDepartmentListId option[value='"+option.value+"']").remove();
	        		
        			//$(this).remove();
        			//tempcount1=0;
        			
        		}
        	$("#strExternalDepartmentListId").prop("selectedIndex", 0);
        	
        	
        	/*else{
        			$("#drugDosageId").val('0');
        		}*/
            return option.value;
        });
		console.log('::::::::::  '+tempcount1);
    	
		if(tempcount1 == 0)
		{
		$("#strExternalDepartmentListId").html('<option value="0#0#0#0#0#0#0#0#0#0">Select Department</option>');
		}
		
	});
	
	
$('#strExternalrefferalInstituteId').change(function(){
	 $('#refferlPatientDeptDivId').hide();
	 $('#strExternalDepartmentListDiv').show();
	
	 var strOtherExtHospitalCode=  ($('select[name=strExternalrefferalInstitute] option:selected').val());
	 if(strOtherExtHospitalCode == 0){
		 $('#strOtherHospitalNameDivId').show();
		 $('#strExternalDepartmentListDiv').hide();
			
	 }else{
		 $('#strExternalDepartmentListDiv').show();
		 $('#strOtherHospitalNameDivId').hide();
	 }
	 
		$('#strExternalDepartmentListId').html(strExternalDepartmentListhtml);
		var tempcount1=0;
		var intemtype=  100;
		
		var options = $('#strExternalDepartmentListId option');
        
		var values = $.map(options ,function(option) {
        	console.log(intemtype +"  "+    (option.value).split('#')[10]);
        	if(intemtype == (option.value).split('#')[10])
        		{
        		$("#strExternalDepartmentListId").val(option.value);	
        			
        		tempcount1 = 1 ;
        			 
        		}else{
        			$("#strExternalDepartmentListId option[value='"+option.value+"']").remove();
	        		
        			//$(this).remove();
        			//tempcount1=0;
        			
        		}
        	$("#strExternalDepartmentListId").prop("selectedIndex", 0);
        	
        	
        	/*else{
        			$("#drugDosageId").val('0');
        		}*/
            return option.value;
        });
		console.log('::::::::::  '+tempcount1);
    	
		if(tempcount1 == 0)
		{
		$("#strExternalDepartmentListId").html('<option value="0#0#0#0#0#0#0#0#0#0">Select Department</option>');
		}
		
	});
	
	
	$(document).ready(function(){
		
		var patCompleteGeneralDtl = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val();
		var reqFlg=patCompleteGeneralDtl.split('#')[18];
		var reqId=patCompleteGeneralDtl.split('#')[19];
		//console.log(reqFlg + ' ===  '+ reqId);
		if(reqFlg == '0'){
			$('.videocallList').hide();
			$('.eteleconsultancyViewDocclass').hide();
			$('#patientcomplaintid').hide();
			
		}
		
	});
	
	function getpdffile(e ){
		
		var temp=e.value;
		$.ajax({url:'/HISDRDESK/services/restful/Template/printpdf/',type:'POST',data:{fileName: temp.split('#')[0] , hosp_code : temp.split('#')[1] },success:function(result)
	    	{
			console.log("----------------------");

			base64toPDF1(result.base64);

			}
	});
	}
	
	function getpdffile1(e ){
		
		var temp=e.value;
		console.log(temp);
		$.ajax({url:'/HISDRDESK/services/restful/Template/printpdf1/',type:'POST',data:{fileName: temp.split('#')[1] , hosp_code : temp.split('#')[0] },success:function(result)
	    	{
			console.log("----------------------"+result.base64);
			
			var isChrome = navigator.userAgent.indexOf("Chrome") != -1; 
			//console.log('isChrome '+isChrome);
			if(isChrome)
			{ 
				var pdfWindow = window.open("");
				pdfWindow.document.write("<object style='width:100vw; height:100vh' data='data:application/pdf;base64,"+result.base64+"'></object>");
				
			}
			else
			{		 
			 window.open("data:application/pdf;base64, " + result.base64); 
			} 
	
			
			//base64toPDF1(result.base64); // commented by ashutoshk to stop duplicate window opening while viewing file

			}
	});
	}
	
	function base64toPDF1(data) {
	    var bufferArray = base64ToArrayBuffer1(data);
	    var blobStore = new Blob([bufferArray], { type: "application/pdf" });
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

	function base64ToArrayBuffer1(data) {
	    var bString = window.atob(data);
	    var bLength = bString.length;
	    var bytes = new Uint8Array(bLength);
	    for (var i = 0; i < bLength; i++) {
	        var ascii = bString.charCodeAt(i);
	        bytes[i] = ascii;
	    }
	    return bytes;
	};
	
	
	$('#clinicalServiceAreaId').change(function(){
		var ClinicalProcedureObj='';
		var jsonarray =[];
		var j=0;
	//	if(!localStorage.getItem('ClinicalProcedureObj'))
		var clinicalServiceAreaIdva=$('#clinicalServiceAreaId').val().split('^')[0];
		console.log(clinicalServiceAreaIdva);
			ClinicalProcedureObj=JSON.parse(localStorage.getItem('ClinicalProcedureObj1')); 
		
			for (i = 0; i < ClinicalProcedureObj.length; i++) {
				 console.log((ClinicalProcedureObj[i].testId).split('^')[6]);
				 var val1=(ClinicalProcedureObj[i].testId).split('^')[6];
				 if(val1 == clinicalServiceAreaIdva){
					 jsonarray[j]= ClinicalProcedureObj[i];
					 j++;
				 }
				} 
			 localStorage.removeItem("ClinicalProcedureObj"); 
			 console.log(jsonarray);
			localStorage.setItem('ClinicalProcedureObj',JSON.stringify(jsonarray));  
		
		
		});
	
	
	function  getDrugAdvice(){
		$('#drugInstructionsId1').val($('#strDrugsRemarksId').val());
		$('#drugAdvicesInstructionsModal1').modal('toggle');
	}
	
	function  getDrugAdvice1(){
		$('#externalDrugInstructionsId').val($('#strDrugsRemarksId1').val());
		$('#drugAdvicesInstructionsModal2').modal('toggle');
	}
	
	