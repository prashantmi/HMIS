<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="medicalboard.transactions.controller.fb.PostMedicalEntryFB"%>
<%@page import="hisglobal.vo.MedicalBoardRequisitionVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/medicalboard/js/postMedicalEntry.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<script>

</script>

<%try{ %>
<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>		
<his:TransactionContainer>
<html:form action="/postMedicalEntry">	   
<%-- 
<body onload="isDocPresent();">
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>		   
   <his:TitleTag name="Post Medical Entry"> 		
   </his:TitleTag>
   
<his:statusNew>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="certificateType"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			<html:select name="postMedicalEntryFB" property="certificateTypeID" tabindex="1" styleClass="regcbo" onchange="submitForm('GETBOARDLIST');">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>">
				<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="board"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			<html:select name="postMedicalEntryFB" property="boardNo" tabindex="1" styleClass="regcbo">
				<html:option value="%">All</html:option>
				<logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST %>">
				<html:options collection="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 </tr>
		 <tr>
		 	<td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="fromDate"/>
			 </font>
		  	</td>
		  	 <td width="25%" class="tdfont">
		  	 	<his:date name="fromDate" value="${postMedicalEntryFB.sysDate}" ></his:date>
		  	 </td>
		 	<td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="toDate"/>
			 </font>
		  	</td>
		  	 <td width="25%" class="tdfont">
		  	 	<his:date name="toDate" value="${postMedicalEntryFB.sysDate}"></his:date>
		  	 </td>
		 </tr>
	</table>	  
	</his:ContentTag>
</his:statusNew>   
    
<his:statusList>
 	<% 	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination",fbPage);
			fbPage.setCurrentPage(((PostMedicalEntryFB)request.getAttribute("postMedicalEntryFB")).getCurrentPage());
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
   				<b><bean:write name="postMedicalEntryFB" property="certificateTypeName"/></b>
   			</td>
   			<%-- <td width="25%" class="tdfonthead">
   				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
   				<b><bean:message key="board"/></b>
   				</font>
   			</td>
   			<td width="25%" class="tdfont">
   				<b><bean:write name="postMedicalEntryFB" property="boardName"/></b>
   			</td>--%>
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
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="crNo"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
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
		 <td width="15%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="lastMedicalVisit"/></b>
			 </font>
			</div>
		  </td>

		 <td width="10%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="board"/></b>
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
			 <html:radio name="postMedicalEntryFB" property="selectedCandidate" value='<%=index+"#"+requisitionVO.getPatCrNo()%>' onclick="getCanidateMedicalDetail(this)" onkeypress="if(event.keyCode==13 getCanidateMedicalDetail(this))" tabindex="1"/>
			 </font>
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="patCrNo"/>
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="patName"/>
			 <html:hidden name="postMedicalEntryFB" property="candidateName" value="<%=requisitionVO.getPatName() %>"/>
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
				<bean:write name="requisitionVO" property="visitDate"/>
			</div>
		  </td>
		 <td class="tdfont">
		   <div align="center">
				<bean:write name="requisitionVO" property="boardName"/>
			</div>
		  </td>

		  </tr>
		  <%} %>
		<%--</logic:iterate>--%>
	 </table>
	</his:ContentTag>
</logic:present>		
</his:statusList>	

