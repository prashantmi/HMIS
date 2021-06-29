<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta charset=utf-8>
<title>MS Approval Ward Allotment Page</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>

<script type="text/javascript">


function getAgeSex()
{		
	var temp1=document.forms[0].strHiddenPatDtl.value.split("^");
	var strUnitCode="1";
	var sexCode="3";
	var strTemp=temp1[3];
	var strAgeSign="";
	var temp2=strTemp.split("/");
	/**
	 * This loop is for generating age Code
	 */
	for(var i=0;i<strTemp.length;i++)
	{
		if(strTemp.charAt(i)=='Y' || strTemp.charAt(i)=='y'){
			strAgeSign=strTemp.charAt(i);
		strUnitCode="3";
		break;
		}
		else if(strTemp.charAt(i)=='M' || strTemp.charAt(i)=='m')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="2";
			break;
		}
		else if(strTemp.charAt(i)=='D' || strTemp.charAt(i)=='d')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="1";
			break;
		}
		else if(strTemp.charAt(i)=='W' || strTemp.charAt(i)=='w')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="4";
			break;
		} 
		else if(strTemp.charAt(i)=='H' || strTemp.charAt(i)=='h')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="5";
			break;
		} 
		else {
			strUnitCode="3";
			break;
		}
	}
	for(var i=0;i<temp2[1].length;i++){
		if(temp2[1].charAt(i)=='M' || temp2[1].charAt(i)=='m'){
			sexCode="1";
			break;
		}
		if(temp2[1].charAt(i)=='F' || temp2[1].charAt(i)=='f'){
			sexCode="2";
			break;
		}
	}
	var temp3=temp2[0].split(strAgeSign);
	document.forms[0].strAge.value=temp3[0];
	if(document.forms[0].strAge.value<=0)
		document.forms[0].strAge.value=1;
	document.forms[0].strAgeUnit.value=strUnitCode; 
	document.forms[0].strSexCode.value=sexCode;
}

function validate1()
{
	var retVal = false;
	var hisValidator = new HISValidator("msApprovalTransBean");
	hisValidator.addValidation("strWardCode", "dontselect=0","Ward is mandatory field");
	hisValidator.addValidation("strRoomNo", "dontselect=0","Room is mandatory field");
	//hisValidator.addValidation("strBedCode", "dontselect=0","Bed is mandatory field");
	retVal = hisValidator.validate(); 
	if(retVal)
	{
		document.forms[0].strDeptUnitCode.value=document.forms[0].approvedCrNo.value.split("@")[4];
		document.forms[0].hmode.value = "SAVEALLOCATE";
		document.forms[0].submit();
	}else{
		hisValidator.clearAllValidations();
		return false;
	}
}

function submitFun()
{

var o= document.getElementsByName("strprivateward");
var o1=document.getElementsByName("strprivatewardValue");

o1[0].value="";
for(var i=0;i<o[0].length;i++)
{
 
 o1[0].value=o1[0].value+"<option value="+o[0][i].value+">"+o[0][i].text+"</option>";
}

document.forms[0].hmode.value ="GOWARD";
document.forms[0].submit();

}

function openPopup ()
{

//var hmaode ="PATLIST";
var url='MsApprovalTransCNT.cnt?hmode=PATLIST'
var featuresList = "width=600,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
 myWindow = window.open(url,'popupWindow',featuresList); 

}

function wardCombo()
{	
	getAgeSex();
	var mode="WARD";
	var url="MsApprovalTransCNT.cnt?hmode="+mode+"&strCombinedValue="+document.forms[0].approvedCrNo.value+"&strAge="+document.forms[0].strAge.value+"&strAgeCode="+document.forms[0].strAgeUnit.value+"&strSex="+document.forms[0].strSexCode.value+"&strPatCatCode="+document.forms[0].strPatCatCode.value+"&strWard="+document.forms[0].strWard.value;
	ajaxFunction(url,"3");
}

function roomCombo(mode)
{	
	var mode="ROOM";
	var url="MsApprovalTransCNT.cnt?hmode="+mode+"&modWard="+document.forms[0].strWardCode.value+"&strCombinedValue="+document.forms[0].approvedCrNo.value+"&strAge="+document.forms[0].strAge.value+"&strAgeCode="+document.forms[0].strAgeUnit.value+"&strSex="+document.forms[0].strSexCode.value+"&strPatCatCode="+document.forms[0].strPatCatCode.value;
	ajaxFunction(url,"1");
}

