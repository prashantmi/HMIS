<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>

<html>
<head>
<title>Patient Discharge Cancellation</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
<link href="../css/newlayout.css" rel="stylesheet" type="text/css">
<%-- 
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
 --%>



<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="JavaScript" src="../js/dischargeCancelBS.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<script type="text/javascript">
var child = null;
var popIndex = 0;
var gblCntrlObj = null;

$(document).ready(function () {
	document.forms[0].strAdmnNo.focus();
	  $("body").click(function(e) {     
	    if($(e.target).attr('id') === "patInfo") {
	        $("#patSideListId").show();
	    }
	    else {
	        $("#patSideListId").hide();
	    }
	  });
	});
	
</script>
<style> 
</style>
</head>
<body onload="butdis(),hlpOnLoad_disCancel();checkMsg();" onUnload="closePopUp();">
<html:form action="/transactions/DischargeCancelTransBSCNT.cnt" method="post" onsubmit="return goFunc();">
<fieldset>
<legend class='legendHeader' id='nonPrintableLegend'>Discharge Cancellation</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="savebutton" type="button" class="btn btn-outline-success mt-1 btn-circle savebtn" data-toggle="modal" data-target="#validateModal" onClick="return validate1();" style="display: none;">
		<i class="fa fa-save iround"  title="Save"></i>
	</button>    												                 
  </div>  

<div id="errID" class="alert alert-danger alert-dismissible fade show" style="display: none;"><bean:write name="dischargecancelTransBean" property="strErrMsgString" /></div>
<div class="alert alert-success alert-dismissible fade show" id="normalMsg" style="display: none;"><bean:write name="dischargecancelTransBean" property="strNormalMsgString"/></div>

 <div id="patDtlTld" style="display: none">		
	<pDtl:patDtlNew crNo="${dischargecancelTransBean.strCrNo}" address="false"></pDtl:patDtlNew>	
</div>	
				
				<div  class="viewport" id="nonPrintable">
				<div class="container-fluid">
				<div class="prescriptionTile">	
		        <div id="patTldglbdiv">
		           <div id="gobox">
	                <div class="row rowFlex reFlex">
	                     <div class="col-sm-3" align="right"><label>Admission No<font color="red">*</font></label></div>
	                     <div class="col-sm-2"><input type="text" class="form-control" name="strAdmnNo" value="${dischargecancelTransBean.strAdmnNo}" maxlength="15" onkeypress="return validateData(event,5)" onkeyup="return goFuncOnEnter(event);" />
			                                       </div>
	                     <div class="col-sm-3" align="left">
		                     <a href="#" class="btn btn-sm btn-success go" onclick="goFunc();"  data-toggle="modal" data-target="#validateModal">
	                                                      GO&nbsp;<i class="fas fa-angle-double-right"></i>						                             						                            
		                     </a>
	                     </div>
	                     <div class="col-sm-4"></div>
	                  </div>
	                  
	                  
				           </div>
				           </div>
				           
				           <div id="admDtlTld" style="display: none">
		                    <bean:write name="dischargecancelTransBean" property="admissionDetailValues" filter="false"/>
	                       </div>
	                       <div id="transChng" style="display:none">	
	                        <p class="subHeaders"><i class="fas fa-undo" style="font-size: 26px">&nbsp;</i>Discharge Cancellation Record Entry</p>
                               </div>	
                                <div id="id1" align="center">
	                                <bean:write name="dischargecancelTransBean" property="changeOfBed" filter="false"/> 
	                                 </div>
	                            <div id="disBnR" style="display: none">
								<div class="row rowFlex reFlex">
								<div class="col-sm-3"><div align="right">Discharge Cancellation By:</div></div>
								<div class="col-sm-3"><select  class="form-control" title="Employee Code and List of Doctors" name="strRmk">
										  <bean:write name="dischargecancelTransBean" property="strRmk" filter="false" />
									   </select></div>
								<div class="col-sm-3"></div>
								<div class="col-sm-3"></div>
				           </div>
				           <div class="row rowFlex reFlex">
								
								<div class="col-sm-3"><div align="right"><font color="red">*</font>Cancellation Remarks:</div></div>
								<div class="col-sm-9">
									<textarea rows="3" cols="25" class="form-control" name="strRsn" id="remarksId"></textarea>	
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
					        <div class="col-sm-10"></div>
					              <div class="col-sm-2" align="right">
					         <i class="fa fa-asterisk" style="color: red;font-size: smaller;"></i>Fields Mandatory</div>				
				           </div>
	
	
	
 
<div class="modal-container" style="display: none;">
	<div id="myModal" class="modal fade" role="dialog">  
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content -->
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">Bed Dashboard Status</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body" id="modalSpaceId">
	        
	      </div>
	    </div>
	
	  </div>
	</div>
	 </div> 
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
<input type="hidden" name="hmode" value=""/>	
<input type="hidden" name="chkIndx" value=""/>
<input type="hidden" name="cnt" value="DischargeCancelTransBSCNT.cnt">
<input type="hidden" name="strAge" value="">
<input type="hidden" name="strAgeUnit" value="">
<input type="hidden" name="strSexCode" value="">
<input type="hidden" name="strErrMsgString" value="${dischargecancelTransBean.strErrMsgString}">
<input type="hidden" name="strNormalMsgString" value="${dischargecancelTransBean.strNormalMsgString}">
<input type="hidden" name="strCrNo" value="${dischargecancelTransBean.strCrNo}"/>
</div>
</div>
</div>
</fieldset>
</html:form>
<script>
if(document.forms[0].strCrNo.value.length>=12){
	getAgeSex();
}
 </script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>