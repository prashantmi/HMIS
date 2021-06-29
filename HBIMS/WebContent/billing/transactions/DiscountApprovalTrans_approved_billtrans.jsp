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
<head><meta charset=utf-8>
<title>Discount Approval</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">	
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="../../billing/js/DiscountApprovalTrans.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
</head>
<body onLoad="butdis();hlpOnLoad();SetCursorToTextEnd('strCrNoId');" onfocus="checkPopUpAndSetDefaultCrNo();" onUnload="closePopUp();">
<html:form action="transactions/DiscountApprovalTransCNT.cnt" method="post" onsubmit="return goFunc();" enctype="multipart/form-data">
<div id="menu1"></div>

<div id ="errMsg" class="errMsg"><bean:write name="discountApprovalTransBean" property="strErrMsg"/></div> 
<div id ="warningMsg" class="warningMsg"><bean:write name="discountApprovalTransBean" property="strWarning"/></div>
<div id ="normalMsg" class="normalMsg"><bean:write  name="discountApprovalTransBean" property="strMsg"/></div>

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



 
 <tag:tab tabLabel="Discount Approval" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	
	
 <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
  <tr class="HEADER"> 
    <td colspan="4">Discount&gt;&gt;Approval</td>
  </tr>
  
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
    <td colspan="3" class="CONTROL">
   <crNo:crNo
						 id="strCrNoId"
						value="${discountApprovalTransBean.strCrNo}"
						js="onkeypress='return initGoFunc(event);'"></crNo:crNo> 
   <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/viewDetails.gif" 
  		 title="click here for patient search"  name='searchPatient' 
  	 onclick="showPatientListingWindow('2',document.forms[0].strCrNo,'setSelectedCrNo');"/>
   <input type="image" style="cursor:pointer;cursor:hand" title="Discount Details" align="top" src ="../../hisglobal/images/Go.png" name="go" value="Go" onclick="return goFunc();" onkeyup="goFuncOnEnter(event);">
  </td>   
  </tr>
  </table>
  <div id="tldglbdiv">
  
   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
     <tr>
       <td width='5%' class='TITLE' align='center'>	<div id="plusonLineId" style="display: none"><img
	      style="cursor: pointer;cursor: hand" src="../../hisglobal/images/plus.gif" name="plusonLine"  onclick="showDetails();" /></div>
	      <div id="minusonLineId"><img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/minus.gif" name="minusonLine" 
               onclick="hideDetails();">
          </div>
       </td>  
       <td colspan="7" class="TITLE">Patient Details</td>
    </tr>
   </table>
  </div>
  <div id="patdtltld" style="display:none">
       <bean:write name="discountApprovalTransBean" property="strPatientDetailsView" filter="false"/>
  </div>
   
  <div id="onLinetld">
       <DisDtl:reqDisDtl  crNo="${discountApprovalTransBean.strCrNo1}" mode="2"></DisDtl:reqDisDtl>
  </div>
   <div id="id1"></div>
   <div id ="disBnR" style="display:none">
   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
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
  </table>     
  </div>
   <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="1px">
  <tr class="FOOTER">
    <td colspan="1" width='90%'><div align='left'><font size="2" color="red">#</font>Discount By combo shows current logged in user in case of employee  otherwise all employee list is shown.</div></td>
    <td colspan="1" width='10%'><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>	
 <div id="lastbuttons" style="display: none"> 	
  <table border="0" class="TABLEWIDTH" align="center">
		<tr>

			
			<td align="center">
			<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer;cursor:hand" title="Save Record"   onclick="validateFunc1();">
			 <img style="cursor: pointer;cursor:hand" src="../../hisglobal/images/btn-clr.png" title="click here to clear the contents"   
				name="clearImg" onclick="initPage();"/> <img style="cursor: pointer;cursor:hand" title="click here to cancel the process" 
				src="../../hisglobal/images/btn-ccl.png" onclick="cancel();"/>
							--><br><a href="#" class="button" id="" onclick='validateFunc1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="initPage()"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
				
				</td>
			
		</tr>
	</table>
	</div>
	<div id="onlyClearbutton" style="display: block">
	<table border="0" class=' TABLEWIDTH' align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td align="center"><!-- <img style="cursor: pointer; cursor: hand" title="click here to clear the contents"  
				src="../../hisglobal/images/btn-clr.png"
				onclick="initPage();"> <img
				style="cursor: pointer; cursor: hand" title="click here to cancel the process"
				src="../../hisglobal/images/btn-ccl.png" onclick="cancelFunc();">
				--><br>
				<a href="#" class="button"	onclick="initPage();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
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
		

</html:form>
<tag:autoIndex></tag:autoIndex>  
</body>
</html>