<his:statusTransactionInProcess>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	<table width="100%" cellspacing="2" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="requisitionNo"/>
			 </font>
		  </td>
		 <td width="25%" class="tdfont">
		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  &nbsp;<b><bean:write name="postMedicalEntryFB" property="reqID"/></b>
			 </font>
		  </td>
		 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="certificateType"/>
			 </font>
		  </td>
		   <td width="25%" class="tdfont">
		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  &nbsp;<b><bean:write name="postMedicalEntryFB" property="certificateTypeName"/></b>
			 </font>
		  </td>
		</tr>
		<tr>
		 <td width="25%" class="tdfonthead">
		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="board"/>
			 </font>
		  </td>
		 <td width="25%" class="tdfont">
		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  &nbsp;<b><bean:write name="postMedicalEntryFB" property="boardName"/></b>
			 </font>
		  </td>
		 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="lastMedicalVisit"/>
			 </font>
		  </td>
		   <td width="25%" class="tdfont">
		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  &nbsp;<b><bean:write name="postMedicalEntryFB" property="lastVisitDate"/></b>
			 </font>
		  </td>
		  
		</tr>
		<tr>
		<td width="50%" class="tdfonthead" colspan="2">
			<div id="DocNotPresentDiv" style="display: none"> 
				<img class="button" src='<his:path src="/hisglobal/images/Red.png"/>' width="90px" height="18px" onclick="getScanDocPopup(false)"  onkeypress="if(event.keyCode==13) getScanDocPopup(false)">
			</div>
			<div id="DocPresentDiv" style="display: none">
				<img class="button" src='<his:path src="/hisglobal/images/Green.png"/>' width="90px" height="18px" style="cursor:pointer" tabindex="1" onclick="getScanDocPopup(true)" 
				onkeypress="if(event.keyCode==13) getScanDocPopup(true)">
			  </div>
		</td>
		 <td width="25%" class="tdfonthead">
			<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="lastCertNo"/></b>
			 </font>
		  </td>
		   <td width="25%" class="tdfont">
		  <font size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  &nbsp;<b><bean:write name="postMedicalEntryFB" property="lastCertNo"/></b>
			 </font>
		  </td>
		</tr>
		</table>
	<!-- --------------------refer detail------------------------------------ -->
	<logic:present name="<%=MedicalBoardConfig.EPISODE_REF_VO_LIST %>">
	<his:SubTitleTag name="Candidate Refer Detail">
	 	<table width="100%" border="0" cellspacing="1" cellpadding="0">  
		 <tr>
      		<td width="5%" align="right">
	      	 <div id="upArrow" style="display: block">
	      	 <img class="button"
							src="<his:path src='/hisglobal/images/arrow-down.png'/>" tabindex="1"
							border="0" style="cursor:pointer" 
							onkeypress="if(event.keyCode==13) showReferDtlDiv(1);" 
							onclick="showReferDtlDiv(1);" >
			</div>
	      	 <div id="downArrow" style="display: none">
	      	 <img class="button"
							src="<his:path src='/hisglobal/images/arrow-up.png'/>" tabindex="1"
							border="0" style="cursor:pointer" 
							onkeypress="if(event.keyCode==13) showReferDtlDiv(2);" 
							onclick="showReferDtlDiv(2);" >
			</div>
		   </td>
     	 </tr>
  	 	</table> 
	</his:SubTitleTag>
	<div id="referDtlDiv" style="display: none">
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="30%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="referToDeptUnit"/></b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="referDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="20%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="visit"/> <bean:message key="status"/></b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="visit"/> <bean:message key="date"/></b>
			 </font>
			</div>
		  </td>
		</tr>
		
		<logic:iterate id="episodeRefVO" name="<%=MedicalBoardConfig.EPISODE_REF_VO_LIST %>" type="hisglobal.vo.EpisodeRefDtlVO" indexId="index">
		<tr>
		 <td class="tdfont">
		 	<div align="center">
		 		<bean:write name="episodeRefVO" property="toDepartment"/>
		 		<%if(episodeRefVO.getToDepartmentUnit()!=null && !episodeRefVO.getToDepartmentUnit().equals("")){ %>
		 		/ <bean:write name="episodeRefVO" property="toDepartmentUnit"/>
		 		<%} %> 
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center">
				<bean:write name="episodeRefVO" property="reffDateTime"/> 
			</div>
		  </td>
		 <td class="tdfont">
		  	<div align="center">
		  	 <%if(episodeRefVO.getEpisodeReferAcceptDate()==null){
		  	 	out.println("Visit pending");
		  	 }	 
		  	 else{
		  	 	out.println("Visit Done");
		  	 } %>	
			 </div>
		  </td>
		  <td class="tdfont">
		  <div align="center">
				<bean:write name="episodeRefVO" property="episodeReferAcceptDate"/> 	
			</div>
		  </td>
		  </tr>
		</logic:iterate>
		
	 </table>
	</his:ContentTag>
	</div>
	</logic:present>
	<!-- ---------------------------Investigation Detail------------------------------------ -->		
	<logic:present name="<%=MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST %>">
	<his:SubTitleTag name="Investigation Detail">
	<table width="100%" border="0" cellspacing="1" cellpadding="0">  
		 <tr>
      		<td width="5%" align="right">
	      	 <div id="upArrow1" style="display: block">
	      	 <img class="button"
							src="<his:path src='/hisglobal/images/arrow-down.png'/>" tabindex="1"
							border="0" style="cursor:pointer" 
							onkeypress="if(event.keyCode==13) showInvDtlDiv(1);" 
							onclick="showInvDtlDiv(1);" >
			</div>
	      	 <div id="downArrow1" style="display: none">
	      	 <img class="button"
							src="<his:path src='/hisglobal/images/arrow-up.png'/>" tabindex="1"
							border="0" style="cursor:pointer" 
							onkeypress="if(event.keyCode==13) showInvDtlDiv(2);" 
							onclick="showInvDtlDiv(2);" >
			</div>
		   </td>
     	 </tr>
  	 	</table> 
	</his:SubTitleTag>
	<div id="invDetailDiv" style="display: none;">
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="30%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="testName"/></b>
			 </font>
			</div>
		  </td>
		 <td width="20%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="requestDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="30%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="test"/> <bean:message key="status"/></b>
			 </font>
			</div>
		  </td>
		 <td width="20%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="result"/> <bean:message key="date"/></b>
			 </font>
			</div>
		  </td>
		</tr>
		
		<logic:iterate id="invRequisitionVO" name="<%=MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST %>" type="hisglobal.vo.MBInvestigationRequisitionDetailVO" indexId="index">
		<tr>
		 <td class="tdfont">
		 	<div align="center">
		  		<bean:write name="invRequisitionVO" property="testName"/>
		 	</div>
		  </td>
		 <td class="tdfont">
		 	<div align="center">
				<bean:write name="invRequisitionVO" property="reqDate"/> 
			</div>
		  </td>
		 <td class="tdfont">
		  	<div align="center">
		  		<bean:write name="invRequisitionVO" property="testStatus"/> 
			 </div>
		  </td>
		  <td class="tdfont">
		  	<div align="center">
		  		<bean:write name="invRequisitionVO" property="resultDate"/> 
			</div>
		  </td>
		  </tr>
		</logic:iterate>
		
	 </table>
	</his:ContentTag>
	</div>
