<%-- 
 Package Service Master ADD jsp
 author: Debashis Sardar
  Created on : 01-Sep-2011
  --%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Package/Estimation Tariff Mapping Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"type="text/css">
<style type="text/css">
.redMultiControl
{
	 
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-style: normal;
	font-weight: normal;
	color: #FF3300;
	background-color: #F1ECE2;
	height: 16px;
	text-align:center;

}
</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="Javascript" src="../../js/billing.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../billing/js/PackageServiceMst.js"></script>


</head>
<!--<body onLoad="checkIsValid();">-->

<body onLoad="getTariffDtls();">

<html:form name="packservBean"
	action="/masters/CNTpackservMst.cnt"
	type="billing.masters.controller.fb.PackageServiceMstFB">

		<div class="errMsg"><bean:write name="packservBean" property="errmsg"  filter="false"/></div>
	<div class="warningMsg"><bean:write name="packservBean" property="warningMsg" filter="false"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="packservBean" property="strNormalMsg" filter="false"/></div>
	
	
	<tag:tab tabLabel="Add Page" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
    
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td class="HEADER" colspan="2">Package/Estimation Tariff Mapping Master&gt;&gt;add</td>
		</tr>
		<tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center">
     
    
		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Tariff Type</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="packageId"><bean:write
				name="packservBean" property="strtariffType" filter="false" /></div>
			</td>
		</tr>
 

		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Package Name</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="packageId"><bean:write
				name="packservBean" property="strpackageName" filter="false" /></div>
			</td>
		</tr>

	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="TITLE">
			<td class="TITLE" colspan="2">
			<div id="plus" style="display: none"><img
				src="../../hisglobal/images/plus.gif" align="left"
				onClick="Showlayer1();"></div>
			<div id="minus" style="display: block"><img
				src="../../hisglobal/images/minus.gif" align="left"
				onClick="Showlayer2();"></div>
			Added Tariff Details</td>
		</tr>

	</table>
	<div id="tariffId"></div>



	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="TITLE"><td><div align="right" id="groupId">Group Name   
		 <select name="groupName" 
				  onchange="isGroupCombo(this);" class='comboNormal'>
				<bean:write name="packservBean" property="strpackgrpModuleCombo"
					filter="false" />
			</select> </div></td>
		</tr>
		</table>
		<table class="TABLEWIDTH" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="TITLE">
			<td align="left" width="95%">New Tariff Details</td>
			
			<td width="5%"><div align="center"><img align="middle" src="../../hisglobal/images/plus.gif"
				onClick="addMultirow();" ></div>
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="40%">
			<div align="CENTER"><font color="red">*</font>Tariff Name</div>
			</td>
			<td class="LABEL" width="18%">
			<div align="CENTER"><font color="red">*</font>Quantity</div>
			</td>
			<td class="LABEL" width="42%">
			<div align="CENTER"><font color="red">*</font>Unit</div>
			</td>

		</tr>
	</table>

	
	
			<div id='id1' style="display: none"></div>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">

		<tr style="display: none;">
			<td class="LABEL">
			<div align="right"><font color="red">*</font>Effective From</div>
			</td>
			<td class="LABEL">
			<div align="left">< dateTag:date name="streffectiveFrm"
				value="${packservBean.strCtDate}" /></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%">Remarks</td>
			<td class="CONTROL" width="50%"><textarea name="strremarks" rows="2"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>


	<div align="center"><!-- <img style="cursor:pointer;cursor:hand" 
		src="../../hisglobal/images/btn-sv.png" title="Save Record"
		onClick="submitData('SAVE');" /> 
		<img style="cursor:pointer;cursor:hand" title="Reset Content" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset(),document.forms[0].effectiveFrm.focus();" />
		<img style="cursor:pointer;cursor:hand" 
		src="../../hisglobal/images/btn-ccl.png" title="Cancel Process" onClick="cancel('LIST');">
		
		-->
		<br><a href="#" class="button" id="" onclick="submitData('SAVE');"><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset(),document.forms[0].counterName.focus();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="isGrpFlag" value="0" />
	<input type="hidden" name="strNormalMsg" value="${packservBean.strNormalMsg}" />
	<cmbPers:cmbPers/>
</html:form>

<jsp:include page="addmultirow_PackageMst_bill.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
