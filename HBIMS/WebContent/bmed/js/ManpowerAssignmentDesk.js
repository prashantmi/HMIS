


//check time
function checkTime(field) { var errorMsg = ""; // regular expression to match required time format 
re = /^(\d{1,2}):(\d{2})(:00)?([ap]m)?$/; 
if(field.value != '') { 
	if(regs = field.value.match(re)) {
		if(regs[4]) { 
			// 12-hour time format with am/pm 
			if(regs[1] < 1 || regs[1] > 12) { 
				errorMsg = "Invalid value for hours: " + regs[1]; 
				}; 
			} else {
				// 24-hour time format 
				if(regs[1] > 23) { 
					errorMsg = "Invalid value for hours: " + regs[1]; 
					}; 
				} 
		if(!errorMsg && regs[2] > 59) {
			errorMsg = "Invalid value for minutes: " + regs[2]; 
			}; 
		} else { 
			errorMsg = "Invalid time format: " + field.value; 
			}; 
	} 
	if(errorMsg != "") { 
		alert(errorMsg); 
		field.focus(); 
		return false; 
		} 
	return true; 
	}



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

function addrow(i)
   {
	     var temp=0,j=0;
	    temp=document.forms[0].flag.value;
	    /*if(temp=='undefined' || isNaN(temp)) 
	    {
	    	alert("inside if:]");
	    	temp=0;
	    }*/
	    temp++;
	    document.forms[0].flag.value=temp;
	    document.forms[0].hmode.value = "NEWROW";
	    document.forms[0].submit();
   }
 function delrow(i)
   {
		var temp=0,j=0,total=0.00,k=0;
	    temp=document.forms[0].flag.value;
	   	temp--;
		document.forms[0].flag.value=temp;
		document.forms[0].hmode.value="REMROW";
		document.forms[0].tselected.value=i;
	    document.forms[0].submit();
   }


function submitforValues()
{alert(document.getElementById("strLabName").value);
	document.getElementById("hmode").value="ADD";
document.forms[0].submit();	
}

function getLabCmb()
{
        var mode = "LABCOMBO";
		alert("Ok");
        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strHospital.value;
 		ajaxFunction(url,"1");		

}
function getBuildCmb()
{
        var mode = "BUILDCOMBO";

        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo="+document.forms[0].strBlockName.value;
 		ajaxFunction(url,"2");		

}

function getFloorCmb()
{
       var mode = "FLOORCOMBO";
       // var paraValue=strBuildingName
      // alert(document.forms[0].strBlockName.value+"^"+document.forms[0].strBuildingName.value);//+document.forms[0].stBuildingName.value);
       var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strBlockName.value+"@"+document.forms[0].strBuildingName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"3");		

}
function getRoomCmb()
{
	 var mode = "ROOMCOMBO";
       // var paraValue=strBuildingName
      // alert(document.forms[0].strBlockName.value+"^"+document.forms[0].strBuildingName.value+"@"+document.forms[0].strFloorName.value);//+document.forms[0].stBuildingName.value);
       var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strBlockName.value+"@"+document.forms[0].strBuildingName.value+"@"+document.forms[0].strFloorName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"4");
}

