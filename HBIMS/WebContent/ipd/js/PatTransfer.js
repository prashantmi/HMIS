function checkPvtWard(){
	var strWardTypeCode=document.forms[0].strWardTypeCode.value;
	var strPvtWardCode=document.forms[0].strPvtWardCode.value;
	if(strWardTypeCode!=strPvtWardCode){
		 if(document.forms[0].strPrevDblOcc.value=="0"){	
		 	document.forms[0].strIsBedVacant.checked=false;
			alert("Ward Must be Private");
			return false;
		 }
		return true;
	} else {
		//document.forms[0].strWardType.value = "0";
		return true;
	}
}


function checkPvtWardForOther()
{
	var strWardTypeCode=document.forms[0].strWardTypeCode.value;
	var strPvtWardCode=document.forms[0].strPvtWardCode.value;
	if(strWardTypeCode!=strPvtWardCode){
			
			document.getElementsByName("strPrevDblOcc")[0].checked=false;
		 	alert("Ward Must be Private");
			return false;
	}
	 else {
	 	document.getElementsByName("strPrevDblOcc")[0].value="1";
		//document.forms[0].strWardType.value = "0";
		return true;
	}
}

function isDoubleOccupancy(obj)
{
  if(!checkPvtWard())
		return false;
		
 if(obj.checked)
 {
   if(document.forms[0].strPrevDblOcc.value=="0")
   {
     if(confirm("You choose Double Occupancy. Are you sure?"))
     {
       document.forms[0].strIsBedVacant.value="0";
       document.forms[0].transId.value="5";  
      //document.forms[0].strWardType.value=document.forms[0].strIpdConfIcuWard.value; 
       alert("Applicable only for selected Ward Type");      
     }
     else
     {
       document.forms[0].strIsBedVacant.checked=false;
       document.forms[0].strIsBedVacant.value="1";
       document.forms[0].transId.value="2"; 
     }
    // alert("transId->"+document.forms[0].transId.value);
   }
   else
   {
    // alert("Patient has already availed Double Occupancy!!::Hold Room:->"+document.forms[0].strHoldRoom.value); 
     document.forms[0].strIsBedVacant.checked=false;
     document.forms[0].strIsBedVacant.value="1";
     document.forms[0].transId.value="2"; 
   }
 }
 else
 {
   document.forms[0].transId.value="2";  
   document.forms[0].strIsBedVacant.value="1";
   document.forms[0].strIsBedVacant.checked=false;
  // alert("transId->"+document.forms[0].transId.value);
 }      
}


