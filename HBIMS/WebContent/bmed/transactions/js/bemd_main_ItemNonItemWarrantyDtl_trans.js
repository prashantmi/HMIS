
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL"; 
	document.forms[0].submit();
}


function getOnLoad()
{
	document.forms[0].strItemMaintenanceContractMode.checked = true;
	document.forms[0].strNonItemMaintenanceContractMode.checked = false;
	document.forms[0].strCheck.value='1';
	document.forms[0].strIsExtend.value='0';
	
}
function validate1() 
{

     if(document.forms[0].strIsExtend.value!='1')
     {
  	      saveDate();
     }
     else
     {
          extendData(); 	
     }
  	 
  	
}

function saveDate()
{
	var hisValidator = new HISValidator("itemWarrantyDetailsFB");
	 
  	if(document.forms[0].strItemMaintenanceContractMode.checked)
  	{ 
  		
  	      var   chkObj = document.getElementsByName("checkid");
  		  var      len = chkObj.length;
		  var countChk = 0;
		  for ( var i = 0; i < len; i++)
		  {
		 	if (chkObj[i].checked)
		     {	
				countChk = countChk + 1;
		     }	
		  }
		  if (countChk != 1)
		  {
			alert("Please Select One Stock Record");
			return false;
		  }
		  else
		  {	 
		  	  hisValidator.addValidation("strDeptId", "dontselect=0","Please Select a Department Name");
		  	  hisValidator.addValidation("strEnggItemTypeId", "dontselect=0","Please Select a Enggineering Item Type");
		  	  hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0","Please Select a Enggineering Item Sub-Type");
		  	  hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select Item Name");
		  }
  	  
  	  
  	}
  	else
  	{
  	   hisValidator.addValidation("strEnggItemTypeId", "dontselect=0","Please Select a Enggineering Item Type");
  	   hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0","Please Select a Enggineering Item Sub-Type");	
  	   hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select Item Name");
  	}
    	hisValidator.addValidation("strSupplierId", "dontselect=0","Please Select Manufacturer Name");
	  	hisValidator.addValidation("strWarrantyStartDate", "req","Warranty Start Date is a Mandatory Field");
	  	hisValidator.addValidation("strWarrantyUpTo", "req","Warranty UpTo is a Mandatory Field");
	  	hisValidator.addValidation("strWarrantyId", "dontselect=0","Please Select Warranty UpTo Unit");
	  	//hisValidator.addValidation("strTenderNo", "req","Tender No is a Mandatory Field");
	  	//hisValidator.addValidation("strTenderDate", "req","Tender Date is a Mandatory Field");
	  	hisValidator.addValidation("strOrderNo", "req","Order No is a Mandatory Field");
	  	hisValidator.addValidation("strOrderDate", "req","Order Date is a Mandatory Field");
		hisValidator.addValidation("strInstallationDate", "req","Installation Date is a Mandatory Field");	  	
	  	//alert("strTermsAndCond :"+document.forms[0].strTermsAndCond);
	  	//hisValidator.addValidation("strTermsAndCond", "req","Terms and Condition is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefNo", "req","Document Reference No is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefDate", "req","Document Reference Date is a Mandatory Field");
  	  	
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
                       return false;
           }
}
function extendData()
{
	
	
    var hisValidator = new HISValidator("itemWarrantyDetailsFB");
  	
  	    if(document.forms[0].strItemMaintenanceContractMode.checked)
	  	{  
	  	  hisValidator.addValidation("strDeptId", "dontselect=0","Please Select a Department Name");
	  	  hisValidator.addValidation("strEnggItemTypeId", "dontselect=0","Please Select a Enggineering Item Type");
	  	  hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0","Please Select a Enggineering Item Sub-Type");
	  	  hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select Item Name");
	  	  
	  	}
	  	else
	  	{
	  	   hisValidator.addValidation("strEnggItemTypeId", "dontselect=0","Please Select a Enggineering Item Type");
	  	   hisValidator.addValidation("strEnggItemSubTypeId", "dontselect=0","Please Select a Enggineering Item Sub-Type");	
	  	   hisValidator.addValidation("strItemBrandId", "dontselect=0","Please Select Item Name");
	  	}
 	  	var date1 = document.forms[0].strExtndWarrantyStartDate.value;  	
 	  	hisValidator.addValidation("strExtndWarrantyStartDate", "req","Extended Warranty Date is a Mandatory Field");
 	  	
 	  	
	  	hisValidator.addValidation("strExtndWarrantyUpTo", "req","Extended Warranty UpTo is a Mandatory Field");
	  	hisValidator.addValidation("strExtndWarrantyId", "dontselect=0","Please Select Extended Warranty UpTo Unit");
	  	//hisValidator.addValidation("strExtndTermsAndCond", "req","Extended Terms and  is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefNo", "req","Document Reference No is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefDate", "req","Document Reference Date is a Mandatory Field");
  	  	
  	  var retVal = hisValidator.validate(); 
          hisValidator.clearAllValidations();
           if(retVal)
           {
           	          
                 	   document.forms[0].hmode.value = "EXTEND";
                 	   document.forms[0].target = "_self";
                       document.forms[0].submit();
           }
           else
           {
                       return false;
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

function getUploadFile(obj,index,fileId)
{
	
	
		if(fileId!='0')
		{
			document.forms[0].hmode.value="GETUPLOADEDFILE"; 
			document.forms[0].strUploadFileId.value=fileId+"."+document.getElementById("strReqUpLoadFileName"+index).value.split(".")[1];
			document.forms[0].target = "_blank";
			document.forms[0].submit();
		}
		else
		{
			alert("No File to Up-Load!!");			
		}
	
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
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Hide";
		document.getElementById("prevLnkDivId").style.display='none';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/minus.gif";
		thisImg.title = "Show";
		document.getElementById("prevLnkDivId").style.display='block';
	}	
 } 
 
 
 
  function showOrHidePrevWarrantyDetails(thisImg)
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
		document.getElementById("prevWarrantyDtlDivId").style.display='block';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";
		document.getElementById("prevWarrantyDtlDivId").style.display='none';
	}	
 } 
 
 function showOrHideFileUpLoadDetails(thisImg)
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
		document.getElementById("docUpLoadId").style.display='block';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";
		document.getElementById("docUpLoadId").style.display='none';
	}	
 	
 } 
 function hideInfoPopup()
 {

	hide_popup('popUpDiv');

}

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
function GetIndex2(index,endVal)  // Pagenation  Two
{
	
	    for (var page = 1; page <= endVal; page++)       // For Loop To Put Normal CSS in All No
        {
          document.getElementById('mg'+page).className = 'pg-normal';      
        }
	     var PageAnchor = document.getElementById('mg'+index);
         PageAnchor.className = 'pg-selected';                    // Apply CSS
          for(var i = 1; i <= endVal ; i++)
		  {
			  document.getElementById("DivId2"+i).style.display="none";
		  }
		  document.getElementById("DivId2"+index).style.display="block";
			 
}

 function ClearPage()
 {
 	
 	if(document.forms[0].strCheck.value=='1')
 	{
	 	document.forms[0].strItemMaintenanceContractMode.checked = true;
		document.forms[0].strNonItemMaintenanceContractMode.checked = false;
		document.forms[0].strCheck.value='1';
 	}
 	else
 	{
 		document.forms[0].strItemMaintenanceContractMode.checked = false;
		document.forms[0].strNonItemMaintenanceContractMode.checked = true;
		document.forms[0].strCheck.value='2';
 	}	
 	
 	document.getElementById("hideWarrantyExtndDtlId").style.display="none";
	document.getElementById("hideWarrantyDtlId").style.display="block";
 	
   document.getElementById("errMsg").innerHTML = "";	
    var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
   var objVal2 = document.getElementById("prevWarrantyDtlId");
   objVal2.innerHTML ="";
   
   var objVal3 = document.getElementById("itemNameId");
    objVal3.innerHTML ="<select name=strItemBrandId class='COMBO_NORMAL'	onchange='getStockDtl();'><bean:write name='itemWarrantyDetailsFB' property='strItemNameCmb' filter='false'/><option value='0'>Select Value</option></select>";
   
   var itemParaObj = document.getElementById("extendTagId");
         itemParaObj.innerHTML ="";
   
      document.getElementById("extndSupplierDivId").innerHTML= "";
     
     document.getElementById("exntdWarrantyStartDateDiv").innerHTML= "";
    
     document.getElementById("exntdWarrantyUpToDiv").innerHTML="";
     
     document.getElementById("warrantyUpToUnitDiv").innerHTML="";
     
     //document.getElementById("termsAndCondDiv").innerHTML=tmp[16];
               
     document.getElementById("termsAndCondDiv").innerHTML="<textarea name='strTermsAndCond'	cols='25' rows='2' readonly></textarea>";
    
     document.forms[0].reset();
   
   
 }  

