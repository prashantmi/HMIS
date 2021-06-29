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
 
 <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
 

<script src="../../hisglobal/js/datepicker1.js"></script>
<script src="../../hisglobal/js/validationBootstrap.js"></script>
<script src="../../hisglobal/masterutil/js/master.js"></script>
<script src="../../hisglobal/js/tab.js"></script>
<script src="../../hisglobal/js/calendar.js"></script>
<script src="../../hisglobal/js/multirow.js"></script>
<script src="../../hisglobal/js/datepicker1.js"></script>
<script src="../../hisglobal/js/validation.js"></script>
<script src="../../hisglobal/js/util.js"></script>
<script src="../../billing/js/DayEndTransBS.js"></script>


<style type="text/css">
.gj-icon {
	color: white;
}

.thright {
    padding: 0.15rem;
    text-align: right!important;;
}
.tdright {
    padding: 0.15rem;
    text-align: right!important;;
}
  
.rowone
{
padding-bottom: 0px;
}
.hrone
{
margin-bottom: 0.2rem;
margin-top: 0rem;
}
</style>
</head>

<body onload="onBodyLoad();">
<html:form action="transactions/DayEndTransBSCNT.cnt" method="post">
 <fieldset form="form1">
	
  <legend class='legendHeader' id='nonPrintableLegend'>Day End Process</legend>
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
		
 <div id="errMsg" class="errMsg"><bean:write name="dayEndTransBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="dayEndTransBean" property="strWarning"/></div>
    <div id="normalMsg" class="normalMsg"><bean:write  name="dayEndTransBean" property="strMsg"/></div>
 
   
<div class="prescriptionTile" >

	
<!--  
	<tag:tab tabLabel="Day End Process" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
-->
<% 
		UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		//System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
		
			
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1") || userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				out.println("<div align='center'><font color ='red' size='5'>Information: After performing Day End , Please Relogin</font></div>");
				
			}
		}
%>   
	<div id='mainDiv2'>
	<div class="modal fade" id="pendingDayEndModal"    tabindex="-1" role="dialog" aria-labelledby="pendingDayEndModalLabel" aria-hidden="true">
  	<div class="modal-dialog "  role="document">
    <div class="modal-content">
    <div class="modal-header">
	 <h7 class="modal-title" >Pending Day Ends</h7>
		<button type="button" class="close" data-dismiss="modal" onclick="tableHideNew();">×</button></div>
      <div class="modal-body" >
		<div style="overflow-x:auto;height: 200px;">
			<table class="table" id="pendingDayEndDetailsTable">
			<tr>
				<bean:write name="dayEndTransBean" property="strPendingDayEndDetailsTable" filter="false"/>					 
			</tr>	
			</table>
		</div>              
      </div>
      <div class="modal-footer" ></div>
    </div>
  </div>
