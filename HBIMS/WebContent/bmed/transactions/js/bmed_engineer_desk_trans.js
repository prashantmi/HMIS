function userDefinedOnLoadFunc()
{
	 
}
function buttonLogicsOnRecordCheck1(these)
{  

     //  alert(document.forms[0].chk.checked);
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
   	  	 disableButton("Acknowledgment");
	     disableButton("Service Action");
		 return false;
   	   }
       else
       {   
         
         var status  = document.getElementById("comboid1").value;
         if(status=="6")
         {
         	enableButton("Acknowledgment");
         	disableButton("Service Action");
         	return;
         }
         if(status=="9")
         {
         	enableButton("Service Action");
         	disableButton("Acknowledgment");
         	return;
         }
	   }
}
function buttonLogicsOnRecordCheck(these)
{
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
		var status  = document.getElementById("comboid1").value;
         if(status=="6")
         {
         	enableButton("Acknowledgment");
         	disableButton("Service Action");
         	return;
         }
         if(status=="9")
         {
         	enableButton("Service Action");
         	disableButton("Acknowledgment");
         	return;
         }
        
		}
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
		
		if(checkCount=='1')
		{
 
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
	 var hisValidator = new HISValidator("hemmsEngineerDeskFB");
	 
	 /*hisValidator.addValidation("strEnggItemTypeId", "dontselect=0",	"Please Select a Engg Item Type");
	 hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0",	"Please Select a Engg Item Sub Type");
	 hisValidator.addValidation("strServiceEnggId", "dontselect=0",	"Please Select a Service Engineer Name");
	 hisValidator.addValidation("strEscalationLevelId","dontselect=0","Escalation Level is a mandatory field");
     hisValidator.addValidation("strName","req","Name is a mandatory field");
     hisValidator.addValidation("strDesignation","req","Designation is a mandatory field");
	 hisValidator.addValidation("strModeofEscId","dontselect=0","Mode Of Escalation is a mandatory field");
	 hisValidator.addValidation("strEscDate","req","Date Of Escalation is a mandatory field");
	 hisValidator.addValidation("strEscTime","req","Time Of Escalation is a mandatory field");
     
  	  var retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();*/
  	document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	//if(retVal)
	/*var complaintStatus = document.getElementById("strComplaintStatus").value;
	alert(complaintStatus);
	if(complaintStatus=="3")
	{		
	
		 document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="4")
	{		
	
		 document.forms[0].hmode.value = "SAVESCHEDULE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="5")
	{		
	
		 document.forms[0].hmode.value = "SAVEATTEND";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}      */  	
}

function validate2() 
{
	 var hisValidator = new HISValidator("hemmsEngineerDeskFB");
	 
	 /*hisValidator.addValidation("strEnggItemTypeId", "dontselect=0",	"Please Select a Engg Item Type");
	 hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0",	"Please Select a Engg Item Sub Type");
	 hisValidator.addValidation("strServiceEnggId", "dontselect=0",	"Please Select a Service Engineer Name");
	 hisValidator.addValidation("strEscalationLevelId","dontselect=0","Escalation Level is a mandatory field");
     hisValidator.addValidation("strName","req","Name is a mandatory field");
     hisValidator.addValidation("strDesignation","req","Designation is a mandatory field");
	 hisValidator.addValidation("strModeofEscId","dontselect=0","Mode Of Escalation is a mandatory field");
	 hisValidator.addValidation("strEscDate","req","Date Of Escalation is a mandatory field");
	 hisValidator.addValidation("strEscTime","req","Time Of Escalation is a mandatory field");
     
  	  var retVal = hisValidator.validate(); 
      hisValidator.clearAllValidations();*/
  	document.forms[0].hmode.value = "SAVESERVICE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	//if(retVal)
	/*var complaintStatus = document.getElementById("strComplaintStatus").value;
	alert(complaintStatus);
	if(complaintStatus=="3")
	{		
	
		 document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="4")
	{		
	
		 document.forms[0].hmode.value = "SAVESCHEDULE";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}
	if(complaintStatus=="5")
	{		
	
		 document.forms[0].hmode.value = "SAVEATTEND";
	     //document.forms[0].target = "_self";
	     document.forms[0].submit();
	}      */  	
}


 
function cancelPage()
{
	
    document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
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
function tableShow(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HEMMS_ODISHA/hisglobal/images/minus.gif");

}

function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HEMMS_ODISHA/hisglobal/images/plus.gif");

}
function getAttendName()

