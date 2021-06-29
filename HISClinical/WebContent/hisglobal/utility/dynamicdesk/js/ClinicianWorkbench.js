$(document).ready(function(){
 	//alert("ajax calling");
	var hospCode = document.getElementsByName("hospCode")[0].value;
	//alert(hospCode);
	$.ajax({url:'/HISClinicalServices/services/restful/opdDoctorDeskWebServices/getPatList?HospCode='+hospCode+'',
			async:true,
			beforesend : $('#maindiv').parent().append('<p id="prescriptionListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
			success:function(data){ 
				$('#prescriptionListMsg').remove();
				//alert(data);
				var str = '';
				if(Object.keys(data.pat_details).length > 0){
					for(var i=0;i<Object.keys(data.pat_details).length;i++){ 
						//alert(window.patcr =data.pat_details[i].PATIENTID);
						str+='<tr>'
						+'<td>'+data.pat_details[i].QUENO+'</td>'
						+'<td>'+data.pat_details[i].ISCONFIRMED+'</td>'
						+'<td>'+data.pat_details[i].PATNAME+'</td>'
						+'<td>'+data.pat_details[i].PATCRNO+'</td>'
						+'<td>'+data.pat_details[i].GENDERAGE+'/'+data.pat_details[i].PATPRIMARYCAT+'</td>'
						+'<td>'+data.pat_details[i].DEPARTMENT+'</td>'
						+'<td>'+data.pat_details[i].DEPARTMENTUNIT+'</td>'
						+'<td>'+data.pat_details[i].ROOM+'</td>'
						+'<td><button type="button" id="I_'+data.pat_details[i].LISTID+'" class="btn btn-success btn-xs" onClick="GotoPrescription(id);">Go to Prescription  </button></td>'
						+'</tr>'; 
				}
					
				}
				
				$('#PatList #opdpatient').append(str);
				 $('<thead></thead>').prependTo('#opdpatient').append($('#opdpatient tr:first'));
				    $("#opdpatient").DataTable();  
				    console.log("Interactive table is ready!"); 
				    $("#opdpatient").DataTable(); 
		
			},		
			error: function(data)
		      {
		    	    alert('request failed :');
		    	}
			});
			
		});


function GotoPrescription(id)
{
var str = id;
var st = str.split("_")[1];
alert(st);
var crno = st.split("@")[0];
var episodecode = st.split("@")[1];
var visitcode = st.split("@")[2];
var deptUnit = st.split("@")[3];
var roomCode = st.split("@")[4];
alert(crno);
alert(episodecode);alert(visitcode);alert(deptUnit);alert(roomCode);
$("#CrNoList").hide();
document.getElementById('CrNoGoText').value = '';
var url ="/HISClinical/emr/uniPagePrescription.cnt?patCrNo="+crno+"&episodeCode="+episodecode+"&episodeVisitNo="+visitcode+"&departmentUnitCode="+deptUnit+"&roomCode="+roomCode;
location.href=url;
//window.open(url,"_blank");
//document.getElementById("CrNoGoText").value="";
//parent.addTab('One Page Prescription','UNIPAGEPRESCRIPTION','14','1',false,"&patCrNo="+crno+"&episodeCode="+episodecode+"&episodeVisitNo="+visitcode+"&departmentUnitCode="+deptUnit+"&roomCode="+roomCode,'1');
}

function GoButton(){ 
		
	var crno = document.getElementById("CrNoGoText").value;
		
	$('#goButton').click(function(){
	if(document.getElementById("CrNoGoText").value!="")
		{
		$.ajax({url:'/HISClinicalServices/services/restful/opdDoctorDeskWebServices/getPatDetailsByCrNo?CrNo='+crno+'',
			async:true,
			beforesend : $('#maindiv').parent().append('<p id="prescriptionListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),
			success:function(data){ 
				//alert(crno);
				$('#prescriptionListMsg').remove();
				//alert(data);
				var str = '';
				if(Object.keys(data.pat_details).length > 0){
					for(var i=0;i<Object.keys(data.pat_details).length;i++){ 
						str+='<tr>'
						+'<td>'+data.pat_details[i].FIRSTNAME+'</td>'
						+'<td>'+data.pat_details[i].CRNO+'</td>'
						+'<td>'+data.pat_details[i].AGE+'</td>'
						+'<td><button type="button" id="I_'+data.pat_details[i].CRNO+'" class="btn btn-success btn-xs" onClick="GotoPrescription(id);">Go to Prescription  </button></td>'
						+'</tr>'; 
				}	
					  $("#CrNoList").show();
					  
				}
				$('#CrNoList #opdCrNoList').append(str);
			},
			error: function(data)
		      {
		    	    alert('request failed :');
		    	}
			});
		}
		
	});
		
	
}











