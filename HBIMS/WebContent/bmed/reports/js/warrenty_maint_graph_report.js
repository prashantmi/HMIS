
function getItemNameCombo()
{	
    //alert("Store ID:::"+document.forms[0].strStoreId.value+":::Dept ID:::"+document.forms[0].strDeptId.value);	
   var url="WarrentyMaintGraphReportCNT.cnt?hmode=GETITEMNAMECOMBO&strDeptId="+document.forms[0].strDeptId.value;
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
       	  var itemParaObj = document.getElementById("labNameId");
       	  itemParaObj.innerHTML ="<select name = 'strLabCode' onchange='' class='comboNormal'>" + res + "</select>";
     }
     else if(mode=="2")
	 {	
       	  var itemParaObj = document.getElementById("complaintNoId");
       	  itemParaObj.innerHTML ="<select name = 'strComplaintNo' onchange='' class='comboNormal'>" + res + "</select>";
     }       
     else if(mode=="5")
	 {	
       	  var itemParaObj = document.getElementById("divItemCmbId");
       	  itemParaObj.innerHTML ="<select name = 'strItemId' onchange='' class='comboNormal'>" + res + "</select>";       
     }         
}

function validate()
{

		 var hisValidator = new HISValidator("warrentyMaintGraphReportFB");

//		 hisValidator.addValidation("strDeptCode", "dontselect=0", "Please Select Department Name" );
//		 hisValidator.addValidation("strLabCode", "dontselect=0", "Please Select Lab Name" );
         if(document.forms[0].strFromDate.value!="")
         {
		 	hisValidator.addValidation("strToDate", "date","Please Select To Date ");
         }
         if(document.forms[0].strToDate.value!="")
         {
		 	hisValidator.addValidation("strFromDate", "date","Please Select From Date");
         }
         if(document.forms[0].strToDate.value!="" && document.forms[0].strFromDate.value!="")
         {
		    hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
         }

		       	 
        
         var retVal = hisValidator.validate();
         hisValidator.clearAllValidations(); 
//		retVal=true;
         if(retVal)
         {
			document.forms[0].hmode.value = "SHOWRPT";
			var obj = document.forms[0].strDeptCode;
			document.forms[0].strStoreName.value = obj[obj.selectedIndex].text;
			document.forms[0].strLabName.value = document.forms[0].strLabCode[document.forms[0].strLabCode.selectedIndex].text;
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
	document.forms[0].hmode.value="unspecified";
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
	var url = "WarrentyMaintGraphReportCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value;
	ajaxFunction(url,"1");
}
function getComplaintNumber()
{
	var mode = "GETCOMPLAINTNUMBER"
	var url = "WarrentyMaintGraphReportCNT.cnt?hmode="+mode+"&deptId="+document.forms[0].strDeptCode.value+"&labId="+document.forms[0].strLabCode.value;
	
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
	 var url="WarrentyMaintGraphReportCNT.cnt?hmode=GETITEMNAME&flag="+0+"&storeId="+document.forms[0].strStoreId.value;
	 ajaxFunction(url,"5");	
}
  