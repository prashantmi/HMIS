<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<!--
  IPD Bill Management New
  
  author: Debashis Sardar
  
  dated: 12th Mar 2013
-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>

<head>
<title>IPD Patient Provisional Billing Report</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
<link
	href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css"
	rel="stylesheet" type="text/css">

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
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>
	
	
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<style type='text/css'>

</style>

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../js/IpdBillMangTrans.js"></script>
<script language="Javascript" >

window.history.forward();

function goFunc() {
  
 	var hisValidator = new HISValidator("ipdProvisionalBillingRpt");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	     hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].CrNo.value=document.forms[0].strCrNo.value;
	    if(retVal){
				document.forms[0].hmode.value="SHOWRPT";
				document.forms[0].submit();
		}else{
		
		return false;
		}
 }
function initGoFunc(eve){
	   	
	    var flag=validateData(eve,5);
  		 if(flag){	
	   	
	   	if(eve.keyCode == 13){
												
				goFunc();
				
				return false;
			}	   	

  }else{
	   		return false;
	   }		

	   }
 </script>
</head>
<body onLoad="SetCursorToTextEnd('strCrNoId');document.forms[0].strCrNo.focus();">
<html:form action="reports/IPDPatientProvisionalBillRptBSCNT.cnt" method="post">


		<div class="viewport" id="nonPrintable">
				<div class="container-fluid">

					
					<div class="row justify-content-center">
						<div class="col-sm-12">
						<br>
							<div class="prescriptionTile">
							<div id='errMsg' class="errMsg"><bean:write name="ipdProvisionalBillingRpt" property="strErrMsg" filter="false"/></div>
                      <div id="normalMsg" class="normalMsg"><bean:write  name="ipdProvisionalBillingRpt" property="strMsg"/></div>
							<div class="row rowFlex reFlex" >
                                           <div class="col-sm-12">
                                          <p class="subHeaders"><button  type="button" class="btn btn-outline-success btn-circle1 " >
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button>&nbsp;IPD Patient Provisional Billing Report</p>
                                          
                                          </div>
                                        
                                          </div>
             
							<div class="row rowFlex reFlex" id="goid">
								<div class="col-sm-3" style="color:#0b3da1;font-weight: 700 !important;font-size: 14px;" align="right">
									<label><font color="red">*</font>CR No.</label>
								</div>
								<div class="col-sm-3">
									<crNo:crNo value="${ipdProvisionalBillingRpt.strCrNo}" className="form-control" js="onkeypress='return initGoFunc(event);'" id="strCrNoId"></crNo:crNo>
								</div>
								<div class="col-sm-2" style="padding-left: 0">
									<a href="#" class="btn btn-sm btn-success"
										onclick="return goFunc();" style="font-size: 1rem;">
										GO&nbsp;<i class="fas fa-angle-double-right"></i>
									</a>
								</div>
								<div class="col-sm-6"></div>

							</div>

<hr>
<div class="row rowFlex reFlex">
									<div class="col-sm-10"></div>
									<div class="col-sm-2" align="right">
										<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
									</div>
								</div>



	<input type="hidden" name="hmode"/>
	<input type="hidden" name="CrNo" value="${ipdProvisionalBillingRpt.strCrNo }"/>
	</div>
	</div>
	</div>
	</div>
	</div>
	</html:form>
	<tag:autoIndex></tag:autoIndex>  
</body>
</html>