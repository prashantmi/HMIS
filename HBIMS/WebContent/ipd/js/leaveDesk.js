function chkUserDefinedFunc(these){
	var checkCount=0;
	var checkValue="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
		}			
	}
	try{
		if(document.forms[0].combo[4].value==2){
			if(checkCount==1){
				enableButton("Join Record");
			} else {
				disableButton("Join Record");
			}
		} else if(document.forms[0].combo[4].value==1){
			if(checkCount==1){
				if(checkValue!="1"){
					enableButton("Offline Leave");
					enableButton("Leave Request");
					enableButton("Leave Record");
				}
			} else {
				disableButton("Offline Leave");
				disableButton("Leave Request");
				disableButton("Leave Record");
			}
		}
	}catch(Err){
		alert(Err);
	}
}

function userDefinedOnLoadFunc(){
	showMenu();
}

function showMenu(these){
	for(var nTmpI =1;nTmpI<3;nTmpI++){
		if(document.forms[0].combo[4].value==nTmpI){
			document.getElementById("menuTable"+nTmpI).style.display = "block";
			document.getElementById("vaccantRowDiv"+nTmpI).style.display = "block";
		} else{
			document.getElementById("menuTable"+nTmpI).style.display = "none";
			document.getElementById("vaccantRowDiv"+nTmpI).style.display = "none";
		}
	}
	if(document.forms[0].combo[4].value==1){
		disableButton("Offline Leave");
		disableButton("Leave Request");
		disableButton("Leave Record");
	}else if(document.forms[0].combo[4].value==2){
		disableButton("Join Record");
	}
}

function buttonClick(mode){
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}

function cancelToDesk(){
	document.forms[0].hmode.value = "CANCELTODESK";
	document.forms[0].submit();
}

function cancelPage(){
	document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}

function viewDesk(){
	if(document.forms[0].strCrNo.value!=""){
		document.forms[0].strCrNo.readOnly=true;
		if(document.forms[0].strValue.value=="0"){
			var obj=document.getElementById("id4");
			obj.style.display="block";
			document.getElementById("id6").style.display="none";
			document.getElementById("id5").style.display="block";	
		}else{
			var obj=document.getElementById("id4");
			obj.style.display="block";
			document.getElementById("id6").style.display="block";
			document.getElementById("id5").style.display="none";
			document.getElementById("retrmkDiv").style.display="block";
		}
	}
}

function funDesk(){
	document.getElementById("id4").style.display="none";
	document.getElementById("retrmkDiv").style.display="none";
}

function hlpOnLoadDesk_LJ(){     
	var o=document.getElementsByName("strIs_Leave_Join");
	var tmp=document.forms[0].strTempLeaveJoin.value;
	if(document.forms[0].strIsBedVacant.value=="1")
		document.forms[0].strIsBedVacant.checked=true;
	else
		document.forms[0].strIsBedVacant.checked=false;  
	var cnt=document.forms[0].cnt.value;
	var frmName=document.forms[0].name;
	if(document.forms[0].strCrNo.value>0){
		var o1=document.getElementById("admDtlTld");
		if((o1.innerHTML).length>9){
			document.getElementById("disBnR").style.display="block";
			document.getElementById("patTldglbdiv").style.display="block";
			document.getElementById("admDtlTldglbdiv").style.display="block";
			document.getElementById("patDtlTld").style.display="block";
			document.getElementById("admDtlTld").style.display="block";
			document.getElementById("transChng").style.display="block";
			if(frmName=="patientLeaveJoinRecordTransBean"){
				document.getElementById("LeaveId").style.display="block";
			}
			document.getElementById("transChngj").style.display="block";
			document.getElementById("disBnRj").style.display="block";
		}else{
			document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
			document.getElementById("patTldglbdiv").style.display="none";
			document.getElementById("admDtlTldglbdiv").style.display="none";
			document.getElementById("patDtlTld").style.display="none";
			document.getElementById("id1").style.display="none";
			document.getElementById("transChng").style.display="none";
			document.getElementById("admDtlTld").style.display="none";
			document.getElementById("disBnR").style.display="none";
			document.getElementsByName("strErrMsgString")[0].value="Invalid CrNo/Data Not Found";
			document.getElementById("errMsg").style.display="block";
			if(frmName=="patientLeaveJoinRecordTransBean"){
				document.getElementById("transChngj").style.display="none";
				document.getElementById("LeaveId").style.display="none";
				document.getElementById("disBnRj").style.display="none";
			}
			document.forms[0].hmode.value="CANCEL";
			document.forms[0].strCrNo.value="";
			document.forms[0].submit();
		}
	}
}