</logic:present>		
	<!-- -------------------------end investigation detail-------------------------------- -->
<!-- --------------------external refer detail------------------------------------ -->
	<logic:present name="<%=MedicalBoardConfig.EPISODE_EXT_REF_VO_LIST %>">
	<his:SubTitleTag name="Candidate External Refer Detail">
	 	<table width="100%" border="0" cellspacing="1" cellpadding="0">  
		 <tr>
      		<td width="5%" align="right">
	      	 <div id="upArrow2" style="display: block">
	      	 <img class="button"
							src="<his:path src='/hisglobal/images/arrow-down.png'/>" tabindex="1"
							border="0" style="cursor:pointer" 
							onkeypress="if(event.keyCode==13) showExtReferDtlDiv(1);" 
							onclick="showExtReferDtlDiv(1);" >
			</div>
	      	 <div id="downArrow2" style="display: none">
	      	 <img class="button"
							src="<his:path src='/hisglobal/images/arrow-up.png'/>" tabindex="1"
							border="0" style="cursor:pointer" 
							onkeypress="if(event.keyCode==13) showExtReferDtlDiv(2);" 
							onclick="showExtReferDtlDiv(2);" >
			</div>
		   </td>
     	 </tr>
  	 	</table> 
	</his:SubTitleTag>
	<div id="extReferDtlDiv" style="display: none">
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="35%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Refer To</b>
			 </font>
			</div>
		  </td>
		 <td width="35%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Refer Reason</b>
			 </font>
			</div>
		  </td>
		 <td width="30%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Date</b>
			 </font>
			</div>
		  </td>
		
		</tr>
		
		<logic:iterate id="extRefVO" name="<%=MedicalBoardConfig.EPISODE_EXT_REF_VO_LIST %>" type="hisglobal.vo.MedicalBoardExternalReferVO" indexId="index">
		<tr>
		 <td class="tdfont">
		 	<div align="center">
		 		<bean:write name="extRefVO" property="extReferTo"/>
		 		 
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center">
				<bean:write name="extRefVO" property="referReason"/> 
			</div>
		  </td>
		   <td class="tdfont">
		  <div align="center">
				<bean:write name="extRefVO" property="entryDate"/> 	
			</div>
		  </td>
		  </tr>
		</logic:iterate>
		
	 </table>
	</his:ContentTag>
	</div>
	</logic:present>
	<!-- --------------------end external refer detail------------------------------------ -->
	
	<his:SubTitleTag name="CheckList Detail">
	</his:SubTitleTag>
	 <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <input type="checkbox" name="selectAll" tabindex="1" onclick="selectAllChecklist(this)">
			 </font>
			</div>
		  </td>
		 <td width="45%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="checklist"/></b>
			 </font>
			</div>
		  </td>
		 <td width="50%" class="tdfonthead">
		  <div align="center" >
