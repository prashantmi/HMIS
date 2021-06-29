var roster={	
		onchange_departmentCode:function()
		  {
			var objDepartment =document.getElementsByName("patAddStateCode")[0];
			var departmentCode = objState.options[objState.selectedIndex].value;
			var action 		= "/HISRegistration/registration/putUnitDepartmentUnitRosterAction.action?departmentCode="+departmentCode;
					
			
			alert(action);
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							
							patientSearch.processGeneralNode(elementName,elementNode);
						}		
					
							
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('onchange_departmentCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }});
		  },
		  processGeneralNode:function(elementName,elementNode)
		  {
			var optionText="<option value='-1'>Select Value</option>";
			
			if(elementNode!=null){
				for(var i=0;i<elementNode.childNodes.length;i++ )
				{
					var optionNode=elementNode.childNodes[i];
					optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
					
				}
			}
			if(elementName=="patRefGnctdHospitalDept"){
				optionText+="<option value='0'>Other</option>";
			}
			
			if(document.getElementsByName(elementName).length!=0)
					document.getElementsByName(elementName)[0].innerHTML=optionText;
			
		  }
		
};



function getUnit()
{
	document.forms[0].action="putUnitDeptUnitRosterMst.action";
	document.forms[0].submit();
}
function getRoom()
{
	document.forms[0].action="putRoomDeptUnitRosterMst.action";
	document.forms[0].submit();
}

function getRoster()
{
	if(document.getElementsByName("rosterModel.strRoomCode")[0].value!="-1")
	{
		document.forms[0].action="putRosterDeptUnitRosterMst.action";
		document.forms[0].submit();
	}
}

function saveRoster()
{
	
	if(checkCombo() && validateIt())
	{
		document.forms[0].action="saveDeptUnitRosterMst.action";
		document.forms[0].submit();
	}
}

function submitCancel()
{
	document.getElementsByName("rosterModel.strDeptCode")[0].value="-1";
	document.forms[0].action="initDeptUnitRosterMst.action";
	document.forms[0].submit();
}

function submitCancelList()
{
	document.getElementsByName("rosterModel.strDeptCode")[0].value="-1";
	document.forms[0].action="cancelListDeptUnitRosterMst.action";
	document.forms[0].submit();
}

function addRowData(forDay)
{
	document.getElementsByName("addRow")[0].value = forDay;
	document.forms[0].action="addRosterRowDeptUnitRosterMst.action";
	document.forms[0].submit();

}

function deleteRowData(forDay_row)
{
	document.getElementsByName("removeRow")[0].value = forDay_row;
	document.forms[0].action="removeRosterRowDeptUnitRosterMst.action";
	document.forms[0].submit();
}

function confirmExecuteRoster()
{
 	var answer = confirm ("Are you sure to Continue ?");
 	if(answer==true)
 	{
 		document.forms[0].action="executeRosterDeptUnitRosterMst.action";
 		document.forms[0].submit();
 		submitForm('EXECUTE_ROSTER');	
 	}
}

function clearForm()
{
	var elements=document.forms[0].elements;
	for(var i=0;i<elements.length;i++){
		if(elements[i].type=="checkbox"){
			elements[i].checked=false;			
		}
	}
	$("#rosttable").hide();
	$('[name="rosterModel.strDeptCode"]')[0].value="-1";
	$('[name="rosterModel.strUnitCode"]')[0].value="-1";
	$('[name="rosterModel.strRoomCode"]')[0].value="-1";
}
//By Waris for roster delete on Aug'18
function submitDelete()
{	
	if(checkCombo() && validateIt())
	{
		var deptNameObj = $('[name="rosterModel.strDeptCode"]')[0];
		var deptName= deptNameObj.options[deptNameObj.selectedIndex].text;
		var consent = confirm("\t\t\t\tALERT!!!\nYou are going to delete the roster for "+deptName+"\n Press \"OK\" to continue\n");
	
		document.forms[0].action="deleteDeptUnitRosterMst.action";
		if(consent)
			document.forms[0].submit();
		
	}
	//alert("inside submit delete")
}

function checkCombo()
{
	var isValid=false;
	if(document.getElementsByName("rosterModel.strDeptCode")[0].value=="-1")
		alert("Please Select Department..!");
	else if(document.getElementsByName("rosterModel.strUnitCode")[0].value=="-1")
		alert("Please Select Unit..!");
	else if(document.getElementsByName("rosterModel.strRoomCode")[0].value=="-1")
		alert("Please Select Room..!");
	else
		isValid=true;
	
	return isValid;

}

