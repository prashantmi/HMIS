	$('#reasonOfVisitAddId').click(function()
 	{
		alert("rov called");
 		if($(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').val().trim().indexOf(';')>='0')
 		{ 			
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
						var visitReasonId=reasonOfVisitVAl[i]+'^'+$("#chiefComplaintSiteId").val()+'^'+$("#chiefComplaintNoOfDaysId").val()+'^'+$("#chiefComplaintDurationId").val();
						console.log('visitReasonId'+visitReasonId);
						 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left:5px"><label><input class="checkedInput" type="checkbox" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked> '+reasonOfVisitVAl[i]+'</label></a>');
						 
						 $(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
					    	 		'<input type="checkbox" class="checkedInput" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked="">  '+
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
 		else if($(this).parent().parent().find('input[name=generalComplaint]').val().trim() != ''){
            var generalComplaintVal = $(this).parent().parent().find('input[name=generalComplaint]').val();
            if(generalComplaintVal.trim()!='')
             {  
               var tmp = 0; 
               $('.reasonOfVisitAdded').find('label .text').each(function(index){ 
                 if($(this).text().split("(")[0].trim().toUpperCase()===generalComplaintVal.trim().toUpperCase()) 
                 { tmp = 1; 
                   return false;  }
               });
               if(tmp==1)
               {
                 swal("Already Added!!");
                 return false;
               }
               else
               {
                 var chiefComplaintNoOfDays=$(this).parent().parent().find('input[name="chiefComplaintNoOfDays"]').val();
                 var siteId=$(this).parent().parent().find('select[name="chiefComplaintSite"] option:selected').val();
                 var chiefComplaintDuration=$(this).parent().parent().find('select[name="chiefComplaintDuration"] option:selected').val();

                 if(siteId != '0' || (chiefComplaintDuration != '0' && chiefComplaintNoOfDays !='')){
                     if(siteId != '0' && (chiefComplaintDuration != '0' && chiefComplaintNoOfDays !=''))
                       var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^'+siteId+'^'+chiefComplaintNoOfDays+'^'+chiefComplaintDuration;
                     else if(siteId != '0')
                       var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^'+siteId+'^0^0'; 
                     else if(chiefComplaintDuration != '0' && chiefComplaintNoOfDays !='')
                       var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^0^'+chiefComplaintNoOfDays+'^'+chiefComplaintDuration;
                     else{
                       swal('Please select all fields');
                       return false;
                     }
                 }
                 else if(chiefComplaintDuration != '0' &&  chiefComplaintNoOfDays == ''){
                   swal('Please enter no of days.');
                   return false;
                 }
                 else if(chiefComplaintDuration == '0' &&  chiefComplaintNoOfDays != ''){
                   swal('Please select duration.');
                   return false;
                 }
                 else{
                   var generalComplaintChkBoxVal='0^'+generalComplaintVal+'^0^0^0';  
                 }
                 
                 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left: 5px;"><label> <input type="checkbox" class="checkedInput" name="visitReason" value="'+generalComplaintChkBoxVal+'"  checked> '+generalComplaintVal+'(ext)</label></a>');
                 
                 $(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
			    	 		'<input type="checkbox" class="checkedInput" name="visitReason" value="'+generalComplaintChkBoxVal+'" checked="">  '+
			    	 		'<span class="text">'+generalComplaintVal+'<sup style="color:red; font-weight:bold;">*</sup> </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');

               }
              
              $(this).parent().parent().find('input[name=generalComplaint]').val(''); 
              $(this).parent().parent().find('input[name="chiefComplaintNoOfDays"]').val('');
              $(this).parent().parent().find('select[name="chiefComplaintSite"]').val('0');
              $(this).parent().parent().find('select[name="chiefComplaintDuration"] ').val('0');
             }
             else{
               swal('Please enter other general complaint to be added');
             }
     }
 		else
 			 swal('Please Select atleast one reason');
 	}); 
	
	
	
	function addComplaints()
 	{
		//alert("rov called");
		//alert($('input[name=txt-snomed-ct-search_VR]').val());
		
 		if($('input[name=txt-snomed-ct-search_VR]').val().trim().indexOf(';')>='0')
 		{ 
 			//alert("1");
		 		if($('input[name=txt-snomed-ct-search_VR]').val().trim()!='')
		 		{ 
		 			//alert("2");
		 		   var reasonOfVisitVAl = $('input[name=txt-snomed-ct-search_VR]').val().split(';');
		 		   //alert("reasonOfVisitVAl:"+reasonOfVisitVAl)
			 	   /*var reasonOfVisitCode = $('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode').split(';');
		 		   alert("reasonOfVisitCode:"+reasonOfVisitCode);*/
		 		 
			 	   for(var i=0;i<(reasonOfVisitVAl.length-1);i++)
		 			{
		 			  var tmp = 0; 
						$('.reasonOfVisitAdded').find('label .text').each(function(index){ 
							if($(this).text().trim().toUpperCase()===reasonOfVisitVAl[i].trim().toUpperCase()) 
							{	tmp = 1; 
								return false;  }
						});
						//alert("tempValue="+tmp);
						if(tmp==1)
						{
							alert(reasonOfVisitVAl+",Already Added");
		
							//swal(reasonOfVisitVAl[i].trim()+", Already Added!!");
							continue;
						}
						//document.getElementById('chiefParaId').style.display = 'block';
						var visitReasonId=reasonOfVisitVAl[i]+'^'+$("#chiefComplaintSiteId").val()+'^'+$("#chiefComplaintNoOfDaysId").val()+'^'+$("#chiefComplaintDurationId").val();
						//alert("visitReasonId:"+visitReasonId);
						console.log('visitReasonId'+visitReasonId);
						 //$(this).parent().parent().parent().find('.reasonOfVisitAdded').append('<a style="padding-left:5px"><label><input class="checkedInput" type="checkbox" name="visitReason" value="'+reasonOfVisitCode[i]+'^'+visitReasonId+'" checked> '+reasonOfVisitVAl[i]+'</label></a>');
						 
						 $('#reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
					    	 		'  '+
					    	 		'<span class="text">'+reasonOfVisitVAl[i]+' </span>'+
					    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
					    	 		'</button></label>');
						 $('#reasonOfVisitAdded').append('&nbsp;&nbsp');
		 			   }
					 $('input[name=txt-snomed-ct-search_VR]').val('');
					
			 		 $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR]').attr('reasonOfVisitCode','');
		 		 }
		 		else
		 			 swal('Please Select atleast one reason');
 		}
 		else if($('input[name=generalComplaint]').val().trim() != ''){
            var generalComplaintVal = $('input[name=generalComplaint]').val();
            if(generalComplaintVal.trim()!='')
             {  
               var tmp = 0; 
               $('.reasonOfVisitAdded').find('label .text').each(function(index){
            	   
            	   //alert($(this).text().trim().toUpperCase());
            	   //alert(generalComplaintVal.trim().toUpperCase());
                 if($(this).text().trim().toUpperCase()===generalComplaintVal.trim().toUpperCase()) 
                 { tmp = 1; 
                   return false;  }
               });
              // alert("tempValue="+tmp);
               if(tmp==1)
               {
                 //swal("Already Added!!");
            	   alert(generalComplaintVal+",Already Added"); 
            	   $('input[name=generalComplaint]').val(''); 
                 return false;
               }
               else
               {
                 
                 $('#reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
			    	 		
			    	 		'<span class="text">'+generalComplaintVal+' </span>'+
			    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
			    	 		'</button></label>');
                 $('#reasonOfVisitAdded').append('&nbsp;&nbsp');
               }
              
              $('input[name=generalComplaint]').val(''); 
              /*$(this).parent().parent().find('input[name="chiefComplaintNoOfDays"]').val('');
              $(this).parent().parent().find('select[name="chiefComplaintSite"]').val('0');
              $(this).parent().parent().find('select[name="chiefComplaintDuration"] ').val('0');*/
             }
             else{
               swal('Please enter other general complaint to be added');
             }
     }
 		else
 			 //swal('Please Select atleast one reason');
 			alert('Please Select atleast one reason');
 	}
	
	
	//$('#generalComplaintId').on('focus', function(e) {
		
	function clearCheifComplaints()
	{
		//alert("generic complaints on focus");
		 $('input[name=txt-snomed-ct-search_VR]').val('');
		
	}	
	function clearGeneralComplaints()
	{
		 $('input[name=generalComplaint]').val('');
	}
	
	
	//$('#saveIdForChiefComplaints').click(function(e) {
	function saveChiefComplaints()
	{
		alert("ROV Save Called");
		
		var form = $('#commonTransactionLayoutFormId');
		validate_ENC_CC_ROV();
			$.ajax({
				   url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_CC_ROV.cnt?hmode=PATCLINICALDOC_ENC_CC_ROV_SAVE"),  
				    type : 'POST',
				    data : form.serialize(), 
				    async: false,
					success: function(data) {
						   	if(data=="true")
				    		{
				           // alert("Chief Complaints Data Saved successfully");
				    		saveFlag=1;
				    	    }
				    	    else
				    	    	{
				    	    	saveFlag=0;
				    	    	}
				      },
				      error: function(data)
				      {
				    	    //alert('request failed :');
				    	}
			});
			
	}	
	
	function getChiefComplaintsData()
	{
         alert("ROV Refresh Called");
		
		var form = $('#commonTransactionLayoutFormId');
		
			$.ajax({
				   url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_CC_ROV.cnt?hmode=PATCLINICALDOC_ENC_CC_ROV_SELECT"),  
				    type : 'POST',
				    data : form.serialize(), 
				    async: false,
					success: function(data) {
						   	if(data=="true")
				    		{
				           // alert("Chief Complaints Data Saved successfully");
				    		saveFlag=1;
				    	    }
				    	    else
				    	    	{
				    	    	saveFlag=0;
				    	    	}
				      },
				      error: function(data)
				      {
				    	    //alert('request failed :');
				    	}
			});
	}

	
	//$('#txt-snomed-ct-search_VR').on('keyup',function(event){
	function setSnomedChiefComplaints()
	{
	if(event.which == 13)
	{
		 //alert('hiii');
		 $('#reasonOfVisitAddId').click();
		}
	 //});
}	
	
	function setGeneralChiefComplaints()
	{
	if(event.which == 13)
	{
		 //alert('hiii');
		 $('#reasonOfVisitAddId').click();
		}
	 //});
}	