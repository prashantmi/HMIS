<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Bed Status-Patient Admission</title>

<link href="../css/transaction.css" 	rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<style type='text/css'>

.bedbutton
{
	border          : 3px #B5B5B5 outset;
	padding       : 25 20px;
	color            : black;
	cursor         : pointer ;
	cursor         : hand;
	text-align    : center;
	text-decoration : none;
	font            : normal 13px Verdana;
}

.bedImages
{
	height: 50px;
	width: 80px;
	cursor: pointer;
	vertical-align: middle;
}
.bedImagesFocus
{
	height: 50px;
	width: 50px;
	cursor: pointer;
	vertical-align: middle;
}
.bedImagesSmall
{
	height: 20px;
	width: 20px;
	cursor: pointer;
	vertical-align: middle;
}
.bedbutton:visited, .bedbutton:hover, .bedbutton:active
{
	color: grey;
}
</style>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/toolTip.js"></script>

<script type="text/javascript">
	function searchBed()
	{
		var hmode = "BEDDETAILS"; 
		if(document.forms[0].strDeptUnitCode.value=="0")
		{
			hmode = "BEDDETAILSPATADMISSION";
		}
		// var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&roomCode="+document.forms[0].strRoom.value+"&bedTypeCode="+document.forms[0].strBedType.value+"&deptUnitCode="+document.forms[0].strDeptUnitCode.value;
		var WrdRoomBedtypUnt_code=document.forms[0].strWard.value+"^"+document.forms[0].strRoom.value+"^"+document.forms[0].strBedType.value+"^"+document.forms[0].strDeptUnitCode.value+"^"+"^"+document.forms[0].strDeptCode.value;
		var url="IpdCNT.cnt?hmode="+hmode+"&modPopUp="+WrdRoomBedtypUnt_code;
		var hisValidator = new HISValidator("paientAdmissionTransBean");
			hisValidator.addValidation("strWard","dontselect=0","Please Select Ward");
		//hisValidator.addValidation("strRoom","dontselect=0","Please select a value Room No");
		//hisValidator.addValidation("strBedType","dontselect=0","Please select a value Bed Type");
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal)
		{
			ajaxFunction(url,"1");
			return true;
		}
		else
		return false;
	}
	
	function getAjaxResponse(res,mode)
	{
		var err = document.getElementById("errMsgID");
		var temp = res.split("####");
		if(temp[0] == "ERROR")
		{
			err.innerHTML = temp[1];
			return;
		} 
		if(mode==1)
		{
				var temp=new Array();
				temp=res.split('^');
				var objEle=document.getElementById("bedStatusDiv");
				document.forms[0].strVacantBed.value=temp[1];
				objEle.innerHTML=temp[0];
				//alert(document.getElementsByName("strVacantBed")[0].value);
				
				if(document.forms[0].strVacantBed.value == "0")
				{
					document.forms[0].strFlag.value="0";
					//window.opener.document.forms[0].strFlag.value=document.forms[0].strFlag.value;
					//alert("There are no vacant beds in this room.Please select another room or ward");
				}
				resize_popUp();
				getBedProperties();
			}
			if(mode==2)//Ward
			{
				var objEle=document.getElementById("wardId");
				objEle.innerHTML='<select name="strWard" class="comboNormal" onChange="getRoomNo()" disabled>'+res+'</select>';
				//getRoomNo();
			}
			if(mode==3)//Room
			{
				var temp=res.split('^');
				var objEle=document.getElementById("roomId");
				objEle.innerHTML='<select name="strRoom" class="comboNormal">'+temp[0]+'</select>';
				document.forms[0].strAdmissionChargeValue.value=temp[1];
			}
			if(mode==101)
			{
				document.getElementById("allData").innerHTML=res;
			}
			if(mode==4)
			{
				window.opener.document.forms[0].strAdmissionChargeValue.readOnly=false;
				window.opener.document.forms[0].strAdmissionChargeValue.value=res;
				window.opener.document.forms[0].strAdmissionChargeValue.readOnly=true;
			}
	}
	function getBedname(obj)
	{
		var name=obj.value;
		window.opener.document.forms[0].strBedCode.value=document.getElementById(name).value;
		
		window.opener.document.forms[0].strRoomCode.value=document.forms[0].strRoom.value;
		window.opener.document.forms[0].strWardCode.value=document.forms[0].strWard.value;
		document.forms[0].strRoomBedName.value=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text+'/'+obj.value;
		alert('You have successfully selected '+obj.value+' bed');
		//window.opener.document.getElementById("roomBedId").innerHTML=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text+'/'+obj.value;
	}
	function getChargeVal()
	{
				
			var hmode = "CHARGEVALUE"; 
			var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+'&treatmentCategCode='+document.forms[0].strTreatmentCategoryCode.value;
			ajaxFunction(url,"4");
	}
	function ok()
	{
			getChargeVal();
			if(document.forms[0].strMsApprovalStatus.value=='0' && document.forms[0].strMsApprovalFlag.value=='6')
			{
				document.forms[0].strwardType.disabled='false';
				document.forms[0].strWard.disabled='false';
				document.forms[0].strwardType.disabled='false';
				document.forms[0].strRoomType.disabled='false';
				document.forms[0].strRoom.disabled='false';
				document.forms[0].strBedType.disabled='false';
				window.close();
			}
			else
			{
				var hisValidator = new HISValidator("paientAdmissionTransBean");
				//	hisValidator.addValidation("strwardType","dontselect=0","Please Select Ward Type");
				hisValidator.addValidation("strWard","dontselect=0","Please Select Ward");
				//hisValidator.addValidation("strRoom","dontselect=0","Please select a value Room No");
				//hisValidator.addValidation("strBedType","dontselect=0","Please select a value Bed Type");
				var retVal = hisValidator.validate(); 
				hisValidator.clearAllValidations();
				if(retVal)
				{
				/*	window.opener.document.forms[0].strFlag.value=document.forms[0].strFlag.value;
					//window.opener.document.forms[0].strMsApprovalFlag.value="0";
					//window.opener.document.forms[0].strMsApprovalStatus.value="";
					if(document.forms[0].strRoom.value!=0)
					window.opener.document.forms[0].strRoomCode.value=document.forms[0].strRoom.value;
					else
						window.opener.document.forms[0].strRoomCode.value="";
					if(document.forms[0].strRoom.value!=0)
						window.opener.document.getElementById("roomBedId").innerHTML=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text;
					else	// Should be empty if not selected
						window.opener.document.getElementById("roomBedId").innerHTML="";
					window.opener.document.getElementById("wardNameId").innerHTML=document.forms[0].strWard[document.forms[0].strWard.selectedIndex].text;
					*/
					window.opener.document.forms[0].strWardCode.value=document.forms[0].strWard.value;
					window.opener.document.forms[0].strRoomTypeCode.value=document.forms[0].strRoomType.value;
					window.opener.document.forms[0].strBedTypeCode.value=document.forms[0].strBedType.value;
					window.opener.document.forms[0].strWardTypeCode.value=document.forms[0].strwardType.value;
					
					window.opener.document.forms[0].strAdmissionChargeValue.readOnly=false;
					window.opener.document.forms[0].strAdmissionChargeValue.value=document.forms[0].strAdmissionChargeValue.value;
					window.opener.document.forms[0].strAdmissionChargeValue.readOnly=true;
					//Addition By Amit Kumar Ateria
					if(document.forms[0].strRoom.value!=0 && window.opener.document.forms[0].strHiddenRoom.value=='1')
					window.opener.document.forms[0].strRoomCode.value=document.forms[0].strRoom.value;					
					
					//window.opener.document.forms[0].strWardName.value=document.forms[0].strWard[document.forms[0].strWard.selectedIndex].text;
					window.opener.document.forms[0].strRoom.value=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text;					
				 	window.close();}
			}
	}
	function setWard()
	{
		var hmode = "GETWARD"; 
		var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value;
		ajaxFunction(url,"2");
	}
	function getWard()
	{
			document.forms[0].strRoom.value="0";
			document.forms[0].strRoomType.value="0";
			document.forms[0].strBedType.value="0";
			var hmode = "GETWARD"; 
			var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+window.opener.document.forms[0].strDeptCode.value+'&treatmentCategCode='+window.opener.document.getElementsByName("strTreatmentCategoryCode")[0].value+'&ageCode='+window.opener.document.forms[0].strAgeUnit.value+'&sexCode='+window.opener.document.forms[0].strSexCode.value+'&age='+window.opener.document.forms[0].strAge.value+'&strCrNo='+window.opener.document.forms[0].strCrNo.value+'&deptUnitCode='+window.opener.document.forms[0].strDeptUnitCode.value;
			ajaxFunction(url,"2");
			//getRoomNo();
	}
	function getRoomNo()
	{
				var hmode = "GETROOM"; 
				var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&roomTypeCode="+document.forms[0].strRoomType.value+"&strWardCode="+
				document.forms[0].strWard.value+"&treatmentCategCode="+document.forms[0].strTreatmentCategoryCode.value+'&treatmentCategCode='+window.opener.document.forms[0].strTreatmentCategoryCode.value+'&ageCode='+window.opener.document.forms[0].strAgeUnit.value+'&sexCode='+window.opener.document.forms[0].strSexCode.value+'&age='+window.opener.document.forms[0].strAge.value+'&strCrNo='+window.opener.document.forms[0].strCrNo.value+'&deptUnitCode='+window.opener.document.forms[0].strDeptUnitCode.value;
				//var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+window.opener.document.forms[0].strDeptCode.value+'&treatmentCategCode='+window.opener.document.forms[0].strTreatmentCategoryCode.value+'&ageCode='+window.opener.document.forms[0].strAgeUnit.value+'&sexCode='+window.opener.document.forms[0].strSexCode.value+'&age='+window.opener.document.forms[0].strAge.value+'&crNo='+window.opener.document.forms[0].strCrNo.value+'&deptUnitCode='+window.opener.document.forms[0].strDeptUnitCode.value;
				ajaxFunction(url,"3");
	}
	/**********added*******/
