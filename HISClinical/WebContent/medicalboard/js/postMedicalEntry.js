
function getCanidateMedicalDetail(obj){
	document.getElementsByName("hmode")[0].value='GETCANDIDATEMEDICALDTL'
	document.getElementsByName("patCrNo")[0].value=obj.value.split("#")[1];
	document.forms[0].submit();
}

function getCandidateList(){
	if(isSelected(document.getElementsByName("certificateTypeID")[0],"Certificate Type")
		&& validateFromDate()){
		submitForm('GETCANDIDATELIST');	
	}
	else{
		return false;
	}	
}
function setEntryOption(obj){
	if(obj.value==0){
		document.getElementById('nextVisitDiv').style.display="block"
		document.getElementById('certificateResultDiv').style.display="none"
	}
	else{
		if(validateCheckList()){
			document.getElementById('nextVisitDiv').style.display="none"
			document.getElementById('certificateResultDiv').style.display="block"
		}
		else{
			obj.checked=false
		}	
	}
}

function showApproveDiv(){
	if(document.getElementsByName('isVerified')[0].checked){
		document.getElementById('approveDetailDiv').style.display="block"
		document.getElementsByName('isVerified')[0].value="1"
	}
	else{
		document.getElementById('approveDetailDiv').style.display="none"
		document.getElementsByName('isVerified')[0].value="0"
	}
}

function getSchedule(){

	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value
	var certificateTypeId=document.getElementsByName("certificateTypeID")[0].value
	popup=window.open("/HISClinical/medicalboard/medicalBoardRequisition.cnt?hmode=GETSCHEDULELIST&unitCode=" + departmentUnitCode + "&certificateType=" + certificateTypeId + "&index=0" ,
	"Select Schedule","height=300,width=500 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
     popup.opener = self;
 	}
}

function validateSave(){
	var valid=false;
	if(document.getElementsByName('medicalPerformed')[0].checked==false 
	&& document.getElementsByName('medicalPerformed')[1].checked==false){
		alert("Please Select Is Medical Performed")
		return false;
	}
		
	if(document.getElementsByName('medicalPerformed')[1].checked){
		if(isEmpty(document.getElementsByName('examDate')[0],"Next Visit Date") && validateNextVisitDate()
		&& isEmpty(document.getElementsByName('reason')[0],"Reason")){
			valid=true;
		
		}
	}
	else{
		if(document.getElementById('noEmployeeActiveDiv'))
		{
			alert('No Employee is Active corresponding to Board, No opinion can be taken. ')
			return false;
		}
		// alert("validateOpinion()  dfdfgdfgfd"+ validateOpinion());
		var validateOpinionFlag=validateOpinion();
	//	alert("valid 123 "+valid)
	//	alert("validateOpinionFlag "+validateOpinionFlag)
		if(validateCheckList() && validateOpinionFlag)
		{
			// alert("inside opininon and check list")
			valid=true;
		}
		/*
		if(validateCheckList() && validateCertificateResult() 
		&& isEmpty(document.getElementsByName("opinion")[0],"Final Opinion")
		&& validateIsApproved()){
			valid=true;
		}
		*/
	}
	//alert("valid"+valid)
	if(valid){
		//alert("saveeeeeeeeeee");
		submitForm('SAVE');
	}
	else{
		return false;
	}
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

function validateOpinion()
{
	var len=document.getElementsByName("empIdArray").length;
	//alert("len "+len);
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("opinionCodeArray")[i].value=="-1")
		{
			alert("Please select opinion");
			document.getElementsByName("opinionCodeArray")[i].focus();
			return false;
		}
		
		if(document.getElementsByName("opinionRemarkArray")[i].value=="")
		{
			alert("Please enter opinion remarks");
			document.getElementsByName("opinionRemarkArray")[i].focus();
			return false;
		}
		
	}
