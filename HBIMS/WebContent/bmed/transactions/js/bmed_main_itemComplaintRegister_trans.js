/**
 * This Function Call When Radio button Click In Stock Details HLP
 */
function radioBtnClick(obj,index)
{			
	
	var val        = document.getElementById('strStockInf'+index).value;
	//alert("index::"+index);
	//alert("val::"+val);
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
	
	
	 var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=PREVIOUSCOMPLAINTDTL&flag=ITEM&strStockInf="+passVal;
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
    
             var hisValidator = new HISValidator("complaintMaintenanceStatusBean");
              
              if(document.forms[0].strComplaintType!=null)
              {
             	hisValidator.addValidation("strComplaintType","req", "Complaint Type is a Mandatory Field" );
              }
             	
             	if(document.forms[0].strIsAttached.checked!=true)
             	{
             		hisValidator.addValidation("strDeptCodeNew","dontselect=0","Please Select Department Name");
             		hisValidator.addValidation("strStoreId","dontselect=0","Please Select Store");
             		
             		document.forms[0].strDeptName.value = document.forms[0].strDeptCodeNew[document.forms[0].strDeptCodeNew.selectedIndex].text;
             	}
             	else 
             	{
             		hisValidator.addValidation("strDeptCode","dontselect=0","Please Select Department");
             		document.forms[0].strDeptName.value = document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text;             		 
             	}


             
             hisValidator.addValidation("strEngineeringItemTypeId","dontselect=0","Please Select Engineering Item Type");
             hisValidator.addValidation("strEngineeringItemSubTypeId","dontselect=0","Please Select Engineering Item Sub Type");
             
             hisValidator.addValidation("strComplaintDescription","req", "Please enter the Complaint Description!" );
             hisValidator.addValidation("strComplaintDescription","maxlen=4000", "Complaint Description should be less than 4000 characters" );
             
             if(document.getElementsByName("strIsItemInWorkingCondition")[1].checked==true)
             
             {
		          hisValidator.addValidation("strComplaintDate", "date","Date is a Mandatory Field");
     	          hisValidator.addValidation("strComplaintTime","req", "Time is a Mandatory Field" );
             }
             
             if(document.forms[0].strItemId!=null ){
                  hisValidator.addValidation("strItemId","dontselect=0","Please Select Item Name");
                  document.forms[0].strItemName.value = document.forms[0].strItemId[document.forms[0].strItemId.selectedIndex].text;
             }
	         
 			 hisValidator.addValidation("strPreferredFromTime","req", "Preferred From Time is a Mandatory Field" );
 			 hisValidator.addValidation("strPreferredToTime","req", "Preferred To Time is a Mandatory Field" );
  			 hisValidator.addValidation("strContactPersonName","req", "Contact Person Name is a Mandatory Field" );
  			 hisValidator.addValidation("strContactNo","req", "Contact No is a Mandatory Field" );
  			 hisValidator.addValidation("strLandMarkDescription","req", "Land Mark Description is a Mandatory Field" );
  			
  			 hisValidator.addValidation("strRemarks","maxlen=100", "Remarks should have less than or equal to 100 Characters" );
 
             hisValidator.addValidation("strLandMarkDescription", "maxlen=4000", "Land Mark Description should have less than or equal to 4000 Characters" );
            
             if(document.getElementsByName('strPreferredFromTime')[0].value.length!=5)
	       	 {
	       	 	alert("Enter From Time in the format (HH:MM 24Hr) ");
	       	 	document.forms[0].strPreferredFromTime.focus();
	       	 	return false;
	       	 }
	       	 
	       	 if(document.getElementsByName('strPreferredToTime')[0].value.length!=5)
	       	 {
	       	 	alert("Enter To Time in the format (HH:MM 24Hr) ");
	       	 	document.forms[0].strPreferredToTime.focus();
	       	 	return false;
	       	 }
	       	 
        	compareTime();
            
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
	 				/*
	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
       GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
	 */
	 
	 			if(document.getElementsByName("strItemOrNonItemMode")[1].checked==true)
	 			{
	 				document.forms[0].strStockInfoVal.value = "0^0^0^0^0^0^0"; 
	 			}
	 			document.forms[0].strNatureOfService.disabled=false;
	         	document.forms[0].hmode.value = "SAVE";
	           	document.forms[0].submit();                      
          }
          else
          {
             return false;
          }
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
	hide_popup_menu();
	document.forms[0].strComplaintId.value="";
	//document.getElementById("stockDtlsDivId").innerHTML = "";
	document.forms[0].submit();
}

function cancelPage()
{
	document.forms[0].hmode.value = "unspecified";
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
        var url ="ComplaintMaintenanceStatusTransACTION.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
 		ajaxFunction(url,"1");		
}

function getStoreName()
{
	var mode = "GETSTORENAME";
	var url = "ComplaintMaintenanceStatusTransACTION.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCodeNew.value;
	ajaxFunction(url,"4");
}

