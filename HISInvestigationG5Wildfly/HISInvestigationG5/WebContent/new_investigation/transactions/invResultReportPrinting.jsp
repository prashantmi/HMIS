<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.nio.file.Files"%>
<%@page import="com.lowagie.text.PageSize"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.lowagie.text.rtf.RtfWriter2"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="com.lowagie.text.Document"%>
<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="new_investigation.vo.InvResultReportPrintingVO"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultReportPrintingFB"%>
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
 <%@page import="java.io.File"%>
<%@page import="java.io.*"%>


 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
 <his:css src="/hisglobal/css/invPopupReport.css" />
 <his:css src="/hisglobal/css/jquery-ui.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
 
 
 
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
<his:javascript src="/hisglobal/js/css-pop-report-inv.js" />
 <his:javascript src="/hisglobal/js/base64.js" />
 <his:javascript src="/hisglobal/js/sprintf.js" />
 
  <his:javascript src="/hisglobal/js/jspdf.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultReportPrintingFB"%>

  
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
 <script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> 
 
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
 <script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />  
<script>
 $(document).ready( function () {
    $('#myTable').DataTable();
} ); 
</script>

 



  <!-- Datatables added by harshita khare  19/02/18-->

<%@page import="new_investigation.InvestigationConfig"%>

<html>



 <script>
  $(function() {
    $( "#popUpDiv" ).resizable();
  });
  </script>
 
  




 <script type="text/javascript">

 function labBased()
 {
	 //added by krishnan nema on 28/09/2018
	 var patTestType = document.getElementsByName("labCode")[0].value.split("#")[1];

	 var allLabFlag = document.getElementsByName("isAllLabPatientBased")[0].value;
	 if(allLabFlag == "1"){

	 }else{
		 document.getElementsByName("patTestType")[0].value = patTestType;
	 }
	 
	 
	 
	 if(!validateTodayOrDate())
 	 {
 	 	 return false;
 	 }
 	if(document.getElementsByName!="-1")
 	{
 	 	
 		document.getElementsByName('onLoadValue')[0].value="ONLOAD";
 		document.getElementsByName('hmode')[0].value="GETDETAILS";
      	document.forms[0].submit();
 	
 	}
 	}
 
 
function demo1(url) {
	
	alert("1");
	var doc = new jsPDF();
	
	var newURL = url;
doc.text(100, 100, url)
	
	alert("confused"+doc.text(100, 100, url));
	//doc.text(20, 30, 'This is client-side Javascript, pumping out a PDF.');
	//doc.addPage();
	//doc.text(20, 20, 'Do you like that?');
	
	// Output as Data URI
	//doc.output('datauri');
	
	doc.output('datauri');
	//document.getElementsByTagName('popUpDiv')[0].innerHTML=doc.output();
  
}
</script>
			


<script type="text/javascript">
        $('a.media').media({width:500, height:400});
    </script>
  
  
<script>

