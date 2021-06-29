<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>


<html>
<head>
<title>Admission Reprint</title>

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


<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../ipd/js/ADT_Trans.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../ipd/js/admReprintBS.js"></script>
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
</script>

<style type="text/css">

@media print 
{ 
		#nonPrintable 
		{
		 display: none; 
		}		
}
</style>
</head>
<body onLoad="reprint();dischargePeprint();openPrintPopUp();markCheck();CNTIni(),butdis(),hlpOnLoad_disCancel();changeRadio(document.forms[0].strCase);checkMsg();">
<html:form action="/transactions/AdmissionReprintTransBSCNT.cnt" method="post" onsubmit="return goFunc();">
<fieldset>

<legend class='legendHeader' id='nonPrintableLegend'>Admission Reprint</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>	
	<!-- <button  id="printbutton" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fa fa-print iround"  title="Print Last Admission Slip"></i>
	</button> -->
	<button  id="savebutton" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#validateModal" onClick="return validate1();" style="display: none;">
		<i class="fa fa-print iround"  title="Print Last Admission Slip"></i>
	</button>
    												                 
  </div>
  
<div class="container-fluid">
<div class="viewport" id="nonPrintable">

<div id="errID" class="alert alert-danger" style="display: none;"><bean:write name="admissionReprintTransBean" property="strErrMsgString" /></div>
<div class="alert alert-success" id="normalMsg" style="display: none;"><bean:write name="admissionReprintTransBean" property="strNormalMsgString"/></div>
<%-- <tag:tab tabLabel="Admission Reprint Details" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab> --%>
	
	
	<div id="patDtlTld" style="display: none"><pDtl:patDtlNew crNo="${admissionReprintTransBean.strCrNo}" address="false"></pDtl:patDtlNew></div>	
	<div id="admDtlTld" style="display:none"><aDtl:addDtlNew admNo="${admissionReprintTransBean.strAdmnNo}"></aDtl:addDtlNew></div>
	
	
	
	<div class="prescriptionTile">
	<div id="patTldglbdiv">
	
	                   <div id="gobox">
	                     <div class="row rowFlex reFlex" >
		                     <div class="col-sm-8"></div>
		                     <div class="col-sm-2">
		                     	<label class="container">Admission No.
		                     		<html:radio property="strCase" name="admissionReprintTransBean" value="1" onclick="changeRadio(this);hideDtl(this);" >&nbsp;</html:radio>
		                     		<span class="checkmark"></span>
							 	</label>
							 </div>
							<div class="col-sm-2">
								<label class="container">CR No.
								<html:radio property="strCase" name="admissionReprintTransBean" value="2" onclick="changeRadio(this);hideDtl(this);" >&nbsp;</html:radio>
								<span class="checkmark"></span>
								</label>
							</div>
	                    </div>
	                     
	                     <div class="row rowFlex reFlex" >
	                     <div class="col-sm-3" align="right"><label>Re-Print Type</label></div>
	                     <div class="col-sm-3"><select name="rePrintType" class="browser-default custom-select" onChange="fun();">
		                                                     <option value="1" selected="">Admission Slip</option>
		                                                     <option value="2" >Discharge Card</option>
		                                                     <option value="3" style="display: none;">Visitor Pass Slip</option>
		                                       </select></div>
	                     <div class="col-sm-3" id="reprintId" align="right"><label>Re-Print Charge</label></div>
	                     <div class="col-sm-3" id="reprintChrg"><bean:write name="admissionReprintTransBean" property="strRePrintCharge"  filter="false"/><i class="fas fa-rupee-sign"></i></div>
	                     </div>
	                     
	                     <div class="row rowFlex reFlex" id='admNoId'>
	                     <div class="col-sm-3" align="right"><label>Admission No<font color="red">*</font></label></div>
	                     <div class="col-sm-3"><input class="form-control" type="text" tabindex='1' name="strAdmnNo" value="${admissionReprintTransBean.strAdmnNo}" 
			                                        maxlength="15" onkeypress="return goRetFunc(event);return validateData(event,5);" />
			                                       </div>
	                     <div class="col-sm-3" align="left"><a href="#" class="btn btn-sm btn-success" onclick="goFunc();"  data-toggle="modal" data-target="#validateModal" style="font-size: 1rem;">
	                             	Go&nbsp;<i class="fas fa-angle-double-right"></i>
	                             </a></div>
	                     <div class="col-sm-3"></div>
	                     </div>
	                      <div class="row rowFlex reFlex" id='crNoId'>
	                     <div class="col-sm-3" align="right"><label>CR No.<font color="red">*</font></label></div>
	                     <div class="col-sm-3"><crNo:crNo id="strCrNoId" className="form-control" value="${admissionReprintTransBean.strCrNo}" 
	                                     js="onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo></div>
	                     <div class="col-sm-3" align="left"><a href="#" class="btn btn-sm btn-success" onclick="goFunc();"  data-toggle="modal" data-target="#validateModal" style="font-size: 1rem;">
	                             	Go&nbsp;<i class="fas fa-angle-double-right"></i>
	                             </a></div>
	                     <div class="col-sm-3"></div>
	                     </div>
	                     </div>
	                     <div id="movementDtlId" style="display: none">
	                     <bean:write name="admissionReprintTransBean" property="strPatientAdmndtl" filter="false"/>
	                     </div><hr>
	                     <div id="admDtlTldglbdiv" style="display:none">
	                     <p class="subHeaders"><i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Admission Details</p>
	                      </div>
	                      <div id="id1" style="display:none">
	                      <div id="VisitPassReprint"></div>    
	                          
                               <div class="row rowFlex reFlex">
                               <div class="col-sm-3" align="right"><label>Total Re-Print Charges</label></div>
                               <div class="col-sm-3" align="left"><div id="totReprintChrg"></div></div>
                               <div class="col-sm-3" align="right"><label>Re-Print Date</label></div>
                               <div class="col-sm-3" align="left"> <font size="2" color="blue">
						       <bean:write name="admissionReprintTransBean" property="strCtDt"  filter="false"/></font></div>
                               </div>
	                      </div>   
	                     <div class="row rowFlex reFlex">
					        <div class="col-sm-10"></div>
					              <div class="col-sm-2" align="right">
					         <i class="fa fa-asterisk" style="color: red;font-size: smaller;"></i>Fields Mandatory</div>				
				           </div>
 <%-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
	  <tr class="HEADER">
		<td colspan="4">Admission Reprint</td>
	  </tr>
	  <tr >
	    <td class="LABEL" colspan="2">
	    <div align="right">
		    <html:radio property="strCase" name="admissionReprintTransBean" value="1" onclick="changeRadio(this);hideDtl(this);" >Admission No.</html:radio>
		    <html:radio property="strCase" name="admissionReprintTransBean" value="2" onclick="changeRadio(this);hideDtl(this);" >CR No.</html:radio>
	    </div>
	    </td>
       </tr>
	</table> --%>  
	
	
	<%-- <table class="TABLEWIDTH" align="center" bgcolor="" cellspacing="1px">
		<tr>
		    <td class="LABEL" width="25%">Re-Print Type</td>
		    <td class="CONTROL" width="25%">
		       <select name="rePrintType" class="comboNormal" onChange="fun();">
		          <option value="1" selected="">Admission Slip</option>
		          <option value="2" >Discharge Card</option>
		          <option value="3" style="display: none;">Visitor Pass Slip</option>
		       </select>
		    </td>
		    <td class="LABEL" width="25%" id="reprintId">Re-Print Charge</td>
		    <td class="CONTROL" width="25%">
		    <div id="reprintChrg"><font size="2" color="blue">
		    <img src='../../hisglobal/images/INR.png'><bean:write name="admissionReprintTransBean" property="strRePrintCharge"  filter="false"/>
		    </font></div></td>
		</tr>   
	 </table> --%>   
	<%-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='0px'>
		<tr id='admNoId'>
			<td width="25%" class="LABEL" nowrap="nowrap"><font color="red">*</font>Admission No</td>
			<td width="75%" class="CONTROL">
			<input type="text" tabindex='1' name="strAdmnNo" value="${admissionReprintTransBean.strAdmnNo}" 
			maxlength="15" onkeypress="return goRetFunc(event);return validateData(event,5);" />
			<img style='cursor: pointer; cursor: hand' src="../../hisglobal/images/Go.png" 
			align="top" onclick="goFunc();">
			</td>
		</tr>
		<tr id='crNoId'>
			<td width="25%" class="LABEL"><font color="red" >*</font>CR No.</td>
			<td width="75%" class="CONTROL">
			<crNo:crNo id="strCrNoId" value="${admissionReprintTransBean.strCrNo}" js="onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo>
			<img style='cursor: pointer; cursor: hand' src="../../hisglobal/images/Go.png" 
			align="top" onclick="goFunc();">
			</td>
		</tr>
	</table> --%>
	</div>
	<div id="patDemDtlId" style="display: none;">
	 <!-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='0px'>
		<tr>
		 <td width='3%' class='TITLE' align='center' colspan="1">
		   <div id="plusonLineId" style="display: none"><img style='cursor: pointer;'
				src="../../hisglobal/images/plus.gif" name="plusonLine" title="Show Details" 
				onclick="showDetails();" /></div>
		   <div id="minusonLineId"><img style='cursor: pointer;'
				src="../../hisglobal/images/minus.gif" name="minusonLine" title="Hide Details" 
				onclick="hideDetails();"></div>
			</td>
			<td width="97%" class="TITLE" colspan="3">Patient Demographic Details</td>
	  </tr>
	</table>  -->
	
	</div>
	<%-- <div id="patDtlTld" style="display: none">		
		  <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='25%' class='LABEL' align='center'><div align="right">CR No.</div></td>
			<td width='75%' class='CONTROL' align='center'><div align="left">
			<bean:write name="admissionReprintTransBean" property="strCrNo"/></div>
			</td>			
		</tr>
		
	</table>
	 <pDtl:patDtl
		crNo="${admissionReprintTransBean.strCrNo}" address="false"></pDtl:patDtl>
	
	</div>	 --%>
	<%-- <div id="movementDtlId" style="display: none">
	<bean:write name="admissionReprintTransBean" property="strPatientAdmndtl" filter="false"/>
	</div> --%>
	
	
	
	<!--<div id="admDtlTldglbdiv" style="display:none">
	 <table class="TABLEWIDTH" align="center" border='0' cellspacing='0'>
		<tr>
			<td width='3%' class='TITLE' align='center'  colspan="1">
			<div id="plusonLineId1" style="display: none">
			<img style='cursor: pointer;' src="../../hisglobal/images/plus.gif" 
				name="plusonLine1" title="Show Details" onclick="showDetails1();" ></div>
			<div id="minusonLineId1">
			<img style='cursor: pointer;' src="../../hisglobal/images/minus.gif" 
				name="minusonLine1" title="Hide Details"
				 onclick="hideDetails1();"></div>
			</td>
			<td colspan="97%" class="TITLE" colspan="3">Admission Details</td>
		</tr>
	</table> 
	</div>-->
	<%-- <div id="admDtlTld" style="display:none">
	<aDtl:addDtl admNo="${admissionReprintTransBean.strAdmnNo}"></aDtl:addDtl>
	</div> --%>
	
   
    <%-- <div id="id1" style="display:none">
	<!-- <table class="TABLEWIDTH" align="center" bgcolor="black" cellspacing="1px">
		<tr>
		    <td class="LABEL" width="25%">Re-Print Type</td>
		    <td class="CONTROL" width="25%">
		       <select name="rePrintType" class="comboMax" onChange="reprintCmb(this);">
		          <option selected value="1">Admission Slip</option>
		          <option value="2">Discharge Summary</option>
		          <option value="3">Visitor Pass Slip</option>
		       </select>
		    </td>
		    <td class="LABEL" width="25%">Re-Print Charge/print</td>
		    <td class="CONTROL" width="25%"><div id="reprintChrg"><font size="2" color="blue">Rs.<bean:write name="admissionReprintTransBean" property="strRePrintCharge"  filter="false"/></font></div></td>
		</tr>   
	 </table>    --> 
	 <div id="VisitPassReprint"></div>    
	 <table class="TABLEWIDTH" align="center" bgcolor="" cellspacing="1px">
		<tr> 
		    <td class="LABEL" width="25%">Total Re-Print Charges</td>
		    <td class="CONTROL" width="25%"><div id="totReprintChrg"></div></td>
		    <td class="LABEL" width="25%">Re-Print Date</td>
		    <td class="CONTROL" width="25%">
		    <font size="2" color="blue">
		    <bean:write name="admissionReprintTransBean" property="strCtDt"  filter="false"/></font></td>
		</tr>
      </table>
	</div>   --%> 
	
	<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="FOOTER"> 
	    	<td colspan="8"><font size="2" color="red">*</font>Mandatory Fields</td>
	  	</tr>
  	</table>
   -->
  	<%-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td align="center">
				<logic:notEmpty name="admissionReprintTransBean" property="strCrNo">
					<!-- <img style='cursor:pointer;' src="../../hisglobal/images/btn-pnt.png" title="To print the Record"  onClick="return validate1();"> -->
					<a href="#" class="button" id="" onClick="return validate1();" ><span class="print">Print</span></a>
				</logic:notEmpty>
				<!-- <img style='cursor:pointer;' src="../../hisglobal/images/btn-clr.png" title="Clear the Record" onClick="return fun();">
				<img style='cursor:pointer;' src="../../hisglobal/images/btn-ccl.png" title="Go back to the Main Page" onClick="cancelFunc();">
				 -->
				 
				<a href="#" class="button"	onClick="return fun();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table> --%>
