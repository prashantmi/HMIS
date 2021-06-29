<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<!-- 
/**
 * Developer : Tanvi Sappal
 * Version : 1.0
 * Date : 23/Apr/2009
*/
-->
<html>
<head>
<meta charset=UTF-8">
<title>Return Process</title>

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


<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../js/returnFrom_mmsTransNEW.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../js/mms.js"></script>
<script type="text/javascript">

</script>
</head>


<body onload="document.forms[0].strCrNo.focus();getReport();">
<html:form action="/transactions/ReturnFromTransCNTNEW.cnt" name="returnFromTransBean" type="mms.transactions.controller.fb.ReturnFromTransFB" method="post">
	
	
<div class="viewport" id="nonPrintable">
			<div class="container-fluid">

				 
			<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
						<div class="row rowFlex reFlex">
						<div class="col-md-12" align="center">
				       	<div class="errMsg" id="errMsg"><bean:write name="returnFromTransBean" property="strErr" /></div>
						<div class="warningMsg" id="warningMsg"><bean:write name="returnFromTransBean" property="strWarning" /></div>
						<div class="normalMsg" id="normalMsg"><bean:write name="returnFromTransBean" property="strMsg" /></div>
						</div></div>
							<div class="row rowFlex reFlex" id="saveId">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<!-- <button  name="offlineIssueIndentBean" property="strViewChk" onclick="transferToViewPage();" title="Click Here To View Off Line Issue Item Detail" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn">
											<i class="fas fa-eye iround"  title="View"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="initPage();"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									<button type="button" id="savebutton"
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2' id="submitId" onclick=' return validateNewDemand();'
										name="patientAdmissionModiTransBean"
										style="background-color: #5cb85c;">
										<i class="fa fa-download iround" title="Save"></i>
									</button>
 -->
								</div>
							</div>	
	
	<logic:equal value="0" name="returnFromTransBean" property="strMode" >
	
	
	<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Return From Patient&gt;&gt;
									</p>
							
								</div>
							</div>
    </logic:equal>
    
    <logic:equal value="1" name="returnFromTransBean" property="strMode" >
	<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Return From Staff&gt;&gt;
									</p>
							
								</div>
							</div>
	
	    </logic:equal>
    
    <logic:equal value="2" name="returnFromTransBean" property="strMode" >
	
	<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Return From Patient/Staff
									</p>
							
								</div>
	</div>

</logic:equal>


		<div class="row rowFlex reFlex">
			<div class="col-md-2 px-4"><font color="red">*</font>Store Name</div>
			<div class="col-md-2"><select name="strStoreId"  onchange="itemCategoryCombo();"
					id="strStoreId" class='browser-default custom-select'>
					<option value="0">Select Value</option>
				<bean:write name="returnFromTransBean" property="strStoreNameCombo" filter="false" />
			</select></div>
			<div class="col-md-2"><font color="red">*</font>Item Category</div>
			<div class="col-md-2"><div id='itemCategoryId'>
				<select name="strItemCategoryNo"
					id="strItemCategoryNo" class='browser-default custom-select'>
				
				<option value="10">Drug</option>
			</select></div></div>
			<div class="col-md-1"><font color="red">*</font>CR No.</div>
			<div class="col-md-2"><crNo:crNo value="${returnFromTransBean.strCrNo}" js=" onkeypress='return initGoFunc(event);'"></crNo:crNo></div>
			<div class="col-md-1 col-xs-12"> <a class="btn btn-sm btn-success" href="#" onclick="return goFunc();" onkeyup="goFuncOnEnter(event);" style="font-size:1rem;">GO <i class="fas fa-angle-double-right"></i></a></div>
			
		</div>
		
		<div class="row">
			<div class="col-md-12" align="right"><font size="2" color="red">*</font> Mandatory Fields</div>
		</div>	
	<div id="blanket" style="display:none;"></div>
<div class="popUpDiv" id="popUpDiv" style="display:none;">

<table bgcolor="white">

<tr>

<td>

 <div id="issueDtlsDivId" style="display:block;"></div>

</td>

</tr>

</table>

</div>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="storeName" value="${returnFromTransBean.storeName}" />
	<input type="hidden" name="itemCatName" value="${returnFromTransBean.itemCatName}" />
	<input type="hidden" name="crNo" value="${returnFromTransBean.crNo}" />
	<input type="hidden" name="strId" value="${returnFromTransBean.strId}" />
	<input type="hidden" name="strConfCatCode" value="${returnFromTransBean.strConfCatCode}" />
	<input type="hidden" name="itemCategory" value="${returnFromTransBean.itemCategory}" />
	<input type="hidden" name="strMode"   value="${returnFromTransBean.strMode}">
	<input type="hidden" name="strIssueMode"   value="${returnFromTransBean.strIssueMode}">
	<input type="hidden" name="strReturnDrugValidity"   value="${returnFromTransBean.strReturnDrugValidity}">
	<input type="hidden" name="strReturnNo" value="${returnFromTransBean.strReturnNo}"/>


	
	</div>
	</div>
	</div>
	</div>
	</div>

</html:form>
<tag:autoIndex></tag:autoIndex>
<script type="text/javascript">

$('.datepicker').each(function(){
    $(this).datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
});
var today=new Date();
var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var mmm=arr[today.getMonth()];
var hrs=today.getHours();
var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
$('.datepicker').val(dd);

</script>
</body>
</html>

