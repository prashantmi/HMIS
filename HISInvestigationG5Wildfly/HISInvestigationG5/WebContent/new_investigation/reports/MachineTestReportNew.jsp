<!--
## Copyright Information	: C-DAC, Noida
## Project Name				: eSushrut
## Author          		 	: github.com/prashantmi
## Module Name				: INVESTIGATION
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="java.util.*"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>Machine Test Report</title>
  <meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">


  <!---New Resources Created by Prashant Start----------------------------------------------------------------------------->

  <!--JQuery Added by Prashant-->
  <script src="media/jquery/3.4.1/jquery-3.4.1.min.js" type="text/javascript"></script>

  <!--Propper JS Added by Prashant-->
  <script src="media/popper.js/1.15.0/umd/popper.min.js"></script>
  <!--Tooltip JS Added by Prashant-->
  <script src="media/tooltip.js/1.3.2/umd/tooltip.min.js"></script>


  <!--JQueryUI Added by Prashant-->
  <script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
  <link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
  <script src="media/jquery-ui/1.12.1/jquery.ui.datepicker.validation.min.js" type="text/javascript"></script>
  <script src="media/jquery-ui/1.12.1/jquery.validate.min.js" type="text/javascript"></script>

  <!--Bootstrap Added by Prashant-->
  <link href="media/bootstrap/bootstrap-4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <script src="media/bootstrap/bootstrap-4.3.1/js/bootstrap.min.js" type="text/javascript"></script>


  <!--Bootstrap Datables Added by Prashant-->
  <link href="media/dataTables/DataTables-1.10.18/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
  <script src="media/dataTables/DataTables-1.10.18/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>


  <!--Bootstrap Datatables Buttons Added by Prashant-->
  <link href="media/dataTables/Buttons-1.5.6/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.bootstrap4.min.js" type="text/javascript"></script>

  <script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.flash.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.html5.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.print.min.js" type="text/javascript"></script>


  <!--Excel HTML5 export button Added by Prashant-->
  <script src="media/dataTables/JSZip-2.5.0/jszip.min.js" type="text/javascript"></script>

  <!--PDF HTML5 export button Added by Prashant-->
  <script src="media/dataTables/pdfmake-0.1.36/pdfmake.min.js" type="text/javascript"></script>
  <script src="media/dataTables/pdfmake-0.1.36/vfs_fonts.js" type="text/javascript"></script>


  <!--Bootstrap Datables FixedHeader Added by Prashant-->
  <link href="media/dataTables/FixedHeader-3.1.4/css/fixedHeader.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/FixedHeader-3.1.4/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
  <script src="media/dataTables/FixedHeader-3.1.4/js/fixedHeader.bootstrap4.min.js" type="text/javascript"></script>
  <!--not ness-->


  <!--Bootstrap Datables RowGroup Added by Prashant-->
  <link href="media/dataTables/RowGroup-1.1.0/css/rowGroup.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/RowGroup-1.1.0/js/dataTables.rowGroup.min.js" type="text/javascript"></script>
  <script src="media/dataTables/RowGroup-1.1.0/js/rowGroup.bootstrap4.min.js" type="text/javascript"></script>
  <!--not ness-->


  <!--Bootstrap Datables Responsive Added by Prashant-->
  <link href="media/dataTables/Responsive-2.2.2/css/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Responsive-2.2.2/js/responsive.bootstrap4.min.js" type="text/javascript"></script>


  <!--Fontawesome Icons---->
  <link rel="stylesheet" href="media/fontawesome/5.10.1/css/all.css" />


  <!---For Bootstrap 3 moment.js dattimepicker---->
  <!-- <link rel="stylesheet" href="media/bootstrap/bootstrap-3.3.7/css/bootstrap.min.css" /> -->
  <script type="text/javascript" src="media/moment-js/moment.js"></script>
  <script type="text/javascript" src="media/bootstrap/bootstrapDateTime/js/bootstrap-datetimepicker.min.js"></script>
  <link rel="stylesheet" href="media/bootstrap/bootstrapDateTime/css/bootstrap-datetimepicker.min.css" />

  <!---Datatables Date sorting plugin----->
  <script type="text/javascript" src="media/moment-js/datetime-moment.js"></script>


  <!--Custome Resources----->
  <script type="text/javascript" src="media/reports/js/machineTestReport.js"></script>
  <link rel="stylesheet" href=media/reports/css/machineTestReport.css />

  <!---New Resources Created by Prashant End--------------------------------------------------------------------------------------->

</head>

<body>
  <html:form action="/MachineTestReport" enctype="multipart/form-data">

    <%-- <bean:define name="MachineTestReportNewFB" property="fromDate" id="frDate" type="java.lang.String" />
<bean:define name="MachineTestReportNewFB" property="toDate" id="tDate" type="java.lang.String" /> --%>

    <%-- <%
 if(frDate==null||frDate.equalsIgnoreCase(""))
    {
     Date date = new Date(System.currentTimeMillis());
     SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
     frDate=formatter.format(date);
    }

 if(tDate==null||tDate.equalsIgnoreCase(""))
    {
     Date date = new Date(System.currentTimeMillis());
     SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
     tDate=formatter.format(date);
    }