/*************************getting max opinoin code selected*********************************************/
	
	var opinionLen=document.getElementsByName("opinionCodeArray").length;
	
	var opinion_Code_Array= new Array(opinionLen);
	var opinion_Count_Array=new Array(opinionLen);
		
	for(var i=0;i<opinionLen;i++)
	{
		opinion_Code_Array[i]=null;
		opinion_Count_Array[i]=0;
	}
	
	for(var i=0;i<opinionLen;i++)
	{
		var index="";
		
		//alert("opinion_Code_Array befor "+opinion_Code_Array);
		//alert("opinion_Count_Array "+opinion_Count_Array);
		for(var j=0;j<opinionLen;j++)
		{
			var flag=false;
			if(document.getElementsByName("opinionCodeArray")[i].value==opinion_Code_Array[j])
			{
				flag=true;
				index=j;
				break;
			}
		}
		
		if(flag)
		{
			//alert("inside if");
			opinion_Count_Array[index]=opinion_Count_Array[index]+1;
		}
		else
		{
			//alert("inside else");
			for(var k=0;k<opinionLen;k++)
			{
				if(opinion_Code_Array[k]==null)
				{
					opinion_Code_Array[k]=document.getElementsByName("opinionCodeArray")[i].value;
					opinion_Count_Array[k]=opinion_Count_Array[k]+1;
					break;
				}
			}
		}
		
		
	}
	
	//alert("opinion_Code_Array final "+opinion_Code_Array);
	//alert("opinion_Count_Array final "+opinion_Count_Array);
	
	var max=opinion_Count_Array[0];
	
	
	if(opinion_Count_Array.length>1)
	{
		for(var i=1;i<opinion_Count_Array.length;i++)
		{
			if(max>opinion_Count_Array[i])
			{
				max=max;
			}
			else
			{
				max=opinion_Count_Array[i];
			}
		}
	}
	
	//alert("max count "+max);
	
	var max_Count_Code_Array=new Array(opinion_Count_Array.length);
	
	var j=0;
	for(var i=0;i<opinion_Count_Array.length;i++)
	{
		if(max==opinion_Count_Array[i])
		{
			max_Count_Code_Array[j]=opinion_Code_Array[i];
			j=j+1;
		}
	}
	
	//alert("max_Count_Code_Array[j] "+max_Count_Code_Array);
	
	
/*************************************************************************************/	
	if(document.getElementsByName("onlineOfflineFlag")[0].value=="2")
	{
		if(document.getElementsByName("approvedBy")[0].value=="-1")
			{
				alert("Please select approved by");
				document.getElementsByName("approvedBy")[0].focus();
				return false;
			}
			
			if(document.getElementsByName("approvedDate")[0].value=="")
			{
				alert("Please select approved date");
				document.getElementsByName("approvedDate")[0].focus();
				return false;
			}
			
			var days=noOfDays(document.getElementsByName("sysdate")[0].value,document.getElementsByName("approvedDate")[0].value);
			//alert("dayssssss "+days);
			if(days<0)
			{
				alert("Approved date can not be greater than today date");
				document.getElementsByName("approvedDate")[0].focus();
				return false;
			}
						
			if(document.getElementsByName("opinionCode")[0].value=="-1")
			{
				alert("Please select final opinion");
				document.getElementsByName("opinionCode")[0].focus();
				return false;
			}
			
			var codeFlag=false;
			
			for(var i=0;i<max_Count_Code_Array.length;i++)
			{
				if(document.getElementsByName("opinionCode")[0].value==max_Count_Code_Array[i])
				{
					codeFlag=true;					
				}
			}
			
			/*if(!codeFlag) //Commented By Pragya as per CR 407
			{
				alert("Please select majority opinion");
				document.getElementsByName("opinionCode")[0].focus();
				return false;
			}*/
			
			if(document.getElementsByName("finalRemark")[0].value=="")
			{
				alert("Please select final remark");
				document.getElementsByName("finalRemark")[0].focus();
				return false;
			}
			
			
	}
	else
	{
		//alert("inside else.....");
		if(document.getElementsByName("isVerified")[0].checked)
		{
			if(document.getElementsByName("approvedBy")[0].value=="-1")
			{
				alert("Please select approved by");
				document.getElementsByName("approvedBy")[0].focus();
				return false;
			}
			if(document.getElementsByName("approvedDate")[0].value=="")
			{
				alert("Please select approved date");
				document.getElementsByName("approvedDate")[0].focus();
				return false;
			}
			
			var days=noOfDays(document.getElementsByName("sysdate")[0].value,document.getElementsByName("approvedDate")[0].value);
			if(days<0)
			{
				alert("Approved date can not be greater than today date");
				document.getElementsByName("approvedDate")[0].focus();
				return false;
			}
			
			if(document.getElementsByName("opinionCode")[0].value=="-1")
			{
				alert("Please select final opinion");
				document.getElementsByName("opinionCode")[0].focus();
				return false;
			}
			var codeFlag=false;
			
			for(var i=0;i<max_Count_Code_Array.length;i++)
			{
				if(document.getElementsByName("opinionCode")[0].value==max_Count_Code_Array[i])
				{
					codeFlag=true;					
				}
			}
			
			/*if(!codeFlag) // Commented as per CR 407
			{
				alert("Please select majority opinion");
				document.getElementsByName("opinionCode")[0].focus();
				return false;
			}*/
			if(document.getElementsByName("finalRemark")[0].value=="")
			{
				alert("Please select final remark");
				document.getElementsByName("finalRemark")[0].focus();
				return false;
			}
		}
	}
	
	//return false;
	
	//alert("in lasttttt");
	return true;	 
}
	

