
function buttonLogicsOnClick1(mode)
{
	       var Obj = document.getElementsByName("combo");
   	 	
 	       if(Obj[0].value =="0")
		   {
				alert("Please Select Hospital Name!!!");
				Obj[0].focus();
				return ;
			}
		    else
			{ 
				if(Obj[1].value =="0")
				{
				   alert("Please Select Lab Name!!!");
				   Obj[1].focus();
				   return ;	
				}
				else
				{
					 add(mode); 	
				}
   	 	   	 		
		   	}

}
function submitforValues()
{//alert(document.getElementById("strLabName").value);
	document.getElementById("hmode").value="ADD";
document.forms[0].submit();	
}

function getLabCmb()
{
        var mode = "LABCOMBO";
        var url ="InstallationMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
 		ajaxFunction(url,"1");		

}
function getBlockCmb()
{
	var mode = "BLOCKCOMBO";
	var url  = "InstallationMstCNT.cnt?hmode="+mode+"&BlockId="+document.forms[0].strBlockName.value
	ajaxFunction(url,"10");		   
}
function getBuildCmb()
{
        var mode = "BUILDCOMBO";
        var url ="InstallationMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strBuildingName.value;
 		ajaxFunction(url,"2");		

}

function getFloorCmb()
{
       var mode = "FLOORCOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strFloorName.value;
 	   ajaxFunction(url,"3");		

}
function getRoomCmb()
{
       var mode = "ROOMCOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strRoomName.value;
 	   ajaxFunction(url,"4");
}
function getLocationCmb()
{
       var mode = "LOCATIONCOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strHospital.value+"@"+document.forms[0].strLabName.value;
 	   ajaxFunction(url,"13");
}
function getLocationDtl()
{
       if(document.forms[0].strLocationName.value=="0")
       {
       		setDefaultLocation();
	 		return true;
       }
       var mode = "LOCATIONDTL";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&locationId="+document.forms[0].strLocationName.value;
 	   ajaxFunction(url,"14");
}

function getEquipGroupCmb()
{
	   var mode = "EQUIPGROUPCOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strHospital.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"5");
}
function getEquipSubGroupCmb()
{
	   var mode = "EQUIPSUBGROUPCOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&groupId="+document.forms[0].strEquipGroup.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"6");
}
function getEquipNameCmb()
{
	   var mode = "EQUIPNAMECOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&subGroupId="+document.forms[0].strEquipSubGroup.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"7");
}
function getEquipModelNameCmb()
{
	   var mode = "EQUIPMODELNAMECOMBO";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&itemId="+document.forms[0].strEquipName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"8");
}
function getSupNameCmb()
{
	   var mode = "SUPPLIERNAMECOMBO";
	   var val = document.forms[0].strEquipGroup.value + "^" + document.forms[0].strEquipSubGroup.value + "^" + document.forms[0].strEquipName.value 
	   			 + "^" + document.forms[0].strEquipModel.value + "^" + document.forms[0].strSrlNoCmb.value;

       var url ="InstallationMstCNT.cnt?hmode="+mode+"&comboVal="+val;
 	   ajaxFunction(url,"9");
}
function getStockCmb()
{
	   var mode = "EQUIPSERIALNO";
	   var val = document.forms[0].strEquipGroup.value + "^" + document.forms[0].strEquipSubGroup.value + "^" + document.forms[0].strEquipName.value 
	   			 + "^" + document.forms[0].strEquipModel.value + "^" + document.forms[0].strHospital.value+ "^" + document.forms[0].strLabName.value
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&comboVal="+val;
 	   ajaxFunction(url,"11");
}
function getDrInCharge()
{
	   var mode = "DRINCHARGE";
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&comboVal="+document.forms[0].strHospital.value;
 	   ajaxFunction(url,"15");
}

function getInstallationDtl()
{
	   if(document.forms[0].strSrlNoCmb.value=="0")
       {
       		setDefaultInstallValue();
	 		return true;
       }
	   var mode = "INSTALLATIONDTL";
	   var val = document.forms[0].strEquipName.value + "^" + document.forms[0].strEquipModel.value+ "^" + document.forms[0].strSrlNoCmb.value; 
       var url ="InstallationMstCNT.cnt?hmode="+mode+"&comboVal="+val;
 	   ajaxFunction(url,"12");
}