function onClickGO(hospitalCode)
{
	
	for(var i =0; i<document.getElementsByName('dateType').length; i++){
		
		if(document.getElementsByName('dateType')[i].checked){
			var dateType2=document.getElementsByName('dateType')[i].value;
			document.getElementsByName('dateType')[0].value=dateType2;
		}
	}
	  
	    
	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     document.getElementsByName('onLoadValue')[0].value="";
     
     if(!validateTodayOrDate())
    	 {return false;}
     
     
     if(hosCodeLen==3)
		{ 
          if (textLength==5||textLength==13||textLength==0)
	        {
        	  document.getElementsByName("patCRNo")[0].value="";
        	  if(textLength==13)
	           {
	             document.getElementsByName("patCRNo")[0].value=crno;
	            }
        	  
        			  
              
              if(document.getElementsByName("labCode")[0].value=="-1")
    	      {
    	     	alert("Select Lab   Name ... ");
    		    document.getElementsByName("labCode")[0].focus();
    		    return false;
    	      }
	        // testWiseCode
 /* testGroupCode
 fromSampleNo
 fromLabNo
 toLabNo
 toSampleNo */
 var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); showOnLoad
 
 
 if(genTypeValue=="P")
	{
	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
     {
    	alert("Select    Name ... ");
	    document.getElementsByName("tempPatCRNo")[0].focus();
	    return false;
     }  
 
	}
if(genTypeValue=="T")
	{
	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
     {
    	alert("Select Test   Name ... ");
	    document.getElementsByName("testWiseCode")[0].focus();
	    return false;
     }
 
	}
if(genTypeValue=="L")
	{
	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
     {
    	alert("Select From Lab No ... ");
	    document.getElementsByName("fromLabNo")[0].focus();
	    return false;
     }
 
	 if(document.getElementsByName("toLabNo")[0].value=="-1")
     {
    	alert("Select To Lab No ... ");
	    document.getElementsByName("toLabNo")[0].focus();
	    return false;
     }
	}
if(genTypeValue=="S")
	{
	  
	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
     {
    	alert("Select From Sample No ... ");
	    document.getElementsByName("fromSampleNo")[0].focus();
	    return false;
     }

	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
     {
    	alert("Select To Sample No ... ");
	    document.getElementsByName("toSampleNo")[0].focus();
	    return false;
     }
	 
	}  


if(genTypeValue=="TG")
{
	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
     {
    	alert("Select Test Group  Name ... ");
	    document.getElementsByName("testGroupCodeWise")[0].focus();
	    return false;
     }
	 
}  //allPatient

              
        	        document.getElementsByName('hmode')[0].value="GETDETAILS";
        	        document.forms[0].submit();
        	   
             }

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("tempPatCRNo")[0])
	            {
	            document.getElementsByName("tempPatCRNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
			    if (textLength==7||textLength==15||textLength==0)
				 {
			    	 document.getElementsByName("patCRNo")[0].value="";
			    	if(textLength==15)
						 {
						 document.getElementsByName("patCRNo")[0].value=crno;
						 }
					           if(document.getElementsByName("labCode")[0].value=="-1")
					       	{
					       		alert("Select Lab   Name ... ");
					       		 
					       		document.getElementsByName("labCode")[0].focus();
					       		return false;
					      	}
					       	 
					           
					           var genTypeValue=document.getElementsByName('generationType')[0].value;
					       	//alert("onload"+genTypeValue); showOnLoad
					        
					        
					        if(genTypeValue=="P")
					       	{
					       	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
					            {
					           	alert("Select    Name ... ");
					       	    document.getElementsByName("tempPatCRNo")[0].focus();
					       	    return false;
					            }  
					        
					       	}
					       if(genTypeValue=="T")
					       	{
					       	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
					            {
					           	alert("Select Test   Name ... ");
					       	    document.getElementsByName("testWiseCode")[0].focus();
					       	    return false;
					            }
					        
					       	}
					       if(genTypeValue=="L")
					       	{
					       	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
					            {
					           	alert("Select From Lab No ... ");
					       	    document.getElementsByName("fromLabNo")[0].focus();
					       	    return false;
					            }
					        
					       	 if(document.getElementsByName("toLabNo")[0].value=="-1")
					            {
					           	alert("Select To Lab No ... ");
					       	    document.getElementsByName("toLabNo")[0].focus();
					       	    return false;
					            }
					       	}
					       if(genTypeValue=="S")
					       	{
					       	  
					       	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
					            {
					           	alert("Select From Sample No ... ");
					       	    document.getElementsByName("fromSampleNo")[0].focus();
					       	    return false;
					            }

					       	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
					            {
					           	alert("Select To Sample No ... ");
					       	    document.getElementsByName("toSampleNo")[0].focus();
					       	    return false;
					            }
					       	 
					       	}  


					       if(genTypeValue=="TG")
					       {
					       	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
					            {
					           	alert("Select Test Group  Name ... ");
					       	    document.getElementsByName("testGroupCodeWise")[0].focus();
					       	    return false;
					            }
					       	 
					       }  //allPatient
					           
					       	document.getElementsByName('hmode')[0].value="GETDETAILS";
					       	document.forms[0].submit();
					       	   
					     }
					
			     else
			     {     
				   		    alert("InValid CR No");
				     		if(document.getElementsByName("tempPatCRNo")[0]){
				            document.getElementsByName("tempPatCRNo")[0].focus()
				                      }    
			          
			     }
          }
   return true;
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
 


 
function displaySamplePatDetails()
{	
	var count=0;
	var reportType=document.getElementsByName('reportType')[0].value;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	var strAllChkDetail="";
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{


        	//document.getElementsByClassName(rowid).style.color ="green";
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }
  	//}
	  
 
      }
	
	if(count==0)
   	{
   	alert("please select a record");
   	return false;
   	}
	
	var src="/hisglobal/js/Payment.pdf";
	
	
	//demo1();
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	 
	
	 
	strAllChkDetail=strAllChkDetail+"&selectedCheckbox="+document.getElementsByName('selectedCheckbox')[0].value;
	
	var mode="SHOWPATDETAILS";
	
	var url='/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode+strAllChkDetail+'&reportType='+reportType;


	              
 
	 
		
//	alert("final url"+url);
	
	AddRowToTableAddMoreValue(url);
	popup('popUpDiv');
	 
    
	
	//document.forms[0].submit();
 	
	}
  
	
function AddRowToTableAddMoreValue(finalMadCode)
{
	 
	// alert("working fine"+finalMadCode);
	 
	 //var mode = "SHOWPATDETAILS";
	 
	//var url ='/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode
	 
	var nRow=0;
	var tableObj=document.getElementById('addMoreValue');
	var numRows=tableObj.rows.length;
	//alert(document.getElementById("setPdf"));
	if(document.getElementById("setPdf")!=null){
		
document.getElementById("setPdf").remove();
 //document.getElementById("deleteRow").deleteRow(1);
	}
	
	//alert("total length"+numRows);
		nRow=numRows;

		$("#addMoreValue tr").remove(); 

		
	var tabRow=tableObj.insertRow(0);
	tabRow.id=parseInt(0);
	 
	var td1=document.createElement("TD");
 
	td1.innerHTML="<iframe id='setPdf' src='"+ finalMadCode + "' width='100%' height='450px' type='application/pdf'    ></iframe>";   
	td1.className='tdfont';
	td1.colspan="1";
		
	//document.getElementById("addMoreValue").rows[0].innerHTML=td1;
	tabRow.appendChild(td1);  
	 
	 

	//document.forms[0].numberOfRow.value=numRows;
	  }	
	
