function validateAddRow()
{	
	var retVal=true;
	
		if(comboValidation(document.getElementsByName('strEmployeeName')[0],'Employee'))
		{
			if(document.getElementsByName("strRole")[0].value==""){
				alert("Please Enter Role !");
				document.getElementsByName("strRole")[0].focus();
				return false;
			}
			else{

					return true;
				}
		}							
		else{
			return false;
		}
}

function validateAddRowToTable(){
		
	var strRole=document.getElementsByName("strRole")[0].value;
	if(strRole==""){
		alert("Please Enter Role");
		document.getElementsByName("strRole")[0].focus();
		return false;
	}
}

function AddRowToTable()
{		
		if(validateAddRowToTable()==false){
			return false;
		}
		var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
		
		var EmpName="strArrEmployee";
		var role="strRole";
		var dutyRemarks="strDutyRemarks";
		var tableName = "tableParamCampId";
		//document.getElementById("warningMsg").innerHTML="";
		
		var nRow=0;
		var tableObj=document.getElementById(tableName);
		var numRows=tableObj.rows.length;
		
		if(numRows>2)	nRow=tableObj.rows[numRows-1].id;
		else			nRow=numRows;
		
		var tabRow=tableObj.insertRow(numRows);
		tabRow.id=parseInt(nRow)+1;
		
		//alert(tabRow.id);
		
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		
		var selectObject=document.getElementsByName("strEmployeeName")[0];
		var value=selectObject.value;
		var selectedIndex=selectObject.selectedIndex;
		var optionsArray=selectObject.options;
		var label=optionsArray[selectedIndex].text;
		selectObject.remove(selectedIndex);
			//alert("value"+value+" Lable :"+label);
		td1.innerHTML="<div align='center'>"
			+ "<input type='hidden' name='"+EmpName+"Id' value='"+value+"'> "
			+ "<input type='hidden' name='"+EmpName+"Name' value='"+label+"'> "
			+ label
			+ "</div>";
			td1.className='tdfont';
			td1.width="30%";
			
		///////////////////////	
		td2.innerHTML="<div align='center'>"
			+ "<input type='hidden' name='strArrRole' value='"+document.getElementsByName("strRole")[0].value+"'> "
			+ document.getElementsByName("strRole")[0].value
			+ "</div>";
			td2.className='tdfont';
			td2.width="30%";
			
		///////////////////////	
		td3.innerHTML="<div align='center'>"
			+ "<input type='hidden' name='strArrDutyRemarks' value='"+document.getElementsByName("strDutyRemarks")[0].value+"'> "
			+ document.getElementsByName("strDutyRemarks")[0].value
			+ "</div>";
			td3.className='tdfont';
			td3.width="30%";
		///////////////////////
		td4.className='tdfont';
		td4.colSpan="1";
		td4.width="2%"
		td4.innerHTML="<div align='center'><img src='/HIS/hisglobal/images/avai/minus.gif'  onClick='deleteRowAdd(document.getElementById(\""+(parseInt(nRow)+1)+"\"))' style='cursor: pointer'></div>";
	    ///////////////////////	
			
		tabRow.appendChild(td1);
		tabRow.appendChild(td2);
		tabRow.appendChild(td3);		
		tabRow.appendChild(td4);
		
		document.getElementsByName('numberOfRow')[0].value=numRows;
		document.getElementsByName("strRole")[0].value="";
		document.getElementsByName("strDutyRemarks")[0].value="";			
}



function deleteRowAdd(Str)
{	
	//alert("inside deleteRow()");
	var temp=Str;
	var empName =  "strEmployeeName";
	var strEmployeeName = "strEmployeeName";
	var tableName = "tableParamCampId";
	
	var nRow=0;
	var tableObj=document.getElementById(tableName);
	var numRows=tableObj.rows.length;
	
	//alert(numRows);
	var elementLabel=strEmployeeName//+"Label";
	var selectObject=document.getElementsByName(empName)[0];
	var newOption=document.createElement('option');
	newOption.text=document.getElementsByName(elementLabel)[temp.rowIndex-2].value;
	newOption.value=document.getElementsByName(strEmployeeName)[temp.rowIndex-2].value;
	selectObject.add(newOption,null)
	
	//var tableObj=document.getElementById(tableName);
	//alert(temp.rowIndex);
	//alert(document.getElementsByName('numberOfRow')[0].value);
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}


