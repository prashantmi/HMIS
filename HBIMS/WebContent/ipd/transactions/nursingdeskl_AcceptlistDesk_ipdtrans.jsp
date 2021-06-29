<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Patient Acceptance List Page</title>
<link href="../../ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../ipd/js/nursingdesk_trans.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../ipd/js/nursingDesk.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->



<script type='text/javascript'>
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
//,document.forms[0].nNoOfRow.focus()  on body onload(deleted due to error in IE.)


function changeColorForBedCombo()
{

	var c=document.getElementById("strbed"+document.getElementsByName("bedvalue")[0].value);
	for(var i=0;i<=c.length;i++)
	{
		if(i !=0)
		{
			if(c.options[i].value.split("^")[3] == 1) //Sharable But Vacant Bed
			c.options[i].style="background-color: #E0EBEB";
			else
			c.options[i].style="background-color: #F1F6F6"; //Non Sharable and Vacant Bed

			if( c.options[i].value.split("^")[3] == 1  )
			{
  				if( c.options[i].value.split("^")[4] > 0 )  //Sharable and Occupied Bed
  				{
					c.options[i].style="background-color: #D8D8D8 ";
  				}
			}
		}
		
	}

}
</script>
<!-- <script type='text/javascript'>
$(document).ready(function(){
	//var url = "modalbox.php";
	var mode="BEDSTATUS";
	var temp=document.forms[0].strWard.value+"^"+document.forms[0].strRoom.value+"^0^"+document.forms[0].strUnit.value+"^"+document.forms[0].strchk.value;
	var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+temp;
	jQuery('#modellink').click(function(e) {
	    $('.modal-container').load(createFHashAjaxQuery(url),function(result){
			$('#myModal').modal({show:true});
		});
	});
}); -->
</script>
</head>

