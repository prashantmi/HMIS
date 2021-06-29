/***************************  Date Validations ***************************************************/
function validateDateEqualTo(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	//var objDate = document.getElementsByName(_nmDate)[0]; 
	//var objToDate = document.getElementsByName(_nmToDate)[0];
	
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date.toLocaleString()==toDate.toLocaleString())
		return true;
	else
		return false; 
}

function validateDateEqualToDayBefore(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date.toLocaleString()==(addToDate(toDate,-1,"D")).toLocaleString())
		return true;
	else
		return false; 
}

function validateDateEqualNDayBefore(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	//alert(date.toLocaleString()+" == "+toDate.toLocaleString() +"  =="+(date.toLocaleString() == toDate.toLocaleString() )+"  <"+(date<toDate)+"  >"+(date>toDate)+"  <="+(date<=toDate)+"  >="+(date>=toDate));
	//alert(date+" == "+addToDate(toDate,-1,"D")+"  "+(date == addToDate(toDate,-1,"D")));
	if(date.toLocaleString()==toDate.toLocaleString() || date.toLocaleString()==(addToDate(toDate,-1,"D")).toLocaleString())
		return true;
	else
		return false; 
}

function validateDateEqualNDayAfter(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date.toLocaleString()==toDate.toLocaleString() || date.toLocaleString()==(addToDate(toDate,1,"D")).toLocaleString())
		return true;
	else
		return false; 
}

function validateDateEqualNBefore(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date<=toDate)
		return true;
	else
		return false; 
}

function validateDateEqualNAfter(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);

	//alert(date.toLocaleString()+" and "+toDate.toLocaleString()+"  =="+(date.toLocaleString()==toDate.toLocaleString())+
	//"  <"+(date<toDate)+"  >"+(date>toDate)+"  <="+(date<=toDate)+"  >="+(date>=toDate));
	if(date>=toDate)
		return true;
	else
		return false; 
}

function validateDateBeforeOnly(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date<toDate)
		return true;
	else
		return false; 
}

function validateDateAfterOnly(_objDate, _fmtDate, _objToDate, _fmtToDate)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date>toDate)
		return true;
	else
		return false; 
}

function validateDateEqualNBeforeCountDays(_objDate, _fmtDate, _objToDate, _fmtToDate, _beforeDays)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date.toLocaleString()==toDate.toLocaleString() || date.toLocaleString()==(addToDate(toDate,-parseInt(_beforeDays),"D")).toLocaleString())
		return true;
	else
		return false; 
}

function validateDateEqualNAfterCountDays(_objDate, _fmtDate, _objToDate, _fmtToDate, _afterDays)
{
	var date = convertStrToDate(_objDate.value,_fmtDate);
	var toDate = convertStrToDate(_objToDate.value,_fmtToDate);
	
	if(date.toLocaleString()==toDate.toLocaleString() || date.toLocaleString()==(addToDate(toDate,parseInt(_afterDays),"D")).toLocaleString())
		return true;
	else
		return false; 
}
/***************************  End Date Validations ***************************************************/


/***************************  Time Validations  ***************************************************/
function validateTime(_objTime)
{
	//var objTime = document.getElementsByName(_nmTime)[0];
	if(_objTime.value!="")
	{
		if(_objTime.value.length<3 || _objTime.value.length>5)	return false;		
		if(_objTime.value.indexOf(':') == -1)	return false;
		var tokens = _objTime.value.split(":");
		if(tokens.length>2)	return false;
		var hour = parseInt(trimLeftZero(tokens[0]));
		var min = parseInt(trimLeftZero(tokens[1]));
		if(hour<0 || hour>23)	return false;
		if(min<0 || min>59)	return false;
		var time = "";
		if(hour<10)	time+="0"+hour;	else	time+=hour;
		if(min<10)	time+=":"+"0"+min;	else	time+=":"+min;
	}
	return true;
}

function validateTimeEqualNLess(_objTime, _objToTime)
{
	//var objTime = document.getElementsByName(_nmTime)[0];
	//var objToTime = document.getElementsByName(_nmToTime)[0];
	if(_objTime.value!="" && _objToTime.value!="" && validateTime(_objTime) && validateTime(_objToTime))
	{
		var tokensTime = _objTime.value.split(":");
		var tokensToTime = _objToTime.value.split(":");
		var hour1 = parseInt(trimLeftZero(tokensTime[0]));
		var min1 = parseInt(trimLeftZero(tokensTime[1]));
		var hour2 = parseInt(trimLeftZero(tokensToTime[0]));
		var min2 = parseInt(trimLeftZero(tokensToTime[1]));

		if(hour1<hour2)	return true;
		else if(hour1==hour2 && min1<min2)	return true;
		else return false;
	}
	return true;
}
/***************************  End Time Validations  ***************************************************/
