function userDefinedOnLoadFunc()
{
	 
}
function onLoadFunc()
{
	if(document.forms[0].strConsumableFlag.value=="1")
	{
		document.getElementById("qtyRow").style.display = "";
		document.getElementById("statusTD").innerHTML = "";
		
	}
	else
	{
		document.getElementById("qtyRow").style.display = "none";
		document.getElementById("statusTD").innerHTML = "Status";
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
		 /*enableButton("Acknowledgment");
         enableButton("Schedule");
         enableButton("Attend");
         enableButton("Close");*/
         enableButton("View");
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
	//alert("Inside 3"+modeNo+"::"+mode+"::"+display);
	
	var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");
	var index;
	if(mode=="ADD")
	{
		add(mode);
	}
	else if(mode=="SHOWRPT")
	{
		if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Hospital Name");
				document.getElementById("comboid0").focus();
				return false;
			}
			else			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Lab Category");
				document.getElementById("comboid1").focus();
				return false;
			}
		add(mode);
	}
	else
	{
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
			index = parseInt(i) + 1; 
			break;
		}			
	}
	try
	{
		if(checkCount=='1')
		{
		/*if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Hospital Name");
				document.getElementById("comboid0").focus();
				disableButton("View");
				return false;
			}
			else			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Lab Category");
				document.getElementById("comboid1").focus();
				disableButton("View");
				return false;
			}*/
		var rowObj = document.getElementById("tr"+index);
		index = parseInt(index) + 4;
		hemmsSparePartMgmtDeskFB[index].value = hemmsSparePartMgmtDeskFB[index].value+"#"+rowObj.cells[4].innerHTML;
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
  }//end else part		
		
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function getGroupName()
{
	var mode = "GROUPNAME"
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&cmbValues="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"1");
}
function getItemName()
{	
	var mode = "GETITEMNAME";
	var val = document.forms[0].strDeptCode.value + "^" + document.forms[0].strGroupName.value + "^" + document.forms[0].strSubGroupName.value.split("^")[1] ;
   	var url="HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&cmbValues="+val;
    ajaxFunction(url,"2");
}
function getSparePartName()
{
	var mode = "GETSPAREPARTNAME";
   	  var url="HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&cmbValues="+document.forms[0].strEquipName.value;
      ajaxFunction(url,"3");
}
function getManufecturerName() {
	var mode = "MANUFECTURERNAME";
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&cmbValues="+document.forms[0].strSparePartName.value;
	ajaxFunction(url, "4");
}
function getSupplierName() {
	var mode = "SUPPLIERNAME";
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode;
	ajaxFunction(url, "5");
}
function getSubGroupName()
{
	var mode = "SUBGROUPNAME"
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&cmbValues="+document.forms[0].strGroupName.value;
	ajaxFunction(url,"6");
} 


function getStoreName()
{

	var mode = "GETSTORENAME"
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"2");
} 

function getEnggItemSubTypeCombo()
{
        var mode = "ENGGITEMSUBTYPECMB";
        var url ="HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&enggItemType="+document.forms[0].strEngineeringItemTypeId.value;
 		ajaxFunction(url,"1");		
}

function getItemCategoryName()
{
	var mode = "GETITEMCATEGORYNAME";
        var url ="HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&storeId="+document.forms[0].strStoreId.value;
 		ajaxFunction(url,"3");	
}

 

function getStockDtl()
{	   
   var objVal1 = document.getElementById("stockValueId");
   objVal1.innerHTML ="";
   
   var url="HemmsSparePartMgmtDeskACTION.cnt?hmode=GETSTOCKDTL&itemNo="+document.forms[0].strItemId.value+"&deptId="+document.forms[0].strDeptCode.value;
   ajaxFunction(url,"6");
} 



function getEquipmentBrandName() 
{
	var mode = "ITEMBRANDNAME";

	// alert("strore id"+document.forms[0].strItemName.value);
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&strItemId="
			+ document.forms[0].strItemId.value + "&storeId="+ document.forms[0].strStoreId.value;
	ajaxFunction(url,"9");

}