{

var obj= document.forms[0].strVendorServiceEngName;

document.forms[0].strAttendedContactPerson.value= getSelectedLabel(obj);

}

function isSparePartsMaintenanceInvolved()
{
	//getSpareParts();
  var div1=document.getElementById("spareDiv");

	//if(document.forms[0].strIsSparePartsMaintenanceInvolved.checked)
	if(document.getElementsByName("strIsSparePartsMaintenanceInvolved").checked)
	{
	  		 div1.style.display='block';
	 		 document.forms[0].strIsSparePartsMaintenanceInvolved.value="1";
	      	 
	 }
	else{
	div1.style.display='none';
	document.forms[0].strIsSparePartsMaintenanceInvolved.value="0";
	}
}

function isSparePartsUsed()
{
	//getSpareParts();
  var div1=document.getElementById("id1");
  var elementTable = document.getElementById("SparePartMultiRowId");
	

	//if(document.forms[0].strIsSparePartsMaintenanceInvolved.checked)
	if(document.forms[0].strIsSparePartsMaintenanceInvolved.value=="1")
	{
	elementTable.style.display = "block";
	  		 div1.style.display='block';
	 		 document.forms[0].strIsSparePartsMaintenanceInvolved.value="1";
	      	 
	 }
	else{
	elementTable.style.display = "none";
	div1.style.display='none';
	document.forms[0].strIsSparePartsMaintenanceInvolved.value="0";
	}
}
function getSpareParts()

{

var url="HemmsEngineerDeskACTION.cnt?hmode=initializeSpareParts&flag=1&itemId="+document.forms[0].strItemId.value+"&storeId="+document.forms[0].strStoreId.value+"&enggItemType="+document.forms[0].strEnggItemTypeId.value+
"&enggItemSubType="+document.forms[0].strEnggItemSubTypeId.value+"&strPageFlag="+document.forms[0].strPageFlag.value;
    
  
    ajaxFunction(url,"17");

 
}
function getAjaxResponse(res,mode)
{
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 

	if(mode=="1")
	   { 
     
			if(res=="")
			{
				document.getElementById("enggItemSubTypeCmbDivId").innerHTML="<select name ='strEngineeringItemSubTypeId' onchange='getServiceEngName();' class='COMBO_NORMAL' tabindex='1' ><option value='0'>Select Value</option></select>";
				
				
			}
			else
			{
				var objVal= document.getElementById("enggItemSubTypeCmbDivId");
				if(document.forms[0].strPageFlag.value=="NON_ITEM")
				{
					
					
					objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='COMBO_NORMAL' onchange='getNonItemName();emptyDetailsForNonItem();getServiceEngName();' tabindex='1' >"+res+"</select>";
					
				}
				else
				{
				
					
					objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' onchange='getServiceEngName();' class='COMBO_NORMAL' tabindex='1' >"+res+"</select>";
					
					
				}				
			}
		}	


    if(mode=="2")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("stockValueId");
       	            	itemParaObj.innerHTML = res;
          	  		//ajaxCallGetDataTrans('xyz', 'trans', document.forms[0].strItemId.value);
        }       
        
        // var objVal1 =  document.getElementById("prevCompDtlId");
		 var objVal2 =  document.getElementById("warrantyDetailsTable");
		 var objVal3 =  document.getElementById("maintenanceContractDetailsTable");
		 
  		// objVal1.innerHTML ="";
  		  objVal2.innerHTML ="";
  		   objVal3.innerHTML ="";
    }


   
	
	if(mode=="3")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("itemNameId");
       	            itemParaObj.innerHTML ="<select name = 'strItemId' onchange='getStockDtl();' class='COMBO_NORMAL'>" + res + "</select>";
        }       
    }
    
    if(mode=="4")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("storeNameId");
       	          
       	            itemParaObj.innerHTML ="<select name = 'strStoreId' onchange='getItemNameOnBasisOfStore();' class='COMBO_NORMAL'>" + res + "</select>";
        }  