function UpdateValues()
{
getEquipGroupCmb();	
getEquipSubGroupCmb();
getEquipNameCmb();
getEquipModelNameCmb();
getSupNameCmb();
}
function getAjaxResponse(res,mode)
{

      var err = document.getElementById("strErr");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 


   if(mode=="1")
   { 
			var objVal= document.getElementById("LabNameDiv");
			objVal.innerHTML = "<select name ='strLabName' class='comboMax'>"+res+"</select>";	
			getLocationCmb();
			//setDefaultComboValue("LocationDivId","strLocationName");
			setDefaultComboValue("EquipmentGroupDiv","strEquipGroup");
			setDefaultLocation();
			//getDrInCharge();
			//getBlockCmb();getEquipGroupCmb();
	}
	else if(mode=="2")
	{
			var objVal= document.getElementById("BuildDivID");
			objVal.innerHTML ="<select name='strBuildingName' class='comboMax' onchange='getFloorCmb()'>"+res+"</select>";		
			getFloorCmb();
	}
	
	else if(mode=="3")
	{
			var objVal= document.getElementById("FloorDivId");
			objVal.innerHTML ="<select name='strFloorName' class='comboMax' onchange='getRoomCmb()'>"+res+"</select>";		
			getRoomCmb();
	}
	
	else if(mode=="4")
	{
			var objVal= document.getElementById("RoomDivId");
			objVal.innerHTML ="<select name='strRoomName' class='comboMax'>"+res+"</select>";		
	}
	else if(mode=="5")
	{
	 		var objVal= document.getElementById("EquipmentGroupDiv");
			objVal.innerHTML ="<select name='strEquipGroup' class='comboMax' onchange='getEquipSubGroupCmb();'>"+res+"</select>";
			getDrInCharge();		
	}	
	else if(mode=="6")
	{
	 		var objVal= document.getElementById("EquipmentSubGroupDiv");
			objVal.innerHTML ="<select name='strEquipSubGroup' class='comboMax' onchange='getEquipNameCmb()'>"+res+"</select>";		
			getEquipNameCmb();
	}
	
	else if(mode=="7")
	{
	 		var objVal= document.getElementById("EquipmentNameDiv");
			objVal.innerHTML ="<select name='strEquipName' class='comboMax' onchange='getEquipModelNameCmb()'>"+res+"</select>";
			setDefaultComboValue("EquipmentModelDiv","strEquipModel");
			setDefaultComboValue("SrlNoDiv","strSrlNoCmb");
			setDefaultComboValue("SupplierDiv","strSupplierName");
			setDefaultInstallValue();		
	}

	else if(mode=="8")
	{
	 		var objVal= document.getElementById("EquipmentModelDiv");
			objVal.innerHTML ="<select name='strEquipModel' class='comboMax' onchange='getStockCmb()'>"+res+"</select>";		
			setDefaultComboValue("SrlNoDiv","strSrlNoCmb");
			setDefaultComboValue("SupplierDiv","strSupplierName");
			setDefaultInstallValue();		

	}
	else if(mode=="9")
	{
	 		var objVal= document.getElementById("SupplierDiv");
			objVal.innerHTML ="<select name='strSupplierName' class='comboMax'>"+res+"</select>";
			getInstallationDtl();	
	}	
	else if(mode=="10")
	{
	 		var objVal= document.getElementById("BlockDivId");
			objVal.innerHTML ="<select name='strBlockName' class='comboMax' onchange='getBuildCmb()'>"+res+"</select>";		
			getBuildCmb();
	}
	else if(mode=="11")
	{
	 		var objVal= document.getElementById("SrlNoDiv");
			objVal.innerHTML ="<select name='strSrlNoCmb' class='comboMax' onchange='getSupNameCmb()'>"+res+"</select>";
			setDefaultComboValue("SupplierDiv","strSupplierName");
			setDefaultInstallValue();		
	}	
	else if(mode=="12")
	{
			var objVal = document.getElementById("RecievedDtDiv");
	 		objVal.innerHTML = "<label id='strRecievedDt' name='strRecievedDt'>" + res.split("^")[0] + "</label>"
			document.forms[0].strRecievedDt.value = res.split("^")[0];
			if(res.split("^")[1] == "null")
			{
				objVal= document.getElementById("strInstalStartDt");
		 		objVal.value = "";
		 		objVal= document.getElementById("strInstalEndDt");
		 		objVal.value = "";
				document.forms[0].strContactNo.value = "";
				return true;
			}
	 		
	 		objVal = document.getElementById("strInstalStartDt");
	 		objVal.value = res.split("^")[1];
	 		objVal= document.getElementById("strInstalEndDt");
	 		objVal.value = res.split("^")[2];
			document.forms[0].strContactNo.value = res.split("^")[3];
	}	
	else if(mode=="13")
	{
	 		var objVal= document.getElementById("LocationDivId");
			objVal.innerHTML ="<select name='strLocationName' class='comboMax' onchange='getLocationDtl()'>"+res+"</select>";
			setDefaultLocation();		
			getEquipGroupCmb();
				
			
	}	
	else if(mode=="14")
	{
	 		var strVal = res.split("^");
	 		var obj = document.forms[0].strFloorName;
	 		document.forms[0].strBlockName[0].value = strVal[0];
	 		document.forms[0].strBuildingName[0].value = strVal[1];
	 		document.forms[0].strFloorName[0].value = strVal[2];
	 		document.forms[0].strRoomName[0].value = strVal[3];
	 		getBlockCmb();	
	}	
	else if(mode=="15")
	{
			var objVal= document.getElementById("DrInChrgDiv");
			objVal.innerHTML ="<select name='strDrInCharge' id='strDrInCharge' class='comboMax'>"+res+"</select>";		
	}		
}

