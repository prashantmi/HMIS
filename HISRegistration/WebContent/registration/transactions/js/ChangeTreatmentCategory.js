
var departmentList="";
var departmentUnitList="";
var treatmentCategory={
processGeneralNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	if(elementNode!=null){
		for(var i=0;i<elementNode.childNodes.length;i++ )
		{
			var optionNode=elementNode.childNodes[i];
			optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
		}
	}

	if(document.getElementsByName(elementName).length!=0)
			document.getElementsByName(elementName)[0].innerHTML=optionText;
	
	
	
  }
};


//Submit form if valid
/*$('#submitId').click(function(e){
	if(validateSecondaryCategoryChange())
	{
	$("#ChangeTreatmentCategory").attr('action',"/HISRegistration/registration/transactions/SAVEChangeTreatmentCategory.action");

		$('#ChangeTreatmentCategory').submit();
		}
	});*/

//Clear Form Data
$('#clearId').click(function(e){
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
	
});

//Clear Initial Data
$('#initialClearId').click(function(e){
	
	
	$("#ChangeTreatmentCategory").attr('action',"/HISRegistration/registration/transactions/ChangeTreatmentCategory.action");
	$('#ChangeTreatmentCategory').submit();
	
});


function validateSecondaryCategoryChange(){
	var a = 0;
	var b = 0;
	var c =0;
	//alert($("#isOPDFlagId").is(':checked'));
	//alert($("#isOPDFlagId1").is(':checked'));
	if($("#isOPDFlagId").is(':checked') || $("#isOPDFlagId1").is(':checked')|| $("#isOPDFlagId10").is(':checked'))
		{
		//alert("1");
	for(i=0;i<document.getElementsByName('selectEpisode').length;i++){
		if(document.getElementsByName('selectEpisode')[i].checked==true){
			a++;
			if (document.getElementsByName('newSecCatCode')[i].options[document.getElementsByName('newSecCatCode')[i].selectedIndex].value == "-1" ) {
				alert("Please Select Treatment Category");
				document.getElementsByName('newSecCatCode')[i].focus();
				return false;
			}
			else if (document.getElementsByName('arrRemarks')[i].value==null || document.getElementsByName('arrRemarks')[i].value==""){
				alert("Please Enter Reason");
				document.getElementsByName('arrRemarks')[i].focus();
				return false;
			}
		}			
	}
	
	for(i=0;i<document.getElementsByName('revokeChk').length;i++)
	{
		if(document.getElementsByName('revokeChk')[i].checked==true)
		{
			b++;
			if(document.getElementsByName("arrRemarks")[i].value=="")
			{
				alert("Please Enter Reason");
				document.getElementsByName('arrRemarks')[i].focus();
				return false;
			}

			
		}
	}
	if (a == 0 && b == 0)
	{
		alert('Please Select Atleast One Record');
		return false;
	}
	else 
	{
		//alert("else");
		return true;
	}
}
	else
	{
		
		if(document.getElementsByName('selectEpisode')[0].checked==true || document.getElementsByName('revokeChk')[0].checked==true )
		{	//alert("in");
			c++;
			
			if (document.getElementsByName('newSecCatCode')[0].options[document.getElementsByName('newSecCatCode')[0].selectedIndex].value == "-1" ) {
				alert("Please Select Treatment Category");
				document.getElementsByName('newSecCatCode')[0].focus();
				return false;
			}
			else if (document.getElementsByName('remarks')[0].value==null || document.getElementsByName('remarks')[0].value==""){
				alert("Please Enter Reason");
				document.getElementsByName('remarks')[0].focus();
				return false;
			}
			if (c == 0)
			{
				alert('Please Select Atleast One Record');
				return false;
			}
			else 
			{
				//alert("else");
				return true;
			}			
		}
		
	}
	
	
}
function enableEpisode(obj)
{
	//alert("inside enable episode function");
		var index=obj.title;
	var i=parseInt(index);
	
	var idx="";
	
	//var elementArraylength=document.getElementsByName('selectEpisode').length;
					
			if(document.getElementsByName('selectEpisode')[i].checked==true)
			{	
				//document.getElementById(idDivDateText).style.display ="none";
				document.getElementsByName('newSecCatCode')[i].disabled=false;
				document.getElementsByName('arrValidUpto')[i].disabled=false;
				document.getElementsByName('arrRemarks')[i].disabled=false;
				if(document.getElementsByName('newSecCatCode')[i].value!="-1")
				{
					idx="#arrValidUptoId_"+[i];
					document.getElementsByName('newSecCatCode')[i].disabled=true;
					/*$(idx).datepicker({dateFormat: 'dd-M-yy'}).datepicker();*/
					 $(idx).DatePicker({format: 'd-M-Y',default_position :'below', onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} });
					
					//document.getElementsByName('newSecCatCode')[i].datepicker({dateFormat: 'dd-M-yy'}).datepicker();
					//document.getElementById(idDivDateControl).style.display ="block";
				}
				
				document.getElementsByName("revokeChk")[i].checked=false;
			}
			else
			{
				document.getElementsByName('newSecCatCode')[i].disabled=true;
				document.getElementsByName('arrValidUpto')[i].disabled=true;
				document.getElementsByName('arrRemarks')[i].disabled=true;
			//document.getElementById(idDivDateControl).style.display ="none";
			//document.getElementById(idDivDateText).style.display ="block";
			}
			
			
	
}
function enableIPDEpisode(obj)
{
	
			if(document.getElementsByName('selectEpisode')[0].checked==true)
			{	
				document.getElementsByName('newSecCatCode')[0].disabled=false;
				document.getElementsByName('remarks')[0].disabled=false;
				if(document.getElementsByName('newSecCatCode')[0].value!="-1")
				{
				
					document.getElementsByName('newSecCatCode')[0].disabled=true;
				
				}
				
				document.getElementsByName("revokeChk")[0].checked=false;
			}
			else
			{
				document.getElementsByName('newSecCatCode')[0].disabled=true;
				document.getElementsByName('remarks')[0].disabled=true;
			}
			
			
	
}
function enableRevokeEpisode(obj)
{	var index=obj.title;
	var i=parseInt(index);
	
	document.getElementsByName('arrValidUpto')[i].value=document.getElementsByName("hiddenValidUptoDate")[i].value;
	if(document.getElementsByName("revokeChk")[i].checked==true)
	{
	document.getElementsByName('newSecCatCode')[i].disabled=true;
	document.getElementsByName('arrValidUpto')[i].disabled=true;
	document.getElementsByName('arrRemarks')[i].disabled=false;
	document.getElementsByName("selectEpisode")[i].checked=false;
	}
else
{
	document.getElementsByName('newSecCatCode')[i].disabled=true;
	document.getElementsByName('arrValidUpto')[i].disabled=true;
	document.getElementsByName('arrRemarks')[i].disabled=true;
}
}

