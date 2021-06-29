
///////////////////////getting index of char on keypress////////////////////////////////////////////////
/*function openPopup(url,eventObj, height, width)
{
	alert("duplicate");
if(eventObj.type=="click" || eventObj.keyCode==13)
 {
	alert("reg :: openPopup()");
	height=300;
	width=700;
   	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 

 	if(!child.opener)
 		child.opener = self;
 	}
 return child
}*/

function getCursorIdex(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate();
		r.moveEnd('character', o.value.length);
		if (r.text == '') return o.value.length;
		return o.value.lastIndexOf(r.text);
	} else return o.selectionStart;
}

var veriyDocJSONArray=[];
var ageBoundRange=null;
var dupCardGeneration={submitByChoice:function (objchoice){
	$('#EpiError').hide();
		var objPatCrNo= document.getElementsByName("patCrNo")[0].value;
		var action 			= "/HISRegistration/registration/transactions/choiceDuplicateCardGeneration.action?choice="+objchoice+"&patCrNo="+objPatCrNo;
		$.ajax({url: action,type:"POST",async:true,dataType:"html" ,success:function(data)
			{
			var elem = document.getElementById("divEpisodeId");
			elem.innerHTML= data;
			if(data.length<='0' && objchoice!='5'){
				$('#submitId').hide();$('#clearId').hide();
				$('#_divDupPrintChoiceId').hide();
			}else{
				$('#submitId').show();$('#clearId').show();
			}
			$('[name="duplicateRenewRemarks"]').validatebox({required: true,validType: 'WithoutZeroSymbolandSpace'	});
			},error: function(errorMsg,textstatus,errorthrown) {
	            alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	        }
		});
		
}
};// end of patDtlModification Object Method

function displayAmount(obj,amount){
if(obj.checked==true)
{
	var value = (obj.value).split("#");
	if(value[1] == 1){
		$("#isDupFlag").hide();
	}
	else{
		$("#isDupFlag").show();
	}
document.getElementsByName('duplicateRenewCost')[0].value=parseInt(amount);
}
else
document.getElementsByName('duplicateRenewCost')[0].value=parseInt(amount);
}

$('[name="duplicateRenewRemarks"]').validatebox({
	required: true,	
	validType: 'WithoutZeroSymbolandSpace'
});


$('[name="requestBy"]').change(function(){
	var obj = document.getElementsByName("requestBy")[0];
	if(!obj)	return;
	if(obj.value=="1")
		{
		$('[name="requestRelation"]').validatebox({
			validType: ['selectCombo[0]']
		});
		$('[name="requestByName"]').validatebox({
			required: true,
			validType: 'alpha'
		});

		$('[name="requestByAddress"]').validatebox({
			required: true,
			validType: 'alpha'
		});

		
		}
	
});





//Submit form if valid


$('#submitId').click(function(e){
	
	if(!(document.getElementsByName('choice')[2].checked))
	{
		var count=0;
		//alert("before assignment");
		var len=document.getElementsByName("departmentToGenerateDupCard").length;
		if(len>0){
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("departmentToGenerateDupCard")[i].checked){
			count++;
			}
		}
	
		if(count==0){
			$('#EpiError').show();
			return false;
		}
		else
			$('#EpiError').hide();
		}

	}
	//alert($('#departmentToGenerateDupCard')[0].length);
	$("#DuplicateCardGeneration").attr('action',"/HISRegistration/registration/transactions/SAVEDuplicateCardGeneration.action");
	if($('#DuplicateCardGeneration').form('validate'))
		{
		    sortandEncodebase64($("#DuplicateCardGeneration"));
			$('#DuplicateCardGeneration').submit();
		}
			
	//e.preventDefault();
}); 
////////////////////////   jquery Validation   /////////////////