<!--		  	<b><font color="#FF0000">*</font></b>-->
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="remarks"/></b>
			 </font>
			</div>
		  </td>
		  </tr>
		<!------------------------Detail of checklist already Added---------------------- -->
		  <logic:present name="<%=MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST %>">
		   <logic:iterate id="checklistVO" name="<%=MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST%>" type="hisglobal.vo.MedicalBoardChecklistVO">
		  	 <tr>
		  	 <td width="5%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <input type="checkbox" name="checkList" disabled="disabled" checked="checked">
				 </font>
				</div>
			  </td>
		  	 <td width="45%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <bean:write name="checklistVO" property="checklist"/>
				 </font>
				</div>
			  </td>
		  	 <td width="50%" class="tdfont">
			  	<div align="center" >
			  		<bean:write name="checklistVO" property="remarks"/>
			  	</div>
			  </td>
		  	</tr>
		  	</logic:iterate>
		  </logic:present>	
		  
		   <!---------------------------------Checklist Detail---------------------------------  -->
		   
		   <logic:present name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST %>">
		  <logic:iterate id="checklistVO" name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST%>" type="hisglobal.vo.MedicalBoardChecklistVO" indexId="index">
		  	 <tr>
		  	 <td width="5%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <input type="checkbox" name="selectedCheckList" tabindex="1" value='<%=index +"#"+checklistVO.getChecklistID() +"#"+checklistVO.getIsCompulsory()%>' onclick="enableChecklist(this)" >
				 </font>
				</div>
			  </td>
		  	 <td width="45%" class="tdfont">
		  	  <div align="center" >
			  	<%String fontColor="#000000"; %>
					<logic:equal name="checklistVO" property="isCompulsory" value="<%=MedicalBoardConfig.COMPULSORY_AT_TIME_OF_POST_ENTRY %>">
						<%fontColor="#990000"; %>
					</logic:equal>
					<font color="<%=fontColor %>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:write name="checklistVO" property="checklist"/>
					</font>
				<input type="hidden" name="checkListName" value="<%=checklistVO.getChecklist() %>"/>	
			  </div>
			 </td>
		  	 <td width="50%" class="tdfont">
			  <div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <html:text name="postMedicalEntryFB" property="remarks" size="40" maxlength="50" tabindex="1" disabled="true" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				 </font>
				</div>
			  </td>
		  	</tr>
		  </logic:iterate>
		  </logic:present>
		 </table>
	</his:ContentTag>
	
	<his:ContentTag>
		<table width="100%">
			<tr>
			 	<td width="50%" class="tdfonthead">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="isMedicalPerformed"/>
					</font>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:radio name="postMedicalEntryFB" property="medicalPerformed" value="<%=MedicalBoardConfig.IS_MEDICAL_PERFORMED_YES %>" onchange="setEntryOption(this)" onkeypress="if(event.keyCode==13) setEntryOption(this);" tabindex="1"></html:radio>
						<b><bean:message key="yes"/></b>
						<html:radio name="postMedicalEntryFB" property="medicalPerformed" value="<%=MedicalBoardConfig.IS_MEDICAL_PERFORMED_NO%>" onchange="setEntryOption(this)" onkeypress="if(event.keyCode==13) setEntryOption(this);" tabindex="1"></html:radio>
						<b><bean:message key="no"/></b>
					</font>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<div id="nextVisitDiv" style="display: none">
		<his:SubTitleTag name="Next Visit Detail">
		</his:SubTitleTag>	
	
		<his:ContentTag>
		<table width="100%">
			<tr>
				<td width="50%" class="tdfonthead">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="nextVisitDate"/>
					</font>
				</td>
				<td width="50%" class="tdfont">
					<his:date name="examDate"></his:date>
				  	<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>'  style="cursor:pointer" border="1" 
		   				tabindex="1" onkeypress="if(event.keyCode==13) getSchedule()" onclick="getSchedule()" title="Click to select Date">

				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="reason"/>
					</font>
				</td>
				<td width="50%" class="tdfont">
					<html:textarea name="postMedicalEntryFB" property="reason" cols="40" tabindex="1" onkeypress="return CheckMaxLength(event,this,100,1)"/>
				</td>
			</tr>
		</table>
		</his:ContentTag>		
	</div>
	
	<div id="certificateResultDiv" style="display: none">
		<logic:present name="<%=MedicalBoardConfig.ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID %>">
		<logic:notEmpty name="<%=MedicalBoardConfig.ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID %>">
		<his:SubTitleTag name="Board Opinion Detail">
		</his:SubTitleTag>	
		<table width="100%" cellspacing="1">
			<tr>
				<td width="33%" class="tdfonthead" align="center">
					<div align="center">
						<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="boardMember"/>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead" align="center">
					<div align="center">
						<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="opinion"/>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead" align="center">
					<div align="center">
						<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="opinionRemark"/>
						</font>
					</div>	
				</td>
			</tr>
		<logic:iterate id="empEntryObj" name="<%=MedicalBoardConfig.ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID%>" type="hisglobal.utility.Entry" indexId="index">
			<tr>
				<td width="33%" class="tdfont">
					<div align="center">
						<bean:write name="empEntryObj" property="label"/>
						<html:hidden name="postMedicalEntryFB" property="empIdArray" value="<%=empEntryObj.getValue() %>"/>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:select name="postMedicalEntryFB" property="opinionCodeArray" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID%>" >
								<html:options collection="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID%>" property="value" labelProperty="label"/>
								</logic:present>
							</html:select>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:text property="opinionRemarkArray" name="postMedicalEntryFB" maxlength="100" tabindex="1"></html:text>
					</div>
				</td>
			</tr>
		</logic:iterate>	
		</table>	
		</logic:notEmpty>
		</logic:present>	
		
		<logic:empty name="<%=MedicalBoardConfig.ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID %>">
		<div id="noEmployeeActiveDiv">
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		No Active Employee is present corresponding to the Board 
		</font>
		</div>
		</logic:empty>
		
		<logic:notEmpty name="<%=MedicalBoardConfig.ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID %>">

		<his:ContentTag>
		<his:SubTitleTag name="" >
			<div align="left">
				<logic:equal value="<%=MedicalBoardConfig.ONLINE %>" name="postMedicalEntryFB" property="onlineOfflineFlag">
					<html:checkbox name="postMedicalEntryFB" property="isVerified" tabindex="1" onclick="showApproveDiv(this)" value="1"></html:checkbox>
				</logic:equal>
			
				<bean:message key="verificationDetail" />
			</div>
			
			
		</his:SubTitleTag>	
		<logic:equal value="<%=MedicalBoardConfig.OFFLINE %>" name="postMedicalEntryFB" property="onlineOfflineFlag">
			<table width="100%" cellspacing="1">	
