 
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.reports.controller.fb.LabWiseStatisticalReportFB"%>
 
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
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

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
	
	//alert(document.getElementById("strFromDate").value);
	//alert(document.getElementById("testcode").value);
	
	var dt = document.getElementsByName('dateTypeCheck')[0].value;
	if(dt=="datewise")
	{
	 if(!validateTodayOrDate())
    	 {return false;}
	}
	else if(dt=="monthwise")
	{
	 if(!validateMonthYear())
		{return false;}
	}
	else if(dt=="yearwise")
	{
	 if(!validateYear())
	 	{return false;}
	}
	else
	{
	 if(!validateHrMin())
		 {return false;}
	}
	

var tCode = document.getElementsByName("testCode")[0].value;
var lCode = document.getElementsByName("labCode")[0].value;
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



function onClearButton(){

	document.forms[0].hmode.value="NEW";
	document.forms[0].reset();
	onLoadPageCheck();
	onClearSet();
	document.forms[0].submit();
} 

 function onClearSet()
{
	
	var tCode = document.getElementsByName("testCode")[0].value;
	var lCode = document.getElementsByName("labCode")[0].value;
	
	
	if(lCode!="-1")
	{
		lCode="-1";
		document.getElementsByName("labCode")[0].value=lCode;
	}
	if(tCode!="-1")
	{
		tcode="-1";
		document.getElementsByName("testCode")[0].value=tCode;
	}
	time();
	document.getElementsByName('frmHr')[0].value="00";
	document.getElementsByName('frmMin')[0].value="01";
	document.getElementsByName('fromHrTime')[0].value="00";
	document.getElementsByName('fromMinTime')[0].value="01";

} 
 
function time()
{
	var today=new Date();
	var h=today.getHours();
var m=today.getMinutes();
var s=today.getSeconds();
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



function onLoadPage()
{

	document.forms[0].strIsFooter.checked = true;
	
}


function validateMonthYear()
{
	var yr1= document.getElementsByName('fromYear')[0].value;
	var mnth1= document.getElementsByName('fromMonth')[0].value;
	var yr2= document.getElementsByName('toYear')[0].value;
	var mnth2= document.getElementsByName('toMonth')[0].value;
	var d1 = new Date();
	var n1 = d1.getMonth();
	var mS=parseInt(n1);
	var mU1=parseInt(mnth1);
	var mU2=parseInt(mnth2);
	var d2 = new Date();
	var n2 = d2.getFullYear(); 
	var yS=parseInt(n2);
	var yU1=parseInt(yr1);
	var yU2=parseInt(yr2);
	if(mU1>(mS+1))
	{
		alert("Select month not greater than system month");
		return false;
	}
	if(yU1>yS)
	{
		alert("Select year not greater than system year");
		return false;
	}
	if(mU2>(mS+1))
	{
		alert("Select month not greater than system month");
		return false;
	}
	if(yU2>yS)
	{
		alert("Select year not greater than system year");
		return false;
	}
	if(yU1>yU2)
		{
			alert("From year cannot be greater than to year");
			return false;
		}
	if(yU1==yU2)
	{
		if(mU1>mU2)
		{
			alert("From month cannot be greater than to month");
			return false;
		}
	}	
	return true;
}





function validateYear()
{
	

	var y1= document.getElementsByName('fromYr')[0].value;
	var y2= document.getElementsByName('toYr')[0].value;
	var d3 = new Date();
	var n3 = d3.getFullYear(); 
	var yS=parseInt(n3);
	var yU1=parseInt(y1);
	var yU2=parseInt(y2);
	if(yU1>yS)
	{
		alert("Select year not greater than system year");
		return false;
	}
	if(yU2>yS)
	{
		alert("Select year not greater than system year");
		return false;
	}
	if(yU1>yU2)
		{
			alert("From year cannot be greater than to year");
			return false;
		}
	
	return true;
	
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
		document.getElementById("monthWiseCheck").style.display="none";
		document.getElementById("yearWiseCheck").style.display="none";

		}
	else if(dt=="today")
		{
		//alert("t");
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="";
		document.getElementById("monthWiseCheck").style.display="none";
		document.getElementById("yearWiseCheck").style.display="none";

		}
	else if(dt=="monthwise")
	{
	//alert("t");
	document.getElementById("dateWiseCheck").style.display="none";
	document.getElementById("timeWiseCheck").style.display="none";
	document.getElementById("monthWiseCheck").style.display="";
	document.getElementById("yearWiseCheck").style.display="none";
	}
	else if(dt=="yearwise")
	{
	//alert("t");
	document.getElementById("dateWiseCheck").style.display="none";
	document.getElementById("timeWiseCheck").style.display="none";
	document.getElementById("monthWiseCheck").style.display="none";
	document.getElementById("yearWiseCheck").style.display="";
	}	
//	var labCode = document.getElementsByName("labCode")[0].value;
  //  alert(labCode);
  	
	 
		document.forms[0].hmode.value="GETTEST";
		document.forms[0].submit();
}

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
	document.getElementById("monthWiseCheck").style.display="none";
	document.getElementById("yearWiseCheck").style.display="none";
}
	
