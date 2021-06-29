<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">    
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="media/assets/img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Online Requisition Raising(Patient Based Labs)</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
	
<script src="media/bootstrap4/js/bootstrap.min.js"></script>
<script src="media/bootstrap4/js/popper.min.js"></script>
<script src="media/bootstrap4/js/jquery.min.js"></script>

    <!-- Bootstrap core CSS     -->
    <link href="media/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="media/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="media/assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="media/assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
    
    <link rel="stylesheet" href="media/bootstrap4/css/jquery.calendar.css" />
    <link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });

});



</script>

<style type="text/css">
.anyClass {
  height:130px;
  overflow-y: scroll;
}

.centerClass{
  height:400px;
  overflow-y: scroll;
}

.btn-circle {
    width: 30px;
    height: 30px;
    padding: 6px 0px;
    border-radius: 15px;
    text-align: center;
    font-size: 12px;
    line-height: 1.42857;
}


/* .fade-scale {
  transform: scale(0);
  opacity: 0;
  -webkit-transition: all .25s linear;
  -o-transition: all .25s linear;
  transition: all .25s linear;
}

.fade-scale.in {
  opacity: 1;
  transform: scale(1);
} */

/* .bd-labWiseTestModal-modal-lg .modal-content{
	background-color: #e6ecff;
} */
.bd-labWiseTestModal-modal-lg .modal-header{
	background-color: #9999ff;
	color: white;
}

.bd-labWiseTestModal-modal-lg .modal-body #testList{
	font-weight: bold;
	font-size: 10;
}

</style>

</head>
<body>