getEmpCombo();    
    }
    
    if(mode=="5")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("itemNameId");
       	            itemParaObj.innerHTML ="<select name = 'strItemId' onchange='getStockDtl();' class='COMBO_NORMAL'>" + res + "</select>";       
        }       
    }    
    
    //	Warranty Details
     if(mode=="6")
	{	
	   
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("warrantyDetailsTable");
       	            	itemParaObj.innerHTML = res.split("**")[0];
          	  		
          	  		getMaintenanceContractDetails(res.split("**")[1]);
        }       
    }
    
    //PREVIOUS COMPLAINT DTL
     if(mode=="7")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	          //  var itemParaObj = document.getElementById("prevCompDtlId");
       	            //	itemParaObj.innerHTML = res.split("**")[0];
          	  		//ajaxCallGetDataTrans('xyz', 'trans', document.forms[0].strItemId.value);
          	  		getWarrantyDtl(res.split("**")[1]);
        }       
    }
    
    //	Maintenance Contract Details
     if(mode=="8")
	{	
	   
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("maintenanceContractDetailsTable");
       	            	itemParaObj.innerHTML = res;
          	  		//ajaxCallGetDataTrans('xyz', 'trans', document.forms[0].strItemId.value);
        }       
    }
    
     //	Get Non-Item Name
     if(mode=="9")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("itemNameId");
       	            itemParaObj.innerHTML ="<select name = 'strNonItemId' onchange='getPreviousComplaintDtl();' class='COMBO_NORMAL'>" + res + "</select>";
        }       
    }
    
    //NON_ITEM PREVIOUS COMPLAINT DTL
     if(mode=="10")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	           // var itemParaObj = document.getElementById("prevCompDtlId");
       	            	//itemParaObj.innerHTML = res.split("**")[0];
          	  		
          	  		//getWarrantyDtlForNonItem(res.split("**")[1]);
        }       
    }
    
     //Non-Item	Warranty Details
     if(mode=="11")
	{	
	   
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("warrantyDetailsTable");
       	            	itemParaObj.innerHTML = res.split("**")[0];
          	  		getMaintenanceContractDetailsForNonItem(res.split("**")[1]);
        }       
    }
    
    // Non-Item	Maintenance Contract Details
     if(mode=="12")
	{	
	   
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("maintenanceContractDetailsTable");
       	            	itemParaObj.innerHTML = res;
          	  		
        }       
    }
    
    if(mode=="13")
    {
      var temp1 = res.split("#");
      
      
      document.getElementById("vendorcontactNoDivId").innerHTML=temp1[0];
      document.getElementById("vendorAddrDivId").innerHTML=temp1[1];
       
    
    
    }
    
    if(mode=="14")
    {
    
   
    
     var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
	   	
         err.innerHTML = temp1[1];	
       }
       else
       {
        
       	            var itemParaObj = document.getElementById("strVendorServiceEngId");
       	            itemParaObj.innerHTML ="<select name = 'strVendorServiceEngName' onchange='getAttendName()' class='COMBO_NORMAL'>" + res + "</select>";   
       	            
       	            
       	            var itemParaObj2 = document.getElementById("strVerifiedById");
       	            itemParaObj2.innerHTML ="<select name = 'strVerifiedBy' onchange='getAttendName()' class='COMBO_NORMAL'>" + res + "</select>";    
       }       
    
  
    }
    
     if(mode=="15")
    {
    
   
    
     var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
	   	
         err.innerHTML = temp1[1];	
       }
       else
       {
        
       	            var itemParaObj = document.getElementById("strEmpDivId");
       	            itemParaObj.innerHTML ="<select name = 'strEmpId' onchange='getEmpDesignation()' class='COMBO_NORMAL'>" + res + "</select>";   
       	       
       	           
       }       
    
      
    }
    
      if(mode=="16")
    {
    
   
    
     var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
	   alert(err);
         err.innerHTML = temp1[1];	
       }
       else
       {
        			
       	            var itemParaObj = document.getElementById("strDesignationDivId");
       	            itemParaObj.innerHTML = res ;   
       	            
       	       
       	           
       }       
    
      
    }
    
    if(mode=="17")
    
    {
    
    		 var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
	   alert(err);
         err.innerHTML = temp1[1];	
       }
       else
       {
        		
        		 var temp1 =res.split("#");
        			
       	            var itemParaObj = document.getElementById("strSparePartStatusDivId");
       	            itemParaObj.innerHTML ="<select name = 'strSparePartStatusId'  id='strSparePartStatusId'  onchange='changeSparePartAddPopup(this)' class='COMBO_NORMAL'>" + temp1[0] + "</select>";   
       	       
       	       
       	         var itemParaObj2 = document.getElementById("strWarrantyUptoUnitDivId");
       	            itemParaObj2.innerHTML ="<select name = 'strWarrantyUptoUnitId' onchange='' id='strWarrantyUptoUnitId' class='COMBO_NORMAL'>" + temp1[1] + "</select>";   
       	       
       	       
       	       var itemParaObj3 = document.getElementById("strSparePartDivId");
       	            itemParaObj3.innerHTML ="<select name = 'strSparePartId' id='strSparePartId'   onchange='' class='COMBO_NORMAL'>" + temp1[2] + "</select>";   
       	       
       	         
       	       var itemParaObj4 = document.getElementById("strSpareManufacturerDivId");
       	            itemParaObj4.innerHTML ="<select name = 'strSpareManufacturerId' id='strSpareManufacturerId' onchange='' class='COMBO_NORMAL'>" + temp1[3] + "</select>";   
       	       
       	             
       	        var itemParaObj5 = document.getElementById("sparepartTablePartDivId");
       	          itemParaObj5.innerHTML=temp1[4];
       	      
       	     
       	         
       	           
       }       
    }
}
function showOrHideSparePartReqRow(radioElementWorkStatus) {
	
	if(radioElementWorkStatus.value==1) { //this element is Work Complete
		
		if(radioElementWorkStatus.checked) {
			hideSparePartReqRow();
		} else {
			showSparePartReqRow();
		}
		
	} else if(radioElementWorkStatus.value==2) { //this element is Work In Complete
		if(radioElementWorkStatus.checked) {
			showSparePartReqRow();
			
		} else {
			hideSparePartReqRow();
		}
	}
	
}

