	$(document).ready(function(){
		/*$('#txt-snomed-ct-search_VR').focus();*/
	});

	function ehrVisitReasonOnLoad()
    { 
    	newfunc();
    	load_UNIVD('1','','450970008');  
    }

    function newfunc()
    { 
   		if(document.getElementsByName("ehrVisitReason")[0].value!="")
   			{  
   			document.getElementById("txt-snomed-ct-search_VR").value=document.getElementsByName("ehrVisitReason")[0].value;
   			VRSetDataExist();
   			} 
    }
    
	  function load_UNIVD(elmtId,semantictag,refsetId)
	   {
	    	//newfunc(); 
	    if(elmtId=="VR")
	    	{
	    	var id="ehrVisitReason"; 	    	
	    	}

	     //setTarget(id); 
	    var semantictag_IN;
	    var acceptability_IN;
	    var state_IN ;
	    var returnlimit_IN;
	    var retterm_value ={}; 
	    var callbck_index =function(ret_OUT){setValueVR(ret_OUT);};

	    console.log('callbck_index'+callbck_index);
	    if(elmtId==null || elmtId==undefined)
	    	{
	    	elmtId="VR"; semantictag="DISORDER";
	    	}
	    //elmtId="2"; 
	    console.log('semantictag   '+semantictag);
	    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);
	    $("#conceptdiv_1").hide();
	    $("#norecorddiv_1").hide();
	    }  

		function load_UNIVD2(elmtId,semantictag,refsetId)
	    {
	    	//newfunc(); 
	    if(elmtId=="VR2")
	    	{
	    	var id="diagnosisReason";   	
	    	}
	     //setTarget(id); 
	    var semantictag_IN;
	    var acceptability_IN;
	    var state_IN ;
	    var returnlimit_IN;
	    var retterm_value ={};

	    var callbck_index =function(ret_OUT){setValueVR2(ret_OUT);};

	    if(elmtId==null || elmtId==undefined)
	    {
	      elmtId="VR2"; semantictag="DISORDER"; 
	    }
	    //elmtId="2"; 
	    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index); 
	    $("#conceptdiv_2").hide();
	    $("#norecorddiv_1").hide(); 
	    }

		
		function load_UNIVD3(elmtId,semantictag,refsetId)
	    {
	    	//newfunc(); 
	    if(elmtId=="VR2")
	    	{
	    	var id="ChronicDiseaseId";   	
	    	}
	     //setTarget(id); 
	    var semantictag_IN;
	    var acceptability_IN;
	    var state_IN ;
	    var returnlimit_IN;
	    var retterm_value ={};

	    var callbck_index =function(ret_OUT){setValueVR3(ret_OUT);};

	    if(elmtId==null || elmtId==undefined)
	    {
	      elmtId="VR3"; semantictag="DISORDER"; 
	    }
	    //elmtId="2"; 
	    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index); 
	    $("#conceptdiv_2").hide();
	    $("#norecorddiv_1").hide(); 
	    
	    }

		
		  function load_UNIVD4(elmtId,semantictag,refsetId)
		   {
		    	//newfunc();
			  
			  console.log('load_UNIVD4');
		    if(elmtId=="VR4")
		    	{
		    	var id="ehrAllergies"; 	    	
		    	}
		    console.log('5');
		     //setTarget(id); 
		    var semantictag_IN;
		    var acceptability_IN;
		    var state_IN ;
		    var returnlimit_IN;
		    var retterm_value ={}; 
		    console.log('7');
		    var callbck_index =function(ret_OUT){setValueVR4(ret_OUT);};
		    
		    console.log('callbck_index'+callbck_index);

		    if(elmtId==null || elmtId==undefined)
		    	{
		    	elmtId="VR4"; semantictag="DISORDER";
		    	}
		    console.log('10');
		    //elmtId="2"; 
		    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);
		    console.log('11');
		    $("#conceptdiv_4").hide();
		    $("#norecorddiv_1").hide();
		    }  

		  function load_UNIVD5(elmtId,semantictag,refsetId)
		   {
		    	//newfunc();
			  
			  console.log('load_UNIVD5');
		    if(elmtId=="VR5")
		    	{
		    	var id="ehrAllergies"; 	    	
		    	}
		    console.log('5');
		     //setTarget(id); 
		    var semantictag_IN;
		    var acceptability_IN;
		    var state_IN ;
		    var returnlimit_IN;
		    var retterm_value ={}; 
		    console.log('7');
		    var callbck_index =function(ret_OUT){setValueVR5(ret_OUT);};
		    
		    console.log('callbck_index'+callbck_index);

		    if(elmtId==null || elmtId==undefined)
		    	{
		    	elmtId="VR5"; semantictag="body structure";
		    	}
		    console.log('10');
		    //elmtId="2"; 
		    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);
		    console.log('11');
		    $("#conceptdiv_5").hide();
		    $("#norecorddiv_1").hide();
		    }  
		  
		  function load_UNIVD6(elmtId,semantictag,refsetId)
		   {
		    	//newfunc();
			  
			  console.log('load_UNIVD5');
		    if(elmtId=="VR6")
		    	{
		    	var id="ehrAllergies"; 	    	
		    	}
		    console.log('5');
		     //setTarget(id); 
		    var semantictag_IN;
		    var acceptability_IN;
		    var state_IN ;
		    var returnlimit_IN;
		    var retterm_value ={}; 
		    console.log('7');
		    var callbck_index =function(ret_OUT){setValueVR6(ret_OUT);};
		    
		    console.log('callbck_index'+callbck_index);

		    if(elmtId==null || elmtId==undefined)
		    	{
		    	elmtId="VR6"; semantictag="finding";
		    	}
		    console.log('10');
		    //elmtId="2"; 
		    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);
		    console.log('11');
		    $("#conceptdiv_6").hide();
		    $("#norecorddiv_1").hide();
		    }  
		  
		  
		  function load_UNIVD7(elmtId,semantictag,refsetId)
		   {
		    	//newfunc();
			  
			  console.log('load_UNIVD5');
		    if(elmtId=="VR7")
		    	{
		    	var id="ehrAllergies"; 	    	
		    	}
		    console.log('5');
		     //setTarget(id); 
		    var semantictag_IN;
		    var acceptability_IN;
		    var state_IN ;
		    var returnlimit_IN;
		    var retterm_value ={}; 
		    console.log('7');
		    var callbck_index =function(ret_OUT){setValueVR7(ret_OUT);};
		    
		    console.log('callbck_index'+callbck_index);

		    if(elmtId==null || elmtId==undefined)
		    	{
		    	elmtId="VR7"; semantictag="procedure";
		    	}
		    console.log('10');
		    //elmtId="2"; 
		    selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);
		    console.log('11');
		    $("#conceptdiv_7").hide();
		    $("#norecorddiv_1").hide();
		    }  
	    function setfreeTextVR()   //for free text
	    {
	    	//document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	    	
	    	var text_data_VR= document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	    	if(text_data_VR.length > 0 )
	    	{
	    		var lastchar_VR= text_data_VR[text_data_VR.length - 1]; 
	    		if(lastchar_VR.match(";"))
	    		{ 
	    			document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	    		}
	    		else
	    		{ 
	    			document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value +";";
	    		}
	    	}

	    }

	    function setValueVR(selectedSNOMEDTerm)
	    { 
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="ehrVisitReason";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";

	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var reasonOfVisitCode = $('#txt-snomed-ct-search_VR').attr('reasonOfVisitCode');
	    	if(reasonOfVisitCode=='')
	 	    	$('#txt-snomed-ct-search_VR').attr('reasonOfVisitCode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR').attr('reasonOfVisitCode',reasonOfVisitCode+';'+str1);
	      }
	 	}
	    function setValueVR2(selectedSNOMEDTerm)
	    { 
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="diagnosisReason";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";

	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var diagnosisCode = $('#txt-snomed-ct-search_VR2').attr('diagnosisCode');
	    	if(diagnosisCode=='')
	 	    	$('#txt-snomed-ct-search_VR2').attr('diagnosisCode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR2').attr('diagnosisCode',diagnosisCode+';'+str1);
	      }
	   } 
	    
	    
	    function setValueVR3(selectedSNOMEDTerm)
	    { 
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="ChronicDiseaseId";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";

	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var diagnosisCode = $('#txt-snomed-ct-search_VR3').attr('diagnosisCode');
	    	if(diagnosisCode=='')
	 	    	$('#txt-snomed-ct-search_VR3').attr('chronicdiseasecode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR3').attr('chronicdiseasecode',str1);
	      }
	   } 
	    
	    function setTarget2(id)
	    {
	     if(id=="diagnosisReason")
	     {
	   		 document.getElementsByName("targetId2")[0].value="diagnosisReason";}
	     }  
	    function setTarget(id)
	    {
	     if(id=="ehrVisitReason")
	     {
	    	document.getElementsByName("targetId")[0].value="ehrVisitReason";}
	     }
	    
	    function setTarget4(id)
	    {
	     if(id=="ehrAllergies")
	     {
	    	document.getElementsByName("targetId4")[0].value="ehrAllergies";}
	     }
	    
	    function setValueVR4(selectedSNOMEDTerm)
	    { 
	    	console.log('8');
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="ehrAllergies";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";
	    console.log('9');
	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var reasonOfVisitCode = $('#txt-snomed-ct-search_VR4').attr('allergynamecode');
	    	if(reasonOfVisitCode=='')
	 	    	$('#txt-snomed-ct-search_VR4').attr('allergyNameCode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR4').attr('allergyNameCode',reasonOfVisitCode+';'+str1);
	      }
	 	}
	    
	    
	    function setValueVR5(selectedSNOMEDTerm)
	    { 
	    	console.log('8');
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="ehrAllergies";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";
	    console.log('9');
	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var reasonOfVisitCode = $('#txt-snomed-ct-search_VR5').attr('allergysitecode');
	    	if(reasonOfVisitCode=='')
	 	    	$('#txt-snomed-ct-search_VR5').attr('allergysitecode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR5').attr('allergysitecode',reasonOfVisitCode+';'+str1);
	      }
	 	}
	    
	    function setValueVR6(selectedSNOMEDTerm)
	    { 
	    	console.log('8');
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="ehrAllergies";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";
	    console.log('9');
	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var reasonOfVisitCode = $('#txt-snomed-ct-search_VR6').attr('allergysymptomscode');
	    	if(reasonOfVisitCode=='')
	 	    	$('#txt-snomed-ct-search_VR6').attr('allergysymptomscode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR6').attr('allergysymptomscode',reasonOfVisitCode+';'+str1);
	      }
	 	}
	    
	    
	    
	    function setValueVR7(selectedSNOMEDTerm)
	    { 
	    	console.log('8');
	    //var target=document.getElementsByName("targetId")[0].value;
	    var target="ehrAllergies";
	    	
	    var targetPrefferedTerm="";
	    var targetConceptId="";
	    console.log('9');
	    if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	    { 	
	    	/* var arr=selectedSNOMEDTerm.split("@@@");
	    	var str=arr[0];
	    	var str1=arr[1];
	    	 */
	    	 var str=selectedSNOMEDTerm.term;
	    	var str1=selectedSNOMEDTerm.conceptId; 
	    	var reasonOfVisitCode = $('#txt-snomed-ct-search_VR7').attr('clinicalprocedurecode');
	    	if(reasonOfVisitCode=='')
	 	    	$('#txt-snomed-ct-search_VR7').attr('clinicalprocedurecode',str1);
	    	else 
	 	    	$('#txt-snomed-ct-search_VR7').attr('clinicalprocedurecode',reasonOfVisitCode+';'+str1);
	      }
	 	}
	    
