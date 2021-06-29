
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Bed Status</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/toolTip.js"></script>
<script type="text/javascript">

	function getBedStatus(){
		
		if(document.forms[0].bedType.selectedIndex != 0){
		
			if(document.forms[0].roomType.selectedIndex == 0){
					
					document.forms[0].bType.selectedIndex = 0;
					alert("Please Select A Room Type");
					document.forms[0].roomType.focus();
					return false;
			}else{
				
				myFunc('1',"","");
			}
		}else{
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
		}
	}

var pWindow ="";

function myFunc(mode,obj,status){

	if(mode == '1'){
		var hmode = "BEDSTATUSDTL"; 
		var url = "AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode=${advanceAdviceTransBean.strWard}&bDate=${advanceAdviceTransBean.strPropAdmissionDate}&bedType="+document.forms[0].bedType.value+"&roomType="+document.forms[0].roomType.value;
		ajaxFunction(url,"1");
	
	}else if(mode == '2'){
	
			pWindow = obj;
			var hmode = "BEDSTATUSPATIENTDTL"; 
		var url = "AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode=${advanceAdviceTransBean.strWard}&bDate="+obj.name+"&bedType="+document.forms[0].bedType.value+"&roomType="+document.forms[0].roomType.value+"&bStatus="+status;
		ajaxFunction(url,"2");
	}
	
	}
	
	function getAjaxResponse(res,mode){
	
		if(mode == '1'){
			
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = res;
			getBedProperties();
		}else if(mode == '2'){
			var objEle = document.getElementById("menu1");
			objEle.innerHTML = res;
			
			display_popup_menu(pWindow,"menu1","20","");
		}
		if(mode==101){
			document.getElementById("allData").innerHTML=res;
		}
	}


	function bedStatusCheck_test(){
		
			var checkObj = document.forms[0].bedStatusCheck;
			var chkCnt = parseInt(0); 
			var selDtchk = parseInt(0);
			for(i = 0 ; i<checkObj.length; i++){
					
				if(checkObj[i].checked){
					selDtchk = i;
					chkCnt++;
					if(chkCnt>1){
						alert("Please Check Any One Status");
						chkCnt = 0;
						return false;
					}
					
				}
				
			}
			if(chkCnt == 0){
					alert("Please Check a Status");
					chkCnt = 0;
					return false;
				}else{
				
					var rVal = document.forms[0].roomType[document.forms[0].roomType.selectedIndex].text+"<input type='hidden' name='strRoomType' value='"+document.forms[0].roomType.value+"'>";
					var bVal = document.forms[0].bedType[document.forms[0].bedType.selectedIndex].text+"<input type='hidden' name='strBedType' value='"+document.forms[0].bedType.value+"'>";
					
					var sDate = checkObj[selDtchk].value;
					
					window.opener.setBedStatus( rVal, bVal ,sDate);
					
					window.close();
				}		
	}

	function roomTypeChange(obj){

			document.forms[0].bedType.selectedIndex = 0;
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
		}

	

</script>

</head>
<body >
<html:form action="/transactions/AdmissionAdviceTransCNT.cnt" method="post">

<div class="normalMsg" id="normalMsg"></div>
<div class="popup" id="menu1" style="display:none;">
</div>
<table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Bed Status</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL">Room Type</td>
    <td width="25%" class="CONTROL"><select name="roomType" onChange="roomTypeChange(this);"> <bean:write name="advanceAdviceTransBean" property="strRoomTypeValues" filter="false"/> </select> </td>   
    <td width="25%" class="LABEL">Bed Type</td>
    <td width="25%" class="CONTROL"><select name="bedType" onChange="return getBedStatus();"><bean:write name="advanceAdviceTransBean" property="strBedTypeValues" filter="false" /></select></td>   
  </tr>
 <tr>
  <td colspan="4"> 
  <div id="bedStatusDiv"></div>
</td>
  </tr>
   <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img
				src="../../hisglobal/images/btn-ok.png" onClick="return bedStatusCheck_test();" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
				onClick="window.close();" /></td>
		</tr>
	</table>
<a> </a>
	<input type="hidden" name="hmode" />
	<div id="allData" style="z-index: 100"></div>
</html:form>
</body>
</html>