function showSparePartReqRow() {
	document.getElementById("sparePartReqRowId").style.display="table-row";
	
}
function hideSparePartReqRow() {
	document.getElementById("sparePartReqRowId").style.display="none";
}
function clearSparePartRadioButton() {
	var arrElementSparePartStockDetailsRadio=document.getElementsByName("strSparePartStockDetailsRadio");
	if(arrElementSparePartStockDetailsRadio!=null) {
		for (i = 0; i < arrElementSparePartStockDetailsRadio.length; ++i) {
			arrElementSparePartStockDetailsRadio[i].checked = false;

		}
	}
	
}



function showTaskDiv(elementCheckBox) {
	moveTaskDisplay();
	display_popup_menu(elementCheckBox.parentNode, 'taskDivMain', '', '');
}

function moveTaskDisplay() {
	var elementTaskDivMain = document.getElementById("taskDivMain");
	var elementTaskDivBottom = document.getElementById("taskDivBottom");

	if (elementTaskDivBottom.innerHTML != "") 
	{
		elementTaskDivMain.innerHTML = elementTaskDivBottom.innerHTML;
		elementTaskDivBottom.innerHTML = "";
	}

}

function isCostInvolved(obj)
{
		if(document.getElementsByName("strIsCostInvolved")[0].checked==true)
		{
			document.getElementById("costInvolvedDivId1").style.display='block';
			document.getElementById("costInvolvedDivId2").style.display='block';
			document.getElementById("costInvolvedDivId3").style.display='block';
			document.getElementById("costInvolvedDivId4").style.display='block';
			document.getElementById("costInvolvedDivId5").style.display='block';
			document.getElementById("costInvolvedDivId6").style.display='block';
		}
		else
		{
			document.getElementById("costInvolvedDivId1").style.display='none';
			document.getElementById("costInvolvedDivId2").style.display='none';
			document.getElementById("costInvolvedDivId3").style.display='none';
			document.getElementById("costInvolvedDivId4").style.display='none';
			document.getElementById("costInvolvedDivId5").style.display='none';
			document.getElementById("costInvolvedDivId6").style.display='none';
		}
}	

function hideOrShowGatePass(elementCheckBox) {
	if (elementCheckBox.checked) {
		showSpanOrDiv("getPassLebel");
		showSpanOrDiv("getPassControl");
	} else {
		hideSpanOrDiv("getPassLebel");
		hideSpanOrDiv("getPassControl");
	}
}
function hideSpanOrDiv(strIdValue) {
	var elementDivOrSpan = document.getElementById(strIdValue);
	elementDivOrSpan.style.display = "none";
}
function showSpanOrDiv(strIdValue) {
	var elementDivOrSpan = document.getElementById(strIdValue);
	elementDivOrSpan.style.display = "block";
}