function validateCertificateResult(){
	if(document.getElementsByName('result')[0].checked==false 
	&& document.getElementsByName('result')[1].checked==false){
		alert("Please Select Certificate Result")
		return false;
	}
	else{
		return true;
	}
} 
function validateIsApproved(){
	if(document.getElementsByName('isVerified')[0].checked==true){
		if(document.getElementsByName('isApproved')[0].checked){
			if((isEmpty(document.getElementsByName('approvedDate')[0],"Approved Date"))
			&& validateApprovedDate()){
				return true;
			}
			else{
				return false;
			}
		}
	}	
	return true;
} 

function clearForm()
{
	document.getElementsByName("selectAll")[0].checked=false;
	selectAllChecklist(document.getElementsByName("selectAll")[0]);
	document.getElementsByName("medicalPerformed")[0].checked=false;
	document.getElementsByName("medicalPerformed")[1].checked=false;
	if(document.getElementsByName("opinionCodeArray"))
		for(var i=0; i<document.getElementsByName("opinionCodeArray").length; i++)
			document.getElementsByName("opinionCodeArray")[i].value="-1";
	if(document.getElementsByName("opinionRemarkArray"))
		for(var i=0; i<document.getElementsByName("opinionRemarkArray").length; i++)
			document.getElementsByName("opinionRemarkArray")[i].value="";
	if(document.getElementsByName("result")[0])
	{
		document.getElementsByName("result")[0].checked=false;
		document.getElementsByName("result")[1].checked=false;
	}
	if(document.getElementsByName("opinion")[0])	document.getElementsByName("opinion")[0].value="";
	if(document.getElementsByName("isVerified")[0])	document.getElementsByName("isVerified")[0].checked=false;
	if(document.getElementsByName("isApproved")[0])	document.getElementsByName("isApproved")[0].checked=false;
	if(document.getElementsByName("approvedBy")[0])	document.getElementsByName("approvedBy")[0].value="-1";
	if(document.getElementsByName("approvedDate")[0]) document.getElementsByName("approvedDate")[0].value="";
	document.getElementsByName("examDate")[0].value="";
	document.getElementsByName("opinionCode")[0].value = "-1";
	if(document.getElementsByName("reason")[0])	document.getElementsByName("reason")[0].value="";
	document.getElementsByName("finalRemark")[0].value="";
	document.getElementById('nextVisitDiv').style.display="none";
	document.getElementById('certificateResultDiv').style.display="none";
	showApproveDiv(document.getElementsByName("isVerified")[0]);
}
   
function selectAllChecklist(obj){
	var selectedChecklist=document.getElementsByName("selectedCheckList")
	if(obj.checked){
		for(var i=0;i<selectedChecklist.length;i++){
			selectedChecklist[i].checked=true;
			enableChecklist(selectedChecklist[i])
		}
	}
	else{
		for(var i=0;i<selectedChecklist.length;i++){
			selectedChecklist[i].checked=false;
			enableChecklist(selectedChecklist[i])
		}
	}
}

