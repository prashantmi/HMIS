<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head>
<meta charset=UTF-8">
<title>Local Purchase</title>
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

 -->
  <script src="/HBIMS/mms/lpasset/jquery.min.js"></script>
    <script src="/HBIMS/mms/lpasset/bootstrap.min.js"></script>  
 <script src="/HBIMS/mms/lpasset/gijgo.min.js" type="text/javascript"></script>
  <script src="/HBIMS/mms/lpasset/popper.min.js"></script>
<link rel="stylesheet" href="/HBIMS/mms/lpasset/font-awesome.min.css">
    <link href="/HBIMS/mms/lpasset/gijgo.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="/HBIMS/mms/lpasset/bootstrap.min.css">

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/autocomplete.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">


<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>

<script language="JavaScript" src="../../hisglobal/js/jquery.autocomplete.min.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script  src="../../hisglobal/js/validationBootstrap.js"></script>
<script type="text/javascript" src="../../mms/bootstrap/js/bootstrap.min.js"></script>
<script  src="../../ipd/js/jquery.simplemodal.js"></script>
<link rel="stylesheet" href="/HBIMS/mms/flexdatalist/jquery.flexdatalist.min.css">
<script src="/HBIMS/mms/flexdatalist/jquery.flexdatalist.min.js"></script> 

<script language="Javascript" src="/HBIMS/mms/js/LocalPurchaseNew_item_mmstrans.js"></script>
<script type="text/javascript">
/* $(function() {    
	//alert("ggtreTRETerertretre");
	var itemList = [];
	$('#strMultiRowItemId option').each(function() {
	    itemList.push({ "value" :this.textContent , "data" : this.value.split("^")[0] });
	});

	$('#strSearchDrug').autocomplete({
	   lookup: itemList,
	   minChars:3,
	   onSelect: function (suggestion) {  
		  // alert('1');      
	     getDrugNameSelected(suggestion.data);	    
	   }	    
	 });
	 
	 $('#strSearchDrug').click(function(){	 
	 	$(this).val("");
	 	
	 });	
  });
*/

function drugNameFun(obj){

	let itemList = [];
	$('#strMultiRowItemId option').each(function() {
	    itemList.push({ "value" :this.textContent , "data" : this.value.split("^")[0] });
	});

	
	//console.log('running');
	  $(obj).autocomplete({
		   lookup: itemList,
		   minChars:3,
		   onSelect: function (suggestion) {  
		//	   alert('1');      
		     getDrugNameSelected(suggestion.data , obj);	    
		   }	    
		 }); 

	  $(obj).click(function(){	 
		 	$(this).val("");
		 	
		 }); 
}
    
</script>
<style type="text/css">
	
	body {
	font-family: 'Source Sans Pro', sans-serif;
	font-size: 12px;
	line-height: 0.8;
	color: #333;
	background-color: #fff;
	font-weight: 501;
	background: #eaeef3;
}
.form-control {
	font-size: 0.8rem;
	/*font-family:  "Helvetica Neue", "Helvetica";*/
}

.browser-default {
	font-size: 0.8rem;
	/*font-family:  "Helvetica Neue", "Helvetica";*/
}

.row {
	padding-bottom: 5px;
}
.prescriptionTile {
	margin: 0.5% 0;
	border-bottom: 1px solid #d7d7d7;
	padding-bottom: 10px;
	padding: 1% 2% 0.5% 2%;
	background-color: #fff;
	transition: 0.5s;
	box-shadow: 0 0.5px 10px 2px #b0acac;
	border-radius: .25rem;
	/*color: #666;*/
	
}

.prescriptionTile:hover {
	/*box-shadow: 0 1px 18px 0px #bababa;*/
	box-shadow: 0 2px 10px 2px #847d7d;
	/* -webkit-transform: translateY(-4px); */
	/* transform: translateY(-4px); */
}

.subHeaders {
	font-weight: 501 !important;
	/*color: rgba(33, 32, 32, 0.7);

    font-family: "Helvetica Neue", "Helvetica";*/
	font-size: 20px !important;
	padding-bottom: 0px !important;
}
.newrow {
	padding-top: 2px;
	padding-bottom: 2px;
	background-image: linear-gradient(to right, #49B2F3, #02629C);
	background-color: #fff;
	transition: 0.5s;
	box-shadow: 0 0.5px 10px 2px #b0acac;
	border-radius: 5px;
}
.table thead th {
   
    padding-top: 0;
   
}
.table td, .table th {
   
border-bottom: 2px solid
    #dee2e6;
}

.autocomplete-suggestion{
font-weight: 500 !important;
}

</style>
<script >

function alphanumeric(e)
{ 

var k;
document.all ? k = e.keyCode : k = e.which;
//console.log(e.keyCode +'   ===  '+e.which);
return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57) || k == 45 || k == 47);
	
}
</script>
<html>
<head>
<title>LOCAL PURCHASE(GRN)</title>