function ValidateSameCrNo(obj)
{
	
	if(obj.checked)
	{
		if(document.getElementById("nextDiv")==null)
    	{
    		alert("Pdf Is Not Generated");
    	}
    	else
    		{
    		
    	document.getElementById("nextDiv").style.display=""; 
    		}
		
		  
                
	}
  else
     	{
	  document.getElementById('gob').style.display="";
      	document.getElementById('cancel').style.display="";
      	
          }
	 
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	/* if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    		 
			    	alert("please select same cr no");
			    	document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
				}
		}
	} */
 
}
function showPatDetails(k)
{
document.getElementById("showhide"+k).style.display="";
document.getElementById("hide"+k).style.display="";
document.getElementById("show"+k).style.display="none";	
	
}

function hidePatDetails(k)
{
document.getElementById("showhide"+k).style.display="none";
document.getElementById("hide"+k).style.display="none";
document.getElementById("show"+k).style.display="";	
}

function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}



 
  
  
 /*  function checkDate(frmDate,ToDate)
  {
	 
	 // alert(frmDate,ToDate);
	  var flag=true;
	  var calDate=frmDate;
	 
	  var today = new Date();
		 var date=convertDateToStr(today,"dd-Mon-yyyy");
			var Time=convertDateToStr(today,"hh:mm"); 
			  if(calDate>date) 
				  {
				  alert("From Date Will Not Exceed Than SysDate");
				  return false;
				 
				  }
			  if(ToDate>date) 
			  {
			  alert("To Date Will Not Exceed Than SysDate");
			  return false;
			  }
		    
			  return flag;	    
  }    */

  function doPagination(e,p)
  {
  	document.getElementsByName('currentPage')[0].value=p;
  	document.getElementsByName('hmode')[0].value='PAGINATION';
  	document.forms[0].submit();
  }
  
  
   /* setInterval(function() {
	   
	  document.getElementsByName('hmode')[0].value='NEW';
	  document.forms[0].submit();
       
   }, 1000); */
   
   <%
   UserVO uservoRefresh=ControllerUTIL.getUserVO(request);
     String hospitalCodeOnrefresh=uservoRefresh.getHospitalCode();
   
   %>
   
  function refresh()
   {



		var cbs =document.getElementsByName('chkSamplePatient');
		for(var i=0; i < cbs.length; i++) {
		 // if(cbs[i].type == 'checkbox') 
	    //{
	    	
	    	if(cbs[i].checked)
	    	{

	        	var rowid=i+"rowss";

	        	var len= document.getElementsByClassName(rowid).length;
	        	 
	        	 for(var j=0;j<len;j++)
	        	 {
	        	    document.getElementsByClassName(rowid)[j].style.color="green";
	        	}
	   
	    	 }
	  	//}
		  
	 
	      }
	      
	  document.getElementById('selectAllCheckbox').checked=false;
	   
	    $('input:checkbox.jpCheckbox').prop('checked',false);
	    
	    isChecked=false;
	  // document.getElementsByName('hmode')[0].value='NEW';
		//  document.forms[0].submit(); 
	   <%--  onClickGO('<%=hospitalCodeOnrefresh%>'); --%>
   }
  
   
   function pdfDetails(obj)
   {
   	
	   
	   document.getElementsByName('pdfGenerationType')[0].value=obj.value;
	   
	  // alert(document.getElementsByName('pdfGenerationType')[0].value);
	   document.getElementsByName('hmode')[0].value="GETDETAILS";
       document.forms[0].submit();
  
   }

   
   
   
   function getDetails(obj)
   {
   	
   	
   	document.getElementsByName('generationType')[0].value=obj.value;
   	
   	 if(document.getElementsByName("labCode")[0].value=="-1")
        {//testGroupWise
       	alert("Select Lab   Name ... ");

       	document.getElementsByName("patientWise")[0].checked = true;
       	document.getElementsByName("allPatient")[0].checked = false;
       	  document.getElementsByName("testGroupWise")[0].checked = false;
       	  document.getElementsByName("testWise")[0].checked = false;
       	  document.getElementsByName("sampleNoWise")[0].checked = false;
       	  document.getElementsByName("labNoWise")[0].checked = false;
   	    document.getElementsByName("labCode")[0].focus();
   	    return false;
        }
    //alert(document.getElementsByName('generationType')[0].value);
    
   document.getElementsByName('hmode')[0].value='GETTYPEWISEDETAIL';
   	document.forms[0].submit();
   	///document.forms[0].submit();
   	  if(obj.value=="P")
   		{
   		  
   document.getElementById("patientwise").style.display="";	
   document.getElementById("patientwisename").style.display="";	
   document.getElementById("testwise").style.display="none";	
   document.getElementById("labnowise").style.display="none";	
   document.getElementById("labnowise2").style.display="none";	
   document.getElementById("samplenowise").style.display="none";	
   		}
   	if(obj.value=="T")
   		{
   		   
   	document.getElementById("testwise").style.display="";	
   		document.getElementById("patientwise").style.display="none";
   		document.getElementById("patientwisename").style.display="none";	
   	document.getElementById("labnowise").style.display="none";	
   	document.getElementById("labnowise2").style.display="none";	
   	document.getElementById("samplenowise").style.display="none";	
   		}
   	if(obj.value=="L")
   		{
   		 
   	document.getElementById("labnowise").style.display="";	
   	document.getElementById("labnowise2").style.display="";	
   		document.getElementById("patientwise").style.display="none";
   		document.getElementById("patientwisename").style.display="none";
   	document.getElementById("testwise").style.display="none";	
   	document.getElementById("samplenowise").style.display="none";	
   		}
   	if(obj.value=="S")
   		{
   		  
   	document.getElementById("samplenowise").style.display="";	
   		document.getElementById("patientwise").style.display="none";	
   		document.getElementById("patientwisename").style.display="none";
   	document.getElementById("testwise").style.display="none";	
   	document.getElementById("labnowise").style.display="none";	
   	document.getElementById("labnowise2").style.display="none";	
   	
   		}  
   	
   	if(obj.value=="TG")
   	{
   	  
   		document.getElementById("testGrpWise").style.display="";
   document.getElementById("samplenowise").style.display="none";	
   document.getElementById("samplenowise2").style.display="none";	
   document.getElementById("toLabSampleNo").style.display="none";	
   	document.getElementById("patientwise").style.display="none";	
   	document.getElementById("patientwisename").style.display="none";
   document.getElementById("testwise").style.display="none";	
   document.getElementById("labnowise").style.display="none";	
   document.getElementById("labnowise2").style.display="none";	

   	}  
   	
   	
   	if(obj.value=="AP")
   	{
   	  
   		document.getElementById("testGrpWise").style.display="none";
   	document.getElementById("samplenowise").style.display="none";	
   	document.getElementById("samplenowise2").style.display="none";	
   	document.getElementById("toLabSampleNo").style.display="none";	
   	document.getElementById("patientwise").style.display="none";	
   	document.getElementById("patientwisename").style.display="none";
   	document.getElementById("testwise").style.display="none";	
   	document.getElementById("labnowise").style.display="none";	
   	document.getElementById("labnowise2").style.display="none";	

   	 
   	labBased();
   	}
   	
   }

   function checkEntryType()
   {
    var dateType=document.getElementsByName('dateType')[0].value;
    if (dateType=="p"){
    	document.getElementsByName('dateType')[2].checked=true;
    } else {
    	document.getElementsByName('dateType')[1].checked=true;
    }
   	
   	var genTypeValue=document.getElementsByName('generationType')[0].value;
   	//alert("onload"+genTypeValue); showOnLoad
   	 if(genTypeValue=='')
   		 {
   		 document.getElementById("showOnLoad").style.display="";	
   		 document.getElementById("patient").checked = true;
   		 }
   	 
   	 if(genTypeValue=="P")
   		{
   		  
   document.getElementById("patientwise").style.display="";	
   document.getElementById("patientwisename").style.display="";
   document.getElementById("testwise").style.display="none";	
   document.getElementById("labnowise").style.display="none";	
   document.getElementById("labnowise2").style.display="none";	
   document.getElementById("samplenowise").style.display="none";	
   document.getElementById("samplenowise2").style.display="none";	
   document.getElementById("toLabSampleNo").style.display="none";	
   		}
   	if(genTypeValue=="T")
   		{
   		   
   	document.getElementById("testwise").style.display="";	
   		document.getElementById("patientwise").style.display="none";	
   		document.getElementById("patientwisename").style.display="none";
   	document.getElementById("labnowise").style.display="none";	
   	document.getElementById("labnowise2").style.display="none";	
   	document.getElementById("samplenowise").style.display="none";	
   	document.getElementById("samplenowise2").style.display="none";	
   	document.getElementById("toLabSampleNo").style.display="none";	
   		}
   	if(genTypeValue=="L")
   		{
   		  
   	document.getElementById("labnowise").style.display="";	
   	document.getElementById("labnowise2").style.display="";	
   		document.getElementById("patientwise").style.display="none";
   		document.getElementById("patientwisename").style.display="none";
   	document.getElementById("testwise").style.display="none";	
   	document.getElementById("samplenowise").style.display="none";	
   	document.getElementById("samplenowise2").style.display="none";	
   	document.getElementById("toLabSampleNo").style.display="";	
   		}
   	if(genTypeValue=="S")
   		{
   		  
   	document.getElementById("samplenowise").style.display="";	
   	document.getElementById("samplenowise2").style.display="";	
   	document.getElementById("toLabSampleNo").style.display="";	
   		document.getElementById("patientwise").style.display="none";
   		document.getElementById("patientwisename").style.display="none";
   	document.getElementById("testwise").style.display="none";	
   	document.getElementById("labnowise").style.display="none";	
   	document.getElementById("labnowise2").style.display="none";	
   	
   		}  
   	
   	
   	if(genTypeValue=="TG")
   	{
   	  
   		document.getElementById("testGrpWise").style.display="";
   document.getElementById("samplenowise").style.display="none";	
   document.getElementById("samplenowise2").style.display="none";	
   document.getElementById("toLabSampleNo").style.display="none";	
   	document.getElementById("patientwise").style.display="none";
   	document.getElementById("patientwisename").style.display="none";
   document.getElementById("testwise").style.display="none";	
   document.getElementById("labnowise").style.display="none";	
   document.getElementById("labnowise2").style.display="none";	

   	}  //allPatient
   	
   	if(genTypeValue=="AP")
   	{
   	  
   		document.getElementById("testGrpWise").style.display="none";
   	document.getElementById("samplenowise").style.display="none";	
   	document.getElementById("samplenowise2").style.display="none";	
   	document.getElementById("toLabSampleNo").style.display="none";	
   	document.getElementById("patientwise").style.display="none";
   	document.getElementById("patientwisename").style.display="none";
   	document.getElementById("testwise").style.display="none";	
   	document.getElementById("labnowise").style.display="none";	
   	document.getElementById("labnowise2").style.display="none";	
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
        	if(document.getElementById("nextDiv")==null)
        	{
        		alert("Pdf Is Not Generated");
        	}
        	else
        		{
        		
        	document.getElementById("nextDiv").style.display=""; 
        		}
        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').prop('checked',isChecked);
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
    </script>
    <style>
.textBoxCss {
    background: white;
    color: #135d8c;
    width: 180px;
    padding: 4px 10px 4px 15px;
    border-radius: 20px;
    box-shadow: 0 1px 0 #ccc inset;
    transition: 500ms all ease;
    outline: 0;
}

 

</style>
<style>
 
</style>
<body onload="checkEntryType()">
<html:form action="/invResultReportPrinting">
		<html:hidden name="InvResultReportPrintingFB" property="hmode" />
		<html:hidden name="InvResultReportPrintingFB" property="isPatDetailPage" />
		<html:hidden name="InvResultReportPrintingFB" property="selectedCheckbox" />
	 	<html:hidden name="InvResultReportPrintingFB" property="showStatus" />
		<html:hidden name="InvResultReportPrintingFB" property="currentPage" />
		<html:hidden name="InvResultReportPrintingFB" property="patCRNo" />
		<html:hidden name="InvResultReportPrintingFB" property="sysDate" />
	  	  
		<html:hidden name="InvResultReportPrintingFB" property="getSearchType" />
	  	<html:hidden name="InvResultReportPrintingFB" property="generationType"    />
	  	<html:hidden name="InvResultReportPrintingFB" property="onLoadValue"    />
	
		<html:hidden name="InvResultReportPrintingFB" property="resultEntryTemplateValue" />	 
		<html:hidden name="InvResultReportPrintingFB" property="parameterCode" />	     
	   	<html:hidden name="InvResultReportPrintingFB" property="parantParameterCode" />
	    	<html:hidden name="InvResultReportPrintingFB" property="requisitionDNo" />
	     <html:hidden name="InvResultReportPrintingFB" property="resultEntryTemplateValueWithHash" />	
	     <html:hidden name="InvResultReportPrintingFB" property="pdfGenerationType" />
	     <html:hidden name="InvResultReportPrintingFB" property="patTestType" />	
	     <html:hidden name="InvResultReportPrintingFB" property="isAllLabPatientBased" />
	
	  	<html:hidden name="InvResultReportPrintingFB" property="dateType"    />
	  	  	     
	  
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	<logic:equal name="InvResultReportPrintingFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
		<his:TitleTag name="Result Report Printing">
		<%-- 	<his:insertDateTag/> --%>
		
		</his:TitleTag>
		 
		  <%
		    String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
            %>
      <bean:define name="InvResultReportPrintingFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="InvResultReportPrintingFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
    	 <logic:equal name="InvResultReportPrintingFB" property="showStatus" value="0">
    	<his:SubTitleTag name="Details">
  			</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			    
			     <tr>
			    <td class="tdfont" width="25%" colspan="4">
			        <div align="center">
			         <logic:notEqual name="InvResultReportPrintingFB" property="pdfGenerationType" value="1">
			           <input type="radio" name="pdfGenerationType" value="0" onclick="pdfDetails(this)" checked="checked"> <bean:message key="pdfgeneratedlist"/> 
					   <input type="radio" name="pdfGenerationType" value="1" onclick="pdfDetails(this)"> <bean:message key="pdfgeneartioninprocesslist"/> 
		 				</logic:notEqual>
					 				<logic:equal name="InvResultReportPrintingFB" property="pdfGenerationType" value="1">
					 	<input type="radio" name="pdfGenerationType" value="0" onclick="pdfDetails(this)" > <bean:message key="pdfgeneratedlist"/> 
					   <input type="radio" name="pdfGenerationType" value="1" onclick="pdfDetails(this)" checked="checked"> <bean:message key="pdfgeneartioninprocesslist"/> 
					   
					   
				 				</logic:equal>
			         
				     </div>
			      </td>
			     </tr>
			      <tr>    
			     <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_REPORT_PRINTING%>">
			      <div align="left">
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="labCode"   onchange="labBased()" tabindex="1"  >
				       					<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_REPORT_PRINTING%>" property="value" labelProperty="label"/>
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
								<bean:message key="reportType"/>&nbsp;
						 </font>
				     </div>
				     
				     
			      </td>
			      
			      
			      <td width="25%" class="tdfont">
			      
			       <div align="left">
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="reportType"  tabindex="1"  >
				       					<html:option value="0">Result Entry</html:option>
				 	   					<html:option value="1">Department Result Entry</html:option>
		 		      				</html:select>
				      				</span>
				  </div>
				  
				  
			     </td>
			     </tr>
		     <tr>            
 			<td class="tdfont" width="25%">
				
			</td>
			<td class="tdfont" width="25%">
				<input type="radio" name="dateType" value="r" >Requisition Date
        		     &nbsp;&nbsp;&nbsp;
        		     <input type="radio" name="dateType" value="p" >PatientAcceptance Date
			</td>
			<td class="tdfont" width="25%">

			</td>
			<td class="tdfont" width="25%">
				
			</td>
			</tr>
			 <tr> 
			 
			<td class="tdfont" width="25%">
			<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="fromDate" />&nbsp;
				</font>
			</div>
			</td>
			<td class="tdfont" width="25%">
	   		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">
	    		 &nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
	    		</div>
			</td>
			
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="toDate"/>&nbsp;
				</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr>
 		
 		
 		 <tr  >
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="ResultEntryType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" colspan="3">
			      <div align="left"   >                                                                                                           
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="P">
						 
						<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="P">
						<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)"   value="P" />
						</logic:notEqual>
						<bean:message key="PatientWise"/>
						
						<%-- &nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="T">
						<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked"  value="T" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="T">
					<input type="radio" name="testWise" onclick="getDetails(this)"   value="T" />
						</logic:notEqual>
						
						<bean:message key="testWise"/>
	     		 
	     		 		<!-- updated by krishnan nema on 28/09/2018 -->
	     		        <logic:notEqual name="InvResultReportPrintingFB" property="patTestType" value="2">                                                                                       
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  checked="checked" value="S" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  value="S" />
						</logic:notEqual>
						
						
						<bean:message key="sampleNoWise"/>
	     		  		</logic:notEqual>
	     		         
	     		         
	     		        <logic:notEqual name="InvResultReportPrintingFB" property="patTestType" value="2">                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWise"/>
						</logic:notEqual>
						
						<logic:equal name="InvResultReportPrintingFB" property="patTestType" value="2">
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWiseAcc"/>
						</logic:equal>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)" checked="checked" value="TG" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)"  value="TG" />
						</logic:notEqual>
						
						
						<bean:message key="testGrpWise"/>
						
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvResultReportPrintingFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)" checked="checked" value="AP" />
						</logic:equal>
						<logic:notEqual name="InvResultReportPrintingFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)"  value="AP" />
						</logic:notEqual>
						
						
						<bean:message key="allPatient"/> --%>
	     		   </div>  
	     		    
			     </td>
			     
			     </tr>
 		     <tr>
			    <td width="25%" class="tdfont">
			    <div align="right" id="showOnLoad" style="display:none">
			    
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			        <div align="right" >
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <logic:equal name="InvResultReportPrintingFB" property="generationType" value="P">
								 
								<bean:message key="crNO"/>&nbsp;
								
								</logic:equal>
								 <logic:equal name="InvResultReportPrintingFB" property="generationType" value="T">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="testName"/>&nbsp;
								</logic:equal>
								 <!-- updated by krishnan nema on 01/10/2018 -->
								<%--  <logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal> --%>
								 
								  <logic:notEqual name="InvResultReportPrintingFB" property="patTestType" value="2">
								 <logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal>
								</logic:notEqual>
								
								<logic:equal name="InvResultReportPrintingFB" property="patTestType" value="2">
								 <logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromAccessionNo"/>&nbsp;
								</logic:equal>
								</logic:equal>
								 
								 
								 <logic:equal name="InvResultReportPrintingFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromSampleNo"/>&nbsp;
								</logic:equal>
								<logic:equal name="InvResultReportPrintingFB" property="generationType" value="TG">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="tstgrpName"/>&nbsp;
								</logic:equal>
						 </font>
				     </div>
				     
				     
				     
				  
				  
			      </td>
			      <%
			      UserVO uservo=ControllerUTIL.getUserVO(request);
			      Date todayDateobj = new Date();
					SimpleDateFormat dateob = new SimpleDateFormat("yy");
					String strDate= dateob.format(todayDateobj);
			      String hospitalCode=uservo.getHospitalCode();
			      String val=uservo.getHospitalCode()+strDate;
			      %>
			      <td width="25%" class="tdfont">
			      <div align="left" id="patientwise" >                                                                                                           
					 &nbsp;&nbsp;&nbsp; <input type="text" id="textBoxCss" name="tempPatCRNo" value="<%=val%>" maxlength="15" size="20"  onkeypress="return validateNumeric(event,this)" tabindex="1"> 
				   </div>  
				   
				    <logic:present name="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="testwise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="testWiseCode"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				   <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="samplenowise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="fromSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				   <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="labnowise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="fromLabNo"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				    
				    
				     <logic:present name="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="testGrpWise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="testGroupCodeWise"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				   
				  
			     </td>
			     <td width="25%" class="tdfont">
			     
			       <div align="left" id="patientwisename" >                                                                                                           
					 Search Name&nbsp;<input type="text" id="textBoxCss" name="tempPatName"  maxlength="20" size="20"  onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
				   </div>  
				   
				   
				   
			        <div align="right" id="toLabSampleNo"  style="display: none;">
			         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			     				
			     				<!-- updated by krishnan nema on 01/10/2018 -->
			     				<%-- <logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal> --%>
								<logic:notEqual name="InvResultReportPrintingFB" property="patTestType" value="2">
								<logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal>
								</logic:notEqual>
								
								<logic:equal name="InvResultReportPrintingFB" property="patTestType" value="2">
								<logic:equal name="InvResultReportPrintingFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toAccessionNo"/>&nbsp;
								</logic:equal>
								</logic:equal>
								
								
								 <logic:equal name="InvResultReportPrintingFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toSampleNo"/>&nbsp;
								</logic:equal>
								</font>
			      </div>
			      </td>
			      
			      <td width="25%" class="tdfont">
			       <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="labnowise2" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="toLabNo"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				  
				   <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="samplenowise2" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="InvResultReportPrintingFB" property="toSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			   <tr>
			   <td class="tdfont" align="center" colspan="2" width="25%">
			  <div align="right">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')" onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
			   </div>
			   </td>
			  
			  
			   <td class="tdfont" align="center" colspan="2" width="25%">
			  <div align="left">
			 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc()" onclick="cancelFunc()" tabindex="1">
			   </div>
			   </td>
			   
			   
			  
			   
			  
			   </tr>  
			     </table>
	 <logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">	   
     <%boolean flag=false; %>
  	  <%
				//Pagination Logic
				/* PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((InvResultReportPrintingFB)request.getAttribute("InvResultReportPrintingFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=15;
					fbPage.setMaxRecords(maxRecord);  */
				 
				 %>
				 
			<%-- 	  <his:PaginationTag name="fbPagination"></his:PaginationTag>   --%>
	
			 <logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<% flag=true; %>
			</logic:present>
			<logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			
			
			<table   id="myTable"  width="100%"   bgcolor="#EBEBEB" >                          <!--Datatables added by harshita khare  -->
			      <thead>              
				<tr>
					<th width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</th>
					<th width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="crNO"/></font></b>
					</th>
					<th width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patientName"/></font></b>
					</th>
					<th width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="age/gender"/></font></b>
					</th>
					<th width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="departmentunit"/></font></b>
					</th>
					<th width="12%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</th>
					<th width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</th>
					
					<!-- updated by krishnan nema on 28/09/2018 -->
					<logic:notEqual name="InvResultReportPrintingFB" property="patTestType" value="2">
							<th width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampLabNo"/></font></b>
					</th>
					</logic:notEqual>
					
					<logic:equal name="InvResultReportPrintingFB" property="patTestType" value="2">
							<th width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="accessionNowise"/></font></b>
					</th>
					</logic:equal>
					
					<th width="9%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus"/></font></b>
					</th>
				</tr>
				 </thead>
			     <tbody>
			   
			
					<%
					 List<InvResultReportPrintingVO> lstPatVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
			        //int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					//int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
					String grpCode="";
					int countrows=0;
					for(int j=0;j<lstPatVO.size();j++)
				{
					//int i=j-startIndex;
					boolean firstTimeTravesall=true;
				    //if(j<size)
								
					InvResultReportPrintingVO voPat=lstPatVO.get(j);
					//System.out.println("list size ====== " +lstPatVO.size());
					String  chkVal=voPat.getPatPuk()+","+voPat.getRequisitionNo()+","+voPat.getRequisitionDNo()+","+voPat.getGroupCode()+","+voPat.getReportUrl(); 
// 				if(voPat.getGroupCode()!=null)
// 				{   
					 
				 
// 					grpCode+='&';
// 					String[] SplitGrpCode=grpCode.split("&");
// 					int length=SplitGrpCode.length;
// 					if(SplitGrpCode.length>1)
// 					for(int x=0;x<SplitGrpCode.length;x++)
// 					{
//                     if(SplitGrpCode[x].equals(voPat.getRequisitionNo()+voPat.getGroupCode()))
//                     {
// 					 firstTimeTravesall=false;
//                     }
//                     else
//                     {
//                     	 firstTimeTravesall=true;	
//                     }
// 				} 
					 
// 					 grpCode+=voPat.getRequisitionNo()+voPat.getGroupCode(); 
     				 
// 				}
				 

				 if(firstTimeTravesall)
		 			{
				
					 String color="";
					 if(voPat.getReqDtlStatus()!=null && voPat.getReqDtlStatus().equals("14"))
					 {
						color="green"; 
						 
					 }
					 else
						 color="#000000";
					 
					 
				%>   
					<tr id="<%=countrows %>rows" >
						<td width="3%" align="left" >
							<font color="#000000" size="2" 
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
							</font>
						</td>
						<td width="15%" align="left" >
						  
						  <font color='<%=color%>' size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<span class="<%=countrows %>rowss" ><%=voPat.getPatPuk() %></span></font> 
				     		 
				  		</td>
				  		
				  		<td width="15%" align="left"  >
				  		 
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 		<span class="<%=countrows %>rowss" ><%=voPat.getPatName() %></span></font>
						  
				  		
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 		<span class="<%=countrows %>rowss" ><%=voPat.getPatAge()+"/"+voPat.getPatGender()%></span></font>
						  
				  		</td>
				  		 <td  width="15%" align="left">
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<span class="<%=countrows %>rowss" >	<%=voPat.getPatUnitName() %></span></font>
						  
				  		</td>
				  		<td width="12%" align="left">
				  		 
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 		<span class="<%=countrows %>rowss" >	<%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName() %></span></font>
						  
				  		</td>
				  		<td width="11%" align="left">
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 		<span class="<%=countrows %>rowss" ><%=voPat.getPatLabName() %></span></font>
						 
				  		</td>
				  		
				  			<td width="8%" align="left">
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 		<span class="<%=countrows %>rowss" ><%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo() %></span></font>
						  
						   
						  
				  		</td>
				  		
				  		
				  		<td width="9%" align="left">
				  		 
				  		<font color='<%=color%>'  size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<span class="<%=countrows %>rowss" >	<%=voPat.getPatStatus() %></span></font>
						  
						   
						  
				  		</td>
					</tr>
					<%countrows++; } voPat=null;    } System.gc();
					%>
					</tbody>
			</table>
			</logic:notEmpty>
			</logic:present>
			
			
			
			
			 
			  <div id="container">


	 
    <div id="blanket" style="display:none;"></div>
	<div draggable="true" id="popUpDiv" style="display:none;">
	<his:SubTitleTag name="Result Printing Details">
    	
           <img src='/HISInvestigationG5/hisglobal/css/close.png'  onclick="popupClose('popUpDiv')"'> 
 
  			</his:SubTitleTag>
	<table id="addMoreValue" width="100%">