function enableChecklist(obj){
	index=obj.value.split("#")[0]
	if(obj.checked){
		document.getElementsByName("remarks")[index].disabled=false;
		//document.getElementsByName("remarks")[obj].value=""
	}
	else{
		document.getElementsByName("remarks")[index].disabled=true;
		document.getElementsByName("remarks")[index].value=""
	}
}
function setIsApproved(obj){
	if(obj.checked){
		document.getElementsByName("isApproved")[0].value="1";
	}
	else{
		document.getElementsByName("isApproved")[0].value="0"
	}
}
function validateCheckList(){
	
	var valid=true;
	if(document.getElementsByName("selectedCheckList")){
		var checklist=document.getElementsByName("selectedCheckList")
		for(var i=0;i<checklist.length;i++){
			
			/*
			if(!checklist[i].checked){
				alert("Please select the compulsory checklist :"+ document.getElementsByName("checkListName")[0].value)
				return false
			}
			else{
				valid= true;
			}
			*/
			if(checklist[i].value.split("#")[1]=="<%=MedicalBoardConfig.COMPULSORY_AT_TIME_OF_POST_ENTRY%>"){
				if(!checklist[i].checked){
					alert("Please select the compulsory checklist :"+ document.getElementsByName("checkListName")[i].value)
					return false
				}
				else{
					valid= true;
				}
			}
			
		}
	}
	return valid
}

function validateApprovedDate(){
	var sysdate=document.getElementsByName("sysdate")[0]
	var approvedDate=document.getElementsByName("approvedDate")[0]
	var lastVisitDate=document.getElementsByName("lastVisitDate")[0]
	if(!compareDate(approvedDate,sysdate,2)){
		alert("Approved Date cannot be greater than current Date")
		return false;
	}
	if(!compareDate(lastVisitDate,approvedDate,2)){
		alert("Approved Date cannot be less than last Visit Date")
		return false;
	}
	return true;

}

function validateFromDate(){
	var sysdate=document.getElementsByName("sysdate")[0]
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	if(!compareDate(toDate,sysdate,2)){
		alert("To Date cannot be greater than Current Date")
		return false;
	}
	if(!compareDate(fromDate,sysdate,2)){
		alert("From Date cannot be greater than Current Date")
		return false;
	}
	if(toDate.value!="" && fromDate.value!=""){
		if(!compareDate(fromDate,toDate,2)){
			alert("From Date cannot be greater than To Date")
			return false;
		}
	}	
	return true;
}

function validateNextVisitDate(){
	var sysdate=document.getElementsByName("sysdate")[0]
	var examDate=document.getElementsByName("examDate")[0]
	if(!compareDate(sysdate,examDate,2)){
		alert("Next Visit Date cannot be smaller than Current Date")
		return false;
	}
	return true;
}

function showReferDtlDiv(value){
	if(value==1){
		document.getElementById("referDtlDiv").style.display="block"
		document.getElementById("upArrow").style.display="none"
		document.getElementById("downArrow").style.display="block"
	}	
	else{
		document.getElementById("referDtlDiv").style.display="none"
		document.getElementById("upArrow").style.display="block"
		document.getElementById("downArrow").style.display="none"
	}
}

function showExtReferDtlDiv(value){
	if(value==1){
		document.getElementById("extReferDtlDiv").style.display="block"
		document.getElementById("upArrow2").style.display="none"
		document.getElementById("downArrow2").style.display="block"
	}	
	else{
		document.getElementById("extReferDtlDiv").style.display="none"
		document.getElementById("upArrow2").style.display="block"
		document.getElementById("downArrow2").style.display="none"
	}
}

function showInvDtlDiv(value){
	if(value==1){
		document.getElementById("invDetailDiv").style.display="block"
		document.getElementById("upArrow1").style.display="none"
		document.getElementById("downArrow1").style.display="block"
	}	
	else{
		document.getElementById("invDetailDiv").style.display="none"
		document.getElementById("upArrow1").style.display="block"
		document.getElementById("downArrow1").style.display="none"
	}
}

function isDocPresent(){
	if(document.getElementsByName("patIsDocPresent")[0].value=='0')
	{
				document.getElementById("DocNotPresentDiv").style.display="block"
		
	}
else
{
	document.getElementById("DocPresentDiv").style.display="block"
}
}

function getScanDocPopup(flag)
{
	if(document.forms[0].patIsDocPresent.value=='1')
	{ 
		var url = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+document.forms[0].patCrNo.value+"&strDocTypeId=15";
		
		var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=800,width=350,left=10,top=10');  
		child.moveTo(2,2);
		child.opener.focus();
	}
	

	else{
		alert("Scan Document not Avalaible!!!!");
		return false;
	}  	
  	
  
}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
