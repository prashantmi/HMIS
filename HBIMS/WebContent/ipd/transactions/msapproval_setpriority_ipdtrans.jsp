<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
  <%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
  <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
  <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
  <%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>

<html>
<head>
<meta charset=utf-8>
<title>Set Priority</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>

<script type="text/javascript">
function ajaxWard()
{ 
  
  var url="MsApprovalTransCNT.cnt?hmode=ROOM&modWard="+document.forms[0].strprivatewardName.value;
//  alert("url=="+url);
   ajaxFunction(url,'1');

}
function ajaxFunRoom()
{ 
  
  var url="MsApprovalTransCNT.cnt?hmode=ROOMDETAIL&modWard="+document.forms[0].strprivatewardName.value+"&modRoom="+document.forms[0].strRoomName.value;
 // alert("url=="+url);
   ajaxFunction(url,'2');

}

function getAjaxResponse(res,mode)
{
//alert("res"+res);

var objVal;
if(mode=="1"){
objVal= document.getElementById("divRoom");
objVal.innerHTML = "<select name ='strRoomName' onChange = 'ajaxFunRoom();'  >" + res + "</select>";
}
if(mode=="2"){
objVal= document.getElementById("divroomdetail");
objVal.innerHTML =  res  ;

}
}
function validate1()
{ 

document.forms[0].hmode.value = "SAVEPRIORITY";
//alert("mode=="+document.forms[0].hmode.value);
document.forms[0].submit();
}
</script>
</head>

<body>
<html:form action="/transactions/MsApprovalTransCNT" method="post">
	<div class="normalMsg" id="normalMsg"></div>
<tag:tab tabLabel="Set Priority " selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table border="0" class="TABLEWIDTH" align="center">
  <tr>
    <td colspan="4">&nbsp;</td>
   
  </tr>
  <tr class= "HEADER">
    <td colspan="2">Patient Demographic Details</td>
    <td colspan="2">Date&nbsp;&nbsp;<bean:write name="msApprovalTransBean" property ="strCtDate"/></td>
	 
  </tr>
 <tr>
 <td width="25%" class="LABEL" >CR No</td>
 <td width="75%" class="CONTROL" colspan ="3"><bean:write name = "msApprovalTransBean" property = "strCrNo" />
  <html:hidden  name  = "msApprovalTransBean" property  = "hcrno"/>
  <html:hidden  name  = "msApprovalTransBean" property  = "hadvno"/>
  <html:hidden  name  = "msApprovalTransBean" property  = "hbkdate"/>
 </td>
  
 </tr>
		<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px"> -->
			<tr><td>
				<pDtl:patDtl crNo="${msApprovalTransBean.strCrNo}" address="true"></pDtl:patDtl>
			</td></tr>
		<!-- </table> -->
 
 <tr>
 <td width="21%"  class="LABEL">File No/Episode No </td>
 <td width="21%" class="CONTROL"><bean:write name = "msApprovalTransBean" property = "strEpisodeNumber" /></td>
 <td width="21%" class="LABEL" ><font color ="red">MLC No</font> </td>
 <td width="21%" class="CONTROL"><font color ="red"><bean:write name = "msApprovalTransBean" property = "strMlcNo" /></font></td>
 </tr>
 <tr>
 <td width="21%" class="LABEL" >Dept/Unit</td>
 <td width="21%" class="CONTROL"><bean:write name="msApprovalTransBean" property="strDeptUnit"/></td>
 <td width="21%" class="LABEL" >Adm Date Time</td>
 <td width="21%" class="CONTROL"><date:date
				name="strPropAdminssionDate"
				value="${msApprovalTransBean.strCtDate}"></date:date></td>
 </tr>
 <tr>
 <td width="21%"  class="LABEL">Ward</td>
 <td width="21%" class="CONTROL">&nbsp;</td>
 <td width="21%" class="LABEL" >Bed</td>
 <td width="21%" class="CONTROL">&nbsp;</td>
 </tr>
 
 <tr>
 <td width="21%"  CLASS="LABEL">Priority</td>
 <td width="21%" class="CONTROL"><select name = "strPriority" >
 <option value="0">Normal</option>
 <option value="1">High</option>
 <option value ="2">Top</option>
 <option value="3">Low</option>
 </select></td>
 <td width="21%" class="LABEL">&nbsp;</td>
 <td width="21%" class="CONTROL">&nbsp;</td>
 </tr>
<tr>
 <td width="21%" class="LABEL">Ward No</td>
 <td width="21%" class="CONTROL"><select name="strprivatewardName" onChange = 'ajaxWard();' >
 <bean:write name="msApprovalTransBean"
					property="strprivatewardValue" filter="false"  />
 </select></td>
 <td width="21%" class="LABEL">Room</td>
 <td width="21%" class="CONTROL"><div id ='divRoom'></div>
</td>
 </tr>
 <tr><td colspan='4' valign ='top'>
 <div id ="divroomdetail" >
 </div></td>
 </tr>
<tr>
<td width="21%" class="LABEL"><font color="red">*</font>Reason</td>
<td width="21%" class="CONTROL"><html:textarea 
				name="msApprovalTransBean" property="strPriorityReason" value="" /></td>
<td width="21%" class="LABEL" >Order By </td>
<td width="21%" class="CONTROL"><html:text name ="msApprovalTransBean" property ="strOrderBy" value =""/></td>
</tr>
<tr class="HEADER" ><td colspan="4">&nbsp;</td></tr>
</table>
        	
<table border="0" class="TABLEWIDTH" align="center">
  <tr>
   <td align="center"><img src="../../hisglobal/images/btn-sv.png" onClick="return validate1();"/>
    <img src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"/> </td>
   
  </tr>
</table>

 <input type ="hidden" name ="hmode">

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
