/*function $(selector) {
    var nodes = typeof selector === "string" 
        ? Array.from(document.querySelectorAll(selector))
        : [selector];
    var resultObject = {
        hide: function () {
            nodes.forEach(function (node) {
                node.style.visibility = "hidden";
            });
        }
    };
    return resultObject;
}*/
function approvalData1(val){

	
	/*
	var pat_dtls = {
		 "pat_dtls": {	
		 "requestID": val.hrgstr_req_id,
		 "CRNo": val.hrgnum_puk,
		 "scrResponse": val.hrgstr_screen_response ,
		 "consName": val.hrgstr_consultant_name,
		 "deptUnitCode": val.hrgnum_deptunit_code.toString(),
		 "deptUnitName": val.hrgstr_deptunit_name ,
         "requestStatus": "1" ,
         "consMobileNo": val.hrgnum_pat_mobile_no.toString(), 
         "patDocs": val.hrgstr_patdocs,
         "docMessage": val.hrgstr_doc_message ,
         "cnsltntId": val.hgstr_consultant_code.toString() ,
         "rmrks": val.hrgstr_remarks, 
         "email": val.gstr_pat_email_add ,
         "patWeight": val.hrgnum_pat_weight.toString(), 
         "patHeight": val.hrgnum_pat_height.toString(),
         "patMedication": val.hrgstr_pat_medication.toString() ,
         "patPastDiagnosis": val.hrgstr_pat_pastdiagnosis,
         "patAllergies": val.hrgstr_pat_allergies,
         "userId": val.gnum_userid.toString() ,
         "patientToken": val.patient_token ,
         "address": "",
         "hospCode": val.gnum_hosp_code.toString() ,
         "patMobileNo": val.hrgnum_pat_mobile_no.toString() ,
         "patName": val.hrgstr_pat_name ,
         "patAge": val.hrgstr_pat_age.toString() ,
         "patGender": val.hrgstr_pat_gender.toString() ,
         "appt_dept_unit": "10510@1002@Dharamshala"    ,
         "state_code": "09",
         "district_code": "140", 
         "country_code": "IND" ,
         "pat_guardian": "FatherName" ,
         "appointmentDate": val.hapdt_appointment_date.toString(), 
         "appointmentTime": val.hapstr_slot_start_time.toString() ,
         "start_time": val.hapstr_slot_start_time.toString(), 
         "end_time": val.hapstr_slot_end_time.toString(), 
         "shiftId": "13"
    }
}
*/
	var data = JSON.stringify(val);
	
//	console.log(':::::'+data);
	//var urldata='/HISServices/service/eConsultReq/updateRequestStatus?hospCode='+val.gnum_hosp_code.toString()+'&requestID='+val.hrgstr_req_id+'&reqStatus=1&consltID='+val.hgstr_consultant_code+'&consltName='+val.hrgstr_consultant_name+'&consltMobNo='+val.hrgnum_cons_mobile_no+'&docMessage='+val.hrgstr_doc_message+'&doctorToken='+val.doctor_token+''
	//console.log(':::::'+urldata);
	
	$.ajax({url:'/HISServices/service/eConsultReq/updateRequestStatus',type:'POST',data:{JsonData:data },success:function(result)
    	{
		console.log(':::::::::::::::::::: '+JSON.stringify(result));
		if(result1.status = "1"){
			//alert('Patient Approval Succesfully');
			//swal("Approve", "Patient Approve Successfully ", "success");

			swal("Patient Approve Successfully")
			.then((value) => {
				document.forms[0].strHiddenDeptCode.value=document.forms[0].deptUnitName.value ;
				document.forms[0].hmode.value="NEW";
				document.forms[0].submit();
			});
			
			
			//return true;
		}else{
			//$(reqid).show();
			swal("Error", "Something Went Worng! ", "error");
			/*alert('Something Went Wrong');
			return false;*/
		}
    	}
	});
}

