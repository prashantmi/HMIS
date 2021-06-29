/**
 * Developer : Amit Kumar 
 * Version : 1.0 
 * Date : 11/Jan/2011
 * 
 */
 
function onLoadFunction()
{
	document.getElementsByName("strIsInternal")[0].checked=true;
}
 
 function ClearPage()
 {
   document.getElementById("errMsg").innerHTML = "";	
    var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   	var objVal= document.getElementById("LeftItemIds");
	    objVal.innerHTML = "<select name='strLeftItemIds' size='8' 	multiple style='width: 280px' ></select>";
	var objVal= document.getElementById("RightItemIds");
	    objVal.innerHTML = "<select name='strRightItemIds' size='8' 	multiple style='width: 280px' ></select>";
   document.forms[0].reset();
 }  
 
 
 
 function changeViewMode(chkObj)
{ 	
	
    if(chkObj.checked && chkObj.value=='0')
 	{ 	
 		 document.forms[0].strComplaintFlg.value = '1'; 
 		 document.forms[0].strIsInternal[1].checked=false;      
 	}
 	if(chkObj.checked && chkObj.value=='1')
 	{ 		 
 		 document.forms[0].strComplaintFlg.value = '2'; 
 		 document.forms[0].strIsInternal[0].checked=false; 
    }
}

 /**
  * This function is working as Global function to 
  * Hide or Show the Stock Details
  */
 function showOrHideStockDetails(thisImg)
 {
 	//alert(thisImg.title);
 	if (thisImg == null) 
 	{
		alert("Cannot find this image object.");
	}
	if (thisImg.title == "Show") 
	{
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/minus.gif";
		thisImg.title = "Hide";
		document.getElementById("prevLnkDivId").style.display='none';
						
	}
	else
	{  
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";
		document.getElementById("prevLnkDivId").style.display='block';
	}	
 } 
 function ClearModifyPage()
 {
 	
   document.getElementById("errMsg").innerHTML = "";
   document.forms[0].strMaintenanceDesc.value="";	
   
   document.forms[0].strFromHour.value="00";	
   document.forms[0].strFromMin.value="00";	
   document.forms[0].strToHour.value="00";	
   document.forms[0].strToMin.value="00";	
   document.forms[0].strMaintenancePeriod.value="0";	
   document.forms[0].strUnitId.value="0";
   document.forms[0].strMaintenancePeriodInDays.value="0";
   document.forms[0].strRemarks.value="";
     
 }  
 
 function validateUpdate()
 {
 	var hisValidator = new HISValidator("itemMaintenanceMstBean");
 	 hisValidator.addValidation("strEffectiveFrom","req","Effective From Date is a mandatory field!!!");
 	 hisValidator.addValidation("strMaintenanceDesc","maxlen=1000","Maintenance Description should not contain greater than 1000 characters!!!" );
     
     if(document.forms[0].strFromHour.value=="00" || document.forms[0].strFromHour.value=="0")
     {
	 	alert("Please Enter Prefered Time From");
	 	document.forms[0].strFromHour.focus();
	 	return false;
     }
     else
     {
     	hisValidator.addValidation("strFromHour", "req", "From  Hour is a Mandatory Field!!!" );
  	 	hisValidator.addValidation("strFromHour", "minlen=2", "From  Hour must be in a Valid Hour Format 00" );
     	
     }
  	 hisValidator.addValidation("strFromMin", "req", "From Minute is a Mandatory Field!!!" );
  	 hisValidator.addValidation("strFromMin", "minlen=2", "From Minute must be in a Valid Hour Format 00" );
  	 
  	  if(document.forms[0].strToHour.value=="00" ||document.forms[0].strToHour.value=="0" )
     {
	 	alert("Please Enter Prefered Time To");
	 	document.forms[0].strToHour.focus();
	 	return false;
     }
     else
     {
     	hisValidator.addValidation("strToHour", "req", "From  Hour is a Mandatory Field!!!" );
  	 	hisValidator.addValidation("strToHour", "minlen=2", "From  Hour must be in a Valid Hour Format 00" );
     	
     }  	   	
  	 hisValidator.addValidation("strToMin", "req", "To Minute is a Mandatory Field!!!" );
  	 hisValidator.addValidation("strToMin", "minlen=2", "To Minute must be in a Valid Hour Format 00" );
  	 
  	 hisValidator.addValidation("strMaintenancePeriod","req","Maintenance Period is a mandatory field!!!");
  	 
  	 hisValidator.addValidation("strMaintenancePeriodInDays","req","Duration of alert before the Scheduled Days is a mandatory field!!!");
  	 hisValidator.addValidation("strUnitId","dontselect=0","Please Select a Unit Name!!!");
  	 
  	 hisValidator.addValidation("strEffectiveFrom","req","Effective From Date is a mandatory field!!!");
  	 hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not contain greater than 100 characters!!!" );
  	 
 	var retVal = hisValidator.validate(); 
    hisValidator.clearAllValidations(); 
 	if(retVal)
	{
		var count = selectListRecords("strRightItemIds");
		if (count == 0) 
		{
			alert("Please populate at least one Task Name from Available Task in Selected Task.");
			return false;
		}
	
		document.forms[0].hmode.value = "UPDATE";

		document.forms[0].submit();
		
	}		
 	
 } 
 function validate1()
 {
 	 var hisValidator = new HISValidator("itemMaintenanceMstBean");
 	
 	         if(document.forms[0].strIsInternal[0].checked)
			 {
			 	document.forms[0].strComplaintFlg.value = '1';
			 }
			 else
			 {
			 	if(document.forms[0].strIsInternal[1].checked)
			    {
			    	document.forms[0].strComplaintFlg.value= '2';
			    }
			    else
			    {
			    	alert("Please select Complaint type!!");
			    	return false;
			    }
			 	
			 }		 	
	
	 	 hisValidator.addValidation("strItemCatgId","dontselect=0","Please Select Item Category!!!");
		 hisValidator.addValidation("strEnggItemTypeId","dontselect=0","Please Select Engg Item Type!!!");
		 hisValidator.addValidation("strEnggItemSubTypeId","dontselect=0","Please Select Engg Item Sub-Type!!!");
		 hisValidator.addValidation("strItemid","dontselect=0","Please Select a Item  Name!!!");
		 hisValidator.addValidation("strMaintenanceId","dontselect=0","Please Select a Maintenance Name!!!");
		 hisValidator.addValidation("strMaintenanceDesc","req","Maintenance Description is Mandatory Field!!!" );
		 hisValidator.addValidation("strMaintenanceDesc","maxlen=1000","Maintenance Description should not contain greater than 1000 characters!!!" );
	   
	
         
     if(document.forms[0].strFromHour.value=="00" || document.forms[0].strFromHour.value=="0")
     {
	 	alert("Please Enter Prefered Time From");
	 	return false;
     }
     else
     {
     	hisValidator.addValidation("strFromHour", "req", "From  Hour is a Mandatory Field!!!" );
  	 	hisValidator.addValidation("strFromHour", "minlen=2", "From  Hour must be in a Valid Hour Format 00" );
     	
     }
	  	 hisValidator.addValidation("strFromMin", "req", "From Minute is a Mandatory Field!!!" );
	  	 hisValidator.addValidation("strFromMin", "minlen=2", "From Minute must be in a Valid Hour Format 00" );
  	 
  	  if(document.forms[0].strToHour.value=="00" ||document.forms[0].strToHour.value=="0" )
     {
	 	alert("Please Enter Prefered Time To");
	 	return false;
     }
     else
     {
     	hisValidator.addValidation("strToHour", "req", "From  Hour is a Mandatory Field!!!" );
  	 	hisValidator.addValidation("strToHour", "minlen=2", "From  Hour must be in a Valid Hour Format 00" );
     	
     }  	   	
	  	 hisValidator.addValidation("strToMin", "req", "To Minute is a Mandatory Field!!!" );
	  	 hisValidator.addValidation("strToMin", "minlen=2", "To Minute must be in a Valid Hour Format 00" );
	  	 
	  	 hisValidator.addValidation("strMaintenancePeriod","req","Maintenance Period is a mandatory field!!!");
	  	 
	  	 hisValidator.addValidation("strMaintenancePeriodInDays","req","Duration of alert before the Scheduled Days is a mandatory field!!!");
	  	 hisValidator.addValidation("strUnitId","dontselect=0","Please Select a Unit Name!!!");
	  	 
	  	 hisValidator.addValidation("strEffectiveFrom","req","Effective From Date is a mandatory field!!!");
	  	 hisValidator.addValidation("strRemarks","maxlen=100","Remarks should not contain greater than 100 characters!!!" );
  	 
	document.forms[0].strStoreIdValue.value= document.forms[0].strStoreId.value;
	var retVal = hisValidator.validate();
      		hisValidator.clearAllValidations();
	
	
	if(retVal)
	{
		var count = selectListRecords("strRightItemIds");
		if (count == 0) {
			alert("Please populate at least one Task Name from Available Task in Selected Task.");
			return false;
		}
	  
		document.forms[0].hmode.value = "SAVE";
    	document.forms[0].submit();
		
	}		
	
}	
 
 function  lessThen60(_these)
 { 	
		if(parseInt(_these.value)>=60)
		{
			_these.value = "00";
		} 
		 else 
		   if(parseInt(_these.value)>5 && parseInt(_these.value)<=9)
		   {
			 _these.value = "";
		   }
	}
	
	function  lessThen24(_these){
		if(parseInt(_these.value)>=24)
		{
			_these.value = "00";
		} 
		 else 
		   if(parseInt(_these.value)>2 && parseInt(_these.value)<=3)
		   {
			 _these.value = "";
		   }
	}
	
	function twoDig(_these)
	{
		if(_these.value.length==1)
		{
			_these.value = "0"+_these.value;
		} 
		else 
		if(_these.value.length==0)
		
			_these.value = "00";
	}
