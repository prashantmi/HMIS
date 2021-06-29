function submitOTDetailsOnValidate()
{
	AddRowToTableOT();

}

function AddRowToTableOT()
{
	
	//if(document.getElementsByName('diagnosisRecordStatus')[0].value!="1")
	//{
	//alert(document.getElementsByName("operations")[0].value);
 
	/*if((document.getElementsByName("operations")[0].value="-1")
	{
		alert("please select atleast one operation to add");
		return;
	}*/
	
	if(document.getElementsByName("operations")[0].value!="-1")
	{
	//if(validateRow()=="true")
	//{
		
	var resp=document.getElementsByName('comboOptionString')[0].value;
	var respDiagnosisSite=document.getElementsByName('comboDiagnosisSite')[0].value;
	var nRow=0;
	var tableObj=document.getElementById('operationTable');
	var numRows=tableObj.rows.length;
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id.split("#")[1];
	}
	else
	{
		nRow=numRows;
	}
	
	   var tabRow1=tableObj.insertRow(numRows);
		tabRow1.id="otRow#"+(parseInt(nRow)+1);
	
		var td7=document.createElement("TD");
		var td8=document.createElement("TD");
		var td9=document.createElement("TD");
		var td10=document.createElement("TD");
		var td11=document.createElement("TD");
		var td12=document.createElement("TD");
		var td13=document.createElement("TD"); 
		
		var operationDate = document.getElementsByName('operationDate')[0].value;
		
		var operationCombo = document.getElementsByName('operations')[0];
		if(operationCombo.selectedIndex=="0")
		{
			var operation = "";
			var operationCode = "";
		}
		else
		{
		var operation = operationCombo.options[operationCombo.selectedIndex].text;
		var operationCode = document.getElementsByName("operations")[0].value;
		}
		
	    var surgeonCombo = document.getElementsByName('surgeons')[0];
	    if(surgeonCombo.selectedIndex=="0")
	    {
	    	var surgeonName = "";
	    	var surgeonCode ="";
	    }
	    else
	    {	
	    var surgeonName = surgeonCombo.options[surgeonCombo.selectedIndex].text;
	    var surgeonCode = document.getElementsByName("surgeons")[0].value;
	    }
	    
	    var operationFindings = document.getElementsByName("search_2")[0].value;
	   
		
		td8.innerHTML="<div align='center'>"
			+"<input name='selOperationDate' value='"+operationDate+"' type='hidden'></input>"
			+operationDate +"</div>";
		td8.className='tdfont';																													
		td8.colspan="1";
		
		

		td9.innerHTML="<div align='center'>"
			+"<input name='selOperationName' value='"+operation+"' type='hidden'></input>"
			+"<input name='selOperationCode' value='"+operationCode+"' type='hidden'></input>"
			+operation +"</div>";
		td9.className='tdfont';																													
		td9.colspan="1";
		

		td10.innerHTML="<div align='center'>"
			+"<input name='selSurgeonName' value='"+surgeonName+"' type='hidden'></input>"
			+"<input name='selSergeonCode' value='"+surgeonCode+"' type='hidden'></input>"
			+surgeonName +"</div>";
		td10.className='tdfont';																													
		td10.colspan="1";


		td11.innerHTML="<div align='center'>"
			+"<input name='selOperativeFindings' value='"+operationFindings+"' type='hidden'></input>"
			+operationFindings +"</div>";
		td11.className='tdfont';																													
		td11.colspan="1";
		
		
		td12.className='tdfont';
		td12.colspan="1";
		td12.innerHTML="<div align='center'><img src='/HIS/hisglobal/images/avai/minus.gif' onClick='deleteDiagRow("+(parseInt(nRow)+1)+")'></div>";
	
		tabRow1.appendChild(td8);
	
		tabRow1.appendChild(td9);
		tabRow1.appendChild(td10);
		tabRow1.appendChild(td11);
		tabRow1.appendChild(td12);
		
		document.getElementsByName('search_2')[0].value="";
		document.getElementsByName('operationDate')[0].value="";
		document.getElementsByName('operations')[0].value="-1";
		document.getElementsByName('surgeons')[0].value="-1";
	
	}	
	
}

