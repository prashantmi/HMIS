/**
 * This Function Call When Radio button Click In Stock Details HLP
 */
function radioBtnClick(obj,index)
{			
	var val        = document.getElementById('strStockInf'+index).value;
	document.getElementById('checkid'+index).value = 1;
	var passVal = val+"^"+1;
	document.forms[0].strStockInfoVal.value = val;
	/*
	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
       GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
	 */
	var tmp         = val.split("^");
	var StoreName   = document.getElementById('StoreName'+index).value;
	var ItemSlNo    = document.getElementById('ItemSlNo'+index).value;
	var BatchNo     = document.getElementById('BatchNo'+index).value;
	var ManufactNo  = document.getElementById('ManufactNo'+index).value;
	var storeId     = tmp[0];
	var itemId      = tmp[1];
	var itemBrandId = tmp[2];
	var stockStatus = tmp[6];
	
	
	 var url="ComplaintLogOfflineACTION.cnt?hmode=PREVIOUSCOMPLAINTDTL&flag=ITEM&strStockInf="+passVal;
     ajaxFunction(url,"7");
	
	/*HEMNUM_ITEM_ID, HEMSTR_BATCH_NO, HEMNUM_ITEM_SL_NO, HEMNUM_SL_NO, GNUM_HOSPITAL_CODE)*/
	
}

 /**
  * This function is working as Global function to 
  * Hide or Show the Stock Details
  */
 function showOrHideStockDetails(thisImg)
 {
 	
 	if (thisImg == null) 
 	{
		alert("Cannot find this image object.");
	}
	if (thisImg.title == "Show") 
	{
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/minus.gif";
		thisImg.title = "Hide";
		document.getElementById("prevLnkDivId").style.display='block';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";
		document.getElementById("prevLnkDivId").style.display='none';
	}	
 } 
 
 /**
  * This function is to 
  * Hide or Show the Complaint Details
  */
function showOrHideComplaintDetails(thisImg)
 {
 	
 	if (thisImg == null) 
 	{
		alert("Cannot find this image object.");
	}
	if (thisImg.title == "Show") 
	{
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/minus.gif";
		thisImg.title = "Hide";
		document.getElementById("prevComplaintDtlDivId").style.display='block';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";
		document.getElementById("prevComplaintDtlDivId").style.display='none';
	}	
 }  

function showOrHidePrevMaintenanceDetails(thisImg)
{
	showOrHideComplaintDetails(thisImg);
}
 
 /*
  * For Pagination
  */
function GetIndex(index,endVal)  // Pagenation  One
{
	
	    for (var page = 1; page <= endVal; page++)       // For Loop To Put Normal CSS in All No
        {
          document.getElementById('pg'+page).className = 'pg-normal';
        }
	    var oldPageAnchor = document.getElementById('pg'+index);   // Apply CSS
        oldPageAnchor.className = 'pg-selected';
        
          for(var i = 1; i <= endVal ; i++)
		  {
			  document.getElementById("DivId"+i).style.display="none";
		  }
		  document.getElementById("DivId"+index).style.display="block";
			 
}

