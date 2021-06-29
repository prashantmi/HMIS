<!--
 /******************************************add_roomConfigMst.jsp***************************************\
 /*******************************************Start of Block***************************************\

 -->

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset="utf-8" />
<title>Room Config Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/popup.js"></script>
<script type="text/javascript">
function groupCombo(mode)
{
  if(mode=="UNITVAL")
  {
	   var mode="UNITVAL";
	   var url="CNTRoomConfigMst.cnt?hmode="+mode+"&modName="+document.forms[0].strBuildingId.value;
	   ajaxFunction(url,"1");
  }
}
function getAjaxResponse(res,mode)
{
  if(mode=="1") 
  {
    var objVal = document.getElementById("blockId");
   	// objVal.innerHTML = "<select name = 'strBlockId' class='comboNormal' onChange=\"groupCombo1('UNITVAL1');\ ">" + res + "</select>";
   	objVal.innerHTML = "<select name='strBlockId'  class='comboNormal' onChange=\"groupCombo1('UNITVAL1');\">" + res + "</select>";
    var objVal1 =    document.getElementById("floorId");  
     objVal1.innerHTML = "<select name = 'strFloorId' class='comboNormal'><option value='0'>Select Value</option></select>";
     var objVal2 =  document.getElementById("roomId");   
     objVal2.innerHTML = "<select name = 'strRoomId' class='comboNormal'><option value='0'>Select Value</option></select>";
  }
  else if(mode=="2")  
  {
     var objVal = document.getElementById("floorId");
     objVal.innerHTML = "<select name='strFloorId'  class='comboNormal' onChange=\"groupCombo2('UNITVAL2');\">" + res + "</select>";
     var objVal2 =  document.getElementById("roomId");   
     objVal2.innerHTML = "<select name = 'strRoomId' class='comboNormal'><option value='0'>Select Value</option></select>";
  } 
  else if(mode=="3") 
  {
     var objVal = document.getElementById("roomId");
     objVal.innerHTML = "<select name='strRoomId'  class='comboNormal'>" + res + "</select>";
  }   
}
function groupCombo1(mode)
{
  if(mode=="UNITVAL1")
  {
    var mode="UNITVAL1";
    var url="CNTRoomConfigMst.cnt?hmode="+mode+"&modName="+document.forms[0].strBuildingId.value+"&modName1="+document.forms[0].strBlockId.value;
    ajaxFunction(url,"2");
  }
}
function groupCombo2(mode) 
{
  if(mode=="UNITVAL2")
  {
    var mode="UNITVAL2";
    var url="CNTRoomConfigMst.cnt?hmode="+mode+"&modName="+document.forms[0].strFloorId.value+"&strBuildingId="+document.forms[0].strBuildingId.value+"&strBlockId="+document.forms[0].strBlockId.value;
	ajaxFunction(url,"3");
  }
}
function groupCombo3(mode) 
{
  if(mode=="UNITVAL3")
  {
    var mode="UNITVAL3";
    var url="CNTRoomConfigMst.cnt?hmode="+mode+"&modName="+document.forms[0].strRoomId.value;
    ajaxFunction(url,"4");
  }
}
function validate1()
{	
		var hisValidator = new HISValidator("roomconfigBean"); 
		hisValidator.addValidation("strRoomDescription","req","Room Description is a Mandotary Field"); 
     	//hisValidator.addValidation("strBuildingId","dontselect=0","Please Select A Value From Building Name Combo");
     	hisValidator.addValidation("strRoomTypeId","dontselect=0","Please Select A Value From Room Type Combo");
     	hisValidator.addValidation("strNoOfBeds","req","No of Beds is a Mandotary Field");
     	//hisValidator.addValidation("strBlockId","dontselect=0","Please Select A Value From Block Name Combo");
     	//hisValidator.addValidation("strFloorId","dontselect=0","Please Select A Value From Floor Name Combo");
     	//hisValidator.addValidation("strRoomId","dontselect=0","Please Select A Value From Room Number Combo");
	   	hisValidator.addValidation("strEffectiveFrom", "dtgtet=${roomconfigBean.strCtDate}","Effective From Date should be Greater than or Equal to Current Date");
      	hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks Should have less than or equal to 50 Characters" );		
		var retVal = hisValidator.validate(); 
		if(retVal)
		{
				document.forms[0].hmode.value = "SAVE";
				document.forms[0].submit();
		}
		else
		{
		 	return false;
		}
}	
function PropertyPopUp(parent)
{
	display_popup_menu(parent,'divProperties','250','100');  
}
// function called on cross sign on room properties pop-up
function closeProperties()
{
	document.getElementById("divProperties").style.display="none";
}
// function called on ok button on belonging pop-up
function OkProperty()
{
	var PropertyId = document.getElementsByName("strPropertyId");	
	var MultiRowMode = document.getElementsByName("strMultiRowMode");
	var MultiRowlength = PropertyId.length-1;
	var flag =0;
	
	for(i=0;i<MultiRowlength;i++)
	{
		if(PropertyId[i].value=="0")
			flag=1;
		else
			MultiRowMode[i].value=1;
		for(j=i+1;j<MultiRowlength;j++)
		{
			if(PropertyId[i].value == PropertyId[j].value)
			{
				flag=2;
				break;
			}
		}
	}
	if(flag==1)
			alert("Please select a property");
	else if(flag==2)
		alert("Property Name cannot be same in two rows");
	else
	document.getElementById("divProperties").style.display="none";
	document.getElementById("savedRowId").value=document.getElementsByName("rowIndex1")[0].value;
}

// function called on cancel button on properties pop-up
function delRow()
{
	var delRow=parseInt(document.getElementById("savedRowId").value)+1;
	if(parseInt(document.getElementsByName("rowIndex1")[0].value)!=1)
	{
		for(var i=delRow;i<=parseInt(document.getElementsByName("rowIndex1")[0].value);i++)
		{
			document.getElementById("id1").removeChild(document.getElementById("id1-"+i));
			deleteRow("1-"+i,'1','0');
		}
		document.getElementsByName("rowIndex1")[0].value=document.getElementById("savedRowId").value;
	}
	else
	{
	for(var i=delRow;i<=parseInt(document.getElementsByName("rowIndex1")[0].value);i++)
	{
			document.getElementById("id1").removeChild(document.getElementById("id1-"+i));
			deleteRow("1-"+i,'1','0'); 
	}
		document.getElementsByName("rowIndex1")[0].value=document.getElementById("savedRowId").value;
	}	
	if(document.getElementsByName('rowIndex1')[0].value=='0')
	addRows(new Array('strPropertyId'),new Array('s'),'1','1','R');	
	document.getElementById("divProperties").style.display="none";	
} 

</script>
</head>
<body onLoad="document.forms[0].strRoomDescription.focus()">
<html:form name="roomconfigBean" action="/masters/CNTRoomConfigMst"
	type="ipd.masters.vo.RoomConfigMstVO">
	<div class="errMsg"><bean:write name="roomconfigBean"
		property="strerrorMsg" /></div>
	<div id=normalMsg class="normalMsg"><bean:write
		name="roomconfigBean" property="strMsg" /></div>
	<div class="warningMsg"><bean:write name="roomconfigBean"
		property="strWarning" /></div>
	<tag:tab tabLabel="Add Room" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="2">Room Config Master&gt;&gt; Add</td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right"><font color="red">*</font>Room Description</div>
			</td>
			<td class="CONTROL" width="45%"><html:text name="roomconfigBean"
				onkeyup="lTrim(this);" onblur="Trim(this);"
				property="strRoomDescription" value="" maxlength="25"
				onkeypress="return validateData(event,9);" /> <img
				src="../../hisglobal/images/property.png"
				onClick="PropertyPopUp(this);" title="Search Properties"
				style="cursor: pointer; position: absolute;" /></td>
		</tr>
	</table>
	<div id='divProperties' style='display: none' class='popup'
		align='center'>
	<table border="0" width="300" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td>Room Properties</td>
			<td><img src="../../hisglobal/images/FrStopAutoHide.png"
				onClick='closeProperties();' align="right" style="cursor: pointer;" />
			</td>
		</tr>
		<tr>
			<td width="98%" class="multiLabel">Property Name</td>
			<td width="2%" class="multiLabel"><img
				src="../../hisglobal/images/plus.gif"
				onClick="addRows(new Array('strPropertyId'),new Array('s'),'1','1','R');"
				style="cursor: pointer;"></td>
		</tr>
	</table>
	<div id="id1"></div>
	<table width="300" cellspacing="1px">
		<tr>
			<td colspan='2' align="center"><img
				src='../../hisglobal/images/btn-ok.png'
				style='cursor: hand; cursor: pointer;' title='save data'
				onClick='OkProperty();' /> <img
				src='../../hisglobal/images/btn-ccl.png'
				style='cursor: hand; cursor: pointer;' title='clear data'
				onClick="delRow();" /></td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL" width="45%">
			<div align="right"><font color="red">*</font>Room Type</div>
			</td>
			<td class="CONTROL" width="45%"><select name="strRoomTypeId"
				id="strRoomTypeId" class='comboNormal'>
				<bean:write name="roomconfigBean" property="roomTypeComboAdd"
					filter="false" />
			</select></td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right"><font color="red">*</font>Room Status</div>
			</td>
			<td class="CONTROL" width="45%"><html:select
				name="roomconfigBean" property="strRoomStatus">
				<html:option value="1">OK</html:option>
				<html:option value="0">Not OK</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right"><font color="red">*</font>No. of Beds</div>
			</td>
			<td class="CONTROL" width="45%"><html:text name="roomconfigBean"
				onkeyup="lTrim(this);" onblur="Trim(this);" property="strNoOfBeds"
				value="" maxlength="3" size="4"
				onkeypress="return validateData(event,5);" /></td>
		</tr>
		<tr class="HEADER">
			<td width="90%" colspan=2>Mapped with Estate</td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Building Name</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="buildingId"><select name="strBuildingId"
				class='comboNormal' onChange="groupCombo('UNITVAL');">
				<bean:write name="roomconfigBean" property="strBuildingDetailAdd"
					filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Block Name</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="blockId"><select name="strBlockId"
				class='comboNormal' id="strBlockId">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Floor Name</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="floorId"><select name="strFloorId"
				class='comboNormal' id="strFloorId">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="45%">
			<div align="right">Room Number</div>
			</td>
			<td class="CONTROL" width="45%">
			<div align="left" id="roomId"><select name="strRoomId"
				class='comboNormal' id="strRoomId">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				id="strEffectiveFrom" value="${roomconfigBean.strEffectiveFrom}" /></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea name="strRemarks"
				onkeyup="textAreaMaxLength(this,6),lTrim(this);"
				onblur="Trim(this);" cols="20" rows="2" id="strRemarks"></textarea>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font>Mandatory
			Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<!-- <td align="right"><img src="../../hisglobal/images/btn-sv.png"
				onClick=" return validate1();" style="cursor: pointer;" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
				onClick="cancel('LIST');" style="cursor: pointer;" /></td> -->
				
				<td align="right">
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
		</td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<cmbPers:cmbPers />
</html:form>
<jsp:include page="multirow_roomConfig_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>