function leaveJoinDesk()
{
	if(document.forms[0].strCrNo.value>0){ 
		var o=document.getElementsByName("strIs_Leave_Join");
		var cnt="PatientLeaveJoinRecordTransCNT.cnt";
		var currDtUtWrRmBd=document.forms[0].curDtUtWrRmBd.value;
		var mode="transOf";
		var url=cnt+"?hmode="+mode+"&modName="+1+"&currDtl="+currDtUtWrRmBd+"&isBedVacant="+document.forms[0].strIsBedVacant.value;
		ajaxFunction(url,"111");
		if(o[0].value=="leave"){
			document.getElementById("transChngj").style.display="none";
			document.getElementById("disBnRj").style.display="none";
			document.getElementById("id1").style.display="none";
			document.getElementById("transChngl").style.display="block";
			document.getElementById("id0").style.display="block";
		} 
		if(o[0].value=="join"){
			document.getElementById("transChngj").style.display="block";
			document.getElementById("disBnRj").style.display="block";
			document.getElementById("id1").style.display="block";
			document.getElementById("transChngl").style.display="none";
			document.getElementById("id0").style.display="none";
		}
	}else{
		document.getElementById("transChngj").style.display="none";
		document.getElementById("disBnRj").style.display="none";
		document.getElementById("id1").style.display="none";
		document.getElementById("transChngl").style.display="none";
		document.getElementById("id0").style.display="none";
	} 
}

function saveInDesk()
{
	var frmName=document.forms[0].name;
	var hisValidator = new HISValidator(frmName); 
	if(document.forms[0].strTempLeaveJoin.value=="leave"){
		hisValidator.addValidation("strAdviceL", "req","Advice given at the time of Leave  Is  Mandatory");
		document.forms[0].strLeavingDt.value=document.forms[0].strCtDate.value;
	}else{
		hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
		hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
		hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
		hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
		hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
		hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
		hisValidator.addValidation("strRsn_Entry", "req","Joining Remarks Is  Mandatory");
		document.forms[0].strEntryDate.value=document.forms[0].strCtDate.value;
	}
	document.forms[0].strIsBedVacant.disabled=false;
	if(document.forms[0].strIsBedVacant.checked)
		document.forms[0].strIsBedVacant.value="1";
	else
		document.forms[0].strIsBedVacant.value="0";   
	var o=document.getElementsByName("strIs_Leave_Join");
	if(o[0].value=="leave")
		document.forms[0].strTempLeaveJoin.value="leave";
	if(o[0].value=="join")
		document.forms[0].strTempLeaveJoin.value="join";
	var retVal = hisValidator.validate();
	/******time validation******/
	var timeStatFlg=1;
	var datwidTime=document.forms[0].strCtDateTime.value;
	var myTimeArr=new Array();
	myTimeArr=datwidTime.split("/");
	var netTime=myTimeArr[1];
	myTimeArr=netTime.split(":");
	var am_entry = document.forms[0].strShiftAmPm_L.value;
	if(am_entry=="2"){
		var hh_entry = parseInt(document.forms[0].strShiftHour_L.value) +12; //entry Hour
	}else{
		var hh_entry = document.forms[0].strShiftHour_L.value; //entry Hour
	}  
	var mm_entry = document.forms[0].strShiftMin_L.value;  //entry Mint
	var ss_entry = document.forms[0].strShiftSec_L.value;  //entry Secs
	var hh_curnt = myTimeArr[0]; //current Hour
	var mm_curnt = myTimeArr[1]; //current Mint
	var ss_curnt = myTimeArr[2]; //current Secs
	if(parseInt(hh_curnt)>=parseInt(hh_entry)){
		timeStatFlg=0;
		if(parseInt(hh_curnt)==parseInt(hh_entry)){
			if(parseInt(mm_curnt)>=parseInt(mm_entry)){
				timeStatFlg=0;
				if(parseInt(mm_curnt)==parseInt(mm_entry)){
					if(parseInt(ss_curnt)>parseInt(ss_entry)){
						timeStatFlg=0;
					}else{
						timeStatFlg=1;
					}
				}     
			}else{
				timeStatFlg=1;
			}
		}
	}else{
		timeStatFlg=1;
	}
	if(o[0].value=="leave")
		if(timeStatFlg==1){
			alert("Leaving Time cannot be greater than current Time!!!");
		   	retVal=false;
		}
	/******time validation******/
	if(retVal==true){
		document.forms[0].hmode.value="SAVE";
		hisValidator.clearAllValidations();
		hisValidator=null;
		document.forms[0].submit();
	}else{
		return false;
		
	}  
}