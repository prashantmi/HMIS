var count = true;
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
{alert(document.getElementById("strLabName").value);
	document.getElementById("hmode").value="ADD";
document.forms[0].submit();	
}

function getLabCmb()
{
        var mode = "LABCOMBO";

        var url ="BuildingMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
 		ajaxFunction(url,"1");		

}
function getEmpcmb()
{
        var mode = "EMPCOMBO";

        var url ="MaintAndWarrantyDeskConfigMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strUserID.value;
 		ajaxFunction(url,"2");		

}

function getAjaxResponse(res,mode)
{
	  var objVal;
      var err = document.getElementById("strErr");
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
			document.getElementById("LabNameDiv").innerHTML="<select name ='strLabName' class='comboMax' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			objVal= document.getElementById("LabNameDiv");
			objVal.innerHTML = "<select name ='strLabName' class='comboMax' onchange='getBlockDtl()' >"+res+"</select>";		
		}
	}
	if(mode=="2")
	{
			objVal = document.getElementById("strBlock");
			objVal.innerHTML = "<select id='strBlockName' name ='strBlockName' class='comboMax' >"+res+"</select>";
	}	
	if(mode=="3")
	{
		if(res!=0)
		{
			count = false;
			var tmpVal=document.getElementById("strBuildingName").value;
			alert('Building Name "'+tmpVal.trim()+'" Already Exists');
		}
		else
		{
			count = true;
		}	
	}	
}

function validate1(mode)
{   
			if(!count)
			{
				var tmpVal=document.getElementById("strBuildingName").value;
				alert('Building Name "'+tmpVal.trim()+'" Already Exists');
				return false;
			}     
 	
     
             var hisValidator = new HISValidator("BuildingMstFB");
             
             hisValidator.addValidation("strHospital","dontselect=0","Please Select Hospital Name");
             //hisValidator.addValidation("strLabName","dontselect=0","Please Select Lab Name");
             hisValidator.addValidation("strBlockName","dontselect=0","Please Select Block Name");
 	         hisValidator.addValidation("strBuildingName","req", "Building Name is a Mandatory Field" );
	         
	          hisValidator.addValidation("strUserID","dontselect=0","Please Select User Name");
              hisValidator.addValidation("strEmpID","dontselect=0","Please Select Emp Name");
	          hisValidator.addValidation("strEvent","dontselect=0","Please Select Event Type");
	         
	      //    hisValidator.addValidation("strTestName","req", "Test Name is a Mandatory Field" );
 
            hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
          //  hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            
  var retVal = hisValidator.validate(); 

          if(retVal)
          {
                 	   document.forms[0].hmode.value = mode;
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}

function getBlockDtl()
{
	var url="BuildingMstCNT.cnt?hmode=BLOCKDTL&storeId="+document.forms[0].strHospital.value
			+"&labId="+document.forms[0].strLabName.value;
   	ajaxFunction(url,"2");
}
function chkDuplicateBld(mode)
{
	
	var strBuildingName = document.getElementById("strBuildingName").value;
	if(mode=="1")
	{
		var url = "BuildingMstCNT.cnt?hmode=CHKDUPLICATE&blockId="+document.forms[0].strBlockName.value+"&storeId="
		+document.forms[0].strHospital.value+"&mode="+mode+"&buildingName="+strBuildingName.trim();
	}
	else if(mode=="2")
	{
		var url = "BuildingMstCNT.cnt?hmode=CHKDUPLICATE&blockId="+document.forms[0].strBlockName.value+"&storeId="
		+document.forms[0].strHospital.value+"&mode="+mode+"&buildingName="+strBuildingName.trim()
		+"&buildingId="+document.forms[0].strBuildingId.value;
	}
	ajaxFunction(url, "3");
	
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
 