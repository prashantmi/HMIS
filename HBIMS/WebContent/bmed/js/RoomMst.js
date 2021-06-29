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

        var url ="RoomMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
 		ajaxFunction(url,"1");		

}
function getBuildCmb()
{
        var mode = "BUILDCOMBO";

        var url ="RoomMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strBlockName.value;
 		ajaxFunction(url,"2");		

}

function getFloorCmb()
{
       var mode = "FLOORCOMBO";
       var url ="RoomMstCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strBlockName.value+"@"+document.forms[0].strBuildingName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"3");		

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
			objVal.innerHTML = "<select name ='strLabName' class='comboMax' onchange='getBlockCmb()'>"+res+"</select>";		
	}
	else if(mode=="2")
	{
			var objVal= document.getElementById("BuildDivID");
			objVal.innerHTML ="<select name='strBuildingName' class='comboMax' onchange='getFloorCmb()'>"+res+"</select>";		
	}
	else if(mode=="3")
	{
			var objVal= document.getElementById("FloorDivId");
			objVal.innerHTML ="<select name='strFloorName' class='comboMax' >"+res+"</select>";		
	}
	else if(mode=="4")
	{
			var objVal= document.getElementById("BlockDivId");
			objVal.innerHTML ="<select name='strBlockName' class='comboMax' onchange='getBuildCmb()'>"+res+"</select>";		
	}
	else if(mode=="5")
	{
		if(res!=0)
		{
			count = false;
			var tmpVal=document.getElementById("strRoomName").value;
			alert('Room Name "'+tmpVal.trim()+'" Already Exists');
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
				var tmpVal=document.getElementById("strRoomName").value;
				alert('Room Name "'+tmpVal.trim()+'" Already Exists');
				return false;
			}  
             var hisValidator = new HISValidator("RoomMstFB");
             
             hisValidator.addValidation("strHospital","dontselect=0","Please Select Hospital Name");
             //hisValidator.addValidation("strLabName","dontselect=0","Please Select Lab Name");
	         
	          hisValidator.addValidation("strBlockName","dontselect=0","Please Select Block Name");
              hisValidator.addValidation("strBuildingName","dontselect=0","Please Select Building Name");
	          hisValidator.addValidation("strFloorName","dontselect=0","Please Select Floor Name");
	         
	       hisValidator.addValidation("strRoomName","req", "Room Name is a Mandatory Field" );
 
            hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
           hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            
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

function getBlockCmb()
{
        var mode = "BLOCKCOMBO";

        var url ="RoomMstCNT.cnt?hmode="+mode+"&storeId="+document.forms[0].strHospital.value
        		 +"&labId="+document.forms[0].strLabName.value;
 		ajaxFunction(url,"4");		

}
function chkDuplicateRoom(mode)
{
	
	var strRoomName = document.getElementById("strRoomName").value;
	if(strRoomName=="")
		return false;
	
	if(mode=="1")
	{
		var url = "RoomMstCNT.cnt?hmode=CHKDUPLICATE&blockName="+document.forms[0].strBlockName.value+"&storeId="
		+document.forms[0].strHospital.value+"&mode="+mode
		+"&buildName="+document.forms[0].strBuildingName.value+"&floorName="+document.forms[0].strFloorName.value
		+"&roomName="+document.forms[0].strRoomName.value;
	}
	else if(mode=="2")
	{
		var url = "RoomMstCNT.cnt?hmode=CHKDUPLICATE&blockName="+document.forms[0].strBlockName.value+"&storeId="
		+document.forms[0].strHospital.value+"&mode="+mode+"&roomId="+document.forms[0].strRoomId.value
		+"&buildName="+document.forms[0].strBuildingName.value+"&floorName="+document.forms[0].strFloorName.value
		+"&roomName="+document.forms[0].strRoomName.value;
	}
	ajaxFunction(url, "5");
	
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