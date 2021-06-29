<!--By prashantMi -->

<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>


<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.reports.controller.fb.InvestigationListingReportFB"%>
 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/Cannedstyle.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonUtility.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
   
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/new_investigation/js/" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />
<%-- <his:javascript src="/new_investigation/js/validationInv_rptNew.js" /> --%>
<%@page import="hisglobal.hisconfig.Config"%>
 


<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
 

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
 
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>


<html>

 <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>


 

 <script type="text/javascript">
 
 
 
 
 

function validate(){
	
	var dt = document.getElementsByName('dateTypeCheck')[0].value;
	if(dt=="datewise")
	{
	 if(!validateTodayOrDate())
    	 {return false;}
	}
	else
	{
	 if(!validateHrMin())
		 {return false;}
	}
	
	
var tCode = document.getElementsByName("testCode")[0].value;
var lCode = document.getElementsByName("labCode")[0].value;
var dCode = document.getElementsByName("deptCode")[0].value;
var sCode = document.getElementsByName("sampleCode")[0].value;

if(document.getElementById('dateCheck1').checked==true)
{
	var fd=document.getElementsByName("fromDate")[0].value;
	var td=document.getElementsByName("toDate")[0].value;
	var diff=dateDiffNew(fd,td);
	var d=parseInt(diff);
	//alert("days"+diff);
	var m= Math.floor(d/30);
	//alert("months"+m);
	if(m > 3)
		{
			alert("Enter date within 3 months");
			return false;
		}	
}

if(dCode=="-1")
{
alert("select dept");
return false;
}
if(lCode=="-1")
{
alert("select lab");
return false;
}
if(tCode=="-1")
	{
	alert("select test");
	return false;
	}
if(sCode=="-1")
{
alert("select sample");
return false;
}

	
	  /*  if(!validateFromMin())
		  {
		 	return false;
		  }
	  if(!validateFromHr())
	  {
	 	return false;
	  }
	
	  if(!validateToHr())
		 {return false;}
	 
	  if(!validateToMin())
		 {return false;}   */
	  

	
	
		document.forms[0].hmode.value = "SHOWRPT";
		
			/* if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
			{
				document.forms[0].target = "_self";
			}else{
				document.forms[0].target = "_blank";
			} */
		document.forms[0].target = "_self";
		document.forms[0].submit();
		 
	}
	
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}


function onClear(){

	document.forms[0].hmode.value="NEW";
	document.forms[0].submit();

} 
function onClearButton()
{
	document.forms[0].reset();
	onLoadPageCheck();
	onClearSet(); 
	onClear();

} 

function onLoadPage()
{

	document.forms[0].strIsFooter.checked = true;
	
}


