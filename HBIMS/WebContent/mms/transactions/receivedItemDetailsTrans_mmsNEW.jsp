<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>



<html>
<head>
<meta charset=UTF-8">
<title>Gifted Item Details</title>

<!-- added 20 April 2020 -->
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
<!-- end -->

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../js/receivedItemDetailsNEW.js"></script>
<script language="JavaScript">



</script>

</head>
<body >
<html:form name="receiveFromThirdPartyTransBean" action="/transactions/ReceiveFromThirdPartyTransCNTNEW" type="mms.transactions.controller.fb.ReceiveFromThirdPartyTransFB">
	
	
	<div class="viewport" id="nonPrintable">
			<div class="container-fluid">

				 
	<div id="errMsg" class="errMsg"><bean:write name="receiveFromThirdPartyTransBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="receiveFromThirdPartyTransBean" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="receiveFromThirdPartyTransBean" property="strNormalMsg" /></div>
	
			<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<!-- <button type="button" class=" btn btn-secondary btn-circle"
										onclick="document.forms[0].reset();"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									 --><button id='pbtn' type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" 
									 		property="strReceivedItemViewMode" name="receiveFromThirdPartyTransBean"
									 		value="3" onclick="changeViewMode(this);">
											<i class="fas fa-eye iround"  title="View"></i>
									</button>
									<!-- <button type="button" id="savebutton"
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2' onClick="return validate();"
										name="patientAdmissionModiTransBean"
										style="background-color: #5cb85c;">
										<i class="fa fa-download iround" title="Save"></i>
									</button> -->
								</div>
							</div>
	<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Received From Third Party Details
									</p>
									<!-- <button  type="button" class="btn btn-outline-success mt-1  savebtn" onclick="cancelFunc();">
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button> -->
								</div>
								<!-- <div class="col-sm-1">
									<div class="custom-control custom-radio">
										<input type="radio" id='customRadio'
											class="custom-control-input" name="strCase" value="1"
											onclick="changeViewMode(this);"> <label
											class="custom-control-label" for="customRadio">Breakage</label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="custom-control custom-radio">
										<input type="radio" id='customRadio1'
											class="custom-control-input" checked="checked" name="strCase"
											value="2" onclick="changeViewMode(this);"> <label
											class="custom-control-label" for="customRadio1">Lost</label>
									</div>
								 -->
				</div>
								
							
	
	<div class="row rowFlex reFlex" style="display:none;"> 
			<div class="col-md-4"></div>
			<div align="right" class="col-md-1 custom-control custom-radio"> 
	        <input type="radio" id='customRadio' class="custom-control-input" name="strReceivedItemApprovedMode" value="4" checked="checked" onClick="changeViewMode(this);"/>
	        <label class="custom-control-label" for="customRadio">NEW</label>
			</div>
			<div align="right" class="col-md-2 custom-control custom-radio">
			<input type="radio"id='customRadio1' class="custom-control-input" name="strReceivedItemStockUpdateMode" value="2" onClick="changeViewMode(this);"/>
			<label class="custom-control-label" for="customRadio">UPDATE STOCK</label>
			</div>
			<div align="right" class="col-md-1 custom-control custom-radio">
			<input type="radio" id='customRadio2' class="custom-control-input" name="strReceivedItemViewMode" value="3"  onClick="changeViewMode(this);"/>
			<label class="custom-control-label" for="customRadio">VIEW</label>
			</div>
	</div>
	
	<%-- <tag:tab tabLabel="" selectedTab="FIRST" align="center" width="TABLEWIDTH">
              </tag:tab>      
     --%>          
              
   <%--            
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	     <tr class="HEADER">
			<td colspan="3">Received From Third Party Details</td>
			
			
			 <td colspan="1" align="right" >
		     	<span>
		     		<html:checkbox property="strReceivedItemViewMode" name="receiveFromThirdPartyTransBean" value="3" onclick="">View</html:checkbox>
		     		
		     	</span>
		     </td>	
			
		</tr>
		
		 <tr style="display: none;">
	   <td colspan="4" class="TITLE">
	   
	    <div align="right" > 
	        <input type="radio"  name="strReceivedItemApprovedMode" value="4" checked="checked" onClick="changeViewMode(this);"/>
			NEW <input type="radio" name="strReceivedItemStockUpdateMode" value="2" onClick="changeViewMode(this);"/>UPDATE STOCK
			<input type="radio" name="strReceivedItemViewMode" value="3"  onClick="changeViewMode(this);"/>VIEW
	    </div>
	    
	   </td>
	   </tr>
	 --%>   
	 <div class="row rowFlex reFlex">
	 <div class="col-md-2 px-4"><font color="red">*</font>Store Name</div>
						<div class="col-md-3"><select name="strStoreId" class='browser-default custom-select' onchange="reSetViewDetails(),getItemCategorys(this);">
					<bean:write name="receiveFromThirdPartyTransBean" property="strStoreNameCombo" filter="false" />
					</select> 
    					</div>
    					<div class="col-md-1"></div>
						<div class="col-md-2"><font color="red">*</font>Item Category</div>
						<div class="col-md-3"><div id="itemCategoryDivId">
						<select name="strItemCategoryId" class='browser-default custom-select'>
							<bean:write name="receiveFromThirdPartyTransBean" property="strItemCategoryCombo" filter="false" />
						</select></div>
    
						</div>
			
	 </div>
	 
	 <div id="itemDetailsMode" style="display: block;">
		<div class="row rowFlex reFlex" >
	 		<div class="col-md-2 px-4">
	 		<font color="red">*</font>Third Party Name
	 		</div>
	 		<div class="col-md-3">
			<select class="browser-default custom-select" name="strInstituteId">
			<bean:write name="receiveFromThirdPartyTransBean" property="strInstituteValues" filter="false"/>
			</select>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-2">
	   			<label>Remarks</label>
			</div>
			<div class="col-md-3">
        		<textarea class="form-control" name="strRemarks" rows="2" cols="18"></textarea>
             </div>	
    		
    		<div class="col-md-1 col-xs-12"> <a class="btn btn-sm btn-success" href="#" onclick="return getInventoryDtls();" style="font-size:1rem;">GO <i class="fas fa-angle-double-right"></i></a></div>
    	
    </div></div>
	 <!--   </tr>
	   <tr>
	   <td colspan="4" class="" align="center">
	   <img style="cursor: pointer; " title="Get Gifted Item Details" src="../../hisglobal/images/Go.png" onclick="return getInventoryDtls();"/>
	   <a href="#" class="button" id="go"	onclick=""></a> 
	   </td>
	   </tr>
	  </table>
	  </div> 
	  -->
	 <div id="giftedItemViewMode" style="display: none">
	
	<div class="row rowFlex reFlex">
		<div class="col-md-2 px-4"><font color="red">*</font>From Date</div>
		<div class="col-md-3"><input  name="strFromDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strCurrentDate}"
											style="color: rgba(113, 111, 111, 0.87);">
											</div>
		<div class="col-md-1"></div>
		<div class="col-md-2"><font color="red">*</font>To Date</div>
		<div class="col-md-3"><input  name="strToDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strCurrentDate}"
											style="color: rgba(113, 111, 111, 0.87);">
											</div>
		<div class="col-md-1 col-xs-12"> <a class="btn btn-sm btn-success" href="#" onclick="return getReceivedViewDetails();" style="font-size:1rem;">GO <i class="fas fa-angle-double-right"></i></a></div>	
	</div>
	
	<div class="row rowFlex reFlex" style="display: none;">
		<div class="col-md-6">Gifted Details For Financial Year</div>
		<div class="col-md-6"><div id="finYearDivId"></div></div>
	</div>
		
	  
	  	<div id="giftedViewDetailsDivId" style="display: none">
	   
	  	</div>
	  
	 </div>
		 
		<div class="row">       
			 <div class="col-md-12" align="right"><font size="2" color="red">*</font> Mandatory Fields </div>
		</div>
	<br>
	
<input type="hidden" name="hmode"/>

<input type="hidden" name="strStoreName" value=""/>
<input type="hidden" name="strItemCategoryName" value=""/>
<input type="hidden" name="strItemCategoryCode" value=""/>
<input type="hidden" name="strInstituteName" value=""/>
<input type="hidden" name="strStatus" value=""/>
<input type="hidden"  name="strConfigIssueRate"  value="">
<input type="hidden" name="strCurrentDate" value="${receiveFromThirdPartyTransBean.strCurrentDate}" />

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