
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Tracking</title>


<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.min.css" rel="stylesheet">

<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="JavaScript" src="../js/patientTracking.js"></script>

<script type="text/javascript">

var child = null;
var popIndex = 0;
var gblCntrlObj = null;
</script>

<style type="text/css">
  
    
    
</style>

</head>
<body onload="CNTIni(),butdis(),hlpOnLoad_disCancel();changeRadio(document.forms[0].strCase);" onUnload="closePopUp();">


<html:form action="/transactions/PatientTrackingTransBSCNT.cnt" method="post" onsubmit="return goFunc();">


<fieldset form="form1" >
	
  <legend class='legendHeader' id='nonPrintableLegend'>Patient Tracking</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>		
	</button>	
	<button  type="button" id="viewbutton" class="float-right btn btn-outline-primary  mt-1 btn-circle savebtn"  onClick="reopenMov();"  data-toggle="modal" data-target="#validateModal" style="display: none;">					
		<i class="fas fa-eye iround"  title="View"></i>
	</button>												                 
  </div>
  <div class="container-fluid">
  <div class="viewport">
         <div id="blanket" style="display: none; height: 543px;"></div>
           <div id="errMsg" class="errMsg"><bean:write name="patientTrackingTransBean" property="strErrMsgString" /></div>
            <div class="normalMsg" id="normalMsg"><bean:write name="patientTrackingTransBean" property="strNormalMsgString"/></div>
<%-- <tag:tab tabLabel="Patient Tracking" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab> --%>
	<div id="patDtlTld" style="display: none">		
	          <pDtl:patDtlNew crNo="${patientTrackingTransBean.strCrNo}" address="false"></pDtl:patDtlNew>
		</div>
			              <div class="prescriptionTile" >
	                      <div id="patTldglbdiv" >
	   
				         <div id="gobox">
	                     <div class="row rowFlex reFlex" >
	                     <div class="col-sm-8"></div>
	                     <div class="col-sm-2">
	                     <label class="container">Admission No.
  							<html:radio property="strCase" name="patientTrackingTransBean" value="1" onclick="changeRadio(this);hideDtl(this);" >&nbsp;</html:radio>
  							<span class="checkmark"></span>
						</label>
	                     </div>
	                     <div class="col-sm-2">
	                     <label class="container">CR No.
  							<html:radio property="strCase" name="patientTrackingTransBean" value="2" onclick="changeRadio(this);hideDtl(this);" >&nbsp;</html:radio>
  							<span class="checkmark"></span>
						</label>
						</div>
	                     </div>
	                     
	                    
	                     <div class="row rowFlex reFlex" id='admNoId'>
	                     <div class="col-sm-3" align="right"><label>Admission No<font color="red">*</font></label></div>
	                     <div class="col-sm-2"><input type="text" name="strAdmnNo" value="${patientTrackingTransBean.strAdmnNo}" maxlength="15" 
			                                                   onkeypress="return validateData(event,5)" onkeyup="return goFuncOnEnter(event);" class='form-control' />
			                                       </div>
	                     <div class="col-sm-7" align="left"><a href="#" class="btn btn-sm btn-success" onclick="return goFunc();"  style="font-size: 1rem;" data-toggle="modal" data-target="#validateModal">
							    	GO&nbsp;<i class="fas fa-angle-double-right"></i></a></div>
	                    
	                     </div>
	                      <div class="row rowFlex reFlex" id='crNoId'>
		                     <div class="col-sm-3" align="right"><label>CR No.<font color="red">*</font></label></div>
		                     <div class="col-sm-2"><crNo:crNo value="${patientTrackingTransBean.strCrNo}" js="onkeypress='return validateData(event,5)' onkeyup='return goFuncOnEnter(event);'" className='form-control'></crNo:crNo></div>
		                     <div class="col-sm-7" align="left"> 
							 	<a href="#" class="btn btn-sm btn-success" onclick="return goFunc();"  style="font-size: 1rem;" data-toggle="modal" data-target="#validateModal">
							    	GO&nbsp;<i class="fas fa-angle-double-right"></i></a>
							 </div>	                     
	                     </div>
	                     </div>
	                     
	                     
	                     
											
											
									           
			           
		<div id="admDtlTldglbdiv" style="display: none"></div>
	<div id="admDtlTld" style="display: none"></div>
	
		<div id="movementDtlId" style="display: none">
	      <bean:write name="patientTrackingTransBean" property="strMovementDtl" filter="false"/>
	</div>	
	<div id="movDetails" style="display: none">	
				        
	</div>
		
		</div>	
	<div id="patDemDtlId" style="display: none;">
	<!-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='0'>
	  <tr>
		 <td width='5%' class='TITLE' align='center'>
		   <div id="plusonLineId" style="display: none"><img style='cursor: pointer; cursor: hand'
				src="../../hisglobal/images/plus.gif" name="plusonLine" title="Show Details" onclick="showDetails();" /></div>
		   <div id="minusonLineId"><img style='cursor: pointer; cursor: hand;'
				src="../../hisglobal/images/minus.gif" name="minusonLine" title="Hide Details" onclick="hideDetails();"></div>
		   </td>
		   <td colspan="3" width="95%" class="TITLE">Patient Demographic Details</td>
	  </tr>
	</table> -->	
	</div>
	<div id="patDtlTld" style="display: none">		
	<%--  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='25%' class='LABEL' align='center'><div align="right">CR No.</div></td>
			<td width='75%' class='CONTROL' align='center'><div align="left">
			<bean:write name="patientTrackingTransBean" property="strCrNo"/></div>
			</td>			
		</tr>
	</table> --%>
	 <%-- <pDtl:patDtlTr crNo="${patientTrackingTransBean.strCrNo}" address="false"></pDtl:patDtlTr>
	  --%>
	 
	 
		
	<%-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px' id='admDtlId'>
		<tr>
			<td width='25%' class='LABEL' align='center'><div align="right">Admission Date/Time</div></td>
			<td width='25%' class='CONTROL' align='center'><div align="left">
			<bean:write name="patientTrackingTransBean" property="strAdmnDate"/></div>
			</td>
			<td width='25%' class='LABEL' align='center'><div align="right">Discharge Date/Time</div></td>
			<td width='25%' class='CONTROL' align='center'><div align="left">
			<bean:write name="patientTrackingTransBean" property="strDischargeTime"/></div>
			</td>
		</tr>		
	</table> --%>
	<%-- <nav id="admDtlId" class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #efe3f08a !important; line-height: 1.5">
                         <div class="navbar-header" align="center">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem; color: black;">Admission Date/Time:<bean:write name="patientTrackingTransBean" property="strAdmnDate"/></a>
				        </div>
				        <ul class="navbar-nav mx-auto">
				        <li><a class="navbar-brand" href="#" style="font-size: 1.0rem; color: black;">Discharge Date/Time:<bean:write name="patientTrackingTransBean" property="strDischargeTime"/></a> </li>
				        </ul>
				        </nav> --%>
	</div>
	<div id="admDtlTldglbdiv" style="display: none"></div>
	<div id="admDtlTld" style="display: none"></div>
	
	<div id="movementDtlId" style="display: none">
	<!--  <nav id="navId" class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 1.5">
                         <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem;">Patient Admission List</a>
				        </div>
				        </nav> -->
	<bean:write name="patientTrackingTransBean" property="strMovementDtl" filter="false"/>
	</div>
	<div id="movDetails" style="display: none">	
	 <!-- <nav id="nav" class="navbar navbar-expand-sm bg-primary navbar-dark" style="padding: .1rem 1rem;border-radius: .3rem; background-color: #086ea5 !important; line-height: 1.5">
                         <div class="navbar-header" align="left">
				              <a class="navbar-brand" href="#" style="font-size: 1.0rem;">Patient Movement Details</a>
				        </div>
				        </nav> -->
				        
	</div>
	 <hr id="hrid">
                           <div class="row rowFlex reFlex" id="footerid">
					        <div class="col-sm-10"></div>
					              <div class="col-sm-2" align="right">
					         <i class="fa fa-asterisk" style="color: red;font-size: smaller;"></i>Fields Mandatory</div>				
				           </div>
	<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr class="FOOTER"> 
    <td colspan="8"><font size="2" color="red">*</font>Mandatory Fields</td>
  </tr>
  </table> -->
 <!--  <table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td align="center">		
		<img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-clr.png" title="Clear the Record" onClick="fun();">
		<img style='cursor:pointer;cursor:hand' src="../../hisglobal/images/btn-ccl.png" title="Go back to the Main Page" onClick="cancelPage();">
	</td>
	</tr>
