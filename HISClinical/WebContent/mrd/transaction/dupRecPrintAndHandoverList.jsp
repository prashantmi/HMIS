<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Anant Patel
 ## Module Name					: MRD
 ## Process/Database Object Name:Duplicate Record issue handover Request
 ## Purpose						:Duplicate Record handover Process
 ## Date of Creation			:20-Jan-2015 

 -->
 <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.vo.DupRecPrintReqVO"%>
<%@page import="mrd.transaction.controller.fb.DuplicateRecordPrintReqFB;"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function submitPage(mode)
{
	var elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


</script>

<html:form action="/dupRecPrintAndHandover">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>  
	<his:TitleTag name="Duplicate Record Printing And Handover">	
	</his:TitleTag>
	<his:SubTitleTag name="Duplicate Record Requests">
	</his:SubTitleTag>
		<% 	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination",fbPage);
			System.out.print("adsadadd:::"+((DuplicateRecordPrintReqFB)request.getAttribute("duplicateRecordPrintReqFB")).getCurrentPage());
			fbPage.setCurrentPage(((DuplicateRecordPrintReqFB)request.getAttribute("duplicateRecordPrintReqFB")).getCurrentPage());
			fbPage.setObjArrName(MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER);
			fbPage.setAppendInTitle("Records");
			fbPage.setMaxRecords(10);
		%>
	
    <logic:present name="<%=MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER%>">
    		<his:PaginationTag name="fbPagination"></his:PaginationTag>		
    	<his:ContentTag>
    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="patientName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordType"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="reqBy"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="requestedDate"/>
								</b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recDetail"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="approvedBy"/>
								</b>
							</font>
						</div>
					</td>
				
				</tr>
				<logic:notEmpty name="<%=MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER %>">
					<% String str="";
					DupRecPrintReqVO[] dupRecordRequestVo=(DupRecPrintReqVO[])session.getAttribute(MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER);
					
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					String fontCss="";
					boolean disabled=false;
					for(int i=startIndex;i<=endIndex;i++)
					{
						DupRecPrintReqVO voDupRecordRequest =(DupRecPrintReqVO)dupRecordRequestVo[i];
						
						str=voDupRecordRequest.getPatName()+"@"+voDupRecordRequest.getRecordType()+"@"+voDupRecordRequest.getEntryDate()+"@"
						+voDupRecordRequest.getRecordDesc()+"@"+voDupRecordRequest.getApprovedBy()+"@"+voDupRecordRequest.getPatCrNo()+"@"+voDupRecordRequest.getRemarks()+"@"
						+voDupRecordRequest.getReqReason()+"@"+voDupRecordRequest.getRequestByName()+"@"+voDupRecordRequest.getReqType()+"@"+voDupRecordRequest.getPatAge()
						+"@"+voDupRecordRequest.getPatContactNo()+"@"+voDupRecordRequest.getPatGenderCode()+"@"+voDupRecordRequest.getRequestBy()+"@"+voDupRecordRequest.getReqNo()+"@"+voDupRecordRequest.getHospitalCode();
						
					%>
				
						<tr>
							    
							
							
							<td width="5%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="duplicateRecordPrintReqFB" property="selectRecord" value='<%=str %>' onclick="submitPage('GETDTL')" ></html:radio>
								</font>
							</div>
							</td>													
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voDupRecordRequest.getPatName() %>
									</font>
								</div>	
							</td>
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voDupRecordRequest.getRecordType() %>
									</font>
								</div>	
							</td>
							<td width="15%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voDupRecordRequest.getRequestByName() %>
									</font>
								</div>	
							</td>
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voDupRecordRequest.getEntryDate() %>
									</font>
								</div>	
							</td>
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voDupRecordRequest.getRecordDesc() %>
									</font>
								</div>	
							</td>
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voDupRecordRequest.getApprovedBy() %>
									</font>
								</div>	
							</td>
							
							
									
									</tr>
									<%} %>
						
					</logic:notEmpty>
					<logic:empty name="<%=MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER %>">
						<tr>
							<div align="center">
								<td colspan="5">
								No Record Found							
								</td>
							<div align="left">
						</tr>
					</logic:empty>
					
			</table>		
    	</his:ContentTag>
    </logic:present>	
    	<his:ButtonToolBarTag>					
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
    	</his:ButtonToolBarTag>    
    <html:hidden name="duplicateRecordPrintReqFB" property="hmode"/>
    <html:hidden name="duplicateRecordPrintReqFB" property="currentPage"/>
    <html:hidden name="duplicateRecordPrintReqFB" property="selectRecord"/>
     
</body>
<his:status/>
</html:form>   