function setTimeCheck(obj)
{
	document.getElementsByName('dateTypeCheck')[0].value=obj.value;
	//alert("inside here");
	document.getElementById("dateWiseCheck").style.display="none";
	document.getElementById("timeWiseCheck").style.display="";
	document.getElementById("monthWiseCheck").style.display="none";
	document.getElementById("yearWiseCheck").style.display="none";

}

function setMonthCheck(obj)
{
	document.getElementsByName('dateTypeCheck')[0].value=obj.value;
	//alert("inside here");
	document.getElementById("dateWiseCheck").style.display="none";
	document.getElementById("timeWiseCheck").style.display="none";
	document.getElementById("monthWiseCheck").style.display="";
	document.getElementById("yearWiseCheck").style.display="none";
}


function setYearCheck(obj)
{
	document.getElementsByName('dateTypeCheck')[0].value=obj.value;
	//alert("inside here");
	document.getElementById("dateWiseCheck").style.display="none";
	document.getElementById("timeWiseCheck").style.display="none";
	document.getElementById("monthWiseCheck").style.display="none";
	document.getElementById("yearWiseCheck").style.display="";
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
		document.getElementById('monthCheck').checked=false;
		document.getElementById('yearCheck').checked=false;
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		document.getElementById("monthWiseCheck").style.display="none";
		document.getElementById("yearWiseCheck").style.display="none";

		}
		else if(dt=="today")
		{
		//alert("t");
		document.getElementById('todayCheck').checked=true;
		document.getElementById('dateCheck').checked=false;
		document.getElementById('monthCheck').checked=false;
		document.getElementById('yearCheck').checked=false;
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="";
		document.getElementById("monthWiseCheck").style.display="none";
		document.getElementById("yearWiseCheck").style.display="none";

		}
		else if(dt=="monthwise")
		{
		//alert(dt);
		document.getElementById('todayCheck').checked=false;
		document.getElementById('dateCheck').checked=false;
		document.getElementById('monthCheck').checked=true;
		document.getElementById('yearCheck').checked=false;
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="none";
		document.getElementById("monthWiseCheck").style.display="";
		document.getElementById("yearWiseCheck").style.display="none";
	
		}
		else if(dt=="yearwise")
		{
		//alert("t");
		document.getElementById('todayCheck').checked=false;
		document.getElementById('dateCheck').checked=false;
		document.getElementById('monthCheck').checked=false;
		document.getElementById('yearCheck').checked=true;
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display="none";
		document.getElementById("monthWiseCheck").style.display="none";
		document.getElementById("yearWiseCheck").style.display="";

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

	
	var d1 = new Date();
	var n1 = d1.getMonth();
	document.getElementsByName('fromMonth')[0].value=n1+1;
	var d2 = new Date();
	var n2 = d2.getFullYear(); 
	document.getElementsByName('fromYear')[0].value	=n2;
	document.getElementsByName('toMonth')[0].value=n1+1; 
	document.getElementsByName('toYear')[0].value	=n2;
	var d3 = new Date();
	var n3 = d3.getFullYear(); 
	document.getElementsByName('fromYr')[0].value	=n3;
	document.getElementsByName('toYr')[0].value	=n3;
	
	
	 
	if((parseInt(document.getElementsByName('frmMnth')[0].value)!=parseInt(n1+1)) || (parseInt(document.getElementsByName('toMnth')[0].value)!=parseInt(n1+1)))
		{
		
		document.getElementsByName('fromMonth')[0].value=document.getElementsByName('frmMnth')[0].value;
		document.getElementsByName('toMonth')[0].value=document.getElementsByName('toMnth')[0].value;
		}
	if((parseInt(document.getElementsByName('frmY')[0].value)!=parseInt(n2)) || (parseInt(document.getElementsByName('toY')[0].value)!=parseInt(n2)))
    	{
		document.getElementsByName('fromYear')[0].value=document.getElementsByName('frmY')[0].value;
		document.getElementsByName('toYear')[0].value=document.getElementsByName('toY')[0].value;
		
    	}
	
	if((parseInt(document.getElementsByName('fY')[0].value)!=parseInt(n3)) || (parseInt(document.getElementsByName('tY')[0].value)!=parseInt(n3)))
	{
	document.getElementsByName('fromYr')[0].value=document.getElementsByName('fY')[0].value;
	document.getElementsByName('toYr')[0].value=document.getElementsByName('tY')[0].value;
	
	}
	
	
	
}


