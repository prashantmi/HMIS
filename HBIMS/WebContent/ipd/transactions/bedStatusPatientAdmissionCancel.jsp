
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
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/toolTip.js"></script>
<script type="text/javascript">
	function searchBed()
	{
		var hmode = "BEDDETAILS"; 
		//var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardCode="+document.forms[0].strWard.value+"&roomCode="+document.forms[0].strRoom.value+"&bedTypeCode="+document.forms[0].strBedType.value;
		var WrdRoomBedtypUnt_code=document.forms[0].strWard.value+"^"+document.forms[0].strRoom.value+"^"+document.forms[0].strBedType.value+"^"+document.forms[0].strDeptUnitCode.value;
		var url="IpdCNT.cnt?hmode="+hmode+"&modPopUp="+WrdRoomBedtypUnt_code;
		var hisValidator = new HISValidator("newBornTransBean");
		hisValidator.addValidation("strRoom","dontselect=0","Please select a value Room No");
		var retVal = hisValidator.validate(); 
		if(retVal)
		{
			ajaxFunction(url,"1");
			return true;
		}
		else
		return false;
	}
	function getAjaxResponse(res,mode){
		if(mode==1)
		{
			
			var temp=res.split('^');
			var objEle=document.getElementById("bedStatusDiv");
			objEle.innerHTML=temp[0];
			resize_popUp();
			document.forms[0].msApprovalFlag.value=temp[1];
			if(document.forms[0].strVacantBed.value==0)
			{
				document.forms[0].strFlag.value="0";
				alert("There are no vacant beds in this room.Please select another room or ward");
			}
			getBedProperties();
		}
		if(mode==2)
		{
			var objEle=document.getElementById("wardId");
			objEle.innerHTML='<select name="strWard" class="comboNormal">'+res+'</select>';
		}
		if(mode==3)
		{
			var objEle=document.getElementById("roomId");
			objEle.innerHTML='<select name="strRoom" onChange="" class="comboNormal">'+res+'</select>';
		}
		if(mode==101){
			document.getElementById("allData").innerHTML=res;
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
	function ok()
	{
			if(document.forms[0].msApprovalFlag.value==0)
			{
				window.opener.document.forms[0].strFlag.value=document.forms[0].strFlag.value;
				window.opener.document.forms[0].strMsApprovalFlag.value="0";
				window.opener.document.forms[0].strMsApprovalStatus.value="";
				window.opener.document.forms[0].strRoomCode.value=document.forms[0].strRoom.value;
				window.opener.document.getElementById("roomBedId").innerHTML=document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].text;
				window.opener.document.getElementById("wardNameId").innerHTML=document.forms[0].strWard[document.forms[0].strWard.selectedIndex].text;
				window.close();
				return true;
			}
			else
			{
				alert("Ms officer can approved");
				return false;
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
		var hmode = "GETWARD"; 
		var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&wardTypeCode="+document.forms[0].strwardType.value+"&deptCode="+document.forms[0].strDeptCode.value;
		ajaxFunction(url,"2");
	}
	function getRoomNo()
	{
		
		var hmode = "GETROOM"; 
		var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&roomTypeCode="+document.forms[0].strRoomType.value+"&wardCode="+
		document.forms[0].strWard.value;
		ajaxFunction(url,"3");
	}
	/**********added*******/
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
<body >
<html:form action="/transactions/NewBornBabyTransCNT.cnt"	method="post" onsubmit="">

<div class="normalMsg" id="normalMsg"></div>
<div class="popup" id="menu1" style="display:none;">
</div>
		
		
		<table class="TABLEWIDTH" align="center" border="0" cellspacing="1px">
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
    	<select name="strWard" onChange="" class="comboNormal">
    		<bean:write name="newBornTransBean" property="strWard" filter="false"/>
    	</select>
    	</div> 
    </td>
 </tr>
  <tr> 
    <td width="25%" class="LABEL"><font color="red">*</font>Room Type</td>
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
    <td colspan="2" class="LABEL"><font color="red">*</font>Bed Type</td>
    <td colspan="2" class="multiControl"><select name="strBedType" onChange="return getBedStatus();" class="comboNormal">
    	 <bean:write name="newBornTransBean" property="strBedType" filter="false"/>
    </select></td>
   </tr>
 
  
  <tr>
  	<td align="center" colspan="4"><img src="../../hisglobal/images/btn-search.png"	onClick="return searchBed();" /></td>
  </tr>
  
  
  </table> 
  <div id="bedStatusDiv">
  </div>
   <table border="0" class="TABLEWIDTH" align="center">
	
	<tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
	<tr>

		<td align="right"><img
			src="../../hisglobal/images/btn-ok.png" onClick="return ok();" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			onClick="window.close();" /></td>
	</tr>
</table>
<input type="hidden" name="hmode" />
<input type="hidden" name="strRoomBedName" value=""/>
<input type="hidden" name="strFlag" value="1"/>
<input type="hidden" name="strDeptCode" value="${newBornTransBean.strDeptCode}"/>
<input type="hidden" name="msApprovalFlag" value=""/>
<div id="allData" style="z-index: 100"></div>

</html:form>
</body>
</html>