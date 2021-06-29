window.onload=function(){
/*
	if(document.getElementsByName("hmode")[0].value=='GETPATDTL'){
		//alert("popup")
		var auditHeader=document.getElementsByName("auditHeader")[0].value
		popup=window.open("/HISInvestigationG5/utility/patientAuditLogMst.cnt?hmode=POPUP&auditHeader=" + auditHeader,
		"DATA" ,"menubar=yes,scrollbars=yes, height=500,width=800 ,top=" + "150" + ",left=" + "150" );
	}
*/
	if(document.getElementsByName("hmode")[0].value=='GETAUDITDETAIL'){
		hideMenuFrame();
	}
	else{
		showMenuFrame();
	}	
	if(document.getElementsByName("isDateWise")){
		//alert(document.getElementsByName("isDateWise")[0].checked)
		if(document.getElementsByName("isDateWise")[1].checked){
			document.getElementById("dateWiseDiv").style.display="none"
		}
		else{
			document.getElementById("dateWiseDiv").style.display="block"
		}
	}
}

function submitFormOnValidate(flag,mode){
	//alert(flag)
	if(flag){
		document.getElementsByName("hmode")[0].value='GETPATDTL'
		document.forms[0].submit()
	}
	else{
		return false;
	}
}

function openPopup(path,event,height,width){
	if(isSelected(document.getElementsByName("auditLogId")[0],"Audit Header")){
		var popup=window.open(path,"Search","scrollbars=yes,height="+ height + ",width=" + width + "left=10,top=10");
		popup.moveTo(250,250);
	}
	else{
		return false;
	}
}

function showDateDiv(obj){
	if(obj.value=="1"){
		document.getElementById("dateWiseDiv").style.display="block";
	}
	else{
		document.getElementById("dateWiseDiv").style.display="none";
	}
}
function getAuditLogHeader(obj){
	if(obj.value!="-1"){
		document.getElementsByName("hmode")[0].value='GETAUDITHEADER'
		document.forms[0].submit()
	}
	else{
		document.getElementsByName("auditLogId")[0].value="-1"
	}
}
function addAuditHeader(){
	document.getElementsByName("hmode")[0].value='ADDAUDIT'
	if(validateAdd()){
		document.forms[0].submit()
	}
	else{
		return false;
	}	
}
function removeAuditHeader(value){
	document.getElementsByName("auditLogIdToRemove")[0].value=value
	document.getElementsByName("hmode")[0].value='REMOVEAUDIT'
	document.forms[0].submit()
}
function validateAdd(){
	if(isSelected(document.getElementsByName("moduleId")[0],"Module Name")
		&& isSelected(document.getElementsByName("auditLogId")[0],"Audit Header")
		){
		return true
	}
	else{
		return false;
	}
}
function getData(){
	var valid= true;
	if(document.getElementsByName("auditHeaderSize")[0].value==0){
		if(isSelected(document.getElementsByName("moduleId")[0],"Module Name")
			&& isSelected(document.getElementsByName("auditLogId")[0],"Audit Header")
			){
			valid= true;
		}
		else{
			valid= false;
		}
	}
	if(valid && validateDate()){
		document.getElementsByName("hmode")[0].value='GETAUDITDETAIL'
		document.forms[0].submit()
	}
}
function validateDate(){
	var sysdate=document.getElementsByName("sysdate")[0]
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	var registerDate=document.getElementsByName("registerDate")[0]
	
	if(!compareDate(registerDate,fromDate,2)){
		alert("From Date cannot be smaller than Registration Date");
		return false;
	}	
	if(!compareDate(fromDate,sysdate,2)){
		alert("From Date cannot be greater than "+ sysdate.value);
		return false;
	}	
	if(!compareDate(toDate,sysdate,2)){
		alert("To Date cannot be greater than "+ sysdate.value);
		return false;
	}
	if(!compareDate(fromDate,toDate,2)){
		alert("From Date cannot be greater than To Date");
		return false;
	}		
	return true;
}

function showLegends()
{
document.getElementById("legendId").style.display="block";
}


function hideLegends()
{
document.getElementById("legendId").style.display="none";
}