<body onLoad="changeColorForBedCombo();addRows(new Array('strArticleName','strQuantity','strBelongingRemark'),new Array('t','t','t'),'1','1','I');">
<html:form action="/transactions/NursingDeskTransCNT" method="post">
	<div class="normalMsg" id="normalMsg"><bean:write name="nursingDeskBean" property="strNormalMsg" /></div>
	<div class="errMsg" id="errMsg"><bean:write name="nursingDeskBean" property="strErrorMsg" /></div>
	<tag:tab tabLabel="Patient Acceptance " selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align='center' cellspacing='1px'>
		<tr class="HEADER">
			<td colspan="6">&nbsp;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align='center' cellspacing='1px'>
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL"><div style="display: none;"><!--  <select disabled
				class="comboNormal" name='strDepartment'
				onChange="fununit('UNIT'), setWardRoom();">
				<bean:write name="nursingDeskBean" property="strdeptproperty" filter="false" />	
			</select>--></div><bean:write name="nursingDeskBean" property="departmentName" filter="false" /></td>
			<td width="25%" class="LABEL">Ward</td>
			<td width="25%" class="CONTROL">
			<div id="wardId" style="display: none;"><!--<select disabled class="comboNormal" name='strWard'>
				<bean:write name="nursingDeskBean" property="strwardproperty" filter="false" />				
			</select>--></div><bean:write name="nursingDeskBean" property="wardName" filter="false" />
			</td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL" colspan="3">
			<div id="unitId"><select disabled class="comboNormal" name='strUnit1'>
				<bean:write name="nursingDeskBean" property="strunitproperty" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL">Room</td>
			<td width="25%" class="CONTROL" colspan="3">
			<div id="roomId"><select disabled class="comboNormal" name='strRoom'>
				<bean:write name="nursingDeskBean" property="strroomproperty" filter="false" />
			</select></div>
			</td>
		</tr>
	</table>
	<div id='divBelonging' style='display: none' class='popup' align='center'>
	<table width='500' border='0' cellspacing ='1'>
		<tr class="HEADER">
			<td align="left">Patient Belonging/Issued Items Details</td>
			<td align='right'><img
				src="../../hisglobal/images/FrStopAutoHide.png"
				style='cursor: pointer;' onClick='closebelonging();' /></td>
		</tr>
	</table>
	<table width='500' border='0' cellspacing ='1'>
		<tr class="TITLE">
			<td colspan="8">Patient Belonging Details</td>
		</tr>
	</table>
	<table width='500' border='0' cellspacing ='1' id="divBelongingDtl" >
			<tr>
			<td width="35%" class='multiLabel'><font color="red">*</font>Item Name</td>
			<td width="25%" class='multiLabel'><font color="red">*</font>Qty.</td>
			<td width="35%" class='multiLabel' nowrap="nowrap">
			<font color="red" id="remarksIdMandatory">*</font>Remarks</td>
			<td width="5%" class='multiLabel'><img style="cursor: pointer;"
				src="../../hisglobal/images/plus.gif"
				onclick="addBelongingDtl('divBelongingDtl')"></td>
		</tr>
	</table>
	<div id="divIssueDtl">
	<table width='500' border='0' cellspacing ='1'>
		<tr class="TITLE">
			<td colspan="8">Patient Issued Item Details</td>
		</tr>
	</table>
	<table width='500' border='0' cellspacing ='1' id="divIssuedItem" >
			<tr>
			<td width="35%" class='multiLabel'><font color="red">*</font>Item Name</td>
			<td width="25%" class='multiLabel'><font color="red">*</font>Qty.</td>
			<td width="35%" class='multiLabel' nowrap="nowrap">
			<font color="red" id="remarksIssueIdMandatory">*</font>Remarks</td>
			<td width="5%" class='multiLabel'><img style="cursor: pointer;"
				src="../../hisglobal/images/plus.gif"
				onclick="addIssuedItem('divIssuedItem')"></td>
		</tr>
	</table>
	</div>
	</div>
	<div id="admitdetail" style='display: block'><bean:write
		name="nursingDeskBean" property="strAdmitDetailProperty"
		filter='false' /></div>

	<div id='divChecklist' align='center'><bean:write
		name="nursingDeskBean" property="strChecklistProperty" filter='false' />
	</div>

	<table border="0" class="TABLEWIDTH" align="center" cellspacing="0px"
		cellpadding="0px">
		<tr class="FOOTER">
			<td width='3%'>
			<div id="plusHelpId" align="center"><img
				src="../../hisglobal/images/plus.gif" name="plusHelp" align="middle"
				style='cursor: pointer;' onclick="showHelpDetails();" /></div>
			<div id="minusHelpId" style="display: none" align="center"><img
				src="../../hisglobal/images/minus.gif" name="minusHelp"
				style='cursor: pointer;' onclick="hideHelpDetails();"></div>
			</td>
			<td>
			<div align="left">Help</div>
			</td>
			<td colspan="6">&nbsp;<font size="2" color="red">*</font>
			Mandatory Fields</td>
		</tr>
	</table>
	<div id="HelpId" style="display: none">
	<table border="0" class="TABLEWIDTH" align="center" bgcolor="#000000"
		cellspacing="1px" cellpadding="1px">
		<tr>
			<td class="CONTROL" style="background-color: #FFFFFF;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button style="background-color: #CFE7E2; height: 20px; width: 20px;"
				disabled></button>
			&nbsp;Patient transfered from another ward</td>
		</tr>
		<tr>
			<td class="CONTROL" style="background-color: #FFFFFF;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button style="background-color: #7AB6C4; height: 20px; width: 20px;"
				disabled></button>
			&nbsp;Outside Patient</td>
		</tr>
		<tr>
			<td class="CONTROL" style="background-color: #FFFFFF;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button style="background-color: #F1ECE2; height: 20px; width: 20px;"
				disabled></button>
			&nbsp;New Admission</td>
		</tr>
	</table>
	</div>
	<div id='id1'></div>
	<div id="rowAdded1" style="display: none"></div>
	<div id="saveid">
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
		   
		  <logic:equal value="1" property="strCheckFlagType" name="nursingDeskBean" >
			<td align="center">
			<!-- <img
				src="../../hisglobal/images/btn-sv.png" style='cursor: pointer;'
				title='Save Record' onClick="validate2('SAVE');" /> <img
				src="../../hisglobal/images/btn-ccl.png" style='cursor: pointer;'
				title='Return to main page' onclick="closeTabOnDesk();" /> -->
				
				<a href="#" class="button"	onClick="validate2('SAVE');"><span class="Save">Save</span></a> 
				<a href="#" class="button" onclick="closeTabOnDesk();"><span class="cancel">Cancel</span></a>
				</td>
		  </logic:equal>
		  <logic:equal value="0" property="strCheckFlagType" name="nursingDeskBean">
		    <td align="center">
		    <!-- <img
				src="../../hisglobal/images/btn-sv.png" style='cursor: pointer;'
				title='Save Record' onClick="validate1('SAVE');" /> <img
				src="../../hisglobal/images/btn-ccl.png" style='cursor: pointer;'
				title='Return to main page' onclick="cancelFunc();" />
				 -->
				<a href="#" class="button"	onClick="validate1('SAVE');"><span class="save">Save</span></a> 
				<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</td>
		  </logic:equal>
		</tr>
	</table>
   </div>

