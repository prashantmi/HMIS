function getStoreName()
{

	var mode = "GETSTORENAME"
	var url = "OfflineSparePartAddTransACTION.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"2");} 

function getEnggItemSubTypeCombo()
{
        var mode = "ENGGITEMSUBTYPECMB";
        var url ="OfflineSparePartAddTransACTION.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
 		ajaxFunction(url,"1");		
}

function getItemCategoryName()
{
	var mode = "GETITEMCATEGORYNAME";
        var url ="OfflineSparePartAddTransACTION.cnt?hmode="+mode+"&storeId="+document.forms[0].strStoreId.value;
 		ajaxFunction(url,"3");	
}

function getItemName()
{	
	var mode = "GETITEMNAME";
   	  var url="OfflineSparePartAddTransACTION.cnt?hmode="+mode+"&itemCategoryId="+document.forms[0].strItemCategoryId.value;
      ajaxFunction(url,"4");
} 

function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
   var url="OfflineSparePartAddTransACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemId.value+"&deptId="+document.forms[0].strDeptCode.value;
   ajaxFunction(url,"6");
} 

function getSparePartName()
{
	var mode = "GETSPAREPARTNAME";
   	  var url="OfflineSparePartAddTransACTION.cnt?hmode="+mode+"&itemId="+document.forms[0].strItemId.value;
      ajaxFunction(url,"5");
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
				
				objVal.innerHTML = "<select name ='strEngineeringItemSubTypeId' class='COMBO_NORMAL' tabindex='1' >"+res+"</select>";
			}
		}	
		
		
	if(mode=="2")
	   { 
     
		  if(res=="")
			{
				document.getElementById("storeNameId").innerHTML="<select name ='strStoreId' class='COMBO_NORMAL' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("storeNameId");
	       	            itemParaObj.innerHTML ="<select name = 'strStoreId' onchange='getItemCategoryName();' class='COMBO_NORMAL'>" + res + "</select>";
	        }       
		}		
		
		
		if(mode=="3")
	    { 
     
		  if(res=="")
			{
				document.getElementById("itemCategoryNameId").innerHTML="<select name ='strItemCategoryId' class='COMBO_NORMAL' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("itemCategoryNameId");
	       	            itemParaObj.innerHTML ="<select name = 'strItemCategoryId' onchange='getItemName();' class='COMBO_NORMAL'>" + res + "</select>";
	        }       
	        			var objVal1 =  document.getElementById("stockValueId");
						 var objVal2 =  document.getElementById("sparePartStockDetailsId");
		 
  		
				  		  objVal1.innerHTML ="";
				  		  objVal2.innerHTML ="";
				  		  
				  		  
							doEmptyCombo(document.getElementsByName("strItemId")[0]);
							doEmptyCombo(document.getElementsByName("strSparePartId")[0]);
	
		
		}		
		
		
		if(mode=="4")
	    { 
     
		  if(res=="")
			{
				document.getElementById("itemNameId").innerHTML="<select name ='strItemId' class='COMBO_NORMAL' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("itemNameId");
	       	            itemParaObj.innerHTML ="<select name = 'strItemId' onchange='getSparePartName();' class='COMBO_NORMAL'>" + res + "</select>";
	        }       
	        			 var objVal1 =  document.getElementById("stockValueId");
						 var objVal2 =  document.getElementById("sparePartStockDetailsId");
		 
  		
				  		  objVal1.innerHTML ="";
				  		  objVal2.innerHTML ="";
		}	
		
		
		if(mode=="5")
	    { 
		  if(res=="")
			{
				document.getElementById("sparePartId").innerHTML="<select name ='strSparePartId' class='COMBO_NORMAL' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("sparePartId");
	       	            itemParaObj.innerHTML ="<select name = 'strSparePartId' class='COMBO_NORMAL'>" + res + "</select>";
	        }  
	        getStockDtl();     
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
	       	            var itemParaObj = document.getElementById("stockValueId");
	       	            	itemParaObj.innerHTML = res;
	       }
	       
	       var obj1 = document.getElementById("sparePartStockDetailsId"); 
	       	   obj1.innerHTML="";      
        }
    
    
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
	       	            var itemParaObj = document.getElementById("sparePartStockDetailsId");
	       	            	itemParaObj.innerHTML = res;
	       	            	
	       }   
	    }   
	       // tO DELETE the Spare-Part Stock Details
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
		       	            var itemParaObj = document.getElementById("sparePartStockDetailsId");
		       	            	itemParaObj.innerHTML = res;
		       	            	
		       }       
		    }  
}



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
	
	
	 var url="OfflineSparePartAddTransACTION.cnt?hmode=SPAREPARTSTOCKDETAILS&strStockInf="+passVal;
     ajaxFunction(url,"7");
	
	/*HEMNUM_ITEM_ID, HEMSTR_BATCH_NO, HEMNUM_ITEM_SL_NO, HEMNUM_SL_NO, GNUM_HOSPITAL_CODE)*/
	
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

