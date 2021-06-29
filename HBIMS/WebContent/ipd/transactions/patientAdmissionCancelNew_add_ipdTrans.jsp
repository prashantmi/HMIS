<!DOCTYPE html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Admission Cancellation</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%-- 
  <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
 <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
  --%>
 

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.min.css" rel="stylesheet">
<link href="../css/newlayout.css" rel="stylesheet" type="text/css">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>


<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->



<style type="text/css">

.table thead:hover{
font-family:fantasy;
box-shadow: 0 0 6px rgba(35, 173, 278, 1);
}

button:hover{
box-shadow: 0 0 6px rgba(35, 173, 278, 1);
}

</style>



 



<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../js/admCancelBS.js"></script>
<script type="text/javascript">

 
</script>
</head>
<body onload="view(); checkMsg();" onFocus="checkPopUpAndSetDefaultCrNo();" >
<html:form action="/transactions/PatientAdmissionCancellationTransBSCNT" method="post">

<fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'>Patient Admission Cancellation</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<!-- <button  type="button" id="cancelButton" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
	<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="openMenu('SIGLEMENUHOME','')">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
    <button  type="button" id="savebutton" class="btn btn-outline-success mt-1 btn-circle savebtn"  tabindex='2' onClick="return validate1();" data-toggle="modal" data-target="#validateModal" style="display: none;">					
		<i class="fa fa-save iround"  title="Save"></i>
	</button>												                 
  </div>
 
<div class="container-fluid viewport" id="viewportDiv">
            
						
	<div class="alert alert-danger" id="errID" style="display: none"><bean:write name="patientAdmissionCanceTransBean" property="strMsgString" /></div>
	<div class="alert alert-success" id="normalMsg" style="display: none"><bean:write name="patientAdmissionCanceTransBean" property="strMsg" /></div>
	<div class="alert alert-warning" id="wrnID" style="display: none"><bean:write name="patientAdmissionCanceTransBean" property="strWarningMsg" /></div>
	

