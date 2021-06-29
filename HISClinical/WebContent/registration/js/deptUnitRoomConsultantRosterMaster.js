window.history.forward()

function checkRoomSelected(mode)
{
var valid=false
	if(comboValidation(document.getElementsByName('roomCode')[0],'Room'))
		{
		valid=true
		submitForm(mode)
		}			
		
	return valid		
}


function addRowData(forDay){
	//alert("Inside AddRow");
	//alert("for day="+forDay)
	document.getElementsByName("addRow")[0].value = forDay;
	submitForm("ADD_ROSTER_ROW");
}

function deleteRowData(forDay_row){
	//alert("deleteRow");
	document.getElementsByName("removeRow")[0].value = forDay_row;
	submitForm("REMOVE_ROSTER_ROW");
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
		alert("Shift Times are overlapping!")
		return false;
	}	   
}
function getDiRi(obj,strIdType)
{
	var strCheckedVal=obj.value;	
	var indx=strCheckedVal.indexOf("_") 	
 	var di=strCheckedVal.substr(0,indx)
 	var ri=strCheckedVal.substr(indx+1,strCheckedVal.length-indx)
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
 var shiftName="shift("+(noOfShift-1)+")";
 var finalVal; 
 
 for(i=initVal;i<document.getElementsByName(shiftName).length;i++)
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
 		shiftName="shift("+(j)+")"; 
 		if(document.getElementsByName(shiftName)[i].checked==true)
 		{ 			 
 			if((j+1)!=noOfShift)
 			{
 				for(k=(j+1);k<noOfShift;k++)
 				{
 					var tempShiftName="shift("+(k)+")";
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


function shiftselect(this_shift)
{	
	s_name = this_shift.name ;
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


		var checkBoxSelectAllValue=obj.value;
		
		
		var consultantRosterFor1stWeek=document.getElementsByName("consultantRosterFor1stWeek");
		var consultantRosterFor2ndWeek=document.getElementsByName("consultantRosterFor2ndWeek");
		var consultantRosterFor3rdWeek=document.getElementsByName("consultantRosterFor3rdWeek");
		var consultantRosterFor4thWeek=document.getElementsByName("consultantRosterFor4thWeek");
		var consultantRosterFor5thWeek=document.getElementsByName("consultantRosterFor5thWeek");
	
	//if selected	
	if(obj.checked){		
		var flag=true;
		}
		else//if unselected
	if(!obj.checked){		
		var flag=false;
		}			
		
		//alert(flag);
		
		selectAllCheckBoxes(checkBoxSelectAllValue,consultantRosterFor1stWeek,flag);
		selectAllCheckBoxes(checkBoxSelectAllValue,consultantRosterFor2ndWeek,flag);
		selectAllCheckBoxes(checkBoxSelectAllValue,consultantRosterFor3rdWeek,flag);
		selectAllCheckBoxes(checkBoxSelectAllValue,consultantRosterFor4thWeek,flag);
		selectAllCheckBoxes(checkBoxSelectAllValue,consultantRosterFor5thWeek,flag);
		
}

function selectAllCheckBoxes(checkBoxSelectAllValue,consultantRosterForaWeek,flag){

	for(var i=0;i < consultantRosterForaWeek.length ;i++){
		
			var a=parseInt(checkBoxSelectAllValue.split("_")[0]);
			var b=parseInt(checkBoxSelectAllValue.split("_")[1]);
			var c=parseInt(consultantRosterForaWeek[i].value.split("_")[0]);
			var d=parseInt(consultantRosterForaWeek[i].value.split("_")[1]);
			
		//alert("a--"+a+"--c--"+c);
		//alert("b--"+b+"--d--"+d);
			
			
			if(a==c && b==d){
			//	alert("hiii")
				consultantRosterForaWeek[i].checked=flag;
						}
				
			}

}





function compareDatewithforeDate(d1,d2,mode,l1,l2){
 //alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
//alert("inside first if of copmparedate "+compareDate(d1,d2, mode));
 //alert("return compareDate(d1,d2, mode)"+compareDate(d1,d2, mode))
 if(compareDate(d1,d2, mode)){
    //alert("valid Date");
		valid = true;
	}
 else {
	 //alert(l1+" should be greater than or equal to  "+l2);
	valid = false;
	}
} 

else
valid=false;
//alert("valid    "+valid);
return valid;
}

	function validateIt(){
		//alert("Validate");
			if(
			comboValidation(document.getElementsByName('departmentCode')[0],"Department") &&
			comboValidation(document.getElementsByName('departmentUnitCode')[0],"Unit") &&
			comboValidation(document.getElementsByName('roomCode')[0],"Room Name") && 
			comboValidation(document.getElementsByName('consultantEmpId')[0],"Consultant")
			
			  )
				return true;
			else
				return false;
	}
	
	function confirmEcecuteRoster()
	{
	 var answer = confirm ("This will execute the roster for all the departments, \n Are you sure want to do this")
	 if(answer==true)
	 	submitForm('EXECUTE_ROSTER');	  
	}
	
	function clearForm(){
		//alert("clear")
		var elements=document.forms[0].elements
		for(var i=0;i<elements.length;i++){
			if(elements[i].type=="checkbox" && elements[i].disabled==false){
				//alert(elements[i].value)
				elements[i].checked=false;		
			
			}
		}
	}
	

function showLegends()
{
document.getElementById("legendId").style.display="block";
}


function hideLegends()
{
document.getElementById("legendId").style.display="none";
}

function changeRoom(){
document.getElementsByName("consultantEmpId")[0].value="-1";
submitForm('GET_UNIT_ROSTER');
}