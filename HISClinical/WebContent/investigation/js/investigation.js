var TIMER = 5;
var SPEED=10;
var overAllstatus=false;
var regexForNumericOnlyValidation=/(^\d\d*$)/;
var regexForWordOnlyValidation=/(^\w\w*$)/;
var regexForAlphanumeric=/[^a-zA-Z0-9_]/ ;
var regexForNumeric=/[0-9]*/;
var browserType =""; 
var regExpForValidateNumeric= /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/;
var MONTH_NAMES=new Array('January','February','March','April','May','June','July','August','September','October','November','December','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
var DAY_NAMES=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sun','Mon','Tue','Wed','Thu','Fri','Sat');
var regexParameter=/[a-zA-Z0-9_#$%&'()+,<=>@[\]^{|}~]/;

function LZ(x) {return(x<0||x>9?"":"0")+x}


//function drag Drop Begin
var drag=0;
var xdif=0;
var ydif=0;
var initx="50px";
var inity="50px";
//var popUpMode="";

function begindrag(event){
if(drag==0){
 floatingd = document.getElementById("popupWindow");

 if(floatingd.style.left==""){
  floatingd.style.left=initx;

 }

 if(floatingd.style.top==""){
  floatingd.style.top=inity;
 }

 prex=floatingd.style.left.replace(/px/,"");
 prey=floatingd.style.top.replace(/px/,"");

drag=1;

xdif=event.clientX-prex;
ydif=event.clientY-prey;

}else{
drag=0;

}

}



function mousepos(event){
floatingd = document.getElementById("popupWindow");
if(drag==1){

 floatingd.style.left = event.clientX-xdif+"px";
 floatingd.style.top = event.clientY-ydif+"px";;

}

}



//function drag Drop End

//First character non digit
function checkFirstAlpha(obj,e)
{
  var len=obj.value.length;
  var browserType;
  var keychar;
  browserType=checkBrowserType(e);
  keychar = String.fromCharCode(browserType);
 // var regExp =/^[A-Z_a-z][A-Z_a-z0-9]*/;
  var regExp =/[A-Z_a-z0-9]*/;
	if(len==0)
	{
	   if(validateNumeric(obj))
	    {
	    HISAlert('info','',"Cannot enter number at first place.");
	    obj.value="";
 		obj.focus();
	    return false;
	    }
	}
else
{
  if(regExp.test(keychar)) 
       {
            return true;
       }
   else
   {
       if(browserType == 8)
 		{ 
			return true;
		}
		 if(browserType == 0)
 		{ 
			return true;
		}
		if(browserType == 32)
 		{ 
			return true;
		}
	     HISAlert('info','',"Incorrect format. Please enter in a correct format");
     obj.value="";
 	obj.focus();
 	return false;
  }
 }
}

//Alpha Numeric
function isAlphaNumeric(obj)
{
//HISAlert('info','',"inside alphanumeric");
if(obj.value.search(regexForWordOnlyValidation)==-1) 
{
   HISAlert('info','',"correct format");
}
else{
 HISAlert('info','',"Incorrect format");
 }

}
//generic function
function genericValidationFunction(obj,regex)
{
//HISAlert('info','',"inside genericValidationFunction");
if(obj.value.search(regex)==-1) 
{
   	HISAlert('info','',"correct format");
   	return true;
}
else{
 	HISAlert('info','',"Incorrect format");
 	return false;
 }

}

function isEmpty(obj)
{
//HISAlert('info','','value of obj '+obj.value);
var status=true;
	if(obj!=null && obj.value=='')
	{
	HISAlert('info','',obj.name+' is Empty ');
	obj.focus();
	status=false;
	}
	else 
	status=true;
			return status;
}


function numericOnly(obj)
{
if(obj.value!="")
{
if(regexForNumericOnlyValidation.test(obj.value)) 
	{
	return true;
	}
	else 
	{
	HISAlert('info','','non numeric character in '+obj.name);
	obj.value=""; 
	obj.focus();
	return false;
	}
}	
}

function numericOnlyModified(obj)
{
if(obj.value!="")
{
if(regexForNumericOnlyValidation.test(obj.value)) 
	{
	return true;
	}
	else 
	{
	HISAlert('info','','non numeric character in '+obj.name);
	obj.value=""; 
	obj.focus();
	return false;
	}
}	
}


function leftTrimWhiteSpace(strValue)
{

var objRegExp = /^(\s*)(\b[\w\W]*)$/;

if(objRegExp.test(strValue)) 
{
    strValue = strValue.replace(objRegExp, '$2');
}
//HISAlert('info','',"Left trim"+strValue);
  return strValue;
}

function rightTrimWhiteSpace(strValue) 
{
//HISAlert('info','',"rightTrimWhiteSpace");
var objRegExp = /^([\w\W]*)(\b\s*)$/;
	if(objRegExp.test(strValue)) 
	{
   		strValue = strValue.replace(objRegExp, '$1');
    }
//HISAlert('info','',"Right trim"+strValue);
  return strValue;
}
 
function trimAllWhitespaces(obj) 
{
var objRegExp = /^(\s*)$/;
var strValue=obj.value;
    //check for all spaces
    if(objRegExp.test(strValue)) {
       strValue = strValue.replace(objRegExp, '');
       if( strValue.length == 0)
          obj.value=strValue;
    }

   //check for leading & trailing spaces
   objRegExp = /^(\s*)([\W\w]*)(\b\s*$)/;
   if(objRegExp.test(strValue)) {
       //remove leading and trailing whitespace characters
       strValue = strValue.replace(objRegExp, '$2');
    }
	
	obj.value=strValue;
  }


function search(element1,element2)
{

var mode="";
//HISAlert('info','',element1.value+"  "+element2.value)






if(element1.value != "" && element1.value!="-1")
{
	mode+="SEARCH1";
	}
else
{
	/*for(i=0;i<document.forms[0].selectedRadio.length;i++)
	{
		if(document.forms[0].selectedRadio[i].checked==true)
			mode+=document.forms[0].selectedRadio[i].value;
	}*/
}
if(element2.value != "" && element2.value!="-1")
	mode+="SEARCH2";

if(mode=='')
{
for(i=0;i<document.forms[0].selectedRadio.length;i++)
	{
		if(document.forms[0].selectedRadio[i].checked==true)
			mode+=document.forms[0].selectedRadio[i].value;
	}
}
document.getElementsByName("hmode")[0].value=mode;
document.forms[0].submit();	

}

function isNetscape(v) {
  /*
  ** Check if the browser is Netscape compatible
  **    v  version number
  ** returns  true if Netscape and version equals or greater
  */
   HISAlert('info','','Netscape');
  return isBrowser("Netscape", v);
  }

function isMicrosoft(v) {
  /*
  ** Check if the browser is Microsoft Internet Explorer compatible
  **    v  version number
  ** returns  true if MSIE and version equals or greater
  */
  HISAlert('info','','microsoft');
  return isBrowser("Microsoft", v);
  }

function isBrowser(b,v) {
  /*
  ** Check if the current browser is compatible
  **  b  browser name
  **  v  version number (if 0 don't check version)
  ** returns true if browser equals and version equals or greater
  */
  browserOk = false;
  versionOk = false;

  browserOk = (navigator.appName.indexOf(b) != -1);
  if (v == 0) versionOk = true;
  else  versionOk = (v <= parseInt(navigator.appVersion));
  return browserOk && versionOk;
  }
  
  function checkBrowserType(e)
  {
	  var checkBrowser=false;
	  var keychar;
	  
	  checkBrowser=isBrowser("Netscape", 4);
	  if(checkBrowser==false)
	  {
	  browserType=e.keyCode;
	  }
	  else
	  {
	  browserType=e.which;
	  }
	   //keychar = String.fromCharCode(browserType);
	 return browserType;

 }


// Numeric Only
  function validateNumeric(obj)
  {
	if(regexForNumericOnlyValidation.test(obj.value))
	{
	     return true;
    }
  else
  {
   obj.focus();
   return false; 
  }
}

// added by shweta 
function datecheck(from,to)
{

//HISAlert('info','','datecheck'); 
if(document.getElementsByName(from)[0].value=="")
{
	HISAlert('info','',"First select effective from date");
	document.getElementsByName(to)[0].value="";
	document.getElementsByName(from)[0].focus();
	return false;
}

//HISAlert('info','',isDate(document.getElementsByName(from)[0].value,'dd-MMM-yyyy')  +'   '+isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
if(!isDate(document.getElementsByName(from)[0].value,'dd-MMM-yyyy') && !isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
	{
	HISAlert('info','','Invalid From Date Format : actual Date Format is dd/MM/YY');
	return false;
	}
	
if(!isDate(document.getElementsByName(to)[0].value,'dd-MMM-yyyy') && !isDate(document.getElementsByName(to)[0].value,'dd/MM/yy'))
	{
	HISAlert('info','','Invalid To Date Format : actual Date Format is dd/MM/YY');
	return false;
	}
	
		

else
{
var aArray;
	if(isDate(document.getElementsByName(to)[0].value,'dd/MM/yy'))
	{
	 aArray=(document.getElementsByName(from)[0].value).split("/");
	}
	else
	{
	aArray=(document.getElementsByName(from)[0].value).split("-");
	}
	
	
	var amonth=aArray[1];
	
	var aday=aArray[0];
	var ayear=aArray[2];
	
	var adate=new Date(amonth +" "+ aday+" "+20+ayear);
	if(!isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
	{
		adate=new Date(amonth +" "+ aday+" "+ayear)
	}
	else
		adate=new Date(amonth +" "+ aday+" "+20+ayear)
		
	var bArray;
	
	if(isDate(document.getElementsByName(to)[0].value,'dd/MM/yy'))
		bArray=(document.getElementsByName(to)[0].value).split("/");
	else
		bArray=(document.getElementsByName(to)[0].value).split("-");
		
	var bmonth=bArray[1];
	var bday=bArray[0];
	var byear=bArray[2];
	var bdate=null;
	if(!isDate(document.getElementsByName(to)[0].value,'dd/MM/yy'))
		bdate=new Date(bmonth +" "+ bday+" "+byear);
	else
		bdate=new Date(bmonth +" "+ bday+" "+20+byear);
	
	if(adate>bdate)
	{
	HISAlert('info',''," To date can\'t be less than From date");
	document.getElementsByName(to)[0].value="";
    document.getElementsByName(to)[0].focus(); 
	return false;
	}
	
	if(CheckSysDate(to,"To Date"))
	  return true;
	  
	  
 }
}

function CheckSysDate(from,label)
{
	var currdate = new Date();
	var aArray;
	//HISAlert('info','',!isDate(document.getElementsByName(from)[0].value,'dd/MM/yyyy'));
	if(!isDate(document.getElementsByName(from)[0].value,'dd-MMM-yyyy') && !isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
	{
	HISAlert('info','','Invalid Date Format for '+label+': actual Date Format is dd-MMM-yyyy')
	return false;
	}
	
	if(isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
		aArray=(document.getElementsByName(from)[0].value).split("/");
	else
		aArray=(document.getElementsByName(from)[0].value).split("-");
	
  //  var aArray=(document.getElementsByName(from)[0].value).split("-");
	var amonth=aArray[1];
	
	var aday=aArray[0];
	var ayear=aArray[2];
	var adate=null;
	if(!isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
		bdate=new Date(amonth +" "+ aday+" "+ayear);
	else
		bdate=new Date(amonth +" "+ aday+" "+20+ayear);
    if (adate >= currdate)
	{
	    HISAlert('info','',label+" can\'t be greater than current date");
	    document.getElementsByName(from)[0].value="";
	    document.getElementsByName(from)[0].focus();
	    return false;
	} 
	
return true;	
}

function CheckSysDateGreater(from,label)
{
	//HISAlert('info','',from.value+" "+document.getElementsByName(from)[0].value)
	var currdate = new Date();
	//HISAlert('info','',"currdate "+currdate);
	currdate.setHours(0);
	currdate.setMilliseconds(0);
	currdate.setMinutes(0);
	currdate.setSeconds(0);
	//HISAlert('info','',isDate(document.getElementsByName(from)[0].value,'dd-MMM-yyyy')  +' CheckSysDateGreater  '+isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
	if(!isDate(document.getElementsByName(from)[0].value,'dd-MMM-yyyy')&& !isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
	{
	HISAlert('info','','Invalid Date Format  : actual Date Format is dd-MMM-yyyy')
	return false;
	}
	
    var aArray=(document.getElementsByName(from)[0].value).split("/");
	var amonth=aArray[1];
	
	var aday=aArray[0];
	var ayear=aArray[2];
	var adate=null;
	if(!isDate(document.getElementsByName(from)[0].value,'dd/MM/yy'))
		bdate=new Date(amonth +" "+ aday+" "+ayear);
	else
		bdate=new Date(amonth +" "+ aday+" "+20+ayear);
	
	var mDate=new Date(currdate.getMonth()+" "+currdate.getDay()+" "+currdate.getFullYear());
	
    if (mDate >= adate)
	{
	    HISAlert('info',''," can\'t be less than current date");
	   from.value="";
	    from.focus();
	    return false;
	} 
	
return true;	
}
//by shweta
function checkCRNo()
{
var valid=true;

if(validateLength(document.forms[0].patCRNo.value,13))
	{
		if(numericOnlyModified(document.forms[0].patCRNo))
		{
		valid=true;
		return true;
		}
	}
else
{
	valid=false;     
	HISAlert('info','',"InValid CR Number");
	document.forms[0].patCRNo.value="";
	document.forms[0].patCRNo.focus();
	return false;
}
}



function validateMinLength(elem,minlen)
{
	var isValid=true;
	if ((elem.length<minlen))
	  {
		isValid = false;
	  }
   return isValid;
 } 
 
function isAlpha(elem,fieldName)
{
//HISAlert('info','',"parseInt(elem.value) is "+parseInt(elem.value));
if(parseInt(elem.value))
{
HISAlert('info','',"Can't put Numeric Value in "+fieldName);
elem.value="";
elem.focus();
return false;
}
else
return true;
}


function getDateFromFormat(val,format) {
	val=val+"";
	format=format+"";
	var i_val=0;
	var i_format=0;
	var c="";
	var token="";
	var token2="";
	var x,y;
	var now=new Date();
	var year=now.getYear();
	var month=now.getMonth()+1;
	var date=1;
	var hh=now.getHours();
	var mm=now.getMinutes();
	var ss=now.getSeconds();
	var ampm="";
	
	while (i_format < format.length) {
		// Get next token from format string
		c=format.charAt(i_format);
		token="";
		while ((format.charAt(i_format)==c) && (i_format < format.length)) {
			token += format.charAt(i_format++);
			}
		// Extract contents of value based on format token
		if (token=="yyyy" || token=="yy" || token=="y") {
			if (token=="yyyy") { x=4;y=4; }
			if (token=="yy")   { x=2;y=2; }
			if (token=="y")    { x=2;y=4; }
			year=_getInt(val,i_val,x,y);
			if (year==null) { return 0; }
			i_val += year.length;
			if (year.length==2) {
				if (year > 70) { year=1900+(year-0); }
				else { year=2000+(year-0); }
				}
			}
		else if (token=="MMM"||token=="NNN"){
			//HISAlert('info','','here');
			month=0;
			for (var i=0; i<MONTH_NAMES.length; i++) {
				var month_name=MONTH_NAMES[i];
				if (val.substring(i_val,i_val+month_name.length).toLowerCase()==month_name.toLowerCase()) {
					if (token=="MMM"||(token=="NNN"&&i>11)) {
						month=i+1;
						if (month>12) { month -= 12; }
						i_val += month_name.length;
						break;
						}
					}
				}
			if ((month < 1)||(month>12)){return 0;}
			}
		else if (token=="EE"||token=="E"){
			for (var i=0; i<DAY_NAMES.length; i++) {
				var day_name=DAY_NAMES[i];
				if (val.substring(i_val,i_val+day_name.length).toLowerCase()==day_name.toLowerCase()) {
					i_val += day_name.length;
					break;
					}
				}
			}
		else if (token=="MM"||token=="M") {
			month=_getInt(val,i_val,token.length,2);
			if(month==null||(month<1)||(month>12)){return 0;}
			i_val+=month.length;}
		else if (token=="dd"||token=="d") {
			date=_getInt(val,i_val,token.length,2);
			if(date==null||(date<1)||(date>31)){return 0;}
			i_val+=date.length;}
		else if (token=="hh"||token=="h") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<1)||(hh>12)){return 0;}
			i_val+=hh.length;}
		else if (token=="HH"||token=="H") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<0)||(hh>23)){return 0;}
			i_val+=hh.length;}
		else if (token=="KK"||token=="K") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<0)||(hh>11)){return 0;}
			i_val+=hh.length;}
		else if (token=="kk"||token=="k") {
			hh=_getInt(val,i_val,token.length,2);
			if(hh==null||(hh<1)||(hh>24)){return 0;}
			i_val+=hh.length;hh--;}
		else if (token=="mm"||token=="m") {
			mm=_getInt(val,i_val,token.length,2);
			if(mm==null||(mm<0)||(mm>59)){return 0;}
			i_val+=mm.length;}
		else if (token=="ss"||token=="s") {
			ss=_getInt(val,i_val,token.length,2);
			if(ss==null||(ss<0)||(ss>59)){return 0;}
			i_val+=ss.length;}
		else if (token=="a") {
			if (val.substring(i_val,i_val+2).toLowerCase()=="am") {ampm="AM";}
			else if (val.substring(i_val,i_val+2).toLowerCase()=="pm") {ampm="PM";}
			else {return 0;}
			i_val+=2;}
		else {
			if (val.substring(i_val,i_val+token.length)!=token) {return 0;}
			else {i_val+=token.length;}
			}
		}
	// If there are any trailing characters left in the value, it doesn't match
	if (i_val != val.length) { return 0; }
	// Is date valid for month?
	if (month==2) {
		// Check for leap year
		if ( ( (year%4==0)&&(year%100 != 0) ) || (year%400==0) ) { // leap year
			if (date > 29){ return 0; }
			}
		else { if (date > 28) { return 0; } }
		}
	if ((month==4)||(month==6)||(month==9)||(month==11)) {
		if (date > 30) { return 0; }
		}
	// Correct hours value
	if (hh<12 && ampm=="PM") { hh=hh-0+12; }
	else if (hh>11 && ampm=="AM") { hh-=12; }
	var newdate=new Date(year,month-1,date,hh,mm,ss);
	return newdate.getTime();
	}

