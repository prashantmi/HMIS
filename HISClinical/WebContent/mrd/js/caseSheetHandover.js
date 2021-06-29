window.onload=function(){
	
	if(document.getElementsByName("selection")){
		var selection=document.getElementsByName("selection")
		//alert(selection[0].value)
		for(var i=0;i<selection.length;i++){
			if(selection[i].checked){
				//alert(selection[i])
				toggleOption(selection[i]);
				//populateDataTable('tblList', jsonArrData);
			break;
			}	
		}
	/*	populateDataTable('tblList', jsonArrData);*/
	}	
	
	if(document.getElementsByName("isAccept")){
		var isAccepted=document.getElementsByName("isAccept")
		for(var i=0;i<isAccepted.length;i++){
			//alert(isAccepted[i].value)
			if(isAccepted[i].checked){
				hideDetail(isAccepted[i]);
				break;
			}	
		}
	}	
	
	
	if(document.getElementsByName("selectedCheckListId")){
		var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#");
		var selectedCheckListId=document.getElementsByName("selectedCheckListId")
		for(var i=0;i<selectedCheckListId.length;i++){
			for(var j=0;j<checkedItem.length;j++){
				if(checkedItem[j]==selectedCheckListId[i].value){
					document.getElementsByName("selectedCheckListId")[i].checked=true
					showRemarksTextBox(document.getElementsByName("selectedCheckListId")[i],i)
				}
			}		
		}
	}
	
	
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
function submitPage(mode){
	if(document.getElementsByName("patAdmNo")[0].value==""){
		alert("Please Enter Admission No.");
		document.getElementsByName("patAdmNo")[0].focus();
		return false;
	}		

}

function toggleOption(obj){

	var selection;
	//alert(obj.value)
	switch(parseInt(obj.value)){
		case 0: 
			document.getElementById("unitWardWise").style.display="block";
			document.getElementById("crNoWise").style.display="none";
			//document.getElementsByName("hmode")[0].value="GETCASESHEETDTL"
			document.getElementsByName("searchMode")[0].value="0"
			document.getElementsByName("hmode")[0].value="NEW"
			break;
		case 1: 
			document.getElementById("crNoWise").style.display="block";
			document.getElementById("unitWardWise").style.display="none";
			document.getElementsByName("searchMode")[0].value="1"
			document.getElementsByName("hmode")[0].value="SEARCHBYCRNO"
			break;
	}
	
	//document.forms[0].submit();
}
function submitMode(obj){
	toggleOption(obj);
	document.forms[0].submit();
}

function validateForm(){
	var valid=false;
	var isAccept=document.getElementsByName("isAccept");
	var isEnclosureSelected=document.getElementsByName("isEnclosureSelected");
	var selectedRecord=document.getElementsByName("selectedRecord");
	//alert(selectedRecord.length);
	//alert(isEnclosureSelected.length);
	for(var j=0;j<selectedRecord.length;j++){
		//alert("22");
		if(selectedRecord[j].checked){
			valid=true;	
		}
		//alert("222");
	}
	if(!valid){
		alert("Please Select at least One Record");
		return false;
	}
	if(isAccept[0].checked){
		for(var i=0;i<isEnclosureSelected.length;i++)
			//alert("221");
		{
			//for(var j=0;j<selectedRecord.length;j++)
			//{
			if(selectedRecord[i].checked)               /*if(selectedRecord[j].checked && selectedRecord[j].value==i){*/
				{
				//alert("21");
				if(isEnclosureSelected[i].value!="1"){
						alert("Please Select Enclosure For " + document.getElementsByName("patientName")[i].value);
						return false;
					}
				}
		
			}
	//	}
		//-----
		
		
		if(!isEmpty(document.getElementsByName("receivedFrom")[0],"Receive From")){
			return false;
		}
		/*if(!isSelected(document.getElementsByName("receivedFrom")[0],"Receive From")){
			return false;
		}*/
		
	}
	else{
		if(!isEmpty(document.getElementsByName("returnReason")[0],"Reason")){
			return false;
		}
	}
	
	return valid;

}

function hideDetail(obj){
	var selectedRecord=document.getElementsByName("selectedRecord")
	
	if(obj.value==1){
		document.getElementById("accpetDiv").style.display="block";
		document.getElementById("rejectDiv").style.display="none";
		for(var i=0;i<selectedRecord.length;i++){
			enableEnclosureButton(selectedRecord[i],i)
		}
	}
	else{
		document.getElementById("accpetDiv").style.display="none";
		document.getElementById("rejectDiv").style.display="block";
		for(var i=0;i<selectedRecord.length;i++){
			var buttonDiv="enclosureButtonDiv" + i
			document.getElementById(buttonDiv+"").style.display="none"
		}
	}
}

function submitMode1(obj){
	document.getElementsByTagName("body")[0].removeAttribute("onload");
	toggleOption(obj);
	document.forms[0].submit();
} 
 
function getWardByUnit(){
	document.getElementsByName("hmode")[0].value="UNIT"
	var unitCode=document.getElementsByName("departmentUnitCode")[0].value
	if(unitCode=="-1"){
		document.getElementsByName("hmode")[0].value="NEW"
	}
	document.forms[0].submit();
}

function enableEnclosureButton(obj,index){
	
	var buttonDiv="enclosureButtonDiv" + index;
	//alert("buttonDiv "+buttonDiv)
	if(obj.checked && document.getElementsByName("isAccept")[0].checked){
		document.getElementById(buttonDiv+"").style.display="block"
	}
	else{
		document.getElementById(buttonDiv+"").style.display="none"
	}
}

function getEnclosures(index){
	
	//alert(index);
	var path="/HISClinical/mrd/caseSheetHandover.cnt?hmode=GETENCLOSURES&index=" + index ;
	var popup=window.open(createFHashAjaxQuery(path),"Enclosures",'height=300,width=600,left=200,top=150,scrollbars=yes');
	
	

}


function checkAllChecklist(obj){
	var checkList=document.getElementsByName("selectedCheckListId");
	if(obj.checked){
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=true;
			showRemarksTextBox(obj,i);
		}	
	}
	else{
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=false;
			showRemarksTextBox(obj,i);
		}	
	
	}
}

