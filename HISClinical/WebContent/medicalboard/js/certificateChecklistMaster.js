function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode)
{
	if(document.getElementsByName('mode')[0].value=="ADD"){
		if(document.getElementsByName('noOfChecklistAdded')[0].value==0)
		{
			alert("Please add atleast one checklist")
			return false;
		}
		else{
			return true;
		}
	}
	else{
		if(document.getElementsByName('noOfChecklistAdded')[0].value==0
		&& !validateChecklist())
		{
			alert("Please make some changes")
			return false;
		}
		else if(validateIsCompulsory()){
			return true;
		}
	
	}
}

function validateChecklist()
{
	var valid=false;
	var checklist=document.getElementsByName('selectedChecklistID')
	for(var i=0;i<checklist.length;i++){
		if(checklist[i].checked){
			valid=true;	
		}	
	}
	return valid
}

function validateIsCompulsory()
{
	var valid=true;
	var isCompulsory=document.getElementsByName('existingIsCompulsory')
	for(var i=0;i<isCompulsory.length;i++){
		if(isCompulsory[i].value=="-1"){
			alert("Please Select Is Compulsory");
			isCompulsory[i].focus()
			return false;	
			
		}	
	}
	return valid
}
function finalSubmit(mode)
{
	if (!validateFinalSubmit(mode)) 
	return;
	submitPage(mode);
	
}
function clearForm(){

	document.getElementsByName('processType')[0].value="-1";
	document.getElementsByName('selectedDesignationId')[0].length=0;
	document.getElementsByName('designationId')[0].length=0;
}
 
function addChecklist(){

	if(isSelected(document.getElementsByName('checklistID')[0],"Checklist") &&
	isSelected(document.getElementsByName('isCompulsory')[0],"Is Compulsory") )
	{
		submitPage('ADDCHECKLIST');
	}	
}
 
function removeChecklist(index){

	document.getElementsByName('checklistToRemove')[0].value=index;
	submitPage('REMOVECHECKLIST');
}

function setIsCompulsory(obj){

	if(obj.value!="-1"){
		var value=obj.value.split("@")[1];
		document.getElementsByName("isCompulsory")[0].value=value
	}
	else{
		document.getElementsByName("isCompulsory")[0].value="-1"
	}	
	
}

function setForEdit(obj){
	if(obj.checked){
		document.getElementsByName("existingIsCompulsory")[obj.value].disabled=false
	}
	else{
		document.getElementsByName("existingIsCompulsory")[obj.value].disabled=true
	}	
}

function checkAll(obj){
	var checklist=document.getElementsByName('selectedChecklistID')
	if(obj.checked){
		for(var i=0;i<checklist.length;i++){
			checklist[i].checked=true
			setForEdit(checklist[i])
		}
	}
	else{
		for(var i=0;i<checklist.length;i++){
			checklist[i].checked=false
			setForEdit(checklist[i])
		}
	}
}

function clearForm(){
	if(document.getElementsByName('mode')[0].value=="MODIFY"){
		document.getElementsByName('selectAll')[0].checked=false
		checkAll(document.getElementsByName('selectAll')[0]);
	}
	document.getElementsByName('checklistID')[0].value="-1"
	document.getElementsByName('isCompulsory')[0].value="-1"
}