<!--			<tr>-->
<!--				<td width="50%" class="tdfonthead" colspan="2" align="center">-->
<!--				<div align="center">-->
<!--					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
<!--					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--						<html:checkbox name="postMedicalEntryFB" property="isApproved" tabindex="1" onclick="setIsApproved(this)"></html:checkbox>-->
<!--						Is Result Approved-->
<!--					</font>-->
<!--				</div>	-->
<!--				</td>-->
<!--			</tr>-->
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="approvedBy"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
				<html:select name="postMedicalEntryFB" property="approvedBy" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.MB_CONSULTANT_LIST %>">
					<html:options collection="<%=MedicalBoardConfig.MB_CONSULTANT_LIST %>" labelProperty="label" property="value"/>
					</logic:present>
				</html:select>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="approvedDate"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<his:date name="approvedDate" value="<%=sysdate %>"></his:date>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="final"/> <bean:message key="opinion"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
				<html:select name="postMedicalEntryFB" property="opinionCode" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID %>">
					<html:options collection="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID %>" labelProperty="label" property="value"/>
					</logic:present>
				</html:select>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="final"/> <bean:message key="remark"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<html:textarea name="postMedicalEntryFB" property="finalRemark" cols="50" tabindex="1" onkeypress="return CheckMaxLength(event,this,500,1)"/>
				</td>
			</tr>
		</table>
		</logic:equal>
		<div id="approveDetailDiv" style="display: none">
		<table width="100%" cellspacing="1">	