function getItemName()
{	
   	  var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=GETITEMNAME&flag="+1+"&deptId="+document.forms[0].strDeptCode.value;
      ajaxFunction(url,"3");
} 

function getItemNameOnBasisOfStore()
{
	 var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
	 ajaxFunction(url,"5");	
}

function getNonItemName()
{
	var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=GETNONITEMNAME&enggItemType="+document.forms[0].strEngineeringItemTypeId.value+"&enggItemSubType="+document.forms[0].strEngineeringItemSubTypeId.value;
    ajaxFunction(url,"9");
}

function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
   if( document.getElementsByName("strDeptCodeNew") && document.forms[0].strDeptCodeNew.value!=0 )
   {
   var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemId.value+"&deptId="+document.forms[0].strDeptCodeNew.value;
   }
   else if(document.getElementsByName("strDeptCode") && document.forms[0].strDeptCode.value!=0)
   {
   var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemId.value+"&deptId="+document.forms[0].strDeptCode.value;
   }
   
   ajaxFunction(url,"2");
} 

function getPreviousComplaintDtl()
{
	  var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=PREVIOUSCOMPLAINTDTL&flag=NON_ITEM"+"&nonItemId="+document.forms[0].strNonItemId.value;
      ajaxFunction(url,"10");
}

function getWarrantyDtl(passVal)
{	  
   var objVal1 = document.getElementById("warrantyDetailsTable");
   objVal1.innerHTML ="";

   var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=WARRANTYDTL&flag=ITEM"+"&strStockInf="+passVal ;
   ajaxFunction(url,"6");   
} 

function getWarrantyDtlForNonItem(passVal)
{
   var objVal1 = document.getElementById("warrantyDetailsTable");
   objVal1.innerHTML ="";

	var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=WARRANTYDTL&flag=NON_ITEM"+"&nonItemId="+passVal ;
    ajaxFunction(url,"11"); 
}


function getMaintenanceContractDetails(passVal)
{
	var objVal1 = document.getElementById("maintenanceContractDetailsTable");
    objVal1.innerHTML ="";

    var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=MAINTENANCECONTRACTDTL&flag=ITEM"+"&strStockInf="+passVal ;
    ajaxFunction(url,"8");   
}