<div class="wrapper">
    <!-- <div class="sidebar" data-color="purple" data-image="media/assets/img/sidebar-4.jpg"> -->
    <div class="sidebar" data-color="blue" data-image="">

    <!--

        Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
        Tip 2: you can also add an image using data-image tag

    -->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <span class="simple-text">
                  <font color="#ffffff" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
											<strong> Online Requisition Raising </strong></font>
                </span>
            </div>

            <ul class="nav">
            	<li class="active">
                   <a href="" data-toggle="modal" data-target=".bd-labWiseTestModal-modal-lg">
                        <!-- <i class="pe-7s-graph"></i> -->
                        <p>Radiology</p>
                    </a> 
                </li>
                <li class="notActive">
                   <a href="">
                        <!-- <i class="pe-7s-graph"></i> -->
                        <p>Non Invasive Lab</p>
                    </a> 
                </li>
                <li class="notActive">
                   <a href="">
                        <!-- <i class="pe-7s-graph"></i> -->
                        <p>Fluroscopy</p>
                    </a> 
                </li>
                <li class="notActive">
                   <a href="">
                        <!-- <i class="pe-7s-graph"></i> -->
                        <p>Mammography</p>
                    </a> 
                </li>
                <li class="notActive">
                   <a href="">
                        <!-- <i class="pe-7s-graph"></i> -->
                        <p>Physiology</p>
                    </a> 
                </li>
			</ul>
    	</div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                 <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"></a>
                </div> 
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="javascript:loadDatatable()" data-toggle="tooltip" title="Previous Requisition Details">
                                <i class="fa fa-history" data-toggle="modal" data-target=".bd-prevReqModal-modal-lg"></i>
                            </a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <!-- <li>
                           <a href="">
                               <img src=" data:image/png;base64,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" 
      					class="rounded-circle" alt="Cinque Terre" width="25" height="25"> Patient Profile
                            </a>
                        </li> -->
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <p>
										<img src=" data:image/png;base64,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" 
      					class="rounded-circle" alt="Cinque Terre" width="25" height="25">Patient Profile
										<b class="caret"></b>
									</p>

                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Patient Details</a></li>
                                <li><a href="#">Admission Details</a></li>
                                <li><a href="#">Upload Requisition Form</a></li>
                                <li><a href="#">Manage Bookmark</a></li>
                                <li><a href="#">Test/group code list</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Previous Requisition Details</a></li>
                              </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="content">
       
            <div class="container-fluid">
                <div class="row">
						<div class="row">
						<!-- START : Different Page Logic -->
						<!-- Page 1 -->
                    <div class="col-md-9" style="display: none;">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Edit Profile</h4>
                            </div>
                            <div class="content">
                                <form>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Company (disabled)</label>
                                                <input type="text" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Username</label>
                                                <input type="text" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Email address</label>
                                                <input type="email" class="form-control" placeholder="Email">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>First Name</label>
                                                <input type="text" class="form-control" placeholder="Company" value="Mike">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Last Name</label>
                                                <input type="text" class="form-control" placeholder="Last Name" value="Andrew">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input type="text" class="form-control" placeholder="Home Address" value="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>City</label>
                                                <input type="text" class="form-control" placeholder="City" value="Mike">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Country</label>
                                                <input type="text" class="form-control" placeholder="Country" value="Andrew">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Postal Code</label>
                                                <input type="number" class="form-control" placeholder="ZIP Code">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>About Me</label>
                                                <textarea rows="5" class="form-control" placeholder="Here can be your description" value="Mike">Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-info btn-fill pull-right">Update Profile</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Page 2 -->
                    <div class="col-md-9">
                        <div class="card">
                            <div class="header">
                                <label>Test / Group Search</label>
                                <input type="text" class="form-control"  placeholder="Ex: Fasting Gluscose" value="">
                            </div>
                            <div class="content table-responsive table-full-width ">
                            	<div class="centerClass">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>S.No.</th>
                                    	<th>Test</th>
                                    	<th>Laboratory</th>
                                    	<th>Sample/Site</th>
                                    	<th>Appointment</th>
                                    	<th>Priority</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        	<td>1</td>
                                        	<td>Chloride</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                        
                                        <tr>
                                        	<td>2</td>
                                        	<td>Total Bilirubin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                        <tr>
                                        	<td>3</td>
                                        	<td>A/G Ratio</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                        <tr>
                                        	<td>4</td>
                                        	<td>Indirect Bilirubin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                         <tr>
                                        	<td>5</td>
                                        	<td>Albumin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                         <tr>
                                        	<td>6</td>
                                        	<td>Globulin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                         <tr>
                                        	<td>7</td>
                                        	<td>Urea</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                         <tr>
                                        	<td>8</td>
                                        	<td>Creatinine</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                         <tr>
                                        	<td>9</td>
                                        	<td>Fasting glucose</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td><i class="pe-7s-date" data-toggle="modal" data-target="#myModal"></i></td>
                                        	<td>Routine</td>
                                        </tr>
                                </table>
								</div>
								<div class="">
								<!-- dskfjhd
								dkfnsd
								df -->
								</div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <!-- END : Different Page Logic -->
                    <div class="col-md-3 card">
                    	<div class="card-header">
                    		<!-- <div class="card"> -->
                            	<div class="header">
                                	<h4 class="title">Billing Information</h4>
                            	</div>
                            	<div class="content">
                                	Amount to be paid :
                            	</div>
                        	<!-- </div> -->
                    	</div>
                    	<div class="card-body">
                        	<div class="card">
                        		<div class="header">
                                	<h4 class="title">Bookmarks</h4>
                            	</div>
                            	<div class="content">
                            		<div class="card">
                            			<input class="form-control" id="myInput" type="text" placeholder="Search..">
                            			
                            			<div class="content anyClass">
		                            		<table>
		                              			<tbody id="myTable">
		                              				<tr><td><a href="#">MRI Brain plain</a></td></tr>
		                                			<tr><td><a href="#">Complete Blood Count</a></td></tr>
		                                			<tr><td><a href="#">Fasting Glucose</a></td></tr>
		                               	 			<tr><td><a href="#">CECT Upper Abdomen</a></td></tr>
		                                			<tr><td><a href="#">Behaviour observation audiometry</a></td></tr>
		                                			<tr><td><a href="#">Immittance Evaluation</a></td></tr>
		                               			</tbody>
		                              		</table>
                            			</div>
                            		</div>
                            	</div>
                        	</div>
                        </div>
                        <div class="card-footer">
                        	<div class="card">
                        		<div class="header">
                                	<h4 class="title">Other Information</h4>
                            	</div>
                            	<div class="content">
                            		<!-- <div class="card">
                            			<div class="header">
                            				<input class="form-control" id="myInput" type="text" placeholder="Search..">		
                            			</div>
                            			<div class="content">
		                            		<table>
		                              			<tbody id="myTable">
		                              				<tr><td><a href="#">MRI Brain plain</a></td></tr>
		                                			<tr><td><a href="#">Complete Blood Count</a></td></tr>
		                                			<tr><td><a href="#">Fasting Glucose</a></td></tr>
		                               	 			<tr><td><a href="#">CECT Upper Abdomen</a></td></tr>
		                                			<tr><td><a href="#">Behaviour observation audiometry</a></td></tr>
		                                			<tr><td><a href="#">Immittance Evaluation</a></td></tr>
		                               			</tbody>
		                              		</table>
		                              		Empty block, needs to discuss
                            			</div>
                            		</div> -->
                            		Empty block, needs to discuss
                            	</div>
                        	</div>
                        </div> 
                    </div>

                </div>
                </div>
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-right">
                    <ul>
                    	<li class="btn btn-fill btn-primary">Save</li>
                        <li class="btn btn-fill btn-danger">Cancel</li>
                            

                    </ul>
                </nav>
                <!-- <p class="copyright pull-right">
                    &copy; <script>document.write(new Date().getFullYear())</script> <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
                </p> -->
            </div>
        </footer>

    </div>
</div>

