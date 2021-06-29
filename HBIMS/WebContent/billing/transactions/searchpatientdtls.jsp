<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8><title>Search Patient Listing</title>


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
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>

<script type="text/javascript">
</script>
</head>
<body onload="configPatListType(),fetchPatientList('1','1');">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/BillingCNT.cnt" method="post">
	
<div id = "errMsg" class="errMsg"><bean:write name="billingBean" property="strErrMsg"/></div> 
<div id='normalMsg'></div>
	
<table width="100%" cellspacing="1px">
	<tr>
			<td>
				<!-- <table width='100%' align="center" cellspacing="1px">
					<tr class="HEADER">
						<td colspan="4">Patient Listing</td>
					</tr>
					<tr>
					</tr>
				</table> -->
				<div id="fetchRecordDivId"></div>
				
				
					<table width='100%' align="center" cellspacing="1px">
					<tr>
					<td class='LABEL'>
						<b>Find By</b> 
						<select name='strSearchType' class='comboNormal'>
							<option value='1'>CR No.</option>
							<option value='2'>Patient Name</option>
						</select> 
						<input type='text' name='strSearchString' class="txtFldMax" style="height: 19px" onkeypress="return getPatListOnEnter(event);"> 
						<label for="sn">
						<img style="cursor:pointer;" src="/HBIMS/hisglobal/transactionutil/images/search_icon1.gif" title="Search Record"  onClick="getSearchPatListBySearch();"
										onkeypress="return getPatListOnEnter(event);" >
						</label>  &nbsp;
					</td>
					</tr>
					</table>	
					<!-- <table width='100%'  cellspacing="0px">
						<tr class="FOOTER">
							<td width='3%'>
								<div id="plusHelpId" align="center">
									<img src="../../hisglobal/images/plus.gif" name="plusHelp" style="cursor:pointer;cursor:hand" align="middle" onclick="showHelpDetails('HelpId');" />
								</div>
								<div id="minusHelpId" style="display: none" align="center">
									<img src="../../hisglobal/images/minus.gif" name="minusHelp" style="cursor:pointer;cursor:hand" onclick="hideHelpDetails('HelpId');"> 
								</div>
							</td>
							<td><div align="left">Help</div></td>
							
						</tr>
					</table> -->
					<div id="HelpId" style="display:none">
						<!-- <table width='100%' align="center" cellspacing="1px">
						<tr>
							<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Use <b>%</b> to Find Any Data of Any Length (Including zero length)</td>
						</tr>
						<tr>
							<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>_</b> to Find Data on a Single Character Exclusion</td>
						</tr>
						<tr class=FOOTER>
							<td></td>
						</tr>
						</table> -->
					</div>			
				
				<table border="0" width='100%' align="center">
					<tr>
						<!-- <td align="right">
							<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/ok.gif" onClick="setCrNo();" title="Select the Patient">
						</td>
						<td align="left">
							<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" onClick="closePopup();" title="Cancel and Close the Window">
						</td>
						 -->
						<td align="right">
		<br><a href="#" class="button" id=""  onClick="setCrNo();" ><span class="ok">Ok</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onClick="closePopup();"><span class="cancel">Cancel</span></a>
		</td>
					</tr>
				</table>					
		</td>
	</tr>
</table>
		
		<input type="hidden" name="patListType" value="${billingBean.usrArg }" />
		<input type="hidden" name="userJsFunctionName" value="${billingBean.usrFuncName }" />	
		<input type="hidden" name="deptCode" value="${billingBean.departmentCode }" />	
		<input type="hidden" name="gblCRValue" value="${billingBean.gblCRValue }" />
		</html:form>
		<tag:autoIndex></tag:autoIndex> 
</body>
</html>