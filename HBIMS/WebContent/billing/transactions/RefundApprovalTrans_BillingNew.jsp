<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head>
<style type="text/css">
        

            </style>
<title>On-Line Refund Approval</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
 <link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../../billing/js/OnLineRefundApprovalTransBS.js"></script>

</head>
<body onLoad="checkRadio();checkMsg();" >

<html:form action="transactions/OnLineRefundApprovalBSCNT.cnt" method="post">

 <fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'> On-Line Refund Approval </legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="lastbuttons" class="btn btn-outline-success mt-1 btn-circle savebtn " tabindex='2' onclick='save();' name="patientAdmissionModiTransBean" >					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>
							                 
  </div>
<div  class="viewport" id="nonPrintable">

<div  class="alert alert-danger alert-dismissible fade show" id="errMsg"  style="display:none;"><bean:write name="onLineRefundApprovalTransBean" property="strErrMsg" filter="false"/></div> 
<div  class="alert alert-success" id="normalMsg" style="display:none;"><bean:write name="onLineRefundApprovalTransBean" property="strMsg" filter="false"/></div>

   
<div class="prescriptionTile" >

	
   
   <div id='mainDiv2'>
	<div class="modal fade" id="todayListModal"    tabindex="-1" role="dialog" aria-labelledby="todayListModalLabel" aria-hidden="true">
  	<div class="modal-dialog modal-lg"  role="document">
    <div class="modal-content">
    <div class="modal-header">
	 <h7 class="modal-title" >Today Approved List</h7>
		<button type="button" class="close" data-dismiss="modal">×</button></div>
      <div class="modal-body" >
		<div style="overflow-x:auto;height: 200px;">
			<div class="row rowFlex reFlex" id='todayList'>
   				<div class="col-sm-12" style="padding: 0;">
   					<bean:write name="onLineRefundApprovalTransBean" property="strTodayApprovalListDtls" filter="false"/>   			
   				</div>
   			</div>
		</div>              
      </div>
      <div class="modal-footer" ></div>
    </div>
  </div>
</div>
</div>
   <div class="row rowFlex reFlex">   
	   <div class="col-sm-12" style="padding: 0;">  
	   		<fieldset form="form1" style="height: 100%;">
		   	<legend class='legendHeader' id='nonPrintableLegend'>Refund Request</legend>
			   <div class="legend7" id='nonPrintableLegend3'>
				   <select name="strRequestMode" class="browser-default custom-select" onchange="filterReqData(this)">
				   <option value="1">Last Five Requests</option>
				   <option value="2">All Requests</option></select>
				   
			    </div>
			    <div class="legend5" id='nonPrintableLegend2'>
				   <button  type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" name="refreshbutton" onclick="getRefundRequestDtls();">
						<i class="fas fa-sync iround"  title="Refresh"></i>
					</button>	
					<button  type="button" class="float-right btn btn-outline-secondary mt-1 btn-circle clearbtn" data-toggle="modal" data-target="#todayListModal" name="eyebutton" onclick="showTodayList();">
						<i class="fas fa-eye iround"  title="Today Approved List"></i>
					</button>	
			    </div>
			    <div id="refundCRNODivId" style="display: block">
			 		<bean:write name="onLineRefundApprovalTransBean" property="strRefundRequestDtls" filter="false"/>  
			    </div>	   
	    	</fieldset>
	  	</div>  
   </div>
 
<div class="row rowFlex reFlex newrow">
   <div class="col-sm-7">
   </div>
   <div class="col-sm-2">
   <select name='strSearchMode' class='browser-default custom-select'><option value='1'>CR No</option><option value='2'>Patient Name</option></select>
   </div>
   <div class="col-sm-2">
         <input type="text" name="strSearchValue" class="form-control" maxlength="15" onkeypress="return validateData(event,9);">
   </div>
   <div class="col-sm-1" style="padding: 0;">
            <button type="button"class="btn btn info" onclick="goFunc();"  onKeyPress="goFunc();" style="color: white;"><i class="fas fa-search"></i></button>
   </div>
   </div>
   
      <div id="tariffDivId" style="display: none"></div>
      <br>
 
   <div class="row rowFlex reFlex">
   <div class="col-sm-2">
   <label><font color="red">*</font>Refund By</label>
   </div>
   <div class="col-sm-2">
   <select name="strRefundedByComboVal" class="browser-default custom-select">
				<bean:write name="onLineRefundApprovalTransBean"
					property="strRefendedBy" filter="false" />
			   </select>
   </div>
   <div class="col-sm-2">
   <label><font color="red">*</font>Remarks</label>
   </div>
   <div class="col-sm-6">
  <textarea rows="2" class="form-control" cols="20" name="strRemarks"></textarea>
   </div>
   
   </div>
 
   
                          <hr>
	                      <div class="row rowFlex reFlex">
									<div class="col-sm-10"></div>
									<div class="col-sm-2" align="right">
										<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
									</div>
								</div>
		<input type="hidden" name="hmode" value="">
		<input type="hidden" name="strRequestValue" value="0">
	</div>
	
	</div></fieldset>
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>