function validate1()
{   
    
    
    
    if(document.forms[0].strIsSparePartsMaintenanceInvolved.checked){
    
    document.forms[0].strSparePartsFlag.value="1";
    
    
    }
    else{
    
    	document.forms[0].strSparePartsFlag.value="0";
    }
    
   
             var hisValidator = new HISValidator("complaintLogOfflineBean");
            
              if(document.forms[0].strComplaintType!=null)
              {
             	hisValidator.addValidation("strComplaintType","req", "Complaint Type is a Mandatory Field" );
              }
             	
             	
             hisValidator.addValidation("strDeptCodeNew","dontselect=0","Please Select Department Name");
             hisValidator.addValidation("strEmpId","dontselect=0","Please Select Complainer Name");
             
             hisValidator.addValidation("strEngineeringItemTypeId","dontselect=0","Please Select Engineering Item Type");
             hisValidator.addValidation("strEngineeringItemSubTypeId","dontselect=0","Please Select Engineering Item Sub Type");
             
            // hisValidator.addValidation("strComplaintDescription","req", "Please enter the Complaint Description!" );
            // hisValidator.addValidation("strComplaintDescription","maxlen=4000", "Complaint Description should be less than 4000 characters" );
             
             if(document.getElementsByName("strIsItemInWorkingCondition")[1].checked==true)
             
             {
		          hisValidator.addValidation("strFromDate", "date","Date is a Mandatory Field");
     	          hisValidator.addValidation("strFromTime","req", "Time is a Mandatory Field" );
             }
             
             if(document.forms[0].strItemOrNonItemMode.value=="1" )
             {
	        	hisValidator.addValidation("strItemId","dontselect=0","Please Select Item Name");
             	hisValidator.addValidation("strStoreId","dontselect=0","Please Select Store");
             	if(!chkStockSelected())
             	{	
             		alert("Please Select Stock");
             		return false;
             	}
	         }
	         else
	         {
	        	hisValidator.addValidation("strNonItemId","dontselect=0","Please Select Non Item Name");
	         }
	         
	         hisValidator.addValidation("strClosingDate","req", "Closing Date is a Mandatory Field!" );
	         hisValidator.addValidation("strClosingTime","req", "From Time is a Mandatory Field!" );
	         
	         
	         
	           if(document.getElementsByName('strClosingTime')[0].value.length!=5)
	       	 {
	       	 	alert("Enter Closing Time in the format (HH:MM 24Hr) ");
	       	 	document.forms[0].strClosingTime.focus();
	       	 	return false;
	       	 }
	          
	         
	          hisValidator.addValidation("strAttendDate","date", "Attend Date is a Mandatory Field!" );
	         hisValidator.addValidation("strAttendTime","req", "Attend Time is a Mandatory Field!" );
	         
	         
	         
	           if(document.getElementsByName('strAttendTime')[0].value.length!=5)
	       	 {
	       	 	alert("Enter Attend Time in the format (HH:MM 24Hr) ");
	       	 	document.forms[0].strAttendTime.focus();
	       	 	return false;
	       	 }
	         
	         
	         hisValidator.addValidation("strProblemDescription","req", "Please enter the Problem  Description!" );
             hisValidator.addValidation("stProblemDescription","maxlen=500", "Problem Description should be less than 500 characters" );
	                
	         
	         hisValidator.addValidation("strSolutionProvided","req", "Please enter the Solution Provided!" );
             hisValidator.addValidation("strSolutionProvided","maxlen=250", "Solution Provided  should be less than 250 characters" );
	         
	         hisValidator.addValidation("strRemarks","maxlen=100", "Complaint Description should be less than 100 characters" );
	         
	         hisValidator.addValidation("strFromDate","req", "From Date is a Mandatory Field!" );
	         hisValidator.addValidation("strFromTime","req", "From Time is a Mandatory Field!" );
	         
	         
	         
	           if(document.getElementsByName('strFromTime')[0].value.length!=5)
	       	 {
	       	 	alert("Enter From Time in the format (HH:MM 24Hr) ");
	       	 	document.forms[0].strFromTime.focus();
	       	 	return false;
	       	 }
	         
	         hisValidator.addValidation("strVerifiedBy","dontselect=0","Please Select Verified By");
	          
	          
	          
	         hisValidator.addValidation("strVerifyDate","req", "Verified Date is a Mandatory Field!" );
	         hisValidator.addValidation("strVerifyTime","req", "Verified Time is a Mandatory Field!" );
	         
	         
	         
	           if(document.getElementsByName('strVerifyTime')[0].value.length!=5)
	       	 {
	       	 	alert("Enter Verified Time in the format (HH:MM 24Hr) ");
	       	 	document.forms[0].strVerifyTime.focus();
	       	 	return false;
	       	 }
	          
	          
	          
	         
	       
       //	compareTime();
          
 			 var retVal = hisValidator.validate(); 

          if(retVal)
          	{
        	  
/*
 *	//To make Warranty / Maintenance Mandatory , UnComment This Portion. 
 * 
   
  	  			if(document.getElementsByName("strWarrantyOrMaintenanceSlNoAndType").length==0) 
	  			 {
	  				alert("No Warranty or Maintenance Contract Exists. Complaint cannot be raised.");
	  				return false;
	  			 } 
	  			
	  			else
	  			{
	  				var eachSelected=false;
	  				
	  				
	  				for(var i=0;i<document.getElementsByName("strWarrantyOrMaintenanceSlNoAndType").length;i++)
	  				{
	  					if(document.getElementsByName("strWarrantyOrMaintenanceSlNoAndType")[i].checked==true)
	  					{
	  					eachSelected=true;
	  					break;
	  					}
	  				}
	  					if(!eachSelected)
	  					{
						alert("Please Select atleast one of the Warranty Or Maintenance Details.");
    	  				return false;	  				
    	  				}
	  			}
*/	  		
	 				
	         	   document.forms[0].hmode.value = "SAVE";
	           	   document.forms[0].submit();
                      
          }
          else
          {
             return false;
          }
}

