$(document).ready(function(){
	
var count=0;
      $('.chronicDiseaseAddRows').click(function(){
         if($(this).parent().parent().parent().find('input[name=txt-snomed-ct-search_VR3]').val().trim().indexOf(';')>='0')
   	   	 { 	 
    	   		count++;
    	   		var str1='';
		       //Change this value to snomed-ct code of chronic disease
		       var chronicDiseaseName=$(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR3"]').val();
		       var duration=$(this).parent().parent().parent().find('input[name="chronicDiseaseNoOfYears"]').val();
		       var remarks=$(this).parent().parent().parent().find('#chronicDiseaseRemarksId').val();
		       var chronicdiseasecode = $(this).parent().parent().find('input[name=txt-snomed-ct-search_VR3]').attr('chronicdiseasecode').split(';');
		       var hiddenField=chronicDiseaseName+'^'+chronicdiseasecode+'^'+duration+'^'+remarks;
		      // alert(chronicDiseaseName+"  "+duration+"  "+remarks+'  '+chronicdiseasecode);
		       console.log('dasdasd'+$(this).find('td').eq(0).children('input').val());
		       console.log(hiddenField.trim());
		       var tmp = 0; 
		       $('#chronicDiseaseListTable tbody').find('tr').each(function(index){
		         if(($(this).find('td').eq(0).children('input').val()).split('^')[1]==(hiddenField.trim()).split('^')[1]) 
		         { tmp = 1; 
		           return false;  }
		       });
		       if(tmp==1)
		       { 
		          swal("Already Added!!");
		          $(this).parent().parent().parent().find('input[name=txt-snomed-ct-search_VR3]').val(''); 
		          $(this).parent().parent().parent().find('input[name="chronicDiseaseNoOfYears"]').val('');   
		          $(this).parent().parent().parent().find('#chronicDiseaseRemarksId').val('');
		         return false;
		       } 
		       
		        if(chronicDiseaseName.trim()!=''/* && duration.trim()!='' && remarks.trim()!=''*/){
		          str1='<tr>'+
		          '<td><input type="checkbox" checked class="chronicDiseaseChkAll" name="chronicDiseaseChk" value="'+hiddenField+'"></td>'+
		          '<td>'+chronicDiseaseName+'</td>'+
		          '<td>'+duration+'</td>';
		          
		          if(remarks!=''){
		            str1+='<td><a class="chronicDiseaseInstructionsModalBtn" style="color: #109f1c" chronicDiseaseInstructions="'+remarks+'" onclick="$(\'#chronicDiseaseInstructionsModal .modal-body p\').text($(this).attr(\'chronicDiseaseInstructions\'));$(\'#chronicDiseaseInstructionsModal\').modal(\'show\');">'+remarks.substring(0, 4)+'..'+'</a></td>';
		          }
		          else{
		        	  str1+='<td></td>';
		          }
		
		          str1+='<td><button class="btn btn-xs btn-danger chronicDiseaseRemoveRow" type="button" onclick="$(this).parent().parent().remove();" id="removeBtnId2" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button></td> </tr>';
		            $(this).parent().parent().parent().parent().find('.table').children('tbody').append(str1); 
		            $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR3"]').val('');
		            $(this).parent().parent().parent().find('input[name="chronicDiseaseNoOfYears"]').val('');
		            $(this).parent().parent().parent().find('#chronicDiseaseRemarksId').val('');
		        }
		        else{
		          swal("Please fill value for chronic disease name.");
		        }
   	   }
       else{
    	   swal('Please Select atleast one reason');
       }
      });
      
      $('#allergiesModal .close').click(function(){
          $('#allergicOrNotId').prop('checked', true);
        });

      $('#allergicOrNotId').change(function() {
          if($(this).is(":checked")) {
              $('#allergiesModal').modal('hide');
          }
          else{
            $('#allergiesModal').modal('show');
          }
                  
      });

        var count1=0;
        $('.allergiesDtlAddRows').click(function(){
            
            var allergynamecode = $('input[name=txt-snomed-ct-search_VR4]').attr('allergynamecode').split(';');
            var allergysitecode = $('input[name=txt-snomed-ct-search_VR5]').attr('allergysitecode').split(';');
            var allergysymptomscode = $('input[name=txt-snomed-ct-search_VR6]').attr('allergysymptomscode').split(';');
            
            var chkBoxId ={
            		"strAllergyNameCode" : allergynamecode[0] ,
            		"strAllergyName" : $("#txt-snomed-ct-search_VR4").val() ,
            		"strSensivityCode" : $("#allergiesSensitivityCodeId").val() ,
            		"strSensivityName" : $("[name='allergiesSensitivityCode'] option:selected").text() ,
            		"stDurationTime" : $("#allergiesDurationId").val() ,
            		"strAllergysiteName" : $("#txt-snomed-ct-search_VR5").val() ,
            		"strAllergysiteCode" : allergysitecode[0] ,
            		"strAllergySytmptomsName" : $("#txt-snomed-ct-search_VR6").val() ,
            		"strAllergySytmptomsCode" : allergysymptomscode[0] ,
            		"strAllergyRemarks" : $("#allergiesDtlRemarksId").val()
            };
            console.log(chkBoxId);
            var hiddenField =JSON.stringify(chkBoxId); //chkBoxId.toString(); //JSON.parse(chkBoxId); //JSON.stringify(chkBoxId); 
            console.log(JSON.parse(hiddenField));
            var parsedata=JSON.parse(hiddenField);
            
            console.log('ParseData');
            console.log(JSON.stringify(parsedata));
            //console.log('Test'+JSON.parse(chkBoxId));
            //var hiddenField="1" //Change this value to snomed-ct code of chronic disease
            // hiddenField= JSON.parse(chkBoxId); ; //allergynamecode+'^'+$("#txt-snomed-ct-search_VR4").val()+'^'+$("[name='allergiesSensitivityCode'] option:selected").text()+'^'+$("#allergiesSensitivityCodeId").val()+'^'+$("#allergiesDurationId").val()+'^'+$("#txt-snomed-ct-search_VR5").val()+'^'+$("#txt-snomed-ct-search_VR5").text()+'^'+$("#txt-snomed-ct-search_VR6").val()+'^'+$("#txt-snomed-ct-search_VR6").text()+'^'+$("#allergiesDtlRemarksId").val()  ;	
            console.log(allergynamecode+'allergy::::: '+hiddenField);
            var allergyName=$(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR4"]').val();
            var sensitivity=$(this).parent().parent().find('select[name="allergiesSensitivityCode"] option:selected').text();
            var duration=$(this).parent().parent().parent().find('input[name="allergiesDuration"]').val();
            var allergySite=$(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR5"]').val();
            var allergySymptoms=$(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR6"]').val();
            var remarks=$(this).parent().parent().parent().find('#allergiesDtlRemarksId').val();
            var sensitivityCode=$(this).parent().parent().find('select[name="allergiesSensitivityCode"] option:selected').val();
            //alert(allergyName+"  "+duration+"  "+remarks+" "+sensitivity+" "+allergySite+" "+allergySymptoms+" "+sensitivityCode);
            var allergydata1='';
            var tmp = 0; 
            $('#allergiesDtlListTable tbody').find('tr').each(function(index){
              if($(this).find('td').eq(0).children('input').val()==hiddenField.trim()) 
              { tmp = 1; 
                return false;  }
            });
            if(tmp==1)
            { 
               swal("Already Added!!");
                $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR4"]').val('');
                $(this).parent().parent().find('select[name="allergiesSensitivityCode"]').val('-1');
                $(this).parent().parent().parent().find('input[name="allergiesDuration"]').val('');
                $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR5"]').val('');
                $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR6"]').val('');
                $(this).parent().parent().parent().find('#allergiesDtlRemarksId').val('');
              return false;
            } 
          if(sensitivityCode == '-1'){
            sensitivity='';
          }
          var count=0;
          if(allergyName.trim()!=''/* && duration.trim()!='' && allergySite.trim()!='' && allergySymptoms.trim()!='' && remarks.trim()!='' && sensitivityCode!= '-1'*/){
        	  count1++;
        	  allergydata1='<tr>'+ 
                '<td><input type="checkbox" class="allergiesDtlChkAll" id="allergyDtlChkId'+count1+'" checked name="allergiesDtlChk" value=""></td>'+
                '<td>'+allergyName+'</td>'+
                '<td>'+sensitivity+'</td>'+
                '<td>'+duration+'</td>'+
                '<td>'+allergySymptoms+'</td>'+
                '<td>'+allergySite+'</td>';
        	  
                if(remarks!=''){
                	allergydata1+='<td><a class="allergiesDtlInstructionsModalBtn" style="color: #109f1c" allergyInstructions="'+remarks+'" onclick="$(\'#allergiesDtlInstructionsModal .modal-body p\').text($(this).attr(\'allergyInstructions\'));$(\'#allergiesDtlInstructionsModal\').modal(\'show\');">'+remarks.substring(0, 4)+'..'+'</a></td>';
                }
                else{
                	allergydata1+='<td></td>';
                }

                allergydata1+='<td><button class="btn btn-xs btn-danger allergiesDtlRemoveRow" type="button" onclick="$(this).parent().parent().remove();" id="removeBtnId2" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button></td>'+
                '</tr>';
               // $(this).parent().parent().parent().parent().find('#drugAdviceListTable').children('tbody').append(
              $(this).parent().parent().parent().find('#allergiesDtlListTable').children('tbody').append(allergydata1);
                $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR4"]').val('');
                $(this).parent().parent().find('select[name="allergiesSensitivityCode"]').val('-1');
                $(this).parent().parent().parent().find('input[name="allergiesDuration"]').val('');
                $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR5"]').val('');
                $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR6"]').val('');
                $(this).parent().parent().parent().find('#allergiesDtlRemarksId').val('');
                
                var temp='#allergyDtlChkId'+count1;
                $(temp).val(hiddenField);
          }
          else{
            swal("Please fill value for allergy name.");
          }
        });

         $('.allergyNameCleanBtn').click(function(){
           $(this).parent().parent().find('#txt-snomed-ct-search_VR4').val('');
           $(this).parent().parent().find('#txt-snomed-ct-search_VR4').attr('allergynamecode','');
        });

        $('.allergySiteCleanBtn').click(function(){
           $(this).parent().parent().find('#txt-snomed-ct-search_VR5').val('');
           $(this).parent().parent().find('#txt-snomed-ct-search_VR5').attr('allergysitecode','');
        });

        $('.allergySymptomsCleanBtn').click(function(){
           $(this).parent().parent().find('#txt-snomed-ct-search_VR6').val('');
           $(this).parent().parent().find('#txt-snomed-ct-search_VR6').attr('allergysymptomscode','');
        });

        $('.allergiesDtlInstructionsModalBtn').click(function(){ 
          $('#allergiesDtlInstructionsModal .modal-body p').text($(this).attr('allergyInstructions'));
          $('#allergiesDtlInstructionsModal').modal('show');
        });

        $('.chronicDiseaseInstructionsModalBtn').click(function(){ 
          $('#chronicDiseaseInstructionsModal .modal-body p').text($(this).attr('chronicDiseaseInstructions'));
          $('#chronicDiseaseInstructionsModal').modal('show');
        });
 });