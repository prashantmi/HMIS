<!DOCTYPE html>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta charset=UTF-8>
</head>
<body>
<form name="dropdown">
<div class="dropdown" id="dropdown1" style="display:none">
<bean:write name="AddServicesIPDTransBean"
				property="strGeneralTariffDetails" filter="false" />
</div>

<div class="dropdown" id="dropdown2" style="display:none">
<bean:write name="AddServicesIPDTransBean"
				property="strPrivateTariffDetails" filter="false" />
</div>

</form>
</body>
</html>