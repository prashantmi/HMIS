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

<head>
<meta charset=utf-8>
<title>DBMS Job Tracking</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
 
 
 
 

 
 
 
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
   <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">



<!-- <script src="../../masterutil/js/master.js"></script> -->
<script src="../../hisglobal/js/tab.js"></script>
<script src="../../hisglobal/js/calendar.js"></script>
<script src="../../hisglobal/js/util.js"></script>
<script src="../../hisglobal/js/validationBootstrap.js"></script>
<script src="../../hisglobal/js/multirow.js"></script>
<script src="../../ipd/js/jobTracking.js"></script>
<script src="../js/patientListing.js"></script>
<script src="../../ipd/js/patientOccupationDetail.js"></script>
  
<style type="text/css">
body
{
font-size:12px;
}
button:hover{
box-shadow: 0 0 6px rgba(35, 173, 278, 1);
}
td{
padding:0.1rem !important;
font-family:fantasy;
border-bottom:0.01px solid rgb(196, 242, 242,0.5);
font-size:0.8rem;
}
.btn-sm
{
font-size:0.8rem;
}
tr:hover{
  background:rgb(196, 242, 242,0.5); 
/* box-shadow: 0 0 6px rgba(35, 173, 278, 1);
 */}

 thead:hover{
padding:0.5rem !important;
font-family:fantasy;
box-shadow: 0 0 6px rgba(35, 173, 278, 1);
} 

thead >*{
font-size:0.8rem;
}


.table th, .table td {
	padding: 0.15rem;
	/*vertical-align: top;*/
	border-top: 1px solid #dee2e6;
	border-collapse: collapse;
}

table.dataTable {
    
    margin-top: 0px !important;
    border-collapse: collapse !important;
    }
.modal-lg
{
padding-top: 70px;
}