function resize_popUp()
{
	var newHeight=parseInt(document.forms[0].strWin_Resize.value);
 	window.resizeTo('600',newHeight);
}
function hideDetailsH(obj)
{
 document.getElementById("minusonLineIdH").style.display="none";
 document.getElementById("plusonLineIdH").style.display="block";
 document.getElementById("bedStatHelpDtl").style.display="none";
}
function showDetailsH()
{
 document.getElementById("plusonLineIdH").style.display="none";
 document.getElementById("minusonLineIdH").style.display="block";
 document.getElementById("bedStatHelpDtl").style.display="block";
}
function getMsApproval()
{
	if(document.forms[0].strMsApprovalStatus.value=='0' && document.forms[0].strMsApprovalFlag.value=='6')
	{
		document.forms[0].strwardType.disabled='true';
		document.forms[0].strWard.disabled='true';
		document.forms[0].strwardType.disabled='true';
		document.forms[0].strRoomType.disabled='true';
		document.forms[0].strRoom.disabled='true';
		document.forms[0].strBedType.disabled='true';
		document.getElementById("searchId").style.display="none";
	}
}
</script>
</head>
<body onload="getMsApproval();">
<html:form action="/transactions/PatientAdmissionTransCNT.cnt" method="post">
<div class="popup" id="menu1" style="display:none;"></div>
<div class="errMsg" id="errMsgID"><bean:write name="paientAdmissionTransBean" property="strMsgString" /></div>
<div class="normalMsg" id="normalMsg"></div>
<table align="center" border="1"  bordercolor="black" cellpadding="0" cellspacing="0" width="100%">
<tr> 
<td width="100%">
<table align="center" border="0" cellspacing="1px" width="100%">
  <tr class="HEADER"> 
     <td colspan="4">Patient Admission &gt;&gt;Bed Details </td>
  </tr>
   <tr> 
    <td colspan="2" class="LABEL">Department</td>
    <td colspan="2" class="CONTROL">
    	<div align="left">
    		<b>
    			<font color="blue"><bean:write property="strDepartmentName" name="paientAdmissionTransBean"/></font>
    		</b>
    	</div>
    </td>   
 </tr>
 <tr>
  <td width="25%" class="LABEL">Ward Type</td>
  <td width="25%" class="multiControl">
  <select name="strwardType" onChange="getWard();" class="comboNormal" disabled>
  	<bean:write name="paientAdmissionTransBean" property="strwardType" filter="false"/>
  </select></td>
  <td width="25%" class="LABEL"><font color="red">*</font>Ward</td>
    <td width="25%" class="multiControl">
    	<div id="wardId">
    	<select name="strWard" onChange="getRoomNo();" class="comboNormal" disabled>
    		<bean:write name="paientAdmissionTransBean" property="strWard" filter="false"/>
    	</select>
    	</div> 
    </td>
 </tr>
  <tr> 
    <td width="25%" class="LABEL">Room Type</td>
    <td width="25%" class="multiControl"><select name="strRoomType" onChange="getRoomNo();" class="comboNormal">
   		 <bean:write name="paientAdmissionTransBean" property="strRoomType" filter="false"/>
    </select> </td>   
    <td width="25%" class="LABEL"><font color="red" style="display: none;">*</font>Room</td>
    <td width="25%" class="multiControl">
    	<div id="roomId">
    	<select name="strRoom" onChange="" class="comboNormal">
    		 <bean:write name="paientAdmissionTransBean" property="strRoom" filter="false"/>    		 
    	</select>
    	</div>
    </td>   
  </tr>
  <tr> 
    <td colspan="" class="LABEL">Bed Type</td>
    <td colspan="" class="multiControl"><select name="strBedType" onChange="" class="comboNormal">
    	 <bean:write name="paientAdmissionTransBean" property="strBedType" filter="false"/>
    </select></td>
    <td align="center" colspan="2" class="LABEL"><div align="center" id="searchId">
    <img src="../../hisglobal/images/btn-search.png"	onClick="return searchBed();" style="cursor: pointer;" /></div></td>
   </tr>   
