/*
 Patient Attendance VIEW JS

 author: Debashis Sardar

 dated: 01/02/2012
*/
function getServiceName(obj)
{
	
	
	if(obj.value==0)
	{
			var objVal = document.getElementsByName("strServiceName")[0];
	        objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' ><option value='0'>Select Value</option></select>";
	
		document.getElementById("ID_HEADER").style.display="";
	    document.getElementById("ID_CAN_CLR").style.display="";
	    document.getElementById("ID_PAT_LIST_HEADER").style.display="none";
	    var objVal=document.getElementById("ID_PAT_LIST");
	    objVal.innerHTML = "";
	
	}
	else
	{	document.getElementById("ID_HEADER").style.display="";
	    document.getElementById("ID_CAN_CLR").style.display="";
	    document.getElementById("ID_PAT_LIST_HEADER").style.display="none";
	    var objVal=document.getElementById("ID_PAT_LIST");
	    objVal.innerHTML = "";
	    
		var serviceTypeId=obj.value;
		var url='/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?';
		url+='hmode=GETSERVICENAME';
		url+='&serviceTypeId=' + serviceTypeId;
		ajaxFunction(url,"1");
	}
}
function serviceNameChange(obj)
{
	if(obj.value==0)
	{
		document.getElementById("ID_HEADER").style.display="";
		document.getElementById("ID_CAN_CLR").style.display="";
		document.getElementById("ID_PAT_LIST_HEADER").style.display="none";
		var objVal=document.getElementById("ID_PAT_LIST");
		objVal.innerHTML = "";
	}
}



function getDateDiff(date_1,date_2) 
{
var diff=0;
if(date_1==date_2)
{
diff=1;
return diff;
}
var retVal=compare_Date(date_1,date_2);
if(retVal.mode==0)
{
var ret=parse_Date(date_1,"-");
var ret1=parse_Date(date_2,"-");
var dt1=ret.month+"-"+ret.day+"-"+ret.year;
var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
date1 = new Date();
date2 = new Date();
diff  = new Date();

{ // Validates first date 
var myDate1=new Array();
myDate1=dt1.split("-");
date1temp = new Date(myDate1[2],(myDate1[0]-1),myDate1[1]);
date1.setTime(date1temp.getTime());
}
{ // Validates second date 
var myDate2=new Array();
myDate2=dt2.split("-");
date2temp = new Date(myDate2[2],(myDate2[0]-1),myDate2[1]);
date2.setTime(date2temp.getTime());
}
// sets difference date to difference of first date and second date
diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
timediff = diff.getTime();
weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
timediff -= days * (1000 * 60 * 60 * 24);
days=parseInt(weeks)*7+days;
var diff = days ;
return diff;
}
}
function GOFunc()
{
	  var frmName=document.forms[0].name;
	  var hisValidator = new HISValidator(frmName); 
	  var diff=0;
	  //alert(document.forms[0].strEffectiveTo.value);  
	hisValidator.addValidation("strServiceType", "dontselect=0", "Please Select Service Type !" );
    hisValidator.addValidation("strServiceName", "dontselect=0", "Please Select Service Name !" );
    
    hisValidator.addValidation("strEffectiveFrom", "req","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "req","To Date is a mandatory field");
	
	/*hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCtDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");*/
	/*if(document.forms[0].strEffectiveTo.value>document.forms[0].strCtDate.value)
	{
		alert("Please Select To Date Less Than or Equal To Current Date");
		reurn false;
	}*/
	var obj = compareDate(document.forms[0].strEffectiveTo.value , document.forms[0].strCtDate.value);	 	  
	if(obj.mode == '2')
	{
		alert("Please Select To Date Less Than or Equal To Current Date");
		return false;
	}
	var obj1 = compareDate(document.forms[0].strEffectiveTo.value , document.forms[0].strEffectiveFrom.value);	 	  
	if(obj1.mode == '0')
	{
		alert("Please Select To Date Greater Than Or Equal To From Date");
		return false;
	}
		
	  	 diff=getDateDiff(document.forms[0].strEffectiveFrom.value,document.forms[0].strEffectiveTo.value);
		  //alert(document.forms[0].strEffectiveTo.value);  
		  //alert(diff);
		var retVal = hisValidator.validate(); 
		if(retVal)
		{
			//alert(diff);
		 if(diff<=90)
		 {
			var serviceTypeId=document.forms[0].strServiceType.value;
			var strServiceName=document.forms[0].strServiceName.value;
			var strEffectiveFrom=document.forms[0].strEffectiveFrom.value;
			var strEffectiveTo=document.forms[0].strEffectiveTo.value;
			
			for(var j=1;j<=(strServiceName.split("^").length);j++)
			{
			strServiceName=	strServiceName.replace("^","@");
			}
			var url='/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?';
			url+='hmode=PATIENTSTATUSVIEW';
			url+='&serviceTypeId=' + serviceTypeId;
			url+='&strServiceName=' + strServiceName;
			url+='&strEffectiveFrom=' + strEffectiveFrom;
			url+='&strEffectiveTo=' + strEffectiveTo;
			//alert(url);
			ajaxFunction(url,"2");
		}
	 	else
	 	{
			alert("From Date and To Date difference should not be greater than 90 days.");
	 		return false;
		}
	}
	else
	{
		return false;
	}
}

function back()
{
	document.forms[0].hmode.value="GO";
 	document.forms[0].submit();
}
function formClear()
{
	 document.forms[0].hmode.value="CLEARVIEW";
     document.forms[0].submit();
}
function getAjaxResponse(res,mode)
{
 
 	if(mode=="1")
   	{
       var serviceName=res.split("@")[0];
	   var objVal = document.getElementsByName("strServiceName")[0];
	   if(serviceName=="" ||serviceName==null)
	       objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' ><option value='0'>Select Value</option></select>";
	   else
	       objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' >"+serviceName+"</select>";
	  
   	}
  	if(mode=="2")
   	{
	   document.getElementById("ID_PAT_LIST_HEADER").style.display="block";
	   var objVal=document.getElementById("ID_PAT_LIST");
	       objVal.innerHTML = res;
	 
  	}
}