function validate1()
{   
	  var hisValidator = new HISValidator("offlineSparePartAddTransFB");
	  
	     hisValidator.addValidation("strDeptCode","dontselect=0","Please Select Department Name");
         hisValidator.addValidation("strStoreId","dontselect=0","Please Select Store Name");
	  
	  
	     hisValidator.addValidation("strEngineeringItemTypeId","dontselect=0","Please Select Engineering Item Type");
	     hisValidator.addValidation("strEngineeringItemSubTypeId","dontselect=0","Please Select Engineering Item Sub Type");
	  
	  
	 	 hisValidator.addValidation("strItemCategoryId","dontselect=0","Please Select Item Category");
	     hisValidator.addValidation("strItemId","dontselect=0","Please Select Item Name");
	  
	     hisValidator.addValidation("strSparePartId","dontselect=0","Please Select Spare-part Name");
	     hisValidator.addValidation("strSparePartSerialNo","req", "Spare Part Serial No is a Mandatory Field" );
	     
         hisValidator.addValidation("strManufacturerId","dontselect=0","Please Select Manufacturer Name");
	  	 hisValidator.addValidation("strManufacturerSerialNo","req", "Manufacturer Serial No is a Mandatory Field" );
	  	 
	  	 
		 hisValidator.addValidation("strWarrantyFromDate", "date","Warranty From Date is a Mandatory Field");
	  
	  	 hisValidator.addValidation("strWarrantyUpto","req", "Warranty Upto is a Mandatory Field" );
	  	 hisValidator.addValidation("strUnitId","dontselect=0","Please Select Unit");
	  
	  	 hisValidator.addValidation("strSpecification","req", "Specification is a Mandatory Field" );
	     hisValidator.addValidation("strSpecification","maxlen=4000", "Specification should have less than or equal to 4000 Characters" );
	  
	     hisValidator.addValidation("strPerformedDate", "date","Performed Date is a Mandatory Field");
	  
	  
	  
	  	 var retVal = hisValidator.validate();
	  	 
	  	   if(retVal)
          {
          			if(checkIfStockDetailsExist())
          			{
          				document.forms[0].hmode.value = "SAVE";
	           	   		document.forms[0].submit();	
          			}
	         	   else
			          {
			             return false;
			          }
          }
          else
          {
             return false;
          }
}


// Clear Page
function clearPage()
{
	document.forms[0].reset();
	document.forms[0].hmode.value="unspecified";
	document.forms[0].submit();
}

function cancelThePage()
{
			document.forms[0].hmode.value="CANCEL";
          	document.forms[0].submit();
}


function deleteSparePartStockDetailRecord(passVal,index)
{
	
	var flag=confirm("Are you sure you want to delete this record ?");
	
	if(flag==false)
	return false;
	
	 var mode = "DELETESPAREPARTSTOCKDTL";
        var url ="OfflineSparePartAddTransACTION.cnt?hmode="+mode+"&strStockInf="+passVal+"&spareId="+document.getElementsByName("strSpareId")[index].value+"&spareSerialNo="+document.getElementsByName("strSparePartNo")[index].value+"&ManufacturerNo="+document.getElementsByName("strManufacturerNo")[index].value;
 		ajaxFunction(url,"8");		
}







function emptyDetails()
{
	doEmptyCombo(document.getElementsByName("strItemCategoryId")[0]);
	doEmptyCombo(document.getElementsByName("strItemId")[0]);
	
		 var objVal1 =  document.getElementById("stockValueId");
		 var objVal2 =  document.getElementById("sparePartStockDetailsId");
		 
  		
  		  objVal1.innerHTML ="";
  		   objVal2.innerHTML ="";
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


function checkIfStockDetailsExist()
{
		//var sparePartDtl = document.forms[0].checkid;
	  	var sparePartDtl = document.getElementsByName("checkid");
	  	if(sparePartDtl)
	  	{
	  		var count=0;
	  		for(i=0;i<sparePartDtl.length;i++)
	  		{
	  			if(sparePartDtl[i].checked==true)
	  			{
	  				count++;
	  			}
	  		}
	  		if(count>0)
	  		{
	  			return true;
	  		}
	  		else
	  		{
	  			alert("Select Atleast one of the Stock Details for which the Spare Part Details Are to be Saved");
	  			return false;
	  		}
	  	}
	  	else
	  	{
	  		alert("No Stock Details exist for which Spare Part Details Are to be Added");
	  		return false;
	  	}
	  	
}	