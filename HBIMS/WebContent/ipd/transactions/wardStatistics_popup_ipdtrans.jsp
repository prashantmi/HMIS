
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<html>
<head>
<meta charset=utf-8>
<title>Ward Statistics</title>

<link href="/AHIMS/ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="/AHIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/AHIMS/hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/tab.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/popup.js"/>


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

function getAjaxResponse(res,mode)
{
  	
}


function resize_popUp()
{
 var newHeight=parseInt(document.forms[0].strWin_Resize.value);
 window.resizeTo('500',newHeight);
}

</script>
</head>
<body>
<html:form action="/transactions/IpdCNT.cnt" method="post">

<div class="normalMsg" id="normalMsg"></div>
<table align="center" border="1" bordercolor="black" cellpadding="0" cellspacing="0" width="100%">
<tr> 
<td width="100%">
<table align="center" border="0" width="100%">
  <tr class="HEADER"> 
     <td colspan="4">Ward Statistics Details</td>
  </tr>
  <tr> 
     <td colspan="1" class="LABEL" width="25%">Department Name</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strDeptName"/></td>
     <td colspan="1" class="LABEL" width="25%">Ward Name</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strWardName"/></td>
  </tr>
  <tr> 
     <td colspan="1" class="LABEL" width="25%">Total Present Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalPresentPat"/></td>
     <td colspan="1" class="LABEL" width="25%">Total Admitted Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalAdmittedPat"/></td>
  </tr>  
  <tr> 
     <td colspan="1" class="LABEL" width="25%">Total New Admission</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalNewPat"/></td>
     <td colspan="1" class="LABEL" width="25%">Total Non-Accepted Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalNonAcceptedPat"/></td>
  </tr>
  <tr> 
     <td colspan="1" class="LABEL" width="25%">Total Transit Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalTransitPat"/></td>
     <td colspan="1" class="LABEL" width="25%">Total Transfer In Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalTransferInPat"/></td>
  </tr>
   <tr> 
     <td colspan="1" class="LABEL" width="25%">Total Transfer Out Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalTransferOutPat"/></td>
     <td colspan="1" class="LABEL" width="25%">Total Discharge Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalDischargePat"/></td>
  </tr>
   <tr> 
     <td colspan="1" class="LABEL" width="25%">Total Death Patient</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalDeathPat"/></td>
     <td colspan="1" class="LABEL" width="25%">Total Bed Available</td>
     <td colspan="1" class="CONTROL" width="25%"><bean:write name="ipdBean" property="strTotalBedAval"/></td>
  </tr>

</table>

<table align="center" border="0" class="TABLEWIDTH">	
	<tr>
		<td align="center" colspan="4">
			<img src="/AHIMS/hisglobal/images/btn-ccl.png" onClick="window.close();" style="cursor: pointer;" onkeypress="onPressingEnter(this,event)"/>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
<input type="hidden" name="hmode" />
</html:form>
</body>
</html>