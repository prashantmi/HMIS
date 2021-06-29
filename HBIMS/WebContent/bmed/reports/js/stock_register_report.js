function changeStatus(value)
{
	if(value=="2")
	{
		document.getElementById("statusRowId").style.display="none";
	}
	else
	{
		document.getElementById("statusRowId").style.display="";
	}
	
}
function getItemNameCombo()
{	
    //alert("Store ID:::"+document.forms[0].strStoreId.value+":::Dept ID:::"+document.forms[0].strDeptId.value);	
   var url="StockRegisterReportCNT.cnt?hmode=GETITEMNAMECOMBO&strDeptId="+document.forms[0].strDeptId.value;
   ajaxFunction(url,"1");
   
  
} 

function getAjaxResponse(res,mode)
{	
	   var objVal;
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
         return;
       } 
       
    
	 if(mode=="1")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("labNameId");
       	            //alert(res);
       	            itemParaObj.innerHTML ="<select name = 'strLabCode' onchange='' class='comboNormal'>" + res + "</select>";
        }       
    }
    
    if(mode=="2")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
	     err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("complaintNoId");
       	           // alert(res);
       	            itemParaObj.innerHTML ="<select name = 'strComplaintNo' onchange='' class='comboNormal'>" + res + "</select>";
        }       
    }
    if(mode=="5")
	{	
	   var err = document.getElementById("errMsg");
	   var temp1 = res.split("####");
	   if(temp1[0] == "ERROR")
	   {
         err.innerHTML = temp1[1];	
       }
       else
       {
       	            var itemParaObj = document.getElementById("divItemCmbId");
       	            itemParaObj.innerHTML ="<select name = 'strItemId' onchange='' class='comboNormal'>" + res + "</select>";       
        }       
    }   
}

function validate()
{
if(document.forms[0].strHospitalText!=null)
{
document.forms[0].strHospitalText.value=document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text;
document.forms[0].strLabText.value=document.forms[0].strLabCode[document.forms[0].strLabCode.selectedIndex].text;
}

 var hisValidator = new HISValidator("equipmentReportFB");
		 //alert("inside validate1");
		/* hisValidator.addValidation("strDeptId", "dontselect=0", "Please Select Department Name" );
         
     	 hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		 hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		 hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		 hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
		       	 
        
         var retVal = hisValidator.validate();
         hisValidator.clearAllValidations(); */
retVal=true;
         if(retVal){
		document.forms[0].hmode.value = "SHOWRPT";
		
			if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
			{
				document.forms[0].target = "_self";
			}else
			{
				document.forms[0].target = "_blank";
			}
		document.forms[0].submit();
		}else{
		return false;
	}
}	
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}




function onLoadPage()
{
	document.forms[0].strIsFooter.checked = true;
	document.forms[0].strDeptId.value = "0";
	document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value;
	document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value;
	
}



function getStoreName()
{
	var mode = "GETSTORENAME"
	var url = "StockRegisterReportCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"1");
}
function getComplaintNumber()
{
	var mode = "GETCOMPLAINTNUMBER"
	var url = "StockRegisterReportCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value+"&labId="+document.forms[0].strLabCode.value;
	
	ajaxFunction(url,"2");
}
function changeVisibility(obj)
{
	getComplaintNumber();
	var selectedValue=obj.value;
	
	
	if(selectedValue=="-1")
	{
		
		document.getElementById("complaintTableId").style.display='none';
		document.getElementById("dateTableId").style.display='none';
		//document.getElementById("toDateRowId").style.display='none';
	}
	
	else if(selectedValue=="Textual")
	{
		
		document.getElementById("complaintTableId").style.display='block';
		document.getElementById("dateTableId").style.display='none';
		//document.getElementById("toDateRowId").style.display='none';
	}
	else
	{
	
		
		document.getElementById("complaintTableId").style.display='none';
		document.getElementById("dateTableId").style.display='block';
		//document.getElementById("toDateRowId").style.display='block';
	}
		
}
function getItemNameOnBasisOfStore()
{
	 var url="StockRegisterReportCNT.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
	 ajaxFunction(url,"5");	
}
  