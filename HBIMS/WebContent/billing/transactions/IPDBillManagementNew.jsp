<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<!--
  IPD Bill Management New
  
  author: Debashis Sardar
  
  dated: 12th Mar 2013
-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>

<head>
<title>IPD Bill Management New</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
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



<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<style type='text/css'>

.accTileHead
{
 font-family: Arial, Helvetica, sans-serif;
 font-size: 0.94rem;
 color:#797070;
}

.accTileTd{
 font-family: Arial, Helvetica, sans-serif;
 font-size: 0.8rem;
}




</style>

<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../js/IpdBillMangTransBS.js"></script>
<script language="Javascript" >

window.history.forward();

function goFunc() 
{  
 		var hisValidator = new HISValidator("ipdBillManagementTransBean");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	    hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	    document.forms[0].CrNo.value=document.forms[0].strCrNo.value;
	    if(retVal)
		{
	    	    
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
				
		}
		else
		{		
			return false;
		}
 }
 function onload() {
	 document.getElementById('goid').style.display="none";
}

function initGoFunc(eve){
	   	
	    var flag=validateData(eve,5);
  		 if(flag){	
	   	
	   	if(eve.keyCode == 13){
												
				goFunc();
				
				return false;
			}	   	

  }else{
	   		return false;
	   }		

	   }
function getgoDetails(chk)
{
	//alert(document.forms[0].chkadm.value);
	document.forms[0].hmode.value="GOADM";
	document.forms[0].submit();
	}
/* function showHelpDetails(divId){
	//alert('show'+divId);
	var divId='HelpId';
	document.getElementById(divId).style.display="block";
			
	document.getElementById('minus'+divId).style.display="block";
	document.getElementById('plus'+divId).style.display="none";
	
}

function hideHelpDetails(divId){
	var divId='HelpId';
	//alert('show'+divId);
	document.getElementById(divId).style.display="none";
		
	document.getElementById('minus'+divId).style.display="none";
	document.getElementById('plus'+divId).style.display="block";
	
} */
 </script>
</head>
<body onLoad="SetCursorToTextEnd('strCrNoId');document.forms[0].strCrNo.focus();">
<html:form action="transactions/IpdBillManagementTransNewBSCNT.cnt" method="post">

		<fieldset form="form1">

						<div id='errMsg' class="errMsg">
							<bean:write name="ipdBillManagementTransBean"
								property="strErrMsg" filter="false" />
						</div>
						<div id="normalMsg" class="normalMsg">
							<bean:write name="ipdBillManagementTransBean" property="strMsg" />
						</div>
					    <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
						
			<legend class='legendHeader' id='nonPrintableLegend'>IPD
				Bill Management</legend>
			<div class="legend2" id='nonPrintableLegend2'>
				<!-- <button  type="button" id="cancelButton" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
				<button type="button"
					class="btn btn-outline-danger mt-1 btn-circle cancelbtn"
					onclick="openMenu('SIGLEMENUHOME','')">
					<i class="fas fa-ban iround" title="Cancel"></i>
				</button>
				<button type="button" id="generatebutton"
					class="btn btn-outline-primary mt-1 btn-circle printbtn"
					tabindex='2' onClick='generate();' data-toggle="modal"
					style="display: none;">
					<i class="fas fa-print iround" title="Print Report"></i>
				</button>
			</div>

			<div class="viewport" id="nonPrintable">
				<div class="container-fluid ">
					<div class="prescriptionTile">
						<div class="row rowFlex reFlex" id='goid'>
							<div class="col-sm-2" align="right">
								<font color="red">*</font>CR No.
							</div>
							<div class="col-sm-2">
								<crNo:crNo value="${ipdBillManagementTransBean.strCrNo}"
									js="onkeypress='return initGoFunc(event);'" id="strCrNoId"
									className="form-control"></crNo:crNo>
							</div>
							<div class="col-sm-2">
								<a href="#" class="btn btn-sm btn-success"
									onclick="return goFunc();" style="font-size: 1rem;">
									GO&nbsp;<i class="fas fa-angle-double-right"></i>
								</a>
							</div>
							<div class="col-sm-6">
								
							</div>
						</div>
					
						<bean:write name="ipdBillManagementTransBean" property="strPatAdmissionList" filter="false" />
							
						
						<%-- <table class="TABLEWIDTH" cellspacing="1px" align="center">
	
 <tr>
   <td class="LABEL" ><font color="red">*</font>CR No.</td>
	 <td class="CONTROL" colspan="4">
        <crNo:crNo value="${ipdBillManagementTransBean.strCrNo}" js="onkeypress='return initGoFunc(event);'" id="strCrNoId"></crNo:crNo>
		<img title="click here for Bill Details" src="../../hisglobal/images/Go.png" name="go"  onclick="return goFunc();" onkeypress="return goFunc();"/>			 
			</td>
		</tr>
		<tr>
					<td width="100%" colspan='6' >
						<bean:write name="ipdBillManagementTransBean" property="strPatAdmissionList" filter="false"/>
					</td>
				</tr>
</table> --%>

						<div id="admlistDivId" style="display: none;"></div>
						<!-- <table class="TABLEWIDTH" border="0" cellpadding="1px"
							cellspacing="1px" align="center">
							<tr class="FOOTER">
								<td width='3%'>
									<div id="plusHelpId" align="center">
										<img style="cursor: pointer; cursor: hand"
											src="../../hisglobal/images/plus.gif" name="plusHelp"
											align="middle" onclick="showHelpDetails('HelpId');" />
									</div>
									<div id="minusHelpId" style="display: none" align="center">
										<img style="cursor: pointer; cursor: hand"
											src="../../hisglobal/images/minus1.gif" name="minusHelp"
											onclick="hideHelpDetails('HelpId');">
									</div>
								</td>
								<td><div align="left">Legends</div></td>
								<td><div align="right">
										<font size="2" color="red">*</font> Mandatory Fields
									</div></td>
							</tr>
						</table> -->
						<div id="HelpId" style="display: none">
							<table class="TABLEWIDTH" border="0" cellspacing="1px"
								align="center">
								<tr>
									<td bgcolor='#FFD700'></td>
									<td class="CONTROL">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Discharged Patient</td>
								</tr>
								<tr class=FOOTER>
									<td colspan='2'></td>
								</tr>
							</table>
						</div>
						<hr>
						<div class="row rowFlex reFlex">
							<div class="col-sm-10"></div>
							<div class="col-sm-2" align="right">
								<i class="fas fa-asterisk"
									style="color: red; font-size: smaller;"></i>Fields Mandatory
							</div>
						</div>

						<input type="hidden" name="hmode" /> <input type="hidden"
							name="CrNo" value="${ipdBillManagementTransBean.strCrNo }" />
					</div>
				</div>
			</div>
		</fieldset>

	</html:form>
	<tag:autoIndex></tag:autoIndex>  
</body>
</html>