function isDate(val,format) {
//HISAlert('info','','isDate investigation.js')
	var date=getDateFromFormat(val,format);
	if (date==0) { return false; }
	return true;
	}
	function _isInteger(val) {
	var digits="1234567890";
	for (var i=0; i < val.length; i++) {
		if (digits.indexOf(val.charAt(i))==-1) { return false; }
		}
	return true;
	}
function _getInt(str,i,minlength,maxlength) {
	for (var x=maxlength; x>=minlength; x--) {
		var token=str.substring(i,i+x);
		if (token.length < minlength) { return null; }
		if (_isInteger(token)) { return token; }
		}
	return null;
	}
	

function checkingCrNo(obj)
{
	var valid=true;

	if(validateMinLength(obj.value,13))
	{
			if(numericOnlyModified(obj))
			{
			valid=true;
			return true;
			}
	}
	else
	{
		valid=false;    
		HISAlert('info','',"Invalid CR Number");
		obj.value="";
		obj.focus();
		return false;
	}
}	
function validateLength(elem,minlen)
{
	var isValid=false; 
	if ((elem.length == minlen))
	  {
		isValid = true;
	  }
   return isValid;
 } 
 
function functionForKeyPress(obj,e)
  {
    var browserType;
  var keychar;
  browserType=checkBrowserType(e);
  keychar = String.fromCharCode(browserType);
 
  if(regexForNumericOnlyValidation.test(keychar)) 
       {
            return true;
       }
   else
   {        
       if(browserType == 8)
 		{ 
			return true;
		}
		 if(browserType == 0)
 		{ 
			return true;
		}
		if(browserType == 32)
 		{ 
			return true;
		}
         
         HISAlert('info','',"Incorrect format. Please enter in a correct format");
     obj.value="";
 	obj.focus();
 	return false;
  }
 }