function enableIPDRevokeEpisode(obj)
{	
	if(document.getElementsByName("revokeChk")[0].checked==true)
	{
	document.getElementsByName('newSecCatCode')[0].disabled=false;
		document.getElementsByName('remarks')[0].disabled=false;
	document.getElementsByName("selectEpisode")[0].checked=false;
	}
else
{
	document.getElementsByName('newSecCatCode')[0].disabled=true;
	document.getElementsByName('remarks')[0].disabled=true;
}
}

//Added by Vasu
//Submit form if valid
$('#submitId').click(function(e){
	//if(validateSecondaryCategoryChangeRequest())
	//{
		//alert("Validated");
	//$("#ChangeTreatmentCategory").attr('action',"/HISRegistration/registration/transactions/SAVEChangeTreatmentCategory.action");
	$("#ChangeTreatmentCategory").attr('action',"/HISRegistration/registration/transactions/SAVETREATMENTCATEGORYChangeTreatmentCategory.action");		
	sortandEncodebase64($("#ChangeTreatmentCategory"));
		$('#ChangeTreatmentCategory').submit();
		//}
	});

//Added by Vasu dated on 16.April.2018 for Client side Form Validation
$(document).ready(function(){
$('[name="letterReferenceNo"]').validatebox({

	required : true,
	validType : [ 'alphaNumeric']
	});

//var today=new Date().toLocaleFormat('%d-%b-%Y');
var options = {  year: 'numeric', month: 'short', day: 'numeric'};
var today=new Date().toLocaleDateString('en-GB', options);
$('#letterDateId').DatePicker({
	format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
}).val(today);

});

//function validate()
//{
//	if(document.getElementsByName('selectedEpisodeForTreatmentCategory').value == )
//	}
//End Vasu
function validateSecondaryCategoryChangeRequest(){
	var a = 0;
	var b = 0;
	var c =0;
	//alert($("#isOPDFlagId").is(':checked'));
	//alert($("#isOPDFlagId1").is(':checked'));
	/*if($("#isOPDFlagId").is(':checked') || $("#isOPDFlagId1").is(':checked')|| $("#isOPDFlagId10").is(':checked'))
		{*/
		//alert("1");
	//for(i=0;i<document.getElementsByName('selectedEpisodeForTreatmentCategory').length;i++){
		//if(document.getElementsByName('selectedEpisodeForTreatmentCategory')[i].checked==true){
			//a++;
			if (document.getElementsByName('secCatCode').options[document.getElementsByName('secCatCode').selectedIndex].value == "-1" ) {
				alert("Please Select Treatment Category");
				document.getElementsByName('secCatCode').focus();
				return false;
			}
			 if (document.getElementsByName('remarksArr').value==null || document.getElementsByName('remarksArr').value==""){
				alert("Please Enter Reason");
				document.getElementsByName('Remarks').focus();
				return false;
			}
		//}			
	//}
	
	/*for(i=0;i<document.getElementsByName('revokeChk').length;i++)
	{
		if(document.getElementsByName('revokeChk')[i].checked==true)
		{
			b++;
			if(document.getElementsByName("arrRemarks")[i].value=="")
			{
				alert("Please Enter Reason");
				document.getElementsByName('arrRemarks')[i].focus();
				return false;
			}

			
		}
	}*/
	//if (a == 0 && b == 0)
	if(a ==0)
	{
		alert('Please Add Atleast One Record');
		return false;
	}
	else 
	{
		//alert("else");
		return true;
	}
//}
	/*else
	{
		
		if(document.getElementsByName('selectedEpisodeForTreatmentCategory')[0].checked==true || document.getElementsByName('revokeChk')[0].checked==true )
		{	//alert("in");
			c++;
			
			if (document.getElementsByName('secCatCode')[0].options[document.getElementsByName('newSecCatCode')[0].selectedIndex].value == "-1" ) {
				alert("Please Select Treatment Category");
				document.getElementsByName('secCatCode')[0].focus();
				return false;
			}
			else if (document.getElementsByName('Remarks')[0].value==null || document.getElementsByName('remarks')[0].value==""){
				alert("Please Enter Reason");
				document.getElementsByName('Remarks')[0].focus();
				return false;
			}
			if (c == 0)
			{
				alert('Please Select Atleast One Record');
				return false;
			}
			else 
			{
				//alert("else");
				return true;
			}			
		}
		
	}*/
	
	
}





