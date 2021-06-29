                               /* Validation Function for discharge Cancellation */


function validate1(){

      var hisValidator = new HISValidator("dischargecancelTransBean");
      hisValidator.addValidation("strAdmnNo","req","Admission No. is a Mandatory Field");
      
      hisValidator.addValidation("strRsn","req","Cancellation Remarks is mandatory");
      
      hisValidator.addValidation("strBed","dontselect=0","Bed is mandatory");
      
     // hisValidator.addValidation("strRmk","dontselect=0","Discharge Cancellation By is mandatory");
      
      var retVal = hisValidator.validate();
		    if(retVal){
		    	document.forms[0].curDept_Unt_RomCode.value=document.forms[0].curDept_Unt_RomCode.value.split('^')[0]+"^"+document.forms[0].curDept_Unt_RomCode.value.split('^')[1]+"^"+document.forms[0].strBed.value.split('^')[1];
		    	document.forms[0].curWrdBedCode.value=document.forms[0].curWrdBedCode.value.split('^')[0]+"^"+document.forms[0].strBed.value.split('^')[0];
		    	document.getElementsByName("strRoom")[0].value=document.getElementsByName("curDept_Unt_RomCode")[0].value.split("^")[2];
		    	document.forms[0].hmode.value = "INSERT";
				document.forms[0].submit();
		}else{
             return false;
          }
 }
               
function goFunc(){
			var hisValidator = new HISValidator("dischargecancelTransBean");
			hisValidator.addValidation("strAdmnNo", "req", "Admission No. is a Mandatory Field");
			hisValidator.addValidation("strAdmnNo", "minlen=10", "Admission No. should be 10 digit");
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
	
  var flag=validateData(e,5);
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

/*
function view(){
	if(document.forms[0].strCrNo.value!="")
	{
		document.forms[0].strCrNo.readOnly=true;	
		var obj=document.getElementById("id4");
		obj.style.display="block";
		document.getElementById("admDtlTld").style.display="block";
		document.getElementById("transChng").style.display="block";
		document.getElementById("disBnR").style.display="block";
		document.getElementById("id1").style.display="block";
	}
}*/
function funroom_disCancel()
{ 
   var mode="ROOM";
   var myArray=new Array();
   var s=document.forms[0].curWrdBedCode.value;
   myArray=s.split("^");
   var temp=myArray[0]+"@"+document.forms[0].strRoomType.value;
   var url=document.forms[0].cnt.value+"?hmode="+mode+"&modRoomType="+temp+'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+ document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value+'&treatmentCategCode='+document.forms[0].strCategoryCode.value+"&deptUnitCode="+document.forms[0].curDept_Unt_RomCode.value.split("^")[1]+"&strCrNo="+document.forms[0].strCrNo.value;
 
  // alert(url);
   ajaxFunction(url,"3");
}
function fun(){
document.forms[0].strAdmnNo.readOnly=false;
	document.forms[0].strAdmnNo.value="";
	document.getElementById("id1").style.display="none";
	document.forms[0].hmode.value="CLEAR";
	 			document.forms[0].submit();
	
}
function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();

}
function hlpOnLoad_disCancel()
{
          document.forms[0].strAdmnNo.readOnly=false;
          var cnt=document.forms[0].cnt.value;
         
          var frmName=document.forms[0].name;
          if(document.forms[0].strCrNo.value>0)
          {
            document.forms[0].strAdmnNo.readOnly=true;
            var o1=document.getElementById("admDtlTld");
            if((o1.innerHTML).length>9)
            {
        	   document.getElementById("gobox").style.display="none";
        	   document.getElementById("savebutton").style.display="block";
               document.getElementById("disBnR").style.display="block";
               document.getElementById("patTldglbdiv").style.display="block";
              // document.getElementById("admDtlTldglbdiv").style.display="block";
               document.getElementById("patDtlTld").style.display="block";
               document.getElementById("admDtlTld").style.display="block";
               document.getElementById("id1").style.display="block";
               document.getElementById("transChng").style.display="block";
             /*  document.getElementById("patDemDtlId").style.display="block";*/
               $('#nonPrintable').addClass('vpinside');
               
               var currDtUtWrRmBd=document.forms[0].curDtUtWrRmBd.value;
             
	           var mode="transOf_BS";
		       var url=cnt+"?hmode="+mode+"&modName="+1+"&currDtl="+currDtUtWrRmBd;
		       //url=url.replace("^","@");
		     //  alert(url);
		       for(var j=1;j<=(url.split("^").length+2);j++)
				{
		    	   url=	url.replace("^","@");
				}
		    //   alert(url);
		       ajaxFunction(url,"111");
		    }
		    else
		    {
		       document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
		       document.getElementById("patTldglbdiv").style.display="none";
		       //document.getElementById("admDtlTldglbdiv").style.display="none";
               document.getElementById("patDtlTld").style.display="none";
               document.getElementById("id1").style.display="none";
               document.getElementById("transChng").style.display="none";
               document.getElementById("admDtlTld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               document.getElementsByName("strErrMsgString")[0].value="Data Not Found/Invalid AdmNo";
               document.getElementById("errMsg").style.display="block";
               document.getElementById("patDemDtlId").style.display="none";
               document.forms[0].hmode.value="CLEAR";
               document.forms[0].strAdmnNo.value="";
               document.forms[0].submit();
		    }
		  }
		  else
		  {
		    
		  }
}

function checkMsg() 
{	
	var err=document.getElementById("errID");
	var nor=document.getElementById("normalMsg");
	var wrn=document.getElementById("wrnID");
	if (document.getElementById("errID").innerHTML != "") 
	{
		err.innerHTML= "<i class='fas fa-exclamation-circle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";		
		err.style.display = "";
	}
	if (nor.innerHTML != "") 
	{
		nor.innerHTML="<i class='far fa-thumbs-up'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		nor.style.display = "";

	}
	if (wrn.innerHTML != "") 
	{
		wrn.innerHTML="<i class='fas fa-exclamation-triangle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Warning!</strong>&nbsp;&nbsp;"+wrn.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		wrn.style.display = "";
	}	
}

