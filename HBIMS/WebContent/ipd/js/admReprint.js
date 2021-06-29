
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
function goFunc(){
			var hisValidator = new HISValidator("admissionReprintTransBean");
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
			
			}
		    else if(obj.value == 2)//CR No Wise
			{	
		    	
				hisValidator.addValidation("strCrNo", "req", "CR No.  is a Mandatory Field" );
		
			}
			//alert(document.forms[0].rePrintType.value);
			//hisValidator.addValidation("strAdmnNo", "req", "Admission No. is a Mandatory Field");
			var retVal = hisValidator.validate();
			 if(retVal)
			  {
				 document.forms[0].strSelCmbVal.value=document.forms[0].rePrintType.value;
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
			  }else{
			  	return false;
			  }
}
function reprint()
{
 var param=0;
 if(document.forms[0].strSelCmbVal.value!=2)//Admission Slip/Visitor Pass
 {
   param= document.forms[0].strCrNo.value;
   document.getElementById("admDtlTldglbdiv").style.display="none";
   document.getElementById("admDtlTld").style.display="none";
   
 }
 else
 {
   param= document.forms[0].strAdmnNo.value;
   document.getElementById("admDtlTldglbdiv").style.display="block";
   document.getElementById("admDtlTld").style.display="block";
 }
 if(param>0)
 {
   document.getElementById("patTldglbdiv").style.display="block";
   document.getElementById("patDtlTld").style.display="block";
   if(document.forms[0].strSelCmbVal.value!=2)//Admission Slip/Visitor Pass
   {
	   document.getElementById("admDtlTldglbdiv").style.display="none";
	   document.getElementById("admDtlTld").style.display="none";
   }
   else
   {
	   document.getElementById("admDtlTldglbdiv").style.display="block";
	   document.getElementById("admDtlTld").style.display="block";
   }
   //document.getElementById("admDtlTldglbdiv").style.display="block";
   //document.getElementById("admDtlTld").style.display="block";
   document.getElementById("id1").style.display="block";
   document.getElementById("patDemDtlId").style.display="block";
   document.forms[0].strAdmnNo.readOnly=true;
   if(document.forms[0].strSelCmbVal.value=="")
   {
	    //alert("1");
   		//document.forms[0].rePrintType.value=document.forms[0].strSelCmbVal.value;
	   document.forms[0].rePrintType.value="1";
   }
   else
   {
	   //alert("2"+document.forms[0].strSelCmbVal.value);
	  document.forms[0].rePrintType.value=document.forms[0].strSelCmbVal.value;
   }
   reprintCmb();
 }
 else
 {
   document.getElementById("patDtlTld").style.display="none";
   document.getElementById("admDtlTldglbdiv").style.display="none";
   document.getElementById("admDtlTld").style.display="none";
   document.getElementById("id1").style.display="none";
   document.getElementById("patDemDtlId").style.display="none";
   //document.forms[0].rePrintType.value=document.forms[0].strSelCmbVal.value;
   if(document.forms[0].strSelCmbVal.value==null || document.forms[0].strSelCmbVal.value=="")
   {
	   //alert("3");
	   document.forms[0].rePrintType.value=document.forms[0].strSelCmbVal.value;
	 //  document.forms[0].rePrintType.value="1";
   }
   else
   {
	   //alert("4");
	   document.forms[0].rePrintType.value=document.forms[0].strSelCmbVal.value;
   }
   document.getElementById("reprintChrg").innerHTML = "<font size='2' color='blue'>Rs.0</font>";
   document.forms[0].strRePrintCharge.value=0;
 }  
 if(document.forms[0].rePrintType.value=="3"){
 	document.getElementById("reprintId").innerHTML="Re-Print Charge for free/for paid";
 }

}



