function chkUserDefinedFunc(these){
	var checkCount=0;
	var checkValue="";
	var unitId="";
	var roomId="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
			unitId=check[i].value.split("*")[1];
			roomId=check[i].value.split("*")[2].split("$")[0];
		}			
	}
	try
	{
	if(document.forms[0].combo[4].value==2)
	{//Non Accepted
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				/*if(document.forms[0].combo[1].value=="0")
				{
					//alert("Please Select Unit no1");
					for(i=0;i<check.length;i++)
					{
						check[i].checked=false;
					}
				}*/
				else if(document.forms[0].combo[2].value==0)
				{
					//alert("Please Select Ward");
					//resetRow();
				}
				else
				{				
					enableButton("Acceptance");
					enableButton("Not Reported");
					//disableButton("Not Reported");
					enableButton("Bed Status");
					enableButton("Ward Statistics");
				}
		}
		else
		{
				enableButton("Bed Status");
				enableButton("Ward Statistics");
				disableButton("Acceptance");
				disableButton("Not Reported");
				//alert("Please Select Room!");
				//document.forms[0].combo[3].focus();
		}
	
	 }	 
	 else  if(document.forms[0].combo[4].value==1)
	 {//Admitted
		if(checkCount==1)
		{
			if(checkValue!="1")
			{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else
				{
					enableButton("Belongings");
					enableButton("Movement");
					enableButton("Offline Leave");
					enableButton("Leave Request");
					enableButton("Leave Record");
					enableButton("Death Notif...");
					enableButton("Issued Items");
					enableButton("Final Discharge");
				}				
			}
			else
			{
				enableButton("Movement");
			}
		} 
		else 
		{
			disableButton("Belongings");
			disableButton("Movement");
			disableButton("Offline Leave");
			disableButton("Leave Request");
			disableButton("Leave Record");
			disableButton("Death Notif...");
			disableButton("Issued Items");
			disableButton("Final Discharge");
		}
	}
	else if(document.forms[0].combo[4].value==3)
	{
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{
					enableButton("Join Record");
					enableButton("Not Reporting");
					//disableButton("Not Reporting");
				}
		} 
		else 
		{
			disableButton("Join Record");
			disableButton("Not Reporting");
		}
	} 
	else if(document.forms[0].combo[4].value==4)
	{//Intransit
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{
					enableButton("Cancel");
				}
		} 
		else 
		{
			disableButton("Cancel");
		}
	}	
	}
	catch(Err)
	{
		alert(Err);
	}
}
function chkUserDefinedFunction(these){
	var checkCount=0;
	var checkValue="";
	var unitId="";
	var roomId="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
			unitId=check[i].value.split("*")[1];
			roomId=check[i].value.split("*")[2].split("$")[0];
		}			
	}
	try
	{
	if(document.forms[0].combo[4].value==2)
	{//Non Accepted
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{				
					enableButton("Acceptance");
					enableButton("Not Reported");
					//disableButton("Not Reported");
					enableButton("Bed Status");
					enableButton("Ward Statistics");
				}
		}
		else
		{
				enableButton("Bed Status");
				enableButton("Ward Statistics");
				disableButton("Acceptance");
				disableButton("Not Reported");
		}
	
	 }	 
	 else  if(document.forms[0].combo[4].value==1)
	 {//Admitted
		if(checkCount==1)
		{
			if(checkValue!="1")
			{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else
				{
					enableButton("Belongings");
					enableButton("Movement");
					enableButton("Offline Leave");
					enableButton("Leave Request");
					enableButton("Leave Record");
					enableButton("Death Notif...");
					enableButton("Issued Items");
					enableButton("Final Discharge");
				}				
			}
			else
			{
				enableButton("Movement");
			}
		} 
		else 
		{
			disableButton("Belongings");
			disableButton("Movement");
			disableButton("Offline Leave");
			disableButton("Leave Request");
			disableButton("Leave Record");
			disableButton("Death Notif...");
			disableButton("Issued Items");
			disableButton("Final Discharge");
		}
	}
	else if(document.forms[0].combo[4].value==3)
	{
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{
					enableButton("Join Record");
					//enableButton("Not Reporting");
					disableButton("Not Reporting");
				}
		} 
		else 
		{
			disableButton("Join Record");
			disableButton("Not Reporting");
		}
	} 
	else if(document.forms[0].combo[4].value==4)
	{//Intransit
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{
					enableButton("Cancel");
				}
		} 
		else 
		{
			disableButton("Cancel");
		}
	}	
	}
	catch(Err)
	{
		alert(Err);
	}
}
function chkUserDefinedFuncRoom(these){
	var checkCount=0;
	var checkValue="";
	var unitId="";
	var roomId="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
			unitId=check[i].value.split("*")[1];
			roomId=check[i].value.split("*")[2].split("$")[0];
		}			
	}
	try
	{
	if(document.forms[0].combo[4].value==2)
	{//Non Accepted
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					//resetRow();
				}
				else
				{				
					enableButton("Acceptance");
					enableButton("Not Reported");
					//disableButton("Not Reported");
					enableButton("Bed Status");
					enableButton("Ward Statistics");
				}
		}
		else
		{
				enableButton("Bed Status");
				enableButton("Ward Statistics");
				disableButton("Acceptance");
				disableButton("Not Reported");
		}
	
	 }	 
	 else  if(document.forms[0].combo[4].value==1)
	 {//Admitted
		if(checkCount==1)
		{
			if(checkValue!="1")
			{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else
				{
					enableButton("Belongings");
					enableButton("Movement");
					enableButton("Offline Leave");
					enableButton("Leave Request");
					enableButton("Leave Record");
					enableButton("Death Notif...");
					enableButton("Issued Items");
					enableButton("Final Discharge");
				}				
			}
			else
			{
				enableButton("Movement");
			}
		} 
		else 
		{
			disableButton("Belongings");
			disableButton("Movement");
			disableButton("Offline Leave");
			disableButton("Leave Request");
			disableButton("Leave Record");
			disableButton("Death Notif...");
			disableButton("Issued Items");
			disableButton("Final Discharge");
		}
	}
	else if(document.forms[0].combo[4].value==3)
	{
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					//resetRow();
				}
				else
				{
					enableButton("Join Record");
					//enableButton("Not Reporting");
					disableButton("Not Reporting");
				}
		} 
		else 
		{
			disableButton("Join Record");
			disableButton("Not Reporting");
		}
	} 
	else if(document.forms[0].combo[4].value==4)
	{//Intransit
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					//resetRow();
				}
				else
				{
					enableButton("Cancel");
				}
		} 
		else 
		{
			disableButton("Cancel");
		}
	}	
	}
	catch(Err)
	{
		alert(Err);
	}
}
function chkUserDefinedFuncUnit(these){
	var checkCount=0;
	var checkValue="";
	var unitId="";
	var roomId="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
			unitId=check[i].value.split("*")[1];
			roomId=check[i].value.split("*")[2].split("$")[0];
		}			
	}
	try
	{
	if(document.forms[0].combo[4].value==2)
	{//Non Accepted
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				
				else
				{				
					enableButton("Acceptance");
					enableButton("Not Reported");
					//disableButton("Not Reported");
					enableButton("Bed Status");
					enableButton("Ward Statistics");
				}
		}
		else
		{
				enableButton("Bed Status");
				enableButton("Ward Statistics");
				disableButton("Acceptance");
				disableButton("Not Reported");
		}
	
	 }	 
	 else  if(document.forms[0].combo[4].value==1)
	 {//Admitted
		if(checkCount==1)
		{
			if(checkValue!="1")
			{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else
				{
					enableButton("Belongings");
					enableButton("Movement");
					enableButton("Offline Leave");
					enableButton("Leave Request");
					enableButton("Leave Record");
					enableButton("Death Notif...");
					enableButton("Issued Items");
					enableButton("Final Discharge");
				}				
			}
			else
			{
				enableButton("Movement");
			}
		} 
		else 
		{
			disableButton("Belongings");
			disableButton("Movement");
			disableButton("Offline Leave");
			disableButton("Leave Request");
			disableButton("Leave Record");
			disableButton("Death Notif...");
			disableButton("Issued Items");
			disableButton("Final Discharge");
		}
	}
	else if(document.forms[0].combo[4].value==3)
	{
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{
					enableButton("Join Record");
					//enableButton("Not Reporting");
					disableButton("Not Reporting");
				}
		} 
		else 
		{
			disableButton("Join Record");
			disableButton("Not Reporting");
		}
	} 
	else if(document.forms[0].combo[4].value==4)
	{//Intransit
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else
				{
					enableButton("Cancel");
				}
		} 
		else 
		{
			disableButton("Cancel");
		}
	}	
	}
	catch(Err)
	{
		alert(Err);
	}
}
function chkUserDefinedFuncAll(these){
	var checkCount=0;
	var checkValue="";
	var unitId="";
	var roomId="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
			unitId=check[i].value.split("*")[1];
			roomId=check[i].value.split("*")[2].split("$")[0];
		}			
	}
	try
	{
	if(document.forms[0].combo[4].value==2)
	{//Non Accepted
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					//resetRow();
				}
				else
				{				
					enableButton("Acceptance");
					enableButton("Not Reported");
					//disableButton("Not Reported");
					enableButton("Bed Status");
					enableButton("Ward Statistics");
				}
		}
		else
		{
				enableButton("Bed Status");
				enableButton("Ward Statistics");
				disableButton("Acceptance");
				disableButton("Not Reported");
		}
	
	 }	 
	 else  if(document.forms[0].combo[4].value==1)
	 {//Admitted
		if(checkCount==1)
		{
			if(checkValue!="1")
			{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					disableButton("Belongings");
					disableButton("Movement");
					disableButton("Offline Leave");
					disableButton("Leave Request");
					disableButton("Leave Record");
					disableButton("Death Notif...");
					disableButton("Issued Items");
					disableButton("Final Discharge");
					//resetRow();
				}
				else
				{
					enableButton("Belongings");
					enableButton("Movement");
					enableButton("Offline Leave");
					enableButton("Leave Request");
					enableButton("Leave Record");
					enableButton("Death Notif...");
					enableButton("Issued Items");
					enableButton("Final Discharge");
				}				
			}
			else
			{
				enableButton("Movement");
			}
		} 
		else 
		{
			disableButton("Belongings");
			disableButton("Movement");
			disableButton("Offline Leave");
			disableButton("Leave Request");
			disableButton("Leave Record");
			disableButton("Death Notif...");
			disableButton("Issued Items");
			disableButton("Final Discharge");
		}
	}
	else if(document.forms[0].combo[4].value==3)
	{
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					//resetRow();
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					//resetRow();
				}
				else
				{
					enableButton("Join Record");
					//enableButton("Not Reporting");
					disableButton("Not Reporting");
				}
		} 
		else 
		{
			disableButton("Join Record");
			disableButton("Not Reporting");
		}
	} 
	else if(document.forms[0].combo[4].value==4)
	{//Intransit
		if(checkCount==1)
		{
				if(document.forms[0].combo[0].value==0)
				{
					alert("Please Select Department");
					for(i=0;i<check.length;i++)
					{
						check[i].checked=false;
					}
				}
				else if(document.forms[0].combo[1].value==0)
				{
					alert("Please Select Unit");
					//resetRow();
				}
				else if(document.forms[0].combo[2].value==0)
				{
					alert("Please Select Ward");
					//resetRow();
				}
				else if(document.forms[0].combo[3].value==0)
				{
					alert("Please Select Room!");
					//resetRow();
				}
				else
				{
					enableButton("Cancel");
				}
		} 
		else 
		{
			disableButton("Cancel");
		}
	}	
	}
	catch(Err)
	{
		alert(Err);
	}
}