function openBlanketPopUp(obj,index,gen)
{
	/*
	Value in strWarrantyTableInfo Concated by ^ Symbol
	      Value in WebRowSet----
			1.HSTNUM_ITEM_ID:              
			2.HSTNUM_ITEMBRAND_ID:         
			3.HSTSTR_BATCH_SL_NO:          
			4.GNUM_HOSPITAL_CODE:          
			5.HSTNUM_ITEM_SL_NO:           
			6.HSTNUM_MANUF_ID:             
			7.HSTNUM_MANUF_SL_NO:          
			8.HSTNUM_SL_NO:                
			9.HSTDT_WARRENTY_DATE:         
		    10.HSTNUM_WARRENTY_UPTO:        
		    11.HSTNUM_WARRENTY_UPTO_UNIT:   
		    12.HSTNUM_IS_ITEM:              
			13.HSTDT_FINANCIAL_START_YEAR:  
			14.HSTDT_FINANCIAL_END_YEAR:    
			15.HPURNUM_UPLOAD_NO:           
			16.HPURSTR_DOC_REF_NO:          
			17.HEMSTR_TERM_N_CON:           
			18.HPURDT_DOC_REF_DATE:         
			19.GSTR_REMARKS:                
			20.GDT_ENTRY_DATE:              
			21.GNUM_SEATID:                 
			22.GNUM_ISVALID:                
			23.HEMSTR_TENDER_NO:            
			24.GDT_LSTMOD_DATE:             
			25.HEMDT_TENDER_DATE:           
			26.GNUM_LSTMOD_SEATID:          
			27.HEMSTR_ORDER_NO:             
			28.HEMDT_ORDER_DATE:            
			29.HEMNO_CANCEL_ID:             
			30.HEMDT_CANCEL_DATE:           
			31.HEMSTR_EXT_TERM_N_CON:       
			32.HEMSTR_CANCEL_REMARKS:       
			33.HEMNUM_IS_EXTENDED:          
			34.HEMDT_EXTENDED_START_DATE:   
			35.HEMNUM_EXTENDED_UPTO:        
			36.HEMNUM_EXTENDED_UPTO_UNIT:   
			37.HPURNUM_EXT_UPLOAD_NO:       
			38.HPURNUM_EXT_DOC_REF_NO:      
			39.HPURDT_EXT_DOC_REF_DATE:     
			40.ITEM_NAME:                   
			41.VENDOR_NAME:                 
			42.WARRENTY_UPTO_UNIT_NAME:   
			43.WARRANTY_EXTEND_DATE:
           * 
           * */
	var strWarrantyTableInfo  = document.getElementById('strWarrantyTableInfo'+index).value;
	var stockInfo ;
    
    if(document.forms[0].strItemMaintenanceContractMode.checked)
    {
		    var   obj    = document.getElementsByName("checkid"); 
		    var   obj2   = document.getElementsByName("strStockInf");
		    var   obj3   = document.getElementsByName("varIndex");
		    
		    var valIndex ;
		     
		     if(obj.length>0)
		     {   
		        for(var i = 0 ; i < obj.length ; i++)
			    {      
			      if(obj[i].checked)
			      {
			        stockInfo = obj2[i].value;
			        valIndex  = obj3[i].value;
		           
			      }
			      
			    }
		     }
		     
		      /*			
		       * 
					1. Store Name
					2. Item SL No
					3. Batch No
					4. Manufacter No
					5. HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
					   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
		               GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE		       
		        */
		    		    
		    var tmp         = stockInfo.split("^");
		    var StoreName   = document.getElementById('StoreName'+valIndex).value;
			var ItemSlNo    = document.getElementById('ItemSlNo'+valIndex).value;
			var BatchNo     = document.getElementById('BatchNo'+valIndex).value;
			var ManufactNo  = document.getElementById('ManufactNo'+valIndex).value;
			var storeId     = tmp[0];
			var itemId      = tmp[1];
			var itemBrandId = tmp[2];
			var stockStatus = tmp[6];
    } 
    if(document.forms[0].strNonItemMaintenanceContractMode.checked)
    {           	    
    	   // HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE	
    	   stockInfo = 0+"^"+0+"^"+document.forms[0].strItemBrandId.value+"^"+0+"^"+0+"^"+108+"^"+0;        	
    }
        
    if(gen=='1')
    {   
    	    
    	var infoPass =  strWarrantyTableInfo;
    	getExtend(infoPass,gen);
    }  
    if(gen=='2')
    {
    	
    	var infoPass =  strWarrantyTableInfo;
    	getCancel(infoPass,gen);
    }   
    if(gen=='3')
    {
    
    	var infoPass =  strWarrantyTableInfo;
    	getView(infoPass,gen);
    }    
   	  
		
}
function getExtend(infoPass,gen)
{	
	
	/*
	       Value in strWarrantyTableInfo Concated by ^ Symbol
	        Value in WebRowSet----
			0.HSTNUM_ITEM_ID:              
			1.HSTNUM_ITEMBRAND_ID:         
			2.HSTSTR_BATCH_SL_NO:          
			3.GNUM_HOSPITAL_CODE:          
			4.HSTNUM_ITEM_SL_NO:           
			5.HSTNUM_MANUF_ID:             
			6.HSTNUM_MANUF_SL_NO:          
			7.HSTNUM_SL_NO:                
			8.HSTDT_WARRENTY_DATE:         
		    9.HSTNUM_WARRENTY_UPTO:        
		    10.HSTNUM_WARRENTY_UPTO_UNIT:   
		    11.HSTNUM_IS_ITEM:              
			12.HSTDT_FINANCIAL_START_YEAR:  
			13.HSTDT_FINANCIAL_END_YEAR:    
			14.HPURNUM_UPLOAD_NO:           
			15.HPURSTR_DOC_REF_NO:          
			16.HEMSTR_TERM_N_CON:           
			17.HPURDT_DOC_REF_DATE:         
			18.GSTR_REMARKS:                
			19.GDT_ENTRY_DATE:              
			20.GNUM_SEATID:                 
			21.GNUM_ISVALID:                
			22.HEMSTR_TENDER_NO:            
			23.GDT_LSTMOD_DATE:             
			24.HEMDT_TENDER_DATE:           
			25.GNUM_LSTMOD_SEATID:          
			26.HEMSTR_ORDER_NO:             
			27.HEMDT_ORDER_DATE:            
			28.HEMNO_CANCEL_ID:             
			29.HEMDT_CANCEL_DATE:           
			30.HEMSTR_EXT_TERM_N_CON:       
			31.HEMSTR_CANCEL_REMARKS:       
			32.HEMNUM_IS_EXTENDED:          
			33.HEMDT_EXTENDED_START_DATE:   
			34.HEMNUM_EXTENDED_UPTO:        
			35.HEMNUM_EXTENDED_UPTO_UNIT:   
			36.HPURNUM_EXT_UPLOAD_NO:       
			37.HPURNUM_EXT_DOC_REF_NO:      
			38.HPURDT_EXT_DOC_REF_DATE:     
			39.ITEM_NAME:                   
			40.VENDOR_NAME:                 
			41.WARRENTY_UPTO_UNIT_NAME:   
			42.WARRANTY_EXTEND_DATE:
           * 
           * */
	document.getElementById("hideWarrantyExtndDtlId").style.display="block";
	document.getElementById("hideWarrantyDtlId").style.display="none";
	document.getElementById('extendTagId').style.display="block";
	
	 document.forms[0].strIsExtend.value='1';
	 
	 var itemParaObj = document.getElementById("extendTagId");
         itemParaObj.innerHTML ="<his:ContentTag><table class='TABLE_STYLE'><tr class='FOOTER_TR'><th colspan='8' align='left'><div id='Id1' style='color:blue;'>Extende Warranty</div></th></tr></table></his:ContentTag>";
    
     var tmp         = infoPass.split("^");
                                       //    Item Id ^ Item BrandId ^ Batch No ^ Hosp Code ^ Item Sl No ^ Manuf Id ^ Manu Fat Sl No ^ Sl No  
     document.forms[0].strIsExtendPK.value = tmp[0]+"^"+tmp[1]+"^"+tmp[2]+"^"+tmp[3]+"^"+tmp[4]+"^"+tmp[5]+"^"+tmp[6]+"^"+tmp[7];
     
   
     document.getElementById("extndSupplierDivId").innerHTML= tmp[40];
     
     document.getElementById("exntdWarrantyStartDateDiv").innerHTML= tmp[8];
    
     document.getElementById("exntdWarrantyUpToDiv").innerHTML=tmp[9];
     
     document.getElementById("warrantyUpToUnitDiv").innerHTML=tmp[41];
     
     //document.getElementById("termsAndCondDiv").innerHTML=tmp[16];
               
     document.getElementById("termsAndCondDiv").innerHTML="<textarea name='strTermsAndCond'	cols='25' rows='2' readonly>"+tmp[30]+"</textarea>";
 	
 
}
 