function save()
{
	
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
 var insertStr="";
 var trans_Id=document.forms[0].transId.value;
 if(trans_Id!="10")
 {
   var puk=document.forms[0].strCrNo.value;
   var myArray=new Array();
   var myArray1=new Array();
   var tr=document.forms[0].strRsn.value;
   var ed=document.forms[0].strEntryDate.value;
   myArray=(document.forms[0].curWrdBedCode.value).split("^");
   var owc=myArray[0];
   myArray1=(document.forms[0].curDept_Unt_RomCode.value).split("^");
   var odc=myArray1[0];
   var ouc=parseInt(myArray1[1])-parseInt(odc*100);
   var orc=myArray1[2];
   var obc=myArray[1];
   var admNo=document.forms[0].curAdmNo.value;
	// strTransferUnit is changed from combo to radio on 25-Apr-2011 by pragya
	//var transFlg=document.forms[0].strTransferUnit[document.forms[0].strTransferUnit.selectedIndex].value;  
	var transFlg=null;
	for(var i=0;i<document.forms[0].strTransferUnit.length;i++)
		if(document.forms[0].strTransferUnit[i].checked)
		{	transFlg=document.forms[0].strTransferUnit[i].value; break;	}
   
   var retVal=false;
   hisValidator.addValidation("strRmk", "dontselect=0","Please Select Advised By!!");
   
   //alert(transFlg);
   //hisValidator.addValidation("strRsn", "req","Reason for Transfer is Mandatory!!");
 if(transFlg=="1")//Change Of Department/Unit
 {
		hisValidator.addValidation("strDepartment", "dontselect=0","Please select a Department!!");
		// Changed by Pragya on 03 May 2011
		if(document.getElementById("unitId")!=null) 
			hisValidator.addValidation("strUnit", "dontselect=0","Please select a Unit!!");
	   
	   //hisValidator.addValidation("strWardType", "dontselect=0","Please select Ward Type!!");
	   hisValidator.addValidation("strWard", "dontselect=0","Please select a Ward!!");
	   //hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
	   /*if(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value=="0")
	   {
	     alert("MS Approval pending.!!!");
	     document.forms[0].strRoom.focus();
	     retVal=false;
	   }
	   else
	   {  
	     */
		if(document.getElementById("roomId")!=null ) 
			hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
		retVal = hisValidator.validate();
	  // }
		
  
	   if(retVal==true)
	   {
	     // Movement to same Location Check By Pragya on 02-May-2011
	     var temp=document.forms[0].strCurrentDeptUnitRoom.value.split('^');
	     if(document.getElementsByName("strDepartment")[0].value==temp[0] 
	     	&& ( document.getElementById("unitId")==null || document.getElementsByName("strUnit")[0].value==temp[1] ) 
	     	&& document.getElementsByName("strWard")[0].value.split('^')[0]==document.getElementsByName("combo")[2].value 
	     	&& ( document.getElementById("roomId")==null || document.getElementsByName("strRoom")[0].value==temp[2] )
	     )
	     {
	     	alert("Can't move to Same Department/Unit/Ward/Room");
	     	return false;
	     }
	     
	     
	     var objWrd=document.forms[0].strWard.value;
	     myArr=new Array();
	     myArr=objWrd.split("^"); //MS Approval Required
	     var dc=document.forms[0].strDepartment.value;
	     var uc=document.forms[0].strUnit.value;
	     var myArrayW=new Array();
	     myArrayW=(document.forms[0].strWard.value).split("^");
	     var wc=myArrayW[0];
	    /* if(wc==owc)
	     { 
	       alert("Patient cannot be transferred to same Ward!!!.Choose differnt ward. ");
	       retVal=false;
	     }*/
	     var rc=document.forms[0].strRoom.value;
	     if(myArr[1]=="1") // bed code to be passed for MS Approval Required case.otherwise no bed code taken
	     {
	      var myAr=rc.split("^");
	      rc=myAr[0];
	      var bc=myAr[1];
	     }
	     else
	      var bc="0";
	     
	     insertStr=puk+"^"+dc+"^"+uc+"^"+wc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
	   }
 }
 if(transFlg=="2")//Change Of Room/Bed
 {
   //hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
   //hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
   hisValidator.addValidation("strBed", "dontselect=0","Please Select a Bed!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var rc=document.forms[0].strRoom.value;
     if(rc=='0')
     {
     	rc=document.forms[0].strBed.value.split('^')[0];
     }
     var bc=document.forms[0].strBed.value.split('^')[1];
    /* if(bc==obc)
     {
      	alert("Patient cannot be transferred to same Bed!!!.Choose a differnt Bed. ");
       		retVal=false;
     }*/
     insertStr=puk+"^"+myArray1[0]+"^"+myArray1[1]+"^"+owc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
   }
 }
 /*if(transFlg=="3")
 {
   
   hisValidator.addValidation("strDepartment", "dontselect=0","Please select a Department!!");
   hisValidator.addValidation("strUnit", "dontselect=0","Please select a Unit!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var dc=document.forms[0].strDepartment.value;
     var uc=document.forms[0].strUnit.value;
   /*  if(dc==odc && uc==ouc)
     {
       alert("Patient cannot be transferred to same Dept & Unit!!!.Choose at least one different.");
       retVal=false;
     }*/
     //insertStr=puk+"^"+dc+"^"+uc+"^"+owc+"^"+orc+"^"+obc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
   //}
 //}
 
 if(transFlg=="4")
 {
   
   hisValidator.addValidation("strServArea", "dontselect=0","Please select Service Area!!");
   hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var myArray3=new Array();
     myArray3=(document.forms[0].strRoom.value).split("^");
     var rc=myArray3[0];
     var servCd=document.forms[0].strServArea.value;
     insertStr=puk+"^"+myArray1[0]+"^"+myArray1[1]+"^"+owc+"^"+rc+"^"+obc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id+"^"+servCd;
   }
 }
 if(transFlg=="5")
 {
   
   hisValidator.addValidation("strLocation", "req","Location is Mandatory!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var loc=document.forms[0].strLocation.value;
     insertStr=puk+"^"+myArray1[0]+"^"+myArray1[1]+"^"+owc+"^"+orc+"^"+obc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id+"^"+loc;
   }
 }
 if( transFlg=="6")//Change Of Unit
 {
		hisValidator.addValidation("strDepartment", "dontselect=0","Please select a Department!!");
		if(document.getElementById("unitId")!=null) 
			hisValidator.addValidation("strUnit", "dontselect=0","Please select a Unit!!");
	   
	   //hisValidator.addValidation("strWardType", "dontselect=0","Please select Ward Type!!");
	   hisValidator.addValidation("strWard", "dontselect=0","Please select a Ward!!");
	   //hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
	   /*if(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value=="0")
	   {
	     alert("MS Approval pending.!!!");
	     document.forms[0].strRoom.focus();
	     retVal=false;
	   }
	   else
	   {  
	     */
		
		retVal = hisValidator.validate();
	  // }
  
	   if(retVal==true)
	   {
	     // Movement to same Location Check By Pragya on 02-May-2011
	     var temp=document.forms[0].strCurrentDeptUnitRoom.value.split('^');
	     if(document.getElementsByName("strDepartment")[0].value==temp[0] 
	     	&& ( document.getElementById("unitId")==null || document.getElementsByName("strUnit")[0].value==temp[1] ) 
	     	&& document.getElementsByName("strWard")[0].value.split('^')[0]==document.getElementsByName("combo")[2].value 
	     	&& ( document.getElementById("roomId")==null || document.getElementsByName("strRoom")[0].value==temp[2] )
	     )
	     {
	     	alert("Can't move to Same Department/Unit/Ward/Room");
	     	return false;
	     }
	     
	     
	     var objWrd=document.forms[0].strWard.value;
	     myArr=new Array();
	     myArr=objWrd.split("^"); //MS Approval Required
	     var dc=document.forms[0].strDepartment.value;
	     var uc=document.forms[0].strUnit.value;
	     var wc=document.forms[0].curWrdBedCode.value.split('^')[0];
	    //var myArrayW=new Array();
	   //  myArrayW=(document.forms[0].strWard.value).split("^");
	    // var wc=myArrayW[0];
	    /* if(wc==owc)
	     { 
	       alert("Patient cannot be transferred to same Ward!!!.Choose differnt ward. ");
	       retVal=false;
	     }*/
	     var rc=document.forms[0].strRoom.value;
	     if(myArr[1]=="1") // bed code to be passed for MS Approval Required case.otherwise no bed code taken
	     {
	      var myAr=rc.split("^");
	      rc=myAr[0];
	      var bc=myAr[1];
	     }
	     else
	      var bc="0";
	     
	     insertStr=puk+"^"+dc+"^"+uc+"^"+wc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
	   }
 }
 
 
  if(transFlg=="3")//On Check Of Service Area And Service.
 {
       hisValidator.addValidation("strServiceType", "dontselect=0","Please select a Service Type!!");
   	   hisValidator.addValidation("strServiceName", "dontselect=0","Please select a Service!!");
	   retVal = hisValidator.validate();
	   if(retVal==true)
	   {
	     var wc="0";
	     var dc="0";
	     var uc="0";
	     var rc="0";
	     var bc="0";
	   
	     document.forms[0].transId.value="7";//Change of Service Area/OT
	   
	     trans_Id=document.forms[0].transId.value;
	   
	     insertStr=puk+"^"+dc+"^"+uc+"^"+wc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
	   
	   /*commented by vikrant*/
	    /* if(document.forms[0].strWhetherVacantBed.checked)
	     	document.forms[0].strWhetherVacantBed.value="1";
	     else
	     	document.forms[0].strWhetherVacantBed.value="0";*/
	   
	    //document.forms[0].strWhetherVacantBed.disabled=false;
	   }
	   var flag=document.forms[0].strValidatedFlag.value;
	   if(flag==0)
	   {
	   	retVal=false;
	    alert("This Service is not raised for the Patient!!!");
	   }
	   
	   
	  
	   
 }
 
   if(retVal==true)
   {
     insertStr=insertStr+"^"+document.forms[0].strRmk.value;
   //  alert("insertStr->>"+insertStr);
   
     //alert(insertStr+"^"+document.forms[0].strRmk.value);
     document.forms[0].strTempVal.value=insertStr;
     document.forms[0].hmode.value="SAVE";  
     document.forms[0].submit();
   }
   else
   {
     return false;
   }  
 }
 else
 {
  alert("No Details to save!!");
  return false;
 }
}



