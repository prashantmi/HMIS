<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head>


   <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
  <style>

.social_icon span{
font-size: 40px;
margin-left: 10px;
color: black;

}

.social_icon{
position: absolute;
left: 20px;
top: -35px;
}


</style>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
 <script type="text/javascript"></script>

      
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    
      
    
     <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
            

<!-- <link href="../css/transaction.css" rel="stylesheet" type="text/css">
 --><link href="../../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet"
	type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">

<script  src="../../masterutil/js/master.js"></script>
<script  src="../../hisglobal/js/tab.js"></script>
<script  src="../../hisglobal/js/calendar.js"></script>
<script  src="../../hisglobal/js/validationBootstrap.js"></script>
<script  src="../../hisglobal/js/multirow.js"></script>
<script  src="../../ipd/js/patientAdmissionBS.js"></script>
<script  src="../../hisglobal/js/util.js"></script>
<script src="../../ipd/js/patientOccupationDetail.js"></script>
<script  src="../js/patientListing.js"></script>
<script  src="../../ipd/js/jquery.js"></script>
<script  src="../../ipd/js/jquery.simplemodal.js"></script>


<script >
	//window.history.forward();


	
	function test() {
		document.getElementsByName("strStreet")[0].focus();
	}

	function view1(obj1, obj2, obj3) {
		document.getElementById(obj1).style.display = "none";
		document.getElementById(obj2).style.display = "";
		document.getElementById(obj3).style.display = "";
	}
	function view2(obj1, obj2, obj3) {
		document.getElementById(obj1).style.display = "";
		document.getElementById(obj2).style.display = "none";
		document.getElementById(obj3).style.display = "none";
	}

	function getAdmissionCharges() {

		var hmode = "CHARGEVALUE";
		var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&wardCode='
				+ document.forms[0].strWardCode.value + '&treatmentCategCode='
				+ document.forms[0].strTreatmentCategoryCode.value;
		ajaxFunction(url, "6");
	}

	var beforePrint = function() {
		showPrintableSlip();
	};
	var afterPrint = function() {
		hidePrintableSlip();
	};
	/*if (window.matchMedia) 
	 {
	 var mediaQueryList = window.matchMedia('print');
	 mediaQueryList.addListener
	 (
	 function(mql) 
	 {
	 if (mql.matches) 
	 {
	 beforePrint();
	 } 
	 else 
	 {
	 afterPrint();
	 }
	 }
	 );
	 }*/

	window.onbeforeprint = beforePrint;
	window.onafterprint = afterPrint;
	

	

</script>

<style type="text/css">

.modal-backdrop.show {
    opacity: 0;
}
nav, nav .nav-wrapper i, nav a.sidenav-trigger, nav a.sidenav-trigger i {
    height: 35px;
    line-height: 35px;
}
nav .brand-logo {
   
    font-size: 1.5rem;
}
input:not([type]), input[type="text"]:not(.browser-default), input[type="password"]:not(.browser-default), input[type="email"]:not(.browser-default), input[type="url"]:not(.browser-default), input[type="time"]:not(.browser-default), input[type="date"]:not(.browser-default), input[type="datetime"]:not(.browser-default), input[type="datetime-local"]:not(.browser-default), input[type="tel"]:not(.browser-default), input[type="number"]:not(.browser-default), input[type="search"]:not(.browser-default), textarea.materialize-textarea {
  
    height: 2.3rem;
   
}
select {
   
    height: 2.3rem;
}
nav {
background-color:  #26a69a;
}

.row {
   
    margin-bottom: 0px;
}
</style>