</table>
<div id="bedStatusDiv"></div>
<table border="0" align="center" width="100%" cellpadding="1" cellspacing="0">
  <tr class="FOOTER">
  	<td colspan="4"><font color='red'>*</font>Mandatory Fields</td>
  </tr>
  <tr style="display: none;">
		<td align="right"><img
			src="../../hisglobal/images/btn-ok.png" onClick="return ok();" style="cursor: pointer;" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			onClick="window.close();" style="cursor: pointer;cursor:hand" /></td>
	</tr>	
</table>   
</td></tr></table>
<input type="hidden" name="hmode" />
<input type="hidden" name="strRoomBedName" value=""/>
<input type="hidden" name="strFlag" value="1"/>
<input type="hidden" name="strDeptCode" value="${paientAdmissionTransBean.strDeptCode}"/>
<input type="hidden" name="strDeptUnitCode" value="${paientAdmissionTransBean.strDeptUnitCode}"/>
<input type="hidden" name="strMsApprovalStatus" value="${paientAdmissionTransBean.strMsApprovalStatus}"/>
<input type="hidden" name="strMsApprovalFlag" value="${paientAdmissionTransBean.strMsApprovalFlag}"/>
<input type="hidden" name="strTreatmentCategoryCode" value="${paientAdmissionTransBean.strTreatmentCategoryCode}"/>
<input type="hidden" name="msApprovalFlag" value=""/>
<input type="hidden" name="strVacantBed" value=""/>
<input type="hidden" name="strAdmissionChargeValue" value="${paientAdmissionTransBean.strAdmissionChargeValue}"/>
<input type="hidden" name="strHiddenUnit" value="${paientAdmissionTransBean.strHiddenUnit}">
<input type="hidden" name="strHiddenRoom" value="${paientAdmissionTransBean.strHiddenRoom}">
<input type="hidden" name="strWin_Resize" value="${paientAdmissionTransBean.strWin_Resize}">

<div id="allData"></div>
</html:form>
</body>
</html>