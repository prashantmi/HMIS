
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8><title>Online Request Cancellation</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/OnlineReqCancellation_Trans.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>

</head>
<body onload="showPatientDetails();" onFocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">

<html:form action="transactions/OnlineRequestCancellationTransCNT.cnt" method="post" onsubmit="GO();">


<div id='normalMsg' class="normalMsg"><bean:write name="OnlineRequestCancellationTransBean" property="strMsg" filter="false"/></div>
<div id='errMsg' class="errMsg"><bean:write name="OnlineRequestCancellationTransBean" property="strErrMsg" filter="false"/></div>
 
   <div id="warningMsg" class="warningMsg"><bean:write
		name="OnlineRequestCancellationTransBean" property="strWarningMsg" filter="false" /></div>

<tag:tab tabLabel="Online Request Cancellation" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
<table class='TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="5"><b>Online Request Cancellation &gt;&gt;</b></td>
  </tr>
 
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font><b>CR No.</b></td>
    <td colspan="3" class="CONTROL">
    <crNo:crNo
						 id="strCrNoId"
						value="${OnlineRequestCancellationTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo>
    
   <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" title="click here for patient search"  name='searchPatient' onclick="showPatientListingWindow('5',document.forms[0].strCrNo,'setSelectedCrNo');"/>
  <img  src="../../hisglobal/images/Go.png" name="go" onclick="return GO();" onkeypress="return initGoFunc(event);">
    </td>   
  </tr>
</table> 

<div  id="details" style="display: none">
			<table class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
  	 			 <tr class="HEADER"> 
  	 			 <td width="25" class="TITLE">
     										<div id="plus" style="display:none">
   											<img src="../../hisglobal/images/plus.gif"  onclick="showinfo();">
   											</div><div id="minus" style="display:block"><img src="../../hisglobal/images/minus.gif"  onclick="hideinfo();"></div>
  										 </td>
  	 			  <td colspan="4"><b> Patient Demographic Information </b></td>
    									
 				 </tr>
 			 </table>
 </div>

 <div  id="demographicInfo" style="display:none" align="center">
		<table class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<bean:write name="OnlineRequestCancellationTransBean" property="strPatientDetailsView" filter="false"></bean:write>
 	</table>
 </div>

<div id="onlinerequest" style="display:none">
		<bean:write name="OnlineRequestCancellationTransBean" property="strOnlineReqDtl" filter="false"/>
</div>
 
<div id="combo" style="display:none">
 
 <table class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
 <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Cancel By</td>
    <td width="25%" class="CONTROL"><select name="strCancelledBy"  class="comboBig" >
    										<bean:write name="OnlineRequestCancellationTransBean" property="strCancelledBy" filter="false"/>
    							 </select></td> 
  </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Cancellation Reason</td>
    <td width="25%" class="CONTROL"><select name="strCancelReason" class="comboNormal" onchange="showText();"> 
    									<bean:write name="OnlineRequestCancellationTransBean" property="strCancelReason" filter="false"/>
    								</select>            
    							 <input type="text" class="txtFldMax" name="strOtherReason" value="" maxlength="" onkeypress="" ></td> 
    
   
    
   </table>
</div>  		
 

	
<table class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
<tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
</tr>
</table>
<div id="lastbuttons" style="display:none">	
<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to save the selected data" src="../../hisglobal/images/btn-sv.png" onClick="validate1();">
			<img style="cursor:pointer;cursor:hand" title="click here to clear the contents"  src="../../hisglobal/images/btn-clr.png" onclick="clearPage();" >
			 <img style="cursor:pointer;cursor:hand" title="click here to cancel the process" src="../../hisglobal/images/btn-ccl.png" onclick="initPage();" >
			 -->
			 <br>
			
			<a href="#" class="button" id="" onclick='validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="clearPage();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="initPage();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
</table>
</div>
<div id="onlyClearbutton" style="display:block">	
<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			<!-- <img style="cursor:pointer;cursor:hand" title="click here to clear the contents"   src="../../hisglobal/images/btn-clr.png" onclick="clearPage();" >
			<img style="cursor:pointer;cursor:hand" title="click here to cancel the process" src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();"/>
			 -->
			 <br>
			<a href="#" class="button"	onclick="clearPage();"><span class="clear">Clear</span></a> 
			<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
</table>
</div>

<input type="hidden" name="hmode"/>
<input type="hidden" name="CrNo" value="${OnlineRequestCancellationTransBean.strCrNo }"/>
<input type="hidden" name="chkValue"/>
<input type="hidden" name="strCRNoSatus" value="${OnlineRequestCancellationTransBean.strCRNoSatus }"/>
<input type="hidden" name="gblCRValue"/>
</html:form>

<div class="popup" id="popup" style="display: none">  

</div>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>