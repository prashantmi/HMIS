<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="medicalboard.transactions.controller.fb.MedicalBoardRequisitionFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.MedicalBoardRequisitionVO"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function setScheduleDate(obj){
	
	index=document.getElementsByName("index")[0].value
	//alert(index)
	if(index=="-1"){
		for(var i=0;i<opener.document.getElementsByName("examDate").length;i++){
			opener.document.getElementsByName("examDate")[i].value=obj.value.split("#")[0]
		}	
	}
	else{
		opener.document.getElementsByName("examDate")[index].value=obj.value.split("#")[0]
	}	
	var maxReq=obj.value.split("#")[1]
	var registeredReq=obj.value.split("#")[2]
	//alert(maxReq+"  "+registeredReq);
	if( parseInt(registeredReq)>= parseInt(maxReq)){
		alert("Request has reached Maximum Limit")
	}	
	self.close();
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
  
</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalBoardRequisition">	   
		   
		   <% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((MedicalBoardRequisitionFB)request.getAttribute("medicalBoardRequisitionFB")).getCurrentPage());
				fbPage.setObjArrName(MedicalBoardConfig.REQUISITION_SCHEDULE_LIST);
				fbPage.setAppendInTitle("Records");
				fbPage.setMaxRecords(5);
			%>
		   <his:PaginationTag name="fbPagination"></his:PaginationTag>
<his:statusList>
   	<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_LIST %>">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="scheduleDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="minReq"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="maxReq"/></b>
			 </font>
			</div>
		  </td>
		  
		 <td width="17%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="registeredReq"/></b>
			 </font>
			</div>
		  </td>
		</tr>
				<%
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					List requisitionVOList=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_SCHEDULE_LIST);
					for(int i=startIndex;i<=endIndex;i++){
						MedicalBoardRequisitionVO requisitionVO=(MedicalBoardRequisitionVO)requisitionVOList.get(i);
				%>
		<%--<logic:iterate id="requisitionVO" name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_LIST %>" type="hisglobal.vo.MedicalBoardRequisitionVO" offset="<%=String.valueOf(startIndex)%>" length="<%=String.valueOf(endIndex)%>"> --%>
		<tr>
		 <td width="5%" class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:radio name="medicalBoardRequisitionFB" property="selectedSchedule" value='<%=requisitionVO.getExamDate()+"#"+requisitionVO.getMaxReq()+"#"+requisitionVO.getRegisteredReq() %>' onclick="setScheduleDate(this)" tabindex="1"></html:radio>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center">
				<%=requisitionVO.getExamDate() %>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		   <div align="center" >
			 <!--
			 <%=requisitionVO.getMinReq() %>
			 -->
			 <% if(requisitionVO.getMinReq()!=null) { %> <%=requisitionVO.getMinReq()%> <% } %>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  	<div align="center">
			 <!--
			 <%=requisitionVO.getMaxReq() %>
			  -->
			  <% if(requisitionVO.getMaxReq()!=null) { %> <%=requisitionVO.getMaxReq()%> <% } %>
			 </div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center">
			 <%=requisitionVO.getRegisteredReq() %>
			</div>
		  </td>
		  </tr>
		<%--</logic:iterate>--%>
		<%} %>
	 </table>
	</logic:present>		
</his:ContentTag>
</his:statusList>	
	
 	<his:ButtonToolBarTag>
         <div align="center">	
             <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
	     </div>
     </his:ButtonToolBarTag>

   <html:hidden name="medicalBoardRequisitionFB" property="hmode"/>
   <html:hidden name="medicalBoardRequisitionFB" property="patCrNo"/>
   <html:hidden name="medicalBoardRequisitionFB" property="departmentCode" />
   <html:hidden name="medicalBoardRequisitionFB" property="departmentUnitCode" />
   <html:hidden name="medicalBoardRequisitionFB" property="reqBy" />
   <html:hidden name="medicalBoardRequisitionFB" property="currentPage" />
   <html:hidden name="medicalBoardRequisitionFB" property="index" />

<his:status/>
</html:form>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
