<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/birt.tld" prefix="birt"%>

<html>
<head>
<meta charset=utf-8>
<title>Day End Process List</title>
<link href="../../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
</head>
<body>

<html:form name="dayEndTransBean"
	action="billing/transactions/DayEndTransCNT"
	type="billing.transactions.DayEndTransFB" method="post">

	<birt:report id="dayend" reportDesign="\billing\reports\dayend_report.rptdesign" height="800" width="900" format="pdf">
    <birt:param name="hospcode" value="${dayEndTransBean.strHospitalCode}"></birt:param>
	<birt:param name="summ_no" value="${dayEndTransBean.strSummNo}"></birt:param>
	<birt:param name="modeval" value="1"></birt:param>
	</birt:report>
	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>