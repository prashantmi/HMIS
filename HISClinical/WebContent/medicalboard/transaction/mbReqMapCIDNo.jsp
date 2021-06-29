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

<%@page import="hisglobal.vo.DocReceivingFormDtlVO"%>
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

function fillCIDNo(obj)
{
	if(obj.checked)
	{
		opener.document.getElementsByName("CIDNo")[0].value=obj.value;
		opener.document.getElementById("divMappedCIDNo").innerHTML="Mapped CID No <font color='red'><b>'" + obj.value +"'</b></font>";
		
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

<his:statusList>
<logic:present name="<%=MedicalBoardConfig.REQUISITION_FILE_CID_LIST %>">   
		   <% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((MedicalBoardRequisitionFB)request.getAttribute("medicalBoardRequisitionFB")).getCurrentPage());
				fbPage.setObjArrName(MedicalBoardConfig.REQUISITION_FILE_CID_LIST);
				fbPage.setAppendInTitle("Records");
				fbPage.setMaxRecords(5);
			%>
		   
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
				<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="CIDNo"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="certificatetype"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="subject"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="recevied"/> <bean:message key="from"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="recevingDate"/></b>
						</font>
					</div>
	  			</td>
	  			<td class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="receivingTime"/></b>
						</font>
					</div>
	  			</td>
			</tr>
				<%
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					List cidList=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_FILE_CID_LIST);
					for(int i=startIndex;i<=endIndex;i++){
						DocReceivingFormDtlVO DocReceivingFormDtlvo=(DocReceivingFormDtlVO)cidList.get(i);
				%>
			<tr>
				<td class="tdfont" >
					<div align="center">
						<html:radio property="CIDNo" value="<%=DocReceivingFormDtlvo.getCIDNo() %>" onclick="fillCIDNo(this)"></html:radio>
					</div>
				</td>
				<td class="tdfont" >
					<div align="center">
						<%=DocReceivingFormDtlvo.getCIDNo() %>
					</div>
				</td>	
				<td class="tdfont" >
					<div align="center">
						<%=MedicalBoardConfig.FTS_NUM_CATEGORY_NAME_ARRAY[Integer.parseInt(DocReceivingFormDtlvo.getCategoryId())]%>
					</div>
				</td>	
				<td class="tdfont" >
					<div align="center">
						<%=DocReceivingFormDtlvo.getDocSubject() %>
					</div>
				</td>	
				<td class="tdfont" >
					<div align="center">
						<%=DocReceivingFormDtlvo.getReceiveFrom() %>
					</div>
				</td>	
				<td class="tdfont" >
					<div align="center">
						<%=DocReceivingFormDtlvo.getReceivingDate() %>
					</div>
				</td>	
				<td class="tdfont" >
					<div align="center">
						<%=DocReceivingFormDtlvo.getReceivingTime() %>
					</div>
				</td>	
			</tr>
		<%} %>
	 </table>
			
</his:ContentTag>
</logic:present>
<logic:empty name="<%=MedicalBoardConfig.REQUISITION_FILE_CID_LIST %>">
	No CID No Found
</logic:empty>
</his:statusList>	
	
 	<his:ButtonToolBarTag>
         <div align="center">	
             <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
	     </div>
     </his:ButtonToolBarTag>

   <html:hidden name="medicalBoardRequisitionFB" property="hmode"/>
   <html:hidden name="medicalBoardRequisitionFB" property="currentPage" />
   <html:hidden name="medicalBoardRequisitionFB" property="index" />

<his:status/>
</html:form>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