</head>
<body onload="viewA();openPrintPopUp();onchangeCountry();checkMsg();" style="background-color: aliceblue"
	onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">
	<html:form action="/transactions/PatientAdmissionTransBSCNT"
		method="post">
		
			<div id="nonPrintable" style="">
				<div class="alert alert-danger" id="errID" style="display: none;"><bean:write name="paientAdmissionTransBean" property="strMsgString" /></div>
				<div class="alert alert-success" id="normalMsg" style="display: none;"><bean:write name="paientAdmissionTransBean" property="strMsg" filter="false" /></div>
				<div class="alert alert-warning" id="wrnID" style="display: none;"><bean:write name="paientAdmissionTransBean" property="strWarningMsg" /></div>
				<%--<tag:tab tabLabel="Patient Admission " selectedTab="FIRST" align ="center" width ="TABLEWIDTH" onlyTabIndexing="0"></tag:tab>--%>
				<!-- <table class="TABLEWIDTH" align="center" cellspacing="0px">
					<tr class="HEADER">
						<td><div align="left">Patient Admission</div></td>
						<td>
							<div align="right">
								<img style="cursor: pointer" name="printLastButton"
									src="../../hisglobal/images/print_on.gif"
									onclick="printLastBill();" title="Print Last Bill">
							</div>
						</td>
					</tr>
				</table> -->
		
						  <!-- 
							  <div class="card ">
											<div class="card-header" style="padding: .1rem 1.25rem;">
												<a href="" class="float-right btn btn-outline-primary mt-1" onclick="printLastBill();" style="padding: .175rem .35rem; line-height: 0.8">
												<i class="fa fa-print" aria-hidden="true" title="Print Last Bill"></i></a>
												<h4 class="card-title ">Patient Admission </h4>
												
											</div>
											<div class="card-body NEW" id="crNoId"> -->
											
												<%-- <div class="form-row"  id='goBox'>
													<div class="col-sm-3 form-group" >
														<font color="red" id="mandCRId" >*</font><label >CR No.</label>
														   
														<div id="patientCrEdId">
														<crNo:crNo id="strCrNoId" value="${paientAdmissionTransBean.strCrNo}" js="onkeypress='return goRetFuncNA(event);return validateData(event,5);'" className='form-control'></crNo:crNo>
														  </div>
						 							</div> <!-- form-group end.// -->
													<div class="col-sm-3 form-group mt-4" style="margin-top: 2rem !important;">
																											
						                             <span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' onclick="showPatientListingWindow('4',document.forms[0].strCrNo,'setSelectedCrNo');"></span>
													 <a href="#" class="btn btn-xs btn-success" onclick="goFuncNA();"  style="border-radius: .5rem;" data-toggle="modal" data-target="#validateModal">
						                             	<i class="fas fa-forward" aria-hidden="true" ></i>Go
						                             </a>
						                             
													</div>
													<div class="col-sm-6 form-group"></div>
									           </div>	
									  			    --%>
									  			                            <div class="container" style="width: 90%">
									  			                            
									  			                            
																		    <div class="col-sm-12">
																		    <br>
																		    <div class="card">
																		   <div class="card-header">
																						<div class="d-flex justify-content-end social_icon" align="left">
																							<span><button type="button" class="btn btn-primary btn-lg" style="font-size:1.2rem ">Patient Admission</button></span>
																						</div>
																		    
																		       <div class="card-image">
																		          <a class="btn-floating halfway-fab waves-effect waves-light primary" style="top: -30px;"><i class="material-icons" title="Print Last Bill">print</i></a>
																		        </div>
																		         
																		        <div class="card-body" style="padding-bottom: 0;">
																		       <!--   <span class="card-title activator grey-text text-darken-4">Patient Admission</span> -->
																			      <div class="row" id="goBox">
																			      
																			        <div class="input-field col s2">
																			        <!--   <input id="strCrNoId" type="text" class="validate" > -->
		                                                                         <crNo:crNo id="strCrNoId" value="${paientAdmissionTransBean.strCrNo}" js="onkeypress='return goRetFuncNA(event);return validateData(event,5);'" className='form-control'></crNo:crNo>
																			          
																			          <label for="last_name">CR No.</label>
																			        </div>
																			         <div class="input-field col s3" >
																			         <a class="btn btn-xs btn-success" onclick="goFuncNA();" data-toggle="modal" data-target="#validateModal"><i class="material-icons">send</i></a>
																			        <!-- <button class="btn waves-effect waves-light " type="submit" name="action">Go
																								    <i class="material-icons right">send</i>
																								  </button> -->
																			        </div>
																			         <div class="input-field col s7">
																			         
																			           </div>
																		          </div>
																		
																		
																		
																		
																		<!--Accordion wrapper-->

																		
																		
																		
			<div class="" id="patDtlID" style="display: none;">
					<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
						<tr class="HEADER">
							<td colspan="4">
								<div id="plusPatDtl" align="left">
									<img src="../../hisglobal/images/plus.gif" onClick="viewX();"
										style="cursor: pointer;" />&nbsp;&nbsp; CR No:&nbsp;<a
										id="patientCrId1"></a> &nbsp;&nbsp;&nbsp;&nbsp;Patient
									Name&nbsp;:&nbsp;<label id="patName1"></label>
								</div>
								<div id="minusPatDtl" style="display: none;" align="left">
									<img src="../../hisglobal/images/minus.gif" onClick="viewY();"
										style="cursor: pointer;" />&nbsp;&nbsp; CR No:&nbsp;<a
										id="patientCrId2" style="display: none;"></a>
									&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label
										id="patName2"></label>
								</div>
							</td>
						</tr>
					</table> -->
					<nav class="nav nav-pills flex-column"  style="display: none;">
				       <div id="plusPatDtl" align="left">
									<img src="../../hisglobal/images/plus.gif" onClick="viewX();"
										style="cursor: pointer;" />&nbsp;&nbsp; CR No:&nbsp;<a
										id="patientCrId1"></a> &nbsp;&nbsp;&nbsp;&nbsp;Patient
									Name&nbsp;:&nbsp;<label id="patName1"></label>
								</div>
								<div id="minusPatDtl" style="display: none;" align="left">
									<img src="../../hisglobal/images/minus.gif" onClick="viewY();"
										style="cursor: pointer;" />&nbsp;&nbsp; CR No:&nbsp;<a
										id="patientCrId2" style="display: none;"></a>
									&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label
										id="patName2"></label>
								</div>
						</nav>	
						<!-- <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
                           <a class="navbar-brand" href="#">Logo</a>
                         <ul class="navbar-nav">
                              <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                              </li>
                              <li class="nav-item">
                          <a class="nav-link" href="#">Link</a>
                       </li>
                           </ul>
                                </nav> -->
				</div>
				
				
				
                
                 <div id="id4">
                       
						<pDtl:patDtlNew crNo="${paientAdmissionTransBean.strCrNo}" address="false"></pDtl:patDtlNew>
				  </div>
			      	<div class="" id="newModificationId" style="display: none">
	                   <!--  <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 1.5">
                         <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem;" style="">Patient Modification Details </a>
				        </div>
				        </nav> -->
				                                                <nav>
															    <div class="nav-wrapper">
															      <a class="brand-logo" style="font-size: 1.2rem">Patient Modification Details</a>
															      
															    </div>
															  </nav>
															 
 <!-- 				  <a href="#" class="nav-item nav-link active" style="background-color: #326faa;">Patient Modification Details </a>  -->				           
				
							<!-- <div id="plusAddModiId" align="left">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view3();" style="cursor: pointer; cursor: hand"/>
						New Modification
					</div>
					<div id="minusAddModiId" style="display:none;" align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view4();" style="cursor: pointer; cursor: hand"/>
								New Modification
					</div> --> 
				</div>
				<div id="newAddressModiId">
					<bean:write name="paientAdmissionTransBean"
						property="strAddressModi" filter="false" />
				</div>
				<%-- <div id="id5" class="container" style="display: none">
					<table class="TABLEWIDTH" align="center" cellspacing="1px">
						<tr>
							<td colspan="4" class="TITLE">New Born Baby Status</td>
						</tr>
						<tr>
							<td width="25%" class="LABEL" colspan="1">Mother Name</td>
							<td width="25%" class="CONTROL" colspan="3"><bean:write
									name="paientAdmissionTransBean" property="strMotherName"
									filter="false" /></td>
						</tr>
						<tr>
							<td width="25%" class="LABEL">Mother CR No.</td>
							<td width="25%" class="CONTROL"><bean:write
									name="paientAdmissionTransBean" property="strMotherCrNo"
									filter="false" /></td>
							<td width="25%" class="LABEL">Mother Admission No.</td>
							<td width="25%" class="CONTROL"><bean:write
									name="paientAdmissionTransBean" property="strMotherAdmissionNo"
									filter="false" /></td>
						</tr>
						<tr>
							<td width="25%" class="LABEL">Mother Nationality</td>
							<td width="25%" class="CONTROL"><bean:write
									name="paientAdmissionTransBean" property="strMotherNationality"
									filter="false" /></td>
							<td width="25%" class="LABEL">Mother Religion</td>
							<td width="25%" class="CONTROL"><bean:write
									name="paientAdmissionTransBean" property="strMotherReligion"
									filter="false" /></td>
						</tr>
					</table> 
					</div> --%>
				<div id="id5" class="" style="display: none">
				
				      
				       <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="line-height: 0.8; padding: .1rem 1rem;">
				         <ul class="navbar-nav">
							    <li class="nav-item active">
							      <a class="nav-link" href="#">New Born Baby Status</a>
							    </li>
							    
							  </ul>
				         </nav>
							
							   <div class="form-group row">
								  <div class="col-sm-2"></div>
								    <div class="col-sm-4">
								    <label >Mother Name</label>
								    <bean:write name="paientAdmissionTransBean" property="strMotherName" filter="false" />
								    </div> 
								   <div class="col-sm-4">
								    <label ></label>
								    </div>
								   </div>
								  <div class="form-group row">
								  <div class="col-sm-2"></div>
								    <div class="col-sm-4">
								    <label >Mother CR No.</label>
                                      <bean:write name="paientAdmissionTransBean" property="strMotherCrNo" 	filter="false" />							
                              	    </div>
								   <div class="col-sm-4">
								    <label >Mother Admission No.</label>
								    <bean:write name="paientAdmissionTransBean" property="strMotherAdmissionNo" filter="false" />
								    </div>
								   </div>
								     <div class="form-group row">
								  <div class="col-sm-2"></div>
								    <div class="col-sm-4">
								    <label >Mother Nationality</label>
                                        <bean:write name="paientAdmissionTransBean" property="strMotherNationality" filter="false" />        
                      	                </div>
								   <div class="col-sm-4">
								    <label >Mother Religion</label>
								    <bean:write name="paientAdmissionTransBean" property="strMotherReligion" filter="false" />
								    </div>
								   </div>
			             </div>
			     
			     <%--     <table class="table">			
						   
				         
				         <tbody>
				         <tr>
				        <!--  <nav class="nav nav-pills flex-column" style="line-height: 0.8;">				               
				              <a href="#" class="nav-item nav-link active"> New Born Baby Status </a>
				         </nav> -->
				         <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="line-height: 0.8; padding: .1rem 1rem;">
				         <ul class="navbar-nav">
							    <li class="nav-item active">
							      <a class="nav-link" href="#">New Born Baby Status</a>
							    </li>
					     </ul>
				         </nav>
				         </tr>
						    <tr>
						      <th scope="row" style="padding: .35rem; border-top: 1px solid #fafcfe;"></th>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;">Mother Name</td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;"><bean:write name="paientAdmissionTransBean" property="strMotherName" filter="false" /></td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;"></td>
						      
						    </tr>
						    <tr>
						      <th scope="row" style="padding: .35rem; border-top: 1px solid #fafcfe;"></th>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;">Mother CR No.</td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;"><bean:write name="paientAdmissionTransBean" property="strMotherCrNo" 	filter="false" /></td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;">Mother Admission No.</td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;"><bean:write name="paientAdmissionTransBean" property="strMotherAdmissionNo" filter="false" /></td>
						    
						    </tr>
						    <tr>
						      <th scope="row" style="padding: .35rem; border-top: 1px solid #fafcfe;"></th>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;">Mother Nationality</td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;"><bean:write name="paientAdmissionTransBean" property="strMotherNationality" filter="false" /></td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;">Mother Religion</td>
						      <td style="padding: .35rem; border-top: 1px solid #fafcfe;"><bean:write name="paientAdmissionTransBean" property="strMotherReligion" filter="false" /></td>
						      
						    </tr>
						  </tbody>
				      </table> --%>
			   
				
								

        	
				<div id="PatientOccId"></div>
				<div id="PatientOccDtl" style="display: none">
					
						                 
						                   <ul class="collapsible Accordion">
											    <li>
											      <div class="collapsible-header" style="font-size: 1.2rem;padding: .5rem 1.25rem; line-height: 0.5"><i class="material-icons">expand_more</i></div>
											      <div class="collapsible-body"><span> <bean:write name="paientAdmissionTransBean" property="occupationDetailValues" filter="false" /></span></div>
											    </li>
											   
											  </ul>
												       
												     
						                
				</div>

				<div class="" id="wardDivId" style="display: none;" >
				
				       <!--   <ul class="nav nav-pills nav-justified">
						    <li class="nav-item" >
						      <a href="#" class="nav-item nav-link active text-left" >Department Ward Details </a>
						    </li>
						    <li class="nav-item" >
						      <a href="#" class="nav-item nav-link active text-right" id='bedStatusDiv' >
						      <i class="fa fa-bed" aria-hidden="true" data-toggle="modal" data-target="#myModal" id='modellink' onClick='bedDetails();'></i>
						    	Bed Status</a>
						      </li>
						  </ul> -->
						  
	                   <!--  <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 1.1">
				        <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem;" style="">Department Ward Details</a>
				        </div>
				          <ul class="navbar-nav ml-auto">
				                    <li><a href="#" class="nav-item nav-link active text-right" id='bedStatusDiv' >
						               <i class="fa fa-bed" aria-hidden="true" data-toggle="modal" data-target="#myModal" id='modellink' onClick='bedDetails();'></i>
						    	        Bed Status</a></li>
                         </ul>						      
				         </nav> -->
				         
					                                     <nav>
															    <div class="nav-wrapper">
															      <a class="brand-logo" style="font-size: 1.2rem">Department Ward Details</a>
															      
															    </div>
															  </nav>
					
					<div id="DeptUnitDivId">
						<bean:write name="paientAdmissionTransBean"
							property="strWardBedModi" filter="false" />
					</div>

					<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
						<tr style="display: none;">
							<td width="25%" colspan="1" class="LABEL">Remarks</td>
							<td width="25%" colspan="3" class="CONTROL"><textarea
									rows="3" cols="25" name="strRemarks"></textarea></td>
						</tr>
					</table> -->
					
				</div>
				
				<div id="csno">
					
			</div>	
				
				
				<div class="" id="emrgencyDivId" style="display: none" >
				         <!-- <nav class="nav nav-pills flex-column" style="line-height: 0.8;">				               
				              <a href="#" class="nav-item nav-link active"><i class="fas fa-ambulance"></i>Emergency Contact Details </a>
				         </nav> -->
			           <%--  <div id="accordion1" >
	                      <div class='card font' style="max-width: 1230px;">
	                       <!--  <div class='card-header' style="padding: .5rem 1.25rem;">
	                          <a class='card-link' data-toggle='collapse' href='#collapseOne1'><i class='fas fa-angle-down rotate-icon' align='Right'></i></a>
	                        </div> -->
	                         <div class='card-header' style="padding: .5rem 1.25rem; background-color: #086ea5; line-height: 0.5">
	                          <a class='card-link' data-toggle='collapse' href='#collapseOne1'><i class='fas fa-ambulance'></i></a>
	                          <label style="color: white;">Emergency Contact Details</label>
	                        </div>
	                           <div id='collapseOne1' class='collapse' data-parent='#accordion1'> 
	                              <div class='card-body'>             
					                <div id="emgAddressDiv">
						                  <bean:write name="paientAdmissionTransBean"
							              property="strEmgAddress" filter="false" />
					                </div>
				                  </div>
					          </div>
					     </div>
					 </div> --%>
					 
					                                          <ul class="collapsible Accordion" style="color: white;">
															    <li>
															      <div class="collapsible-header" style="background-color: #26a69a;font-size: 1.2rem;padding: .5rem 1.25rem; line-height: 1"><i class='fas fa-ambulance'></i>Emergency Contact Details</div>
															      <div class="collapsible-body"><span> <bean:write name="paientAdmissionTransBean"  property="strEmgAddress" filter="false" /></span></div>
															    </li>
															   
															  </ul>
															  <br>
															  
				                             </div>


				<!--  <div id="admissionId" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td  class="LABEL" width="25%">Admission Charges</td>
					<td  class="CONTROL" width="25%">
					<input type="text" name="strAdmissionChargeValue" 
					value="${paientAdmissionTransBean.strAdmissionChargeValue }" class="txtFldMin"><img src='/AHIMS/hisglobal/images/INR.png'>
					</td>
					<td  class="LABEL" width="25%"></td>
					<td  class="LABEL" width="25%"></td>
			</tr>
		</table>
		</div>-->
		
	
				<%-- <div id="saveid" align="center">
					<table border="0" class="TABLEWIDTH" align="center" cellspacing="0">
						<tr class="FOOTER">
							<td><div align='left'>
									<font size="2" color="red">**</font> Fields are mandatory if
									I/II Person Name is entered
								</div></td>
							<td><font size="2" color="red">*</font> Mandatory Fields</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><logic:notEmpty
									name="paientAdmissionTransBean" property="strCrNo">
									<a href="#" class="button" id=""
										onClick="return validate1NA();" tabindex="1"> <span
										class="save">Save</span>
									</a>
									<!-- <img src="../../hisglobal/images/btn-sv.png" onClick="return validate1NA();" style="cursor: pointer; cursor: hand" />-->
								</logic:notEmpty> <!--  <img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer; cursor: hand" /> 
			<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer; cursor: hand;" class="image" />  -->
								<a href="#" class="button" onClick="clearRecord();"><span
									class="clear">Clear</span></a> <a href="#" class="button"
								onclick="cancelFunc();"><span class="cancel">Cancel</span></a></td>
						</tr>
					</table>
					
					<br>
				     <button  type="button" class="btn btn-primary btn-sm"  onClick="return validate1NA();" name="paientAdmissionTransBean" property="strCrNo" >Save</button>					
					 <button  type="button" class="btn btn-primary btn-sm" onclick="cancelFunc();"> Cancel  </button>
					 <button  type="button" class="btn btn-primary btn-sm" onClick="clearRecord();"> Clear </button>
					<br>
				</div> --%>
				 
				
			 
