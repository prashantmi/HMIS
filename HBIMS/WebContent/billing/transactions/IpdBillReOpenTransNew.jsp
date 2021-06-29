<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
 Ipd Bill Re-Open Transaction JSP
  
 author : Debashis Sardar
 
 date: 10-Dec-2011
 -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head><meta charset=utf-8>
<title>IPD Bill Re-Open</title>

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
 
 
 
<!-- <link href="../billing/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
 -->
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<style type='text/css'>

</style>

<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/IpdBillReOpenTransBS.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/billing.js"></script>
<script language="Javascript" src="/HBIMS/billing/js/SearchReceiptDetailsBS.js"></script>
<script type="text/javascript">
	 function viewBill(event)
 {
  var wardCode=1;
  var deptCode=1;
  var accountno=document.forms[0].strPatAccNo.value;
  var billNo=document.forms[0].strRcptNo.value;
  var chk=accountno+"@"+"";
  var reportid='1';
  var reportFormat='pdf';
  
  var url="/HBIMS/billing/transactions/IpdBillManagementTransBSCNT.cnt?hmode=VIEWRPT&deptCode="+deptCode+"&wardCode="+wardCode+"&chk="+chk+"&billNo="+billNo+"&rptMode=1";
  
  openDependentPopup(url,event,500,800)
 }
	
</script>
</head>
<body onLoad="document.forms[0].strRcptNo.focus(),showPatientDetails();">
<html:form action="transactions/IpdBillReOpenBSCNT.cnt"
	 method="post">
	 
	 <fieldset>
	
  <legend class='legendHeader' id='nonPrintableLegend'>IPD Bill Re-Open</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="lastbuttons" class="btn btn-outline-success mt-1 btn-circle savebtn " tabindex='2' onclick='return validate1();' name="patientAdmissionModiTransBean" style="display: none;">					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>
									                 
  </div>
  	
               <div id='patdtltld'>
 				 <bean:write name="IpdBillReOpenTransBean" property="strPatientDtls" filter="false"/>
 				 </div>
 			 
<div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  <div class="col-md-12">
  
<div class="prescriptionTile">
	   
    <div id='errMsg' class="errMsg"><bean:write name="IpdBillReOpenTransBean" property="strErrMsg" filter="false"/></div>
    <div id='normalMsg' class="normalMsg"><bean:write name="IpdBillReOpenTransBean" property="strMsg" filter="false"/></div>
   <div id="warningMsg" class="warningMsg"><bean:write
		name="IpdBillReOpenTransBean" property="strWarningMsg" filter="false" /></div>
    
		<%-- <tag:tab tabList="${IpdBillReOpenTransBean.lhm}" selectedTab="IPDBILLREOPEN" align="center"
			width="TABLEWIDTH"></tag:tab> --%>
			<div class="row rowFlex reFlex" >
			<div class="col-sm-6" id="billdtlId" style="display: none;"><p class="subHeaders"><i class="fas fa-money-bill" style="font-size: 26px;">&nbsp;</i>bill Details</p></div>
			<div class="col-sm-6" align="right" id="viewBil" style="display: none">
			 <a type="button" class="btn btn-info" id="viewBill" style="cursor: pointer;" onclick="viewBill(event)" 
					title="View Bill">View Bill</a></div>
			</div>
			
			<div class="row rowFlex reFlex" id="goid">
								<div class="col-sm-2" align="right">
									<label><font color="red">*</font>IPD Bill No.</label>
								</div>
								<div class="col-sm-2">
												<input type="text" class="form-control" name="strRcptNo"  value="${IpdBillReOpenTransBean.strRcptNo}" maxlength="15" onkeypress="return initGoFunc(event);">

								</div>
								<div class="col-sm-2" style="padding-left: 0">
									<span class="fas fa-search"
										style="cursor: pointer; cursor: hand;"
										id="searhPatientImageId" title="Click here for Patient Search"
										name='searchPatient' data-toggle="modal"
										data-target="#searchModel" onclick="checkForError();"></span>
									<a href="#" class="btn btn-sm btn-success"
										onclick="return goFunc();" style="font-size: 1rem;">
										GO&nbsp;<i class="fas fa-angle-double-right"></i>
									</a>
								</div>
								<div class="col-sm-6"></div>

							</div>
    <table class="TABLEWIDTH" cellspacing="1px" align="center">
		
		
		<%-- <tr>
			
			<td class="LABEL" ><font color="red">*</font>IPD Bill No.</td>
			<td class="CONTROL" colspan="4">
			<input type="text" class="txtFldMax" name="strRcptNo"  value="${IpdBillReOpenTransBean.strRcptNo}" maxlength="15" onkeypress="return initGoFunc(event);">
 				<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" title="click here for patient search"  name='searchPatient' onclick="checkForError();"/>
				<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go"  onclick="return goFunc();" onkeypress="return goFunc();"/>
				 
			</td>
		</tr>
		 --%>
	</table>
	<div  id="details" style="display: none">
			
 				
 </div>