function approvalData(val){

	
	console.log(':::::::::::::::::::'+JSON.stringify(val));
	console.log(val.hrgstr_req_id);
	var reqid='#'+ val.hrgstr_req_id ;
	$(reqid).hide();
	
//console.log(pid+':::::::::::::::'+status);
	
	/*var data = JSON.stringify(vitalJSON); */
	console.log(data); 
	/*var vitalJSON = { 
			'strProfileId' : '',
			'strStatus' : '',
	}*/
/*	var data = JSON.stringify(vitalJSON); 
	console.log(data);*/
	
	var pat_dtls = {
		 "pat_dtls": {	
		 "requestID": val.hrgstr_req_id,
		 "CRNo": val.hrgnum_puk,
		 "scrResponse": val.hrgstr_screen_response ,
		 "consName": val.hrgstr_consultant_name,
		 "deptUnitCode": val.hrgnum_deptunit_code.toString(),
		 "deptUnitName": val.hrgstr_deptunit_name ,
         "requestStatus": "1" ,
         "consMobileNo": val.hrgnum_pat_mobile_no.toString(), 
         "patDocs": val.hrgstr_patdocs,
         "docMessage": val.hrgstr_doc_message ,
         "cnsltntId": val.hgstr_consultant_code.toString() ,
         "rmrks": val.hrgstr_remarks, 
         "email": val.gstr_pat_email_add ,
         "patWeight": val.hrgnum_pat_weight.toString(), 
         "patHeight": val.hrgnum_pat_height.toString(),
         "patMedication": val.hrgstr_pat_medication.toString() ,
         "patPastDiagnosis": val.hrgstr_pat_pastdiagnosis,
         "patAllergies": val.hrgstr_pat_allergies,
         "userId": val.gnum_userid.toString() ,
         "patientToken": val.patient_token ,
         "address": "",
         "hospCode": val.gnum_hosp_code.toString() ,
         "patMobileNo": val.hrgnum_pat_mobile_no.toString() ,
         "patName": val.hrgstr_pat_name ,
         "patAge": val.hrgstr_pat_age.toString() ,
         "patGender": val.hrgstr_pat_gender.toString() ,
         "appt_dept_unit": "10510@1002@Dharamshala"    ,
         "state_code": "09",
         "district_code": "140", 
         "country_code": "IND" ,
         "pat_guardian": "FatherName" ,
         "appointmentDate": val.hapdt_appointment_date.toString(), 
         "appointmentTime": val.hapstr_slot_start_time.toString() ,
         "start_time": val.hapstr_slot_start_time.toString(), 
         "end_time": val.hapstr_slot_end_time.toString(), 
         "shiftId": "13"
    }
}
	var data = JSON.stringify(pat_dtls);
	
	console.log(':::::'+data);
	var urldata='/HISServices/service/eConsultReq/updateRequestStatus?hospCode='+val.gnum_hosp_code.toString()+'&requestID='+val.hrgstr_req_id+'&reqStatus=1&consltID='+val.hgstr_consultant_code+'&consltName='+val.hrgstr_consultant_name+'&consltMobNo='+val.hrgnum_cons_mobile_no+'&docMessage='+val.hrgstr_doc_message+'&doctorToken='+val.doctor_token+''
	console.log(':::::'+urldata);
	
	$.ajax({beforeSend:function(){$(".loader").css("display","");},url:urldata,headers: {"Access-Control-Allow-Origin" : "*"}, crossDomain:true,type:'GET',success:function(result)
    	{
		console.log(':::::::::::::::::::: '+JSON.stringify(result));
		console.log('result.Status '+result.Status);
		//console.log('result.Status check1 '+ if(result.Status = "1"){});
		//console.log('result.Status check2 '+result.Status);
		
		//var strval=result
		//if(result.status = "1"){
		if(result.Status == "1"){
		$.ajax({url:'/HISServices/service/eConsultReq/generateEpisodeWithReqNo?requestId='+val.hrgstr_req_id+'&hospCode='+val.gnum_hosp_code.toString()+'&consltID='+val.hgstr_consultant_code,headers: {"Access-Control-Allow-Origin" : "*"}, crossDomain:true,type:'GET',success:function(result1)
	    	{
			console.log(':::::::::::::::::::: '+result1);
			if(result1.status = "1"){
				//alert('Patient Approval Succesfully');
				//swal("Approve", "Patient Approve Successfully ", "success");

				swal("Patient Approved Successfully")
				.then((value) => {
					//$("#loader-6").css("display","none");
					document.forms[0].strHiddenDeptCode.value=document.forms[0].deptUnitName.value ;
					document.forms[0].hmode.value="NEW";
					document.forms[0].submit();
				});
				
				
				//return true;
			}else{
				$(reqid).show();
				swal("Error", "Something Went Worng! ", "error");
				/*alert('Something Went Wrong');
				return false;*/
			}
	    	}
		})
    }else if(result.Status == "0"){
    	
    	swal("Patient Already Approved")
		.then((value) => {
			//$("#loader-6").css("display","none");
			document.forms[0].strHiddenDeptCode.value=document.forms[0].deptUnitName.value ;
			document.forms[0].hmode.value="NEW";
			document.forms[0].submit();
		});
    }
    	}
	});
}


/*$('#spinnerDiv')
.hide()  // Hide it initially
.ajaxStart(function() {
    $(this).show();
})
.ajaxStop(function() {
    $(this).hide();
})
;*/