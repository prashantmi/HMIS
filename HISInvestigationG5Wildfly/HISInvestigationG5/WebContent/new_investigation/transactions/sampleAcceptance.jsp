<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.SampleAcceptanceVO"%>
<%@page import="org.eclipse.persistence.jpa.jpql.parser.EntryExpression"%>
<%@page import="com.lowagie.tools.arguments.OptionArgument.Entry"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
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
<his:css src="/hisglobal/css/textboxcss.css" />
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
		document.getElementById("machine1").style.display = "";
		document.getElementsByName('areaa')[1].checked=true;
    	//document.getElementsByName("areaa")[0].disabled=true;
    	//document.getElementsByName("areaa")[1].disabled=false;
		
		}
	else
		{}
	
	if(document.getElementsByName('flag')[0].value=='')
	{
	     // alert(document.getElementsByName('flag')[0].value);
			
	document.getElementsByName('areaa')[0].checked=true;
	document.getElementById("machine1").style.display = "none";
	
	}else
	{}
	

	if(document.getElementsByName('flag')[0].value=='0')
		{

		// alert("a"+document.getElementsByName('flag')[0].value);
			
		document.getElementsByName('areaa')[0].checked=true;
		document.getElementById("machine1").style.display = "none";
    	//document.getElementsByName("areaa")[1].disabled=true;
    	//document.getElementsByName("areaa")[0].disabled=false;
		
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
		document.getElementsByName('hmode')[0].value="NEW";
    	document.forms[0].submit();
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
	
	   if(!validateTodayOrDate())
  	 {return false;}
   
	
	
            if(document.getElementsByName("labCode")[0].value=="-1")
        	{
            	 
        		alert("Select Lab Name") ;
        		document.getElementsByName("labCode")[0].focus();
        		return false;
        	}
        	 
        	document.getElementsByName('hmode')[0].value="GETDETAILS";
        	document.forms[0].submit();
	
   return true;
	
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
	//  document.getElementById('gob').style.display="";
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
 		document.getElementById(inc+"."+i+"rejectnActn").value='1';
 		document.getElementById(inc+"."+i+"rejectnRsn").disabled=false;
 		
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
 			document.getElementById(inc+"."+i+"rejectnActn").value='-1';
 	 		document.getElementById(inc+"."+i+"rejectnRsn").disabled=true;
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
	 		
		 var idde=obj.className;
 		 // idde=idde;
 		 var grp=idde.split("#")[2];
 		 
 		 if(grp!='undefined' && grp!='0' && grp!='null')
 		 for(var l=0;l<document.getElementsByClassName(idde).length;l++)
 			 {
 		         // alert( document.getElementsByClassName(idde)[l].id);
 		          var idf=document.getElementsByClassName(idde)[l].id;
 		          document.getElementById(idf).value=obj.value;
 		          
 			 }
	 
 }
 
 function onChaneSubRejReason(obj,inc,k)
 {
	
	
		  
		 document.getElementById(inc+"."+k+"rejectnRsn").value=obj.value;
	 		
		 var idde=obj.className;
 		 // idde=idde;
 		 var grp=idde.split("#")[2];
 		 
 		 if(grp!='undefined' && grp!='0' && grp!='null')
 		 for(var l=0;l<document.getElementsByClassName(idde).length;l++)
 			 {
 		         // alert( document.getElementsByClassName(idde)[l].id);
 		          var idf=document.getElementsByClassName(idde)[l].id;
 		        
 		          document.getElementById(idf).value=obj.value;
 		          
 			 }
	 
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

 		  var idde=obj.className;
 		 // idde=idde;
 		 var grp=idde.split("#")[2];
 		 var hideshow=idde.split("#")[0];
 		 
 		 if(grp!='undefined' && grp!='0' && grp!='null')
 		 for(var l=0;l<document.getElementsByClassName(idde).length;l++)
 			 {
 		          var idf=document.getElementsByClassName(idde)[l].id;
                  var hideshoww=idf.replace(/accepted/g, '');
  		        // alert(hideshoww);
 	 		          document.getElementById(idf).value="0";
 		      
 	 		        document.getElementById(hideshoww+"dailyNO").disabled=true;
 	 		 		document.getElementById(hideshoww+"rejectnActn").disabled=false;
 	 		 		document.getElementById(hideshoww+"rejectnRsn").disabled=false;
 	 		 		document.getElementById(hideshoww+"dailyNO").disabled=true;
 	 		 		document.getElementById(hideshoww+"recieved").disabled=true;
 	 		 		document.getElementById(hideshoww+"chkbox").disabled=true;
 		 	 		          
 			 }
			 
 			}
 		 
 		 
 	else{
 		 
 	 		document.getElementById(inc+"."+k+"dailyNO").disabled=false;
 	 		document.getElementById(inc+"."+k+"rejectnActn").disabled=true;
 	 		document.getElementById(inc+"."+k+"rejectnRsn").disabled=true;
 	 		document.getElementById(inc+"."+k+"dailyNO").disabled=false;
 	 		document.getElementById(inc+"."+k+"recieved").disabled=false;
 	 		document.getElementById(inc+"."+k+"chkbox").disabled=false;
 	 		 
 	 	  var idde=obj.className;
 	 	var grp=idde.split("#")[2];
 		 // idde=idde;
 		 if(grp!='undefined' && grp!='0' && grp!='null')
 		 for(var l=0;l<document.getElementsByClassName(idde).length;l++)
 			 {
 		          
 		          var idf=document.getElementsByClassName(idde)[l].id;
                  var hideshoww=idf.replace(/accepted/g, '');

 		          document.getElementById(idf).value="1";

 		        
 		         
 		         document.getElementById(hideshoww+"dailyNO").disabled=false;
 	 	 		document.getElementById(hideshoww+"rejectnActn").disabled=true;
 	 	 		document.getElementById(hideshoww+"rejectnRsn").disabled=true;
 	 	 		document.getElementById(hideshoww+"dailyNO").disabled=false;
 	 	 		document.getElementById(hideshoww+"recieved").disabled=false;
 	 	 		document.getElementById(hideshoww+"chkbox").disabled=false;
 	 	 		 
 			 }
 	   }
 	 
 	
 	}
 
  
function onClickCheck(obj,size,inc)
{
	//alert(k);
	if(obj.checked)
		{
		
		for(i=0;i<=size;i++)
			{ 
			var q=	document.getElementById(inc+"."+i+"chkbox").checked = true;
			 onCheckSetValues(document.getElementById(inc+"."+i+"chkbox"),1);
		 
			}
	//   document.getElementById('gob').style.display="none";
		}
	  else
	     	{
		  for(i=0;i<=size;i++)
			{ 
	      	//document.getElementById("button").style.display="none";
	      	document.getElementById("button").style.display="none";
		  document.getElementById(inc+"."+i+"chkbox").checked = false;
			}  
			}


}


