/**
 * Page Load Initiation
 **/

$(document).ready(function(){
	
	 $("#patNotUsedCrNoId").attr('size',crNoSize);
	 $("#patNotUsedCrNoId").attr('maxlength',crNoSize+2);
	 
	 var minCrNoSize=10+hosCode.toString().length;
 
	 if($("#afterGoId").val()!=0){
		 $('[name="patNotUsedCrNo"]').validatebox({
				validType : ['numeric','minLength['+minCrNoSize+']','validCRNo']
			});
	 }
	 else{
		 $('[name="patNotUsedCrNo"]').validatebox({
				validType : null
			});
	 }
});

/**
 * Jquery Page validation Methods
 **/
(function(){
	
	$.extend($.fn.validatebox.defaults.rules, {  
		showValidation: {  
			validator: function(param){  
			   return false;  
			},  
	      message: '{0}'
	   }  
	 });	
}());


/**
 * Button Click Methods
 **/
$("#cancelId").click(function(){
	if($("#afterGoId").val()=="1")
		submitForm('CANCEL');
	else
		callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');
});

$("#clearId").click(function(){
	submitForm('CLEAR');
});
$("#imgGoCrNoTobeMergedId").click(function(){
	if($('#OfflineMergeCRNO').form('validate')) 
		return submitFormOnValidate(validateAdditionOfMergedCRNumber(),'GO');
	else
		return false;
});
$("#patNotUsedCrNoId").keypress(function(event){
	if ( event.which != 13 ) {
		return validateNumericOnly(this,event);
	}
	else{
		if($('#OfflineMergeCRNO').form('validate')) 
			return submitFormOnValidate(validateAdditionOfMergedCRNumber(),'GO');
		else
			return false;
	}
});
/*$("#imgMinusDeleteId").click(function(){
	alert(this.value);
	$('[name="deleteCrNo"]')[0].value=this.value;
	submitForm('DELETEROW');
});
$("#imgMinusRevokeId").click(function(){
	alert(this.value);
	$('[name="deleteCrNo"]')[0].value=this.value;
	submitForm('REVOKE');
});*/
$("#submitId").click(function(){
	sortandEncodebase64($("#OfflineMergeCRNO"));
	return submitFormOnValidate(validateSave(),'SAVE');
});

/**
 * Validation Methods
 **/
function submitForm(mode) {
	document.forms[0].action = mode + "OfflineMergeCRNO.action";
	document.forms[0].submit();
}

function validateAdditionOfMergedCRNumber()
{
	var mainCRNo=$('[name="patCrNo"]')[0].value;
	var notUsedCRNo=$('[name="patNotUsedCrNo"]')[0].value;
	$('[name="concatedCrNo"]')[0].value="";
	
		if(mainCRNo==notUsedCRNo)
		{
			//alert("Inside main & Not Used CRNo Equal : Main CRNo-"+mainCRNo+"Not Used CR No"+notUsedCRNo);

			$('[name="patNotUsedCrNo"]')[0].focus();
			$('[name="patNotUsedCrNo"]').validatebox({
				validType : [ 'showValidation[\"You Cannot Add This CR Number\"]' ]
			});
			return false;
		}
		else 
		{
			for(var i=0;i<$('[name="hiddenNotUsedCRNo"]').length;i++)
			{
				//alert("Inside Same Hiddeb Not Used CR No Check : Not Used CR No"+notUsedCRNo+"-hiddenNotUsedCRNo CRNO-"+$('[name="hiddenMergedCRNo"]')[i].value);
				if(notUsedCRNo==$('[name="hiddenNotUsedCRNo"]')[i].value)
				{
					$('[name="patNotUsedCrNo"]')[0].focus();
					$('[name="patNotUsedCrNo"]').validatebox({
						validType : [ 'showValidation[\"'+notUsedCRNo+'is Already Added\"]' ]
					});
					return false;
				}
			}
			
			for(var i=0;i<$('[name="hiddenMergedCRNo"]').length;i++)
			{
				//alert("Inside Mergerd CR No Check : Not Used CR No"+notUsedCRNo+"-Already merged CRNO-"+$('[name="hiddenMergedCRNo"]')[i].value);
				if(notUsedCRNo==$('[name="hiddenMergedCRNo"]')[i].value)
				{
					$('[name="patNotUsedCrNo"]')[0].focus();
					$('[name="patNotUsedCrNo"]').validatebox({
						validType : [ 'showValidation[\"'+notUsedCRNo+'is Already Merged\"]' ]
					});
					return false;
				}
			}
		}
	return true;
} 


function validateSave()
{
	if(typeof $('[name="hiddenNotUsedCRNo"]')[0] == 'undefined')
	{
		$('[name="patNotUsedCrNo"]')[0].focus();
		$('[name="patNotUsedCrNo"]').validatebox({
			required:true,
			validType : [ 'showValidation[\"At Least Add a CR Number To Merge\"]' ]
		});
		return false;
	}
	return true;
}


function deleteRow(crNo)
{
	//alert(crNo);
	$('[name="deleteCrNo"]')[0].value=crNo;
	submitForm('DELETEROW');
}

function revokeCRNO(crNo,index)
{
	//alert(crNo);	alert(index);
	$('[name="revokedCrNo"]')[0].value=crNo;
	if($('[name="reason"]')[index].value=="")
	{
		$('[name="reason"]')[index].focus();
		$('[name="reason"]').validatebox({
			required:true,
			validType : [ 'showValidation[\"Please Enter The Revoke Reason\"]' ]
		});
		//alert("Please Enter The Revoke Reason");
		//document.getElementsByName("reason")[index].focus();
		return false;		
	}
	submitForm('REVOKE');
}
/*function submitFormOnValidate(flag,mode)
{
	if(flag)
	{
		submitForm(mode);
	}
	else{
		return false;
	}
	
}*/