function validateRow()
{
	var Code,name;
	var valid="true";
	var tableObj=document.getElementById('operationTable');
	rowcount=tableObj.rows.length;
	row =0;
	//alert(rowcount);
		//elemDiagnosisName = "txt-snomed-ct-search_1";
	elemDiagnosisName = "operations";
		name = document.getElementsByName(elemDiagnosisName)[0];
		//alert(name);
		elemDiagnosisCode="snomedCTDiagnosisCode";
		
		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row]
		
		if(isEmpty(name,"Diagnosis Name") && comboValidation(diaTypeCode,"Diagnosis Type"))
			{
			if(rowcount>2)
			{
				for(var i=1;i<rowcount-1;i++)
				{
					if(trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
					{
						alert(trimData(document.getElementsByName(elemDiagnosisName)[row].value)+" Already Added");
						valid="false";
						break;
					}
				}
			}

		}
		
		
	//	alert(valid);
		return valid;	
}


function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}


function setOperationName()
{
	//var operationCode=document.getElementsByName('operations')[0];
	//document.getElementsByName('operations')[index].value=operationCode.options[operationCode.selectedIndex].text;
	var operationCombo = document.getElementsByName('operations')[0];
	if(operationCombo.selectedIndex=="0")
	{
		var operation = "";
		var operationCode = "";
	}
	else
	{
	var operation = operationCombo.options[operationCombo.selectedIndex].text;
	//alert(operation);
	document.getElementsByName("selOperationName")[0].value = operation;
	var operationCode = document.getElementsByName("operations")[0].value;
	document.getElementsByName("operationCode")[0].value = operationCode;

	
	}
	
	if(chkDuplicates(operation))
	{

 $('#operationName').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
	 		'  '+
	 		'<span class="text">'+operation+' </span>'+
	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	 		'</button></label>');
 $('#operationName').append('&nbsp;&nbsp;');
 
 $('#operationCode').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
 	 		'  '+
 	 		'<span class="text">'+operationCode+' </span>'+
 	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
 	 		'</button></label>');
	 $('#operationCode').append('&nbsp;&nbsp;');
	 var operationCombo = document.getElementsByName('operations')[0].value = "-1";
}
	else
		{
		  var operationCombo = document.getElementsByName('operations')[0].value = "-1";
		}
}

function chkDuplicates(operation)
{
	//alert("duplicate chk called");
	var pervOperations = document.getElementsByName('prevOperation'); 
	//alert(pervOperations.length);   
	var tmp = "";
	for(var i=0;i<(pervOperations.length-1);i++)
	{
	  var otName = pervOperations[i].value;
	  var otName1 = otName.split(',');
	  //alert(otName1.length);
	  for(var j = 0;j<=(otName1.length-1);j++)
		  {
		     var k = otName1[j];
		    // alert("prevOT:"+k);
		     var finalOperationsToSave = operation.replace(/\s/g, '')
		    // alert("NewOT:"+finalOperationsToSave.trim().toUpperCase());
		     if(k.trim().toUpperCase()===finalOperationsToSave.trim().toUpperCase())
		    	 {
		    	   tmp=1;
		    	   alert(operation+" Already Saved");
		    	   return false;
		    	 }
		  }
	  
	}
	
	var AddedOperations = document.getElementById('operationName').textContent.trim();
	var finalOperations = AddedOperations.replace(/\x/g,",");
	var finalOperationsToSave1 = finalOperations.replace(/\s/g, '').slice(0,-1);
	//alert(finalOperationsToSave);
	var addedOT = finalOperationsToSave1.split(',');
	for(var n = 0;n<=(addedOT.length-1);n++)
		{
		  var m = addedOT[n];
		  var finalOperationsToSave = operation.replace(/\s/g, '')
		    // alert("NewOT:"+finalOperationsToSave.trim().toUpperCase());
		     if(m.trim().toUpperCase()===finalOperationsToSave.trim().toUpperCase())
		    	 {
		    	   tmp=1;
		    	   alert(operation+" Already Added");
		    	   return false;
		    	 }
		
		}
	return true;
}

