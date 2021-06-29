<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ page import="java.io.*" %>
<%@ page import ="java.util.*,java.text.*" %>
<%@ page import ="hisglobal.config.*,hisglobal.vo.*" %>
<%@ page import ="billing.BillConfigUtil" %>

<html>
<head>
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

<script src="../../hisglobal/js/validationBootstrap.js"></script>
<script src="../../hisglobal/js/util.js"></script>
<script src="../../billing/js/DayEndCashHandoverTransBS.js"></script>


<style type="text/css">
.gj-icon {
	color: white;
}
.table th, .table td {
	padding: 0.15rem;
	vertical-align: top;
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
</head>

<body onload="checkMsg();getPendingDayEndDetails();">
<html:form action="transactions/DayEndCashHandoverTransBSCNT.cnt" method="post">
 <fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'>Day End Cash Handover</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fas fa-ban iround"  title="Cancel"></i>
	</button>	
	<button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onclick="" style="display: none;">
		<i class="fas fa-print iround"  title="Print Last Slip"></i>
	</button>
	
    <button  type="button"  class="btn btn-outline-success mt-1 btn-circle savebtn " tabindex='2' onClick="return validateFunc();" >					
		<i class="fas fa-save iround"  title="Save" ></i>
	</button>
									                 
  </div>
<div  class="viewport" id="nonPrintable">
<div class="container-fluid">
		
<div class="alert alert-danger alert-dismissible fade show" id="errMsg"  style="display:none;"><bean:write name="dayEndHandoverTransBean" property="strErrMsg" /></div>
	<div class="alert alert-success alert-dismissible fade show" id="normalMsg" style="display:none;"><bean:write  name="dayEndHandoverTransBean" property="strMsg"/></div>
	<div class="alert alert-warning alert-dismissible fade show"  id="warningMsg" style="display:none;"><bean:write name="dayEndHandoverTransBean" property="strWarning"/></div>			  	
	<div class="row justify-content-center">
	<div class="col-sm-12">
<div class="prescriptionTile">	
<table class="table" id="pendingDayEndDetailsTable">					
		<bean:write name="dayEndHandoverTransBean" property="strPendingDayEndDetailsTable" filter="false" />	
</table>

<div class="row rowFlex reFlex">
	<div class="col sm-12" align="center">
		<button id="strbtn" type="button" class="btn btn-info"   onclick="totalSum();">Next&nbsp;&nbsp;<i class="fas fa-forward"></i></button>
	</div>
</div>
												
	
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="userId" value="${dayEndHandoverTransBean.strUserId}">
	<input type="hidden" name="strCounterId" value="${dayEndHandoverTransBean.strCounterId}">
	<input type="hidden" name="userMode" value="${dayEndHandoverTransBean.strUserMode}">
	<input type="hidden" name="strSessionFromTime" value="">
	<input type="hidden" name="strSessionToTime" value="">
	<input type="hidden" name="billModuleId" value="${dayEndHandoverTransBean.strBillModuleId}">
	<input type="hidden" name="strDayEndAmount" value="${dayEndHandoverTransBean.strDayEndAmount}">
	<input type="hidden" name="strDayEndUserName" value="${dayEndHandoverTransBean.strDayEndUserName}">
	<input type="hidden" name="strDayEndCounterName" value="${dayEndHandoverTransBean.strDayEndCounterName}">
	<input type="hidden" name="strDayEndCreditCol" value="${dayEndHandoverTransBean.strDayEndCreditCol}">
	<input type="hidden" name="strDayEndTimeBoundFlag" value="${dayEndHandoverTransBean.strDayEndTimeBoundFlag}">
	<input type="hidden" name="strDayEndAllowedFlag" value="${dayEndHandoverTransBean.strDayEndAllowedFlag}">
	<input type="hidden" name="strDayEndAllowedTime" value="${dayEndHandoverTransBean.strDayEndAllowedTime}">
	<input type="hidden" name="strCurrentTime" value="${dayEndHandoverTransBean.strCurrentTime}">
	<input type="hidden" name="strDayEndInstAmt" value="${dayEndHandoverTransBean.strDayEndInstAmt}">
	</div>
	</div>
	</div>
</div>
</div>
</fieldset>

<div class="modal fade" id="nextModal" role="dialog">
    <div class="modal-dialog modal-lg ">
    
      <!-- Modal content-->
      <div class="modal-content">
      <div class="modal-header">Handover Details<button type="button" class="close" data-dismiss="modal">&times;</button></div>      
        <div class="modal-body">
        <div class="">Total Amount To Be Handed over&nbsp;(<i class="fas fa-rupee-sign"></i>)&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id='total' style="color: red;"></label></div>
          <div id="POItablediv" >
						
							
								<table id="POITable" class="table">
									<thead>
										<tr>
											<!-- <th>SrNo.</th> -->
											<th>Amount</th>
											<th>Handover To..</th>
											<th>Reference No.</th>
											<th>Handover To Name</th>
											<th><button type="button" class="btn btn-info" id="addmorePOIbutton" onclick="insRow()" />
												<i class="fas fa-plus"></i>
											</button></th>
										</tr>
									</thead>
									<tbody>
										<tr id="tbRow" style="display:none" >
											<!-- <td >1</td> -->
											<td><input type="text" name="strCashHandOverAmount" class="form-control" value="0"  onkeyup="sum(this);" /></td>
											<td><select class="browser-default custom-select"  name="strCashHandoverTo">                                   
                                                     <bean:write name="dayEndHandoverTransBean" property="strCashHandoverToValues" filter="false" />  
												</select>
											</td>
												<td><input type="text" name="strRefNo" class="form-control"   /></td>
												<td><input type="text" name="strHandOverToName" class="form-control"   /></td>
											<td><button type="button" class="btn btn-info"
													 value="Delete" onclick="deleteRow(this)" />
												<i class="fas fa-minus"></i>
											</button></td>
										</tr>
									</tbody>
								</table>
							<hr>
						<div class="row rowFlex reFlex">
						
						<div class="col sm-10" align="right"><label>Entered Amount Total</label></div>
						<div class="col sm-2" ><input class="form-control" name="strTotalAmount" type="text" id="res" style="width: 30%;" readonly /></div>
						
						</div>
					</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-info" onclick="validateFunc();">Save</button>
        </div>
      </div>
      
    </div>
  </div>
  
</html:form>
<tag:autoIndex></tag:autoIndex> 

<script type="text/javascript">
	$('#datepicker').datepicker({uiLibrary: 'bootstrap4', modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	var today=new Date();
	var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var mmm=arr[today.getMonth()];
	var hrs=today.getHours();
	var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
	$('#datepicker').val(dd);
	
	
	$( ".gj-modal" ).find( $("button").last() ).click(function(){
	   getDayEndAmount();
	});
	
	
		  $('.modal-dialog').draggable({
		    handle: ".modal-header"
		  });
	

	</script>



</body>
</html>