function goRetFunc(obj)
{

	var flag=validateData(obj,5);
	
	if(flag){
			if(obj.keyCode==13)
			{
				var flag1=goFuncOnEnter();
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


function goFuncOnEnter()
{
				var hisValidator = new HISValidator("admissionReprintTransBean");
				hisValidator.addValidation("strAdmnNo", "req", "Admission No. is a Mandatory Field" );
				hisValidator.addValidation("strAdmnNo", "minlen=10", "Admission No. must be 10 Digits" );
				var retVal = hisValidator.validate();
			 if(retVal)
			  {
	 			document.forms[0].strSelCmbVal.value=document.forms[0].rePrintType[document.forms[0].rePrintType.selectedIndex].value;
	 			return true;
	 		}else{
				return false;
			}
	
}

function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();
}
function fun()
{
 //if(document.forms[0].strAdmnNo.value>0)
 {
	
    document.forms[0].strAdmnNo.readOnly=false;
	document.forms[0].strAdmnNo.value="";
	document.getElementById("id1").style.display="none";
//	document.forms[0].strSelCmbVal.value=document.forms[0].rePrintType[document.forms[0].rePrintType.selectedIndex].value;
	/*var errMsgObj=document.getElementById("errMsg");
	var normalMsgObj=document.getElementById("normalMsg");
	errMsgObj.innerHTML="";
	normalMsgObj.innerHTML="";*/
	
	//document.forms[0].hmode.value="CLEAR";
	// document.forms[0].submit();
 }
/* else
    return false;	*/
	
}
function validate1(){

      var hisValidator = new HISValidator("admissionReprintTransBean");
      var objPassChk;
      var passNo;
      var passTyp;
      var issueDt;
      var frm;
      var to;
      var amt;
      var checkFlg=0;
      var rePrintArr=new Array();
      rePrintArr=(document.forms[0].rePrintCharges.value).split("^");
      //hisValidator.addValidation("strAdmnNo","req","Admission No. is a Mandatory Field");
      var retVal = hisValidator.validate();
      //alert(document.forms[0].rePrintType.value);
      if(retVal==true && document.forms[0].rePrintType.value=="3")
      {
        objPassChk=document.getElementsByName("strChkPass");
        var myArray=new Array();
        for(var i=0;i<objPassChk.length;i++)
        {
          if(objPassChk[i].checked)
          {
             myArray=objPassChk[i].value.split("^");
             if(checkFlg==0)
             {
               passNo=myArray[0];
               passTyp=myArray[1];
               issueDt=myArray[2];
               frm=myArray[3];
               to=myArray[4];
               amt=myArray[5];
             }
             else
             {
               passNo=passNo+"^"+myArray[0];
               passTyp=passTyp+"^"+myArray[1];
               issueDt=issueDt+"^"+myArray[2];
               frm=frm+"^"+myArray[3];
               to=to+"^"+myArray[4];
               amt=amt+"^"+myArray[5];
             }  
            checkFlg=1; 
          }
        }
        if(checkFlg==1)
        {
         document.forms[0].strPassNo.value=passNo;
         document.forms[0].strPassType.value=passTyp;
         document.forms[0].strIssueDate.value=issueDt;
         document.forms[0].strValidFrom.value=frm;
         document.forms[0].strValidUpto.value=to;
         document.forms[0].strPassAmount.value=amt;
         retVal=true;
        }
        else
        {
          alert("Select at least one Pass!!");
          retVal=false;
        }      
      }
      if(document.forms[0].strAdmnStatusCode.value=="")
	  {
		  alert("Kindly check the admission number");
		  return false;
	  }
      if(retVal==true && document.forms[0].rePrintType.value=="2")
      {
    	  if(document.forms[0].strAdmnStatusCode.value!="16")
    	  {
    		  alert("Discharge Card can't be Generated since Patient is not yet Discharged");
    		  return false;
    	  }
      }  
      if(retVal==true && document.forms[0].rePrintType.value=="1")
      {
    	  if(!(document.forms[0].strAdmnStatusCode.value=="11" || document.forms[0].strAdmnStatusCode.value=="12" || document.forms[0].strAdmnStatusCode.value=="15" || document.forms[0].strAdmnStatusCode.value=="16" || document.forms[0].strAdmnStatusCode.value=="17" || document.forms[0].strAdmnStatusCode.value=="18"))
    	  {
    		  alert("Admission Card can't be Generated since Patient is not Admitted");
    		  return false;
    	  }
      }
		 // retVal=false;//delete 
		    if(retVal)
		    {
		    	if(document.getElementsByName("strCase")[0].checked)//Adm No.
		    		document.forms[0].strPatAdmNo.value=document.forms[0].strAdmnNo.value;
		    	
	 		document.forms[0].hmode.value = "INSERT";
			document.forms[0].submit();
		}else{
             return false;
          }
 }
 function reprintCmb()
 {
   var obj=document.forms[0].rePrintType;
   //alert(obj);
   var objRePrnt=document.getElementById("reprintChrg");
   var objEle1 = document.getElementById("VisitPassReprint");
   var rePrintArr=new Array();
   rePrintArr=(document.forms[0].rePrintCharges.value).split("^");
   if(obj.value=="3")
   {
        objRePrnt.innerHTML = "<font size='2' color='blue'>Rs."+rePrintArr[1]+"</font>";
        document.forms[0].strRePrintCharge.value=rePrintArr[1];
        var ob=document.getElementById("totReprintChrg");
        ob.innerHTML="<font size='2' color='blue'>Rs.0</font>"; 
        var hmode = "VistorPassDtl"; 
		var url = "AdmissionReprintTransCNT.cnt?hmode="+hmode+"&admNo="+document.forms[0].curAdmNo.value;
		ajaxFunction(url,"300");  
   }
   else if(obj.value=="1")
   {
        objEle1.innerHTML="";
        objEle1.style.display="none";
        objRePrnt.innerHTML = "<font size='2' color='blue'>Rs."+rePrintArr[0]+"</font>";
        document.forms[0].strRePrintCharge.value=rePrintArr[0];
        var ob=document.getElementById("totReprintChrg");
        ob.innerHTML="<font size='2' color='blue'>Rs."+document.forms[0].strRePrintCharge.value+"</font>"; 
   }
   else 
   {
        objEle1.innerHTML="";
        objEle1.style.display="none";
        objRePrnt.innerHTML = "<font size='2' color='blue'>Rs"+rePrintArr[2]+"</font>"; 
        document.forms[0].strRePrintCharge.value=rePrintArr[2];
        var ob=document.getElementById("totReprintChrg");
        ob.innerHTML="<font size='2' color='blue'>Rs."+document.forms[0].strRePrintCharge.value+"</font>"; 
   }
   
 }
 
  
function chkBoxClick(obj,indx)
{
  var amount =0;
  var totChrgArr=new Array();
  rePrintArr=(document.forms[0].rePrintCharges.value).split("^");
  objPassChk=document.getElementsByName("strChkPass");
  var myArray=new Array();
  for(var i=0;i<objPassChk.length;i++)
  {
     if(objPassChk[i].checked)
     {
       amount=parseInt(amount)+parseInt(rePrintArr[1]);
     }  
  }
  var ob=document.getElementById("totReprintChrg");
  ob.innerHTML="<font size='2' color='blue'>Rs."+amount+"</font>"; 
  document.forms[0].strRePrintCharge.value=amount;
}

function openSlipPopup()
{
				if(document.forms[0].strSaveFlag.value=='1' && document.forms[0].strPatientCrNo.value!='')
				{
					var url='PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo='+document.forms[0].strPatientCrNo.value+'&strAdmNo='+document.forms[0].strPatAdmNo.value+'&duplicateMode=1';
					//var url='PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=1011100004939';
					child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');  
	 				child.moveTo(900,250);
	 				child.focus();
				}
}
function printSlip()
{
	window.print();
	var t=setTimeout("printSlip1()",2000);
}
function printSlip1()
{
	    if (confirm("Whether Printed Successfully?")) 
        {
            window.close();
			self.close();
        }
        else
        {
        	printSlip();
        }
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
		SetCursorToTextEnd('strCrNoId');
	}
}

function hideDtl(obj)
{
		//document.forms[0].strCrNo.value="";
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
             
}
function hlpOnLoad_disCancel()
{     
	 document.forms[0].strAdmnNo.readOnly=false;
     document.forms[0].strCrNo.readOnly=false;
     var cnt=document.forms[0].cnt.value;
    
     var frmName=document.forms[0].name;
     if(document.forms[0].strCrNo.value>0)
     {
       document.forms[0].strAdmnNo.readOnly=true;
       document.forms[0].strCrNo.readOnly=true;
       if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No. Entered
	   {
          document.getElementById("patTldglbdiv").style.display="block";
          document.getElementById("admDtlTldglbdiv").style.display="block";
          document.getElementById("patDtlTld").style.display="block";
          document.getElementById("admDtlTld").style.display="block";
          document.getElementById("patDemDtlId").style.display="block";
          document.getElementById("movementDtlId").style.display="block";
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
				document.getElementById("admDtlTld").style.display = "block";						
			}
			else if(obj.value == 2)//CR No Wise
			{	
				document.getElementById("admDtlTld").style.display = "";
				SetCursorToTextEnd('strCrNoId');
			}
	    }
	    else
	    {
		  SetCursorToTextEnd('strCrNoId');
		  document.forms[0].strAdmnNo.readOnly=false;
		  document.forms[0].strCrNo.readOnly=false;
		  document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		  document.getElementById("patTldglbdiv").style.display="block";
		  document.getElementById("admDtlTldglbdiv").style.display="none";
          document.getElementById("patDtlTld").style.display="none";
          document.getElementById("admDtlTld").style.display="block";
          document.getElementById("movementDtlId").style.display="none"; 
          document.getElementsByName("strErrMsgString")[0].value="Data Not Found/Invalid CR No";
          document.getElementById("errMsg").style.display="block";
          document.getElementById("patDemDtlId").style.display="none";
          document.getElementById("id1").style.display="none";
          document.forms[0].strAdmnNo.value="";
          document.getElementById("reprintChrg").innerHTML = "<font size='2' color='blue'>Rs.0</font>";
          document.forms[0].strRePrintCharge.value=0;
          /*document.forms[0].hmode.value="CLEAR";
          document.forms[0].strAdmnNo.value="";
          document.forms[0].submit();*/	    }
	 }	
}
function markCheck()
{
	// document.forms[0].rePrintType.value=1;
	
	//alert(document.forms[0].strRePrintCharge.value);
if(document.getElementsByName("strCase")[1].checked)	
    document.getElementsByName("strCase")[1].checked="true";
else
	document.getElementsByName("strCase")[0].checked="true";
}
function getgoDetails(obj)
{
	if(obj.checked)
	{   
		document.forms[0].strAdmnNo.value=obj.value;
		document.forms[0].strPatAdmNo.value=obj.value;
		document.forms[0].hmode.value="GO";
		document.forms[0].submit();
	}
}
function openPrintPopUp()
{
	if(document.forms[0].strSaveFlag.value=='1' && document.forms[0].strPatientCrNo.value!='' )
	{
		printSlip();
		//document.forms[0].hmode.value="PRINTSLIP";
		//alert(document.forms[0].strPatientCrNo.value);
		//document.forms[0].strCrNo.value=document.forms[0].strPatientCrNo.value;
		window.print();
		//document.forms[0].submit();
		//alert(document.forms[0].strCrNo.value);
		//alert(document.forms[0].hmode.value);
		
			//window.close();
		
	
	 }
	if(document.forms[0].strSaveStatus.value=='1' && document.forms[0].strPatientCrNo.value!='' )
	{
		printSlip();
		//document.forms[0].hmode.value="PRINTSLIP";
		//alert(document.forms[0].strPatientCrNo.value);
		//document.forms[0].strCrNo.value=document.forms[0].strPatientCrNo.value;
		window.print();
		//document.forms[0].submit();
		//alert(document.forms[0].strCrNo.value);
		//alert(document.forms[0].hmode.value);
		
			//window.close();
		
	
	 }
	//alert(window.matchMedia('print'));
	 document.forms[0].strSaveFlag.value=0;
	 document.forms[0].strSaveStatus.value=0;
	 //window.onbeforeprint = beforePrint;
	 //window.onafterprint = hidePrintableSlip();
	 //setTimeout("hidePrintableSlip()",2000);
	 //document.getElementById("printableSlip").style.display="none"; 
}
function hidePrintableSlip()
{
	//alert("hide");
	
	document.getElementById("printableSlip").style.display="none"; 
	
	
	}
function printLastBill()
{
	//alert("show");
	//document.getElementById("printableSlip").style.display="";
	if(document.getElementById("printableSlip").style.display=="")
	{
		// alert("showsdsdsdsdsd");
		alert("No Bill Generated Yet.");
		return;
	}
	else
	    window.print();
}

function dischargePeprint()
{
	//alert("hide");
	if(document.forms[0].strSaveFlag.value=='0')
		{
	      document.getElementById("printableSlip").style.display="none"; 
		}
	else
		{
		 document.getElementById("printableSlip").style.display="block";
		}
	
	
	}  
function showPrintableSlip()
{
	//alert("show");
	document.getElementById("printableSlip").style.display=""; 
}
var globalCnt=0;
String.prototype.replaceAll=function(target, replacement) 
{
	  return this.split(target).join(replacement);
};