function bedCombo(mode,these)
{
	if(mode=="BED")
	{
		var mode="BED";
		var url="MsApprovalTransCNT.cnt?hmode="+mode+
		"&roomCode="+document.forms[0].strRoomNo.value +
		"&wardCode="+document.forms[0].strWardCode.value+
		"&deptUnitCode="+document.forms[0].approvedCrNo.value.split("@")[4];
		ajaxFunction(url,"2");
	}
}

function getAjaxResponse(res,mode)
{
	if(res.split("####")[0]=="Error")
	{
		document.getElementById("errMsg").innerHTML=res.split("####")[1];		
	}
	else if(mode=="1")
	{
		var objVal = document.getElementById("roomid");
    	//objVal.innerHTML = "<select name = 'strRoomNo' onChange=\"bedCombo('BED',this);\" >" + res + "</select>";
    	objVal.innerHTML = "<select name = 'strRoomNo'>" + res + "</select>";
    	var objVal1 = document.getElementById("bedid");
    	objVal1.innerHTML = "<select name = 'strBedCode'><option value='0'>Select Value</option></select>";
    }
    else if(mode=="2") 
    {	
		var objVal = document.getElementById("bedid");
		objVal.innerHTML = "<select name = 'strBedCode'>"+res+ "</select>";
	} 
	else if (mode=="3")
	{
		var objVal = document.getElementById("strprivatewardId");
    	objVal.innerHTML = "<select name='strWardCode' class='comboNormal' onchange='roomCombo()'>"+res+"</select>";
    	roomCombo('ROOM');
    	
    	/* var objVal = document.getElementById("roomid");
    	objVal.innerHTML = "<select name = 'strRoomNo' onChange=\"bedCombo('BED');\" ><option value='0'>Select Value</option></select>";
    	
    	var objVal1 = document.getElementById("bedid");
    	objVal1.innerHTML = "<select name = 'strBedCode'><option value='0'>Select Value</option></select>"; */
	}
}

function goFuncOnEnter(eve)
{
		if(eve.keyCode == 13)
		{
				var hisValidator = new HISValidator("msApprovalTransBean");
					hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
			 	var retVal = hisValidator.validate();
			 if(retVal)
			  {
	 			document.forms[0].hmode.value="GOWARD";
	 			document.forms[0].submit();
			}
	 	}else{
			return false;
		}
	}
function clear1(){
	/*document.getElementById("roomid").innerHTML="<select name = 'strRoomNo' onChange=\"bedCombo('BED',this);\" ><option value=\"0\">Select Value</option></select>";
	document.getElementById("bedid").innerHTML="<select name = 'strBedCode' onChange=\"bedCombo('BED');\" ><option value=\"0\">Select Value</option></select>";
	document.getElementById("strprivateward").innerHTML="<option value=\"0\">Select Value</option>";*/
}
function onLoadFunction(){
	document.getElementsByName("approvedCrNo")[0].checked=true;
	wardCombo();
}

function showPatEpDtl(flag)
{
	if(flag=='1')//show
	{
		document.getElementById("patEpDtl").style.display="";
		document.getElementById("minusPatEp").style.display="";
		document.getElementById("plusPatEp").style.display="none";
	}
	else
	{
		document.getElementById("patEpDtl").style.display="none";
		document.getElementById("minusPatEp").style.display="none";
		document.getElementById("plusPatEp").style.display="";
	}
}

</script>
</head>

<body>
<html:form action="/transactions/MsApprovalTransCNT" method="post">


