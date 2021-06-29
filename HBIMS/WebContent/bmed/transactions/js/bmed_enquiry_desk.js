var chkValue	=	"";


function userDefinedOnLoadFunc()
{
	 
}
function buttonLogicsOnRecordCheck1(these)
{  

      //alert("sarat::"+document.forms[0].chk.checked);
       var chkObj  = document.getElementsByName("chk");
       var count = parseInt("0");
   	   for(var i=0; i<chkObj.length; i++) 
   	   {
   	   //  alert(chkObj[i].checked);
   	  		if(chkObj[i].checked)
   	  		{
   	 		  count = count + 1;
   	 	    }	
   	 	     	 	       	 	    	
   	   }
   	   if(count > 1)
   	   {
   	     alert("Plz Select Single Record at a time!!!!!");
   	     for(var i=0; i<chkObj.length; i++) 
   	     {
   	       	chkObj[i].checked=false;
   	  	 }	 	
   	  	 disableButton("View");
   	  	 disableButton("Archieve");
   	  	 disableButton("Warranty View");
   	  	 disableButton("Warnty Archieve");
	     //disableButton("Return");
		 //disableButton("Cancel"); 	    	
   	     return false;
   	   }
       else
       {   
         
         enableButton("View");
   	  	 enableButton("Archieve");
   	  	 enableButton("Warranty View");
   	  	 enableButton("Warnty Archieve");
        //disableButton("Generate");
        /* var tmp = document.getElementById("comboid2").value.split("^");
         var reqType = tmp[1];
         var status  = document.getElementById("comboid3").value;
         if(reqType == "16" || reqType == "18" || reqType=="19")
         {
            if(status == "2")
            {
             enableButton("Return");
             return;
             
            }
            else
            {  
              if(status =="0")
	          {
			    enableButton("Cancel");
			    return;
	          }
              disableButton("Return");
              return;
            }  
         }
         if(status =="0")
	     {
			enableButton("Cancel");
			return;
	     }*/
	   }
}
function buttonLogicsOnRecordCheck(these)
{	//alert("haha");
    var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");
	
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
		}			
	}
	try
	{
	//alert("checkCount"+checkCount);
		if(checkCount=='1')
		{
		
	    	}
		  /*var status ;
		  var complType;
		  var amcComplaint = check[checkCount-1].value.split("@")[2].split("$")[0];
		  
		  if(amcComplaint=='0')
		  {
		  	    status = document.getElementById("comboid3").value;
		  }
		  else
		  {
		  	 complType = document.getElementById("comboid3").value;
		  	    status = document.getElementById("comboid4").value;
		  }
		  	  
		  if(amcComplaint=='0')
		  {
				  if(status == "1")
		          {
		             //enableButton("Schedule");
		             enableButton("View");
		             //enableButton("Reminder Reply");
		             //enableButton("Close");
		             return;
		          }
		          else
		          {
		          	  if(status == "2")
			          {
			             //enableButton("Schedule");
			             enableButton("View");
			             //enableButton("Attend");
			             //enableButton("Reminder Reply");
			             //enableButton("Close");
			             return;
			          }
			          else
			          {
			          	 enableButton("View");
			             return;
			          	
			          }
		          }
		          
		  }
		  else
		  {
		  	 if(complType=='0')
		  	 {
		  	 	
		  	      if(status == "1")
		          {
		                 //enableButton("Schedule");
			             enableButton("View");
			             //enableButton("Grievances");
			             //enableButton("Reminder Reply");
			             //enableButton("Close");
		             return;
		          }
		          else
		          {
		          	  if(status == "2")
			          {
			             //enableButton("Schedule");
			             enableButton("View");
			             //enableButton("Grievances");
			             //enableButton("Attend");
			             //enableButton("Reminder Reply");
			             //enableButton("Close");
			             return;
			          }
			          else
			          {
			          	 enableButton("View");
			             return;
			          	
			          }
		          }
		  	 }
		  	 else
		  	 {
		  	 	
		  	 }
		  	
		  }*/
		   
		
		else
		{
			for(i=0;i<check.length;i++)
			{
				check[i].checked=false;
			}
			if(checkCount!='0')
			{
				alert("Please Select One Record!!!");	
			}
			
			return false;
		}
		
		
	}
	catch(Err)
	{
		alert(Err);
	}
}


