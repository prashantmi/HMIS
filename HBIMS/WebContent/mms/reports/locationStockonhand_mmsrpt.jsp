<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>Location Wise Stock In Hand</title>

<link href="../../hisglobal/css/dwh.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/autocomplete.css" rel="stylesheet"	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-ui.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery.autocomplete.min.js"></script>
<script language="Javascript" src="../../hisglobal/js/autocompleteItemSearch.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/locationStockonhand_mmsrpt.js"></script>
<script>
	function onLoadCancelCheck() {
	
	var seatId= '<%=(String) request.getSession().getAttribute("SEATID")%>';
	if(seatId== "null")
	{
		document.getElementById('cancelBtn').style.display='none';
	}	
 }
</script>
</head>
<body class="background" onload="getStoreTypeDisplay();onLoadCancelCheck();">
<div id="mask"></div>
<div id="dvLoading"></div>
<html:form action="/reports/LocationWiseStockSummaryRptCNT" method="post" styleClass="formbg">

	<div class="errMsg" id="errMsg"><bean:write name="locationStockOnHandRpt"
		property="strErrMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="locationStockOnHandRpt" property="strNormalMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="locationStockOnHandRpt" property="strWarningMsg" /></div>


	<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px">
		<tr class="HEADER">
		 <td>Location Wise Stock In Hand</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">		 
		<tr id="districtDrugWarehouseDivId" >
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>Store Name</td>
			<td width="70%" colspan="3" class="CONTROL">
			<div id="strStoreDivId" >
				<select name="strDistrictStoreId" class="comboMin" onchange="getStoreTypeCmb();">
					<bean:write name="locationStockOnHandRpt" property="strDistrictStoreValues" filter="false"/>
				</select>
			</div>				
			</td>
		</tr>
		<tr id="">
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>Programme Name:</td>
			<td width="70%" colspan="3" class="CONTROL">
			<div id="strProgrammeDivId" >
				<select name="strProgrammeId" class="comboMin" onchange="">
					<bean:write name="locationStockOnHandRpt" property="strProgrammeCmb" filter="false"/>
				</select>
			</div>				
			</td>
		</tr>
		<tr id="" >
			<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>Stock Status</td>
			<td width="70%" colspan="3" class="CONTROL">
			<div id="" >
				<select name="strStatusId" class="comboMin" onchange="" >
					<option title="All" selected="selected" value="0">All</option>
					<option title="Active" value="10">Active(Ready For Issue)</option>
					<option title="Quarantine" value="15">Quarantine</option>
					<option title="InActive" value="11">InActive(NOSQ)</option>
				</select>
			</div>				
			</td>
		</tr>		
	    </table>
	   <div id="storeTypeId"> 
			<div class="line">
				<table class='NEWTABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px">
					<tr><td  width="95%"><font color="red">*</font>Store Type</td></tr>
				</table>		            	
			</div>	
			<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
			
			<tr>
	  			<td class="CONTROL" colspan="2">  			 
					<div id="storeTypeDivId" align="right">
						<select id="strLeftItemIds" name="strLeftItemIds" size="6" multiple style="width: 280px" >
							<bean:write name="locationStockOnHandRpt" property="strDrugCombo" filter="false"/>
						</select>
					</div>				
				</td>
				<td width="6%" class="CONTROL" colspan="2">			
					<center>
						<img src="../../hisglobal/images/forward3.gif" width="30" height="21" onclick ="LeftListTransfer();"></center>
					<center>
				<img src="../../hisglobal/images/forwardward.gif" width="30" height="21" style="display: none;"
					align="middle" onClick="shiftAllToRight('strLeftItemIds','strRightItemIds',1);"/></center>
				<br/>
				<center>				
				<img src="../../hisglobal/images/backward.gif" width="30" height="21" style="display: none;"
					onClick="shiftAllToLeft('strLeftItemIds','strRightItemIds',1);"></center>
				<center>
					<img src="../../hisglobal/images/back3.gif" width="30" height="21" 
						onclick="shiftToLeft('strLeftItemIds','strRightItemIds',1);"/>
				</center>
				</td>			
				<td colspan="2" class="CONTROL">
					<div id="RightItemIds" align="left">
						<select id="strRightItemIds" name="strRightItemIds" size="6" multiple style="width: 280px">
							<bean:write name="locationStockOnHandRpt" property="strRightItemList" filter="false"/>
						</select>
					</div>
				</td>		
			</tr>					
			</table>
		</div>
		
		<div id="itemMultiSelectId" style="display:block;"> 
			<div class="line">
				<table class='NEWTABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px">
					<tr><td  colspan="4" width="95%"><font color="red">*</font>Item Name</td></tr>
					
				</table>		            	
			</div>	
			<table class='TABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px" height="30px">
			<tr>
			<td colspan="2" class="LABEL" width="27%"><font color="red">*</font>Drug Classification</td>
			<td class="CONTROL">
			<div id="drugClassCmbDivId" align="left">
			<select	class='comboNormal' name="strDrugClassCode" tabindex="1" onchange="getLeftComboItems('@');">
					<bean:write name="locationStockOnHandRpt" property="strDrugClassVal" filter="false" />
			</select></div>
			</td>			
				<td	style="font-family: Verdana; height: 15px; font-weight: bold; font-style: normal;"
					colspan="3" align="right">
					<div id="divAtoZ" >
						<a href='#' id="$Id" class="class4" onclick="getLeftComboItems('$');" style="color:red"><b>(0-9)</b></a>&nbsp;
						<a href='#' id="AId" class="class4" onclick="getLeftComboItems('A');"><b>A</b></a>&nbsp; 
						<a href='#' id="BId" class="class4" onclick="getLeftComboItems('B');"><b>B</b></a>&nbsp; 
						<a href='#' id="CId" class="class4" onclick="getLeftComboItems('C');"><b>C</b></a>&nbsp; 
						<a href='#' id="DId" class="class4" onclick="getLeftComboItems('D');"><b>D</b></a>&nbsp;
						<a href='#' id="EId" class="class4" onclick="getLeftComboItems('E');"><b>E</b></a>&nbsp; 
						<a href='#' id="FId" class="class4" onclick="getLeftComboItems('F');"><b>F</b></a>&nbsp; 
						<a href='#' id="GId" class="class4" onclick="getLeftComboItems('G');"><b>G</b></a>&nbsp;
						<a href='#' id="HId" class="class4" onclick="getLeftComboItems('H');"><b>H</b></a>&nbsp; 
						<a href='#' id="IId" class="class4" onclick="getLeftComboItems('I');"><b>I</b></a>&nbsp; 
						<a href='#' id="JId" class="class4" onclick="getLeftComboItems('J');"><b>J</b></a>&nbsp; 
						<a href='#' id="KId" class="class4" onclick="getLeftComboItems('K');"><b>K</b></a>&nbsp;
						<a href='#' id="LId" class="class4" onclick="getLeftComboItems('L');"><b>L</b></a>&nbsp; 
						<a href='#' id="MId" class="class4" onclick="getLeftComboItems('M');"><b>M</b></a>&nbsp; 
						<a href='#' id="NId" class="class4" onclick="getLeftComboItems('N');"><b>N</b></a>&nbsp;
						<a href='#' id="OId" class="class4" onclick="getLeftComboItems('O');"><b>O</b></a>&nbsp; 
						<a href='#' id="PId" class="class4" onclick="getLeftComboItems('P');"><b>P</b></a>&nbsp; 
						<a href='#' id="QId" class="class4" onclick="getLeftComboItems('Q');"><b>Q</b></a>&nbsp; 
						<a href='#' id="RId" class="class4" onclick="getLeftComboItems('R');"><b>R</b></a>&nbsp;
						<a href='#' id="SId" class="class4" onclick="getLeftComboItems('S');"><b>S</b></a>&nbsp; 
						<a href='#' id="TId" class="class4" onclick="getLeftComboItems('T');"><b>T</b></a>&nbsp; 
						<a href='#' id="UId" class="class4" onclick="getLeftComboItems('U');"><b>U</b></a>&nbsp;
						<a href='#' id="VId" class="class4" onclick="getLeftComboItems('V');"><b>V</b></a>&nbsp; 
						<a href='#' id="WId" class="class4" onclick="getLeftComboItems('W');"><b>W</b></a>&nbsp; 
						<a href='#' id="XId" class="class4" onclick="getLeftComboItems('X');"><b>X</b></a>&nbsp; 
						<a href='#' id="YId" class="class4" onclick="getLeftComboItems('Y');"><b>Y</b></a>&nbsp;
						<a href='#' id="ZId" class="class4" onclick="getLeftComboItems('Z');"><b>Z</b></a>&nbsp; 
						<a href='#' id="@Id" class="class4" onclick="getLeftComboItems('@');"><b>All</b></a>&nbsp;  
					</div>
				</td>
			</tr>
			</table>
			<table class='NEWTABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px">	
			<tr>
				<td width="28%" colspan="2" class="LABEL">Search Item Name</td>
				<td class="CONTROL" colspan="2">
				<div id="drugNameDivId" style="display: none;">
					<select id="strDrugName" name="strDrugName" class="comboNormal" >
						<bean:write name="locationStockOnHandRpt" property="strDrugSearchCombo" filter="false" />
					</select>
				</div>							
				<input type="text" id="strSearchDrug" name="strSearchDrug" size="75%"/>
				<div id="DrugNameId" style="display: none;" ></div>	
				<div id="txtFromLeftMutltiSelectCombo" style="display:none; color:blue;font-weight:bold; text-align:left"></div>
				</td>			 
		   </tr>		   
		   </table>		
		   <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		   
			<tr>
	  			<td class="CONTROL" colspan="2">  			 
					<div id="leftItemDivId" align="right">
						<select id="strLeftItemFilterIds" name="strLeftItemFilterIds" size="6" multiple style="width: 280px" onChange='showSelection(this);' >
							<bean:write name="locationStockOnHandRpt" property="strLeftItemFilterList" filter="false"/>
						</select>
					</div>				
				</td>
				<td width="6%" class="CONTROL" colspan="2">			
					<center>
						<img src="../../hisglobal/images/forward3.gif" width="30" height="21" onclick ="LeftDrugTransfer();"></center>
					<center>
				<img src="../../hisglobal/images/forwardward.gif" width="30" height="21" style="display: none;"
					align="middle" onClick="shiftAllToRight('strLeftItemFilterIds','strRightItemFilterIds',1);"/></center>
				<br/>
				<center>				
				<img src="../../hisglobal/images/backward.gif" width="30" height="21" style="display: none;"
					onClick="shiftAllToLeft('strLeftItemFilterIds','strRightItemFilterIds',1);"></center>
				<center>
					<img src="../../hisglobal/images/back3.gif" width="30" height="21" 
						onclick="transferToLeft();"/>
				</center>
				</td>			
				<td colspan="2" class="CONTROL">
					<div id="rightItemDivId" align="left">
						<select id="strRightItemFilterIds" name="strRightItemFilterIds" size="6" multiple style="width: 280px">
							<bean:write name="locationStockOnHandRpt" property="strRightItemFilterList" filter="false"/>
						</select>
					</div>
				</td>		
			</tr>					
			</table>
		</div>
		<table class='TABLEWIDTH' align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			
			<td width="49%" colspan="2" class="LABEL">Batch No. Wise</td>
			<td width="51%" colspan="2" class="CONTROL"><html:checkbox property="strBatchWiseChk"   name="locationStockOnHandRpt"  onclick="vitalRpt(this,1);" title="Click Here To Supplier Wise Challan Detail(s)"  tabindex="1"></html:checkbox></td>
		  </tr>
		 
			<tr class="HEADER">
			   <td colspan="4" class='all-four-rounded-corners'>Only Active & Quarantine Drug Status Will be Counted</td>
		    </tr>
				
			</table>		            	
		
		
		
	
	<div id="htmlRpt">
	<div class="legends"><font size="2" color="red">*</font>Mandatory Field(s) </div>
	<div class="control_button">
	<table  class="TABLEWIDTH" align="center">
	<tr>
	<td align="center"><div>
				<a href="#" class="button" onClick="return validateVital();"><span class="generate">Generate</span></a>
				<a href="#" class="button" onClick="onClickClear();"><span class="clear">Clear</span></a>
				<a href="#" id="cancelBtn" class="button" onClick="cancelPage();"><span class="cancel">Cancel</span></a>				 
				</div></td>
	</tr>	  
	</table>
	</div></div>	
	
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strCurrentDate" value="${locationStockOnHandRpt.strCurrentDate}" />
	<input type="hidden" name="strCurrentStock" value="${locationStockOnHandRpt.strCurrentStock}" />
	<input type="hidden" name="strCircleName" value="${locationStockOnHandRpt.strCircleName}"/>
	<input type="hidden" name="strDistrictName" value="${locationStockOnHandRpt.strDistrictName}"/>
	<input type="hidden" name="strStoreName" value="${locationStockOnHandRpt.strStoreName}"/>
	<input type="hidden" name="strStoreTypeName" value="${locationStockOnHandRpt.strStoreTypeName}"/>
	<input type="hidden" name="strSubStoreName" value="${locationStockOnHandRpt.strSubStoreName}"/>
	<input type="hidden" name="strUserLevel" value="${locationStockOnHandRpt.strUserLevel}" />
	<input type="hidden" name="strIsDdwFlag" value="${locationStockOnHandRpt.strIsDdwFlag}" />
	<input type="hidden" name="strItemBrandId" value="${locationStockOnHandRpt.strItemBrandId}" />
	<input type="hidden" name="strItemId" value="0" />
	<input type="hidden" name="strStoreTypeNameList" />	
	<input type="hidden" name="strBatchWiseChkFlg" value="0" /> 
	<input type="hidden" name="strSubStoreStockChkFlg" value="0"/>
    <input type="hidden" name="strAlphbet" value="$"/>
	<input type="hidden" name="strZeroVitalFlg" value="0"/>
	<input type="hidden" name="strSearchIndex" value="$Id"/>
    <input type="hidden" name="strDrugClassName" value=""/>
    <input type="hidden" name="strProgrammeName" value=""/>
    <input type="hidden" name="strStockStatusName" value=""/>
    

</html:form>
</body>
</html>
