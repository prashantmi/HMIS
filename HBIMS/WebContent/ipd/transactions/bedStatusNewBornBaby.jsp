<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Bed Status New Born Baby</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
 <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link href="../css/basic.css" rel="stylesheet" type="text/css">

<style type="text/css">

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
margin-left:5%;
height: 50px;
width: 80px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesFocus{
height: 50px;
width: 90px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesSmall{
height: 20px;
width: 50px;
cursor: pointer;
vertical-align: middle;

}
.aval
{
color: #004573;
font-weight: 700;
display: inline-block;
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 9px;
}
.bedbutton:visited, .bedbutton:hover, .bedbutton:active{
color: grey;
}
</style>

<!-- <style type='text/css'>

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
td{
	padding:0.5%;
 }

.bedbutton:visited, .bedbutton:hover, .bedbutton:active{
color: white;
}
</style> -->
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/toolTip.js"></script>
<script type="text/javascript" src="/HBIMS/ipd/js/bootstrap.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<script type="text/javascript">
	function searchBed()
	{
		var hmode = "BEDDETAILS";	
		//var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&roomCode="+document.forms[0].strRoom.value+"&bedTypeCode="+document.forms[0].strBedType.value+"&deptUnitCode="+document.forms[0].strDeptUnitCode.value;
		var WrdRoomBedtypUnt_code=document.forms[0].strWard.value+"^"+document.forms[0].strRoom.value+"^"+document.forms[0].strBedType.value+"^"+document.forms[0].strDeptUnitCode.value;
		var url="IpdCNT.cnt?hmode="+hmode+"&modPopUp="+WrdRoomBedtypUnt_code;
		//alert("Search"+document.forms[0].strDeptCode.value);
		var hisValidator = new HISValidator("newBornTransBean");
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
	function getAjaxResponse(res,mode){
		//alert(mode);
	var err = document.getElementById("errId");
	var temp = res.split("####");
	if(temp[0] == "ERROR")
	{
	err.innerHTML = temp[1];
	return;
	} 
	if(mode==1)
	{
		var temp=res.split('^');
		var objEle=document.getElementById("bedStatusDiv");
	//	alert(temp[0]);
		objEle.innerHTML=temp[0];
		//resize_popUp();
		document.forms[0].msApprovalFlag.value=temp[1];
		/*if(document.forms[0].strVacantBed.value==0)
		{
			document.forms[0].strFlag.value="0";
			alert("There are no vacant beds in this room.Please select another room or ward");
		}*/
		getBedProperties();
	}
	if(mode==2)
	{
		var objEle=document.getElementById("wardId");
		objEle.innerHTML='<select name="strWard" class="comboNormal" onChange="getRoomNo();">'+res+'</select>';
	}
	if(mode==3)
	{
		var objEle=document.getElementById("roomId");
		objEle.innerHTML='<select name="strRoom" onChange="" class="comboNormal">'+res+'</select>';
	}
	if(mode==4)
	{
		document.forms[0].strResult.value=res;
		getRoomNo();
		
	}
	if(mode==101){
			document.getElementById("allData").innerHTML=res;
		}
	if(mode==5)
	{
		//alert(mode);
	var temp=res.split('^');
	//alert(res);
	
	
	window.opener.document.forms[0].strRegistrationChargeHidden.value=temp[2];
	createTable(temp[0],temp[1],temp[2]);
	/*document.forms[0].strAdmissionChargeValue.value=temp[0];
	document.forms[0].strAdmissionAdvanceChargeValue.value=temp[1];
	document.forms[0].strRegistrationChargeHidden.value=temp[2];
	document.getElementById("admissionChargeId").innerHTML=temp[0];
	document.getElementById("RegistrationChargeId").innerHTML==temp[2];
	document.getElementById("advanceAdmissionChargeId").innerHTML==temp[1];*/
	
	var glob=0;
	if(window.opener.document.forms[0].strAdmissionCharge.value=="1")
	{
		glob=parseInt(glob)+parseInt(isNaN(temp[0])?0:temp[0]);
	}
	if(window.opener.document.forms[0].strAdmissionAdvance.value=="1")
	{
		glob=parseInt(glob)+parseInt(isNaN(temp[1])?0:temp[1]);				
	}
    if (window.opener.document.forms[0].strNewBornRegistrationCharge.value=="1")
	{
    	glob=parseInt(glob)+parseInt(isNaN(temp[2])?0:temp[2]);				
	}
    window.opener.document.getElementById("TotalID").innerHTML="<img src='/HBIMS/hisglobal/images/INR.png'>"+glob;;
    window.opener.document.forms[0].strTotalAmount.value=glob;	 
    window.close();
	return true;
	}
}
	function getBedname(obj)
	{
		var name=obj.value;
		window.opener.document.forms[0].strBedCode.value=document.getElementById(name).value;
		window.opener.document.forms[0].strRoomCode.value=document.forms[0].strRoom.value;
		window.opener.document.forms[0].strMotherWardCode.value=document.forms[0].strWard.value;
		document.forms[0].strRoomBedName.value=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text+'/'+obj.value;
		alert('You have successfully selected '+obj.value+' bed');
		//window.opener.document.getElementById("roomBedId").innerHTML=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text+'/'+obj.value;
	}
	function ok()
	{
			
			var hisValidator = new HISValidator("newBornTransBean");
			hisValidator.addValidation("strWard","dontselect=0","Please Select Ward");
			//hisValidator.addValidation("strRoom","dontselect=0","Please select a value Room No");
			//hisValidator.addValidation("strBedType","dontselect=0","Please select a value Bed Type");
			var retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
			if(retVal)	
			{
				window.opener.document.forms[0].strFlag.value=document.forms[0].strFlag.value;
				window.opener.document.forms[0].strMsApprovalFlag.value="0";
				window.opener.document.forms[0].strMsApprovalStatus.value="";
				if(document.forms[0].strRoom.value!=0){
					window.opener.document.forms[0].strRoomCode.value=document.forms[0].strRoom.value;
				}
				if(document.forms[0].strRoom.value!=0)
					window.opener.document.getElementById("roomBedId").innerHTML=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text;
				window.opener.document.getElementById("wardNameId").innerHTML=document.forms[0].strWard[document.forms[0].strWard.selectedIndex].text;
				window.opener.document.forms[0].strWardCode.value=document.forms[0].strWard.value;
				window.opener.document.forms[0].strMotherWardTypeCode.value=document.forms[0].strwardType.value;
				window.opener.document.forms[0].strWardName.value=document.forms[0].strWard[document.forms[0].strWard.selectedIndex].text;
				window.opener.document.forms[0].strRoom.value=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text;
				
				var hmode = "GETADVANCEAMOUNT"; 
				
				var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&teatmentCategory="+window.opener.document.forms[0].strTreatmentCategoryCode.value+"&unit_code="+window.opener.document.forms[0].strUnitNewBorn.value;
				//alert(url);
				ajaxFunction(url,"5");
				/*if(!(document.forms[0].strResult.value=="") && (window.opener.document.forms[0].strAdmissionCharge.value=="1" || window.opener.document.forms[0].strNewBornRegistrationCharge.value=="1"||window.opener.document.forms[0].strAdmissionAdvance.value=="1"))
				 	saveTable();*/
				
			}
	}
	function setWard()
	{
		var hmode = "GETWARD"; 
		var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value;
		ajaxFunction(url,"2");
	}
	function getWard()
	{
		document.forms[0].strRoom.value="0";
		document.forms[0].strRoomType.value="0";
		document.forms[0].strBedType.value="0";
		var hmode = "GETWARD"; 
		var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+window.opener.document.forms[0].strDeptNameNewBorn.value+"&sexCode="+window.opener.document.forms[0].strGender.value+"&treatmentCategory="+window.opener.document.forms[0].strMotherTreatmentCateg.value+'&deptUnitCode='+window.opener.document.forms[0].strUnitNewBorn.value+'&crNo='+window.opener.document.forms[0].strCrNo.value;
		ajaxFunction(url,"2");
	}
	function getRoomNo()
	{
		var hmode = "GETROOM"; 
		var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&roomTypeCode="+document.forms[0].strRoomType.value+"&wardCode="+
		document.forms[0].strWard.value+'&treatmentCategCode='+window.opener.document.forms[0].strMotherTreatmentCateg.value+'&ageCode=1&sexCode='+window.opener.document.forms[0].strGender.value+'&age=1&crNo='+window.opener.document.forms[0].strCrNo.value+'&deptUnitCode='+window.opener.document.forms[0].strUnitNewBorn.value;
		//alert(url);
		ajaxFunction(url,"3");
	}
	/**********added*******/
function resize_popUp()
{
// alert("added height->"+document.forms[0].strWin_Resize.value);
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
function getChargeValue()
{
	var hmode = "GETADVANCEAMOUNT"; 
	var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardCode="+window.opener.document.forms[0].strWard.value+"&teatmentCategory="+window.opener.document.forms[0].strTreatmentCategoryCode.value;
	
	ajaxFunction(url,"4");
	
}
function createTable(ob1,ob2,ob3)
{

	var tempStr = "";
	var colspan = "1";
	var flag = 0;
	var innerHtmlStr = "";
	var charge="";
	var o= window.opener.document.getElementById("admissionId");
	o.innerHTML="";
	if (window.opener.document.forms[0].strAdmissionCharge.value=="1" || window.opener.document.forms[0].strNewBornRegistrationCharge.value=="1" ||
		window.opener.document.forms[0].strAdmissionAdvance.value=="1")
		{
		//alert(window.opener.document.forms[0].strAdmissionCharge.value);
			if (window.opener.document.forms[0].strAdmissionCharge.value=="1")
			{
				tempStr = "<td width='25%' class='LABEL'>Admission Charges</td>";
				tempStr = tempStr + "<td width='25%' class='CONTROL'><input type='hidden' name='strAdmissionChargeValue' value='"+ob1+"'><div id='admissionChargeId'> <img src='/HBIMS/hisglobal/images/INR.png'>"+ob1+"</div></td>";
				flag = 1;
				//alert(tempStr);
			}
			else
				colspan = "2";
				
			if (window.opener.document.forms[0].strNewBornRegistrationCharge.value=="1")
			{
			//alert(tempStr);
				tempStr += "<td width='25%' class='LABEL'>Registration Charges</td>";
				tempStr += "<td width='25%' class='CONTROL' colspan = " + colspan + "><input type='hidden' name='strNewBornRegistrationChargeVal' value='"+ob3+"'><div id='RegistrationChargeId'> <img src='/HBIMS/hisglobal/images/INR.png'>"+ob3+"</div></td>";
				flag = 1;
			//	alert(tempStr);
			}
			
			if (flag == 1)
			{
				innerHtmlStr="<table class='TABLEWIDTH' align='center' cellspacing='0px' cellpadding='0px'><tr>" + tempStr + "</tr></table>";
			}
			//
			colspan = "1";
			tempStr = "";
			if (window.opener.document.forms[0].strAdmissionAdvance.value=="1")
			{
			//alert("4");
				tempStr = "<td  width='25%' class='LABEL'>Advance Charge</td>";
				tempStr = tempStr + "<td  width='25%' class='CONTROL'><input type='hidden' name='strAdmissionAdvanceChargeValue' value='"+ob2+"'><div id='advanceAdmissionChargeId'> <img src='/HBIMS/hisglobal/images/INR.png'>"+ob2+"</div></td>";
				flag = 2;
			}
			else
				colspan = "2";
				
			if(flag > 0)
			{
				tempStr += "<td class='LABEL' width='25%'>Total Charge</td>";
				tempStr += "<td class='CONTROL'  width='25%' colspan=" + colspan + "><font color='red'><div id='TotalID'></div></font></td>";
				
				innerHtmlStr += "<table class='TABLEWIDTH' align='center' cellspacing='1px'><tr>" + tempStr + "</tr></table>";
				o.innerHTML = innerHtmlStr;
				o.style.display="block";
			}
			
		}
	else
	  o.innerHTML = "";
}
function saveTable()
{

			var temp=document.forms[0].strResult.value.split('^');
			window.opener.document.forms[0].strRegistrationChargeHidden.value=temp[2];
			createTable(temp[0],temp[1],temp[2]);
			var glob=0;
			if(window.opener.document.forms[0].strAdmissionCharge.value=="1")
			{
				glob=parseInt(glob)+parseInt(temp[0]);
			}else
			   glob=0;
			if(window.opener.document.forms[0].strAdmissionAdvance.value=="1")
			{
				glob=parseInt(glob)+parseInt(temp[1]);
				
			}
			else
			   glob=0;
		    if (window.opener.document.forms[0].strNewBornRegistrationCharge.value=="1")
			{
				glob=parseInt(glob)+parseInt(window.opener.document.forms[0].strRegistrationChargeHidden.value);
				
			}
			else
				glob=0;
				window.opener.document.getElementById("TotalID").innerHTML="<img src='/HBIMS/hisglobal/images/INR.png'>"+glob;
			window.opener.document.forms[0].strTotalAmount.value=glob;
			
}
</script>
</head>
<body onload='searchBed();'>
<html:form action="/transactions/NewBornBabyTransCNT.cnt"	method="post" onsubmit="">
<div id="allData"></div>
<div class="errMsg" id="errId"><bean:write name="newBornTransBean" property="strMsgString" /></div>
<div class="normalMsg" id="normalMsg"></div>
<div class="popup" id="menu1" style="display:none;"></div>
<table align="center" border="1" bordercolor="black" cellpadding="0" cellspacing="0" width='80%'>
<tr> 
<td width="100%">
<table align="center" border="0" cellpadding="0" width='100%'>
  <tr class="HEADER"> 
     <td colspan="4">New Born Baby Admission &gt;&gt;Bed Details </td>
  </tr>
   <tr> 
    <td colspan="2" class="LABEL">Department</td>
    <td colspan="2" class="CONTROL">
    	<div align="left">
    		<b>
    			<bean:write property="strDepartmentName" name="newBornTransBean"/>
    		</b>
    	</div>
    </td>   
 </tr>
 <tr>
  <td width="25%" class="LABEL">Ward Type</td>
  <td width="25%" class="multiControl">
  <select name="strwardType" onChange="getWard();" class="comboNormal">
  	<bean:write name="newBornTransBean" property="strwardType" filter="false"/>
  </select></td>
  <td width="25%" class="LABEL"><font color="red">*</font>Ward</td>
    <td width="25%" class="multiControl">
    	<div id="wardId">
    	<select name="strWard" onChange="getChargeValue();" class="comboNormal">
    		<bean:write name="newBornTransBean" property="strWard" filter="false"/>
    	</select>
    	</div> 
    </td>
 </tr>
  <tr> 
    <td width="25%" class="LABEL">Room Type</td>
    <td width="25%" class="multiControl"><select name="strRoomType" onChange="getRoomNo();" class="comboNormal">
   		 <bean:write name="newBornTransBean" property="strRoomType" filter="false"/>
    </select> </td>   
    <td width="25%" class="LABEL"><font color="red">*</font>Room</td>
    <td width="25%" class="multiControl">
    	<div id="roomId">
    	<select name="strRoom" onChange="" class="comboNormal">
    		 <bean:write name="newBornTransBean" property="strRoom" filter="false"/>
    	</select>
    	</div>
    </td>   
  </tr>
  <tr> 
    <td colspan="" class="LABEL">Bed Type</td>
    <td colspan="" class="multiControl"><select name="strBedType" onChange="return getBedStatus();" class="comboNormal">
    	 <bean:write name="newBornTransBean" property="strBedType" filter="false"/>
    </select></td>
    <td colspan="" class="LABEL"></td>
    <td colspan="" class="CONTROL">
    	 <div align="center">   	<img src="../../hisglobal/images/btn-search.png" data-toggle="modal" data-target="#myModal"  onClick="searchBed();" /></div>
    	
    </td>
    <tr class='FOOTER'>
    	<td colspan="4"><font color='red'>*</font>Mandatory Fields</td>
    </tr>
</table>
</td></tr></table>
<div id="bedStatusDiv" class="container-fluid">
</div>
<table border="0" align="center" width='80%'>
	<tr>
		<td align="right"><img
			src="../../hisglobal/images/btn-ok.png" onClick="return ok();" style="cursor: pointer;"/></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			onClick="window.close();" style="cursor: pointer;"/></td>
	</tr>
</table>

  
<input type="hidden" name="hmode" />
<input type="hidden" name="strRoomBedName" value=""/>
<input type="hidden" name="strFlag" value="1"/>
<input type="hidden" name="strDeptCode" value="${newBornTransBean.strDeptCode}"/>
<input type="hidden" name="strDeptUnitCode" value="${newBornTransBean.strDeptUnitCode}"/>
<input type="hidden" name="strTreatmentCategoryCode" value="${newBornTransBean.strTreatmentCategoryCode}"/>
<input type="hidden" name="strResult" value=""/>
<input type="hidden" name="msApprovalFlag" value=""/>

</html:form>
</body>
</html>"