function buttonLogicsOnClick1(modeNo, mode , display)
{
	//alert("hihi");
	var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");
	
	
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
		}			
	}
	if(checkCount<1)
	{
		alert("Please Select One Record");
		return false;
	}
	try
	{
	//alert("mode of install::"+mode)

		if(mode=='ADDCLASSIFICATION' || mode=='ADDCLASSIFICATION'){
		/*if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				return;
			}
			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select Engineering Item Type");
				document.getElementById("comboid1").focus();
				return;
			}
			
			if(document.getElementById("comboid2").value =="0")
			{
				alert("Please Select Engineering Item Sub Type");
				document.getElementById("comboid2").focus();
				return;
			}*/
			add(mode);
		}
		
		else if(mode=='ADDBUILDING' || mode=='ADDBUILDING'){
	
			add(mode);
		}
		else if(mode=='ADDFLOOR' || mode=='ADDFLOOR'){
	
			add(mode);
		}
		else if(mode=='ADDROOM' || mode=='ADDROOM'){
	
			add(mode);
		}
		else if(mode=='ADDINSTALLATION' || mode=='ADDINSTALLATION'){
	
			add(mode);
		}
		else if(mode=='ADDMINOR' || mode=='ADDMINOR'){
	
			add(mode);
		}
		else if(mode=='ADDSUBHEAD' || mode=='ADDSUBHEAD'){
	
			add(mode);
		}
		else if(mode=='ADDDETAILHEAD' || mode=='ADDDETAILHEAD'){
	
			add(mode);
		}
		else if(mode=='ADDBLOCK' || mode=='ADDBLOCK'){
	     	add(mode);
		}
		else if(mode=='BLOCK' || mode=='BLOCK'){
	      //alert("In block mode");
	     	add(mode);
		}else if(mode=='BUILDING' || mode=='BUILDING'){
	     	add(mode);
		}else if(mode=='FLOOR' || mode=='FLOOR'){
	     	add(mode);
		}else if(mode=='ROOM' || mode=='ROOM'){
	     	add(mode);
		}else if(mode=='INSTALLATION' || mode=='INSTALLATION'){
	     	add(mode);
		}
		else if(mode=='SHOWREPORT' || mode=='SHOWREPORT'){
	     	add(mode);
		}
		
		else if(mode=='Inventory' || mode=='Inventory'){
   
		    Allocation();
		    var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			//alert(" view :  "+url);	
			openPopUp_master(url,'600','300','1');
		
		
		}
		
		else if(mode=='MaintananceEnquiry' || mode=='MaintananceEnquiry'){
			Allocation();
	        var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode=MaintananceEnquiry&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			//alert(" view :  "+url);	
			openPopUp_master(url,'700','400','1');
			
  		}
		else if(mode=='WarrantyEnquiry' || mode=='WarrantyEnquiry'){
			Allocation();
	        var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode=WarrantyEnquiry&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
		  //  alert(" view :  "+url);	
			openPopUp_master(url,'700','400','1');
			
		}
		else if(mode=='InstallationEnquiry' || mode=='InstallationEnquiry'){
	
			Allocation();
		    var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode=InstallationEnquiry&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			//alert(" view :  "+url);	
			openPopUp_master(url,'600','300','1');
				
	 	}
		
		else if(mode=='ManPowerEnquiry' || mode=='ManPowerEnquiry'){
			Allocation();
		    var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			//alert(" view :  "+url);	
			openPopUp_master(url,'600','300','1');
		}
		
		else if(mode=='UpTimeEnquiry' || mode=='UpTimeEnquiry'){
			Allocation();
 		 
	        var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode=UpTimeEnquiry&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
		  //  alert(" view :  "+url);	
			openPopUp_master(url,'700','400','1');
			
		}
		else if(mode=='DownTimeEnquiry' || mode=='DownTimeEnquiry'){
			Allocation();
		    var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			//alert(" view :  "+url);	
			openPopUp_master(url,'600','300','1');
				
		}
		else if(mode=='ComplaintEnquiry' || mode=='ComplaintEnquiry'){
            Allocation();
		    var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			//alert(" view :  "+url);	
			openPopUp_master(url,'600','300','1');		}
		
		else if(mode=='SHOWREPORT' || mode=='SHOWREPORT'){
	     	add(mode);
		}
		
		
		
		
		else if(mode=='SHOWRPT'){
		if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				return;
			}
			if(document.getElementById("comboid3").value =="0")
			{
				alert("Please Select Process Type");
				document.getElementById("comboid3").focus();
				return;
			}
			add(mode);
            }
  
		else{
		if(checkCount=='1')
		{
			if(document.getElementById("comboid3").value =="1" && mode=='VIEWWARRANTY')
			{
				alert("Please Select a Warranty type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			}
			else if(document.getElementById("comboid3").value =="2" && mode=='VIEW')
			 {
			 	alert("Please Select a Maintenance type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			 }
			 
			 if(document.getElementById("comboid3").value =="1" && mode=='ARCHIEVEWARRANTY')
			{
				alert("Please Select a Warranty type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			}
			else if(document.getElementById("comboid3").value =="2" && mode=='ARCHIEVE')
			 {
			 	alert("Please Select a Maintenance type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			 }
			 else
			 	add(mode); 	
		}
		else
		{
			for(i=0;i<check.length;i++)
			{
				check[i].checked=false;
			}
			alert("Please Select One Record!!!");
			return false;
		}
		}
	}
	catch(Err)
	{
		alert(Err);
	}
				
		

}
function buttonLogicsOnClick2(modeNo, mode , display)
{
       if(modeNo != 7)
		{
		
		   var chkObj = document.getElementsByName("chk");
   	 
   			 var count = parseInt("0");
   	 
   	 		 for(var i=0; i<chkObj.length; i++) 
   	 		 {
   	 			if(chkObj[i].checked)
   	 			{
   	 				count = count + 1;
   	 		    }		
   	 		
   	 		 }
   	 	   	  add(mode); 			
		}
		else
		{
			 //add(mode);
		}

}

function buttonLogicsOnClickCancel(modeNo, mode , display)
{
	if(document.getElementById("comboid3").value !="0")
	{
				alert("Please Select Status");
				document.getElementById("comboid3").focus();
				return;
	}
    var chkObj = document.getElementsByName("chk");
   	 
   	var count = parseInt("0");
   	 
   	for(var i=0; i<chkObj.length; i++) 
    {
   	 	
   	 				if(chkObj[i].checked)
   	 				{
   	 			
   	 					count = count + 1;
   	 			
   	 				}		
   	 		
   	}
   	if(count!=0 && count == '1') 
   	{
   	     res=prompt("ENTER REMARKS FOR CANCELATION!","");
         if(!res=="")
         {
           var conf = confirm("Are you sure !!!");
           if(conf == true)
           {
             var chkObj = document.getElementsByName("chk");  
             for(var i=0; i<chkObj.length; i++) 
   	 	     {
   	 	       if(chkObj[i].checked)
   	 		   {
   	 		       chkObj[i].value = chkObj[i].value+"^"+res;
   	 		   }		
   	 		 }
   	 		 add(mode);
           }
           else
           {
             return false;
           }
           
          }
          else
          {
           if(res=="")
           { 
            alert("Enter remarks for rejection");
           } 
          }
      }
      else
      {
           if(res=="")
           { 
            alert("Enter remarks for rejection");
           } 
           else
           {
             if(count>1)
                   alert("Please Select Single Record at a Time!!!");
               else
                   alert("Please Select Record !!!!!");
             
           } 
          return false;
      }
   	
			
}		




function buttonLogicsOnClickReturn(modeNo, mode , display)
{
	   if(modeNo != 7)
		{
		   if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				return;
			}
			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Item Category");
				document.getElementById("comboid1").focus();
				return;
			}
			
			if(document.getElementById("comboid2").value =="0")
			{
				alert("Please Select A Indent Type");
				document.getElementById("comboid2").focus();
				return;
			}
			
			if(document.getElementById("comboid3").value =="0")
			{
				alert("Please Select Status");
				document.getElementById("comboid3").focus();
				return;
			}
    		 var chkObj = document.getElementsByName("chk");
   	 
   			 var count = parseInt("0");
   	 
   	 		 for(var i=0; i<chkObj.length; i++) 
   	 		 {
   	 	
   	 				if(chkObj[i].checked)
   	 				{
   	 			
   	 					count = count + 1;
   	 			
   	 				}		
   	 		
   	 			}
   	 		
   	 	 if(count > 1)
   		 {
   	 		alert("Please Select A Single Record");
   	 		return false;
   		  }
   		  else
   		  {
   		     add(mode);
   		  }
   		 
   	    }
   	    else
   	    {
   	     return false;
   	    }
   	    
  } 	    

function buttonLogicsOnClickPrint(modeNo, mode , display)
{
  var retVal = false;
 
	    if(modeNo == 5)
		{
		   if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				disableButton("Print");
				return;
			}
			else			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Item Category");
				document.getElementById("comboid1").focus();
				disableButton("Print");
				return;
			}
			else
			if(document.getElementById("comboid2").value =="0")
			{
				alert("Please Select A Request Type");
				document.getElementById("comboid2").focus();
				disableButton("Print");
				return;
			}
			
    		 retVal = true;
   		     
   		}
   		
   		if(retVal)
   		{
   		  enableButton("Print");
   		  document.forms[0].target = "_blank";
   		  add(mode);
   		}
   		else
   	    {
   	     return false;
   	    }
} 	    
/************************************************************/
function validate1() 
{
	 var hisValidator = new HISValidator("budgetControlDelegationDeskFB");
	 
	 /*hisValidator.addValidation("strEnggItemTypeId", "dontselect=0",	"Please Select a Engg Item Type");
	 hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0",	"Please Select a Engg Item Sub Type");
	 hisValidator.addValidation("strServiceEnggId", "dontselect=0",	"Please Select a Service Engineer Name");
	 hisValidator.addValidation("strEscalationLevelId","dontselect=0","Escalation Level is a mandatory field");*/
     hisValidator.addValidation("strClassificationCode","req","Classification Code  is a mandatory field");
     hisValidator.addValidation("strClassificationName","req","Classification Name is a mandatory field");
	 
  	  var retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();
  	
	if(retVal)
	{		
	//alert("good to know");
		 document.forms[0].hmode.value = "SAVECLASSIFICATION";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}       	
}



 
function cancelPage()
{
	
    document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}

function getEnggItemSubTypeCombo()
{	   
   var itemParaObj = document.getElementById("serviceEnggNameID");
   itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' ><option value='0'>Select Value</option></select>";
   
   var itemParaObj1 = document.getElementById("esclationLevelId");
   itemParaObj1.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  ><option value='0'>Select Value</option></select>";
   
   
   var itemParaObj1 = document.getElementById("deptId");
   var itemParaObj2 = document.getElementById("contactNo");
   var itemParaObj3 = document.getElementById("emilaId");
		            
   itemParaObj1.innerHTML = "";
   itemParaObj2.innerHTML = "";
   itemParaObj3.innerHTML = "";
	
   var url="HemDeskACTION.cnt?hmode=GETENGGITEMSUBTYPE&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"1");
} 

function getServiceEnggNameCmb()
{	   
   var url="HemDeskACTION.cnt?hmode=GETSERVICEENGG&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
   ajaxFunction(url,"2");
} 

function getEscLevelCmb()
{	   
   var url="HemDeskACTION.cnt?hmode=GETESCLEVEL&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
   ajaxFunction(url,"3");
}


function getDetails()
{	   
   var url="HemDeskACTION.cnt?hmode=GETDETAILS&escalationLevelId="+document.forms[0].strEscalationLevelId.value;
   ajaxFunction(url,"4");
   
}


function getAjaxResponse(res,mode)
{
	   var objVal;
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
         return;
       } 	    	    	    
	    if(mode=="1")
		{	
	       	            var itemParaObj = document.getElementById("engineeringItemSubTypeId");
	       	            itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL' onChange='getServiceEnggNameCmb();'>" + res + "</select>";
	    }
	    
	    if(mode=="2")
		{	
	       	            var itemParaObj = document.getElementById("serviceEnggNameID");
	       	            itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' >" + res + "</select>";
	       	            getEscLevelCmb();
	    }
	    
	    if(mode=="3")
		{	
	       	            var itemParaObj = document.getElementById("esclationLevelId");
	       	            itemParaObj.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  onChange='getDetails();'>" + res + "</select>";
	    }
	    
	    if(mode=="4")
		{	
			            var arrVal = res.split("^");
			            var itemParaObj1 = document.getElementById("deptId");
			            var itemParaObj2 = document.getElementById("contactNo");
			            var itemParaObj3 = document.getElementById("emilaId");
			            document.getElementById("strEscDtl").value=res;			            
			            
			            //            E-Mail                Mob No             Designation          Department
			            if(arrVal[3]!=' '||arrVal[3]!='')
			            {
			              itemParaObj1.innerHTML = arrVal[3];
			            } 
			            else
			            {
			              itemParaObj1.innerHTML = "----";	
			            }
			            if(arrVal[1]!=' '||arrVal[1]!='')
			            { 
			              itemParaObj2.innerHTML = arrVal[1];
			            }
			            else
			            {
			              itemParaObj2.innerHTML = "----";	
			            }
			            if(arrVal[0]!=' '||arrVal[0]!='')
			            {  
			              itemParaObj3.innerHTML = arrVal[0];
			            } 
			            else
			            {
			              itemParaObj3.innerHTML = "----";	
			            } 
			            
			            
	       	            
	    }
}



function clearPage()
{
	var strComplaintId = document.forms[0].strComplaintId.value;
	var strHemDesk = document.forms[0].strHemDesk.value;
	var strChk = document.forms[0].strChk.value;
	
	document.forms[0].reset();
	
	document.forms[0].hmode.value="GRIEVANCES";
	document.forms[0].strComplaintId.value=strComplaintId;
	document.forms[0].strHemDesk.value = strHemDesk;
	document.forms[0].strChk.value = strChk;
	
	document.forms[0].submit();
}


function  Allocation()
{
     //document.getElementById('message').style.display='none';

//	if(checkForAllocation()==false)
	
	//{alert("False");
		// return false;
	//}
	var chk_temp	=	document.forms[0].chk.length;
	var chk			=	"";
	
//	var mode = "VIEWDATA";	
	/*
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	*/
	var cnt_page	=	document.forms[0].action;
		
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) 
			{
				chk =document.forms[0].chk[i].value;
				chkValue= chk;
			//	alert(chkValue);
				
			}
			
		}
	}
	else
	{
		chk =document.forms[0].chk.value;
			chkValue= chk;
	}
	
	var  heightValue = document.forms[0].view_row_length.value * 40;
		
//	var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chk+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
	
	//alert(" view :  "+url);	

		//alert("Hi I M checked!!!");
	//openPopUp_master(url,'700',heightValue,'1');
	//var myPopup     =  window.open(,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');
	try{
     	autoTabIndexing();
    }catch(_Err){
     //	alert("Application Error[TabIndex not Included] Please Contact System Administrator");
 	}
}