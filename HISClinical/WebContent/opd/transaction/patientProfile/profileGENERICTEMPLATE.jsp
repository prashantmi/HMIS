<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="100%">
			<jsp:include page="/opd/transaction/patientProfile/patientProfileChartTableTempWiseTile.jsp" flush="true" />
		</td>
	</tr>
</table>