/*
 *  Save In Case Of IPD Only
 *  Created New function For Save Data
 * 
 */

function saveIPD()
{
	
 var frmName=document.forms[0].name;
 var hisValidator = new HISValidator(frmName); 
 var insertStr="";
 var trans_Id=document.forms[0].transId.value;
 if(trans_Id!="10")
 {
   var puk=document.forms[0].strCrNo.value;
   var myArray=new Array();
   var myArray1=new Array();
   var tr=document.forms[0].strRsn.value;
   var ed=document.forms[0].strEntryDate.value;
   myArray=(document.forms[0].curWrdBedCode.value).split("^");
   var owc=myArray[0];
   myArray1=(document.forms[0].curDept_Unt_RomCode.value).split("^");
   var odc=myArray1[0];
   var ouc=parseInt(myArray1[1])-parseInt(odc*100);
   var orc=myArray1[2];
   var obc=myArray[1];
   var admNo=document.forms[0].curAdmNo.value;
	// strTransferUnit is changed from combo to radio on 25-Apr-2011 by pragya
	//var transFlg=document.forms[0].strTransferUnit[document.forms[0].strTransferUnit.selectedIndex].value;  
	var transFlg=null;
	for(var i=0;i<document.forms[0].strTransferUnit.length;i++)
		if(document.forms[0].strTransferUnit[i].checked)
		{	transFlg=document.forms[0].strTransferUnit[i].value; break;	}
   
   var retVal=false;
   hisValidator.addValidation("strRmk", "dontselect=0","Please Select Advised By!!");
   
   //alert(transFlg);
   //hisValidator.addValidation("strRsn", "req","Reason for Transfer is Mandatory!!");
 if(transFlg=="1")//Change Of Department/Unit
 {
		hisValidator.addValidation("strDepartment", "dontselect=0","Please select a Department!!");
		// Changed by Pragya on 03 May 2011
		if(document.getElementById("unitId")!=null) 
			hisValidator.addValidation("strUnit", "dontselect=0","Please select a Unit!!");
	   
	   //hisValidator.addValidation("strWardType", "dontselect=0","Please select Ward Type!!");
	   hisValidator.addValidation("strWard", "dontselect=0","Please select a Ward!!");
	   //hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
	   /*if(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value=="0")
	   {
	     alert("MS Approval pending.!!!");
	     document.forms[0].strRoom.focus();
	     retVal=false;
	   }
	   else
	   {  
	     */
		if(document.getElementById("roomId")!=null ) 
			hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
		retVal = hisValidator.validate();
	  // }
		
  
	   if(retVal==true)
	   {
	     // Movement to same Location Check By Pragya on 02-May-2011
	     var temp=document.forms[0].strCurrentDeptUnitRoom.value.split('^');
	     if(document.getElementsByName("strDepartment")[0].value==temp[0] 
	     	&& ( document.getElementById("unitId")==null || document.getElementsByName("strUnit")[0].value==temp[1] ) 
	     	&& document.getElementsByName("strWard")[0].value.split('^')[0]==document.getElementsByName("combo")[2].value 
	     	&& ( document.getElementById("roomId")==null || document.getElementsByName("strRoom")[0].value==temp[2] )
	     )
	     {
	     	alert("Can't move to Same Department/Unit/Ward/Room");
	     	return false;
	     }
	     
	     
	     var objWrd=document.forms[0].strWard.value;
	     myArr=new Array();
	     myArr=objWrd.split("^"); //MS Approval Required
	     var dc=document.forms[0].strDepartment.value;
	     var uc=document.forms[0].strUnit.value;
	     var myArrayW=new Array();
	     myArrayW=(document.forms[0].strWard.value).split("^");
	     var wc=myArrayW[0];
	    /* if(wc==owc)
	     { 
	       alert("Patient cannot be transferred to same Ward!!!.Choose differnt ward. ");
	       retVal=false;
	     }*/
	     var rc=document.forms[0].strRoom.value;
	     if(myArr[1]=="1") // bed code to be passed for MS Approval Required case.otherwise no bed code taken
	     {
	      var myAr=rc.split("^");
	      rc=myAr[0];
	      var bc=myAr[1];
	     }
	     else
	      var bc="0";
	     
	     insertStr=puk+"^"+dc+"^"+uc+"^"+wc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
	   }
 }
 if(transFlg=="2")//Change Of Room/Bed
 {
   //hisValidator.addValidation("strBedType", "dontselect=0","Please select Bed Type!!");
   //hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
   hisValidator.addValidation("strBed", "dontselect=0","Please Select a Bed!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var rc=document.forms[0].strRoom.value;
     if(rc=='0')
     {
     	rc=document.forms[0].strBed.value.split('^')[0];
     }
     var bc=document.forms[0].strBed.value.split('^')[1];
    /* if(bc==obc)
     {
      	alert("Patient cannot be transferred to same Bed!!!.Choose a differnt Bed. ");
       		retVal=false;
     }*/
     insertStr=puk+"^"+myArray1[0]+"^"+myArray1[1]+"^"+owc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
   }
 }
 /*if(transFlg=="3")
 {
   
   hisValidator.addValidation("strDepartment", "dontselect=0","Please select a Department!!");
   hisValidator.addValidation("strUnit", "dontselect=0","Please select a Unit!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var dc=document.forms[0].strDepartment.value;
     var uc=document.forms[0].strUnit.value;
   /*  if(dc==odc && uc==ouc)
     {
       alert("Patient cannot be transferred to same Dept & Unit!!!.Choose at least one different.");
       retVal=false;
     }*/
     //insertStr=puk+"^"+dc+"^"+uc+"^"+owc+"^"+orc+"^"+obc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
   //}
 //}
 
 if(transFlg=="4")
 {
   
   hisValidator.addValidation("strServArea", "dontselect=0","Please select Service Area!!");
   hisValidator.addValidation("strRoom", "dontselect=0","Please select a Room!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var myArray3=new Array();
     myArray3=(document.forms[0].strRoom.value).split("^");
     var rc=myArray3[0];
     var servCd=document.forms[0].strServArea.value;
     insertStr=puk+"^"+myArray1[0]+"^"+myArray1[1]+"^"+owc+"^"+rc+"^"+obc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id+"^"+servCd;
   }
 }
 if(transFlg=="5")
 {
   
   hisValidator.addValidation("strLocation", "req","Location is Mandatory!!");
   retVal = hisValidator.validate();
   if(retVal==true)
   {
     var loc=document.forms[0].strLocation.value;
     insertStr=puk+"^"+myArray1[0]+"^"+myArray1[1]+"^"+owc+"^"+orc+"^"+obc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id+"^"+loc;
   }
 }
 if( transFlg=="6")//Change Of Unit
 {
		hisValidator.addValidation("strDepartment", "dontselect=0","Please select a Department!!");
		if(document.getElementById("unitId")!=null) 
			hisValidator.addValidation("strUnit", "dontselect=0","Please select a Unit!!");
	   
	   //hisValidator.addValidation("strWardType", "dontselect=0","Please select Ward Type!!");
	   hisValidator.addValidation("strWard", "dontselect=0","Please select a Ward!!");
	   //hisValidator.addValidation("strRoomType", "dontselect=0","Please select Room Type!!");
	   /*if(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value=="0")
	   {
	     alert("MS Approval pending.!!!");
	     document.forms[0].strRoom.focus();
	     retVal=false;
	   }
	   else
	   {  
	     */
		
		retVal = hisValidator.validate();
	  // }
  
	   if(retVal==true)
	   {
	     // Movement to same Location Check By Pragya on 02-May-2011
	     var temp=document.forms[0].strCurrentDeptUnitRoom.value.split('^');
	     if(document.getElementsByName("strDepartment")[0].value==temp[0] 
	     	&& ( document.getElementById("unitId")==null || document.getElementsByName("strUnit")[0].value==temp[1] ) 
	     	&& document.getElementsByName("strWard")[0].value.split('^')[0]==document.getElementsByName("combo")[2].value 
	     	&& ( document.getElementById("roomId")==null || document.getElementsByName("strRoom")[0].value==temp[2] )
	     )
	     {
	     	alert("Can't move to Same Department/Unit/Ward/Room");
	     	return false;
	     }
	     
	     
	     var objWrd=document.forms[0].strWard.value;
	     myArr=new Array();
	     myArr=objWrd.split("^"); //MS Approval Required
	     var dc=document.forms[0].strDepartment.value;
	     var uc=document.forms[0].strUnit.value;
	     var wc=document.forms[0].curWrdBedCode.value.split('^')[0];
	    //var myArrayW=new Array();
	   //  myArrayW=(document.forms[0].strWard.value).split("^");
	    // var wc=myArrayW[0];
	    /* if(wc==owc)
	     { 
	       alert("Patient cannot be transferred to same Ward!!!.Choose differnt ward. ");
	       retVal=false;
	     }*/
	     var rc=document.forms[0].strRoom.value;
	     if(myArr[1]=="1") // bed code to be passed for MS Approval Required case.otherwise no bed code taken
	     {
	      var myAr=rc.split("^");
	      rc=myAr[0];
	      var bc=myAr[1];
	     }
	     else
	      var bc="0";
	     
	     insertStr=puk+"^"+dc+"^"+uc+"^"+wc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
	   }
 }
 
 
  if(transFlg=="3")//On Check Of Service Area And Service.
 {
       hisValidator.addValidation("strServiceType", "dontselect=0","Please select a Service Type!!");
   	   hisValidator.addValidation("strServiceName", "dontselect=0","Please select a Service!!");
	   retVal = hisValidator.validate();
	   if(retVal==true)
	   {
	     var wc="0";
	     var dc="0";
	     var uc="0";
	     var rc="0";
	     var bc="0";
	   
	     document.forms[0].transId.value="7";//Change of Service Area/OT
	   
	     trans_Id=document.forms[0].transId.value;
	   
	     insertStr=puk+"^"+dc+"^"+uc+"^"+wc+"^"+rc+"^"+bc+"^"+tr+"^"+ed+"^"+owc+"^"+orc+"^"+obc+"^"+admNo+"^"+trans_Id;
	   
	   /*commented by vikrant*/
	    /* if(document.forms[0].strWhetherVacantBed.checked)
	     	document.forms[0].strWhetherVacantBed.value="1";
	     else
	     	document.forms[0].strWhetherVacantBed.value="0";*/
	   
	    //document.forms[0].strWhetherVacantBed.disabled=false;
	   }
	   var flag=document.forms[0].strValidatedFlag.value;
	   if(flag==0)
	   {
	   	retVal=false;
	    alert("This Service is not raised for the Patient!!!");
	   }
	   
	   
	  
	   
 }
 
   if(retVal==true)
   {
     insertStr=insertStr+"^"+document.forms[0].strRmk.value;
   //  alert("insertStr->>"+insertStr);
   
     //alert(insertStr+"^"+document.forms[0].strRmk.value);
     document.forms[0].strTempVal.value=insertStr;
     document.forms[0].action="/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt";
     document.forms[0].hmode.value="SAVEIPD";  
     document.forms[0].submit();
   }
   else
   {
     return false;
   }  
 }
 else
 {
  alert("No Details to save!!");
  return false;
 }
}


