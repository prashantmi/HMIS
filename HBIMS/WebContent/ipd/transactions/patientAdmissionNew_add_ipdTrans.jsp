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
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/0.4.2/sweet-alert.css"> -->
 
 
 
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 <!-- <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/0.4.2/sweet-alert.min.js"></script> -->

 
 
<!-- <link href="../css/transaction.css" rel="stylesheet" type="text/css">
 --><link href="../../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

<script  src="../../masterutil/js/master.js"></script>
<script  src="../../hisglobal/js/tab.js"></script>
<script  src="../../hisglobal/js/calendar.js"></script>
<script  src="../../hisglobal/js/validationBootstrap.js"></script>
<script  src="../../hisglobal/js/multirow.js"></script>
<script  src="../../ipd/js/patientAdmissionBS.js"></script>
<script  src="../../hisglobal/js/util.js"></script>
<script src="../../ipd/js/patientOccupationDetail.js"></script>
<script  src="../js/patientListing.js"></script>
<!-- <script  src="../../ipd/js/jquery.js"></script> -->
<script  src="../../ipd/js/jquery.simplemodal.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
 <script type="text/javascript" src="../js/bootstrap.min.js"></script>
 

<script >
	//window.history.forward();
$(document).ready(function () {
  $("body").click(function(e) {     
    if($(e.target).attr('id') === "patInfo") {
        $("#patSideListId").show();
    }
    else {
        $("#patSideListId").hide();
    }
  });
});
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

	function getAdmissionCharges() 
	{
		if(document.getElementsByName("strAdmissionCharge")[0].value=="1" || document.getElementsByName("strAdvanceDepsoitAtAdmissionCounter")[0].value=="1")
		{
			var hmode = "CHARGEVALUE";
			var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&wardCode='+ document.forms[0].strWardCode.value + '&treatmentCategCode='+ document.forms[0].strTreatmentCategoryCode.value;
			ajaxFunction(url, "6");
		}		
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
	
    



</style>

</head>
<body onload="viewA();openPrintPopUp();onchangeCountry();checkMsg();" onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">
	<html:form action="/transactions/PatientAdmissionTransBSCNT" method="post">
	
	<fieldset>
	
  <legend class='legendHeader' id='nonPrintableLegend'>Patient Admission</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button id="cancelButton" type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" style="background-color:#2e79b4;" onclick="" style="">
		<i class="fa fa-print iround"  title="Print Last Admission Slip"></i>
	</button>
	
    <button  type="button" id="savebutton" class="btn btn-outline-success mt-1 btn-circle savebtn"  tabindex='2' onClick="return validate1NA();"   data-toggle="modal" data-target="#validateModal" style="display: none;">					
		<i class="fa fa-save iround"  title="Save"></i>
	</button>												                 
  </div>
   <div class="legend3" id='nonPrintableLegend3' style="">
 	<logic:equal name="paientAdmissionTransBean" property="strAdmissionCharge" value="1">	
		<font color='#1d7ffd'>Admission Fee: <i class="fa fa-rupee-sign"  title="admFee"></i>
			<label id='admCharges'><bean:write property="strAdmissionChargeValue" name="paientAdmissionTransBean" filter="false" /></label>			
		</font>							
	</logic:equal>
	<logic:equal name="paientAdmissionTransBean" property="strAdvanceDepsoitAtAdmissionCounter" value="1">
		<font color='#1d7ffd'>Advance Amount: <i class="fa fa-rupee-sign"  title="advAmount"></i>
			<label id='advanceAmount'><bean:write property="strAdvanceAmountVal" name="paientAdmissionTransBean" filter="false" /></label>			
		</font>
	</logic:equal>
	
	<input type='hidden' name='strAdmissionChargeValue' value="${paientAdmissionTransBean.strAdmissionChargeValue}" />
	<input type='hidden' name='strAdvanceAmountVal' value="${paientAdmissionTransBean.strAdvanceAmountVal}" />
  </div> 
 
 
 		
		<div class="container-fluid">
		
			<div class="viewport" id="nonPrintable">
				<div class="alert alert-danger  alert-dismissible fade show" id="errID" style="display: none;"><bean:write name="paientAdmissionTransBean" property="strMsgString" /></div>
				<div class="alert alert-success  alert-dismissible fade show" id="normalMsg" style="display: none;"><bean:write name="paientAdmissionTransBean" property="strMsg" filter="false" /></div>
				<div class="alert alert-warning  alert-dismissible fade show" id="wrnID" style="display: none;"><bean:write name="paientAdmissionTransBean" property="strWarningMsg" /></div>
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
		
						  
							  
											<!-- <div class="card-header" style="padding: .1rem 1.25rem;">
												<a href="" class="float-right btn btn-outline-primary mt-1" onclick="printLastBill();" style="padding: .175rem .35rem; line-height: 0.8">
												<i class="fa fa-print" aria-hidden="true" title="Print Last Bill"></i></a>
												<h4 class="card-title ">Patient Admission </h4>
												
											</div> -->
											<%-- <div class="card-body NEW" id="crNoId">
											
												<div class="form-row"  >
													<div class="col-sm-3 form-group" >
														<font color="red" id="mandCRId" >*</font><label >CR No.</label>
														   
														<div id="patientCrEdId">
														<crNo:crNo id="strCrNoId" value="${paientAdmissionTransBean.strCrNo}" js="onkeypress='return goRetFuncNA(event);return validateData(event,5);'" className='form-control form-control-sm'></crNo:crNo>
														  </div>
						 							</div> <!-- form-group end.// -->
													<div class="col-sm-3 form-group mt-4">
																											
						                             <span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' onclick="showPatientListingWindow('4',document.forms[0].strCrNo,'setSelectedCrNo');"></span>
													 <a href="#" class="btn btn-sm btn-success" onclick="goFuncNA();"  style="border-radius: .5rem;" data-toggle="modal" data-target="#validateModal">
						                             	<i class="fas fa-forward" aria-hidden="true" ></i>Go
						                             </a>
						                             
													</div>
													<div class="col-sm-6 form-group"></div>
									           </div>	 --%>
									           
									           <p id="newBabyLink" style="display:none;">	
									           <span id="admissionSpan" style="float:right;margin-right:20px;display:none;">
									           	<button  style="background: #3ac9d6 !important;border:solid 7px;" id="motherDelDetUpdate" class="btn btn-primary" onclick="openNewBornBabyAdm();">New Baby Admission <i class="fas fa-plus-circle"></i></button>
									           </span>
									           								           
												<!--    <span class="spanlink">
												        <a id="motherDelDetUpdate" class="link" onclick="openNewBornBabyAdm();" href="#"><b>Switch To New Born Baby Admission</b></a>
												   </span> -->
											   </p>
									        
									        <div id="id4">                       
												<pDtl:patDtlNew crNo="${paientAdmissionTransBean.strCrNo}" address="false"></pDtl:patDtlNew>
				  							</div>
									  			   
									 <div class="prescriptionTile">
									 
									 <div id='goBox'>
											<div class="row rowFlex reFlex" >
												
												<div class="col-sm-2" align="right"><font color="red" id="mandCRId" >*</font><label >CR No.</label></div>
												<div class="col-sm-2">
													<div id="patientCrEdId" >
														<crNo:crNo id="strCrNoId" value="${paientAdmissionTransBean.strCrNo}" js="onkeypress='return goRetFuncNA(event);return validateData(event,5);'" className='form-control'></crNo:crNo>
													</div>													
												</div>
												<div class="col-sm-2">
													<span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' onclick="showPatientListingWindow('4',document.forms[0].strCrNo,'setSelectedCrNo');"></span>
													<a href="#" class="btn btn-sm btn-success" onclick="goFuncNA();"  data-toggle="modal" data-target="#validateModal" style="font-size: 1rem;">
						                             	GO&nbsp;<i class="fas fa-angle-double-right"></i>
						                             </a></div>
												<div class="col-sm-2"></div>
												<div class="col-sm-4"></div>												
												</div>
											
												</div>
																
				
				
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
				
				
			      	<div class="" id="newModificationId" style="display: none">
			      	<p class="subHeaders"><i class="fas fa-university isubheaders">&nbsp;</i>Patient Address Details</p>	  
	                    
				</div>
				<div id="newAddressModiId">
					<bean:write name="paientAdmissionTransBean" property="strAddressModi" filter="false" />
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
			     
			    
				
									
				<div id="PatientOccId"></div>
				<div id="PatientOccDtl" class="" style="display: none">
					
					
					<%-- <div id="accordion">
	                      <div class='card' style="max-width: 1226px;">
	                        <div class='card-header' style="padding: .5rem 1.25rem; ">
	                          <a class='card-link' data-toggle='collapse' href='#collapseOne'><i class='fas fa-angle-down rotate-icon' style="color: #086ea5"></i></a>
	                        </div>
	                           <div id='collapseOne' class='collapse' data-parent='#accordion'> 
	                              <div class='card-body'>             
					                <div>
						                  <bean:write name="paientAdmissionTransBean" property="occupationDetailValues" filter="false" />
					                </div>
				                  </div>
					          </div>
					     </div>
					 </div>
					 --%>
				</div>
				
				
				


				<div class="" id="wardDivId" style="display: none;" >
				
				      <div class="row rowheight">
			        <div class="col-sm-9 col-md-9"><p class="subHeaders"><i class="fas fa-clinic-medical isubheaders"></i>&nbsp;Department Ward Details</p></div>
			        
			        <div class="col-sm-2 col-md-2">
			          <button type="button" class="btn btn-info btn-sm btnbg"  data-toggle="modal" data-target="#myModal" tabindex='2' onclick="bedDetails();">
			   				<span class="btn-label"><i class="fas fa-bed"></i></span>&nbsp;Bed Status Popup&nbsp;</button>	          
			        </div>
			        <div class="col-sm-1 col-md-1" id='strDeptWardChangeChkId' style='display: none;'>
			        	<label class="container">Change<input type="checkbox" name='strDeptWardChangeChk' onchange="enableDeptWard(this);" value="${paientAdmissionTransBean.strDeptWardChangeChk}"><span class="checkboxmark"></span></label>
					</div>
			      </div>
						  
	                    <!-- nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 1.1">
				        <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#">
				              <i class="fas fa-clinic-medical"></i>
				              Department Ward Details</a>
				        </div>
				          <ul class="navbar-nav ml-auto">
				                    <li><a href="#" class="nav-item nav-link active text-right" id='bedStatusDiv' >
						               <i class="fa fa-bed" aria-hidden="true" data-toggle="modal" data-target="#myModal" id='modellink' onClick='bedDetails();'></i>
						    	        Bed Status</a></li>
                         </ul>						      
				         </nav> -->
				         
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


							<div id="paymentModeDiv">
								<div class="row rowFlex reFlex">
									<div class="col-sm-2">
										<label>Payment Mode</label>
									</div>
									<div class="col-sm-2">
										<select class="browser-default custom-select" tabindex='2' id="strPayMode" name="strPayMode">
											${paientAdmissionTransBean.strPayMode}
									</div>
										</select>
								</div>
									<div class="col-sm-2">Payment Detail</div>
									<div class="col-sm-2">
										<input name="strPayDetail" tabindex='2' type="text" class="form-control" />
									</div>
								
							</div>

							<div class="" id="emrgencyDivId" style="display: none">
						<div class="row rowFlex reFlex">
						<div class="col-sm-12">			        
						<button type="button" class="btn btn-info btn-sm btnbg" tabindex='2' data-toggle="collapse" data-target="#emgid">
							<i class="fa fa-ambulance"></i>&nbsp;&nbsp;Emergency Contact Details</button>
						</div>
						</div>
					 	<div id="emgid" class="collapse">
				   		<br>	     
                                    <div id="emgAddressDiv">
						                  <bean:write name="paientAdmissionTransBean" property="strEmgAddress" filter="false" />
					                </div>				
					    </div>
					</div>
					
				</div>
				
				<div id="csno">
				</div>
				
				
					
			</div>	
				
				
				
				<hr>
				<div class="row rowFlex reFlex">
					<div class="col-sm-10"></div>
					<div class="col-sm-2" align="right"><i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory</div>				
				</div>

				<%--  <div id="admissionId" style="display: none">
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
		</div>
		
		 <div id="saveid" align="center">
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
									<h5 class="modal-title">Bed  Status Dashboard</h5>
									<button type="button" onclick="unloadBootstrap();"
										class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body" id="modalSpaceId"></div>
							</div>
						</div>
					</div>
				</div>
				
		
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
				value="${paientAdmissionTransBean.strPatientCrNo}"> 
				
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
				value="${paientAdmissionTransBean.strPvtWardType}"> 
				<input type="hidden" name="billcount" value="${paientAdmissionTransBean.billcount}">
				<input type="hidden" name="strAdvanceDepsoitAtAdmissionCounter" value="${paientAdmissionTransBean.strAdvanceDepsoitAtAdmissionCounter}">
				<input type="hidden" name="isSingleMenu" value="${paientAdmissionTransBean.isSingleMenu}" />
				
				
				
				
				                    </div> 
								
						  </div>
						 
		
		</div> 
		
		<div class="modal-container" id="payDtlCDMenu" > <!-- style="display: none;"> -->
					<div id="payModeModal" class="modal fade" role="dialog">
						<div class="modal-dialog" style="max-width:700px;">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Payment Details &gt;&gt;Credit / Debit Card</h5>
									<button type="button" 
										class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body" id="modalSpaceId">
								
									<div class='popup' id='payDtlCDMenuInner ' style="display: block">
				
					
					<!-- <p class="subHeaders">Payment Details &gt;&gt;Credit / Debit Card</p> -->
					<div class="row rowFlex reFlex">
					<div class="col-sm-6"><label><font color="red">*</font>Bank Name</label></div>
					<div class="col-sm-6"><input type='text' name='strPayBankName' tabindex="1" class='form-control' value="" onkeypress="return validateData(event,11);" maxlength="50"/></div>
					</div>
					<div class="row rowFlex reFlex">
					<div class="col-sm-6"><label><font color="red">*</font>Card No. (Last 4 Digits)</label></div>
					<div class="col-sm-6"><input type='password' name='strCardNo' tabindex="1" class='form-control' maxlength="4" onkeypress="return validateData(event,5);" /></div>
					</div>
					<div class="row rowFlex reFlex">
					<div class="col-sm-6"><label><font color="red">*</font>Transaction No.(Max : 15 Digits)</label></div>
					<div class="col-sm-6"><input type='text' name='strAuthNo' tabindex="1" class='form-control' onkeypress="return validateData(event,5);" maxlength="15"/></div>
					</div>
					<div class="row rowFlex reFlex">
					<div class="col-sm-6"><label><font color="red">*</font>Transaction Date</label></div>
					<div class="col-sm-6"><input type="text" class="form-control" tabindex="1" maxlength="11" placeholder="(DD-Mon-YYYY)" name="strAuthDate" value="${paientAdmissionTransBean.strCurrentDate }" ></div>
					</div>
					<div class="row rowFlex reFlex">
					<div class="col-sm-6"><label><font color="red">*</font>Card Type</label></div>
					<div class="col-sm-6"><select class='form-control' name='strCardType' tabindex="1" >
								<option value='0'>Select Value</option>
								<option value='1'>Master</option>
								<option value='2'>Visa</option>
								<option value='3'>Rupay</option>
								<option value='4'>Others</option>
						</select></div>
				
					</div>
                     <div class="row rowFlex reFlex">
	                   <div class="col-sm-12">
	                   <label><font color="red">#</font>Ensure that the Transaction/Card Swap is Successful</label>
	                   </div>
					  </div>
					
				
			                       </div>
								</div>
								
								<div class="modal-footer">
								<div class="row rowFlex reFlex">
				               <div class="col-sm-12" align="center">
				                <button type="button" class="btn btn-success" onClick='return validateCreditDebit();'>Save</button>
				                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				         </div>
				   </div>
				</div>
							</div>
						</div>
					</div>
				</div>





	<div class="modal-container" id="payDtlCDDMenu" > <!-- style="display: none;"> -->
					<div  id="payDtlCDDModal" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Payment Details &gt;&gt;Check / DD</h5>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body" id="modalSpaceId">
								
									<div class='popup' id='payDtlCDDMenuInner' style="display: block">
								
								<div class="row rowFlex reFlex">
									<div class="col-sm-6">
										<label><font color="red">*</font>Bank Name</label>
									</div>
									<div class="col-sm-6">
										<input type='text' tabindex="1" name='strPayCDDBankName'
											class='form-control'
											onkeypress='return validateData(event,11);' maxlength="50" />
									</div>
									
								</div>
								<div class="row rowFlex reFlex">
								
									<div class="col-sm-6">
										<label><font color="red">*</font>Cheque / DD No.(Max :
											15 Digits)</label>
									</div>
									<div class="col-sm-6">
										<input type='text' name='strChequeDDNo' tabindex="1"
											class='form-control'
											onkeypress="return validateData(event,5);" maxlength="15" />
									</div>
								</div>
								<div class="row rowFlex reFlex">
									<div class="col-sm-6">
										<label><font color="red">*</font>Cheque / DD Issue
											Date</label>
									</div>
									<div class="col-sm-6">
										<input type="text" tabindex="1" placeholder="(DD-Mon-YYYY)" class="form-control"
											maxlength="11" name="strChequeDDDate"
											value="${paientAdmissionTransBean.strCurrentDate }">
									</div>
									
								</div>
								
							</div>
							
								</div>
								<div class="modal-footer">
                                  <div class="row rowflex reflex">  
                               <div class="col-sm-12">
						        <button type="button" class="btn btn-success" onclick="validateCheckDD();">save</button>
						       <button type="button" class="btn btn-danger" data-dismiss="modal">cancel</button>
						        </div>
						        </div>
                              </div>
								
							</div>
						</div>
					</div>
				</div>



		</fieldset>
	</html:form>
	<script>
		try {
			if (document.forms[0].strFatherNameMandatoryFlag.value == 0)
				document.getElementById("fontIdForFatherName").style.display = "none";
		} catch (_err) {
		}
		if (document.forms[0].strAdmissionMode.value == 2)
			document.getElementById("searhPatientImageId").style.display = "none";
	</script>
	
	<script>
		function unloadBootstrap() {
			//var styleSheet1 = document.getElementById('style1');
			//styleSheet1.disabled = true;
		}
		window.onclick = function(event) {
			//var styleSheet1 = document.getElementById('style1');
			if (event.target == myModal) {
				//styleSheet1.disabled = true;
			}
		}
		
		
		
		$('#alertModal').on('shown.bs.modal', function () {
			  $('#myInput').trigger('focus')
			})
	</script>
	
<div class="modal fade" id="printModal" role="dialog" >
  <div class="modal-dialog modal-lg" role="document" style="">
    <div class="modal-content">
    <div class="modal-header"><button type="button" data-dismiss="modal" onclick="modalSlipPrint()" class="btn btn-primary" ><i class="fa fa-print" title="Print Last Slip"></i>&nbsp;Print</button></div>
    <div class="modal-body" id='printableSlip'>
	<logic:equal name="paientAdmissionTransBean" property="strSaveFlag" value="1">
           <tiles:insert  page="/ipd/transactions/PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo=${paientAdmissionTransBean.strPatientCrNo}&strAdmNo=${paientAdmissionTransBean.strAdmNo}&duplicateMode=0"/>
      </logic:equal>
           
           	<%-- 		<logic:equal value="0" name="paientAdmissionTransBean" property="strSaveFlag">
							<h3 align="center"> <font color="#808080">No slip to print</font></h3>
					</logic:equal> --%>
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
					
					<div id='mainDiv2'>
					<div class="modal fade" id="previewModal"    tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
					  <div class="modal-dialog "  role="document">
					    <div class="modal-content">
					    <div class="modal-header" >
						 <h4 class="modal-title" >Preview & Verify</h4>
							<button type="button" class="close" data-dismiss="modal">×</button></div>
					      <div class="modal-body" >
							<div class="row"  id="setPrevModal">
							
							</div>
					              
					      </div>
					      <div class="modal-footer" >
					        <button  type="button" class="btn btn-success"  tabindex='1' onclick="submit()">Ok&Save</button>
					
					      </div>
					    </div>
					  </div>
					</div>
					</div>
						

	
</body>
</html>