</head>
<body>
	<html:form action="/transactions/LocalPurchaseNewTransCNT.cnt" name="localPurchaseNewTransBean" type="mms.transactions.controller.fb.LocalPurchaseNewTransFB" method="post" >


		<div class="viewport" id="nonPrintable">
			<div class="container-fluid">
<br>		
						<div class="prescriptionTile">
							
							<div class="row">
								<div class="col-sm-10">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-info">
											<i class="fa fa-shopping-bag" title="Cancel"></i>
										</button>
										&nbsp;LOCAL PURCHASE(MRN)
									</p>
								</div>
								<div class="col-sm-2" align="right" style="color: #173479;font-weight: bold;">Type:
        		<label style="color: #736767;"><bean:write  name="localPurchaseNewTransBean" property="strItemCategoeryName" filter="false" /></label></div>
								
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Store Name</label>
								</div>
								<div class="col-sm-2">
									<bean:write  name="localPurchaseNewTransBean" property="strStoreName" filter="false" />
								</div>
								<div class="col-sm-2">
									<label>Supplier<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<select  class='browser-default custom-select' name='strSupplier'><bean:write name="localPurchaseNewTransBean" property="strSupplierCombo" filter="false" /></select>
								</div>
								
								<div class="col-sm-2">
									<label>Enter DC NO.<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control"  onkeypress="return alphanumeric(event);" onkeyup="uppercasefn(this)" id="strDCNoId"  name="strDCNo" autocomplete="off">
								</div>
								
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Challan Date<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<input id="datepicker" type="text" name="strChallanDate">
								</div>
								<div class="col-sm-2">
									<label>Enter Invoice No<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" onkeyup="uppercasefn(this)"  onkeypress="return alphanumeric(event);" id="strInvoiceNoId" name="strInvoiceNo" autocomplete="off">
								</div>
								<div class="col-sm-2">
									<label>Invoice Date<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<input id="datepicker1" type="text" name="strInvoiceDate">
								</div>
								
							</div>
							
							<div class="row">
								<div class="col-sm-2">
									<label>UC<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
								<input type="radio"  name="strUcChk" value="1">Yes
								<input type="radio" name="strUcChk" value="0" checked="checked">No	
								</div>
								
								<div class="col-sm-2">
									<label>LPO No.<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="strLpoNoId" name="strLpoNo"  onkeypress="return alphanumeric(event);" onkeyup="uppercasefn(this)" autocomplete="off">
								</div>
								
								</div>
								
							