//Onchange Of Service Type Combo,Service Name Combo comes.
function getServiceName(obj)
{
	if(obj.value==0)
	{
			var objVal = document.getElementsByName("strServiceName")[0];
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' onchange='serviceValidate(this)'><option value='0'>Select Value</option></select>";
	}
	else
	{
		var serviceTypeId=obj.value;
		var url='/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?';
		url+='hmode=GETSERVICENAME';
		url+='&serviceTypeId=' + serviceTypeId;
		//alert("1");
		ajaxFunction(url,"444");
		
	}
}






function changeMode()
{
	if(document.getElementsByName("strMode")[1].checked)
	{
		document.getElementById("id1").innerHTML=
						"<input type='hidden' name='transId' value='"+document.forms[0].strHospChange.value+"'/>"+
	             "<input type='hidden' name='strEntryDate' value='"+document.forms[0].strTime.value+"'/>"+
	             "<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"+ 
	             "<tr><td  width='25%' class='LABEL' ><div align='right'>Movement Date/Time</div></td>"+
	              "<td width='75%' colspan='3' class='CONTROL' ><font color='blue'>"+document.forms[0].strTime.value+
	             
	             "</font></td></tr>"+  
	              "<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Location</div></td>"+
	             "<td width='75%' class='CONTROL' colspan='3' >"+
	             document.getElementById("strLocationId").innerHTML.replace("strLocation12","strLocation")+
	              "</td></tr>"+"</table>" ; 
	               
	             document.getElementById("transChng").innerHTML='<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px">'+
	   			' <tr><td width="25%" class="TITLE">Movement To'+
	          	'<select style="cursor: pointer; cursor: hand" name="strTransferUnit"'+
				' dir="ltr" title="Transfer to/between Clinical Unit"'+
				'onChange="return transferOf(this);">'+
				'<option value="5" title="Transfer to/between Hospital">Outside Hospital</option></select>'+
	        	'</td></tr></table>'; 
	          document.getElementsByName("strTransferUnit")[0].value="5";
	          if(!document.getElementsByName("strTransferUnit")[0].disabled) 
	          {
	          	document.getElementsByName("strTransferUnit")[0].disabled=true;
	          }
	          document.getElementById("reasonID").innerHTML='<font color="red">*</font>Reason for Movement:';    	
	}
	else
	{
		 document.getElementById("transChng").innerHTML='<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px">'+
		' <tr><td width="25%" class="TITLE">Transfer To'+
      	'<select style="cursor: pointer; cursor: hand" name="strTransferUnit"'+
		'class="comboNormal" dir="ltr" title="Transfer to/between Clinical Unit"'+
		'onChange="return transferOf(this);">'+
		'<option value="1" title="Transfer to/between Ward">Dept/Ward</option>'+
		'<option value="2" title="Transfer to/between Bed">Room/Bed</option>'+
		'</td></tr></table>'; 
		 document.getElementById("reasonID").innerHTML='<font color="red">*</font>Reason for Transfer:'; 
		 var mode="transOf";
	 	var currDtUtWrRmBd=document.forms[0].curDtUtWrRmBd.value;
		var url=document.forms[0].cnt.value+"?hmode="+mode+"&modName=1&currDtl="+currDtUtWrRmBd;
	   ajaxFunction(url,"111");
		
	}
	
	
}
function enableUnitCombo(obj)
{
		var temp=document.forms[0].strCurrentDeptUnitRoom.value.split('^');
		if(document.forms[0].strDepartment.value==temp[0])
		{
			if(obj.checked)
			{
				document.getElementsByName("strUnit")[0].disabled=false;
			}
			else
			{
				document.getElementsByName("strUnit")[0].disabled=true;
				var temp=document.forms[0].strCurrentDeptUnitRoom.value.split('^');
				var unit=temp[1];
       			document.forms[0].strUnit.value=unit;
       			funward();
			}
		}
		else
		{
			document.getElementsByName("strUnit")[0].disabled=false;
		}		
}

function serviceValidate(obj)
{
	if(obj.value!=0)
	{
	var serviceTypeId=document.forms[0].strServiceType.value;
	var strServiceName=obj.value;
	var strCrNo=document.forms[0].strCrNo.value;
	var url='/AHIMS/ipd/transactions/PatientTransferTransCNT.cnt?';
		url+='hmode=SERVICEVALIDATION';
		url+='&serviceTypeId=' + serviceTypeId;
		url+='&strServiceName=' + strServiceName;
		url+='&strCrNo=' + strCrNo;
		ajaxFunction(url,"666");
	}
	
}