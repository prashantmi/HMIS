<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

 
 
<html>
<head>
<meta charset=UTF-8">
<title>Acknowledge Desk</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../js/acknowledgeDesk_trans.js"></script>
<script language="Javascript" src="../js/issueDetails_util.js"></script> 
<script language="Javascript" src="../../hisglobal/js/validationCommon.js"></script>


<script type="text/javascript">


function hideIssuePopup(){
	
		document.getElementById("issueDtlsDivId").innerHTML = "";
		hide_popup('popUpDiv');
		cancelToDesk();
		
		
	}


</script>

</head>
<body onload="getPrintReport();getReturnRequestReport();"> 
<html:form name="acknowledgeTransBean" action="/transactions/AcknowledgeTransCNT"
	type="mms.transactions.controller.fb.AcknowledgeTransFB">
	
<center>
	<div id="errMsg" class="errMsg"><bean:write name="acknowledgeTransBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="acknowledgeTransBean" property="strWarningMsg"/></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="acknowledgeTransBean" property="strNormalMsg"/></div>
	
</center>
		<tag:tab tabLabel="Acknowledge Desk"
				selectedTab="FIRST" align="center" width="TABLEWIDTH">
              </tag:tab>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	<tr class="HEADER">
			<td colspan="4" ></td>
	</tr>
		
   </table>
 
	<bean:write name="acknowledgeTransBean" property="strAcknowledgeDetails" filter="false"/>
	<bean:write name="acknowledgeTransBean" property="strItemDetails" filter="false"/>
		
	 <table class="TABLEWIDTH" align="center" cellpadding="1" cellspacing="1">
	     <tr class="TITLE">
			<td colspan="4" ></td>
	    </tr>
  		<tr>
			<td class="LABEL" width="50%">
			<div align="right"><font color="red">*</font>Remarks</div>
			</td>
			<td class="CONTROL" width="50%">
			<div align="left"><textarea name="strRemarks" rows="2"></textarea>
			</div>
			</td>
		</tr>
  
  		<tr class="FOOTER">
			 <td colspan="4"><font color="red">*</font> Mandatory Fields  </td>
		</tr>
	<!--	<tr id="saveId">
			<td align="center" colspan="4">
				<img style="cursor: pointer"  src="../../hisglobal/images/btn-sv.png" onClick="return validate2();" title="Click to Save Record"/>
				<img style="cursor: pointer" src="../../hisglobal/images/btn-clr.png" title="Click to Clear Page" onClick="resetPage();" />
				<img style="cursor: pointer" src="../../hisglobal/images/back_tab.png" title="Click to Return On Desk" onClick="cancelToDesk();" >
			</td>
		</tr>-->
	</table>
	 <br>
	<div align="center" id="saveId">					 
					 	<a href="#" class="button" id="submitId" onclick=' return validate2();'><span class="save">Save</span></a>
						<a href="#" class="button"	onclick="resetPage();"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelToDesk();"><span class="cancel">Cancel</span></a>
					</div> 
	
<input type="hidden" name="hmode"/>

<input type="hidden" name="strPath" value="${acknowledgeTransBean.strPath}">
<input type="hidden" name="chk" value="${acknowledgeTransBean.chk[0]}">
<input type="hidden" name="strHidVal" value="${acknowledgeTransBean.strHidVal}">
<input type="hidden" name="strAckStatus" value="${acknowledgeTransBean.strAckStatus}">
<input type="hidden" name="strTransNo" value="${acknowledgeTransBean.strTransNo}">
<input type="hidden" name="strStoreId" value="${acknowledgeTransBean.strStoreId}">
<input type="hidden" name="strRequestTypeId" value="${acknowledgeTransBean.strReqTypeId}">
<input type="hidden" name="strRequestMode" value="${acknowledgeTransBean.strRequestMode}">
<input type="hidden" name="strReturnStatus" value="${acknowledgeTransBean.strReturnStatus}">
<input type="hidden" name="strReqTypeId" value="${acknowledgeTransBean.strReqTypeId}">




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
   <div class="popUpDiv" id="popUpDiv1" style="display:none;">
   <table bgcolor="white">
     <tr>
      <td>
         <div id="transferDtlsDivId" style="display:block;"></div>
      </td>
     </tr>
    </table>
   </div>

<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>