</div>
</div>
	<div class="row rowFlex reFlex newrow">						
		<div class="col-sm-3" align="left" >
			<img alt="Show" id="showPendingDayEndTableId" data-toggle="modal" data-target="#pendingDayEndModal" src="../../hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('pendingDayEndDetailsTable',this);">&nbsp;&nbsp;&nbsp;
			<label style="color: white;padding-top: 0.7rem;">Pending Day End Details</label>
		</div>
		<div class="col-sm-2" align="right"><label style="color: white;padding-top: 0.7rem;">Day End Date</label></div>
						
		<logic:equal value="1" property="strUserMode" name="dayEndTransBean">		
		<div class="col-sm-2">
			<logic:equal property="strDateType" name="dayEndTransBean" value="1">
				<input type="hidden" name="strDayEndDate"  value="${dayEndTransBean.strCtDate }"> 
				<label style="color: white;padding-top: 0.7rem;"><bean:write name="dayEndTransBean" property="strCtDate"/> </label>
			</logic:equal>	
			<logic:equal property="strDateType" name="dayEndTransBean" value="2">
				<input  id='datepicker' name="strDayEndDate" class="form-control" value="${dayEndTransBean.strCtDate }" jsFunc="getDayEndAmount();">
			</logic:equal>
		</div>
		<div class="col-sm-2" align="right"><label style="color: #ffa654;padding-top: 0.7rem;">User: <bean:write name="dayEndTransBean" property="strUserName" filter="false" /></label></div>
		<div class="col-sm-1" align="right">
			<button type="button" class="btn btn-outline-success mt-1 btn-circle savebtn " onclick="showhideAmtDiv(this);">	
				<i class="fas fa-rupee-sign" style="font-size: 17px;"></i>
			</button>
			
		</div>
		<div class="col-sm-2" align="left">
			<label style="color: #ffa654;padding-top: 0.7rem;" id='dayEndAmtLabel'><bean:write	name="dayEndTransBean" property="strDayEndAmount" filter="false" /></label>
		</div>
		
		<logic:equal property="strProcessType" name="dayEndTransBean" value="2">
		<table class="TABLEWIDTH">
			<tr>		
				<td width="50%" class="LABEL">Counter Name</td>
				<td width="50%" class="CONTROL"><bean:write
					name="dayEndTransBean" property="strCounterName" filter="false" /></td>
			</tr>
		</table>	
		</logic:equal>		
	</logic:equal>	
	</div>


    
   
   
  <logic:notEqual value="1" property="strUserMode" name="dayEndTransBean">
		<table class="TABLEWIDTH">	
		 
			<tr>
				<td width="50%"   class="LABEL">Day End Date</td>
				<td width="50%"   class="CONTROL">
				
				<logic:equal property="strDateType" name="dayEndTransBean" value="1">
				<%-- <input type="hidden" name="strDayEndDate"  value="${dayEndTransBean.strCtDate }"> --%> 
				<input  id='datepicker' name="strDayEndDate" class="form-control" >
				<bean:write name="dayEndTransBean" property="strCtDate"/> 
				</logic:equal>
				
				<logic:equal property="strDateType" name="dayEndTransBean" value="2">
				<input  id='datepicker' name="strDayEndDate" class="form-control" value="${dayEndTransBean.strCtDate }" jsFunc="getDayEndAmount();">
