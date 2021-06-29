window.onload=function(){
	getFetchedList();
	getAvailableList();

}
var checkShiftValid=true;

function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Billing category
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].shiftId;
		target = document.forms[0].selectedShiftId;	
	}

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
			{
			//alert("value--"+source.options[i].value)
		//	alert("check---"+validateShift(source.options[i].value))
			
	if(validateShift(source.options[i].value)==false)
			{
		//	alert("invalid")
		//	 moveLeftAll("1")
			 checkShiftValid=false;
		 break;
			 
			 
			}
			else
			{
		//	alert("valid")	
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;			
												
			}
	} 
}
//alert(checkShiftValid)
deleteThis(target,source);	
	
}

function moveRightAll(listNo)
{
	var source  = document.forms[0].shiftId;
	
	
	for(var i=0;i<source.length;i++)
	{
//	alert(i);
	//	alert(checkShiftValid);

		source.options[i].selected=true;
		
	
	 }
	 
	 for(var i=0;i<source.length;i++)
	{
	 
	 moveRightSingle("1");

//		alert(checkShiftValid);

		if(checkShiftValid==false)
			{
	//	moveLeftAll("1")
			//alert("Shift Timings are Overlapping");
			
			break;
			} 
	}	
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].shiftId;
		source = document.forms[0].selectedShiftId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].shiftId;
		source = document.forms[0].selectedShiftId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}

function MoveToSelected()
{
	// Select All Shift in Selected
	if(document.forms[0].selectedShiftId)
		for(var i=0;i<document.forms[0].selectedShiftId.length;i++)
			document.forms[0].selectedShiftId.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].shiftId)
	{	
		for(var i=0;i<document.forms[0].shiftId.options.length;i++)
			document.forms[0].shiftId.options[i].selected=false;
	}
}


function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateDayShift()
{
var dayFlag=false;
//alert('fn called')

for(var i=0;i<document.forms[0].selectedShiftId.length;i++)
	{
	
		var shiftArray=document.forms[0].selectedShiftId[i].value.split("@");
		
	//	alert(shiftArray[1])
		
		if(shiftArray[1]=="4")
				{
				dayFlag=true;
				break;
				}

	}
	
//alert("length----"+document.forms[0].selectedShiftId.length)
 //alert("dayFlag-----"+dayFlag)

if(document.forms[0].selectedShiftId.length > 1 && dayFlag==true)
	{
	alert("Day Shift Cannot be Mapped Together With Other Shifts");
	return false;
	}
	else
	return true;
	
}


function validateFinalSubmit()
{
var valid=false;
var validCheck=false;



if(document.forms[0].hmode.value=="ADD")
	{
		if( !comboValidation(document.getElementsByName('rosterTypeId')[0],"Roster Name"))
			{
			valid=true;
			validCheck=true;
			}
	}
	
	//alert("validCheck--"+validCheck)
	if(validCheck==false && document.forms[0].selectedShiftId.options.length==0 )
	{
		alert("Please Select: at Least One Shift");
		document.forms[0].selectedShiftId.focus();
		valid=true;
	}
	
//	alert("s-----"+validateDayShift()) 
	
	if(validateDayShift()==false)
		valid=true;
///	alert("valid---"+valid)
	
	return valid;
	
	
}

function finalSubmit(mode)
{


if (!validateFinalSubmit()) 
	submitPage(mode);
	else
	return;

	
	
}
function clearForm(){

	if(document.forms[0].hmode.value=="ADD"){
   		document.getElementsByName('rosterTypeId')[0].value="-1";
	}
	moveLeftAll(1);
	document.getElementsByName('selectedShiftId')[0].length=0;
	if(document.forms[0].hmode.value=="MODIFY"){
   		document.getElementsByName('isActive')[0].value="-1";
	}

}
  
function moveRightSingleForModify(listNo)
{
	var source;
	var target;

	// 1 -> Parameter
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].shiftId;
		target = document.forms[0].selectedShiftId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveRightAllForModify(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].shiftId;
		target = document.forms[0].selectedShiftId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}

function moveLeftSingleForModify(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].shiftId;
		source = document.forms[0].selectedShiftId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveLeftAllForModify(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].shiftId;
		source = document.forms[0].selectedShiftId;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}

function validateShift(source){
   
   	var target=document.forms[0].selectedShiftId;
    var val="";
    var text="";
    for(var i=0;i<target.length;i++)
    {
    val=target.options[i].value.split("@");
    
       if(source.split("@")[1]==val[1]){
    	 	   alert("Shift Type Already Selected, Cannot be Mapped");
    		   return false;
    									}
    //	if(!checkTimeClash(source.split("@")[2],source.split("@")[3],val[2],val[3])){
    	//	return false;
      //}
    	
    }//for closed
 	return true;
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
	
	
	var status =0;
	
	
	
	if(endTimeHr1-StartTimeHr1 < 0){
		endTimeHr1=endTimeHr1+24;
		if(!(endTimeHr2>StartTimeHr1)){
			StartTimeHr2=StartTimeHr2+24;
		}
		
	}
	
	if(endTimeHr2-StartTimeHr2 < 0){
		endTimeHr2=endTimeHr2+24;
		
	    StartTimeHr1=StartTimeHr1+24;
			
	}
	
	/*
	alert("StartTimeHr1 "+ StartTimeHr1 +" StartTimeMin1 " + StartTimeMin1+
	"\n StartTimeHr2 "+ StartTimeHr2 +" StartTimeMin2 " + StartTimeMin2+
	"\nendTimeHr1 "+ endTimeHr1 +" endTimeMin1 " + endTimeMin1+
	"\nendTimeHr2 "+ endTimeHr2 +" endTimeMin2 " + endTimeMin2);
	*/
		
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
		if(endTimeHr2==StartTimeHr1){
			status=1;
			
		}
		else{
		status=0;}
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

function getTimehrMin(strTime,hrMin)
{
	var lenstr=strTime.length;
	var hours;
	var minutes;
	if(lenstr==5)
	{
		hours=strTime.substring(0,2);
		minutes =strTime.substring(3);
		
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
		//alert("hr " + hours)
		var hour=hours.substring(0,1);
		if(hour=="0"){
			return hours.substring(1,2);
		}	
		else{
			return hours;
		}
				
	}	
		
	if(hrMin=="min")
	{
	    //alert("min" + minutes)
		return minutes;		
	}	
}

function getFetchedList(){

	var fetchedList=document.forms[0].selectedShiftId;
	var selectedData=document.forms[0].fetchedList.value;
	for(var i=0;i<fetchedList.length;i++){
		selectedData=selectedData + fetchedList.options[i].value +"%";
	}
	document.forms[0].fetchedList.value=selectedData;
}
function getAvailableList(){

	var availableList=document.forms[0].shiftId;
	var data=document.forms[0].availableList.value;
	for(var i=0;i<availableList.length;i++){
		data=data + availableList.options[i].value +"%";
	}
	document.forms[0].availableList.value=data;
}

function getShiftsBasedOnRoster(mode,these)
{
if(these.value!="-1")
	{
document.forms[0].hmode.value=mode;
document.forms[0].submit();
	}
	

}