<%-- 	<tag:tab tabLabel="Patient Admission Cancellation" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
 --%>	<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Patient Admission Cancellation</td>
		</tr>
	</table> -->
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;"><crNo:crNo id="strCrNoId"
				value="${patientAdmissionCanceTransBean.strCrNo}"
				js=" onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo> <img
				style="cursor: pointer;" id="searhPatientImageId"
				src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle"
				name='searchPatient'
				onclick="showPatientListingWindow('8',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png"
				onClick="return goFunc();" align="top"
				style="cursor: pointer; cursor: hand"></div>
			<div id="patientCrId" style="display: none;"><bean:write
				name="patientAdmissionCanceTransBean" property="strCrNo" /></div>
			</td>
		</tr>
	</table> --%>
	                    <div id="id4" style="display: none;">		
					         	<pDtl:patDtlNew crNo="${patientAdmissionCanceTransBean.strCrNo}"
						                    	address="false" ></pDtl:patDtlNew>
				                      	</div>
			 
	                       <div class="prescriptionTile">
									     
											<div class="row rowFlex reFlex" id='goBox'>
												
												<div class="col-sm-2" align="right"><font color="red" id="mandCRId" >*</font><label >CR No.</label></div>
												<div class="col-sm-2"><div id="patientCrEdId" >
														<crNo:crNo id="strCrNoId" value="${patientAdmissionCanceTransBean.strCrNo}" js="onkeypress='return goRetFunc(event);return validateData(event,5);'" className='form-control'></crNo:crNo>
														  </div></div>
												<div class="col-sm-2" style="padding-left: 0 "> <span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' data-toggle="modal" data-target="#searchModel"  onclick="fetchPatientList_BS(1,1)"></span>
													 <a href="#" class="btn btn-sm btn-success" onclick="return goFunc();" data-toggle="modal" data-target="#validateModal" style="font-size: 1rem;">
                                                      GO&nbsp;<i class="fas fa-angle-double-right"></i>
						                             </a>
						                             </div>
												<div class="col-sm-2"></div>
												<div class="col-sm-4"></div>
												
												
												</div>
	         
							  <%-- <div class="card " id='goBox'>
											
											<div class="card-body" style="padding: 0rem;">
												<div class="form-row" id='goBox'>
												<div class="col-sm-1 form-group"></div>
													<div class="col-sm-3 form-group" >
														<font color="red" id="mandCRId" >*</font><label >CR No.</label>
														   
														<div id="patientCrEdId">
														<crNo:crNo id="strCrNoId" value="${patientAdmissionCanceTransBean.strCrNo}" js="onkeypress='return goRetFunc(event);return validateData(event,5);'" className='form-control'></crNo:crNo>
														  </div>
						 							</div> <!-- form-group end.// -->
													<div class="col-sm-3 form-group mt-4" style="margin-top: 1.7rem !important;">
																											
						                             <span class="fa fa-search" style="cursor: pointer; cursor: hand;" id="searhPatientImageId" title="Click here for Patient Search" name='searchPatient' data-toggle="modal" data-target="#searchModel"  onclick="fetchPatientList_BS(1,1)"></span>
													 <a href="#" class="btn btn-xs btn-success" onclick="return goFunc();" data-toggle="modal" data-target="#validateModal">
                                                      Go&nbsp;<i class="fas fa-angle-double-right"></i>
						                             </a>
						                             
													</div>
													<div class="col-sm-5 form-group"></div>
									           </div>	
									            </div> <!-- card-body end .// -->
									<div class="card-footer cfooter" id="footer" style="padding: .1rem 1.25rem; background-color: white;" >
									<div class="form-row">
						 			<div class="col-sm-4 form-group" style="margin-bottom: 0">
						 			</div>
						 			
						 			 <div  align="center" class="col-sm-4 form-group" style="margin-bottom: 0;">
									</div>
									<div class="col-sm-4 form-group" align="Right" style="margin-bottom: 0 ; line-height: 2.4"><i class="fa fa-asterisk" aria-hidden="true" style="color: red;font-size: smaller;"></i>Fields Mandatory</div>
						 			</div>
								</div> 
	                </div><!-- card.// --> --%>
	           
				<!-- 											
	                  <div id="adnav" style="display: none">
	                   <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 0.8">
                         <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem;" style="">Patient Admission Cancellation </a>
				        </div>
				        </nav>
	                 </div>  -->
	
	
	
	<%-- <div id="id4" style="display: none;">		
	<div class="row justify-content-center" >
							<div class="col-md-11">
		<pDtl:patDtlNew crNo="${patientAdmissionCanceTransBean.strCrNo}"
			address="false"></pDtl:patDtlNew>
			</div>
			</div>
	</div> --%>
	
	<div id="newModificationFormId" style="display: none">
	<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">Address Details</td>
		</tr>
	</table> -->
	
	 <!-- <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 0.8">
                         <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem;" style="">Address Details </a>
				        </div>
				        </nav> -->
	</div>
	
	
	<div id="newAddressModiId" style="display: none;">
	<div class='row justify-content-center'>
			<div class='col-md-12'>
			<div id='accordion' style="display: none;">

			<div class='card new'>
		    <div class='card-header' style="padding: .15rem 0.10rem; background-color: white;border-bottom:0;height: 20px;">
		      
									  			   
			<p class="subHeaders"><i class="fas fa-map-marker-alt" style="font-size: 26px"></i> <label >Address Details</label><a class='card-link' data-toggle='collapse' href='#collapseOne' style="float: right;">&nbsp&nbsp&nbsp<i class='fas fa-angle-down rotate-icon' style="color:black;"; font-size: 1rem;"></i></a>
			 </p>
			
			  </div>
			 <div id='collapseOne' class='collapse' data-parent='#accordion'>
	         <bean:write name="patientAdmissionCanceTransBean" property="strAddressModi"
		filter="false" />

	</div>
	</div>
	</div>
	</div>
		</div>
		</div>
	<div id='DeparmentUnitModiId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL"><select
				name="StrDeptNameNewBorn" onchange="getUnitCombo();"
				class="comboMax">
				<bean:write property="strDeptValue"
					name="patientAdmissionCanceTransBean" filter="false" />
			</select></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL">
			<div id="unitDivId"><select name="strUnitNewBorn"
				class="comboNormal" onchange="funUnit()">
				<bean:write property="strUnitValue"
					name="patientAdmissionCanceTransBean" filter="false" />
			</select></div>
			</td>
		</tr>
	</table>
	</div>
	<div id='DeparmentUnitId' style="display: none">
	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL"><bean:write
				property="strDeptName" name="patientAdmissionCanceTransBean"
				filter="false" /></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL"><bean:write
				property="strUnitName" name="patientAdmissionCanceTransBean"
				filter="false" /></td>
		</tr>
	</table> --%>
	</div>


	<div id="wardDivId" style="display: none">
							
	<p class="subHeaders"><i class="fas fa-undo" style="font-size: 26px"></i><span class="middle"><label>&nbsp;Cancellation Details</label></span></p>
											
	<div class="row rowFlex reFlex">
		<div class="col-sm-2"><label>Ward</label></div>
		<div class="col-sm-3">
			<div id="wardNameId"><bean:write name="patientAdmissionCanceTransBean" property="strWardName" /></div>			
		</div>
		<div class="col-sm-3"></div>
		<div class="col-sm-4"><div id="roomBedId" style="height:30px;"><bean:write name="patientAdmissionCanceTransBean" property="strRoom" /></div></div>
	</div>
										
	<div class="row rowFlex reFlex">
		<div class="col-sm-2"><label><font color='red' size='2'>*</font>Approved By</label></div>
		<div class="col-sm-3">
			<select name="strConsultantName" class='browser-default custom-select'>
				       <bean:write name="patientAdmissionCanceTransBean" property="strConsultantValues" filter='false' />
	        </select>											
	    </div>
		<logic:greaterThan value="0" name="patientAdmissionCanceTransBean" property="strAdmissionChargeValue">
		
			<div class="col-sm-3"><label><font color="red">Refund Amount(Admission Charges)</font></label></div>
			<div class="col-sm-4">
				<font color="red"><i class="fa fa-rupee-sign"  title="admFee"></i><bean:write name="patientAdmissionCanceTransBean" property="strAdmissionChargeValue" /></font>
			</div>	
		</logic:greaterThan>
				
	</div>
		
	
		<logic:equal value="1" name="patientAdmissionCanceTransBean" property="strAdvanceDepositStatus">
		<div class="row rowFlex reFlex">		
			<div class="col-sm-2"><label><font color="red">Advance Amount Deposited</font></label></div>
			<div class="col-sm-10">
				<font color="red"><i class="fa fa-rupee-sign"  title="advAmt"></i><bean:write name="patientAdmissionCanceTransBean" property="strAdvanceDetails" /></font>
			</div>	
		</div>
		</logic:equal>
	
									
	<div class="row rowFlex reFlex">
		<div class="col-sm-2"><label><font color='red' size='2'>*</font>Cancel Reason</label></div>
		<div class="col-sm-10"><textarea rows="3" cols="25" class="form-control" name="strRemarks" id="remarksId"></textarea>		
			<div style="position: absolute;top: 0;right: 16px;">
				<button type="button" onclick="$(this).parent().parent().find('#remarksId').val('');" class="btn btn-sm btn-default reasonOfVisitCleanBtn">
					<i class="fa fa-times" aria-hidden="true"></i>
				</button>
			</div>
		</div>
	</div>
	
	</div>
										
								
							
	
	
									

	<hr>
	<div class="row rowFlex reFlex">
			<div class="col-sm-12" align="right"><i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory</div>							
	</div>
	<%-- <table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<logic:notEmpty name="patientAdmissionCanceTransBean" property="strCrNo">
				<a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
					<!-- <img src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" style="cursor: pointer; cursor: hand" /> -->
				</logic:notEmpty>
			<!-- <img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer; cursor: hand" />
			<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer; cursor: hand" /> -->
			<a href="#" class="button"	onClick="clearRecord();"><span class="clear">Clear</span></a> 
			<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
		</td>
		</tr>
	</table>
 --%>
 
 </div>
 
