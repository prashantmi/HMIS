
function setItemName(obj){
	var element=obj;
	var itemName=document.getElementsByName("itemName")[0];
	var selectedText=element.options[element.selectedIndex].text;
	itemName.value=selectedText;
	
}
function getItemValues(obj)
{
	var values=obj.value.split("#");
	//alert(values);
	if(document.getElementsByName("hmode")[0].value=="ADD"){
		
		document.getElementsByName("lotNo")[0].value=values[2];
	}
	document.getElementsByName("expiryDate")[0].value=values[1];
	lotDuplicacyCheck(document.getElementsByName("lotNo")[0]);
	
}
function ajaxDuplicacyCheck(lotNo)
{
	var flg = false;
	var count = "";
	var _mode = "AJAX_LOT_DUPLICATE_CHECK";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/masters/ItemLabTestMappingMstACT.cnt?hmode="+_mode+"&lotNo="+lotNo, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			count = data;
			flg = true;
		},
        error: function(error)
        {
            alert('Not Able To Connect');
            document.getElementsByName("lotNo")[0].value="";
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return count;	


}
function ajaxGetItemCombo(storeId)
{
	var flg = false;
	var list = "";
	
	var _mode = "AJAX_ITEM_COMBO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/masters/ItemLabTestMappingMstACT.cnt?hmode="+_mode+"&storeID="+storeId, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				
				list = data;
				flg = true;
			},
	        error: function(error)
	        {
	           
	        	list = null;
	            flg = false;
	        }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		return list;


}
function getItemCombo(obj)
{
	var elementItem=document.getElementsByName("itemIdFromStore")[0];
	if(obj.value!=-1)
		{
			var objList=ajaxGetItemCombo(obj.value);
			if(objList!=null)
			{
				elementItem.innerHTML=null;
				for(var i=0;i<objList.length;i++)
				{
					opt = document.createElement("option");
					opt.value = objList[i].itemId;
					opt.innerHTML = objList[i].itemName;
					
					elementItem.appendChild(opt);
				}
			
				getItemValues(document.getElementsByName("itemIdFromStore")[0]);
				
			}
			else{
				alert("No Data Found !Please Choose Other Option");
			}
		}
	
	
}
function lotDuplicacyCheck(element)
{
	
	var lotNo=element.value;
	var lotNoCount=0;
	var tempLot=document.getElementsByName("tempLotNo")[0].value;
	if(lotNo!=tempLot){
		
	
	if(element.value.length>0){
		
	var count=ajaxDuplicacyCheck(lotNo);
	//alert(count);
	if(count>0)
	{	
		element.value="";
		alert('Lot No Already Exist');
	}
	}
	
	}
	
		
}

function showInfo(obj)
{
	var values=document.getElementsByName("selectedChk")[0].value.split("@");
	document.getElementsByName("tempOtherID")[0].value=values[0];
	var tempLot=document.getElementsByName("tempLotNo")[0].value;
	var lot=document.getElementsByName("lotNo")[0].value;
	document.getElementsByName("tempLotNo")[0].value=lot;
if(obj.value=="manual")
	{
		document.getElementById("storeId").style.display="none";
		document.getElementById("itemIdFromStore").style.display="none";
		//document.getElementById("batchNo").style.display="none";
	}
if(obj.value=="store")
{
	document.getElementById("storeId").style.display="";
	document.getElementById("itemIdFromStore").style.display="";
	//document.getElementById("batchNo").style.display="";
}
	document.getElementsByName("tempLotNo")[0].value=document.getElementsByName("lotNo")[0].value;
}
function finalSubmit(mode)
{
	if(validateTodayOrDate()&&validateForm())
		{
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
		}
}
function validateForm()
{
	if(document.getElementsByName("itemName")[0].value=="")
		{
		document.getElementsByName("itemName")[0].focus();
		alert("Please Enter Value of Item Name");
		 return false;
		}
	if(document.getElementsByName("itemID")[0].value=="-1")
	{
	document.getElementsByName("itemID")[0].focus();
	alert("Please Select Item ID");
	 return false;
	}
	if(document.getElementsByName("lotNo")[0].value=="")
	{
	document.getElementsByName("lotNo")[0].focus();
	alert("Please Enter Lot No");
	 return false;
	}
	if(document.getElementsByName("usageStartDate")[0].value=="")
	{
	
	document.getElementsByName("usageStartDate")[0].focus();
	 return false;
	}
	if(document.getElementsByName("usageEndDate")[0].value=="")
	{
	document.getElementsByName("usageEndDate")[0].focus();
	 return false;
	}
	if(document.getElementsByName("expiryDate")[0].value=="")
	{
	document.getElementsByName("expiryDate")[0].focus();
	 return false;
	}
	
	if(document.getElementsByName("usage")[0].value=="-1")
	{
	document.getElementsByName("usage")[0].focus();	
	alert("Please Select Usage Details");
	 return false;
	}
	
	if(getElement().value=="store")
		{
		if(document.getElementsByName("storeID")[0].value=="-1")
		{
		document.getElementsByName("storeID")[0].focus();
		alert("Please Choose Store Name");
		 return false;
		}
		if(document.getElementsByName("itemIdFromStore")[0].value=="-1")
		{
		document.getElementsByName("itemIdFromStore")[0].focus();
		alert("Please Choose Item Name");
		 return false;
		}
		if(document.getElementsByName("lotNo")[0].value=="")
		{
			alert("Please Enter Lot Number");
		document.getElementsByName()[0].focus();
		 return false;
		}
		}
return true;

}
function setDimensions()
{
	document.getElementsByName("expiryDate")[0].style.width="29%";
	document.getElementsByName("usageEndDate")[0].style.width="29%";
	document.getElementsByName("usageStartDate")[0].style.width="29%";
}