function getCancel(infoPass,gen)
{
	 document.forms[0].strIsExtend.value='0';
	        document.getElementById("hideWarrantyExtndDtlId").style.display="none";
	        document.getElementById("hideWarrantyDtlId").style.display="block";
	        var itemParaObj = document.getElementById("extendTagId");
          itemParaObj.innerHTML ="";
	 	var department     = document.forms[0].strDeptId[document.forms[0].strDeptId.selectedIndex].text;
        var storeName      = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
        var enggItemTyp    = document.forms[0].strEnggItemTypeId[document.forms[0].strEnggItemTypeId.selectedIndex].text;
        var enggItemSubTyp = document.forms[0].strEnggItemSubTypeId[document.forms[0].strEnggItemSubTypeId.selectedIndex].text;
        var iteBrandId     = document.forms[0].strItemBrandId[document.forms[0].strItemBrandId.selectedIndex].text;
        
    if(enggItemTyp!='Select Value')
    {      
    	if(enggItemSubTyp!='Select Value')
    	{                                     
	        var passInfoTofunc =  infoPass +"^"+department+"^" +storeName +"^"+ enggItemTyp+"^"+enggItemSubTyp +"^"+iteBrandId ;
	 	    var url="ItemWarrantyDtlsACTION.cnt?hmode=WARRANTYCANCEL&info="+passInfoTofunc+"&mode="+gen;
	        ajaxFunction(url,"9");
    	}
    	else
    	{
    		 alert("Please Select Engineering Item Sub Type!!!!");
    	     return false;
    	}   
    }
    else
    {
    	alert("Please Select Engineering Item Type!!!!");
    	return false;
    }    
	
} 
function getView(infoPass,gen)
{
	  document.getElementById("hideWarrantyExtndDtlId").style.display="none";
	  document.getElementById("hideWarrantyDtlId").style.display="block";
      var itemParaObj = document.getElementById("extendTagId");
      itemParaObj.innerHTML ="";   
         
      var department     = document.forms[0].strDeptId[document.forms[0].strDeptId.selectedIndex].text;
      var storeName      = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
      var enggItemTyp    = document.forms[0].strEnggItemTypeId[document.forms[0].strEnggItemTypeId.selectedIndex].text;
      var enggItemSubTyp = document.forms[0].strEnggItemSubTypeId[document.forms[0].strEnggItemSubTypeId.selectedIndex].text;
      var iteBrandId     = document.forms[0].strItemBrandId[document.forms[0].strItemBrandId.selectedIndex].text;
        
                                                
      var passInfoTofunc =  infoPass +"^"+department+"^" +storeName +"^"+ enggItemTyp+"^"+enggItemSubTyp +"^"+iteBrandId ;
     
         
	  if(document.forms[0].strItemMaintenanceContractMode.checked)
	     var url="ItemWarrantyDtlsACTION.cnt?hmode=MANTVIEW&info="+passInfoTofunc+"&mode="+gen+"&isItem=1";
	  else
	     var url="ItemWarrantyDtlsACTION.cnt?hmode=MANTVIEW&info="+passInfoTofunc+"&mode="+gen+"&isItem=2";
	  
	  ajaxFunction(url,"10");
	
}
 