<input type="hidden" name="hmode" value=""/>	
<input type="hidden" name="chkIndx" value=""/>
<input type="hidden" name="cnt" value="">
<input type="hidden" name="strSelCmbVal" value="${admissionReprintTransBean.strSelCmbVal}">
<input type="hidden" name="strErrMsgString" value="${admissionReprintTransBean.strErrMsgString}">
<input type="hidden" name="strNormalMsgString" value="${admissionReprintTransBean.strNormalMsgString}">
<input type="hidden" name="strCtDt" value="${admissionReprintTransBean.strCtDt}"/>

<input type="hidden" name="strRePrintCharge" value="${admissionReprintTransBean.strRePrintCharge}"/>
<input type="hidden" name="rePrintCharges" value="${admissionReprintTransBean.rePrintCharges}"/>

	<input type="hidden" name=strIsIntegratedWithBilling value="${admissionReprintTransBean.strIsIntegratedWithBilling}"/>
	<input type="hidden" name=strIsAdvanceAmountAtAdmission value="${admissionReprintTransBean.strIsAdvanceAmountAtAdmission}"/>
	<input type="hidden" name=strIsAdvanceAmountAtAdmissionTaken value="${admissionReprintTransBean.strIsAdvanceAmountAtAdmissionTaken}"/>
	<input type="hidden" name="strAdvanceAmountDate" value="${admissionReprintTransBean.strAdvanceAmountDate}">
	<input type="hidden" name="strAdvanceAmountReceiptNo" value="${admissionReprintTransBean.strAdvanceAmountReceiptNo}">
	<input type="hidden" name="strAdvanceAmount" value="${admissionReprintTransBean.strAdvanceAmount}">
	<input type="hidden" name="strSaveFlag" value="${admissionReprintTransBean.strSaveFlag}">
	<input type="hidden" name="strPatientCrNo" value="${admissionReprintTransBean.strPatientCrNo}">
	<input type="hidden" name="strPatAdmNo" value="${admissionReprintTransBean.strPatAdmNo}">
    <input type="hidden" name="strSaveStatus" value="${admissionReprintTransBean.strSaveStatus}">
    <input type="hidden" name="strAdmnStatusCode" value="${admissionReprintTransBean.strAdmnStatusCode}">