function LeftListTransfer()
{
	var ob1=document.forms[0].strLeftItemIds.value;
	var ob=document.getElementById("LeftItemIds");
	shiftToRight("strLeftItemIds","strRightItemIds",1);
} 
function getItemNameCombo()
{	
   
   var url="ItemMaintenanceMstCNT.cnt?hmode=GETITEMNAME&itemCatNo="+document.forms[0].strItemCatgId.value+"&storeId="+document.forms[0].strStoreId.value;;
   ajaxFunction(url,"1");
} 
function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   var url="ItemMaintenanceMstCNT.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemid.value+"&itemCatNo="+document.forms[0].strItemCatgId.value+"&deptId="+document.forms[0].strStoreId.value;
   ajaxFunction(url,"2");
   
  
} 
function getMaintenanceNameCmb(obj)
{	
   //alert("stockInfo:::"+document.forms[0].strStockInfoVal.value+":::Item Id:::"+document.forms[0].strItemid[document.forms[0].strItemid.selectedIndex].value+":::Dept ID:::"+document.forms[0].strStoreId.value);
   var url="ItemMaintenanceMstCNT.cnt?hmode=MAINTENANCECMB&stockInfo="+obj+"&itemId="+document.forms[0].strItemid[document.forms[0].strItemid.selectedIndex].value+"&deptId="+document.forms[0].strStoreId.value;
  // alert(url);
   ajaxFunction(url,"3");
} 