function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
    var objVal1 = document.getElementById("prevWarrantyDtlId");
   objVal1.innerHTML ="";
   document.forms[0].strSupplierId.value='0';
   
   if(document.forms[0].strItemMaintenanceContractMode.checked)
   {
     var url="ItemWarrantyDtlsACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemBrandId.value+"&deptId="+document.forms[0].strDeptId.value+"&mode="+1;
   }
   else
   {
   	var url="ItemWarrantyDtlsACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemBrandId.value+"&deptId="+0+"&mode="+2;
   }
   //alert(url);
   ajaxFunction(url,"1");
 
} 

function getStoreName()
{	   
   if(document.forms[0].strItemMaintenanceContractMode.checked || document.forms[0].strNonItemMaintenanceContractMode.checked)
   {
   	   var objVal1 = document.getElementById("stockValueId");
       objVal1.innerHTML ="";
   
       var objVal1 = document.getElementById("prevWarrantyDtlId");
       objVal1.innerHTML ="";
       
       document.forms[0].strSupplierId.value='0';
       
	   var url="ItemWarrantyDtlsACTION.cnt?hmode=GETSTORENAME&deptId="+document.forms[0].strDeptId.value;
	   ajaxFunction(url,"2");
   }
   else
   {
   	 alert("Please Select Item or Non-Item !!!");
   	 return false;
   }
  
} 

