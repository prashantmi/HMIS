function goFunc()
{
			var hisValidator = new HISValidator("patientTrackingTransBean");
			var obj=document.forms[0].strCase;
			for(var i = 0 ; i < obj.length ; i++)
			{
				if(obj[i].checked)
				{
					obj = obj[i];
					break;
				}		
			}	
	
			if(obj.value == 1)//Admission No Wise
			{	
				hisValidator.addValidation("strAdmnNo", "req", "Admission No. is a Mandatory Field");
				hisValidator.addValidation("strAdmnNo", "minlen=10", "Admission No. should be 10 digit");
			}
			else if(obj.value == 2)//CR No Wise
			{	
				hisValidator.addValidation("strCrNo", "req", "CR No.  is a Mandatory Field" );
				hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
			}
			var retVal = hisValidator.validate();
			 if(retVal)
			  {
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
			  }else{
			  	return false;
			  }
}

function goFuncOnEnter(eve){
	
  var flag=validateData(eve,5);
  if(flag){

		if(eve.keyCode == 13){		
			return goFunc();
	 	}else{
			return false;
		}
  }else{
  	return false;
  }	
}
function fun(){
document.forms[0].strAdmnNo.readOnly=false;
	document.forms[0].strAdmnNo.value="";
	document.forms[0].strCrNo.readOnly=false;
	document.forms[0].strCrNo.value="";
	//document.getElementById("id1").style.display="none";
	document.forms[0].hmode.value="CLEAR";
	 			document.forms[0].submit();
	
}
function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();

}
function reopenMov()
{	
	 var obj=document.forms[0].strCase;
	 if(obj.value == 1)//Admission No Wise
	 {
		$('#movDetailId').modal('show');
	 }
	 /*else if(obj.value == 2)//CR No Wise
	 {	
		 chk
	 }*/
	
}
function hlpOnLoad_disCancel()
{     
          document.forms[0].strAdmnNo.readOnly=false;
          document.forms[0].strCrNo.readOnly=false;
          var cnt=document.forms[0].cnt.value;
         
          var frmName=document.forms[0].name;
         // alert( document.forms[0].strCrNo.value);
          if(document.forms[0].strCrNo.value>0 && document.forms[0].strCrNo.value.length>7)
          {
            document.forms[0].strAdmnNo.readOnly=true;
            document.forms[0].strCrNo.readOnly=true;
            var o1=document.getElementById("patDtlTld");
            //alert( o1.innerHTML);
           if((o1.innerHTML).length>9)
            {
        	   //alert( o1.innerHTML);
        	   document.getElementById("patTldglbdiv").style.display="block";
               document.getElementById("admDtlTldglbdiv").style.display="block";
               document.getElementById("patDtlTld").style.display="block";
               document.getElementById("admDtlTld").style.display="block";
               document.getElementById("patDemDtlId").style.display="block";
               document.getElementById("movementDtlId").style.display="block";
               /*document.getElementById("gobox").style.display="none";
               document.getElementById("footerid").style.display="none"; 
               document.getElementById("hrid").style.display="none"; */
               
               //document.getElementById("footerId1").style.display="";
               //document.getElementById("hidecard").style.display="none";

               var obj=document.forms[0].strCase;
               for(var i = 0 ; i < obj.length ; i++)
				{
					if(obj[i].checked)
					{
						obj = obj[i];
						break;
					}		
				}	
				
				/*if(obj.value == 1)//Admission No Wise
				{
					//document.getElementById("admDtlId").style.display = "";		
					//document.getElementById("navId").style.display = "none";	
					
					document.getElementById("viewbutton").style.display="block";
					$('#movDetailId').modal('show');
					
				}
				else if(obj.value == 2)//CR No Wise
				{	
					//document.getElementById("admDtlId").style.display = "none";
				}*/
               if(obj.value == 1)//Admission No Wise
				{
					document.getElementById("admDtlId").style.display = "";						
				}
				else if(obj.value == 2)//CR No Wise
				{	
					document.getElementById("admDtlId").style.display = "none";
				}
		    }
		    else
		    {
		       document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		       document.getElementById("patTldglbdiv").style.display="none";
		       document.getElementById("admDtlTldglbdiv").style.display="none";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("movementDtlId").style.display="none"; 
               document.getElementsByName("strErrMsgString")[0].value="Data Not Found/Invalid CR No";
               document.getElementById("errMsg").style.display="block";
               document.getElementById("patDemDtlId").style.display="none";
               document.getElementById("admDtlId").style.display = "none";
               document.forms[0].hmode.value="CLEAR";
               document.forms[0].strAdmnNo.value="";
               document.forms[0].submit();
		    }
		  }		  
}
function getData(val,id,visibility)
{
	 if(visibility=='1')//show
	 document.getElementById(id).style.display="block";
	 if(visibility=='2')//hide
	 document.getElementById(id).style.display="none";
}

function changeRadio(obj)
{
	for(var i = 0 ; i < obj.length ; i++)
	{
		if(obj[i].checked)
		{
			obj = obj[i];
			break;
		}		
	}	
	
	if(obj.value == 1)//Admission No Wise
	{	
		document.getElementById("admNoId").style.display = "";
		document.getElementById("crNoId").style.display = "none";		
	}
	else if(obj.value == 2)//CR No Wise
	{	
		document.getElementById("admNoId").style.display = "none";
		document.getElementById("crNoId").style.display = "";		
	}
}

function hideDtl(obj)
{
		document.forms[0].strCrNo.value="";
		document.forms[0].strAdmnNo.value="";
		document.forms[0].strAdmnNo.readOnly=false;
		document.forms[0].strCrNo.readOnly=false;
		document.forms[0].strErrMsgString.value="";
		document.getElementById("admDtlTldglbdiv").innerHTML="";
        document.getElementById("patDtlTld").innerHTML="";
        document.getElementById("admDtlTld").innerHTML="";
        document.getElementById("movementDtlId").innerHTML=""; 
        document.getElementsByName("strErrMsgString")[0].value="";
        document.getElementById("errMsg").innerHTML="";
        document.getElementById("patDemDtlId").innerHTML="";
        //document.getElementById("admDtlId").innerHTML = "";
        
        document.getElementById("admDtlTldglbdiv").style.display="none";
        document.getElementById("patDtlTld").style.display="none";
        document.getElementById("admDtlTld").style.display="none";
        document.getElementById("movementDtlId").style.display="none"; 
        document.getElementById("patDemDtlId").style.display="none";
        document.getElementById("movDetails").style.display="none";  
        
}
function getMovDetails(obj)
{
	if(obj.checked)
	{
		var hmode = "GETMOVDETAILS"; 
		var url='PatientTrackingTransCNT.cnt?hmode='+hmode+'&admNo='+obj.value;
		ajaxFunction(url,"1");	
	}		
}
function getMovDetails_BS(obj)
{
	if(obj.checked)
	{
		var hmode = "GETMOVDETAILS"; 
		var url='PatientTrackingTransBSCNT.cnt?hmode='+hmode+'&admNo='+obj.value;
		ajaxFunction(url,"1");	
	}		
}
function getAjaxResponse(res,mode)
{
		if(mode == '1')
		{
			var objEle = document.getElementById("movDetails");
			objEle.style.display="";
			objEle.innerHTML =res;		
			//$('#movDetailId').modal('show');
		}	
}
