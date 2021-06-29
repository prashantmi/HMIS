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

        var url ="MaintAndWarrantyDeskConfigMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
 		ajaxFunction(url,"1");		


}


function getMemberDetails(index)
{
 //alert("values are"+document.getElementById("strMemberType"+i).value);	
 var mode="MemberDetails";
 //alert(document.getElementById("strMemberType"+index).value)
 //alert("Index is::"+index);
 document.getElementById("currentRowIndex").value = index;
 
if((document.getElementById("strMemberType"+index).value) == "2" )
 {
//alert("inside internal");
 var url = "EquipmentAuctionAndCondemnationDeskCNT.cnt?hmode="+mode;
 	ajaxFunction(url,"3");
 } 
 if((document.getElementById("strMemberType"+index).value) == "1" )
 {
 	//alert("inside external");
 document.getElementById("strComMemberNameDiv"+index).innerHTML="<input type=\"text\" name=\"strComMemberName\" id=\"strComMemberName"+index+"\"/>"; 
document.getElementById("strComMemberId"+index).value=0;
document.getElementById("strComMemberDesign"+index).value= 0;
 } 
}
function getEmpcmb()
{
        var mode = "EMPCOMBO";
        var url ="MaintAndWarrantyDeskConfigMstCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strUserId.value;
 		ajaxFunction(url,"2");		

}
 function getUserId(rowIndex)
 {
 	//alert("hii");
 	var strValue = document.getElementById("strComMemberName"+rowIndex).value;
 	var strTemp = strValue.split("@");
 	document.getElementById("strComMemberId"+rowIndex).value= strTemp[0];
 	document.getElementById("strComMemberDesign"+rowIndex).value= strTemp[1];
  }

 function getCommitteeDtl()
 {
 // alert("hii");
  var mode="GetCommitteeMemberDetails";
 var url = "EquipmentAuctionAndCondemnationDeskCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strCommitteeName.value;
   		ajaxFunction(url,"4");		
  

 }

function getAjaxResponse(res,mode)
{

      var err = document.getElementById("strErrMsg");
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
			document.getElementById("LabNameDiv").innerHTML="<select name ='strLabName' class='comboNormal' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("LabNameDiv");
			objVal.innerHTML = "<select name ='strLabName' class='comboNormal' >"+res+"</select>";		
		}
	}
	if(mode=="2")
	{
		if(res=="")
		{
			document.getElementById("EmpNameDIv").innerHTML="<select name ='strEmpID' class='comboNormal' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("EmpNameDIv");
			objVal.innerHTML = "<select name ='strEmpID' class='comboNormal' >"+res+"</select>";		
		}
	}	
		
	if(mode=="3")
	{ var rowIndex= document.getElementById("currentRowIndex").value;
		if(res=="")
		{
			document.getElementById("strComMemberNameDiv"+rowIndex).innerHTML="<select name ='strComMemberName' class='comboNormal' ><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("strComMemberNameDiv"+rowIndex);
			objVal.innerHTML = "<select name ='strComMemberName' id='strComMemberName"+rowIndex+ "' class='comboNormal' onchange=\"getUserId('"+rowIndex+"');\">"+res+"</select>";		
		}
	}
	if(mode=="4")
	{ 
	//alert(res);
		if(res=="")
		{
			document.getElementById("committeedtl").innerHTML="No record Found";
		}
		else
		{
			var objVal= document.getElementById("committeedtl");
			objVal.innerHTML = res;		
		}
	}
}

function validate1()
{   

     
              var hisValidator = new HISValidator("stCommitteDesk"); 
            //  hisValidator.addValidation("strHospital","dontselect=0","Please Select Hospital Name");
            //  hisValidator.addValidation("strLabName","dontselect=0","Please Select Lab Name");
	         
	        //  hisValidator.addValidation("strUserID","dontselect=0","Please Select User Name");
            //  hisValidator.addValidation("strEmpID","dontselect=0","Please Select Emp Name");
	       //   hisValidator.addValidation("strEvent","dontselect=0","Please Select Event Type");
	      //  hisValidator.addValidation("strTestName","req", "Test Name is a Mandatory Field" );
           //   hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a Mandatory Field");
          //  hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
            
 // var retVal = hisValidator.validate(); 
var retVal = true;
          if(retVal)
          {
                 	   document.forms[0].hmode.value = "SaveReviewSchdule";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}
