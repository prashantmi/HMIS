
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 <%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>  
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
 
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<his:TransactionContainer>

<his:TitleTag name="Address Modification">
<select name="e">
	<option>Select Value</option>
</select>

</his:TitleTag>

<his:SubTitleTag name="Patient Address Details">

<select name="d">
	<option>Combo box</option>
</select>

</his:SubTitleTag>
<his:ContentTag>
content


</his:ContentTag>
<his:ButtonToolBarTag>
dsadsd
</his:ButtonToolBarTag>

</his:TransactionContainer>

</body>
</html>
