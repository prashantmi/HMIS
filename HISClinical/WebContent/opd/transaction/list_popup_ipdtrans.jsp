<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>List</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/tab.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/multirow.js"/>
<his:javascript src="/hisglobal/js/popup.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function getlist(){
		
		//alert(strUnitValue.selectIndex.value);
		if(document.forms[0].strUnitValue.selectedIndex!= 0){
			
			if(document.forms[0].strAdviceDate.value=='')
			{
				alert('Please select Date');
				document.forms[0].strAdviceDate.focus();
				return false;
			}
			if(document.forms[0].strDeparmentValue.selectedIndex == 0){
					
					alert("Please Select A Department");
					document.forms[0].strDeparmentValue.focus();
					return false;
			}
			
			myFunc('1');	
		}
		
		else{
			var objEle = document.getElementById("listDiv");
			objEle.innerHTML = "";
		}
	}

var pWindow ="";

function myFunc(mode){

	//alert(mode);
	if(mode == '1'){
		var hmode = "LISTDTL"; 
		var url = "/HISClinical/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&unitCode="+document.forms[0].unitCode.value+"&deptCode="+document.forms[0].deptCode.value;
		ajaxFunction(url,"1");
	
	}
}

</script>
</head>
<body>
<html:form action="/transactions/AdmissionAdviceTransCNT.cnt" method="post">
<div class="errMsg" id="errMsg"><bean:write name="advanceAdviceTransBean" property="strMsgString"/></div>
<div class="warningMsg"><bean:write name="advanceAdviceTransBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"></div>
		
 <table width="90%" align="center" bgcolor="black" cellspacing="1px" border="0">
  <tr class="HEADER"> 
    <td  colspan="8">List</td>
    
    <tr> 
    <td width="20%" class="LABEL"> <div align="right"></div>
     <div align="center">Date of Advice </div></td>
    <td width="18%" class="MultiCONTROL"><font color="blue" size="2"><bean:write name="advanceAdviceTransBean" property="strCtDate"/></font></td>
    <td width="18%" class="LABEL"><div align="center">Department</div></td>
    <td width="14%" class="MultiCONTROL"><font color="blue" size="2"><bean:write name="advanceAdviceTransBean" property="strDepartment"/></font></td>   
    <td width="12%" class="LABEL"><div align="center">Unit</div></td>
    <td width="10%" class="MultiCONTROL"><font color="blue" size="2"><bean:write name="advanceAdviceTransBean" property="strUnit"/></font></td>   
    </tr>
  </table>
  
   	<bean:write name="advanceAdviceTransBean" property="listView" filter="false"/>
   		
<table border="0" width="90%" align="center" bgcolor="black" cellspacing="1px">
	<tr class="FOOTER"> 
    <td colspan="8" ><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>	
  </table>
  <table border="0" width="90%" align="center"  cellspacing="1px">
		<tr>
		<td align="center"><img src="/HISClinical/hisglobal/images/Cancel.png"
			onClick="window.close();" /></td>
	
			
		</tr>
	</table>

<input type="hidden" name="hmode" />

</html:form>
</body>
</html>