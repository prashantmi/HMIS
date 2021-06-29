<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
 <%@page import="new_investigation.vo.SampleAcceptanceVO"%>
<%@page import="org.eclipse.persistence.jpa.jpql.parser.EntryExpression"%>
<%@page import="com.lowagie.tools.arguments.OptionArgument.Entry"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
 <%@page import="new_investigation.vo.LabTestVO"%>
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

<%@page import="new_investigation.transactions.controller.fb.SampleAcceptanceFB"%>
<%@page import="new_investigation.transactions.controller.fb.SampleAcceptanceFB"%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/styleNew.css" />
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/styleForInv.css" />
<his:css src="/hisglobal/css/jquery-ui-Inv.css" />
<his:css src="/hisglobal/css/combo.css" />
<his:css src="/hisglobal/css/button.css" />
<his:css src="/hisglobal/css/drop.css" />
<his:css src="/hisglobal/css/sweet-alert.css" />
 <link rel="stylesheet" href="/new_investigation/css/site-demos.css"> 
 

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />
 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

 
 <script type="text/javascript">
            SyntaxHighlighter.defaults['toolbar'] = false;
            SyntaxHighlighter.all();
        </script>
 <script>


 function selectallmachines(obj)
 {
 	//alert(obj.value);
 	var chec=document.getElementById("isselectallmachines").checked
 	//var chec=document.getElementsByName('').checked;
 	if(chec==true)
 	{
 	var len=document.getElementsByName('testBasedMachine').length;
     for(var i=0;i<len;i++)
         {
     	document.getElementsByName('testBasedMachine')[i].value=obj.value;        

         }
 	}
 	else
 		{
      //  alert("Please Select All Machine  Checkbox first.");
 		}


 }
 
 
 function callonload()
 {

 	if(document.getElementsByName('flag')[0].value=='1')
 		{
 		document.getElementsByName('areaa')[1].checked=true;
 		if(document.getElementById("machine1")!=null)
 		document.getElementById("machine1").style.display = "";
 		document.getElementsByName('areaa')[1].checked=true;
     	document.getElementsByName("areaa")[0].disabled=true;
     	document.getElementsByName("areaa")[1].disabled=false;
 		
 		}
 	else
 		{}
 	
 	if(document.getElementsByName('flag')[0].value=='')
 	{
 	     // alert(document.getElementsByName('flag')[0].value);
 			
 	document.getElementsByName('areaa')[0].checked=true;
 	if(document.getElementById("machine1")!=null)
 	document.getElementById("machine1").style.display = "none";
 	
 	}else
 	{}
 	

 	if(document.getElementsByName('flag')[0].value=='0')
 		{

 		// alert("a"+document.getElementsByName('flag')[0].value);
 			
 		document.getElementsByName('areaa')[0].checked=true;
 		if(document.getElementById("machine1")!=null)
 		document.getElementById("machine1").style.display = "none";
     	document.getElementsByName("areaa")[1].disabled=true;
     	document.getElementsByName("areaa")[0].disabled=false;
 		
 		}
 	else
 	{}

 }

  function showdata(obj,id,idd)
  {
  //alert(obj.id);
  	//obj.checked=true;
  	if(id=="area1")
  		{
  		//alert("area1");
  		document.getElementsByName('flag')[0].value=0;
  		document.getElementsByName('areaa')[0].checked=true;
 		document.getElementById("machine1").style.display = "none";
 		
  		}

  		if(id=="machine1")
  			{

  			document.getElementsByName('flag')[0].value=1;
  			document.getElementsByName('areaa')[1].checked=true;
  			document.getElementById("machine1").style.display = "";
  			document.getElementsByName('hmode')[0].value="GETDETAILSMACHINE";
         	document.forms[0].submit();
  		//document.getElementsByName('areaa')[1].disabled=true;
  			}
  	/* 	document.getElementById(idd).style.display="none";

  	document.getElementById(id).style.display=""; */
  	

  }
  
function autoselect()
{

	$('#samplenobarcode').focus();
	
	callonload();
	
	if(document.getElementsByName('bigSampleNo')[0].checked==false)
     {
		//document.getElementsByName('bigSampleNo')[0].checked=true;
      // alert("true");
		
		var len=document.getElementsByName('bigSampleNo').length;
        if(len!=null && len=="1")
            {
        	document.getElementsByName('bigSampleNo')[0].checked=true;
		getDetail1();
            }
		  }
}
 
$(function() {
$( "#datepicker" ).datepicker({
showOn: "button",
buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
buttonImageOnly: true,
buttonText: "Select  "
});
});
</script>
<script>
$(function() {
$( "#datepickert" ).datepicker({
showOn: "button",
buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
buttonImageOnly: true,
buttonText: "Select  "
});
});
</script>

<script>
// just for the demos, avoids form submit
jQuery.validator.setDefaults({
debug: true,
success: "valid"
});
$( "#myform" ).validate({
rules: {
field: {
required: true,
email: true
}
}
});
</script>
  
<script type="text/javascript" >


function onclickgo(value)
{
	
	//alert(value);
	var count =0;
	 for(var i=0;i<document.getElementsByName('bigSampleNo').length;i++)
		 {
           if(document.getElementsByName('bigSampleNo')[i].checked==true)
               {
                 count++;
               }
		 }
	 if(count>1)
		 {
		 var val=count-1;
      alert("Please Select Only One Checkbox at a time");
      document.getElementsByName('bigSampleNo')[val].checked=false;
		 }
	 else
		 {
		 document.getElementById('pagbtn').style.display="";
		 }
	
}