function validateIt()
{
	var chkCnt = 0;
	var isValid=false;
	var elements=document.forms[0].elements;
	for(var i=0;i<elements.length;i++){
		if(elements[i].type=="checkbox" && elements[i].checked==true){
			chkCnt++;
		}
	}
	//alert(chkCnt);
	if(!chkCnt>0)
	{
		alert("Please Choose the Roster Details..!");
	}
	else
		isValid=true;
		
	if(isValid)
		isValid=checkShiftWeekOfMonthConsistency();

	return isValid;	
		
}

function shiftselect(this_shift)
{	
	s_name = this_shift.name ;
	//alert(s_name);
	var arr_s_value = this_shift.value.split("_");
	s_value = arr_s_value[0];
	s__shift_value = arr_s_value[1];
	
	var len;
	var isValid = true;
	count=0;
	len=document.getElementsByName(s_name).length;	
	var i=0;
	var j=0;
	var isValid = true ;
	if ( this_shift.checked == true ) 
	{	
		this_shift.checked = false ;
		var abc_array = document.getElementsByName(s_name);

		for(i=0;i<len;i++)
    	{ 
			var abc  = abc_array[i].value.split("_");     	
			if(abc[0]==s_value && abc_array[i].checked == true )
       		{       
         		alert ( "Only one shift can be selected for the day"); 
				isValid=false;
				break;
       	}
		
	}

	if(isValid)
	{
		this_shift.checked = true ;
	}
	}
	return;
}
function selectAllWeek(obj){
	if(obj.checked){
		var di=obj.value.split("_")[0];
		var ri=obj.value.split("_")[1];
		//alert("ri "+ri)
		var weekIndex="";
		var week1stOfMonth=document.getElementsByName("rosterModel.week1stOfMonth");
		for(var i=0;i<week1stOfMonth.length;i++){
			var a=week1stOfMonth[i].value.split("_")[0];
			var b=week1stOfMonth[i].value.split("_")[1];
			//alert(a==di)
			//alert("b "+ b+"b")
			///alert(b == ri)
			b=b.substring(0,1);
			if((a==di) && (b==ri)){
				//alert("equal")
				weekIndex=i;
				break;
			}
		}
		//alert(i)
		document.getElementsByName("rosterModel.week1stOfMonth")[i].checked=true;
		document.getElementsByName("rosterModel.week2ndOfMonth")[i].checked=true;
		document.getElementsByName("rosterModel.week3rdOfMonth")[i].checked=true;
		document.getElementsByName("rosterModel.week4thOfMonth")[i].checked=true;
		document.getElementsByName("rosterModel.week5thOfMonth")[i].checked=true;
		
	}
	else{
		var di=obj.value.split("_")[0];
		var ri=obj.value.split("_")[1];
		//alert("ri "+ri)
		var weekIndex="";
		var week1stOfMonth=document.getElementsByName("rosterModel.week1stOfMonth");
		for(var i=0;i<week1stOfMonth.length;i++){
			var a=week1stOfMonth[i].value.split("_")[0];
			var b=week1stOfMonth[i].value.split("_")[1];
			//alert(a==di)
			//alert("b "+ b+"b")
			///alert(b == ri)
			b=b.substring(0,1);
			if((a==di) && (b==ri)){
				//alert("equal")
				weekIndex=i;
				break;
			}
		}
		//alert(i)
		document.getElementsByName("rosterModel.week1stOfMonth")[i].checked=false;
		document.getElementsByName("rosterModel.week2ndOfMonth")[i].checked=false;
		document.getElementsByName("rosterModel.week3rdOfMonth")[i].checked=false;
		document.getElementsByName("rosterModel.week4thOfMonth")[i].checked=false;
		document.getElementsByName("rosterModel.week5thOfMonth")[i].checked=false;
	}
}
function getDiRi(obj,strIdType)
{
	var strCheckedVal=obj.value;	
	var indx=strCheckedVal.indexOf("_"); 	
 	var di=strCheckedVal.substr(0,indx);
 	var ri=strCheckedVal.substr(indx+1,strCheckedVal.length-indx);
 	//alert("di "+ di + " \n ri "+ri);
 	if(strIdType=="di")
 		return di;
 	if(strIdType=="ri")
 		return ri; 	
}
function checkShiftTimeClash(obj)
{
	if(obj.checked==false)
 	return false;
 	var noOfShift=document.getElementsByName("startTime").length;
 	//alert("noOfShift  "+ noOfShift);
 	var i;
 	var j;
 	var k=0;  
 	var di=getDiRi(obj,"di");
	var ri=getDiRi(obj,"ri");  
	var initVal=di-1;
 	var tempri=0;
 	var shiftName="rosterModel.shift["+(noOfShift-1)+"]";
 	var finalVal; 
 

 	//for(i=initVal;i<document.getElementsByName(shiftName).length;i++)
 	for(i=initVal;i<document.getElementById(shiftName).length;i++)
 	{
 		var tempDi=getDiRi(document.getElementsByName(shiftName)[i],"di");
 	  	//alert("tempDi  "+ tempDi +"\n di =" + di);
   	 	if(parseInt(di)==parseInt(tempDi))
   	 	{     	 		 	
   	 		tempri=getDiRi(document.getElementsByName(shiftName)[i],"ri");   	     	 	
   	 	}
   	 	else
	   	 	if(tempDi>di)
   	 			break;	 	
   	
 	} 
 	finalVal=parseInt(di)+parseInt(tempri);
 	//alert("initVal=" + initVal + "\n finalVal=" + finalVal);
 	//var arr=new Array(parseInt(noOfShift)*parseInt(finalVal));
	  
 	for(i=initVal;i<finalVal;i++)
 	{
	 	for(j=0;j<noOfShift;j++)
	 	{
 			shiftName="rosterModel.shift["+(j)+"]"; 
 			if(document.getElementsByName(shiftName)[i].checked==true)
 			{ 	
 				
	 			if((j+1)!=noOfShift)
 				{
	 				for(k=(j+1);k<noOfShift;k++)
 					{
	 					var tempShiftName="rosterModel.shift["+(k)+"]";
 						if(document.getElementsByName(tempShiftName)[i].checked==true)
 						{	
	 						if(checkTimeClash(document.getElementsByName("startTime")[j].value,document.getElementsByName("endTime")[j].value,document.getElementsByName("startTime")[k].value,document.getElementsByName("endTime")[k].value)==false)
 							obj.checked=false;
 						}
 					}	
 				}			
 			}
 		}
 	}
}