function validate1()
{   
//alert("Inside Save Process");
     var hisValidator = new HISValidator("InstallationMstFB");
     hisValidator.addValidation("strHospital","dontselect=0","Please Select Hospital Name");
     hisValidator.addValidation("strLabName","dontselect=0","Please Select Lab Name");
	 hisValidator.addValidation("strLocationName","dontselect=0","Please Select Location Name");
     hisValidator.addValidation("strEquipGroup","dontselect=0","Please Select Group Name");
     hisValidator.addValidation("strEquipName","dontselect=0","Please Select Euipment  Name");
     hisValidator.addValidation("strEquipModel","dontselect=0","Please Select Model Name");
     hisValidator.addValidation("strSrlNoCmb","dontselect=0","Please Select Serial No.");
     hisValidator.addValidation("strDrInCharge", "dontselect=0","Please Select In Charge Name");
     hisValidator.addValidation("strInstalStartDt", "date","Start Date is a Mandatory Field");
     hisValidator.addValidation("strInstalEndDt", "date","End Date is a Mandatory Field");
     hisValidator.addValidation("strContactNo", "req","Contact Number is a Mandatory Field");
     hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );

 	 var strInstalStartDt = document.getElementById("strInstalStartDt").value;
 	 //alert("strInstalStartDt::"+strInstalStartDt);
 	 document.getElementById("strInstalEndDt").value=document.getElementById("strInstalEndDt").value.trim();
 	 //alert("strInstalEndDt::"+document.getElementById("strInstalEndDt").value);
	 hisValidator.addValidation("strInstalEndDt", "dtgtet="+strInstalStartDt,"End Date should be Greater than Start Date");            

 	 var strRecievedDt = document.getElementById("strRecievedDt").innerHTML.trim();
 	 //alert("strRecievedDt::"+strRecievedDt);
	 hisValidator.addValidation("strInstalStartDt", "dtgtet="+strRecievedDt,"Start Date should be Greater than Recieved Date");            

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
function updateValidate()
{   
     var hisValidator = new HISValidator("InstallationMstFB");
	 hisValidator.addValidation("strLocationName","dontselect=0","Please Select Location Name");
     hisValidator.addValidation("strDrInCharge", "dontselect=0","Please Select In Charge Name");
     hisValidator.addValidation("strInstalStartDt", "date","Start Date is a Mandatory Field");
     hisValidator.addValidation("strInstalEndDt", "date","End Date is a Mandatory Field");
     hisValidator.addValidation("strContactNo", "req","Contact Number is a Mandatory Field");
     hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );

 	 var strInstalStartDt = document.getElementById("strInstalStartDt").value;
 	 //alert("strInstalStartDt::"+strInstalStartDt);
 	 document.getElementById("strInstalEndDt").value=document.getElementById("strInstalEndDt").value.trim();
 	 //alert("strInstalEndDt::"+document.getElementById("strInstalEndDt").value);
	 hisValidator.addValidation("strInstalEndDt", "dtgtet="+strInstalStartDt,"End Date should be Greater than Start Date");            

 	 var strRecievedDt = document.getElementById("strRecievedDt").innerHTML.trim();
 	 //alert("strRecievedDt::"+strRecievedDt);
	 hisValidator.addValidation("strInstalStartDt", "dtgtet="+strRecievedDt,"Start Date should be Greater than Recieved Date");            

  	 var retVal = hisValidator.validate(); 
     if(retVal)
     {
    	   document.forms[0].hmode.value = "UPDATE";
           document.forms[0].submit();
     }
    else
    {
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
function setDefaultLocation()
{
 	document.forms[0].strBlockName[0].text = "N/A";
    document.forms[0].strBlockName[0].value = "0";
	document.forms[0].strBuildingName[0].text = "N/A";
	document.forms[0].strBuildingName[0].value = "0";
	document.forms[0].strFloorName[0].text = "N/A";
	document.forms[0].strFloorName[0].value = "0";
	document.forms[0].strRoomName[0].text = "N/A";
	document.forms[0].strRoomName[0].value = "0";
}
function setDefaultComboValue(DivId,comboId)
{
	var objVal= document.getElementById(DivId);
	objVal.innerHTML ="<select name='"+ comboId +"' id='"+ comboId +"' class='comboMax'><option value='0'>Select Value </option></select>";
} 
function setDefaultInstallValue()
{
	var objVal= document.getElementById("strRecievedDt");
	objVal.innerHTML = "";
	if(parseInt(document.getElementById("strDrInCharge").length)<=1)
		setDefaultComboValue("DrInChrgDiv","strDrInCharge");
	objVal= document.getElementById("strInstalStartDt").value = "";
	objVal= document.getElementById("strInstalEndDt").value = "";
	document.forms[0].strContactNo.value = "";
}