function checkingSampleNo(obj)
{
	var valid=true;
function keyhandlerforMasters(obj,e)
{
  var browserType;
  var keychar;
  browserType=checkBrowserType(e);
  keychar = String.fromCharCode(browserType);
  		if(browserType == 8)
 		{ 
			return true;
		}
		 if(browserType == 0)
 		{ 
			return true;
		}
		if(browserType == 32)
 		{ 
			return true;
		}
		if(browserType == 47)
 		{ 
			return true;
		}
		if(browserType == 46)
 		{ 
			return true;
		}
		if(browserType >=65 && browserType <= 90 || browserType >=97 && browserType <= 122 )
		{
	
		return true;
		}

		else
		{
		HISAlert('info','',"Can't enter space,digits or special charecters at first place");
		return false;
		}
		
		
function keyhandler(e,param)
{
var length=param.value.length;

if(length==0)
{
	if(e.which >=65 && e.which <= 90 || e.which >=97 && e.which <= 122 )
	{
	
	return true;
	}
	else
	{
		HISAlert('info','',"Can't enter space,digits or special charecters at first place");
		return false;
	}
}
else
{
  if(e.which==32 || e.which >=65 && e.which <= 90 || e.which >=97 && e.which <= 122 || e.which==13 || e.which==8)
    return true;
  else
  {
	  alert (" special characters and digits are not allowed.\n Please remove them and try again.");
	  return false;  
  }
  }  
}

function datecheckwithoutCurrentDateCheck(from,to)
{

	if(!isDate(document.getElementsByName(from)[0].value,'dd-MMM-yyyy'))
		{
			HISAlert('info','','Invalid From Date Format : actual Date Format is dd-MMM-YYYY');
			return false;
		}
	
	if(!isDate(document.getElementsByName(to)[0].value,'dd-MMM-yyyy'))
		{
			HISAlert('info','','Invalid To Date Format : actual Date Format is dd-MMM-YYYY');
			return false;
		}
	
	if(document.getElementsByName(from)[0].value=="")
		{
			HISAlert('info','',"First select effective from date");
			document.forms[0].toDate.value="";
			return false;
		}		
	else
		{
			var aArray=(document.getElementsByName(from)[0].value).split("-");
			var amonth=aArray[1];
			var aday=aArray[0];
			var ayear=aArray[2];
			var adate=new Date(amonth +" "+ aday+" "+ayear);
			var bArray=(document.getElementsByName(to)[0].value).split("-");
			var bmonth=bArray[1];
			var bday=bArray[0];
			var byear=bArray[2];
			var bdate=new Date(bmonth +" "+ bday+" "+byear);
	
			if(adate>bdate)
				{
					HISAlert('info',''," To date can\'t be less than From date");
					document.forms[0].toDate.value="";
    				document.forms[0].toDate.focus(); 
					return false;
				}
				
		 }
		 
}

}



	if(validateMinLength(obj.value,20))
	{
			if(numericOnly(obj))
			{
			valid=true;
			return true;
			}
	}
	else
	{
		valid=false;    
		HISAlert('info','',"InValid Sample Number");
		obj.value="";
		obj.focus();
		return false;
	}
}	