function getItemName()
{	
    //alert("Store ID:::"+document.forms[0].strStoreId.value+":::Dept ID:::"+document.forms[0].strDeptId.value);	
   if(document.forms[0].strStoreId.value!='0')	   
   {
      var url="ItemWarrantyDtlsACTION.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
   }
   else
   {
   	  var url="ItemWarrantyDtlsACTION.cnt?hmode=GETITEMNAME&flag="+1+"&deptId="+document.forms[0].strDeptId.value;
   }   
   ajaxFunction(url,"3");
   
  
} 

function getEnggItemSubType()
{	   
   var url="ItemWarrantyDtlsACTION.cnt?hmode=GETENGGITEMSUBTYPE&deptId="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"4");
   
  
} 
function getEnggItemSubTypeCombo()
{
  var url="ItemWarrantyDtlsACTION.cnt?hmode=GETENGGITEMSUBTYPE&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"6");
}

function getNonItemPrevMantDtl()
{
	    var objVal1 = document.getElementById("prevWarrantyDtlId");
        objVal1.innerHTML ="";  
        
      // HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID||'^'||HSTSTR_BATCH_NO||'^'||
      // HSTSTR_ITEM_SL_NO||'^'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
                
      var tmpStockInfo = 0+"^"+0+"^"+document.forms[0].strItemBrandId.value+"^"+0+"^"+0+"^"+108+"^"+0+"^"+2;       
     document.forms[0].strStockInfoVal.value = tmpStockInfo;
    	 
     var url="ItemWarrantyDtlsACTION.cnt?hmode=GETPREVWARANTYDTL&strStockInf="+tmpStockInfo;
     ajaxFunction(url,"7");
}     