%> --%>

    <div class="container-fluid mt-md-2 mb-md-4 bg-white shadow condensed-grid" id="container1">
    <div class="mx-md-0 shadow rounded bg-white no-gutters">
      <div class=" row p-3 p-md-1 align-items-center shadow rounded bg-white no-gutters d-flex justify-content-center">

        <div class="col-md-3.5 mr-md-2 ml-md-1 ">
          <p class="" style="font-weight: bold;font-size: 21px;"><span><img height="42" width="42" src='media/images/medical-history.svg'></span> Machine WorkList </p>
        </div>

        <div class="col-md-2 mr-md-1 mt-md-2" id="labNameDiv">

          <div class="form-group px-0">
            <select class="form-control px-0 input-sm" id="labName">
              <option value="0">Select Lab</option>
            </select>
          </div>

        </div>

        <div class="col-md-2 mr-md-2 mt-md-2 " id="machineNameDiv" style="display:none;">
          <div class="form-group px-0">
            <select class="form-control input-sm px-0 " id="machineName">
              <option value="0">Select Machine</option>
            </select>
          </div>
        </div>

        <div class='inputDates col-md-2 mr-md-2 mt-md-2'>
          <div class="form-group">
            <div class='input-group date' id='datetimepicker-from'>
              <div class="input-group-append">
                <span class="input-group-text px-1">From Date:</span>
              </div>
              <input type='text' class="form-control px-1 fromDateInput input-sm" value="" onkeydown="return false" />
              <span class="input-group-addon mt-2">
                <i class="fas fa-calendar-alt" style="color: #007BFF; font-size:20px;"></i>
              </span>
            </div>
          </div>

          <div class="form-group">
            <div class='input-group date' id='datetimepicker-fromTime'>
              <div class="input-group-append">
                <span class="input-group-text px-1">From Time:</span>
              </div>
              <input type='text' class="form-control px-1 fromTimeInput input-sm" value="" onkeydown="return false" />
              <span class="input-group-addon mt-2">
                <i class="far fa-clock" style="color: #007BFF; font-size:20px;" aria-hidden="true"></i>
              </span>
            </div>
          </div>


        </div>

        <div class='inputDates col-md-2 mr-md-2  mt-md-2'>


          <div class="form-group">
            <div class='input-group date' id='datetimepicker-to'>
              <div class="input-group-append">
                <span class="input-group-text px-1">To Date:</span>
              </div>
              <input type='text' class="form-control px-1 toDateInput " value="" onkeydown="return false" />
              <span class="input-group-addon mt-2">
                <i class="fas fa-calendar-alt" style="color: #007BFF; font-size:20px;"></i>
              </span>
            </div>
          </div>

          <div class="form-group">
            <div class='input-group date' id='datetimepicker-toTime'>
              <div class="input-group-append">
                <span class="input-group-text px-1">To Time:</span>
              </div>
              <input type='text' class="form-control px-1 toTimeInput " value="" onkeydown="return false" />
              <span class="input-group-addon mt-2">
                <i class="far fa-clock" style="color: #007BFF; font-size:20px;" aria-hidden="true"></i>
              </span>
            </div>
          </div>
        </div>

        <div class="col-md-1 p-md-1 ">
          <button id="getData" type="button" class="btn btn-warning "><i class="text-nowrap">Get Data</i></button>

          <button id="getingData" class="btn btn-primary text-nowrap" style="display: none;" disabled>
            <span class="spinner-border spinner-border-sm"></span> Loading..
          </button>
        </div>

      </div>

      <div class=" row p-3 p-md-1  rounded bg-white no-gutters align-items-center  d-flex justify-content-center">

		<div class="mx-md-3 mt-2">
  			<label class="">Accepted By</label>
		</div>

 		<!-- option Accepted By All Users -->
		<div class="mx-md-3 mt-0 custom-control custom-radio">
  			<input type="radio" class="custom-control-input acceptedByUser" id="acceptedByUserA" name="acceptedByUser" value="0" checked>
  			<label class="custom-control-label" for="acceptedByUserA">All</label>
		</div>

		<!--option Accepted By loged in Users -->
		<div class="mx-md-3 mt-0 custom-control custom-radio">
  			<input type="radio" class="custom-control-input acceptedByUser" id="acceptedByUserM" name="acceptedByUser" value="1">
  			<label class="custom-control-label" for="acceptedByUserM">Accepted By Me</label>
		</div>

      </div>
      </div>

    </div>



    <div id="machineTestReportList" class="container-fluid mt-md-0 mb-md-4 bg-white " style="display: none;">

      <table id="DataTable1" class="table table-sm table-condensed table-striped table-bordered table-hover dt-responsive nowrap mx-md-0 shadow rounded bg-white" style="width:100%">
        <thead>
          <tr>
            <th>
              <i class="fas fa-chevron-circle-up text-danger d-none collapseButton"></i>
              <i class="fas fa-chevron-circle-down text-success expandButton"></i>
              S.No
            </th>
            <th>Cr. No.</th>
            <th>Sample No.</th>
            <th>Lab No.</th>
            <th>Test Name</th>
            <th>Group Name</th>
            <th>Sample Name</th>
            <th>Collection Date/Time</th>
            <th>Acceptance Date/Time</th>
            <th>Patient Name</th>
            <th>Age/Gender</th>


          </tr>
        </thead>
        <tbody>
        </tbody>

      </table>
    </div>


  </html:form>
</body>

</html>
