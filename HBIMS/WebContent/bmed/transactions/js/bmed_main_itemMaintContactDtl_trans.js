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
}
function validate1() 
{

     if(document.forms[0].strIsReNew.value!='1')
     {
  	      saveDate();
     }
     else
     {
          renewData(); 	
     }
  	 
  	
}

function saveDate()
{
	var hisValidator = new HISValidator("itemContractDetailsFB");
  	
  	
	 
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
	  	hisValidator.addValidation("strMantContractSuppId", "dontselect=0","Please Select a Maintenance Contract Supplier");
	  	hisValidator.addValidation("strMantContractTypeId", "dontselect=0","Please Select a Maintenance Contract Type");
	  	hisValidator.addValidation("strMaintenanceContractName", "req","Please enter the Maintenance Company Name");
	  	
	  	//hisValidator.addValidation("strTenderNo", "req","Tender No is a Mandatory Field");
	  	hisValidator.addValidation("strTenderDate", "req","Tender Date is a Mandatory Field");
	  	hisValidator.addValidation("strOrderNo", "req","Order No is a Mandatory Field");
	  	//hisValidator.addValidation("strOrderDate", "req","Order Date is a Mandatory Field");
	  	hisValidator.addValidation("strFromDate", "req","From Date is a Mandatory Field");
	  	hisValidator.addValidation("strToDate", "req","To Date is a Mandatory Field");
	  	
	  	hisValidator.addValidation("strRoutineFrequency", "req","Preventive maintenance is a Mandatory Field");
	  	hisValidator.addValidation("strRoutineUnitId", "dontselect=0","Please Select a Preventive maintenance Unit");
	  	
	  	hisValidator.addValidation("strResponseTime", "req","Response Time is a Mandatory Field");
	  	hisValidator.addValidation("strResponseTimeUnitId", "dontselect=0","Please Select a Response Time Unit");
	  	
	  	hisValidator.addValidation("strMaintenanceCost", "req", "Maintenance Cost is a Mandatory Field");
	  	
	  	hisValidator.addValidation("strTermsAndCond", "req","Terms & Condition is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefNo", "req","Document Reference No is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefDate", "req","Document Reference Date is a Mandatory Field");
  	  	hisValidator.addValidation("strPeneltyCond", "req","Penalty Conditions is a Mandatory Field");
  	
  	
  	
	  	var l_strTenderDate = document.getElementById("strTenderDate").value;
		var l_strOrderDate  = document.getElementById("strOrderDate").value;
		var l_strFromDate   = document.getElementById("strFromDate").value;
		var l_strToDate     = document.getElementById("strToDate").value;
		var l_strDocRefDate = document.getElementById("strDocRefDate").value;
	
	//alert("l_strOrderDate="+l_strOrderDate);
	if(l_strOrderDate!="")
		{
		  	if(l_strTenderDate!="") 
		  	{
			   hisValidator.addValidation("strOrderDate", "dtgt="+l_strTenderDate,"Order Date should be Greater than Tender Date");
		    }
		    if(l_strOrderDate!="") 
		  	{
			   hisValidator.addValidation("strFromDate", "dtgt="+l_strOrderDate,"From Date should be Greater than Order Date");
		    }
		}
    
    if(l_strFromDate!="") 
  	{
	   hisValidator.addValidation("strToDate", "dtgt="+l_strFromDate,"To Date should be Greater than From Date");
    }
	if(l_strOrderDate!="")
	{
	  	if(l_strOrderDate!="") 
	  	{
		   hisValidator.addValidation("strDocRefDate", "dtgt="+l_strOrderDate,"Document Refrence Date should be Greater than Order Date");
	    }
	}
  	
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
function renewData()
{
	
	
    var hisValidator = new HISValidator("itemContractDetailsFB");
  	
  	var l_strTenderDate = document.getElementById("strTenderDate").value;
	var l_strOrderDate  = document.getElementById("strOrderDate").value;
	var l_strFromDate   = document.getElementById("strFromDate").value;
	var l_strToDate     = document.getElementById("strToDate").value;
	var l_strDocRefDate = document.getElementById("strDocRefDate").value;
	 
 	  	hisValidator.addValidation("strMantContractTypeId", "dontselect=0","Please Select a Maintenance Contract Type");
	  	  	
	  	hisValidator.addValidation("strTenderNo", "req","Tender No is a Mandatory Field");
	  	hisValidator.addValidation("strTenderDate", "req","Tender Date is a Mandatory Field");
	  	hisValidator.addValidation("strOrderNo", "req","Order No is a Mandatory Field");
	  	hisValidator.addValidation("strOrderDate", "req","Order Date is a Mandatory Field");
	  	hisValidator.addValidation("strFromDate", "req","From Date is a Mandatory Field");
	  	hisValidator.addValidation("strToDate", "req","To Date is a Mandatory Field");
	  	hisValidator.addValidation("strRoutineFrequency", "req","Routine Frequency is a Mandatory Field");
	  	hisValidator.addValidation("strRenewRoutineUnitId", "dontselect=0","Please Select a Routine Frequency Unit");
	  	hisValidator.addValidation("strResponseTime", "req","Response Time is a Mandatory Field");
	  	hisValidator.addValidation("strRenewResponseTimeUnitId", "dontselect=0","Please Select a Response Time Unit");
	  	hisValidator.addValidation("strMaintenanceCost", "req", "Maintenance Cost is a Mandatory Field");
	  	
	  	hisValidator.addValidation("strTermsAndCond", "req","Terms & Condition is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefNo", "req","Document Reference No is a Mandatory Field");
	  	hisValidator.addValidation("strDocRefDate", "req","Document Reference Date is a Mandatory Field");
  	
	  	if(l_strTenderDate!="") 
	  	{
		   hisValidator.addValidation("strOrderDate", "dtgt="+l_strTenderDate,"Order Date should be Greater than Tender Date");
	    }
	    if(l_strOrderDate!="") 
	  	{
		   hisValidator.addValidation("strFromDate", "dtgt="+l_strOrderDate,"From Date should be Greater than Order Date");
	    }
	    if(l_strFromDate!="") 
	  	{
		   hisValidator.addValidation("strToDate", "dtgt="+l_strFromDate,"To Date should be Greater than From Date");
	    }
	  	if(l_strOrderDate!="") 
	  	{
		   hisValidator.addValidation("strDocRefDate", "dtgt="+l_strOrderDate,"Document Refrence Date should be Greater than Order Date");
	    }
  	
  	  var retVal = hisValidator.validate(); 
          hisValidator.clearAllValidations();
           if(retVal)
           {
           	          
                 	   document.forms[0].hmode.value = "RENEW";
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
 
 
 function showOrHidePrevMaintenanceDetails(thisImg)
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
		document.getElementById("prevMantDtlDivId").style.display='block';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";
		document.getElementById("prevMantDtlDivId").style.display='none';
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
 	
     document.getElementById("errMsg").innerHTML = "";	
     var objVal1 = document.getElementById("stockValueId");
       objVal1.innerHTML ="";
   
     var objVal2 = document.getElementById("prevMaintDtlId");
        objVal2.innerHTML ="";
   
     var objVal3 = document.getElementById("itemNameId");
        objVal3.innerHTML ="<select name=strItemBrandId class='COMBO_NORMAL'	onchange='getStockDtl();'><bean:write name='itemContractDetailsFB' property='strItemNameCmb' filter='false'/><option value='0'>Select Value</option></select>";
   
     var itemParaObj = document.getElementById("reNewTagId");
         itemParaObj.innerHTML ="";
   
     document.forms[0].strRoutineUnitId.value="0";
     document.forms[0].strResponseTimeUnitId.value="0";
    
     document.getElementById("routineFreqUnitDiv").style.display="block";
     document.getElementById("responseTimeUnitDiv").style.display="block";
     document.getElementById("cancelResponseTimeUnitDiv").style.display="none";
     document.getElementById("cancelRoutineFreqUnitDiv").style.display="none";
       	 
   
     var itemParaObj = document.getElementById("reNewTagId");
         itemParaObj.innerHTML ="";
     
     document.getElementById("maintenanceContractSupplierId").style.display="block";
     document.getElementById("renewMaintenanceContractSupplierId").style.display="none";
     document.getElementById("mantContName").innerHTML= "<input type='text' class='TEXT_FIELD_MAX' name='strMaintenanceContractName' maxlength='15' value=''>";

     document.getElementById("tenderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strTenderNo' maxlength='15' value='' onkeypress='return validateData(event,5);'>";
     
     document.getElementById("orderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strOrderNo' maxlength='15' value='' 	onkeypress='return validateData(event,5);'>";
     
     document.forms[0].strOrderDate.value="";
     document.forms[0].strTenderDate.value="";
     
     document.forms[0].strFromDate.value="";
     document.forms[0].strToDate.value="";
     
     document.forms[0].strDocRefDate.value="";
     
    
      document.getElementById("routineFreqDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strRoutineFrequency' maxlength='2' value='' 	onkeypress='return validateData(event,5);'>";
      document.getElementById("responseTimeDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strResponseTime' maxlength='1' value='' 	onkeypress='return validateData(event,5);'>";
      document.getElementById("mantCostDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strMaintenanceCost' maxlength='10' value='' 	onkeypress=\"return (numericWithTwoDecimalPlaces(this,',','.',event));\" >";
      document.getElementById("remarksDiv").innerHTML="<textarea name='strRemarks'	cols='25' rows='2' ></textarea>";
      document.getElementById("termsCondDiv").innerHTML="<textarea name='strTermsAndCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'></textarea>";
      document.getElementById("penaltyDiv").innerHTML="<textarea name='strPeneltyCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'></textarea>";
     
   
     document.forms[0].reset();
   
   
 }  

function openBlanketPopUp(obj,index,gen)
{
	/*
	Value in strReaminInfo Concated by ^ Symbol
	       0. HEMNUM_ROUTINE_VISIT
		   1. HEMNUM_BREAK_VISIT
           2. HEMSTR_RESPONSE_TIME 
           3. HEMNUM_ROUTINE_FREQ
           4. Frequency Unit Name  
           5. Response Time Unit Name 
           6. HEMSTR_TENDER_NO 
           7. HPURNUM_UPLOAD_NO 
           8. HPURSTR_DOC_REF_NO  
           9. HEMDT_TENDER_DATE,
           10. HPURDT_DOC_REF_DATE  
           11. HEMSTR_ORDER_NO  
           12. HEMDT_ORDER_DATE 
           13. GSTR_REMARKS 
           14. HEMNUM_IS_RENEWED
           15. HEMSTR_FREQ_UNIT 
           16. HEMSTR_RES_TIME_UNIT
           17. SL NO
           * 
           * */
	var mantContSupplier = document.getElementById('strMantContSupplier'+index).value;
	var mantContType     = document.getElementById('strMantContName'+index).value;
	var strReaminInfo    = document.getElementById('strReaminInfo'+index).value;
	var mantStartDate    = document.getElementById('strMantStartDate'+index).value;
	var mantEndDate      = document.getElementById('strMantEndDate'+index).value;
	var mantCost         = document.getElementById('strMantCost'+index).value;
	var termsCond        = document.getElementById('strTermsCond'+index).value;
	var penelty          = document.getElementById('strPenelty'+index).value;
    var pKey		     = document.getElementById('strPKey'+index).value;
    
	var department       = document.forms[0].strDeptId[document.forms[0].strDeptId.selectedIndex].text;
    var storeName        = document.forms[0].strStoreId[document.forms[0].strStoreId.selectedIndex].text;
    var enggItemTyp      = document.forms[0].strEnggItemTypeId[document.forms[0].strEnggItemTypeId.selectedIndex].text;
    var enggItemSubTyp   = document.forms[0].strEnggItemSubTypeId[document.forms[0].strEnggItemSubTypeId.selectedIndex].text;
    var iteBrandId       = document.forms[0].strItemBrandId[document.forms[0].strItemBrandId.selectedIndex].text;
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
    	//                  0            1               2                3                4              5            6             7                   8                9                   10            11               12           13                 14         15          16    
    	var infoPass =  department+"@"+storeName+"@"+enggItemTyp+"@"+enggItemSubTyp+"@"+iteBrandId+"@"+ItemSlNo+"@"+ManufactNo+"@"+stockInfo+"@"+mantContSupplier+"@"+mantContType+"@"+mantStartDate+"@"+mantEndDate+"@"+mantCost+"@"+strReaminInfo+"@"+termsCond+"@"+penelty+"@"+pKey;
    	getRenew(infoPass,gen);
    }  
    if(gen=='2')
    {
    	//                  0             1             2                3                  4             5            6              7                 8                 9                   10             11             12           13         
    	var infoPass =  department+"@"+storeName+"@"+enggItemTyp+"@"+enggItemSubTyp+"@"+iteBrandId+"@"+ItemSlNo+"@"+ManufactNo+"@"+stockInfo+"@"+mantContSupplier+"@"+mantContType+"@"+mantStartDate+"@"+mantEndDate+"@"+mantCost+"@"+strReaminInfo;
    	getCancel(infoPass,gen);
    }   
    if(gen=='3')
    {
    	//                  0              1             2               3                  4             5             6             7                 8                  9                10               11              12           13                      
    	var infoPass =  department+"@"+storeName+"@"+enggItemTyp+"@"+enggItemSubTyp+"@"+iteBrandId+"@"+ItemSlNo+"@"+ManufactNo+"@"+stockInfo+"@"+mantContSupplier+"@"+mantContType+"@"+mantStartDate+"@"+mantEndDate+"@"+mantCost+"@"+strReaminInfo;
    	getView(infoPass,gen);
    }    
   	  
		
}
function getRenew(infoPass,gen)
{	
	 document.forms[0].strIsReNew.value='1';
	 var tmp         = infoPass.split("@");
	 
	 var itemParaObj = document.getElementById("reNewTagId");
         itemParaObj.innerHTML ="<table class='TABLE_STYLE'><tr class='FOOTER_TR'><th colspan='8' align='left'><div id='Id1' style='color:blue;'>Previous Maintenance [ Renew :::: "+tmp[9]+"/"+tmp[12]+" ]</div></th></tr></table>";
     
     
     document.forms[0].strIsReNewPK.value = tmp[16];
     document.getElementById("maintenanceContractSupplierId").style.display="none";
     document.getElementById("renewMaintenanceContractSupplierId").style.display="block";
     
     document.getElementById("renewMaintenanceContractSupplierId").innerHTML= tmp[8];
     document.getElementById("mantContName").innerHTML= tmp[9];
    
     var arrTmp = tmp[13].split("^");     
    
     document.getElementById("tenderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strTenderNo' maxlength='25' value="+arrTmp[6]+" onkeypress='return validateData(event,8);'>";
     
     document.getElementById("orderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strOrderNo' maxlength='25' value="+arrTmp[11]+" 	onkeypress='return validateData(event,8);'>";
     
     document.forms[0].strOrderDate.value=arrTmp[12];
     document.forms[0].strTenderDate.value=arrTmp[9];
     
     document.forms[0].strFromDate.value=tmp[10];
     document.forms[0].strToDate.value=tmp[11];
     
     //document.forms[0].strDocRefDate.value=arrTmp[10];
     
     
     //document.getElementById("upLoadDiv").innerHTML="<input type='text' class='TEXT_FIELD_MAX' name='strDocRefNo' value='"+arrTmp[8]+"' onkeypress='return validateData(event,9);' maxlength='35'>";
     document.getElementById("routineFreqDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strRoutineFrequency' maxlength='2' value="+arrTmp[3]+" 	onkeypress='return validateData(event,5);'>";
     document.getElementById("responseTimeDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strResponseTime' maxlength='1' value="+arrTmp[2]+" 	onkeypress='return validateData(event,5);'>";
     document.getElementById("mantCostDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strMaintenanceCost' maxlength='10' value="+tmp[12]+" 	onkeypress=\"return (numericWithTwoDecimalPlaces(this,',','.',event));\" >";
     document.getElementById("remarksDiv").innerHTML="<textarea name='strRemarks'	cols='25' rows='2' onkeypress='return validateData(event,9);' >"+arrTmp[13]+"</textarea>";
     document.getElementById("termsCondDiv").innerHTML="<textarea name='strTermsAndCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'>"+tmp[14]+"</textarea>";
     document.getElementById("penaltyDiv").innerHTML="<textarea name='strPeneltyCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'>"+tmp[15]+"</textarea>";
     
     var url="ItemMaintContractDtlsACTION.cnt?hmode=GETRENEWPAGE&info="+tmp[13]+"&mode="+gen;
     
     ajaxFunction(url,"13");
     
     /*	 
	  Value in strReaminInfo Concated by ^ Symbol
	       0. HEMNUM_ROUTINE_VISIT
		   1. HEMNUM_BREAK_VISIT
           2. HEMSTR_RESPONSE_TIME 
           3. HEMNUM_ROUTINE_FREQ
           4. Frequency Unit Name  
           5. Response Time Unit Name 
           6. HEMSTR_TENDER_NO 
           7. HPURNUM_UPLOAD_NO 
           8. HPURSTR_DOC_REF_NO  
           9. HEMDT_TENDER_DATE,
           10. HPURDT_DOC_REF_DATE  
           11. HEMSTR_ORDER_NO  
           12. HEMDT_ORDER_DATE 
           13. GSTR_REMARKS 
           14. HEMNUM_IS_RENEWED
           15. HEMSTR_FREQ_UNIT 
           16. HEMSTR_RES_TIME_UNIT
           17. SL NO
	 */
	
 
}
function clearDiv()
{ 
	
	  var itemParaObj = document.getElementById("reNewTagId");
      itemParaObj.innerHTML ="";
      document.forms[0].strIsReNew.value='0';
      document.forms[0].strIsReNewPK.value = '0';
	
      document.getElementById("maintenanceContractSupplierId").style.display="block";
      document.getElementById("renewMaintenanceContractSupplierId").style.display="none";
     
      document.getElementById("renewMaintenanceContractSupplierId").innerHTML= "";
      document.getElementById("mantContName").innerHTML="<input type='text' class='TEXT_FIELD_MAX' name='strMaintenanceContractName' maxlength='250' value=''  onkeypress='return validateData(event,9);'>";
      
      document.getElementById("tenderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strTenderNo' maxlength='25' value='' onkeypress='return validateData(event,8);'>";
     
      document.getElementById("orderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strOrderNo' maxlength='25' value='' onkeypress='return validateData(event,8);'>";
     
      document.forms[0].strOrderDate.value="";
      document.forms[0].strTenderDate.value="";
     
      document.forms[0].strFromDate.value="";
      document.forms[0].strToDate.value="";
      document.getElementById("routineFreqDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strRoutineFrequency' maxlength='2' value='' 	onkeypress='return validateData(event,5);'>";
      document.getElementById("responseTimeDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strResponseTime' maxlength='1' value='' 	onkeypress='return validateData(event,5);'>";
      document.getElementById("mantCostDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strMaintenanceCost' maxlength='15' value='' 	onkeypress=\"return (numericWithTwoDecimalPlaces(this,',','.',event));\" >";
      document.getElementById("remarksDiv").innerHTML="<textarea name='strRemarks'	cols='25' rows='2' ></textarea>";
      document.getElementById("termsCondDiv").innerHTML="<textarea name='strTermsAndCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'></textarea>";
      document.getElementById("penaltyDiv").innerHTML="<textarea name='strPeneltyCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'></textarea>";
	

}
 
function getCancel(infoPass,gen)
{
	var enggItemTyp    = document.forms[0].strEnggItemTypeId[document.forms[0].strEnggItemTypeId.selectedIndex].text;
    var enggItemSubTyp = document.forms[0].strEnggItemSubTypeId[document.forms[0].strEnggItemSubTypeId.selectedIndex].text;
	document.forms[0].strIsReNew.value='0';
    clearDiv();
    
    if(enggItemTyp!='Select Value')
    {      
    	if(enggItemSubTyp!='Select Value')
    	{     
	      var url="ItemMaintContractDtlsACTION.cnt?hmode=MANTCANCEL&info="+infoPass+"&mode="+gen;
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
	var enggItemTyp    = document.forms[0].strEnggItemTypeId[document.forms[0].strEnggItemTypeId.selectedIndex].text;
    var enggItemSubTyp = document.forms[0].strEnggItemSubTypeId[document.forms[0].strEnggItemSubTypeId.selectedIndex].text;
	 clearDiv();
	  
	if(enggItemTyp!='Select Value')
    {      
    	if(enggItemSubTyp!='Select Value')
    	{     
	       if(document.forms[0].strItemMaintenanceContractMode.checked)
	       		var url="ItemMaintContractDtlsACTION.cnt?hmode=MANTVIEW&info="+infoPass+"&mode="+gen+"&isItem=1";
	       else
	       		var url="ItemMaintContractDtlsACTION.cnt?hmode=MANTVIEW&info="+infoPass+"&mode="+gen+"&isItem=2";
	       ajaxFunction(url,"10");
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
 
function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
    var objVal1 = document.getElementById("prevMaintDtlId");
   objVal1.innerHTML ="";
   
   if(document.forms[0].strItemMaintenanceContractMode.checked)
   {
     var url="ItemMaintContractDtlsACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemBrandId.value+"&deptId="+document.forms[0].strDeptId.value+"&mode="+1;
   }
   else
   {
   	var url="ItemMaintContractDtlsACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemBrandId.value+"&deptId="+0+"&mode="+2;
   }
  
   ajaxFunction(url,"1");
 
} 


function getStoreName()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   var objVal1 = document.getElementById("prevMaintDtlId");
   objVal1.innerHTML ="";
   var itemParaObj = document.getElementById("maintenanceContractSupplierId");
   itemParaObj.innerHTML = "<select name = 'strMantContractSuppId' class='COMBO_NORMAL'><option value='0'>Select Value</option></select>";
   clearDiv();
   var url="ItemMaintContractDtlsACTION.cnt?hmode=GETSTORENAME&deptId="+document.forms[0].strDeptId.value;
   ajaxFunction(url,"2");
  
} 

function getItemName()
{	
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   var objVal1 = document.getElementById("prevMaintDtlId");
   objVal1.innerHTML ="";
   clearDiv();
  
    //alert("Store ID:::"+document.forms[0].strStoreId.value+":::Dept ID:::"+document.forms[0].strDeptId.value);	
   if(document.forms[0].strStoreId.value!='0')	   
   {
      var url="ItemMaintContractDtlsACTION.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
   }
   else
   {
   	  var url="ItemMaintContractDtlsACTION.cnt?hmode=GETITEMNAME&flag="+1+"&deptId="+document.forms[0].strDeptId.value;
   }   
   ajaxFunction(url,"3");
   
  
} 

function getEnggItemSubType()
{	   
   
   var url="ItemMaintContractDtlsACTION.cnt?hmode=GETENGGITEMSUBTYPE&deptId="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"4");
   
  
} 

function getContractSupplier()
{	   
   var url="ItemMaintContractDtlsACTION.cnt?hmode=GETSUPPLIER&itemId="+document.forms[0].strItemBrandId.value+"^"+document.forms[0].strCheck.value;
   ajaxFunction(url,"5");
     
} 


function getEnggItemSubTypeCombo()
{
	
	 if(document.forms[0].strNonItemMaintenanceContractMode.checked)
     {
	    var objVal1 = document.getElementById("prevMaintDtlId");
        objVal1.innerHTML ="";
        clearDiv();  
     }
        
   var url="ItemMaintContractDtlsACTION.cnt?hmode=GETENGGITEMSUBTYPE&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"6");
}

function getNonItemPrevMantDtl()
{       
                      
      //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID||'^'||HSTSTR_BATCH_NO||'^'||
      // HSTSTR_ITEM_SL_NO||'^'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
                
      var tmpStockInfo = 0+"^"+0+"^"+document.forms[0].strItemBrandId.value+"^"+0+"^"+0+"^"+108+"^"+0+"^"+2;       
     document.forms[0].strStockInfoVal.value = tmpStockInfo;
    	 
     var url="ItemMaintContractDtlsACTION.cnt?hmode=GETPREVMANTDTL&strStockInf="+tmpStockInfo;
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
 	}
}
/*
 * This function is called in case of Non-Item 
 */
function getNonItem()
{
	 
	 if(document.forms[0].strCheck.value='2')
	 {
	 	  var objVal1 = document.getElementById("prevMaintDtlId");
          objVal1.innerHTML ="";
          clearDiv();     
	 	  var url="ItemMaintContractDtlsACTION.cnt?hmode=GETITEMNAME&flag="+2+"&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
          ajaxFunction(url,"11");
	 }  
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
		   
	       	           
	       	            var itemParaObj = document.getElementById("engineeringItemSubTypeId");
	       	            if(document.forms[0].strItemMaintenanceContractMode.checked)
	       	            {
	       	            	itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL'>" + res + "</select>";
	       	            }
	       	            else
	       	            {
	       	            	itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL' onChange='getNonItem();'>" + res + "</select>";
	       	            	var itemParaObj = document.getElementById("itemNameId");
	       	                itemParaObj.innerHTML ="<select name = 'strItemBrandId' onchange='getNonItemPrevMantDtl();' class='COMBO_NORMAL'><option value='0'>Select Value</option></select>";
	       	                var itemParaObj = document.getElementById("maintenanceContractSupplierId");
	       	                itemParaObj.innerHTML = "<select name = 'strMantContractSuppId' class='COMBO_NORMAL'><option value='0'>Select Value</option></select>";
	       	            }
	        
	       
	    }
	    
	     if(mode=="5")
		{	
		   
	       	            var itemParaObj = document.getElementById("maintenanceContractSupplierId");
	       	            itemParaObj.innerHTML = "<select name = 'strMantContractSuppId' class='COMBO_NORMAL'>" + res + "</select>";
	          	 
			      
	        
	       
	    }
	    
	    if(mode=="7")
		{	
	    		
	       	            var itemParaObj = document.getElementById("prevMaintDtlId");
	       	            itemParaObj.innerHTML = res;
	       	            
	       	            
	       	            if(!document.forms[0].strItemMaintenanceContractMode.checked)
	       	            {
	       	            	getContractSupplier();
	       	            }
	 		      
	        
	       
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
					    
					    if(document.forms[0].strIsCancel.value=='1')
	       	            {				    
							    
								if(res=='1')
								{			    
									
										if(document.forms[0].strCheck.value=='1')
										{		    
											      var   obj    = document.getElementsByName("checkid"); 
							                      var   obj2   = document.getElementsByName("strStockInf");
							                      var   obj3   = document.getElementsByName("varIndex");
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
											
										  document.forms[0].strIsCancel.value='0';  
										   getNonItemPrevMantDtl();
										}    
										   
						           } 	  
	       	            }
	       	                    
			           
	       
	       
	    }
	    /* This Mode is used to get Pre-Selected combo for Renew page */
	    if(mode=="13")
		{	
		  
	       	var  arr = res.split("$$");
	       	
	       	            document.getElementById("routineFreqUnitDiv").style.display="none";
                        document.getElementById("responseTimeUnitDiv").style.display="none";
 	       	
	       	            var itemParaObj = document.getElementById("cancelResponseTimeUnitDiv");
	       	            itemParaObj.innerHTML ="<select	name='strRenewRoutineUnitId' class='COMBO_NORMAL'>" + arr[0] + "</select>";
	       	            
	       	            var itemParaObj1 = document.getElementById("cancelRoutineFreqUnitDiv");
	       	            itemParaObj1.innerHTML ="<select	name='strRenewResponseTimeUnitId' class='COMBO_NORMAL'>" + arr[1] + "</select>";
	          	        
	          	        document.getElementById("cancelResponseTimeUnitDiv").style.display="block";
                        document.getElementById("cancelRoutineFreqUnitDiv").style.display="block";  
	          	        
			           
	        
	       
	    }
   
} 

/**
 * This Function Call When Radio button Click In Stock Details HLP
 */
function radioBtnClick(obj,index)
{		
	var passVal;
	clearRenew();  // Clear Renew
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
	 var url="ItemMaintContractDtlsACTION.cnt?hmode=GETPREVMANTDTL&strStockInf="+passVal;
     ajaxFunction(url,"7");
	
	
	
}

function clearRenew()
{
	
	var itemParaObj = document.getElementById("reNewTagId");
         itemParaObj.innerHTML ="";
     
     document.getElementById("maintenanceContractSupplierId").style.display="block";
     document.getElementById("renewMaintenanceContractSupplierId").style.display="none";
     document.getElementById("mantContName").innerHTML= "<input type='text' class='TEXT_FIELD_MAX' name='strMaintenanceContractName' maxlength='15' value=''>";

     document.getElementById("tenderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strTenderNo' maxlength='15' value='' onkeypress='return validateDataWithSpecialChars(event,9 ,'\')' >";
     
     document.getElementById("orderNoDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strOrderNo' maxlength='15' value=''  onkeypress='return validateDataWithSpecialChars(event,9 ,'\')'	>";
     
     document.forms[0].strOrderDate.value="";
     document.forms[0].strTenderDate.value="";
     
     document.forms[0].strFromDate.value="";
     document.forms[0].strToDate.value="";
     
     document.forms[0].strDocRefDate.value="";
     
    
      document.getElementById("routineFreqDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strRoutineFrequency' maxlength='2' value='' 	onkeypress='return validateData(event,5);'>";
      document.getElementById("responseTimeDiv").innerHTML="<input type='text'	class='TEXT_FIELD_SMALL' name='strResponseTime' maxlength='1' value='' 	onkeypress='return validateData(event,5);'>";
      document.getElementById("mantCostDiv").innerHTML="<input type='text'	class='TEXT_FIELD_MAX' name='strMaintenanceCost' maxlength='10' value='' 	onkeypress=\"return (numericWithTwoDecimalPlaces(this,',','.',event));\" >";
      document.getElementById("remarksDiv").innerHTML="<textarea name='strRemarks'	cols='25' rows='2' ></textarea>";
      document.getElementById("termsCondDiv").innerHTML="<textarea name='strTermsAndCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'></textarea>";
      document.getElementById("penaltyDiv").innerHTML="<textarea name='strPeneltyCond'	cols='25' rows='2' onkeypress='return validateData(event,9);'></textarea>";
}



/**
 * This Method is Used when Cancel Pop-Up 
 * 
 */
function SaveCancel()
{
	var hisValidator = new HISValidator("itemContractDetailsFB");
	hisValidator.addValidation("strCancelTypeId", "dontselect=0","Please Select a Cancel Type");
	hisValidator.addValidation("strCancelRemarks", "req","Cancel remarks is a Mandatory Field");
	var retVal = hisValidator.validate(); 
    hisValidator.clearAllValidations();
           if(retVal)
           {
                  var url="ItemMaintContractDtlsACTION.cnt?hmode=SAVECANCEL&cancelType="+document.forms[0].strCancelTypeId.value+"&cancelRemarks="+document.forms[0].strCancelRemarks.value+"&stockInfo="+document.forms[0].strCancelStockInfo.value+"&slNo="+document.forms[0].strSlNo.value;
                          	  
	           	  ajaxFunction(url,"12");
	           	  document.forms[0].strIsCancel.value='1';
	 	          hide_popup('popUpDiv');
           }
           else
           {
                       return false;
           }
	
	
}


// Function for Numeric(11,2)
function numericWithTwoDecimalPlaces(fld, milSep, decSep, e)
{
	var sep = 0;
	var key = '';
	var milSep="";
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;
	//if (whichCode == 13) return true;  // Enter
	if (whichCode == 0)	return true; //tab-index
	//alert(whichCode);
	if (whichCode == 8) return true;  // Back-Space 
	key = String.fromCharCode(whichCode);  // Get key value from key code
		if (strCheck.indexOf(key) == -1) return false;  // Not a valid key
		//len = fld.value.length;
		len=9;
		for(i = 0; i < len; i++)
		if ((fld.value.charAt(i) != decSep)) break;
		aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) fld.value = '';
	if (len == 1) fld.value = ''+ decSep + '' + aux;
	if (len == 2) fld.value = ''+ decSep + aux;
	if (len > 2) {
	aux2 = '';
	for (j = 0, i = len - 3; i >= 0; i--) {
	if (j == 3) {
	aux2 += milSep;
	j = 0;
	}
	aux2 += aux.charAt(i);
	j++;
	}
	fld.value = " ";
	len2 = aux2.length;
	for (i = len2 - 1; i >= 0; i--)
	fld.value += aux2.charAt(i);
	fld.value += decSep + aux.substr(len - 2, len);
	}
	return false;
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