</style>
<script type="text/javascript">


	
var beforePrint = function() 
{  
	showPrintableSlip();
};
var afterPrint = function() 
{               
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

function emgDiv()
{
	var divClass=document.getElementById("collapseOne").className;
	if(divClass=="collapse")
	{
		document.getElementById("collapseOne").className="";
	}
	else
	{
		document.getElementById("collapseOne").className="collapse";
	}
		
}


</script>

</head>

<body onload="view();jobTracking('13');onchangeCountry(); checkMsg();" onUnload="">

<html:form action="/transactions/JobTrackingTransBSCNT" method="post" styleId="form1">

<fieldset form="form1">
	
  <legend style="width:auto; margin-bottom: 0px;font-size: 1.5rem;font-weight: 400;margin-left:10px;color: #666;" id='nonPrintableLegend'>DBMS Job Tracking(Since Last 30 Days)</legend>
  <div class="legend2" id='nonPrintableLegend2'>
<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle" onclick="cancelFunc();" style="background-color: #d9534f;">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle" data-toggle="modal" data-target="#printModal" onclick="" style="background-color:#2e79b4;">
		<i class="fa fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' onClick="return validate1();" name="jobTrackingTransBean"  data-toggle="modal" data-target="#validateModal" style="display: none;background-color: #5cb85c;">					
		<i class="fa fa-save iround"  title="Save" ></i>
	</button>	
	
<!-- 	<button  type="button" id="admitButton" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' onClick="return validate1();">					
		<i class="fa fa-ambulance"  title="Save" ></i>
	</button>
	 -->
											                 
  </div>
  <div class="legend3" id='nonPrintableLegend3'>
 	<logic:equal name="jobTrackingTransBean" property="strAdmissionChargeAtCounter" value="1">	
		<font color='red'>Admission Fee Deposited: <i class="fa fa-rupee-sign"  title="admFee"></i>
			<bean:write property="strAdmissionChargeValue" name="jobTrackingTransBean" filter="false" />			
		</font>
			<input type='hidden' name='strAdmissionChargeValue' value="${jobTrackingTransBean.strAdmissionChargeValue}" />
			<input type='hidden' name='strAdmissionCharge' value="${jobTrackingTransBean.strAdmissionChargeValue}" />		
	</logic:equal>
  </div>
 
	<div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
					<div class="alert alert-danger alert-dismissible fade show" id="errMsg"  style="display:none;"><bean:write name="jobTrackingTransBean" property="strMsgString"/></div>
					<div class="alert alert-success alert-dismissible fade show" id="normalMsg" style="display:none;"><bean:write name="jobTrackingTransBean" property="strMsg"/></div>
					<div class="alert alert-warning alert-dismissible fade show"  id="wrnID" style="display:none;"><bean:write name="jobTrackingTransBean" property="strWarningMsg"/></div>			  	<div class="row justify-content-center" >
								<div class="col-md-12">												
								<div class="prescriptionTile" style="padding-bottom: 0;padding-top: 0;" id="mainDataTable">
								<br>
									<div id="fetchRecordDivId"></div>											
									<div class="" id='searchdata' style="display: none;">
		                              	<div class="form-row" >
												<div class="col-sm-3 form-group" id="patientCrLabelId"><font color="red" id="mandCRId" >*</font><label >CR No.</label>
												<div id="patientCrEdId">
                                                       <crNo:crNo id="strCrNoId" value="${jobTrackingTransBean.strCrNo}" js="onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo>
                                                </div>
					 							</div>
												<div class="col-sm-2 form-group mt-4" style="margin-top: 2rem !important;">
												 <a href="#" class="btn btn-xs btn-success" onclick="goFuncNA();" style="border-radius: .5rem;">
					                             	<i class="fas fa-forward"  ></i>Go
					                             </a>								                             
												</div>
												<div class="col-sm-2 form-group"><label >Find By:</label>
													<select name='strSearchType' class='form-control'>
											               <option value='1'>CR No.</option>
											               <option value='2'>Patient Name</option>
											        </select>													                     
												</div>
												<div class="col-sm-2 form-group" style="margin-top: 2rem !important;">
													<input type="text" class="form-control">
												</div>
												<div class="col-sm-3 form-group mt-4" style="margin-top: 2rem !important;">
												 <a href="#" class="btn btn-xs btn-success"  style="border-radius: .5rem;" onClick="getSearchPatListBySearchforpatientmodification();">Search</a>            
												</div>
								   		</div>
			                  		</div>	
								</div>
								
															  
							  <div id="id4" align='center'>
							  <pDtl:patDtlNew crNo="${jobTrackingTransBean.strCrNo}" address="false"></pDtl:patDtlNew>
							  </div>
							  
							  	
							  <div class="" id="patDtlID" style="display: none;">	                           
								 <table class="table" style="width: 100%;">	
									<tr id="patInformationIdPlus" style="display: none;" class="HEADER">
										<td width="100%" ><div id="plusImageId">
										<img src="../../hisglobal/images/plus.gif" onClick="viewX();" style="cursor: pointer;"/>
										&nbsp;&nbsp;
										CR No:&nbsp;<bean:write name="jobTrackingTransBean" property="strCrNo"/>
										&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName1"></label></div></td>
									</tr>
									<tr id="patInformationIdMinus" style="display: none;" class="HEADER">
										<td width="100%" >
										<div id="minusImageId">
										<img src="../../hisglobal/images/minus.gif" onClick="viewY();" style="cursor: pointer;"/>&nbsp;&nbsp;
										CR No:&nbsp;<bean:write name="jobTrackingTransBean" property="strCrNo"/>
										&nbsp;&nbsp;&nbsp;&nbsp;Patient Name&nbsp;:&nbsp;<label id="patName2"></label></div></td>
									</tr>
								</table>
							</div>
		
		
		<div class="prescriptionTile" id="newModificationFormId" style="display: none">
			<p class="subHeaders"><i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Patient Address Details</p>	          	
		      	<div id="newAddressModiId"><bean:write name="jobTrackingTransBean" property="strAddressModi" filter="false" /></div>
		      			
			<div id="deptWardId" style="display: none">
				<div class="row">
			        <div class="col-sm-10 col-md-10"><p class="subHeaders"><i class="fas fa-clinic-medical" style="font-size: 26px"></i>&nbsp;Department Ward Details</p></div>
			        <div class="col-sm-2 col-md-2">
			          <button type="button" class="btn btn-info btn-sm btnbg" data-toggle="modal" data-target="#bedPopupModal" onclick="bedDetails();">
			   				<span class="btn-label"><i class="fas fa-bed"></i></span>&nbsp;Bed Status Popup&nbsp;</button>	          
			        </div>
			      </div>
				  <div class="row">
			    	<div class="col-sm-12 col-md-12">	 
						<div id="wardDivId" style="display: none">
					 		<div class="" style="/*max-width: 1230px;*/">
					 			<div class=""><bean:write property="strDeptUnitWardRoomCatConsulatant" name="jobTrackingTransBean" filter="false" /></div>
							</div>
						</div>
						<div id='DeparmentUnitModiId' style="display: none"></div>
						<div id='DeparmentUnitId' style="display: none"></div>
						<div id="remarksId" style="display: none">Remarks:<textarea rows="3" cols="25" name="strRemarks"></textarea></div>
						<div id="csno" style="display: none"></div>
					</div>
				</div>	    
			</div>		
			<div  id="emrgencyDivId" style="display:none">
		        <button type="button" class="btn btn-info btn-sm btnbg" onclick='emgDiv();' data-toggle="collapse" data-target="#emgid" ><i class="fa fa-ambulance"></i>&nbsp;&nbsp;Emergency Contact Details</button>
			   	<div id="emgid" class="collapse">
				   	<br><br>	     
					<div id="emgAddressDiv" style="display: block;"><bean:write name="jobTrackingTransBean" property="strEmgAddress" filter="false"/></div>
				</div>	
		        <div id="PatientOccId"></div>
			    <div id="PatientOccDtl" style="display: none"><bean:write name="jobTrackingTransBean" property="occupationDetailValues" filter="false" /></div>	 			 	 	
		 	</div>
		 							<div class="row rowFlex reFlex">
							<div class="col-sm-10"></div>
							<div class="col-sm-2" align="right">
								<i class="fa fa-asterisk"
									style="color: red; font-size: smaller;"></i>Fields Mandatory
							</div>

						</div>

</div>
</div>


						<%-- <table border="0" class="TABLEWIDTH" align="center" cellspacing="0"> 
			<tr class="FOOTER">
			    <td><div align='left'><font size="2" color="red" tabindex='1'>**</font>Fields are mandatory if I/II Person Name is entered</div></td>
				<td><font size="2" color="red" tabindex='1'>*</font>Mandatory Fields</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				
					<logic:notEmpty name="jobTrackingTransBean" property="strCrNo">
						<!-- <img src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" style="cursor: pointer; cursor: hand" /> -->
						<a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
					</logic:notEmpty>
					<!--   <img src="../../hisglobal/images/btn-clr.png" onClick="clearRecord();" style="cursor: pointer; cursor: hand" />-->
					<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
					<!-- <img src="../../hisglobal/images/btn-ccl.png" onClick=" cancelFunc();" style="cursor: pointer; cursor: hand" /> -->
				</td>
			</tr>
		</table> --%>
		
		
		                 <!-- <nav class="navbar navbar-expand-sm bg-primary navbar-dark" style="line-height: 0.8; padding: .1rem 1rem; background-color: #086ea5 !important; ">
					        <div class="navbar-header" align="left" >
					              <a class="navbar-brand" href="#" style="font-size: 1rem"><font color="red">**</font>Fields are mandatory if I/II Person Name is entered</a>
					        </div>
					          <ul class="navbar-nav ml-auto">
					          <li><a class="navbar-brand" href="#" style="font-size: 1rem"><font color="red" >**</font>Mandatory Fields</a></li>
	                       </ul>						      
					         </nav> -->
					         
					        

	
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strWardTypeCode" value="${jobTrackingTransBean.strWardTypeCode}" />
		<input type="hidden" name="strBedTypeCode" value="${jobTrackingTransBean.strBedTypeCode}" />
		<input type="hidden" name="strRoomTypeCode" value="${jobTrackingTransBean.strRoomTypeCode}" />
		<input type="hidden" name="strEpisodeCode" value="${jobTrackingTransBean.strEpisodeCode}" />
		<input type="hidden" name="strVisitNo" value="${jobTrackingTransBean.strVisitNo}" />
		<input type="hidden" name="strMlcNo" value="${jobTrackingTransBean.strMlcNo}" />
		<input type="hidden" name="strAdviceAdmNo" value="${jobTrackingTransBean.strAdviceAdmNo}" />
		<input type="hidden" name="strIsUrban" value="${jobTrackingTransBean.strIsUrban}" />
		<input type="hidden" name="strBedCode" value="${jobTrackingTransBean.strBedCode}" />
		<input type="hidden" name="strBookingDate" value="${jobTrackingTransBean.strBookingDate}" />
		<input type="hidden" name="strFlag" value="1" />
		<input type="hidden" name="strMsApprovalFlag" value="${jobTrackingTransBean.strMsApprovalFlag}" />
		<input type="hidden" name="strMsApprovalStatus" value="${jobTrackingTransBean.strMsApprovalStatus}" />
		<input type="hidden" name="strNewBorn" value="${jobTrackingTransBean.strNewBorn}" />
		<input type="hidden" name="strDeptName" value="${jobTrackingTransBean.strDeptName}" />
		<input type="hidden" name="strUnitName" value="${jobTrackingTransBean.strUnitName}" />
		<input type="hidden" name="strAdmNo" value="${jobTrackingTransBean.strAdmNo}" />
		<input type="hidden" name="strBookingDate" value="${jobTrackingTransBean.strBookingDate}" />
		<input type="hidden" name="strDeptUnitName" value="${jobTrackingTransBean.strDeptUnitName}" />
		<input type="hidden" name="strWardName" value="${jobTrackingTransBean.strWardName}" />
		<input type="hidden" name="strRoom" value="${jobTrackingTransBean.strRoom}" />
		<input type="hidden" name="strConsultantName" value="${jobTrackingTransBean.strConsultantName}">
		<input type="hidden" name="strAgeUnit" value="">
		<input type="hidden" name="strSexCode" value="">
		<input type="hidden" name="strAge" value="">
		
		<input type="hidden" name="strAdmDateTime" value="${jobTrackingTransBean.strAdmDateTime}" />
		<input type="hidden" name=strIsIntegratedWithBilling value="${jobTrackingTransBean.strIsIntegratedWithBilling}"/>
		<input type="hidden" name=strIsAdvanceAmountAtAdmission value="${jobTrackingTransBean.strIsAdvanceAmountAtAdmission}"/>
		<input type="hidden" name=strIsAdvanceAmountAtAdmissionTaken value="${jobTrackingTransBean.strIsAdvanceAmountAtAdmissionTaken}"/>
		<input type="hidden" name="strAdvanceAmountDate" value="${jobTrackingTransBean.strAdvanceAmountDate}">
		<input type="hidden" name="strAdvanceAmountReceiptNo" value="${jobTrackingTransBean.strAdvanceAmountReceiptNo}">
		<input type="hidden" name="strAdvanceAmount" value="${jobTrackingTransBean.strAdvanceAmount}">
		<input type="hidden" name="strUnitReq" value="${jobTrackingTransBean.strUnitReq}">
		<input type="hidden" name="strRoomReq" value="${jobTrackingTransBean.strRoomReq}">
		<input type="hidden" name="strSaveFlag" value="${jobTrackingTransBean.strSaveFlag}">
		<input type="hidden" name="strPatientCrNo" value="${jobTrackingTransBean.strPatientCrNo}">
		<input type="hidden" name="strFlagForCheck" value="${jobTrackingTransBean.strFlagForCheck}">
		<input type="hidden" name="strAdmissionChargeAtCounter" value="${jobTrackingTransBean.strAdmissionChargeAtCounter}">
		<input type="hidden" name="strPatIsUnknown" value="${jobTrackingTransBean.strPatIsUnknown}">
		<input type="hidden" name="isSingleMenu" value="${jobTrackingTransBean.isSingleMenu}">
		
		
		
		                                 </div> 
		                                </div> <!-- card-body end .// -->
							 			<!-- <div class="card-footer cfooter" id="footerdata" style="padding: .1rem 1.25rem;display: none;">
							 			<div class="form-row">
							 			<div class="col-sm-4 form-group" style="margin-bottom: 0">
							 			</div>
							 			<div class="col-sm-4 form-group" id="saveid" style="margin-bottom: 0;">
							 			</div>
							 			<div class="col-sm-4 form-group" align="center" id="save" style="margin-bottom: 0; display: none">
						                 <button  type="button" id="savebutton" class="btn btn-success btn-sm"  onClick="return validate1();" name="jobTrackingTransBean"  data-toggle="modal" data-target="#validateModal">Save</button>					
						                 <button  type="button" class="btn btn-danger btn-sm" onclick="cancelFunc();">Cancel</button> 
						                </div>
						                 <div class="col-sm-4 form-group" align="Right" style="margin-bottom: 0 ;line-height: 2.4">
						                 <i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory</div>
						               </div>
							 			</div> -->
								
		</div>	 
	

</fieldset>
</html:form>

<%--<tag:autoIndex></tag:autoIndex>--%>


<div class="modal fade" id="printModal" role="dialog" >
  <div class="modal-dialog modal-lg" role="document" style="">
    <div class="modal-content">
    <div class="modal-header"><button type="button"  onclick="modalSlipPrint()" class="btn btn-primary" ><i class="fa fa-print" title="Print Last Slip"></i>&nbsp;Print</button></div>
    <div class="modal-body" id='printableSlip'>
	<logic:equal name="jobTrackingTransBean"  property="strSaveFlag" value="1">
		<tiles:insert  page="/ipd/transactions/PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo=${jobTrackingTransBean.strPatientCrNo}&strAdmNo=${jobTrackingTransBean.strAdmNo}&duplicateMode=1"/>
	</logic:equal>



					<%-- <logic:notEqual value="1" name="jobTrackingTransBean" property="strSaveFlag">
							<h3 align="center"> <font color="#808080">No slip to print</font></h3>
					</logic:notEqual> --%>


				</div>
       
    </div>
  </div>
</div>


<script>

function unloadBootstrap()
{
	var styleSheet1=document.getElementById('style1');
	styleSheet1.disabled= true ;
}
window.onclick = function(event) 
{
	var styleSheet1=document.getElementById('style1');
    if (event.target == bedPopupModal) 
    {
    	styleSheet1.disabled= true ;
    }
}


</script>


<!-- MODAL VALIDATION -->
				
<div id='mainDiv' style='display:none;'>
<div class="fade" id="validateModal"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body"><h5 id='warn'></h5><p id='len'></p></div>
      <div class="modal-footer"><button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
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
        <button  type="button" class="btn btn-success"  onclick="submit()">Ok&Save</button>

      </div>
    </div>
  </div>
</div>
</div>


<div class="modal fade" id="bedPopupModal" tabindex="-1" role="dialog" aria-labelledby="bedPopupModalLabel">
 <div class="modal-dialog  modal-lg" role="document">
   <div class="modal-content">
    <div class="modal-header">    
      <h4 class="modal-title" id="myModalssLabel">Bed Dashboard Status</h4>
       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="unloadBootstrap();"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body" id="bedModalHTML"></div>
      <div class="modal-footer"></div>
    </div>
   </div>
  </div>
  
</body>


</html>