function getEquipGroupCmb()
{
	 var mode = "EQUIPGROUPCOMBO";
       // var paraValue=strBuildingName
       alert("ok");
        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strLabName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"5");
}
function getEquipSubGroupCmb()
{
	 var mode = "EQUIPSUBGROUPCOMBO";
       // var paraValue=strBuildingName
       alert("ok");
        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strLabName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"6");
}
function getEquipNameCmb()
{
	 var mode = "EQUIPNAMECOMBO";
       // var paraValue=strBuildingName
      alert("ok");
        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strLabName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"7");
}
function getEquipModelNameCmb()
{
	 var mode = "EQUIPMODELNAMECOMBO";
       // var paraValue=strBuildingName
       alert("ok");
        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strLabName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"8");
}
function getSupNameCmb()
{
	 var mode = "SUPPLIERNAMECOMBO";
       // var paraValue=strBuildingName
       alert(document.forms[0].strLabName.value);
        var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.forms[0].strLabName.value;//+"^"+document.forms[0].stBuildingName.value;
 	   ajaxFunction(url,"9");
}
function UpdateValues()
{
getEquipGroupCmb();	
getEquipSubGroupCmb();
getEquipNameCmb();
getEquipModelNameCmb();
getSupNameCmb();
}
function getDownTimeDetail()
{
	var mode="GETDOWNTIMEDETAIL";
	alert(document.getElementById('strDownTimeId').value);
    var url ="ManpowerAssignmentDeskCNT.cnt?hmode="+mode+"&parentcombo1="+document.getElementById('strDownTimeId').value;
    alert(url);
    ajaxFunction(url,"10");
	
	
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
			document.getElementById("LabNameDiv").innerHTML="<select name ='strLabName' class='comboNormal' onchange='UpdateValues()'><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("LabNameDiv");
			objVal.innerHTML = "<select name ='strLabName' class='comboNormal' onchange='UpdateValues()'>"+res+"</select>";		
		}
	}
	if(mode=="2")
	{
		if(res=="")
		{
			document.getElementById("BuildDivID").innerHTML="<select name ='strBuildingName' id='strBuildingName' class='comboNormal' onchange='getFloorCmb()'><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("BuildDivID");
			objVal.innerHTML ="<select name='strBuildingName' class='comboNormal' onchange='getFloorCmb()'>"+res+"</select>";		
		}
	}
	
	if(mode=="3")
	{
		if(res=="")
		{
			document.getElementById("FloorDivId").innerHTML="<select name ='strFloorName' class='comboNormal' onchange='getRoomCmb()'><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("FloorDivId");
			objVal.innerHTML ="<select name='strFloorName' class='comboNormal' onchange='getRoomCmb()'>"+res+"</select>";		
		}
	}
	
	if(mode=="4")
	{
		if(res=="")
		{
			document.getElementById("RoomDivId").innerHTML="<select name ='strRoomName' class='comboNormal'><option value='0'>Data N/A</option></select>";
		}
		else
		{
			var objVal= document.getElementById("RoomDivId");
			objVal.innerHTML ="<select name='strRoomName' class='comboNormal'>"+res+"</select>";		
		}
	}
	if(mode=="5")
	{
		if(res=="")
		{
			//alert("in mode 5");
			document.getElementById("EquipmentGroupDiv").innerHTML="<select name ='strEquipGroup' class='comboNormal'><option value='0'>Data N/A</option></select>";
		}
		else
		{
	 		//alert("in mode 5");
	 		var objVal= document.getElementById("EquipmentGroupDiv");
			objVal.innerHTML ="<select name='strEquipGroup' class='comboNormal'>"+res+"</select>";		
		}
	}	
	if(mode=="6")
	{
		if(res=="")
		{
			//alert("in mode 6");
			document.getElementById("EquipmentSubGroupDiv").innerHTML="<select name ='strSubEquipGroup' class='comboNormal'><option value='0'>Data N/A</option></select>";
		}
		else
		{
	 		//alert("in mode 6");
	 		var objVal= document.getElementById("EquipmentSubGroupDiv");
			objVal.innerHTML ="<select name='strEquipSubGroup' class='comboNormal'>"+res+"</select>";		
		}
		
		
	}
	
	if(mode=="7")
	{
		if(res=="")
		{
			//alert("in mode 7");
			document.getElementById("EquipmentNameDiv").innerHTML="<select name ='strEquipName' class='comboNormal'><option value='0'>Data N/A</option></select>";
		}
		else
		{
	 		//alert("in mode 7");
	 		var objVal= document.getElementById("EquipmentNameDiv");
			objVal.innerHTML ="<select name='strEquipName' class='comboNormal'>"+res+"</select>";		
		}
		
		
	}

	if(mode=="8")
	{
		if(res=="")
		{
			//alert("in mode 8");
			document.getElementById("EquipmentModelDiv").innerHTML="<select name ='strEquipModel' class='comboNormal'><option value='0'>Data N/A</option></select>";
		}
		else
		{
	 		//alert("in mode 8");
	 		var objVal= document.getElementById("EquipmentModelDiv");
			objVal.innerHTML ="<select name='strEquipModel' class='comboNormal'>"+res+"</select>";		
		}
		
		
	}
	if(mode=="9")
	{
		if(res=="")
		{
			//alert("in mode 9");
			document.getElementById("SupplierDiv").innerHTML="<select name ='strSupplierName' class='comboNormal'><option value='0'>Data N/A</option></select>";
		}
		else
		{
	 	//	alert("in mode 9");
	 		var objVal= document.getElementById("SupplierDiv");
			objVal.innerHTML ="<select name='strSupplierName' class='comboNormal'>"+res+"</select>";		
		}
		
		
	}	
	
	if(mode=="10")
	{
		alert("In ajax");
		
		
	}			
}

