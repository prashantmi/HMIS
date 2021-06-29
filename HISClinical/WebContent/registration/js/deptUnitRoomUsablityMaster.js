window.history.forward()




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
	if(obj.checked){
		var di=obj.value.split("_")[0]
		var ri=obj.value.split("_")[1]
		//alert("ri "+ri)
		var weekIndex=""
		var week1stOfMonth=document.getElementsByName("week1stOfMonth")
		for(var i=0;i<week1stOfMonth.length;i++){
			var a=week1stOfMonth[i].value.split("_")[0]
			var b=week1stOfMonth[i].value.split("_")[1]
			//alert(a==di)
			//alert("b "+ b+"b")
			///alert(b == ri)
			b=b.substring(0,1)
			if((a==di) && (b==ri)){
				//alert("equal")
				weekIndex=i;
				break;
			}
		}
		//alert(i)
		document.getElementsByName("week1stOfMonth")[i].checked=true;
		document.getElementsByName("week2ndOfMonth")[i].checked=true;
		document.getElementsByName("week3rdOfMonth")[i].checked=true;
		document.getElementsByName("week4thOfMonth")[i].checked=true;
		document.getElementsByName("week5thOfMonth")[i].checked=true;
		
	}
	else{
		var di=obj.value.split("_")[0]
		var ri=obj.value.split("_")[1]
		//alert("ri "+ri)
		var weekIndex=""
		var week1stOfMonth=document.getElementsByName("week1stOfMonth")
		for(var i=0;i<week1stOfMonth.length;i++){
			var a=week1stOfMonth[i].value.split("_")[0]
			var b=week1stOfMonth[i].value.split("_")[1]
			//alert(a==di)
			//alert("b "+ b+"b")
			///alert(b == ri)
			b=b.substring(0,1)
			if((a==di) && (b==ri)){
				//alert("equal")
				weekIndex=i;
				break;
			}
		}
		//alert(i)
		document.getElementsByName("week1stOfMonth")[i].checked=false;
		document.getElementsByName("week2ndOfMonth")[i].checked=false;
		document.getElementsByName("week3rdOfMonth")[i].checked=false;
		document.getElementsByName("week4thOfMonth")[i].checked=false;
		document.getElementsByName("week5thOfMonth")[i].checked=false;
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
			comboValidation(document.getElementsByName('roomCode')[0],"Room Name")
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
		
		var roomUsabilityFor1stWeek=document.getElementsByName("roomUsabilityFor1stWeek")
		var roomUsabilityFor2ndWeek=document.getElementsByName("roomUsabilityFor2ndWeek")
		var roomUsabilityFor3rdWeek=document.getElementsByName("roomUsabilityFor3rdWeek")
		var roomUsabilityFor4thWeek=document.getElementsByName("roomUsabilityFor4thWeek")
		var roomUsabilityFor5thWeek=document.getElementsByName("roomUsabilityFor5thWeek")
		
		
		for(var i=0; i < roomUsabilityFor1stWeek.length; i++)
			{
			roomUsabilityFor1stWeek[i].value="0";
			}
		
		for(var i=0; i < roomUsabilityFor2ndWeek.length; i++)
			{
			roomUsabilityFor2ndWeek[i].value="0";
			}
		
		for(var i=0; i < roomUsabilityFor3rdWeek.length; i++)
			{
			roomUsabilityFor3rdWeek[i].value="0";
			}
		
		for(var i=0; i < roomUsabilityFor4thWeek.length; i++)
			{
			roomUsabilityFor4thWeek[i].value="0";
			}
		
		
		for(var i=0; i < roomUsabilityFor5thWeek.length; i++)
			{
			roomUsabilityFor5thWeek[i].value="0";
			}
		
		
		
	}
	