// ~~~~~ Util Functions for Prescription.jsp	    
	    
		function patientSumBtnToggleFun(){
			$('.patSummaryTile').toggle();
			$('#prescriptionTileContainer').toggleClass('col-md-9 col-md-12');
			$('#prescriptionTileContainer').toggleClass('col-sm-9 col-sm-12'); 
			$('.prescriptionTileRowFirstCol').toggleClass('col-md-11 col-md-10');
		            
		} 

	    function prescTabSearch(e){ 
	      var txt = e.value;
	      txt = txt.toUpperCase(); 
	      $('.nav-tabs li:not(:contains('+txt+'))').hide('slow',function(){
	        $('.nav-tabs li:visible').eq(0).children('a').click();
	      });
	      $('.nav-tabs li:contains('+txt+')').show('slow',function(){
	        $('.nav-tabs li:visible').eq(0).children('a').click();
	      });
	    }
	    

		function checkAllBookmarkItem(e){
		 	 if($(e).is(':checked')) 
			 {
		 	    $(e).parent().parent().find('input[name=bookmarkSubTest]').prop('checked', true);
		 	  } else {
		 	    $(e).parent().parent().find('input[name=bookmarkSubTest]').prop('checked', false);
		 	  }
			}
	    

        function prescImgFixedTriggerFun(imgSrc){   
				console.log('imgSrc:::>>> '+imgSrc);
				imgSrc = localStorage.getItem(imgSrc);
				if($('#prescImgFixed').attr('src')=='')
				{
					$('#prescriptionTileContainer').toggleClass('col-md-9 col-md-12');
					$('#prescriptionTileContainer').toggleClass('col-sm-9 col-sm-12'); 
					$('#prescImgFixed').toggle();				
					$('#prescImgControlBtnGrpFixed').toggle();	
					$('.prescriptionTileRowsCol').toggleClass('col-sm-2 col-sm-12');
					$('.prescriptionTileRowsCol').toggleClass('col-md-1 col-md-12'); 
				}
				$('#prescImgFixed').attr('src','data:image/jpeg;base64,'+imgSrc); 
				/* $('#prescImgCloseBtnFixed').toggle();
				$('#prescImgRotateLeftBtnFixed').toggle();
				$('#prescImgRotateRightBtnFixed').toggle();
				$('#prescImgZoomOutBtnFixed').toggle();
				$('#prescImgZoomInBtnFixed').toggle();
				$('#prescImgOriginalBtnFixed').toggle();  */
		      } 
        
        
        function prescImgFixedTriggerFun1(imgSrc){   
			console.log('imgSrc:::>>> '+imgSrc);
			//imgSrc = localStorage.getItem(imgSrc);
			if($('#prescImgFixed').attr('src')=='')
			{
				$('#prescriptionTileContainer').toggleClass('col-md-9 col-md-12');
				$('#prescriptionTileContainer').toggleClass('col-sm-9 col-sm-12'); 
				$('#prescImgFixed').toggle();				
				$('#prescImgControlBtnGrpFixed').toggle();	
				$('.prescriptionTileRowsCol').toggleClass('col-sm-2 col-sm-12');
				$('.prescriptionTileRowsCol').toggleClass('col-md-1 col-md-12'); 
			}
			$('#prescImgFixed').attr('src','data:image/jpeg;base64,'+imgSrc); 
			/* $('#prescImgCloseBtnFixed').toggle();
			$('#prescImgRotateLeftBtnFixed').toggle();
			$('#prescImgRotateRightBtnFixed').toggle();
			$('#prescImgZoomOutBtnFixed').toggle();
			$('#prescImgZoomInBtnFixed').toggle();
			$('#prescImgOriginalBtnFixed').toggle();  */
	      } 

		$(document).ready(function(){ 
			$('#patSummaryNameGenAgeCat').text($('#patNamePrescriptionPanel').text()+' ('+$('#patGenAgeCatPrescriptionPanel').text()+')');
			$('#patSummaryCRN').text($('#patCrNoPrescriptionPanel').text()); 
			
			$('#patientSumBtn').click(function(){
				
				$('#patSummaryNameGenAgeCat').text($('#patNamePrescriptionPanel').text()+' ('+$('#patGenAgeCatPrescriptionPanel').text()+')');
				$('#patSummaryCRN').text($('#patCrNoPrescriptionPanel').text());
				
				$('#patVitalSingsDatePrescriptionPanel').text($('#otherInfoIdValue').text()); 
			
				var test='';
				$('input[name=reasonOfVisit]').each(function()
						{
							console.log($(this).parent().find('span').find("text text1").text());   //+'^'+$(this).parent().text();
							var str=($(this).parent().find('span').text());
							test = test+'<li>'+str.substring(0, str.length-1)+'</li>';
							//ResonOfVisitJsonArray[j]=JSON.parse($(this).parent().find('i').text());
							//console.log('DDDDDDDDD  '+$(this).parent().find('i').text());
							//console.log('DDDDDDDDD '+document.getElementsByName("visitReasonHidden")[j].value);
							//j++; 
						});
				
				var drug='';
				$('input[name=drugsAdvices]').each(function()
						{
							//console.log('name=drugsAdvices::::>>>'+$(this).val());
							if($(this).val().trim().split('&&')[1]!=100) 
							{
								//DrugCodeCat[j]=$(this).val();
								console.log("DrugJson::::::: "+$(this).val());
								drug = drug+'<li>'+($(this).val()).split("&&")[0]+'</li>';
								//DrugJsonArray[j]=JSON.parse($(this).parent().find('i').text());
								//j++;
							}
							
						});
			
				
				$('#patMedicineDatePrescriptionPanel').html(drug); 
				$('#patRecentLabTestPrescriptionPanel').html(test);
				 
				
				
			});
				
				
			
			$('#prescImgCloseBtnFixed').click(function(){ 
				$('#prescImgFixed').toggle();
				$('#prescImgFixed').attr('src','');
				$('#prescImgControlBtnGrpFixed').toggle();
				/* $(this).hide();
				$('#prescImgRotateLeftBtnFixed').toggle();
				$('#prescImgRotateRightBtnFixed').toggle();
				$('#prescImgZoomOutBtnFixed').toggle();
				$('#prescImgZoomInBtnFixed').toggle();
				$('#prescImgOriginalBtnFixed').toggle();  */ 
				$('#prescriptionTileContainer').toggleClass('col-md-9 col-md-12');
				$('#prescriptionTileContainer').toggleClass('col-sm-9 col-sm-12'); 
				$('.prescriptionTileRowsCol').toggleClass('col-sm-2 col-sm-12');
				$('.prescriptionTileRowsCol').toggleClass('col-md-1 col-md-12'); 
				});
			
			$('#prescImgRotateLeftBtnFixed').click(function(){ 
				$('#prescImgFixed').css('transform','rotate('+(getRotationDegrees($('#prescImgFixed'))-90)+'deg)');   
				console.log($('#prescImgFixed').css('transform'));
				});
			$('#prescImgRotateRightBtnFixed').click(function(){
				$('#prescImgFixed').css('transform','rotate('+(getRotationDegrees($('#prescImgFixed'))+90)+'deg)'); 
				console.log($('#prescImgFixed').css('transform'));
			});
			$('#prescImgOriginalBtnFixed').click(function(){
				$('#prescImgFixed').css('transform','rotate(0deg) scale(1)'); 
				console.log($('#prescImgFixed').css('transform'));
			});
			$('#prescImgZoomInBtnFixed').click(function(){
				var num = Number(getScaleVal($('#prescImgFixed')))+0.1; 
				$('#prescImgFixed').css('transform','scale('+num+')');  
				console.log($('#prescImgFixed').css('transform'));
			});
			$('#prescImgZoomOutBtnFixed').click(function(){
				$('#prescImgFixed').css('transform','scale('+(getScaleVal($('#prescImgFixed'))-0.1)+')');  
				console.log($('#prescImgFixed').css('transform'));
			});
			 $( "#prescImgFixed" ).draggable();
			});
		
		function getRotationDegrees(obj) {
		    var matrix = obj.css("-webkit-transform") ||
		    obj.css("-moz-transform")    ||
		    obj.css("-ms-transform")     ||
		    obj.css("-o-transform")      ||
		    obj.css("transform");
		    if(matrix !== 'none') {
		        var values = matrix.split('(')[1].split(')')[0].split(',');
		        var a = values[0];
		        var b = values[1];
		        var angle = Math.round(Math.atan2(b, a) * (180/Math.PI));
		    } else { var angle = 0; }
		    console.log('angle:::>>>>'+angle);
		    return (angle < 0) ? angle + 360 : angle;
		}
		function getScaleVal(obj) {
		    var matrix = obj.css("-webkit-transform") ||
		    obj.css("-moz-transform")    ||
		    obj.css("-ms-transform")     ||
		    obj.css("-o-transform")      ||
		    obj.css("transform");
		    if(matrix !== 'none') {
		        var values = matrix.split('(')[1].split(')')[0].split(',');
		        var a = values[0]; 
		        var angle = a;
		    } else { var angle = 1; }
		    console.log('angle:::>>>>'+angle);
		    return angle;
		}
		
		 $(document).ready(function(){
			 tippy('.nextPatientPresc', {
	             content: ($('#nextPatNamePrescriptionPanel').text().trim().length>0 ? $('#nextPatNamePrescriptionPanel').text() : 'No More Patient'), /*document.querySelector('#nextPatientPrescBtnToolTip'),*/
	             delay: 50,
	             arrow: true,
	             arrowType: 'round',
	             size: 'small',
	             duration: 500,
	             animation: 'scale'
	         });

	       tippy('.prevPatientPresc', {
	             content: ($('#prevPatNamePrescriptionPanel').text().trim().length>0 ? $('#prevPatNamePrescriptionPanel').text() : 'First Patient'), // document.querySelector('#prevPatientPrescBtnToolTip')
	             delay: 50,
	             arrow: true,
	             arrowType: 'round',
	             size: 'small',
	             duration: 500,
	             animation: 'scale'
	         });

	       tippy('.nextPatientName', {
	             content: 'Next Patient Name',
	             delay: 50,
	             arrow: true,
	             arrowType: 'round',
	             size: 'small',
	             duration: 500,
	             animation: 'scale'
	         });
	       
	       tippy('.prevPatientName', {
	             content: 'Previous Patient Name',
	             delay: 50,
	             arrow: true,
	             arrowType: 'round',
	             size: 'small',
	             duration: 500,
	             animation: 'scale'
	         });

	       tippy('.progressNoteMacroBtn', {
	             content: 'Macro: Predefined Text',
	             delay: 50,
	             arrow: true,
	             arrowType: 'round',
	             size: 'large',
	             duration: 500,
	             animation: 'scale'
	         });

	       tippy('#patSideListBtn', {
	             content: 'Patient List',
	             delay: 50,
	             arrow: true,
	             arrowType: 'round',
	             size: 'large',
	             duration: 500,
	             animation: 'scale'
	         });
	       
	       tippy('.patSearchRightPanel', {
	           content: 'Patient Search',
	           delay: 50,
	           arrow: true,
	           arrowType: 'round',
	           size: 'large',
	           duration: 500,
	           animation: 'scale'
	       });
	       
	       tippy('#patientSumBtn', {
	           content: 'Patient Summary',
	           delay: 50,
	           arrow: true,
	           arrowType: 'round',
	           size: 'large',
	           duration: 500,
	           animation: 'scale'
	       });
		 });
       
       
       $(window).keypress(function(event) {
  	     if (!(event.which == 115 && event.ctrlKey) && !(event.which == 19)) return true;
  	     $('.prescPrintBtn').click();
  	     event.preventDefault();
  	     return false;
  	     /* $('.investigationLabReport').click(); 
	    	 $('.patSearchRightPanel').click(); */
  	     }); 
       
    hotkeys('ctrl+f,ctrl+i,ctrl+m,ctrl+p,esc',function(event,handler){  //,ctrl+shift+n,ctrl+c
			switch(handler.key){
				case "ctrl+f": $('.patSearchRightPanel').click(); event.preventDefault(); break;
				case "ctrl+i": $('.investigationLabReport').click(); event.preventDefault(); break;
				case "ctrl+m": $('#patSideListBtn').click(); event.preventDefault(); break;
				case "ctrl+p": $('ul.prescViewVisitLst li.prescImgListItems').first().find('a').click(); event.preventDefault(); break;
				/*case "ctrl+shift+n": $('.nextPatientPresc').click(); event.preventDefault(); break;*/
				/*case "ctrl+c": $('.rightPanelClose').click(); event.preventDefault(); break;*/
				case "esc": $('.modal').modal('hide'); event.preventDefault(); break;
			}
			
        });
       
    $(document).ready(function(){
		$('.printPrescSAveBtn').click(function(){
			swal({
				  title: "Are you sure to Save?",
				  text: "Press ok to save!",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
			.then(function(willDelete) {
			  if (willDelete) {
				  $('#printPrescriptionModal').modal('hide');
				  SavePrintData(localStorage.getItem('myJSON'));
			  } else {
			     return false;
			  }
			});
		}); 
		$("#printPrescriptionModal").on('hide.bs.modal', function () {
			$('#printPrescFrameId').remove();
		 });
	
		$("#patreferal").on('hide.bs.modal', function () {
			$('#patreferalFrameId').remove();
		 }); 
		 
	});
    
    $(document).ready(function(){
    	$("#followUpDocumentUpload").dropzone({ 
				url: "#",
				acceptedFiles:"image/*",
				clickable:["#followUpDocumentUpload","#followUpDocumentUpload div"],
				ignoreHiddenFiles:false,
				autoProcessQueue:false,   // use myDropzone.processQueue() to trigger auto processing
				addRemoveLinks:true,
				dictCancelUpload:"Cancel upload",
				dictCancelUploadConfirmation:"Are you sure to cancel",
				dictRemoveFile:"Remove selected file",
				previewsContainer: null,
				dictDefaultMessage: "Drag documents to be uploaded",
				dictFallbackMessage: "Your browser don't support",
				dictResponseError: "Error in upload",
				dictRemoveFileConfirmation: "Are you sure to remove"
				 });
    	});
       
       $(document).ready(function(){ 
           var viewer; 
   		var crNo = $('#patCrNoPrescriptionPanel').text(); //  331011800025054   331011800015989   331011800025470 
   		var hospCode = $('#patHospitalCodePrescriptionPanel').text(); // '33101'
   		var patEpisodeCode = $('#patEpisodeCodePrescriptionPanel').text(); // 10211003   $('#patEpisodeCodePrescriptionPanel').text();
   		var seatId = $('#patSeatIdPrescriptionPanel').text();  // 10001   $('#patSeatIdPrescriptionPanel').text();
     	    var tree = [];
        	var nodes = [];

        function getImage(episodeCode,count,visitNo,visitDate){
        		console.log('episodeCode::::::>>>>>>>'+episodeCode);
           	if(episodeCode==patEpisodeCode)
               { 
  		    	$('#prescViewVisiBtnId').removeAttr('disabled'); 
        		 $.ajax({
               	 url:'/HISDRDESK/services/restful/UserService/retriveImageData?DeptValue=hello^'+crNo+'^'+visitNo+'^'+episodeCode+'^'+hospCode+'',   
               	 type:'GET', 
               	 async: true,
               	 beforesend: $('.prescViewVisitLst').append('<li role="presentation" id="prescViewVisitLstMsgId"><a><i class="fa fa-spinner fa-sping"></i> Loading</a></li>'),
               	 success:function(result2)
                	{ 
                 	   if(result2.status==1 && result2.ImageData!=null)
                 	   {
                     	   if(result2.ImageData.length>0)
                     	   { 
                     		  $('#prescViewVisiBtnId').removeAttr('disabled');
                        	    $('#prescViewVisitLstMsgId').remove();
                         	    console.log('success retriveImageData::::>>>'+result2.ImageData.length+':::episode:::>>'+episodeCode+'::::count:::::>>>>>'+count); 
                         	    $('.prescViewVisitLst').append('<li class="divider"></li>');
                         	    //$('.prescViewVisitLst').append('<li class="dropdown-header" style="color: #0e0e35;font-weight: bold;background-color: #e4e4e4;">VISIT '+visitNo+'</li>'); 
                         	    for(var j=0;j<result2.ImageData.length;j++)
                             	{
                         	    	localStorage.removeItem('prescImg'+count.toString()+(j+1));
                         	    	localStorage.setItem('prescImg'+count.toString()+(j+1),result2.ImageData[j].IMG_DOCUMENT); 
                                 	$('.prescViewVisitLst').append('<li role="presentation" class="prescImgListItems" style="font-size: 12px;"><a style="color: #6060ed;" role="menuitem" tabindex="-1" href="javascript:;" onclick="prescImgFixedTriggerFun(\'prescImg'+count.toString()+(j+1)+'\')">'+result2.ImageData[j].VISIT_NO+'</a></li>');  //visitDate.split(" ")[0]
       /*                  			$('#patPrescViewModal').find('.pictures').append('<li><img  alt="Visit '+(j+1)+'" data-original="data:image/jpeg;base64,'+result2.ImageData[j].IMG_DOCUMENT+'" src="data:image/jpeg;base64,'+result2.ImageData[j].IMG_DOCUMENT+'" ></li>');
                        	        						nodes[j]={
                                                                   text: "visit "+(j+1),
                                                                   icon: "glyphicon glyphicon-calendar",
                                                                   selectedIcon: "glyphicon glyphicon-calendar",
                                                                   color: "#727272",
                                                                   backColor: "#FFFFFF",
                                                                   href: "#node-"+(j+1),
                                                                   selectable: true,
                                                                   state: {
                                                                     checked: false, 
                                                                   },
                                                                   id: count.toString()+j.toString(),
                                                                   tags: ['available']  
                        	                 					 }; */
                                 	}
                                 	  
                                  $('#treeMsgP').remove();  
                     	   }
                     	   else
                         {
                     		 $('#prescViewVisitLstMsgId a').text('Not Uploaded');
                          }
                 	   }
                 	   else  
                     	 {
   	              		 $('#prescViewVisitLstMsgId a').text('Not Uploaded');
   	             		 $('#treeMsgP').text('Not Uploaded');	             		 
                     	 }
                         
                	}, 
                	complete: function(){
                		 
   /*                  	for(var j=0;j<count;j++)
                       {
                    		tree[j] = {
                                   text: "Prescription Episode "+(j+1),
                                   state: {
                                         expanded: true
                                    },
                                   nodes: nodes
                                 } ; 
   	
                       } 
           	    
                 	 var galley = document.getElementById('galley');
                     viewer = new Viewer(galley, {
                        url: 'data-original',
                      }); 

                     $('#tree').treeview({
                         data: tree,
                         backColor: '#a0d1fb'
                       });  

                     $('#tree').on('nodeSelected', function(event, node) {
                     	  console.log('node::>>>'+node.id);
                     	  //  viewer.view(node.id); 
                     	}); */

                     	} 
            	});
               }
      		    else 
      	   		   {
      		    	/*$('#prescViewVisitLstMsgId a').text('Not Uploaded');*/
      		    	$('#prescViewVisiBtnId').attr('disabled','');
      	   		   }
            	}
                   function getTree() { 
                       console.log('result getTree:::>>');
                       var str = 'one^'+crNo+'^two^'+patEpisodeCode+'^'+hospCode;
                   	/*  $.ajax({url:'https://220.156.189.222/HBIMS/services/restful/UserService/retriveImageData?DeptValue=one^331011800025470^two^10511001^33101',type:'GET', crossOrigin: true,success:function(result)
                       	{
                       		console.log('success retriveImageData::::>>>');
                       	}
                   	}); */
                    	
                    		 $.ajax({
                           	 url:'/HISDRDESK/services/restful/UserService/getPatDataViewPrescription?CrNo='+crNo+'&seatid='+seatId+'',    //getPatData
                           	 type:'GET',
                           	 async: true,
                             beforeSend: function ( xhr ) {    
                            	 
                                $('#tree').append('<font id="treeMsgP" style="color:#1e87f0"><i class="fa fa-spinner fa-spin" style="font-size:18px"></i> Loading prescription....</font>'); // Do not comment !!! Not In use.
                                
                           	 /* $('.prescViewVisitLst').append('<li role="presentation" id="prescViewVisitLstMsgId"><a><i class="fa fa-spinner fa-sping"></i> Loading</a></li>'); */
                             },
                           	 success:function(result)
                            	{
                                 	console.log('result::>>'+JSON.stringify(result));
                                 	if(result.status==1)
                                 	{
                                 		console.log('result.status:::>>>'+result.status);
	                                    for(var i=0;i<result.pat_details.length;i++)
	                                 	{ 
	                                     	console.log('result.pat_details.length:::>>>'+result.pat_details.length+'::: i >>'+i+'::::result.pat_details[i].EPISODECODE:::>>'+result.pat_details[i].EPISODECODE);
	                                     	
	                                     	getImage(result.pat_details[i].EPISODECODE,i+1,result.pat_details[i].VISITNO,result.pat_details[i].EPISODEDATE);
	                                     
	                                 	}
	                                 }
	                                 else
	                                 {
	                                     $('#prescViewVisitLstMsgId a').text('Not Uploaded');
	                                     $('#prescViewVisiBtnId').attr('disabled','');
		   	          					    tippy('#prescViewVisiBtnId', {
		   	      				              content: 'Not Uploaded',
		   	      				              delay: 20,
		   	      				              arrow: true,
		   	      				              arrowType: 'round',
		   	      				              size: 'small',
		   	      				              duration: 500,
		   	      				              animation: 'scale'
		   	          					    });
	                                 } 
                            	},
                            	error : function(jqXHR, exception){
                            		if (jqXHR.status == 404) {
                            			console.log('error_____');
                                		$('#prescViewVisitLstMsgId a').text('Not Found');
                                        $('#prescViewVisiBtnId').attr('disabled','');
                                    } 
                            	}
                        	});  
                   } 
                    getTree();  
            });
       		
       var colorArray = ['36a2eb','ff6384','4bc0c0','ffcd56','9966ff','ff9f40','ffcf9f','ffe6aa','a5dfdf','9ad0f5','a0cece','c9cbcf','8afdfd','f67019','4dc9f6']; 
    	
       $(document).ready(function(){ 
      	 var crNo = $('#patCrNoPrescriptionPanel').text(); //  331011800025054   331011800015989   331011800025470 
      	 var hospCode = $('#patHospitalCodePrescriptionPanel').text(); // '33101'

      	 $.ajax({url:'/HISDRDESK/services/restful/invService/investigationDataList?crNo='+crNo+'&hosCode='+hospCode+'',  //http://10.226.21.120:8088/eHMS_Web_Services/service/invService/investigationDataList?crNo=331011800003255&hosCode=33101
      			async:true,
      			success:function(data){ 
      				var str = '';
      				var menuId = 'investigationModalNavMenuItem';
      				for(var i=0;i<data.length;i++)
      				{   str = '';
      					var entryDateArr = [];
      					var chartData = [];
      					var chartDataSetArr = [];
      					var testParamListArr = [];
      					/* str+='<h5 class="text-left" style="margin:0px;">Test Name : '+data[i].TESTNAME+'</h5>';  */
      					$('.investigationModalNavMenu').append('<li><a href="#'+menuId+(i+1)+'" data-toggle="tab">'+data[i].TESTNAME+'</a></li>'); 
      					str+='<div class="table-responsive"><table class="table table-hover table-striped table-condensed" id="'+data[i].TESTNAME.trim().split(' ').join('_')+'">';
      					str+='<thead><tr style="color:#6d6db7"><th>'+data[i].TESTNAME+'</th>';	
      					for(var j=0;j<data[i].DATA.length;j++)
      					{
      						str+='<th>'+data[i].DATA[j].ENTRYDATE+'</th>';
      						entryDateArr[j]=data[i].DATA[j].ENTRYDATE;
      					}
      					str+='<th>Range</th>';
      					str+='<th>Action</th>';
      					str+='</tr></thead>'; 
      					str+='<tbody>'; 
      					for(var j=0;j<1;j++)
      					{   
      						for(var k=0;k<data[i].DATA[j].DETAILS.length;k++)
      						{ 
      							chartData[k]=[]; 
      							str+='<tr>';
      							str+='<td>'+data[i].DATA[j].DETAILS[k].PARAMETERNAME+'</td>';
      							testParamListArr[k] = data[i].DATA[j].DETAILS[k].PARAMETERNAME;
      							for(var m=0;m<data[i].DATA.length;m++)
      							{   if(m==(data[i].DATA.length-1))
      									str+='<td style="color:green">'+data[i].DATA[m].DETAILS[k].VALUE+'</td>';
      								else
          								str+='<td>'+data[i].DATA[m].DETAILS[k].VALUE+'</td>';
      								chartData[k][m]=data[i].DATA[m].DETAILS[k].VALUE;
      							}
      							str+='<td><i>'+data[i].DATA[j].DETAILS[k].RANGE+'</i></td>';  
      							str+='<td class="text-right"><button id="parameterTrendBtn_'+k+'" class="btn btn-sm btn-info testTrendsBtn" type="button" parameterCode="'+data[i].DATA[j].DETAILS[k].PARAMETERCODE+'" onclick="trendParamInChart(this,\''+data[i].DATA[j].DETAILS[k].PARAMETERCODE+'\')">Trends</button></td>'; 
      							str+='</tr>';
      						} 					
      					}
      					str+='</tbody>'; 
      					str+='</table></div>';
      					str+='<h5 class="text-left cumulativeTestChartHeading">Cumulative Test Trend Chart</h5>';
      					str+='<div class="col-sm-12" style="position: relative;"><canvas id="'+menuId+(i+1)+'_chart"></canvas></div>';
      					str+='<div class="col-sm-12"><p style="margin-top:30px; font-weight:normal"><font class="text-danger">*</font> Only when a test component (e.g. HbA1c) is being found more then once in a particular user\'s report then a "Trend" is generated.</p></div>';
      					$('.investigationModalNavMenuContent').append('<div class="tab-pane" id="'+menuId+(i+1)+'">'+str+'</div>');
      					console.log('chartData.length::::::>>>>>>'+chartData.length);
      					for(var k=0; k<chartData.length;k++)
      					{ 
      						chartDataSetArr[k]={
      				                label: testParamListArr[k],
      				                backgroundColor: '#'+colorArray[k],
      				                borderColor: '#'+colorArray[k],
      								fill: false,
      				                data: chartData[k]								
      								};
      					}
      				    console.log('chartData::::>>>'+chartDataSetArr.toString());
      					var ctx = document.getElementById(menuId+(i+1)+'_chart').getContext('2d'); 
      					if(entryDateArr.length>1)
      					{ 
      						$('.cumulativeTestChartHeading').show();
      					    var chart = new Chart(ctx, { 
      					        type: 'line',  
      					        data: {
      					            labels: entryDateArr.length==1? [entryDateArr[0],'']:entryDateArr,
      					            datasets: chartDataSetArr
      					        },

      					        // Configuration options go here
      					        options: {}
      					    });
      					}
      					else
      						$('.cumulativeTestChartHeading').hide();

      					for(var k=0; k<testParamListArr.length;k++)
      					{  
      					    tippy('#parameterTrendBtn_'+k, {
      				              content: testParamListArr[k],
      				              delay: 50,
      				              arrow: true,
      				              arrowType: 'round',
      				              size: 'small',
      				              duration: 500,
      				              animation: 'scale'
      				          });
      					} 
      				} 
      			} 
      		});	 
      	        
      		
      			$.ajax({url:'/HISDRDESK/services/restful/invService/reportList?crNo='+crNo+'&hosCode='+hospCode+'',
      				async:true,
      				beforesend : $('.patInvestigationTestList').parent().append('<p id="reportListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
      				success:function(result){ 
      					console.log('result::::::::>>>>>>'+result);
      					if(result.length>0)
      					{
      						$('#reportListMsg').remove();
      						for(var i=0;i<result.length;i++)
      						{ 
      							//console.log('result[i].REQDNO::::::::::>>>>>'+result[i].REQDNO+':::::::::::::result[i].TESTNAME::::::::::>>>>>'+result[i].TESTNAME);
      							$('.patInvestigationTestList').append('<li><a onclick="getPatPrescriptionImageInNewWindow(\''+crNo+'\',\''+result[i].REQDNO+'\')">'+result[i].TESTNAME+' </a> <i style="color:#5dbdbd;">('+result[i].REPORTDATE+')</i></li>');				
      						}
      					}
      					else
      						$('#reportListMsg').text('No Record Found !');				
      				},
      				complete: $('#reportListMsg').text('Error !!!') 
      			}); 
      		});

      	function trendParamInChart(e,paramCode)
      	{	
      		var crNo = $('#patCrNoPrescriptionPanel').text(); //  331011800025054   331011800015989   331011800025470 
      		var hospCode = $('#patHospitalCodePrescriptionPanel').text(); // '33101'
      				
      		console.log('paramCode::::::::::>>>>>>>'+paramCode);
      		if($(e).attr('parameterCode')==$('#parameterTrendChartDiv').attr('parameterChartDivParamCode'))
      		{
      			$('#parameterTrendChartDiv').remove();
      			return false;
      		}
      		else
      		$.ajax({url:'/HISDRDESK/services/restful/invService/paraRawData?crNo='+crNo+'&hosCode='+hospCode+'&paraCode='+paramCode+'',
      		async:true,
      		beforesend : $(e).parent().parent().parent().parent().after('<p id="trendParamChartMsgId"><i class="fa fa-spinner fa-spin"></i> Loading...</p>'),
      		success:function(result){ 
      			console.log('result::::::::>>>>>>'+result);
      			if(result.length>0)
      			{
      				$('#trendParamChartMsgId').remove();
      				if($('#parameterTrendChartDiv'))
      					$('#parameterTrendChartDiv').remove();
      				var strH='<div id="parameterTrendChartDiv" class="col-sm-12" parameterChartDivParamCode="'+paramCode+'" style="position: relative;"><h5 class="text-left" style="margin:0px">Component Trend Chart</h5><canvas id="chart_'+paramCode+'"></canvas><br></div>';
      				$(e).parent().parent().parent().parent().after(strH);
      				var entryDate = [];
      				var paramValue = [];
      				console.log('result[0].DETAILS.length::::::>>>'+result[0].DETAILS.length);
      				var i = 0;
      				for(i; i<result[0].DETAILS.length; i++)
      				{
      					entryDate[i] = result[0].DETAILS[i].ENTRYDATE;
      					paramValue[i] = result[0].DETAILS[i].VALUE;
      			    } 
      				var ctx2 = document.getElementById('chart_'+paramCode).getContext('2d'); 
      				if(entryDate.length>1)
      				{
      					$('#parameterTrendChartDiv').show();
      				    new Chart(ctx2, { 
      				        type: 'line',  
      				        data: {
      				            labels: entryDate.length==1? [entryDate[0],'']:entryDate,  
      				            datasets: [{
      				                label: result[0].PARAMETERNAME,  
      				                backgroundColor: '#'+colorArray[0],
      				                borderColor: '#'+colorArray[0], 
      				                fill:false,
      				                data: paramValue								
      								}]
      				        }, 
      				        // Configuration options go here
      				        options: {}
      				    });
      				}
      				else 
      					$('#parameterTrendChartDiv').hide(); 
      			}
      			else
      				$('#trendParamChartMsgId').text('No Record Found !');				
      		}
      	}); 
      	}
      	
      	function getPatPrescriptionImageInNewWindow(crno,reqdno)
    	{ 
    		 var hospCode = $('#patHospitalCodePrescriptionPanel').text(); // '33101'
    				
    		console.log('crno:::::'+crno+'::::::::::reqdno:::::>>'+reqdno);
    		$.ajax({url:'/HISDRDESK/services/restful/invService/reportData?crNo='+crno+'&reqDNo='+reqdno+'&hosCode='+hospCode+'',
    			async:true,
    			success:function(result){
    				// Chrome 1 - 68
    				var isChrome = navigator.userAgent.indexOf("Chrome") != -1; 
    				if(isChrome)
    				{ 
    					var pdfWindow = window.open("");
    					pdfWindow.document.write("<object style='width:100vw; height:100vh' data='data:application/pdf;base64,"+result[0].PDFDATA+"'></object>");
    					
    				}
    				else
    				{		 
    				 window.open("data:application/pdf;base64, " + result[0].PDFDATA); 
    				} 
    				
    			}
    		});  
    		}
      	
   	 $(document).ready(function(){
   		
   	  var viewer; 
		var crNo = $('#patCrNoPrescriptionPanel').text(); //  331011800025054   331011800015989   331011800025470 
		var hospCode = $('#patHospitalCodePrescriptionPanel').text(); // '33101'
		var patEpisodeCode = $('#patEpisodeCodePrescriptionPanel').text(); // 10211003   $('#patEpisodeCodePrescriptionPanel').text();
		var seatId = $('#patSeatIdPrescriptionPanel').text();  // 10001   $('#patSeatIdPrescriptionPanel').text();
 	    var tree = [];
    	var nodes = [];
    	var reqId=$('input[name=eTeleconsultancyreq]').val();
    	
    	$.ajax({ url:'/HISServices/service/eConsultReq/getDocStatus2?requestId='+reqId+'&hospCode='+hospCode+ ' ',   
          	 type:'GET', 
          	// async: true,
          	 beforesend: $('.prescViewVisitLst').append('<li role="presentation" id="prescViewVisitLstMsgId"><a><i class="fa fa-spinner fa-sping"></i> Loading</a></li>'),
          	 success:function(result2)
           	{
          		 console.log('result2result2result2result2'+JSON.parse(result2).REQ.length);
          		var count1=JSON.parse(result2).REQ.length ;
    		 	for(var q=1 ; q <= count1  ; q++ ){
    		 		$('#eteleconsultancyViewDocLinkClassUl').append("<li id='eteleconsultancyViewDocLinkClass'><a class='eteleconsultancyViewDocLinkClass' href='#'><input type='hidden' value="+q+"><i class='glyphicon glyphicon-picture' style='color:grey'></i> Image ("+q+")</a> </li>");  
    		 	}
            	   if(count1 >= '1' )
            	   {
            		   
            		 	$('#eteleconsultancyViewDocLinkClassID').removeAttr('disabled');   
            		 	
            		 	
            		   
            	   }
           	}
    	})
    	
   		 
   	 });
      	
      	 $(document).ready(function(){ 
             var viewer; 
     		var crNo = $('#patCrNoPrescriptionPanel').text(); //  331011800025054   331011800015989   331011800025470 
     		var hospCode = $('#patHospitalCodePrescriptionPanel').text(); // '33101'
     		var patEpisodeCode = $('#patEpisodeCodePrescriptionPanel').text(); // 10211003   $('#patEpisodeCodePrescriptionPanel').text();
     		var seatId = $('#patSeatIdPrescriptionPanel').text();  // 10001   $('#patSeatIdPrescriptionPanel').text();
       	    var tree = [];
          	var nodes = [];
          	var reqId=$('input[name=eTeleconsultancyreq]').val();
          	
          	$('#prescViewVisiBtnId').removeAttr('disabled'); 
          	
         /* 	$( "#eteleconsultancyViewDocId").change(function() {
          	//  alert( "Handler for .change() called." );
          		var slno=$('select[name="eteleconsultancyViewDoc"] option:selected').val();

          		
          		
          		
          	var imgdata='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h2TkDE/azzYBeWn7ATIiP/w18HRGvn/+85/hICindiNXOShP2wP/+Mc/gjfXgDPtevHFF4s6kjzqT8CZe+iRm36lb4DaJRp0mcpGHjIwdtA96eKruroLymGT8PzSl74U/FUH0FnQPf0HkG/HHXeMM6AvQHdkeu+996IugAzokbEvHgLlS21M5QC6UH2SD52pDznPmDEj6lDbJJPuJf8is0dKo6QEnAfLCUT/+c9/HtHok08+GXnsPQkoceLEiXb22WdHgzfffHN77LHHCrmZouHLHpR4MxDPOuusyNtiiy3s4YcfLuahuK233tp23XXXokIBTg8eAnR0CLPv97//fRsyZEghx+Lhh0AZ+JDGmTJ0Gtdppy299NL2i1/8ItogWtoOvQYEvD744AM755xzoiwgn8HGWbxWWmklGzhwoD3//PPRdvjQPtqFYctAODNAiZ6hkUwCS93FFlss8o4++ug4I99+++1n1157bbEM5xVXXDH2sKG/8MIL7V//+lfQIpd4Ij9twVGlfU15dDJixIhIX3vtte0HP/hBsT3kA93DD96cy+2RMhmwn/fhhx/GwJMMnAcMGFDcy7zoootiC0JtAKoDG0OmY445Js44R+xRDl10KdAR9eyxxx72pz/9KWTcZJNNbJ999ilQZDjyyCOjndD95S9/CT2kfQCwZ5wJIA16MGzYMDv00EOjLeD111+3Sy65JPSK/q+77rqibjlTFrlZ1SwoaDdt4ACya+k6lffll18uriDT+tAHWzZEk+ShV+kaqDyQvPQZ0Sv1kzZ8+PAY07SZ8f21r30t+JJHf2BrF198cfDFhhiTki0di//3f/9nyy67bFyzTXj99dcHD2jhzbbc4MGDoy2UTcc76AjTejBQjAYW1wwEzihz+eWXDweAsXDPkkOdoegB0PhHH33U1lxzTdtrr71iWYGimPlZaigqgS9njgcffNC+/vWvh3PQoHvooYfiy/aUBSgcQ6ccx7777mtf+MIXIp+IjCUMQD7yU+NIQX3Usdpqq4V8G264YTEq4YEaZQEDYrfddotr6vjmN79pBxxwQNxDS5qMe/XVVw951lprrUhHP+wtPfvss7buuutG29Ardd97771xcE3a/fffX2wXZaFlkJBPW9ABoE5oKEP6DTfcYHvuuWe0gUiBOjHmc889N8rusMMO0V+AcuhW8qu/oEtBevbZ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'
          		prescImgFixedTriggerFun1(imgdata);
          		
          		$.ajax({ url:'/HISServices/service/eConsultReq/retriveImageData?hospCode='+hospCode+'&requestID='+reqId+'&slno='+slno,   
           	 type:'GET', 
           	// async: true,
           	 beforesend: $('.prescViewVisitLst').append('<li role="presentation" id="prescViewVisitLstMsgId"><a><i class="fa fa-spinner fa-sping"></i> Loading</a></li>'),
           	 success:function(result2)
            	{
           		 console.log('result2result2result2result2'+result2);
             	   if(result2.ImageData != null)
             	   {
             		  console.log('::::::::::::1');
                 	   if(result2.ImageData.length>0)
                 	   { 
                 		  console.log('::::::::::::2');
                 		  $('#prescViewVisiBtnId').removeAttr('disabled');
                    	    $('#prescViewVisitLstMsgId a').remove();
                     	   // console.log('success retriveImageData::::>>>'+result2.ImageData.length+':::episode:::>>'+episodeCode+'::::count:::::>>>>>'+count); 
                     	    $('.prescViewVisitLst').append('<li class="divider"></li>');
                     	    //$('.prescViewVisitLst').append('<li class="dropdown-header" style="color: #0e0e35;font-weight: bold;background-color: #e4e4e4;">VISIT '+visitNo+'</li>'); 
                     	    for(var j=0;j<result2.ImageData.length;j++)
                         	{
                     	    	
                     	    	prescImgFixedTriggerFun1(result2.ImageData[j].GBYTE_DOC_DATA);
                     	    	localStorage.removeItem('prescImg'+count.toString()+(j+1));
                     	    	localStorage.setItem('prescImg'+count.toString()+(j+1),result2.ImageData[j].GBYTE_DOC_DATA); 
                             	$('.prescViewVisitLst').append('<li role="presentation" class="prescImgListItems" style="font-size: 12px;"><a style="color: #6060ed;" role="menuitem" tabindex="-1" href="javascript:;" onclick="prescImgFixedTriggerFun(\'prescImg'+count.toString()+(j+1)+'\')">'+result2.ImageData[j].VISIT_NO+'</a></li>');  //visitDate.split(" ")[0]
                     			$('#patPrescViewModal').find('.pictures').append('<li><img  alt="Visit '+(j+1)+'" data-original="data:image/jpeg;base64,'+result2.ImageData[j].IMG_DOCUMENT+'" src="data:image/jpeg;base64,'+result2.ImageData[j].IMG_DOCUMENT+'" ></li>');
                    	        						nodes[j]={
                                                               text: "visit "+(j+1),
                                                               icon: "glyphicon glyphicon-calendar",
                                                               selectedIcon: "glyphicon glyphicon-calendar",
                                                               color: "#727272",
                                                               backColor: "#FFFFFF",
                                                               href: "#node-"+(j+1),
                                                               selectable: true,
                                                               state: {
                                                                 checked: false, 
                                                               },
                                                               id: count.toString()+j.toString(),
                                                               tags: ['available']  
                    	                 					 }; 
                             	}
                             	  
                              $('#treeMsgP').remove();  
                 	   }
                 	   else
                     {
                 		 $('#prescViewVisitLstMsgId a').text('Not Uploaded');
                      }
             	   }
             	   else  
                 	 {
	              		 $('#prescViewVisitLstMsgId a').text('Not Uploaded');
	             		 $('#treeMsgP').text('Not Uploaded');	             		 
                 	 }
                     
            	}, 
            	complete: function(){
            		 
     	} 
        	
   			 
   		})	 
   		 });*/
          	
          	
          //	$('.eteleconsultancyViewDocLinkClass').on('click',  function() {
          	//$( ".eteleconsultancyViewDocLinkClass").on(function() {
             // 	 alert( "Handler for .change() called." );
              		//var slno=$('select[name="eteleconsultancyViewDoc"] option:selected').val();
          		$(document).on('click', '.eteleconsultancyViewDocLinkClass', function(e) {

          		var slno=$(this).first()[0].childNodes[0].value;
          		
          		

              		
              		var imgdata="";
              		
            //  	var imgdata='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h2TkDE/azzYBeWn7ATIiP/w18HRGvn/+85/hICindiNXOShP2wP/+Mc/gjfXgDPtevHFF4s6kjzqT8CZe+iRm36lb4DaJRp0mcpGHjIwdtA96eKruroLymGT8PzSl74U/FUH0FnQPf0HkG/HHXeMM6AvQHdkeu+996IugAzokbEvHgLlS21M5QC6UH2SD52pDznPmDEj6lDbJJPuJf8is0dKo6QEnAfLCUT/+c9/HtHok08+GXnsPQkoceLEiXb22WdHgzfffHN77LHHCrmZouHLHpR4MxDPOuusyNtiiy3s4YcfLuahuK233tp23XXXokIBTg8eAnR0CLPv97//fRsyZEghx+Lhh0AZ+JDGmTJ0Gtdppy299NL2i1/8ItogWtoOvQYEvD744AM755xzoiwgn8HGWbxWWmklGzhwoD3//PPRdvjQPtqFYctAODNAiZ6hkUwCS93FFlss8o4++ug4I99+++1n1157bbEM5xVXXDH2sKG/8MIL7V//+lfQIpd4Ij9twVGlfU15dDJixIhIX3vtte0HP/hBsT3kA93DD96cy+2RMhmwn/fhhx/GwJMMnAcMGFDcy7zoootiC0JtAKoDG0OmY445Js44R+xRDl10KdAR9eyxxx72pz/9KWTcZJNNbJ999ilQZDjyyCOjndD95S9/CT2kfQCwZ5wJIA16MGzYMDv00EOjLeD111+3Sy65JPSK/q+77rqibjlTFrlZ1SwoaDdt4ACya+k6lffll18uriDT+tAHWzZEk+ShV+kaqDyQvPQZ0Sv1kzZ8+PAY07SZ8f21r30t+JJHf2BrF198cfDFhhiTki0di//3f/9nyy67bFyzTXj99dcHD2jhzbbc4MGDoy2UTcc76AjTejBQjAYW1wwEzihz+eWXDweAsXDPkkOdoegB0PhHH33U1lxzTdtrr71iWYGimPlZaigqgS9njgcffNC+/vWvh3PQoHvooYfiy/aUBSgcQ6ccx7777mtf+MIXIp+IjCUMQD7yU+NIQX3Usdpqq4V8G264YTEq4YEaZQEDYrfddotr6vjmN79pBxxwQNxDS5qMe/XVVw951lprrUhHP+wtPfvss7buuutG29Ardd97771xcE3a/fffX2wXZaFlkJBPW9ABoE5oKEP6DTfcYHvuuWe0gUiBOjHmc889N8rusMMO0V+AcuhW8qu/oEtBevbZ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'
              	//	prescImgFixedTriggerFun1(imgdata);
              		
              		$.ajax({ url:'/HISServices/service/eConsultReq/retriveImageData?hospCode='+hospCode+'&requestID='+reqId+'&slno='+slno,   
               	 type:'GET', 
               	// async: true,
               	 beforesend: $('.prescViewVisitLst').append('<li role="presentation" id="prescViewVisitLstMsgId"><a><i class="fa fa-spinner fa-sping"></i> Loading</a></li>'),
               	 success:function(result2)
                	{
               		 console.log('result2result2result2result2'+result2);
                 	   if(result2.ImageData != null)
                 	   {
                 		  console.log('::::::::::::1');
                     	   if(result2.ImageData.length>0)
                     	   { 
                     		  console.log('::::::::::::2');
                     		  $('#prescViewVisiBtnId').removeAttr('disabled');
                        	    $('#prescViewVisitLstMsgId a').remove();
                         	   // console.log('success retriveImageData::::>>>'+result2.ImageData.length+':::episode:::>>'+episodeCode+'::::count:::::>>>>>'+count); 
                         	    $('.prescViewVisitLst').append('<li class="divider"></li>');
                         	    //$('.prescViewVisitLst').append('<li class="dropdown-header" style="color: #0e0e35;font-weight: bold;background-color: #e4e4e4;">VISIT '+visitNo+'</li>'); 
                         	    for(var j=0;j<result2.ImageData.length;j++)
                             	{
                         	    	
                         	    	prescImgFixedTriggerFun1(result2.ImageData[j].GBYTE_DOC_DATA);
                         	    	localStorage.removeItem('prescImg'+count.toString()+(j+1));
                         	    	localStorage.setItem('prescImg'+count.toString()+(j+1),result2.ImageData[j].GBYTE_DOC_DATA); 
                                 	$('.prescViewVisitLst').append('<li role="presentation" class="prescImgListItems" style="font-size: 12px;"><a style="color: #6060ed;" role="menuitem" tabindex="-1" href="javascript:;" onclick="prescImgFixedTriggerFun(\'prescImg'+count.toString()+(j+1)+'\')">'+result2.ImageData[j].VISIT_NO+'</a></li>');  //visitDate.split(" ")[0]
                         			$('#patPrescViewModal').find('.pictures').append('<li><img  alt="Visit '+(j+1)+'" data-original="data:image/jpeg;base64,'+result2.ImageData[j].IMG_DOCUMENT+'" src="data:image/jpeg;base64,'+result2.ImageData[j].IMG_DOCUMENT+'" ></li>');
                        	        						nodes[j]={
                                                                   text: "visit "+(j+1),
                                                                   icon: "glyphicon glyphicon-calendar",
                                                                   selectedIcon: "glyphicon glyphicon-calendar",
                                                                   color: "#727272",
                                                                   backColor: "#FFFFFF",
                                                                   href: "#node-"+(j+1),
                                                                   selectable: true,
                                                                   state: {
                                                                     checked: false, 
                                                                   },
                                                                   id: count.toString()+j.toString(),
                                                                   tags: ['available']  
                        	                 					 }; 
                                 	}
                                 	  
                                  $('#treeMsgP').remove();  
                     	   }
                     	   else
                         {
                     		 $('#prescViewVisitLstMsgId a').text('Not Uploaded');
                          }
                 	   }
                 	   else  
                     	 {
    	              		 $('#prescViewVisitLstMsgId a').text('Not Uploaded');
    	             		 $('#treeMsgP').text('Not Uploaded');	             		 
                     	 }
                         
                	}, 
                	complete: function(){
                		 
         	} 
            	
       			 
       		})	 
       		 });
          	
          	
      	 });
      	 
      	 
      	 
   	  const speechRecognition=window.speechRecognition || window.webkitSpeechRecognition;

	  var recognition=new speechRecognition();

	  var textbox;
	
	  let content="";

	  recognition.continuous=true;

	  recognition.onstart=function(){
		  console.log("voice recognition is on");
	  }
	  recognition.onspeechend=function(){
		  console.log("No Activity");

	  }
	  recognition.onerror=function(event){
		  console.log(event);
		  console.log("aborted");

	  }
	  recognition.onresult=function(event){
	  	var current=event.resultIndex;
	  	var transcript=event.results[current][0].transcript;
	  	content +=transcript;
	  	
	  	textbox.val(content);
	  }

	  $(".start-btn").click(function(event){		  
		  textbox=$(this).parent().parent().find("textarea");
		  $(this).css("display","none");
	  		$(this).siblings().css("display","");
	  	if(content.length){
	  		//content += '';
	  		content=$(textbox).val()+" ";
	  	}
	  	
	  	recognition.start();
	  	$(textbox).focus();
	  	console.log("start");
	  });

	  	$(".stop-btn").click(function(event){
	  		recognition.abort();
	  		$(this).css("display","none");
	  		$(this).siblings().css("display","");
	  		console.log("stopped");
	  });
	  	
	  	
	  	
	  	function abortMic(obj){
	  		$(obj).parent().find(".stop-btn").trigger("click");
	  	}
	 
       