<%-- <div class="container ">--%>
				<div class="modal-container" style="display: none;">
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog modal-lg">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Bed  Status Dashboard</h4>
									<button type="button" onclick="unloadBootstrap();"
										class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body" id="modalSpaceId"></div>
								
							</div>

						</div>
					</div>
				</div>
				
				
				
				<!-- MODAL VALIDATION -->
				
<div id='mainDiv' style='display:none;'>
<div class="fade" id="validateModal"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <div class="modal-body">
           <h5 id='warn'></h5>
           <p id='len'></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>

      </div>
    </div>
  </div>
</div>
</div>
				
<!-- END-->
			<input type="hidden" name="hmode" /> <input type="hidden"
				name="strWardTypeCode"
				value="${paientAdmissionTransBean.strWardTypeCode}" /> <input
				type="hidden" name="strBedTypeCode"
				value="${paientAdmissionTransBean.strBedTypeCode}" /> <input
				type="hidden" name="strWardName"
				value="${paientAdmissionTransBean.strWardName}" /> <input
				type="hidden" name="strRoomTypeCode"
				value="${paientAdmissionTransBean.strRoomTypeCode}" /> <input
				type="hidden" name="strEpisodeCode"
				value="${paientAdmissionTransBean.strEpisodeCode}" /> <input
				type="hidden" name="strVisitNo"
				value="${paientAdmissionTransBean.strVisitNo}" /> <input
				type="hidden" name="strMlcNo"
				value="${paientAdmissionTransBean.strMlcNo}" /> <input
				type="hidden" name="strAdviceAdmNo"
				value="${paientAdmissionTransBean.strAdviceAdmNo}" /> <input
				type="hidden" name="strIsUrban"
				value="${paientAdmissionTransBean.strIsUrban}" /> <input
				type="hidden" name="strBedCode"
				value="${paientAdmissionTransBean.strBedCode}" /> <input
				type="hidden" name="strWard"
				value="${paientAdmissionTransBean.strWard}" /> <input type="hidden"
				name="strRoom" value="${paientAdmissionTransBean.strRoom}" /> <input
				type="hidden" name="strBookingDate"
				value="${paientAdmissionTransBean.strBookingDate}" /> <input
				type="hidden" name="strFlag" value="1" /> <input type="hidden"
				name="strMsApprovalFlag"
				value="${paientAdmissionTransBean.strMsApprovalFlag}" /> <input
				type="hidden" name="strMsApprovalStatus"
				value="${paientAdmissionTransBean.strMsApprovalStatus}" /> <input
				type="hidden" name="strNewBorn"
				value="${paientAdmissionTransBean.strNewBorn}" /> <input
				type="hidden" name="strMotherCrNo"
				value="${paientAdmissionTransBean.strMotherCrNo}" /> <input
				type="hidden" name="strMotherAdmissionNo"
				value="${paientAdmissionTransBean.strMotherAdmissionNo}" /> <input
				type="hidden" name="strMotherNationalityCode"
				value="${paientAdmissionTransBean.strMotherNationalityCode}" /> <input
				type="hidden" name="strMotherReligionCode"
				value="${paientAdmissionTransBean.strMotherReligionCode}" /> <input
				type="hidden" name="strMotherName"
				value="${paientAdmissionTransBean.strMotherName}" /> <input
				type="hidden" name="strAdmissionCharge"
				value="${paientAdmissionTransBean.strAdmissionCharge}" /> <input
				type="hidden" name="strPatStatusCode"
				value="${paientAdmissionTransBean.strPatStatusCode}" /> <input
				type="hidden" name="strAdviceStatus"
				value="${paientAdmissionTransBean.strAdviceStatus}" /> <input
				type="hidden" name="strNoOfFreePass"
				value="${paientAdmissionTransBean.strNoOfFreePass}" /> <input
				type="hidden" name="strFreePassValid"
				value="${paientAdmissionTransBean.strFreePassValid}" /> <input
				type="hidden" name=strDepartmentName
				value="${paientAdmissionTransBean.strDepartmentName}" /> <input
				type="hidden" name=strIsAdmissionOnline
				value="${paientAdmissionTransBean.strIsAdmissionOnline}" /> <input
				type="hidden" name=strPrimaryCategoryCode
				value="${paientAdmissionTransBean.strPrimaryCategoryCode}" /> <input
				type="hidden" name="strAgeUnit"
				value="${paientAdmissionTransBean.strAgeUnit}"> <input
				type="hidden" name="strSexCode"
				value="${paientAdmissionTransBean.strSexCode}"> <input
				type="hidden" name="strAge"
				value="${paientAdmissionTransBean.strAge}"> <input
				type="hidden" name="strFatherNameMandatoryFlag"
				value="${paientAdmissionTransBean.strFatherNameMandatoryFlag}">
			    <input type="hidden" name="strAdmissionMode"
				value="${paientAdmissionTransBean.strAdmissionMode}"> <input
				type="hidden" name="strConsultantName"
				value="${paientAdmissionTransBean.strConsultantName}"> <input
				type="hidden" name="strDeptUnitName"
				value="${paientAdmissionTransBean.strDeptUnitName}"> <input
				type="hidden" name="strHiddenUnit"
				value="${paientAdmissionTransBean.strHiddenUnit}"> <input
				type="hidden" name="strHiddenRoom"
				value="${paientAdmissionTransBean.strHiddenRoom}"> <input
				type="hidden" name=strIsIntegratedWithBilling
				value="${paientAdmissionTransBean.strIsIntegratedWithBilling}" /> <input
				type="hidden" name=strIsAdvanceAmountAtAdmission
				value="${paientAdmissionTransBean.strIsAdvanceAmountAtAdmission}" />
			    <input type="hidden" name=strIsAdvanceAmountAtAdmissionTaken
				value="${paientAdmissionTransBean.strIsAdvanceAmountAtAdmissionTaken}" />
			    <input type="hidden" name="strAdvanceAmountDate"
				value="${paientAdmissionTransBean.strAdvanceAmountDate}"> <input
				type="hidden" name="strAdvanceAmountReceiptNo"
				value="${paientAdmissionTransBean.strAdvanceAmountReceiptNo}">
			    <input type="hidden" name="strAdvanceAmount"
				value="${paientAdmissionTransBean.strAdvanceAmount}"> <input
				type="hidden" name="strSaveFlag"
				value="${paientAdmissionTransBean.strSaveFlag}"> <input
				type="hidden" name="strPatientCrNo"
				value="${paientAdmissionTransBean.strPatientCrNo}"> <input
				type="hidden" name="strAdmissionChargeAtCounter"
				value="${paientAdmissionTransBean.strAdmissionChargeAtCounter}">
			    <input type="hidden" name="strAdmNo"
				value="${paientAdmissionTransBean.strAdmNo}"> <input
				type="hidden" name="gblCRValue" /> <input type="hidden"
				name="strPatIsUnknown"
				value="${paientAdmissionTransBean.strPatIsUnknown}"> <input
				type="hidden" name="strMsAppStatus"
				value="${paientAdmissionTransBean.strMsAppStatus}"> <input
				type="hidden" name="strIcuWardType"
				value="${paientAdmissionTransBean.strIcuWardType}"> <input
				type="hidden" name="strPvtWardType"
				value="${paientAdmissionTransBean.strPvtWardType}"> <input
				type="hidden" name="billcount"
				value="${paientAdmissionTransBean.billcount}">
				
				
									<!-- <div class="card-footer cfooter" id="footerdata" style="padding: .1rem 1.25rem;">
									<div class="form-row">
						 			<div class="col-sm-4 form-group" style="margin-bottom: 0">
						 			</div>
						 			<div class="col-sm-4 form-group" id="save" style="margin-bottom: 0">
						 			</div>
						 			 <div id="saveid" align="center" class="col-sm-4 form-group" style="margin-bottom: 0;display: none">
									     <button  type="button" class="btn btn-success btn-sm"  onClick="return validate1NA();" style="font-size: 1rem" name="paientAdmissionTransBean" property="strCrNo"  data-toggle="modal" data-target="#validateModal">Save<i class="fa fa-save"></i></button>					
										 <button  type="button" class="btn btn-danger btn-sm" onclick="cancelFunc();" style="font-size: 1rem"> Cancel <i class="fa fa-times-circle-o"></i> </button>
									</div>
									<div class="col-sm-4 form-group" align="Right" style="margin-bottom: 0 ; line-height: 2.4"><i class="fa fa-asterisk" aria-hidden="true" style="color: red;font-size: smaller;"></i>Fields Mandatory</div>
						 			</div>
								</div> card.// -->
								
								
							 
				 </div>
				 <div class="card-action" align="Right" style="padding: 0;">
				 <div class="row">
				 <div class="input-field col s7" align="Right" id="saveid1">
				 </div>
				 <div class="input-field col s7" align="Right" id="saveid" style="display: none;">
								<button class="btn waves-effect waves-light" type="submit" name="action" onClick="return validate1NA();" name="paientAdmissionTransBean" property="strCrNo"  data-toggle="modal" data-target="#validateModal">Save<i class="material-icons right"></i> </button>
								<button class="btn waves-effect waves-light" type="submit" name="action" onclick="cancelFunc();">Cancel<i class="material-icons right"></i> </button>
								</div>
				 <div class="input-field col s5"><a style="color: black;"><i class="material-icons" style="color: red;font-size: medium;">star</i>Fields Mandatory</a></div>
				 </div>
				 </div>
				
				
				 </div>
				  </div>
			</div>	 
			</div>
		</div>
	</html:form>
	<!-- <script>
		try {
			if (document.forms[0].strFatherNameMandatoryFlag.value == 0)
				document.getElementById("fontIdForFatherName").style.display = "none";
		} catch (_err) {
		}
		if (document.forms[0].strAdmissionMode.value == 2)
			document.getElementById("searhPatientImageId").style.display = "none";
	</script> -->
	<div id='printableSlip'>
		<logic:equal name="paientAdmissionTransBean" property="strSaveFlag"
			value="1">


          <tiles:insert  page="/ipd/transactions/PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo=${paientAdmissionTransBean.strPatientCrNo}&strAdmNo=${paientAdmissionTransBean.strAdmNo}&duplicateMode=0"/>



		</logic:equal>
	</div>
	<script>
		function unloadBootstrap() {
			var styleSheet1 = document.getElementById('style1');
			styleSheet1.disabled = true;
		}
		window.onclick = function(event) {
			var styleSheet1 = document.getElementById('style1');
			if (event.target == myModal) {
				styleSheet1.disabled = true;
			}
		}
		
		
		
		$('#alertModal').on('shown.bs.modal', function () {
			  $('#myInput').trigger('focus')
			})
			
			
  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.collapsible');
    var instances = M.Collapsible.init(elems, '.collapsible-body');
  });
		
		
	        
         document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems);
  });
        

	</script>
	
	<!-- <script>
$(document).ready(function(){
    $('.collapsible').collapsible();
  });
  
$(document).ready(function(){
    $('select').formSelect();
  });
</script>
	 -->


	
</body>
</html>