<div id='mainDiv1' style=''>
<div class="modal fade " id="searchModel"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    <div class="modal-header">
	 <h4 class="modal-title">Paitent Listing</h4>
		<button type="button" 
		class="close" data-dismiss="modal">&times;</button></div>
	
      <div  class="modal-body" id ="fetchRecordDivId"  >
     
      </div>
       
      <div class="modal-footer">
        <!-- <button  type="button" class="btn btn-success btn-sm ml-auto" style="font-size: 1rem" name="paientAdmissionTransBean" title="Select the Patient">Ok<i class="fa fa-check"></i></button>					
		<button  type="button" class="btn btn-danger btn-sm mr-auto" data-dismiss="modal" style="font-size: 1rem" name="paientAdmissionTransBean" title="Cancel and Close the Window">Cancel<i class="fa fa-times-circle-o"></i></button> -->	
      </div>
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

	<input type="hidden" name="hmode" />
	<input type="hidden" name="deskmode" />
	<input type="hidden" name="strWardTypeCode" value="${patientAdmissionCanceTransBean.strWardTypeCode}" />
	<input type="hidden" name="strDeptUnitCode" value="${patientAdmissionCanceTransBean.strDeptUnitCode}" />
	<input type="hidden" name="strTreatmentCategoryCode" value="${patientAdmissionCanceTransBean.strTreatmentCategoryCode}" />
	<input type="hidden" name="strWardCode" value="${patientAdmissionCanceTransBean.strWardCode}" />
	<input type="hidden" name="strBedTypeCode" value="${patientAdmissionCanceTransBean.strBedTypeCode}" />
	<input type="hidden" name="strWardName" value="${patientAdmissionCanceTransBean.strWardName}" />
	<input type="hidden" name="strRoomTypeCode" value="${patientAdmissionCanceTransBean.strRoomTypeCode}" />
	<input type="hidden" name="strDeptCode" value="${patientAdmissionCanceTransBean.strDeptCode}" />
	<input type="hidden" name="strConsultantCode" value="${patientAdmissionCanceTransBean.strConsultantCode}" />
	<input type="hidden" name="strEpisodeCode" value="${patientAdmissionCanceTransBean.strEpisodeCode}" />
	<input type="hidden" name="strVisitNo" value="${patientAdmissionCanceTransBean.strVisitNo}" />
	<input type="hidden" name="strMlcNo" value="${patientAdmissionCanceTransBean.strMlcNo}" />
	<input type="hidden" name="strAdviceAdmNo" value="${patientAdmissionCanceTransBean.strAdviceAdmNo}" />
	<input type="hidden" name="strIsUrban" value="${patientAdmissionCanceTransBean.strIsUrban}" />
	<input type="hidden" name="strBedCode" value="${patientAdmissionCanceTransBean.strBedCode}" />
	<input type="hidden" name="strRoomCode" value="${patientAdmissionCanceTransBean.strRoomCode}" />
	<input type="hidden" name="strBookingDate" value="${patientAdmissionCanceTransBean.strBookingDate}" />
	<input type="hidden" name="strFlag" value="1" />
	<input type="hidden" name="strMsApprovalFlag" value="${patientAdmissionCanceTransBean.strMsApprovalFlag}" />
	<input type="hidden" name="strMsApprovalStatus" value="${patientAdmissionCanceTransBean.strMsApprovalStatus}" />
	<input type="hidden" name="strNewBorn" value="${patientAdmissionCanceTransBean.strNewBorn}" />
	<input type="hidden" name="strDeptName" value="${patientAdmissionCanceTransBean.strDeptName}" />
	<input type="hidden" name="strUnitName" value="${patientAdmissionCanceTransBean.strUnitName}" />
	<input type="hidden" name="strAdmNo" value="${patientAdmissionCanceTransBean.strAdmNo}" />
	<input type="hidden" name="gblCRValue"/>
	<input type="hidden" name="strAdmissionChargeValue" value="${patientAdmissionCanceTransBean.strAdmissionChargeValue}" />
	<input type="hidden" name="isSingleMenu" value="${patientAdmissionCanceTransBean.isSingleMenu}" />
	       
	</div>
	</fieldset>
</html:form>
<%-- <tag:autoIndex></tag:autoIndex> --%>



</body>
</html>