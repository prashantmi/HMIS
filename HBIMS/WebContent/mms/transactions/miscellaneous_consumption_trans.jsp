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
<script language="Javascript" src="../../mms/js/miscellaneous_consumption_trans.js"></script>
<script language="Javascript" src="../js/searchItems_util.js"></script>


</head>
<body >
<html:form name="miscellaneousConsumptionBean" action="transactions/MiscellaneousConsumptionTransCNT" type="mms.transactions.controller.fb.MiscellaneousConsumptionTransFB">

	
<center>
	<div id="errMsg" class="errMsg"><bean:write name="miscellaneousConsumptionBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="miscellaneousConsumptionBean" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="miscellaneousConsumptionBean" property="strNormalMsg" /></div>

	
<tag:tab tabLabel="Miscellaneous Consumption"
				selectedTab="FIRST" align="center" width="TABLEWIDTH">
              </tag:tab>
 </center>

<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">  
	 
	<tr class="HEADER">
			<td colspan="6"></td>
	</tr>
	
	<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Store Name</td>
			
      		<td width="25%" class ="CONTROL" align="center" > 
		      <html:select name="miscellaneousConsumptionBean" styleClass="comboMax" property="strStoreId" onchange="getItemCategoryCmb();">
		       <bean:write name="miscellaneousConsumptionBean" property="strStoreNameValues" filter="false"/>
       		  </html:select>
     	   </td>
     	   <td class="LABEL" width="25%"><font color="red">*</font>Item Category</td>
  		 	<td width="25%" class ="CONTROL" align="center" > <div id="itemCatDivId">      
       		<select name="strItemCategoryId1" class="comboNormal"><bean:write name="miscellaneousConsumptionBean" property="strItemCategoryValues" filter="false"/></select>
       		</div>
      		</td>
	</tr>
</table>
<table class="TABLEWIDTH" align="center" cellpadding="1" cellspacing="1">   
		
	<tr class="TITLE">
	  <td colspan="4"><div id='' align="right">
		<div>
		  <img style="cursor: pointer;height: 20px" id='strSearchItemButtonDivId' src="../../hisglobal/images/ItemFinder.png"  title='Click to Search Items' onclick='getItemSelectPopup();'>
		</div>
	  </td>
	</tr>
	</table>
 <table class="TABLEWIDTH" align="center"cellpadding="1px" cellspacing="1px" bgcolor="black">
   <tr>
   
	   <td class='multiRPTLabel' width='40%'>Item/Drug Name</td>
	   <td class='multiRPTLabel' width='20%'>Batch/Serial No.</td>
	   <td class='multiRPTLabel' width='10%'>Avl Qty</td>
	   <td class='multiRPTLabel' width='10%'><font color="red">*</font>Consumption Qty</td>
	   <td class='multiRPTLabel' width='20%'><font color="red">*</font>Consumption Unit</td>
    </tr>
  </table>
    <div id="id1"></div>
    <table class="TABLEWIDTH" align="center" cellpadding="1"
		cellspacing="1">
		<tr class="TITLE">
			<td colspan="6"></td>
		</tr>
		</table>
    <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
    <tr>
	    <td class="LABEL" width="50%" colspan="1">Remarks</td>
	    <td width="50%" class ="CONTROL" colspan="1"><textarea name="strRemarks"cols="16" rows="2"></textarea> </td>
    </tr>
   </table>
    
	<table class="TABLEWIDTH" align="center"cellpadding="1px" cellspacing="1px">
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
</table>
<!-- 
<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center">
			<img style="cursor: pointer; "
				src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" title="Click to Save Record"/>
				<img style="cursor: pointer; " src="../../hisglobal/images/btn-clr.png" title="Click to Clear Page" onClick="Clear();" >
			<img  style="cursor: pointer; " src="../../hisglobal/images/btn-ccl.png" title="Click to cancel process"   onClick="cancelFunc();" >
		</td>
		</tr>
</table>-->
<br>
    <div align="center" id=" ">					 
					 	<a href="#" class="button" id="submitId" onclick=' return validate1();'><span class="save">Save</span></a>
						<a href="#" class="button"	onclick="cancel();"><span class="clear">Clear</span></a> 
						<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
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


</html:form>
<jsp:include page="miscellaneous_consumption_item_search_row.jsp"></jsp:include>
	<tag:autoIndex></tag:autoIndex>   
</body>
