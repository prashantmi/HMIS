function ADD(e){
	console.log("ADD");
	document.forms[0].hmode.value="ADD";
	document.forms[0].submit();
}


function viewTemplate(){
	
	var count =0;
	  //var n = $( "input:checked" ).length;
	  //console.log(n);
	var checkCount=0;
	var check=document.getElementsByName("chk");
	for(var i=0;i<check.length;i++){
		if(check[i].checked==true)
			checkCount++;
	}

if(checkCount == 1){
	  var checkboxes = document.getElementsByName('chk');
	  var vals = "";
	  for (var i=0, n=checkboxes.length;i<n;i++) 
	  {
		//  if(count == 0 && count == 1){ 
			  
	      if (checkboxes[i].checked) 
	      {
	          vals = checkboxes[i].value;
	          ++ count ; 
	      }
	  
	  /*}else{
		  alert("Please Select Only One CheckBox");
		  break ;
	  }*/
	 }
}else{
	alert("Please Select Only One CheckBox");
	return false;
}
	  console.log(vals);

	var ViewTemplateData ={
		"strTemplateCode" : vals.split('#')[0] ,
		"strHospitaCode"  :	vals.split('#')[2] , 
	    "strDeptCode":	vals.split('#')[1] 
	};
var data = JSON.stringify(ViewTemplateData);
	$('#modalTemplateName').html('<span style="color: DodgerBlue">Template Name :</span> '+ vals.split('#')[6]);
	$('#modaldeptName').html('<span style="color: DodgerBlue">Dept Name : </span>'+ vals.split('#')[5]);
	$.ajax({url:'/HISDRDESK/services/restful/Template/getHtml/',type:'POST',data:{JsonData:data},success:function(result)
    	{
		//alert(result.strData);
		console.log(result.strData);
		$('#printPrescriptionModal .modal-body').html(result.strData);
		$('#printPrescriptionModal').modal('show');
		/*if(localStorage.getItem("FormattedHTML1"))
			localStorage.removeItem("FormattedHTML1");
			localStorage.setItem("FormattedHTML1", result.strData);

			$('#printPrescriptionModal .modal-body').prepend('<iframe id="printPrescFrameId" style="width:100%;height:80vh;" src="/HISDRDESK/new_opd/transaction/OPDTemplateMstAction.cnt?hmode=PRINTPRESCVIEW"></iframe>');
			$('#printPrescriptionModal').modal('show');
			$("#printPrescriptionModal").find(".modal-backdrop").css({"z-index": "-1"})*/
}
	});
}

$(document).ready(function(){
	
	$("#printPrescriptionModal").on('hide.bs.modal', function () {
		$('#printPrescFrameId').remove();
	 });
});
function deleteRecord(){

	
	var count =0;
	  //var n = $( "input:checked" ).length;
	  //console.log(n);
	var checkCount=0;
	var check=document.getElementsByName("chk");
	for(var i=0;i<check.length;i++){
		if(check[i].checked==true)
			checkCount++;
	}

if(checkCount == 1){
	  var checkboxes = document.getElementsByName('chk');
	  var vals = "";
	  for (var i=0, n=checkboxes.length;i<n;i++) 
	  {
		//  if(count == 0 && count == 1){ 
			  
	      if (checkboxes[i].checked) 
	      {
	          vals = checkboxes[i].value;
	          ++ count ; 
	      }
	  
	  /*}else{
		  alert("Please Select Only One CheckBox");
		  break ;
	  }*/
	 }
}else{
	alert("Please Select Only One CheckBox");
	return false;
}


	document.forms[0].strTemplateDeleteParm.value=vals;

	document.forms[0].hmode.value="DELETE";
	document.forms[0].submit();
	
}

function MODIFY(e){
	var count =0;
	var checkCount=0;
	var check=document.getElementsByName("chk");
	for(var i=0;i<check.length;i++){
		if(check[i].checked==true)
			checkCount++;
	}

if(checkCount == 1){
	  var checkboxes = document.getElementsByName('chk');
	  var vals = "";
	  for (var i=0, n=checkboxes.length;i<n;i++) 
	  {
		//  if(count == 0 && count == 1){ 
			  
	      if (checkboxes[i].checked) 
	      {
	          vals = checkboxes[i].value;
	          ++ count ; 
	      }
	  
	  /*}else{
		  alert("Please Select Only One CheckBox");
		  break ;
	  }*/
	 }
}else{
	alert("Please Select Only One CheckBox");
	return false;
}
	  console.log(vals);

	var ViewTemplateData ={
		"strTemplateCode" : vals.split('#')[0] ,
		"strHospitaCode"  :	vals.split('#')[2] , 
	    "strDeptCode":	vals.split('#')[1] 
	};
var data = JSON.stringify(ViewTemplateData);
	
	
	console.log("MODIFY");
	document.forms[0].hmode.value="MODIFY";
	document.forms[0].submit();
}