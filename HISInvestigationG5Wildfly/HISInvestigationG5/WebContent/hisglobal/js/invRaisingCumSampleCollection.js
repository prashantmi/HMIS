function showInstructions5(inst,coll,tech)
{
	
	//alert(coll);
	
	//alert(tech);
	
	deleteTableInst();

	if(inst.split("$")[1]!='')
		{
		
	addRowToTableInst(inst);
	
		}
	if(coll.split("$")[1]!='')
	{
		
	addRowToTableInst(coll);
	
	}
	if(tech.split("$")[1]!='')
	{
		
		addRowToTableInst(tech);
		
	}
		
	
	popup("popUpDiv5");
	
	
	
	}
	



	function closeInstructions()
{
	popup("popUpDiv5");

	
	}
	
	
	
function addRowToTableInst(inst)
{
	
	//inst.replace("/\n/gm","<br>");
	//alert("d");
	//alert(inst);
		var nRow=0;
	var tableObj=document.getElementById('allInstructions');
	
	var tr=document.createElement("TR");
	 
	//tr.setAttribute("id","setPdf");
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 //inst=inst.replace("@#","<br>");
// alert(inst);
	td1.innerHTML="<div  align='left' >"+inst.split('$')[0]+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	td2.innerHTML="<div align='left' >"+inst.split('$')[1]+"</div>";   
	td2.className='tdfont';
	td2.colspan="1";
   
	
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
}







//
function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
document.getElementById("divLegends").style.display="none";
}



function openPopup(url, eventObj, height, width) {
	if (eventObj.type == "click" || eventObj.keyCode == 13) {
		child = window.open(url, 'popupWindow',
				'status=yes,scrollbars=yes,height=' + height + ',width='
						+ width + ',left=10,top=10');
		child.moveTo(250, 250);
		child.focus();

		if (!child.opener)
			child.opener = self;
	}
}

function ValidateSameCr(obj)
{
	
//alert("inside here");

	if(obj.checked)
	{
	
		document.getElementById('nextDiv1').style.display="";
		
              
	}
else
   	{
	//  document.getElementById('gob').style.display="";
    //	document.getElementById('cancel').style.display="";
        }
	 
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{
		
		var Value=obj.value;
		var splitValue=Value.split("#");

		document.getElementsByName('patCrNo')[0].value=splitValue[0];
		//alert(document.getElementsByName('patCrNo')[0].value);
		
		
		concatenateChkBoxVal =concatenateChkBoxVal.concat(splitValue[1]);
		concatenateChkBoxVal+=',';
		
		// alert("offlineAppoitMentDate VAlue"+document.getElementsByName('offlineAppoitMentDate')[0].value);
		 
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    		 
			    	alert("Please Select Same CR Number");
			    	document.getElementById('nextDiv1').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
			    	 
			  
				}
		}
	}

} 


//AJax Functions for checking Duplicacy
function chkSampleNoDuplicacyThroughAjax(obj)
{
	var sampleNo=obj.value;
//alert("inside duplicate check" + sampleNo);

	var sampleAreaCode=document.getElementsByName("areaCode")[0].value;
alert(sampleAreaCode);
		var isSampleNoPresent = chkSampleNo(sampleNo,sampleAreaCode);
		
		isSampleNoPresent=isSampleNoPresent=="true"?true:false;
		//alert(isSampleNoPresent);
		if(isSampleNoPresent)
		{
			alert("Sample Number already present");
			obj.value="";
			obj.focus();
			return false;
		}
		
		return true;
}

function chkSampleNo(sampleNo,sampleAreaCode)
{
	var flg = false;
	var isSampleNoPresent = false;
	var _mode = "AJX_DUPLICACY_SAMPLENO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt?hmode="+_mode+"&strSampleNo="+sampleNo+"&sampleAreaCode="+sampleAreaCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isSampleNoPresent = data;
			flg = true;
		},
    error: function(error)
    {
        //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
        	//alert("No Kitchen Found");
        //alert(error+"Error while populating Meal Time Information");
        isSampleNoPresent = false;
        flg = false;
    }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isSampleNoPresent;
}



//End AjaxFunctions



function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	
	document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
}

