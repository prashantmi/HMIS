<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>



<html>
<head>
<meta charset=UTF-8">
<title>Update Stock Status </title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../js/dwh_main_updateStockStatus_trans.js"></script>
<script language="JavaScript">



</script>

</head>
<body >
<html:form name="updateStockStatusTransFB" action="/transactions/UpdateStockStatusTransCNT" type="mms.transactions.controller.fb.UpdateStockStatusTransFB">
	<div id="errMsg" class="errMsg"><bean:write name="updateStockStatusTransFB" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="updateStockStatusTransFB" property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="updateStockStatusTransFB" property="strNormalMsg" /></div>
	
	<tag:tab tabLabel="Update Stock Status" selectedTab="FIRST" align="center" width="TABLEWIDTH">
              </tag:tab>      
              
              
              
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	     <tr class="HEADER">
			<td colspan="3"></td>
			
			
			 <td colspan="1" align="right" >
		     	<span>
		     		<html:checkbox property="strViewMode" name="updateStockStatusTransFB" value="3" onclick="openViewPage();">View</html:checkbox>
		     	</span>
		     </td>	
			
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
					<select name="strItemCategoryId" class='comboMax' onchange='getDrugCombo();'>
						<bean:write name="updateStockStatusTransFB" property="strItemCatgCmb" filter="false" />
					</select>
				</div>
			</td>		
	   </tr>
	   
	   <tr>
			<td width="25%" colspan="1" class="LABEL">Group Name</td>
			<td width="25%" colspan="1" class="CONTROL">
			<div id="strGroupDivId" >
				<select name="strGroupId" class="comboMax" onchange="getDrugCombo();">
					<bean:write name="updateStockStatusTransFB" property="strGroupCombo" filter="false"/>
				</select>
			</div>				
			</td>
		
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>Item/Drug Name</td>
			<td width="25%" colspan="1" class="CONTROL">
			<div id="strDrugDivId" >
				<select name="strDrugId" class="comboMax" >
					<option value="0">Select Value</option>
				</select>
			</div>
			  				
			</td>
			
		</tr>
		<tr>
		<td width="100%" colspan="4" class="CONTROL">
			
			<div id="s" align="center"><img style="cursor: pointer; " title="Get Details" src="../../hisglobal/images/Go.png" onclick="return getCurrentStockDetails();"/></div>
						  				
			</td>
		</tr>	
	   </table> 
	   
	   <div id="strCurrentStockDtlDivId" ></div>	
	 
		<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
		     <tr id="stockDtlsDivId" class="HEADER">
	     			<td colspan="4">New Stock Status Details</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%" colspan="1"><font color="red">*</font>New Stock Status</td>				
				<td  class="CONTROL" width="25%" colspan="1">
					<div id="stockStatusComboDivId">
						<select name="strNewStockStatus" class="comboNormal">
							<option value="0">Select Value</option>
						</select>
					</div>
				</td>
			<td class="LABEL" width="25%" colspan="1"><font color="red">*</font>Whether Update New Status In All DWH</td>
			<td class="CONTROL" width="25%" colspan="1" >				
					<input type="checkbox" name="strWhetherUpdateNewStatusInAllDWH"  onClick="chkBoxFunc(this);"  value="1" >				
			</td>
		
					 
		</tr>
		
		</table> 
    
   <table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
      <tr id="showQtyDiv" style="display:block;">
	   	 <td width="25%" colspan='1' class="LABEL">Qty</td>
	   	 
         <td width="25%" colspan='1' class="CONTROL">
         	<input type="text" name="strQty" maxlength="9"  id="strQtyId"   readonly/>
         </td>
         
         <td width="75%" colspan='3' class="LABEL"></td>     
         
   </tr>
  
  </table> 
   <table class="TABLEWIDTH" align="center" border="0" cellspacing ="1px">
		
		<tr >
		<td class="LABEL" width="25%" colspan="1" ><font color="red">*</font>Approved By</td>
			<td class="CONTROL" width="25%" colspan="1" >
				<div id="approvedByComboDivId">
					<select name="strApprovedBy" class="comboNormal" onchange="checkValCombo();">
						<option value="0">Select Value</option>
					</select>
				</div>
			</td>		
			<td class="CONTROL" width="25%" id='labelId'>
            	<div id='labelNameId'></div>
  			 </td>
			 <td class="CONTROL" width="25%">
            	<div id="nameOtherFld" style="display: none">
            		<input type='text' name='strApprovedByOther' value='' onkeypress='return validateData(event,16);' maxlength='50'>
            	</div>
            </td>
		</tr>
		
	 <tr >
		    <td width="25%" class ="LABEL" valign="middle" colspan="1"><font color="red">*</font>Reason To Update Status</td>
		    <td width="25%" class ="CONTROL" colspan="1">
			    <textarea  name="strRemarks" cols="25" rows="2" onkeypress="return validateData(event,9);"></textarea>
		    </td>
		      <td width="50%" class ="CONTROL" colspan="2">
 	 </tr>
		
		</table>
	
	
		 
		 <table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
                
		<tr class="FOOTER">
			 <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>
	
	
	<table  class="TABLEWIDTH" align="center">
	  <tr>
		<td align="center">
			  <img style="cursor:pointer;cursor:pointer" title="Click to Save Record" src="../../hisglobal/images/btn-sv.png"  onClick=" return validate1();" onkeypress="if(event.keyCode==13) validate1();"/>
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
<input type="hidden" name="strHiddenValues" value="0" />
<input type="hidden" name="strIndex" value="" />
<input type="hidden" name="strChkValue" value="0" />

</html:form>
	<tag:autoIndex></tag:autoIndex>   
</body>
</html>