function chkStockSelected()
{
	var flag = false;
	var idIndex = 10;
	var stkObj = document.getElementById("checkid"+idIndex);
	if(stkObj != null)
		while(stkObj)
		{
			if(stkObj.checked==true)
			{
				flag = true;
				return flag;
			}
			idIndex++;	
			stkObj = document.getElementById("checkid"+idIndex);
		}
	return flag;	
}
function getAttendName()

{

var obj= document.forms[0].strVendorServiceEngName;

document.forms[0].strAttendedContactPerson.value= getSelectedLabel(obj);


}


/*
function validate1(){   
     
      var hisValidator = new HISValidator("LCRequestTransBean"); 
       var retVal=true;       
       	  
          if(document.getElementById("cancelDiv").style.display=="block")  
          {
            hisValidator.addValidation("strActionRemarks", "req", "Cancel Remarks is a Mandatory Field" );
         
          }else{          
          
        	hisValidator.addValidation("strStoreId", "dontselect=0", "Please Select A Store" );
       	  if(document.forms[0].strRequestTypeId.value=="10")
       	  {     
       	  	hisValidator.addValidation("strPONo", "dontselect=0", "Please Select A PO No." );
       	  	hisValidator.addValidation("strLCValidity", "req", "Validity is a Mandatory Field" );
       	  	hisValidator.addValidation("strFavourOf", "req", "In Favour Of is a Mandatory Field" );
       	  	hisValidator.addValidation("strBankName", "req", "Bank Name is a Mandatory Field" );
       	  	hisValidator.addValidation("strBankAddress", "req", "Bank Address is a Mandatory Field" );
       	  	hisValidator.addValidation("strLCTerms", "req", "Terms & Conditions is a Mandatory Field" );
       	  }else{
       	  	hisValidator.addValidation("strLCNo", "dontselect=0", "Please Select A LC No." );
       	  	hisValidator.addValidation("strNewLCValidity", "req", "Validity is a Mandatory Field" );
       	  	hisValidator.addValidation("strNewFavourOf", "req", "In Favour Of is a Mandatory Field" );
         	hisValidator.addValidation("strNewLCTerms", "req", "Terms & Conditions is a Mandatory Field" );
     
       	  }
          }
             retVal = hisValidator.validate(); 
       
          if(retVal){
          
           if(document.getElementById("cancelDiv").style.display=="block")  
          {
          var pendingReqChk = document.getElementsByName("strchkvisit");  
          var selectedReq  = "";
          if(pendingReqChk.length>0)
          {
         	 for(i=0;i<pendingReqChk.length;i++)
         	 {
         	 	if(pendingReqChk[i].checked==true)
         	 	{
         	 		selectedReq = selectedReq +","+ pendingReqChk[i].value;
         	 	}
         	 }
         	 document.forms[0].strSelectedReq.value=	selectedReq;
         }
          			alert("selectedReq-"+selectedReq);
          			document.forms[0].hmode.value = "INSERTCANCEL";
                    document.forms[0].submit();
          
          }else{      
          			document.forms[0].hmode.value = "INSERT";
                    document.forms[0].submit();
                   }
            }else{
					return false;
				 }
    }
*/

// call on clear add page
function clearPage()
{
	document.forms[0].reset();
	var strDeptCode=document.forms[0].strOldDepartmentCode.value;
	document.forms[0].hmode.value = "initializeItemComplaintRegister";
	document.forms[0].strDeptCode.value = strDeptCode;
	document.forms[0].submit();
}

function cancelPage()
{
	document.forms[0].hmode.value = "goToWelcomePage";
	document.forms[0].submit();
}



// Started from here by Vivek Aggarwal


/**
 * changeViewMode
 * @param {Object} chkObj 
 */