<tr id="deleteRow">
 
</tr>	 
	
	 
	</table>
<left>
    	 
</left>
	</div>	
    <!-- end #mainContent --></div>
			 </logic:equal>
			 
			 

<script type="text/javascript">

function demo1(url) {
	
	alert("2");
	var doc = new jsPDF();
	
	var newURL = url;
	doc.text(100, 100, url)
	
	alert("confused"+url);
	//doc.text(20, 30, 'This is client-side Javascript, pumping out a PDF.');
	//doc.addPage();
	//doc.text(20, 20, 'Do you like that?');
	
	// Output as Data URI
	//doc.output('datauri');
	
	doc.output('datauri');
	//document.getElementsByTagName('popUpDiv')[0].innerHTML=doc.output();
  
}


function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
document.getElementById("divLegends").style.display="none";
}

function showLegends1(){
	  document.getElementById("divLegends1").style.display="block"; 
}
function showLegendsNone1(){
document.getElementById("divLegends1").style.display="none";
}

</script>
			 
			 <%-- <his:ButtonToolBarTag>
				    <logic:notEqual name="InvResultReportPrintingFB" property="pdfGenerationType" value="1">
				    	 <img class="button"   src='<his:path src="/hisglobal/images/PrintResults.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				          </logic:notEqual>
				    <logic:present name="<%=InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO %>">
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    </logic:present>
				    
				    </his:ButtonToolBarTag> --%>
				    
				    
	<his:ButtonToolBarTag>
	 <logic:notEqual name="InvResultReportPrintingFB" property="pdfGenerationType" value="1">
				    	 <img class="button"   src='<his:path src="/hisglobal/images/PrintResults.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				          </logic:notEqual>
				    
	</his:ButtonToolBarTag>
    
				    
				    
				    

	     
	        
	<%--  <his:status/>		 --%>
	 
	 <div id="divLegends1" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="#228B22" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Green color row</div>
						</font></td>
						<td width="80%"><font color="#228B22" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Report Viewed</div>
						</font></td>
					</tr>
					
					<tr>
						<td width="20%"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Black color row</div>
						</font></td>
						<td width="80%"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Report Generated</div>
						</font></td>
					</tr>
					

				</table>
			</his:ContentTag>
		</div>
	 
	 <his:SubTitleTag>
			 
			 <his:name>
				<bean:message key="legends" />
			</his:name>
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"></td>
					<td width="30%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
								src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
								tabindex="1" onclick="showLegends1();"
								onkeypress="if(event.keyCode==13) showLegends1();"> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
								src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
								tabindex="1" onclick="showLegendsNone1();"
								onkeypress="if(event.keyCode==13) showLegendsNone1();">
						</div>
					</td>
				</tr>
			</table>
			 </his:SubTitleTag>    
</html:form>
</body>
</html>