function checkTimeClash(startTime1,endTime1,startTime2,endTime2)
{	
	var StartTimeHr1=parseInt(getTimehrMin(startTime1,"hr"));
	var StartTimeMin1=parseInt(getTimehrMin(startTime1,"min"));
	var StartTimeHr2=parseInt(getTimehrMin(startTime2,"hr"));
	var StartTimeMin2=parseInt(getTimehrMin(startTime2,"min"));	
	var endTimeHr1=parseInt(getTimehrMin(endTime1,"hr"));
	var endTimeMin1=parseInt(getTimehrMin(endTime1,"min"));
	var endTimeHr2=parseInt(getTimehrMin(endTime2,"hr"));
	var endTimeMin2=parseInt(getTimehrMin(endTime2,"min"));	
	/*alert("StartTimeHr1 "+ StartTimeHr1 +" StartTimeMin1 " + StartTimeMin1+
	"\n StartTimeHr2 "+ StartTimeHr2 +" StartTimeMin2 " + StartTimeMin2+
	"\nendTimeHr1 "+ endTimeHr1 +" endTimeMin1 " + endTimeMin1+
	"\nendTimeHr2 "+ endTimeHr2 +" endTimeMin2 " + endTimeMin2);
	*/
	var status =0;
	if(endTimeHr1<=StartTimeHr2)
	{
		//alert("check1");
		if(endTimeHr1==StartTimeHr2)
		{
			//alert("check2");
			if(endTimeMin1<=StartTimeMin2)
			{
				//alert("check3");
				status=1;
			}
			else
			{
				//alert("check4");
				status=0;
			}
		}
		else
		{
			//alert("check5");
			status=1;
		}
	}
	else
	{
		//alert("check6");
		status=0;
	}
	
	if(status==1)	
	{
		//alert("time is not clashing")
		return true;
	}
	else
	{
		alert("Shift Times are overlapping!");
		return false;
	}	   
}

function getTimehrMin(strTime,hrMin)
{
	var lenstr=strTime.length;
	var hours;
	var minutes;
	if(lenstr==5)
	{
		hours=strTime.substring(0,2);
		minutes =strTime.substring(5,3);
	}
	if(lenstr==4)
	{
		hours=strTime.substring(0,1);
		minutes=strTime.substring(2,4);
	}
	//alert("length" + lenstr);
	//alert("hours="+ hours + " \n minutes="+ minutes);

	if(hrMin=="hr")
	{
		//alert("hr" + hours)
		return hours;
	}	
		
	if(hrMin=="min")
	{
	    //alert("min" + minutes)
		return minutes;		
	}	
}

