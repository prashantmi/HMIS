<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Ward Criteria Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="javascript">
/*

function validate1(){	
	alert("valid");
	if(document.forms[0].strChkAge.checked==false)
	   document.forms[0].strChkAge.value=0; 
	   
	   alert("document.forms[0].strChkAge.value"+document.forms[0].strChkAge.value);
	    document.forms[0].hmode.value = "SAVE";
		document.forms[0].submit();
			
		
	}
	*/

	function validate1()
	{	
		var hisValidator = new HISValidator("wardcriteria"); 
		hisValidator.addValidation("strWardCmb","dontselect=0","Please select a value from Ward Combo");
		if(document.forms[0].strChkAge.checked==false)
		{
		   	hisValidator.addValidation("strGender","dontselect=0","Please select a value from Gender Combo");
		   	hisValidator.addValidation("strFromAge", "req", "From Age is a Mandatory Field");
		   	hisValidator.addValidation("strToAge", "req", "To Age is a Mandatory Field");
		   	hisValidator.addValidation("strFunit", "dontselect=0", "Please select a value from Unit");
	   }   
	   if(document.forms[0].strChkTreatment.checked==false)
	   {
			hisValidator.addValidation("strRCategory","dontselect=0","Please Select Category from the List");
	   }
	   if(document.forms[0].strChkDisease.checked==false)
	   {
			hisValidator.addValidation("strRDisease","dontselect=0","Please Select Disease  from the List");
	   }
		hisValidator.addValidation("strEffectiveFrom", "req","Effective From is a Mandatory Field");
	 	hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardcriteria.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
	 	hisValidator.addValidation("strRemark", "maxlen=50","Remark should not be greater than 50 Characters.");
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal) 
		{
			retVal = agecriteria();
			if(retVal) 
			{
				selectListRecords("strRCategory");
				selectListRecords("strRDisease");
				if(document.forms[0].strChkAge.checked && document.forms[0].strChkTreatment.checked && document.forms[0].strChkDisease.checked)
				{
					alert("Please Define atleast One Criteria for this Ward");
					return false;
				}
				else
				{
					document.forms[0].hmode.value = "SAVE";
					document.forms[0].submit();
				}
			}
		}
		return retVal;
	}
function divChk2()
{
	if(document.forms[0].strChkAge.checked==false)
	{
		document.getElementById("ageid").style.display="";
		document.getElementById("ageAllId").style.display="none";
		document.forms[0].strChkAge.value=0;
	}
	else
	{
		document.getElementById("ageid").style.display="none";
		document.getElementById("ageAllId").style.display="";
		document.forms[0].strChkAge.value=1; 
	}
}