<div  id="billDetails" style="display: none">
								<div class="row rowFlex reFlex">
									<div class="col-sm-3" align="right">
										<label>Bill Date</label>
									</div>
									<div class="col-sm-3">
										<bean:write name="IpdBillReOpenTransBean"
											property="strBillDate" filter="false" />
									</div>
								
								
									<div class="col-sm-3" align="right">
										<label>Approved By</label>
									</div>
									<div class="col-sm-3">
										<bean:write name="IpdBillReOpenTransBean"
											property="strApprovedBy" filter="false" />
									</div>
								</div>
								<div class="row rowFlex reFlex">
									<div class="col-sm-3" align="right">
										<label>Expense Amount</label>
									</div>
									<div class="col-sm-3">
										<bean:write name="IpdBillReOpenTransBean"
											property="strExpenseAmt" filter="false" />
									</div>
							
								
									<div class="col-sm-3" align="right">
										<label>Received Amount</label>
									</div>
									<div class="col-sm-3">
										<bean:write name="IpdBillReOpenTransBean"
											property="strReceiveAmt" filter="false" />
									</div>
								</div>



								<!-- <div id="lastbuttons" style="display: none">
	<table border="0" class="TABLEWIDTH" cellspacing="1px" align="center">
		<tr>
			<td align="center">
			
			 <a href="#" class="button"	onClick="return validate1();"><span class="save">Save</span></a>
			 <a href="#" class="button"	onClick="showFirstPage();"><span class="clear">Clear</span></a> 
			<a href="#" class="button"	onClick="initPage();"><span class="cancel">Cancel</span></a> 
		</tr>
	</table>
	</div> -->
	</div>
<div id="onlyClearbutton" style="display:block">	

</div>

                           <hr>
							<div class="row rowFlex reFlex">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" align="right">
									<i class="fas fa-asterisk"
										style="color: red; font-size: smaller;"></i>Fields Mandatory
								</div>
							</div>
	
	<input type="hidden" name="hmode"/>
	<input type="hidden" name="strCrNo" value="${IpdBillReOpenTransBean.strCrNo }"/>
	<input type="hidden" name="chkValue"/>
	<input type="hidden" name="RcptNo" value="${IpdBillReOpenTransBean.strRcptNo }"/>
	<input type="hidden" name="strPatAccNo" value="${IpdBillReOpenTransBean.strPatAccNo }"/>
	<input type="hidden" value="0" name="popId">
	<input type="hidden" name="selectedTab">
    <input type="hidden" name="strBillDate" value="${IpdBillReOpenTransBean.strBillDate }"/>
    <input type="hidden" name="strApprovedBy" value="${IpdBillReOpenTransBean.strApprovedBy }"/>
    <input type="hidden" name="strExpenseAmt" value="${IpdBillReOpenTransBean.strExpenseAmt }"/>
    <input type="hidden" name="strReceiveAmt" value="${IpdBillReOpenTransBean.strReceiveAmt }"/>
    <input type="hidden" name="strBillCatGrp" value="${IpdBillReOpenTransBean.strBillCatGrp }"/>
    <input type="hidden" name="strBillCatCode" value="${IpdBillReOpenTransBean.strBillCatCode }"/>
	
	</div>
	</div>
	</div>
	</fieldset>
	</html:form>
	<tag:autoIndex></tag:autoIndex> 
	
	<div class="modal fade " id="searchModel" tabindex="-1" role="dialog"
		aria-labelledby="validateModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Paitent Listing</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body" id="fetchRecordDivId"></div>

				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
		 
</body>
</html>