<%-- <div class="container ">--%>
<div class="modal-container" style="display: none;">
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">Bed Dashboard Status</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body" id="modalSpaceId">
	        
	      </div>
	    </div>
	
	  </div>
	</div>
</div>


	<input type="hidden" name="hmode">
	<input type='hidden' name='hiddencrno'>
	<input type='hidden' name='hiddenadmno'>
	<input type='hidden' name='hiddenbed'>
	<input type='hidden' name='hiddennursecheck'>
	<input type='hidden' name='hiddenchkremark'>
	<input type='hidden' name='hiddenflag'>
	<input type='hidden' name='hiddenbelonging'>
	<input type='hidden' name='strhiddendepartment'>
	<input type='hidden' name='strhiddendunit'>
	<input type='hidden' name='strhiddenward'>
	<input type='hidden' name='strhiddenRoom' value="${nursingDeskBean.strRoom}">
	<input type='hidden' name='stroldradio'>
	<input type='hidden' name='strhtransinflag'>
	<input type='hidden' name='strhmoveno' value="">
	<input type='hidden' name='delRowsNo' id="delRowsNo" value="0">
	<input type="hidden" name="strNursCheckListMandatoryFlag" value="${nursingDeskBean.strNursCheckListMandatoryFlag}">
	<input type="hidden" name="strRemarksMandatoryFlag"	value="${nursingDeskBean.strRemarksMandatoryFlag}" />
	<input type=hidden name=strIssuedItemValues value='${nursingDeskBean.strIssuedItemValues }'>
	<input type=hidden name=strBelongingItemValues value='${nursingDeskBean.strBelongingItemValues }'>
	<input type=hidden name=strIssuedItemRequired value='${nursingDeskBean.strIssuedItemRequired }'>
	<input type=hidden name=strBelongingRequired value='${nursingDeskBean.strBelongingRequired }'>
	<html:hidden name="nursingDeskBean" property="strIsCancelMode"></html:hidden>
	<input type="hidden" name="rowIndex1" value="0">
	<input type="hidden" name="rowLength1" value="0">
	<input type="hidden" name="strPreviousOccupiedbed" value='${nursingDeskBean.strPreviousOccupiedbed }'>
	<input type="hidden" name="strDepartment" value='${nursingDeskBean.strDepartment }'>
	<input type="hidden" name="strWard" value='${nursingDeskBean.strWard }'>
	<input type="hidden" name="index">
	<input type="hidden" name="strIsSharableFlag" value='${nursingDeskBean.strIsSharableFlag }'>
	<input type="hidden" name="strCheckFlagType" value='${nursingDeskBean.strCheckFlagType }'>
	<input type="hidden" name="strIcuWardType" value="${nursingDeskBean.strIcuWardType}">
	<input type="hidden" name="strPvtWardType" value="${nursingDeskBean.strPvtWardType}">
	<input type="hidden" name="strWadType" value='${nursingDeskBean.strWadType }'>
	<input type="hidden" name="billcount" value="${nursingDeskBean.billcount}">
	<cmbPers:cmbPers />
</html:form>
<script>
	if(document.getElementsByName("strIssuedItemRequired")[0].value==0)
		document.getElementById("divIssueDtl").style.display="none"
	if(document.forms[0].strRemarksMandatoryFlag.value==0){
		document.getElementById("remarksIssueIdMandatory").style.display="none"
		document.getElementById("remarksIdMandatory").style.display="none"
	}
	if(document.getElementsByName("strBelongingRequired")[0].value==0)
		document.getElementById("divBelongingDtl").style.display="none"
	if(document.forms[0].strRemarksMandatoryFlag.value==0){
		document.getElementById("remarksIssueIdMandatory").style.display="none"
		document.getElementById("remarksIdMandatory").style.display="none"
	}
</script>
</body>
</html>