<!--			<tr>-->
<!--				<td width="50%" class="tdfonthead" colspan="2" align="center">-->
<!--				<div align="center">-->
<!--					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>-->
<!--					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--						<html:checkbox name="postMedicalEntryFB" property="isApproved" tabindex="1" onclick="setIsApproved(this)"></html:checkbox>-->
<!--						Is Result Approved-->
<!--					</font>-->
<!--				</div>	-->
<!--				</td>-->
<!--			</tr>-->
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="approvedBy"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
				<html:select name="postMedicalEntryFB" property="approvedBy" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.MB_CONSULTANT_LIST %>">
					<html:options collection="<%=MedicalBoardConfig.MB_CONSULTANT_LIST %>" labelProperty="label" property="value"/>
					</logic:present>
				</html:select>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="approvedDate"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<his:date name="approvedDate" value="<%=sysdate %>"></his:date>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="final"/> <bean:message key="opinion"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
				<html:select name="postMedicalEntryFB" property="opinionCode" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID %>">
					<html:options collection="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID %>" labelProperty="label" property="value"/>
					</logic:present>
				</html:select>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="final"/> <bean:message key="remark"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<html:textarea name="postMedicalEntryFB" property="finalRemark" cols="50" tabindex="1" onkeypress="return CheckMaxLength(event,this,100,1)"/>
				</td>
			</tr>
		</table>
		</div>
		</his:ContentTag>
		
	</logic:notEmpty>
		
	</div>
</his:statusTransactionInProcess>
	
	<his:ButtonToolBarTag>
         <div align="center">	
       			
       		<his:statusTransactionInProcess>
	          	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateSave();" tabindex="1" onclick ="validateSave();">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1" onclick ="clearForm();">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('GETCANDIDATELIST');" tabindex="1" onclick ="submitForm('GETCANDIDATELIST');">
	  			
	        </his:statusTransactionInProcess>
	   
	    
	        <his:statusList>
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	        </his:statusList>
	        <his:statusNew>
	        	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13)getCandidateList();" tabindex="1" onclick ="getCandidateList();">
	        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
	
         </div>
     </his:ButtonToolBarTag>
     
   <html:hidden name="postMedicalEntryFB" property="hmode"/>
   <html:hidden name="postMedicalEntryFB" property="patCrNo"/>
   <html:hidden name="postMedicalEntryFB" property="reqID"/>
   <html:hidden name="postMedicalEntryFB" property="slNo"/>
   <html:hidden name="postMedicalEntryFB" property="certificateTypeID"/>
   <html:hidden name="postMedicalEntryFB" property="certificateTypeName"/>
   <html:hidden name="postMedicalEntryFB" property="departmentUnitCode"/>
   <html:hidden name="postMedicalEntryFB" property="boardNo"/>
   <html:hidden name="postMedicalEntryFB" property="boardName"/>
   <html:hidden name="postMedicalEntryFB" property="currentPage"/>
   <html:hidden name="postMedicalEntryFB" property="lastVisitDate"/>
    <html:hidden name="postMedicalEntryFB" property="onlineOfflineFlag"/>
   <input type="hidden" name="sysdate" value="<%=sysdate%>">
   <html:hidden name="postMedicalEntryFB" property="patIsDocPresent"/>
<his:status/> 
	</body>     
   </html:form>

</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