function displayTaskPopup(elementCheckBox) {
	if (elementCheckBox.checked) {
		showTaskDiv(elementCheckBox);
	} else {
		//alert('Do Nothing!');
	}
}

function addSparePartsDetails(targetNode) {
	moveSparePartAddRepairOrReplaceDisplay();
	display_popup_menu(targetNode, 'divSparePartAddRepairOrReplace', '', '');
	// display_popup_menu(targetNode,'sparePartAddRepairOrReplace','','');
}

function moveSparePartAddRepairOrReplaceDisplay() {
	var elementSparePartAddRepairOrReplace = document
			.getElementById("sparePartAddRepairOrReplace");
	var elementDivSparePartAddRepairOrReplace = document
			.getElementById("divSparePartAddRepairOrReplace");

	if (elementSparePartAddRepairOrReplace.innerHTML != "") {
		elementDivSparePartAddRepairOrReplace.innerHTML = elementSparePartAddRepairOrReplace.innerHTML;
		elementSparePartAddRepairOrReplace.innerHTML = "";
	}

}

function changeSparePartAddPopup(elementSparePartStatusCombo) {
	var strSparePartStatusId = elementSparePartStatusCombo.value;

	var divElementSparePartStockDetails = document
			.getElementById("sparePartStockDetails");
	var divAddSparePartDetails = document.getElementById("addSparePartDetails");
	var tableSparePartAddRepairOrReplaceButton = document
			.getElementById("sparePartAddRepairOrReplaceButton");
	var elementSparePartNameDiv = document.getElementById("sparePartNameDiv");
	var elementSparePartIdCombo = document
			.getElementById("strSparePartIdCombo");
	
	clearSparePartRadioButton();

	if (strSparePartStatusId == 0) {
		// Show Nothing
		divElementSparePartStockDetails.style.display = 'none';
		divAddSparePartDetails.style.display = 'none';
		tableSparePartAddRepairOrReplaceButton.style.display = 'none';

	} else if (strSparePartStatusId == 1) {
		// New Spare Part Addition
		divElementSparePartStockDetails.style.display = 'none';
		divAddSparePartDetails.style.display = 'block';
		elementSparePartNameDiv.style.display = 'none';
		elementSparePartIdCombo.style.display = 'block';
		tableSparePartAddRepairOrReplaceButton.style.display = 'table';

	} else if (strSparePartStatusId == 2) {
		// Replace Spare Part
		divElementSparePartStockDetails.style.display = 'block';
		divAddSparePartDetails.style.display = 'none';
		elementSparePartNameDiv.style.display = 'block';
		elementSparePartIdCombo.style.display = 'none';
		tableSparePartAddRepairOrReplaceButton.style.display = 'table';

	} else if (strSparePartStatusId == 3) {
		// Repair Spare Part
		divElementSparePartStockDetails.style.display = 'block';
		divAddSparePartDetails.style.display = 'none';
		tableSparePartAddRepairOrReplaceButton.style.display = 'table';

	}

}
function showAddSparePartDetailsInReplaceMode(strSparePartName) {

	var divAddSparePartDetails = document.getElementById("addSparePartDetails");
	var elementSparePartNameDiv = document.getElementById("sparePartNameDiv");

	var elementSparePartStatusCombo = document
			.getElementById("strSparePartStatusId");
	var strSparePartStatusId = elementSparePartStatusCombo.value;
	elementSparePartNameDiv.innerHTML = strSparePartName;
	if (strSparePartStatusId == 2) {
		divAddSparePartDetails.style.display = 'block';
	} else {
		divAddSparePartDetails.style.display = 'none';
	}

}
function validateSparePartAdd() {
	var elementSparePartStatusCombo = document
			.getElementById("strSparePartStatusId");
	var strSparePartStatusId = elementSparePartStatusCombo.value;

	var hisValidator = new HISValidator("complaintLogOfflineBean");

	if (strSparePartStatusId != 3) {

		if (strSparePartStatusId == 1) {

			hisValidator.addValidation("strSparePartId", "dontselect='0'",
					"Please Select a Spare Part Name.");
		}
		hisValidator.addValidation("strSpareItemSerialNo", "req",
				"Item Serial No. is a mandatory field");
		hisValidator.addValidation("strManufacturerId", "dontselect='0'",
				"Please Select a Manufacture Name.");
		hisValidator.addValidation("strWarrantyFromDateDD", "req",
				"Warranty From Date Day is a mandatory field");
		hisValidator.addValidation("strWarrantyFromDateYYYY", "req",
				"Warranty From Date Year is a mandatory field");
		hisValidator.addValidation("strWarrantyUpto", "req",
				"Warranty Upto is a mandatory field");
		hisValidator.addValidation("strWarrantyUptoUnitId", "dontselect='0'",
				"Please Select a Warranty Upto Unit.");
		hisValidator.addValidation("strSpecification", "req",
				"Specification is a mandatory field");
		hisValidator.addValidation("strSpecification", "maxlen=4000",
				"Specification cannot exceeds 4000 characters.");
		hisValidator.addValidation("strPerformedDateDD", "req",
				"Performed Date Day is a mandatory field");
		hisValidator.addValidation("strPerformedDateYYYY", "req",
				"Performed Date Year is a mandatory field");

	}

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		
		if (strSparePartStatusId != 3) {
			//if (!(checkDate("strWarrantyFromDate") && checkDate("strPerformedDate"))) {
				//return false;
			//}
		}
		if (strSparePartStatusId != 1) {
			var arrElementSparePartStockDetailsRadio = document
					.getElementsByName("strSparePartStockDetailsRadio");
			if (arrElementSparePartStockDetailsRadio == null) {
				alert("There is no stock to replace or repair!");
				return false;
			} else {
				var i = 0;
				var fChecked = false;
				for (i = 0; i < arrElementSparePartStockDetailsRadio.length; ++i) {
					if (arrElementSparePartStockDetailsRadio[i].checked) {
						fChecked = true;
						break;
					}
				}
				if (!fChecked) {
					alert("Please select an existing stock to replace or repair!");
					return false;
				}
			}

		}
		//alert("Saved!");
		hide_popup_menu('divSparePartAddRepairOrReplace');
	}
	return true;

}
function checkDate(strDateName) {

	
	var elementDD_Date = document.getElementById(strDateName + "DD");
	var elementMM_Date = document.getElementById(strDateName + "MM");
	var elementYYYY_Date = document.getElementById(strDateName + "YYYY");
	var elementDate = document.getElementById(strDateName);

	var strDD_Date = elementDD_Date.value;
	var strMM_Date = elementMM_Date.value;
	var strYYYY_Date = elementYYYY_Date.value;

	var nDD_Date = parseInt(strDD_Date);
	var nMM_Date = parseInt(strMM_Date);
	var nYYYY_Date = parseInt(strYYYY_Date);

	if (strYYYY_Date < 1000) {
		alert("Year is in YYYY format.");
		elementYYYY_Date.focus();
		return false;
	}

	var validDD = true;
	switch (nMM_Date) {
	case 2:
		if (isleap(nYYYY_Date)) {
			if (nDD_Date > 29) {
				alert("Date should be less than or equal to 29");
				validDD = false;
			}
		} else {
			if (nDD_Date > 28) {
				alert("Date should be less than or equal to 28");
				validDD = false;
			}

		}
		break;
	case 1:
	case 3:
	case 5:
	case 7:
	case 8:
	case 10:
	case 12:
		if (nDD_Date > 31) {
			alert("Date should be less than or equal to 31");
			validDD = false;
		}
		break;
	case 2:
	case 4:
	case 6:
	case 9:
	case 11:
		if (nDD_Date > 30) {
			alert("Date should be less than or equal to 30");
			validDD = false;
		}
		break;

	}
	if (validDD == false) {
		elementDD_Date.focus();
		return false;
	}
	elementDate.value = getDateString(strDD_Date,strMM_Date,strYYYY_Date);
	return true;
}
function isleap(yr) {

	if ((parseInt(yr) % 4) == 0) {
		if (parseInt(yr) % 100 == 0) {
			if (parseInt(yr) % 400 != 0) {
				// alert("Not Leap");
				return false;
			}
			if (parseInt(yr) % 400 == 0) {
				// alert("Leap");
				return true;
			}
		}
		if (parseInt(yr) % 100 != 0) {
			// alert("Leap");
			return true;
		}
	}
	if ((parseInt(yr) % 4) != 0) {
		// alert("Not Leap");
		return false;
	}
}
function resetSparePartAddPopup() {

	/*
	 * Display Component
	 */
	var divElementSparePartStockDetails = document
			.getElementById("sparePartStockDetails");
	var divAddSparePartDetails = document.getElementById("addSparePartDetails");
	var tableSparePartAddRepairOrReplaceButton = document
			.getElementById("sparePartAddRepairOrReplaceButton");

	/*
	 * Input Component
	 */
	var elementSparePartStatusCombo = document
			.getElementById("strSparePartStatusId");
	var arrElementSparePartStockDetailsRadio = document
			.getElementsByName("strSparePartStockDetailsRadio");
	var elementSparePartCombo = document.getElementById("strSparePartId");
	var elementSpareItemSerialNoText = document
			.getElementById("strSpareItemSerialNo");
	var elementManufacturerCombo = document.getElementById("strSpareManufacturerId");
	var elementManufactureSerialNoText = document
			.getElementById("strSpareManufactureSerialNo");
	var elementWarrantyFromDateDDText = document
			.getElementById("strWarrantyFromDateDD");
	var elementWarrantyFromDateMMCombo = document
			.getElementById("strWarrantyFromDateMM");
	var elementWarrantyFromDateYYYYText = document
			.getElementById("strWarrantyFromDateYYYY");
	var elementWarrantyUptoText = document.getElementById("strWarrantyUpto");
	var elementWarrantyUptoUnitCombo = document
			.getElementById("strWarrantyUptoUnitId");
	var elementSpecificationText = document.getElementById("strSpecification");
	var elementPerformedDateDDText = document
			.getElementById("strPerformedDateDD");
	var elementPerformedDateMMCombo = document
			.getElementById("strPerformedDateMM");
	var elementPerformedDateYYYYText = document
			.getElementById("strPerformedDateYYYY");

	elementSparePartStatusCombo.value = "0";
	/*
	for (i = 0; i < arrElementSparePartStockDetailsRadio.length; ++i) {
		arrElementSparePartStockDetailsRadio[i].checked = false;

	}
	*/
	
	clearSparePartRadioButton();
	
	elementSparePartCombo.value = "0";
	elementSpareItemSerialNoText.value = "";
	elementManufacturerCombo.value = "0";
	elementManufactureSerialNoText.value = "";
	elementWarrantyFromDateDDText.value = "";
	elementWarrantyFromDateMMCombo.value = "01";
	elementWarrantyFromDateYYYYText.value = "";
	elementWarrantyUptoText.value = "";
	elementWarrantyUptoUnitCombo.value = "0";
	elementSpecificationText.value = "";
	elementPerformedDateDDText.value = "";
	elementPerformedDateMMCombo.value = "01";
	elementPerformedDateYYYYText.value = "";

	divElementSparePartStockDetails.style.display = 'none';
	divAddSparePartDetails.style.display = 'none';
	tableSparePartAddRepairOrReplaceButton.style.display = 'none';

}
function cancelSparePartAddPopup() {
	resetSparePartAddPopup();
	hide_popup_menu('divSparePartAddRepairOrReplace');
}