<br>
							<table id="POITable" class="table" style="font-size: 13px;margin-bottom: 0;">
							    <thead>
							      <tr>
							        
							        <th>Batch No.<font color="red">*</font></th>
							        <th>Item Name<font color="red">*</font></th>
							        <th>Item Category</th>
							        <th>Mfg Date<font color="red">*</font></th>
							         <th>Exp Date<font color="red">*</font></th>
							           <th>Total Qty<font color="red">*</font></th>
							           <th>Pur.Rate<font color="red">*</font></th>
							          <th>GST(%)<font color="red">*</font></th>
							           <th>Adm(%)<font color="red">*</font></th>
							           <th><button type="button" class="btn btn-info" onclick="insRow();"style="padding: 3px .55rem;font-size: 13px;"><i class="fa fa-plus"></i></button></th>
							      </tr>
							    </thead>
							    <tbody id="RowId">
							    <tr id='0' style='display:none;'>
							        <td  style="width: 7%;padding: .25rem;"><input type="text" maxlength="27" name="strbatchno" onkeyup="uppercasefn(this)" value="0" class="form-control"></td>
							        <td  style="width: 25%;padding: .25rem;"><!-- <div id="itemBrandDivId" style="display: none;">
										<select name="strMultiRowItemId" id="strMultiRowItemId" class="comboTooMax" >
										<bean:write name="localPurchaseNewTransBean" property="strItemBrandCombo" filter="false" />
										</select> 
										</div>--><input type="text" class="form-control" value="0" placeholder='Enter atleast 3 characters' onkeypress="drugNameFun(this);" id="strSearchDrug" name="strSearchDrug" size="120%"/> </td>
							        <td style="width: 9%;"><label><bean:write  name="localPurchaseNewTransBean" property="strItemCategoeryName" filter="false" /></label></td>
							        <td  style="width: 13%;padding: .25rem;"><input id="strMfgDate0" value="${localPurchaseNewTransBean.strCtDate}" type="text" name="strMfgDate"></td>
							        <td style="width: 13%;padding: .25rem;"><input id="strExpDate0" value="${localPurchaseNewTransBean.strCtDate}" type="text" name="strExpDate"></td>
							        <td style="width: 7%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,5);" class="form-control" autocomplete="off" name="strTotalQty" value="0"  onchange="calQty(this);"></td>
							        <td style="width: 7%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,7);" class="form-control" autocomplete="off" name="strPurRate1" value="0"  onchange="calRateUnit(this);"></td>
							        <td style="width: 6%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,7);" class="form-control" autocomplete="off" name="strGST" maxlength="5" value="0" onchange="calRateWithTax(this);"></td>
							        <td style="width: 8%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,7);" class="form-control" autocomplete="off" name="strAdmchg" value="0" maxlength="5"  onchange="calMRP(this);"></td>
							        
							        <td style="width: 8%;"><button type="button" class="btn btn-info" onclick="deleteRow(this)" style="padding: 3px .55rem;font-size: 13px;"><i class="fa fa-minus" ></i></button>
							        <button id ="prevw" type="button" class="btn btn-warning" data-toggle="modal" onclick="preview(this);" data-target="#RowpreviewModal" style="padding: 3px .55rem;font-size: 13px;"><i class="fa fa-eye" ></i></button>
							       		<input type="hidden" name="strAdm" /><input type="hidden" name="strBrandId" value="" />
							       		<input type="hidden" name="strCosttoPat" />
										<input type="hidden" name="strPurWidTax" />
										  <input type="hidden" name="strPurRate" /> 
										<!-- <input type="hidden" name="strTotalQty" /> -->
							       	</td>
							      </tr>
							      <tr id='1'>
							        <td  style="width: 7%;padding: .25rem;"><input type="text" maxlength="27" autocomplete="off" name="strbatchno" onkeyup="uppercasefn(this)" class="form-control"></td>
							        <td  style="width: 25%;padding: .25rem;"><!-- <div id="itemBrandDivId" style="display: none;">
										<select name="strMultiRowItemId" id="strMultiRowItemId" class="comboTooMax" >
										<bean:write name="localPurchaseNewTransBean" property="strItemBrandCombo" filter="false" />
										</select> 
										</div>--><input type="text" class="form-control" placeholder='Enter atleast 3 characters' onkeypress="drugNameFun(this);" id="strSearchDrug" name="strSearchDrug" size="120%"/> </td>
							        <td style="width: 9%;"><label><bean:write  name="localPurchaseNewTransBean" property="strItemCategoeryName" filter="false" /></label></td>
							        <td  style="width: 15%;padding: .25rem;"><input id="strMfgDate1" type="text" name="strMfgDate"></td>
							        <td style="width: 15%;padding: .25rem;"><input id="strExpDate1" type="text" name="strExpDate"></td>
							        <td style="width: 7%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,5);" class="form-control" autocomplete="off" name="strTotalQty" onchange="calQty(this);"></td>
							        <td style="width: 7%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,7);" class="form-control" autocomplete="off" name="strPurRate1" onchange="calRateUnit(this);"></td>
							        <td style="width: 6%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,7);" class="form-control" autocomplete="off" name="strGST" maxlength="5" onchange="calRateWithTax(this);"></td>
							        <td style="width: 8%;padding: .25rem;"><input type="text" onkeypress="return validateData(event,7);" class="form-control" autocomplete="off" name="strAdmchg" maxlength="5" onchange="calMRP(this);"></td>
							        <td style="width: 8%;"><button type="button" class="btn btn-info" onclick="deleteRow(this)" style="padding: 3px .55rem;font-size: 13px;"><i class="fa fa-minus" ></i></button>
							        <button id ="prevw" type="button" class="btn btn-warning" data-toggle="modal" onclick="preview(this);" data-target="#RowpreviewModal" style="padding: 3px .55rem;font-size: 13px;"><i class="fa fa-eye" ></i></button><input type="hidden" name="strBrandId" value="" /></td>
							      </tr>
							      <tr id='2'>
							    <td colspan=2 style="padding: .25rem;"><input type="hidden" name="strPurRate" value=""/><div  style="color: #173479;font-weight: bold;">
									Pur Rate/Unit(excl Taxes):&nbsp;<label style="color: #736767;">0</label>
								</div></td>
								
								<td style="width: 25%;padding: .25rem;">.</td>
								
								<!-- <td style="width: 9%;padding: .25rem;">.</td> -->
								
								
								<td colspan=2 style="width: 13%;padding: .25rem;"><div  style="color: #173479;font-weight: bold;">
								<input type="hidden" name="strPurWidTax" value=""/>
									Pur Rate/Unit(incl Taxes):&nbsp;<label style="color: #736767;">0</label>
								</div></td>
								
								<td colspan=2 style="width: 20%;padding: .25rem;"><div  style="color: #173479;font-weight: bold;">
								<input type="hidden" name="strAdm" value=""/>
									Adm Charges/Unit:&nbsp;<label style="color: #736767;">0</label>
								</div></td>
								
								
								
								<td colspan=3 style="width: 20%;padding: .25rem;"><div  style="color: #173479;font-weight: bold;">
								<input type="hidden" name="strCosttoPat" value=""/>
									Cost to Patient/Unit:&nbsp;<label style="color: #736767;">0 </label>
								</div></td>
							      </tr>
							      <tr style='display:none;'>
							       <td  style="width: 7%;padding: .25rem;">.</td>
			<td style="width: 25%;padding: .25rem;">Selected Drug Name: </td>
			<td style="width: 25%;padding: .25rem;"><div id="DrugNameId" style="font-weight:bold"></div></td>
		</tr>
							    </tbody>
							  </table>
							 
						<!--   <div class="row" style="font-size: 13px">
							  	
								<div class="col-sm-2">
									
								</div>
								
								<div class="col-sm-3">
									
								</div>
								
								<div class="col-sm-3" style="color: #173479;font-weight: bold;">
									Pur Rate/Unit(incl Taxes)(<i class="fa fa-rupee"></i>):&nbsp;<label style="color: #736767;">1234</label>
								</div>
								
								<div class="col-sm-2" style="color: #173479;font-weight: bold;">
									Adm Charges/Unit(<i class="fa fa-rupee"></i>):&nbsp;<label style="color: #736767;">1234</label>
								</div>
								
								<div class="col-sm-2" style="color: #173479;font-weight: bold;">
									Cost to Patient/Unit(<i class="fa fa-rupee"></i>):&nbsp;<label style="color: #736767;">1234</label>
								</div>
								<div class="col-sm-"></div>

							</div>
						 -->								
						 <br>
							<div class="row">
								
								
								<div class="col-sm-3" align="right">
									<label>Remarks</label>
								</div>
								<div class="col-sm-7">
									<textarea class="form-control" rows="2" id="comment" name="strRemarks" ></textarea>
								</div>
								<div class="col-sm-2"></div>
							</div>
							
							<div class="row">
								<div class="col-sm-12" align="center">
									
									<button type="button" id="savebutton" class="btn btn-success" onclick="validate1();"  ><i class="fa fa-save"></i>
									&nbsp;Preview&Save</button>
									<button type="button" class="btn btn-danger" onclick="cancelToDesk();" data-dismiss="modal"><i class="fa fa-ban"></i>&nbsp;Cancel</button>
									
								</div>
							</div>