/**
 * changeViewMode
 * @param {Object} chkObj 
 */
function changeViewMode(chkObj)
{ 	
    if(chkObj.value =='1')
 	{ 	
 		 document.forms[0].strCheck.value = '1'; 
 		 document.getElementById('itemHide2').style.display="block";
 		 document.getElementById('TitleHide1').style.display="block";
         document.getElementById('TitleHide2').style.display="none";
         ClearPage();
         document.forms[0].strItemMaintenanceContractMode.checked = true;
 		 document.forms[0].strNonItemMaintenanceContractMode.checked = false;
 		 document.forms[0].strIsExtend.value='0';
 	}
 	if(chkObj.value =='2')
 	{ 		 
 		 
 		 document.forms[0].strCheck.value = '2'; 
         document.getElementById('TitleHide1').style.display="none";
         document.getElementById('TitleHide2').style.display="block";
         document.getElementById('itemHide2').style.display="none";
         ClearPage();
         document.forms[0].strItemMaintenanceContractMode.checked = false;
 		 document.forms[0].strNonItemMaintenanceContractMode.checked = true;
 		 document.forms[0].strIsExtend.value='0';
 	}
}
/*
 * This function is called in case of Non-Item 
 */
function getNonItem()
{
	if(document.forms[0].strCheck.value='2')
	 {
	 	  var url="ItemWarrantyDtlsACTION.cnt?hmode=GETITEMNAME&flag="+2+"&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
          ajaxFunction(url,"11");
	 }  
}