</div>

</div>
</div>
</fieldset>
</html:form>
<tag:autoIndex></tag:autoIndex>
<%-- <div id='printableSlip'>
	<logic:equal name="admissionReprintTransBean"  property="strSaveFlag" value="1">
		<tiles:insert  page="/ipd/transactions/PatientAdmissionTransCNT.cnt?hmode=PRINTSLIP&strCrNo=${admissionReprintTransBean.strPatientCrNo}&strAdmNo=${admissionReprintTransBean.strPatAdmNo}&duplicateMode=1"/>
	</logic:equal>
		<logic:equal name="admissionReprintTransBean"  property="strSaveStatus" value="1">
		<tiles:insert  page="/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode=DISCHARGESLIP&strCrNo=${admissionReprintTransBean.strPatientCrNo}&strAdmNo=${admissionReprintTransBean.strPatAdmNo}&duplicateMode=1"/>
	</logic:equal>
</div> --%>


<div class="modal fade" id="printModal" role="dialog" >
  <div class="modal-dialog modal-lg" role="document" style="">
    <div class="modal-content">
    <div class="modal-header"><button type="button" data-dismiss="modal" onclick="modalSlipPrint()" class="btn btn-primary" ><i class="fa fa-print" title="Print Last Slip"></i>&nbsp;Print</button></div>
    <div class="modal-body" id='printableSlip'>
	<logic:equal name="admissionReprintTransBean"  property="strSaveFlag" value="1">
		<tiles:insert  page="/ipd/transactions/PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo=${admissionReprintTransBean.strPatientCrNo}&strAdmNo=${admissionReprintTransBean.strPatAdmNo}&duplicateMode=1"/>
	</logic:equal>
		<logic:equal name="admissionReprintTransBean"  property="strSaveStatus" value="1">
		<tiles:insert  page="/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode=DISCHARGESLIP&strCrNo=${admissionReprintTransBean.strPatientCrNo}&strAdmNo=${admissionReprintTransBean.strPatAdmNo}&duplicateMode=1"/>
	</logic:equal>
	</div>
       
    </div>
  </div>
</div>


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
</body>
</html>