function userDefinedOnLoadFunc(){
	showMenu();
	enableButton("Bed Status");
	enableButton("Ward Statistics");
}

function showMenu(these){
	
	
	/*for(var nTmpI =1;nTmpI<5;nTmpI++){
		if(document.forms[0].combo[4].value==nTmpI){
			document.getElementById("menuTable"+nTmpI).style.display = "block";
			document.getElementById("vaccantRowDiv"+nTmpI).style.display = "block";
		} else{
			document.getElementById("menuTable"+nTmpI).style.display = "none";
			document.getElementById("vaccantRowDiv"+nTmpI).style.display = "none";
		}
	}
	if(document.forms[0].combo[4].value==1){
		disableButton("Belongings");
		disableButton("Movement");
		disableButton("Offline Leave");
		disableButton("Leave Request");
		disableButton("Leave Record");
		disableButton("Death Notif...");
		disableButton("Final Discharge");
	}else if(document.forms[0].combo[4].value==2){
		disableButton("Acceptance");
		disableButton("Not Reported");
	}else if(document.forms[0].combo[4].value==3){
		disableButton("Join Record");
		disableButton("Not Reporting");
	}*/
	
}

function buttonClick(mode,remarks1,remarks2)
{
	if(document.getElementsByName("strVisibilityMode")[0].value=="1")
	{
		showMenuFrame();
		
	}
	if(mode=="NOTREPORTING")
	{
		var checkValue;
		var strFinalRemarks;
		strFinalRemarks=remarks1;
		if(remarks1 && remarks2)
		{
			var check=document.getElementsByName("chk");
			for(i=0;i<check.length;i++)
			{
				if(check[i].checked==true)
				{
					checkValue=check[i].value.replace(/[$]/g,"@").split("@")[3].split("*")[0];
					break;
				}			
			}
			//alert(checkValue);
			if(checkValue=='1')//After Admission
			{
				strFinalRemarks=remarks1;
				//alert(strFinalRemarks);
			}
			else
				strFinalRemarks=remarks2;
		}
		document.forms[0].comboValue.value=strFinalRemarks;
		mode="NOTREPORTING";
	} 
	if(mode=="NOTREPORTED")
	{
		var checkValue;
		var strFinalRemarks;
		strFinalRemarks=remarks1;
		if(remarks1 && remarks2)
		{
			var check=document.getElementsByName("chk");
			for(i=0;i<check.length;i++)
			{
				if(check[i].checked==true)
				{
					checkValue=check[i].value.replace(/[$]/g,"@").split("@")[3].split("*")[0];
					break;
				}			
			}
			//alert(checkValue);
			if(checkValue=='1')//After Admission
			{
				strFinalRemarks=remarks1;
				//alert(strFinalRemarks);
			}
			else
				strFinalRemarks=remarks2;
		}
		document.forms[0].comboValue.value=strFinalRemarks;
		mode="NOTREPORTED";
	} 
	else if(mode=="CANCEL")
	{
		document.forms[0].comboValue.value="CANCEL";
		mode = "ACCEPTANCE";
	}
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}
// function added by Amit Kumar Ateria for Not Reporting Confirmation Alert
function confirmNotReport(mode,remarks1,remarks2){
	var check=document.getElementsByName("chk");
	var notReportedFalg;
			for(i=0;i<check.length;i++)
			{
				if(check[i].checked==true)
				{
					notReportedFalg=check[i].value.replace(/[$]/g,"@").split("@")[4].split("*")[3];
					break;
				}			
			}
	if(notReportedFalg==1)
	{
		alert("Patient can't be not reported before time limit");
		return;
	}
	/*var flag=confirm("Are you sure patient did not report");
	if(flag)
	{
		var remarks=prompt("Enter Remarks For Not Reported Patient");
		if(remarks.length >= 1)
	    {
	    	if(remarks.length >= 100)
		    {
		    	alert("Remarks should be less than 100 characters !");
		        return false;
		    }
		    else
		    {
		    	var flag2=confirm("Are You Sure This Patient is NOT REPORTED ?");
		    	if(flag2)
		    	{
		    		
		    		var finalRemarks1=remarks1;
		    		var finalRemarks2=remarks2;
		    		buttonClick(mode,finalRemarks1,finalRemarks2);
		    	}
		    }	    	
	    }
	    else
	    {
	    		alert("Remarks is a mandatory field !");
		        return false;
	    }
	}
	else
	{
		return false;
	}*/
	var finalRemarks1=remarks1;
	var finalRemarks2=remarks2;
	buttonClick(mode,finalRemarks1,finalRemarks2);
}


