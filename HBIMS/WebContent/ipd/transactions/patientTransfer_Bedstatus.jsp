<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>
<head>
<meta charset=utf-8>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" Type="text/css">
<title>Bed Status</title>
</head>
<body>

<html:form action="/transactions/PatientTransferTransCNT" method="post">
<div class="normalMsg" id="normalMsg"></div>
	<bean:write name="patientMoveTransBean" property="strBedProperty" filter="false" />
</html:form>
</body>

</html>
