
function isGroupCombo(obj)
{
	var grpId = obj.value;
 	var mode="TARIFFCOMBO";
	var url="CNTpackservMst.cnt?hmode="+mode+"&grpId="+grpId;
	ajaxFunction(url,"2");
}

function getTariffDtls(){

	document.forms[0].groupName.focus();
	var mode="TARIFFDETAIL";
	var url="CNTpackservMst.cnt?hmode="+mode;
	ajaxFunction(url,"1");
	document.getElementById("normalMsg").style.display="block";	
	document.getElementById("normalMsg").innerHTML=document.forms[0].strNormalMsg.value;
}

var gblIndex = "";

 function getUnitDtls(cmbObj , index){
 
 	gblIndex = index;
 
 	var unitId = cmbObj.value.split('^')[1];

 	var mode="BASEUNITVAL";
	var url="CNTpackservMst.cnt?hmode="+mode+"&unitId="+unitId;
	ajaxFunction(url,"3");
	document.getElementById("normalMsg").style.display="block";
 
 }

function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{
		
		
		document.getElementById("normalMsg").style.display="block";
		
		document.getElementById("normalMsg").innerHTML=document.forms[0].strNormalMsg.value;
		var objEle=document.getElementById("tariffId");
		
		objEle.innerHTML=res;
		
		
		
	}
	if(mode=="2")
	{
		var objEle=document.getElementById("trfcmb#delIndex#");
						
		objEle.innerHTML="<select name='strMultiTariffId' onchange='getUnitDtls(this , \"#delIndex#\");' id='tariffname#delIndex#' class='comboMax'>"+res+"</select>";
	}
	
	if(mode == "3"){
	
		document.getElementById("trfUnitDivId"+gblIndex).innerHTML = "<select name='strMultiUnitId' id='unitname"+gblIndex+"'  class='comboNormal' >"+res+"</select>";
	}
	
}

function Showlayer1()
{
  var obj=document.getElementById("plus");
  obj.style.display="none";
  var obj=document.getElementById("minus");
  obj.style.display="block";
   var obj=document.getElementById("tariffId");
  obj.style.display="block";
}

function Showlayer2()
{
  var obj=document.getElementById("plus");
  obj.style.display="block";
  var obj=document.getElementById("minus");
  obj.style.display="none";
  var obj=document.getElementById("tariffId");
  obj.style.display="none";
}
function CheckDataExistsByCmbText(dataName, multiCompName,userMessage){
		
		if(userMessage == '') userMessage = "Data Already Exists";

			var dataVal = document.getElementsByName(dataName);
			var dataLen = dataVal.length;

		if(dataLen != 0){

			var multVal = document.getElementsByName(multiCompName);
			var multLen = multVal.length - 1;

			
			for(i=0; i<dataLen; i++){

				for(j=0; j<multLen; j++){

					
					if(dataVal[i].value == multVal[j][multVal[j].selectedIndex].text){

							alert(userMessage);
							multVal[j].focus();

							return false;
					}
				}
			}
		}

		return true;
	}
function submitData(mode){

	var hisValidator = new HISValidator("packservBean");
	var retVal = checkMultirow('strMultiTariffId','Same Tariff Name cannot be Entered ');
		
		if(retVal){
		
		retVal = CheckDataExistsByCmbText('strtariffName','strMultiTariffId','Tariff Name Already Exists');  
	 	
		}
		
		if(retVal){
		hisValidator.addValidation("strMultiTariffId","dontselect=0","Please select a value from Tariff Name Combo");
	 	 hisValidator.addValidation("strMultiQty", "req", "Quantity is a Mandatory Field" );
    	 hisValidator.addValidation("strMultiUnitId","dontselect=0","Please select a value from Unit Combo");
    	 hisValidator.addValidation("streffectiveFrm", "req", "Effective From Date is a Mandatory Field" ); 
		  hisValidator.addValidation("document.forms[0].streffectiveFrm","dtgtet="+"document.forms[0].strCtDate.value","Effective From Date must be Greater Than Or Equal To Current Date");
		hisValidator.addValidation("strremarks","maxlen=50","Remarks should not be greater than 50 characters");
	
	 retVal = hisValidator.validate();
	
	}
	if(retVal){	
		if(document.forms[0].strMultiQty.value=="0")
		{
			alert("Quantity cannot be zero");
			retVal = false;
		}
	}
	if(retVal){	
	var arr = document.getElementsByName("strMultiTariffId");
		var multirowLen = arr.length-1;
		if(multirowLen>1)
		{
		for(i=0; i< multirowLen-1 ; i++){
			for(j=multirowLen-1; j>i; j--){
			
			if(document.forms[0].strMultiTariffId[j].value == document.forms[0].strMultiTariffId[i].value)
			{
				alert("Selected Tariff Name can not be same");
				retVal = false;
			 	break;
			 }
		 }	
		}	
	 }
	}
	if(retVal){	
		document.forms[0].hmode.value="SAVE";
	 	document.forms[0].submit();
	}else{		
		return false;
	}	
}

function addMultirow()
{
     var retVal;
	 var hisValidator = new HISValidator("packservBean");
	 hisValidator.addValidation("groupName","dontselect=0","First Select the Group Name");
	 retVal = hisValidator.validate();
	if(retVal){
		// Modified by Manisha 
		addRows(new Array('tariffname','quantity','unitname'),new Array('s','t','s'),'1','1','R');
        document.getElementById("id1").style.display="block";
	}
	else
	{
	    document.getElementById("id1").style.display="none";
		return false;
	}
}
function submitDataModify(mode)
{

   var hisValidator = new HISValidator("packservBean");
   	 hisValidator.addValidation("strqty", "req", "Quantity is a Mandatory Field" );
     hisValidator.addValidation("strunitId","dontselect=0","Please select a value from Unit Combo");
     
	hisValidator.addValidation("strremarks","maxlen=50","Remarks should not be greater than 50 characters");
  
   var retVal = hisValidator.validate();
   
   if(retVal){	
		if(document.forms[0].strqty.value=="0")
		{
			alert("Quantity cannot be zero");
			retVal = false;
		}
	}
	if(retVal){
	 	document.forms[0].hmode.value=mode;			
         document.forms[0].submit();
    }
	else
	{		
		return false;
	}	
}