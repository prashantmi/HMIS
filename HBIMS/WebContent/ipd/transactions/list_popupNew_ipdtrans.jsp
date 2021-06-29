
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>
<head>
<title>Admission Advice List</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

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
<style type="text/css">
.navbar-expand-sm .navbar-nav .nav-link {
    padding-right: 11.5rem;
    padding-left: 0.5rem;
}

.navbar{
padding-bottom: 0;
padding-top: 0;
background-image: linear-gradient(to right,#49B2F3, #02629C);
}

a.md-wg-deal-link, a {
font-size: 16px;
color: white;

}

a.md-wg-deal-link, a:hover {

color: white;

}

</style>
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
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode+"&unitCode="+document.forms[0].unitCode.value+"&deptCode="+document.forms[0].deptCode.value;
		ajaxFunction(url,"1");
	}
}


</script>
</head>
<body>
<html:form action="/ipd/transactions/AdmissionAdviceTransBSCNT.cnt" method="post">
<div class="errMsg" id="errMsg"><bean:write name="advanceAdviceTransBean" property="strMsgString"/></div>
<div class="warningMsg"><bean:write name="advanceAdviceTransBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"></div>

<bean:write name="advanceAdviceTransBean" property="listView" filter="false"/>

<input type="hidden" name="hmode" />
</html:form>
</body>
</html>