function getLeftItemList()
{ 	
	 var url="ItemMaintenanceMstCNT.cnt?hmode=LEFTTASKLIST&mainTenanceId="+document.forms[0].strMaintenanceId.value+"&stockInfo="+document.forms[0].strStockInfoVal.value+"&itemId="+document.forms[0].strItemid[document.forms[0].strItemid.selectedIndex].value+"&deptId="+document.forms[0].strStoreId.value;      
	 ajaxFunction(url,"4");
}
function getEnggItemSubTypeCombo()
{	
   
   var url="ItemMaintenanceMstCNT.cnt?hmode=GETENGGITEMSUBTYPE&enggItemType="+document.forms[0].strEnggItemTypeId.value;
   ajaxFunction(url,"5");
}


function hideStockPopup()
{
    document.forms[0].strItemid.value="0";
	hide_popup('popUpDiv');

}
function getWarrantyMaintenanceDtl(batchNo,itemSlNo,itemId,itemBrandId)
{ 
   var url="ItemMaintenanceMstCNT.cnt?hmode=GETWARRNTYMAINTENANCE&batchNo="+batchNo+"&itemSlNo="+itemSlNo+"&itemId="+itemId+"&itemBrandId="+itemBrandId;
   ajaxFunction(url,"6");
} 
function radioBtnClick(obj,index)
{
	var val        = document.getElementById('stockInf'+index).value;
	
	
	document.forms[0].strStockInfoVal.value = val;
	
	/*
	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
       GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
	 */
	var tmp        = val.split("^");
	var StoreName  = document.getElementById('StoreName'+index).value;
	var ItemSlNo   = document.getElementById('ItemSlNo'+index).value;
	var BatchNo    = document.getElementById('BatchNo'+index).value;
	var ManufactNo = document.getElementById('ManufactNo'+index).value;
	
	getWarrantyMaintenanceDtl(BatchNo,ItemSlNo,tmp[1],tmp[2]);
	
	var objVal1 = document.getElementById("stockValueId");
	
	objVal1.innerHTML = "<table class='TABLEWIDTH' align='center' border='0' bgcolor='#CC9966' cellspacing='1px'  cellpadding='1px'>" +
			"<input type='hidden' name='stockInf'  value='"+val+"'><tr><td colspan='1' class='multiLabel'>Store</td><td colspan='1' class='multiLabel'>ItemSl.No.</td>" +
			"<td colspan='1' class='multiLabel'>Batch No</td><td colspan='1' class='multiLabel'>Manufact No.</td>" +
			"</tr><tr><td colspan='1' class='multiControl'>"+StoreName+"</td><td colspan='1' class='multiControl'>"+tmp[4]+"</td>" +
			"<td colspan='1' class='multiControl'>"+tmp[3]+"</td><td colspan='1' class='multiControl'>"+ManufactNo+"</td></tr></table>";
	
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


function getAjaxResponse(res,mode)
{
	var objVal;
	if(mode=="1")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }else
        {
       	  objVal= document.getElementById("itemNameId");
		  objVal.innerHTML = "<select name='strItemid' class='comboNormal' onchange='getStockDtl();'>" + res + "</select>";
		  
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
    	   //alert("stockInfo:::"+res);//stockInf10
    	   
    	   var obj=res;
    	   
    	   var itemParaObj = document.getElementById("stockDtlsDivId");
       	           
          	  itemParaObj.innerHTML = res;
          	  //ajaxCallGetData('strDisplayMode', 'strProcessMode', document.forms[0].strItemid.value  );
		      popup('popUpDiv', '', '');
		      
		     // getMaintenanceNameCmb(obj);
		      
        }
       
    }
    
    if(mode=="3")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }else
        {
       	  objVal= document.getElementById("maintenanceId");
		  objVal.innerHTML = "<select name='strMaintenanceId' class='comboNormal' onchange='getLeftItemList();' >" + res + "</select>";
		  
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
       	        
       	        var temp = res.split("@@");
       	       	objVal= document.getElementById("LeftItemIds");
				objVal.innerHTML = "<select name='strLeftItemIds' size='8' 	multiple style='width: 280px' >" + temp[0] + "</select>";
				removeOptions();
				objVal= document.getElementById("RightItemIds");
				objVal.innerHTML = "<select name='strRightItemIds' size='8' 	multiple style='width: 280px' >" + temp[1] + "</select>";
		  
        }
       
    }
    
     if(mode=="5")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }else
        {
       	  objVal= document.getElementById("engineeringItemSubTypeId");
		  objVal.innerHTML = "<select name='strEnggItemSubTypeId' class='comboNormal' >" + res + "</select>";
		  
        }
       
    }
    
    
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
       	       
       	        var temp = res.split("$$$$");
       	        var elementWarrantyDetailsBody = document.getElementById("warrantyDTL");
				var elementMaintenanceContractDetailsBody = document.getElementById("maintenanceDTL");
				
				if(temp[0]=="")
				{
					elementWarrantyDetailsBody.style.display="none";
				}
				else
				{
					elementWarrantyDetailsBody.style.display="block";
				}
				
				if(temp[1]=="")
				{
					elementMaintenanceContractDetailsBody.style.display="none";
				}
				else
				{
					elementMaintenanceContractDetailsBody.style.display="block";
				}
			    
			    
				
					elementWarrantyDetailsBody.innerHTML = temp[0];
				
					elementMaintenanceContractDetailsBody.innerHTML = temp[1];
				getMaintenanceNameCmb();				
				
		  
        }
       
    }
} 


