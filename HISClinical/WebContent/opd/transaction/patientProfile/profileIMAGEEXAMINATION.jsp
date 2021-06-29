<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.OpdPatientImageDtlVO"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
	String deskMenuId = request.getParameter("DeskMenuID");
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma) session
			.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	if (proforma != null)
	{
		Map mpImage = (Map)proforma.mapProfileOptions.get(deskMenuId);
		List lstExamImages = (List) mpImage.get("lstOpdPatientImageDtlVO");
%>

<%@page import="java.util.Iterator"%>
<table width='100%'>
	<tr>
		<td width="100%" align="left">
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="examimagedtl"/>
				</font>
			</b>
		</td>
	</tr>
</table>

<table width='100%'>
	<tr>
		
		<td width='50%' align='center'>
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="image"/> <bean:message key="name"/>
				</font>
			</b>
		</td>
		<td width='50%' align='center'>
			<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="remarks"/>
				</font>
			</b>
		</td>
	</tr>
	<%
		Iterator lstExamImagesItr = lstExamImages.iterator();
		while (lstExamImagesItr.hasNext())
		{
			 OpdPatientImageDtlVO vo = (OpdPatientImageDtlVO)lstExamImagesItr.next();
	%>
	<tr>
		
		<td width='50%' align='center'>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<%=vo.getImageName()%>
			</font>
		</td>
		<td width='50%' align='center'>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<%=vo.getRemarks()%>
			</font>
		</td>
	</tr>
	<tr>
		<td width='100%' align='center' colspan='2'>

		<%
			String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" + ServletsUtilityConfig.FILE_PATH_WINDOWS + "="
			+ Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS + "&" + ServletsUtilityConfig.FILE_PATH_LINUX + "="
			+ Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX + "&" + ServletsUtilityConfig.FILE_NAME + "="
			+ vo.getImageFileName();
		
			/*String winPath = Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS;
			String linuxPath = Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX;
			String fileName = vo.getImageFileName();
			
			HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, winPath, linuxPath);
			fileUtil.readFile();*/
		%>
			<img id='<%=vo.getDirPath()+"/"+vo.getImageFileName()%>' alt='<%=vo.getImageName()%>' src='<%=path%>'  />
		</td>
	</tr>
	<%
		}
	%>
</table>
<%
	}
%>