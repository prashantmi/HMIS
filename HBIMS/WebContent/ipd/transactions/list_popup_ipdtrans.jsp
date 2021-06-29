
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>
<head>
<meta charset=utf-8>
<title>Admission Advice List</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/tab.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/multirow.js"/>
<his:javascript src="/hisglobal/js/popup.js"/>

<script type="text/javascript">
function getlist()
{
		if(document.forms[0].strUnitValue.selectedIndex!= 0)
		{
			if(document.forms[0].strAdviceDate.value=='')
			{
				alert('Please select Date');
				document.forms[0].strAdviceDate.focus();
				return false;
			}
			if(document.forms[0].strDeparmentValue.selectedIndex == 0)
			{
					alert("Please Select A Department");
					document.forms[0].strDeparmentValue.focus();
					return false;
			}
			myFunc('1');	
		}
		else
		{
			var objEle = document.getElementById("listDiv");
			objEle.innerHTML = "";
		}
	}

var pWindow ="";

function myFunc(mode)
{
	if(mode == '1')
	{
		var hmode = "LISTDTL"; 
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&unitCode="+document.forms[0].unitCode.value+"&deptCode="+document.forms[0].deptCode.value;
		ajaxFunction(url,"1");
	}
}


</script>
</head>
<body>
<html:form action="/ipd/transactions/AdmissionAdviceTransCNT.cnt" method="post">
<div class="errMsg" id="errMsg"><bean:write name="advanceAdviceTransBean" property="strMsgString"/></div>
<div class="warningMsg"><bean:write name="advanceAdviceTransBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"></div>
		
 <table width="100%" align="center" bgcolor="pink" cellspacing="1px" border="0">
  <tr class="HEADER"> 
    <td  colspan="8">Admission Advice List</td>    
   <!--   <tr> 
    <td width="32%" class="LABEL" colspan="1"><div align="right"></div>
     <div align="center">Date of Advice </div></td>
    <td width="68%" class="CONTROL" colspan="7"><font color="blue" size="2">
    <bean:write name="advanceAdviceTransBean" property="strCtDate"/></font></td>
    <tr>
    <td width="32%" class="LABEL"><div align="center">Department</div></td>
    <td width="25%" class="CONTROL"><font color="blue" size="2">
    <bean:write name="advanceAdviceTransBean" property="strDepartment"/></font></td>   
    <td width="25%" class="LABEL"><div align="center">Unit</div></td>
    <td width="18%" class="CONTROL"><font color="blue" size="2">
    <bean:write name="advanceAdviceTransBean" property="strUnit"/></font></td> -->   
    </tr>
  </table>  
   	<bean:write name="advanceAdviceTransBean" property="listView" filter="false"/>
<table border="0" width="100%" align="center" bgcolor="pink" cellspacing="1px">
	<tr class="HEADER"> 
    <td colspan="8"></td>
  </tr>	
  </table>
<table border="0" width="90%" align="center"  cellspacing="1px">
		<tr style="display:none">
		<td align="center" colspan="8">
		<!-- <img src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="goBack();" style="cursor: pointer;"/> -->
		<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
		</td>
		</tr>
</table>
<input type="hidden" name="hmode" />
</html:form>
</body>
</html>