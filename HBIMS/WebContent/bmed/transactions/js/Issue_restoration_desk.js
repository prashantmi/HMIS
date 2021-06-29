function ClearPage()
{
  	 document.forms[0].reset();
}
function cancelPage()
{
	
    document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}

function changeMode(chkObj)
{ 	
    if(chkObj.value =='1')
 	{ 	
 		 document.forms[0].strStatus.value = '1'; 
 		 document.forms[0].strIsApproved.checked = true;
 		 document.forms[0].strIsReject.checked = false;
 		 
 	}
 	if(chkObj.value =='2')
 	{ 			 
 		 document.forms[0].strStatus.value = '2'; 
 		 document.forms[0].strIsApproved.checked = false;
 		 document.forms[0].strIsReject.checked = true;
 		 
 	}
}


function check()
{
	document.forms[0].strIsApproved.checked = true;
	document.forms[0].strIsReject.checked = false;
	document.forms[0].strStatus.value = '1'; 
}

function validate1() 
{
	if(document.forms[0].strIsApproved.checked || document.forms[0].strIsReject.checked)
  	{
	
          var hisValidator = new HISValidator("hemComplaintAppDeskFB");
          hisValidator.addValidation("strRemarks", "req","Remarks is a Mandatory Field");
  	  	
  	      var retVal = hisValidator.validate(); 
          hisValidator.clearAllValidations();
           if(retVal)
           {
                 	  document.forms[0].hmode.value = "SAVE";
                      document.forms[0].target = "_self";
                      document.forms[0].submit();
           }
           else
           {
           	           document.forms[0].strRemarks.focus(); 
                       return false;
           }
  	}
  	else
    {
    	document.forms[0].strIsApproved.focus();
                       alert("Approval is a Mandatory");
                       return false;
    }

}

function userDefinedOnLoadFunc()
{
	 
}
function buttonLogicsOnRecordCheck(these)
{  

     //alert(document.forms[0].chk.checked);
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
	     disableButton("Return");
		 disableButton("Cancel"); 	    	
   	     return false;
   	   }
       else
       {   
         
         /*enableButton("View");
        //disableButton("Generate");
         var tmp = document.getElementById("comboid2").value.split("^");
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
	   
	   if(document.getElementById("comboid3").value =="11")
	   {
	   //	alert("hiiii@!!")
	   	enableButton("Issue");
	   	return;
	   }
	   
	    if(document.getElementById("comboid3").value =="4")
	   {
	   	enableButton("Restoration");
	   return;
	   }
	   
	   }
	   
}
function chkUserDefinedFunc(these)
{
   
}


function buttonLogicsOnClick1(modeNo, mode , display)
{
        		
		     if(document.getElementById("comboid0").value =="0")
		     {
				alert("Please Select Hospital Name");
				document.getElementById("comboid0").focus();
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
   	 		 if(count!='0' || count=='1')
   	 		 {
   	 		   if(mode=='ISSUE' || mode=='ISSUE')
   	 		   {
   	 		 	add(mode);
   	 		   }  	
   	 		    if(mode=='PENDING' || mode=='PENDING')
   	 		   {
   	 		 	 add(mode);
   	 		   }  	 
   	 		    if(mode=='InitializeRestoration'|| mode=='InitializeRestoration')
   	 		   {
   	 		 		add(mode);

   	 		   }
   	 		    if(mode=='InitializeApproval'|| mode=='InitializeApproval')
   	 		   {
   	 		  	 		 	add(mode);
  
   	 		   }  	   	 
   	 		   	 	
   	 		 }
   	 		 else
   	 		 {
   	 		 	alert("Please select single record!!");
   	 		 	return;
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

function  Allocation()
{
     //document.getElementById('message').style.display='none';

//	if(checkForAllocation()==false)
	
	//{alert("False");
		// return false;
	//}
	 var chkObj = document.getElementsByName("chk");
	var chk_temp	=	chkObj.length;
	var chk			=	"";
	
	
//	var mode = "VIEWDATA";	
	/*
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	*/
	var cnt_page	=	document.forms[0].action;
	alert("hiii");	
	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) 
			{
				chk =document.forms[0].chk[i].value;
				chkValue= chk;
				alert(chkValue);
				
			}
		}	
   }
}            