<%-- 				<date:date name="strDayEndDate" value="${dayEndTransBean.strCtDate }"  jsFunc="getDayEndAmount();"></date:date>
 --%>				</logic:equal>
				
				
				</td>
			</tr>
		
			<tr>
				<td width="50%"   class="LABEL"><font color="red">*</font>User Name</td>
				<td width="50%"   class="CONTROL"><select name="strUsrId" onchange="hidePendingDayEndDetails();getDayEndAmountUserNCounterWise();" class="comboNormal">
					<bean:write name="dayEndTransBean" property="strUserVal"  filter="false" /></select></td>
			</tr>
			</table>	
		
		<logic:equal property="strProcessType" name="dayEndTransBean" value="2">
		<table class="TABLEWIDTH">
			<tr>		
				<td width="50%"   class="LABEL"><font color="red">*</font>Counter Name</td>
				<td width="50%"   class="CONTROL"><select name="strCountId" class="comboNormal" onchange="getDayEndAmountUserNCounterWise();">
					<bean:write name="dayEndTransBean" property="strCounterVal" filter="false" /></select></td>
			</tr>
		</table>	
		</logic:equal>
	</logic:notEqual>
		
	<div style="display: none;" id='allAmountDiv'>
		<div class="row rowFlex reFlex">
			<div class="col-sm-7" align="right"><label>Total Scroll/Day End Cash Amount(<i class="fas fa-rupee-sign"></i>)</label></div>
			<div class="col-sm-2" id="dayEndAmtDiv"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndAmount" filter="false" /></b></font></div>
			<div class="col-sm-3"></div>
		</div>
		<div class="row rowFlex reFlex">
			<div class="col-sm-7"  align="right"><label>Total Scroll(Cheque/DD/Credit & Debit Card/Wallet/Virtual Account/Jan Arogya/Prisoner/Others)(<i class="fas fa-rupee-sign"></i>)</label></div>
			<div class="col-sm-2" id="dayEndAmtDiv2"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndInstAmt" filter="false" /></b></font>
		</div>
		<div class="col-sm-3"></div>
		</div>
		<div class="row rowFlex reFlex">
			<div class="col-sm-7"  align="right"><label>Total Scroll/Day End Credit(CM Fund) Amount(<i class="fas fa-rupee-sign"></i>)</label></div>
			<div class="col-sm-2" id="dayEndAmtDiv1"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndCreditCol" filter="false" /></b></font></div>
			<div class="col-sm-3"></div>
		</div>
		<div class="row rowFlex reFlex">
				<div class="col-sm-7"  align="right"><label>Total Scroll/Day End Credit(CM Fund) Amount(<i class="fas fa-rupee-sign"></i>)</label></div>
				<div class="col-sm-2" id="dayEndAmtDiv1"><font color='red'><b><bean:write	name="dayEndTransBean" property="strDayEndCreditCol" filter="false" /></b></font></div>
				<div class="col-sm-3"></div>
		</div>
	</div>
	<table class="TABLEWIDTH">		
	
		<tr style="display: none;">
			<td width="50%" class="LABEL" colspan="2" align="center">Details Required</td>
			<td width="50%" class="CONTROL" colspan="2">
			<html:checkbox property="strIsDetailsRequired" value="1" name="dayEndTransBean" onclick="getVisibilityDetails(this);" ></html:checkbox>
			</td>
		</tr>
		
		</table>

	<div id="paymentDetailsRequiredDivId" style="display: none;">
		<div class="row rowFlex reFlex">
			<div class="col-sm-3"></div>
			<div class="col-sm-2" align="right"><label>Is Payment Mode Required</label></div>
			<div class="col-sm-1">
				<html:checkbox property="strIsPayModeRequired" value="1" name="dayEndTransBean"></html:checkbox>
			</div>
			<div class="col-sm-2" align="right"><label>Is Payment Detail Required</label></div>
			<div class="col-sm-1">
				<html:checkbox property="strIsPayDetailRequired" value="1" name="dayEndTransBean"></html:checkbox>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
		
	<hr class='hrone'>
	<div id="DenominationDivId">
		<div class="row rowFlex reFlex newrow" style='display: none;'>
			<div class="col sm-12">
				<label class="blink"><font color='ffcc00' style="cursor: pointer;">Amount Breakup Details -Denomination Details</font></label>
				<font color='#FF8C00' size="3" id="plussign">&nbsp;</font>
				<font color='#FF8C00' size="3" id="minusign" style="display: none;">&nbsp;</font>
			</div>
		</div>
		<div class="row rowFlex reFlex rowone" align="right">
							
							<div class="col-sm-8"></div>
							<div class="col-sm-1"><label>Total </label></div>
							<div class="col-sm-2">
								<input type="text" class="form-control" name="strTotalSum" maxlength="9" readonly="readonly" value="0">								
                        	</div>
							<div class="col-sm-1" align='left'>
								<a style="text-decoration: none;cursor: pointer;" onclick="clearvalues();"><img style="width: 22px;cursor: pointer;" src="/HIS/hisglobal/drDeskAssets/img/clear3.png"></a>
							</div>
							
		</div>
		<div class="row rowFlex reFlex">
			<div class="col-sm-4" style="padding: 0;">
				<fieldset form="form1" style="height: 100%;">
					<legend class='legendHeader' id='nonPrintableLegend'>Note(s)</legend>

					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>2000&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control"
								name="strTwoThousandNotes" maxlength="5"
								onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>1000&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control"
								name="strThousandNotes" maxlength="5"
								onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0" disabled="disabled">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>500&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control"
								name="strFiveHundNotes" maxlength="5"
								onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>200&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control"
								name="strTwoHundNotes" maxlength="5"
								onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>


				</fieldset>
			</div>
			<div class="col-sm-4" style="padding: 0;">
				<fieldset form="form1" style="height: 100%;">
					<legend class='legendHeader' id='nonPrintableLegend'>Note(s)</legend>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>100&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strHundNotes"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>50&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strFiftyNotes"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>20&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strTwentyNotes"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>10&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strTenNotes"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>

				</fieldset>
			</div>
			<div class="col-sm-4" style="padding: 0;">
				<fieldset form="form1" style="height: 100%;">
					<legend class='legendHeader' id='nonPrintableLegend'>Coin(s)</legend>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>10&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strtenCoins"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>5&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strFiveCoins"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>2&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strTwoCoins"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="row rowFlex reFlex">
						<div class="col-sm-4"></div>
						<div class="col-sm-2">
							<label>1&nbsp;&nbsp;×</label>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="strOneCoins"
								maxlength="5" onblur="checkFieldForCash(this)"
								onclick="makingEmptyFieldForCash(this)"
								onkeyup="sumAmount()"
								onkeypress="return validateData(event,5)" value="0">
										</div>
										<div class="col-sm-3"></div>
									</div>
								</fieldset>
							</div>

						</div>
						<br>
						


	</div>

					
	<div class="row rowFlex reFlex" style="display: none;">
		<div class="col-sm-2"></div>
		<div class="col-sm-2"><label>Report Format</label></div>
		<div class="col-sm-2">
			<select name="strReportFormat" id="reportTypeId" class="browser-default custom-select" onchange="">
				<option value="html">Html</option>
				<option value="pdf">Pdf</option>
			</select>
		</div>
		<div class="col-sm-2"><label>User Remarks</label></div>
		<div class="col-sm-2"><input class="form-control" type="text" name="strUserRemarks"></div>
		<div class="col-sm-2"></div>
	</div>
		
	
	<div id="saveid">
	
	</div>
	<div class="row rowFlex reFlex rowone">
		<div class="col-sm-12" align="right">
			<img src="/HBIMS/hisglobal/images/html-png.png"  title="Html" id="htmlId" class="changeImg changeImgGray" alt="Html Report" onclick="selectFunction(0)" tabindex="0" selected="selected" style="width: 40px;cursor: pointer;">
			<img src="/HBIMS/hisglobal/images/pdf-png.png" title="Pdf" id="pdfId" class="changeImg" alt="Acrobat Reader" onclick="selectFunction(1)" tabindex="0" style="width: 32px;cursor: pointer;">
		</div>
	</div>
	<hr>
	        <div class="row rowFlex reFlex" style="display: none;">
									<div class="col-sm-10"></div>
									<div class="col-sm-2" align="right">
										<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
									</div>
								</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="userId" value="${dayEndTransBean.strUserId}">
	<input type="hidden" name="strCounterId" value="${dayEndTransBean.strCounterId}">
	<input type="hidden" name="userMode" value="${dayEndTransBean.strUserMode}">
	<input type="hidden" name="strSessionFromTime" value="">
	<input type="hidden" name="strSessionToTime" value="">
	<input type="hidden" name="billModuleId" value="${dayEndTransBean.strBillModuleId}">
	<input type="hidden" name="strDayEndAmount" value="${dayEndTransBean.strDayEndAmount}">
	<input type="hidden" name="strDayEndUserName" value="${dayEndTransBean.strDayEndUserName}">
	<input type="hidden" name="strDayEndCounterName" value="${dayEndTransBean.strDayEndCounterName}">
	<input type="hidden" name="strDayEndCreditCol" value="${dayEndTransBean.strDayEndCreditCol}">
	<input type="hidden" name="strDayEndTimeBoundFlag" value="${dayEndTransBean.strDayEndTimeBoundFlag}">
	<input type="hidden" name="strDayEndAllowedFlag" value="${dayEndTransBean.strDayEndAllowedFlag}">
	<input type="hidden" name="strDayEndAllowedTime" value="${dayEndTransBean.strDayEndAllowedTime}">
	<input type="hidden" name="strCurrentTime" value="${dayEndTransBean.strCurrentTime}">
	<input type="hidden" name="strDayEndInstAmt" value="${dayEndTransBean.strDayEndInstAmt}">
	<input type="hidden" name="strNegativeDayEndAllowed" value="${dayEndTransBean.strNegativeDayEndAllowed}">
	<input type="hidden" name="strDenominationAllowed" value="${dayEndTransBean.strDenominationAllowed}">
	
</div>
</div>
</fieldset>
</html:form>


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

	window.onclick = function(event) 
	{
		var elementTable = document.getElementById("pendingDayEndDetailsTable");
		var imageElement = document.getElementById("showPendingDayEndTableId");
		
		elementTable.style.display = "table";
		var strOnclick = "tableShow('pendingDayEndDetailsTable',this);";
		imageElement.setAttribute("onclick", strOnclick);
		imageElement.setAttribute("src", "../../hisglobal/images/plus.gif");	
	}
	</script>



</body>
</html>