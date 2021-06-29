var count=true;
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

        var url ="BlockMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
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
			document.getElementById("LabNameDiv").innerHTML="<select name ='strLabName' class='comboMax' ><option value='0'>Select Value</option></select>";
		}
		else
		{
			var objVal= document.getElementById("LabNameDiv");
			objVal.innerHTML = "<select name ='strLabName' class='comboMax' >"+res+"</select>";		
		}
	}
	if(mode=="2")
	{
		if(res!=0)
		{
			count = false;
			var tmpVal=document.getElementById("strBlockName").value;
			alert('Block Name "'+tmpVal.trim()+'" Already Exists');
			//document.getElementById("strBlockName").focus();
		}
		else
		{
			count = true;
		}	
	}	
}

function validate1()
{   
			if(!count)
			{
				var tmpVal=document.getElementById("strBlockName").value;
				alert('Block Name "'+tmpVal.trim()+'" Already Exists');
				return false;
			}     
             var hisValidator = new HISValidator("BlockMstFB");
             
             hisValidator.addValidation("strHospital","dontselect=0","Please Select Hospital Name");
             hisValidator.addValidation("strBlockName","req","Block Name is Mandatory Field");
	         hisValidator.addValidation("strUserID","dontselect=0","Please Select User Name");
             hisValidator.addValidation("strEmpID","dontselect=0","Please Select Emp Name");
	         hisValidator.addValidation("strEvent","dontselect=0","Please Select Event Type");
	         hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
                      
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

function chkDuplicateBlock(mode)
{
	
	var strBlockName = document.getElementById("strBlockName").value;
	if(strBlockName=="")
	{
		return false;
	}
	if(mode=="1")
	{
		var url = "BlockMstCNT.cnt?hmode=CHKDUPLICATE&blockName="+strBlockName.trim()+"&storeId="
		+document.forms[0].strHospital.value+"&mode="+mode+"&blockId=0";
	}
	else if(mode=="2")
	{
		var url = "BlockMstCNT.cnt?hmode=CHKDUPLICATE&blockName="+strBlockName.trim()+"&storeId="
		+document.forms[0].strHospital.value+"&mode="+mode+"&mode="+document.forms[0].strBlockid.value;
	}
	ajaxFunction(url, "2");
	
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
