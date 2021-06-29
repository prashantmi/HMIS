
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<meta charset=utf-8>
<title>Diagnosis Detail</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">


<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>

</head>
<body >
<html:form action="ipd/transactions/CNTMsApprovalTrans" method ="post">
	<div class="normalMsg" id="normalMsg"></div>	
<table class="TABLEWIDTH" align="center">
  <tr class="HEADER">
  <td colspan ="4">Diagnosis Details</td>
  </tr>
  <tr>
  <td width="25%" class="LABEL">Department </td>
  <td width="25%" class="CONTROL"><select name="strdepartment">
  <option value ='0'>Select Value</option>
  </select></td>
  <td width="25%" class="LABEL">Search</td>
   <td width="25%" class="CONTROL"><input type="text" name="strsearch" value =""></td>
  </tr>
  </table>			
  <table class="TABLEWIDTH" align="center">
  <tr>
  <td width="10%" class="multiLabel">Sr.No</td>
  <td width="60%" class="multiLabel">Diagnosis Name</td>
  <td width="40%" class="multiLabel">ICD Code</td>
  </tr>
   <tr class="FOOTER"> 
    <td colspan ="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><input name="ok" type="image" value="ok"
				src="../../hisglobal/images/btn-ok.png"
				onClick=" return validate1();" /></td>
			<td align="left">
			<img src="../../hisglobal/images/btn-ccl.png"
			onClick="window.close();" />
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
</html:form>

</body>
</html>