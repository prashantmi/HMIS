function validate1()
{	
	var hisValidator = new HISValidator("patientAdmissionCanceTransBean"); 
	hisValidator.addValidation("strRemarks", "req", "Cancel Reason is Mandatory Field" ); 
	hisValidator.addValidation("strConsultantName","dontselect=0","Please Select Approved By");
	
	var retVal = hisValidator.validate(); 
	if(retVal)
	{
		if(document.forms[0].strAdmissionChargeValue.value>0)
		{
			var admcharge=document.forms[0].strAdmissionChargeValue.value;
			if(confirm("Please Refund Rs. "+admcharge+" To Patient"))
			{
				document.forms[0].hmode.value = "INSERT";
				document.forms[0].deskmode.value="ADMCANCEL";
				document.forms[0].submit();
				window.close();
			}
		}
		else
		{
			document.forms[0].hmode.value = "INSERT";
			document.forms[0].deskmode.value="ADMCANCEL";
			document.forms[0].submit();
			window.close();
		}
	}	
}
function getAjaxResponse(res,mode){
		if(mode==1)
		{
			document.getElementById("unitDivId").innerHTML="<select name='strUnitNewBorn'>"+res+"</select>";			
		}		
	}
	
function goRetFunc(obj)
{
	var flag=validateData(obj,5);
	
	if(flag){
			if(obj.keyCode==13)
			{
				var flag1=goFunc();
				if(flag1)
				{
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
				}
				else
				{
					return false;
				}
				
			}
		}
		else
		{
			return false;
		}
}

function goFunc(){
	
	var hisValidator = new HISValidator("patientAdmissionCanceTransBean");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
				
		}else{
						return false;
		}
		
	

	var retVal=validateModalAlert(document.forms[0].strCrNo,"15","CR NO. is Mandatory Field","1");
	
	
	if(retVal)
	{
		document.forms[0].hmode.value="GO";

		document.forms[0].submit();
	}else
		return false;
	
	
}
function bedDetails()
{
		var hmode = "BEDSTATUS"; 
		var url='PatientAdmissionModiTransCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strWardTypeCode.value+'&roomTypeCode='+document.forms[0].strMotherRoomTypeCode.value+
	    '&wardCode='+document.forms[0].strWardCode.value+'&deptCode='+document.forms[0].strDeptCode.value+'&crNo='+document.forms[0].strCrNo.value;
		var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		 myWindow = window.open(url,'popupWindow',featuresList); 
		myWindow.focus();

}
function view()
{
	if(document.forms[0].strCrNo.value!="")
	{
		if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered
		{
			
			
			document.getElementById("id4").style.display="block";
			document.getElementById("wardDivId").style.display="block";
			document.getElementById("patientCrEdId").style.display="none";
			document.getElementById("patientCrId").style.display="block";
			document.getElementById("newModificationFormId").style.display="block";
			document.getElementById("mandCRId").style.display="none";
			document.getElementById("goBox").style.display='none';
		    document.getElementById("accordion").style.display='block';
		   // document.getElementById("cardheader").style.display='block';
		    document.getElementById("savebutton").style.display="block";
			




			if(document.forms[0].strNewBorn.value==0)
			{
				document.getElementById("DeparmentUnitId").style.display="block";
			}
			if(document.forms[0].strNewBorn.value==1)
			{
				
				document.getElementById("DeparmentUnitModiId").style.display="block";
			}
			
			
			//$('#viewportDiv').addClass('vpinside');

		}
		else//only predefined Digits in CR Field
		{
			//SetCursorToTextEnd('strCrNoId');
			SetSelectedCrNo();
			//document.getElementById("patientCrId").style.display="none";
			document.getElementById("newModificationFormId").style.display="none";
			document.getElementById("newAddressModiId").style.display="none";
			document.getElementById("DeparmentUnitModiId").style.display="none";
			document.getElementById("DeparmentUnitId").style.display="none";
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("id4").style.display="none";
			document.getElementById("patientCrEdId").style.display="block";
			document.getElementById("mandCRId").style.display="";
			document.forms[0].strCrNo.focus();
			

		}
	}
	else
	{
		document.getElementById("patientCrId").style.display="none";
		document.getElementById("newModificationFormId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		document.getElementById("DeparmentUnitModiId").style.display="none";
		document.getElementById("DeparmentUnitId").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("id4").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		document.getElementById("mandCRId").style.display="";
		document.forms[0].strCrNo.focus();
	}
	
	
		if(document.forms[0].isSingleMenu.value=="1")
			setSingleWindow();
	
}	
function view1()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
}
function getUnitCombo()
{
	var hmode = "GETUNIT"; 
	//alert("Hello"+document.forms[0].StrDeptNameNewBorn.value);
	var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].StrDeptNameNewBorn.value;
	ajaxFunction(url,"1");

}
function view2()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
	var obj=document.getElementById("wardDivId");
	obj.style.display="block";
	
}
function view3()
{
	
	var o1=document.getElementById("plusAddModiId");
	var o2=document.getElementById("minusAddModiId");
	var o3=document.getElementById("newAddressModiId");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
}
function view4()
{
	
	var o1=document.getElementById("plusAddModiId");
	var o2=document.getElementById("minusAddModiId");
	var o3=document.getElementById("newAddressModiId");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
	var obj=document.getElementById("wardDivId");
	obj.style.display="block";
	
}
function clearRecord()
{
	/*document.forms[0].strCrNo.value="";
	document.getElementById("warningMsg").innerHTML="";
	document.getElementById("normalMsg").innerHTML="";
	document.getElementById("errMsg").innerHTML="";
	view();*/
	document.forms[0].hmode.value="CLEAR";
	document.forms[0].submit();
}

 function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();
}
 
 
 function checkMsg(id)
 {
 	if(document.getElementById("errID").innerHTML!="")
 	{
 		document.getElementById("errID").style.display="";
 	}
 	//alert(document.getElementById("normalMsg").innerHTML);
 	if(document.getElementById("normalMsg").innerHTML!="")
 	{
 		document.getElementById("normalMsg").style.display="";
 	}
 	if(document.getElementById("wrnID").innerHTML!="")
 	{
 		document.getElementById("wrnID").style.display="";
 	}
 	

 }
 
 function validateModalAlert(obj,size,msg,type)
 {
	 
	
 	var validateModal=document.getElementById("validateModal");
 	var mainDiv=document.getElementById("mainDiv")	
 	
 	
 	if(type=="1")
 		{
 			validateModal.classList.remove("modal");
 			mainDiv.style.display="none";
 	
 	
 			if(obj.value.length==size)
 				return true;
 			else
 			{	
 				mainDiv.style.display="";
 				validateModal.classList.add("modal");
 				document.getElementById("warn").innerHTML=msg;
 				document.getElementById("len").innerHTML="[Current length="+obj.value.length+"]";
 				return false;
 			}	
 		}
 	
 	
 }
 

 
 