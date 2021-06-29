var globalCounter = 0;

function getOPDRosterSchedule() 
{
//	alert("inside getOPDRosterSchedule");
	var action = "/HISRegistration/appointment/getOPDRosterScheduleApptConfigMst.action";
	var finalParaId=document.getElementsByName('actualParameterId')[document.getElementsByName('actualParameterId').length-1].value;
	//alert($('[name="appointmentForId"]').val());
	var data = {
			finalParaId:finalParaId,
			appointmentForId : $('[name="appointmentForId"]').val()
	  };
	$('#AppointmentShift').html("");
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				//alert(returnHTML);
				if(returnHTML!=""){
					$('#AppointmentShift').html(returnHTML);
					$("[name='currentDateAppt']").validatebox({
						required:true				
					}).blur(checkEmptyObjectAndPopulateWithData);					
					$("[name='priorAppt']").validatebox({
						required:true				
					}).blur(checkEmptyObjectAndPopulateWithData);
					$("[name='overBook']").validatebox({
						required:true				
					}).blur(checkEmptyObjectAndPopulateWithData);
					$("[name='portal']").validatebox({
						required:true				
					}).blur(checkEmptyObjectAndPopulateWithData);
					$("[name='opdApptSlots']").validatebox({
						required:false,
						validType : 'integer'
					}).blur(checkEmptyObjectAndPopulateWithData);
					$("[name='ipdApptSlots']").validatebox({
						required:false	,
						validType : 'integer'
					}).blur(checkEmptyObjectAndPopulateWithData);
				}
				else{
					AddDefaultScheduleHTML();
				}
				$('#AppointmentShiftHeader').show();
				$('#AppointmentShift').show();
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('getOPDRosterSchedule---' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
			}
			});
}

function checkEmptyObjectAndPopulateWithData(){
	var objName= this.name;
	var count=0;
	var val=this.value;
	for(var i=0;i<document.getElementsByName(objName).length;i++){
	 if(document.getElementsByName(objName)[i].value!=""){
		 count++;		 
	 }	
	}
	if(count==1){
		$("[name='"+objName+"']").val(val);
	}
} 
function AddDefaultScheduleHTML(){
	var strHTML="<div class='div-table-row listHeader' >";
	strHTML+="<div class='div-table-col' style='width: 10%;'>";
	strHTML+="<b>Shift</b>";
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 10%;'>";
	strHTML+="<b>Start Time(24hh:mi)</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 10%;'>";
	strHTML+="<b>End Time(24hh:mi)</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 25%;'>";
	strHTML+="<b>Days Of Week</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 9%;'>";
	strHTML+="<b>Current Date</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 8%;'>";
	strHTML+="<b>No. Of Prior Appointments</b>"; 
	strHTML+="</div>";
	/*strHTML+="<div class='div-table-col' style='width: 8%;'>";
	strHTML+="<b>Overbook</b>"; 
	strHTML+="</div>";*/
	strHTML+="<div class='div-table-col' style='width: 5%;'>";
	strHTML+="<b>Portal</b>"; 
	strHTML+="</div>";//AppointmentShift
	
	strHTML+="<div class='div-table-col' style='width: 5%;'>";
	strHTML+="<b>OPD</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 5%;'>";
	strHTML+="<b>IPD</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col' style='width: 8%;'>";
	strHTML+="<b>EMG</b>"; 
	strHTML+="</div>";
	
	strHTML+="<div class='div-table-col' style='width: 10%;display:none;'>";
	strHTML+="<b>VIP Slot Allowed</b>"; 
	strHTML+="</div>";
	strHTML+="<div class='div-table-col   width5' >";
	strHTML+="<img src='/HISRegistration/hisglobal/images/plus.png' id='ADDROW' >";
	strHTML+="</div>";
	strHTML+="</div>";
	$('#AppointmentShift').html(strHTML);
	$('#ADDROW').click(AddRowToTable2);
	AddRowToTable2();
}