//Function for displaying selected Lab List
function showSelectedLabTestList(objSelectedLabTest)
{
	
	//alert("showselected"+objSelectedLabTest.value);
	var chkVal=objSelectedLabTest.value;
	// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential      //Please Ensure the corresponding Changes before changing this order
	var chkArr=chkVal.split("#");
	//Adding Row with the given values
	if(objSelectedLabTest.checked)
		{
		document.getElementById("td1"+chkVal).style.backgroundColor="#ff9999";
		document.getElementById("td2"+chkVal).style.backgroundColor="#ff9999";
		document.getElementById("td3"+chkVal).style.backgroundColor="#ff9999";
		
			AddRowToTable(chkArr);
			//Setting labCodeArray Value
			var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
			if(labTestCodeArray!=null&&labTestCodeArray!="")
				labTestCodeArray=labTestCodeArray+"@"+chkVal;
			else
				labTestCodeArray=chkVal;
			document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
			//alert("final test array"+labTestCodeArray);
		}
	else
	{
	document.getElementById("td1"+chkVal).style.backgroundColor="";
	document.getElementById("td2"+chkVal).style.backgroundColor="";
	document.getElementById("td3"+chkVal).style.backgroundColor="";
	
//to delete the test on de selecting the corresponding check box	
//	alert(chkArr[0]);
//	alert(chkArr[2]);
	deleteRow(1,chkArr[0],chkArr[2]);
	
	
	}
}


//Function For Check Displaying Test Group Details
//Function for displaying selected Lab List
//function showSelectedLabTestGroupList(objSelectedLabTest)
//{
//	var chkVal=objSelectedLabTest.value;
//	// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential      //Please Ensure the corresponding Changes before changing this order
	
////labtestGroupValues

//var concatenateChkBoxVal="";
	 
//	var cbs =document.getElementsByName('labtestGroupValues');
//	//alert(cbs.length);
//	//for(var i=0; i < cbs.length; i++) {
//   	//alert(cbs[cbs.length-1].value);
//    // }

//   	var chkArr=chkVal.split("#");
  	
//	var spiltValue=cbs[cbs.length-1].value.split("@");
//	for(var i=0; i < spiltValue.length; i++)
//		{
//		var finalGroupValue=spiltValue[i].split("#");
//       // alert(finalGroupValue[10]);
//       // alert(chkArr[10]);
//        if(finalGroupValue[0]+finalGroupValue[10]==chkArr[0]+chkArr[10])
      	 
//       	 {
//       	 //alert(i);
//       	 AddRowToTable(finalGroupValue);
//    		//Setting labCodeArray Value
//    		var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
//    		if(labTestCodeArray!=null&&labTestCodeArray!="")
//    			labTestCodeArray=labTestCodeArray+"@"+spiltValue[i];
//    		else
//    			labTestCodeArray+=spiltValue[i];
//    		document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
      	 
//    		//alert(document.getElementsByName('labTestCodeArray')[0].value);
//       	 }
		
		
//		}

//								//alert(document.getElementsByName('labtestGroupValues')[0].value);
	


//											//Adding Row with the given values
//											/* if(objSelectedLabTest.checked)
//															{
//																		AddRowToTable(chkArr);
//																			//Setting labCodeArray Value
//																	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
//																		if(labTestCodeArray!=null&&labTestCodeArray!="")
//				labTestCodeArray=labTestCodeArray+"@"+chkVal;
//																			else
//																					labTestCodeArray=chkVal;
//																		document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
//																	} */
//}