function setMonth(obj)
{
	document.getElementsByName('frmMnth')[0].value=document.getElementsByName('fromMonth')[0].value;
	document.getElementsByName('toMnth')[0].value=document.getElementsByName('toMonth')[0].value;
	document.getElementsByName('frmY')[0].value=document.getElementsByName('fromYear')[0].value;
	document.getElementsByName('toY')[0].value=document.getElementsByName('toYear')[0].value;

}


function setYear(obj)
{
	document.getElementsByName('fY')[0].value=document.getElementsByName('fromYr')[0].value;
	document.getElementsByName('tY')[0].value=document.getElementsByName('toYr')[0].value;
}
	




	 
	function setTime(obj)
	{
		//alert("inside set Time");
		//alert(document.getElementsByName('fromHrTime')[0].value);
		document.getElementsByName('frmHr')[0].value=document.getElementsByName('fromHrTime')[0].value;
		document.getElementsByName('frmMin')[0].value=document.getElementsByName('fromMinTime')[0].value;
		document.getElementsByName('toHr')[0].value=document.getElementsByName('toHrTime')[0].value;
		document.getElementsByName('toMin')[0].value=document.getElementsByName('toMinTime')[0].value;
	}
/* function startTime() {
    var today=new Date();
    var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    //document.getElementById('txt').innerHTML = h+":"+m+":"+s;
    var t = setTimeout(function(){startTime()},500);
 document.getElementById("hr").value = h;
document.getElementById("min").value = m;

}*/

