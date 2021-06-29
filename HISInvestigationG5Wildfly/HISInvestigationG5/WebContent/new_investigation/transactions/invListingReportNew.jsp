<%@page import="new_investigation.InvestigationListingConfig"%>
<%@page import="new_investigation.vo.template.invListingReportNewVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.hisconfig.Config"%>


<!-- Added form date features -->
<%@page import="java.util.*"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%-- <%@page import="java.text.SimpleDateFormat"%> --%>
<%-- <%@page import="java.util.Date"%> --%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="new_investigation.InvestigationListingConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<!-- <script src="scripts/js/dataTables.jqueryui.min.js" type="text/javascript"></script>
<script src="scripts/js/buttons.jqueryui.min.js" type="text/javascript"></script>
-->

<!-- Added form pdf excel button -->
<script src="scripts/js/jszip.min.js" type="text/javascript"></script>
<script src="scripts/js/pdfmake.min.js" type="text/javascript"></script>
<script src="scripts/js/vfs_fonts.js" type="text/javascript"></script>


<!-- Added by Prashant start-->
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script>
<link href="media/dataTables/Responsive-2.2.2/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css" />

<script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.flash.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.print.min.js" type="text/javascript"></script>

<link href="media/dataTables/Buttons-1.5.6/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />

<link href="media/dataTables/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="media/dataTables/css/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css" />
<link href="media/dataTables/css/buttons.jqueryui.min.css" rel="stylesheet" type="text/css" />

<script src="media/common/js/commonDateOperations.js" type="text/javascript"></script>
<!-- Added by Prashant end-->