function getTest() {
	//call new mode in action GETTEST
	
	var dt = document.getElementsByName('dateTypeCheck')[0].value;
	//alert(dt);
	if(dt=="datewise")
		{
		//alert("d");
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
	else if(dt=="today")
		{
		//alert("t");
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="";
		}
	else if(dt=="datewise1")
		{
		//alert("d");
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
		
	
	/* var sCode = document.getElementsByName("sampleCode")[0].value;
	sCode="%";
	document.getElementsByName("sampleCode")[0].value=sCode; */
		document.forms[0].hmode.value="GETTEST";
		document.forms[0].submit();
}



function getLab() {
	
	
	var dt = document.getElementsByName('dateTypeCheck')[0].value;
	//alert(dt);
	if(dt=="datewise")
		{
		//alert("d");
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
	else if(dt=="today")
		{
		//alert("t");
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="";
		}
	else if(dt=="datewise1")
		{
		//alert("d");
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
	
		document.forms[0].hmode.value="GETLAB";
		document.forms[0].submit();
		
		
		
}


function getSample() {
	
	
	var dt = document.getElementsByName('dateTypeCheck')[0].value;
	//alert(dt);
	if(dt=="datewise")
		{
		//alert("d");
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
	else if(dt=="today")
		{
		//alert("t");
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="";
		}
	else if(dt=="datewise1")
		{
		//alert("d");
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
		document.forms[0].hmode.value="GETSAMPLE";
		document.forms[0].submit();
		
		
		
}


/* function validateDatewisemonth()
{
	if(document.getElementById('dateCheck1').checked==true)
	{
		var fd=document.getElementsByName("fromDate")[0].value;
		var td=document.getElementsByName("toDate")[0].value;
		var diff=dateDiffNew(fd,td);
		var d=parseInt(diff);
		alert("days"+diff);
		var m= Math.floor(d/30);
		alert("months"+m);
		if(m >= 3)
			{
				alert("Enter correct date");
				//return false;
			}	
}
 */





function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}

function setDateTime(obj)
{
	
	//alert("inside here");
	document.getElementsByName('dateTypeCheck')[0].value=obj.value;
	document.getElementById("dateWiseCheck").style.display="";
	document.getElementById("timeWiseCheck").style.display="none";
}
	
function setTimeCheck(obj)
{
	document.getElementsByName('dateTypeCheck')[0].value=obj.value;
	//alert("inside here");
	document.getElementById("dateWiseCheck").style.display="none";
	document.getElementById("timeWiseCheck").style.display="";
}
	
function onLoadPageCheck()
{
		
	var dt = document.getElementsByName('dateTypeCheck')[0].value;
	//alert(dt);
	if(document.getElementsByName('dateTypeCheck')[0].value!='')
		{
			
		if(dt=="datewise")
		{
		//alert("d");
		document.getElementById('todayCheck').checked=false;
		document.getElementById('dateCheck').checked=true;
		document.getElementById('dateCheck1').checked=false;
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
		else if(dt=="today")
		{
		//alert("t");
		document.getElementById('todayCheck').checked=true;
		document.getElementById('dateCheck').checked=false;
		document.getElementById('dateCheck1').checked=false;
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="";
		}
		else if(dt=="datewise1")
		{
			//alert("d");
			document.getElementById('todayCheck').checked=false;
			document.getElementById('dateCheck1').checked=true;
			document.getElementById('dateCheck').checked=false;
			document.getElementById("dateWiseCheck").style.display="";
			document.getElementById("timeWiseCheck").style.display="none";
			}
		}
	else
		{
		document.getElementById('todayCheck').checked=true;
		
		}
	//alert(document.getElementsByName('frmHr')[0].value);
	if(document.getElementsByName('frmHr')[0].value=='')
		{
		document.getElementsByName('frmHr')[0].value="00";
		document.getElementsByName('frmMin')[0].value="01";
		document.getElementsByName('fromHrTime')[0].value="00";
		document.getElementsByName('fromMinTime')[0].value="01";
	
		}
	else
		{
		
		document.getElementsByName('fromHrTime')[0].value=document.getElementsByName('frmHr')[0].value;
		document.getElementsByName('fromMinTime')[0].value=document.getElementsByName('frmMin')[0].value;
		}
	    
	if(document.getElementsByName('toHr')[0].value=='')
	 {
		time();
	 }
	
	else
	{
		document.getElementsByName('toHrTime')[0].value=document.getElementsByName('toHr')[0].value;
		document.getElementsByName('toMinTime')[0].value=document.getElementsByName('toMin')[0].value;
	}
	
}
	 
	function setTimeN(obj)
	{
		//alert("inside set Time");
		//alert(document.getElementsByName('fromHrTime')[0].value);
		document.getElementsByName('frmHr')[0].value=document.getElementsByName('fromHrTime')[0].value;
		document.getElementsByName('frmMin')[0].value=document.getElementsByName('fromMinTime')[0].value;
		document.getElementsByName('toHr')[0].value=document.getElementsByName('toHrTime')[0].value;
		document.getElementsByName('toMin')[0].value=document.getElementsByName('toMinTime')[0].value;
	}


function checkTime(i) {
    if (i<10) {i = "0" + i; }
    return i;
} 
    
    
 
function compareTime(){ 
	// alert("inside compareTime");
           if(valFromHr > valToHr)
            {
              alert("From hr cannot be greater than to hour");
              document.getElementsByName('fromHr')[0].focus();            
              return false;
            }
            if(valFromHr == valToHr)
            {
               if(valFromMin >= valToMin)
               {
                 alert ("From min should be less than to min");
                 document.getElementsByName('fromMin')[0].focus(); 
                 return false;                            
               }
            }
            return true;
        }   
	
	function validateHrMin(){
	   success=false;
	   
	   document.getElementsByName('frmHr')[0].value=document.getElementsByName('fromHrTime')[0].value;
	   
	//   alert ("validateHrMin");
	    if(validatefromHr()&& validateToHr()&& validateFromMin() && validateToMin()){
	       if(compareTime()){
    	       success=true;
    	   }    	    
	    }       
      return success ;
	}
	
     
     
	function validatefromHr(){	
//	 alert("inside validate validatefromHr");
      valFromHr=document.getElementsByName('fromHrTime')[0].value;
      
   //alert('valFromHr'+valFromHr);
	  if(valFromHr==null||valFromHr==""){	  	       
	      alert("Please Enter value in Hr Field");	  	      
	      document.getElementsByName('fromHr')[0].focus();
	      return false;     
	  }
	  if(valFromHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('fromHour')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
        
    function validateToHr(){	 
    	valToHr=document.getElementsByName('toHrTime')[0].value;
	  if(valToHr==null||valToHr==""){	  	       
	      alert("Please Enter value in To Hr Field");	  	      
	      document.getElementsByName('toHour')[0].focus();
	      return false;     
	  }
	  if(valToHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('toHour')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
	function validateToMin(){	 
		valToMin=document.getElementsByName('toMinTime')[0].value;
	  if(valToMin==null||valToMin==""){	  	       
	      alert("Please Enter value in TO Min Field");	  	      
	      document.getElementsByName('toMin')[0].focus();
	      return false;     
	  }
	  if(valToMin>59){
	    alert("To Min Value canont be greater than 59");
	    document.getElementsByName('toMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}
	
	function validateFromMin(){	 
	  valFromMin=document.getElementsByName('fromMinTime')[0].value;
	  if(valFromMin==null||valFromMin==""){	  	       
	      alert("Please Enter value in From Min Field");	  	      
	      document.getElementsByName('fromMin')[0].focus();
	      return false;     
	  }
	  if(valFromMin>59){
	    alert("From Min Value canont be greater than 59");
	    document.getElementsByName('fromMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}
    
	
	function time()
	{
		<%String reqdTime=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "HH:mm:ss");%>
		
		var today=new Date();
  		/* var h=today.getHours();
    	var m=today.getMinutes();
    	var s=today.getSeconds(); */
    	
    	var h=<%=reqdTime.split(":")[0]%>;
    	var m=<%=reqdTime.split(":")[1]%>;
    	var s=<%=reqdTime.split(":")[2]%>;
    	
    	m = checkTime(m);
    	s = checkTime(s);
    
   	 //	var t = setTimeout(function(){startTime()},500);
 		document.getElementById("hr").value = h;
		document.getElementById("min").value = m;
	
		document.getElementsByName('toHr')[0].value=h;
		document.getElementsByName('toMin')[0].value=m;
		document.getElementsByName('toHrTime')[0].value=h;
		document.getElementsByName('toMinTime')[0].value=m;
	}
	
	
     
	function onClearSet()
	{
	
		
		var dCode = document.getElementsByName("deptCode")[0].value;
		var sCode = document.getElementsByName("sampleCode")[0].value;
		document.getElementsByName('fromHrTime')[0].value="00";
		document.getElementsByName('fromMinTime')[0].value="01";
		document.getElementsByName('frmHr')[0].value="00";
		document.getElementsByName('frmMin')[0].value="01";
		/* document.getElementsByName('fromDate')[0].value= Date();
		document.getElementsByName('toDate')[0].value= Date(); */
		time();
		if(dCode!="-1")
		{
		dCode="-1";
		document.getElementsByName("deptCode")[0].value=dCode;
		}
		
		if(sCode!="-1")
		{
		sCode="-1";
		document.getElementsByName("sampleCode")[0].value=sCode;
		}
		
	}
	
	
	
	
	
	function compareDateValidationNew(frDate,toDate)
	{
		//alert("compare date validation new");
		var seprator1 = "", seprator2 = "";
		var retValue = false;
		var dtMode = 0;
		var retValues1,retValues2;
		
		if (frDate != "" && frDate != null && toDate != "" && toDate != null)
		{
			seprator1 = getSeperatorNew(frDate);		//function that returns seperator
			seprator2 = getSeperatorNew(toDate);		//function that returns seperator
			
			if (seprator1 != "" && frDate.length >= 8)
			{
				retValues1 = parseDateNew(frDate,seprator1);
							
				if (seprator2 != "" && toDate.length >= 8 && retValues1.cancelKey == true)
				{
					retValues2 = parseDateNew(toDate,seprator2);
					if(retValues2.cancelKey == true)
					{
						if(retValues1.year == retValues2.year)
						{
							if(retValues1.month == retValues2.month)
							{
								if(retValues1.day == retValues2.day)
									dtMode = 1;
								else
								{
									if(retValues1.day > retValues2.day)
										dtMode = 2;
									else
										dtMode = 0;
								}
							}
							else
							{
								if(retValues1.month > retValues2.month)
									dtMode = 2;
								else
									dtMode = 0;	
							}
						}
						else
						{
							if(retValues1.year > retValues2.year)
								dtMode = 2;
							else
								dtMode = 0;		
						}
						
						retValue = true;
					}
				}
			}
		}
		
		return {cancelKey :retValue,mode:dtMode};
	} //end compareDate() function

	/*
		this is internal function that parses the date into day, month & year
	*/
	function parseDateNew(dtStr,seprator)
	{
		var pos1,pos2,tempLen=0;
		var tempStr = "";
		var intDay = 0,intMon = 0,intYear = 0;
		var retValue = false;
		
		pos1 = dtStr.indexOf(seprator);
		pos2 = dtStr.indexOf(seprator,pos1+1);
		//alert(pos1 + "pos1");
		//alert(pos2);
		if(pos1 > 0 && pos2 > (pos1 + 1))
		{
			//day
			tempStr = dtStr.substring(0,pos1);
			tempLen = tempStr.length;
			if(tempLen > 0 && tempLen <=2)			{
				//alert(isNaN(tempStr));
				if(isNaN(tempStr))
				intDay = tempStr;
				else
				intDay = parseInt(tempStr,'10');
				//month
				tempStr = dtStr.substring(pos1+1,pos2);
				tempLen = tempStr.length;
				if(tempLen > 0 && tempLen <= 3)
				{
					//format given as dd/mmm/yyyy
					if(tempLen == 3) tempStr = getMonthIntNew(tempStr);	
					
					intMon = parseInt(tempStr,'10');
					if(intMon > 0) 
					{
						//year
						tempStr = dtStr.substring(pos2+1);
						tempLen = tempStr.length;
						if(tempLen == 4)
						{
							intYear = parseInt(tempStr,'10');
							if (intYear >= 1900 && intYear <= 9900)
							{
								if (intMon >= 1 && intMon <= 12)
								{
									if (intDay > 0 && intDay <= DaysInMonthNew(intMon, intYear))
										retValue = true;
								}
							}
						}
					}
				}
			}
		}
		
		return {cancelKey :retValue,day:intDay,month:intMon,year:intYear};
	}

	/*
		This is internal function that finds the seperator used for seperating the date
	*/
	function getSeperatorNew(dtStr)
	{
		var seprator = "-";
		
	/* 	if (dtStr.indexOf("-") > -1)
			seprator = "-";
		else
		{
			if (dtStr.indexOf("/") > -1)
				seprator = "/";
			else
			{
				if (dtStr.indexOf(".") > -1)
					seprator = ".";
			}
		}	 *///endif
		return seprator;
	}

	//this is internal function that converts the month(in string) into month(in integer)
	function getMonthIntNew(str)
	{
		var month = "0";
		
		switch(str.toUpperCase())
		{
			case "JAN":
				month = "1";
				break;
			case "FEB":
				month = "2";
				break;
			case "MAR":
				month = "3";
				break;
			case "APR":
				month = "4";
				break;
			case "MAY":
				month = "5";
				break;
			case "JUN":
				month = "6";
				break;
			case "JUL":
				month = "7";
				break;
			case "AUG":
				month = "8";
				break;
			case "SEP":
				month = "9";
				break;
			case "OCT":
				month = "10";
				break;
			case "NOV":
				month = "11";
				break;
			case "DEC":
				month = "12";
				break;
		}
		return month;
	}

	//this is internal function that returns no of days in a month for the specified year
	function DaysInMonthNew(mon, year) 
	{
		var retVal;
		
		retVal = 31;
		if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {retVal = 30;}
		if (mon == 2) {retVal = daysInFebruaryNew(year);}
	   	return retVal;
	}

	//this is internal function that returns day in feb month for specified year
	function daysInFebruaryNew (year)
	{
		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
		 
		
	/*this function submits the page*/
	//end of validateFun


	/********added by deepak********/
	function dateDiffNew(date_1,date_2) 
	{
	var retVal=compareDateValidationNew(date_1,date_2);
		if(retVal.mode==0 || retVal.mode==1)
		{
			var ret=parseDateNew(date_1,"-");
			var ret1=parseDateNew(date_2,"-");
			var dt1=ret.month+"-"+ret.day+"-"+ret.year;
			var dt2=ret1.month+"-"+ret1.day+"-"+ret1.year;
			date1 = new Date();
			date2 = new Date();
			diff = new Date();
		//alert("Valid From/in format DD-MM-YYYY->"+dt1);
		//alert("Valid To/in format DD-MM-YYYY->"+dt2);
		{
		// Validates first date 
			var myDate1=new Array();
			myDate1=dt1.split("-");
			date1temp = new Date(myDate1[2],(myDate1[0]-1),myDate1[1]);
			date1.setTime(date1temp.getTime());
		}
		{ 
		// Validates second date 
			var myDate2=new Array();
			myDate2=dt2.split("-");
			date2temp = new Date(myDate2[2],(myDate2[0]-1),myDate2[1]);
			//alert("date2temp.getTime()->"+date2temp.getTime());
			date2.setTime(date2temp.getTime());
		}
		// sets difference date to difference of first date and second date
		//alert("date1.getTime()->"+date1.getTime());
			diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
			timediff = diff.getTime();
			//alert("timediff->"+timediff);
			weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
			timediff -= weeks * (1000 * 60 * 60 * 24 * 7);
			days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
			timediff -= days * (1000 * 60 * 60 * 24);
			days=parseInt(weeks)*7+days;
			var diff = /*weeks + " weeks, " +*/ days + " days " ;
			//alert("date diff->"+diff);
			return diff;
		/*
		var o=document.getElementById("daysOnLeave");
		o.innerHTML="<font color='blue' weight='strong'>"+diff+"</font>";
		document.forms[0].strDaysOnLeave.value=diff;*/
		}
	}
	
	function cancelFunc()
	{
		window.parent.closeTab();
	}   
    
	
</script>
   
   
   
<style >
</style>
 


<body onload="onLoadPageCheck()">
<html:form action="/reports/InvestigationListingReportActionRd" >
	   
	   <div class="errMsg" id="errMsg"><bean:write name="InvestigationListingReportFB" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="InvestigationListingReportFB" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="InvestigationListingReportFB" property="strWarningMsg"/></div>
	   
	   <html:hidden name="InvestigationListingReportFB" property="hmode" />
	    <html:hidden name="InvestigationListingReportFB" property="sysDate" />
	      <html:hidden name="InvestigationListingReportFB" property="dateTypeCheck" />
	       <html:hidden name="InvestigationListingReportFB" property="frmHr" />
	        <html:hidden name="InvestigationListingReportFB" property="frmMin" />
	         <html:hidden name="InvestigationListingReportFB" property="toHr" /> 
	         <html:hidden name="InvestigationListingReportFB" property="toMin" />
	     
	         
	  	 <his:TransactionContainer>
	 
		<his:TitleTag name="Investigation Listing Report">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String fromTimeLabel="";
              String toTimeLabel="";
              String fromTimeControl="" ;
              String toTimeControl="" ;
         %>
      <bean:define name="InvestigationListingReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="InvestigationListingReportFB" property="toDate" id="tDate" type="java.lang.String"/> 
	   
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%> 
    	 
    	<his:SubTitleTag name="Details">
    		<input type="radio" name="datetype" value="today"  onclick="setTimeCheck(this)"   id="todayCheck"><bean:message key="today"/>
			<input type="radio" name="datetype" value="datewise" onclick="setDateTime(this)" id="dateCheck"><bean:message key="dateWise"/>
    		<input type="radio" name="datetype" value="datewise1" onclick="setDateTime(this)" id="dateCheck1"><bean:message key="dateWise3month"/>
    	</his:SubTitleTag>
   		
   	
   	
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			     
			      <tr id="dateWiseCheck" style="display: none">   
		      
			         
 			<td class="tdfonthead" width="25%">
 			
 		
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
					</font>
        		</div>
        	
			</td>
			<td class="tdfont" width="25%">
			 
			<div  >
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
			</div>
			</td>
 			<td class="tdfonthead" width="25%">
 			<div  >
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>&nbsp;
					</font>
        		</div>
        	</div>
        	
 			
			</td>
			<td class="tdfont" width="25%">
			<div  >
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</div>
			
			</td>

 		</tr>
 		<tr  id="timeWiseCheck">
 			<td class="tdfonthead" width="25%">
 		
        		<div align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromtime"/>&nbsp;
					</font>
        		</div>
  
			</td>
			
			<td class="tdfont" width="25%">
			<div >
	    		<input type="text"  name="fromHrTime" onchange="setTimeN(this)" value="" style="width: 35px; height: 20px" /><b> : </b><input type="text"  name="fromMinTime" onchange="setTimeN(this)" value="" style="width: 35px; height: 20px"/>(HH:MM 24 Hrs)
				

					<div id="txt"></div>
			</div>
		
			</td>
			
		
			<td class="tdfonthead" width="25%">
			
 			<div  >
        		<div align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="totime"/>&nbsp;
					</font>
        		</div>
        	</div>
        	
 			
			</td>
			<td class="tdfont" width="25%">
			<div >
	    		<input type="text"  name="toHrTime" onchange="setTimeN(this)" id="hr" value="" style="width: 35px; height: 20px" /><b> : </b><input type="text"  name="toMinTime" onchange="setTimeN(this)" id="min" value="" style="width: 35px; height: 20px"/>(HH:MM 24 Hrs)
				

					<div id="txt"></div>
			</div>
		
			</td>
 		</tr>
 	

 
 <tr>
 				 <td width="25%" class="tdfonthead">
			      <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="dept"/>&nbsp;
						 </font>
				     </div>
			     
			     
			      </td>
			      <td width="25%" class="tdfont">
			       <logic:present	name="<%=InvestigationConfig.DEPART_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="deptCode" tabindex="1" onchange="getLab()" style="max-width:80%;">
										<html:option value="-1">Select department</html:option>
											<html:option value="%">All</html:option>
										<html:options
											collection="<%=InvestigationConfig.DEPART_COMBO %>"
											property="value" labelProperty="label" />
									
									</html:select>

		</logic:present>
			      
			     </td>
 
 
 
 
 
 
			    <td width="25%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="lab"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present	name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="labCode" onchange="getTest()" tabindex="1" style="max-width:80%;">
											<html:option value="-1">Select lab</html:option>
												<html:option value="%">All</html:option>
			    							<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
											property="value" labelProperty="label" />
										
							</html:select>
							
				</logic:present>

		 <logic:notPresent	name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="labCode" onchange="getTest()" tabindex="1" style="max-width:80%;">
											<html:option value="-1">Select lab</html:option>
									</html:select>

		</logic:notPresent> 
		
		
			     </td>
			    
			     </tr>
			     
		<tr>     
			
			     
			      <tr>
			      
			       <td width="25%" class="tdfonthead">
			      <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="test"/>&nbsp;
						 </font>
				     </div>
			     
			     
			      </td>
			      <td width="25%" class="tdfont">
			       <logic:present	name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="testCode" onchange="getSample()" tabindex="1" style="max-width:80%;">
										<html:option value="-1">Select test</html:option>
										<html:option value="%">All</html:option>
			      						<html:options
											collection="<%=InvestigationConfig.LIST_TEST_COMBO %>"
											property="value" labelProperty="label" />
											
				
									</html:select>
					</logic:present>

			      <logic:notPresent	name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="testCode" onchange="getSample()" tabindex="1" style="max-width:80%;">
										<html:option value="-1">Select test</html:option>
									</html:select>

				</logic:notPresent>
			     </td>
			      <td width="25%" class="tdfonthead">
			
				<div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="sample"/>&nbsp;
						 </font>
				     </div>
			
			
			
			</td>
			      
			<td width="25%" class="tdfont">
			
			     <logic:present	name="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="sampleCode" tabindex="1" onchange="" style="max-width:80%;">
										<html:option value="-1">Select sample</html:option>
										<html:option value="%">All</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>"
											property="value" labelProperty="label" />
										
									</html:select>

		</logic:present>
		 <logic:notPresent	name="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>">
							<html:select name="InvestigationListingReportFB" property="sampleCode" onchange="" tabindex="1" style="max-width:80%;">
										<html:option value="-1">Select sample</html:option>
									</html:select>

				</logic:notPresent>	
			
			
			</td>
			      
			    
			    
			     </tr>
			   
			   
			   
		<tr>
			      
			      
			      
			      
			    <td width="25%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="pat_type"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <select name="pat_type"  onchange="">
			<option value="%">Both</option>
			<option value="2">IPD</option>
			<option value="1">OPD</option>
			</select>
			     </td>
			     
			     
			     <td width="25%" class="tdfonthead">
			      <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="status"/>&nbsp;&nbsp;
						 </font>
				     </div>
			             
				     </div>
			    
			      </td>
			      <td width="25%" class="tdfont">
			       <select name="status"  onchange="" style="max-width:80%;">
			<option value="%">All</option>
			<option value="2">Requisition Raised</option>
			<option value="3">Sample Collected</option>
			<option value="4">Packing List generated</option>
			<option value="6">Sample Accepted</option>
			<option value="7">Result Entered</option>
			<option value="8">Result Validated</option>
			<option value="9">Patient Rejected</option>
			<option value="11">Test Rescheduled</option>
			<option value="12">Sample Cancelled</option>
			<option value="13">Ready For Report Printing</option>
			<option value="14">Report Printed</option>
			<option value="15">Test Cancelled</option>
			<option value="16">Test Deleted</option>
			<option value="26">Report Generated</option>			
			</select>
			      
			     </td>
			     </tr>
			
		<tr>
			
			
			<td width="25%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="rptfrmt"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <select name="strReportFormat"  onchange="">
			<option value="html">Html</option>
			<option value="pdf">Pdf</option>
			<option value="xls">Excel</option>
			</select>
			     </td>
			
			
			
			
			
			
			<td width="25%" class="tdfonthead">
			</td>
			<td width="25%" class="tdfont">
			</td>
		</tr>
			   

			     </table>
			          
			   
			   </his:ContentTag>
			       
			 <his:ButtonToolBarTag>
				      
				    	 <img class="button"   src='<his:path src="/hisglobal/images/btn-generate.png"/>'       tabindex="1" onClick="return validate();" >
				         
				      <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'     onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onClick="onClearButton();" >
				          <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'     onkeypress="if(event.keyCode==13) cancelFunc();"  tabindex="1" onClick="cancelFunc();"  >
				     
				    </his:ButtonToolBarTag>
	  
	    </his:TransactionContainer>
	   
	 <his:status/>		    
</html:form>
</body>
</html>