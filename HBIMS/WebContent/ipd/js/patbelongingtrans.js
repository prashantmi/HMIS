function validate1()
{
	var hisValidator = new HISValidator("patbelongingTransBean");
	var retVal = true;	
	if(document.forms[0].strValue.value==1){//Return
		if(typeof(document.forms[0].strNoSave)!="undefined"){
			return false;
		}else{
			var txt = document.getElementsByName("strItemReturnToU");
			var chk = document.getElementsByName("strchkvisit"); 		
			for(var j=0;j<chk.length;j++){
				if(chk[j].checked==true){
					if(txt[j].value==""){
						alert("Item Return To is a mandatory");
						retVal = false;
					}
				}
			}
			if(retVal){
				var i=0;
				var objChk=document.getElementsByName("strchkvisit");
				var recDt = document.getElementsByName("strItemReceivedDate");

				for(i=0;i<recDt.length;i++){
					if(objChk[i].checked){
						var o=document.getElementsByName("strItemReturnDateU");
						var rtVal = compareDate(o[i].value,recDt[i].value);
						if(rtVal.mode == 0){
							alert("Return Date should be greater than or equal to Received Date");	 
							return false;
						}
					}
				}
				document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
			}
			else
			{
				return false;
			}
		}
	}
	else//Belonging Add
	{
		hisValidator.addValidation("strItemName", "req","Item Name Is mandatory");
		hisValidator.addValidation("strItemQuantity", "req","Item Quantity Is mandatory");
		if(document.forms[0].strRemarksMandatoryFlag.value==1)
			{hisValidator.addValidation("strRemarks", "req","Remarks Is mandatory");
			hisValidator.addValidation("strRemarks", "maxlen=100","");}
		retVal = hisValidator.validate();
		if(retVal){
			retVal = checkMultirow('strItemName',"Item Name Already Exists");
		}
		if(retVal){
			document.forms[0].hmode.value = "INSERT";
			document.forms[0].submit();
		}else{
			return false;
		}
	}
}

function goFuncOnEnter(e)
{
		if(e.keyCode == 13){
		 goFunc();
		 }else{
		 	return false;
		 }
}


function goFunc()
	{
		var hisValidator = new HISValidator("patbelongingTransBean");  
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	//	hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}else{
		
		return false;
		}
	}




function view(){
	if(document.forms[0].strCrNo.value!=""){
		document.forms[0].strCrNo.readOnly=true;
		//document.forms[0].strValue.value=document.forms[0].strValueHidden.value
		if(document.forms[0].strValue.value=="0")
		{
			var obj=document.getElementById("id4");
			obj.style.display="block";
			document.getElementById("id6").style.display="none";
			document.getElementById("id7").style.display="none";
			document.getElementById("id5").style.display="block";	
			
		}
		else
		{
			var strHidden=document.forms[0].strHiddenPatDtl.value;
			var temp=strHidden.split("^");
			var size=parseInt(document.forms[0].strItemCount.value)-1;
			if(size==1)
			{
				document.forms[0].strItemReturnToU.value=temp[0];
				document.forms[0].strItemReturnToU.readOnly="true";
			}
			else
			{
				for(var i=0;i<size;i++)
				{
				  document.forms[0].strItemReturnToU[i].value=temp[0];
				  document.forms[0].strItemReturnToU[i].readOnly="true";
				}
			}
			var obj=document.getElementById("id4");
			obj.style.display="block";
			document.getElementById("id6").style.display="block";
			document.getElementById("id7").style.display="block";
			document.getElementById("id5").style.display="none";
			document.getElementById("retrmkDiv").style.display="block";
		}
		
	}
	else
	{
		
	}
}

function fun(){
document.forms[0].strCrNo.readOnly=false;
	document.forms[0].strCrNo.value="";
	document.getElementById("id4").style.display="none";
	document.getElementById("retrmkDiv").style.display="none";
	
	
}
function chkBoxClick(obj,index){
	
	if(obj.checked){
	  	
		
		document.getElementById("itemid0"+index).style.display="none";
		document.getElementById("itemid1"+index).style.display="block";
		var o=document.getElementsByName("strItemReturnToU");
		o[index-1].readOnly=false;
		var strHidden=document.forms[0].strHiddenPatDtl.value;
		var temp=strHidden.split("^");
		document.forms[0].strchkvisit.value=index-1;
		var index1=index-1;
		var o=parseInt(document.forms[0].strItemCount.value)-1;
		if(o==1)
		{
			if(document.forms[0].strRelation.value=="0")
			{
				document.forms[0].strItemReturnToU.readOnly=false;
				document.forms[0].strItemReturnToU.value=temp[0];
				document.forms[0].strItemReturnToU.readOnly=true;
			}
			else
			{
				document.forms[0].strItemReturnToU.readOnly=false;
				document.forms[0].strItemReturnToU.value="";
			}
		}
		else
		{
			if(document.forms[0].strRelation[index1].value=="0")
			{
				document.forms[0].strItemReturnToU[index1].readOnly=true;
				document.forms[0].strItemReturnToU[index1].value=temp[0];
				document.forms[0].strItemReturnToU[index1].readOnly=false;
			}
			else
			{
				document.forms[0].strItemReturnToU[index1].readOnly=false;
				document.forms[0].strItemReturnToU[index1].value="";
			}
		}
		
		
		
	}else{
		 document.getElementById("itemid0"+index).style.display="block";
		document.getElementById("itemid1"+index).style.display="none";
	//	if(document.getElementById("strItemReturnTo0"+index))
	    var o=document.getElementsByName("strItemReturnToU");
	    o[index-1].value="";
			o[index-1].readOnly=true;
		var strHidden=document.forms[0].strHiddenPatDtl.value;
		var temp=strHidden.split("^");
		document.forms[0].strchkvisit.value=index-1;
		var index1=index-1;
		var o=parseInt(document.forms[0].strItemCount.value);
		
		if(o==2)
		{
			if(document.forms[0].strRelation.value=="0")
			{
				if(document.forms[0].strItemReturnToU.readOnly==true)
					document.forms[0].strItemReturnToU.readOnly=false;
				document.forms[0].strItemReturnToU.value=temp[0];
				document.forms[0].strItemReturnToU.readOnly=true;
			}
			else
			{
				document.forms[0].strItemReturnToU.readOnly=false;
				document.forms[0].strItemReturnToU.value="";
			}
			
		}
		else
		{
			if(document.forms[0].strRelation[index1].value=="0")
			{
				if(document.forms[0].strItemReturnToU[index1].readOnly==true)
					document.forms[0].strItemReturnToU[index1].readOnly=false;
				document.forms[0].strItemReturnToU[index1].value=temp[0];
				document.forms[0].strItemReturnToU[index1].readOnly=true;
			}
			else
			{
				document.forms[0].strItemReturnToU[index1].readOnly=false;
				document.forms[0].strItemReturnToU[index1].value="";
			}
		}
	}
	
}
function menuPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();
   }