<div class="normalMsg" id="normalMsg"></div>
<div class="errMsg" id="errMsg">${msApprovalTransBean.strErr}</div>
	<tag:tab tabLabel="Ward Allotment" selectedTab="FIRST" width="TABLEWIDTH"></tag:tab>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr class="HEADER"><td colspan="6">Ward Allotment</td></tr>
		<tr>
			<bean:write name="msApprovalTransBean" property="strPatApprovedList" filter="false" />
	</table>
	
	<div id="patEp">
	<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding="1px">
		<tr class="TITLE">
			<td colspan="4" width="100%">
			<div id="plusPatEp">
			<img alt="Show Patient Episode Details" src="../../hisglobal/images/plus.gif" style="cursor: pointer;"
			onclick="showPatEpDtl('1')">&nbsp;&nbsp;Episode Details</div>
			<div id="minusPatEp" style="display: none;">
			<img alt="Hide Patient Episode Details" src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			onclick="showPatEpDtl('2')">&nbsp;&nbsp;Episode Details</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="patEpDtl" style="display: none;">
	<table class='TABLEWIDTH' align='center' cellspacing='1px'
		cellpadding="1px">
		<tr>
			<td class="LABEL" width="25%">Episode No</td>
			<td class="CONTROL"  width="25%"><bean:write
				name="msApprovalTransBean" property="strEpisodeNumber" /></td>
				
			<td class="LABEL" width="25%">Consultant</td>
			<td class="CONTROL"  width="25%">
			<div id="divconsultant"><bean:write name="msApprovalTransBean"
				property="strConsultantName" /></div>
			</td>	
		</tr>
		<tr>
			<td class="LABEL" width="25%">Proposed Department/Unit</td>
			<td class="CONTROL" width="25%"><bean:write
				name="msApprovalTransBean" property="strDeptUnitCode" /></td>
			<td class="LABEL" width="25%">Proposed Ward</td>
			<td class="CONTROL" width="25%"><bean:write
				name="msApprovalTransBean" property="strWardName" /></td>	
		</tr>
		
		<tr>
			<td class="LABEL" width="25%"><bean:write
				name="msApprovalTransBean" property="strdateLable" /></td>
			<td width="25%" class="CONTROL"><font color="blue"><bean:write
				name="msApprovalTransBean" property="strPropAdminssionDate" /></font></td>
				
			<td class="LABEL" width="25%">Proposed Surgery Date</td>
			<td width="25%" class="CONTROL"><font color="blue"><bean:write
				name="msApprovalTransBean" property="strSurgeryDate" /></font></td>	
	   </tr>
	   
	   <tr>
			<td class="LABEL" width="25%">Admission Type</td>
			<td width="25%" class="CONTROL"><bean:write
				name="msApprovalTransBean" property="strAdmissionType" /></td>
				
			<td class="LABEL" width="25%">Approximately Length Of Stay</td>
			<td width="25%" class="CONTROL"><bean:write
				name="msApprovalTransBean" property="strLengthOfStay" /></td>	
	   </tr>
		
		<!--  <tr>
			<td class="LABEL" width="25%">Provisional Diagnosis</td>
			<td class="CONTROL" colspan="3" width="75%">
			<div id="divdiagnosis">
				<bean:write
				name="msApprovalTransBean" property="strProvisionDiagnosis" filter="false"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Consultant</td>
			<td class="CONTROL" colspan="3" width="25%">
			<div id="divconsultant"><bean:write name="msApprovalTransBean"
				property="strConsultantName" /></div>
			</td>
		</tr>-->
	</table>
	</div>
	
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr>
			<td CLASS='multiLabel' width='50%'><font color="red">*</font>Ward</td>
			<td CLASS='multiLabel' width='50%'><font color="red">*</font>Room</td>
			<td CLASS='multiLabel' width='40%' colspan="2" style="display:none"><font color="red">*</font>Bed</td>
		</tr>
		<tr>
			<td class="multiControl" width='15%'>
			<div id="strprivatewardId"><select name="strWardCode" class='comboNormal' onchange='roomCombo()'>
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td class='multiControl' width='15%'>
			<div id='roomid'><select name='strRoomNo'>
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td width='15%' class='multiControl' colspan="2" style="display:none">
			<div id='bedid'><select name='strBedCode'>
				<option value="0">Select Value</option>
			</select></div>
		</tr>
		<tr class="FOOTER">
			<td colspan="6">
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center">
	<tr>
		<td align="center">
		<!-- <img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/btn-sv.png" onClick="return validate1();"/>
		<img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/btn-clr.png" onclick="clear1();"/>
		<img style="cursor: hand; cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"/>
		 -->
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="clear1;"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
		</td>		
	</tr>
	</table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="strDeptUnitCode">
	<input type="hidden" name="strWard" value="${msApprovalTransBean.strWard}">
	<input type="hidden" name="1">
	<input type="hidden" name="h2mode">
	<input type="hidden" name="h3mode">
	<input type="hidden" name="h12mode">
	<input type="hidden" name="strAge">
	<input type="hidden" name="strAgeUnit">
	<input type="hidden" name="strSexCode">
	<input type="hidden" name="strprivatewardValue">
	<html:hidden name="msApprovalTransBean" property="hstrCrNo" />
	<html:hidden name="msApprovalTransBean" property="hstrAdviceNo" />
	
	<cmbPers:cmbPers></cmbPers:cmbPers>
	
</html:form>
<script>
onLoadFunction();
</script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>