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
<title>Discount Approval</title>

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
 

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">	
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<!-- <script language="Javascript" src="../../hisglobal/js/validation.js"></script>-->
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../../billing/js/DiscountApprovalTransBS.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<style>
.table th, .table td {
    padding: 0.40rem;
    
}
.form-form-control {
    display: block;
    width: 100%;
    height: calc(0.5em + 0.75rem + 2px);
    padding: 0;
    font-weight: 600;
    line-height: 0;
    color: 
#495057;
background-color: #fff;
background-clip: padding-box;
border: 1px solid
    #fff;
}

</style>

</head>
<body onLoad="checkMsg();butdis();hlpOnLoad();SetCursorToTextEnd('strCrNoId');" onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">
<html:form action="transactions/DiscountApprovalTransBSCNT.cnt" method="post" onsubmit="return goFunc();" enctype="multipart/form-data">

<fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'>Discount Approval</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button" id="savebutton" class="btn btn-outline-success mt-1 btn-circle savebtn " tabindex='2' onclick='validateFunc1();' name="patientAdmissionModiTransBean" style="display: none;">					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>
									                 
  </div>
  	
  		
<!-- <div id="menu1"></div>
 -->

<div id="patdtltld" style="display:none">
       <bean:write name="discountApprovalTransBean" property="strPatientDetailsView" filter="false"/>
  </div>
<div  class="viewport" id="nonPrintable">
		<div class="container-fluid">
  <div class="col-md-12">
  <div id ="errMsg" class="alert alert-danger alert-dismissible fade show" style="display: none;"><bean:write name="discountApprovalTransBean" property="strErrMsg"/></div> 
<div id ="wrnID" class="alert alert-warnig alert-dismissible fade show" style="display: none;"><bean:write name="discountApprovalTransBean" property="strWarning"/></div>
<div id ="normalMsg" class="alert alert-success alert-dismissible fade show" style="display: none;"><bean:write  name="discountApprovalTransBean" property="strMsg"/></div>
  
<div class="prescriptionTile" style="padding-bottom: 0;padding-top: 0;" id="mainDataTable">

<div class='popup' id='tariffDtl' style="display:none">
	<table width='400' border="0" cellspacing ="0px">
		<tr class="HEADER"> 
			<th colspan='3' align='left'>&nbsp;Discount Details</th>
			<th align='right'><img  style='cursor:pointer;cursor:hand' src='../../hisglobal/images/stop.png'
				onClick="hidePayDetails('tariffDtl');"></th>
	    </tr>
	 </table>   
	 <table width='400' border="0" cellspacing ="1px">
		<tr>
			<td width="25%" class='LABEL'>Discount By</td>
			<td width="25%" class='CONTROL'><div id ='1'></div></td>
			<td width="25%" class='LABEL'>Discount Date:</td>
			<td width="25%" class='CONTROL'><div id ='2'></div></td>
		</tr>
		<tr>
			<td width="25%" class='LABEL'>Discount Reason:</td>
		   	<td width="25%" class='CONTROL' ><div id ='3'></div></td>
		  	<td width="25%" class='LABEL'></td>
			<td width="25%" class='CONTROL'></td>
		</tr>
        </table>
	</div>

