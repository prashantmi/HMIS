<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css"
	rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css"
	rel="stylesheet">
<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>




<link href="/HBIMS/ipd/css/transaction.css" rel="stylesheet"
	type="text/css">
<!-- <link href="/HBIMS/hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="/HBIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" 	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/popup.css" rel="stylesheet" type="text/css"> -->
<style type='text/css'>
padding-right
:
 
11
.5rem
;

    
padding-left
:
 
0
.5rem
;


}
.navbar {
	padding: 0;
	background-image: linear-gradient(to right, #49B2F3, #02629C);
}

a.md-wg-deal-link, a {
	font-size: 16px;
	color: white;
}

a.md-wg-deal-link, a:hover {
	color: white;
}

.bedbutton {
	border: 3px #B5B5B5 outset;
	padding: 25 20px;
	color: black;
	cursor: pointer;
	cursor: hand;
	text-align: center;
	text-decoration: none;
	font: normal 13px Verdana;
}

.bedImages {
	height: 40px;
	width: 35px;
	cursor: pointer;
	vertical-align: middle;
}

.bedImagesFocus {
	height: 50px;
	width: 50px;
	cursor: pointer;
	vertical-align: middle;
}

.bedImagesSmall {
	height: 20px;
	width: 20px;
	cursor: pointer;
	vertical-align: middle;
}

.bedbutton:visited, .bedbutton:hover, .bedbutton:active {
	color: grey;
}
</style>

<his:javascript src="/hisglobal/masterutil/js/master.js" />
<his:javascript src="/hisglobal/js/tab.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/multirow.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/toolTip.js" />

<script type="text/javascript">
	function getBedStatus() {
		if (document.forms[0].bedType.selectedIndex != 0) {
			if (document.forms[0].roomType.selectedIndex == 0) {
				document.forms[0].bType.selectedIndex = 0;
				document.forms[0].roomType.focus();
				return false;
			} else {
				myFunc('1', "", "");
			}
		} else {
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
		}
	}

	var pWindow = "";
	function getRoomNo() {
		var hmode = "MODEROOM";
		var ward = document.getElementsByName("wardName")[0].value;
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="
				+ hmode
				+ "&wardCode="
				+ ward
				+ "&roomTypeCode="
				+ document.getElementsByName("roomType")[0].value
				+ "&sexCode="
				+ document.forms[0].strSex.value
				+ "&age="
				+ document.forms[0].strAge.value
				+ "&treatmentCategory="
				+ document.forms[0].strPrimaryCategory.value
				+ "&unitvalue="
				+ document.forms[0].strUnitValue.value
				+ "&deptcode="
				+ document.forms[0].strDepartmentValue.value
				+ "&deptUnitCode="
				+ document.forms[0].strUnitValue.value
				+ "&crNo="
				+ document.getElementsByName("patCrNo")[0].value
				+ "&ageCode="
				+ document.forms[0].strUnitCode.value;
		ajaxFunction(url, "4");

	}

	function myFunc(mode, obj, status) {
		if (mode == '1') {
			var hmode = "BEDSTATUSDTL";
			var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="
					+ hmode
					+ "&wardCode=${advanceAdviceTransBean.strWard}&bDate=${advanceAdviceTransBean.strPropAdmissionDate}&bedType="
					+ document.forms[0].bedType.value
					+ "&roomType="
					+ document.forms[0].roomType.value;
			ajaxFunction(url, "1");
		} else if (mode == '2') {
			pWindow = obj;
			var hmode = "BEDSTATUSPATIENTDTL";
			var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="
					+ hmode
					+ "&wardCode=${advanceAdviceTransBean.strWard}&bDate="
					+ obj.name
					+ "&bedType="
					+ document.forms[0].bedType.value
					+ "&roomType="
					+ document.forms[0].roomType.value
					+ "&bStatus=" + status;
			ajaxFunction(url, "2");

		}
	}
	function getAjaxResponse(res, mode) {
		if (mode == '1') {
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = res;
			getBedProperties();
		} else if (mode == '2') {
			var objEle = document.getElementById("menu1");
			objEle.innerHTML = res;
			display_popup_menu(pWindow, "menu1", "20", "");
		} else if (mode == '4') {
			var objEle = document.getElementById("roomId");
			objEle.innerHTML = '<select name="roomDetails" onChange=""  class="form-control">'
					+ res + '</select>';
		} else if (mode == '5') {
			var objEle = document.getElementById("roomStatusDiv");
			objEle.innerHTML = res.split("^")[0];
			getBedProperties1();
			//resize_popUp();

			//var objEle=document.getElementById("roomId");
			//objEle.innerHTML='<select name="roomDetails" onChange="">'+res+'</select>';
		} else if (mode == '6') {
			var objEle = document.getElementById("wardId");
			if (res == '') {
				objEle.innerHTML = '<select name="wardName" class="form-control"><option value=0 selected>Select Value</option></select>';
			} else {
				objEle.innerHTML = '<select name="wardName" class="form-control">'
						+ res + '</select>';
			}

			//var objEle=document.getElementById("roomId");
			//objEle.innerHTML='<select name="roomDetails" onChange="">'+res+'</select>';
		}
		if (mode == 101) {
			document.getElementById("allData").innerHTML = res;
		}
	}

	function bedStatusCheck_test() {
		window.opener.document.forms[0].strRoomType.value = document.forms[0].roomType.value;
		window.opener.document.forms[0].strBedType.value = document.forms[0].bedType.value;
		if (document.forms[0].roomType.value == "0")
			window.opener.document.getElementById("roomBedId").innerHTML = "/"
					+ document.forms[0].bedType[document.forms[0].bedType.selectedIndex].text;
		else
			window.opener.document.getElementById("roomBedId").innerHTML = document.forms[0].roomType[document.forms[0].roomType.selectedIndex].text
					+ "/"
					+ document.forms[0].bedType[document.forms[0].bedType.selectedIndex].text;
		//window.opener.document.forms[0].roomType.disabled="false";
		window.close();
	}
	function getBedProperties1() {
		var hmode = "BEDPROPERTIES";
		//var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&roomCode="+document.forms[0].strRoom.value+"&bedTypeCode="+document.forms[0].strBedType.value+"&deptUnitCode="+document.forms[0].strDeptUnitCode.value;
		var WrdRoomBedtypUnt_code = document.getElementsByName("wardName")[0].value
				+ "^"
				+ document.getElementsByName("roomDetails")[0].value
				+ "^"
				+ document.getElementsByName("bedType")[0].value
				+ "^"
				+ document.forms[0].strUnitCode.value;
		var url = "/HBIMS/ipd/transactions/IpdCNT.cnt?hmode=" + hmode
				+ "&modPopUp=" + WrdRoomBedtypUnt_code + "&imagepath=advice";
		ajaxFunction(url, "101");
	}
	function roomTypeChange(obj) {
		document.forms[0].bedType.selectedIndex = 0;
		var objEle = document.getElementById("bedStatusDiv");
		objEle.innerHTML = "";
	}
	function onSearch(these) {
		var hmode = "BEDDETAILS";
		//var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
		var WrdRoomBedtypUnt_code = document.forms[0].wardName.value + "^"
				+ document.forms[0].roomDetails.value + "^"
				+ document.forms[0].bedType.value + "^"
				+ document.forms[0].strUnitCode.value;
		//var url = "AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].wardName.value+"&roomCode="+document.forms[0].roomDetails.value+"&bedType="+document.forms[0].bedType.value+'&deptUnitCode='+document.forms[0].strUnitCode.value;
		var url = "/HBIMS/ipd/transactions/IpdCNT.cnt?hmode=" + hmode
				+ "&modPopUp=" + WrdRoomBedtypUnt_code + "&imagepath=adivce";
		var hisValidator = new HISValidator(document.forms[0].name);
		hisValidator.addValidation("wardName", "dontselect=0",
				"Please select a Ward");
		var retVal = hisValidator.validate();
		if (retVal) {
			ajaxFunction(url, "5");
			return true;
		} else
			return false;
	}
	function setWard() {
		var hmode = "WARD";
		//var dept=window.opener.document.forms[0].strDepartmentValue.value;
		var dept = document.forms[0].strDepartmentValue.value;
		//alert("dept->"+dept);
		//alert(window.opener.document.forms[0].strDepartmentValue.value)
		//	alert(window.opener.document.forms[0].strSex.value);
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="
				+ hmode
				+ "&wardTypeCode="
				+ document.forms[0].wardType.value
				+ "&deptname="
				+ window.opener.document.forms[0].strDepartment.value
				+ "&sexCode="
				+ window.opener.document.forms[0].strSex.value
				+ "&age="
				+ window.opener.document.forms[0].strAge.value
				+ "&treatmentCategory="
				+ window.opener.document.forms[0].strPrimaryCategory.value
				+ "&unitvalue="
				+ window.opener.document.forms[0].strUnitValue.value
				+ "&deptcode="
				+ window.opener.document.forms[0].strDepartmentValue.value
				+ "&deptUnitCode="
				+ window.opener.document.forms[0].strUnitValue.value
				+ "&crNo="
				+ window.opener.document.getElementsByName("patCrNo")[0].value
				+ "&ageCode="
				+ window.opener.document.forms[0].strUnitCode.value;
		ajaxFunction(url, "6");
	}
	/* function resize_popUp() {
		//alert("added height->"+document.forms[0].strWin_Resize.value);
		var newHeight = parseInt(document.forms[0].strWin_Resize.value);
		//alert("new height->"+newHeight);
		window.resizeTo('500', newHeight);
	} */
	function hideDetailsH(obj) {
		document.getElementById("minusonLineIdH").style.display = "none";
		document.getElementById("plusonLineIdH").style.display = "block";
		document.getElementById("bedStatHelpDtl").style.display = "none";
	}
	function showDetailsH() {
		document.getElementById("plusonLineIdH").style.display = "none";
		document.getElementById("minusonLineIdH").style.display = "block";
		document.getElementById("bedStatHelpDtl").style.display = "block";
	}
	function autoTabIndexing() {
		var fFlagFocusFirst = true;
		var allImageObj = document.getElementsByTagName('img');
		var allAnchorObj = document.getElementsByTagName('A');
		var allSelectObj = document.getElementsByTagName('SELECT');
		var allInputObj = document.getElementsByTagName('INPUT');
		var allTxtObj = document.getElementsByTagName('TEXTAREA');
		/*for(var nTmpI=0; nTmpI<document.forms[0].elements.length; nTmpI++)
		{
			document.forms[0].elements[nTmpI].setAttribute('tabIndex','1');
			if(fFlagFocusFirst && document.forms[0].elements[nTmpI].type!='hidden' 
				&& (document.forms[0].elements[nTmpI].tagName=='INPUT' 
				||document.forms[0].elements[nTmpI].tagName=='SELECT' 
				|| document.forms[0].elements[nTmpI].tagName=='TEXTAREA'))
			{
				document.forms[0].elements[nTmpI].focus();
		    if(((document.forms[0].elements[nTmpI].tagName=='INPUT' 
		    	&& document.forms[0].elements[nTmpI].type=='text') 
		    	|| document.forms[0].elements[nTmpI].tagName=='TEXTAREA') 
		    	&& document.forms[0].elements[nTmpI].value.length >0)
		    {
				document.forms[0].elements[nTmpI].select();
				fFlagFocusFirst=false;
			}
		}
		}*/
		for (var nTmpI = 0; nTmpI < allImageObj.length; nTmpI++) {
			allImageObj[nTmpI].setAttribute('tabIndex', '1');
			if (allImageObj[nTmpI].src.split('/')[allImageObj[nTmpI].src
					.split('/').length - 1] == 'tp.gif')
				allImageObj[nTmpI].setAttribute('tabIndex', '0');
		}
		for (var nTmpI = 0; nTmpI < allAnchorObj.length; nTmpI++) {
			allAnchorObj[nTmpI].setAttribute('tabIndex', '1');
		}
		for (var nTmpI = 0; nTmpI < allSelectObj.length; nTmpI++) {
			allSelectObj[nTmpI].setAttribute('tabIndex', '1');
		}
		for (var nTmpI = 0; nTmpI < allInputObj.length; nTmpI++) {
			allInputObj[nTmpI].setAttribute('tabIndex', '1');
		}
		for (var nTmpI = 0; nTmpI < allTxtObj.length; nTmpI++) {
			allTxtObj[nTmpI].setAttribute('tabIndex', '1');
		}
	}
</script>
</head>
<body onload="autoTabIndexing()">
	<html:form action="/transactions/AdmissionAdviceTransBSCNT.cnt"
		method="post">

		<div class="normalMsg" id="normalMsg"></div>

		<div class="row rowFlex reFlex">
			<div class="col-sm-5" align="right">
				<label>Department</label>
			</div>
			<div class="col-sm-7">
				<b><i> <font color="blue"><bean:write
								name="advanceAdviceTransBean" property="strDepartment" /></font>
				</i></b>
				<div id="offlineUnitId"></div>
			</div>
		</div>
		<div class="row rowFlex reFlex">
			<div class="col-sm-3">
				<label>Ward Type</label>
			</div>
			<div class="col-sm-3">
				<select name="wardType" onChange="setWard();" class="form-control"
					tabindex="1"><bean:write name="advanceAdviceTransBean"
						property="strWardTypeValue" filter="false" />
				</select>
			</div>
			<div class="col-sm-3">
				<label><font color="red">*</font>Ward</label>
			</div>
			<div class="col-sm-3">
				<select name="wardName" onChange="" class="form-control">
					<bean:write name="advanceAdviceTransBean" property="strWardValues"
						filter="false" />
				</select>
			</div>

		</div>
		<div class="row rowFlex reFlex">
			<div class="col-sm-3">
				<label>Room Type</label>
			</div>
			<div class="col-sm-3">
				<select name="roomType" onChange="getRoomNo();" class="form-control"><bean:write
						name="advanceAdviceTransBean" property="strRoomTypeValues"
						filter="false" />
				</select>
			</div>
			<div class="col-sm-3">
				<label><font color="red">*</font>Room</label>
			</div>
			<div class="col-sm-3" id="roomId">
				<select name="roomDetails" onChange="" class="form-control">
					<bean:write name="advanceAdviceTransBean" property="strRoomValues"
						filter="false" />
				</select>
			</div>

		</div>
		<div class="row rowFlex reFlex">
			<div class="col-sm-3">
				<label><font color="red">*</font>Bed Type</label>
			</div>
			<div class="col-sm-3">
				<select name="bedType" onChange="return getBedStatus();"
					class="form-control">
					<bean:write name="advanceAdviceTransBean"
						property="strBedTypeValues" filter="false" />
				</select>
			</div>

			<div class="col-sm-6" align="right">
				<div id="searchId">
					<button type="button" class="btn btn-primary"
						onClick="return onSearch(this);">search</button>
				</div>
			</div>

		</div>
		<div class="popup" id="menu1" style="display: none;"></div>


		<div id="roomStatusDiv"></div>
		<!-- <div id="bedStatusDiv"></div> -->
		<!-- <table  width="100%" align="center" border="0" >	
	<tr class="HEADER"> 
    <td colspan="4" align="right"><font size="2" color="red">*</font>Mandatory Fields</td>
  </tr>
	
</table> -->

		<input type="hidden" name="hmode" />
		<input type="hidden" name="strUnitCode"
			value="${advanceAdviceTransBean.strUnitCode }" />
		<div id="allData"></div>
	</html:form>
	<script>
		getRoomNo();
	</script>
</body>
</html>