function setSurgeonName()
{
	//var surgeonCode=document.getElementsByName('surgeons')[0];
	//document.getElementsByName('surgeons')[index].value=surgeonCode.options[surgeonCode.selectedIndex].text;
	 var surgeonCombo = document.getElementsByName('surgeons')[0];
	    if(surgeonCombo.selectedIndex=="0")
	    {
	    	var surgeonName = "";
	    	var surgeonCode ="";
	    }
	    else
	    {	
	    var surgeonName = surgeonCombo.options[surgeonCombo.selectedIndex].text;
	    //alert(surgeonName);
	    document.getElementsByName("selSurgeonName")[0].value = surgeonName;
	    var surgeonCode = document.getElementsByName("surgeons")[0].value;
	    document.getElementsByName("surgeonCode")[0].value = surgeonCode;
	    
	    }
	    
	    
	    if(checkDuplicateSurgeons(surgeonName))
	    	{
	    	 $('#surgeonName').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
	 	 	 		'  '+
	 	 	 		'<span class="text">'+surgeonName+' </span>'+
	 	 	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	 	 	 		'</button></label>');
	 		 $('#surgeonName').append('&nbsp;&nbsp;');
	 		 
	 		 $('#surgeonCode').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
	 		 	 		'  '+
	 		 	 		'<span class="text">'+surgeonCode+' </span>'+
	 		 	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
	 		 	 		'</button></label>');
	 			 $('#surgeonCode').append('&nbsp;&nbsp;');
	 			 
	 			var surgeonCombo = document.getElementsByName('surgeons')[0].value = "-1";
	    	}
	    else
	    	{
	    	var surgeonCombo = document.getElementsByName('surgeons')[0].value = "-1";
	    	}
}

function checkDuplicateSurgeons(surgeonName)
{
   var surgeons = document.getElementById('surgeonName').textContent.trim();
	
	var finalSurgeons = surgeons.replace(/\x/g,",");
	var finalSurgeonsToSave = finalSurgeons.replace(/\s/g, '').slice(0,-1);
	
	var addedSurgeons = finalSurgeonsToSave.split(',');
	for(var n = 0;n<=(addedSurgeons.length-1);n++)
		{
		  var m = addedSurgeons[n];
		  var finalSurgeonsToSave1 = surgeonName.replace(/\s/g, '')
		    // alert("NewOT:"+finalOperationsToSave.trim().toUpperCase());
		     if(m.trim().toUpperCase()===finalSurgeonsToSave1.trim().toUpperCase())
		    	 {
		    	   tmp=1;
		    	   alert(surgeonName+" Already Added");
		    	   return false;
		    	 }
		
		}
	return true;
}
function validate_ENC_OT_DTL()
{
	//alert("1");
	var currDate = document.getElementsByName("entryDate")[0].value;	
	//alert(currDate);
	
	var selectedDate =document.getElementsByName("operationDate")[0].value;
	//alert(selectedDate);
	var d = new Date(selectedDate);
	//alert(d);
	
	
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

if (month.length < 2) month = '0' + month;
if (day.length < 2) day = '0' + day;

const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                  ];
var mon = monthNames[d.getMonth()];

var treatmentStartDate = [day, mon, year].join('-');

//alert([day, mon, year].join('-'));