function relationSet(obj,a)
{
	var strHidden=document.forms[0].strHiddenPatDtl.value;
	var temp=strHidden.split("^");
	var o=parseInt(document.forms[0].strItemCount.value)-1;
	//alert(o);
	if(o==1)
	{
		if(obj.value=="0")
		{
			
			if(document.forms[0].strItemReturnToU.readOnly==true)
			{
				
				document.forms[0].strItemReturnToU.readOnly=false;
			}
			
			document.forms[0].strItemReturnToU.value=temp[0];
			document.forms[0].strItemReturnToU.readOnly=true;
		}
		else
		{
			
				document.forms[0].strItemReturnToU.readOnly=false;
			
			document.forms[0].strItemReturnToU.value="";
		}
	}
	else
	{
		if(obj.value=="0")
		{
			if(document.forms[0].strItemReturnToU[a-1].readOnly==true)
			{
				document.forms[0].strItemReturnToU[a-1].readOnly=false;
			}
			document.forms[0].strItemReturnToU[a-1].value=temp[0];
			document.forms[0].strItemReturnToU[a-1].readOnly=true;
		}
		else
		{
			
			document.forms[0].strItemReturnToU[a-1].readOnly=false;
			document.forms[0].strItemReturnToU[a-1].value="";
		
		}
	}

}
function getIndex(_these){
	var nTmpI;
	var strSelectedObjName=document.getElementsByName(_these.name)
	for(nTmpI=0;nTmpI<strSelectedObjName.length;nTmpI++)
		if(_these==strSelectedObjName[nTmpI])
			break;
	return nTmpI;
}

function checkBOrI(_these){
	var nIndex = getIndex(_these);
	if(document.getElementsByName("strBelonging")[nIndex].checked && document.getElementsByName("strIssue")[nIndex].checked){
		alert("Must Select Only One Either Belonging or Issue for One Item.");
		_these.checked = false;
	}
}

function addBelonging(_tableId){
	var array = new Array("<select name=strBelongingItem onchange='itemChange(this)'>"+
	document.getElementsByName("strBelongingItemValues")[0].value+
	"<option value='others'>Others</option></select>"+
	'<center><input name="strItemName" style="display: none" type=text></center><input name="strItemId" type=hidden><input name="strItemType" value=2 type=hidden>',
	'<input class="txtFldNormal" name="strItemQuantity" onkeypress="return validateData(event,5);" maxlength="3" type="text">',
	'<input class="txtFldNormal" name="strRemarks" onkeypress="return validateData(event,9);" maxlength="100" type="text">');
	multiRow(_tableId,array, 1, 0, true);
}

function addIssuedItem(_tableId){
	var array = new Array("<select name=strBelongingItem onchange='itemChange(this)'>"+
	document.getElementsByName("strIssuedItemValues")[0].value+
	"<option value='others'>Others</option></select>"+
	'<center><input name="strItemName" style="display: none" type=text></center><input name="strItemId" type=hidden><input name="strItemType" value=1 type=hidden>',
	'<input class="txtFldNormal" name="strItemQuantity" onkeypress="return validateData(event,5);" maxlength="3" type="text">',
	'<input class="txtFldNormal" name="strRemarks" onkeypress="return validateData(event,9);" maxlength="100" type="text">');
	multiRow(_tableId,array, 1, 0, true);
}

function itemChange(_these){
	if(_these.value=="others"){
		document.getElementsByName("strItemName")[getIndex(_these)].style.display = "block";
	} else {
		document.getElementsByName("strItemName")[getIndex(_these)].style.display = "none";
		document.getElementsByName("strItemName")[getIndex(_these)].value=_these.value.split("^")[1];
		document.getElementsByName("strItemId")[getIndex(_these)].value=_these.value.split("^")[0];
	}
}