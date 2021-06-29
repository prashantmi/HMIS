<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="medicalboard.transactions.controller.fb.MedicalExamInitiationFB"%>
<%@page import="hisglobal.vo.MedicalBoardRequisitionVO"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="investigation.InvestigationStaticConfigurator"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/medicalboard/js/medicalExamInitiation.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function displayFileInPopup(path)
{
	window.open(createFHashAjaxQuery(path),'DisplayFile','status=yes,scrollbars=yes,height='+screen.availHeight/2+',width='+screen.availWidth/2+ ',left=100,top=100,,dependent=yes,resizable=yes');
}
function validateIsRefer(){
	var selectedCandidate=document.getElementsByName("selectedCandidate")
	var isReferred=document.getElementsByName("isReferred")
	var candidateName=document.getElementsByName("candidateName")
	var valid=true;
	var count=0;
	for(var i=0;i<selectedCandidate.length;i++){
		if(selectedCandidate[i].checked){
			count++;
		}
	}
	for(var i=0;i<selectedCandidate.length;i++){
		if(selectedCandidate[i].checked){
			if(isReferred[i].value=='<%=MedicalBoardConfig.IS_REFERRED_YES%>' && count>1){
				valid=false;
				alert("Cannot refer. " + candidateName[i].value + "as already referred")
				break;
			}
		}
	}
	return valid
}

function validateIsInvRaised(){
	var selectedCandidate=document.getElementsByName("selectedCandidate")
	var isInvestigationRaised=document.getElementsByName("isInvestigationRaised")
	var candidateName=document.getElementsByName("candidateName")
	var valid=true;
	var count=0;
	for(var i=0;i<selectedCandidate.length;i++){
		if(selectedCandidate[i].checked){
			count++;
		}
	}
	for(var i=0;i<selectedCandidate.length;i++){
		if(selectedCandidate[i].checked){
			if(isInvestigationRaised[i].value=='<%=MedicalBoardConfig.IS_INVESTIGATION_RAISED_YES%>' && count>1){
				valid=false;
				alert("Cannot raise Investigation for " + candidateName[i].value + ". as already raised")
				break;
			}
		}
	}
	return valid
}
</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalExamInitiation">	   
		   
   <his:TitleTag name="Medical Exam Initiation"> 		
   </his:TitleTag>
   
<his:statusNew>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="50%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="certificateType"/></b>
			 </font>
	
		  </td>
		 <td width="50%" class="tdfont">
			<html:select name="medicalExamInitiationFB" property="certificateTypeID" tabindex="1" styleClass="regcbo" onchange="getCandidateList(this);">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>">
				<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 </tr>
	</table>	  

</his:statusNew>   
    