function getContractSupplier()
{	   
   var url="ItemWarrantyDtlsACTION.cnt?hmode=GETSUPPLIER&itemId="+document.forms[0].strItemBrandId.value+"^"+document.forms[0].strCheck.value;
   ajaxFunction(url,"5");
  
  
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
	       
       	            var itemParaObj = document.getElementById("stockValueId");
       	            	itemParaObj.innerHTML = res;
       	            	getContractSupplier();
          	  
		      
      
       
    }
    
    if(mode=="2")
	{	
	   
       	            var itemParaObj = document.getElementById("storeNameDiv");
       	            
       	            itemParaObj.innerHTML = "<select name = 'strStoreId'  onchange='getItemName();' class='COMBO_NORMAL'>" + res + "</select>";
          	        getItemName();
		      
       
       
    }
    
    if(mode=="3")
	{	
	   
       	            var itemParaObj = document.getElementById("itemNameId");
       	            itemParaObj.innerHTML ="<select name = 'strItemBrandId' onchange='getStockDtl();' class='COMBO_NORMAL'>" + res + "</select>";
          	        
		           
       
       
    }
    
    if(mode=="4")
	{	
	  
       
       	            var itemParaObj = document.getElementById("engineeringItemSubTypeId");
       	            itemParaObj.innerHTML = "<select name = 'strEnggItemTypeId' class='COMBO_NORMAL'>" + res + "</select>";
          	 
		      
      
       
    }
    
    if(mode=="6")
	{	
	  
       	            //alert("Inside Mode 6:::"+document.forms[0].strItemMaintenanceContractMode.value);
       	            var itemParaObj = document.getElementById("engineeringItemSubTypeId");
       	            if(document.forms[0].strItemMaintenanceContractMode.checked)
       	            {
       	            	itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL'>" + res + "</select>";
       	            }
       	            else
       	            {
       	            	itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL' onChange='getNonItem();'>" + res + "</select>";
       	            }
 		     	            
       	            
          	 
		      
        
       
    }
    
     if(mode=="5")
	{	
	   
       	
       	            var itemParaObj = document.getElementById("supplierIdDiv");
       	            itemParaObj.innerHTML = "<select name = 'strSupplierId' class='COMBO_NORMAL'>" + res + "</select>";
          	 
		      
        
       
    }
    
    if(mode=="7")
	{	
		   
	            var itemParaObj = document.getElementById("prevWarrantyDtlId");
       	            itemParaObj.innerHTML = res;
       	            document.getElementById('hideWarrantyDtlId').style.display="block";
	                document.getElementById('hideWarrantyExtndDtlId').style.display="none";
	                document.getElementById('extendTagId').style.display="none";         
       	            
       	            getContractSupplier();
 		      
        
       
    }
    
    
    if(mode=="8")
	{	
	   
       	            var itemParaObj = document.getElementById("strRenew");
       	            itemParaObj.innerHTML = res;
          	        popup('popUpDiv', '120', '320');
		      
        
       
    }
    
    
    if(mode=="9")
	{	
	   
       	            var itemParaObj = document.getElementById("strRenew");
       	            itemParaObj.innerHTML = res;
          	        popup('popUpDiv', '120', '320');
		      
        
       
    }
    if(mode=="10")
	{	
	   
       	            var itemParaObj = document.getElementById("strRenew");
       	            itemParaObj.innerHTML = res;
          	        popup('popUpDiv', '120', '320');
		      
       
       
    }
    
    if(mode=="11")
	{	
	  
       	            var itemParaObj = document.getElementById("itemNameId");
       	            itemParaObj.innerHTML ="<select name = 'strItemBrandId' onchange='getNonItemPrevMantDtl();' class='COMBO_NORMAL'>" + res + "</select>";
          	        
		           
        
       
    }
    
    if(mode=="12");
	{	
	  
                
           	      // Start If 
           	        var stockInfo ;
           	        var valIndex ;
				    
						    var   obj    = document.getElementsByName("checkid"); 
						    var   obj2   = document.getElementsByName("strStockInf");
						    var   obj3   = document.getElementsByName("varIndex");
							if(res=='1')
							{				    
									if(document.forms[0].strIsCancel.value=='1')
									{		    
											  document.forms[0].strIsCancel.value='0';  
											     if(obj.length>0)
											     {   
											        for(var i = 0 ; i < obj.length ; i++)
												    {      
												      if(obj[i].checked)
												      {
												        stockInfo = obj2[i].value;
												        valIndex  = obj3[i].value;
											           
												      }
												      
												    }
											     }	
											    											     
											     radioBtnClick(obj,valIndex);				        
									}
									else
									{
										getNonItemPrevMantDtl();
									}  
									   
					           } 	        
		           
       
       
    }
}  
 