</table> -->

<!-- <div id='mainDiv1' style=''>
<div class="modal fade " id="searchModel"   tabindex="-1" role="dialog" aria-labelledby="validateModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
    <div class="modal-header">
	 <h4 class="modal-title">Status Details</h4>
		</div>
      <div  class="modal-body" id ="fetchRecordDivId"  >
      
   
     
      </div>
     
    </div>
  </div>
</div>
</div>  -->


<input type="hidden" name="hmode" value=""/>	
<input type="hidden" name="cnt" value="">
<input type="hidden" name="strErrMsgString" value="${patientTrackingTransBean.strErrMsgString}">
<input type="hidden" name="strNormalMsgString" value="${patientTrackingTransBean.strNormalMsgString}">
              
									
	                
	           </div>
	      </div>
	 </div> 
</fieldset>
</html:form>
<%-- <tag:autoIndex></tag:autoIndex> --%>
<script>
$(document).ready(function(){
    $('.login-info-box').fadeOut();
    $('.login-show').addClass('show-log-panel');
});




/* $('.login-reg-panel input[type="radio"]').on('change', function() {
    if($('#log-login-show').is(':checked')) {
        $('.register-info-box').fadeOut(); 
        $('.login-info-box').fadeIn();
        
        $('.white-panel').addClass('right-log');
        $('.register-show').addClass('show-log-panel');
        $('.login-show').removeClass('show-log-panel');
        
    }
    else if($('#log-reg-show').is(':checked')) {
        $('.register-info-box').fadeIn();
        $('.login-info-box').fadeOut();
        
        $('.white-panel').removeClass('right-log');
        
        $('.login-show').addClass('show-log-panel');
        $('.register-show').removeClass('show-log-panel');
    }
});
   */
</script>
</body>
</html>