function showOrHideWarrantyDtl(mode, thisImg) {
	if (thisImg == null) {
		alert("Cannot find this image object.");
	}
	if (mode == "SHOW") {
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/minus.gif";
		thisImg.title = "Hide";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick","showOrHideWarrantyDtl('HIDE',this);");

		// Change body display
		document.getElementById("id11").style.display="block";
		
	} else if (mode == "HIDE") {
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick","showOrHideWarrantyDtl('SHOW',this);");

		// Change body display
		document.getElementById("id11").style.display="none";
	} else {
		alert("showOrHideWarrantyDetails ::=> Unknown mode:" + mode);
	}
}
function showOrHideMaintenanceContractDtl(mode, thisImg) {
	if (thisImg == null) {
		alert("Cannot find this image object.");
	}
	if (mode == "SHOW") {
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/minus.gif";
		thisImg.title = "Hide";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick",
				"showOrHideMaintenanceContractDtl('HIDE',this);");

		// Change body display
		
		document.getElementById("id22").style.display="block";

	} else if (mode == "HIDE") {
		// Change Image Attribute
		thisImg.src = "/HBIMS/hisglobal/images/plus.gif";
		thisImg.title = "Show";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick","showOrHideMaintenanceContractDtl('SHOW',this);");

		// Change body display
		document.getElementById("id22").style.display="none";
	} else {
		alert("showOrHideMaintenanceContractDetails ::=> Unknown mode:" + mode);
	}
}