function validateAlphaNumericWithSpaceOnly(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//HISAlert('info','',key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==95))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ _+-/").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

function sysAfter(from_date,msg)
{
	//HISAlert('info','',"hi inside investigation.js");
	if(msg=="") 
		msg="Date Is smaller than Current Date";
	//HISAlert('info','',"from Date=== " +from_date);
	
	var flag=true;
	var mon=new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");
	if(isDate(document.getElementsByName(from_date)[0].value,'dd/MM/yy'))
		var seperator="/";
	else
		var seperator="-";
		
	if(document.getElementsByName(from_date)[0].value.replace(/\s*/,"")=="")
		flag=true;	
		
	else if(dateValidator(document.getElementsByName(from_date)[0].value,seperator) )
		{
			var curD=new Date();	
			var splits=(from_date.value).split(seperator);
			if(curD.getFullYear()>splits[2])//for entered year is small	
				{	
					HISAlert('info','',msg);
					flag=false;	
				}	
			else if(curD.getFullYear()==splits[2])//for entered year equals current year
				{	
					var i=0;	
					for(;i<mon.length;i++)	
					if(splits[1].toUpperCase()==mon[i])
							break;	
					if(curD.getMonth()>i)//entered month is small	
					{	
						HISAlert('info','',msg);	
						flag=false;	
					}
						
					else if(curD.getMonth()==i)//entered month is equals current month	
					{		
						if(curD.getDate()>splits[0])
						{								
							HISAlert('info','',msg);
							from_date.value="";
							flag=false;	
						}
							
						else	
							flag=true;	
					}	
						
					else			
					flag=true;		
				}	
		}//end of else if(dateValidator(from_date.value,seperator) )
	
		else
		{	
			flag=false;
			
			from_date.focus();	
			
		}	
		//HISAlert('info','',"flag=" + flag);
return flag;
	}//end of sysAfter(obj.seperator)
	
function dateValidator(objVal,seperator)//value Based
{	
	//HISAlert('info','',"inside date validator in datevalidation.js");
	var dateSplit=objVal.split(seperator); 
	var flag=true;	
	if(objVal.replace(/\s*/,"")=="")
		flag=true;
	else if(dateSplit.length!=3)
	{
		HISAlert('info','',"Use format DD"+seperator +"MMM"+seperator+"YYYY");
		flag=false;
	}	
else if((dateSplit[0].replace(/\s*/,"")=="" || isNaN(dateSplit[0])) || (dateSplit[2].replace(/\s*/,"")=="" || isNaN(dateSplit[2])))
	{
		HISAlert('info','',"Check Either Date or Year is not Correctly Entered");
		flag=false;	
}	else if(!isMonth(dateSplit[1]))
	{		HISAlert('info','',"Month Is Not Correctly Entered!");
		flag=false;
	}	
else if(dateSplit[2].length!=4)
	{	
	HISAlert('info','',"Enter Full Year");
		flag=false;
	}	
else	{
		if(dateSplit[0]>=0 && dateSplit[0]<=getDays(dateSplit[1],dateSplit[2]))	
			flag=true;
		else
		{

			HISAlert('info','',"Date Is Not Correctly Entered");	

		flag=false;

		}
	}
	return flag;
}//End od dateValidator
	


function openSiteDiagnosis(url,obj)
{
	//10811906070910000101#0#template#11051130
	var values= obj.name.split('#')
	if(values.length==4)
	{
	mywindow=window.open (url+"?requisitionDNo="+values[0]+"&sessionNo="+values[1]+"&elementCode="+values[3],"mywindow","location=1,status=0,scrollbars=1,width=580,height=500");
	mywindow.moveTo(250,250);
	}
	else if(values.length==2)
	{
		mywindow=window.open (url+"?elementCode="+values[1],"mywindow","location=1,status=0,scrollbars=1,width=580,height=500");
		mywindow.moveTo(250,250);
	}
	else
	{
	}
	
	
	
}


function openTestResults(url,obj)
{
	//10811906070910000101#0#template#11051130
	var values= obj.name.split('#')
	if(values.length==4)
	{
	mywindow=window.open (url+"?requisitionDNo="+values[0]+"&sessionNo="+values[1]+"&elementCode="+values[3],"mywindow","location=1,status=0,scrollbars=0,width=500,height=500");
	mywindow.moveTo(250,250);
	}else if(values.length==2)
	{
		if(url.indexOf('?')==-1)
		{
			mywindow=window.open (url+"?elementCode="+values[1],"mywindow","location=1,status=0,scrollbars=0,width=500,height=500");
		}
		else
		{
			mywindow=window.open (url+"&elementCode="+values[1],"mywindow","location=1,status=0,scrollbars=0,width=500,height=500");
		}
	mywindow.moveTo(250,250);
	}
	else
	{
	}

	
}

/*function openImageUtility(url,thisobj,mapkey)
{
	//10811906070910000101#0#template#11051130
	//var values= obj.name.split('#');
	var primaryKey=document.forms[0].primaryKey.value;
	var primaryKey2=document.forms[0].laboratoryCode.value.substring(0,3);
	//HISAlert('info','',primaryKey+"2--->"+primaryKey2);
	if(primaryKey==null || primaryKey=='')
	{
		primaryKey=mapkey.substring(mapkey.indexOf('#')+1,mapkey.length);
		HISAlert('info','','primary key'+primaryKey);
	}
	else
	{
		primaryKey+="_"+mapkey.substring(mapkey.indexOf('#')+1,mapkey.length);
	}
	
	//mywindow=window.open (url+"&primaryKey="+primaryKey+"&elementId="+replaceHashWithUnderScore(thisobj.name)+"&mapKey="+mapkey.substring(mapkey.indexOf('#')+1,mapkey.length)+"&submitMode=UPDATE&primaryKey2="+primaryKey2,"mywindow","location=1,status=0,scrollbars=0,width=500,height=500");
	//mywindow.moveTo(250,250);
	
	var tempUrl=url+"&primaryKey="+primaryKey+"&elementId="+replaceHashWithUnderScore(thisobj.name)+"&mapKey="+mapkey.substring(mapkey.indexOf('#')+1,mapkey.length)+"&submitMode=UPDATE&primaryKey2="+primaryKey2;
	dojo.addOnLoad(function() {
        // create the dialog:
        secondDlg = new dijit.Dialog({
            title: "Programatic Dialog Creation",
            style: "width: 300px",
            content: "<iframe src='"+tempUrl+"' id='famePdf' style='width: 600px;height: 400px'></iframe>",
            onCancel : closeDialog,
     		draggable : true
        });
    });
    secondDlg.show();
	
	//dojo.when(diag,"onClose",alert("Submitting for duplicate Printing"));
	
}
function closeDialog()
{
	 diag.destroyRecursive();
	 
  //alert("..In closeDialog");
}
*/

function replaceHashWithUnderScore(str)
{
	var newStr=str.replace(/#/gi,'_');
	//HISAlert('info','',newStr);
return newStr;
}
function replaceUnderScoreWith(str)
{
	str=str.replace('_','#');
return str;
}

function openAntiMicrobial(url,thisobj,mapkey,resultEntryGroupIndex)
{
	//10811906070910000101#0#template#11051130
	//var values= obj.name.split('#');
	alert(url+" "+mapkey+" "+resultEntryGroupIndex);
	var elementCode=thisobj.name.split('#')[3];
	mywindow=window.open (url+'&requisitionDNo='+mapkey+'&elementCode='+elementCode+'&sessionNo='+resultEntryGroupIndex,"antimicrobial","location=1,status=0,width=600,height=600");
	mywindow.moveTo(300,300);
		
}

// start code for css menu functionaity in 
//Result Entry//ResultValidation//ResultReValidation


//for ajax calling
function init(url)
{
	//HISAlert('info','',url);
try
{ 
// Firefox, Opera 8.0+, Safari  
req=new XMLHttpRequest();  }
catch (e)  
{  
//Internet Explorer 
try
{    req=new ActiveXObject("Msxml2.XMLHTTP");    }
catch (e)
{    
try
  {      req=new ActiveXObject("Microsoft.XMLHTTP");      }
catch (e)
  {      HISAlert('info','',"Your browser does not support AJAX!");      return false;      }    }  }  

//HISAlert('info','','hiiiiiiiiiiii');
req.open("GET", url, true);
req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
}
function ajaxResponseForDocument()
{
	if (req.readyState == 4)
	{
		if (req.status == 200) 
		{
			 var id;
			 var str=req.responseText;
			// alert(str);
			 if(str!=null && str!="" && str!='3')
			 {
				
					 //Popup.hide('myDiv');
					 if(str!='3'){removeDiv();}
				 
				 setResponse();
			 }
			 else
			 {
				 //alert("in else setResponse")
				  //Popup.hide('myDiv');				 
				 //removeDiv();
				 setResponse();
			 }
		}	
	}
}
//setting response
function setResponse()
{
	document.forms[0].hmode.value='OTHERFUNCTIONALITYFORWORD';
	//alert("Selected Test is Repeat "+document.forms[0].hmode.value);
	document.forms[0].submit();
}


//for cssMenu Process
function CancelledTest(sampleNo,workOrderGroupIndex,workOrderIndex,fromProcess,typeMode)
{
	//alert("typeMode------->"+typeMode);	
	var strhmode='';
	if(typeMode=='CANCELTEST')
	{
		strhmode='RESULTENTRYVALIDATIONCANCEL';
	}
	else if(typeMode=='RESCHEDULETEST')
	{
		strhmode='RESULTENTRYVALIDATIONRESCHEDULE';
	}
	else if(typeMode=='RERESULTENTRY')
	{
		strhmode='RESULTENTRYVALIDATIONRERESULTENTRY';	
	}
	
	var url='';
		url='/AHIMS/investigation/transaction/otherFunctionality.cnt?hmode='+strhmode+'&workOrderGroupIndex='+workOrderGroupIndex+'&sampleNo='+sampleNo+'&workOrderIndex='+workOrderIndex+'&fromProcess='+fromProcess;
	if(sampleNo!="")
	{
		//alert(document.getElementById("rejectionReasonIdForSample").options[document.getElementById("rejectionReasonIdForSample").selectedIndex].value);
		if(typeMode!='RERESULTENTRY')
		url+='&cancelledCode='+document.getElementById("rejectionReasonIdForSample").options[document.getElementById("rejectionReasonIdForSample").selectedIndex].value;
		
	}
	else
	{
		//alert(document.getElementById("rejectionReasonIdForPatient").options[document.getElementById("rejectionReasonIdForPatient").selectedIndex].value);
		if(typeMode!='RERESULTENTRY')
		url+='&cancelledCode='+document.getElementById("rejectionReasonIdForPatient").options[document.getElementById("rejectionReasonIdForPatient").selectedIndex].value;
		
	}
	//alert(url);
	init(url);	
	req.onreadystatechange = ajaxResponseForDocument;
	req.send(null);
	
}

function performAction(typeMode,workOrderGroupIndex,workOrderIndex,sampleNo,fromProcess)
{
	//fromOption : 1 for result validation <--> 2 for result revalidation <-->3 for result entry	
	
	if(sampleNo==""||sampleNo==null)
	sampleNo="0";
	
	if(typeMode=='CANCELTEST')
	{
		//alert(typeMode);
		createDiv(sampleNo,workOrderGroupIndex,workOrderIndex,fromProcess,typeMode);
	}
	else if(typeMode=='RESCHEDULETEST')
	{
		//alert(typeMode);
		createDiv(sampleNo,workOrderGroupIndex,workOrderIndex,fromProcess,typeMode);
	}
	else if(typeMode=='RERESULTENTRY')
	{
		//alert(typeMode);
		CancelledTest(sampleNo,workOrderGroupIndex,workOrderIndex,fromProcess,typeMode);
		//createDiv(sampleNo,workOrderIndex,fromProcess,typeMode);
	}
}
//create div element
function createDiv(sampleNo,workOrderGroupIndex,workOrderIndex,fromProcess,typeMode) {


var newdiv = document.getElementById('myDiv'); 
//alert('newdiv  '+newdiv)
var table=document.createElement('table');
table.id='myDivTable';
table.width="100%";
newdiv.appendChild(table);
var tr=document.createElement('tr');
table.appendChild(tr);
tr.innerHTML="<td class='header' width='100%' colspan='2'><div align='left'></div></td>";
tr=document.createElement('tr');
table.appendChild(tr);  
tr.innerHTML="<td class='tdfonthead' width='50%'><div align='left'><b>Rejection Reason</b></div></td>";

if(sampleNo!="")
{
	  tr.innerHTML+="<td class='tdfont' width='50%'>"+rejectedReasonComboForSample+"<td>";
}
else
{
	  tr.innerHTML+="<td class='tdfont' width='50%'>"+rejectedReasonComboForPatient+"<td>";	  
}
tr=document.createElement('tr');
table.appendChild(tr);
td=document.createElement('td');
tr.appendChild(td);
td.width='100%';
td.colSpan='2';  
td.innerHTML="<div align='center'><img src='/AHIMS/hisglobal/images/ok.gif' onclick=CancelledTest("+sampleNo+","+workOrderGroupIndex+","+workOrderIndex+","+fromProcess+",'"+typeMode+"') /><img src='/AHIMS/hisglobal/images/btn-ccl.png' onclick='removeDiv()'/></div>";
//alert(newdiv.innerHTML);  
Popup.showModal('myDiv');
}

function removeDiv() {
	  //HISAlert('info','',divNum);
	  //HISAlert('info','',divNum.id);
	  var d = document.getElementById('myDiv');
	  var olddiv = document.getElementById('myDivTable');
	  //HISAlert('info','','delete');
	  d.removeChild(olddiv);
	  Popup.hide('myDiv');
}
// end code for css menu functionait
function updateAllEditorsValueToTextArea()
{
	
	var textareaElements=document.getElementsByTagName('textarea');
	var textareaElementsLength=textareaElements.length;
	
	for(var textAreaIndex=0;textAreaIndex<textareaElementsLength;textAreaIndex++)
	{
		var editorElement=document.getElementById(textareaElements[textAreaIndex].name);
		if(editorElement !=null && editorElement!='undefined')
		{
			WYSIWYG.updateTextArea(textareaElements[textAreaIndex].name);
		}
		
	}
	

}

function assignTabIndex()
{
var tableIndex=0;

for(i=0;i<document.forms[0].elements.length;i++)
{
if((document.forms[0].elements[i].tagName=='INPUT' && document.forms[0].elements[i].type!='hidden') || document.forms[0].elements[i].tagName=='SELECT' || document.forms[0].elements[i].tagName=='TEXTAREA'|| document.forms[0].elements[i].tagName=='textarea')
{
	//HISAlert('info','',document.forms[0].elements[i].tagName);
	if(tableIndex==0)
	{
	tableIndex++;
	document.forms[0].elements[i].tabindex=tableIndex;
   
	
	document.forms[0].elements[i].focus();
	}
	else
	{
	tableIndex++;
	//HISAlert('info','','assign-->'+document.forms[0].elements[i].tagName);
	document.forms[0].elements[i].onFocus="callOnFocus(this)";
	document.forms[0].elements[i].tabindex=tableIndex;
	}
}

}
}


function numericOnly(evnt,obj)
{// Ascii Code 0 - 48 To 9 - 57 , for . - 46
	try
	{
	 
		var flag = true;
		
		var key =0 ,character;
		if( typeof evnt.charCode != 'undefined')
		{	
			key = evnt.keyCode;
			character = evnt.charCode;
		}
		else 
			character=evnt.keyCode;
		var pattern=/\./;
		if( key!=0 || ( !pattern.test(obj.value) && character==46) ||(obj.value.length==0 && character==45) || (character>=48 && character<= 57))
			return true;
		else
			return false;
	}
	catch(e)
	{
		HISAlert('info','',"numericOnly() Error Message -> "+e.message);
		return false;
	}
}


function numericOnlyWithEvent(evnt,obj)
{// Ascii Code 0 - 48 To 9 - 57 , for . - 46
	try
	{
	
		var flag = true;
		
		var key =0 ,character;
		if( typeof evnt.charCode != 'undefined')
		{	
			key = evnt.keyCode;
			character = evnt.charCode;
		}
		else 
			character=evnt.keyCode;
		var pattern=/\./;
		if( key!=0 || ( !pattern.test(obj.value) && character==46) ||(obj.value.length==0 && character==45) || (character>=48 && character<= 57))
			return true;
		else
			return false;
	}
	catch(e)
	{
		HISAlert('info','',"Error Message -> "+e.message);
	}
}

function viewComboText(comboobj){
if(comboobj.options.length>0 && comboobj.value!="-1"){
	for(var i=0;i<comboobj.options.length;i++)
		if(comboobj.options[i].value==comboobj.value){
			comboobj.title=comboobj.options[i].text;
			break;
		}
	}
}

function emptycombo(comboobj){
  if(comboobj){
  comboobj.options.length=0;
  comboobj.options.length=1;
  comboobj.options[0].value="-1";
  comboobj.options[0].text="--Select Value--";
  comboobj.value="-1";
  }    
} 

function getUniqueId()
{
     var dateObject = new Date();
     var uniqueId =dateObject.getHours()+':'+dateObject.getMinutes()+':'+dateObject.getSeconds();
     return uniqueId;
}
/*****************************************************************/
/************investigation alert start*******************/
/*****************************************************************/


function CSSAlertPanelModalArea(){
	 var objIframe=document.getElementById('AlertPanelModalArea');
	 objIframe.style.left=0;
	 objIframe.style.top=0;
	 //objIframe.style.height="150%";
	 //objIframe.style.width="150%";
	 var the_height= document.body.scrollHeight;  
  	 var the_width= document.body.scrollWidth;
  	 objIframe.style.height=the_height;
	 objIframe.style.width=the_width;	
	 
	 objIframe.style.position = "absolute";
	 objIframe.style.background="silver";
	 var value=6;
	 objIframe.style.opacity = value/10;
	 objIframe.style.filter = 'alpha(opacity=' + value*10 + ')';	 
	  
}
function CSSAlertPanel(left,top,height,width,border){
	 var objDiv=document.getElementById('IDALERTDIVCONTENT');
	 objDiv.style.left=left;
	 objDiv.style.top=top;
	 objDiv.style.height=height;
	 objDiv.style.width=width;
	 objDiv.style.border=border;
	 objDiv.style.marginTop="-100px";
	 objDiv.style.marginLeft="-200px";	 
	 objDiv.style.position = "absolute";	 
	 objDiv.style.background="#FFF6ED";	 
	 objDiv.style.zIndex="100";       
}
function CSSTitle(){
	
	 var objP=document.getElementById('divtitle');
	 objP.style.margin="0";
	 objP.style.display="";
	 objP.style.lineHeight="20px";
	 objP.style.color="white";	 
	 objP.style.fontWeight="normal";
	 objP.style.padding="0 0 0 5px";
	 objP.style.backgroundColor="#086fa6";
	// objP.style.backgroundImage = "url(/AHIMS/hisglobal/images/shd-trans-FFB468.png)";	 
	 objP.style.backgroundPosition="left";
	 objP.style.backgroundRepeat="repeat-y"
	 objP.style.verticalAlign="top";
}

function ToggleAlertPanel(id)
{
	var panelContainer = document.getElementById(id);
	//clearInterval(document.getElementById('AlertPanel').timer);
	if (panelContainer.style.display == "none")
	{
		panelContainer.style.display = "";
		//document.getElementById('AlertPanel').style.display='';
		document.getElementById('AlertPanelModalArea').focus();
		document.body.onfocus = function() { document.getElementById('AlertPanelModalArea').focus(); };		
	}
	else
	{
		//document.getElementById('AlertPanel').style.display='none';
		panelContainer.style.display = "none";		
		document.body.onfocus = function() { return true; };
		fadeHISAlert(0);		
	}	
}
/*
alertType={"error","info" ,"help" }
title= title of alert box
alertString = content to be displayed in alert  
*/
function HISAlert(aType,title,alertString){
try{
//alert("alertType-" + aType + " title=" + title + " alertString=" + alertString);

var imageName="alertcross.png";

if(aType="" || aType=="info")
{
// alert(title.length)
 if(title==""){ 
 	title="HIS Information !"; 	
 }	
  imageName="alertinfo.png";   
}

if(aType=="error")
{
 if(title=="")
 	title="HIS Error !";
  imageName="alertcross.png"; 
}
if(aType=="help")
{
 if(title=="")
 	title="HIS Help !";
  imageName="alerthelp.png"; 
}

if(!document.getElementById("AlertPanel")){
 var divTag = document.createElement("div");
 divTag.id="AlertPanel";
 divTag.style.display="none";
 var str ="<iframe frameborder='0' scrolling='0' id='AlertPanelModalArea'></iframe>";
	str+="<div id='IDALERTDIVCONTENT' ><div id='divtitle' ><b>"+title+"</b></div>";
	str+="<table width='100%' style='vertical-align:top;'  cellpadding='0' cellspacing='0'>";
	str+="<tr><td width='20%' style='vertical-align:top;' >";
	str+="<img style='width:50px;height:50px;' id='alertImg' src='/AHIMS/hisglobal/images/"+imageName+"'></td>";
	str+="<td width='80%' style='vertical-align:middle;' ><div id='divAlertString'>"+alertString+"</div></td></tr></table><br>";
	str+="<table width='100%' style='vertical-align:bottom;'  cellpadding='0' cellspacing='0'>";
	str+="<tr><td  style='vertical-align:top;' ><div align='center'>";
	str+="<img tabindex='0' id='alertOK' src='/AHIMS/hisglobal/images/ok.gif'  onkeypress='return closepopup(event,this);' onclick=\"ToggleAlertPanel('AlertPanel')\">";
	str+="</div></td></tr></table><input type='hidden' name='objToFocus' ><br></div>";
  divTag.innerHTML=str;
  document.body.appendChild(divTag);  
 
 
}
else
{
 document.getElementById("divtitle").innerHTML=title;
 document.getElementById("divAlertString").innerHTML=alertString;
 document.getElementById("alertImg").src='/AHIMS/hisglobal/images/'+imageName;
}

var left="50%";
var top="50%";
var height="120px";
var width="300px";
var border="solid 1px black";

CSSAlertPanelModalArea();
CSSAlertPanel(left,top,height,width,border);
CSSTitle();
//HISAlert('info','',alertString.length)

var objDiv=document.getElementById('IDALERTDIVCONTENT');
var thisText = alertString.length;
if (title.length > alertString.length)
{ thisText = title.length; }

aWidth = (thisText * 5) + 80;
aHeight = 125;

if(thisText>50){
objDiv.style.width = "auto";
objDiv.style.height = "auto";

}
//objDiv.style.left = (document.body.clientWidth - aWidth)/2;
//objDiv.style.top = (document.body.clientHeight - aHeight)/2;
 
ToggleAlertPanel('AlertPanel');
clearInterval(document.getElementById('AlertPanel').timer);
document.getElementById('AlertPanel').timer = setInterval("fadeHISAlert(1)", TIMER);
var autohide=10;
 if(autohide) { 
    window.setTimeout("hideDialog()", (autohide * 1000));
  } 
}
catch(e)
{
 alert(alertString);
 //alert("exception \nError is : "  + e.message );
}  
}
function hideDialog() {
  var dialog = document.getElementById('AlertPanel');
  clearInterval(dialog.timer);
  dialog.timer = setInterval("fadeHISAlert(0)", TIMER);  
  dialog.style.display = "none";		
  document.body.onfocus = function() { return true; };	
}



function closepopup(e,obj){
try{
if(obj.id=='alertOK' && e.keyCode==13){
 ToggleAlertPanel('AlertPanel');
 fadeHISAlert(0);
 return true;
}
else{
 return false;
}
}
catch(e){
	alert("Exception here")
}
}

// fade-in the dialog box //
function fadeHISAlert(flag) {
  if(flag == null) {
    flag = 1;
  }  
  if(flag == 1) {  
  	if((document.activeElement.id!='AlertPanel' || document.activeElement.id!= 'alertOK') && document.getElementsByName("objToFocus")[0].value==""){  		
  		document.getElementsByName("objToFocus")[0].value=document.activeElement.name;
    }  
    document.getElementById("alertOK").focus();
  }
  else
  {
  	clearInterval(document.getElementById('AlertPanel').timer);
  	var objForFocus=document.getElementsByName(document.getElementsByName("objToFocus")[0].value)[0];	
	if(objForFocus){
	 	objForFocus.focus();	 	
	}
	document.getElementsByName("objToFocus")[0].value="";
  } 
}
/*****************************************************************/
/************investigation alert ends*******************/
/*****************************************************************/

function validateResultEntryGroupDetails(processCheckboxName)
{
	var isSubmit=false;
	var i=0;
	for(i=0;i<document.getElementsByName(processCheckboxName).length;i++)
	{
		if(document.getElementsByName(processCheckboxName)[i].checked==true)
		{
			j=0;
			var value=document.getElementsByName(processCheckboxName)[i].value;
			var patternString="^"+value+"";
			var regularExpression=new RegExp(patternString);
			var k=0;

			for(k=0;k<document.getElementsByTagName("select").length;k++)
			{
			var elementChecked=document.getElementsByTagName("select")[k];
				if(regularExpression.test(elementChecked.name))
					{
						if(elementChecked.selectedIndex==-1 || elementChecked.selectedIndex==0)
						{
							HISAlert('info','',"Select the field Focussed");
							elementChecked.focus();
							return false;
						}
					}
			}

			for(k=0;k<document.getElementsByTagName("input").length;k++)
			{
			var elementChecked=document.getElementsByTagName("input")[k];
				if(regularExpression.test(elementChecked.name))
				{
				trimAllWhitespaces(elementChecked);
					if(elementChecked.value.length==0)
					{
						HISAlert('info','',"Enter the field Focussed");
					elementChecked.focus();
					return false;
					}
			}
			}

	for(k=0;k<document.getElementsByTagName("TextArea").length;k++)
	{
	var elementChecked=document.getElementsByTagName("TextArea")[k];
	if(regularExpression.test(elementChecked.name))
	{
	trimAllWhitespaces(elementChecked);
	if(elementChecked.value.length==0)
	{
		HISAlert('info','',"Enter the field Focussed");
	elementChecked.focus();
	return false;
	}
	}
	}
	
	}
	}

	isSubmit=true;
	
	return isSubmit;
	
}
function validateResultEntryDetails(processCheckboxName)
{
	var isSubmit=false;
	var i=0;
	for(i=0;i<document.getElementsByName(processCheckboxName).length;i++)
	{
		if(document.getElementsByName(processCheckboxName)[i].checked==true)
		{
			j=0;
			var value=document.getElementsByName(processCheckboxName)[i].value;
			var patternString="^"+value+"";
			var regularExpression=new RegExp(patternString);
			var k=0;

			for(k=0;k<document.getElementsByTagName("select").length;k++)
			{
			var elementChecked=document.getElementsByTagName("select")[k];
				if(regularExpression.test(elementChecked.name))
					{
						if(elementChecked.selectedIndex==-1 || elementChecked.selectedIndex==0)
						{
							HISAlert('info','',"Select the field Focussed");
							elementChecked.focus();
							return false;
						}
					}
			}

			for(k=0;k<document.getElementsByTagName("input").length;k++)
			{
			var elementChecked=document.getElementsByTagName("input")[k];
				if(regularExpression.test(elementChecked.name))
				{
				trimAllWhitespaces(elementChecked);
					if(elementChecked.value.length==0)
					{
						HISAlert('info','',"Enter the field Focussed");
					elementChecked.focus();
					return false;
					}
				}
			}

	for(k=0;k<document.getElementsByTagName("TextArea").length;k++)
	{
	var elementChecked=document.getElementsByTagName("TextArea")[k];
	if(regularExpression.test(elementChecked.name))
	{
	trimAllWhitespaces(elementChecked);
	if(elementChecked.value.length==0)
	{
		HISAlert('info','',"Enter the field Focussed");
	elementChecked.focus();
	return false;
	}
	}
	}
	isSubmit=true;
	}
	}

	
	
	return isSubmit;
	
}



/***********************Inv Div POPUP*******************/


//script for create popup in div frame
function create_div_dynamic(submitMode,title,width,height,ditachMode)
{
	//alert('create_div_dynamic');
	if(document.getElementById("backgroundFilter")==null)
	{

		var dv = document.createElement('div');
		var strDiv="";
		var currentWindows=getf1Document(window);
		//alert("currentWindows in Create Div-->?"+currentWindows);
		if(currentWindows!=null && currentWindows!=undefined)
		{
			//alert("currentWindows in !Null-->?")
			if(currentWindows.document.getElementById("f1")!=null && currentWindows.document.getElementById("f1")!=undefined)
			{
				//alert("f1 in !Null-->?")
				var fsDocument=currentWindows.document.getElementById("f1").contentWindow.document;
				//alert(fsDocument+'frames length ::=');
				if(fsDocument.getElementById("backgroundFilterfs1")==null)
				{
					//alert("backgroundFilterfs1 created");
					strDiv="<div id='backgroundFilterfs1'></div>";
					fsDocument.body.innerHTML+=strDiv;
				}
						
			}
		}
		//alert("False");
		strDiv="";
		strDiv="<div id='backgroundFilter'></div>" ;
		var popUpWindowDivInnerHtml="";
		if(document.getElementById("popupWindow")==null)
		{
			//alert("CreateElement");
			popUpWindowDivInnerHtml="<div id='popupWindow' ><input type='hidden' id='popUpMode' value='DIVMODEL'><div id='topLeft' align='left' onclick='begindrag(event)'>"+title+"</div><div id ='topRight' align='right'><img height='15' width='15' src='/AHIMS/hisglobal/images/PopOut.jpg' alt='Detach' style='cursor: pointer' onclick='DetachDiv(\""+ditachMode+"\");'/><img height='15' width='15' src='/AHIMS/hisglobal/images/Mini.jpg' alt='Maximize' style='cursor: pointer' onclick='MaximizeDiv(\""+width+"\",\""+height+"\",\""+submitMode+"\",\""+ditachMode+"\");'/><img height='13' width='13' src='/AHIMS/hisglobal/images/close.jpg' alt='Close' style='cursor: pointer' onclick='closedDiv(\""+submitMode+"\")'/></div><div id='popupBody' ><iframe src='#' id='famePdf' style='width: 600px;height: 400px'></iframe></div></div>";			
			dv.innerHTML=strDiv+popUpWindowDivInnerHtml;	
			document.forms[0].appendChild(dv);
		}
		else
		{
			document.getElementById("topLeft").innerHTML=title;
			document.getElementById("popupBody").innerHTML="<iframe src='#' id='famePdf' style='width: 600px;height: 400px'></iframe>";
		}
		//document.in appendChild(strDiv);
		
		
		
	}
}

function MinimizeDiv(width,height,submitMode,ditachMode)
{
	
	//"<img height='15' width='15' src='/AHIMS/hisglobal/images/Mini.jpg' alt='Minimize' style='cursor: pointer' onclick='MaximizeDiv(\""+width+"\",\""+height+"\");'/><img height='13' width='13' src='/AHIMS/hisglobal/images/close.jpg' alt='Close' style='cursor: pointer' onclick='closedDiv(\""+submitMode+"\");'/>"
	document.getElementById("topRight").innerHTML="<img height='15' width='15' src='/AHIMS/hisglobal/images/PopOut.jpg' alt='Detach' style='cursor: pointer' onclick='DetachDiv(\""+ditachMode+"\");'/><img height='15' width='15' src='/AHIMS/hisglobal/images/Mini.jpg' alt='Maximize' style='cursor: pointer' onclick='MaximizeDiv(\""+width+"\",\""+height+"\",\""+submitMode+"\",\""+ditachMode+"\");'/><img height='13' width='13' src='/AHIMS/hisglobal/images/close.jpg' alt='Close' style='cursor: pointer' onclick='closedDiv(\""+submitMode+"\");'/>";
	document.getElementById('popupBody').style.display='block';
	document.getElementById("popupBody").style.width=(width)+"px";
	document.getElementById("popupBody").style.height=(height)+"px";
	//document.getElementById("popupWindow").style.top=top+"px";
	//document.getElementById("popupWindow").style.left=left+"px";
	
	document.getElementById("famePdf").style.width=(width)+"px";
	document.getElementById("famePdf").style.height=(height)+"px";
	
	document.getElementById("popupWindow").style.width=(parseInt(width)+6)+"px";
	document.getElementById("popupWindow").style.height=(parseInt(height)+25)+"px";
	
	document.getElementById("popupWindow").style.left="40px";
	document.getElementById("popupWindow").style.top="40px";
	alert("parseInt(width/2)-------->"+(parseInt(width)+6)/2);
	document.getElementById("topLeft").style.width=(parseInt(width)+6)/2+"px";
	document.getElementById("topRight").style.width=(parseInt(width)+6)/2+"px";
	
	
}
function MaximizeDiv(width,height,submitMode,ditachMode)
{
	
	
	//"<img height='15' width='15' src='/AHIMS/hisglobal/images/maximize.jpg' alt='Maximize' style='cursor: pointer' onclick='MinimizeDiv(\""+width+"\",\""+height+"\");'/><img height='13' width='13' src='/AHIMS/hisglobal/images/close.jpg' alt='Close' style='cursor: pointer' onclick='closedDiv(\""+submitMode+"\");'/>"
	
	document.getElementById("topRight").innerHTML="<img height='15' width='15' src='/AHIMS/hisglobal/images/PopOut.jpg' alt='Detach' style='cursor: pointer' onclick='DetachDiv(\""+ditachMode+"\");'/><img height='15' width='15' src='/AHIMS/hisglobal/images/maximize.jpg' alt='Minimize' style='cursor: pointer' onclick='MinimizeDiv(\""+width+"\",\""+height+"\",\""+submitMode+"\",\""+ditachMode+"\");'/><img height='13' width='13' src='/AHIMS/hisglobal/images/close.jpg' alt='Close' style='cursor: pointer' onclick='closedDiv(\""+submitMode+"\");'/>";
	
	document.getElementById('popupBody').style.display='block';
	document.getElementById("popupBody").style.width=(parseInt(width)+176)+"px";
	document.getElementById("popupBody").style.height=(parseInt(height)+225)+"px";
	//document.getElementById("popupWindow").style.top=top+"px";
	//document.getElementById("popupWindow").style.left=left+"px";
	
	document.getElementById("famePdf").style.width=(parseInt(width)+176)+"px";
	document.getElementById("famePdf").style.height=(parseInt(height)+225)+"px";
	
	document.getElementById("popupWindow").style.width=(parseInt(width)+180)+"px";
	document.getElementById("popupWindow").style.height=(parseInt(height)+250)+"px";
	
	document.getElementById("popupWindow").style.left="20px";
	document.getElementById("popupWindow").style.top="20px";
	
	//alert("parseInt(width/2)-------->"+(parseInt(width)+176)/2);
	document.getElementById("topLeft").style.width=(parseInt(width)+176)/2+"px";
	document.getElementById("topRight").style.width=(parseInt(width)+176)/2+"px";
}
function closedDiv(mode)
{
try
{
//document.forms[0].hmode.value=document.forms[0].mode.value;		
popUp();
backgroundFilter();
//document.getElementById('famePdf').src="#";
if(mode!='null')
{
	//alert(mode);
	document.forms[0].hmode.value=mode;//'RESULTDUPLICATEPRINTING';
	document.forms[0].submit();
}
}
catch (e) {
	// TODO: handle exception
	/*if(document.getElementById('popupWindow')!=undefined && document.getElementById('popupWindow')!=null)
	{
		alert("Remove Div");
		document.body.removeChild(document.getElementById('popupWindow'));

	}*/	
}
}
function DisableBackUrl(url,submitMode,width,height,title,ditachMode)
{
	//alert('DisableBackUrl Step--1');
	
	create_div_dynamic(submitMode,title,width,height,ditachMode);
	
	popUp();backgroundFilter();
	
	//alert('DisableBackUrl Step--2'); 
	
	document.getElementById('famePdf').src=url;	
	document.getElementById('popupBody').style.display='block';
	document.getElementById("popupBody").style.width=(width)+"px";
	document.getElementById("popupBody").style.height=(height)+"px";
	//document.getElementById("popupWindow").style.top=top+"px";
	//document.getElementById("popupWindow").style.left=left+"px";
	
	document.getElementById("famePdf").style.width=(width)+"px";
	document.getElementById("famePdf").style.height=(height)+"px";
	
	document.getElementById("popupWindow").style.width=(parseInt(width)+6)+"px";
	document.getElementById("popupWindow").style.height=(parseInt(height)+25)+"px";
	
	document.getElementById("popupWindow").style.left="40px";
	document.getElementById("popupWindow").style.top="40px";
	
	//alert("parseInt(width/2)-------->"+(parseInt(width)+6)/2);
	document.getElementById("topLeft").style.width=(parseInt(width)+6)/2+"px";
	document.getElementById("topRight").style.width=(parseInt(width)+6)/2+"px";
	
	
}

function DetachDiv(ditachMode)
{
	//document.getElementById('myframe').src;
	alert(ditachMode);
	var url=document.getElementById('famePdf').src;
	popUp();
	backgroundFilter();
		if(document.getElementById('popupWindow')!=null)
		{
			//popUpMode="POPUP";
			
			if(ditachMode!='null')
			{	
			
			    alert(url+"mode"+document.forms[0].mode.value+"mode"+document.forms[0].hmode.value);				
				document.forms[0].hmode.value=document.forms[0].mode.value;				
				document.forms[0].hmode.value=document.forms[0].mode.value+'UPDATETESTOBJECTS';			       
			    alert("mode"+document.forms[0].mode.value+"mode"+document.forms[0].hmode.value);		       
				document.forms[0].submit();
				   
			}
			
			var newWindow = window.open(url,'newWin','toolbar=yes,location=yes,scrollbars=yes,status=yes,width=630,height=400');
			newWindow.moveTo(250,250);
			newWindow.focus();
			
			if(!newWindow.opener)
				newWindow.opener = self;
				   
		
		
		}
	
}

/***********************Inv Div POPUP*******************/
/*****************************************************************/
/************investigation alert ends*******************/
/*****************************************************************/


function disableElements(processCheckboxName)
{
	var isSubmit=false;
	var i=0;
	
	for(i=0;i<document.getElementsByName(processCheckboxName).length;i++)
	{
		if(document.getElementsByName(processCheckboxName)[i].checked==false)
		{
			j=0;
			var value=document.getElementsByName(processCheckboxName)[i].value;
			var patternString="^"+value+"";
			var regularExpression=new RegExp(patternString);
			var k=0;
				for(k=0;k<document.getElementsByTagName("select").length;k++)
				{
				var elementChecked=document.getElementsByTagName("select")[k];
					if(regularExpression.test(elementChecked.name))
					{
					elementChecked.disabled=true;
					}
				}
		
				for(k=0;k<document.getElementsByTagName("input").length;k++)
				{
				var elementChecked=document.getElementsByTagName("input")[k];
					if(elementChecked.disabled.type!='button'&& regularExpression.test(elementChecked.name))
					{
					elementChecked.disabled=true;
					}
				}
		
				for(k=0;k<document.getElementsByTagName("TextArea").length;k++)
				{
				var elementChecked=document.getElementsByTagName("TextArea")[k];
			
					if(regularExpression.test(elementChecked.name))
					{
					elementChecked.disabled=true;
					}
				}
		}
		
		isSubmit=true;
	}
	
	
	return isSubmit;
	
}

function getFormValues(fobj)

{
	var str = "";
	var valueArr = null;
	var val = "";
	var cmd = "";

	for ( var i = 0; i < fobj.elements.length; i++)

	{
		switch (fobj.elements[i].type)
		{
			
		case "text":

			str += fobj.elements[i].name + "="
					+ escape(fobj.elements[i].value) + "&";
			break;

		 case "textarea":

			str += fobj.elements[i].name + "="
					+ escape(fobj.elements[i].value) + "&";
			break;
		
		case "hidden":

			str += fobj.elements[i].name + "="
					+ escape(fobj.elements[i].value) + "&";
			break;
		case "select-one":

			str += fobj.elements[i].name
					+ "="
					+ fobj.elements[i].options[fobj.elements[i].selectedIndex].value
					+ "&";
			break;

		}
	}

	str = str.substr(0, (str.length - 1));
	return str;

}

function initpost(url,parameters)
{
	//HISAlert('info','',url);
try
{ 
// Firefox, Opera 8.0+, Safari  
req=new XMLHttpRequest();  }
catch (e)  
{  
//Internet Explorer 
try
{    req=new ActiveXObject("Msxml2.XMLHTTP");    }
catch (e)
{    
try
  {      req=new ActiveXObject("Microsoft.XMLHTTP");      }
catch (e)
  {      HISAlert('info','',"Your browser does not support AJAX!");      return false;      }    }  }  

//HISAlert('info','','hiiiiiiiiiiii');
req.open("POST", url, true);
req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//alert(parameters.length);
req.setRequestHeader("Content-length", parameters.length);
req.setRequestHeader("Connection", "close");
}

function validatefromDate(fromDate,toDate,currentDate,msg)
{
	if(sysBefore(document.getElementsByName(fromDate)[0],null)==false)
	{
		document.getElementsByName(fromDate)[0].value=currentDate;
	}else{datecheck(fromDate,toDate);}
}
function validatetoDate(fromDate,toDate,currentDate,msg)
{
	if(datecheck(fromDate,toDate)==true)
	{
		if(sysBefore(document.getElementsByName(toDate)[0],null)==false)
		{
			document.getElementsByName(toDate)[0].value=currentDate;
		}
	}
}
function resinitilizePageSubmit()
{
	document.forms[0].currentPageNo.value="1";
	document.forms[0].currentblock.value="1";
	
}
function callAjaxFunctionCheckDuplicateExists(Obj)
{
	var strUrl="";
	var strTempSampleNo=null;
	if(Obj==null)
	{
		for(var i=0;i<document.getElementsByName("tempSampleNo").length;i++)
		{
			
			if(strTempSampleNo==null)
			{
				strTempSampleNo=document.getElementsByName("tempSampleNo")[i].value;
			}
			else
			{
				strTempSampleNo+=","+document.getElementsByName("tempSampleNo")[i].value;
			}
			/*strUrl="/AHIMS/investigation/transaction/sampleCollection.cnt?hmode=checkDuplicateSampleNo&currentSampleNo="+document.getElementsByName("tempSampleNo")[i].value;
			
			if(getDuplicateOrNot(strUrl)==false)
			{
				document.getElementsByName("tempSampleNo")[i].focus();
			}
			*/
		}
		
		strUrl="/AHIMS/investigation/transaction/sampleCollection.cnt?hmode=checkDuplicateSampleNo&currentSampleNo="+strTempSampleNo;
		
		
		
		if(getDuplicateOrNot(strUrl)==false)
		{
			alert("OBJ::NULL::::Inside getDuplicateOrNot");
			return false;
		}
		
	}
	else
	{
		strUrl="/AHIMS/investigation/transaction/sampleCollection.cnt?hmode=checkDuplicateSampleNo&currentSampleNo="+Obj.value;
		//getDuplicateOrNot(strUrl);		
		
		if(getDuplicateOrNot(strUrl)==false)
		{
			return false;
		}
		
		
	}
	
	return true;
}
function getDuplicateOrNot(url)
{
	var flag=true;
	var count=0;
	var xhrArgs1 = {url: url,sync:true, postData: "",handleAs: "text",
			load: function(data) 
			{
				//alert("1"+data);
				var falseValue="";
				if(data!=null && data!="")
				{
					var arrData=data.split(",");
					for(var i=0;i<arrData.length;i++)
					{
						var arrResult=arrData[i].split("@");
						if(parseInt(arrResult[1])>0)
						{
							count++;
							falseValue=arrResult[0];
						}
					}	
				}
				//alert("2"+falseValue);
				if(count>0)
				{
					
					for(var j=0;j<document.getElementsByName("tempSampleNo").length;j++)
					{
						if(document.getElementsByName("tempSampleNo")[j].value==falseValue)
						{
							
							flag=false;	
							alert("Sample no "+arrResult[0]+" already Exists."+flag);									
							//document.getElementsByName("tempSampleNo")[j].focus();
							return flag;
						}																	
					}
					//alert("if");
				}
				
			},
			 error: function(error) {
	            alert(error+"Error while loading User Infomation please login again or may be user noth authorized to raise tests");
	           
	        }};

			var deferred1 = dojo.xhrPost(xhrArgs1);
			
	return flag;		
}

function callCheckDuplicateExists()
{
	//alert("callCheckDuplicateExists");
	var strCurrent="";
	for(var k=0;k<document.getElementsByName('tempSampleNo').length;k++)
	{
		var count=0;
		for(var l=0;l<document.getElementsByName('tempSampleNo').length;l++)
		{
			if(document.getElementsByName('isSelectedSample')[k].checked==true)
			{
				if(document.getElementsByName('tempSampleNo')[k].value==document.getElementsByName('tempSampleNo')[l].value)
				{
					count++;
				}
				
			}
		}
			if(count>1)
			{
				alert("Sample no "+document.getElementsByName('tempSampleNo')[k].value+" already Exists.");
				document.getElementsByName("tempSampleNo")[k].focus();
				return false;	
			}	
		}
	return true;
	}
	
	
	
function deSelectCheckAllIfOneItemIsDeSelected(checkAllElementName,ItemsElementName)
{


var checkAllFun=false;

    for(i=0;i<document.getElementsByName('ItemsElementName').length;i++)
    {
        if(document.getElementsByName('ItemsElementName')[i].checked==false)
        {
            checkAllFun=true;
            break;
        }
    }

    if(checkAllFun)
        {
        
            document.getElementsByName('checkAllElementName')[0].checked=false;
        
   	}
    else
    {
            document.getElementsByName('checkAllElementName')[0].checked=true;

    }

}		