<div class='popup' id='discountDtl2' style="display:none">
	<table width='400' border="0" cellspacing ="0px">
		<tr class="HEADER"> 
			<th colspan='3' align='left'>&nbsp;Discount Details</th>
			<th align='right'><img  style='cursor:pointer;cursor:hand' src='../../hisglobal/images/stop.png'
				onClick="hidePayDetails('discountDtl2');"></th>
	    </tr>
	 </table>   
	 <table width='400' border="0" cellspacing ="1px">
		<tr>
			<td width="25%" class='LABEL'>Discount By:</td>
			<td width="25%" class='CONTROL'><div id ='11'></div></td>
			<td width="25%" class='LABEL'>Discount Date:</td>
			<td width="25%" class='CONTROL'><div id ='22'></div></td>
			
		</tr>
		<tr>
		    <td width="25%" class='LABEL'>Discount Reason:</td>
		   	<td width="25%" class='CONTROL'><div id ='33'></div></td>
		   	<td width="25%" class='LABEL'></td>
		   	<td width="25%" class='CONTROL'></td>
		</tr>
		
	  </table>
	</div>



 
 <%-- <tag:tab tabLabel="Discount Approval" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab> --%>
	<br>
							<div class="row rowFlex reFlex" id="goid">
								<div class="col-sm-2" align="right">
									<label><font color="red">*</font>CR No.</label>
								</div>
								<div class="col-sm-2">
									<crNo:crNo id="strCrNoId"
										value="${discountApprovalTransBean.strCrNo}"
										js="onkeypress='return initGoFunc(event);'"
										className="form-control"></crNo:crNo>
								</div>
								<div class="col-sm-2" style="padding-left: 0">
									<span class="fas fa-search"
										style="cursor: pointer; cursor: hand;"
										id="searhPatientImageId" title="Click here for Patient Search"
										name='searchPatient' data-toggle="modal"
										data-target="#searchModel" onclick="fetchPatientList_BS(1,1)"></span>
									<a href="#" class="btn btn-sm btn-success"
										onclick="return goFunc();" style="font-size: 1rem;">
										GO&nbsp;<i class="fas fa-angle-double-right"></i>
									</a>
								</div>
								<div class="col-sm-6"></div>

							</div>

							<%-- <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="HEADER"> 
    <td colspan="4">Discount&gt;&gt;Approval</td>
  </tr>
  
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
    <td colspan="3" class="CONTROL">
   <crNo:crNo id="strCrNoId" value="${discountApprovalTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'"></crNo:crNo> 
   <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" 
  		 title="click here for patient search"  name='searchPatient' 
  	 onclick="showPatientListingWindow('2',document.forms[0].strCrNo,'setSelectedCrNo');"/>
   <input type="image" style="cursor:pointer;cursor:hand" title="Discount Details" align="top" src ="../../hisglobal/images/Go.png" name="go" value="Go" onclick="return goFunc();" onkeyup="goFuncOnEnter(event);">
  </td>   
  </tr>
  </table> --%>
  <div id="tldglbdiv">
  
   <!-- <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
     <tr>
       <td width='5%' class='TITLE' align='center'>	<div id="plusonLineId" style="display: none"><img
	      style="cursor: pointer;cursor: hand" src="../../hisglobal/images/plus.gif" name="plusonLine"  onclick="showDetails();" /></div>
	      <div id="minusonLineId"><img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/minus.gif" name="minusonLine" 
               onclick="hideDetails();">
          </div>
       </td>  
       <td colspan="7" class="TITLE">Patient Details</td>
    </tr>
   </table> -->
  </div>
  
   
  <div id="onLinetld">
       <DisDtl:reqDisDtlNew  crNo="${discountApprovalTransBean.strCrNo1}" mode="2"></DisDtl:reqDisDtlNew>
  </div>
  
   <div id="id1"></div>
   
   
   <div id ="disBnR" style="display:none">
  
   <div class="row rowFlex reFlex">
   
   <div class="col-sm-2"><label><font color='red'>*</font>Discount By:</label></div>
   <div class="col-sm-2"><select name="strRmk" class="browser-default custom-select"> <bean:write name="discountApprovalTransBean" property="strRmk" filter="false"/></select></div>
   <div class="col-sm-2"><label><font color="red">*</font>Discount Reason/Remarks:</label></div>
 <div class="col-sm-2"><select name="strRsn" onChange="groupCombo();" class="browser-default custom-select"><bean:write name="discountApprovalTransBean" property="strRsn" filter="false"/>
				<option value='0'>others</option>
			</select>Others Specify:<input name="strDrt" type="text"  class="form-control" value="" readonly="readonly"></div> 
     <div class="col-sm-3" align="right"><label>Upload Document[<font color="red">JP(E)G/PDF</font>]</label></div>
     <div class="col-sm-1" align="right"><div class="fileUpload">
				<span>
					<img name="uploadImgId" style="cursor: pointer" src="/HBIMS/hisglobal/images/upload.png">Upload</span>
	  		  		<input type="hidden" name="MAX_FILE_SIZE" value="2000000"/>   
					<input type="file" class="upload" name="uploadedFile" accept="image/*,application/pdf" id="uploadedFileId"/>
			</div></div>
   </div>
   
 
  <%--  <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
	<tr> 
		<td width="24%" class='LABEL' colspan='1'>
			<div align="right"><font color='red'>*</font>Discount By:</div></td>
		<td width="76%" class="CONTROL" colspan='3'>
		        <select name="strRmk">
		                 <bean:write name="discountApprovalTransBean" property="strRmk" filter="false"/>
		        </select>
		        
		</td>
	</tr>
	<tr>
		<td width="25%" class="LABEL" colspan='1'><div align="right"><font color="red">*</font>Discount Reason/Remarks:</div></td> 
		<td width="25%" class="CONTROL" colspan='1'>
			<select name="strRsn" onChange="groupCombo();"><bean:write name="discountApprovalTransBean" property="strRsn" filter="false"/>
				<option value='0'>others</option>
			</select>Others Specify:<input name="strDrt" type="text"  class="txtFldTxtArea" value="" readonly="readonly">
		</td>
		<td width="25%" class="LABEL" colspan='1'><div align="right">Upload Document[<font color="red">JP(E)G/PDF</font>]</div></td> 
		<td width="25%" class="CONTROL" colspan='1'>
			<div class="fileUpload">
				<span>
					<img name="uploadImgId" style="cursor: pointer" src="/HBIMS/hisglobal/images/upload.png">Upload</span>
	  		  		<input type="hidden" name="MAX_FILE_SIZE" value="2000000"/>   
					<input type="file" class="upload" name="uploadedFile" accept="image/*,application/pdf" id="uploadedFileId"/>
			</div>
		</td>
	</tr>
  </table>  --%>    
  </div>
                            <hr>
							<div class="row rowFlex reFlex">
								<div class="col-sm-10"></div>
								<div class="col-sm-2" align="right">
									<i class="fas fa-asterisk"
										style="color: red; font-size: smaller;"></i>Fields Mandatory
								</div>
							</div>
							<div id="lastbuttons" style="display: none"> 	
  <!-- <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			
			<td align="center">
			<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer;cursor:hand" title="Save Record"   onclick="validateFunc1();">
			 <img style="cursor: pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" title="click here to clear the contents"   
				name="clearImg" onclick="initPage();"/> <img style="cursor: pointer;cursor:hand" title="click here to cancel the process" 
				src="../../hisglobal/images/btn-ccl.png" onclick="cancel();"/>
							<br><a href="#" class="button" id="" onclick='validateFunc1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="initPage()"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
				
				</td>
			
		</tr>
	</table> -->
	</div>
	<div id="onlyClearbutton" style="display: block">
	
	</div>

	<input type="hidden" name="hmode" value="">
	<input type="hidden" name="strTempVal" value="">
	<input type="hidden" name="strMsgString" value="${discountApprovalTransBean.strMsgString}">
	<input type="hidden" name="strPopUpId" value="${discountApprovalTransBean.strPopUpId}">
	<input type="hidden" name="strBId" value="${discountApprovalTransBean.strBId}">
	<input type="hidden" name="strChk" value="">
	<input type="hidden" name="CrNo" value="${discountApprovalTransBean.strCrNo}">
	<input type="hidden" name="strCRNoSatus" value="${discountApprovalTransBean.strCRNoSatus}">
	<input type="hidden" name="gblCRValue"/>
		
</div>
</div>
</div>
</div>
</fieldset>
</html:form>
<tag:autoIndex></tag:autoIndex>

	<!-- <div class="modal fade" id="validateModal" tabindex="-1" role="dialog" 
		aria-labelledby="validateModalLabel" aria-hidden="true">
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
	</div> -->



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