<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>



<html>
<head>
<meta charset=UTF-8">
<title>Update Stock Status</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">


	
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>

<script language="JavaScript" src="../js/dwh_main_updateStockStatus_trans.js"></script>


<script language="JavaScript">



</script>

</head>
<body >
<html:form name="updateStockStatusTransFB" action="/transactions/UpdateStockStatusTransCNT" type="mms.transactions.controller.fb.UpdateStockStatusTransFB">
	<div id="errMsg" class="errMsg"><bean:write name="updateStockStatusTransFB" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="updateStockStatusTransFB" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="updateStockStatusTransFB" property="strNormalMsg" /></div>
	
	<div class='popup' id='drugDtlId' style="display: none">
		<table width='400' border="0" cellspacing="1" cellpadding="1">
			<tr class="HEADER">
				<td colspan='3'>Details</td>
	
				<th align='right'><img style='cursor: pointer; '
					src='../../hisglobal/images/popUp_cancel.JPG' title='Click to Close Pop-Up' onClick="hideDrugDetails('drugDtlId');">
				</th>
			</tr>
		</table>


		<table width='400' border="0" cellspacing="1" cellpadding="1">
	
			<tr>
				<td colspan="1" class='multiLabel'>Approved By</td>
				<td colspan="1" class='multiLabel'>Reason</td>
				<td colspan="1" class='multiLabel'>Whether updated in all DWH</td>
			</tr>
			<tr>
				<td colspan="1" class='multiControl'>
				<div id='1'></div>
				</td>
				
				<td colspan="1" class='multiControl'>
				<div id='2'></div>
				</td>
				
				<td colspan="1" class='multiControl'>
				<div id='3'></div>
				</td>
	
			</tr>
								
		</table>
	</div>
	
	
	<tag:tab tabLabel="Update Stock Status" selectedTab="FIRST" align="center" width="TABLEWIDTH">
              </tag:tab>      
              
              
              
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	     <tr class="HEADER">
			<td colspan="4"></td>
			 
		</tr>
		
		
	    <tr>
			<td width="25%" class="LABEL">
			<font color="red">*</font>Store Name
			</td>
			<td width="25%" class="CONTROL">
				<select name="strStoreId" class='comboMax' onchange="getItemCategory();">
						<bean:write name="updateStockStatusTransFB" property="strDrugWareHouseNameCmb" filter="false" />
				</select>
			</td>
			
			<td  width="25%" class="LABEL">
				<font color="red">*</font>Item Category
			</td>
			<td width="25%" class="CONTROL">
				<div id="itemCategoryDivId">
					<select name="strItemCategoryId" class='comboMax'>
						<bean:write name="updateStockStatusTransFB" property="strItemCatgCmb" filter="false" />
					</select>
				</div>
			</td>		
	   </tr>
	   
	  
	   
	    <tr >
			<td class="LABEL" colspan="2"><font color="red">*</font>From Date</td>
			<td class="CONTROL" colspan="2"><dateTag:date name="strFromDate" value="${updateStockStatusTransFB.strCurrentDate}" /></td>
		</tr>
		<tr >
			<td class="LABEL" colspan="2"><font color="red">*</font>To Date</td>
			<td class="CONTROL" width="25%" ><dateTag:date name="strToDate" value="${updateStockStatusTransFB.strCurrentDate}" /></td>
			
			<td class="multiControl" width="25%" colspan="1">
				<img style="cursor: pointer; " title="Get Details" src="../../hisglobal/images/Go.png" onclick="return getUpdatedStockStatusViewDetails();"/>
			</td>
			
		</tr>     
		
		
	   
	   </table> 
	   
	   <div id="viewCurrentStockDtlDivId" ></div>	
	 
	
	
		 
		 <table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
                
		<tr class="FOOTER">
			 <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>
	
	
	<table  class="TABLEWIDTH" align="center">
	  <tr>
		<td align="center">
              <img style="cursor:pointer;cursor:pointer" title="Click to Clear Page" src="../../hisglobal/images/btn-clr.png" name="clearImg" onclick="document.forms[0].reset(),clearMsg('INITVAL')" onkeypress="if(event.keyCode==13) document.forms[0].reset(),clearMsg('INITVAL');">
              <img style="cursor: pointer; " title="Click to Return Main Menu" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage()" onkeypress="if(event.keyCode==13) cancelPage()" />              
		</td>
	 </tr>
	</table>	



<input type="hidden" name="hmode"/>

<input type="hidden" name="strStoreName" value=""/>
<input type="hidden" name="strItemCategoryName" value=""/>
<input type="hidden" name="strItemCategoryCode" value=""/>
<input type="hidden" name="strCurrentDate" value="${updateStockStatusTransFB.strCurrentDate}" />
<input type="hidden" name="strHiddenValues" value="${updateStockStatusTransFB.strHiddenValues}" />

</html:form>
	<tag:autoIndex></tag:autoIndex>   
</body>
</html>