/**
 * This Function Call When Radio button Click In Stock Details HLP
 */
function radioBtnClick(obj,index)
{		
	var passVal;
	// Here we Block Warranty Details and Hide Warranty Extend Details
	document.getElementById('hideWarrantyDtlId').style.display="block";
	document.getElementById('hideWarrantyExtndDtlId').style.display="none";
	document.getElementById('extendTagId').style.display="none";
	
	
	if(document.forms[0].strItemMaintenanceContractMode.checked)
    {
    	var val        = document.getElementById('strStockInf'+index).value;
		document.getElementById('checkid'+index).value = 1;
		var passVal = val+"^"+1;
		
		document.forms[0].strStockInfoVal.value = document.getElementById('strStockInf'+index).value;
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
    }
    else
    {
       var tmpStockInfo = 0+"^"+0+"^"+document.forms[0].strItemBrandId.value+"^"+0+"^"+0+"^"+108+"^"+0; 	
    }
	 var url="ItemWarrantyDtlsACTION.cnt?hmode=GETPREVWARANTYDTL&strStockInf="+passVal;
     ajaxFunction(url,"7");
	
	
	
}

/**
 * This Method is Used when Cancel Pop-Up 
 * 
 */
function SaveCancel()
{
	var hisValidator = new HISValidator("itemWarrantyDetailsFB");
	hisValidator.addValidation("strCancelTypeId", "dontselect=0","Please Select a Cancel Type");
	hisValidator.addValidation("strCancelRemarks", "req","Cancel remarks is a Mandatory Field");
	var retVal = hisValidator.validate(); 
    hisValidator.clearAllValidations();
           if(retVal)
           {
                  var url="ItemWarrantyDtlsACTION.cnt?hmode=SAVECANCEL&cancelType="+document.forms[0].strCancelTypeId.value+"&cancelRemarks="+document.forms[0].strCancelRemarks.value+"&stockInfo="+document.forms[0].strCancelStockInfo.value+"&slNo="+document.forms[0].strSlNo.value;
                  ajaxFunction(url,"12");
	           	  document.forms[0].strIsCancel.value='1';
	 	          hide_popup('popUpDiv');
           }
           else
           {
                       return false;
           }
	
	
}

