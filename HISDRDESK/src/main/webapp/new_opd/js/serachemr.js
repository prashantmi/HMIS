function checkbydate(){
	
	var checkBox = document.getElementById("strCheckbyDateId");
	if(checkBox.checked == true){
		 document.getElementById("DivId").style.display = "block";
	}else{
		document.getElementById("DivId").style.display = "none";
	}
}


function strCheckByAgeFun(){
	
	var checkBox = document.getElementById("strCheckByAgeId");
	if(checkBox.checked == true){
		 document.getElementById("AgeDivId").style.display = "block";
	}else{
		document.getElementById("AgeDivId").style.display = "none";
	}
}


function strCheckByGenderFun(){
	
	var checkBox = document.getElementById("strCheckByGenderId");
	if(checkBox.checked == true){
		 document.getElementById("GenderDivId").style.display = "block";
	}else{
		document.getElementById("GenderDivId").style.display = "none";
	}
}

function strCheckByDeptFun(){
	
	var checkBox = document.getElementById("strCheckByDeptId");
	if(checkBox.checked == true){
		 document.getElementById("DeptDivId").style.display = "block";
	}else{
		document.getElementById("DeptDivId").style.display = "none";
	}
}



function serachemrData(e){
	
	
	
	$('#SearchParaIs').val().trim();
	
	if($('#SearchParaIs').val().trim() ==''){
		alert('Enter Search Parametere');
		return false;
	}
	
	$(' .TestDivIdClass').html('');
	$(' .DrugDivIdClass').html('');
	$(' .ChiefComplaintClass').html('');
	$(' .DiagnosisClass').html('');
	$(' .crNoIdDivIdClass').html('');
	var FromDate='';
	var ToDate='';
	var ChkVal='';
	var checkBox = document.getElementById("strCheckbyDateId");
	if(checkBox.checked == true){
		FromDate =  $('#strfromDateId').val().trim();
		ToDate=  $('#strToDateId').val().trim();
		ChkVal ='1' ;
		if(FromDate == ''){
			alert('Please select From Date');
			return false;
		}
		if(ToDate == ''){
			alert('Please select To Date');
			return false;
		}
		
	}else{
		 FromDate='';
		 ToDate='';
		 ChkVal ='0' ;
	}
	
	var FromAge='';
	var ToAge='';
	var AgeChkVal='';
	var AgecheckBox = document.getElementById("strCheckByAgeId");
	if(AgecheckBox.checked == true){
		FromAge =  $('#strFromAgeId').val().trim();
		ToAge=  $('#strToAgeId').val().trim();
		AgeChkVal ='1' ;
		if(FromAge == ''){
			alert('Please Enter From Age');
			return false;
		}
		if(ToAge == ''){
			alert('Please Enter To Age');
			return false;
		}
		
	}else{
		FromAge='';
		 ToAge='';
		 AgeChkVal ='0' ;
	}
	
	
	
	
	var strGender='';
	var strChkGenderVal='';
	var AgecheckBox = document.getElementById("strCheckByGenderId");
	if(AgecheckBox.checked == true){
		strGender =  $('#strGenderId').val().trim();
		strChkGenderVal ='1' ;
		
	}else{
		strGender='';
		strChkGenderVal ='0' ;
	}
	
	

	var strDeptCode='';
	var strChkDeptCodeVal='';
	var DeptcheckBox = document.getElementById("strDeptCodeId");
	if(DeptcheckBox.checked == true){
		strDeptCode =  $('#strGenderId').val().trim();
		strChkDeptCodeVal ='1' ;
		if(strDeptCode == '0')
			{
			alert('Please Select Dept Code');
			return false;
			}
		
	}else{
		strDeptCode='';
		strChkDeptCodeVal ='0' ;
	}
	
	
	
	var strVital='';
	var strVitalVal='';
	var strVitalBox = document.getElementById("strVitalSerachId");
	if(strVital.checked == true){
		
		strVitalVal ='1' ;
		
	}else{
		
		strVitalVal ='0' ;
	}
	
	
	var SearchJSON = { 
			'searchPara' : $('#SearchParaIs').val().trim() ,
			'strIsDataFilterByDate' : ChkVal,
			'strFromDate'  			: FromDate,
			'strToDate'				: ToDate	,
			'strFromAge'			: FromAge ,
			'strToAge'				: ToAge ,
			'strAgeChkVal'			: AgeChkVal ,
			'strChkGenderVal'		: strChkGenderVal ,
			'strGender' 			: strGender , 
			'strDeptCode'			: strDeptCode , 
			'strChkDeptCodeVal'		: strChkDeptCodeVal ,
			'strVitalVal'			: strVitalVal
			
		
	};
	var data = JSON.stringify(SearchJSON);
	console.log(data);
	
	$.ajax({url:'/HISDRDESK/services/restful/search/emr/',type:'POST',data:{JsonData:data},success:function(result)
    	{
		 console.log(result);
		 
		 console.log(result.TestJson);
		 console.log(result.TestJson.length);
			 if(result.TestJson.length > 0){
				 $('#TestDivId').html('( '+result.TestJson.length+' Record Found )');
				 for (var i = 0; i < result.TestJson.length ; i++) {
					 $(' .TestDivIdClass').append(' <li> ' +result.TestJson[i]+'</li> ');
					}
			 }else
				 $(' .TestDivIdClass').append(''); 
			 
			 console.log(result.DrugJson);
			 console.log(result.DrugJson.length);
			 
				 if(result.DrugJson.length > 0){
					 $('#DrugDivId').html('( '+result.DrugJson.length+' Record Found )');
								 for (var i = 0; i < result.DrugJson.length ; i++) {
									// console.log('1111');
									 $(' .DrugDivIdClass').append(' <li> ' +result.DrugJson[i]+'</li> ');
									}
							 }else
								 $(' .DrugDivIdClass').append('');
				 
				 
				 if(result.DiagnosisJson.length > 0){
					 $('#DiagnosisDivId').html('( '+result.DiagnosisJson.length+' Record Found )');
					 for (var i = 0; i < result.DiagnosisJson.length ; i++) {
						// console.log('1111');
						 $(' .DiagnosisClass').append(' <li> ' +result.DiagnosisJson[i]+'</li> ');
						}
				 }else
					 $(' .DiagnosisClass').append('');
				 
				 
				 if(result.chiefComplainthashSet.length > 0){
					 $('#ChiefcomplaintDivId').html('( '+result.chiefComplainthashSet.length+' Record Found )');
					 for (var i = 0; i < result.chiefComplainthashSet.length ; i++) {
						// console.log('1111');
						 $(' .ChiefComplaintClass').append(' <li> ' +result.chiefComplainthashSet[i]+'</li> ');
						
						}
				 }else
					 $(' .ChiefComplaintClass').append('');
					 
				 
				 if(result.crhashSet.length > 0){
					 				$('#CRnoDivId').html('( '+result.crhashSet.length+' Record Found )');
									 for (var i = 0; i < result.crhashSet.length ; i++) {
										// console.log('1111');
										 $(' .crNoIdDivIdClass').append(' <li> ' +result.crhashSet[i]+'</li> ');
										}
								 }else
									 $(' .crNoIdDivIdClass').append('');
		 
    	}
	});
}