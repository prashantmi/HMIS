<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<bean:define name="PhotoUploadFB" property="imageHeader" id="strImgPath" type="java.lang.String"></bean:define>
<%
	//String strImgPath="/image/showImage?"+imageIndex+"="+imageKey+"&"+key+"="+MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION;
%>
<body> 
	<img src='<his:path src="<%=strImgPath%>"/>' alt="no image found in session" >	
</body>