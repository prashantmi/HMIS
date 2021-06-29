<!-- 
/**
 * @author Pragya Sharma
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.*"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.OpdPatientImageDtlVO"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/opd/js/imageEditorFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function viewPrevious()
{
   	submitFormOnValidate(true,'VIEWPREVLOG');
}

function viewNext()
{
   	submitFormOnValidate(true,'VIEWNEXTLOG');
}
</script>

</head>

<body>
<html:form action="/opdImageExamTab">

	<his:statusNew>		
		<bean:define id="selLogIndex" name="OpdImageExamTabFB" property="selectedLogIndex" type="java.lang.String"></bean:define>
		<bean:define id="isNext" name="OpdImageExamTabFB" property="isNextLogPresent" type="java.lang.String"></bean:define>
		<%
			List lstImageLog = (List) session.getAttribute(OpdConfig.OPD_IMAGE_EXAM_IMAGES_VIEW_LOG_LIST_OF_IMAGE_DTL_VO);
			OpdPatientImageDtlVO imageDtlVO = (OpdPatientImageDtlVO) lstImageLog.get(Integer.parseInt(selLogIndex));
			String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
				+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS + "&" 
				+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX + "&" 
				+ ServletsUtilityConfig.FILE_NAME + "=" + imageDtlVO.getImageFileName(); 
		%>
		
		<table width="100%" cellspacing="1" cellpadding="0" align="center">
			<tr>
				<td width="5%">
					<%	if(imageDtlVO.getIsLogPresent()!=null && !imageDtlVO.getIsLogPresent().equals(OpdConfig.NO))	{	%>
						<img class="button" src='<his:path src="/hisglobal/images/arrdouble-left.png"/>'  style="cursor:pointer" tabindex="1" onclick ="viewPrevious();" onkeypress="if(event.keyCode==13) viewPrevious();" >
					<%	} %>
				</td>
				<td width="90%">
					<img src='<%=path%>' alt="No Image Found" title='<%=imageDtlVO.getImageName()%>' />
				</td>
				<td width="5%">
					<%	if(isNext!=null && !isNext.equals(OpdConfig.NO))	{	%>
						<img class="button" src='<his:path src="/hisglobal/images/arrdouble-right.png"/>'  style="cursor:pointer" tabindex="1" onclick ="viewNext();" onkeypress="if(event.keyCode==13) viewNext();" >
					<%	} %>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=imageDtlVO.getImageName()%>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=imageDtlVO.getEntryDate()%>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(imageDtlVO.getRemarks()!=null)?imageDtlVO.getRemarks():""%>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3" >
					<div align="center">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="self.close()" onkeypress="if(event.keyCode==13) self.close()" >
					</div>
				</td>
			</tr>
		</table>
	</his:statusNew>
		
	<his:statusUnsuccessfull>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="self.close()" onkeypress="if(event.keyCode==13) self.close()" >
		</his:ButtonToolBarTag>
	</his:statusUnsuccessfull>

<html:hidden name="OpdImageExamTabFB" property="hmode"/>
<html:hidden name="OpdImageExamTabFB" property="userSeatId"/>
<html:hidden name="OpdImageExamTabFB" property="departmentUnitCode"/>
<html:hidden name="OpdImageExamTabFB" property="patCrNo"/>
<html:hidden name="OpdImageExamTabFB" property="episodeCode"/>
<html:hidden name="OpdImageExamTabFB" property="episodeVisitNo"/>
<html:hidden name="OpdImageExamTabFB" property="admissionNo"/>

<html:hidden name="OpdImageExamTabFB" property="patSerialNo"/>
<html:hidden name="OpdImageExamTabFB" property="title"/>
<html:hidden name="OpdImageExamTabFB" property="imageFileName"/>
<html:hidden name="OpdImageExamTabFB" property="imageOutFileName"/>
<html:hidden name="OpdImageExamTabFB" property="colorDesc"/>
<html:hidden name="OpdImageExamTabFB" property="dirOutPath"/>
<html:hidden name="OpdImageExamTabFB" property="selectedSNo"/>
<html:hidden name="OpdImageExamTabFB" property="editingStatus"/>

<html:hidden name="OpdImageExamTabFB" property="isNextLogPresent"/>
<html:hidden name="OpdImageExamTabFB" property="selectedLogIndex"/>

</html:form>
</body>
</html>