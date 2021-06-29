<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<html>
<head>
<meta charset=utf-8>
<title>Bed Status Online Advice</title>

<link href="/HBIMS/ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" 	type="text/css">
<link href="/HBIMS/hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<style type='text/css'>
.bedbutton{
border          : 3px #B5B5B5 outset;
padding         : 25 20px;
color           : black;
cursor          : pointer ;
cursor          : hand;
text-align      : center;
text-decoration : none;
font            : normal 13px Verdana;
}

.bedImages{
height: 40px;
width: 35px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesFocus{
height: 50px;
width: 50px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesSmall{
height: 20px;
width: 20px;
cursor: pointer;
vertical-align: middle;

}
.bedbutton:visited, .bedbutton:hover, .bedbutton:active{
color: grey;
}
</style>

<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/tab.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/multirow.js"/>
<his:javascript src="/hisglobal/js/popup.js"/>
<his:javascript src="/hisglobal/js/toolTip.js"/>

<script type="text/javascript">

	function getBedStatus()
	{
		if(document.forms[0].bedType.selectedIndex != 0)
		{
			if(document.forms[0].roomType.selectedIndex == 0)
			{
					document.forms[0].bType.selectedIndex = 0;
					document.forms[0].roomType.focus();
					return false;
			}
			else
			{
				myFunc('1',"","");
			}
		}
		else
		{
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
		}
	}

var pWindow ="";
function getRoomNo()
{
	var hmode = "MODEROOM";
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].wardName.value+"&roomTypeCode="+document.forms[0].roomType.value+"&sexCode="+window.opener.document.forms[0].strSex.value+"&age="+window.opener.document.forms[0].strAge.value+"&treatmentCategory="+window.opener.document.forms[0].strPrimaryCategory.value
		         +"&unitvalue="+window.opener.document.forms[0].strUnitValue.value+"&deptcode="+window.opener.document.forms[0].strDepartmentValue.value+"&deptUnitCode="+window.opener.document.forms[0].strUnitValue.value+"&crNo="+window.opener.document.getElementsByName("patCrNo")[0].value+"&ageCode="+window.opener.document.forms[0].strUnitCode.value;
		ajaxFunction(url,"4");
}

function myFunc(mode,obj,status)
{
	if(mode == '1')
	{
		var hmode = "BEDSTATUSDTL"; 
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode=${advanceAdviceTransBean.strWard}&bDate=${advanceAdviceTransBean.strPropAdmissionDate}&bedType="+document.forms[0].bedType.value+"&roomType="+document.forms[0].roomType.value;
		ajaxFunction(url,"1");	
	}
	else if(mode == '2')
	{	
			pWindow = obj;
			var hmode = "BEDSTATUSPATIENTDTL"; 
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode=${advanceAdviceTransBean.strWard}&bDate="+obj.name+"&bedType="+document.forms[0].bedType.value+"&roomType="+document.forms[0].roomType.value+"&bStatus="+status;
		ajaxFunction(url,"2");		
	}	
}	
function getAjaxResponse(res,mode)
{	
		if(mode == '1')
		{
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = res;
			getBedProperties();
		}
		else if(mode == '2')
		{
			var objEle = document.getElementById("menu1");
			objEle.innerHTML = res;
			display_popup_menu(pWindow,"menu1","20","");
		}
		else if(mode=='4')
		{
			var objEle=document.getElementById("roomId");
			objEle.innerHTML='<select name="roomDetails" onChange=""  class="comboNormal">'+res+'</select>';
		}
		else if(mode=='5')
		{
			var objEle=document.getElementById("roomStatusDiv");
			objEle.innerHTML=res.split("^")[0];
			getBedProperties1();
			resize_popUp();
			
			//var objEle=document.getElementById("roomId");
			//objEle.innerHTML='<select name="roomDetails" onChange="">'+res+'</select>';
		}
		else if(mode=='6')
		{
			var objEle=document.getElementById("wardId");
			if(res=='')
			{
				objEle.innerHTML='<select name="wardName" class="comboNormal"><option value=0 selected>Select Value</option></select>';
			}
			else
			{
				objEle.innerHTML='<select name="wardName" class="comboNormal">'+res+'</select>';
			}
			
			//var objEle=document.getElementById("roomId");
			//objEle.innerHTML='<select name="roomDetails" onChange="">'+res+'</select>';
		}
		if(mode==101){
		document.getElementById("allData").innerHTML=res;
	}
}

function bedStatusCheck_test()
{		
			window.opener.document.forms[0].strRoomType.value=document.forms[0].roomType.value;	
			window.opener.document.forms[0].strBedType.value=document.forms[0].bedType.value;
			window.opener.document.getElementById("roomBedId").innerHTML=document.forms[0].roomType[document.forms[0].roomType.selectedIndex].text+"/"+document.forms[0].bedType[document.forms[0].bedType.selectedIndex].text;
			//window.opener.document.forms[0].roomType.disabled="false";
			window.close();
}
function getBedProperties1()
{
		var hmode = "BEDPROPERTIES"; 
		//var url = "PatientAdmissionTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&roomCode="+document.forms[0].strRoom.value+"&bedTypeCode="+document.forms[0].strBedType.value+"&deptUnitCode="+document.forms[0].strDeptUnitCode.value;
		var WrdRoomBedtypUnt_code=document.forms[0].wardName.value+"^"+document.forms[0].roomDetails.value+"^"+document.forms[0].bedType.value+"^"+document.forms[0].strUnitCode.value;
		var url="/HBIMS/ipd/transactions/IpdCNT.cnt?hmode="+hmode+"&modPopUp="+WrdRoomBedtypUnt_code+"&imagepath=advice";
		ajaxFunction(url,"101");
}
function roomTypeChange(obj)
{
			document.forms[0].bedType.selectedIndex = 0;
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
}
function onSearch(these)
{
		 var hmode = "BEDDETAILS"; 
		 //var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
		 var WrdRoomBedtypUnt_code=document.forms[0].wardName.value+"^"+document.forms[0].roomDetails.value+"^"+document.forms[0].bedType.value+"^"+document.forms[0].strUnitCode.value;
		 //var url = "AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].wardName.value+"&roomCode="+document.forms[0].roomDetails.value+"&bedType="+document.forms[0].bedType.value+'&deptUnitCode='+document.forms[0].strUnitCode.value;
		 var url="/HBIMS/ipd/transactions/IpdCNT.cnt?hmode="+hmode+"&modPopUp="+WrdRoomBedtypUnt_code+"&imagepath=adivce";
		 var hisValidator = new HISValidator(document.forms[0].name);
		 hisValidator.addValidation("wardName","dontselect=0","Please select a Ward");
		 var retVal = hisValidator.validate(); 
		if(retVal)
		{
			ajaxFunction(url,"5");
			return true;
		}
		else
		return false;
}
function setWard()
{
		var hmode = "WARD"; 
		var dept=window.opener.document.forms[0].strDepartmentValue.value;
		//alert("dept->"+dept);
		//alert(window.opener.document.forms[0].strDepartmentValue.value)
	//	alert(window.opener.document.forms[0].strSex.value);
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].wardType.value+"&deptname="+window.opener.document.forms[0].strDepartment.value+"&sexCode="+window.opener.document.forms[0].strSex.value+"&age="+window.opener.document.forms[0].strAge.value+"&treatmentCategory="+window.opener.document.forms[0].strPrimaryCategory.value
		         +"&unitvalue="+window.opener.document.forms[0].strUnitValue.value+"&deptcode="+window.opener.document.forms[0].strDepartmentValue.value+"&deptUnitCode="+window.opener.document.forms[0].strUnitValue.value+"&crNo="+window.opener.document.getElementsByName("patCrNo")[0].value+"&ageCode="+window.opener.document.forms[0].strUnitCode.value;
		ajaxFunction(url,"6");
			
}
function resize_popUp()
{
 //alert("added height->"+document.forms[0].strWin_Resize.value);
 var newHeight=parseInt(document.forms[0].strWin_Resize.value);
 //alert("new height->"+newHeight);
 window.resizeTo('500',newHeight);
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
</script>

</head>
<body onload="autoTabIndexing()">
<html:form action="/ipd/transactions/AdmissionAdviceTransCNT.cnt" method="post">

<div class="normalMsg" id="normalMsg"></div>
<div class="popup" id="menu1" style="display:none;">
</div>
<table align="center" border="1" width="300%" bordercolor="black" cellpadding="0" cellspacing="0">
<tr> 
<td width="100%">
<table align="center" border="0" width="300%">
  <tr class="HEADER"> 
     <td colspan="4">Admission Advice &gt;&gt;Bed Details </td>
  </tr>
  <tr> 
    <td colspan="2" class="LABEL">Department</td>
    <td colspan="2" class="CONTROL">
    	<div align="center">
    		<b><i>
    		<font color="blue"><bean:write name="advanceAdviceTransBean" property="strDepartment"/></font>
    		</i></b>
    	</div>
    </td>   
 </tr>
 <tr>
  <td width="25%" class="LABEL">Ward Type</td>
  <td width="25%" class="multiControl"><select name="wardType" onChange="setWard();" class="comboNormal"><bean:write name="advanceAdviceTransBean" property="strWardTypeValue" filter="false"/> </select></td>
  <td width="25%" class="LABEL"><font color="red">*</font>Ward</td>
    <td width="25%" class="multiControl">
    	<div id="wardId">
    	<select name="wardName" onChange="" class="comboNormal">
    	<bean:write name="advanceAdviceTransBean" property="strWardValues" filter="false" /></select>
    	</div> 
    </td>
 </tr>
  <tr> 
    <td width="25%" class="LABEL">Room Type</td>
    <td width="25%" class="multiControl"><select name="roomType" onChange="getRoomNo();" class="comboNormal"><bean:write name="advanceAdviceTransBean" property="strRoomTypeValues" filter="false"/> </select> </td>   
    <td width="25%" class="LABEL">Room</td>
    <td width="25%" class="multiControl">
    	<div id="roomId">
    	<select name="roomDetails" onChange="" class="comboNormal">
    		<bean:write name="advanceAdviceTransBean" property="strRoomValues" filter="false"/>
    	</select>
    	</div>
    </td>   
  </tr>
  <tr> 
    <td colspan=""  class="LABEL"><font color="red">*</font>Bed Type</td>
    <td colspan=""  class="multiControl">
    <select name="bedType" onChange="return getBedStatus();" class="comboNormal">
    <bean:write name="advanceAdviceTransBean" property="strBedTypeValues" filter="false" /></select></td>  
    <td align="center" colspan="2" class="LABEL"><div align="center" id="searchId">
  	<img src="../../hisglobal/images/btn-search.png" style="cursor: pointer;"	onClick="return onSearch(this);" /></div></td>
  </tr>
</table>
  
<div id="roomStatusDiv"></div> 
<!-- <div id="bedStatusDiv"></div> -->
<table border="0" width="300%" align="center">	
	<tr class="FOOTER" class="TABLEWIDTH"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  <tr>
		<td align="right">
		<!-- <img src="/HBIMS/hisglobal/images/btn-ok.png" onClick="return bedStatusCheck_test();" style="cursor: pointer;" /></td>
		<td align="left"><img src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="window.close();" style="cursor: pointer;"/></td>
		-->
		<br>
		<td align="right"><a href="#" class="button" id="" onClick="return bedStatusCheck_test();"><span class="ok">Ok</span></a></td>
			<td align="left"><a href="#" class="button" onclick="window.close();"><span class="cancel">Cancel</span></a></<td>
	</tr>
</table>	
</td></tr></table>
<input type="hidden" name="hmode" />
<input type="hidden" name="strUnitCode" value="${advanceAdviceTransBean.strUnitCode }"/>
<div id="allData"></div>
<script>getRoomNo();</script>
</html:form>
</body>
</html>