function onClickCheckReject(obj,size,inc)
{
	//alert(k);
	if(obj.checked)
		{
		
		for(i=0;i<=size;i++)
			{ 
		document.getElementById(inc+"."+i+"chkbox").checked = true;
		document.getElementById("rejectbutton").style.display="";
		document.getElementById("buttonModifyAccepted").style.display="";
		 
			}
	  // document.getElementById('gob').style.display="none";
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

		document.getElementById("button").style.display = "none";
		
		
	var count=0;
	 
	var concatenateChkBoxVal="";
	var concatenateChkBoxValnew="";
	var cbs =document.getElementsByName('chkSamplePatient');
	 
	
	
	var newcheck =document.getElementsByName('accepted');
	 
	for(var i=0; i < newcheck.length; i++) {
		
	//	alert(document.getElementsByName('accepted')[i].id);
		
		var idd=document.getElementsByName('accepted')[i].id;
		
		var val=document.getElementById(idd).value;
		//alert(val);
		var newid=idd.replace(/accepted/g, '');
		//alert(newid);
		
		
		if(val=="0")
			{
		
			var ide=newid+"rejectnActn";
				//alert(ide);
			if(document.getElementById(ide).value=="-1")
			{
				alert("Please Select Rejection Action");
				document.getElementById("button").style.display = "";
				     
			        return null;
			}
			
			if(document.getElementById(ide).value=="1")
			{
				var idee=newid+"rejectnRsn";

			
				if(document.getElementById(idee).value=="-1")
				{
					alert("Please Select Rejection Reason");
					document.getElementById("button").style.display = "";
					
				        return null;
				}
				
				
				
			}
			
			
			}
	
	}
	
	//return null;
    	
	for(var i=0; i < cbs.length; i++) {
			
		if(cbs[i].checked)
    	{
			   concatenateChkBoxValnew =concatenateChkBoxValnew.concat(cbs[i].value);
     	    	concatenateChkBoxValnew+='@';
     	    	
    	count++;	
		var id=cbs[i].id;
		
		 
		var indexWithSubIndex=id.replace('chkbox', '');
    
		var index=indexWithSubIndex.split(".")[0];
		
		//var subIndex=indexWithSubIndex.split(".")[1];
		
		if(document.getElementById(indexWithSubIndex+"accepted").value=="1"&&document.getElementById(index+".dailyNO").value=="")
	 	{
	 		 
	 		 
		     	alert("Enter Lab No"); 
	 		document.getElementById(indexWithSubIndex+"dailyNO").focus();
	 		return false;
	 	}
    	
    	 }
		
      }
    
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxValnew;
    //alert("concatenateChkBoxVal"+concatenateChkBoxValnew);
    //return null;
    
	//alert("stop");return false;
	
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

function onReject()
{


	if (confirm('Are you sure you want to Reject Sample')) {
	    // Save it!
	
	
	var count=0;
	 
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatient');
	 
	for(var i=0; i < cbs.length; i++) {
			
		if(cbs[i].checked)
    	{
    		
    	count++;	
		var id=cbs[i].id;
		 
		var indexWithSubIndex=id.substring(0, 3);
    	
		//var index=indexWithSubIndex.split(".")[0];
		//var subIndex=indexWithSubIndex.split(".")[1];
		    	
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
	document.getElementsByName('hmode')[0].value="REJECT";
	
	document.forms[0].submit();
	
   return true;
	
	}
 else {
    // Do nothing!
}
	
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
function setOldValue(x) {
 // alert("X is  "+x.value);
 // var a = document.getElementsByName("oldValue").value;
 //alert("a is "+a);
  document.getElementsByName("oldValue").value = x.value;
  var b = document.getElementsByName("oldValue").value;
  //alert("b is "+b);
}

function chkDailyLabNoDuplicacyThroughAjax(obj,event)
{

	 //var oldLabNumber = document.getElementsByName("oldValue").value;
	 //alert("old is "+oldLabNumber);
	//alert("check lab no");
	var labNoConfiguration=obj.value;
	//alert("labNoConfiguration "+labNoConfiguration);
	//var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;
	//alert(labNoConfiguration.length);
	if(labNoConfiguration.length<0)
		return true;

	//if(oldLabNumber == labNoConfiguration)
		//return true;
	
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

function chkDailyLabNoDuplicacyThroughAjaxModify(obj,event)
{

	 var oldLabNumber = document.getElementsByName("oldValue").value;
	 //alert("old is "+oldLabNumber);
	//alert("check lab no");
	var labNoConfiguration=obj.value;
	//alert("labNoConfiguration "+labNoConfiguration);
	//var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;
	//alert(labNoConfiguration.length);
	if(labNoConfiguration.length<0)
		return true;

	if(oldLabNumber == labNoConfiguration)
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
function CheckAutoSampleNoFormate(LabCode,TestCode,patType,tempSampleNo)
{
//alert("ajax"+tempSampleNo);
	//alert("inside Ajax"+sampleArea);
	
	var flg = false;
	var autoGenFormate = "";
	var _mode = "AJX_CHECK_AUTO_SAMPLENO_GEN";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleAcceptance.cnt?hmode="+_mode+"&labCode="+LabCode+"&testCodee="+TestCode+"&patType="+patType+"&tempSampleNo="+tempSampleNo, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			autoGenFormate = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return autoGenFormate;
}

function onCheckSetValues1(obj,k)
{	
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	
	
	 
	//chkBoxSample[i].checked=false;
	var checkValueForAutoGen= obj.value;
	var splitedAutoGenValue=checkValueForAutoGen.split("#");

	var labCode=splitedAutoGenValue[7];
	var testCode=splitedAutoGenValue[2];
	var patType=splitedAutoGenValue[9];
	var sampleconfig=splitedAutoGenValue[8];
	var testName=splitedAutoGenValue[3];
	var islabnoconfigure=splitedAutoGenValue[16];
	
	//alert(testCode);
if(sampleconfig==2)	 
	 {
	//var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig);
	var autoGenFormate=islabnoconfigure;
	//alert(autoGenFormate);
if(autoGenFormate == '' )
	{
	 //document.getElementById("button").style.display="none";
    alert("Please configured Lab No. for Test " +testName);
    $('input:checkbox').removeAttr('checked');  
	  document.getElementById("button").style.display="none";
        
	
	}
	else
		{
		document.getElementById("button").style.display="";		 
		}
	 }
else
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

/* function onCheckSetValues(obj,k)
{	
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	
	for(var i=0;i<chkBoxSample.length;i++)
	{
	 
	//chkBoxSample[i].checked=false;
	var checkValueForAutoGen= obj.value;
	var splitedAutoGenValue=checkValueForAutoGen.split("#");

	var labCode=splitedAutoGenValue[7];
	var testCode=splitedAutoGenValue[2];
	var patType=splitedAutoGenValue[9];
	var sampleconfig=splitedAutoGenValue[8];
	var testName=splitedAutoGenValue[3];
	//alert(splitedAutoGenValue[17]);
	var islabnoconfigure=splitedAutoGenValue[16];
	
	//alert(testCode);
if(sampleconfig==2)	 
	 {
	 
	//var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig);
	var autoGenFormate=islabnoconfigure;
	//alert(autoGenFormate);
if(autoGenFormate == '' )
	{
	 //document.getElementById("button").style.display="none";
    alert("Please configured Lab No. for Test " +testName);
    $('input:checkbox').removeAttr('checked');  
	  document.getElementById("button").style.display="none";
         break;
	
	}
	else
		{
		document.getElementById("button").style.display="";		 
		}
	 }
else
	document.getElementById("button").style.display="";
	}
	
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
		
		if(cbs[i].checked)
    	{
     	 
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 
    	}
   
      }
	 alert(concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	 
	
	
} */


//chandan


function onCheckSetValues(obj,k)
{	
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	
	for(var i=0;i<chkBoxSample.length;i++)
	{
	 
	//chkBoxSample[i].checked=false;
	var checkValueForAutoGen= obj.value;
	var splitedAutoGenValue=checkValueForAutoGen.split("#");

	var labCode=splitedAutoGenValue[7];
	var testCode=splitedAutoGenValue[2];
	var patType=splitedAutoGenValue[9];
	var sampleconfig=splitedAutoGenValue[8];
	var testName=splitedAutoGenValue[3];
	//alert(splitedAutoGenValue[17]);
	var islabnoconfigure=splitedAutoGenValue[16];
	var grpcode=splitedAutoGenValue[17];
	var reqno=splitedAutoGenValue[14];

//	alert("grpcode"+grpcode);
	//alert(testCode);
if(sampleconfig==2)	 
	 {
	 
	//var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig);
	var autoGenFormate=islabnoconfigure;
	//alert(autoGenFormate);
if(autoGenFormate == '' )
	{
	 //document.getElementById("button").style.display="none";
    alert("Please configured Lab No. for Test " +testName);
    $('input:checkbox').removeAttr('checked');  
	  document.getElementById("button").style.display="none";
         break;
	
	}
	else
		{
		document.getElementById("button").style.display="";		 
		}
	 }
else
	document.getElementById("button").style.display="";
	}
	

	if(obj.checked)
		{

		//alert("checked");

		var datanew=obj.value;

		var splitedAutoGenValuenewnew=datanew.split("#");

		var grpcodenewnew=splitedAutoGenValuenewnew[17];
		var reqnonewnew=splitedAutoGenValuenewnew[14];

		   var flag="0";
          

			var cbsnew =document.getElementsByName('chkSamplePatient');

			if(grpcodenewnew!="null" && grpcodenewnew!="0")
            {

				for(var i1=0; i1 < cbsnew.length; i1++)
			     {

						var data=cbsnew[i1].value;

		             var splitedAutoGenValuenew=data.split("#");
						
						var grpcodenew=splitedAutoGenValuenew[17];
						var reqnonew=splitedAutoGenValuenew[14];

						if(reqnonew==reqnonewnew && grpcodenew==grpcodenewnew )
		         	   {
						//	alert("matchreqno group");
			            	   
		         		   flag="1";
		         		   
		         		   cbsnew[i1].checked=true;

			         	   
		         	   }
			            
				    
				}
				
            }
			else
	{
				// alert("not group");
	               flag="1";
	}
		

		}
	else
		{

		//alert("unchecked");
		

		var datanew=obj.value;

		var splitedAutoGenValuenewnew=datanew.split("#");

		var grpcodenewnew=splitedAutoGenValuenewnew[17];
		var reqnonewnew=splitedAutoGenValuenewnew[14];

		   var flag="0";
          

			var cbsnew =document.getElementsByName('chkSamplePatient');

			if(grpcodenewnew!="null" && grpcodenewnew!="0")
            {

				for(var i1=0; i1 < cbsnew.length; i1++)
			     {

						var data=cbsnew[i1].value;

		             var splitedAutoGenValuenew=data.split("#");
						
						var grpcodenew=splitedAutoGenValuenew[17];
						var reqnonew=splitedAutoGenValuenew[14];

						if(reqnonew==reqnonewnew && grpcodenew==grpcodenewnew )
		         	   {
						//	alert("matchreqno group uncked");
			            	   
		         		   flag="1";
		         		   
		         		   cbsnew[i1].checked=false;

			         	   
		         	   }
			            
				    
				}
				
            }
			else
	{
				// alert("not group unchecked");
	               flag="1";
	}
			
		}
	
}

//end


function onCheckSetValuesReject(obj,k)
{	
	
	
	document.getElementById("rejectbutton").style.display=""; 
	document.getElementById("buttonModifyAccepted").style.display=""; 
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
		
		if(cbs[i].checked)
    	{
     	 
    	//concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	//concatenateChkBoxVal+='@';
    	 
    	}
   
      }



    
	// alert(concatenateChkBoxVal);
	//document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	 

if(obj.checked)
		{

		//alert("checked");

		var datanew=obj.value;

		var splitedAutoGenValuenewnew=datanew.split("#");

		var grpcodenewnew=splitedAutoGenValuenewnew[17];
		var reqnonewnew=splitedAutoGenValuenewnew[14];

		   var flag="0";
          

			var cbsnew =document.getElementsByName('chkSamplePatient');

			if(grpcodenewnew!="null" && grpcodenewnew!="0")
            {

				for(var i1=0; i1 < cbsnew.length; i1++)
			     {

						var data=cbsnew[i1].value;

		             var splitedAutoGenValuenew=data.split("#");
						
						var grpcodenew=splitedAutoGenValuenew[17];
						var reqnonew=splitedAutoGenValuenew[14];

						if(reqnonew==reqnonewnew && grpcodenew==grpcodenewnew )
		         	   {
						//	alert("matchreqno group");
			            	   
		         		   flag="1";
		         		   
		         		   cbsnew[i1].checked=true;

			         	   
		         	   }
			            
				    
				}
				
            }
			else
	{
				// alert("not group");
	               flag="1";
	}
		

		}
	else
		{

		//alert("unchecked");
		

		var datanew=obj.value;

		var splitedAutoGenValuenewnew=datanew.split("#");

		var grpcodenewnew=splitedAutoGenValuenewnew[17];
		var reqnonewnew=splitedAutoGenValuenewnew[14];

		   var flag="0";
          

			var cbsnew =document.getElementsByName('chkSamplePatient');

			if(grpcodenewnew!="null" && grpcodenewnew!="0")
            {

				for(var i1=0; i1 < cbsnew.length; i1++)
			     {

						var data=cbsnew[i1].value;

		             var splitedAutoGenValuenew=data.split("#");
						
						var grpcodenew=splitedAutoGenValuenew[17];
						var reqnonew=splitedAutoGenValuenew[14];

						if(reqnonew==reqnonewnew && grpcodenew==grpcodenewnew )
		         	   {
							//alert("matchreqno group uncked");
			            	   
		         		   flag="1";
		         		   
		         		   cbsnew[i1].checked=false;

			         	   
		         	   }
			            
				    
				}
				
            }
			else
	{
				// alert("not group unchecked");
	               flag="1";
	}
			
		}
		

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

function cancelFunc()
{
	window.parent.closeTab();
}

</script>
<script type="text/javascript">
        var isChecked = false;

        function allSelected(obj,size) 
        {
        	
          
        	if(obj.checked)
    		{
        		//alert("");
    		var sizee=document.getElementsByName('chkSamplePat');
    		for(var j1=0;j1<sizee.length;j1++)
    			{ 
    			document.getElementsByName("chkSamplePat")[j1].checked = true;
    			//var element=document.getElementsByName("chkSamplePat")[j1];
    			//alert(r);
    		//	onClickCheck(element,1,1);
    		 
    			}
    		
    		sizee=document.getElementsByName('chkSamplePatient');
    		for(var j1=0;j1<sizee.length;j1++)
			{ 
			document.getElementsByName("chkSamplePatient")[j1].checked = true;
			
			onCheckSetValues1(document.getElementsByName("chkSamplePatient")[j1],0);
			//var element=document.getElementsByName("chkSamplePat")[j1];
			//alert(r);
		//	onClickCheck(element,1,1);
		 
			}
    		
    		
    //	   document.getElementById('gob').style.display="none";
    		}
    	  else
    	     	{
    		  //alert("s");
    		  $('input:checkbox').removeAttr('checked');  
    		  document.getElementById("button").style.display="none";
    			}
           
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
        
        function allSelectedReject() 
        {
          
        	document.getElementById("rejectbutton").style.display="";
        	document.getElementById("buttonModifyAccepted").style.display=""; 
        	// this line is for toggle the check
            isChecked = !isChecked;

            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').attr('checked',isChecked);

            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
    </script>
    <script type="text/javascript">
    function display_DateTime()
    {
     var refresh=1000; // Refresh rate in milli seconds
       mytime=setTimeout('display_ct()')
      }
 function display_ct()
 {
    var strcount
      var x = new Date()
            	    
     switch (new Date().getMonth())
     {
           case 0:
                 day = "Jan";
                 break;
           case 1:
            	 day = "Feb";
            	 break;
           case 2:
            	day = "Mar";
                break;
           case 3:
                day = "Apr";
                break;
 		    case 4:
	             day = "May";
	              break;
		    case 5:
		         day = "Jun";
		  	      break;
		   	case 6:
		   	     day = "Jul";
		   	     break;
		   	case 7:
		         day = "Aug";
		         break;
		   	case 8:
		        day = "Sep";
		        break;
		    case 9:
		   	    day = "Oct";
		   	    break;
		    case 10:
		  	     day = "Nov";
		   	case 11:
		    	 day = "Dec";
		         break;
		            		     
       }
            	   var x1=x.getDate() + "-" + day + "-" + x.getFullYear();
            	   x1 = x1 + "   " + x.getHours( )+ ":" + x.getMinutes() + ":" + x.getSeconds();
            	   document.getElementById('dateTime').style.fontSize='15px';
            	   document.getElementById('dateTime').style.color='#111111';
            	   document.getElementById('dateTime').innerHTML = x1;

            	   tt=display_DateTime();
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

//added by krishnan nema on 23/10/2018
function onModifyAccepted()
{
	//alert("hello");

	document.getElementById("buttonModifyAccepted").style.display = "none";

	var count=0;
	 
	var concatenateChkBoxVal="";
	 
	var cbs =document.getElementsByName('chkSamplePatient');
	
	 
	for(var i=0; i < cbs.length; i++) {
	if(cbs[i].checked)
    {
    		
    	count++;	
		var id=cbs[i].id;

		//var indexWithSubIndex=id.replace('chkbox', '');
		var indexWithSubIndex=id.substring(0, 3);
    	//var index=indexWithSubIndex.split(".")[0];
		//var subIndex=indexWithSubIndex.split(".")[1];
		
		
		/* if(document.getElementById(indexWithSubIndex+"accepted").value=="1"&&document.getElementById(index+".dailyNO").value=="")
	 	{
	 		 
	 		 
		     	alert("Enter Lab No"); 
	 		document.getElementById(indexWithSubIndex+"dailyNO").focus();
	 		return false;
	 	} */
    	
    	 }
   
      }
	
	//alert("stop");return false;
	
	 if(count==0)
   	{
   	alert("please select a Atleast One record");
   	 
    	
   	return false;
   	}
	
	 var nodes = document.getElementById("dcalc").getElementsByTagName('*');
	 //alert("nodes"+nodes);
	 for(var i = 0; i < nodes.length; i++)
	 {
		 //alert("nodes"+nodes[i].value);
	      nodes[i].disabled = false;
	 }
	 
	//document.getElementsByName('showStatus')[0].value='0';
	 
	//Validations for CR NO and Combo should be done here
	document.getElementsByName('hmode')[0].value="MODIFYACCEPTED";
	document.forms[0].submit(); 
	
   return true; 
	

   
}

/* START :Added by krishnan nema on 26/11/2018 */

	  
	


function alternate(id){
		var table = document.getElementById(id);   
		var rows = table.getElementsByTagName("tr");   
	    for(i = 0; i < rows.length; i++){           
	  //manipulate rows 
	      if(i % 2 == 0){ 
	        rows[i].className = "even"; 
	      }else{ 
	        rows[i].className = "odd"; 
	      }       
	    } 
	  
	}

function changeRowColor(tableId, obj1, objIndex){
	var tid = "table"+tableId;
	var table = document.getElementById(tid);  
	var rows = table.getElementsByTagName("tr"); 
	if(obj1.checked){
		rows[0].className = "highlight";
	}else{
		rows[0].className = "transparent";
	}
}

function changeRowColorAccepted(tableId, obj1, objIndex){
	//alert("accepted");
	var tid = "tableAccepted"+tableId;
	var table = document.getElementById(tid);  
	var rows = table.getElementsByTagName("tr"); 
	if(obj1.checked){
		rows[0].className = "highlight";
	}else{
		rows[0].className = "transparent";
	}
}
 
</script>
 <style> 
 .odd{background-color: #ffffff;} 
  .even{background-color: #ccf5ff;} 
  .transparent{background-color: #ffffff;} 
  .highlight{background-color: #ffcccc;}
  #disable:hover{
  	background-color: yellow;
  }
</style> 
 <!-- END :Added by krishnan nema on 26/11/2018  -->
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
#colorCycle { background-color:#87CEFA;
border: 1px solid #1277b5;
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
0% {background-color:#87CEFA;}
25% {background-color:#87CEFA;}
50% {background-color:#87CEFA;}
75% {background-color:#87CEFA;}
}

@-webkit-keyframes homeCycle
{
0% {background-color:#87CEFA;}
25% {background-color:#87CEFA;}
50% {background-color:#87CEFA;}
75% {background-color:#87CEFA;}
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
String strdivdob="\"\""; 

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
	 		
<body onload="callonload();alternate('table3');">
<html:form   action="/sampleAcceptance" >
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
	  	    
	  
 
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="SampleAcceptanceFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
		
		<his:TitleTag name="Sample Acceptance" >
		
		
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
    	  
  			<logic:equal name="SampleAcceptanceFB" property="showStatus" value="0">
            <div class="menu" style="  position: relative;"> 
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			
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
					 	
						 				
						 				  <span class="custom-dropdown small">
						 				<html:select name="SampleAcceptanceFB" property="machineCodee" tabindex="1" onchange="showSearchDiv(this)">					
											<bean:define id="patPakList" type="java.util.List" name="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>"></bean:define>
										
										<%if(patPakList.size()>1){ %>
										<html:option value="-1">Select Value</html:option>	
										<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"  />								
										<%} else{%>
									
										<html:option value="-1">Select Value</html:option>	
																	
										<%} %>
										</html:select> 
										</span>
										
						
					 			
					 	<%-- 		
					 			
					 				 <html:select name="SampleAcceptanceFB" property="machineCodee" tabindex="1" onchange="showSearchDiv(this)">	
					 				 <html:option value="-1">Select Value</html:option>					
											<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"  />
										</html:select>  
									 --%>
									 </div>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfonthead" width="25%"></td>
				 			 </tr>	
				 			 
			     <tr>
			        <td width="25%"  class="tdfont">
			           <div align="right">
			               <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabType"/>&nbsp;
						   </font>
				     </div>
			      </td>
			      <td width="25%"   class="tdfont">
			        <logic:present name="<%=InvestigationConfig.SAMPLE_ACCEPTANCE_LAB_COMBO%>">
			           <div align="left" >
			      <span class="color-picker"></span>
			      <span class="custom-dropdown small">
			      
				              <html:select   name="SampleAcceptanceFB" property="labCode"    tabindex="1"  >
				       			<html:option value="%">ALL</html:option>
				 	   			<html:options collection="<%=InvestigationConfig.SAMPLE_ACCEPTANCE_LAB_COMBO%>" property="value" labelProperty="label"/>
				      		 </html:select>
				     </span>
				      </div>
				   </logic:present>
			     </td>
			     <td width="25%" class="tdfont">
			    
			      <div align="right">
			               <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Packing List&nbsp;
						   </font>
				     </div>
				     
			      </td>
 		
			      <td width="25%" class="tdfont">
 
			       
			       <html:radio name="SampleAcceptanceFB"   tabindex="1" property="acceptance" value="1"  ></html:radio>
						
						Not Accepted
						
						<html:radio name="SampleAcceptanceFB" tabindex="1" property="acceptance" value="2" ></html:radio>
						
						Accepted
			       
			       
			   
			     </td>
			    
			    
			     </tr>
			   <tr>            
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		
			</td>
		   	<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl%>'align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 		
			</td>   
 		        
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>
					</font>
        		</div>
			 </td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>   
			  </tr>
			  <tr>
			  	<td class="tdfont" width="25%">
			  			<div  align="right">
			  			CR No.&nbsp;
			  			</div>
			  	</td>
			  	
			  	<td class="tdfont" width="25%">
			  	<%
										      UserVO uservo11=ControllerUTIL.getUserVO(request);
										    Date todayDateobj = new Date();
								 			   SimpleDateFormat dateob = new SimpleDateFormat("yy");
												String strDate= dateob.format(todayDateobj);
										      String hospitalCode=uservo11.getHospitalCode();
										      String val=uservo11.getHospitalCode()+strDate;
										    //  val = "";
										      %>   
			  		<input type="text" id="textBoxCss" name="searchCrNo" maxlength="20" size="20"
								onkeypress="return validateNumeric(event,this)" value="<%=val %>"
								tabindex="1">
			  	</td>
			  	<td class="tdfont" width="25%">
			  	</td>
			  	<td class="tdfont" width="25%">
			  	</td>
			  	
			  </tr>
			 <tr>
			   <td  align="center" colspan="4" width="25%">
				  <left>
				  <div align="right" id="centerer">
				 	 <a   id="button" class="cssButton"   style="cursor: pointer;text-decoration: none" onkeypress="if(event.keyCode==13) getDetail()   " onclick="getDetail()" tabindex="1">GO</a>
				 <a   id="button" class="cssButton"   style="cursor: pointer;text-decoration: none" onkeypress="if(event.keyCode==13) cancelFunc()   " onclick="cancelFunc()" tabindex="1">CANCEL</a>
				
				 </div>
				 </left>
			  </td>
			 </tr>  
			     </table>
			     </div>
			     </logic:equal>
     <%boolean flag=false; %>
  	   	<%  
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((SampleAcceptanceFB)request.getAttribute("SampleAcceptanceFB")).getCurrentPage());
					//fbPage.setObjArrName(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO);
					fbPage.setAppendInTitle("List ");
			
			
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 %>
			<his:PaginationTag name="fbPagination"></his:PaginationTag>
				 <logic:equal name="SampleAcceptanceFB" property="showStatus" value="0">
			<logic:present name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO %>">
			<% flag=true; %>
			 
			<table  id="table2" style="  width: 100% ;" border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB"  >
				<tr>
					<td width="10%" align="left">	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
						 <bean:message key="select"/> </font>
	                  </b>
					</td>
					<td width="30%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 <bean:message key="PackingListNo"/> </font></b>
					</td>
					<td width="30%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="GenerationDateTime"/></font></b>
					</td>
					 <td width="30%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="LaboratoryName"/></font></b>
					 </td>
				</tr>
			</table>
			</logic:present>
			   <logic:empty name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO %>">
				  <center>
				   <font id="colorCycl"  size="4">
				      No Record Found  
				    </center>
				  </left>
			  </logic:empty>
			<logic:present name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO %>">
			<table  id="table3"  style="  width: 100%">
					<%
					Map<String,List<SampleAcceptanceVO>> _mp=(Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO);
						 int i,size=0;
						
						 Iterator itr=_mp.keySet().iterator();
						while(itr.hasNext())
						{
					boolean firstTimeTravesal=true;
					boolean samePackingListNo=false;
						String packingListNo=(String)itr.next();
						List<SampleAcceptanceVO> lstSampleAcceptanceVO=_mp.get(packingListNo);
					 		if(lstSampleAcceptanceVO!=null && lstSampleAcceptanceVO.size()>0 )
					 			size=lstSampleAcceptanceVO.size();
					 		if(size>1)
					 			samePackingListNo=true;
					 		for(int k=0;k<size;k++)
					 		{
					 			
					 			if(firstTimeTravesal)
					 			{
								SampleAcceptanceVO voSam=lstSampleAcceptanceVO.get(k);
									String chkVal= voSam.getLabCode()+"#"+voSam.getPackingListNO()+"#"+voSam.getTestCode()+"#"+voSam.getShortSampleName();   %>
						 		<tr>
										<td width="10%" align="left">
											<input type="radio" name="chkRadioSamplePatient" value='<%=chkVal%>' onclick="displaySamplePatDetails()">
										</td>
										<td width="30%" align="left">
										 <div align="left">
										 	<%=voSam.getPackingListNO() %> 
										 </div>
								  		</td>
								  		<td width="30%" align="left">
								  		<div align="left">
								  		<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
										 	<%=voSam.getPackingListDate() %></font>
										 </div>
								  		</td>
							  		   <td width="30%" align="left">
								  		<div align="left">
								  		<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
										 	<%=voSam.getLabName() %></font>
										 </div>
								  		</td>
									</tr>
								 <%}
					 			 //logic to hide the vo for same packing list no
					 			 if(samePackingListNo)firstTimeTravesal=false;  // making second iteration false; 
					 			%>
							<%  } 
					 	} %>
				</table>
				 </logic:present>
			 </logic:equal>
   <his:statusTransactionInProcess>
	  </his:statusTransactionInProcess>
	  
	   <!--    ****************************to display the NOT accepted samples ***************************************************** -->
	  <!--  <style>
  				#pageHeader0{
  					position: fixed;
				 	}
				 #pageHeader1{
				 	position: fixed;
				 	width:100%;
				}
			</style> -->
	   
		  <logic:equal name="SampleAcceptanceFB" property="hmode" value="SHOWPATDETAILS">
		  <logic:equal name="SampleAcceptanceFB" property="acceptance" value="1">
       <logic:present name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO %>">
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
 	
	 		<table style=" width: 100%">
	 			<tr>
	 			              <td class="tdfonthead" >
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
					 				<html:hidden name="SampleAcceptanceFB" property="acceptance" />
					 			</td>
					 			<td class="tdfonthead" >
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
					 			 
					 			 <td class="tdfonthead" >
					 			<div align="left">
					 			 <font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="packListDate"/> 
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
 		 
 		<his:SubTitleTag name="Sample Details">
  			</his:SubTitleTag>
  		
  			<div id="dcalc">
  			
			<table style=" width: 100%" >
	 			<tr bgcolor="#BFBFBF" >
	 			              <td width="5%" >
	 			              <%String chkBoxx="allSelected(this,"+size+")"; %>
					 		 		<div align="left" >
					 		 		<input type="checkbox" id="selectAllCheckbox" onclick="<%=chkBoxx%>" />
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
					 			  
					 		
					 			 
					 			<%--  <td width="12%" class="tdfonthead" >
					 			<div align="left">
					 					 <bean:message key="labNo"/>
					 				</div>
					 			</td>
					 			 
					 			<td width="12%" class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="accepted"/>
					 				</div>
					 			</td> --%>
					 		<!-- 	  <td width="12%" class="tdfonthead" >
					 			<div align="left">
					 				Machine No
					 				</div>
					 			</td> -->		
                          <%--         <td width="12%" "  class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="rejectionAction"/>
					 				</div>
					 			</td>				 			 
					 			 
					 			 <td width="12%"    class="tdfonthead" >
					 			<div align="left">
					 				<bean:message key="RejectionReason"/>
					 				</div>
					 			</td> --%>
				 </tr> 
				 </table>
				 
				 <table width="92%"  align="right"   bgcolor="#F8F8FF" style="color:red;">
				 			
				 			<tr>
				 			<td width="5%"> </td>
				 		
				 			<td width="20%"><b><u><bean:message key="testName"/></u></b> </td>
				 				<td width="20%"><b><u> </u></b></td>
				 			<td width="15%"><b><u><bean:message key="accepted"/></u></b></td>
				 		<td width="10%"><b><u><bean:message key="rejectionAction"/></u></b></td>
				 		<td width="10%"><b><u><bean:message key="RejectionReason"/></u></b></td>
				 	<td width="10%"><b><input type="checkbox" checked="checked" id="isselectallmachines" title="Tick for selecting all machines at once and Untick for selecting machine one by one"><u><bean:message key="machine"/></u></b>
				 	
				 	</td>
				 			
				 			</tr>
				 			
				 			
				 			
				 			</table>
				 			
	 		<%
					Map<String,List<SampleAcceptanceVO>> _mpp=(Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO);
					boolean sameSampleNO=false;
						 
					String strCombo=(String)session.getAttribute(InvestigationConfig.TEST_REJECTION_REASON_COMBO);
					
					String testMachineCombo=(String)session.getAttribute(InvestigationConfig.LIST_MACHINE_COMBO);
					String testMachineString=(String)session.getAttribute(InvestigationConfig.LIST_MACHINE_STRING);
					String[] splitTestMachineString=null;
					if(testMachineString!=null && testMachineString.equals("")==false)
					splitTestMachineString=testMachineString.split("@");
					else
						;
					
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
					 			System.out.println("machine id"+voSampleAccCollection.getMachineCode());
								//For parent the value of requistion DNo is kept as 0. This will be used to distinguish parent from child					 			
					 			String chkVal=voSampleAccCollection.getPackingListNO()+"#"+voSampleAccCollection.getSampleNo()+"#"+voSampleAccCollection.getTestCode()+"#"+voSampleAccCollection.getTestName()+"#"+voSampleAccCollection.getLabName()+"#" + "0";
					 			String subChkVal=voSampleAccCollection.getPackingListNO()+"#"+voSampleAccCollection.getSampleNo()+"#"+voSampleAccCollection.getTestCode()+"#"+voSampleAccCollection.getTestName()+"#"+voSampleAccCollection.getLabName()+"#" + voSampleAccCollection.getRequisitionDNo()+"#"+voSampleAccCollection.getTempSampleNo()+"#"+voSampleAccCollection.getLabCode()+"#"+voSampleAccCollection.getLabNoConfiguration()+"#"+voSampleAccCollection.getPatType()+"#"+voSampleAccCollection.getPatCRNo()+"#"+voSampleAccCollection.getPatName()+"#"+voSampleAccCollection.getPatAge()+"#"+voSampleAccCollection.getPatGender()+"#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getShortSampleName()+"#"+voSampleAccCollection.getIslabnoconfig()+"#"+voSampleAccCollection.getGroupCode()+"#"; 
					 			String labNO=voSampleAccCollection.getLabNoConfiguration();
					 			String defaultMachineId="-1";
					 			String testWiseMachineCombo="";
					 			System.out.println("crno = "+voSampleAccCollection.getPatCRNo()+", Patname = "+voSampleAccCollection.getPatName());
					 			if(splitTestMachineString!=null)
					 			 for(int kk=0;kk<splitTestMachineString.length;kk++)
					 			{
					 				String testcodes=splitTestMachineString[kk].split("#")[2];
				 					String[] testcode=testcodes.split("\\$\\$");
				 					
					 				for(int k1=0;k1<testcode.length;k1++)
					 				{
					 					
					 				
					 				if(testcode[k1].equals(voSampleAccCollection.getTestCode()))
					 				{
					 					
					 					if(voSampleAccCollection.getMachineCode()==null || voSampleAccCollection.getMachineCode().equals("-1") )
					 					defaultMachineId=splitTestMachineString[kk].split("#")[0];
					 					else
					 						defaultMachineId=voSampleAccCollection.getMachineCode();
					 					
					 					int idPlace=testMachineCombo.lastIndexOf(defaultMachineId);
					 					
					 					String part1=testMachineCombo.substring(0, idPlace+7);
					 					part1=part1+" selected ";
					 					String part2=testMachineCombo.substring(idPlace+7, testMachineCombo.length());
					 					
					 					testWiseMachineCombo=part1.concat(part2);
					 					
					 				}
					 				}	
					 				
					 			} 
					 			 if(testWiseMachineCombo.equals(""))
					 				testWiseMachineCombo=testMachineCombo;
					 		
					 			
					 			
					 			
					 			
					 			
					 			
					 			int lstSize=0;
					 			lstSize=lstSampleAcceptanceVO.size();
					 			//labNO="1";
					 			if(firstTimeTravesa)
					 			{
											%>
											<table width ="100%" >
				 			<tr bgcolor="#bfbfbf">
				 			    	  <td   width="5%"  align="left">
				 			    <div align="left"   > 
                            <b>	 <%String chkBox="onClickCheck(this,"+size+","+inc+")"; %></b>
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
								 	<input type="text" style="text-align: right" class="textBoxCss" id="<%=inc %>.dailyNO" name="labNoConfiguratio"  maxlength="20" size="15"  onkeypress=""  onkeyup="return <%=strDaily %>"  onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />
								 	 </font>
								 	</div>
					 			</td>
					 			<%}
					 			else if(labNO.equals(InvestigationConfig.SAME_AS_SAMPLE_LAB))
					 			{
					 			%> 
					 			<td width="15%" >
								  <div align="left">
								 <input type="text" style="text-align: right" class="textBoxCss" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguratio"  maxlength="20" size="15" value="<%=voSampleAccCollection.getTempSampleNo() %>"  onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />								 			
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
					 				<b>Receive Status</b>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 			<%String StrRecv="onChaneRecieve(this,"+size+","+inc+" )"; %>
								<span class="custom-dropdown small">
								 <select id="<%=inc %>.recieved" name="recieve"  onChange="<%=StrRecv %>"   style="width:100;" tabindex="1">
									<option value="1">Yes</option>
									<option value="0">No</option>
								 </select>
								 </span>
					 				</div>
					 			</td>
					 		
					 				
					 			
					 			
					 			
						        <%-- <td width="12%" >
								         <div align="left">
								             <%String strACC="onChaneAccepted(this,"+size+","+inc+" )"; %>
									        
									        <span class="custom-dropdown small">
									         <select name="accepte" id="<%=inc %>.accepted"   onchange="<%=strACC %>" style="width:100;" tabindex="1">
												<option value="1">Yes</option>
												<option value="0">No</option>
											 </select>
											 </span>
									     </div>
						       </td>  
								<td width="12%"   >
								         <div align="left">
								             <%String strRejActn="onChaneRejActn(this,"+size+","+inc+" )"; %>
									       <span class="custom-dropdown small">
									        <select id="<%=inc %>.rejectnActn" name="rejectionActio" disabled="true"  onchange="<%=strRejActn %>" style="width:100;" tabindex="1">
												<option value="1">Cancelled</option>
												<option value="2">Rescheduled</option>
											 </select>
											 </span>
									     </div>
						       </td>   
						        <td>
								 		   <div align="left">
									 		   <%String strRejResn="onChaneRejResn(this,"+size+","+inc+" )"; %>
									 		   <span class="custom-dropdown small">
									 		   <select id="<%=inc %>.rejectnRsn" disabled="true" name='rejectionReaso' onchange="<%=strRejResn %>" tabindex='1'>
							 					<%=strCombo%>
							 			         </select>
							 			         </span>
						 			      </div>
								</td> --%>
				 			</tr>
				 			
				 			</table>
				 			
				 			<%if(testTable)
				 				{%>
				 			<%-- <table width="92%"  align="right"   bgcolor="#F8F8FF" style="color:red;">
				 			
				 			<tr>
				 			<td width="5%"> </td>
				 		
				 			<td width="20%"><b><u><bean:message key="testName"/></u></b> </td>
				 				<td width="20%"><b><u> </u></b></td>
				 			<td width="15%"><b><u><bean:message key="accepted"/></u></b></td>
				 		<td width="10%"><b><u><bean:message key="rejectionAction"/></u></b></td>
				 		<td width="10%"><b><u><bean:message key="RejectionReason"/></u></b></td>
				 	<td width="10%"><b><input type="checkbox" checked="checked" id="isselectallmachines" title="Tick for selecting all machines at once and Untick for selecting machine one by one"><u><bean:message key="machine"/></u></b>
				 	
				 	</td>
				 			
				 			</tr>
				 			
				 			
				 			
				 			</table> --%>
				 			
				 		<% testTable=false;} %>
				 			 <%}
					 			 //logic to hide the vo for same packing list no
					 			 if(sameSampleNO)firstTimeTravesa=false;  // making second iteration false; 
					 			%>
					 			
					 			
					 			<table id = "table<%=inc%>.<%=k%>" style=" width: 92%" align="right"  bgcolor="<%=colorsDiv[k]%>">
					 			
				 			 <tr  id="disable">
				 			    
				 			    	   <td   width="5%"   align="left">
				 			          <div align="left"   > 		
					 				 <%String setChkBox="onCheckSetValues(this,"+k+")";
					 				 String strId= inc + "." +k;  %>
					 				<input   type="checkbox" class="jpCheckbox" id="<%=inc%>.<%=k%>chkbox" name="chkSamplePatient"       onclick="<%=setChkBox %>;" value='<%=subChkVal%>'  > 
				 			    	</div>	 			   
				 			    	 </td> 
				 			     <td width="20%"  id="colorCycl"  >
					 				<div align="left"  >
					 				  <%=voSampleAccCollection.getTestName() %>  
					 				  <input type="hidden"   value="<%=voSampleAccCollection.getRequisitionDNo() %>" name="tempReqDNo"/>
					 				  <input type="hidden"   value="<%=voSampleAccCollection.getPriorityCode() %>" name="priorityCode"/>
					 				    <input type="hidden"   value="<%=voSampleAccCollection.getCollDate() %>" name="collDate"/>
					 				  
					 				  
					 				  
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
								 	  <input type="text" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguration"  maxlength="20" size="15"   onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1" />
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
									      <select id="<%=inc%>.<%=k%>accepted" name="accepted" class="<%= "status#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getGroupCode() %>" onchange="<%=strSubAcc %>" style="width:100;" tabindex="1">
												<option value="1"><bean:message key="yes"/> </option>
												<option value="0"><bean:message key="no"/> </option>
											 </select>
											 </span>
									     </div>
						       </td>  
								<td width="10%"  >
								         <div align="left" >
									    <span class="custom-dropdown small">
									    <%String strSubRejActn="onChaneSubRejActn(this,"+inc+","+k+")"; %>
									     <select   class="<%= "action#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getGroupCode() %>"  id="<%=inc %>.<%=k%>rejectnActn" name="rejectionAction"  onchange="<%=strSubRejActn %>" disabled="disabled" style="width:100;" tabindex="1">
												<option value="-1">Select value</option>
												<option value="1"><bean:message key="cancelled"/> </option>
												<%-- <option value="2"><bean:message key="rescheduled"/> </option> --%>
											 </select>
											 </span>
									     </div>
						       </td>   
				 		   <td width="10%">
				 		   <div align="left" >
		 			   <span class="custom-dropdown small">
		 			    <%String strSubRejReason="onChaneSubRejReason(this,"+inc+","+k+")"; %>
		 			     <select  class="<%= "reason#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getGroupCode() %>" id="<%=inc %>.<%=k%>rejectnRsn"  onchange="<%=strSubRejReason %>"  disabled="disabled" name='rejectionReason' tabindex='1'>
		 				<option value="-1">Select value</option>
		 					<%=strCombo%>
		 			     </select>
		 			     </span>
		 			     </div>
				 		</td>
				 		
				 		
				 	  <%-- <td width="10%">
				 		   <div align="left" >
		 			   <span class="custom-dropdown small">
		 			   <select id="<%=inc %>.<%=k%>machine" name='testBasedMachine' tabindex='1'>
		 					<%=testWiseMachineCombo%>
		 			     </select>
		 			     </span>
		 			     </div>
				 		</td> 
				 		 --%>
				 	 <%--  <td class="tdfonthead" colspan="10" width="5%">
				 		   <div align="left" >
		 			   <span class="custom-dropdown small">
		 			   <select id="<%=k%>machine" name='testBasedMachine' tabindex='1'>
		 					<%=testWiseMachineCombo%>
		 			     </select>
		 			     </span>
		 			     </div>
				 		</td>   --%>
				 		
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
						</logic:present>		
               </logic:equal>
               </logic:equal>
               
               
            <!--    ****************************to display the accepted samples ******************************************************-->
               
         
         
           <logic:equal name="SampleAcceptanceFB" property="hmode" value="SHOWPATDETAILS">
            <logic:equal name="SampleAcceptanceFB" property="acceptance" value="2">
       <logic:present name="<%=InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO %>">
			 		 		<%
	 		Map<String,List<SampleAcceptanceVO>> mpSamp = (Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO);
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
	 			              <td class="tdfonthead" >
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
					 				<html:hidden name="SampleAcceptanceFB" property="acceptance" />
					 			</td>
					 			<td class="tdfonthead" >
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
					 			 
					 			 <td class="tdfonthead" >
					 			<div align="left">
					 			 <font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="packListDate"/>	Packing List date
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
 		<his:SubTitleTag name="Sample Details"></
  			</his:SubTitleTag>
  			<div id="dcalc">
	 		<table style=" width: 100%" >
	 			<tr bgcolor="#BFBFBF" >
	 			              <td width="5%" >
					 		 		<div align="left" >
					 		 		<input type="checkbox" id="selectAllCheckbox" onclick="allSelectedReject()" />
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
					 			  
					 		
					 			 
					 			<%--  <td width="12%" class="tdfonthead" >
					 			<div align="left">
					 					 <bean:message key="labNo"/>
					 				</div>
					 			</td>
					 			 
					 			<td width="12%" class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="accepted"/>
					 				</div>
					 			</td> --%>
					 		<!-- 	  <td width="12%" class="tdfonthead" >
					 			<div align="left">
					 				Machine No
					 				</div>
					 			</td> -->		
                          <%--         <td width="12%" "  class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="rejectionAction"/>
					 				</div>
					 			</td>				 			 
					 			 
					 			 <td width="12%"    class="tdfonthead" >
					 			<div align="left">
					 				<bean:message key="RejectionReason"/>
					 				</div>
					 			</td> --%>
				 </tr> 
				 </table>
				 
				 <table width="92%"  align="right"   bgcolor="#F8F8FF" style="color:red;">
				 			
				 			<tr>
				 			<td width="5%"> </td>
				 		
				 			<td width="20%"><b><u><bean:message key="testName"/></u></b> </td>
				 				<td width="20%"><b><u> </u></b></td>
				 			<td width="15%"><b><u></u></b></td>
				 		<td width="15%"><b><u><bean:message key="rejectionAction"/></u></b></td>
				 		<td width="15%"><b><u><bean:message key="RejectionReason"/></u></b></td>
				 	<td width="10%"><b><input type="checkbox" checked="checked" id="isselectallmachines" title="Tick for selecting all machines at once and Untick for selecting machine one by one"><u><bean:message key="machine"/></u></b>
				 	
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
					 			//String subChkVal=voSampleAccCollection.getPackingListNO()+"#"+voSampleAccCollection.getSampleNo()+"#"+voSampleAccCollection.getTestCode()+"#"+voSampleAccCollection.getTestName()+"#"+voSampleAccCollection.getLabName()+"#" + voSampleAccCollection.getRequisitionDNo()+"#"+voSampleAccCollection.getTempSampleNo()+"#"+voSampleAccCollection.getLabCode()+"#"+voSampleAccCollection.getLabNoConfiguration()+"#"+voSampleAccCollection.getPatType()+"#"+voSampleAccCollection.getPatCRNo()+"#"+voSampleAccCollection.getPatName()+"#"+voSampleAccCollection.getPatAge()+"#"+voSampleAccCollection.getPatGender()+"#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getShortSampleName()+"#"+voSampleAccCollection.getIslabnoconfig()+"#"; 
					 			String subChkVal=voSampleAccCollection.getPackingListNO()+"#"+voSampleAccCollection.getSampleNo()+"#"+voSampleAccCollection.getTestCode()+"#"+voSampleAccCollection.getTestName()+"#"+voSampleAccCollection.getLabName()+"#" + voSampleAccCollection.getRequisitionDNo()+"#"+voSampleAccCollection.getTempSampleNo()+"#"+voSampleAccCollection.getLabCode()+"#"+voSampleAccCollection.getLabNoConfiguration()+"#"+voSampleAccCollection.getPatType()+"#"+voSampleAccCollection.getPatCRNo()+"#"+voSampleAccCollection.getPatName()+"#"+voSampleAccCollection.getPatAge()+"#"+voSampleAccCollection.getPatGender()+"#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getShortSampleName()+"#"+voSampleAccCollection.getIslabnoconfig()+"#"+voSampleAccCollection.getGroupCode()+"#";
					 			
					 			String labNO=voSampleAccCollection.getLabNoConfiguration();
					 			int lstSize=0;
					 			lstSize=lstSampleAcceptanceVO.size();
					 			//labNO="1";
					 			if(firstTimeTravesa)
					 			{
											%>
											<table width ="100%" >
				 			<tr bgcolor="#bfbfbf">
				 			    	  <td   width="5%"  align="left">
				 			    <div align="left"   > 
<b>				 			    <%String chkBox="onClickCheckReject(this,"+size+","+inc+")"; %></b>
				 			    <input  type="checkbox" class="jpCheckbox" name="chkSamplePat" value='<%=chkVal %>'  onChange="<%=chkBox %>"> </div>
				 			    </td> 
					 			<td  width="15%">
					 				<div align="left"  >
					 				 <bean:message key="sampleNo"/>:&nbsp;&nbsp;
					 				<b> <%=voSampleAccCollection.getTempSampleNo() %> &nbsp;&nbsp; ( <%=voSampleAccCollection.getSampleName() %> )</b>
					 				</div>
					 			</td>
			
					 			
					
					 			
					 			
	
					 			<td width="15%">
								   <!-- <div align="left" >
								 	

								 	</div> -->
								 <%if(labNO.equals(InvestigationConfig.MANUAL_LAB))
					 			{
					 			%>
					 			
								 <div align="left">
								 	<font color=" ">	
								 	<%String strDaily="validateNum(this,event,"+size+","+inc+" )"; %>  
								 	<input type="text" style="text-align: right" class="textBoxCss" id="<%=inc %>.dailyNO" name="labNoConfiguratio"  maxlength="20" size="15"  onkeypress=""  onkeyup="return <%=strDaily %>"   value="<%=voSampleAccCollection.getStrDailyLabNo() %>" onclick="setOldValue(this)" onblur="chkDailyLabNoDuplicacyThroughAjaxModify(this,event);" tabindex="1" />
								 	 </font>
								 	</div>
					 			
					 			<%}
					 			else
					 			{
					 			%> 
					 			
								   <div align="left" id="<%=inc %>.dailyNO">
								 		<html:hidden name="SampleAcceptanceFB" property="labNoConfiguration" />
								 	</div>
					 			
					 			<%} %>
					 			</td>
					 		
					 			<td width="30%" >
					 			<div align="left" >
					 			&nbsp;&nbsp;
					 				<b>Receive Status:&nbsp;&nbsp;
					 		<%if(voSampleAccCollection.getRecieved().equals("1")) {%>
								<u>Yes</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 <%}else{ %>
								 <u>No</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 <%} %>
								 
								 
								 
								 Accepted:&nbsp;&nbsp;
								 <%if(voSampleAccCollection.getAccepted().equals("1")){ %>
								<u>Yes</u>   <input type="hidden"  value="<%=voSampleAccCollection.getAccepted()%>" name="accepted"/>
								<%}else{ %>
								<u>No</u>
								<%} %>
								
								
								
								</b></div>
					 			</td>
					 		
					 				
					 			
					 			
					 			
						        <%-- <td width="12%" >
								         <div align="left">
								             <%String strACC="onChaneAccepted(this,"+size+","+inc+" )"; %>
									        
									        <span class="custom-dropdown small">
									         <select name="accepte" id="<%=inc %>.accepted"   onchange="<%=strACC %>" style="width:100;" tabindex="1">
												<option value="1">Yes</option>
												<option value="0">No</option>
											 </select>
											 </span>
									     </div>
						       </td>  
								<td width="12%"   >
								         <div align="left">
								             <%String strRejActn="onChaneRejActn(this,"+size+","+inc+" )"; %>
									       <span class="custom-dropdown small">
									        <select id="<%=inc %>.rejectnActn" name="rejectionActio" disabled="true"  onchange="<%=strRejActn %>" style="width:100;" tabindex="1">
												<option value="1">Cancelled</option>
												<option value="2">Rescheduled</option>
											 </select>
											 </span>
									     </div>
						       </td>   
						        <td>
								 		   <div align="left">
									 		   <%String strRejResn="onChaneRejResn(this,"+size+","+inc+" )"; %>
									 		   <span class="custom-dropdown small">
									 		   <select id="<%=inc %>.rejectnRsn" disabled="true" name='rejectionReaso' onchange="<%=strRejResn %>" tabindex='1'>
							 					<%=strCombo%>
							 			         </select>
							 			         </span>
						 			      </div>
								</td> --%>
								
								
								
				 			</tr>
				 			
				 			</table>
				 			
				 			<%if(testTable)
				 				{%>
				 			<%-- <table width="92%"  align="right"   bgcolor="#F8F8FF" style="color:red;">
				 			
				 			<tr>
				 			<td width="5%"> </td>
				 		
				 			<td width="20%"><b><u><bean:message key="testName"/></u></b> </td>
				 				<td width="20%"><b><u> </u></b></td>
				 			<td width="15%"><b><u></u></b></td>
				 		<td width="15%"><b><u><bean:message key="rejectionAction"/></u></b></td>
				 		<td width="15%"><b><u><bean:message key="RejectionReason"/></u></b></td>
				 	<td width="10%"><b><input type="checkbox" checked="checked" id="isselectallmachines" title="Tick for selecting all machines at once and Untick for selecting machine one by one"><u><bean:message key="machine"/></u></b>
				 	
				 	</td>
				 			
				 			</tr>
				 			
				 			
				 			
				 			</table> --%>
				 			
				 		<% testTable=false;} %>
				 			 <%}
					 			 //logic to hide the vo for same packing list no
					 			 if(sameSampleNO)firstTimeTravesa=false;  // making second iteration false; 
					 			%>
					 			
					 			<table id = "tableAccepted<%=inc%>.<%=k%>" style=" width: 92%" align="right"  bgcolor="<%=colorsDiv[k]%>">
					 			
				 			 <tr  id="disable">
				 			    
				 			    	   <td   width="5%"   align="left">
				 			          <div align="left"   > 		
					 				 <%String setChkBox="onCheckSetValuesReject(this,"+k+")";
					 				 String strId= inc + "." +k;  %>
					 				<input   type="checkbox" class="jpCheckbox" id="<%=inc%>.<%=k%>chkbox" name="chkSamplePatient"   onchange="<%=setChkBox %>" value='<%=subChkVal%>'  > 
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
			
							<!-- updated by krishnan nema on 02/11/2018 -->
					 			<%-- <td width="20%" >
								 			<div align="left"  >
								 					 
             					 			<%=voSampleAccCollection.getStrDailyLabNo() %>
	    					 				</div>
					 			</td> --%>
					 			
					 			 <%if(labNO.equals(InvestigationConfig.MANUAL_LAB))
					 			{
					 				%>
					 			<td width="20%" >
						  		 	<div align="left"  >
								 	<%-- <%if(lstSize!=1)
								 		{%>
								 	  <input type="text" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguration"  maxlength="20" size="15"   onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" value="<%=voSampleAccCollection.getStrDailyLabNo() %>" tabindex="1" />
		     			  		 	<%} 
		     			  		 	else
		     			  		 	{%>
		     			  		 		<input type="hidden" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" value="<%=labNO%>" readonly="true" name="labNoConfiguration"/>
		     			  		 	<%} %> --%>
		     			  		 	
		     			  		 	<input type="text" style="text-align: right" class="textBoxCssSub" id="<%=inc%>.<%=k%>dailyNO" name="labNoConfiguration"  maxlength="20" size="15"  onclick="setOldValue(this)" onblur="chkDailyLabNoDuplicacyThroughAjaxModify(this,event);" value="<%=voSampleAccCollection.getStrDailyLabNo() %>" tabindex="1" />
		     			  		 	</div>
		     			  		 	
					 			</td>
					 			<%}
					 			else { %>
					 			
					 			<td width="20%" >
								 			<div align="left"  >
								 					 
             					 			 <%=voSampleAccCollection.getStrDailyLabNo() %>
	    					 				</div>
					 			</td>
					 			
					 			
					 			<%} %>
					 		
				 		      <td width="15%">
								         <div align="left">
								       <%--   <%String strSubAcc="onChaneSubAcc(this,"+inc+","+k+")"; %>
									    <span class="custom-dropdown small">
									      <select id="<%=inc%>.<%=k%>accepted" name="accepted"  onchange="<%=strSubAcc %>" style="width:100;" tabindex="1">
												<option value="1"><bean:message key="yes"/> </option>
												<option value="0"><bean:message key="no"/> </option>
											 </select>
											 </span> --%>
									     </div>
						       </td>  
								<td width="15%"  >
								         <div align="left" >
									    <span class="custom-dropdown small">
									    <%String strSubRejActn="onChaneSubRejActn(this,"+inc+","+k+")"; %>
									     <select    class="<%= "action#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getGroupCode() %>" id="<%=inc %>.<%=k%>rejectnActn" name="rejectionAction"  onchange="<%=strSubRejActn %>" style="width:100;" tabindex="1">
												<option value="-1">Select value</option>
												<option value="1"><bean:message key="cancelled"/> </option>
												<%-- <option value="2"><bean:message key="rescheduled"/> </option> --%>
											 </select>
											 </span>
									     </div>
						       </td>   
				 		   <td width="15%">
				 		   <div align="left" >
		 			   <span class="custom-dropdown small">
		 			    <%String strSubRejReason="onChaneSubRejReason(this,"+inc+","+k+")"; %>
		 			     <select class="<%= "reason#"+voSampleAccCollection.getRequisitionNo()+"#"+voSampleAccCollection.getGroupCode() %>" id="<%=inc %>.<%=k%>rejectnRsn"  onchange="<%=strSubRejReason %>"  name='rejectionReason' tabindex='1'>
		 			     <option value="-1">Select value</option>
		 					<%=strCombo%>
		 			     </select>
		 			     </span>
		 			     </div>
				 		</td>
						
						<!-- Added by krishnan nema  23/10/2018-->
							<td width="10%">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="SampleAcceptanceFB" property="testBasedMachine"  tabindex="1" value="<%=voSampleAccCollection.getDefaultmachineCode() %>" onchange="selectallmachines(this)">
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>">
						 	   								<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
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
						</logic:present>		
               </logic:equal>
               </logic:equal>
               
			      </his:ContentTag>
			       <his:ButtonToolBarTag>
				    <his:statusTransactionInProcess>
				    	 <img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				          <a  id="button"          class="cssButton"  style="cursor:pointer;display:none;text-decoration: none" onkeypress="if(event.keyCode==13) onSave();"  tabindex="1" onclick ="onSave();" >SAVE</a>
				          <a  id="buttonModifyAccepted"          class="cssButton"  style="cursor:pointer;display:none;text-decoration: none" onkeypress="if(event.keyCode==13) onModifyAccepted();"  tabindex="1" onclick ="onModifyAccepted();" >MODIFY</a>
				         
				  	      <a  id="button"  class="cssButton"  style="cursor:pointer;text-decoration: none" onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">CANCEL</a>
				  	       <a  id="rejectbutton"  class="cssButton"  style="cursor:pointer;display:none;text-decoration: none" onkeypress="if(event.keyCode==13) onReject();"  tabindex="1" onclick ="onReject();" >REJECT</a>
				  	      <!-- added by krishnan nema on 23/10/2018 -->
				  	      
				  	 <logic:notEmpty name="<%=InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO  %>">
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    </logic:notEmpty>
				    </his:statusTransactionInProcess>
		 	</his:ButtonToolBarTag>
		<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
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
	<html:hidden name="SampleAcceptanceFB" property="oldValue"/>
		<his:status/>
	 	</html:form>
</body>