function bedStatus(mode)
{
	if(document.getElementsByName('combo')[0].value==0 || document.getElementsByName('combo')[0].value=='')
	{
		alert("Please Select Department");
		return;
	}
	else if(document.getElementsByName('combo')[1].value==0 || document.getElementsByName('combo')[1].value=='')
	{
		alert("Please Select Unit");
		return;
	}
	else if(document.getElementsByName('combo')[2].value==0 || document.getElementsByName('combo')[2].value=='')
	{
		alert("Please Select Ward");
		return;
	}
	else
	{
		var wardCode=document.getElementsByName('combo')[2].value;
		var roomCode=document.getElementsByName('combo')[3].value;
		var deptUnitCode=document.getElementsByName('combo')[1].value;
		var temp=wardCode+"^"+roomCode+"^0^"+deptUnitCode+"^0";
		var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+temp;
		openPopUp_master(createFHashAjaxQuery(url),"400","200","1");
	}
}


function wardStatistics(mode)
{
	if(document.getElementsByName('combo')[0].value==0 || document.getElementsByName('combo')[0].value=='')
	{
		alert("Please Select Department");
		return;
	}
	else if(document.getElementsByName('combo')[2].value==0 || document.getElementsByName('combo')[2].value=='')
	{
		alert("Please Select Ward");
		return;
	}
	else
	{
		var deptCode=document.getElementsByName('combo')[0].value;
		var wardCode=document.getElementsByName('combo')[2].value;
		var roomCode=document.getElementsByName('combo')[3].value;
		var deptUnitCode=document.getElementsByName('combo')[1].value;
		var deptName=document.getElementsByName('combo')[0].options[document.getElementsByName('combo')[0].selectedIndex].text;
		var wardName=document.getElementsByName('combo')[2].options[document.getElementsByName('combo')[2].selectedIndex].text;
		var roomName=document.getElementsByName('combo')[3].options[document.getElementsByName('combo')[3].selectedIndex].text;
		var deptUnitName=document.getElementsByName('combo')[1].options[document.getElementsByName('combo')[1].selectedIndex].text;
		var temp=deptCode+"^"+deptUnitCode+"^"+wardCode+"^"+roomCode;
		var temp1=deptName+"^"+deptUnitName+"^"+wardName+"^"+roomName;
		var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+temp+"&modComboNames="+temp1;
		openPopUp_master(url,"700","200","1");
	}
}