<div class="modal fade" id="preModal" role="dialog">
    <div class="modal-dialog modal-lg" style="max-width: 1000px;">
    
      <!-- Modal content-->
      <div class="modal-content">
      <div class="modal-header" style="font-size: 20px;font-weight: bold;color: black;">Item Preview<button type="button" class="close" data-dismiss="modal">&times;</button></div>      
        <div class="modal-body">
        <div class="row">
        	
        	<div class="col-sm-4">
        		
          

        	</div>
        	<div class="col-sm-5" style="line-height: 15px">
        		<div class="row" style="padding-bottom: 0;font-weight: bold;">
        			<label style="margin-bottom: 0;">All India Institute of medical Science Raipur</label>
        		</div>
        		<div class="row" style="padding-bottom: 0;font-weight: bold;">
        			<div class="col-sm-1"></div>
        			<div class="col-sm-10"><label style="margin-bottom: 0;">Raipur</label></div>
        			<div class="col-sm-1"></div>
        		</div>
        		<div class="row" style="padding-bottom: 0;font-weight: bold;">
        			<div class="col-sm-1"></div>
        			<div class="col-sm-10"><label style="margin-bottom: 0;">Store Name : </label><bean:write  name="localPurchaseNewTransBean" property="strStoreName" filter="false" /></div>
        			<div class="col-sm-3"></div>
        		</div>
        	</div>
        	<div class="col-sm-3"></div>
        </div>
<br>     
<div id="previewItem" class="row">
	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">LP MRN No:</label></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">LP MRN Date:</label></div>
	<div class="col-sm-2"></div>

	<div class="col-sm-3"align="right"><label style="margin-bottom: 0;">LP Delievery Challan No:</label></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">LP Invoice No:</label></div>
	<div class="col-sm-2"></div>

	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">LP Delievery Challan Date:</label></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">LP Invoice Date:</label></div>
	<div class="col-sm-2"></div>

	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">Supplier Name:</label></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">Received by(User ID):</label></div>
	<div class="col-sm-2"></div>

	<div class="col-sm-3" align="right"><label style="margin-bottom: 0;">LPO No:</label></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-2"></div>
	<div class="col-sm-3" align="right"></div>
	<div class="col-sm-2"></div>
</div>

<table class="table">
	<thead>
		<tr>
			                       
			                        <th>Serial No</th> 
							        <th>Item Name</th>
							       <th>Qty</th>
							        <th>Batch</th>
							         <th>Item Category</th>
							           <th>Expiry</th>
							            <th>Tax(%)</th>
							           <th>Pur.Rate</th>
							          <th>Pur.Rate(Incl.Tax)</th>
							           <th>Total Amt(<i class="fa fa-rupee"></i>)</th>
							           
		</tr>
		</thead>
		<tbody id="itemrow">
			
		</tbody>
	
</table>
<div class="row">
	<div class="col-sm-8"></div>
	<div class="col-sm-2" align="right"><label style="font-weight: bold;">Grand Total(<i class="fa fa-rupee"></i>):</label></div>
	<div id="gtotal" class="col-sm-2"><label>0</label></div>
	
</div>
<div class="row">
	
	<div class="col-sm-2" align="right"><label style="font-weight: bold;"></label></div>
	<div class="col-sm-2"><label></label></div>
	<div class="col-sm-8"></div>
