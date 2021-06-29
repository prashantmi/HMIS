<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="mrd.transaction.controller.fb.EmrCommonDeskFB"%>
<%@page import="hisglobal.vo.DocumentUploadDtlVO"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>

<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/> 
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/generic_patient_profile.js"/> --%>
<his:javascript src="/hisglobal/js/popup.js"/>
<%@page import="opd.OpdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>

function viewPrintProfile(e,profileStatus,url,profileHeader,serialNo,isPDF)
{
	var status="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS%>";

	var newUrl = url+"&profileHeader="+profileHeader+"&serialNo="+serialNo+"&isSinglePageFlag="+isPDF+"&isCallFromEMR="+"1";
	if(profileStatus==status)
	{
			alert("Please First Generate The Profile");
			return false;
	}
	else
	{
		openDependentPopup(createFHashAjaxQuery(newUrl),e,700,700,true);
	}
}

function getUploadedeDoc(event,docId,docType)
{
	//alert(docId);
	//alert("Hello OPEN Doc")
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open(createFHashAjaxQuery("/HISClinical/opd/opdDocumentArchival.cnt?hmode=VIEWDOC&documentCode="+docId+"&documentDirectoryPath="+docType),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}

</script>
</head>
<html:form action="/emrDesk">

	<body>
		<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
<his:TitleTag name="Generated Documents(Profiles )" >
</his:TitleTag>
	<logic:present  name="<%=MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY%>">
			<logic:notEmpty name="<%=MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY%>">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="SNo" />
						</font>
					</div>
				</td>
				
				<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="documentTitle" />
						</font>
					</div>
				</td>

			
			
			<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="type" />
						</font>
					</div>
				</td>

			
			
			<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="generationdate" />
						</font>
					</div>
				</td>

			

				<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
						</font>
					</div>
				</td>

			</tr>
			
			<logic:iterate id="patProfileVO" name="<%=MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY%>" indexId="idx" type="hisglobal.vo.PatientProfileDetailVO">
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><%=(idx.intValue()+1)%>.</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=patProfileVO.getProfileHeader()%>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=patProfileVO.getProfileHeader()%>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=patProfileVO.getEntryDate()%>
						</font>
					</div>
				</td>
				<%
					String displayFileServletURL = "/HISClinical/DisplayFileServlet?" + ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS 
							+ "&" + ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_LINUX 
							+ "&" + ServletsUtilityConfig.FILE_NAME + "=" + patProfileVO.getProfileId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
				 %>
 <%-- <% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId="+patProfileVO.getProfileId(); %>
				<td width="90%" class="tdfont">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<a style="cursor: pointer;" onclick="displayProfileFile('<%=url%>');"> --%>
						<td width="15%" class="tdfont">
						<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">	
						<% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId="+patProfileVO.getProfileId(); %>
				        <% String profileHeader=patProfileVO.getProfileHeader(); %>
				        <% String serialNo=patProfileVO.getSerialNo(); %>
				         <% String isPDF=patProfileVO.getIsSinglePageFlag(); %>
				         <%System.out.println("isSinglePageFlag:"+isPDF); %>
				        <%-- <a style="cursor: pointer;" onclick="viewPrintProfile(event,'<%=patProfileVO.getProfileStatus() %>','<%=url%>');">  --%> <!-- commented by Dheeraj  -->
						<a style="cursor: pointer;" onclick="viewPrintProfile(event,'<%=patProfileVO.getProfileStatus() %>','<%=url%>','<%=patProfileVO.getProfileHeader() %>','<%=serialNo%>','<%=isPDF%>');"> 
								VIEW
							</a>
						</font>
					</div>
				</td>

			</tr>
			
			</logic:iterate>
			
		</table>
	</his:ContentTag>
	</logic:notEmpty>	
			</logic:present>
			
		<logic:notPresent name="<%=MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY%>" >
		<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
		 		<b>
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noProfileFound"/> </b>
					</font>
				</b>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
		</logic:notPresent>
		
		
		<logic:present  name="<%=MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY%>">
			<logic:empty name="<%=MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY%>">
			<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
		 		<b>
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noProfileFound"/> </b>
					</font>
				</b>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
		</logic:empty>
		</logic:present>
		<his:TitleTag name="Uploaded Documents" >
</his:TitleTag>
	<logic:present  name="<%=MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY%>">
			<logic:notEmpty name="<%=MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY%>">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="SNo" />
						</font>
					</div>
				</td>
				
				<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="documentTitle" />
						</font>
					</div>
				</td>

			
			
			<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="type" />
						</font>
					</div>
				</td>

			
			
			<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="uploadedDate" />
						</font>
					</div>
				</td>

			

				<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
						</font>
					</div>
				</td>

			</tr>
			
			
			<logic:iterate id="docUploadVO" name="<%=MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY%>" indexId="idx" type="hisglobal.vo.DocumentUploadDtlVO">
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><%=(idx.intValue()+1)%>.</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=docUploadVO.getDocumentTitle()%>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=docUploadVO.getDocumentTitle()%>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=docUploadVO.getEntryDate()%>
						</font>
					</div>
				</td>
				<%-- <%
					String displayFileServletURL = "/HISClinical/DisplayFileServlet?" + ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS 
							+ "&" + ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_LINUX 
							+ "&" + ServletsUtilityConfig.FILE_NAME + "=" + patProfileVO.getProfileId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
				 %> --%>
 <%-- <% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId="+patProfileVO.getProfileId(); %>
				<td width="90%" class="tdfont">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<a style="cursor: pointer;" onclick="displayProfileFile('<%=url%>');"> --%>
						<td width="15%" class="tdfont">
						<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">	
						<%-- <% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId="+patProfileVO.getProfileId(); %>
				        <% String profileHeader=docUploadVO.getDocumentTitle(); %>
				        <% String serialNo=patProfileVO.getSerialNo(); %>
				         <% String isPDF=patProfileVO.getIsSinglePageFlag(); %> --%>
				        <%-- <a style="cursor: pointer;" onclick="viewPrintProfile(event,'<%=patProfileVO.getProfileStatus() %>','<%=url%>');">  --%> <!-- commented by Dheeraj  -->
						<a style="cursor: pointer;" onclick="getUploadedeDoc(event,'<%=docUploadVO.getDocumentCode()%>','<%=docUploadVO.getFileType() %>')"> 
								VIEW
							</a>
						</font>
					</div>
				</td>

			</tr>
			
			</logic:iterate>
			
		</table>
	</his:ContentTag>
	</logic:notEmpty>	
			</logic:present>
			
		<logic:notPresent name="<%=MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY%>" >
		<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
		 		<b>
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					No Document Found
					</font>
				</b>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
		</logic:notPresent>
		
		
		<logic:present  name="<%=MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY%>">
			<logic:empty name="<%=MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY%>">
			<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
		 		<b>
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					No Document Found
					</font>
				</b>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
		</logic:empty>
		</logic:present>
							
</html:form>
<html:hidden name="EmrCommonDeskFB" property="episodeCode" />	
</body>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 