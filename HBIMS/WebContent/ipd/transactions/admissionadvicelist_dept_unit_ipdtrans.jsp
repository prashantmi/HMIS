<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Admission Advice List</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../ipd/js/admAdvList.js"></script>

<script type="text/javascript">
</script>

</head>
<body >
<html:form action="/transactions/AdmissionAdviceTransCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
<div class="popup" id="menu1" style="display:none;">
</div>		
<table class="TABLEWIDTH" align="center" class="TABLEWIDTH">
  <tr class="HEADER"> 
    <td colspan="4">Admission Advice list for Department and Unit</td>
  </tr>
  <tr>
  	<td class="LABEL">CR N.o.</td>
  	<td class="LABEL">Patient Name</td>
  	<td class="LABEL">Age/Sex</td>
  	<td class="LABEL">Ward N.o.</td>
  	<td class="LABEL">Prop Admission Date</td>
  </tr>
  <tr>
  	<td><div></div></td>
  </tr>
   <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img
				src="../../hisglobal/images/btn-ok.png" onClick="return bedStatusCheck_test();" /></td>
			<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
				onClick="window.close();" /></td>
		</tr>
	</table>
<a> </a>
	<input type="hidden" name="hmode" />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>