function getEquipmentSlNo() {
	var mode = "EQUIPMENTSLNO";
	var url = "HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&strItemBrandId="
			+ document.forms[0].strItemBrandId.value +"&strItemId="+document.forms[0].strItemId.value+"&strStoreId="
			+document.forms[0].strStoreId.value;
	ajaxFunction(url, "11");
}
function getEquipName()
{	
	
	var mode = "GETEQUIPMENTNAME";
	var url="HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&strSubGrpId="+document.forms[0].strSubGroupName.value;
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
 	      var Obj = document.getElementById("GroupNameDiv");
	      Obj.innerHTML ="<select name ='strGroupName' id='strGroupName' onChange='getItemName();' class='comboMax'>" + res + "</select>";
	}	
	else if(mode=="2")
	{ 
	      var Obj = document.getElementById("EquipNameDivId");
	      Obj.innerHTML ="<select name ='strEquipName' id='strEquipName' onChange='getSparePartName();' class='comboMax'>" + res + "</select>";
	}		
	else if(mode=="3")
	{ 
	      var Obj = document.getElementById("SparePartDivId");
	      Obj.innerHTML ="<select name ='strSparePartName' id='strSparePartName'  onChange='getManufecturerName();' class='comboMax'>" + res + "</select>";
	}		
	else if(mode=="4")
	{ 
	      var Obj = document.getElementById("strManufacturerDiv");
	      Obj.innerHTML ="<select name ='strManufacturerName' id='strManufacturerName' class='comboMax'>" + res + "</select>";
		  getSupplierName();
	}
	else if(mode=="5")
	{ 
	      var Obj = document.getElementById("SupplierDiv");
	      Obj.innerHTML ="<select name ='strSuppliedName' id='strSuppliedName' class='comboMax'>" + res + "</select>";
	}		
	else if(mode=="6")
	{ 
	      var Obj = document.getElementById("SubGroupNameDiv");
	      Obj.innerHTML ="<select name ='strSubGroupName' id='strSubGroupName' onChange='showOrHideSrlNo();getEquipName();' class='comboMax'>" + res + "</select>";
	}
	else if(mode=="12")
	{ 
	      var Obj = document.getElementById("EquipNameDivId");
	      Obj.innerHTML ="<select name ='strEquipName' id='strEquipName' onChange='getSparePartName();' class='comboMax'>" + res + "</select>";
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
		    
		 if(mode=="9")
	    { 
     	  if(res=="")
			{
				document.getElementById("itemModelId").innerHTML="<select name ='strItemBrandId' class='comboMax' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("itemModelId");
	       	            itemParaObj.innerHTML ="<select name = 'strItemBrandId' onchange='getSupplierName();' class='comboMax'>" + res + "</select>";
	        }
	            getSparePartName();
		}
		
		if(mode=="10")
	    { 
     	  if(res=="")
			{
				document.getElementById("equipmentSupplierId").innerHTML="<select name ='strEquipmentSupplierId' class='comboMax' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("equipmentSupplierId");
	       	            itemParaObj.innerHTML ="<select name = 'strEquipmentSupplierId' onchange='getEquipmentSlNo();' class='comboMax'>" + res + "</select>";
	        }
		}
		if(mode=="11")
	    { 
	      if(res=="")
			{
				document.getElementById("equipmentSlNoId").innerHTML="<select name ='strEquipmentSerialNo' class='comboMax' tabindex='1' ><option value='0'>Select Value</option></select>";
			}
	       else
	       {
	       	            var itemParaObj = document.getElementById("equipmentSlNoId");
	       	            itemParaObj.innerHTML ="<select name = 'strEquipmentSerialNo' class='comboMax'>" + res + "</select>";
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
	
	
	 var url="HemmsSparePartMgmtDeskACTION.cnt?hmode=SPAREPARTSTOCKDETAILS&strStockInf="+passVal;
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
	if(document.forms[0].strSubGroupName.value.split("^")[0] == '0')
	{
		if(serialNo=="")
		{
			alert("Serial No. is a Mandatory Field");
			return false;
		}
		else
		{
		document.forms[0].strSparePartSrlNoVal.value = serialNo;
		}
	}
	else if(document.forms[0].strSubGroupName.value.split("^")[0] == '1')
	{
		if(document.forms[0].strReceivedQty.value=="")
		{
			alert("Received Quantity is a Mandatory Field");
			return false;
		}
	}
	else
	{
			alert("Something wrong please Select right option");
			return false;
	}	
	  var hisValidator = new HISValidator("hemmsSparePartMgmtDeskFB");
	  
	  hisValidator.addValidation("strDeptCode","dontselect=0","Please Select Hospital Name");
      hisValidator.addValidation("strGroupName","dontselect=0","Please Select Group Name");
	  hisValidator.addValidation("strEquipName","dontselect=0","Please Select Equipment Name");
	  hisValidator.addValidation("strSparePartName","dontselect=0","Please Select Spare Part Name");
//	  hisValidator.addValidation("strSparePartSrlNoVal","req","Spare Part Serial No. is a Mandatory Field");
	  hisValidator.addValidation("strRate", "req", "Rate is a Mandatory Field");
	  hisValidator.addValidation("strRate", "amount=11,2","Rate format should be 000000000.00");
	  hisValidator.addValidation("strUnitRateID", "dontselect=0","Please Select a Rate Unit");
	  hisValidator.addValidation("strReceivedDate", "req","Received is a Mandatory Field");
	  hisValidator.addValidation("strSuppliedName", "dontselect=0","Please Select a Supplied By");
	  hisValidator.addValidation("strItemSpecification", "maxlen=2000","Specification cannot be greater than 2000 Characters");
		
		if((document.forms[0].strWarrantyFromDate.value != "") || (document.forms[0].strWarrantyUpto.value != ""))
		{			
			hisValidator.addValidation("strWarrantyFromDate", "req","Warranty Date is a Mandatory Field");
//			hisValidator.addValidation("strWarrantyUpTo", "req","Warranty Upto is a Mandatory Field");
			hisValidator.addValidation("strWarrantyUpToUnit", "dontselect=0","Please Select a Unit");
		}		
		if(document.forms[0].strPoDate.value!="") 
		{
			hisValidator.addValidation("strReceivedDate", "dtgtet="+document.forms[0].strPoDate.value,"Received Date should be Greater than or Equal to P.O. Date");
		}	  
	  
	  
	  	 var retVal = hisValidator.validate();
	  	 
	  	   if(retVal)
          {
          		document.forms[0].hmode.value = "SAVE";
	           	document.forms[0].submit();	
          }
          else
          {
             return false;
          }
}


// Clear Page
function clearPage()
{
	/*var strComplaintId = document.forms[0].strComplaintId.value;
	var strHemDesk = document.forms[0].strHemDesk.value;*/
	var strChk = document.forms[0].strChk.value;
	
	document.forms[0].reset();
	document.forms[0].hmode.value="ADD";
	/*document.forms[0].hmode.value="GRIEVANCES";
	document.forms[0].strComplaintId.value=strComplaintId;
	document.forms[0].strHemDesk.value = strHemDesk;*/
	document.forms[0].strChk.value = strChk;
	document.forms[0].submit();
}

function cancelPage()
{
	document.forms[0].hmode.value = "CANCELPAGE";
	document.forms[0].submit();
}


function deleteSparePartStockDetailRecord(passVal,index)
{
	
	var flag=confirm("Are you sure you want to delete this record ?");
	
	if(flag==false)
	return false;
	
	 var mode = "DELETESPAREPARTSTOCKDTL";
        var url ="HemmsSparePartMgmtDeskACTION.cnt?hmode="+mode+"&strStockInf="+passVal+"&spareId="+document.getElementsByName("strSpareId")[index].value+"&spareSerialNo="+document.getElementsByName("strSparePartNo")[index].value+"&ManufacturerNo="+document.getElementsByName("strManufacturerNo")[index].value;
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
		var sparePartDtl = document.forms[0].checkid;
	  	
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
	  			alert("Select at least one of the Stock Details for which the Spare Part Details Are to be Saved");
	  			return false;
	  		}
	  	}
	  	else
	  	{
	  		alert("No Stock Details exist for which Spare Part Details Are to be Added");
	  		return false;
	  	}
	  	
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

 function showOrHideSrlNo()
 {
 	var subGrp = document.getElementById("strSubGroupName").value;
 	if(subGrp.split("^")[0]=="0")
 	{
		document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Serial No.';
		document.getElementById("addSparePartsSrlNo").style.display = "";
		document.getElementById("strSparePartSerialNo").style.display = "";
		document.getElementById("strReceivedQty").style.display = "none";
		document.getElementById("addedSrlNoDiv").style.display = "";
	}
	else 
	{
		document.getElementById("batchNoDivId").innerHTML ='<font color="red">*</font>Received Quantity';
		document.getElementById("addSparePartsSrlNo").style.display = "none";
		document.getElementById("strSparePartSerialNo").style.display = "none";
		document.getElementById("strReceivedQty").style.display = "";
		document.getElementById("addedSrlNoDiv").style.display = "none";
	}
	getItemName();
	emptySrlNo(); 
 }
 
var serialNo = "";
var index = 0;
var rowIndex = 0;
var tableRowIndex=-1;

 function addSerialNo()
 {
 	var srlNo = document.getElementById("strSparePartSerialNo").value;
 	if(compareSrlNo(srlNo))
	{
		alert("Duplicate Serial Number");
		return false;
	}
 	
 	addTD(srlNo);
 	serialNo = serialNo + srlNo + "*";
 	document.forms[0].strSparePartSerialNo.value = "";
 }
 
 function addTD(srlNo)
 {
	if(index > 3)
	{
		rowIndex++;
		insertRow();
	}

	insertColumn(srlNo);
	index++;		
 }
 
 function compareSrlNo(srlNo)
 {
 	var tmpSrlNo = serialNo.split("*");
 	var flag = false;
 	for(i = 0;i<tmpSrlNo.length;i++)
 	{
 		if(tmpSrlNo[i] == srlNo)
 		{
 			flag = true;
 			return flag;
 		}
 	}
 }	
 
 function insertRow()
 {
 	index = 0;
	var srlNoTable = document.getElementById("sparePartSrlNoTable");
	var row = srlNoTable.insertRow(srlNoTable.rows.length);
	row.id = "srlNoTR" + rowIndex;		
 }
 
 function insertColumn(srlNo)
 {
	if(document.getElementById("srlNoTR" + rowIndex) == null)
 	{
 		var table = document.getElementById("sparePartSrlNoTable");
 		var row = table.insertRow(0);
 		row.id = "srlNoTR0";
 		index = 0;
 	}
	var srlTR = document.getElementById("srlNoTR" + rowIndex);
	tableRowIndex = srlTR.rowIndex;
	var clmn = srlTR.insertCell(index);
	clmn.className = "LABEL";
	clmn.style.textAlign = "Center";
	clmn.style.width = "25%";
	clmn.innerHTML= srlNo;
	clmn.appendChild(createImg(index));		
 }
 function createImg(i)
 {
 	var img = document.createElement("IMG");
 	img.id = "deleteSrlNo";
 	img.title = "Remove";
	img.setAttribute("src","../../hisglobal/images/delete_on.gif");
	img.style.cursor = "pointer";
	img.style.height = "12px";
	img.style.width = "12px";
	img.addEventListener("click",deleteSrlNo.bind(this,i),false);
	return img;
 }	

 function emptySrlNo()
 {
 	if(serialNo!="")
	{
	 	serialNo = "";
 		deleteRows();
 	}
 }
 
 function deleteSrlNo(cellIndex,img)
 {
 	var temp = img.currentTarget.parentElement.parentElement;
 	var rIndex = temp.rowIndex;
 	var cellValue = temp.cells[cellIndex].childNodes[0].nodeValue;
 	deleteCells(rIndex,cellIndex,cellValue);
 }	
 
 function deleteCells(rIndex,cellIndex,cellvalue)
 {
 	var table = document.getElementById("sparePartSrlNoTable");
 	reArrangeSrlNo(table.rows[rIndex].id,cellIndex,cellvalue);
 }	
 
 function reArrangeSrlNo(rId,cellIndex,cellvalue)
 {
 	var temp = serialNo.split("*");
 	var tempSrlNo = "";
 	for(i=0;i<temp.length-1;i++)
 	{
 		if(temp[i]!=cellvalue)
 		{
 			tempSrlNo = tempSrlNo + temp[i] + "*" ;
 		}
 	}
 	serialNo = tempSrlNo;

	deleteRows();

 	temp = serialNo.split("*");
 	rowIndex = 0;
// 	tableRowIndex = document.getElementById("sparePartSrlNoTable").rowIndex;
 	insertRow();
 	for(j=0;j<temp.length-1;j++)
 	{
 		addTD(temp[j]);
 	}
 }
 
 function deleteRows()
 {
 	var table = document.getElementById("sparePartSrlNoTable");
	var srlTR
	for(k=0;k<=rowIndex;k++)
	{
 		srlTR = document.getElementById("srlNoTR" + k);
	 	table.deleteRow(srlTR.rowIndex);
	} 
 }
 