function showSelectedLabTestGroupList(objSelectedLabTest)
{
	var chkVal=objSelectedLabTest.value;
	// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential      //Please Ensure the corresponding Changes before changing this order
	
	
	if(objSelectedLabTest.checked)
		{
		
		document.getElementById("grptd1"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
		document.getElementById("grptd2"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
		document.getElementById("grptd3"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="#ff9999";
	
//labtestGroupValues

var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('labtestGroupValues');
	//alert(chkVal);
	//for(var i=0; i < cbs.length; i++) {
  	//alert(cbs[cbs.length-1].value);
   // }

  	var chkArr=chkVal.split("#");
  	
	var spiltValue=cbs[cbs.length-1].value.split("@");
	
	for(var i=0; i < spiltValue.length; i++)
		{
		var finalGroupValue=spiltValue[i].split("#");
      // alert(finalGroupValue[10]);
      // alert(chkArr[10]);
       if(finalGroupValue[0]+finalGroupValue[10]==chkArr[0]+chkArr[10])
      	 
      	 {
      	 //alert(i);
      	 AddRowToTable(finalGroupValue);
   		//Setting labCodeArray Value
   		var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
   		if(labTestCodeArray!=null&&labTestCodeArray!="")
   			labTestCodeArray=labTestCodeArray+"@"+spiltValue[i];
   		else
   			labTestCodeArray+=spiltValue[i];
   		document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
      	 
   		//alert(document.getElementsByName('labTestCodeArray')[0].value);
      	 }
		
		
		}
		}
	else
		{
		
		document.getElementById("grptd1"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="";
		document.getElementById("grptd2"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="";
		document.getElementById("grptd3"+chkVal.split("#")[0]+chkVal.split("#")[10]).style.backgroundColor="";
		
		
		 var cbs =document.getElementsByName('labtestGroupValues');
		//alert(cbs.length);
		var spiltValue=cbs[cbs.length-1].value.split("@");
		//alert(spiltValue);
		for(var i=0; i < spiltValue.length; i++)
			{
			
			var finalGroupValue=spiltValue[i].split("#");
			//alert(finalGroupValue[0]+finalGroupValue[10]+","+chkVal.split("#")[0]+chkVal.split("#")[10]);
			 if((finalGroupValue[0]+finalGroupValue[10])==(chkVal.split("#")[0]+chkVal.split("#")[10])) 
				 deleteGroupRow(1,chkVal.split("#")[0],chkVal.split("#")[10]);
			
			
			
			} 
		
		
		
		}
	
//alert(document.getElementsByName('labtestGroupValues')[0].value);
	


	//Adding Row with the given values
	/* if(objSelectedLabTest.checked)
		{
			AddRowToTable(chkArr);
			//Setting labCodeArray Value
			var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
			if(labTestCodeArray!=null&&labTestCodeArray!="")
				labTestCodeArray=labTestCodeArray+"@"+chkVal;
			else
				labTestCodeArray=chkVal;
			document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
		} */
}


$("#datepicker").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)));


function AddRowToTable(chkArr)
{
	// Logic to regain the Lab Test Values
	
	if(document.getElementById('tableSelectedLabTestId')!=null)
		{
		
	document.getElementById('tableSelectedLabTestId').style.display="";
		}
	
	
	// Lab Code
	//alert(chkArr);
	var labCode=chkArr[0];
	// Lab Name
	var labName=chkArr[1];
	// Test Code
	var testCode=chkArr[2];
	// Test Name
	var testName=chkArr[3];
	// Sample Combo String
	var sampleComboStr=chkArr[4];
	// Test Type
	var testType=chkArr[5];
	// is Appointment
	var isAppointment=chkArr[6];
	 
	// is Confidential
	var isConfidential=chkArr[7];
	// Sample Code
	var sampleCode=chkArr[8];
	// Priority
	var priority=chkArr[9];
	// Test GroupCode
	var testGroupCode=chkArr[10];
	// Test Group Type
	var testGroupType=chkArr[11];
	
	//Is Mandatory Req 
	var isMandatoryReq=chkArr[12];
	
var mandTextBoxCombo=chkArr[15];
var mandTextBoxComboName=chkArr[16];
		var finalMadCode=chkArr[17];
		var uomComboStr=chkArr[18];
		var containerComboStr=chkArr[19];
		var defaultVolume=chkArr[20];
		var sampleValues=chkArr[21];
		var sampnoconfig=chkArr[22];
		
		var testGrpCode=chkArr[23];
		var offlineAptNo=chkArr[24];	
		
		var pat=chkArr[28];
		var coll=chkArr[29];
		var tech=chkArr[30];
		
		//alert(pat.split("$")[1]);
	//	alert(coll.split("$")[1]);
		//alert(tech.split("$")[1]);
		document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value=mandTextBoxComboName;
		document.getElementsByName('finalMandCode')[0].value=finalMadCode;
	
	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	//alert("addrowtotable"+labTestCodeArray);
	var patCrNo=document.getElementsByName('patCrNo')[0].value;
	var labCodeSaved=document.getElementsByName("labCode");
	var testCodeSaved=document.getElementsByName("testCode");
	var divAptTagRow="aptTagRow_"+labCode+"_"+testCode;  //+labCode+testCode
	//alert(divAptTagRow);
	var size=0;
	if(typeof labCodeSaved!='undefined' && labCodeSaved!=null)
		size=labCodeSaved.length;
	if(size>0)
	{
		for(var i=0;i<size;i++)
			{
				if(labCodeSaved[i].value==labCode)
					{
						if(testCodeSaved[i].value==testCode)
						{
							alert("Laboratory :'"+labName+"' with corresponding Test :'"+testName+"' Already added. Please Select another Lab/Test");
							var cbs =document.getElementsByName('chkLabTest');
							 //alert(cbs);
							for(var i=0; i < cbs.length; i++) {
								if(cbs[i].checked)
						    	{
								var id=cbs[i].id;
								//alert(id);
								if(id==labCode+testCode)
									{
								      document.getElementById(id).checked = false;
						    	     }
							     }
						      }
							return false;
						
						}
					}
			}
		
	}
	var nRow=0;
	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
	var numRows=tableObj.rows.length;
	nRow=numRows;
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	//alert(nRow);

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	var td5=document.createElement("TD");
	var td6=document.createElement("TD");
	var td7=document.createElement("TD");
	var td8=document.createElement("TD");
	var td9=document.createElement("TD");
	var td10=document.createElement("TD");

	td1.innerHTML="<div align='left' id='checkOnSave'>"+testName+"<input type='hidden' name='labCode' value='"+labCode+"'/> </div>";   
	td1.className='tdfont';
	td1.colspan="1";
	td1.style.width='10%';
		
	td2.innerHTML="<div align='left'>"+labName+"<input type='hidden' name='testCode' value='"+testCode+"'/></div>";
	td2.className='tdfont';
	td2.colspan="1";
	td2.style.width='10%';
	
	//alert("testType="+testType);
	if(testType=="P") // Sample or Slide Based
		td3.innerHTML="<div align='left'>Patient Based Test</div>";
	else
		td3.innerHTML="<div align='left'><select name='sampleInfo' tabindex='1' onchange='setSampleCodeUsingAjax(this,"+(labCode)+","+(testCode)+","+(nRow)+")'  >"+sampleComboStr+"</select></div>";
		
	td3.className='tdfont';																													
	td3.colspan="1";
	td3.style.width='10%';
	
/* 	if(isMandatoryReq=="1")
	{ 
	
	var splitComboTextBoxValue=mandTextBoxCombo.split('&');
	var comboval=0;
	var popup="popUpDiv";
	for(comboval;comboval<splitComboTextBoxValue.length;comboval++)
		{
		if(comboval>0){ 
			
			AddRowToTableAddMoreValue(splitComboTextBoxValue[comboval],mandTextBoxComboName,finalMadCode);
		}
		else
			{ 
		//alert(splitComboTextBoxValue[comboval]);
td4.innerHTML=td4.innerHTML+"<div align='left'>"+splitComboTextBoxValue[comboval]+"</div>";
		}
		}
	if(comboval>0)
	td4.innerHTML=td4.innerHTML+" <img src='/HISInvestigationG5/hisglobal/images/addMore.jpg' id='minusButton' onclick='popup(\""+popup+"\")''> ";
	
	}
else
	{ 
	td4.innerHTML="<div align='left'>NA</div>";
	}
	td4.className='tdfont';
td4.colspan="1"; */



	var paraId=labCode+"^"+testCode+"^0^0^0^0^0";
	if(isAppointment=="1")
		{ 
		
		td5.innerHTML="<div id='"+divAptTagRow+"'></div> <div  align='left'><input type='hidden' name='dateTag' onchange='setDateInApoitment("+patCrNo+",\""+(paraId)+"\",this,\""+divAptTagRow+"\")' id='datepicker'></div>";
		$( "#datepicker" ).datepicker({
			dateFormat: 'dd-M-yy',
			showOn: "button",
			buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
			buttonImageOnly: true,
			buttonText: "Select  "
			}).datepicker("setDate", new Date());
					//alert(td6.innerHTML);
		var paraId=labCode+"^"+testCode+"^0^0^0^0^0";
		
	//	getAptSlotDetails('"+patCrNo+"','"+paraId+"','',divAptTagRow);
		getAptSlotDetails("\'"+patCrNo+"\'",paraId,'',divAptTagRow,2);
		}
		else
		
		td5.innerHTML="<div align='left'>NA</div>";	
		td5.className='tdfont';
		td5.colspan="1";
		td5.style.width='15%';
		
		
		
	td6.innerHTML="<div align='left'><select name='priority' tabindex='1' onchange='setPriorityUsingAjax(this,"+(labCode)+","+(testCode)+")'  ><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>'>Normal</option><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>'>Urgent</option><option value='<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_CONFIDENTIAL%>'>Confidential</option></select></div>";
	td6.className='tdfont';
	td6.colspan="1";
	td6.style.width='10%';
	
	//var ind=document.getElementById((parseInt(nRow)+1));
	//alert("ind="+ind)
 
 // td8.className='tdfont';
 // td8.colspan="1";
 // td8.innerHTML="<div align='left'><img src='/BLDHISInvestigationG5/hisglobal/images/imgDatepicker.png' onclick ='showIndication(event,parseInt(\""+ind.rowIndex+"\"))'></div>";
	
 
// td7.innerHTML="<div align='left'><html:text name='invRaisingCumSamCollectionFB' property='containerVolume' size='6' tabindex='1'></html:text></div>";
//  td7.colspan="1";
//  td7.className='tdfont';
//  7	td.width="10%";
 td4.className='tdfont';
  td4.colspan='1';
 
  if(sampnoconfig=="1"||sampnoconfig=="2")
  td4.innerHTML="<div align='left'><input type='hidden' value='"+sampnoconfig+"' name ='tempSampleNo' size='6'></div>";
  else
	td4.innerHTML="<div align='left'><input type='text'  name ='tempSampleNo' onblur='chkSampleNoDuplicacyThroughAjax(this)' size='6'></div>";
	
	
	td4.style.width='10%';    
	
	
	
td7.className='tdfont';
  td7.colspan='1';
  if(testType=="P") // Sample or Slide Based
		td7.innerHTML="<div align='left'>NA</div>";
		else
  td7.innerHTML="<div id='volumediv"+nRow+"' align='left'><input type='text'  name ='containerVolume' value='"+defaultVolume+"'  size='6'></div>";
  td7.style.width='10%';
	

  if(testType=="P") // Sample or Slide Based
		td8.innerHTML="<div align='left'>NA</div>";
		else
	td8.innerHTML="<div id='uomdiv"+nRow+"' align='left'><select name='uomCode' tabindex='1' >"+uomComboStr+"</select></div>";
	td8.className='tdfont';
	td8.colspan="1";
	td8.style.width='10%';
	
	
	 if(testType=="P") // Sample or Slide Based
			td9.innerHTML="<div align='left'>NA</div>";
			else
	td9.innerHTML="<div id='containerdiv"+nRow+"' align='left'><select name='containerCode' tabindex='1' >"+containerComboStr+"</select></div>";
	td9.className='tdfont';
	td9.colspan="1";
	td9.style.width='10%';
	
	pat=pat.split('\n').join("<br>");
	tech=tech.split('\n').join("<br>");
	coll=coll.split('\n').join("<br>");
 
	td10.className='tdfont';
	td10.colspan="1";
	if( (pat.split("$")[1]=='') && (tech.split("$")[1]=='') && (coll.split("$")[1]=='') )
		{
		
		td10.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+")'></div>";
		}
	else
		{
	//	alert("d");
	td10.innerHTML="<div align='left'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRow("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+")'><img  title='Show Instructions' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onClick='showInstructions5("+"\""+(pat)+"\""+","+"\""+(coll)+"\""+","+"\""+(tech)+"\""+")'></div>";
		}
	td10.style.width='5%';
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	tabRow.appendChild(td5);
	tabRow.appendChild(td6);
	
	tabRow.appendChild(td4);
	
	
	tabRow.appendChild(td7);
	tabRow.appendChild(td8);
	tabRow.appendChild(td9);
	tabRow.appendChild(td10);
	
	
	 document.forms[0].numberOfRow.value=numRows;
	 $( "#datepicker" ).datepicker({
		 dateFormat: 'dd-M-yy',
		 showOn: "button",
		 buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
		 buttonImageOnly: true,
		 buttonText: "Select  "
		 }).datepicker("setDate", new Date());
		 	 //Calendar.setup({'inputField':'requirmentDate'+(parseInt(indexVolSpecific)+1) ,'ifFormat':'%d-%b-%Y','button':(parseInt(indexVolSpecific)+1)+'requirmentDate','singleClick':true});
}
