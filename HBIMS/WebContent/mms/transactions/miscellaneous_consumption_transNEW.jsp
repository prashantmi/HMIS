<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>



<head>
<meta charset=UTF-8">
<title>Miscellaneous Consumption</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../mms/js/mms.js"></script>
<script language="Javascript" src="../../mms/js/miscellaneous_consumption_transNEW.js"></script>
<script language="Javascript" src="../js/searchItems_utilNEW.js"></script>


<!-- added on 20 april 2020 -->

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


<style type="text/css">
.col-sm-1half, .col-sm-8half, .col-sm-half {
    position: relative;
    min-height: 1px;
    padding-right: 15px;
    padding-left: 15px;
}
.custom-radio .custom-control-label::before {
    background-color: white;  /* orange */
}

.col-md-1half, .col-md-8half, .col-md-half {
    position: relative;
    min-height: 1px;
    padding-right: 15px;
    padding-left: 15px;
}
@media (min-width: 768px) {
    .col-sm-1half,.col-md-1half, .col-sm-8half {
        float: left;
    }
    .col-sm-1half ,.col-md-1half{
        width: 12.495%;
    }
    .col-sm-half,.col-md-half {
        width: 4.165%;
    }
    .col-sm-2half,.col-sm-2half {
        width: 20.495%;
    }
}
.table
{
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-weight: 400;
	color: #212529;
}
textarea
{
width:100%;

}
</style>




</head>
<body >
<html:form name="miscellaneousConsumptionBean" action="transactions/MiscellaneousConsumptionTransCNTNEW" type="mms.transactions.controller.fb.MiscellaneousConsumptionTransFB">

				
<div class="viewport" id="nonPrintable">
			<div class="container-fluid">
				

			<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
						<div class="row rowFlex reFlex">
							<div class="col-md-12" align="center">
							<div id="errMsg" class="errMsg"><bean:write name="miscellaneousConsumptionBean" property="strErrMsg" /></div>
							<div id="warningMsg" class="warningMsg"><bean:write name="miscellaneousConsumptionBean" property="strWarningMsg" /></div>
							<div id="normalMsg" class="normalMsg"><bean:write name="miscellaneousConsumptionBean" property="strNormalMsg" /></div>
							</div>
						</div>
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="document.forms[0].reset();"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									<button type="button" id="submitId"
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2' onClick=" return validate1();"
										name="patientAdmissionModiTransBean"
										style="background-color: #5cb85c;">
										<i class="fa fa-download iround" title="Save"></i>
									</button>
								</div>
							</div>

					<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp; Miscellaneous Consumption
									</p>
							
								</div>
							</div>



	
		<br>
	<div class="row rowFlex reFlex">
		<div class="col-md-2 px-4"><font color="red">*</font><label>Store Name</label></div>
		<div class="col-md-3"><html:select name="miscellaneousConsumptionBean" styleClass="browser-default custom-select" property="strStoreId" onchange="getItemCategoryCmb();">
		       <bean:write name="miscellaneousConsumptionBean" property="strStoreNameValues" filter="false"/>
       		  </html:select></div>
       	<div class="col-md-1"></div>
       	<div class="col-md-2"><font color="red">*</font>Item Category</div>
       	<div class="col-md-2"><div id="itemCatDivId">      
       		<select name="strItemCategoryId1" class="browser-default custom-select"><bean:write name="miscellaneousConsumptionBean" property="strItemCategoryValues" filter="false"/></select>
       		</div>
       	</div>
       	<div class="col-md-2"><button type="button" id='strSearchItemButtonDivId'
											class="btn btn-success"  onclick='getItemSelectPopup();'>
											<i class="fas fa-search" title="Click Here To Search Items">&nbsp;<span>Item Finder</span></i>
										</button></div>
	</div>
	<br>
	<!-- <div class="row rowFlex reFlex">
		<div class="col-md-11 px-4"><div>
		  <img style="cursor: pointer;height: 20px" id='strSearchItemButtonDivId' src="../../hisglobal/images/ItemFinder.png"  title='Click to Search Items' onclick='getItemSelectPopup();'>
		</div></div>
	</div> -->
	
	<div class="row rowFlex reFlex">
		<div class="col-md-11 px-4"><table class="table table-striped text-uppercase" style='font-weight:bold;' align="center"cellpadding="1px" cellspacing="1px" bgcolor="black">
   <tr>
   
	   <td class='' width='35%'>Item/Drug Name</td>
	   <td class='' width='15%'>Batch/Serial No.</td>
	   <td class='' width='10%'>Avl Qty</td>
	   <td class='' width='15%'><font color="red">*</font>Consumption Qty</td>
	   <td class='' width='20%'><font color="red">*</font>Consumption Unit</td>
    </tr>
  </table>
    <div id="id1"></div>
		</div>
	</div>
	<br>
	<div class="row rowFlex reFlex">
		<div class="col-md-2 px-4"><label>Remarks</label></div>
		<div class="col-md-9"><textarea class="form-control" name="strRemarks"cols="16" rows="2"></textarea></div>
	</div>
	
	<div class="row rowFlex reFlex">
		<div class="col-md-12 FOOTER" align="right"><font size="2" color="red">*</font> Mandatory Fields </div>
	</div>
	
	
</div>
</div>
</div>     
     
 	<input type="hidden" name="hmode"/>
	<input type="hidden" name="strStoreName" value=""/>
	<input type="hidden" name="strReOrderFlgColor"	value="${miscellaneousConsumptionBean.strReOrderFlgColor}">
	<input type="hidden" name="strItemDtlWithIssueQty" id="strItemDtlWithIssueQty" value="0"/>
	
		

<cmbPers:cmbPers />

<div id="blanket" style="display:none;"></div>
<div class="popUpDiv" id="popUpDiv" style="display:none;">
<table bgcolor="white">
<tr>
<td>
<div id="searchItemsDtlsDivId" style="display:block;"></div>
</td>
</tr>
</table>
</div>
</div>
</div>

</html:form>
<jsp:include page="miscellaneous_consumption_item_search_rowNEW.jsp"></jsp:include>
	<tag:autoIndex></tag:autoIndex>   
</body>