<his:javascript src="/hisglobal/js/calendar.js" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<link href="media/themes/AdvancedView.css" rel="stylesheet" type="text/css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<%-- <his:javascript src="/hisglobal/js/commonUtility.js" /> --%>
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>

 /* Added By PashantMi Server Side DataTables Processing Ajax*/
  /* function onbodyLoad(){
	 $(document).ready(function() {
		var _mode = "NEW_AJAX_SERVER";
		var fromDate=document.getElementsByName("fromDate")[0].value;
  	     var toDate=document.getElementsByName("toDate")[0].value;

  	   var table = $('#example').DataTable( {
	        // lengthChange: false,
	        dom: 'Bfrtip',
	        buttons: [ 'colvis', 'copy', 'excel',
	               {
	                extend: 'pdfHtml5',
	                title: 'Investigation Listing Report',
	                text: 'PDF',
	                orientation: 'landscape',
	                pageSize: 'A4'
	                /* customize: function ( doc ) {
	                	doc.content[1].table.widths = [
	                	'5%',
	                	'30%',
	                	'30%',
	                	'10%',
	                	'10%',
	                	'10%',
	                	'5%'
	                	]  }
	            }
	        ],

	         "bServerSide": true,
	         "sAjaxSource": "/HISInvestigationG5/new_investigation/invListingReportNew.cnt?hmode="+_mode+"&fromDate="+fromDate+"&toDate="+toDate, sync:true, postData: "", handleAs: "text",
	         "bProcessing": true,
	        "sPaginationType": "full_numbers",
	         "bJQueryUI": true,
	         "ordering": false

	     } );

	 } );
	 } */

	 /* Added By PashantMi Clien Side DataTables Processing Ajax*/
	 function onbodyLoad(){
		 $(document).ready(function() {
			var _mode = "NEW_AJAX_CLIENT";
			var fromDate=document.getElementsByName("fromDate")[0].value;
	  	     var toDate=document.getElementsByName("toDate")[0].value;
	  	   var searchDateType=$('#selectDateTypeInput').val();
		     var table = $('#example').DataTable( {
		          // lengthChange: false,
		          dom: 'Bfrtip',
		          buttons: [ 'colvis', 'copy', 'excel',
		            {
		           		extend: 'pdfHtml5',
		               	title: 'Investigation Listing Report',
		               	text: 'PDF',
		               	orientation: 'landscape',
		               	pageSize: 'A3',
		               	pageMargins: [ 0, 0, 0, 0 ], // try #1 setting margins
		                margin: [ 0, 0, 0, 0 ], // try #2 setting margins
		                alignment: 'center',
		                exportOptions: {
		                columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,15]
		             }
		           /* customize: function ( doc ) {
		              	doc.content[1].table.widths = [
		               	'5%',
		               	'30%',
		               	'30%',
		               	'10%',
		               	'10%',
		               	'10%',
		               	'5%'
		              	]  }*/
		            }
		           ],

		         "ajax": {
		        	"url": "/HISInvestigationG5/new_investigation/invListingReportNew.cnt?hmode="+_mode+"&fromDate="+fromDate+"&toDate="+toDate+"&searchDateType="+searchDateType, sync:true, postData: "", handleAs: "text",
		         dataSrc: "aaData"
		           },
		         /* "columns": [
	        		 { "data": 'sno' },
	        		 { "data": 'crNumber' },
	        		 { "data": 'patientStatus' },
	        		 { "data": 'patientName' },
	        		 { "data": 'ageGender' },
	        		 { "data": 'groupName' },
	        		 { "data": 'testName' },
	        		 { "data": 'sampleName' },
	        		 { "data": 'sampleNum' },
	        		 { "data": 'labNum' },
	        		 { "data": 'status' },
	        		 {  "data": 'labName' },
	        		 {  "data": 'reqDate' },
	        		 {  "data": 'department' },
	        		 {  "data": 'wardName' }
	        		],	 */
		         "sPaginationType": "full_numbers",
		         "bJQueryUI": true,
		         //"ordering": false

		         initComplete: function () {

		       	this.api().columns([6]).every( function () {
		            var column = this;
		            //console.log(column);
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

		       	this.api().columns([5]).every( function () {
		            var column = this;
		            //console.log(column);
		            var select = $("#grpNameFltr").on( 'change', function () {
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

		       	this.api().columns([10]).every( function () {
		            var column = this;
		            //console.log(column);
		            var select = $("#statusFltr").on( 'change', function () {
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
		            var select = $("#SampleNameFltr").on( 'change', function () {
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





		       	this.api().columns([11]).every( function () {
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
		      	this.api().columns([15]).every( function () {
		            var column = this;
		            //console.log(column);
		            var select = $("#MachineNameFltr").on( 'change', function () {
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

            	} // initComplete

		     } );


		 } );
		 }
</script>
<script>
function onClickGO()
{
	if(validateDat()){
		document.getElementsByName('hmode')[0].value='GETDATEWISELISTING';
		var searchDateType=$('#selectDateTypeInput').val();
		$('[name="searchDateType"]')[0].value=searchDateType;
		document.forms[0].submit();
	}
}

$(function (){
	var searchDateType=$('[name="searchDateType"]')[0].value;
	if(searchDateType!="" && searchDateType!="0")
	$('#selectDateTypeInput').val(searchDateType);
	
});

function validateDat(){
	var fromDate=document.getElementsByName("fromDate")[0].value;
	var toDate=document.getElementsByName("toDate")[0].value;

	var fDate=stringToDate(fromDate,"dd-mmm-yyyy","-");
	var tDate=stringToDate(toDate,"dd-mmm-yyyy","-");

	var diffDate=diffDates(fDate,tDate);

	if(diffDate.response=="false"){
		alert(diffDate.reason);
		return false;
	}
	else if(diffDate.response=="true" && diffDate.dates >7) {
		alert("Only one week's data is allowed to fetch that is 7 Days so select From-Date and To-Date Accordingly");
		return false;
	}
	else if(diffDate.response=="true" && diffDate.dates <=7){
		return true;
	}
	else {
		alert("Somthing Went Worng Please Contact The Developer");
		return false;
	}
}
</script>
</head>

<body onload="onbodyLoad();">
<html:form action="/invListingReportNew" enctype="multipart/form-data">

			<bean:define name="invListingReportNewFB" property="fromDate" id="frDate"
				type="java.lang.String" />
			<bean:define name="invListingReportNewFB" property="toDate" id="tDate"
				type="java.lang.String" />

			 	<%
			 	if(frDate==null||frDate.equalsIgnoreCase(""))
			         {

			 		Date date = new Date(System.currentTimeMillis());
			 		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
			 		frDate=formatter.format(date);
			 		System.out.println(formatter.format(date));

			        	/* Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
			        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); */
			         }

					  if(tDate==null||tDate.equalsIgnoreCase(""))
					  {
					  	/* Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
					  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); */
						  Date date = new Date(System.currentTimeMillis());
					 		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
					 		tDate=formatter.format(date);
					 		System.out.println(formatter.format(date));
					   }
				%>

<div class="container">
	<table class="table table-bordered" width="100%" style="background-color: #4d4dff;">
		<tr align="center">
			<td>
				<select class="form-control custom-select selectDateTypeInput" id="selectDateTypeInput">
					<option value="-1" disabled>Choose Date</option>
					<option selected value="1">Requisition Date</option>
					<option value="2">Sample Collection Date</option>
					<%-- <option value="3">Patient Acceptance Date</option>
					<option value="4">Result Entry Date</option> --%>
				</select>
			</td>
			<td align="right"><font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>From Date</strong></font></td>
			<td align="left">
				<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
			</td>
			<td align="right"><font color="#ffffff" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>To Date</strong></font></td>
			<td align="left">
				<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" /></td>
			<td align="left">
			<img src="media/images/ic_launcher_go.png" class="newGo" id="gob"style="cursor: pointer"
						onkeypress="if(event.keyCode==13) onClickGO()"
						onclick="onClickGO()" tabindex="1">
			</td>
		</tr>
	</table>
	<table id="patListFilter" width="100%">
	<tr>
		<td>
	    	<table>
				<tr id="filter_col1" data-column="1">
			    	<td width="50%" align="right" >
			            <font color="#ff0000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			            <strong>Group Name &nbsp;</strong>
			            </font>
			        </td>
			        <td width="50%" align="left"><select id="grpNameFltr" style="width: 130px"><option value="">Select Groupname</option></select></td>
			   </tr>
			</table>
	    </td>
	    <td>
	    	<table>
				<tr id="filter_col2" data-column="2">
			    	<td width="50%" align="right">
			            <font color="#ff0000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			            <strong>Test Name &nbsp;</strong>
			            </font>
			        </td>
			        <td width="50%" align="left"><select id="testNameFltr" style="width: 130px"><option value="">Select Testname</option></select></td>
			   </tr>
			</table>
	    </td>
	    <td>
	    	<table>
				<tr id="filter_col2" data-column="2">
			    	<td width="50%" align="right">
			            <font color="#ff0000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			            <strong>Status &nbsp;</strong>
			            </font>
			        </td>
			        <td width="50%" align="left"><select id="statusFltr" style="width: 130px"><option value="">Select Status</option></select></td>
			   </tr>
			</table>
	    </td>
	    <td>
	    	<table>
				<tr id="filter_col2" data-column="2">
			    	<td width="50%" align="right">
			            <font color="#ff0000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			            <strong>Sample Name &nbsp;</strong>
			            </font>
			        </td>
			        <td width="50%" align="left"><select id="SampleNameFltr" style="width: 140px"><option value="">Select Samplename</option></select></td>
			   </tr>
			</table>
	    </td>
	    <td>
	    	<table>
				<tr id="filter_col2" data-column="2">
			    	<td width="50%" align="right">
			            <font color="#ff0000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			            <strong>Lab Name &nbsp;</strong>
			            </font>
			        </td>
			        <td width="50%" align="left"><select id="labNameFltr" style="width: 130px"><option value="">Select Labname</option></select></td>
			   </tr>
			</table>
	    </td>

	    <td>
	    	<table>
				<tr id="filter_col2" data-column="2">
			    	<td width="50%" align="right">
			            <font color="#ff0000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
			            <strong>Machine Name &nbsp;</strong>
			            </font>
			        </td>
			        <td width="50%" align="left"><select id="MachineNameFltr" style="width: 140px"><option value="">Select Machinename</option></select></td>
			   </tr>
			</table>
	    </td>
	</tr>
	</table>
</div>
<%-- <logic:empty name="<%=InvestigationListingConfig.LIST_INV_LISTING_REPORT_NEW%>">
	<center>
			<font color="red" size="4"> <bean:message key="noRecord" /></font>
	</center>
</logic:empty>

<logic:notEmpty name="<%=InvestigationListingConfig.LIST_INV_LISTING_REPORT_NEW%>">
 --%>

<div class="container">
<table id="example" class="display responsive" style="width:100%">
        <thead>
            <tr>
                <th>S.No</th>
                <th>CR Number</th>
                <th>Patient Status</th>
                <th>Patient Name</th>
                <th>Age/Gender</th>
                <th>Group Name</th>
                <th>Test Name</th>
                <th>Sample Name</th>
                <th>Sample No.</th>
                <th>Lab/Accession No.</th>
                <th>Status</th>
                <th>Lab Name</th>
                <th>Requisition Date</th>
                <th>Department</th>
                <th>Ward Name</th>
                 <th>Machine Name</th>
            </tr>
        </thead>
        <tbody>
      <%--   <%
			List<invListingReportNewVO> lst1=(List<invListingReportNewVO>)session.getAttribute(InvestigationListingConfig.LIST_INV_LISTING_REPORT_NEW);
			System.out.println("-=====-"+lst1);
			int i1,size1=0;
			if(lst1!=null && lst1.size()>0 )
				size1=lst1.size();
				String grpCode1="";
				for(int j=0;j<lst1.size();j++)
				{
					invListingReportNewVO voPat=lst1.get(j);
		%>
            <tr>
                <td><%=j+1%></td>
                <td><%=voPat.getPuk_fromreqno()%></td>
                <td><%=voPat.getPatType()%></td>
                <td><%=voPat.getPat_fromreqno()%></td>
                <td><%=voPat.getAge_gender()%></td>
                <td><%=voPat.getGroupName()%></td>
                <td><%=voPat.getTestName()%></td>
                <td><%=voPat.getSampleName()%></td>
                <td><%=voPat.getSample_num()%></td>
                 <td><%=voPat.getLab_num()%></td>
                <td><%=voPat.getStatus()%></td>
                <td><%=voPat.getLabName()%></td>
                <td><%=voPat.getReq_date()%></td>
            </tr>
            <%
				}
			%>   --%>
        </tbody>
         <tfoot>
            <tr style="display: none;">
                <th>S.No</th>
                <th>CR Number</th>
                <th>Patient Status</th>
                <th>Patient Name</th>
                <th>Age/Gender</th>
                <th>Group Name</th>
                <th>Test Name</th>
                <th>Sample Name</th>
                <th>Sample No.</th>
                <th>Lab/Accession No.</th>
                <th>Status</th>
                <th>Lab Name</th>
                <th>Requisition Date</th>
                <th>Department</th>
                <th>Ward Name</th>
                <th>Machine Name</th>
            </tr>
        </tfoot>
</table>
</div>
<%-- </logic:notEmpty> --%>
<html:hidden name="invListingReportNewFB" property="hmode" />
<html:hidden name="invListingReportNewFB" property="startIndex" />
<html:hidden name="invListingReportNewFB" property="totalRow" />
<html:hidden name="invListingReportNewFB" property="rowLimit" />
<html:hidden name="invListingReportNewFB" property="searchDateType" />

</html:form>


</body>
</html>