<!-- <div class="row nav">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Additional Information</h4>
                            </div>
                            <div class="content">
                                <form>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Advised By</label>
                                                <input type="text" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                    </div>
									<div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Indications</label>
                                                <textarea rows="2" class="form-control" placeholder="Here can be your patient indications..." value="Mike"></textarea>
                                            </div>
                                        </div>
                                    </div>
								</form>
                            </div>
                        </div>
                        </div> -->


	
    <!--   Core JS Files   -->
    <script src="media/assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
	<script src="media/assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Charts Plugin -->
	<script src="media/assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="media/assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="media/assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="media/assets/js/demo.js"></script>
		<script src="media/bootstrap4/js/jquery-3.3.1.slim.min.js"></script>
		<script src="media/bootstrap4/js/jquery.calendar.js"></script>
		
		
		<!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <!-- <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div> -->
        
        <!-- Modal body -->
        <div class="modal-body">
         
			<div id="pnlEventCalendar" style="width:100%;"></div>
			
			<script>
			$(function () {
				$('#pnlEventCalendar').calendar({months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
					days: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],onSelect: function (event) {
						$('#lblEventCalendar').text(event.label);
				}});
			});
			</script>
		
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        	<table width="100%">
        		<tbody>
        			<tr>
        				<td width="60%"><p>Appointment date: <b><span id="lblEventCalendar">[date]</span>&nbsp; <b style="color: Blue">Free</b></b></p></td>
        				<td width="40%"><button type="button" class="btn btn-danger" data-dismiss="modal">Close</button></td>
        			</tr>
        		</tbody>
        	</table>
          
          
        </div>
        
      </div>
    </div>
  </div>
  <script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>

  <script>
  function loadDatatable(){
	 	$('#prevRequisitionDtl').DataTable();
	}
  </script>
  <div class="modal bd-prevReqModal-modal-lg" id="prevReqModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Previous Requisition Details</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div> 
        
        <!-- Modal body -->
        <div class="modal-body">
         	<table class="table table-hover" id="prevRequisitionDtl">
                                    <thead>
                                    <tr>
                                        <th>S.No.</th>
                                    	<th>Test</th>
                                    	<th>Laboratory</th>
                                    	<th>Sample Name</th>
                                    	<th>Status</th>
                                    	<th>Priority</th>
                                    	<th>Requisition Date</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        	<td>1</td>
                                        	<td>Chloride</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td>Sample Collected</td>
                                        	<td>Routine</td>
                                        	<td>03-Apr-2019 02:26:03</td>
                                        </tr>
                                        
                                        <tr>
                                        	<td>2</td>
                                        	<td>Total Bilirubin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td>Sample Accepted</td>
                                        	<td>Routine</td>
                                        	<td>02-Apr-2019 18:20:03</td>
                                        </tr>
                                        <tr>
                                        	<td>3</td>
                                        	<td>A/G Ratio</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td>Requisition Raised</td>
                                        	<td>Routine</td>
                                        	<td>26-Mar-2019 15:02:53</td>
                                        </tr>
                                        <tr>
                                        	<td>4</td>
                                        	<td>Indirect Bilirubin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td>Machine Processing</td>
                                        	<td>Routine</td>
                                        	<td>02-Apr-2019 08:47:58</td>
                                        </tr>
                                         <tr>
                                        	<td>5</td>
                                        	<td>Albumin</td>
                                        	<td>Biochemistry</td>
                                        	<td>Serum</td>
                                        	<td>Report Generated</td>
                                        	<td>Urgent</td>
                                        	<td>01-Apr-2019 10:33:52</td>
                                        </tr>
                                        
                                </table>
		</div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <div class="modal fade bd-labWiseTestModal-modal-lg" id="labWiseTestModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Interventional Radiology Test List</h4>
            <!-- <button class="btn btn-warning btn-fill btn-sm btn-circle" data-toggle="tooltip" title="Switch user view"> <i class="fa fa-exchange"></i></button>
          	<button class="btn btn-danger btn-fill btn-sm btn-circle" data-dismiss="modal" data-toggle="tooltip" title="Close"><i class="fa fa-remove"></i></button> -->
        </div> 
        
        <!-- Modal body -->
        <div class="modal-body">
         	<table id="testList" class="">
         		<tbody>
         			<tr>
         				<td>
         				<input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
     					ABDOMINAL DSA (RI35)(INR 0 )
     					</td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				AORTIC STENTING (RI57)(INR 0 )</td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				AORTOGRAM (RI27)(INR 0 )</td>
         			</tr>
         			<tr>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				BALLOON TEST OCCLUSION (RI18)(INR 0 )</td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				CEREBRAL DSA (RI26)(INR 0 ) </td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				HEPATIC VENOUS ANGIOPLASTY (RI51)(INR 0 ) </td>
         			</tr>
         			<tr>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				LOWER LIMB DSA (RI34)(INR 0 )</td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				INTRACRANIAL SPASMOLYSIS (RI25)(INR 0 )</td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				DURAL SINUS ANGIOPLASTY WITH STENTING (RI74)(INR 0 )</td>
         			</tr>
         			<tr>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				CT GUIDED ABSCESS DRAINAGE (RI71)(INR 0 )</td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				CAROTID ANGIOPLASTY WITH STENTING (RI19)(INR 0 ) </td>
         				<td><input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
         				CT GUIDED BIOPSY (RI55)(INR 0 )</td>
         			</tr>
         		</tbody>
         	</table>
		</div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        	<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
		
		
	
	
		
	</body>
	
</html>
    