function removeOptions()
{
	
	var select_leftElement=document.getElementsByName('strLeftItemIds')[0];
	var select_rightElement=document.getElementsByName('strRightItemIds')[0];
	
	var array_option_leftElement=select_leftElement.options;
	var array_option_rightElement=select_rightElement.options;
	
	
	var array_option_removableOptions=new Array();
	var count=0;
	for(i=0;i<array_option_rightElement.length;++i) {
		
		
		for(j=0;j<array_option_leftElement.length;++j) {
			if(array_option_leftElement[j].value == array_option_rightElement[i].value) {
				
				array_option_removableOptions[count]=array_option_leftElement[j];
				count=count+1;
				break;
			}
			
		}
	}
	for(i=0;i<array_option_removableOptions.length;++i) {
		select_leftElement.remove(array_option_removableOptions[i].index);
	}
}

function buttonLogicsOnClick1(mode)
{
	       var Obj = document.getElementsByName("combo");
   	 	
 	       if(Obj[0].value =="0")
		   {
				alert("Please Select Department/Store Name");
				Obj[0].focus();
				return ;
			}
		    else
			{ 
   	 	   	  add(mode); 			
		   	}

}

function showViewItemMaintenance(form1) 
{

	var chkObj = document.getElementsByName("chk");
	
	var len = chkObj.length;

	var countChk = 0;

	for ( var i = 0; i < len; i++)
		if (chkObj[i].checked)
			countChk = countChk + 1;

	if (countChk != 1) 
	{
		alert("Please Select One Record!!!");
		return false;
	}
	

	for ( var i = 0; i < len; i++) 
	{

		if (chkObj[i].checked) 
		{
			url = 'ItemMaintenanceMstCNT.cnt?hmode=viewPage' + '&chk=' + chkObj[i].value+ '&comboName=' + document.forms[0].combo[0][document.forms[0].combo[0].selectedIndex].text;
			window.open(createFHashAjaxQuery(url), "popupWindow",
					"width=610,height=450,scrollbars=yes");
		}
	}

}

/**
 * Function for File Up-Loading
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
