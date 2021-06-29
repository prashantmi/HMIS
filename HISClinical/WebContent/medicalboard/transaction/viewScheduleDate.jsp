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
<%@page import="medicalboard.transactions.controller.fb.MedicalBoardInitializationFB"%>
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

function submitPage(mode)
{
	//alert(mode);
	opener.document.getElementsByName("hmode")[0].value=mode;
	opener.document.forms[0].submit();
}

function doPagination(e,p)
{
	//alert("page number "+ p);
	document.getElementsByName('currentPage')[0].value=p;
	//opener.document.getElementsByName("popupCurrentPage")[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function setScheduleDate(mode){
	var len=document.getElementsByName("selectedSchedule").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedSchedule")[i].checked)
		{
			var minReqCount = parseInt(document.getElementsByName("minReqArray")[i].value);
			var maxReqCount = parseInt(document.getElementsByName("maxReqArray")[i].value);
			var regReqCount = parseInt(document.getElementsByName("registeredReqArray")[i].value);
			
			opener.document.getElementsByName("scheduleDate")[0].value=document.getElementsByName("selectedSchedule")[i].value;
			opener.document.getElementsByName("minReq")[0].value = minReqCount;
			opener.document.getElementsByName("maxReq")[0].value = maxReqCount;
			opener.document.getElementsByName("registeredReq")[0].value = regReqCount;
			
			var htmlCodeMessage = "";
			if(regReqCount<minReqCount)
				htmlCodeMessage="Registered Requisition is less than the Required Minimum Requisition";
			if(regReqCount>maxReqCount)
				htmlCodeMessage="Already Registered Requisition is greater than the Maximum Requisition";
			htmlCodeMessage="<font color='#FF0000' size='2' face='Verdana,Arial,Helvetica,sans-serif'><b>"+htmlCodeMessage+"</b></font>";
			opener.document.getElementsByName("divMessage")[0].value = htmlCodeMessage;
		}
	}
	//alert("mode"+ mode);
	submitPage(mode);
	self.close();
}


  
</script>

	
<his:TransactionContainer>
<html:form action="/medicalBoardInitialization">	   
	 <% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((MedicalBoardInitializationFB)request.getAttribute("MedicalBoardInitializationFB")).getCurrentPage());
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

		<tr>
		 <td width="5%" class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:radio name="MedicalBoardInitializationFB" property="selectedSchedule" value="<%=requisitionVO.getExamDate()%>" onclick="setScheduleDate('GETBOARDDETAIL')" tabindex="1"></html:radio>
			 <html:hidden name="MedicalBoardInitializationFB" property="minReqArray" value="<%=requisitionVO.getMinReq() %>"/>
			 <html:hidden name="MedicalBoardInitializationFB" property="maxReqArray" value="<%=requisitionVO.getMaxReq() %>"/>
			 <html:hidden name="MedicalBoardInitializationFB" property="registeredReqArray" value="<%=requisitionVO.getRegisteredReq() %>"/>
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
     
     <logic:present name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_LIST %>">
     <logic:empty name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_LIST %>">
     	<font color="#FF0000" size="2">
     		No Date Scheduled for this Certificate Type
     	</font>
     </logic:empty>
     </logic:present>

   <html:hidden name="MedicalBoardInitializationFB" property="hmode"/>
   <html:hidden name="MedicalBoardInitializationFB" property="currentPage" />
   
<his:status/>
</html:form>
</his:TransactionContainer>

