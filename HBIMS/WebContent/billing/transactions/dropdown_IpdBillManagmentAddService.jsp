<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
</head>
<body>
<form name="dropdown">
<div class="dropdown" id="dropdown1" style="display:none">
<bean:write name="ipdBillManagementTransBean"
				property="strOfflineTariffDetails" filter="false" />
</div>
</form>
</body>
</html>