function isItemInWorkingConditionchangeMode()
{	
	var rowid = document.getElementById("dateAndTimeId");	
	
	 if(document.getElementsByName("strIsItemInWorkingCondition")[0].checked==true)
     {
 		   rowid.style.display="none";
 	 }
	
	 if(document.getElementsByName("strIsItemInWorkingCondition")[1].checked==true)
 	 {
			rowid.style.display=''; 	
 	 }
}






function emptyDetailsForItem()
{
	
		doEmptyCombo(document.getElementsByName("strItemId")[0]);
		
		 var objVal1 = document.getElementById("stockValueId");
		// var objVal2 =  document.getElementById("prevCompDtlId");
		 var objVal3 =  document.getElementById("warrantyDetailsTable");
		 var objVal4 =  document.getElementById("maintenanceContractDetailsTable");
		 
  		 objVal1.innerHTML ="";
  		 // objVal2.innerHTML ="";
  		   objVal3.innerHTML ="";
  		    objVal4.innerHTML ="";	
	
}

function emptyDetailsForNonItem()
{
	doEmptyCombo(document.getElementsByName("strNonItemId")[0]);
	
		// var objVal2 =  document.getElementById("prevCompDtlId");
		 var objVal3 =  document.getElementById("warrantyDetailsTable");
		 var objVal4 =  document.getElementById("maintenanceContractDetailsTable");
		 
  		
  		 // objVal2.innerHTML ="";
  		   objVal3.innerHTML ="";
  		    objVal4.innerHTML ="";	
}


