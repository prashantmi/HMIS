
$(document).ready(function(){
	
	var profileArraId=[];
	console.log('Inside Precription Profile');
	
	$('input[name=SearchPara]').on('select:flexdatalist',function(e, set, options){
		
		/* alert('You pressed enter!'+e.which);
		if(e.which == 13) {
	        alert('You pressed enter!');
	    }*/
		var investigation = $(this).val(); 
 		let f = 0;
 		var invObj = localStorage.getItem('PrescriptionBookMarkJsonArrayObj'); 
 		invObj = JSON.parse(invObj); 
 		var val;
 		var profileId ;
 		for(var v=0; v<invObj.length;v++)
 		{ 
 			if (invObj[v].SERACH_DATA.toUpperCase() == investigation.toUpperCase()) {
		        console.log(invObj[v].HSTR_PROFILE_NAME+'::::::::>>>>'+JSON.stringify(invObj[v].HJOSN_RX_COMPLETE));
		        val = invObj[v].HJOSN_RX_COMPLETE;
		        profileId= invObj[v].HRGNUM_PROFILE_ID;
		        f=1;
		        break;
		    } 
 		}
 		for(var z=0; z<profileArraId.length;z++)
 		{
 			if(profileId == profileArraId[z])
 				{
 				var box= confirm("Bookmark is already added ! \n Are you sure want to add original data and delete Previoud Data ?");
 				if(box == true){
 					$( ".clearAllValues" ).click();
 					getPrescriptionProfileJsonData(val);
 					profileArraId =[];
 					profileArraId[profileArraId.length]=profileId;
 					$('#SearchParaIs').val('');
 					return false;
 				}else{
 					$('#SearchParaIs').val('');
 					return false
 				}
 				}
 		}
 		//console.log('NKIT'+profileArraId.length)
 		if(profileArraId.length >= 1){
 			
 			var box= confirm("One Bookmark is already added ! \n Are you sure want to add new Bookmark data and delete Previoud Data ?");
				if(box == true){
					$( ".clearAllValues" ).click();
		 			profileArraId =[];
		 			profileArraId[profileArraId.length]=profileId;
		 			getPrescriptionProfileJsonData(val);
		 			$('#SearchParaIs').val('');
		 			return false;
				}else{
					$('#SearchParaIs').val('');
					return false;
				}
				
 			
 		}
 		if(f==1)
 			{
 			$( ".clearAllValues" ).click();
 			console.log('profileId'+profileId +'profileId length '+profileArraId.length);
 			profileArraId[profileArraId.length]=profileId;
 			$('#SearchParaIs').val('');
 				getPrescriptionProfileJsonData(val);
 			}
 	    //}
 	});
});