function deleteRowNoMOd(Str)
{	
	//alert("inside deleteRow()");
	//alert(Str);
	var temp=Str;
	var empName =  "strEmployeeName";
	var strEmployeeName = "strEmployeeName";
	var tableName = "tableParamCampId";
	
	var nRow=0;
	var tableObj=document.getElementById(tableName);
	var numRows=tableObj.rows.length;
	
	
	var elementLabel=strEmployeeName+"Label";
	var selectObject=document.getElementsByName(empName)[0];
	var newOption=document.createElement('option');
	//alert(temp.rowIndex);
	
	//alert(elementLabel);
	//newOption.text=document.getElementsByName(elementLabel)[temp.rowIndex-2].value;
	//newOption.value=document.getElementsByName(strEmployeeName)[temp.rowIndex-2].value;
	selectObject.add(newOption,null)
	
	//var tableObj=document.getElementById(tableName);
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}
function onkeyTimeHour(_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+hh);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38) // on Arrow press Down-40 Up-38
	{
		if(k==40)		hh=(hh+1)%24;
		else if(k==38)	hh=(24+hh-1)%24;

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;
	}
	if(k!=0)	return true;
	else		return false;
}

function onblurTimeHourCheck(_hObj)
{
	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);
	// Setting Hour
	if(hh<10)	_hObj.value="0"+hh;
	else			_hObj.value=hh;
}


function onkeyTimeMin(_mObj,_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+mm);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38)	// on Arrow press Down-40 Up-38
	{
		if(k==40)
		{
			if(mm==59)	hh=(hh+1)%24;
			mm=(mm+1)%60;
		}
		else if(k==38)
		{
			if(mm==0)	hh=(24+hh-1)%24;
			mm=(60+mm-1)%60;
		}

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;

		// Setting Minutes
		if(mm<10) _mObj.value="0"+mm;
		else		_mObj.value=mm;
	}
	if(k!=0)	return true;
	else		return false;
}

function onblurTimeMinCheck(_mObj)
{
	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);
	// Setting Minutes
	if(mm<10)	_mObj.value="0"+mm;
	else			_mObj.value=mm;
}


function datecheck()
{	
      var adate=convertStrToDate(document.getElementsByName("strCampRequisitionDate")[0].value, 'dd-Mon-yyyy');
      var bdate=convertStrToDate(document.getElementsByName("sysdate")[0].value, 'dd-Mon-yyyy');
      
      if(adate<bdate)
      {
      alert("Camp Req Date should be greater than System Date");
      document.forms[0].strCampRequisitionDate.value="";
      document.forms[0].strCampRequisitionDate.focus(); 
      return false;
      }
 return true;
}

function startDatecheck()
{	
      var adate=convertStrToDate(document.getElementsByName("strCampStartDateTime")[0].value, 'dd-Mon-yyyy');
      var bdate=convertStrToDate(document.getElementsByName("strCampRequisitionDate")[0].value, 'dd-Mon-yyyy');
      
      if(adate<bdate)
      {
      alert("Camp Start Date should be greater than Requition Date");
      document.forms[0].strCampStartDateTime.value="";
      document.forms[0].strCampStartDateTime.focus(); 
      return false;
      }
 return true;
}

function endDatecheck()
{	
	  var startDT=document.getElementsByName("strCampEndDateTime")[0].value +" "+
	  	document.getElementsByName("campEndHr")[0].value +":"+document.getElementsByName("campEndMin")[0].value;
      var adate=convertStrToDate(startDT, 'dd-Mon-yyyy hh:mm');
      
      var endDt=document.getElementsByName("strCampStartDateTime")[0].value +" "+
       	document.getElementsByName("campStartHr")[0].value +":"+document.getElementsByName("campStartMin")[0].value;     
      var bdate=convertStrToDate(endDt, 'dd-Mon-yyyy hh:mm');
      
      if(adate<bdate)
      {
      alert("Camp End Date & Time should be greater than Camp Start Date & Time");
      document.forms[0].strCampEndDateTime.value="";
      document.forms[0].campEndHr.value="";
      document.forms[0].campEndMin.value="";
      document.forms[0].strCampEndDateTime.focus(); 
      return false;
      }
 return true;
}

function onkeyTimeHour(_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+hh);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38) // on Arrow press Down-40 Up-38
	{
		if(k==40)		hh=(hh+1)%24;
		else if(k==38)	hh=(24+hh-1)%24;

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;
	}
	if(k!=0)	return true;
	else		return false;
}

function onblurTimeHourCheck(_hObj)
{
	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);
	// Setting Hour
	if(hh<10)	_hObj.value="0"+hh;
	else			_hObj.value=hh;
}