function changeViewMode()
{    
	if(document.getElementsByName("strItemOrNonItemMode")[0].checked==true)
 	{
 		    document.forms[0].hmode.value = "initializeItemComplaintRegister";
            document.forms[0].submit();
 	}
	else if(document.getElementsByName("strItemOrNonItemMode")[1].checked==true)
 	{
 		    document.forms[0].hmode.value = "initializeNonItemComplaintRegister";
            document.forms[0].submit();
 	}
}

function getEnggItemSubTypeCombo()
{
        var mode = "ENGGITEMSUBTYPECMB";
        var url ="ComplaintLogOfflineACTION.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
 		ajaxFunction(url,"1");		
}

function getStoreName()
{
	var mode = "GETSTORENAME"
	var url = "ComplaintLogOfflineACTION.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCodeNew.value;
	

	ajaxFunction(url,"4");
	
	
}


function getVenderDetails()
{
	var mode = "GETVENDORDTLS"
	var url = "ComplaintLogOfflineACTION.cnt?hmode="+mode+"&vendorId="+document.forms[0].strVendorId.value;
	ajaxFunction(url,"13");
}



function getstrComplaintType()
{
 
 document.forms[0].hmode.value = "initializeItemComplaintRegister";
            document.forms[0].submit();

}



function getServiceEngName()
{
   var mode ;
	
   /* var obj2 = document.forms[0].strItemOrNonItemMode[0];
	var obj = document.forms[0].strComplaintType[0];
	   
	  if(obj2.checked == true)
	 { 
		if(obj.checked == true){
		mode = "GETSERVICEENGNAME"
		}
	
		else if(obj.checked == false)
		{
		 mode = "GETVENDORCOMBO";
		}
	
	
	}
	
	else{   	
			if(obj.checked == false){
				mode = "GETSERVICEENGNAME"
			}
	
		else if(obj.checked == true)
		{
			 mode = "GETVENDORCOMBO";
		}
	
	
	}
	*/
   var obj2 = document.getElementsByName('strComplaintType');
   var obj;
   for (var i=0;i<obj2.length;i++)
	   {
	   if(obj2[i].checked)
		   {
		   obj=obj2[i].value;
		 //  alert(obj);
		   }
	   }
   if(obj=='2')
	   {
	   document.getElementById('strVendorServiceEngId').style.display="none";
	   
	   document.getElementById('vendorServiceDivId').style.display="";
	   mode = "GETSERVICEENGNAME"
	   }
   else
	   {
	   document.getElementById('strVendorServiceEngId').style.display="";
	   
	   document.getElementById('vendorServiceDivId').style.display="none";
	   mode = "GETSERVICEENGNAME"
	   }
	var url = "ComplaintLogOfflineACTION.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value+"&enggItemSubType="+document.forms[0].strEngineeringItemSubTypeId.value;
	ajaxFunction(url,"14");
}



function getItemName()
{	

   	  var url="ComplaintLogOfflineACTION.cnt?hmode=GETITEMNAME&flag="+1+"&deptId="+document.forms[0].strDeptCode.value;
      ajaxFunction(url,"3");
} 


function getEmpCombo()
{


   	  var url="ComplaintLogOfflineACTION.cnt?hmode=EMPCOMBO&flag="+1+"&deptId="+document.forms[0].strDeptCodeNew.value;
      ajaxFunction(url,"15");


}

function getEmpDesignation()
{

	    
   	  var url="ComplaintLogOfflineACTION.cnt?hmode=EMPDESIGNATION&flag="+1+"&empId="+document.forms[0].strEmpId[0].value;
   	 
      ajaxFunction(url,"16");


}


function getItemNameOnBasisOfStore()
{
	 var url="ComplaintLogOfflineACTION.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
	 ajaxFunction(url,"5");	
}

function getNonItemName()
{
	var url="ComplaintLogOfflineACTION.cnt?hmode=GETNONITEMNAME&enggItemType="+document.forms[0].strEngineeringItemTypeId.value+"&enggItemSubType="+document.forms[0].strEngineeringItemSubTypeId.value;
    ajaxFunction(url,"9");
}

function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
   if( document.getElementsByName("strDeptCodeNew") && document.forms[0].strDeptCodeNew.value!=0 )
   {
   var url="ComplaintLogOfflineACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemId.value+"&deptId="+document.forms[0].strDeptCodeNew.value;
   }
   else if(document.getElementsByName("strDeptCode") && document.forms[0].strDeptCode.value!=0)
   {
   var url="ComplaintLogOfflineACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemId.value+"&deptId="+document.forms[0].strDeptCode.value;
   }
   
   ajaxFunction(url,"2");
} 