/*function getDataPrescriptionProfile(e){
	console.log('Test::::::::');
}
*/
function getPrescriptionProfileJsonData(val){
	//alert(1);
	console.log('Inside Prescription Profile Json');
	console.log(val.InvestigationJsonArray.length);
	
	//$('#PresCriptionModal').modal('hide');
	if(val.InvestigationJsonArray.length!=null)
	{
		var flag = 0;
		val.InvestigationJsonArray.forEach(function(item){
			var tempWidth = 0;
			$('.investigationAdded').find('a').each(function(index){
				tempWidth+=$(this).width();
			});
			if(tempWidth > ($('.investigationAdded').width()-380))
			{
				flag=1;
				
				
				
				//$('.investigationAdded').append('<a class="moreToggleInvestigation '+item.LABNAME.trim().split(' ').join('_')+'" style="padding-left: 5px;display:none;"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+item.PRVTESTCODE+'^'+item.PRVLABCODE+'^'+item.LABNAME+'"> '+item.TESTNAME+'</label></a>');
				
				
				 var InvestigationDtlsJson = {
						 "IsExternal"   :   "0" ,
						 "TestName" 	:   item.TestName ,
			    		 "TestCode"		:	item.TestCode ,
			    		 "LabCode"		:	item.LabCode , 
			    		 "SampleCode"	:	item.SampleCode ,
			    		 "SampleName"	:	item.SampleName ,
			    		 "LabName"		:	item.LabName ,
			    		// "TestName"		:	investigationVAl1.split('^')[5] ,
			    		 "IsTestGroup"	:	"0" 
			     }
				 var sideremks=' ';
				 if('SideCode' in item ) {
				 
				 if(item.SideCode !='' && item.SideCode !='0')
					 sideremks = sideremks + '('+ item.SideName + ')' ;
				 if(item.SideRemarks != '')
					 sideremks = sideremks + ' ('+item.SideRemarks +')' ;
				}
				 
				 console.log(JSON.stringify(InvestigationDtlsJson));
				 
				if(item.LabCode != '0'){
					$('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;display:none;white-space: normal;" type="button" class="value btn btn-xs moreToggleInvestigation '+item.LABNAME.trim().split(' ').join('_')+'">'+
			    	 		'<input type="checkbox" class="checkedInput" checked name="reasonOfVisit" value="'+item.TestCode+'^'+item.LabCode+'^'+item.SampleCode+'^'+item.SampleName+'^'+item.LabName+'^'+item.TestName+'^0'+'">  '+
			    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
			    	 		'<span class="text text1">'+item.LabCode + sideremks +'<sup style="color:red; font-weight:bold;">*</sup>  </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
				}else{
					$('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;display:none;white-space: normal;" type="button" class="value btn btn-xs moreToggleInvestigation '+item.LABNAME.trim().split(' ').join('_')+'">'+
			    	 		'<input type="checkbox" class="checkedInput" checked name="reasonOfVisit" value="'+item.TestCode+'^'+item.LabCode+'^'+item.SampleCode+'^'+item.SampleName+'^'+item.LabName+'^'+item.TestName+'^0'+'">  '+
			    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
			    	 		'<span class="text text1">'+item.LabCode + sideremks+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
				}
			
	    	 		
				 tippy('.'+item.LabName.trim().split(' ').join('_'), {
		              content: item.LabName.trim(),
		              delay: 50,
		              arrow: true,
		              arrowType: 'round',
		              size: 'medium',
		              duration: 500,
		              animation: 'scale'
		          });
			}
			else
			{
				//$('.investigationAdded').append('<a class="'+item.LABNAME.trim().split(' ').join('_')+'" style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="reasonOfVisit" value="'+item.PRVTESTCODE+'^'+item.PRVLABCODE+'^'+item.LABNAME+'"> '+item.TESTNAME+'</label></a>');
				
				 var InvestigationDtlsJson = {
						 "IsExternal"   :   "0" ,
						 "TestName" 	:   item.TestName ,
			    		 "TestCode"		:	item.TestCode ,
			    		 "LabCode"		:	item.LabCode , 
			    		 "SampleCode"	:	item.SampleCode ,
			    		 "SampleName"	:	item.SampleName ,
			    		 "LabName"		:	item.LabName ,
			    		// "TestName"		:	investigationVAl1.split('^')[5] ,
			    		 "IsTestGroup"	:	"0"  
			     }
				 
				 var sideremks=' ';
					if('SideCode' in item ) {
					
					 
					 if(item.SideCode !='' && item.SideCode !='0')
						 sideremks = sideremks + '('+ item.SideName + ')' ;
					 if(item.SideRemarks != '')
						 sideremks = sideremks + ' ('+item.SideRemarks +')' ;
					}
					
				 console.log(JSON.stringify(InvestigationDtlsJson));
				 if(item.LabCode != '0'){
				$('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;white-space: normal;" type="button" class="value btn btn-xs '+item.LabName.trim().split(' ').join('_')+'">'+
		    	 		'<input type="checkbox" class="checkedInput" checked name="reasonOfVisit" value="'+item.TestCode+'^'+item.LabCode+'^'+item.SampleCode+'^'+item.SampleName+'^'+item.LabName+'^'+item.TestName+'^0'+'">  '+
		    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
		    	 		'<span class="text text1">'+item.TestName + sideremks+' </span>'+
		    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
		    	 		'</button></label>');
				 }else{
					 $('.investigationAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;white-space: normal;" type="button" class="value btn btn-xs '+item.LabName.trim().split(' ').join('_')+'">'+
				    	 		'<input type="checkbox" class="checkedInput" checked name="reasonOfVisit" value="'+item.TestCode+'^'+item.LabCode+'^'+item.SampleCode+'^'+item.SampleName+'^'+item.LabName+'^'+item.TestName+'^0'+'">  '+
				    	 		'<i class="" style="display :none">'+JSON.stringify(InvestigationDtlsJson)+' </i>'+
				    	 		'<span class="text text1">'+item.TestName+ sideremks +'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
				    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
				    	 		'</button></label>');
				 }
				 tippy('.'+item.LabName.trim().split(' ').join('_'), {
		              content: item.LabName.trim(),
		              delay: 50,
		              arrow: true,
		              arrowType: 'round',
		              size: 'medium',
		              duration: 500,
		              animation: 'scale'
		          });

			}

		});

		if(flag==1)
		{
			$('.investigationAdded').parent().prepend('<a style="text-decoration:none;float:right" class="btn btn-link investigationAddedMoreViewBtn">more..</a>');					
/*					$('.investigationAdded').parent().parent().find('div').eq(1).append('<a style="text-decoration:none" class="btn btn-link investigationAddedMoreViewBtn">more..</a>');*/					
			$('.investigationAddedMoreViewBtn').click(function(){
				$('.investigationAdded .moreToggleInvestigation').fadeToggle('fast');
				/*$(this).hide();*/
			});
		}
	}
	
	/*for drug*/
	
	
	if(val.DrugJsonArray.length!=null)
	{
		var flag = 0;
		val.DrugJsonArray.forEach(function(item,index){   
			if(true)   // rest of items will be hidden with 'more...' link
			{
				var strfrequnctName ='';
				if(item.FrequencyCode == '11')
					strfrequnctName = 'OD';
				else if(item.FrequencyCode == '12')
					strfrequnctName = 'BD';
				else if(item.FrequencyCode == '13')
					strfrequnctName = 'TDS';
				else if(item.FrequencyCode == '14')
					strfrequnctName = 'SOS';
				else if(item.FrequencyCode == '15')
					strfrequnctName = 'QID';
				else if(item.FrequencyCode == '16')
					strfrequnctName = 'HS';
				else
					strfrequnctName = '--';
					
				
			  
				var DrugJson ={
	 					"IsExterNal"	:		"0" ,	
	 					"DrugName"		 :	 item.DrugName ,
	 					"DrugCode"		 :	 item.DrugCode ,
	 					"DoaseName"		:	item.DoaseName ,
	 					"DoaseCode"		:	item.DoaseCode ,
	 					"FrequencyName"	:	strfrequnctName ,
	 					"FrequencyCode" :	item.FrequencyCode ,
	 					"StartDate"		:	item.StartDate ,
	 					"DrugDays"		:	item.DrugDays ,
	 					"DrugQuantity"	:	"" ,
	 					"DrugInstruction" :	item.DrugInstruction
	 					
	 			}
				console.log(JSON.stringify(DrugJson));
				
				var today = new Date();
				var dd = today.getDate();

				var mm = today.getMonth()+1; 
				var yyyy = today.getFullYear();
				if(dd<10) 
				{
				    dd='0'+dd;
				} 

				if(mm<10) 
				{
				    mm='0'+mm;
				} 
				today = yyyy+'-'+mm+'-'+dd;
				
				var chckDrugVal = item.DrugName+'&&'+item.DrugCode+'&&'+item.DoaseName+'&&'+item.DoaseCode+'&&'+strfrequnctName+'&&'+item.FrequencyCode+'&&'+today+'&&'+item.DrugDays+'&&'+item.DrugInstruction;
				$('.drugsAdvicesAdded').parent().find('.table').children('tbody').append('<tr> <td><input type="checkbox" checked class="checkedInput" name="drugsAdvices" value="'+chckDrugVal+'" ><i class="text1" style="display :none">'+JSON.stringify(DrugJson)+' </i></td><td>'+item.DrugName+'</td><td>'+item.DoaseName+'</td><td>'+strfrequnctName+'</td><td>'+today+'</td><td>'+item.DrugDays+'</td><td>'+item.DrugQuantity+'</td><td><a  class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+item.DrugInstruction+'" onclick="$(\'#drugAdvicesInstructionsModal\').modal(\'show\');">'+item.DrugInstruction.substring(0, 4)+'..'+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td><td><button class="btn btn-xs monogrambtn" type="button" data-toggle="modal" data-target="CimsMonographId"><i class="fas fa-capsules" style="color: Crimson;"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs Genericbarndbtn" type="button" data-toggle="modal" data-target="GenericMonogramId"><i class="fas fa-tablets" style="color: DeepSkyBlue;"></i></button></td></tr>');				
				  $('.drugAdvicesInstructionsModalBtn').click(function(){ 
					$('#drugAdvicesInstructionsModal .modal-body p').text($(this).attr('drugInstructions'));
				});
				  $('.drugEditBtn').click(function(){
					  
					  console.log((item.DrugCode).split('#')[0] == 100);
					  console.log('::::::::::::::::'+item.DrugCode);
					    if($('input[name=drugName]').val()!='')
					    	return false;
					    
					    if((item.DrugCode).split('#')[0] == 100){
					    	 console.log('::::::::::::::::'+item.DrugCode);
					    	var drugName = $(this).parent().parent().find('td').eq(1).text();
					 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
					 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
					 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
					 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
					 		var drugQuantity = $(this).parent().parent().find('td').eq(6).text(); 
					 		var instructions = $(this).parent().parent().find('td').eq(7).text(); 
					 		$('#externalDrugBtn').click();
					 		$(this).parent().parent().remove();
					 		$('input[name=externalDrugName]').val(drugName);
					 		/*$('select[name=drugDosage]').val(drugDosage);*/
					 		$('select[name=externalDrugDosage]').val($('select[name=drugDosage] option:contains('+drugDosage+')').val());
					 		$('select[name=externalDrugFrequency]').val($('select[name=drugFrequency] option:contains('+drugFrequency+')').val());
					 		$('input[name=externalDrugStartDate]').val(drugStartDate); 
					 		$('input[name=externalDrugDays]').val(drugDays);
					 		$('input[name=externalDrugQuantity]').val(drugQuantity); 
					 		$('textarea[name=externalDrugInstructions]').val(instructions); 
					 		//$('input[name=externalDrugName]').focus();
					    } else{
					    	
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
					 		$('input[name=drugStartDate]').val(drugStartDate); 
					 		$('input[name=drugDays]').val(drugDays);
					 		$('input[name=drugQuantity]').val(drugQuantity); 
					 		$('textarea[name=drugInstructions]').val(instructions); 
					 		//$('input[name=drugName]').focus();
					 		//return false;
					    }
				 		
					});  
				  
				  $('.monogrambtn').click(function(){
					  
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

			}
			else
			{
				flag=1;
				var strfrequnctName ='';
				if(item.FrequencyCode == '11')
					strfrequnctName = 'OD';
				else if(item.FrequencyCode == '12')
					strfrequnctName = 'BD';
				else if(item.FrequencyCode == '13')
					strfrequnctName = 'TDS';
				else if(item.FrequencyCode == '14')
					strfrequnctName = 'SOS';
				else if(item.FrequencyCode == '15')
					strfrequnctName = 'QID';
				else if(item.FrequencyCode == '16')
					strfrequnctName = 'HS';
				else
					strfrequnctName = '--';
					
				var today = new Date();
				var dd = today.getDate();

				var mm = today.getMonth()+1; 
				var yyyy = today.getFullYear();
				if(dd<10) 
				{
				    dd='0'+dd;
				} 

				if(mm<10) 
				{
				    mm='0'+mm;
				} 
				today = yyyy+'-'+mm+'-'+dd;
				
				var chckDrugVal = item.DrugName+'&&'+item.DrugCode+'&&'+item.DoaseName+'&&'+item.DoaseCode+'&&'+strfrequnctName+'&&'+item.FrequencyCode+'&&'+today+'&&'+item.DrugDays+'&&'+item.DrugInstruction;
			  
				var DrugJson ={
	 					"IsExterNal"	:		"0" ,	
	 					"DrugName"		 :	 item.DrugName ,
	 					"DrugCode"		 :	 item.DrugCode ,
	 					"DoaseName"		:	item.DoaseName ,
	 					"DoaseCode"		:	item.DoaseCode ,
	 					"FrequencyName"	:	strfrequnctName ,
	 					"FrequencyCode" :	item.FrequencyCode ,
	 					"StartDate"		:	item.StartDate ,
	 					"DrugDays"		:	item.DrugDays ,
	 					"DrugQuantity"	:	"" ,
	 					"DrugInstruction" :	item.DrugInstruction
	 					
	 			}
				console.log(JSON.stringify(DrugJson));
				
				
				$('.drugsAdvicesAdded').parent().find('.table').children('tbody').append('<tr class="moreToggleDrugAdvice" style="display:none"> <td><input checked type="checkbox" class="checkedInput" name="drugsAdvices" value="'+chckDrugVal+'" ><i class="text1" style="display :none">'+JSON.stringify(DrugJson)+' </i></td><td>'+item.DrugName+'</td><td>'+item.DoaseName+'</td><td>'+strfrequnctName+'</td><td>'+item.StartDate+'</td><td>'+item.DrugDays+'</td><td>'+item.DrugQuantity+'</td><td><a  class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" drugInstructions="'+item.DrugInstruction+'" onclick="$(\'#drugAdvicesInstructionsModal\').modal(\'show\');">'+item.DrugInstruction.substring(0, 4)+'..'+'</a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td><td><button class="btn btn-xs monogrambtn" type="button" data-toggle="modal" data-target="CimsMonographId"><i class="fas fa-capsules" style="color: Crimson;"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs Genericbarndbtn" type="button" data-toggle="modal" data-target="GenericMonogramId"><i class="fas fa-tablets" style="color: DeepSkyBlue;"></i></button></td></tr>');				
				  $('.drugAdvicesInstructionsModalBtn').click(function(){ 
					$('#drugAdvicesInstructionsModal .modal-body p').text($(this).attr('drugInstructions'));
				});
				  $('.drugEditBtn').click(function(){
					  
					  console.log(item.DrugCode == 100);
					  console.log('::::::::::::::::'+item.HSTNUM_ITEM_ID);
					  
					    if($('input[name=drugName]').val()!='')
					    	return false;
					    	if(item.DrugCode == 100){
					    	
					    	var drugName = $(this).parent().parent().find('td').eq(1).text();
					 		var drugDosage = $(this).parent().parent().find('td').eq(2).text();
					 		var drugFrequency = $(this).parent().parent().find('td').eq(3).text(); 
					 		var drugStartDate = $(this).parent().parent().find('td').eq(4).text(); 
					 		var drugDays = $(this).parent().parent().find('td').eq(5).text(); 
					 		var drugQuantity = $(this).parent().parent().find('td').eq(6).text(); 
					 		var instructions = $(this).parent().parent().find('td').eq(7).text(); 
					 		
					 		$(this).parent().parent().remove();
					 		$('input[name=externalDrugName]').val(drugName);
					 		/*$('select[name=drugDosage]').val(drugDosage);*/
					 		$('select[name=externalDrugDosage]').val($('select[name=externalDrugDosage] option:contains('+drugDosage+')').val());
					 		$('select[name=externalDrugFrequency]').val($('select[name=externalDrugFrequency] option:contains('+drugFrequency+')').val());
					 		$('input[name=externalDrugStartDate]').val(drugStartDate); 
					 		$('input[name=externalDrugDays]').val(drugDays);
					 		$('input[name=externalDrugQuantity]').val(drugQuantity); 
					 		$('textarea[name=externalDrugInstructions]').val(instructions); 
					 		$('input[name=externalDrugName]').focus();
					    } else{
					    	
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
					 		$('input[name=drugStartDate]').val(drugStartDate); 
					 		$('input[name=drugDays]').val(drugDays);
					 		$('input[name=drugQuantity]').val(drugQuantity); 
					 		$('textarea[name=drugInstructions]').val(instructions); 
					 		//$('input[name=drugName]').focus();
					 		//return false;
					    }
					});  
				  
				  	$('.monogrambtn').click(function(){
					  
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
			}
		});
		
		if(flag == 1)
		//if(5 == 6)
		{
		      $('.drugsAdvicesAdded').parent().append('<a style="text-decoration:none" class="btn btn-link drugAdviceAddedMoreViewBtn">more...</a>');				
			  $('.drugAdviceAddedMoreViewBtn').click(function(){
				  $('.moreToggleDrugAdvice').fadeToggle('fast');
			  });				
		}
		 
	}
	
	
	if(val.DiagnosisNote !=null){
		$('#diagnosisNoteId').val(val.DiagnosisNote);
	}
	
	if(val.InvestgationNote !=null){
		$('#investigationNoteId').val(val.InvestgationNote);
	}
	
	if(val.strtreatmentAdvice !=null){
		$('#treatmentAdviceId').val(val.strtreatmentAdvice);
	}
	
	if(val.FOLLOW_UP[0].progressNote !=null){
		$('#progressNote').val(val.FOLLOW_UP[0].progressNote);
	}
	
	//progressNote
	
	if(val.ClinicalProcedureJsonArray!=null)
	{
		val.ClinicalProcedureJsonArray.forEach(function(item){ 
			
			var ClinicalProcedureJson = {
						"IsExternal"  			:  item.IsExternal ,
						"ProceduresName"		:	item.ProceduresName ,
						"ProcedureCode"			:	item.ProcedureCode ,
						"ProcedureSideCode"		:	item.ProcedureSideCode ,
						"ProcedureSideName"		:	item.ProcedureSideName ,
						"ProcedureSideRemarks"	:	item.ProcedureSideRemarks ,
						"ServiceAreaCode"		:	item.ServiceAreaCode,
						"ServiceAreaName"		:	item.ServiceAreaName
				};
				 console.log(JSON.stringify(ClinicalProcedureJson));
			// Dressing#3310181^102#1#NR#sss\
				 var tooltipval='';
				 
				 if(item.ProcedureSideCode != '0'){
					 tooltipval = tooltipval +" ["+item.ProcedureSideName + "]";
				 }
				 if(item.ProcedureSideRemarks !=''){
					 tooltipval = tooltipval + " " + item.ProcedureSideRemarks;
				 }
			var clinicalProcValue=item.ProceduresName+'#'+item.ProcedureCode+'#'+item.ProcedureSideCode+'#'+item.ProcedureSideName+'#'+item.ProcedureSideRemarks+'#'+item.ServiceAreaCode+'#'+item.ServiceAreaName;
				$('.clinicalProceduresAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" data-toggle="tooltip" title="'+ tooltipval +'" class="value btn btn-xs">'+
		    	 		'<input type="checkbox" class="checkedInput" name="clinicalProc" value="'+clinicalProcValue+'" checked="checked">  '+
		    	 		'<i class="" style="display :none">'+JSON.stringify(ClinicalProcedureJson)+' </i>'+
		    	 		'<span class="text">'+item.ProceduresName+' </span>'+
		    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
		    	 		'</button></label>');
				
				
			
				
		});
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	if(val.ReasonOfVisitJsonArray!=null)
	{
		var flag = 0;
		var temp='';
		val.ReasonOfVisitJsonArray.forEach(function(item){ 
			console.log(item.ReasonOfVisitJsonArray);
			
			
			/*var str=(item.HSTTR_REASONOFVISIT).replace("[", "");
			str=str.replace(']','');
			var temp= (str).split(',');
			*/
			/*item.HSTTR_REASONOFVISIT.forEach(function(it)
			{
				console.log(':::::::::::'+it.val());
			}		
			);*/
			
/*			for(var i=0 ; i<temp.length;i++)
				{*/
				console.log(':::::::::::'+temp[i]);
				
			/*if(temp[i]!='')
			{*/
/*				var x=temp[i].split('^')[2];
				console.log('Side::::::'+x);
				switch (parseInt(x)) {
				  case 0:
				    text = "Side";
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
				    text = "No value found";
				} 
				var y=temp[i].split('^')[4];
				switch (parseInt(y)) {
				  case 0:
				    text1 = "";
				    break;
					case 1:
				    text1 = "Days";
				    break;
					case 2:
				    text1 = "Weeks";
				    break;
					case 3:
				    text1 = "Months";
				    break;
					case 4:
				    text1 = "Years";
				    break;
					default:
				    text1 = "No value found";
				}

				*/
				var tmp='';
				/*if(temp[i].split('^')[4] != 0 && temp[i].split('^')[2] != 0)
				tmp=text+'['+ temp[i].split('^')[2] +text1 + ']';
				else if(temp[i].split('^')[4] != 0)
					tmp='['+ temp[i].split('^')[2] +text1 + ']';
				else if(temp[i].split('^')[2] != 0)
					tmp=text ;
				*/
				
				var tempWidth = 0;
				$('.reasonOfVisitAdded').find('a').each(function(index){
					tempWidth+=$(this).width();
				});
				if(tempWidth > ($('.reasonOfVisitAdded').width()-250))
				{
					
					flag=1;
					if(item.VisitReasonSideName !='' && item.VisitReasonSideName !='0')
					{
						tmp=tmp +' '+item.VisitReasonSideName ;
					}
					if(item.VisitReasonNoOfDays !=''){
						tmp=tmp + '[' +item.VisitReasonNoOfDays + ' ' +  item.VisitComplaintDurationName +' ]';
					}
					if(item.VisitReasonRemarks != ''){
						tmp=tmp+ ' ' +item.VisitReasonRemarks ;
					}
					
					console.log('tmptmp'+tmp);
					var chiefComplaintJson ={
							"IsExternalVisit"	:			"1" ,
							"VisitReasonName" : 		 	item.VisitReasonName,
							"VisitReasonCode" :			 	item.VisitReasonCode,
							"VisitReasonSideCode" : 		"0",
							"VisitReasonSideName" :			item.VisitReasonSideName,
							"VisitReasonNoOfDays" : 	 	"",
							"VisitComplaintDurationCode" :  "1",
							"VisitComplaintDurationName" :  item.VisitComplaintDurationName,
							"VisitReasonRemarks"		: 	"",
						};
						
						console.log(JSON.stringify(chiefComplaintJson));
						//41982003^Male climacteric^2^15^2^Renmsk
						var tempchkval=item.VisitReasonCode +'^'+item.VisitReasonName +'^'+item.VisitReasonSideCode +'^'+item.VisitReasonNoOfDays +'^'+item.VisitComplaintDurationCode +'^'+item.VisitReasonRemarks;
					//$('.reasonOfVisitAdded').append('<a class="moreToggleVisitReason" style="padding-left:5px;display:none;"><label><input class="checkedInput" type="checkbox" name="visitReason" value="'+item.VISITREASON+'"> '+item.VISITREASON+'</label></a>');
					
					$('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;display:none;white-space: normal;" type="button" class="value btn btn-xs moreToggleVisitReason"    title="'+tmp+'" data-toggle="tooltip" >'+
			    	 		'<input type="checkbox" checked class="checkedInput" name="visitReason" value="'+tempchkval+'">  '+
			    	 		'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
			    	 		'<span class="text">'+item.VisitReasonName+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
				}
				else
					{
					if(item.VisitReasonSideName !='' && item.VisitReasonSideName !='0')
					{
						tmp=tmp+" "+item.VisitReasonSideName ;
					}
					if(item.VisitReasonNoOfDays !=''){
						tmp=tmp+ "[" + item.VisitReasonNoOfDays + " " +  item.VisitComplaintDurationName +" ]";
					}
					if(item.VisitReasonRemarks != ''){
						tmp=tmp+ " " +item.VisitReasonRemarks ;
					}
					
					console.log('tmptmp'+tmp);
					var chiefComplaintJson ={
							"IsExternalVisit"	:			"1" ,
							"VisitReasonName" : 		 	item.VisitReasonName,
							"VisitReasonCode" :			 	item.VisitReasonCode,
							"VisitReasonSideCode" : 		"0",
							"VisitReasonSideName" :			item.VisitReasonSideName,
							"VisitReasonNoOfDays" : 	 	"",
							"VisitComplaintDurationCode" :  "1",
							"VisitComplaintDurationName" :  item.VisitComplaintDurationName,
							"VisitReasonRemarks"		: 	"",
						};
					var tempchkval=item.VisitReasonCode +'^'+item.VisitReasonName +'^'+"0" +'^'+"" +'^'+"1" +'^'+"";
						console.log(JSON.stringify(chiefComplaintJson));
						
					$('.reasonOfVisitAdded').append('<label><button  tabindex="0" style="padding-left: 5px;font-weight:700;white-space: normal;" type="button" class="value btn btn-xs moreToggleVisitReason tooltipcss "   title="'+tmp+'" data-toggle="tooltip"  >'+
			    	 		'<input type="checkbox" checked class="checkedInput" name="visitReason" value="'+tempchkval+'">  '+
			    	 		'<i class="text" style="display :none">'+JSON.stringify(chiefComplaintJson)+' </i>'+
			    	 		'<span class="text" id="respRateIdValue"  >'+item.VisitReasonName+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
					//$(".tooltipcss").prop("title", tmp);
					//$('[data-toggle="tooltip"]').tooltip();
					}
					//$('.reasonOfVisitAdded').append('<a style="padding-left:5px"><label><input class="checkedInput" type="checkbox" name="visitReason" value="'+item.VISITREASON+'"> '+item.VISITREASON+'</label></a>');
				
					

			//}
		//}
		});

		if(flag==1)
		{
			$('.reasonOfVisitAdded').parent().prepend('<a style="text-decoration:none;float:right" class="btn btn-link reasonOfVisitAddedMoreViewBtn">more..</a>');					
/*					$('.reasonOfVisitAdded').parent().parent().find('div').eq(1).append('<a style="text-decoration:none" class="btn btn-link reasonOfVisitAddedMoreViewBtn">more..</a>');*/					
			$('.reasonOfVisitAddedMoreViewBtn').click(function(){
				$('.reasonOfVisitAdded .moreToggleVisitReason').fadeToggle('fast');
				$(this).hide();
			});
		}
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	
	if(val.DiagnosisJsonArray!=null)
	{
		var flag = 0;
		val.DiagnosisJsonArray.forEach(function(item){ 
	/*		console.log('::::::::::'+item.HSTTR_DIAGNOSIS);
			var str=(item.HSTTR_DIAGNOSIS).replace("[", "");
			str=str.replace(']','');
			var tmp= (str).split(',');
			
			for(var i=0 ; i<tmp.length;i++)
			{
				
			if(tmp[i]!='')
			{
				var tempWidth = 0;
				$('.diagnosisAdded').find('a').each(function(index){
					tempWidth+=$(this).width();
				});
				
				
				var a=tmp[i].split('^')[1];
				var x=a.split('#')[2];
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
				    text = "No value found";
				}
				var tooval = '';
				if(x !=0)
					tooval=a.split('#')[1] + '['+text+']' ;
				else
					tooval=a.split('#')[1];*/
			var tempWidth = 0;
			$('.diagnosisAdded').find('a').each(function(index){
				tempWidth+=$(this).width();
			});
		
				if(tempWidth > ($('.diagnosisAdded').width()-250))
				{
					flag=1;
					//$('.diagnosisAdded').append('<a class="moreToggleDiagnosis" style="padding-left:5px; display:none;"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisname="'+item.DIAGNOSTICTYPENAME+'" value="'+item.DIAGNOSTICCODE+'#'+item.DIAGNOSTICTYPECODE+'"> '+item.DIGNOSISNAME+'</label></a>');
					
					
					var DiagnosisJson ={
		 						"IsSnomed"				:			"1" ,
								"DiagnosisName" 		: 		 	item.DiagnosisName,
								"DiagnosisCode" 		:			item.DiagnosisCode,
								"DiagnosisSideCode" 	: 			item.DiagnosisSideCode ,
								"DiagnosisSideName" 	:			item.DiagnosisSideName,
								"DiagnosisTypeCode" 	: 	 		item.DiagnosisTypeCode ,
								"DiagnosisTypeNamee" 	: 			item.DiagnosisTypeNamee,
								"DiagnosisRemarks"	:				item.DiagnosisRemarks
							};
							
		 				console.log(JSON.stringify(DiagnosisJson));
		 				var tooval='';
		 				
		 				if(item.DiagnosisSideCode !='0')
		 					tooval=tooval+" "+item.DiagnosisSideName ;
		 				if(item.DiagnosisTypeCode !='0')
		 					tooval=tooval+" ("+item.DiagnosisTypeNamee + " )" ;
		 				if(item.DiagnosisRemarks != '')
		 					tooval=tooval+" "+item.DiagnosisRemarks ;
		 				
		 				console.log('toovaltooval'+tooval);
		 				
		 				//82214002#14#1^Five day fever#Final#2##0#Remaks
		 				var tempchkval=item.DiagnosisCode+'#'+item.DiagnosisTypeCode+'#'+item.IsSnomed+'^'+item.DiagnosisName+'#'+item.DiagnosisTypeNamee+'#'+item.DiagnosisSideCode+'##0'+'#'+item.DiagnosisRemarks
					$('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700; display:none;white-space: normal;" type="button" class="value btn btn-xs moreToggleDiagnosis tooltipcss" title="'+tooval+'" data-toggle="tooltip">'+
			    	 		'<input type="checkbox" checked class="checkedInput"  diagnosisname="'+item.DiagnosisName+'" name="diagnosisAdded" value="'+tempchkval+'">  '+
			    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
			    	 		'<span class="text">'+item.DiagnosisName+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
		 				/*$(".tooltipcss").prop("title", '');
		 				$(".tooltipcss").prop("title", tooval);*/
				}
				else{
					//$('.diagnosisAdded').append('<a style="padding-left:5px"><label><input type="checkbox" class="checkedInput" name="diagnosisAdded" diagnosisname="'+item.DIAGNOSTICTYPENAME+'" value="'+item.DIAGNOSTICCODE+'#'+item.DIAGNOSTICTYPECODE+'"> '+item.DIGNOSISNAME+'</label></a>'); 						
					
					
	 				
					var DiagnosisJson ={
	 						"IsSnomed"				:			"1" ,
							"DiagnosisName" 		: 		 	item.DiagnosisName,
							"DiagnosisCode" 		:			item.DiagnosisCode,
							"DiagnosisSideCode" 	: 			item.DiagnosisSideCode ,
							"DiagnosisSideName" 	:			item.DiagnosisSideName,
							"DiagnosisTypeCode" 	: 	 		item.DiagnosisTypeCode ,
							"DiagnosisTypeNamee" 	: 			item.DiagnosisTypeNamee,
							"DiagnosisRemarks"	:				item.DiagnosisRemarks
						};
						
	 				console.log(JSON.stringify(DiagnosisJson));
	 				var tooval="";
	 			
	 				if(item.DiagnosisSideCode !='0')
	 					tooval=tooval+" "+item.DiagnosisSideName ;
	 				if(item.DiagnosisTypeCode !='0' || item.DiagnosisTypeCode !='')
	 					tooval=tooval+" ("+item.DiagnosisTypeNamee + " )" ;
	 				if(item.DiagnosisRemarks != '')
	 					tooval=tooval+" "+item.DiagnosisRemarks ;
	 				
	 				console.log('toovaltooval'+tooval);
	 				var tempchkval=item.DiagnosisCode+'#'+item.DiagnosisTypeCode+'#'+item.IsSnomed+'^'+item.DiagnosisName+'#'+item.DiagnosisTypeNamee+'#'+item.DiagnosisSideCode+'##0'+'#'+item.DiagnosisRemarks
					var tmpcount=0;
					$('.diagnosisAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;white-space: normal;" type="button" class="value btn btn-xs moreToggleDiagnosis tooltipcss" title="'+tooval+'" data-toggle="tooltip">'+
		    	 		'<input type="checkbox" checked class="checkedInput" name="diagnosisAdded" diagnosisname="'+item.DiagnosisName+'" value="'+tempchkval+'">  '+
		    	 		'<i class="text" style="display :none">'+JSON.stringify(DiagnosisJson)+' </i>'+
		    	 		'<span class="text">'+item.DiagnosisName+' </span>'+
		    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
		    	 		'</button></label>');
	 				//$(".tooltipcss").prop("title", '');
	 				//$(".tooltipcss").prop("title", tooval);
		}
		});
		if(flag==1)
		{
			$('.diagnosisAdded').parent().prepend('<a style="text-decoration:none; float:right" class="btn btn-link disgnosisAddedMoreViewBtn">more..</a>');					
/*					$('.diagnosisAdded').parent().parent().find('div').eq(1).append('<a style="text-decoration:none" class="btn btn-link disgnosisAddedMoreViewBtn">more..</a>');*/					
			$('.disgnosisAddedMoreViewBtn').click(function(){
				$('.diagnosisAdded .moreToggleDiagnosis').fadeToggle('fast');
				/*$(this).hide();*/
			});
		}
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	if(val.PatientRefrel!=null)
	{
		var flag = 0;
		val.PatientRefrel.forEach(function(item){ 
			
			
			var reffralJson={
					"strReffralDeptCmb" : item.strReffralDeptCmb ,
					"strReffralDepttext" : item.strReffralDepttext , 
					"strreferralType"    : item.strreferralType ,
					"strreferralTypeName" : item.strreferralTypeName ,
					"strReffralReason"   :  item.strReffralReason ,
					"strReffralDeptDone" : item.strReffralDeptDone ,
					
					"strExternalHospitalcode" : item.strExternalHospital ,
					"strExternalDepartmentcode"   :  item.strExternalDepartment ,
					"strExternalrefferalInstitutecode" : item.strExternalrefferalInstitute ,
					
					"strExternalHospitalName" : item.strExternalHospitalName1 ,
					"strExternalDepartmentName"   :  item.strExternalDepartmentName ,
					"strExternalrefferalInstituteName" : item.strExternalrefferalInstituteName ,
					'strOtherAssociateHospitalName'		: item.strOtherAssociateHospitalName ,
					'strShowData'  						: item.strShowData
			} ;
			
			/*var reffralJson={
					"strReffralDeptCmb" : item.strReffralDeptCmb ,
					"strReffralDepttext" : item.strReffralDepttext , 
					"strreferralType"    : item.strreferralType ,
					"strreferralTypeName" : item.strreferralTypeName ,
					"strReffralReason"   :  item.strReffralReason ,
					"strReffralDeptDone" : item.strReffralDeptDone
			} ;*/
			console.log(reffralJson);
			
			var tooltipdata ='-';
			if(item.strreferralType != 0)
				tooltipdata =item.strreferralTypeName;
			if(item.strReffralReason != '')
				tooltipdata =item.strreferralTypeName +'(' + item.strReffralReason +' ) ';
			
			strrefcount++;
			$('.refferalAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button"  data-toggle="tooltip" title="'+ tooltipdata +'"  class="value btn btn-xs '+item.strReffralDepttext.trim()+'">'+
	    	 		'<input type="checkbox" class="checkedInput" name="referalchk" id="strreferalchkId'+strrefcount+'"  value="" checked="">  '+
	    	 		'<span class="text">'+item.strShowData.trim()+'<sup style="color:red; font-weight:bold;"></sup> </span>'+
	    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	    	 		'</button></label>');
			var temp='#strreferalchkId'+strrefcount;
            $(temp).val(JSON.stringify(reffralJson));
         
			
		});
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	$('#followUpPlannedVisitDaysId').val(val.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDays);
	$('#followUpPlannedVisitDateId').val(val.FOLLOW_UP[0].Planned_Visit[0].plannedVisitDate);
	if((val.FOLLOW_UP[0].Planned_Visit[0].plannedVisitSos).trim() == ('SOS').trim() ){
		$('#followUpPlannedVisitSosId').prop('checked', true);
	}
	$('#ConfidentialInfoId').val(val.strConfidentialsInfo);
}

function ActionRequiredForProfilePrescription(pid , status){
	
	console.log(pid+':::::::::::::::'+status);
	
	var data = JSON.stringify(vitalJSON); 
	console.log(data); 
	var vitalJSON = { 
			'strProfileId' : pid,
			'strStatus' : status,
	}
	var data = JSON.stringify(vitalJSON); 
	console.log(data);
	$.ajax({url:'/HISDRDESK/services/restful/DrDesk/savePrscriptionProfileData/',type:'POST',data:{JsonData:data},success:function(result)
    	{
			console.log(result);
			
			if(result == 1)
				{
				
				 $.alert({
				        title: 'Success!',
				        content: 'Data Saved successfully!!',
				    });
				 console.log('Vital Data Saved successfully');
				}else{
					console.log('Error!!!');
					 $.alert({
					        title: 'Error!',
					        content: 'Unable to Save Data!!',
					    });
				}
    	}
	});
				 
}