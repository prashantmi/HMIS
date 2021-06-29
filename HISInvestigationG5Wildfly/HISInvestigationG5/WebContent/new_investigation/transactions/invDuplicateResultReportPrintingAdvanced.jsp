<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.InvDuplicateResultReportPrintingVO"%>
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
<%@page
	import="new_investigation.transactions.controller.fb.InvDuplicateResultReportPrintingFB"%>
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
<%--  <his:css src="/hisglobal/css/drop.css" /> --%>
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
<%@page
	import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page
	import="new_investigation.transactions.controller.fb.InvDuplicateResultReportPrintingFB"%>



<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />

<!-- Added by harshita khare -->
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>

<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" /> 
<link href="media/dataTables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" /> -->                              <!--CHECK COLOUR  -->
<link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
<script src="media/bootstrap/js/date-dd-MMM-yyyy.js" type="text/javascript"></script>
<!-- Added by harshita  -->


<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true">
	
</script>
<!-- Added by harshita  -->
<!-- <script>

$(document).ready(function () {
    $("#patientList").dataTable({
    	 "pageLength":25,
    	"sPaginationType": "full_numbers",
        "bJQueryUI": true,
   	
      responsive: true,
      
       	initComplete: function () {

       	this.api().columns([5]).every( function () {
            var column = this;
            var select = $("#testNameFltr").on( 'change', function () {
                var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw(); 
                    } ); 
            column.data().unique().sort().each( function ( d, j ) {
            	var val = $('<div/>').html(d).text();
              select.append( '<option value="'+val+'">'+val+'</option>')
            } );
          } );

       this.api().columns([4]).every( function () {
            var column = this;
            //console.log(column);
            var select = $("#labNameFltr").on( 'change', function () {
                var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw(); 
                    } ); 
            column.data().unique().sort().each( function ( d, j ) {
            	var val = $('<div/>').html(d).text();
              select.append( '<option value="'+val+'">'+val+'</option>')
            } );
          } );

      this.api().columns([7]).every( function () {
            var column = this;
            //console.log(column);
            var select = $("#patStatusFltr").on( 'change', function () {
                var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw(); 
                    } ); 
            column.data().unique().sort().each( function ( d, j ) {
            	var val = $('<div/>').html(d).text();
              select.append( '<option value="'+val+'">'+val+'</option>')
            } );
          } );
	       	
     /*  	 	$('input.global_filter').on( 'keyup click', function () {
            	filterGlobal();
        	});
      */

      
       	 	$('input.column_filter').on( 'keyup click', function () {
            	filterColumn( $(this).parents('tr').attr('data-column') );
        	}); 

       /* 	$('#testNameFltr').on('change', function(){
           table.search(this.value).draw();   
        }); */
        	
        }

		
	});
}); 



function filterColumn ( i ) {
    $('#patientList').DataTable().column( i ).search(
        $('#col'+i+'_filter').val(),
        $('#col'+i+'_regex').prop("checked", true),
        $('#col'+i+'_smart').prop("checked", true)
    ).draw();
} 

</script> -->
<script>
$(document).ready( function () {
    $('#patientList').DataTable({
   	 "pageLength": 25,
    
        });


    
} );
</script>


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
	 {return false;}
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

		alert("confused" + doc.text(100, 100, url));
		//doc.text(20, 30, 'This is client-side Javascript, pumping out a PDF.');
		//doc.addPage();
		//doc.text(20, 20, 'Do you like that?');

		// Output as Data URI
		//doc.output('datauri');

		doc.output('datauri');
		//document.getElementsByTagName('popUpDiv')[0].innerHTML=doc.output();

	}
</script>



<!-- <script type="text/javascript">
	$('a.media').media({
		width : 500,
		height : 400
	});
</script>
 -->

