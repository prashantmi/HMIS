<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function getImageView(event,path)
{

	openDependentPopup(path,event,600,700,'yes');
}

	
</script>
	


<html:form action="/emrDesk">

	<body>
		<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
		<his:TitleTag name="Image Examination">
		</his:TitleTag>
		<logic:present name="<%=MrdConfig.PATIENT_EXAMINATION_IMAGES%>" >
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					
					<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="recordDate"/>
							</font>
						</div>
					</td>
					<td width="60%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="name"/>
								
							</font>
						</div>
					</td>
					
				</tr>
				<logic:iterate name="<%= MrdConfig.PATIENT_EXAMINATION_IMAGES%>" id="imageId" type="hisglobal.vo.OpdPatientImageDtlVO">
		
				<%
							String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
								+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + imageId.getDirPath() + "&" 
								+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + imageId.getDirPath()+ "&" 
								+ ServletsUtilityConfig.FILE_NAME + "=" + imageId.getImageFileName(); 
						%>
					<tr>
						
						<td class="tdfont" width="40%" >
							<div align="center">
							<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="imageId" property="entryDate" />
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" width="60%" >
							<div align="center">
							<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								
								<a style="cursor:pointer" tabindex="1" onclick="getImageView(event,'<%=path%>')" onkeypress="getImageView(event,'<%=path%>')"  >
										<U>	<bean:write name="imageId" property="imageName" /></U>
				      					</a>
								</font>
								</b>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>		
			</logic:present>
	
		<logic:notPresent name="<%=MrdConfig.PATIENT_EXAMINATION_IMAGES%>" >
		<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
		 		<b>
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noImageExaminationFound"/> </b>
					</font>
				</b>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
		</logic:notPresent>
		
		
		
	</body>	
	<his:status/>

</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 