function divChk()
{
	if(document.forms[0].strChkTreatment.checked==false)
	{
		document.forms[0].strChkTreatment.value =0;
		document.getElementById("treatmentid").style.display="block";
		document.getElementById("treatmentAllId").style.display="none";
	}
	else
	{
		document.forms[0].strChkTreatment.value =1;
		document.getElementById("treatmentid").style.display="none";
		document.getElementById("treatmentAllId").style.display="block";
	}
}
function divChk1()
{
	if(document.forms[0].strChkDisease.checked==false)
	{
		document.getElementById("diseaseid").style.display="block";
		document.getElementById("diseaseAllId").style.display="none";
		document.forms[0].strChkDisease.value =0;
	}
	else 
	{
		document.getElementById("diseaseid").style.display="none";
		document.getElementById("diseaseAllId").style.display="block";
	}
}
function agecriteria()
{
	var retVal = true;
	var flag = 0;
	var agelength = 1;
	var frmAgeArr;
	var toAgeArr;
	var sexArr;
	var unitArr;

	var tempFrmAge;
	var tempToAge;
	var tempSex;
	var tempUnit;
			
	var tempFrmAge1;
	var tempToAge1;
	var tempSex1;
	var tempUnit1;
	
	 if(document.forms[0].strChkAge.checked==false) 
	 {
	 	frmAgeArr = document.forms[0].strFromAge;
	 	toAgeArr = document.forms[0].strToAge;
	 	sexArr = document.forms[0].strGender;
	 	unitArr = document.forms[0].strFunit;
	 	if(!isNaN(frmAgeArr.length))
	 	{
	 		agelength = frmAgeArr.length;
		for( var i=0;i<agelength;i++) 
		{
			tempFrmAge = frmAgeArr[i].value;
			tempToAge = toAgeArr[i].value;
			tempSex = sexArr[i].value;
			tempUnit = unitArr[i].value;
			
			for( var j = 0;j<agelength;j++) 
			{
				//same row can not compare
				if(i != j) 
				{
					tempFrmAge1 = frmAgeArr[j].value;
					tempToAge1 = toAgeArr[j].value;
					tempSex1 = sexArr[j].value;
					tempUnit1 = unitArr[j].value;
					if((tempUnit == tempUnit1) && (tempSex == tempSex1))
					{
						//validate from age
						if(parseInt(tempFrmAge1,10) >= parseInt(tempFrmAge,10) && 
							parseInt(tempFrmAge1,10) <= parseInt(tempToAge,10)) 
						{
							flag = 1;
							retVal = false;
							break;
						}
						
						//validate to age
						if(parseInt(tempToAge1,10) >= parseInt(tempFrmAge,10) && 
							parseInt(tempToAge1,10) <= parseInt(tempToAge,10)) 
						{
							flag = 1;
							retVal = false;
							break;
						}
					}
				}
			} //inner for loop
			if(flag == 1) break;
		} 	
	 }
	 }
	 else
	 {
	 	retVal = true;
	 }
	 return retVal;
}
function clearData()
{
	if(!document.forms[0].strChkAge.checked)
	{
		document.getElementById("ageid").style.display="none";
		document.getElementById("ageAllId").style.display="block";		
	}
	if(!document.forms[0].strChkTreatment.checked)
	{
		document.getElementById("treatmentid").style.display="none";
		document.getElementById("treatmentAllId").style.display="block";		
		document.forms[0].strChkTreatment.checked=true;
		shiftAllToLeft("strLCategoryCmb","strRCategory",1);
	}
	if(!document.forms[0].strChkDisease.checked)
	{
		document.getElementById("diseaseid").style.display="none";
		document.getElementById("diseaseAllId").style.display="block";
		document.forms[0].strChkDisease.checked="true";
		shiftAllToLeft("strLDiseaseCmb","strRDisease",1);
	}
	document.forms[0].reset();
}
function getRoomValues()
{
	var hmode = "GETROOMVALUES"; 
	var url = "CNTWardCriteriaMst.cnt?hmode="+hmode+"&strWardCodeNoA="+document.forms[0].strWardCmb.value;
	ajaxFunction(url,"1");
}
function getAjaxResponse(res,mode)
{
	if(mode=="1")
		document.getElementById("roomId").innerHTML="<select name='strRoomNo' class='comboNormal'>"+res+"</select>";
}
</script>
</head>
<body onLoad="addRows(new Array('strGender','strFromAge','strToAge','strFunit'),new Array('s','t','t','s'),'1','1','I');">
<html:form action="/masters/CNTWardCriteriaMst">
	<div class="errMsg"><bean:write name="wardcriteria"
		property="strErrorMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="wardcriteria" property="strmsg" /></div>
	<div class="warningMsg"><bean:write name="wardcriteria"
		property="strwarnings" /></div>
	<tag:tab tabLabel="Add Ward Criteria" selectedTab="FIRST"
		align="center" width="TABLEWIDTH">
	</tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="6">Ward Criteria &gt;&gt; Add</td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Ward</td>
			<td class="CONTROL" width="25%" colspan="1"><select
				onchange="getRoomValues()" name="strWardCmb" class='comboNormal'>
				<bean:write name="wardcriteria" property="strWardCode"
					filter="false" />
			</select></td>
			<td class="LABEL" width="25%">Room Name</td>
			<td class="CONTROL" width="25%">
			<div id="roomId"><select name="strRoomNo" class='comboNormal'>
				<option value="0">Select All</option>
			</select></div>
			</td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td width="35%">Age/Gender Criteria</td>
			<td width="30%">
			<div align="right"><input type="checkbox" name="strChkAge"
				checked="checked" value="1" onClick="divChk2();"></div>
			</td>
			<td width="35%">
			<div id="ageAllId">All</div>
			</td>
		</tr>
	</table>
	<div id="ageid" style="display: none">
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="35%" class="multiLabel"><font color="red">*</font>Sex</td>
			<td width="15%" class="multiLabel"><font color="red">*</font>Age From</td>
			<td width="15%" class="multiLabel"><font color="red">*</font>Age To</td>
			<td width="25%" class="multiLabel"><font color="red">*</font>Unit</td>
			<td width="10%" class="multiLabel">
			<img src="../../hisglobal/images/plus.gif"
				onClick="addRows(new Array('strGender','strFromAge','strToAge','strFunit'),new Array('s','t','t','s'),'1','1','R');"></td>
		</tr>
	</table>
	<div id="id1"></div>
	</div>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td width="35%">Patient Category Criteria</td>
			<td width="30%">
			<div align="right"><input type="checkbox"
				name="strChkTreatment" checked="checked" value="1"
				onClick="divChk();"></div>
			</td>
			<td width="35%">
			<div id="treatmentAllId">All</div>
			</td>
		</tr>
	</table>
	<div id="treatmentid" style="display: none">
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td WIDTH="100%" class="LABEL" valign="top" colspan="4">
			<div align="left"><font color="red">*</font>Patient Category</div></td>
		</tr>
		<tr>		
			<td WIDTH="35%" class="CONTROL">
			<div id="lcategoryId" align="right">
			<html:select name="wardcriteria" property="strLCategoryCmb" size="8" style="width: 150px">
				<bean:write name="wardcriteria" property="strLCategory" filter="false" />
			</html:select></div>
			</td>
			<td width="30%" class="CONTROL">
			<div align="center">
			<img src="../../hisglobal/images/forward3.gif" width="35" height="31" align="top"
				onClick='shiftToRight("strLCategoryCmb","strRCategory",1);'>
			</div><br>
			<div align="center">
			<img src="../../hisglobal/images/back3.gif" width="35"
				height="31"
				onClick='shiftToLeft("strLCategoryCmb","strRCategory",1);'>
			</div>			
			</td>		
			<td width="35%" class="CONTROL">
			<div id="rcategoryId" align="left">
			<select name="strRCategory" size="8" multiple style="width: 150px">
			</select></div>
			</td>
		</tr>
	</table>
	</div>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td width="35%">Disease Type Criteria</td>
			<td width="30%">
			<div align="right"><input type="checkbox" name="strChkDisease"
				checked="checked" value="1" onClick="divChk1();"></div>
			</td>
			<td width="35%">
			<div id="diseaseAllId">All</div>
			</td>

		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="6" width="100%">
			<div id="diseaseid" style="display: none" align="center">
			<table cellpadding="1px" cellspacing="1px" width="100%">
				<tr>
					<td WIDTH="100%" class="LABEL" valign="top" colspan="6">
					<div align="left"><font color="red">*</font>Disease Type</div></td>
				</tr>
				<tr>	
					<td WIDTH="35%" class="CONTROL">
					<div id="lcategoryId" align="right">
					<html:select name="wardcriteria" property="strLDiseaseCmb" size="8" style="width: 150px">
						<bean:write name="wardcriteria" property="strLDisease" filter="false" />
					</html:select></div>
					</td>
					<td width="30%" class="CONTROL">
					<div align="center">
					<img src="../../hisglobal/images/forward3.gif" width="35"
						height="31" align="top" onClick='shiftToRight("strLDiseaseCmb","strRDisease",1);'>
					</div>
					<div align="center">
					<img src="../../hisglobal/images/back3.gif" width="35" height="31"
						onClick='shiftToLeft("strLDiseaseCmb","strRDisease",1);'>
					</div>
					</td>
					<td class="CONTROL" WIDTH="35%">
					<div id="rcategoryId" align="left">
					<select name="strRDisease" size="8" multiple style="width: 150px">
					</select></div>
					</td>
				</tr>				
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" nowrap><font color="red">*</font>Effective Date</td>
			<td class="CONTROL" colspan="5">
			<date:date
				name="strEffectiveFrom" value="${wardcriteria.strEffectiveFrom}"></date:date></td>
		</tr>
		<tr>
			<td width="15%" class="LABEL">Remarks</td>
			<td colspan="5" class="CONTROL"><textarea onkeyup="lTrim(this);" onblur="Trim(this);" name="strRemark"
				cols="25" rows="2"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="6"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td align="center">
			<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer"
				onClick="destroyMultiRow('1');  return validate1('SAVE'); " />
			<img src="../../hisglobal/images/btn-clr.png" style="cursor: pointer"
				onClick="clearData();">
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer"
				onClick="cancel('LIST');" /></td>
		</tr>
	</table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="hstrchkAge">
	<cmbPers:cmbPers/>
</html:form>
<jsp:include page="multirow_wardcriteriaMst_ipd.jsp"></jsp:include>
</body>
</html>