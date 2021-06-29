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
<%@page import="new_investigation.reports.controller.fb.MachineTestReportFB"%>
 
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
 
 
 <!--PDF HTML5 export button Added by Prashant-->
<script src="media/dataTables/pdfmake-0.1.36/pdfmake.min.js" type="text/javascript"></script>
<script src="media/dataTables/pdfmake-0.1.36/vfs_fonts.js" type="text/javascript"></script>

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
    var lCode = document.getElementsByName("labCode")[0].value;	 
	var mCode = document.getElementsByName("machineCode")[0].value;	
	//var tCode = document.getElementsByName("testCode")[0].value;

    if(lCode=="-1")
	{
	alert("select lab");
	return false;
	}
	if(mCode=="-1")
	{
	alert("select machine");
	return false;
	}
	
	/*if(tCode=="-1")
	{
	alert("select test");
	return false;
	}
	
	        document.forms[0].hmode.value = "SHOWRPT";
			
				if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
				{
					document.forms[0].target = "_self";
				}else{
					document.forms[0].target = "_blank";
				} */
			document.forms[0].hmode.value = "SHOWRPT";
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



function onLoadPage()
{

	document.forms[0].strIsFooter.checked = true;
	
}


function getLabBasedMachine() {
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
		
//	var labCode = document.getElementsByName("labCode")[0].value;
  //  alert(labCode);
  	
	 
		document.forms[0].hmode.value="GETMACHINE";
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
		document.getElementById("dateWiseCheck").style.display="";
		document.getElementById("timeWiseCheck").style.display="none";
		}
		else if(dt=="today")
		{
		//alert("t");

		 document.getElementById('todayCheck').checked=true;
		document.getElementById('dateCheck').checked=false;
		document.getElementById("dateWiseCheck").style.display="none";
		document.getElementById("timeWiseCheck").style.display=""; 

			

			
		
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
    if (i<10) {i = "0" + i};
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
	3
     
     
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
<html:form action="/reports/MachineTestReportAction" >
	   
	   <div class="errMsg" id="errMsg"><bean:write name="MachineTestReportFB" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="MachineTestReportFB" property="strNormalMsg"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="MachineTestReportFB" property="strWarningMsg"/></div>
	   
	   <html:hidden name="MachineTestReportFB" property="hmode" />
	    <html:hidden name="MachineTestReportFB" property="sysDate" />
	      <html:hidden name="MachineTestReportFB" property="dateTypeCheck" />
	       <html:hidden name="MachineTestReportFB" property="frmHr" />
	        <html:hidden name="MachineTestReportFB" property="frmMin" />
	         <html:hidden name="MachineTestReportFB" property="toHr" /> 
	         <html:hidden name="MachineTestReportFB" property="toMin" />
	         
	  	 <his:TransactionContainer>
	 
		<his:TitleTag name="Machine Test Report">
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
      <bean:define name="MachineTestReportFB" property="fromDate" id="frDate" type="java.lang.String" />
	   <bean:define name="MachineTestReportFB" property="toDate" id="tDate" type="java.lang.String"/> 
	   
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
    		<input type="radio" name="datetype" value="today"  onclick="setTimeCheck(this)"   id="todayCheck" style=""> <bean:message key="today"/> 
		 <input type="radio" name="datetype" value="datewise" onclick="setDateTime(this)" id="dateCheck" checked><bean:message key="dateWise"/> 
			
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
 	

 
 <tr>
 
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
							<html:select name="MachineTestReportFB" property="labCode" onchange="getLabBasedMachine()" tabindex="1" style="max-width:80%;">
											<html:option value="-1">Select lab</html:option>
											
											<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
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
								 <bean:message key="ParameterMachineCombo"/>&nbsp;
						 </font>
				     </div>
			     
			     
			      </td>
			      <td width="25%" class="tdfont">
			       <logic:present	name="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>">
							<html:select name="MachineTestReportFB" property="machineCode" tabindex="1" style="max-width:80%;">
										<html:option value="-1">Select Machine</html:option>
									
			      						<html:options
											collection="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>"
											property="value" labelProperty="label" />
											
							</html:select>
					</logic:present>

			     <logic:notPresent	name="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>">
							<html:select name="MachineTestReportFB" property="machineCode" tabindex="1" style="max-width:80%;">
										<html:option value="1">Select Machine</html:option>
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