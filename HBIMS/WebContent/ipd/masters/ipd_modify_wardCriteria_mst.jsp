<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<jsp:useBean id="beans" class="ipd.masters.vo.WardCriteriaMstVO" scope="request" />
<jsp:useBean id="hlp" class="ipd.masters.controller.hlp.WardMstCriteriaHLP" scope="request">
</jsp:useBean>
<%if (!(request.getAttribute("wardcriteria") == null))
				beans = (ipd.masters.vo.WardCriteriaMstVO) request
						.getAttribute("wardcriteria");
			String str = hlp.getMultiRow(beans, 1);
			%>
<html>
<head>
<meta charset=utf-8>
<title>Ward Criteria Master Modify Page</title>
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

function multInitFunc()
{
		var rowLength = <%=beans.getNtotalRows() %> 
		document.multirow.rowLength1.value = rowLength;
		document.multirow.rowIndex1.value = rowLength; 
}
function divChk2()
{
	if(document.forms[0].strChkAge.checked==false)
	{
		document.getElementById("ageid").style.display="block";
		document.getElementById("ageAllId").style.display="none";
		document.forms[0].strChkAge.value=0;
	}
	else 
	{
		document.getElementById("ageid").style.display="none";
		document.getElementById("ageAllId").style.display="block";
		document.forms[0].strChkAge.value=1; 
	}
}
function divChk()
{
	if(document.forms[0].strChkTreatment.checked==false)
	{
		document.getElementById("treatmentid").style.display="block";
		document.getElementById("treatmentAllId").style.display="none";
		document.forms[0].strChkTreatment.value = -1;
	}
	else 
	{
		document.getElementById("treatmentid").style.display="none";
		document.getElementById("treatmentAllId").style.display="block";
		document.forms[0].strChkTreatment.value = 1;
	}
}
function divChk1()
{
	if(document.forms[0].strChkDisease.checked==false)
	{
		document.getElementById("diseaseid").style.display="block";
		document.getElementById("diseaseAllId").style.display="none";
		document.forms[0].strChkDisease.value = -1;
	}
	else 
	{
		document.getElementById("diseaseid").style.display="none";
		document.getElementById("diseaseAllId").style.display="block";
		document.forms[0].strChkDisease.value = 1;
	}
}
function validate1(mode)
{	
	   var hisValidator = new HISValidator("wardcriteria"); 
	   if(document.forms[0].strChkAge.checked==false)
	   {
		   hisValidator.addValidation("strGender","dontselect=0","Please select a value from Gender  Combo");
		   hisValidator.addValidation("strFromAge", "req", "From Age is a Mandatory Field" );
		   hisValidator.addValidation("strToAge", "req", "To Age is a Mandatory Field" );
		   hisValidator.addValidation("strFunit", "dontselect=0", "Please select a value from Unit");
	   }
	   if(document.forms[0].strChkTreatment.checked==false)
	   {
	   	 	hisValidator.addValidation("strRCategory","dontselect=0","Please Select Treatment  from the List");
	   }
	   if(document.forms[0].strChkDisease.checked==false)
	   { 
			hisValidator.addValidation("strRDisease","dontselect=0","Please Select Disease  from the List");
	   }
			//hisValidator.addValidation("strEffectiveFrom", "req","Effective From is a Mandatory Field");
			//hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardcriteria.strCtDate}", "Effective Date should be Greater than Current Date" );
	 		//	hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardcriteria.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
		var retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
	
		if(retVal) 
		{
			retVal = agecriteria();
			if(retVal) 
			{
				var i=selectListRecords("strRCategory");		
				var j=selectListRecords("strRDisease");
				var obj=document.getElementsByName("strRCategory");
				if(document.forms[0].strChkAge.checked && document.forms[0].strChkTreatment.checked && document.forms[0].strChkDisease.checked)
				{
					alert("Please Define atleast One Criteria for this ward ")
					return false;
				}
				else
				{
					 document.forms[0].hmode.value = mode;
					 document.forms[0].submit();
				}
			}
		}
		return retVal;
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
	
	//
	var tempFrmAge;
	var tempToAge;
	var tempSex;
	var tempUnit;
			
	var tempFrmAge1;
	var tempToAge1;
	var tempSex1;
	var tempUnit1;
	var obj=document.getElementsByName("strFromAge");
	
	 if(document.forms[0].strChkAge.checked==false) 
	 {
	 	frmAgeArr = document.getElementsByName("strFromAge");
	 	toAgeArr = document.getElementsByName("strToAge");
	 	sexArr = document.getElementsByName("strGender");
	 	unitArr = document.getElementsByName("strFunit");
	 	if(!isNaN(frmAgeArr.length))
	 		agelength = frmAgeArr.length;
	 		else
	 		agelength=1;
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
							alert("From Age (" + tempFrmAge1 + ") is between from age (" + tempFrmAge + ") and to age (" + tempToAge + ")");
							break;
						}
						//validate to age
						if(parseInt(tempToAge1,10) >= parseInt(tempFrmAge,10) && 
							parseInt(tempToAge1,10) <= parseInt(tempToAge,10)) {
							flag = 1;
							retVal = false;
							alert("To Age (" + tempToAge1 + ") is between from age (" + tempFrmAge + ") and to age (" + tempToAge + ")");
							break;
						}
					}
				}
			} //inner for loop
			if(flag == 1) break;
		} 	
	 }
	 return retVal;
}
	
	
</script>
</head>
<body onLoad = "divChk2();divChk();divChk1();multInitFunc();">
<html:form action="/masters/CNTWardCriteriaMst">
	<div class="errMsg"><bean:write name="wardcriteria" property="strErrorMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="wardcriteria" property="strmsg"/></div>
	<div class="warningMsg"><bean:write name="wardcriteria" property="strwarnings"/></div>
	<tag:tab tabLabel="Modify Ward Criteria" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="6">Ward Criteria &gt;&gt;Modify</td>
		</tr>		
		<tr>
			<td width="25%" class="LABEL" nowrap>Ward Name</td>
			<td  width="25" class="CONTROL"><bean:write name ="wardcriteria" property ="strWardName" />
			<html:hidden name = "wardcriteria"  property ="strWardModi"/></td>
			<td width="25%" class="LABEL" nowrap>Room Desc</td>
			<td  width="25" class="CONTROL"><bean:write name ="wardcriteria" property ="strRoomName" />
			<html:hidden name = "wardcriteria"  property ="strRoomNo"/></td>
		</tr>
		</table>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
	<tr class="HEADER">
		<td nowrap width="35%"> Age/Gender Criteria</td>
		<td width="30%"><div align ="right"><% if(beans.getStrChkAge().equals("0")){ %>
		<input type ="checkbox" name ="strChkAge" onClick = "divChk2();"> <%} %>
		<% if(beans.getStrChkAge().equals("1")){ %>
		<input type ="checkbox" name ="strChkAge" checked ="checked" 
		onClick ="divChk2();addRows(new Array('strGender','strFromAge','strToAge','strFunit'),new Array('s','t','t','S'),'1','1','R');"><%} %>
		</div></td>
		<td width="35%"><div id="ageAllId">All</div>
		</td>
	</tr>
	</table>
	<div id ="ageid">
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
				<tr>
					<td width="35%" class="multiLabel"><font color="red">*</font>Sex</td>
					<td width="15%" class="multiLabel"><font color="red">*</font>Age From</td>
					<td width="15%" class="multiLabel"><font color="red">*</font>Age To</td>
					<td width="25%" class="multiLabel"><font color="red">*</font>Unit</td>
					<td width="10%" class="multiLabel">
					<img src="../../hisglobal/images/plus.gif"  style="cursor:hand;pointer:hand"
						onClick="addRows(new Array('strGender','strFromAge','strToAge','strFunit'),new Array('s','t','t','S'),'1','1','R');"></td>
				</tr>
			</table>
			<div id="id1"><%=str%></div>
			</div>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
	 <tr class="HEADER">
		<td nowrap width="35%">Patient Category Criteria</td>
		<td width="30%">
		<div align ="right">
		<% if(beans.getStrChkTreatment().equals("0")) %><input type ="checkbox" name = "strChkTreatment"   onClick = "divChk();">
		<% if(beans.getStrChkTreatment().equals("1")) %><input type ="checkbox" name = "strChkTreatment" checked ="checked"   onClick ="divChk();"></div></td>
		<td width="35%"><div id="treatmentAllId">All</div>
		</td>		
	</tr>		
	<tr>
		<td colspan ="6">
        <div id ="treatmentid"> 
		<table cellspacing="1px" cellpadding="1px" width="100%">
		<tr>
			<td WIDTH="100%" class="LABEL" valign="top" colspan="6">
			<div align="left"><font color="red">*</font>Patient Category</div></td>
		</tr>
		<tr>	
			<td WIDTH="35%" class="CONTROL">
			<div id="lcategoryId" align="right">			
				<html:select name = "wardcriteria" property ="strLCategoryCmb"   size="8" style="width: 150px">
				<bean:write name = "wardcriteria" property ="strLCategoryModi" filter ="false"/>
				</html:select>
			</div>
			</td>
			<td width="30%" class="CONTROL">
			<div align="center">
			<img src="../../hisglobal/images/forward3.gif" width="35"
				height="31" align="top"
				onClick='shiftToRight("strLCategoryCmb","strRCategory",1);'>
			</div><br>
			<div align="center">
			<img src="../../hisglobal/images/back3.gif" width="35" height="31"
				 onClick='shiftToLeft("strLCategoryCmb","strRCategory",1);'></div>
			<td width="35%" class="CONTROL">
			<div id="rcategoryId" align="left">
			<select name="strRCategory" size="8" multiple="multiple" style="width: 150px">
				<bean:write name = "wardcriteria" property ="strRCategoryModi" filter ="false"/>
			</select></div>
			</td>
		</tr>		
	   </table>
	   </div>
	   </td>
	   </tr>		
	   <tr class="HEADER">
		<td nowrap width="35%">Disease Type Criteria</td>
		<td width="30%">
		<div align ="right">
		<% if(beans.getStrChkDisease().equals("0")) %><input type ="checkbox" name = "strChkDisease" onClick = "divChk1();">
		<% if(beans.getStrChkDisease().equals("1")) %><input type ="checkbox" name = "strChkDisease" checked ="checked"   onClick ="divChk1();"></div>
		</td>
		<td><div id="diseaseAllId">All</div>
		</td>
		</tr>
		<tr>
		<td colspan ="6">
        <div id ="diseaseid"> 
		<table cellspacing="1px" cellpadding="1px" width="100%"> 
		<tr>
			<td WIDTH="100%" class="LABEL" valign="top" colspan="6">
			<div align="left"><font color="red">*</font>Disease Type</div></td>
		</tr>
		<tr>	
			<td WIDTH="35%" class="CONTROL">
			<div id="lcategoryId" align="right">			
			<html:select name="wardcriteria" property="strLDiseaseCmb"   size="8" style="width: 150px">
				<bean:write name="wardcriteria" property="strLDiseaseModi" filter ="false"/>
			</html:select>
			</div>
			</td>
			<td width="30%" class="CONTROL">
				<div align="center">
				<img src="../../hisglobal/images/forward3.gif" width="35" height="31" align="top"
					onClick='shiftToRight("strLDiseaseCmb","strRDisease",1);'>
				</div><br>
				<div align="center">
				<img src="../../hisglobal/images/back3.gif" width="35" height="31"
					 onClick='shiftToLeft("strLDiseaseCmb","strRDisease",1);'>
				</div>
			</td>
			<td class="CONTROL" WIDTH="35%">
			<div id="rcategoryId" align="left">
				<select name="strRDisease" size="8" multiple style="width: 150px">
				<bean:write name = "wardcriteria" property ="strRDiseaseModi" filter ="false"/>
			</select>
			</div>
			</td>
		</tr>		
	 </table>
	</div>
	</td>
	</tr>
	<tr>
			<td class="LABEL" nowrap>Effective Date</td>
			<td class="CONTROL" colspan ="5"><%=beans.getStrEffectiveFrom() %></td>
	</tr>
	<tr>
			<td width="15%" class="LABEL">Remarks</td>
			<td colspan="5" class="CONTROL">
			<textarea onkeyup="lTrim(this);" onblur="Trim(this);" name="strRemark" cols="25" rows="2" ><% if(beans.getStrRemark() != null)out.print(beans.getStrRemark());%></textarea></td>
	</tr>
	<tr class="FOOTER">
			<td colspan="6"><font size="2" color="red">*</font>Mandatory Fields</td>
	</tr>
	</table>	
	<table CLASS ="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
	<tr> 
		<td align="center">
		<img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer" onClick=" destroyMultiRow('1');return validate1('UPDATE');"/>
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');"/>
	   </td>
	</tr>
	</table>	
	<input type="hidden" name="chk" value="<%=beans.chk[0] %>">
	<input type="hidden" name="hmode" value="<%=beans.getHmode()%>">
	<input type="hidden" name="strEffectiveFrom" value="<%=beans.getStrEffectiveFrom()%>" />
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
<jsp:include page="multirow_wardcriteriaMst_ipd.jsp"></jsp:include>
</html>