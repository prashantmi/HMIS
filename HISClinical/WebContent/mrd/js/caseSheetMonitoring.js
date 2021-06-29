window.onload=function(){
	
	if(document.getElementsByName("selection")){
		var selection=document.getElementsByName("selection")
		for(var i=0;i<selection.length;i++){
			if(selection[i].checked){
				//alert(selection[i])
				toggleOption(selection[i]);
				break;
			}	
		}
	}	
	var status=document.getElementsByName("caseSheetStatus")[0].value
	var isDuplicate=document.getElementsByName("isDuplicate")[0].value
	
	if(status==1 || status==0 && isDuplicate==0){
		document.getElementById("lostDiv").style.display="block";
		document.getElementById("lostLabel").style.display="block";
		document.getElementById("foundLabel").style.display="none";
		document.getElementById("duplicateLabel").style.display="none";
		document.getElementById("removeLabel").style.display="none";
		document.getElementsByName("updateType")[0].checked=true
		document.getElementsByName("updateType")[1].disabled=true
		document.getElementsByName("updateType")[2].disabled=true
		document.getElementsByName("updateType")[3].disabled=true
		
	}
	if(status==1 && isDuplicate==1){
		if(document.getElementById("lostDiv")){
			document.getElementById("lostDiv").style.display="block";
		}	
		document.getElementById("lostLabel").style.display="block";
		document.getElementById("removeLabel").style.display="block";
		document.getElementById("foundLabel").style.display="none";
		document.getElementById("duplicateLabel").style.display="none";
		document.getElementsByName("updateType")[0].checked=true
		document.getElementsByName("updateType")[1].disabled=true
		document.getElementsByName("updateType")[2].disabled=true
		
	}
	if(status==4 && isDuplicate==0){
		//alert("status lost + original")
		if(document.getElementById("foundDiv")){
			document.getElementById("foundDiv").style.display="block"
		}	
		if(document.getElementById("foundLabel")){
			document.getElementById("foundLabel").style.display="block"
		}	
		document.getElementById("duplicateLabel").style.display="block"
		document.getElementById("lostLabel").style.display="none"
		document.getElementById("removeLabel").style.display="none"
		document.getElementsByName("updateType")[1].checked=true
		document.getElementsByName("updateType")[0].disabled=true
		//alert(document.getElementsByName("hideDuplicateLabel")[0].value)
		if(document.getElementsByName("hideDuplicateLabel")[0].value=="true"){
			document.getElementsByName("updateType")[2].disabled=true
		}
		else{
			document.getElementsByName("updateType")[2].disabled=false;
		}
		document.getElementsByName("updateType")[3].disabled=true
		
		
	}
	if(status==5){
		document.getElementById("lostLabel").style.display="block"
		document.getElementById("foundLabel").style.display="block"
		document.getElementById("duplicateLabel").style.display="block"
		document.getElementsByName("updateType")[0].checked=true
		document.getElementsByName("updateType")[3].disabled=true
	}
	if(status==4 && isDuplicate==1){
		document.getElementById("foundDiv").style.display="block"
		document.getElementById("duplicateLabel").style.display="block"
		document.getElementById("foundLabel").style.display="block"
		document.getElementById("lostLabel").style.display="none"
		document.getElementById("removeLabel").style.display="none"
		document.getElementsByName("updateType")[1].checked=true
		document.getElementsByName("updateType")[0].disabled=true
		document.getElementsByName("updateType")[3].disabled=true
		
		
		
	}

	
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
function submitPage(mode){
	if(document.getElementsByName("patAdmNo")[0].value==""){
		alert("Please Enter Admission No.");
		document.getElementsByName("patAdmNo")[0].focus();
		return false;
	}		

}

function toggleOption(obj){

	var selection;
	switch(parseInt(obj.value)){
		case 0: 
			document.getElementById("unitWardWise").style.display="block";
			document.getElementById("crNoWise").style.display="none";
			//document.getElementsByName("hmode")[0].value="GETCASESHEETDTL"
			document.getElementsByName("searchMode")[0].value="0"
			document.getElementsByName("hmode")[0].value="NEW"
			break;
		case 1: 
			document.getElementById("crNoWise").style.display="block";
			document.getElementById("unitWardWise").style.display="none";
			document.getElementsByName("searchMode")[0].value="1"
			document.getElementsByName("hmode")[0].value="SEARCHBYCRNO"
			break;
	}
	
	//document.forms[0].submit();
}
function submitMode(obj){
	toggleOption(obj);
	document.forms[0].submit();
}

function getAction(obj){
	var array=obj.value.split("#");
	var patCrNo= array[0]
	var status=array[1]
	var isDuplicate=array[2]
	document.getElementsByName("patCrNo")[0].value=patCrNo
	document.getElementsByName("caseSheetStatus")[0].value=status
	document.getElementsByName("isDuplicate")[0].value=isDuplicate
	document.getElementsByName("hmode")[0].value="GETCASESHEETSTATUS"
	document.getElementsByName("creationDate")[0].value=array[5];
	document.forms[0].submit()

}

function updateCaseSheet(){

	if(!validateForm()){
		return false;
	}	
	document.getElementsByName("hmode")[0].value="UPDATECASESHEETSTATUS"
	document.forms[0].submit()
}


function validateForm(){
	var valid=true;
	var option
	var updateType=document.getElementsByName("updateType")
	for(var i=0;i<updateType.length;i++){
		if(updateType[i].checked){
			//alert(selection[i])
			option=updateType[i].value;
			break;
		}	
	}
	if(option=="0"){
		if(document.getElementsByName("reportedBy")[0].value==""){
			alert("Enter Reported By");
			document.getElementsByName("reportedBy")[0].focus()
			 return false;
		}
		if(document.getElementsByName("lostDate")[0].value==""){
			alert("Enter Lost Date");
			document.getElementsByName("lostDate")[0].focus()
			 return false;
		}
		if(document.getElementsByName("lostTimeHr")[0].value==""){
			alert("Enter Lost Time Hr");
			document.getElementsByName("lostTimeHr")[0].focus()
			  return false;
		}
		if(document.getElementsByName("lostTimeMin")[0].value==""){
			alert("Enter Lost Time Min");
			document.getElementsByName("lostTimeMin")[0].focus()
			  return false;
		}
		if(checkDate(document.getElementsByName("lostDate")[0])<1 && checkDate(document.getElementsByName("lostDate")[0])!=0)
		{
			alert("Lost Date Cannot be Greater Than Today ("+document.getElementsByName("sysDate")[0].value +" )");
			document.getElementsByName("lostDate")[0].focus();
			return false;
		}
		else if(!checkTime(document.getElementsByName("lostTimeHr")[0],
		document.getElementsByName("lostTimeMin")[0],document.getElementsByName("lostDate")[0]))
		{
			return false;
		}
		
		if(document.getElementsByName("lostType")[0].value=="-1"){
			alert("Enter Lost Type");
			document.getElementsByName("lostType")[0].focus()
			  return false;
		}
		if(document.getElementsByName("lastSeenDate")[0].value!=""){
		if(checkDate(document.getElementsByName("lastSeenDate")[0])<1 && checkDate(document.getElementsByName("lastSeenDate")[0])!=0)
		{
			alert("Last Seen Date Cannot be Greater Than Today( "+document.getElementsByName("sysDate")[0].value +" )");
			document.getElementsByName("lastSeenDate")[0].focus();
			return false;
		}
		}
		var lostDate=document.getElementsByName("lostDate")[0].value;
		//alert("creationDate :"+document.getElementsByName("creationDate")[0].value);
		if(typeof document.getElementsByName("creationDate")[0] !='undefined')
		{
			var creationDate=document.getElementsByName("creationDate")[0].value;
			if(!checkLostDate(lostDate,creationDate))
			{
				return false;
			}
		}
		if(document.getElementsByName("lastSeenDate")[0].value!=""){
 		var lstSeen_hr=document.getElementsByName("lastSeenTimeHr")[0].value;
    	var lstSeen_min=document.getElementsByName("lastSeenTimeMin")[0].value;
    	if(document.getElementsByName("lastSeenTimeHr")[0].value==""){
			alert("Enter Last Seen Time Hr");
			document.getElementsByName("lastSeenTimeHr")[0].focus()
			  return false;
		}
		if(document.getElementsByName("lastSeenTimeMin")[0].value==""){
			alert("Enter Last Seen Time Time Min");
			document.getElementsByName("lastSeenTimeMin")[0].focus()
			  return false;
		}
    	
    	if(parseInt(lstSeen_hr)>=24)
    	{
    		if(parseInt(lstSeen_hr)==24)
    		{
    			document.getElementsByName("lastSeenTimeHr")[0].value=0;
    		}
    		else
    		{
    			alert("Hours cannot be greater than 23");
    			document.getElementsByName("foundTimeHr")[0].focus();
    			return false;
    		}
    	}
    	if(parseInt(lstSeen_min)>=60)
    	{
    		if(parseInt(lstSeen_min)==60)
    		{
    			document.getElementsByName("lastSeenTimeMin")[0].value=0;
    		}
    		else
    		{
    			alert("Minutes cannot be greater than 59");
    			document.getElementsByName("lastSeenTimeMin")[0].focus();
    			return false;
    		}
   	 	}
   	 	var lastseendate=document.getElementsByName("lastSeenDate")[0].value;
   	 	if(!checkLastSeenDate(lostDate,lastseendate))
		{
				return false;
		}
		if(!checkLastSeenCreationDate(lastseendate,creationDate))
		{
				return false;
		}
		
//		else if(!checkTime(document.getElementsByName("lastSeenTimeHr")[0],
//		document.getElementsByName("lastSeenTimeMin")[0],document.getElementsByName("lastSeenDate")[0]))
//		{
//			return false;
//		}
	}
	}
	if(option=="1")
	{
		var foundDate=document.getElementsByName("foundDate")[0].value;
		//alert("cur_date="+cur_date);
		var lostDate=document.getElementsByName("lstDate")[0].value;
		if(document.getElementsByName("foundBy")[0].value==""){
			alert("Enter Found By");
			document.getElementsByName("foundBy")[0].focus()
			 return false;
		}
		if(document.getElementsByName("foundDate")[0].value==""){
			alert("Enter Found Date");
			document.getElementsByName("foundDate")[0].focus()
			 return false;
		}
		if(document.getElementsByName("foundTimeHr")[0].value==""){
			alert("Enter Found Time Hr");
			document.getElementsByName("foundTimeHr")[0].focus()
			  return false;
		}
		if(document.getElementsByName("foundTimeMin")[0].value==""){
			alert("Enter Found Time Min");
			document.getElementsByName("foundTimeMin")[0].focus()
			  return false;
		}
		if(!validateSysDate())
		{
			alert("Found Date Cannot be Greater Than  Current Date ("+document.getElementsByName("sysDate")[0].value +" )");
			return false;
		}
		if(!checkFoundDate(foundDate,lostDate))
		{
				return false;
		}
	}
	
	return true;

}

function showDiv(obj){
	if(obj.value==0){
		document.getElementById("lostDiv").style.display="block";
		document.getElementById("foundDiv").style.display="none";
		document.getElementById("duplicateDiv").style.display="none";
		document.getElementById("removeDiv").style.display="none";
	}
	if(obj.value==1){
		document.getElementById("foundDiv").style.display="block";
		document.getElementById("lostDiv").style.display="none";
		document.getElementById("duplicateDiv").style.display="none";
		document.getElementById("removeDiv").style.display="none";
	}
	if(obj.value==2){
		document.getElementById("duplicateDiv").style.display="block";
		document.getElementById("lostDiv").style.display="none";
		document.getElementById("foundDiv").style.display="none";
		document.getElementById("removeDiv").style.display="none";
	}
	if(obj.value==3){
		document.getElementById("removeDiv").style.display="block";
		document.getElementById("lostDiv").style.display="none";
		document.getElementById("duplicateDiv").style.display="none";
		document.getElementById("foundDiv").style.display="none";
	}
	
}
 
function clearForm()
{
 	 document.getElementsByName("reportedBy")[0].value="";
 	 document.getElementsByName("lostDate")[0].value="";
 	 document.getElementsByName("lostTimeHr")[0].value="";
 	 document.getElementsByName("lostTimeMin")[0].value="";
 	 document.getElementsByName("lostType")[0].value="-1";
 	 document.getElementsByName("lastSeenDate")[0].value="";
 	 document.getElementsByName("lastSeenTimeHr")[0].value="";
 	 document.getElementsByName("lastSeenTimeMin")[0].value="";
 	 document.getElementsByName("lostArea")[0].value="";
 	 document.getElementsByName("lostRemarks")[0].value="";
 	 document.getElementsByName("foundBy")[0].value="";
 	 document.getElementsByName("foundDate")[0].value="";
 	 document.getElementsByName("foundTimeHr")[0].value="";
 	 document.getElementsByName("foundTimeMin")[0].value="";
 	 document.getElementsByName("foundRemarks")[0].value="";
 	 document.getElementsByName("cancelRemarks")[0].value="";
 	 document.getElementsByName("remarks")[0].value="";
    
}

function checkDate(toDate)
{
	var valid=true;
	var days=0;
	var a=toDate.value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
    days=((bdate-adate)/86400000);
   
    return days;
}

function checkTime(hr,min,toDate)
{
	var days=checkDate(toDate);
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var minute=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var valid=true;
	
	if(days==0)
	{
		if(parseInt(trimNum(hr.value)) > hour)
		{
			alert("Hour Cannot be Greater than "+ hour);
			hr.focus();
			valid=false;
		}
		else if(parseInt(trimNum(min.value)) > parseInt("59"))
		{
			alert("Minute Cannot be Greater than 59");
			min.focus();
			valid=false;
		}
		else if(parseInt(trimNum(hr.value)) >= hour && parseInt(trimNum(min.value)) > minute)
		{
			alert("Minute Cannot be Greater than "+minute);
			min.focus();
			valid=false;
		}
	}
	else
	{
		if(days==1)
		{
			if(parseInt(trimNum(hr.value)) > parseInt("23"))
			{
				alert("Hour Cannot be Greater than 23");
				hr.focus();
				valid=false;
			}
			else if(parseInt(trimNum(min.value)) > parseInt("59"))
			{
				alert("Minute Cannot be Greater than 59");
				min.focus();
				valid=false;
			}
		}
	}
	return valid;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function getWardByUnit(){
	document.getElementsByName("hmode")[0].value="UNIT"
	var unitCode=document.getElementsByName("departmentUnitCode")[0].value
	if(unitCode=="-1"){
		document.getElementsByName("hmode")[0].value="NEW"
	}
	document.forms[0].submit();
}

function checkFoundDate(foundDate,lostDate)
{
	var msg="";
   var found_date=convertStrToDate(foundDate, "dd-Mon-yyyy");
   
   
    var b=lostDate.toString(); 
    var lst_dateArr=b.split(" "); // date and time are splitted here
    var lst_date=lst_dateArr[0];
    var lst_time=lst_dateArr[1];
    var timeArr=lst_time.split(":"); // hours and mins are splitted here
    var lst_hr= timeArr[0];
    var lst_min=timeArr[1];
      
    var lost_date=convertStrToDate(lostDate, "dd-Mon-yyyy");
    var cur_hr=document.getElementsByName("foundTimeHr")[0].value;
    var cur_min=document.getElementsByName("foundTimeMin")[0].value;
    if(parseInt(cur_hr)>=24)
    {
    	if(parseInt(cur_hr)==24)
    	{
    		document.getElementsByName("foundTimeHr")[0].value=0;
    	}
    	else
    	{
    		alert("Hours cannot be greater than 23");
    		document.getElementsByName("foundTimeHr")[0].focus();
    		return false;
    	}
    }
    if(parseInt(cur_min)>=60)
    {
    	if(parseInt(cur_min)==60)
    	{
    		document.getElementsByName("foundTimeMin")[0].value=0;
    	}
    	else
    	{
    		alert("Minutes cannot be greater than 59");
    		document.getElementsByName("foundTimeMin")[0].focus();
    		return false;
    	}
    }
   			
    if(found_date<=lost_date)
    {
    	if(found_date.toLocaleString()==lost_date.toLocaleString())
    	{
    		if(cur_hr<=lst_hr)
    		{
    			if(cur_hr==lst_hr)
    			{
    				if(cur_min<lst_min)
    				{
    					alert("Found Date should be Greater than Lost Date ( "+lostDate+" )");
    					return false;
    				}
       			}
    			else 
    			{
    				alert("Found Date should be Greater than Lost Date ( "+lostDate+" )");
    				return false;
    			}
    		}
    	}
    	else
    	{
    		alert("Found Date should be Greater than Lost Date ( "+lostDate+" )");
    		return false;
    	}
    }
    return true;
}

function validateSysDate()
{
	var valid=true;
	var foundDate=document.getElementsByName("foundDate")[0].value;
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var ntHour=document.getElementsByName("hiddenTimeHr")[0].value;
	var ntMin=document.getElementsByName("hiddenTimeMin")[0].value;
	var days=noOfDays(foundDate,sysDate);
	if(days<0)
	{		
		document.getElementsByName("foundDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) > hour)
			{
				document.getElementsByName("foundTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("foundTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("foundTimeMin")[0].value)) > min)
			{
				document.getElementsByName("foundTimeMin")[0].focus();
				valid=false;
			}
		}
		else
			valid=true;
	}
	
	return valid;
}
function noOfDays(a,b)
{
	var valid=true;
	var days=0;
	var a_temp=a.toString();
    var aArray=a_temp.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
    var b_temp=b.toString();
    var bArray=b_temp.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
    days=((bdate-adate)/86400000);

    return days;
}
function checkLostDate(lostDate,creationDate)
{
	   
    var b=creationDate.toString(); 
    var creation_dateArr=b.split(" "); // date and time are splitted here
    var creation_date=creation_dateArr[0];
    var creation_time=creation_dateArr[1];
    var timeArr=creation_time.split(":"); // hours and mins are splitted here
    var creation_hr= timeArr[0];
    var creation_min=timeArr[1];
    
    
    var lost_date=convertStrToDate(lostDate, "dd-Mon-yyyy");
      
    var create_date=convertStrToDate(creationDate, "dd-Mon-yyyy");
    
    //alert("lost date :"+lost_date);
    //alert("create_date :"+create_date);
    if(lost_date<=create_date)
    {
    	if(lost_date.toLocaleString()==create_date.toLocaleString())
    	{
    		var lost_hr=document.getElementsByName("lostTimeHr")[0].value;
    		var lost_min=document.getElementsByName("lostTimeMin")[0].value;
    		if(lost_hr<=creation_hr)
    		{
    			if(lost_hr==creation_hr)
    			{
    				if(lost_min<creation_min)
    				{
    					 alert("Lost Date cannot be less than Record Creation Date ( "+creationDate+" )");
    					 return false;
    				}
    			}
    			else 
    			{	
    				alert("Lost Date cannot be less than Record Creation Date ( "+creationDate+" )");
    				return false;
    			}
    			   
    		}
    	}
    	else
    	{
    		alert("Lost Date cannot be less than Record Creation Date ( "+creationDate+" )");
    		return false;
    	}
    		
    }
    //alert("true");
    return true;
}
function checkLastSeenDate(lostDate,lastseendate)
{
	   
    var lost_date=convertStrToDate(lostDate, "dd-Mon-yyyy");
      
    var lastseen_date=convertStrToDate(lastseendate, "dd-Mon-yyyy");
    
    if(lost_date<=lastseen_date)
    {
    	if(lost_date.toLocaleString()==lastseen_date.toLocaleString())
    	{
    		var lost_hr=document.getElementsByName("lostTimeHr")[0].value;
    		var lost_min=document.getElementsByName("lostTimeMin")[0].value;
    		if(typeof document.getElementsByName("lastSeenTimeHr")[0] !='undefined')
    			var lastseen_hr=document.getElementsByName("lastSeenTimeHr")[0].value;
    		else
    		    var lastseen_hr=0;
    		if(typeof document.getElementsByName("lastSeenTimeMin")[0] !='undefined')    
    			var lastseen_min=document.getElementsByName("lastSeenTimeMin")[0].value;
    		else
    			var lastseen_min=0;
    		if(lost_hr<=lastseen_hr)
    		{
    			if(lost_hr==lastseen_hr)
    			{
    				if(lost_min<lastseen_min)
    				{
    					 alert("Lost Date cannot be less than Last Seen Date ( "+lastseendate+" "+lastseen_hr+":"+lastseen_min+" )");
    					 return false;
    				}
    			}
    			else 
    			{	
    				alert("Lost Date cannot be less than Last Seen Date ( "+lastseendate+" "+lastseen_hr+":"+lastseen_min+" )");
    				return false;
    			}
    			   
    		}
    	}
    	else
    	{
    		if(typeof document.getElementsByName("lastSeenTimeHr")[0] !='undefined')
    			var lastseen_hr=document.getElementsByName("lastSeenTimeHr")[0].value;
    		else
    		    var lastseen_hr=0;
    		if(typeof document.getElementsByName("lastSeenTimeMin")[0] !='undefined')    
    			var lastseen_min=document.getElementsByName("lastSeenTimeMin")[0].value;
    		alert("Lost Date cannot be less than Last Seen Date ( "+lastseendate+" "+lastseen_hr+":"+lastseen_min+" )");
    		return false;
    	}
    		
    }
    
    return true;
}
function checkLastSeenCreationDate(lastseendate,creationDate)
{
	 var b=creationDate.toString(); 
    var creation_dateArr=b.split(" "); // date and time are splitted here
    var creation_date=creation_dateArr[0];
    var creation_time=creation_dateArr[1];
    var timeArr=creation_time.split(":"); // hours and mins are splitted here
    var creation_hr= timeArr[0];
    var creation_min=timeArr[1];
    
    var lastseen_date=convertStrToDate(lastseendate, "dd-Mon-yyyy");
      
    var create_date=convertStrToDate(creationDate, "dd-Mon-yyyy");
    
    if(lastseen_date<=create_date)
    {
    	if(lastseen_date.toLocaleString()==create_date.toLocaleString())
    	{
    		var lastseen_hr=document.getElementsByName("lastSeenTimeHr")[0].value;
    		var lastseen_min=document.getElementsByName("lastSeenTimeMin")[0].value;
    		if(lastseen_hr<=creation_hr)
    		{
    			if(lastseen_hr==creation_hr)
    			{
    				if(lastseen_min<creation_min)
    				{
    					 alert("Last Seen Date cannot be less than Record Creation Date ( "+creationDate+" )");
    					 return false;
    				}
    			}
    			else 
    			{	
    				alert("Last Seen Date cannot be less than Record Creation Date ( "+creationDate+" )");
    				return false;
    			}
    			   
    		}
    	}
    	else
    	{
    		alert("Last Seen Date cannot be less than Record Creation Date ( "+creationDate+" )");
    		return false;
    	}
    		
    }
    
    return true;
}