function cancelToDesk()
{
	//hideMenu();
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
			document.getElementById("id6").style.display="block";
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
			document.getElementById("patDtlTld").style.display="none";
			document.getElementById("crNoId").style.display="none";
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
			document.getElementById("crNoId").style.display="";
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
	//alert(document.forms[0].strTempLeaveJoin.value);
	if(document.forms[0].strTempLeaveJoin.value=="leave"){
		if(document.forms[0].strRemarksOthersOnlineMandatoryFlag.value==1)
			hisValidator.addValidation("strAdviceL", "req","Advice given at the time of Leave  Is  Mandatory");
		document.forms[0].strLeavingDt.value=document.forms[0].strCtDate.value;
	}else{
		hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
		hisValidator.addValidation("strPatCondJ", "maxlen=200","Patient Condition at the time of Joining can't have more than 200 characters");
		//hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
		hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
		//hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
		hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
		hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
		if(document.forms[0].strRemarksOthersJoinMandatoryFlag.value==1)
			hisValidator.addValidation("strRsn_Entry", "req","Joining Remarks Is  Mandatory");
			hisValidator.addValidation("strRsn_Entry", "maxlen=100","Joining Remarks can't have more than 100 characters");
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
	/*var timeStatFlg=1;
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
		}*/
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

function saveInDeskIPD()
{
	var frmName=document.forms[0].name;
	var hisValidator = new HISValidator(frmName); 
	//alert(document.forms[0].strTempLeaveJoin.value);
	if(document.forms[0].strTempLeaveJoin.value=="leave"){
		if(document.forms[0].strRemarksOthersOnlineMandatoryFlag.value==1)
			hisValidator.addValidation("strAdviceL", "req","Advice given at the time of Leave  Is  Mandatory");
		document.forms[0].strLeavingDt.value=document.forms[0].strCtDate.value;
	}else{
		hisValidator.addValidation("strPatCondJ", "req","Patient Condition at the time of Joining Is  Mandatory");
		hisValidator.addValidation("strPatCondJ", "maxlen=200","Patient Condition at the time of Joining can't have more than 200 characters");
		//hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
		hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
		//hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
		hisValidator.addValidation("strBed", "dontselect=0","Please select a bed!!");
		hisValidator.addValidation("strRmkJ", "dontselect=0","Join Accepted By Is  Mandatory");
		if(document.forms[0].strRemarksOthersJoinMandatoryFlag.value==1)
			hisValidator.addValidation("strRsn_Entry", "req","Joining Remarks Is  Mandatory");
			hisValidator.addValidation("strRsn_Entry", "maxlen=100","Joining Remarks can't have more than 100 characters");
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
	/*var timeStatFlg=1;
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
		}*/
	/******time validation******/
	if(retVal==true){
		document.forms[0].hmode.value="SAVEIPD";
		hisValidator.clearAllValidations();
		hisValidator=null;
		document.forms[0].submit();
	}else{
		return false;
		
	}  
}

function hideMenuFrame()
{	
//alert("parent.document.getElementById('fs2').cols="+parent.document.getElementById("fs2").cols)
	if(window.XMLHttpRequest) // Mozilla
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}
	//if (window.ActiveXObject)
	else
	{
		if(parent.document.getElementById("fs2").cols == "230,*")
		{
			parent.document.getElementById("fs2").cols = "0,*";
			parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-right.png";
		}
	}	
}
function showMenuFrame()
{	
//alert("showMenuFrame in reg");
	if(window.XMLHttpRequest) // Mozilla
	{
		//parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}
	//if (window.ActiveXObject)
	else
	{
		parent.document.getElementById("fs2").cols = "230,*";
		parent.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}	
}