//to move appt type to right ..	
	function moveRightSingle(firstName,secondName)
	{
				var firstMenuObj=document.getElementsByName(firstName)[0];
				var secondMenuObj=document.getElementsByName(secondName)[0];
				var ThirdMenuObj=document.getElementsByName("defaultApptTypeId")[0];
				     
				var val1 = "";
				var val2 = "";
				var t1 = 0;
				
				
				var totalElement=firstMenuObj.options.length;
				 for(var i=0;i<totalElement;i++)
				{
					if(firstMenuObj.options[i].selected)
					{
						val1 = firstMenuObj.options[i].value;
						val2 = firstMenuObj.options[i].text;			
					
						t1 = secondMenuObj.length;							
						secondMenuObj.length = (t1+1);	
						
						secondMenuObj.options[t1].value = val1;
						secondMenuObj.options[t1].text  = val2;			
															
	                }
	               if(secondName=="apptTypeIdSel")
	               { 
	               
	                	populateCombo(secondMenuObj,ThirdMenuObj);
	               }	
	            }
	            
			  deleteThis(secondMenuObj,firstMenuObj);			
	        
	}

	// to move appt type to left ..	
	function moveLeftSingle(secondName,firstName)
	{	
	           
				var val1 = "";
				var val2 = "";		
				var t1 = 0;
				//var t2=-1;
				var firstMenuObj=document.getElementsByName(firstName)[0];
				var secondMenuObj=document.getElementsByName(secondName)[0];
				var ThirdMenuObj=document.getElementsByName("defaultApptTypeId")[0];			
				var totalElement =secondMenuObj.options.length;
					
						
				for(var i=0;i<totalElement;i++)
				{
					if(secondMenuObj.options[i].selected)
					{
						val1 = secondMenuObj.options[i].value;
						val2 = secondMenuObj.options[i].text;
						
						t1 = firstMenuObj.length;							
						firstMenuObj.length = (t1+1);				
						
						firstMenuObj.options[t1].value = val1;
						firstMenuObj.options[t1].text  = val2;													
					}
				}
				
				deleteThis(firstMenuObj,secondMenuObj);
				if(secondName=="apptTypeIdSel")
				{ 
				   	populateCombo(secondMenuObj,ThirdMenuObj);
	             }		

	}
	
	function populateCombo(menu1,menucmb1)
	{
				var val1 = "";
				var val2 = "";
				var t1 = 0;
				
				var defaultVal=menucmb1.value;
				var flagFound=0;
				menucmb1.options.length=0;
				var totalElement=menu1.options.length;
				menucmb1.options.length=menu1.options.length;
				for(var i=0;i<totalElement;i++)
				{
				val1 = menu1.options[i].value;
				val2 = menu1.options[i].text;					
				menucmb1.options[i].value = val1;
				menucmb1.options[i].text  = val2;
				if(menucmb1.options[i].value==defaultVal)	
					flagFound=1;												
			    if(defaultVal==""||flagFound==0)
			   		menucmb1.value = menucmb1.options[0].value;		   
			   else
			   		menucmb1.value =defaultVal;
				}		  			
	}   
	
	
	function deleteThis(srcMenuObj,targetMenuObj)
	{	
		var t =0;
		var val1 = "";
		var val2 = "";		
		var len  = targetMenuObj.length;
		var len2 = srcMenuObj.length;					
		var a1 = new Array(len);
		var a2 = new Array(len);	
		var a3 = new Array(len2);	
		
		for(var i=0;i<len;i++)
		{		
			a1[i]= targetMenuObj.options[i].value;
			a2[i]= targetMenuObj.options[i].text;					
		}	
		
		for( i=0;i<len2;i++)
		{		
			a3[i]= srcMenuObj.options[i].value;
		}
			
		targetMenuObj.length = 0;	
		var counter = 0;	
		var k = 0;	
		var flag = 0;
				
		for(i=0;i<len;i++)
		{		
				flag = 0;
			for(k=0;k<len2;k++)
			{		
				if(a1[i]==a3[k])
				{	
					flag = 1;					
				}					
			}		
			if(flag==0)
			{
				targetMenuObj.length = (counter+1);
				targetMenuObj.options[counter].value = a1[i];
				targetMenuObj.options[counter].text  = a2[i]; 
				counter++;			
			}		
		}
		
	}
	
	// to delete row from multirow..
	function DeleteRows(rowId)
	{ 			
		var tr1;
	 	
	 	tr1=document.getElementById(rowId);
	 	tr1.parentNode.removeChild(tr1);	
	 		
	}
	
	// to generate unique id for each row..multirow..
	function getUniqueId()
	{
	    var dateObject = new Date();
	    var uniqueId =dateObject.getHours()+dateObject.getMinutes()+dateObject.getSeconds();
	    //alert("unique id is:::"+uniqueId);
	     return uniqueId;
	}

	// to make multirow...
 function AddRowToTable2()
	{
 	//followng var is used to make option list..
 	var temp=document.getElementById('hiddenSelForShifts').innerHTML;
 
 	var uniqueId=getUniqueId();
 	var rId="TR_SHIFT_NEW_" + uniqueId;
 	var tableId="AppointmentShift";//outer div id (main div)
 	    	
 	var $div = $("<div>", {id: rId, class: "div-table-row listData"});
 	$("#"+ tableId).append($div);
 	
 	var str="<input type='hidden' name='weekOfMonth'  value='0' ><select  name='shiftId' style='width:90%;' id='SHIFTID_"+uniqueId+"' >"+temp+"</select>";
 	$div = $("<div>", {class: "div-table-col",html:str}) ;
 	$('#'+ rId).append($div);
 	$div.width("10%");
 	
 	str="<input type='text' name='startTime' size='4' maxlength='5' style='min-width:4px'  onkeyup='insertColons(this)' id='STARTID_"+uniqueId+"'/>";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("10%");
		
 	
 	str="<input type='text' name='endTime' size='4' style='min-width:4px' maxlength='5' style='min-width:4px' onkeyup='insertColons(this)' id='ENDID_"+uniqueId+"'/>";
 	$div = $("<div>", {class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("10%");

 	str="<input type='hidden' name='shiftwiseSelectedDays' id='SHIFTWISESELECTEDDAYS_"+uniqueId+"' />"+
		"<input type='checkbox' name='chk_"+uniqueId+"' checked value='1' >Sun"+ 
		" <input type='checkbox' name='chk_"+uniqueId+"' checked value='2' >Mon"+ 
		" <input type='checkbox' name='chk_"+uniqueId+"' checked value='3' >Tue"+ 
		" <input type='checkbox' name='chk_"+uniqueId+"'  checked value='4' >Wed"+ 
		"<input type='checkbox' name='chk_"+uniqueId+"' checked value='5' >Thu"+ 
		" <input type='checkbox' name='chk_"+uniqueId+"' checked value='6' >Fri"+ 
		"<input type='checkbox' name='chk_"+uniqueId+"'  checked value='7' >Sat"; 
		$div = $("<div>", { class: "div-table-col ",html:str}); 
 	$('#'+rId).append($div);
 	$div.width("25%");
 	
 	str="<input type='text' name='currentDateAppt' size='4' style='min-width:4px' maxlength='3' onkeypress='return isNumberKey(event)'/>";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("9%");
 	
 	str="<input type='text' name='priorAppt' size='4' style='min-width:4px' maxlength='3' onkeypress='return isNumberKey(event)'/>";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("8%");
 	
 	/*str="<input type='text' name='overBook' size='4' style='min-width:4px' maxlength='4' onkeypress='return isNumberKey(event)'/>";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("8%");*/
 	
 	str="<input type='text' name='portal' size='4' style='min-width:4px' maxlength='3' onkeypress='return isNumberKey(event)'/>";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("5%");
 	/***/
 	str="<input type='text' name='opdApptSlots' size='4' style='min-width:4px' maxlength='3' />";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("5%");
 	str="<input type='text' name='ipdApptSlots' size='4' style='min-width:4px' maxlength='3' />";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("5%");
 	str="<input type='text' name='overBook' size='4' style='min-width:4px' maxlength='3' onkeypress='return isNumberKey(event)'/>";
 	$div = $("<div>", { class: "div-table-col ",html:str}) ;
 	$('#'+rId).append($div);
 	$div.width("8%");
 	/***/
 	str="<input type='hidden' name='isVipSlotAllowed' id='SHIFTWISESELECTEDRADIO_"+uniqueId+"' />"+"<input type='radio' name='rdo_"+uniqueId+"' value='1' checked>Yes<input type='radio' name='rdo_"+uniqueId+"' value='2' >No";
 	//str="<input type='checkbox' name='isVipSlotAllowed' />";
 	$div = $("<div>", { class: "div-table-col ",html:str, style: "display:none"}) ;
 	$('#'+rId).append($div);
 	$div.width("8%");
		
		
 	str="<img src='/HISRegistration/hisglobal/images/Minus.png' onclick=DeleteRows('TR_SHIFT_NEW_"+uniqueId+"')>";
		$div = $("<div>", { class: "div-table-col width5",html:str}) ;
 	$('#'+rId).append($div);
 	
 	applyMultirowValidation();
	}

 function applyMultirowValidation(){
 	
 	$("[name='startTime']").validatebox({
 		required:true				
 	});
 	$("[name='endTime']").validatebox({
 		required:true				
 	});
 	$("[name='currentDateAppt']").validatebox({
 		required:true				
 	});
 	$("[name='priorAppt']").validatebox({
 		required:true				
 	});
 	$("[name='overBook']").validatebox({
 		required:true				
 	});
 	$("[name='portal']").validatebox({
 		required:true				
 	}); 	
 }
 


//cancel event
function submitCancelAction(cnt)
{
	document.forms[0].action="cancelApptConfigMst.action";
	document.forms[0].submit();
}


//to ensure only numerals are entered
function isNumberKey(evt){
 var charCode = (evt.which) ? evt.which : event.keyCode;
	
 if(charCode==45){
	 //check to make sure only -1 should be entered
	 return true;
 }
 if (charCode > 31 && (charCode < 48 || charCode > 57))
     return false;
 return true;
}




// to save sel apptType Values and Labels..to be called before submit..
function getSelApptType()
{
	var selIds= new Array();
	var selText = new Array();
	var strSelIds="test";
	var strSelText ="test";
	
	var ddl = document.getElementsByName('apptTypeIdSel')[0];
	for (var i = 0; i < ddl.options.length; i++) {
		selIds[i] = ddl .options[i].value;
		selText[i] = ddl .options[i].text;
		if(strSelIds=="test")
		{
			strSelIds=ddl.options[i].value;	
		}
		else
		{
			strSelIds=strSelIds+'@@'+ddl.options[i].value;
		}
		
		if(strSelText=="test")
		{
			strSelText=ddl.options[i].text;	
		}
		else
		{
			strSelText=strSelText+'@@'+ddl .options[i].text;
		}
	}
	
	document.getElementsByName('selApptTypesText')[0].value=strSelText;
	document.getElementsByName('selApptTypesValues')[0].value=strSelIds;
	//alert(strSelText);
}

function insertColons(obj)
{
	//alert(obj.id);
	var newValWithColons;
	if(obj.value.length == 2)
	{
		//alert("in if");
		newValWithColons=obj.value.replace(obj.value,obj.value+":");
		//alert("after replace..."+newValWithColons);
		document.getElementById(obj.id).value=newValWithColons;
	}
	
	
	//return newValWithColons;
}
function CheckRecordsBeforeDelete(params)
{
	//alert("kaka");
	var fFlag = true;
	var Flag = true;
	var temp;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("|")[0])>0)
		{
			fFlag=false;
			break;
		}
		
	}	
	if(fFlag)
	{
	deleteRecords(_param);
	}
	else
		alert("The Selected record can not be deleted as appointment is pending for this record.");
	
}