function validate1(mode)
{   

     
             var hisValidator = new HISValidator("stManPowerAssignDeskName");
           
 			hisValidator.addValidation("strEmpName","dontselect=0","Please Select Employee Name");
 			hisValidator.addValidation("strDesignName","dontselect=0","Please Select Designation");
 			hisValidator.addValidation("strDepartmentName","dontselect=0","Please Select Department");
 			hisValidator.addValidation("strHemmUser","dontselect=0","Please Select User Name");
            
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


function validate2()
{   

     
             var hisValidator = new HISValidator("DownTimeDeskName");
             
             hisValidator.addValidation("strDownDate","req", "Date is a Mandatory Field!" );
             hisValidator.addValidation("strDownTime","req", "Time is a Mandatory Field!" );
             hisValidator.addValidation("strRemarks","req", "Remarks is a Mandatory Field!" );
             hisValidator.addValidation("strUserName","dontselect=0","Please Select Username");
            
 
            
  var retVal = hisValidator.validate(); 

          if(retVal)
          {
                 	   document.forms[0].hmode.value = "SAVE_Down_Time";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}


function validate3()
{   

     
             var hisValidator = new HISValidator("UpTimeDeskName");
              hisValidator.addValidation("strUpDate","req", "Date is a Mandatory Field!" );
             hisValidator.addValidation("strUpTime","req", "Time is a Mandatory Field!" );
             hisValidator.addValidation("strProbIndentifRemarks","req", "Please Enter Problem Identification." );
             hisValidator.addValidation("strRectificationRemarks","req", "Please Enter Rectification Remarks." );
             hisValidator.addValidation("strUptimeUserName","dontselect=0","Please Select Username");
 
             var retVal = hisValidator.validate(); 

          if(retVal)
          {  
        	  
        		 if(!checkTime(document.forms[0].strUpTime)) return false;
          
                 	   document.forms[0].hmode.value = "SAVE_UpTime";
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
function srlNO()
{
	var obj = document.getElementsByName("strSerialNo");
	var id = obj[obj.length-2].id;
//	obj[obj.length-2].value = id.substr(id.length-1,id.length);
	obj[obj.length-2].value = obj.length-1;
}

function delSrlNO()
{
	var obj = document.getElementsByName("strSerialNo");
	var j = 1;
	for(i=0;i<obj.length-1;i++)
	{
		obj[i].value = j++;
	}
}

function createAssignmentDetail()
{
	var emp = document.forms[0].strEmpNameOldCmb.value;
	emp = replaceAll(emp);
    emp = emp.split("#");
    
    var desg = document.forms[0].strDesignNameOldCmb.value;
	desg = replaceAll(desg);
    desg = desg.split("#");
    
    var dept = document.forms[0].strDepartmentOldCmb.value;
	dept = replaceAll(dept);
    dept = dept.split("#");
    
    var hemm = document.forms[0].strHemmUserOldCmb.value;
	hemm = replaceAll(hemm);
    hemm = hemm.split("#");
    
    var obj = document.getElementById("assignTable");
    var str1 = "";
    for(i=1;i<emp.length-1;i++)
    {
    	var row = obj.insertRow(obj.rows.length);
	    str1 =  "<td width='4%' colspan='1' class='LABEL'><div align='center'><input type='text' name='strSerialNo'"
					+ "id='strSerialNo1-"+i+"' readonly='readonly'  size='5' value = '" + i +"' ></div></td>"
					+ "<td width='19%' colspan='1' class='CONTROL'><div id='EmpDiv' align='center'><select name='strEmpName'"
					+ "id='strEmpName1-"+i+"'>" + emp[i] + "</select></div></td><td width='19%' colspan='1' class='CONTROL'>"
					+ "<div id='DesignatioDiv' align='center'><select name='strDesignName' id='strDesignName1-"+i+"'>"
					+ desg[i] + "</select></div></td><td width='27%' colspan='1' class='CONTROL'><div id='DepartmentDiv' align='center'>"
					+ "<select name='strDepartmentName' id='strDepartmentName1-"+i+"'>" + dept[i] + "</select></div></td>"
					+ "<td width='20%' colspan='1' class='CONTROL'><div id='DepartmentDiv' align='center'><select name='strHemmUser'"
					+ "id='strHemmUser1-"+i+"'>" + hemm[i] + "</select></div></td><td width='1%' colspan='1' class='CONTROL'><img"
					+ " src='../../hisglobal/images/minus.gif' style='cursor: pointer;' title='Delete Employee'"
					+ " OnClick='deleteRow('#delIndex#',1,1);delSrlNO();'></td>";
		row.innerHTML = str1;
	}
	
}
function createAssignmentViewDetail()
{
	var emp = document.forms[0].strEmpNameOldCmb.value;
    emp = emp.split("#");
    var desg = document.forms[0].strDesignNameOldCmb.value;
    desg = desg.split("#");
    var dept = document.forms[0].strDepartmentOldCmb.value;
    dept = dept.split("#");
    var hemm = document.forms[0].strHemmUserOldCmb.value;
    hemm = hemm.split("#");
    
    var obj = document.getElementById("assignTable");
	if(emp.length<=2)
	{
		document.getElementById("statusTR").style.display = "";
		return;
	}
    var str1 = "";
    for(i=1;i<emp.length-1;i++)
    {
    	var row = obj.insertRow(obj.rows.length);
	    str1 =  "<td width='4%' colspan='1' class='LABEL'><div align='center'><label name='strSerialNo'"
					+ "id='strSerialNo1-"+i+"'>" + i +"</label></div></td>"
					+ "<td width='19%' colspan='1' class='CONTROL'><div id='EmpDiv' align='center'><label name='strEmpName'"
					+ "id='strEmpName1-"+i+"'>" + emp[i] + "</label></div></td><td width='19%' colspan='1' class='CONTROL'>"
					+ "<div id='DesignatioDiv' align='center'><label name='strDesignName' id='strDesignName1-"+i+"'>"
					+ desg[i] + "</label></div></td><td width='27%' colspan='1' class='CONTROL'><div id='DepartmentDiv' align='center'>"
					+ "<label name='strDepartmentName' id='strDepartmentName1-"+i+"'>" + dept[i] + "</label></div></td>"
					+ "<td width='20%' colspan='1' class='CONTROL'><div id='DepartmentDiv' align='center'><label name='strHemmUser'"
					+ "id='strHemmUser1-"+i+"'>" + hemm[i] + "</label></div></td><td width='1%' colspan='1' class='CONTROL'></td>";
		row.innerHTML = str1;
	}
	
}
function replaceAll(str)
{
	var patt1 = /\^/g;
    str = str.replace(patt1,'"');
    return str;
}