</div>
   </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" onclick="save();">Ok&Save</button>
        </div>
      </div>
      
    </div>
  </div>

  <div class="modal fade" id="RowpreviewModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
      <div class="modal-header" style="font-size: 20px;font-weight: bold;color: black;">Item Preview<button type="button" class="close" data-dismiss="modal">&times;</button></div>      
        <div id="prvwpop" class="modal-body">
      
         <div class="row">
         	<div class="col-md-4" style="border-right:1px dotted black ; line-height: 15px;" align="Right">
         		<b>Item Name</b></div>
         <div class="col-md-8">All India Istitute of medical Science Raipur</div>
         <div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>Type</b></div>
         <div class="col-md-8">Drug</div>
         <div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>Serial No.</b></div>
         <div class="col-md-8">9610120</div>
         <div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>Exp Date</b></div>
         <div class="col-md-8">25-feb-2020</div>
         <div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>GST(%)</b></div>
         <div class="col-md-8">9610120</div>
         <div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>Adm(%)</b></div>
         <div class="col-md-8">9610120</div>
         	<div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>Qty(No.)</b></div>
         <div class="col-md-8">9610120</div>
        <div class="col-md-4" style="border-right:1px dotted black" align="Right">
        	<b>Pur Rate/Unit(encl Taxes):</b></div>
         	<div class="col-md-8">1234</div>
         	<div class="col-md-4" style="border-right:1px dotted black" align="Right">
         		<b>ADM Charges:</b></div>
         		<div class="col-md-8"> 4235654</div>
         		<div class="col-md-4" style="border-right:1px dotted black" align="Right">
         			<b>Cost to Patient: </b></div>
         			<div class="col-md-8">4235</div>

        </div>
        <div class="row">
        	<div class="col-sm-7"></div>
        	<div class="col-sm-5" style="color: #173479;font-weight: bold;">Total Quantity(No.):
        		<label style="color: #736767;">1000</label></div>
        </div>
        <!-- <div class="modal-footer">
          <button type="button" class="btn btn-success" onclick="validateFunc();">Ok&Save</button>
        </div>
 -->      </div>
      
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
<div id="itemBrandDivId" style="display: none;">
										<select name="strMultiRowItemId" id="strMultiRowItemId" class="comboTooMax" >
										<bean:write name="localPurchaseNewTransBean" property="strItemBrandCombo" filter="false" />
										</select> 
										</div>

	<form>
	<script type="text/javascript">
	$('#datepicker').datepicker({ uiLibrary: 'bootstrap4',modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	$('#datepicker1').datepicker({uiLibrary: 'bootstrap4', modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	$('#strMfgDate1').datepicker({ uiLibrary: 'bootstrap4',modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	$('#strExpDate1').datepicker({ uiLibrary: 'bootstrap4',modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	$('#datepicker3').datepicker({uiLibrary: 'bootstrap4', modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	var today=new Date();
	var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var mmm=arr[today.getMonth()];
	var hrs=today.getHours();
	var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
	$('#datepicker1').val(dd);
	$('#datepicker').val(dd);
	$('#strMfgDate1').val(dd);
	$('#strExpDate1').val(dd);
	$('#datepicker3').val(dd);

	function insRow() {
	 /* var x = document.getElementById('POITable');
	  var y=x.querySelectorAll("tbody")[0];
	  var new_row = x.rows[1].cloneNode(true);
	  var len = x.rows.length;
	  new_row.style.display="";
	  new_row.id=x.rows[1].id+(parseInt(len)-1);
	  y.appendChild(new_row);
	  new_row.style.display="";
	  new_row.querySelectorAll("input")[0].value=0;*/
	// new_row.querySelectorAll("input")[0].removeAttribute("readonly");
	 /* var x = document.getElementById('POITable');
	  var y=x.querySelectorAll("tbody")[0];
	  var new_row = x.rows[1].cloneNode(true);
	  var new_row1 = x.rows[2].cloneNode(true);
	  var len = x.rows.length;
	  new_row.style.display="";
	  new_row.id=x.rows[1].id+(parseInt(len)-1);
	  y.appendChild(new_row);
	  new_row.style.display="";
	  new_row1.style.display="";
	  new_row1.id=x.rows[1].id+(parseInt(len)-1);
	  y.appendChild(new_row1);
	  new_row1.style.display="";
	  for(var i=0;i<new_row.querySelectorAll("input").length;i++)
		  new_row.querySelectorAll("input")[i].value='';
	  for(var j=0;j<new_row1.querySelectorAll("label").length;j++)
		  new_row1.querySelectorAll("label")[j].innerHTML='0';*/

		  var x = document.getElementById('POITable');
		  var y=x.querySelectorAll("tbody")[0];
		  var new_row = x.rows[1].cloneNode(true);
		  var new_row1 = x.rows[3].cloneNode(true);
		  var len = x.rows.length;
		  new_row.style.display="";
		  new_row.id=parseInt(x.rows[2].id)+(parseInt(len-3));
		  new_row.querySelectorAll("input[name='strMfgDate']")[0].id="strMfgDate"+new_row.id;
		  new_row.querySelectorAll("input[name='strExpDate']")[0].id="strExpDate"+new_row.id;
		  

		 // $('#datepicker2').datepicker({ uiLibrary: 'bootstrap4',modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
		  y.appendChild(new_row);
		  
		  new_row.style.display="";
		  new_row1.style.display="";
		  new_row1.id=parseInt(x.rows[2].id)+(parseInt(len-2));
		  y.appendChild(new_row1);
		  new_row1.style.display="";
		  for(var i=0;i<new_row.querySelectorAll("input").length;i++)
			  new_row.querySelectorAll("input")[i].value='';
		  for(var j=0;j<new_row1.querySelectorAll("label").length;j++)
			  new_row1.querySelectorAll("label")[j].innerHTML='0';
		  addDp("strMfgDate"+new_row.id);
		  addDp("strExpDate"+new_row.id);	  
	}

	function addDp(id){
		
		  $("#"+id).datepicker({ uiLibrary: 'bootstrap4',modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});

		var today=new Date();
		var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
		var mmm=arr[today.getMonth()];
		var hrs=today.getHours();
		var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
		$("#"+id).val(dd);
		

	}

	function deleteRow(row) {
  var i = row.parentNode.parentNode.rowIndex;
  document.getElementById('RowId').deleteRow(i);
  document.getElementById('RowId').deleteRow(parseInt(i-1));
}

	function getDrugNameSelected(itemId ,obj )
	{	//alert("ggtreTRETerertretre");
	var flag = 0;
	var sel = document.forms[0].strMultiRowItemId;	

	var i = obj.parentNode.parentNode.id;
	var j = parseInt(i/2) + 1;
	document.getElementsByName("strBrandId")[0].value = "0";
	document.getElementsByName("strBrandId")[j].value = itemId;
	//var totRowLength = parseInt(document.getElementsByName("rowLength1")[0].value,10);
	//alert("sel"+sel);
	/* if(totRowLength > 0)
	{
		var retValue = confirm("All values will be reset\n\nAreYou Sure?");
		if(retValue) 
			resetMultiRow("1");
		else
		{
			document.forms[0].strSearchDrug.value = "";
			return;
		}	
	} */
	  		
	for (var i=0; i<sel.options.length; i++) {				
		if ( sel.options[i].value.split("^")[0] == itemId) 
		{
			sel.selectedIndex = i;			
			flag = 1;
			break;
		}				
	}	
	//alert("flag"+flag);
	if(flag == 0)
	{
		sel.selectedIndex=0;
		document.forms[0].strSearchDrug.value = "";
	}
	else
	{
		//alert("3");
		displaySelectedDrug();
		//ajaxExistingBatchName();
	}	    
	 
}

function displaySelectedDrug() 
{
//	alert("4");
	if(document.forms[0].strMultiRowItemId[document.forms[0].strMultiRowItemId.selectedIndex].value!='0')
  {	
	 	document.getElementById("DrugNameId").innerHTML=document.forms[0].strMultiRowItemId[document.forms[0].strMultiRowItemId.selectedIndex].text;
   	if(document.getElementById("itemDivId")!=null)
   		document.getElementById("itemDivId").innerHTML = document.forms[0].strMultiRowItemId[document.forms[0].strMultiRowItemId.selectedIndex].text;
 //  	alert("5");
  }
  else
  {
//	alert("6");
  	document.getElementById("DrugNameId").innerHTML="";
  	if(document.getElementById("itemDivId")!=null)
  		document.getElementById("itemDivId").innerHTML = "";
  }  
}


function uppercasefn(obj){

	//document.getElementById(obj.id).value=(obj.value).toUpperCase();
	$("input[type=text]").keyup(function(){
		  $(this).val( $(this).val().toUpperCase() );
		});
}
function preview(obj) 
{
	var i = obj.parentNode.parentNode.id;
	//alert(i);
	var batch = document.getElementById(parseInt(i)).querySelectorAll("input")[0].value;
	var itm = document.getElementById(parseInt(i)).querySelectorAll("input")[1].value;
	var exp = document.getElementById(parseInt(i)).querySelectorAll("input")[3].value;
	//var purrateperpack = document.getElementById(parseInt(i)).querySelectorAll("input")[6].value;
	var gstper = document.getElementById(parseInt(i)).querySelectorAll("input")[6].value;
	var admper = document.getElementById(parseInt(i)).querySelectorAll("input")[7].value;
	var qty = document.getElementById(parseInt(i)).querySelectorAll("input")[4].value;
	//var purwidouttax = document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML;
	var purwidtax = document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML;
	var admchg = document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML;
	var mrp = document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML;
		//var dept = document.getElementsByName("strDeptCode")[0].options[document.getElementsByName("strDeptCode")[0].selectedIndex].text;
	
	var lDiv = "<div class='col-md-12' style ='padding-bottom: 10px;' align='center'>";
	var lDiv1 = "<div class='col-md-12' style ='padding-bottom: 10px;line-height: 15px;' align='center'>";
	var rDiv = "<div class='col-md-8'>";
	var cDiv = "</div>";

	var tmpDiv = lDiv1 + "<b>Item Name : </b>" + itm + cDiv ; // + rDiv + itm + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>Type : </b>Item" + cDiv ;//+ rDiv + "Drug" + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>Batch No. : </b>"+ batch + cDiv; //+ rDiv + batch + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>Expiry Date : </b>"+ exp + cDiv; //+ rDiv + exp + cDiv;
	// tmpDiv = tmpDiv + lDiv + "<b>Purchase Rate/Pack : </b>" + purrateperpack + cDiv; // + rDiv + purrateperpack + cDiv;

	 tmpDiv = tmpDiv + lDiv + "<b>GST% : </b>" + gstper + cDiv; //+ rDiv + gstper + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>ADM% : </b>" + admper + cDiv; //+ rDiv + admper + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>Total Quantity(No.) : </b>" + qty + cDiv; // + rDiv + qty + cDiv;
	// tmpDiv = tmpDiv + lDiv + "<b>Purchase Rate(Excl. Tax) : </b>" + purwidouttax + cDiv; // + rDiv + purwidouttax + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>Purchase Rate(Incl. Tax) : </b>"+ purwidtax + cDiv; // + rDiv + purwidtax + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>ADM Charges : </b>" + admchg + cDiv; // + rDiv + admchg + cDiv;
	 tmpDiv = tmpDiv + lDiv + "<b>Cost to Patient : </b>" + mrp + cDiv; //+ rDiv + mrp + cDiv;
	//alert(document.getElementById("prvwpop").innerHTML);
	document.getElementById("prvwpop").innerHTML = tmpDiv;
	//document.getElementById("prevw").setAttribute("data-target","#RowpreviewModal");
}

//call on cancel button 
function cancelToDesk(){
	document.forms[0].hmode.value="CANCELTODESK";
	document.forms[0].submit();
}

function save(){
    var hisValidator = new HISValidator("localPurchaseNewTransBean");
    var retVal = hisValidator.validate();
    hisValidator.clearAllValidations();
    //var retVal=true;
    if (retVal) {
           // console.log("3553535"+retVal);
            document.forms[0].hmode.value = "SAVE";
            //document.getElementById("saveid").style.display = "none";
            document.forms[0].submit();
        }
     else {
        return false;
    }
}

function validate1()
{
	var flag = false;
	//var cboCountry = document.getElementsByName("strCountry")[0];
	document.getElementById("savebutton").setAttribute("data-target", "#validateModal"); 
	var hisValidator = new HISValidator("localPurchaseNewTransBean");
	hisValidator.addValidation("strSupplier", "dontselect=0","Supplier is a Mandatory Field");
	hisValidator.addValidation("strDCNo", "req", "DC No.  is a Mandatory Field");
	hisValidator.addValidation("strChallanDate", "req", "Challan Date  is a Mandatory Field");
	hisValidator.addValidation("strInvoiceNo", "req", "Invoice No.  is a Mandatory Field");
	hisValidator.addValidation("strInvoiceDate", "req", "Invoice Date  is a Mandatory Field");
	hisValidator.addValidation("strSearchDrug", "req", "Item is a Mandatory Field");
	hisValidator.addValidation("strbatchno", "req", "Batch No. is a Mandatory Field");
	hisValidator.addValidation("strExpDate", "req", "Expiry Date is a Mandatory Field");
	hisValidator.addValidation("strMfgDate", "req", "Mfg Date is a Mandatory Field");
	hisValidator.addValidation("strTotalQty", "req", "Total Qty is a Mandatory Field");
	//hisValidator.addValidation("strPurRate", "req", "Purchase Rate is a Mandatory Field");
	//hisValidator.addValidation("strRatePack", "req", "Rate/Pack is a Mandatory Field");
	hisValidator.addValidation("strGST", "req", "GST% is a Mandatory Field");
	hisValidator.addValidation("strAdmchg", "req", "ADM% is a Mandatory Field");

	hisValidator.addValidation("strChallanDate","dtltet="+document.forms[0].strCtDate.value,"Challan Date should not be greater than today's Date");
	hisValidator.addValidation("strInvoiceDate","dtltet="+document.forms[0].strCtDate.value,"Invoice Date should not be greater than today's Date");
	hisValidator.addValidation("strMfgDate","dtltet="+document.forms[0].strCtDate.value,"Mfg Date should not be greater than today's Date");
	hisValidator.addValidation("strExpDate","dtgtet="+document.forms[0].strCtDate.value,"Expiry Date should not be less than today's Date");
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if (retVal) {
		previewForm();	
	}
}

//call on cancel button 
function cancelToDesk(){
	document.forms[0].hmode.value="CANCELTODESK";
	document.forms[0].submit();
}
function save(){
    var hisValidator = new HISValidator("localPurchaseNewTransBean");
    var retVal = hisValidator.validate();
    hisValidator.clearAllValidations();
    //var retVal=true;
    if (retVal) {
           // console.log("3553535"+retVal);
            document.forms[0].hmode.value = "SAVE";
            //document.getElementById("saveid").style.display = "none";
            document.forms[0].submit();
        }
     else {
        return false;
    }
}
function previewForm()
{
	//alert("strSearchDrug  "+document.getElementsByName("strSearchDrug").length);
	 var x = document.getElementById('POITable');
	 var y=x.querySelectorAll("tbody")[0];
	 var len = y.rows.length;
	 var otd="<td>";
	 var otd1="<td style='line-height: 15px;'>";
	 var ctd="</td>";
	 var otr="<tr>";
	 var ctr="</tr>";
	 var tmpdiv1="";
		//alert("length ::"+len);
	var lDiv = "<div class='col-sm-3' align='right'><label style='margin-bottom: 1;'>";
	var rDiv = "<div class='col-sm-2'>";
	var cDiv = "</div>";
	
	var tmpDiv = lDiv + "<b>LP Delivery Challan No. : </b>"+ cDiv + rDiv + document.forms[0].strDCNo.value + cDiv + rDiv + cDiv + lDiv + "<b>LP Invoice No. : </b>"+ cDiv + rDiv + document.forms[0].strInvoiceNo.value + cDiv;
	tmpDiv = tmpDiv + lDiv + "<b>LP Delivery Challan Date : </b>"+ cDiv + rDiv + document.forms[0].strChallanDate.value + cDiv + rDiv + cDiv + lDiv + "<b>LP Invoice Date : </b>"+ cDiv + rDiv + document.forms[0].strInvoiceDate.value + cDiv;
	//tmpDiv = tmpDiv + lDiv + "<b>Supplier : </b>"+ cDiv + rDiv + document.forms[0].strSupplier[document.forms[0].strSupplier.selectedIndex].text  + cDiv + rDiv + cDiv + rDiv + cDiv + lDiv + cDiv + rDiv + cDiv ;
	tmpDiv = tmpDiv + lDiv + "<b>Supplier : </b>"+ cDiv + rDiv + document.forms[0].strSupplier[document.forms[0].strSupplier.selectedIndex].text + cDiv + rDiv + cDiv + lDiv + "<b>LP L.P.O No. : </b>"+ cDiv + rDiv + document.forms[0].strLpoNo.value + cDiv;
		
		var gtot=0;
	 var i=1;
	 do
	 {
		//alert("purwidtax"+ document.getElementsByName("strPurRate")[i].value +"-----"+document.getElementsByName("strPurWidTax")[i].value);
		var purtax = parseFloat(document.getElementsByName("strPurRate")[i].value)+(parseFloat(document.getElementsByName("strPurRate")[i].value) * parseFloat(document.getElementsByName("strGST")[i].value))/100 ;
		//alert(purtax);
		 tmpdiv1 = tmpdiv1 + "<tr>";
		tmpdiv1 = tmpdiv1 + otd + (i) + ctd;
		tmpdiv1 = tmpdiv1 + otd1 + document.getElementsByName("strSearchDrug")[i].value + ctd;
		tmpdiv1 = tmpdiv1 + otd + document.getElementsByName("strTotalQty")[i].value + ctd;
		tmpdiv1 = tmpdiv1 + otd + document.getElementsByName("strbatchno")[i].value + ctd;
		tmpdiv1 = tmpdiv1 + otd + document.forms[0].strItemCategoeryName.value + ctd;
		tmpdiv1 = tmpdiv1 + otd + document.getElementsByName("strExpDate")[i].value + ctd;
		tmpdiv1 = tmpdiv1 + otd + document.getElementsByName("strGST")[i].value + ctd;
		tmpdiv1 = tmpdiv1 + otd + document.getElementsByName("strPurRate")[i].value + ctd;
		tmpdiv1 = tmpdiv1 + otd + purtax  + ctd;
		var totamt = Math.round((parseFloat(purtax) * parseFloat(document.getElementsByName("strTotalQty")[i].value)) *100.0)/100.0;
		
		tmpdiv1 = tmpdiv1 + otd + totamt.toFixed(2) + ctd;
		tmpdiv1 = tmpdiv1 + "</tr>";
		gtot = gtot + totamt;
		i++;
		//tmpdiv1 = tmpdiv1 + otd + document.getElementsByName("strSearchDrug")[i].value + ctd;
	 }while(i < parseInt(len/2))

	 document.getElementById("previewItem").innerHTML=tmpDiv;
	 document.getElementById("itemrow").innerHTML=tmpdiv1;
	 document.getElementById("gtotal").innerHTML=gtot.toFixed(2);
	// document.getElementById("savebutton").setAttribute("data-target","#preModal");
		$('#preModal').modal('show');
}

	</script>
</html:form>

<input type="hidden" name="hmode"/>
<input type="hidden" name="strCtDate" value="${localPurchaseNewTransBean.strCtDate}" />
<input type="hidden" name="strStoreId" value="${localPurchaseNewTransBean.strStoreId}" />
<input type="hidden" name="strItemCategoeryName" value="${localPurchaseNewTransBean.strItemCategoeryName}" />
<input type="hidden" name="strItemCategoryNo" value="${localPurchaseNewTransBean.strReqTypeId}" />
</body>
</html>