
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<title>Online Request Cancellation</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css"
	rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css"
	rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css"
	rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet"
	type="text/css">


<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>

<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />


<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript"
	src="../../billing/js/OnlineReqCancellation_TransBS.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>

</head>
<body onload="showPatientDetails();"
	onFocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">

	<html:form
		action="transactions/OnlineRequestCancellationTransBSCNT.cnt"
		method="post" onsubmit="goFunc();">

		<fieldset form="form1">

			<legend class='legendHeader' id='nonPrintableLegend'>Online
				Request Cancellation</legend>
			<div class="legend2" id='nonPrintableLegend2'>
				<!-- <button  type="button" id="cancelButton" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
				<button type="button"
					class="btn btn-outline-danger mt-1 btn-circle cancelbtn"
					onclick="openMenu('SIGLEMENUHOME','')">
					<i class="fas fa-ban iround" title="Cancel"></i>
				</button>
				<button type="button" id="lastbuttons"
					class="btn btn-outline-success mt-1 btn-circle savebtn "
					tabindex='2' onclick='validate1();' style="display: none;">
					<i class="fas fa-save iround" title="Save"></i>
				</button>
			</div>
			<div id='normalMsg' class="normalMsg">
				<bean:write name="OnlineRequestCancellationTransBean"
					property="strMsg" filter="false" />
			</div>
			<div id='errMsg' class="errMsg">
				<bean:write name="OnlineRequestCancellationTransBean"
					property="strErrMsg" filter="false" />
			</div>
			<div id="warningMsg" class="warningMsg">
				<bean:write name="OnlineRequestCancellationTransBean"
					property="strWarningMsg" filter="false" />
			</div>


			<div id="demographicInfo" style="display: none" align="center">
				<bean:write name="OnlineRequestCancellationTransBean"
					property="strPatientDetailsView" filter="false"></bean:write>
			</div>

			<div class="viewport" id="nonPrintable">
				<div class="container-fluid ">
					<div class="prescriptionTile">
						<div class="row rowFlex reFlex" id='goid'>
							<div class="col-sm-2" align="right">
								<font color="red">*</font>CR No.
							</div>
							<div class="col-sm-2">
								<crNo:crNo id="strCrNoId"
									value="${OnlineRequestCancellationTransBean.strCrNo}"
									js="onkeypress='return initGoFunc(event);'"
									className="form-control"></crNo:crNo>
							</div>
							<div class="col-sm-2" style="padding-left: 0;">
								<span class="fas fa-search"
										style="cursor: pointer; cursor: hand;"
										id="searhPatientImageId" title="Click here for Patient Search"
										name='searchPatient' data-toggle="modal"
										data-target="#searchModel" onclick="fetchPatientList_BS1(1,1,5)"></span>
								<a href="#" class="btn btn-sm btn-success"
									onclick="return goFunc();" style="font-size: 1rem;">
									GO&nbsp;<i class="fas fa-angle-double-right"></i>
								</a>
							</div>
							<div class="col-sm-6"></div>
						</div>

						<div id="details" style="display: none">

							<p class="subHeaders">
								<i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Online Request
								
							</p>
						</div>



						<div id="onlinerequest" style="display: none">
							<bean:write name="OnlineRequestCancellationTransBean"
								property="strOnlineReqDtl" filter="false" />
						</div>

						<div id="combo" style="display: none">
					<br>
							
							<div class="row rowFlex reFlex">
							<div class="col-sm-2" align="right">
									<font color="red">*</font>Cancel By
								</div>
								<div class="col-sm-2">
									<select name="strCancelledBy"
										class="browser-default custom-select">
										<bean:write name="OnlineRequestCancellationTransBean"
											property="strCancelledBy" filter="false" />
									</select>
								</div>
								<div class="col-sm-2" align="right">
									<font color="red">*</font>Cancellation Reason
								</div>
								<div class="col-sm-2">
									<select name="strCancelReason"
										class="browser-default custom-select" onchange="showText();">
										<bean:write name="OnlineRequestCancellationTransBean"
											property="strCancelReason" filter="false" />
									</select>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="strOtherReason"
										value="" maxlength="" onkeypress="">
								</div>
								<div class="col-sm-2"></div>

							</div>

						</div>




						<div id="lastbuttons" style="display: none"></div>
						<div id="onlyClearbutton" style="display: block"></div>
						<hr>
						<div class="row rowFlex reFlex">
							<div class="col-sm-10"></div>
							<div class="col-sm-2" align="right">
								<i class="fas fa-asterisk"
									style="color: red; font-size: smaller;"></i>Fields Mandatory
							</div>
						</div>
						<input type="hidden" name="hmode" /> <input type="hidden"
							name="CrNo"
							value="${OnlineRequestCancellationTransBean.strCrNo }" /> <input
							type="hidden" name="chkValue" /> <input type="hidden"
							name="strCRNoSatus"
							value="${OnlineRequestCancellationTransBean.strCRNoSatus }" /> <input
							type="hidden" name="gblCRValue" />
					</div>
				</div>
			</div>
		</fieldset>
	</html:form>

	<div class="popup" id="popup" style="display: none"></div>
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