function checkTime(i) {
    if (i<10) {i = "0" + i ;}
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
	   /* valFromHr=document.getElementsByName('fromHrTime')[0];
	   valFromMin=document.getElementsByName('fromMinTime')[0];
	   valToHr=document.getElementsByName('toHrTime')[0];
	   valToMin=document.getElementsByName('toMinTime')[0]; */
	  // alert(valFromHr);
	   
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
    
    
	function cancelFunc()
	{
		window.parent.closeTab();
	}  
    
	
</script>
   
   
   
<style >
</style>
 


<body onload="onLoadPageCheck()">
<html:form action="/reports/LabWiseStatisticalReportAction" >
	   
	   <div class="errMsg" id="errMsg"><bean:write name="LabWiseStatisticalReportFB" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="LabWiseStatisticalReportFB" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="LabWiseStatisticalReportFB" property="strWarningMsg"/></div>
	   
	   <html:hidden name="LabWiseStatisticalReportFB" property="hmode" />
	    <html:hidden name="LabWiseStatisticalReportFB" property="sysDate" />
	      <html:hidden name="LabWiseStatisticalReportFB" property="dateTypeCheck" />
	       <html:hidden name="LabWiseStatisticalReportFB" property="frmHr" />
	        <html:hidden name="LabWiseStatisticalReportFB" property="frmMin" />
	         <html:hidden name="LabWiseStatisticalReportFB" property="toHr" /> 
	         <html:hidden name="LabWiseStatisticalReportFB" property="toMin" />
	         <html:hidden name="LabWiseStatisticalReportFB" property="frmMnth" />
	         <html:hidden name="LabWiseStatisticalReportFB" property="toMnth" />
	         <html:hidden name="LabWiseStatisticalReportFB" property="frmY" />
   	         <html:hidden name="LabWiseStatisticalReportFB" property="toY" />
	          <html:hidden name="LabWiseStatisticalReportFB" property="fY" />
	           <html:hidden name="LabWiseStatisticalReportFB" property="tY" />
	            <html:hidden name="LabWiseStatisticalReportFB" property="fromMonthYear" />
	             <html:hidden name="LabWiseStatisticalReportFB" property="toMonthYear" />
	            
	         
	         
	         
	         
	  	 <his:TransactionContainer>
	 
		<his:TitleTag name="Lab Wise Statistical Report">
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
      <bean:define name="LabWiseStatisticalReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="LabWiseStatisticalReportFB" property="toDate" id="tDate" type="java.lang.String"/> 
	   
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
			<input type="radio" name="datetype" value="monthwise" onclick="setMonthCheck(this)" id="monthCheck"><bean:message key="monthwise"/>
    		<input type="radio" name="datetype" value="yearwise" onclick="setYearCheck(this)" id="yearCheck"><bean:message key="yearwise"/>
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
	    		<input type="text"  name="fromHrTime" onchange="setTime(this)" value="" style="width: 35px; height: 20px" /><b> : </b><input type="text"  name="fromMinTime" onchange="setTime(this)" value="" style="width: 35px; height: 20px"/>(HH:MM 24 Hrs)
				

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
	    		<input type="text"  name="toHrTime" onchange="setTime(this)" id="hr" value="" style="width: 35px; height: 20px" /><b> : </b><input type="text"  name="toMinTime" onchange="setTime(this)" id="min" value="" style="width: 35px; height: 20px"/>(HH:MM 24 Hrs)
				

					<div id="txt"></div>
			</div>
		
			</td>
 		</tr>
 	

  <tr id="monthWiseCheck" style="display: none">   
		      
			         
 			<td class="tdfonthead" width="25%">
 			
 		
        		<div align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromMonth"/>&nbsp;
					</font>
        		</div>
        	
			</td>
		<td class="tdfont" width="25%">
        	<select id="m" name="fromMonth" onchange="setMonth(this)" style="max-width:45%;" >
			<option value ="01">January</option>
			<option value ="02">February</option>
			<option value ="03">March</option>
			<option value ="04">April</option>
			<option value ="05">May</option>
			<option value ="06">June</option>
			<option value ="07">July</option>
			<option value ="08">August</option>
			<option value ="09">September</option>
			<option value ="10">October</option>
			<option value ="11">November</option>
			<option value ="12">December</option>
			</select>
			<select name="fromYear" onchange="setMonth(this)" style="max-width:30%;">
			<option value ="2015">2015</option>
			<option value ="2016">2016</option>
			<option value ="2017">2017</option>
			<option value ="2018">2018</option>
		 	<option value ="2019">2019</option>
		 	<option value ="2020">2020</option>
		 	</select>
        </td>
			
			
 			<td class="tdfonthead" width="25%">
 			<div  >
        		<div  align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toMonth"/>&nbsp;
					</font>
        		</div>
        	</div>
        	
 			
			</td>
		
		<td class="tdfont" width="25%">
		
        	<select id="m" name="toMonth" onchange="setMonth(this)" style="max-width:45%;" >
			<option value ="01">January</option>
			<option value ="02">February</option>
			<option value ="03">March</option>
			<option value ="04">April</option>
			<option value ="05">May</option>
			<option value ="06">June</option>
			<option value ="07">July</option>
			<option value ="08">August</option>
			<option value ="09">September</option>
			<option value ="10">October</option>
			<option value ="11">November</option>
			<option value ="12">December</option>
			</select>
			<select name="toYear" onchange="setMonth(this)" style="max-width:30%;">
			<option value ="2015">2015</option>
			<option value ="2016">2016</option>
			<option value ="2017">2017</option>
			<option value ="2018">2018</option>
		 	<option value ="2019">2019</option>
		 	<option value ="2020">2020</option>
		 	</select>
        </td>
	</tr>
 
 
 <tr id="yearWiseCheck" style="display: none">   
		      
			         
 			<td class="tdfonthead" width="25%">
 			
 		
        		<div align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromYear"/>&nbsp;
					</font>
        		</div>
        	
			</td>
		<td class="tdfont" width="25%">
        	
			<select name="fromYr" onchange="setYear(this)" style="max-width:30%;">
			<option value ="2015">2015</option>
			<option value ="2016">2016</option>
			<option value ="2017">2017</option>
			<option value ="2018">2018</option>
		 	<option value ="2019">2019</option>
		 	<option value ="2020">2020</option>
		 	</select>
        </td>
			
			
 			<td class="tdfonthead" width="25%">
 			<div  >
        		<div  align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toYear"/>&nbsp;
					</font>
        		</div>
        	</div>
        	
 			
			</td>
		
		<td class="tdfont" width="25%">
		
			<select name="toYr" onchange="setYear(this)" style="max-width:30%;">
			<option value ="2015">2015</option>
			<option value ="2016">2016</option>
			<option value ="2017">2017</option>
			<option value ="2018">2018</option>
		 	<option value ="2019">2019</option>
		 	<option value ="2020">2020</option>
		 	</select>
        </td>
	</tr>
 
 
 
 
 <tr>
 
			    <td width="25%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 <bean:message key="deptlab"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present	name="<%=InvestigationConfig.LIST_LAB_DEPT_COMBO %>">
							<html:select name="LabWiseStatisticalReportFB" property="labCode" onchange="getTest()" tabindex="1" style="max-width:80%;">
											<html:option value="-1">Select dept-lab</html:option>
											<html:options
											collection="<%=InvestigationConfig.LIST_LAB_DEPT_COMBO %>"
											property="value" labelProperty="label" />
											<html:option value="0">All</html:option>
									</html:select>

		</logic:present>
			     </td>
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
							<html:select name="LabWiseStatisticalReportFB" property="testCode" tabindex="1" style="max-width:80%;">
										<html:option value="-1">Select test</html:option>
			      						<html:options
											collection="<%=InvestigationConfig.LIST_TEST_COMBO %>"
											property="value" labelProperty="label" />
											<html:option value="0">All</html:option>
							</html:select>
					</logic:present>

			     <logic:notPresent	name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
							<html:select name="LabWiseStatisticalReportFB" property="testCode" tabindex="1" style="max-width:80%;">
										<html:option value="-1">Select test</html:option>
									</html:select>

				</logic:notPresent>
			      
			     </td>
			     </tr>
			     
		<tr>     
			    <!--  <tr>
			    <td width="25%" class="tdfonthead">
			        
			      </td>
			      <td width="25%" class="tdfont">
			     
			     </td>
			     <td width="25%" class="tdfonthead">
			      </td>
			      <td width="25%" class="tdfont">
			     </td>
			     </tr> -->
			     
			     
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
			     <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rptMode"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <select name="reportMode">
			      	<option value="1">Consolidated</option>
			      	<option value="2">Detailed</option>
			      </select>
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