function getMaintenanceContractDetailsForNonItem(passVal)
{
	var objVal1 = document.getElementById("maintenanceContractDetailsTable");
    objVal1.innerHTML ="";

  	var url="ComplaintMaintenanceStatusTransACTION.cnt?hmode=MAINTENANCECONTRACTDTL&flag=NON_ITEM"+"&strNonItemId="+passVal ;
    ajaxFunction(url,"12");   
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
				document.getElementById("enggItemSubTypeCmbDivId").innerHTML="<select name ='strEngineeringItemSubTypeId' class='COMBO_NORMAL' tabindex='1' ><option value='0'>Select Value</option></select>";
				
				
			}
			else
			{
				var objVal= document.getElementById("enggItemSubTypeCmbDivId");
				if(document.forms[0].strPageFlag.value=="NON_ITEM")
				{
					objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='COMBO_NORMAL' onchange='getNonItemName();emptyDetailsForNonItem();' tabindex='1' >"+res+"</select>";
				}
				else
				{
					objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='COMBO_NORMAL' onchange='' tabindex='1' >"+res+"</select>";
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
        
         var objVal1 =  document.getElementById("prevCompDtlId");
		 var objVal2 =  document.getElementById("warrantyDetailsTable");
		 var objVal3 =  document.getElementById("maintenanceContractDetailsTable");
		 
  		 objVal1.innerHTML ="";
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
       	            var itemParaObj = document.getElementById("prevCompDtlId");
       	            	itemParaObj.innerHTML = res.split("**")[0];
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
       	            var itemParaObj = document.getElementById("prevCompDtlId");
       	            	itemParaObj.innerHTML = res.split("**")[0];
          	  		
          	  		getWarrantyDtlForNonItem(res.split("**")[1]);
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
    
    
    // Item	Maintenance Contract Details
     if(mode=="13")
	{	
	   //alert(res);
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {   
       	           // document.getElementById("popUpDiv").style.display="block";
       	            //var itemParaObj = document.getElementById("stockDtlsDivId");
       	            	//itemParaObj.innerHTML = res;
       	            	
       	    var itemParaObj = document.getElementById("stockDtlsDivId");
			itemParaObj.innerHTML = res;
			itemParaObj.style.display='';
			popup('popUpDiv', '130', '60');
          	  		
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



 function onLoadFunction()
{

	var div1 = document.getElementById("divEmpDetails");
	var div2 = document.getElementById("divDeptNameAndStoreDetails");
	var div3 = document.getElementById("divEmpDeptAndDesignation"); 
	
	if(document.getElementsByName("strIsAttached")[0].checked)
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
		 var objVal2 =  document.getElementById("prevCompDtlId");
		 var objVal3 =  document.getElementById("warrantyDetailsTable");
		 var objVal4 =  document.getElementById("maintenanceContractDetailsTable");
		 
  		 objVal1.innerHTML ="";
  		  objVal2.innerHTML ="";
  		   objVal3.innerHTML ="";
  		    objVal4.innerHTML ="";	
	
}

function emptyDetailsForNonItem()
{
	doEmptyCombo(document.getElementsByName("strNonItemId")[0]);
	
		 var objVal2 =  document.getElementById("prevCompDtlId");
		 var objVal3 =  document.getElementById("warrantyDetailsTable");
		 var objVal4 =  document.getElementById("maintenanceContractDetailsTable");
		 
  		
  		  objVal2.innerHTML ="";
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
      



function getReport()

{
	 
	  if(document.forms[0].strComplaintId.value!="")
       {
		var hmode = "GETITEMCOMPLAINTVOUCHER";
		var url = "ComplaintMaintenanceStatusTransACTION.cnt?hmode=" + hmode + "&strStockInfoVal=" + document.forms[0].strStockInfoVal.value;
		//alert(url);
		ajaxFunction(url, "13");
       }		
}

/**
 * Prints the report.
 * @return
 */
function printData() {

    newwin = window.open('', 'printwin',
			'left=100,top=100,width=700,height=700,scrollbars=yes');
	newwin.document.write('<HTML><HEAD>');
	newwin.document.write((document.getElementsByTagName("head")[0]).innerHTML);
	newwin.document.write('<style type="text/css"> .hidecontrol { display: none; } </style>\n');
	newwin.document.write('<script>\n');
	newwin.document.write('function chkstate(){ \n');
	newwin.document.write('window.close();\n');
	newwin.document.write('}\n');
	newwin.document.write('function print_win(){\n');
	newwin.document.write('window.print();\n');
	newwin.document.write('chkstate();\n');
	newwin.document.write('}\n');
	newwin.document.write('<\/script>\n');
	newwin.document.write('</HEAD>\n');
	newwin.document.write('<BODY onload="print_win()">\n');
	newwin.document.write(document.getElementById('stockDtlsDivId').innerHTML);
	newwin.document.write('</BODY>\n');
	newwin.document.write('</HTML>\n');
	newwin.document.close();

}	

//hide_popup_menu("stockDtlsDivId");

	function hide_popup_menu(){
	
		//alert("inside hide pop up");
		document.getElementById("stockDtlsDivId").innerHTML = "";
		
		hide_popup('popUpDiv');
		
	}
	
	//added on 21/05/2018.....now done by database
/*function chkWaranty(strWarrantyDate,strWarrantyUpto,strWarrantyUptoUnit) {
			var strWarrantyUptoUnit=strWarrantyUptoUnit.substring(0, strWarrantyUptoUnit.lastIndexOf('('));
			var date=strWarrantyDate.split('-');
			var dd=date[0];
			var mm=date[1];
			var yyyy=date[2];
			var today = new Date();
		var c_year = today.getFullYear();
		var c_month=today.getMonth()+1;
		var c_day=today.getDate();
		 switch(mm){
			//1
			case "Jan":
				mm=1;
			break;
			//2
			case "Feb":
				mm=2;
			break;
			//3
			case "Mar":
				mm=3;		
			break;
			//4
			case "Apr":
				mm=4;
			break;
			//5
			case "May":
				mm=5;		
			break;
			//6
			case "Jun":
				mm=6;		
			break;
			//7
			case "Jul":
				mm=7;
			break;
			//8
			case "Aug":
				mm=8;
			break;
			//9
			case "Sep":
				mm=9;
			break;
			//10
			case "Oct":
				mm=10;
			break;
			//11
			case "Nov":
				mm=11;	
			break;
			//12
			case "Dec":
				mm=12;		
			break;
			}

		 switch(strWarrantyUptoUnit){
			case "Month":
					mm=+mm+ +strWarrantyUpto;
				break;
			case "Day":	
					dd=+dd + +strWarrantyUpto;
				break;
			case "Year":
					yyyy=+yyyy + +strWarrantyUpto;	
				break;
		}
		 alert("current date="+c_day);
		 alert("waranty date="+dd);
		 alert("waranty month="+mm);
		 alert("current month="+c_month);
		 alert("waranty year="+yyyy);
		 alert("current year="+c_year);
		if((c_year>yyyy) || (c_month>mm) || (c_day>dd)){
			document.getElementById('radWaranty').setAttribute("disabled","disabled");
			console.log('1');
			//document.getElementById("radWaranty").disabled = true;		
		}
		console.log('2');
	}
      */
	
	function editOthers(obj)
	{
		if(obj.value=="15")//Others
		{
			document.forms[0].strNatureOfService.value=obj[obj.selectedIndex].text ;
			document.forms[0].strNatureOfService.disabled=false;
		}
		else
		{
			document.forms[0].strNatureOfService.value=obj[obj.selectedIndex].text ;
			document.forms[0].strNatureOfService.disabled=true;
		}
	}