<his:statusList>
 	<% 	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination",fbPage);
			fbPage.setCurrentPage(((MedicalExamInitiationFB)request.getAttribute("medicalExamInitiationFB")).getCurrentPage());
			fbPage.setObjArrName(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			fbPage.setAppendInTitle("Candidate");
			fbPage.setMaxRecords(15);
		%>
	<logic:present name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>">
   <table width="100%">
   		<tr>
   			<td width="50%" class="tdfonthead">
   				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
   				<b><bean:message key="certificateType"/></b>
   				</font>
   			</td>
   			<td width="50%" class="tdfont">
   				<b><bean:write name="medicalExamInitiationFB" property="certificateTypeName"/></b>
   			</td>
   		</tr>
   	</table>
   <his:SubTitleTag name="Candidate List">
   </his:SubTitleTag>
    
   <his:PaginationTag name="fbPagination"></his:PaginationTag>
   	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <input type="checkbox" name="selectAll" tabindex="1" onclick="selectAllCandidate(this)">
			 </font>
			</div>
		  </td>
		 <td width="30%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="candidate"/> <bean:message key="name"/></b>
			 </font>
			</div>
		  </td>
		 <td width="10%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="gender/age"/></b>
			 </font>
			</div>
		  </td>
		  
		 <td width="15%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="requisitionDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="5%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="visitNo"/></b>
			 </font>
			</div>
		  </td>
		 <td width="10%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="referStatus"/></b>
			 </font>
			</div>
		  </td>
		  <!--
			 <td width="15%" class="tdfonthead">
				  <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 <b><bean:message key="raisedInvStatus"/></b>
					 </font>
				  </div>
			  </td>
		  -->
		  <td width="10%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b>External Refer Status</b>
			 </font>
			</div>
		  </td>
		  <td width="10%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b>Image</b>
			 </font>
			</div>
		  </td>
		  
		  
		</tr>
		<%	int startIndex= fbPage.getStartIndex();
			int endIndex=fbPage.getEndIndex();
			List requisitionVOList=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			for(int index=startIndex;index<=endIndex;index++){
				MedicalBoardRequisitionVO requisitionVO=(MedicalBoardRequisitionVO)requisitionVOList.get(index);
				pageContext.setAttribute("requisitionVO",requisitionVO);
		%>
		<%--<logic:iterate id="requisitionVO" name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>" type="hisglobal.vo.MedicalBoardRequisitionVO" indexId="index" offset="<%=startIndex+""%>" length="<%=endIndex+""%>"> --%>
		<tr>
		 <td class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:checkbox name="medicalExamInitiationFB" property="selectedCandidate" value='<%=index+""%>' tabindex="1"/>
			 <html:hidden name="medicalExamInitiationFB" property="isReferred" value="<%=requisitionVO.getIsReferred() %>"/>
			 <html:hidden name="medicalExamInitiationFB" property="isInvestigationRaised" value="<%=requisitionVO.getIsInvestigationRaised() %>"/>
			 </font>
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="patName"/>
			 <html:hidden name="medicalExamInitiationFB" property="candidateName" value="<%=requisitionVO.getPatName() %>"/>
			</div>
		  </td>
		 <td  class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="patAgeGender"/>
			</div>
		  </td>
		 <td  class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="entryDate"/>
			</div>
		  </td>
		 <td class="tdfont">
		   <div align="center">
				<bean:write name="requisitionVO" property="visitNo"/>
			</div>
		  </td>
		 <td class="tdfont">
		   <div align="center">
		   		<logic:equal name="requisitionVO" property="referStatus" value="1">
					<a onclick="getCandidateReferDetail('<bean:write name="requisitionVO" property="patCrNo"/>')"
					 onkeypress="if(event.keyCode==13) getCandidateReferDetail('<bean:write name="requisitionVO" property="patCrNo"/>')" 
					 tabindex="1" style="cursor:pointer" >View Status</a>
				</logic:equal>
			</div>
		  </td>
		  <!--
		 <td  class="tdfont">
		   <div align="center">
				<logic:equal name="requisitionVO" property="invStatus" value="1">
					<a onclick="getCandidateInvDetail('<%=requisitionVO.getPatCrNo()%>','<%=requisitionVO.getEpisodeCode() %>','<%=requisitionVO.getEpisodeVisitNo() %>')" 
					onkeypress="if(event.keyCode==13) getCandidateInvDetail('<%=requisitionVO.getPatCrNo()%>','<%=requisitionVO.getEpisodeCode() %>','<%=requisitionVO.getEpisodeVisitNo() %>')"
					tabindex="1" style="cursor:pointer">View Status</a>
				</logic:equal>
		  </div>
	    </td>
		  -->
		  <td  class="tdfont">
		   <div align="center">
				<logic:equal name="requisitionVO" property="extReferStatus" value="1">
					<a onclick="getCandidateExtReferDetail('<%=requisitionVO.getReqID()%>',event)" 
					onkeypress="if(event.keyCode==13) getCandidateExtReferDetail('<%=requisitionVO.getReqID()%>',event)"
					tabindex="1" style="cursor:pointer">View Status</a>
				</logic:equal>
			</div>
		  </td>
		  
		   <td  class="tdfont">
		   <logic:notEqual name="requisitionVO" property="isImageUploaded" value="1">   
								No Image Uploaded
			</logic:notEqual>
			<logic:equal name="requisitionVO" property="isImageUploaded" value="1"> 
				<bean:define id="strImgName" name="requisitionVO" property="imageFileNo" type="java.lang.String"></bean:define>
				<%
					String displayFileServletURL = ServletsUtilityConfig.SERVLET_DISPLAY_FTP_FILE_URL+ "?" 
							+ ServletsUtilityConfig.APP_SERVER_MAIN_FOLDER_WINDOWS + "=" + Config.PATH_APPLICATION_SERVER_FILE_STORAGE_WINDOWS 
							+ "&" + ServletsUtilityConfig.APP_SERVER_MAIN_FOLDER_LINUX + "=" + Config.PATH_APPLICATION_SERVER_FILE_STORAGE_LINUX 
							+ "&" + ServletsUtilityConfig.FTP_SERVER_MAIN_FOLDER + "=" + InvestigationStaticConfigurator.resultprintingftpaddress 
							+ "&" + ServletsUtilityConfig.FILE_TARGET_FOLDER + "=" + Config.TARGET_FOLDER_PATIENT_IMAGE 
						+ "&" + ServletsUtilityConfig.FILE_NAME + "="+ strImgName;
				 %>
				<a style="cursor: pointer;" onclick="displayFileInPopup('<%=displayFileServletURL%>');" title="View Patient Image">
					View Image
				</a>
			</logic:equal>
		   
		   
		   
		  
		  </td>
		  </tr>
		  <%} %>
		<%--</logic:iterate>--%>
	 </table>
	</his:ContentTag>
</logic:present>		
</his:statusList>	
	
	<his:ButtonToolBarTag>
		<div align="center">	
			<his:statusList>
				<img class="button" src='<his:path src="/hisglobal/images/Refer1.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) getReferMapping()" tabindex="1" onclick ="getReferMapping();">
				<!--
				<img class="button" src='<his:path src="/hisglobal/images/RaiseInvestigation.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) getInvestigationMapping()" tabindex="1" onclick ="getInvestigationMapping()">
				-->
				<img class="button" src='<his:path src="/hisglobal/images/btn-ext-refer.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) referExternal()" tabindex="1" onclick ="referExternal()">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	        </his:statusList>
	        <his:statusNew>
	        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
	
         </div>
     </his:ButtonToolBarTag>
     
   <html:hidden name="medicalExamInitiationFB" property="hmode"/>
   <html:hidden name="medicalExamInitiationFB" property="patCrNo"/>
   <html:hidden name="medicalExamInitiationFB" property="certificateTypeID"/>
   <html:hidden name="medicalExamInitiationFB" property="certificateTypeName"/>
   <html:hidden name="medicalExamInitiationFB" property="departmentUnitCode"/>
   <html:hidden name="medicalExamInitiationFB" property="referDept"/>
   <html:hidden name="medicalExamInitiationFB" property="labTestCode"/>
   <html:hidden name="medicalExamInitiationFB" property="selectedSampleCode"/>
   <html:hidden name="medicalExamInitiationFB" property="checkedItem"/>
   <html:hidden name="medicalExamInitiationFB" property="currentPage"/>
   <html:hidden name="medicalExamInitiationFB" property="extReferTo"/>
   <html:hidden name="medicalExamInitiationFB" property="referReason"/>
   <html:hidden name="medicalExamInitiationFB" property="reqID"/>
   
<his:status/>      
   </html:form>

</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