function getPreviousComplaintDtl()
{
	  var url="ComplaintLogOfflineACTION.cnt?hmode=PREVIOUSCOMPLAINTDTL&flag=NON_ITEM"+"&nonItemId="+document.forms[0].strNonItemId.value;
      ajaxFunction(url,"10");
}

function getWarrantyDtl(passVal)
{	  

   var objVal1 = document.getElementById("warrantyDetailsTable");
   objVal1.innerHTML ="";

   var url="ComplaintLogOfflineACTION.cnt?hmode=WARRANTYDTL&flag=ITEM"+"&strStockInf="+passVal ;
   ajaxFunction(url,"6");   
} 

function getWarrantyDtlForNonItem(passVal)
{
   var objVal1 = document.getElementById("warrantyDetailsTable");
   objVal1.innerHTML ="";

	var url="ComplaintLogOfflineACTION.cnt?hmode=WARRANTYDTL&flag=NON_ITEM"+"&nonItemId="+passVal ;
    ajaxFunction(url,"11"); 
}


function getMaintenanceContractDetails(passVal)
{
	var objVal1 = document.getElementById("maintenanceContractDetailsTable");
    objVal1.innerHTML ="";

    var url="ComplaintLogOfflineACTION.cnt?hmode=MAINTENANCECONTRACTDTL&flag=ITEM"+"&strStockInf="+passVal ;
    ajaxFunction(url,"8");   
}

function getMaintenanceContractDetailsForNonItem(passVal)
{
	var objVal1 = document.getElementById("maintenanceContractDetailsTable");
    objVal1.innerHTML ="";

  	var url="ComplaintLogOfflineACTION.cnt?hmode=MAINTENANCECONTRACTDTL&flag=NON_ITEM"+"&strNonItemId="+passVal ;
    ajaxFunction(url,"12");   
}

function getSpareParts()

{

var url="ComplaintLogOfflineACTION.cnt?hmode=initializeAttended&flag=1&itemId="+document.forms[0].strItemId.value+"&storeId="+document.forms[0].strStoreId.value+
"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value+"&enggItemSubType="+document.forms[0].strEngineeringItemSubTypeId.value+
"&strStockInf="+document.forms[0].strStockInfoVal.value+"&strPageFlag="+document.forms[0].strPageFlag.value;
    
  
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
					
					
					objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='COMBO_NORMAL' onchange='getNonItemName();' tabindex='1' >"+res+"</select>";
					
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
       	            var itemParaObj = document.getElementById("nonItemId");
       	            itemParaObj.innerHTML ="<select name = 'strNonItemId' onchange='getPreviousComplaintDtl();' class='COMBO_NORMAL'>" + res + "</select>";
        }
        getServiceEngName();       
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

function showHideEmpDetails(chk)
{
	var div1 = document.getElementById("divEmpDetails");
	var div2 = document.getElementById("divDeptNameAndStoreDetails");
	var div3 = document.getElementById("divEmpDeptAndDesignation"); 
	
	
	if(chk.checked)
	{
		div1.style.display='';
		div3.style.display='';
		div2.style.display="none";
		document.getElementsByName("strIsAttached")[0].value=1;
		getItemName();
	}
	else
	{
		div1.style.display="none";
		div3.style.display="none";
		div2.style.display='';
		document.getElementsByName("strIsAttached")[0].value=0;
		getStoreName();
		doEmptyCombo(document.getElementsByName("strItemId")[0]);
		
	}
}


