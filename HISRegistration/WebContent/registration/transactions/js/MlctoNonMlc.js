/**
 * @ author : Aadil
 */

var arrPatDtlGlobalJsonObj;

$(document).ready(function(){
	
	if($("#afterGoId").val()=="1"){
		$('[name="chks"]').validatebox({
			validType: 'requireRadio[\'input[name=chks]\', \'Please Select Atleast One Record To Save\']'
		});
	}
	
	$('#submitId').click(function(e){
		if($('#MlcToNonMlc').form('validate'))
		{
			sortandEncodebase64($("#MlcToNonMlc"));
			submitForm('SAVE');
		}else{
			return false;
		}
	});
});

function submitForm(mode) {
	document.forms[0].action = mode + "MlcToNonMlc.action";
	document.forms[0].submit();
}

function selectDeselect(objChk,index)
{
	var selRemarksId= "selRemarksId"+index;
	$('[name="selRemarks"]').validatebox({required : true});
	//$('.tempClass').attr("disabled", false);
	$('[name="selRemarks"]').attr("disabled", true);
	eval('$("#'+selRemarksId+'").attr("disabled", false)');
	eval('$("#'+selRemarksId+'").validatebox({required : true,validType: \'alphaNumSpecialChar\'})');
	$('[name="selRemarks"]')[index].value = "";
	$('[name="selRemarks"]')[index].disabled= false;
	
}