function doEmptyCombo(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var op=document.createElement("option");
		op.value="0";
		op.innerHTML="Select Value";
		cbo.appendChild(op);
	}
}

function compareTime(){ 
       	
       	  valFromHr=document.getElementsByName('strPreferredFromTime')[0].value.split(":")[0];
           valToHr=document.getElementsByName('strPreferredToTime')[0].value.split(":")[0];
           valFromMin=document.getElementsByName('strPreferredFromTime')[0].value.split(":")[1];
           valToMin=document.getElementsByName('strPreferredToTime')[0].value.split(":")[1];
     
       	
    
       	
          if(valFromHr > valToHr)
            {
              alert("From hr cannot be greater than to hour");
              document.getElementsByName('fromHour')[0].focus();            
              return false;
            }
            if(valFromHr == valToHr)
            {
               if(valFromMin >= valToMin)
               {
                 alert ("From min should be less than to min");
                 document.getElementsByName('fromMin')[0].focus(); 
                 return false;                            
               }
            }
            return true;
        }  
        
//functions for multi-row
function addrow(i)
   {
	     var temp=0,j=0;
	    temp=document.forms[0].flag.value;
	    /*if(temp=='undefined' || isNaN(temp)) 
	    {
	    	alert("inside if:]");
	    	temp=0;
	    }*/
	    temp++;
	    document.forms[0].flag.value=temp;
	    document.forms[0].hmode.value = "NEWROW";
	    document.forms[0].submit();
   }
 function delrow(i)
   {
		var temp=0,j=0,total=0.00,k=0;
	    temp=document.forms[0].flag.value;
	   	temp--;
		document.forms[0].flag.value=temp;
		document.forms[0].hmode.value="REMROW";
		document.forms[0].tselected.value=i;
	    document.forms[0].submit();
   }
   function calculateTotal(obj)
{
	//alert("inside function");
	var check = document.getElementsByName("strSpareCost");
	//alert("check"+check+"check.length"+check.length);
	var checkedVal=0;
	for(i=0;i<check.length;i++)
	{
		if(isNaN(parseInt(check[i].value)))
			check[i].value=0;
			checkedVal+=parseInt(check[i].value);
			
			//checkedVal+=parseInt(check[i].value);
			//alert(checkedVal);	
	}
	if(isNaN(checkedVal))
		checkedVal=0;
	document.getElementById("strTotalCost").value=checkedVal;
	document.getElementById("strNetCost").value=checkedVal;
}
function setNetAmount(obj)
{
	val=obj.value;
	if(isNaN(val)||val.length<1)
		{
			alert("Please enter a valid numeric value");
			obj.focus();
		}
	else{
		document.getElementById("strNetCost").value=(parseInt(document.getElementById("strTotalCost").value)-parseInt(val));
		
		}	
		
}
function isPenaltyConditionchangeMode()
{	
	var rowid = document.getElementById("penaltyAmtId");
	//var rowid1 = document.getElementById("penaltyAmtNotId");
	//var rowid2 = document.getElementById("orderDtlId");	
	
	 if(document.getElementById("strIsPenality").checked==true)
     {	
     //document.getElementById("strIsPenality").value="0";
 		   rowid.style.display="block";
 		   //rowid1.style.display="none";
 		//   rowid2.style.display="block";
 	 }
	else
	 {
 		   rowid.style.display="none";
 		   document.getElementsByName("strPenalityAmount").value="0";
 		   document.getElementsByName("strOfficeOrderNo").value="0";
 		   document.getElementsByName("strOfficeOrderDt").value="";
 		   //rowid1.style.display="block";
 		  // rowid2.style.display="none";
 	 }
 	 
}
function setSequenceNo()
 {
 	alert(document.getElementsByName("rowIndex1").value);
 }
 function chkRecordSaved()
 {
 	if(document.forms[0].strRetValue.value=="1")
 	{
 		alert(document.forms[0].strMsgString.value);
 		document.forms[0].hmode.value = "ListPage";
		document.forms[0].submit();
 	}
 }