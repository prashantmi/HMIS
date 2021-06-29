window.onload=function(){
	
	if(document.getElementsByName("hmode")[0].value=="PRINT"){
		var path="/HISClinical/mrd/transaction/patientDischargePrintingPopup.jsp";
		var height=450;
		var width=600
		var winl = (screen.width - width) / 2;
	  	var wint = (screen.height - height) / 2;
		var child=window.open(createFHashAjaxQuery(path),"","height=" + height + ",width=" + width + ",top=" + wint + ",left=" + winl + "alwaysRaised=yes,scrollbars=yes");
		/*var isSuccessfull=window.confirm("Is printing Successfull")
		child.focus()
		if(isSuccessfull){
			document.getElementsByName("hmode")[0].value="SAVE"
			document.forms[0].submit();
		}*/
	}
	if(document.getElementsByName("selection")){
		var selection=document.getElementsByName("selection")
		for(var i=0;i<selection.length;i++){
			if(selection[i].checked){
				//alert(selection[i])
				toggleOption(selection[i]);
				break;
			}	
		}
	}	
	var handOverTo=document.getElementsByName("handOverTo")[0]
	if(handOverTo){
		showDiv(handOverTo)
		if(handOverTo.value=="1"){
			document.getElementsByName("handOverTo")[0].disabled=true;
		}
		if(handOverTo.value=="0"){
			document.getElementsByName("handOverTo")[0].disabled=true;
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
	switch(parseInt(obj.value)){
		case 0: 
			document.getElementById("unitWardWise").style.display="block";
			document.getElementById("crNoWise").style.display="none";
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

function getDischargeDtl(obj){
	var array=obj.value.split("#");
	var patCrNo= array[0]
	var profileId=array[1]
	var profileStatus=array[2]
	document.getElementsByName("patCrNo")[0].value=patCrNo
	document.getElementsByName("profileId")[0].value=profileId
	document.getElementsByName("profileStatus")[0].value=profileStatus
	document.getElementsByName("hmode")[0].value="GETDISCHARGEDTL"
	document.forms[0].submit()

}

function validateForm(){
	var valid=false;
	var handOverTo=document.getElementsByName("handOverTo")[0].value
	if(comboValidation(document.getElementsByName("handOverTo")[0],"Handed Over To")){
		valid=true	
	}
	else{
		return false;
	}
	if(handOverTo=="0"){
		if(comboValidation(document.getElementsByName("relativeCode")[0],"Relationship")
		&& (isEmpty(document.getElementsByName("relativeName")[0],"Relative Name"))
		&& (isEmpty(document.getElementsByName("relativeAddress")[0],"Address of Relative"))
		){
			valid=true;			
		}
		else{
			valid=false;
		}
	}
	if(handOverTo=="1"){
		if(isEmpty(document.getElementsByName("officerName")[0],"Officer Name")
		&& isEmpty(document.getElementsByName("officerDesign")[0],"Officer Designation")
		&& isEmpty(document.getElementsByName("batchNo")[0]," Badge No")
		){
			valid=true;	
		}
		else{
			valid=false;
		}
	}
	if(valid){
		valid=true;
	}
	else{
		valid=false;
	}
	return valid;

}

function showDiv(obj){
	if(obj.value=="0"){
		document.getElementById("relativeDiv").style.display="block";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="none";
	}
	if(obj.value=="1"){
		document.getElementById("relativeDiv").style.display="none";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="block";
	}
	if(obj.value=="2"){
		document.getElementById("relativeDiv").style.display="none";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="none";
	}
	if(obj.value=="-1"){
		document.getElementById("relativeDiv").style.display="none";
		document.getElementById("otherInfo").style.display="block";
		document.getElementById("policeDiv").style.display="none";
	}
	
}
 
function clearForm()
{
 	 document.getElementsByName("relativeCode")[0].value="-1";
 	 document.getElementsByName("relativeName")[0].value="";
 	 document.getElementsByName("relativeAddress")[0].value="";
 	 document.getElementsByName("isReceiptTaken")[0].checked=false;
 	 document.getElementsByName("remarks")[0].value="";
 	 document.getElementsByName("officerName")[0].value="";
 	 document.getElementsByName("officerDesign")[0].value="";
 	 document.getElementsByName("batchNo")[0].value="";
}

function getPrintDtl(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
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

function checkIsRecieptTaken(obj){
	if(obj.checked){
		obj.value="1"
	}	
	else{
		obj.value="0"
	}	
}