function isSparePartsMaintenanceInvolved()
{
  var div1=document.getElementById("spareDiv");

if(document.forms[0].strIsSparePartsMaintenanceInvolved.checked)
{
     
 
  
  
  var hisValidator = new HISValidator("complaintLogOfflineBean");
            
             	
             		hisValidator.addValidation("strDeptCodeNew","dontselect=0","Please Select Department Name");
             		hisValidator.addValidation("strStoreId","dontselect=0","Please Select Store");
             	                        
                    
             if(document.forms[0].strItemId!=null )
             {
	       		 hisValidator.addValidation("strItemId","dontselect=0","Please Select Item Name");
	       		 if(document.forms[0].checkid.checked == false)
	       		 {
	       		 		alert("Select one item from Stock Detail");
	       		 }
	       		 
	         }
	         
  
  if(hisValidator.validate())
  {
  		 div1.style.display='block';
 		 document.forms[0].strIsSparePartsMaintenanceInvolved.value="1";
  
       getSpareParts();
  
  }
  
  else{
  		document.forms[0].strIsSparePartsMaintenanceInvolved.checked=false;
  		document.forms[0].strIsSparePartsMaintenanceInvolved.value="0";
  }
  
}

else{
div1.style.display='none';
document.forms[0].strIsSparePartsMaintenanceInvolved.value="0";
}


 
}




 function onLoadFunction()
{

	var div1 = document.getElementById("divEmpDetails");
	var div2 = document.getElementById("divDeptNameAndStoreDetails");
	var div3 = document.getElementById("divEmpDeptAndDesignation"); 
	
/*	if(document.getElementsByName("strIsAttached")[0].checked)
	{
		div1.style.display='';
		div3.style.display='';
		div2.style.display="none";
		document.getElementsByName("strIsAttached")[0].value=1;
	}
	else
	{
		div1.style.display="none";
		div3.style.display="none";
		div2.style.display='';
		document.getElementsByName("strIsAttached")[0].value=0;
	}
	*/
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

/**
 * Function for File Up-Lodaing
 */

function onUploadedFileName(obj,index,fileId)
{
	
		if(fileId!='0')
		{
			document.forms[0].hmode.value="GETUPLOADEDFILE"; 
			document.forms[0].strUploadFileId.value=fileId;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
		}
		else
		{
			alert("No File to Up-Load!!");			
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
      
      function validateHrMin(){
         success=false;
         if(validateFromHr() && validateFromMin() && validateToHr() && validateToMin()){
             if(compareTime()){
             success=true;
         }            
          }       
      return success ;
      }
      
     
     
      function validateFromHr(){    
       valFromHr=document.getElementsByName('fromHour')[0].value;
      if(valFromHr==null||valFromHr==""){              
            alert("Please Enter value in From Hr Field");               
            document.getElementsByName('fromHour')[0].focus();
            return false;     
        }
        if(valFromHr>23){
          alert("Hr Value canont be greater than 23");
          document.getElementsByName('fromHour')[0].focus();
          return false;     
        }
        return true;      
      } 
      
        
    function validateToHr(){  
      valToHr=document.getElementsByName('toHour')[0].value;
        if(valToHr==null||valToHr==""){                
            alert("Please Enter value in To Hr Field");                 
            document.getElementsByName('toHour')[0].focus();
            return false;     
        }
        if(valToHr>23){
          alert("Hr Value canont be greater than 23");
          document.getElementsByName('toHour')[0].focus();
          return false;     
        }
        return true;      
      } 
      
      function validateToMin(){     
      valToMin=document.getElementsByName('toMin')[0].value;
        if(valToMin==null||valToMin==""){              
            alert("Please Enter value in To Min Field");                
            document.getElementsByName('toMin')[0].focus();
            return false;     
        }
        if(valToMin>59){
          alert("To Min Value canont be greater than 59");
          document.getElementsByName('toMin')[0].focus();
          return false;     
        }
        return true;      
      }	
      
      
      
      function enableVendorDetails(){
      

      if(document.getElementsByName('isRepairedByVendor')[0].checked == true){
      document.getElementsByName('strVendorId')[0].disabled= false;
      document.getElementsByName('strInvoiceNo')[0].disabled= false;
      
      
      }   
      
      else if(document.getElementsByName('isRepairedByVendor')[0].checked == false) {
      
      document.getElementsByName('strVendorId')[0].disabled=true;
      document.getElementsByName('strInvoiceNo')[0].disabled=true;
      document.getElementById("vendorAddrDivId").innerHTML="";
      document.getElementById("vendorcontactNoDivId").innerHTML="";
      
      }      
      
      }
      
      
      //  another js 
      
      
      
      

function tableShow(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HBIMS/hisglobal/images/minus.gif");

}

function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HBIMS/hisglobal/images/plus.gif");

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
	//case 2:
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

function getDateString(dd, mm, yyyy) {
	var arrMonthNames = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec");
	return dd+"-"+arrMonthNames[parseInt(mm, 10)-1]+"-"+yyyy;
}


function cancelAttended() {
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
	
}


function clearAttended() {
	resetSparePartAddPopup();
	document.forms[0].reset();
}

function checkTimeFormat(eleTime) {
	var strTime=eleTime.value;
	
	var arrTime = strTime.split(":");
	

	if (arrTime.length != 2) {
		alert("Please enter the  Time in HH:MM 24hr format only!");
		eleTime.focus();
		return false;
	}

	

	var strTimeHH = arrTime[0];
	var strTimeMM = arrTime[1];

	
	
	var numTimeHH = parseInt(strTimeHH);
	var numTimeMM = parseInt(strTimeMM);

	

	if (isNaN(numTimeHH) || isNaN(numTimeMM)) {
		alert("Please enter the  Time in HH:MM 24hr format only!");
		eleTime.focus();
		return false;
	} else {
		if(numTimeHH>=24) {
			alert("Hour should be less than 24.");
			eleTime.focus();
			return false;
		}
		if(numTimeMM>=60) {
			
			alert("Minute should be less than 60.");
			eleTime.focus();
			return false;
		}
	}

	return true;
	
}


function validateAttendDetailSave(mode) 
{
	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");

	hisValidator.addValidation("strServiceEngineerName", "req",	"Service Engineer Name is a mandatory field.");
	hisValidator.addValidation("strContactNo", "req", "Contact No is a mandatory field.");
	hisValidator.addValidation("strAttendDate", "req", "Attend Date is a mandatory field.");
	hisValidator.addValidation("strAttendTime", "req", "Attend Time is a mandatory field.");
	hisValidator.addValidation("strProblemDescription", "req", "Problem Description is a mandatory field.");
	hisValidator.addValidation("strProblemDescription", "maxlen=500", "Problem Description cannot exceeds 500 characters.");
	
	/*
	if(document.getElementsByName("strItemOrSparePartMovedOut")[0].checked) {
		hisValidator.addValidation("strGatePassNo", "req",
		"Gate Pass No is a mandatory field.");
	}
	*/
	hisValidator.addValidation("strSolutionProvided", "req", "Solution Provided is a mandatory field.");
	hisValidator.addValidation("strSolutionProvided", "maxlen=250", "Solution Provided cannot exceeds 250 characters.");
	

	hisValidator.addValidation("strFromDate", "req", "From Date is a mandatory field.");
	hisValidator.addValidation("strFromTime", "req", "From Time is a mandatory field.");
	
	hisValidator.addValidation("strToDate", "req", "To Date is a mandatory field.");
	hisValidator.addValidation("strToTime", "req", "To Time is a mandatory field.");
	
	hisValidator.addValidation("strFromDate", "dtltet="+document.forms[0].strToDate.value, "From Date should be less than equal To Date.");
	

	hisValidator.addValidation("strRemarks", "req", "Attendent Remarks is a mandatory field.");
	hisValidator.addValidation("strRemarks", "maxlen=100","Remarks cannot exceeds 100 characters.");


	if(document.getElementsByName("strIsCostInvolved")[0].checked==true)
	{
		hisValidator.addValidation("strCost", "req", "Cost is a mandatory field.");
	}

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		
		if(checkTimeFormat(document.getElementsByName("strAttendTime")[0])==false) {
			return false;
		}
		if(checkTimeFormat(document.getElementsByName("strFromTime")[0])==false) {
			return false;
		}
		if(checkTimeFormat(document.getElementsByName("strToTime")[0])==false) {
			return false;
		}
		var fWorkingConditionChecked=false;
		var i=0;
		var arrRadioWorkingCondition=document.getElementsByName("strWorkingCondition");
		for(i=0;i<arrRadioWorkingCondition.length;++i ) {
			if(arrRadioWorkingCondition[i].checked) {
				fWorkingConditionChecked=true;
				break;
			}
		}
		if(fWorkingConditionChecked==false) {
			alert("Please Select Working Condition.");
			return;
		}
		
		var fWorkStatusChecked=false;
		i=0;
		var arrRadioWorkStatus=document.getElementsByName("strWorkStatus");
		for(i=0;i<arrRadioWorkStatus.length;++i ) {
			if(arrRadioWorkStatus[i].checked) {
				fWorkStatusChecked=true;
				break;
			}
		}
		if(fWorkStatusChecked==false) {
			alert("Please Select Work Status.");
			return;
		}
		
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
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
      
      
      
      
      
      
	