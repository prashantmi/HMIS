<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Leave Success Page IPD</title>
<link href="/AHIMS/ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="/AHIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/AHIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="/AHIMS/hisglobal/css/popup.css" rel="stylesheet" type="text/css">

</head>
<body>
<html:form action="/transactions/PatientLeaveCNT.cnt" method="post">
	<div class="normalMsg" id="normalMsg" align="center"><bean:write name="patientLeaveBean" property="strNormalMsgString" /></div>
</html:form>
</body>
</html>