<script>
function onClickGO(hospitalCode)
{
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
 
 
 if(genTypeValue=="P"||genTypeValue=='')
	{
	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
     {
    	alert("Select Name  ");
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
					        
					        
					        if(genTypeValue=="P"||genTypeValue=='')
					       	{
					        	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
					             {
					            	alert("Select Name  ");
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

	function displaySamplePatDetails() {
		var count = 0;
		document.getElementsByName('isPatDetailPage')[0].value = "1";
		var strAllChkDetail = "";
		var concatenateChkBoxVal = "";
		//var cbs = document.getElementsByTagName('input');
		var cbs = document.getElementsByName('chkSamplePatient');
		for (var i = 0; i < cbs.length; i++) {
			// if(cbs[i].type == 'checkbox') 
			//{

			if (cbs[i].checked) {

				count++;
				concatenateChkBoxVal = concatenateChkBoxVal
						.concat(cbs[i].value);
				concatenateChkBoxVal += '@';
			}
			//}

		}

		if (count == 0) {
			alert("please select a record");
			return false;
		}

		 

		//demo1();
		document.getElementsByName('selectedCheckbox')[0].value = concatenateChkBoxVal;

		strAllChkDetail = strAllChkDetail + "&selectedCheckbox="
				+ document.getElementsByName('selectedCheckbox')[0].value;

		var mode = "DUPLICATESHOWPATDETAILS";

		var url = '/HISInvestigationG5/new_investigation/invDuplicateResultReportPrintingAdvanced.cnt?hmode='
				+ mode + strAllChkDetail;

		//	alert("final url"+url);

		AddRowToTableAddMoreValue(url);
		popup('popUpDiv');

		//document.forms[0].submit();

	}

	function AddRowToTableAddMoreValue(finalMadCode) {

		// alert("working fine"+finalMadCode);

		//var mode = "SHOWPATDETAILS";

		//var url ='/HISInvestigationG5/new_investigation/invResultReportPrinting.cnt?hmode='+mode

		var nRow = 0;
		var tableObj = document.getElementById('addMoreValue');
		var numRows = tableObj.rows.length;
		//alert(document.getElementById("setPdf"));
		if (document.getElementById("setPdf") != null) {

			document.getElementById("setPdf").remove();
			//document.getElementById("deleteRow").deleteRow(1);
		}

		//alert("total length"+numRows);
		nRow = numRows;

		var tabRow = tableObj.insertRow(numRows);
		tabRow.id = parseInt(nRow);

		var td1 = document.createElement("TD");

		td1.innerHTML = "<iframe src='"+ finalMadCode + "' width='100%' height='450px' type='application/pdf'    ></iframe> ";
		td1.className = 'tdfont';
		td1.colspan = "1";

		tabRow.appendChild(td1);

		//document.forms[0].numberOfRow.value=numRows;
	}

	function ValidateSameCrNo(obj) {

		if (obj.checked) {
			document.getElementById('nextDiv').style.display = "";

		} else {
			document.getElementById('gob').style.display = "";
			document.getElementById('cancel').style.display = "";

		}

		var objCurrentCheckBox = obj.value;

	}
	function showPatDetails(k) {
		document.getElementById("showhide" + k).style.display = "";
		document.getElementById("hide" + k).style.display = "";
		document.getElementById("show" + k).style.display = "none";

	}

	function hidePatDetails(k) {
		document.getElementById("showhide" + k).style.display = "none";
		document.getElementById("hide" + k).style.display = "none";
		document.getElementById("show" + k).style.display = "";
	}

	function submitFor() {
		document.getElementsByName('showStatus')[0].value = '0';
		document.getElementsByName('hmode')[0].value = 'NEW';
		document.forms[0].submit();
	}

	function checkDate(obj) {
		var flag = true;
		var calDate = obj.value;
		var today = new Date();
		var date = convertDateToStr(today, "dd-Mon-yyyy");
		var Time = convertDateToStr(today, "hh:mm");
		if (calDate > date) {
			alert("Date Will Not Exceed Than SysDate!...");
			return false;

		}

	}

	function doPagination(e, p) {
		document.getElementsByName('currentPage')[0].value = p;
		document.getElementsByName('hmode')[0].value = 'PAGINATION';
		document.forms[0].submit();
	}

	
	 <%
	   UserVO uservoRefresh=ControllerUTIL.getUserVO(request);
	     String hospitalCodeOnrefresh=uservoRefresh.getHospitalCode();
	   
	   %>
	   
	  function refresh()
	   {
		  // document.getElementsByName('hmode')[0].value='NEW';
			//  document.forms[0].submit(); 
		   onClickGO('<%=hospitalCodeOnrefresh%>');
	   }
	  
	
	/* setInterval(function() {
		 //alert(document.getElementsByName("patCRNo")[0].value);
		if(document.getElementsByName("patCRNo")[0].value!='')
			{
			
		 document.getElementsByName("tempPatCRNo")[0].value=document.getElementsByName("patCRNo")[0].value;
			}
	   }, 100);
	
	
	function cancelform()
	{
		document.getElementsByName("patCRNo")[0].value="";
		document.getElementsByName('hmode')[0].value ='NEW';
		document.forms[0].submit();
		
	} */

	
	 function pdfDetails(obj)
	   {
		       var pdfCombo = document.getElementsByName("pdfCombo")[0].value;
	          document.getElementsByName("combo")[0].value = pdfCombo;
		   document.getElementsByName('pdfGenerationType')[0].value=obj.value;
		   //alert(document.getElementsByName('pdfGenerationType')[0].value);
		 //  document.getElementsByName('hmode')[0].value="GETDETAILS";
	     //document.forms[0].submit();
	  
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
	    
		   var a = document.getElementsByName("combo")[0].value;

		   if(a==1)
			   {
			    
			     document.getElementsByName("pdfCombo")[0].value="1";
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
 
	
	
	
	</script>




<script type="text/javascript">
	var isChecked = false;
	function allSelected() {
		document.getElementById("nextDiv").style.display = "";
		// this line is for toggle the check
		isChecked = !isChecked;
		//below line refers to 'jpCheckbox' class
		$('input:checkbox.jpCheckbox').prop('checked', isChecked);
		//OR,
		//$('input:checkbox.jpCheckbox').attr('checked','checked');
	}
	
	
	function cancelFunc()
	{
		window.parent.closeTab();
	}

	function updateSelctBox(){
	 
		document.getElementsByName('labCode')[0].setAttribute('class', 'form-control');
		document.getElementsByName('labCode')[0].style.width = "250px";
		document.getElementsByName('pdfGenerationType')[0].setAttribute('class', 'form-control');
		document.getElementsByName('pdfGenerationType')[0].style.width = "150px"; 
		
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
.form-control {
    display: block;
    width: 80%;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}

 /* .table.dataTable tr{ background-color:  #ffe6e6; } */ 
 .table.dataTable tr:nth-child(even)  { background-color:  #ecf9f2;} 

</style>


<style>
.vertpan img:hover {
	transform: rotateX(180deg);
}
</style>
<style>
</style>
<body onload="updateSelctBox();checkEntryType()">
	<html:form action="/invDuplicateResultReportPrintingAdvanced">
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="hmode" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="isPatDetailPage" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="selectedCheckbox" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="showStatus" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="currentPage" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="patCRNo" />
			<html:hidden name="InvDuplicateResultReportPrintingFB"  
			property="pdfGenerationType" />
			<html:hidden name="InvDuplicateResultReportPrintingFB"  
			property="sysDate" />
			
			<html:hidden name="InvDuplicateResultReportPrintingFB" property="getSearchType" />
	  	  	   <html:hidden name="InvDuplicateResultReportPrintingFB" property="generationType"    />
	  	  	     <html:hidden name="InvDuplicateResultReportPrintingFB" property="onLoadValue"    />
	  	  	       <html:hidden name="InvDuplicateResultReportPrintingFB" property="combo" /> 
	  	  	        <html:hidden name="InvDuplicateResultReportPrintingFB" property="pdfGenerationType" /> 
		  <%!
		boolean readOnly;
		%>
		<%
			this.readOnly=false;
		%> 
		<logic:equal name="InvDuplicateResultReportPrintingFB"
			property="hmode" value="VIEW">
			<%
				this.readOnly=true;
			%>
		</logic:equal>
		<%-- <his:TitleTag name="Duplicate Report Printing" >
			<his:insertDateTag />
		</his:TitleTag>  --%>
		<article>
  <header style="background-color:#1E90FF;padding:6px">
    <h2 style="color:white;"><b>Duplicate Result Report Printing</b></h2>
   
  </header>
  
</article>

		<%
			String fromDateLabel="" ;
		              String toDateLabel="" ;
		              String fromDateControl="" ;
		              String toDateControl="" ;
		%>
		<bean:define name="InvDuplicateResultReportPrintingFB"
			property="fromDate" id="frDate" type="java.lang.String" />
		<bean:define name="InvDuplicateResultReportPrintingFB"
			property="toDate" id="tDate" type="java.lang.String" />
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
		<logic:equal name="InvDuplicateResultReportPrintingFB"
			property="showStatus" value="0">
			<%-- <his:SubTitleTag name="Details"></
  			</his:SubTitleTag> --%>
  		
			<table width="100%" border="0" cellspacing="1" cellpadding="0" style="display:none;">
			    <tbody>
			     <tr>
			  <td class="tdfont" width="25%" colspan="4">
			        <div align="right" style="background-color:#1E90FF;padding: 7px;">
			         <logic:notEqual name="InvDuplicateResultReportPrintingFB" property="pdfGenerationType" value="1">
			           <input type="radio" name="pdfGenerationType" value="0"  onclick="pdfDetails(this);" checked="checked"><b><bean:message key="before"/></b>
			           <input type="radio" name="pdfGenerationType" value="1"  onclick="pdfDetails(this);"  ><b><bean:message key="after"/></b>
		 				</logic:notEqual>
					 				<logic:equal name="InvDuplicateResultReportPrintingFB" property="pdfGenerationType" value="1">
					 	  <input type="radio" name="pdfGenerationType" value="0" onclick="pdfDetails(this);"   ><b><bean:message key="before"/></b>
					 	<input type="radio" name="pdfGenerationType" value="1"  onclick="pdfDetails(this);" checked="checked" ><b><bean:message key="after"/></b>
				 				</logic:equal>
			         
				     </div>
			      </td> 
			      <%-- <td> 
			       <div align="center" style="background-color: #C0C0C0; padding: 8px;">
					   <span class="custom-dropdown small">
					  
				 <html:select name="InvDuplicateResultReportPrintingFB" property="pdfGenerationType" onclick="pdfDetails(this)" tabindex="1"  >
				       					<html:option value="0">Before 15 Days</html:option>
				 	   					<html:option value="1">After 15 Days</html:option>
		 		      				</html:select>
		 		      				
		 		      				   
				      				</span>
				  </div>
</td> --%>

			     </tr>
			     
			      <tr>    
			     <%-- <tr>
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
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING%>">
			      <div align="left">
					   <span class="custom-dropdown small">
				 <html:select name="InvDuplicateResultReportPrintingFB" property="labCode"   onchange="labBased()" tabindex="1"  >
				       					<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING%>" property="value" labelProperty="label"/>
		 		      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     
			     </td>
			     <td width="25%" class="tdfont">
			      </td>
			      <td width="25%" class="tdfont">
			     </td>
			     </tr> --%>
			     <%--  <tr>            
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
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
 		</tr> --%>
 		 
 		 <tr  style="display:none;">
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
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="P">
						 
						<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="P">
						<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)"   value="P" />
						</logic:notEqual>
						<bean:message key="PatientWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="T">
						<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked"  value="T" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="T">
					<input type="radio" name="testWise" onclick="getDetails(this)"   value="T" />
						</logic:notEqual>
						
						<bean:message key="testWise"/>
	     		 
	     		         <!-- updated by krishnan nema on 28/09/2018 -->
	     		        <logic:notEqual name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">                                                                                      
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  checked="checked" value="S" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  value="S" />
						</logic:notEqual>
						
						
						<bean:message key="sampleNoWise"/>
						</logic:notEqual>
	     		  
	     		        <logic:notEqual name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWise"/>
						</logic:notEqual>
						
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWiseAcc"/>
						</logic:equal>
						
						
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)" checked="checked" value="TG" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)"  value="TG" />
						</logic:notEqual>
						
						
						<bean:message key="testGrpWise"/>
						
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)" checked="checked" value="AP" />
						</logic:equal>
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)"  value="AP" />
						</logic:notEqual>
						
						
						<bean:message key="allPatient"/>
	     		   </div>  
	     		    
			     </td>
			     
			     </tr>
 		     <tr style="display:none;">
			    <td width="25%" class="tdfont">
			    <div align="right" id="showOnLoad" style="display:none">
			    <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			        <div align="right" >
			            
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="P">
								 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="crNO"/>&nbsp;
								</logic:equal>
								 <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="T">
								  <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						        	*	 
						         </font> 
								<bean:message key="testName"/>&nbsp;
								</logic:equal>
								
								<!-- updated by krishnan nema on 01/10/2018 -->
								 <%-- <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
								 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal> --%>
								<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
								<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
								 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal>
								</logic:notEqual>
								
								<logic:equal name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
								<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
								 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="fromAccessionNo"/>&nbsp;
								</logic:equal>
								</logic:equal>
								
								
								 <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="fromSampleNo"/>&nbsp;
								</logic:equal>
								<logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="TG">
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
				 <html:select name="InvDuplicateResultReportPrintingFB" property="testWiseCode"    tabindex="1"   >
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
				 <html:select name="InvDuplicateResultReportPrintingFB" property="fromSampleNo"    tabindex="1"  >
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
				 <html:select name="InvDuplicateResultReportPrintingFB" property="fromLabNo"    tabindex="1"   >
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
				 <html:select name="InvDuplicateResultReportPrintingFB" property="testGroupCodeWise"    tabindex="1"   >
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
			     				<%-- <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
			     				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal> --%>
								 <logic:notEqual name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
								 <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
			     				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal>
								</logic:notEqual>
								
								<logic:equal name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
								 <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="L">
			     				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font> 
								<bean:message key="toAccessionNo"/>&nbsp;
								</logic:equal>
								</logic:equal>
								
								 <logic:equal name="InvDuplicateResultReportPrintingFB" property="generationType" value="S">
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
				 <html:select name="InvDuplicateResultReportPrintingFB" property="toLabNo"    tabindex="1"   >
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
				 <html:select name="InvDuplicateResultReportPrintingFB" property="toSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
				<%-- <tr>
					<td class="tdfont" align="center" colspan="4" width="25%">
						<div align="center">
							<img class="button"
								src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')"
								onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
						</div>
					</td>
				</tr> --%>
				</tbody>
			</table>
			</div>
			<!-- </div> -->
			
			  

			<%
				boolean flag=false;
			%>
			<%
				//Pagination Logic
						/* PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((InvDuplicateResultReportPrintingFB)request.getAttribute("InvDuplicateResultReportPrintingFB")).getCurrentPage());
						fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
						fbPage.setAppendInTitle("List ");
						int maxRecord=15;
						fbPage.setMaxRecords(maxRecord); */
			%>
			 <style>
.newGo{
	height: 34px;
	width: 45px;
	color: green;
}
</style>

<!-- <div class="panel panel-primary">
	  <div class="panel-body" style="background-color:"> --> 
					 <table class="table table-bordered" style="width: 100%;">
						<tbody>
						<tr>
							<%-- <td>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong>
										<bean:message key="sampleColl" />
									</strong>&nbsp;
								</font>
							</td> --%>
							<td>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong>
										<bean:message key="LabType" />&nbsp;
									</strong>
								</font>
							</td>
							 <td>
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"></font> 
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong>
									<bean:message key="searchBy"/>&nbsp;&nbsp;&nbsp;
									</strong>
								</font>
							</td> 
							<td>
								<div id='divfromDate' style='<%=fromDateLabel %>' align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong> 
										<bean:message key="fromDate" />&nbsp;
									</strong>
									</font>
								</div>
							</td>
							<td>
								<div id='divToDate' style='<%=toDateLabel %>' align="left">
									
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<strong> 
										<bean:message key="toDate" />&nbsp;
									</strong>
									</font>
									
								</div>
							</td>
							<td  style="vertical-align : middle;text-align:center;">
								<% 
									UserVO uservoNew=ControllerUTIL.getUserVO(request);
			    					String hCode=uservoNew.getHospitalCode();
									%>
								<%-- <img src='media/images/ic_launcher_go.png' class="newGo" id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) onClickGO('<%=hCode%>')"
										onclick="onClickGO('<%=hCode%>')" tabindex="1"> --%>
										 <a href="#" onclick="onClickGO('<%=hCode%>')" 
										 	onkeypress="if(event.keyCode==13) onClickGO('<%=hCode%>')"
										 	class="btn btn-success btn-sm">
											<span class="glyphicon glyphicon-search"></span> Search 
        								</a>
							</td>
						</tr>
						<tr>
							
                            <%-- <td style="vertical-align : middle;text-align:left;">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="InvResultValidationFB" property="sampleAreaName"/>
								</font>
								<html:hidden name="InvResultValidationFB" property="sampleAreaCode"/>
							</td> --%>
							<td>
								<logic:present	name="<%=InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING%>">
									
									<html:select name="InvDuplicateResultReportPrintingFB" property="labCode" tabindex="1" onchange="labBased()" >
											<html:option value="%">All</html:option>
											<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING%>" property="value" labelProperty="label" />
										</html:select>
										
								</logic:present>
							</td> 
							 <td>
							
									<%-- <html:select name="InvResultValidationFB" property="pdfGenerationType" tabindex="1" onchange="pdfDetails(this)" >
											<html:option value="1"><bean:message key="reqNoDate"/></html:option>
											<html:option value="0"><bean:message key="sampleCollDateTest"/></html:option>
									</html:select> --%>
									<select class="form-control" name=pdfCombo onchange="pdfDetails(this)" tabindex="1">
				      			
  									<option value="0"  >Before 15 Days</option>
  									<option value="1" >After 15 Days</option>
  								</select>
										
								<%-- <bean:message key="reqNoDate"/>&nbsp; 
								<html:radio name="InvResultValidationFB" property="searchBy" onclick="showReqDate();callGetDetails();"  value="1"></html:radio>
								<bean:message key="sampleCollDateTest"/>&nbsp;
								<html:radio name="InvResultValidationFB" property="searchBy" onclick="showCollDate();callGetDetails();"  value="0"></html:radio> --%>
							</td>
							
							<td>
								<div id='divfromDateControl' style='<%=fromDateControl %>' align="left" >
									 <his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" /> 
									<!--  <div class="form-group">
									<div class='input-group date' id='datetimepicker1'>
            							<input type='text' class="form-control" />
            								<span class="input-group-addon">
                            					<span class="glyphicon glyphicon-calendar"></span>
            								</span>
          								</div>
          								</div> -->
								</div>
							</td>
							
							<td>
								<div id='divToDateControl' style='<%=toDateControl %>' align="left">
									<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
								</div>
							</td>
							<logic:present name="<%=InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO%>">
							
							<td style="vertical-align : middle;text-align:center;">
								 <a id="cancel" href="#" onclick="cancelFunc()" 
										 	onkeypress="if(event.keyCode==13) cancelFunc()"
										 	class="btn btn-danger btn-sm">
											<span class="glyphicon glyphicon-remove-sign"></span> Cancel 
        								</a>
							</td>
							
				            </logic:present>
							
						</tr>
						</tbody>
					</table>
					<!-- </div>
					</div> -->

	
	<%-- <div class="panel panel-primary">
	  <div class="panel-body" style="background-color:#d9e8f7"> 
<div class="container-fluid" style="background-color: #1E90FF; padding: 13px;">
					
						<div class="row">
						
					<div class="col-sm-1" align="left">

			       
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif" > 
									<strong> <bean:message key="LabType"/>&nbsp;</strong>
								
						 </font>
						  </div>
				     
			       <div class="col-sm-2" align="left" style="padding-left:0;padding-right:0;">
			       <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING%>">
			      <div align="left" >
					   <span class="custom-dropdown small">
				 <html:select name="InvDuplicateResultReportPrintingFB" property="labCode"   onchange="labBased()" tabindex="1"  >
				       					<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING%>" property="value" labelProperty="label"/>
		 		      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     
			    
			     </div>
			     <div class="col-sm-1" align="center" style="padding-left:0;padding-right:0;">

			       
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif" > 
									<strong> Criteria :</strong>
								
						 </font>
						  </div>
						   <div align="left" class="col-sm-1" style="padding-left:0;padding-right:0" >
					   <span class="custom-dropdown small">
					  
				 <html:select name="InvDuplicateResultReportPrintingFB"  property="pdfGenerationType" onchange="pdfDetails(this)" tabindex="1"  >
				       					<html:option value="0">Before 15 Days</html:option>
				 	   					<html:option value="1">After 15 Days</html:option>
		 		      				</html:select>
		 		      				
		 		      				   
				      				</span>
				      				<select class="form-control" name=pdfGenerationType onchange="pdfDetails(this)" tabindex="1">
				      			
  									<option value="0"  >Before 15 Days</option>
  									<option value="1" >After 15 Days</option>
  								</select>
  								</span>
				  </div>
							
					
								
								 <div class="col-sm-1" id="divfromDate" style='<%=fromDateLabel %>' align="right" style="padding-left:0;padding-right:0;">
								<!-- 
											 <font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#ffffff"><strong> From Date</strong>&nbsp;
										  </font> -->
										 <font color="#FFFFFF" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		                     <font color="#000000" size="4" face="Verdana, Arial, Helvetica,sans-serif">
						                 <strong><bean:message key="fromDate"/>&nbsp;</strong>
					                       </font>
									
								</div>
								<div class="col-sm-2" id="divfromDateControl" style='<%=fromDateLabel %>' align="left" style="padding-left:0;padding-right:0;">
								
										 <!--  <input type="text" class="textBoxCss" name="fromDate" id="fromDate" readonly="" value="01-Feb-2019" size="12"> 	<img onkeypress="if(event.keyCode==13) this.click()" src="/HISInvestigationG5/hisglobal/images/cal.png" id="fromDate1" style="cursor: pointer; border: 1px solid red;" title="Date selector" tabindex="1" onmouseover="this.style.background='red';" onmouseout="this.style.background=''" align="top"> 	<script language="JavaScript"> Calendar.setup( { inputField     :    "fromDate",mapkey : '32', ifFormat       :    "%d-%b-%Y", button         :    "fromDate1" , singleClick    :    true });	</script> -->
									
									<!-- <input type="hidden" value="01-Feb-2019" name="fromDateHidden">  -->
									&nbsp;&nbsp;&nbsp; <his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/> <!-- <input type="text" class="textBoxCss" name="fromDate" id="fromDate" readonly="" value="01-Mar-2019" size="12"> --> 	<!-- <img onkeypress="if(event.keyCode==13) this.click()" src="/HISInvestigationG5/hisglobal/images/cal.png" id="fromDate1" style="cursor: pointer; border: 1px ;" title="Date selector" tabindex="1" onmouseover="this.style.background='red';" onmouseout="this.style.background=''" align="top"> 	<script language="JavaScript"> Calendar.setup( { inputField     :    "fromDate",mapkey : '32', ifFormat       :    "%d-%b-%Y", button         :    "fromDate1" , singleClick    :    true });	</script>   
									 -->
								
								</div>
								<div class="col-sm-1" id="divToDate" style='<%=toDateLabel %>' align="right" style="padding-left:0;padding-right:0;">
									
										 <!-- <font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#ffffff"><strong> To Date</strong>&nbsp;
										</font> -->
										<font color="#FFFFFF" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif">
						<strong><bean:message key="toDate"/>&nbsp;</strong>
					</font>
										
									
								</div>
								
								<div class="col-sm-2" id="divToDateControl" style='<%=toDateControl %>' align="left" style="padding-left:0;padding-right:0;">
									<!-- 
										<input type="text" class="textBoxCss" name="toDate" id="toDate" readonly="" value="26-Feb-2019" size="12"> 	<img onkeypress="if(event.keyCode==13) this.click()" src="/HISInvestigationG5/hisglobal/images/cal.png" id="toDate1" style="cursor: pointer; border: 1px solid red;" title="Date selector" tabindex="1" onmouseover="this.style.background='red';" onmouseout="this.style.background=''" align="top"> 	<script language="JavaScript"> Calendar.setup( { inputField     :    "toDate",mapkey : '32', ifFormat       :    "%d-%b-%Y", button         :    "toDate1" , singleClick    :    true });	</script>
									 <input type="hidden" value="26-Feb-2019" name="toDateHidden"> -->
									 	&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/><!-- <input type="text" class="textBoxCss" name="toDate" id="toDate" readonly="" value="26-ma-2019" size="12"> --> 	<!-- <img onkeypress="if(event.keyCode==13) this.click()" src="/HISInvestigationG5/hisglobal/images/cal.png" id="toDate1" style="cursor: pointer; border: 1px;" title="Date selector" tabindex="1" onmouseover="this.style.background='red';" onmouseout="this.style.background=''" align="top"> 	<script language="JavaScript"> Calendar.setup( { inputField     :    "toDate",mapkey : '32', ifFormat       :    "%d-%b-%Y", button         :    "toDate1" , singleClick    :    true });	</script> -->
									 
								
								</div> 
								<div class="col-sm-1" align="center" style="padding-left:0;padding-right:0;">

			       
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif" > 
									<strong> Criteria :</strong>
								
						 </font>
						  </div>
						   <div align="left" class="col-sm-1" style="padding-left:0;padding-right:0" >
					   <span class="custom-dropdown small">
					  
				 <html:select name="InvDuplicateResultReportPrintingFB"  property="pdfGenerationType" onchange="pdfDetails(this)" tabindex="1"  >
				       					<html:option value="0">Before 15 Days</html:option>
				 	   					<html:option value="1">After 15 Days</html:option>
		 		      				</html:select>
		 		      				
		 		      				   
				      				</span>
				      				<select class="form-control" name=pdfGenerationType onchange="pdfDetails(this)" tabindex="1">
  									<option value="0"  >Before 15 Days</option>
  									<option value="1" >After 15 Days</option>
  								</select>
  								</span>
				  </div>
							
        		
			
								
								
								
								<div class="col-sm-1" align="center" style="padding-left:0;">
									<!-- <img src="media/images/ic_launcher_go.png" class="newGo" id="gob" style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('96101')" onclick="onClickGO('96101')" tabindex="1">  -->
									   <!-- <button type="button" class="btn btn-success"   style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('96101')" onclick="onClickGO('96101')" tabindex="1">Go</button> -->
								 <a href="#" onclick="onClickGO('96101')" 
										 	onkeypress="if(event.keyCode==13) onClickGO('96101')"
										 	class="btn btn-success btn-sm">
											<span class="glyphicon glyphicon-search"></span> Search 
        								</a>
								
						</div>
					</div>
					</div>
					</div>
					 </div>  --%>
					


			<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				<%
					flag=true;
				%>
				<logic:empty
					name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
					<center>
						<font color="red" size="4"> <bean:message key="noRecord" /></font>
					</center>
				</logic:empty>
				<logic:notEmpty
					name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
					
	<div class="panel panel-primary">
	 <div class="panel-body" style="background-color:">
					<div class="container-fluid" >
					
						<div class="row">
						
					<div class="col-sm-12" align="center">
					
				<table   id="patientList"  width="100%"   bgcolor="#1E90FF" class="table table-striped" class="display responsive" >                          <!--Datatables added by harshita khare  -->
			      <thead bgcolor="#1E90FF"> 
					 <tr> 
						<td width="3%" align="left"><b> <font color="#FFFFFF"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <input
									type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />
							</font>
						</b></td>
						<td width="12%" align="left"><b><font
								color="#FFFFFF" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="crNO" /></font></b></td>
						<td width="12%" align="left"><b><font color="#FFFFFF"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="patientName" /></font></b></td>
						<td width="12%" align="left"><b> <font
								color="#FFFFFF" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="age/gender" /></font></b></td>
						<%-- <td width="15%" align="left"><b><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif" style="display: none;"> <bean:message
										key="departmentunit" /></font></b></td> --%>
										<td width="14%" align="left"><b><font color="#FFFFFF"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="labName" /></font></b></td>
						<td width="12%" align="left"><b> <font color="#FFFFFF"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="TestName" /></font></b></td>
						
						
						<!-- updated by krishnan nema on 01/10/2018 -->
						<%-- <td width="8%" align="left"><b> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="sampLabNo" /></font></b></td> --%>
										
						<logic:notEqual name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
							<td width="10%" align="left"  >
						<b> <font color="#FFFFFF" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<%-- <bean:message key="sampLabNo"/> --%>Sample/LabNo.</font></b>
					</td>
					</logic:notEqual>
					
					<logic:equal name="InvDuplicateResultReportPrintingFB" property="patTestType" value="2">
							<td width="10%" align="left"  >
						<b> <font color="#FFFFFF" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="accessionNowise"/></font></b>
					</td>
					</logic:equal>
						
						
						<td width="12%" align="left"><b> <font
								color="#FFFFFF" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="patStatus" /></font></b></td>
									
										<td width="12%" align="left"><b> 
											<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
												<logic:equal name="InvDuplicateResultReportPrintingFB" property="pdfGenerationType" value="0">  
													RequisitionDate
												</logic:equal>
												<logic:equal name="InvDuplicateResultReportPrintingFB" property="pdfGenerationType" value="1">  
													AcceptanceDate
												</logic:equal>
											</font>
										</b></td>
									
					</tr> 
				 </thead>
			     <tbody>
			        		<%
							List<InvDuplicateResultReportPrintingVO> lstPatVO=(List<InvDuplicateResultReportPrintingVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
									 int i,size=0;
							 		if(lstPatVO!=null && lstPatVO.size()>0 )
							 			size=lstPatVO.size();
									//int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
									//int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
									String grpCode="";
									for(int j=0;j<lstPatVO.size();j++)
								{
									//int i=j-startIndex;
									boolean firstTimeTravesall=true;
								//if(j<size)
												//{
									InvDuplicateResultReportPrintingVO voPat=lstPatVO.get(j);
									String  chkVal=voPat.getPatPuk()+","+voPat.getRequisitionNo()+","+voPat.getRequisitionDNo()+","+voPat.getGroupCode();
// 								if(voPat.getGroupCode()!=null)
// 								{   
									 
								 
// 									grpCode+='&';
// 									String[] SplitGrpCode=grpCode.split("&");
// 									int length=SplitGrpCode.length;
// 									if(SplitGrpCode.length>1)
// 									for(int x=0;x<SplitGrpCode.length;x++)
// 									{
// 				                    if(SplitGrpCode[x].equals(voPat.getRequisitionNo()+voPat.getGroupCode()))
// 				                    {
// 									 firstTimeTravesall=false;
// 				                    }
// 				                    else
// 			                        {
// 			                        	 firstTimeTravesall=true;	
// 			                        }
// 								} 
									 
// 									 grpCode+=voPat.getRequisitionNo()+voPat.getGroupCode();
									 
				     				 
// 								}
								 

								 if(firstTimeTravesall)
						 			{
								
						%>
						
						<tr >
							<td width="3%" align="left"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <input
									type="checkbox" class="jpCheckbox" name="chkSamplePatient"
									value='<%=chkVal%>' onclick="ValidateSameCrNo(this)">
							</font></td>
							<td width="12%" align="left" ><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatPuk()%></font>

							</td>

							<td width="12%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName()%></font>


							</td>
							<td width="12%" align="left" ><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge()+"/"+voPat.getPatGender()%></font>

							</td>
							<%-- <td width="15%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif" style="display: none;"> <%=voPat.getPatUnitName()%></font>

							</td> --%>
							<td width="12%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatLabName()%></font>

							</td>
							<td width="12%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName() %></font>

							</td>
							
								<td width="10%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo()%></font>



							</td>
							
							<td width="10%" align="left"><font color="#000000"
								size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus()%></font>



							</td>
							<td width="12%"align="left">
														
			          	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getRequisitionDate() %></font>
														
	                      	</td>
						</tr>
						
						<%
						 			}}   /* } */ 
						%>
						</tbody>
					</table>
					</div>
						</div>
							</div>
							</div>
							</div>
						
						
				</logic:notEmpty>
			</logic:present>
			<div id="container">
				<div id="blanket" style="display: none;"></div>
				<div draggable="true" id="popUpDiv" style="display: none;">
					<his:SubTitleTag name="Duplicate Report Printing Details">
						<div class="vertpan pic">
							<img src='/HISInvestigationG5/hisglobal/css/close.png'
								onclick="popupClose('popUpDiv')"'>
						</div>
					</his:SubTitleTag>
					<table id="addMoreValue" width="100%">
						<tr id="deleteRow">
						</tr>
					</table>
					<left></left>
				</div>
				<!-- end #mainContent -->
			</div>
		</logic:equal>


		<script type="text/javascript">
			function demo1(url) {

				alert("2");
				var doc = new jsPDF();

				var newURL = url;
				doc.text(100, 100, url)

				alert("confused" + url);
				//doc.text(20, 30, 'This is client-side Javascript, pumping out a PDF.');
				//doc.addPage();
				//doc.text(20, 20, 'Do you like that?');

				// Output as Data URI
				//doc.output('datauri');
				doc.output('datauri');
				//document.getElementsByTagName('popUpDiv')[0].innerHTML=doc.output();
			}
		</script>
		 <his:ButtonToolBarTag>
			<%-- <img class="button"
				src='<his:path src="/hisglobal/images/PrintResults.png"/>'
				id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
				onclick="displaySamplePatDetails();"> --%>
				<a href="#" class="btn btn-warning btn-sm" onclick="displaySamplePatDetails();">
          <span class="glyphicon glyphicon-print" ></span> Print Result
        </a>
			<%-- <logic:present
				name="<%=InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO%>">
				<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel"
					tabindex="1" style="cursor: pointer"
					onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1"
					onclick="cancelFunc();">
					<!-- onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
					onclick="submitFor();"> -->
					 <a href="#" class="btn btn-info btn-sm" onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick="cancelFunc();">
          <span class="glyphicon glyphicon-remove"></span> Cancel 
        </a>
			</logic:present>  --%>
		</his:ButtonToolBarTag> 
				
				    
	
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="resultEntryTemplateValue" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="parameterCode" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="parantParameterCode" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="requisitionDNo" />
		<html:hidden name="InvDuplicateResultReportPrintingFB"
			property="resultEntryTemplateValueWithHash" />
			<html:hidden name="InvDuplicateResultReportPrintingFB" property="isAllLabPatientBased" />
			
			<html:hidden name="InvDuplicateResultReportPrintingFB" property="patTestType" />
		<his:status />
	</html:form>
</body>
</html>