//return [year, month, day].join('-');
	
	
	//var selected = convertStrToDate(myDate, 'dd-Mon-yyyy');
	//alert(selected);
	
	var d1 = Date.parse(currDate);
	var d2 = Date.parse(treatmentStartDate);
	
	//alert("d1=="+d1+"==d2=="+d2);
	
	if (d1 < d2) {
        alert ("Operation Date Must be less than or equals Today Date");
 		document.getElementById("operationDate").focus(); 
 	
 	 return false;
 	}
	
	
	//alert(document.getElementById('reasonOfVisitAdded').textContent);
	var operations = document.getElementById('operationName').textContent.trim();
	
	var finalOperations = operations.replace(/\x/g,",");
	var finalOperationsToSave = finalOperations.replace(/\s/g, '').slice(0,-1);
	//alert(finalOperationsToSave);
	
   var operationsCode = document.getElementById('operationCode').textContent.trim();
	
	var finalOperationsCode = operationsCode.replace(/\x/g,",");
	var finalOperationsCodeToSave = finalOperationsCode.replace(/\s/g, '').slice(0,-1);
	//alert(finalOperationsCodeToSave);
	
	document.getElementsByName("selOperationName")[0].value = finalOperationsToSave;
	document.getElementsByName("operationCode")[0].value = finalOperationsCodeToSave;
	
	//For surgeons
   var surgeons = document.getElementById('surgeonName').textContent.trim();
	
	var finalSurgeons = surgeons.replace(/\x/g,",");
	var finalSurgeonsToSave = finalSurgeons.replace(/\s/g, '').slice(0,-1);
	//alert(finalSurgeonsToSave);
	
    var surgeonsCode = document.getElementById('surgeonCode').textContent.trim();
	
	var finalSurgeonsCode = surgeonsCode.replace(/\x/g,",");
	var finalSurgeonsCodeToSave = finalSurgeonsCode.replace(/\s/g, '').slice(0,-1);
	//alert(finalSurgeonsCodeToSave);
	
	document.getElementsByName("selSurgeonName")[0].value = finalSurgeonsToSave;
	document.getElementsByName("surgeonCode")[0].value = finalSurgeonsCodeToSave;
	
	document.getElementsByName("operativeFindings")[0].value=document.getElementsByName("search_2")[0].value;
	//alert(document.getElementsByName("operativeFindings")[0].value);
	
	var operativeFindingsLength = document.getElementsByName("operativeFindings")[0].value.length;
	 if(operativeFindingsLength>2000)
	 {
        alert("Maximum 2000 characters are allowed in Operative Findings");
        document.getElementsByName("search_2")[0].focus();
        return false;
	 }
	 
	 document.getElementsByName("operationSummary")[0].value=document.getElementsByName("search_3")[0].value;
		//alert(document.getElementsByName("operationSummary")[0].value);
		
		var operationSummaryLength = document.getElementsByName("operationSummary")[0].value.length;
		 if(operationSummaryLength>2000)
		 {
	        alert("Maximum 2000 characters are allowed in Operation Summary");
	        document.getElementsByName("search_3")[0].focus();
	        return false;
		 }
	
	return true;
}	

function hideOperationRow(idx)
{
	//alert(idx);
	var tableObj=document.getElementById('operationTable');
	var temp=document.getElementById("otRow#"+(parseInt(idx)+2));
	
	temp.style.display ="none";
	return true;
}

function deleteOperations(slno,idx)
{
	//alert(slno);
	
	var answer = window.confirm("Please confirm to delete OT Details")
	if (answer) {
		$.ajax({
			url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_OT_DTL.cnt?hmode=DELETE_PREV_PAT_OPERATIONS_AJAX&slno="+slno+""),  
		    type : 'GET',
		    datatype: "json",
		    async : false,
			success: function(data)
			{
				hideOperationRow(idx);
				
		      },
			
		      error: function(data)
		      {
		    	    alert('request failed :');
		    	}

			});
	}
	else {
	    return false;
	}
	
	
	
}

