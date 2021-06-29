function chkUserDefinedFunc(these)
{
	var checkCount=0;
	var checkValue="";
	var check=document.getElementsByName("chk");
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
			checkValue=check[i].value.replace("$","@").split("@")[2];
		}			
	}
	try
	{
		if(document.forms[0].combo.value==0)
		{
			if(checkCount>0)
			{
				disableButton("Add");
				enableButton("Approve");
				disableButton("Ward Allote...");
				disableButton("List");
				disableButton("Cancel");

				if(checkCount > 1)
				{					
					disableButton("View");
				}
				else
				{					
					enableButton("View");
				}
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		} 
		else if(document.forms[0].combo.value==1)
		{			
			if(checkCount>0)
			{
				disableButton("Add");
				enableButton("Approve");
				if(checkCount > 1)
				{
					disableButton("Ward Allote...");	
					disableButton("View");
				}
				else
				{
					enableButton("Ward Allote...");
					enableButton("View");
				}
				disableButton("List");
				disableButton("Cancel");				
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		}
		else if(document.forms[0].combo.value==2)
		{
			if(checkCount>0)
			{
				disableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				disableButton("List");
				enableButton("Cancel");

				if(checkCount > 1)
				{					
					disableButton("View");
				}
				else
				{					
					enableButton("View");
				}
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		}
		else if(document.forms[0].combo.value==4)
		{
			if(checkCount>0)
			{
				disableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				disableButton("List");
				disableButton("Cancel");

				if(checkCount > 1)
				{					
					disableButton("View");
				}
				else
				{					
					enableButton("View");
				}
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		}
		else if(document.forms[0].combo.value==5)
		{
			if(checkCount>0)
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				if(checkCount > 1)
				{					
						disableButton("View");
				}
				else
				{					
						enableButton("View");
				}
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		}
		else if(document.forms[0].combo.value==6)
		{
			if(checkCount>0)
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");

			if(checkCount > 1)
			{					
					disableButton("View");
			}
			else
			{					
					enableButton("View");
			}
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		}
		else if(document.forms[0].combo.value==7)
		{
			if(checkCount>0)
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");

				if(checkCount > 1)
				{					
					disableButton("View");
				}
				else
				{					
					enableButton("View");
				}
			}
			else
			{
				enableButton("Add");
				disableButton("Approve");
				disableButton("Ward Allote...");
				enableButton("List");
				disableButton("Cancel");
				disableButton("View");
			}
		}
	}
	catch(Err)
	{
	//	alert(Err);
	}
}

function Test()
{
 //alert("Test");
}

function changeButtonState(comValue , chkobj)
{
	
	
	alert("comvalue : "+comValue+ " chkobj "+chkobj );
	
	
  if(comValue==0 && chkobj == true)
  {
  
   document.getElementById("addId").style.color = "red";
   document.getElementById("approveId").style.color = "blue";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "red";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "blue";
  }else if(comValue==0 && chkobj == false){
  	
   document.getElementById("addId").style.color = "blue";
   document.getElementById("approveId").style.color = "red";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "blue";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "red";
  	
  	  	
  }else if(comValue==1 && chkobj == true)
  {
  
   document.getElementById("addId").style.color = "red";
   document.getElementById("approveId").style.color = "blue";
   document.getElementById("allotementId").style.color = "blue";
   document.getElementById("approvelistId").style.color = "red";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "blue";
  }else if(comValue==1 && chkobj == false){
  	
   document.getElementById("addId").style.color = "blue";
   document.getElementById("approveId").style.color = "red";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "blue";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "blue";
  	
  }else if(comValue==2 && chkobj == true)
  {

   document.getElementById("addId").style.color = "red";
   document.getElementById("approveId").style.color = "red";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "red";
   document.getElementById("cancelId").style.color = "blue";
   document.getElementById("viewId").style.color = "blue";
   
  }else if(comValue==2 && chkobj == false){
  	
  	document.getElementById("addId").style.color = "blue";
   document.getElementById("approveId").style.color = "red";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "blue";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "red";
  }else if(comValue==4 && chkobj == true)
  {

   document.getElementById("addId").style.color = "red";
   document.getElementById("approveId").style.color = "red";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "red";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "blue";
  }else{
  	
  	document.getElementById("addId").style.color = "blue";
   document.getElementById("approveId").style.color = "red";
   document.getElementById("allotementId").style.color = "red";
   document.getElementById("approvelistId").style.color = "blue";
   document.getElementById("cancelId").style.color = "red";
   document.getElementById("viewId").style.color = "red";
  	
  }
  
}

function ipdMSRecordChkStatus(chkobj1)
{
	alert("X");
	var comValue = document.forms[0].combo[document.forms[0].combo.selectedIndex].value;
	var chkobjArr = document.getElementsByName("chk");
	var chkobj = false;
	
	var comboCount = parseInt("0");
	
	for(var i = 0; i < chkobjArr.length ; i++){
		
		if(chkobjArr[i].checked == true){	
			chkobj = true;
			if(comValue==2 && chkobjArr[i].value.split("@")[2].split("|")[0]=="FALSE"){
				chkobjArr[i].checked = false;
				alert("    The Proposed Admission Date for Patient is "+
				chkobjArr[i].value.split("@")[2].split("|")[1]+
				"\nAnd Ward Allotement can Only be Done On or Above This Date");
			}	
		}
	}
	changeButtonState(comValue , chkobj);
}

function IpdMSRecordComboStatus(comObj)
{
  
	alert("Y");
	var comValue = comObj.value;

	var chkobjArr = document.getElementsByName("chk");

	var chkobj = false;
	
	for(var i = 0; i < chkobjArr.length ; i++){
		
		if(chkobjArr[i].checked == true){
			
		chkobj = true;
			
		}
		
	}
	
	changeButtonState(comValue , chkobj);
  
}

function IpdMscheckColor(mode,display)
{
	alert("Z");
if( mode == "ADD" && document.getElementById("addId").style.color == "blue")
{
add(mode);
}

if(mode == "APPROVE" && document.getElementById("approveId").style.color == "blue")
{

 add(mode);
}
if( mode == "ALLOTEMENT" && document.getElementById("allotementId").style.color == "blue")
{

 add(mode);
} 

if( mode == "APPROVLIST" && document.getElementById("approvelistId").style.color == "blue")
{

 add(mode);
} 
if(mode == "MODECNCEL" && document.getElementById("cancelId").style.color == "blue")
{ 


	
		var chkobjArr = document.getElementsByName("chk");

	var count = parseInt(0);
	
	for(var i = 0; i < chkobjArr.length ; i++){
		
		if(chkobjArr[i].checked == true){
			
		count = count + 1;
			
		}
		
	}
	
	
	if(count > 1){
		alert("Please Select One Record");
		return false;
	}else if(count < 1){
		alert("Please Select A Record");
		return false;
	}else{
	
 		add(mode);
	}


}
if(mode == "VIEW" && document.getElementById("viewId").style.color == "blue")
{ 
	
	
		var chkobjArr = document.getElementsByName("chk");

	var count = parseInt(0);
	
	for(var i = 0; i < chkobjArr.length ; i++){
		
		if(chkobjArr[i].checked == true){
			
		count = count + 1;
			
		}
		
	}
	
	
	if(count > 1){
		alert("Please Select One Record");
		return false;
	}else if(count < 1){
		alert("Please Select A Record");
		return false;
	}else{
	
 		add(mode);
	}
}
}