function openPopup(url, eventObj, height, width) {
	if (eventObj.type == "click" || eventObj.keyCode == 13) {
		child = window.open(url, 'popupWindow',
				'status=yes,scrollbars=yes,height=' + height + ',width='
						+ width + ',left=10,top=10');
		child.moveTo(250, 250);
		child.focus();

		if (!child.opener)
			child.opener = self;
	}
}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
//Function for displaying selected Lab List
function validateTodayOrDate()
{
	success=false;        	   
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	valSysDate=document.getElementsByName('sysDate')[0];
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
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


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function getDetail()
{

	if(document.getElementsByName('flag')[0].value=='1')
	{
		if(document.getElementsByName('machineCodee')[0].value==-1)
			{
       alert("Please Select Machine");
       return false;
			}
	}
	
	
            if(document.getElementsByName("sampleNumber")[0].value=="")
        	{
            	 
        		alert("Enter a Sample Number") ;
        		document.getElementsByName("sampleNumber")[0].focus();
        		return false;
        	}
        	 
            
        	document.getElementsByName('hmode')[0].value="GETDETAILS1";
        	document.forms[0].submit();
	
   return true;
	
}


function getDetail1()
{
	 	
	var count=0;
	 for(var i=0;i<document.getElementsByName('bigSampleNo').length;i++)
	 {
       if(document.getElementsByName('bigSampleNo')[i].checked==true)
           {
             count++;
           }
	 }
 if(count==0)
	 {
	
  alert("Please Select Record");
  
	 }
 else
	 {
	    	 
            
        	document.getElementsByName('hmode')[0].value="GETDETAILS";
        	document.forms[0].submit();
	
   return true;
	 }
}

function validateMinLength(elem,minlen) {
    var isValid = true;
  if(elem)
         value=elem.value;
  else
         value="";
                         
   if ((value.length<minlen))
		{
     	isValid = false;
     }
return isValid;

} 	 

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function httpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		initReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			 request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		{
			initReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function initReq(reqType,url,isAsynch)
{
	//alert("inside init request");
 /* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
 /* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	var queryString;
	request.send(queryString);
}

function ValidateSameCrNo(obj)
{
	
	if(obj.checked)
	{
		document.getElementById('nextDiv').style.display="";
                
	}
  else
     	{
	  document.getElementById('gob').style.display="";
      	document.getElementById('cancel').style.display="";
      	
          }
	 
	//document.getElementById('gob').style.display="none";
	 
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{
	 		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    		 
			     alert("Please Select Same CR No");
			    	document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
				}
		}
	}
	//displaySamplePatDetails();
}
	 

function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}
function displaySamplePatDetails()
{	
 	var count=0;
	//document.getElementsByName('isPatDetailPage')[0].value="1";
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkRadioSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	  	if(cbs[i].checked)
    	{
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }
   
      }
	 
	document.getElementsByName('selectedRadioCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
	document.forms[0].submit();
	}
  
function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
document.getElementById("divLegends").style.display="none";
}

 function onChaneAccepted(obj,size,inc)
{
	if(obj.value=='0')
		{
		 document.getElementById(inc+".recieved").disabled=true;
		 document.getElementById(inc+".dailyNO").disabled=true;
		 document.getElementById(inc+".rejectnActn").disabled=false;
		 document.getElementById(inc+".rejectnRsn").disabled=false;
		 for(i=0;i<=size;i++)
			{ 
		 
		document.getElementById(inc+"."+i+"accepted").value='0';
		document.getElementById(inc+"."+i+"rejectnActn").disabled=true;
		document.getElementById(inc+"."+i+"rejectnRsn").disabled=true;
		document.getElementById(inc+"."+i+"dailyNO").disabled=true;
		document.getElementById(inc+"."+i+"accepted").disabled=true;
		document.getElementById(inc+"."+i+"dailyNO").disabled=true;
		document.getElementById(inc+"."+i+"chkbox").disabled=true;
			}
		 
		}
	else{
		
		document.getElementById(inc+".recieved").disabled=false;
		 document.getElementById(inc+".dailyNO").disabled=false;
		 document.getElementById(inc+".rejectnActn").disabled=true;
		 document.getElementById(inc+".rejectnRsn").disabled=true;
		for(i=0;i<=size;i++)
		{ 
		 document.getElementById(inc+"."+i+"accepted").value='1';
			document.getElementById(inc+"."+i+"dailyNO").disabled=false;
			document.getElementById(inc+"."+i+"accepted").disabled=false;
			document.getElementById(inc+"."+i+"dailyNO").disabled=false;
			document.getElementById(inc+"."+i+"chkbox").disabled=false;
			 
			 
		}
	}
	 
	
	}
 
 
 
 function onChaneRecieve(obj,size,inc)
 {
    
	 //alert(document.getElementsByName("recieved")[0].value);
	 
	 
 	if(obj.value=='0')
 		{
 	//	 document.getElementById(inc+".accepted").disabled=true;
 		 document.getElementById(inc+".dailyNO").disabled=true;
 		//document.getElementById("rejectnActn").disabled=false;
 		//document.getElementById("rejectnRsn").disabled=false;
 		 
 		 for(i=0;i<=size;i++)
 			{ 
 		//	document.getElementById(inc+"."+i+"recieved").value=obj.value;
 		document.getElementById(inc+"."+i+"accepted").value='0';
 		document.getElementById(inc+"."+i+"dailyNO").disabled=true;
 		document.getElementById(inc+"."+i+"accepted").disabled=true;
 		document.getElementById(inc+"."+i+"dailyNO").disabled=true;
 		document.getElementById(inc+"."+i+"chkbox").disabled=true;
 		 
 			}
 		 
 		//document.getElementById(inc+"."+i+"rejectnReson").style.display="";
 		}
 	else{
 		
 	//	document.getElementById(inc+".accepted").disabled=false;
 		 document.getElementById(inc+".dailyNO").disabled=false;
 		//document.getElementById("rejectnActn").disabled=true;
 		//document.getElementById("rejectnRsn").disabled=true;
 		
 		for(i=0;i<=size;i++)
 		{ 
 			// document.getElementById(inc+"."+i+"recieved").value=obj.value;
 			document.getElementById(inc+"."+i+"accepted").value='1';
 			  
 			document.getElementById(inc+"."+i+"dailyNO").disabled=false;
 			document.getElementById(inc+"."+i+"accepted").disabled=false;
 			 
 			document.getElementById(inc+"."+i+"dailyNO").disabled=false;
 			document.getElementById(inc+"."+i+"chkbox").disabled=false;
 			 
 		}
 	}
 	
 	}
 
 function onChaneRejActn(obj,size,inc)
 {
	 for(i=0;i<=size;i++)
		{ 
		  
		document.getElementById(inc+"."+i+"rejectnActn").value=obj.value;
		
		 
	 
		}
	 
 }
 
 function onChaneRejResn(obj,size,inc)
 {
	 for(i=0;i<=size;i++)
		 {
	
		  
		 document.getElementById(inc+"."+i+"rejectnRsn").value=obj.value;
	 		
		 }
	 
 }
 
  function onChaneSubRejActn(obj,inc,k)
 {
	
	
		  
		 document.getElementById(inc+"."+k+"rejectnActn").value=obj.value;
	 		
		 
	 
 }
 
 function onChaneSubRejReason(obj,inc,k)
 {
	
	
		  
		 document.getElementById(inc+"."+k+"rejectnRsn").value=obj.value;
	 		
		 
	 
 }
  
 
 function onChaneSubAcc(obj,inc,k)
 {
  
	  
 	if(obj.value=='0')
 		{
 		 
 		document.getElementById(inc+"."+k+"dailyNO").disabled=true;
 		document.getElementById(inc+"."+k+"rejectnActn").disabled=false;
 		document.getElementById(inc+"."+k+"rejectnRsn").disabled=false;
 		document.getElementById(inc+"."+k+"dailyNO").disabled=true;
 		document.getElementById(inc+"."+k+"recieved").disabled=true;
 		document.getElementById(inc+"."+k+"chkbox").disabled=true;
 		 
 			}
 		 
 		 
 	else{
 		 
 	 		document.getElementById(inc+"."+k+"dailyNO").disabled=false;
 	 		document.getElementById(inc+"."+k+"rejectnActn").disabled=true;
 	 		document.getElementById(inc+"."+k+"rejectnRsn").disabled=true;
 	 		document.getElementById(inc+"."+k+"dailyNO").disabled=false;
 	 		document.getElementById(inc+"."+k+"recieved").disabled=false;
 	 		document.getElementById(inc+"."+k+"chkbox").disabled=false;
 	 		 
 	 			 
 	   }
 	 
 	
 	}
 
  
function onClickCheck(obj,size,inc)
{
	//alert(k);
	if(obj.checked)
		{
		
		for(i=0;i<=size;i++)
			{ 
		document.getElementById(inc+"."+i+"chkbox").checked = true;
		document.getElementById("button").style.display=""; 
			}
	   document.getElementById('gob').style.display="none";
		}
	  else
	     	{
		  for(i=0;i<=size;i++)
			{ 
	      	//document.getElementById("button").style.display="none";
		  document.getElementById(inc+"."+i+"chkbox").checked = false;
			}  
			}


}

function onSave()
{


	document.getElementById("button1").style.display="none";
	
	var count=0;
	 
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatient');
	 
	for(var i=0; i < cbs.length; i++) {
			
		if(cbs[i].checked)
    	{
    		
    	count++;	
		var id=cbs[i].id;
		 
		//var indexWithSubIndex=id.substring(0, 3);
    	var indexWithSubIndex=id.replace('chkbox', '');
    //	alert(indexWithSubIndex);
		//var index=indexWithSubIndex.split(".")[0];
		//var subIndex=indexWithSubIndex.split(".")[1];
		if(document.getElementById(indexWithSubIndex+"accepted").value=="1"&&document.getElementById(indexWithSubIndex+"dailyNO").value=="")
	 	{
	 		 
	 		 
		     	alert("Enter Lab No"); 
	 		document.getElementById(indexWithSubIndex+"dailyNO").focus();
	 		return false;
	 	}
    	
    	 }
   
      }
	
	if(count==0)
   	{
   	alert("please select a Atleast One record");
   	 
    	
   	return false;
   	}
	
	 var nodes = document.getElementById("dcalc").getElementsByTagName('*');
	 for(var i = 0; i < nodes.length; i++)
	 {
	      nodes[i].disabled = false;
	 }
	 
	document.getElementsByName('showStatus')[0].value='0';
	 
	//Validations for CR NO and Combo should be done here
	document.getElementsByName('hmode')[0].value="SAVE";
	document.forms[0].submit();
	
   return true;
	
	
}

function setlabCode(obj)
{
	var code=obj.value;
	alert(code);
	alert(obj.value);
	document.getElementsByName("labCode")[0].value=code;
	 
	}

function callFunction(obj)
{
	alert("w");
	}
//AJax Functions for checking Duplicacy
function chkDailyLabNoDuplicacyThroughAjax(obj,event)
{
	var labNoConfiguration=obj.value;
	//var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;
	//alert(labNoConfiguration.length);
	if(labNoConfiguration.length<0)
		return true;
	
		var isDailyLabNoPresent = chkDailyLabNo(labNoConfiguration);
		
		isDailyLabNoPresent=isDailyLabNoPresent=="true"?true:false;
		//alert(isDailyLabNoPresent);
		if(isDailyLabNoPresent)
		{
			alert("Daily Lab Number already present");
			obj.value="";
			obj.focus();
			return false;
		}
	return true;
}

function chkDailyLabNo(labNoConfiguration)
{
	var flg = false;
	var isSampleNoPresent = false;
	var _mode = "AJX_DUPLICACY_DAILYLABNO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleAcceptance.cnt?hmode="+_mode+"&strDailyLabNo="+labNoConfiguration, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isSampleNoPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isSampleNoPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isSampleNoPresent;
}

//End AjaxFunctions

function onCheckSetValues(obj,k)
{	
	//alert("inside");
	document.getElementById("button").style.display=""; 
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
		
		if(cbs[i].checked)
    	{
     	 
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 
    	}
   
      }
	// alert(concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	 
	}

</script>
<script>
    $(document).ready(function() {
        var first = true;

        // Hide menu once we know its width
        $('#showmenu').click(function() {
            var $menu = $('.menu');
            if ($menu.is(':visible')) {
                // Slide away
                $menu.animate({left: -($menu.outerWidth() + 10)}, function() {
                    $menu.hide();
                });
            }
            else {
                // Slide in
                $menu.show().css("left", -($menu.outerWidth() + 10)).animate({left: 0});
            }
        });
    });
  
function validateNum(obj,e,size,inc)
{
	var setValue=obj.value;
	//alert(setValue);
	for(i=0;i<=size;i++)
		{
		document.getElementById(inc+"."+i+"dailyNO").value=setValue;
		}
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
	
}//end of numericOnly

function validateNumer(obj,e)
{
	 
	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
	
}//end of numericOnly



function setStatusc(obj)
{
	  
	
	
	if(obj.checked)
		{
		document.getElementsByName('setStatus')[0].value="1";
	//	alert(document.getElementsByName('setStatus')[0].value);
		}
	else
		{
		document.getElementsByName('setStatus')[0].value="0";
		//alert(document.getElementsByName('setStatus')[0].value);
		
		}
	}
	
	
function cancelFunc()
{
	window.parent.closeTab();
}
	
</script>
<script type="text/javascript">
        var isChecked = false;

        function allSelected() 
        {
          
        	document.getElementById("button").style.display=""; 
        	// this line is for toggle the check
            isChecked = !isChecked;

            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').attr('checked',isChecked);

            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
    </script>
   
    <script>
/* JS for demo only */
var colors = ['#1abc9c', '2c3e50', '2980b9', '7f8c8d', 'f1c40f', 'd35400', '27ae60'];

colors.each(function (color) {
  $$('.color-picker')[0].insert(
    '<div class="square" style="background: #' + color + '"></div>'
  );
});

$$('.color-picker')[0].on('click', '.square', function(event, square) {
  background = square.getStyle('background');
  $$('.custom-dropdown select').each(function (dropdown) {
    dropdown.setStyle({'background' : background});
  });
});

/*
 * Original version at
 * http://red-team-design.com/making-html-dropdowns-not-suck
 */ 
</script>
<style>

.scroll_div
{
width: 800px;
height:50px;
overflow-y: hidden;
overflow-x: scroll;
text-align: justify;
margin:0;
padding:10px 10px 10px 10px;
scrollbar-face-color: #666669;
scrollbar-highlight-color: #030000;
scrollbar-3dlight-color: #030000;
scrollbar-darkshadow-color:#030000 ;
scrollbar-shadow-color: #030000;
scrollbar-arrow-color: #030000;
scrollbar-track-color: #030000;
}
 
</style>
<style>
#colorCycle { background-color: #8C8984;
border: medium solid #1277b5;
padding-top: 5px; padding-right: 7px; padding-bottom: 7px; padding-left: 7px;
color: #FFF;
text-align: left;
animation-name: homeCycle;
animation-duration:6s;
animation-direction:alternate;
animation-iteration-count:infinite;

-webkit-animation-name: homeCycle;
-webkit-animation-duration:6s;
-webkit-animation-direction:alternate;
-webkit-animation-iteration-count:infinite; }
@keyframes homeCycle
{
0% {background-color:#8C8984;}
25% {background-color:#8C8984;}
50% {background-color:#8C8984;}
75% {background-color:#8C8984;}
}

@-webkit-keyframes homeCycle
{
0% {background-color:#8C8984;}
25% {background-color:#8C8984;}
50% {background-color:#8C8984;}
75% {background-color:#8C8984;}
} 
 #disable:hover{
  	background-color: yellow;
  }
</style>    	
<style>
#colorCycl   {
        -webkit-animation: color-change 1s infinite;
        -moz-animation: color-change 1s infinite;
        -o-animation: color-change 1s infinite;
        -ms-animation: color-change 1s infinite;
        animation: color-change 1s infinite;
    }

    @-webkit-keyframes color-change {
        0% { color: blue; }
        50% { color: blue; }
        100% { color: blue; }
    }
    @-moz-keyframes color-change {
        0% { color: blue; }
        50% { color: blue; }
        100% { color: blue; }
    }
    @-ms-keyframes color-change {
        0% { color: blue; }
        50% { color: blue; }
        100% { color: blue; }
    }
    @-o-keyframes color-change {
        0% { color: blue; }
        50% { color: blue; }
        100% { color: blue; }
    }
    @keyframes color-change {
        0% { color: blue; }
        50% { color: blue; }
        100% { color: blue; }
    }


</style>
<style>
/* The CSS */
#combo {
    padding:2.5px;
    margin: 0;
    -webkit-border-radius:3.5px;
    -moz-border-radius:3.5px;
    border-radius:4px;
    -webkit-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
    -moz-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
    box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
    background: #f8f8f8;
    color:#888;
    border:none;
    outline:none;
    display: inline-block;
    -webkit-appearance:none;
    -moz-appearance:none;
    appearance:none;
    cursor:pointer;
}

/* Targetting Webkit browsers only. FF will show the dropdown arrow with so much padding. */
@media screen and (-webkit-min-device-pixel-ratio:0) {
    select {padding-right:18px}
}

label {position:relative}
label:after {
    content:'>';
    background: url(/HISInvestigationG5/hisglobal/images/comboimage.png) no-repeat 96% 0;
    font:20px "Consolas", monospace,top;
    color:#aaa;
    -webkit-transform:rotate(90deg);
    -moz-transform:rotate(90deg);
    -ms-transform:rotate(90deg);
    transform:rotate(90deg);
    right:8px; top:-3.5px;
    padding:0 0 2px;
    border-bottom:1px solid #ddd;
    position:absolute;
    pointer-events:none;
}
label:before {
    content:'';
    right:6px; top:-2px;
    width:20px; height:20px;
    background:#1277b5;
    position:absolute;
    pointer-events:none;
    display:block;
}
</style>


<style>
.textBoxCss {
    background: white;
    color: #135d8c;
    width: 180px;
   /*  padding: 4px 10px 4px 15px; */
    border-radius: 20px;
    box-shadow: 0 1px 0 #ccc inset;
    transition: 500ms all ease;
    outline: 0;
   
}

 

</style>

<style>
.textBoxCssSub {
    background: white;
    color: #135d8c;
    width: 148px;
  /*   padding: 4px 10px 4px 15px; */
    border-radius: 20px;
    box-shadow: 0 1px 0 #ccc inset;
    transition: 500ms all ease;
    outline: 0;
    
}

 

</style>


<% String strdivage="\"\"";
String strdivdob="\"\""; %>

<%
String[] colorsDiv = {	"#ffe6e6", "#e6ffe6", "#ffe6cc", "#ffffcc", "#f2e6d9", 
						"#ffeee6", "#ccffeb", "#e6ffe6", "#c5edd7", "#ffebe6", 
						"#e6ffff", "#ffe6ff", "#ffeee6", "#ecf9f2", "#ffe6e6", 
						"#e6ffe6", "#ffe6cc", "#ffffcc", "#f2e6d9", "#ffeee6", 
						"#ccffeb", "#e6ffe6", "#c5edd7", "#ffebe6", "#e6ffff", 
						"#ffe6ff", "#ffeee6", "#ecf9f2", "#ffe6e6", "#e6ffe6", 
						"#ffe6cc", "#ffffcc", "#f2e6d9", "#ffeee6", "#ccffeb", 
						"#e6ffe6", "#c5edd7", "#ffebe6", "#e6ffff", "#ffe6ff", 
						"#ffeee6", "#ecf9f2", "#ffe6e6", "#e6ffe6", "#ffe6cc"
	 					}; %>
	 					
<body  onload="autoselect()">
<html:form   action="/sampleAcceptanceThroughBarCode" >
	<html:hidden name="SampleAcceptanceFB" property="hmode" />
	
		<html:hidden name="SampleAcceptanceFB" property="flag" />
	
	<html:hidden name="SampleAcceptanceFB" property="isPatDetailPage" />
	<html:hidden name="SampleAcceptanceFB" property="selectedCheckbox" />
	<html:hidden name="SampleAcceptanceFB" property="selectedRadioCheckbox" />
	 <html:hidden name="SampleAcceptanceFB" property="showStatus" />
	 <html:hidden name="SampleAcceptanceFB" property="currentPage" />
	  <html:hidden name="SampleAcceptanceFB" property="patCRNo" />
	  <html:hidden name="SampleAcceptanceFB" property="hideTile" />
	  	  <html:hidden name="SampleAcceptanceFB" property="sysDate" />
	  	  
	  	  	  <html:hidden name="SampleAcceptanceFB" value="0" property="setStatus" />
	  
	  
 
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="SampleAcceptanceFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
		
		<his:TitleTag name="Sample Acceptance Through Bar Code" >
		
		<div align="right" style="margin-right: 35px;">
           
            
            <input type="radio"  name="areaa" onclick="showdata(this,'area1','machine1')">Normal        
            <input type="radio"  name="areaa" onclick="showdata(this,'machine1','area1')">Machine Wise        
               
                  </div>
                  
		<body>
		<!-- <body onload=display_DateTime(); > -->
			<!-- <span  id='dateTime' ></span> -->
			</body>
		</his:TitleTag>
	 
		<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
               
         %>
      <bean:define name="SampleAcceptanceFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="SampleAcceptanceFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
    	
    	  <logic:notEqual name="SampleAcceptanceFB" property="hmode" value="GETDETAILS">
  			<logic:equal name="SampleAcceptanceFB" property="showStatus" value="0">
            <div class="menu" style="  position: relative;"> 
            
            
            <!-- added by chandan for short sample -->
            
            
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
						
							<%
								UserVO uservo=ControllerUTIL.getUserVO(request);
								      Date todayDateobj = new Date();
										SimpleDateFormat dateob = new SimpleDateFormat("yy");
										String strDate= dateob.format(todayDateobj);
								      String hospitalCode=uservo.getHospitalCode();
								      String val=uservo.getHospitalCode()+strDate;
							%>
							
							 <tr id="machine1" >
					 			<td class="tdfont" width="25%">
					 				<div align="right">
					 				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
															Machine Wise
													 </font>
					 					
					 				</div>
					 			</td>
					 			<td class="tdfonthead" width="25%" >
					 			<div align="left">
					 				 <html:select name="SampleAcceptanceFB" property="machineCodee" tabindex="1" onchange="showSearchDiv(this)">	
					 				 <html:option value="-1">Select Value</html:option>					
											<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"  />
										</html:select>  
									
									 </div>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfonthead" width="25%"></td>
				 			 </tr>	
				 			 
							
						<tr>
							<td width="25%" class="tdfont">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="LabType" />&nbsp;
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead"><logic:present
									name="<%=InvestigationConfig.LAB_COMBO%>">
									<div align="left">
										<span class="custom-dropdown small"> <html:select
												name="SampleAcceptanceFB" property="labCode"
												tabindex="1">
												<html:option value="%">All</html:option>
												<html:options
													collection="<%=InvestigationConfig.LAB_COMBO%>"
													property="value" labelProperty="label" />
											</html:select>
										</span>
									</div>
								</logic:present></td>

							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td class="tdfont" width="25%">
								<div id='divfromDate' style='<%=fromDateLabel%>' align="right">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="fromDate" />
									</font>
								</div>

							</td>
							<td class="tdfonthead" width="25%">
								<div id='divfromDateControl' style='<%=fromDateControl%>'
									align="left">
									<his:date name='fromDate' dateFormate="%d-%b-%Y"
										value="<%=frDate%>" />
								</div>

							</td>

							<td class="tdfont" width="25%">
								<div id='divfromDate' style='<%=toDateLabel%>' align="right">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="toDate" />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="25%">
								<div id='divfromDateControl' style='<%=toDateControl%>'
									align="left">
									<his:date name='toDate' dateFormate="%d-%b-%Y"
										value="<%=tDate%>" />
								</div>

							</td>

						</tr>
						
			
			     <tr>
			        <td width="25%"  class="tdfont">
			           <div align="right">
			               <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="barcodeNo"/>&nbsp;
						   </font>
				     </div>
			      </td>
			     <td width="25%" class="tdfont">
			  <%-- <html:text id="samplenobarcode" name="SampleAcceptanceFB" property="sampleNumber"	 maxlength="25" size="25" readonly="<%=this.readOnly=false %>"
					onkeypress="if(event.keyCode==13) getDetail()"
							tabindex="1">
								</html:text> --%>
				 <input type="text" id="samplenobarcode" 
				  name="sampleNumber"  maxlength="25" size="25"  onkeypress="if(event.keyCode==13) getDetail()"
							tabindex="1"/>
				 				
							<!-- 	<input type="text"  class="textBoxCss" name="sampleNumber" maxlength="25" size="25" onkeypress="return validateAlphaNumericOnly(event,this)"
							tabindex="1" /> -->
			      </td>
			 </tr>
			 <tr>
							<td class="tdfont" align="center" colspan="2" width="25%">
								<div align="right">
									<img class="button"
										src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) getDetail()"
										onclick="getDetail()" tabindex="1">
										
									
									<img class="button"
										src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) cancelFunc()"
										onclick="cancelFunc()" tabindex="1">
									
										<!-- <a  id="button"  class="cssButton"  style="cursor:pointer;text-decoration: none" onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">CANCEL</a> -->
										
								</div>
							</td>
						</tr>
			<!--  <tr>
			   <td   class="tdfont"    width="5%">
				  
				  <div align="center" id="centerer">
				 	 <a   id="button" class="cssButton"   style="cursor: pointer;text-decoration: none" onkeypress="if(event.keyCode==13) getDetail()   " onclick="getDetail()" tabindex="1">GO</a>
				 </div>
				 
			  </td >
			  </tr> -->
			 <!--  <tr>
			  <td class="tdfont"    width="25%">
			  <input type="checkbox"   onclick="setStatusc(this)" />
			  </td>
			 </tr>   -->
			     </table>
			     </div>
			     </logic:equal>
      </logic:notEqual>
			    	
			    	<logic:equal name="SampleAcceptanceFB" property="showStatus" value="3">
<%
						boolean flag=false;
					%>
					<%
						//Pagination Logic
								PaginationFB fbPage= new PaginationFB();
								pageContext.setAttribute("fbPagination",fbPage);
								fbPage.setCurrentPage(((SampleAcceptanceFB)request.getAttribute("SampleAcceptanceFB")).getCurrentPage());
								fbPage.setObjArrName(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS);
								fbPage.setAppendInTitle("List ");
								int maxRecord=10;
								fbPage.setMaxRecords(maxRecord);
					%>

					 
						<his:PaginationTag name="fbPagination"></his:PaginationTag>

						<his:SubTitleTag name="Sample Acceptance Through Barcode Details"></
  			</his:SubTitleTag>
 
						<logic:present name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS%>">
						
							<%
								flag=true;
							%>

							<table id="table2" width="100%"
								border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB">
								<tr>

									<td width="5%" align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="select" />
										</font>
									</b></td>
									<td width="10%" align="left"><b><font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="crNO" />
										</font></b></td>
								
										</font></b></td>
									<td width="10%" align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="labName" />
										</font></b></td>
									<td width="10%" align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="reqDate" />
										</font></b></td>
									<td width="20%" align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="patName" />
										</font></b></td>
									<td width="10%" align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="age" />
										</font></b></td>
									<td width="10%" align="left"><b><font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="patStatus" />
										</font></b></td>
									<td width="15%" align="left"><b><font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="departmentunit" />
										</font></b></td>
									<td width="10%" align="left"><b> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="visitDate" />
										</font></b></td>

								</tr>
						
							 </table> 


						</logic:present>
						
                   <logic:empty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS%>">
                    <center>
				   <font id="colorCycl"  size="4">
				      No Record Found  
				      </font>
				    </center>
                   
                   </logic:empty>
                    
 
  
				    
						<logic:notEmpty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS%>">

								<table  width="100%"
								border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB"> 

								<%
									List<SampleAcceptanceVO> lstPatVO=(List<SampleAcceptanceVO>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS);
									 		
											 int i,size=0;
									 		 
									 		if(lstPatVO!=null && lstPatVO.size()>0 )
									 			size=lstPatVO.size();
											int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
											int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
									 		
											for(int j=startIndex;j<=endIndex;j++)
											{
												//int i=j-startIndex;
												 
											if(j<size)
															{
												SampleAcceptanceVO voPat=lstPatVO.get(j);
												 
												System.out.println("big sample no::"+voPat.getSampleNo());
												
											String chkVal=voPat.getTempSampleNo()+"###"+voPat.getSampleNo();
											System.out.println("big sample no::"+chkVal);
								%>
								<tr>

									<td width="5%" align="left"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <input
											type="checkbox" name="bigSampleNo" value='<%=chkVal%>'
											onclick="onclickgo('<%=chkVal%>')">
									</font></td>
									<td width="10%" align="left">
										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatCRNo()%></font>
										</div>
									</td>

								
									<td width="10%" align="left">


										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getLabName()%></font>
										</div>
									</td>
									<td width="10%" align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getRequisitionDate()%></font>
										</div>

									</td>
									<td width="20%" align="left">


										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName()%></font>
										</div>
									</td>
									<td width="10%" align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge()%></font>
										</div>

									</td>
									<td width="10%" align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus()%></font>
										</div>

									</td>
									<td width="15%" align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatDeptName()+"/"+voPat.getPatUnitName()%></font>
										</div>
									</td>
									<td width="10%" align="left">

										<div align="left">
											<font color="#000000" size="2"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatVisitDate()%></font>
										</div>
									</td>

								</tr>
								<%
									}  }
								%>

							</table>
							
						</logic:notEmpty>
						
						<his:ButtonToolBarTag>
								<%	String val="NEW"; %>
	
     		<img class="button"
										src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) getDetail1()"
										onclick="getDetail1()" tabindex="1">
			   
	             
		  
     		<img class="button"
										src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) submitForm('<%=val %>')"
										onclick="submitForm('<%=val %>')" tabindex="1">
			   
	             </his:ButtonToolBarTag>
					 </logic:equal>
			    	 
			    	 
			    	
    
		  <logic:equal name="SampleAcceptanceFB" property="hmode" value="GETDETAILS">
       <logic:present name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS %>">
			 		 		<%
	 		Map<String,List<SampleAcceptanceVO>> mpSamp= (Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO);
			 	 int i,size=0;
				Iterator itr1=mpSamp.keySet().iterator();
			while(itr1.hasNext())
				{
				String sampleNo=(String)itr1.next();
			List<SampleAcceptanceVO> lstSampleAcceptanceVO=mpSamp.get(sampleNo);
				if(lstSampleAcceptanceVO!=null && lstSampleAcceptanceVO.size()>0 )
					size=lstSampleAcceptanceVO.size();
					 
					for(int k=0;k<size;k++)
						{
					SampleAcceptanceVO voPatientCollection=lstSampleAcceptanceVO.get(k);
	 		%>
 		 <his:SubTitleTag name="Sample Acceptance  Details"></
  			</his:SubTitleTag>
	 		<table style=" width: 100%" >
	 			<tr >
	 			              <td class="tdfont" >
					 		 		<div align="left">
					 		 		<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
										 	 <bean:message key="labName"/>&nbsp;
								    </font>
					 				
					 				</div>
					 			</td>
					 			<td id="colorCycle" >
					 		 		<div align="left">
					 				<b>	<%=voPatientCollection.getLabName() %></b>
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 			<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
										 <bean:message key="PackingListNo"/>&nbsp;
								    </font>
					 				</div>
					 			</td>
					 			  
					 			<td id="colorCycle" >
					 			<div align="left">
					 				<b><%=voPatientCollection.getPackingListNO() %></b>
					 				</div>
					 			</td>
					 			 
					 			 <td class="tdfont" >
					 			<div align="left">
					 			 <font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
								 	Packing List date
								    </font>
					 					
					 				</div>
					 			</td>
					 			 
					 			<td id="colorCycle" >
					 			<div align="left">
					 					<b><%=voPatientCollection.getPackingListDate() %></b>
					 				</div>
					 			</td>
				 	 </tr> 
				</table >	
 		 <%  break;} 
 		 break;
 		 }%>
 		 <logic:notEmpty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO %>">
 		<his:SubTitleTag name="Sample Details"></
  			</his:SubTitleTag>
  			<div id="dcalc">
	 		<table style=" width: 100%" >
	 			<tr bgcolor="#B2CCFF" >
	 			              <td width="5%" >
					 		 		<div align="left" >
					 		 		<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />
					 				<b><bean:message key="selectAll"/>&nbsp;</b>
					 				</div>
					 			</td>
					 			<td width="15%"  >
					 		 		<div align="left"  >
					 				<b>Sample Details</b>
					 					 
					 				</div>
					 			</td>
					 				<td width="15%"  >
					 			<div align="left">
					 			<b>	 <bean:message key="labNo"/></b>
					 				</div>
					 			</td>
					 			
					 			<td width="30%"  >
					 			<div align="left">
					 				
								<b> Sample Receive Status </b>
					 				</div>
					 			</td>
					 			  
					 		
					 		

				 </tr> 
				 </table>
				 
	 		<%
					Map<String,List<SampleAcceptanceVO>> _mpp=(Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO);
					boolean sameSampleNO=false;
						 
					String strCombo=(String)session.getAttribute(InvestigationConfig.TEST_REJECTION_REASON_COMBO);
						Iterator itrr=_mpp.keySet().iterator();
						int inc=1;
						boolean testTable=true;
						while(itrr.hasNext())
						{
					boolean firstTimeTravesa=true;
							
							 
						String sampleNo=(String)itrr.next();
						List<SampleAcceptanceVO> lstSampleAcceptanceVO=_mpp.get(sampleNo);
					 		if(lstSampleAcceptanceVO!=null && lstSampleAcceptanceVO.size()>0 )
					 			size=lstSampleAcceptanceVO.size();
					 		if(size>1)
					 			sameSampleNO=true;
					 		
					 	
					 		for(int k=0;k<size;k++)
					 		{
					 			
					 			SampleAcceptanceVO voSampleAccCollection=lstSampleAcceptanceVO.get(k);
								//For parent the value of requistion DNo is kept as 0. This will be used to distinguish parent from child					 			
					 			String chkVal=voSampleAccCollection.getPackingListNO()+"#"+voSampleAccCollection.getSampleNo()+"#"+voSampleAccCollection.getTestCode()+"#"+voSampleAccCollection.getTestName()+"#"+voSampleAccCollection.getLabName()+"#" + "0";
					 			String subChkVal=voSampleAccCollection.getPackingListNO()+"#"+voSampleAccCollection.getSampleNo()+"#"+voSampleAccCollection.getTestCode()+"#"+voSampleAccCollection.getTestName()+"#"+voSampleAccCollection.getLabName()+"#" + voSampleAccCollection.getRequisitionDNo()+"#"+voSampleAccCollection.getTempSampleNo()+"#"+voSampleAccCollection.getLabCode()+"#"+voSampleAccCollection.getLabNoConfiguration()+"#"+voSampleAccCollection.getPatType()+"#"+voSampleAccCollection.getShortSampleName()+"#"+voSampleAccCollection.getCollDate()+"#"+voSampleAccCollection.getPatCRNo()+"#"+voSampleAccCollection.getPatName()+"#"+voSampleAccCollection.getPatAge()+"#"+voSampleAccCollection.getPatGender(); 
					 			String labNO=voSampleAccCollection.getLabNoConfiguration();
					 			int lstSize=0;
					 			lstSize=lstSampleAcceptanceVO.size();
					 			//labNO="1";
					 			if(firstTimeTravesa)
					 			{
											%>
											<table width ="100%" >
				 			<tr bgcolor="#BBCCFF">
				 			    	  <td   width="5%"  align="left">
				 			    <div align="left"   > 
<b>				 			    <%String chkBox="onClickCheck(this,"+size+","+inc+")"; %></b>
				 			    <input  type="checkbox" class="jpCheckbox" name="chkSamplePat" value='<%=chkVal %>'  onChange="<%=chkBox %>"> </div>
				 			    </td> 
					 			<td  width="15%">
					 				<div align="left"  >
					 				 <bean:message key="sampleNo"/>:&nbsp;&nbsp;
					 				<b> <%=voSampleAccCollection.getTempSampleNo() %> &nbsp;&nbsp; ( <%=voSampleAccCollection.getSampleName() %> )</b><br>

<b> CR No.: <%=voSampleAccCollection.getPatCRNo() %> &nbsp;&nbsp; Pat. Name : <%=voSampleAccCollection.getPatName() %> </b>
					 				</div>
					 			</td>
					 			<%-- <td width="15%">
					 			<div align="left">
					 				<bean:message key="SampleName"/>:&nbsp;
					 					<b> <%=voSampleAccCollection.getSampleName() %>
					 					
					 					</b> 
					 				</div>
					 			</td> --%>
					 			
					 			
					 			
					 			
					 				<%if(labNO.equals(InvestigationConfig.MANUAL_LAB))
					 			{
					 				%>
					 			<td width="15%">
								 <div align="left">
								 	<font color=" ">	
								 	<%String strDaily="validateNum(this,event,"+size+","+inc+" )"; %>  
								 	<input type="text" style="text-align: right" class="textBoxCss" id="<%=inc %>.dailyNO" name="labNoConfiguratio"  maxlength="20" size="15"  onkeypress="return validateNumeric(event,this)"  onkeyup="return <%=strDaily %>"  onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />
								 	 </font>
								 	</div>
					 			</td>
					 			<%}
					 			else if(labNO.equals(InvestigationConfig.SAME_AS_SAMPLE_LAB))
					 			{
					 			%> 
					 			<td width="15%" >
								  <div align="left">
								 <input type="text" style="text-align: right" class="textBoxCss" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguratio"  maxlength="20" size="15" value="<%=voSampleAccCollection.getTempSampleNo() %>"  onkeypress="return validateNumeric(event,this)"  onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />								 			
								 </div>
					 			</td>
					 			<%}
					 			else
					 			{
					 			%> 
					 			<td width="15%">
								   <div align="left" id="<%=inc %>.dailyNO">
								 		<html:hidden name="SampleAcceptanceFB" property="labNoConfiguration" />
								 	</div>
					 			</td>
					 		
					 			
					 			
					 			<%} %>
					 			
					 			
					 			
					 			<td width="30%" >
					 			<div align="left" >
					 			&nbsp;&nbsp;
					 				<b>RECEIVE STATUS</b>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 			<%String StrRecv="onChaneRecieve(this,"+size+","+inc+" )"; %>
								<span class="custom-dropdown small">
								 <select id="<%=inc %>.recieved" name="recieve"  onChange="<%=StrRecv %>"   style="width:100;" tabindex="1">
									<option value="1">Yes</option>
									<option value="0">No</option>
								 </select>
								 </span>
					 				</div>
					 			</td>
					 		
					 			 
				 			</tr>
				 			
				 			</table>
				 			
				 			<%if(testTable)
				 				{%>
				 			<table width="92%"  align="right"   bgcolor="#F8F8FF" style="color:red;">
				 			
				 			<tr>
				 			<td width="5%"> </td>
				 		
				 			<td width="20%"><b><u><bean:message key="testName"/></u></b> </td>
				 				<td width="20%"><b><u> </u></b></td>
				 			<td width="15%"><b><u><bean:message key="accepted"/></u></b></td>
				 		<td width="15%"><b><u><bean:message key="rejectionAction"/></u></b></td>
				 		<td width="15%"><b><u><bean:message key="RejectionReason"/></u></b></td>
				 	        <td width="10%"><b><input type="checkbox" checked="checked" id="isselectallmachines" title="Tick for selecting all machines at once and Untick for selecting machine one by one"><u><bean:message key="machine"/></u></b></td>
				 			
				 			</tr>
				 			
				 			
				 			
				 			</table>
				 			
				 		<% testTable=false;} %>
				 			 <%}
					 			 //logic to hide the vo for same packing list no
					 			 if(sameSampleNO)firstTimeTravesa=false;  // making second iteration false; 
					 			%>
					 			
					 			<table style=" width: 92%" align="right"  bgcolor="<%=colorsDiv[k]%>">
					 			
				 			 <tr  id="disable">
				 			    
				 			    	   <td   width="5%"   align="left">
				 			          <div align="left"   > 		
					 				 <%String setChkBox="onCheckSetValues(this,"+k+")";
					 				 String strId= inc + "." +k;  %>
					 				<input   type="checkbox" class="jpCheckbox" id="<%=inc%>.<%=k%>chkbox" name="chkSamplePatient"       onchange="<%=setChkBox %>" value='<%=subChkVal%>'  > 
				 			    	</div>	 			   
				 			    	 </td> 
				 			     <td width="20%"  id="colorCycl"  >
					 				<div align="left"  >
					 				  <%=voSampleAccCollection.getTestName() %>  
					 				  <input type="hidden"   value="<%=voSampleAccCollection.getRequisitionDNo() %>" name="tempReqDNo"/>
					 				</div>
					 			    <input type="hidden" id="<%=inc%>.<%=k%>recieved" value="1" name="recieved"/>
				 			   </td>
				 			   
				 			       
				 		<!-- when only single test -->
				 		
				 		<!-- single test ends -->
				 		 <%if(labNO.equals(InvestigationConfig.MANUAL_LAB))
					 			{
					 				%>
					 			<td width="20%" >
								 	<div align="left"  >
								 	<%if(lstSize!=1)
								 		{%>
								 	  <input type="text" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguration"  maxlength="20" size="15"  onkeypress="return validateNumeric(event,this)"     onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />
		     			  		 	<%} 
		     			  		 	else
		     			  		 	{%>
		     			  		 	<input type="hidden" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" value="<%=labNO%>" readonly="true" name="labNoConfiguration"/>
		     			  		 	<%} %>
		     			  		 	</div>
					 			</td>
					 			<%}
					 			else if(labNO.equals(InvestigationConfig.SAME_AS_SAMPLE_LAB))
					 			{
					 			%> 
					 			<td width="20%">
								 	<div align="left"  >	<%if(lstSize!=1)
								 		{%>
								 	  <input type="text" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguration"  maxlength="20" size="15"  onkeypress="return validateNumeric(event,this)"     onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />
		     			  		 	<%} 
		     			  		 	else
		     			  		 	{%>
		     			  		 	<input type="hidden" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" value="<%=labNO%>" readonly="true" name="labNoConfiguration"/>
		     			  		 	<%} %> </div>
					 			</td>
					 			<%}else { %>
					 			
					 			<td width="20%" >
								 			<div align="left"  >
								 					 
             					 			  <input type="hidden" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" value="<%=labNO%>" readonly="true" name="labNoConfiguration"/>
	    					 				</div>
					 			</td>
					 			
					 			
					 			<%} %>
				 		      <td width="15%">
								         <div align="left">
								         <%String strSubAcc="onChaneSubAcc(this,"+inc+","+k+")"; %>
									    <span class="custom-dropdown small">
									      <select id="<%=inc%>.<%=k%>accepted" name="accepted"  onchange="<%=strSubAcc %>" style="width:100;" tabindex="1">
												<option value="1"><bean:message key="yes"/> </option>
												<option value="0"><bean:message key="no"/> </option>
											 </select>
											 </span>
									     </div>
						       </td>  
								<td width="15%"  >
								         <div align="left" >
									    <span class="custom-dropdown small">
									    <%String strSubRejActn="onChaneSubRejActn(this,"+inc+","+k+")"; %>
									     <select   id="<%=inc %>.<%=k%>rejectnActn" name="rejectionAction"  onchange="<%=strSubRejActn %>" disabled="disabled" style="width:100;" tabindex="1">
												<option value="-1">Select value </option>
												<option value="1"><bean:message key="cancelled"/></option>
											 </select>
											 </span>
									     </div>
						       </td>   
				 		   <td width="15%">
				 		   <div align="left" >
		 			   <span class="custom-dropdown small">
		 			    <%String strSubRejReason="onChaneSubRejReason(this,"+inc+","+k+")"; %>
		 			     <select id="<%=inc %>.<%=k%>rejectnRsn"  onchange="<%=strSubRejReason %>"  disabled="disabled" name='rejectionReason' tabindex='1'>
		 					<%=strCombo%>
		 			     </select>
		 			     </span>
		 			     </div>
				 		</td>
						
						
						<td width="10%">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								 <% if(voSampleAccCollection.getMachineCode()==null || voSampleAccCollection.getMachineCode().equals("-1") ){ %>
	 								<html:select name="SampleAcceptanceFB" property="testBasedMachine"  tabindex="1" value="<%=voSampleAccCollection.getDefaultmachineCode() %>" onchange="selectallmachines(this)">
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>">
						 	   								<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							<%}
	 								 else{ %>
		      							
	      							
	      							<html:select name="SampleAcceptanceFB" property="testBasedMachine"  tabindex="1" value="<%=voSampleAccCollection.getMachineCode() %>" onchange="selectallmachines(this)">
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>">
						 	   								<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							<%} %>
	      							</span>
					 			</div>
					 			</td>
					 			
						
				 		<%String validation="validation(this,"+inc+","+k+")"; %>
				 			 </tr>
				 			
		     <%   } 
					 		inc++;
			}
		%>
								</table>
								</div>
		
								</logic:notEmpty>
		<logic:empty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO %>">
				  	        <center>
				   <font id="colorCycl"  size="4">
				      No Record Found  
				      </font>
				    </center>
				    </logic:empty>
						</logic:present>		
               </logic:equal>
			      </his:ContentTag>
			       <his:ButtonToolBarTag>
				    <his:statusTransactionInProcess>
				  	 <logic:notEmpty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS %>">
				          <a  id="button1"  class="cssButton"   onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="onSave();" >SAVE</a>
				  	      <a  id="button"  class="cssButton"  style="cursor:pointer;text-decoration: none" onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">CANCEL</a>
				    </logic:notEmpty>
				  
				    </his:statusTransactionInProcess>
				    <logic:empty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS %>">
				    	<!-- 	<a  id="button"  class="cssButton"  style="cursor:pointer;text-decoration: none" onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">CANCEL</a> -->
				    		</logic:empty>
		 	</his:ButtonToolBarTag>
		<his:SubTitleTag>
		<%-- <his:name>
			<bean:message key="legends"/>
		</his:name> --%>
		<%-- <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table> --%>
	</his:SubTitleTag>
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="blue"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	 <bean:message key="normal"/>
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="red"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	 <bean:message key="urgent"/>
					</div>
					</font>
				</td>				
			</tr>
		</table>
	</his:ContentTag>
	</div>
		<his:status/>
	 	</html:form>
</body>