function selectAllRecord(obj){
	var selectedRecord=document.getElementsByName("selectedRecord")
	if(obj.checked){
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=true;
			enableEnclosureButton(selectedRecord[i],i);
		}
	}
	else{
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=false;
			enableEnclosureButton(selectedRecord[i],i);
		}
	}
}

function showRemarksTextBox(checkListId,index)
 {
 	if(checkListId.checked)
     {
       document.getElementsByName('remarks')[index].disabled=false;
     
     }else{
       document.getElementsByName('remarks')[index].disabled=true;
       document.getElementsByName('remarks')[index].value="";
     }
     
 }
 
function clearForm()
{
 	
    var isEnclosureSelected=document.getElementsByName('isEnclosureSelected');
    var selectAll=document.getElementsByName('selectAll')[0];
    var selectEnclosureButton=document.getElementsByName('selectEnclosure');
  	selectAllRecord(selectAll)
    for(var j=0;j<isEnclosureSelected.length;j++)
    {
    	isEnclosureSelected[j].value="";
    }
     
    for(var j=0;j<selectEnclosureButton.length;j++)
    {
    	selectEnclosureButton[j].value="Select Enclosure";
    }
    
    var checklist=document.getElementsByName('selectedCheckListId');
  
     for(var i=0;i<checklist.length;i++)
     {
      	checklist[i].checked=false;
     	document.getElementsByName('remarks')[i].value="";
     	document.getElementsByName('remarks')[i].disabled=true;
     }
    //document.getElementsByName("selectedAllCheckListId")[0].checked=false;
	if(document.getElementsByName("returnReason")[0]){
		document.getElementsByName("returnReason")[0].value=""
	}
	document.getElementsByName("isAccept")[0].checked=true
	document.getElementsByName("receivedFrom")[0].value="-1";
	document.getElementsByName("receivedFrom")[0].value="";
	document.getElementsByName("checkedItem")[0].value="";
    hideDetail(document.getElementsByName("isAccept")[0]);
    document.getElementsByName("hmode")[0].value="CLEAR";
  	document.forms[0].submit();
	     
}