function onkeyTimeMin(_mObj,_hObj,_e) // 24-Hour Format
{	
	validateNumeric(_e);
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+mm);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38)	// on Arrow press Down-40 Up-38
	{
		if(k==40)
		{
			if(mm==59)	hh=(hh+1)%24;
			mm=(mm+1)%60;
		}
		else if(k==38)
		{
			if(mm==0)	hh=(24+hh-1)%24;
			mm=(60+mm-1)%60;
		}

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;

		// Setting Minutes
		if(mm<10) _mObj.value="0"+mm;
		else		_mObj.value=mm;
	}
	if(k!=0)	return true;
	else		return false;
}

function onblurTimeMinCheck(_mObj)
{
	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);
	// Setting Minutes
	if(mm<10)	_mObj.value="0"+mm;
	else			_mObj.value=mm;
}

function noOfDays(a,b){
 
      var aArray=a.split("-");
      var amonth=aArray[1];
      
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);// camp date
      
      
      var bArray=b.split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);// system date
      var days=(bdate-adate)/ 86400000;
 	  return days;
 
 }
 
function TimeCheck()
{
	var dayStart=0;
	var dayEnd=0;
		dayStart=noOfDays(document.getElementsByName("strCampStartDateTime")[0].value,document.getElementsByName("sysdate")[0].value);
		dayEnd=noOfDays(document.getElementsByName("strCampEndDateTime")[0].value,document.getElementsByName("sysdate")[0].value);
		alert("dayStart :"+dayStart+" dayEnd :"+dayEnd);
	
	if((dayStart==0) &&(dayEnd==0))
		{
			if(document.getElementsByName("campStartHr")[0].value!="")
			{
				if(document.getElementsByName("campStartHr")[0].value < document.getElementsByName("sysTimeHr")[0].value)
				{
					alert("Camp Start Time Should be Greater than System Time");
					document.getElementsByName("campStartHr")[0].focus();
					return false;
				}
				else
				{
					if(document.getElementsByName("campStartHr")[0].value == document.getElementsByName("sysTimeHr")[0].value)
					{
						if(document.getElementsByName("campStartMin")[0].value <= document.getElementsByName("sysTimeMin")[0].value)
						{
							alert("Camp Start Time Should be Greater than System Time");
							document.getElementsByName("campStartMin")[0].focus();
							return false;
						}
					}
				}
				
				//Checking for End Time
				if(document.getElementsByName("campEndHr")[0].value < document.getElementsByName("campStartHr")[0].value)
				{
					alert("Camp End Time Should be Greater than Camp Start Time");
					document.getElementsByName("campEndHr")[0].focus();
					return false;
				}
				else
				{
					if(document.getElementsByName("campEndHr")[0].value == document.getElementsByName("campStartHr")[0].value)
					{
						if(document.getElementsByName("campEndMin")[0].value <= document.getElementsByName("campStartMin")[0].value)
						{
							alert("Camp End Time Should be Greater than System Time");
							document.getElementsByName("campEndMin")[0].focus();
							return false;
						}
					}
				}
				
			}
		}
		if((dayStart + dayEnd)<0)
		{
			if(document.getElementsByName("campStartHr")[0].value!="")
			{
				//Checking for End Time
				if(document.getElementsByName("campEndHr")[0].value < document.getElementsByName("campStartHr")[0].value)
				{
					alert("Camp End Time Should be Greater than Camp Start Time");
					document.getElementsByName("campEndHr")[0].focus();
					return false;
				}
				else
				{
					if(document.getElementsByName("campEndHr")[0].value == document.getElementsByName("campStartHr")[0].value)
					{
						if(document.getElementsByName("campEndMin")[0].value <= document.getElementsByName("campStartMin")[0].value)
						{
							alert("Camp End Time Should be Greater than System Time");
							document.getElementsByName("campEndMin")[0].focus();
							return false;
						}
					}
				}
			}	
		}	
		
		//alert("3")
		
		 if(campStartHr>campEndHr)
		 {
		  alert("Camp End Time should be greater than Camp Start Time:::: ") 
		   document.forms[0].campStartHr.focus();
		   return false;
		 }
		  else if(campStartHr==campEndHr&&campStartMin==campEndMin)
			 {
			 		alert("Camp End Time Should Be Greater Than Camp Start Time ") 
			   		document.forms[0].campEndMin.focus();
			   		return false;
			 }
		 else if(campStartHr==campEndHr&&campStartMin>campEndMin)
		 {
		 		alert("Camp End Time Should Be Greater Than Camp Start Time ") 
		   		document.forms[0].campEndMin.focus();
		   		return false;
		 }	
		 
	return true;	
}